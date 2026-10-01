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
 * to keep generated sources IDE-sized; public only because {@code Float4Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float4OpsKernelsAddress {
    private Float4OpsKernelsAddress() {}

    public static long add_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, otherX + _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, otherY + _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, otherZ + _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, otherW + _selfw);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _otherx + _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _othery + _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _otherz + _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _otherw + _selfw);
        return dest;
    }

    public static long div_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx / scalar);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / scalar);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz / scalar);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw / scalar);
        return dest;
    }

    public static long div_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx / otherX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / otherY);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz / otherZ);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw / otherW);
        return dest;
    }

    public static long div_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx / _otherx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy / _othery);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz / _otherz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw / _otherw);
        return dest;
    }

    public static long fma_unsafe(long dest, long src, float b, float cX, float cY, float cZ, float cW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, b, cX));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, b, cY));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, b, cZ));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, b, cW));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (b) + (cX)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (b) + (cY)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (b) + (cZ)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (b) + (cW)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, long c, float b) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_unsafe_mulAdd(dest, src, c, b);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _cx = UnsafeOpsHolder.U.getFloat(c);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        float _cw = UnsafeOpsHolder.U.getFloat(c + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, b, _cx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, b, _cy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, b, _cz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, b, _cw));
        return dest;
    }

    /** {@code fma_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long fma_unsafe_mulAdd(long dest, long src, long c, float b) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _cx = UnsafeOpsHolder.U.getFloat(c);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        float _cw = UnsafeOpsHolder.U.getFloat(c + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (b) + (_cx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (b) + (_cy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (b) + (_cz)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (b) + (_cw)));
        return dest;
    }

    public static long fma_unsafe(long dest, long src, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, bX, cX));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, bY, cY));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, bZ, cZ));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, bW, cW));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (bX) + (cX)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (bY) + (cY)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (bZ) + (cZ)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (bW) + (cW)));
            return dest;
        }
    }

    public static long fma_unsafe(long dest, long src, long b, long c) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_unsafe_mulAdd(dest, src, b, c);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _bx = UnsafeOpsHolder.U.getFloat(b);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _bw = UnsafeOpsHolder.U.getFloat(b + 12L);
        float _cx = UnsafeOpsHolder.U.getFloat(c);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        float _cw = UnsafeOpsHolder.U.getFloat(c + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _bx, _cx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _by, _cy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _bz, _cz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    /** {@code fma_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long fma_unsafe_mulAdd(long dest, long src, long b, long c) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _bx = UnsafeOpsHolder.U.getFloat(b);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _bw = UnsafeOpsHolder.U.getFloat(b + 12L);
        float _cx = UnsafeOpsHolder.U.getFloat(c);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        float _cw = UnsafeOpsHolder.U.getFloat(c + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_bx) + (_cx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_by) + (_cy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_bz) + (_cz)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_bw) + (_cw)));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, scalar * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, scalar * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, scalar * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, scalar * _selfw);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, otherX * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, otherY * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, otherZ * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, otherW * _selfw);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _otherx * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _othery * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _otherz * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _otherw * _selfw);
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, -_selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_selfw);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx - otherX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy - otherY);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz - otherZ);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw - otherW);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx - _otherx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy - _othery);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz - _otherz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw - _otherw);
        return dest;
    }

    public static long makeUniformDirection_unsafe(long dest, float u, float v, float w) {
        float _t0 = (float) java.lang.Math.sqrt(u);
        float _t1 = v * 6.2831855f;
        float _t3 = w * 6.2831855f;
        float _t4 = (float) java.lang.Math.sqrt(1.0f - u);
        UnsafeOpsHolder.U.putFloat(dest, Math.cosWithSin(_t1) * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.sinWithCos(_t1) * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.cosWithSin(_t3) * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.sinWithCos(_t3) * _t0);
        return dest;
    }

    public static long set_unsafe(long dest, float vX, float vY, float vZ, float vW) {
        UnsafeOpsHolder.U.putFloat(dest, vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, vY);
        UnsafeOpsHolder.U.putFloat(dest + 8L, vZ);
        UnsafeOpsHolder.U.putFloat(dest + 12L, vW);
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _vw = UnsafeOpsHolder.U.getFloat(v + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _vy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _vz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _vw);
        return dest;
    }

    public static long set_unsafe(long dest, float s) {
        UnsafeOpsHolder.U.putFloat(dest, s);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s);
        return dest;
    }

    public static long makeZero_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        return dest;
    }

    public static long bezier_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
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
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(p1X, _t7, _selfx * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(p1Y, _t7, _selfy * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(p1Z, _t7, _selfz * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(p1W, _t7, _selfw * _t8) + java.lang.Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    /** {@code bezier_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
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
        UnsafeOpsHolder.U.putFloat(dest, ((p1X) * (_t7) + (_selfx * _t8)) + ((p2X) * (_t6) + (p3X * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((p1Y) * (_t7) + (_selfy * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((p1Z) * (_t7) + (_selfz * _t8)) + ((p2Z) * (_t6) + (p3Z * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((p1W) * (_t7) + (_selfw * _t8)) + ((p2W) * (_t6) + (p3W * _t2)));
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
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _p3w = UnsafeOpsHolder.U.getFloat(p3 + 12L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t3 = _t0 * _t0;
        return bezier_unsafe_fma_s136b7288_1(dest, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, t * _t1, 3.0f * _t0 * _t1, 3.0f * t * _t3, _t0 * _t3);
    }

    /** Piece 2 of {@code bezier_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long bezier_unsafe_fma_s136b7288_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w, float _t2, float _t6, float _t7, float _t8) {
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_p1x, _t7, _selfx * _t8) + java.lang.Math.fma(_p2x, _t6, _p3x * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_p1z, _t7, _selfz * _t8) + java.lang.Math.fma(_p2z, _t6, _p3z * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_p1w, _t7, _selfw * _t8) + java.lang.Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    /** {@code bezier_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _p3w = UnsafeOpsHolder.U.getFloat(p3 + 12L);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t3 = _t0 * _t0;
        return bezier_unsafe_mulAdd_sff8e514d_1(dest, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, t * _t1, 3.0f * _t0 * _t1, 3.0f * t * _t3, _t0 * _t3);
    }

    /** Piece 2 of {@code bezier_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long bezier_unsafe_mulAdd_sff8e514d_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w, float _t2, float _t6, float _t7, float _t8) {
        UnsafeOpsHolder.U.putFloat(dest, ((_p1x) * (_t7) + (_selfx * _t8)) + ((_p2x) * (_t6) + (_p3x * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_p1z) * (_t7) + (_selfz * _t8)) + ((_p2z) * (_t6) + (_p3z * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_p1w) * (_t7) + (_selfw * _t8)) + ((_p2w) * (_t6) + (_p3w * _t2)));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, _selfy * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, _selfz * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(p2W, _t0, java.lang.Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    /** {@code bezier2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, ((p2X) * (_t0) + (((p1X) * (_t3) + (_selfx * _t4)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((p2Y) * (_t0) + (((p1Y) * (_t3) + (_selfy * _t4)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((p2Z) * (_t0) + (((p1Z) * (_t3) + (_selfz * _t4)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((p2W) * (_t0) + (((p1W) * (_t3) + (_selfw * _t4)))));
        return dest;
    }

    public static long bezier2_unsafe(long dest, long src, long p1, long p2, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_unsafe_fma(dest, src, p1, p2, t);
        return bezier2_unsafe_mulAdd(dest, src, p1, p2, t);
    }

    /** {@code bezier2_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2_unsafe_fma(long dest, long src, long p1, long p2, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_p2x, _t0, java.lang.Math.fma(_p1x, _t3, _selfx * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_p2z, _t0, java.lang.Math.fma(_p1z, _t3, _selfz * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_p2w, _t0, java.lang.Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    /** {@code bezier2_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2_unsafe_mulAdd(long dest, long src, long p1, long p2, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, ((_p2x) * (_t0) + (((_p1x) * (_t3) + (_selfx * _t4)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_p2z) * (_t0) + (((_p1z) * (_t3) + (_selfz * _t4)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_p2w) * (_t0) + (((_p1w) * (_t3) + (_selfw * _t4)))));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2Tangent_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        UnsafeOpsHolder.U.putFloat(dest, ((p1X - _selfx) * (_t2) + ((p2X - p1X) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((p1Z - _selfz) * (_t2) + ((p2Z - p1Z) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((p1W - _selfw) * (_t2) + ((p2W - p1W) * _t1)));
        return dest;
    }

    public static long bezier2Tangent_unsafe(long dest, long src, long p1, long p2, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_unsafe_mulAdd(dest, src, p1, p2, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezier2Tangent_unsafe_mulAdd(long dest, long src, long p1, long p2, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        UnsafeOpsHolder.U.putFloat(dest, ((_p1x - _selfx) * (_t2) + ((_p2x - _p1x) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_p1z - _selfz) * (_t2) + ((_p2z - _p1z) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_p1w - _selfw) * (_t2) + ((_p2w - _p1w) * _t1)));
        return dest;
    }

    public static long bezierTangent_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(p3W - p2W, _t2, java.lang.Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezierTangent_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        UnsafeOpsHolder.U.putFloat(dest, ((p3X - p2X) * (_t2) + (((p1X - _selfx) * (_t6) + ((p2X - p1X) * _t5)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((p3Z - p2Z) * (_t2) + (((p1Z - _selfz) * (_t6) + ((p2Z - p1Z) * _t5)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((p3W - p2W) * (_t2) + (((p1W - _selfw) * (_t6) + ((p2W - p1W) * _t5)))));
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
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _p3w = UnsafeOpsHolder.U.getFloat(p3 + 12L);
        float _t1 = 1.0f - t;
        return bezierTangent_unsafe_fma_s720db537_1(dest, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, 3.0f * t * t, 6.0f * t * _t1, 3.0f * _t1 * _t1);
    }

    /** Piece 2 of {@code bezierTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long bezierTangent_unsafe_fma_s720db537_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w, float _t2, float _t5, float _t6) {
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_p3x - _p2x, _t2, java.lang.Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_p3z - _p2z, _t2, java.lang.Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_p3w - _p2w, _t2, java.lang.Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long bezierTangent_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _p3w = UnsafeOpsHolder.U.getFloat(p3 + 12L);
        float _t1 = 1.0f - t;
        return bezierTangent_unsafe_mulAdd_s25db1838_1(dest, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, 3.0f * t * t, 6.0f * t * _t1, 3.0f * _t1 * _t1);
    }

    /** Piece 2 of {@code bezierTangent_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long bezierTangent_unsafe_mulAdd_s25db1838_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w, float _t2, float _t5, float _t6) {
        UnsafeOpsHolder.U.putFloat(dest, ((_p3x - _p2x) * (_t2) + (((_p1x - _selfx) * (_t6) + ((_p2x - _p1x) * _t5)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_p3z - _p2z) * (_t2) + (((_p1z - _selfz) * (_t6) + ((_p2z - _p1z) * _t5)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_p3w - _p2w) * (_t2) + (((_p1w - _selfw) * (_t6) + ((_p2w - _p1w) * _t5)))));
        return dest;
    }

    public static long catmullRom_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_unsafe_fma(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRom_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRom_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_fma(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * (java.lang.Math.fma(2.0f, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (java.lang.Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_unsafe_fma_sa2f7419_1(dest, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_fma_sa2f7419_1(long dest, float p1Z, float p1W, float p2Z, float p2W, float p3Z, float p3W, float t, float _selfz, float _selfw, float _t0, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (java.lang.Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (java.lang.Math.fma(2.0f, p1W, t * (p2W - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, p2W, -p3W))), _t0, java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        float _t1 = t * _t0;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * (((2.0f) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) * (_t0) + (((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X)) * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (((2.0f) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) * (_t0) + (((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y)) * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (((2.0f) * (p1Z) + (t * (p2Z - _selfz))) + ((((((2.0f) * (_selfz) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) * (_t0) + (((((3.0f) * (p1Z) + (p3Z - _selfz))) - (3.0f) * (p2Z)) * _t1))));
        return catmullRom_unsafe_mulAdd_s5c170c44_1(dest, p1W, p2W, p3W, t, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_mulAdd_s5c170c44_1(long dest, float p1W, float p2W, float p3W, float t, float _selfw, float _t0, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (((2.0f) * (p1W) + (t * (p2W - _selfw))) + ((((((2.0f) * (_selfw) + (((4.0f) * (p2W) - (p3W))))) - (5.0f) * (p1W))) * (_t0) + (((((3.0f) * (p1W) + (p3W - _selfw))) - (3.0f) * (p2W)) * _t1))));
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
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _p3w = UnsafeOpsHolder.U.getFloat(p3 + 12L);
        float _t0 = t * t;
        return catmullRom_unsafe_fma_s6d33ebad_1(dest, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, _t0, t * _t0);
    }

    /** Piece 2 of {@code catmullRom_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_fma_s6d33ebad_1(long dest, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w, float _t0, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * (java.lang.Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (java.lang.Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (java.lang.Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, _p2z, -_p3z))), _t0, java.lang.Math.fma(-3.0f, _p2z, java.lang.Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1)));
        return catmullRom_unsafe_fma_s6d33ebad_2(dest, t, _selfw, _p1w, _p2w, _p3w, _t0, _t1);
    }

    /** Piece 3 of {@code catmullRom_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_fma_s6d33ebad_2(long dest, float t, float _selfw, float _p1w, float _p2w, float _p3w, float _t0, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (java.lang.Math.fma(2.0f, _p1w, t * (_p2w - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1w, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, _p2w, -_p3w))), _t0, java.lang.Math.fma(-3.0f, _p2w, java.lang.Math.fma(3.0f, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRom_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _p3w = UnsafeOpsHolder.U.getFloat(p3 + 12L);
        float _t0 = t * t;
        return catmullRom_unsafe_mulAdd_s34ebee96_1(dest, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, _t0, t * _t0);
    }

    /** Piece 2 of {@code catmullRom_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRom_unsafe_mulAdd_s34ebee96_1(long dest, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w, float _t0, float _t1) {
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * (((2.0f) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) * (_t0) + (((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x)) * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (((2.0f) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) * (_t0) + (((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y)) * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (((2.0f) * (_p1z) + (t * (_p2z - _selfz))) + ((((((2.0f) * (_selfz) + (((4.0f) * (_p2z) - (_p3z))))) - (5.0f) * (_p1z))) * (_t0) + (((((3.0f) * (_p1z) + (_p3z - _selfz))) - (3.0f) * (_p2z)) * _t1))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (((2.0f) * (_p1w) + (t * (_p2w - _selfw))) + ((((((2.0f) * (_selfw) + (((4.0f) * (_p2w) - (_p3w))))) - (5.0f) * (_p1w))) * (_t0) + (((((3.0f) * (_p1w) + (_p3w - _selfw))) - (3.0f) * (_p2w)) * _t1))));
        return dest;
    }

    public static long catmullRomTangent_unsafe(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_unsafe_fma(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRomTangent_unsafe_mulAdd(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRomTangent_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_fma(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return catmullRomTangent_unsafe_fma_saf023f22_1(dest, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_fma_saf023f22_1(long dest, float p1Z, float p1W, float p2Z, float p2W, float p3Z, float p3W, float t, float _selfz, float _selfw, float _t0) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, p2Z, -p3Z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, p2W, -p3W))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    /** {@code catmullRomTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) + (((3.0f * ((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X))) * (_t0) + (p2X - _selfx)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) + (((3.0f * ((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y))) * (_t0) + (p2Y - _selfy)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfz) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) + (((3.0f * ((((3.0f) * (p1Z) + (p3Z - _selfz))) - (3.0f) * (p2Z))) * (_t0) + (p2Z - _selfz)))));
        return catmullRomTangent_unsafe_mulAdd_s62419d55_1(dest, p1W, p2W, p3W, t, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd_s62419d55_1(long dest, float p1W, float p2W, float p3W, float t, float _selfw, float _t0) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfw) + (((4.0f) * (p2W) - (p3W))))) - (5.0f) * (p1W))) + (((3.0f * ((((3.0f) * (p1W) + (p3W - _selfw))) - (3.0f) * (p2W))) * (_t0) + (p2W - _selfw)))));
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
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _p3w = UnsafeOpsHolder.U.getFloat(p3 + 12L);
        return catmullRomTangent_unsafe_fma_s6ecbdfbc_1(dest, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, t * t);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_fma_s6ecbdfbc_1(long dest, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w, float _t0) {
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, _p2z, -_p3z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2z, java.lang.Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return catmullRomTangent_unsafe_fma_s6ecbdfbc_2(dest, t, _selfw, _p1w, _p2w, _p3w, _t0);
    }

    /** Piece 3 of {@code catmullRomTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_fma_s6ecbdfbc_2(long dest, float t, float _selfw, float _p1w, float _p2w, float _p3w, float _t0) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1w, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, _p2w, -_p3w))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2w, java.lang.Math.fma(3.0f, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    /** {@code catmullRomTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd(long dest, long src, long p1, long p2, long p3, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _p1x = UnsafeOpsHolder.U.getFloat(p1);
        float _p1y = UnsafeOpsHolder.U.getFloat(p1 + 4L);
        float _p1z = UnsafeOpsHolder.U.getFloat(p1 + 8L);
        float _p1w = UnsafeOpsHolder.U.getFloat(p1 + 12L);
        float _p2x = UnsafeOpsHolder.U.getFloat(p2);
        float _p2y = UnsafeOpsHolder.U.getFloat(p2 + 4L);
        float _p2z = UnsafeOpsHolder.U.getFloat(p2 + 8L);
        float _p2w = UnsafeOpsHolder.U.getFloat(p2 + 12L);
        float _p3x = UnsafeOpsHolder.U.getFloat(p3);
        float _p3y = UnsafeOpsHolder.U.getFloat(p3 + 4L);
        float _p3z = UnsafeOpsHolder.U.getFloat(p3 + 8L);
        float _p3w = UnsafeOpsHolder.U.getFloat(p3 + 12L);
        return catmullRomTangent_unsafe_mulAdd_s28928921_1(dest, t, _selfx, _selfy, _selfz, _selfw, _p1x, _p1y, _p1z, _p1w, _p2x, _p2y, _p2z, _p2w, _p3x, _p3y, _p3z, _p3w, t * t);
    }

    /** Piece 2 of {@code catmullRomTangent_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long catmullRomTangent_unsafe_mulAdd_s28928921_1(long dest, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _p1x, float _p1y, float _p1z, float _p1w, float _p2x, float _p2y, float _p2z, float _p2w, float _p3x, float _p3y, float _p3z, float _p3w, float _t0) {
        UnsafeOpsHolder.U.putFloat(dest, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) + (((3.0f * ((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x))) * (_t0) + (_p2x - _selfx)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) + (((3.0f * ((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y))) * (_t0) + (_p2y - _selfy)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfz) + (((4.0f) * (_p2z) - (_p3z))))) - (5.0f) * (_p1z))) + (((3.0f * ((((3.0f) * (_p1z) + (_p3z - _selfz))) - (3.0f) * (_p2z))) * (_t0) + (_p2z - _selfz)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfw) + (((4.0f) * (_p2w) - (_p3w))))) - (5.0f) * (_p1w))) + (((3.0f * ((((3.0f) * (_p1w) + (_p3w - _selfw))) - (3.0f) * (_p2w))) * (_t0) + (_p2w - _selfw)))));
        return dest;
    }

    public static long hermite_unsafe(long dest, long src, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermite_unsafe_mulAdd(dest, src, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * java.lang.Math.fma(t, t, -t);
        float _t7 = java.lang.Math.fma(t - 2.0f, _t0, t);
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t10, t0W * _t7) + java.lang.Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    /** {@code hermite_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermite_unsafe_mulAdd(long dest, long src, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t10) + (t0W * _t7)) + ((t1W) * (_t5) + (v1W * _t9)));
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
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _t0z = UnsafeOpsHolder.U.getFloat(t0 + 8L);
        float _t0w = UnsafeOpsHolder.U.getFloat(t0 + 12L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _v1z = UnsafeOpsHolder.U.getFloat(v1 + 8L);
        float _v1w = UnsafeOpsHolder.U.getFloat(v1 + 12L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t1z = UnsafeOpsHolder.U.getFloat(t1 + 8L);
        float _t1w = UnsafeOpsHolder.U.getFloat(t1 + 12L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        return hermite_unsafe_fma_s3652656d_1(dest, _selfx, _selfy, _selfz, _selfw, _t0x, _t0y, _t0z, _t0w, _v1x, _v1y, _v1z, _v1w, _t1x, _t1y, _t1z, _t1w, t * java.lang.Math.fma(t, t, -t), java.lang.Math.fma(t - 2.0f, _t0, t), java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2)), java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f)));
    }

    /** Piece 2 of {@code hermite_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long hermite_unsafe_fma_s3652656d_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t0x, float _t0y, float _t0z, float _t0w, float _v1x, float _v1y, float _v1z, float _v1w, float _t1x, float _t1y, float _t1z, float _t1w, float _t5, float _t7, float _t9, float _t10) {
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t10, _t0x * _t7) + java.lang.Math.fma(_t1x, _t5, _v1x * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t10, _t0z * _t7) + java.lang.Math.fma(_t1z, _t5, _v1z * _t9));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t10, _t0w * _t7) + java.lang.Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    /** {@code hermite_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermite_unsafe_mulAdd(long dest, long src, long t0, long v1, long t1, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _t0z = UnsafeOpsHolder.U.getFloat(t0 + 8L);
        float _t0w = UnsafeOpsHolder.U.getFloat(t0 + 12L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _v1z = UnsafeOpsHolder.U.getFloat(v1 + 8L);
        float _v1w = UnsafeOpsHolder.U.getFloat(v1 + 12L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t1z = UnsafeOpsHolder.U.getFloat(t1 + 8L);
        float _t1w = UnsafeOpsHolder.U.getFloat(t1 + 12L);
        float _t0 = t * t;
        float _t2 = t * _t0;
        return hermite_unsafe_mulAdd_s97f7bc6a_1(dest, _selfx, _selfy, _selfz, _selfw, _t0x, _t0y, _t0z, _t0w, _v1x, _v1y, _v1z, _v1w, _t1x, _t1y, _t1z, _t1w, t * ((t) * (t) - (t)), ((t - 2.0f) * (_t0) + (t)), ((3.0f) * (_t0) - (_t2 + _t2)), ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0)))));
    }

    /** Piece 2 of {@code hermite_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long hermite_unsafe_mulAdd_s97f7bc6a_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t0x, float _t0y, float _t0z, float _t0w, float _v1x, float _v1y, float _v1z, float _v1w, float _t1x, float _t1y, float _t1z, float _t1w, float _t5, float _t7, float _t9, float _t10) {
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t10) + (_t0x * _t7)) + ((_t1x) * (_t5) + (_v1x * _t9)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t10) + (_t0z * _t7)) + ((_t1z) * (_t5) + (_v1z * _t9)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t10) + (_t0w * _t7)) + ((_t1w) * (_t5) + (_v1w * _t9)));
        return dest;
    }

    public static long hermiteTangent_unsafe(long dest, long src, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_unsafe_mulAdd(dest, src, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        float _t6 = 6.0f * java.lang.Math.fma(t, t, -t);
        float _t7 = 6.0f * java.lang.Math.fma(-t, t, t);
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t6, t0W * _t9) + java.lang.Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    /** {@code hermiteTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd(long dest, long src, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t6) + (t0W * _t9)) + ((t1W) * (_t8) + (v1W * _t7)));
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
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _t0z = UnsafeOpsHolder.U.getFloat(t0 + 8L);
        float _t0w = UnsafeOpsHolder.U.getFloat(t0 + 12L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _v1z = UnsafeOpsHolder.U.getFloat(v1 + 8L);
        float _v1w = UnsafeOpsHolder.U.getFloat(v1 + 12L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t1z = UnsafeOpsHolder.U.getFloat(t1 + 8L);
        float _t1w = UnsafeOpsHolder.U.getFloat(t1 + 12L);
        float _t0 = t * t;
        return hermiteTangent_unsafe_fma_s2b22155c_1(dest, _selfx, _selfy, _selfz, _selfw, _t0x, _t0y, _t0z, _t0w, _v1x, _v1y, _v1z, _v1w, _t1x, _t1y, _t1z, _t1w, 6.0f * java.lang.Math.fma(t, t, -t), 6.0f * java.lang.Math.fma(-t, t, t), java.lang.Math.fma(3.0f, _t0, -(t + t)), java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f)));
    }

    /** Piece 2 of {@code hermiteTangent_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long hermiteTangent_unsafe_fma_s2b22155c_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t0x, float _t0y, float _t0z, float _t0w, float _v1x, float _v1y, float _v1z, float _v1w, float _t1x, float _t1y, float _t1z, float _t1w, float _t6, float _t7, float _t8, float _t9) {
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t6, _t0x * _t9) + java.lang.Math.fma(_t1x, _t8, _v1x * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t6, _t0z * _t9) + java.lang.Math.fma(_t1z, _t8, _v1z * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t6, _t0w * _t9) + java.lang.Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    /** {@code hermiteTangent_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd(long dest, long src, long t0, long v1, long t1, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0x = UnsafeOpsHolder.U.getFloat(t0);
        float _t0y = UnsafeOpsHolder.U.getFloat(t0 + 4L);
        float _t0z = UnsafeOpsHolder.U.getFloat(t0 + 8L);
        float _t0w = UnsafeOpsHolder.U.getFloat(t0 + 12L);
        float _v1x = UnsafeOpsHolder.U.getFloat(v1);
        float _v1y = UnsafeOpsHolder.U.getFloat(v1 + 4L);
        float _v1z = UnsafeOpsHolder.U.getFloat(v1 + 8L);
        float _v1w = UnsafeOpsHolder.U.getFloat(v1 + 12L);
        float _t1x = UnsafeOpsHolder.U.getFloat(t1);
        float _t1y = UnsafeOpsHolder.U.getFloat(t1 + 4L);
        float _t1z = UnsafeOpsHolder.U.getFloat(t1 + 8L);
        float _t1w = UnsafeOpsHolder.U.getFloat(t1 + 12L);
        float _t0 = t * t;
        return hermiteTangent_unsafe_mulAdd_se595d3d1_1(dest, _selfx, _selfy, _selfz, _selfw, _t0x, _t0y, _t0z, _t0w, _v1x, _v1y, _v1z, _v1w, _t1x, _t1y, _t1z, _t1w, 6.0f * ((t) * (t) - (t)), 6.0f * ((t) - (t) * (t)), ((3.0f) * (_t0) - (t + t)), ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t)))));
    }

    /** Piece 2 of {@code hermiteTangent_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long hermiteTangent_unsafe_mulAdd_se595d3d1_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t0x, float _t0y, float _t0z, float _t0w, float _v1x, float _v1y, float _v1z, float _v1w, float _t1x, float _t1y, float _t1z, float _t1w, float _t6, float _t7, float _t8, float _t9) {
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t6) + (_t0x * _t9)) + ((_t1x) * (_t8) + (_v1x * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t6) + (_t0z * _t9)) + ((_t1z) * (_t8) + (_v1z * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t6) + (_t0w * _t9)) + ((_t1w) * (_t8) + (_v1w * _t7)));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(t, otherW - _selfw, _selfw));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (otherZ - _selfz) + (_selfz)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((t) * (otherW - _selfw) + (_selfw)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, long other, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_unsafe_mulAdd(dest, src, other, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lerp_unsafe_mulAdd(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((t) * (_otherx - _selfx) + (_selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (_othery - _selfy) + (_selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (_otherz - _selfz) + (_selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((t) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(tX, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(tY, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(tZ, otherZ - _selfz, _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(tW, otherW - _selfw, _selfw));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((tX) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((tY) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((tZ) * (otherZ - _selfz) + (_selfz)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((tW) * (otherW - _selfw) + (_selfw)));
            return dest;
        }
    }

    public static long lerp_unsafe(long dest, long src, long other, long t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_unsafe_mulAdd(dest, src, other, t);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _tx = UnsafeOpsHolder.U.getFloat(t);
        float _ty = UnsafeOpsHolder.U.getFloat(t + 4L);
        float _tz = UnsafeOpsHolder.U.getFloat(t + 8L);
        float _tw = UnsafeOpsHolder.U.getFloat(t + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_tx, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_ty, _othery - _selfy, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_tz, _otherz - _selfz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lerp_unsafe_mulAdd(long dest, long src, long other, long t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _tx = UnsafeOpsHolder.U.getFloat(t);
        float _ty = UnsafeOpsHolder.U.getFloat(t + 4L);
        float _tz = UnsafeOpsHolder.U.getFloat(t + 8L);
        float _tw = UnsafeOpsHolder.U.getFloat(t + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((_tx) * (_otherx - _selfx) + (_selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_ty) * (_othery - _selfy) + (_selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_tz) * (_otherz - _selfz) + (_selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_tw) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, otherX, otherY, otherZ, otherW, t);
        return slerp_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_unsafe_fma_sd88779b3_1(dest, src, otherX, otherY, otherZ, otherW, t, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sd88779b3_1(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(otherW, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_unsafe_fma_sd88779b3_2(dest, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sd88779b3_2(long dest, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _t64, float _sp0) {
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        return slerp_unsafe_mulAdd_s5b9cc662_1(dest, src, otherX, otherY, otherZ, otherW, t, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24)))))));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s5b9cc662_1(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31) {
        float _t40 = ((otherW) * (_t14) - (_t31 * _t19));
        float _t41 = ((otherZ) * (_t14) - (_t31 * _t21));
        float _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        float _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        UnsafeOpsHolder.U.putFloat(dest, ((_t16) * (_t64) + (_sp0 * _t51)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t24) * (_t64) + (_sp0 * _t52)));
        return slerp_unsafe_mulAdd_s5b9cc662_2(dest, _t19, _t21, _t49, _t50, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s5b9cc662_2(long dest, float _t19, float _t21, float _t49, float _t50, float _t64, float _sp0) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t21) * (_t64) + (_sp0 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, otherZ, otherW, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long slerp_degenerate_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, otherX, otherY, otherZ, otherW, t);
        return slerp_degenerate_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (otherZ - _selfz) + (_selfz)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((t) * (otherW - _selfw) + (_selfw)));
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
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t10 = java.lang.Math.fma(_otherw, _otherw, java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_unsafe_fma_sff32df1b_1(dest, src, other, t, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sff32df1b_1(long dest, long src, long other, float t, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = java.lang.Math.fma(_otherw * _t14, _t19, java.lang.Math.fma(_otherz * _t14, _t21, java.lang.Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(_otherw, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(_otherz, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(_otherx, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(_othery, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_unsafe_fma_sff32df1b_2(dest, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sff32df1b_2(long dest, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _t64, float _sp0) {
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t10 = ((_otherw) * (_otherw) + (((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_unsafe_mulAdd_s4d4fe30a_1(dest, src, other, t, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s4d4fe30a_1(long dest, long src, long other, float t, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = ((_otherw * _t14) * (_t19) + (((_otherz * _t14) * (_t21) + (((_otherx * _t14) * (_t16) + (_othery * _t14 * _t24))))));
        float _t40 = ((_otherw) * (_t14) - (_t31 * _t19));
        float _t41 = ((_otherz) * (_t14) - (_t31 * _t21));
        float _t42 = ((_otherx) * (_t14) - (_t31 * _t16));
        float _t43 = ((_othery) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        UnsafeOpsHolder.U.putFloat(dest, ((_t16) * (_t64) + (_sp0 * _t51)));
        return slerp_unsafe_mulAdd_s4d4fe30a_2(dest, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s4d4fe30a_2(long dest, float _t19, float _t21, float _t24, float _t49, float _t50, float _t52, float _t64, float _sp0) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t24) * (_t64) + (_sp0 * _t52)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t21) * (_t64) + (_sp0 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t);
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
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 1.4551915E-11f) {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (_othery - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (_otherz - _selfz) + (_selfz)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((t) * (_otherw - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_unsafe_fma_s62f6c999_1(dest, src, otherX, otherY, otherZ, otherW, t, epsilon, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s62f6c999_1(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(otherW, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_unsafe_fma_s62f6c999_2(dest, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s62f6c999_2(long dest, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _t64, float _sp0) {
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        return slerp_unsafe_mulAdd_s764fbade_1(dest, src, otherX, otherY, otherZ, otherW, t, epsilon, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24)))))));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s764fbade_1(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31) {
        float _t40 = ((otherW) * (_t14) - (_t31 * _t19));
        float _t41 = ((otherZ) * (_t14) - (_t31 * _t21));
        float _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        float _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        UnsafeOpsHolder.U.putFloat(dest, ((_t16) * (_t64) + (_sp0 * _t51)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t24) * (_t64) + (_sp0 * _t52)));
        return slerp_unsafe_mulAdd_s764fbade_2(dest, _t19, _t21, _t49, _t50, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s764fbade_2(long dest, float _t19, float _t21, float _t49, float _t50, float _t64, float _sp0) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t21) * (_t64) + (_sp0 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long slerp_degenerate_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_degenerate_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (otherX - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (otherY - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (otherZ - _selfz) + (_selfz)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((t) * (otherW - _selfw) + (_selfw)));
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
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t10 = java.lang.Math.fma(_otherw, _otherw, java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_unsafe_fma_s306ec141_1(dest, src, other, t, epsilon, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s306ec141_1(long dest, long src, long other, float t, float epsilon, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = java.lang.Math.fma(_otherw * _t14, _t19, java.lang.Math.fma(_otherz * _t14, _t21, java.lang.Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(_otherw, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(_otherz, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(_otherx, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(_othery, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_unsafe_fma_s306ec141_2(dest, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s306ec141_2(long dest, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _t64, float _sp0) {
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, long other, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t10 = ((_otherw) * (_otherw) + (((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_unsafe_mulAdd_s152962c6_1(dest, src, other, t, epsilon, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s152962c6_1(long dest, long src, long other, float t, float epsilon, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = ((_otherw * _t14) * (_t19) + (((_otherz * _t14) * (_t21) + (((_otherx * _t14) * (_t16) + (_othery * _t14 * _t24))))));
        float _t40 = ((_otherw) * (_t14) - (_t31 * _t19));
        float _t41 = ((_otherz) * (_t14) - (_t31 * _t21));
        float _t42 = ((_otherx) * (_t14) - (_t31 * _t16));
        float _t43 = ((_othery) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.slerp_degenerate(dest, src, other, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        UnsafeOpsHolder.U.putFloat(dest, ((_t16) * (_t64) + (_sp0 * _t51)));
        return slerp_unsafe_mulAdd_s152962c6_2(dest, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s152962c6_2(long dest, float _t19, float _t21, float _t24, float _t49, float _t50, float _t52, float _t64, float _sp0) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t24) * (_t64) + (_sp0 * _t52)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t21) * (_t64) + (_sp0 * _t50)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long other, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.slerp_degenerate_unsafe(dest, src, other, t, epsilon);
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
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, long other, float t, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
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
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                UnsafeOpsHolder.U.putFloat(dest, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            UnsafeOpsHolder.U.putFloat(dest, ((t) * (_otherx - _selfx) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (_othery - _selfy) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (_otherz - _selfz) + (_selfz)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((t) * (_otherw - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static long absolute_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.abs(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.abs(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.abs(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.abs(_selfw));
        return dest;
    }

    public static long acos_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.acos(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.acos(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.acos(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.acos(_selfw));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, float bX, float bY, float bZ, float bW, float scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(scalar, bX, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(scalar, bY, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(scalar, bZ, _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(scalar, bW, _selfw));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((scalar) * (bX) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((scalar) * (bY) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((scalar) * (bZ) + (_selfz)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((scalar) * (bW) + (_selfw)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, long b, float scalar) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_unsafe_mulAdd(dest, src, b, scalar);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _bx = UnsafeOpsHolder.U.getFloat(b);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _bw = UnsafeOpsHolder.U.getFloat(b + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(scalar, _bx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(scalar, _by, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(scalar, _bz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(scalar, _bw, _selfw));
        return dest;
    }

    /** {@code addScaled_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long addScaled_unsafe_mulAdd(long dest, long src, long b, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _bx = UnsafeOpsHolder.U.getFloat(b);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _bw = UnsafeOpsHolder.U.getFloat(b + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((scalar) * (_bx) + (_selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((scalar) * (_by) + (_selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((scalar) * (_bz) + (_selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((scalar) * (_bw) + (_selfw)));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(bX, cX, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(bY, cY, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(bZ, cZ, _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(bW, cW, _selfw));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((bX) * (cX) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((bY) * (cY) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((bZ) * (cZ) + (_selfz)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((bW) * (cW) + (_selfw)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, long b, long c) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_unsafe_mulAdd(dest, src, b, c);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _bx = UnsafeOpsHolder.U.getFloat(b);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _bw = UnsafeOpsHolder.U.getFloat(b + 12L);
        float _cx = UnsafeOpsHolder.U.getFloat(c);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        float _cw = UnsafeOpsHolder.U.getFloat(c + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_bx, _cx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_by, _cy, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_bz, _cz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    /** {@code addScaled_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long addScaled_unsafe_mulAdd(long dest, long src, long b, long c) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _bx = UnsafeOpsHolder.U.getFloat(b);
        float _by = UnsafeOpsHolder.U.getFloat(b + 4L);
        float _bz = UnsafeOpsHolder.U.getFloat(b + 8L);
        float _bw = UnsafeOpsHolder.U.getFloat(b + 12L);
        float _cx = UnsafeOpsHolder.U.getFloat(c);
        float _cy = UnsafeOpsHolder.U.getFloat(c + 4L);
        float _cz = UnsafeOpsHolder.U.getFloat(c + 8L);
        float _cw = UnsafeOpsHolder.U.getFloat(c + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((_bx) * (_cx) + (_selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_by) * (_cy) + (_selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_bz) * (_cz) + (_selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_bw) * (_cw) + (_selfw)));
        return dest;
    }

    public static float angleBetween_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_unsafe_fma(src, otherX, otherY, otherZ, otherW);
        return angleBetween_unsafe_mulAdd(src, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_unsafe_fma(long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = otherZ * -_selfw;
        float _t7 = otherY * -_selfw;
        float _t8 = -(otherY * _selfz);
        float _t9 = -otherX * _selfw;
        float _t10 = -otherX * _selfy;
        float _t11 = -otherX * _selfz;
        float _t24 = java.lang.Math.fma(otherW, _selfz, _t6) - java.lang.Math.fma(otherZ, _selfw, _t6);
        float _t25 = java.lang.Math.fma(otherW, _selfy, _t7) - java.lang.Math.fma(otherY, _selfw, _t7);
        float _t26 = java.lang.Math.fma(otherZ, _selfy, _t8) - java.lang.Math.fma(otherY, _selfz, _t8);
        float _t27 = java.lang.Math.fma(otherW, _selfx, _t9) - java.lang.Math.fma(otherX, _selfw, _t9);
        float _t28 = java.lang.Math.fma(otherY, _selfx, _t10) - java.lang.Math.fma(otherX, _selfy, _t10);
        float _t29 = java.lang.Math.fma(otherZ, _selfx, _t11) - java.lang.Math.fma(otherX, _selfz, _t11);
        return angleBetween_unsafe_fma_sfeb7e238_1(src, otherX, otherY, otherZ, otherW, _selfx, _selfy, _selfz, _selfw, java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29))))));
    }

    /** Piece 2 of {@code angleBetween_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_unsafe_fma_sfeb7e238_1(long src, float otherX, float otherY, float otherZ, float otherW, float _selfx, float _selfy, float _selfz, float _selfw, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherW, _selfw, java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    /** {@code angleBetween_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_unsafe_mulAdd(long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = otherZ * -_selfw;
        float _t7 = otherY * -_selfw;
        float _t8 = -(otherY * _selfz);
        float _t9 = -otherX * _selfw;
        float _t10 = -otherX * _selfy;
        float _t11 = -otherX * _selfz;
        float _t24 = ((otherW) * (_selfz) + (_t6)) - ((otherZ) * (_selfw) + (_t6));
        float _t25 = ((otherW) * (_selfy) + (_t7)) - ((otherY) * (_selfw) + (_t7));
        float _t26 = ((otherZ) * (_selfy) + (_t8)) - ((otherY) * (_selfz) + (_t8));
        float _t27 = ((otherW) * (_selfx) + (_t9)) - ((otherX) * (_selfw) + (_t9));
        float _t28 = ((otherY) * (_selfx) + (_t10)) - ((otherX) * (_selfy) + (_t10));
        float _t29 = ((otherZ) * (_selfx) + (_t11)) - ((otherX) * (_selfz) + (_t11));
        float _ct0 = ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))))))))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.angleBetween_degenerate(src, otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((otherW) * (_selfw) + (((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))))));
    }

    public static float angleBetween_degenerate(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(src, otherX, otherY, otherZ, otherW);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static float angleBetween_degenerate_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_unsafe_fma(src, otherX, otherY, otherZ, otherW);
        return angleBetween_degenerate_unsafe_mulAdd(src, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_unsafe_fma(long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        float _t34 = -(_t20 * _t17);
        return angleBetween_degenerate_unsafe_fma_s9518f531_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32), java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33), java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34));
    }

    /** Piece 2 of {@code angleBetween_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_unsafe_fma_s9518f531_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t35, float _t48, float _t49, float _t50, float _t51, float _t52) {
        float _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** {@code angleBetween_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_unsafe_mulAdd(long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        float _t34 = -(_t20 * _t17);
        return angleBetween_degenerate_unsafe_mulAdd_sd6edd6bc_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t20 * -_t19, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32)), ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33)), ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_unsafe_mulAdd_sd6edd6bc_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t35, float _t48, float _t49, float _t50, float _t51, float _t52) {
        float _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static float angleBetween_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_unsafe_fma(src, other);
        return angleBetween_unsafe_mulAdd(src, other);
    }

    /** {@code angleBetween_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_unsafe_fma(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t6 = _otherz * -_selfw;
        float _t7 = _othery * -_selfw;
        float _t8 = -(_othery * _selfz);
        float _t9 = -_otherx * _selfw;
        float _t10 = -_otherx * _selfy;
        float _t11 = -_otherx * _selfz;
        return angleBetween_unsafe_fma_sfa0bc3c0_1(src, other, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, java.lang.Math.fma(_otherw, _selfz, _t6) - java.lang.Math.fma(_otherz, _selfw, _t6), java.lang.Math.fma(_otherw, _selfy, _t7) - java.lang.Math.fma(_othery, _selfw, _t7), java.lang.Math.fma(_otherz, _selfy, _t8) - java.lang.Math.fma(_othery, _selfz, _t8), java.lang.Math.fma(_otherw, _selfx, _t9) - java.lang.Math.fma(_otherx, _selfw, _t9), java.lang.Math.fma(_othery, _selfx, _t10) - java.lang.Math.fma(_otherx, _selfy, _t10), java.lang.Math.fma(_otherz, _selfx, _t11) - java.lang.Math.fma(_otherx, _selfz, _t11));
    }

    /** Piece 2 of {@code angleBetween_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_unsafe_fma_sfa0bc3c0_1(long src, long other, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        float _ct0 = java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.angleBetween_degenerate(src, other);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherw, _selfw, java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    /** {@code angleBetween_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_unsafe_mulAdd(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t6 = _otherz * -_selfw;
        float _t7 = _othery * -_selfw;
        float _t8 = -(_othery * _selfz);
        float _t9 = -_otherx * _selfw;
        float _t10 = -_otherx * _selfy;
        float _t11 = -_otherx * _selfz;
        return angleBetween_unsafe_mulAdd_s87ad6dbb_1(src, other, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, ((_otherw) * (_selfz) + (_t6)) - ((_otherz) * (_selfw) + (_t6)), ((_otherw) * (_selfy) + (_t7)) - ((_othery) * (_selfw) + (_t7)), ((_otherz) * (_selfy) + (_t8)) - ((_othery) * (_selfz) + (_t8)), ((_otherw) * (_selfx) + (_t9)) - ((_otherx) * (_selfw) + (_t9)), ((_othery) * (_selfx) + (_t10)) - ((_otherx) * (_selfy) + (_t10)), ((_otherz) * (_selfx) + (_t11)) - ((_otherx) * (_selfz) + (_t11)));
    }

    /** Piece 2 of {@code angleBetween_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_unsafe_mulAdd_s87ad6dbb_1(long src, long other, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        float _ct0 = ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))))))))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsAddress.angleBetween_degenerate(src, other);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((_otherw) * (_selfw) + (((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))))));
    }

    public static float angleBetween_degenerate(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(src, other);
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
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        return angleBetween_degenerate_unsafe_fma_s1fa64b4d_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -_t22 * _t17, _t22 * -_t19, -(_t20 * _t17), _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31));
    }

    /** Piece 2 of {@code angleBetween_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_unsafe_fma_s1fa64b4d_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t32, float _t33, float _t34, float _t35, float _t48, float _t49) {
        float _t50 = java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32);
        float _t51 = java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33);
        float _t52 = java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34);
        float _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** {@code angleBetween_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_unsafe_mulAdd(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        return angleBetween_degenerate_unsafe_mulAdd_sb73b015c_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -_t22 * _t17, _t22 * -_t19, -(_t20 * _t17), _t20 * -_t19, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_unsafe_mulAdd_sb73b015c_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t32, float _t33, float _t34, float _t35, float _t48, float _t49) {
        float _t50 = ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32));
        float _t51 = ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33));
        float _t52 = ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34));
        float _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static long asin_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.asin(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.asin(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.asin(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.asin(_selfw));
        return dest;
    }

    public static long atan_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.atan(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.atan(_selfw));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, float x) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_selfx, x));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_selfy, x));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_selfz, x));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.atan2(_selfw, x));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, float xX, float xY, float xZ, float xW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_selfx, xX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_selfy, xY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_selfz, xZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.atan2(_selfw, xW));
        return dest;
    }

    public static long atan2_unsafe(long dest, long src, long x) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _xx = UnsafeOpsHolder.U.getFloat(x);
        float _xy = UnsafeOpsHolder.U.getFloat(x + 4L);
        float _xz = UnsafeOpsHolder.U.getFloat(x + 8L);
        float _xw = UnsafeOpsHolder.U.getFloat(x + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_selfx, _xx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_selfy, _xy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_selfz, _xz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.atan2(_selfw, _xw));
        return dest;
    }

    public static long cbrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.cbrt(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.cbrt(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.cbrt(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cbrt(_selfw));
        return dest;
    }

    public static long ceil_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.ceil(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.ceil(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.ceil(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.ceil(_selfw));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, float min, float max) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(Math.max(_selfx, min), max));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, min), max));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(Math.max(_selfz, min), max));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(Math.max(_selfw, min), max));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(Math.max(_selfx, minX), maxX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, minY), maxY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(Math.max(_selfz, minZ), maxZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(Math.max(_selfw, minW), maxW));
        return dest;
    }

    public static long clamp_unsafe(long dest, long src, long min, long max) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _minx = UnsafeOpsHolder.U.getFloat(min);
        float _miny = UnsafeOpsHolder.U.getFloat(min + 4L);
        float _minz = UnsafeOpsHolder.U.getFloat(min + 8L);
        float _minw = UnsafeOpsHolder.U.getFloat(min + 12L);
        float _maxx = UnsafeOpsHolder.U.getFloat(max);
        float _maxy = UnsafeOpsHolder.U.getFloat(max + 4L);
        float _maxz = UnsafeOpsHolder.U.getFloat(max + 8L);
        float _maxw = UnsafeOpsHolder.U.getFloat(max + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(Math.max(_selfx, _minx), _maxx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(Math.max(_selfy, _miny), _maxy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(Math.max(_selfz, _minz), _maxz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static float compAdd_unsafe(long src) {
        return UnsafeOpsHolder.U.getFloat(src + 12L) + (UnsafeOpsHolder.U.getFloat(src + 8L) + (UnsafeOpsHolder.U.getFloat(src) + UnsafeOpsHolder.U.getFloat(src + 4L)));
    }

    public static float compMax_unsafe(long src) {
        return Math.max(Math.max(Math.max(UnsafeOpsHolder.U.getFloat(src), UnsafeOpsHolder.U.getFloat(src + 4L)), UnsafeOpsHolder.U.getFloat(src + 8L)), UnsafeOpsHolder.U.getFloat(src + 12L));
    }

    public static float compMin_unsafe(long src) {
        return Math.min(Math.min(Math.min(UnsafeOpsHolder.U.getFloat(src), UnsafeOpsHolder.U.getFloat(src + 4L)), UnsafeOpsHolder.U.getFloat(src + 8L)), UnsafeOpsHolder.U.getFloat(src + 12L));
    }

    public static float compMul_unsafe(long src) {
        return UnsafeOpsHolder.U.getFloat(src + 12L) * UnsafeOpsHolder.U.getFloat(src + 8L) * UnsafeOpsHolder.U.getFloat(src) * UnsafeOpsHolder.U.getFloat(src + 4L);
    }

    public static long copySign_unsafe(long dest, long src, float sign) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.copySign(_selfx, sign));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, sign));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.copySign(_selfz, sign));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.copySign(_selfw, sign));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, float signX, float signY, float signZ, float signW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.copySign(_selfx, signX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, signY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.copySign(_selfz, signZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.copySign(_selfw, signW));
        return dest;
    }

    public static long copySign_unsafe(long dest, long src, long sign) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _signx = UnsafeOpsHolder.U.getFloat(sign);
        float _signy = UnsafeOpsHolder.U.getFloat(sign + 4L);
        float _signz = UnsafeOpsHolder.U.getFloat(sign + 8L);
        float _signw = UnsafeOpsHolder.U.getFloat(sign + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.copySign(_selfx, _signx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.copySign(_selfy, _signy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.copySign(_selfz, _signz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.copySign(_selfw, _signw));
        return dest;
    }

    public static long cos_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.cos(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.cos(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.cos(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cos(_selfw));
        return dest;
    }

    public static long cosh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.cosh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.cosh(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.cosh(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosh(_selfw));
        return dest;
    }

    public static long cross_unsafe(long dest, long src, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cross_unsafe_fma(dest, src, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return cross_unsafe_mulAdd(dest, src, vX, vY, vZ, vW, wX, wY, wZ, wW);
    }

    /** {@code cross_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cross_unsafe_fma(long dest, long src, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = -vZ * wY;
        float _t7 = -vW * wZ;
        float _t8 = -vW * wY;
        float _t9 = -vZ * wX;
        float _t10 = -vW * wX;
        float _t11 = -(vY * wX);
        float _t24 = java.lang.Math.fma(vY, wZ, _t6) - java.lang.Math.fma(vZ, wY, _t6);
        float _t25 = java.lang.Math.fma(vZ, wW, _t7) - java.lang.Math.fma(vW, wZ, _t7);
        float _t26 = java.lang.Math.fma(vY, wW, _t8) - java.lang.Math.fma(vW, wY, _t8);
        float _t27 = java.lang.Math.fma(vX, wZ, _t9) - java.lang.Math.fma(vZ, wX, _t9);
        float _t28 = java.lang.Math.fma(vX, wW, _t10) - java.lang.Math.fma(vW, wX, _t10);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfw, _t24, java.lang.Math.fma(_selfy, _t25, -_selfz * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-_selfw, _t27, java.lang.Math.fma(_selfz, _t28, -_selfx * _t25)));
        return cross_unsafe_fma_sa8ccb45b_1(dest, _selfx, _selfy, _selfz, _selfw, _t24, _t26, _t27, _t28, java.lang.Math.fma(vX, wY, _t11) - java.lang.Math.fma(vY, wX, _t11));
    }

    /** Piece 2 of {@code cross_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long cross_unsafe_fma_sa8ccb45b_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t24, float _t26, float _t27, float _t28, float _t29) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfw, _t29, java.lang.Math.fma(_selfx, _t26, -(_selfy * _t28))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(-_selfz, _t29, java.lang.Math.fma(_selfy, _t27, -_selfx * _t24)));
        return dest;
    }

    /** {@code cross_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cross_unsafe_mulAdd(long dest, long src, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = -vZ * wY;
        float _t7 = -vW * wZ;
        float _t8 = -vW * wY;
        float _t9 = -vZ * wX;
        float _t10 = -vW * wX;
        float _t11 = -(vY * wX);
        float _t24 = ((vY) * (wZ) + (_t6)) - ((vZ) * (wY) + (_t6));
        float _t25 = ((vZ) * (wW) + (_t7)) - ((vW) * (wZ) + (_t7));
        float _t26 = ((vY) * (wW) + (_t8)) - ((vW) * (wY) + (_t8));
        float _t27 = ((vX) * (wZ) + (_t9)) - ((vZ) * (wX) + (_t9));
        float _t28 = ((vX) * (wW) + (_t10)) - ((vW) * (wX) + (_t10));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfw) * (_t24) + (((_selfy) * (_t25) - (_selfz * _t26)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((((_selfz) * (_t28) - (_selfx * _t25))) - (_selfw) * (_t27)));
        return cross_unsafe_mulAdd_sdb8cfacc_1(dest, _selfx, _selfy, _selfz, _selfw, _t24, _t26, _t27, _t28, ((vX) * (wY) + (_t11)) - ((vY) * (wX) + (_t11)));
    }

    /** Piece 2 of {@code cross_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long cross_unsafe_mulAdd_sdb8cfacc_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t24, float _t26, float _t27, float _t28, float _t29) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfw) * (_t29) + (((_selfx) * (_t26) - (_selfy * _t28)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((((_selfy) * (_t27) - (_selfx * _t24))) - (_selfz) * (_t29)));
        return dest;
    }

    public static long cross_unsafe(long dest, long src, long v, long w) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cross_unsafe_fma(dest, src, v, w);
        return cross_unsafe_mulAdd(dest, src, v, w);
    }

    /** {@code cross_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cross_unsafe_fma(long dest, long src, long v, long w) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _vw = UnsafeOpsHolder.U.getFloat(v + 12L);
        float _wx = UnsafeOpsHolder.U.getFloat(w);
        float _wy = UnsafeOpsHolder.U.getFloat(w + 4L);
        float _wz = UnsafeOpsHolder.U.getFloat(w + 8L);
        float _ww = UnsafeOpsHolder.U.getFloat(w + 12L);
        float _t6 = -_vz * _wy;
        float _t7 = -_vw * _wz;
        float _t8 = -_vw * _wy;
        float _t9 = -_vz * _wx;
        return cross_unsafe_fma_s7a3d5584_1(dest, _selfx, _selfy, _selfz, _selfw, _vx, _vy, _vw, _wx, _wy, _ww, -_vw * _wx, -(_vy * _wx), java.lang.Math.fma(_vy, _wz, _t6) - java.lang.Math.fma(_vz, _wy, _t6), java.lang.Math.fma(_vz, _ww, _t7) - java.lang.Math.fma(_vw, _wz, _t7), java.lang.Math.fma(_vy, _ww, _t8) - java.lang.Math.fma(_vw, _wy, _t8), java.lang.Math.fma(_vx, _wz, _t9) - java.lang.Math.fma(_vz, _wx, _t9));
    }

    /** Piece 2 of {@code cross_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long cross_unsafe_fma_s7a3d5584_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _vx, float _vy, float _vw, float _wx, float _wy, float _ww, float _t10, float _t11, float _t24, float _t25, float _t26, float _t27) {
        float _t28 = java.lang.Math.fma(_vx, _ww, _t10) - java.lang.Math.fma(_vw, _wx, _t10);
        float _t29 = java.lang.Math.fma(_vx, _wy, _t11) - java.lang.Math.fma(_vy, _wx, _t11);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfw, _t24, java.lang.Math.fma(_selfy, _t25, -_selfz * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-_selfw, _t27, java.lang.Math.fma(_selfz, _t28, -_selfx * _t25)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfw, _t29, java.lang.Math.fma(_selfx, _t26, -(_selfy * _t28))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(-_selfz, _t29, java.lang.Math.fma(_selfy, _t27, -_selfx * _t24)));
        return dest;
    }

    /** {@code cross_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long cross_unsafe_mulAdd(long dest, long src, long v, long w) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _vw = UnsafeOpsHolder.U.getFloat(v + 12L);
        float _wx = UnsafeOpsHolder.U.getFloat(w);
        float _wy = UnsafeOpsHolder.U.getFloat(w + 4L);
        float _wz = UnsafeOpsHolder.U.getFloat(w + 8L);
        float _ww = UnsafeOpsHolder.U.getFloat(w + 12L);
        float _t6 = -_vz * _wy;
        float _t7 = -_vw * _wz;
        float _t8 = -_vw * _wy;
        float _t9 = -_vz * _wx;
        float _t10 = -_vw * _wx;
        return cross_unsafe_mulAdd_seb635173_1(dest, _selfx, _selfy, _selfz, _selfw, _vx, _vy, _wx, _wy, -(_vy * _wx), ((_vy) * (_wz) + (_t6)) - ((_vz) * (_wy) + (_t6)), ((_vz) * (_ww) + (_t7)) - ((_vw) * (_wz) + (_t7)), ((_vy) * (_ww) + (_t8)) - ((_vw) * (_wy) + (_t8)), ((_vx) * (_wz) + (_t9)) - ((_vz) * (_wx) + (_t9)), ((_vx) * (_ww) + (_t10)) - ((_vw) * (_wx) + (_t10)));
    }

    /** Piece 2 of {@code cross_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long cross_unsafe_mulAdd_seb635173_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _vx, float _vy, float _wx, float _wy, float _t11, float _t24, float _t25, float _t26, float _t27, float _t28) {
        float _t29 = ((_vx) * (_wy) + (_t11)) - ((_vy) * (_wx) + (_t11));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfw) * (_t24) + (((_selfy) * (_t25) - (_selfz * _t26)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((((_selfz) * (_t28) - (_selfx * _t25))) - (_selfw) * (_t27)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfw) * (_t29) + (((_selfx) * (_t26) - (_selfy * _t28)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((((_selfy) * (_t27) - (_selfx * _t24))) - (_selfz) * (_t29)));
        return dest;
    }

    public static long degrees_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.toDegrees(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.toDegrees(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.toDegrees(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.toDegrees(_selfw));
        return dest;
    }

    public static float distance_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 12L) - otherW;
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 8L) - otherZ;
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - otherX;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - otherY;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
        } else {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 12L) - otherW;
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 8L) - otherZ;
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - otherX;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - otherY;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
        }
    }

    public static float distance_unsafe(long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return distance_unsafe_mulAdd(src, other);
        float _t0 = UnsafeOpsHolder.U.getFloat(src + 12L) - UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t1 = UnsafeOpsHolder.U.getFloat(src + 8L) - UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t2 = UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other);
        float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L);
        return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
    }

    /** {@code distance_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float distance_unsafe_mulAdd(long src, long other) {
        float _t0 = UnsafeOpsHolder.U.getFloat(src + 12L) - UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t1 = UnsafeOpsHolder.U.getFloat(src + 8L) - UnsafeOpsHolder.U.getFloat(other + 8L);
        float _t2 = UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other);
        float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L);
        return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
    }

    public static float distanceSquared_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 12L) - otherW;
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 8L) - otherZ;
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - otherX;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 12L) - otherW;
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 8L) - otherZ;
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - otherX;
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
        }
    }

    public static float distanceSquared_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 12L) - UnsafeOpsHolder.U.getFloat(other + 12L);
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 8L) - UnsafeOpsHolder.U.getFloat(other + 8L);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other);
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L);
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            float _t0 = UnsafeOpsHolder.U.getFloat(src + 12L) - UnsafeOpsHolder.U.getFloat(other + 12L);
            float _t1 = UnsafeOpsHolder.U.getFloat(src + 8L) - UnsafeOpsHolder.U.getFloat(other + 8L);
            float _t2 = UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other);
            float _t3 = UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L);
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
        }
    }

    public static float dot_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherW, UnsafeOpsHolder.U.getFloat(src + 12L), java.lang.Math.fma(otherZ, UnsafeOpsHolder.U.getFloat(src + 8L), java.lang.Math.fma(otherX, UnsafeOpsHolder.U.getFloat(src), otherY * UnsafeOpsHolder.U.getFloat(src + 4L))));
        } else {
            return ((otherW) * (UnsafeOpsHolder.U.getFloat(src + 12L)) + (((otherZ) * (UnsafeOpsHolder.U.getFloat(src + 8L)) + (((otherX) * (UnsafeOpsHolder.U.getFloat(src)) + (otherY * UnsafeOpsHolder.U.getFloat(src + 4L)))))));
        }
    }

    public static float dot_unsafe(long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(other + 12L), UnsafeOpsHolder.U.getFloat(src + 12L), java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(other + 8L), UnsafeOpsHolder.U.getFloat(src + 8L), java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(other), UnsafeOpsHolder.U.getFloat(src), UnsafeOpsHolder.U.getFloat(other + 4L) * UnsafeOpsHolder.U.getFloat(src + 4L))));
        } else {
            return ((UnsafeOpsHolder.U.getFloat(other + 12L)) * (UnsafeOpsHolder.U.getFloat(src + 12L)) + (((UnsafeOpsHolder.U.getFloat(other + 8L)) * (UnsafeOpsHolder.U.getFloat(src + 8L)) + (((UnsafeOpsHolder.U.getFloat(other)) * (UnsafeOpsHolder.U.getFloat(src)) + (UnsafeOpsHolder.U.getFloat(other + 4L) * UnsafeOpsHolder.U.getFloat(src + 4L)))))));
        }
    }

    public static long exp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.exp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.exp(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.exp(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.exp(_selfw));
        return dest;
    }

    public static long exp2_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.pow(2.0f, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.pow(2.0f, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.pow(2.0f, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.pow(2.0f, _selfw));
        return dest;
    }

    public static long expm1_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.expm1(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.expm1(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.expm1(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.expm1(_selfw));
        return dest;
    }

    public static long faceforward_unsafe(long dest, long src, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t4);
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0f ? 1.0f : -1.0f;
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t4);
            return dest;
        }
    }

    public static long faceforward_unsafe(long dest, long src, long I, long Nref) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_unsafe_mulAdd(dest, src, I, Nref);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(I + 12L), UnsafeOpsHolder.U.getFloat(Nref + 12L), java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(I + 8L), UnsafeOpsHolder.U.getFloat(Nref + 8L), java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(I), UnsafeOpsHolder.U.getFloat(Nref), UnsafeOpsHolder.U.getFloat(I + 4L) * UnsafeOpsHolder.U.getFloat(Nref + 4L)))) < 0.0f ? 1.0f : -1.0f;
        UnsafeOpsHolder.U.putFloat(dest, _selfx * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t4);
        return dest;
    }

    /** {@code faceforward_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long faceforward_unsafe_mulAdd(long dest, long src, long I, long Nref) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = ((UnsafeOpsHolder.U.getFloat(I + 12L)) * (UnsafeOpsHolder.U.getFloat(Nref + 12L)) + (((UnsafeOpsHolder.U.getFloat(I + 8L)) * (UnsafeOpsHolder.U.getFloat(Nref + 8L)) + (((UnsafeOpsHolder.U.getFloat(I)) * (UnsafeOpsHolder.U.getFloat(Nref)) + (UnsafeOpsHolder.U.getFloat(I + 4L) * UnsafeOpsHolder.U.getFloat(Nref + 4L))))))) < 0.0f ? 1.0f : -1.0f;
        UnsafeOpsHolder.U.putFloat(dest, _selfx * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t4);
        return dest;
    }

    public static long floor_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.floor(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.floor(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.floor(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.floor(_selfw));
        return dest;
    }

    public static long fract_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(_selfx - Math.floor(_selfx), 0.99999994f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy - Math.floor(_selfy), 0.99999994f));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(_selfz - Math.floor(_selfz), 0.99999994f));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(_selfw - Math.floor(_selfw), 0.99999994f));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, float y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.hypot(_selfx, y));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.hypot(_selfy, y));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.hypot(_selfz, y));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.hypot(_selfw, y));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, float yX, float yY, float yZ, float yW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.hypot(_selfx, yX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.hypot(_selfy, yY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.hypot(_selfz, yZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.hypot(_selfw, yW));
        return dest;
    }

    public static long hypot_unsafe(long dest, long src, long y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _yx = UnsafeOpsHolder.U.getFloat(y);
        float _yy = UnsafeOpsHolder.U.getFloat(y + 4L);
        float _yz = UnsafeOpsHolder.U.getFloat(y + 8L);
        float _yw = UnsafeOpsHolder.U.getFloat(y + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.hypot(_selfx, _yx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.hypot(_selfy, _yy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.hypot(_selfz, _yz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.hypot(_selfw, _yw));
        return dest;
    }

    public static long inverse_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, 1.0f / _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 1.0f / _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 1.0f / _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f / _selfw);
        return dest;
    }

    public static long inverseSqrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, (1.0f / (float) java.lang.Math.sqrt(_selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (1.0f / (float) java.lang.Math.sqrt(_selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (1.0f / (float) java.lang.Math.sqrt(_selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (1.0f / (float) java.lang.Math.sqrt(_selfw)));
        return dest;
    }

    public static float length_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))));
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            return (float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))))));
        }
    }

    public static float lengthSquared_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            return java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            return ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        }
    }

    public static long log_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.log(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.log(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.log(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.log(_selfw));
        return dest;
    }

    public static long log10_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.log10(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.log10(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.log10(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.log10(_selfw));
        return dest;
    }

    public static long log1p_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.log1p(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.log1p(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.log1p(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.log1p(_selfw));
        return dest;
    }

    public static long log2_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.log2(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.log2(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.log2(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.log2(_selfw));
        return dest;
    }

    public static float manhattanDistance_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src) - otherX) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 4L) - otherY) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 8L) - otherZ) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 12L) - otherW);
    }

    public static float manhattanDistance_unsafe(long src, long other) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src) - UnsafeOpsHolder.U.getFloat(other)) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 4L) - UnsafeOpsHolder.U.getFloat(other + 4L)) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 8L) - UnsafeOpsHolder.U.getFloat(other + 8L)) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 12L) - UnsafeOpsHolder.U.getFloat(other + 12L));
    }

    public static float manhattanLength_unsafe(long src) {
        return java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src)) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 4L)) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 8L)) + java.lang.Math.abs(UnsafeOpsHolder.U.getFloat(src + 12L));
    }

    public static long max_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.max(_selfx, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.max(_selfz, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.max(_selfw, scalar));
        return dest;
    }

    public static long max_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.max(_selfx, otherX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, otherY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.max(_selfz, otherZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.max(_selfw, otherW));
        return dest;
    }

    public static long max_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.max(_selfx, _otherx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.max(_selfy, _othery));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.max(_selfz, _otherz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.max(_selfw, _otherw));
        return dest;
    }

    public static long min_unsafe(long dest, long src, float scalar) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(_selfx, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(_selfz, scalar));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(_selfw, scalar));
        return dest;
    }

    public static long min_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(_selfx, otherX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, otherY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(_selfz, otherZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(_selfw, otherW));
        return dest;
    }

    public static long min_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.min(_selfx, _otherx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.min(_selfy, _othery));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.min(_selfz, _otherz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.min(_selfw, _otherw));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, float y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, flooredMod(_selfx, y));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, y));
        UnsafeOpsHolder.U.putFloat(dest + 8L, flooredMod(_selfz, y));
        UnsafeOpsHolder.U.putFloat(dest + 12L, flooredMod(_selfw, y));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, float yX, float yY, float yZ, float yW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, flooredMod(_selfx, yX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, yY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, flooredMod(_selfz, yZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, flooredMod(_selfw, yW));
        return dest;
    }

    public static long mod_unsafe(long dest, long src, long y) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _yx = UnsafeOpsHolder.U.getFloat(y);
        float _yy = UnsafeOpsHolder.U.getFloat(y + 4L);
        float _yz = UnsafeOpsHolder.U.getFloat(y + 8L);
        float _yw = UnsafeOpsHolder.U.getFloat(y + 12L);
        UnsafeOpsHolder.U.putFloat(dest, flooredMod(_selfx, _yx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, flooredMod(_selfy, _yy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, flooredMod(_selfz, _yz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, flooredMod(_selfw, _yw));
        return dest;
    }

    public static long nextDown_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.nextDown(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.nextDown(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.nextDown(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.nextDown(_selfw));
        return dest;
    }

    public static long nextUp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.nextUp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.nextUp(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.nextUp(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.nextUp(_selfw));
        return dest;
    }

    public static long normalize_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalize_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t4);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code normalize_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normalize_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t4);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t4);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long normalizeMul_unsafe(long dest, long src, float length) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizeMul_unsafe_mulAdd(dest, src, length);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t5);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code normalizeMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normalizeMul_unsafe_mulAdd(long dest, long src, float length) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t5);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t5);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long outerProduct_unsafe(long dest, long src, float rowX, float rowY, float rowZ, float rowW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, rowX * _selfx);
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _rowx = UnsafeOpsHolder.U.getFloat(row);
        float _rowy = UnsafeOpsHolder.U.getFloat(row + 4L);
        float _rowz = UnsafeOpsHolder.U.getFloat(row + 8L);
        float _roww = UnsafeOpsHolder.U.getFloat(row + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _rowx * _selfx);
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
        return outerProduct_unsafe_s12171ae2_1(dest, _selfx, _selfy, _selfz, _selfw, _roww);
    }

    /** Piece 2 of {@code outerProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static long outerProduct_unsafe_s12171ae2_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _roww) {
        UnsafeOpsHolder.U.putFloat(dest + 48L, _roww * _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 52L, _roww * _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 56L, _roww * _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 60L, _roww * _selfw);
        return dest;
    }

    public static long pow_unsafe(long dest, long src, float exponent) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.pow(_selfx, exponent));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.pow(_selfy, exponent));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.pow(_selfz, exponent));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.pow(_selfw, exponent));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, float exponentX, float exponentY, float exponentZ, float exponentW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.pow(_selfx, exponentX));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.pow(_selfy, exponentY));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.pow(_selfz, exponentZ));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.pow(_selfw, exponentW));
        return dest;
    }

    public static long pow_unsafe(long dest, long src, long exponent) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _exponentx = UnsafeOpsHolder.U.getFloat(exponent);
        float _exponenty = UnsafeOpsHolder.U.getFloat(exponent + 4L);
        float _exponentz = UnsafeOpsHolder.U.getFloat(exponent + 8L);
        float _exponentw = UnsafeOpsHolder.U.getFloat(exponent + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.pow(_selfx, _exponentx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.pow(_selfy, _exponenty));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.pow(_selfz, _exponentz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.pow(_selfw, _exponentw));
        return dest;
    }

    public static long project_unsafe(long dest, long src, float ontoX, float ontoY, float ontoZ, float ontoW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_unsafe_mulAdd(dest, src, ontoX, ontoY, ontoZ, ontoW);
        float _t9 = java.lang.Math.fma(ontoW, UnsafeOpsHolder.U.getFloat(src + 12L), java.lang.Math.fma(ontoZ, UnsafeOpsHolder.U.getFloat(src + 8L), java.lang.Math.fma(ontoX, UnsafeOpsHolder.U.getFloat(src), ontoY * UnsafeOpsHolder.U.getFloat(src + 4L)))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
        UnsafeOpsHolder.U.putFloat(dest, ontoX * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ontoY * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ontoZ * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ontoW * _t9);
        return dest;
    }

    /** {@code project_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long project_unsafe_mulAdd(long dest, long src, float ontoX, float ontoY, float ontoZ, float ontoW) {
        float _t9 = ((ontoW) * (UnsafeOpsHolder.U.getFloat(src + 12L)) + (((ontoZ) * (UnsafeOpsHolder.U.getFloat(src + 8L)) + (((ontoX) * (UnsafeOpsHolder.U.getFloat(src)) + (ontoY * UnsafeOpsHolder.U.getFloat(src + 4L))))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
        UnsafeOpsHolder.U.putFloat(dest, ontoX * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ontoY * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ontoZ * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ontoW * _t9);
        return dest;
    }

    public static long project_unsafe(long dest, long src, long onto) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_unsafe_mulAdd(dest, src, onto);
        float _ontox = UnsafeOpsHolder.U.getFloat(onto);
        float _ontoy = UnsafeOpsHolder.U.getFloat(onto + 4L);
        float _ontoz = UnsafeOpsHolder.U.getFloat(onto + 8L);
        float _ontow = UnsafeOpsHolder.U.getFloat(onto + 12L);
        float _t9 = java.lang.Math.fma(_ontow, UnsafeOpsHolder.U.getFloat(src + 12L), java.lang.Math.fma(_ontoz, UnsafeOpsHolder.U.getFloat(src + 8L), java.lang.Math.fma(_ontox, UnsafeOpsHolder.U.getFloat(src), _ontoy * UnsafeOpsHolder.U.getFloat(src + 4L)))) / java.lang.Math.fma(_ontow, _ontow, java.lang.Math.fma(_ontoz, _ontoz, java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        UnsafeOpsHolder.U.putFloat(dest, _ontox * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _ontoy * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _ontoz * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _ontow * _t9);
        return dest;
    }

    /** {@code project_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long project_unsafe_mulAdd(long dest, long src, long onto) {
        float _ontox = UnsafeOpsHolder.U.getFloat(onto);
        float _ontoy = UnsafeOpsHolder.U.getFloat(onto + 4L);
        float _ontoz = UnsafeOpsHolder.U.getFloat(onto + 8L);
        float _ontow = UnsafeOpsHolder.U.getFloat(onto + 12L);
        float _t9 = ((_ontow) * (UnsafeOpsHolder.U.getFloat(src + 12L)) + (((_ontoz) * (UnsafeOpsHolder.U.getFloat(src + 8L)) + (((_ontox) * (UnsafeOpsHolder.U.getFloat(src)) + (_ontoy * UnsafeOpsHolder.U.getFloat(src + 4L))))))) / ((_ontow) * (_ontow) + (((_ontoz) * (_ontoz) + (((_ontox) * (_ontox) + (_ontoy * _ontoy))))));
        UnsafeOpsHolder.U.putFloat(dest, _ontox * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _ontoy * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _ontoz * _t9);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _ontow * _t9);
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, float normalX, float normalY, float normalZ, float normalW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return projectOnPlane_unsafe_mulAdd(dest, src, normalX, normalY, normalZ, normalW);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-normalX, _t3, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-normalY, _t3, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-normalZ, _t3, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(-normalW, _t3, _selfw));
        return dest;
    }

    /** {@code projectOnPlane_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long projectOnPlane_unsafe_mulAdd(long dest, long src, float normalX, float normalY, float normalZ, float normalW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) - (normalX) * (_t3)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) - (normalY) * (_t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) - (normalZ) * (_t3)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) - (normalW) * (_t3)));
        return dest;
    }

    public static long projectOnPlane_unsafe(long dest, long src, long normal) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return projectOnPlane_unsafe_mulAdd(dest, src, normal);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _normalw = UnsafeOpsHolder.U.getFloat(normal + 12L);
        float _t3 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-_normalx, _t3, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-_normaly, _t3, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-_normalz, _t3, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    /** {@code projectOnPlane_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long projectOnPlane_unsafe_mulAdd(long dest, long src, long normal) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _normalw = UnsafeOpsHolder.U.getFloat(normal + 12L);
        float _t3 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) - (_normalx) * (_t3)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) - (_normaly) * (_t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) - (_normalz) * (_t3)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) - (_normalw) * (_t3)));
        return dest;
    }

    public static long radians_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.toRadians(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.toRadians(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.toRadians(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.toRadians(_selfw));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, float normalX, float normalY, float normalZ, float normalW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_unsafe_mulAdd(dest, src, normalX, normalY, normalZ, normalW);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = 2.0f * java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-normalX, _t4, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-normalY, _t4, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-normalZ, _t4, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(-normalW, _t4, _selfw));
        return dest;
    }

    /** {@code reflect_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long reflect_unsafe_mulAdd(long dest, long src, float normalX, float normalY, float normalZ, float normalW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = 2.0f * ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) - (normalX) * (_t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) - (normalY) * (_t4)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) - (normalZ) * (_t4)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) - (normalW) * (_t4)));
        return dest;
    }

    public static long reflect_unsafe(long dest, long src, long normal) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_unsafe_mulAdd(dest, src, normal);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _normalw = UnsafeOpsHolder.U.getFloat(normal + 12L);
        float _t4 = 2.0f * java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-_normalx, _t4, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-_normaly, _t4, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-_normalz, _t4, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    /** {@code reflect_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long reflect_unsafe_mulAdd(long dest, long src, long normal) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _normalw = UnsafeOpsHolder.U.getFloat(normal + 12L);
        float _t4 = 2.0f * ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) - (_normalx) * (_t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) - (_normaly) * (_t4)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) - (_normalz) * (_t4)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) - (_normalw) * (_t4)));
        return dest;
    }

    public static long refract_unsafe(long dest, long src, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_unsafe_mulAdd(dest, src, normalX, normalY, normalZ, normalW, eta);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        float _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0f), eta * eta, 1.0f);
        float _t11 = java.lang.Math.fma(eta, _t4, (float) java.lang.Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(eta, _selfx, -(normalX * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(eta, _selfy, -(normalY * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(eta, _selfz, -(normalZ * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code refract_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long refract_unsafe_mulAdd(long dest, long src, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        float _t8 = ((((_t4) * (_t4) - (1.0f))) * (eta * eta) + (1.0f));
        float _t11 = ((eta) * (_t4) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t8))));
        if (_t8 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, ((eta) * (_selfx) - (normalX * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((eta) * (_selfy) - (normalY * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((eta) * (_selfz) - (normalZ * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((eta) * (_selfw) - (normalW * _t11)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long refract_unsafe(long dest, long src, long normal, float eta) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return refract_unsafe_fma(dest, src, normal, eta);
        return refract_unsafe_mulAdd(dest, src, normal, eta);
    }

    /** {@code refract_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long refract_unsafe_fma(long dest, long src, long normal, float eta) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _normalw = UnsafeOpsHolder.U.getFloat(normal + 12L);
        float _t4 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        float _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0f), eta * eta, 1.0f);
        return refract_unsafe_fma_sf1fd9d5f_1(dest, eta, _selfx, _selfy, _selfz, _selfw, _normalx, _normaly, _normalz, _normalw, _t8, java.lang.Math.fma(eta, _t4, (float) java.lang.Math.sqrt(Math.max(0.0f, _t8))));
    }

    /** Piece 2 of {@code refract_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long refract_unsafe_fma_sf1fd9d5f_1(long dest, float eta, float _selfx, float _selfy, float _selfz, float _selfw, float _normalx, float _normaly, float _normalz, float _normalw, float _t8, float _t11) {
        if (_t8 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(eta, _selfx, -(_normalx * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(eta, _selfy, -(_normaly * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(eta, _selfz, -(_normalz * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code refract_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long refract_unsafe_mulAdd(long dest, long src, long normal, float eta) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _normalx = UnsafeOpsHolder.U.getFloat(normal);
        float _normaly = UnsafeOpsHolder.U.getFloat(normal + 4L);
        float _normalz = UnsafeOpsHolder.U.getFloat(normal + 8L);
        float _normalw = UnsafeOpsHolder.U.getFloat(normal + 12L);
        float _t4 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        float _t8 = ((((_t4) * (_t4) - (1.0f))) * (eta * eta) + (1.0f));
        float _t11 = ((eta) * (_t4) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t8))));
        if (_t8 >= 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, ((eta) * (_selfx) - (_normalx * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((eta) * (_selfy) - (_normaly * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((eta) * (_selfz) - (_normalz * _t11)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((eta) * (_selfw) - (_normalw * _t11)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long round_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.rint(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.rint(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.rint(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.rint(_selfw));
        return dest;
    }

    public static long sign_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.signum(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.signum(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.signum(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.signum(_selfw));
        return dest;
    }

    public static long sin_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.sin(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.sin(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.sin(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.sin(_selfw));
        return dest;
    }

    public static long sinh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.sinh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.sinh(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.sinh(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.sinh(_selfw));
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, float edge0, float edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_mulAdd(dest, src, edge0, edge1);
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 8L) - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 12L) - edge0) * _t0_inv));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 8L) - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 12L) - edge0) * _t0_inv));
        UnsafeOpsHolder.U.putFloat(dest, ((3.0f) - (2.0f) * (_t13)) * _t13 * _t13);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((3.0f) - (2.0f) * (_t14)) * _t14 * _t14);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((3.0f) - (2.0f) * (_t15)) * _t15 * _t15);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_mulAdd(dest, src, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        float _t16 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 8L) - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 12L) - edge0W) / (edge1W - edge0W)));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        float _t16 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 8L) - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 12L) - edge0W) / (edge1W - edge0W)));
        UnsafeOpsHolder.U.putFloat(dest, ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((3.0f) - (2.0f) * (_t17)) * _t17 * _t17);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((3.0f) - (2.0f) * (_t18)) * _t18 * _t18);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((3.0f) - (2.0f) * (_t19)) * _t19 * _t19);
        return dest;
    }

    public static long smoothstep_unsafe(long dest, long src, long edge0, long edge1) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_unsafe_fma(dest, src, edge0, edge1);
        return smoothstep_unsafe_mulAdd(dest, src, edge0, edge1);
    }

    /** {@code smoothstep_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_fma(long dest, long src, long edge0, long edge1) {
        float _edge0x = UnsafeOpsHolder.U.getFloat(edge0);
        float _edge0y = UnsafeOpsHolder.U.getFloat(edge0 + 4L);
        float _edge0z = UnsafeOpsHolder.U.getFloat(edge0 + 8L);
        float _edge0w = UnsafeOpsHolder.U.getFloat(edge0 + 12L);
        return smoothstep_unsafe_fma_s704d23e_1(dest, src, edge1, _edge0x, _edge0y, _edge0z, _edge0w);
    }

    /** Piece 2 of {@code smoothstep_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long smoothstep_unsafe_fma_s704d23e_1(long dest, long src, long edge1, float _edge0x, float _edge0y, float _edge0z, float _edge0w) {
        float _t16 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - _edge0x) / (UnsafeOpsHolder.U.getFloat(edge1) - _edge0x)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - _edge0y) / (UnsafeOpsHolder.U.getFloat(edge1 + 4L) - _edge0y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 8L) - _edge0z) / (UnsafeOpsHolder.U.getFloat(edge1 + 8L) - _edge0z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 12L) - _edge0w) / (UnsafeOpsHolder.U.getFloat(edge1 + 12L) - _edge0w)));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    /** {@code smoothstep_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long smoothstep_unsafe_mulAdd(long dest, long src, long edge0, long edge1) {
        float _edge0x = UnsafeOpsHolder.U.getFloat(edge0);
        float _edge0y = UnsafeOpsHolder.U.getFloat(edge0 + 4L);
        float _edge0z = UnsafeOpsHolder.U.getFloat(edge0 + 8L);
        float _edge0w = UnsafeOpsHolder.U.getFloat(edge0 + 12L);
        float _t16 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src) - _edge0x) / (UnsafeOpsHolder.U.getFloat(edge1) - _edge0x)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 4L) - _edge0y) / (UnsafeOpsHolder.U.getFloat(edge1 + 4L) - _edge0y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 8L) - _edge0z) / (UnsafeOpsHolder.U.getFloat(edge1 + 8L) - _edge0z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (UnsafeOpsHolder.U.getFloat(src + 12L) - _edge0w) / (UnsafeOpsHolder.U.getFloat(edge1 + 12L) - _edge0w)));
        UnsafeOpsHolder.U.putFloat(dest, ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16);
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((3.0f) - (2.0f) * (_t17)) * _t17 * _t17);
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((3.0f) - (2.0f) * (_t18)) * _t18 * _t18);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((3.0f) - (2.0f) * (_t19)) * _t19 * _t19);
        return dest;
    }

    public static long sqrt_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, (float) java.lang.Math.sqrt(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, (float) java.lang.Math.sqrt(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, (float) java.lang.Math.sqrt(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) java.lang.Math.sqrt(_selfw));
        return dest;
    }

    public static long step_unsafe(long dest, long src, float edge) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx < edge ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < edge ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz < edge ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static long step_unsafe(long dest, long src, float edgeX, float edgeY, float edgeZ, float edgeW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx < edgeX ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < edgeY ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz < edgeZ ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw < edgeW ? 0.0f : 1.0f);
        return dest;
    }

    public static long step_unsafe(long dest, long src, long edge) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _edgex = UnsafeOpsHolder.U.getFloat(edge);
        float _edgey = UnsafeOpsHolder.U.getFloat(edge + 4L);
        float _edgez = UnsafeOpsHolder.U.getFloat(edge + 8L);
        float _edgew = UnsafeOpsHolder.U.getFloat(edge + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx < _edgex ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy < _edgey ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz < _edgez ? 0.0f : 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw < _edgew ? 0.0f : 1.0f);
        return dest;
    }

    public static long tan_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.tan(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.tan(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.tan(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.tan(_selfw));
        return dest;
    }

    public static long tanh_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.tanh(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.tanh(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.tanh(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.tanh(_selfw));
        return dest;
    }

    public static long trunc_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz >= 0.0f ? Math.floor(_selfz) : Math.ceil(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw >= 0.0f ? Math.floor(_selfw) : Math.ceil(_selfw));
        return dest;
    }

    public static long ulp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.ulp(_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.ulp(_selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.ulp(_selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.ulp(_selfw));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long mat) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_unsafe_fma(dest, src, mat);
        return preMul_unsafe_mulAdd(dest, src, mat);
    }

    /** {@code preMul_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_fma(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
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
        return preMul_unsafe_fma_s29370ba_1(dest, mat, _selfx, _selfy, _selfz, _selfw, _mat00, _mat10, _mat20, _mat30, _mat01, _mat11, _mat21, _mat31, _mat02, _mat12, _mat22, _mat32, _mat03, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMul_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long preMul_unsafe_fma_s29370ba_1(long dest, long mat, float _selfx, float _selfy, float _selfz, float _selfw, float _mat00, float _mat10, float _mat20, float _mat30, float _mat01, float _mat11, float _mat21, float _mat31, float _mat02, float _mat12, float _mat22, float _mat32, float _mat03, float _mat13, float _mat23) {
        float _mat33 = UnsafeOpsHolder.U.getFloat(mat + 60L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_mat03, _selfw, java.lang.Math.fma(_mat02, _selfz, java.lang.Math.fma(_mat00, _selfx, _mat01 * _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_mat13, _selfw, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_mat23, _selfw, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_mat33, _selfw, java.lang.Math.fma(_mat32, _selfz, java.lang.Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    /** {@code preMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_mulAdd(long dest, long src, long mat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _mat00 = UnsafeOpsHolder.U.getFloat(mat);
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
        return preMul_unsafe_mulAdd_s5d8dd4bf_1(dest, mat, _selfx, _selfy, _selfz, _selfw, _mat00, _mat10, _mat20, _mat30, _mat01, _mat11, _mat21, _mat31, _mat02, _mat12, _mat22, _mat32, _mat03, _mat13, _mat23);
    }

    /** Piece 2 of {@code preMul_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long preMul_unsafe_mulAdd_s5d8dd4bf_1(long dest, long mat, float _selfx, float _selfy, float _selfz, float _selfw, float _mat00, float _mat10, float _mat20, float _mat30, float _mat01, float _mat11, float _mat21, float _mat31, float _mat02, float _mat12, float _mat22, float _mat32, float _mat03, float _mat13, float _mat23) {
        float _mat33 = UnsafeOpsHolder.U.getFloat(mat + 60L);
        UnsafeOpsHolder.U.putFloat(dest, ((_mat03) * (_selfw) + (((_mat02) * (_selfz) + (((_mat00) * (_selfx) + (_mat01 * _selfy)))))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_mat13) * (_selfw) + (((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_mat23) * (_selfw) + (((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_mat33) * (_selfw) + (((_mat32) * (_selfz) + (((_mat30) * (_selfx) + (_mat31 * _selfy)))))));
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_unsafe_mulAdd(dest, src, quatX, quatY, quatZ, quatW);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * java.lang.Math.fma(quatY, _selfz, -(quatZ * _selfy));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, _selfz))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    /** {@code rotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_mulAdd(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((quatX) * (_selfy) - (quatY * _selfx));
        float _t10 = 2.0f * ((quatZ) * (_selfx) - (quatX * _selfz));
        float _t11 = 2.0f * ((quatY) * (_selfz) - (quatZ * _selfy));
        UnsafeOpsHolder.U.putFloat(dest, ((quatY) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatZ) * (_t10)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((quatZ) * (_t11) + (((((quatW) * (_t10) + (_selfy))) - (quatX) * (_t9)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfz))) - (quatY) * (_t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, long quat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_unsafe_mulAdd(dest, src, quat);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
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
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    /** {@code rotate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotate_unsafe_mulAdd(long dest, long src, long quat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
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
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_mulAdd(dest, src, angle, axisX, axisY, axisZ);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        UnsafeOpsHolder.U.putFloat(dest, ((_t2) * (axisX * _t5) + (((_selfx) * (_t0) + (((axisY) * (_selfz) - (axisZ * _selfy)) * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t2) * (axisY * _t5) + (((_selfy) * (_t0) + (((axisZ) * (_selfx) - (axisX * _selfz)) * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t2) * (axisZ * _t5) + (((_selfz) * (_t0) + (((axisX) * (_selfy) - (axisY * _selfx)) * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_mulAdd(dest, src, axis, angle);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t2, _axisx * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t2, _axisy * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t2, _axisz * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, long axis, float angle) {
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        UnsafeOpsHolder.U.putFloat(dest, ((_t2) * (_axisx * _t5) + (((_selfx) * (_t0) + (((_axisy) * (_selfz) - (_axisz * _selfy)) * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t2) * (_axisy * _t5) + (((_selfy) * (_t0) + (((_axisz) * (_selfx) - (_axisx * _selfz)) * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t2) * (_axisz * _t5) + (((_selfz) * (_t0) + (((_axisx) * (_selfy) - (_axisy * _selfx)) * _t1)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotateInverse_unsafe(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_unsafe_mulAdd(dest, src, quatX, quatY, quatZ, quatW);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * java.lang.Math.fma(quatZ, _selfy, -(quatY * _selfz));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, _selfy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfz))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    /** {@code rotateInverse_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateInverse_unsafe_mulAdd(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((quatX) * (_selfz) - (quatZ * _selfx));
        float _t10 = 2.0f * ((quatY) * (_selfx) - (quatX * _selfy));
        float _t11 = 2.0f * ((quatZ) * (_selfy) - (quatY * _selfz));
        UnsafeOpsHolder.U.putFloat(dest, ((quatZ) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatY) * (_t10)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfy))) - (quatZ) * (_t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((quatY) * (_t11) + (((((quatW) * (_t10) + (_selfz))) - (quatX) * (_t9)))));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotateInverse_unsafe(long dest, long src, long quat) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_unsafe_mulAdd(dest, src, quat);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
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
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    /** {@code rotateInverse_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateInverse_unsafe_mulAdd(long dest, long src, long quat) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
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
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, _selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t0, -(_selfz * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfy, _t1, _selfz * _t0));
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, _selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t0) - (_selfz * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfy) * (_t1) + (_selfz * _t0)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
            return dest;
        }
    }

    public static long rotateY_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t0, _selfz * _t1));
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t0, -(_selfx * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t0) + (_selfz * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t0) - (_selfx * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
            return dest;
        }
    }

    public static long rotateZ_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t0, -(_selfy * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfx, _t1, _selfy * _t0));
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t0) - (_selfy * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfx) * (_t1) + (_selfy * _t0)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
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
