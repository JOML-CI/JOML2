// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2x2Ops} whose leading storage
 * parameter is a {@code double[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double2x2Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double2x2OpsKernelsArray {
    private Double2x2OpsKernelsArray() {}

    public static double[] invert_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dest[destOffset] = _t6 * _sp0;
        dest[destOffset + 1] = -(_t9 * _sp0);
        dest[destOffset + 2] = -(_t8 * _sp1);
        dest[destOffset + 3] = _t7 * _sp1;
        return dest;
    }

    /** {@code invert_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] invert_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dest[destOffset] = _t6 * _sp0;
        dest[destOffset + 1] = -(_t9 * _sp0);
        dest[destOffset + 2] = -(_t8 * _sp1);
        dest[destOffset + 3] = _t7 * _sp1;
        return dest;
    }

    public static double[] invertProduct_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] invertProduct_degenerate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        double _t12 = _other01 * _t0;
        double _t13 = _other00 * _t0;
        double _t14 = _other11 * _t1;
        double _t15 = _other10 * _t1;
        double _t16 = _self10 * _t2;
        double _t17 = _self00 * _t3;
        double _t18 = _self11 * _t2;
        double _t19 = _self01 * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        double _t36_inv = 1.0 / _t36;
        double _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        double _t37_inv = 1.0 / _t37;
        return invertProduct_degenerate_s13d521b0_1_fma(dest, destOffset, _t14 * _t0 / _t36, _t18 * _t3 / _t37, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), -(_t19 * _t2 * _t37_inv), -(_t15 * _t0 * _t36_inv));
    }

    /** {@code invertProduct_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] invertProduct_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        double _t12 = _other01 * _t0;
        double _t13 = _other00 * _t0;
        double _t14 = _other11 * _t1;
        double _t15 = _other10 * _t1;
        double _t16 = _self10 * _t2;
        double _t17 = _self00 * _t3;
        double _t18 = _self11 * _t2;
        double _t19 = _self01 * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        double _t36_inv = 1.0 / _t36;
        double _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        double _t37_inv = 1.0 / _t37;
        return invertProduct_degenerate_s13d521b0_1_mulAdd(dest, destOffset, _t14 * _t0 / _t36, _t18 * _t3 / _t37, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), -(_t19 * _t2 * _t37_inv), -(_t15 * _t0 * _t36_inv));
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_1_fma(double[] dest, int destOffset, double _t48, double _t49, double _t50, double _t53, double _t54, double _t55, double _t56, double _t57) {
        double _t58 = _t48 * _t49;
        double _t59 = _t53 * _t50;
        double _t60 = _t56 * _t48;
        double _t61 = _t55 * _t53;
        dest[destOffset] = java.lang.Math.fma(_t54, _t55, _t58) + java.lang.Math.fma(_t48, _t49, -_t58);
        dest[destOffset + 1] = java.lang.Math.fma(_t57, _t49, _t61) + java.lang.Math.fma(_t55, _t53, -_t61);
        dest[destOffset + 2] = java.lang.Math.fma(_t54, _t50, _t60) + java.lang.Math.fma(_t56, _t48, -_t60);
        dest[destOffset + 3] = java.lang.Math.fma(_t57, _t56, _t59) + java.lang.Math.fma(_t53, _t50, -_t59);
        return dest;
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_1_mulAdd(double[] dest, int destOffset, double _t48, double _t49, double _t50, double _t53, double _t54, double _t55, double _t56, double _t57) {
        double _t58 = _t48 * _t49;
        double _t59 = _t53 * _t50;
        double _t60 = _t56 * _t48;
        double _t61 = _t55 * _t53;
        dest[destOffset] = ((_t54) * (_t55) + (_t58)) + ((_t48) * (_t49) - (_t58));
        dest[destOffset + 1] = ((_t57) * (_t49) + (_t61)) + ((_t55) * (_t53) - (_t61));
        dest[destOffset + 2] = ((_t54) * (_t50) + (_t60)) + ((_t56) * (_t48) - (_t60));
        dest[destOffset + 3] = ((_t57) * (_t56) + (_t59)) + ((_t53) * (_t50) - (_t59));
        return dest;
    }

    public static double[] normal_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dest[destOffset] = _t6 * _sp0;
        dest[destOffset + 1] = -(_t8 * _sp1);
        dest[destOffset + 2] = -(_t9 * _sp0);
        dest[destOffset + 3] = _t7 * _sp1;
        return dest;
    }

    /** {@code normal_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] normal_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dest[destOffset] = _t6 * _sp0;
        dest[destOffset + 1] = -(_t8 * _sp1);
        dest[destOffset + 2] = -(_t9 * _sp0);
        dest[destOffset + 3] = _t7 * _sp1;
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
}
