// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double2Ops} and its sibling kernel units. Not public API.
 */
public final class Double2OpsKernelsAddress {
    private Double2OpsKernelsAddress() {}

    public static long add_unsafe(long dest, long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, otherX + _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, otherY + _selfy);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _otherx + _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _othery + _selfy);
        return dest;
    }

    public static long div_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx / scalar);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy / scalar);
        return dest;
    }

    public static long div_unsafe(long dest, long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx / otherX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy / otherY);
        return dest;
    }

    public static long div_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx / _otherx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy / _othery);
        return dest;
    }

    public static long fma_unsafe(long dest, long src, double b, double cX, double cY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_selfx, b, cX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, b, cY));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, long c, double b) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _cx = UnsafeOpsHolder.U.getDouble(c + 0L);
        double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_selfx, b, _cx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, b, _cy));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, double bX, double bY, double cX, double cY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_selfx, bX, cX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, bY, cY));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, long b, long c) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _bx = UnsafeOpsHolder.U.getDouble(b + 0L);
        double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
        double _cx = UnsafeOpsHolder.U.getDouble(c + 0L);
        double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_selfx, _bx, _cx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, _by, _cy));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, scalar * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, scalar * _selfy);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, otherX * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, otherY * _selfy);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _otherx * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _othery * _selfy);
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, -_selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_selfy);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx - otherX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy - otherY);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx - _otherx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy - _othery);
        return dest;
    }

    public static long makeUniformDirection_unsafe(long dest, double u) {
        double _t0 = u * 6.283185307179586;
        double _t1 = Math.sin(_t0);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.cosFromSin(_t1, _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t1);
        return dest;
    }

    public static long set_unsafe(long dest, double vX, double vY) {
        UnsafeOpsHolder.U.putDouble(dest + 0L, vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, vY);
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _vy);
        return dest;
    }

    public static long set_unsafe(long dest, double s) {
        UnsafeOpsHolder.U.putDouble(dest + 0L, s);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s);
        return dest;
    }

    public static long makeZero_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest + 0L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(p1X, _t7, _selfx * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, long p1, long p2, long p3, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1 + 0L);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2 + 0L);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3 + 0L);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_p1x, _t7, _selfx * _t8) + Math.fma(_p2x, _t6, _p3x * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, double p1X, double p1Y, double p2X, double p2Y, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, long p1, long p2, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1 + 0L);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2 + 0L);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, double p1X, double p1Y, double p2X, double p2Y, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, long p1, long p2, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1 + 0L);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2 + 0L);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, long p1, long p2, long p3, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1 + 0L);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2 + 0L);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3 + 0L);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        UnsafeOpsHolder.U.putDouble(dest + 0L, 0.5 * (Math.fma(2.0, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, long p1, long p2, long p3, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1 + 0L);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2 + 0L);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3 + 0L);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        UnsafeOpsHolder.U.putDouble(dest + 0L, 0.5 * (Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), _t0, Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * (Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), _t0, Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0 = t * t;
        UnsafeOpsHolder.U.putDouble(dest + 0L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, long p1, long p2, long p3, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1 + 0L);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2 + 0L);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3 + 0L);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _t0 = t * t;
        UnsafeOpsHolder.U.putDouble(dest + 0L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), Math.fma(3.0 * Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), Math.fma(3.0 * Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, long t0, long v1, long t1, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0x = UnsafeOpsHolder.U.getDouble(t0 + 0L);
        double _t0y = UnsafeOpsHolder.U.getDouble(t0 + 8L);
        double _v1x = UnsafeOpsHolder.U.getDouble(v1 + 0L);
        double _v1y = UnsafeOpsHolder.U.getDouble(v1 + 8L);
        double _t1x = UnsafeOpsHolder.U.getDouble(t1 + 0L);
        double _t1y = UnsafeOpsHolder.U.getDouble(t1 + 8L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, long t0, long v1, long t1, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0x = UnsafeOpsHolder.U.getDouble(t0 + 0L);
        double _t0y = UnsafeOpsHolder.U.getDouble(t0 + 8L);
        double _v1x = UnsafeOpsHolder.U.getDouble(v1 + 0L);
        double _v1y = UnsafeOpsHolder.U.getDouble(v1 + 8L);
        double _t1x = UnsafeOpsHolder.U.getDouble(t1 + 0L);
        double _t1y = UnsafeOpsHolder.U.getDouble(t1 + 8L);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, double otherX, double otherY, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(t, otherX - _selfx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(t, otherY - _selfy, _selfy));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(t, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(t, _othery - _selfy, _selfy));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, double otherX, double otherY, double tX, double tY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(tX, otherX - _selfx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(tY, otherY - _selfy, _selfy));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, long t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _tx = UnsafeOpsHolder.U.getDouble(t + 0L);
        double _ty = UnsafeOpsHolder.U.getDouble(t + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_tx, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_ty, _othery - _selfy, _selfy));
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, double otherX, double otherY, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 2.2250738585072014E-308 && _t5 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t);
        double _t6 = Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 2.2250738585072014E-308 && _t6 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t);
        double _t7 = (1.0 / Math.sqrt(_t5));
        double _t10 = (1.0 / Math.sqrt(_t6));
        double _t12 = _selfx * _t7;
        double _t16 = _selfy * _t7;
        double _t20 = t * Math.sqrt(_t6) + (1.0 - t) * Math.sqrt(_t5);
        double _t21 = Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        double _t26 = Math.fma(otherX, _t10, -(_t21 * _t12));
        double _t27 = Math.fma(otherY, _t10, -(_t21 * _t16));
        double _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        double _t31 = Math.fma(_t30, _t12, _t26);
        double _t32 = Math.fma(_t30, _t16, _t27);
        double _t35 = Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 5.048709793414476E-29 && _t35 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t);
        double _t39 = t * Math.atan2(Math.sqrt(_t35), _t21);
        double _sp0 = _t20 * Math.sin(_t39) * (1.0 / Math.sqrt(_t35));
        double _t44 = _t20 * Math.cos(_t39);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t12, _t44, _sp0 * _t31));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, double otherX, double otherY, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, t);
        Double2OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, t);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, double otherX, double otherY, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t1 = unitScale(otherX, otherY, otherX);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = otherX * _t1;
        double _t8 = otherY * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t11_inv = 1.0 / _t11;
        double _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / Math.sqrt(_t18));
        double _t23 = (1.0 / Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t28 = -_t27;
        double _t37 = t * Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * Math.sqrt(_t19) * (_t11 / _t2);
        double _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        double _t48 = Math.fma(_t47, _t25, _t43);
        double _t49 = Math.fma(_t47, _t27, _t44);
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / Math.sqrt(_t60));
        double _t64 = t * Math.atan2(Math.sqrt(_t60), _t38 * _t51);
        double _t68 = _t37 * Math.sin(_t64);
        double _t69 = _t37 * Math.cos(_t64);
        double _t72, _t73;
        if (_t60 > 0.0) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 5.048709793414476E-29) {
                    UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(t, otherY - _selfy, _selfy));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 2.2250738585072014E-308 && _t5 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t6 = Math.fma(_otherx, _otherx, _othery * _othery);
        if (!(_t6 > 2.2250738585072014E-308 && _t6 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t7 = (1.0 / Math.sqrt(_t5));
        double _t10 = (1.0 / Math.sqrt(_t6));
        double _t12 = _selfx * _t7;
        double _t16 = _selfy * _t7;
        double _t20 = t * Math.sqrt(_t6) + (1.0 - t) * Math.sqrt(_t5);
        double _t21 = Math.fma(_otherx * _t10, _t12, _othery * _t10 * _t16);
        double _t26 = Math.fma(_otherx, _t10, -(_t21 * _t12));
        double _t27 = Math.fma(_othery, _t10, -(_t21 * _t16));
        double _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        double _t31 = Math.fma(_t30, _t12, _t26);
        double _t32 = Math.fma(_t30, _t16, _t27);
        double _t35 = Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 5.048709793414476E-29 && _t35 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t39 = t * Math.atan2(Math.sqrt(_t35), _t21);
        double _sp0 = _t20 * Math.sin(_t39) * (1.0 / Math.sqrt(_t35));
        double _t44 = _t20 * Math.cos(_t39);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t12, _t44, _sp0 * _t31));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t);
        Double2OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L, t);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _t1 = unitScale(_otherx, _othery, _otherx);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = _otherx * _t1;
        double _t8 = _othery * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t11_inv = 1.0 / _t11;
        double _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / Math.sqrt(_t18));
        double _t23 = (1.0 / Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t28 = -_t27;
        double _t37 = t * Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * Math.sqrt(_t19) * (_t11 / _t2);
        double _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        double _t48 = Math.fma(_t47, _t25, _t43);
        double _t49 = Math.fma(_t47, _t27, _t44);
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / Math.sqrt(_t60));
        double _t64 = t * Math.atan2(Math.sqrt(_t60), _t38 * _t51);
        double _t68 = _t37 * Math.sin(_t64);
        double _t69 = _t37 * Math.cos(_t64);
        double _t72, _t73;
        if (_t60 > 0.0) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 5.048709793414476E-29) {
                    UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(t, _othery - _selfy, _selfy));
        }
        return dest;
    }

    public static long absolute_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.abs(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.abs(_selfy));
        return dest;
    }

    public static long acos_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.acos(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.acos(_selfy));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, double bX, double bY, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(scalar, bX, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(scalar, bY, _selfy));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long b, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _bx = UnsafeOpsHolder.U.getDouble(b + 0L);
        double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(scalar, _bx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(scalar, _by, _selfy));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, double bX, double bY, double cX, double cY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(bX, cX, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(bY, cY, _selfy));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long b, long c) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _bx = UnsafeOpsHolder.U.getDouble(b + 0L);
        double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
        double _cx = UnsafeOpsHolder.U.getDouble(c + 0L);
        double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_bx, _cx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_by, _cy, _selfy));
        return dest;
    }

    public static double angleBetween_unsafe(long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _ct0 = Math.abs(Math.fma(otherY, _selfx, -(otherX * _selfy)));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY);
        return Math.atan2(_ct0, Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static double angleBetween_degenerate(long src, double otherX, double otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2OpsKernelsAddress.angleBetween_degenerate_unsafe(src, otherX, otherY);
        return Double2OpsKernelsSegment.angleBetween_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY);
    }

    public static double angleBetween_degenerate_unsafe(long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = otherY * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = otherX * _t0;
        double _t9 = _selfy * _t1;
        double _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static double angleBetween_unsafe(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _ct0 = Math.abs(Math.fma(_othery, _selfx, -(_otherx * _selfy)));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsAddress.angleBetween_degenerate(src, other);
        return Math.atan2(_ct0, Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static double angleBetween_degenerate(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2OpsKernelsAddress.angleBetween_degenerate_unsafe(src, other);
        return Double2OpsKernelsSegment.angleBetween_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
    }

    public static double angleBetween_degenerate_unsafe(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _t0 = unitScale(_otherx, _othery, _otherx);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = _othery * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = _otherx * _t0;
        double _t9 = _selfy * _t1;
        double _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static long asin_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.asin(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.asin(_selfy));
        return dest;
    }

    public static long atan_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.atan(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan(_selfy));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, double x) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.atan2(_selfx, x));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_selfy, x));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, double xX, double xY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.atan2(_selfx, xX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_selfy, xY));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, long x) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _xx = UnsafeOpsHolder.U.getDouble(x + 0L);
        double _xy = UnsafeOpsHolder.U.getDouble(x + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.atan2(_selfx, _xx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_selfy, _xy));
        return dest;
    }

    public static long cbrt_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.cbrt(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.cbrt(_selfy));
        return dest;
    }

    public static long ceil_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.ceil(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.ceil(_selfy));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, double min, double max) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.min(Math.max(_selfx, min), max));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(Math.max(_selfy, min), max));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, double minX, double minY, double maxX, double maxY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.min(Math.max(_selfx, minX), maxX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(Math.max(_selfy, minY), maxY));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, long min, long max) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _minx = UnsafeOpsHolder.U.getDouble(min + 0L);
        double _miny = UnsafeOpsHolder.U.getDouble(min + 8L);
        double _maxx = UnsafeOpsHolder.U.getDouble(max + 0L);
        double _maxy = UnsafeOpsHolder.U.getDouble(max + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.min(Math.max(_selfx, _minx), _maxx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(Math.max(_selfy, _miny), _maxy));
        return dest;
    }

    public static double compAdd_unsafe(long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        return _selfx + _selfy;
    }

    public static double compMax_unsafe(long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        return Math.max(_selfx, _selfy);
    }

    public static double compMin_unsafe(long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        return Math.min(_selfx, _selfy);
    }

    public static double compMul_unsafe(long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        return _selfx * _selfy;
    }

    public static long copySign_unsafe(long dest, long src, double sign) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.copySign(_selfx, sign));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.copySign(_selfy, sign));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, double signX, double signY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.copySign(_selfx, signX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.copySign(_selfy, signY));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, long sign) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _signx = UnsafeOpsHolder.U.getDouble(sign + 0L);
        double _signy = UnsafeOpsHolder.U.getDouble(sign + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.copySign(_selfx, _signx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.copySign(_selfy, _signy));
        return dest;
    }

    public static long cos_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.cos(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.cos(_selfy));
        return dest;
    }

    public static long cosh_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.cosh(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.cosh(_selfy));
        return dest;
    }

    public static double cross_unsafe(long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        return Math.fma(otherY, _selfx, -(otherX * _selfy));
    }

    public static double cross_unsafe(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        return Math.fma(_othery, _selfx, -(_otherx * _selfy));
    }

    public static long degrees_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.toDegrees(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.toDegrees(_selfy));
        return dest;
    }

    public static double distance_unsafe(long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0 = _selfx - otherX;
        double _t1 = _selfy - otherY;
        return Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static double distance_unsafe(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _t0 = _selfx - _otherx;
        double _t1 = _selfy - _othery;
        return Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static double distanceSquared_unsafe(long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0 = _selfx - otherX;
        double _t1 = _selfy - otherY;
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static double distanceSquared_unsafe(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _t0 = _selfx - _otherx;
        double _t1 = _selfy - _othery;
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static double dot_unsafe(long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        return Math.fma(otherX, _selfx, otherY * _selfy);
    }

    public static double dot_unsafe(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        return Math.fma(_otherx, _selfx, _othery * _selfy);
    }

    public static long exp_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.exp(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.exp(_selfy));
        return dest;
    }

    public static long exp2_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.pow(2.0, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.pow(2.0, _selfy));
        return dest;
    }

    public static long expm1_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.expm1(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.expm1(_selfy));
        return dest;
    }

    public static long faceforward_unsafe(long dest, long src, double IX, double IY, double NrefX, double NrefY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        if (Math.fma(IX, NrefX, IY * NrefY) < 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 0L, -_selfx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -_selfy);
        }
        return dest;
    }

    public static long faceforward_unsafe(long dest, long src, long I, long Nref) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _Ix = UnsafeOpsHolder.U.getDouble(I + 0L);
        double _Iy = UnsafeOpsHolder.U.getDouble(I + 8L);
        double _Nrefx = UnsafeOpsHolder.U.getDouble(Nref + 0L);
        double _Nrefy = UnsafeOpsHolder.U.getDouble(Nref + 8L);
        if (Math.fma(_Ix, _Nrefx, _Iy * _Nrefy) < 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 0L, -_selfx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -_selfy);
        }
        return dest;
    }

    public static long floor_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.floor(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.floor(_selfy));
        return dest;
    }

    public static long fract_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, double y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.hypot(_selfx, y));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.hypot(_selfy, y));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, double yX, double yY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.hypot(_selfx, yX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.hypot(_selfy, yY));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, long y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _yx = UnsafeOpsHolder.U.getDouble(y + 0L);
        double _yy = UnsafeOpsHolder.U.getDouble(y + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.hypot(_selfx, _yx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.hypot(_selfy, _yy));
        return dest;
    }

    public static long inverse_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, 1.0 / _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 1.0 / _selfy);
        return dest;
    }

    public static long inverseSqrt_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, (1.0 / Math.sqrt(_selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, (1.0 / Math.sqrt(_selfy)));
        return dest;
    }

    public static double length_unsafe(long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        return Math.sqrt(Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    public static double lengthSquared_unsafe(long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        return Math.fma(_selfx, _selfx, _selfy * _selfy);
    }

    public static long log_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.log(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.log(_selfy));
        return dest;
    }

    public static long log10_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.log10(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.log10(_selfy));
        return dest;
    }

    public static long log1p_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.log1p(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.log1p(_selfy));
        return dest;
    }

    public static long log2_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.log2(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.log2(_selfy));
        return dest;
    }

    public static double manhattanDistance_unsafe(long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        return Math.abs(_selfx - otherX) + Math.abs(_selfy - otherY);
    }

    public static double manhattanDistance_unsafe(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        return Math.abs(_selfx - _otherx) + Math.abs(_selfy - _othery);
    }

    public static double manhattanLength_unsafe(long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        return Math.abs(_selfx) + Math.abs(_selfy);
    }

    public static long max_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.max(_selfx, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.max(_selfy, scalar));
        return dest;
    }

    public static long max_unsafe(long dest, long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.max(_selfx, otherX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.max(_selfy, otherY));
        return dest;
    }

    public static long max_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.max(_selfx, _otherx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.max(_selfy, _othery));
        return dest;
    }

    public static long min_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.min(_selfx, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(_selfy, scalar));
        return dest;
    }

    public static long min_unsafe(long dest, long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.min(_selfx, otherX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(_selfy, otherY));
        return dest;
    }

    public static long min_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.min(_selfx, _otherx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(_selfy, _othery));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, double y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, flooredMod(_selfx, y));
        UnsafeOpsHolder.U.putDouble(dest + 8L, flooredMod(_selfy, y));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, double yX, double yY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, flooredMod(_selfx, yX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, flooredMod(_selfy, yY));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, long y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _yx = UnsafeOpsHolder.U.getDouble(y + 0L);
        double _yy = UnsafeOpsHolder.U.getDouble(y + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, flooredMod(_selfx, _yx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, flooredMod(_selfy, _yy));
        return dest;
    }

    public static long nextDown_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.nextDown(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.nextDown(_selfy));
        return dest;
    }

    public static long nextUp_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.nextUp(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.nextUp(_selfy));
        return dest;
    }

    public static long normalize_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        double _t2 = (1.0 / Math.sqrt(_t1));
        if (_t1 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx * _t2);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t2);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 0L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        }
        return dest;
    }

    public static long normalizeMul_unsafe(long dest, long src, double length) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        double _t3 = length * (1.0 / Math.sqrt(_t1));
        if (_t1 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t3);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 0L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        }
        return dest;
    }

    public static double orientedAngle_unsafe(long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t2 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        double _ct0 = Math.abs(_t2);
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsAddress.orientedAngle_degenerate(src, otherX, otherY);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static double orientedAngle_degenerate(long src, double otherX, double otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2OpsKernelsAddress.orientedAngle_degenerate_unsafe(src, otherX, otherY);
        return Double2OpsKernelsSegment.orientedAngle_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY);
    }

    public static double orientedAngle_degenerate_unsafe(long src, double otherX, double otherY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = otherY * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = otherX * _t0;
        double _t9 = _selfy * _t1;
        double _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t15 = unitScale(_t14, _t14, _t14);
        double _t19 = _t14 * _t15;
        double _t21 = Math.atan2(Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0 ? -_t21 : _t21;
    }

    public static double orientedAngle_unsafe(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _t2 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        double _ct0 = Math.abs(_t2);
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsAddress.orientedAngle_degenerate(src, other);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static double orientedAngle_degenerate(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2OpsKernelsAddress.orientedAngle_degenerate_unsafe(src, other);
        return Double2OpsKernelsSegment.orientedAngle_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
    }

    public static double orientedAngle_degenerate_unsafe(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _t0 = unitScale(_otherx, _othery, _otherx);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = _othery * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = _otherx * _t0;
        double _t9 = _selfy * _t1;
        double _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t15 = unitScale(_t14, _t14, _t14);
        double _t19 = _t14 * _t15;
        double _t21 = Math.atan2(Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0 ? -_t21 : _t21;
    }

    public static long outerProduct_unsafe(long dest, long src, double rowX, double rowY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, rowX * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, rowX * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, rowY * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 24L, rowY * _selfy);
        return dest;
    }

    public static long outerProduct_unsafe(long dest, long src, long row) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _rowx = UnsafeOpsHolder.U.getDouble(row + 0L);
        double _rowy = UnsafeOpsHolder.U.getDouble(row + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _rowx * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _rowx * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _rowy * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _rowy * _selfy);
        return dest;
    }

    public static long pow_unsafe(long dest, long src, double exponent) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.pow(_selfx, exponent));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.pow(_selfy, exponent));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, double exponentX, double exponentY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.pow(_selfx, exponentX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.pow(_selfy, exponentY));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, long exponent) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _exponentx = UnsafeOpsHolder.U.getDouble(exponent + 0L);
        double _exponenty = UnsafeOpsHolder.U.getDouble(exponent + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.pow(_selfx, _exponentx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.pow(_selfy, _exponenty));
        return dest;
    }

    public static long project_unsafe(long dest, long src, double ontoX, double ontoY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t5 = Math.fma(ontoX, _selfx, ontoY * _selfy) / Math.fma(ontoX, ontoX, ontoY * ontoY);
        UnsafeOpsHolder.U.putDouble(dest + 0L, ontoX * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ontoY * _t5);
        return dest;
    }

    public static long project_unsafe(long dest, long src, long onto) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _ontox = UnsafeOpsHolder.U.getDouble(onto + 0L);
        double _ontoy = UnsafeOpsHolder.U.getDouble(onto + 8L);
        double _t5 = Math.fma(_ontox, _selfx, _ontoy * _selfy) / Math.fma(_ontox, _ontox, _ontoy * _ontoy);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _ontox * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _ontoy * _t5);
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, double normalX, double normalY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t1 = Math.fma(normalX, _selfx, normalY * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-normalX, _t1, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-normalY, _t1, _selfy));
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, long normal) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal + 0L);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _t1 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-_normalx, _t1, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-_normaly, _t1, _selfy));
        return dest;
    }

    public static long radians_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.toRadians(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.toRadians(_selfy));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, double normalX, double normalY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t2 = 2.0 * Math.fma(normalX, _selfx, normalY * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-normalX, _t2, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-normalY, _t2, _selfy));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, long normal) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal + 0L);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _t2 = 2.0 * Math.fma(_normalx, _selfx, _normaly * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-_normalx, _t2, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-_normaly, _t2, _selfy));
        return dest;
    }

    public static long refract_unsafe(long dest, long src, double normalX, double normalY, double eta) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t2 = Math.fma(normalX, _selfx, normalY * _selfy);
        double _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0), eta * eta, 1.0);
        double _t9 = Math.fma(eta, _t2, Math.sqrt(Math.max(0.0, _t6)));
        if (_t6 >= 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(eta, _selfx, -(normalX * _t9)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(eta, _selfy, -(normalY * _t9)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 0L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        }
        return dest;
    }

    public static long refract_unsafe(long dest, long src, long normal, double eta) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal + 0L);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _t2 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        double _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0), eta * eta, 1.0);
        double _t9 = Math.fma(eta, _t2, Math.sqrt(Math.max(0.0, _t6)));
        if (_t6 >= 0.0) {
            UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(eta, _selfx, -(_normalx * _t9)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(eta, _selfy, -(_normaly * _t9)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 0L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        }
        return dest;
    }

    public static long round_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.rint(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.rint(_selfy));
        return dest;
    }

    public static long sign_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.signum(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.signum(_selfy));
        return dest;
    }

    public static long sin_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.sin(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.sin(_selfy));
        return dest;
    }

    public static long sinh_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.sinh(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.sinh(_selfy));
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, double edge0, double edge1) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t7 = Math.max(0.0, Math.min(1.0, (_selfx - edge0) * _t0_inv));
        double _t8 = Math.max(0.0, Math.min(1.0, (_selfy - edge0) * _t0_inv));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-2.0, _t7, 3.0) * _t7 * _t7);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-2.0, _t8, 3.0) * _t8 * _t8);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, double edge0X, double edge0Y, double edge1X, double edge1Y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t8 = Math.max(0.0, Math.min(1.0, (_selfx - edge0X) / (edge1X - edge0X)));
        double _t9 = Math.max(0.0, Math.min(1.0, (_selfy - edge0Y) / (edge1Y - edge0Y)));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-2.0, _t8, 3.0) * _t8 * _t8);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-2.0, _t9, 3.0) * _t9 * _t9);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, long edge0, long edge1) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _edge0x = UnsafeOpsHolder.U.getDouble(edge0 + 0L);
        double _edge0y = UnsafeOpsHolder.U.getDouble(edge0 + 8L);
        double _edge1x = UnsafeOpsHolder.U.getDouble(edge1 + 0L);
        double _edge1y = UnsafeOpsHolder.U.getDouble(edge1 + 8L);
        double _t8 = Math.max(0.0, Math.min(1.0, (_selfx - _edge0x) / (_edge1x - _edge0x)));
        double _t9 = Math.max(0.0, Math.min(1.0, (_selfy - _edge0y) / (_edge1y - _edge0y)));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(-2.0, _t8, 3.0) * _t8 * _t8);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-2.0, _t9, 3.0) * _t9 * _t9);
        return dest;
    }

    public static long sqrt_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.sqrt(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.sqrt(_selfy));
        return dest;
    }

    public static long step_unsafe(long dest, long src, double edge) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx < edge ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy < edge ? 0.0 : 1.0);
        return dest;
    }

    public static long step_unsafe(long dest, long src, double edgeX, double edgeY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx < edgeX ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy < edgeY ? 0.0 : 1.0);
        return dest;
    }

    public static long step_unsafe(long dest, long src, long edge) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _edgex = UnsafeOpsHolder.U.getDouble(edge + 0L);
        double _edgey = UnsafeOpsHolder.U.getDouble(edge + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx < _edgex ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy < _edgey ? 0.0 : 1.0);
        return dest;
    }

    public static long tan_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.tan(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.tan(_selfy));
        return dest;
    }

    public static long tanh_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.tanh(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.tanh(_selfy));
        return dest;
    }

    public static long trunc_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy));
        return dest;
    }

    public static long ulp_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.ulp(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.ulp(_selfy));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat + 0L);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 24L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static long preMulDirectionMat2x3_unsafe(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat + 0L);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 24L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static long preMulDirectionMat3x3_unsafe(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat + 0L);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 24L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static long preMulPositionMat4x4_unsafe(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat + 0L);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat03 = UnsafeOpsHolder.U.getDouble(mat + 96L);
        double _mat13 = UnsafeOpsHolder.U.getDouble(mat + 104L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat03)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat13)));
        return dest;
    }

    public static long preMulPositionMat2x3_unsafe(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat + 0L);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 24L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat02)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static long preMulPositionMat3x3_unsafe(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat + 0L);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 24L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 56L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat02)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfx, _t0, _selfy * _t1));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, double angle, double pivotX, double pivotY) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _selfx - pivotX;
        double _t3 = _selfy - pivotY;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long pivot, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _selfx - _pivotx;
        double _t3 = _selfy - _pivoty;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivoty)));
        return dest;
    }

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent
     * field: multiplying by it is exact. Clamped to [2^-126, 2^126], so zero and subnormal
     * values scale up without overflow and the largest floats land in [2, 4).
     */
    private static float unitScale(float a, float b, float c) {
        int e = java.lang.Math.max(java.lang.Math.max(Float.floatToRawIntBits(a) & 0x7F800000,
                Float.floatToRawIntBits(b) & 0x7F800000), Float.floatToRawIntBits(c) & 0x7F800000);
        return Float.intBitsToFloat(0x7F000000 - java.lang.Math.min(java.lang.Math.max(e, 0x00800000), 0x7E800000));
    }

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }

    /**
     * The floored remainder of x and y, exactly kotlin.Float.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
    private static float flooredMod(float x, float y) {
        float q = (float) Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p24f && java.lang.Math.abs(y) <= Float.MAX_VALUE) {
            float r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0f), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        float r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }

    /** Double-precision twin of {@link #flooredMod(float, float)}. */
    private static double flooredMod(double x, double y) {
        double q = Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p53 && java.lang.Math.abs(y) <= Double.MAX_VALUE) {
            double r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        double r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }
}
