// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double4Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double4Ops} and its sibling kernel units. Not public API.
 */
public final class Double4OpsKernelsAddress {
    private Double4OpsKernelsAddress() {}

    public static long add_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, otherX + _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, otherY + _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, otherZ + _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, otherW + _selfw);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _otherx + _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _othery + _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _otherz + _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _otherw + _selfw);
        return dest;
    }

    public static long div_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx / scalar);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy / scalar);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz / scalar);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw / scalar);
        return dest;
    }

    public static long div_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx / otherX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy / otherY);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz / otherZ);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw / otherW);
        return dest;
    }

    public static long div_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx / _otherx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy / _othery);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz / _otherz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw / _otherw);
        return dest;
    }

    public static long fma_unsafe(long dest, long src, double b, double cX, double cY, double cZ, double cW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_selfx, b, cX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, b, cY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_selfz, b, cZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_selfw, b, cW));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, long c, double b) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _cx = UnsafeOpsHolder.U.getDouble(c);
        double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
        double _cz = UnsafeOpsHolder.U.getDouble(c + 16L);
        double _cw = UnsafeOpsHolder.U.getDouble(c + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_selfx, b, _cx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, b, _cy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_selfz, b, _cz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_selfw, b, _cw));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_selfx, bX, cX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, bY, cY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_selfz, bZ, cZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_selfw, bW, cW));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, long b, long c) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _bx = UnsafeOpsHolder.U.getDouble(b);
        double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
        double _bz = UnsafeOpsHolder.U.getDouble(b + 16L);
        double _bw = UnsafeOpsHolder.U.getDouble(b + 24L);
        double _cx = UnsafeOpsHolder.U.getDouble(c);
        double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
        double _cz = UnsafeOpsHolder.U.getDouble(c + 16L);
        double _cw = UnsafeOpsHolder.U.getDouble(c + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_selfx, _bx, _cx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, _by, _cy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_selfz, _bz, _cz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, scalar * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, scalar * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, scalar * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, scalar * _selfw);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, otherX * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, otherY * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, otherZ * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, otherW * _selfw);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _otherx * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _othery * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _otherz * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _otherw * _selfw);
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, -_selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_selfw);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx - otherX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy - otherY);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz - otherZ);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw - otherW);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx - _otherx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy - _othery);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz - _otherz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw - _otherw);
        return dest;
    }

    public static long makeUniformDirection_unsafe(long dest, double u, double v, double w) {
        double _t0 = java.lang.Math.sqrt(u);
        double _t1 = v * 6.283185307179586;
        double _t3 = w * 6.283185307179586;
        double _t4 = Math.sin(_t1);
        double _t5 = java.lang.Math.sqrt(1.0 - u);
        double _t6 = Math.sin(_t3);
        UnsafeOpsHolder.U.putDouble(dest, Math.cosFromSin(_t4, _t1) * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t4 * _t5);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.cosFromSin(_t6, _t3) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t6 * _t0);
        return dest;
    }

    public static long set_unsafe(long dest, double vX, double vY, double vZ, double vW) {
        UnsafeOpsHolder.U.putDouble(dest, vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, vY);
        UnsafeOpsHolder.U.putDouble(dest + 16L, vZ);
        UnsafeOpsHolder.U.putDouble(dest + 24L, vW);
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        double _vw = UnsafeOpsHolder.U.getDouble(v + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _vy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _vz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _vw);
        return dest;
    }

    public static long set_unsafe(long dest, double s) {
        UnsafeOpsHolder.U.putDouble(dest, s);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s);
        return dest;
    }

    public static long makeZero_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(p1X, _t7, _selfx * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(p1Z, _t7, _selfz * _t8) + Math.fma(p2Z, _t6, p3Z * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(p1W, _t7, _selfw * _t8) + Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, long p1, long p2, long p3, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p1w = UnsafeOpsHolder.U.getDouble(p1 + 24L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p2w = UnsafeOpsHolder.U.getDouble(p2 + 24L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _p3z = UnsafeOpsHolder.U.getDouble(p3 + 16L);
        double _p3w = UnsafeOpsHolder.U.getDouble(p3 + 24L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t3 = _t0 * _t0;
        return bezier_unsafe_s98b6ddda_1(dest, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, t * _t1, 3.0 * _t0 * _t1, 3.0 * t * _t3, _t0 * _t3);
    }

    /** Piece 2 of {@code bezier_unsafe}, split to fit the inline budget; reached only through it. */
    private static long bezier_unsafe_s98b6ddda_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w, double _t2, double _t6, double _t7, double _t8) {
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_p1x, _t7, _selfx * _t8) + Math.fma(_p2x, _t6, _p3x * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_p1w, _t7, _selfw * _t8) + Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(p2W, _t0, Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, long p1, long p2, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p1w = UnsafeOpsHolder.U.getDouble(p1 + 24L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p2w = UnsafeOpsHolder.U.getDouble(p2 + 24L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_p2w, _t0, Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, long p1, long p2, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p1w = UnsafeOpsHolder.U.getDouble(p1 + 24L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p2w = UnsafeOpsHolder.U.getDouble(p2 + 24L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(p3W - p2W, _t2, Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, long p1, long p2, long p3, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p1w = UnsafeOpsHolder.U.getDouble(p1 + 24L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p2w = UnsafeOpsHolder.U.getDouble(p2 + 24L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _p3z = UnsafeOpsHolder.U.getDouble(p3 + 16L);
        double _p3w = UnsafeOpsHolder.U.getDouble(p3 + 24L);
        double _t1 = 1.0 - t;
        return bezierTangent_unsafe_sc50bd54f_1(dest, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, 3.0 * t * t, 6.0 * t * _t1, 3.0 * _t1 * _t1);
    }

    /** Piece 2 of {@code bezierTangent_unsafe}, split to fit the inline budget; reached only through it. */
    private static long bezierTangent_unsafe_sc50bd54f_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w, double _t2, double _t5, double _t6) {
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_p3w - _p2w, _t2, Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * (Math.fma(2.0, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_unsafe_se47aeadb_1(dest, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_unsafe}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_se47aeadb_1(long dest, double p1Z, double p1W, double p2Z, double p2W, double p3Z, double p3W, double t, double _selfz, double _selfw, double _t0, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * (Math.fma(2.0, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0, p1Z, Math.fma(2.0, _selfz, Math.fma(4.0, p2Z, -p3Z))), _t0, Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _selfz)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * (Math.fma(2.0, p1W, t * (p2W - _selfw)) + Math.fma(Math.fma(-5.0, p1W, Math.fma(2.0, _selfw, Math.fma(4.0, p2W, -p3W))), _t0, Math.fma(-3.0, p2W, Math.fma(3.0, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, long p1, long p2, long p3, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p1w = UnsafeOpsHolder.U.getDouble(p1 + 24L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p2w = UnsafeOpsHolder.U.getDouble(p2 + 24L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _p3z = UnsafeOpsHolder.U.getDouble(p3 + 16L);
        double _p3w = UnsafeOpsHolder.U.getDouble(p3 + 24L);
        double _t0 = t * t;
        return catmullRom_unsafe_s180b5851_1(dest, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, _t0, t * _t0);
    }

    /** Piece 2 of {@code catmullRom_unsafe}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_s180b5851_1(long dest, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w, double _t0, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * (Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), _t0, Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * (Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), _t0, Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * (Math.fma(2.0, _p1z, t * (_p2z - _selfz)) + Math.fma(Math.fma(-5.0, _p1z, Math.fma(2.0, _selfz, Math.fma(4.0, _p2z, -_p3z))), _t0, Math.fma(-3.0, _p2z, Math.fma(3.0, _p1z, _p3z - _selfz)) * _t1)));
        return catmullRom_unsafe_s180b5851_2(dest, t, _selfw, _p1w, _p2w, _p3w, _t0, _t1);
    }

    /** Piece 3 of {@code catmullRom_unsafe}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_s180b5851_2(long dest, double t, double _selfw, double _p1w, double _p2w, double _p3w, double _t0, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * (Math.fma(2.0, _p1w, t * (_p2w - _selfw)) + Math.fma(Math.fma(-5.0, _p1w, Math.fma(2.0, _selfw, Math.fma(4.0, _p2w, -_p3w))), _t0, Math.fma(-3.0, _p2w, Math.fma(3.0, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return catmullRomTangent_unsafe_sadf55e6e_1(dest, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_sadf55e6e_1(long dest, double p1Z, double p1W, double p2Z, double p2W, double p3Z, double p3W, double t, double _selfz, double _selfw, double _t0) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Z, Math.fma(2.0, _selfz, Math.fma(4.0, p2Z, -p3Z))), Math.fma(3.0 * Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1W, Math.fma(2.0, _selfw, Math.fma(4.0, p2W, -p3W))), Math.fma(3.0 * Math.fma(-3.0, p2W, Math.fma(3.0, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, long p1, long p2, long p3, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p1w = UnsafeOpsHolder.U.getDouble(p1 + 24L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p2w = UnsafeOpsHolder.U.getDouble(p2 + 24L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _p3z = UnsafeOpsHolder.U.getDouble(p3 + 16L);
        double _p3w = UnsafeOpsHolder.U.getDouble(p3 + 24L);
        return catmullRomTangent_unsafe_sb6145d56_1(dest, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, t * t);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_sb6145d56_1(long dest, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w, double _t0) {
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), Math.fma(3.0 * Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), Math.fma(3.0 * Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1z, Math.fma(2.0, _selfz, Math.fma(4.0, _p2z, -_p3z))), Math.fma(3.0 * Math.fma(-3.0, _p2z, Math.fma(3.0, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return catmullRomTangent_unsafe_sb6145d56_2(dest, t, _selfw, _p1w, _p2w, _p3w, _t0);
    }

    /** Piece 3 of {@code catmullRomTangent_unsafe}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_sb6145d56_2(long dest, double t, double _selfw, double _p1w, double _p2w, double _p3w, double _t0) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1w, Math.fma(2.0, _selfw, Math.fma(4.0, _p2w, -_p3w))), Math.fma(3.0 * Math.fma(-3.0, _p2w, Math.fma(3.0, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_selfw, _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, long t0, long v1, long t1, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0x = UnsafeOpsHolder.U.getDouble(t0);
        double _t0y = UnsafeOpsHolder.U.getDouble(t0 + 8L);
        double _t0z = UnsafeOpsHolder.U.getDouble(t0 + 16L);
        double _t0w = UnsafeOpsHolder.U.getDouble(t0 + 24L);
        double _v1x = UnsafeOpsHolder.U.getDouble(v1);
        double _v1y = UnsafeOpsHolder.U.getDouble(v1 + 8L);
        double _v1z = UnsafeOpsHolder.U.getDouble(v1 + 16L);
        double _v1w = UnsafeOpsHolder.U.getDouble(v1 + 24L);
        double _t1x = UnsafeOpsHolder.U.getDouble(t1);
        double _t1y = UnsafeOpsHolder.U.getDouble(t1 + 8L);
        double _t1z = UnsafeOpsHolder.U.getDouble(t1 + 16L);
        double _t1w = UnsafeOpsHolder.U.getDouble(t1 + 24L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        return hermite_unsafe_s61a0031d_1(dest, _selfx, _selfy, _selfz, _selfw, _t0x, _t0y, _t0z, _t0w, _v1x, _v1y, _v1z, _v1w, _t1x, _t1y, _t1z, _t1w, _t0, _t2, t * Math.fma(t, t, -t), Math.fma(t - 2.0, _t0, t), Math.fma(3.0, _t0, -(_t2 + _t2)));
    }

    /** Piece 2 of {@code hermite_unsafe}, split to fit the inline budget; reached only through it. */
    private static long hermite_unsafe_s61a0031d_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t0x, double _t0y, double _t0z, double _t0w, double _v1x, double _v1y, double _v1z, double _v1w, double _t1x, double _t1y, double _t1z, double _t1w, double _t0, double _t2, double _t5, double _t7, double _t9) {
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_selfw, _t10, _t0w * _t7) + Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_selfw, _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, long t0, long v1, long t1, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0x = UnsafeOpsHolder.U.getDouble(t0);
        double _t0y = UnsafeOpsHolder.U.getDouble(t0 + 8L);
        double _t0z = UnsafeOpsHolder.U.getDouble(t0 + 16L);
        double _t0w = UnsafeOpsHolder.U.getDouble(t0 + 24L);
        double _v1x = UnsafeOpsHolder.U.getDouble(v1);
        double _v1y = UnsafeOpsHolder.U.getDouble(v1 + 8L);
        double _v1z = UnsafeOpsHolder.U.getDouble(v1 + 16L);
        double _v1w = UnsafeOpsHolder.U.getDouble(v1 + 24L);
        double _t1x = UnsafeOpsHolder.U.getDouble(t1);
        double _t1y = UnsafeOpsHolder.U.getDouble(t1 + 8L);
        double _t1z = UnsafeOpsHolder.U.getDouble(t1 + 16L);
        double _t1w = UnsafeOpsHolder.U.getDouble(t1 + 24L);
        double _t0 = t * t;
        return hermiteTangent_unsafe_s5b6a94a_1(dest, _selfx, _selfy, _selfz, _selfw, _t0x, _t0y, _t0z, _t0w, _v1x, _v1y, _v1z, _v1w, _t1x, _t1y, _t1z, _t1w, 6.0 * Math.fma(t, t, -t), 6.0 * Math.fma(-t, t, t), Math.fma(3.0, _t0, -(t + t)), Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0)));
    }

    /** Piece 2 of {@code hermiteTangent_unsafe}, split to fit the inline budget; reached only through it. */
    private static long hermiteTangent_unsafe_s5b6a94a_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t0x, double _t0y, double _t0z, double _t0w, double _v1x, double _v1y, double _v1z, double _v1w, double _t1x, double _t1y, double _t1z, double _t1w, double _t6, double _t7, double _t8, double _t9) {
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_selfw, _t6, _t0w * _t9) + Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(t, otherX - _selfx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(t, otherY - _selfy, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(t, otherZ - _selfz, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(t, otherW - _selfw, _selfw));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(t, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(t, _othery - _selfy, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(t, _otherz - _selfz, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(tX, otherX - _selfx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(tY, otherY - _selfy, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(tZ, otherZ - _selfz, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(tW, otherW - _selfw, _selfw));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, long t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _tx = UnsafeOpsHolder.U.getDouble(t);
        double _ty = UnsafeOpsHolder.U.getDouble(t + 8L);
        double _tz = UnsafeOpsHolder.U.getDouble(t + 16L);
        double _tw = UnsafeOpsHolder.U.getDouble(t + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_tx, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_ty, _othery - _selfy, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_tz, _otherz - _selfz, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        double _t10 = Math.fma(otherW, otherW, Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t10));
        double _t16 = _selfx * _t11;
        double _t19 = _selfw * _t11;
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
        return slerp_unsafe_sd0587f75_1(dest, src, otherX, otherY, otherZ, otherW, t, _t14, _t16, _t19, _t21, _t24, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9), Math.fma(otherW * _t14, _t19, Math.fma(otherZ * _t14, _t21, Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24))));
    }

    /** Piece 2 of {@code slerp_unsafe}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_sd0587f75_1(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28, double _t31) {
        double _t40 = Math.fma(otherW, _t14, -(_t31 * _t19));
        double _t41 = Math.fma(otherZ, _t14, -(_t31 * _t21));
        double _t42 = Math.fma(otherX, _t14, -(_t31 * _t16));
        double _t43 = Math.fma(otherY, _t14, -(_t31 * _t24));
        double _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = Math.fma(_t48, _t19, _t40);
        double _t50 = Math.fma(_t48, _t21, _t41);
        double _t51 = Math.fma(_t48, _t16, _t42);
        double _t52 = Math.fma(_t48, _t24, _t43);
        double _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _sp0 = _t28 * Math.sin(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        double _t66 = _t28 * Math.cos(_t61);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t16, _t66, _sp0 * _t51));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t24, _t66, _sp0 * _t52));
        return slerp_unsafe_sd0587f75_2(dest, _t19, _t21, _t49, _t50, _sp0, _t66);
    }

    /** Piece 3 of {@code slerp_unsafe}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_sd0587f75_2(long dest, double _t19, double _t21, double _t49, double _t50, double _sp0, double _t66) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t21, _t66, _sp0 * _t50));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double4OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, otherZ, otherW, t);
        Double4OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t7 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = java.lang.Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = Math.fma(_t80, _t43, _t72);
        double _t82 = Math.fma(_t80, _t45, _t73);
        double _t83 = Math.fma(_t80, _t47, _t74);
        double _t84 = Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t114 = _t60 * Math.sin(_t110);
        double _t115 = _t60 * Math.cos(_t110);
        double _t120, _t121, _t122, _t123;
        if (_t106 > 0.0) {
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 5.048709793414476E-29) {
                    UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(t, otherZ - _selfz, _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t10 = Math.fma(_otherw, _otherw, Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        return slerp_unsafe_sd12e401d_1(dest, src, other, t, _otherx, _othery, _otherz, _otherw, (1.0 / java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_unsafe}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_sd12e401d_1(long dest, long src, long other, double t, double _otherx, double _othery, double _otherz, double _otherw, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28) {
        double _t31 = Math.fma(_otherw * _t14, _t19, Math.fma(_otherz * _t14, _t21, Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        double _t40 = Math.fma(_otherw, _t14, -(_t31 * _t19));
        double _t41 = Math.fma(_otherz, _t14, -(_t31 * _t21));
        double _t42 = Math.fma(_otherx, _t14, -(_t31 * _t16));
        double _t43 = Math.fma(_othery, _t14, -(_t31 * _t24));
        double _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = Math.fma(_t48, _t19, _t40);
        double _t50 = Math.fma(_t48, _t21, _t41);
        double _t51 = Math.fma(_t48, _t16, _t42);
        double _t52 = Math.fma(_t48, _t24, _t43);
        double _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        return slerp_unsafe_sd12e401d_2(dest, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.sin(_t61) * (1.0 / java.lang.Math.sqrt(_t57)), _t28 * Math.cos(_t61));
    }

    /** Piece 3 of {@code slerp_unsafe}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_sd12e401d_2(long dest, double _t16, double _t19, double _t21, double _t24, double _t49, double _t50, double _t51, double _t52, double _sp0, double _t66) {
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t16, _t66, _sp0 * _t51));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t24, _t66, _sp0 * _t52));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t21, _t66, _sp0 * _t50));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double4OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t);
        Double4OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 32L), 0L, t);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t7 = unitScale(_otherz, _otherw, java.lang.Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = java.lang.Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = Math.fma(_t80, _t43, _t72);
        double _t82 = Math.fma(_t80, _t45, _t73);
        double _t83 = Math.fma(_t80, _t47, _t74);
        double _t84 = Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t114 = _t60 * Math.sin(_t110);
        double _t115 = _t60 * Math.cos(_t110);
        double _t120, _t121, _t122, _t123;
        if (_t106 > 0.0) {
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 5.048709793414476E-29) {
                    UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(t, _othery - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(t, _otherz - _selfz, _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    public static long absolute_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.abs(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.abs(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.abs(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.abs(_selfw));
        return dest;
    }

    public static long acos_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.acos(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.acos(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.acos(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.acos(_selfw));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, double bX, double bY, double bZ, double bW, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(scalar, bX, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(scalar, bY, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(scalar, bZ, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(scalar, bW, _selfw));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long b, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _bx = UnsafeOpsHolder.U.getDouble(b);
        double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
        double _bz = UnsafeOpsHolder.U.getDouble(b + 16L);
        double _bw = UnsafeOpsHolder.U.getDouble(b + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(scalar, _bx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(scalar, _by, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(scalar, _bz, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(scalar, _bw, _selfw));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(bX, cX, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(bY, cY, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(bZ, cZ, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(bW, cW, _selfw));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long b, long c) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _bx = UnsafeOpsHolder.U.getDouble(b);
        double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
        double _bz = UnsafeOpsHolder.U.getDouble(b + 16L);
        double _bw = UnsafeOpsHolder.U.getDouble(b + 24L);
        double _cx = UnsafeOpsHolder.U.getDouble(c);
        double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
        double _cz = UnsafeOpsHolder.U.getDouble(c + 16L);
        double _cw = UnsafeOpsHolder.U.getDouble(c + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_bx, _cx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_by, _cy, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_bz, _cz, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    public static double angleBetween_unsafe(long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t12 = Math.fma(otherW, _selfz, -(otherZ * _selfw));
        double _t13 = Math.fma(otherW, _selfy, -(otherY * _selfw));
        double _t14 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        double _t15 = Math.fma(otherW, _selfx, -(otherX * _selfw));
        double _t16 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        double _t17 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        double _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY, otherZ, otherW);
        return Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    public static double angleBetween_degenerate(long src, double otherX, double otherY, double otherZ, double otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double4OpsKernelsAddress.angleBetween_degenerate_unsafe(src, otherX, otherY, otherZ, otherW);
        return Double4OpsKernelsSegment.angleBetween_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_degenerate_unsafe(long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t6 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t7 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = otherW * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = otherZ * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = otherY * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = otherX * _t6;
        double _t23 = _selfy * _t7;
        return angleBetween_degenerate_unsafe_s3bbd08ac_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, Math.fma(_t16, _t17, -(_t18 * _t19)), Math.fma(_t20, _t21, -(_t22 * _t23)), Math.fma(_t18, _t21, -(_t22 * _t17)), Math.fma(_t16, _t21, -(_t22 * _t19)), Math.fma(_t18, _t23, -(_t20 * _t17)), Math.fma(_t16, _t23, -(_t20 * _t19)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_unsafe_s3bbd08ac_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t36, double _t37, double _t38, double _t39, double _t40, double _t41) {
        double _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        double _t58 = _t36 * _t51;
        double _t59 = _t41 * _t51;
        double _t60 = _t40 * _t51;
        double _t61 = _t39 * _t51;
        double _t62 = _t37 * _t51;
        double _t63 = _t38 * _t51;
        return Math.atan2(java.lang.Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static double angleBetween_unsafe(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t12 = Math.fma(_otherw, _selfz, -(_otherz * _selfw));
        double _t13 = Math.fma(_otherw, _selfy, -(_othery * _selfw));
        double _t14 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        double _t15 = Math.fma(_otherw, _selfx, -(_otherx * _selfw));
        double _t16 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        double _t17 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        double _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.angleBetween_degenerate(src, other);
        return Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    public static double angleBetween_degenerate(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double4OpsKernelsAddress.angleBetween_degenerate_unsafe(src, other);
        return Double4OpsKernelsSegment.angleBetween_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 32L), 0L);
    }

    public static double angleBetween_degenerate_unsafe(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t6 = unitScale(_otherz, _otherw, java.lang.Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t7 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = _otherw * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = _otherz * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = _othery * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = _otherx * _t6;
        double _t23 = _selfy * _t7;
        return angleBetween_degenerate_unsafe_s575d8c1c_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, Math.fma(_t16, _t17, -(_t18 * _t19)), Math.fma(_t20, _t21, -(_t22 * _t23)), Math.fma(_t18, _t21, -(_t22 * _t17)), Math.fma(_t16, _t21, -(_t22 * _t19)), Math.fma(_t18, _t23, -(_t20 * _t17)), Math.fma(_t16, _t23, -(_t20 * _t19)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_unsafe_s575d8c1c_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t36, double _t37, double _t38, double _t39, double _t40, double _t41) {
        double _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        double _t58 = _t36 * _t51;
        double _t59 = _t41 * _t51;
        double _t60 = _t40 * _t51;
        double _t61 = _t39 * _t51;
        double _t62 = _t37 * _t51;
        double _t63 = _t38 * _t51;
        return Math.atan2(java.lang.Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static long asin_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.asin(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.asin(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.asin(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.asin(_selfw));
        return dest;
    }

    public static long atan_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.atan(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.atan(_selfw));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, double x) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_selfx, x));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_selfy, x));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_selfz, x));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.atan2(_selfw, x));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, double xX, double xY, double xZ, double xW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_selfx, xX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_selfy, xY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_selfz, xZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.atan2(_selfw, xW));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, long x) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _xx = UnsafeOpsHolder.U.getDouble(x);
        double _xy = UnsafeOpsHolder.U.getDouble(x + 8L);
        double _xz = UnsafeOpsHolder.U.getDouble(x + 16L);
        double _xw = UnsafeOpsHolder.U.getDouble(x + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_selfx, _xx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_selfy, _xy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_selfz, _xz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.atan2(_selfw, _xw));
        return dest;
    }

    public static long cbrt_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.cbrt(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.cbrt(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.cbrt(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cbrt(_selfw));
        return dest;
    }

    public static long ceil_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.ceil(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.ceil(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.ceil(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.ceil(_selfw));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, double min, double max) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.min(java.lang.Math.max(_selfx, min), max));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.min(java.lang.Math.max(_selfy, min), max));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.min(java.lang.Math.max(_selfz, min), max));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.min(java.lang.Math.max(_selfw, min), max));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, double minX, double minY, double minZ, double minW, double maxX, double maxY, double maxZ, double maxW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.min(java.lang.Math.max(_selfx, minX), maxX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.min(java.lang.Math.max(_selfy, minY), maxY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.min(java.lang.Math.max(_selfz, minZ), maxZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.min(java.lang.Math.max(_selfw, minW), maxW));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, long min, long max) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _minx = UnsafeOpsHolder.U.getDouble(min);
        double _miny = UnsafeOpsHolder.U.getDouble(min + 8L);
        double _minz = UnsafeOpsHolder.U.getDouble(min + 16L);
        double _minw = UnsafeOpsHolder.U.getDouble(min + 24L);
        double _maxx = UnsafeOpsHolder.U.getDouble(max);
        double _maxy = UnsafeOpsHolder.U.getDouble(max + 8L);
        double _maxz = UnsafeOpsHolder.U.getDouble(max + 16L);
        double _maxw = UnsafeOpsHolder.U.getDouble(max + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.min(java.lang.Math.max(_selfx, _minx), _maxx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.min(java.lang.Math.max(_selfy, _miny), _maxy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.min(java.lang.Math.max(_selfz, _minz), _maxz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.min(java.lang.Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static double compAdd_unsafe(long src) {
        return UnsafeOpsHolder.U.getDouble(src + 24L) + (UnsafeOpsHolder.U.getDouble(src + 16L) + (UnsafeOpsHolder.U.getDouble(src) + UnsafeOpsHolder.U.getDouble(src + 8L)));
    }

    public static double compMax_unsafe(long src) {
        return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(UnsafeOpsHolder.U.getDouble(src), UnsafeOpsHolder.U.getDouble(src + 8L)), UnsafeOpsHolder.U.getDouble(src + 16L)), UnsafeOpsHolder.U.getDouble(src + 24L));
    }

    public static double compMin_unsafe(long src) {
        return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(UnsafeOpsHolder.U.getDouble(src), UnsafeOpsHolder.U.getDouble(src + 8L)), UnsafeOpsHolder.U.getDouble(src + 16L)), UnsafeOpsHolder.U.getDouble(src + 24L));
    }

    public static double compMul_unsafe(long src) {
        return UnsafeOpsHolder.U.getDouble(src + 24L) * UnsafeOpsHolder.U.getDouble(src + 16L) * UnsafeOpsHolder.U.getDouble(src) * UnsafeOpsHolder.U.getDouble(src + 8L);
    }

    public static long copySign_unsafe(long dest, long src, double sign) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.copySign(_selfx, sign));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.copySign(_selfy, sign));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.copySign(_selfz, sign));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.copySign(_selfw, sign));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, double signX, double signY, double signZ, double signW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.copySign(_selfx, signX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.copySign(_selfy, signY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.copySign(_selfz, signZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.copySign(_selfw, signW));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, long sign) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _signx = UnsafeOpsHolder.U.getDouble(sign);
        double _signy = UnsafeOpsHolder.U.getDouble(sign + 8L);
        double _signz = UnsafeOpsHolder.U.getDouble(sign + 16L);
        double _signw = UnsafeOpsHolder.U.getDouble(sign + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.copySign(_selfx, _signx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.copySign(_selfy, _signy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.copySign(_selfz, _signz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.copySign(_selfw, _signw));
        return dest;
    }

    public static long cos_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.cos(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.cos(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.cos(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cos(_selfw));
        return dest;
    }

    public static long cosh_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.cosh(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.cosh(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.cosh(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cosh(_selfw));
        return dest;
    }

    public static long cross_unsafe(long dest, long src, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t12 = Math.fma(vY, wZ, -(vZ * wY));
        double _t13 = Math.fma(vZ, wW, -(vW * wZ));
        double _t14 = Math.fma(vY, wW, -(vW * wY));
        double _t15 = Math.fma(vX, wZ, -(vZ * wX));
        double _t16 = Math.fma(vX, wW, -(vW * wX));
        double _t17 = Math.fma(vX, wY, -(vY * wX));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static long cross_unsafe(long dest, long src, long v, long w) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        double _vw = UnsafeOpsHolder.U.getDouble(v + 24L);
        double _wx = UnsafeOpsHolder.U.getDouble(w);
        double _wy = UnsafeOpsHolder.U.getDouble(w + 8L);
        double _wz = UnsafeOpsHolder.U.getDouble(w + 16L);
        double _ww = UnsafeOpsHolder.U.getDouble(w + 24L);
        double _t12 = Math.fma(_vy, _wz, -(_vz * _wy));
        double _t13 = Math.fma(_vz, _ww, -(_vw * _wz));
        double _t14 = Math.fma(_vy, _ww, -(_vw * _wy));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        return cross_unsafe_sf9d19023_1(dest, _selfx, _selfy, _selfz, _selfw, _t12, _t13, _t14, Math.fma(_vx, _wz, -(_vz * _wx)), Math.fma(_vx, _ww, -(_vw * _wx)), Math.fma(_vx, _wy, -(_vy * _wx)));
    }

    /** Piece 2 of {@code cross_unsafe}, split to fit the inline budget; reached only through it. */
    private static long cross_unsafe_sf9d19023_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static long degrees_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.toDegrees(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.toDegrees(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.toDegrees(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.toDegrees(_selfw));
        return dest;
    }

    public static double distance_unsafe(long src, double otherX, double otherY, double otherZ, double otherW) {
        double _t0 = UnsafeOpsHolder.U.getDouble(src + 24L) - otherW;
        double _t1 = UnsafeOpsHolder.U.getDouble(src + 16L) - otherZ;
        double _t2 = UnsafeOpsHolder.U.getDouble(src) - otherX;
        double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - otherY;
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static double distance_unsafe(long src, long other) {
        double _t0 = UnsafeOpsHolder.U.getDouble(src + 24L) - UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t1 = UnsafeOpsHolder.U.getDouble(src + 16L) - UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t2 = UnsafeOpsHolder.U.getDouble(src) - UnsafeOpsHolder.U.getDouble(other);
        double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - UnsafeOpsHolder.U.getDouble(other + 8L);
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static double distanceSquared_unsafe(long src, double otherX, double otherY, double otherZ, double otherW) {
        double _t0 = UnsafeOpsHolder.U.getDouble(src + 24L) - otherW;
        double _t1 = UnsafeOpsHolder.U.getDouble(src + 16L) - otherZ;
        double _t2 = UnsafeOpsHolder.U.getDouble(src) - otherX;
        double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static double distanceSquared_unsafe(long src, long other) {
        double _t0 = UnsafeOpsHolder.U.getDouble(src + 24L) - UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t1 = UnsafeOpsHolder.U.getDouble(src + 16L) - UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t2 = UnsafeOpsHolder.U.getDouble(src) - UnsafeOpsHolder.U.getDouble(other);
        double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - UnsafeOpsHolder.U.getDouble(other + 8L);
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static double dot_unsafe(long src, double otherX, double otherY, double otherZ, double otherW) {
        return Math.fma(otherW, UnsafeOpsHolder.U.getDouble(src + 24L), Math.fma(otherZ, UnsafeOpsHolder.U.getDouble(src + 16L), Math.fma(otherX, UnsafeOpsHolder.U.getDouble(src), otherY * UnsafeOpsHolder.U.getDouble(src + 8L))));
    }

    public static double dot_unsafe(long src, long other) {
        return Math.fma(UnsafeOpsHolder.U.getDouble(other + 24L), UnsafeOpsHolder.U.getDouble(src + 24L), Math.fma(UnsafeOpsHolder.U.getDouble(other + 16L), UnsafeOpsHolder.U.getDouble(src + 16L), Math.fma(UnsafeOpsHolder.U.getDouble(other), UnsafeOpsHolder.U.getDouble(src), UnsafeOpsHolder.U.getDouble(other + 8L) * UnsafeOpsHolder.U.getDouble(src + 8L))));
    }

    public static long exp_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.exp(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.exp(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.exp(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.exp(_selfw));
        return dest;
    }

    public static long exp2_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.pow(2.0, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.pow(2.0, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.pow(2.0, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.pow(2.0, _selfw));
        return dest;
    }

    public static long expm1_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.expm1(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.expm1(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.expm1(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.expm1(_selfw));
        return dest;
    }

    public static long faceforward_unsafe(long dest, long src, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0 ? 1.0 : -1.0;
        UnsafeOpsHolder.U.putDouble(dest, _selfx * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t4);
        return dest;
    }

    public static long faceforward_unsafe(long dest, long src, long I, long Nref) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = Math.fma(UnsafeOpsHolder.U.getDouble(I + 24L), UnsafeOpsHolder.U.getDouble(Nref + 24L), Math.fma(UnsafeOpsHolder.U.getDouble(I + 16L), UnsafeOpsHolder.U.getDouble(Nref + 16L), Math.fma(UnsafeOpsHolder.U.getDouble(I), UnsafeOpsHolder.U.getDouble(Nref), UnsafeOpsHolder.U.getDouble(I + 8L) * UnsafeOpsHolder.U.getDouble(Nref + 8L)))) < 0.0 ? 1.0 : -1.0;
        UnsafeOpsHolder.U.putDouble(dest, _selfx * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t4);
        return dest;
    }

    public static long floor_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.floor(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.floor(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.floor(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.floor(_selfw));
        return dest;
    }

    public static long fract_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.min(_selfz - Math.floor(_selfz), 0.9999999999999999));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.min(_selfw - Math.floor(_selfw), 0.9999999999999999));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, double y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.hypot(_selfx, y));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.hypot(_selfy, y));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.hypot(_selfz, y));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.hypot(_selfw, y));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, double yX, double yY, double yZ, double yW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.hypot(_selfx, yX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.hypot(_selfy, yY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.hypot(_selfz, yZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.hypot(_selfw, yW));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, long y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _yx = UnsafeOpsHolder.U.getDouble(y);
        double _yy = UnsafeOpsHolder.U.getDouble(y + 8L);
        double _yz = UnsafeOpsHolder.U.getDouble(y + 16L);
        double _yw = UnsafeOpsHolder.U.getDouble(y + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.hypot(_selfx, _yx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.hypot(_selfy, _yy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.hypot(_selfz, _yz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.hypot(_selfw, _yw));
        return dest;
    }

    public static long inverse_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, 1.0 / _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 1.0 / _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 1.0 / _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0 / _selfw);
        return dest;
    }

    public static long inverseSqrt_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, (1.0 / java.lang.Math.sqrt(_selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, (1.0 / java.lang.Math.sqrt(_selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, (1.0 / java.lang.Math.sqrt(_selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, (1.0 / java.lang.Math.sqrt(_selfw)));
        return dest;
    }

    public static double length_unsafe(long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        return java.lang.Math.sqrt(Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))));
    }

    public static double lengthSquared_unsafe(long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        return Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static long log_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.log(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.log(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.log(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.log(_selfw));
        return dest;
    }

    public static long log10_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.log10(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.log10(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.log10(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.log10(_selfw));
        return dest;
    }

    public static long log1p_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.log1p(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.log1p(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.log1p(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.log1p(_selfw));
        return dest;
    }

    public static long log2_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.log2(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.log2(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.log2(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.log2(_selfw));
        return dest;
    }

    public static double manhattanDistance_unsafe(long src, double otherX, double otherY, double otherZ, double otherW) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src) - otherX) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 8L) - otherY) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 16L) - otherZ) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 24L) - otherW);
    }

    public static double manhattanDistance_unsafe(long src, long other) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src) - UnsafeOpsHolder.U.getDouble(other)) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 8L) - UnsafeOpsHolder.U.getDouble(other + 8L)) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 16L) - UnsafeOpsHolder.U.getDouble(other + 16L)) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 24L) - UnsafeOpsHolder.U.getDouble(other + 24L));
    }

    public static double manhattanLength_unsafe(long src) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src)) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 8L)) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 16L)) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 24L));
    }

    public static long max_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.max(_selfx, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.max(_selfy, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.max(_selfz, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.max(_selfw, scalar));
        return dest;
    }

    public static long max_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.max(_selfx, otherX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.max(_selfy, otherY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.max(_selfz, otherZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.max(_selfw, otherW));
        return dest;
    }

    public static long max_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.max(_selfx, _otherx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.max(_selfy, _othery));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.max(_selfz, _otherz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.max(_selfw, _otherw));
        return dest;
    }

    public static long min_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.min(_selfx, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.min(_selfy, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.min(_selfz, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.min(_selfw, scalar));
        return dest;
    }

    public static long min_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.min(_selfx, otherX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.min(_selfy, otherY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.min(_selfz, otherZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.min(_selfw, otherW));
        return dest;
    }

    public static long min_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.min(_selfx, _otherx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.min(_selfy, _othery));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.min(_selfz, _otherz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.min(_selfw, _otherw));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, double y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, flooredMod(_selfx, y));
        UnsafeOpsHolder.U.putDouble(dest + 8L, flooredMod(_selfy, y));
        UnsafeOpsHolder.U.putDouble(dest + 16L, flooredMod(_selfz, y));
        UnsafeOpsHolder.U.putDouble(dest + 24L, flooredMod(_selfw, y));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, double yX, double yY, double yZ, double yW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, flooredMod(_selfx, yX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, flooredMod(_selfy, yY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, flooredMod(_selfz, yZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, flooredMod(_selfw, yW));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, long y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _yx = UnsafeOpsHolder.U.getDouble(y);
        double _yy = UnsafeOpsHolder.U.getDouble(y + 8L);
        double _yz = UnsafeOpsHolder.U.getDouble(y + 16L);
        double _yw = UnsafeOpsHolder.U.getDouble(y + 24L);
        UnsafeOpsHolder.U.putDouble(dest, flooredMod(_selfx, _yx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, flooredMod(_selfy, _yy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, flooredMod(_selfz, _yz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, flooredMod(_selfw, _yw));
        return dest;
    }

    public static long nextDown_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.nextDown(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.nextDown(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.nextDown(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.nextDown(_selfw));
        return dest;
    }

    public static long nextUp_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.nextUp(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.nextUp(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.nextUp(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.nextUp(_selfw));
        return dest;
    }

    public static long normalize_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t4);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long normalizeMul_unsafe(long dest, long src, double length) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _t5 = length * (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t5);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t5);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t5);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t5);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long outerProduct_unsafe(long dest, long src, double rowX, double rowY, double rowZ, double rowW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, rowX * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, rowX * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, rowX * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, rowX * _selfw);
        UnsafeOpsHolder.U.putDouble(dest + 32L, rowY * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 40L, rowY * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 48L, rowY * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 56L, rowY * _selfw);
        UnsafeOpsHolder.U.putDouble(dest + 64L, rowZ * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 72L, rowZ * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 80L, rowZ * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 88L, rowZ * _selfw);
        UnsafeOpsHolder.U.putDouble(dest + 96L, rowW * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 104L, rowW * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 112L, rowW * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 120L, rowW * _selfw);
        return dest;
    }

    public static long outerProduct_unsafe(long dest, long src, long row) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _rowx = UnsafeOpsHolder.U.getDouble(row);
        double _rowy = UnsafeOpsHolder.U.getDouble(row + 8L);
        double _rowz = UnsafeOpsHolder.U.getDouble(row + 16L);
        double _roww = UnsafeOpsHolder.U.getDouble(row + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _rowx * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _rowx * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _rowx * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _rowx * _selfw);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _rowy * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _rowy * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _rowy * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _rowy * _selfw);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _rowz * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 72L, _rowz * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 80L, _rowz * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 88L, _rowz * _selfw);
        return outerProduct_unsafe_s12171ae2_1(dest, _selfx, _selfy, _selfz, _selfw, _roww);
    }

    /** Piece 2 of {@code outerProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static long outerProduct_unsafe_s12171ae2_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _roww) {
        UnsafeOpsHolder.U.putDouble(dest + 96L, _roww * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 104L, _roww * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 112L, _roww * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 120L, _roww * _selfw);
        return dest;
    }

    public static long pow_unsafe(long dest, long src, double exponent) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.pow(_selfx, exponent));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.pow(_selfy, exponent));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.pow(_selfz, exponent));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.pow(_selfw, exponent));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, double exponentX, double exponentY, double exponentZ, double exponentW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.pow(_selfx, exponentX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.pow(_selfy, exponentY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.pow(_selfz, exponentZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.pow(_selfw, exponentW));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, long exponent) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _exponentx = UnsafeOpsHolder.U.getDouble(exponent);
        double _exponenty = UnsafeOpsHolder.U.getDouble(exponent + 8L);
        double _exponentz = UnsafeOpsHolder.U.getDouble(exponent + 16L);
        double _exponentw = UnsafeOpsHolder.U.getDouble(exponent + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.pow(_selfx, _exponentx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.pow(_selfy, _exponenty));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.pow(_selfz, _exponentz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.pow(_selfw, _exponentw));
        return dest;
    }

    public static long project_unsafe(long dest, long src, double ontoX, double ontoY, double ontoZ, double ontoW) {
        double _t9 = Math.fma(ontoW, UnsafeOpsHolder.U.getDouble(src + 24L), Math.fma(ontoZ, UnsafeOpsHolder.U.getDouble(src + 16L), Math.fma(ontoX, UnsafeOpsHolder.U.getDouble(src), ontoY * UnsafeOpsHolder.U.getDouble(src + 8L)))) / Math.fma(ontoW, ontoW, Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY)));
        UnsafeOpsHolder.U.putDouble(dest, ontoX * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ontoY * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ontoZ * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ontoW * _t9);
        return dest;
    }

    public static long project_unsafe(long dest, long src, long onto) {
        double _ontox = UnsafeOpsHolder.U.getDouble(onto);
        double _ontoy = UnsafeOpsHolder.U.getDouble(onto + 8L);
        double _ontoz = UnsafeOpsHolder.U.getDouble(onto + 16L);
        double _ontow = UnsafeOpsHolder.U.getDouble(onto + 24L);
        double _t9 = Math.fma(_ontow, UnsafeOpsHolder.U.getDouble(src + 24L), Math.fma(_ontoz, UnsafeOpsHolder.U.getDouble(src + 16L), Math.fma(_ontox, UnsafeOpsHolder.U.getDouble(src), _ontoy * UnsafeOpsHolder.U.getDouble(src + 8L)))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        UnsafeOpsHolder.U.putDouble(dest, _ontox * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _ontoy * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _ontoz * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _ontow * _t9);
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, double normalX, double normalY, double normalZ, double normalW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-normalX, _t3, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-normalY, _t3, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(-normalZ, _t3, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-normalW, _t3, _selfw));
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, long normal) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _normalw = UnsafeOpsHolder.U.getDouble(normal + 24L);
        double _t3 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-_normalx, _t3, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-_normaly, _t3, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(-_normalz, _t3, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    public static long radians_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.toRadians(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.toRadians(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.toRadians(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.toRadians(_selfw));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, double normalX, double normalY, double normalZ, double normalW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = 2.0 * Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-normalX, _t4, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-normalY, _t4, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(-normalZ, _t4, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-normalW, _t4, _selfw));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, long normal) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _normalw = UnsafeOpsHolder.U.getDouble(normal + 24L);
        double _t4 = 2.0 * Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-_normalx, _t4, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-_normaly, _t4, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(-_normalz, _t4, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    public static long refract_unsafe(long dest, long src, double normalX, double normalY, double normalZ, double normalW, double eta) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        double _t11 = Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, Math.fma(eta, _selfx, -(normalX * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(eta, _selfy, -(normalY * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(eta, _selfz, -(normalZ * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long refract_unsafe(long dest, long src, long normal, double eta) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _normalw = UnsafeOpsHolder.U.getDouble(normal + 24L);
        double _t4 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        return refract_unsafe_s566c1de7_1(dest, eta, _selfx, _selfy, _selfz, _selfw, _normalx, _normaly, _normalz, _normalw, _t8, Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8))));
    }

    /** Piece 2 of {@code refract_unsafe}, split to fit the inline budget; reached only through it. */
    private static long refract_unsafe_s566c1de7_1(long dest, double eta, double _selfx, double _selfy, double _selfz, double _selfw, double _normalx, double _normaly, double _normalz, double _normalw, double _t8, double _t11) {
        if (_t8 >= 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, Math.fma(eta, _selfx, -(_normalx * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(eta, _selfy, -(_normaly * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(eta, _selfz, -(_normalz * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long round_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.rint(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.rint(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.rint(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.rint(_selfw));
        return dest;
    }

    public static long sign_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.signum(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.signum(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.signum(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.signum(_selfw));
        return dest;
    }

    public static long sin_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.sin(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.sin(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.sin(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.sin(_selfw));
        return dest;
    }

    public static long sinh_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.sinh(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.sinh(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.sinh(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.sinh(_selfw));
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, double edge0, double edge1) {
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t13 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - edge0) * _t0_inv));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - edge0) * _t0_inv));
        double _t15 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - edge0) * _t0_inv));
        double _t16 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 24L) - edge0) * _t0_inv));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(-2.0, _t15, 3.0) * _t15 * _t15);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        double _t16 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - edge0X) / (edge1X - edge0X)));
        double _t17 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - edge0Y) / (edge1Y - edge0Y)));
        double _t18 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - edge0Z) / (edge1Z - edge0Z)));
        double _t19 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 24L) - edge0W) / (edge1W - edge0W)));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-2.0, _t17, 3.0) * _t17 * _t17);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(-2.0, _t18, 3.0) * _t18 * _t18);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-2.0, _t19, 3.0) * _t19 * _t19);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, long edge0, long edge1) {
        double _edge0x = UnsafeOpsHolder.U.getDouble(edge0);
        double _edge0y = UnsafeOpsHolder.U.getDouble(edge0 + 8L);
        double _edge0z = UnsafeOpsHolder.U.getDouble(edge0 + 16L);
        double _edge0w = UnsafeOpsHolder.U.getDouble(edge0 + 24L);
        return smoothstep_unsafe_s9b21a18d_1(dest, src, edge1, _edge0x, _edge0y, _edge0z, _edge0w);
    }

    /** Piece 2 of {@code smoothstep_unsafe}, split to fit the inline budget; reached only through it. */
    private static long smoothstep_unsafe_s9b21a18d_1(long dest, long src, long edge1, double _edge0x, double _edge0y, double _edge0z, double _edge0w) {
        double _t16 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - _edge0x) / (UnsafeOpsHolder.U.getDouble(edge1) - _edge0x)));
        double _t17 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - _edge0y) / (UnsafeOpsHolder.U.getDouble(edge1 + 8L) - _edge0y)));
        double _t18 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - _edge0z) / (UnsafeOpsHolder.U.getDouble(edge1 + 16L) - _edge0z)));
        double _t19 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 24L) - _edge0w) / (UnsafeOpsHolder.U.getDouble(edge1 + 24L) - _edge0w)));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(-2.0, _t17, 3.0) * _t17 * _t17);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(-2.0, _t18, 3.0) * _t18 * _t18);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(-2.0, _t19, 3.0) * _t19 * _t19);
        return dest;
    }

    public static long sqrt_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.sqrt(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.sqrt(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.sqrt(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.sqrt(_selfw));
        return dest;
    }

    public static long step_unsafe(long dest, long src, double edge) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx < edge ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy < edge ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz < edge ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw < edge ? 0.0 : 1.0);
        return dest;
    }

    public static long step_unsafe(long dest, long src, double edgeX, double edgeY, double edgeZ, double edgeW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx < edgeX ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy < edgeY ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz < edgeZ ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw < edgeW ? 0.0 : 1.0);
        return dest;
    }

    public static long step_unsafe(long dest, long src, long edge) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _edgex = UnsafeOpsHolder.U.getDouble(edge);
        double _edgey = UnsafeOpsHolder.U.getDouble(edge + 8L);
        double _edgez = UnsafeOpsHolder.U.getDouble(edge + 16L);
        double _edgew = UnsafeOpsHolder.U.getDouble(edge + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx < _edgex ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy < _edgey ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz < _edgez ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw < _edgew ? 0.0 : 1.0);
        return dest;
    }

    public static long tan_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.tan(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.tan(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.tan(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.tan(_selfw));
        return dest;
    }

    public static long tanh_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.tanh(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.tanh(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.tanh(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.tanh(_selfw));
        return dest;
    }

    public static long trunc_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz >= 0.0 ? Math.floor(_selfz) : Math.ceil(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw >= 0.0 ? Math.floor(_selfw) : Math.ceil(_selfw));
        return dest;
    }

    public static long ulp_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.ulp(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.ulp(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.ulp(_selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.ulp(_selfw));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat30 = UnsafeOpsHolder.U.getDouble(mat + 24L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat31 = UnsafeOpsHolder.U.getDouble(mat + 56L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 72L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 80L);
        double _mat32 = UnsafeOpsHolder.U.getDouble(mat + 88L);
        double _mat03 = UnsafeOpsHolder.U.getDouble(mat + 96L);
        double _mat13 = UnsafeOpsHolder.U.getDouble(mat + 104L);
        double _mat23 = UnsafeOpsHolder.U.getDouble(mat + 112L);
        return preMul_unsafe_sa1243cb5_1(dest, mat, _selfx, _selfy, _selfz, _selfw, _mat00, _mat10, _mat20, _mat30, _mat01, _mat11, _mat21, _mat31, _mat02, _mat12, _mat22, _mat32, _mat03, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMul_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMul_unsafe_sa1243cb5_1(long dest, long mat, double _selfx, double _selfy, double _selfz, double _selfw, double _mat00, double _mat10, double _mat20, double _mat30, double _mat01, double _mat11, double _mat21, double _mat31, double _mat02, double _mat12, double _mat22, double _mat32, double _mat03, double _mat13, double _mat23) {
        double _mat33 = UnsafeOpsHolder.U.getDouble(mat + 120L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_mat03, _selfw, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_mat13, _selfw, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_mat23, _selfw, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_mat33, _selfw, Math.fma(_mat32, _selfz, Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * Math.fma(quatX, _selfy, -(quatY * _selfx));
        double _t10 = 2.0 * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        double _t11 = 2.0 * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, long quat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _quatx = UnsafeOpsHolder.U.getDouble(quat);
        double _quaty = UnsafeOpsHolder.U.getDouble(quat + 8L);
        double _quatz = UnsafeOpsHolder.U.getDouble(quat + 16L);
        double _quatw = UnsafeOpsHolder.U.getDouble(quat + 24L);
        double _t9 = 2.0 * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        double _t10 = 2.0 * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        double _t11 = 2.0 * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotateInverse_unsafe(long dest, long src, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        double _t10 = 2.0 * Math.fma(quatY, _selfx, -(quatX * _selfy));
        double _t11 = 2.0 * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotateInverse_unsafe(long dest, long src, long quat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _quatx = UnsafeOpsHolder.U.getDouble(quat);
        double _quaty = UnsafeOpsHolder.U.getDouble(quat + 8L);
        double _quatz = UnsafeOpsHolder.U.getDouble(quat + 16L);
        double _quatw = UnsafeOpsHolder.U.getDouble(quat + 24L);
        double _t9 = 2.0 * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        double _t10 = 2.0 * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        double _t11 = 2.0 * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfy, _t1, -(_selfz * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_selfy, _t0, _selfz * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_selfx, _t1, _selfz * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_selfz, _t1, -(_selfx * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotateZ_unsafe(long dest, long src, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_selfx, _t0, _selfy * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    /**
     * The power of two that brings max(|a|, |b|, |c|) into [1, 2), from the largest exponent
     * field: multiplying by it is exact. Clamped to [2^-1022, 2^1022], so zero and subnormal
     * values scale up without overflow and the largest doubles land in [2, 4).
     */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }

    /**
     * The floored remainder of x and y, exactly kotlin.Double.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
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
