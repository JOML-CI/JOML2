// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double3Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double3OpsKernelsAddress {
    private Double3OpsKernelsAddress() {}

    public static long add_unsafe(long dest, long src, double otherX, double otherY, double otherZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, otherX + _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, otherY + _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, otherZ + _selfz);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _otherx + _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _othery + _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _otherz + _selfz);
        return dest;
    }

    public static long div_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx / scalar);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy / scalar);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz / scalar);
        return dest;
    }

    public static long div_unsafe(long dest, long src, double otherX, double otherY, double otherZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx / otherX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy / otherY);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz / otherZ);
        return dest;
    }

    public static long div_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx / _otherx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy / _othery);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz / _otherz);
        return dest;
    }

    public static long fma_unsafe(long dest, long src, double b, double cX, double cY, double cZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, b, cX));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, b, cY));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, b, cZ));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (b) + (cX)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (b) + (cY)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (b) + (cZ)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, long c, double b) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _cx = UnsafeOpsHolder.U.getDouble(c);
            double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
            double _cz = UnsafeOpsHolder.U.getDouble(c + 16L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, b, _cx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, b, _cy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, b, _cz));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _cx = UnsafeOpsHolder.U.getDouble(c);
            double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
            double _cz = UnsafeOpsHolder.U.getDouble(c + 16L);
            UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (b) + (_cx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (b) + (_cy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (b) + (_cz)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, bX, cX));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, bY, cY));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, bZ, cZ));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (bX) + (cX)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (bY) + (cY)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (bZ) + (cZ)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, long b, long c) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_unsafe_mulAdd(dest, src, b, c);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _bx = UnsafeOpsHolder.U.getDouble(b);
        double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
        double _bz = UnsafeOpsHolder.U.getDouble(b + 16L);
        double _cx = UnsafeOpsHolder.U.getDouble(c);
        double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
        double _cz = UnsafeOpsHolder.U.getDouble(c + 16L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _bx, _cx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _by, _cy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _bz, _cz));
        return dest;
    }

    /** {@code fma_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long fma_unsafe_mulAdd(long dest, long src, long b, long c) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _bx = UnsafeOpsHolder.U.getDouble(b);
        double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
        double _bz = UnsafeOpsHolder.U.getDouble(b + 16L);
        double _cx = UnsafeOpsHolder.U.getDouble(c);
        double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
        double _cz = UnsafeOpsHolder.U.getDouble(c + 16L);
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_bx) + (_cx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_by) + (_cy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_bz) + (_cz)));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, scalar * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, scalar * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, scalar * _selfz);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, double otherX, double otherY, double otherZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, otherX * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, otherY * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, otherZ * _selfz);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _otherx * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _othery * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _otherz * _selfz);
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, -_selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_selfz);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, double otherX, double otherY, double otherZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx - otherX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy - otherY);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz - otherZ);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx - _otherx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy - _othery);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz - _otherz);
        return dest;
    }

    public static long makeUniformDirection_unsafe(long dest, double u, double v) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t1 = v * 6.283185307179586;
            double _t4 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
            UnsafeOpsHolder.U.putDouble(dest, _t4 * Math.cosWithSin(_t1));
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t4 * Math.sinWithCos(_t1));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(2.0, u, -1.0));
            return dest;
        } else {
            double _t1 = v * 6.283185307179586;
            double _t4 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
            UnsafeOpsHolder.U.putDouble(dest, _t4 * Math.cosWithSin(_t1));
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t4 * Math.sinWithCos(_t1));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((2.0) * (u) - (1.0)));
            return dest;
        }
    }

    public static long set_unsafe(long dest, double vX, double vY, double vZ) {
        UnsafeOpsHolder.U.putDouble(dest, vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, vY);
        UnsafeOpsHolder.U.putDouble(dest + 16L, vZ);
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _vy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _vz);
        return dest;
    }

    public static long set_unsafe(long dest, double s) {
        UnsafeOpsHolder.U.putDouble(dest, s);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s);
        return dest;
    }

    public static long makeZero_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
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
        return dest;
    }

    /** {@code bezier_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier_unsafe_mulAdd(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
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
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _p3z = UnsafeOpsHolder.U.getDouble(p3 + 16L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_p1x, _t7, _selfx * _t8) + java.lang.Math.fma(_p2x, _t6, _p3x * _t2));
        return bezier_unsafe_fma_sc73aa9e7_1(dest, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t2, _t6, _t7, _t8);
    }

    /** Piece 2 of {@code bezier_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long bezier_unsafe_fma_sc73aa9e7_1(long dest, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t2, double _t6, double _t7, double _t8) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_p1z, _t7, _selfz * _t8) + java.lang.Math.fma(_p2z, _t6, _p3z * _t2));
        return dest;
    }

    /** {@code bezier_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _p3z = UnsafeOpsHolder.U.getDouble(p3 + 16L);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putDouble(dest, ((_p1x) * (_t7) + (_selfx * _t8)) + ((_p2x) * (_t6) + (_p3x * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_p1z) * (_t7) + (_selfz * _t8)) + ((_p2z) * (_t6) + (_p3z * _t2)));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, _selfx * _t4)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, _selfy * _t4)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, _selfz * _t4)));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            UnsafeOpsHolder.U.putDouble(dest, ((p2X) * (_t0) + (((p1X) * (_t3) + (_selfx * _t4)))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((p2Y) * (_t0) + (((p1Y) * (_t3) + (_selfy * _t4)))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((p2Z) * (_t0) + (((p1Z) * (_t3) + (_selfz * _t4)))));
            return dest;
        }
    }

    public static long bezier2_unsafe(long dest, long src, long p1, long p2, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_unsafe_mulAdd(dest, src, p1, p2, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_p2x, _t0, java.lang.Math.fma(_p1x, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_p2z, _t0, java.lang.Math.fma(_p1z, _t3, _selfz * _t4)));
        return dest;
    }

    /** {@code bezier2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2_unsafe_mulAdd(long dest, long src, long p1, long p2, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, ((_p2x) * (_t0) + (((_p1x) * (_t3) + (_selfx * _t4)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_p2z) * (_t0) + (((_p1z) * (_t3) + (_selfz * _t4)))));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            UnsafeOpsHolder.U.putDouble(dest, ((p1X - _selfx) * (_t2) + ((p2X - p1X) * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((p1Z - _selfz) * (_t2) + ((p2Z - p1Z) * _t1)));
            return dest;
        }
    }

    public static long bezier2Tangent_unsafe(long dest, long src, long p1, long p2, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_unsafe_mulAdd(dest, src, p1, p2, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2Tangent_unsafe_mulAdd(long dest, long src, long p1, long p2, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        UnsafeOpsHolder.U.putDouble(dest, ((_p1x - _selfx) * (_t2) + ((_p2x - _p1x) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_p1z - _selfz) * (_t2) + ((_p2z - _p1z) * _t1)));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezierTangent_unsafe_mulAdd(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, ((p3X - p2X) * (_t2) + (((p1X - _selfx) * (_t6) + ((p2X - p1X) * _t5)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((p3Z - p2Z) * (_t2) + (((p1Z - _selfz) * (_t6) + ((p2Z - p1Z) * _t5)))));
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
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _p3z = UnsafeOpsHolder.U.getDouble(p3 + 16L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_p3x - _p2x, _t2, java.lang.Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_p3z - _p2z, _t2, java.lang.Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezierTangent_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _p3z = UnsafeOpsHolder.U.getDouble(p3 + 16L);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        UnsafeOpsHolder.U.putDouble(dest, ((_p3x - _p2x) * (_t2) + (((_p1x - _selfx) * (_t6) + ((_p2x - _p1x) * _t5)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_p3z - _p2z) * (_t2) + (((_p1z - _selfz) * (_t6) + ((_p2z - _p1z) * _t5)))));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_unsafe_fma(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return catmullRom_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@code catmullRom_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_fma(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * (java.lang.Math.fma(2.0, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * (java.lang.Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_unsafe_fma_s34909040_1(dest, p1Z, p2Z, p3Z, t, _selfz, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_fma_s34909040_1(long dest, double p1Z, double p2Z, double p3Z, double t, double _selfz, double _t0, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * (java.lang.Math.fma(2.0, p1Z, t * (p2Z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - _selfz)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_mulAdd(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * (((2.0) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0) * (_selfx) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) * (_t0) + (((((3.0) * (p1X) + (p3X - _selfx))) - (3.0) * (p2X)) * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * (((2.0) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0) * (_selfy) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) * (_t0) + (((((3.0) * (p1Y) + (p3Y - _selfy))) - (3.0) * (p2Y)) * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * (((2.0) * (p1Z) + (t * (p2Z - _selfz))) + ((((((2.0) * (_selfz) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) * (_t0) + (((((3.0) * (p1Z) + (p3Z - _selfz))) - (3.0) * (p2Z)) * _t1))));
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
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _p3z = UnsafeOpsHolder.U.getDouble(p3 + 16L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * (java.lang.Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1x, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0, _p2x, java.lang.Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        return catmullRom_unsafe_fma_s26b03350_1(dest, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_fma_s26b03350_1(long dest, double t, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t0, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * (java.lang.Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0, _p2y, java.lang.Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * (java.lang.Math.fma(2.0, _p1z, t * (_p2z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, _p2z, -_p3z))), _t0, java.lang.Math.fma(-3.0, _p2z, java.lang.Math.fma(3.0, _p1z, _p3z - _selfz)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _p3z = UnsafeOpsHolder.U.getDouble(p3 + 16L);
        double _t0 = t * t;
        double _t1 = t * _t0;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * (((2.0) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0) * (_selfx) + (((4.0) * (_p2x) - (_p3x))))) - (5.0) * (_p1x))) * (_t0) + (((((3.0) * (_p1x) + (_p3x - _selfx))) - (3.0) * (_p2x)) * _t1))));
        return catmullRom_unsafe_mulAdd_se5e7111_1(dest, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_mulAdd_se5e7111_1(long dest, double t, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t0, double _t1) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * (((2.0) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0) * (_selfy) + (((4.0) * (_p2y) - (_p3y))))) - (5.0) * (_p1y))) * (_t0) + (((((3.0) * (_p1y) + (_p3y - _selfy))) - (3.0) * (_p2y)) * _t1))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * (((2.0) * (_p1z) + (t * (_p2z - _selfz))) + ((((((2.0) * (_selfz) + (((4.0) * (_p2z) - (_p3z))))) - (5.0) * (_p1z))) * (_t0) + (((((3.0) * (_p1z) + (_p3z - _selfz))) - (3.0) * (_p2z)) * _t1))));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_unsafe_fma(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return catmullRomTangent_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@code catmullRomTangent_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_fma(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = t * t;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, p2X, -p3X))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, p2Y, -p3Y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, p2Z, -p3Z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        return dest;
    }

    /** {@code catmullRomTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = t * t;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfx) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) + (((3.0 * ((((3.0) * (p1X) + (p3X - _selfx))) - (3.0) * (p2X))) * (_t0) + (p2X - _selfx)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfy) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) + (((3.0 * ((((3.0) * (p1Y) + (p3Y - _selfy))) - (3.0) * (p2Y))) * (_t0) + (p2Y - _selfy)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfz) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) + (((3.0 * ((((3.0) * (p1Z) + (p3Z - _selfz))) - (3.0) * (p2Z))) * (_t0) + (p2Z - _selfz)))));
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
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _p3z = UnsafeOpsHolder.U.getDouble(p3 + 16L);
        double _t0 = t * t;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1x, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, _p2x, -_p3x))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2x, java.lang.Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        return catmullRomTangent_unsafe_fma_sd37ad20b_1(dest, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_fma_sd37ad20b_1(long dest, double t, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t0) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, _p2y, -_p3y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2y, java.lang.Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, _p2z, -_p3z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2z, java.lang.Math.fma(3.0, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return dest;
    }

    /** {@code catmullRomTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _p3x = UnsafeOpsHolder.U.getDouble(p3);
        double _p3y = UnsafeOpsHolder.U.getDouble(p3 + 8L);
        double _p3z = UnsafeOpsHolder.U.getDouble(p3 + 16L);
        double _t0 = t * t;
        UnsafeOpsHolder.U.putDouble(dest, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfx) + (((4.0) * (_p2x) - (_p3x))))) - (5.0) * (_p1x))) + (((3.0 * ((((3.0) * (_p1x) + (_p3x - _selfx))) - (3.0) * (_p2x))) * (_t0) + (_p2x - _selfx)))));
        return catmullRomTangent_unsafe_mulAdd_s9253be94_1(dest, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd_s9253be94_1(long dest, double t, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t0) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfy) + (((4.0) * (_p2y) - (_p3y))))) - (5.0) * (_p1y))) + (((3.0 * ((((3.0) * (_p1y) + (_p3y - _selfy))) - (3.0) * (_p2y))) * (_t0) + (_p2y - _selfy)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfz) + (((4.0) * (_p2z) - (_p3z))))) - (5.0) * (_p1z))) + (((3.0 * ((((3.0) * (_p1z) + (_p3z - _selfz))) - (3.0) * (_p2z))) * (_t0) + (_p2z - _selfz)))));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermite_unsafe_mulAdd(dest, src, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * java.lang.Math.fma(t, t, -t);
        double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
        return dest;
    }

    /** {@code hermite_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermite_unsafe_mulAdd(long dest, long src, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * ((t) * (t) - (t));
        double _t7 = ((t - 2.0) * (_t0) + (t));
        double _t9 = ((3.0) * (_t0) - (_t2 + _t2));
        double _t10 = ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0))));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9)));
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
        double _t0x = UnsafeOpsHolder.U.getDouble(t0);
        double _t0y = UnsafeOpsHolder.U.getDouble(t0 + 8L);
        double _t0z = UnsafeOpsHolder.U.getDouble(t0 + 16L);
        double _v1x = UnsafeOpsHolder.U.getDouble(v1);
        double _v1y = UnsafeOpsHolder.U.getDouble(v1 + 8L);
        double _v1z = UnsafeOpsHolder.U.getDouble(v1 + 16L);
        double _t1x = UnsafeOpsHolder.U.getDouble(t1);
        double _t1y = UnsafeOpsHolder.U.getDouble(t1 + 8L);
        double _t1z = UnsafeOpsHolder.U.getDouble(t1 + 16L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * java.lang.Math.fma(t, t, -t);
        double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t10, _t0x * _t7) + java.lang.Math.fma(_t1x, _t5, _v1x * _t9));
        return hermite_unsafe_fma_s1b0d4710_1(dest, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, _t5, _t7, _t9, _t10);
    }

    /** Piece 2 of {@code hermite_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long hermite_unsafe_fma_s1b0d4710_1(long dest, double _selfy, double _selfz, double _t0y, double _t0z, double _v1y, double _v1z, double _t1y, double _t1z, double _t5, double _t7, double _t9, double _t10) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t10, _t0z * _t7) + java.lang.Math.fma(_t1z, _t5, _v1z * _t9));
        return dest;
    }

    /** {@code hermite_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermite_unsafe_mulAdd(long dest, long src, long t0, long v1, long t1, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0x = UnsafeOpsHolder.U.getDouble(t0);
        double _t0y = UnsafeOpsHolder.U.getDouble(t0 + 8L);
        double _t0z = UnsafeOpsHolder.U.getDouble(t0 + 16L);
        double _v1x = UnsafeOpsHolder.U.getDouble(v1);
        double _v1y = UnsafeOpsHolder.U.getDouble(v1 + 8L);
        double _v1z = UnsafeOpsHolder.U.getDouble(v1 + 16L);
        double _t1x = UnsafeOpsHolder.U.getDouble(t1);
        double _t1y = UnsafeOpsHolder.U.getDouble(t1 + 8L);
        double _t1z = UnsafeOpsHolder.U.getDouble(t1 + 16L);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * ((t) * (t) - (t));
        double _t7 = ((t - 2.0) * (_t0) + (t));
        double _t9 = ((3.0) * (_t0) - (_t2 + _t2));
        double _t10 = ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0))));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t10) + (_t0x * _t7)) + ((_t1x) * (_t5) + (_v1x * _t9)));
        return hermite_unsafe_mulAdd_s4e2f5335_1(dest, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, _t5, _t7, _t9, _t10);
    }

    /** Piece 2 of {@code hermite_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long hermite_unsafe_mulAdd_s4e2f5335_1(long dest, double _selfy, double _selfz, double _t0y, double _t0z, double _v1y, double _v1z, double _t1y, double _t1z, double _t5, double _t7, double _t9, double _t10) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t10) + (_t0z * _t7)) + ((_t1z) * (_t5) + (_v1z * _t9)));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_unsafe_mulAdd(dest, src, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = t * t;
        double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
        double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
        return dest;
    }

    /** {@code hermiteTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd(long dest, long src, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = t * t;
        double _t6 = 6.0 * ((t) * (t) - (t));
        double _t7 = 6.0 * ((t) - (t) * (t));
        double _t8 = ((3.0) * (_t0) - (t + t));
        double _t9 = ((3.0) * (_t0) + (((1.0) - (4.0) * (t))));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7)));
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
        double _t0x = UnsafeOpsHolder.U.getDouble(t0);
        double _t0y = UnsafeOpsHolder.U.getDouble(t0 + 8L);
        double _t0z = UnsafeOpsHolder.U.getDouble(t0 + 16L);
        double _v1x = UnsafeOpsHolder.U.getDouble(v1);
        double _v1y = UnsafeOpsHolder.U.getDouble(v1 + 8L);
        double _v1z = UnsafeOpsHolder.U.getDouble(v1 + 16L);
        double _t1x = UnsafeOpsHolder.U.getDouble(t1);
        double _t1y = UnsafeOpsHolder.U.getDouble(t1 + 8L);
        double _t1z = UnsafeOpsHolder.U.getDouble(t1 + 16L);
        double _t0 = t * t;
        double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
        double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t6, _t0x * _t9) + java.lang.Math.fma(_t1x, _t8, _v1x * _t7));
        return hermiteTangent_unsafe_fma_safd30eab_1(dest, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, _t6, _t7, _t8, _t9);
    }

    /** Piece 2 of {@code hermiteTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long hermiteTangent_unsafe_fma_safd30eab_1(long dest, double _selfy, double _selfz, double _t0y, double _t0z, double _v1y, double _v1z, double _t1y, double _t1z, double _t6, double _t7, double _t8, double _t9) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t6, _t0z * _t9) + java.lang.Math.fma(_t1z, _t8, _v1z * _t7));
        return dest;
    }

    /** {@code hermiteTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd(long dest, long src, long t0, long v1, long t1, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0x = UnsafeOpsHolder.U.getDouble(t0);
        double _t0y = UnsafeOpsHolder.U.getDouble(t0 + 8L);
        double _t0z = UnsafeOpsHolder.U.getDouble(t0 + 16L);
        double _v1x = UnsafeOpsHolder.U.getDouble(v1);
        double _v1y = UnsafeOpsHolder.U.getDouble(v1 + 8L);
        double _v1z = UnsafeOpsHolder.U.getDouble(v1 + 16L);
        double _t1x = UnsafeOpsHolder.U.getDouble(t1);
        double _t1y = UnsafeOpsHolder.U.getDouble(t1 + 8L);
        double _t1z = UnsafeOpsHolder.U.getDouble(t1 + 16L);
        double _t0 = t * t;
        double _t6 = 6.0 * ((t) * (t) - (t));
        double _t7 = 6.0 * ((t) - (t) * (t));
        double _t8 = ((3.0) * (_t0) - (t + t));
        double _t9 = ((3.0) * (_t0) + (((1.0) - (4.0) * (t))));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t6) + (_t0x * _t9)) + ((_t1x) * (_t8) + (_v1x * _t7)));
        return hermiteTangent_unsafe_mulAdd_s6a2b03a4_1(dest, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, _t6, _t7, _t8, _t9);
    }

    /** Piece 2 of {@code hermiteTangent_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd_s6a2b03a4_1(long dest, double _selfy, double _selfz, double _t0y, double _t0z, double _v1y, double _v1z, double _t1y, double _t1z, double _t6, double _t7, double _t8, double _t9) {
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t6) + (_t0z * _t9)) + ((_t1z) * (_t8) + (_v1z * _t7)));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((t) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((t) * (otherZ - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, long other, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _otherx = UnsafeOpsHolder.U.getDouble(other);
            double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
            double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _otherx = UnsafeOpsHolder.U.getDouble(other);
            double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
            double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
            UnsafeOpsHolder.U.putDouble(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((t) * (_othery - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((t) * (_otherz - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(tX, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(tY, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(tZ, otherZ - _selfz, _selfz));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, ((tX) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((tY) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((tZ) * (otherZ - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, long other, long t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_unsafe_mulAdd(dest, src, other, t);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _tx = UnsafeOpsHolder.U.getDouble(t);
        double _ty = UnsafeOpsHolder.U.getDouble(t + 8L);
        double _tz = UnsafeOpsHolder.U.getDouble(t + 16L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_tx, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_ty, _othery - _selfy, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_tz, _otherz - _selfz, _selfz));
        return dest;
    }

    /** {@code lerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lerp_unsafe_mulAdd(long dest, long src, long other, long t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _tx = UnsafeOpsHolder.U.getDouble(t);
        double _ty = UnsafeOpsHolder.U.getDouble(t + 8L);
        double _tz = UnsafeOpsHolder.U.getDouble(t + 16L);
        UnsafeOpsHolder.U.putDouble(dest, ((_tx) * (_otherx - _selfx) + (_selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_ty) * (_othery - _selfy) + (_selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_tz) * (_otherz - _selfz) + (_selfz)));
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, otherX, otherY, otherZ, t);
        return slerp_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, t);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, double otherX, double otherY, double otherZ, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_unsafe_fma_s44d6ee40_1(dest, src, otherX, otherY, otherZ, t, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), java.lang.Math.fma(otherX, _t12, -(_t26 * _t14)), java.lang.Math.fma(otherY, _t12, -(_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s44d6ee40_1(long dest, long src, double otherX, double otherY, double otherZ, double t, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, double otherX, double otherY, double otherZ, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t);
        double _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        double _t33 = ((otherZ) * (_t12) - (_t26 * _t17));
        double _t34 = ((otherX) * (_t12) - (_t26 * _t14));
        double _t35 = ((otherY) * (_t12) - (_t26 * _t20));
        return slerp_unsafe_mulAdd_se625b6a5_1(dest, src, otherX, otherY, otherZ, t, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, _t33, _t34, _t35, -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20)))));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_se625b6a5_1(long dest, long src, double otherX, double otherY, double otherZ, double t, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35, double _t39) {
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putDouble(dest, ((_t14) * (_t53) + (_sp0 * _t41)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t20) * (_t53) + (_sp0 * _t42)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, double otherX, double otherY, double otherZ, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, otherZ, t);
        Double3OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, otherX, otherY, otherZ, t);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, otherX, otherY, otherZ, t);
        return slerp_degenerate_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, t);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, double otherX, double otherY, double otherZ, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = (1.0 / java.lang.Math.sqrt(_t24));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t48 = t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 5.048709793414476E-29) {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, double otherX, double otherY, double otherZ, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = (1.0 / java.lang.Math.sqrt(_t24));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t48 = t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 5.048709793414476E-29) {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((t) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((t) * (otherZ - _selfz) + (_selfz)));
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
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t8 = java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = java.lang.Math.fma(_otherz * _t12, _t17, java.lang.Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        return slerp_unsafe_fma_s29f3f682_1(dest, src, other, t, _othery, _t12, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(_otherz, _t12, -(_t26 * _t17)), java.lang.Math.fma(_otherx, _t12, -(_t26 * _t14)));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s29f3f682_1(long dest, long src, long other, double t, double _othery, double _t12, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34) {
        double _t35 = java.lang.Math.fma(_othery, _t12, -(_t26 * _t20));
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t8 = ((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = ((_otherz * _t12) * (_t17) + (((_otherx * _t12) * (_t14) + (_othery * _t12 * _t20))));
        return slerp_unsafe_mulAdd_s3ae7ab55_1(dest, src, other, t, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, ((_otherz) * (_t12) - (_t26 * _t17)), ((_otherx) * (_t12) - (_t26 * _t14)), ((_othery) * (_t12) - (_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s3ae7ab55_1(long dest, long src, long other, double t, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putDouble(dest, ((_t14) * (_t53) + (_sp0 * _t41)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t20) * (_t53) + (_sp0 * _t42)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t);
        Double3OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 24L), 0L, t);
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
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = (1.0 / java.lang.Math.sqrt(_t24));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t48 = t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 5.048709793414476E-29) {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, long other, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = (1.0 / java.lang.Math.sqrt(_t24));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t48 = t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 5.048709793414476E-29) {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((t) * (_othery - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((t) * (_otherz - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, otherX, otherY, otherZ, t, epsilon);
        return slerp_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, t, epsilon);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t, epsilon);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_unsafe_fma_s60dafbc3_1(dest, src, otherX, otherY, otherZ, t, epsilon, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), java.lang.Math.fma(otherX, _t12, -(_t26 * _t14)), java.lang.Math.fma(otherY, _t12, -(_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s60dafbc3_1(long dest, long src, double otherX, double otherY, double otherZ, double t, double epsilon, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t, epsilon);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t, epsilon);
        double _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        return slerp_unsafe_mulAdd_s13e6ba16_1(dest, src, otherX, otherY, otherZ, t, epsilon, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, ((otherZ) * (_t12) - (_t26 * _t17)), ((otherX) * (_t12) - (_t26 * _t14)), ((otherY) * (_t12) - (_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s13e6ba16_1(long dest, long src, double otherX, double otherY, double otherZ, double t, double epsilon, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t, epsilon);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putDouble(dest, ((_t14) * (_t53) + (_sp0 * _t41)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t20) * (_t53) + (_sp0 * _t42)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, double otherX, double otherY, double otherZ, double t, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, otherZ, t, epsilon);
        Double3OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, otherX, otherY, otherZ, t, epsilon);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, otherX, otherY, otherZ, t, epsilon);
        return slerp_degenerate_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, t, epsilon);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = (1.0 / java.lang.Math.sqrt(_t24));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t48 = t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = (1.0 / java.lang.Math.sqrt(_t24));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t48 = t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((t) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((t) * (otherZ - _selfz) + (_selfz)));
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
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        double _t8 = java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = java.lang.Math.fma(_otherz * _t12, _t17, java.lang.Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        return slerp_unsafe_fma_sa9ddb549_1(dest, src, other, t, epsilon, _otherx, _othery, _t12, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(_otherz, _t12, -(_t26 * _t17)));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sa9ddb549_1(long dest, long src, long other, double t, double epsilon, double _otherx, double _othery, double _t12, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33) {
        double _t34 = java.lang.Math.fma(_otherx, _t12, -(_t26 * _t14));
        double _t35 = java.lang.Math.fma(_othery, _t12, -(_t26 * _t20));
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, long other, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        double _t8 = ((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = ((_otherz * _t12) * (_t17) + (((_otherx * _t12) * (_t14) + (_othery * _t12 * _t20))));
        return slerp_unsafe_mulAdd_s7aa861a6_1(dest, src, other, t, epsilon, _othery, _t12, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, ((_otherz) * (_t12) - (_t26 * _t17)), ((_otherx) * (_t12) - (_t26 * _t14)));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s7aa861a6_1(long dest, long src, long other, double t, double epsilon, double _othery, double _t12, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34) {
        double _t35 = ((_othery) * (_t12) - (_t26 * _t20));
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putDouble(dest, ((_t14) * (_t53) + (_sp0 * _t41)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t20) * (_t53) + (_sp0 * _t42)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, double t, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t, epsilon);
        Double3OpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 24L), 0L, t, epsilon);
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
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = (1.0 / java.lang.Math.sqrt(_t24));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t48 = t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, long other, double t, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t28 = (1.0 / java.lang.Math.sqrt(_t24));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t48 = t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putDouble(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putDouble(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((t) * (_othery - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((t) * (_otherz - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static long absolute_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.abs(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.abs(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.abs(_selfz));
        return dest;
    }

    public static long acos_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.acos(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.acos(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.acos(_selfz));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, double bX, double bY, double bZ, double scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(scalar, bX, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(scalar, bY, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(scalar, bZ, _selfz));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, ((scalar) * (bX) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((scalar) * (bY) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((scalar) * (bZ) + (_selfz)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, long b, double scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _bx = UnsafeOpsHolder.U.getDouble(b);
            double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
            double _bz = UnsafeOpsHolder.U.getDouble(b + 16L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(scalar, _bx, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(scalar, _by, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(scalar, _bz, _selfz));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _bx = UnsafeOpsHolder.U.getDouble(b);
            double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
            double _bz = UnsafeOpsHolder.U.getDouble(b + 16L);
            UnsafeOpsHolder.U.putDouble(dest, ((scalar) * (_bx) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((scalar) * (_by) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((scalar) * (_bz) + (_selfz)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(bX, cX, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(bY, cY, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(bZ, cZ, _selfz));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, ((bX) * (cX) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((bY) * (cY) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((bZ) * (cZ) + (_selfz)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, long b, long c) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_unsafe_mulAdd(dest, src, b, c);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _bx = UnsafeOpsHolder.U.getDouble(b);
        double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
        double _bz = UnsafeOpsHolder.U.getDouble(b + 16L);
        double _cx = UnsafeOpsHolder.U.getDouble(c);
        double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
        double _cz = UnsafeOpsHolder.U.getDouble(c + 16L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_bx, _cx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_by, _cy, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_bz, _cz, _selfz));
        return dest;
    }

    /** {@code addScaled_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long addScaled_unsafe_mulAdd(long dest, long src, long b, long c) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _bx = UnsafeOpsHolder.U.getDouble(b);
        double _by = UnsafeOpsHolder.U.getDouble(b + 8L);
        double _bz = UnsafeOpsHolder.U.getDouble(b + 16L);
        double _cx = UnsafeOpsHolder.U.getDouble(c);
        double _cy = UnsafeOpsHolder.U.getDouble(c + 8L);
        double _cz = UnsafeOpsHolder.U.getDouble(c + 16L);
        UnsafeOpsHolder.U.putDouble(dest, ((_bx) * (_cx) + (_selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_by) * (_cy) + (_selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_bz) * (_cz) + (_selfz)));
        return dest;
    }

    public static double angleBetween_unsafe(long src, double otherX, double otherY, double otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_unsafe_mulAdd(src, otherX, otherY, otherZ);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t3 = otherY * -_selfz;
        double _t4 = -(otherX * _selfy);
        double _t5 = otherX * -_selfz;
        double _t12 = java.lang.Math.fma(otherZ, _selfy, _t3) - java.lang.Math.fma(otherY, _selfz, _t3);
        double _t13 = java.lang.Math.fma(otherY, _selfx, _t4) - java.lang.Math.fma(otherX, _selfy, _t4);
        double _t14 = java.lang.Math.fma(otherZ, _selfx, _t5) - java.lang.Math.fma(otherX, _selfz, _t5);
        double _ct0 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY, otherZ);
        return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    /** {@code angleBetween_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_unsafe_mulAdd(long src, double otherX, double otherY, double otherZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t3 = otherY * -_selfz;
        double _t4 = -(otherX * _selfy);
        double _t5 = otherX * -_selfz;
        double _t12 = ((otherZ) * (_selfy) + (_t3)) - ((otherY) * (_selfz) + (_t3));
        double _t13 = ((otherY) * (_selfx) + (_t4)) - ((otherX) * (_selfy) + (_t4));
        double _t14 = ((otherZ) * (_selfx) + (_t5)) - ((otherX) * (_selfz) + (_t5));
        double _ct0 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY, otherZ);
        return Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))));
    }

    public static double angleBetween_degenerate(long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.angleBetween_degenerate_unsafe(src, otherX, otherY, otherZ);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && VirtualMemoryHolder.virtualMemory().asSlice(src, 24L).isNative()) return Double3OpsKernelsSegment.angleBetween_degenerate_unsafe(VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, otherX, otherY, otherZ);
        return Double3OpsKernelsSegment.angleBetween_degenerate_api(VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, otherX, otherY, otherZ);
    }

    public static double angleBetween_degenerate_unsafe(long src, double otherX, double otherY, double otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_unsafe_mulAdd(src, otherX, otherY, otherZ);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = otherZ * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = otherY * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = otherX * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        double _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        double _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        double _t29 = unitScale(_t27, _t28, _t26);
        double _t33 = _t26 * _t29;
        double _t34 = _t27 * _t29;
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /** {@code angleBetween_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_unsafe_mulAdd(long src, double otherX, double otherY, double otherZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = otherZ * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = otherY * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = otherX * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        double _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        double _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        double _t29 = unitScale(_t27, _t28, _t26);
        double _t33 = _t26 * _t29;
        double _t34 = _t27 * _t29;
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
    }

    public static double angleBetween_unsafe(long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_unsafe_mulAdd(src, other);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t3 = _othery * -_selfz;
        double _t4 = -(_otherx * _selfy);
        double _t5 = _otherx * -_selfz;
        double _t12 = java.lang.Math.fma(_otherz, _selfy, _t3) - java.lang.Math.fma(_othery, _selfz, _t3);
        double _t13 = java.lang.Math.fma(_othery, _selfx, _t4) - java.lang.Math.fma(_otherx, _selfy, _t4);
        double _t14 = java.lang.Math.fma(_otherz, _selfx, _t5) - java.lang.Math.fma(_otherx, _selfz, _t5);
        double _ct0 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.angleBetween_degenerate(src, other);
        return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    /** {@code angleBetween_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_unsafe_mulAdd(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t3 = _othery * -_selfz;
        double _t4 = -(_otherx * _selfy);
        double _t5 = _otherx * -_selfz;
        double _t12 = ((_otherz) * (_selfy) + (_t3)) - ((_othery) * (_selfz) + (_t3));
        double _t13 = ((_othery) * (_selfx) + (_t4)) - ((_otherx) * (_selfy) + (_t4));
        double _t14 = ((_otherz) * (_selfx) + (_t5)) - ((_otherx) * (_selfz) + (_t5));
        double _ct0 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.angleBetween_degenerate(src, other);
        return Math.atan2(java.lang.Math.sqrt(_ct0), ((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))));
    }

    public static double angleBetween_degenerate(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.angleBetween_degenerate_unsafe(src, other);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && VirtualMemoryHolder.virtualMemory().asSlice(src, 24L).isNative() && VirtualMemoryHolder.virtualMemory().asSlice(other, 24L).isNative()) return Double3OpsKernelsSegment.angleBetween_degenerate_unsafe(VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 24L), 0L);
        return Double3OpsKernelsSegment.angleBetween_degenerate_api(VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 24L), 0L);
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
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t0 = unitScale(_otherx, _othery, _otherz);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = _otherz * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = _othery * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = _otherx * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        double _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        double _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        double _t29 = unitScale(_t27, _t28, _t26);
        double _t33 = _t26 * _t29;
        double _t34 = _t27 * _t29;
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /** {@code angleBetween_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_unsafe_mulAdd(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t0 = unitScale(_otherx, _othery, _otherz);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = _otherz * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = _othery * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = _otherx * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        double _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        double _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        double _t29 = unitScale(_t27, _t28, _t26);
        double _t33 = _t26 * _t29;
        double _t34 = _t27 * _t29;
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
    }

    public static long asin_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.asin(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.asin(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.asin(_selfz));
        return dest;
    }

    public static long atan_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.atan(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan(_selfz));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, double x) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_selfx, x));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_selfy, x));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_selfz, x));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, double xX, double xY, double xZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_selfx, xX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_selfy, xY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_selfz, xZ));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, long x) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _xx = UnsafeOpsHolder.U.getDouble(x);
        double _xy = UnsafeOpsHolder.U.getDouble(x + 8L);
        double _xz = UnsafeOpsHolder.U.getDouble(x + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_selfx, _xx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_selfy, _xy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_selfz, _xz));
        return dest;
    }

    public static long cbrt_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.cbrt(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.cbrt(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.cbrt(_selfz));
        return dest;
    }

    public static long ceil_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.ceil(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.ceil(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.ceil(_selfz));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, double min, double max) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.min(Math.max(_selfx, min), max));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(Math.max(_selfy, min), max));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(Math.max(_selfz, min), max));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.min(Math.max(_selfx, minX), maxX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(Math.max(_selfy, minY), maxY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(Math.max(_selfz, minZ), maxZ));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, long min, long max) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _minx = UnsafeOpsHolder.U.getDouble(min);
        double _miny = UnsafeOpsHolder.U.getDouble(min + 8L);
        double _minz = UnsafeOpsHolder.U.getDouble(min + 16L);
        double _maxx = UnsafeOpsHolder.U.getDouble(max);
        double _maxy = UnsafeOpsHolder.U.getDouble(max + 8L);
        double _maxz = UnsafeOpsHolder.U.getDouble(max + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.min(Math.max(_selfx, _minx), _maxx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(Math.max(_selfy, _miny), _maxy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(Math.max(_selfz, _minz), _maxz));
        return dest;
    }

    public static long closestPointOnLine_unsafe(long dest, long src, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return closestPointOnLine_unsafe_mulAdd(dest, src, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        double _t0 = lineEndZ - lineStartZ;
        double _t1 = lineEndX - lineStartX;
        double _t2 = lineEndY - lineStartY;
        double _t10 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        double _t14 = Math.max(0.0, Math.min(1.0, java.lang.Math.fma(_t0, UnsafeOpsHolder.U.getDouble(src + 16L) - lineStartZ, java.lang.Math.fma(_t1, UnsafeOpsHolder.U.getDouble(src) - lineStartX, _t2 * (UnsafeOpsHolder.U.getDouble(src + 8L) - lineStartY))) / _t10));
        if (_t10 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t1, _t14, lineStartX));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t2, _t14, lineStartY));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t0, _t14, lineStartZ));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, lineStartX);
            UnsafeOpsHolder.U.putDouble(dest + 8L, lineStartY);
            UnsafeOpsHolder.U.putDouble(dest + 16L, lineStartZ);
        }
        return dest;
    }

    /** {@code closestPointOnLine_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long closestPointOnLine_unsafe_mulAdd(long dest, long src, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        double _t0 = lineEndZ - lineStartZ;
        double _t1 = lineEndX - lineStartX;
        double _t2 = lineEndY - lineStartY;
        double _t10 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        double _t14 = Math.max(0.0, Math.min(1.0, ((_t0) * (UnsafeOpsHolder.U.getDouble(src + 16L) - lineStartZ) + (((_t1) * (UnsafeOpsHolder.U.getDouble(src) - lineStartX) + (_t2 * (UnsafeOpsHolder.U.getDouble(src + 8L) - lineStartY))))) / _t10));
        if (_t10 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, ((_t1) * (_t14) + (lineStartX)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t2) * (_t14) + (lineStartY)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t0) * (_t14) + (lineStartZ)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, lineStartX);
            UnsafeOpsHolder.U.putDouble(dest + 8L, lineStartY);
            UnsafeOpsHolder.U.putDouble(dest + 16L, lineStartZ);
        }
        return dest;
    }

    public static long closestPointOnLine_unsafe(long dest, long src, long lineStart, long lineEnd) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return closestPointOnLine_unsafe_fma(dest, src, lineStart, lineEnd);
        return closestPointOnLine_unsafe_mulAdd(dest, src, lineStart, lineEnd);
    }

    /** {@code closestPointOnLine_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long closestPointOnLine_unsafe_fma(long dest, long src, long lineStart, long lineEnd) {
        double _lineStartx = UnsafeOpsHolder.U.getDouble(lineStart);
        double _lineStarty = UnsafeOpsHolder.U.getDouble(lineStart + 8L);
        double _lineStartz = UnsafeOpsHolder.U.getDouble(lineStart + 16L);
        double _t0 = UnsafeOpsHolder.U.getDouble(lineEnd + 16L) - _lineStartz;
        double _t1 = UnsafeOpsHolder.U.getDouble(lineEnd) - _lineStartx;
        double _t2 = UnsafeOpsHolder.U.getDouble(lineEnd + 8L) - _lineStarty;
        double _t10 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        double _t14 = Math.max(0.0, Math.min(1.0, java.lang.Math.fma(_t0, UnsafeOpsHolder.U.getDouble(src + 16L) - _lineStartz, java.lang.Math.fma(_t1, UnsafeOpsHolder.U.getDouble(src) - _lineStartx, _t2 * (UnsafeOpsHolder.U.getDouble(src + 8L) - _lineStarty))) / _t10));
        if (_t10 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t1, _t14, _lineStartx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t2, _t14, _lineStarty));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t0, _t14, _lineStartz));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, _lineStartx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _lineStarty);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _lineStartz);
        }
        return dest;
    }

    /** {@code closestPointOnLine_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long closestPointOnLine_unsafe_mulAdd(long dest, long src, long lineStart, long lineEnd) {
        double _lineStartx = UnsafeOpsHolder.U.getDouble(lineStart);
        double _lineStarty = UnsafeOpsHolder.U.getDouble(lineStart + 8L);
        double _lineStartz = UnsafeOpsHolder.U.getDouble(lineStart + 16L);
        double _t0 = UnsafeOpsHolder.U.getDouble(lineEnd + 16L) - _lineStartz;
        double _t1 = UnsafeOpsHolder.U.getDouble(lineEnd) - _lineStartx;
        double _t2 = UnsafeOpsHolder.U.getDouble(lineEnd + 8L) - _lineStarty;
        double _t10 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        double _t14 = Math.max(0.0, Math.min(1.0, ((_t0) * (UnsafeOpsHolder.U.getDouble(src + 16L) - _lineStartz) + (((_t1) * (UnsafeOpsHolder.U.getDouble(src) - _lineStartx) + (_t2 * (UnsafeOpsHolder.U.getDouble(src + 8L) - _lineStarty))))) / _t10));
        if (_t10 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, ((_t1) * (_t14) + (_lineStartx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t2) * (_t14) + (_lineStarty)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t0) * (_t14) + (_lineStartz)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, _lineStartx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _lineStarty);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _lineStartz);
        }
        return dest;
    }

    public static double compAdd_unsafe(long src) {
        return UnsafeOpsHolder.U.getDouble(src + 16L) + (UnsafeOpsHolder.U.getDouble(src) + UnsafeOpsHolder.U.getDouble(src + 8L));
    }

    public static double compMax_unsafe(long src) {
        return Math.max(Math.max(UnsafeOpsHolder.U.getDouble(src), UnsafeOpsHolder.U.getDouble(src + 8L)), UnsafeOpsHolder.U.getDouble(src + 16L));
    }

    public static double compMin_unsafe(long src) {
        return Math.min(Math.min(UnsafeOpsHolder.U.getDouble(src), UnsafeOpsHolder.U.getDouble(src + 8L)), UnsafeOpsHolder.U.getDouble(src + 16L));
    }

    public static double compMul_unsafe(long src) {
        return UnsafeOpsHolder.U.getDouble(src + 16L) * UnsafeOpsHolder.U.getDouble(src) * UnsafeOpsHolder.U.getDouble(src + 8L);
    }

    public static long copySign_unsafe(long dest, long src, double sign) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.copySign(_selfx, sign));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.copySign(_selfy, sign));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.copySign(_selfz, sign));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, double signX, double signY, double signZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.copySign(_selfx, signX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.copySign(_selfy, signY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.copySign(_selfz, signZ));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, long sign) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _signx = UnsafeOpsHolder.U.getDouble(sign);
        double _signy = UnsafeOpsHolder.U.getDouble(sign + 8L);
        double _signz = UnsafeOpsHolder.U.getDouble(sign + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.copySign(_selfx, _signx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.copySign(_selfy, _signy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.copySign(_selfz, _signz));
        return dest;
    }

    public static long cos_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.cos(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.cos(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.cos(_selfz));
        return dest;
    }

    public static long cosh_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.cosh(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.cosh(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.cosh(_selfz));
        return dest;
    }

    public static long cross_unsafe(long dest, long src, double otherX, double otherY, double otherZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t3 = -(otherY * _selfz);
            double _t4 = -(otherZ * _selfx);
            double _t5 = -(otherX * _selfy);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(otherZ, _selfy, _t3) - java.lang.Math.fma(otherY, _selfz, _t3));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(otherX, _selfz, _t4) - java.lang.Math.fma(otherZ, _selfx, _t4));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(otherY, _selfx, _t5) - java.lang.Math.fma(otherX, _selfy, _t5));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t3 = -(otherY * _selfz);
            double _t4 = -(otherZ * _selfx);
            double _t5 = -(otherX * _selfy);
            UnsafeOpsHolder.U.putDouble(dest, ((otherZ) * (_selfy) + (_t3)) - ((otherY) * (_selfz) + (_t3)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((otherX) * (_selfz) + (_t4)) - ((otherZ) * (_selfx) + (_t4)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((otherY) * (_selfx) + (_t5)) - ((otherX) * (_selfy) + (_t5)));
            return dest;
        }
    }

    public static long cross_unsafe(long dest, long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return cross_unsafe_mulAdd(dest, src, other);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t3 = -(_othery * _selfz);
        double _t4 = -(_otherz * _selfx);
        double _t5 = -(_otherx * _selfy);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_otherz, _selfy, _t3) - java.lang.Math.fma(_othery, _selfz, _t3));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_otherx, _selfz, _t4) - java.lang.Math.fma(_otherz, _selfx, _t4));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_othery, _selfx, _t5) - java.lang.Math.fma(_otherx, _selfy, _t5));
        return dest;
    }

    /** {@code cross_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cross_unsafe_mulAdd(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _t3 = -(_othery * _selfz);
        double _t4 = -(_otherz * _selfx);
        double _t5 = -(_otherx * _selfy);
        UnsafeOpsHolder.U.putDouble(dest, ((_otherz) * (_selfy) + (_t3)) - ((_othery) * (_selfz) + (_t3)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_otherx) * (_selfz) + (_t4)) - ((_otherz) * (_selfx) + (_t4)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_othery) * (_selfx) + (_t5)) - ((_otherx) * (_selfy) + (_t5)));
        return dest;
    }

    public static long degrees_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.toDegrees(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.toDegrees(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.toDegrees(_selfz));
        return dest;
    }

    public static double distance_unsafe(long src, double otherX, double otherY, double otherZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 16L) - otherZ;
            double _t1 = UnsafeOpsHolder.U.getDouble(src) - otherX;
            double _t2 = UnsafeOpsHolder.U.getDouble(src + 8L) - otherY;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 16L) - otherZ;
            double _t1 = UnsafeOpsHolder.U.getDouble(src) - otherX;
            double _t2 = UnsafeOpsHolder.U.getDouble(src + 8L) - otherY;
            return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
        }
    }

    public static double distance_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 16L) - UnsafeOpsHolder.U.getDouble(other + 16L);
            double _t1 = UnsafeOpsHolder.U.getDouble(src) - UnsafeOpsHolder.U.getDouble(other);
            double _t2 = UnsafeOpsHolder.U.getDouble(src + 8L) - UnsafeOpsHolder.U.getDouble(other + 8L);
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 16L) - UnsafeOpsHolder.U.getDouble(other + 16L);
            double _t1 = UnsafeOpsHolder.U.getDouble(src) - UnsafeOpsHolder.U.getDouble(other);
            double _t2 = UnsafeOpsHolder.U.getDouble(src + 8L) - UnsafeOpsHolder.U.getDouble(other + 8L);
            return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
        }
    }

    public static double distanceSquared_unsafe(long src, double otherX, double otherY, double otherZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 16L) - otherZ;
            double _t1 = UnsafeOpsHolder.U.getDouble(src) - otherX;
            double _t2 = UnsafeOpsHolder.U.getDouble(src + 8L) - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 16L) - otherZ;
            double _t1 = UnsafeOpsHolder.U.getDouble(src) - otherX;
            double _t2 = UnsafeOpsHolder.U.getDouble(src + 8L) - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        }
    }

    public static double distanceSquared_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 16L) - UnsafeOpsHolder.U.getDouble(other + 16L);
            double _t1 = UnsafeOpsHolder.U.getDouble(src) - UnsafeOpsHolder.U.getDouble(other);
            double _t2 = UnsafeOpsHolder.U.getDouble(src + 8L) - UnsafeOpsHolder.U.getDouble(other + 8L);
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            double _t0 = UnsafeOpsHolder.U.getDouble(src + 16L) - UnsafeOpsHolder.U.getDouble(other + 16L);
            double _t1 = UnsafeOpsHolder.U.getDouble(src) - UnsafeOpsHolder.U.getDouble(other);
            double _t2 = UnsafeOpsHolder.U.getDouble(src + 8L) - UnsafeOpsHolder.U.getDouble(other + 8L);
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        }
    }

    public static double dot_unsafe(long src, double otherX, double otherY, double otherZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherZ, UnsafeOpsHolder.U.getDouble(src + 16L), java.lang.Math.fma(otherX, UnsafeOpsHolder.U.getDouble(src), otherY * UnsafeOpsHolder.U.getDouble(src + 8L)));
        } else {
            return ((otherZ) * (UnsafeOpsHolder.U.getDouble(src + 16L)) + (((otherX) * (UnsafeOpsHolder.U.getDouble(src)) + (otherY * UnsafeOpsHolder.U.getDouble(src + 8L)))));
        }
    }

    public static double dot_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(other + 16L), UnsafeOpsHolder.U.getDouble(src + 16L), java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(other), UnsafeOpsHolder.U.getDouble(src), UnsafeOpsHolder.U.getDouble(other + 8L) * UnsafeOpsHolder.U.getDouble(src + 8L)));
        } else {
            return ((UnsafeOpsHolder.U.getDouble(other + 16L)) * (UnsafeOpsHolder.U.getDouble(src + 16L)) + (((UnsafeOpsHolder.U.getDouble(other)) * (UnsafeOpsHolder.U.getDouble(src)) + (UnsafeOpsHolder.U.getDouble(other + 8L) * UnsafeOpsHolder.U.getDouble(src + 8L)))));
        }
    }

    public static long exp_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.exp(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.exp(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.exp(_selfz));
        return dest;
    }

    public static long exp2_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.pow(2.0, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.pow(2.0, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.pow(2.0, _selfz));
        return dest;
    }

    public static long expm1_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.expm1(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.expm1(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.expm1(_selfz));
        return dest;
    }

    public static long faceforward_unsafe(long dest, long src, double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t3 = java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY)) < 0.0 ? 1.0 : -1.0;
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t3);
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t3 = ((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))) < 0.0 ? 1.0 : -1.0;
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t3);
            return dest;
        }
    }

    public static long faceforward_unsafe(long dest, long src, long I, long Nref) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_unsafe_mulAdd(dest, src, I, Nref);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t3 = java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(I + 16L), UnsafeOpsHolder.U.getDouble(Nref + 16L), java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(I), UnsafeOpsHolder.U.getDouble(Nref), UnsafeOpsHolder.U.getDouble(I + 8L) * UnsafeOpsHolder.U.getDouble(Nref + 8L))) < 0.0 ? 1.0 : -1.0;
        UnsafeOpsHolder.U.putDouble(dest, _selfx * _t3);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t3);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t3);
        return dest;
    }

    /** {@code faceforward_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long faceforward_unsafe_mulAdd(long dest, long src, long I, long Nref) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t3 = ((UnsafeOpsHolder.U.getDouble(I + 16L)) * (UnsafeOpsHolder.U.getDouble(Nref + 16L)) + (((UnsafeOpsHolder.U.getDouble(I)) * (UnsafeOpsHolder.U.getDouble(Nref)) + (UnsafeOpsHolder.U.getDouble(I + 8L) * UnsafeOpsHolder.U.getDouble(Nref + 8L))))) < 0.0 ? 1.0 : -1.0;
        UnsafeOpsHolder.U.putDouble(dest, _selfx * _t3);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t3);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t3);
        return dest;
    }

    public static long floor_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.floor(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.floor(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.floor(_selfz));
        return dest;
    }

    public static long fract_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(_selfz - Math.floor(_selfz), 0.9999999999999999));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, double y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.hypot(_selfx, y));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.hypot(_selfy, y));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.hypot(_selfz, y));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, double yX, double yY, double yZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.hypot(_selfx, yX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.hypot(_selfy, yY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.hypot(_selfz, yZ));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, long y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _yx = UnsafeOpsHolder.U.getDouble(y);
        double _yy = UnsafeOpsHolder.U.getDouble(y + 8L);
        double _yz = UnsafeOpsHolder.U.getDouble(y + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.hypot(_selfx, _yx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.hypot(_selfy, _yy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.hypot(_selfz, _yz));
        return dest;
    }

    public static long inverse_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, 1.0 / _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 1.0 / _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 1.0 / _selfz);
        return dest;
    }

    public static long inverseSqrt_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, (1.0 / java.lang.Math.sqrt(_selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, (1.0 / java.lang.Math.sqrt(_selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, (1.0 / java.lang.Math.sqrt(_selfz)));
        return dest;
    }

    public static double length_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            return java.lang.Math.sqrt(java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            return java.lang.Math.sqrt(((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        }
    }

    public static double lengthSquared_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            return java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            return ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        }
    }

    public static long log_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.log(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.log(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.log(_selfz));
        return dest;
    }

    public static long log10_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.log10(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.log10(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.log10(_selfz));
        return dest;
    }

    public static long log1p_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.log1p(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.log1p(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.log1p(_selfz));
        return dest;
    }

    public static long log2_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.log2(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.log2(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.log2(_selfz));
        return dest;
    }

    public static double manhattanDistance_unsafe(long src, double otherX, double otherY, double otherZ) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src) - otherX) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 8L) - otherY) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 16L) - otherZ);
    }

    public static double manhattanDistance_unsafe(long src, long other) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src) - UnsafeOpsHolder.U.getDouble(other)) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 8L) - UnsafeOpsHolder.U.getDouble(other + 8L)) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 16L) - UnsafeOpsHolder.U.getDouble(other + 16L));
    }

    public static double manhattanLength_unsafe(long src) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src)) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 8L)) + java.lang.Math.abs(UnsafeOpsHolder.U.getDouble(src + 16L));
    }

    public static long max_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.max(_selfx, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.max(_selfy, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.max(_selfz, scalar));
        return dest;
    }

    public static long max_unsafe(long dest, long src, double otherX, double otherY, double otherZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.max(_selfx, otherX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.max(_selfy, otherY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.max(_selfz, otherZ));
        return dest;
    }

    public static long max_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.max(_selfx, _otherx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.max(_selfy, _othery));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.max(_selfz, _otherz));
        return dest;
    }

    public static long min_unsafe(long dest, long src, double scalar) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.min(_selfx, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(_selfy, scalar));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(_selfz, scalar));
        return dest;
    }

    public static long min_unsafe(long dest, long src, double otherX, double otherY, double otherZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.min(_selfx, otherX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(_selfy, otherY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(_selfz, otherZ));
        return dest;
    }

    public static long min_unsafe(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.min(_selfx, _otherx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.min(_selfy, _othery));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.min(_selfz, _otherz));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, double y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, flooredMod(_selfx, y));
        UnsafeOpsHolder.U.putDouble(dest + 8L, flooredMod(_selfy, y));
        UnsafeOpsHolder.U.putDouble(dest + 16L, flooredMod(_selfz, y));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, double yX, double yY, double yZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, flooredMod(_selfx, yX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, flooredMod(_selfy, yY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, flooredMod(_selfz, yZ));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, long y) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _yx = UnsafeOpsHolder.U.getDouble(y);
        double _yy = UnsafeOpsHolder.U.getDouble(y + 8L);
        double _yz = UnsafeOpsHolder.U.getDouble(y + 16L);
        UnsafeOpsHolder.U.putDouble(dest, flooredMod(_selfx, _yx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, flooredMod(_selfy, _yy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, flooredMod(_selfz, _yz));
        return dest;
    }

    public static long nextDown_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.nextDown(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.nextDown(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.nextDown(_selfz));
        return dest;
    }

    public static long nextUp_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.nextUp(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.nextUp(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.nextUp(_selfz));
        return dest;
    }

    public static long normalize_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalize_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t3);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    /** {@code normalize_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normalize_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t3);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t3);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long normalizeMul_unsafe(long dest, long src, double length) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizeMul_unsafe_mulAdd(dest, src, length);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t4);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    /** {@code normalizeMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normalizeMul_unsafe_mulAdd(long dest, long src, double length) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t4);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t4);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static double orientedAngle_unsafe(long src, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_unsafe_mulAdd(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t4 = -(otherX * _selfy);
        double _t5 = -(otherZ * _selfx);
        double _t6 = -(otherY * _selfz);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t16 = java.lang.Math.fma(otherY, _selfx, _t4) - java.lang.Math.fma(otherX, _selfy, _t4);
        double _t17 = java.lang.Math.fma(otherX, _selfz, _t5) - java.lang.Math.fma(otherZ, _selfx, _t5);
        double _t18 = java.lang.Math.fma(otherZ, _selfy, _t6) - java.lang.Math.fma(otherY, _selfz, _t6);
        double _ct0 = java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t17, _t17, _t18 * _t18));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.orientedAngle_degenerate(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy)));
        return java.lang.Math.fma(_t16, normalZ * _t7, java.lang.Math.fma(_t17, normalY * _t7, _t18 * (normalX * _t7))) < 0.0 ? -_t24 : _t24;
    }

    /** {@code orientedAngle_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_unsafe_mulAdd(long src, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t4 = -(otherX * _selfy);
        double _t5 = -(otherZ * _selfx);
        double _t6 = -(otherY * _selfz);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t16 = ((otherY) * (_selfx) + (_t4)) - ((otherX) * (_selfy) + (_t4));
        double _t17 = ((otherX) * (_selfz) + (_t5)) - ((otherZ) * (_selfx) + (_t5));
        double _t18 = ((otherZ) * (_selfy) + (_t6)) - ((otherY) * (_selfz) + (_t6));
        double _ct0 = ((_t16) * (_t16) + (((_t17) * (_t17) + (_t18 * _t18))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.orientedAngle_degenerate(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))));
        return ((_t16) * (normalZ * _t7) + (((_t17) * (normalY * _t7) + (_t18 * (normalX * _t7))))) < 0.0 ? -_t24 : _t24;
    }

    public static double orientedAngle_degenerate(long src, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.orientedAngle_degenerate_unsafe(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && VirtualMemoryHolder.virtualMemory().asSlice(src, 24L).isNative()) return Double3OpsKernelsSegment.orientedAngle_degenerate_unsafe(VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return Double3OpsKernelsSegment.orientedAngle_degenerate_api(VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static double orientedAngle_degenerate_unsafe(long src, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_unsafe_fma(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return orientedAngle_degenerate_unsafe_mulAdd(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@code orientedAngle_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_unsafe_fma(long src, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherY * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = otherX * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = _selfz * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        double _t21 = -(_t13 * _t10);
        double _t30 = java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19);
        double _t31 = java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20);
        double _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        double _t33 = unitScale(_t31, _t32, _t30);
        return orientedAngle_degenerate_unsafe_fma_sa776174e_1(normalX, normalY, normalZ, unitScale(normalX, normalY, normalZ), _t9, _t10, _t11, _t12, _t13, _t14, _t33, _t30 * _t33, _t31 * _t33, _t32 * _t33);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_degenerate_unsafe_fma_sa776174e_1(double normalX, double normalY, double normalZ, double _t0, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t33, double _t39, double _t40, double _t41) {
        double _t46 = Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(normalZ * _t0, _t39, java.lang.Math.fma(normalX * _t0, _t40, normalY * _t0 * _t41)) < 0.0 ? -_t46 : _t46;
    }

    /** {@code orientedAngle_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_unsafe_mulAdd(long src, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = unitScale(normalX, normalY, normalZ);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherY * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = otherX * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = _selfz * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        double _t21 = -(_t13 * _t10);
        double _t30 = ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19));
        double _t31 = ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20));
        double _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        double _t33 = unitScale(_t31, _t32, _t30);
        double _t39 = _t30 * _t33;
        double _t40 = _t31 * _t33;
        double _t41 = _t32 * _t33;
        double _t46 = Math.atan2(java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((normalZ * _t0) * (_t39) + (((normalX * _t0) * (_t40) + (normalY * _t0 * _t41)))) < 0.0 ? -_t46 : _t46;
    }

    public static double orientedAngle_unsafe(long src, long other, long normal) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_unsafe_fma(src, other, normal);
        return orientedAngle_unsafe_mulAdd(src, other, normal);
    }

    /** {@code orientedAngle_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_unsafe_fma(long src, long other, long normal) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _t4 = -(_otherx * _selfy);
        double _t5 = -(_otherz * _selfx);
        double _t6 = -(_othery * _selfz);
        double _t16 = java.lang.Math.fma(_othery, _selfx, _t4) - java.lang.Math.fma(_otherx, _selfy, _t4);
        double _t17 = java.lang.Math.fma(_otherx, _selfz, _t5) - java.lang.Math.fma(_otherz, _selfx, _t5);
        double _t18 = java.lang.Math.fma(_otherz, _selfy, _t6) - java.lang.Math.fma(_othery, _selfz, _t6);
        double _ct0 = java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t17, _t17, _t18 * _t18));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.orientedAngle_degenerate(src, other, normal);
        return orientedAngle_unsafe_fma_s2c25e10d_1(_selfx, _selfy, _selfz, _otherx, _othery, _otherz, _normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t16, _t17, _t18, _ct0);
    }

    /** Piece 2 of {@code orientedAngle_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_unsafe_fma_s2c25e10d_1(double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _normalx, double _normaly, double _normalz, double _t7, double _t16, double _t17, double _t18, double _ct0) {
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy)));
        return java.lang.Math.fma(_t16, _normalz * _t7, java.lang.Math.fma(_t17, _normaly * _t7, _t18 * (_normalx * _t7))) < 0.0 ? -_t24 : _t24;
    }

    /** {@code orientedAngle_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_unsafe_mulAdd(long src, long other, long normal) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _t4 = -(_otherx * _selfy);
        double _t5 = -(_otherz * _selfx);
        double _t6 = -(_othery * _selfz);
        double _t16 = ((_othery) * (_selfx) + (_t4)) - ((_otherx) * (_selfy) + (_t4));
        double _t17 = ((_otherx) * (_selfz) + (_t5)) - ((_otherz) * (_selfx) + (_t5));
        double _t18 = ((_otherz) * (_selfy) + (_t6)) - ((_othery) * (_selfz) + (_t6));
        double _ct0 = ((_t16) * (_t16) + (((_t17) * (_t17) + (_t18 * _t18))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.orientedAngle_degenerate(src, other, normal);
        return orientedAngle_unsafe_mulAdd_s17147de4_1(_selfx, _selfy, _selfz, _otherx, _othery, _otherz, _normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t16, _t17, _t18, _ct0);
    }

    /** Piece 2 of {@code orientedAngle_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_unsafe_mulAdd_s17147de4_1(double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _normalx, double _normaly, double _normalz, double _t7, double _t16, double _t17, double _t18, double _ct0) {
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), ((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))));
        return ((_t16) * (_normalz * _t7) + (((_t17) * (_normaly * _t7) + (_t18 * (_normalx * _t7))))) < 0.0 ? -_t24 : _t24;
    }

    public static double orientedAngle_degenerate(long src, long other, long normal) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.orientedAngle_degenerate_unsafe(src, other, normal);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && VirtualMemoryHolder.virtualMemory().asSlice(src, 24L).isNative() && VirtualMemoryHolder.virtualMemory().asSlice(other, 24L).isNative() && VirtualMemoryHolder.virtualMemory().asSlice(normal, 24L).isNative()) return Double3OpsKernelsSegment.orientedAngle_degenerate_unsafe(VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(normal, 24L), 0L);
        return Double3OpsKernelsSegment.orientedAngle_degenerate_api(VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(normal, 24L), 0L);
    }

    public static double orientedAngle_degenerate_unsafe(long src, long other, long normal) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_unsafe_fma(src, other, normal);
        return orientedAngle_degenerate_unsafe_mulAdd(src, other, normal);
    }

    /** {@code orientedAngle_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_unsafe_fma(long src, long other, long normal) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _othery * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = _otherx * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = _otherz * _t1;
        double _t14 = _selfz * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        double _t21 = -(_t13 * _t10);
        double _t30 = java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19);
        double _t31 = java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20);
        double _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        return orientedAngle_degenerate_unsafe_fma_scc2b4492_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, _t30, _t31, _t32, unitScale(_t31, _t32, _t30));
    }

    /** Piece 2 of {@code orientedAngle_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_degenerate_unsafe_fma_scc2b4492_1(double _normalx, double _normaly, double _normalz, double _t0, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t30, double _t31, double _t32, double _t33) {
        double _t39 = _t30 * _t33;
        double _t40 = _t31 * _t33;
        double _t41 = _t32 * _t33;
        double _t46 = Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(_normalz * _t0, _t39, java.lang.Math.fma(_normalx * _t0, _t40, _normaly * _t0 * _t41)) < 0.0 ? -_t46 : _t46;
    }

    /** {@code orientedAngle_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_unsafe_mulAdd(long src, long other, long normal) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _othery * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = _otherx * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = _otherz * _t1;
        double _t14 = _selfz * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        double _t21 = -(_t13 * _t10);
        double _t30 = ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19));
        double _t31 = ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20));
        double _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        double _t33 = unitScale(_t31, _t32, _t30);
        return orientedAngle_degenerate_unsafe_mulAdd_s68b92f9_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, _t31, _t32, _t33, _t30 * _t33);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_degenerate_unsafe_mulAdd_s68b92f9_1(double _normalx, double _normaly, double _normalz, double _t0, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t31, double _t32, double _t33, double _t39) {
        double _t40 = _t31 * _t33;
        double _t41 = _t32 * _t33;
        double _t46 = Math.atan2(java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((_normalz * _t0) * (_t39) + (((_normalx * _t0) * (_t40) + (_normaly * _t0 * _t41)))) < 0.0 ? -_t46 : _t46;
    }

    public static long outerProduct_unsafe(long dest, long src, double rowX, double rowY, double rowZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, rowX * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, rowX * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, rowX * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, rowY * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 32L, rowY * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 40L, rowY * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 48L, rowZ * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 56L, rowZ * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 64L, rowZ * _selfz);
        return dest;
    }

    public static long outerProduct_unsafe(long dest, long src, long row) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _rowx = UnsafeOpsHolder.U.getDouble(row);
        double _rowy = UnsafeOpsHolder.U.getDouble(row + 8L);
        double _rowz = UnsafeOpsHolder.U.getDouble(row + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _rowx * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _rowx * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _rowx * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _rowy * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _rowy * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _rowy * _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _rowz * _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _rowz * _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 64L, _rowz * _selfz);
        return dest;
    }

    public static long perpendicular_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        if (java.lang.Math.abs(_selfz) < java.lang.Math.abs(_selfx)) {
            UnsafeOpsHolder.U.putDouble(dest, _selfy);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -_selfx);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfz);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -_selfy);
        }
        return dest;
    }

    public static long pow_unsafe(long dest, long src, double exponent) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.pow(_selfx, exponent));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.pow(_selfy, exponent));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.pow(_selfz, exponent));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, double exponentX, double exponentY, double exponentZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.pow(_selfx, exponentX));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.pow(_selfy, exponentY));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.pow(_selfz, exponentZ));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, long exponent) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _exponentx = UnsafeOpsHolder.U.getDouble(exponent);
        double _exponenty = UnsafeOpsHolder.U.getDouble(exponent + 8L);
        double _exponentz = UnsafeOpsHolder.U.getDouble(exponent + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.pow(_selfx, _exponentx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.pow(_selfy, _exponenty));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.pow(_selfz, _exponentz));
        return dest;
    }

    public static long project_unsafe(long dest, long src, double ontoX, double ontoY, double ontoZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t7 = java.lang.Math.fma(ontoZ, UnsafeOpsHolder.U.getDouble(src + 16L), java.lang.Math.fma(ontoX, UnsafeOpsHolder.U.getDouble(src), ontoY * UnsafeOpsHolder.U.getDouble(src + 8L))) / java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY));
            UnsafeOpsHolder.U.putDouble(dest, ontoX * _t7);
            UnsafeOpsHolder.U.putDouble(dest + 8L, ontoY * _t7);
            UnsafeOpsHolder.U.putDouble(dest + 16L, ontoZ * _t7);
            return dest;
        } else {
            double _t7 = ((ontoZ) * (UnsafeOpsHolder.U.getDouble(src + 16L)) + (((ontoX) * (UnsafeOpsHolder.U.getDouble(src)) + (ontoY * UnsafeOpsHolder.U.getDouble(src + 8L))))) / ((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))));
            UnsafeOpsHolder.U.putDouble(dest, ontoX * _t7);
            UnsafeOpsHolder.U.putDouble(dest + 8L, ontoY * _t7);
            UnsafeOpsHolder.U.putDouble(dest + 16L, ontoZ * _t7);
            return dest;
        }
    }

    public static long project_unsafe(long dest, long src, long onto) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_unsafe_mulAdd(dest, src, onto);
        double _ontox = UnsafeOpsHolder.U.getDouble(onto);
        double _ontoy = UnsafeOpsHolder.U.getDouble(onto + 8L);
        double _ontoz = UnsafeOpsHolder.U.getDouble(onto + 16L);
        double _t7 = java.lang.Math.fma(_ontoz, UnsafeOpsHolder.U.getDouble(src + 16L), java.lang.Math.fma(_ontox, UnsafeOpsHolder.U.getDouble(src), _ontoy * UnsafeOpsHolder.U.getDouble(src + 8L))) / java.lang.Math.fma(_ontoz, _ontoz, java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy));
        UnsafeOpsHolder.U.putDouble(dest, _ontox * _t7);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _ontoy * _t7);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _ontoz * _t7);
        return dest;
    }

    /** {@code project_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long project_unsafe_mulAdd(long dest, long src, long onto) {
        double _ontox = UnsafeOpsHolder.U.getDouble(onto);
        double _ontoy = UnsafeOpsHolder.U.getDouble(onto + 8L);
        double _ontoz = UnsafeOpsHolder.U.getDouble(onto + 16L);
        double _t7 = ((_ontoz) * (UnsafeOpsHolder.U.getDouble(src + 16L)) + (((_ontox) * (UnsafeOpsHolder.U.getDouble(src)) + (_ontoy * UnsafeOpsHolder.U.getDouble(src + 8L))))) / ((_ontoz) * (_ontoz) + (((_ontox) * (_ontox) + (_ontoy * _ontoy))));
        UnsafeOpsHolder.U.putDouble(dest, _ontox * _t7);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _ontoy * _t7);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _ontoz * _t7);
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, double normalX, double normalY, double normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t2 = java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-normalX, _t2, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-normalY, _t2, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-normalZ, _t2, _selfz));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t2 = ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
            UnsafeOpsHolder.U.putDouble(dest, ((_selfx) - (normalX) * (_t2)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) - (normalY) * (_t2)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) - (normalZ) * (_t2)));
            return dest;
        }
    }

    public static long projectOnPlane_unsafe(long dest, long src, long normal) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _normalx = UnsafeOpsHolder.U.getDouble(normal);
            double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
            double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
            double _t2 = java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-_normalx, _t2, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-_normaly, _t2, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-_normalz, _t2, _selfz));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _normalx = UnsafeOpsHolder.U.getDouble(normal);
            double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
            double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
            double _t2 = ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
            UnsafeOpsHolder.U.putDouble(dest, ((_selfx) - (_normalx) * (_t2)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) - (_normaly) * (_t2)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) - (_normalz) * (_t2)));
            return dest;
        }
    }

    public static long radians_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.toRadians(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.toRadians(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.toRadians(_selfz));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, double normalX, double normalY, double normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t3 = 2.0 * java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-normalX, _t3, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-normalY, _t3, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-normalZ, _t3, _selfz));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t3 = 2.0 * ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
            UnsafeOpsHolder.U.putDouble(dest, ((_selfx) - (normalX) * (_t3)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) - (normalY) * (_t3)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) - (normalZ) * (_t3)));
            return dest;
        }
    }

    public static long reflect_unsafe(long dest, long src, long normal) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_unsafe_mulAdd(dest, src, normal);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _t3 = 2.0 * java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-_normalx, _t3, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-_normaly, _t3, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-_normalz, _t3, _selfz));
        return dest;
    }

    /** {@code reflect_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long reflect_unsafe_mulAdd(long dest, long src, long normal) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _t3 = 2.0 * ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) - (_normalx) * (_t3)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) - (_normaly) * (_t3)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) - (_normalz) * (_t3)));
        return dest;
    }

    public static long refract_unsafe(long dest, long src, double normalX, double normalY, double normalZ, double eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_unsafe_mulAdd(dest, src, normalX, normalY, normalZ, eta);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t3 = java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
        double _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0), eta * eta, 1.0);
        double _t10 = java.lang.Math.fma(eta, _t3, java.lang.Math.sqrt(Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(eta, _selfx, -(normalX * _t10)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(eta, _selfy, -(normalY * _t10)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(eta, _selfz, -(normalZ * _t10)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    /** {@code refract_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long refract_unsafe_mulAdd(long dest, long src, double normalX, double normalY, double normalZ, double eta) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t3 = ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
        double _t7 = ((((_t3) * (_t3) - (1.0))) * (eta * eta) + (1.0));
        double _t10 = ((eta) * (_t3) + (java.lang.Math.sqrt(Math.max(0.0, _t7))));
        if (_t7 >= 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, ((eta) * (_selfx) - (normalX * _t10)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((eta) * (_selfy) - (normalY * _t10)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((eta) * (_selfz) - (normalZ * _t10)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long refract_unsafe(long dest, long src, long normal, double eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_unsafe_mulAdd(dest, src, normal, eta);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _t3 = java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
        double _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0), eta * eta, 1.0);
        double _t10 = java.lang.Math.fma(eta, _t3, java.lang.Math.sqrt(Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(eta, _selfx, -(_normalx * _t10)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(eta, _selfy, -(_normaly * _t10)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(eta, _selfz, -(_normalz * _t10)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    /** {@code refract_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long refract_unsafe_mulAdd(long dest, long src, long normal, double eta) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _normalx = UnsafeOpsHolder.U.getDouble(normal);
        double _normaly = UnsafeOpsHolder.U.getDouble(normal + 8L);
        double _normalz = UnsafeOpsHolder.U.getDouble(normal + 16L);
        double _t3 = ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
        double _t7 = ((((_t3) * (_t3) - (1.0))) * (eta * eta) + (1.0));
        double _t10 = ((eta) * (_t3) + (java.lang.Math.sqrt(Math.max(0.0, _t7))));
        if (_t7 >= 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, ((eta) * (_selfx) - (_normalx * _t10)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((eta) * (_selfy) - (_normaly * _t10)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((eta) * (_selfz) - (_normalz * _t10)));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long round_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.rint(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.rint(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.rint(_selfz));
        return dest;
    }

    public static long sign_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.signum(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.signum(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.signum(_selfz));
        return dest;
    }

    public static long sin_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.sin(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.sin(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.sin(_selfz));
        return dest;
    }

    public static long sinh_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.sinh(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.sinh(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.sinh(_selfz));
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, double edge0, double edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_mulAdd(dest, src, edge0, edge1);
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t10 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - edge0) * _t0_inv));
        double _t11 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - edge0) * _t0_inv));
        double _t12 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - edge0) * _t0_inv));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, _t10, 3.0) * _t10 * _t10);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-2.0, _t11, 3.0) * _t11 * _t11);
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, double edge0, double edge1) {
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t10 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - edge0) * _t0_inv));
        double _t11 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - edge0) * _t0_inv));
        double _t12 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - edge0) * _t0_inv));
        UnsafeOpsHolder.U.putDouble(dest, ((3.0) - (2.0) * (_t10)) * _t10 * _t10);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((3.0) - (2.0) * (_t11)) * _t11 * _t11);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((3.0) - (2.0) * (_t12)) * _t12 * _t12);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_mulAdd(dest, src, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        double _t12 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - edge0X) / (edge1X - edge0X)));
        double _t13 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - edge0Y) / (edge1Y - edge0Y)));
        double _t14 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - edge0Z) / (edge1Z - edge0Z)));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        double _t12 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - edge0X) / (edge1X - edge0X)));
        double _t13 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - edge0Y) / (edge1Y - edge0Y)));
        double _t14 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - edge0Z) / (edge1Z - edge0Z)));
        UnsafeOpsHolder.U.putDouble(dest, ((3.0) - (2.0) * (_t12)) * _t12 * _t12);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((3.0) - (2.0) * (_t13)) * _t13 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((3.0) - (2.0) * (_t14)) * _t14 * _t14);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, long edge0, long edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_mulAdd(dest, src, edge0, edge1);
        double _edge0x = UnsafeOpsHolder.U.getDouble(edge0);
        double _edge0y = UnsafeOpsHolder.U.getDouble(edge0 + 8L);
        double _edge0z = UnsafeOpsHolder.U.getDouble(edge0 + 16L);
        double _t12 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - _edge0x) / (UnsafeOpsHolder.U.getDouble(edge1) - _edge0x)));
        double _t13 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - _edge0y) / (UnsafeOpsHolder.U.getDouble(edge1 + 8L) - _edge0y)));
        double _t14 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - _edge0z) / (UnsafeOpsHolder.U.getDouble(edge1 + 16L) - _edge0z)));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, long edge0, long edge1) {
        double _edge0x = UnsafeOpsHolder.U.getDouble(edge0);
        double _edge0y = UnsafeOpsHolder.U.getDouble(edge0 + 8L);
        double _edge0z = UnsafeOpsHolder.U.getDouble(edge0 + 16L);
        double _t12 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src) - _edge0x) / (UnsafeOpsHolder.U.getDouble(edge1) - _edge0x)));
        double _t13 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 8L) - _edge0y) / (UnsafeOpsHolder.U.getDouble(edge1 + 8L) - _edge0y)));
        double _t14 = Math.max(0.0, Math.min(1.0, (UnsafeOpsHolder.U.getDouble(src + 16L) - _edge0z) / (UnsafeOpsHolder.U.getDouble(edge1 + 16L) - _edge0z)));
        UnsafeOpsHolder.U.putDouble(dest, ((3.0) - (2.0) * (_t12)) * _t12 * _t12);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((3.0) - (2.0) * (_t13)) * _t13 * _t13);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((3.0) - (2.0) * (_t14)) * _t14 * _t14);
        return dest;
    }

    public static long sqrt_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.sqrt(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.sqrt(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.sqrt(_selfz));
        return dest;
    }

    public static long step_unsafe(long dest, long src, double edge) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx < edge ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy < edge ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz < edge ? 0.0 : 1.0);
        return dest;
    }

    public static long step_unsafe(long dest, long src, double edgeX, double edgeY, double edgeZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx < edgeX ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy < edgeY ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz < edgeZ ? 0.0 : 1.0);
        return dest;
    }

    public static long step_unsafe(long dest, long src, long edge) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _edgex = UnsafeOpsHolder.U.getDouble(edge);
        double _edgey = UnsafeOpsHolder.U.getDouble(edge + 8L);
        double _edgez = UnsafeOpsHolder.U.getDouble(edge + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx < _edgex ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy < _edgey ? 0.0 : 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz < _edgez ? 0.0 : 1.0);
        return dest;
    }

    public static long tan_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.tan(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.tan(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.tan(_selfz));
        return dest;
    }

    public static long tanh_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.tanh(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.tanh(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.tanh(_selfz));
        return dest;
    }

    public static long triangleNormal_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = p1Y - _selfy;
        double _t1 = p2Z - _selfz;
        double _t2 = p1Z - _selfz;
        double _t3 = p2Y - _selfy;
        double _t4 = p1X - _selfx;
        double _t5 = p2X - _selfx;
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = java.lang.Math.fma(_t0, _t1, _t9) - java.lang.Math.fma(_t2, _t3, _t9);
        double _t19 = java.lang.Math.fma(_t4, _t3, _t10) - java.lang.Math.fma(_t0, _t5, _t10);
        double _t20 = java.lang.Math.fma(_t2, _t5, _t11) - java.lang.Math.fma(_t4, _t1, _t11);
        double _ct0 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.triangleNormal_degenerate(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t25 = (1.0 / java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putDouble(dest, _t18 * _t25);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t20 * _t25);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t19 * _t25);
        return dest;
    }

    /** {@code triangleNormal_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_unsafe_mulAdd(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = p1Y - _selfy;
        double _t1 = p2Z - _selfz;
        double _t2 = p1Z - _selfz;
        double _t3 = p2Y - _selfy;
        double _t4 = p1X - _selfx;
        double _t5 = p2X - _selfx;
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = ((_t0) * (_t1) + (_t9)) - ((_t2) * (_t3) + (_t9));
        double _t19 = ((_t4) * (_t3) + (_t10)) - ((_t0) * (_t5) + (_t10));
        double _t20 = ((_t2) * (_t5) + (_t11)) - ((_t4) * (_t1) + (_t11));
        double _ct0 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.triangleNormal_degenerate(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t25 = (1.0 / java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putDouble(dest, _t18 * _t25);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t20 * _t25);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t19 * _t25);
        return dest;
    }

    public static long triangleNormal_degenerate(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.triangleNormal_degenerate_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        Double3OpsKernelsSegment.triangleNormal_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static long triangleNormal_degenerate_unsafe(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_unsafe_fma(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return triangleNormal_degenerate_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    /** {@code triangleNormal_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_degenerate_unsafe_fma(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = _selfx * _t19;
        double _t32 = _selfy * _t19;
        double _t34 = _selfz * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        double _t41 = p2Y * _t19 - _t32;
        double _t42 = p2X * _t19 - _t30;
        double _t43 = p2Z * _t19 - _t34;
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        return triangleNormal_degenerate_unsafe_fma_s86db5eb0_1(dest, _t52, _t53, _t54, _t55, _t56, _t57, -(_t54 * _t55), -(_t57 * _t53), -(_t52 * _t56));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long triangleNormal_degenerate_unsafe_fma_s86db5eb0_1(long dest, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t61, double _t62, double _t63) {
        double _t70 = java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61);
        double _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        double _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t83 * _t78);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t83 * _t79);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t83 * _t77);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    /** {@code triangleNormal_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_degenerate_unsafe_mulAdd(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = _selfx * _t19;
        double _t32 = _selfy * _t19;
        double _t34 = _selfz * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        double _t41 = p2Y * _t19 - _t32;
        double _t42 = p2X * _t19 - _t30;
        double _t43 = p2Z * _t19 - _t34;
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        return triangleNormal_degenerate_unsafe_mulAdd_s19554b4f_1(dest, _t52, _t53, _t54, _t55, _t56, _t57, -(_t54 * _t55), -(_t57 * _t53), -(_t52 * _t56));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long triangleNormal_degenerate_unsafe_mulAdd_s19554b4f_1(long dest, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t61, double _t62, double _t63) {
        double _t70 = ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61));
        double _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        double _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t83 * _t78);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t83 * _t79);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t83 * _t77);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long triangleNormal_unsafe(long dest, long src, long p1, long p2) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_unsafe_fma(dest, src, p1, p2);
        return triangleNormal_unsafe_mulAdd(dest, src, p1, p2);
    }

    /** {@code triangleNormal_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_unsafe_fma(long dest, long src, long p1, long p2) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = UnsafeOpsHolder.U.getDouble(p1 + 8L) - _selfy;
        double _t1 = UnsafeOpsHolder.U.getDouble(p2 + 16L) - _selfz;
        double _t2 = UnsafeOpsHolder.U.getDouble(p1 + 16L) - _selfz;
        double _t3 = UnsafeOpsHolder.U.getDouble(p2 + 8L) - _selfy;
        double _t4 = UnsafeOpsHolder.U.getDouble(p1) - _selfx;
        double _t5 = UnsafeOpsHolder.U.getDouble(p2) - _selfx;
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = java.lang.Math.fma(_t0, _t1, _t9) - java.lang.Math.fma(_t2, _t3, _t9);
        double _t19 = java.lang.Math.fma(_t4, _t3, _t10) - java.lang.Math.fma(_t0, _t5, _t10);
        double _t20 = java.lang.Math.fma(_t2, _t5, _t11) - java.lang.Math.fma(_t4, _t1, _t11);
        double _ct0 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.triangleNormal_degenerate(dest, src, p1, p2);
        return triangleNormal_unsafe_fma_s19449a3b_1(dest, _t18, _t19, _t20, (1.0 / java.lang.Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code triangleNormal_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long triangleNormal_unsafe_fma_s19449a3b_1(long dest, double _t18, double _t19, double _t20, double _t25) {
        UnsafeOpsHolder.U.putDouble(dest, _t18 * _t25);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t20 * _t25);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t19 * _t25);
        return dest;
    }

    /** {@code triangleNormal_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_unsafe_mulAdd(long dest, long src, long p1, long p2) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = UnsafeOpsHolder.U.getDouble(p1 + 8L) - _selfy;
        double _t1 = UnsafeOpsHolder.U.getDouble(p2 + 16L) - _selfz;
        double _t2 = UnsafeOpsHolder.U.getDouble(p1 + 16L) - _selfz;
        double _t3 = UnsafeOpsHolder.U.getDouble(p2 + 8L) - _selfy;
        double _t4 = UnsafeOpsHolder.U.getDouble(p1) - _selfx;
        double _t5 = UnsafeOpsHolder.U.getDouble(p2) - _selfx;
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = ((_t0) * (_t1) + (_t9)) - ((_t2) * (_t3) + (_t9));
        double _t19 = ((_t4) * (_t3) + (_t10)) - ((_t0) * (_t5) + (_t10));
        double _t20 = ((_t2) * (_t5) + (_t11)) - ((_t4) * (_t1) + (_t11));
        double _ct0 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsAddress.triangleNormal_degenerate(dest, src, p1, p2);
        return triangleNormal_unsafe_mulAdd_sfd3a8b3a_1(dest, _t18, _t19, _t20, (1.0 / java.lang.Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code triangleNormal_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long triangleNormal_unsafe_mulAdd_sfd3a8b3a_1(long dest, double _t18, double _t19, double _t20, double _t25) {
        UnsafeOpsHolder.U.putDouble(dest, _t18 * _t25);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t20 * _t25);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _t19 * _t25);
        return dest;
    }

    public static long triangleNormal_degenerate(long dest, long src, long p1, long p2) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.triangleNormal_degenerate_unsafe(dest, src, p1, p2);
        Double3OpsKernelsSegment.triangleNormal_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p1, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p2, 24L), 0L);
        return dest;
    }

    public static long triangleNormal_degenerate_unsafe(long dest, long src, long p1, long p2) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_unsafe_fma(dest, src, p1, p2);
        return triangleNormal_degenerate_unsafe_mulAdd(dest, src, p1, p2);
    }

    /** {@code triangleNormal_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_degenerate_unsafe_fma(long dest, long src, long p1, long p2) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        double _t30 = _selfx * _t19;
        double _t32 = _selfy * _t19;
        double _t34 = _selfz * _t19;
        double _t38 = _p1x * _t19 - _t30;
        double _t39 = _p1y * _t19 - _t32;
        double _t40 = _p1z * _t19 - _t34;
        double _t41 = _p2y * _t19 - _t32;
        double _t42 = _p2x * _t19 - _t30;
        double _t43 = _p2z * _t19 - _t34;
        return triangleNormal_degenerate_unsafe_fma_sbe30bab4_1(dest, _t38, _t39, _t40, _t41, _t42, _t43, unitScale(_t38, _t39, _t40), unitScale(_t42, _t41, _t43));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long triangleNormal_degenerate_unsafe_fma_sbe30bab4_1(long dest, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45) {
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t61 = -(_t54 * _t55);
        double _t62 = -(_t57 * _t53);
        double _t63 = -(_t52 * _t56);
        double _t70 = java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61);
        double _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        double _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t83 * _t78);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t83 * _t79);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t83 * _t77);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    /** {@code triangleNormal_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_degenerate_unsafe_mulAdd(long dest, long src, long p1, long p2) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _p1x = UnsafeOpsHolder.U.getDouble(p1);
        double _p1y = UnsafeOpsHolder.U.getDouble(p1 + 8L);
        double _p1z = UnsafeOpsHolder.U.getDouble(p1 + 16L);
        double _p2x = UnsafeOpsHolder.U.getDouble(p2);
        double _p2y = UnsafeOpsHolder.U.getDouble(p2 + 8L);
        double _p2z = UnsafeOpsHolder.U.getDouble(p2 + 16L);
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        double _t30 = _selfx * _t19;
        double _t32 = _selfy * _t19;
        double _t34 = _selfz * _t19;
        double _t38 = _p1x * _t19 - _t30;
        double _t39 = _p1y * _t19 - _t32;
        double _t40 = _p1z * _t19 - _t34;
        double _t41 = _p2y * _t19 - _t32;
        double _t42 = _p2x * _t19 - _t30;
        double _t43 = _p2z * _t19 - _t34;
        return triangleNormal_degenerate_unsafe_mulAdd_sd5cce0e3_1(dest, _t38, _t39, _t40, _t41, _t42, _t43, unitScale(_t38, _t39, _t40), unitScale(_t42, _t41, _t43));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long triangleNormal_degenerate_unsafe_mulAdd_sd5cce0e3_1(long dest, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45) {
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t61 = -(_t54 * _t55);
        double _t62 = -(_t57 * _t53);
        double _t63 = -(_t52 * _t56);
        double _t70 = ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61));
        double _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        double _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t83 * _t78);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t83 * _t79);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t83 * _t77);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long trunc_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz >= 0.0 ? Math.floor(_selfz) : Math.ceil(_selfz));
        return dest;
    }

    public static long ulp_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, Math.ulp(_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.ulp(_selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.ulp(_selfz));
        return dest;
    }

    public static long xyz0_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        return dest;
    }

    public static long xyz1_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long mat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_unsafe_mulAdd(dest, src, mat);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 24L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 56L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_mat02, _selfz, java.lang.Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_mulAdd(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 24L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 56L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        UnsafeOpsHolder.U.putDouble(dest, ((_mat02) * (_selfz) + (((_mat00) * (_selfx) + (_mat01 * _selfy)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static long preMulDirectionMat3x4_unsafe(long dest, long src, long mat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulDirectionMat3x4_unsafe_mulAdd(dest, src, mat);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 72L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 80L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_mat02, _selfz, java.lang.Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMulDirectionMat3x4_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulDirectionMat3x4_unsafe_mulAdd(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 72L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 80L);
        UnsafeOpsHolder.U.putDouble(dest, ((_mat02) * (_selfz) + (((_mat00) * (_selfx) + (_mat01 * _selfy)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static long preMulDirectionMat4x4_unsafe(long dest, long src, long mat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulDirectionMat4x4_unsafe_mulAdd(dest, src, mat);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 72L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 80L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_mat02, _selfz, java.lang.Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMulDirectionMat4x4_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulDirectionMat4x4_unsafe_mulAdd(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 72L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 80L);
        UnsafeOpsHolder.U.putDouble(dest, ((_mat02) * (_selfz) + (((_mat00) * (_selfx) + (_mat01 * _selfy)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static long preMulPositionMat3x4_unsafe(long dest, long src, long mat) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat3x4_unsafe_fma(dest, src, mat);
        return preMulPositionMat3x4_unsafe_mulAdd(dest, src, mat);
    }

    /** {@code preMulPositionMat3x4_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulPositionMat3x4_unsafe_fma(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat03 = UnsafeOpsHolder.U.getDouble(mat + 24L);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat13 = UnsafeOpsHolder.U.getDouble(mat + 56L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 72L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 80L);
        double _mat23 = UnsafeOpsHolder.U.getDouble(mat + 88L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_mat00, _selfx, java.lang.Math.fma(_mat01, _selfy, java.lang.Math.fma(_mat02, _selfz, _mat03))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    /** {@code preMulPositionMat3x4_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulPositionMat3x4_unsafe_mulAdd(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat03 = UnsafeOpsHolder.U.getDouble(mat + 24L);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat13 = UnsafeOpsHolder.U.getDouble(mat + 56L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 72L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 80L);
        double _mat23 = UnsafeOpsHolder.U.getDouble(mat + 88L);
        UnsafeOpsHolder.U.putDouble(dest, ((_mat00) * (_selfx) + (((_mat01) * (_selfy) + (((_mat02) * (_selfz) + (_mat03)))))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))));
        return dest;
    }

    public static long preMulPositionMat4x4_unsafe(long dest, long src, long mat) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat4x4_unsafe_fma(dest, src, mat);
        return preMulPositionMat4x4_unsafe_mulAdd(dest, src, mat);
    }

    /** {@code preMulPositionMat4x4_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulPositionMat4x4_unsafe_fma(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 72L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 80L);
        double _mat03 = UnsafeOpsHolder.U.getDouble(mat + 96L);
        double _mat13 = UnsafeOpsHolder.U.getDouble(mat + 104L);
        double _mat23 = UnsafeOpsHolder.U.getDouble(mat + 112L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_mat00, _selfx, java.lang.Math.fma(_mat01, _selfy, java.lang.Math.fma(_mat02, _selfz, _mat03))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    /** {@code preMulPositionMat4x4_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulPositionMat4x4_unsafe_mulAdd(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 72L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 80L);
        double _mat03 = UnsafeOpsHolder.U.getDouble(mat + 96L);
        double _mat13 = UnsafeOpsHolder.U.getDouble(mat + 104L);
        double _mat23 = UnsafeOpsHolder.U.getDouble(mat + 112L);
        UnsafeOpsHolder.U.putDouble(dest, ((_mat00) * (_selfx) + (((_mat01) * (_selfy) + (((_mat02) * (_selfz) + (_mat03)))))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))));
        return dest;
    }

    public static long preMulProject_unsafe(long dest, long src, long mat) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulProject_unsafe_fma(dest, src, mat);
        return preMulProject_unsafe_mulAdd(dest, src, mat);
    }

    /** {@code preMulProject_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulProject_unsafe_fma(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 72L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 80L);
        double _mat03 = UnsafeOpsHolder.U.getDouble(mat + 96L);
        double _mat13 = UnsafeOpsHolder.U.getDouble(mat + 104L);
        double _mat23 = UnsafeOpsHolder.U.getDouble(mat + 112L);
        return preMulProject_unsafe_fma_sc229c305_1(dest, mat, _selfx, _selfy, _selfz, _mat00, _mat10, _mat20, _mat01, _mat11, _mat21, _mat02, _mat12, _mat22, _mat03, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulProject_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preMulProject_unsafe_fma_sc229c305_1(long dest, long mat, double _selfx, double _selfy, double _selfz, double _mat00, double _mat10, double _mat20, double _mat01, double _mat11, double _mat21, double _mat02, double _mat12, double _mat22, double _mat03, double _mat13, double _mat23) {
        double _t2_inv = 1.0 / java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(mat + 24L), _selfx, java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(mat + 56L), _selfy, java.lang.Math.fma(UnsafeOpsHolder.U.getDouble(mat + 88L), _selfz, UnsafeOpsHolder.U.getDouble(mat + 120L))));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_mat00, _selfx, java.lang.Math.fma(_mat01, _selfy, java.lang.Math.fma(_mat02, _selfz, _mat03))) * _t2_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))) * _t2_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))) * _t2_inv);
        return dest;
    }

    /** {@code preMulProject_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulProject_unsafe_mulAdd(long dest, long src, long mat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _mat00 = UnsafeOpsHolder.U.getDouble(mat);
        double _mat10 = UnsafeOpsHolder.U.getDouble(mat + 8L);
        double _mat20 = UnsafeOpsHolder.U.getDouble(mat + 16L);
        double _mat01 = UnsafeOpsHolder.U.getDouble(mat + 32L);
        double _mat11 = UnsafeOpsHolder.U.getDouble(mat + 40L);
        double _mat21 = UnsafeOpsHolder.U.getDouble(mat + 48L);
        double _mat02 = UnsafeOpsHolder.U.getDouble(mat + 64L);
        double _mat12 = UnsafeOpsHolder.U.getDouble(mat + 72L);
        double _mat22 = UnsafeOpsHolder.U.getDouble(mat + 80L);
        double _mat03 = UnsafeOpsHolder.U.getDouble(mat + 96L);
        double _mat13 = UnsafeOpsHolder.U.getDouble(mat + 104L);
        double _mat23 = UnsafeOpsHolder.U.getDouble(mat + 112L);
        return preMulProject_unsafe_mulAdd_sa199ea6e_1(dest, mat, _selfx, _selfy, _selfz, _mat00, _mat10, _mat20, _mat01, _mat11, _mat21, _mat02, _mat12, _mat22, _mat03, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulProject_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preMulProject_unsafe_mulAdd_sa199ea6e_1(long dest, long mat, double _selfx, double _selfy, double _selfz, double _mat00, double _mat10, double _mat20, double _mat01, double _mat11, double _mat21, double _mat02, double _mat12, double _mat22, double _mat03, double _mat13, double _mat23) {
        double _t2_inv = 1.0 / ((UnsafeOpsHolder.U.getDouble(mat + 24L)) * (_selfx) + (((UnsafeOpsHolder.U.getDouble(mat + 56L)) * (_selfy) + (((UnsafeOpsHolder.U.getDouble(mat + 88L)) * (_selfz) + (UnsafeOpsHolder.U.getDouble(mat + 120L)))))));
        UnsafeOpsHolder.U.putDouble(dest, ((_mat00) * (_selfx) + (((_mat01) * (_selfy) + (((_mat02) * (_selfz) + (_mat03)))))) * _t2_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))) * _t2_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))) * _t2_inv);
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, double quatX, double quatY, double quatZ, double quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_unsafe_mulAdd(dest, src, quatX, quatY, quatZ, quatW);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t9 = 2.0 * java.lang.Math.fma(quatX, _selfy, -(quatY * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(quatZ, _selfx, -(quatX * _selfz));
        double _t11 = 2.0 * java.lang.Math.fma(quatY, _selfz, -(quatZ * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, _selfz))));
        return dest;
    }

    /** {@code rotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_mulAdd(long dest, long src, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t9 = 2.0 * ((quatX) * (_selfy) - (quatY * _selfx));
        double _t10 = 2.0 * ((quatZ) * (_selfx) - (quatX * _selfz));
        double _t11 = 2.0 * ((quatY) * (_selfz) - (quatZ * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, ((quatY) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatZ) * (_t10)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((quatZ) * (_t11) + (((((quatW) * (_t10) + (_selfy))) - (quatX) * (_t9)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfz))) - (quatY) * (_t11)))));
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, long quat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_unsafe_mulAdd(dest, src, quat);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
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
        return dest;
    }

    /** {@code rotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_mulAdd(long dest, long src, long quat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
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
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_unsafe_mulAdd(dest, src, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = _selfy - pivotY;
        double _t1 = _selfx - pivotX;
        double _t2 = _selfz - pivotZ;
        double _t12 = 2.0 * java.lang.Math.fma(quatX, _t0, -(quatY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(quatZ, _t1, -(quatX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(quatY, _t2, -(quatZ * _t0));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(quatY, _t12, java.lang.Math.fma(-quatZ, _t13, java.lang.Math.fma(quatW, _t14, _selfx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(quatZ, _t14, java.lang.Math.fma(-quatX, _t12, java.lang.Math.fma(quatW, _t13, _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(quatX, _t13, java.lang.Math.fma(-quatY, _t14, java.lang.Math.fma(quatW, _t12, _selfz))));
        return dest;
    }

    /** {@code rotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_mulAdd(long dest, long src, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = _selfy - pivotY;
        double _t1 = _selfx - pivotX;
        double _t2 = _selfz - pivotZ;
        double _t12 = 2.0 * ((quatX) * (_t0) - (quatY * _t1));
        double _t13 = 2.0 * ((quatZ) * (_t1) - (quatX * _t2));
        double _t14 = 2.0 * ((quatY) * (_t2) - (quatZ * _t0));
        UnsafeOpsHolder.U.putDouble(dest, ((quatY) * (_t12) + (((((quatW) * (_t14) + (_selfx))) - (quatZ) * (_t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((quatZ) * (_t14) + (((((quatW) * (_t13) + (_selfy))) - (quatX) * (_t12)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((quatX) * (_t13) + (((((quatW) * (_t12) + (_selfz))) - (quatY) * (_t14)))));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long quat, long pivot) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_unsafe_fma(dest, src, quat, pivot);
        return rotateAround_unsafe_mulAdd(dest, src, quat, pivot);
    }

    /** {@code rotateAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_fma(long dest, long src, long quat, long pivot) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _quatx = UnsafeOpsHolder.U.getDouble(quat);
        double _quaty = UnsafeOpsHolder.U.getDouble(quat + 8L);
        double _quatz = UnsafeOpsHolder.U.getDouble(quat + 16L);
        double _quatw = UnsafeOpsHolder.U.getDouble(quat + 24L);
        double _t0 = _selfy - UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t1 = _selfx - UnsafeOpsHolder.U.getDouble(pivot);
        double _t2 = _selfz - UnsafeOpsHolder.U.getDouble(pivot + 16L);
        double _t12 = 2.0 * java.lang.Math.fma(_quatx, _t0, -(_quaty * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(_quatz, _t1, -(_quatx * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(_quaty, _t2, -(_quatz * _t0));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_quaty, _t12, java.lang.Math.fma(-_quatz, _t13, java.lang.Math.fma(_quatw, _t14, _selfx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_quatz, _t14, java.lang.Math.fma(-_quatx, _t12, java.lang.Math.fma(_quatw, _t13, _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_quatx, _t13, java.lang.Math.fma(-_quaty, _t14, java.lang.Math.fma(_quatw, _t12, _selfz))));
        return dest;
    }

    /** {@code rotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_mulAdd(long dest, long src, long quat, long pivot) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _quatx = UnsafeOpsHolder.U.getDouble(quat);
        double _quaty = UnsafeOpsHolder.U.getDouble(quat + 8L);
        double _quatz = UnsafeOpsHolder.U.getDouble(quat + 16L);
        double _quatw = UnsafeOpsHolder.U.getDouble(quat + 24L);
        double _t0 = _selfy - UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t1 = _selfx - UnsafeOpsHolder.U.getDouble(pivot);
        double _t2 = _selfz - UnsafeOpsHolder.U.getDouble(pivot + 16L);
        double _t12 = 2.0 * ((_quatx) * (_t0) - (_quaty * _t1));
        double _t13 = 2.0 * ((_quatz) * (_t1) - (_quatx * _t2));
        double _t14 = 2.0 * ((_quaty) * (_t2) - (_quatz * _t0));
        UnsafeOpsHolder.U.putDouble(dest, ((_quaty) * (_t12) + (((((_quatw) * (_t14) + (_selfx))) - (_quatz) * (_t13)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_quatz) * (_t14) + (((((_quatw) * (_t13) + (_selfy))) - (_quatx) * (_t12)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_quatx) * (_t13) + (((((_quatw) * (_t12) + (_selfz))) - (_quaty) * (_t14)))));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_mulAdd(dest, src, angle, axisX, axisY, axisZ);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t1)));
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        UnsafeOpsHolder.U.putDouble(dest, ((_t2) * (axisX * _t5) + (((_selfx) * (_t0) + (((axisY) * (_selfz) - (axisZ * _selfy)) * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t2) * (axisY * _t5) + (((_selfy) * (_t0) + (((axisZ) * (_selfx) - (axisX * _selfz)) * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t2) * (axisZ * _t5) + (((_selfz) * (_t0) + (((axisX) * (_selfy) - (axisY * _selfx)) * _t1)))));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_mulAdd(dest, src, axis, angle);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t2, _axisx * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t2, _axisy * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t2, _axisz * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t1)));
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, long axis, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        UnsafeOpsHolder.U.putDouble(dest, ((_t2) * (_axisx * _t5) + (((_selfx) * (_t0) + (((_axisy) * (_selfz) - (_axisz * _selfy)) * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t2) * (_axisy * _t5) + (((_selfy) * (_t0) + (((_axisz) * (_selfx) - (_axisx * _selfz)) * _t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t2) * (_axisz * _t5) + (((_selfz) * (_t0) + (((_axisx) * (_selfy) - (_axisy * _selfx)) * _t1)))));
        return dest;
    }

    public static long rotateAxisAround_unsafe(long dest, long src, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxisAround_unsafe_mulAdd(dest, src, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = UnsafeOpsHolder.U.getDouble(src) - pivotX;
        double _t4 = UnsafeOpsHolder.U.getDouble(src + 16L) - pivotZ;
        double _t5 = UnsafeOpsHolder.U.getDouble(src + 8L) - pivotY;
        double _t8 = java.lang.Math.fma(axisZ, _t4, java.lang.Math.fma(axisX, _t3, axisY * _t5));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(java.lang.Math.fma(axisY, _t4, -(axisZ * _t5)), _t1, java.lang.Math.fma(_t2, axisX * _t8, pivotX))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t5, _t0, java.lang.Math.fma(java.lang.Math.fma(axisZ, _t3, -(axisX * _t4)), _t1, java.lang.Math.fma(_t2, axisY * _t8, pivotY))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t4, _t0, java.lang.Math.fma(java.lang.Math.fma(axisX, _t5, -(axisY * _t3)), _t1, java.lang.Math.fma(_t2, axisZ * _t8, pivotZ))));
        return dest;
    }

    /** {@code rotateAxisAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxisAround_unsafe_mulAdd(long dest, long src, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = UnsafeOpsHolder.U.getDouble(src) - pivotX;
        double _t4 = UnsafeOpsHolder.U.getDouble(src + 16L) - pivotZ;
        double _t5 = UnsafeOpsHolder.U.getDouble(src + 8L) - pivotY;
        double _t8 = ((axisZ) * (_t4) + (((axisX) * (_t3) + (axisY * _t5))));
        UnsafeOpsHolder.U.putDouble(dest, ((_t3) * (_t0) + (((((axisY) * (_t4) - (axisZ * _t5))) * (_t1) + (((_t2) * (axisX * _t8) + (pivotX)))))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t5) * (_t0) + (((((axisZ) * (_t3) - (axisX * _t4))) * (_t1) + (((_t2) * (axisY * _t8) + (pivotY)))))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t4) * (_t0) + (((((axisX) * (_t5) - (axisY * _t3))) * (_t1) + (((_t2) * (axisZ * _t8) + (pivotZ)))))));
        return dest;
    }

    public static long rotateAxisAround_unsafe(long dest, long src, long axis, long pivot, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxisAround_unsafe_fma(dest, src, axis, pivot, angle);
        return rotateAxisAround_unsafe_mulAdd(dest, src, axis, pivot, angle);
    }

    /** {@code rotateAxisAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxisAround_unsafe_fma(long dest, long src, long axis, long pivot, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(pivot + 16L);
        double _t2 = 1.0 - _t0;
        double _t3 = UnsafeOpsHolder.U.getDouble(src) - _pivotx;
        double _t4 = UnsafeOpsHolder.U.getDouble(src + 16L) - _pivotz;
        double _t5 = UnsafeOpsHolder.U.getDouble(src + 8L) - _pivoty;
        double _t8 = java.lang.Math.fma(_axisz, _t4, java.lang.Math.fma(_axisx, _t3, _axisy * _t5));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisy, _t4, -(_axisz * _t5)), _t1, java.lang.Math.fma(_t2, _axisx * _t8, _pivotx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t5, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisz, _t3, -(_axisx * _t4)), _t1, java.lang.Math.fma(_t2, _axisy * _t8, _pivoty))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t4, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisx, _t5, -(_axisy * _t3)), _t1, java.lang.Math.fma(_t2, _axisz * _t8, _pivotz))));
        return dest;
    }

    /** {@code rotateAxisAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxisAround_unsafe_mulAdd(long dest, long src, long axis, long pivot, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(pivot + 16L);
        double _t2 = 1.0 - _t0;
        double _t3 = UnsafeOpsHolder.U.getDouble(src) - _pivotx;
        double _t4 = UnsafeOpsHolder.U.getDouble(src + 16L) - _pivotz;
        double _t5 = UnsafeOpsHolder.U.getDouble(src + 8L) - _pivoty;
        double _t8 = ((_axisz) * (_t4) + (((_axisx) * (_t3) + (_axisy * _t5))));
        UnsafeOpsHolder.U.putDouble(dest, ((_t3) * (_t0) + (((((_axisy) * (_t4) - (_axisz * _t5))) * (_t1) + (((_t2) * (_axisx * _t8) + (_pivotx)))))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t5) * (_t0) + (((((_axisz) * (_t3) - (_axisx * _t4))) * (_t1) + (((_t2) * (_axisy * _t8) + (_pivoty)))))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t4) * (_t0) + (((((_axisx) * (_t5) - (_axisy * _t3))) * (_t1) + (((_t2) * (_axisz * _t8) + (_pivotz)))))));
        return dest;
    }

    public static long rotateInverse_unsafe(long dest, long src, double quatX, double quatY, double quatZ, double quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_unsafe_mulAdd(dest, src, quatX, quatY, quatZ, quatW);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t9 = 2.0 * java.lang.Math.fma(quatX, _selfz, -(quatZ * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(quatY, _selfx, -(quatX * _selfy));
        double _t11 = 2.0 * java.lang.Math.fma(quatZ, _selfy, -(quatY * _selfz));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, _selfy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfz))));
        return dest;
    }

    /** {@code rotateInverse_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateInverse_unsafe_mulAdd(long dest, long src, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t9 = 2.0 * ((quatX) * (_selfz) - (quatZ * _selfx));
        double _t10 = 2.0 * ((quatY) * (_selfx) - (quatX * _selfy));
        double _t11 = 2.0 * ((quatZ) * (_selfy) - (quatY * _selfz));
        UnsafeOpsHolder.U.putDouble(dest, ((quatZ) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatY) * (_t10)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfy))) - (quatZ) * (_t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((quatY) * (_t11) + (((((quatW) * (_t10) + (_selfz))) - (quatX) * (_t9)))));
        return dest;
    }

    public static long rotateInverse_unsafe(long dest, long src, long quat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_unsafe_mulAdd(dest, src, quat);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
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
        return dest;
    }

    /** {@code rotateInverse_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateInverse_unsafe_mulAdd(long dest, long src, long quat) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
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
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putDouble(dest, _selfx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t0, -(_selfz * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfy, _t1, _selfz * _t0));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putDouble(dest, _selfx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t0) - (_selfz * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfy) * (_t1) + (_selfz * _t0)));
            return dest;
        }
    }

    public static long rotateXAround_unsafe(long dest, long src, double angle, double pivotY, double pivotZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = UnsafeOpsHolder.U.getDouble(src + 8L) - pivotY;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 16L) - pivotZ;
            UnsafeOpsHolder.U.putDouble(dest, _selfx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotY)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotZ)));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = UnsafeOpsHolder.U.getDouble(src + 8L) - pivotY;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 16L) - pivotZ;
            UnsafeOpsHolder.U.putDouble(dest, _selfx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t2) * (_t0) + (((pivotY) - (_t3) * (_t1)))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotZ)))));
            return dest;
        }
    }

    public static long rotateXAround_unsafe(long dest, long src, long pivot, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateXAround_unsafe_mulAdd(dest, src, pivot, angle);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(pivot + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = UnsafeOpsHolder.U.getDouble(src + 8L) - _pivoty;
        double _t3 = UnsafeOpsHolder.U.getDouble(src + 16L) - _pivotz;
        UnsafeOpsHolder.U.putDouble(dest, _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, _pivoty)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, _pivotz)));
        return dest;
    }

    /** {@code rotateXAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXAround_unsafe_mulAdd(long dest, long src, long pivot, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(pivot + 16L);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = UnsafeOpsHolder.U.getDouble(src + 8L) - _pivoty;
        double _t3 = UnsafeOpsHolder.U.getDouble(src + 16L) - _pivotz;
        UnsafeOpsHolder.U.putDouble(dest, _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t2) * (_t0) + (((_pivoty) - (_t3) * (_t1)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t2) * (_t1) + (((_t3) * (_t0) + (_pivotz)))));
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t0, _selfz * _t1));
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t0, -(_selfx * _t1)));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t0) + (_selfz * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t0) - (_selfx * _t1)));
            return dest;
        }
    }

    public static long rotateYAround_unsafe(long dest, long src, double angle, double pivotX, double pivotZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - pivotX;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 16L) - pivotZ;
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotX)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(-_t2, _t1, pivotZ)));
            return dest;
        } else {
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - pivotX;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 16L) - pivotZ;
            UnsafeOpsHolder.U.putDouble(dest, ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotX)))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t3) * (_t0) + (((pivotZ) - (_t2) * (_t1)))));
            return dest;
        }
    }

    public static long rotateYAround_unsafe(long dest, long src, long pivot, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
            double _pivotz = UnsafeOpsHolder.U.getDouble(pivot + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - _pivotx;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 16L) - _pivotz;
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, _pivotx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(-_t2, _t1, _pivotz)));
            return dest;
        } else {
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
            double _pivotz = UnsafeOpsHolder.U.getDouble(pivot + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - _pivotx;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 16L) - _pivotz;
            UnsafeOpsHolder.U.putDouble(dest, ((_t2) * (_t0) + (((_t3) * (_t1) + (_pivotx)))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t3) * (_t0) + (((_pivotz) - (_t2) * (_t1)))));
            return dest;
        }
    }

    public static long rotateZ_unsafe(long dest, long src, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t0, -(_selfy * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfx, _t1, _selfy * _t0));
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t0) - (_selfy * _t1)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfx) * (_t1) + (_selfy * _t0)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
            return dest;
        }
    }

    public static long rotateZAround_unsafe(long dest, long src, double angle, double pivotX, double pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - pivotX;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - pivotY;
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotX)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotY)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
            return dest;
        } else {
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - pivotX;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - pivotY;
            UnsafeOpsHolder.U.putDouble(dest, ((_t2) * (_t0) + (((pivotX) - (_t3) * (_t1)))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotY)))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
            return dest;
        }
    }

    public static long rotateZAround_unsafe(long dest, long src, long pivot, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
            double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - _pivotx;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - _pivoty;
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, _pivotx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, _pivoty)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
            return dest;
        } else {
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _pivotx = UnsafeOpsHolder.U.getDouble(pivot);
            double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = UnsafeOpsHolder.U.getDouble(src) - _pivotx;
            double _t3 = UnsafeOpsHolder.U.getDouble(src + 8L) - _pivoty;
            UnsafeOpsHolder.U.putDouble(dest, ((_t2) * (_t0) + (((_pivotx) - (_t3) * (_t1)))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t2) * (_t1) + (((_t3) * (_t0) + (_pivoty)))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
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
