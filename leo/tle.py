"""Reading TLE files and the orbit quantities that follow from a TLE."""
from dataclasses import dataclass, field

import numpy as np
from sgp4.api import WGS72, Satrec

from .timeutil import tle_epoch

# WGS-72 constants, the set SGP4 is defined with
MU = 398600.8          # km^3/s^2
RE = 6378.135          # km
J2 = 0.001082616


@dataclass
class TLE:
    name: str
    line1: str
    line2: str
    sat: Satrec = field(init=False, repr=False)

    def __post_init__(self):
        self.line1 = self.line1.rstrip()
        self.line2 = self.line2.rstrip()
        self.sat = Satrec.twoline2rv(self.line1, self.line2, WGS72)

    @property
    def norad(self):
        return int(self.line1[2:7])

    @property
    def intl(self):
        """International designator, e.g. '93036A' (launch year, number, piece)."""
        return self.line1[9:17].strip()

    @property
    def epoch(self):
        return tle_epoch(self.line1)

    @property
    def mean_motion(self):
        """Mean motion in rad/s."""
        return self.sat.no_kozai / 60.0

    @property
    def a(self):
        """Semi-major axis in km (from the Kozai mean motion; good to a few km)."""
        return (MU / self.mean_motion ** 2) ** (1.0 / 3.0)

    @property
    def e(self):
        return self.sat.ecco

    @property
    def inc_deg(self):
        return np.degrees(self.sat.inclo)

    @property
    def raan_deg(self):
        return np.degrees(self.sat.nodeo) % 360.0

    @property
    def perigee_alt(self):
        return self.a * (1 - self.e) - RE

    @property
    def apogee_alt(self):
        return self.a * (1 + self.e) - RE

    @property
    def period_min(self):
        return 2 * np.pi / self.mean_motion / 60.0

    def nodal_rate(self):
        """Secular J2 regression of the node, rad/s: -1.5 n J2 (Re/p)^2 cos i."""
        p = self.a * (1 - self.e ** 2)
        return -1.5 * self.mean_motion * J2 * (RE / p) ** 2 * np.cos(self.sat.inclo)


def read_tles(path, encoding="utf-8-sig"):
    """Read a file of two- or three-line element sets. A name line is optional."""
    lines = [l.rstrip("\r\n") for l in open(path, encoding=encoding, errors="replace")]
    lines = [l for l in lines if l.strip() and not l.startswith("#")]
    out = []
    i = 0
    while i < len(lines) - 1:
        if lines[i].startswith("1 ") and lines[i + 1].startswith("2 "):
            name = lines[i - 1].strip() if i > 0 and not lines[i - 1].startswith(("1 ", "2 ")) else lines[i][2:7]
            out.append(TLE(name, lines[i], lines[i + 1]))
            i += 2
        else:
            i += 1
    return out


def write_tles(path, tles, header=None):
    with open(path, "w") as f:
        if header:
            for h in header.splitlines():
                f.write(f"# {h}\n")
        for t in tles:
            f.write(f"{t.name}\n{t.line1}\n{t.line2}\n")


def unique_by_norad(tles):
    """Keep the newest element set for each catalogue number."""
    best = {}
    for t in tles:
        if t.norad not in best or t.epoch > best[t.norad].epoch:
            best[t.norad] = t
    return list(best.values())
