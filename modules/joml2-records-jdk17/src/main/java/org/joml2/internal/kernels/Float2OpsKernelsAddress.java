// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float2Ops} and its sibling kernel units. Not public API.
 */
public final class Float2OpsKernelsAddress {
    private Float2OpsKernelsAddress() {}

    public static long add_unsafe(long dest, long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, otherX + _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, otherY + _selfy);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _otherx + _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _othery + _selfy);
        return dest;
    }

    public static long div_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx / scalar);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / scalar);
        return dest;
    }

    public static long div_unsafe(long dest, long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx / otherX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / otherY);
        return dest;
    }

    public static long div_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx / _otherx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / _othery);
        return dest;
    }

    public static long fma_unsafe(long dest, long src, float b, float cX, float cY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, b, cX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, b, cY));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, long c, float b) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _cx = UnsafeOpsHolder.U.getFloat(c + 0L);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, b, _cx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, b, _cy));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, float bX, float bY, float cX, float cY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, bX, cX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, bY, cY));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, long b, long c) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _bx = UnsafeOpsHolder.U.getFloat(b + 0L);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _cx = UnsafeOpsHolder.U.getFloat(c + 0L);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _bx, _cx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _by, _cy));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, scalar * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, scalar * _selfy);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, otherX * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, otherY * _selfy);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _otherx * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _othery * _selfy);
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, -_selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_selfy);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx - otherX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy - otherY);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx - _otherx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy - _othery);
        return dest;
    }

    public static long makeUniformDirection_unsafe(long dest, float u) {
        float _t0 = u * 6.2831855f;
        float _t1 = (float) Math.sin(_t0);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.cosFromSin(_t1, _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t1);
        return dest;
    }

    public static long set_unsafe(long dest, float vX, float vY) {
        UnsafeOpsHolder.U.putFloat(dest + 0L, vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, vY);
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v + 0L);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _vy);
        return dest;
    }

    public static long set_unsafe(long dest, float s) {
        UnsafeOpsHolder.U.putFloat(dest + 0L, s);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s);
        return dest;
    }

    public static long makeZero_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest + 0L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(p1X, _t7, _selfx * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1 + 0L);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2 + 0L);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3 + 0L);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_p1x, _t7, _selfx * _t8) + Math.fma(_p2x, _t6, _p3x * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, long p1, long p2, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1 + 0L);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2 + 0L);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, long p1, long p2, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1 + 0L);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2 + 0L);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1 + 0L);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2 + 0L);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3 + 0L);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest + 0L, 0.5f * (Math.fma(2.0f, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1 + 0L);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2 + 0L);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3 + 0L);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest + 0L, 0.5f * (Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), _t0, Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), _t0, Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest + 0L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1 + 0L);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2 + 0L);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3 + 0L);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest + 0L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), Math.fma(3.0f * Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), Math.fma(3.0f * Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, long t0, long v1, long t1, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0 + 0L);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1 + 0L);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1 + 0L);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, long t0, long v1, long t1, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0 + 0L);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1 + 0L);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1 + 0L);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, float otherX, float otherY, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(t, otherX - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(t, otherY - _selfy, _selfy));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(t, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(t, _othery - _selfy, _selfy));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, float otherX, float otherY, float tX, float tY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(tX, otherX - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(tY, otherY - _selfy, _selfy));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, long t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _tx = UnsafeOpsHolder.U.getFloat(t + 0L);
        float _ty = UnsafeOpsHolder.U.getFloat(t + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_tx, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_ty, _othery - _selfy, _selfy));
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, float otherX, float otherY, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t);
        float _t6 = Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t);
        float _t7 = (1.0f / (float) Math.sqrt(_t5));
        float _t10 = (1.0f / (float) Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t20 = t * (float) Math.sqrt(_t6) + (1.0f - t) * (float) Math.sqrt(_t5);
        float _t21 = Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        float _t26 = Math.fma(otherX, _t10, -(_t21 * _t12));
        float _t27 = Math.fma(otherY, _t10, -(_t21 * _t16));
        float _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        float _t31 = Math.fma(_t30, _t12, _t26);
        float _t32 = Math.fma(_t30, _t16, _t27);
        float _t35 = Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 1.4551915E-11f && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, t);
        float _t39 = t * (float) Math.atan2((float) Math.sqrt(_t35), _t21);
        float _sp0 = _t20 * (float) Math.sin(_t39) * (1.0f / (float) Math.sqrt(_t35));
        float _t44 = _t20 * (float) Math.cos(_t39);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t12, _t44, _sp0 * _t31));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, float otherX, float otherY, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long slerp_degenerate_unsafe(long dest, long src, float otherX, float otherY, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t1 = unitScale(otherX, otherY, otherX);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = otherX * _t1;
        float _t8 = otherY * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = Math.min(_t2, _t1);
        float _t11_inv = 1.0f / _t11;
        float _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) Math.sqrt(_t18));
        float _t23 = (1.0f / (float) Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t28 = -_t27;
        float _t37 = t * (float) Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) Math.sqrt(_t19) * (_t11 / _t2);
        float _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        float _t48 = Math.fma(_t47, _t25, _t43);
        float _t49 = Math.fma(_t47, _t27, _t44);
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) Math.sqrt(_t60));
        float _t64 = t * (float) Math.atan2((float) Math.sqrt(_t60), _t38 * _t51);
        float _t68 = _t37 * (float) Math.sin(_t64);
        float _t69 = _t37 * (float) Math.cos(_t64);
        float _t72, _t73;
        if (_t60 > 0.0f) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(t, otherY - _selfy, _selfy));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t6 = Math.fma(_otherx, _otherx, _othery * _othery);
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t7 = (1.0f / (float) Math.sqrt(_t5));
        float _t10 = (1.0f / (float) Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t20 = t * (float) Math.sqrt(_t6) + (1.0f - t) * (float) Math.sqrt(_t5);
        float _t21 = Math.fma(_otherx * _t10, _t12, _othery * _t10 * _t16);
        float _t26 = Math.fma(_otherx, _t10, -(_t21 * _t12));
        float _t27 = Math.fma(_othery, _t10, -(_t21 * _t16));
        float _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        float _t31 = Math.fma(_t30, _t12, _t26);
        float _t32 = Math.fma(_t30, _t16, _t27);
        float _t35 = Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 1.4551915E-11f && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t39 = t * (float) Math.atan2((float) Math.sqrt(_t35), _t21);
        float _sp0 = _t20 * (float) Math.sin(_t39) * (1.0f / (float) Math.sqrt(_t35));
        float _t44 = _t20 * (float) Math.cos(_t39);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t12, _t44, _sp0 * _t31));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long slerp_degenerate_unsafe(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _t1 = unitScale(_otherx, _othery, _otherx);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = _otherx * _t1;
        float _t8 = _othery * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = Math.min(_t2, _t1);
        float _t11_inv = 1.0f / _t11;
        float _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) Math.sqrt(_t18));
        float _t23 = (1.0f / (float) Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t28 = -_t27;
        float _t37 = t * (float) Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) Math.sqrt(_t19) * (_t11 / _t2);
        float _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        float _t48 = Math.fma(_t47, _t25, _t43);
        float _t49 = Math.fma(_t47, _t27, _t44);
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) Math.sqrt(_t60));
        float _t64 = t * (float) Math.atan2((float) Math.sqrt(_t60), _t38 * _t51);
        float _t68 = _t37 * (float) Math.sin(_t64);
        float _t69 = _t37 * (float) Math.cos(_t64);
        float _t72, _t73;
        if (_t60 > 0.0f) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(t, _othery - _selfy, _selfy));
        }
        return dest;
    }

    public static long absolute_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.abs(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.abs(_selfy));
        return dest;
    }

    public static long acos_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.acos(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.acos(_selfy));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, float bX, float bY, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(scalar, bX, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(scalar, bY, _selfy));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long b, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _bx = UnsafeOpsHolder.U.getFloat(b + 0L);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(scalar, _bx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(scalar, _by, _selfy));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, float bX, float bY, float cX, float cY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(bX, cX, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(bY, cY, _selfy));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long b, long c) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _bx = UnsafeOpsHolder.U.getFloat(b + 0L);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _cx = UnsafeOpsHolder.U.getFloat(c + 0L);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_bx, _cx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_by, _cy, _selfy));
        return dest;
    }

    public static float angleBetween_unsafe(long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _ct0 = Math.abs(Math.fma(otherY, _selfx, -(otherX * _selfy)));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY);
        return (float) Math.atan2(_ct0, Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static float angleBetween_degenerate(long src, float otherX, float otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.angleBetween_degenerate_unsafe(src, otherX, otherY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static float angleBetween_degenerate_unsafe(long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = unitScale(otherX, otherY, otherX);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = otherY * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = otherX * _t0;
        float _t9 = _selfy * _t1;
        float _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t13 = unitScale(_t12, _t12, _t12);
        return (float) Math.atan2(Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static float angleBetween_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _ct0 = Math.abs(Math.fma(_othery, _selfx, -(_otherx * _selfy)));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.angleBetween_degenerate(src, other);
        return (float) Math.atan2(_ct0, Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static float angleBetween_degenerate(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.angleBetween_degenerate_unsafe(src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static float angleBetween_degenerate_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t13 = unitScale(_t12, _t12, _t12);
        return (float) Math.atan2(Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static long asin_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.asin(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.asin(_selfy));
        return dest;
    }

    public static long atan_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.atan(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.atan(_selfy));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, float x) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.atan2(_selfx, x));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.atan2(_selfy, x));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, float xX, float xY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.atan2(_selfx, xX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.atan2(_selfy, xY));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, long x) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _xx = UnsafeOpsHolder.U.getFloat(x + 0L);
        float _xy = UnsafeOpsHolder.U.getFloat(x + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.atan2(_selfx, _xx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.atan2(_selfy, _xy));
        return dest;
    }

    public static long cbrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.cbrt(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.cbrt(_selfy));
        return dest;
    }

    public static long ceil_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.ceil(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.ceil(_selfy));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, float min, float max) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(Math.max(_selfx, min), max));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, min), max));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, float minX, float minY, float maxX, float maxY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(Math.max(_selfx, minX), maxX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, minY), maxY));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, long min, long max) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _minx = UnsafeOpsHolder.U.getFloat(min + 0L);
        float _miny = UnsafeOpsHolder.U.getFloat(min + 4L);
        float _maxx = UnsafeOpsHolder.U.getFloat(max + 0L);
        float _maxy = UnsafeOpsHolder.U.getFloat(max + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(Math.max(_selfx, _minx), _maxx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, _miny), _maxy));
        return dest;
    }

    public static float compAdd_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        return _selfx + _selfy;
    }

    public static float compMax_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        return Math.max(_selfx, _selfy);
    }

    public static float compMin_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        return Math.min(_selfx, _selfy);
    }

    public static float compMul_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        return _selfx * _selfy;
    }

    public static long copySign_unsafe(long dest, long src, float sign) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.copySign(_selfx, sign));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, sign));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, float signX, float signY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.copySign(_selfx, signX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, signY));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, long sign) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _signx = UnsafeOpsHolder.U.getFloat(sign + 0L);
        float _signy = UnsafeOpsHolder.U.getFloat(sign + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.copySign(_selfx, _signx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, _signy));
        return dest;
    }

    public static long cos_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.cos(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.cos(_selfy));
        return dest;
    }

    public static long cosh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.cosh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.cosh(_selfy));
        return dest;
    }

    public static float cross_unsafe(long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        return Math.fma(otherY, _selfx, -(otherX * _selfy));
    }

    public static float cross_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        return Math.fma(_othery, _selfx, -(_otherx * _selfy));
    }

    public static long degrees_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.toDegrees(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.toDegrees(_selfy));
        return dest;
    }

    public static float distance_unsafe(long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = _selfx - otherX;
        float _t1 = _selfy - otherY;
        return (float) Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static float distance_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _t0 = _selfx - _otherx;
        float _t1 = _selfy - _othery;
        return (float) Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static float distanceSquared_unsafe(long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = _selfx - otherX;
        float _t1 = _selfy - otherY;
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static float distanceSquared_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _t0 = _selfx - _otherx;
        float _t1 = _selfy - _othery;
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static float dot_unsafe(long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        return Math.fma(otherX, _selfx, otherY * _selfy);
    }

    public static float dot_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        return Math.fma(_otherx, _selfx, _othery * _selfy);
    }

    public static long exp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.exp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.exp(_selfy));
        return dest;
    }

    public static long exp2_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.pow(2.0f, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.pow(2.0f, _selfy));
        return dest;
    }

    public static long expm1_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.expm1(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.expm1(_selfy));
        return dest;
    }

    public static long faceforward_unsafe(long dest, long src, float IX, float IY, float NrefX, float NrefY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        if (Math.fma(IX, NrefX, IY * NrefY) < 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, -_selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -_selfy);
        }
        return dest;
    }

    public static long faceforward_unsafe(long dest, long src, long I, long Nref) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _Ix = UnsafeOpsHolder.U.getFloat(I + 0L);
        float _Iy = UnsafeOpsHolder.U.getFloat(I + 4L);
        float _Nrefx = UnsafeOpsHolder.U.getFloat(Nref + 0L);
        float _Nrefy = UnsafeOpsHolder.U.getFloat(Nref + 4L);
        if (Math.fma(_Ix, _Nrefx, _Iy * _Nrefy) < 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, -_selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -_selfy);
        }
        return dest;
    }

    public static long floor_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.floor(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.floor(_selfy));
        return dest;
    }

    public static long fract_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(_selfx - (float) Math.floor(_selfx), 0.99999994f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy - (float) Math.floor(_selfy), 0.99999994f));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, float y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.hypot(_selfx, y));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.hypot(_selfy, y));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, float yX, float yY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.hypot(_selfx, yX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.hypot(_selfy, yY));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, long y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _yx = UnsafeOpsHolder.U.getFloat(y + 0L);
        float _yy = UnsafeOpsHolder.U.getFloat(y + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.hypot(_selfx, _yx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.hypot(_selfy, _yy));
        return dest;
    }

    public static long inverse_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, 1.0f / _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f / _selfy);
        return dest;
    }

    public static long inverseSqrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (1.0f / (float) Math.sqrt(_selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (1.0f / (float) Math.sqrt(_selfy)));
        return dest;
    }

    public static float length_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        return (float) Math.sqrt(Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    public static float lengthSquared_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        return Math.fma(_selfx, _selfx, _selfy * _selfy);
    }

    public static long log_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.log(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.log(_selfy));
        return dest;
    }

    public static long log10_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.log10(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.log10(_selfy));
        return dest;
    }

    public static long log1p_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.log1p(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.log1p(_selfy));
        return dest;
    }

    public static long log2_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.log2(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.log2(_selfy));
        return dest;
    }

    public static float manhattanDistance_unsafe(long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        return Math.abs(_selfx - otherX) + Math.abs(_selfy - otherY);
    }

    public static float manhattanDistance_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        return Math.abs(_selfx - _otherx) + Math.abs(_selfy - _othery);
    }

    public static float manhattanLength_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        return Math.abs(_selfx) + Math.abs(_selfy);
    }

    public static long max_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.max(_selfx, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, scalar));
        return dest;
    }

    public static long max_unsafe(long dest, long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.max(_selfx, otherX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, otherY));
        return dest;
    }

    public static long max_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.max(_selfx, _otherx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, _othery));
        return dest;
    }

    public static long min_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(_selfx, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, scalar));
        return dest;
    }

    public static long min_unsafe(long dest, long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(_selfx, otherX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, otherY));
        return dest;
    }

    public static long min_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(_selfx, _otherx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, _othery));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, float y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, flooredMod(_selfx, y));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, y));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, float yX, float yY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, flooredMod(_selfx, yX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, yY));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, long y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _yx = UnsafeOpsHolder.U.getFloat(y + 0L);
        float _yy = UnsafeOpsHolder.U.getFloat(y + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, flooredMod(_selfx, _yx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, _yy));
        return dest;
    }

    public static long nextDown_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.nextDown(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.nextDown(_selfy));
        return dest;
    }

    public static long nextUp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.nextUp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.nextUp(_selfy));
        return dest;
    }

    public static long normalize_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        float _t2 = (1.0f / (float) Math.sqrt(_t1));
        if (_t1 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx * _t2);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t2);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        }
        return dest;
    }

    public static long normalizeMul_unsafe(long dest, long src, float length) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        float _t3 = length * (1.0f / (float) Math.sqrt(_t1));
        if (_t1 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t3);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        }
        return dest;
    }

    public static float orientedAngle_unsafe(long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t2 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _ct0 = Math.abs(_t2);
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.orientedAngle_degenerate(src, otherX, otherY);
        return (float) Math.atan2(Math.copySign(_ct0, _t2), Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static float orientedAngle_degenerate(long src, float otherX, float otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.orientedAngle_degenerate_unsafe(src, otherX, otherY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static float orientedAngle_degenerate_unsafe(long src, float otherX, float otherY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = unitScale(otherX, otherY, otherX);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = otherY * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = otherX * _t0;
        float _t9 = _selfy * _t1;
        float _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t15 = unitScale(_t14, _t14, _t14);
        float _t19 = _t14 * _t15;
        float _t21 = (float) Math.atan2(Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0f ? -_t21 : _t21;
    }

    public static float orientedAngle_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _t2 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _ct0 = Math.abs(_t2);
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsAddress.orientedAngle_degenerate(src, other);
        return (float) Math.atan2(Math.copySign(_ct0, _t2), Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static float orientedAngle_degenerate(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2OpsKernelsAddress.orientedAngle_degenerate_unsafe(src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static float orientedAngle_degenerate_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t15 = unitScale(_t14, _t14, _t14);
        float _t19 = _t14 * _t15;
        float _t21 = (float) Math.atan2(Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0f ? -_t21 : _t21;
    }

    public static long outerProduct_unsafe(long dest, long src, float rowX, float rowY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, rowX * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, rowX * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, rowY * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 12L, rowY * _selfy);
        return dest;
    }

    public static long outerProduct_unsafe(long dest, long src, long row) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _rowx = UnsafeOpsHolder.U.getFloat(row + 0L);
        float _rowy = UnsafeOpsHolder.U.getFloat(row + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _rowx * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _rowx * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _rowy * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _rowy * _selfy);
        return dest;
    }

    public static long pow_unsafe(long dest, long src, float exponent) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.pow(_selfx, exponent));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.pow(_selfy, exponent));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, float exponentX, float exponentY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.pow(_selfx, exponentX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.pow(_selfy, exponentY));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, long exponent) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _exponentx = UnsafeOpsHolder.U.getFloat(exponent + 0L);
        float _exponenty = UnsafeOpsHolder.U.getFloat(exponent + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.pow(_selfx, _exponentx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.pow(_selfy, _exponenty));
        return dest;
    }

    public static long project_unsafe(long dest, long src, float ontoX, float ontoY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t5 = Math.fma(ontoX, _selfx, ontoY * _selfy) / Math.fma(ontoX, ontoX, ontoY * ontoY);
        UnsafeOpsHolder.U.putFloat(dest + 0L, ontoX * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ontoY * _t5);
        return dest;
    }

    public static long project_unsafe(long dest, long src, long onto) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _ontox = UnsafeOpsHolder.U.getFloat(onto + 0L);
        float _ontoy = UnsafeOpsHolder.U.getFloat(onto + 4L);
        float _t5 = Math.fma(_ontox, _selfx, _ontoy * _selfy) / Math.fma(_ontox, _ontox, _ontoy * _ontoy);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _ontox * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _ontoy * _t5);
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, float normalX, float normalY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t1 = Math.fma(normalX, _selfx, normalY * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-normalX, _t1, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-normalY, _t1, _selfy));
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, long normal) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal + 0L);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _t1 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-_normalx, _t1, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-_normaly, _t1, _selfy));
        return dest;
    }

    public static long radians_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.toRadians(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.toRadians(_selfy));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, float normalX, float normalY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t2 = 2.0f * Math.fma(normalX, _selfx, normalY * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-normalX, _t2, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-normalY, _t2, _selfy));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, long normal) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal + 0L);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _t2 = 2.0f * Math.fma(_normalx, _selfx, _normaly * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-_normalx, _t2, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-_normaly, _t2, _selfy));
        return dest;
    }

    public static long refract_unsafe(long dest, long src, float normalX, float normalY, float eta) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t2 = Math.fma(normalX, _selfx, normalY * _selfy);
        float _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0f), eta * eta, 1.0f);
        float _t9 = Math.fma(eta, _t2, (float) Math.sqrt(Math.max(0.0f, _t6)));
        if (_t6 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(eta, _selfx, -(normalX * _t9)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(eta, _selfy, -(normalY * _t9)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        }
        return dest;
    }

    public static long refract_unsafe(long dest, long src, long normal, float eta) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal + 0L);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _t2 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        float _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0f), eta * eta, 1.0f);
        float _t9 = Math.fma(eta, _t2, (float) Math.sqrt(Math.max(0.0f, _t6)));
        if (_t6 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(eta, _selfx, -(_normalx * _t9)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(eta, _selfy, -(_normaly * _t9)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        }
        return dest;
    }

    public static long round_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.rint(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.rint(_selfy));
        return dest;
    }

    public static long sign_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.signum(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.signum(_selfy));
        return dest;
    }

    public static long sin_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.sin(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.sin(_selfy));
        return dest;
    }

    public static long sinh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.sinh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.sinh(_selfy));
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, float edge0, float edge1) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t7 = Math.max(0.0f, Math.min(1.0f, (_selfx - edge0) * _t0_inv));
        float _t8 = Math.max(0.0f, Math.min(1.0f, (_selfy - edge0) * _t0_inv));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-2.0f, _t7, 3.0f) * _t7 * _t7);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, float edge0X, float edge0Y, float edge1X, float edge1Y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t8 = Math.max(0.0f, Math.min(1.0f, (_selfx - edge0X) / (edge1X - edge0X)));
        float _t9 = Math.max(0.0f, Math.min(1.0f, (_selfy - edge0Y) / (edge1Y - edge0Y)));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-2.0f, _t9, 3.0f) * _t9 * _t9);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, long edge0, long edge1) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _edge0x = UnsafeOpsHolder.U.getFloat(edge0 + 0L);
        float _edge0y = UnsafeOpsHolder.U.getFloat(edge0 + 4L);
        float _edge1x = UnsafeOpsHolder.U.getFloat(edge1 + 0L);
        float _edge1y = UnsafeOpsHolder.U.getFloat(edge1 + 4L);
        float _t8 = Math.max(0.0f, Math.min(1.0f, (_selfx - _edge0x) / (_edge1x - _edge0x)));
        float _t9 = Math.max(0.0f, Math.min(1.0f, (_selfy - _edge0y) / (_edge1y - _edge0y)));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-2.0f, _t9, 3.0f) * _t9 * _t9);
        return dest;
    }

    public static long sqrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.sqrt(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.sqrt(_selfy));
        return dest;
    }

    public static long step_unsafe(long dest, long src, float edge) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx < edge ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static long step_unsafe(long dest, long src, float edgeX, float edgeY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx < edgeX ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < edgeY ? 0.0f : 1.0f);
        return dest;
    }

    public static long step_unsafe(long dest, long src, long edge) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _edgex = UnsafeOpsHolder.U.getFloat(edge + 0L);
        float _edgey = UnsafeOpsHolder.U.getFloat(edge + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx < _edgex ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < _edgey ? 0.0f : 1.0f);
        return dest;
    }

    public static long tan_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.tan(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.tan(_selfy));
        return dest;
    }

    public static long tanh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.tanh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.tanh(_selfy));
        return dest;
    }

    public static long trunc_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx >= 0.0f ? (float) Math.floor(_selfx) : (float) Math.ceil(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy >= 0.0f ? (float) Math.floor(_selfy) : (float) Math.ceil(_selfy));
        return dest;
    }

    public static long ulp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.ulp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.ulp(_selfy));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat + 0L);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static long preMulDirectionMat2x3_unsafe(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat + 0L);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static long preMulDirectionMat3x3_unsafe(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat + 0L);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static long preMulPositionMat4x4_unsafe(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat + 0L);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat03 = UnsafeOpsHolder.U.getFloat(mat + 48L);
        float _mat13 = UnsafeOpsHolder.U.getFloat(mat + 52L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat03)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat13)));
        return dest;
    }

    public static long preMulPositionMat2x3_unsafe(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat + 0L);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat02)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static long preMulPositionMat3x3_unsafe(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat + 0L);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 28L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat02)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfx, _t0, _selfy * _t1));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, float angle, float pivotX, float pivotY) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - pivotX;
        float _t3 = _selfy - pivotY;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long pivot, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot + 0L);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - _pivotx;
        float _t3 = _selfy - _pivoty;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivoty)));
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
