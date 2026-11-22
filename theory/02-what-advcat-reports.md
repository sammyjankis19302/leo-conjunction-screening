# 02 — What AdvCAT reports, exactly

*[← 01: Element sets and SGP4](01-tles-and-sgp4.md)  ·  next: [Debris clouds →](03-debris-clouds.md)*

AdvCAT (STK's Advanced Conjunction Analysis Tools) gives each object a safety
ellipsoid. It reports an event whenever two ellipsoids come close. Each event in
its *Close Approach by Min Sep* report has four numbers: Time In, Time Out,
Min Separation and Min Range. The report does not define them, and in our project
report we read them loosely. This note sets out the geometry, the definitions
recovered by reproducing AdvCAT's output, and what the numbers do and do not mean.

## 1. Safety ellipsoids

Each object's ellipsoid is aligned with its own velocity:

```math
\hat{T} = \frac{\mathbf{v}}{|\mathbf{v}|}, \qquad
\hat{C} = \frac{\mathbf{r}\times\mathbf{v}}{|\mathbf{r}\times\mathbf{v}|}, \qquad
\hat{N} = \hat{T}\times\hat{C}
```

*T* points along the track, *C* along the orbit normal (cross-track), and *N* lies
in the orbit plane, nearly radial for a circular orbit. With semi-axes
*a*<sub>T</sub> = 20 km, *a*<sub>C</sub> = 10 km, *a*<sub>N</sub> = 5 km and the
rotation *A* whose rows are those unit vectors, the ellipsoid around centre **c** is

```math
\mathcal{E} = \{\mathbf{x} : (\mathbf{x}-\mathbf{c})^\mathsf{T} M^{-1} (\mathbf{x}-\mathbf{c}) \le 1\}, \qquad
M = A^\mathsf{T}\,\mathrm{diag}(a_T^2, a_C^2, a_N^2)\,A
```

Long along the track, short radially: the shape follows where orbit predictions are
uncertain, which is mostly along the track.

## 2. Do two ellipsoids overlap?

For ellipsoids with shape matrices *M*<sub>1</sub>, *M*<sub>2</sub> and centres
**d** apart, Perram and Wertheim's contact function is

```math
F(\lambda) = \lambda(1-\lambda)\, \mathbf{d}^\mathsf{T} \left[(1-\lambda)M_1 + \lambda M_2\right]^{-1} \mathbf{d},
\qquad 0 < \lambda < 1
```

It has a single maximum. The ellipsoids overlap if max *F* < 1, touch if it equals 1,
and are apart if it exceeds 1. For two unit spheres 3 apart, max *F* = 9/4, the
square of 3/(1+1).

## 3. How far apart are they?

When the ellipsoids are apart, the distance between their surfaces is the largest
gap along any direction **u** (the separating-axis form of convex distance):

```math
g = \max_{|\mathbf{u}|=1} \left[\, \mathbf{u}\cdot\mathbf{d} - \sqrt{\mathbf{u}^\mathsf{T} M_1 \mathbf{u}} - \sqrt{\mathbf{u}^\mathsf{T} M_2 \mathbf{u}} \,\right]
```

Each square root is how far an ellipsoid reaches in direction **u**.
[`leo/ellipsoid.py`](../leo/ellipsoid.py) implements both functions, and
[`tests/test_leo.py`](../tests/test_leo.py) checks them against closed forms.

## 4. The definitions, recovered

Three of AdvCAT's events involve two scenario satellites, CARTOSAT-2 and ASTROSAT,
so every input is known exactly. Candidate definitions were tested until all
numbers matched:

| AdvCAT column | Definition that reproduces it |
|---|---|
| **Min Range** | smallest centre-to-centre distance, \|**d**\| |
| **Min Separation** | smallest surface gap *g* over the encounter, or `Intersect` if max *F* < 1 at some instant |
| **Time In / Time Out** | the two instants at which *g* = 10 km, the close-approach threshold |

| Event | Min Range | Min Separation | Time In, Time Out |
|---|---|---|---|
| 06:45 | 14.668758 km, same to 1 mm | 1.068592 vs 1.068591 km | within 0.4 ms |
| 07:33 | 3.551351 km, same | Intersect, same | within 0.3 ms |
| 09:11 | 30.368512 km, same | 1.200267 km, same | within 0.1 ms |

The ellipsoid colours in STK's 3D view fit the same picture. In the YOUTHSAT
frames in the README, the ellipsoids are red at closest approach, yellow until
Time Out and green after it.

## 5. What the numbers mean, and what they do not

**The threshold is a gap between ellipsoids, not a radius.** A 10 km threshold
between ellipsoids that reach 20 km along the track means centres up to about
50 km apart can count. The largest Min Range in the July export is 46.9 km.

**"Intersect" is not a collision warning.** Two 20 × 10 × 5 km ellipsoids can
overlap with their centres up to 40 km apart. Our project report took
`Intersect` as confirmation of high-risk events. It is only a coarse geometric
flag; 683 of the 1,955 events in July had it. Operators decide on collision
*probability*, computed from the orbit uncertainty (covariance) of both objects
and their physical size. TLEs carry no covariance, which is why AdvCAT falls back
on fixed ellipsoids. AdvCAT can compute a probability when covariance is supplied;
the `.ca` file holds those settings, unused here.

**Encounters last seconds.** At 11.9 km/s the centres close 44 km in 3.7 s. The
07:33 event's Time In and Time Out are 7.33 s apart: the stretch during which
ASTROSAT's centre is within about 44 km of CARTOSAT-2's.

## 6. The screening procedure

[`leo/screening.py`](../leo/screening.py) follows the same steps as AdvCAT:

1. **Apogee/perigee filter.** A secondary whose altitude band, padded by 30 km,
   misses the primary's band cannot come close.
2. **Coarse pass.** Propagate everything on a 60 s grid. Wherever the range rate
   **d**·(**v**<sub>2</sub> − **v**<sub>1</sub>) turns from negative to positive, a
   local minimum of range lies inside that step.
3. **Refinement.** Minimise the range on the full SGP4 states. If the minimum
   exceeds the threshold plus both ellipsoids' reach (10 + 20 + 20 km), stop.
4. **Ellipsoids.** Minimise *F* and *g* over the encounter, then solve *g* = 10 km on
   each side for Time In and Time Out.

AdvCAT also has an *out-of-date* filter, which drops element sets older than a set
age (30 days in our file). It was switched off. With it on, the 2017 element sets
in the catalogues would have been dropped, and the near-empty report would have
exposed the problem.
