// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double3x4Ops} whose leading storage
 * parameter is a {@code double[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double3x4Ops} and its sibling kernel units. Not public API.
 */
public final class Double3x4OpsKernelsArray {
    private Double3x4OpsKernelsArray() {}

    public static double[] invNegativeX_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 6];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t8 = _self10 * _t0;
        double _t9 = _self21 * _t1;
        double _t10 = _self11 * _t0;
        double _t11 = _self20 * _t1;
        double _t12 = _self22 * _t1;
        double _t13 = _self12 * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dest[destOffset] = -(_t21 * _t26);
            dest[destOffset + 1] = -(_t22 * _t26);
            dest[destOffset + 2] = -(_t20 * _t26);
        } else {
            dest[destOffset] = -0.0;
            dest[destOffset + 1] = -0.0;
            dest[destOffset + 2] = -0.0;
        }
        return dest;
    }

    public static double[] invNegativeY_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 1];
        double _self02 = src[srcOffset + 2];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _t0 = unitScale(_self00, _self01, _self02);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t8 = _self01 * _t0;
        double _t9 = _self20 * _t1;
        double _t10 = _self00 * _t0;
        double _t11 = _self21 * _t1;
        double _t12 = _self22 * _t1;
        double _t13 = _self02 * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dest[destOffset] = -(_t22 * _t26);
            dest[destOffset + 1] = -(_t21 * _t26);
            dest[destOffset + 2] = -(_t20 * _t26);
        } else {
            dest[destOffset] = -0.0;
            dest[destOffset + 1] = -0.0;
            dest[destOffset + 2] = -0.0;
        }
        return dest;
    }

    public static double[] invNegativeZ_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 1];
        double _self02 = src[srcOffset + 2];
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 6];
        double _t0 = unitScale(_self00, _self01, _self02);
        double _t1 = unitScale(_self10, _self11, _self12);
        double _t8 = _self00 * _t0;
        double _t9 = _self11 * _t1;
        double _t10 = _self01 * _t0;
        double _t11 = _self10 * _t1;
        double _t12 = _self12 * _t1;
        double _t13 = _self02 * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dest[destOffset] = -(_t21 * _t26);
            dest[destOffset + 1] = -(_t22 * _t26);
            dest[destOffset + 2] = -(_t20 * _t26);
        } else {
            dest[destOffset] = -0.0;
            dest[destOffset + 1] = -0.0;
            dest[destOffset + 2] = -0.0;
        }
        return dest;
    }

    public static double[] invPositiveX_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 6];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t8 = _self10 * _t0;
        double _t9 = _self21 * _t1;
        double _t10 = _self11 * _t0;
        double _t11 = _self20 * _t1;
        double _t12 = _self22 * _t1;
        double _t13 = _self12 * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dest[destOffset] = _t21 * _t26;
            dest[destOffset + 1] = _t22 * _t26;
            dest[destOffset + 2] = _t20 * _t26;
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
        }
        return dest;
    }

    public static double[] invPositiveY_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 1];
        double _self02 = src[srcOffset + 2];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _t0 = unitScale(_self00, _self01, _self02);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t8 = _self01 * _t0;
        double _t9 = _self20 * _t1;
        double _t10 = _self00 * _t0;
        double _t11 = _self21 * _t1;
        double _t12 = _self22 * _t1;
        double _t13 = _self02 * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dest[destOffset] = _t22 * _t26;
            dest[destOffset + 1] = _t21 * _t26;
            dest[destOffset + 2] = _t20 * _t26;
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
        }
        return dest;
    }

    public static double[] invPositiveZ_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 1];
        double _self02 = src[srcOffset + 2];
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 6];
        double _t0 = unitScale(_self00, _self01, _self02);
        double _t1 = unitScale(_self10, _self11, _self12);
        double _t8 = _self00 * _t0;
        double _t9 = _self11 * _t1;
        double _t10 = _self01 * _t0;
        double _t11 = _self10 * _t1;
        double _t12 = _self12 * _t1;
        double _t13 = _self02 * _t0;
        double _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        double _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        double _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dest[destOffset] = _t21 * _t26;
            dest[destOffset + 1] = _t22 * _t26;
            dest[destOffset + 2] = _t20 * _t26;
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
        }
        return dest;
    }

    public static double[] invert_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 1];
        double _self02 = src[srcOffset + 2];
        double _self03 = src[srcOffset + 3];
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 6];
        double _self13 = src[srcOffset + 7];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self23 = src[srcOffset + 11];
        double _t0 = unitScale(_self10, _self11, _self12);
        double _t1 = unitScale(_self20, _self21, _self22);
        double _t2 = unitScale(_self00, _self01, _self02);
        double _t15 = _self11 * _t0;
        double _t16 = _self22 * _t1;
        double _t17 = _self12 * _t0;
        double _t18 = _self21 * _t1;
        double _t19 = _self10 * _t0;
        double _t20 = _self20 * _t1;
        double _t21 = _self02 * _t2;
        double _t22 = _self00 * _t2;
        double _t23 = _self01 * _t2;
        return invert_degenerate_sa19c02d6_1(dest, destOffset, _t0, _t1, _t2, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _self23 * _t1, _self13 * _t0, _self03 * _t2, Math.fma(_t15, _t16, -(_t17 * _t18)), Math.fma(_t19, _t18, -(_t15 * _t20)), Math.fma(_t21, _t18, -(_t23 * _t16)), Math.fma(_t23, _t17, -(_t21 * _t15)), Math.fma(_t17, _t20, -(_t19 * _t16)), Math.fma(_t22, _t16, -(_t21 * _t20)));
    }

    /** Piece 2 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invert_degenerate_sa19c02d6_1(double[] dest, int destOffset, double _t0, double _t1, double _t2, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t47, double _t48, double _t50, double _t51, double _t52, double _t53) {
        double _t54 = Math.fma(_t21, _t19, -(_t22 * _t17));
        double _t55 = Math.fma(_t23, _t20, -(_t22 * _t18));
        double _t56 = Math.fma(_t22, _t15, -(_t23 * _t19));
        double _t60_inv = 1.0 / Math.fma(_t48, _t21, Math.fma(_t47, _t22, -(Math.fma(_t19, _t16, -(_t17 * _t20)) * _t23)));
        double _sp2 = _t1 * _t60_inv;
        double _sp1 = _t0 * _t60_inv;
        double _sp0 = _t2 * _t60_inv;
        dest[destOffset] = _t47 * _sp0;
        dest[destOffset + 1] = _t50 * _sp1;
        dest[destOffset + 2] = _t51 * _sp2;
        dest[destOffset + 3] = -(Math.fma(_t51, _t24, Math.fma(_t50, _t25, _t47 * _t26)) * _t60_inv);
        dest[destOffset + 4] = _t52 * _sp0;
        dest[destOffset + 5] = _t53 * _sp1;
        dest[destOffset + 6] = _t54 * _sp2;
        dest[destOffset + 7] = -(Math.fma(_t54, _t24, Math.fma(_t53, _t25, _t52 * _t26)) * _t60_inv);
        dest[destOffset + 8] = _t48 * _sp0;
        dest[destOffset + 9] = _t55 * _sp1;
        dest[destOffset + 10] = _t56 * _sp2;
        dest[destOffset + 11] = -(Math.fma(_t56, _t24, Math.fma(_t55, _t25, _t48 * _t26)) * _t60_inv);
        return dest;
    }

    public static double[] invertProduct_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 6];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _other00 = other[otherOffset];
        double _other01 = other[otherOffset + 1];
        double _other02 = other[otherOffset + 2];
        double _other10 = other[otherOffset + 4];
        double _other11 = other[otherOffset + 5];
        double _other12 = other[otherOffset + 6];
        double _other20 = other[otherOffset + 8];
        double _other21 = other[otherOffset + 9];
        double _other22 = other[otherOffset + 10];
        return invertProduct_degenerate_s13d521b0_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _self10, _self11, _self12, src[srcOffset + 7], _self20, _self21, _self22, src[srcOffset + 11], _other00, _other01, _other02, other[otherOffset + 3], _other10, _other11, _other12, other[otherOffset + 7], _other20, _other21, _other22, other[otherOffset + 11], Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)), Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11)), Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11)), Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21)));
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other01, double _other02, double _other03, double _other10, double _other11, double _other12, double _other13, double _other20, double _other21, double _other22, double _other23, double _t24, double _t25, double _t26, double _t27) {
        double _t28 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        double _t29 = Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21));
        double _t30 = Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01));
        double _t31 = Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01));
        double _t32 = Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01));
        double _t36 = unitScale(_t25, _t24, _t26);
        double _t37 = unitScale(_t28, _t29, _t27);
        double _t38 = unitScale(_t30, _t31, _t32);
        double _t48 = _t24 * _t36;
        double _t49 = _t27 * _t37;
        double _t50 = _t29 * _t37;
        double _t51 = _t26 * _t36;
        return invertProduct_degenerate_s13d521b0_2(dest, destOffset, _t36, _t37, _t38, _t48, _t49, _t50, _t51, _t25 * _t36, _t28 * _t37, _t32 * _t38, _t30 * _t38, _t31 * _t38, Math.fma(_other03, _self20, Math.fma(_other13, _self21, Math.fma(_other23, _self22, _self23))) * _t37, Math.fma(_other03, _self00, Math.fma(_other13, _self01, Math.fma(_other23, _self02, _self03))) * _t38, Math.fma(_other03, _self10, Math.fma(_other13, _self11, Math.fma(_other23, _self12, _self13))) * _t36, Math.fma(_t48, _t49, -(_t50 * _t51)));
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_2(double[] dest, int destOffset, double _t36, double _t37, double _t38, double _t48, double _t49, double _t50, double _t51, double _t52, double _t53, double _t54, double _t55, double _t56, double _t60, double _t61, double _t62, double _t83) {
        double _t84 = Math.fma(_t52, _t50, -(_t53 * _t48));
        double _t86 = Math.fma(_t50, _t54, -(_t56 * _t49));
        double _t87 = Math.fma(_t56, _t51, -(_t48 * _t54));
        double _t88 = Math.fma(_t53, _t51, -(_t52 * _t49));
        double _t89 = Math.fma(_t55, _t49, -(_t53 * _t54));
        double _t90 = Math.fma(_t52, _t54, -(_t55 * _t51));
        double _t96_inv = 1.0 / Math.fma(_t84, _t54, Math.fma(_t83, _t55, -(Math.fma(_t52, _t49, -(_t53 * _t51)) * _t56)));
        double _sp2 = _t37 * _t96_inv;
        double _sp1 = _t36 * _t96_inv;
        double _sp0 = _t38 * _t96_inv;
        dest[destOffset] = _t83 * _sp0;
        dest[destOffset + 1] = _t86 * _sp1;
        dest[destOffset + 2] = _t87 * _sp2;
        dest[destOffset + 3] = -(Math.fma(_t87, _t60, Math.fma(_t83, _t61, _t86 * _t62)) * _t96_inv);
        dest[destOffset + 4] = _t88 * _sp0;
        dest[destOffset + 5] = _t89 * _sp1;
        dest[destOffset + 6] = _t90 * _sp2;
        return invertProduct_degenerate_s13d521b0_3(dest, destOffset, _t60, _t61, _t62, _t84, _t88, _t89, _t90, Math.fma(_t53, _t56, -(_t55 * _t50)), Math.fma(_t55, _t48, -(_t52 * _t56)), _t96_inv, _sp2, _sp1, _sp0);
    }

    /** Piece 4 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_3(double[] dest, int destOffset, double _t60, double _t61, double _t62, double _t84, double _t88, double _t89, double _t90, double _t91, double _t92, double _t96_inv, double _sp2, double _sp1, double _sp0) {
        dest[destOffset + 7] = -(Math.fma(_t90, _t60, Math.fma(_t89, _t62, _t88 * _t61)) * _t96_inv);
        dest[destOffset + 8] = _t84 * _sp0;
        dest[destOffset + 9] = _t91 * _sp1;
        dest[destOffset + 10] = _t92 * _sp2;
        dest[destOffset + 11] = -(Math.fma(_t92, _t60, Math.fma(_t84, _t61, _t91 * _t62)) * _t96_inv);
        return dest;
    }

    public static double[] toRigid_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 1];
        double _self02 = src[srcOffset + 2];
        double _self03 = src[srcOffset + 3];
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 6];
        double _self13 = src[srcOffset + 7];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self23 = src[srcOffset + 11];
        double _t0 = unitScale(_self01, _self11, _self21);
        double _t1 = unitScale(_self02, _self12, _self22);
        double _t2 = unitScale(_self00, _self10, _self20);
        double _t12 = _self21 * _t0;
        double _t13 = _self01 * _t0;
        double _t14 = _self11 * _t0;
        double _t15 = _self22 * _t1;
        double _t16 = _self02 * _t1;
        double _t17 = _self12 * _t1;
        double _t18 = _self20 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self10 * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t33 = _t30 * _t18;
        double _t34 = _t30 * _t19;
        double _t35 = _t30 * _t20;
        double _t36 = _t31 * _t17;
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
        double _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0;
            _t87 = -_t34;
        } else {
            _t72 = 0.0;
            _t75 = -_t35;
            _t87 = _t33;
        }
        double _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0;
            _t88 = -_t38;
        } else {
            _t73 = 0.0;
            _t76 = -_t36;
            _t88 = _t37;
        }
        double _t74, _t77, _t89;
        if (java.lang.Math.abs(_t40) < java.lang.Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        double _t102 = _t99 * _t72;
        double _t103 = _t100 * _t73;
        double _t104 = _t101 * _t74;
        double _t105 = _t100 * _t76;
        double _t106 = _t99 * _t75;
        double _t107 = _t101 * _t77;
        double _t114 = _t100 * _t88;
        double _t115 = _t101 * _t89;
        double _t116 = _t99 * _t87;
        double _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t167 = 1.0;
                    _t170 = 0.0;
                    _t166 = 0.0;
                    _t168 = 0.0;
                    _t171 = 1.0;
                    _t169 = 0.0;
                    _t172 = 0.0;
                    _t173 = 1.0;
                } else {
                    _t165 = _t102;
                    _t167 = _t116;
                    _t170 = _t106;
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                    _t171 = Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = Math.fma(_t36, _t105, -(_t37 * _t114));
                    _t167 = Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = Math.fma(_t33, _t36, -(_t35 * _t37));
                    _t167 = Math.fma(_t34, _t37, -(_t33 * _t38));
                    _t170 = Math.fma(_t35, _t38, -(_t34 * _t36));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t115;
                    _t168 = _t104;
                    _t171 = _t107;
                    _t169 = Math.fma(_t39, _t115, -(_t41 * _t104));
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                    _t173 = Math.fma(_t41, _t107, -(_t40 * _t115));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = Math.fma(_t35, _t40, -(_t33 * _t41));
                    _t171 = Math.fma(_t34, _t41, -(_t35 * _t39));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = Math.fma(_t39, _t36, -(_t41 * _t38));
                    _t172 = Math.fma(_t40, _t38, -(_t39 * _t37));
                    _t173 = Math.fma(_t41, _t37, -(_t40 * _t36));
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            }
        }
        double _t182 = _t170 - _t166;
        double _t184 = _t170 + _t166;
        double _t194, _t195, _t196;
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        double _t199 = _t195 + _t165;
        double _t200 = _t196 + _t168;
        double _t201 = _t168 - _t196;
        double _t202 = _t195 - _t165;
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t208));
        if (_t206 > 0.0) {
            dest[destOffset + 3] = _sp0 * _t182;
            dest[destOffset + 4] = _sp0 * _t201;
            dest[destOffset + 5] = _sp0 * _t202;
            dest[destOffset + 6] = 0.5 * java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                dest[destOffset + 3] = 0.5 * java.lang.Math.sqrt(_t208);
                dest[destOffset + 4] = _sp3 * _t199;
                dest[destOffset + 5] = _sp3 * _t200;
                dest[destOffset + 6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dest[destOffset + 3] = _sp1 * _t199;
                    dest[destOffset + 4] = 0.5 * java.lang.Math.sqrt(_t209);
                    dest[destOffset + 5] = _sp1 * _t184;
                    dest[destOffset + 6] = _sp1 * _t201;
                } else {
                    dest[destOffset + 3] = _sp2 * _t200;
                    dest[destOffset + 4] = _sp2 * _t184;
                    dest[destOffset + 5] = 0.5 * java.lang.Math.sqrt(_t210);
                    dest[destOffset + 6] = _sp2 * _t202;
                }
            }
        }
        dest[destOffset] = _self03;
        dest[destOffset + 1] = _self13;
        dest[destOffset + 2] = _self23;
        return dest;
    }

    public static double[] toTransform_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 1];
        double _self02 = src[srcOffset + 2];
        double _self03 = src[srcOffset + 3];
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 6];
        double _self13 = src[srcOffset + 7];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self23 = src[srcOffset + 11];
        double _t0 = unitScale(_self01, _self11, _self21);
        double _t1 = unitScale(_self02, _self12, _self22);
        double _t2 = unitScale(_self00, _self10, _self20);
        double _t12 = _self21 * _t0;
        double _t13 = _self01 * _t0;
        double _t14 = _self11 * _t0;
        double _t15 = _self22 * _t1;
        double _t16 = _self02 * _t1;
        double _t17 = _self12 * _t1;
        double _t18 = _self20 * _t2;
        double _t19 = _self00 * _t2;
        double _t20 = _self10 * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        double _t32 = (1.0 / java.lang.Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (java.lang.Math.abs(_t35) < java.lang.Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (java.lang.Math.abs(_t39) < java.lang.Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (java.lang.Math.abs(_t42) < java.lang.Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / java.lang.Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        double _t105 = _t102 * _t75;
        double _t106 = _t103 * _t76;
        double _t107 = _t104 * _t77;
        double _t108 = _t103 * _t79;
        double _t109 = _t102 * _t78;
        double _t110 = _t104 * _t80;
        double _t117 = _t103 * _t91;
        double _t118 = _t104 * _t92;
        double _t119 = _t102 * _t90;
        double _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t170 = 1.0;
                    _t173 = 0.0;
                    _t169 = 0.0;
                    _t171 = 0.0;
                    _t174 = 1.0;
                    _t172 = 0.0;
                    _t175 = 0.0;
                    _t176 = 1.0;
                } else {
                    _t168 = _t105;
                    _t170 = _t119;
                    _t173 = _t109;
                    _t169 = Math.fma(_t35, _t105, -(_t36 * _t109));
                    _t171 = Math.fma(_t37, _t109, -(_t35 * _t119));
                    _t174 = Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = Math.fma(_t38, _t108, -(_t39 * _t117));
                    _t170 = Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = Math.fma(_t35, _t38, -(_t37 * _t39));
                    _t170 = Math.fma(_t36, _t39, -(_t35 * _t40));
                    _t173 = Math.fma(_t37, _t40, -(_t36 * _t38));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        } else {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t118;
                    _t171 = _t107;
                    _t174 = _t110;
                    _t172 = Math.fma(_t41, _t118, -(_t43 * _t107));
                    _t175 = Math.fma(_t42, _t107, -(_t41 * _t110));
                    _t176 = Math.fma(_t43, _t110, -(_t42 * _t118));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = Math.fma(_t37, _t42, -(_t35 * _t43));
                    _t174 = Math.fma(_t36, _t43, -(_t37 * _t41));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = Math.fma(_t41, _t38, -(_t43 * _t40));
                    _t175 = Math.fma(_t42, _t40, -(_t41 * _t39));
                    _t176 = Math.fma(_t43, _t39, -(_t42 * _t38));
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            }
        }
        double _t185 = _t173 - _t169;
        double _t186 = java.lang.Math.max(_t170, _t174);
        double _t187 = _t173 + _t169;
        double _t196 = Math.fma(Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, Math.fma(Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        double _t197, _t198, _t199;
        if (_t196 < 0.0) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        double _t202 = _t198 + _t168;
        double _t203 = _t199 + _t171;
        double _t204 = _t171 - _t199;
        double _t205 = _t198 - _t168;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t211));
        dest[destOffset] = _self03;
        dest[destOffset + 1] = _self13;
        dest[destOffset + 2] = _self23;
        dest[destOffset + 3] = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dest[destOffset + 4] = _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187;
        dest[destOffset + 5] = _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213);
        dest[destOffset + 6] = _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dest[destOffset + 7] = _t196 < 0.0 ? -_t56 : _t56;
        dest[destOffset + 8] = _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0;
        dest[destOffset + 9] = _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1;
        return dest;
    }

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        double _t24 = Math.fma(_t23, _t14, upX);
        double _t25 = Math.fma(_t23, _t16, upY);
        double _t26 = Math.fma(_t23, _t15, upZ);
        return lookAt_lh_sd1cc022f_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), Math.fma(_t24, _t16, -(_t25 * _t14)), Math.fma(_t25, _t15, -(_t26 * _t16)), Math.fma(_t26, _t14, -(_t24 * _t15)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_sd1cc022f_1(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t22, double _t33, double _t34, double _t35) {
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        dest[destOffset] = Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54));
        dest[destOffset + 1] = Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55));
        dest[destOffset + 2] = Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56));
        return lookAt_lh_sd1cc022f_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)), Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
    }

    /** Piece 3 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_sd1cc022f_2(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t58, double _t60) {
        dest[destOffset + 3] = Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03)));
        dest[destOffset + 4] = Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54));
        dest[destOffset + 5] = Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55));
        dest[destOffset + 6] = Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56));
        dest[destOffset + 7] = Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13)));
        dest[destOffset + 8] = Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54));
        dest[destOffset + 9] = Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55));
        dest[destOffset + 10] = Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56));
        dest[destOffset + 11] = Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23)));
        return dest;
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _self02 = src[srcOffset + 2];
        double _self12 = src[srcOffset + 6];
        double _self22 = src[srcOffset + 10];
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        double _t27 = Math.fma(_t26, _t19, upY);
        double _t28 = Math.fma(_t26, _t17, upX);
        double _t29 = Math.fma(_t26, _t18, upZ);
        return lookAt_rh_s32404a0d_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], _self02, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self12, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], -_self02, -_self12, -_self22, _t17, _t18, _t19, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)), _t27, _t29, Math.fma(_t27, _t17, -(_t28 * _t19)), Math.fma(_t28, _t18, -(_t29 * _t17)));
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_s32404a0d_1(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25, double _t27, double _t29, double _t36, double _t37) {
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        dest[destOffset] = Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57));
        dest[destOffset + 1] = Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58));
        return lookAt_rh_s32404a0d_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)), Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58)));
    }

    /** Piece 3 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_s32404a0d_2(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        dest[destOffset + 2] = Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59));
        dest[destOffset + 3] = Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03)));
        dest[destOffset + 4] = Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57));
        dest[destOffset + 5] = Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58));
        dest[destOffset + 6] = Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59));
        dest[destOffset + 7] = Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13)));
        dest[destOffset + 8] = Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57));
        dest[destOffset + 9] = Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58));
        dest[destOffset + 10] = Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59));
        dest[destOffset + 11] = Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23)));
        return dest;
    }

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _t0 = center[centerOffset + 2] - _eyez;
        double _t1 = center[centerOffset] - _eyex;
        double _t2 = center[centerOffset + 1] - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        return lookAt_lh_s2812e005_3(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _eyex, _eyey, _eyez, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _upx, _upy, _upz, _t23, Math.fma(_t23, _t14, _upx));
    }

    /** Part 1 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double lookAt_lh_s2812e005_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _eyex, double _eyey, double _eyez, double _t14, double _t16, double _t15, double _t22, double _t43, double _t45, double _t44, double _t54, double _t55, double _t56, double _t58) {
        double _t60 = Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55));
        dest[destOffset] = Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54));
        dest[destOffset + 1] = Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55));
        dest[destOffset + 2] = Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56));
        dest[destOffset + 3] = Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03)));
        dest[destOffset + 4] = Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54));
        dest[destOffset + 5] = Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55));
        dest[destOffset + 6] = Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56));
        dest[destOffset + 7] = Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13)));
        dest[destOffset + 8] = Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54));
        dest[destOffset + 9] = Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55));
        return _t60;
    }

    /** Part 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_s2812e005_2(double[] dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _t15, double _t22, double _t44, double _t56, double _t58, double _t60) {
        dest[destOffset + 10] = Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56));
        dest[destOffset + 11] = Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23)));
        return dest;
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_s2812e005_3(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _t14, double _t15, double _t16, double _t22, double _upx, double _upy, double _upz, double _t23, double _t24) {
        double _t25 = Math.fma(_t23, _t16, _upy);
        double _t26 = Math.fma(_t23, _t15, _upz);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        double _t58 = Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45));
        return lookAt_lh_s2812e005_2(dest, destOffset, _self20, _self21, _self22, _self23, _t15, _t22, _t44, _t56, _t58, lookAt_lh_s2812e005_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _eyex, _eyey, _eyez, _t14, _t16, _t15, _t22, _t43, _t45, _t44, Math.fma(_t44, _t16, -(_t45 * _t15)), Math.fma(_t43, _t15, -(_t44 * _t14)), _t56, _t58));
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _t3 = center[centerOffset + 2] - _eyez;
        double _t4 = center[centerOffset] - _eyex;
        double _t5 = center[centerOffset + 1] - _eyey;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        return lookAt_rh_sdbe11fb_2(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _eyex, _eyey, _eyez, _t17, _t18, _t19, _upx, _upy, _upz, Math.fma(_t26, _t19, _upy), Math.fma(_t26, _t17, _upx), Math.fma(_t26, _t18, _upz));
    }

    /** Part 1 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_sdbe11fb_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t17, double _t19, double _t18, double _t25, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        dest[destOffset] = Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57));
        dest[destOffset + 1] = Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58));
        dest[destOffset + 2] = Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59));
        dest[destOffset + 3] = Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03)));
        dest[destOffset + 4] = Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57));
        dest[destOffset + 5] = Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58));
        dest[destOffset + 6] = Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59));
        dest[destOffset + 7] = Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13)));
        dest[destOffset + 8] = Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57));
        dest[destOffset + 9] = Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58));
        dest[destOffset + 10] = Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59));
        dest[destOffset + 11] = Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23)));
        return dest;
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_sdbe11fb_2(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _t17, double _t18, double _t19, double _upx, double _upy, double _upz, double _t27, double _t28, double _t29) {
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        return lookAt_rh_sdbe11fb_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, (-_self02), (-_self12), (-_self22), _t17, _t19, _t18, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)), _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
    }

    public static double[] makeLookAt_lh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -Math.fma(upZ, _t14, Math.fma(upX, _t15, upY * _t16));
        double _t21 = Math.fma(_t20, _t15, upX);
        double _t22 = Math.fma(_t20, _t16, upY);
        double _t23 = Math.fma(_t20, _t14, upZ);
        double _t30 = Math.fma(_t21, _t16, -(_t22 * _t15));
        double _t31 = Math.fma(_t22, _t14, -(_t23 * _t16));
        double _t32 = Math.fma(_t23, _t15, -(_t21 * _t14));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeLookAt_lh_sb695d1e4_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makeLookAt_lh_sb695d1e4_1(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = Math.fma(_t42, _t16, -(_t41 * _t14));
        double _t50 = Math.fma(_t40, _t14, -(_t42 * _t15));
        double _t51 = Math.fma(_t41, _t15, -(_t40 * _t16));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50));
        dest[destOffset + 8] = _t15;
        dest[destOffset + 9] = _t16;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = -Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16));
        return dest;
    }

    public static double[] makeLookAt_rh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -Math.fma(upZ, _t14, Math.fma(upX, _t15, upY * _t16));
        double _t21 = Math.fma(_t20, _t16, upY);
        double _t22 = Math.fma(_t20, _t15, upX);
        double _t23 = Math.fma(_t20, _t14, upZ);
        double _t30 = Math.fma(_t21, _t15, -(_t22 * _t16));
        double _t31 = Math.fma(_t22, _t14, -(_t23 * _t15));
        double _t32 = Math.fma(_t23, _t16, -(_t21 * _t14));
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeLookAt_rh_s1f6e482a_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makeLookAt_rh_s1f6e482a_1(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = Math.fma(_t41, _t14, -(_t42 * _t16));
        double _t50 = Math.fma(_t42, _t15, -(_t40 * _t14));
        double _t51 = Math.fma(_t40, _t16, -(_t41 * _t15));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50));
        dest[destOffset + 8] = -_t15;
        dest[destOffset + 9] = -_t16;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16));
        return dest;
    }

    public static double[] makeLookAt_lh(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t0 = center[centerOffset + 2] - _eyez;
        double _t1 = center[centerOffset] - _eyex;
        double _t2 = center[centerOffset + 1] - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -Math.fma(_upz, _t14, Math.fma(_upx, _t15, _upy * _t16));
        double _t21 = Math.fma(_t20, _t15, _upx);
        double _t22 = Math.fma(_t20, _t16, _upy);
        double _t23 = Math.fma(_t20, _t14, _upz);
        return makeLookAt_lh_scc2c3ffe_1(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t16, -(_t22 * _t15)), Math.fma(_t22, _t14, -(_t23 * _t16)), Math.fma(_t23, _t15, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makeLookAt_lh_scc2c3ffe_1(double[] dest, int destOffset, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = Math.fma(_t42, _t16, -(_t41 * _t14));
        double _t50 = Math.fma(_t40, _t14, -(_t42 * _t15));
        double _t51 = Math.fma(_t41, _t15, -(_t40 * _t16));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50));
        dest[destOffset + 8] = _t15;
        dest[destOffset + 9] = _t16;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = -Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16));
        return dest;
    }

    public static double[] makeLookAt_rh(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t0 = center[centerOffset + 2] - _eyez;
        double _t1 = center[centerOffset] - _eyex;
        double _t2 = center[centerOffset + 1] - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t20 = -Math.fma(_upz, _t14, Math.fma(_upx, _t15, _upy * _t16));
        double _t21 = Math.fma(_t20, _t16, _upy);
        double _t22 = Math.fma(_t20, _t15, _upx);
        double _t23 = Math.fma(_t20, _t14, _upz);
        return makeLookAt_rh_s7db9b09c_1(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t15, -(_t22 * _t16)), Math.fma(_t22, _t14, -(_t23 * _t15)), Math.fma(_t23, _t16, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makeLookAt_rh_s7db9b09c_1(double[] dest, int destOffset, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0;
            _t41 = 0.0;
            _t42 = 0.0;
        }
        double _t49 = Math.fma(_t41, _t14, -(_t42 * _t16));
        double _t50 = Math.fma(_t42, _t15, -(_t40 * _t14));
        double _t51 = Math.fma(_t40, _t16, -(_t41 * _t15));
        dest[destOffset] = _t40;
        dest[destOffset + 1] = _t41;
        dest[destOffset + 2] = _t42;
        dest[destOffset + 3] = -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41));
        dest[destOffset + 4] = _t49;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t51;
        dest[destOffset + 7] = -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50));
        dest[destOffset + 8] = -_t15;
        dest[destOffset + 9] = -_t16;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16));
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
