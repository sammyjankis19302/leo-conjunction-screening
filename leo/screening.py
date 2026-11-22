"""AdvCAT-style conjunction screening with open-source SGP4.

The steps follow what AdvCAT does, with the report definitions that
analysis/validate_against_stk.py recovers from the STK output:

1. Apogee/perigee filter: a secondary can only come close if its altitude band,
   padded, overlaps the primary's.
2. Coarse pass: propagate everything on a fixed grid and flag every interval in
   which the range rate d.(v2 - v1) changes sign from negative to positive. Each
   one brackets a local minimum of the range.
3. Refinement: find the time of closest approach and the minimum range with a
   bounded scalar minimisation on the full SGP4 states.
4. Ellipsoids: report the event if the gap between the safety ellipsoids drops
   below the threshold. Time In and Time Out are the two instants at which the gap
   equals the threshold; Min Separation is the smallest gap, or "Intersect" if
   the ellipsoids overlap.
"""
from dataclasses import dataclass
from datetime import timedelta

import numpy as np
from scipy.optimize import brentq, minimize_scalar

from . import ellipsoid as ell
from .propagate import state, states
from .timeutil import grid


@dataclass
class Event:
    primary: str
    secondary: str
    secondary_norad: int
    tca: object
    min_range_km: float
    rel_speed_km_s: float
    intersect: bool
    min_separation_km: float      # nan when intersect
    time_in: object
    time_out: object


class _Pair:
    """Continuous-time access to one primary/secondary pair through SGP4."""

    def __init__(self, p, s, t0, axes_p, axes_s):
        self.p, self.s, self.t0 = p, s, t0
        self.axes_p, self.axes_s = axes_p, axes_s

    def rv(self, sec):
        t = [self.t0 + timedelta(seconds=float(sec))]
        rp, vp = state(self.p, t)
        rs, vs = state(self.s, t)
        return rp[0], vp[0], rs[0], vs[0]

    def range(self, sec):
        rp, vp, rs, vs = self.rv(sec)
        return np.linalg.norm(rs - rp)

    def geometry(self, sec):
        rp, vp, rs, vs = self.rv(sec)
        return rs - rp, ell.shape_matrix(rp, vp, self.axes_p), ell.shape_matrix(rs, vs, self.axes_s)

    def gap(self, sec):
        d, M1, M2 = self.geometry(sec)
        return ell.gap(d, M1, M2)[0]

    def contact(self, sec):
        d, M1, M2 = self.geometry(sec)
        return ell.contact(d, M1, M2)


def refine(pair, a, b, threshold):
    """Closest approach in [a, b] (seconds from t0), then the ellipsoid quantities."""
    res = minimize_scalar(pair.range, bounds=(a, b), method="bounded", options={"xatol": 1e-5})
    tca, rmin = res.x, res.fun
    rp, vp, rs, vs = pair.rv(tca)
    vrel = np.linalg.norm(vs - vp)
    reach = max(pair.axes_p) + max(pair.axes_s)
    if rmin > threshold + reach:
        return None                                  # the surfaces cannot get within the threshold
    # the gap is smallest close to, but not exactly at, the time of closest approach
    span = (threshold + reach) / max(vrel, 1e-3)
    c = minimize_scalar(pair.contact, bounds=(tca - span, tca + span), method="bounded", options={"xatol": 1e-5})
    intersect = c.fun < 1.0
    if intersect:
        sep = float("nan")
    else:
        g = minimize_scalar(pair.gap, bounds=(tca - span, tca + span), method="bounded", options={"xatol": 1e-5})
        sep = g.fun
        if sep >= threshold:
            return None

    def edge(direction):
        step = max(0.05, 0.2 * span)
        x0 = tca
        x1 = tca + direction * step
        for _ in range(400):
            if pair.gap(x1) >= threshold:
                break
            x0, x1 = x1, x1 + direction * step
        f = lambda x: pair.gap(x) - threshold
        return brentq(f, min(x0, x1), max(x0, x1), xtol=1e-5)

    t_in, t_out = edge(-1), edge(+1)
    t = lambda x: pair.t0 + timedelta(seconds=float(x))
    return Event(pair.p.name, pair.s.name, pair.s.norad, t(tca), rmin, vrel, intersect, sep, t(t_in), t(t_out))


def screen(primaries, secondaries, start, seconds, step=60.0, axes=(20.0, 10.0, 5.0), secondary_axes=None,
           threshold=10.0, ap_pad=30.0, chunk=1500, progress=None):
    """Screen every primary against every secondary over [start, start + seconds]."""
    secondary_axes = axes if secondary_axes is None else secondary_axes
    times = grid(start, seconds, step)
    rp, vp, ep = states(primaries, times)
    events = []
    bound = threshold + max(axes) + max(secondary_axes) + 25.0     # generous coarse-pass bound, km
    peri = np.array([s.perigee_alt for s in secondaries])
    apo = np.array([s.apogee_alt for s in secondaries])
    for c0 in range(0, len(secondaries), chunk):
        sub = secondaries[c0:c0 + chunk]
        rs, vs, es = states(sub, times)
        ok_s = ~es.any(axis=1)
        for i, p in enumerate(primaries):
            if ep[i].any():
                continue
            band = (peri[c0:c0 + chunk] - ap_pad <= p.apogee_alt) & (apo[c0:c0 + chunk] + ap_pad >= p.perigee_alt)
            idx = np.nonzero(band & ok_s & np.array([s.norad != p.norad for s in sub]))[0]
            if len(idx) == 0:
                continue
            d = rs[idx] - rp[i][None]
            w = vs[idx] - vp[i][None]
            rr = np.einsum("mtk,mtk->mt", d, w)
            m_idx, t_idx = np.nonzero((rr[:, :-1] < 0) & (rr[:, 1:] >= 0))
            d0, w0 = d[m_idx, t_idx], w[m_idx, t_idx]
            tau = np.clip(-np.einsum("nk,nk->n", d0, w0) / np.einsum("nk,nk->n", w0, w0), 0, step)
            est = np.linalg.norm(d0 + w0 * tau[:, None], axis=1)
            for k in np.nonzero(est < bound)[0]:
                s = sub[idx[m_idx[k]]]
                a = t_idx[k] * step
                ev = refine(_Pair(p, s, start, axes, secondary_axes), a, a + step, threshold)
                if ev is not None:
                    events.append(ev)
        if progress:
            progress(min(c0 + chunk, len(secondaries)), len(secondaries), len(events))
    return events
