"""The July screening, redone with element sets that match the date.

Primaries: the 18 Hope_2 satellites that have a May 2026 element set.
Secondaries: CelesTrak's active list (May 2026) plus the catalogued debris of
COSMOS 2251 and IRIDIUM 33. Element sets more than seven days older than the
window are left out, because SGP4 errors grow with age.

Same safety ellipsoids (20 x 10 x 5 km), same 10 km threshold, same definitions
as AdvCAT, over 24 hours starting 1 May 2026 12:00 UTC, right at the epoch of
most of the element sets.

What this catalogue lacks: inactive satellites, rocket bodies, and all debris
other than the two clouds. The event count is therefore a floor, not a total.

Writes data/results/screening_2026-05-01.csv.
"""
import csv
import os
import sys
import time
from datetime import datetime, timedelta, timezone

import numpy as np

ROOT = os.path.join(os.path.dirname(os.path.abspath(__file__)), "..")
sys.path.insert(0, ROOT)
from leo.screening import screen                      # noqa: E402
from leo.timeutil import stk_format                   # noqa: E402
from leo.tle import read_tles, unique_by_norad        # noqa: E402

T = os.path.join(ROOT, "data", "tle")
START = datetime(2026, 5, 1, 12, 0, tzinfo=timezone.utc)
HOURS = 24
MAX_AGE = timedelta(days=7)


def load():
    prim = read_tles(os.path.join(T, "scenario_2026-05-01.tle"))
    sec = (read_tles(os.path.join(T, "celestrak_active_2026-05-01.tle"))
           + read_tles(os.path.join(T, "cosmos-2251_debris_2026-04.tle"))
           + read_tles(os.path.join(T, "iridium-33_debris_2026-04.tle")))
    sec = unique_by_norad(sec)
    fresh = [s for s in sec if START - s.epoch <= MAX_AGE]
    return prim, sec, fresh


def main():
    prim, sec, fresh = load()
    print(f"{len(prim)} primaries; {len(sec)} secondaries, {len(fresh)} within {MAX_AGE.days} days of the window "
          f"({len(sec) - len(fresh)} older ones left out)")
    t0 = time.time()
    ev = screen(prim, fresh, START, HOURS * 3600.0, step=60.0, axes=(20.0, 10.0, 5.0), threshold=10.0, ap_pad=30.0,
                progress=lambda done, n, k: print(f"  {done}/{n} secondaries screened, {k} events, "
                                                  f"{time.time() - t0:.0f} s", flush=True))
    ev.sort(key=lambda e: (e.primary, e.tca))
    path = os.path.join(ROOT, "data", "results", "screening_2026-05-01.csv")
    with open(path, "w", newline="") as f:
        w = csv.writer(f)
        w.writerow(["primary", "secondary", "secondary_norad", "time_in_utc", "time_out_utc", "tca_utc",
                    "min_separation_km", "min_range_km", "rel_speed_km_s"])
        for e in ev:
            w.writerow([e.primary, e.secondary, e.secondary_norad, stk_format(e.time_in), stk_format(e.time_out),
                        stk_format(e.tca), "Intersect" if e.intersect else f"{e.min_separation_km:.6f}",
                        f"{e.min_range_km:.6f}", f"{e.rel_speed_km_s:.4f}"])
    print(f"\n{len(ev)} events in {HOURS} h; {sum(e.intersect for e in ev)} with intersecting ellipsoids; "
          f"wrote {os.path.relpath(path, ROOT)}")
    for p in prim:
        mine = [e for e in ev if e.primary == p.name]
        if mine:
            c = min(mine, key=lambda e: e.min_range_km)
            print(f"  {p.name:20s} {len(mine):4d} events  closest {c.min_range_km:7.3f} km with {c.secondary} ({c.secondary_norad})")
        else:
            print(f"  {p.name:20s}    0 events")


if __name__ == "__main__":
    main()
