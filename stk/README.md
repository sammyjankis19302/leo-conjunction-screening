# The STK scenario

*[← Conjunction screening for a LEO digital twin](../README.md)*

`Hope_2` is the final scenario of the aerospace half of the project. It was
built in STK 11.2 (AGI's Systems Tool Kit) and saved on 10 July 2026. The folder
is complete, so it opens in STK 11 or later through **File → Open → `Hope_2.sc`**.

```
stk/
├── Hope_2/
│   ├── Hope_2.sc, .sc3, .sca         the scenario: epoch, interval, globe and 3D-window settings
│   ├── <SATELLITE>_<NORAD>.sa, .sa3  33 satellites; each .sa holds the element set used and the ephemeris computed
│   ├── AdvCAT1.ca, .ca3              the conjunction screening
│   └── ... workbook, timeline and atmosphere files STK writes alongside
└── advcat_close_approaches_2026-07-10.csv   AdvCAT's report, as exported for the software side
```

## Settings

| | |
|---|---|
| Analysis interval | 10 Jul 2026 06:30:00 – 11 Jul 2026 06:30:00 UTC |
| Propagator | SGP4 for every satellite, 60 s ephemeris steps, stored in ICRF |
| Element sets | from STK's local database `stkAllTLE.tce`, epochs 28–29 Mar 2017 (see below) |
| AdvCAT primaries | all 33 satellites |
| AdvCAT secondaries | the four STK catalogues `stkSatDbAll.tce`, `stkSatDb.tce`, `stkAllTLE.tce`, `stkAllComm.tce`, plus the 33 satellites |
| Safety ellipsoid | fixed, 20 km tangential × 10 km cross-track × 5 km normal, for primaries and secondaries |
| Close-approach threshold | 10 km, applied to the gap between ellipsoid surfaces |
| Filters | apogee/perigee filter on (30 km pad), time filter on (30 km pad), path filter off, out-of-date filter off |
| Sampling | adaptive, 1 s to 300 s |
| Hard-body radius | 1 m |

## The satellites

\* Indian satellite.

| Satellite | NORAD | Element set used | Inclination | Mean altitude | In May 2026 active list |
|---|---|---|---|---|---|
| ADEOS-II | 27597 | 29 Mar 2017 | 98.4° | 801 km | no |
| ADEOS | 24277 | 29 Mar 2017 | 98.7° | 794 km | no |
| AISAT | 40054 | 29 Mar 2017 | 98.2° | 639 km | no |
| ASTROSAT * | 40930 | 29 Mar 2017 | 6.0° | 642 km | yes |
| CARTOSAT-1 * | 28649 | 29 Mar 2017 | 97.9° | 618 km | yes |
| CARTOSAT-2A * | 32783 | 29 Mar 2017 | 97.9° | 634 km | yes |
| CARTOSAT-2B * | 36795 | 29 Mar 2017 | 97.9° | 634 km | yes |
| CARTOSAT-2C * | 41599 | 29 Mar 2017 | 97.5° | 508 km | yes |
| CARTOSAT-2D * | 41948 | 29 Mar 2017 | 97.5° | 508 km | yes |
| CARTOSAT-2 * | 29710 | 29 Mar 2017 | 97.9° | 634 km | no |
| DUBAISAT-1 | 35682 | 29 Mar 2017 | 97.8° | 666 km | yes |
| DUBAISAT-2 | 39419 | 29 Mar 2017 | 97.7° | 587 km | yes |
| DZZ-HR | 39731 | 29 Mar 2017 | 98.4° | 752 km | yes |
| FAISAT | 23465 | 29 Mar 2017 | 82.9° | 993 km | no |
| FAISAT_2V | 24954 | 29 Mar 2017 | 82.9° | 964 km | no |
| GEODETIC_EARTH | 7734 | 29 Mar 2017 | 115.0° | 830 km | no |
| GEOSAT | 15595 | 29 Mar 2017 | 108.0° | 765 km | no |
| GEOS_2 | 3093 | 29 Mar 2017 | 105.8° | 1327 km | no |
| JUGNU * | 37839 | 29 Mar 2017 | 20.0° | 851 km | yes |
| KAZEOSAT_2 | 40010 | 29 Mar 2017 | 97.9° | 623 km | yes |
| NEOSSAT | 39089 | 29 Mar 2017 | 98.6° | 777 km | yes |
| QSAT-EOS | 40301 | 29 Mar 2017 | 97.4° | 497 km | no |
| RISAT-1 * | 38248 | 29 Mar 2017 | 97.6° | 539 km | no |
| RISAT-2 * | 34807 | 29 Mar 2017 | 41.2° | 469 km | no: re-entered 30 Oct 2022 |
| SARAL * | 39086 | 29 Mar 2017 | 98.5° | 785 km | yes |
| SCATSAT_1 * | 41790 | 29 Mar 2017 | 98.1° | 725 km | yes |
| SRMSAT * | 37841 | 28 Mar 2017 | 20.0° | 857 km | yes |
| SWAYAM * | 41607 | 29 Mar 2017 | 97.5° | 505 km | no |
| TELEOS_1 | 41169 | 29 Mar 2017 | 15.0° | 539 km | no |
| THEOS | 33396 | 29 Mar 2017 | 98.7° | 825 km | yes |
| TIGRISAT | 40043 | 29 Mar 2017 | 97.9° | 649 km | yes |
| WEOS | 27599 | 29 Mar 2017 | 98.4° | 794 km | no |
| YOUTHSAT * | 37388 | 29 Mar 2017 | 98.6° | 811 km | yes |

The mean altitudes come from the 2017 element sets. The element sets themselves are
also in [`data/tle/scenario_2017-03-29.tle`](../data/tle/scenario_2017-03-29.tle).

## Before you reuse it

**The element sets are from March 2017.** STK chose the newest set in its local
database for each satellite, and for every one of them that set was nine years
older than the scenario. The positions in July 2026 are therefore not where these
satellites were. [Looking back](../README.md#looking-back-a-2017-sky-in-2026)
measures how far off they are. RISAT-2, which re-entered in 2022, is propagated
some 430 km underground. To reuse the scenario for real dates, load current
element sets, from CelesTrak or Space-Track, into each satellite first.

## The exported report

`advcat_close_approaches_2026-07-10.csv` is AdvCAT's *Close Approach by Min Sep*
report, saved as CSV and handed to the software side. Its original file name was
`Khushii_stuff101.csv`. It holds a header describing the settings, a list of the
objects screened, and then one row per event:

| Column | Meaning (recovered in [theory 02](../theory/02-what-advcat-reports.md)) |
|---|---|
| Object Name | the secondary: `tle NNNNN` (catalogue number) or a scenario satellite file |
| Time In, Time Out (UTCG) | when the gap between the two ellipsoids falls below, then rises back above, 10 km |
| Min Separation (km) | the smallest gap between the ellipsoid surfaces, or `Intersect` if they overlapped |
| Min Range (km) | the smallest centre-to-centre distance |

Two quirks. The report has no column naming the primary, so each event's primary
can only be inferred from the order of the rows ([`leo/stk.py`](../leo/stk.py),
`split_by_primary`). And most objects appear in two to four of the screened
catalogues, so 4,312 rows hold 1,955 distinct events.
