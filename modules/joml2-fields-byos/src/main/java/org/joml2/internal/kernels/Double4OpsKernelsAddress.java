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
 * to keep generated sources IDE-sized; public only because {@code Double4Ops}
 * and its SIMD cell call it from their own packages. Not public API.
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, b, cX));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, b, cY));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, b, cZ));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, b, cW));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (b) + (cX)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (b) + (cY)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (b) + (cZ)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (b) + (cW)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, long c, double b) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_unsafe_mulAdd(dest, src, c, b);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _cx = UnsafeOpsHolder.U.getDouble(c);
        double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
        double _cz = UnsafeOpsHolder.U.getDouble(c + 16L);
        double _cw = UnsafeOpsHolder.U.getDouble(c + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, b, _cx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, b, _cy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, b, _cz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, b, _cw));
        return dest;
    }

    /** {@code fma_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long fma_unsafe_mulAdd(long dest, long src, long c, double b) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _cx = UnsafeOpsHolder.U.getDouble(c);
        double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
        double _cz = UnsafeOpsHolder.U.getDouble(c + 16L);
        double _cw = UnsafeOpsHolder.U.getDouble(c + 24L);
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (b) + (_cx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (b) + (_cy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (b) + (_cz)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (b) + (_cw)));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, bX, cX));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, bY, cY));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, bZ, cZ));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, bW, cW));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (bX) + (cX)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (bY) + (cY)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (bZ) + (cZ)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (bW) + (cW)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, long b, long c) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_unsafe_mulAdd(dest, src, b, c);
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
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _bx, _cx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _by, _cy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _bz, _cz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    /** {@code fma_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long fma_unsafe_mulAdd(long dest, long src, long b, long c) {
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
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_bx) + (_cx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_by) + (_cy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_bz) + (_cz)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_bw) + (_cw)));
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
        double _t4 = java.lang.Math.sqrt(1.0 - u);
        UnsafeOpsHolder.U.putDouble(dest, Math.cosWithSin(_t1) * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.sinWithCos(_t1) * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.cosWithSin(_t3) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.sinWithCos(_t3) * _t0);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
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
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(p1X, _t7, _selfx * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(p1Y, _t7, _selfy * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(p1Z, _t7, _selfz * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(p1W, _t7, _selfw * _t8) + java.lang.Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    /** {@code bezier_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier_unsafe_mulAdd(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
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
        UnsafeOpsHolder.U.putDouble(dest, ((p1X) * (_t7) + (_selfx * _t8)) + ((p2X) * (_t6) + (p3X * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((p1Y) * (_t7) + (_selfy * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((p1Z) * (_t7) + (_selfz * _t8)) + ((p2Z) * (_t6) + (p3Z * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((p1W) * (_t7) + (_selfw * _t8)) + ((p2W) * (_t6) + (p3W * _t2)));
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, long p1, long p2, long p3, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier_unsafe_fma(dest, src, p1, p2, p3, t);
        return bezier_unsafe_mulAdd(dest, src, p1, p2, p3, t);
    }

    /** {@code bezier_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier_unsafe_fma(long dest, long src, long p1, long p2, long p3, double t) {
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
        return bezier_unsafe_fma_sc73aa9e7_1(dest, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, t * _t1, 3.0 * _t0 * _t1, 3.0 * t * _t3, _t0 * _t3);
    }

    /** Piece 2 of {@code bezier_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long bezier_unsafe_fma_sc73aa9e7_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w, double _t2, double _t6, double _t7, double _t8) {
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_p1x, _t7, _selfx * _t8) + java.lang.Math.fma(_p2x, _t6, _p3x * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_p1z, _t7, _selfz * _t8) + java.lang.Math.fma(_p2z, _t6, _p3z * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_p1w, _t7, _selfw * _t8) + java.lang.Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    /** {@code bezier_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, double t) {
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
        return bezier_unsafe_mulAdd_sd1b11470_1(dest, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, t * _t1, 3.0 * _t0 * _t1, 3.0 * t * _t3, _t0 * _t3);
    }

    /** Piece 2 of {@code bezier_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long bezier_unsafe_mulAdd_sd1b11470_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w, double _t2, double _t6, double _t7, double _t8) {
        UnsafeOpsHolder.U.putDouble(dest, ((_p1x) * (_t7) + (_selfx * _t8)) + ((_p2x) * (_t6) + (_p3x * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_p1z) * (_t7) + (_selfz * _t8)) + ((_p2z) * (_t6) + (_p3z * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_p1w) * (_t7) + (_selfw * _t8)) + ((_p2w) * (_t6) + (_p3w * _t2)));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, _selfy * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, _selfz * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(p2W, _t0, java.lang.Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    /** {@code bezier2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2_unsafe_mulAdd(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, ((p2X) * (_t0) + (((p1X) * (_t3) + (_selfx * _t4)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((p2Y) * (_t0) + (((p1Y) * (_t3) + (_selfy * _t4)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((p2Z) * (_t0) + (((p1Z) * (_t3) + (_selfz * _t4)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((p2W) * (_t0) + (((p1W) * (_t3) + (_selfw * _t4)))));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, long p1, long p2, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_unsafe_fma(dest, src, p1, p2, t);
        return bezier2_unsafe_mulAdd(dest, src, p1, p2, t);
    }

    /** {@code bezier2_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2_unsafe_fma(long dest, long src, long p1, long p2, double t) {
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
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_p2x, _t0, java.lang.Math.fma(_p1x, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_p2z, _t0, java.lang.Math.fma(_p1z, _t3, _selfz * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_p2w, _t0, java.lang.Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    /** {@code bezier2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2_unsafe_mulAdd(long dest, long src, long p1, long p2, double t) {
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
        UnsafeOpsHolder.U.putDouble(dest, ((_p2x) * (_t0) + (((_p1x) * (_t3) + (_selfx * _t4)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_p2z) * (_t0) + (((_p1z) * (_t3) + (_selfz * _t4)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_p2w) * (_t0) + (((_p1w) * (_t3) + (_selfw * _t4)))));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2Tangent_unsafe_mulAdd(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        UnsafeOpsHolder.U.putDouble(dest, ((p1X - _selfx) * (_t2) + ((p2X - p1X) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((p1Z - _selfz) * (_t2) + ((p2Z - p1Z) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((p1W - _selfw) * (_t2) + ((p2W - p1W) * _t1)));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, long p1, long p2, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_unsafe_mulAdd(dest, src, p1, p2, t);
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
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2Tangent_unsafe_mulAdd(long dest, long src, long p1, long p2, double t) {
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
        UnsafeOpsHolder.U.putDouble(dest, ((_p1x - _selfx) * (_t2) + ((_p2x - _p1x) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_p1z - _selfz) * (_t2) + ((_p2z - _p1z) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_p1w - _selfw) * (_t2) + ((_p2w - _p1w) * _t1)));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(p3W - p2W, _t2, java.lang.Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezierTangent_unsafe_mulAdd(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, ((p3X - p2X) * (_t2) + (((p1X - _selfx) * (_t6) + ((p2X - p1X) * _t5)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((p3Z - p2Z) * (_t2) + (((p1Z - _selfz) * (_t6) + ((p2Z - p1Z) * _t5)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((p3W - p2W) * (_t2) + (((p1W - _selfw) * (_t6) + ((p2W - p1W) * _t5)))));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, long p1, long p2, long p3, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_unsafe_fma(dest, src, p1, p2, p3, t);
        return bezierTangent_unsafe_mulAdd(dest, src, p1, p2, p3, t);
    }

    /** {@code bezierTangent_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezierTangent_unsafe_fma(long dest, long src, long p1, long p2, long p3, double t) {
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
        return bezierTangent_unsafe_fma_scc593ce_1(dest, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, 3.0 * t * t, 6.0 * t * _t1, 3.0 * _t1 * _t1);
    }

    /** Piece 2 of {@code bezierTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long bezierTangent_unsafe_fma_scc593ce_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w, double _t2, double _t5, double _t6) {
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_p3x - _p2x, _t2, java.lang.Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_p3z - _p2z, _t2, java.lang.Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_p3w - _p2w, _t2, java.lang.Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezierTangent_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, double t) {
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
        return bezierTangent_unsafe_mulAdd_s8f38ddb7_1(dest, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, 3.0 * t * t, 6.0 * t * _t1, 3.0 * _t1 * _t1);
    }

    /** Piece 2 of {@code bezierTangent_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long bezierTangent_unsafe_mulAdd_s8f38ddb7_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w, double _t2, double _t5, double _t6) {
        UnsafeOpsHolder.U.putDouble(dest, ((_p3x - _p2x) * (_t2) + (((_p1x - _selfx) * (_t6) + ((_p2x - _p1x) * _t5)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_p3z - _p2z) * (_t2) + (((_p1z - _selfz) * (_t6) + ((_p2z - _p1z) * _t5)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_p3w - _p2w) * (_t2) + (((_p1w - _selfw) * (_t6) + ((_p2w - _p1w) * _t5)))));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_unsafe_fma(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRom_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRom_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_fma(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * (java.lang.Math.fma(2.0, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * (java.lang.Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_unsafe_fma_sdeb8d718_1(dest, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_fma_sdeb8d718_1(long dest, double p1Z, double p1W, double p2Z, double p2W, double p3Z, double p3W, double t, double _selfz, double _selfw, double _t0, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * (java.lang.Math.fma(2.0, p1Z, t * (p2Z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - _selfz)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * (java.lang.Math.fma(2.0, p1W, t * (p2W - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1W, java.lang.Math.fma(2.0, _selfw, java.lang.Math.fma(4.0, p2W, -p3W))), _t0, java.lang.Math.fma(-3.0, p2W, java.lang.Math.fma(3.0, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_mulAdd(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * (((2.0) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0) * (_selfx) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) * (_t0) + (((((3.0) * (p1X) + (p3X - _selfx))) - (3.0) * (p2X)) * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * (((2.0) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0) * (_selfy) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) * (_t0) + (((((3.0) * (p1Y) + (p3Y - _selfy))) - (3.0) * (p2Y)) * _t1))));
        return catmullRom_unsafe_mulAdd_s7ded4a9b_1(dest, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_mulAdd_s7ded4a9b_1(long dest, double p1Z, double p1W, double p2Z, double p2W, double p3Z, double p3W, double t, double _selfz, double _selfw, double _t0, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * (((2.0) * (p1Z) + (t * (p2Z - _selfz))) + ((((((2.0) * (_selfz) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) * (_t0) + (((((3.0) * (p1Z) + (p3Z - _selfz))) - (3.0) * (p2Z)) * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * (((2.0) * (p1W) + (t * (p2W - _selfw))) + ((((((2.0) * (_selfw) + (((4.0) * (p2W) - (p3W))))) - (5.0) * (p1W))) * (_t0) + (((((3.0) * (p1W) + (p3W - _selfw))) - (3.0) * (p2W)) * _t1))));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, long p1, long p2, long p3, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_unsafe_fma(dest, src, p1, p2, p3, t);
        return catmullRom_unsafe_mulAdd(dest, src, p1, p2, p3, t);
    }

    /** {@code catmullRom_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_fma(long dest, long src, long p1, long p2, long p3, double t) {
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
        return catmullRom_unsafe_fma_s26b03350_1(dest, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, _t0, t * _t0);
    }

    /** Piece 2 of {@code catmullRom_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_fma_s26b03350_1(long dest, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w, double _t0, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * (java.lang.Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1x, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0, _p2x, java.lang.Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * (java.lang.Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0, _p2y, java.lang.Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * (java.lang.Math.fma(2.0, _p1z, t * (_p2z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, _p2z, -_p3z))), _t0, java.lang.Math.fma(-3.0, _p2z, java.lang.Math.fma(3.0, _p1z, _p3z - _selfz)) * _t1)));
        return catmullRom_unsafe_fma_s26b03350_2(dest, t, _selfw, _p1w, _p2w, _p3w, _t0, _t1);
    }

    /** Piece 3 of {@code catmullRom_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_fma_s26b03350_2(long dest, double t, double _selfw, double _p1w, double _p2w, double _p3w, double _t0, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * (java.lang.Math.fma(2.0, _p1w, t * (_p2w - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1w, java.lang.Math.fma(2.0, _selfw, java.lang.Math.fma(4.0, _p2w, -_p3w))), _t0, java.lang.Math.fma(-3.0, _p2w, java.lang.Math.fma(3.0, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, double t) {
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
        return catmullRom_unsafe_mulAdd_se5e7111_1(dest, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, _t0, t * _t0);
    }

    /** Piece 2 of {@code catmullRom_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_mulAdd_se5e7111_1(long dest, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w, double _t0, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * (((2.0) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0) * (_selfx) + (((4.0) * (_p2x) - (_p3x))))) - (5.0) * (_p1x))) * (_t0) + (((((3.0) * (_p1x) + (_p3x - _selfx))) - (3.0) * (_p2x)) * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * (((2.0) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0) * (_selfy) + (((4.0) * (_p2y) - (_p3y))))) - (5.0) * (_p1y))) * (_t0) + (((((3.0) * (_p1y) + (_p3y - _selfy))) - (3.0) * (_p2y)) * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * (((2.0) * (_p1z) + (t * (_p2z - _selfz))) + ((((((2.0) * (_selfz) + (((4.0) * (_p2z) - (_p3z))))) - (5.0) * (_p1z))) * (_t0) + (((((3.0) * (_p1z) + (_p3z - _selfz))) - (3.0) * (_p2z)) * _t1))));
        return catmullRom_unsafe_mulAdd_se5e7111_2(dest, t, _selfw, _p1w, _p2w, _p3w, _t0, _t1);
    }

    /** Piece 3 of {@code catmullRom_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_mulAdd_se5e7111_2(long dest, double t, double _selfw, double _p1w, double _p2w, double _p3w, double _t0, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * (((2.0) * (_p1w) + (t * (_p2w - _selfw))) + ((((((2.0) * (_selfw) + (((4.0) * (_p2w) - (_p3w))))) - (5.0) * (_p1w))) * (_t0) + (((((3.0) * (_p1w) + (_p3w - _selfw))) - (3.0) * (_p2w)) * _t1))));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_unsafe_fma(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRomTangent_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRomTangent_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_fma(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, p2X, -p3X))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, p2Y, -p3Y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return catmullRomTangent_unsafe_fma_sc54890ad_1(dest, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_fma_sc54890ad_1(long dest, double p1Z, double p1W, double p2Z, double p2W, double p3Z, double p3W, double t, double _selfz, double _selfw, double _t0) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, p2Z, -p3Z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1W, java.lang.Math.fma(2.0, _selfw, java.lang.Math.fma(4.0, p2W, -p3W))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2W, java.lang.Math.fma(3.0, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    /** {@code catmullRomTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd(long dest, long src, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfx) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) + (((3.0 * ((((3.0) * (p1X) + (p3X - _selfx))) - (3.0) * (p2X))) * (_t0) + (p2X - _selfx)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfy) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) + (((3.0 * ((((3.0) * (p1Y) + (p3Y - _selfy))) - (3.0) * (p2Y))) * (_t0) + (p2Y - _selfy)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfz) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) + (((3.0 * ((((3.0) * (p1Z) + (p3Z - _selfz))) - (3.0) * (p2Z))) * (_t0) + (p2Z - _selfz)))));
        return catmullRomTangent_unsafe_mulAdd_sb16e00a4_1(dest, p1W, p2W, p3W, t, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd_sb16e00a4_1(long dest, double p1W, double p2W, double p3W, double t, double _selfw, double _t0) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfw) + (((4.0) * (p2W) - (p3W))))) - (5.0) * (p1W))) + (((3.0 * ((((3.0) * (p1W) + (p3W - _selfw))) - (3.0) * (p2W))) * (_t0) + (p2W - _selfw)))));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, long p1, long p2, long p3, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_unsafe_fma(dest, src, p1, p2, p3, t);
        return catmullRomTangent_unsafe_mulAdd(dest, src, p1, p2, p3, t);
    }

    /** {@code catmullRomTangent_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_fma(long dest, long src, long p1, long p2, long p3, double t) {
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
        return catmullRomTangent_unsafe_fma_sd37ad20b_1(dest, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, t * t);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_fma_sd37ad20b_1(long dest, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w, double _t0) {
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1x, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, _p2x, -_p3x))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2x, java.lang.Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, _p2y, -_p3y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2y, java.lang.Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, _p2z, -_p3z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2z, java.lang.Math.fma(3.0, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return catmullRomTangent_unsafe_fma_sd37ad20b_2(dest, t, _selfw, _p1w, _p2w, _p3w, _t0);
    }

    /** Piece 3 of {@code catmullRomTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_fma_sd37ad20b_2(long dest, double t, double _selfw, double _p1w, double _p2w, double _p3w, double _t0) {
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1w, java.lang.Math.fma(2.0, _selfw, java.lang.Math.fma(4.0, _p2w, -_p3w))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2w, java.lang.Math.fma(3.0, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    /** {@code catmullRomTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, double t) {
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
        return catmullRomTangent_unsafe_mulAdd_s9253be94_1(dest, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, t * t);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd_s9253be94_1(long dest, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _p1x, double _p1y, double _p1z, double _p1w, double _p2x, double _p2y, double _p2z, double _p2w, double _p3x, double _p3y, double _p3z, double _p3w, double _t0) {
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfx) + (((4.0) * (_p2x) - (_p3x))))) - (5.0) * (_p1x))) + (((3.0 * ((((3.0) * (_p1x) + (_p3x - _selfx))) - (3.0) * (_p2x))) * (_t0) + (_p2x - _selfx)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfy) + (((4.0) * (_p2y) - (_p3y))))) - (5.0) * (_p1y))) + (((3.0 * ((((3.0) * (_p1y) + (_p3y - _selfy))) - (3.0) * (_p2y))) * (_t0) + (_p2y - _selfy)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfz) + (((4.0) * (_p2z) - (_p3z))))) - (5.0) * (_p1z))) + (((3.0 * ((((3.0) * (_p1z) + (_p3z - _selfz))) - (3.0) * (_p2z))) * (_t0) + (_p2z - _selfz)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfw) + (((4.0) * (_p2w) - (_p3w))))) - (5.0) * (_p1w))) + (((3.0 * ((((3.0) * (_p1w) + (_p3w - _selfw))) - (3.0) * (_p2w))) * (_t0) + (_p2w - _selfw)))));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermite_unsafe_mulAdd(dest, src, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * java.lang.Math.fma(t, t, -t);
        double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t10, t0W * _t7) + java.lang.Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    /** {@code hermite_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermite_unsafe_mulAdd(long dest, long src, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * ((t) * (t) - (t));
        double _t7 = ((t - 2.0) * (_t0) + (t));
        double _t9 = ((3.0) * (_t0) - (_t2 + _t2));
        double _t10 = ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0))));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t10) + (t0W * _t7)) + ((t1W) * (_t5) + (v1W * _t9)));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, long t0, long v1, long t1, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermite_unsafe_fma(dest, src, t0, v1, t1, t);
        return hermite_unsafe_mulAdd(dest, src, t0, v1, t1, t);
    }

    /** {@code hermite_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermite_unsafe_fma(long dest, long src, long t0, long v1, long t1, double t) {
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
        return hermite_unsafe_fma_s1b0d4710_1(dest, _selfx, _selfy, _selfz, _selfw, _t0x, _t0y, _t0z, _t0w, _v1x, _v1y, _v1z, _v1w, _t1x, _t1y, _t1z, _t1w, _t0, _t2, t * java.lang.Math.fma(t, t, -t), java.lang.Math.fma(t - 2.0, _t0, t), java.lang.Math.fma(3.0, _t0, -(_t2 + _t2)));
    }

    /** Piece 2 of {@code hermite_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long hermite_unsafe_fma_s1b0d4710_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t0x, double _t0y, double _t0z, double _t0w, double _v1x, double _v1y, double _v1z, double _v1w, double _t1x, double _t1y, double _t1z, double _t1w, double _t0, double _t2, double _t5, double _t7, double _t9) {
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t10, _t0x * _t7) + java.lang.Math.fma(_t1x, _t5, _v1x * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t10, _t0z * _t7) + java.lang.Math.fma(_t1z, _t5, _v1z * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t10, _t0w * _t7) + java.lang.Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    /** {@code hermite_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermite_unsafe_mulAdd(long dest, long src, long t0, long v1, long t1, double t) {
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
        return hermite_unsafe_mulAdd_s4e2f5335_1(dest, _selfx, _selfy, _selfz, _selfw, _t0x, _t0y, _t0z, _t0w, _v1x, _v1y, _v1z, _v1w, _t1x, _t1y, _t1z, _t1w, t * ((t) * (t) - (t)), ((t - 2.0) * (_t0) + (t)), ((3.0) * (_t0) - (_t2 + _t2)), ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0)))));
    }

    /** Piece 2 of {@code hermite_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long hermite_unsafe_mulAdd_s4e2f5335_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t0x, double _t0y, double _t0z, double _t0w, double _v1x, double _v1y, double _v1z, double _v1w, double _t1x, double _t1y, double _t1z, double _t1w, double _t5, double _t7, double _t9, double _t10) {
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t10) + (_t0x * _t7)) + ((_t1x) * (_t5) + (_v1x * _t9)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t10) + (_t0z * _t7)) + ((_t1z) * (_t5) + (_v1z * _t9)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t10) + (_t0w * _t7)) + ((_t1w) * (_t5) + (_v1w * _t9)));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_unsafe_mulAdd(dest, src, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
        double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t6, t0W * _t9) + java.lang.Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    /** {@code hermiteTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd(long dest, long src, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = t * t;
        double _t6 = 6.0 * ((t) * (t) - (t));
        double _t7 = 6.0 * ((t) - (t) * (t));
        double _t8 = ((3.0) * (_t0) - (t + t));
        double _t9 = ((3.0) * (_t0) + (((1.0) - (4.0) * (t))));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t6) + (t0W * _t9)) + ((t1W) * (_t8) + (v1W * _t7)));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, long t0, long v1, long t1, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_unsafe_fma(dest, src, t0, v1, t1, t);
        return hermiteTangent_unsafe_mulAdd(dest, src, t0, v1, t1, t);
    }

    /** {@code hermiteTangent_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermiteTangent_unsafe_fma(long dest, long src, long t0, long v1, long t1, double t) {
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
        return hermiteTangent_unsafe_fma_safd30eab_1(dest, _selfx, _selfy, _selfz, _selfw, _t0x, _t0y, _t0z, _t0w, _v1x, _v1y, _v1z, _v1w, _t1x, _t1y, _t1z, _t1w, 6.0 * java.lang.Math.fma(t, t, -t), 6.0 * java.lang.Math.fma(-t, t, t), java.lang.Math.fma(3.0, _t0, -(t + t)), java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0)));
    }

    /** Piece 2 of {@code hermiteTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long hermiteTangent_unsafe_fma_safd30eab_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t0x, double _t0y, double _t0z, double _t0w, double _v1x, double _v1y, double _v1z, double _v1w, double _t1x, double _t1y, double _t1z, double _t1w, double _t6, double _t7, double _t8, double _t9) {
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t6, _t0x * _t9) + java.lang.Math.fma(_t1x, _t8, _v1x * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t6, _t0z * _t9) + java.lang.Math.fma(_t1z, _t8, _v1z * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t6, _t0w * _t9) + java.lang.Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    /** {@code hermiteTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd(long dest, long src, long t0, long v1, long t1, double t) {
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
        return hermiteTangent_unsafe_mulAdd_s6a2b03a4_1(dest, _selfx, _selfy, _selfz, _selfw, _t0x, _t0y, _t0z, _t0w, _v1x, _v1y, _v1z, _v1w, _t1x, _t1y, _t1z, _t1w, 6.0 * ((t) * (t) - (t)), 6.0 * ((t) - (t) * (t)), ((3.0) * (_t0) - (t + t)), ((3.0) * (_t0) + (((1.0) - (4.0) * (t)))));
    }

    /** Piece 2 of {@code hermiteTangent_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd_s6a2b03a4_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t0x, double _t0y, double _t0z, double _t0w, double _v1x, double _v1y, double _v1z, double _v1w, double _t1x, double _t1y, double _t1z, double _t1w, double _t6, double _t7, double _t8, double _t9) {
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t6) + (_t0x * _t9)) + ((_t1x) * (_t8) + (_v1x * _t7)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t6) + (_t0z * _t9)) + ((_t1z) * (_t8) + (_v1z * _t7)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t6) + (_t0w * _t9)) + ((_t1w) * (_t8) + (_v1w * _t7)));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(t, otherW - _selfw, _selfw));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((t) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((t) * (otherZ - _selfz) + (_selfz)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((t) * (otherW - _selfw) + (_selfw)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, long other, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_unsafe_mulAdd(dest, src, other, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lerp_unsafe_mulAdd(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, ((t) * (_otherx - _selfx) + (_selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((t) * (_othery - _selfy) + (_selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((t) * (_otherz - _selfz) + (_selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((t) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(tX, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(tY, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(tZ, otherZ - _selfz, _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(tW, otherW - _selfw, _selfw));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((tX) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((tY) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((tZ) * (otherZ - _selfz) + (_selfz)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((tW) * (otherW - _selfw) + (_selfw)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, long other, long t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_unsafe_mulAdd(dest, src, other, t);
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
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_tx, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_ty, _othery - _selfy, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_tz, _otherz - _selfz, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lerp_unsafe_mulAdd(long dest, long src, long other, long t) {
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
        UnsafeOpsHolder.U.putDouble(dest, ((_tx) * (_otherx - _selfx) + (_selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_ty) * (_othery - _selfy) + (_selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_tz) * (_otherz - _selfz) + (_selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_tw) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, otherX, otherY, otherZ, otherW, t);
        return slerp_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        double _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t10));
        double _t16 = _selfx * _t11;
        double _t19 = _selfw * _t11;
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
        return slerp_unsafe_fma_seb36941e_1(dest, src, otherX, otherY, otherZ, otherW, t, _t14, _t16, _t19, _t21, _t24, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9), java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24))));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_seb36941e_1(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28, double _t31) {
        double _t40 = java.lang.Math.fma(otherW, _t14, -(_t31 * _t19));
        double _t41 = java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21));
        double _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        double _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        double _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        double _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        double _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        double _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        double _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _t64 = _t28 * Math.cosWithSin(_t61);
        double _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        return slerp_unsafe_fma_seb36941e_2(dest, _t19, _t21, _t49, _t50, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_seb36941e_2(long dest, double _t19, double _t21, double _t49, double _t50, double _t64, double _sp0) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        double _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t10));
        double _t16 = _selfx * _t11;
        double _t19 = _selfw * _t11;
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
        double _t31 = ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24))))));
        return slerp_unsafe_mulAdd_s10dbb2dd_1(dest, src, otherX, otherY, otherZ, otherW, t, _t14, _t16, _t19, _t21, _t24, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9), _t31, ((otherW) * (_t14) - (_t31 * _t19)));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s10dbb2dd_1(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28, double _t31, double _t40) {
        double _t41 = ((otherZ) * (_t14) - (_t31 * _t21));
        double _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        double _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        double _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        double _t49 = ((_t48) * (_t19) + (_t40));
        double _t50 = ((_t48) * (_t21) + (_t41));
        double _t51 = ((_t48) * (_t16) + (_t42));
        double _t52 = ((_t48) * (_t24) + (_t43));
        double _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _t64 = _t28 * Math.cosWithSin(_t61);
        double _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        UnsafeOpsHolder.U.putDouble(dest, ((_t16) * (_t64) + (_sp0 * _t51)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t24) * (_t64) + (_sp0 * _t52)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t21) * (_t64) + (_sp0 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double4OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, otherZ, otherW, t);
        Double4OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, otherX, otherY, otherZ, otherW, t);
        return slerp_degenerate_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        double _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        double _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        double _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 5.048709793414476E-29) {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        double _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        double _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        double _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        double _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        double _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        double _t81 = ((_t80) * (_t43) + (_t72));
        double _t82 = ((_t80) * (_t45) + (_t73));
        double _t83 = ((_t80) * (_t47) + (_t74));
        double _t84 = ((_t80) * (_t49) + (_t75));
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 5.048709793414476E-29) {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((t) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((t) * (otherZ - _selfz) + (_selfz)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((t) * (otherW - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long other, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, other, t);
        return slerp_unsafe_mulAdd(dest, src, other, t);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t10 = java.lang.Math.fma(_otherw, _otherw, java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        return slerp_unsafe_fma_s29f3f682_1(dest, src, other, t, _otherx, _othery, _otherz, _otherw, (1.0 / java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s29f3f682_1(long dest, long src, long other, double t, double _otherx, double _othery, double _otherz, double _otherw, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28) {
        double _t31 = java.lang.Math.fma(_otherw * _t14, _t19, java.lang.Math.fma(_otherz * _t14, _t21, java.lang.Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        double _t40 = java.lang.Math.fma(_otherw, _t14, -(_t31 * _t19));
        double _t41 = java.lang.Math.fma(_otherz, _t14, -(_t31 * _t21));
        double _t42 = java.lang.Math.fma(_otherx, _t14, -(_t31 * _t16));
        double _t43 = java.lang.Math.fma(_othery, _t14, -(_t31 * _t24));
        double _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        double _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        double _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        double _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        double _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        return slerp_unsafe_fma_s29f3f682_2(dest, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s29f3f682_2(long dest, double _t16, double _t19, double _t21, double _t24, double _t49, double _t50, double _t51, double _t52, double _t64, double _sp0) {
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t10 = ((_otherw) * (_otherw) + (((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))))));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        return slerp_unsafe_mulAdd_s3ae7ab55_1(dest, src, other, t, _otherx, _othery, _otherz, _otherw, (1.0 / java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s3ae7ab55_1(long dest, long src, long other, double t, double _otherx, double _othery, double _otherz, double _otherw, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28) {
        double _t31 = ((_otherw * _t14) * (_t19) + (((_otherz * _t14) * (_t21) + (((_otherx * _t14) * (_t16) + (_othery * _t14 * _t24))))));
        double _t40 = ((_otherw) * (_t14) - (_t31 * _t19));
        double _t41 = ((_otherz) * (_t14) - (_t31 * _t21));
        double _t42 = ((_otherx) * (_t14) - (_t31 * _t16));
        double _t43 = ((_othery) * (_t14) - (_t31 * _t24));
        double _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        double _t49 = ((_t48) * (_t19) + (_t40));
        double _t50 = ((_t48) * (_t21) + (_t41));
        double _t51 = ((_t48) * (_t16) + (_t42));
        double _t52 = ((_t48) * (_t24) + (_t43));
        double _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _t64 = _t28 * Math.cosWithSin(_t61);
        double _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        UnsafeOpsHolder.U.putDouble(dest, ((_t16) * (_t64) + (_sp0 * _t51)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t24) * (_t64) + (_sp0 * _t52)));
        return slerp_unsafe_mulAdd_s3ae7ab55_2(dest, _t19, _t21, _t49, _t50, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s3ae7ab55_2(long dest, double _t19, double _t21, double _t49, double _t50, double _t64, double _sp0) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t21) * (_t64) + (_sp0 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double4OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t);
        Double4OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 32L), 0L, t);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, long other, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, other, t);
        return slerp_degenerate_unsafe_mulAdd(dest, src, other, t);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        double _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        double _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        double _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 5.048709793414476E-29) {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        double _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        double _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        double _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        double _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        double _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        double _t81 = ((_t80) * (_t43) + (_t72));
        double _t82 = ((_t80) * (_t45) + (_t73));
        double _t83 = ((_t80) * (_t47) + (_t74));
        double _t84 = ((_t80) * (_t49) + (_t75));
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 5.048709793414476E-29) {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((t) * (_othery - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((t) * (_otherz - _selfz) + (_selfz)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((t) * (_otherw - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        double _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t10));
        double _t16 = _selfx * _t11;
        double _t19 = _selfw * _t11;
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
        return slerp_unsafe_fma_sad5b3725_1(dest, src, otherX, otherY, otherZ, otherW, t, epsilon, _t14, _t16, _t19, _t21, _t24, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9), java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24))));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sad5b3725_1(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28, double _t31) {
        double _t40 = java.lang.Math.fma(otherW, _t14, -(_t31 * _t19));
        double _t41 = java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21));
        double _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        double _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        double _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        double _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        double _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        double _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        double _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > epsilon && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _t64 = _t28 * Math.cosWithSin(_t61);
        double _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        return slerp_unsafe_fma_sad5b3725_2(dest, _t19, _t21, _t49, _t50, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sad5b3725_2(long dest, double _t19, double _t21, double _t49, double _t50, double _t64, double _sp0) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        double _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t10));
        double _t16 = _selfx * _t11;
        double _t19 = _selfw * _t11;
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
        return slerp_unsafe_mulAdd_s683dbde_1(dest, src, otherX, otherY, otherZ, otherW, t, epsilon, _t14, _t16, _t19, _t21, _t24, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9), ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24)))))));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s683dbde_1(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28, double _t31) {
        double _t40 = ((otherW) * (_t14) - (_t31 * _t19));
        double _t41 = ((otherZ) * (_t14) - (_t31 * _t21));
        double _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        double _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        double _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        double _t49 = ((_t48) * (_t19) + (_t40));
        double _t50 = ((_t48) * (_t21) + (_t41));
        double _t51 = ((_t48) * (_t16) + (_t42));
        double _t52 = ((_t48) * (_t24) + (_t43));
        double _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _t64 = _t28 * Math.cosWithSin(_t61);
        double _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        UnsafeOpsHolder.U.putDouble(dest, ((_t16) * (_t64) + (_sp0 * _t51)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t24) * (_t64) + (_sp0 * _t52)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t21) * (_t64) + (_sp0 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double4OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        Double4OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, otherX, otherY, otherZ, otherW, t, epsilon);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_degenerate_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        double _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        double _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        double _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        double _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        double _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        double _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        double _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        double _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        double _t81 = ((_t80) * (_t43) + (_t72));
        double _t82 = ((_t80) * (_t45) + (_t73));
        double _t83 = ((_t80) * (_t47) + (_t74));
        double _t84 = ((_t80) * (_t49) + (_t75));
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((t) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((t) * (otherZ - _selfz) + (_selfz)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((t) * (otherW - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long other, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, other, t, epsilon);
        return slerp_unsafe_mulAdd(dest, src, other, t, epsilon);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, long other, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        double _t10 = java.lang.Math.fma(_otherw, _otherw, java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        return slerp_unsafe_fma_sa9ddb549_1(dest, src, other, t, epsilon, _otherx, _othery, _otherz, _otherw, (1.0 / java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sa9ddb549_1(long dest, long src, long other, double t, double epsilon, double _otherx, double _othery, double _otherz, double _otherw, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28) {
        double _t31 = java.lang.Math.fma(_otherw * _t14, _t19, java.lang.Math.fma(_otherz * _t14, _t21, java.lang.Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        double _t40 = java.lang.Math.fma(_otherw, _t14, -(_t31 * _t19));
        double _t41 = java.lang.Math.fma(_otherz, _t14, -(_t31 * _t21));
        double _t42 = java.lang.Math.fma(_otherx, _t14, -(_t31 * _t16));
        double _t43 = java.lang.Math.fma(_othery, _t14, -(_t31 * _t24));
        double _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        double _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        double _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        double _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        double _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > epsilon && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        return slerp_unsafe_fma_sa9ddb549_2(dest, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sa9ddb549_2(long dest, double _t16, double _t19, double _t21, double _t24, double _t49, double _t50, double _t51, double _t52, double _t64, double _sp0) {
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, long other, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        double _t10 = ((_otherw) * (_otherw) + (((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))))));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        return slerp_unsafe_mulAdd_s7aa861a6_1(dest, src, other, t, epsilon, _otherx, _othery, _otherz, _otherw, (1.0 / java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s7aa861a6_1(long dest, long src, long other, double t, double epsilon, double _otherx, double _othery, double _otherz, double _otherw, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28) {
        double _t31 = ((_otherw * _t14) * (_t19) + (((_otherz * _t14) * (_t21) + (((_otherx * _t14) * (_t16) + (_othery * _t14 * _t24))))));
        double _t40 = ((_otherw) * (_t14) - (_t31 * _t19));
        double _t41 = ((_otherz) * (_t14) - (_t31 * _t21));
        double _t42 = ((_otherx) * (_t14) - (_t31 * _t16));
        double _t43 = ((_othery) * (_t14) - (_t31 * _t24));
        double _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        double _t49 = ((_t48) * (_t19) + (_t40));
        double _t50 = ((_t48) * (_t21) + (_t41));
        double _t51 = ((_t48) * (_t16) + (_t42));
        double _t52 = ((_t48) * (_t24) + (_t43));
        double _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _t64 = _t28 * Math.cosWithSin(_t61);
        double _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        UnsafeOpsHolder.U.putDouble(dest, ((_t16) * (_t64) + (_sp0 * _t51)));
        return slerp_unsafe_mulAdd_s7aa861a6_2(dest, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s7aa861a6_2(long dest, double _t19, double _t21, double _t24, double _t49, double _t50, double _t52, double _t64, double _sp0) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t24) * (_t64) + (_sp0 * _t52)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t21) * (_t64) + (_sp0 * _t50)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, double t, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double4OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t, epsilon);
        Double4OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 32L), 0L, t, epsilon);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, long other, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, other, t, epsilon);
        return slerp_degenerate_unsafe_mulAdd(dest, src, other, t, epsilon);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, long other, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        double _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        double _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        double _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, long other, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        double _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        double _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        double _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        double _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        double _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        double _t81 = ((_t80) * (_t43) + (_t72));
        double _t82 = ((_t80) * (_t45) + (_t73));
        double _t83 = ((_t80) * (_t47) + (_t74));
        double _t84 = ((_t80) * (_t49) + (_t75));
        double _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t113 = _t60 * Math.sinWithCos(_t110);
        double _t114 = _t60 * Math.cosWithSin(_t110);
        double _t119, _t120, _t121, _t122;
        if (_t106 > 0.0) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((t) * (_othery - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((t) * (_otherz - _selfz) + (_selfz)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((t) * (_otherw - _selfw) + (_selfw)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(scalar, bX, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(scalar, bY, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(scalar, bZ, _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(scalar, bW, _selfw));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((scalar) * (bX) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((scalar) * (bY) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((scalar) * (bZ) + (_selfz)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((scalar) * (bW) + (_selfw)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, long b, double scalar) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_unsafe_mulAdd(dest, src, b, scalar);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _bx = UnsafeOpsHolder.U.getDouble(b);
        double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
        double _bz = UnsafeOpsHolder.U.getDouble(b + 16L);
        double _bw = UnsafeOpsHolder.U.getDouble(b + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(scalar, _bx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(scalar, _by, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(scalar, _bz, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(scalar, _bw, _selfw));
        return dest;
    }

    /** {@code addScaled_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long addScaled_unsafe_mulAdd(long dest, long src, long b, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _bx = UnsafeOpsHolder.U.getDouble(b);
        double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
        double _bz = UnsafeOpsHolder.U.getDouble(b + 16L);
        double _bw = UnsafeOpsHolder.U.getDouble(b + 24L);
        UnsafeOpsHolder.U.putDouble(dest, ((scalar) * (_bx) + (_selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((scalar) * (_by) + (_selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((scalar) * (_bz) + (_selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((scalar) * (_bw) + (_selfw)));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(bX, cX, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(bY, cY, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(bZ, cZ, _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(bW, cW, _selfw));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((bX) * (cX) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((bY) * (cY) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((bZ) * (cZ) + (_selfz)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((bW) * (cW) + (_selfw)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, long b, long c) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_unsafe_mulAdd(dest, src, b, c);
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
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_bx, _cx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_by, _cy, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_bz, _cz, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    /** {@code addScaled_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long addScaled_unsafe_mulAdd(long dest, long src, long b, long c) {
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
        UnsafeOpsHolder.U.putDouble(dest, ((_bx) * (_cx) + (_selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_by) * (_cy) + (_selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_bz) * (_cz) + (_selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_bw) * (_cw) + (_selfw)));
        return dest;
    }

    public static double angleBetween_unsafe(long src, double otherX, double otherY, double otherZ, double otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_unsafe_fma(src, otherX, otherY, otherZ, otherW);
        return angleBetween_unsafe_mulAdd(src, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_unsafe_fma(long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t6 = otherZ * -_selfw;
        double _t7 = otherY * -_selfw;
        double _t8 = -(otherY * _selfz);
        double _t9 = -otherX * _selfw;
        double _t10 = -otherX * _selfy;
        double _t11 = -otherX * _selfz;
        double _t24 = java.lang.Math.fma(otherW, _selfz, _t6) - java.lang.Math.fma(otherZ, _selfw, _t6);
        double _t25 = java.lang.Math.fma(otherW, _selfy, _t7) - java.lang.Math.fma(otherY, _selfw, _t7);
        double _t26 = java.lang.Math.fma(otherZ, _selfy, _t8) - java.lang.Math.fma(otherY, _selfz, _t8);
        double _t27 = java.lang.Math.fma(otherW, _selfx, _t9) - java.lang.Math.fma(otherX, _selfw, _t9);
        double _t28 = java.lang.Math.fma(otherY, _selfx, _t10) - java.lang.Math.fma(otherX, _selfy, _t10);
        double _t29 = java.lang.Math.fma(otherZ, _selfx, _t11) - java.lang.Math.fma(otherX, _selfz, _t11);
        return angleBetween_unsafe_fma_s1cfca1f8_1(src, otherX, otherY, otherZ, otherW, _selfx, _selfy, _selfz, _selfw, java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29))))));
    }

    /** Piece 2 of {@code angleBetween_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_unsafe_fma_s1cfca1f8_1(long src, double otherX, double otherY, double otherZ, double otherW, double _selfx, double _selfy, double _selfz, double _selfw, double _ct0) {
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY, otherZ, otherW);
        return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherW, _selfw, java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    /** {@code angleBetween_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_unsafe_mulAdd(long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t6 = otherZ * -_selfw;
        double _t7 = otherY * -_selfw;
        double _t8 = -(otherY * _selfz);
        double _t9 = -otherX * _selfw;
        double _t10 = -otherX * _selfy;
        double _t11 = -otherX * _selfz;
        double _t24 = ((otherW) * (_selfz) + (_t6)) - ((otherZ) * (_selfw) + (_t6));
        double _t25 = ((otherW) * (_selfy) + (_t7)) - ((otherY) * (_selfw) + (_t7));
        double _t26 = ((otherZ) * (_selfy) + (_t8)) - ((otherY) * (_selfz) + (_t8));
        double _t27 = ((otherW) * (_selfx) + (_t9)) - ((otherX) * (_selfw) + (_t9));
        double _t28 = ((otherY) * (_selfx) + (_t10)) - ((otherX) * (_selfy) + (_t10));
        double _t29 = ((otherZ) * (_selfx) + (_t11)) - ((otherX) * (_selfz) + (_t11));
        double _ct0 = ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))))))))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY, otherZ, otherW);
        return Math.atan2(java.lang.Math.sqrt(_ct0), ((otherW) * (_selfw) + (((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))))));
    }

    public static double angleBetween_degenerate(long src, double otherX, double otherY, double otherZ, double otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double4OpsKernelsAddress.angleBetween_degenerate_unsafe(src, otherX, otherY, otherZ, otherW);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && VirtualMemoryHolder.virtualMemory().asSlice(src, 32L).isNative()) return Double4OpsKernelsSegment.angleBetween_degenerate_unsafe(VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, otherX, otherY, otherZ, otherW);
        return Double4OpsKernelsSegment.angleBetween_degenerate_api(VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_degenerate_unsafe(long src, double otherX, double otherY, double otherZ, double otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_unsafe_fma(src, otherX, otherY, otherZ, otherW);
        return angleBetween_degenerate_unsafe_mulAdd(src, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_unsafe_fma(long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = otherW * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = otherZ * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = otherY * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = otherX * _t6;
        double _t23 = _selfy * _t7;
        double _t30 = _t18 * -_t19;
        double _t31 = -_t22 * _t23;
        double _t32 = -_t22 * _t17;
        double _t33 = _t22 * -_t19;
        double _t34 = -(_t20 * _t17);
        return angleBetween_degenerate_unsafe_fma_s8b67c38d_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32), java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33), java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34));
    }

    /** Piece 2 of {@code angleBetween_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_unsafe_fma_s8b67c38d_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t35, double _t48, double _t49, double _t50, double _t51, double _t52) {
        double _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        double _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        double _t70 = _t48 * _t63;
        double _t71 = _t53 * _t63;
        double _t72 = _t52 * _t63;
        double _t73 = _t51 * _t63;
        double _t74 = _t49 * _t63;
        double _t75 = _t50 * _t63;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** {@code angleBetween_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_unsafe_mulAdd(long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = otherW * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = otherZ * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = otherY * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = otherX * _t6;
        double _t23 = _selfy * _t7;
        double _t30 = _t18 * -_t19;
        double _t31 = -_t22 * _t23;
        double _t32 = -_t22 * _t17;
        double _t33 = _t22 * -_t19;
        double _t34 = -(_t20 * _t17);
        return angleBetween_degenerate_unsafe_mulAdd_sb238156c_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t20 * -_t19, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32)), ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33)), ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_unsafe_mulAdd_sb238156c_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t35, double _t48, double _t49, double _t50, double _t51, double _t52) {
        double _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        double _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        double _t70 = _t48 * _t63;
        double _t71 = _t53 * _t63;
        double _t72 = _t52 * _t63;
        double _t73 = _t51 * _t63;
        double _t74 = _t49 * _t63;
        double _t75 = _t50 * _t63;
        return Math.atan2(java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static double angleBetween_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_unsafe_fma(src, other);
        return angleBetween_unsafe_mulAdd(src, other);
    }

    /** {@code angleBetween_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_unsafe_fma(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t6 = _otherz * -_selfw;
        double _t7 = _othery * -_selfw;
        double _t8 = -(_othery * _selfz);
        double _t9 = -_otherx * _selfw;
        double _t10 = -_otherx * _selfy;
        double _t11 = -_otherx * _selfz;
        return angleBetween_unsafe_fma_sfa0bc3c0_1(src, other, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, java.lang.Math.fma(_otherw, _selfz, _t6) - java.lang.Math.fma(_otherz, _selfw, _t6), java.lang.Math.fma(_otherw, _selfy, _t7) - java.lang.Math.fma(_othery, _selfw, _t7), java.lang.Math.fma(_otherz, _selfy, _t8) - java.lang.Math.fma(_othery, _selfz, _t8), java.lang.Math.fma(_otherw, _selfx, _t9) - java.lang.Math.fma(_otherx, _selfw, _t9), java.lang.Math.fma(_othery, _selfx, _t10) - java.lang.Math.fma(_otherx, _selfy, _t10), java.lang.Math.fma(_otherz, _selfx, _t11) - java.lang.Math.fma(_otherx, _selfz, _t11));
    }

    /** Piece 2 of {@code angleBetween_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_unsafe_fma_sfa0bc3c0_1(long src, long other, double _selfx, double _selfy, double _selfz, double _selfw, double _otherx, double _othery, double _otherz, double _otherw, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29) {
        double _ct0 = java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29)))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.angleBetween_degenerate(src, other);
        return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherw, _selfw, java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    /** {@code angleBetween_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_unsafe_mulAdd(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t6 = _otherz * -_selfw;
        double _t7 = _othery * -_selfw;
        double _t8 = -(_othery * _selfz);
        double _t9 = -_otherx * _selfw;
        double _t10 = -_otherx * _selfy;
        double _t11 = -_otherx * _selfz;
        return angleBetween_unsafe_mulAdd_s87ad6dbb_1(src, other, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, ((_otherw) * (_selfz) + (_t6)) - ((_otherz) * (_selfw) + (_t6)), ((_otherw) * (_selfy) + (_t7)) - ((_othery) * (_selfw) + (_t7)), ((_otherz) * (_selfy) + (_t8)) - ((_othery) * (_selfz) + (_t8)), ((_otherw) * (_selfx) + (_t9)) - ((_otherx) * (_selfw) + (_t9)), ((_othery) * (_selfx) + (_t10)) - ((_otherx) * (_selfy) + (_t10)), ((_otherz) * (_selfx) + (_t11)) - ((_otherx) * (_selfz) + (_t11)));
    }

    /** Piece 2 of {@code angleBetween_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_unsafe_mulAdd_s87ad6dbb_1(long src, long other, double _selfx, double _selfy, double _selfz, double _selfw, double _otherx, double _othery, double _otherz, double _otherw, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29) {
        double _ct0 = ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))))))))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsAddress.angleBetween_degenerate(src, other);
        return Math.atan2(java.lang.Math.sqrt(_ct0), ((_otherw) * (_selfw) + (((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))))));
    }

    public static double angleBetween_degenerate(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double4OpsKernelsAddress.angleBetween_degenerate_unsafe(src, other);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && VirtualMemoryHolder.virtualMemory().asSlice(src, 32L).isNative() && VirtualMemoryHolder.virtualMemory().asSlice(other, 32L).isNative()) return Double4OpsKernelsSegment.angleBetween_degenerate_unsafe(VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 32L), 0L);
        return Double4OpsKernelsSegment.angleBetween_degenerate_api(VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 32L), 0L);
    }

    public static double angleBetween_degenerate_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_unsafe_fma(src, other);
        return angleBetween_degenerate_unsafe_mulAdd(src, other);
    }

    /** {@code angleBetween_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_unsafe_fma(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = _otherw * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = _otherz * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = _othery * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = _otherx * _t6;
        double _t23 = _selfy * _t7;
        double _t30 = _t18 * -_t19;
        double _t31 = -_t22 * _t23;
        return angleBetween_degenerate_unsafe_fma_s1fa64b4d_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -_t22 * _t17, _t22 * -_t19, -(_t20 * _t17), _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31));
    }

    /** Piece 2 of {@code angleBetween_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_unsafe_fma_s1fa64b4d_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t32, double _t33, double _t34, double _t35, double _t48, double _t49) {
        double _t50 = java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32);
        double _t51 = java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33);
        double _t52 = java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34);
        double _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        double _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        double _t70 = _t48 * _t63;
        double _t71 = _t53 * _t63;
        double _t72 = _t52 * _t63;
        double _t73 = _t51 * _t63;
        double _t74 = _t49 * _t63;
        double _t75 = _t50 * _t63;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** {@code angleBetween_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_unsafe_mulAdd(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = _otherw * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = _otherz * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = _othery * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = _otherx * _t6;
        double _t23 = _selfy * _t7;
        double _t30 = _t18 * -_t19;
        double _t31 = -_t22 * _t23;
        return angleBetween_degenerate_unsafe_mulAdd_sb73b015c_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -_t22 * _t17, _t22 * -_t19, -(_t20 * _t17), _t20 * -_t19, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_unsafe_mulAdd_sb73b015c_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t32, double _t33, double _t34, double _t35, double _t48, double _t49) {
        double _t50 = ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32));
        double _t51 = ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33));
        double _t52 = ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34));
        double _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        double _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        double _t70 = _t48 * _t63;
        double _t71 = _t53 * _t63;
        double _t72 = _t52 * _t63;
        double _t73 = _t51 * _t63;
        double _t74 = _t49 * _t63;
        double _t75 = _t50 * _t63;
        return Math.atan2(java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
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
        UnsafeOpsHolder.U.putDouble(dest, Math.min(Math.max(_selfx, min), max));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(Math.max(_selfy, min), max));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(Math.max(_selfz, min), max));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.min(Math.max(_selfw, min), max));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, double minX, double minY, double minZ, double minW, double maxX, double maxY, double maxZ, double maxW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.min(Math.max(_selfx, minX), maxX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(Math.max(_selfy, minY), maxY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(Math.max(_selfz, minZ), maxZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.min(Math.max(_selfw, minW), maxW));
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
        UnsafeOpsHolder.U.putDouble(dest, Math.min(Math.max(_selfx, _minx), _maxx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(Math.max(_selfy, _miny), _maxy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(Math.max(_selfz, _minz), _maxz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.min(Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static double compAdd_unsafe(long src) {
        return UnsafeOpsHolder.U.getDouble(src + 24L) + (UnsafeOpsHolder.U.getDouble(src + 16L) + (UnsafeOpsHolder.U.getDouble(src) + UnsafeOpsHolder.U.getDouble(src + 8L)));
    }

    public static double compMax_unsafe(long src) {
        return Math.max(Math.max(Math.max(UnsafeOpsHolder.U.getDouble(src), UnsafeOpsHolder.U.getDouble(src + 8L)), UnsafeOpsHolder.U.getDouble(src + 16L)), UnsafeOpsHolder.U.getDouble(src + 24L));
    }

    public static double compMin_unsafe(long src) {
        return Math.min(Math.min(Math.min(UnsafeOpsHolder.U.getDouble(src), UnsafeOpsHolder.U.getDouble(src + 8L)), UnsafeOpsHolder.U.getDouble(src + 16L)), UnsafeOpsHolder.U.getDouble(src + 24L));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cross_unsafe_fma(dest, src, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return cross_unsafe_mulAdd(dest, src, vX, vY, vZ, vW, wX, wY, wZ, wW);
    }

    /** {@code cross_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cross_unsafe_fma(long dest, long src, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t6 = -vZ * wY;
        double _t7 = -vW * wZ;
        double _t8 = -vW * wY;
        double _t9 = -vZ * wX;
        double _t10 = -vW * wX;
        double _t11 = -(vY * wX);
        double _t24 = java.lang.Math.fma(vY, wZ, _t6) - java.lang.Math.fma(vZ, wY, _t6);
        double _t25 = java.lang.Math.fma(vZ, wW, _t7) - java.lang.Math.fma(vW, wZ, _t7);
        double _t26 = java.lang.Math.fma(vY, wW, _t8) - java.lang.Math.fma(vW, wY, _t8);
        double _t27 = java.lang.Math.fma(vX, wZ, _t9) - java.lang.Math.fma(vZ, wX, _t9);
        double _t28 = java.lang.Math.fma(vX, wW, _t10) - java.lang.Math.fma(vW, wX, _t10);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfw, _t24, java.lang.Math.fma(_selfy, _t25, -_selfz * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-_selfw, _t27, java.lang.Math.fma(_selfz, _t28, -_selfx * _t25)));
        return cross_unsafe_fma_s5084e4df_1(dest, _selfx, _selfy, _selfz, _selfw, _t24, _t26, _t27, _t28, java.lang.Math.fma(vX, wY, _t11) - java.lang.Math.fma(vY, wX, _t11));
    }

    /** Piece 2 of {@code cross_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long cross_unsafe_fma_s5084e4df_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t24, double _t26, double _t27, double _t28, double _t29) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfw, _t29, java.lang.Math.fma(_selfx, _t26, -(_selfy * _t28))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(-_selfz, _t29, java.lang.Math.fma(_selfy, _t27, -_selfx * _t24)));
        return dest;
    }

    /** {@code cross_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cross_unsafe_mulAdd(long dest, long src, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t6 = -vZ * wY;
        double _t7 = -vW * wZ;
        double _t8 = -vW * wY;
        double _t9 = -vZ * wX;
        double _t10 = -vW * wX;
        double _t11 = -(vY * wX);
        double _t24 = ((vY) * (wZ) + (_t6)) - ((vZ) * (wY) + (_t6));
        double _t25 = ((vZ) * (wW) + (_t7)) - ((vW) * (wZ) + (_t7));
        double _t26 = ((vY) * (wW) + (_t8)) - ((vW) * (wY) + (_t8));
        double _t27 = ((vX) * (wZ) + (_t9)) - ((vZ) * (wX) + (_t9));
        double _t28 = ((vX) * (wW) + (_t10)) - ((vW) * (wX) + (_t10));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfw) * (_t24) + (((_selfy) * (_t25) - (_selfz * _t26)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((((_selfz) * (_t28) - (_selfx * _t25))) - (_selfw) * (_t27)));
        return cross_unsafe_mulAdd_sf21bd2e0_1(dest, _selfx, _selfy, _selfz, _selfw, _t24, _t26, _t27, _t28, ((vX) * (wY) + (_t11)) - ((vY) * (wX) + (_t11)));
    }

    /** Piece 2 of {@code cross_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long cross_unsafe_mulAdd_sf21bd2e0_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t24, double _t26, double _t27, double _t28, double _t29) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfw) * (_t29) + (((_selfx) * (_t26) - (_selfy * _t28)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((((_selfy) * (_t27) - (_selfx * _t24))) - (_selfz) * (_t29)));
        return dest;
    }

    public static long cross_unsafe(long dest, long src, long v, long w) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cross_unsafe_fma(dest, src, v, w);
        return cross_unsafe_mulAdd(dest, src, v, w);
    }

    /** {@code cross_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cross_unsafe_fma(long dest, long src, long v, long w) {
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
        double _t6 = -_vz * _wy;
        double _t7 = -_vw * _wz;
        double _t8 = -_vw * _wy;
        double _t9 = -_vz * _wx;
        return cross_unsafe_fma_s7a3d5584_1(dest, _selfx, _selfy, _selfz, _selfw, _vx, _vy, _vw, _wx, _wy, _ww, -_vw * _wx, -(_vy * _wx), java.lang.Math.fma(_vy, _wz, _t6) - java.lang.Math.fma(_vz, _wy, _t6), java.lang.Math.fma(_vz, _ww, _t7) - java.lang.Math.fma(_vw, _wz, _t7), java.lang.Math.fma(_vy, _ww, _t8) - java.lang.Math.fma(_vw, _wy, _t8), java.lang.Math.fma(_vx, _wz, _t9) - java.lang.Math.fma(_vz, _wx, _t9));
    }

    /** Piece 2 of {@code cross_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long cross_unsafe_fma_s7a3d5584_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _vx, double _vy, double _vw, double _wx, double _wy, double _ww, double _t10, double _t11, double _t24, double _t25, double _t26, double _t27) {
        double _t28 = java.lang.Math.fma(_vx, _ww, _t10) - java.lang.Math.fma(_vw, _wx, _t10);
        double _t29 = java.lang.Math.fma(_vx, _wy, _t11) - java.lang.Math.fma(_vy, _wx, _t11);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfw, _t24, java.lang.Math.fma(_selfy, _t25, -_selfz * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-_selfw, _t27, java.lang.Math.fma(_selfz, _t28, -_selfx * _t25)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfw, _t29, java.lang.Math.fma(_selfx, _t26, -(_selfy * _t28))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(-_selfz, _t29, java.lang.Math.fma(_selfy, _t27, -_selfx * _t24)));
        return dest;
    }

    /** {@code cross_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cross_unsafe_mulAdd(long dest, long src, long v, long w) {
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
        double _t6 = -_vz * _wy;
        double _t7 = -_vw * _wz;
        double _t8 = -_vw * _wy;
        double _t9 = -_vz * _wx;
        double _t10 = -_vw * _wx;
        return cross_unsafe_mulAdd_seb635173_1(dest, _selfx, _selfy, _selfz, _selfw, _vx, _vy, _wx, _wy, -(_vy * _wx), ((_vy) * (_wz) + (_t6)) - ((_vz) * (_wy) + (_t6)), ((_vz) * (_ww) + (_t7)) - ((_vw) * (_wz) + (_t7)), ((_vy) * (_ww) + (_t8)) - ((_vw) * (_wy) + (_t8)), ((_vx) * (_wz) + (_t9)) - ((_vz) * (_wx) + (_t9)), ((_vx) * (_ww) + (_t10)) - ((_vw) * (_wx) + (_t10)));
    }

    /** Piece 2 of {@code cross_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long cross_unsafe_mulAdd_seb635173_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _vx, double _vy, double _wx, double _wy, double _t11, double _t24, double _t25, double _t26, double _t27, double _t28) {
        double _t29 = ((_vx) * (_wy) + (_t11)) - ((_vy) * (_wx) + (_t11));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfw) * (_t24) + (((_selfy) * (_t25) - (_selfz * _t26)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((((_selfz) * (_t28) - (_selfx * _t25))) - (_selfw) * (_t27)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfw) * (_t29) + (((_selfx) * (_t26) - (_selfy * _t28)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((((_selfy) * (_t27) - (_selfx * _t24))) - (_selfz) * (_t29)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 24L) - otherW;
            double _t1 = UnsafeOpsHolder.U.getDouble(src + 16L) - otherZ;
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - otherX;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - otherY;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
        } else {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 24L) - otherW;
            double _t1 = UnsafeOpsHolder.U.getDouble(src + 16L) - otherZ;
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - otherX;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - otherY;
            return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
        }
    }

    public static double distance_unsafe(long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return distance_unsafe_mulAdd(src, other);
        double _t0 = UnsafeOpsHolder.U.getDouble(src + 24L) - UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t1 = UnsafeOpsHolder.U.getDouble(src + 16L) - UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t2 = UnsafeOpsHolder.U.getDouble(src) - UnsafeOpsHolder.U.getDouble(other);
        double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - UnsafeOpsHolder.U.getDouble(other + 8L);
        return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
    }

    /** {@code distance_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double distance_unsafe_mulAdd(long src, long other) {
        double _t0 = UnsafeOpsHolder.U.getDouble(src + 24L) - UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t1 = UnsafeOpsHolder.U.getDouble(src + 16L) - UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t2 = UnsafeOpsHolder.U.getDouble(src) - UnsafeOpsHolder.U.getDouble(other);
        double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - UnsafeOpsHolder.U.getDouble(other + 8L);
        return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
    }

    public static double distanceSquared_unsafe(long src, double otherX, double otherY, double otherZ, double otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 24L) - otherW;
            double _t1 = UnsafeOpsHolder.U.getDouble(src + 16L) - otherZ;
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - otherX;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 24L) - otherW;
            double _t1 = UnsafeOpsHolder.U.getDouble(src + 16L) - otherZ;
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - otherX;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
        }
    }

    public static double distanceSquared_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 24L) - UnsafeOpsHolder.U.getDouble(other + 24L);
            double _t1 = UnsafeOpsHolder.U.getDouble(src + 16L) - UnsafeOpsHolder.U.getDouble(other + 16L);
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - UnsafeOpsHolder.U.getDouble(other);
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - UnsafeOpsHolder.U.getDouble(other + 8L);
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 24L) - UnsafeOpsHolder.U.getDouble(other + 24L);
            double _t1 = UnsafeOpsHolder.U.getDouble(src + 16L) - UnsafeOpsHolder.U.getDouble(other + 16L);
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - UnsafeOpsHolder.U.getDouble(other);
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - UnsafeOpsHolder.U.getDouble(other + 8L);
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
        }
    }

    public static double dot_unsafe(long src, double otherX, double otherY, double otherZ, double otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherW, UnsafeOpsHolder.U.getDouble(src + 24L), java.lang.Math.fma(otherZ, UnsafeOpsHolder.U.getDouble(src + 16L), java.lang.Math.fma(otherX, UnsafeOpsHolder.U.getDouble(src), otherY * UnsafeOpsHolder.U.getDouble(src + 8L))));
        } else {
            return ((otherW) * (UnsafeOpsHolder.U.getDouble(src + 24L)) + (((otherZ) * (UnsafeOpsHolder.U.getDouble(src + 16L)) + (((otherX) * (UnsafeOpsHolder.U.getDouble(src)) + (otherY * UnsafeOpsHolder.U.getDouble(src + 8L)))))));
        }
    }

    public static double dot_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(other + 24L), UnsafeOpsHolder.U.getDouble(src + 24L), java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(other + 16L), UnsafeOpsHolder.U.getDouble(src + 16L), java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(other), UnsafeOpsHolder.U.getDouble(src), UnsafeOpsHolder.U.getDouble(other + 8L) * UnsafeOpsHolder.U.getDouble(src + 8L))));
        } else {
            return ((UnsafeOpsHolder.U.getDouble(other + 24L)) * (UnsafeOpsHolder.U.getDouble(src + 24L)) + (((UnsafeOpsHolder.U.getDouble(other + 16L)) * (UnsafeOpsHolder.U.getDouble(src + 16L)) + (((UnsafeOpsHolder.U.getDouble(other)) * (UnsafeOpsHolder.U.getDouble(src)) + (UnsafeOpsHolder.U.getDouble(other + 8L) * UnsafeOpsHolder.U.getDouble(src + 8L)))))));
        }
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0 ? 1.0 : -1.0;
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t4);
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0 ? 1.0 : -1.0;
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t4);
            return dest;
        }
    }

    public static long faceforward_unsafe(long dest, long src, long I, long Nref) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_unsafe_mulAdd(dest, src, I, Nref);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(I + 24L), UnsafeOpsHolder.U.getDouble(Nref + 24L), java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(I + 16L), UnsafeOpsHolder.U.getDouble(Nref + 16L), java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(I), UnsafeOpsHolder.U.getDouble(Nref), UnsafeOpsHolder.U.getDouble(I + 8L) * UnsafeOpsHolder.U.getDouble(Nref + 8L)))) < 0.0 ? 1.0 : -1.0;
        UnsafeOpsHolder.U.putDouble(dest, _selfx * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t4);
        return dest;
    }

    /** {@code faceforward_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long faceforward_unsafe_mulAdd(long dest, long src, long I, long Nref) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = ((UnsafeOpsHolder.U.getDouble(I + 24L)) * (UnsafeOpsHolder.U.getDouble(Nref + 24L)) + (((UnsafeOpsHolder.U.getDouble(I + 16L)) * (UnsafeOpsHolder.U.getDouble(Nref + 16L)) + (((UnsafeOpsHolder.U.getDouble(I)) * (UnsafeOpsHolder.U.getDouble(Nref)) + (UnsafeOpsHolder.U.getDouble(I + 8L) * UnsafeOpsHolder.U.getDouble(Nref + 8L))))))) < 0.0 ? 1.0 : -1.0;
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
        UnsafeOpsHolder.U.putDouble(dest, Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(_selfz - Math.floor(_selfz), 0.9999999999999999));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.min(_selfw - Math.floor(_selfw), 0.9999999999999999));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            return java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))));
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            return java.lang.Math.sqrt(((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))))));
        }
    }

    public static double lengthSquared_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            return java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            return ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        }
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
        UnsafeOpsHolder.U.putDouble(dest, Math.max(_selfx, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.max(_selfy, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.max(_selfz, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.max(_selfw, scalar));
        return dest;
    }

    public static long max_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.max(_selfx, otherX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.max(_selfy, otherY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.max(_selfz, otherZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.max(_selfw, otherW));
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
        UnsafeOpsHolder.U.putDouble(dest, Math.max(_selfx, _otherx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.max(_selfy, _othery));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.max(_selfz, _otherz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.max(_selfw, _otherw));
        return dest;
    }

    public static long min_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.min(_selfx, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(_selfy, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(_selfz, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.min(_selfw, scalar));
        return dest;
    }

    public static long min_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.min(_selfx, otherX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(_selfy, otherY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(_selfz, otherZ));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.min(_selfw, otherW));
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
        UnsafeOpsHolder.U.putDouble(dest, Math.min(_selfx, _otherx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(_selfy, _othery));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(_selfz, _otherz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.min(_selfw, _otherw));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalize_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
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

    /** {@code normalize_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normalize_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizeMul_unsafe_mulAdd(dest, src, length);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
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

    /** {@code normalizeMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normalizeMul_unsafe_mulAdd(long dest, long src, double length) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_unsafe_mulAdd(dest, src, ontoX, ontoY, ontoZ, ontoW);
        double _t9 = java.lang.Math.fma(ontoW, UnsafeOpsHolder.U.getDouble(src + 24L), java.lang.Math.fma(ontoZ, UnsafeOpsHolder.U.getDouble(src + 16L), java.lang.Math.fma(ontoX, UnsafeOpsHolder.U.getDouble(src), ontoY * UnsafeOpsHolder.U.getDouble(src + 8L)))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
        UnsafeOpsHolder.U.putDouble(dest, ontoX * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ontoY * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ontoZ * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ontoW * _t9);
        return dest;
    }

    /** {@code project_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long project_unsafe_mulAdd(long dest, long src, double ontoX, double ontoY, double ontoZ, double ontoW) {
        double _t9 = ((ontoW) * (UnsafeOpsHolder.U.getDouble(src + 24L)) + (((ontoZ) * (UnsafeOpsHolder.U.getDouble(src + 16L)) + (((ontoX) * (UnsafeOpsHolder.U.getDouble(src)) + (ontoY * UnsafeOpsHolder.U.getDouble(src + 8L))))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
        UnsafeOpsHolder.U.putDouble(dest, ontoX * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ontoY * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ontoZ * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ontoW * _t9);
        return dest;
    }

    public static long project_unsafe(long dest, long src, long onto) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_unsafe_mulAdd(dest, src, onto);
        double _ontox = UnsafeOpsHolder.U.getDouble(onto);
        double _ontoy = UnsafeOpsHolder.U.getDouble(onto + 8L);
        double _ontoz = UnsafeOpsHolder.U.getDouble(onto + 16L);
        double _ontow = UnsafeOpsHolder.U.getDouble(onto + 24L);
        double _t9 = java.lang.Math.fma(_ontow, UnsafeOpsHolder.U.getDouble(src + 24L), java.lang.Math.fma(_ontoz, UnsafeOpsHolder.U.getDouble(src + 16L), java.lang.Math.fma(_ontox, UnsafeOpsHolder.U.getDouble(src), _ontoy * UnsafeOpsHolder.U.getDouble(src + 8L)))) / java.lang.Math.fma(_ontow, _ontow, java.lang.Math.fma(_ontoz, _ontoz, java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        UnsafeOpsHolder.U.putDouble(dest, _ontox * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _ontoy * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _ontoz * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _ontow * _t9);
        return dest;
    }

    /** {@code project_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long project_unsafe_mulAdd(long dest, long src, long onto) {
        double _ontox = UnsafeOpsHolder.U.getDouble(onto);
        double _ontoy = UnsafeOpsHolder.U.getDouble(onto + 8L);
        double _ontoz = UnsafeOpsHolder.U.getDouble(onto + 16L);
        double _ontow = UnsafeOpsHolder.U.getDouble(onto + 24L);
        double _t9 = ((_ontow) * (UnsafeOpsHolder.U.getDouble(src + 24L)) + (((_ontoz) * (UnsafeOpsHolder.U.getDouble(src + 16L)) + (((_ontox) * (UnsafeOpsHolder.U.getDouble(src)) + (_ontoy * UnsafeOpsHolder.U.getDouble(src + 8L))))))) / ((_ontow) * (_ontow) + (((_ontoz) * (_ontoz) + (((_ontox) * (_ontox) + (_ontoy * _ontoy))))));
        UnsafeOpsHolder.U.putDouble(dest, _ontox * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _ontoy * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _ontoz * _t9);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _ontow * _t9);
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, double normalX, double normalY, double normalZ, double normalW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return projectOnPlane_unsafe_mulAdd(dest, src, normalX, normalY, normalZ, normalW);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-normalX, _t3, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-normalY, _t3, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-normalZ, _t3, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(-normalW, _t3, _selfw));
        return dest;
    }

    /** {@code projectOnPlane_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long projectOnPlane_unsafe_mulAdd(long dest, long src, double normalX, double normalY, double normalZ, double normalW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) - (normalX) * (_t3)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) - (normalY) * (_t3)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) - (normalZ) * (_t3)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) - (normalW) * (_t3)));
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, long normal) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return projectOnPlane_unsafe_mulAdd(dest, src, normal);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _normalw = UnsafeOpsHolder.U.getDouble(normal + 24L);
        double _t3 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-_normalx, _t3, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-_normaly, _t3, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-_normalz, _t3, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    /** {@code projectOnPlane_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long projectOnPlane_unsafe_mulAdd(long dest, long src, long normal) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _normalw = UnsafeOpsHolder.U.getDouble(normal + 24L);
        double _t3 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) - (_normalx) * (_t3)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) - (_normaly) * (_t3)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) - (_normalz) * (_t3)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) - (_normalw) * (_t3)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_unsafe_mulAdd(dest, src, normalX, normalY, normalZ, normalW);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = 2.0 * java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-normalX, _t4, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-normalY, _t4, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-normalZ, _t4, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(-normalW, _t4, _selfw));
        return dest;
    }

    /** {@code reflect_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long reflect_unsafe_mulAdd(long dest, long src, double normalX, double normalY, double normalZ, double normalW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = 2.0 * ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) - (normalX) * (_t4)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) - (normalY) * (_t4)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) - (normalZ) * (_t4)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) - (normalW) * (_t4)));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, long normal) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_unsafe_mulAdd(dest, src, normal);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _normalw = UnsafeOpsHolder.U.getDouble(normal + 24L);
        double _t4 = 2.0 * java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-_normalx, _t4, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-_normaly, _t4, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-_normalz, _t4, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    /** {@code reflect_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long reflect_unsafe_mulAdd(long dest, long src, long normal) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _normalw = UnsafeOpsHolder.U.getDouble(normal + 24L);
        double _t4 = 2.0 * ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) - (_normalx) * (_t4)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) - (_normaly) * (_t4)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) - (_normalz) * (_t4)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) - (_normalw) * (_t4)));
        return dest;
    }

    public static long refract_unsafe(long dest, long src, double normalX, double normalY, double normalZ, double normalW, double eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_unsafe_mulAdd(dest, src, normalX, normalY, normalZ, normalW, eta);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        double _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0), eta * eta, 1.0);
        double _t11 = java.lang.Math.fma(eta, _t4, java.lang.Math.sqrt(Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(eta, _selfx, -(normalX * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(eta, _selfy, -(normalY * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(eta, _selfz, -(normalZ * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    /** {@code refract_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long refract_unsafe_mulAdd(long dest, long src, double normalX, double normalY, double normalZ, double normalW, double eta) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        double _t8 = ((((_t4) * (_t4) - (1.0))) * (eta * eta) + (1.0));
        double _t11 = ((eta) * (_t4) + (java.lang.Math.sqrt(Math.max(0.0, _t8))));
        if (_t8 >= 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, ((eta) * (_selfx) - (normalX * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((eta) * (_selfy) - (normalY * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((eta) * (_selfz) - (normalZ * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((eta) * (_selfw) - (normalW * _t11)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long refract_unsafe(long dest, long src, long normal, double eta) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return refract_unsafe_fma(dest, src, normal, eta);
        return refract_unsafe_mulAdd(dest, src, normal, eta);
    }

    /** {@code refract_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long refract_unsafe_fma(long dest, long src, long normal, double eta) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _normalw = UnsafeOpsHolder.U.getDouble(normal + 24L);
        double _t4 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        double _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0), eta * eta, 1.0);
        return refract_unsafe_fma_se3ec4226_1(dest, eta, _selfx, _selfy, _selfz, _selfw, _normalx, _normaly, _normalz, _normalw, _t8, java.lang.Math.fma(eta, _t4, java.lang.Math.sqrt(Math.max(0.0, _t8))));
    }

    /** Piece 2 of {@code refract_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long refract_unsafe_fma_se3ec4226_1(long dest, double eta, double _selfx, double _selfy, double _selfz, double _selfw, double _normalx, double _normaly, double _normalz, double _normalw, double _t8, double _t11) {
        if (_t8 >= 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(eta, _selfx, -(_normalx * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(eta, _selfy, -(_normaly * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(eta, _selfz, -(_normalz * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    /** {@code refract_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long refract_unsafe_mulAdd(long dest, long src, long normal, double eta) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _normalw = UnsafeOpsHolder.U.getDouble(normal + 24L);
        double _t4 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        double _t8 = ((((_t4) * (_t4) - (1.0))) * (eta * eta) + (1.0));
        double _t11 = ((eta) * (_t4) + (java.lang.Math.sqrt(Math.max(0.0, _t8))));
        if (_t8 >= 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, ((eta) * (_selfx) - (_normalx * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((eta) * (_selfy) - (_normaly * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((eta) * (_selfz) - (_normalz * _t11)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((eta) * (_selfw) - (_normalw * _t11)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_mulAdd(dest, src, edge0, edge1);
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t13 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - edge0) * _t0_inv));
        double _t14 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - edge0) * _t0_inv));
        double _t15 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - edge0) * _t0_inv));
        double _t16 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 24L) - edge0) * _t0_inv));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-2.0, _t15, 3.0) * _t15 * _t15);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, double edge0, double edge1) {
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t13 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - edge0) * _t0_inv));
        double _t14 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - edge0) * _t0_inv));
        double _t15 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - edge0) * _t0_inv));
        double _t16 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 24L) - edge0) * _t0_inv));
        UnsafeOpsHolder.U.putDouble(dest, ((3.0) - (2.0) * (_t13)) * _t13 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((3.0) - (2.0) * (_t14)) * _t14 * _t14);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((3.0) - (2.0) * (_t15)) * _t15 * _t15);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((3.0) - (2.0) * (_t16)) * _t16 * _t16);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_mulAdd(dest, src, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        double _t16 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - edge0X) / (edge1X - edge0X)));
        double _t17 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - edge0Y) / (edge1Y - edge0Y)));
        double _t18 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - edge0Z) / (edge1Z - edge0Z)));
        double _t19 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 24L) - edge0W) / (edge1W - edge0W)));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-2.0, _t17, 3.0) * _t17 * _t17);
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-2.0, _t18, 3.0) * _t18 * _t18);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(-2.0, _t19, 3.0) * _t19 * _t19);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        double _t16 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - edge0X) / (edge1X - edge0X)));
        double _t17 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - edge0Y) / (edge1Y - edge0Y)));
        double _t18 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - edge0Z) / (edge1Z - edge0Z)));
        double _t19 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 24L) - edge0W) / (edge1W - edge0W)));
        UnsafeOpsHolder.U.putDouble(dest, ((3.0) - (2.0) * (_t16)) * _t16 * _t16);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((3.0) - (2.0) * (_t17)) * _t17 * _t17);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((3.0) - (2.0) * (_t18)) * _t18 * _t18);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((3.0) - (2.0) * (_t19)) * _t19 * _t19);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, long edge0, long edge1) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_fma(dest, src, edge0, edge1);
        return smoothstep_unsafe_mulAdd(dest, src, edge0, edge1);
    }

    /** {@code smoothstep_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_fma(long dest, long src, long edge0, long edge1) {
        double _edge0x = UnsafeOpsHolder.U.getDouble(edge0);
        double _edge0y = UnsafeOpsHolder.U.getDouble(edge0 + 8L);
        double _edge0z = UnsafeOpsHolder.U.getDouble(edge0 + 16L);
        double _edge0w = UnsafeOpsHolder.U.getDouble(edge0 + 24L);
        return smoothstep_unsafe_fma_s704d23e_1(dest, src, edge1, _edge0x, _edge0y, _edge0z, _edge0w);
    }

    /** Piece 2 of {@code smoothstep_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long smoothstep_unsafe_fma_s704d23e_1(long dest, long src, long edge1, double _edge0x, double _edge0y, double _edge0z, double _edge0w) {
        double _t16 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - _edge0x) / (UnsafeOpsHolder.U.getDouble(edge1) - _edge0x)));
        double _t17 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - _edge0y) / (UnsafeOpsHolder.U.getDouble(edge1 + 8L) - _edge0y)));
        double _t18 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - _edge0z) / (UnsafeOpsHolder.U.getDouble(edge1 + 16L) - _edge0z)));
        double _t19 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 24L) - _edge0w) / (UnsafeOpsHolder.U.getDouble(edge1 + 24L) - _edge0w)));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-2.0, _t17, 3.0) * _t17 * _t17);
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-2.0, _t18, 3.0) * _t18 * _t18);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(-2.0, _t19, 3.0) * _t19 * _t19);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, long edge0, long edge1) {
        double _edge0x = UnsafeOpsHolder.U.getDouble(edge0);
        double _edge0y = UnsafeOpsHolder.U.getDouble(edge0 + 8L);
        double _edge0z = UnsafeOpsHolder.U.getDouble(edge0 + 16L);
        double _edge0w = UnsafeOpsHolder.U.getDouble(edge0 + 24L);
        return smoothstep_unsafe_mulAdd_sf1e925cd_1(dest, src, edge1, _edge0x, _edge0y, _edge0z, _edge0w);
    }

    /** Piece 2 of {@code smoothstep_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long smoothstep_unsafe_mulAdd_sf1e925cd_1(long dest, long src, long edge1, double _edge0x, double _edge0y, double _edge0z, double _edge0w) {
        double _t16 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - _edge0x) / (UnsafeOpsHolder.U.getDouble(edge1) - _edge0x)));
        double _t17 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - _edge0y) / (UnsafeOpsHolder.U.getDouble(edge1 + 8L) - _edge0y)));
        double _t18 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - _edge0z) / (UnsafeOpsHolder.U.getDouble(edge1 + 16L) - _edge0z)));
        double _t19 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 24L) - _edge0w) / (UnsafeOpsHolder.U.getDouble(edge1 + 24L) - _edge0w)));
        UnsafeOpsHolder.U.putDouble(dest, ((3.0) - (2.0) * (_t16)) * _t16 * _t16);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((3.0) - (2.0) * (_t17)) * _t17 * _t17);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((3.0) - (2.0) * (_t18)) * _t18 * _t18);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((3.0) - (2.0) * (_t19)) * _t19 * _t19);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_unsafe_fma(dest, src, mat);
        return preMul_unsafe_mulAdd(dest, src, mat);
    }

    /** {@code preMul_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_fma(long dest, long src, long mat) {
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
        return preMul_unsafe_fma_s29370ba_1(dest, mat, _selfx, _selfy, _selfz, _selfw, _mat00, _mat10, _mat20, _mat30, _mat01, _mat11, _mat21, _mat31, _mat02, _mat12, _mat22, _mat32, _mat03, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMul_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preMul_unsafe_fma_s29370ba_1(long dest, long mat, double _selfx, double _selfy, double _selfz, double _selfw, double _mat00, double _mat10, double _mat20, double _mat30, double _mat01, double _mat11, double _mat21, double _mat31, double _mat02, double _mat12, double _mat22, double _mat32, double _mat03, double _mat13, double _mat23) {
        double _mat33 = UnsafeOpsHolder.U.getDouble(mat + 120L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_mat03, _selfw, java.lang.Math.fma(_mat02, _selfz, java.lang.Math.fma(_mat00, _selfx, _mat01 * _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_mat13, _selfw, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_mat23, _selfw, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_mat33, _selfw, java.lang.Math.fma(_mat32, _selfz, java.lang.Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    /** {@code preMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_mulAdd(long dest, long src, long mat) {
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
        return preMul_unsafe_mulAdd_s5d8dd4bf_1(dest, mat, _selfx, _selfy, _selfz, _selfw, _mat00, _mat10, _mat20, _mat30, _mat01, _mat11, _mat21, _mat31, _mat02, _mat12, _mat22, _mat32, _mat03, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMul_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preMul_unsafe_mulAdd_s5d8dd4bf_1(long dest, long mat, double _selfx, double _selfy, double _selfz, double _selfw, double _mat00, double _mat10, double _mat20, double _mat30, double _mat01, double _mat11, double _mat21, double _mat31, double _mat02, double _mat12, double _mat22, double _mat32, double _mat03, double _mat13, double _mat23) {
        double _mat33 = UnsafeOpsHolder.U.getDouble(mat + 120L);
        UnsafeOpsHolder.U.putDouble(dest, ((_mat03) * (_selfw) + (((_mat02) * (_selfz) + (((_mat00) * (_selfx) + (_mat01 * _selfy)))))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_mat13) * (_selfw) + (((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_mat23) * (_selfw) + (((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_mat33) * (_selfw) + (((_mat32) * (_selfz) + (((_mat30) * (_selfx) + (_mat31 * _selfy)))))));
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, double quatX, double quatY, double quatZ, double quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_unsafe_mulAdd(dest, src, quatX, quatY, quatZ, quatW);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(quatX, _selfy, -(quatY * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(quatZ, _selfx, -(quatX * _selfz));
        double _t11 = 2.0 * java.lang.Math.fma(quatY, _selfz, -(quatZ * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, _selfz))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    /** {@code rotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_mulAdd(long dest, long src, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((quatX) * (_selfy) - (quatY * _selfx));
        double _t10 = 2.0 * ((quatZ) * (_selfx) - (quatX * _selfz));
        double _t11 = 2.0 * ((quatY) * (_selfz) - (quatZ * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, ((quatY) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatZ) * (_t10)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((quatZ) * (_t11) + (((((quatW) * (_t10) + (_selfy))) - (quatX) * (_t9)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfz))) - (quatY) * (_t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, long quat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_unsafe_mulAdd(dest, src, quat);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _quatx = UnsafeOpsHolder.U.getDouble(quat);
        double _quaty = UnsafeOpsHolder.U.getDouble(quat + 8L);
        double _quatz = UnsafeOpsHolder.U.getDouble(quat + 16L);
        double _quatw = UnsafeOpsHolder.U.getDouble(quat + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        double _t11 = 2.0 * java.lang.Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_quaty, _t9, java.lang.Math.fma(-_quatz, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_quatz, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quaty, _t11, java.lang.Math.fma(_quatw, _t9, _selfz))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    /** {@code rotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_mulAdd(long dest, long src, long quat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _quatx = UnsafeOpsHolder.U.getDouble(quat);
        double _quaty = UnsafeOpsHolder.U.getDouble(quat + 8L);
        double _quatz = UnsafeOpsHolder.U.getDouble(quat + 16L);
        double _quatw = UnsafeOpsHolder.U.getDouble(quat + 24L);
        double _t9 = 2.0 * ((_quatx) * (_selfy) - (_quaty * _selfx));
        double _t10 = 2.0 * ((_quatz) * (_selfx) - (_quatx * _selfz));
        double _t11 = 2.0 * ((_quaty) * (_selfz) - (_quatz * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, ((_quaty) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quatz) * (_t10)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_quatz) * (_t11) + (((((_quatw) * (_t10) + (_selfy))) - (_quatx) * (_t9)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfz))) - (_quaty) * (_t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_mulAdd(dest, src, angle, axisX, axisY, axisZ);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        UnsafeOpsHolder.U.putDouble(dest, ((_t2) * (axisX * _t5) + (((_selfx) * (_t0) + (((axisY) * (_selfz) - (axisZ * _selfy)) * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t2) * (axisY * _t5) + (((_selfy) * (_t0) + (((axisZ) * (_selfx) - (axisX * _selfz)) * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t2) * (axisZ * _t5) + (((_selfz) * (_t0) + (((axisX) * (_selfy) - (axisY * _selfx)) * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_mulAdd(dest, src, axis, angle);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t2, _axisx * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t2, _axisy * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t2, _axisz * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, long axis, double angle) {
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        UnsafeOpsHolder.U.putDouble(dest, ((_t2) * (_axisx * _t5) + (((_selfx) * (_t0) + (((_axisy) * (_selfz) - (_axisz * _selfy)) * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t2) * (_axisy * _t5) + (((_selfy) * (_t0) + (((_axisz) * (_selfx) - (_axisx * _selfz)) * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t2) * (_axisz * _t5) + (((_selfz) * (_t0) + (((_axisx) * (_selfy) - (_axisy * _selfx)) * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotateInverse_unsafe(long dest, long src, double quatX, double quatY, double quatZ, double quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_unsafe_mulAdd(dest, src, quatX, quatY, quatZ, quatW);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(quatX, _selfz, -(quatZ * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(quatY, _selfx, -(quatX * _selfy));
        double _t11 = 2.0 * java.lang.Math.fma(quatZ, _selfy, -(quatY * _selfz));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfz))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    /** {@code rotateInverse_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateInverse_unsafe_mulAdd(long dest, long src, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((quatX) * (_selfz) - (quatZ * _selfx));
        double _t10 = 2.0 * ((quatY) * (_selfx) - (quatX * _selfy));
        double _t11 = 2.0 * ((quatZ) * (_selfy) - (quatY * _selfz));
        UnsafeOpsHolder.U.putDouble(dest, ((quatZ) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatY) * (_t10)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfy))) - (quatZ) * (_t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((quatY) * (_t11) + (((((quatW) * (_t10) + (_selfz))) - (quatX) * (_t9)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotateInverse_unsafe(long dest, long src, long quat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_unsafe_mulAdd(dest, src, quat);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _quatx = UnsafeOpsHolder.U.getDouble(quat);
        double _quaty = UnsafeOpsHolder.U.getDouble(quat + 8L);
        double _quatz = UnsafeOpsHolder.U.getDouble(quat + 16L);
        double _quatw = UnsafeOpsHolder.U.getDouble(quat + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        double _t11 = 2.0 * java.lang.Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_quatz, _t9, java.lang.Math.fma(-_quaty, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quatz, _t11, java.lang.Math.fma(_quatw, _t9, _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_quaty, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfz))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    /** {@code rotateInverse_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateInverse_unsafe_mulAdd(long dest, long src, long quat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _quatx = UnsafeOpsHolder.U.getDouble(quat);
        double _quaty = UnsafeOpsHolder.U.getDouble(quat + 8L);
        double _quatz = UnsafeOpsHolder.U.getDouble(quat + 16L);
        double _quatw = UnsafeOpsHolder.U.getDouble(quat + 24L);
        double _t9 = 2.0 * ((_quatx) * (_selfz) - (_quatz * _selfx));
        double _t10 = 2.0 * ((_quaty) * (_selfx) - (_quatx * _selfy));
        double _t11 = 2.0 * ((_quatz) * (_selfy) - (_quaty * _selfz));
        UnsafeOpsHolder.U.putDouble(dest, ((_quatz) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quaty) * (_t10)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfy))) - (_quatz) * (_t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_quaty) * (_t11) + (((((_quatw) * (_t10) + (_selfz))) - (_quatx) * (_t9)))));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putDouble(dest, _selfx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t0, -(_selfz * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfy, _t1, _selfz * _t0));
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putDouble(dest, _selfx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t0) - (_selfz * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfy) * (_t1) + (_selfz * _t0)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
            return dest;
        }
    }

    public static long rotateY_unsafe(long dest, long src, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t0, _selfz * _t1));
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t0, -(_selfx * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t0) + (_selfz * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t0) - (_selfx * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
            return dest;
        }
    }

    public static long rotateZ_unsafe(long dest, long src, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t0, -(_selfy * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfx, _t1, _selfy * _t0));
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t0) - (_selfy * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfx) * (_t1) + (_selfy * _t0)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
            return dest;
        }
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
