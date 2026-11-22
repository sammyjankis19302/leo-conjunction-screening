"""Check the open-source pipeline against STK's own output for the July 2026 scenario.

1. Ephemeris. Each .sa file stores the ephemeris STK computed (ICRF, 60 s steps,
   10-11 Jul 2026) together with the TLE it used. SGP4 from the `sgp4` package, with
   Skyfield's TEME-to-GCRS rotation, is compared with it point by point.

2. Close approaches. AdvCAT screened all 33 satellites against each other, as well
   as against the catalogues. Three events involve two scenario satellites
   (ASTROSAT and CARTOSAT-2), each listed once per primary. The open-source
   screener, run on the same 33 satellites with the same ellipsoids, threshold and
   interval, has to find those events and nothing else, with the same numbers.

Writes data/results/stk_ephemeris_check.csv and data/results/stk_event_check.csv.
"""
import csv
import os
import sys

import numpy as np

ROOT = os.path.join(os.path.dirname(os.path.abspath(__file__)), "..")
sys.path.insert(0, ROOT)
from leo import stk                                   # noqa: E402
from leo.propagate import gcrs                        # noqa: E402
from leo.screening import screen                      # noqa: E402
from leo.timeutil import stk_format                   # noqa: E402

SCEN = os.path.join(ROOT, "stk", "Hope_2")
OUT = os.path.join(ROOT, "data", "results")


def ephemeris_check(sats):
    rows = []
    for s in sats:
        r, v = gcrs(s.tle, s.times)
        dr = np.linalg.norm(r - s.ephemeris[:, 1:4], axis=1)
        dv = np.linalg.norm(v - s.ephemeris[:, 4:7], axis=1)
        rows.append(dict(satellite=s.name, norad=s.ssc, points=len(dr), tle_epoch=s.tle.epoch.strftime("%Y-%m-%d %H:%M"),
                         max_dr_mm=dr.max() * 1e3, rms_dr_mm=np.sqrt(np.mean(dr ** 2)) * 1e3, max_dv_um_s=dv.max() * 1e6))
    return rows


def event_check(sats, cat, reported):
    tles = [s.tle for s in sats]
    ours = screen(tles, tles, cat.start, (cat.stop - cat.start).total_seconds(), step=60.0,
                  axes=cat.primary_axes_km, threshold=cat.threshold_km, ap_pad=cat.apogee_perigee_pad_km)
    names = {s.name for s in sats}
    stk_rows = [e for e in reported if e.secondary[:-3] in names]
    rows = []
    for e in stk_rows:
        # this export has no primary column; for scenario objects the pair is unambiguous
        other = e.secondary[:-3]
        match = [o for o in ours if o.secondary == other and abs((o.tca - e.time_in).total_seconds()) < 30]
        o = match[0] if match else None
        rows.append(dict(
            primary=o.primary if o else "", secondary=other,
            stk_time_in=stk_format(e.time_in), ours_time_in=stk_format(o.time_in) if o else "",
            d_time_in_ms=(o.time_in - e.time_in).total_seconds() * 1e3 if o else np.nan,
            stk_time_out=stk_format(e.time_out), ours_time_out=stk_format(o.time_out) if o else "",
            d_time_out_ms=(o.time_out - e.time_out).total_seconds() * 1e3 if o else np.nan,
            stk_min_sep_km="Intersect" if e.intersect else f"{e.min_separation_km:.6f}",
            ours_min_sep_km="Intersect" if (o and o.intersect) else (f"{o.min_separation_km:.6f}" if o else ""),
            stk_min_range_km=e.min_range_km, ours_min_range_km=o.min_range_km if o else np.nan,
            d_min_range_m=(o.min_range_km - e.min_range_km) * 1e3 if o else np.nan,
            ours_tca=stk_format(o.tca) if o else "", rel_speed_km_s=o.rel_speed_km_s if o else np.nan))
    extra = [o for o in ours if not any(r["secondary"] == o.secondary and r["ours_tca"] == stk_format(o.tca) for r in rows)]
    return rows, ours, extra


def write(path, rows):
    with open(path, "w", newline="") as f:
        w = csv.DictWriter(f, fieldnames=list(rows[0].keys()))
        w.writeheader()
        for r in rows:
            w.writerow({k: (f"{v:.6f}" if isinstance(v, float) else v) for k, v in r.items()})


def main():
    sats = stk.read_scenario(SCEN)
    cat = stk.read_advcat(os.path.join(SCEN, "AdvCAT1.ca"))
    reported = stk.read_close_approaches(os.path.join(ROOT, "stk", "advcat_close_approaches_2026-07-10.csv"))
    os.makedirs(OUT, exist_ok=True)

    eph = ephemeris_check(sats)
    write(os.path.join(OUT, "stk_ephemeris_check.csv"), eph)
    worst = max(eph, key=lambda r: r["max_dr_mm"])
    print(f"Ephemeris: {len(eph)} satellites x {eph[0]['points']} points, frame {sats[0].frame}")
    print(f"  largest position difference from STK: {worst['max_dr_mm']:.2f} mm ({worst['satellite']}); "
          f"largest velocity difference: {max(r['max_dv_um_s'] for r in eph):.2f} um/s")

    rows, ours, extra = event_check(sats, cat, reported)
    write(os.path.join(OUT, "stk_event_check.csv"), rows)
    print(f"\nClose approaches between scenario satellites ({cat.start:%d %b %Y %H:%M} + 24 h, "
          f"ellipsoid {cat.primary_axes_km} km, threshold {cat.threshold_km:g} km)")
    print(f"  AdvCAT lists {len(rows)}; the open-source screener finds {len(ours)}; unmatched: {len(extra)}")
    for r in rows:
        print(f"  {r['primary']:>16s} vs {r['secondary']:<16s} range {r['stk_min_range_km']:10.6f} | {r['ours_min_range_km']:10.6f} km "
              f"({r['d_min_range_m']:+.2f} m)   sep {r['stk_min_sep_km']:>9s} | {r['ours_min_sep_km']:>9s}   "
              f"in {r['d_time_in_ms']:+.1f} ms  out {r['d_time_out_ms']:+.1f} ms")


if __name__ == "__main__":
    main()
