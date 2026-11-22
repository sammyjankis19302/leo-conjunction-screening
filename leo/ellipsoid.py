"""Safety ellipsoids and the three numbers AdvCAT reports for a close approach.

Each object carries an ellipsoid aligned with its own tangential / cross-track /
normal (NTC) axes:

    T = v/|v|,   C = (r x v)/|r x v|,   N = T x C

With shape matrix M = A^T diag(a^2) A (rows of A are T, C, N), the ellipsoid is
{x : (x - c)^T M^-1 (x - c) <= 1}.

  * Min Range       - centre-to-centre distance at closest approach
  * Min Separation  - smallest distance between the two ellipsoid surfaces
  * Intersect       - the ellipsoids overlap at some instant

For two ellipsoids, Perram and Wertheim's contact function

    F(lam) = lam (1 - lam) d^T [(1 - lam) M1 + lam M2]^-1 d,   d = c2 - c1

has a single maximum on (0, 1); the ellipsoids overlap when max F < 1, touch at
max F = 1, and are apart when max F > 1. When they are apart, the surface gap is

    gap = max over unit u of  u.d - sqrt(u^T M1 u) - sqrt(u^T M2 u)

(the separating-axis form of the distance between convex bodies).
"""
import numpy as np
from scipy.optimize import minimize, minimize_scalar


def ntc_axes(r, v):
    t = v / np.linalg.norm(v)
    c = np.cross(r, v)
    c /= np.linalg.norm(c)
    n = np.cross(t, c)
    return np.stack([t, c, n])


def shape_matrix(r, v, axes):
    A = ntc_axes(r, v)
    return A.T @ np.diag(np.asarray(axes, float) ** 2) @ A


def contact(d, M1, M2):
    """max over lam of the Perram-Wertheim contact function."""
    f = lambda lam: -lam * (1 - lam) * d @ np.linalg.solve((1 - lam) * M1 + lam * M2, d)
    res = minimize_scalar(f, bounds=(1e-9, 1 - 1e-9), method="bounded", options={"xatol": 1e-10})
    return -res.fun


def overlap(d, M1, M2):
    return contact(d, M1, M2) < 1.0


def gap(d, M1, M2, u0=None):
    """Distance between the ellipsoid surfaces (km); 0 if they overlap. Returns (gap, direction)."""
    if overlap(d, M1, M2):
        return 0.0, None
    u0 = d / np.linalg.norm(d) if u0 is None else u0

    def neg(x):
        u = x / np.linalg.norm(x)
        return -(u @ d - np.sqrt(u @ M1 @ u) - np.sqrt(u @ M2 @ u))

    res = minimize(neg, u0, method="BFGS", options={"gtol": 1e-12})
    res2 = minimize(neg, res.x, method="Nelder-Mead", options={"xatol": 1e-12, "fatol": 1e-13, "maxiter": 2000})
    best = res2 if res2.fun < res.fun else res
    return max(-best.fun, 0.0), best.x / np.linalg.norm(best.x)
