// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2x3Ops} whose leading storage
 * parameter is a {@code double[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double2x3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double2x3OpsKernelsArray {
    private Double2x3OpsKernelsArray() {}

    public static double[] invert_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t8 = _self11 * _t0;
        double _t9 = _self00 * _t1;
        double _t10 = _self01 * _t1;
        double _t11 = _self10 * _t0;
        double _t12 = _self02 * _t1;
        double _t13 = _self12 * _t0;
        double _t17 = -(_t10 * _t11);
        double _t18 = -(_t10 * _t13);
        double _t19 = -(_t12 * _t11);
        double _t22_inv = 1.0 / (java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17));
        double _sp1 = _t0 * _t22_inv;
        double _sp0 = _t1 * _t22_inv;
        dest[destOffset] = _t8 * _sp0;
        dest[destOffset + 1] = -(_t11 * _sp0);
        dest[destOffset + 2] = -(_t10 * _sp1);
        dest[destOffset + 3] = _t9 * _sp1;
        dest[destOffset + 4] = (java.lang.Math.fma(_t10, _t13, _t18) - java.lang.Math.fma(_t12, _t8, _t18)) * _t22_inv;
        dest[destOffset + 5] = (java.lang.Math.fma(_t12, _t11, _t19) - java.lang.Math.fma(_t9, _t13, _t19)) * _t22_inv;
        return dest;
    }

    /** {@code invert_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] invert_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t8 = _self11 * _t0;
        double _t9 = _self00 * _t1;
        double _t10 = _self01 * _t1;
        double _t11 = _self10 * _t0;
        double _t12 = _self02 * _t1;
        double _t13 = _self12 * _t0;
        double _t17 = -(_t10 * _t11);
        double _t18 = -(_t10 * _t13);
        double _t19 = -(_t12 * _t11);
        double _t22_inv = 1.0 / (((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17)));
        double _sp1 = _t0 * _t22_inv;
        double _sp0 = _t1 * _t22_inv;
        dest[destOffset] = _t8 * _sp0;
        dest[destOffset + 1] = -(_t11 * _sp0);
        dest[destOffset + 2] = -(_t10 * _sp1);
        dest[destOffset + 3] = _t9 * _sp1;
        dest[destOffset + 4] = (((_t10) * (_t13) + (_t18)) - ((_t12) * (_t8) + (_t18))) * _t22_inv;
        dest[destOffset + 5] = (((_t12) * (_t11) + (_t19)) - ((_t9) * (_t13) + (_t19))) * _t22_inv;
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
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        double _other02 = other[otherOffset + 4];
        double _other12 = other[otherOffset + 5];
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        double _t16 = _other01 * _t0;
        double _t17 = _other00 * _t0;
        double _t18 = _other11 * _t1;
        double _t19 = _other10 * _t1;
        double _t20 = _self10 * _t2;
        double _t23 = _self01 * _t3;
        double _t24 = _self12 * _t2;
        double _t25 = _self02 * _t3;
        double _t26 = _other02 * _t0;
        double _t27 = _other12 * _t1;
        double _t42 = -(_t16 * _t19);
        return invertProduct_degenerate_s13d521b0_1_fma(dest, destOffset, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _self00 * _t3, _self11 * _t2, _t23, _t24, _t25, _t26, _t27, -(_t23 * _t20), -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42));
    }

    /** {@code invertProduct_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double[] invertProduct_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        double _other02 = other[otherOffset + 4];
        double _other12 = other[otherOffset + 5];
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        double _t16 = _other01 * _t0;
        double _t17 = _other00 * _t0;
        double _t18 = _other11 * _t1;
        double _t19 = _other10 * _t1;
        double _t20 = _self10 * _t2;
        double _t23 = _self01 * _t3;
        double _t24 = _self12 * _t2;
        double _t25 = _self02 * _t3;
        double _t26 = _other02 * _t0;
        double _t27 = _other12 * _t1;
        double _t42 = -(_t16 * _t19);
        return invertProduct_degenerate_s13d521b0_1_mulAdd(dest, destOffset, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _self00 * _t3, _self11 * _t2, _t23, _t24, _t25, _t26, _t27, -(_t23 * _t20), -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42)));
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_1_fma(double[] dest, int destOffset, double _t0, double _t1, double _t2, double _t3, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t27, double _t43, double _t44, double _t45, double _t46, double _t47, double _t56) {
        double _t56_inv = 1.0 / _t56;
        double _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        double _t57_inv = 1.0 / _t57;
        double _t70 = _t18 * _t0 / _t56;
        double _t71 = _t22 * _t3 / _t57;
        double _t72 = _t21 * _t2 / _t57;
        double _t75 = _t17 * _t1 / _t56;
        double _t76 = -(_t16 * _t1 * _t56_inv);
        double _t77 = -(_t20 * _t3 * _t57_inv);
        double _t78 = -(_t23 * _t2 * _t57_inv);
        double _t79 = -(_t19 * _t0 * _t56_inv);
        double _t86 = _t70 * _t71;
        double _t88 = _t78 * _t70;
        double _t89 = _t77 * _t75;
        dest[destOffset] = java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86);
        dest[destOffset + 1] = java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89);
        dest[destOffset + 2] = java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88);
        return invertProduct_degenerate_s13d521b0_2_fma(dest, destOffset, _t16, _t17, _t18, _t19, _t26, _t27, _t46, _t47, _t56_inv, _t70, _t72, _t75, _t76, _t78, _t79, -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv), -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv), _t75 * _t72);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_1_mulAdd(double[] dest, int destOffset, double _t0, double _t1, double _t2, double _t3, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t27, double _t43, double _t44, double _t45, double _t46, double _t47, double _t56) {
        double _t56_inv = 1.0 / _t56;
        double _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        double _t57_inv = 1.0 / _t57;
        double _t70 = _t18 * _t0 / _t56;
        double _t71 = _t22 * _t3 / _t57;
        double _t72 = _t21 * _t2 / _t57;
        double _t75 = _t17 * _t1 / _t56;
        double _t76 = -(_t16 * _t1 * _t56_inv);
        double _t77 = -(_t20 * _t3 * _t57_inv);
        double _t78 = -(_t23 * _t2 * _t57_inv);
        double _t79 = -(_t19 * _t0 * _t56_inv);
        double _t86 = _t70 * _t71;
        double _t88 = _t78 * _t70;
        double _t89 = _t77 * _t75;
        dest[destOffset] = ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86));
        dest[destOffset + 1] = ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89));
        dest[destOffset + 2] = ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88));
        return invertProduct_degenerate_s13d521b0_2_mulAdd(dest, destOffset, _t16, _t17, _t18, _t19, _t26, _t27, _t46, _t47, _t56_inv, _t70, _t72, _t75, _t76, _t78, _t79, -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv), -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv), _t75 * _t72);
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_2_fma(double[] dest, int destOffset, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t46, double _t47, double _t56_inv, double _t70, double _t72, double _t75, double _t76, double _t78, double _t79, double _t84, double _t85, double _t87) {
        dest[destOffset + 3] = java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87);
        dest[destOffset + 4] = java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv)));
        dest[destOffset + 5] = java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv)));
        return dest;
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_2_mulAdd(double[] dest, int destOffset, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t46, double _t47, double _t56_inv, double _t70, double _t72, double _t75, double _t76, double _t78, double _t79, double _t84, double _t85, double _t87) {
        dest[destOffset + 3] = ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87));
        dest[destOffset + 4] = ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv))));
        dest[destOffset + 5] = ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv))));
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
