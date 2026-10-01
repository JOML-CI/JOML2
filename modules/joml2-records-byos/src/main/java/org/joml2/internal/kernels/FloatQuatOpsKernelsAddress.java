// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link FloatQuatOps} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code FloatQuatOps}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class FloatQuatOpsKernelsAddress {
    private FloatQuatOpsKernelsAddress() {}

    public static long invert_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t3_inv = 1.0f / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
            UnsafeOpsHolder.U.putFloat(dest, -(_selfx * _t3_inv));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_selfy * _t3_inv));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_selfz * _t3_inv));
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t3_inv);
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t3_inv = 1.0f / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
            UnsafeOpsHolder.U.putFloat(dest, -(_selfx * _t3_inv));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_selfy * _t3_inv));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_selfz * _t3_inv));
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t3_inv);
            return dest;
        }
    }

    public static long invertProduct_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t20 = java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t21 = java.lang.Math.fma(otherW, _selfw, -otherX * _selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz);
        float _t22 = java.lang.Math.fma(otherY, _selfx, otherZ * _selfw) + java.lang.Math.fma(otherW, _selfz, -otherX * _selfy);
        float _t23 = java.lang.Math.fma(otherX, _selfz, otherW * _selfy) + java.lang.Math.fma(otherY, _selfw, -(otherZ * _selfx));
        float _t27_inv = 1.0f / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        UnsafeOpsHolder.U.putFloat(dest, -(_t20 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t23 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t22 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t21 * _t27_inv);
        return dest;
    }

    /** {@code invertProduct_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t20 = ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherZ) * (_selfy) - (otherY * _selfz));
        float _t21 = ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz));
        float _t22 = ((otherY) * (_selfx) + (otherZ * _selfw)) + ((otherW) * (_selfz) - (otherX * _selfy));
        float _t23 = ((otherX) * (_selfz) + (otherW * _selfy)) + ((otherY) * (_selfw) - (otherZ * _selfx));
        float _t27_inv = 1.0f / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
        UnsafeOpsHolder.U.putFloat(dest, -(_t20 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t23 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t22 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t21 * _t27_inv);
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_unsafe_fma(dest, src, other);
        return invertProduct_unsafe_mulAdd(dest, src, other);
    }

    /** {@code invertProduct_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_fma(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t20 = java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t21 = java.lang.Math.fma(_otherw, _selfw, -_otherx * _selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz);
        float _t22 = java.lang.Math.fma(_othery, _selfx, _otherz * _selfw) + java.lang.Math.fma(_otherw, _selfz, -_otherx * _selfy);
        float _t23 = java.lang.Math.fma(_otherx, _selfz, _otherw * _selfy) + java.lang.Math.fma(_othery, _selfw, -(_otherz * _selfx));
        float _t27_inv = 1.0f / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        UnsafeOpsHolder.U.putFloat(dest, -(_t20 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t23 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t22 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t21 * _t27_inv);
        return dest;
    }

    /** {@code invertProduct_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_mulAdd(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t20 = ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_otherz) * (_selfy) - (_othery * _selfz));
        float _t21 = ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz));
        float _t22 = ((_othery) * (_selfx) + (_otherz * _selfw)) + ((_otherw) * (_selfz) - (_otherx * _selfy));
        float _t23 = ((_otherx) * (_selfz) + (_otherw * _selfy)) + ((_othery) * (_selfw) - (_otherz * _selfx));
        float _t27_inv = 1.0f / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
        UnsafeOpsHolder.U.putFloat(dest, -(_t20 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t23 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t22 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t21 * _t27_inv);
        return dest;
    }

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

    public static long makeUniformRotation_unsafe(long dest, float u1, float u2, float u3) {
        float _t0 = (float) java.lang.Math.sqrt(u1);
        float _t1 = u2 * 6.2831855f;
        float _t3 = u3 * 6.2831855f;
        float _t4 = (float) java.lang.Math.sqrt(1.0f - u1);
        UnsafeOpsHolder.U.putFloat(dest, Math.sinWithCos(_t1) * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.cosWithSin(_t1) * _t4);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.sinWithCos(_t3) * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosWithSin(_t3) * _t0);
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

    public static long makeFromDualQuat_unsafe(long dest, float dqRX, float dqRY, float dqRZ, float dqRW) {
        UnsafeOpsHolder.U.putFloat(dest, dqRX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, dqRY);
        UnsafeOpsHolder.U.putFloat(dest + 8L, dqRZ);
        UnsafeOpsHolder.U.putFloat(dest + 12L, dqRW);
        return dest;
    }

    public static long makeFromMatrixMat3x3_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m20 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 12L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 16L);
        float _m21 = UnsafeOpsHolder.U.getFloat(m + 20L);
        float _m02 = UnsafeOpsHolder.U.getFloat(m + 24L);
        float _m12 = UnsafeOpsHolder.U.getFloat(m + 28L);
        float _m22 = UnsafeOpsHolder.U.getFloat(m + 32L);
        float _t0 = _m00 + _m11;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        return makeFromMatrixMat3x3_unsafe_se9dcf998_1(dest, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x3_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeFromMatrixMat3x3_unsafe_se9dcf998_1(long dest, float _m00, float _m11, float _m22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t1);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t7);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t9);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t15));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t4);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t6);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t4);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t8);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t7);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t6);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t8);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static long makeFromMatrixMat3x4_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m02 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 16L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 20L);
        float _m12 = UnsafeOpsHolder.U.getFloat(m + 24L);
        float _m20 = UnsafeOpsHolder.U.getFloat(m + 32L);
        float _m21 = UnsafeOpsHolder.U.getFloat(m + 36L);
        float _m22 = UnsafeOpsHolder.U.getFloat(m + 40L);
        float _t0 = _m00 + _m11;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        return makeFromMatrixMat3x4_unsafe_s928885d3_1(dest, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeFromMatrixMat3x4_unsafe_s928885d3_1(long dest, float _m00, float _m11, float _m22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t1);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t7);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t9);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t15));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t4);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t6);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t4);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t8);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t7);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t6);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t8);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static long makeFromMatrixMat4x4_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m20 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 16L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 20L);
        float _m21 = UnsafeOpsHolder.U.getFloat(m + 24L);
        float _m02 = UnsafeOpsHolder.U.getFloat(m + 32L);
        float _m12 = UnsafeOpsHolder.U.getFloat(m + 36L);
        float _m22 = UnsafeOpsHolder.U.getFloat(m + 40L);
        float _t0 = _m00 + _m11;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        return makeFromMatrixMat4x4_unsafe_s3ec70630_1(dest, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeFromMatrixMat4x4_unsafe_s3ec70630_1(long dest, float _m00, float _m11, float _m22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t1);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t7);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t9);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t15));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t4);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t6);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t4);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t8);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t7);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t6);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t8);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static long toDualQuat_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        return dest;
    }

    public static long toMatrix_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix_unsafe_fma(dest, src);
        return toMatrix_unsafe_mulAdd(dest, src);
    }

    /** {@code toMatrix_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toMatrix_unsafe_fma(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 36L, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        return toMatrix_unsafe_fma_sdc57158d_1(dest, _selfx, _selfy);
    }

    /** Piece 2 of {@code toMatrix_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long toMatrix_unsafe_fma_sdc57158d_1(long dest, float _selfx, float _selfy) {
        UnsafeOpsHolder.U.putFloat(dest + 40L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 48L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 52L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 56L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 60L, 1.0f);
        return dest;
    }

    /** {@code toMatrix_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toMatrix_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putFloat(dest, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * ((_selfx) * (_selfy) + (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * ((_selfx) * (_selfz) - (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 2.0f * ((_selfx) * (_selfy) - (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 2.0f * ((_selfx) * (_selfz) + (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        return toMatrix_unsafe_mulAdd_sc2f236b6_1(dest);
    }

    /** Piece 2 of {@code toMatrix_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long toMatrix_unsafe_mulAdd_sc2f236b6_1(long dest) {
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 48L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 52L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 56L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 60L, 1.0f);
        return dest;
    }

    public static long toMatrix3x3_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix3x3_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    /** {@code toMatrix3x3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toMatrix3x3_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putFloat(dest, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * ((_selfx) * (_selfy) + (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * ((_selfx) * (_selfz) - (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * ((_selfx) * (_selfy) - (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * ((_selfx) * (_selfz) + (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        return dest;
    }

    public static long toMatrix3x4_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix3x4_unsafe_fma(dest, src);
        return toMatrix3x4_unsafe_mulAdd(dest, src);
    }

    /** {@code toMatrix3x4_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toMatrix3x4_unsafe_fma(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2));
        return toMatrix3x4_unsafe_fma_sa2f6a18c_1(dest, _selfx, _selfy, _selfz, _selfw);
    }

    /** Piece 2 of {@code toMatrix3x4_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long toMatrix3x4_unsafe_fma_sa2f6a18c_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 40L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    /** {@code toMatrix3x4_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toMatrix3x4_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putFloat(dest, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * ((_selfx) * (_selfy) - (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * ((_selfx) * (_selfz) + (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 2.0f * ((_selfx) * (_selfy) + (_t1)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 2.0f * ((_selfx) * (_selfz) - (_t2)));
        UnsafeOpsHolder.U.putFloat(dest + 36L, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 40L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long decomposeSwingTwist_unsafe(long swing, long twist, long src, float axisX, float axisY, float axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSwingTwist_unsafe_fma(swing, twist, src, axisX, axisY, axisZ);
        return decomposeSwingTwist_unsafe_mulAdd(swing, twist, src, axisX, axisY, axisZ);
    }

    /** {@code decomposeSwingTwist_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeSwingTwist_unsafe_fma(long swing, long twist, long src, float axisX, float axisY, float axisZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(swing, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        UnsafeOpsHolder.U.putFloat(swing + 4L, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        UnsafeOpsHolder.U.putFloat(swing + 8L, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        return decomposeSwingTwist_unsafe_fma_s7bbd72c2_1(swing, twist, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeSwingTwist_unsafe_fma_s7bbd72c2_1(long swing, long twist, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        UnsafeOpsHolder.U.putFloat(swing + 12L, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        UnsafeOpsHolder.U.putFloat(twist, _t12);
        UnsafeOpsHolder.U.putFloat(twist + 4L, _t13);
        UnsafeOpsHolder.U.putFloat(twist + 8L, _t14);
        UnsafeOpsHolder.U.putFloat(twist + 12L, _t11);
        return swing;
    }

    /** {@code decomposeSwingTwist_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeSwingTwist_unsafe_mulAdd(long swing, long twist, long src, float axisX, float axisY, float axisZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(swing, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        UnsafeOpsHolder.U.putFloat(swing + 4L, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        UnsafeOpsHolder.U.putFloat(swing + 8L, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        UnsafeOpsHolder.U.putFloat(swing + 12L, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return decomposeSwingTwist_unsafe_mulAdd_s7d13b401_1(swing, twist, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeSwingTwist_unsafe_mulAdd_s7d13b401_1(long swing, long twist, float _t11, float _t12, float _t13, float _t14) {
        UnsafeOpsHolder.U.putFloat(twist, _t12);
        UnsafeOpsHolder.U.putFloat(twist + 4L, _t13);
        UnsafeOpsHolder.U.putFloat(twist + 8L, _t14);
        UnsafeOpsHolder.U.putFloat(twist + 12L, _t11);
        return swing;
    }

    public static long decomposeSwingTwist_unsafe(long swing, long twist, long src, long axis) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSwingTwist_unsafe_fma(swing, twist, src, axis);
        return decomposeSwingTwist_unsafe_mulAdd(swing, twist, src, axis);
    }

    /** {@code decomposeSwingTwist_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeSwingTwist_unsafe_fma(long swing, long twist, long src, long axis) {
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(swing, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        UnsafeOpsHolder.U.putFloat(swing + 4L, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        return decomposeSwingTwist_unsafe_fma_s9213e089_1(swing, twist, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeSwingTwist_unsafe_fma_s9213e089_1(long swing, long twist, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        UnsafeOpsHolder.U.putFloat(swing + 8L, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        UnsafeOpsHolder.U.putFloat(swing + 12L, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        UnsafeOpsHolder.U.putFloat(twist, _t12);
        UnsafeOpsHolder.U.putFloat(twist + 4L, _t13);
        UnsafeOpsHolder.U.putFloat(twist + 8L, _t14);
        UnsafeOpsHolder.U.putFloat(twist + 12L, _t11);
        return swing;
    }

    /** {@code decomposeSwingTwist_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeSwingTwist_unsafe_mulAdd(long swing, long twist, long src, long axis) {
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(swing, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        UnsafeOpsHolder.U.putFloat(swing + 4L, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        return decomposeSwingTwist_unsafe_mulAdd_sda9110ca_1(swing, twist, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeSwingTwist_unsafe_mulAdd_sda9110ca_1(long swing, long twist, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        UnsafeOpsHolder.U.putFloat(swing + 8L, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        UnsafeOpsHolder.U.putFloat(swing + 12L, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        UnsafeOpsHolder.U.putFloat(twist, _t12);
        UnsafeOpsHolder.U.putFloat(twist + 4L, _t13);
        UnsafeOpsHolder.U.putFloat(twist + 8L, _t14);
        UnsafeOpsHolder.U.putFloat(twist + 12L, _t11);
        return swing;
    }

    public static long getSwing_unsafe(long dest, long src, float axisX, float axisY, float axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getSwing_unsafe_fma(dest, src, axisX, axisY, axisZ);
        return getSwing_unsafe_mulAdd(dest, src, axisX, axisY, axisZ);
    }

    /** {@code getSwing_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getSwing_unsafe_fma(long dest, long src, float axisX, float axisY, float axisZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        return dest;
    }

    /** {@code getSwing_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getSwing_unsafe_mulAdd(long dest, long src, float axisX, float axisY, float axisZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return dest;
    }

    public static long getSwing_unsafe(long dest, long src, long axis) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getSwing_unsafe_fma(dest, src, axis);
        return getSwing_unsafe_mulAdd(dest, src, axis);
    }

    /** {@code getSwing_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getSwing_unsafe_fma(long dest, long src, long axis) {
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        return getSwing_unsafe_fma_sc55c06b6_1(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getSwing_unsafe_fma_sc55c06b6_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        return dest;
    }

    /** {@code getSwing_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getSwing_unsafe_mulAdd(long dest, long src, long axis) {
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        return getSwing_unsafe_mulAdd_scf97a5ff_1(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long getSwing_unsafe_mulAdd_scf97a5ff_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return dest;
    }

    public static long getTwist_unsafe(long dest, long src, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getTwist_unsafe_mulAdd(dest, src, axisX, axisY, axisZ);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = java.lang.Math.fma(axisZ, UnsafeOpsHolder.U.getFloat(src + 8L), java.lang.Math.fma(axisX, UnsafeOpsHolder.U.getFloat(src), axisY * UnsafeOpsHolder.U.getFloat(src + 4L)));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            UnsafeOpsHolder.U.putFloat(dest, axisX * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 4L, axisY * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 8L, axisZ * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t5);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f);
        }
        return dest;
    }

    /** {@code getTwist_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getTwist_unsafe_mulAdd(long dest, long src, float axisX, float axisY, float axisZ) {
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = ((axisZ) * (UnsafeOpsHolder.U.getFloat(src + 8L)) + (((axisX) * (UnsafeOpsHolder.U.getFloat(src)) + (axisY * UnsafeOpsHolder.U.getFloat(src + 4L)))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            UnsafeOpsHolder.U.putFloat(dest, axisX * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 4L, axisY * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 8L, axisZ * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t5);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f);
        }
        return dest;
    }

    public static long getTwist_unsafe(long dest, long src, long axis) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getTwist_unsafe_mulAdd(dest, src, axis);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = java.lang.Math.fma(_axisz, UnsafeOpsHolder.U.getFloat(src + 8L), java.lang.Math.fma(_axisx, UnsafeOpsHolder.U.getFloat(src), _axisy * UnsafeOpsHolder.U.getFloat(src + 4L)));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            UnsafeOpsHolder.U.putFloat(dest, _axisx * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _axisy * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _axisz * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t5);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f);
        }
        return dest;
    }

    /** {@code getTwist_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getTwist_unsafe_mulAdd(long dest, long src, long axis) {
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = ((_axisz) * (UnsafeOpsHolder.U.getFloat(src + 8L)) + (((_axisx) * (UnsafeOpsHolder.U.getFloat(src)) + (_axisy * UnsafeOpsHolder.U.getFloat(src + 4L)))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            UnsafeOpsHolder.U.putFloat(dest, _axisx * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _axisy * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _axisz * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t5);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f);
        }
        return dest;
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f);
        return dest;
    }

    public static long makeZero_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
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

    public static long nlerp_unsafe(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return nlerp_unsafe_mulAdd(dest, src, targetX, targetY, targetZ, targetW, alpha);
        float _t0 = 1.0f - alpha;
        float _t5 = java.lang.Math.fma(alpha, targetW, UnsafeOpsHolder.U.getFloat(src + 12L) * _t0);
        float _t6 = java.lang.Math.fma(alpha, targetZ, UnsafeOpsHolder.U.getFloat(src + 8L) * _t0);
        float _t7 = java.lang.Math.fma(alpha, targetX, UnsafeOpsHolder.U.getFloat(src) * _t0);
        float _t8 = java.lang.Math.fma(alpha, targetY, UnsafeOpsHolder.U.getFloat(src + 4L) * _t0);
        float _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t7 * _t13);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t8 * _t13);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t6 * _t13);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t5 * _t13);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code nlerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerp_unsafe_mulAdd(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t5 = ((alpha) * (targetW) + (UnsafeOpsHolder.U.getFloat(src + 12L) * _t0));
        float _t6 = ((alpha) * (targetZ) + (UnsafeOpsHolder.U.getFloat(src + 8L) * _t0));
        float _t7 = ((alpha) * (targetX) + (UnsafeOpsHolder.U.getFloat(src) * _t0));
        float _t8 = ((alpha) * (targetY) + (UnsafeOpsHolder.U.getFloat(src + 4L) * _t0));
        float _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t7 * _t13);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t8 * _t13);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t6 * _t13);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t5 * _t13);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long nlerp_unsafe(long dest, long src, long target, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerp_unsafe_fma(dest, src, target, alpha);
        return nlerp_unsafe_mulAdd(dest, src, target, alpha);
    }

    /** {@code nlerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerp_unsafe_fma(long dest, long src, long target, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t5 = java.lang.Math.fma(alpha, UnsafeOpsHolder.U.getFloat(target + 12L), UnsafeOpsHolder.U.getFloat(src + 12L) * _t0);
        float _t6 = java.lang.Math.fma(alpha, UnsafeOpsHolder.U.getFloat(target + 8L), UnsafeOpsHolder.U.getFloat(src + 8L) * _t0);
        float _t7 = java.lang.Math.fma(alpha, UnsafeOpsHolder.U.getFloat(target), UnsafeOpsHolder.U.getFloat(src) * _t0);
        float _t8 = java.lang.Math.fma(alpha, UnsafeOpsHolder.U.getFloat(target + 4L), UnsafeOpsHolder.U.getFloat(src + 4L) * _t0);
        float _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t7 * _t13);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t8 * _t13);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t6 * _t13);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t5 * _t13);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code nlerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerp_unsafe_mulAdd(long dest, long src, long target, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t5 = ((alpha) * (UnsafeOpsHolder.U.getFloat(target + 12L)) + (UnsafeOpsHolder.U.getFloat(src + 12L) * _t0));
        float _t6 = ((alpha) * (UnsafeOpsHolder.U.getFloat(target + 8L)) + (UnsafeOpsHolder.U.getFloat(src + 8L) * _t0));
        float _t7 = ((alpha) * (UnsafeOpsHolder.U.getFloat(target)) + (UnsafeOpsHolder.U.getFloat(src) * _t0));
        float _t8 = ((alpha) * (UnsafeOpsHolder.U.getFloat(target + 4L)) + (UnsafeOpsHolder.U.getFloat(src + 4L) * _t0));
        float _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t7 * _t13);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t8 * _t13);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t6 * _t13);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t5 * _t13);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long nlerpShortest_unsafe(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerpShortest_unsafe_fma(dest, src, targetX, targetY, targetZ, targetW, alpha);
        return nlerpShortest_unsafe_mulAdd(dest, src, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code nlerpShortest_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerpShortest_unsafe_fma(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = 1.0f - alpha;
        float _t18, _t19, _t20, _t21;
        if (-java.lang.Math.fma(_selfw, targetW, java.lang.Math.fma(_selfz, targetZ, java.lang.Math.fma(_selfx, targetX, _selfy * targetY))) > 0.0f) {
            _t18 = java.lang.Math.fma(alpha, -targetW, _selfw * _t1);
            _t19 = java.lang.Math.fma(alpha, -targetZ, _selfz * _t1);
            _t20 = java.lang.Math.fma(alpha, -targetX, _selfx * _t1);
            _t21 = java.lang.Math.fma(alpha, -targetY, _selfy * _t1);
        } else {
            _t18 = java.lang.Math.fma(alpha, targetW, _selfw * _t1);
            _t19 = java.lang.Math.fma(alpha, targetZ, _selfz * _t1);
            _t20 = java.lang.Math.fma(alpha, targetX, _selfx * _t1);
            _t21 = java.lang.Math.fma(alpha, targetY, _selfy * _t1);
        }
        float _t25 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t20, _t20, _t21 * _t21)));
        return nlerpShortest_unsafe_fma_s604b4fea_1(dest, _t18, _t19, _t20, _t21, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long nlerpShortest_unsafe_fma_s604b4fea_1(long dest, float _t18, float _t19, float _t20, float _t21, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t20 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t21 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t19 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t18 * _t26);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code nlerpShortest_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerpShortest_unsafe_mulAdd(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = 1.0f - alpha;
        float _t18, _t19, _t20, _t21;
        if (-((_selfw) * (targetW) + (((_selfz) * (targetZ) + (((_selfx) * (targetX) + (_selfy * targetY)))))) > 0.0f) {
            _t18 = ((_selfw * _t1) - (alpha) * (targetW));
            _t19 = ((_selfz * _t1) - (alpha) * (targetZ));
            _t20 = ((_selfx * _t1) - (alpha) * (targetX));
            _t21 = ((_selfy * _t1) - (alpha) * (targetY));
        } else {
            _t18 = ((alpha) * (targetW) + (_selfw * _t1));
            _t19 = ((alpha) * (targetZ) + (_selfz * _t1));
            _t20 = ((alpha) * (targetX) + (_selfx * _t1));
            _t21 = ((alpha) * (targetY) + (_selfy * _t1));
        }
        float _t25 = ((_t18) * (_t18) + (((_t19) * (_t19) + (((_t20) * (_t20) + (_t21 * _t21))))));
        return nlerpShortest_unsafe_mulAdd_s68e0f27d_1(dest, _t18, _t19, _t20, _t21, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long nlerpShortest_unsafe_mulAdd_s68e0f27d_1(long dest, float _t18, float _t19, float _t20, float _t21, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t20 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t21 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t19 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t18 * _t26);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long nlerpShortest_unsafe(long dest, long src, long target, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerpShortest_unsafe_fma(dest, src, target, alpha);
        return nlerpShortest_unsafe_mulAdd(dest, src, target, alpha);
    }

    /** {@code nlerpShortest_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerpShortest_unsafe_fma(long dest, long src, long target, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t1 = 1.0f - alpha;
        float _t18, _t19, _t20, _t21;
        if (-java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety))) > 0.0f) {
            _t18 = java.lang.Math.fma(alpha, -_targetw, _selfw * _t1);
            _t19 = java.lang.Math.fma(alpha, -_targetz, _selfz * _t1);
            _t20 = java.lang.Math.fma(alpha, -_targetx, _selfx * _t1);
            _t21 = java.lang.Math.fma(alpha, -_targety, _selfy * _t1);
        } else {
            _t18 = java.lang.Math.fma(alpha, _targetw, _selfw * _t1);
            _t19 = java.lang.Math.fma(alpha, _targetz, _selfz * _t1);
            _t20 = java.lang.Math.fma(alpha, _targetx, _selfx * _t1);
            _t21 = java.lang.Math.fma(alpha, _targety, _selfy * _t1);
        }
        return nlerpShortest_unsafe_fma_s512aac7_1(dest, _t18, _t19, _t20, _t21, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t20, _t20, _t21 * _t21))));
    }

    /** Piece 2 of {@code nlerpShortest_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long nlerpShortest_unsafe_fma_s512aac7_1(long dest, float _t18, float _t19, float _t20, float _t21, float _t25) {
        float _t26 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t20 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t21 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t19 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t18 * _t26);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code nlerpShortest_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerpShortest_unsafe_mulAdd(long dest, long src, long target, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t1 = 1.0f - alpha;
        float _t18, _t19, _t20, _t21;
        if (-((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety)))))) > 0.0f) {
            _t18 = ((_selfw * _t1) - (alpha) * (_targetw));
            _t19 = ((_selfz * _t1) - (alpha) * (_targetz));
            _t20 = ((_selfx * _t1) - (alpha) * (_targetx));
            _t21 = ((_selfy * _t1) - (alpha) * (_targety));
        } else {
            _t18 = ((alpha) * (_targetw) + (_selfw * _t1));
            _t19 = ((alpha) * (_targetz) + (_selfz * _t1));
            _t20 = ((alpha) * (_targetx) + (_selfx * _t1));
            _t21 = ((alpha) * (_targety) + (_selfy * _t1));
        }
        float _t25 = ((_t18) * (_t18) + (((_t19) * (_t19) + (((_t20) * (_t20) + (_t21 * _t21))))));
        return nlerpShortest_unsafe_mulAdd_s94422b6a_1(dest, _t18, _t19, _t20, _t21, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long nlerpShortest_unsafe_mulAdd_s94422b6a_1(long dest, float _t18, float _t19, float _t20, float _t21, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t20 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t21 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t19 * _t26);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t18 * _t26);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, targetX, targetY, targetZ, targetW, alpha);
        return slerp_unsafe_mulAdd(dest, src, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = _selfw + targetW;
        float _t3 = _selfz + targetZ;
        float _t4 = _selfx + targetX;
        float _t5 = _selfy + targetY;
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _ct0 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct0 > 2.0E-6f && _ct0 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsAddress.slerp_degenerate(dest, src, targetX, targetY, targetZ, targetW, alpha);
        float _t22 = 2.0f * Math.sinWithCos(_t14) / _ct0;
        float _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t22;
        float _t25, _t26;
        if (alpha < 0.5f) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t25, targetX * _t26));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t25, targetY * _t26));
        return slerp_unsafe_fma_se22bcaff_1(dest, targetZ, targetW, _selfz, _selfw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_se22bcaff_1(long dest, float targetZ, float targetW, float _selfz, float _selfw, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t25, targetZ * _t26));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t25, targetW * _t26));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = _selfw + targetW;
        float _t3 = _selfz + targetZ;
        float _t4 = _selfx + targetX;
        float _t5 = _selfy + targetY;
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _ct0 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct0 > 2.0E-6f && _ct0 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsAddress.slerp_degenerate(dest, src, targetX, targetY, targetZ, targetW, alpha);
        float _t22 = 2.0f * Math.sinWithCos(_t14) / _ct0;
        float _t24 = Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t22;
        float _t25, _t26;
        if (alpha < 0.5f) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t25) + (targetX * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t25) + (targetY * _t26)));
        return slerp_unsafe_mulAdd_s7457022_1(dest, targetZ, targetW, _selfz, _selfw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s7457022_1(long dest, float targetZ, float targetW, float _selfz, float _selfw, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t25) + (targetZ * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t25) + (targetW * _t26)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.slerp_degenerate_unsafe(dest, src, targetX, targetY, targetZ, targetW, alpha);
        FloatQuatOpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_mulAdd(dest, src, targetX, targetY, targetZ, targetW, alpha);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfw + targetW;
        float _t2 = _selfz + targetZ;
        float _t3 = _selfx + targetX;
        float _t4 = _selfy + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _t13, _t14;
        if (_t11 > 4.0f - _t11) {
            _t13 = 1.0f - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0f - _t5;
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t13, targetX * _t14));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t13, targetY * _t14));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t13, targetZ * _t14));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t13, targetW * _t14));
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfw + targetW;
        float _t2 = _selfz + targetZ;
        float _t3 = _selfx + targetX;
        float _t4 = _selfy + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _t13, _t14;
        if (_t11 > 4.0f - _t11) {
            _t13 = 1.0f - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0f - _t5;
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t13) + (targetX * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t13) + (targetY * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t13) + (targetZ * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t13) + (targetW * _t14)));
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long target, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, target, alpha);
        return slerp_unsafe_mulAdd(dest, src, target, alpha);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, long target, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t2 = _selfw + _targetw;
        float _t3 = _selfz + _targetz;
        float _t4 = _selfx + _targetx;
        float _t5 = _selfy + _targety;
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _ct0 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct0 > 2.0E-6f && _ct0 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsAddress.slerp_degenerate(dest, src, target, alpha);
        float _t22 = 2.0f * Math.sinWithCos(_t14) / _ct0;
        return slerp_unsafe_fma_sd03c405c_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, _targetx, _targety, _targetz, _targetw, _t22, Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t22);
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_sd03c405c_1(long dest, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _targetx, float _targety, float _targetz, float _targetw, float _t22, float _t24) {
        float _t25, _t26;
        if (alpha < 0.5f) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t25, _targetx * _t26));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t25, _targety * _t26));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t25, _targetz * _t26));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t25, _targetw * _t26));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, long target, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t2 = _selfw + _targetw;
        float _t3 = _selfz + _targetz;
        float _t4 = _selfx + _targetx;
        float _t5 = _selfy + _targety;
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _ct0 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct0 > 2.0E-6f && _ct0 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsAddress.slerp_degenerate(dest, src, target, alpha);
        float _t22 = 2.0f * Math.sinWithCos(_t14) / _ct0;
        float _t24 = Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t22;
        float _t25, _t26;
        if (alpha < 0.5f) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        return slerp_unsafe_mulAdd_sbb1575df_1(dest, _selfx, _selfy, _selfz, _selfw, _targetx, _targety, _targetz, _targetw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_sbb1575df_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _targetx, float _targety, float _targetz, float _targetw, float _t25, float _t26) {
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t25) + (_targetx * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t25) + (_targety * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t25) + (_targetz * _t26)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t25) + (_targetw * _t26)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long target, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.slerp_degenerate_unsafe(dest, src, target, alpha);
        FloatQuatOpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(target, 16L), 0L, alpha);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, long target, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, target, alpha);
        return slerp_degenerate_unsafe_mulAdd(dest, src, target, alpha);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, long target, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t1 = _selfw + _targetw;
        float _t2 = _selfz + _targetz;
        float _t3 = _selfx + _targetx;
        float _t4 = _selfy + _targety;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _t13, _t14;
        if (_t11 > 4.0f - _t11) {
            _t13 = 1.0f - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0f - _t5;
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t13, _targetx * _t14));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t13, _targety * _t14));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t13, _targetz * _t14));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t13, _targetw * _t14));
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, long target, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t1 = _selfw + _targetw;
        float _t2 = _selfz + _targetz;
        float _t3 = _selfx + _targetx;
        float _t4 = _selfy + _targety;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _t13, _t14;
        if (_t11 > 4.0f - _t11) {
            _t13 = 1.0f - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0f - _t5;
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t13) + (_targetx * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t13) + (_targety * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t13) + (_targetz * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t13) + (_targetw * _t14)));
        return dest;
    }

    public static long slerpShortest_unsafe(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerpShortest_unsafe_fma(dest, src, targetX, targetY, targetZ, targetW, alpha);
        return slerpShortest_unsafe_mulAdd(dest, src, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code slerpShortest_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerpShortest_unsafe_fma(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t12 = java.lang.Math.fma(_selfw, targetW, java.lang.Math.fma(_selfz, targetZ, java.lang.Math.fma(_selfx, targetX, _selfy * targetY)));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
            _t21 = -targetW;
            _t22 = -targetZ;
            _t23 = -targetX;
            _t24 = -targetY;
        } else {
            _t21 = targetW;
            _t22 = targetZ;
            _t23 = targetX;
            _t24 = targetY;
        }
        float _t30 = (float) java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0f));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return slerpShortest_unsafe_fma_se0b08d51_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)));
    }

    /** Piece 2 of {@code slerpShortest_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerpShortest_unsafe_fma_se0b08d51_1(long dest, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = java.lang.Math.fma(_selfw, _t38, _t32 * _t21);
            _t44 = java.lang.Math.fma(_selfz, _t38, _t32 * _t22);
            _t45 = java.lang.Math.fma(_selfx, _t38, _t32 * _t23);
            _t46 = java.lang.Math.fma(_selfy, _t38, _t32 * _t24);
        } else {
            _t43 = java.lang.Math.fma(alpha, _t21, _selfw * _t1);
            _t44 = java.lang.Math.fma(alpha, _t22, _selfz * _t1);
            _t45 = java.lang.Math.fma(alpha, _t23, _selfx * _t1);
            _t46 = java.lang.Math.fma(alpha, _t24, _selfy * _t1);
        }
        float _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t51 * _t45);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t51 * _t46);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t51 * _t44);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t51 * _t43);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code slerpShortest_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerpShortest_unsafe_mulAdd(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t12 = ((_selfw) * (targetW) + (((_selfz) * (targetZ) + (((_selfx) * (targetX) + (_selfy * targetY))))));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
            _t21 = -targetW;
            _t22 = -targetZ;
            _t23 = -targetX;
            _t24 = -targetY;
        } else {
            _t21 = targetW;
            _t22 = targetZ;
            _t23 = targetX;
            _t24 = targetY;
        }
        float _t30 = (float) java.lang.Math.sqrt(((1.0f) - (_t15) * (_t15)));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return slerpShortest_unsafe_mulAdd_sb30139b4_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, ((Math.cosWithSin(_t18)) - (_t32) * (_t15)));
    }

    /** Piece 2 of {@code slerpShortest_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerpShortest_unsafe_mulAdd_sb30139b4_1(long dest, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = ((_selfw) * (_t38) + (_t32 * _t21));
            _t44 = ((_selfz) * (_t38) + (_t32 * _t22));
            _t45 = ((_selfx) * (_t38) + (_t32 * _t23));
            _t46 = ((_selfy) * (_t38) + (_t32 * _t24));
        } else {
            _t43 = ((alpha) * (_t21) + (_selfw * _t1));
            _t44 = ((alpha) * (_t22) + (_selfz * _t1));
            _t45 = ((alpha) * (_t23) + (_selfx * _t1));
            _t46 = ((alpha) * (_t24) + (_selfy * _t1));
        }
        float _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t51 * _t45);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t51 * _t46);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t51 * _t44);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t51 * _t43);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long slerpShortest_unsafe(long dest, long src, long target, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerpShortest_unsafe_fma(dest, src, target, alpha);
        return slerpShortest_unsafe_mulAdd(dest, src, target, alpha);
    }

    /** {@code slerpShortest_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerpShortest_unsafe_fma(long dest, long src, long target, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t12 = java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
            _t21 = -_targetw;
            _t22 = -_targetz;
            _t23 = -_targetx;
            _t24 = -_targety;
        } else {
            _t21 = _targetw;
            _t22 = _targetz;
            _t23 = _targetx;
            _t24 = _targety;
        }
        float _t30 = (float) java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0f));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return slerpShortest_unsafe_fma_s31e1a86_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)));
    }

    /** Piece 2 of {@code slerpShortest_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerpShortest_unsafe_fma_s31e1a86_1(long dest, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = java.lang.Math.fma(_selfw, _t38, _t32 * _t21);
            _t44 = java.lang.Math.fma(_selfz, _t38, _t32 * _t22);
            _t45 = java.lang.Math.fma(_selfx, _t38, _t32 * _t23);
            _t46 = java.lang.Math.fma(_selfy, _t38, _t32 * _t24);
        } else {
            _t43 = java.lang.Math.fma(alpha, _t21, _selfw * _t1);
            _t44 = java.lang.Math.fma(alpha, _t22, _selfz * _t1);
            _t45 = java.lang.Math.fma(alpha, _t23, _selfx * _t1);
            _t46 = java.lang.Math.fma(alpha, _t24, _selfy * _t1);
        }
        float _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t51 * _t45);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t51 * _t46);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t51 * _t44);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t51 * _t43);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code slerpShortest_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerpShortest_unsafe_mulAdd(long dest, long src, long target, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t12 = ((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety))))));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
            _t21 = -_targetw;
            _t22 = -_targetz;
            _t23 = -_targetx;
            _t24 = -_targety;
        } else {
            _t21 = _targetw;
            _t22 = _targetz;
            _t23 = _targetx;
            _t24 = _targety;
        }
        float _t30 = (float) java.lang.Math.sqrt(((1.0f) - (_t15) * (_t15)));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return slerpShortest_unsafe_mulAdd_seef19a49_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, ((Math.cosWithSin(_t18)) - (_t32) * (_t15)));
    }

    /** Piece 2 of {@code slerpShortest_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerpShortest_unsafe_mulAdd_seef19a49_1(long dest, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = ((_selfw) * (_t38) + (_t32 * _t21));
            _t44 = ((_selfz) * (_t38) + (_t32 * _t22));
            _t45 = ((_selfx) * (_t38) + (_t32 * _t23));
            _t46 = ((_selfy) * (_t38) + (_t32 * _t24));
        } else {
            _t43 = ((alpha) * (_t21) + (_selfw * _t1));
            _t44 = ((alpha) * (_t22) + (_selfz * _t1));
            _t45 = ((alpha) * (_t23) + (_selfx * _t1));
            _t46 = ((alpha) * (_t24) + (_selfy * _t1));
        }
        float _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t51 * _t45);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t51 * _t46);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t51 * _t44);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t51 * _t43);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long squad_unsafe(long dest, long src, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return squad_unsafe_fma(dest, src, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return squad_unsafe_mulAdd(dest, src, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
    }

    /** {@code squad_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long squad_unsafe_fma(long dest, long src, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        float _t0 = 1.0f - t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = Math.min(t, _t0);
        float _t23 = Math.min(4.0f, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6))));
        float _t27 = 4.0f - _t23;
        float _t33 = _t7 * quatArcAngle_fma(_t23);
        float _t41 = (float) java.lang.Math.sqrt(_t27 * _t23);
        float _t47 = _t23 > _t27 ? _t7 : 0.0f;
        float _t51 = 2.0f / _t41 * Math.sinWithCos(_t33);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = t + t;
        return squad_unsafe_fma_sf2488599_1(dest, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t0, _t7, _t41, _t47, _t51, _t41 > 2.0E-6f ? java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t23, 1.0f), _t51, Math.cosWithSin(_t33)) : 1.0f - _t47, _selfx, _selfy, _selfz, _selfw, _t1, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t0 * _t1);
    }

    /** Piece 2 of {@code squad_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_fma_sf2488599_1(long dest, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, float _t0, float _t7, float _t41, float _t47, float _t51, float _t57, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t8, float _t9, float _t10, float _t11, float _t12) {
        float _t24 = Math.min(4.0f, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
        float _t28 = 4.0f - _t24;
        float _t34 = _t7 * quatArcAngle_fma(_t24);
        float _t43 = (float) java.lang.Math.sqrt(_t28 * _t24);
        float _t48 = _t24 > _t28 ? _t7 : 0.0f;
        float _t52 = 2.0f / _t43 * Math.sinWithCos(_t34);
        float _t55 = _t41 > 2.0E-6f ? _t51 : _t47;
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t52;
            _t58 = java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t24, 1.0f), _t52, Math.cosWithSin(_t34));
        } else {
            _t56 = _t48;
            _t58 = 1.0f - _t48;
        }
        float _t59, _t60, _t61, _t62;
        if (t < 0.5f) {
            _t59 = _t57;
            _t60 = _t55;
            _t61 = _t58;
            _t62 = _t56;
        } else {
            _t59 = _t55;
            _t60 = _t57;
            _t61 = _t56;
            _t62 = _t58;
        }
        return squad_unsafe_fma_sf2488599_2(dest, control0Y, control1Y, targetY, _selfy, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0f)), _t59, _t60, _t61, _t62, java.lang.Math.fma(control0X, _t59, control1X * _t60), java.lang.Math.fma(control0W, _t59, control1W * _t60), java.lang.Math.fma(_selfw, _t61, targetW * _t62), java.lang.Math.fma(control0Z, _t59, control1Z * _t60), java.lang.Math.fma(_selfz, _t61, targetZ * _t62), java.lang.Math.fma(_selfx, _t61, targetX * _t62));
    }

    /** Piece 3 of {@code squad_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_fma_sf2488599_2(long dest, float control0Y, float control1Y, float targetY, float _selfy, float _t12, float _t16, float _t59, float _t60, float _t61, float _t62, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76) {
        float _t77 = java.lang.Math.fma(control0Y, _t59, control1Y * _t60);
        float _t78 = java.lang.Math.fma(_selfy, _t61, targetY * _t62);
        float _t79 = _t72 + _t73;
        float _t80 = _t74 + _t75;
        float _t81 = _t71 + _t76;
        float _t82 = _t77 + _t78;
        float _t87 = Math.min(4.0f, java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82))));
        float _t89 = 4.0f - _t87;
        float _t92 = _t16 * quatArcAngle_fma(_t87);
        float _t96 = (float) java.lang.Math.sqrt(_t89 * _t87);
        float _t99 = _t87 > _t89 ? _t16 : 0.0f;
        float _t101 = 2.0f / _t96 * Math.sinWithCos(_t92);
        float _t103, _t104;
        if (_t96 > 2.0E-6f) {
            _t103 = _t101;
            _t104 = java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t87, 1.0f), _t101, Math.cosWithSin(_t92));
        } else {
            _t103 = _t99;
            _t104 = 1.0f - _t99;
        }
        float _t105, _t106;
        if (_t12 < 0.5f) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t71, _t105, _t76 * _t106));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t77, _t105, _t78 * _t106));
        return squad_unsafe_fma_sf2488599_3(dest, _t72, _t73, _t74, _t75, _t105, _t106);
    }

    /** Piece 4 of {@code squad_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_fma_sf2488599_3(long dest, float _t72, float _t73, float _t74, float _t75, float _t105, float _t106) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t74, _t105, _t75 * _t106));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t72, _t105, _t73 * _t106));
        return dest;
    }

    /** {@code squad_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long squad_unsafe_mulAdd(long dest, long src, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        float _t0 = 1.0f - t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = Math.min(t, _t0);
        float _t23 = Math.min(4.0f, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6)))))));
        float _t27 = 4.0f - _t23;
        float _t33 = _t7 * quatArcAngle_mulAdd(_t23);
        float _t41 = (float) java.lang.Math.sqrt(_t27 * _t23);
        float _t47 = _t23 > _t27 ? _t7 : 0.0f;
        float _t51 = 2.0f / _t41 * Math.sinWithCos(_t33);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = t + t;
        float _t12 = _t0 * _t1;
        return squad_unsafe_mulAdd_s749ec21c_1(dest, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t7, _t41, _t47, _t51, _t41 > 2.0E-6f ? ((((1.0f) - (0.5f) * (_t23))) * (_t51) + (Math.cosWithSin(_t33))) : 1.0f - _t47, _selfx, _selfy, _selfz, _selfw, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t12, Math.min(_t12, ((1.0f) - (_t0) * (_t1))));
    }

    /** Piece 2 of {@code squad_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_mulAdd_s749ec21c_1(long dest, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, float _t7, float _t41, float _t47, float _t51, float _t57, float _selfx, float _selfy, float _selfz, float _selfw, float _t8, float _t9, float _t10, float _t11, float _t12, float _t16) {
        float _t24 = Math.min(4.0f, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
        float _t28 = 4.0f - _t24;
        float _t34 = _t7 * quatArcAngle_mulAdd(_t24);
        float _t43 = (float) java.lang.Math.sqrt(_t28 * _t24);
        float _t48 = _t24 > _t28 ? _t7 : 0.0f;
        float _t52 = 2.0f / _t43 * Math.sinWithCos(_t34);
        float _t55 = _t41 > 2.0E-6f ? _t51 : _t47;
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t52;
            _t58 = ((((1.0f) - (0.5f) * (_t24))) * (_t52) + (Math.cosWithSin(_t34)));
        } else {
            _t56 = _t48;
            _t58 = 1.0f - _t48;
        }
        float _t59, _t60, _t61, _t62;
        if (t < 0.5f) {
            _t59 = _t57;
            _t60 = _t55;
            _t61 = _t58;
            _t62 = _t56;
        } else {
            _t59 = _t55;
            _t60 = _t57;
            _t61 = _t56;
            _t62 = _t58;
        }
        float _t72 = ((control0W) * (_t59) + (control1W * _t60));
        float _t73 = ((_selfw) * (_t61) + (targetW * _t62));
        return squad_unsafe_mulAdd_s749ec21c_2(dest, _t12, _t16, ((control0X) * (_t59) + (control1X * _t60)), _t72, _t73, ((control0Z) * (_t59) + (control1Z * _t60)), ((_selfz) * (_t61) + (targetZ * _t62)), ((_selfx) * (_t61) + (targetX * _t62)), ((control0Y) * (_t59) + (control1Y * _t60)), ((_selfy) * (_t61) + (targetY * _t62)), _t72 + _t73);
    }

    /** Piece 3 of {@code squad_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_mulAdd_s749ec21c_2(long dest, float _t12, float _t16, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t79) {
        float _t80 = _t74 + _t75;
        float _t81 = _t71 + _t76;
        float _t82 = _t77 + _t78;
        float _t87 = Math.min(4.0f, ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82)))))));
        float _t89 = 4.0f - _t87;
        float _t92 = _t16 * quatArcAngle_mulAdd(_t87);
        float _t96 = (float) java.lang.Math.sqrt(_t89 * _t87);
        float _t99 = _t87 > _t89 ? _t16 : 0.0f;
        float _t101 = 2.0f / _t96 * Math.sinWithCos(_t92);
        float _t103, _t104;
        if (_t96 > 2.0E-6f) {
            _t103 = _t101;
            _t104 = ((((1.0f) - (0.5f) * (_t87))) * (_t101) + (Math.cosWithSin(_t92)));
        } else {
            _t103 = _t99;
            _t104 = 1.0f - _t99;
        }
        float _t105, _t106;
        if (_t12 < 0.5f) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_t71) * (_t105) + (_t76 * _t106)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t77) * (_t105) + (_t78 * _t106)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t74) * (_t105) + (_t75 * _t106)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t72) * (_t105) + (_t73 * _t106)));
        return dest;
    }

    public static long squad_unsafe(long dest, long src, long control0, long control1, long target, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return squad_unsafe_fma(dest, src, control0, control1, target, t);
        return squad_unsafe_mulAdd(dest, src, control0, control1, target, t);
    }

    /** {@code squad_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long squad_unsafe_fma(long dest, long src, long control0, long control1, long target, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _control0x = UnsafeOpsHolder.U.getFloat(control0);
        float _control0y = UnsafeOpsHolder.U.getFloat(control0 + 4L);
        float _control0z = UnsafeOpsHolder.U.getFloat(control0 + 8L);
        float _control0w = UnsafeOpsHolder.U.getFloat(control0 + 12L);
        float _control1x = UnsafeOpsHolder.U.getFloat(control1);
        float _control1y = UnsafeOpsHolder.U.getFloat(control1 + 4L);
        float _control1z = UnsafeOpsHolder.U.getFloat(control1 + 8L);
        float _control1w = UnsafeOpsHolder.U.getFloat(control1 + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t0 = 1.0f - t;
        return squad_unsafe_fma_s8dc4f73_1(dest, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, t + t, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, Math.min(t, _t0), _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety);
    }

    /** Piece 2 of {@code squad_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_fma_s8dc4f73_1(long dest, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t0, float _t1, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11) {
        float _t12 = _t0 * _t1;
        float _t23 = Math.min(4.0f, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6))));
        float _t24 = Math.min(4.0f, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
        float _t27 = 4.0f - _t23;
        float _t28 = 4.0f - _t24;
        float _t33 = _t7 * quatArcAngle_fma(_t23);
        float _t34 = _t7 * quatArcAngle_fma(_t24);
        float _t41 = (float) java.lang.Math.sqrt(_t27 * _t23);
        float _t43 = (float) java.lang.Math.sqrt(_t28 * _t24);
        float _t47 = _t23 > _t27 ? _t7 : 0.0f;
        float _t51 = 2.0f / _t41 * Math.sinWithCos(_t33);
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t51;
            _t57 = java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t23, 1.0f), _t51, Math.cosWithSin(_t33));
        } else {
            _t55 = _t47;
            _t57 = 1.0f - _t47;
        }
        return squad_unsafe_fma_s8dc4f73_2(dest, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0f)), _t24, _t34, _t43, _t24 > _t28 ? _t7 : 0.0f, 2.0f / _t43 * Math.sinWithCos(_t34), _t55, _t57);
    }

    /** Piece 3 of {@code squad_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_fma_s8dc4f73_2(long dest, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t12, float _t16, float _t24, float _t34, float _t43, float _t48, float _t52, float _t55, float _t57) {
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t52;
            _t58 = java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t24, 1.0f), _t52, Math.cosWithSin(_t34));
        } else {
            _t56 = _t48;
            _t58 = 1.0f - _t48;
        }
        float _t59, _t60, _t61, _t62;
        if (t < 0.5f) {
            _t59 = _t57;
            _t60 = _t55;
            _t61 = _t58;
            _t62 = _t56;
        } else {
            _t59 = _t55;
            _t60 = _t57;
            _t61 = _t56;
            _t62 = _t58;
        }
        float _t71 = java.lang.Math.fma(_control0x, _t59, _control1x * _t60);
        float _t72 = java.lang.Math.fma(_control0w, _t59, _control1w * _t60);
        float _t73 = java.lang.Math.fma(_selfw, _t61, _targetw * _t62);
        float _t74 = java.lang.Math.fma(_control0z, _t59, _control1z * _t60);
        float _t75 = java.lang.Math.fma(_selfz, _t61, _targetz * _t62);
        float _t76 = java.lang.Math.fma(_selfx, _t61, _targetx * _t62);
        float _t77 = java.lang.Math.fma(_control0y, _t59, _control1y * _t60);
        float _t78 = java.lang.Math.fma(_selfy, _t61, _targety * _t62);
        float _t79 = _t72 + _t73;
        float _t80 = _t74 + _t75;
        float _t81 = _t71 + _t76;
        float _t82 = _t77 + _t78;
        float _t87 = Math.min(4.0f, java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82))));
        return squad_unsafe_fma_s8dc4f73_3(dest, _t12, _t16, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t87, 4.0f - _t87);
    }

    /** Piece 4 of {@code squad_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_fma_s8dc4f73_3(long dest, float _t12, float _t16, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t87, float _t89) {
        float _t92 = _t16 * quatArcAngle_fma(_t87);
        float _t96 = (float) java.lang.Math.sqrt(_t89 * _t87);
        float _t99 = _t87 > _t89 ? _t16 : 0.0f;
        float _t101 = 2.0f / _t96 * Math.sinWithCos(_t92);
        float _t103, _t104;
        if (_t96 > 2.0E-6f) {
            _t103 = _t101;
            _t104 = java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t87, 1.0f), _t101, Math.cosWithSin(_t92));
        } else {
            _t103 = _t99;
            _t104 = 1.0f - _t99;
        }
        float _t105, _t106;
        if (_t12 < 0.5f) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t71, _t105, _t76 * _t106));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t77, _t105, _t78 * _t106));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t74, _t105, _t75 * _t106));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t72, _t105, _t73 * _t106));
        return dest;
    }

    /** {@code squad_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long squad_unsafe_mulAdd(long dest, long src, long control0, long control1, long target, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _control0x = UnsafeOpsHolder.U.getFloat(control0);
        float _control0y = UnsafeOpsHolder.U.getFloat(control0 + 4L);
        float _control0z = UnsafeOpsHolder.U.getFloat(control0 + 8L);
        float _control0w = UnsafeOpsHolder.U.getFloat(control0 + 12L);
        float _control1x = UnsafeOpsHolder.U.getFloat(control1);
        float _control1y = UnsafeOpsHolder.U.getFloat(control1 + 4L);
        float _control1z = UnsafeOpsHolder.U.getFloat(control1 + 8L);
        float _control1w = UnsafeOpsHolder.U.getFloat(control1 + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t0 = 1.0f - t;
        return squad_unsafe_mulAdd_s1f58adbc_1(dest, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, t + t, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, Math.min(t, _t0), _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety);
    }

    /** Piece 2 of {@code squad_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_mulAdd_s1f58adbc_1(long dest, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t0, float _t1, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11) {
        float _t12 = _t0 * _t1;
        float _t23 = Math.min(4.0f, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6)))))));
        float _t24 = Math.min(4.0f, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
        float _t27 = 4.0f - _t23;
        float _t28 = 4.0f - _t24;
        float _t33 = _t7 * quatArcAngle_mulAdd(_t23);
        float _t34 = _t7 * quatArcAngle_mulAdd(_t24);
        float _t41 = (float) java.lang.Math.sqrt(_t27 * _t23);
        float _t43 = (float) java.lang.Math.sqrt(_t28 * _t24);
        float _t47 = _t23 > _t27 ? _t7 : 0.0f;
        float _t51 = 2.0f / _t41 * Math.sinWithCos(_t33);
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t51;
            _t57 = ((((1.0f) - (0.5f) * (_t23))) * (_t51) + (Math.cosWithSin(_t33)));
        } else {
            _t55 = _t47;
            _t57 = 1.0f - _t47;
        }
        return squad_unsafe_mulAdd_s1f58adbc_2(dest, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t12, Math.min(_t12, ((1.0f) - (_t0) * (_t1))), _t24, _t34, _t43, _t24 > _t28 ? _t7 : 0.0f, 2.0f / _t43 * Math.sinWithCos(_t34), _t55, _t57);
    }

    /** Piece 3 of {@code squad_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_mulAdd_s1f58adbc_2(long dest, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t12, float _t16, float _t24, float _t34, float _t43, float _t48, float _t52, float _t55, float _t57) {
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t52;
            _t58 = ((((1.0f) - (0.5f) * (_t24))) * (_t52) + (Math.cosWithSin(_t34)));
        } else {
            _t56 = _t48;
            _t58 = 1.0f - _t48;
        }
        float _t59, _t60, _t61, _t62;
        if (t < 0.5f) {
            _t59 = _t57;
            _t60 = _t55;
            _t61 = _t58;
            _t62 = _t56;
        } else {
            _t59 = _t55;
            _t60 = _t57;
            _t61 = _t56;
            _t62 = _t58;
        }
        float _t71 = ((_control0x) * (_t59) + (_control1x * _t60));
        float _t72 = ((_control0w) * (_t59) + (_control1w * _t60));
        float _t73 = ((_selfw) * (_t61) + (_targetw * _t62));
        float _t74 = ((_control0z) * (_t59) + (_control1z * _t60));
        float _t75 = ((_selfz) * (_t61) + (_targetz * _t62));
        float _t76 = ((_selfx) * (_t61) + (_targetx * _t62));
        float _t77 = ((_control0y) * (_t59) + (_control1y * _t60));
        float _t78 = ((_selfy) * (_t61) + (_targety * _t62));
        float _t79 = _t72 + _t73;
        float _t80 = _t74 + _t75;
        float _t81 = _t71 + _t76;
        float _t82 = _t77 + _t78;
        float _t87 = Math.min(4.0f, ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82)))))));
        return squad_unsafe_mulAdd_s1f58adbc_3(dest, _t12, _t16, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t87, 4.0f - _t87);
    }

    /** Piece 4 of {@code squad_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_mulAdd_s1f58adbc_3(long dest, float _t12, float _t16, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t87, float _t89) {
        float _t92 = _t16 * quatArcAngle_mulAdd(_t87);
        float _t96 = (float) java.lang.Math.sqrt(_t89 * _t87);
        float _t99 = _t87 > _t89 ? _t16 : 0.0f;
        float _t101 = 2.0f / _t96 * Math.sinWithCos(_t92);
        float _t103, _t104;
        if (_t96 > 2.0E-6f) {
            _t103 = _t101;
            _t104 = ((((1.0f) - (0.5f) * (_t87))) * (_t101) + (Math.cosWithSin(_t92)));
        } else {
            _t103 = _t99;
            _t104 = 1.0f - _t99;
        }
        float _t105, _t106;
        if (_t12 < 0.5f) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_t71) * (_t105) + (_t76 * _t106)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t77) * (_t105) + (_t78 * _t106)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t74) * (_t105) + (_t75 * _t106)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t72) * (_t105) + (_t73 * _t106)));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(otherX, _selfz, otherW * _selfy) + java.lang.Math.fma(otherY, _selfw, otherZ * -_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(otherY, _selfx, otherZ * _selfw) + java.lang.Math.fma(otherW, _selfz, -(otherX * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(otherW, _selfw, otherX * -_selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    /** {@code mul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mul_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherZ) * (_selfy) - (otherY * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((otherX) * (_selfz) + (otherW * _selfy)) + ((otherY) * (_selfw) - (otherZ * _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((otherY) * (_selfx) + (otherZ * _selfw)) + ((otherW) * (_selfz) - (otherX * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz)));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_unsafe_mulAdd(dest, src, other);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_otherx, _selfz, _otherw * _selfy) + java.lang.Math.fma(_othery, _selfw, _otherz * -_selfx));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_othery, _selfx, _otherz * _selfw) + java.lang.Math.fma(_otherw, _selfz, -(_otherx * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_otherw, _selfw, _otherx * -_selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    /** {@code mul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mul_unsafe_mulAdd(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_otherz) * (_selfy) - (_othery * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_otherx) * (_selfz) + (_otherw * _selfy)) + ((_othery) * (_selfw) - (_otherz * _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_othery) * (_selfx) + (_otherz * _selfw)) + ((_otherw) * (_selfz) - (_otherx * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz)));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherY, _selfz, -(otherZ * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(otherY, _selfw, otherZ * _selfx) + java.lang.Math.fma(otherW, _selfy, -otherX * _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(otherX, _selfy, otherW * _selfz) + java.lang.Math.fma(otherZ, _selfw, -(otherY * _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(otherW, _selfw, -otherX * _selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    /** {@code preMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherY) * (_selfz) - (otherZ * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((otherY) * (_selfw) + (otherZ * _selfx)) + ((otherW) * (_selfy) - (otherX * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((otherX) * (_selfy) + (otherW * _selfz)) + ((otherZ) * (_selfw) - (otherY * _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz)));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_unsafe_mulAdd(dest, src, other);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_othery, _selfz, -(_otherz * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_othery, _selfw, _otherz * _selfx) + java.lang.Math.fma(_otherw, _selfy, -_otherx * _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_otherx, _selfy, _otherw * _selfz) + java.lang.Math.fma(_otherz, _selfw, -(_othery * _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_otherw, _selfw, -_otherx * _selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    /** {@code preMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_mulAdd(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_othery) * (_selfz) - (_otherz * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_othery) * (_selfw) + (_otherz * _selfx)) + ((_otherw) * (_selfy) - (_otherx * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_otherx) * (_selfy) + (_otherw * _selfz)) + ((_otherz) * (_selfw) - (_othery * _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz)));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(weight, otherX, _selfx));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(weight, otherY, _selfy));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(weight, otherZ, _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(weight, otherW, _selfw));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((weight) * (otherX) + (_selfx)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((weight) * (otherY) + (_selfy)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((weight) * (otherZ) + (_selfz)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((weight) * (otherW) + (_selfw)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, long other, float weight) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_unsafe_mulAdd(dest, src, other, weight);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(weight, _otherx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(weight, _othery, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(weight, _otherz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(weight, _otherw, _selfw));
        return dest;
    }

    /** {@code addScaled_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long addScaled_unsafe_mulAdd(long dest, long src, long other, float weight) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((weight) * (_otherx) + (_selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((weight) * (_othery) + (_selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((weight) * (_otherz) + (_selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((weight) * (_otherw) + (_selfw)));
        return dest;
    }

    public static float angle_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            return 2.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))), UnsafeOpsHolder.U.getFloat(src + 12L));
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            return 2.0f * Math.atan2((float) java.lang.Math.sqrt(((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))), UnsafeOpsHolder.U.getFloat(src + 12L));
        }
    }

    public static float angleTo_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleTo_unsafe_mulAdd(src, otherX, otherY, otherZ, otherW);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(otherW, _selfw, java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy))) > 0.0f) {
            _t9 = -otherW;
            _t10 = -otherZ;
            _t11 = -otherX;
            _t12 = -otherY;
        } else {
            _t9 = otherW;
            _t10 = otherZ;
            _t11 = otherX;
            _t12 = otherY;
        }
        float _t13 = _selfw - _t9;
        float _t14 = _selfz - _t10;
        float _t15 = _selfx - _t11;
        float _t16 = _selfy - _t12;
        float _t17 = _selfw + _t9;
        float _t18 = _selfz + _t10;
        float _t19 = _selfx + _t11;
        float _t20 = _selfy + _t12;
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), (float) java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleTo_unsafe_mulAdd(long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9, _t10, _t11, _t12;
        if (-((otherW) * (_selfw) + (((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))))) > 0.0f) {
            _t9 = -otherW;
            _t10 = -otherZ;
            _t11 = -otherX;
            _t12 = -otherY;
        } else {
            _t9 = otherW;
            _t10 = otherZ;
            _t11 = otherX;
            _t12 = otherY;
        }
        float _t13 = _selfw - _t9;
        float _t14 = _selfz - _t10;
        float _t15 = _selfx - _t11;
        float _t16 = _selfy - _t12;
        float _t17 = _selfw + _t9;
        float _t18 = _selfz + _t10;
        float _t19 = _selfx + _t11;
        float _t20 = _selfy + _t12;
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))))), (float) java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))));
    }

    public static float angleTo_unsafe(long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleTo_unsafe_mulAdd(src, other);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_otherw, _selfw, java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy))) > 0.0f) {
            _t9 = -_otherw;
            _t10 = -_otherz;
            _t11 = -_otherx;
            _t12 = -_othery;
        } else {
            _t9 = _otherw;
            _t10 = _otherz;
            _t11 = _otherx;
            _t12 = _othery;
        }
        float _t13 = _selfw - _t9;
        float _t14 = _selfz - _t10;
        float _t15 = _selfx - _t11;
        float _t16 = _selfy - _t12;
        float _t17 = _selfw + _t9;
        float _t18 = _selfz + _t10;
        float _t19 = _selfx + _t11;
        float _t20 = _selfy + _t12;
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), (float) java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleTo_unsafe_mulAdd(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t9, _t10, _t11, _t12;
        if (-((_otherw) * (_selfw) + (((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))))) > 0.0f) {
            _t9 = -_otherw;
            _t10 = -_otherz;
            _t11 = -_otherx;
            _t12 = -_othery;
        } else {
            _t9 = _otherw;
            _t10 = _otherz;
            _t11 = _otherx;
            _t12 = _othery;
        }
        float _t13 = _selfw - _t9;
        float _t14 = _selfz - _t10;
        float _t15 = _selfx - _t11;
        float _t16 = _selfy - _t12;
        float _t17 = _selfw + _t9;
        float _t18 = _selfz + _t10;
        float _t19 = _selfx + _t11;
        float _t20 = _selfy + _t12;
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))))), (float) java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))));
    }

    public static long axis_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return axis_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
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

    /** {@code axis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long axis_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
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

    public static long calculateW_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, _selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
            UnsafeOpsHolder.U.putFloat(dest + 12L, (float) java.lang.Math.sqrt(Math.max(0.0f, java.lang.Math.fma(-_selfx, _selfx, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(-_selfz, _selfz, 1.0f))))));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            UnsafeOpsHolder.U.putFloat(dest, _selfx);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
            UnsafeOpsHolder.U.putFloat(dest + 12L, (float) java.lang.Math.sqrt(Math.max(0.0f, ((((((1.0f) - (_selfz) * (_selfz))) - (_selfy) * (_selfy))) - (_selfx) * (_selfx)))));
            return dest;
        }
    }

    public static long conjugate_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, -_selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw);
        return dest;
    }

    public static long conjugateBy_unsafe(long dest, long src, float qX, float qY, float qZ, float qW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return conjugateBy_unsafe_fma(dest, src, qX, qY, qZ, qW);
        return conjugateBy_unsafe_mulAdd(dest, src, qX, qY, qZ, qW);
    }

    /** {@code conjugateBy_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long conjugateBy_unsafe_fma(long dest, long src, float qX, float qY, float qZ, float qW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t20 = java.lang.Math.fma(qX, _selfy, qW * _selfz) + java.lang.Math.fma(qZ, _selfw, qY * -_selfx);
        float _t21 = java.lang.Math.fma(qY, _selfw, qZ * _selfx) + java.lang.Math.fma(qW, _selfy, -(qX * _selfz));
        float _t22 = java.lang.Math.fma(qX, _selfw, qW * _selfx) + java.lang.Math.fma(qY, _selfz, -(qZ * _selfy));
        float _t23 = java.lang.Math.fma(qW, _selfw, qX * -_selfx) - java.lang.Math.fma(qY, _selfy, qZ * _selfz);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(qY, _t20, -qZ * _t21) + java.lang.Math.fma(qW, _t22, -qX * _t23));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(qZ, _t22, -qY * _t23) + java.lang.Math.fma(qW, _t21, -qX * _t20));
        return conjugateBy_unsafe_fma_sc73a58f2_1(dest, qX, qY, qZ, qW, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long conjugateBy_unsafe_fma_sc73a58f2_1(long dest, float qX, float qY, float qZ, float qW, float _t20, float _t21, float _t22, float _t23) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(qX, _t21, qW * _t20) + java.lang.Math.fma(-qY, _t22, -qZ * _t23));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(qX, _t22, qW * _t23) - java.lang.Math.fma(-qZ, _t20, -qY * _t21));
        return dest;
    }

    /** {@code conjugateBy_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long conjugateBy_unsafe_mulAdd(long dest, long src, float qX, float qY, float qZ, float qW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t20 = ((qX) * (_selfy) + (qW * _selfz)) + ((qZ) * (_selfw) - (qY * _selfx));
        float _t21 = ((qY) * (_selfw) + (qZ * _selfx)) + ((qW) * (_selfy) - (qX * _selfz));
        float _t22 = ((qX) * (_selfw) + (qW * _selfx)) + ((qY) * (_selfz) - (qZ * _selfy));
        float _t23 = ((qW) * (_selfw) - (qX * _selfx)) - ((qY) * (_selfy) + (qZ * _selfz));
        UnsafeOpsHolder.U.putFloat(dest, ((qY) * (_t20) - (qZ * _t21)) + ((qW) * (_t22) - (qX * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((qZ) * (_t22) - (qY * _t23)) + ((qW) * (_t21) - (qX * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((qX) * (_t21) + (qW * _t20)) + ((-qY) * (_t22) - (qZ * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((qX) * (_t22) + (qW * _t23)) - ((-qZ) * (_t20) - (qY * _t21)));
        return dest;
    }

    public static long conjugateBy_unsafe(long dest, long src, long q) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return conjugateBy_unsafe_fma(dest, src, q);
        return conjugateBy_unsafe_mulAdd(dest, src, q);
    }

    /** {@code conjugateBy_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long conjugateBy_unsafe_fma(long dest, long src, long q) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _qx = UnsafeOpsHolder.U.getFloat(q);
        float _qy = UnsafeOpsHolder.U.getFloat(q + 4L);
        float _qz = UnsafeOpsHolder.U.getFloat(q + 8L);
        float _qw = UnsafeOpsHolder.U.getFloat(q + 12L);
        float _t20 = java.lang.Math.fma(_qx, _selfy, _qw * _selfz) + java.lang.Math.fma(_qz, _selfw, _qy * -_selfx);
        float _t21 = java.lang.Math.fma(_qy, _selfw, _qz * _selfx) + java.lang.Math.fma(_qw, _selfy, -(_qx * _selfz));
        float _t22 = java.lang.Math.fma(_qx, _selfw, _qw * _selfx) + java.lang.Math.fma(_qy, _selfz, -(_qz * _selfy));
        float _t23 = java.lang.Math.fma(_qw, _selfw, _qx * -_selfx) - java.lang.Math.fma(_qy, _selfy, _qz * _selfz);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_qy, _t20, -_qz * _t21) + java.lang.Math.fma(_qw, _t22, -_qx * _t23));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_qz, _t22, -_qy * _t23) + java.lang.Math.fma(_qw, _t21, -_qx * _t20));
        return conjugateBy_unsafe_fma_s30370fa7_1(dest, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long conjugateBy_unsafe_fma_s30370fa7_1(long dest, float _qx, float _qy, float _qz, float _qw, float _t20, float _t21, float _t22, float _t23) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_qx, _t21, _qw * _t20) + java.lang.Math.fma(-_qy, _t22, -_qz * _t23));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_qx, _t22, _qw * _t23) - java.lang.Math.fma(-_qz, _t20, -_qy * _t21));
        return dest;
    }

    /** {@code conjugateBy_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long conjugateBy_unsafe_mulAdd(long dest, long src, long q) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _qx = UnsafeOpsHolder.U.getFloat(q);
        float _qy = UnsafeOpsHolder.U.getFloat(q + 4L);
        float _qz = UnsafeOpsHolder.U.getFloat(q + 8L);
        float _qw = UnsafeOpsHolder.U.getFloat(q + 12L);
        float _t20 = ((_qx) * (_selfy) + (_qw * _selfz)) + ((_qz) * (_selfw) - (_qy * _selfx));
        float _t21 = ((_qy) * (_selfw) + (_qz * _selfx)) + ((_qw) * (_selfy) - (_qx * _selfz));
        float _t22 = ((_qx) * (_selfw) + (_qw * _selfx)) + ((_qy) * (_selfz) - (_qz * _selfy));
        float _t23 = ((_qw) * (_selfw) - (_qx * _selfx)) - ((_qy) * (_selfy) + (_qz * _selfz));
        UnsafeOpsHolder.U.putFloat(dest, ((_qy) * (_t20) - (_qz * _t21)) + ((_qw) * (_t22) - (_qx * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_qz) * (_t22) - (_qy * _t23)) + ((_qw) * (_t21) - (_qx * _t20)));
        return conjugateBy_unsafe_mulAdd_s1a35192_1(dest, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long conjugateBy_unsafe_mulAdd_s1a35192_1(long dest, float _qx, float _qy, float _qz, float _qw, float _t20, float _t21, float _t22, float _t23) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_qx) * (_t21) + (_qw * _t20)) + ((-_qy) * (_t22) - (_qz * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_qx) * (_t22) + (_qw * _t23)) - ((-_qz) * (_t20) - (_qy * _t21)));
        return dest;
    }

    public static long difference_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return difference_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3_inv = 1.0f / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        UnsafeOpsHolder.U.putFloat(dest, (java.lang.Math.fma(otherX, _selfw, -otherW * _selfx) + java.lang.Math.fma(otherY, _selfz, -(otherZ * _selfy))) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -otherW * _sp0 - otherX * _sp1 + java.lang.Math.fma(otherY, _selfw, otherZ * _selfx) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, (java.lang.Math.fma(otherX, _selfy, -otherW * _selfz) + java.lang.Math.fma(otherZ, _selfw, -otherY * _selfx)) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(otherX, _selfx, otherW * _selfw) * _t3_inv - (-otherY * _sp0 - otherZ * _sp1));
        return dest;
    }

    /** {@code difference_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long difference_unsafe_mulAdd(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3_inv = 1.0f / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        UnsafeOpsHolder.U.putFloat(dest, (((otherX) * (_selfw) - (otherW * _selfx)) + ((otherY) * (_selfz) - (otherZ * _selfy))) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -otherW * _sp0 - otherX * _sp1 + ((otherY) * (_selfw) + (otherZ * _selfx)) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, (((otherX) * (_selfy) - (otherW * _selfz)) + ((otherZ) * (_selfw) - (otherY * _selfx))) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((otherX) * (_selfx) + (otherW * _selfw)) * _t3_inv - (-otherY * _sp0 - otherZ * _sp1));
        return dest;
    }

    public static long difference_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return difference_unsafe_fma(dest, src, other);
        return difference_unsafe_mulAdd(dest, src, other);
    }

    /** {@code difference_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long difference_unsafe_fma(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t3_inv = 1.0f / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        UnsafeOpsHolder.U.putFloat(dest, (java.lang.Math.fma(_otherx, _selfw, -_otherw * _selfx) + java.lang.Math.fma(_othery, _selfz, -(_otherz * _selfy))) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_otherw * _sp0 - _otherx * _sp1 + java.lang.Math.fma(_othery, _selfw, _otherz * _selfx) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, (java.lang.Math.fma(_otherx, _selfy, -_otherw * _selfz) + java.lang.Math.fma(_otherz, _selfw, -_othery * _selfx)) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_otherx, _selfx, _otherw * _selfw) * _t3_inv - (-_othery * _sp0 - _otherz * _sp1));
        return dest;
    }

    /** {@code difference_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long difference_unsafe_mulAdd(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t3_inv = 1.0f / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        UnsafeOpsHolder.U.putFloat(dest, (((_otherx) * (_selfw) - (_otherw * _selfx)) + ((_othery) * (_selfz) - (_otherz * _selfy))) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_otherw * _sp0 - _otherx * _sp1 + ((_othery) * (_selfw) + (_otherz * _selfx)) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, (((_otherx) * (_selfy) - (_otherw * _selfz)) + ((_otherz) * (_selfw) - (_othery * _selfx))) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_otherx) * (_selfx) + (_otherw * _selfw)) * _t3_inv - (-_othery * _sp0 - _otherz * _sp1));
        return dest;
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return exp_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t2 = Math.min(Math.exp(UnsafeOpsHolder.U.getFloat(src + 12L)), 3.4028235E38f);
        float _t4 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        float _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
        if (_t4 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t10);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t10);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t10);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosWithSin(_t5) * _t2);
        return dest;
    }

    /** {@code exp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long exp_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t2 = Math.min(Math.exp(UnsafeOpsHolder.U.getFloat(src + 12L)), 3.4028235E38f);
        float _t4 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        float _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
        if (_t4 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t10);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t10);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t10);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosWithSin(_t5) * _t2);
        return dest;
    }

    public static long getEulerAnglesXYZ_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_unsafe_fma(dest, src);
        return getEulerAnglesXYZ_unsafe_mulAdd(dest, src);
    }

    /** {@code getEulerAnglesXYZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_fma(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t11, _t14));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t12, _t13));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t10 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t11 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t12 = 2.0f * ((_selfx) * (_selfw) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t14 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t11, _t14));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t12, _t13));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) + (2.0f * ((_selfx) * (_selfz) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static long getEulerAnglesXYZ_unsafe(long dest, long src, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_unsafe_fma(dest, src, epsilon);
        return getEulerAnglesXYZ_unsafe_mulAdd(dest, src, epsilon);
    }

    /** {@code getEulerAnglesXYZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_fma(long dest, long src, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t11, _t14));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t12, _t13));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_mulAdd(long dest, long src, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t10 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t11 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t12 = 2.0f * ((_selfx) * (_selfw) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t14 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t11, _t14));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t12, _t13));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) + (2.0f * ((_selfx) * (_selfz) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static long getEulerAnglesXZY_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_unsafe_fma(dest, src);
        return getEulerAnglesXZY_unsafe_mulAdd(dest, src);
    }

    /** {@code getEulerAnglesXZY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXZY_unsafe_fma(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        return getEulerAnglesXZY_unsafe_fma_s85ae3751_1(dest, _selfx, _selfy, _selfz, _selfw, _t1, _t9, 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy)), _t11, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesXZY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesXZY_unsafe_fma_s85ae3751_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1), _t12));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t9, _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * -_selfw)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(_selfy, _selfz, _selfx * -_selfw))));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXZY_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t10 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        float _t12 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(2.0f * ((_selfx) * (_selfw) - (_t1)), _t12));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(((2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) * (_t9) - (_t11 * 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)))), ((_t12) * (_t11) - (_t9 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw))))));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static long getEulerAnglesXZY_unsafe(long dest, long src, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_unsafe_fma(dest, src, epsilon);
        return getEulerAnglesXZY_unsafe_mulAdd(dest, src, epsilon);
    }

    /** {@code getEulerAnglesXZY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXZY_unsafe_fma(long dest, long src, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        return getEulerAnglesXZY_unsafe_fma_s3711f277_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t1, _t9, 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy)), _t11, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesXZY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesXZY_unsafe_fma_s3711f277_1(long dest, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1), _t12));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t9, _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * -_selfw)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(_selfy, _selfz, _selfx * -_selfw))));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXZY_unsafe_mulAdd(long dest, long src, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t10 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        float _t12 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(2.0f * ((_selfx) * (_selfw) - (_t1)), _t12));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(((2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) * (_t9) - (_t11 * 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)))), ((_t12) * (_t11) - (_t9 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw))))));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static long getEulerAnglesYXZ_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_unsafe_fma(dest, src);
        return getEulerAnglesYXZ_unsafe_mulAdd(dest, src);
    }

    /** {@code getEulerAnglesYXZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_fma(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        return getEulerAnglesYXZ_unsafe_fma_scba00415_1(dest, _selfx, _selfy, _selfz, _selfw, _t0, _t2, _t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t0), _t11, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesYXZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_fma_scba00415_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t12));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, _t0), _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2))));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t10 = 2.0f * ((_selfx) * (_selfw) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t12 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t12));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(((_t9) * (2.0f * ((_selfx) * (_selfw) + (_t0))) - (_t11 * 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)))), ((_t11) * (_t12) - (_t9 * 2.0f * ((_selfx) * (_selfz) - (_t2))))));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static long getEulerAnglesYXZ_unsafe(long dest, long src, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_unsafe_fma(dest, src, epsilon);
        return getEulerAnglesYXZ_unsafe_mulAdd(dest, src, epsilon);
    }

    /** {@code getEulerAnglesYXZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_fma(long dest, long src, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        return getEulerAnglesYXZ_unsafe_fma_s5196a29b_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t0, _t2, _t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t0), _t11, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesYXZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_fma_s5196a29b_1(long dest, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t12));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, _t0), _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2))));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_mulAdd(long dest, long src, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t10 = 2.0f * ((_selfx) * (_selfw) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t12 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t12));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(((_t9) * (2.0f * ((_selfx) * (_selfw) + (_t0))) - (_t11 * 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)))), ((_t11) * (_t12) - (_t9 * 2.0f * ((_selfx) * (_selfz) - (_t2))))));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static long getEulerAnglesYZX_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_unsafe_fma(dest, src);
        return getEulerAnglesYZX_unsafe_mulAdd(dest, src);
    }

    /** {@code getEulerAnglesYZX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYZX_unsafe_fma(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = _selfz * _selfw;
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t2);
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t13 = 2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t12, _t15));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t13, _t14));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYZX_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = _selfz * _selfw;
        float _t11 = 2.0f * ((_selfx) * (_selfy) + (_t2));
        float _t12 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t13 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        float _t15 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t12, _t15));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(((_t14) * (2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz))) + (2.0f * ((_selfx) * (_selfy) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t13, _t14));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    public static long getEulerAnglesYZX_unsafe(long dest, long src, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_unsafe_fma(dest, src, epsilon);
        return getEulerAnglesYZX_unsafe_mulAdd(dest, src, epsilon);
    }

    /** {@code getEulerAnglesYZX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYZX_unsafe_fma(long dest, long src, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = _selfz * _selfw;
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t2);
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t13 = 2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t12, _t15));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t13, _t14));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYZX_unsafe_mulAdd(long dest, long src, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = _selfz * _selfw;
        float _t11 = 2.0f * ((_selfx) * (_selfy) + (_t2));
        float _t12 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t13 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        float _t15 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t12, _t15));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(((_t14) * (2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz))) + (2.0f * ((_selfx) * (_selfy) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t13, _t14));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    public static long getEulerAnglesZXY_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_unsafe_fma(dest, src);
        return getEulerAnglesZXY_unsafe_mulAdd(dest, src);
    }

    /** {@code getEulerAnglesZXY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZXY_unsafe_fma(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfz * _selfz;
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t12 = 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t11, _t14));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t12, _t13));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZXY_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfz * _selfz;
        float _t10 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t11 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t12 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t11, _t14));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) + (2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t12, _t13));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static long getEulerAnglesZXY_unsafe(long dest, long src, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_unsafe_fma(dest, src, epsilon);
        return getEulerAnglesZXY_unsafe_mulAdd(dest, src, epsilon);
    }

    /** {@code getEulerAnglesZXY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZXY_unsafe_fma(long dest, long src, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfz * _selfz;
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t12 = 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t11, _t14));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t12, _t13));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZXY_unsafe_mulAdd(long dest, long src, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfz * _selfz;
        float _t10 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t11 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t12 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t11, _t14));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) + (2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t12, _t13));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static long getEulerAnglesZYX_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_unsafe_fma(dest, src);
        return getEulerAnglesZYX_unsafe_mulAdd(dest, src);
    }

    /** {@code getEulerAnglesZYX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZYX_unsafe_fma(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f);
        return getEulerAnglesZYX_unsafe_fma_sa9b48487_1(dest, _selfx, _selfy, _selfz, _selfw, _t1, _t8, 2.0f * java.lang.Math.fma(_selfy, _selfw, -_selfx * _selfz), _t10, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f), java.lang.Math.fma(_t10, _t10, _t8 * _t8));
    }

    /** Piece 2 of {@code getEulerAnglesZYX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesZYX_unsafe_fma_sa9b48487_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t8, float _t9, float _t10, float _t11, float _t13) {
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(2.0f * java.lang.Math.fma(_selfz, _selfw, -_selfx * _selfy), _t11));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t10 * -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t8, _t10));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZYX_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * ((_selfx) * (_selfy) + (_t1));
        float _t9 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t10 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 2.2737368E-13f) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(((_t8) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) - (_t10 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)))), ((_t11) * (_t10) - (_t8 * 2.0f * ((_selfx) * (_selfy) - (_t1))))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t8, _t10));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    public static long getEulerAnglesZYX_unsafe(long dest, long src, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_unsafe_fma(dest, src, epsilon);
        return getEulerAnglesZYX_unsafe_mulAdd(dest, src, epsilon);
    }

    /** {@code getEulerAnglesZYX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZYX_unsafe_fma(long dest, long src, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f);
        return getEulerAnglesZYX_unsafe_fma_s45e5ea9d_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t1, _t8, 2.0f * java.lang.Math.fma(_selfy, _selfw, -_selfx * _selfz), _t10, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f), java.lang.Math.fma(_t10, _t10, _t8 * _t8));
    }

    /** Piece 2 of {@code getEulerAnglesZYX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesZYX_unsafe_fma_s45e5ea9d_1(long dest, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t8, float _t9, float _t10, float _t11, float _t13) {
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(2.0f * java.lang.Math.fma(_selfz, _selfw, -_selfx * _selfy), _t11));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t10 * -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t8, _t10));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZYX_unsafe_mulAdd(long dest, long src, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * ((_selfx) * (_selfy) + (_t1));
        float _t9 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t10 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(((_t8) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) - (_t10 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)))), ((_t11) * (_t10) - (_t8 * 2.0f * ((_selfx) * (_selfy) - (_t1))))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t8, _t10));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    public static long integrate_unsafe(long dest, long src, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return integrate_unsafe_fma(dest, src, angularVelX, angularVelY, angularVelZ, dt);
        return integrate_unsafe_mulAdd(dest, src, angularVelX, angularVelY, angularVelZ, dt);
    }

    /** {@code integrate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long integrate_unsafe_fma(long dest, long src, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.cosWithSin(_t7);
        float _t11 = Math.sinWithCos(_t7) / _t7;
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t15, _t16, _t17;
        if (_t6 > 0.0f) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t9, _selfw * _t15) + java.lang.Math.fma(_selfz, _t16, -(_selfy * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfx, _t17, _selfw * _t16) + java.lang.Math.fma(_selfy, _t9, -(_selfz * _t15)));
        return integrate_unsafe_fma_s71bfe01c_1(dest, _t9, _selfx, _selfy, _selfz, _selfw, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long integrate_unsafe_fma_s71bfe01c_1(long dest, float _t9, float _selfx, float _selfy, float _selfz, float _selfw, float _t15, float _t16, float _t17) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfy, _t15, _selfz * _t9) + java.lang.Math.fma(_selfw, _t17, -_selfx * _t16));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t9, -_selfx * _t15) - java.lang.Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    /** {@code integrate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long integrate_unsafe_mulAdd(long dest, long src, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.cosWithSin(_t7);
        float _t11 = Math.sinWithCos(_t7) / _t7;
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t15, _t16, _t17;
        if (_t6 > 0.0f) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t9) + (_selfw * _t15)) + ((_selfz) * (_t16) - (_selfy * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfx) * (_t17) + (_selfw * _t16)) + ((_selfy) * (_t9) - (_selfz * _t15)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfy) * (_t15) + (_selfz * _t9)) + ((_selfw) * (_t17) - (_selfx * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t9) - (_selfx * _t15)) - ((_selfy) * (_t16) + (_selfz * _t17)));
        return dest;
    }

    public static long integrate_unsafe(long dest, long src, long angularVel, float dt) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return integrate_unsafe_fma(dest, src, angularVel, dt);
        return integrate_unsafe_mulAdd(dest, src, angularVel, dt);
    }

    /** {@code integrate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long integrate_unsafe_fma(long dest, long src, long angularVel, float dt) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * dt;
        float _t1 = UnsafeOpsHolder.U.getFloat(angularVel + 8L) * _t0;
        float _t2 = UnsafeOpsHolder.U.getFloat(angularVel) * _t0;
        float _t3 = UnsafeOpsHolder.U.getFloat(angularVel + 4L) * _t0;
        float _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.cosWithSin(_t7);
        float _t11 = Math.sinWithCos(_t7) / _t7;
        float _t15, _t16, _t17;
        if (_t6 > 0.0f) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t9, _selfw * _t15) + java.lang.Math.fma(_selfz, _t16, -(_selfy * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfx, _t17, _selfw * _t16) + java.lang.Math.fma(_selfy, _t9, -(_selfz * _t15)));
        return integrate_unsafe_fma_s1addb2d7_1(dest, _selfx, _selfy, _selfz, _selfw, _t9, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long integrate_unsafe_fma_s1addb2d7_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t15, float _t16, float _t17) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfy, _t15, _selfz * _t9) + java.lang.Math.fma(_selfw, _t17, -_selfx * _t16));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t9, -_selfx * _t15) - java.lang.Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    /** {@code integrate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long integrate_unsafe_mulAdd(long dest, long src, long angularVel, float dt) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * dt;
        float _t1 = UnsafeOpsHolder.U.getFloat(angularVel + 8L) * _t0;
        float _t2 = UnsafeOpsHolder.U.getFloat(angularVel) * _t0;
        float _t3 = UnsafeOpsHolder.U.getFloat(angularVel + 4L) * _t0;
        float _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.cosWithSin(_t7);
        float _t11 = Math.sinWithCos(_t7) / _t7;
        float _t15, _t16, _t17;
        if (_t6 > 0.0f) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t9) + (_selfw * _t15)) + ((_selfz) * (_t16) - (_selfy * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfx) * (_t17) + (_selfw * _t16)) + ((_selfy) * (_t9) - (_selfz * _t15)));
        return integrate_unsafe_mulAdd_s785a69e4_1(dest, _selfx, _selfy, _selfz, _selfw, _t9, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long integrate_unsafe_mulAdd_s785a69e4_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t15, float _t16, float _t17) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfy) * (_t15) + (_selfz * _t9)) + ((_selfw) * (_t17) - (_selfx * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t9) - (_selfx * _t15)) - ((_selfy) * (_t16) + (_selfz * _t17)));
        return dest;
    }

    public static long invNegativeX_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeX_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t12 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t10 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t9 * _t16));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    /** {@code invNegativeX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invNegativeX_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t12 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t10 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t9 * _t16));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    public static long invNegativeY_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeY_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t9 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t12 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t10 * _t16));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    /** {@code invNegativeY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invNegativeY_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t9 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t12 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t10 * _t16));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    public static long invNegativeZ_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeZ_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t10 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t9 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t12 * _t16));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    /** {@code invNegativeZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invNegativeZ_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t10 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t9 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t12 * _t16));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    public static long invNormalizedNegativeX_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))) - (1.0f)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            return dest;
        }
    }

    public static long invNormalizedNegativeY_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, -2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, -2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))) - (1.0f)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            return dest;
        }
    }

    public static long invNormalizedNegativeZ_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, -2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, -2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))) - (1.0f)));
            return dest;
        }
    }

    public static long invNormalizedPositiveX_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            return dest;
        }
    }

    public static long invNormalizedPositiveY_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz)))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            return dest;
        }
    }

    public static long invNormalizedPositiveZ_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
            return dest;
        }
    }

    public static long invPositiveX_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveX_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t12 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t10 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t9 * _t16);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code invPositiveX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invPositiveX_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t12 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t10 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t9 * _t16);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long invPositiveY_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveY_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t9 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t12 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t10 * _t16);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code invPositiveY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invPositiveY_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t9 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t12 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t10 * _t16);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long invPositiveZ_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveZ_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t10 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t9 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t12 * _t16);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code invPositiveZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invPositiveZ_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t10 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t9 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t12 * _t16);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return log_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t6);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2))));
        return dest;
    }

    /** {@code log_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long log_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t6);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t6);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2)))));
        return dest;
    }

    public static long negativeX_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeX_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -_selfy * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t12 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t9 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t10 * _t16));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    /** {@code negativeX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long negativeX_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t12 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t9 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t10 * _t16));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    public static long negativeY_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeY_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t10 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t12 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t9 * _t16));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    /** {@code negativeY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long negativeY_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t10 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t12 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t9 * _t16));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    public static long negativeZ_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeZ_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t9 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t10 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t12 * _t16));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
        return dest;
    }

    /** {@code negativeZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long negativeZ_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, -(_t9 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t10 * _t16));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t12 * _t16));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, -0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, -0.0f);
        }
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

    public static long normalizedNegativeX_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))) - (1.0f)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            return dest;
        }
    }

    public static long normalizedNegativeY_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, -2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))) - (1.0f)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, -2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            return dest;
        }
    }

    public static long normalizedNegativeZ_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, -2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, -2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))) - (1.0f)));
            return dest;
        }
    }

    public static long normalizedPositiveX_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz)))));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            return dest;
        }
    }

    public static long normalizedPositiveY_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz)))));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            return dest;
        }
    }

    public static long normalizedPositiveZ_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
            return dest;
        } else {
            float _selfx = UnsafeOpsHolder.U.getFloat(src);
            float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
            return dest;
        }
    }

    public static long positiveX_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveX_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -_selfy * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t12 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t9 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t10 * _t16);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code positiveX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long positiveX_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t12 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t9 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t10 * _t16);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long positiveY_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveY_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t10 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t12 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t9 * _t16);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code positiveY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long positiveY_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t10 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t12 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t9 * _t16);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long positiveZ_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveZ_unsafe_mulAdd(dest, src);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t9 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t10 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t12 * _t16);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    /** {@code positiveZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long positiveZ_unsafe_mulAdd(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t9 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t10 * _t16);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t12 * _t16);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        return dest;
    }

    public static long pow_unsafe(long dest, long src, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return pow_unsafe_fma(dest, src, t);
        return pow_unsafe_mulAdd(dest, src, t);
    }

    /** {@code pow_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long pow_unsafe_fma(long dest, long src, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t12 = Math.min(Math.exp(t == 0.0f ? 0.0f : t * Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2)))), 3.4028235E38f);
        float _t13 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t20, _t21, _t22;
        if (_t2 > 0.0f) {
            _t20 = t * _selfz * _t13;
            _t21 = t * _selfx * _t13;
            _t22 = t * _selfy * _t13;
        } else {
            _t20 = t * 0.0f;
            _t21 = t * 0.0f;
            _t22 = t * 0.0f;
        }
        float _t25 = java.lang.Math.fma(_t20, _t20, java.lang.Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (float) java.lang.Math.sqrt(_t25);
        return pow_unsafe_fma_s4f41aa3d_1(dest, _t12, _t20, _t21, _t22, _t25, _t26, _t12 * (Math.sinWithCos(_t26) / _t26));
    }

    /** Piece 2 of {@code pow_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long pow_unsafe_fma_s4f41aa3d_1(long dest, float _t12, float _t20, float _t21, float _t22, float _t25, float _t26, float _t31) {
        if (_t25 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t21 * _t31);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t22 * _t31);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t20 * _t31);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosWithSin(_t26) * _t12);
        return dest;
    }

    /** {@code pow_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long pow_unsafe_mulAdd(long dest, long src, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t12 = Math.min(Math.exp(t == 0.0f ? 0.0f : t * Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2))))), 3.4028235E38f);
        float _t13 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t20, _t21, _t22;
        if (_t2 > 0.0f) {
            _t20 = t * _selfz * _t13;
            _t21 = t * _selfx * _t13;
            _t22 = t * _selfy * _t13;
        } else {
            _t20 = t * 0.0f;
            _t21 = t * 0.0f;
            _t22 = t * 0.0f;
        }
        float _t25 = ((_t20) * (_t20) + (((_t21) * (_t21) + (_t22 * _t22))));
        float _t26 = (float) java.lang.Math.sqrt(_t25);
        return pow_unsafe_mulAdd_se9a875c_1(dest, _t12, _t20, _t21, _t22, _t25, _t26, _t12 * (Math.sinWithCos(_t26) / _t26));
    }

    /** Piece 2 of {@code pow_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long pow_unsafe_mulAdd_se9a875c_1(long dest, float _t12, float _t20, float _t21, float _t22, float _t25, float _t26, float _t31) {
        if (_t25 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t21 * _t31);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t22 * _t31);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t20 * _t31);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosWithSin(_t26) * _t12);
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float step) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_unsafe_fma(dest, src, targetX, targetY, targetZ, targetW, step);
        return rotateTowards_unsafe_mulAdd(dest, src, targetX, targetY, targetZ, targetW, step);
    }

    /** {@code rotateTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_fma(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float step) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_selfw, targetW, java.lang.Math.fma(_selfz, targetZ, java.lang.Math.fma(_selfx, targetX, _selfy * targetY))) > 0.0f) {
            _t9 = -targetW;
            _t10 = -targetZ;
            _t11 = -targetX;
            _t12 = -targetY;
        } else {
            _t9 = targetW;
            _t10 = targetZ;
            _t11 = targetX;
            _t12 = targetY;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t17 = _selfw - _t9;
        float _t18 = _selfz - _t10;
        float _t19 = _selfx - _t11;
        float _t20 = _selfy - _t12;
        float _t30 = java.lang.Math.fma(_t15, _t15, _t16 * _t16);
        float _t37 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, _t30));
        float _t38 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        return rotateTowards_unsafe_fma_sc2de3c1c_1(dest, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37 * _t38, _t46, 4.0f * _t46);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_sc2de3c1c_1(long dest, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t46, float _t49) {
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t56 = 1.0f - _t55;
        float _t61 = (_t46 + _t46) * _t55;
        float _t65 = 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43));
        float _t74 = java.lang.Math.fma(-0.25f * (_t30 + java.lang.Math.fma(_t14, _t14, _t13 * _t13) + (java.lang.Math.fma(-_t20, _t20, -_t19 * _t19) + java.lang.Math.fma(-_t17, _t17, -_t18 * _t18))), _t65, Math.cosWithSin(_t61));
        float _t79, _t80, _t81, _t82;
        if (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f) {
            _t79 = java.lang.Math.fma(_selfw, _t74, _t65 * _t9);
            _t80 = java.lang.Math.fma(_selfz, _t74, _t65 * _t10);
            _t81 = java.lang.Math.fma(_selfx, _t74, _t65 * _t11);
            _t82 = java.lang.Math.fma(_selfy, _t74, _t65 * _t12);
        } else {
            _t79 = java.lang.Math.fma(_selfw, _t56, _t9 * _t55);
            _t80 = java.lang.Math.fma(_selfz, _t56, _t10 * _t55);
            _t81 = java.lang.Math.fma(_selfx, _t56, _t11 * _t55);
            _t82 = java.lang.Math.fma(_selfy, _t56, _t12 * _t55);
        }
        float _t86 = java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82)));
        return rotateTowards_unsafe_fma_sc2de3c1c_2(dest, _t79, _t80, _t81, _t82, _t86, (1.0f / (float) java.lang.Math.sqrt(_t86)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_sc2de3c1c_2(long dest, float _t79, float _t80, float _t81, float _t82, float _t86, float _t87) {
        if (_t86 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t87 * _t81);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t87 * _t82);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t87 * _t80);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t87 * _t79);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code rotateTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_mulAdd(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float step) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9, _t10, _t11, _t12;
        if (-((_selfw) * (targetW) + (((_selfz) * (targetZ) + (((_selfx) * (targetX) + (_selfy * targetY)))))) > 0.0f) {
            _t9 = -targetW;
            _t10 = -targetZ;
            _t11 = -targetX;
            _t12 = -targetY;
        } else {
            _t9 = targetW;
            _t10 = targetZ;
            _t11 = targetX;
            _t12 = targetY;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t17 = _selfw - _t9;
        float _t18 = _selfz - _t10;
        float _t19 = _selfx - _t11;
        float _t20 = _selfy - _t12;
        float _t30 = ((_t15) * (_t15) + (_t16 * _t16));
        float _t37 = ((_t13) * (_t13) + (((_t14) * (_t14) + (_t30))));
        float _t38 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        return rotateTowards_unsafe_mulAdd_sac2bf7c5_1(dest, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37 * _t38, _t46, 4.0f * _t46);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_sac2bf7c5_1(long dest, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t46, float _t49) {
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t56 = 1.0f - _t55;
        float _t61 = (_t46 + _t46) * _t55;
        float _t65 = 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43));
        float _t74 = ((-0.25f * (_t30 + ((_t14) * (_t14) + (_t13 * _t13)) + (((-_t20) * (_t20) - (_t19 * _t19)) + ((-_t17) * (_t17) - (_t18 * _t18))))) * (_t65) + (Math.cosWithSin(_t61)));
        float _t79, _t80, _t81, _t82;
        if (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f) {
            _t79 = ((_selfw) * (_t74) + (_t65 * _t9));
            _t80 = ((_selfz) * (_t74) + (_t65 * _t10));
            _t81 = ((_selfx) * (_t74) + (_t65 * _t11));
            _t82 = ((_selfy) * (_t74) + (_t65 * _t12));
        } else {
            _t79 = ((_selfw) * (_t56) + (_t9 * _t55));
            _t80 = ((_selfz) * (_t56) + (_t10 * _t55));
            _t81 = ((_selfx) * (_t56) + (_t11 * _t55));
            _t82 = ((_selfy) * (_t56) + (_t12 * _t55));
        }
        float _t86 = ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82))))));
        return rotateTowards_unsafe_mulAdd_sac2bf7c5_2(dest, _t79, _t80, _t81, _t82, _t86, (1.0f / (float) java.lang.Math.sqrt(_t86)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_sac2bf7c5_2(long dest, float _t79, float _t80, float _t81, float _t82, float _t86, float _t87) {
        if (_t86 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t87 * _t81);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t87 * _t82);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t87 * _t80);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t87 * _t79);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, long target, float step) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_unsafe_fma(dest, src, target, step);
        return rotateTowards_unsafe_mulAdd(dest, src, target, step);
    }

    /** {@code rotateTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_fma(long dest, long src, long target, float step) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety))) > 0.0f) {
            _t9 = -_targetw;
            _t10 = -_targetz;
            _t11 = -_targetx;
            _t12 = -_targety;
        } else {
            _t9 = _targetw;
            _t10 = _targetz;
            _t11 = _targetx;
            _t12 = _targety;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t30 = java.lang.Math.fma(_t15, _t15, _t16 * _t16);
        return rotateTowards_unsafe_fma_sb1885abf_1(dest, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _selfw - _t9, _selfz - _t10, _selfx - _t11, _selfy - _t12, _t30, java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, _t30)));
    }

    /** Piece 2 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_sb1885abf_1(long dest, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t37) {
        float _t38 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t43 = _t37 * _t38;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        float _t49 = 4.0f * _t46;
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t61 = (_t46 + _t46) * _t55;
        float _t65 = 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43));
        return rotateTowards_unsafe_fma_sb1885abf_2(dest, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t43, _t55, 1.0f - _t55, _t65, java.lang.Math.fma(-0.25f * (_t30 + java.lang.Math.fma(_t14, _t14, _t13 * _t13) + (java.lang.Math.fma(-_t20, _t20, -_t19 * _t19) + java.lang.Math.fma(-_t17, _t17, -_t18 * _t18))), _t65, Math.cosWithSin(_t61)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_sb1885abf_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t43, float _t55, float _t56, float _t65, float _t74) {
        float _t79, _t80, _t81, _t82;
        if (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f) {
            _t79 = java.lang.Math.fma(_selfw, _t74, _t65 * _t9);
            _t80 = java.lang.Math.fma(_selfz, _t74, _t65 * _t10);
            _t81 = java.lang.Math.fma(_selfx, _t74, _t65 * _t11);
            _t82 = java.lang.Math.fma(_selfy, _t74, _t65 * _t12);
        } else {
            _t79 = java.lang.Math.fma(_selfw, _t56, _t9 * _t55);
            _t80 = java.lang.Math.fma(_selfz, _t56, _t10 * _t55);
            _t81 = java.lang.Math.fma(_selfx, _t56, _t11 * _t55);
            _t82 = java.lang.Math.fma(_selfy, _t56, _t12 * _t55);
        }
        float _t86 = java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82)));
        float _t87 = (1.0f / (float) java.lang.Math.sqrt(_t86));
        if (_t86 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t87 * _t81);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t87 * _t82);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t87 * _t80);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t87 * _t79);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    /** {@code rotateTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_mulAdd(long dest, long src, long target, float step) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t9, _t10, _t11, _t12;
        if (-((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety)))))) > 0.0f) {
            _t9 = -_targetw;
            _t10 = -_targetz;
            _t11 = -_targetx;
            _t12 = -_targety;
        } else {
            _t9 = _targetw;
            _t10 = _targetz;
            _t11 = _targetx;
            _t12 = _targety;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t30 = ((_t15) * (_t15) + (_t16 * _t16));
        return rotateTowards_unsafe_mulAdd_sdc78ce94_1(dest, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _selfw - _t9, _selfz - _t10, _selfx - _t11, _selfy - _t12, _t30, ((_t13) * (_t13) + (((_t14) * (_t14) + (_t30)))));
    }

    /** Piece 2 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_sdc78ce94_1(long dest, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t37) {
        float _t38 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t43 = _t37 * _t38;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        float _t49 = 4.0f * _t46;
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t56 = 1.0f - _t55;
        float _t61 = (_t46 + _t46) * _t55;
        float _t65 = 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43));
        float _t74 = ((-0.25f * (_t30 + ((_t14) * (_t14) + (_t13 * _t13)) + (((-_t20) * (_t20) - (_t19 * _t19)) + ((-_t17) * (_t17) - (_t18 * _t18))))) * (_t65) + (Math.cosWithSin(_t61)));
        float _t79, _t80, _t81, _t82;
        if (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f) {
            _t79 = ((_selfw) * (_t74) + (_t65 * _t9));
            _t80 = ((_selfz) * (_t74) + (_t65 * _t10));
            _t81 = ((_selfx) * (_t74) + (_t65 * _t11));
            _t82 = ((_selfy) * (_t74) + (_t65 * _t12));
        } else {
            _t79 = ((_selfw) * (_t56) + (_t9 * _t55));
            _t80 = ((_selfz) * (_t56) + (_t10 * _t55));
            _t81 = ((_selfx) * (_t56) + (_t11 * _t55));
            _t82 = ((_selfy) * (_t56) + (_t12 * _t55));
        }
        return rotateTowards_unsafe_mulAdd_sdc78ce94_2(dest, _t79, _t80, _t81, _t82);
    }

    /** Piece 3 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_sdc78ce94_2(long dest, float _t79, float _t80, float _t81, float _t82) {
        float _t86 = ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82))))));
        float _t87 = (1.0f / (float) java.lang.Math.sqrt(_t86));
        if (_t86 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t87 * _t81);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t87 * _t82);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t87 * _t80);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t87 * _t79);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long lookAlong_lh(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        FloatQuatOpsKernelsSegment.lookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static long lookAlong_lh_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, upX);
        float _t20 = java.lang.Math.fma(_t18, _t13, upY);
        float _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_unsafe_fma_sc15c32f4_1(dest, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sc15c32f4_1(long dest, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        return lookAlong_lh_unsafe_fma_sc15c32f4_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sc15c32f4_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        return lookAlong_lh_unsafe_fma_sc15c32f4_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sc15c32f4_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t19 = ((_t18) * (_t12) + (upX));
        float _t20 = ((_t18) * (_t13) + (upY));
        float _t21 = ((_t18) * (_t11) + (upZ));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return lookAlong_lh_unsafe_mulAdd_s588deedb_1(dest, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s588deedb_1(long dest, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return lookAlong_lh_unsafe_mulAdd_s588deedb_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s588deedb_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2) {
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_unsafe_mulAdd_s588deedb_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s588deedb_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        FloatQuatOpsKernelsSegment.lookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static long lookAlong_rh_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, upY);
        float _t22 = java.lang.Math.fma(_t20, _t12, upX);
        float _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_unsafe_fma_s21542cce_1(dest, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s21542cce_1(long dest, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        return lookAlong_rh_unsafe_fma_s21542cce_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s21542cce_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        return lookAlong_rh_unsafe_fma_s21542cce_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s21542cce_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t21 = ((_t20) * (_t13) + (upY));
        float _t22 = ((_t20) * (_t12) + (upX));
        float _t23 = ((_t20) * (_t11) + (upZ));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_unsafe_mulAdd_sac4825bd_1(dest, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sac4825bd_1(long dest, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return lookAlong_rh_unsafe_mulAdd_sac4825bd_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sac4825bd_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2) {
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_unsafe_mulAdd_sac4825bd_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sac4825bd_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static long lookAlong_lh(long dest, long src, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dir, up);
        FloatQuatOpsKernelsSegment.lookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 12L), 0L);
        return dest;
    }

    public static long lookAlong_lh_unsafe(long dest, long src, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dir, up);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dir, up);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        return lookAlong_lh_unsafe_fma_s96ce96ea_1(dest, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t19, _t21, java.lang.Math.fma(_t19, _t13, -(_t20 * _t12)), java.lang.Math.fma(_t20, _t11, -(_t21 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s96ce96ea_1(long dest, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t19, float _t21, float _t28, float _t29) {
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        return lookAlong_lh_unsafe_fma_s96ce96ea_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t39, _t41, _t43, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s96ce96ea_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t39, float _t41, float _t43, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73) {
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        return lookAlong_lh_unsafe_fma_s96ce96ea_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s96ce96ea_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t19 = ((_t18) * (_t12) + (_upx));
        float _t20 = ((_t18) * (_t13) + (_upy));
        float _t21 = ((_t18) * (_t11) + (_upz));
        return lookAlong_lh_unsafe_mulAdd_se2d36c15_1(dest, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, ((_t19) * (_t13) - (_t20 * _t12)), ((_t20) * (_t11) - (_t21 * _t13)), ((_t21) * (_t12) - (_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_se2d36c15_1(long dest, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30) {
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        return lookAlong_lh_unsafe_mulAdd_se2d36c15_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11)))), ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_se2d36c15_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        return lookAlong_lh_unsafe_mulAdd_se2d36c15_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_se2d36c15_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dir, up);
        FloatQuatOpsKernelsSegment.lookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 12L), 0L);
        return dest;
    }

    public static long lookAlong_rh_unsafe(long dest, long src, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dir, up);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dir, up);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        return lookAlong_rh_unsafe_fma_s5403cc14_1(dest, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t21, _t22, java.lang.Math.fma(_t20, _t11, _upz), java.lang.Math.fma(_t21, _t12, -(_t22 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5403cc14_1(long dest, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t21, float _t22, float _t23, float _t30) {
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        return lookAlong_rh_unsafe_fma_s5403cc14_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5403cc14_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t44, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74) {
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        return lookAlong_rh_unsafe_fma_s5403cc14_3(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t50, _t51, _t62, _t67, _t68, _t70, _t71, _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5403cc14_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2, float _sp0) {
        float _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        return lookAlong_rh_unsafe_fma_s5403cc14_4(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 5 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5403cc14_4(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        return lookAlong_rh_unsafe_mulAdd_sfcfb9957_1(dest, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, ((_t21) * (_t12) - (_t22 * _t13)), ((_t22) * (_t11) - (_t23 * _t12)), ((_t23) * (_t13) - (_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sfcfb9957_1(long dest, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32) {
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        return lookAlong_rh_unsafe_mulAdd_sfcfb9957_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sfcfb9957_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        return lookAlong_rh_unsafe_mulAdd_sfcfb9957_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sfcfb9957_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static long lookAlong_lh(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        FloatQuatOpsKernelsSegment.lookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static long lookAlong_lh_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, upX);
        float _t20 = java.lang.Math.fma(_t18, _t13, upY);
        float _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_unsafe_fma_se9067338_1(dest, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_se9067338_1(long dest, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        return lookAlong_lh_unsafe_fma_se9067338_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_se9067338_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        return lookAlong_lh_unsafe_fma_se9067338_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_se9067338_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t19 = ((_t18) * (_t12) + (upX));
        float _t20 = ((_t18) * (_t13) + (upY));
        float _t21 = ((_t18) * (_t11) + (upZ));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return lookAlong_lh_unsafe_mulAdd_sb80cdb81_1(dest, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sb80cdb81_1(long dest, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return lookAlong_lh_unsafe_mulAdd_sb80cdb81_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sb80cdb81_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2) {
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_unsafe_mulAdd_sb80cdb81_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sb80cdb81_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        FloatQuatOpsKernelsSegment.lookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static long lookAlong_rh_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, upY);
        float _t22 = java.lang.Math.fma(_t20, _t12, upX);
        float _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_unsafe_fma_sceb4cb1a_1(dest, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_sceb4cb1a_1(long dest, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        return lookAlong_rh_unsafe_fma_sceb4cb1a_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_sceb4cb1a_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        return lookAlong_rh_unsafe_fma_sceb4cb1a_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_sceb4cb1a_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t21 = ((_t20) * (_t13) + (upY));
        float _t22 = ((_t20) * (_t12) + (upX));
        float _t23 = ((_t20) * (_t11) + (upZ));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_unsafe_mulAdd_s55703723_1(dest, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s55703723_1(long dest, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return lookAlong_rh_unsafe_mulAdd_s55703723_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s55703723_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2) {
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_unsafe_mulAdd_s55703723_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s55703723_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static long lookAlong_lh(long dest, long src, long dir, long up, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dir, up, epsilon);
        FloatQuatOpsKernelsSegment.lookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 12L), 0L, epsilon);
        return dest;
    }

    public static long lookAlong_lh_unsafe(long dest, long src, long dir, long up, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dir, up, epsilon);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dir, up, epsilon);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        return lookAlong_lh_unsafe_fma_sd82b6aa6_1(dest, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t19, _t21, java.lang.Math.fma(_t19, _t13, -(_t20 * _t12)), java.lang.Math.fma(_t20, _t11, -(_t21 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sd82b6aa6_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t19, float _t21, float _t28, float _t29) {
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        return lookAlong_lh_unsafe_fma_sd82b6aa6_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t39, _t41, _t43, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sd82b6aa6_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t39, float _t41, float _t43, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73) {
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        return lookAlong_lh_unsafe_fma_sd82b6aa6_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sd82b6aa6_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t19 = ((_t18) * (_t12) + (_upx));
        float _t20 = ((_t18) * (_t13) + (_upy));
        float _t21 = ((_t18) * (_t11) + (_upz));
        return lookAlong_lh_unsafe_mulAdd_sf9449a9b_1(dest, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, ((_t19) * (_t13) - (_t20 * _t12)), ((_t20) * (_t11) - (_t21 * _t13)), ((_t21) * (_t12) - (_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sf9449a9b_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30) {
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        return lookAlong_lh_unsafe_mulAdd_sf9449a9b_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11)))), ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sf9449a9b_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        return lookAlong_lh_unsafe_mulAdd_sf9449a9b_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sf9449a9b_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, long dir, long up, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dir, up, epsilon);
        FloatQuatOpsKernelsSegment.lookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 12L), 0L, epsilon);
        return dest;
    }

    public static long lookAlong_rh_unsafe(long dest, long src, long dir, long up, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dir, up, epsilon);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dir, up, epsilon);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        return lookAlong_rh_unsafe_fma_s3ec297d8_1(dest, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t21, _t22, java.lang.Math.fma(_t20, _t11, _upz), java.lang.Math.fma(_t21, _t12, -(_t22 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s3ec297d8_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t21, float _t22, float _t23, float _t30) {
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        return lookAlong_rh_unsafe_fma_s3ec297d8_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s3ec297d8_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t44, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74) {
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        return lookAlong_rh_unsafe_fma_s3ec297d8_3(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t50, _t51, _t62, _t67, _t68, _t70, _t71, _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s3ec297d8_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2, float _sp0) {
        float _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        return lookAlong_rh_unsafe_fma_s3ec297d8_4(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 5 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s3ec297d8_4(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        return lookAlong_rh_unsafe_mulAdd_s1edaf42d_1(dest, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t21, _t23, ((_t21) * (_t12) - (_t22 * _t13)), ((_t22) * (_t11) - (_t23 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s1edaf42d_1(long dest, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t21, float _t23, float _t30, float _t31) {
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        return lookAlong_rh_unsafe_mulAdd_s1edaf42d_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41)))));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s1edaf42d_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        return lookAlong_rh_unsafe_mulAdd_s1edaf42d_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s1edaf42d_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, axisX * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, axisY * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, axisZ * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosWithSin(_t0));
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, long axis, float angle) {
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, _axisx * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _axisy * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _axisz * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosWithSin(_t0));
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ);
        FloatQuatOpsKernelsSegment.makeRotationLookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, upX);
        float _t20 = java.lang.Math.fma(_t18, _t13, upY);
        float _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = java.lang.Math.fma(_t38, _t11, -(_t39 * _t12));
        float _t65 = java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13));
        float _t66 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13));
        float _t68 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40));
        float _t69 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42));
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t49);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t69);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t68);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t48);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t19 = ((_t18) * (_t12) + (upX));
        float _t20 = ((_t18) * (_t13) + (upY));
        float _t21 = ((_t18) * (_t11) + (upZ));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = ((_t38) * (_t11) - (_t39 * _t12));
        float _t65 = ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13))));
        float _t66 = ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13))));
        float _t68 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40))));
        float _t69 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42))));
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t49);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t69);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t68);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t48);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ);
        FloatQuatOpsKernelsSegment.makeRotationLookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t14 = -_t13;
        float _t15 = -_t11;
        float _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, upY);
        float _t22 = java.lang.Math.fma(_t20, _t12, upX);
        float _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = java.lang.Math.fma(_t40, _t12, _t41 * -_t11);
        float _t67 = java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14));
        float _t68 = java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14));
        float _t70 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42));
        float _t71 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45));
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t51);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t71);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t70);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t50);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t14 = -_t13;
        float _t15 = -_t11;
        float _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t21 = ((_t20) * (_t13) + (upY));
        float _t22 = ((_t20) * (_t12) + (upX));
        float _t23 = ((_t20) * (_t11) + (upZ));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = ((_t40) * (_t12) - (_t41 * _t11));
        float _t67 = ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14))));
        float _t68 = ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14))));
        float _t70 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42))));
        float _t71 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45))));
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t51);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t71);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t70);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t50);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dir, up);
        FloatQuatOpsKernelsSegment.makeRotationLookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 12L), 0L);
        return dest;
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dir, up);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dir, up);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = java.lang.Math.fma(_t38, _t11, -(_t39 * _t12));
        float _t65 = java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13));
        float _t66 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13));
        float _t68 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40));
        float _t69 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42));
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t49);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t69);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t68);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t48);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t19 = ((_t18) * (_t12) + (_upx));
        float _t20 = ((_t18) * (_t13) + (_upy));
        float _t21 = ((_t18) * (_t11) + (_upz));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = ((_t38) * (_t11) - (_t39 * _t12));
        float _t65 = ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13))));
        float _t66 = ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13))));
        float _t68 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40))));
        float _t69 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42))));
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t49);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t69);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t68);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t48);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dir, up);
        FloatQuatOpsKernelsSegment.makeRotationLookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 12L), 0L);
        return dest;
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dir, up);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dir, up);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t14 = -_t13;
        float _t15 = -_t11;
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = java.lang.Math.fma(_t40, _t12, _t41 * -_t11);
        float _t67 = java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14));
        float _t68 = java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14));
        float _t70 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42));
        float _t71 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45));
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t51);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t71);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t70);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t50);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t14 = -_t13;
        float _t15 = -_t11;
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = ((_t40) * (_t12) - (_t41 * _t11));
        float _t67 = ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14))));
        float _t68 = ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14))));
        float _t70 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42))));
        float _t71 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45))));
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t51);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t71);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t70);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t50);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        FloatQuatOpsKernelsSegment.makeRotationLookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, upX);
        float _t20 = java.lang.Math.fma(_t18, _t13, upY);
        float _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = java.lang.Math.fma(_t38, _t11, -(_t39 * _t12));
        float _t65 = java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13));
        float _t66 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13));
        float _t68 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40));
        float _t69 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42));
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t49);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t69);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t68);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t48);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t19 = ((_t18) * (_t12) + (upX));
        float _t20 = ((_t18) * (_t13) + (upY));
        float _t21 = ((_t18) * (_t11) + (upZ));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = ((_t38) * (_t11) - (_t39 * _t12));
        float _t65 = ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13))));
        float _t66 = ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13))));
        float _t68 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40))));
        float _t69 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42))));
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t49);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t69);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t68);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t48);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        FloatQuatOpsKernelsSegment.makeRotationLookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t14 = -_t13;
        float _t15 = -_t11;
        float _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, upY);
        float _t22 = java.lang.Math.fma(_t20, _t12, upX);
        float _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = java.lang.Math.fma(_t40, _t12, _t41 * -_t11);
        float _t67 = java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14));
        float _t68 = java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14));
        float _t70 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42));
        float _t71 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45));
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t51);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t71);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t70);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t50);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t14 = -_t13;
        float _t15 = -_t11;
        float _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t21 = ((_t20) * (_t13) + (upY));
        float _t22 = ((_t20) * (_t12) + (upX));
        float _t23 = ((_t20) * (_t11) + (upZ));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = ((_t40) * (_t12) - (_t41 * _t11));
        float _t67 = ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14))));
        float _t68 = ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14))));
        float _t70 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42))));
        float _t71 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45))));
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t51);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t71);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t70);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t50);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, long dir, long up, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dir, up, epsilon);
        FloatQuatOpsKernelsSegment.makeRotationLookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 12L), 0L, epsilon);
        return dest;
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, long dir, long up, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dir, up, epsilon);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dir, up, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = java.lang.Math.fma(_t38, _t11, -(_t39 * _t12));
        float _t65 = java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13));
        float _t66 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13));
        float _t68 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40));
        float _t69 = java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42));
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t49);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t69);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t68);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t48);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t19 = ((_t18) * (_t12) + (_upx));
        float _t20 = ((_t18) * (_t13) + (_upy));
        float _t21 = ((_t18) * (_t11) + (_upz));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t48 = _t39 + _t12;
        float _t49 = _t39 - _t12;
        float _t60 = ((_t38) * (_t11) - (_t39 * _t12));
        float _t65 = ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13))));
        float _t66 = ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13))));
        float _t68 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40))));
        float _t69 = ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42))));
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t49);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t69);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t68);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t48);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, long dir, long up, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dir, up, epsilon);
        FloatQuatOpsKernelsSegment.makeRotationLookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 12L), 0L, epsilon);
        return dest;
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, long dir, long up, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dir, up, epsilon);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dir, up, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t14 = -_t13;
        float _t15 = -_t11;
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = java.lang.Math.fma(_t40, _t12, _t41 * -_t11);
        float _t67 = java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14));
        float _t68 = java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14));
        float _t70 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42));
        float _t71 = java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45));
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t51);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t71);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t70);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t50);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, long dir, long up, float epsilon) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t14 = -_t13;
        float _t15 = -_t11;
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t50 = _t40 - _t12;
        float _t51 = _t40 + _t12;
        float _t62 = ((_t40) * (_t12) - (_t41 * _t11));
        float _t67 = ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14))));
        float _t68 = ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14))));
        float _t70 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42))));
        float _t71 = ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45))));
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t51);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t71);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t70);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t50);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static long makeRotationTo_unsafe(long dest, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_unsafe_fma(dest, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return makeRotationTo_unsafe_mulAdd(dest, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code makeRotationTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_fma(long dest, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_unsafe_fma_s4c531fa7_1(dest, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_fma_s4c531fa7_1(long dest, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > 6.0E-8f) {
            UnsafeOpsHolder.U.putFloat(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t17 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t16 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putFloat(dest + 4L, _t30 * _t19);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _t30 * _t18);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd(long dest, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t16 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_unsafe_mulAdd_s23659066_1(dest, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd_s23659066_1(long dest, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > 6.0E-8f) {
            UnsafeOpsHolder.U.putFloat(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t17 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t16 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putFloat(dest + 4L, _t30 * _t19);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _t30 * _t18);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            }
        }
        return dest;
    }

    public static long makeRotationTo_unsafe(long dest, long fromDir, long toDir) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_unsafe_fma(dest, fromDir, toDir);
        return makeRotationTo_unsafe_mulAdd(dest, fromDir, toDir);
    }

    /** {@code makeRotationTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_fma(long dest, long fromDir, long toDir) {
        float _fromDirx = UnsafeOpsHolder.U.getFloat(fromDir);
        float _fromDiry = UnsafeOpsHolder.U.getFloat(fromDir + 4L);
        float _fromDirz = UnsafeOpsHolder.U.getFloat(fromDir + 8L);
        float _toDirx = UnsafeOpsHolder.U.getFloat(toDir);
        float _toDiry = UnsafeOpsHolder.U.getFloat(toDir + 4L);
        float _toDirz = UnsafeOpsHolder.U.getFloat(toDir + 8L);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t15 = java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        float _t16 = java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx);
        float _t17 = java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_unsafe_fma_s654afdc5_1(dest, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_fma_s654afdc5_1(long dest, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > 6.0E-8f) {
            UnsafeOpsHolder.U.putFloat(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t17 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t16 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putFloat(dest + 4L, _t30 * _t19);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _t30 * _t18);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd(long dest, long fromDir, long toDir) {
        float _fromDirx = UnsafeOpsHolder.U.getFloat(fromDir);
        float _fromDiry = UnsafeOpsHolder.U.getFloat(fromDir + 4L);
        float _fromDirz = UnsafeOpsHolder.U.getFloat(fromDir + 8L);
        float _toDirx = UnsafeOpsHolder.U.getFloat(toDir);
        float _toDiry = UnsafeOpsHolder.U.getFloat(toDir + 4L);
        float _toDirz = UnsafeOpsHolder.U.getFloat(toDir + 8L);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t15 = ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry));
        float _t16 = ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx));
        float _t17 = ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_unsafe_mulAdd_sbfdcd4f0_1(dest, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd_sbfdcd4f0_1(long dest, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > 6.0E-8f) {
            UnsafeOpsHolder.U.putFloat(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t17 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t16 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putFloat(dest + 4L, _t30 * _t19);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _t30 * _t18);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            }
        }
        return dest;
    }

    public static long makeRotationTo_unsafe(long dest, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_unsafe_fma(dest, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return makeRotationTo_unsafe_mulAdd(dest, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code makeRotationTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_fma(long dest, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_unsafe_fma_s9663613d_1(dest, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_fma_s9663613d_1(long dest, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > epsilon) {
            UnsafeOpsHolder.U.putFloat(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t17 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t16 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putFloat(dest + 4L, _t30 * _t19);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _t30 * _t18);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd(long dest, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t16 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_unsafe_mulAdd_s69ed13b2_1(dest, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd_s69ed13b2_1(long dest, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > epsilon) {
            UnsafeOpsHolder.U.putFloat(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t17 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t16 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putFloat(dest + 4L, _t30 * _t19);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _t30 * _t18);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            }
        }
        return dest;
    }

    public static long makeRotationTo_unsafe(long dest, long fromDir, long toDir, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_unsafe_fma(dest, fromDir, toDir, epsilon);
        return makeRotationTo_unsafe_mulAdd(dest, fromDir, toDir, epsilon);
    }

    /** {@code makeRotationTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_fma(long dest, long fromDir, long toDir, float epsilon) {
        float _fromDirx = UnsafeOpsHolder.U.getFloat(fromDir);
        float _fromDiry = UnsafeOpsHolder.U.getFloat(fromDir + 4L);
        float _fromDirz = UnsafeOpsHolder.U.getFloat(fromDir + 8L);
        float _toDirx = UnsafeOpsHolder.U.getFloat(toDir);
        float _toDiry = UnsafeOpsHolder.U.getFloat(toDir + 4L);
        float _toDirz = UnsafeOpsHolder.U.getFloat(toDir + 8L);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t15 = java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        float _t16 = java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx);
        float _t17 = java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_unsafe_fma_seb46d7cb_1(dest, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_fma_seb46d7cb_1(long dest, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > epsilon) {
            UnsafeOpsHolder.U.putFloat(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t17 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t16 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putFloat(dest + 4L, _t30 * _t19);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _t30 * _t18);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd(long dest, long fromDir, long toDir, float epsilon) {
        float _fromDirx = UnsafeOpsHolder.U.getFloat(fromDir);
        float _fromDiry = UnsafeOpsHolder.U.getFloat(fromDir + 4L);
        float _fromDirz = UnsafeOpsHolder.U.getFloat(fromDir + 8L);
        float _toDirx = UnsafeOpsHolder.U.getFloat(toDir);
        float _toDiry = UnsafeOpsHolder.U.getFloat(toDir + 4L);
        float _toDirz = UnsafeOpsHolder.U.getFloat(toDir + 8L);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t15 = ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry));
        float _t16 = ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx));
        float _t17 = ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_unsafe_mulAdd_s6570cf44_1(dest, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd_s6570cf44_1(long dest, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > epsilon) {
            UnsafeOpsHolder.U.putFloat(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t17 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t16 * _t32);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                UnsafeOpsHolder.U.putFloat(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putFloat(dest + 4L, _t30 * _t19);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _t30 * _t18);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            } else {
                UnsafeOpsHolder.U.putFloat(dest, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
                UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
            }
        }
        return dest;
    }

    public static long makeRotationX_unsafe(long dest, float angle) {
        float _t0 = 0.5f * angle;
        UnsafeOpsHolder.U.putFloat(dest, Math.sinWithCos(_t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosWithSin(_t0));
        return dest;
    }

    public static long makeRotationXYZ_unsafe(long dest, float angleX, float angleY, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXYZ_unsafe_mulAdd(dest, angleX, angleY, angleZ);
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t6;
        float _t12 = _t7 * _t4;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t10, _t5, _t9 * -_t8));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        return dest;
    }

    /** {@code makeRotationXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationXYZ_unsafe_mulAdd(long dest, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t6;
        float _t12 = _t7 * _t4;
        UnsafeOpsHolder.U.putFloat(dest, ((_t9) * (_t5) + (_t10 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t10) * (_t5) - (_t9 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t11) * (_t5) + (_t12 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t12) * (_t5) - (_t11 * _t8)));
        return dest;
    }

    public static long makeRotationXZY_unsafe(long dest, float angleX, float angleZ, float angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXZY_unsafe_mulAdd(dest, angleX, angleZ, angleY);
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t9, _t8, _t10 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        return dest;
    }

    /** {@code makeRotationXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationXZY_unsafe_mulAdd(long dest, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        UnsafeOpsHolder.U.putFloat(dest, ((_t9) * (_t5) - (_t10 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t11) * (_t8) - (_t12 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t9) * (_t8) + (_t10 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t12) * (_t8) + (_t11 * _t5)));
        return dest;
    }

    public static long makeRotationY_unsafe(long dest, float angle) {
        float _t0 = 0.5f * angle;
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.sinWithCos(_t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosWithSin(_t0));
        return dest;
    }

    public static long makeRotationYXZ_unsafe(long dest, float angleY, float angleX, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYXZ_unsafe_mulAdd(dest, angleY, angleX, angleZ);
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t10, _t5, -(_t9 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        return dest;
    }

    /** {@code makeRotationYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationYXZ_unsafe_mulAdd(long dest, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        UnsafeOpsHolder.U.putFloat(dest, ((_t9) * (_t5) + (_t10 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t10) * (_t5) - (_t9 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t11) * (_t8) - (_t12 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t12) * (_t8) + (_t11 * _t5)));
        return dest;
    }

    public static long makeRotationYZX_unsafe(long dest, float angleY, float angleZ, float angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYZX_unsafe_mulAdd(dest, angleY, angleZ, angleX);
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t10, _t5, _t9 * -_t8));
        return dest;
    }

    /** {@code makeRotationYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationYZX_unsafe_mulAdd(long dest, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        UnsafeOpsHolder.U.putFloat(dest, ((_t9) * (_t5) + (_t10 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t11) * (_t5) + (_t12 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t12) * (_t5) - (_t11 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t10) * (_t5) - (_t9 * _t8)));
        return dest;
    }

    public static long makeRotationZ_unsafe(long dest, float angle) {
        float _t0 = 0.5f * angle;
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.sinWithCos(_t0));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosWithSin(_t0));
        return dest;
    }

    public static long makeRotationZXY_unsafe(long dest, float angleZ, float angleX, float angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZXY_unsafe_mulAdd(dest, angleZ, angleX, angleY);
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t6;
        float _t12 = _t7 * _t4;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t9, _t5, _t10 * -_t8));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t9, _t8, _t10 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        return dest;
    }

    /** {@code makeRotationZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationZXY_unsafe_mulAdd(long dest, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t6;
        float _t12 = _t7 * _t4;
        UnsafeOpsHolder.U.putFloat(dest, ((_t9) * (_t5) - (_t10 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t11) * (_t5) + (_t12 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t9) * (_t8) + (_t10 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t12) * (_t5) - (_t11 * _t8)));
        return dest;
    }

    public static long makeRotationZYX_unsafe(long dest, float angleZ, float angleY, float angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZYX_unsafe_mulAdd(dest, angleZ, angleY, angleX);
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t11, _t8, _t12 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t12, _t8, -(_t11 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t10, _t5, _t9 * _t8));
        return dest;
    }

    /** {@code makeRotationZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationZYX_unsafe_mulAdd(long dest, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        UnsafeOpsHolder.U.putFloat(dest, ((_t9) * (_t5) - (_t10 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t11) * (_t8) + (_t12 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t12) * (_t8) - (_t11 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t10) * (_t5) + (_t9 * _t8)));
        return dest;
    }

    public static long preRotateX_unsafe(long dest, long src, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateX_unsafe_mulAdd(dest, src, angle);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t1, _selfw * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t1, -(_selfz * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfy, _t2, _selfz * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t1, -(_selfx * _t2)));
        return dest;
    }

    /** {@code preRotateX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateX_unsafe_mulAdd(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t1) + (_selfw * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t1) - (_selfz * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfy) * (_t2) + (_selfz * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t1) - (_selfx * _t2)));
        return dest;
    }

    public static long preRotateY_unsafe(long dest, long src, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateY_unsafe_mulAdd(dest, src, angle);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t1, _selfz * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t1, _selfw * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t1, -(_selfx * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t1, -(_selfy * _t2)));
        return dest;
    }

    /** {@code preRotateY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateY_unsafe_mulAdd(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t1) + (_selfz * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t1) + (_selfw * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t1) - (_selfx * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t1) - (_selfy * _t2)));
        return dest;
    }

    public static long preRotateZ_unsafe(long dest, long src, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateZ_unsafe_mulAdd(dest, src, angle);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t1, -(_selfy * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfx, _t2, _selfy * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t1, _selfw * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t1, -(_selfz * _t2)));
        return dest;
    }

    /** {@code preRotateZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateZ_unsafe_mulAdd(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t1) - (_selfy * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfx) * (_t2) + (_selfy * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t1) + (_selfw * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t1) - (_selfz * _t2)));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_mulAdd(dest, src, angle, axisX, axisY, axisZ);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t1, _selfw * _t3) + java.lang.Math.fma(_selfy, _t4, -(_selfz * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t1, _selfz * _t3) + java.lang.Math.fma(_selfw, _t5, -_selfx * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t5, _selfw * _t4) + java.lang.Math.fma(_selfz, _t1, -(_selfy * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t1, -_selfx * _t3) - java.lang.Math.fma(_selfy, _t5, _selfz * _t4));
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t1) + (_selfw * _t3)) + ((_selfy) * (_t4) - (_selfz * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t1) + (_selfz * _t3)) + ((_selfw) * (_t5) - (_selfx * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t5) + (_selfw * _t4)) + ((_selfz) * (_t1) - (_selfy * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t1) - (_selfx * _t3)) - ((_selfy) * (_t5) + (_selfz * _t4)));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_mulAdd(dest, src, axis, angle);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = UnsafeOpsHolder.U.getFloat(axis) * _t2;
        float _t4 = UnsafeOpsHolder.U.getFloat(axis + 8L) * _t2;
        float _t5 = UnsafeOpsHolder.U.getFloat(axis + 4L) * _t2;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t1, _selfw * _t3) + java.lang.Math.fma(_selfy, _t4, -(_selfz * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t1, _selfz * _t3) + java.lang.Math.fma(_selfw, _t5, -_selfx * _t4));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t5, _selfw * _t4) + java.lang.Math.fma(_selfz, _t1, -(_selfy * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t1, -_selfx * _t3) - java.lang.Math.fma(_selfy, _t5, _selfz * _t4));
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, long axis, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = UnsafeOpsHolder.U.getFloat(axis) * _t2;
        float _t4 = UnsafeOpsHolder.U.getFloat(axis + 8L) * _t2;
        float _t5 = UnsafeOpsHolder.U.getFloat(axis + 4L) * _t2;
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t1) + (_selfw * _t3)) + ((_selfy) * (_t4) - (_selfz * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t1) + (_selfz * _t3)) + ((_selfw) * (_t5) - (_selfx * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t5) + (_selfw * _t4)) + ((_selfz) * (_t1) - (_selfy * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t1) - (_selfx * _t3)) - ((_selfy) * (_t5) + (_selfz * _t4)));
        return dest;
    }

    public static long rotateTo_unsafe(long dest, long src, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_unsafe_fma(dest, src, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return rotateTo_unsafe_mulAdd(dest, src, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code rotateTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_fma(long dest, long src, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_unsafe_fma_sbc9d98aa_1(dest, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_fma_sbc9d98aa_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_mulAdd(long dest, long src, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_unsafe_mulAdd_sb5b2c505_1(dest, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_mulAdd_sb5b2c505_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static long rotateTo_unsafe(long dest, long src, long fromDir, long toDir) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_unsafe_fma(dest, src, fromDir, toDir);
        return rotateTo_unsafe_mulAdd(dest, src, fromDir, toDir);
    }

    /** {@code rotateTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_fma(long dest, long src, long fromDir, long toDir) {
        float _fromDirx = UnsafeOpsHolder.U.getFloat(fromDir);
        float _fromDiry = UnsafeOpsHolder.U.getFloat(fromDir + 4L);
        float _fromDirz = UnsafeOpsHolder.U.getFloat(fromDir + 8L);
        float _toDirx = UnsafeOpsHolder.U.getFloat(toDir);
        float _toDiry = UnsafeOpsHolder.U.getFloat(toDir + 4L);
        float _toDirz = UnsafeOpsHolder.U.getFloat(toDir + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_unsafe_fma_sca439734_1(dest, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_fma_sca439734_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_mulAdd(long dest, long src, long fromDir, long toDir) {
        float _fromDirx = UnsafeOpsHolder.U.getFloat(fromDir);
        float _fromDiry = UnsafeOpsHolder.U.getFloat(fromDir + 4L);
        float _fromDirz = UnsafeOpsHolder.U.getFloat(fromDir + 8L);
        float _toDirx = UnsafeOpsHolder.U.getFloat(toDir);
        float _toDiry = UnsafeOpsHolder.U.getFloat(toDir + 4L);
        float _toDirz = UnsafeOpsHolder.U.getFloat(toDir + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_unsafe_mulAdd_s9184fbb3_1(dest, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx)), ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry)), ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz)), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_mulAdd_s9184fbb3_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static long rotateTo_unsafe(long dest, long src, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_unsafe_fma(dest, src, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return rotateTo_unsafe_mulAdd(dest, src, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code rotateTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_fma(long dest, long src, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_unsafe_fma_s66a47ce6_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_fma_s66a47ce6_1(long dest, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_mulAdd(long dest, long src, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_unsafe_mulAdd_se6fda78b_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_mulAdd_se6fda78b_1(long dest, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static long rotateTo_unsafe(long dest, long src, long fromDir, long toDir, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_unsafe_fma(dest, src, fromDir, toDir, epsilon);
        return rotateTo_unsafe_mulAdd(dest, src, fromDir, toDir, epsilon);
    }

    /** {@code rotateTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_fma(long dest, long src, long fromDir, long toDir, float epsilon) {
        float _fromDirx = UnsafeOpsHolder.U.getFloat(fromDir);
        float _fromDiry = UnsafeOpsHolder.U.getFloat(fromDir + 4L);
        float _fromDirz = UnsafeOpsHolder.U.getFloat(fromDir + 8L);
        float _toDirx = UnsafeOpsHolder.U.getFloat(toDir);
        float _toDiry = UnsafeOpsHolder.U.getFloat(toDir + 4L);
        float _toDirz = UnsafeOpsHolder.U.getFloat(toDir + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_unsafe_fma_sfc8d6af8_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_fma_sfc8d6af8_1(long dest, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
        float _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_mulAdd(long dest, long src, long fromDir, long toDir, float epsilon) {
        float _fromDirx = UnsafeOpsHolder.U.getFloat(fromDir);
        float _fromDiry = UnsafeOpsHolder.U.getFloat(fromDir + 4L);
        float _fromDirz = UnsafeOpsHolder.U.getFloat(fromDir + 8L);
        float _toDirx = UnsafeOpsHolder.U.getFloat(toDir);
        float _toDiry = UnsafeOpsHolder.U.getFloat(toDir + 4L);
        float _toDirz = UnsafeOpsHolder.U.getFloat(toDir + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_unsafe_mulAdd_s8cd5ff99_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx)), ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry)), ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz)), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_mulAdd_s8cd5ff99_1(long dest, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
        float _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateX_unsafe_mulAdd(dest, src, angle);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t1, _selfw * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t1, _selfz * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t1, -(_selfy * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t1, -(_selfx * _t2)));
        return dest;
    }

    /** {@code rotateX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateX_unsafe_mulAdd(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t1) + (_selfw * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t1) + (_selfz * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t1) - (_selfy * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t1) - (_selfx * _t2)));
        return dest;
    }

    public static long rotateXYZ_unsafe(long dest, long src, float angleX, float angleY, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXYZ_unsafe_fma(dest, src, angleX, angleY, angleZ);
        return rotateXYZ_unsafe_mulAdd(dest, src, angleX, angleY, angleZ);
    }

    /** {@code rotateXYZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXYZ_unsafe_fma(long dest, long src, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t21, _selfw * _t19) + java.lang.Math.fma(_selfy, _t20, -(_selfz * _t22)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t21, _selfz * _t19) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t20));
        return rotateXYZ_unsafe_fma_s2d5fcdf3_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_fma_s2d5fcdf3_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t22, _selfw * _t20) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t21, -_selfx * _t19) - java.lang.Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    /** {@code rotateXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXYZ_unsafe_mulAdd(long dest, long src, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t12) * (_t5) - (_t11 * _t8));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t21) + (_selfw * _t19)) + ((_selfy) * (_t20) - (_selfz * _t22)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t21) + (_selfz * _t19)) + ((_selfw) * (_t22) - (_selfx * _t20)));
        return rotateXYZ_unsafe_mulAdd_sfb9921e8_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_mulAdd_sfb9921e8_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t22) + (_selfw * _t20)) + ((_selfz) * (_t21) - (_selfy * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t21) - (_selfx * _t19)) - ((_selfy) * (_t22) + (_selfz * _t20)));
        return dest;
    }

    public static long rotateXZY_unsafe(long dest, long src, float angleX, float angleZ, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXZY_unsafe_fma(dest, src, angleX, angleZ, angleY);
        return rotateXZY_unsafe_mulAdd(dest, src, angleX, angleZ, angleY);
    }

    /** {@code rotateXZY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXZY_unsafe_fma(long dest, long src, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t21 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        float _t22 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t19, _selfw * _t21) + java.lang.Math.fma(_selfy, _t20, -(_selfz * _t22)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t19, _selfz * _t21) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t20));
        return rotateXZY_unsafe_fma_s801838e9_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_fma_s801838e9_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t22, _selfw * _t20) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t19, -_selfx * _t21) - java.lang.Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    /** {@code rotateXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXZY_unsafe_mulAdd(long dest, long src, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t21 = ((_t11) * (_t8) - (_t12 * _t5));
        float _t22 = ((_t10) * (_t5) - (_t9 * _t8));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t19) + (_selfw * _t21)) + ((_selfy) * (_t20) - (_selfz * _t22)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t19) + (_selfz * _t21)) + ((_selfw) * (_t22) - (_selfx * _t20)));
        return rotateXZY_unsafe_mulAdd_s3f58af8e_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_mulAdd_s3f58af8e_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t22) + (_selfw * _t20)) + ((_selfz) * (_t19) - (_selfy * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t19) - (_selfx * _t21)) - ((_selfy) * (_t22) + (_selfz * _t20)));
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateY_unsafe_mulAdd(dest, src, angle);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t1, -(_selfz * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t1, _selfw * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t2, _selfz * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t1, -(_selfy * _t2)));
        return dest;
    }

    /** {@code rotateY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateY_unsafe_mulAdd(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t1) - (_selfz * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t1) + (_selfw * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t2) + (_selfz * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t1) - (_selfy * _t2)));
        return dest;
    }

    public static long rotateYXZ_unsafe(long dest, long src, float angleY, float angleX, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYXZ_unsafe_fma(dest, src, angleY, angleX, angleZ);
        return rotateYXZ_unsafe_mulAdd(dest, src, angleY, angleX, angleZ);
    }

    /** {@code rotateYXZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYXZ_unsafe_fma(long dest, long src, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t19, _selfw * _t20) + java.lang.Math.fma(_selfy, _t21, -(_selfz * _t22)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t19, _selfz * _t20) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t21));
        return rotateYXZ_unsafe_fma_s75cbabd_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_fma_s75cbabd_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t22, _selfw * _t21) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t19, -_selfx * _t20) - java.lang.Math.fma(_selfy, _t22, _selfz * _t21));
        return dest;
    }

    /** {@code rotateYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYXZ_unsafe_mulAdd(long dest, long src, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t12) * (_t8) - (_t11 * _t5));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t19) + (_selfw * _t20)) + ((_selfy) * (_t21) - (_selfz * _t22)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t19) + (_selfz * _t20)) + ((_selfw) * (_t22) - (_selfx * _t21)));
        return rotateYXZ_unsafe_mulAdd_s4e66b8a_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_mulAdd_s4e66b8a_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t22) + (_selfw * _t21)) + ((_selfz) * (_t19) - (_selfy * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t19) - (_selfx * _t20)) - ((_selfy) * (_t22) + (_selfz * _t21)));
        return dest;
    }

    public static long rotateYZX_unsafe(long dest, long src, float angleY, float angleZ, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYZX_unsafe_fma(dest, src, angleY, angleZ, angleX);
        return rotateYZX_unsafe_mulAdd(dest, src, angleY, angleZ, angleX);
    }

    /** {@code rotateYZX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYZX_unsafe_fma(long dest, long src, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t5, _t11 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t21, _selfw * _t19) + java.lang.Math.fma(_selfy, _t22, -(_selfz * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t21, _selfz * _t19) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t22));
        return rotateYZX_unsafe_fma_s8e7e47b5_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_fma_s8e7e47b5_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t20, _selfw * _t22) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t21, -_selfx * _t19) - java.lang.Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    /** {@code rotateYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYZX_unsafe_mulAdd(long dest, long src, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t20 = ((_t12) * (_t5) + (_t11 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t21) + (_selfw * _t19)) + ((_selfy) * (_t22) - (_selfz * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t21) + (_selfz * _t19)) + ((_selfw) * (_t20) - (_selfx * _t22)));
        return rotateYZX_unsafe_mulAdd_s7a45713a_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_mulAdd_s7a45713a_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t20) + (_selfw * _t22)) + ((_selfz) * (_t21) - (_selfy * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t21) - (_selfx * _t19)) - ((_selfy) * (_t20) + (_selfz * _t22)));
        return dest;
    }

    public static long rotateZ_unsafe(long dest, long src, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateZ_unsafe_mulAdd(dest, src, angle);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t1, _selfy * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t1, -(_selfx * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfz, _t1, _selfw * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t1, -(_selfz * _t2)));
        return dest;
    }

    /** {@code rotateZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZ_unsafe_mulAdd(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t1) + (_selfy * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t1) - (_selfx * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfz) * (_t1) + (_selfw * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t1) - (_selfz * _t2)));
        return dest;
    }

    public static long rotateZXY_unsafe(long dest, long src, float angleZ, float angleX, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZXY_unsafe_fma(dest, src, angleZ, angleX, angleY);
        return rotateZXY_unsafe_mulAdd(dest, src, angleZ, angleX, angleY);
    }

    /** {@code rotateZXY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZXY_unsafe_fma(long dest, long src, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t21, _selfw * _t22) + java.lang.Math.fma(_selfy, _t19, -(_selfz * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t21, _selfz * _t22) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t19));
        return rotateZXY_unsafe_fma_s7d047c05_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_fma_s7d047c05_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t20, _selfw * _t19) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t22)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t21, -_selfx * _t22) - java.lang.Math.fma(_selfy, _t20, _selfz * _t19));
        return dest;
    }

    /** {@code rotateZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZXY_unsafe_mulAdd(long dest, long src, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t21) + (_selfw * _t22)) + ((_selfy) * (_t19) - (_selfz * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t21) + (_selfz * _t22)) + ((_selfw) * (_t20) - (_selfx * _t19)));
        return rotateZXY_unsafe_mulAdd_s5d615372_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_mulAdd_s5d615372_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t20) + (_selfw * _t19)) + ((_selfz) * (_t21) - (_selfy * _t22)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t21) - (_selfx * _t22)) - ((_selfy) * (_t20) + (_selfz * _t19)));
        return dest;
    }

    public static long rotateZYX_unsafe(long dest, long src, float angleZ, float angleY, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZYX_unsafe_fma(dest, src, angleZ, angleY, angleX);
        return rotateZYX_unsafe_mulAdd(dest, src, angleZ, angleY, angleX);
    }

    /** {@code rotateZYX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZYX_unsafe_fma(long dest, long src, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfx, _t19, _selfw * _t21) + java.lang.Math.fma(_selfy, _t22, -(_selfz * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfy, _t19, _selfz * _t21) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t22));
        return rotateZYX_unsafe_fma_s663d2e7_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_fma_s663d2e7_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t20, _selfw * _t22) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_selfw, _t19, -_selfx * _t21) - java.lang.Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    /** {@code rotateZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZYX_unsafe_mulAdd(long dest, long src, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t12) * (_t8) + (_t11 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t11) * (_t8) - (_t12 * _t5));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfx) * (_t19) + (_selfw * _t21)) + ((_selfy) * (_t22) - (_selfz * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfy) * (_t19) + (_selfz * _t21)) + ((_selfw) * (_t20) - (_selfx * _t22)));
        return rotateZYX_unsafe_mulAdd_sa0a2164c_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_mulAdd_sa0a2164c_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t20) + (_selfw * _t22)) + ((_selfz) * (_t19) - (_selfy * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_selfw) * (_t19) - (_selfx * _t21)) - ((_selfy) * (_t20) + (_selfz * _t22)));
        return dest;
    }

    public static long transform_unsafe(long dest, long src, float vX, float vY, float vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transform_unsafe_mulAdd(dest, src, vX, vY, vZ);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, vY, -(_selfy * vX));
        float _t10 = 2.0f * java.lang.Math.fma(_selfz, vX, -(_selfx * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(_selfy, vZ, -(_selfz * vY));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfy, _t9, java.lang.Math.fma(-_selfz, _t10, java.lang.Math.fma(_selfw, _t11, vX))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfz, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, vY))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfy, _t11, java.lang.Math.fma(_selfw, _t9, vZ))));
        return dest;
    }

    /** {@code transform_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long transform_unsafe_mulAdd(long dest, long src, float vX, float vY, float vZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (vY) - (_selfy * vX));
        float _t10 = 2.0f * ((_selfz) * (vX) - (_selfx * vZ));
        float _t11 = 2.0f * ((_selfy) * (vZ) - (_selfz * vY));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfy) * (_t9) + (((((_selfw) * (_t11) + (vX))) - (_selfz) * (_t10)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfz) * (_t11) + (((((_selfw) * (_t10) + (vY))) - (_selfx) * (_t9)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (vZ))) - (_selfy) * (_t11)))));
        return dest;
    }

    public static long transform_unsafe(long dest, long src, long v) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transform_unsafe_mulAdd(dest, src, v);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _vy, -(_selfy * _vx));
        float _t10 = 2.0f * java.lang.Math.fma(_selfz, _vx, -(_selfx * _vz));
        float _t11 = 2.0f * java.lang.Math.fma(_selfy, _vz, -(_selfz * _vy));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfy, _t9, java.lang.Math.fma(-_selfz, _t10, java.lang.Math.fma(_selfw, _t11, _vx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfz, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, _vy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfy, _t11, java.lang.Math.fma(_selfw, _t9, _vz))));
        return dest;
    }

    /** {@code transform_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long transform_unsafe_mulAdd(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_vy) - (_selfy * _vx));
        float _t10 = 2.0f * ((_selfz) * (_vx) - (_selfx * _vz));
        float _t11 = 2.0f * ((_selfy) * (_vz) - (_selfz * _vy));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfy) * (_t9) + (((((_selfw) * (_t11) + (_vx))) - (_selfz) * (_t10)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfz) * (_t11) + (((((_selfw) * (_t10) + (_vy))) - (_selfx) * (_t9)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (_vz))) - (_selfy) * (_t11)))));
        return dest;
    }

    public static long transformInverse_unsafe(long dest, long src, float vX, float vY, float vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformInverse_unsafe_mulAdd(dest, src, vX, vY, vZ);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, vZ, -(_selfz * vX));
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, vX, -(_selfx * vY));
        float _t11 = 2.0f * java.lang.Math.fma(_selfz, vY, -(_selfy * vZ));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfz, _t9, java.lang.Math.fma(-_selfy, _t10, java.lang.Math.fma(_selfw, _t11, vX))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfz, _t11, java.lang.Math.fma(_selfw, _t9, vY))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfy, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, vZ))));
        return dest;
    }

    /** {@code transformInverse_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long transformInverse_unsafe_mulAdd(long dest, long src, float vX, float vY, float vZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (vZ) - (_selfz * vX));
        float _t10 = 2.0f * ((_selfy) * (vX) - (_selfx * vY));
        float _t11 = 2.0f * ((_selfz) * (vY) - (_selfy * vZ));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfz) * (_t9) + (((((_selfw) * (_t11) + (vX))) - (_selfy) * (_t10)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (vY))) - (_selfz) * (_t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfy) * (_t11) + (((((_selfw) * (_t10) + (vZ))) - (_selfx) * (_t9)))));
        return dest;
    }

    public static long transformInverse_unsafe(long dest, long src, long v) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformInverse_unsafe_mulAdd(dest, src, v);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _vz, -(_selfz * _vx));
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _vx, -(_selfx * _vy));
        float _t11 = 2.0f * java.lang.Math.fma(_selfz, _vy, -(_selfy * _vz));
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_selfz, _t9, java.lang.Math.fma(-_selfy, _t10, java.lang.Math.fma(_selfw, _t11, _vx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfz, _t11, java.lang.Math.fma(_selfw, _t9, _vy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_selfy, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, _vz))));
        return dest;
    }

    /** {@code transformInverse_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long transformInverse_unsafe_mulAdd(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * ((_selfx) * (_vz) - (_selfz * _vx));
        float _t10 = 2.0f * ((_selfy) * (_vx) - (_selfx * _vy));
        float _t11 = 2.0f * ((_selfz) * (_vy) - (_selfy * _vz));
        UnsafeOpsHolder.U.putFloat(dest, ((_selfz) * (_t9) + (((((_selfw) * (_t11) + (_vx))) - (_selfy) * (_t10)))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (_vy))) - (_selfz) * (_t11)))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_selfy) * (_t11) + (((((_selfw) * (_t10) + (_vz))) - (_selfx) * (_t9)))));
        return dest;
    }

    /** cos(k pi/8) and sin(k pi/8), k = 0..4: the float quatArcAngle's rotations. */
    private static final double[] QUAT_ARC_ANGLE_COS = new double[] {1.0, 0x1.d906bcf328d46p-1, 0x1.6a09e667f3bcdp-1, 0x1.87de2a6aea963p-2, 0.0};
    private static final double[] QUAT_ARC_ANGLE_SIN = new double[] {0.0, 0x1.87de2a6aea963p-2, 0x1.6a09e667f3bcdp-1, 0x1.d906bcf328d46p-1, 1.0};

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]. With
     * |a-b|^2 = 4 - s and half the angle in [0, pi/2], that half is k pi/8 + asin(|a-b|/2
     * cos(k pi/8) - |a+b|/2 sin(k pi/8)) for the k (four comparisons of 4 - s) that keeps the
     * argument within sin(pi/16): there the arcsine's odd Taylor polynomial to x^11 (3e-10 of it
     * left out), in double, is exact to well below a float ulp. Within 0.5005 ulp of the angle,
     * and no call, where java.lang.Math.asin is one: slerp is 15-24% faster.
     */
    private static float quatArcAngle_fma(float s) {
        double d = 4.0 - s, sd = java.lang.Math.sqrt(d), ss = java.lang.Math.sqrt(s);
        int k = (d > 0x1.37ca1866b95cfp-3 ? 1 : 0) + (d > 0x1.3c10eaca8ab4fp+0 ? 1 : 0)
                + (d > 0x1.61f78a9abaa59p+1 ? 1 : 0) + (d > 0x1.ec835e79946a3p+1 ? 1 : 0);
        double x = 0.5 * java.lang.Math.fma(sd, QUAT_ARC_ANGLE_COS[k], -ss * QUAT_ARC_ANGLE_SIN[k]), t = x * x;
        double p = java.lang.Math.fma(t, java.lang.Math.fma(t, java.lang.Math.fma(t, java.lang.Math.fma(t, 0x1.6e8ba2e8ba2e9p-6, 0x1.f1c71c71c71c7p-6),
                0x1.6db6db6db6db7p-5), 0x1.3333333333333p-4), 0x1.5555555555555p-3);
        return (float) (k * (Math.PI / 4) + 2 * java.lang.Math.fma(x * t, p, x));
    }

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]. With
     * |a-b|^2 = 4 - s and half the angle in [0, pi/2], that half is k pi/8 + asin(|a-b|/2
     * cos(k pi/8) - |a+b|/2 sin(k pi/8)) for the k (four comparisons of 4 - s) that keeps the
     * argument within sin(pi/16): there the arcsine's odd Taylor polynomial to x^11 (3e-10 of it
     * left out), in double, is exact to well below a float ulp. Within 0.5005 ulp of the angle,
     * and no call, where java.lang.Math.asin is one: slerp is 15-24% faster.
     */
    private static float quatArcAngle_mulAdd(float s) {
        double d = 4.0 - s, sd = java.lang.Math.sqrt(d), ss = java.lang.Math.sqrt(s);
        int k = (d > 0x1.37ca1866b95cfp-3 ? 1 : 0) + (d > 0x1.3c10eaca8ab4fp+0 ? 1 : 0)
                + (d > 0x1.61f78a9abaa59p+1 ? 1 : 0) + (d > 0x1.ec835e79946a3p+1 ? 1 : 0);
        double x = 0.5 * ((sd) * (QUAT_ARC_ANGLE_COS[k]) - (ss * QUAT_ARC_ANGLE_SIN[k])), t = x * x;
        double p = ((t) * (((t) * (((t) * (((t) * (0x1.6e8ba2e8ba2e9p-6) + (0x1.f1c71c71c71c7p-6))) + (0x1.6db6db6db6db7p-5))) + (0x1.3333333333333p-4))) + (0x1.5555555555555p-3));
        return (float) (k * (Math.PI / 4) + 2 * ((x * t) * (p) + (x)));
    }
}
