// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float3OpsKernelsAddress {
    private Float3OpsKernelsAddress() {}

    public static long add_unsafe(long dest, long src, float otherX, float otherY, float otherZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, otherX + _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, otherY + _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, otherZ + _selfz);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _otherx + _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _othery + _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _otherz + _selfz);
        return dest;
    }

    public static long div_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx / scalar);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / scalar);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz / scalar);
        return dest;
    }

    public static long div_unsafe(long dest, long src, float otherX, float otherY, float otherZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx / otherX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / otherY);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz / otherZ);
        return dest;
    }

    public static long div_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx / _otherx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / _othery);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz / _otherz);
        return dest;
    }

    public static long fma_unsafe(long dest, long src, float b, float cX, float cY, float cZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, b, cX));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, b, cY));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, b, cZ));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (b) + (cX)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (b) + (cY)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (b) + (cZ)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, long c, float b) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _cx = UnsafeOpsHolder.U.getFloat(c);
            float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
            float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, b, _cx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, b, _cy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, b, _cz));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _cx = UnsafeOpsHolder.U.getFloat(c);
            float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
            float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (b) + (_cx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (b) + (_cy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (b) + (_cz)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, bX, cX));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, bY, cY));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, bZ, cZ));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (bX) + (cX)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (bY) + (cY)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (bZ) + (cZ)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, long b, long c) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_unsafe_mulAdd(dest, src, b, c);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _bx = UnsafeOpsHolder.U.getFloat(b);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _cx = UnsafeOpsHolder.U.getFloat(c);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _bx, _cx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _by, _cy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _bz, _cz));
        return dest;
    }

    /** {@code fma_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long fma_unsafe_mulAdd(long dest, long src, long b, long c) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _bx = UnsafeOpsHolder.U.getFloat(b);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _cx = UnsafeOpsHolder.U.getFloat(c);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_bx) + (_cx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_by) + (_cy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_bz) + (_cz)));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, scalar * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, scalar * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, scalar * _selfz);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float otherX, float otherY, float otherZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, otherX * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, otherY * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, otherZ * _selfz);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _otherx * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _othery * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _otherz * _selfz);
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, -_selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_selfz);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, float otherX, float otherY, float otherZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx - otherX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy - otherY);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz - otherZ);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx - _otherx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy - _othery);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz - _otherz);
        return dest;
    }

    public static long makeUniformDirection_unsafe(long dest, float u, float v) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t1 = v * 6.2831855f;
            float _t4 = 2.0f * (float) java.lang.Math.sqrt(u * (1.0f - u));
            UnsafeOpsHolder.U.putFloat(dest, _t4 * Math.cosWithSin(_t1));
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t4 * Math.sinWithCos(_t1));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(2.0f, u, -1.0f));
            return dest;
        } else {
            float _t1 = v * 6.2831855f;
            float _t4 = 2.0f * (float) java.lang.Math.sqrt(u * (1.0f - u));
            UnsafeOpsHolder.U.putFloat(dest, _t4 * Math.cosWithSin(_t1));
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t4 * Math.sinWithCos(_t1));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((2.0f) * (u) - (1.0f)));
            return dest;
        }
    }

    public static long set_unsafe(long dest, float vX, float vY, float vZ) {
        UnsafeOpsHolder.U.putFloat(dest, vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, vY);
        UnsafeOpsHolder.U.putFloat(dest + 8L, vZ);
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _vy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _vz);
        return dest;
    }

    public static long set_unsafe(long dest, float s) {
        UnsafeOpsHolder.U.putFloat(dest, s);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s);
        return dest;
    }

    public static long makeZero_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(p1X, _t7, _selfx * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(p1Y, _t7, _selfy * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(p1Z, _t7, _selfz * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2));
        return dest;
    }

    /** {@code bezier_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, ((p1X) * (_t7) + (_selfx * _t8)) + ((p2X) * (_t6) + (p3X * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((p1Y) * (_t7) + (_selfy * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((p1Z) * (_t7) + (_selfz * _t8)) + ((p2Z) * (_t6) + (p3Z * _t2)));
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier_unsafe_fma(dest, src, p1, p2, p3, t);
        return bezier_unsafe_mulAdd(dest, src, p1, p2, p3, t);
    }

    /** {@code bezier_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier_unsafe_fma(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_p1x, _t7, _selfx * _t8) + java.lang.Math.fma(_p2x, _t6, _p3x * _t2));
        return bezier_unsafe_fma_s136b7288_1(dest, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t2, _t6, _t7, _t8);
    }

    /** Piece 2 of {@code bezier_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long bezier_unsafe_fma_s136b7288_1(long dest, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t2, float _t6, float _t7, float _t8) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_p1z, _t7, _selfz * _t8) + java.lang.Math.fma(_p2z, _t6, _p3z * _t2));
        return dest;
    }

    /** {@code bezier_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, ((_p1x) * (_t7) + (_selfx * _t8)) + ((_p2x) * (_t6) + (_p3x * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_p1z) * (_t7) + (_selfz * _t8)) + ((_p2z) * (_t6) + (_p3z * _t2)));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t0 = t * t;
            float _t1 = 1.0f - t;
            float _t3 = (t + t) * _t1;
            float _t4 = _t1 * _t1;
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, _selfx * _t4)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, _selfy * _t4)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, _selfz * _t4)));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t0 = t * t;
            float _t1 = 1.0f - t;
            float _t3 = (t + t) * _t1;
            float _t4 = _t1 * _t1;
            UnsafeOpsHolder.U.putFloat(dest, ((p2X) * (_t0) + (((p1X) * (_t3) + (_selfx * _t4)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((p2Y) * (_t0) + (((p1Y) * (_t3) + (_selfy * _t4)))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((p2Z) * (_t0) + (((p1Z) * (_t3) + (_selfz * _t4)))));
            return dest;
        }
    }

    public static long bezier2_unsafe(long dest, long src, long p1, long p2, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_unsafe_mulAdd(dest, src, p1, p2, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_p2x, _t0, java.lang.Math.fma(_p1x, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_p2z, _t0, java.lang.Math.fma(_p1z, _t3, _selfz * _t4)));
        return dest;
    }

    /** {@code bezier2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2_unsafe_mulAdd(long dest, long src, long p1, long p2, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, ((_p2x) * (_t0) + (((_p1x) * (_t3) + (_selfx * _t4)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_p2z) * (_t0) + (((_p1z) * (_t3) + (_selfz * _t4)))));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t1 = t + t;
            float _t2 = 2.0f * (1.0f - t);
            UnsafeOpsHolder.U.putFloat(dest, ((p1X - _selfx) * (_t2) + ((p2X - p1X) * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((p1Z - _selfz) * (_t2) + ((p2Z - p1Z) * _t1)));
            return dest;
        }
    }

    public static long bezier2Tangent_unsafe(long dest, long src, long p1, long p2, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_unsafe_mulAdd(dest, src, p1, p2, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2Tangent_unsafe_mulAdd(long dest, long src, long p1, long p2, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        UnsafeOpsHolder.U.putFloat(dest, ((_p1x - _selfx) * (_t2) + ((_p2x - _p1x) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_p1z - _selfz) * (_t2) + ((_p2z - _p1z) * _t1)));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezierTangent_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, ((p3X - p2X) * (_t2) + (((p1X - _selfx) * (_t6) + ((p2X - p1X) * _t5)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((p3Z - p2Z) * (_t2) + (((p1Z - _selfz) * (_t6) + ((p2Z - p1Z) * _t5)))));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_unsafe_fma(dest, src, p1, p2, p3, t);
        return bezierTangent_unsafe_mulAdd(dest, src, p1, p2, p3, t);
    }

    /** {@code bezierTangent_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezierTangent_unsafe_fma(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_p3x - _p2x, _t2, java.lang.Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_p3z - _p2z, _t2, java.lang.Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezierTangent_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, ((_p3x - _p2x) * (_t2) + (((_p1x - _selfx) * (_t6) + ((_p2x - _p1x) * _t5)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_p3z - _p2z) * (_t2) + (((_p1z - _selfz) * (_t6) + ((_p2z - _p1z) * _t5)))));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_unsafe_fma(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return catmullRom_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@code catmullRom_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_fma(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * (java.lang.Math.fma(2.0f, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (java.lang.Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (java.lang.Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * (((2.0f) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) * (_t0) + (((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X)) * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (((2.0f) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) * (_t0) + (((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y)) * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (((2.0f) * (p1Z) + (t * (p2Z - _selfz))) + ((((((2.0f) * (_selfz) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) * (_t0) + (((((3.0f) * (p1Z) + (p3Z - _selfz))) - (3.0f) * (p2Z)) * _t1))));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_unsafe_fma(dest, src, p1, p2, p3, t);
        return catmullRom_unsafe_mulAdd(dest, src, p1, p2, p3, t);
    }

    /** {@code catmullRom_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_fma(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * (java.lang.Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        return catmullRom_unsafe_fma_s6d33ebad_1(dest, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_fma_s6d33ebad_1(long dest, float t, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t0, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (java.lang.Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (java.lang.Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, _p2z, -_p3z))), _t0, java.lang.Math.fma(-3.0f, _p2z, java.lang.Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * (((2.0f) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) * (_t0) + (((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x)) * _t1))));
        return catmullRom_unsafe_mulAdd_s34ebee96_1(dest, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_mulAdd_s34ebee96_1(long dest, float t, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t0, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (((2.0f) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) * (_t0) + (((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y)) * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (((2.0f) * (_p1z) + (t * (_p2z - _selfz))) + ((((((2.0f) * (_selfz) + (((4.0f) * (_p2z) - (_p3z))))) - (5.0f) * (_p1z))) * (_t0) + (((((3.0f) * (_p1z) + (_p3z - _selfz))) - (3.0f) * (_p2z)) * _t1))));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_unsafe_fma(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return catmullRomTangent_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@code catmullRomTangent_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_fma(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, p2Z, -p3Z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        return dest;
    }

    /** {@code catmullRomTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) + (((3.0f * ((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X))) * (_t0) + (p2X - _selfx)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) + (((3.0f * ((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y))) * (_t0) + (p2Y - _selfy)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfz) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) + (((3.0f * ((((3.0f) * (p1Z) + (p3Z - _selfz))) - (3.0f) * (p2Z))) * (_t0) + (p2Z - _selfz)))));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, long p1, long p2, long p3, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_unsafe_fma(dest, src, p1, p2, p3, t);
        return catmullRomTangent_unsafe_mulAdd(dest, src, p1, p2, p3, t);
    }

    /** {@code catmullRomTangent_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_fma(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        return catmullRomTangent_unsafe_fma_s6ecbdfbc_1(dest, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_fma_s6ecbdfbc_1(long dest, float t, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t0) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, _p2z, -_p3z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2z, java.lang.Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return dest;
    }

    /** {@code catmullRomTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) + (((3.0f * ((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x))) * (_t0) + (_p2x - _selfx)))));
        return catmullRomTangent_unsafe_mulAdd_s28928921_1(dest, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd_s28928921_1(long dest, float t, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t0) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) + (((3.0f * ((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y))) * (_t0) + (_p2y - _selfy)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfz) + (((4.0f) * (_p2z) - (_p3z))))) - (5.0f) * (_p1z))) + (((3.0f * ((((3.0f) * (_p1z) + (_p3z - _selfz))) - (3.0f) * (_p2z))) * (_t0) + (_p2z - _selfz)))));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermite_unsafe_mulAdd(dest, src, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * java.lang.Math.fma(t, t, -t);
        float _t7 = java.lang.Math.fma(t - 2.0f, _t0, t);
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
        return dest;
    }

    /** {@code hermite_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermite_unsafe_mulAdd(long dest, long src, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9)));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, long t0, long v1, long t1, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermite_unsafe_fma(dest, src, t0, v1, t1, t);
        return hermite_unsafe_mulAdd(dest, src, t0, v1, t1, t);
    }

    /** {@code hermite_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermite_unsafe_fma(long dest, long src, long t0, long v1, long t1, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _t0z = UnsafeOpsHolder.U.getFloat(t0 + 8L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _v1z = UnsafeOpsHolder.U.getFloat(v1 + 8L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t1z = UnsafeOpsHolder.U.getFloat(t1 + 8L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * java.lang.Math.fma(t, t, -t);
        float _t7 = java.lang.Math.fma(t - 2.0f, _t0, t);
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t10, _t0x * _t7) + java.lang.Math.fma(_t1x, _t5, _v1x * _t9));
        return hermite_unsafe_fma_s3652656d_1(dest, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, _t5, _t7, _t9, _t10);
    }

    /** Piece 2 of {@code hermite_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long hermite_unsafe_fma_s3652656d_1(long dest, float _selfy, float _selfz, float _t0y, float _t0z, float _v1y, float _v1z, float _t1y, float _t1z, float _t5, float _t7, float _t9, float _t10) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t10, _t0z * _t7) + java.lang.Math.fma(_t1z, _t5, _v1z * _t9));
        return dest;
    }

    /** {@code hermite_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermite_unsafe_mulAdd(long dest, long src, long t0, long v1, long t1, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _t0z = UnsafeOpsHolder.U.getFloat(t0 + 8L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _v1z = UnsafeOpsHolder.U.getFloat(v1 + 8L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t1z = UnsafeOpsHolder.U.getFloat(t1 + 8L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t10) + (_t0x * _t7)) + ((_t1x) * (_t5) + (_v1x * _t9)));
        return hermite_unsafe_mulAdd_s97f7bc6a_1(dest, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, _t5, _t7, _t9, _t10);
    }

    /** Piece 2 of {@code hermite_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long hermite_unsafe_mulAdd_s97f7bc6a_1(long dest, float _selfy, float _selfz, float _t0y, float _t0z, float _v1y, float _v1z, float _t1y, float _t1z, float _t5, float _t7, float _t9, float _t10) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t10) + (_t0z * _t7)) + ((_t1z) * (_t5) + (_v1z * _t9)));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_unsafe_mulAdd(dest, src, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = t * t;
        float _t6 = 6.0f * java.lang.Math.fma(t, t, -t);
        float _t7 = 6.0f * java.lang.Math.fma(-t, t, t);
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
        return dest;
    }

    /** {@code hermiteTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd(long dest, long src, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7)));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, long t0, long v1, long t1, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_unsafe_fma(dest, src, t0, v1, t1, t);
        return hermiteTangent_unsafe_mulAdd(dest, src, t0, v1, t1, t);
    }

    /** {@code hermiteTangent_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermiteTangent_unsafe_fma(long dest, long src, long t0, long v1, long t1, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _t0z = UnsafeOpsHolder.U.getFloat(t0 + 8L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _v1z = UnsafeOpsHolder.U.getFloat(v1 + 8L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t1z = UnsafeOpsHolder.U.getFloat(t1 + 8L);
        float _t0 = t * t;
        float _t6 = 6.0f * java.lang.Math.fma(t, t, -t);
        float _t7 = 6.0f * java.lang.Math.fma(-t, t, t);
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t6, _t0x * _t9) + java.lang.Math.fma(_t1x, _t8, _v1x * _t7));
        return hermiteTangent_unsafe_fma_s2b22155c_1(dest, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, _t6, _t7, _t8, _t9);
    }

    /** Piece 2 of {@code hermiteTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long hermiteTangent_unsafe_fma_s2b22155c_1(long dest, float _selfy, float _selfz, float _t0y, float _t0z, float _v1y, float _v1z, float _t1y, float _t1z, float _t6, float _t7, float _t8, float _t9) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t6, _t0z * _t9) + java.lang.Math.fma(_t1z, _t8, _v1z * _t7));
        return dest;
    }

    /** {@code hermiteTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd(long dest, long src, long t0, long v1, long t1, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _t0z = UnsafeOpsHolder.U.getFloat(t0 + 8L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _v1z = UnsafeOpsHolder.U.getFloat(v1 + 8L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t1z = UnsafeOpsHolder.U.getFloat(t1 + 8L);
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t6) + (_t0x * _t9)) + ((_t1x) * (_t8) + (_v1x * _t7)));
        return hermiteTangent_unsafe_mulAdd_se595d3d1_1(dest, _selfy, _selfz, _t0y, _t0z, _v1y, _v1z, _t1y, _t1z, _t6, _t7, _t8, _t9);
    }

    /** Piece 2 of {@code hermiteTangent_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd_se595d3d1_1(long dest, float _selfy, float _selfz, float _t0y, float _t0z, float _v1y, float _v1z, float _t1y, float _t1z, float _t6, float _t7, float _t8, float _t9) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t6) + (_t0z * _t9)) + ((_t1z) * (_t8) + (_v1z * _t7)));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (otherZ - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, long other, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _otherx = UnsafeOpsHolder.U.getFloat(other);
            float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
            float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _otherx = UnsafeOpsHolder.U.getFloat(other);
            float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
            float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (_othery - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (_otherz - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(tX, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(tY, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(tZ, otherZ - _selfz, _selfz));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, ((tX) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((tY) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((tZ) * (otherZ - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, long other, long t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_unsafe_mulAdd(dest, src, other, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _tx = UnsafeOpsHolder.U.getFloat(t);
        float _ty = UnsafeOpsHolder.U.getFloat(t + 4L);
        float _tz = UnsafeOpsHolder.U.getFloat(t + 8L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_tx, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_ty, _othery - _selfy, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_tz, _otherz - _selfz, _selfz));
        return dest;
    }

    /** {@code lerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lerp_unsafe_mulAdd(long dest, long src, long other, long t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _tx = UnsafeOpsHolder.U.getFloat(t);
        float _ty = UnsafeOpsHolder.U.getFloat(t + 4L);
        float _tz = UnsafeOpsHolder.U.getFloat(t + 8L);
        UnsafeOpsHolder.U.putFloat(dest, ((_tx) * (_otherx - _selfx) + (_selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_ty) * (_othery - _selfy) + (_selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_tz) * (_otherz - _selfz) + (_selfz)));
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, otherX, otherY, otherZ, t);
        return slerp_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, t);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, float otherX, float otherY, float otherZ, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t);
        float _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_unsafe_fma_sc6bb0ea4_1(dest, src, otherX, otherY, otherZ, t, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), java.lang.Math.fma(otherX, _t12, -(_t26 * _t14)), java.lang.Math.fma(otherY, _t12, -(_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sc6bb0ea4_1(long dest, long src, float otherX, float otherY, float otherZ, float t, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33, float _t34, float _t35) {
        float _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        float _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        float _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        float _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float otherZ, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t);
        float _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        return slerp_unsafe_mulAdd_s981b0ea5_1(dest, src, otherX, otherY, otherZ, t, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, ((otherZ) * (_t12) - (_t26 * _t17)), ((otherX) * (_t12) - (_t26 * _t14)), ((otherY) * (_t12) - (_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s981b0ea5_1(long dest, long src, float otherX, float otherY, float otherZ, float t, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33, float _t34, float _t35) {
        float _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        float _t40 = ((_t39) * (_t17) + (_t33));
        float _t41 = ((_t39) * (_t14) + (_t34));
        float _t42 = ((_t39) * (_t20) + (_t35));
        float _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putFloat(dest, ((_t14) * (_t53) + (_sp0 * _t41)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t20) * (_t53) + (_sp0 * _t42)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, float otherX, float otherY, float otherZ, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, otherZ, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long slerp_degenerate_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, otherX, otherY, otherZ, t);
        return slerp_degenerate_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, t);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, float otherX, float otherY, float otherZ, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        float _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        float _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float otherZ, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        float _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        float _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        float _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        float _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        float _t74 = ((_t73) * (_t31) + (_t61));
        float _t75 = ((_t73) * (_t33) + (_t62));
        float _t76 = ((_t73) * (_t35) + (_t63));
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (otherZ - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long other, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, other, t);
        return slerp_unsafe_mulAdd(dest, src, other, t);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t8 = java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = java.lang.Math.fma(_otherz * _t12, _t17, java.lang.Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        return slerp_unsafe_fma_sff32df1b_1(dest, src, other, t, _otherx, _othery, _t12, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(_otherz, _t12, -(_t26 * _t17)));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sff32df1b_1(long dest, long src, long other, float t, float _otherx, float _othery, float _t12, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33) {
        float _t34 = java.lang.Math.fma(_otherx, _t12, -(_t26 * _t14));
        float _t35 = java.lang.Math.fma(_othery, _t12, -(_t26 * _t20));
        float _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        float _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        float _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        float _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t8 = ((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = ((_otherz * _t12) * (_t17) + (((_otherx * _t12) * (_t14) + (_othery * _t12 * _t20))));
        return slerp_unsafe_mulAdd_s4d4fe30a_1(dest, src, other, t, _othery, _t12, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, ((_otherz) * (_t12) - (_t26 * _t17)), ((_otherx) * (_t12) - (_t26 * _t14)));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s4d4fe30a_1(long dest, long src, long other, float t, float _othery, float _t12, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33, float _t34) {
        float _t35 = ((_othery) * (_t12) - (_t26 * _t20));
        float _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        float _t40 = ((_t39) * (_t17) + (_t33));
        float _t41 = ((_t39) * (_t14) + (_t34));
        float _t42 = ((_t39) * (_t20) + (_t35));
        float _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putFloat(dest, ((_t14) * (_t53) + (_sp0 * _t41)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t20) * (_t53) + (_sp0 * _t42)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long slerp_degenerate_unsafe(long dest, long src, long other, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, other, t);
        return slerp_degenerate_unsafe_mulAdd(dest, src, other, t);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        float _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        float _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        float _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        float _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        float _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        float _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        float _t74 = ((_t73) * (_t31) + (_t61));
        float _t75 = ((_t73) * (_t33) + (_t62));
        float _t76 = ((_t73) * (_t35) + (_t63));
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (_othery - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (_otherz - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, otherX, otherY, otherZ, t, epsilon);
        return slerp_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, t, epsilon);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, float otherX, float otherY, float otherZ, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t, epsilon);
        float _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t, epsilon);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_unsafe_fma_sb85d2568_1(dest, src, otherX, otherY, otherZ, t, epsilon, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), java.lang.Math.fma(otherX, _t12, -(_t26 * _t14)), java.lang.Math.fma(otherY, _t12, -(_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sb85d2568_1(long dest, long src, float otherX, float otherY, float otherZ, float t, float epsilon, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33, float _t34, float _t35) {
        float _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        float _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        float _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        float _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > epsilon && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t, epsilon);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float otherZ, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t, epsilon);
        float _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t, epsilon);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        return slerp_unsafe_mulAdd_s3442adab_1(dest, src, otherX, otherY, otherZ, t, epsilon, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, ((otherZ) * (_t12) - (_t26 * _t17)), ((otherX) * (_t12) - (_t26 * _t14)), ((otherY) * (_t12) - (_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s3442adab_1(long dest, long src, float otherX, float otherY, float otherZ, float t, float epsilon, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33, float _t34, float _t35) {
        float _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        float _t40 = ((_t39) * (_t17) + (_t33));
        float _t41 = ((_t39) * (_t14) + (_t34));
        float _t42 = ((_t39) * (_t20) + (_t35));
        float _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > epsilon && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, t, epsilon);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putFloat(dest, ((_t14) * (_t53) + (_sp0 * _t41)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t20) * (_t53) + (_sp0 * _t42)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, float otherX, float otherY, float otherZ, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, otherZ, t, epsilon);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long slerp_degenerate_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, otherX, otherY, otherZ, t, epsilon);
        return slerp_degenerate_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, t, epsilon);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, float otherX, float otherY, float otherZ, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        float _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        float _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float otherZ, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        float _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        float _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        float _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        float _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        float _t74 = ((_t73) * (_t31) + (_t61));
        float _t75 = ((_t73) * (_t33) + (_t62));
        float _t76 = ((_t73) * (_t35) + (_t63));
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (otherZ - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long other, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, other, t, epsilon);
        return slerp_unsafe_mulAdd(dest, src, other, t, epsilon);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, long other, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t8 = java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        return slerp_unsafe_fma_s306ec141_1(dest, src, other, t, epsilon, _otherx, _othery, _otherz, _t12, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), java.lang.Math.fma(_otherz * _t12, _t17, java.lang.Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20)));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s306ec141_1(long dest, long src, long other, float t, float epsilon, float _otherx, float _othery, float _otherz, float _t12, float _t14, float _t17, float _t20, float _t24, float _t26) {
        float _t33 = java.lang.Math.fma(_otherz, _t12, -(_t26 * _t17));
        float _t34 = java.lang.Math.fma(_otherx, _t12, -(_t26 * _t14));
        float _t35 = java.lang.Math.fma(_othery, _t12, -(_t26 * _t20));
        float _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        float _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        float _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        float _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > epsilon && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, long other, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t8 = ((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = ((_otherz * _t12) * (_t17) + (((_otherx * _t12) * (_t14) + (_othery * _t12 * _t20))));
        return slerp_unsafe_mulAdd_s152962c6_1(dest, src, other, t, epsilon, _otherx, _othery, _t12, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, ((_otherz) * (_t12) - (_t26 * _t17)));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s152962c6_1(long dest, long src, long other, float t, float epsilon, float _otherx, float _othery, float _t12, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33) {
        float _t34 = ((_otherx) * (_t12) - (_t26 * _t14));
        float _t35 = ((_othery) * (_t12) - (_t26 * _t20));
        float _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        float _t40 = ((_t39) * (_t17) + (_t33));
        float _t41 = ((_t39) * (_t14) + (_t34));
        float _t42 = ((_t39) * (_t20) + (_t35));
        float _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > epsilon && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _t53 = _t24 * Math.cosWithSin(_t50);
        float _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        UnsafeOpsHolder.U.putFloat(dest, ((_t14) * (_t53) + (_sp0 * _t41)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t20) * (_t53) + (_sp0 * _t42)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t, epsilon);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long slerp_degenerate_unsafe(long dest, long src, long other, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, other, t, epsilon);
        return slerp_degenerate_unsafe_mulAdd(dest, src, other, t, epsilon);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, long other, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        float _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        float _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, long other, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        float _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        float _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        float _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        float _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        float _t74 = ((_t73) * (_t31) + (_t61));
        float _t75 = ((_t73) * (_t33) + (_t62));
        float _t76 = ((_t73) * (_t35) + (_t63));
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t98 = _t48 * Math.sinWithCos(_t95);
        float _t99 = _t48 * Math.cosWithSin(_t95);
        float _t103, _t104, _t105;
        if (_t91 > 0.0f) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (_othery - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (_otherz - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static long absolute_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.abs(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.abs(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.abs(_selfz));
        return dest;
    }

    public static long acos_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.acos(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.acos(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.acos(_selfz));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, float bX, float bY, float bZ, float scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(scalar, bX, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(scalar, bY, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(scalar, bZ, _selfz));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, ((scalar) * (bX) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((scalar) * (bY) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((scalar) * (bZ) + (_selfz)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, long b, float scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _bx = UnsafeOpsHolder.U.getFloat(b);
            float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
            float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(scalar, _bx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(scalar, _by, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(scalar, _bz, _selfz));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _bx = UnsafeOpsHolder.U.getFloat(b);
            float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
            float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
            UnsafeOpsHolder.U.putFloat(dest, ((scalar) * (_bx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((scalar) * (_by) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((scalar) * (_bz) + (_selfz)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(bX, cX, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(bY, cY, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(bZ, cZ, _selfz));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, ((bX) * (cX) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((bY) * (cY) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((bZ) * (cZ) + (_selfz)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, long b, long c) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_unsafe_mulAdd(dest, src, b, c);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _bx = UnsafeOpsHolder.U.getFloat(b);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _cx = UnsafeOpsHolder.U.getFloat(c);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_bx, _cx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_by, _cy, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_bz, _cz, _selfz));
        return dest;
    }

    /** {@code addScaled_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long addScaled_unsafe_mulAdd(long dest, long src, long b, long c) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _bx = UnsafeOpsHolder.U.getFloat(b);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _cx = UnsafeOpsHolder.U.getFloat(c);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        UnsafeOpsHolder.U.putFloat(dest, ((_bx) * (_cx) + (_selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_by) * (_cy) + (_selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_bz) * (_cz) + (_selfz)));
        return dest;
    }

    public static float angleBetween_unsafe(long src, float otherX, float otherY, float otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_unsafe_mulAdd(src, otherX, otherY, otherZ);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t3 = otherY * -_selfz;
        float _t4 = -(otherX * _selfy);
        float _t5 = otherX * -_selfz;
        float _t12 = java.lang.Math.fma(otherZ, _selfy, _t3) - java.lang.Math.fma(otherY, _selfz, _t3);
        float _t13 = java.lang.Math.fma(otherY, _selfx, _t4) - java.lang.Math.fma(otherX, _selfy, _t4);
        float _t14 = java.lang.Math.fma(otherZ, _selfx, _t5) - java.lang.Math.fma(otherX, _selfz, _t5);
        float _ct0 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY, otherZ);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    /** {@code angleBetween_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_unsafe_mulAdd(long src, float otherX, float otherY, float otherZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t3 = otherY * -_selfz;
        float _t4 = -(otherX * _selfy);
        float _t5 = otherX * -_selfz;
        float _t12 = ((otherZ) * (_selfy) + (_t3)) - ((otherY) * (_selfz) + (_t3));
        float _t13 = ((otherY) * (_selfx) + (_t4)) - ((otherX) * (_selfy) + (_t4));
        float _t14 = ((otherZ) * (_selfx) + (_t5)) - ((otherX) * (_selfz) + (_t5));
        float _ct0 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY, otherZ);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))));
    }

    public static float angleBetween_degenerate(long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.angleBetween_degenerate_unsafe(src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static float angleBetween_degenerate_unsafe(long src, float otherX, float otherY, float otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_unsafe_mulAdd(src, otherX, otherY, otherZ);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = unitScale(otherX, otherY, otherZ);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = otherZ * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = otherY * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = otherX * _t0;
        float _t17 = _t10 * -_t11;
        float _t18 = -(_t13 * _t9);
        float _t19 = _t13 * -_t11;
        float _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        float _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        float _t29 = unitScale(_t27, _t28, _t26);
        float _t33 = _t26 * _t29;
        float _t34 = _t27 * _t29;
        float _t35 = _t28 * _t29;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /** {@code angleBetween_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_unsafe_mulAdd(long src, float otherX, float otherY, float otherZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = unitScale(otherX, otherY, otherZ);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = otherZ * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = otherY * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = otherX * _t0;
        float _t17 = _t10 * -_t11;
        float _t18 = -(_t13 * _t9);
        float _t19 = _t13 * -_t11;
        float _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        float _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        float _t29 = unitScale(_t27, _t28, _t26);
        float _t33 = _t26 * _t29;
        float _t34 = _t27 * _t29;
        float _t35 = _t28 * _t29;
        return Math.atan2((float) java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
    }

    public static float angleBetween_unsafe(long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_unsafe_mulAdd(src, other);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t3 = _othery * -_selfz;
        float _t4 = -(_otherx * _selfy);
        float _t5 = _otherx * -_selfz;
        float _t12 = java.lang.Math.fma(_otherz, _selfy, _t3) - java.lang.Math.fma(_othery, _selfz, _t3);
        float _t13 = java.lang.Math.fma(_othery, _selfx, _t4) - java.lang.Math.fma(_otherx, _selfy, _t4);
        float _t14 = java.lang.Math.fma(_otherz, _selfx, _t5) - java.lang.Math.fma(_otherx, _selfz, _t5);
        float _ct0 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.angleBetween_degenerate(src, other);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    /** {@code angleBetween_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_unsafe_mulAdd(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t3 = _othery * -_selfz;
        float _t4 = -(_otherx * _selfy);
        float _t5 = _otherx * -_selfz;
        float _t12 = ((_otherz) * (_selfy) + (_t3)) - ((_othery) * (_selfz) + (_t3));
        float _t13 = ((_othery) * (_selfx) + (_t4)) - ((_otherx) * (_selfy) + (_t4));
        float _t14 = ((_otherz) * (_selfx) + (_t5)) - ((_otherx) * (_selfz) + (_t5));
        float _ct0 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.angleBetween_degenerate(src, other);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))));
    }

    public static float angleBetween_degenerate(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.angleBetween_degenerate_unsafe(src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static float angleBetween_degenerate_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_unsafe_fma(src, other);
        return angleBetween_degenerate_unsafe_mulAdd(src, other);
    }

    /** {@code angleBetween_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_unsafe_fma(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t0 = unitScale(_otherx, _othery, _otherz);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = _otherz * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = _othery * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = _otherx * _t0;
        float _t17 = _t10 * -_t11;
        float _t18 = -(_t13 * _t9);
        float _t19 = _t13 * -_t11;
        float _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        float _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        float _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        float _t29 = unitScale(_t27, _t28, _t26);
        float _t33 = _t26 * _t29;
        float _t34 = _t27 * _t29;
        float _t35 = _t28 * _t29;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /** {@code angleBetween_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_unsafe_mulAdd(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t0 = unitScale(_otherx, _othery, _otherz);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = _otherz * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = _othery * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = _otherx * _t0;
        float _t17 = _t10 * -_t11;
        float _t18 = -(_t13 * _t9);
        float _t19 = _t13 * -_t11;
        float _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        float _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        float _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        float _t29 = unitScale(_t27, _t28, _t26);
        float _t33 = _t26 * _t29;
        float _t34 = _t27 * _t29;
        float _t35 = _t28 * _t29;
        return Math.atan2((float) java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
    }

    public static long asin_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.asin(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.asin(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.asin(_selfz));
        return dest;
    }

    public static long atan_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.atan(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan(_selfz));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, float x) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_selfx, x));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_selfy, x));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_selfz, x));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, float xX, float xY, float xZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_selfx, xX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_selfy, xY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_selfz, xZ));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, long x) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _xx = UnsafeOpsHolder.U.getFloat(x);
        float _xy = UnsafeOpsHolder.U.getFloat(x + 4L);
        float _xz = UnsafeOpsHolder.U.getFloat(x + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_selfx, _xx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_selfy, _xy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_selfz, _xz));
        return dest;
    }

    public static long cbrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.cbrt(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.cbrt(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.cbrt(_selfz));
        return dest;
    }

    public static long ceil_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.ceil(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.ceil(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.ceil(_selfz));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, float min, float max) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(Math.max(_selfx, min), max));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, min), max));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(Math.max(_selfz, min), max));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(Math.max(_selfx, minX), maxX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, minY), maxY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(Math.max(_selfz, minZ), maxZ));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, long min, long max) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _minx = UnsafeOpsHolder.U.getFloat(min);
        float _miny = UnsafeOpsHolder.U.getFloat(min + 4L);
        float _minz = UnsafeOpsHolder.U.getFloat(min + 8L);
        float _maxx = UnsafeOpsHolder.U.getFloat(max);
        float _maxy = UnsafeOpsHolder.U.getFloat(max + 4L);
        float _maxz = UnsafeOpsHolder.U.getFloat(max + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(Math.max(_selfx, _minx), _maxx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, _miny), _maxy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(Math.max(_selfz, _minz), _maxz));
        return dest;
    }

    public static long closestPointOnLine_unsafe(long dest, long src, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return closestPointOnLine_unsafe_mulAdd(dest, src, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        float _t0 = lineEndZ - lineStartZ;
        float _t1 = lineEndX - lineStartX;
        float _t2 = lineEndY - lineStartY;
        float _t10 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t14 = Math.max(0.0f, Math.min(1.0f, java.lang.Math.fma(_t0, UnsafeOpsHolder.U.getFloat(src + 8L) - lineStartZ, java.lang.Math.fma(_t1, UnsafeOpsHolder.U.getFloat(src) - lineStartX, _t2 * (UnsafeOpsHolder.U.getFloat(src + 4L) - lineStartY))) / _t10));
        if (_t10 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t1, _t14, lineStartX));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t2, _t14, lineStartY));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t0, _t14, lineStartZ));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, lineStartX);
            UnsafeOpsHolder.U.putFloat(dest + 4L, lineStartY);
            UnsafeOpsHolder.U.putFloat(dest + 8L, lineStartZ);
        }
        return dest;
    }

    /** {@code closestPointOnLine_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long closestPointOnLine_unsafe_mulAdd(long dest, long src, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        float _t0 = lineEndZ - lineStartZ;
        float _t1 = lineEndX - lineStartX;
        float _t2 = lineEndY - lineStartY;
        float _t10 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t14 = Math.max(0.0f, Math.min(1.0f, ((_t0) * (UnsafeOpsHolder.U.getFloat(src + 8L) - lineStartZ) + (((_t1) * (UnsafeOpsHolder.U.getFloat(src) - lineStartX) + (_t2 * (UnsafeOpsHolder.U.getFloat(src + 4L) - lineStartY))))) / _t10));
        if (_t10 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, ((_t1) * (_t14) + (lineStartX)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t2) * (_t14) + (lineStartY)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t0) * (_t14) + (lineStartZ)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, lineStartX);
            UnsafeOpsHolder.U.putFloat(dest + 4L, lineStartY);
            UnsafeOpsHolder.U.putFloat(dest + 8L, lineStartZ);
        }
        return dest;
    }

    public static long closestPointOnLine_unsafe(long dest, long src, long lineStart, long lineEnd) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return closestPointOnLine_unsafe_fma(dest, src, lineStart, lineEnd);
        return closestPointOnLine_unsafe_mulAdd(dest, src, lineStart, lineEnd);
    }

    /** {@code closestPointOnLine_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long closestPointOnLine_unsafe_fma(long dest, long src, long lineStart, long lineEnd) {
        float _lineStartx = UnsafeOpsHolder.U.getFloat(lineStart);
        float _lineStarty = UnsafeOpsHolder.U.getFloat(lineStart + 4L);
        float _lineStartz = UnsafeOpsHolder.U.getFloat(lineStart + 8L);
        float _t0 = UnsafeOpsHolder.U.getFloat(lineEnd + 8L) - _lineStartz;
        float _t1 = UnsafeOpsHolder.U.getFloat(lineEnd) - _lineStartx;
        float _t2 = UnsafeOpsHolder.U.getFloat(lineEnd + 4L) - _lineStarty;
        float _t10 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t14 = Math.max(0.0f, Math.min(1.0f, java.lang.Math.fma(_t0, UnsafeOpsHolder.U.getFloat(src + 8L) - _lineStartz, java.lang.Math.fma(_t1, UnsafeOpsHolder.U.getFloat(src) - _lineStartx, _t2 * (UnsafeOpsHolder.U.getFloat(src + 4L) - _lineStarty))) / _t10));
        if (_t10 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t1, _t14, _lineStartx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t2, _t14, _lineStarty));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t0, _t14, _lineStartz));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, _lineStartx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _lineStarty);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _lineStartz);
        }
        return dest;
    }

    /** {@code closestPointOnLine_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long closestPointOnLine_unsafe_mulAdd(long dest, long src, long lineStart, long lineEnd) {
        float _lineStartx = UnsafeOpsHolder.U.getFloat(lineStart);
        float _lineStarty = UnsafeOpsHolder.U.getFloat(lineStart + 4L);
        float _lineStartz = UnsafeOpsHolder.U.getFloat(lineStart + 8L);
        float _t0 = UnsafeOpsHolder.U.getFloat(lineEnd + 8L) - _lineStartz;
        float _t1 = UnsafeOpsHolder.U.getFloat(lineEnd) - _lineStartx;
        float _t2 = UnsafeOpsHolder.U.getFloat(lineEnd + 4L) - _lineStarty;
        float _t10 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t14 = Math.max(0.0f, Math.min(1.0f, ((_t0) * (UnsafeOpsHolder.U.getFloat(src + 8L) - _lineStartz) + (((_t1) * (UnsafeOpsHolder.U.getFloat(src) - _lineStartx) + (_t2 * (UnsafeOpsHolder.U.getFloat(src + 4L) - _lineStarty))))) / _t10));
        if (_t10 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, ((_t1) * (_t14) + (_lineStartx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t2) * (_t14) + (_lineStarty)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t0) * (_t14) + (_lineStartz)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, _lineStartx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _lineStarty);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _lineStartz);
        }
        return dest;
    }

    public static float compAdd_unsafe(long src) {
        return UnsafeOpsHolder.U.getFloat(src + 8L) + (UnsafeOpsHolder.U.getFloat(src) + UnsafeOpsHolder.U.getFloat(src + 4L));
    }

    public static float compMax_unsafe(long src) {
        return Math.max(Math.max(UnsafeOpsHolder.U.getFloat(src), UnsafeOpsHolder.U.getFloat(src + 4L)), UnsafeOpsHolder.U.getFloat(src + 8L));
    }

    public static float compMin_unsafe(long src) {
        return Math.min(Math.min(UnsafeOpsHolder.U.getFloat(src), UnsafeOpsHolder.U.getFloat(src + 4L)), UnsafeOpsHolder.U.getFloat(src + 8L));
    }

    public static float compMul_unsafe(long src) {
        return UnsafeOpsHolder.U.getFloat(src + 8L) * UnsafeOpsHolder.U.getFloat(src) * UnsafeOpsHolder.U.getFloat(src + 4L);
    }

    public static long copySign_unsafe(long dest, long src, float sign) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.copySign(_selfx, sign));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, sign));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.copySign(_selfz, sign));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, float signX, float signY, float signZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.copySign(_selfx, signX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, signY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.copySign(_selfz, signZ));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, long sign) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _signx = UnsafeOpsHolder.U.getFloat(sign);
        float _signy = UnsafeOpsHolder.U.getFloat(sign + 4L);
        float _signz = UnsafeOpsHolder.U.getFloat(sign + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.copySign(_selfx, _signx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, _signy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.copySign(_selfz, _signz));
        return dest;
    }

    public static long cos_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.cos(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.cos(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.cos(_selfz));
        return dest;
    }

    public static long cosh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.cosh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.cosh(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.cosh(_selfz));
        return dest;
    }

    public static long cross_unsafe(long dest, long src, float otherX, float otherY, float otherZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t3 = -(otherY * _selfz);
            float _t4 = -(otherZ * _selfx);
            float _t5 = -(otherX * _selfy);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(otherZ, _selfy, _t3) - java.lang.Math.fma(otherY, _selfz, _t3));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(otherX, _selfz, _t4) - java.lang.Math.fma(otherZ, _selfx, _t4));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(otherY, _selfx, _t5) - java.lang.Math.fma(otherX, _selfy, _t5));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t3 = -(otherY * _selfz);
            float _t4 = -(otherZ * _selfx);
            float _t5 = -(otherX * _selfy);
            UnsafeOpsHolder.U.putFloat(dest, ((otherZ) * (_selfy) + (_t3)) - ((otherY) * (_selfz) + (_t3)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((otherX) * (_selfz) + (_t4)) - ((otherZ) * (_selfx) + (_t4)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((otherY) * (_selfx) + (_t5)) - ((otherX) * (_selfy) + (_t5)));
            return dest;
        }
    }

    public static long cross_unsafe(long dest, long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return cross_unsafe_mulAdd(dest, src, other);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t3 = -(_othery * _selfz);
        float _t4 = -(_otherz * _selfx);
        float _t5 = -(_otherx * _selfy);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_otherz, _selfy, _t3) - java.lang.Math.fma(_othery, _selfz, _t3));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_otherx, _selfz, _t4) - java.lang.Math.fma(_otherz, _selfx, _t4));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_othery, _selfx, _t5) - java.lang.Math.fma(_otherx, _selfy, _t5));
        return dest;
    }

    /** {@code cross_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cross_unsafe_mulAdd(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t3 = -(_othery * _selfz);
        float _t4 = -(_otherz * _selfx);
        float _t5 = -(_otherx * _selfy);
        UnsafeOpsHolder.U.putFloat(dest, ((_otherz) * (_selfy) + (_t3)) - ((_othery) * (_selfz) + (_t3)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_otherx) * (_selfz) + (_t4)) - ((_otherz) * (_selfx) + (_t4)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_othery) * (_selfx) + (_t5)) - ((_otherx) * (_selfy) + (_t5)));
        return dest;
    }

    public static long degrees_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.toDegrees(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.toDegrees(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.toDegrees(_selfz));
        return dest;
    }

    public static float distance_unsafe(long src, float otherX, float otherY, float otherZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 8L) - otherZ;
            float _t1 = UnsafeOpsHolder.U.getFloat(src) - otherX;
            float _t2 = UnsafeOpsHolder.U.getFloat(src + 4L) - otherY;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 8L) - otherZ;
            float _t1 = UnsafeOpsHolder.U.getFloat(src) - otherX;
            float _t2 = UnsafeOpsHolder.U.getFloat(src + 4L) - otherY;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
        }
    }

    public static float distance_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 8L) - UnsafeOpsHolder.U.getFloat(other + 8L);
            float _t1 = UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other);
            float _t2 = UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 8L) - UnsafeOpsHolder.U.getFloat(other + 8L);
            float _t1 = UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other);
            float _t2 = UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L);
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
        }
    }

    public static float distanceSquared_unsafe(long src, float otherX, float otherY, float otherZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 8L) - otherZ;
            float _t1 = UnsafeOpsHolder.U.getFloat(src) - otherX;
            float _t2 = UnsafeOpsHolder.U.getFloat(src + 4L) - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 8L) - otherZ;
            float _t1 = UnsafeOpsHolder.U.getFloat(src) - otherX;
            float _t2 = UnsafeOpsHolder.U.getFloat(src + 4L) - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        }
    }

    public static float distanceSquared_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 8L) - UnsafeOpsHolder.U.getFloat(other + 8L);
            float _t1 = UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other);
            float _t2 = UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L);
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 8L) - UnsafeOpsHolder.U.getFloat(other + 8L);
            float _t1 = UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other);
            float _t2 = UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L);
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        }
    }

    public static float dot_unsafe(long src, float otherX, float otherY, float otherZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherZ, UnsafeOpsHolder.U.getFloat(src + 8L), java.lang.Math.fma(otherX, UnsafeOpsHolder.U.getFloat(src), otherY * UnsafeOpsHolder.U.getFloat(src + 4L)));
        } else {
            return ((otherZ) * (UnsafeOpsHolder.U.getFloat(src + 8L)) + (((otherX) * (UnsafeOpsHolder.U.getFloat(src)) + (otherY * UnsafeOpsHolder.U.getFloat(src + 4L)))));
        }
    }

    public static float dot_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(other + 8L), UnsafeOpsHolder.U.getFloat(src + 8L), java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(other), UnsafeOpsHolder.U.getFloat(src), UnsafeOpsHolder.U.getFloat(other + 4L) * UnsafeOpsHolder.U.getFloat(src + 4L)));
        } else {
            return ((UnsafeOpsHolder.U.getFloat(other + 8L)) * (UnsafeOpsHolder.U.getFloat(src + 8L)) + (((UnsafeOpsHolder.U.getFloat(other)) * (UnsafeOpsHolder.U.getFloat(src)) + (UnsafeOpsHolder.U.getFloat(other + 4L) * UnsafeOpsHolder.U.getFloat(src + 4L)))));
        }
    }

    public static long exp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.exp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.exp(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.exp(_selfz));
        return dest;
    }

    public static long exp2_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.pow(2.0f, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.pow(2.0f, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.pow(2.0f, _selfz));
        return dest;
    }

    public static long expm1_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.expm1(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.expm1(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.expm1(_selfz));
        return dest;
    }

    public static long faceforward_unsafe(long dest, long src, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t3 = java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY)) < 0.0f ? 1.0f : -1.0f;
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t3);
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t3 = ((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))) < 0.0f ? 1.0f : -1.0f;
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t3);
            return dest;
        }
    }

    public static long faceforward_unsafe(long dest, long src, long I, long Nref) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_unsafe_mulAdd(dest, src, I, Nref);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t3 = java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(I + 8L), UnsafeOpsHolder.U.getFloat(Nref + 8L), java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(I), UnsafeOpsHolder.U.getFloat(Nref), UnsafeOpsHolder.U.getFloat(I + 4L) * UnsafeOpsHolder.U.getFloat(Nref + 4L))) < 0.0f ? 1.0f : -1.0f;
        UnsafeOpsHolder.U.putFloat(dest, _selfx * _t3);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t3);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t3);
        return dest;
    }

    /** {@code faceforward_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long faceforward_unsafe_mulAdd(long dest, long src, long I, long Nref) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t3 = ((UnsafeOpsHolder.U.getFloat(I + 8L)) * (UnsafeOpsHolder.U.getFloat(Nref + 8L)) + (((UnsafeOpsHolder.U.getFloat(I)) * (UnsafeOpsHolder.U.getFloat(Nref)) + (UnsafeOpsHolder.U.getFloat(I + 4L) * UnsafeOpsHolder.U.getFloat(Nref + 4L))))) < 0.0f ? 1.0f : -1.0f;
        UnsafeOpsHolder.U.putFloat(dest, _selfx * _t3);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t3);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t3);
        return dest;
    }

    public static long floor_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.floor(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.floor(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.floor(_selfz));
        return dest;
    }

    public static long fract_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(_selfx - Math.floor(_selfx), 0.99999994f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy - Math.floor(_selfy), 0.99999994f));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(_selfz - Math.floor(_selfz), 0.99999994f));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, float y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.hypot(_selfx, y));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.hypot(_selfy, y));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.hypot(_selfz, y));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, float yX, float yY, float yZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.hypot(_selfx, yX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.hypot(_selfy, yY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.hypot(_selfz, yZ));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, long y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _yx = UnsafeOpsHolder.U.getFloat(y);
        float _yy = UnsafeOpsHolder.U.getFloat(y + 4L);
        float _yz = UnsafeOpsHolder.U.getFloat(y + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.hypot(_selfx, _yx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.hypot(_selfy, _yy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.hypot(_selfz, _yz));
        return dest;
    }

    public static long inverse_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, 1.0f / _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f / _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 1.0f / _selfz);
        return dest;
    }

    public static long inverseSqrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, (1.0f / (float) java.lang.Math.sqrt(_selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (1.0f / (float) java.lang.Math.sqrt(_selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (1.0f / (float) java.lang.Math.sqrt(_selfz)));
        return dest;
    }

    public static float length_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            return (float) java.lang.Math.sqrt(((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        }
    }

    public static float lengthSquared_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            return java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            return ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        }
    }

    public static long log_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.log(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.log(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.log(_selfz));
        return dest;
    }

    public static long log10_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.log10(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.log10(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.log10(_selfz));
        return dest;
    }

    public static long log1p_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.log1p(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.log1p(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.log1p(_selfz));
        return dest;
    }

    public static long log2_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.log2(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.log2(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.log2(_selfz));
        return dest;
    }

    public static float manhattanDistance_unsafe(long src, float otherX, float otherY, float otherZ) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src) - otherX) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 4L) - otherY) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 8L) - otherZ);
    }

    public static float manhattanDistance_unsafe(long src, long other) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other)) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L)) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 8L) - UnsafeOpsHolder.U.getFloat(other + 8L));
    }

    public static float manhattanLength_unsafe(long src) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src)) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 4L)) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 8L));
    }

    public static long max_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.max(_selfx, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.max(_selfz, scalar));
        return dest;
    }

    public static long max_unsafe(long dest, long src, float otherX, float otherY, float otherZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.max(_selfx, otherX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, otherY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.max(_selfz, otherZ));
        return dest;
    }

    public static long max_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.max(_selfx, _otherx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, _othery));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.max(_selfz, _otherz));
        return dest;
    }

    public static long min_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(_selfx, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(_selfz, scalar));
        return dest;
    }

    public static long min_unsafe(long dest, long src, float otherX, float otherY, float otherZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(_selfx, otherX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, otherY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(_selfz, otherZ));
        return dest;
    }

    public static long min_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(_selfx, _otherx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, _othery));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(_selfz, _otherz));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, float y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, flooredMod(_selfx, y));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, y));
        UnsafeOpsHolder.U.putFloat(dest + 8L, flooredMod(_selfz, y));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, float yX, float yY, float yZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, flooredMod(_selfx, yX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, yY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, flooredMod(_selfz, yZ));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, long y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _yx = UnsafeOpsHolder.U.getFloat(y);
        float _yy = UnsafeOpsHolder.U.getFloat(y + 4L);
        float _yz = UnsafeOpsHolder.U.getFloat(y + 8L);
        UnsafeOpsHolder.U.putFloat(dest, flooredMod(_selfx, _yx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, _yy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, flooredMod(_selfz, _yz));
        return dest;
    }

    public static long nextDown_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.nextDown(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.nextDown(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.nextDown(_selfz));
        return dest;
    }

    public static long nextUp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.nextUp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.nextUp(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.nextUp(_selfz));
        return dest;
    }

    public static long normalize_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalize_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t3);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code normalize_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normalize_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t3);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t3);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long normalizeMul_unsafe(long dest, long src, float length) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizeMul_unsafe_mulAdd(dest, src, length);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t4 = length * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t4);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code normalizeMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normalizeMul_unsafe_mulAdd(long dest, long src, float length) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t4 = length * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t4);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static float orientedAngle_unsafe(long src, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_unsafe_mulAdd(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t4 = -(otherX * _selfy);
        float _t5 = -(otherZ * _selfx);
        float _t6 = -(otherY * _selfz);
        float _t7 = unitScale(normalX, normalY, normalZ);
        float _t16 = java.lang.Math.fma(otherY, _selfx, _t4) - java.lang.Math.fma(otherX, _selfy, _t4);
        float _t17 = java.lang.Math.fma(otherX, _selfz, _t5) - java.lang.Math.fma(otherZ, _selfx, _t5);
        float _t18 = java.lang.Math.fma(otherZ, _selfy, _t6) - java.lang.Math.fma(otherY, _selfz, _t6);
        float _ct0 = java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t17, _t17, _t18 * _t18));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.orientedAngle_degenerate(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
        float _t24 = Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy)));
        return java.lang.Math.fma(_t16, normalZ * _t7, java.lang.Math.fma(_t17, normalY * _t7, _t18 * (normalX * _t7))) < 0.0f ? -_t24 : _t24;
    }

    /** {@code orientedAngle_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_unsafe_mulAdd(long src, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t4 = -(otherX * _selfy);
        float _t5 = -(otherZ * _selfx);
        float _t6 = -(otherY * _selfz);
        float _t7 = unitScale(normalX, normalY, normalZ);
        float _t16 = ((otherY) * (_selfx) + (_t4)) - ((otherX) * (_selfy) + (_t4));
        float _t17 = ((otherX) * (_selfz) + (_t5)) - ((otherZ) * (_selfx) + (_t5));
        float _t18 = ((otherZ) * (_selfy) + (_t6)) - ((otherY) * (_selfz) + (_t6));
        float _ct0 = ((_t16) * (_t16) + (((_t17) * (_t17) + (_t18 * _t18))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.orientedAngle_degenerate(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
        float _t24 = Math.atan2((float) java.lang.Math.sqrt(_ct0), ((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))));
        return ((_t16) * (normalZ * _t7) + (((_t17) * (normalY * _t7) + (_t18 * (normalX * _t7))))) < 0.0f ? -_t24 : _t24;
    }

    public static float orientedAngle_degenerate(long src, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.orientedAngle_degenerate_unsafe(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static float orientedAngle_degenerate_unsafe(long src, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_unsafe_fma(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return orientedAngle_degenerate_unsafe_mulAdd(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@code orientedAngle_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_unsafe_fma(long src, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherY * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = otherX * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = otherZ * _t1;
        float _t14 = _selfz * _t2;
        float _t19 = -(_t11 * _t12);
        float _t20 = -(_t9 * _t14);
        float _t21 = -(_t13 * _t10);
        float _t30 = java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19);
        float _t31 = java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20);
        float _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        float _t33 = unitScale(_t31, _t32, _t30);
        return orientedAngle_degenerate_unsafe_fma_s28a2199e_1(normalX, normalY, normalZ, unitScale(normalX, normalY, normalZ), _t9, _t10, _t11, _t12, _t13, _t14, _t33, _t30 * _t33, _t31 * _t33, _t32 * _t33);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_degenerate_unsafe_fma_s28a2199e_1(float normalX, float normalY, float normalZ, float _t0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t33, float _t39, float _t40, float _t41) {
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(normalZ * _t0, _t39, java.lang.Math.fma(normalX * _t0, _t40, normalY * _t0 * _t41)) < 0.0f ? -_t46 : _t46;
    }

    /** {@code orientedAngle_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_unsafe_mulAdd(long src, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = unitScale(normalX, normalY, normalZ);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherY * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = otherX * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = otherZ * _t1;
        float _t14 = _selfz * _t2;
        float _t19 = -(_t11 * _t12);
        float _t20 = -(_t9 * _t14);
        float _t21 = -(_t13 * _t10);
        float _t30 = ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19));
        float _t31 = ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20));
        float _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        float _t33 = unitScale(_t31, _t32, _t30);
        float _t39 = _t30 * _t33;
        float _t40 = _t31 * _t33;
        float _t41 = _t32 * _t33;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((normalZ * _t0) * (_t39) + (((normalX * _t0) * (_t40) + (normalY * _t0 * _t41)))) < 0.0f ? -_t46 : _t46;
    }

    public static float orientedAngle_unsafe(long src, long other, long normal) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_unsafe_fma(src, other, normal);
        return orientedAngle_unsafe_mulAdd(src, other, normal);
    }

    /** {@code orientedAngle_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_unsafe_fma(long src, long other, long normal) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _t4 = -(_otherx * _selfy);
        float _t5 = -(_otherz * _selfx);
        float _t6 = -(_othery * _selfz);
        float _t16 = java.lang.Math.fma(_othery, _selfx, _t4) - java.lang.Math.fma(_otherx, _selfy, _t4);
        float _t17 = java.lang.Math.fma(_otherx, _selfz, _t5) - java.lang.Math.fma(_otherz, _selfx, _t5);
        float _t18 = java.lang.Math.fma(_otherz, _selfy, _t6) - java.lang.Math.fma(_othery, _selfz, _t6);
        float _ct0 = java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t17, _t17, _t18 * _t18));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.orientedAngle_degenerate(src, other, normal);
        return orientedAngle_unsafe_fma_s2c25e10d_1(_selfx, _selfy, _selfz, _otherx, _othery, _otherz, _normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t16, _t17, _t18, _ct0);
    }

    /** Piece 2 of {@code orientedAngle_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_unsafe_fma_s2c25e10d_1(float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _normalx, float _normaly, float _normalz, float _t7, float _t16, float _t17, float _t18, float _ct0) {
        float _t24 = Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy)));
        return java.lang.Math.fma(_t16, _normalz * _t7, java.lang.Math.fma(_t17, _normaly * _t7, _t18 * (_normalx * _t7))) < 0.0f ? -_t24 : _t24;
    }

    /** {@code orientedAngle_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_unsafe_mulAdd(long src, long other, long normal) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _t4 = -(_otherx * _selfy);
        float _t5 = -(_otherz * _selfx);
        float _t6 = -(_othery * _selfz);
        float _t16 = ((_othery) * (_selfx) + (_t4)) - ((_otherx) * (_selfy) + (_t4));
        float _t17 = ((_otherx) * (_selfz) + (_t5)) - ((_otherz) * (_selfx) + (_t5));
        float _t18 = ((_otherz) * (_selfy) + (_t6)) - ((_othery) * (_selfz) + (_t6));
        float _ct0 = ((_t16) * (_t16) + (((_t17) * (_t17) + (_t18 * _t18))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.orientedAngle_degenerate(src, other, normal);
        return orientedAngle_unsafe_mulAdd_s17147de4_1(_selfx, _selfy, _selfz, _otherx, _othery, _otherz, _normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t16, _t17, _t18, _ct0);
    }

    /** Piece 2 of {@code orientedAngle_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_unsafe_mulAdd_s17147de4_1(float _selfx, float _selfy, float _selfz, float _otherx, float _othery, float _otherz, float _normalx, float _normaly, float _normalz, float _t7, float _t16, float _t17, float _t18, float _ct0) {
        float _t24 = Math.atan2((float) java.lang.Math.sqrt(_ct0), ((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))));
        return ((_t16) * (_normalz * _t7) + (((_t17) * (_normaly * _t7) + (_t18 * (_normalx * _t7))))) < 0.0f ? -_t24 : _t24;
    }

    public static float orientedAngle_degenerate(long src, long other, long normal) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.orientedAngle_degenerate_unsafe(src, other, normal);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static float orientedAngle_degenerate_unsafe(long src, long other, long normal) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_unsafe_fma(src, other, normal);
        return orientedAngle_degenerate_unsafe_mulAdd(src, other, normal);
    }

    /** {@code orientedAngle_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_unsafe_fma(long src, long other, long normal) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _othery * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = _otherx * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = _otherz * _t1;
        float _t14 = _selfz * _t2;
        float _t19 = -(_t11 * _t12);
        float _t20 = -(_t9 * _t14);
        float _t21 = -(_t13 * _t10);
        float _t30 = java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19);
        float _t31 = java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20);
        float _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        return orientedAngle_degenerate_unsafe_fma_scc2b4492_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, _t30, _t31, _t32, unitScale(_t31, _t32, _t30));
    }

    /** Piece 2 of {@code orientedAngle_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_degenerate_unsafe_fma_scc2b4492_1(float _normalx, float _normaly, float _normalz, float _t0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t30, float _t31, float _t32, float _t33) {
        float _t39 = _t30 * _t33;
        float _t40 = _t31 * _t33;
        float _t41 = _t32 * _t33;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(_normalz * _t0, _t39, java.lang.Math.fma(_normalx * _t0, _t40, _normaly * _t0 * _t41)) < 0.0f ? -_t46 : _t46;
    }

    /** {@code orientedAngle_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float orientedAngle_degenerate_unsafe_mulAdd(long src, long other, long normal) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _othery * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = _otherx * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = _otherz * _t1;
        float _t14 = _selfz * _t2;
        float _t19 = -(_t11 * _t12);
        float _t20 = -(_t9 * _t14);
        float _t21 = -(_t13 * _t10);
        float _t30 = ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19));
        float _t31 = ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20));
        float _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        float _t33 = unitScale(_t31, _t32, _t30);
        return orientedAngle_degenerate_unsafe_mulAdd_s68b92f9_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, _t31, _t32, _t33, _t30 * _t33);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_degenerate_unsafe_mulAdd_s68b92f9_1(float _normalx, float _normaly, float _normalz, float _t0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t31, float _t32, float _t33, float _t39) {
        float _t40 = _t31 * _t33;
        float _t41 = _t32 * _t33;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((_normalz * _t0) * (_t39) + (((_normalx * _t0) * (_t40) + (_normaly * _t0 * _t41)))) < 0.0f ? -_t46 : _t46;
    }

    public static long outerProduct_unsafe(long dest, long src, float rowX, float rowY, float rowZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, rowX * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, rowX * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, rowX * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, rowY * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 16L, rowY * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 20L, rowY * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 24L, rowZ * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 28L, rowZ * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 32L, rowZ * _selfz);
        return dest;
    }

    public static long outerProduct_unsafe(long dest, long src, long row) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _rowx = UnsafeOpsHolder.U.getFloat(row);
        float _rowy = UnsafeOpsHolder.U.getFloat(row + 4L);
        float _rowz = UnsafeOpsHolder.U.getFloat(row + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _rowx * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _rowx * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _rowx * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _rowy * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _rowy * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _rowy * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _rowz * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _rowz * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 32L, _rowz * _selfz);
        return dest;
    }

    public static long perpendicular_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        if (java.lang.Math.abs(_selfz) < java.lang.Math.abs(_selfx)) {
            UnsafeOpsHolder.U.putFloat(dest, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -_selfx);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfz);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -_selfy);
        }
        return dest;
    }

    public static long pow_unsafe(long dest, long src, float exponent) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.pow(_selfx, exponent));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.pow(_selfy, exponent));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.pow(_selfz, exponent));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, float exponentX, float exponentY, float exponentZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.pow(_selfx, exponentX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.pow(_selfy, exponentY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.pow(_selfz, exponentZ));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, long exponent) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _exponentx = UnsafeOpsHolder.U.getFloat(exponent);
        float _exponenty = UnsafeOpsHolder.U.getFloat(exponent + 4L);
        float _exponentz = UnsafeOpsHolder.U.getFloat(exponent + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.pow(_selfx, _exponentx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.pow(_selfy, _exponenty));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.pow(_selfz, _exponentz));
        return dest;
    }

    public static long project_unsafe(long dest, long src, float ontoX, float ontoY, float ontoZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t7 = java.lang.Math.fma(ontoZ, UnsafeOpsHolder.U.getFloat(src + 8L), java.lang.Math.fma(ontoX, UnsafeOpsHolder.U.getFloat(src), ontoY * UnsafeOpsHolder.U.getFloat(src + 4L))) / java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY));
            UnsafeOpsHolder.U.putFloat(dest, ontoX * _t7);
            UnsafeOpsHolder.U.putFloat(dest + 4L, ontoY * _t7);
            UnsafeOpsHolder.U.putFloat(dest + 8L, ontoZ * _t7);
            return dest;
        } else {
            float _t7 = ((ontoZ) * (UnsafeOpsHolder.U.getFloat(src + 8L)) + (((ontoX) * (UnsafeOpsHolder.U.getFloat(src)) + (ontoY * UnsafeOpsHolder.U.getFloat(src + 4L))))) / ((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))));
            UnsafeOpsHolder.U.putFloat(dest, ontoX * _t7);
            UnsafeOpsHolder.U.putFloat(dest + 4L, ontoY * _t7);
            UnsafeOpsHolder.U.putFloat(dest + 8L, ontoZ * _t7);
            return dest;
        }
    }

    public static long project_unsafe(long dest, long src, long onto) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_unsafe_mulAdd(dest, src, onto);
        float _ontox = UnsafeOpsHolder.U.getFloat(onto);
        float _ontoy = UnsafeOpsHolder.U.getFloat(onto + 4L);
        float _ontoz = UnsafeOpsHolder.U.getFloat(onto + 8L);
        float _t7 = java.lang.Math.fma(_ontoz, UnsafeOpsHolder.U.getFloat(src + 8L), java.lang.Math.fma(_ontox, UnsafeOpsHolder.U.getFloat(src), _ontoy * UnsafeOpsHolder.U.getFloat(src + 4L))) / java.lang.Math.fma(_ontoz, _ontoz, java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy));
        UnsafeOpsHolder.U.putFloat(dest, _ontox * _t7);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _ontoy * _t7);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _ontoz * _t7);
        return dest;
    }

    /** {@code project_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long project_unsafe_mulAdd(long dest, long src, long onto) {
        float _ontox = UnsafeOpsHolder.U.getFloat(onto);
        float _ontoy = UnsafeOpsHolder.U.getFloat(onto + 4L);
        float _ontoz = UnsafeOpsHolder.U.getFloat(onto + 8L);
        float _t7 = ((_ontoz) * (UnsafeOpsHolder.U.getFloat(src + 8L)) + (((_ontox) * (UnsafeOpsHolder.U.getFloat(src)) + (_ontoy * UnsafeOpsHolder.U.getFloat(src + 4L))))) / ((_ontoz) * (_ontoz) + (((_ontox) * (_ontox) + (_ontoy * _ontoy))));
        UnsafeOpsHolder.U.putFloat(dest, _ontox * _t7);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _ontoy * _t7);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _ontoz * _t7);
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, float normalX, float normalY, float normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t2 = java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-normalX, _t2, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-normalY, _t2, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-normalZ, _t2, _selfz));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t2 = ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) - (normalX) * (_t2)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) - (normalY) * (_t2)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) - (normalZ) * (_t2)));
            return dest;
        }
    }

    public static long projectOnPlane_unsafe(long dest, long src, long normal) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _normalx = UnsafeOpsHolder.U.getFloat(normal);
            float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
            float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
            float _t2 = java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-_normalx, _t2, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-_normaly, _t2, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-_normalz, _t2, _selfz));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _normalx = UnsafeOpsHolder.U.getFloat(normal);
            float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
            float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
            float _t2 = ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) - (_normalx) * (_t2)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) - (_normaly) * (_t2)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) - (_normalz) * (_t2)));
            return dest;
        }
    }

    public static long radians_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.toRadians(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.toRadians(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.toRadians(_selfz));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, float normalX, float normalY, float normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t3 = 2.0f * java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-normalX, _t3, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-normalY, _t3, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-normalZ, _t3, _selfz));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t3 = 2.0f * ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) - (normalX) * (_t3)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) - (normalY) * (_t3)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) - (normalZ) * (_t3)));
            return dest;
        }
    }

    public static long reflect_unsafe(long dest, long src, long normal) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_unsafe_mulAdd(dest, src, normal);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _t3 = 2.0f * java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-_normalx, _t3, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-_normaly, _t3, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-_normalz, _t3, _selfz));
        return dest;
    }

    /** {@code reflect_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long reflect_unsafe_mulAdd(long dest, long src, long normal) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _t3 = 2.0f * ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) - (_normalx) * (_t3)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) - (_normaly) * (_t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) - (_normalz) * (_t3)));
        return dest;
    }

    public static long refract_unsafe(long dest, long src, float normalX, float normalY, float normalZ, float eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_unsafe_mulAdd(dest, src, normalX, normalY, normalZ, eta);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t3 = java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
        float _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0f), eta * eta, 1.0f);
        float _t10 = java.lang.Math.fma(eta, _t3, (float) java.lang.Math.sqrt(Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(eta, _selfx, -(normalX * _t10)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(eta, _selfy, -(normalY * _t10)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(eta, _selfz, -(normalZ * _t10)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code refract_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long refract_unsafe_mulAdd(long dest, long src, float normalX, float normalY, float normalZ, float eta) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t3 = ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
        float _t7 = ((((_t3) * (_t3) - (1.0f))) * (eta * eta) + (1.0f));
        float _t10 = ((eta) * (_t3) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t7))));
        if (_t7 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, ((eta) * (_selfx) - (normalX * _t10)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((eta) * (_selfy) - (normalY * _t10)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((eta) * (_selfz) - (normalZ * _t10)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long refract_unsafe(long dest, long src, long normal, float eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_unsafe_mulAdd(dest, src, normal, eta);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _t3 = java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
        float _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0f), eta * eta, 1.0f);
        float _t10 = java.lang.Math.fma(eta, _t3, (float) java.lang.Math.sqrt(Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(eta, _selfx, -(_normalx * _t10)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(eta, _selfy, -(_normaly * _t10)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(eta, _selfz, -(_normalz * _t10)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code refract_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long refract_unsafe_mulAdd(long dest, long src, long normal, float eta) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _t3 = ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
        float _t7 = ((((_t3) * (_t3) - (1.0f))) * (eta * eta) + (1.0f));
        float _t10 = ((eta) * (_t3) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t7))));
        if (_t7 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, ((eta) * (_selfx) - (_normalx * _t10)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((eta) * (_selfy) - (_normaly * _t10)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((eta) * (_selfz) - (_normalz * _t10)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long round_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.rint(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.rint(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.rint(_selfz));
        return dest;
    }

    public static long sign_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.signum(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.signum(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.signum(_selfz));
        return dest;
    }

    public static long sin_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.sin(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.sin(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.sin(_selfz));
        return dest;
    }

    public static long sinh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.sinh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.sinh(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.sinh(_selfz));
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, float edge0, float edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_mulAdd(dest, src, edge0, edge1);
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t10 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - edge0) * _t0_inv));
        float _t11 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - edge0) * _t0_inv));
        float _t12 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 8L) - edge0) * _t0_inv));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, _t10, 3.0f) * _t10 * _t10);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-2.0f, _t11, 3.0f) * _t11 * _t11);
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t10 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - edge0) * _t0_inv));
        float _t11 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - edge0) * _t0_inv));
        float _t12 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 8L) - edge0) * _t0_inv));
        UnsafeOpsHolder.U.putFloat(dest, ((3.0f) - (2.0f) * (_t10)) * _t10 * _t10);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((3.0f) - (2.0f) * (_t11)) * _t11 * _t11);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((3.0f) - (2.0f) * (_t12)) * _t12 * _t12);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_mulAdd(dest, src, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        float _t12 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - edge0X) / (edge1X - edge0X)));
        float _t13 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - edge0Y) / (edge1Y - edge0Y)));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 8L) - edge0Z) / (edge1Z - edge0Z)));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        float _t12 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - edge0X) / (edge1X - edge0X)));
        float _t13 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - edge0Y) / (edge1Y - edge0Y)));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 8L) - edge0Z) / (edge1Z - edge0Z)));
        UnsafeOpsHolder.U.putFloat(dest, ((3.0f) - (2.0f) * (_t12)) * _t12 * _t12);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((3.0f) - (2.0f) * (_t13)) * _t13 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((3.0f) - (2.0f) * (_t14)) * _t14 * _t14);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, long edge0, long edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_mulAdd(dest, src, edge0, edge1);
        float _edge0x = UnsafeOpsHolder.U.getFloat(edge0);
        float _edge0y = UnsafeOpsHolder.U.getFloat(edge0 + 4L);
        float _edge0z = UnsafeOpsHolder.U.getFloat(edge0 + 8L);
        float _t12 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - _edge0x) / (UnsafeOpsHolder.U.getFloat(edge1) - _edge0x)));
        float _t13 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - _edge0y) / (UnsafeOpsHolder.U.getFloat(edge1 + 4L) - _edge0y)));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 8L) - _edge0z) / (UnsafeOpsHolder.U.getFloat(edge1 + 8L) - _edge0z)));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, long edge0, long edge1) {
        float _edge0x = UnsafeOpsHolder.U.getFloat(edge0);
        float _edge0y = UnsafeOpsHolder.U.getFloat(edge0 + 4L);
        float _edge0z = UnsafeOpsHolder.U.getFloat(edge0 + 8L);
        float _t12 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - _edge0x) / (UnsafeOpsHolder.U.getFloat(edge1) - _edge0x)));
        float _t13 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - _edge0y) / (UnsafeOpsHolder.U.getFloat(edge1 + 4L) - _edge0y)));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 8L) - _edge0z) / (UnsafeOpsHolder.U.getFloat(edge1 + 8L) - _edge0z)));
        UnsafeOpsHolder.U.putFloat(dest, ((3.0f) - (2.0f) * (_t12)) * _t12 * _t12);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((3.0f) - (2.0f) * (_t13)) * _t13 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((3.0f) - (2.0f) * (_t14)) * _t14 * _t14);
        return dest;
    }

    public static long sqrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, (float) java.lang.Math.sqrt(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) java.lang.Math.sqrt(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) java.lang.Math.sqrt(_selfz));
        return dest;
    }

    public static long step_unsafe(long dest, long src, float edge) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx < edge ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < edge ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static long step_unsafe(long dest, long src, float edgeX, float edgeY, float edgeZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx < edgeX ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < edgeY ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz < edgeZ ? 0.0f : 1.0f);
        return dest;
    }

    public static long step_unsafe(long dest, long src, long edge) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _edgex = UnsafeOpsHolder.U.getFloat(edge);
        float _edgey = UnsafeOpsHolder.U.getFloat(edge + 4L);
        float _edgez = UnsafeOpsHolder.U.getFloat(edge + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx < _edgex ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < _edgey ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz < _edgez ? 0.0f : 1.0f);
        return dest;
    }

    public static long tan_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.tan(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.tan(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.tan(_selfz));
        return dest;
    }

    public static long tanh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.tanh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.tanh(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.tanh(_selfz));
        return dest;
    }

    public static long triangleNormal_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = p1Y - _selfy;
        float _t1 = p2Z - _selfz;
        float _t2 = p1Z - _selfz;
        float _t3 = p2Y - _selfy;
        float _t4 = p1X - _selfx;
        float _t5 = p2X - _selfx;
        float _t9 = -(_t2 * _t3);
        float _t10 = -(_t0 * _t5);
        float _t11 = -(_t4 * _t1);
        float _t18 = java.lang.Math.fma(_t0, _t1, _t9) - java.lang.Math.fma(_t2, _t3, _t9);
        float _t19 = java.lang.Math.fma(_t4, _t3, _t10) - java.lang.Math.fma(_t0, _t5, _t10);
        float _t20 = java.lang.Math.fma(_t2, _t5, _t11) - java.lang.Math.fma(_t4, _t1, _t11);
        float _ct0 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.triangleNormal_degenerate(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        float _t25 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putFloat(dest, _t18 * _t25);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t20 * _t25);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t19 * _t25);
        return dest;
    }

    /** {@code triangleNormal_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = p1Y - _selfy;
        float _t1 = p2Z - _selfz;
        float _t2 = p1Z - _selfz;
        float _t3 = p2Y - _selfy;
        float _t4 = p1X - _selfx;
        float _t5 = p2X - _selfx;
        float _t9 = -(_t2 * _t3);
        float _t10 = -(_t0 * _t5);
        float _t11 = -(_t4 * _t1);
        float _t18 = ((_t0) * (_t1) + (_t9)) - ((_t2) * (_t3) + (_t9));
        float _t19 = ((_t4) * (_t3) + (_t10)) - ((_t0) * (_t5) + (_t10));
        float _t20 = ((_t2) * (_t5) + (_t11)) - ((_t4) * (_t1) + (_t11));
        float _ct0 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.triangleNormal_degenerate(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        float _t25 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        UnsafeOpsHolder.U.putFloat(dest, _t18 * _t25);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t20 * _t25);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t19 * _t25);
        return dest;
    }

    public static long triangleNormal_degenerate(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.triangleNormal_degenerate_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long triangleNormal_degenerate_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_unsafe_fma(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return triangleNormal_degenerate_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    /** {@code triangleNormal_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_degenerate_unsafe_fma(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t19 = Math.min(1.0f, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        float _t38 = p1X * _t19 - _t30;
        float _t39 = p1Y * _t19 - _t32;
        float _t40 = p1Z * _t19 - _t34;
        float _t41 = p2Y * _t19 - _t32;
        float _t42 = p2X * _t19 - _t30;
        float _t43 = p2Z * _t19 - _t34;
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t56 = _t43 * _t45;
        float _t57 = _t40 * _t44;
        return triangleNormal_degenerate_unsafe_fma_s159ea42e_1(dest, _t52, _t53, _t54, _t55, _t56, _t57, -(_t54 * _t55), -(_t57 * _t53), -(_t52 * _t56));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long triangleNormal_degenerate_unsafe_fma_s159ea42e_1(long dest, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t61, float _t62, float _t63) {
        float _t70 = java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61);
        float _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        float _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        float _t73 = unitScale(_t71, _t72, _t70);
        float _t77 = _t70 * _t73;
        float _t78 = _t71 * _t73;
        float _t79 = _t72 * _t73;
        float _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        float _t83 = (1.0f / (float) java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t83 * _t78);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t83 * _t79);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t83 * _t77);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code triangleNormal_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_degenerate_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t19 = Math.min(1.0f, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        float _t38 = p1X * _t19 - _t30;
        float _t39 = p1Y * _t19 - _t32;
        float _t40 = p1Z * _t19 - _t34;
        float _t41 = p2Y * _t19 - _t32;
        float _t42 = p2X * _t19 - _t30;
        float _t43 = p2Z * _t19 - _t34;
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t56 = _t43 * _t45;
        float _t57 = _t40 * _t44;
        return triangleNormal_degenerate_unsafe_mulAdd_s5ee8e41_1(dest, _t52, _t53, _t54, _t55, _t56, _t57, -(_t54 * _t55), -(_t57 * _t53), -(_t52 * _t56));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long triangleNormal_degenerate_unsafe_mulAdd_s5ee8e41_1(long dest, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t61, float _t62, float _t63) {
        float _t70 = ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61));
        float _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        float _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        float _t73 = unitScale(_t71, _t72, _t70);
        float _t77 = _t70 * _t73;
        float _t78 = _t71 * _t73;
        float _t79 = _t72 * _t73;
        float _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        float _t83 = (1.0f / (float) java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t83 * _t78);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t83 * _t79);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t83 * _t77);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long triangleNormal_unsafe(long dest, long src, long p1, long p2) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_unsafe_fma(dest, src, p1, p2);
        return triangleNormal_unsafe_mulAdd(dest, src, p1, p2);
    }

    /** {@code triangleNormal_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_unsafe_fma(long dest, long src, long p1, long p2) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = UnsafeOpsHolder.U.getFloat(p1 + 4L) - _selfy;
        float _t1 = UnsafeOpsHolder.U.getFloat(p2 + 8L) - _selfz;
        float _t2 = UnsafeOpsHolder.U.getFloat(p1 + 8L) - _selfz;
        float _t3 = UnsafeOpsHolder.U.getFloat(p2 + 4L) - _selfy;
        float _t4 = UnsafeOpsHolder.U.getFloat(p1) - _selfx;
        float _t5 = UnsafeOpsHolder.U.getFloat(p2) - _selfx;
        float _t9 = -(_t2 * _t3);
        float _t10 = -(_t0 * _t5);
        float _t11 = -(_t4 * _t1);
        float _t18 = java.lang.Math.fma(_t0, _t1, _t9) - java.lang.Math.fma(_t2, _t3, _t9);
        float _t19 = java.lang.Math.fma(_t4, _t3, _t10) - java.lang.Math.fma(_t0, _t5, _t10);
        float _t20 = java.lang.Math.fma(_t2, _t5, _t11) - java.lang.Math.fma(_t4, _t1, _t11);
        float _ct0 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.triangleNormal_degenerate(dest, src, p1, p2);
        return triangleNormal_unsafe_fma_s19449a3b_1(dest, _t18, _t19, _t20, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code triangleNormal_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long triangleNormal_unsafe_fma_s19449a3b_1(long dest, float _t18, float _t19, float _t20, float _t25) {
        UnsafeOpsHolder.U.putFloat(dest, _t18 * _t25);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t20 * _t25);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t19 * _t25);
        return dest;
    }

    /** {@code triangleNormal_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_unsafe_mulAdd(long dest, long src, long p1, long p2) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = UnsafeOpsHolder.U.getFloat(p1 + 4L) - _selfy;
        float _t1 = UnsafeOpsHolder.U.getFloat(p2 + 8L) - _selfz;
        float _t2 = UnsafeOpsHolder.U.getFloat(p1 + 8L) - _selfz;
        float _t3 = UnsafeOpsHolder.U.getFloat(p2 + 4L) - _selfy;
        float _t4 = UnsafeOpsHolder.U.getFloat(p1) - _selfx;
        float _t5 = UnsafeOpsHolder.U.getFloat(p2) - _selfx;
        float _t9 = -(_t2 * _t3);
        float _t10 = -(_t0 * _t5);
        float _t11 = -(_t4 * _t1);
        float _t18 = ((_t0) * (_t1) + (_t9)) - ((_t2) * (_t3) + (_t9));
        float _t19 = ((_t4) * (_t3) + (_t10)) - ((_t0) * (_t5) + (_t10));
        float _t20 = ((_t2) * (_t5) + (_t11)) - ((_t4) * (_t1) + (_t11));
        float _ct0 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsAddress.triangleNormal_degenerate(dest, src, p1, p2);
        return triangleNormal_unsafe_mulAdd_sfd3a8b3a_1(dest, _t18, _t19, _t20, (1.0f / (float) java.lang.Math.sqrt(_ct0)));
    }

    /** Piece 2 of {@code triangleNormal_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long triangleNormal_unsafe_mulAdd_sfd3a8b3a_1(long dest, float _t18, float _t19, float _t20, float _t25) {
        UnsafeOpsHolder.U.putFloat(dest, _t18 * _t25);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t20 * _t25);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t19 * _t25);
        return dest;
    }

    public static long triangleNormal_degenerate(long dest, long src, long p1, long p2) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.triangleNormal_degenerate_unsafe(dest, src, p1, p2);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long triangleNormal_degenerate_unsafe(long dest, long src, long p1, long p2) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_unsafe_fma(dest, src, p1, p2);
        return triangleNormal_degenerate_unsafe_mulAdd(dest, src, p1, p2);
    }

    /** {@code triangleNormal_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_degenerate_unsafe_fma(long dest, long src, long p1, long p2) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _t19 = Math.min(1.0f, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        float _t38 = _p1x * _t19 - _t30;
        float _t39 = _p1y * _t19 - _t32;
        float _t40 = _p1z * _t19 - _t34;
        float _t41 = _p2y * _t19 - _t32;
        float _t42 = _p2x * _t19 - _t30;
        float _t43 = _p2z * _t19 - _t34;
        return triangleNormal_degenerate_unsafe_fma_sbe30bab4_1(dest, _t38, _t39, _t40, _t41, _t42, _t43, unitScale(_t38, _t39, _t40), unitScale(_t42, _t41, _t43));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long triangleNormal_degenerate_unsafe_fma_sbe30bab4_1(long dest, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45) {
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t56 = _t43 * _t45;
        float _t57 = _t40 * _t44;
        float _t61 = -(_t54 * _t55);
        float _t62 = -(_t57 * _t53);
        float _t63 = -(_t52 * _t56);
        float _t70 = java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61);
        float _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        float _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        float _t73 = unitScale(_t71, _t72, _t70);
        float _t77 = _t70 * _t73;
        float _t78 = _t71 * _t73;
        float _t79 = _t72 * _t73;
        float _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        float _t83 = (1.0f / (float) java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t83 * _t78);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t83 * _t79);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t83 * _t77);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code triangleNormal_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long triangleNormal_degenerate_unsafe_mulAdd(long dest, long src, long p1, long p2) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _t19 = Math.min(1.0f, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        float _t38 = _p1x * _t19 - _t30;
        float _t39 = _p1y * _t19 - _t32;
        float _t40 = _p1z * _t19 - _t34;
        float _t41 = _p2y * _t19 - _t32;
        float _t42 = _p2x * _t19 - _t30;
        float _t43 = _p2z * _t19 - _t34;
        return triangleNormal_degenerate_unsafe_mulAdd_sd5cce0e3_1(dest, _t38, _t39, _t40, _t41, _t42, _t43, unitScale(_t38, _t39, _t40), unitScale(_t42, _t41, _t43));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long triangleNormal_degenerate_unsafe_mulAdd_sd5cce0e3_1(long dest, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45) {
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t56 = _t43 * _t45;
        float _t57 = _t40 * _t44;
        float _t61 = -(_t54 * _t55);
        float _t62 = -(_t57 * _t53);
        float _t63 = -(_t52 * _t56);
        float _t70 = ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61));
        float _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        float _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        float _t73 = unitScale(_t71, _t72, _t70);
        float _t77 = _t70 * _t73;
        float _t78 = _t71 * _t73;
        float _t79 = _t72 * _t73;
        float _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        float _t83 = (1.0f / (float) java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t83 * _t78);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t83 * _t79);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t83 * _t77);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long trunc_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz >= 0.0f ? Math.floor(_selfz) : Math.ceil(_selfz));
        return dest;
    }

    public static long ulp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.ulp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.ulp(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.ulp(_selfz));
        return dest;
    }

    public static long xyz0_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        return dest;
    }

    public static long xyz1_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f);
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long mat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_unsafe_mulAdd(dest, src, mat);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 28L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat02, _selfz, java.lang.Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_mulAdd(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 28L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        UnsafeOpsHolder.U.putFloat(dest, ((_mat02) * (_selfz) + (((_mat00) * (_selfx) + (_mat01 * _selfy)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static long preMulDirectionMat3x4_unsafe(long dest, long src, long mat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulDirectionMat3x4_unsafe_mulAdd(dest, src, mat);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 36L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 40L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat02, _selfz, java.lang.Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMulDirectionMat3x4_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulDirectionMat3x4_unsafe_mulAdd(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 36L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 40L);
        UnsafeOpsHolder.U.putFloat(dest, ((_mat02) * (_selfz) + (((_mat00) * (_selfx) + (_mat01 * _selfy)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static long preMulDirectionMat4x4_unsafe(long dest, long src, long mat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulDirectionMat4x4_unsafe_mulAdd(dest, src, mat);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 36L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 40L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat02, _selfz, java.lang.Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMulDirectionMat4x4_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulDirectionMat4x4_unsafe_mulAdd(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 36L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 40L);
        UnsafeOpsHolder.U.putFloat(dest, ((_mat02) * (_selfz) + (((_mat00) * (_selfx) + (_mat01 * _selfy)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static long preMulPositionMat3x4_unsafe(long dest, long src, long mat) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat3x4_unsafe_fma(dest, src, mat);
        return preMulPositionMat3x4_unsafe_mulAdd(dest, src, mat);
    }

    /** {@code preMulPositionMat3x4_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulPositionMat3x4_unsafe_fma(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat03 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat13 = UnsafeOpsHolder.U.getFloat(mat + 28L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 36L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 40L);
        float _mat23 = UnsafeOpsHolder.U.getFloat(mat + 44L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat00, _selfx, java.lang.Math.fma(_mat01, _selfy, java.lang.Math.fma(_mat02, _selfz, _mat03))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    /** {@code preMulPositionMat3x4_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulPositionMat3x4_unsafe_mulAdd(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat03 = UnsafeOpsHolder.U.getFloat(mat + 12L);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat13 = UnsafeOpsHolder.U.getFloat(mat + 28L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 36L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 40L);
        float _mat23 = UnsafeOpsHolder.U.getFloat(mat + 44L);
        UnsafeOpsHolder.U.putFloat(dest, ((_mat00) * (_selfx) + (((_mat01) * (_selfy) + (((_mat02) * (_selfz) + (_mat03)))))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))));
        return dest;
    }

    public static long preMulPositionMat4x4_unsafe(long dest, long src, long mat) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat4x4_unsafe_fma(dest, src, mat);
        return preMulPositionMat4x4_unsafe_mulAdd(dest, src, mat);
    }

    /** {@code preMulPositionMat4x4_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulPositionMat4x4_unsafe_fma(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 36L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 40L);
        float _mat03 = UnsafeOpsHolder.U.getFloat(mat + 48L);
        float _mat13 = UnsafeOpsHolder.U.getFloat(mat + 52L);
        float _mat23 = UnsafeOpsHolder.U.getFloat(mat + 56L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat00, _selfx, java.lang.Math.fma(_mat01, _selfy, java.lang.Math.fma(_mat02, _selfz, _mat03))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    /** {@code preMulPositionMat4x4_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulPositionMat4x4_unsafe_mulAdd(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 36L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 40L);
        float _mat03 = UnsafeOpsHolder.U.getFloat(mat + 48L);
        float _mat13 = UnsafeOpsHolder.U.getFloat(mat + 52L);
        float _mat23 = UnsafeOpsHolder.U.getFloat(mat + 56L);
        UnsafeOpsHolder.U.putFloat(dest, ((_mat00) * (_selfx) + (((_mat01) * (_selfy) + (((_mat02) * (_selfz) + (_mat03)))))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))));
        return dest;
    }

    public static long preMulProject_unsafe(long dest, long src, long mat) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulProject_unsafe_fma(dest, src, mat);
        return preMulProject_unsafe_mulAdd(dest, src, mat);
    }

    /** {@code preMulProject_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulProject_unsafe_fma(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 36L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 40L);
        float _mat03 = UnsafeOpsHolder.U.getFloat(mat + 48L);
        float _mat13 = UnsafeOpsHolder.U.getFloat(mat + 52L);
        float _mat23 = UnsafeOpsHolder.U.getFloat(mat + 56L);
        return preMulProject_unsafe_fma_sc229c305_1(dest, mat, _selfx, _selfy, _selfz, _mat00, _mat10, _mat20, _mat01, _mat11, _mat21, _mat02, _mat12, _mat22, _mat03, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulProject_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preMulProject_unsafe_fma_sc229c305_1(long dest, long mat, float _selfx, float _selfy, float _selfz, float _mat00, float _mat10, float _mat20, float _mat01, float _mat11, float _mat21, float _mat02, float _mat12, float _mat22, float _mat03, float _mat13, float _mat23) {
        float _t2_inv = 1.0f / java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(mat + 12L), _selfx, java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(mat + 28L), _selfy, java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(mat + 44L), _selfz, UnsafeOpsHolder.U.getFloat(mat + 60L))));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat00, _selfx, java.lang.Math.fma(_mat01, _selfy, java.lang.Math.fma(_mat02, _selfz, _mat03))) * _t2_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))) * _t2_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))) * _t2_inv);
        return dest;
    }

    /** {@code preMulProject_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMulProject_unsafe_mulAdd(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
        float _mat10 = UnsafeOpsHolder.U.getFloat(mat + 4L);
        float _mat20 = UnsafeOpsHolder.U.getFloat(mat + 8L);
        float _mat01 = UnsafeOpsHolder.U.getFloat(mat + 16L);
        float _mat11 = UnsafeOpsHolder.U.getFloat(mat + 20L);
        float _mat21 = UnsafeOpsHolder.U.getFloat(mat + 24L);
        float _mat02 = UnsafeOpsHolder.U.getFloat(mat + 32L);
        float _mat12 = UnsafeOpsHolder.U.getFloat(mat + 36L);
        float _mat22 = UnsafeOpsHolder.U.getFloat(mat + 40L);
        float _mat03 = UnsafeOpsHolder.U.getFloat(mat + 48L);
        float _mat13 = UnsafeOpsHolder.U.getFloat(mat + 52L);
        float _mat23 = UnsafeOpsHolder.U.getFloat(mat + 56L);
        return preMulProject_unsafe_mulAdd_sa199ea6e_1(dest, mat, _selfx, _selfy, _selfz, _mat00, _mat10, _mat20, _mat01, _mat11, _mat21, _mat02, _mat12, _mat22, _mat03, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMulProject_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preMulProject_unsafe_mulAdd_sa199ea6e_1(long dest, long mat, float _selfx, float _selfy, float _selfz, float _mat00, float _mat10, float _mat20, float _mat01, float _mat11, float _mat21, float _mat02, float _mat12, float _mat22, float _mat03, float _mat13, float _mat23) {
        float _t2_inv = 1.0f / ((UnsafeOpsHolder.U.getFloat(mat + 12L)) * (_selfx) + (((UnsafeOpsHolder.U.getFloat(mat + 28L)) * (_selfy) + (((UnsafeOpsHolder.U.getFloat(mat + 44L)) * (_selfz) + (UnsafeOpsHolder.U.getFloat(mat + 60L)))))));
        UnsafeOpsHolder.U.putFloat(dest, ((_mat00) * (_selfx) + (((_mat01) * (_selfy) + (((_mat02) * (_selfz) + (_mat03)))))) * _t2_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))) * _t2_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))) * _t2_inv);
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_unsafe_mulAdd(dest, src, quatX, quatY, quatZ, quatW);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t9 = 2.0f * java.lang.Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * java.lang.Math.fma(quatY, _selfz, -(quatZ * _selfy));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, _selfz))));
        return dest;
    }

    /** {@code rotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_mulAdd(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t9 = 2.0f * ((quatX) * (_selfy) - (quatY * _selfx));
        float _t10 = 2.0f * ((quatZ) * (_selfx) - (quatX * _selfz));
        float _t11 = 2.0f * ((quatY) * (_selfz) - (quatZ * _selfy));
        UnsafeOpsHolder.U.putFloat(dest, ((quatY) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatZ) * (_t10)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((quatZ) * (_t11) + (((((quatW) * (_t10) + (_selfy))) - (quatX) * (_t9)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfz))) - (quatY) * (_t11)))));
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, long quat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_unsafe_mulAdd(dest, src, quat);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _quatx = UnsafeOpsHolder.U.getFloat(quat);
        float _quaty = UnsafeOpsHolder.U.getFloat(quat + 4L);
        float _quatz = UnsafeOpsHolder.U.getFloat(quat + 8L);
        float _quatw = UnsafeOpsHolder.U.getFloat(quat + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        float _t11 = 2.0f * java.lang.Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_quaty, _t9, java.lang.Math.fma(-_quatz, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_quatz, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quaty, _t11, java.lang.Math.fma(_quatw, _t9, _selfz))));
        return dest;
    }

    /** {@code rotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_mulAdd(long dest, long src, long quat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _quatx = UnsafeOpsHolder.U.getFloat(quat);
        float _quaty = UnsafeOpsHolder.U.getFloat(quat + 4L);
        float _quatz = UnsafeOpsHolder.U.getFloat(quat + 8L);
        float _quatw = UnsafeOpsHolder.U.getFloat(quat + 12L);
        float _t9 = 2.0f * ((_quatx) * (_selfy) - (_quaty * _selfx));
        float _t10 = 2.0f * ((_quatz) * (_selfx) - (_quatx * _selfz));
        float _t11 = 2.0f * ((_quaty) * (_selfz) - (_quatz * _selfy));
        UnsafeOpsHolder.U.putFloat(dest, ((_quaty) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quatz) * (_t10)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_quatz) * (_t11) + (((((_quatw) * (_t10) + (_selfy))) - (_quatx) * (_t9)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfz))) - (_quaty) * (_t11)))));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_unsafe_mulAdd(dest, src, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = _selfy - pivotY;
        float _t1 = _selfx - pivotX;
        float _t2 = _selfz - pivotZ;
        float _t12 = 2.0f * java.lang.Math.fma(quatX, _t0, -(quatY * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(quatZ, _t1, -(quatX * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(quatY, _t2, -(quatZ * _t0));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(quatY, _t12, java.lang.Math.fma(-quatZ, _t13, java.lang.Math.fma(quatW, _t14, _selfx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(quatZ, _t14, java.lang.Math.fma(-quatX, _t12, java.lang.Math.fma(quatW, _t13, _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(quatX, _t13, java.lang.Math.fma(-quatY, _t14, java.lang.Math.fma(quatW, _t12, _selfz))));
        return dest;
    }

    /** {@code rotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_mulAdd(long dest, long src, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = _selfy - pivotY;
        float _t1 = _selfx - pivotX;
        float _t2 = _selfz - pivotZ;
        float _t12 = 2.0f * ((quatX) * (_t0) - (quatY * _t1));
        float _t13 = 2.0f * ((quatZ) * (_t1) - (quatX * _t2));
        float _t14 = 2.0f * ((quatY) * (_t2) - (quatZ * _t0));
        UnsafeOpsHolder.U.putFloat(dest, ((quatY) * (_t12) + (((((quatW) * (_t14) + (_selfx))) - (quatZ) * (_t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((quatZ) * (_t14) + (((((quatW) * (_t13) + (_selfy))) - (quatX) * (_t12)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((quatX) * (_t13) + (((((quatW) * (_t12) + (_selfz))) - (quatY) * (_t14)))));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long quat, long pivot) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_unsafe_fma(dest, src, quat, pivot);
        return rotateAround_unsafe_mulAdd(dest, src, quat, pivot);
    }

    /** {@code rotateAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_fma(long dest, long src, long quat, long pivot) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _quatx = UnsafeOpsHolder.U.getFloat(quat);
        float _quaty = UnsafeOpsHolder.U.getFloat(quat + 4L);
        float _quatz = UnsafeOpsHolder.U.getFloat(quat + 8L);
        float _quatw = UnsafeOpsHolder.U.getFloat(quat + 12L);
        float _t0 = _selfy - UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _t1 = _selfx - UnsafeOpsHolder.U.getFloat(pivot);
        float _t2 = _selfz - UnsafeOpsHolder.U.getFloat(pivot + 8L);
        float _t12 = 2.0f * java.lang.Math.fma(_quatx, _t0, -(_quaty * _t1));
        float _t13 = 2.0f * java.lang.Math.fma(_quatz, _t1, -(_quatx * _t2));
        float _t14 = 2.0f * java.lang.Math.fma(_quaty, _t2, -(_quatz * _t0));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_quaty, _t12, java.lang.Math.fma(-_quatz, _t13, java.lang.Math.fma(_quatw, _t14, _selfx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_quatz, _t14, java.lang.Math.fma(-_quatx, _t12, java.lang.Math.fma(_quatw, _t13, _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_quatx, _t13, java.lang.Math.fma(-_quaty, _t14, java.lang.Math.fma(_quatw, _t12, _selfz))));
        return dest;
    }

    /** {@code rotateAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAround_unsafe_mulAdd(long dest, long src, long quat, long pivot) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _quatx = UnsafeOpsHolder.U.getFloat(quat);
        float _quaty = UnsafeOpsHolder.U.getFloat(quat + 4L);
        float _quatz = UnsafeOpsHolder.U.getFloat(quat + 8L);
        float _quatw = UnsafeOpsHolder.U.getFloat(quat + 12L);
        float _t0 = _selfy - UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _t1 = _selfx - UnsafeOpsHolder.U.getFloat(pivot);
        float _t2 = _selfz - UnsafeOpsHolder.U.getFloat(pivot + 8L);
        float _t12 = 2.0f * ((_quatx) * (_t0) - (_quaty * _t1));
        float _t13 = 2.0f * ((_quatz) * (_t1) - (_quatx * _t2));
        float _t14 = 2.0f * ((_quaty) * (_t2) - (_quatz * _t0));
        UnsafeOpsHolder.U.putFloat(dest, ((_quaty) * (_t12) + (((((_quatw) * (_t14) + (_selfx))) - (_quatz) * (_t13)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_quatz) * (_t14) + (((((_quatw) * (_t13) + (_selfy))) - (_quatx) * (_t12)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_quatx) * (_t13) + (((((_quatw) * (_t12) + (_selfz))) - (_quaty) * (_t14)))));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_mulAdd(dest, src, angle, axisX, axisY, axisZ);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t1)));
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        UnsafeOpsHolder.U.putFloat(dest, ((_t2) * (axisX * _t5) + (((_selfx) * (_t0) + (((axisY) * (_selfz) - (axisZ * _selfy)) * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t2) * (axisY * _t5) + (((_selfy) * (_t0) + (((axisZ) * (_selfx) - (axisX * _selfz)) * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t2) * (axisZ * _t5) + (((_selfz) * (_t0) + (((axisX) * (_selfy) - (axisY * _selfx)) * _t1)))));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_mulAdd(dest, src, axis, angle);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t2, _axisx * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t2, _axisy * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t2, _axisz * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t1)));
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, long axis, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        UnsafeOpsHolder.U.putFloat(dest, ((_t2) * (_axisx * _t5) + (((_selfx) * (_t0) + (((_axisy) * (_selfz) - (_axisz * _selfy)) * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t2) * (_axisy * _t5) + (((_selfy) * (_t0) + (((_axisz) * (_selfx) - (_axisx * _selfz)) * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t2) * (_axisz * _t5) + (((_selfz) * (_t0) + (((_axisx) * (_selfy) - (_axisy * _selfx)) * _t1)))));
        return dest;
    }

    public static long rotateAxisAround_unsafe(long dest, long src, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxisAround_unsafe_mulAdd(dest, src, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t3 = UnsafeOpsHolder.U.getFloat(src) - pivotX;
        float _t4 = UnsafeOpsHolder.U.getFloat(src + 8L) - pivotZ;
        float _t5 = UnsafeOpsHolder.U.getFloat(src + 4L) - pivotY;
        float _t8 = java.lang.Math.fma(axisZ, _t4, java.lang.Math.fma(axisX, _t3, axisY * _t5));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(java.lang.Math.fma(axisY, _t4, -(axisZ * _t5)), _t1, java.lang.Math.fma(_t2, axisX * _t8, pivotX))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t5, _t0, java.lang.Math.fma(java.lang.Math.fma(axisZ, _t3, -(axisX * _t4)), _t1, java.lang.Math.fma(_t2, axisY * _t8, pivotY))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t4, _t0, java.lang.Math.fma(java.lang.Math.fma(axisX, _t5, -(axisY * _t3)), _t1, java.lang.Math.fma(_t2, axisZ * _t8, pivotZ))));
        return dest;
    }

    /** {@code rotateAxisAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxisAround_unsafe_mulAdd(long dest, long src, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t3 = UnsafeOpsHolder.U.getFloat(src) - pivotX;
        float _t4 = UnsafeOpsHolder.U.getFloat(src + 8L) - pivotZ;
        float _t5 = UnsafeOpsHolder.U.getFloat(src + 4L) - pivotY;
        float _t8 = ((axisZ) * (_t4) + (((axisX) * (_t3) + (axisY * _t5))));
        UnsafeOpsHolder.U.putFloat(dest, ((_t3) * (_t0) + (((((axisY) * (_t4) - (axisZ * _t5))) * (_t1) + (((_t2) * (axisX * _t8) + (pivotX)))))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t5) * (_t0) + (((((axisZ) * (_t3) - (axisX * _t4))) * (_t1) + (((_t2) * (axisY * _t8) + (pivotY)))))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t4) * (_t0) + (((((axisX) * (_t5) - (axisY * _t3))) * (_t1) + (((_t2) * (axisZ * _t8) + (pivotZ)))))));
        return dest;
    }

    public static long rotateAxisAround_unsafe(long dest, long src, long axis, long pivot, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxisAround_unsafe_fma(dest, src, axis, pivot, angle);
        return rotateAxisAround_unsafe_mulAdd(dest, src, axis, pivot, angle);
    }

    /** {@code rotateAxisAround_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxisAround_unsafe_fma(long dest, long src, long axis, long pivot, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(pivot + 8L);
        float _t2 = 1.0f - _t0;
        float _t3 = UnsafeOpsHolder.U.getFloat(src) - _pivotx;
        float _t4 = UnsafeOpsHolder.U.getFloat(src + 8L) - _pivotz;
        float _t5 = UnsafeOpsHolder.U.getFloat(src + 4L) - _pivoty;
        float _t8 = java.lang.Math.fma(_axisz, _t4, java.lang.Math.fma(_axisx, _t3, _axisy * _t5));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisy, _t4, -(_axisz * _t5)), _t1, java.lang.Math.fma(_t2, _axisx * _t8, _pivotx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t5, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisz, _t3, -(_axisx * _t4)), _t1, java.lang.Math.fma(_t2, _axisy * _t8, _pivoty))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t4, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisx, _t5, -(_axisy * _t3)), _t1, java.lang.Math.fma(_t2, _axisz * _t8, _pivotz))));
        return dest;
    }

    /** {@code rotateAxisAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxisAround_unsafe_mulAdd(long dest, long src, long axis, long pivot, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(pivot + 8L);
        float _t2 = 1.0f - _t0;
        float _t3 = UnsafeOpsHolder.U.getFloat(src) - _pivotx;
        float _t4 = UnsafeOpsHolder.U.getFloat(src + 8L) - _pivotz;
        float _t5 = UnsafeOpsHolder.U.getFloat(src + 4L) - _pivoty;
        float _t8 = ((_axisz) * (_t4) + (((_axisx) * (_t3) + (_axisy * _t5))));
        UnsafeOpsHolder.U.putFloat(dest, ((_t3) * (_t0) + (((((_axisy) * (_t4) - (_axisz * _t5))) * (_t1) + (((_t2) * (_axisx * _t8) + (_pivotx)))))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t5) * (_t0) + (((((_axisz) * (_t3) - (_axisx * _t4))) * (_t1) + (((_t2) * (_axisy * _t8) + (_pivoty)))))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t4) * (_t0) + (((((_axisx) * (_t5) - (_axisy * _t3))) * (_t1) + (((_t2) * (_axisz * _t8) + (_pivotz)))))));
        return dest;
    }

    public static long rotateInverse_unsafe(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_unsafe_mulAdd(dest, src, quatX, quatY, quatZ, quatW);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t9 = 2.0f * java.lang.Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * java.lang.Math.fma(quatZ, _selfy, -(quatY * _selfz));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfz))));
        return dest;
    }

    /** {@code rotateInverse_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateInverse_unsafe_mulAdd(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t9 = 2.0f * ((quatX) * (_selfz) - (quatZ * _selfx));
        float _t10 = 2.0f * ((quatY) * (_selfx) - (quatX * _selfy));
        float _t11 = 2.0f * ((quatZ) * (_selfy) - (quatY * _selfz));
        UnsafeOpsHolder.U.putFloat(dest, ((quatZ) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatY) * (_t10)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfy))) - (quatZ) * (_t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((quatY) * (_t11) + (((((quatW) * (_t10) + (_selfz))) - (quatX) * (_t9)))));
        return dest;
    }

    public static long rotateInverse_unsafe(long dest, long src, long quat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_unsafe_mulAdd(dest, src, quat);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _quatx = UnsafeOpsHolder.U.getFloat(quat);
        float _quaty = UnsafeOpsHolder.U.getFloat(quat + 4L);
        float _quatz = UnsafeOpsHolder.U.getFloat(quat + 8L);
        float _quatw = UnsafeOpsHolder.U.getFloat(quat + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        float _t11 = 2.0f * java.lang.Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_quatz, _t9, java.lang.Math.fma(-_quaty, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quatz, _t11, java.lang.Math.fma(_quatw, _t9, _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_quaty, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfz))));
        return dest;
    }

    /** {@code rotateInverse_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateInverse_unsafe_mulAdd(long dest, long src, long quat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _quatx = UnsafeOpsHolder.U.getFloat(quat);
        float _quaty = UnsafeOpsHolder.U.getFloat(quat + 4L);
        float _quatz = UnsafeOpsHolder.U.getFloat(quat + 8L);
        float _quatw = UnsafeOpsHolder.U.getFloat(quat + 12L);
        float _t9 = 2.0f * ((_quatx) * (_selfz) - (_quatz * _selfx));
        float _t10 = 2.0f * ((_quaty) * (_selfx) - (_quatx * _selfy));
        float _t11 = 2.0f * ((_quatz) * (_selfy) - (_quaty * _selfz));
        UnsafeOpsHolder.U.putFloat(dest, ((_quatz) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quaty) * (_t10)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfy))) - (_quatz) * (_t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_quaty) * (_t11) + (((((_quatw) * (_t10) + (_selfz))) - (_quatx) * (_t9)))));
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, _selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t0, -(_selfz * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfy, _t1, _selfz * _t0));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, _selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t0) - (_selfz * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfy) * (_t1) + (_selfz * _t0)));
            return dest;
        }
    }

    public static long rotateXAround_unsafe(long dest, long src, float angle, float pivotY, float pivotZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src + 4L) - pivotY;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 8L) - pivotZ;
            UnsafeOpsHolder.U.putFloat(dest, _selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotY)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotZ)));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src + 4L) - pivotY;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 8L) - pivotZ;
            UnsafeOpsHolder.U.putFloat(dest, _selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t2) * (_t0) + (((pivotY) - (_t3) * (_t1)))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotZ)))));
            return dest;
        }
    }

    public static long rotateXAround_unsafe(long dest, long src, long pivot, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateXAround_unsafe_mulAdd(dest, src, pivot, angle);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(pivot + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = UnsafeOpsHolder.U.getFloat(src + 4L) - _pivoty;
        float _t3 = UnsafeOpsHolder.U.getFloat(src + 8L) - _pivotz;
        UnsafeOpsHolder.U.putFloat(dest, _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, _pivoty)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, _pivotz)));
        return dest;
    }

    /** {@code rotateXAround_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXAround_unsafe_mulAdd(long dest, long src, long pivot, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(pivot + 8L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = UnsafeOpsHolder.U.getFloat(src + 4L) - _pivoty;
        float _t3 = UnsafeOpsHolder.U.getFloat(src + 8L) - _pivotz;
        UnsafeOpsHolder.U.putFloat(dest, _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t2) * (_t0) + (((_pivoty) - (_t3) * (_t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t2) * (_t1) + (((_t3) * (_t0) + (_pivotz)))));
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t0, _selfz * _t1));
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t0, -(_selfx * _t1)));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t0) + (_selfz * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t0) - (_selfx * _t1)));
            return dest;
        }
    }

    public static long rotateYAround_unsafe(long dest, long src, float angle, float pivotX, float pivotZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - pivotX;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 8L) - pivotZ;
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotX)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(-_t2, _t1, pivotZ)));
            return dest;
        } else {
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - pivotX;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 8L) - pivotZ;
            UnsafeOpsHolder.U.putFloat(dest, ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotX)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t3) * (_t0) + (((pivotZ) - (_t2) * (_t1)))));
            return dest;
        }
    }

    public static long rotateYAround_unsafe(long dest, long src, long pivot, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
            float _pivotz = UnsafeOpsHolder.U.getFloat(pivot + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - _pivotx;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 8L) - _pivotz;
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, _pivotx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(-_t2, _t1, _pivotz)));
            return dest;
        } else {
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
            float _pivotz = UnsafeOpsHolder.U.getFloat(pivot + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - _pivotx;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 8L) - _pivotz;
            UnsafeOpsHolder.U.putFloat(dest, ((_t2) * (_t0) + (((_t3) * (_t1) + (_pivotx)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t3) * (_t0) + (((_pivotz) - (_t2) * (_t1)))));
            return dest;
        }
    }

    public static long rotateZ_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t0, -(_selfy * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfx, _t1, _selfy * _t0));
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t0) - (_selfy * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfx) * (_t1) + (_selfy * _t0)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
            return dest;
        }
    }

    public static long rotateZAround_unsafe(long dest, long src, float angle, float pivotX, float pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - pivotX;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - pivotY;
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotX)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotY)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
            return dest;
        } else {
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - pivotX;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - pivotY;
            UnsafeOpsHolder.U.putFloat(dest, ((_t2) * (_t0) + (((pivotX) - (_t3) * (_t1)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotY)))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
            return dest;
        }
    }

    public static long rotateZAround_unsafe(long dest, long src, long pivot, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
            float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - _pivotx;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - _pivoty;
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, _pivotx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, _pivoty)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
            return dest;
        } else {
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
            float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - _pivotx;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - _pivoty;
            UnsafeOpsHolder.U.putFloat(dest, ((_t2) * (_t0) + (((_pivotx) - (_t3) * (_t1)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t2) * (_t1) + (((_t3) * (_t0) + (_pivoty)))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
            return dest;
        }
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

    /**
     * The floored remainder of x and y, exactly kotlin.Float.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
    private static float flooredMod(float x, float y) {
        float q = Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p24f && java.lang.Math.abs(y) <= Float.MAX_VALUE) {
            float r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0f), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        float r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }
}
