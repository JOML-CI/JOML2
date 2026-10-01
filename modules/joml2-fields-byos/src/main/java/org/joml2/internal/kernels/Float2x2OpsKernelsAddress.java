// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2x2Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float2x2Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float2x2OpsKernelsAddress {
    private Float2x2OpsKernelsAddress() {}

    public static long getColumn_unsafe(long dest, long src, int col) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        UnsafeOpsHolder.U.putFloat(dest, _idxSw0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _idxSw1);
        return dest;
    }

    public static float getRotationAngle_unsafe(long src) {
        return Math.atan2(UnsafeOpsHolder.U.getFloat(src + 4L), UnsafeOpsHolder.U.getFloat(src));
    }

    public static long getRow_unsafe(long dest, long src, int row) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _idxSw0;
        float _idxSw1;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        UnsafeOpsHolder.U.putFloat(dest, _idxSw0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _idxSw1);
        return dest;
    }

    public static long cofactor_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self10);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self00);
        return dest;
    }

    public static float determinant_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t1 = -(_self01 * _self10);
            return java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(src), UnsafeOpsHolder.U.getFloat(src + 12L), _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        } else {
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _t1 = -(_self01 * _self10);
            return ((UnsafeOpsHolder.U.getFloat(src)) * (UnsafeOpsHolder.U.getFloat(src + 12L)) + (_t1)) - ((_self01) * (_self10) + (_t1));
        }
    }

    public static float frobeniusNorm_unsafe(long src) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_self11, _self11, java.lang.Math.fma(_self10, _self10, java.lang.Math.fma(_self00, _self00, _self01 * _self01))));
        } else {
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
            return (float) java.lang.Math.sqrt(((_self11) * (_self11) + (((_self10) * (_self10) + (((_self00) * (_self00) + (_self01 * _self01)))))));
        }
    }

    public static long invert_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_unsafe_mulAdd(dest, src);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = -(_self01 * _self10);
        float _t5 = java.lang.Math.fma(_self00, _self11, _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return Float2x2OpsKernelsAddress.invert_degenerate(dest, src);
        float _t5_inv = 1.0f / _t5;
        UnsafeOpsHolder.U.putFloat(dest, _self11 * _t5_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_self10 * _t5_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_self01 * _t5_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self00 * _t5_inv);
        return dest;
    }

    /** {@code invert_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = -(_self01 * _self10);
        float _t5 = ((_self00) * (_self11) + (_t1)) - ((_self01) * (_self10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return Float2x2OpsKernelsAddress.invert_degenerate(dest, src);
        float _t5_inv = 1.0f / _t5;
        UnsafeOpsHolder.U.putFloat(dest, _self11 * _t5_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_self10 * _t5_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_self01 * _t5_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self00 * _t5_inv);
        return dest;
    }

    public static long invert_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x2OpsKernelsAddress.invert_degenerate_unsafe(dest, src);
        Float2x2OpsKernelsSegment.invert_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    public static long invert_degenerate_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_unsafe_mulAdd(dest, src);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t6 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t9 * _sp0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t8 * _sp1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t7 * _sp1);
        return dest;
    }

    /** {@code invert_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invert_degenerate_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t6 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t9 * _sp0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t8 * _sp1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t7 * _sp1);
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_unsafe_fma(dest, src, other);
        return invertProduct_unsafe_mulAdd(dest, src, other);
    }

    /** {@code invertProduct_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_fma(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t2 = -(_other01 * _other10);
        float _t3 = -(_self01 * _self10);
        float _t10 = java.lang.Math.fma(_other00, _other11, _t2) - java.lang.Math.fma(_other01, _other10, _t2);
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return Float2x2OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        float _t10_inv = 1.0f / _t10;
        float _t11 = java.lang.Math.fma(_self00, _self11, _t3) - java.lang.Math.fma(_self01, _self10, _t3);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return Float2x2OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        float _t11_inv = 1.0f / _t11;
        float _t22 = _other11 / _t10;
        float _t23 = _self11 / _t11;
        return invertProduct_unsafe_fma_saeb649aa_1(dest, _t22, _t23, _self00 / _t11, _other00 / _t10, -(_other01 * _t10_inv), -(_self10 * _t11_inv), -(_self01 * _t11_inv), -(_other10 * _t10_inv), _t22 * _t23);
    }

    /** Piece 2 of {@code invertProduct_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_fma_saeb649aa_1(long dest, float _t22, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32) {
        float _t33 = _t27 * _t24;
        float _t34 = _t30 * _t22;
        float _t35 = _t29 * _t27;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33));
        return dest;
    }

    /** {@code invertProduct_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_unsafe_mulAdd(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t2 = -(_other01 * _other10);
        float _t3 = -(_self01 * _self10);
        float _t10 = ((_other00) * (_other11) + (_t2)) - ((_other01) * (_other10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return Float2x2OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        float _t10_inv = 1.0f / _t10;
        float _t11 = ((_self00) * (_self11) + (_t3)) - ((_self01) * (_self10) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return Float2x2OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        float _t11_inv = 1.0f / _t11;
        float _t22 = _other11 / _t10;
        float _t23 = _self11 / _t11;
        float _t24 = _self00 / _t11;
        float _t27 = _other00 / _t10;
        return invertProduct_unsafe_mulAdd_s7f31bc6f_1(dest, _t22, _t23, _t24, _t27, -(_other01 * _t10_inv), -(_self10 * _t11_inv), -(_self01 * _t11_inv), -(_other10 * _t10_inv), _t22 * _t23, _t27 * _t24);
    }

    /** Piece 2 of {@code invertProduct_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_mulAdd_s7f31bc6f_1(long dest, float _t22, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t33) {
        float _t34 = _t30 * _t22;
        float _t35 = _t29 * _t27;
        UnsafeOpsHolder.U.putFloat(dest, ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33)));
        return dest;
    }

    public static long invertProduct_degenerate(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x2OpsKernelsAddress.invertProduct_degenerate_unsafe(dest, src, other);
        Float2x2OpsKernelsSegment.invertProduct_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    public static long invertProduct_degenerate_unsafe(long dest, long src, long other) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_unsafe_fma(dest, src, other);
        return invertProduct_degenerate_unsafe_mulAdd(dest, src, other);
    }

    /** {@code invertProduct_degenerate_unsafe} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_degenerate_unsafe_fma(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t0 = unitScale(_other00, _other01, _other00);
        float _t1 = unitScale(_other10, _other11, _other10);
        float _t2 = unitScale(_self10, _self11, _self10);
        float _t3 = unitScale(_self00, _self01, _self00);
        float _t12 = _other01 * _t0;
        float _t13 = _other00 * _t0;
        float _t14 = _other11 * _t1;
        float _t15 = _other10 * _t1;
        float _t16 = _self10 * _t2;
        float _t17 = _self00 * _t3;
        float _t18 = _self11 * _t2;
        float _t19 = _self01 * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        float _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        return invertProduct_degenerate_unsafe_fma_see1ccc65_1(dest, _t0, _t1, _t2, _t3, _t12, _t13, _t15, _t16, _t17, _t18, _t19, _t36, 1.0f / _t36, _t37, 1.0f / _t37, _t14 * _t0 / _t36);
    }

    /** Piece 2 of {@code invertProduct_degenerate_unsafe_fma}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_fma_see1ccc65_1(long dest, float _t0, float _t1, float _t2, float _t3, float _t12, float _t13, float _t15, float _t16, float _t17, float _t18, float _t19, float _t36, float _t36_inv, float _t37, float _t37_inv, float _t48) {
        float _t49 = _t18 * _t3 / _t37;
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t54 = -(_t12 * _t1 * _t36_inv);
        float _t55 = -(_t16 * _t3 * _t37_inv);
        float _t56 = -(_t19 * _t2 * _t37_inv);
        float _t57 = -(_t15 * _t0 * _t36_inv);
        float _t58 = _t48 * _t49;
        float _t59 = _t53 * _t50;
        float _t60 = _t56 * _t48;
        float _t61 = _t55 * _t53;
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_t54, _t55, _t58) + java.lang.Math.fma(_t48, _t49, -_t58));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_t57, _t49, _t61) + java.lang.Math.fma(_t55, _t53, -_t61));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_t54, _t50, _t60) + java.lang.Math.fma(_t56, _t48, -_t60));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_t57, _t56, _t59) + java.lang.Math.fma(_t53, _t50, -_t59));
        return dest;
    }

    /** {@code invertProduct_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long invertProduct_degenerate_unsafe_mulAdd(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t0 = unitScale(_other00, _other01, _other00);
        float _t1 = unitScale(_other10, _other11, _other10);
        float _t2 = unitScale(_self10, _self11, _self10);
        float _t3 = unitScale(_self00, _self01, _self00);
        float _t12 = _other01 * _t0;
        float _t13 = _other00 * _t0;
        float _t14 = _other11 * _t1;
        float _t15 = _other10 * _t1;
        float _t16 = _self10 * _t2;
        float _t17 = _self00 * _t3;
        float _t18 = _self11 * _t2;
        float _t19 = _self01 * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        float _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        return invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_1(dest, _t0, _t1, _t2, _t3, _t12, _t13, _t15, _t16, _t17, _t19, _t36, 1.0f / _t36, _t37, 1.0f / _t37, _t14 * _t0 / _t36, _t18 * _t3 / _t37);
    }

    /** Piece 2 of {@code invertProduct_degenerate_unsafe_mulAdd}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_mulAdd_s373ec3ce_1(long dest, float _t0, float _t1, float _t2, float _t3, float _t12, float _t13, float _t15, float _t16, float _t17, float _t19, float _t36, float _t36_inv, float _t37, float _t37_inv, float _t48, float _t49) {
        float _t50 = _t17 * _t2 / _t37;
        float _t53 = _t13 * _t1 / _t36;
        float _t54 = -(_t12 * _t1 * _t36_inv);
        float _t55 = -(_t16 * _t3 * _t37_inv);
        float _t56 = -(_t19 * _t2 * _t37_inv);
        float _t57 = -(_t15 * _t0 * _t36_inv);
        float _t58 = _t48 * _t49;
        float _t59 = _t53 * _t50;
        float _t60 = _t56 * _t48;
        float _t61 = _t55 * _t53;
        UnsafeOpsHolder.U.putFloat(dest, ((_t54) * (_t55) + (_t58)) + ((_t48) * (_t49) - (_t58)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_t57) * (_t49) + (_t61)) + ((_t55) * (_t53) - (_t61)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_t54) * (_t50) + (_t60)) + ((_t56) * (_t48) - (_t60)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_t57) * (_t56) + (_t59)) + ((_t53) * (_t50) - (_t59)));
        return dest;
    }

    public static long normal_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normal_unsafe_mulAdd(dest, src);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = -(_self01 * _self10);
        float _t5 = java.lang.Math.fma(_self00, _self11, _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return Float2x2OpsKernelsAddress.normal_degenerate(dest, src);
        float _t5_inv = 1.0f / _t5;
        UnsafeOpsHolder.U.putFloat(dest, _self11 * _t5_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_self01 * _t5_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_self10 * _t5_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self00 * _t5_inv);
        return dest;
    }

    /** {@code normal_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normal_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t1 = -(_self01 * _self10);
        float _t5 = ((_self00) * (_self11) + (_t1)) - ((_self01) * (_self10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return Float2x2OpsKernelsAddress.normal_degenerate(dest, src);
        float _t5_inv = 1.0f / _t5;
        UnsafeOpsHolder.U.putFloat(dest, _self11 * _t5_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_self01 * _t5_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_self10 * _t5_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self00 * _t5_inv);
        return dest;
    }

    public static long normal_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x2OpsKernelsAddress.normal_degenerate_unsafe(dest, src);
        Float2x2OpsKernelsSegment.normal_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    public static long normal_degenerate_unsafe(long dest, long src) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_unsafe_mulAdd(dest, src);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t6 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t8 * _sp1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t9 * _sp0));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t7 * _sp1);
        return dest;
    }

    /** {@code normal_degenerate_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long normal_degenerate_unsafe_mulAdd(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t6 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t8 * _sp1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t9 * _sp0));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t7 * _sp1);
        return dest;
    }

    public static float trace_unsafe(long src) {
        return UnsafeOpsHolder.U.getFloat(src) + UnsafeOpsHolder.U.getFloat(src + 12L);
    }

    public static long transpose_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _other00 + _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _other10 + _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _other01 + _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _other11 + _self11);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float scalar) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, scalar * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, scalar * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, scalar * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, scalar * _self11);
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, -_self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_self11);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _self00 - _other00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 - _other10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01 - _other01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11 - _other11);
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        float _v00 = UnsafeOpsHolder.U.getFloat(v);
        float _v10 = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _v01 = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _v11 = UnsafeOpsHolder.U.getFloat(v + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _v00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _v10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _v01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _v11);
        return dest;
    }

    public static long setMat2x3_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _m00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _m10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _m01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _m11);
        return dest;
    }

    public static long setMat3x3_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 12L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 16L);
        UnsafeOpsHolder.U.putFloat(dest, _m00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _m10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _m01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _m11);
        return dest;
    }

    public static long to2x3_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        return dest;
    }

    public static long to3x3_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long decomposeLDU_unsafe(long lower, long diagonal, long upper, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _rcp0 = 1.0f / _self00;
        float _sp0 = UnsafeOpsHolder.U.getFloat(src + 4L) * _rcp0;
        UnsafeOpsHolder.U.putFloat(lower, 1.0f);
        UnsafeOpsHolder.U.putFloat(lower + 4L, _sp0);
        UnsafeOpsHolder.U.putFloat(lower + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(lower + 12L, 1.0f);
        UnsafeOpsHolder.U.putFloat(diagonal, _self00);
        UnsafeOpsHolder.U.putFloat(diagonal + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(diagonal + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(diagonal + 12L, _self11 - _self01 * _sp0);
        UnsafeOpsHolder.U.putFloat(upper, 1.0f);
        UnsafeOpsHolder.U.putFloat(upper + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(upper + 8L, _self01 * _rcp0);
        UnsafeOpsHolder.U.putFloat(upper + 12L, 1.0f);
        return lower;
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f);
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_unsafe_mulAdd(dest, src, other, t);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(t, _other00 - _self00, _self00));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(t, _other10 - _self10, _self10));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(t, _other01 - _self01, _self01));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(t, _other11 - _self11, _self11));
        return dest;
    }

    /** {@code lerp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long lerp_unsafe_mulAdd(long dest, long src, long other, float t) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((t) * (_other00 - _self00) + (_self00)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((t) * (_other10 - _self10) + (_self10)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((t) * (_other01 - _self01) + (_self01)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((t) * (_other11 - _self11) + (_self11)));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long right) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_unsafe_mulAdd(dest, src, right);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _right00 = UnsafeOpsHolder.U.getFloat(right);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        return dest;
    }

    /** {@code mul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long mul_unsafe_mulAdd(long dest, long src, long right) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _right00 = UnsafeOpsHolder.U.getFloat(right);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((_right00) * (_self00) + (_right10 * _self01)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_right00) * (_self10) + (_right10 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_right01) * (_self00) + (_right11 * _self01)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_right01) * (_self10) + (_right11 * _self11)));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_unsafe_mulAdd(dest, src, other);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        return dest;
    }

    /** {@code preMul_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long preMul_unsafe_mulAdd(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((_other00) * (_self00) + (_other01 * _self10)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((_other10) * (_self00) + (_other11 * _self10)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((_other00) * (_self01) + (_other01 * _self11)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((_other10) * (_self01) + (_other11 * _self11)));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long other, float weight) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_unsafe_mulAdd(dest, src, other, weight);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(weight, _other00, _self00));
        UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(weight, _other10, _self10));
        UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(weight, _other01, _self01));
        UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(weight, _other11, _self11));
        return dest;
    }

    /** {@code addScaled_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static long addScaled_unsafe_mulAdd(long dest, long src, long other, float weight) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest, ((weight) * (_other00) + (_self00)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, ((weight) * (_other10) + (_self10)));
        UnsafeOpsHolder.U.putFloat(dest + 8L, ((weight) * (_other01) + (_self01)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, ((weight) * (_other11) + (_self11)));
        return dest;
    }

    public static long makeOuterProduct_unsafe(long dest, float colX, float colY, float rowX, float rowY) {
        UnsafeOpsHolder.U.putFloat(dest, colX * rowX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, colY * rowX);
        UnsafeOpsHolder.U.putFloat(dest + 8L, colX * rowY);
        UnsafeOpsHolder.U.putFloat(dest + 12L, colY * rowY);
        return dest;
    }

    public static long makeOuterProduct_unsafe(long dest, long col, long row) {
        float _colx = UnsafeOpsHolder.U.getFloat(col);
        float _coly = UnsafeOpsHolder.U.getFloat(col + 4L);
        float _rowx = UnsafeOpsHolder.U.getFloat(row);
        float _rowy = UnsafeOpsHolder.U.getFloat(row + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _colx * _rowx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _coly * _rowx);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _colx * _rowy);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _coly * _rowy);
        return dest;
    }

    public static long makeRotation_unsafe(long dest, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        UnsafeOpsHolder.U.putFloat(dest, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t1);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, float vX, float vY) {
        UnsafeOpsHolder.U.putFloat(dest, vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, vY);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _vy);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, float s) {
        UnsafeOpsHolder.U.putFloat(dest, s);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s);
        return dest;
    }

    public static long preRotate_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
            return dest;
        } else {
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (_t0) - (_self10 * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self00) * (_t1) + (_self10 * _t0)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self01) * (_t0) - (_self11 * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self01) * (_t1) + (_self11 * _t0)));
            return dest;
        }
    }

    public static long preScale_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _self00 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11 * vY);
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _self00 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * _vy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11 * _vy);
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, float s) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s * _self11);
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
            UnsafeOpsHolder.U.putFloat(dest + 8L, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
            return dest;
        } else {
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (_t0) + (_self01 * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self10) * (_t0) + (_self11 * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 8L, ((_self01) * (_t0) - (_self00 * _t1)));
            UnsafeOpsHolder.U.putFloat(dest + 12L, ((_self11) * (_t0) - (_self10 * _t1)));
            return dest;
        }
    }

    public static long scale_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _self00 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11 * vY);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _self00 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01 * _vy);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11 * _vy);
        return dest;
    }

    public static long mulVec2_unsafe(long dest, long src, float vX, float vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, vX, _self01 * vY));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self10, vX, _self11 * vY));
            return dest;
        } else {
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (vX) + (_self01 * vY)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self10) * (vX) + (_self11 * vY)));
            return dest;
        }
    }

    public static long mulVec2_unsafe(long dest, long src, long v) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = UnsafeOpsHolder.U.getFloat(v);
            float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, java.lang.Math.fma(_self00, _vx, _self01 * _vy));
            UnsafeOpsHolder.U.putFloat(dest + 4L, java.lang.Math.fma(_self10, _vx, _self11 * _vy));
            return dest;
        } else {
            float _vx = UnsafeOpsHolder.U.getFloat(v);
            float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
            float _self00 = UnsafeOpsHolder.U.getFloat(src);
            float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
            float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
            float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
            UnsafeOpsHolder.U.putFloat(dest, ((_self00) * (_vx) + (_self01 * _vy)));
            UnsafeOpsHolder.U.putFloat(dest + 4L, ((_self10) * (_vx) + (_self11 * _vy)));
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
}
