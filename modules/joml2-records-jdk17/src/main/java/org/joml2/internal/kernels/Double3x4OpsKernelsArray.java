// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

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
        double _t26 = (1.0 / Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dest[destOffset + 0] = -(_t21 * _t26);
            dest[destOffset + 1] = -(_t22 * _t26);
            dest[destOffset + 2] = -(_t20 * _t26);
        } else {
            dest[destOffset + 0] = -0.0;
            dest[destOffset + 1] = -0.0;
            dest[destOffset + 2] = -0.0;
        }
        return dest;
    }

    public static double[] invNegativeY_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _t26 = (1.0 / Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dest[destOffset + 0] = -(_t22 * _t26);
            dest[destOffset + 1] = -(_t21 * _t26);
            dest[destOffset + 2] = -(_t20 * _t26);
        } else {
            dest[destOffset + 0] = -0.0;
            dest[destOffset + 1] = -0.0;
            dest[destOffset + 2] = -0.0;
        }
        return dest;
    }

    public static double[] invNegativeZ_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _t26 = (1.0 / Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dest[destOffset + 0] = -(_t21 * _t26);
            dest[destOffset + 1] = -(_t22 * _t26);
            dest[destOffset + 2] = -(_t20 * _t26);
        } else {
            dest[destOffset + 0] = -0.0;
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
        double _t26 = (1.0 / Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dest[destOffset + 0] = _t21 * _t26;
            dest[destOffset + 1] = _t22 * _t26;
            dest[destOffset + 2] = _t20 * _t26;
        } else {
            dest[destOffset + 0] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
        }
        return dest;
    }

    public static double[] invPositiveY_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _t26 = (1.0 / Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dest[destOffset + 0] = _t22 * _t26;
            dest[destOffset + 1] = _t21 * _t26;
            dest[destOffset + 2] = _t20 * _t26;
        } else {
            dest[destOffset + 0] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
        }
        return dest;
    }

    public static double[] invPositiveZ_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _t26 = (1.0 / Math.sqrt(_t25));
        if (_t25 != 0.0) {
            dest[destOffset + 0] = _t21 * _t26;
            dest[destOffset + 1] = _t22 * _t26;
            dest[destOffset + 2] = _t20 * _t26;
        } else {
            dest[destOffset + 0] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
        }
        return dest;
    }

    public static double[] invert_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _t23 = _self01 * _t2;
        return invert_degenerate_sa19c02d6_1(dest, destOffset, _t0, _t1, _t2, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _self00 * _t2, _t23, _self23 * _t1, _self13 * _t0, _self03 * _t2, Math.fma(_t15, _t16, -(_t17 * _t18)), Math.fma(_t19, _t18, -(_t15 * _t20)), Math.fma(_t21, _t18, -(_t23 * _t16)), Math.fma(_t23, _t17, -(_t21 * _t15)), Math.fma(_t17, _t20, -(_t19 * _t16)));
    }

    /** Piece 2 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invert_degenerate_sa19c02d6_1(double[] dest, int destOffset, double _t0, double _t1, double _t2, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t47, double _t48, double _t50, double _t51, double _t52) {
        double _t53 = Math.fma(_t22, _t16, -(_t21 * _t20));
        double _t54 = Math.fma(_t21, _t19, -(_t22 * _t17));
        double _t55 = Math.fma(_t23, _t20, -(_t22 * _t18));
        double _t56 = Math.fma(_t22, _t15, -(_t23 * _t19));
        double _t60_inv = 1.0 / Math.fma(_t48, _t21, Math.fma(_t47, _t22, -(Math.fma(_t19, _t16, -(_t17 * _t20)) * _t23)));
        double _sp2 = _t1 * _t60_inv;
        double _sp1 = _t0 * _t60_inv;
        double _sp0 = _t2 * _t60_inv;
        dest[destOffset + 0] = _t47 * _sp0;
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
        double _other00 = other[otherOffset + 0];
        double _other01 = other[otherOffset + 1];
        double _other02 = other[otherOffset + 2];
        double _other10 = other[otherOffset + 4];
        double _other11 = other[otherOffset + 5];
        double _other12 = other[otherOffset + 6];
        double _other20 = other[otherOffset + 8];
        double _other21 = other[otherOffset + 9];
        double _other22 = other[otherOffset + 10];
        return invertProduct_degenerate_s13d521b0_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _self10, _self11, _self12, src[srcOffset + 7], _self20, _self21, _self22, src[srcOffset + 11], _other00, _other01, _other02, other[otherOffset + 3], _other10, _other11, _other12, other[otherOffset + 7], _other20, _other21, _other22, other[otherOffset + 11], Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)), Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11)), Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11)), Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21)));
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
        dest[destOffset + 0] = _t83 * _sp0;
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

    public static double[] transpose_scalar(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src[srcOffset + _i];
            dest[destOffset + _i] = _eself;
        }
        return dest;
    }

    public static double[] add_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src[srcOffset + _i];
            double _eother = other[otherOffset + _i];
            dest[destOffset + _i] = _eother + _eself;
        }
        return dest;
    }

    public static double[] mul_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src[srcOffset + _i];
            dest[destOffset + _i] = scalar * _eself;
        }
        return dest;
    }

    public static double[] negate_scalar(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src[srcOffset + _i];
            dest[destOffset + _i] = -_eself;
        }
        return dest;
    }

    public static double[] sub_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src[srcOffset + _i];
            double _eother = other[otherOffset + _i];
            dest[destOffset + _i] = _eself - _eother;
        }
        return dest;
    }

    public static double[] set_scalar(double[] dest, int destOffset, double[] v, int vOffset) {
        for (int _i = 0; _i < 12; _i++) {
            double _ev = v[vOffset + _i];
            dest[destOffset + _i] = _ev;
        }
        return dest;
    }

    public static double[] withTranslation_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double tX, double tY, double tZ) {
        double _self00 = src[srcOffset + 0];
        double _self01 = src[srcOffset + 1];
        double _self02 = src[srcOffset + 2];
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 6];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = tX;
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self12;
        dest[destOffset + 7] = tY;
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = tZ;
        return dest;
    }

    public static double[] withTranslation_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] t, int tOffset) {
        double _self00 = src[srcOffset + 0];
        double _self01 = src[srcOffset + 1];
        double _self02 = src[srcOffset + 2];
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 6];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _tx = t[tOffset + 0];
        double _ty = t[tOffset + 1];
        double _tz = t[tOffset + 2];
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = _tx;
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self12;
        dest[destOffset + 7] = _ty;
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _tz;
        return dest;
    }

    public static double[] toRigid_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _t30 = (1.0 / Math.sqrt(_t29));
        double _t31 = (1.0 / Math.sqrt(_t28));
        double _t32 = (1.0 / Math.sqrt(_t27));
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
        if (Math.abs(_t33) < Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0;
            _t87 = -_t34;
        } else {
            _t72 = 0.0;
            _t75 = -_t35;
            _t87 = _t33;
        }
        double _t73, _t76, _t88;
        if (Math.abs(_t37) < Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0;
            _t88 = -_t38;
        } else {
            _t73 = 0.0;
            _t76 = -_t36;
            _t88 = _t37;
        }
        double _t74, _t77, _t89;
        if (Math.abs(_t40) < Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0;
            _t89 = -_t39;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
            _t89 = _t40;
        }
        double _t99 = (1.0 / Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
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
        double _sp0 = 0.5 * (1.0 / Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t210));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t208));
        if (_t206 > 0.0) {
            dest[destOffset + 3] = _sp0 * _t182;
            dest[destOffset + 4] = _sp0 * _t201;
            dest[destOffset + 5] = _sp0 * _t202;
            dest[destOffset + 6] = 0.5 * Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest[destOffset + 3] = 0.5 * Math.sqrt(_t208);
                dest[destOffset + 4] = _sp3 * _t199;
                dest[destOffset + 5] = _sp3 * _t200;
                dest[destOffset + 6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dest[destOffset + 3] = _sp1 * _t199;
                    dest[destOffset + 4] = 0.5 * Math.sqrt(_t209);
                    dest[destOffset + 5] = _sp1 * _t184;
                    dest[destOffset + 6] = _sp1 * _t201;
                } else {
                    dest[destOffset + 3] = _sp2 * _t200;
                    dest[destOffset + 4] = _sp2 * _t184;
                    dest[destOffset + 5] = 0.5 * Math.sqrt(_t210);
                    dest[destOffset + 6] = _sp2 * _t202;
                }
            }
        }
        dest[destOffset + 0] = _self03;
        dest[destOffset + 1] = _self13;
        dest[destOffset + 2] = _self23;
        return dest;
    }

    public static double[] toTransform_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _t30 = (1.0 / Math.sqrt(_t29));
        double _t31 = (1.0 / Math.sqrt(_t28));
        double _t32 = (1.0 / Math.sqrt(_t27));
        double _t35 = _t30 * _t18;
        double _t36 = _t30 * _t19;
        double _t37 = _t30 * _t20;
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t56 = _t29 <= 0.0 ? 0.0 : Math.sqrt(_t29) / _t2;
        double _t75, _t78, _t90;
        if (Math.abs(_t35) < Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0;
            _t90 = -_t36;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
            _t90 = _t35;
        }
        double _t76, _t79, _t91;
        if (Math.abs(_t39) < Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0;
            _t91 = -_t40;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
            _t91 = _t39;
        }
        double _t77, _t80, _t92;
        if (Math.abs(_t42) < Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0;
            _t92 = -_t41;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
            _t92 = _t42;
        }
        double _t102 = (1.0 / Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
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
        double _t186 = Math.max(_t170, _t174);
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
        double _sp0 = 0.5 * (1.0 / Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / Math.sqrt(_t213));
        double _sp3 = 0.5 * (1.0 / Math.sqrt(_t211));
        dest[destOffset + 0] = _self03;
        dest[destOffset + 1] = _self13;
        dest[destOffset + 2] = _self23;
        dest[destOffset + 3] = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dest[destOffset + 4] = _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * Math.sqrt(_t212) : _sp2 * _t187;
        dest[destOffset + 5] = _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * Math.sqrt(_t213);
        dest[destOffset + 6] = _t209 > 0.0 ? 0.5 * Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dest[destOffset + 7] = _t196 < 0.0 ? -_t56 : _t56;
        dest[destOffset + 8] = _t27 <= 0.0 ? 0.0 : Math.sqrt(_t27) / _t0;
        dest[destOffset + 9] = _t28 <= 0.0 ? 0.0 : Math.sqrt(_t28) / _t1;
        return dest;
    }

    public static double[] lerp_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src[srcOffset + _i];
            double _eother = other[otherOffset + _i];
            dest[destOffset + _i] = Math.fma(t, _eother - _eself, _eself);
        }
        return dest;
    }

    public static double[] mul_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _right00 = right[rightOffset + 0];
        double _right01 = right[rightOffset + 1];
        double _right02 = right[rightOffset + 2];
        double _right03 = right[rightOffset + 3];
        double _right10 = right[rightOffset + 4];
        double _right11 = right[rightOffset + 5];
        double _right12 = right[rightOffset + 6];
        double _right13 = right[rightOffset + 7];
        double _right20 = right[rightOffset + 8];
        double _right21 = right[rightOffset + 9];
        double _right22 = right[rightOffset + 10];
        double _right23 = right[rightOffset + 11];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            double _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1));
            dest[destOffset + _lo + 1] = Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1));
            dest[destOffset + _lo + 2] = Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1));
            dest[destOffset + _lo + 3] = Math.fma(_right03, _eself0, Math.fma(_right13, _eself1, Math.fma(_right23, _eself2, _eself3)));
        }
        return dest;
    }

    public static double[] mulMat2x3_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _right00 = right[rightOffset + 0];
        double _right10 = right[rightOffset + 1];
        double _right01 = right[rightOffset + 2];
        double _right11 = right[rightOffset + 3];
        double _right02 = right[rightOffset + 4];
        double _right12 = right[rightOffset + 5];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            double _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = Math.fma(_right00, _eself0, _right10 * _eself1);
            dest[destOffset + _lo + 1] = Math.fma(_right01, _eself0, _right11 * _eself1);
            dest[destOffset + _lo + 2] = _eself2;
            dest[destOffset + _lo + 3] = Math.fma(_right02, _eself0, Math.fma(_right12, _eself1, _eself3));
        }
        return dest;
    }

    public static double[] mulMat3x3_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _right00 = right[rightOffset + 0];
        double _right10 = right[rightOffset + 1];
        double _right20 = right[rightOffset + 2];
        double _right01 = right[rightOffset + 3];
        double _right11 = right[rightOffset + 4];
        double _right21 = right[rightOffset + 5];
        double _right02 = right[rightOffset + 6];
        double _right12 = right[rightOffset + 7];
        double _right22 = right[rightOffset + 8];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            double _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1));
            dest[destOffset + _lo + 1] = Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1));
            dest[destOffset + _lo + 2] = Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1));
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    public static double[] mulMat4x4_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _self00 = src[srcOffset + 0];
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
        for (int _l = 0; _l < 4; _l++) {
            int _lo = _l * 4;
            double _eright0 = right[rightOffset + _lo];
            double _eright1 = right[rightOffset + _lo + 1];
            double _eright2 = right[rightOffset + _lo + 2];
            double _eright3 = right[rightOffset + _lo + 3];
            dest[destOffset + _lo] = Math.fma(_eright3, _self03, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest[destOffset + _lo + 1] = Math.fma(_eright3, _self13, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest[destOffset + _lo + 2] = Math.fma(_eright3, _self23, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21)));
            dest[destOffset + _lo + 3] = _eright3;
        }
        return dest;
    }

    public static double[] preMul_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self00 = src[srcOffset + 0];
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
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eother0 = other[otherOffset + _lo];
            double _eother1 = other[otherOffset + _lo + 1];
            double _eother2 = other[otherOffset + _lo + 2];
            double _eother3 = other[otherOffset + _lo + 3];
            dest[destOffset + _lo] = Math.fma(_eother2, _self20, Math.fma(_eother0, _self00, _eother1 * _self10));
            dest[destOffset + _lo + 1] = Math.fma(_eother2, _self21, Math.fma(_eother0, _self01, _eother1 * _self11));
            dest[destOffset + _lo + 2] = Math.fma(_eother2, _self22, Math.fma(_eother0, _self02, _eother1 * _self12));
            dest[destOffset + _lo + 3] = Math.fma(_eother0, _self03, Math.fma(_eother1, _self13, Math.fma(_eother2, _self23, _eother3)));
        }
        return dest;
    }

    public static double[] preMulMat2x2_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _other00 = other[otherOffset + 0];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        dest[destOffset + 0] = Math.fma(_other00, _self00, _other01 * _self10);
        dest[destOffset + 1] = Math.fma(_other00, _self01, _other01 * _self11);
        dest[destOffset + 2] = Math.fma(_other00, _self02, _other01 * _self12);
        dest[destOffset + 3] = Math.fma(_other00, _self03, _other01 * _self13);
        dest[destOffset + 4] = Math.fma(_other10, _self00, _other11 * _self10);
        dest[destOffset + 5] = Math.fma(_other10, _self01, _other11 * _self11);
        dest[destOffset + 6] = Math.fma(_other10, _self02, _other11 * _self12);
        dest[destOffset + 7] = Math.fma(_other10, _self03, _other11 * _self13);
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static double[] preMulMat2x3_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _other00 = other[otherOffset + 0];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        double _other02 = other[otherOffset + 4];
        double _other12 = other[otherOffset + 5];
        dest[destOffset + 0] = Math.fma(_other00, _self00, _other01 * _self10);
        dest[destOffset + 1] = Math.fma(_other00, _self01, _other01 * _self11);
        dest[destOffset + 2] = Math.fma(_other00, _self02, _other01 * _self12);
        dest[destOffset + 3] = Math.fma(_other00, _self03, Math.fma(_other01, _self13, _other02));
        dest[destOffset + 4] = Math.fma(_other10, _self00, _other11 * _self10);
        dest[destOffset + 5] = Math.fma(_other10, _self01, _other11 * _self11);
        dest[destOffset + 6] = Math.fma(_other10, _self02, _other11 * _self12);
        dest[destOffset + 7] = Math.fma(_other10, _self03, Math.fma(_other11, _self13, _other12));
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static double[] preMulMat3x3_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _other00 = other[otherOffset + 0];
        double _other10 = other[otherOffset + 1];
        double _other20 = other[otherOffset + 2];
        double _other01 = other[otherOffset + 3];
        double _other11 = other[otherOffset + 4];
        double _other21 = other[otherOffset + 5];
        double _other02 = other[otherOffset + 6];
        double _other12 = other[otherOffset + 7];
        double _other22 = other[otherOffset + 8];
        dest[destOffset + 0] = Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10));
        dest[destOffset + 1] = Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11));
        dest[destOffset + 2] = Math.fma(_other02, _self22, Math.fma(_other00, _self02, _other01 * _self12));
        return preMulMat3x3_scalar_se8539c5a_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] preMulMat3x3_scalar_se8539c5a_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        dest[destOffset + 3] = Math.fma(_other02, _self23, Math.fma(_other00, _self03, _other01 * _self13));
        dest[destOffset + 4] = Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10));
        dest[destOffset + 5] = Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11));
        dest[destOffset + 6] = Math.fma(_other12, _self22, Math.fma(_other10, _self02, _other11 * _self12));
        dest[destOffset + 7] = Math.fma(_other12, _self23, Math.fma(_other10, _self03, _other11 * _self13));
        dest[destOffset + 8] = Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10));
        dest[destOffset + 9] = Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11));
        dest[destOffset + 10] = Math.fma(_other22, _self22, Math.fma(_other20, _self02, _other21 * _self12));
        dest[destOffset + 11] = Math.fma(_other22, _self23, Math.fma(_other20, _self03, _other21 * _self13));
        return dest;
    }

    public static double[] preMulMat4x4_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        return preMulMat4x4_scalar_s94908bf2_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], other[otherOffset + 0], other[otherOffset + 1], other[otherOffset + 2], other[otherOffset + 3], other[otherOffset + 4], other[otherOffset + 5], other[otherOffset + 6], other[otherOffset + 7], other[otherOffset + 8], other[otherOffset + 9], other[otherOffset + 10], other[otherOffset + 11], other[otherOffset + 12], other[otherOffset + 13], other[otherOffset + 14], other[otherOffset + 15]);
    }

    /** Piece 2 of {@code preMulMat4x4_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] preMulMat4x4_scalar_s94908bf2_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other10, double _other20, double _other30, double _other01, double _other11, double _other21, double _other31, double _other02, double _other12, double _other22, double _other32, double _other03, double _other13, double _other23, double _other33) {
        dest[destOffset + 0] = Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10));
        dest[destOffset + 1] = Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10));
        dest[destOffset + 2] = Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10));
        dest[destOffset + 3] = Math.fma(_other32, _self20, Math.fma(_other30, _self00, _other31 * _self10));
        dest[destOffset + 4] = Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11));
        dest[destOffset + 5] = Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11));
        dest[destOffset + 6] = Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11));
        dest[destOffset + 7] = Math.fma(_other32, _self21, Math.fma(_other30, _self01, _other31 * _self11));
        dest[destOffset + 8] = Math.fma(_other02, _self22, Math.fma(_other00, _self02, _other01 * _self12));
        dest[destOffset + 9] = Math.fma(_other12, _self22, Math.fma(_other10, _self02, _other11 * _self12));
        return preMulMat4x4_scalar_s94908bf2_2(dest, destOffset, _self02, _self03, _self12, _self13, _self22, _self23, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22, _other32, _other03, _other13, _other23, _other33);
    }

    /** Piece 3 of {@code preMulMat4x4_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] preMulMat4x4_scalar_s94908bf2_2(double[] dest, int destOffset, double _self02, double _self03, double _self12, double _self13, double _self22, double _self23, double _other00, double _other10, double _other20, double _other30, double _other01, double _other11, double _other21, double _other31, double _other02, double _other12, double _other22, double _other32, double _other03, double _other13, double _other23, double _other33) {
        dest[destOffset + 10] = Math.fma(_other22, _self22, Math.fma(_other20, _self02, _other21 * _self12));
        dest[destOffset + 11] = Math.fma(_other32, _self22, Math.fma(_other30, _self02, _other31 * _self12));
        dest[destOffset + 12] = Math.fma(_other00, _self03, Math.fma(_other01, _self13, Math.fma(_other02, _self23, _other03)));
        dest[destOffset + 13] = Math.fma(_other10, _self03, Math.fma(_other11, _self13, Math.fma(_other12, _self23, _other13)));
        dest[destOffset + 14] = Math.fma(_other20, _self03, Math.fma(_other21, _self13, Math.fma(_other22, _self23, _other23)));
        dest[destOffset + 15] = Math.fma(_other30, _self03, Math.fma(_other31, _self13, Math.fma(_other32, _self23, _other33)));
        return dest;
    }

    public static double[] addScaled_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src[srcOffset + _i];
            double _eother = other[otherOffset + _i];
            dest[destOffset + _i] = Math.fma(weight, _eother, _eself);
        }
        return dest;
    }

    public static double[] composeTRSMul_scalar(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _t0 = scaleZ + scaleZ;
        double _t1 = scaleX + scaleX;
        double _t2 = scaleY + scaleY;
        double _t3 = rotationY * rotationW;
        double _t4 = rotationZ * rotationZ;
        double _t5 = rotationZ * rotationW;
        return composeTRSMul_scalar_s68e9003e_1(dest, destOffset, translationX, translationY, translationZ, m[mOffset + 0], m[mOffset + 1], m[mOffset + 2], m[mOffset + 3], m[mOffset + 4], m[mOffset + 5], m[mOffset + 6], m[mOffset + 7], m[mOffset + 8], m[mOffset + 9], m[mOffset + 10], m[mOffset + 11], Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
    }

    /** Piece 2 of {@code composeTRSMul_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] composeTRSMul_scalar_s68e9003e_1(double[] dest, int destOffset, double translationX, double translationY, double translationZ, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32) {
        dest[destOffset + 0] = Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27));
        dest[destOffset + 1] = Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27));
        dest[destOffset + 2] = Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27));
        dest[destOffset + 3] = Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, translationX)));
        dest[destOffset + 4] = Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31));
        dest[destOffset + 5] = Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31));
        dest[destOffset + 6] = Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31));
        dest[destOffset + 7] = Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, translationY)));
        dest[destOffset + 8] = Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26));
        dest[destOffset + 9] = Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26));
        dest[destOffset + 10] = Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26));
        dest[destOffset + 11] = Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, translationZ)));
        return dest;
    }

    public static double[] composeTRSMul_scalar(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset) {
        double _rotationx = rotation[rotationOffset + 0];
        double _rotationy = rotation[rotationOffset + 1];
        double _rotationz = rotation[rotationOffset + 2];
        double _rotationw = rotation[rotationOffset + 3];
        double _scalex = scale[scaleOffset + 0];
        double _scaley = scale[scaleOffset + 1];
        double _scalez = scale[scaleOffset + 2];
        double _t0 = _scalez + _scalez;
        double _t1 = _scalex + _scalex;
        double _t2 = _scaley + _scaley;
        double _t3 = _rotationy * _rotationw;
        double _t5 = _rotationz * _rotationw;
        return composeTRSMul_scalar_sa5f27970_1(dest, destOffset, translation[translationOffset + 0], translation[translationOffset + 1], translation[translationOffset + 2], _rotationx, _rotationy, _rotationz, _scalex, _scaley, _scalez, m[mOffset + 0], m[mOffset + 1], m[mOffset + 2], m[mOffset + 3], m[mOffset + 4], m[mOffset + 5], m[mOffset + 6], m[mOffset + 7], m[mOffset + 8], m[mOffset + 9], m[mOffset + 10], m[mOffset + 11], _t0, _t1, _t2, _t3, _rotationz * _rotationz, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0);
    }

    /** Piece 2 of {@code composeTRSMul_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] composeTRSMul_scalar_sa5f27970_1(double[] dest, int destOffset, double _translationx, double _translationy, double _translationz, double _rotationx, double _rotationy, double _rotationz, double _scalex, double _scaley, double _scalez, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t0, double _t1, double _t2, double _t3, double _t4, double _t24, double _t25, double _t26, double _t27, double _t28) {
        double _t30 = Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex);
        double _t31 = Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley);
        dest[destOffset + 0] = Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27));
        dest[destOffset + 1] = Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27));
        dest[destOffset + 2] = Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27));
        dest[destOffset + 3] = Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, _translationx)));
        dest[destOffset + 4] = Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31));
        dest[destOffset + 5] = Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31));
        dest[destOffset + 6] = Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31));
        dest[destOffset + 7] = Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, _translationy)));
        return composeTRSMul_scalar_sa5f27970_2(dest, destOffset, _translationz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t26, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez));
    }

    /** Piece 3 of {@code composeTRSMul_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] composeTRSMul_scalar_sa5f27970_2(double[] dest, int destOffset, double _translationz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t26, double _t29, double _t32) {
        dest[destOffset + 8] = Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26));
        dest[destOffset + 9] = Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26));
        dest[destOffset + 10] = Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26));
        dest[destOffset + 11] = Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, _translationz)));
        return dest;
    }

    public static double[] lookAlong_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / Math.sqrt(_t4));
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
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t12, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        return lookAlong_scalar_sea4cc172_1(dest, destOffset, upX, upY, upZ, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_scalar_sea4cc172_1(double[] dest, int destOffset, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = Math.fma(_t39, _t12, -(_t38 * _t13));
        double _t47 = Math.fma(_t37, _t13, -(_t39 * _t11));
        double _t48 = Math.fma(_t38, _t11, -(_t37 * _t12));
        dest[destOffset + 0] = Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39));
        dest[destOffset + 1] = Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48));
        dest[destOffset + 2] = Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39));
        dest[destOffset + 5] = Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48));
        dest[destOffset + 6] = Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13));
        dest[destOffset + 7] = _self13;
        return lookAlong_scalar_sea4cc172_2(dest, destOffset, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code lookAlong_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_scalar_sea4cc172_2(double[] dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        dest[destOffset + 8] = Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39));
        dest[destOffset + 9] = Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48));
        dest[destOffset + 10] = Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static double[] lookAlong_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset + 0];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset + 0];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / Math.sqrt(_t4));
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
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        return lookAlong_scalar_s89f4dbe6_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _upx, _upy, _upz, _t11, _t12, _t13, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)), Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_scalar_s89f4dbe6_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = Math.fma(_t39, _t12, -(_t38 * _t13));
        double _t47 = Math.fma(_t37, _t13, -(_t39 * _t11));
        double _t48 = Math.fma(_t38, _t11, -(_t37 * _t12));
        dest[destOffset + 0] = Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39));
        dest[destOffset + 1] = Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48));
        dest[destOffset + 2] = Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39));
        return lookAlong_scalar_s89f4dbe6_2(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code lookAlong_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_scalar_s89f4dbe6_2(double[] dest, int destOffset, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
        dest[destOffset + 5] = Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48));
        dest[destOffset + 6] = Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39));
        dest[destOffset + 9] = Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48));
        dest[destOffset + 10] = Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (SimdSupport.VECTOR_API) return Double3x4OpsSimd.lookAt_lh(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Double3x4OpsKernelsArray.lookAt_lh_scalar(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static double[] lookAt_lh_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / Math.sqrt(_t9));
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
        return lookAt_lh_scalar_s12764612_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), Math.fma(_t24, _t16, -(_t25 * _t14)), Math.fma(_t25, _t15, -(_t26 * _t16)), Math.fma(_t26, _t14, -(_t24 * _t15)));
    }

    /** Piece 2 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_scalar_s12764612_1(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t22, double _t33, double _t34, double _t35) {
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / Math.sqrt(_t38));
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
        dest[destOffset + 0] = Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54));
        dest[destOffset + 1] = Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55));
        dest[destOffset + 2] = Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56));
        return lookAt_lh_scalar_s12764612_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)), Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
    }

    /** Piece 3 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_scalar_s12764612_2(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t58, double _t60) {
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
        if (SimdSupport.VECTOR_API) return Double3x4OpsSimd.lookAt_rh(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Double3x4OpsKernelsArray.lookAt_rh_scalar(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static double[] lookAt_rh_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _self02 = src[srcOffset + 2];
        double _self12 = src[srcOffset + 6];
        double _self22 = src[srcOffset + 10];
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / Math.sqrt(_t12));
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
        return lookAt_rh_scalar_s129e8284_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset + 0], src[srcOffset + 1], _self02, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self12, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], -_self02, -_self12, -_self22, _t17, _t18, _t19, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)), _t27, _t28, Math.fma(_t26, _t18, upZ), Math.fma(_t27, _t17, -(_t28 * _t19)));
    }

    /** Piece 2 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_scalar_s129e8284_1(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25, double _t27, double _t28, double _t29, double _t36) {
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        double _t42 = (1.0 / Math.sqrt(_t41));
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
        dest[destOffset + 0] = Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57));
        return lookAt_rh_scalar_s129e8284_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)), Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58)));
    }

    /** Piece 3 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_scalar_s129e8284_2(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
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

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        if (SimdSupport.VECTOR_API) return Double3x4OpsSimd.lookAt_lh(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Double3x4OpsKernelsArray.lookAt_lh_scalar(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static double[] lookAt_lh_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyex = eye[eyeOffset + 0];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _centerx = center[centerOffset + 0];
        double _centery = center[centerOffset + 1];
        double _centerz = center[centerOffset + 2];
        double _upx = up[upOffset + 0];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t0 = _centerz - _eyez;
        double _t1 = _centerx - _eyex;
        double _t2 = _centery - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / Math.sqrt(_t9));
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
        return lookAt_lh_scalar_s27351174_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16)));
    }

    /** Piece 2 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_scalar_s27351174_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t22, double _t23) {
        double _t24 = Math.fma(_t23, _t14, _upx);
        double _t25 = Math.fma(_t23, _t16, _upy);
        double _t26 = Math.fma(_t23, _t15, _upz);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / Math.sqrt(_t38));
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
        return lookAt_lh_scalar_s27351174_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _t14, _t15, _t16, _t22, _t43, _t44, _t45, Math.fma(_t44, _t16, -(_t45 * _t15)), Math.fma(_t43, _t15, -(_t44 * _t14)), Math.fma(_t45, _t14, -(_t43 * _t16)), Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
    }

    /** Piece 3 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_scalar_s27351174_2(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t58) {
        double _t60 = Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55));
        dest[destOffset + 0] = Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54));
        dest[destOffset + 1] = Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55));
        dest[destOffset + 2] = Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56));
        dest[destOffset + 3] = Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03)));
        dest[destOffset + 4] = Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54));
        dest[destOffset + 5] = Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55));
        dest[destOffset + 6] = Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56));
        dest[destOffset + 7] = Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13)));
        dest[destOffset + 8] = Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54));
        dest[destOffset + 9] = Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55));
        return lookAt_lh_scalar_s27351174_3(dest, destOffset, _self20, _self21, _self22, _self23, _t15, _t22, _t44, _t56, _t58, _t60);
    }

    /** Piece 4 of {@code lookAt_lh_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_scalar_s27351174_3(double[] dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _t15, double _t22, double _t44, double _t56, double _t58, double _t60) {
        dest[destOffset + 10] = Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56));
        dest[destOffset + 11] = Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23)));
        return dest;
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        if (SimdSupport.VECTOR_API) return Double3x4OpsSimd.lookAt_rh(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Double3x4OpsKernelsArray.lookAt_rh_scalar(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static double[] lookAt_rh_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _self02 = src[srcOffset + 2];
        double _self12 = src[srcOffset + 6];
        double _self22 = src[srcOffset + 10];
        double _eyex = eye[eyeOffset + 0];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _centerx = center[centerOffset + 0];
        double _centery = center[centerOffset + 1];
        double _centerz = center[centerOffset + 2];
        double _t3 = _centerz - _eyez;
        double _t4 = _centerx - _eyex;
        double _t5 = _centery - _eyey;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / Math.sqrt(_t12));
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
        return lookAt_rh_scalar_sf289589e_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], _self02, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self12, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], _eyex, _eyey, _eyez, up[upOffset + 0], up[upOffset + 1], up[upOffset + 2], -_self02, -_self12, -_self22, _t17, _t18, _t19, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
    }

    /** Piece 2 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_scalar_sf289589e_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t0, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25) {
        double _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        double _t27 = Math.fma(_t26, _t19, _upy);
        double _t28 = Math.fma(_t26, _t17, _upx);
        double _t29 = Math.fma(_t26, _t18, _upz);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        double _t42 = (1.0 / Math.sqrt(_t41));
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
        return lookAt_rh_scalar_sf289589e_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _t0, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, Math.fma(_t47, _t18, -(_t48 * _t19)), Math.fma(_t48, _t17, -(_t46 * _t18)), Math.fma(_t46, _t19, -(_t47 * _t17)));
    }

    /** Piece 3 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_scalar_sf289589e_2(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _t0, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59) {
        double _t61 = Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47));
        double _t63 = Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58));
        dest[destOffset + 0] = Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57));
        dest[destOffset + 1] = Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58));
        dest[destOffset + 2] = Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59));
        dest[destOffset + 3] = Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03)));
        dest[destOffset + 4] = Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57));
        dest[destOffset + 5] = Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58));
        dest[destOffset + 6] = Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59));
        dest[destOffset + 7] = Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13)));
        dest[destOffset + 8] = Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57));
        return lookAt_rh_scalar_sf289589e_3(dest, destOffset, _self20, _self21, _self22, _self23, _t2, _t18, _t19, _t25, _t47, _t48, _t58, _t59, _t61, _t63);
    }

    /** Piece 4 of {@code lookAt_rh_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_scalar_sf289589e_3(double[] dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _t2, double _t18, double _t19, double _t25, double _t47, double _t48, double _t58, double _t59, double _t61, double _t63) {
        dest[destOffset + 9] = Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58));
        dest[destOffset + 10] = Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59));
        dest[destOffset + 11] = Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23)));
        return dest;
    }

    public static double[] makeLookAt_lh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / Math.sqrt(_t9));
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
        return makeLookAt_lh_sb695d1e4_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / Math.sqrt(_t35)));
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
        dest[destOffset + 0] = _t40;
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
        double _t10 = (1.0 / Math.sqrt(_t9));
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
        return makeLookAt_rh_s1f6e482a_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / Math.sqrt(_t35)));
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
        dest[destOffset + 0] = _t40;
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
        double _eyex = eye[eyeOffset + 0];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _centerx = center[centerOffset + 0];
        double _centery = center[centerOffset + 1];
        double _centerz = center[centerOffset + 2];
        double _upx = up[upOffset + 0];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t0 = _centerz - _eyez;
        double _t1 = _centerx - _eyex;
        double _t2 = _centery - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / Math.sqrt(_t9));
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
        double _t36 = (1.0 / Math.sqrt(_t35));
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
        dest[destOffset + 0] = _t40;
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
        double _eyex = eye[eyeOffset + 0];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _centerx = center[centerOffset + 0];
        double _centery = center[centerOffset + 1];
        double _centerz = center[centerOffset + 2];
        double _upx = up[upOffset + 0];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t0 = _centerz - _eyez;
        double _t1 = _centerx - _eyex;
        double _t2 = _centery - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / Math.sqrt(_t9));
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
        double _t36 = (1.0 / Math.sqrt(_t35));
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
        dest[destOffset + 0] = _t40;
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

    public static double[] mapXYZ_scalar(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src[srcOffset + _i];
            dest[destOffset + _i] = _eself;
        }
        return dest;
    }

    public static double[] mapXYnZ_scalar(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            double _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = _eself0;
            dest[destOffset + _lo + 1] = _eself1;
            dest[destOffset + _lo + 2] = -_eself2;
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    public static double[] mapXnYZ_scalar(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            double _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = _eself0;
            dest[destOffset + _lo + 1] = -_eself1;
            dest[destOffset + _lo + 2] = _eself2;
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    public static double[] preRotateAround_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -rotY;
        double _t3 = -rotX;
        double _t4 = rotX + rotX;
        double _t5 = rotY + rotY;
        double _t6 = rotZ + rotZ;
        double _t7 = rotW * _t5;
        double _t8 = rotW * _t6;
        double _t10 = rotW * _t4;
        double _t14 = Math.fma(-rotZ, _t6, 1.0);
        return preRotateAround_scalar_sd55bc0f7_1(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], -pivotZ, _t4, _t5, rotZ * _t6, Math.fma(rotZ, _t4, _t7), Math.fma(rotY, _t4, _t8), Math.fma(rotZ, _t5, _t10), Math.fma(rotY, _t4, -_t8), Math.fma(rotZ, _t5, -_t10), Math.fma(rotZ, _t4, -_t7), Math.fma(_t0, _t5, _t14), Math.fma(_t3, _t4, _t14), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)));
    }

    /** Piece 2 of {@code preRotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] preRotateAround_scalar_sd55bc0f7_1(double[] dest, int destOffset, double rotX, double rotY, double pivotX, double pivotY, double pivotZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t2, double _t4, double _t5, double _t9, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        dest[destOffset + 0] = Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19));
        dest[destOffset + 1] = Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19));
        dest[destOffset + 2] = Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19));
        dest[destOffset + 3] = Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19)));
        dest[destOffset + 4] = Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23));
        dest[destOffset + 5] = Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23));
        dest[destOffset + 6] = Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23));
        dest[destOffset + 7] = Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17)));
        return preRotateAround_scalar_sd55bc0f7_2(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t4, _t5, _t18, _t21, _t24);
    }

    /** Piece 3 of {@code preRotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] preRotateAround_scalar_sd55bc0f7_2(double[] dest, int destOffset, double rotX, double rotY, double pivotX, double pivotY, double pivotZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t4, double _t5, double _t18, double _t21, double _t24) {
        dest[destOffset + 8] = Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18));
        dest[destOffset + 9] = Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18));
        dest[destOffset + 10] = Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18));
        dest[destOffset + 11] = Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21)));
        return dest;
    }

    public static double[] preRotateAround_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _rotx = rot[rotOffset + 0];
        double _roty = rot[rotOffset + 1];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _pivotz = pivot[pivotOffset + 2];
        double _t0 = -_roty;
        double _t3 = -_rotx;
        double _t4 = _rotx + _rotx;
        double _t5 = _roty + _roty;
        double _t6 = _rotz + _rotz;
        double _t7 = _rotw * _t5;
        double _t8 = _rotw * _t6;
        double _t10 = _rotw * _t4;
        double _t14 = Math.fma(-_rotz, _t6, 1.0);
        return preRotateAround_scalar_sae33137_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _rotx, _roty, pivot[pivotOffset + 0], pivot[pivotOffset + 1], _pivotz, -_pivotz, _t4, _t5, _rotz * _t6, Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7), Math.fma(_t0, _t5, _t14), Math.fma(_t3, _t4, _t14), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)));
    }

    /** Piece 2 of {@code preRotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] preRotateAround_scalar_sae33137_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _pivotx, double _pivoty, double _pivotz, double _t2, double _t4, double _t5, double _t9, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        dest[destOffset + 0] = Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19));
        dest[destOffset + 1] = Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19));
        dest[destOffset + 2] = Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19));
        dest[destOffset + 3] = Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19)));
        dest[destOffset + 4] = Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23));
        dest[destOffset + 5] = Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23));
        dest[destOffset + 6] = Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23));
        dest[destOffset + 7] = Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17)));
        return preRotateAround_scalar_sae33137_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _pivotx, _pivoty, _pivotz, _t4, _t5, _t18, _t21, _t24);
    }

    /** Piece 3 of {@code preRotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] preRotateAround_scalar_sae33137_2(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _pivotx, double _pivoty, double _pivotz, double _t4, double _t5, double _t18, double _t21, double _t24) {
        dest[destOffset + 8] = Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18));
        dest[destOffset + 9] = Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18));
        dest[destOffset + 10] = Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18));
        dest[destOffset + 11] = Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21)));
        return dest;
    }

    public static double[] preRotateAxis_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        return preRotateAxis_scalar_sbea7a0a6_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, _t4, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(_t11, _t2, -(axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] preRotateAxis_scalar_sbea7a0a6_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest[destOffset + 0] = Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24));
        dest[destOffset + 1] = Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24));
        dest[destOffset + 2] = Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24));
        dest[destOffset + 3] = Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24));
        dest[destOffset + 4] = Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19));
        dest[destOffset + 5] = Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19));
        dest[destOffset + 6] = Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19));
        dest[destOffset + 7] = Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19));
        dest[destOffset + 8] = Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23));
        dest[destOffset + 9] = Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23));
        dest[destOffset + 10] = Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23));
        dest[destOffset + 11] = Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23));
        return dest;
    }

    public static double[] preRotateAxis_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _axisx = axis[axisOffset + 0];
        double _axisy = axis[axisOffset + 1];
        double _axisz = axis[axisOffset + 2];
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t4 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        return preRotateAxis_scalar_se345b508_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_axisz, _t0, _t11 * _t4), Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_t11, _t4, -(_axisz * _t0)), Math.fma(_t11, _t6, -(_axisx * _t0)), Math.fma(_t11, _t2, -(_axisy * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] preRotateAxis_scalar_se345b508_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest[destOffset + 0] = Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24));
        dest[destOffset + 1] = Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24));
        dest[destOffset + 2] = Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24));
        dest[destOffset + 3] = Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24));
        dest[destOffset + 4] = Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19));
        dest[destOffset + 5] = Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19));
        dest[destOffset + 6] = Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19));
        dest[destOffset + 7] = Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19));
        dest[destOffset + 8] = Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23));
        dest[destOffset + 9] = Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23));
        dest[destOffset + 10] = Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23));
        dest[destOffset + 11] = Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23));
        return dest;
    }

    public static double[] preRotateQuat_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = -qY;
        double _t2 = -qX;
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        double _t6 = qW * _t4;
        double _t7 = qW * _t5;
        double _t8 = qW * _t3;
        double _t12 = Math.fma(-qZ, _t5, 1.0);
        return preRotateQuat_scalar_sdbf598b9_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], Math.fma(qZ, _t3, _t6), Math.fma(qY, _t3, _t7), Math.fma(qZ, _t4, _t8), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t4, -_t8), Math.fma(qZ, _t3, -_t6), Math.fma(_t0, _t4, _t12), Math.fma(_t2, _t3, _t12), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
    }

    /** Piece 2 of {@code preRotateQuat_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] preRotateQuat_scalar_sdbf598b9_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22) {
        dest[destOffset + 0] = Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17));
        dest[destOffset + 1] = Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17));
        dest[destOffset + 2] = Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17));
        dest[destOffset + 3] = Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17));
        dest[destOffset + 4] = Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21));
        dest[destOffset + 5] = Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21));
        dest[destOffset + 6] = Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21));
        dest[destOffset + 7] = Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21));
        dest[destOffset + 8] = Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16));
        dest[destOffset + 9] = Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16));
        dest[destOffset + 10] = Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16));
        dest[destOffset + 11] = Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16));
        return dest;
    }

    public static double[] preRotateQuat_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qx = q[qOffset + 0];
        double _qy = q[qOffset + 1];
        double _qz = q[qOffset + 2];
        double _qw = q[qOffset + 3];
        double _t0 = -_qy;
        double _t2 = -_qx;
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        double _t6 = _qw * _t4;
        double _t7 = _qw * _t5;
        double _t8 = _qw * _t3;
        double _t12 = Math.fma(-_qz, _t5, 1.0);
        return preRotateQuat_scalar_sf8d60118_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], Math.fma(_qz, _t3, _t6), Math.fma(_qy, _t3, _t7), Math.fma(_qz, _t4, _t8), Math.fma(_qy, _t3, -_t7), Math.fma(_qz, _t4, -_t8), Math.fma(_qz, _t3, -_t6), Math.fma(_t0, _t4, _t12), Math.fma(_t2, _t3, _t12), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
    }

    /** Piece 2 of {@code preRotateQuat_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] preRotateQuat_scalar_sf8d60118_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22) {
        dest[destOffset + 0] = Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17));
        dest[destOffset + 1] = Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17));
        dest[destOffset + 2] = Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17));
        dest[destOffset + 3] = Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17));
        dest[destOffset + 4] = Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21));
        dest[destOffset + 5] = Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21));
        dest[destOffset + 6] = Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21));
        dest[destOffset + 7] = Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21));
        dest[destOffset + 8] = Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16));
        dest[destOffset + 9] = Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16));
        dest[destOffset + 10] = Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16));
        dest[destOffset + 11] = Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16));
        return dest;
    }

    public static double[] preRotateX_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _self00 = src[srcOffset + 0];
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
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = Math.fma(_self10, _t1, -(_self20 * _t0));
        dest[destOffset + 5] = Math.fma(_self11, _t1, -(_self21 * _t0));
        dest[destOffset + 6] = Math.fma(_self12, _t1, -(_self22 * _t0));
        dest[destOffset + 7] = Math.fma(_self13, _t1, -(_self23 * _t0));
        dest[destOffset + 8] = Math.fma(_self10, _t0, _self20 * _t1);
        dest[destOffset + 9] = Math.fma(_self11, _t0, _self21 * _t1);
        dest[destOffset + 10] = Math.fma(_self12, _t0, _self22 * _t1);
        dest[destOffset + 11] = Math.fma(_self13, _t0, _self23 * _t1);
        return dest;
    }

    public static double[] preRotateY_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _self00 = src[srcOffset + 0];
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
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset + 0] = Math.fma(_self00, _t1, _self20 * _t0);
        dest[destOffset + 1] = Math.fma(_self01, _t1, _self21 * _t0);
        dest[destOffset + 2] = Math.fma(_self02, _t1, _self22 * _t0);
        dest[destOffset + 3] = Math.fma(_self03, _t1, _self23 * _t0);
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self12;
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = Math.fma(_self20, _t1, -(_self00 * _t0));
        dest[destOffset + 9] = Math.fma(_self21, _t1, -(_self01 * _t0));
        dest[destOffset + 10] = Math.fma(_self22, _t1, -(_self02 * _t0));
        dest[destOffset + 11] = Math.fma(_self23, _t1, -(_self03 * _t0));
        return dest;
    }

    public static double[] preRotateZ_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _self00 = src[srcOffset + 0];
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
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset + 0] = Math.fma(_self00, _t1, -(_self10 * _t0));
        dest[destOffset + 1] = Math.fma(_self01, _t1, -(_self11 * _t0));
        dest[destOffset + 2] = Math.fma(_self02, _t1, -(_self12 * _t0));
        dest[destOffset + 3] = Math.fma(_self03, _t1, -(_self13 * _t0));
        dest[destOffset + 4] = Math.fma(_self00, _t0, _self10 * _t1);
        dest[destOffset + 5] = Math.fma(_self01, _t0, _self11 * _t1);
        dest[destOffset + 6] = Math.fma(_self02, _t0, _self12 * _t1);
        dest[destOffset + 7] = Math.fma(_self03, _t0, _self13 * _t1);
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static double[] preScale_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        double _self00 = src[srcOffset + 0];
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
        dest[destOffset + 0] = _self00 * vX;
        dest[destOffset + 1] = _self01 * vX;
        dest[destOffset + 2] = _self02 * vX;
        dest[destOffset + 3] = _self03 * vX;
        dest[destOffset + 4] = _self10 * vY;
        dest[destOffset + 5] = _self11 * vY;
        dest[destOffset + 6] = _self12 * vY;
        dest[destOffset + 7] = _self13 * vY;
        dest[destOffset + 8] = _self20 * vZ;
        dest[destOffset + 9] = _self21 * vZ;
        dest[destOffset + 10] = _self22 * vZ;
        dest[destOffset + 11] = _self23 * vZ;
        return dest;
    }

    public static double[] preScale_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _vx = v[vOffset + 0];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        dest[destOffset + 0] = _self00 * _vx;
        dest[destOffset + 1] = _self01 * _vx;
        dest[destOffset + 2] = _self02 * _vx;
        dest[destOffset + 3] = _self03 * _vx;
        dest[destOffset + 4] = _self10 * _vy;
        dest[destOffset + 5] = _self11 * _vy;
        dest[destOffset + 6] = _self12 * _vy;
        dest[destOffset + 7] = _self13 * _vy;
        dest[destOffset + 8] = _self20 * _vz;
        dest[destOffset + 9] = _self21 * _vz;
        dest[destOffset + 10] = _self22 * _vz;
        dest[destOffset + 11] = _self23 * _vz;
        return dest;
    }

    public static double[] preScale_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double s) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src[srcOffset + _i];
            dest[destOffset + _i] = s * _eself;
        }
        return dest;
    }

    public static double[] preScaleAround_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src[srcOffset + 0];
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
        double _t0 = 1.0 - s;
        dest[destOffset + 0] = s * _self00;
        dest[destOffset + 1] = s * _self01;
        dest[destOffset + 2] = s * _self02;
        dest[destOffset + 3] = Math.fma(s, _self03, pivotX * _t0);
        dest[destOffset + 4] = s * _self10;
        dest[destOffset + 5] = s * _self11;
        dest[destOffset + 6] = s * _self12;
        dest[destOffset + 7] = Math.fma(s, _self13, pivotY * _t0);
        dest[destOffset + 8] = s * _self20;
        dest[destOffset + 9] = s * _self21;
        dest[destOffset + 10] = s * _self22;
        dest[destOffset + 11] = Math.fma(s, _self23, pivotZ * _t0);
        return dest;
    }

    public static double[] preScaleAround_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _self00 = src[srcOffset + 0];
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
        double _pivotx = pivot[pivotOffset + 0];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        double _t0 = 1.0 - s;
        dest[destOffset + 0] = s * _self00;
        dest[destOffset + 1] = s * _self01;
        dest[destOffset + 2] = s * _self02;
        dest[destOffset + 3] = Math.fma(s, _self03, _pivotx * _t0);
        dest[destOffset + 4] = s * _self10;
        dest[destOffset + 5] = s * _self11;
        dest[destOffset + 6] = s * _self12;
        dest[destOffset + 7] = Math.fma(s, _self13, _pivoty * _t0);
        dest[destOffset + 8] = s * _self20;
        dest[destOffset + 9] = s * _self21;
        dest[destOffset + 10] = s * _self22;
        dest[destOffset + 11] = Math.fma(s, _self23, _pivotz * _t0);
        return dest;
    }

    public static double[] preScaleAround_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src[srcOffset + 0];
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
        dest[destOffset + 0] = sX * _self00;
        dest[destOffset + 1] = sX * _self01;
        dest[destOffset + 2] = sX * _self02;
        dest[destOffset + 3] = Math.fma(pivotX, 1.0 - sX, sX * _self03);
        dest[destOffset + 4] = sY * _self10;
        dest[destOffset + 5] = sY * _self11;
        dest[destOffset + 6] = sY * _self12;
        dest[destOffset + 7] = Math.fma(pivotY, 1.0 - sY, sY * _self13);
        dest[destOffset + 8] = sZ * _self20;
        dest[destOffset + 9] = sZ * _self21;
        dest[destOffset + 10] = sZ * _self22;
        dest[destOffset + 11] = Math.fma(pivotZ, 1.0 - sZ, sZ * _self23);
        return dest;
    }

    public static double[] preScaleAround_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _sx = s[sOffset + 0];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        double _pivotx = pivot[pivotOffset + 0];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        dest[destOffset + 0] = _sx * _self00;
        dest[destOffset + 1] = _sx * _self01;
        dest[destOffset + 2] = _sx * _self02;
        dest[destOffset + 3] = Math.fma(_pivotx, 1.0 - _sx, _sx * _self03);
        dest[destOffset + 4] = _sy * _self10;
        dest[destOffset + 5] = _sy * _self11;
        dest[destOffset + 6] = _sy * _self12;
        dest[destOffset + 7] = Math.fma(_pivoty, 1.0 - _sy, _sy * _self13);
        dest[destOffset + 8] = _sz * _self20;
        dest[destOffset + 9] = _sz * _self21;
        dest[destOffset + 10] = _sz * _self22;
        dest[destOffset + 11] = Math.fma(_pivotz, 1.0 - _sz, _sz * _self23);
        return dest;
    }

    public static double[] preTranslate_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        double _self00 = src[srcOffset + 0];
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
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = _self03 + vX;
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self12;
        dest[destOffset + 7] = _self13 + vY;
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23 + vZ;
        return dest;
    }

    public static double[] preTranslate_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _vx = v[vOffset + 0];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = _self03 + _vx;
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self12;
        dest[destOffset + 7] = _self13 + _vy;
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = _self23 + _vz;
        return dest;
    }

    public static double[] reflect_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        double _self00 = src[srcOffset + 0];
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
        double _sp0 = normalX + normalX;
        double _t0 = -_self02;
        double _t9 = _sp0 * normalZ;
        double _t10 = _sp0 * normalY;
        double _t11 = (normalY + normalY) * normalZ;
        double _t12 = Math.fma(-2.0, normalX * normalX, 1.0);
        double _t13 = Math.fma(-2.0, normalY * normalY, 1.0);
        double _t14 = Math.fma(-2.0, normalZ * normalZ, 1.0);
        dest[destOffset + 0] = Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10)));
        dest[destOffset + 1] = Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10)));
        dest[destOffset + 2] = Math.fma(_self02, _t14, Math.fma(-_self01, _t11, -(_self00 * _t9)));
        dest[destOffset + 3] = _self03;
        return reflect_scalar_s966b1270_1(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self12, -_self22, _t9, _t10, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code reflect_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] reflect_scalar_s966b1270_1(double[] dest, int destOffset, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        dest[destOffset + 4] = Math.fma(_t1, _t9, Math.fma(_self10, _t12, -(_self11 * _t10)));
        dest[destOffset + 5] = Math.fma(_t1, _t11, Math.fma(_self11, _t13, -(_self10 * _t10)));
        dest[destOffset + 6] = Math.fma(_self12, _t14, Math.fma(-_self11, _t11, -(_self10 * _t9)));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = Math.fma(_t2, _t9, Math.fma(_self20, _t12, -(_self21 * _t10)));
        dest[destOffset + 9] = Math.fma(_t2, _t11, Math.fma(_self21, _t13, -(_self20 * _t10)));
        dest[destOffset + 10] = Math.fma(_self22, _t14, Math.fma(-_self21, _t11, -(_self20 * _t9)));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static double[] reflect_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _normalx = normal[normalOffset + 0];
        double _normaly = normal[normalOffset + 1];
        double _normalz = normal[normalOffset + 2];
        double _sp0 = _normalx + _normalx;
        double _t0 = -_self02;
        double _t9 = _sp0 * _normalz;
        double _t10 = _sp0 * _normaly;
        double _t11 = (_normaly + _normaly) * _normalz;
        double _t12 = Math.fma(-2.0, _normalx * _normalx, 1.0);
        double _t13 = Math.fma(-2.0, _normaly * _normaly, 1.0);
        dest[destOffset + 0] = Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10)));
        dest[destOffset + 1] = Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10)));
        return reflect_scalar_s3742c39c_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self12, -_self22, _t9, _t10, _t11, _t12, _t13, Math.fma(-2.0, _normalz * _normalz, 1.0));
    }

    /** Piece 2 of {@code reflect_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] reflect_scalar_s3742c39c_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        dest[destOffset + 2] = Math.fma(_self02, _t14, Math.fma(-_self01, _t11, -(_self00 * _t9)));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = Math.fma(_t1, _t9, Math.fma(_self10, _t12, -(_self11 * _t10)));
        dest[destOffset + 5] = Math.fma(_t1, _t11, Math.fma(_self11, _t13, -(_self10 * _t10)));
        dest[destOffset + 6] = Math.fma(_self12, _t14, Math.fma(-_self11, _t11, -(_self10 * _t9)));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = Math.fma(_t2, _t9, Math.fma(_self20, _t12, -(_self21 * _t10)));
        dest[destOffset + 9] = Math.fma(_t2, _t11, Math.fma(_self21, _t13, -(_self20 * _t10)));
        dest[destOffset + 10] = Math.fma(_self22, _t14, Math.fma(-_self21, _t11, -(_self20 * _t9)));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static double[] rotateAround_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -rotY;
        double _t2 = -rotX;
        double _t3 = -pivotZ;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t11 = rotZ * _t7;
        double _t16 = Math.fma(-rotZ, _t7, 1.0);
        double _t20 = Math.fma(rotZ, _t5, _t8);
        double _t25 = Math.fma(rotY, _t5, -_t9);
        return rotateAround_scalar_sa4106cbe_1(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t3, _t5, _t6, _t11, Math.fma(rotY, _t5, _t9), Math.fma(rotZ, _t6, _t10), _t20, Math.fma(rotZ, _t5, -_t8), _t25, Math.fma(rotZ, _t6, -_t10), Math.fma(_t0, _t6, _t16), Math.fma(_t2, _t5, _t16), Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)), Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
    }

    /** Piece 2 of {@code rotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] rotateAround_scalar_sa4106cbe_1(double[] dest, int destOffset, double rotX, double rotY, double pivotX, double pivotY, double pivotZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t3, double _t5, double _t6, double _t11, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t39) {
        double _t40 = Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18)));
        double _t41 = Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24)));
        dest[destOffset + 0] = Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18));
        dest[destOffset + 1] = Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28));
        dest[destOffset + 2] = Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26));
        dest[destOffset + 3] = Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03)));
        dest[destOffset + 4] = Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18));
        dest[destOffset + 5] = Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28));
        dest[destOffset + 6] = Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26));
        dest[destOffset + 7] = Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13)));
        return rotateAround_scalar_sa4106cbe_2(dest, destOffset, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, _t39, _t40, _t41);
    }

    /** Piece 3 of {@code rotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] rotateAround_scalar_sa4106cbe_2(double[] dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t39, double _t40, double _t41) {
        dest[destOffset + 8] = Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18));
        dest[destOffset + 9] = Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28));
        dest[destOffset + 10] = Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26));
        dest[destOffset + 11] = Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23)));
        return dest;
    }

    public static double[] rotateAround_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _rotx = rot[rotOffset + 0];
        double _roty = rot[rotOffset + 1];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _pivotz = pivot[pivotOffset + 2];
        double _t0 = -_roty;
        double _t2 = -_rotx;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t16 = Math.fma(-_rotz, _t7, 1.0);
        return rotateAround_scalar_s66d6dff0_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _rotx, _roty, pivot[pivotOffset + 0], pivot[pivotOffset + 1], _pivotz, -_pivotz, _t5, _t6, _rotz * _t7, Math.fma(_roty, _t5, _t9), Math.fma(_rotz, _t6, _t10), Math.fma(_rotz, _t5, _t8), Math.fma(_rotz, _t5, -_t8), Math.fma(_roty, _t5, -_t9), Math.fma(_rotz, _t6, -_t10), Math.fma(_t0, _t6, _t16), Math.fma(_t2, _t5, _t16), Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
    }

    /** Piece 2 of {@code rotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] rotateAround_scalar_s66d6dff0_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _pivotx, double _pivoty, double _pivotz, double _t3, double _t5, double _t6, double _t11, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29) {
        double _t39 = Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25)));
        double _t40 = Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)));
        double _t41 = Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)));
        dest[destOffset + 0] = Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18));
        dest[destOffset + 1] = Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28));
        dest[destOffset + 2] = Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26));
        dest[destOffset + 3] = Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03)));
        dest[destOffset + 4] = Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18));
        dest[destOffset + 5] = Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28));
        dest[destOffset + 6] = Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26));
        return rotateAround_scalar_s66d6dff0_2(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, _t39, _t40, _t41);
    }

    /** Piece 3 of {@code rotateAround_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] rotateAround_scalar_s66d6dff0_2(double[] dest, int destOffset, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t39, double _t40, double _t41) {
        dest[destOffset + 7] = Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13)));
        dest[destOffset + 8] = Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18));
        dest[destOffset + 9] = Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28));
        dest[destOffset + 10] = Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26));
        dest[destOffset + 11] = Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23)));
        return dest;
    }

    public static double[] rotateAxis_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        return rotateAxis_scalar_s17fb56c1_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)));
    }

    /** Piece 2 of {@code rotateAxis_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] rotateAxis_scalar_s17fb56c1_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest[destOffset + 0] = Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21));
        dest[destOffset + 1] = Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19));
        dest[destOffset + 2] = Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21));
        dest[destOffset + 5] = Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19));
        dest[destOffset + 6] = Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21));
        dest[destOffset + 9] = Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19));
        dest[destOffset + 10] = Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static double[] rotateAxis_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _axisx = axis[axisOffset + 0];
        double _axisy = axis[axisOffset + 1];
        double _axisz = axis[axisOffset + 2];
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t5 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        return rotateAxis_scalar_sad5c0221_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_axisz, _t0, _t11 * _t5), Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_t11, _t2, -(_axisy * _t0)), Math.fma(_t11, _t5, -(_axisz * _t0)), Math.fma(_t11, _t6, -(_axisx * _t0)));
    }

    /** Piece 2 of {@code rotateAxis_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] rotateAxis_scalar_sad5c0221_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest[destOffset + 0] = Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21));
        dest[destOffset + 1] = Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19));
        dest[destOffset + 2] = Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21));
        dest[destOffset + 5] = Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19));
        dest[destOffset + 6] = Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21));
        dest[destOffset + 9] = Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19));
        dest[destOffset + 10] = Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static double[] rotateQuat_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = -qY;
        double _t2 = -qX;
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        double _t6 = qW * _t4;
        double _t7 = qW * _t5;
        double _t8 = qW * _t3;
        double _t12 = Math.fma(-qZ, _t5, 1.0);
        return rotateQuat_scalar_se37743d6_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], Math.fma(qY, _t3, _t7), Math.fma(qZ, _t4, _t8), Math.fma(qZ, _t3, _t6), Math.fma(qZ, _t3, -_t6), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t4, -_t8), Math.fma(_t0, _t4, _t12), Math.fma(_t2, _t3, _t12), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
    }

    /** Piece 2 of {@code rotateQuat_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] rotateQuat_scalar_se37743d6_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22) {
        dest[destOffset + 0] = Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14));
        dest[destOffset + 1] = Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21));
        dest[destOffset + 2] = Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14));
        dest[destOffset + 5] = Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21));
        dest[destOffset + 6] = Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14));
        dest[destOffset + 9] = Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21));
        dest[destOffset + 10] = Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static double[] rotateQuat_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qx = q[qOffset + 0];
        double _qy = q[qOffset + 1];
        double _qz = q[qOffset + 2];
        double _qw = q[qOffset + 3];
        double _t0 = -_qy;
        double _t2 = -_qx;
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        double _t6 = _qw * _t4;
        double _t7 = _qw * _t5;
        double _t8 = _qw * _t3;
        double _t12 = Math.fma(-_qz, _t5, 1.0);
        return rotateQuat_scalar_sd2e2bb25_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], Math.fma(_qy, _t3, _t7), Math.fma(_qz, _t4, _t8), Math.fma(_qz, _t3, _t6), Math.fma(_qz, _t3, -_t6), Math.fma(_qy, _t3, -_t7), Math.fma(_qz, _t4, -_t8), Math.fma(_t0, _t4, _t12), Math.fma(_t2, _t3, _t12), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
    }

    /** Piece 2 of {@code rotateQuat_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] rotateQuat_scalar_sd2e2bb25_1(double[] dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22) {
        dest[destOffset + 0] = Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14));
        dest[destOffset + 1] = Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21));
        dest[destOffset + 2] = Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14));
        dest[destOffset + 5] = Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21));
        dest[destOffset + 6] = Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14));
        dest[destOffset + 9] = Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21));
        dest[destOffset + 10] = Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static double[] rotateYXZ_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _self00 = src[srcOffset + 0];
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
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t2 * _t5;
        double _t18 = Math.fma(_t6, _t2, _t3 * _t4);
        double _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        dest[destOffset + 0] = Math.fma(_self02, _t20, Math.fma(_self00, _t18, _self01 * _t10));
        return rotateYXZ_scalar_s11458353_1(dest, destOffset, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t10, _t1 * _t5, _t5 * _t4, _t5 * _t3, _t18, Math.fma(_t8, _t4, _t1 * _t2), _t20, Math.fma(_t6, _t4, -(_t2 * _t3)));
    }

    /** Piece 2 of {@code rotateYXZ_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] rotateYXZ_scalar_s11458353_1(double[] dest, int destOffset, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t10, double _t12, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21) {
        dest[destOffset + 1] = Math.fma(_self02, _t19, Math.fma(_self00, _t21, _self01 * _t16));
        dest[destOffset + 2] = Math.fma(_self02, _t17, Math.fma(_self00, _t12, -(_self01 * _t0)));
        dest[destOffset + 3] = _self03;
        dest[destOffset + 4] = Math.fma(_self12, _t20, Math.fma(_self10, _t18, _self11 * _t10));
        dest[destOffset + 5] = Math.fma(_self12, _t19, Math.fma(_self10, _t21, _self11 * _t16));
        dest[destOffset + 6] = Math.fma(_self12, _t17, Math.fma(_self10, _t12, -(_self11 * _t0)));
        dest[destOffset + 7] = _self13;
        dest[destOffset + 8] = Math.fma(_self22, _t20, Math.fma(_self20, _t18, _self21 * _t10));
        dest[destOffset + 9] = Math.fma(_self22, _t19, Math.fma(_self20, _t21, _self21 * _t16));
        dest[destOffset + 10] = Math.fma(_self22, _t17, Math.fma(_self20, _t12, -(_self21 * _t0)));
        dest[destOffset + 11] = _self23;
        return dest;
    }

    public static double[] scale_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            double _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = _eself0 * vX;
            dest[destOffset + _lo + 1] = _eself1 * vY;
            dest[destOffset + _lo + 2] = _eself2 * vZ;
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    public static double[] scale_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset + 0];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            double _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = _eself0 * _vx;
            dest[destOffset + _lo + 1] = _eself1 * _vy;
            dest[destOffset + _lo + 2] = _eself2 * _vz;
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    public static double[] scale_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            double _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = s * _eself0;
            dest[destOffset + _lo + 1] = s * _eself1;
            dest[destOffset + _lo + 2] = s * _eself2;
            dest[destOffset + _lo + 3] = _eself3;
        }
        return dest;
    }

    public static double[] scaleAround_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src[srcOffset + 0];
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
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _t3 = pivotZ * _t0;
        dest[destOffset + 0] = s * _self00;
        dest[destOffset + 1] = s * _self01;
        dest[destOffset + 2] = s * _self02;
        dest[destOffset + 3] = Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03)));
        dest[destOffset + 4] = s * _self10;
        dest[destOffset + 5] = s * _self11;
        dest[destOffset + 6] = s * _self12;
        dest[destOffset + 7] = Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13)));
        dest[destOffset + 8] = s * _self20;
        dest[destOffset + 9] = s * _self21;
        dest[destOffset + 10] = s * _self22;
        dest[destOffset + 11] = Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23)));
        return dest;
    }

    public static double[] scaleAround_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _self00 = src[srcOffset + 0];
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
        double _pivotx = pivot[pivotOffset + 0];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        double _t0 = 1.0 - s;
        double _t1 = _pivotx * _t0;
        double _t2 = _pivoty * _t0;
        double _t3 = _pivotz * _t0;
        dest[destOffset + 0] = s * _self00;
        dest[destOffset + 1] = s * _self01;
        dest[destOffset + 2] = s * _self02;
        dest[destOffset + 3] = Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03)));
        dest[destOffset + 4] = s * _self10;
        dest[destOffset + 5] = s * _self11;
        dest[destOffset + 6] = s * _self12;
        dest[destOffset + 7] = Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13)));
        dest[destOffset + 8] = s * _self20;
        return scaleAround_scalar_s5acbe4c4_1(dest, destOffset, s, _self20, _self21, _self22, _self23, _t1, _t2, _t3);
    }

    /** Piece 2 of {@code scaleAround_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] scaleAround_scalar_s5acbe4c4_1(double[] dest, int destOffset, double s, double _self20, double _self21, double _self22, double _self23, double _t1, double _t2, double _t3) {
        dest[destOffset + 9] = s * _self21;
        dest[destOffset + 10] = s * _self22;
        dest[destOffset + 11] = Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23)));
        return dest;
    }

    public static double[] scaleAround_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src[srcOffset + 0];
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
        double _t3 = pivotX * (1.0 - sX);
        double _t4 = pivotY * (1.0 - sY);
        double _t5 = pivotZ * (1.0 - sZ);
        dest[destOffset + 0] = sX * _self00;
        dest[destOffset + 1] = sY * _self01;
        dest[destOffset + 2] = sZ * _self02;
        dest[destOffset + 3] = Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03)));
        dest[destOffset + 4] = sX * _self10;
        dest[destOffset + 5] = sY * _self11;
        dest[destOffset + 6] = sZ * _self12;
        dest[destOffset + 7] = Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13)));
        dest[destOffset + 8] = sX * _self20;
        dest[destOffset + 9] = sY * _self21;
        dest[destOffset + 10] = sZ * _self22;
        dest[destOffset + 11] = Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23)));
        return dest;
    }

    public static double[] scaleAround_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _self00 = src[srcOffset + 0];
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
        double _sx = s[sOffset + 0];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        double _pivotx = pivot[pivotOffset + 0];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        double _t3 = _pivotx * (1.0 - _sx);
        double _t4 = _pivoty * (1.0 - _sy);
        double _t5 = _pivotz * (1.0 - _sz);
        dest[destOffset + 0] = _sx * _self00;
        dest[destOffset + 1] = _sy * _self01;
        dest[destOffset + 2] = _sz * _self02;
        dest[destOffset + 3] = Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03)));
        dest[destOffset + 4] = _sx * _self10;
        dest[destOffset + 5] = _sy * _self11;
        dest[destOffset + 6] = _sz * _self12;
        return scaleAround_scalar_s738c02f7_1(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _sx, _sy, _sz, _t3, _t4, _t5);
    }

    /** Piece 2 of {@code scaleAround_scalar}, split to fit the inline budget; reached only through it. */
    private static double[] scaleAround_scalar_s738c02f7_1(double[] dest, int destOffset, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _sx, double _sy, double _sz, double _t3, double _t4, double _t5) {
        dest[destOffset + 7] = Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13)));
        dest[destOffset + 8] = _sx * _self20;
        dest[destOffset + 9] = _sy * _self21;
        dest[destOffset + 10] = _sz * _self22;
        dest[destOffset + 11] = Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23)));
        return dest;
    }

    public static double[] translate_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            double _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = _eself0;
            dest[destOffset + _lo + 1] = _eself1;
            dest[destOffset + _lo + 2] = _eself2;
            dest[destOffset + _lo + 3] = Math.fma(_eself0, vX, Math.fma(_eself1, vY, Math.fma(_eself2, vZ, _eself3)));
        }
        return dest;
    }

    public static double[] translate_scalar(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset + 0];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            double _eself3 = src[srcOffset + _lo + 3];
            dest[destOffset + _lo] = _eself0;
            dest[destOffset + _lo + 1] = _eself1;
            dest[destOffset + _lo + 2] = _eself2;
            dest[destOffset + _lo + 3] = Math.fma(_eself0, _vx, Math.fma(_eself1, _vy, Math.fma(_eself2, _vz, _eself3)));
        }
        return dest;
    }

    public static double[] lerpComposeTRSMul_fma(double[] dest, int destOffset, double[] t1, int t1Offset, double[] t2, int t2Offset, double[] q1, int q1Offset, double[] q2, int q2Offset, double[] s1, int s1Offset, double[] s2, int s2Offset, double[] m, int mOffset, double alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _t1o = t1Offset + _i * 3;
            int _t2o = t2Offset + _i * 3;
            int _q1o = q1Offset + _i * 4;
            int _q2o = q2Offset + _i * 4;
            int _s1o = s1Offset + _i * 3;
            int _s2o = s2Offset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            double _ax = t1[_t1o + 0], _ay = t1[_t1o + 1], _az = t1[_t1o + 2];
            double _tx = Math.fma(alpha, t2[_t2o + 0] - _ax, _ax);
            double _ty = Math.fma(alpha, t2[_t2o + 1] - _ay, _ay);
            double _tz = Math.fma(alpha, t2[_t2o + 2] - _az, _az);
            double _bx = s1[_s1o + 0], _by = s1[_s1o + 1], _bz = s1[_s1o + 2];
            double _sx = Math.fma(alpha, s2[_s2o + 0] - _bx, _bx);
            double _sy = Math.fma(alpha, s2[_s2o + 1] - _by, _by);
            double _sz = Math.fma(alpha, s2[_s2o + 2] - _bz, _bz);
            double _ux = q1[_q1o + 0], _uy = q1[_q1o + 1], _uz = q1[_q1o + 2], _uw = q1[_q1o + 3];
            double _vx = q2[_q2o + 0], _vy = q2[_q2o + 1], _vz = q2[_q2o + 2], _vw = q2[_q2o + 3];
            double _dot = Math.fma(_uw, _vw, Math.fma(_uz, _vz, Math.fma(_ux, _vx, _uy * _vy)));
            if (_dot < 0.0) { _vx = -_vx; _vy = -_vy; _vz = -_vz; _vw = -_vw; }
            double _qx = Math.fma(alpha, _vx - _ux, _ux);
            double _qy = Math.fma(alpha, _vy - _uy, _uy);
            double _qz = Math.fma(alpha, _vz - _uz, _uz);
            double _qw = Math.fma(alpha, _vw - _uw, _uw);
            double _len2 = (_qx * _qx + _qy * _qy) + (_qz * _qz + _qw * _qw);
            double _ninv = _len2 > 0.0 ? 1.0 / Math.sqrt(_len2) : 0.0;
            _qx *= _ninv; _qy *= _ninv; _qz *= _ninv; _qw *= _ninv;
            double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            double _m00 = m[_mo + 0], _m01 = m[_mo + 1], _m02 = m[_mo + 2], _m03 = m[_mo + 3];
            double _m10 = m[_mo + 4], _m11 = m[_mo + 5], _m12 = m[_mo + 6], _m13 = m[_mo + 7];
            double _m20 = m[_mo + 8], _m21 = m[_mo + 9], _m22 = m[_mo + 10], _m23 = m[_mo + 11];
            double _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            double _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            double _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            dest[_do + 0] = _e00;
            dest[_do + 1] = _e01;
            dest[_do + 2] = _e02;
            dest[_do + 3] = _e03;
            dest[_do + 4] = _e10;
            dest[_do + 5] = _e11;
            dest[_do + 6] = _e12;
            dest[_do + 7] = _e13;
            dest[_do + 8] = _e20;
            dest[_do + 9] = _e21;
            dest[_do + 10] = _e22;
            dest[_do + 11] = _e23;
        }
        return dest;
    }

    public static double[] lerpComposeTRSMul_mulAdd(double[] dest, int destOffset, double[] t1, int t1Offset, double[] t2, int t2Offset, double[] q1, int q1Offset, double[] q2, int q2Offset, double[] s1, int s1Offset, double[] s2, int s2Offset, double[] m, int mOffset, double alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _t1o = t1Offset + _i * 3;
            int _t2o = t2Offset + _i * 3;
            int _q1o = q1Offset + _i * 4;
            int _q2o = q2Offset + _i * 4;
            int _s1o = s1Offset + _i * 3;
            int _s2o = s2Offset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            double _ax = t1[_t1o + 0], _ay = t1[_t1o + 1], _az = t1[_t1o + 2];
            double _tx = alpha * (t2[_t2o + 0] - _ax) + _ax;
            double _ty = alpha * (t2[_t2o + 1] - _ay) + _ay;
            double _tz = alpha * (t2[_t2o + 2] - _az) + _az;
            double _bx = s1[_s1o + 0], _by = s1[_s1o + 1], _bz = s1[_s1o + 2];
            double _sx = alpha * (s2[_s2o + 0] - _bx) + _bx;
            double _sy = alpha * (s2[_s2o + 1] - _by) + _by;
            double _sz = alpha * (s2[_s2o + 2] - _bz) + _bz;
            double _ux = q1[_q1o + 0], _uy = q1[_q1o + 1], _uz = q1[_q1o + 2], _uw = q1[_q1o + 3];
            double _vx = q2[_q2o + 0], _vy = q2[_q2o + 1], _vz = q2[_q2o + 2], _vw = q2[_q2o + 3];
            double _dot = _uw * _vw + (_uz * _vz + (_ux * _vx + (_uy * _vy)));
            if (_dot < 0.0) { _vx = -_vx; _vy = -_vy; _vz = -_vz; _vw = -_vw; }
            double _qx = alpha * (_vx - _ux) + _ux;
            double _qy = alpha * (_vy - _uy) + _uy;
            double _qz = alpha * (_vz - _uz) + _uz;
            double _qw = alpha * (_vw - _uw) + _uw;
            double _len2 = (_qx * _qx + _qy * _qy) + (_qz * _qz + _qw * _qw);
            double _ninv = _len2 > 0.0 ? 1.0 / Math.sqrt(_len2) : 0.0;
            _qx *= _ninv; _qy *= _ninv; _qz *= _ninv; _qw *= _ninv;
            double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            double _m00 = m[_mo + 0], _m01 = m[_mo + 1], _m02 = m[_mo + 2], _m03 = m[_mo + 3];
            double _m10 = m[_mo + 4], _m11 = m[_mo + 5], _m12 = m[_mo + 6], _m13 = m[_mo + 7];
            double _m20 = m[_mo + 8], _m21 = m[_mo + 9], _m22 = m[_mo + 10], _m23 = m[_mo + 11];
            double _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + _tx;
            double _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + _ty;
            double _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + _tz;
            dest[_do + 0] = _e00;
            dest[_do + 1] = _e01;
            dest[_do + 2] = _e02;
            dest[_do + 3] = _e03;
            dest[_do + 4] = _e10;
            dest[_do + 5] = _e11;
            dest[_do + 6] = _e12;
            dest[_do + 7] = _e13;
            dest[_do + 8] = _e20;
            dest[_do + 9] = _e21;
            dest[_do + 10] = _e22;
            dest[_do + 11] = _e23;
        }
        return dest;
    }

    public static double[] composeTRSMul_fma(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _translationo = translationOffset + _i * 3;
            int _rotationo = rotationOffset + _i * 4;
            int _scaleo = scaleOffset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            double _tx = translation[_translationo + 0], _ty = translation[_translationo + 1], _tz = translation[_translationo + 2];
            double _sx = scale[_scaleo + 0], _sy = scale[_scaleo + 1], _sz = scale[_scaleo + 2];
            double _qx = rotation[_rotationo + 0], _qy = rotation[_rotationo + 1], _qz = rotation[_rotationo + 2], _qw = rotation[_rotationo + 3];
            double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            double _m00 = m[_mo + 0], _m01 = m[_mo + 1], _m02 = m[_mo + 2], _m03 = m[_mo + 3];
            double _m10 = m[_mo + 4], _m11 = m[_mo + 5], _m12 = m[_mo + 6], _m13 = m[_mo + 7];
            double _m20 = m[_mo + 8], _m21 = m[_mo + 9], _m22 = m[_mo + 10], _m23 = m[_mo + 11];
            double _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            double _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            double _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            dest[_do + 0] = _e00;
            dest[_do + 1] = _e01;
            dest[_do + 2] = _e02;
            dest[_do + 3] = _e03;
            dest[_do + 4] = _e10;
            dest[_do + 5] = _e11;
            dest[_do + 6] = _e12;
            dest[_do + 7] = _e13;
            dest[_do + 8] = _e20;
            dest[_do + 9] = _e21;
            dest[_do + 10] = _e22;
            dest[_do + 11] = _e23;
        }
        return dest;
    }

    public static double[] composeTRSMul_mulAdd(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _translationo = translationOffset + _i * 3;
            int _rotationo = rotationOffset + _i * 4;
            int _scaleo = scaleOffset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            double _tx = translation[_translationo + 0], _ty = translation[_translationo + 1], _tz = translation[_translationo + 2];
            double _sx = scale[_scaleo + 0], _sy = scale[_scaleo + 1], _sz = scale[_scaleo + 2];
            double _qx = rotation[_rotationo + 0], _qy = rotation[_rotationo + 1], _qz = rotation[_rotationo + 2], _qw = rotation[_rotationo + 3];
            double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            double _m00 = m[_mo + 0], _m01 = m[_mo + 1], _m02 = m[_mo + 2], _m03 = m[_mo + 3];
            double _m10 = m[_mo + 4], _m11 = m[_mo + 5], _m12 = m[_mo + 6], _m13 = m[_mo + 7];
            double _m20 = m[_mo + 8], _m21 = m[_mo + 9], _m22 = m[_mo + 10], _m23 = m[_mo + 11];
            double _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + _tx;
            double _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + _ty;
            double _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + _tz;
            dest[_do + 0] = _e00;
            dest[_do + 1] = _e01;
            dest[_do + 2] = _e02;
            dest[_do + 3] = _e03;
            dest[_do + 4] = _e10;
            dest[_do + 5] = _e11;
            dest[_do + 6] = _e12;
            dest[_do + 7] = _e13;
            dest[_do + 8] = _e20;
            dest[_do + 9] = _e21;
            dest[_do + 10] = _e22;
            dest[_do + 11] = _e23;
        }
        return dest;
    }

    public static double[] composeTRSMulPadded_fma(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset) {
        double _qx = rotation[rotationOffset + 0], _qy = rotation[rotationOffset + 1], _qz = rotation[rotationOffset + 2], _qw = rotation[rotationOffset + 3];
        double _tx = translation[translationOffset + 0], _ty = translation[translationOffset + 1], _tz = translation[translationOffset + 2];
        double _sx = scale[scaleOffset + 0], _sy = scale[scaleOffset + 1], _sz = scale[scaleOffset + 2];
        double _m00 = m[mOffset + 0], _m01 = m[mOffset + 1], _m02 = m[mOffset + 2], _m03 = m[mOffset + 3];
        double _m10 = m[mOffset + 4], _m11 = m[mOffset + 5], _m12 = m[mOffset + 6], _m13 = m[mOffset + 7];
        double _m20 = m[mOffset + 8], _m21 = m[mOffset + 9], _m22 = m[mOffset + 10], _m23 = m[mOffset + 11];
        double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        return composeTRSMulPadded_fma_s2cd83ccc_1(dest, destOffset, _qx, _qy, _qz, _qw, _tx, _ty, _tz, _sx, _sy, _sz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _xx, _yy, _zz);
    }

    /** Piece 2 of {@code composeTRSMulPadded_fma}, split to fit the inline budget; reached only through it. */
    private static double[] composeTRSMulPadded_fma_s2cd83ccc_1(double[] dest, int destOffset, double _qx, double _qy, double _qz, double _qw, double _tx, double _ty, double _tz, double _sx, double _sy, double _sz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _xx, double _yy, double _zz) {
        double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        double _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
        return composeTRSMulPadded_fma_s2cd83ccc_2(dest, destOffset, _ty, _tz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t10, _t11, _t12, _t20, _t21, _t22, _e00, _e01, _e02, _e03);
    }

    /** Piece 3 of {@code composeTRSMulPadded_fma}, split to fit the inline budget; reached only through it. */
    private static double[] composeTRSMulPadded_fma_s2cd83ccc_2(double[] dest, int destOffset, double _ty, double _tz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t10, double _t11, double _t12, double _t20, double _t21, double _t22, double _e00, double _e01, double _e02, double _e03) {
        double _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
        double _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
        dest[destOffset + 0] = _e00;
        dest[destOffset + 1] = _e01;
        dest[destOffset + 2] = _e02;
        dest[destOffset + 3] = _e03;
        dest[destOffset + 4] = _e10;
        dest[destOffset + 5] = _e11;
        dest[destOffset + 6] = _e12;
        dest[destOffset + 7] = _e13;
        dest[destOffset + 8] = _e20;
        dest[destOffset + 9] = _e21;
        dest[destOffset + 10] = _e22;
        dest[destOffset + 11] = _e23;
        return dest;
    }

    public static double[] composeTRSMulPadded_mulAdd(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset) {
        double _qx = rotation[rotationOffset + 0], _qy = rotation[rotationOffset + 1], _qz = rotation[rotationOffset + 2], _qw = rotation[rotationOffset + 3];
        double _tx = translation[translationOffset + 0], _ty = translation[translationOffset + 1], _tz = translation[translationOffset + 2];
        double _sx = scale[scaleOffset + 0], _sy = scale[scaleOffset + 1], _sz = scale[scaleOffset + 2];
        double _m00 = m[mOffset + 0], _m01 = m[mOffset + 1], _m02 = m[mOffset + 2], _m03 = m[mOffset + 3];
        double _m10 = m[mOffset + 4], _m11 = m[mOffset + 5], _m12 = m[mOffset + 6], _m13 = m[mOffset + 7];
        double _m20 = m[mOffset + 8], _m21 = m[mOffset + 9], _m22 = m[mOffset + 10], _m23 = m[mOffset + 11];
        double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        return composeTRSMulPadded_mulAdd_s4adeac6f_1(dest, destOffset, _qx, _qy, _qz, _qw, _tx, _ty, _tz, _sx, _sy, _sz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _xx, _yy, _zz);
    }

    /** Piece 2 of {@code composeTRSMulPadded_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] composeTRSMulPadded_mulAdd_s4adeac6f_1(double[] dest, int destOffset, double _qx, double _qy, double _qz, double _qw, double _tx, double _ty, double _tz, double _sx, double _sy, double _sz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _xx, double _yy, double _zz) {
        double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        double _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + _tx;
        return composeTRSMulPadded_mulAdd_s4adeac6f_2(dest, destOffset, _ty, _tz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t10, _t11, _t12, _t20, _t21, _t22, _e00, _e01, _e02, _e03);
    }

    /** Piece 3 of {@code composeTRSMulPadded_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] composeTRSMulPadded_mulAdd_s4adeac6f_2(double[] dest, int destOffset, double _ty, double _tz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t10, double _t11, double _t12, double _t20, double _t21, double _t22, double _e00, double _e01, double _e02, double _e03) {
        double _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + _ty;
        double _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + _tz;
        dest[destOffset + 0] = _e00;
        dest[destOffset + 1] = _e01;
        dest[destOffset + 2] = _e02;
        dest[destOffset + 3] = _e03;
        dest[destOffset + 4] = _e10;
        dest[destOffset + 5] = _e11;
        dest[destOffset + 6] = _e12;
        dest[destOffset + 7] = _e13;
        dest[destOffset + 8] = _e20;
        dest[destOffset + 9] = _e21;
        dest[destOffset + 10] = _e22;
        dest[destOffset + 11] = _e23;
        return dest;
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

    /** Double-precision twin of {@link #unitScale(float, float, float)}. */
    private static double unitScale(double a, double b, double c) {
        long e = java.lang.Math.max(java.lang.Math.max(Double.doubleToRawLongBits(a) & 0x7FF0000000000000L,
                Double.doubleToRawLongBits(b) & 0x7FF0000000000000L), Double.doubleToRawLongBits(c) & 0x7FF0000000000000L);
        return Double.longBitsToDouble(0x7FE0000000000000L
                - java.lang.Math.min(java.lang.Math.max(e, 0x0010000000000000L), 0x7FD0000000000000L));
    }
}
