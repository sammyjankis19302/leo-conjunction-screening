"""Readers for the STK 11 files in stk/: satellites (.sa), the AdvCAT object
(.ca) and AdvCAT's close-approach report exported as CSV."""
import csv
import os
import re
from dataclasses import dataclass, field
from datetime import timedelta

import numpy as np

from .timeutil import stk_time
from .tle import TLE


@dataclass
class StkSatellite:
    name: str
    ssc: int
    tle: TLE
    epoch: object                 # scenario epoch of the stored ephemeris
    ephemeris: np.ndarray         # columns: t [s from epoch], x y z [m], vx vy vz [m/s]
    frame: str

    @property
    def times(self):
        return [self.epoch + timedelta(seconds=float(s)) for s in self.ephemeris[:, 0]]


def read_satellite(path):
    """Name, catalogue number, the TLE STK actually used, and the stored ephemeris."""
    txt = open(path, encoding="latin-1").read()
    name = re.search(r"^Name\s+(\S+)", txt, re.M).group(1)
    ssc = int(re.search(r"SSCNumber\s+(\d+)", txt).group(1))
    m = re.search(r"\n\s*TLE\s*\n\s*(1 [^\n]{67})[^\n]*\n\s*(2 [^\n]{67})", txt)
    tle = TLE(name, m.group(1), m.group(2))
    block = txt.split("BEGIN Ephemeris", 1)[1].split("END Ephemeris", 1)[0]
    epoch = stk_time(re.search(r"ScenarioEpoch\s+(.+)", block).group(1))
    frame = re.search(r"CoordinateSystem\s+(\S+)", block).group(1)
    rows = block.split("EphemerisTimePosVel", 1)[1]
    eph = np.array([[float(x) for x in l.split()] for l in rows.strip().splitlines() if l.strip()])
    return StkSatellite(name, ssc, tle, epoch, eph, frame)


def read_scenario(folder):
    """All satellites in a scenario folder, sorted by name."""
    files = sorted(f for f in os.listdir(folder) if f.endswith(".sa"))
    return [read_satellite(os.path.join(folder, f)) for f in files]


@dataclass
class AdvCat:
    start: object
    stop: object
    threshold_km: float
    primary_axes_km: tuple        # tangential, cross-track, normal
    primaries: list = field(default_factory=list)
    secondary_catalogues: list = field(default_factory=list)
    secondary_files: list = field(default_factory=list)
    apogee_perigee_pad_km: float = 0.0
    time_pad_km: float = 0.0


def read_advcat(path):
    txt = open(path, encoding="latin-1").read()
    num = lambda key: float(re.search(rf"{key}\s+([-+0-9.eE]+)", txt).group(1))
    prim = txt.split("BEGIN Primaries", 1)[1].split("END Primaries", 1)[0]
    sec = txt.split("BEGIN Secondaries", 1)[1].split("END Secondaries", 1)[0]
    ell = re.search(r"BEGIN DefaultEllipsoid(.*?)END DefaultEllipsoid", prim, re.S).group(1)
    axes = tuple(float(re.search(rf"{k}\s+([-+0-9.eE]+)", ell).group(1)) / 1e3
                 for k in ("Tangential", "CrossTrack", "Normal"))
    return AdvCat(
        start=stk_time(re.search(r"StartTime\s+(.+)", txt).group(1)),
        stop=stk_time(re.search(r"StopTime\s+(.+)", txt).group(1)),
        threshold_km=num("Threshold") / 1e3,
        primary_axes_km=axes,
        primaries=re.findall(r"Name\s+Satellite/(\S+)", prim),
        secondary_catalogues=re.findall(r"BEGIN TleFile\s+Name\s+(\S+)", sec),
        secondary_files=re.findall(r"BEGIN Satellite\s+Name\s+(\S+)", sec),
        apogee_perigee_pad_km=num("ApogeePerigeePad") / 1e3,
        time_pad_km=num("TimeDistancePad") / 1e3,
    )


@dataclass
class CloseApproach:
    secondary: str
    time_in: object
    time_out: object
    min_separation_km: float      # gap between the ellipsoids; nan when they intersect
    intersect: bool
    min_range_km: float


def read_close_approaches(path):
    """Events from AdvCAT's 'Close Approach by Min Sep' report saved as CSV, in file order."""
    rows = list(csv.reader(open(path, encoding="latin-1")))
    start = next(i for i, r in enumerate(rows) if r and r[0] == "Object Name") + 1
    out = []
    for r in rows[start:]:
        if len(r) < 5:
            continue
        inter = r[3].strip() == "Intersect"
        out.append(CloseApproach(r[0].strip(), stk_time(r[1]), stk_time(r[2]),
                                 float("nan") if inter else float(r[3]), inter, float(r[4])))
    return out


def split_by_primary(events):
    """The export has no primary column. AdvCAT lists each primary's events in
    increasing Min Separation (intersections first), so a drop in that value
    marks the start of the next primary's block."""
    blocks, cur, prev = [], [], None
    for ev in events:
        key = -1.0 if ev.intersect else ev.min_separation_km
        if prev is not None and key < prev - 1e-9:
            blocks.append(cur)
            cur = []
        cur.append(ev)
        prev = key
    blocks.append(cur)
    return blocks
