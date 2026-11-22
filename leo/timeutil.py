"""Time handling. Everything is UTC; SGP4 takes a two-part Julian date."""
from datetime import datetime, timedelta, timezone

import numpy as np
from sgp4.api import jday

UTC = timezone.utc


def stk_time(text):
    """Parse an STK UTCG string such as '10 Jul 2026 06:30:00.000'."""
    text = text.strip().strip('"')
    if "." in text:                                   # STK can print 9 decimals; datetime takes 6
        head, frac = text.rsplit(".", 1)
        text = f"{head}.{frac[:6]}"
    for fmt in ("%d %b %Y %H:%M:%S.%f", "%d %b %Y %H:%M:%S"):
        try:
            return datetime.strptime(text, fmt).replace(tzinfo=UTC)
        except ValueError:
            pass
    raise ValueError(f"not an STK time: {text!r}")


def stk_format(t, decimals=3):
    """Format a datetime the way STK prints it."""
    s = t.strftime("%d %b %Y %H:%M:%S.%f")
    return s[: len(s) - (6 - decimals)] if decimals < 6 else s


def jd_parts(times):
    """Two-part Julian dates (whole, fraction) for a sequence of datetimes, as contiguous arrays."""
    jd = np.empty(len(times))
    fr = np.empty(len(times))
    for k, t in enumerate(times):
        jd[k], fr[k] = jday(t.year, t.month, t.day, t.hour, t.minute, t.second + t.microsecond * 1e-6)
    return jd, fr


def grid(start, seconds, step):
    """Datetimes from start, every `step` seconds, over `seconds` (inclusive of the end)."""
    n = int(round(seconds / step))
    return [start + timedelta(seconds=k * step) for k in range(n + 1)]


def tle_epoch(line1):
    """Epoch of a TLE as a datetime (two-digit years 57-99 are 1900s)."""
    yy = int(line1[18:20])
    day = float(line1[20:32])
    year = 1900 + yy if yy >= 57 else 2000 + yy
    return datetime(year, 1, 1, tzinfo=UTC) + timedelta(days=day - 1.0)
