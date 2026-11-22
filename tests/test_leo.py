"""Checks against STK's own output, against closed-form geometry, and against
facts about the scenario. Run:  python3 tests/test_leo.py"""
import os
import sys
from datetime import datetime, timezone

import numpy as np

ROOT = os.path.join(os.path.dirname(os.path.abspath(__file__)), "..")
sys.path.insert(0, ROOT)
from leo import ellipsoid as ell                     # noqa: E402
from leo import stk                                  # noqa: E402
from leo.propagate import gcrs, state                # noqa: E402
from leo.screening import screen                     # noqa: E402
from leo.timeutil import jd_parts, stk_time          # noqa: E402
from leo.tle import TLE, read_tles                   # noqa: E402

SCEN = os.path.join(ROOT, "stk", "Hope_2")
CSV = os.path.join(ROOT, "stk", "advcat_close_approaches_2026-07-10.csv")
TLE_DIR = os.path.join(ROOT, "data", "tle")

YOUTHSAT = TLE("YOUTHSAT", "1 37388U 11015B   17088.84885257 -.00000196  00000-0 -66383-4 0  9996",
               "2 37388  98.5689 163.9434 0016373 120.3219 239.9559 14.24162678308884")


def test_tle_fields():
    assert YOUTHSAT.norad == 37388 and YOUTHSAT.intl == "11015B"
    assert YOUTHSAT.epoch.strftime("%Y-%m-%d %H:%M") == "2017-03-29 20:22"
    assert abs(YOUTHSAT.inc_deg - 98.5689) < 1e-9
    assert 795 < YOUTHSAT.perigee_alt < YOUTHSAT.apogee_alt < 830          # an 810 km sun-synchronous orbit
    assert abs(YOUTHSAT.period_min - 1440 / 14.24162678) < 0.01


def test_stk_time_with_nine_decimals():
    t = stk_time("10 Jul 2026 06:30:00.123456789")
    assert t == datetime(2026, 7, 10, 6, 30, 0, 123456, tzinfo=timezone.utc)


def test_sgp4_reproduces_stk_ephemeris():
    for name in ("YOUTHSAT_37388", "RISAT-2_34807", "QSAT-EOS_40301"):
        s = stk.read_satellite(os.path.join(SCEN, name + ".sa"))
        assert s.frame == "ICRF" and len(s.ephemeris) == 1441
        r, v = gcrs(s.tle, s.times)
        assert np.abs(r - s.ephemeris[:, 1:4]).max() < 2e-3, name              # metres: under 2 mm
        assert np.abs(v - s.ephemeris[:, 4:7]).max() < 2e-6, name              # m/s


def test_scenario_used_2017_element_sets():
    sats = stk.read_scenario(SCEN)
    assert len(sats) == 33
    assert all(s.tle.epoch.year == 2017 and s.tle.epoch.month == 3 for s in sats)
    risat2 = [s for s in sats if s.ssc == 34807][0]                          # re-entered 30 Oct 2022
    radius = np.linalg.norm(risat2.ephemeris[:, 1:4], axis=1) / 1e3
    assert radius.max() < 6378.137 - 400                                     # STK drew it ~430 km underground
    jd, fr = jd_parts(risat2.times)
    err, _, _ = risat2.tle.sat.sgp4_array(jd, fr)
    assert (err == 6).all()                                                  # SGP4's own "decayed" flag


def test_advcat_settings():
    cat = stk.read_advcat(os.path.join(SCEN, "AdvCAT1.ca"))
    assert cat.primary_axes_km == (20.0, 10.0, 5.0) and cat.threshold_km == 10.0
    assert len(cat.primaries) == 33
    assert cat.secondary_catalogues == ["stkSatDbAll.tce", "stkSatDb.tce", "stkAllTLE.tce", "stkAllComm.tce"]
    assert (cat.stop - cat.start).total_seconds() == 86400


def test_close_approach_export():
    ev = stk.read_close_approaches(CSV)
    assert len(ev) == 4312 and len({(e.secondary, e.time_in, e.min_range_km) for e in ev}) == 1955
    blocks = stk.split_by_primary(ev)
    first = blocks[0][0]                                    # the YOUTHSAT event in the screenshots
    assert first.secondary == "tle 39777" and first.intersect and abs(first.min_range_km - 1.791183) < 1e-9
    assert first.time_out.strftime("%H:%M:%S.%f")[:-3] == "07:45:56.934"


def test_contact_function_closed_forms():
    I = np.eye(3)
    d = np.array([3.0, 0.0, 0.0])
    assert abs(ell.contact(d, I, I) - 9 / 4) < 1e-9                         # spheres: d^2 / (r1 + r2)^2
    assert abs(ell.gap(d, I, I)[0] - 1.0) < 1e-7
    r, v = np.array([7000.0, 0, 0]), np.array([0, 7.5, 0])                  # tangential axis along y
    M = ell.shape_matrix(r, v, (20, 10, 5))
    assert abs(ell.gap(np.array([0, 50.0, 0]), M, M)[0] - 10.0) < 1e-6        # 50 - 20 - 20
    assert abs(ell.gap(np.array([20.0, 0, 0]), M, M)[0] - 10.0) < 1e-6        # radial: 20 - 5 - 5
    assert ell.overlap(np.array([0, 39.0, 0]), M, M) and not ell.overlap(np.array([0, 41.0, 0]), M, M)


def test_screener_reproduces_advcat():
    a = stk.read_satellite(os.path.join(SCEN, "ASTROSAT_40930.sa")).tle
    c = stk.read_satellite(os.path.join(SCEN, "CARTOSAT-2_29710.sa")).tle
    cat = stk.read_advcat(os.path.join(SCEN, "AdvCAT1.ca"))
    ours = screen([c], [a], cat.start, 86400.0)
    ref = sorted({(e.time_in, e.time_out, e.min_separation_km, e.intersect, e.min_range_km)
                  for e in stk.read_close_approaches(CSV) if e.secondary == "ASTROSAT_40930.sa"})
    assert len(ours) == len(ref) == 3
    for o, (t_in, t_out, sep, inter, rng) in zip(sorted(ours, key=lambda e: e.tca), ref):
        assert abs(o.min_range_km - rng) < 1e-3                             # 1 m
        assert abs((o.time_in - t_in).total_seconds()) < 2e-3                # 2 ms
        assert abs((o.time_out - t_out).total_seconds()) < 2e-3
        assert o.intersect == inter
        if not inter:
            assert abs(o.min_separation_km - sep) < 1e-3


def test_old_element_sets_are_thousands_of_km_off():
    new = {t.norad: t for t in read_tles(os.path.join(TLE_DIR, "scenario_2026-05-01.tle"))}[37388]
    r1, _ = state(YOUTHSAT, [new.epoch])
    r2, _ = state(new, [new.epoch])
    assert np.linalg.norm(r1 - r2) > 1000.0


def test_debris_planes_regress_as_cos_i():
    cos = read_tles(os.path.join(TLE_DIR, "cosmos-2251_debris_2026-04.tle"))
    iri = read_tles(os.path.join(TLE_DIR, "iridium-33_debris_2026-04.tle"))
    assert {t.intl[:5] for t in cos} == {"93036"} and {t.intl[:5] for t in iri} == {"97051"}
    ratio = np.median([t.nodal_rate() for t in cos]) / np.median([t.nodal_rate() for t in iri])
    assert abs(ratio / (np.cos(np.radians(74.0)) / np.cos(np.radians(86.35))) - 1) < 0.08


if __name__ == "__main__":
    tests = [(k, v) for k, v in sorted(globals().items()) if k.startswith("test_")]
    for name, fn in tests:
        fn()
        print(f"  ok  {name}")
    print(f"{len(tests)} passed")
