"""How far off were the July 2026 positions?

STK picked the newest element set it had for each satellite: 29 March 2017, from
the TLE database bundled with STK 11.2. This script compares those element sets
with ones from May 2026 (CelesTrak's active-satellite list, downloaded for the
project) for the 18 scenario satellites that list contains.

For each satellite it reports, at the 2026 epoch, where the 2017 element set puts
it compared with the 2026 one: the total distance, the difference in position
around the orbit, and the angle between the two orbit planes. It then compares
both at the scenario epoch, 10 Jul 2026 06:30 UTC.

Writes data/tle/scenario_2017-03-29.tle, data/tle/scenario_2026-05-01.tle and
data/results/tle_age.csv.
"""
import csv
import glob
import os
import sys
from datetime import datetime, timezone

import numpy as np

ROOT = os.path.join(os.path.dirname(os.path.abspath(__file__)), "..")
sys.path.insert(0, ROOT)
from leo import stk                                   # noqa: E402
from leo.propagate import state                       # noqa: E402
from leo.timeutil import jd_parts                     # noqa: E402
from leo.tle import read_tles, write_tles             # noqa: E402

SCEN_EPOCH = datetime(2026, 7, 10, 6, 30, tzinfo=timezone.utc)
ACTIVE = os.path.join(ROOT, "data", "tle", "celestrak_active_2026-05-01.tle")


def arg_latitude(r, v):
    h = np.cross(r, v)
    n = np.cross([0.0, 0.0, 1.0], h)
    n /= np.linalg.norm(n)
    hu = h / np.linalg.norm(h)
    return np.degrees(np.arctan2(np.cross(n, r) @ hu, n @ r)) % 360.0


def compare(old, new, when):
    r1, v1 = state(old, [when])
    r2, v2 = state(new, [when])
    r1, v1, r2, v2 = r1[0], v1[0], r2[0], v2[0]
    du = (arg_latitude(r1, v1) - arg_latitude(r2, v2) + 180.0) % 360.0 - 180.0
    h1, h2 = np.cross(r1, v1), np.cross(r2, v2)
    plane = np.degrees(np.arccos(np.clip(h1 @ h2 / np.linalg.norm(h1) / np.linalg.norm(h2), -1, 1)))
    return np.linalg.norm(r1 - r2), du, plane


def main():
    sats = stk.read_scenario(os.path.join(ROOT, "stk", "Hope_2"))
    old = [s.tle for s in sats]
    write_tles(os.path.join(ROOT, "data", "tle", "scenario_2017-03-29.tle"), old,
               "Element sets STK 11.2 used for the 33 satellites of the Hope_2 scenario (10-11 Jul 2026).\n"
               "Extracted from stk/Hope_2/*.sa by analysis/tle_age.py. Epochs: 28-29 Mar 2017.")
    active = {t.norad: t for t in read_tles(ACTIVE)}
    found = [(t, active[t.norad]) for t in old if t.norad in active]
    new = []
    for o, n in found:
        n.name = o.name
        new.append(n)
    write_tles(os.path.join(ROOT, "data", "tle", "scenario_2026-05-01.tle"), new,
               "May 2026 element sets for the 18 Hope_2 satellites in CelesTrak's active list,\n"
               "taken from data/tle/celestrak_active_2026-05-01.tle by analysis/tle_age.py.")
    rows = []
    for o in old:
        n = active.get(o.norad)
        row = dict(satellite=o.name, norad=o.norad, epoch_used=o.epoch.strftime("%Y-%m-%d"),
                   alt_2017_km=(o.a - 6378.135), in_active_2026=n is not None)
        if n is not None:
            d_ep, du_ep, pl_ep = compare(o, n, n.epoch)
            d_sc, du_sc, pl_sc = compare(o, n, SCEN_EPOCH)
            row.update(epoch_2026=n.epoch.strftime("%Y-%m-%d %H:%M"), alt_2026_km=n.a - 6378.135,
                       d_alt_km=n.a - o.a, dist_at_2026_epoch_km=d_ep, phase_at_2026_epoch_deg=du_ep,
                       plane_angle_deg=pl_ep, dist_at_scenario_km=d_sc, phase_at_scenario_deg=du_sc)
        rows.append(row)
    keys = ["satellite", "norad", "epoch_used", "alt_2017_km", "in_active_2026", "epoch_2026", "alt_2026_km", "d_alt_km",
            "dist_at_2026_epoch_km", "phase_at_2026_epoch_deg", "plane_angle_deg", "dist_at_scenario_km",
            "phase_at_scenario_deg"]
    with open(os.path.join(ROOT, "data", "results", "tle_age.csv"), "w", newline="") as f:
        w = csv.DictWriter(f, fieldnames=keys)
        w.writeheader()
        for r in rows:
            w.writerow({k: (f"{r[k]:.3f}" if isinstance(r.get(k), float) else r.get(k, "")) for k in keys})

    have = [r for r in rows if r["in_active_2026"]]
    d = np.array([r["dist_at_2026_epoch_km"] for r in have])
    ph = np.abs([r["phase_at_2026_epoch_deg"] for r in have])
    pl = np.array([r["plane_angle_deg"] for r in have])
    print(f"{len(old)} satellites; {len(have)} have a May 2026 element set in CelesTrak's active list")
    print(f"At the 2026 epoch the 2017 element sets are off by {d.min():.0f}-{d.max():.0f} km (median {np.median(d):.0f} km)")
    print(f"  position around the orbit: {ph.min():.0f}-{ph.max():.0f} deg off (median {np.median(ph):.0f} deg); "
          f"orbit planes {pl.min():.1f}-{pl.max():.1f} deg apart (median {np.median(pl):.1f} deg)")
    for r in sorted(have, key=lambda r: -r["dist_at_2026_epoch_km"]):
        print(f"  {r['satellite']:20s} {r['dist_at_2026_epoch_km']:8.0f} km  phase {r['phase_at_2026_epoch_deg']:+7.1f} deg  "
              f"plane {r['plane_angle_deg']:5.1f} deg  altitude {r['alt_2017_km']:5.0f} -> {r['alt_2026_km']:5.0f} km")
    print("Not in the 2026 active list:", ", ".join(r["satellite"] for r in rows if not r["in_active_2026"]))

    # SGP4 flags a satellite as decayed (error 6) when its radius falls below one Earth radius
    for s in sats:
        jd, fr = jd_parts(s.times)
        err, _, _ = s.tle.sat.sgp4_array(jd, fr)
        if err.any():
            lo, hi = s.tle.epoch, s.times[0]
            while (hi - lo).total_seconds() > 3600:              # first instant SGP4 reports decay
                mid = lo + (hi - lo) / 2
                j, f = jd_parts([mid])
                e, _, _ = s.tle.sat.sgp4_array(j, f)
                lo, hi = (lo, mid) if e[0] else (mid, hi)
            rad = np.linalg.norm(s.ephemeris[:, 1:4], axis=1) / 1e3
            print(f"{s.name}: SGP4 error {int(err.max())} ('decayed') at every scenario time; from this element set it "
                  f"first reports decay on {hi:%d %b %Y}. STK's stored ephemeris puts it {6378.137 - rad.max():.0f}-"
                  f"{6378.137 - rad.min():.0f} km below the equatorial radius.")

    # two objects in the team's files have element sets a few days apart: what does a few days cost?
    K = os.path.join(ROOT, "data", "tle")
    pairs = [("COSMOS 2251", "cosmos-2251_parent_2026-04-25.tle", "cosmos-2251_debris_2026-04.tle", 22675),
             ("IRIDIUM 33", "iridium-33_debris_2026-04.tle", "iridium-33_parent_2026-04-25.tle", 24946)]
    for name, f_old, f_new, nid in pairs:
        a = [t for t in read_tles(os.path.join(K, f_old)) if t.norad == nid][0]
        b = [t for t in read_tles(os.path.join(K, f_new)) if t.norad == nid][0]
        o, n = (a, b) if a.epoch < b.epoch else (b, a)
        dist, du, pl = compare(o, n, n.epoch)
        print(f"{name}: element set {(n.epoch - o.epoch).total_seconds() / 86400:.2f} days old is off by {dist:.2f} km at the newer epoch")


if __name__ == "__main__":
    main()
