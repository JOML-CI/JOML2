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
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code FloatQuatOps} and its sibling kernel units. Not public API.
 */
public final class FloatQuatOpsKernelsAddress {
    private FloatQuatOpsKernelsAddress() {}

    public static long invert_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3_inv = 1.0f / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest, -(_selfx * _t3_inv));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_selfy * _t3_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_selfz * _t3_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _selfw * _t3_inv);
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t20 = Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t21 = Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz);
        float _t22 = Math.fma(otherY, _selfx, otherZ * _selfw) + Math.fma(otherW, _selfz, -(otherX * _selfy));
        float _t23 = Math.fma(otherX, _selfz, otherW * _selfy) + Math.fma(otherY, _selfw, -(otherZ * _selfx));
        float _t27_inv = 1.0f / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
        UnsafeOpsHolder.U.putFloat(dest, -(_t20 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t23 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t22 * _t27_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t21 * _t27_inv);
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t20 = Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t21 = Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz);
        float _t22 = Math.fma(_othery, _selfx, _otherz * _selfw) + Math.fma(_otherw, _selfz, -(_otherx * _selfy));
        float _t23 = Math.fma(_otherx, _selfz, _otherw * _selfy) + Math.fma(_othery, _selfw, -(_otherz * _selfx));
        float _t27_inv = 1.0f / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
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
        float _t4 = Math.sin(_t1);
        float _t5 = (float) java.lang.Math.sqrt(1.0f - u1);
        float _t6 = Math.sin(_t3);
        UnsafeOpsHolder.U.putFloat(dest, _t4 * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.cosFromSin(_t4, _t1) * _t5);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t6 * _t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosFromSin(_t6, _t3) * _t0);
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
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
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
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
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
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(_selfx, _selfy, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(_selfx, _selfz, -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 2.0f * Math.fma(_selfx, _selfy, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 2.0f * Math.fma(_selfx, _selfz, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 36L, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        return toMatrix_unsafe_sbae01176_1(dest, _selfx, _selfy);
    }

    /** Piece 2 of {@code toMatrix_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toMatrix_unsafe_sbae01176_1(long dest, float _selfx, float _selfy) {
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 48L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 52L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 56L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 60L, 1.0f);
        return dest;
    }

    public static long toMatrix3x3_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(_selfx, _selfy, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(_selfx, _selfz, -_t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 2.0f * Math.fma(_selfx, _selfy, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 20L, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * Math.fma(_selfx, _selfz, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    public static long toMatrix3x4_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(_selfx, _selfy, -_t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(_selfx, _selfz, _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 2.0f * Math.fma(_selfx, _selfy, _t1));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 24L, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 2.0f * Math.fma(_selfx, _selfz, -_t2));
        return toMatrix3x4_unsafe_s7a8856ef_1(dest, _selfx, _selfy, _selfz, _selfw);
    }

    /** Piece 2 of {@code toMatrix3x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long toMatrix3x4_unsafe_s7a8856ef_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw) {
        UnsafeOpsHolder.U.putFloat(dest + 36L, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 40L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 44L, 0.0f);
        return dest;
    }

    public static long decomposeSwingTwist_unsafe(long swing, long twist, long src, float axisX, float axisY, float axisZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
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
        UnsafeOpsHolder.U.putFloat(swing, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        UnsafeOpsHolder.U.putFloat(swing + 4L, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        UnsafeOpsHolder.U.putFloat(swing + 8L, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        return decomposeSwingTwist_unsafe_safa43d1_1(swing, twist, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeSwingTwist_unsafe_safa43d1_1(long swing, long twist, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        UnsafeOpsHolder.U.putFloat(swing + 12L, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        UnsafeOpsHolder.U.putFloat(twist, _t12);
        UnsafeOpsHolder.U.putFloat(twist + 4L, _t13);
        UnsafeOpsHolder.U.putFloat(twist + 8L, _t14);
        UnsafeOpsHolder.U.putFloat(twist + 12L, _t11);
        return swing;
    }

    public static long decomposeSwingTwist_unsafe(long swing, long twist, long src, long axis) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t2 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
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
        UnsafeOpsHolder.U.putFloat(swing, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        UnsafeOpsHolder.U.putFloat(swing + 4L, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        return decomposeSwingTwist_unsafe_s8eba8cda_1(swing, twist, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_unsafe}, split to fit the inline budget; reached only through it. */
    private static long decomposeSwingTwist_unsafe_s8eba8cda_1(long swing, long twist, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        UnsafeOpsHolder.U.putFloat(swing + 8L, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        UnsafeOpsHolder.U.putFloat(swing + 12L, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        UnsafeOpsHolder.U.putFloat(twist, _t12);
        UnsafeOpsHolder.U.putFloat(twist + 4L, _t13);
        UnsafeOpsHolder.U.putFloat(twist + 8L, _t14);
        UnsafeOpsHolder.U.putFloat(twist + 12L, _t11);
        return swing;
    }

    public static long getSwing_unsafe(long dest, long src, float axisX, float axisY, float axisZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
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
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        return dest;
    }

    public static long getSwing_unsafe(long dest, long src, long axis) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t2 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
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
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        return getSwing_unsafe_sb618a507_1(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_unsafe}, split to fit the inline budget; reached only through it. */
    private static long getSwing_unsafe_sb618a507_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        return dest;
    }

    public static long getTwist_unsafe(long dest, long src, float axisX, float axisY, float axisZ) {
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = Math.fma(axisZ, UnsafeOpsHolder.U.getFloat(src + 8L), Math.fma(axisX, UnsafeOpsHolder.U.getFloat(src), axisY * UnsafeOpsHolder.U.getFloat(src + 4L)));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
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
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t2 = Math.fma(_axisz, UnsafeOpsHolder.U.getFloat(src + 8L), Math.fma(_axisx, UnsafeOpsHolder.U.getFloat(src), _axisy * UnsafeOpsHolder.U.getFloat(src + 4L)));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(t, otherX - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(t, otherY - _selfy, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(t, otherZ - _selfz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(t, otherW - _selfw, _selfw));
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, float t) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(t, _otherx - _selfx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(t, _othery - _selfy, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(t, _otherz - _selfz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    public static long nlerp_unsafe(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = Math.fma(alpha, targetW - _selfw, _selfw);
        float _t5 = Math.fma(alpha, targetZ - _selfz, _selfz);
        float _t6 = Math.fma(alpha, targetX - _selfx, _selfx);
        float _t7 = Math.fma(alpha, targetY - _selfy, _selfy);
        float _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t6 * _t12);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t7 * _t12);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t5 * _t12);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t4 * _t12);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long nlerp_unsafe(long dest, long src, long target, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(target + 12L) - _selfw, _selfw);
        float _t5 = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(target + 8L) - _selfz, _selfz);
        float _t6 = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(target) - _selfx, _selfx);
        float _t7 = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(target + 4L) - _selfy, _selfy);
        float _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t6 * _t12);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t7 * _t12);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t5 * _t12);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t4 * _t12);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long nlerpShortest_unsafe(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t17, _t18, _t19, _t20;
        if (-Math.fma(_selfw, targetW, Math.fma(_selfz, targetZ, Math.fma(_selfx, targetX, _selfy * targetY))) > 0.0f) {
            _t17 = Math.fma(alpha, -targetW - _selfw, _selfw);
            _t18 = Math.fma(alpha, -targetZ - _selfz, _selfz);
            _t19 = Math.fma(alpha, -targetX - _selfx, _selfx);
            _t20 = Math.fma(alpha, -targetY - _selfy, _selfy);
        } else {
            _t17 = Math.fma(alpha, targetW - _selfw, _selfw);
            _t18 = Math.fma(alpha, targetZ - _selfz, _selfz);
            _t19 = Math.fma(alpha, targetX - _selfx, _selfx);
            _t20 = Math.fma(alpha, targetY - _selfy, _selfy);
        }
        float _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        return nlerpShortest_unsafe_sa6377d5b_1(dest, _t17, _t18, _t19, _t20, _t24, (1.0f / (float) java.lang.Math.sqrt(_t24)));
    }

    /** Piece 2 of {@code nlerpShortest_unsafe}, split to fit the inline budget; reached only through it. */
    private static long nlerpShortest_unsafe_sa6377d5b_1(long dest, float _t17, float _t18, float _t19, float _t20, float _t24, float _t25) {
        if (_t24 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t19 * _t25);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t20 * _t25);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t18 * _t25);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t17 * _t25);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long nlerpShortest_unsafe(long dest, long src, long target, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t17, _t18, _t19, _t20;
        if (-Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety))) > 0.0f) {
            _t17 = Math.fma(alpha, -_targetw - _selfw, _selfw);
            _t18 = Math.fma(alpha, -_targetz - _selfz, _selfz);
            _t19 = Math.fma(alpha, -_targetx - _selfx, _selfx);
            _t20 = Math.fma(alpha, -_targety - _selfy, _selfy);
        } else {
            _t17 = Math.fma(alpha, _targetw - _selfw, _selfw);
            _t18 = Math.fma(alpha, _targetz - _selfz, _selfz);
            _t19 = Math.fma(alpha, _targetx - _selfx, _selfx);
            _t20 = Math.fma(alpha, _targety - _selfy, _selfy);
        }
        float _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        return nlerpShortest_unsafe_s2db22c00_1(dest, _t17, _t18, _t19, _t20, _t24, (1.0f / (float) java.lang.Math.sqrt(_t24)));
    }

    /** Piece 2 of {@code nlerpShortest_unsafe}, split to fit the inline budget; reached only through it. */
    private static long nlerpShortest_unsafe_s2db22c00_1(long dest, float _t17, float _t18, float _t19, float _t20, float _t24, float _t25) {
        if (_t24 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t19 * _t25);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t20 * _t25);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t18 * _t25);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t17 * _t25);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 1.0f - alpha;
        float _t1 = _selfw + targetW;
        float _t2 = _selfz + targetZ;
        float _t3 = _selfx + targetX;
        float _t4 = _selfy + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = java.lang.Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) java.lang.Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _t26, _t27;
        if (_t19 > 2.0E-6f) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0f - _t5;
                _t27 = _t5;
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t27, targetX * _t26));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t27, targetY * _t26));
        return slerp_unsafe_s93c01fea_1(dest, targetZ, targetW, _selfz, _selfw, _t26, _t27);
    }

    /** Piece 2 of {@code slerp_unsafe}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_s93c01fea_1(long dest, float targetZ, float targetW, float _selfz, float _selfw, float _t26, float _t27) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, _t27, targetZ * _t26));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t27, targetW * _t26));
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long target, float alpha) {
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
        return slerp_unsafe_s58c21ac7_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, _targetx, _targety, _targetz, _targetw, 1.0f - alpha, alpha < 0.5f ? 1.0f : 0.0f, java.lang.Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4)))));
    }

    /** Piece 2 of {@code slerp_unsafe}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_s58c21ac7_1(long dest, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _targetx, float _targety, float _targetz, float _targetw, float _t0, float _t5, float _t11) {
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) java.lang.Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _t26, _t27;
        if (_t19 > 2.0E-6f) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0f - _t5;
                _t27 = _t5;
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t27, _targetx * _t26));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t27, _targety * _t26));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, _t27, _targetz * _t26));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t27, _targetw * _t26));
        return dest;
    }

    public static long slerpShortest_unsafe(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(_selfw, targetW, Math.fma(_selfz, targetZ, Math.fma(_selfx, targetX, _selfy * targetY)));
        float _t16 = Math.acos(java.lang.Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
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
        return slerpShortest_unsafe_s3d6ffb6c_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t17, 1.0f / _t17, Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest_unsafe}, split to fit the inline budget; reached only through it. */
    private static long slerpShortest_unsafe_s3d6ffb6c_1(long dest, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
            _t42 = Math.fma(_selfw, _t25, _t19 * _t21) * _t17_inv;
            _t43 = Math.fma(_selfz, _t25, _t19 * _t22) * _t17_inv;
            _t44 = Math.fma(_selfx, _t25, _t19 * _t23) * _t17_inv;
            _t45 = Math.fma(_selfy, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = Math.fma(alpha, _t21, _selfw * _t0);
            _t43 = Math.fma(alpha, _t22, _selfz * _t0);
            _t44 = Math.fma(alpha, _t23, _selfx * _t0);
            _t45 = Math.fma(alpha, _t24, _selfy * _t0);
        }
        float _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        float _t50 = (1.0f / (float) java.lang.Math.sqrt(_t49));
        if (_t49 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t50 * _t44);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t50 * _t45);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t50 * _t43);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t50 * _t42);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long slerpShortest_unsafe(long dest, long src, long target, float alpha) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t16 = Math.acos(java.lang.Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
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
        return slerpShortest_unsafe_s265d2a01_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t17, 1.0f / _t17, Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest_unsafe}, split to fit the inline budget; reached only through it. */
    private static long slerpShortest_unsafe_s265d2a01_1(long dest, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
            _t42 = Math.fma(_selfw, _t25, _t19 * _t21) * _t17_inv;
            _t43 = Math.fma(_selfz, _t25, _t19 * _t22) * _t17_inv;
            _t44 = Math.fma(_selfx, _t25, _t19 * _t23) * _t17_inv;
            _t45 = Math.fma(_selfy, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = Math.fma(alpha, _t21, _selfw * _t0);
            _t43 = Math.fma(alpha, _t22, _selfz * _t0);
            _t44 = Math.fma(alpha, _t23, _selfx * _t0);
            _t45 = Math.fma(alpha, _t24, _selfy * _t0);
        }
        float _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        float _t50 = (1.0f / (float) java.lang.Math.sqrt(_t49));
        if (_t49 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t50 * _t44);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t50 * _t45);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t50 * _t43);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t50 * _t42);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long squad_unsafe(long dest, long src, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        float _t0 = 1.0f - t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        float _t12 = 1.0f - _t7;
        float _t25 = java.lang.Math.min(4.0f, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6))));
        float _t27 = quatArcAngle(_t25);
        float _t29 = 4.0f - _t25;
        float _t41 = (float) java.lang.Math.sqrt(_t29 * _t25);
        float _t45 = 2.0f / _t41;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t45 * Math.sin(t * _t27);
            _t57 = _t45 * Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        return squad_unsafe_sf7cd6cd4_1(dest, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t0, _t7, _t12, _t55, _t57, _selfx, _selfy, _selfz, _selfw, t + t, _selfw + targetW, _selfz + targetZ, _selfx + targetX);
    }

    /** Piece 2 of {@code squad_unsafe}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_sf7cd6cd4_1(long dest, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, float _t0, float _t7, float _t12, float _t55, float _t57, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t8, float _t9, float _t10) {
        float _t11 = _selfy + targetY;
        float _t13 = _t0 * _t1;
        float _t26 = java.lang.Math.min(4.0f, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11))));
        float _t28 = quatArcAngle(_t26);
        float _t30 = 4.0f - _t26;
        float _t43 = (float) java.lang.Math.sqrt(_t30 * _t26);
        float _t46 = 2.0f / _t43;
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t46 * Math.sin(t * _t28);
            _t58 = _t46 * Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        float _t68 = Math.fma(control0W, _t57, control1W * _t55);
        float _t69 = Math.fma(_selfw, _t58, targetW * _t56);
        float _t70 = Math.fma(control0Z, _t57, control1Z * _t55);
        float _t71 = Math.fma(_selfz, _t58, targetZ * _t56);
        return squad_unsafe_sf7cd6cd4_2(dest, _t13, Math.fma(-_t0, _t1, 1.0f), _t13 < 0.5f ? 1.0f : 0.0f, Math.fma(control0X, _t57, control1X * _t55), _t68, _t69, _t70, _t71, Math.fma(_selfx, _t58, targetX * _t56), Math.fma(control0Y, _t57, control1Y * _t55), Math.fma(_selfy, _t58, targetY * _t56), _t68 + _t69, _t70 + _t71);
    }

    /** Piece 3 of {@code squad_unsafe}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_sf7cd6cd4_2(long dest, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76) {
        float _t77 = _t67 + _t72;
        float _t78 = _t73 + _t74;
        float _t83 = java.lang.Math.min(4.0f, Math.fma(_t75, _t75, Math.fma(_t76, _t76, Math.fma(_t77, _t77, _t78 * _t78))));
        float _t84 = quatArcAngle(_t83);
        float _t85 = 4.0f - _t83;
        float _t91 = (float) java.lang.Math.sqrt(_t85 * _t83);
        float _t93 = 2.0f / _t91;
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
                _t99 = _t17;
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t67, _t98, _t72 * _t99));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t73, _t98, _t74 * _t99));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t70, _t98, _t71 * _t99));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t68, _t98, _t69 * _t99));
        return dest;
    }

    public static long squad_unsafe(long dest, long src, long control0, long control1, long target, float t) {
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
        return squad_unsafe_s7cc6a34_1(dest, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, 1.0f - t, t + t, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, t < 0.5f ? 1.0f : 0.0f, _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety);
    }

    /** Piece 2 of {@code squad_unsafe}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_s7cc6a34_1(long dest, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t0, float _t1, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11) {
        float _t13 = _t0 * _t1;
        return squad_unsafe_s7cc6a34_2(dest, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, _t7, 1.0f - _t7, _t13, Math.fma(-_t0, _t1, 1.0f), _t13 < 0.5f ? 1.0f : 0.0f, java.lang.Math.min(4.0f, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6)))), java.lang.Math.min(4.0f, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11)))));
    }

    /** Piece 3 of {@code squad_unsafe}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_s7cc6a34_2(long dest, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t0, float _t7, float _t12, float _t13, float _t14, float _t17, float _t25, float _t26) {
        float _t27 = quatArcAngle(_t25);
        float _t28 = quatArcAngle(_t26);
        float _t29 = 4.0f - _t25;
        float _t30 = 4.0f - _t26;
        float _t41 = (float) java.lang.Math.sqrt(_t29 * _t25);
        float _t43 = (float) java.lang.Math.sqrt(_t30 * _t26);
        float _t45 = 2.0f / _t41;
        float _t46 = 2.0f / _t43;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t45 * Math.sin(t * _t27);
            _t57 = _t45 * Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t46 * Math.sin(t * _t28);
            _t58 = _t46 * Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        return squad_unsafe_s7cc6a34_3(dest, _t13, _t14, _t17, Math.fma(_control0x, _t57, _control1x * _t55), Math.fma(_control0w, _t57, _control1w * _t55), Math.fma(_selfw, _t58, _targetw * _t56), Math.fma(_control0z, _t57, _control1z * _t55), Math.fma(_selfz, _t58, _targetz * _t56), Math.fma(_selfx, _t58, _targetx * _t56), Math.fma(_control0y, _t57, _control1y * _t55), Math.fma(_selfy, _t58, _targety * _t56));
    }

    /** Piece 4 of {@code squad_unsafe}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_s7cc6a34_3(long dest, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74) {
        float _t75 = _t68 + _t69;
        float _t76 = _t70 + _t71;
        float _t77 = _t67 + _t72;
        float _t78 = _t73 + _t74;
        float _t83 = java.lang.Math.min(4.0f, Math.fma(_t75, _t75, Math.fma(_t76, _t76, Math.fma(_t77, _t77, _t78 * _t78))));
        float _t84 = quatArcAngle(_t83);
        float _t85 = 4.0f - _t83;
        float _t91 = (float) java.lang.Math.sqrt(_t85 * _t83);
        float _t93 = 2.0f / _t91;
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
                _t99 = _t17;
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t67, _t98, _t72 * _t99));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t73, _t98, _t74 * _t99));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t70, _t98, _t71 * _t99));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t68, _t98, _t69 * _t99));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(otherX, _selfz, otherW * _selfy) + Math.fma(otherY, _selfw, -(otherZ * _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(otherY, _selfx, otherZ * _selfw) + Math.fma(otherW, _selfz, -(otherX * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz));
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
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_otherx, _selfz, _otherw * _selfy) + Math.fma(_othery, _selfw, -(_otherz * _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_othery, _selfx, _otherz * _selfw) + Math.fma(_otherw, _selfz, -(_otherx * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherY, _selfz, -(otherZ * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(otherY, _selfw, otherZ * _selfx) + Math.fma(otherW, _selfy, -(otherX * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(otherX, _selfy, otherW * _selfz) + Math.fma(otherZ, _selfw, -(otherY * _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_othery, _selfz, -(_otherz * _selfy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_othery, _selfw, _otherz * _selfx) + Math.fma(_otherw, _selfy, -(_otherx * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_otherx, _selfy, _otherw * _selfz) + Math.fma(_otherz, _selfw, -(_othery * _selfx)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float weight) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(weight, otherX, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(weight, otherY, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(weight, otherZ, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(weight, otherW, _selfw));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long other, float weight) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(weight, _otherx, _selfx));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(weight, _othery, _selfy));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(weight, _otherz, _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(weight, _otherw, _selfw));
        return dest;
    }

    public static float angle_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        return 2.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))), UnsafeOpsHolder.U.getFloat(src + 12L));
    }

    public static float angleTo_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9, _t10, _t11, _t12;
        if (-Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy))) > 0.0f) {
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
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), (float) java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    public static float angleTo_unsafe(long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t9, _t10, _t11, _t12;
        if (-Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy))) > 0.0f) {
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
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), (float) java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    public static long axis_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        UnsafeOpsHolder.U.putFloat(dest, _selfx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz);
        UnsafeOpsHolder.U.putFloat(dest + 12L, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0f))))));
        return dest;
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t20 = Math.fma(qX, _selfy, qW * _selfz) + Math.fma(qZ, _selfw, -(qY * _selfx));
        float _t21 = Math.fma(qY, _selfw, qZ * _selfx) + Math.fma(qW, _selfy, -(qX * _selfz));
        float _t22 = Math.fma(qX, _selfw, qW * _selfx) + Math.fma(qY, _selfz, -(qZ * _selfy));
        float _t23 = Math.fma(qW, _selfw, -(qX * _selfx)) - Math.fma(qY, _selfy, qZ * _selfz);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(qY, _t20, -(qZ * _t21)) + Math.fma(qW, _t22, -(qX * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(qZ, _t22, -(qY * _t23)) + Math.fma(qW, _t21, -(qX * _t20)));
        return conjugateBy_unsafe_s1552883d_1(dest, qX, qY, qZ, qW, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_unsafe}, split to fit the inline budget; reached only through it. */
    private static long conjugateBy_unsafe_s1552883d_1(long dest, float qX, float qY, float qZ, float qW, float _t20, float _t21, float _t22, float _t23) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(qX, _t21, qW * _t20) + Math.fma(-qY, _t22, -(qZ * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(qX, _t22, qW * _t23) - Math.fma(-qZ, _t20, -(qY * _t21)));
        return dest;
    }

    public static long conjugateBy_unsafe(long dest, long src, long q) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _qx = UnsafeOpsHolder.U.getFloat(q);
        float _qy = UnsafeOpsHolder.U.getFloat(q + 4L);
        float _qz = UnsafeOpsHolder.U.getFloat(q + 8L);
        float _qw = UnsafeOpsHolder.U.getFloat(q + 12L);
        float _t20 = Math.fma(_qx, _selfy, _qw * _selfz) + Math.fma(_qz, _selfw, -(_qy * _selfx));
        float _t21 = Math.fma(_qy, _selfw, _qz * _selfx) + Math.fma(_qw, _selfy, -(_qx * _selfz));
        float _t22 = Math.fma(_qx, _selfw, _qw * _selfx) + Math.fma(_qy, _selfz, -(_qz * _selfy));
        float _t23 = Math.fma(_qw, _selfw, -(_qx * _selfx)) - Math.fma(_qy, _selfy, _qz * _selfz);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_qy, _t20, -(_qz * _t21)) + Math.fma(_qw, _t22, -(_qx * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_qz, _t22, -(_qy * _t23)) + Math.fma(_qw, _t21, -(_qx * _t20)));
        return conjugateBy_unsafe_s65a54d08_1(dest, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_unsafe}, split to fit the inline budget; reached only through it. */
    private static long conjugateBy_unsafe_s65a54d08_1(long dest, float _qx, float _qy, float _qz, float _qw, float _t20, float _t21, float _t22, float _t23) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_qx, _t21, _qw * _t20) + Math.fma(-_qy, _t22, -(_qz * _t23)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_qx, _t22, _qw * _t23) - Math.fma(-_qz, _t20, -(_qy * _t21)));
        return dest;
    }

    public static long difference_unsafe(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3_inv = 1.0f / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        UnsafeOpsHolder.U.putFloat(dest, (Math.fma(otherX, _selfw, -(otherW * _selfx)) + Math.fma(otherY, _selfz, -(otherZ * _selfy))) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(otherW * _sp0) - otherX * _sp1 + Math.fma(otherY, _selfw, otherZ * _selfx) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, (Math.fma(otherX, _selfy, -(otherW * _selfz)) + Math.fma(otherZ, _selfw, -(otherY * _selfx))) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(otherX, _selfx, otherW * _selfw) * _t3_inv - (-(otherY * _sp0) - otherZ * _sp1));
        return dest;
    }

    public static long difference_unsafe(long dest, long src, long other) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _otherx = UnsafeOpsHolder.U.getFloat(other);
        float _othery = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _otherz = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _otherw = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t3_inv = 1.0f / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        UnsafeOpsHolder.U.putFloat(dest, (Math.fma(_otherx, _selfw, -(_otherw * _selfx)) + Math.fma(_othery, _selfz, -(_otherz * _selfy))) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_otherw * _sp0) - _otherx * _sp1 + Math.fma(_othery, _selfw, _otherz * _selfx) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 8L, (Math.fma(_otherx, _selfy, -(_otherw * _selfz)) + Math.fma(_otherz, _selfw, -(_othery * _selfx))) * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_otherx, _selfx, _otherw * _selfw) * _t3_inv - (-(_othery * _sp0) - _otherz * _sp1));
        return dest;
    }

    public static float dot_unsafe(long src, float otherX, float otherY, float otherZ, float otherW) {
        return Math.fma(otherW, UnsafeOpsHolder.U.getFloat(src + 12L), Math.fma(otherZ, UnsafeOpsHolder.U.getFloat(src + 8L), Math.fma(otherX, UnsafeOpsHolder.U.getFloat(src), otherY * UnsafeOpsHolder.U.getFloat(src + 4L))));
    }

    public static float dot_unsafe(long src, long other) {
        return Math.fma(UnsafeOpsHolder.U.getFloat(other + 12L), UnsafeOpsHolder.U.getFloat(src + 12L), Math.fma(UnsafeOpsHolder.U.getFloat(other + 8L), UnsafeOpsHolder.U.getFloat(src + 8L), Math.fma(UnsafeOpsHolder.U.getFloat(other), UnsafeOpsHolder.U.getFloat(src), UnsafeOpsHolder.U.getFloat(other + 4L) * UnsafeOpsHolder.U.getFloat(src + 4L))));
    }

    public static long exp_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _t2 = java.lang.Math.min(Math.exp(UnsafeOpsHolder.U.getFloat(src + 12L)), 3.4028235E38f);
        float _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        float _t7 = Math.sin(_t5);
        float _t10 = _t2 * (_t7 / _t5);
        if (_t4 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _selfx * _t10);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _selfy * _t10);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _selfz * _t10);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosFromSin(_t7, _t5) * _t2);
        return dest;
    }

    public static long getEulerAnglesXYZ_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfy * _selfz;
        float _t3 = _selfz * _selfz;
        float _t8 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(2.0f * Math.fma(_selfx, _selfw, _t1), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t3), 1.0f)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t9, _t10));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t3), 1.0f)));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12)));
        return dest;
    }

    public static long getEulerAnglesXZY_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfy * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfw, _t1);
        float _t8 = 2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(2.0f * Math.fma(_selfx, _selfw, -_t1), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t7, _t9));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f)));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static long getEulerAnglesYXZ_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = _selfz * _selfz;
        float _t8 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, -(_selfy * _selfz));
        float _t10 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t3), 1.0f)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t8, _t10));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t3), 1.0f)));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12)));
        return dest;
    }

    public static long getEulerAnglesYZX_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t8 = 2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(2.0f * Math.fma(_selfx, _selfw, -(_selfy * _selfz)), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t8, _t9));
        }
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static long getEulerAnglesZXY_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = _selfz * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t8 = 2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t1), 1.0f)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t8, _t9));
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static long getEulerAnglesZYX_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = _selfz * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t8 = 2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f)));
        } else {
            UnsafeOpsHolder.U.putFloat(dest, Math.atan2(2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, Math.atan2(_t7, _t9));
        }
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static long integrate_unsafe(long dest, long src, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.sin(_t7);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t10 = Math.cosFromSin(_t9, _t7);
        float _t11 = _t9 * (1.0f / (float) java.lang.Math.sqrt(_t6));
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
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t10, _selfw * _t15) + Math.fma(_selfz, _t16, -(_selfy * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfx, _t17, _selfw * _t16) + Math.fma(_selfy, _t10, -(_selfz * _t15)));
        return integrate_unsafe_s236ae959_1(dest, _selfx, _selfy, _selfz, _selfw, _t10, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long integrate_unsafe_s236ae959_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t10, float _t15, float _t16, float _t17) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfy, _t15, _selfz * _t10) + Math.fma(_selfw, _t17, -(_selfx * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t10, -(_selfx * _t15)) - Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    public static long integrate_unsafe(long dest, long src, long angularVel, float dt) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * dt;
        float _t1 = UnsafeOpsHolder.U.getFloat(angularVel + 8L) * _t0;
        float _t2 = UnsafeOpsHolder.U.getFloat(angularVel) * _t0;
        float _t3 = UnsafeOpsHolder.U.getFloat(angularVel + 4L) * _t0;
        float _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.sin(_t7);
        float _t10 = Math.cosFromSin(_t9, _t7);
        float _t11 = _t9 / _t7;
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
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t10, _selfw * _t15) + Math.fma(_selfz, _t16, -(_selfy * _t17)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfx, _t17, _selfw * _t16) + Math.fma(_selfy, _t10, -(_selfz * _t15)));
        return integrate_unsafe_s342f0c06_1(dest, _selfx, _selfy, _selfz, _selfw, _t10, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long integrate_unsafe_s342f0c06_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t10, float _t15, float _t16, float _t17) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfy, _t15, _selfz * _t10) + Math.fma(_selfw, _t17, -(_selfx * _t16)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t10, -(_selfx * _t15)) - Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    public static long invNegativeX_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw)));
        return dest;
    }

    public static long invNormalizedNegativeY_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, -(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw))));
        return dest;
    }

    public static long invNormalizedNegativeZ_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, -(2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
        return dest;
    }

    public static long invNormalizedPositiveX_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw));
        return dest;
    }

    public static long invNormalizedPositiveY_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        return dest;
    }

    public static long invNormalizedPositiveZ_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    public static long invPositiveX_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        return (float) java.lang.Math.sqrt(Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))));
    }

    public static float lengthSquared_unsafe(long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        return Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static long log_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
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
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.log((float) java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2))));
        return dest;
    }

    public static long negativeX_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw))));
        return dest;
    }

    public static long normalizedNegativeY_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, -(2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz)));
        return dest;
    }

    public static long normalizedNegativeZ_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, -(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
        return dest;
    }

    public static long normalizedPositiveX_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(-2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        return dest;
    }

    public static long normalizedPositiveY_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
        UnsafeOpsHolder.U.putFloat(dest + 8L, 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        return dest;
    }

    public static long normalizedPositiveZ_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw));
        UnsafeOpsHolder.U.putFloat(dest + 4L, 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    public static long positiveX_unsafe(long dest, long src) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t12 = java.lang.Math.min(Math.exp(t == 0.0f ? 0.0f : t * Math.log((float) java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2)))), 3.4028235E38f);
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
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (float) java.lang.Math.sqrt(_t25);
        float _t28 = Math.sin(_t26);
        return pow_unsafe_s3ac3da3c_1(dest, _t12, _t20, _t21, _t22, _t25, _t26, _t28, _t12 * (_t28 / _t26));
    }

    /** Piece 2 of {@code pow_unsafe}, split to fit the inline budget; reached only through it. */
    private static long pow_unsafe_s3ac3da3c_1(long dest, float _t12, float _t20, float _t21, float _t22, float _t25, float _t26, float _t28, float _t31) {
        if (_t25 > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t21 * _t31);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t22 * _t31);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t20 * _t31);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        }
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosFromSin(_t28, _t26) * _t12);
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float step) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t7 = Math.fma(_selfw, targetW, Math.fma(_selfz, targetZ, Math.fma(_selfx, targetX, _selfy * targetY)));
        float _t11 = Math.acos(java.lang.Math.min(1.0f, java.lang.Math.abs(_t7)));
        float _t12 = Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0f) {
            _t13 = -targetW;
            _t14 = -targetZ;
            _t15 = -targetX;
            _t16 = -targetY;
        } else {
            _t13 = targetW;
            _t14 = targetZ;
            _t15 = targetX;
            _t16 = targetY;
        }
        float _t17 = _selfw - _t13;
        float _t18 = _selfz - _t14;
        float _t19 = _selfx - _t15;
        float _t20 = _selfy - _t16;
        float _t21 = _selfw + _t13;
        float _t22 = _selfz + _t14;
        float _t23 = _selfx + _t15;
        float _t24 = _selfy + _t16;
        return rotateTowards_unsafe_s7f7168a3_1(dest, step, _selfx, _selfy, _selfz, _selfw, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, 4.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24))))));
    }

    /** Piece 2 of {@code rotateTowards_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_s7f7168a3_1(long dest, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t36) {
        float _t39 = _t36 > 0.0f ? java.lang.Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        float _t42 = Math.sin(_t11 * _t39);
        float _t44 = Math.sin(_t40 * _t11);
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
            _t65 = Math.fma(_selfw, _t44, _t42 * _t13) * _t12_inv;
            _t66 = Math.fma(_selfz, _t44, _t42 * _t14) * _t12_inv;
            _t67 = Math.fma(_selfx, _t44, _t42 * _t15) * _t12_inv;
            _t68 = Math.fma(_selfy, _t44, _t42 * _t16) * _t12_inv;
        } else {
            _t65 = Math.fma(_selfw, _t40, _t13 * _t39);
            _t66 = Math.fma(_selfz, _t40, _t14 * _t39);
            _t67 = Math.fma(_selfx, _t40, _t15 * _t39);
            _t68 = Math.fma(_selfy, _t40, _t16 * _t39);
        }
        float _t72 = Math.fma(_t65, _t65, Math.fma(_t66, _t66, Math.fma(_t67, _t67, _t68 * _t68)));
        return rotateTowards_unsafe_s7f7168a3_2(dest, _t65, _t66, _t67, _t68, _t72, (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_s7f7168a3_2(long dest, float _t65, float _t66, float _t67, float _t68, float _t72, float _t73) {
        if (_t72 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t73 * _t67);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t73 * _t68);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t73 * _t66);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t73 * _t65);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, long target, float step) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _targetx = UnsafeOpsHolder.U.getFloat(target);
        float _targety = UnsafeOpsHolder.U.getFloat(target + 4L);
        float _targetz = UnsafeOpsHolder.U.getFloat(target + 8L);
        float _targetw = UnsafeOpsHolder.U.getFloat(target + 12L);
        float _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t11 = Math.acos(java.lang.Math.min(1.0f, java.lang.Math.abs(_t7)));
        float _t12 = Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0f) {
            _t13 = -_targetw;
            _t14 = -_targetz;
            _t15 = -_targetx;
            _t16 = -_targety;
        } else {
            _t13 = _targetw;
            _t14 = _targetz;
            _t15 = _targetx;
            _t16 = _targety;
        }
        return rotateTowards_unsafe_sf3c324a_1(dest, step, _selfx, _selfy, _selfz, _selfw, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, _selfw - _t13, _selfz - _t14, _selfx - _t15, _selfy - _t16, _selfw + _t13, _selfz + _t14, _selfx + _t15, _selfy + _t16);
    }

    /** Piece 2 of {@code rotateTowards_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_sf3c324a_1(long dest, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
        float _t36 = 4.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? java.lang.Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        float _t42 = Math.sin(_t11 * _t39);
        float _t44 = Math.sin(_t40 * _t11);
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
            _t65 = Math.fma(_selfw, _t44, _t42 * _t13) * _t12_inv;
            _t66 = Math.fma(_selfz, _t44, _t42 * _t14) * _t12_inv;
            _t67 = Math.fma(_selfx, _t44, _t42 * _t15) * _t12_inv;
            _t68 = Math.fma(_selfy, _t44, _t42 * _t16) * _t12_inv;
        } else {
            _t65 = Math.fma(_selfw, _t40, _t13 * _t39);
            _t66 = Math.fma(_selfz, _t40, _t14 * _t39);
            _t67 = Math.fma(_selfx, _t40, _t15 * _t39);
            _t68 = Math.fma(_selfy, _t40, _t16 * _t39);
        }
        float _t72 = Math.fma(_t65, _t65, Math.fma(_t66, _t66, Math.fma(_t67, _t67, _t68 * _t68)));
        return rotateTowards_unsafe_sf3c324a_2(dest, _t65, _t66, _t67, _t68, _t72, (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_sf3c324a_2(long dest, float _t65, float _t66, float _t67, float _t68, float _t72, float _t73) {
        if (_t72 != 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _t73 * _t67);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _t73 * _t68);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _t73 * _t66);
            UnsafeOpsHolder.U.putFloat(dest + 12L, _t73 * _t65);
        } else {
            UnsafeOpsHolder.U.putFloat(dest, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.0f);
        }
        return dest;
    }

    public static long lookAlong_unsafe(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t19 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t20 = Math.fma(_t19, _t12, upX);
        float _t21 = Math.fma(_t19, _t13, upY);
        float _t22 = Math.fma(_t19, _t11, upZ);
        float _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        float _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        float _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        return lookAlong_unsafe_s93c4579a_1(dest, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t29, _t30, _t31, _t34, (1.0f / (float) java.lang.Math.sqrt(_t34)));
    }

    /** Piece 2 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s93c4579a_1(long dest, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t29, float _t30, float _t31, float _t34, float _t35) {
        float _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0f;
            _t40 = 0.0f;
            _t41 = 0.0f;
        }
        float _t42 = -_t40;
        float _t43 = -_t39;
        float _t45 = 1.0f + _t39;
        float _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        return lookAlong_unsafe_s93c4579a_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t42, _t12 - _t40, _t40 + _t12, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11)), _t73, _t74, Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s93c4579a_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t39, float _t42, float _t49, float _t50, float _t61, float _t66, float _t68, float _t69, float _t70, float _t72, float _t73, float _t74, float _t75, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        float _t120, _t121, _t122, _t123;
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            _t120 = _sp0 * _t69;
            _t121 = _sp0 * _t49;
            _t122 = 0.5f * (float) java.lang.Math.sqrt(_t72);
            _t123 = _sp0 * _t68;
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                _t120 = _sp1 * _t50;
                _t121 = _sp1 * _t70;
                _t122 = _sp1 * _t68;
                _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            } else {
                if (_t61 > _t11) {
                    _t120 = _sp2 * _t66;
                    _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                    _t122 = _sp2 * _t49;
                    _t123 = _sp2 * _t70;
                } else {
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t121 = _sp3 * _t66;
                    _t122 = _sp3 * _t69;
                    _t123 = _sp3 * _t50;
                }
            }
        }
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t122, _selfw * _t123) + Math.fma(_selfy, _t120, -(_selfz * _t121)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t122, _selfz * _t123) + Math.fma(_selfw, _t121, -(_selfx * _t120)));
        return lookAlong_unsafe_s93c4579a_3(dest, _selfx, _selfy, _selfz, _selfw, _t120, _t121, _t122, _t123);
    }

    /** Piece 4 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s93c4579a_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t120, float _t121, float _t122, float _t123) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t121, _selfw * _t120) + Math.fma(_selfz, _t122, -(_selfy * _t123)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t122, -(_selfx * _t123)) - Math.fma(_selfy, _t121, _selfz * _t120));
        return dest;
    }

    public static long lookAlong_unsafe(long dest, long src, long dir, long up) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t19 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t20 = Math.fma(_t19, _t12, _upx);
        float _t21 = Math.fma(_t19, _t13, _upy);
        float _t22 = Math.fma(_t19, _t11, _upz);
        return lookAlong_unsafe_s6c6e4e50_1(dest, _selfx, _selfy, _selfz, _selfw, _upx, _upy, _upz, _t11, _t12, _t13, _t20, _t22, Math.fma(_t20, _t13, -(_t21 * _t12)), Math.fma(_t21, _t11, -(_t22 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s6c6e4e50_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t20, float _t22, float _t29, float _t30) {
        float _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        float _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(_t34));
        float _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0f;
            _t40 = 0.0f;
            _t41 = 0.0f;
        }
        float _t42 = -_t40;
        float _t43 = -_t39;
        float _t45 = 1.0f + _t39;
        return lookAlong_unsafe_s6c6e4e50_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t40, _t42, _t43, _t12 - _t40, _t40 + _t12, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11)), Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11)));
    }

    /** Piece 3 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s6c6e4e50_2(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t39, float _t40, float _t42, float _t43, float _t49, float _t50, float _t61, float _t66, float _t68, float _t69, float _t70, float _t72, float _t73, float _t74) {
        float _t75 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        float _t120, _t121, _t122, _t123;
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            _t120 = _sp0 * _t69;
            _t121 = _sp0 * _t49;
            _t122 = 0.5f * (float) java.lang.Math.sqrt(_t72);
            _t123 = _sp0 * _t68;
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                _t120 = _sp1 * _t50;
                _t121 = _sp1 * _t70;
                _t122 = _sp1 * _t68;
                _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            } else {
                if (_t61 > _t11) {
                    _t120 = _sp2 * _t66;
                    _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                    _t122 = _sp2 * _t49;
                    _t123 = _sp2 * _t70;
                } else {
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t121 = _sp3 * _t66;
                    _t122 = _sp3 * _t69;
                    _t123 = _sp3 * _t50;
                }
            }
        }
        return lookAlong_unsafe_s6c6e4e50_3(dest, _selfx, _selfy, _selfz, _selfw, _t120, _t121, _t122, _t123);
    }

    /** Piece 4 of {@code lookAlong_unsafe}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_unsafe_s6c6e4e50_3(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t120, float _t121, float _t122, float _t123) {
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t122, _selfw * _t123) + Math.fma(_selfy, _t120, -(_selfz * _t121)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t122, _selfz * _t123) + Math.fma(_selfw, _t121, -(_selfx * _t120)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t121, _selfw * _t120) + Math.fma(_selfz, _t122, -(_selfy * _t123)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t122, -(_selfx * _t123)) - Math.fma(_selfy, _t121, _selfz * _t120));
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        UnsafeOpsHolder.U.putFloat(dest, axisX * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, axisY * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, axisZ * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, long axis, float angle) {
        float _axisx = UnsafeOpsHolder.U.getFloat(axis);
        float _axisy = UnsafeOpsHolder.U.getFloat(axis + 4L);
        float _axisz = UnsafeOpsHolder.U.getFloat(axis + 8L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        UnsafeOpsHolder.U.putFloat(dest, _axisx * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _axisy * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _axisz * _t1);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static long makeRotationLookAlong_unsafe(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t19 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t20 = Math.fma(_t19, _t12, upX);
        float _t21 = Math.fma(_t19, _t13, upY);
        float _t22 = Math.fma(_t19, _t11, upZ);
        float _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        float _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        float _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(_t34));
        float _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0f;
            _t40 = 0.0f;
            _t41 = 0.0f;
        }
        float _t42 = -_t40;
        float _t43 = -_t39;
        float _t45 = 1.0f + _t39;
        float _t49 = _t40 + _t12;
        float _t50 = _t12 - _t40;
        float _t61 = Math.fma(_t39, _t11, -(_t40 * _t12));
        float _t66 = Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13));
        float _t67 = Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13));
        float _t69 = Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41));
        float _t70 = Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41));
        float _t72 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11));
        float _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        float _t75 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t50);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t70);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t72));
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t73));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t69);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t49);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t67);
            } else {
                if (_t61 > _t11) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t69);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t50);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t49);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t70);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_unsafe(long dest, long dir, long up) {
        float _dirx = UnsafeOpsHolder.U.getFloat(dir);
        float _diry = UnsafeOpsHolder.U.getFloat(dir + 4L);
        float _dirz = UnsafeOpsHolder.U.getFloat(dir + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(up);
        float _upy = UnsafeOpsHolder.U.getFloat(up + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(up + 8L);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t19 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t20 = Math.fma(_t19, _t12, _upx);
        float _t21 = Math.fma(_t19, _t13, _upy);
        float _t22 = Math.fma(_t19, _t11, _upz);
        float _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        float _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        float _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(_t34));
        float _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0f;
            _t40 = 0.0f;
            _t41 = 0.0f;
        }
        float _t42 = -_t40;
        float _t43 = -_t39;
        float _t45 = 1.0f + _t39;
        float _t49 = _t40 + _t12;
        float _t50 = _t12 - _t40;
        float _t61 = Math.fma(_t39, _t11, -(_t40 * _t12));
        float _t66 = Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13));
        float _t67 = Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13));
        float _t69 = Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41));
        float _t70 = Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41));
        float _t72 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11));
        float _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        float _t75 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            UnsafeOpsHolder.U.putFloat(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _t50);
            UnsafeOpsHolder.U.putFloat(dest + 8L, _sp0 * _t70);
            UnsafeOpsHolder.U.putFloat(dest + 12L, 0.5f * (float) java.lang.Math.sqrt(_t72));
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                UnsafeOpsHolder.U.putFloat(dest, 0.5f * (float) java.lang.Math.sqrt(_t73));
                UnsafeOpsHolder.U.putFloat(dest + 4L, _sp3 * _t69);
                UnsafeOpsHolder.U.putFloat(dest + 8L, _sp3 * _t49);
                UnsafeOpsHolder.U.putFloat(dest + 12L, _sp3 * _t67);
            } else {
                if (_t61 > _t11) {
                    UnsafeOpsHolder.U.putFloat(dest, _sp1 * _t69);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _t50);
                } else {
                    UnsafeOpsHolder.U.putFloat(dest, _sp2 * _t49);
                    UnsafeOpsHolder.U.putFloat(dest + 4L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putFloat(dest + 8L, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putFloat(dest + 12L, _sp2 * _t70);
                }
            }
        }
        return dest;
    }

    public static long makeRotationTo_unsafe(long dest, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
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
        float _t15 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t16 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        float _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_unsafe_s872480c0_1(dest, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_s872480c0_1(long dest, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
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
        float _t15 = Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        float _t16 = Math.fma(_fromDirx, _toDiry, -(_fromDiry * _toDirx));
        float _t17 = Math.fma(_fromDirz, _toDirx, -(_fromDirx * _toDirz));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_unsafe_s388f5ce2_1(dest, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_s388f5ce2_1(long dest, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
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

    public static long makeRotationX_unsafe(long dest, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        UnsafeOpsHolder.U.putFloat(dest, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static long makeRotationXYZ_unsafe(long dest, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t10, _t7, _t11 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t11, _t7, -(_t10 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t9, _t7, _t12 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t12, _t7, -(_t9 * _t5)));
        return dest;
    }

    public static long makeRotationXZY_unsafe(long dest, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t10, _t7, -(_t11 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t12, _t5, -(_t9 * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t10, _t5, _t11 * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t9, _t5, _t12 * _t7));
        return dest;
    }

    public static long makeRotationY_unsafe(long dest, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static long makeRotationYXZ_unsafe(long dest, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t10, _t7, _t11 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t11, _t7, -(_t10 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t12, _t5, -(_t9 * _t7)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t9, _t5, _t12 * _t7));
        return dest;
    }

    public static long makeRotationYZX_unsafe(long dest, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t5, _t2);
        float _t7 = Math.cosFromSin(_t3, _t0);
        float _t8 = Math.cosFromSin(_t4, _t1);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t8;
        float _t11 = _t4 * _t7;
        float _t12 = _t7 * _t8;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t9, _t6, _t12 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t10, _t6, _t11 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t11, _t6, -(_t10 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t12, _t6, -(_t9 * _t5)));
        return dest;
    }

    public static long makeRotationZ_unsafe(long dest, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        UnsafeOpsHolder.U.putFloat(dest, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static long makeRotationZXY_unsafe(long dest, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t4, _t1);
        float _t7 = Math.cosFromSin(_t5, _t2);
        float _t8 = Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t10, _t7, -(_t11 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t9, _t7, _t12 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t10, _t5, _t11 * _t7));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t12, _t7, -(_t9 * _t5)));
        return dest;
    }

    public static long makeRotationZYX_unsafe(long dest, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_t12, _t5, -(_t9 * _t8)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_t10, _t8, _t11 * _t5));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_t11, _t8, -(_t10 * _t5)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_t9, _t5, _t12 * _t8));
        return dest;
    }

    public static long preRotateX_unsafe(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t2, _selfw * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t2, -(_selfz * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfy, _t1, _selfz * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t2, -(_selfx * _t1)));
        return dest;
    }

    public static long preRotateY_unsafe(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t2, _selfz * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t2, _selfw * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, _t2, -(_selfx * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t2, -(_selfy * _t1)));
        return dest;
    }

    public static long preRotateZ_unsafe(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t2, -(_selfy * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfx, _t1, _selfy * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, _t2, _selfw * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t2, -(_selfz * _t1)));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t5, _selfw * _t2) + Math.fma(_selfy, _t3, -(_selfz * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t5, _selfz * _t2) + Math.fma(_selfw, _t4, -(_selfx * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t4, _selfw * _t3) + Math.fma(_selfz, _t5, -(_selfy * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t5, -(_selfx * _t2)) - Math.fma(_selfy, _t4, _selfz * _t3));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = UnsafeOpsHolder.U.getFloat(axis) * _t1;
        float _t3 = UnsafeOpsHolder.U.getFloat(axis + 8L) * _t1;
        float _t4 = UnsafeOpsHolder.U.getFloat(axis + 4L) * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t5, _selfw * _t2) + Math.fma(_selfy, _t3, -(_selfz * _t4)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t5, _selfz * _t2) + Math.fma(_selfw, _t4, -(_selfx * _t3)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t4, _selfw * _t3) + Math.fma(_selfz, _t5, -(_selfy * _t2)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t5, -(_selfx * _t2)) - Math.fma(_selfy, _t4, _selfz * _t3));
        return dest;
    }

    public static long rotateTo_unsafe(long dest, long src, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
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
        float _t15 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        float _t16 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_unsafe_s5f7292ad_1(dest, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_s5f7292ad_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
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
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t44, _selfw * _t45) + Math.fma(_selfy, _t46, -(_selfz * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t44, _selfz * _t45) + Math.fma(_selfw, _t47, -(_selfx * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t47, _selfw * _t46) + Math.fma(_selfz, _t44, -(_selfy * _t45)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t44, -(_selfx * _t45)) - Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    public static long rotateTo_unsafe(long dest, long src, long fromDir, long toDir) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
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
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_unsafe_sed1405b_1(dest, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, Math.fma(_fromDirx, _toDiry, -(_fromDiry * _toDirx)), Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), Math.fma(_fromDirz, _toDirx, -(_fromDirx * _toDirz)), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_sed1405b_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
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
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t44, _selfw * _t45) + Math.fma(_selfy, _t46, -(_selfz * _t47)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t44, _selfz * _t45) + Math.fma(_selfw, _t47, -(_selfx * _t46)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t47, _selfw * _t46) + Math.fma(_selfz, _t44, -(_selfy * _t45)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t44, -(_selfx * _t45)) - Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t2, _selfw * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t2, _selfz * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, _t2, -(_selfy * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t2, -(_selfx * _t1)));
        return dest;
    }

    public static long rotateXYZ_unsafe(long dest, long src, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t21, _selfw * _t19) + Math.fma(_selfy, _t20, -(_selfz * _t22)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t21, _selfz * _t19) + Math.fma(_selfw, _t22, -(_selfx * _t20)));
        return rotateXYZ_unsafe_sbdd7c928_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_sbdd7c928_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t22, _selfw * _t20) + Math.fma(_selfz, _t21, -(_selfy * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t21, -(_selfx * _t19)) - Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    public static long rotateXZY_unsafe(long dest, long src, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t21 = Math.fma(_t10, _t8, -(_t11 * _t5));
        float _t22 = Math.fma(_t12, _t5, -(_t9 * _t8));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t19, _selfw * _t21) + Math.fma(_selfy, _t20, -(_selfz * _t22)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t19, _selfz * _t21) + Math.fma(_selfw, _t22, -(_selfx * _t20)));
        return rotateXZY_unsafe_sa95ff786_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_sa95ff786_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t22, _selfw * _t20) + Math.fma(_selfz, _t19, -(_selfy * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t19, -(_selfx * _t21)) - Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t2, -(_selfz * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t2, _selfw * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t1, _selfz * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t2, -(_selfy * _t1)));
        return dest;
    }

    public static long rotateYXZ_unsafe(long dest, long src, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t19, _selfw * _t20) + Math.fma(_selfy, _t21, -(_selfz * _t22)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t19, _selfz * _t20) + Math.fma(_selfw, _t22, -(_selfx * _t21)));
        return rotateYXZ_unsafe_s3fca413a_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_s3fca413a_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t22, _selfw * _t21) + Math.fma(_selfz, _t19, -(_selfy * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t19, -(_selfx * _t20)) - Math.fma(_selfy, _t22, _selfz * _t21));
        return dest;
    }

    public static long rotateYZX_unsafe(long dest, long src, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t21, _selfw * _t19) + Math.fma(_selfy, _t22, -(_selfz * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t21, _selfz * _t19) + Math.fma(_selfw, _t20, -(_selfx * _t22)));
        return rotateYZX_unsafe_s358828a_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_s358828a_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t20, _selfw * _t22) + Math.fma(_selfz, _t21, -(_selfy * _t19)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t21, -(_selfx * _t19)) - Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    public static long rotateZ_unsafe(long dest, long src, float angle) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = Math.cosFromSin(_t1, _t0);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t2, _selfy * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t2, -(_selfx * _t1)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfz, _t2, _selfw * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t2, -(_selfz * _t1)));
        return dest;
    }

    public static long rotateZXY_unsafe(long dest, long src, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t21, _selfw * _t22) + Math.fma(_selfy, _t19, -(_selfz * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t21, _selfz * _t22) + Math.fma(_selfw, _t20, -(_selfx * _t19)));
        return rotateZXY_unsafe_s6e82c462_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_s6e82c462_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t20, _selfw * _t19) + Math.fma(_selfz, _t21, -(_selfy * _t22)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t21, -(_selfx * _t22)) - Math.fma(_selfy, _t20, _selfz * _t19));
        return dest;
    }

    public static long rotateZYX_unsafe(long dest, long src, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sin(_t0);
        float _t4 = Math.sin(_t1);
        float _t5 = Math.sin(_t2);
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t6 = Math.cosFromSin(_t3, _t0);
        float _t7 = Math.cosFromSin(_t4, _t1);
        float _t8 = Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfx, _t19, _selfw * _t21) + Math.fma(_selfy, _t22, -(_selfz * _t20)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfy, _t19, _selfz * _t21) + Math.fma(_selfw, _t20, -(_selfx * _t22)));
        return rotateZYX_unsafe_s71b42f14_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_unsafe}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_s71b42f14_1(long dest, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t20, _selfw * _t22) + Math.fma(_selfz, _t19, -(_selfy * _t21)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_selfw, _t19, -(_selfx * _t21)) - Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    public static long transform_unsafe(long dest, long src, float vX, float vY, float vZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, vY, -(_selfy * vX));
        float _t10 = 2.0f * Math.fma(_selfz, vX, -(_selfx * vZ));
        float _t11 = 2.0f * Math.fma(_selfy, vZ, -(_selfz * vY));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfy, _t9, Math.fma(-_selfz, _t10, Math.fma(_selfw, _t11, vX))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfz, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, vY))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t10, Math.fma(-_selfy, _t11, Math.fma(_selfw, _t9, vZ))));
        return dest;
    }

    public static long transform_unsafe(long dest, long src, long v) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _t9 = 2.0f * Math.fma(_selfx, _vy, -(_selfy * _vx));
        float _t10 = 2.0f * Math.fma(_selfz, _vx, -(_selfx * _vz));
        float _t11 = 2.0f * Math.fma(_selfy, _vz, -(_selfz * _vy));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfy, _t9, Math.fma(-_selfz, _t10, Math.fma(_selfw, _t11, _vx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfz, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, _vy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfx, _t10, Math.fma(-_selfy, _t11, Math.fma(_selfw, _t9, _vz))));
        return dest;
    }

    public static long transformInverse_unsafe(long dest, long src, float vX, float vY, float vZ) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t9 = 2.0f * Math.fma(_selfx, vZ, -(_selfz * vX));
        float _t10 = 2.0f * Math.fma(_selfy, vX, -(_selfx * vY));
        float _t11 = 2.0f * Math.fma(_selfz, vY, -(_selfy * vZ));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfz, _t9, Math.fma(-_selfy, _t10, Math.fma(_selfw, _t11, vX))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfx, _t10, Math.fma(-_selfz, _t11, Math.fma(_selfw, _t9, vY))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfy, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, vZ))));
        return dest;
    }

    public static long transformInverse_unsafe(long dest, long src, long v) {
        float _selfx = UnsafeOpsHolder.U.getFloat(src);
        float _selfy = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _selfw = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _t9 = 2.0f * Math.fma(_selfx, _vz, -(_selfz * _vx));
        float _t10 = 2.0f * Math.fma(_selfy, _vx, -(_selfx * _vy));
        float _t11 = 2.0f * Math.fma(_selfz, _vy, -(_selfy * _vz));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_selfz, _t9, Math.fma(-_selfy, _t10, Math.fma(_selfw, _t11, _vx))));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_selfx, _t10, Math.fma(-_selfz, _t11, Math.fma(_selfw, _t9, _vy))));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_selfy, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, _vz))));
        return dest;
    }

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]:
     * 2 asin(|a-b|/2) up to pi/2 and pi - 2 asin(|a+b|/2) beyond, so asin always sees an
     * argument of at most sqrt(2)/2 and the angle stays accurate at both ends.
     */
    private static float quatArcAngle(float s) {
        float d = 4.0f - s;
        return s > d ? 2.0f * Math.asin(0.5f * (float) java.lang.Math.sqrt(d))
                : (float) Math.PI - 2.0f * Math.asin(0.5f * (float) java.lang.Math.sqrt(s));
    }
}
