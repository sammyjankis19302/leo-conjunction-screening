# The project report as submitted

*[← Conjunction screening for a LEO digital twin](../README.md)*

[`IDP_report_as_submitted.pdf`](IDP_report_as_submitted.pdf) is our team's 60-page
interdisciplinary project report, *Digital Twin for LEO Satellite Constellations:
Energy-Aware ICT Modeling and GNN-Based Kessler Syndrome Mitigation* (course XX367P,
RV College of Engineering, 2025–26), by Khushi Choudki, Keerthi M, Tejas L and
Akula Uday Kiran, guided by Dr. Rachana S Akki. It is kept unchanged as the record of
what was handed in.

**Which part is which.** Chapter 3 (pages 12–20) is the aerospace work this repository
is about: the STK scenarios, propagation, access and AER reports, and the AdvCAT
screening of SWISSCUBE on 7–8 May 2026. Chapters 4–8 cover the software side's digital
twin (the N-body engine, the GNN risk predictor, reinforcement learning and the
interface), which lives in
[their repository](https://github.com/KhushiChoudki/Energy-Aware-Digital-Twin-of-LEO-Satellite-Constellations-Using-Scalable-N-Body-Simulation-).

**What it came before.** The report was written in June 2026, before the final
33-satellite screening of 10 July 2026 that this repository is built on. That
screening, and the finding that it ran on element sets from 2017, are in the
[README](../README.md#looking-back-a-2017-sky-in-2026).

## Corrections

| Where | What the report says | What is right |
|---|---|---|
| Section 1.2.4 | the 10 km threshold is a bounding sphere around the satellite | it applies to the gap between the two safety-ellipsoid surfaces, so events with centres up to about 50 km apart are reported ([mistake 7](../MISTAKES.md#7-treating-the-10-km-threshold-as-a-radius)) |
| Sections 3.8.3–3.8.4, Figs. 3.5–3.6 | red ellipsoids and events marked *Intersect* are a high collision risk | *Intersect* means two 20 × 10 × 5 km ellipsoids overlapped, which can happen with the centres 40 km apart: a geometric flag, not a risk ([mistake 6](../MISTAKES.md#6-reading-intersect-as-high-risk)) |
| page headers | another project's title, "AI-Driven Fault Detection and Self-Healing Framework for Adaptive Circuit Monitoring" | a leftover from the report template |
| cover, certificate and declaration | university numbers 1RV24AS004 and 1RV24AS061 | 1RV23AS004 and 1RV23AS061 |
