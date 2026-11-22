"""The two debris clouds of the 2009 Iridium 33 / Cosmos 2251 collision, as they
were in April 2026.

For every catalogued fragment: perigee and apogee altitude and period (the
Gabbard diagram), the node, and the secular J2 regression of the node,

    dOmega/dt = -1.5 n J2 (Re/p)^2 cos i.

The regression rate depends on cos i, and the two parents flew at 74.0 deg
(Cosmos) and 86.4 deg (Iridium). How fast each cloud's planes fan out around the
Earth depends on the spread of that rate across its fragments.

Writes data/results/debris_clouds.csv.
"""
import csv
import os
import sys

import numpy as np

ROOT = os.path.join(os.path.dirname(os.path.abspath(__file__)), "..")
sys.path.insert(0, ROOT)
from leo.tle import read_tles                         # noqa: E402

T = os.path.join(ROOT, "data", "tle")
CLOUDS = [("Cosmos 2251", "cosmos-2251_debris_2026-04.tle", 22675),
          ("Iridium 33", "iridium-33_debris_2026-04.tle", 24946)]


def circular_std_deg(angles_deg):
    R = np.abs(np.mean(np.exp(1j * np.radians(angles_deg))))
    return np.degrees(np.sqrt(-2.0 * np.log(R)))


def load():
    out = {}
    for name, f, parent in CLOUDS:
        out[name] = (read_tles(os.path.join(T, f)), parent)
    return out


def main():
    rows = []
    for name, (tles, parent) in load().items():
        inc = np.array([t.inc_deg for t in tles])
        rate = np.degrees(np.array([t.nodal_rate() for t in tles])) * 86400.0        # deg/day
        raan = np.array([t.raan_deg for t in tles])
        per = np.array([t.perigee_alt for t in tles])
        apo = np.array([t.apogee_alt for t in tles])
        q1, q3 = np.percentile(rate, [25, 75])
        wrap = 360.0 / (q3 - q1) / 365.25
        print(f"{name}: {len(tles)} catalogued objects, inclination {np.median(inc):.2f} deg "
              f"(range {inc.min():.2f}-{inc.max():.2f})")
        print(f"   perigee {per.min():.0f}-{per.max():.0f} km, apogee {apo.min():.0f}-{apo.max():.0f} km")
        print(f"   node regression {np.median(rate):.3f} deg/day (interquartile spread {q3 - q1:.4f} deg/day): "
              f"half the planes drift a full 360 deg apart every {wrap:.0f} years")
        print(f"   node spread in Apr 2026: circular std {circular_std_deg(raan):.0f} deg")
        for t, r in zip(tles, rate):
            rows.append(dict(cloud=name, norad=t.norad, name=t.name, intl=t.intl, epoch=t.epoch.strftime("%Y-%m-%d"),
                             a_km=t.a, e=t.e, inc_deg=t.inc_deg, raan_deg=t.raan_deg, perigee_km=t.perigee_alt,
                             apogee_km=t.apogee_alt, period_min=t.period_min, node_rate_deg_day=r,
                             parent=t.norad == parent))
    with open(os.path.join(ROOT, "data", "results", "debris_clouds.csv"), "w", newline="") as f:
        w = csv.DictWriter(f, fieldnames=list(rows[0].keys()))
        w.writeheader()
        for r in rows:
            w.writerow({k: (f"{v:.6f}" if isinstance(v, float) else v) for k, v in r.items()})
    c = [r["node_rate_deg_day"] for r in rows if r["cloud"] == "Cosmos 2251"]
    i = [r["node_rate_deg_day"] for r in rows if r["cloud"] == "Iridium 33"]
    print(f"\nThe Cosmos planes regress {np.median(c) / np.median(i):.1f} times faster than the Iridium planes "
          f"(cos 74.0 / cos 86.4 = {np.cos(np.radians(74.0)) / np.cos(np.radians(86.4)):.1f}).")


if __name__ == "__main__":
    main()
