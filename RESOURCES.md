# Resources

*[← Conjunction screening for a LEO digital twin](README.md)*

## Orbit propagation

- F. R. Hoots and R. L. Roehrich, *Models for Propagation of NORAD Element Sets*,
  Spacetrack Report No. 3, 1980. The original SGP4.
- D. A. Vallado, P. Crawford, R. Hujsak and T. S. Kelso, "Revisiting Spacetrack
  Report #3," AIAA 2006-6753, AIAA/AAS Astrodynamics Specialist Conference, 2006.
  The revised SGP4 that STK, `sgp4` and Skyfield all implement.
- D. A. Vallado, *Fundamentals of Astrodynamics and Applications*, 4th ed.,
  Microcosm Press, 2013. Orbital elements, frames, J2 perturbations.

## Conjunction screening

- J. W. Perram and M. S. Wertheim, "Statistical mechanics of hard ellipsoids. I.
  Overlap algorithm and the contact function," *Journal of Computational Physics*
  58, 409–416, 1985. The contact function used to test ellipsoid overlap.
- AGI, STK Help: *Advanced CAT (AdvCAT)*. The tool, its filters and its reports.
  The help does not spell out the report's column definitions;
  [theory 02](theory/02-what-advcat-reports.md) recovers them.
- NASA, *Spacecraft Conjunction Assessment and Collision Avoidance Best Practices
  Handbook*. How operators actually decide: collision probability from covariance.

## The 2009 collision and its debris

- T. S. Kelso, "Analysis of the Iridium 33–Cosmos 2251 Collision," AAS 09-368, and
  AMOS Conference, 2009. [celestrak.org/publications/AAS/09-368](https://celestrak.org/publications/AAS/09-368/)
- "Analysis and Consequences of the Iridium 33–Cosmos 2251 Collision," NASA
  technical report 20100008433. [ntrs.nasa.gov/citations/20100008433](https://ntrs.nasa.gov/citations/20100008433)
- L. Anselmo and C. Pardini, "Analysis of the consequences in low Earth orbit of
  the collision between Cosmos 2251 and Iridium 33," ISTI-CNR, 2009.
- D. J. Kessler and B. G. Cour-Palais, "Collision frequency of artificial
  satellites: the creation of a debris belt," *Journal of Geophysical Research*
  83(A6), 2637–2646, 1978.

## The satellites

- ISRO, "Atmospheric Re-entry of RISAT-2," November 2022: re-entry on 30 October
  2022, 00:06 UTC, in the Indian Ocean near Jakarta.
  [isro.gov.in](https://www.isro.gov.in/Atmospheric_Re_entryRISAT2.html)

## Data

- **CelesTrak** (T. S. Kelso): element sets for the active-satellite list and the
  two debris clouds, downloaded for the project in April–May 2026.
  [celestrak.org](https://celestrak.org)
- **STK's local database** (`stkAllTLE.tce` and the other catalogue files) supplied
  the July scenario's element sets, all from March 2017.

## Software

**During the project**

- **STK 11.2** (AGI, now Ansys): scenarios, SGP4 and HPOP propagation, access and
  AER reports, AdvCAT
- **Rust, wgpu and egui**: the digital twin, built by Khushi Choudki and Keerthi M

**For this repository**

- **Python 3**, **NumPy**, **SciPy**, **Matplotlib**
- **sgp4** (Brandon Rhodes): Vallado's SGP4 in C++ with Python bindings.
  [github.com/brandon-rhodes/python-sgp4](https://github.com/brandon-rhodes/python-sgp4)
- **Skyfield** (Brandon Rhodes): the rotation from TEME to GCRS.
  [rhodesmill.org/skyfield](https://rhodesmill.org/skyfield/)
