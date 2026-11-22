# Data

*[← Conjunction screening for a LEO digital twin](../README.md)*

## Element sets: `tle/`

Each file begins with comment lines (`#`) that say where it came from.

| File | Contents | Source |
|---|---|---|
| `scenario_2017-03-29.tle` | the 33 element sets STK used in the July scenario, epochs 28–29 Mar 2017 | extracted from `stk/Hope_2/*.sa` by `analysis/tle_age.py` |
| `scenario_2026-05-01.tle` | May 2026 element sets for the 18 of those satellites in CelesTrak's active list | taken from the next file by `analysis/tle_age.py` |
| `celestrak_active_2026-05-01.tle` | CelesTrak's active-satellite list, 15,307 objects, epochs 7 Apr–4 May 2026 (98 % on 1 May) | CelesTrak, downloaded for the project; from the software side's repository (`orbital_sim/active_sats.txt`) |
| `cosmos-2251_debris_2026-04.tle` | COSMOS 2251 and 584 catalogued fragments (1993-036), epochs 4–30 Apr 2026 | CelesTrak, via the software side's repository |
| `iridium-33_debris_2026-04.tle` | IRIDIUM 33 and 107 catalogued fragments (1997-051), epochs 9–25 Apr 2026 | CelesTrak, via the software side's repository |
| `cosmos-2251_parent_2026-04-25.tle`, `iridium-33_parent_2026-04-25.tle` | the two parents at a second epoch, for the age comparison | the software side's repository |

## Results: `results/`

Every file here is written by a script in [`analysis/`](../analysis/) and can be
regenerated.

| File | Written by | One row per |
|---|---|---|
| `stk_ephemeris_check.csv` | `validate_against_stk.py` | satellite: largest and RMS position difference from STK, largest velocity difference |
| `stk_event_check.csv` | `validate_against_stk.py` | AdvCAT event between scenario satellites, with STK's and our Time In, Time Out, Min Separation, Min Range |
| `tle_age.csv` | `tle_age.py` | scenario satellite: 2017 and 2026 altitude, distance, position around the orbit and plane angle between the two predictions |
| `screening_2026-05-01.csv` | `screen_2026.py` | close approach in the 2026 screening, in the same columns as AdvCAT's report plus the time of closest approach and relative speed |
| `debris_clouds.csv` | `debris_clouds.py` | catalogued fragment: orbit, perigee and apogee, J2 node regression rate |

Times are UTC, distances km, speeds km/s.
