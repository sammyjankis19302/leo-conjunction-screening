# 01 — Element sets and SGP4

*[← README](../README.md)  ·  next: [What AdvCAT reports →](02-what-advcat-reports.md)*

Every position in this project starts from a two-line element set (TLE) and the
SGP4 model that interprets it. This note covers what a TLE contains, why the
open-source SGP4 here matches STK to a millimetre, and why an old TLE is
worthless no matter how good the propagator is.

## 1. What a TLE is

YOUTHSAT's element set, exactly as STK used it:

```
1 37388U 11015B   17088.84885257 -.00000196  00000-0 -66383-4 0  9996
2 37388  98.5689 163.9434 0016373 120.3219 239.9559 14.24162678308884
```

| Field | Value | Meaning |
|---|---|---|
| `37388` | catalogue (NORAD) number | the object's identity |
| `11015B` | international designator | launched in 2011, launch 15, piece B |
| `17088.84885257` | epoch | 2017, day 88.849: 29 March 2017, 20:22 UTC |
| `-66383-4` | B\* | drag term, −6.6 × 10⁻⁵ per Earth radius |
| `98.5689` | inclination [deg] | sun-synchronous, slightly retrograde |
| `163.9434` | right ascension of the ascending node [deg] | where the orbit crosses the equator going north |
| `0016373` | eccentricity | 0.0016373: nearly circular |
| `120.3219`, `239.9559` | argument of perigee, mean anomaly [deg] | where the perigee is, and where the satellite is |
| `14.24162678` | mean motion [rev/day] | sets the size of the orbit |

These are **mean** elements: a fit to tracking data, made for use with SGP4 and
only meaningful through it. From the mean motion *n* (WGS-72, μ = 398 600.8 km³/s²):

```math
a = \left(\frac{\mu}{n^2}\right)^{1/3} = 7189.6\ \mathrm{km}, \qquad
h_p = a(1-e) - R_\oplus = 800\ \mathrm{km}, \qquad h_a = a(1+e) - R_\oplus = 823\ \mathrm{km}
```

which is YOUTHSAT's 811 km orbit, with a period of 101.1 minutes.

## 2. SGP4, TEME, and why it matches STK

SGP4 (Hoots and Roehrich, 1980, revised by Vallado et al., 2006) is an analytical
theory. It applies the secular and periodic effects of the Earth's oblateness (J2,
J3, J4) and an atmospheric drag model scaled by B\*. Its output is position and
velocity in **TEME**, a quasi-inertial frame tied to the true equator and a "mean"
equinox.

STK runs this revised SGP4 (its satellite files record the version as
`VersionIndicator 20080918`) and stores ephemeris in ICRF. The Python `sgp4` package is Vallado's code too, and
Skyfield applies the rotation from TEME to ICRF/GCRS. With the element set read
from each `.sa` file, [`analysis/validate_against_stk.py`](../analysis/validate_against_stk.py)
compares the two over the whole scenario:

| | |
|---|---|
| satellites × points | 33 × 1,441 |
| largest position difference | 1.29 mm (QSAT-EOS) |
| largest velocity difference | 1.5 µm/s |

A millimetre is far below anything physical; the difference is round-off and
frame arithmetic. The agreement matters because it lets anyone check an STK result
without an STK licence.

Distances between satellites, relative speeds and safety ellipsoids do not depend
on the frame, so the screener in [`leo/screening.py`](../leo/screening.py) works
directly in TEME.

## 3. What an element set's age costs

A TLE is a snapshot. Fitting errors and unmodelled forces (above all drag, which
depends on solar activity no TLE can predict) make the prediction drift, mostly
along the track. The team's files contain two objects with element sets a few days
apart, and the newer set can stand in for the truth:

| Object | Age of the older set | Position difference at the newer epoch |
|---|---|---|
| IRIDIUM 33 | 0.56 days | 0.10 km |
| COSMOS 2251 | 4.9 days | 0.42 km |

Both orbit near 780 km, where drag is weak; lower satellites drift faster. Against
a 10 km threshold, a few days' drift is acceptable.

The July 2026 scenario used element sets from March 2017, 9.1 years old. Against
May 2026 element sets for the 18 satellites that have them
([`analysis/tle_age.py`](../analysis/tle_age.py)):

| | |
|---|---|
| distance between the two predicted positions | 4,056–13,925 km, median 11,051 km |
| difference in position around the orbit | 33°–170°, median 98° |
| angle between the two orbit planes | 3.0°–97.4°, median 24° |

The largest possible difference between two points on orbits of radius ~7,100 km is
~14,200 km, the orbit's diameter. After nine years the errors fill that whole range.
The satellite could be anywhere on its orbit, and the orbit itself has turned.
A better propagator cannot help, because the error is in the starting point.
Converting these satellites to HPOP would only have propagated the 2017 state more
carefully.

## 4. When SGP4 says a satellite has decayed

SGP4 returns an error code with every state. Code 6 means the satellite has
decayed: the computed radius is less than one Earth radius. For RISAT-2's 2017 set,
SGP4 first returns code 6 in June 2024, and returns it at every instant of the July
2026 scenario. The real satellite re-entered on 30 October 2022 (ISRO). STK stored
an ephemeris for it anyway, at radii of 5,942–5,953 km, some 430 km underground.
[`tests/test_leo.py`](../tests/test_leo.py) checks both facts.

Checking the error code, or simply that the altitude is positive, would have caught
it.
