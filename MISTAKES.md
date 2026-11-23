# Mistakes, and what each one cost

*[← Conjunction screening for a LEO digital twin](README.md)*

These are mine, from the July 2026 screening, apart from the last two, which are
in our [team report](report/README.md) and which I share. I found most of them while building this
repository, by checking STK's output with open-source code. Each one ends with the
check that would have caught it.

---

### 1. A 2026 screening on 2017 element sets

**What happened.** STK propagated each satellite from the newest element set in its
local database. Every one of those sets was from 28–29 March 2017. I set the
scenario to 10 July 2026 and never looked at the element sets' epochs.

**What it cost.** Every conjunction in the July run is fictional. The positions are
4,000–14,000 km from where 2026 element sets put the satellites, which is as far
off as positions on an orbit can be. The setup was right; the sky was wrong.

**The check.** Before any propagation, print the epoch of every element set and
compare it with the analysis date. An element set a few days old drifts by a
fraction of a kilometre; one nine years old says nothing about where the satellite
is.

### 2. The filter that would have caught it was switched off

**What happened.** AdvCAT has an out-of-date filter that drops element sets older
than a set age. Our file had it set to 30 days, and switched off.

**What it cost.** Nothing in the run warned me.

**The check.** Leave the out-of-date filter on unless there is a reason not to. An
empty or near-empty report is a warning, not an inconvenience.

### 3. A satellite flying underground

**What happened.** RISAT-2 re-entered on 30 October 2022. From its 2017 element set,
SGP4 returns error code 6 (decayed) at every instant of the scenario. STK still
stored an ephemeris, about 430 km below the Earth's surface, and AdvCAT screened
it.

**What it cost.** A satellite that did not exist was screened for collisions.

**The check.** Look at the propagator's error codes, or at least check that every
altitude is positive.

### 4. Four catalogues that overlap

**What happened.** I screened against all four of STK's catalogue files at once.
Most objects are in two to four of them.

**What it cost.** The report has 4,312 rows for 1,955 events, most of them listed
two to four times. Any count taken straight from the report is inflated.

**The check.** Screen against one catalogue, or remove duplicates by catalogue
number before reporting.

### 5. A report without its primary

**What happened.** The CSV I exported for the software side lists each event's
secondary, but not the primary it belongs to.

**What it cost.** Which satellite an event belongs to can only be inferred from the
order of the rows. Inferring it here leaves one primary of the 33 with no block.

**The check.** Choose a report style that names both objects, or export one report
per primary.

### 6. Reading "Intersect" as high risk

**What happened.** Our project report said that because every listed event was
flagged `Intersect`, every event was a high-confidence one.

**What it cost.** A misreading. `Intersect` means two 20 × 10 × 5 km ellipsoids
overlapped, which can happen with the centres 40 km apart. It is a coarse
geometric flag, not a risk. Collision risk needs the orbit uncertainty of both
objects, and TLEs carry none.

**The check.** Know the definition of every number before interpreting it.
[Theory 02](theory/02-what-advcat-reports.md) has the definitions, recovered by
reproducing AdvCAT's output.

### 7. Treating the 10 km threshold as a radius

**What happened.** Our report described the 10 km threshold as a bounding sphere
around the satellite.

**What it cost.** AdvCAT applies it to the gap between the two ellipsoid surfaces.
With ellipsoids reaching 20 km along the track, events with centres up to about
50 km apart are reported. The July export's largest Min Range is 46.9 km.

**The check.** The same as for 6: a number whose definition you cannot state is not
yet a result.
