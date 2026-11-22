# 03 — How one collision becomes a shell of debris

*[← 02: What AdvCAT reports](02-what-advcat-reports.md)  ·  [README](../README.md)*

On 10 February 2009 at 16:56 UTC, the operational Iridium 33 and the derelict
Cosmos 2251 collided at about 789 km above northern Siberia, at 11.7 km/s (Kelso,
2009). It was the first accidental collision between two intact satellites. The
digital twin models this event, and the project set out to study cascades of it.
This note uses the team's April 2026 element sets for every catalogued fragment
to show what 17 years have done to the two clouds.

## 1. The two clouds in April 2026

| | Cosmos 2251 | Iridium 33 |
|---|---|---|
| catalogued objects in the team's files | 585 | 108 |
| inclination | 74.02° (73.53–74.27) | 86.34° (85.96–86.47) |
| perigee altitude | 363–774 km | 500–768 km |
| apogee altitude | 394–1,609 km | 518–1,046 km |
| node regression (J2), median | −1.882°/day | −0.438°/day |
| spread of that rate, middle half | 0.112°/day | 0.025°/day |
| time for the middle half to fan out by 360° | 9 years | 40 years |
| spread of node directions today (circular std) | 160°: all around | 53°: a band |

## 2. The Gabbard diagram

A Gabbard diagram plots each fragment twice, at its apogee and at its perigee,
against its orbital period ([figure](../figures/debris-clouds.png), left). A
fragment kicked forward in the collision gains energy: its period grows, its apogee
rises, and its perigee stays near the collision point. A fragment kicked backward
loses energy: its perigee drops while its apogee stays near 789 km. The two
branches form the X that marks a breakup.

Seventeen years of drag have since pulled down the low perigees and cleared the
shortest-period fragments, so today's diagram is the survivors' version.

## 3. Why the planes fan out

The Earth's equatorial bulge (J2) makes every orbit plane regress about the
polar axis:

```math
\dot\Omega = -\frac{3}{2}\, n\, J_2 \left(\frac{R_\oplus}{p}\right)^2 \cos i, \qquad p = a(1-e^2)
```

Two consequences follow, both visible in the data.

**The clouds regress at different rates.** The rate is proportional to cos *i*:
cos 74.0° / cos 86.4° = 4.4. The fragments' median rates differ by a factor of 4.3.

**Each cloud spreads.** The rate also depends on the orbit size, roughly as
*a*<sup>−7/2</sup>, so fragments thrown to different heights regress at slightly
different rates. For the Cosmos cloud the middle half of the rates spans
0.112°/day, so in 9 years those planes drift a full turn apart. For Iridium the
spread is 0.025°/day, and a full turn takes 40 years.

After 17 years the Cosmos planes point evenly in every direction
([figure](../figures/debris-clouds.png), right): the cloud is a shell around the
Earth. The Iridium planes, more polar and slower to turn, still cluster: a band.
Drag has changed every fragment's orbit size over those years, so today's rates do
not let each node be traced back individually. The statistics, though, are plain
J2.

## 4. Why it matters

A debris cloud starts as a spray along one orbit. Within a decade it is a shell
that every satellite in that altitude band crosses twice per orbit. That is the
mechanism behind the Kessler cascade the project was built to study (Kessler and
Cour-Palais, 1978), and behind the "debris bands" in the digital twin's
fragmentation model. In the 2026 screening, Cosmos 2251 fragments were the
secondary in 89 of the 743 events, second only to Starlink satellites (230).
