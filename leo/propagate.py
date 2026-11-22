"""SGP4 propagation.

SGP4 returns position and velocity in TEME, a quasi-inertial frame. Ranges,
relative speeds and the ellipsoid geometry used for screening do not depend on
the frame, so screening stays in TEME. Only the comparison with STK's stored
ICRF ephemeris needs a rotation, and Skyfield does that.
"""
import numpy as np
from sgp4.api import SatrecArray

from .timeutil import jd_parts


def states(tles, times):
    """TEME positions (km) and velocities (km/s), shape (n_sat, n_time, 3), plus SGP4 error codes."""
    jd, fr = jd_parts(times)
    arr = SatrecArray([t.sat for t in tles])
    err, r, v = arr.sgp4(jd, fr)
    return r, v, err


def state(tle, times):
    """TEME position and velocity of one satellite, shape (n_time, 3)."""
    r, v, err = states([tle], times)
    if err.any():
        raise RuntimeError(f"SGP4 error {int(err.max())} for {tle.name}")
    return r[0], v[0]


def gcrs(tle, times):
    """Position (m) and velocity (m/s) in GCRS, which agrees with ICRF here, via Skyfield."""
    from skyfield.api import EarthSatellite, load

    ts = load.timescale(builtin=True)
    sat = EarthSatellite(tle.line1, tle.line2, tle.name, ts)
    g = sat.at(ts.from_datetimes(list(times)))
    return g.position.m.T, g.velocity.m_per_s.T
