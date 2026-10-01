// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float4Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float4Ops} and its sibling kernel units. Not public API.
 */
public final class Float4OpsKernelsAddress {
    private Float4OpsKernelsAddress() {}

    public static long add_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, otherX + _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, otherY + _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, otherZ + _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, otherW + _selfw);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _otherx + _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _othery + _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _otherz + _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _otherw + _selfw);
        return dest;
    }

    public static long div_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx / scalar);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / scalar);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz / scalar);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw / scalar);
        return dest;
    }

    public static long div_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx / otherX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / otherY);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz / otherZ);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw / otherW);
        return dest;
    }

    public static long div_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx / _otherx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / _othery);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz / _otherz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw / _otherw);
        return dest;
    }

    public static long fma_unsafe(long dest, long src, float b, float cX, float cY, float cZ, float cW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, b, cX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, b, cY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, b, cZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, b, cW));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, long c, float b) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _cx = UnsafeOpsHolder.U.getFloat(c + 0L);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        float _cw = UnsafeOpsHolder.U.getFloat(c + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, b, _cx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, b, _cy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, b, _cz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, b, _cw));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, bX, cX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, bY, cY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, bZ, cZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, bW, cW));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, long b, long c) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _bx = UnsafeOpsHolder.U.getFloat(b + 0L);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _bw = UnsafeOpsHolder.U.getFloat(b + 12L);
        float _cx = UnsafeOpsHolder.U.getFloat(c + 0L);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        float _cw = UnsafeOpsHolder.U.getFloat(c + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _bx, _cx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _by, _cy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, _bz, _cz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, scalar * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, scalar * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, scalar * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, scalar * _selfw);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, otherX * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, otherY * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, otherZ * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, otherW * _selfw);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _otherx * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _othery * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _otherz * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _otherw * _selfw);
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, -_selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_selfw);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx - otherX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy - otherY);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz - otherZ);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw - otherW);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx - _otherx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy - _othery);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz - _otherz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw - _otherw);
        return dest;
    }

    public static long makeUniformDirection_unsafe(long dest, float u, float v, float w) {
        float _t0 = (float) Math.sqrt(u);
        float _t1 = v * 6.2831855f;
        float _t3 = w * 6.2831855f;
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sqrt(1.0f - u);
        float _t6 = (float) Math.sin(_t3);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.cosFromSin(_t4, _t1) * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t4 * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.cosFromSin(_t6, _t3) * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t6 * _t0);
        return dest;
    }

    public static long set_unsafe(long dest, float vX, float vY, float vZ, float vW) {
        UnsafeOpsHolder.U.putFloat(dest + 0L, vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, vY);
        UnsafeOpsHolder.U.putFloat(dest + 8L, vZ);
        UnsafeOpsHolder.U.putFloat(dest + 12L, vW);
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v + 0L);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _vw = UnsafeOpsHolder.U.getFloat(v + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _vy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _vz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _vw);
        return dest;
    }

    public static long set_unsafe(long dest, float s) {
        UnsafeOpsHolder.U.putFloat(dest + 0L, s);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s);
        return dest;
    }

    public static long makeZero_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest + 0L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(p1X, _t7, _selfx * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(p1Z, _t7, _selfz * _t8) + Math.fma(p2Z, _t6, p3Z * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(p1W, _t7, _selfw * _t8) + Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1 + 0L);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2 + 0L);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3 + 0L);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _p3w = UnsafeOpsHolder.U.getFloat(p3 + 12L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_p1x, _t7, _selfx * _t8) + Math.fma(_p2x, _t6, _p3x * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_p1w, _t7, _selfw * _t8) + Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(p2W, _t0, Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, long p1, long p2, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1 + 0L);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2 + 0L);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_p2w, _t0, Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, long p1, long p2, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1 + 0L);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2 + 0L);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(p3W - p2W, _t2, Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1 + 0L);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2 + 0L);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3 + 0L);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _p3w = UnsafeOpsHolder.U.getFloat(p3 + 12L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_p3w - _p2w, _t2, Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest + 0L, 0.5f * (Math.fma(2.0f, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), _t0, Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (Math.fma(2.0f, p1W, t * (p2W - _selfw)) + Math.fma(Math.fma(-5.0f, p1W, Math.fma(2.0f, _selfw, Math.fma(4.0f, p2W, -p3W))), _t0, Math.fma(-3.0f, p2W, Math.fma(3.0f, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1 + 0L);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2 + 0L);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3 + 0L);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _p3w = UnsafeOpsHolder.U.getFloat(p3 + 12L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest + 0L, 0.5f * (Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), _t0, Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), _t0, Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + Math.fma(Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), _t0, Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (Math.fma(2.0f, _p1w, t * (_p2w - _selfw)) + Math.fma(Math.fma(-5.0f, _p1w, Math.fma(2.0f, _selfw, Math.fma(4.0f, _p2w, -_p3w))), _t0, Math.fma(-3.0f, _p2w, Math.fma(3.0f, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest + 0L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), Math.fma(3.0f * Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1W, Math.fma(2.0f, _selfw, Math.fma(4.0f, p2W, -p3W))), Math.fma(3.0f * Math.fma(-3.0f, p2W, Math.fma(3.0f, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1 + 0L);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2 + 0L);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3 + 0L);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _p3w = UnsafeOpsHolder.U.getFloat(p3 + 12L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest + 0L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), Math.fma(3.0f * Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), Math.fma(3.0f * Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), Math.fma(3.0f * Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1w, Math.fma(2.0f, _selfw, Math.fma(4.0f, _p2w, -_p3w))), Math.fma(3.0f * Math.fma(-3.0f, _p2w, Math.fma(3.0f, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, long t0, long v1, long t1, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0 + 0L);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _t0z = UnsafeOpsHolder.U.getFloat(t0 + 8L);
        float _t0w = UnsafeOpsHolder.U.getFloat(t0 + 12L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1 + 0L);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _v1z = UnsafeOpsHolder.U.getFloat(v1 + 8L);
        float _v1w = UnsafeOpsHolder.U.getFloat(v1 + 12L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1 + 0L);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t1z = UnsafeOpsHolder.U.getFloat(t1 + 8L);
        float _t1w = UnsafeOpsHolder.U.getFloat(t1 + 12L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t10, _t0w * _t7) + Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, long t0, long v1, long t1, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0 + 0L);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _t0z = UnsafeOpsHolder.U.getFloat(t0 + 8L);
        float _t0w = UnsafeOpsHolder.U.getFloat(t0 + 12L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1 + 0L);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _v1z = UnsafeOpsHolder.U.getFloat(v1 + 8L);
        float _v1w = UnsafeOpsHolder.U.getFloat(v1 + 12L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1 + 0L);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t1z = UnsafeOpsHolder.U.getFloat(t1 + 8L);
        float _t1w = UnsafeOpsHolder.U.getFloat(t1 + 12L);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t6, _t0w * _t9) + Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(t, otherX - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(t, otherY - _selfy, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(t, otherZ - _selfz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(t, otherW - _selfw, _selfw));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(t, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(t, _othery - _selfy, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(t, _otherz - _selfz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(tX, otherX - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(tY, otherY - _selfy, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(tZ, otherZ - _selfz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(tW, otherW - _selfw, _selfw));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, long t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _tx = UnsafeOpsHolder.U.getFloat(t + 0L);
        float _ty = UnsafeOpsHolder.U.getFloat(t + 4L);
        float _tz = UnsafeOpsHolder.U.getFloat(t + 8L);
        float _tw = UnsafeOpsHolder.U.getFloat(t + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_tx, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_ty, _othery - _selfy, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_tz, _otherz - _selfz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        float _t10 = Math.fma(otherW, otherW, Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) Math.sqrt(_t9));
        float _t14 = (1.0f / (float) Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        float _t28 = t * (float) Math.sqrt(_t10) + (1.0f - t) * (float) Math.sqrt(_t9);
        float _t31 = Math.fma(otherW * _t14, _t19, Math.fma(otherZ * _t14, _t21, Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        float _t40 = Math.fma(otherW, _t14, -(_t31 * _t19));
        float _t41 = Math.fma(otherZ, _t14, -(_t31 * _t21));
        float _t42 = Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = Math.fma(_t48, _t19, _t40);
        float _t50 = Math.fma(_t48, _t21, _t41);
        float _t51 = Math.fma(_t48, _t16, _t42);
        float _t52 = Math.fma(_t48, _t24, _t43);
        float _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        float _t61 = t * (float) Math.atan2((float) Math.sqrt(_t57), _t31);
        float _sp0 = _t28 * (float) Math.sin(_t61) * (1.0f / (float) Math.sqrt(_t57));
        float _t66 = _t28 * (float) Math.cos(_t61);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t16, _t66, _sp0 * _t51));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t24, _t66, _sp0 * _t52));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t21, _t66, _sp0 * _t50));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, otherZ, otherW, t);
        Float4OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t7 = unitScale(otherZ, otherW, Math.max(Math.abs(otherX), Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(Math.abs(_selfx), Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) Math.sqrt(_t36));
        float _t41 = (1.0f / (float) Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = Math.fma(_t80, _t43, _t72);
        float _t82 = Math.fma(_t80, _t45, _t73);
        float _t83 = Math.fma(_t80, _t47, _t74);
        float _t84 = Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(Math.abs(_t83), Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) Math.sqrt(_t106));
        float _t110 = t * (float) Math.atan2((float) Math.sqrt(_t106), _t63 * _t90);
        float _t114 = _t60 * (float) Math.sin(_t110);
        float _t115 = _t60 * (float) Math.cos(_t110);
        float _t120, _t121, _t122, _t123;
        if (_t106 > 0.0f) {
            _t120 = _t108 * _t100;
            _t121 = _t108 * _t97;
            _t122 = _t108 * _t99;
            _t123 = _t108 * _t98;
        } else {
            _t120 = _t47;
            _t121 = _t45;
            _t122 = _t50;
            _t123 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(t, otherZ - _selfz, _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t10 = Math.fma(_otherw, _otherw, Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t11 = (1.0f / (float) Math.sqrt(_t9));
        float _t14 = (1.0f / (float) Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        float _t28 = t * (float) Math.sqrt(_t10) + (1.0f - t) * (float) Math.sqrt(_t9);
        float _t31 = Math.fma(_otherw * _t14, _t19, Math.fma(_otherz * _t14, _t21, Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        float _t40 = Math.fma(_otherw, _t14, -(_t31 * _t19));
        float _t41 = Math.fma(_otherz, _t14, -(_t31 * _t21));
        float _t42 = Math.fma(_otherx, _t14, -(_t31 * _t16));
        float _t43 = Math.fma(_othery, _t14, -(_t31 * _t24));
        float _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = Math.fma(_t48, _t19, _t40);
        float _t50 = Math.fma(_t48, _t21, _t41);
        float _t51 = Math.fma(_t48, _t16, _t42);
        float _t52 = Math.fma(_t48, _t24, _t43);
        float _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t61 = t * (float) Math.atan2((float) Math.sqrt(_t57), _t31);
        float _sp0 = _t28 * (float) Math.sin(_t61) * (1.0f / (float) Math.sqrt(_t57));
        float _t66 = _t28 * (float) Math.cos(_t61);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t16, _t66, _sp0 * _t51));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t24, _t66, _sp0 * _t52));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t21, _t66, _sp0 * _t50));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t);
        Float4OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L, t);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t7 = unitScale(_otherz, _otherw, Math.max(Math.abs(_otherx), Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(Math.abs(_selfx), Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) Math.sqrt(_t36));
        float _t41 = (1.0f / (float) Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = Math.fma(_t80, _t43, _t72);
        float _t82 = Math.fma(_t80, _t45, _t73);
        float _t83 = Math.fma(_t80, _t47, _t74);
        float _t84 = Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(Math.abs(_t83), Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) Math.sqrt(_t106));
        float _t110 = t * (float) Math.atan2((float) Math.sqrt(_t106), _t63 * _t90);
        float _t114 = _t60 * (float) Math.sin(_t110);
        float _t115 = _t60 * (float) Math.cos(_t110);
        float _t120, _t121, _t122, _t123;
        if (_t106 > 0.0f) {
            _t120 = _t108 * _t100;
            _t121 = _t108 * _t97;
            _t122 = _t108 * _t99;
            _t123 = _t108 * _t98;
        } else {
            _t120 = _t47;
            _t121 = _t45;
            _t122 = _t50;
            _t123 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(t, _othery - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(t, _otherz - _selfz, _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    public static long absolute_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.abs(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.abs(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.abs(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.abs(_selfw));
        return dest;
    }

    public static long acos_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.acos(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.acos(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.acos(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.acos(_selfw));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, float bX, float bY, float bZ, float bW, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(scalar, bX, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(scalar, bY, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(scalar, bZ, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(scalar, bW, _selfw));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long b, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _bx = UnsafeOpsHolder.U.getFloat(b + 0L);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _bw = UnsafeOpsHolder.U.getFloat(b + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(scalar, _bx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(scalar, _by, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(scalar, _bz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(scalar, _bw, _selfw));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(bX, cX, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(bY, cY, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(bZ, cZ, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(bW, cW, _selfw));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long b, long c) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _bx = UnsafeOpsHolder.U.getFloat(b + 0L);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _bw = UnsafeOpsHolder.U.getFloat(b + 12L);
        float _cx = UnsafeOpsHolder.U.getFloat(c + 0L);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        float _cw = UnsafeOpsHolder.U.getFloat(c + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_bx, _cx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_by, _cy, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_bz, _cz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    public static float angleBetween_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t12 = Math.fma(otherW, _selfz, -(otherZ * _selfw));
        float _t13 = Math.fma(otherW, _selfy, -(otherY * _selfw));
        float _t14 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t15 = Math.fma(otherW, _selfx, -(otherX * _selfw));
        float _t16 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _t17 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        float _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY, otherZ, otherW);
        return (float) Math.atan2((float) Math.sqrt(_ct0), Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    public static float angleBetween_degenerate(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(src, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.angleBetween_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = unitScale(otherZ, otherW, Math.max(Math.abs(otherX), Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(Math.abs(_selfx), Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t36 = Math.fma(_t16, _t17, -(_t18 * _t19));
        float _t37 = Math.fma(_t20, _t21, -(_t22 * _t23));
        float _t38 = Math.fma(_t18, _t21, -(_t22 * _t17));
        float _t39 = Math.fma(_t16, _t21, -(_t22 * _t19));
        float _t40 = Math.fma(_t18, _t23, -(_t20 * _t17));
        float _t41 = Math.fma(_t16, _t23, -(_t20 * _t19));
        float _t51 = unitScale(Math.max(Math.abs(_t37), Math.abs(_t38)), Math.max(Math.abs(_t39), Math.abs(_t40)), Math.max(Math.abs(_t41), Math.abs(_t36)));
        float _t58 = _t36 * _t51;
        float _t59 = _t41 * _t51;
        float _t60 = _t40 * _t51;
        float _t61 = _t39 * _t51;
        float _t62 = _t37 * _t51;
        float _t63 = _t38 * _t51;
        return (float) Math.atan2((float) Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static float angleBetween_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t12 = Math.fma(_otherw, _selfz, -(_otherz * _selfw));
        float _t13 = Math.fma(_otherw, _selfy, -(_othery * _selfw));
        float _t14 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t15 = Math.fma(_otherw, _selfx, -(_otherx * _selfw));
        float _t16 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _t17 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        float _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.angleBetween_degenerate(src, other);
        return (float) Math.atan2((float) Math.sqrt(_ct0), Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    public static float angleBetween_degenerate(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(src, other);
        return Float4OpsKernelsSegment.angleBetween_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
    }

    public static float angleBetween_degenerate_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t6 = unitScale(_otherz, _otherw, Math.max(Math.abs(_otherx), Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(Math.abs(_selfx), Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        float _t36 = Math.fma(_t16, _t17, -(_t18 * _t19));
        float _t37 = Math.fma(_t20, _t21, -(_t22 * _t23));
        float _t38 = Math.fma(_t18, _t21, -(_t22 * _t17));
        float _t39 = Math.fma(_t16, _t21, -(_t22 * _t19));
        float _t40 = Math.fma(_t18, _t23, -(_t20 * _t17));
        float _t41 = Math.fma(_t16, _t23, -(_t20 * _t19));
        float _t51 = unitScale(Math.max(Math.abs(_t37), Math.abs(_t38)), Math.max(Math.abs(_t39), Math.abs(_t40)), Math.max(Math.abs(_t41), Math.abs(_t36)));
        float _t58 = _t36 * _t51;
        float _t59 = _t41 * _t51;
        float _t60 = _t40 * _t51;
        float _t61 = _t39 * _t51;
        float _t62 = _t37 * _t51;
        float _t63 = _t38 * _t51;
        return (float) Math.atan2((float) Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static long asin_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.asin(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.asin(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.asin(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.asin(_selfw));
        return dest;
    }

    public static long atan_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.atan(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.atan(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.atan(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.atan(_selfw));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, float x) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.atan2(_selfx, x));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.atan2(_selfy, x));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.atan2(_selfz, x));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.atan2(_selfw, x));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, float xX, float xY, float xZ, float xW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.atan2(_selfx, xX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.atan2(_selfy, xY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.atan2(_selfz, xZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.atan2(_selfw, xW));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, long x) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _xx = UnsafeOpsHolder.U.getFloat(x + 0L);
        float _xy = UnsafeOpsHolder.U.getFloat(x + 4L);
        float _xz = UnsafeOpsHolder.U.getFloat(x + 8L);
        float _xw = UnsafeOpsHolder.U.getFloat(x + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.atan2(_selfx, _xx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.atan2(_selfy, _xy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.atan2(_selfz, _xz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.atan2(_selfw, _xw));
        return dest;
    }

    public static long cbrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.cbrt(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.cbrt(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.cbrt(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.cbrt(_selfw));
        return dest;
    }

    public static long ceil_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.ceil(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.ceil(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.ceil(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.ceil(_selfw));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, float min, float max) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(Math.max(_selfx, min), max));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, min), max));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(Math.max(_selfz, min), max));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(Math.max(_selfw, min), max));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(Math.max(_selfx, minX), maxX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, minY), maxY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(Math.max(_selfz, minZ), maxZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(Math.max(_selfw, minW), maxW));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, long min, long max) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _minx = UnsafeOpsHolder.U.getFloat(min + 0L);
        float _miny = UnsafeOpsHolder.U.getFloat(min + 4L);
        float _minz = UnsafeOpsHolder.U.getFloat(min + 8L);
        float _minw = UnsafeOpsHolder.U.getFloat(min + 12L);
        float _maxx = UnsafeOpsHolder.U.getFloat(max + 0L);
        float _maxy = UnsafeOpsHolder.U.getFloat(max + 4L);
        float _maxz = UnsafeOpsHolder.U.getFloat(max + 8L);
        float _maxw = UnsafeOpsHolder.U.getFloat(max + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(Math.max(_selfx, _minx), _maxx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, _miny), _maxy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(Math.max(_selfz, _minz), _maxz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static float compAdd_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        return _selfw + (_selfz + (_selfx + _selfy));
    }

    public static float compMax_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        return Math.max(Math.max(Math.max(_selfx, _selfy), _selfz), _selfw);
    }

    public static float compMin_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        return Math.min(Math.min(Math.min(_selfx, _selfy), _selfz), _selfw);
    }

    public static float compMul_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        return _selfw * _selfz * _selfx * _selfy;
    }

    public static long copySign_unsafe(long dest, long src, float sign) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.copySign(_selfx, sign));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, sign));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.copySign(_selfz, sign));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.copySign(_selfw, sign));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, float signX, float signY, float signZ, float signW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.copySign(_selfx, signX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, signY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.copySign(_selfz, signZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.copySign(_selfw, signW));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, long sign) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _signx = UnsafeOpsHolder.U.getFloat(sign + 0L);
        float _signy = UnsafeOpsHolder.U.getFloat(sign + 4L);
        float _signz = UnsafeOpsHolder.U.getFloat(sign + 8L);
        float _signw = UnsafeOpsHolder.U.getFloat(sign + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.copySign(_selfx, _signx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, _signy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.copySign(_selfz, _signz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.copySign(_selfw, _signw));
        return dest;
    }

    public static long cos_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.cos(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.cos(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.cos(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.cos(_selfw));
        return dest;
    }

    public static long cosh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.cosh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.cosh(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.cosh(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.cosh(_selfw));
        return dest;
    }

    public static long cross_unsafe(long dest, long src, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t12 = Math.fma(vY, wZ, -(vZ * wY));
        float _t13 = Math.fma(vZ, wW, -(vW * wZ));
        float _t14 = Math.fma(vY, wW, -(vW * wY));
        float _t15 = Math.fma(vX, wZ, -(vZ * wX));
        float _t16 = Math.fma(vX, wW, -(vW * wX));
        float _t17 = Math.fma(vX, wY, -(vY * wX));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static long cross_unsafe(long dest, long src, long v, long w) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _vx = UnsafeOpsHolder.U.getFloat(v + 0L);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _vw = UnsafeOpsHolder.U.getFloat(v + 12L);
        float _wx = UnsafeOpsHolder.U.getFloat(w + 0L);
        float _wy = UnsafeOpsHolder.U.getFloat(w + 4L);
        float _wz = UnsafeOpsHolder.U.getFloat(w + 8L);
        float _ww = UnsafeOpsHolder.U.getFloat(w + 12L);
        float _t12 = Math.fma(_vy, _wz, -(_vz * _wy));
        float _t13 = Math.fma(_vz, _ww, -(_vw * _wz));
        float _t14 = Math.fma(_vy, _ww, -(_vw * _wy));
        float _t15 = Math.fma(_vx, _wz, -(_vz * _wx));
        float _t16 = Math.fma(_vx, _ww, -(_vw * _wx));
        float _t17 = Math.fma(_vx, _wy, -(_vy * _wx));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static long degrees_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.toDegrees(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.toDegrees(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.toDegrees(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.toDegrees(_selfw));
        return dest;
    }

    public static float distance_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfw - otherW;
        float _t1 = _selfz - otherZ;
        float _t2 = _selfx - otherX;
        float _t3 = _selfy - otherY;
        return (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static float distance_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t0 = _selfw - _otherw;
        float _t1 = _selfz - _otherz;
        float _t2 = _selfx - _otherx;
        float _t3 = _selfy - _othery;
        return (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static float distanceSquared_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfw - otherW;
        float _t1 = _selfz - otherZ;
        float _t2 = _selfx - otherX;
        float _t3 = _selfy - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static float distanceSquared_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t0 = _selfw - _otherw;
        float _t1 = _selfz - _otherz;
        float _t2 = _selfx - _otherx;
        float _t3 = _selfy - _othery;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static float dot_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        return Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    public static float dot_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        return Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    public static long exp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.exp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.exp(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.exp(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.exp(_selfw));
        return dest;
    }

    public static long exp2_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.pow(2.0f, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.pow(2.0f, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.pow(2.0f, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.pow(2.0f, _selfw));
        return dest;
    }

    public static long expm1_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.expm1(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.expm1(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.expm1(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.expm1(_selfw));
        return dest;
    }

    public static long faceforward_unsafe(long dest, long src, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        if (Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, -_selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -_selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -_selfz);
            UnsafeOpsHolder.U.putFloat(dest + 12L, -_selfw);
        }
        return dest;
    }

    public static long faceforward_unsafe(long dest, long src, long I, long Nref) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _Ix = UnsafeOpsHolder.U.getFloat(I + 0L);
        float _Iy = UnsafeOpsHolder.U.getFloat(I + 4L);
        float _Iz = UnsafeOpsHolder.U.getFloat(I + 8L);
        float _Iw = UnsafeOpsHolder.U.getFloat(I + 12L);
        float _Nrefx = UnsafeOpsHolder.U.getFloat(Nref + 0L);
        float _Nrefy = UnsafeOpsHolder.U.getFloat(Nref + 4L);
        float _Nrefz = UnsafeOpsHolder.U.getFloat(Nref + 8L);
        float _Nrefw = UnsafeOpsHolder.U.getFloat(Nref + 12L);
        if (Math.fma(_Iw, _Nrefw, Math.fma(_Iz, _Nrefz, Math.fma(_Ix, _Nrefx, _Iy * _Nrefy))) < 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, -_selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -_selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -_selfz);
            UnsafeOpsHolder.U.putFloat(dest + 12L, -_selfw);
        }
        return dest;
    }

    public static long floor_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.floor(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.floor(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.floor(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.floor(_selfw));
        return dest;
    }

    public static long fract_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(_selfx - (float) Math.floor(_selfx), 0.99999994f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy - (float) Math.floor(_selfy), 0.99999994f));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(_selfz - (float) Math.floor(_selfz), 0.99999994f));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(_selfw - (float) Math.floor(_selfw), 0.99999994f));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, float y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.hypot(_selfx, y));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.hypot(_selfy, y));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.hypot(_selfz, y));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.hypot(_selfw, y));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, float yX, float yY, float yZ, float yW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.hypot(_selfx, yX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.hypot(_selfy, yY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.hypot(_selfz, yZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.hypot(_selfw, yW));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, long y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _yx = UnsafeOpsHolder.U.getFloat(y + 0L);
        float _yy = UnsafeOpsHolder.U.getFloat(y + 4L);
        float _yz = UnsafeOpsHolder.U.getFloat(y + 8L);
        float _yw = UnsafeOpsHolder.U.getFloat(y + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.hypot(_selfx, _yx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.hypot(_selfy, _yy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.hypot(_selfz, _yz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.hypot(_selfw, _yw));
        return dest;
    }

    public static long inverse_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, 1.0f / _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f / _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 1.0f / _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f / _selfw);
        return dest;
    }

    public static long inverseSqrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (1.0f / (float) Math.sqrt(_selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (1.0f / (float) Math.sqrt(_selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (1.0f / (float) Math.sqrt(_selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (1.0f / (float) Math.sqrt(_selfw)));
        return dest;
    }

    public static float length_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        return (float) Math.sqrt(Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))));
    }

    public static float lengthSquared_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        return Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static long log_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.log(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.log(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.log(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.log(_selfw));
        return dest;
    }

    public static long log10_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.log10(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.log10(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.log10(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.log10(_selfw));
        return dest;
    }

    public static long log1p_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.log1p(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.log1p(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.log1p(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.log1p(_selfw));
        return dest;
    }

    public static long log2_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.log2(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.log2(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.log2(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.log2(_selfw));
        return dest;
    }

    public static float manhattanDistance_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        return Math.abs(_selfx - otherX) + Math.abs(_selfy - otherY) + Math.abs(_selfz - otherZ) + Math.abs(_selfw - otherW);
    }

    public static float manhattanDistance_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        return Math.abs(_selfx - _otherx) + Math.abs(_selfy - _othery) + Math.abs(_selfz - _otherz) + Math.abs(_selfw - _otherw);
    }

    public static float manhattanLength_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        return Math.abs(_selfx) + Math.abs(_selfy) + Math.abs(_selfz) + Math.abs(_selfw);
    }

    public static long max_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.max(_selfx, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.max(_selfz, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.max(_selfw, scalar));
        return dest;
    }

    public static long max_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.max(_selfx, otherX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, otherY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.max(_selfz, otherZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.max(_selfw, otherW));
        return dest;
    }

    public static long max_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.max(_selfx, _otherx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, _othery));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.max(_selfz, _otherz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.max(_selfw, _otherw));
        return dest;
    }

    public static long min_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(_selfx, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(_selfz, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(_selfw, scalar));
        return dest;
    }

    public static long min_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(_selfx, otherX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, otherY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(_selfz, otherZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(_selfw, otherW));
        return dest;
    }

    public static long min_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.min(_selfx, _otherx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, _othery));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(_selfz, _otherz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(_selfw, _otherw));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, float y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, flooredMod(_selfx, y));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, y));
        UnsafeOpsHolder.U.putFloat(dest + 8L, flooredMod(_selfz, y));
        UnsafeOpsHolder.U.putFloat(dest + 12L, flooredMod(_selfw, y));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, float yX, float yY, float yZ, float yW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, flooredMod(_selfx, yX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, yY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, flooredMod(_selfz, yZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, flooredMod(_selfw, yW));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, long y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _yx = UnsafeOpsHolder.U.getFloat(y + 0L);
        float _yy = UnsafeOpsHolder.U.getFloat(y + 4L);
        float _yz = UnsafeOpsHolder.U.getFloat(y + 8L);
        float _yw = UnsafeOpsHolder.U.getFloat(y + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, flooredMod(_selfx, _yx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, _yy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, flooredMod(_selfz, _yz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, flooredMod(_selfw, _yw));
        return dest;
    }

    public static long nextDown_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.nextDown(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.nextDown(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.nextDown(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.nextDown(_selfw));
        return dest;
    }

    public static long nextUp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.nextUp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.nextUp(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.nextUp(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.nextUp(_selfw));
        return dest;
    }

    public static long normalize_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t4 = (1.0f / (float) Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t4);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long normalizeMul_unsafe(long dest, long src, float length) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t5 = length * (1.0f / (float) Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t5);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long outerProduct_unsafe(long dest, long src, float rowX, float rowY, float rowZ, float rowW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, rowX * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, rowX * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, rowX * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, rowX * _selfw);
        UnsafeOpsHolder.U.putFloat(dest + 16L, rowY * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 20L, rowY * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 24L, rowY * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 28L, rowY * _selfw);
        UnsafeOpsHolder.U.putFloat(dest + 32L, rowZ * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 36L, rowZ * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 40L, rowZ * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 44L, rowZ * _selfw);
        UnsafeOpsHolder.U.putFloat(dest + 48L, rowW * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 52L, rowW * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 56L, rowW * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 60L, rowW * _selfw);
        return dest;
    }

    public static long outerProduct_unsafe(long dest, long src, long row) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _rowx = UnsafeOpsHolder.U.getFloat(row + 0L);
        float _rowy = UnsafeOpsHolder.U.getFloat(row + 4L);
        float _rowz = UnsafeOpsHolder.U.getFloat(row + 8L);
        float _roww = UnsafeOpsHolder.U.getFloat(row + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _rowx * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _rowx * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _rowx * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _rowx * _selfw);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _rowy * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _rowy * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _rowy * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _rowy * _selfw);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _rowz * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 36L, _rowz * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 40L, _rowz * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 44L, _rowz * _selfw);
        UnsafeOpsHolder.U.putFloat(dest + 48L, _roww * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 52L, _roww * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 56L, _roww * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 60L, _roww * _selfw);
        return dest;
    }

    public static long pow_unsafe(long dest, long src, float exponent) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.pow(_selfx, exponent));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.pow(_selfy, exponent));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.pow(_selfz, exponent));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.pow(_selfw, exponent));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, float exponentX, float exponentY, float exponentZ, float exponentW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.pow(_selfx, exponentX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.pow(_selfy, exponentY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.pow(_selfz, exponentZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.pow(_selfw, exponentW));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, long exponent) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _exponentx = UnsafeOpsHolder.U.getFloat(exponent + 0L);
        float _exponenty = UnsafeOpsHolder.U.getFloat(exponent + 4L);
        float _exponentz = UnsafeOpsHolder.U.getFloat(exponent + 8L);
        float _exponentw = UnsafeOpsHolder.U.getFloat(exponent + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.pow(_selfx, _exponentx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.pow(_selfy, _exponenty));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.pow(_selfz, _exponentz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.pow(_selfw, _exponentw));
        return dest;
    }

    public static long project_unsafe(long dest, long src, float ontoX, float ontoY, float ontoZ, float ontoW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = Math.fma(ontoW, _selfw, Math.fma(ontoZ, _selfz, Math.fma(ontoX, _selfx, ontoY * _selfy))) / Math.fma(ontoW, ontoW, Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY)));
        UnsafeOpsHolder.U.putFloat(dest + 0L, ontoX * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ontoY * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ontoZ * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ontoW * _t9);
        return dest;
    }

    public static long project_unsafe(long dest, long src, long onto) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _ontox = UnsafeOpsHolder.U.getFloat(onto + 0L);
        float _ontoy = UnsafeOpsHolder.U.getFloat(onto + 4L);
        float _ontoz = UnsafeOpsHolder.U.getFloat(onto + 8L);
        float _ontow = UnsafeOpsHolder.U.getFloat(onto + 12L);
        float _t9 = Math.fma(_ontow, _selfw, Math.fma(_ontoz, _selfz, Math.fma(_ontox, _selfx, _ontoy * _selfy))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        UnsafeOpsHolder.U.putFloat(dest + 0L, _ontox * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _ontoy * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _ontoz * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _ontow * _t9);
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, float normalX, float normalY, float normalZ, float normalW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-normalX, _t3, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-normalY, _t3, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-normalZ, _t3, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-normalW, _t3, _selfw));
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, long normal) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal + 0L);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _normalw = UnsafeOpsHolder.U.getFloat(normal + 12L);
        float _t3 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-_normalx, _t3, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-_normaly, _t3, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-_normalz, _t3, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    public static long radians_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.toRadians(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.toRadians(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.toRadians(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.toRadians(_selfw));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, float normalX, float normalY, float normalZ, float normalW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = 2.0f * Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-normalX, _t4, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-normalY, _t4, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-normalZ, _t4, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-normalW, _t4, _selfw));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, long normal) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal + 0L);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _normalw = UnsafeOpsHolder.U.getFloat(normal + 12L);
        float _t4 = 2.0f * Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-_normalx, _t4, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-_normaly, _t4, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-_normalz, _t4, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    public static long refract_unsafe(long dest, long src, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(eta, _selfx, -(normalX * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(eta, _selfy, -(normalY * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(eta, _selfz, -(normalZ * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long refract_unsafe(long dest, long src, long normal, float eta) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal + 0L);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _normalw = UnsafeOpsHolder.U.getFloat(normal + 12L);
        float _t4 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(eta, _selfx, -(_normalx * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(eta, _selfy, -(_normaly * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(eta, _selfz, -(_normalz * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 0L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long round_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.rint(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.rint(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.rint(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.rint(_selfw));
        return dest;
    }

    public static long sign_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.signum(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.signum(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.signum(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.signum(_selfw));
        return dest;
    }

    public static long sin_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.sin(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.sin(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.sin(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.sin(_selfw));
        return dest;
    }

    public static long sinh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.sinh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.sinh(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.sinh(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.sinh(_selfw));
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, float edge0, float edge1) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = Math.max(0.0f, Math.min(1.0f, (_selfx - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (_selfy - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (_selfz - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (_selfw - edge0) * _t0_inv));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t16 = Math.max(0.0f, Math.min(1.0f, (_selfx - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (_selfy - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (_selfz - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (_selfw - edge0W) / (edge1W - edge0W)));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, long edge0, long edge1) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _edge0x = UnsafeOpsHolder.U.getFloat(edge0 + 0L);
        float _edge0y = UnsafeOpsHolder.U.getFloat(edge0 + 4L);
        float _edge0z = UnsafeOpsHolder.U.getFloat(edge0 + 8L);
        float _edge0w = UnsafeOpsHolder.U.getFloat(edge0 + 12L);
        float _edge1x = UnsafeOpsHolder.U.getFloat(edge1 + 0L);
        float _edge1y = UnsafeOpsHolder.U.getFloat(edge1 + 4L);
        float _edge1z = UnsafeOpsHolder.U.getFloat(edge1 + 8L);
        float _edge1w = UnsafeOpsHolder.U.getFloat(edge1 + 12L);
        float _t16 = Math.max(0.0f, Math.min(1.0f, (_selfx - _edge0x) / (_edge1x - _edge0x)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (_selfy - _edge0y) / (_edge1y - _edge0y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (_selfz - _edge0z) / (_edge1z - _edge0z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (_selfw - _edge0w) / (_edge1w - _edge0w)));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    public static long sqrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.sqrt(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.sqrt(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.sqrt(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.sqrt(_selfw));
        return dest;
    }

    public static long step_unsafe(long dest, long src, float edge) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx < edge ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < edge ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz < edge ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static long step_unsafe(long dest, long src, float edgeX, float edgeY, float edgeZ, float edgeW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx < edgeX ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < edgeY ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz < edgeZ ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw < edgeW ? 0.0f : 1.0f);
        return dest;
    }

    public static long step_unsafe(long dest, long src, long edge) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _edgex = UnsafeOpsHolder.U.getFloat(edge + 0L);
        float _edgey = UnsafeOpsHolder.U.getFloat(edge + 4L);
        float _edgez = UnsafeOpsHolder.U.getFloat(edge + 8L);
        float _edgew = UnsafeOpsHolder.U.getFloat(edge + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx < _edgex ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < _edgey ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz < _edgez ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw < _edgew ? 0.0f : 1.0f);
        return dest;
    }

    public static long tan_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.tan(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.tan(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.tan(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.tan(_selfw));
        return dest;
    }

    public static long tanh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, (float) Math.tanh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) Math.tanh(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) Math.tanh(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) Math.tanh(_selfw));
        return dest;
    }

    public static long trunc_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx >= 0.0f ? (float) Math.floor(_selfx) : (float) Math.ceil(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy >= 0.0f ? (float) Math.floor(_selfy) : (float) Math.ceil(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz >= 0.0f ? (float) Math.floor(_selfz) : (float) Math.ceil(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw >= 0.0f ? (float) Math.floor(_selfw) : (float) Math.ceil(_selfw));
        return dest;
    }

    public static long ulp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.ulp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.ulp(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.ulp(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.ulp(_selfw));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat + 0L);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat30 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat31 = UnsafeOpsHolder.U.getFloat(mat + 28L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 36L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 40L);
        float _mat32 = UnsafeOpsHolder.U.getFloat(mat + 44L);
        float _mat03 = UnsafeOpsHolder.U.getFloat(mat + 48L);
        float _mat13 = UnsafeOpsHolder.U.getFloat(mat + 52L);
        float _mat23 = UnsafeOpsHolder.U.getFloat(mat + 56L);
        float _mat33 = UnsafeOpsHolder.U.getFloat(mat + 60L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_mat03, _selfw, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_mat13, _selfw, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_mat23, _selfw, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_mat33, _selfw, Math.fma(_mat32, _selfz, Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, long quat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _quatx = UnsafeOpsHolder.U.getFloat(quat + 0L);
        float _quaty = UnsafeOpsHolder.U.getFloat(quat + 4L);
        float _quatz = UnsafeOpsHolder.U.getFloat(quat + 8L);
        float _quatw = UnsafeOpsHolder.U.getFloat(quat + 12L);
        float _t9 = 2.0f * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        float _t10 = 2.0f * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        float _t11 = 2.0f * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis + 0L);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotateInverse_unsafe(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotateInverse_unsafe(long dest, long src, long quat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _quatx = UnsafeOpsHolder.U.getFloat(quat + 0L);
        float _quaty = UnsafeOpsHolder.U.getFloat(quat + 4L);
        float _quatz = UnsafeOpsHolder.U.getFloat(quat + 8L);
        float _quatw = UnsafeOpsHolder.U.getFloat(quat + 12L);
        float _t9 = 2.0f * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        float _t10 = 2.0f * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        float _t11 = 2.0f * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t1, -(_selfz * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfy, _t0, _selfz * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _t1, _selfz * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, _t1, -(_selfx * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotateZ_unsafe(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfx, _t0, _selfy * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
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
