// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link DoubleQuatOps} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code DoubleQuatOps}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class DoubleQuatOpsKernelsAddress {
    private DoubleQuatOpsKernelsAddress() {}

    public static long invert_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _t3_inv = 1.0 / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
            UnsafeOpsHolder.U.putDouble(dest, -(_selfx * _t3_inv));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_selfy * _t3_inv));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_selfz * _t3_inv));
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t3_inv);
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            double _t3_inv = 1.0 / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
            UnsafeOpsHolder.U.putDouble(dest, -(_selfx * _t3_inv));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_selfy * _t3_inv));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_selfz * _t3_inv));
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t3_inv);
            return dest;
        }
    }

    public static long invertProduct_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t20 = java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherZ, _selfy, -(otherY * _selfz));
        double _t21 = java.lang.Math.fma(otherW, _selfw, -otherX * _selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz);
        double _t22 = java.lang.Math.fma(otherY, _selfx, otherZ * _selfw) + java.lang.Math.fma(otherW, _selfz, -otherX * _selfy);
        double _t23 = java.lang.Math.fma(otherX, _selfz, otherW * _selfy) + java.lang.Math.fma(otherY, _selfw, -(otherZ * _selfx));
        double _t27_inv = 1.0 / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        UnsafeOpsHolder.U.putDouble(dest, -(_t20 * _t27_inv));
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t23 * _t27_inv));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t22 * _t27_inv));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t21 * _t27_inv);
        return dest;
    }

    /** {@code invertProduct_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_mulAdd(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t20 = ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherZ) * (_selfy) - (otherY * _selfz));
        double _t21 = ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz));
        double _t22 = ((otherY) * (_selfx) + (otherZ * _selfw)) + ((otherW) * (_selfz) - (otherX * _selfy));
        double _t23 = ((otherX) * (_selfz) + (otherW * _selfy)) + ((otherY) * (_selfw) - (otherZ * _selfx));
        double _t27_inv = 1.0 / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
        UnsafeOpsHolder.U.putDouble(dest, -(_t20 * _t27_inv));
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t23 * _t27_inv));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t22 * _t27_inv));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t21 * _t27_inv);
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_unsafe_fma(dest, src, other);
        return invertProduct_unsafe_mulAdd(dest, src, other);
    }

    /** {@code invertProduct_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_fma(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t20 = java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_otherz, _selfy, -(_othery * _selfz));
        double _t21 = java.lang.Math.fma(_otherw, _selfw, -_otherx * _selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz);
        double _t22 = java.lang.Math.fma(_othery, _selfx, _otherz * _selfw) + java.lang.Math.fma(_otherw, _selfz, -_otherx * _selfy);
        double _t23 = java.lang.Math.fma(_otherx, _selfz, _otherw * _selfy) + java.lang.Math.fma(_othery, _selfw, -(_otherz * _selfx));
        double _t27_inv = 1.0 / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        UnsafeOpsHolder.U.putDouble(dest, -(_t20 * _t27_inv));
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t23 * _t27_inv));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t22 * _t27_inv));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t21 * _t27_inv);
        return dest;
    }

    /** {@code invertProduct_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_mulAdd(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t20 = ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_otherz) * (_selfy) - (_othery * _selfz));
        double _t21 = ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz));
        double _t22 = ((_othery) * (_selfx) + (_otherz * _selfw)) + ((_otherw) * (_selfz) - (_otherx * _selfy));
        double _t23 = ((_otherx) * (_selfz) + (_otherw * _selfy)) + ((_othery) * (_selfw) - (_otherz * _selfx));
        double _t27_inv = 1.0 / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
        UnsafeOpsHolder.U.putDouble(dest, -(_t20 * _t27_inv));
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t23 * _t27_inv));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t22 * _t27_inv));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t21 * _t27_inv);
        return dest;
    }

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

    public static long makeUniformRotation_unsafe(long dest, double u1, double u2, double u3) {
        double _t0 = java.lang.Math.sqrt(u1);
        double _t1 = u2 * 6.283185307179586;
        double _t3 = u3 * 6.283185307179586;
        double _t4 = java.lang.Math.sqrt(1.0 - u1);
        UnsafeOpsHolder.U.putDouble(dest, Math.sinWithCos(_t1) * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.cosWithSin(_t1) * _t4);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.sinWithCos(_t3) * _t0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cosWithSin(_t3) * _t0);
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

    public static long makeFromDualQuat_unsafe(long dest, double dqRX, double dqRY, double dqRZ, double dqRW) {
        UnsafeOpsHolder.U.putDouble(dest, dqRX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, dqRY);
        UnsafeOpsHolder.U.putDouble(dest + 16L, dqRZ);
        UnsafeOpsHolder.U.putDouble(dest + 24L, dqRW);
        return dest;
    }

    public static long makeFromMatrixMat3x3_unsafe(long dest, long m) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m20 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 24L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 32L);
        double _m21 = UnsafeOpsHolder.U.getDouble(m + 40L);
        double _m02 = UnsafeOpsHolder.U.getDouble(m + 48L);
        double _m12 = UnsafeOpsHolder.U.getDouble(m + 56L);
        double _m22 = UnsafeOpsHolder.U.getDouble(m + 64L);
        double _t0 = _m00 + _m11;
        double _t10 = _m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_m00 - (_m11 + _m22));
        double _t16 = 1.0 + (_m11 - (_m00 + _m22));
        double _t17 = 1.0 + (_m22 - _t0);
        return makeFromMatrixMat3x3_unsafe_se9dcf998_1(dest, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x3_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeFromMatrixMat3x3_unsafe_se9dcf998_1(long dest, double _m00, double _m11, double _m22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t1);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t7);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t9);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t15));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t4);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t6);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t4);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t16));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t8);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t7);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t6);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t8);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t17));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static long makeFromMatrixMat3x4_unsafe(long dest, long m) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m02 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 32L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 40L);
        double _m12 = UnsafeOpsHolder.U.getDouble(m + 48L);
        double _m20 = UnsafeOpsHolder.U.getDouble(m + 64L);
        double _m21 = UnsafeOpsHolder.U.getDouble(m + 72L);
        double _m22 = UnsafeOpsHolder.U.getDouble(m + 80L);
        double _t0 = _m00 + _m11;
        double _t10 = _m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_m00 - (_m11 + _m22));
        double _t16 = 1.0 + (_m11 - (_m00 + _m22));
        double _t17 = 1.0 + (_m22 - _t0);
        return makeFromMatrixMat3x4_unsafe_s928885d3_1(dest, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeFromMatrixMat3x4_unsafe_s928885d3_1(long dest, double _m00, double _m11, double _m22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t1);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t7);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t9);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t15));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t4);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t6);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t4);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t16));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t8);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t7);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t6);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t8);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t17));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static long makeFromMatrixMat4x4_unsafe(long dest, long m) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m20 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 32L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 40L);
        double _m21 = UnsafeOpsHolder.U.getDouble(m + 48L);
        double _m02 = UnsafeOpsHolder.U.getDouble(m + 64L);
        double _m12 = UnsafeOpsHolder.U.getDouble(m + 72L);
        double _m22 = UnsafeOpsHolder.U.getDouble(m + 80L);
        double _t0 = _m00 + _m11;
        double _t10 = _m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_m00 - (_m11 + _m22));
        double _t16 = 1.0 + (_m11 - (_m00 + _m22));
        double _t17 = 1.0 + (_m22 - _t0);
        return makeFromMatrixMat4x4_unsafe_s3ec70630_1(dest, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat4x4_unsafe}, split to fit the inline budget; reached only through it. */
    private static long makeFromMatrixMat4x4_unsafe_s3ec70630_1(long dest, double _m00, double _m11, double _m22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t1);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t7);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t9);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t15));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t4);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t6);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t4);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t16));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t8);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t7);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t6);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t8);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t17));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static long toDualQuat_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        return dest;
    }

    public static long toMatrix_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix_unsafe_fma(dest, src);
        return toMatrix_unsafe_mulAdd(dest, src);
    }

    /** {@code toMatrix_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toMatrix_unsafe_fma(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * java.lang.Math.fma(_selfx, _selfy, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 2.0 * java.lang.Math.fma(_selfx, _selfy, -_t1));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 72L, 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        return toMatrix_unsafe_fma_sdc57158d_1(dest, _selfx, _selfy);
    }

    /** Piece 2 of {@code toMatrix_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long toMatrix_unsafe_fma_sdc57158d_1(long dest, double _selfx, double _selfy) {
        UnsafeOpsHolder.U.putDouble(dest + 80L, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 96L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 104L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 112L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 120L, 1.0);
        return dest;
    }

    /** {@code toMatrix_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toMatrix_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putDouble(dest, ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * ((_selfx) * (_selfy) + (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * ((_selfx) * (_selfz) - (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 2.0 * ((_selfx) * (_selfy) - (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 2.0 * ((_selfx) * (_selfz) + (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 72L, 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        return toMatrix_unsafe_mulAdd_sc2f236b6_1(dest, _selfx, _selfy);
    }

    /** Piece 2 of {@code toMatrix_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long toMatrix_unsafe_mulAdd_sc2f236b6_1(long dest, double _selfx, double _selfy) {
        UnsafeOpsHolder.U.putDouble(dest + 80L, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 96L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 104L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 112L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 120L, 1.0);
        return dest;
    }

    public static long toMatrix3x3_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix3x3_unsafe_fma(dest, src);
        return toMatrix3x3_unsafe_mulAdd(dest, src);
    }

    /** {@code toMatrix3x3_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toMatrix3x3_unsafe_fma(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * java.lang.Math.fma(_selfx, _selfy, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 2.0 * java.lang.Math.fma(_selfx, _selfy, -_t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        return dest;
    }

    /** {@code toMatrix3x3_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toMatrix3x3_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putDouble(dest, ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * ((_selfx) * (_selfy) + (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * ((_selfx) * (_selfz) - (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 2.0 * ((_selfx) * (_selfy) - (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 40L, 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * ((_selfx) * (_selfz) + (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        return dest;
    }

    public static long toMatrix3x4_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix3x4_unsafe_fma(dest, src);
        return toMatrix3x4_unsafe_mulAdd(dest, src);
    }

    /** {@code toMatrix3x4_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toMatrix3x4_unsafe_fma(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * java.lang.Math.fma(_selfx, _selfy, -_t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 2.0 * java.lang.Math.fma(_selfx, _selfy, _t1));
        UnsafeOpsHolder.U.putDouble(dest + 40L, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2));
        return toMatrix3x4_unsafe_fma_sa2f6a18c_1(dest, _selfx, _selfy, _selfz, _selfw);
    }

    /** Piece 2 of {@code toMatrix3x4_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long toMatrix3x4_unsafe_fma_sa2f6a18c_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 80L, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    /** {@code toMatrix3x4_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long toMatrix3x4_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        UnsafeOpsHolder.U.putDouble(dest, ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * ((_selfx) * (_selfy) - (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * ((_selfx) * (_selfz) + (_t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 2.0 * ((_selfx) * (_selfy) + (_t1)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t0)))));
        UnsafeOpsHolder.U.putDouble(dest + 48L, 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 2.0 * ((_selfx) * (_selfz) - (_t2)));
        return toMatrix3x4_unsafe_mulAdd_s3857129d_1(dest, _selfx, _selfy, _selfz, _selfw);
    }

    /** Piece 2 of {@code toMatrix3x4_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long toMatrix3x4_unsafe_mulAdd_s3857129d_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw) {
        UnsafeOpsHolder.U.putDouble(dest + 72L, 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 80L, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        UnsafeOpsHolder.U.putDouble(dest + 88L, 0.0);
        return dest;
    }

    public static long decomposeSwingTwist_unsafe(long swing, long twist, long src, double axisX, double axisY, double axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSwingTwist_unsafe_fma(swing, twist, src, axisX, axisY, axisZ);
        return decomposeSwingTwist_unsafe_mulAdd(swing, twist, src, axisX, axisY, axisZ);
    }

    /** {@code decomposeSwingTwist_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeSwingTwist_unsafe_fma(long swing, long twist, long src, double axisX, double axisY, double axisZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        double _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(swing, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        UnsafeOpsHolder.U.putDouble(swing + 8L, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        UnsafeOpsHolder.U.putDouble(swing + 16L, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        return decomposeSwingTwist_unsafe_fma_s6599693d_1(swing, twist, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeSwingTwist_unsafe_fma_s6599693d_1(long swing, long twist, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        UnsafeOpsHolder.U.putDouble(swing + 24L, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        UnsafeOpsHolder.U.putDouble(twist, _t12);
        UnsafeOpsHolder.U.putDouble(twist + 8L, _t13);
        UnsafeOpsHolder.U.putDouble(twist + 16L, _t14);
        UnsafeOpsHolder.U.putDouble(twist + 24L, _t11);
        return swing;
    }

    /** {@code decomposeSwingTwist_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeSwingTwist_unsafe_mulAdd(long swing, long twist, long src, double axisX, double axisY, double axisZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        double _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(swing, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        UnsafeOpsHolder.U.putDouble(swing + 8L, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        UnsafeOpsHolder.U.putDouble(swing + 16L, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        UnsafeOpsHolder.U.putDouble(swing + 24L, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return decomposeSwingTwist_unsafe_mulAdd_s2ec41138_1(swing, twist, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeSwingTwist_unsafe_mulAdd_s2ec41138_1(long swing, long twist, double _t11, double _t12, double _t13, double _t14) {
        UnsafeOpsHolder.U.putDouble(twist, _t12);
        UnsafeOpsHolder.U.putDouble(twist + 8L, _t13);
        UnsafeOpsHolder.U.putDouble(twist + 16L, _t14);
        UnsafeOpsHolder.U.putDouble(twist + 24L, _t11);
        return swing;
    }

    public static long decomposeSwingTwist_unsafe(long swing, long twist, long src, long axis) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSwingTwist_unsafe_fma(swing, twist, src, axis);
        return decomposeSwingTwist_unsafe_mulAdd(swing, twist, src, axis);
    }

    /** {@code decomposeSwingTwist_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeSwingTwist_unsafe_fma(long swing, long twist, long src, long axis) {
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        double _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(swing, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        UnsafeOpsHolder.U.putDouble(swing + 8L, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        return decomposeSwingTwist_unsafe_fma_s9213e089_1(swing, twist, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long decomposeSwingTwist_unsafe_fma_s9213e089_1(long swing, long twist, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        UnsafeOpsHolder.U.putDouble(swing + 16L, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        UnsafeOpsHolder.U.putDouble(swing + 24L, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        UnsafeOpsHolder.U.putDouble(twist, _t12);
        UnsafeOpsHolder.U.putDouble(twist + 8L, _t13);
        UnsafeOpsHolder.U.putDouble(twist + 16L, _t14);
        UnsafeOpsHolder.U.putDouble(twist + 24L, _t11);
        return swing;
    }

    /** {@code decomposeSwingTwist_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long decomposeSwingTwist_unsafe_mulAdd(long swing, long twist, long src, long axis) {
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        double _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(swing, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        UnsafeOpsHolder.U.putDouble(swing + 8L, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        return decomposeSwingTwist_unsafe_mulAdd_sda9110ca_1(swing, twist, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long decomposeSwingTwist_unsafe_mulAdd_sda9110ca_1(long swing, long twist, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        UnsafeOpsHolder.U.putDouble(swing + 16L, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        UnsafeOpsHolder.U.putDouble(swing + 24L, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        UnsafeOpsHolder.U.putDouble(twist, _t12);
        UnsafeOpsHolder.U.putDouble(twist + 8L, _t13);
        UnsafeOpsHolder.U.putDouble(twist + 16L, _t14);
        UnsafeOpsHolder.U.putDouble(twist + 24L, _t11);
        return swing;
    }

    public static long getSwing_unsafe(long dest, long src, double axisX, double axisY, double axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getSwing_unsafe_fma(dest, src, axisX, axisY, axisZ);
        return getSwing_unsafe_mulAdd(dest, src, axisX, axisY, axisZ);
    }

    /** {@code getSwing_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getSwing_unsafe_fma(long dest, long src, double axisX, double axisY, double axisZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        double _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        return dest;
    }

    /** {@code getSwing_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getSwing_unsafe_mulAdd(long dest, long src, double axisX, double axisY, double axisZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        double _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return dest;
    }

    public static long getSwing_unsafe(long dest, long src, long axis) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getSwing_unsafe_fma(dest, src, axis);
        return getSwing_unsafe_mulAdd(dest, src, axis);
    }

    /** {@code getSwing_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getSwing_unsafe_fma(long dest, long src, long axis) {
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        double _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        return getSwing_unsafe_fma_sc55c06b6_1(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getSwing_unsafe_fma_sc55c06b6_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        return dest;
    }

    /** {@code getSwing_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getSwing_unsafe_mulAdd(long dest, long src, long axis) {
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        double _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        return getSwing_unsafe_mulAdd_scf97a5ff_1(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long getSwing_unsafe_mulAdd_scf97a5ff_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return dest;
    }

    public static long getTwist_unsafe(long dest, long src, double axisX, double axisY, double axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getTwist_unsafe_mulAdd(dest, src, axisX, axisY, axisZ);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = java.lang.Math.fma(axisZ, UnsafeOpsHolder.U.getDouble(src + 16L), java.lang.Math.fma(axisX, UnsafeOpsHolder.U.getDouble(src), axisY * UnsafeOpsHolder.U.getDouble(src + 8L)));
        double _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t6 = _t2 * _t5;
        if (_t4 > 1.0E-30) {
            UnsafeOpsHolder.U.putDouble(dest, axisX * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 8L, axisY * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 16L, axisZ * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t5);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        }
        return dest;
    }

    /** {@code getTwist_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getTwist_unsafe_mulAdd(long dest, long src, double axisX, double axisY, double axisZ) {
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = ((axisZ) * (UnsafeOpsHolder.U.getDouble(src + 16L)) + (((axisX) * (UnsafeOpsHolder.U.getDouble(src)) + (axisY * UnsafeOpsHolder.U.getDouble(src + 8L)))));
        double _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t6 = _t2 * _t5;
        if (_t4 > 1.0E-30) {
            UnsafeOpsHolder.U.putDouble(dest, axisX * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 8L, axisY * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 16L, axisZ * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t5);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        }
        return dest;
    }

    public static long getTwist_unsafe(long dest, long src, long axis) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getTwist_unsafe_mulAdd(dest, src, axis);
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = java.lang.Math.fma(_axisz, UnsafeOpsHolder.U.getDouble(src + 16L), java.lang.Math.fma(_axisx, UnsafeOpsHolder.U.getDouble(src), _axisy * UnsafeOpsHolder.U.getDouble(src + 8L)));
        double _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t6 = _t2 * _t5;
        if (_t4 > 1.0E-30) {
            UnsafeOpsHolder.U.putDouble(dest, _axisx * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _axisy * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _axisz * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t5);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        }
        return dest;
    }

    /** {@code getTwist_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getTwist_unsafe_mulAdd(long dest, long src, long axis) {
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = ((_axisz) * (UnsafeOpsHolder.U.getDouble(src + 16L)) + (((_axisx) * (UnsafeOpsHolder.U.getDouble(src)) + (_axisy * UnsafeOpsHolder.U.getDouble(src + 8L)))));
        double _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t6 = _t2 * _t5;
        if (_t4 > 1.0E-30) {
            UnsafeOpsHolder.U.putDouble(dest, _axisx * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _axisy * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _axisz * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw * _t5);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        }
        return dest;
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        return dest;
    }

    public static long makeZero_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
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

    public static long nlerp_unsafe(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return nlerp_unsafe_mulAdd(dest, src, targetX, targetY, targetZ, targetW, alpha);
        double _t0 = 1.0 - alpha;
        double _t5 = java.lang.Math.fma(alpha, targetW, UnsafeOpsHolder.U.getDouble(src + 24L) * _t0);
        double _t6 = java.lang.Math.fma(alpha, targetZ, UnsafeOpsHolder.U.getDouble(src + 16L) * _t0);
        double _t7 = java.lang.Math.fma(alpha, targetX, UnsafeOpsHolder.U.getDouble(src) * _t0);
        double _t8 = java.lang.Math.fma(alpha, targetY, UnsafeOpsHolder.U.getDouble(src + 8L) * _t0);
        double _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t7 * _t13);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t8 * _t13);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t6 * _t13);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t5 * _t13);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    /** {@code nlerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerp_unsafe_mulAdd(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t5 = ((alpha) * (targetW) + (UnsafeOpsHolder.U.getDouble(src + 24L) * _t0));
        double _t6 = ((alpha) * (targetZ) + (UnsafeOpsHolder.U.getDouble(src + 16L) * _t0));
        double _t7 = ((alpha) * (targetX) + (UnsafeOpsHolder.U.getDouble(src) * _t0));
        double _t8 = ((alpha) * (targetY) + (UnsafeOpsHolder.U.getDouble(src + 8L) * _t0));
        double _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t7 * _t13);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t8 * _t13);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t6 * _t13);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t5 * _t13);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long nlerp_unsafe(long dest, long src, long target, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerp_unsafe_fma(dest, src, target, alpha);
        return nlerp_unsafe_mulAdd(dest, src, target, alpha);
    }

    /** {@code nlerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerp_unsafe_fma(long dest, long src, long target, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t5 = java.lang.Math.fma(alpha, UnsafeOpsHolder.U.getDouble(target + 24L), UnsafeOpsHolder.U.getDouble(src + 24L) * _t0);
        double _t6 = java.lang.Math.fma(alpha, UnsafeOpsHolder.U.getDouble(target + 16L), UnsafeOpsHolder.U.getDouble(src + 16L) * _t0);
        double _t7 = java.lang.Math.fma(alpha, UnsafeOpsHolder.U.getDouble(target), UnsafeOpsHolder.U.getDouble(src) * _t0);
        double _t8 = java.lang.Math.fma(alpha, UnsafeOpsHolder.U.getDouble(target + 8L), UnsafeOpsHolder.U.getDouble(src + 8L) * _t0);
        double _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t7 * _t13);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t8 * _t13);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t6 * _t13);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t5 * _t13);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    /** {@code nlerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerp_unsafe_mulAdd(long dest, long src, long target, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t5 = ((alpha) * (UnsafeOpsHolder.U.getDouble(target + 24L)) + (UnsafeOpsHolder.U.getDouble(src + 24L) * _t0));
        double _t6 = ((alpha) * (UnsafeOpsHolder.U.getDouble(target + 16L)) + (UnsafeOpsHolder.U.getDouble(src + 16L) * _t0));
        double _t7 = ((alpha) * (UnsafeOpsHolder.U.getDouble(target)) + (UnsafeOpsHolder.U.getDouble(src) * _t0));
        double _t8 = ((alpha) * (UnsafeOpsHolder.U.getDouble(target + 8L)) + (UnsafeOpsHolder.U.getDouble(src + 8L) * _t0));
        double _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t7 * _t13);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t8 * _t13);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t6 * _t13);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t5 * _t13);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long nlerpShortest_unsafe(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerpShortest_unsafe_fma(dest, src, targetX, targetY, targetZ, targetW, alpha);
        return nlerpShortest_unsafe_mulAdd(dest, src, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code nlerpShortest_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerpShortest_unsafe_fma(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = 1.0 - alpha;
        double _t18, _t19, _t20, _t21;
        if (-java.lang.Math.fma(_selfw, targetW, java.lang.Math.fma(_selfz, targetZ, java.lang.Math.fma(_selfx, targetX, _selfy * targetY))) > 0.0) {
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
        double _t25 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t20, _t20, _t21 * _t21)));
        return nlerpShortest_unsafe_fma_s34fa5d39_1(dest, _t18, _t19, _t20, _t21, _t25, (1.0 / java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long nlerpShortest_unsafe_fma_s34fa5d39_1(long dest, double _t18, double _t19, double _t20, double _t21, double _t25, double _t26) {
        if (_t25 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t20 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t21 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t19 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t18 * _t26);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    /** {@code nlerpShortest_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerpShortest_unsafe_mulAdd(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = 1.0 - alpha;
        double _t18, _t19, _t20, _t21;
        if (-((_selfw) * (targetW) + (((_selfz) * (targetZ) + (((_selfx) * (targetX) + (_selfy * targetY)))))) > 0.0) {
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
        double _t25 = ((_t18) * (_t18) + (((_t19) * (_t19) + (((_t20) * (_t20) + (_t21 * _t21))))));
        return nlerpShortest_unsafe_mulAdd_sd8c3c930_1(dest, _t18, _t19, _t20, _t21, _t25, (1.0 / java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long nlerpShortest_unsafe_mulAdd_sd8c3c930_1(long dest, double _t18, double _t19, double _t20, double _t21, double _t25, double _t26) {
        if (_t25 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t20 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t21 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t19 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t18 * _t26);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long nlerpShortest_unsafe(long dest, long src, long target, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerpShortest_unsafe_fma(dest, src, target, alpha);
        return nlerpShortest_unsafe_mulAdd(dest, src, target, alpha);
    }

    /** {@code nlerpShortest_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerpShortest_unsafe_fma(long dest, long src, long target, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _targetx = UnsafeOpsHolder.U.getDouble(target);
        double _targety = UnsafeOpsHolder.U.getDouble(target + 8L);
        double _targetz = UnsafeOpsHolder.U.getDouble(target + 16L);
        double _targetw = UnsafeOpsHolder.U.getDouble(target + 24L);
        double _t1 = 1.0 - alpha;
        double _t18, _t19, _t20, _t21;
        if (-java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety))) > 0.0) {
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
        double _t25 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t20, _t20, _t21 * _t21)));
        return nlerpShortest_unsafe_fma_sb139a93e_1(dest, _t18, _t19, _t20, _t21, _t25, (1.0 / java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long nlerpShortest_unsafe_fma_sb139a93e_1(long dest, double _t18, double _t19, double _t20, double _t21, double _t25, double _t26) {
        if (_t25 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t20 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t21 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t19 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t18 * _t26);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    /** {@code nlerpShortest_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long nlerpShortest_unsafe_mulAdd(long dest, long src, long target, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _targetx = UnsafeOpsHolder.U.getDouble(target);
        double _targety = UnsafeOpsHolder.U.getDouble(target + 8L);
        double _targetz = UnsafeOpsHolder.U.getDouble(target + 16L);
        double _targetw = UnsafeOpsHolder.U.getDouble(target + 24L);
        double _t1 = 1.0 - alpha;
        double _t18, _t19, _t20, _t21;
        if (-((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety)))))) > 0.0) {
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
        double _t25 = ((_t18) * (_t18) + (((_t19) * (_t19) + (((_t20) * (_t20) + (_t21 * _t21))))));
        return nlerpShortest_unsafe_mulAdd_sd993163d_1(dest, _t18, _t19, _t20, _t21, _t25, (1.0 / java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long nlerpShortest_unsafe_mulAdd_sd993163d_1(long dest, double _t18, double _t19, double _t20, double _t21, double _t25, double _t26) {
        if (_t25 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t20 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t21 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t19 * _t26);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t18 * _t26);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, targetX, targetY, targetZ, targetW, alpha);
        return slerp_unsafe_mulAdd(dest, src, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = _selfw + targetW;
        double _t3 = _selfz + targetZ;
        double _t4 = _selfx + targetX;
        double _t5 = _selfy + targetY;
        double _t10 = Math.min(4.0, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return DoubleQuatOpsKernelsAddress.slerp_degenerate(dest, src, targetX, targetY, targetZ, targetW, alpha);
        double _t22 = 2.0 * Math.sinWithCos(_t14) / _ct0;
        double _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5, _t10, -1.0) * _t22;
        double _t25, _t26;
        if (alpha < 0.5) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t25, targetX * _t26));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t25, targetY * _t26));
        return slerp_unsafe_fma_s211ca66_1(dest, targetZ, targetW, _selfz, _selfw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_s211ca66_1(long dest, double targetZ, double targetW, double _selfz, double _selfw, double _t25, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t25, targetZ * _t26));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t25, targetW * _t26));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = _selfw + targetW;
        double _t3 = _selfz + targetZ;
        double _t4 = _selfx + targetX;
        double _t5 = _selfy + targetY;
        double _t10 = Math.min(4.0, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return DoubleQuatOpsKernelsAddress.slerp_degenerate(dest, src, targetX, targetY, targetZ, targetW, alpha);
        double _t22 = 2.0 * Math.sinWithCos(_t14) / _ct0;
        double _t24 = Math.cosWithSin(_t14) - ((0.5) * (_t10) - (1.0)) * _t22;
        double _t25, _t26;
        if (alpha < 0.5) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t25) + (targetX * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t25) + (targetY * _t26)));
        return slerp_unsafe_mulAdd_s8e475681_1(dest, targetZ, targetW, _selfz, _selfw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s8e475681_1(long dest, double targetZ, double targetW, double _selfz, double _selfw, double _t25, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t25) + (targetZ * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t25) + (targetW * _t26)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.slerp_degenerate_unsafe(dest, src, targetX, targetY, targetZ, targetW, alpha);
        DoubleQuatOpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_mulAdd(dest, src, targetX, targetY, targetZ, targetW, alpha);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfw + targetW;
        double _t2 = _selfz + targetZ;
        double _t3 = _selfx + targetX;
        double _t4 = _selfy + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t13, targetX * _t14));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t13, targetY * _t14));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t13, targetZ * _t14));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t13, targetW * _t14));
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfw + targetW;
        double _t2 = _selfz + targetZ;
        double _t3 = _selfx + targetX;
        double _t4 = _selfy + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t13) + (targetX * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t13) + (targetY * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t13) + (targetZ * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t13) + (targetW * _t14)));
        return dest;
    }

    public static long slerp_unsafe(long dest, long src, long target, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_unsafe_fma(dest, src, target, alpha);
        return slerp_unsafe_mulAdd(dest, src, target, alpha);
    }

    /** {@code slerp_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_fma(long dest, long src, long target, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _targetx = UnsafeOpsHolder.U.getDouble(target);
        double _targety = UnsafeOpsHolder.U.getDouble(target + 8L);
        double _targetz = UnsafeOpsHolder.U.getDouble(target + 16L);
        double _targetw = UnsafeOpsHolder.U.getDouble(target + 24L);
        double _t2 = _selfw + _targetw;
        double _t3 = _selfz + _targetz;
        double _t4 = _selfx + _targetx;
        double _t5 = _selfy + _targety;
        double _t10 = Math.min(4.0, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return DoubleQuatOpsKernelsAddress.slerp_degenerate(dest, src, target, alpha);
        double _t22 = 2.0 * Math.sinWithCos(_t14) / _ct0;
        return slerp_unsafe_fma_se0ddcc4b_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, _targetx, _targety, _targetz, _targetw, _t22, Math.cosWithSin(_t14) - java.lang.Math.fma(0.5, _t10, -1.0) * _t22);
    }

    /** Piece 2 of {@code slerp_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_fma_se0ddcc4b_1(long dest, double alpha, double _selfx, double _selfy, double _selfz, double _selfw, double _targetx, double _targety, double _targetz, double _targetw, double _t22, double _t24) {
        double _t25, _t26;
        if (alpha < 0.5) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t25, _targetx * _t26));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t25, _targety * _t26));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t25, _targetz * _t26));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t25, _targetw * _t26));
        return dest;
    }

    /** {@code slerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_unsafe_mulAdd(long dest, long src, long target, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _targetx = UnsafeOpsHolder.U.getDouble(target);
        double _targety = UnsafeOpsHolder.U.getDouble(target + 8L);
        double _targetz = UnsafeOpsHolder.U.getDouble(target + 16L);
        double _targetw = UnsafeOpsHolder.U.getDouble(target + 24L);
        double _t2 = _selfw + _targetw;
        double _t3 = _selfz + _targetz;
        double _t4 = _selfx + _targetx;
        double _t5 = _selfy + _targety;
        double _t10 = Math.min(4.0, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _ct0 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct0 > 2.0E-14 && _ct0 < Double.POSITIVE_INFINITY)) return DoubleQuatOpsKernelsAddress.slerp_degenerate(dest, src, target, alpha);
        double _t22 = 2.0 * Math.sinWithCos(_t14) / _ct0;
        double _t24 = Math.cosWithSin(_t14) - ((0.5) * (_t10) - (1.0)) * _t22;
        double _t25, _t26;
        if (alpha < 0.5) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        return slerp_unsafe_mulAdd_s74e20ac6_1(dest, _selfx, _selfy, _selfz, _selfw, _targetx, _targety, _targetz, _targetw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerp_unsafe_mulAdd_s74e20ac6_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _targetx, double _targety, double _targetz, double _targetw, double _t25, double _t26) {
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t25) + (_targetx * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t25) + (_targety * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t25) + (_targetz * _t26)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t25) + (_targetw * _t26)));
        return dest;
    }

    public static long slerp_degenerate(long dest, long src, long target, double alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.slerp_degenerate_unsafe(dest, src, target, alpha);
        DoubleQuatOpsKernelsSegment.slerp_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(target, 32L), 0L, alpha);
        return dest;
    }

    public static long slerp_degenerate_unsafe(long dest, long src, long target, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_unsafe_fma(dest, src, target, alpha);
        return slerp_degenerate_unsafe_mulAdd(dest, src, target, alpha);
    }

    /** {@code slerp_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_fma(long dest, long src, long target, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _targetx = UnsafeOpsHolder.U.getDouble(target);
        double _targety = UnsafeOpsHolder.U.getDouble(target + 8L);
        double _targetz = UnsafeOpsHolder.U.getDouble(target + 16L);
        double _targetw = UnsafeOpsHolder.U.getDouble(target + 24L);
        double _t1 = _selfw + _targetw;
        double _t2 = _selfz + _targetz;
        double _t3 = _selfx + _targetx;
        double _t4 = _selfy + _targety;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t13, _targetx * _t14));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t13, _targety * _t14));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t13, _targetz * _t14));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t13, _targetw * _t14));
        return dest;
    }

    /** {@code slerp_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerp_degenerate_unsafe_mulAdd(long dest, long src, long target, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _targetx = UnsafeOpsHolder.U.getDouble(target);
        double _targety = UnsafeOpsHolder.U.getDouble(target + 8L);
        double _targetz = UnsafeOpsHolder.U.getDouble(target + 16L);
        double _targetw = UnsafeOpsHolder.U.getDouble(target + 24L);
        double _t1 = _selfw + _targetw;
        double _t2 = _selfz + _targetz;
        double _t3 = _selfx + _targetx;
        double _t4 = _selfy + _targety;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        double _t13, _t14;
        if (_t11 > 4.0 - _t11) {
            _t13 = 1.0 - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0 - _t5;
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t13) + (_targetx * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t13) + (_targety * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t13) + (_targetz * _t14)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t13) + (_targetw * _t14)));
        return dest;
    }

    public static long slerpShortest_unsafe(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerpShortest_unsafe_fma(dest, src, targetX, targetY, targetZ, targetW, alpha);
        return slerpShortest_unsafe_mulAdd(dest, src, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code slerpShortest_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerpShortest_unsafe_fma(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 1.0 - alpha;
        double _t12 = java.lang.Math.fma(_selfw, targetW, java.lang.Math.fma(_selfz, targetZ, java.lang.Math.fma(_selfx, targetX, _selfy * targetY)));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
        double _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0) {
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
        double _t31 = java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0));
        double _t32 = 1.0 / _t31;
        return slerpShortest_unsafe_fma_s293c734_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(alpha * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    /** Piece 2 of {@code slerpShortest_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerpShortest_unsafe_fma_s293c734_1(long dest, double alpha, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t21, double _t22, double _t23, double _t24, double _t31, double _t33, double _t34) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = java.lang.Math.fma(_selfw, _t34, _t33 * _t21);
            _t44 = java.lang.Math.fma(_selfz, _t34, _t33 * _t22);
            _t45 = java.lang.Math.fma(_selfx, _t34, _t33 * _t23);
            _t46 = java.lang.Math.fma(_selfy, _t34, _t33 * _t24);
        } else {
            _t43 = java.lang.Math.fma(alpha, _t21, _selfw * _t0);
            _t44 = java.lang.Math.fma(alpha, _t22, _selfz * _t0);
            _t45 = java.lang.Math.fma(alpha, _t23, _selfx * _t0);
            _t46 = java.lang.Math.fma(alpha, _t24, _selfy * _t0);
        }
        double _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t51 * _t45);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t51 * _t46);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t51 * _t44);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t51 * _t43);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    /** {@code slerpShortest_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerpShortest_unsafe_mulAdd(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 1.0 - alpha;
        double _t12 = ((_selfw) * (targetW) + (((_selfz) * (targetZ) + (((_selfx) * (targetX) + (_selfy * targetY))))));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
        double _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0) {
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
        double _t31 = java.lang.Math.sqrt(((1.0) - (_t15) * (_t15)));
        double _t32 = 1.0 / _t31;
        return slerpShortest_unsafe_mulAdd_s85f2d32f_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(alpha * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    /** Piece 2 of {@code slerpShortest_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerpShortest_unsafe_mulAdd_s85f2d32f_1(long dest, double alpha, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t21, double _t22, double _t23, double _t24, double _t31, double _t33, double _t34) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = ((_selfw) * (_t34) + (_t33 * _t21));
            _t44 = ((_selfz) * (_t34) + (_t33 * _t22));
            _t45 = ((_selfx) * (_t34) + (_t33 * _t23));
            _t46 = ((_selfy) * (_t34) + (_t33 * _t24));
        } else {
            _t43 = ((alpha) * (_t21) + (_selfw * _t0));
            _t44 = ((alpha) * (_t22) + (_selfz * _t0));
            _t45 = ((alpha) * (_t23) + (_selfx * _t0));
            _t46 = ((alpha) * (_t24) + (_selfy * _t0));
        }
        double _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t51 * _t45);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t51 * _t46);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t51 * _t44);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t51 * _t43);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long slerpShortest_unsafe(long dest, long src, long target, double alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerpShortest_unsafe_fma(dest, src, target, alpha);
        return slerpShortest_unsafe_mulAdd(dest, src, target, alpha);
    }

    /** {@code slerpShortest_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerpShortest_unsafe_fma(long dest, long src, long target, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _targetx = UnsafeOpsHolder.U.getDouble(target);
        double _targety = UnsafeOpsHolder.U.getDouble(target + 8L);
        double _targetz = UnsafeOpsHolder.U.getDouble(target + 16L);
        double _targetw = UnsafeOpsHolder.U.getDouble(target + 24L);
        double _t0 = 1.0 - alpha;
        double _t12 = java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety)));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
        double _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0) {
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
        double _t31 = java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0));
        double _t32 = 1.0 / _t31;
        return slerpShortest_unsafe_fma_sb6fe2cb9_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(alpha * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    /** Piece 2 of {@code slerpShortest_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long slerpShortest_unsafe_fma_sb6fe2cb9_1(long dest, double alpha, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t21, double _t22, double _t23, double _t24, double _t31, double _t33, double _t34) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = java.lang.Math.fma(_selfw, _t34, _t33 * _t21);
            _t44 = java.lang.Math.fma(_selfz, _t34, _t33 * _t22);
            _t45 = java.lang.Math.fma(_selfx, _t34, _t33 * _t23);
            _t46 = java.lang.Math.fma(_selfy, _t34, _t33 * _t24);
        } else {
            _t43 = java.lang.Math.fma(alpha, _t21, _selfw * _t0);
            _t44 = java.lang.Math.fma(alpha, _t22, _selfz * _t0);
            _t45 = java.lang.Math.fma(alpha, _t23, _selfx * _t0);
            _t46 = java.lang.Math.fma(alpha, _t24, _selfy * _t0);
        }
        double _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t51 * _t45);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t51 * _t46);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t51 * _t44);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t51 * _t43);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    /** {@code slerpShortest_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long slerpShortest_unsafe_mulAdd(long dest, long src, long target, double alpha) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _targetx = UnsafeOpsHolder.U.getDouble(target);
        double _targety = UnsafeOpsHolder.U.getDouble(target + 8L);
        double _targetz = UnsafeOpsHolder.U.getDouble(target + 16L);
        double _targetw = UnsafeOpsHolder.U.getDouble(target + 24L);
        double _t0 = 1.0 - alpha;
        double _t12 = ((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety))))));
        double _t15 = Math.min(1.0, java.lang.Math.abs(_t12));
        double _t17 = Math.acos(_t15);
        double _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0) {
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
        double _t31 = java.lang.Math.sqrt(((1.0) - (_t15) * (_t15)));
        double _t32 = 1.0 / _t31;
        return slerpShortest_unsafe_mulAdd_sf6885c14_1(dest, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t21, _t22, _t23, _t24, _t31, Math.sin(alpha * _t17) * _t32, Math.sin(_t0 * _t17) * _t32);
    }

    /** Piece 2 of {@code slerpShortest_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long slerpShortest_unsafe_mulAdd_sf6885c14_1(long dest, double alpha, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t21, double _t22, double _t23, double _t24, double _t31, double _t33, double _t34) {
        double _t43, _t44, _t45, _t46;
        if (_t31 > 0.0) {
            _t43 = ((_selfw) * (_t34) + (_t33 * _t21));
            _t44 = ((_selfz) * (_t34) + (_t33 * _t22));
            _t45 = ((_selfx) * (_t34) + (_t33 * _t23));
            _t46 = ((_selfy) * (_t34) + (_t33 * _t24));
        } else {
            _t43 = ((alpha) * (_t21) + (_selfw * _t0));
            _t44 = ((alpha) * (_t22) + (_selfz * _t0));
            _t45 = ((alpha) * (_t23) + (_selfx * _t0));
            _t46 = ((alpha) * (_t24) + (_selfy * _t0));
        }
        double _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t51 * _t45);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t51 * _t46);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t51 * _t44);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t51 * _t43);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long squad_unsafe(long dest, long src, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return squad_unsafe_fma(dest, src, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return squad_unsafe_mulAdd(dest, src, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
    }

    /** {@code squad_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long squad_unsafe_fma(long dest, long src, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t) {
        double _t0 = 1.0 - t;
        double _t3 = control0W + control1W;
        double _t4 = control0Z + control1Z;
        double _t5 = control0X + control1X;
        double _t6 = control0Y + control1Y;
        double _t7 = Math.min(t, _t0);
        double _t23 = Math.min(4.0, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6))));
        double _t27 = 4.0 - _t23;
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t47 = _t23 > _t27 ? _t7 : 0.0;
        double _t51 = 2.0 / _t41 * Math.sinWithCos(_t33);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = t + t;
        return squad_unsafe_fma_se2ea4f30_1(dest, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t0, _t7, _t41, _t47, _t51, _t41 > 2.0E-14 ? java.lang.Math.fma(java.lang.Math.fma(-0.5, _t23, 1.0), _t51, Math.cosWithSin(_t33)) : 1.0 - _t47, _selfx, _selfy, _selfz, _selfw, _t1, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t0 * _t1);
    }

    /** Piece 2 of {@code squad_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_fma_se2ea4f30_1(long dest, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, double _t0, double _t7, double _t41, double _t47, double _t51, double _t57, double _selfx, double _selfy, double _selfz, double _selfw, double _t1, double _t8, double _t9, double _t10, double _t11, double _t12) {
        double _t24 = Math.min(4.0, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
        double _t28 = 4.0 - _t24;
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        double _t48 = _t24 > _t28 ? _t7 : 0.0;
        double _t52 = 2.0 / _t43 * Math.sinWithCos(_t34);
        double _t55 = _t41 > 2.0E-14 ? _t51 : _t47;
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t52;
            _t58 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t24, 1.0), _t52, Math.cosWithSin(_t34));
        } else {
            _t56 = _t48;
            _t58 = 1.0 - _t48;
        }
        double _t59, _t60, _t61, _t62;
        if (t < 0.5) {
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
        return squad_unsafe_fma_se2ea4f30_2(dest, control0Y, control1Y, targetX, targetY, _selfx, _selfy, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0)), _t59, _t60, _t61, _t62, java.lang.Math.fma(control0X, _t59, control1X * _t60), java.lang.Math.fma(control0W, _t59, control1W * _t60), java.lang.Math.fma(_selfw, _t61, targetW * _t62), java.lang.Math.fma(control0Z, _t59, control1Z * _t60), java.lang.Math.fma(_selfz, _t61, targetZ * _t62));
    }

    /** Piece 3 of {@code squad_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_fma_se2ea4f30_2(long dest, double control0Y, double control1Y, double targetX, double targetY, double _selfx, double _selfy, double _t12, double _t16, double _t59, double _t60, double _t61, double _t62, double _t71, double _t72, double _t73, double _t74, double _t75) {
        double _t76 = java.lang.Math.fma(_selfx, _t61, targetX * _t62);
        double _t77 = java.lang.Math.fma(control0Y, _t59, control1Y * _t60);
        double _t78 = java.lang.Math.fma(_selfy, _t61, targetY * _t62);
        double _t79 = _t72 + _t73;
        double _t80 = _t74 + _t75;
        double _t81 = _t71 + _t76;
        double _t82 = _t77 + _t78;
        double _t87 = Math.min(4.0, java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82))));
        double _t89 = 4.0 - _t87;
        double _t92 = _t16 * quatArcAngle(_t87);
        double _t96 = java.lang.Math.sqrt(_t89 * _t87);
        double _t99 = _t87 > _t89 ? _t16 : 0.0;
        double _t101 = 2.0 / _t96 * Math.sinWithCos(_t92);
        double _t103, _t104;
        if (_t96 > 2.0E-14) {
            _t103 = _t101;
            _t104 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t87, 1.0), _t101, Math.cosWithSin(_t92));
        } else {
            _t103 = _t99;
            _t104 = 1.0 - _t99;
        }
        double _t105, _t106;
        if (_t12 < 0.5) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t71, _t105, _t76 * _t106));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t77, _t105, _t78 * _t106));
        return squad_unsafe_fma_se2ea4f30_3(dest, _t72, _t73, _t74, _t75, _t105, _t106);
    }

    /** Piece 4 of {@code squad_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_fma_se2ea4f30_3(long dest, double _t72, double _t73, double _t74, double _t75, double _t105, double _t106) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t74, _t105, _t75 * _t106));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t72, _t105, _t73 * _t106));
        return dest;
    }

    /** {@code squad_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long squad_unsafe_mulAdd(long dest, long src, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t) {
        double _t0 = 1.0 - t;
        double _t3 = control0W + control1W;
        double _t4 = control0Z + control1Z;
        double _t5 = control0X + control1X;
        double _t6 = control0Y + control1Y;
        double _t7 = Math.min(t, _t0);
        double _t23 = Math.min(4.0, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6)))))));
        double _t27 = 4.0 - _t23;
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t47 = _t23 > _t27 ? _t7 : 0.0;
        double _t51 = 2.0 / _t41 * Math.sinWithCos(_t33);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = t + t;
        double _t12 = _t0 * _t1;
        return squad_unsafe_mulAdd_s6c3f765b_1(dest, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t7, _t41, _t47, _t51, _t41 > 2.0E-14 ? ((((1.0) - (0.5) * (_t23))) * (_t51) + (Math.cosWithSin(_t33))) : 1.0 - _t47, _selfx, _selfy, _selfz, _selfw, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t12, Math.min(_t12, ((1.0) - (_t0) * (_t1))));
    }

    /** Piece 2 of {@code squad_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_mulAdd_s6c3f765b_1(long dest, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, double _t7, double _t41, double _t47, double _t51, double _t57, double _selfx, double _selfy, double _selfz, double _selfw, double _t8, double _t9, double _t10, double _t11, double _t12, double _t16) {
        double _t24 = Math.min(4.0, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
        double _t28 = 4.0 - _t24;
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        double _t48 = _t24 > _t28 ? _t7 : 0.0;
        double _t52 = 2.0 / _t43 * Math.sinWithCos(_t34);
        double _t55 = _t41 > 2.0E-14 ? _t51 : _t47;
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t52;
            _t58 = ((((1.0) - (0.5) * (_t24))) * (_t52) + (Math.cosWithSin(_t34)));
        } else {
            _t56 = _t48;
            _t58 = 1.0 - _t48;
        }
        double _t59, _t60, _t61, _t62;
        if (t < 0.5) {
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
        double _t72 = ((control0W) * (_t59) + (control1W * _t60));
        double _t73 = ((_selfw) * (_t61) + (targetW * _t62));
        return squad_unsafe_mulAdd_s6c3f765b_2(dest, _t12, _t16, ((control0X) * (_t59) + (control1X * _t60)), _t72, _t73, ((control0Z) * (_t59) + (control1Z * _t60)), ((_selfz) * (_t61) + (targetZ * _t62)), ((_selfx) * (_t61) + (targetX * _t62)), ((control0Y) * (_t59) + (control1Y * _t60)), ((_selfy) * (_t61) + (targetY * _t62)), _t72 + _t73);
    }

    /** Piece 3 of {@code squad_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_mulAdd_s6c3f765b_2(long dest, double _t12, double _t16, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77, double _t78, double _t79) {
        double _t80 = _t74 + _t75;
        double _t81 = _t71 + _t76;
        double _t82 = _t77 + _t78;
        double _t87 = Math.min(4.0, ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82)))))));
        double _t89 = 4.0 - _t87;
        double _t92 = _t16 * quatArcAngle(_t87);
        double _t96 = java.lang.Math.sqrt(_t89 * _t87);
        double _t99 = _t87 > _t89 ? _t16 : 0.0;
        double _t101 = 2.0 / _t96 * Math.sinWithCos(_t92);
        double _t103, _t104;
        if (_t96 > 2.0E-14) {
            _t103 = _t101;
            _t104 = ((((1.0) - (0.5) * (_t87))) * (_t101) + (Math.cosWithSin(_t92)));
        } else {
            _t103 = _t99;
            _t104 = 1.0 - _t99;
        }
        double _t105, _t106;
        if (_t12 < 0.5) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_t71) * (_t105) + (_t76 * _t106)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t77) * (_t105) + (_t78 * _t106)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t74) * (_t105) + (_t75 * _t106)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t72) * (_t105) + (_t73 * _t106)));
        return dest;
    }

    public static long squad_unsafe(long dest, long src, long control0, long control1, long target, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return squad_unsafe_fma(dest, src, control0, control1, target, t);
        return squad_unsafe_mulAdd(dest, src, control0, control1, target, t);
    }

    /** {@code squad_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long squad_unsafe_fma(long dest, long src, long control0, long control1, long target, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _control0x = UnsafeOpsHolder.U.getDouble(control0);
        double _control0y = UnsafeOpsHolder.U.getDouble(control0 + 8L);
        double _control0z = UnsafeOpsHolder.U.getDouble(control0 + 16L);
        double _control0w = UnsafeOpsHolder.U.getDouble(control0 + 24L);
        double _control1x = UnsafeOpsHolder.U.getDouble(control1);
        double _control1y = UnsafeOpsHolder.U.getDouble(control1 + 8L);
        double _control1z = UnsafeOpsHolder.U.getDouble(control1 + 16L);
        double _control1w = UnsafeOpsHolder.U.getDouble(control1 + 24L);
        double _targetx = UnsafeOpsHolder.U.getDouble(target);
        double _targety = UnsafeOpsHolder.U.getDouble(target + 8L);
        double _targetz = UnsafeOpsHolder.U.getDouble(target + 16L);
        double _targetw = UnsafeOpsHolder.U.getDouble(target + 24L);
        double _t0 = 1.0 - t;
        return squad_unsafe_fma_s3bf9890a_1(dest, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, t + t, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, Math.min(t, _t0), _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety);
    }

    /** Piece 2 of {@code squad_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_fma_s3bf9890a_1(long dest, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _control0x, double _control0y, double _control0z, double _control0w, double _control1x, double _control1y, double _control1z, double _control1w, double _targetx, double _targety, double _targetz, double _targetw, double _t0, double _t1, double _t3, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11) {
        double _t12 = _t0 * _t1;
        double _t23 = Math.min(4.0, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6))));
        double _t24 = Math.min(4.0, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
        double _t27 = 4.0 - _t23;
        double _t28 = 4.0 - _t24;
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        double _t47 = _t23 > _t27 ? _t7 : 0.0;
        double _t51 = 2.0 / _t41 * Math.sinWithCos(_t33);
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t51;
            _t57 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t23, 1.0), _t51, Math.cosWithSin(_t33));
        } else {
            _t55 = _t47;
            _t57 = 1.0 - _t47;
        }
        return squad_unsafe_fma_s3bf9890a_2(dest, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0)), _t24, _t34, _t43, _t24 > _t28 ? _t7 : 0.0, 2.0 / _t43 * Math.sinWithCos(_t34), _t55, _t57);
    }

    /** Piece 3 of {@code squad_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_fma_s3bf9890a_2(long dest, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _control0x, double _control0y, double _control0z, double _control0w, double _control1x, double _control1y, double _control1z, double _control1w, double _targetx, double _targety, double _targetz, double _targetw, double _t12, double _t16, double _t24, double _t34, double _t43, double _t48, double _t52, double _t55, double _t57) {
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t52;
            _t58 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t24, 1.0), _t52, Math.cosWithSin(_t34));
        } else {
            _t56 = _t48;
            _t58 = 1.0 - _t48;
        }
        double _t59, _t60, _t61, _t62;
        if (t < 0.5) {
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
        double _t71 = java.lang.Math.fma(_control0x, _t59, _control1x * _t60);
        double _t72 = java.lang.Math.fma(_control0w, _t59, _control1w * _t60);
        double _t73 = java.lang.Math.fma(_selfw, _t61, _targetw * _t62);
        double _t74 = java.lang.Math.fma(_control0z, _t59, _control1z * _t60);
        double _t75 = java.lang.Math.fma(_selfz, _t61, _targetz * _t62);
        double _t76 = java.lang.Math.fma(_selfx, _t61, _targetx * _t62);
        double _t77 = java.lang.Math.fma(_control0y, _t59, _control1y * _t60);
        double _t78 = java.lang.Math.fma(_selfy, _t61, _targety * _t62);
        double _t79 = _t72 + _t73;
        double _t80 = _t74 + _t75;
        double _t81 = _t71 + _t76;
        double _t82 = _t77 + _t78;
        double _t87 = Math.min(4.0, java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82))));
        return squad_unsafe_fma_s3bf9890a_3(dest, _t12, _t16, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t87, 4.0 - _t87);
    }

    /** Piece 4 of {@code squad_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_fma_s3bf9890a_3(long dest, double _t12, double _t16, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77, double _t78, double _t87, double _t89) {
        double _t92 = _t16 * quatArcAngle(_t87);
        double _t96 = java.lang.Math.sqrt(_t89 * _t87);
        double _t99 = _t87 > _t89 ? _t16 : 0.0;
        double _t101 = 2.0 / _t96 * Math.sinWithCos(_t92);
        double _t103, _t104;
        if (_t96 > 2.0E-14) {
            _t103 = _t101;
            _t104 = java.lang.Math.fma(java.lang.Math.fma(-0.5, _t87, 1.0), _t101, Math.cosWithSin(_t92));
        } else {
            _t103 = _t99;
            _t104 = 1.0 - _t99;
        }
        double _t105, _t106;
        if (_t12 < 0.5) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t71, _t105, _t76 * _t106));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t77, _t105, _t78 * _t106));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t74, _t105, _t75 * _t106));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t72, _t105, _t73 * _t106));
        return dest;
    }

    /** {@code squad_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long squad_unsafe_mulAdd(long dest, long src, long control0, long control1, long target, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _control0x = UnsafeOpsHolder.U.getDouble(control0);
        double _control0y = UnsafeOpsHolder.U.getDouble(control0 + 8L);
        double _control0z = UnsafeOpsHolder.U.getDouble(control0 + 16L);
        double _control0w = UnsafeOpsHolder.U.getDouble(control0 + 24L);
        double _control1x = UnsafeOpsHolder.U.getDouble(control1);
        double _control1y = UnsafeOpsHolder.U.getDouble(control1 + 8L);
        double _control1z = UnsafeOpsHolder.U.getDouble(control1 + 16L);
        double _control1w = UnsafeOpsHolder.U.getDouble(control1 + 24L);
        double _targetx = UnsafeOpsHolder.U.getDouble(target);
        double _targety = UnsafeOpsHolder.U.getDouble(target + 8L);
        double _targetz = UnsafeOpsHolder.U.getDouble(target + 16L);
        double _targetw = UnsafeOpsHolder.U.getDouble(target + 24L);
        double _t0 = 1.0 - t;
        return squad_unsafe_mulAdd_sc1231c0b_1(dest, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, t + t, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, Math.min(t, _t0), _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety);
    }

    /** Piece 2 of {@code squad_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_mulAdd_sc1231c0b_1(long dest, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _control0x, double _control0y, double _control0z, double _control0w, double _control1x, double _control1y, double _control1z, double _control1w, double _targetx, double _targety, double _targetz, double _targetw, double _t0, double _t1, double _t3, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11) {
        double _t12 = _t0 * _t1;
        double _t23 = Math.min(4.0, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6)))))));
        double _t24 = Math.min(4.0, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
        double _t27 = 4.0 - _t23;
        double _t28 = 4.0 - _t24;
        double _t33 = _t7 * quatArcAngle(_t23);
        double _t34 = _t7 * quatArcAngle(_t24);
        double _t41 = java.lang.Math.sqrt(_t27 * _t23);
        double _t43 = java.lang.Math.sqrt(_t28 * _t24);
        double _t47 = _t23 > _t27 ? _t7 : 0.0;
        double _t51 = 2.0 / _t41 * Math.sinWithCos(_t33);
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t51;
            _t57 = ((((1.0) - (0.5) * (_t23))) * (_t51) + (Math.cosWithSin(_t33)));
        } else {
            _t55 = _t47;
            _t57 = 1.0 - _t47;
        }
        return squad_unsafe_mulAdd_sc1231c0b_2(dest, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t12, Math.min(_t12, ((1.0) - (_t0) * (_t1))), _t24, _t34, _t43, _t24 > _t28 ? _t7 : 0.0, 2.0 / _t43 * Math.sinWithCos(_t34), _t55, _t57);
    }

    /** Piece 3 of {@code squad_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_mulAdd_sc1231c0b_2(long dest, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _control0x, double _control0y, double _control0z, double _control0w, double _control1x, double _control1y, double _control1z, double _control1w, double _targetx, double _targety, double _targetz, double _targetw, double _t12, double _t16, double _t24, double _t34, double _t43, double _t48, double _t52, double _t55, double _t57) {
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t52;
            _t58 = ((((1.0) - (0.5) * (_t24))) * (_t52) + (Math.cosWithSin(_t34)));
        } else {
            _t56 = _t48;
            _t58 = 1.0 - _t48;
        }
        double _t59, _t60, _t61, _t62;
        if (t < 0.5) {
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
        double _t71 = ((_control0x) * (_t59) + (_control1x * _t60));
        double _t72 = ((_control0w) * (_t59) + (_control1w * _t60));
        double _t73 = ((_selfw) * (_t61) + (_targetw * _t62));
        double _t74 = ((_control0z) * (_t59) + (_control1z * _t60));
        double _t75 = ((_selfz) * (_t61) + (_targetz * _t62));
        double _t76 = ((_selfx) * (_t61) + (_targetx * _t62));
        double _t77 = ((_control0y) * (_t59) + (_control1y * _t60));
        double _t78 = ((_selfy) * (_t61) + (_targety * _t62));
        double _t79 = _t72 + _t73;
        double _t80 = _t74 + _t75;
        double _t81 = _t71 + _t76;
        double _t82 = _t77 + _t78;
        double _t87 = Math.min(4.0, ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82)))))));
        return squad_unsafe_mulAdd_sc1231c0b_3(dest, _t12, _t16, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t87, 4.0 - _t87);
    }

    /** Piece 4 of {@code squad_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long squad_unsafe_mulAdd_sc1231c0b_3(long dest, double _t12, double _t16, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77, double _t78, double _t87, double _t89) {
        double _t92 = _t16 * quatArcAngle(_t87);
        double _t96 = java.lang.Math.sqrt(_t89 * _t87);
        double _t99 = _t87 > _t89 ? _t16 : 0.0;
        double _t101 = 2.0 / _t96 * Math.sinWithCos(_t92);
        double _t103, _t104;
        if (_t96 > 2.0E-14) {
            _t103 = _t101;
            _t104 = ((((1.0) - (0.5) * (_t87))) * (_t101) + (Math.cosWithSin(_t92)));
        } else {
            _t103 = _t99;
            _t104 = 1.0 - _t99;
        }
        double _t105, _t106;
        if (_t12 < 0.5) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_t71) * (_t105) + (_t76 * _t106)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t77) * (_t105) + (_t78 * _t106)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t74) * (_t105) + (_t75 * _t106)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t72) * (_t105) + (_t73 * _t106)));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(otherX, _selfz, otherW * _selfy) + java.lang.Math.fma(otherY, _selfw, otherZ * -_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(otherY, _selfx, otherZ * _selfw) + java.lang.Math.fma(otherW, _selfz, -(otherX * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(otherW, _selfw, otherX * -_selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    /** {@code mul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mul_unsafe_mulAdd(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherZ) * (_selfy) - (otherY * _selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((otherX) * (_selfz) + (otherW * _selfy)) + ((otherY) * (_selfw) - (otherZ * _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((otherY) * (_selfx) + (otherZ * _selfw)) + ((otherW) * (_selfz) - (otherX * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz)));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_unsafe_mulAdd(dest, src, other);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_otherx, _selfz, _otherw * _selfy) + java.lang.Math.fma(_othery, _selfw, _otherz * -_selfx));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_othery, _selfx, _otherz * _selfw) + java.lang.Math.fma(_otherw, _selfz, -(_otherx * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_otherw, _selfw, _otherx * -_selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    /** {@code mul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mul_unsafe_mulAdd(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_otherz) * (_selfy) - (_othery * _selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_otherx) * (_selfz) + (_otherw * _selfy)) + ((_othery) * (_selfw) - (_otherz * _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_othery) * (_selfx) + (_otherz * _selfw)) + ((_otherw) * (_selfz) - (_otherx * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz)));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherY, _selfz, -(otherZ * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(otherY, _selfw, otherZ * _selfx) + java.lang.Math.fma(otherW, _selfy, -otherX * _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(otherX, _selfy, otherW * _selfz) + java.lang.Math.fma(otherZ, _selfw, -(otherY * _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(otherW, _selfw, -otherX * _selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    /** {@code preMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_mulAdd(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherY) * (_selfz) - (otherZ * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((otherY) * (_selfw) + (otherZ * _selfx)) + ((otherW) * (_selfy) - (otherX * _selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((otherX) * (_selfy) + (otherW * _selfz)) + ((otherZ) * (_selfw) - (otherY * _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz)));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_unsafe_mulAdd(dest, src, other);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_othery, _selfz, -(_otherz * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_othery, _selfw, _otherz * _selfx) + java.lang.Math.fma(_otherw, _selfy, -_otherx * _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_otherx, _selfy, _otherw * _selfz) + java.lang.Math.fma(_otherz, _selfw, -(_othery * _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_otherw, _selfw, -_otherx * _selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    /** {@code preMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_mulAdd(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_othery) * (_selfz) - (_otherz * _selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_othery) * (_selfw) + (_otherz * _selfx)) + ((_otherw) * (_selfy) - (_otherx * _selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_otherx) * (_selfy) + (_otherw * _selfz)) + ((_otherz) * (_selfw) - (_othery * _selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz)));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW, double weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(weight, otherX, _selfx));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(weight, otherY, _selfy));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(weight, otherZ, _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(weight, otherW, _selfw));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((weight) * (otherX) + (_selfx)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((weight) * (otherY) + (_selfy)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((weight) * (otherZ) + (_selfz)));
            UnsafeOpsHolder.U.putDouble(dest + 24L, ((weight) * (otherW) + (_selfw)));
            return dest;
        }
    }

    public static long addScaled_unsafe(long dest, long src, long other, double weight) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_unsafe_mulAdd(dest, src, other, weight);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(weight, _otherx, _selfx));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(weight, _othery, _selfy));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(weight, _otherz, _selfz));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(weight, _otherw, _selfw));
        return dest;
    }

    /** {@code addScaled_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long addScaled_unsafe_mulAdd(long dest, long src, long other, double weight) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, ((weight) * (_otherx) + (_selfx)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((weight) * (_othery) + (_selfy)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((weight) * (_otherz) + (_selfz)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((weight) * (_otherw) + (_selfw)));
        return dest;
    }

    public static double angle_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            return 2.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))), UnsafeOpsHolder.U.getDouble(src + 24L));
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            return 2.0 * Math.atan2(java.lang.Math.sqrt(((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))), UnsafeOpsHolder.U.getDouble(src + 24L));
        }
    }

    public static double angleTo_unsafe(long src, double otherX, double otherY, double otherZ, double otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleTo_unsafe_mulAdd(src, otherX, otherY, otherZ, otherW);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(otherW, _selfw, java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy))) > 0.0) {
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
        double _t13 = _selfw - _t9;
        double _t14 = _selfz - _t10;
        double _t15 = _selfx - _t11;
        double _t16 = _selfy - _t12;
        double _t17 = _selfw + _t9;
        double _t18 = _selfz + _t10;
        double _t19 = _selfx + _t11;
        double _t20 = _selfy + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleTo_unsafe_mulAdd(long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9, _t10, _t11, _t12;
        if (-((otherW) * (_selfw) + (((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))))) > 0.0) {
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
        double _t13 = _selfw - _t9;
        double _t14 = _selfz - _t10;
        double _t15 = _selfx - _t11;
        double _t16 = _selfy - _t12;
        double _t17 = _selfw + _t9;
        double _t18 = _selfz + _t10;
        double _t19 = _selfx + _t11;
        double _t20 = _selfy + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))))), java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))));
    }

    public static double angleTo_unsafe(long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleTo_unsafe_mulAdd(src, other);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_otherw, _selfw, java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy))) > 0.0) {
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
        double _t13 = _selfw - _t9;
        double _t14 = _selfz - _t10;
        double _t15 = _selfx - _t11;
        double _t16 = _selfy - _t12;
        double _t17 = _selfw + _t9;
        double _t18 = _selfz + _t10;
        double _t19 = _selfx + _t11;
        double _t20 = _selfy + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleTo_unsafe_mulAdd(long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t9, _t10, _t11, _t12;
        if (-((_otherw) * (_selfw) + (((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))))) > 0.0) {
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
        double _t13 = _selfw - _t9;
        double _t14 = _selfz - _t10;
        double _t15 = _selfx - _t11;
        double _t16 = _selfy - _t12;
        double _t17 = _selfw + _t9;
        double _t18 = _selfz + _t10;
        double _t19 = _selfx + _t11;
        double _t20 = _selfy + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))))), java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))));
    }

    public static long axis_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return axis_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0) {
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

    /** {@code axis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long axis_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0) {
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

    public static long calculateW_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, _selfx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.sqrt(Math.max(0.0, java.lang.Math.fma(-_selfx, _selfx, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(-_selfz, _selfz, 1.0))))));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            UnsafeOpsHolder.U.putDouble(dest, _selfx);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz);
            UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.sqrt(Math.max(0.0, ((((((1.0) - (_selfz) * (_selfz))) - (_selfy) * (_selfy))) - (_selfx) * (_selfx)))));
            return dest;
        }
    }

    public static long conjugate_unsafe(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, -_selfx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_selfy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_selfz);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _selfw);
        return dest;
    }

    public static long conjugateBy_unsafe(long dest, long src, double qX, double qY, double qZ, double qW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return conjugateBy_unsafe_fma(dest, src, qX, qY, qZ, qW);
        return conjugateBy_unsafe_mulAdd(dest, src, qX, qY, qZ, qW);
    }

    /** {@code conjugateBy_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long conjugateBy_unsafe_fma(long dest, long src, double qX, double qY, double qZ, double qW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t20 = java.lang.Math.fma(qX, _selfy, qW * _selfz) + java.lang.Math.fma(qZ, _selfw, qY * -_selfx);
        double _t21 = java.lang.Math.fma(qY, _selfw, qZ * _selfx) + java.lang.Math.fma(qW, _selfy, -(qX * _selfz));
        double _t22 = java.lang.Math.fma(qX, _selfw, qW * _selfx) + java.lang.Math.fma(qY, _selfz, -(qZ * _selfy));
        double _t23 = java.lang.Math.fma(qW, _selfw, qX * -_selfx) - java.lang.Math.fma(qY, _selfy, qZ * _selfz);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(qY, _t20, -qZ * _t21) + java.lang.Math.fma(qW, _t22, -qX * _t23));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(qZ, _t22, -qY * _t23) + java.lang.Math.fma(qW, _t21, -qX * _t20));
        return conjugateBy_unsafe_fma_sa46805e_1(dest, qX, qY, qZ, qW, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long conjugateBy_unsafe_fma_sa46805e_1(long dest, double qX, double qY, double qZ, double qW, double _t20, double _t21, double _t22, double _t23) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(qX, _t21, qW * _t20) + java.lang.Math.fma(-qY, _t22, -qZ * _t23));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(qX, _t22, qW * _t23) - java.lang.Math.fma(-qZ, _t20, -qY * _t21));
        return dest;
    }

    /** {@code conjugateBy_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long conjugateBy_unsafe_mulAdd(long dest, long src, double qX, double qY, double qZ, double qW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t20 = ((qX) * (_selfy) + (qW * _selfz)) + ((qZ) * (_selfw) - (qY * _selfx));
        double _t21 = ((qY) * (_selfw) + (qZ * _selfx)) + ((qW) * (_selfy) - (qX * _selfz));
        double _t22 = ((qX) * (_selfw) + (qW * _selfx)) + ((qY) * (_selfz) - (qZ * _selfy));
        double _t23 = ((qW) * (_selfw) - (qX * _selfx)) - ((qY) * (_selfy) + (qZ * _selfz));
        UnsafeOpsHolder.U.putDouble(dest, ((qY) * (_t20) - (qZ * _t21)) + ((qW) * (_t22) - (qX * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((qZ) * (_t22) - (qY * _t23)) + ((qW) * (_t21) - (qX * _t20)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((qX) * (_t21) + (qW * _t20)) + ((-qY) * (_t22) - (qZ * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((qX) * (_t22) + (qW * _t23)) - ((-qZ) * (_t20) - (qY * _t21)));
        return dest;
    }

    public static long conjugateBy_unsafe(long dest, long src, long q) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return conjugateBy_unsafe_fma(dest, src, q);
        return conjugateBy_unsafe_mulAdd(dest, src, q);
    }

    /** {@code conjugateBy_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long conjugateBy_unsafe_fma(long dest, long src, long q) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _qx = UnsafeOpsHolder.U.getDouble(q);
        double _qy = UnsafeOpsHolder.U.getDouble(q + 8L);
        double _qz = UnsafeOpsHolder.U.getDouble(q + 16L);
        double _qw = UnsafeOpsHolder.U.getDouble(q + 24L);
        double _t20 = java.lang.Math.fma(_qx, _selfy, _qw * _selfz) + java.lang.Math.fma(_qz, _selfw, _qy * -_selfx);
        double _t21 = java.lang.Math.fma(_qy, _selfw, _qz * _selfx) + java.lang.Math.fma(_qw, _selfy, -(_qx * _selfz));
        double _t22 = java.lang.Math.fma(_qx, _selfw, _qw * _selfx) + java.lang.Math.fma(_qy, _selfz, -(_qz * _selfy));
        double _t23 = java.lang.Math.fma(_qw, _selfw, _qx * -_selfx) - java.lang.Math.fma(_qy, _selfy, _qz * _selfz);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_qy, _t20, -_qz * _t21) + java.lang.Math.fma(_qw, _t22, -_qx * _t23));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_qz, _t22, -_qy * _t23) + java.lang.Math.fma(_qw, _t21, -_qx * _t20));
        return conjugateBy_unsafe_fma_s30370fa7_1(dest, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long conjugateBy_unsafe_fma_s30370fa7_1(long dest, double _qx, double _qy, double _qz, double _qw, double _t20, double _t21, double _t22, double _t23) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_qx, _t21, _qw * _t20) + java.lang.Math.fma(-_qy, _t22, -_qz * _t23));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_qx, _t22, _qw * _t23) - java.lang.Math.fma(-_qz, _t20, -_qy * _t21));
        return dest;
    }

    /** {@code conjugateBy_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long conjugateBy_unsafe_mulAdd(long dest, long src, long q) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _qx = UnsafeOpsHolder.U.getDouble(q);
        double _qy = UnsafeOpsHolder.U.getDouble(q + 8L);
        double _qz = UnsafeOpsHolder.U.getDouble(q + 16L);
        double _qw = UnsafeOpsHolder.U.getDouble(q + 24L);
        double _t20 = ((_qx) * (_selfy) + (_qw * _selfz)) + ((_qz) * (_selfw) - (_qy * _selfx));
        double _t21 = ((_qy) * (_selfw) + (_qz * _selfx)) + ((_qw) * (_selfy) - (_qx * _selfz));
        double _t22 = ((_qx) * (_selfw) + (_qw * _selfx)) + ((_qy) * (_selfz) - (_qz * _selfy));
        double _t23 = ((_qw) * (_selfw) - (_qx * _selfx)) - ((_qy) * (_selfy) + (_qz * _selfz));
        UnsafeOpsHolder.U.putDouble(dest, ((_qy) * (_t20) - (_qz * _t21)) + ((_qw) * (_t22) - (_qx * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_qz) * (_t22) - (_qy * _t23)) + ((_qw) * (_t21) - (_qx * _t20)));
        return conjugateBy_unsafe_mulAdd_s1a35192_1(dest, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long conjugateBy_unsafe_mulAdd_s1a35192_1(long dest, double _qx, double _qy, double _qz, double _qw, double _t20, double _t21, double _t22, double _t23) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_qx) * (_t21) + (_qw * _t20)) + ((-_qy) * (_t22) - (_qz * _t23)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_qx) * (_t22) + (_qw * _t23)) - ((-_qz) * (_t20) - (_qy * _t21)));
        return dest;
    }

    public static long difference_unsafe(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return difference_unsafe_mulAdd(dest, src, otherX, otherY, otherZ, otherW);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3_inv = 1.0 / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _sp1 = _t3_inv * _selfz;
        double _sp0 = _selfy * _t3_inv;
        UnsafeOpsHolder.U.putDouble(dest, (java.lang.Math.fma(otherX, _selfw, -otherW * _selfx) + java.lang.Math.fma(otherY, _selfz, -(otherZ * _selfy))) * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -otherW * _sp0 - otherX * _sp1 + java.lang.Math.fma(otherY, _selfw, otherZ * _selfx) * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, (java.lang.Math.fma(otherX, _selfy, -otherW * _selfz) + java.lang.Math.fma(otherZ, _selfw, -otherY * _selfx)) * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(otherX, _selfx, otherW * _selfw) * _t3_inv - (-otherY * _sp0 - otherZ * _sp1));
        return dest;
    }

    /** {@code difference_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long difference_unsafe_mulAdd(long dest, long src, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3_inv = 1.0 / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        double _sp1 = _t3_inv * _selfz;
        double _sp0 = _selfy * _t3_inv;
        UnsafeOpsHolder.U.putDouble(dest, (((otherX) * (_selfw) - (otherW * _selfx)) + ((otherY) * (_selfz) - (otherZ * _selfy))) * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -otherW * _sp0 - otherX * _sp1 + ((otherY) * (_selfw) + (otherZ * _selfx)) * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, (((otherX) * (_selfy) - (otherW * _selfz)) + ((otherZ) * (_selfw) - (otherY * _selfx))) * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((otherX) * (_selfx) + (otherW * _selfw)) * _t3_inv - (-otherY * _sp0 - otherZ * _sp1));
        return dest;
    }

    public static long difference_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return difference_unsafe_fma(dest, src, other);
        return difference_unsafe_mulAdd(dest, src, other);
    }

    /** {@code difference_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long difference_unsafe_fma(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t3_inv = 1.0 / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _sp1 = _t3_inv * _selfz;
        double _sp0 = _selfy * _t3_inv;
        UnsafeOpsHolder.U.putDouble(dest, (java.lang.Math.fma(_otherx, _selfw, -_otherw * _selfx) + java.lang.Math.fma(_othery, _selfz, -(_otherz * _selfy))) * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_otherw * _sp0 - _otherx * _sp1 + java.lang.Math.fma(_othery, _selfw, _otherz * _selfx) * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, (java.lang.Math.fma(_otherx, _selfy, -_otherw * _selfz) + java.lang.Math.fma(_otherz, _selfw, -_othery * _selfx)) * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_otherx, _selfx, _otherw * _selfw) * _t3_inv - (-_othery * _sp0 - _otherz * _sp1));
        return dest;
    }

    /** {@code difference_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long difference_unsafe_mulAdd(long dest, long src, long other) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _otherx = UnsafeOpsHolder.U.getDouble(other);
        double _othery = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _otherz = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _otherw = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t3_inv = 1.0 / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        double _sp1 = _t3_inv * _selfz;
        double _sp0 = _selfy * _t3_inv;
        UnsafeOpsHolder.U.putDouble(dest, (((_otherx) * (_selfw) - (_otherw * _selfx)) + ((_othery) * (_selfz) - (_otherz * _selfy))) * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_otherw * _sp0 - _otherx * _sp1 + ((_othery) * (_selfw) + (_otherz * _selfx)) * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 16L, (((_otherx) * (_selfy) - (_otherw * _selfz)) + ((_otherz) * (_selfw) - (_othery * _selfx))) * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_otherx) * (_selfx) + (_otherw * _selfw)) * _t3_inv - (-_othery * _sp0 - _otherz * _sp1));
        return dest;
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return exp_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t2 = Math.min(Math.exp(UnsafeOpsHolder.U.getDouble(src + 24L)), 1.7976931348623157E308);
        double _t4 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t5 = java.lang.Math.sqrt(_t4);
        double _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
        if (_t4 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t10);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t10);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t10);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cosWithSin(_t5) * _t2);
        return dest;
    }

    /** {@code exp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long exp_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _t2 = Math.min(Math.exp(UnsafeOpsHolder.U.getDouble(src + 24L)), 1.7976931348623157E308);
        double _t4 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t5 = java.lang.Math.sqrt(_t4);
        double _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
        if (_t4 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t10);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t10);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t10);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cosWithSin(_t5) * _t2);
        return dest;
    }

    public static long getEulerAnglesXYZ_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_unsafe_fma(dest, src);
        return getEulerAnglesXYZ_unsafe_mulAdd(dest, src);
    }

    /** {@code getEulerAnglesXYZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_fma(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t12 = 2.0 * java.lang.Math.fma(_selfx, _selfw, -_t1);
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        return getEulerAnglesXYZ_unsafe_fma_sd609e9d3_1(dest, _selfx, _selfy, _selfz, _selfw, _t2, 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2), 2.0 * java.lang.Math.fma(_selfx, _selfw, _t1), _t12, _t13, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0), java.lang.Math.fma(_t13, _t13, _t12 * _t12));
    }

    /** Piece 2 of {@code getEulerAnglesXYZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_fma_sd609e9d3_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t2, double _t10, double _t11, double _t12, double _t13, double _t14, double _t16) {
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t11, _t14));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t12, _t13));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), 2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t10 = 2.0 * ((_selfx) * (_selfz) + (_t2));
        double _t11 = 2.0 * ((_selfx) * (_selfw) + (_t1));
        double _t12 = 2.0 * ((_selfx) * (_selfw) - (_t1));
        double _t13 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t14 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t11, _t14));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t12, _t13));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(((_t13) * (2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw))) + (2.0 * ((_selfx) * (_selfz) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static long getEulerAnglesXYZ_unsafe(long dest, long src, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_unsafe_fma(dest, src, epsilon);
        return getEulerAnglesXYZ_unsafe_mulAdd(dest, src, epsilon);
    }

    /** {@code getEulerAnglesXYZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_fma(long dest, long src, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t12 = 2.0 * java.lang.Math.fma(_selfx, _selfw, -_t1);
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        return getEulerAnglesXYZ_unsafe_fma_scadb9cc0_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t2, 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2), 2.0 * java.lang.Math.fma(_selfx, _selfw, _t1), _t12, _t13, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0), java.lang.Math.fma(_t13, _t13, _t12 * _t12));
    }

    /** Piece 2 of {@code getEulerAnglesXYZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_fma_scadb9cc0_1(long dest, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t2, double _t10, double _t11, double _t12, double _t13, double _t14, double _t16) {
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t11, _t14));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t12, _t13));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), 2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXYZ_unsafe_mulAdd(long dest, long src, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t10 = 2.0 * ((_selfx) * (_selfz) + (_t2));
        double _t11 = 2.0 * ((_selfx) * (_selfw) + (_t1));
        double _t12 = 2.0 * ((_selfx) * (_selfw) - (_t1));
        double _t13 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t14 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t11, _t14));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t12, _t13));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(((_t13) * (2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw))) + (2.0 * ((_selfx) * (_selfz) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static long getEulerAnglesXZY_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_unsafe_fma(dest, src);
        return getEulerAnglesXZY_unsafe_mulAdd(dest, src);
    }

    /** {@code getEulerAnglesXZY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXZY_unsafe_fma(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfy * _selfz;
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _t1);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0);
        return getEulerAnglesXZY_unsafe_fma_s85ae3751_1(dest, _selfx, _selfy, _selfz, _selfw, _t1, _t9, 2.0 * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy)), _t11, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesXZY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesXZY_unsafe_fma_s85ae3751_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t1, double _t9, double _t10, double _t11, double _t12, double _t14) {
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(2.0 * java.lang.Math.fma(_selfx, _selfw, -_t1), _t12));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(java.lang.Math.fma(2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t9, _t11 * -2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * -_selfw)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0 * java.lang.Math.fma(_selfy, _selfz, _selfx * -_selfw))));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXZY_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfy * _selfz;
        double _t9 = 2.0 * ((_selfx) * (_selfw) + (_t1));
        double _t10 = 2.0 * ((_selfz) * (_selfw) - (_selfx * _selfy));
        double _t11 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        double _t12 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(2.0 * ((_selfx) * (_selfw) - (_t1)), _t12));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(((2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw))) * (_t9) - (_t11 * 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw)))), ((_t12) * (_t11) - (_t9 * 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw))))));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static long getEulerAnglesXZY_unsafe(long dest, long src, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_unsafe_fma(dest, src, epsilon);
        return getEulerAnglesXZY_unsafe_mulAdd(dest, src, epsilon);
    }

    /** {@code getEulerAnglesXZY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXZY_unsafe_fma(long dest, long src, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfy * _selfz;
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _t1);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0);
        return getEulerAnglesXZY_unsafe_fma_sb096ccd2_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t1, _t9, 2.0 * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy)), _t11, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesXZY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesXZY_unsafe_fma_sb096ccd2_1(long dest, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t1, double _t9, double _t10, double _t11, double _t12, double _t14) {
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(2.0 * java.lang.Math.fma(_selfx, _selfw, -_t1), _t12));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(java.lang.Math.fma(2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t9, _t11 * -2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * -_selfw)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0 * java.lang.Math.fma(_selfy, _selfz, _selfx * -_selfw))));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesXZY_unsafe_mulAdd(long dest, long src, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfy * _selfz;
        double _t9 = 2.0 * ((_selfx) * (_selfw) + (_t1));
        double _t10 = 2.0 * ((_selfz) * (_selfw) - (_selfx * _selfy));
        double _t11 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        double _t12 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(2.0 * ((_selfx) * (_selfw) - (_t1)), _t12));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(((2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw))) * (_t9) - (_t11 * 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw)))), ((_t12) * (_t11) - (_t9 * 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw))))));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static long getEulerAnglesYXZ_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_unsafe_fma(dest, src);
        return getEulerAnglesYXZ_unsafe_mulAdd(dest, src);
    }

    /** {@code getEulerAnglesYXZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_fma(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        return getEulerAnglesYXZ_unsafe_fma_scba00415_1(dest, _selfx, _selfy, _selfz, _selfw, _t0, _t2, _t9, 2.0 * java.lang.Math.fma(_selfx, _selfw, -_t0), _t11, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesYXZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_fma_scba00415_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t2, double _t9, double _t10, double _t11, double _t12, double _t14) {
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(2.0 * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t12));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(java.lang.Math.fma(_t9, 2.0 * java.lang.Math.fma(_selfx, _selfw, _t0), _t11 * -2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2))));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t9 = 2.0 * ((_selfx) * (_selfz) + (_t2));
        double _t11 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        return getEulerAnglesYXZ_unsafe_mulAdd_sec9222de_1(dest, _selfx, _selfy, _selfz, _selfw, _t0, _t2, _t9, 2.0 * ((_selfx) * (_selfw) - (_t0)), _t11, ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz)))), ((_t11) * (_t11) + (_t9 * _t9)));
    }

    /** Piece 2 of {@code getEulerAnglesYXZ_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_mulAdd_sec9222de_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t2, double _t9, double _t10, double _t11, double _t12, double _t14) {
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(2.0 * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t12));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(((_t9) * (2.0 * ((_selfx) * (_selfw) + (_t0))) - (_t11 * 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw)))), ((_t11) * (_t12) - (_t9 * 2.0 * ((_selfx) * (_selfz) - (_t2))))));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static long getEulerAnglesYXZ_unsafe(long dest, long src, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_unsafe_fma(dest, src, epsilon);
        return getEulerAnglesYXZ_unsafe_mulAdd(dest, src, epsilon);
    }

    /** {@code getEulerAnglesYXZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_fma(long dest, long src, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _t2);
        double _t11 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        return getEulerAnglesYXZ_unsafe_fma_s196759e6_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t0, _t2, _t9, 2.0 * java.lang.Math.fma(_selfx, _selfw, -_t0), _t11, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesYXZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_fma_s196759e6_1(long dest, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t2, double _t9, double _t10, double _t11, double _t12, double _t14) {
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(2.0 * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t12));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(java.lang.Math.fma(_t9, 2.0 * java.lang.Math.fma(_selfx, _selfw, _t0), _t11 * -2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0 * java.lang.Math.fma(_selfx, _selfz, -_t2))));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_mulAdd(long dest, long src, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfy * _selfz;
        double _t2 = _selfy * _selfw;
        double _t9 = 2.0 * ((_selfx) * (_selfz) + (_t2));
        double _t11 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        return getEulerAnglesYXZ_unsafe_mulAdd_sb2035665_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t0, _t2, _t9, 2.0 * ((_selfx) * (_selfw) - (_t0)), _t11, ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz)))), ((_t11) * (_t11) + (_t9 * _t9)));
    }

    /** Piece 2 of {@code getEulerAnglesYXZ_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesYXZ_unsafe_mulAdd_sb2035665_1(long dest, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t2, double _t9, double _t10, double _t11, double _t12, double _t14) {
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(2.0 * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t12));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t9, _t11));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(((_t9) * (2.0 * ((_selfx) * (_selfw) + (_t0))) - (_t11 * 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw)))), ((_t11) * (_t12) - (_t9 * 2.0 * ((_selfx) * (_selfz) - (_t2))))));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t10, java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static long getEulerAnglesYZX_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_unsafe_fma(dest, src);
        return getEulerAnglesYZX_unsafe_mulAdd(dest, src);
    }

    /** {@code getEulerAnglesYZX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYZX_unsafe_fma(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = _selfz * _selfw;
        double _t13 = 2.0 * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0);
        return getEulerAnglesYZX_unsafe_fma_sab797d5d_1(dest, _selfx, _selfy, _selfz, _selfw, _t2, 2.0 * java.lang.Math.fma(_selfx, _selfy, _t2), 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t13, _t14, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0), java.lang.Math.fma(_t14, _t14, _t13 * _t13));
    }

    /** Piece 2 of {@code getEulerAnglesYZX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesYZX_unsafe_fma_sab797d5d_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t2, double _t11, double _t12, double _t13, double _t14, double _t15, double _t17) {
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t12, _t15));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(java.lang.Math.fma(_t14, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0 * java.lang.Math.fma(_selfx, _selfy, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t13, _t14));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t11, java.lang.Math.sqrt(_t17)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYZX_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = _selfz * _selfw;
        double _t11 = 2.0 * ((_selfx) * (_selfy) + (_t2));
        double _t12 = 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw));
        double _t13 = 2.0 * ((_selfy) * (_selfw) - (_selfx * _selfz));
        double _t14 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        double _t15 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t12, _t15));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(((_t14) * (2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz))) + (2.0 * ((_selfx) * (_selfy) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t13, _t14));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t11, java.lang.Math.sqrt(_t17)));
        return dest;
    }

    public static long getEulerAnglesYZX_unsafe(long dest, long src, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_unsafe_fma(dest, src, epsilon);
        return getEulerAnglesYZX_unsafe_mulAdd(dest, src, epsilon);
    }

    /** {@code getEulerAnglesYZX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYZX_unsafe_fma(long dest, long src, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = _selfz * _selfw;
        double _t13 = 2.0 * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        double _t14 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0);
        return getEulerAnglesYZX_unsafe_fma_s64e9465e_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t2, 2.0 * java.lang.Math.fma(_selfx, _selfy, _t2), 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t13, _t14, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0), java.lang.Math.fma(_t14, _t14, _t13 * _t13));
    }

    /** Piece 2 of {@code getEulerAnglesYZX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesYZX_unsafe_fma_s64e9465e_1(long dest, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t2, double _t11, double _t12, double _t13, double _t14, double _t15, double _t17) {
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t12, _t15));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(java.lang.Math.fma(_t14, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0 * java.lang.Math.fma(_selfx, _selfy, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t13, _t14));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t11, java.lang.Math.sqrt(_t17)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesYZX_unsafe_mulAdd(long dest, long src, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = _selfz * _selfw;
        double _t11 = 2.0 * ((_selfx) * (_selfy) + (_t2));
        double _t12 = 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw));
        double _t13 = 2.0 * ((_selfy) * (_selfw) - (_selfx * _selfz));
        double _t14 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        double _t15 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t12, _t15));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(((_t14) * (2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz))) + (2.0 * ((_selfx) * (_selfy) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t13, _t14));
        }
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t11, java.lang.Math.sqrt(_t17)));
        return dest;
    }

    public static long getEulerAnglesZXY_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_unsafe_fma(dest, src);
        return getEulerAnglesZXY_unsafe_mulAdd(dest, src);
    }

    /** {@code getEulerAnglesZXY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZXY_unsafe_fma(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfz * _selfz;
        double _t12 = 2.0 * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t1), 1.0);
        return getEulerAnglesZXY_unsafe_fma_s87caf5d_1(dest, _selfx, _selfy, _selfz, _selfw, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t12, _t13, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t1), 1.0), java.lang.Math.fma(_t13, _t13, _t12 * _t12));
    }

    /** Piece 2 of {@code getEulerAnglesZXY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesZXY_unsafe_fma_s87caf5d_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t10, double _t11, double _t12, double _t13, double _t14, double _t16) {
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t11, _t14));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t12, _t13));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZXY_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfz * _selfz;
        double _t10 = 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz));
        double _t11 = 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw));
        double _t12 = 2.0 * ((_selfz) * (_selfw) - (_selfx * _selfy));
        double _t13 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t1))));
        double _t14 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t1))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t11, _t14));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(((_t13) * (2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw))) + (2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t12, _t13));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static long getEulerAnglesZXY_unsafe(long dest, long src, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_unsafe_fma(dest, src, epsilon);
        return getEulerAnglesZXY_unsafe_mulAdd(dest, src, epsilon);
    }

    /** {@code getEulerAnglesZXY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZXY_unsafe_fma(long dest, long src, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfz * _selfz;
        double _t12 = 2.0 * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        double _t13 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t1), 1.0);
        return getEulerAnglesZXY_unsafe_fma_s681cf85e_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t12, _t13, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t1), 1.0), java.lang.Math.fma(_t13, _t13, _t12 * _t12));
    }

    /** Piece 2 of {@code getEulerAnglesZXY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesZXY_unsafe_fma_s681cf85e_1(long dest, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t10, double _t11, double _t12, double _t13, double _t14, double _t16) {
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t11, _t14));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(java.lang.Math.fma(_t13, 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t12, _t13));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZXY_unsafe_mulAdd(long dest, long src, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t1 = _selfz * _selfz;
        double _t10 = 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz));
        double _t11 = 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw));
        double _t12 = 2.0 * ((_selfz) * (_selfw) - (_selfx * _selfy));
        double _t13 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t1))));
        double _t14 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t1))));
        double _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t11, _t14));
        } else {
            UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(((_t13) * (2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw))) + (2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t12, _t13));
        }
        UnsafeOpsHolder.U.putDouble(dest, Math.atan2(_t10, java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static long getEulerAnglesZYX_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_unsafe_fma(dest, src);
        return getEulerAnglesZYX_unsafe_mulAdd(dest, src);
    }

    /** {@code getEulerAnglesZYX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZYX_unsafe_fma(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t8 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _t1);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0);
        return getEulerAnglesZYX_unsafe_fma_sa9b48487_1(dest, _selfx, _selfy, _selfz, _selfw, _t1, _t8, 2.0 * java.lang.Math.fma(_selfy, _selfw, -_selfx * _selfz), _t10, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0), java.lang.Math.fma(_t10, _t10, _t8 * _t8));
    }

    /** Piece 2 of {@code getEulerAnglesZYX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesZYX_unsafe_fma_sa9b48487_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t1, double _t8, double _t9, double _t10, double _t11, double _t13) {
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(2.0 * java.lang.Math.fma(_selfz, _selfw, -_selfx * _selfy), _t11));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(java.lang.Math.fma(_t8, 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t10 * -2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0 * java.lang.Math.fma(_selfx, _selfy, -_t1))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t8, _t10));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t9, java.lang.Math.sqrt(_t13)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZYX_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t8 = 2.0 * ((_selfx) * (_selfy) + (_t1));
        double _t9 = 2.0 * ((_selfy) * (_selfw) - (_selfx * _selfz));
        double _t10 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t0))));
        double _t11 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t0))));
        double _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 7.888609052210118E-31) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(2.0 * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(((_t8) * (2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw))) - (_t10 * 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)))), ((_t11) * (_t10) - (_t8 * 2.0 * ((_selfx) * (_selfy) - (_t1))))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t8, _t10));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t9, java.lang.Math.sqrt(_t13)));
        return dest;
    }

    public static long getEulerAnglesZYX_unsafe(long dest, long src, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_unsafe_fma(dest, src, epsilon);
        return getEulerAnglesZYX_unsafe_mulAdd(dest, src, epsilon);
    }

    /** {@code getEulerAnglesZYX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZYX_unsafe_fma(long dest, long src, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t8 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _t1);
        double _t10 = java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0);
        return getEulerAnglesZYX_unsafe_fma_s26fbdd54_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t1, _t8, 2.0 * java.lang.Math.fma(_selfy, _selfw, -_selfx * _selfz), _t10, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0), java.lang.Math.fma(_t10, _t10, _t8 * _t8));
    }

    /** Piece 2 of {@code getEulerAnglesZYX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long getEulerAnglesZYX_unsafe_fma_s26fbdd54_1(long dest, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t1, double _t8, double _t9, double _t10, double _t11, double _t13) {
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(2.0 * java.lang.Math.fma(_selfz, _selfw, -_selfx * _selfy), _t11));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(java.lang.Math.fma(_t8, 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t10 * -2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0 * java.lang.Math.fma(_selfx, _selfy, -_t1))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t8, _t10));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t9, java.lang.Math.sqrt(_t13)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long getEulerAnglesZYX_unsafe_mulAdd(long dest, long src, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t8 = 2.0 * ((_selfx) * (_selfy) + (_t1));
        double _t9 = 2.0 * ((_selfy) * (_selfw) - (_selfx * _selfz));
        double _t10 = ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_t0))));
        double _t11 = ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_t0))));
        double _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(2.0 * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, Math.atan2(((_t8) * (2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw))) - (_t10 * 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)))), ((_t11) * (_t10) - (_t8 * 2.0 * ((_selfx) * (_selfy) - (_t1))))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, Math.atan2(_t8, _t10));
        }
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.atan2(_t9, java.lang.Math.sqrt(_t13)));
        return dest;
    }

    public static long integrate_unsafe(long dest, long src, double angularVelX, double angularVelY, double angularVelZ, double dt) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return integrate_unsafe_fma(dest, src, angularVelX, angularVelY, angularVelZ, dt);
        return integrate_unsafe_mulAdd(dest, src, angularVelX, angularVelY, angularVelZ, dt);
    }

    /** {@code integrate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long integrate_unsafe_fma(long dest, long src, double angularVelX, double angularVelY, double angularVelZ, double dt) {
        double _t0 = 0.5 * dt;
        double _t1 = angularVelZ * _t0;
        double _t2 = angularVelX * _t0;
        double _t3 = angularVelY * _t0;
        double _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.cosWithSin(_t7);
        double _t11 = Math.sinWithCos(_t7) / _t7;
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t9, _selfw * _t15) + java.lang.Math.fma(_selfz, _t16, -(_selfy * _t17)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfx, _t17, _selfw * _t16) + java.lang.Math.fma(_selfy, _t9, -(_selfz * _t15)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfy, _t15, _selfz * _t9) + java.lang.Math.fma(_selfw, _t17, -_selfx * _t16));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t9, -_selfx * _t15) - java.lang.Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    /** {@code integrate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long integrate_unsafe_mulAdd(long dest, long src, double angularVelX, double angularVelY, double angularVelZ, double dt) {
        double _t0 = 0.5 * dt;
        double _t1 = angularVelZ * _t0;
        double _t2 = angularVelX * _t0;
        double _t3 = angularVelY * _t0;
        double _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.cosWithSin(_t7);
        double _t11 = Math.sinWithCos(_t7) / _t7;
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t9) + (_selfw * _t15)) + ((_selfz) * (_t16) - (_selfy * _t17)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfx) * (_t17) + (_selfw * _t16)) + ((_selfy) * (_t9) - (_selfz * _t15)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfy) * (_t15) + (_selfz * _t9)) + ((_selfw) * (_t17) - (_selfx * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t9) - (_selfx * _t15)) - ((_selfy) * (_t16) + (_selfz * _t17)));
        return dest;
    }

    public static long integrate_unsafe(long dest, long src, long angularVel, double dt) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return integrate_unsafe_fma(dest, src, angularVel, dt);
        return integrate_unsafe_mulAdd(dest, src, angularVel, dt);
    }

    /** {@code integrate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long integrate_unsafe_fma(long dest, long src, long angularVel, double dt) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * dt;
        double _t1 = UnsafeOpsHolder.U.getDouble(angularVel + 16L) * _t0;
        double _t2 = UnsafeOpsHolder.U.getDouble(angularVel) * _t0;
        double _t3 = UnsafeOpsHolder.U.getDouble(angularVel + 8L) * _t0;
        double _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.cosWithSin(_t7);
        double _t11 = Math.sinWithCos(_t7) / _t7;
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t9, _selfw * _t15) + java.lang.Math.fma(_selfz, _t16, -(_selfy * _t17)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfx, _t17, _selfw * _t16) + java.lang.Math.fma(_selfy, _t9, -(_selfz * _t15)));
        return integrate_unsafe_fma_sf4780dd8_1(dest, _selfx, _selfy, _selfz, _selfw, _t9, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long integrate_unsafe_fma_sf4780dd8_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t9, double _t15, double _t16, double _t17) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfy, _t15, _selfz * _t9) + java.lang.Math.fma(_selfw, _t17, -_selfx * _t16));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t9, -_selfx * _t15) - java.lang.Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    /** {@code integrate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long integrate_unsafe_mulAdd(long dest, long src, long angularVel, double dt) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * dt;
        double _t1 = UnsafeOpsHolder.U.getDouble(angularVel + 16L) * _t0;
        double _t2 = UnsafeOpsHolder.U.getDouble(angularVel) * _t0;
        double _t3 = UnsafeOpsHolder.U.getDouble(angularVel + 8L) * _t0;
        double _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.cosWithSin(_t7);
        double _t11 = Math.sinWithCos(_t7) / _t7;
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t9) + (_selfw * _t15)) + ((_selfz) * (_t16) - (_selfy * _t17)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfx) * (_t17) + (_selfw * _t16)) + ((_selfy) * (_t9) - (_selfz * _t15)));
        return integrate_unsafe_mulAdd_s40aa3099_1(dest, _selfx, _selfy, _selfz, _selfw, _t9, _t15, _t16, _t17);
    }

    /** Piece 2 of {@code integrate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long integrate_unsafe_mulAdd_s40aa3099_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t9, double _t15, double _t16, double _t17) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfy) * (_t15) + (_selfz * _t9)) + ((_selfw) * (_t17) - (_selfx * _t16)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t9) - (_selfx * _t15)) - ((_selfy) * (_t16) + (_selfz * _t17)));
        return dest;
    }

    public static long invNegativeX_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeX_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t12 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t10 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t9 * _t16));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    /** {@code invNegativeX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invNegativeX_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw));
        double _t10 = 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw));
        double _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t12 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t10 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t9 * _t16));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    public static long invNegativeY_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeY_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t9 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t12 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t10 * _t16));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    /** {@code invNegativeY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invNegativeY_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw));
        double _t10 = 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw));
        double _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t9 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t12 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t10 * _t16));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    public static long invNegativeZ_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeZ_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        double _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t10 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t9 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t12 * _t16));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    /** {@code invNegativeZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invNegativeZ_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz));
        double _t10 = 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw));
        double _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t10 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t9 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t12 * _t16));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    public static long invNormalizedNegativeX_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz))) - (1.0)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            return dest;
        }
    }

    public static long invNormalizedNegativeY_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, -2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, -2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz))) - (1.0)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            return dest;
        }
    }

    public static long invNormalizedNegativeZ_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, -2.0 * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, -2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))) - (1.0)));
            return dest;
        }
    }

    public static long invNormalizedPositiveX_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz)))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            return dest;
        }
    }

    public static long invNormalizedPositiveY_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz)))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            return dest;
        }
    }

    public static long invNormalizedPositiveZ_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, 2.0 * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
            return dest;
        }
    }

    public static long invPositiveX_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveX_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t12 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t10 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t9 * _t16);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    /** {@code invPositiveX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invPositiveX_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw));
        double _t10 = 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw));
        double _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t12 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t10 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t9 * _t16);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long invPositiveY_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveY_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t9 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t12 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t10 * _t16);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    /** {@code invPositiveY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invPositiveY_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw));
        double _t10 = 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw));
        double _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t9 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t12 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t10 * _t16);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long invPositiveZ_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveZ_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        double _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t10 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t9 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t12 * _t16);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    /** {@code invPositiveZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invPositiveZ_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz));
        double _t10 = 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw));
        double _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t10 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t9 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t12 * _t16);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return log_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t6 = Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t6);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.log(java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2))));
        return dest;
    }

    /** {@code log_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long log_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t6 = Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _selfx * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _selfy * _t6);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _selfz * _t6);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.log(java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2)))));
        return dest;
    }

    public static long negativeX_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeX_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfz, -_selfy * _selfw);
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t12 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t9 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t10 * _t16));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    /** {@code negativeX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long negativeX_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw));
        double _t10 = 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw));
        double _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t12 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t9 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t10 * _t16));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    public static long negativeY_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeY_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t10 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t12 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t9 * _t16));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    /** {@code negativeY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long negativeY_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz));
        double _t10 = 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw));
        double _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t10 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t12 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t9 * _t16));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    public static long negativeZ_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeZ_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        double _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t9 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t10 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t12 * _t16));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
        return dest;
    }

    /** {@code negativeZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long negativeZ_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw));
        double _t10 = 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw));
        double _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, -(_t9 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t10 * _t16));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t12 * _t16));
        } else {
            UnsafeOpsHolder.U.putDouble(dest, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, -0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, -0.0);
        }
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

    public static long normalizedNegativeX_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -2.0 * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz))) - (1.0)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            return dest;
        }
    }

    public static long normalizedNegativeY_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, -2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, -2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz))) - (1.0)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, -2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            return dest;
        }
    }

    public static long normalizedNegativeZ_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, -2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, -2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, -2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy))) - (1.0)));
            return dest;
        }
    }

    public static long normalizedPositiveX_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, ((1.0) - (2.0) * (((_selfy) * (_selfy) + (_selfz * _selfz)))));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            return dest;
        }
    }

    public static long normalizedPositiveY_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, 2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfz * _selfz)))));
            UnsafeOpsHolder.U.putDouble(dest + 16L, 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            return dest;
        }
    }

    public static long normalizedPositiveZ_unsafe(long dest, long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(-2.0, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
            return dest;
        } else {
            double _selfx = UnsafeOpsHolder.U.getDouble(src);
            double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
            double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
            double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
            UnsafeOpsHolder.U.putDouble(dest, 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 8L, 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            UnsafeOpsHolder.U.putDouble(dest + 16L, ((1.0) - (2.0) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
            return dest;
        }
    }

    public static long positiveX_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveX_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfz, -_selfy * _selfw);
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t12 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t9 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t10 * _t16);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    /** {@code positiveX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long positiveX_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_selfy) + (_selfz * _selfw));
        double _t10 = 2.0 * ((_selfx) * (_selfz) - (_selfy * _selfw));
        double _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        double _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t12 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t9 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t10 * _t16);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long positiveY_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveY_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t10 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t12 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t9 * _t16);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    /** {@code positiveY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long positiveY_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_selfw) + (_selfy * _selfz));
        double _t10 = 2.0 * ((_selfx) * (_selfy) - (_selfz * _selfw));
        double _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        double _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t10 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t12 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t9 * _t16);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long positiveZ_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveZ_unsafe_mulAdd(dest, src);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        double _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        double _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t9 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t10 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t12 * _t16);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    /** {@code positiveZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long positiveZ_unsafe_mulAdd(long dest, long src) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_selfz) + (_selfy * _selfw));
        double _t10 = 2.0 * ((_selfy) * (_selfz) - (_selfx * _selfw));
        double _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        double _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t9 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t10 * _t16);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t12 * _t16);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        return dest;
    }

    public static long pow_unsafe(long dest, long src, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return pow_unsafe_fma(dest, src, t);
        return pow_unsafe_mulAdd(dest, src, t);
    }

    /** {@code pow_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long pow_unsafe_fma(long dest, long src, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t12 = Math.min(Math.exp(t == 0.0 ? 0.0 : t * Math.log(java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2)))), 1.7976931348623157E308);
        double _t13 = Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2));
        double _t20, _t21, _t22;
        if (_t2 > 0.0) {
            _t20 = t * _selfz * _t13;
            _t21 = t * _selfx * _t13;
            _t22 = t * _selfy * _t13;
        } else {
            _t20 = t * 0.0;
            _t21 = t * 0.0;
            _t22 = t * 0.0;
        }
        double _t25 = java.lang.Math.fma(_t20, _t20, java.lang.Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = java.lang.Math.sqrt(_t25);
        return pow_unsafe_fma_sd3545480_1(dest, _t12, _t20, _t21, _t22, _t25, _t26, _t12 * (Math.sinWithCos(_t26) / _t26));
    }

    /** Piece 2 of {@code pow_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long pow_unsafe_fma_sd3545480_1(long dest, double _t12, double _t20, double _t21, double _t22, double _t25, double _t26, double _t31) {
        if (_t25 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t21 * _t31);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t22 * _t31);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t20 * _t31);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cosWithSin(_t26) * _t12);
        return dest;
    }

    /** {@code pow_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long pow_unsafe_mulAdd(long dest, long src, double t) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        double _t12 = Math.min(Math.exp(t == 0.0 ? 0.0 : t * Math.log(java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2))))), 1.7976931348623157E308);
        double _t13 = Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2));
        double _t20, _t21, _t22;
        if (_t2 > 0.0) {
            _t20 = t * _selfz * _t13;
            _t21 = t * _selfx * _t13;
            _t22 = t * _selfy * _t13;
        } else {
            _t20 = t * 0.0;
            _t21 = t * 0.0;
            _t22 = t * 0.0;
        }
        double _t25 = ((_t20) * (_t20) + (((_t21) * (_t21) + (_t22 * _t22))));
        double _t26 = java.lang.Math.sqrt(_t25);
        return pow_unsafe_mulAdd_sc66e84ab_1(dest, _t12, _t20, _t21, _t22, _t25, _t26, _t12 * (Math.sinWithCos(_t26) / _t26));
    }

    /** Piece 2 of {@code pow_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long pow_unsafe_mulAdd_sc66e84ab_1(long dest, double _t12, double _t20, double _t21, double _t22, double _t25, double _t26, double _t31) {
        if (_t25 > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t21 * _t31);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t22 * _t31);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t20 * _t31);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        }
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cosWithSin(_t26) * _t12);
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double step) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_unsafe_fma(dest, src, targetX, targetY, targetZ, targetW, step);
        return rotateTowards_unsafe_mulAdd(dest, src, targetX, targetY, targetZ, targetW, step);
    }

    /** {@code rotateTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_fma(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double step) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_selfw, targetW, java.lang.Math.fma(_selfz, targetZ, java.lang.Math.fma(_selfx, targetX, _selfy * targetY))) > 0.0) {
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
        double _t13 = _selfw + _t9;
        double _t14 = _selfz + _t10;
        double _t15 = _selfx + _t11;
        double _t16 = _selfy + _t12;
        double _t17 = _selfw - _t9;
        double _t18 = _selfz - _t10;
        double _t19 = _selfx - _t11;
        double _t20 = _selfy - _t12;
        double _t27 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)));
        double _t28 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        double _t31 = _t27 * _t28;
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        return rotateTowards_unsafe_fma_sb0662701_1(dest, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t31, 2.0 * (1.0 / java.lang.Math.sqrt(_t31)), 4.0 * _t34, _t34 + _t34);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_sb0662701_1(long dest, double step, double _selfx, double _selfy, double _selfz, double _selfw, double _t9, double _t10, double _t11, double _t12, double _t31, double _sp0, double _t36, double _t37) {
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        double _t41 = 1.0 - _t40;
        double _t52 = _sp0 * Math.sin(_t37 * _t40);
        double _t53 = _sp0 * Math.sin(_t41 * _t37);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = java.lang.Math.fma(_selfw, _t53, _t52 * _t9);
            _t67 = java.lang.Math.fma(_selfz, _t53, _t52 * _t10);
            _t68 = java.lang.Math.fma(_selfx, _t53, _t52 * _t11);
            _t69 = java.lang.Math.fma(_selfy, _t53, _t52 * _t12);
        } else {
            _t66 = java.lang.Math.fma(_selfw, _t41, _t9 * _t40);
            _t67 = java.lang.Math.fma(_selfz, _t41, _t10 * _t40);
            _t68 = java.lang.Math.fma(_selfx, _t41, _t11 * _t40);
            _t69 = java.lang.Math.fma(_selfy, _t41, _t12 * _t40);
        }
        double _t73 = java.lang.Math.fma(_t66, _t66, java.lang.Math.fma(_t67, _t67, java.lang.Math.fma(_t68, _t68, _t69 * _t69)));
        return rotateTowards_unsafe_fma_sb0662701_2(dest, _t66, _t67, _t68, _t69, _t73, (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_sb0662701_2(long dest, double _t66, double _t67, double _t68, double _t69, double _t73, double _t74) {
        if (_t73 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t74 * _t68);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t74 * _t69);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t74 * _t67);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t74 * _t66);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    /** {@code rotateTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_mulAdd(long dest, long src, double targetX, double targetY, double targetZ, double targetW, double step) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9, _t10, _t11, _t12;
        if (-((_selfw) * (targetW) + (((_selfz) * (targetZ) + (((_selfx) * (targetX) + (_selfy * targetY)))))) > 0.0) {
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
        double _t13 = _selfw + _t9;
        double _t14 = _selfz + _t10;
        double _t15 = _selfx + _t11;
        double _t16 = _selfy + _t12;
        double _t17 = _selfw - _t9;
        double _t18 = _selfz - _t10;
        double _t19 = _selfx - _t11;
        double _t20 = _selfy - _t12;
        double _t27 = ((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))));
        double _t28 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        double _t31 = _t27 * _t28;
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        return rotateTowards_unsafe_mulAdd_s86ad17ba_1(dest, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t31, 2.0 * (1.0 / java.lang.Math.sqrt(_t31)), 4.0 * _t34, _t34 + _t34);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s86ad17ba_1(long dest, double step, double _selfx, double _selfy, double _selfz, double _selfw, double _t9, double _t10, double _t11, double _t12, double _t31, double _sp0, double _t36, double _t37) {
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        double _t41 = 1.0 - _t40;
        double _t52 = _sp0 * Math.sin(_t37 * _t40);
        double _t53 = _sp0 * Math.sin(_t41 * _t37);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = ((_selfw) * (_t53) + (_t52 * _t9));
            _t67 = ((_selfz) * (_t53) + (_t52 * _t10));
            _t68 = ((_selfx) * (_t53) + (_t52 * _t11));
            _t69 = ((_selfy) * (_t53) + (_t52 * _t12));
        } else {
            _t66 = ((_selfw) * (_t41) + (_t9 * _t40));
            _t67 = ((_selfz) * (_t41) + (_t10 * _t40));
            _t68 = ((_selfx) * (_t41) + (_t11 * _t40));
            _t69 = ((_selfy) * (_t41) + (_t12 * _t40));
        }
        double _t73 = ((_t66) * (_t66) + (((_t67) * (_t67) + (((_t68) * (_t68) + (_t69 * _t69))))));
        return rotateTowards_unsafe_mulAdd_s86ad17ba_2(dest, _t66, _t67, _t68, _t69, _t73, (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_s86ad17ba_2(long dest, double _t66, double _t67, double _t68, double _t69, double _t73, double _t74) {
        if (_t73 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t74 * _t68);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t74 * _t69);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t74 * _t67);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t74 * _t66);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long rotateTowards_unsafe(long dest, long src, long target, double step) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_unsafe_fma(dest, src, target, step);
        return rotateTowards_unsafe_mulAdd(dest, src, target, step);
    }

    /** {@code rotateTowards_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_fma(long dest, long src, long target, double step) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _targetx = UnsafeOpsHolder.U.getDouble(target);
        double _targety = UnsafeOpsHolder.U.getDouble(target + 8L);
        double _targetz = UnsafeOpsHolder.U.getDouble(target + 16L);
        double _targetw = UnsafeOpsHolder.U.getDouble(target + 24L);
        double _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety))) > 0.0) {
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
        double _t13 = _selfw + _t9;
        double _t14 = _selfz + _t10;
        double _t15 = _selfx + _t11;
        double _t16 = _selfy + _t12;
        double _t17 = _selfw - _t9;
        double _t18 = _selfz - _t10;
        double _t19 = _selfx - _t11;
        double _t20 = _selfy - _t12;
        return rotateTowards_unsafe_fma_s39509018_1(dest, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16))), java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20))));
    }

    /** Piece 2 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s39509018_1(long dest, double step, double _selfx, double _selfy, double _selfz, double _selfw, double _t9, double _t10, double _t11, double _t12, double _t27, double _t28) {
        double _t31 = _t27 * _t28;
        double _sp0 = 2.0 * (1.0 / java.lang.Math.sqrt(_t31));
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        double _t37 = _t34 + _t34;
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        double _t41 = 1.0 - _t40;
        double _t52 = _sp0 * Math.sin(_t37 * _t40);
        double _t53 = _sp0 * Math.sin(_t41 * _t37);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = java.lang.Math.fma(_selfw, _t53, _t52 * _t9);
            _t67 = java.lang.Math.fma(_selfz, _t53, _t52 * _t10);
            _t68 = java.lang.Math.fma(_selfx, _t53, _t52 * _t11);
            _t69 = java.lang.Math.fma(_selfy, _t53, _t52 * _t12);
        } else {
            _t66 = java.lang.Math.fma(_selfw, _t41, _t9 * _t40);
            _t67 = java.lang.Math.fma(_selfz, _t41, _t10 * _t40);
            _t68 = java.lang.Math.fma(_selfx, _t41, _t11 * _t40);
            _t69 = java.lang.Math.fma(_selfy, _t41, _t12 * _t40);
        }
        double _t73 = java.lang.Math.fma(_t66, _t66, java.lang.Math.fma(_t67, _t67, java.lang.Math.fma(_t68, _t68, _t69 * _t69)));
        return rotateTowards_unsafe_fma_s39509018_2(dest, _t66, _t67, _t68, _t69, _t73, (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_fma_s39509018_2(long dest, double _t66, double _t67, double _t68, double _t69, double _t73, double _t74) {
        if (_t73 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t74 * _t68);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t74 * _t69);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t74 * _t67);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t74 * _t66);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    /** {@code rotateTowards_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTowards_unsafe_mulAdd(long dest, long src, long target, double step) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _targetx = UnsafeOpsHolder.U.getDouble(target);
        double _targety = UnsafeOpsHolder.U.getDouble(target + 8L);
        double _targetz = UnsafeOpsHolder.U.getDouble(target + 16L);
        double _targetw = UnsafeOpsHolder.U.getDouble(target + 24L);
        double _t9, _t10, _t11, _t12;
        if (-((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety)))))) > 0.0) {
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
        double _t13 = _selfw + _t9;
        double _t14 = _selfz + _t10;
        double _t15 = _selfx + _t11;
        double _t16 = _selfy + _t12;
        double _t17 = _selfw - _t9;
        double _t18 = _selfz - _t10;
        double _t19 = _selfx - _t11;
        double _t20 = _selfy - _t12;
        double _t27 = ((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))));
        double _t28 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        return rotateTowards_unsafe_mulAdd_sad32d58d_1(dest, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t27, _t28, _t27 * _t28);
    }

    /** Piece 2 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_sad32d58d_1(long dest, double step, double _selfx, double _selfy, double _selfz, double _selfw, double _t9, double _t10, double _t11, double _t12, double _t27, double _t28, double _t31) {
        double _sp0 = 2.0 * (1.0 / java.lang.Math.sqrt(_t31));
        double _t34 = Math.atan2(java.lang.Math.sqrt(_t28), java.lang.Math.sqrt(_t27));
        double _t36 = 4.0 * _t34;
        double _t37 = _t34 + _t34;
        double _t40 = _t36 > 0.0 ? Math.min(1.0, step / _t36) : 0.0;
        double _t41 = 1.0 - _t40;
        double _t52 = _sp0 * Math.sin(_t37 * _t40);
        double _t53 = _sp0 * Math.sin(_t41 * _t37);
        double _t66, _t67, _t68, _t69;
        if (0.5 * java.lang.Math.sqrt(_t31) > 0.0) {
            _t66 = ((_selfw) * (_t53) + (_t52 * _t9));
            _t67 = ((_selfz) * (_t53) + (_t52 * _t10));
            _t68 = ((_selfx) * (_t53) + (_t52 * _t11));
            _t69 = ((_selfy) * (_t53) + (_t52 * _t12));
        } else {
            _t66 = ((_selfw) * (_t41) + (_t9 * _t40));
            _t67 = ((_selfz) * (_t41) + (_t10 * _t40));
            _t68 = ((_selfx) * (_t41) + (_t11 * _t40));
            _t69 = ((_selfy) * (_t41) + (_t12 * _t40));
        }
        double _t73 = ((_t66) * (_t66) + (((_t67) * (_t67) + (((_t68) * (_t68) + (_t69 * _t69))))));
        return rotateTowards_unsafe_mulAdd_sad32d58d_2(dest, _t66, _t67, _t68, _t69, _t73, (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code rotateTowards_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTowards_unsafe_mulAdd_sad32d58d_2(long dest, double _t66, double _t67, double _t68, double _t69, double _t73, double _t74) {
        if (_t73 != 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _t74 * _t68);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t74 * _t69);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t74 * _t67);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t74 * _t66);
        } else {
            UnsafeOpsHolder.U.putDouble(dest, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
        }
        return dest;
    }

    public static long lookAlong_lh(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        DoubleQuatOpsKernelsSegment.lookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static long lookAlong_lh_unsafe(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, upX);
        double _t20 = java.lang.Math.fma(_t18, _t13, upY);
        double _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_unsafe_fma_sdf0896b4_1(dest, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sdf0896b4_1(long dest, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return lookAlong_lh_unsafe_fma_sdf0896b4_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sdf0896b4_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        return lookAlong_lh_unsafe_fma_sdf0896b4_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sdf0896b4_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t19 = ((_t18) * (_t12) + (upX));
        double _t20 = ((_t18) * (_t13) + (upY));
        double _t21 = ((_t18) * (_t11) + (upZ));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return lookAlong_lh_unsafe_mulAdd_sfb4f7db7_1(dest, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sfb4f7db7_1(long dest, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return lookAlong_lh_unsafe_mulAdd_sfb4f7db7_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sfb4f7db7_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1, double _sp2) {
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_unsafe_mulAdd_sfb4f7db7_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_sfb4f7db7_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        DoubleQuatOpsKernelsSegment.lookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static long lookAlong_rh_unsafe(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, upY);
        double _t22 = java.lang.Math.fma(_t20, _t12, upX);
        double _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_unsafe_fma_s5d422ade_1(dest, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5d422ade_1(long dest, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        return lookAlong_rh_unsafe_fma_s5d422ade_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5d422ade_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        return lookAlong_rh_unsafe_fma_s5d422ade_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5d422ade_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t21 = ((_t20) * (_t13) + (upY));
        double _t22 = ((_t20) * (_t12) + (upX));
        double _t23 = ((_t20) * (_t11) + (upZ));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_unsafe_mulAdd_sa8b101a1_1(dest, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sa8b101a1_1(long dest, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return lookAlong_rh_unsafe_mulAdd_sa8b101a1_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sa8b101a1_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3, double _sp1, double _sp2) {
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_unsafe_mulAdd_sa8b101a1_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sa8b101a1_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static long lookAlong_lh(long dest, long src, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dir, up);
        DoubleQuatOpsKernelsSegment.lookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L);
        return dest;
    }

    public static long lookAlong_lh_unsafe(long dest, long src, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dir, up);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dir, up);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        double _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        return lookAlong_lh_unsafe_fma_s96ce96ea_1(dest, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t19, _t21, java.lang.Math.fma(_t19, _t13, -(_t20 * _t12)), java.lang.Math.fma(_t20, _t11, -(_t21 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s96ce96ea_1(long dest, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t19, double _t21, double _t28, double _t29) {
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        return lookAlong_lh_unsafe_fma_s96ce96ea_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t39, _t41, _t43, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s96ce96ea_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t39, double _t41, double _t43, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73) {
        double _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t72));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        return lookAlong_lh_unsafe_fma_s96ce96ea_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s96ce96ea_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t19 = ((_t18) * (_t12) + (_upx));
        double _t20 = ((_t18) * (_t13) + (_upy));
        double _t21 = ((_t18) * (_t11) + (_upz));
        return lookAlong_lh_unsafe_mulAdd_se2d36c15_1(dest, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, ((_t19) * (_t13) - (_t20 * _t12)), ((_t20) * (_t11) - (_t21 * _t13)), ((_t21) * (_t12) - (_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_se2d36c15_1(long dest, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30) {
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        return lookAlong_lh_unsafe_mulAdd_se2d36c15_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11)))), ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_se2d36c15_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_unsafe_mulAdd_se2d36c15_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_se2d36c15_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dir, up);
        DoubleQuatOpsKernelsSegment.lookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L);
        return dest;
    }

    public static long lookAlong_rh_unsafe(long dest, long src, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dir, up);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dir, up);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        return lookAlong_rh_unsafe_fma_s5403cc14_1(dest, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t21, _t22, java.lang.Math.fma(_t20, _t11, _upz), java.lang.Math.fma(_t21, _t12, -(_t22 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5403cc14_1(long dest, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t21, double _t22, double _t23, double _t30) {
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        return lookAlong_rh_unsafe_fma_s5403cc14_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5403cc14_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t44, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74) {
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        double _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        return lookAlong_rh_unsafe_fma_s5403cc14_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s5403cc14_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t21 = ((_t20) * (_t13) + (_upy));
        double _t22 = ((_t20) * (_t12) + (_upx));
        double _t23 = ((_t20) * (_t11) + (_upz));
        return lookAlong_rh_unsafe_mulAdd_sfcfb9957_1(dest, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, ((_t21) * (_t12) - (_t22 * _t13)), ((_t22) * (_t11) - (_t23 * _t12)), ((_t23) * (_t13) - (_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sfcfb9957_1(long dest, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32) {
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        return lookAlong_rh_unsafe_mulAdd_sfcfb9957_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sfcfb9957_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_unsafe_mulAdd_sfcfb9957_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_sfcfb9957_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static long lookAlong_lh(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        DoubleQuatOpsKernelsSegment.lookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static long lookAlong_lh_unsafe(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, upX);
        double _t20 = java.lang.Math.fma(_t18, _t13, upY);
        double _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_unsafe_fma_sf304bc97_1(dest, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sf304bc97_1(long dest, double upX, double upY, double upZ, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return lookAlong_lh_unsafe_fma_sf304bc97_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sf304bc97_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        return lookAlong_lh_unsafe_fma_sf304bc97_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_sf304bc97_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t19 = ((_t18) * (_t12) + (upX));
        double _t20 = ((_t18) * (_t13) + (upY));
        double _t21 = ((_t18) * (_t11) + (upZ));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return lookAlong_lh_unsafe_mulAdd_s136adba4_1(dest, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s136adba4_1(long dest, double upX, double upY, double upZ, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return lookAlong_lh_unsafe_mulAdd_s136adba4_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s136adba4_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3, double _sp1, double _sp2) {
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_unsafe_mulAdd_s136adba4_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s136adba4_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        DoubleQuatOpsKernelsSegment.lookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static long lookAlong_rh_unsafe(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, upY);
        double _t22 = java.lang.Math.fma(_t20, _t12, upX);
        double _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_unsafe_fma_s8ac55e65_1(dest, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s8ac55e65_1(long dest, double upX, double upY, double upZ, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        return lookAlong_rh_unsafe_fma_s8ac55e65_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s8ac55e65_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        return lookAlong_rh_unsafe_fma_s8ac55e65_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s8ac55e65_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t21 = ((_t20) * (_t13) + (upY));
        double _t22 = ((_t20) * (_t12) + (upX));
        double _t23 = ((_t20) * (_t11) + (upZ));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_unsafe_mulAdd_s3d7cbd42_1(dest, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s3d7cbd42_1(long dest, double upX, double upY, double upZ, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return lookAlong_rh_unsafe_mulAdd_s3d7cbd42_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s3d7cbd42_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3, double _sp1, double _sp2) {
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_unsafe_mulAdd_s3d7cbd42_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s3d7cbd42_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static long lookAlong_lh(long dest, long src, long dir, long up, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.lookAlong_lh_unsafe(dest, src, dir, up, epsilon);
        DoubleQuatOpsKernelsSegment.lookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L, epsilon);
        return dest;
    }

    public static long lookAlong_lh_unsafe(long dest, long src, long dir, long up, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_unsafe_fma(dest, src, dir, up, epsilon);
        return lookAlong_lh_unsafe_mulAdd(dest, src, dir, up, epsilon);
    }

    /** {@code lookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_fma(long dest, long src, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        double _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        return lookAlong_lh_unsafe_fma_s13a9f121_1(dest, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t19, _t21, java.lang.Math.fma(_t19, _t13, -(_t20 * _t12)), java.lang.Math.fma(_t20, _t11, -(_t21 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s13a9f121_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t19, double _t21, double _t28, double _t29) {
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        return lookAlong_lh_unsafe_fma_s13a9f121_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t39, _t41, _t43, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s13a9f121_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t39, double _t41, double _t43, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73) {
        double _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t72));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        return lookAlong_lh_unsafe_fma_s13a9f121_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_fma_s13a9f121_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd(long dest, long src, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t19 = ((_t18) * (_t12) + (_upx));
        double _t20 = ((_t18) * (_t13) + (_upy));
        double _t21 = ((_t18) * (_t11) + (_upz));
        return lookAlong_lh_unsafe_mulAdd_s1044c1e6_1(dest, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, ((_t19) * (_t13) - (_t20 * _t12)), ((_t20) * (_t11) - (_t21 * _t13)), ((_t21) * (_t12) - (_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s1044c1e6_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30) {
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        return lookAlong_lh_unsafe_mulAdd_s1044c1e6_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11)))), ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s1044c1e6_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t71));
        double _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5 * java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5 * java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5 * java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t74);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_unsafe_mulAdd_s1044c1e6_3(dest, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_lh_unsafe_mulAdd_s1044c1e6_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t119, double _t120, double _t121, double _t122) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static long lookAlong_rh(long dest, long src, long dir, long up, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.lookAlong_rh_unsafe(dest, src, dir, up, epsilon);
        DoubleQuatOpsKernelsSegment.lookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L, epsilon);
        return dest;
    }

    public static long lookAlong_rh_unsafe(long dest, long src, long dir, long up, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_unsafe_fma(dest, src, dir, up, epsilon);
        return lookAlong_rh_unsafe_mulAdd(dest, src, dir, up, epsilon);
    }

    /** {@code lookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_fma(long dest, long src, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        return lookAlong_rh_unsafe_fma_s7c0d0f7_1(dest, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t21, _t22, java.lang.Math.fma(_t20, _t11, _upz), java.lang.Math.fma(_t21, _t12, -(_t22 * _t13)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s7c0d0f7_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t21, double _t22, double _t23, double _t30) {
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        return lookAlong_rh_unsafe_fma_s7c0d0f7_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s7c0d0f7_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t44, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74) {
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        double _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        return lookAlong_rh_unsafe_fma_s7c0d0f7_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_fma_s7c0d0f7_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd(long dest, long src, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t21 = ((_t20) * (_t13) + (_upy));
        double _t22 = ((_t20) * (_t12) + (_upx));
        double _t23 = ((_t20) * (_t11) + (_upz));
        return lookAlong_rh_unsafe_mulAdd_s8bb0b544_1(dest, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, ((_t21) * (_t12) - (_t22 * _t13)), ((_t22) * (_t11) - (_t23 * _t12)), ((_t23) * (_t13) - (_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s8bb0b544_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32) {
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        return lookAlong_rh_unsafe_mulAdd_s8bb0b544_2(dest, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41)))), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s8bb0b544_2(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t14, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp3) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t76));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5 * java.lang.Math.sqrt(_t76);
                }
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_unsafe_mulAdd_s8bb0b544_3(dest, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long lookAlong_rh_unsafe_mulAdd_s8bb0b544_3(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t121, double _t122, double _t123, double _t124) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, axisX * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, axisY * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, axisZ * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cosWithSin(_t0));
        return dest;
    }

    public static long makeRotationAxis_unsafe(long dest, long axis, double angle) {
        double _axisx = UnsafeOpsHolder.U.getDouble(axis);
        double _axisy = UnsafeOpsHolder.U.getDouble(axis + 8L);
        double _axisz = UnsafeOpsHolder.U.getDouble(axis + 16L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, _axisx * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _axisy * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _axisz * _t1);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cosWithSin(_t0));
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ);
        DoubleQuatOpsKernelsSegment.makeRotationLookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, upX);
        double _t20 = java.lang.Math.fma(_t18, _t13, upY);
        double _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_unsafe_fma_s1ac5f254_1(dest, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_s1ac5f254_1(long dest, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t48, double _t49) {
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        double _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38));
        return makeRotationLookAlong_lh_unsafe_fma_s1ac5f254_2(dest, _t11, _t12, _t38, _t41, _t48, _t49, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_s1ac5f254_2(long dest, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t49);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t69);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t68);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t48);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t19 = ((_t18) * (_t12) + (upX));
        double _t20 = ((_t18) * (_t13) + (upY));
        double _t21 = ((_t18) * (_t11) + (upZ));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_unsafe_mulAdd_s1fb56b27_1(dest, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_s1fb56b27_1(long dest, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t48, double _t49, double _t60) {
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return makeRotationLookAlong_lh_unsafe_mulAdd_s1fb56b27_2(dest, _t11, _t12, _t38, _t41, _t48, _t49, _t60, ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_s1fb56b27_2(long dest, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t49);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t69);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t68);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t48);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ);
        DoubleQuatOpsKernelsSegment.makeRotationLookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        makeRotationLookAlong_rh_unsafe_fma_s528c9166_500(dest, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    /** Part of {@code makeRotationLookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_s528c9166_500(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, upY);
        double _t22 = java.lang.Math.fma(_t20, _t12, upX);
        double _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        makeRotationLookAlong_rh_unsafe_fma_s528c9166_500_sc794ae54_1(dest, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_fma_s528c9166_500}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_s528c9166_500_sc794ae54_1(long dest, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t50) {
        double _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        double _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        double _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41));
        makeRotationLookAlong_rh_unsafe_fma_s528c9166_500_sc794ae54_2(dest, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_unsafe_fma_s528c9166_500}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_s528c9166_500_sc794ae54_2(long dest, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t51);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t71);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t70);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t50);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t71);
                }
            }
        }
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t21 = ((_t20) * (_t13) + (upY));
        double _t22 = ((_t20) * (_t12) + (upX));
        double _t23 = ((_t20) * (_t11) + (upZ));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return makeRotationLookAlong_rh_unsafe_mulAdd_s3a18c539_1(dest, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_s3a18c539_1(long dest, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t50, double _t51, double _t62) {
        double _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return makeRotationLookAlong_rh_unsafe_mulAdd_s3a18c539_2(dest, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_s3a18c539_2(long dest, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t51);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t71);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t70);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t50);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dir, up);
        DoubleQuatOpsKernelsSegment.makeRotationLookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L);
        return dest;
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dir, up);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dir, up);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        double _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_unsafe_fma_s61209f8a_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_s61209f8a_1(long dest, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return makeRotationLookAlong_lh_unsafe_fma_s61209f8a_2(dest, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_s61209f8a_2(long dest, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1) {
        return makeRotationLookAlong_lh_unsafe_fma_s61209f8a_3(dest, _t11, _t12, _t38, _t41, _t48, _t49, _t60, _t65, _t66, _t68, _t69, _t71, _t72, _t73, _t74, _sp0, _sp1, 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_s61209f8a_3(long dest, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t49);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t69);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t68);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t48);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t19 = ((_t18) * (_t12) + (_upx));
        double _t20 = ((_t18) * (_t13) + (_upy));
        double _t21 = ((_t18) * (_t11) + (_upz));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return makeRotationLookAlong_lh_unsafe_mulAdd_sbd37ae05_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_sbd37ae05_1(long dest, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return makeRotationLookAlong_lh_unsafe_mulAdd_sbd37ae05_2(dest, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_sbd37ae05_2(long dest, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t49);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t69);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t68);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t48);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dir, up);
        DoubleQuatOpsKernelsSegment.makeRotationLookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L);
        return dest;
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, long dir, long up) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dir, up);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dir, up);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, long dir, long up) {
        makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_500(dest, dir, up);
        return dest;
    }

    /** Part of {@code makeRotationLookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_500(long dest, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_500_sefcef3af_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_500}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_500_sefcef3af_1(long dest, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_500_sefcef3af_2(dest, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_500}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_500_sefcef3af_2(long dest, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1) {
        makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_500_sefcef3af_3(dest, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, _t67, _t68, _t70, _t71, _t73, _t74, _t75, _t76, _sp0, _sp1, 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_500}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_sf11f4e7c_500_sefcef3af_3(long dest, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t51);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t71);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t70);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t50);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t71);
                }
            }
        }
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, long dir, long up) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t21 = ((_t20) * (_t13) + (_upy));
        double _t22 = ((_t20) * (_t12) + (_upx));
        double _t23 = ((_t20) * (_t11) + (_upz));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return makeRotationLookAlong_rh_unsafe_mulAdd_s24c40fcf_1(dest, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_s24c40fcf_1(long dest, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (5.048709793414476E-29) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return makeRotationLookAlong_rh_unsafe_mulAdd_s24c40fcf_2(dest, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_s24c40fcf_2(long dest, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2) {
        return makeRotationLookAlong_rh_unsafe_mulAdd_s24c40fcf_3(dest, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, _t67, _t68, _t70, _t71, _t73, _t74, _t75, _t76, _sp0, _sp1, _sp2, 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_s24c40fcf_3(long dest, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t51);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t71);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t70);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t50);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        DoubleQuatOpsKernelsSegment.makeRotationLookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, upX);
        double _t20 = java.lang.Math.fma(_t18, _t13, upY);
        double _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_unsafe_fma_s4464a5b7_1(dest, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_s4464a5b7_1(long dest, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t48, double _t49) {
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        double _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38));
        return makeRotationLookAlong_lh_unsafe_fma_s4464a5b7_2(dest, _t11, _t12, _t38, _t41, _t48, _t49, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_s4464a5b7_2(long dest, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t49);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t69);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t68);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t48);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t19 = ((_t18) * (_t12) + (upX));
        double _t20 = ((_t18) * (_t13) + (upY));
        double _t21 = ((_t18) * (_t11) + (upZ));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        double _t34 = (1.0 / java.lang.Math.sqrt(_t33));
        double _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        return makeRotationLookAlong_lh_unsafe_mulAdd_s111723f4_1(dest, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0 + _t38, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_s111723f4_1(long dest, double _t11, double _t12, double _t13, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t48, double _t49, double _t60) {
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return makeRotationLookAlong_lh_unsafe_mulAdd_s111723f4_2(dest, _t11, _t12, _t38, _t41, _t48, _t49, _t60, ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_s111723f4_2(long dest, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t49);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t69);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t68);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t48);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        DoubleQuatOpsKernelsSegment.makeRotationLookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        makeRotationLookAlong_rh_unsafe_fma_se775cc5d_500(dest, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    /** Part of {@code makeRotationLookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_se775cc5d_500(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, upY);
        double _t22 = java.lang.Math.fma(_t20, _t12, upX);
        double _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        makeRotationLookAlong_rh_unsafe_fma_se775cc5d_500_sfe79d122_1(dest, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_fma_se775cc5d_500}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_se775cc5d_500_sfe79d122_1(long dest, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t50) {
        double _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        double _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        double _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41));
        makeRotationLookAlong_rh_unsafe_fma_se775cc5d_500_sfe79d122_2(dest, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_unsafe_fma_se775cc5d_500}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_se775cc5d_500_sfe79d122_2(long dest, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t51);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t71);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t70);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t50);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t71);
                }
            }
        }
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        double _t21 = ((_t20) * (_t13) + (upY));
        double _t22 = ((_t20) * (_t12) + (upX));
        double _t23 = ((_t20) * (_t11) + (upZ));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        return makeRotationLookAlong_rh_unsafe_mulAdd_s9d84302a_1(dest, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0 + _t41, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_s9d84302a_1(long dest, double _t11, double _t12, double _t13, double _t14, double _t15, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t50, double _t51, double _t62) {
        double _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        double _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return makeRotationLookAlong_rh_unsafe_mulAdd_s9d84302a_2(dest, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, _t74, _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_s9d84302a_2(long dest, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t51);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t71);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t70);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t50);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_lh(long dest, long dir, long up, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(dest, dir, up, epsilon);
        DoubleQuatOpsKernelsSegment.makeRotationLookAlong_lh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L, epsilon);
        return dest;
    }

    public static long makeRotationLookAlong_lh_unsafe(long dest, long dir, long up, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_unsafe_fma(dest, dir, up, epsilon);
        return makeRotationLookAlong_lh_unsafe_mulAdd(dest, dir, up, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma(long dest, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        double _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        double _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        double _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        double _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        double _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_unsafe_fma_sd8be2141_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_sd8be2141_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        double _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0 - _t38 - _t11));
        return makeRotationLookAlong_lh_unsafe_fma_sd8be2141_2(dest, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0 + _t11 - _t38)), 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_sd8be2141_2(long dest, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1) {
        return makeRotationLookAlong_lh_unsafe_fma_sd8be2141_3(dest, _t11, _t12, _t38, _t41, _t48, _t49, _t60, _t65, _t66, _t68, _t69, _t71, _t72, _t73, _t74, _sp0, _sp1, 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_lh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_fma_sd8be2141_3(long dest, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t49);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t69);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t68);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t48);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd(long dest, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t19 = ((_t18) * (_t12) + (_upx));
        double _t20 = ((_t18) * (_t13) + (_upy));
        double _t21 = ((_t18) * (_t11) + (_upz));
        double _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        double _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        double _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        double _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return makeRotationLookAlong_lh_unsafe_mulAdd_s8588a676_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0 / java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_s8588a676_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t28, double _t29, double _t30, double _t33, double _t34) {
        double _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0;
            _t39 = 0.0;
            _t40 = 0.0;
        }
        double _t41 = -_t39;
        double _t42 = -_t40;
        double _t43 = -_t38;
        double _t44 = 1.0 + _t38;
        double _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        double _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        double _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0 - _t38 - _t11))));
        double _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0 + _t11 - _t38))));
        return makeRotationLookAlong_lh_unsafe_mulAdd_s8588a676_2(dest, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5 * (1.0 / java.lang.Math.sqrt(_t71)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_lh_unsafe_mulAdd_s8588a676_2(long dest, double _t11, double _t12, double _t38, double _t41, double _t48, double _t49, double _t60, double _t65, double _t66, double _t68, double _t69, double _t71, double _t72, double _t73, double _t74, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t65);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t49);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t69);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t72));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t68);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t48);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t73));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t49);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t48);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t66);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t74));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static long makeRotationLookAlong_rh(long dest, long dir, long up, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return DoubleQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(dest, dir, up, epsilon);
        DoubleQuatOpsKernelsSegment.makeRotationLookAlong_rh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L, epsilon);
        return dest;
    }

    public static long makeRotationLookAlong_rh_unsafe(long dest, long dir, long up, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_unsafe_fma(dest, dir, up, epsilon);
        return makeRotationLookAlong_rh_unsafe_mulAdd(dest, dir, up, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_fma(long dest, long dir, long up, double epsilon) {
        makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_500(dest, dir, up, epsilon);
        return dest;
    }

    /** Part of {@code makeRotationLookAlong_rh_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_500(long dest, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        double _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        double _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        double _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        double _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        double _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        double _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        double _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_500_s8924f784_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_500}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_500_s8924f784_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        double _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0 - _t41 + _t11));
        makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_500_s8924f784_2(dest, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0 - _t11 - _t41)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_500}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_500_s8924f784_2(long dest, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1) {
        makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_500_s8924f784_3(dest, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, _t67, _t68, _t70, _t71, _t73, _t74, _t75, _t76, _sp0, _sp1, 0.5 * (1.0 / java.lang.Math.sqrt(_t76)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_500}, split to fit the inline budget; reached only through it. */
    private static void makeRotationLookAlong_rh_unsafe_fma_s6ae5b88f_500_s8924f784_3(long dest, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t51);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t71);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t70);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t50);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t71);
                }
            }
        }
    }

    /** {@code makeRotationLookAlong_rh_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd(long dest, long dir, long up, double epsilon) {
        double _dirx = UnsafeOpsHolder.U.getDouble(dir);
        double _diry = UnsafeOpsHolder.U.getDouble(dir + 8L);
        double _dirz = UnsafeOpsHolder.U.getDouble(dir + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(up);
        double _upy = UnsafeOpsHolder.U.getDouble(up + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(up + 16L);
        double _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        double _t21 = ((_t20) * (_t13) + (_upy));
        double _t22 = ((_t20) * (_t12) + (_upx));
        double _t23 = ((_t20) * (_t11) + (_upz));
        double _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        double _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        double _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        double _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return makeRotationLookAlong_rh_unsafe_mulAdd_sa724bb6c_1(dest, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_sa724bb6c_1(long dest, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t14, double _t15, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (2.2250738585072014E-308))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t43 = -_t41;
        double _t44 = -_t40;
        double _t45 = -_t42;
        double _t46 = 1.0 + _t41;
        double _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        double _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0 - _t41 + _t11))));
        double _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0 - _t11 - _t41))));
        return makeRotationLookAlong_rh_unsafe_mulAdd_sa724bb6c_2(dest, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), _t75, _t76, 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_sa724bb6c_2(long dest, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2) {
        return makeRotationLookAlong_rh_unsafe_mulAdd_sa724bb6c_3(dest, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, _t67, _t68, _t70, _t71, _t73, _t74, _t75, _t76, _sp0, _sp1, _sp2, 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 4 of {@code makeRotationLookAlong_rh_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationLookAlong_rh_unsafe_mulAdd_sa724bb6c_3(long dest, double _t11, double _t12, double _t15, double _t40, double _t41, double _t43, double _t50, double _t51, double _t62, double _t67, double _t68, double _t70, double _t71, double _t73, double _t74, double _t75, double _t76, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0) {
            UnsafeOpsHolder.U.putDouble(dest, _sp0 * _t67);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _t51);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _sp0 * _t71);
            UnsafeOpsHolder.U.putDouble(dest + 24L, 0.5 * java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                UnsafeOpsHolder.U.putDouble(dest, 0.5 * java.lang.Math.sqrt(_t74));
                UnsafeOpsHolder.U.putDouble(dest + 8L, _sp3 * _t70);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _sp3 * _t50);
                UnsafeOpsHolder.U.putDouble(dest + 24L, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    UnsafeOpsHolder.U.putDouble(dest, _sp1 * _t70);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, 0.5 * java.lang.Math.sqrt(_t75));
                    UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _t51);
                } else {
                    UnsafeOpsHolder.U.putDouble(dest, _sp2 * _t50);
                    UnsafeOpsHolder.U.putDouble(dest + 8L, _sp2 * _t68);
                    UnsafeOpsHolder.U.putDouble(dest + 16L, 0.5 * java.lang.Math.sqrt(_t76));
                    UnsafeOpsHolder.U.putDouble(dest + 24L, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static long makeRotationTo_unsafe(long dest, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_unsafe_fma(dest, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return makeRotationTo_unsafe_mulAdd(dest, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code makeRotationTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_fma(long dest, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_unsafe_fma_s452a61cf_1(dest, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_fma_s452a61cf_1(long dest, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > 1.0E-13) {
            UnsafeOpsHolder.U.putDouble(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t17 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t16 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                UnsafeOpsHolder.U.putDouble(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putDouble(dest + 8L, _t30 * _t19);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _t30 * _t18);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            } else {
                UnsafeOpsHolder.U.putDouble(dest, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd(long dest, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t16 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_unsafe_mulAdd_sab5f5842_1(dest, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd_sab5f5842_1(long dest, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > 1.0E-13) {
            UnsafeOpsHolder.U.putDouble(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t17 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t16 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                UnsafeOpsHolder.U.putDouble(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putDouble(dest + 8L, _t30 * _t19);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _t30 * _t18);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            } else {
                UnsafeOpsHolder.U.putDouble(dest, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
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
        double _fromDirx = UnsafeOpsHolder.U.getDouble(fromDir);
        double _fromDiry = UnsafeOpsHolder.U.getDouble(fromDir + 8L);
        double _fromDirz = UnsafeOpsHolder.U.getDouble(fromDir + 16L);
        double _toDirx = UnsafeOpsHolder.U.getDouble(toDir);
        double _toDiry = UnsafeOpsHolder.U.getDouble(toDir + 8L);
        double _toDirz = UnsafeOpsHolder.U.getDouble(toDir + 16L);
        double _t4 = _fromDirz + _toDirz;
        double _t5 = _fromDirx + _toDirx;
        double _t6 = _fromDiry + _toDiry;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        double _t15 = java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        double _t16 = java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx);
        double _t17 = java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_unsafe_fma_s654afdc5_1(dest, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_fma_s654afdc5_1(long dest, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > 1.0E-13) {
            UnsafeOpsHolder.U.putDouble(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t17 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t16 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                UnsafeOpsHolder.U.putDouble(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putDouble(dest + 8L, _t30 * _t19);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _t30 * _t18);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            } else {
                UnsafeOpsHolder.U.putDouble(dest, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd(long dest, long fromDir, long toDir) {
        double _fromDirx = UnsafeOpsHolder.U.getDouble(fromDir);
        double _fromDiry = UnsafeOpsHolder.U.getDouble(fromDir + 8L);
        double _fromDirz = UnsafeOpsHolder.U.getDouble(fromDir + 16L);
        double _toDirx = UnsafeOpsHolder.U.getDouble(toDir);
        double _toDiry = UnsafeOpsHolder.U.getDouble(toDir + 8L);
        double _toDirz = UnsafeOpsHolder.U.getDouble(toDir + 16L);
        double _t4 = _fromDirz + _toDirz;
        double _t5 = _fromDirx + _toDirx;
        double _t6 = _fromDiry + _toDiry;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        double _t15 = ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry));
        double _t16 = ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx));
        double _t17 = ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_unsafe_mulAdd_sbfdcd4f0_1(dest, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd_sbfdcd4f0_1(long dest, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > 1.0E-13) {
            UnsafeOpsHolder.U.putDouble(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t17 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t16 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                UnsafeOpsHolder.U.putDouble(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putDouble(dest + 8L, _t30 * _t19);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _t30 * _t18);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            } else {
                UnsafeOpsHolder.U.putDouble(dest, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            }
        }
        return dest;
    }

    public static long makeRotationTo_unsafe(long dest, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_unsafe_fma(dest, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return makeRotationTo_unsafe_mulAdd(dest, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code makeRotationTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_fma(long dest, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_unsafe_fma_sfcc38d6c_1(dest, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_fma_sfcc38d6c_1(long dest, double epsilon, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > epsilon) {
            UnsafeOpsHolder.U.putDouble(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t17 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t16 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                UnsafeOpsHolder.U.putDouble(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putDouble(dest + 8L, _t30 * _t19);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _t30 * _t18);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            } else {
                UnsafeOpsHolder.U.putDouble(dest, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd(long dest, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t16 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_unsafe_mulAdd_sf92a3789_1(dest, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd_sf92a3789_1(long dest, double epsilon, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > epsilon) {
            UnsafeOpsHolder.U.putDouble(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t17 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t16 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                UnsafeOpsHolder.U.putDouble(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putDouble(dest + 8L, _t30 * _t19);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _t30 * _t18);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            } else {
                UnsafeOpsHolder.U.putDouble(dest, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            }
        }
        return dest;
    }

    public static long makeRotationTo_unsafe(long dest, long fromDir, long toDir, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_unsafe_fma(dest, fromDir, toDir, epsilon);
        return makeRotationTo_unsafe_mulAdd(dest, fromDir, toDir, epsilon);
    }

    /** {@code makeRotationTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_fma(long dest, long fromDir, long toDir, double epsilon) {
        double _fromDirx = UnsafeOpsHolder.U.getDouble(fromDir);
        double _fromDiry = UnsafeOpsHolder.U.getDouble(fromDir + 8L);
        double _fromDirz = UnsafeOpsHolder.U.getDouble(fromDir + 16L);
        double _toDirx = UnsafeOpsHolder.U.getDouble(toDir);
        double _toDiry = UnsafeOpsHolder.U.getDouble(toDir + 8L);
        double _toDirz = UnsafeOpsHolder.U.getDouble(toDir + 16L);
        double _t4 = _fromDirz + _toDirz;
        double _t5 = _fromDirx + _toDirx;
        double _t6 = _fromDiry + _toDiry;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        double _t15 = java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        double _t16 = java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx);
        double _t17 = java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_unsafe_fma_s53dcedb6_1(dest, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_fma_s53dcedb6_1(long dest, double epsilon, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > epsilon) {
            UnsafeOpsHolder.U.putDouble(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t17 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t16 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                UnsafeOpsHolder.U.putDouble(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putDouble(dest + 8L, _t30 * _t19);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _t30 * _t18);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            } else {
                UnsafeOpsHolder.U.putDouble(dest, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd(long dest, long fromDir, long toDir, double epsilon) {
        double _fromDirx = UnsafeOpsHolder.U.getDouble(fromDir);
        double _fromDiry = UnsafeOpsHolder.U.getDouble(fromDir + 8L);
        double _fromDirz = UnsafeOpsHolder.U.getDouble(fromDir + 16L);
        double _toDirx = UnsafeOpsHolder.U.getDouble(toDir);
        double _toDiry = UnsafeOpsHolder.U.getDouble(toDir + 8L);
        double _toDirz = UnsafeOpsHolder.U.getDouble(toDir + 16L);
        double _t4 = _fromDirz + _toDirz;
        double _t5 = _fromDirx + _toDirx;
        double _t6 = _fromDiry + _toDiry;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        double _t15 = ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry));
        double _t16 = ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx));
        double _t17 = ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_unsafe_mulAdd_sa93d8893_1(dest, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long makeRotationTo_unsafe_mulAdd_sa93d8893_1(long dest, double epsilon, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > epsilon) {
            UnsafeOpsHolder.U.putDouble(dest, _t15 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 8L, _t17 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 16L, _t16 * _t32);
            UnsafeOpsHolder.U.putDouble(dest + 24L, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                UnsafeOpsHolder.U.putDouble(dest, _t30 * _t13);
                UnsafeOpsHolder.U.putDouble(dest + 8L, _t30 * _t19);
                UnsafeOpsHolder.U.putDouble(dest + 16L, _t30 * _t18);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            } else {
                UnsafeOpsHolder.U.putDouble(dest, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
                UnsafeOpsHolder.U.putDouble(dest + 24L, 0.0);
            }
        }
        return dest;
    }

    public static long makeRotationX_unsafe(long dest, double angle) {
        double _t0 = 0.5 * angle;
        UnsafeOpsHolder.U.putDouble(dest, Math.sinWithCos(_t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cosWithSin(_t0));
        return dest;
    }

    public static long makeRotationXYZ_unsafe(long dest, double angleX, double angleY, double angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXYZ_unsafe_mulAdd(dest, angleX, angleY, angleZ);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t10, _t5, _t9 * -_t8));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        return dest;
    }

    /** {@code makeRotationXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationXYZ_unsafe_mulAdd(long dest, double angleX, double angleY, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        UnsafeOpsHolder.U.putDouble(dest, ((_t9) * (_t5) + (_t10 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t10) * (_t5) - (_t9 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t11) * (_t5) + (_t12 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t12) * (_t5) - (_t11 * _t8)));
        return dest;
    }

    public static long makeRotationXZY_unsafe(long dest, double angleX, double angleZ, double angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXZY_unsafe_mulAdd(dest, angleX, angleZ, angleY);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t9, _t8, _t10 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        return dest;
    }

    /** {@code makeRotationXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationXZY_unsafe_mulAdd(long dest, double angleX, double angleZ, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        UnsafeOpsHolder.U.putDouble(dest, ((_t9) * (_t5) - (_t10 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t11) * (_t8) - (_t12 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t9) * (_t8) + (_t10 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t12) * (_t8) + (_t11 * _t5)));
        return dest;
    }

    public static long makeRotationY_unsafe(long dest, double angle) {
        double _t0 = 0.5 * angle;
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.sinWithCos(_t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cosWithSin(_t0));
        return dest;
    }

    public static long makeRotationYXZ_unsafe(long dest, double angleY, double angleX, double angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYXZ_unsafe_mulAdd(dest, angleY, angleX, angleZ);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t10, _t5, -(_t9 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        return dest;
    }

    /** {@code makeRotationYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationYXZ_unsafe_mulAdd(long dest, double angleY, double angleX, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t4;
        double _t12 = _t3 * _t6;
        UnsafeOpsHolder.U.putDouble(dest, ((_t9) * (_t5) + (_t10 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t10) * (_t5) - (_t9 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t11) * (_t8) - (_t12 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t12) * (_t8) + (_t11 * _t5)));
        return dest;
    }

    public static long makeRotationYZX_unsafe(long dest, double angleY, double angleZ, double angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYZX_unsafe_mulAdd(dest, angleY, angleZ, angleX);
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t10, _t5, _t9 * -_t8));
        return dest;
    }

    /** {@code makeRotationYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationYZX_unsafe_mulAdd(long dest, double angleY, double angleZ, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        UnsafeOpsHolder.U.putDouble(dest, ((_t9) * (_t5) + (_t10 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t11) * (_t5) + (_t12 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t12) * (_t5) - (_t11 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t10) * (_t5) - (_t9 * _t8)));
        return dest;
    }

    public static long makeRotationZ_unsafe(long dest, double angle) {
        double _t0 = 0.5 * angle;
        UnsafeOpsHolder.U.putDouble(dest, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.sinWithCos(_t0));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.cosWithSin(_t0));
        return dest;
    }

    public static long makeRotationZXY_unsafe(long dest, double angleZ, double angleX, double angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZXY_unsafe_mulAdd(dest, angleZ, angleX, angleY);
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t9, _t5, _t10 * -_t8));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t9, _t8, _t10 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        return dest;
    }

    /** {@code makeRotationZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationZXY_unsafe_mulAdd(long dest, double angleZ, double angleX, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t1);
        double _t7 = Math.cosWithSin(_t0);
        double _t8 = Math.sinWithCos(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t6;
        double _t12 = _t7 * _t4;
        UnsafeOpsHolder.U.putDouble(dest, ((_t9) * (_t5) - (_t10 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t11) * (_t5) + (_t12 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t9) * (_t8) + (_t10 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t12) * (_t5) - (_t11 * _t8)));
        return dest;
    }

    public static long makeRotationZYX_unsafe(long dest, double angleZ, double angleY, double angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZYX_unsafe_mulAdd(dest, angleZ, angleY, angleX);
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_t11, _t8, _t12 * _t5));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_t12, _t8, -(_t11 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_t10, _t5, _t9 * _t8));
        return dest;
    }

    /** {@code makeRotationZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long makeRotationZYX_unsafe_mulAdd(long dest, double angleZ, double angleY, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        UnsafeOpsHolder.U.putDouble(dest, ((_t9) * (_t5) - (_t10 * _t8)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_t11) * (_t8) + (_t12 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_t12) * (_t8) - (_t11 * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_t10) * (_t5) + (_t9 * _t8)));
        return dest;
    }

    public static long preRotateX_unsafe(long dest, long src, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateX_unsafe_mulAdd(dest, src, angle);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t1, _selfw * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t1, -(_selfz * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfy, _t2, _selfz * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t1, -(_selfx * _t2)));
        return dest;
    }

    /** {@code preRotateX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateX_unsafe_mulAdd(long dest, long src, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t1) + (_selfw * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t1) - (_selfz * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfy) * (_t2) + (_selfz * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t1) - (_selfx * _t2)));
        return dest;
    }

    public static long preRotateY_unsafe(long dest, long src, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateY_unsafe_mulAdd(dest, src, angle);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t1, _selfz * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t1, _selfw * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t1, -(_selfx * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t1, -(_selfy * _t2)));
        return dest;
    }

    /** {@code preRotateY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateY_unsafe_mulAdd(long dest, long src, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t1) + (_selfz * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t1) + (_selfw * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t1) - (_selfx * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t1) - (_selfy * _t2)));
        return dest;
    }

    public static long preRotateZ_unsafe(long dest, long src, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateZ_unsafe_mulAdd(dest, src, angle);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t1, -(_selfy * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfx, _t2, _selfy * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t1, _selfw * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t1, -(_selfz * _t2)));
        return dest;
    }

    /** {@code preRotateZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preRotateZ_unsafe_mulAdd(long dest, long src, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t1) - (_selfy * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfx) * (_t2) + (_selfy * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t1) + (_selfw * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t1) - (_selfz * _t2)));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_mulAdd(dest, src, angle, axisX, axisY, axisZ);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisZ * _t2;
        double _t5 = axisY * _t2;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t1, _selfw * _t3) + java.lang.Math.fma(_selfy, _t4, -(_selfz * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t1, _selfz * _t3) + java.lang.Math.fma(_selfw, _t5, -_selfx * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t5, _selfw * _t4) + java.lang.Math.fma(_selfz, _t1, -(_selfy * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t1, -_selfx * _t3) - java.lang.Math.fma(_selfy, _t5, _selfz * _t4));
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = axisX * _t2;
        double _t4 = axisZ * _t2;
        double _t5 = axisY * _t2;
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t1) + (_selfw * _t3)) + ((_selfy) * (_t4) - (_selfz * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t1) + (_selfz * _t3)) + ((_selfw) * (_t5) - (_selfx * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t5) + (_selfw * _t4)) + ((_selfz) * (_t1) - (_selfy * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t1) - (_selfx * _t3)) - ((_selfy) * (_t5) + (_selfz * _t4)));
        return dest;
    }

    public static long rotateAxis_unsafe(long dest, long src, long axis, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_unsafe_mulAdd(dest, src, axis, angle);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = UnsafeOpsHolder.U.getDouble(axis) * _t2;
        double _t4 = UnsafeOpsHolder.U.getDouble(axis + 16L) * _t2;
        double _t5 = UnsafeOpsHolder.U.getDouble(axis + 8L) * _t2;
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t1, _selfw * _t3) + java.lang.Math.fma(_selfy, _t4, -(_selfz * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t1, _selfz * _t3) + java.lang.Math.fma(_selfw, _t5, -_selfx * _t4));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t5, _selfw * _t4) + java.lang.Math.fma(_selfz, _t1, -(_selfy * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t1, -_selfx * _t3) - java.lang.Math.fma(_selfy, _t5, _selfz * _t4));
        return dest;
    }

    /** {@code rotateAxis_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateAxis_unsafe_mulAdd(long dest, long src, long axis, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        double _t3 = UnsafeOpsHolder.U.getDouble(axis) * _t2;
        double _t4 = UnsafeOpsHolder.U.getDouble(axis + 16L) * _t2;
        double _t5 = UnsafeOpsHolder.U.getDouble(axis + 8L) * _t2;
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t1) + (_selfw * _t3)) + ((_selfy) * (_t4) - (_selfz * _t5)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t1) + (_selfz * _t3)) + ((_selfw) * (_t5) - (_selfx * _t4)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t5) + (_selfw * _t4)) + ((_selfz) * (_t1) - (_selfy * _t3)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t1) - (_selfx * _t3)) - ((_selfy) * (_t5) + (_selfz * _t4)));
        return dest;
    }

    public static long rotateTo_unsafe(long dest, long src, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_unsafe_fma(dest, src, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return rotateTo_unsafe_mulAdd(dest, src, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code rotateTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_fma(long dest, long src, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_unsafe_fma_s5f452cc6_1(dest, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_fma_s5f452cc6_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > 1.0E-13) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_mulAdd(long dest, long src, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_unsafe_mulAdd_saa165925_1(dest, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_mulAdd_saa165925_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > 1.0E-13) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static long rotateTo_unsafe(long dest, long src, long fromDir, long toDir) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_unsafe_fma(dest, src, fromDir, toDir);
        return rotateTo_unsafe_mulAdd(dest, src, fromDir, toDir);
    }

    /** {@code rotateTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_fma(long dest, long src, long fromDir, long toDir) {
        double _fromDirx = UnsafeOpsHolder.U.getDouble(fromDir);
        double _fromDiry = UnsafeOpsHolder.U.getDouble(fromDir + 8L);
        double _fromDirz = UnsafeOpsHolder.U.getDouble(fromDir + 16L);
        double _toDirx = UnsafeOpsHolder.U.getDouble(toDir);
        double _toDiry = UnsafeOpsHolder.U.getDouble(toDir + 8L);
        double _toDirz = UnsafeOpsHolder.U.getDouble(toDir + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = _fromDirz + _toDirz;
        double _t5 = _fromDirx + _toDirx;
        double _t6 = _fromDiry + _toDiry;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_unsafe_fma_sca439734_1(dest, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), _t23, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_fma_sca439734_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t23, double _t24, double _t29, double _t30) {
        double _t35 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
        double _t44, _t45, _t46, _t47;
        if (_t24 > 1.0E-13) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_mulAdd(long dest, long src, long fromDir, long toDir) {
        double _fromDirx = UnsafeOpsHolder.U.getDouble(fromDir);
        double _fromDiry = UnsafeOpsHolder.U.getDouble(fromDir + 8L);
        double _fromDirz = UnsafeOpsHolder.U.getDouble(fromDir + 16L);
        double _toDirx = UnsafeOpsHolder.U.getDouble(toDir);
        double _toDiry = UnsafeOpsHolder.U.getDouble(toDir + 8L);
        double _toDirz = UnsafeOpsHolder.U.getDouble(toDir + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = _fromDirz + _toDirz;
        double _t5 = _fromDirx + _toDirx;
        double _t6 = _fromDiry + _toDiry;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_unsafe_mulAdd_s9184fbb3_1(dest, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx)), ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry)), ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz)), _t23, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_mulAdd_s9184fbb3_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t23, double _t24, double _t29, double _t30) {
        double _t35 = (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
        double _t44, _t45, _t46, _t47;
        if (_t24 > 1.0E-13) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static long rotateTo_unsafe(long dest, long src, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_unsafe_fma(dest, src, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return rotateTo_unsafe_mulAdd(dest, src, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code rotateTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_fma(long dest, long src, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        double _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_unsafe_fma_s9a7f33bd_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_fma_s9a7f33bd_1(long dest, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_mulAdd(long dest, long src, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ, double epsilon) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        double _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        double _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_unsafe_mulAdd_sca04fc96_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_mulAdd_sca04fc96_1(long dest, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static long rotateTo_unsafe(long dest, long src, long fromDir, long toDir, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_unsafe_fma(dest, src, fromDir, toDir, epsilon);
        return rotateTo_unsafe_mulAdd(dest, src, fromDir, toDir, epsilon);
    }

    /** {@code rotateTo_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_fma(long dest, long src, long fromDir, long toDir, double epsilon) {
        double _fromDirx = UnsafeOpsHolder.U.getDouble(fromDir);
        double _fromDiry = UnsafeOpsHolder.U.getDouble(fromDir + 8L);
        double _fromDirz = UnsafeOpsHolder.U.getDouble(fromDir + 16L);
        double _toDirx = UnsafeOpsHolder.U.getDouble(toDir);
        double _toDiry = UnsafeOpsHolder.U.getDouble(toDir + 8L);
        double _toDirz = UnsafeOpsHolder.U.getDouble(toDir + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = _fromDirz + _toDirz;
        double _t5 = _fromDirx + _toDirx;
        double _t6 = _fromDiry + _toDiry;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        double _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_unsafe_fma_sd0c0dd17_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), _t23, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_fma_sd0c0dd17_1(long dest, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t23, double _t24, double _t29, double _t30) {
        double _t35 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(0.25, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateTo_unsafe_mulAdd(long dest, long src, long fromDir, long toDir, double epsilon) {
        double _fromDirx = UnsafeOpsHolder.U.getDouble(fromDir);
        double _fromDiry = UnsafeOpsHolder.U.getDouble(fromDir + 8L);
        double _fromDirz = UnsafeOpsHolder.U.getDouble(fromDir + 16L);
        double _toDirx = UnsafeOpsHolder.U.getDouble(toDir);
        double _toDiry = UnsafeOpsHolder.U.getDouble(toDir + 8L);
        double _toDirz = UnsafeOpsHolder.U.getDouble(toDir + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t4 = _fromDirz + _toDirz;
        double _t5 = _fromDirx + _toDirx;
        double _t6 = _fromDiry + _toDiry;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        double _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        double _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_unsafe_mulAdd_sa19bca0_1(dest, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx)), ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry)), ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz)), _t23, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateTo_unsafe_mulAdd_sa19bca0_1(long dest, double epsilon, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t23, double _t24, double _t29, double _t30) {
        double _t35 = (1.0 / java.lang.Math.sqrt(((0.25) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
        double _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static long rotateX_unsafe(long dest, long src, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateX_unsafe_mulAdd(dest, src, angle);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t1, _selfw * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t1, _selfz * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t1, -(_selfy * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t1, -(_selfx * _t2)));
        return dest;
    }

    /** {@code rotateX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateX_unsafe_mulAdd(long dest, long src, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t1) + (_selfw * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t1) + (_selfz * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t1) - (_selfy * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t1) - (_selfx * _t2)));
        return dest;
    }

    public static long rotateXYZ_unsafe(long dest, long src, double angleX, double angleY, double angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXYZ_unsafe_fma(dest, src, angleX, angleY, angleZ);
        return rotateXYZ_unsafe_mulAdd(dest, src, angleX, angleY, angleZ);
    }

    /** {@code rotateXYZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXYZ_unsafe_fma(long dest, long src, double angleX, double angleY, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        double _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        double _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        double _t22 = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t21, _selfw * _t19) + java.lang.Math.fma(_selfy, _t20, -(_selfz * _t22)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t21, _selfz * _t19) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t20));
        return rotateXYZ_unsafe_fma_sd7cb3c3a_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_fma_sd7cb3c3a_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t22, _selfw * _t20) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t21, -_selfx * _t19) - java.lang.Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    /** {@code rotateXYZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXYZ_unsafe_mulAdd(long dest, long src, double angleX, double angleY, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = ((_t11) * (_t5) + (_t12 * _t8));
        double _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        double _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        double _t22 = ((_t12) * (_t5) - (_t11 * _t8));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t21) + (_selfw * _t19)) + ((_selfy) * (_t20) - (_selfz * _t22)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t21) + (_selfz * _t19)) + ((_selfw) * (_t22) - (_selfx * _t20)));
        return rotateXYZ_unsafe_mulAdd_s47b2086b_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateXYZ_unsafe_mulAdd_s47b2086b_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t22) + (_selfw * _t20)) + ((_selfz) * (_t21) - (_selfy * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t21) - (_selfx * _t19)) - ((_selfy) * (_t22) + (_selfz * _t20)));
        return dest;
    }

    public static long rotateXZY_unsafe(long dest, long src, double angleX, double angleZ, double angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXZY_unsafe_fma(dest, src, angleX, angleZ, angleY);
        return rotateXZY_unsafe_mulAdd(dest, src, angleX, angleZ, angleY);
    }

    /** {@code rotateXZY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXZY_unsafe_fma(long dest, long src, double angleX, double angleZ, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        double _t20 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        double _t21 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        double _t22 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t19, _selfw * _t21) + java.lang.Math.fma(_selfy, _t20, -(_selfz * _t22)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t19, _selfz * _t21) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t20));
        return rotateXZY_unsafe_fma_sa958745e_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_fma_sa958745e_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t22, _selfw * _t20) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t19, -_selfx * _t21) - java.lang.Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    /** {@code rotateXZY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateXZY_unsafe_mulAdd(long dest, long src, double angleX, double angleZ, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        double _t20 = ((_t11) * (_t5) + (_t12 * _t8));
        double _t21 = ((_t11) * (_t8) - (_t12 * _t5));
        double _t22 = ((_t10) * (_t5) - (_t9 * _t8));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t19) + (_selfw * _t21)) + ((_selfy) * (_t20) - (_selfz * _t22)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t19) + (_selfz * _t21)) + ((_selfw) * (_t22) - (_selfx * _t20)));
        return rotateXZY_unsafe_mulAdd_sf480cfa7_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateXZY_unsafe_mulAdd_sf480cfa7_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t22) + (_selfw * _t20)) + ((_selfz) * (_t19) - (_selfy * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t19) - (_selfx * _t21)) - ((_selfy) * (_t22) + (_selfz * _t20)));
        return dest;
    }

    public static long rotateY_unsafe(long dest, long src, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateY_unsafe_mulAdd(dest, src, angle);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t1, -(_selfz * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t1, _selfw * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t2, _selfz * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t1, -(_selfy * _t2)));
        return dest;
    }

    /** {@code rotateY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateY_unsafe_mulAdd(long dest, long src, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t1) - (_selfz * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t1) + (_selfw * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t2) + (_selfz * _t1)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t1) - (_selfy * _t2)));
        return dest;
    }

    public static long rotateYXZ_unsafe(long dest, long src, double angleY, double angleX, double angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYXZ_unsafe_fma(dest, src, angleY, angleX, angleZ);
        return rotateYXZ_unsafe_mulAdd(dest, src, angleY, angleX, angleZ);
    }

    /** {@code rotateYXZ_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYXZ_unsafe_fma(long dest, long src, double angleY, double angleX, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        double _t20 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        double _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        double _t22 = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t19, _selfw * _t20) + java.lang.Math.fma(_selfy, _t21, -(_selfz * _t22)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t19, _selfz * _t20) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t21));
        return rotateYXZ_unsafe_fma_s9e7c9846_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_fma_s9e7c9846_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t22, _selfw * _t21) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t20)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t19, -_selfx * _t20) - java.lang.Math.fma(_selfy, _t22, _selfz * _t21));
        return dest;
    }

    /** {@code rotateYXZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYXZ_unsafe_mulAdd(long dest, long src, double angleY, double angleX, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t3 * _t7;
        double _t12 = _t4 * _t6;
        double _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        double _t20 = ((_t11) * (_t8) + (_t12 * _t5));
        double _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        double _t22 = ((_t12) * (_t8) - (_t11 * _t5));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t19) + (_selfw * _t20)) + ((_selfy) * (_t21) - (_selfz * _t22)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t19) + (_selfz * _t20)) + ((_selfw) * (_t22) - (_selfx * _t21)));
        return rotateYXZ_unsafe_mulAdd_s88dbf03b_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateYXZ_unsafe_mulAdd_s88dbf03b_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t22) + (_selfw * _t21)) + ((_selfz) * (_t19) - (_selfy * _t20)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t19) - (_selfx * _t20)) - ((_selfy) * (_t22) + (_selfz * _t21)));
        return dest;
    }

    public static long rotateYZX_unsafe(long dest, long src, double angleY, double angleZ, double angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYZX_unsafe_fma(dest, src, angleY, angleZ, angleX);
        return rotateYZX_unsafe_mulAdd(dest, src, angleY, angleZ, angleX);
    }

    /** {@code rotateYZX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYZX_unsafe_fma(long dest, long src, double angleY, double angleZ, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t3;
        double _t12 = _t6 * _t4;
        double _t19 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        double _t20 = java.lang.Math.fma(_t12, _t5, _t11 * _t8);
        double _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        double _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t21, _selfw * _t19) + java.lang.Math.fma(_selfy, _t22, -(_selfz * _t20)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t21, _selfz * _t19) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t22));
        return rotateYZX_unsafe_fma_sed0e3746_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_fma_sed0e3746_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t20, _selfw * _t22) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t21, -_selfx * _t19) - java.lang.Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    /** {@code rotateYZX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateYZX_unsafe_mulAdd(long dest, long src, double angleY, double angleZ, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t7 * _t3;
        double _t12 = _t6 * _t4;
        double _t19 = ((_t10) * (_t5) + (_t9 * _t8));
        double _t20 = ((_t12) * (_t5) + (_t11 * _t8));
        double _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        double _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t21) + (_selfw * _t19)) + ((_selfy) * (_t22) - (_selfz * _t20)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t21) + (_selfz * _t19)) + ((_selfw) * (_t20) - (_selfx * _t22)));
        return rotateYZX_unsafe_mulAdd_s628a647f_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateYZX_unsafe_mulAdd_s628a647f_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t20) + (_selfw * _t22)) + ((_selfz) * (_t21) - (_selfy * _t19)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t21) - (_selfx * _t19)) - ((_selfy) * (_t20) + (_selfz * _t22)));
        return dest;
    }

    public static long rotateZ_unsafe(long dest, long src, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateZ_unsafe_mulAdd(dest, src, angle);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t1, _selfy * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t1, -(_selfx * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfz, _t1, _selfw * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t1, -(_selfz * _t2)));
        return dest;
    }

    /** {@code rotateZ_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZ_unsafe_mulAdd(long dest, long src, double angle) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = 0.5 * angle;
        double _t1 = Math.cosWithSin(_t0);
        double _t2 = Math.sinWithCos(_t0);
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t1) + (_selfy * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t1) - (_selfx * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfz) * (_t1) + (_selfw * _t2)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t1) - (_selfz * _t2)));
        return dest;
    }

    public static long rotateZXY_unsafe(long dest, long src, double angleZ, double angleX, double angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZXY_unsafe_fma(dest, src, angleZ, angleX, angleY);
        return rotateZXY_unsafe_mulAdd(dest, src, angleZ, angleX, angleY);
    }

    /** {@code rotateZXY_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZXY_unsafe_fma(long dest, long src, double angleZ, double angleX, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        double _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        double _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        double _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t21, _selfw * _t22) + java.lang.Math.fma(_selfy, _t19, -(_selfz * _t20)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t21, _selfz * _t22) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t19));
        return rotateZXY_unsafe_fma_s8382a436_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_fma_s8382a436_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t20, _selfw * _t19) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t22)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t21, -_selfx * _t22) - java.lang.Math.fma(_selfy, _t20, _selfz * _t19));
        return dest;
    }

    /** {@code rotateZXY_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZXY_unsafe_mulAdd(long dest, long src, double angleZ, double angleX, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.cosWithSin(_t0);
        double _t4 = Math.cosWithSin(_t1);
        double _t5 = Math.cosWithSin(_t2);
        double _t6 = Math.sinWithCos(_t0);
        double _t7 = Math.sinWithCos(_t1);
        double _t8 = Math.sinWithCos(_t2);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t6 * _t4;
        double _t12 = _t7 * _t3;
        double _t19 = ((_t11) * (_t8) + (_t12 * _t5));
        double _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        double _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        double _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t21) + (_selfw * _t22)) + ((_selfy) * (_t19) - (_selfz * _t20)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t21) + (_selfz * _t22)) + ((_selfw) * (_t20) - (_selfx * _t19)));
        return rotateZXY_unsafe_mulAdd_sef63e53b_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateZXY_unsafe_mulAdd_sef63e53b_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t20) + (_selfw * _t19)) + ((_selfz) * (_t21) - (_selfy * _t22)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t21) - (_selfx * _t22)) - ((_selfy) * (_t20) + (_selfz * _t19)));
        return dest;
    }

    public static long rotateZYX_unsafe(long dest, long src, double angleZ, double angleY, double angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZYX_unsafe_fma(dest, src, angleZ, angleY, angleX);
        return rotateZYX_unsafe_mulAdd(dest, src, angleZ, angleY, angleX);
    }

    /** {@code rotateZYX_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZYX_unsafe_fma(long dest, long src, double angleZ, double angleY, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t3 * _t7;
        double _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        double _t20 = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        double _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        double _t22 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfx, _t19, _selfw * _t21) + java.lang.Math.fma(_selfy, _t22, -(_selfz * _t20)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfy, _t19, _selfz * _t21) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t22));
        return rotateZYX_unsafe_fma_s1d3346aa_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_fma_s1d3346aa_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t20, _selfw * _t22) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, java.lang.Math.fma(_selfw, _t19, -_selfx * _t21) - java.lang.Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    /** {@code rotateZYX_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long rotateZYX_unsafe_mulAdd(long dest, long src, double angleZ, double angleY, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sinWithCos(_t0);
        double _t4 = Math.sinWithCos(_t1);
        double _t5 = Math.sinWithCos(_t2);
        double _t6 = Math.cosWithSin(_t0);
        double _t7 = Math.cosWithSin(_t1);
        double _t8 = Math.cosWithSin(_t2);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = _t3 * _t4;
        double _t10 = _t6 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t3 * _t7;
        double _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        double _t20 = ((_t12) * (_t8) + (_t11 * _t5));
        double _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        double _t22 = ((_t11) * (_t8) - (_t12 * _t5));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfx) * (_t19) + (_selfw * _t21)) + ((_selfy) * (_t22) - (_selfz * _t20)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfy) * (_t19) + (_selfz * _t21)) + ((_selfw) * (_t20) - (_selfx * _t22)));
        return rotateZYX_unsafe_mulAdd_s20ba1663_1(dest, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long rotateZYX_unsafe_mulAdd_s20ba1663_1(long dest, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t20) + (_selfw * _t22)) + ((_selfz) * (_t19) - (_selfy * _t21)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, ((_selfw) * (_t19) - (_selfx * _t21)) - ((_selfy) * (_t20) + (_selfz * _t22)));
        return dest;
    }

    public static long transform_unsafe(long dest, long src, double vX, double vY, double vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transform_unsafe_mulAdd(dest, src, vX, vY, vZ);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, vY, -(_selfy * vX));
        double _t10 = 2.0 * java.lang.Math.fma(_selfz, vX, -(_selfx * vZ));
        double _t11 = 2.0 * java.lang.Math.fma(_selfy, vZ, -(_selfz * vY));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfy, _t9, java.lang.Math.fma(-_selfz, _t10, java.lang.Math.fma(_selfw, _t11, vX))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfz, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, vY))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfy, _t11, java.lang.Math.fma(_selfw, _t9, vZ))));
        return dest;
    }

    /** {@code transform_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long transform_unsafe_mulAdd(long dest, long src, double vX, double vY, double vZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (vY) - (_selfy * vX));
        double _t10 = 2.0 * ((_selfz) * (vX) - (_selfx * vZ));
        double _t11 = 2.0 * ((_selfy) * (vZ) - (_selfz * vY));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfy) * (_t9) + (((((_selfw) * (_t11) + (vX))) - (_selfz) * (_t10)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfz) * (_t11) + (((((_selfw) * (_t10) + (vY))) - (_selfx) * (_t9)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (vZ))) - (_selfy) * (_t11)))));
        return dest;
    }

    public static long transform_unsafe(long dest, long src, long v) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transform_unsafe_mulAdd(dest, src, v);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _vy, -(_selfy * _vx));
        double _t10 = 2.0 * java.lang.Math.fma(_selfz, _vx, -(_selfx * _vz));
        double _t11 = 2.0 * java.lang.Math.fma(_selfy, _vz, -(_selfz * _vy));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfy, _t9, java.lang.Math.fma(-_selfz, _t10, java.lang.Math.fma(_selfw, _t11, _vx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfz, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, _vy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfy, _t11, java.lang.Math.fma(_selfw, _t9, _vz))));
        return dest;
    }

    /** {@code transform_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long transform_unsafe_mulAdd(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_vy) - (_selfy * _vx));
        double _t10 = 2.0 * ((_selfz) * (_vx) - (_selfx * _vz));
        double _t11 = 2.0 * ((_selfy) * (_vz) - (_selfz * _vy));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfy) * (_t9) + (((((_selfw) * (_t11) + (_vx))) - (_selfz) * (_t10)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfz) * (_t11) + (((((_selfw) * (_t10) + (_vy))) - (_selfx) * (_t9)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (_vz))) - (_selfy) * (_t11)))));
        return dest;
    }

    public static long transformInverse_unsafe(long dest, long src, double vX, double vY, double vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformInverse_unsafe_mulAdd(dest, src, vX, vY, vZ);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, vZ, -(_selfz * vX));
        double _t10 = 2.0 * java.lang.Math.fma(_selfy, vX, -(_selfx * vY));
        double _t11 = 2.0 * java.lang.Math.fma(_selfz, vY, -(_selfy * vZ));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfz, _t9, java.lang.Math.fma(-_selfy, _t10, java.lang.Math.fma(_selfw, _t11, vX))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfz, _t11, java.lang.Math.fma(_selfw, _t9, vY))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfy, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, vZ))));
        return dest;
    }

    /** {@code transformInverse_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long transformInverse_unsafe_mulAdd(long dest, long src, double vX, double vY, double vZ) {
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (vZ) - (_selfz * vX));
        double _t10 = 2.0 * ((_selfy) * (vX) - (_selfx * vY));
        double _t11 = 2.0 * ((_selfz) * (vY) - (_selfy * vZ));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfz) * (_t9) + (((((_selfw) * (_t11) + (vX))) - (_selfy) * (_t10)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (vY))) - (_selfz) * (_t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfy) * (_t11) + (((((_selfw) * (_t10) + (vZ))) - (_selfx) * (_t9)))));
        return dest;
    }

    public static long transformInverse_unsafe(long dest, long src, long v) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformInverse_unsafe_mulAdd(dest, src, v);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * java.lang.Math.fma(_selfx, _vz, -(_selfz * _vx));
        double _t10 = 2.0 * java.lang.Math.fma(_selfy, _vx, -(_selfx * _vy));
        double _t11 = 2.0 * java.lang.Math.fma(_selfz, _vy, -(_selfy * _vz));
        UnsafeOpsHolder.U.putDouble(dest, java.lang.Math.fma(_selfz, _t9, java.lang.Math.fma(-_selfy, _t10, java.lang.Math.fma(_selfw, _t11, _vx))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfz, _t11, java.lang.Math.fma(_selfw, _t9, _vy))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, java.lang.Math.fma(_selfy, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, _vz))));
        return dest;
    }

    /** {@code transformInverse_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long transformInverse_unsafe_mulAdd(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(src);
        double _selfy = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _selfw = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t9 = 2.0 * ((_selfx) * (_vz) - (_selfz * _vx));
        double _t10 = 2.0 * ((_selfy) * (_vx) - (_selfx * _vy));
        double _t11 = 2.0 * ((_selfz) * (_vy) - (_selfy * _vz));
        UnsafeOpsHolder.U.putDouble(dest, ((_selfz) * (_t9) + (((((_selfw) * (_t11) + (_vx))) - (_selfy) * (_t10)))));
        UnsafeOpsHolder.U.putDouble(dest + 8L, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (_vy))) - (_selfz) * (_t11)))));
        UnsafeOpsHolder.U.putDouble(dest + 16L, ((_selfy) * (_t11) + (((((_selfw) * (_t10) + (_vz))) - (_selfx) * (_t9)))));
        return dest;
    }

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]:
     * 2 asin(|a-b|/2) up to pi/2 and pi - 2 asin(|a+b|/2) beyond, so asin always sees an
     * argument of at most sqrt(2)/2 and the angle stays accurate at both ends.
     */
    private static double quatArcAngle(double s) {
        double d = 4.0 - s;
        return s > d ? 2.0 * Math.asin(0.5 * java.lang.Math.sqrt(d)) : Math.PI - 2.0 * Math.asin(0.5 * java.lang.Math.sqrt(s));
    }
}
