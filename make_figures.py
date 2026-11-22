"""Rebuild every generated figure in figures/ from the files in data/results/.

Run the analysis scripts first (see README, "Running it")."""
import csv
import os
import sys
from datetime import timedelta

import matplotlib

matplotlib.use("Agg")
import matplotlib.pyplot as plt                      # noqa: E402
import numpy as np                                   # noqa: E402
from matplotlib.patches import Ellipse               # noqa: E402

ROOT = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, ROOT)
from leo import ellipsoid as ell                     # noqa: E402
from leo import stk                                  # noqa: E402
from leo.propagate import state                      # noqa: E402
from leo.timeutil import stk_time                    # noqa: E402

R = os.path.join(ROOT, "data", "results")
FIG = os.path.join(ROOT, "figures")

# reference palette (light): categorical slots, ink, chrome, status
BLUE, ORANGE, AQUA = "#2a78d6", "#eb6834", "#1baf7a"
INK, INK2, MUTED = "#0b0b0b", "#52514e", "#898781"
GRID, AXIS, SURFACE = "#e1e0d9", "#c3c2b7", "#fcfcfb"
GOOD, WARNING, CRITICAL = "#0ca30c", "#fab219", "#d03b3b"

plt.rcParams.update({
    "font.family": "DejaVu Sans", "font.size": 9.5, "axes.titlesize": 10.5, "axes.titleweight": "bold",
    "axes.edgecolor": AXIS, "axes.labelcolor": INK2, "axes.linewidth": 0.8, "xtick.color": INK2, "ytick.color": INK2,
    "xtick.major.size": 0, "ytick.major.size": 0, "axes.grid": True, "grid.color": GRID, "grid.linewidth": 0.6,
    "axes.spines.top": False, "axes.spines.right": False, "figure.facecolor": SURFACE, "axes.facecolor": SURFACE,
    "savefig.facecolor": SURFACE, "legend.frameon": False, "text.color": INK, "axes.titlecolor": INK,
})


def rows(name):
    return list(csv.DictReader(open(os.path.join(R, name))))


def save(fig, name):
    fig.savefig(os.path.join(FIG, name), dpi=170, bbox_inches="tight")
    plt.close(fig)
    print("wrote figures/" + name)


def short(name):
    return name.rsplit("_", 1)[0]


# ---------------------------------------------------------------- STK agreement
def fig_stk_agreement():
    r = sorted(rows("stk_ephemeris_check.csv"), key=lambda x: float(x["max_dr_mm"]))
    names = [short(x["satellite"]) for x in r]
    vals = [float(x["max_dr_mm"]) for x in r]
    fig, ax = plt.subplots(figsize=(7.2, 7.4))
    y = np.arange(len(r))
    ax.barh(y, vals, height=0.62, color=BLUE)
    ax.set_yticks(y, names, fontsize=8.2)
    ax.set_xlim(0, 1.6)
    ax.set_xlabel("largest position difference over 10-11 Jul 2026  [mm]")
    ax.grid(axis="y", visible=False)
    ax.set_title("Open-source SGP4 against STK's stored ephemeris", loc="left", pad=20)
    ax.text(0, 1.008, f"33 satellites x 1,441 points each (60 s steps, ICRF). Worst case {max(vals):.2f} mm.",
            transform=ax.transAxes, color=INK2, fontsize=8.8)
    save(fig, "stk-agreement.png")


# ---------------------------------------------------------------- AdvCAT events
def event_series(sats, ev, half=6.0, dt=0.02):
    a = {s.name: s for s in sats}
    p, q = a[ev["primary"]].tle, a[ev["secondary"]].tle
    tca = stk_time(ev["ours_tca"])
    off = np.arange(-half, half + dt / 2, dt)
    times = [tca + timedelta(seconds=float(x)) for x in off]
    rp, vp = state(p, times)
    rs, vs = state(q, times)
    rng = np.linalg.norm(rs - rp, axis=1)
    gaps, over = [], []
    for k in range(len(off)):
        d = rs[k] - rp[k]
        M1, M2 = ell.shape_matrix(rp[k], vp[k], (20, 10, 5)), ell.shape_matrix(rs[k], vs[k], (20, 10, 5))
        g, _ = ell.gap(d, M1, M2)
        gaps.append(g)
        over.append(ell.overlap(d, M1, M2))
    return off, rng, np.array(gaps), np.array(over), tca


def fig_advcat_events():
    sats = stk.read_scenario(os.path.join(ROOT, "stk", "Hope_2"))
    evs = [e for e in rows("stk_event_check.csv") if e["primary"].startswith("CARTOSAT-2_")]
    evs.sort(key=lambda e: e["ours_tca"])
    fig, axes = plt.subplots(2, 3, figsize=(11.6, 5.6), sharex=True, gridspec_kw={"height_ratios": [1, 1]})
    for j, ev in enumerate(evs):
        off, rng, gap, over, tca = event_series(sats, ev)
        t_in = (stk_time(ev["stk_time_in"]) - tca).total_seconds()
        t_out = (stk_time(ev["stk_time_out"]) - tca).total_seconds()
        a1, a2 = axes[0, j], axes[1, j]
        a1.plot(off, rng, color=BLUE, lw=2)
        k = int(np.argmin(rng))
        a1.plot(off[k], rng[k], "o", ms=7, color=BLUE, mec=SURFACE, mew=2)
        a1.annotate(f"STK  {float(ev['stk_min_range_km']):.6f} km\nours {float(ev['ours_min_range_km']):.6f} km",
                    (off[k], rng[k]), xytext=(0, 30), textcoords="offset points", ha="center", fontsize=8.2, color=INK,
                    bbox=dict(fc=SURFACE, ec="none", pad=1.5))
        a1.set_ylim(0, 90)
        a1.set_title(f"{tca:%H:%M:%S} UTC  ·  {float(ev['rel_speed_km_s']):.1f} km/s", loc="left")
        a2.plot(off, gap, color=ORANGE, lw=2)
        a2.axhline(10, color=INK2, lw=1, ls=(0, (4, 3)))
        if over.any():
            a2.axvspan(off[over][0], off[over][-1], color=CRITICAL, alpha=0.13, lw=0)
            a2.text(0, 1.0, "ellipsoids\noverlap", ha="center", va="bottom", fontsize=8, color=INK2)
        for x in (t_in, t_out):
            for ax in (a1, a2):
                ax.axvline(x, color=MUTED, lw=1)
        sep = ev["stk_min_sep_km"]
        lab = "Min Separation\nSTK Intersect\nours Intersect" if sep == "Intersect" else \
            f"Min Separation\nSTK {float(sep):.6f} km\nours {float(ev['ours_min_sep_km']):.6f} km"
        a2.text(0.5, 0.97, lab, transform=a2.transAxes, ha="center", va="top", fontsize=8.2, color=INK,
                bbox=dict(fc=SURFACE, ec="none", pad=1.5))
        a2.set_ylim(0, 48 if sep == "Intersect" else 45)
        a2.set_xlabel("seconds from closest approach")
        a1.text(t_in, 4, " Time In", fontsize=8, color=INK2, ha="left")
        a1.text(t_out, 4, "Time Out ", fontsize=8, color=INK2, ha="right")
    axes[0, 0].set_ylabel("centre-to-centre range [km]")
    axes[1, 0].set_ylabel("gap between ellipsoids [km]")
    axes[1, 0].text(-5.8, 11.2, "10 km threshold", fontsize=8, color=INK2)
    fig.suptitle("The three CARTOSAT-2 / ASTROSAT close approaches, recomputed and compared with AdvCAT",
                 x=0.01, ha="left", fontsize=11, fontweight="bold")
    fig.text(0.01, 0.925, "Grey lines: AdvCAT's Time In and Time Out. The open-source values fall within 0.4 ms of them.",
             fontsize=8.8, color=INK2)
    fig.tight_layout(rect=(0, 0, 1, 0.92))
    save(fig, "advcat-reproduced.png")


# ---------------------------------------------------------------- encounter geometry
def fig_encounter():
    sats = stk.read_scenario(os.path.join(ROOT, "stk", "Hope_2"))
    ev = [e for e in rows("stk_event_check.csv") if e["primary"].startswith("CARTOSAT-2_") and e["stk_min_sep_km"] == "Intersect"][0]
    a = {s.name: s for s in sats}
    p, q = a[ev["primary"]].tle, a[ev["secondary"]].tle
    tca = stk_time(ev["ours_tca"])
    rp, vp = state(p, [tca])
    rs, vs = state(q, [tca])
    d0, w = (rs - rp)[0], (vs - vp)[0]
    ex = w / np.linalg.norm(w)                         # along the relative path
    ey = d0 - (d0 @ ex) * ex                           # across it, towards the miss
    ey /= np.linalg.norm(ey)
    P = np.stack([ex, ey], axis=1)
    t_in = (stk_time(ev["stk_time_in"]) - tca).total_seconds()
    t_out = (stk_time(ev["stk_time_out"]) - tca).total_seconds()
    fig, ax = plt.subplots(figsize=(11.0, 4.6))

    def draw(M, c, color, fill_alpha):
        S = P.T @ M @ P                                # shadow of the ellipsoid on the plane
        vals, vecs = np.linalg.eigh(S)
        ang = np.degrees(np.arctan2(vecs[1, 1], vecs[0, 1]))
        rgb = matplotlib.colors.to_rgb(color)
        ax.add_patch(Ellipse(c, 2 * np.sqrt(vals[1]), 2 * np.sqrt(vals[0]), angle=ang,
                             fc=(*rgb, fill_alpha), ec=color, lw=1.8))

    draw(ell.shape_matrix(rp[0], vp[0], (20, 10, 5)), (0, 0), BLUE, 0.12)
    ax.text(0, -16, "CARTOSAT-2's\nellipsoid", ha="center", va="top", fontsize=8.5, color=INK)
    states = [(t_in - 1.5, GOOD, "gap over 10 km:\nnothing reported"), (t_in, WARNING, "Time In:\ngap = 10 km"),
              (0.0, CRITICAL, "closest approach:\nellipsoids overlap"), (t_out, WARNING, "Time Out:\ngap = 10 km")]
    path = []
    for x, color, label in states:
        t = tca + timedelta(seconds=x)
        a_r, a_v = state(p, [t])
        b_r, b_v = state(q, [t])
        c = P.T @ (b_r - a_r)[0]
        path.append(c)
        draw(ell.shape_matrix(b_r[0], b_v[0], (20, 10, 5)), c, color, 0.10)
        ax.plot(*c, "o", ms=5, color=color, mec=SURFACE, mew=1.5)
        below = color == GOOD
        ax.text(c[0] - (6 if below else 0), -15 if below else 30, f"{label}\nt = {x:+.2f} s", ha="center",
                va="top" if below else "bottom", fontsize=8.2, color=INK)
    path = np.array(path)
    ax.annotate("", xy=(path[-1][0] + 14, path[-1][1]), xytext=(path[0][0] - 14, path[0][1]),
                arrowprops=dict(arrowstyle="->", color=INK2, lw=1), zorder=0)
    ax.text(path[-1][0] + 15, path[-1][1], "ASTROSAT,\nrelative path", va="center", fontsize=8, color=INK2)
    ax.set_aspect("equal")
    ax.set_xlim(-92, 92)
    ax.set_ylim(-34, 52)
    ax.set_xlabel("km along the relative path (11.9 km/s)")
    ax.set_ylabel("km across it")
    ax.set_title("What AdvCAT measures: the 07:33:51 close approach, seen from CARTOSAT-2", loc="left", pad=22)
    ax.text(0, 1.03, "ASTROSAT's ellipsoid at four instants. Both ellipsoids are projected onto the plane of relative "
            f"motion; Min Range {float(ev['stk_min_range_km']):.2f} km.", transform=ax.transAxes, fontsize=8.3, color=INK2)
    save(fig, "encounter-geometry.png")


# ---------------------------------------------------------------- TLE age
def fig_tle_age():
    r = [x for x in rows("tle_age.csv") if x["in_active_2026"] == "True"]
    from datetime import datetime
    age = [(datetime.fromisoformat(x["epoch_2026"]) - datetime.fromisoformat(x["epoch_used"])).days for x in r]
    dist = [float(x["dist_at_2026_epoch_km"]) for x in r]
    fig, ax = plt.subplots(figsize=(8.2, 4.8))
    ax.set_xscale("log")
    ax.set_yscale("log")
    ax.axhline(10, color=INK2, lw=1, ls=(0, (4, 3)))
    ax.text(0.33, 11.5, "10 km: the screening threshold", fontsize=8.2, color=INK2)
    ax.axhline(2 * 7100, color=INK2, lw=1, ls=(0, (4, 3)))
    ax.text(0.33, 2 * 7100 * 1.15, "~14,000 km: an orbit's diameter, the largest error possible", fontsize=8.2, color=INK2)
    ax.plot([0.56, 4.89], [0.10, 0.42], "o", ms=8, color=BLUE, mec=SURFACE, mew=2,
            label="same object, two element sets from April 2026")
    ax.annotate("IRIDIUM 33\n0.56 days: 0.10 km", (0.56, 0.10), xytext=(10, -4), textcoords="offset points", fontsize=8.2, va="top")
    ax.annotate("COSMOS 2251\n4.9 days: 0.42 km", (4.89, 0.42), xytext=(10, -4), textcoords="offset points", fontsize=8.2, va="top")
    ax.plot(age, dist, "o", ms=8, color=ORANGE, mec=SURFACE, mew=2,
            label="Hope_2 satellites: STK's 2017 element set against May 2026")
    ax.annotate(f"18 satellites, 9.1 years:\n{min(dist):,.0f}-{max(dist):,.0f} km", (np.median(age), np.median(dist)),
                xytext=(-150, -26), textcoords="offset points", fontsize=8.2)
    ax.set_xlim(0.3, 2e4)
    ax.set_ylim(0.03, 1e5)
    ax.set_xlabel("age of the element set [days]")
    ax.set_ylabel("position error [km]")
    ax.set_title("What an element set's age costs", loc="left")
    ax.legend(loc="center right", bbox_to_anchor=(1.0, 0.6), fontsize=8.2)
    save(fig, "tle-age.png")


def fig_scenario():
    r = rows("tle_age.csv")
    tl = {t.name: t for t in __import__("leo.tle", fromlist=["read_tles"]).read_tles(
        os.path.join(ROOT, "data", "tle", "scenario_2017-03-29.tle"))}
    fig, ax = plt.subplots(figsize=(8.2, 4.6))
    for flag, face, lab in (("True", BLUE, "in CelesTrak's active list, May 2026"),
                            ("False", "none", "not in the active list: inactive or re-entered")):
        sel = [x for x in r if x["in_active_2026"] == flag]
        inc = [tl[x["satellite"]].inc_deg for x in sel]
        alt = [float(x["alt_2017_km"]) for x in sel]
        ax.plot(inc, alt, "o", ms=8, mfc=face, mec=BLUE if flag == "True" else INK2, mew=1.6, ls="none", label=lab)
    t = tl["RISAT-2_34807"]
    ax.annotate("RISAT-2: re-entered 30 Oct 2022. In the July 2026\nscenario STK flew it ~430 km below the surface", (t.inc_deg, t.a - 6378.135),
                xytext=(18, 46), textcoords="offset points", fontsize=8.2,
                arrowprops=dict(arrowstyle="-", color=INK2, lw=0.8))
    t = tl["ASTROSAT_40930"]
    ax.annotate("ASTROSAT", (t.inc_deg, t.a - 6378.135), xytext=(8, 4), textcoords="offset points", fontsize=8.2)
    ax.set_xlabel("inclination [deg]")
    ax.set_ylabel("mean altitude, 2017 element set [km]")
    ax.set_title("The 33 satellites of the July 2026 scenario", loc="left")
    ax.legend(loc="upper left", fontsize=8.2)
    save(fig, "hope2-satellites.png")


# ---------------------------------------------------------------- 2026 screening
def fig_screening():
    ev = rows("screening_2026-05-01.csv")
    alt = {x["satellite"]: float(x["alt_2026_km"]) for x in rows("tle_age.csv") if x["alt_2026_km"]}
    names = sorted(alt, key=lambda n: sum(e["primary"] == n for e in ev))
    inter = [sum(e["primary"] == n and e["min_separation_km"] == "Intersect" for e in ev) for n in names]
    near = [sum(e["primary"] == n and e["min_separation_km"] != "Intersect" for e in ev) for n in names]
    fig, (a1, a2) = plt.subplots(1, 2, figsize=(11.6, 5.4), gridspec_kw={"width_ratios": [1.25, 1]})
    y = np.arange(len(names))
    a1.barh(y, inter, height=0.66, color=BLUE, label="ellipsoids intersect")
    a1.barh(y, near, left=np.array(inter) + 0.6, height=0.66, color=ORANGE, label="gap under 10 km, no overlap")
    a1.set_yticks(y, [f"{short(n)}  ({alt[n]:.0f} km)" for n in names], fontsize=8.2)
    for k in range(len(names)):
        a1.text(inter[k] + near[k] + 3, k, str(inter[k] + near[k]), va="center", fontsize=8, color=INK2)
    a1.grid(axis="y", visible=False)
    a1.set_xlabel("events in 24 h")
    a1.set_title("Events per satellite, 1-2 May 2026", loc="left")
    a1.legend(loc="lower right", fontsize=8.2)
    v = np.array([float(e["rel_speed_km_s"]) for e in ev])
    a2.hist(v, bins=np.arange(0, 16.01, 0.5), color=BLUE, rwidth=0.86)
    a2.set_xlabel("relative speed at closest approach [km/s]")
    a2.set_ylabel("events")
    a2.set_title(f"How fast they pass ({len(ev)} events)", loc="left")
    a2.grid(axis="x", visible=False)
    fig.tight_layout()
    save(fig, "screening-2026.png")


# ---------------------------------------------------------------- debris clouds
def fig_debris():
    d = rows("debris_clouds.csv")
    fig, (a1, a2) = plt.subplots(1, 2, figsize=(11.6, 4.8), gridspec_kw={"width_ratios": [1.15, 1]})
    for cloud, color in (("Cosmos 2251", BLUE), ("Iridium 33", ORANGE)):
        s = [x for x in d if x["cloud"] == cloud]
        per = np.array([float(x["period_min"]) for x in s])
        a1.plot(per, [float(x["apogee_km"]) for x in s], "o", ms=3.2, color=color, alpha=0.75, mew=0,
                label=f"{cloud} ({len(s)} objects)")
        a1.plot(per, [float(x["perigee_km"]) for x in s], "o", ms=3.2, mfc="none", mec=color, mew=0.8, alpha=0.75)
        raan = np.array([float(x["raan_deg"]) for x in s])
        h, edges = np.histogram(raan, bins=np.arange(0, 361, 15))
        a2.step(edges, 100 * np.append(h, h[-1]) / len(s), where="post", color=color, lw=2, label=cloud)
    a1.axhline(789, color=INK2, lw=1, ls=(0, (4, 3)))
    a1.text(a1.get_xlim()[0] if False else 92.2, 805, "collision altitude, 789 km", fontsize=8, color=INK2)
    a1.set_xlabel("orbital period [min]")
    a1.set_ylabel("altitude [km]: apogee (filled), perigee (open)")
    a1.set_title("Gabbard diagram, April 2026", loc="left")
    a1.legend(loc="upper left", fontsize=8.2, markerscale=2)
    a2.axhline(100 * 15 / 360, color=INK2, lw=1, ls=(0, (4, 3)))
    a2.text(4, 100 * 15 / 360 + 0.5, "evenly spread", fontsize=8, color=INK2)
    a2.set_xlim(0, 360)
    a2.set_xticks(range(0, 361, 60))
    a2.set_xlabel("right ascension of the ascending node [deg]")
    a2.set_ylabel("share of the cloud per 15 deg bin [%]")
    a2.set_title("Where the orbit planes point, 17 years on", loc="left")
    a2.legend(loc="upper right", fontsize=8.2)
    fig.tight_layout()
    save(fig, "debris-clouds.png")


if __name__ == "__main__":
    os.makedirs(FIG, exist_ok=True)
    fig_stk_agreement()
    fig_advcat_events()
    fig_encounter()
    fig_tle_age()
    fig_scenario()
    fig_screening()
    fig_debris()
