// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double4x4Ops} whose leading storage
 * parameter is a {@code double[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double4x4Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double4x4OpsKernelsArray {
    private Double4x4OpsKernelsArray() {}

    public static double[] invNegativeX_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 9];
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 6];
        double _self02 = src[srcOffset + 8];
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
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
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
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 9];
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 6];
        double _self02 = src[srcOffset + 8];
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
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
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
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t12 = unitScale(_self22, _self23, Math.max(java.lang.Math.abs(_self20), java.lang.Math.abs(_self21)));
        double _t13 = unitScale(_self32, _self33, Math.max(java.lang.Math.abs(_self30), java.lang.Math.abs(_self31)));
        double _t14 = unitScale(_self12, _self13, Math.max(java.lang.Math.abs(_self10), java.lang.Math.abs(_self11)));
        double _t15 = unitScale(_self02, _self03, Math.max(java.lang.Math.abs(_self00), java.lang.Math.abs(_self01)));
        return invert_degenerate_sa19c02d6_1(dest, destOffset, _t12, _t13, _t14, _t15, _self21 * _t12, _self32 * _t13, _self22 * _t12, _self31 * _t13, _self13 * _t14, _self33 * _t13, _self23 * _t12, _self11 * _t14, _self12 * _t14, _self20 * _t12, _self30 * _t13, _self10 * _t14, _self03 * _t15, _self02 * _t15, _self00 * _t15, _self01 * _t15);
    }

    /** Piece 2 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invert_degenerate_sa19c02d6_1(double[] dest, int destOffset, double _t12, double _t13, double _t14, double _t15, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47) {
        double _t84 = Math.fma(_t32, _t33, -_t34 * _t35);
        double _t85 = Math.fma(_t34, _t37, -_t38 * _t33);
        double _t86 = Math.fma(_t32, _t37, -_t38 * _t35);
        return invert_degenerate_sa19c02d6_2(dest, destOffset, _t12, _t13, _t14, _t15, _t36, _t39, _t40, _t43, _t44, _t45, _t46, _t47, _t84, _t85, _t86, Math.fma(_t41, _t35, -(_t32 * _t42)), Math.fma(_t41, _t33, -_t34 * _t42), Math.fma(_t41, _t37, -_t38 * _t42), Math.fma(_t39, _t33, -_t40 * _t35), Math.fma(_t40, _t37, -_t36 * _t33), Math.fma(_t39, _t37, -_t36 * _t35), Math.fma(_t39, _t34, -_t40 * _t32), Math.fma(_t40, _t38, -_t36 * _t34), Math.fma(_t39, _t38, -_t36 * _t32), Math.fma(_t43, _t33, -_t40 * _t42), Math.fma(_t43, _t37, -_t36 * _t42), Math.fma(_t43, _t34, -_t40 * _t41), Math.fma(_t43, _t38, -_t36 * _t41), Math.fma(_t43, _t35, -_t39 * _t42), Math.fma(_t43, _t32, -_t39 * _t41), Math.fma(_t84, _t36, Math.fma(_t85, _t39, -(_t86 * _t40))));
    }

    /** Piece 3 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invert_degenerate_sa19c02d6_2(double[] dest, int destOffset, double _t12, double _t13, double _t14, double _t15, double _t36, double _t39, double _t40, double _t43, double _t44, double _t45, double _t46, double _t47, double _t84, double _t85, double _t86, double _t87, double _t88, double _t89, double _t90, double _t91, double _t92, double _t93, double _t94, double _t95, double _t96, double _t97, double _t98, double _t99, double _t100, double _t101, double _t114) {
        double _t115 = Math.fma(_t87, _t40, Math.fma(_t84, _t43, -(_t88 * _t39)));
        double _t116 = Math.fma(_t87, _t36, Math.fma(_t86, _t43, -(_t89 * _t39)));
        double _t117 = Math.fma(_t88, _t36, Math.fma(_t85, _t43, -(_t89 * _t40)));
        double _t123_inv = 1.0 / Math.fma(-_t115, _t44, Math.fma(_t116, _t45, Math.fma(_t114, _t46, -(_t117 * _t47))));
        double _sp1 = _t14 * _t123_inv;
        double _sp0 = _t15 * _t123_inv;
        dest[destOffset] = _t114 * _sp0;
        dest[destOffset + 1] = -(_t117 * _sp0);
        dest[destOffset + 2] = _t116 * _sp0;
        dest[destOffset + 3] = -(_t115 * _sp0);
        dest[destOffset + 4] = -Math.fma(_t84, _t44, Math.fma(_t85, _t47, _t86 * -_t45)) * _sp1;
        dest[destOffset + 5] = Math.fma(_t88, _t44, Math.fma(_t85, _t46, _t89 * -_t45)) * _sp1;
        dest[destOffset + 6] = -Math.fma(_t87, _t44, Math.fma(_t86, _t46, _t89 * -_t47)) * _sp1;
        return invert_degenerate_sa19c02d6_3(dest, destOffset, _t44, _t45, _t46, _t47, _t84, _t87, _t88, _t90, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, _t101, _t13 * _t123_inv, _t12 * _t123_inv, _sp1);
    }

    /** Piece 4 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invert_degenerate_sa19c02d6_3(double[] dest, int destOffset, double _t44, double _t45, double _t46, double _t47, double _t84, double _t87, double _t88, double _t90, double _t91, double _t92, double _t93, double _t94, double _t95, double _t96, double _t97, double _t98, double _t99, double _t100, double _t101, double _sp3, double _sp2, double _sp1) {
        dest[destOffset + 7] = Math.fma(_t87, _t45, Math.fma(_t84, _t46, _t88 * -_t47)) * _sp1;
        dest[destOffset + 8] = Math.fma(_t90, _t44, Math.fma(_t91, _t47, _t92 * -_t45)) * _sp2;
        dest[destOffset + 9] = -Math.fma(_t96, _t44, Math.fma(_t91, _t46, _t97 * -_t45)) * _sp2;
        dest[destOffset + 10] = Math.fma(_t100, _t44, Math.fma(_t92, _t46, _t97 * -_t47)) * _sp2;
        dest[destOffset + 11] = -Math.fma(_t100, _t45, Math.fma(_t90, _t46, _t96 * -_t47)) * _sp2;
        dest[destOffset + 12] = -Math.fma(_t93, _t44, Math.fma(_t94, _t47, _t95 * -_t45)) * _sp3;
        dest[destOffset + 13] = Math.fma(_t98, _t44, Math.fma(_t94, _t46, _t99 * -_t45)) * _sp3;
        dest[destOffset + 14] = -Math.fma(_t101, _t44, Math.fma(_t95, _t46, _t99 * -_t47)) * _sp3;
        dest[destOffset + 15] = Math.fma(_t101, _t45, Math.fma(_t93, _t46, _t98 * -_t47)) * _sp3;
        return dest;
    }

    public static double[] invert_affine(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _t12 = Math.fma(_self11, _self22, -_self12 * _self21);
        double _t13 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t14 = Math.fma(_self10, _self22, -_self12 * _self20);
        double _t22 = Math.fma(_self02, _t13, Math.fma(_self00, _t12, -(_self01 * _t14)));
        if (!(java.lang.Math.abs(_t22) > 2.2250738585072014E-308 && java.lang.Math.abs(_t22) < 4.49423283715579E307)) return Double4x4OpsKernelsArray.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t22_inv = 1.0 / _t22;
        dest[destOffset] = _t12 * _t22_inv;
        dest[destOffset + 1] = Math.fma(_self12, _self20, -(_self10 * _self22)) * _t22_inv;
        dest[destOffset + 2] = _t13 * _t22_inv;
        return invert_affine_s4464130f_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _self03, _t12, _t13, _t14, Math.fma(_self12, _self23, -_self13 * _self22), Math.fma(_self11, _self23, -_self13 * _self21), Math.fma(_self10, _self23, -_self13 * _self20), _t22_inv);
    }

    /** Piece 2 of {@code invert_affine}, split to fit the inline budget; reached only through it. */
    private static double[] invert_affine_s4464130f_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _self03, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t22_inv) {
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = Math.fma(_self02, _self21, -_self01 * _self22) * _t22_inv;
        dest[destOffset + 5] = Math.fma(_self00, _self22, -_self02 * _self20) * _t22_inv;
        dest[destOffset + 6] = Math.fma(_self01, _self20, -_self00 * _self21) * _t22_inv;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = Math.fma(_self01, _self12, -_self02 * _self11) * _t22_inv;
        dest[destOffset + 9] = Math.fma(_self02, _self10, -_self00 * _self12) * _t22_inv;
        dest[destOffset + 10] = Math.fma(_self00, _self11, -_self01 * _self10) * _t22_inv;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(_self03, _t12, Math.fma(_self01, _t15, -_self02 * _t16)) * _t22_inv;
        dest[destOffset + 13] = Math.fma(_self03, _t14, Math.fma(_self00, _t15, -_self02 * _t17)) * _t22_inv;
        dest[destOffset + 14] = -Math.fma(_self03, _t13, Math.fma(_self00, _t16, -_self01 * _t17)) * _t22_inv;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] invertProduct_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self20 = src[srcOffset + 2];
        double _self21 = src[srcOffset + 6];
        double _self22 = src[srcOffset + 10];
        double _self23 = src[srcOffset + 14];
        double _other01 = other[otherOffset + 4];
        double _other11 = other[otherOffset + 5];
        double _other21 = other[otherOffset + 6];
        double _other31 = other[otherOffset + 7];
        return invertProduct_degenerate_s13d521b0_7(dest, destOffset, src[srcOffset], src[srcOffset + 1], _self20, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self21, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], _self23, src[srcOffset + 15], other[otherOffset], other[otherOffset + 1], other[otherOffset + 2], other[otherOffset + 3], _other01, _other11, _other21, _other31, other[otherOffset + 8], other[otherOffset + 9], other[otherOffset + 10], other[otherOffset + 11], other[otherOffset + 12], other[otherOffset + 13], other[otherOffset + 14], other[otherOffset + 15], Math.fma(_other31, _self23, Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21))));
    }

    /** Part 1 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_s13d521b0_1(double _t48, double _t49, double _t50, double _t52, double _t53, double _t55, double _t56, double _t57, double _t59, double _t76, double _t77, double _t78) {
        double _t96 = _t48 * _t76;
        double _t97 = _t52 * _t77;
        double _t98 = _t55 * _t77;
        double _t99 = _t49 * _t76;
        double _t101 = _t53 * _t77;
        double _t102 = _t50 * _t76;
        return Math.fma((Math.fma(_t96, _t97, _t98 * -_t99)), (_t56 * _t78), Math.fma((Math.fma(_t99, _t101, _t97 * -_t102)), (_t59 * _t78), -((Math.fma(_t96, _t101, _t98 * -_t102)) * (_t57 * _t78))));
    }

    /** Part 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_s13d521b0_2(double _t48, double _t49, double _t51, double _t52, double _t54, double _t55, double _t57, double _t58, double _t59, double _t76, double _t77, double _t78) {
        double _t96 = _t48 * _t76;
        double _t97 = _t52 * _t77;
        double _t98 = _t55 * _t77;
        double _t99 = _t49 * _t76;
        double _t105 = _t51 * _t76;
        double _t106 = _t54 * _t77;
        return Math.fma((Math.fma(_t105, _t98, -(_t106 * _t96))), (_t57 * _t78), Math.fma((Math.fma(_t96, _t97, _t98 * -_t99)), (_t58 * _t78), -((Math.fma(_t105, _t97, _t106 * -_t99)) * (_t59 * _t78))));
    }

    /** Part 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_degenerate_s13d521b0_3(double[] dest, int destOffset, double _t48, double _t49, double _t50, double _t51, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t76, double _t77, double _t78, double _t79, double _t178, double _t179) {
        double _t96 = _t48 * _t76;
        double _t97 = _t52 * _t77;
        double _t98 = _t55 * _t77;
        double _t99 = _t49 * _t76;
        double _t100 = _t56 * _t78;
        double _t101 = _t53 * _t77;
        double _t102 = _t50 * _t76;
        double _t105 = _t51 * _t76;
        double _t106 = _t54 * _t77;
        double _t107 = _t58 * _t78;
        double _t153 = Math.fma(_t105, _t101, _t106 * -_t102);
        double _t180 = Math.fma((Math.fma(_t105, _t98, -(_t106 * _t96))), _t100, Math.fma((Math.fma(_t96, _t101, _t98 * -_t102)), _t107, -(_t153 * (_t59 * _t78))));
        double _t181 = Math.fma((Math.fma(_t105, _t97, _t106 * -_t99)), _t100, Math.fma((Math.fma(_t99, _t101, _t97 * -_t102)), _t107, -(_t153 * (_t57 * _t78))));
        double _t187_inv = 1.0 / Math.fma(-_t179, (_t61 * _t79), Math.fma(_t180, (_t60 * _t79), Math.fma(_t178, (_t62 * _t79), -(_t181 * (_t63 * _t79)))));
        double _sp0 = _t79 * _t187_inv;
        dest[destOffset] = _t178 * _sp0;
        dest[destOffset + 1] = -(_t181 * _sp0);
        dest[destOffset + 2] = _t180 * _sp0;
        dest[destOffset + 3] = -(_t179 * _sp0);
        return _t187_inv;
    }

    /** Part 4 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_s13d521b0_4(double[] dest, int destOffset, double _t48, double _t49, double _t50, double _t51, double _t52, double _t53, double _t54, double _t55, double _t60, double _t61, double _t62, double _t63, double _t76, double _t77, double _t78, double _t79, double _t187_inv) {
        double _t96 = _t48 * _t76;
        double _t97 = _t52 * _t77;
        double _t98 = _t55 * _t77;
        double _t99 = _t49 * _t76;
        double _t101 = _t53 * _t77;
        double _t102 = _t50 * _t76;
        double _t105 = _t51 * _t76;
        double _t106 = _t54 * _t77;
        double _t108 = _t61 * _t79;
        double _t109 = _t60 * _t79;
        double _t110 = _t62 * _t79;
        double _t111 = _t63 * _t79;
        double _t148 = Math.fma(_t96, _t97, _t98 * -_t99);
        double _t149 = Math.fma(_t99, _t101, _t97 * -_t102);
        double _t150 = Math.fma(_t96, _t101, _t98 * -_t102);
        double _t151 = Math.fma(_t105, _t98, -(_t106 * _t96));
        double _t152 = Math.fma(_t105, _t97, _t106 * -_t99);
        double _t153 = Math.fma(_t105, _t101, _t106 * -_t102);
        double _sp1 = _t78 * _t187_inv;
        dest[destOffset + 4] = -Math.fma(_t148, _t108, Math.fma(_t149, _t111, _t150 * -_t109)) * _sp1;
        dest[destOffset + 5] = Math.fma(_t152, _t108, Math.fma(_t149, _t110, _t153 * -_t109)) * _sp1;
        dest[destOffset + 6] = -Math.fma(_t151, _t108, Math.fma(_t150, _t110, _t153 * -_t111)) * _sp1;
        dest[destOffset + 7] = Math.fma(_t151, _t109, Math.fma(_t148, _t110, _t152 * -_t111)) * _sp1;
    }

    /** Part 5 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_degenerate_s13d521b0_5(double[] dest, int destOffset, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t76, double _t77, double _t78, double _t79, double _t187_inv) {
        double _t97 = _t52 * _t77;
        double _t98 = _t55 * _t77;
        double _t100 = _t56 * _t78;
        double _t101 = _t53 * _t77;
        double _t103 = _t59 * _t78;
        double _t104 = _t57 * _t78;
        double _t106 = _t54 * _t77;
        double _t107 = _t58 * _t78;
        double _t108 = _t61 * _t79;
        double _t109 = _t60 * _t79;
        double _t110 = _t62 * _t79;
        double _t111 = _t63 * _t79;
        double _t154 = Math.fma(_t103, _t97, _t98 * -_t104);
        double _t155 = Math.fma(_t104, _t101, _t97 * -_t100);
        double _t156 = Math.fma(_t103, _t101, _t98 * -_t100);
        double _t160 = Math.fma(_t107, _t97, _t106 * -_t104);
        double _t161 = Math.fma(_t107, _t101, _t106 * -_t100);
        double _t164 = Math.fma(_t107, _t98, _t106 * -_t103);
        double _sp2 = _t76 * _t187_inv;
        dest[destOffset + 8] = Math.fma(_t154, _t108, Math.fma(_t155, _t111, _t156 * -_t109)) * _sp2;
        dest[destOffset + 9] = -Math.fma(_t160, _t108, Math.fma(_t155, _t110, _t161 * -_t109)) * _sp2;
        dest[destOffset + 10] = Math.fma(_t164, _t108, Math.fma(_t156, _t110, _t161 * -_t111)) * _sp2;
        dest[destOffset + 11] = -Math.fma(_t164, _t109, Math.fma(_t154, _t110, _t160 * -_t111)) * _sp2;
    }

    /** Part 6 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_6(double[] dest, int destOffset, double _t48, double _t49, double _t50, double _t51, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t76, double _t77, double _t78, double _t79, double _t187_inv) {
        double _t96 = _t48 * _t76;
        double _t99 = _t49 * _t76;
        double _t100 = _t56 * _t78;
        double _t102 = _t50 * _t76;
        double _t103 = _t59 * _t78;
        double _t104 = _t57 * _t78;
        double _t105 = _t51 * _t76;
        double _t107 = _t58 * _t78;
        double _t108 = _t61 * _t79;
        double _t109 = _t60 * _t79;
        double _t110 = _t62 * _t79;
        double _t111 = _t63 * _t79;
        double _t157 = Math.fma(_t103, _t99, _t96 * -_t104);
        double _t158 = Math.fma(_t104, _t102, _t99 * -_t100);
        double _t159 = Math.fma(_t103, _t102, _t96 * -_t100);
        double _t162 = Math.fma(_t107, _t99, _t105 * -_t104);
        double _t163 = Math.fma(_t107, _t102, _t105 * -_t100);
        double _t165 = Math.fma(_t107, _t96, _t105 * -_t103);
        double _sp3 = _t77 * _t187_inv;
        dest[destOffset + 12] = -Math.fma(_t157, _t108, Math.fma(_t158, _t111, _t159 * -_t109)) * _sp3;
        dest[destOffset + 13] = Math.fma(_t162, _t108, Math.fma(_t158, _t110, _t163 * -_t109)) * _sp3;
        dest[destOffset + 14] = -Math.fma(_t165, _t108, Math.fma(_t159, _t110, _t163 * -_t111)) * _sp3;
        dest[destOffset + 15] = Math.fma(_t165, _t109, Math.fma(_t157, _t110, _t162 * -_t111)) * _sp3;
        return dest;
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_7(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _other00, double _other10, double _other20, double _other30, double _other01, double _other11, double _other21, double _other31, double _other02, double _other12, double _other22, double _other32, double _other03, double _other13, double _other23, double _other33, double _t48) {
        return invertProduct_degenerate_s13d521b0_8(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _self03, _self13, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22, _other32, _other03, _other13, _other23, _other33, _t48, Math.fma(_other32, _self23, Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21))), Math.fma(_other33, _self23, Math.fma(_other23, _self22, Math.fma(_other03, _self20, _other13 * _self21))), Math.fma(_other30, _self23, Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21))), Math.fma(_other32, _self33, Math.fma(_other22, _self32, Math.fma(_other02, _self30, _other12 * _self31))), Math.fma(_other33, _self33, Math.fma(_other23, _self32, Math.fma(_other03, _self30, _other13 * _self31))), Math.fma(_other30, _self33, Math.fma(_other20, _self32, Math.fma(_other00, _self30, _other10 * _self31))), Math.fma(_other31, _self33, Math.fma(_other21, _self32, Math.fma(_other01, _self30, _other11 * _self31))), Math.fma(_other33, _self13, Math.fma(_other23, _self12, Math.fma(_other03, _self10, _other13 * _self11))), Math.fma(_other32, _self13, Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11))));
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_8(double[] dest, int destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _self03, double _self13, double _other00, double _other10, double _other20, double _other30, double _other01, double _other11, double _other21, double _other31, double _other02, double _other12, double _other22, double _other32, double _other03, double _other13, double _other23, double _other33, double _t48, double _t49, double _t50, double _t51, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57) {
        double _t58 = Math.fma(_other30, _self13, Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11)));
        double _t59 = Math.fma(_other31, _self13, Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)));
        double _t60 = Math.fma(_other32, _self03, Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01)));
        double _t61 = Math.fma(_other33, _self03, Math.fma(_other23, _self02, Math.fma(_other03, _self00, _other13 * _self01)));
        double _t62 = Math.fma(_other30, _self03, Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01)));
        double _t63 = Math.fma(_other31, _self03, Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01)));
        return invertProduct_degenerate_s13d521b0_9(dest, destOffset, _t48, _t49, _t50, _t51, _t52, _t53, _t54, _t55, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, unitScale(_t49, _t50, Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t48))), unitScale(_t52, _t53, Math.max(java.lang.Math.abs(_t54), java.lang.Math.abs(_t55))), unitScale(_t57, _t56, Math.max(java.lang.Math.abs(_t58), java.lang.Math.abs(_t59))), unitScale(_t60, _t61, Math.max(java.lang.Math.abs(_t62), java.lang.Math.abs(_t63))));
    }

    /** Piece 4 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_degenerate_s13d521b0_9(double[] dest, int destOffset, double _t48, double _t49, double _t50, double _t51, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t76, double _t77, double _t78, double _t79) {
        double _t187_inv = invertProduct_degenerate_s13d521b0_3(dest, destOffset, _t48, _t49, _t50, _t51, _t52, _t53, _t54, _t55, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t76, _t77, _t78, _t79, invertProduct_degenerate_s13d521b0_1(_t48, _t49, _t50, _t52, _t53, _t55, _t56, _t57, _t59, _t76, _t77, _t78), invertProduct_degenerate_s13d521b0_2(_t48, _t49, _t51, _t52, _t54, _t55, _t57, _t58, _t59, _t76, _t77, _t78));
        invertProduct_degenerate_s13d521b0_4(dest, destOffset, _t48, _t49, _t50, _t51, _t52, _t53, _t54, _t55, _t60, _t61, _t62, _t63, _t76, _t77, _t78, _t79, _t187_inv);
        invertProduct_degenerate_s13d521b0_5(dest, destOffset, _t52, _t53, _t54, _t55, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t76, _t77, _t78, _t79, _t187_inv);
        return invertProduct_degenerate_s13d521b0_6(dest, destOffset, _t48, _t49, _t50, _t51, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t76, _t77, _t78, _t79, _t187_inv);
    }

    public static double[] normal_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t12 = unitScale(_self22, _self23, Math.max(java.lang.Math.abs(_self20), java.lang.Math.abs(_self21)));
        double _t13 = unitScale(_self32, _self33, Math.max(java.lang.Math.abs(_self30), java.lang.Math.abs(_self31)));
        double _t14 = unitScale(_self12, _self13, Math.max(java.lang.Math.abs(_self10), java.lang.Math.abs(_self11)));
        double _t15 = unitScale(_self02, _self03, Math.max(java.lang.Math.abs(_self00), java.lang.Math.abs(_self01)));
        return normal_degenerate_s1987ae41_1(dest, destOffset, _t12, _t13, _t14, _t15, _self21 * _t12, _self32 * _t13, _self22 * _t12, _self31 * _t13, _self13 * _t14, _self33 * _t13, _self23 * _t12, _self11 * _t14, _self12 * _t14, _self20 * _t12, _self30 * _t13, _self10 * _t14, _self03 * _t15, _self02 * _t15, _self00 * _t15, _self01 * _t15);
    }

    /** Piece 2 of {@code normal_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] normal_degenerate_s1987ae41_1(double[] dest, int destOffset, double _t12, double _t13, double _t14, double _t15, double _t32, double _t33, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t40, double _t41, double _t42, double _t43, double _t44, double _t45, double _t46, double _t47) {
        double _t84 = Math.fma(_t32, _t33, -_t34 * _t35);
        double _t85 = Math.fma(_t34, _t37, -_t38 * _t33);
        double _t86 = Math.fma(_t32, _t37, -_t38 * _t35);
        return normal_degenerate_s1987ae41_2(dest, destOffset, _t12, _t13, _t14, _t15, _t36, _t39, _t40, _t43, _t44, _t45, _t46, _t47, _t84, _t85, _t86, Math.fma(_t41, _t35, -(_t32 * _t42)), Math.fma(_t41, _t33, -_t34 * _t42), Math.fma(_t41, _t37, -_t38 * _t42), Math.fma(_t39, _t33, -_t40 * _t35), Math.fma(_t40, _t37, -_t36 * _t33), Math.fma(_t39, _t37, -_t36 * _t35), Math.fma(_t43, _t33, -_t40 * _t42), Math.fma(_t43, _t37, -_t36 * _t42), Math.fma(_t43, _t35, -_t39 * _t42), Math.fma(_t39, _t34, -_t40 * _t32), Math.fma(_t40, _t38, -_t36 * _t34), Math.fma(_t39, _t38, -_t36 * _t32), Math.fma(_t43, _t34, -_t40 * _t41), Math.fma(_t43, _t38, -_t36 * _t41), Math.fma(_t43, _t32, -_t39 * _t41), Math.fma(_t84, _t36, Math.fma(_t85, _t39, -(_t86 * _t40))));
    }

    /** Piece 3 of {@code normal_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] normal_degenerate_s1987ae41_2(double[] dest, int destOffset, double _t12, double _t13, double _t14, double _t15, double _t36, double _t39, double _t40, double _t43, double _t44, double _t45, double _t46, double _t47, double _t84, double _t85, double _t86, double _t87, double _t88, double _t89, double _t90, double _t91, double _t92, double _t93, double _t94, double _t95, double _t96, double _t97, double _t98, double _t99, double _t100, double _t101, double _t114) {
        double _t115 = Math.fma(_t87, _t40, Math.fma(_t84, _t43, -(_t88 * _t39)));
        double _t116 = Math.fma(_t87, _t36, Math.fma(_t86, _t43, -(_t89 * _t39)));
        double _t117 = Math.fma(_t88, _t36, Math.fma(_t85, _t43, -(_t89 * _t40)));
        double _t123_inv = 1.0 / Math.fma(-_t115, _t44, Math.fma(_t116, _t45, Math.fma(_t114, _t46, -(_t117 * _t47))));
        double _sp3 = _t13 * _t123_inv;
        double _sp2 = _t12 * _t123_inv;
        double _sp1 = _t14 * _t123_inv;
        double _sp0 = _t15 * _t123_inv;
        dest[destOffset] = _t114 * _sp0;
        dest[destOffset + 1] = -Math.fma(_t84, _t44, Math.fma(_t85, _t47, _t86 * -_t45)) * _sp1;
        dest[destOffset + 2] = Math.fma(_t90, _t44, Math.fma(_t91, _t47, _t92 * -_t45)) * _sp2;
        dest[destOffset + 3] = -Math.fma(_t96, _t44, Math.fma(_t97, _t47, _t98 * -_t45)) * _sp3;
        dest[destOffset + 4] = -(_t117 * _sp0);
        return normal_degenerate_s1987ae41_3(dest, destOffset, _t44, _t45, _t46, _t47, _t84, _t85, _t86, _t87, _t88, _t89, _t90, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, _t101, _t115, _t116, _sp3, _sp2, _sp1, _sp0);
    }

    /** Piece 4 of {@code normal_degenerate}, split to fit the inline budget; reached only through it. */
    private static double[] normal_degenerate_s1987ae41_3(double[] dest, int destOffset, double _t44, double _t45, double _t46, double _t47, double _t84, double _t85, double _t86, double _t87, double _t88, double _t89, double _t90, double _t91, double _t92, double _t93, double _t94, double _t95, double _t96, double _t97, double _t98, double _t99, double _t100, double _t101, double _t115, double _t116, double _sp3, double _sp2, double _sp1, double _sp0) {
        dest[destOffset + 5] = Math.fma(_t88, _t44, Math.fma(_t85, _t46, _t89 * -_t45)) * _sp1;
        dest[destOffset + 6] = -Math.fma(_t93, _t44, Math.fma(_t91, _t46, _t94 * -_t45)) * _sp2;
        dest[destOffset + 7] = Math.fma(_t99, _t44, Math.fma(_t97, _t46, _t100 * -_t45)) * _sp3;
        dest[destOffset + 8] = _t116 * _sp0;
        dest[destOffset + 9] = -Math.fma(_t87, _t44, Math.fma(_t86, _t46, _t89 * -_t47)) * _sp1;
        dest[destOffset + 10] = Math.fma(_t95, _t44, Math.fma(_t92, _t46, _t94 * -_t47)) * _sp2;
        dest[destOffset + 11] = -Math.fma(_t101, _t44, Math.fma(_t98, _t46, _t100 * -_t47)) * _sp3;
        dest[destOffset + 12] = -(_t115 * _sp0);
        dest[destOffset + 13] = Math.fma(_t87, _t45, Math.fma(_t84, _t46, _t88 * -_t47)) * _sp1;
        dest[destOffset + 14] = -Math.fma(_t95, _t45, Math.fma(_t90, _t46, _t93 * -_t47)) * _sp2;
        dest[destOffset + 15] = Math.fma(_t101, _t45, Math.fma(_t96, _t46, _t99 * -_t47)) * _sp3;
        return dest;
    }

    public static double[] normal_affine(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _t12 = Math.fma(_self11, _self22, -_self12 * _self21);
        double _t13 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t14 = Math.fma(_self10, _self22, -_self12 * _self20);
        double _t22 = Math.fma(_self02, _t13, Math.fma(_self00, _t12, -(_self01 * _t14)));
        if (!(java.lang.Math.abs(_t22) > 2.2250738585072014E-308 && java.lang.Math.abs(_t22) < 4.49423283715579E307)) return Double4x4OpsKernelsArray.normal_degenerate(dest, destOffset, src, srcOffset);
        double _t22_inv = 1.0 / _t22;
        dest[destOffset] = _t12 * _t22_inv;
        dest[destOffset + 1] = Math.fma(_self02, _self21, -_self01 * _self22) * _t22_inv;
        return normal_affine_s702f0754_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _self03, _t12, _t13, _t14, Math.fma(_self12, _self23, -_self13 * _self22), Math.fma(_self11, _self23, -_self13 * _self21), Math.fma(_self10, _self23, -_self13 * _self20), _t22_inv);
    }

    /** Piece 2 of {@code normal_affine}, split to fit the inline budget; reached only through it. */
    private static double[] normal_affine_s702f0754_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _self03, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t22_inv) {
        dest[destOffset + 2] = Math.fma(_self01, _self12, -_self02 * _self11) * _t22_inv;
        dest[destOffset + 3] = -Math.fma(_self03, _t12, Math.fma(_self01, _t15, -_self02 * _t16)) * _t22_inv;
        dest[destOffset + 4] = Math.fma(_self12, _self20, -(_self10 * _self22)) * _t22_inv;
        dest[destOffset + 5] = Math.fma(_self00, _self22, -_self02 * _self20) * _t22_inv;
        dest[destOffset + 6] = Math.fma(_self02, _self10, -_self00 * _self12) * _t22_inv;
        dest[destOffset + 7] = Math.fma(_self03, _t14, Math.fma(_self00, _t15, -_self02 * _t17)) * _t22_inv;
        dest[destOffset + 8] = _t13 * _t22_inv;
        dest[destOffset + 9] = Math.fma(_self01, _self20, -_self00 * _self21) * _t22_inv;
        dest[destOffset + 10] = Math.fma(_self00, _self11, -_self01 * _self10) * _t22_inv;
        dest[destOffset + 11] = -Math.fma(_self03, _t13, Math.fma(_self00, _t16, -_self01 * _t17)) * _t22_inv;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 14] = 0.0;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] toRigid_degenerate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
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
                    _t168 = Math.fma(_t35, _t106, -_t33 * _t116);
                    _t171 = Math.fma(_t34, _t116, -(_t35 * _t102));
                    _t169 = _t33;
                    _t172 = _t35;
                    _t173 = _t34;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t165 = Math.fma(_t36, _t105, -_t37 * _t114);
                    _t167 = Math.fma(_t37, _t103, -(_t38 * _t105));
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                    _t166 = _t36;
                    _t168 = _t38;
                    _t171 = _t37;
                    _t169 = _t105;
                    _t172 = _t114;
                    _t173 = _t103;
                } else {
                    _t165 = Math.fma(_t33, _t36, _t35 * -_t37);
                    _t167 = Math.fma(_t34, _t37, -_t33 * _t38);
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
                    _t173 = Math.fma(_t41, _t107, -_t40 * _t115);
                } else {
                    _t165 = _t39;
                    _t167 = _t41;
                    _t170 = _t40;
                    _t166 = Math.fma(_t33, _t39, -(_t34 * _t40));
                    _t168 = Math.fma(_t35, _t40, -_t33 * _t41);
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
                    _t173 = Math.fma(_t41, _t37, -_t40 * _t36);
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
            if (_t194 > Math.max(_t167, _t171)) {
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
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
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
                    _t171 = Math.fma(_t37, _t109, -_t35 * _t119);
                    _t174 = Math.fma(_t36, _t119, -(_t37 * _t105));
                    _t172 = _t35;
                    _t175 = _t37;
                    _t176 = _t36;
                }
            } else {
                if (_t29 <= 0.0) {
                    _t168 = Math.fma(_t38, _t108, -_t39 * _t117);
                    _t170 = Math.fma(_t39, _t106, -(_t40 * _t108));
                    _t173 = Math.fma(_t40, _t117, -(_t38 * _t106));
                    _t169 = _t38;
                    _t171 = _t40;
                    _t174 = _t39;
                    _t172 = _t108;
                    _t175 = _t117;
                    _t176 = _t106;
                } else {
                    _t168 = Math.fma(_t35, _t38, _t37 * -_t39);
                    _t170 = Math.fma(_t36, _t39, -_t35 * _t40);
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
                    _t176 = Math.fma(_t43, _t110, -_t42 * _t118);
                } else {
                    _t168 = _t41;
                    _t170 = _t43;
                    _t173 = _t42;
                    _t169 = Math.fma(_t35, _t41, -(_t36 * _t42));
                    _t171 = Math.fma(_t37, _t42, -_t35 * _t43);
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
                    _t176 = Math.fma(_t43, _t39, -_t42 * _t38);
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

    public static double[] frustumAabb_no(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = _self03 + _self33;
        double _t1 = _self11 + _self31;
        double _t2 = _self22 + _self32;
        double _t3 = _self12 + _self32;
        double _t4 = _self21 + _self31;
        double _t5 = _self01 + _self31;
        double _t6 = _self23 + _self33;
        double _t7 = _self13 + _self33;
        double _t8 = _self02 + _self32;
        double _t9 = _self10 + _self30;
        double _t10 = _self20 + _self30;
        double _t11 = _self00 + _self30;
        double _t12 = _self21 - _self31;
        double _t13 = _self20 - _self30;
        double _t14 = _self22 - _self32;
        double _t15 = _self23 - _self33;
        double _t16 = _self03 - _self33;
        double _t17 = _self01 - _self31;
        double _t18 = _self02 - _self32;
        double _t19 = _self00 - _self30;
        double _t20 = _self11 - _self31;
        double _t21 = _self12 - _self32;
        double _t22 = _self13 - _self33;
        double _t23 = _self10 - _self30;
        double _t72 = Math.fma(_t1, _t2, -_t3 * _t4);
        double _t73 = Math.fma(_t3, _t6, -_t7 * _t2);
        double _t74 = Math.fma(_t1, _t6, -_t7 * _t4);
        double _t75 = Math.fma(_t9, _t4, -_t1 * _t10);
        double _t76 = Math.fma(_t9, _t2, -_t3 * _t10);
        double _t77 = Math.fma(_t9, _t12, -_t1 * _t13);
        double _t78 = Math.fma(_t1, _t14, -_t3 * _t12);
        double _t79 = Math.fma(_t9, _t14, -_t3 * _t13);
        double _t80 = Math.fma(_t3, _t15, -_t7 * _t14);
        double _t81 = Math.fma(_t1, _t15, -_t7 * _t12);
        double _t82 = Math.fma(_t2, _t20, _t4 * -_t21);
        double _t83 = Math.fma(_t6, _t21, _t2 * -_t22);
        double _t84 = Math.fma(_t6, _t20, _t4 * -_t22);
        double _t85 = Math.fma(_t4, _t23, _t10 * -_t20);
        double _t86 = Math.fma(_t2, _t23, _t10 * -_t21);
        double _t87 = Math.fma(_t23, _t12, -_t20 * _t13);
        double _t88 = Math.fma(_t20, _t14, -_t21 * _t12);
        double _t89 = Math.fma(_t23, _t14, -_t21 * _t13);
        double _t90 = Math.fma(_t21, _t15, -_t22 * _t14);
        double _t91 = Math.fma(_t20, _t15, -_t22 * _t12);
        double _t92 = Math.fma(_t9, _t6, -_t7 * _t10);
        double _t93 = Math.fma(_t9, _t15, -_t7 * _t13);
        double _t94 = Math.fma(_t6, _t23, _t10 * -_t22);
        double _t95 = Math.fma(_t23, _t15, -_t22 * _t13);
        double _t193 = Math.fma(_t8, _t75, Math.fma(_t11, _t72, -(_t5 * _t76)));
        double _t194 = Math.fma(_t8, _t77, Math.fma(_t11, _t78, -(_t5 * _t79)));
        double _t197 = Math.fma(_t18, _t75, Math.fma(_t19, _t72, -(_t17 * _t76)));
        double _t198 = Math.fma(_t18, _t77, Math.fma(_t19, _t78, -(_t17 * _t79)));
        double _t201 = Math.fma(_t18, _t85, Math.fma(_t19, _t82, -(_t17 * _t86)));
        double _t202 = Math.fma(_t18, _t87, Math.fma(_t19, _t88, -(_t17 * _t89)));
        double _t205 = Math.fma(_t8, _t85, Math.fma(_t11, _t82, -_t5 * _t86));
        double _t206 = Math.fma(_t8, _t87, Math.fma(_t11, _t88, -_t5 * _t89));
        double _t224 = java.lang.Math.abs(_t193);
        double _t225 = java.lang.Math.abs(_t194);
        double _t226 = java.lang.Math.abs(_t197);
        double _t227 = java.lang.Math.abs(_t198);
        double _t228 = java.lang.Math.abs(_t201);
        double _t229 = java.lang.Math.abs(_t202);
        double _t230 = java.lang.Math.abs(_t205);
        double _t231 = java.lang.Math.abs(_t206);
        double _t248_inv = 1.0 / (_t224 > _t225 * 9.094947017729282E-13 ? _t193 : Math.copySign(0.0, _t194));
        double _t249_inv = 1.0 / (_t225 > _t224 * 9.094947017729282E-13 ? _t194 : Math.copySign(0.0, _t193));
        double _t250_inv = 1.0 / (_t226 > _t227 * 9.094947017729282E-13 ? _t197 : Math.copySign(0.0, _t198));
        double _t251_inv = 1.0 / (_t227 > _t226 * 9.094947017729282E-13 ? _t198 : Math.copySign(0.0, _t197));
        double _t252_inv = 1.0 / (_t228 > _t229 * 9.094947017729282E-13 ? _t201 : Math.copySign(0.0, _t202));
        double _t253_inv = 1.0 / (_t229 > _t228 * 9.094947017729282E-13 ? _t202 : Math.copySign(0.0, _t201));
        double _t254_inv = 1.0 / (_t230 > _t231 * 9.094947017729282E-13 ? _t205 : Math.copySign(0.0, _t206));
        double _t255_inv = 1.0 / (_t231 > _t230 * 9.094947017729282E-13 ? _t206 : Math.copySign(0.0, _t205));
        double _t264 = Math.fma(_t0, _t76, Math.fma(_t11, _t73, -(_t8 * _t92))) * _t248_inv;
        double _t265 = Math.fma(_t0, _t79, Math.fma(_t11, _t80, -(_t8 * _t93))) * _t249_inv;
        double _t266 = Math.fma(_t16, _t76, Math.fma(_t19, _t73, -(_t18 * _t92))) * _t250_inv;
        double _t267 = Math.fma(_t16, _t79, Math.fma(_t19, _t80, -(_t18 * _t93))) * _t251_inv;
        double _t268 = Math.fma(_t16, _t86, Math.fma(_t19, _t83, -_t18 * _t94)) * _t252_inv;
        double _t269 = Math.fma(_t16, _t89, Math.fma(_t19, _t90, -_t18 * _t95)) * _t253_inv;
        double _t270 = Math.fma(_t0, _t86, Math.fma(_t11, _t83, -_t8 * _t94)) * _t254_inv;
        double _t271 = Math.fma(_t0, _t89, Math.fma(_t11, _t90, -_t8 * _t95)) * _t255_inv;
        double _t280 = -Math.fma(_t0, _t72, Math.fma(_t5, _t73, -(_t8 * _t74))) * _t248_inv;
        double _t281 = -Math.fma(_t0, _t78, Math.fma(_t5, _t80, -(_t8 * _t81))) * _t249_inv;
        double _t282 = -Math.fma(_t16, _t72, Math.fma(_t17, _t73, -(_t18 * _t74))) * _t250_inv;
        double _t283 = -Math.fma(_t16, _t78, Math.fma(_t17, _t80, -(_t18 * _t81))) * _t251_inv;
        double _t284 = -Math.fma(_t16, _t82, Math.fma(_t17, _t83, -_t18 * _t84)) * _t252_inv;
        double _t285 = -Math.fma(_t16, _t88, Math.fma(_t17, _t90, -_t18 * _t91)) * _t253_inv;
        double _t286 = -Math.fma(_t0, _t82, Math.fma(_t5, _t83, -_t8 * _t84)) * _t254_inv;
        double _t287 = -Math.fma(_t0, _t88, Math.fma(_t5, _t90, -_t8 * _t91)) * _t255_inv;
        double _t288 = -Math.fma(_t0, _t75, Math.fma(_t11, _t74, -_t5 * _t92)) * _t248_inv;
        double _t289 = -Math.fma(_t0, _t77, Math.fma(_t11, _t81, -_t5 * _t93)) * _t249_inv;
        double _t290 = -Math.fma(_t16, _t75, Math.fma(_t19, _t74, -(_t17 * _t92))) * _t250_inv;
        double _t291 = -Math.fma(_t16, _t77, Math.fma(_t19, _t81, -(_t17 * _t93))) * _t251_inv;
        double _t292 = -Math.fma(_t16, _t85, Math.fma(_t19, _t84, -_t17 * _t94)) * _t252_inv;
        double _t293 = -Math.fma(_t16, _t87, Math.fma(_t19, _t91, -_t17 * _t95)) * _t253_inv;
        double _t294 = -Math.fma(_t0, _t85, Math.fma(_t11, _t84, -(_t5 * _t94))) * _t254_inv;
        double _t295 = -Math.fma(_t0, _t87, Math.fma(_t11, _t91, -(_t5 * _t95))) * _t255_inv;
        double _t296 = _t264 != _t264 ? _t265 : _t264;
        double _t297 = _t266 != _t266 ? _t267 : _t266;
        double _t298 = _t268 != _t268 ? _t269 : _t268;
        double _t299 = _t270 != _t270 ? _t271 : _t270;
        double _t300 = _t265 != _t265 ? _t264 : _t265;
        double _t301 = _t267 != _t267 ? _t266 : _t267;
        double _t302 = _t269 != _t269 ? _t268 : _t269;
        double _t303 = _t271 != _t271 ? _t270 : _t271;
        double _t304 = _t280 != _t280 ? _t281 : _t280;
        double _t305 = _t282 != _t282 ? _t283 : _t282;
        double _t306 = _t284 != _t284 ? _t285 : _t284;
        double _t307 = _t286 != _t286 ? _t287 : _t286;
        double _t308 = _t281 != _t281 ? _t280 : _t281;
        double _t309 = _t283 != _t283 ? _t282 : _t283;
        double _t310 = _t285 != _t285 ? _t284 : _t285;
        double _t311 = _t287 != _t287 ? _t286 : _t287;
        double _t312 = _t288 != _t288 ? _t289 : _t288;
        double _t313 = _t290 != _t290 ? _t291 : _t290;
        double _t314 = _t292 != _t292 ? _t293 : _t292;
        double _t315 = _t294 != _t294 ? _t295 : _t294;
        double _t316 = _t289 != _t289 ? _t288 : _t289;
        double _t317 = _t291 != _t291 ? _t290 : _t291;
        double _t318 = _t293 != _t293 ? _t292 : _t293;
        double _t319 = _t295 != _t295 ? _t294 : _t295;
        dest[destOffset] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t304, _t305), _t306), _t307), _t308), _t309), _t310), _t311);
        dest[destOffset + 1] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t296, _t297), _t298), _t299), _t300), _t301), _t302), _t303);
        dest[destOffset + 2] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t312, _t313), _t314), _t315), _t316), _t317), _t318), _t319);
        dest[destOffset + 3] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t304, _t305), _t306), _t307), _t308), _t309), _t310), _t311);
        dest[destOffset + 4] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t296, _t297), _t298), _t299), _t300), _t301), _t302), _t303);
        dest[destOffset + 5] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t312, _t313), _t314), _t315), _t316), _t317), _t318), _t319);
        return dest;
    }

    public static double[] frustumAabb_zo(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = _self03 + _self33;
        double _t1 = _self11 + _self31;
        double _t2 = _self12 + _self32;
        double _t3 = _self01 + _self31;
        double _t4 = _self13 + _self33;
        double _t5 = _self02 + _self32;
        double _t6 = _self10 + _self30;
        double _t7 = _self00 + _self30;
        double _t8 = _self21 - _self31;
        double _t9 = _self20 - _self30;
        double _t10 = _self22 - _self32;
        double _t11 = _self23 - _self33;
        double _t12 = _self03 - _self33;
        double _t13 = _self01 - _self31;
        double _t14 = _self02 - _self32;
        double _t15 = _self00 - _self30;
        double _t16 = _self11 - _self31;
        double _t17 = _self12 - _self32;
        double _t18 = _self13 - _self33;
        double _t19 = _self10 - _self30;
        double _t68 = Math.fma(_self22, _t1, -(_self21 * _t2));
        double _t69 = Math.fma(_self23, _t2, -(_self22 * _t4));
        double _t70 = Math.fma(_self23, _t1, -_self21 * _t4);
        double _t71 = Math.fma(_self21, _t6, -(_self20 * _t1));
        double _t72 = Math.fma(_self22, _t6, -(_self20 * _t2));
        double _t73 = Math.fma(_self22, _t16, -_self21 * _t17);
        double _t74 = Math.fma(_self23, _t17, -(_self22 * _t18));
        double _t75 = Math.fma(_self23, _t16, -(_self21 * _t18));
        double _t76 = Math.fma(_self21, _t19, -_self20 * _t16);
        double _t77 = Math.fma(_self22, _t19, -(_self20 * _t17));
        double _t78 = Math.fma(_self23, _t6, -_self20 * _t4);
        double _t79 = Math.fma(_self23, _t19, -(_self20 * _t18));
        double _t82 = Math.fma(_t6, _t8, -(_t1 * _t9));
        double _t83 = Math.fma(_t1, _t10, -_t2 * _t8);
        double _t84 = Math.fma(_t6, _t10, -_t2 * _t9);
        double _t85 = Math.fma(_t2, _t11, -_t4 * _t10);
        double _t86 = Math.fma(_t1, _t11, -_t4 * _t8);
        double _t91 = Math.fma(_t19, _t8, -(_t16 * _t9));
        double _t92 = Math.fma(_t16, _t10, -_t17 * _t8);
        double _t93 = Math.fma(_t19, _t10, -_t17 * _t9);
        double _t94 = Math.fma(_t17, _t11, -_t18 * _t10);
        double _t95 = Math.fma(_t16, _t11, -_t18 * _t8);
        double _t99 = Math.fma(_t6, _t11, -_t4 * _t9);
        double _t102 = Math.fma(_t19, _t11, -_t18 * _t9);
        double _t189 = Math.fma(_t5, _t71, Math.fma(_t7, _t68, -(_t3 * _t72)));
        double _t191 = Math.fma(_t14, _t71, Math.fma(_t15, _t68, -(_t13 * _t72)));
        double _t193 = Math.fma(_t14, _t76, Math.fma(_t15, _t73, -(_t13 * _t77)));
        double _t195 = Math.fma(_t5, _t76, Math.fma(_t7, _t73, -_t3 * _t77));
        double _t204 = java.lang.Math.abs(_t189);
        double _t205 = java.lang.Math.abs(_t191);
        double _t206 = java.lang.Math.abs(_t193);
        double _t207 = java.lang.Math.abs(_t195);
        double _t216 = Math.fma(_t5, _t82, Math.fma(_t7, _t83, -(_t3 * _t84)));
        double _t218 = Math.fma(_t14, _t82, Math.fma(_t15, _t83, -(_t13 * _t84)));
        double _t220 = Math.fma(_t14, _t91, Math.fma(_t15, _t92, -(_t13 * _t93)));
        double _t222 = Math.fma(_t5, _t91, Math.fma(_t7, _t92, -_t3 * _t93));
        double _t232 = java.lang.Math.abs(_t216);
        double _t233 = java.lang.Math.abs(_t218);
        double _t234 = java.lang.Math.abs(_t220);
        double _t235 = java.lang.Math.abs(_t222);
        double _t244_inv = 1.0 / (_t204 > _t232 * 9.094947017729282E-13 ? _t189 : Math.copySign(0.0, _t216));
        double _t245_inv = 1.0 / (_t232 > _t204 * 9.094947017729282E-13 ? _t216 : Math.copySign(0.0, _t189));
        double _t246_inv = 1.0 / (_t205 > _t233 * 9.094947017729282E-13 ? _t191 : Math.copySign(0.0, _t218));
        double _t247_inv = 1.0 / (_t233 > _t205 * 9.094947017729282E-13 ? _t218 : Math.copySign(0.0, _t191));
        double _t248_inv = 1.0 / (_t206 > _t234 * 9.094947017729282E-13 ? _t193 : Math.copySign(0.0, _t220));
        double _t249_inv = 1.0 / (_t234 > _t206 * 9.094947017729282E-13 ? _t220 : Math.copySign(0.0, _t193));
        double _t250_inv = 1.0 / (_t207 > _t235 * 9.094947017729282E-13 ? _t195 : Math.copySign(0.0, _t222));
        double _t251_inv = 1.0 / (_t235 > _t207 * 9.094947017729282E-13 ? _t222 : Math.copySign(0.0, _t195));
        double _t256 = Math.fma(_t0, _t72, Math.fma(_t7, _t69, -(_t5 * _t78))) * _t244_inv;
        double _t257 = Math.fma(_t12, _t72, Math.fma(_t15, _t69, -(_t14 * _t78))) * _t246_inv;
        double _t258 = Math.fma(_t12, _t77, Math.fma(_t15, _t74, -_t14 * _t79)) * _t248_inv;
        double _t259 = Math.fma(_t0, _t77, Math.fma(_t7, _t74, -_t5 * _t79)) * _t250_inv;
        double _t264 = -Math.fma(_t0, _t68, Math.fma(_t3, _t69, -(_t5 * _t70))) * _t244_inv;
        double _t265 = -Math.fma(_t12, _t68, Math.fma(_t13, _t69, -(_t14 * _t70))) * _t246_inv;
        double _t266 = -Math.fma(_t12, _t73, Math.fma(_t13, _t74, -_t14 * _t75)) * _t248_inv;
        double _t267 = -Math.fma(_t0, _t73, Math.fma(_t3, _t74, -_t5 * _t75)) * _t250_inv;
        double _t268 = -Math.fma(_t0, _t71, Math.fma(_t7, _t70, -_t3 * _t78)) * _t244_inv;
        double _t269 = -Math.fma(_t12, _t71, Math.fma(_t15, _t70, -(_t13 * _t78))) * _t246_inv;
        double _t270 = -Math.fma(_t12, _t76, Math.fma(_t15, _t75, -_t13 * _t79)) * _t248_inv;
        double _t271 = -Math.fma(_t0, _t76, Math.fma(_t7, _t75, -(_t3 * _t79))) * _t250_inv;
        double _t276 = Math.fma(_t0, _t84, Math.fma(_t7, _t85, -(_t5 * _t99))) * _t245_inv;
        double _t277 = Math.fma(_t12, _t84, Math.fma(_t15, _t85, -(_t14 * _t99))) * _t247_inv;
        double _t278 = Math.fma(_t12, _t93, Math.fma(_t15, _t94, -_t14 * _t102)) * _t249_inv;
        double _t279 = Math.fma(_t0, _t93, Math.fma(_t7, _t94, -_t5 * _t102)) * _t251_inv;
        double _t284 = -Math.fma(_t0, _t83, Math.fma(_t3, _t85, -(_t5 * _t86))) * _t245_inv;
        double _t285 = -Math.fma(_t12, _t83, Math.fma(_t13, _t85, -(_t14 * _t86))) * _t247_inv;
        double _t286 = -Math.fma(_t12, _t92, Math.fma(_t13, _t94, -_t14 * _t95)) * _t249_inv;
        double _t287 = -Math.fma(_t0, _t92, Math.fma(_t3, _t94, -_t5 * _t95)) * _t251_inv;
        double _t288 = -Math.fma(_t0, _t82, Math.fma(_t7, _t86, -_t3 * _t99)) * _t245_inv;
        double _t289 = -Math.fma(_t12, _t82, Math.fma(_t15, _t86, -(_t13 * _t99))) * _t247_inv;
        double _t290 = -Math.fma(_t12, _t91, Math.fma(_t15, _t95, -_t13 * _t102)) * _t249_inv;
        double _t291 = -Math.fma(_t0, _t91, Math.fma(_t7, _t95, -(_t3 * _t102))) * _t251_inv;
        double _t292 = _t256 != _t256 ? _t276 : _t256;
        double _t293 = _t257 != _t257 ? _t277 : _t257;
        double _t294 = _t258 != _t258 ? _t278 : _t258;
        double _t295 = _t259 != _t259 ? _t279 : _t259;
        double _t296 = _t264 != _t264 ? _t284 : _t264;
        double _t297 = _t265 != _t265 ? _t285 : _t265;
        double _t298 = _t266 != _t266 ? _t286 : _t266;
        double _t299 = _t267 != _t267 ? _t287 : _t267;
        double _t300 = _t268 != _t268 ? _t288 : _t268;
        double _t301 = _t269 != _t269 ? _t289 : _t269;
        double _t302 = _t270 != _t270 ? _t290 : _t270;
        double _t303 = _t271 != _t271 ? _t291 : _t271;
        double _t304 = _t276 != _t276 ? _t256 : _t276;
        double _t305 = _t277 != _t277 ? _t257 : _t277;
        double _t306 = _t278 != _t278 ? _t258 : _t278;
        double _t307 = _t279 != _t279 ? _t259 : _t279;
        double _t308 = _t284 != _t284 ? _t264 : _t284;
        double _t309 = _t285 != _t285 ? _t265 : _t285;
        double _t310 = _t286 != _t286 ? _t266 : _t286;
        double _t311 = _t287 != _t287 ? _t267 : _t287;
        double _t312 = _t288 != _t288 ? _t268 : _t288;
        double _t313 = _t289 != _t289 ? _t269 : _t289;
        double _t314 = _t290 != _t290 ? _t270 : _t290;
        double _t315 = _t291 != _t291 ? _t271 : _t291;
        dest[destOffset] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t296, _t297), _t298), _t299), _t308), _t309), _t310), _t311);
        dest[destOffset + 1] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t292, _t293), _t294), _t295), _t304), _t305), _t306), _t307);
        dest[destOffset + 2] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t300, _t301), _t302), _t303), _t312), _t313), _t314), _t315);
        dest[destOffset + 3] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t296, _t297), _t298), _t299), _t308), _t309), _t310), _t311);
        dest[destOffset + 4] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t292, _t293), _t294), _t295), _t304), _t305), _t306), _t307);
        dest[destOffset + 5] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t300, _t301), _t302), _t303), _t312), _t313), _t314), _t315);
        return dest;
    }

    public static double[] frustumAabb_no(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = _self03 + _self33;
        double _t1 = _self11 + _self31;
        double _t2 = _self22 + _self32;
        double _t3 = _self12 + _self32;
        double _t4 = _self21 + _self31;
        double _t5 = _self01 + _self31;
        double _t6 = _self23 + _self33;
        double _t7 = _self13 + _self33;
        double _t8 = _self02 + _self32;
        double _t9 = _self10 + _self30;
        double _t10 = _self20 + _self30;
        double _t11 = _self00 + _self30;
        double _t12 = _self21 - _self31;
        double _t13 = _self20 - _self30;
        double _t14 = _self22 - _self32;
        double _t15 = _self23 - _self33;
        double _t16 = _self03 - _self33;
        double _t17 = _self01 - _self31;
        double _t18 = _self02 - _self32;
        double _t19 = _self00 - _self30;
        double _t20 = _self11 - _self31;
        double _t21 = _self12 - _self32;
        double _t22 = _self13 - _self33;
        double _t23 = _self10 - _self30;
        double _t72 = Math.fma(_t1, _t2, -_t3 * _t4);
        double _t73 = Math.fma(_t3, _t6, -_t7 * _t2);
        double _t74 = Math.fma(_t1, _t6, -_t7 * _t4);
        double _t75 = Math.fma(_t9, _t4, -_t1 * _t10);
        double _t76 = Math.fma(_t9, _t2, -_t3 * _t10);
        double _t77 = Math.fma(_t9, _t12, -_t1 * _t13);
        double _t78 = Math.fma(_t1, _t14, -_t3 * _t12);
        double _t79 = Math.fma(_t9, _t14, -_t3 * _t13);
        double _t80 = Math.fma(_t3, _t15, -_t7 * _t14);
        double _t81 = Math.fma(_t1, _t15, -_t7 * _t12);
        double _t82 = Math.fma(_t2, _t20, _t4 * -_t21);
        double _t83 = Math.fma(_t6, _t21, _t2 * -_t22);
        double _t84 = Math.fma(_t6, _t20, _t4 * -_t22);
        double _t85 = Math.fma(_t4, _t23, _t10 * -_t20);
        double _t86 = Math.fma(_t2, _t23, _t10 * -_t21);
        double _t87 = Math.fma(_t23, _t12, -_t20 * _t13);
        double _t88 = Math.fma(_t20, _t14, -_t21 * _t12);
        double _t89 = Math.fma(_t23, _t14, -_t21 * _t13);
        double _t90 = Math.fma(_t21, _t15, -_t22 * _t14);
        double _t91 = Math.fma(_t20, _t15, -_t22 * _t12);
        double _t92 = Math.fma(_t9, _t6, -_t7 * _t10);
        double _t93 = Math.fma(_t9, _t15, -_t7 * _t13);
        double _t94 = Math.fma(_t6, _t23, _t10 * -_t22);
        double _t95 = Math.fma(_t23, _t15, -_t22 * _t13);
        double _t193 = Math.fma(_t8, _t75, Math.fma(_t11, _t72, -(_t5 * _t76)));
        double _t194 = Math.fma(_t8, _t77, Math.fma(_t11, _t78, -(_t5 * _t79)));
        double _t197 = Math.fma(_t18, _t75, Math.fma(_t19, _t72, -(_t17 * _t76)));
        double _t198 = Math.fma(_t18, _t77, Math.fma(_t19, _t78, -(_t17 * _t79)));
        double _t201 = Math.fma(_t18, _t85, Math.fma(_t19, _t82, -(_t17 * _t86)));
        double _t202 = Math.fma(_t18, _t87, Math.fma(_t19, _t88, -(_t17 * _t89)));
        double _t205 = Math.fma(_t8, _t85, Math.fma(_t11, _t82, -_t5 * _t86));
        double _t206 = Math.fma(_t8, _t87, Math.fma(_t11, _t88, -_t5 * _t89));
        double _t224 = java.lang.Math.abs(_t193);
        double _t225 = java.lang.Math.abs(_t194);
        double _t226 = java.lang.Math.abs(_t197);
        double _t227 = java.lang.Math.abs(_t198);
        double _t228 = java.lang.Math.abs(_t201);
        double _t229 = java.lang.Math.abs(_t202);
        double _t230 = java.lang.Math.abs(_t205);
        double _t231 = java.lang.Math.abs(_t206);
        double _t248_inv = 1.0 / (_t224 > epsilon * _t225 ? _t193 : Math.copySign(0.0, _t194));
        double _t249_inv = 1.0 / (_t225 > epsilon * _t224 ? _t194 : Math.copySign(0.0, _t193));
        double _t250_inv = 1.0 / (_t226 > epsilon * _t227 ? _t197 : Math.copySign(0.0, _t198));
        double _t251_inv = 1.0 / (_t227 > epsilon * _t226 ? _t198 : Math.copySign(0.0, _t197));
        double _t252_inv = 1.0 / (_t228 > epsilon * _t229 ? _t201 : Math.copySign(0.0, _t202));
        double _t253_inv = 1.0 / (_t229 > epsilon * _t228 ? _t202 : Math.copySign(0.0, _t201));
        double _t254_inv = 1.0 / (_t230 > epsilon * _t231 ? _t205 : Math.copySign(0.0, _t206));
        double _t255_inv = 1.0 / (_t231 > epsilon * _t230 ? _t206 : Math.copySign(0.0, _t205));
        double _t264 = Math.fma(_t0, _t76, Math.fma(_t11, _t73, -(_t8 * _t92))) * _t248_inv;
        double _t265 = Math.fma(_t0, _t79, Math.fma(_t11, _t80, -(_t8 * _t93))) * _t249_inv;
        double _t266 = Math.fma(_t16, _t76, Math.fma(_t19, _t73, -(_t18 * _t92))) * _t250_inv;
        double _t267 = Math.fma(_t16, _t79, Math.fma(_t19, _t80, -(_t18 * _t93))) * _t251_inv;
        double _t268 = Math.fma(_t16, _t86, Math.fma(_t19, _t83, -_t18 * _t94)) * _t252_inv;
        double _t269 = Math.fma(_t16, _t89, Math.fma(_t19, _t90, -_t18 * _t95)) * _t253_inv;
        double _t270 = Math.fma(_t0, _t86, Math.fma(_t11, _t83, -_t8 * _t94)) * _t254_inv;
        double _t271 = Math.fma(_t0, _t89, Math.fma(_t11, _t90, -_t8 * _t95)) * _t255_inv;
        double _t280 = -Math.fma(_t0, _t72, Math.fma(_t5, _t73, -(_t8 * _t74))) * _t248_inv;
        double _t281 = -Math.fma(_t0, _t78, Math.fma(_t5, _t80, -(_t8 * _t81))) * _t249_inv;
        double _t282 = -Math.fma(_t16, _t72, Math.fma(_t17, _t73, -(_t18 * _t74))) * _t250_inv;
        double _t283 = -Math.fma(_t16, _t78, Math.fma(_t17, _t80, -(_t18 * _t81))) * _t251_inv;
        double _t284 = -Math.fma(_t16, _t82, Math.fma(_t17, _t83, -_t18 * _t84)) * _t252_inv;
        double _t285 = -Math.fma(_t16, _t88, Math.fma(_t17, _t90, -_t18 * _t91)) * _t253_inv;
        double _t286 = -Math.fma(_t0, _t82, Math.fma(_t5, _t83, -_t8 * _t84)) * _t254_inv;
        double _t287 = -Math.fma(_t0, _t88, Math.fma(_t5, _t90, -_t8 * _t91)) * _t255_inv;
        double _t288 = -Math.fma(_t0, _t75, Math.fma(_t11, _t74, -_t5 * _t92)) * _t248_inv;
        double _t289 = -Math.fma(_t0, _t77, Math.fma(_t11, _t81, -_t5 * _t93)) * _t249_inv;
        double _t290 = -Math.fma(_t16, _t75, Math.fma(_t19, _t74, -(_t17 * _t92))) * _t250_inv;
        double _t291 = -Math.fma(_t16, _t77, Math.fma(_t19, _t81, -(_t17 * _t93))) * _t251_inv;
        double _t292 = -Math.fma(_t16, _t85, Math.fma(_t19, _t84, -_t17 * _t94)) * _t252_inv;
        double _t293 = -Math.fma(_t16, _t87, Math.fma(_t19, _t91, -_t17 * _t95)) * _t253_inv;
        double _t294 = -Math.fma(_t0, _t85, Math.fma(_t11, _t84, -(_t5 * _t94))) * _t254_inv;
        double _t295 = -Math.fma(_t0, _t87, Math.fma(_t11, _t91, -(_t5 * _t95))) * _t255_inv;
        double _t296 = _t264 != _t264 ? _t265 : _t264;
        double _t297 = _t266 != _t266 ? _t267 : _t266;
        double _t298 = _t268 != _t268 ? _t269 : _t268;
        double _t299 = _t270 != _t270 ? _t271 : _t270;
        double _t300 = _t265 != _t265 ? _t264 : _t265;
        double _t301 = _t267 != _t267 ? _t266 : _t267;
        double _t302 = _t269 != _t269 ? _t268 : _t269;
        double _t303 = _t271 != _t271 ? _t270 : _t271;
        double _t304 = _t280 != _t280 ? _t281 : _t280;
        double _t305 = _t282 != _t282 ? _t283 : _t282;
        double _t306 = _t284 != _t284 ? _t285 : _t284;
        double _t307 = _t286 != _t286 ? _t287 : _t286;
        double _t308 = _t281 != _t281 ? _t280 : _t281;
        double _t309 = _t283 != _t283 ? _t282 : _t283;
        double _t310 = _t285 != _t285 ? _t284 : _t285;
        double _t311 = _t287 != _t287 ? _t286 : _t287;
        double _t312 = _t288 != _t288 ? _t289 : _t288;
        double _t313 = _t290 != _t290 ? _t291 : _t290;
        double _t314 = _t292 != _t292 ? _t293 : _t292;
        double _t315 = _t294 != _t294 ? _t295 : _t294;
        double _t316 = _t289 != _t289 ? _t288 : _t289;
        double _t317 = _t291 != _t291 ? _t290 : _t291;
        double _t318 = _t293 != _t293 ? _t292 : _t293;
        double _t319 = _t295 != _t295 ? _t294 : _t295;
        dest[destOffset] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t304, _t305), _t306), _t307), _t308), _t309), _t310), _t311);
        dest[destOffset + 1] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t296, _t297), _t298), _t299), _t300), _t301), _t302), _t303);
        dest[destOffset + 2] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t312, _t313), _t314), _t315), _t316), _t317), _t318), _t319);
        dest[destOffset + 3] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t304, _t305), _t306), _t307), _t308), _t309), _t310), _t311);
        dest[destOffset + 4] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t296, _t297), _t298), _t299), _t300), _t301), _t302), _t303);
        dest[destOffset + 5] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t312, _t313), _t314), _t315), _t316), _t317), _t318), _t319);
        return dest;
    }

    public static double[] frustumAabb_zo(double[] dest, int destOffset, double[] src, int srcOffset, double epsilon) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = _self03 + _self33;
        double _t1 = _self11 + _self31;
        double _t2 = _self12 + _self32;
        double _t3 = _self01 + _self31;
        double _t4 = _self13 + _self33;
        double _t5 = _self02 + _self32;
        double _t6 = _self10 + _self30;
        double _t7 = _self00 + _self30;
        double _t8 = _self21 - _self31;
        double _t9 = _self20 - _self30;
        double _t10 = _self22 - _self32;
        double _t11 = _self23 - _self33;
        double _t12 = _self03 - _self33;
        double _t13 = _self01 - _self31;
        double _t14 = _self02 - _self32;
        double _t15 = _self00 - _self30;
        double _t16 = _self11 - _self31;
        double _t17 = _self12 - _self32;
        double _t18 = _self13 - _self33;
        double _t19 = _self10 - _self30;
        double _t68 = Math.fma(_self22, _t1, -(_self21 * _t2));
        double _t69 = Math.fma(_self23, _t2, -(_self22 * _t4));
        double _t70 = Math.fma(_self23, _t1, -_self21 * _t4);
        double _t71 = Math.fma(_self21, _t6, -(_self20 * _t1));
        double _t72 = Math.fma(_self22, _t6, -(_self20 * _t2));
        double _t73 = Math.fma(_self22, _t16, -_self21 * _t17);
        double _t74 = Math.fma(_self23, _t17, -(_self22 * _t18));
        double _t75 = Math.fma(_self23, _t16, -(_self21 * _t18));
        double _t76 = Math.fma(_self21, _t19, -_self20 * _t16);
        double _t77 = Math.fma(_self22, _t19, -(_self20 * _t17));
        double _t78 = Math.fma(_self23, _t6, -_self20 * _t4);
        double _t79 = Math.fma(_self23, _t19, -(_self20 * _t18));
        double _t82 = Math.fma(_t6, _t8, -(_t1 * _t9));
        double _t83 = Math.fma(_t1, _t10, -_t2 * _t8);
        double _t84 = Math.fma(_t6, _t10, -_t2 * _t9);
        double _t85 = Math.fma(_t2, _t11, -_t4 * _t10);
        double _t86 = Math.fma(_t1, _t11, -_t4 * _t8);
        double _t91 = Math.fma(_t19, _t8, -(_t16 * _t9));
        double _t92 = Math.fma(_t16, _t10, -_t17 * _t8);
        double _t93 = Math.fma(_t19, _t10, -_t17 * _t9);
        double _t94 = Math.fma(_t17, _t11, -_t18 * _t10);
        double _t95 = Math.fma(_t16, _t11, -_t18 * _t8);
        double _t99 = Math.fma(_t6, _t11, -_t4 * _t9);
        double _t102 = Math.fma(_t19, _t11, -_t18 * _t9);
        double _t189 = Math.fma(_t5, _t71, Math.fma(_t7, _t68, -(_t3 * _t72)));
        double _t191 = Math.fma(_t14, _t71, Math.fma(_t15, _t68, -(_t13 * _t72)));
        double _t193 = Math.fma(_t14, _t76, Math.fma(_t15, _t73, -(_t13 * _t77)));
        double _t195 = Math.fma(_t5, _t76, Math.fma(_t7, _t73, -_t3 * _t77));
        double _t204 = java.lang.Math.abs(_t189);
        double _t205 = java.lang.Math.abs(_t191);
        double _t206 = java.lang.Math.abs(_t193);
        double _t207 = java.lang.Math.abs(_t195);
        double _t216 = Math.fma(_t5, _t82, Math.fma(_t7, _t83, -(_t3 * _t84)));
        double _t218 = Math.fma(_t14, _t82, Math.fma(_t15, _t83, -(_t13 * _t84)));
        double _t220 = Math.fma(_t14, _t91, Math.fma(_t15, _t92, -(_t13 * _t93)));
        double _t222 = Math.fma(_t5, _t91, Math.fma(_t7, _t92, -_t3 * _t93));
        double _t232 = java.lang.Math.abs(_t216);
        double _t233 = java.lang.Math.abs(_t218);
        double _t234 = java.lang.Math.abs(_t220);
        double _t235 = java.lang.Math.abs(_t222);
        double _t244_inv = 1.0 / (_t204 > epsilon * _t232 ? _t189 : Math.copySign(0.0, _t216));
        double _t245_inv = 1.0 / (_t232 > epsilon * _t204 ? _t216 : Math.copySign(0.0, _t189));
        double _t246_inv = 1.0 / (_t205 > epsilon * _t233 ? _t191 : Math.copySign(0.0, _t218));
        double _t247_inv = 1.0 / (_t233 > epsilon * _t205 ? _t218 : Math.copySign(0.0, _t191));
        double _t248_inv = 1.0 / (_t206 > epsilon * _t234 ? _t193 : Math.copySign(0.0, _t220));
        double _t249_inv = 1.0 / (_t234 > epsilon * _t206 ? _t220 : Math.copySign(0.0, _t193));
        double _t250_inv = 1.0 / (_t207 > epsilon * _t235 ? _t195 : Math.copySign(0.0, _t222));
        double _t251_inv = 1.0 / (_t235 > epsilon * _t207 ? _t222 : Math.copySign(0.0, _t195));
        double _t256 = Math.fma(_t0, _t72, Math.fma(_t7, _t69, -(_t5 * _t78))) * _t244_inv;
        double _t257 = Math.fma(_t12, _t72, Math.fma(_t15, _t69, -(_t14 * _t78))) * _t246_inv;
        double _t258 = Math.fma(_t12, _t77, Math.fma(_t15, _t74, -_t14 * _t79)) * _t248_inv;
        double _t259 = Math.fma(_t0, _t77, Math.fma(_t7, _t74, -_t5 * _t79)) * _t250_inv;
        double _t264 = -Math.fma(_t0, _t68, Math.fma(_t3, _t69, -(_t5 * _t70))) * _t244_inv;
        double _t265 = -Math.fma(_t12, _t68, Math.fma(_t13, _t69, -(_t14 * _t70))) * _t246_inv;
        double _t266 = -Math.fma(_t12, _t73, Math.fma(_t13, _t74, -_t14 * _t75)) * _t248_inv;
        double _t267 = -Math.fma(_t0, _t73, Math.fma(_t3, _t74, -_t5 * _t75)) * _t250_inv;
        double _t268 = -Math.fma(_t0, _t71, Math.fma(_t7, _t70, -_t3 * _t78)) * _t244_inv;
        double _t269 = -Math.fma(_t12, _t71, Math.fma(_t15, _t70, -(_t13 * _t78))) * _t246_inv;
        double _t270 = -Math.fma(_t12, _t76, Math.fma(_t15, _t75, -_t13 * _t79)) * _t248_inv;
        double _t271 = -Math.fma(_t0, _t76, Math.fma(_t7, _t75, -(_t3 * _t79))) * _t250_inv;
        double _t276 = Math.fma(_t0, _t84, Math.fma(_t7, _t85, -(_t5 * _t99))) * _t245_inv;
        double _t277 = Math.fma(_t12, _t84, Math.fma(_t15, _t85, -(_t14 * _t99))) * _t247_inv;
        double _t278 = Math.fma(_t12, _t93, Math.fma(_t15, _t94, -_t14 * _t102)) * _t249_inv;
        double _t279 = Math.fma(_t0, _t93, Math.fma(_t7, _t94, -_t5 * _t102)) * _t251_inv;
        double _t284 = -Math.fma(_t0, _t83, Math.fma(_t3, _t85, -(_t5 * _t86))) * _t245_inv;
        double _t285 = -Math.fma(_t12, _t83, Math.fma(_t13, _t85, -(_t14 * _t86))) * _t247_inv;
        double _t286 = -Math.fma(_t12, _t92, Math.fma(_t13, _t94, -_t14 * _t95)) * _t249_inv;
        double _t287 = -Math.fma(_t0, _t92, Math.fma(_t3, _t94, -_t5 * _t95)) * _t251_inv;
        double _t288 = -Math.fma(_t0, _t82, Math.fma(_t7, _t86, -_t3 * _t99)) * _t245_inv;
        double _t289 = -Math.fma(_t12, _t82, Math.fma(_t15, _t86, -(_t13 * _t99))) * _t247_inv;
        double _t290 = -Math.fma(_t12, _t91, Math.fma(_t15, _t95, -_t13 * _t102)) * _t249_inv;
        double _t291 = -Math.fma(_t0, _t91, Math.fma(_t7, _t95, -(_t3 * _t102))) * _t251_inv;
        double _t292 = _t256 != _t256 ? _t276 : _t256;
        double _t293 = _t257 != _t257 ? _t277 : _t257;
        double _t294 = _t258 != _t258 ? _t278 : _t258;
        double _t295 = _t259 != _t259 ? _t279 : _t259;
        double _t296 = _t264 != _t264 ? _t284 : _t264;
        double _t297 = _t265 != _t265 ? _t285 : _t265;
        double _t298 = _t266 != _t266 ? _t286 : _t266;
        double _t299 = _t267 != _t267 ? _t287 : _t267;
        double _t300 = _t268 != _t268 ? _t288 : _t268;
        double _t301 = _t269 != _t269 ? _t289 : _t269;
        double _t302 = _t270 != _t270 ? _t290 : _t270;
        double _t303 = _t271 != _t271 ? _t291 : _t271;
        double _t304 = _t276 != _t276 ? _t256 : _t276;
        double _t305 = _t277 != _t277 ? _t257 : _t277;
        double _t306 = _t278 != _t278 ? _t258 : _t278;
        double _t307 = _t279 != _t279 ? _t259 : _t279;
        double _t308 = _t284 != _t284 ? _t264 : _t284;
        double _t309 = _t285 != _t285 ? _t265 : _t285;
        double _t310 = _t286 != _t286 ? _t266 : _t286;
        double _t311 = _t287 != _t287 ? _t267 : _t287;
        double _t312 = _t288 != _t288 ? _t268 : _t288;
        double _t313 = _t289 != _t289 ? _t269 : _t289;
        double _t314 = _t290 != _t290 ? _t270 : _t290;
        double _t315 = _t291 != _t291 ? _t271 : _t291;
        dest[destOffset] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t296, _t297), _t298), _t299), _t308), _t309), _t310), _t311);
        dest[destOffset + 1] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t292, _t293), _t294), _t295), _t304), _t305), _t306), _t307);
        dest[destOffset + 2] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t300, _t301), _t302), _t303), _t312), _t313), _t314), _t315);
        dest[destOffset + 3] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t296, _t297), _t298), _t299), _t308), _t309), _t310), _t311);
        dest[destOffset + 4] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t292, _t293), _t294), _t295), _t304), _t305), _t306), _t307);
        dest[destOffset + 5] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t300, _t301), _t302), _t303), _t312), _t313), _t314), _t315);
        return dest;
    }

    public static double[] frustumCorner_no(double[] dest, int destOffset, double[] src, int srcOffset, FrustumCorner corner) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = _self03 + _self33;
        double _t1 = _self11 + _self31;
        double _t2 = _self22 + _self32;
        double _t3 = _self12 + _self32;
        double _t4 = _self21 + _self31;
        double _t5 = _self01 + _self31;
        double _t6 = _self23 + _self33;
        double _t7 = _self13 + _self33;
        double _t8 = _self02 + _self32;
        double _t9 = _self10 + _self30;
        double _t10 = _self20 + _self30;
        double _t11 = _self00 + _self30;
        double _t12 = _self21 - _self31;
        double _t13 = _self20 - _self30;
        double _t14 = _self22 - _self32;
        double _t15 = _self23 - _self33;
        double _t16 = _self03 - _self33;
        double _t17 = _self01 - _self31;
        double _t18 = _self02 - _self32;
        double _t19 = _self00 - _self30;
        double _t20 = _self11 - _self31;
        double _t21 = _self12 - _self32;
        double _t22 = _self13 - _self33;
        double _t23 = _self10 - _self30;
        double _t72 = Math.fma(_t1, _t2, -_t3 * _t4);
        double _t73 = Math.fma(_t3, _t6, -_t7 * _t2);
        double _t74 = Math.fma(_t1, _t6, -_t7 * _t4);
        double _t75 = Math.fma(_t9, _t4, -_t1 * _t10);
        double _t76 = Math.fma(_t9, _t2, -_t3 * _t10);
        double _t77 = Math.fma(_t9, _t12, -_t1 * _t13);
        double _t78 = Math.fma(_t1, _t14, -_t3 * _t12);
        double _t79 = Math.fma(_t9, _t14, -_t3 * _t13);
        double _t80 = Math.fma(_t3, _t15, -_t7 * _t14);
        double _t81 = Math.fma(_t1, _t15, -_t7 * _t12);
        double _t82 = Math.fma(_t2, _t20, _t4 * -_t21);
        double _t83 = Math.fma(_t6, _t21, _t2 * -_t22);
        double _t84 = Math.fma(_t6, _t20, _t4 * -_t22);
        double _t85 = Math.fma(_t4, _t23, _t10 * -_t20);
        double _t86 = Math.fma(_t2, _t23, _t10 * -_t21);
        double _t87 = Math.fma(_t23, _t12, -_t20 * _t13);
        double _t88 = Math.fma(_t20, _t14, -_t21 * _t12);
        double _t89 = Math.fma(_t23, _t14, -_t21 * _t13);
        double _t90 = Math.fma(_t21, _t15, -_t22 * _t14);
        double _t91 = Math.fma(_t20, _t15, -_t22 * _t12);
        double _t92 = Math.fma(_t9, _t6, -_t7 * _t10);
        double _t93 = Math.fma(_t9, _t15, -_t7 * _t13);
        double _t94 = Math.fma(_t6, _t23, _t10 * -_t22);
        double _t95 = Math.fma(_t23, _t15, -_t22 * _t13);
        double _t193 = Math.fma(_t8, _t75, Math.fma(_t11, _t72, -(_t5 * _t76)));
        double _t194 = Math.fma(_t8, _t77, Math.fma(_t11, _t78, -(_t5 * _t79)));
        double _t197 = Math.fma(_t18, _t75, Math.fma(_t19, _t72, -(_t17 * _t76)));
        double _t198 = Math.fma(_t18, _t77, Math.fma(_t19, _t78, -(_t17 * _t79)));
        double _t201 = Math.fma(_t18, _t85, Math.fma(_t19, _t82, -(_t17 * _t86)));
        double _t202 = Math.fma(_t18, _t87, Math.fma(_t19, _t88, -(_t17 * _t89)));
        double _t205 = Math.fma(_t8, _t85, Math.fma(_t11, _t82, -(_t5 * _t86)));
        double _t206 = Math.fma(_t8, _t87, Math.fma(_t11, _t88, -(_t5 * _t89)));
        double _t224 = java.lang.Math.abs(_t193);
        double _t225 = java.lang.Math.abs(_t194);
        double _t226 = java.lang.Math.abs(_t197);
        double _t227 = java.lang.Math.abs(_t198);
        double _t228 = java.lang.Math.abs(_t201);
        double _t229 = java.lang.Math.abs(_t202);
        double _t230 = java.lang.Math.abs(_t205);
        double _t231 = java.lang.Math.abs(_t206);
        double _t240 = _t225 * 9.094947017729282E-13;
        double _t241 = _t224 * 9.094947017729282E-13;
        double _t242 = _t227 * 9.094947017729282E-13;
        double _t243 = _t226 * 9.094947017729282E-13;
        double _t244 = _t229 * 9.094947017729282E-13;
        double _t245 = _t228 * 9.094947017729282E-13;
        double _t246 = _t231 * 9.094947017729282E-13;
        double _t247 = _t230 * 9.094947017729282E-13;
        double _t248_inv = 1.0 / (_t224 > _t240 ? _t193 : Math.copySign(0.0, _t194));
        double _t249_inv = 1.0 / (_t225 > _t241 ? _t194 : Math.copySign(0.0, _t193));
        double _t250_inv = 1.0 / (_t226 > _t242 ? _t197 : Math.copySign(0.0, _t198));
        double _t251_inv = 1.0 / (_t227 > _t243 ? _t198 : Math.copySign(0.0, _t197));
        double _t252_inv = 1.0 / (_t228 > _t244 ? _t201 : Math.copySign(0.0, _t202));
        double _t253_inv = 1.0 / (_t229 > _t245 ? _t202 : Math.copySign(0.0, _t201));
        double _t254_inv = 1.0 / (_t230 > _t246 ? _t205 : Math.copySign(0.0, _t206));
        double _t255_inv = 1.0 / (_t231 > _t247 ? _t206 : Math.copySign(0.0, _t205));
        double _t264 = Math.fma(_t0, _t76, Math.fma(_t11, _t73, -_t8 * _t92)) * _t248_inv;
        double _t265 = Math.fma(_t0, _t79, Math.fma(_t11, _t80, -_t8 * _t93)) * _t249_inv;
        double _t266 = Math.fma(_t16, _t76, Math.fma(_t19, _t73, -_t18 * _t92)) * _t250_inv;
        double _t267 = Math.fma(_t16, _t79, Math.fma(_t19, _t80, -_t18 * _t93)) * _t251_inv;
        double _t268 = Math.fma(_t16, _t86, Math.fma(_t19, _t83, -_t18 * _t94)) * _t252_inv;
        double _t269 = Math.fma(_t16, _t89, Math.fma(_t19, _t90, -_t18 * _t95)) * _t253_inv;
        double _t270 = Math.fma(_t0, _t86, Math.fma(_t11, _t83, -_t8 * _t94)) * _t254_inv;
        double _t271 = Math.fma(_t0, _t89, Math.fma(_t11, _t90, -_t8 * _t95)) * _t255_inv;
        double _t280 = -Math.fma(_t0, _t72, Math.fma(_t5, _t73, -_t8 * _t74)) * _t248_inv;
        double _t281 = -Math.fma(_t0, _t78, Math.fma(_t5, _t80, -_t8 * _t81)) * _t249_inv;
        double _t282 = -Math.fma(_t16, _t72, Math.fma(_t17, _t73, -_t18 * _t74)) * _t250_inv;
        double _t283 = -Math.fma(_t16, _t78, Math.fma(_t17, _t80, -_t18 * _t81)) * _t251_inv;
        double _t284 = -Math.fma(_t16, _t82, Math.fma(_t17, _t83, -_t18 * _t84)) * _t252_inv;
        double _t285 = -Math.fma(_t16, _t88, Math.fma(_t17, _t90, -_t18 * _t91)) * _t253_inv;
        double _t286 = -Math.fma(_t0, _t82, Math.fma(_t5, _t83, -_t8 * _t84)) * _t254_inv;
        double _t287 = -Math.fma(_t0, _t88, Math.fma(_t5, _t90, -_t8 * _t91)) * _t255_inv;
        double _t288 = -Math.fma(_t0, _t75, Math.fma(_t11, _t74, -_t5 * _t92)) * _t248_inv;
        double _t289 = -Math.fma(_t0, _t77, Math.fma(_t11, _t81, -_t5 * _t93)) * _t249_inv;
        double _t290 = -Math.fma(_t16, _t75, Math.fma(_t19, _t74, -_t17 * _t92)) * _t250_inv;
        double _t291 = -Math.fma(_t16, _t77, Math.fma(_t19, _t81, -_t17 * _t93)) * _t251_inv;
        double _t292 = -Math.fma(_t16, _t85, Math.fma(_t19, _t84, -_t17 * _t94)) * _t252_inv;
        double _t293 = -Math.fma(_t16, _t87, Math.fma(_t19, _t91, -_t17 * _t95)) * _t253_inv;
        double _t294 = -Math.fma(_t0, _t85, Math.fma(_t11, _t84, -_t5 * _t94)) * _t254_inv;
        double _t295 = -Math.fma(_t0, _t87, Math.fma(_t11, _t91, -_t5 * _t95)) * _t255_inv;
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (corner) {
            case NXNYNZ: _idxSw0 = _t280 != _t280 ? _t224 > _t240 ? _t280 : _t281 : _t280; _idxSw1 = _t264 != _t264 ? _t224 > _t240 ? _t264 : _t265 : _t264; _idxSw2 = _t288 != _t288 ? _t224 > _t240 ? _t288 : _t289 : _t288; break;
            case PXNYNZ: _idxSw0 = _t282 != _t282 ? _t226 > _t242 ? _t282 : _t283 : _t282; _idxSw1 = _t266 != _t266 ? _t226 > _t242 ? _t266 : _t267 : _t266; _idxSw2 = _t290 != _t290 ? _t226 > _t242 ? _t290 : _t291 : _t290; break;
            case PXPYNZ: _idxSw0 = _t284 != _t284 ? _t228 > _t244 ? _t284 : _t285 : _t284; _idxSw1 = _t268 != _t268 ? _t228 > _t244 ? _t268 : _t269 : _t268; _idxSw2 = _t292 != _t292 ? _t228 > _t244 ? _t292 : _t293 : _t292; break;
            case NXPYNZ: _idxSw0 = _t286 != _t286 ? _t230 > _t246 ? _t286 : _t287 : _t286; _idxSw1 = _t270 != _t270 ? _t230 > _t246 ? _t270 : _t271 : _t270; _idxSw2 = _t294 != _t294 ? _t230 > _t246 ? _t294 : _t295 : _t294; break;
            case NXNYPZ: _idxSw0 = _t281 != _t281 ? _t225 > _t241 ? _t281 : _t280 : _t281; _idxSw1 = _t265 != _t265 ? _t225 > _t241 ? _t265 : _t264 : _t265; _idxSw2 = _t289 != _t289 ? _t225 > _t241 ? _t289 : _t288 : _t289; break;
            case PXNYPZ: _idxSw0 = _t283 != _t283 ? _t227 > _t243 ? _t283 : _t282 : _t283; _idxSw1 = _t267 != _t267 ? _t227 > _t243 ? _t267 : _t266 : _t267; _idxSw2 = _t291 != _t291 ? _t227 > _t243 ? _t291 : _t290 : _t291; break;
            case PXPYPZ: _idxSw0 = _t285 != _t285 ? _t229 > _t245 ? _t285 : _t284 : _t285; _idxSw1 = _t269 != _t269 ? _t229 > _t245 ? _t269 : _t268 : _t269; _idxSw2 = _t293 != _t293 ? _t229 > _t245 ? _t293 : _t292 : _t293; break;
            case NXPYPZ: _idxSw0 = _t287 != _t287 ? _t231 > _t247 ? _t287 : _t286 : _t287; _idxSw1 = _t271 != _t271 ? _t231 > _t247 ? _t271 : _t270 : _t271; _idxSw2 = _t295 != _t295 ? _t231 > _t247 ? _t295 : _t294 : _t295; break;
            default: throw new IllegalArgumentException("Unknown FrustumCorner: " + corner);
        }
        dest[destOffset] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        return dest;
    }

    public static double[] frustumCorner_zo(double[] dest, int destOffset, double[] src, int srcOffset, FrustumCorner corner) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = _self03 + _self33;
        double _t1 = _self11 + _self31;
        double _t2 = _self12 + _self32;
        double _t3 = _self01 + _self31;
        double _t4 = _self13 + _self33;
        double _t5 = _self02 + _self32;
        double _t6 = _self10 + _self30;
        double _t7 = _self00 + _self30;
        double _t8 = _self21 - _self31;
        double _t9 = _self20 - _self30;
        double _t10 = _self22 - _self32;
        double _t11 = _self23 - _self33;
        double _t12 = _self03 - _self33;
        double _t13 = _self01 - _self31;
        double _t14 = _self02 - _self32;
        double _t15 = _self00 - _self30;
        double _t16 = _self11 - _self31;
        double _t17 = _self12 - _self32;
        double _t18 = _self13 - _self33;
        double _t19 = _self10 - _self30;
        double _t68 = Math.fma(_self22, _t1, -(_self21 * _t2));
        double _t69 = Math.fma(_self23, _t2, -_self22 * _t4);
        double _t70 = Math.fma(_self23, _t1, -_self21 * _t4);
        double _t71 = Math.fma(_self21, _t6, -(_self20 * _t1));
        double _t72 = Math.fma(_self22, _t6, -(_self20 * _t2));
        double _t73 = Math.fma(_self22, _t16, -(_self21 * _t17));
        double _t74 = Math.fma(_self23, _t17, -_self22 * _t18);
        double _t75 = Math.fma(_self23, _t16, -_self21 * _t18);
        double _t76 = Math.fma(_self21, _t19, -(_self20 * _t16));
        double _t77 = Math.fma(_self22, _t19, -(_self20 * _t17));
        double _t78 = Math.fma(_self23, _t6, -_self20 * _t4);
        double _t79 = Math.fma(_self23, _t19, -_self20 * _t18);
        double _t82 = Math.fma(_t6, _t8, _t1 * -_t9);
        double _t83 = Math.fma(_t1, _t10, -_t2 * _t8);
        double _t84 = Math.fma(_t6, _t10, -_t2 * _t9);
        double _t85 = Math.fma(_t2, _t11, -_t4 * _t10);
        double _t86 = Math.fma(_t1, _t11, -_t4 * _t8);
        double _t91 = Math.fma(_t19, _t8, _t16 * -_t9);
        double _t92 = Math.fma(_t16, _t10, -_t17 * _t8);
        double _t93 = Math.fma(_t19, _t10, -_t17 * _t9);
        double _t94 = Math.fma(_t17, _t11, -_t18 * _t10);
        double _t95 = Math.fma(_t16, _t11, -_t18 * _t8);
        double _t99 = Math.fma(_t6, _t11, -_t4 * _t9);
        double _t102 = Math.fma(_t19, _t11, -_t18 * _t9);
        double _t189 = Math.fma(_t5, _t71, Math.fma(_t7, _t68, -(_t3 * _t72)));
        double _t191 = Math.fma(_t14, _t71, Math.fma(_t15, _t68, -(_t13 * _t72)));
        double _t193 = Math.fma(_t14, _t76, Math.fma(_t15, _t73, -(_t13 * _t77)));
        double _t195 = Math.fma(_t5, _t76, Math.fma(_t7, _t73, -(_t3 * _t77)));
        double _t204 = java.lang.Math.abs(_t189);
        double _t205 = java.lang.Math.abs(_t191);
        double _t206 = java.lang.Math.abs(_t193);
        double _t207 = java.lang.Math.abs(_t195);
        double _t212 = _t204 * 9.094947017729282E-13;
        double _t213 = _t205 * 9.094947017729282E-13;
        double _t214 = _t206 * 9.094947017729282E-13;
        double _t215 = _t207 * 9.094947017729282E-13;
        double _t216 = Math.fma(_t5, _t82, Math.fma(_t7, _t83, -(_t3 * _t84)));
        double _t218 = Math.fma(_t14, _t82, Math.fma(_t15, _t83, -(_t13 * _t84)));
        double _t220 = Math.fma(_t14, _t91, Math.fma(_t15, _t92, -(_t13 * _t93)));
        double _t222 = Math.fma(_t5, _t91, Math.fma(_t7, _t92, -(_t3 * _t93)));
        double _t232 = java.lang.Math.abs(_t216);
        double _t233 = java.lang.Math.abs(_t218);
        double _t234 = java.lang.Math.abs(_t220);
        double _t235 = java.lang.Math.abs(_t222);
        double _t240 = _t232 * 9.094947017729282E-13;
        double _t241 = _t233 * 9.094947017729282E-13;
        double _t242 = _t234 * 9.094947017729282E-13;
        double _t243 = _t235 * 9.094947017729282E-13;
        double _t244_inv = 1.0 / (_t204 > _t240 ? _t189 : Math.copySign(0.0, _t216));
        double _t245_inv = 1.0 / (_t232 > _t212 ? _t216 : Math.copySign(0.0, _t189));
        double _t246_inv = 1.0 / (_t205 > _t241 ? _t191 : Math.copySign(0.0, _t218));
        double _t247_inv = 1.0 / (_t233 > _t213 ? _t218 : Math.copySign(0.0, _t191));
        double _t248_inv = 1.0 / (_t206 > _t242 ? _t193 : Math.copySign(0.0, _t220));
        double _t249_inv = 1.0 / (_t234 > _t214 ? _t220 : Math.copySign(0.0, _t193));
        double _t250_inv = 1.0 / (_t207 > _t243 ? _t195 : Math.copySign(0.0, _t222));
        double _t251_inv = 1.0 / (_t235 > _t215 ? _t222 : Math.copySign(0.0, _t195));
        double _t256 = Math.fma(_t0, _t72, Math.fma(_t7, _t69, -_t5 * _t78)) * _t244_inv;
        double _t257 = Math.fma(_t12, _t72, Math.fma(_t15, _t69, -_t14 * _t78)) * _t246_inv;
        double _t258 = Math.fma(_t12, _t77, Math.fma(_t15, _t74, -_t14 * _t79)) * _t248_inv;
        double _t259 = Math.fma(_t0, _t77, Math.fma(_t7, _t74, -_t5 * _t79)) * _t250_inv;
        double _t264 = -Math.fma(_t0, _t68, Math.fma(_t3, _t69, -_t5 * _t70)) * _t244_inv;
        double _t265 = -Math.fma(_t12, _t68, Math.fma(_t13, _t69, -_t14 * _t70)) * _t246_inv;
        double _t266 = -Math.fma(_t12, _t73, Math.fma(_t13, _t74, -_t14 * _t75)) * _t248_inv;
        double _t267 = -Math.fma(_t0, _t73, Math.fma(_t3, _t74, -_t5 * _t75)) * _t250_inv;
        double _t268 = -Math.fma(_t0, _t71, Math.fma(_t7, _t70, -_t3 * _t78)) * _t244_inv;
        double _t269 = -Math.fma(_t12, _t71, Math.fma(_t15, _t70, -_t13 * _t78)) * _t246_inv;
        double _t270 = -Math.fma(_t12, _t76, Math.fma(_t15, _t75, -_t13 * _t79)) * _t248_inv;
        double _t271 = -Math.fma(_t0, _t76, Math.fma(_t7, _t75, -_t3 * _t79)) * _t250_inv;
        double _t276 = Math.fma(_t0, _t84, Math.fma(_t7, _t85, -_t5 * _t99)) * _t245_inv;
        double _t277 = Math.fma(_t12, _t84, Math.fma(_t15, _t85, -_t14 * _t99)) * _t247_inv;
        double _t278 = Math.fma(_t12, _t93, Math.fma(_t15, _t94, -_t14 * _t102)) * _t249_inv;
        double _t279 = Math.fma(_t0, _t93, Math.fma(_t7, _t94, -_t5 * _t102)) * _t251_inv;
        double _t284 = -Math.fma(_t0, _t83, Math.fma(_t3, _t85, -_t5 * _t86)) * _t245_inv;
        double _t285 = -Math.fma(_t12, _t83, Math.fma(_t13, _t85, -_t14 * _t86)) * _t247_inv;
        double _t286 = -Math.fma(_t12, _t92, Math.fma(_t13, _t94, -_t14 * _t95)) * _t249_inv;
        double _t287 = -Math.fma(_t0, _t92, Math.fma(_t3, _t94, -_t5 * _t95)) * _t251_inv;
        double _t288 = -Math.fma(_t0, _t82, Math.fma(_t7, _t86, -_t3 * _t99)) * _t245_inv;
        double _t289 = -Math.fma(_t12, _t82, Math.fma(_t15, _t86, -_t13 * _t99)) * _t247_inv;
        double _t290 = -Math.fma(_t12, _t91, Math.fma(_t15, _t95, -_t13 * _t102)) * _t249_inv;
        double _t291 = -Math.fma(_t0, _t91, Math.fma(_t7, _t95, -_t3 * _t102)) * _t251_inv;
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (corner) {
            case NXNYNZ: _idxSw0 = _t264 != _t264 ? _t204 > _t240 ? _t264 : _t284 : _t264; _idxSw1 = _t256 != _t256 ? _t204 > _t240 ? _t256 : _t276 : _t256; _idxSw2 = _t268 != _t268 ? _t204 > _t240 ? _t268 : _t288 : _t268; break;
            case PXNYNZ: _idxSw0 = _t265 != _t265 ? _t205 > _t241 ? _t265 : _t285 : _t265; _idxSw1 = _t257 != _t257 ? _t205 > _t241 ? _t257 : _t277 : _t257; _idxSw2 = _t269 != _t269 ? _t205 > _t241 ? _t269 : _t289 : _t269; break;
            case PXPYNZ: _idxSw0 = _t266 != _t266 ? _t206 > _t242 ? _t266 : _t286 : _t266; _idxSw1 = _t258 != _t258 ? _t206 > _t242 ? _t258 : _t278 : _t258; _idxSw2 = _t270 != _t270 ? _t206 > _t242 ? _t270 : _t290 : _t270; break;
            case NXPYNZ: _idxSw0 = _t267 != _t267 ? _t207 > _t243 ? _t267 : _t287 : _t267; _idxSw1 = _t259 != _t259 ? _t207 > _t243 ? _t259 : _t279 : _t259; _idxSw2 = _t271 != _t271 ? _t207 > _t243 ? _t271 : _t291 : _t271; break;
            case NXNYPZ: _idxSw0 = _t284 != _t284 ? _t232 > _t212 ? _t284 : _t264 : _t284; _idxSw1 = _t276 != _t276 ? _t232 > _t212 ? _t276 : _t256 : _t276; _idxSw2 = _t288 != _t288 ? _t232 > _t212 ? _t288 : _t268 : _t288; break;
            case PXNYPZ: _idxSw0 = _t285 != _t285 ? _t233 > _t213 ? _t285 : _t265 : _t285; _idxSw1 = _t277 != _t277 ? _t233 > _t213 ? _t277 : _t257 : _t277; _idxSw2 = _t289 != _t289 ? _t233 > _t213 ? _t289 : _t269 : _t289; break;
            case PXPYPZ: _idxSw0 = _t286 != _t286 ? _t234 > _t214 ? _t286 : _t266 : _t286; _idxSw1 = _t278 != _t278 ? _t234 > _t214 ? _t278 : _t258 : _t278; _idxSw2 = _t290 != _t290 ? _t234 > _t214 ? _t290 : _t270 : _t290; break;
            case NXPYPZ: _idxSw0 = _t287 != _t287 ? _t235 > _t215 ? _t287 : _t267 : _t287; _idxSw1 = _t279 != _t279 ? _t235 > _t215 ? _t279 : _t259 : _t279; _idxSw2 = _t291 != _t291 ? _t235 > _t215 ? _t291 : _t271 : _t291; break;
            default: throw new IllegalArgumentException("Unknown FrustumCorner: " + corner);
        }
        dest[destOffset] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        return dest;
    }

    public static double[] frustumCorner_no(double[] dest, int destOffset, double[] src, int srcOffset, FrustumCorner corner, double epsilon) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = _self03 + _self33;
        double _t1 = _self11 + _self31;
        double _t2 = _self22 + _self32;
        double _t3 = _self12 + _self32;
        double _t4 = _self21 + _self31;
        double _t5 = _self01 + _self31;
        double _t6 = _self23 + _self33;
        double _t7 = _self13 + _self33;
        double _t8 = _self02 + _self32;
        double _t9 = _self10 + _self30;
        double _t10 = _self20 + _self30;
        double _t11 = _self00 + _self30;
        double _t12 = _self21 - _self31;
        double _t13 = _self20 - _self30;
        double _t14 = _self22 - _self32;
        double _t15 = _self23 - _self33;
        double _t16 = _self03 - _self33;
        double _t17 = _self01 - _self31;
        double _t18 = _self02 - _self32;
        double _t19 = _self00 - _self30;
        double _t20 = _self11 - _self31;
        double _t21 = _self12 - _self32;
        double _t22 = _self13 - _self33;
        double _t23 = _self10 - _self30;
        double _t72 = Math.fma(_t1, _t2, -_t3 * _t4);
        double _t73 = Math.fma(_t3, _t6, -_t7 * _t2);
        double _t74 = Math.fma(_t1, _t6, -_t7 * _t4);
        double _t75 = Math.fma(_t9, _t4, -_t1 * _t10);
        double _t76 = Math.fma(_t9, _t2, -_t3 * _t10);
        double _t77 = Math.fma(_t9, _t12, -_t1 * _t13);
        double _t78 = Math.fma(_t1, _t14, -_t3 * _t12);
        double _t79 = Math.fma(_t9, _t14, -_t3 * _t13);
        double _t80 = Math.fma(_t3, _t15, -_t7 * _t14);
        double _t81 = Math.fma(_t1, _t15, -_t7 * _t12);
        double _t82 = Math.fma(_t2, _t20, _t4 * -_t21);
        double _t83 = Math.fma(_t6, _t21, _t2 * -_t22);
        double _t84 = Math.fma(_t6, _t20, _t4 * -_t22);
        double _t85 = Math.fma(_t4, _t23, _t10 * -_t20);
        double _t86 = Math.fma(_t2, _t23, _t10 * -_t21);
        double _t87 = Math.fma(_t23, _t12, -_t20 * _t13);
        double _t88 = Math.fma(_t20, _t14, -_t21 * _t12);
        double _t89 = Math.fma(_t23, _t14, -_t21 * _t13);
        double _t90 = Math.fma(_t21, _t15, -_t22 * _t14);
        double _t91 = Math.fma(_t20, _t15, -_t22 * _t12);
        double _t92 = Math.fma(_t9, _t6, -_t7 * _t10);
        double _t93 = Math.fma(_t9, _t15, -_t7 * _t13);
        double _t94 = Math.fma(_t6, _t23, _t10 * -_t22);
        double _t95 = Math.fma(_t23, _t15, -_t22 * _t13);
        double _t193 = Math.fma(_t8, _t75, Math.fma(_t11, _t72, -(_t5 * _t76)));
        double _t194 = Math.fma(_t8, _t77, Math.fma(_t11, _t78, -(_t5 * _t79)));
        double _t197 = Math.fma(_t18, _t75, Math.fma(_t19, _t72, -(_t17 * _t76)));
        double _t198 = Math.fma(_t18, _t77, Math.fma(_t19, _t78, -(_t17 * _t79)));
        double _t201 = Math.fma(_t18, _t85, Math.fma(_t19, _t82, -(_t17 * _t86)));
        double _t202 = Math.fma(_t18, _t87, Math.fma(_t19, _t88, -(_t17 * _t89)));
        double _t205 = Math.fma(_t8, _t85, Math.fma(_t11, _t82, -(_t5 * _t86)));
        double _t206 = Math.fma(_t8, _t87, Math.fma(_t11, _t88, -(_t5 * _t89)));
        double _t224 = java.lang.Math.abs(_t193);
        double _t225 = java.lang.Math.abs(_t194);
        double _t226 = java.lang.Math.abs(_t197);
        double _t227 = java.lang.Math.abs(_t198);
        double _t228 = java.lang.Math.abs(_t201);
        double _t229 = java.lang.Math.abs(_t202);
        double _t230 = java.lang.Math.abs(_t205);
        double _t231 = java.lang.Math.abs(_t206);
        double _t240 = epsilon * _t225;
        double _t241 = epsilon * _t224;
        double _t242 = epsilon * _t227;
        double _t243 = epsilon * _t226;
        double _t244 = epsilon * _t229;
        double _t245 = epsilon * _t228;
        double _t246 = epsilon * _t231;
        double _t247 = epsilon * _t230;
        double _t248_inv = 1.0 / (_t224 > _t240 ? _t193 : Math.copySign(0.0, _t194));
        double _t249_inv = 1.0 / (_t225 > _t241 ? _t194 : Math.copySign(0.0, _t193));
        double _t250_inv = 1.0 / (_t226 > _t242 ? _t197 : Math.copySign(0.0, _t198));
        double _t251_inv = 1.0 / (_t227 > _t243 ? _t198 : Math.copySign(0.0, _t197));
        double _t252_inv = 1.0 / (_t228 > _t244 ? _t201 : Math.copySign(0.0, _t202));
        double _t253_inv = 1.0 / (_t229 > _t245 ? _t202 : Math.copySign(0.0, _t201));
        double _t254_inv = 1.0 / (_t230 > _t246 ? _t205 : Math.copySign(0.0, _t206));
        double _t255_inv = 1.0 / (_t231 > _t247 ? _t206 : Math.copySign(0.0, _t205));
        double _t264 = Math.fma(_t0, _t76, Math.fma(_t11, _t73, -_t8 * _t92)) * _t248_inv;
        double _t265 = Math.fma(_t0, _t79, Math.fma(_t11, _t80, -_t8 * _t93)) * _t249_inv;
        double _t266 = Math.fma(_t16, _t76, Math.fma(_t19, _t73, -_t18 * _t92)) * _t250_inv;
        double _t267 = Math.fma(_t16, _t79, Math.fma(_t19, _t80, -_t18 * _t93)) * _t251_inv;
        double _t268 = Math.fma(_t16, _t86, Math.fma(_t19, _t83, -_t18 * _t94)) * _t252_inv;
        double _t269 = Math.fma(_t16, _t89, Math.fma(_t19, _t90, -_t18 * _t95)) * _t253_inv;
        double _t270 = Math.fma(_t0, _t86, Math.fma(_t11, _t83, -_t8 * _t94)) * _t254_inv;
        double _t271 = Math.fma(_t0, _t89, Math.fma(_t11, _t90, -_t8 * _t95)) * _t255_inv;
        double _t280 = -Math.fma(_t0, _t72, Math.fma(_t5, _t73, -_t8 * _t74)) * _t248_inv;
        double _t281 = -Math.fma(_t0, _t78, Math.fma(_t5, _t80, -_t8 * _t81)) * _t249_inv;
        double _t282 = -Math.fma(_t16, _t72, Math.fma(_t17, _t73, -_t18 * _t74)) * _t250_inv;
        double _t283 = -Math.fma(_t16, _t78, Math.fma(_t17, _t80, -_t18 * _t81)) * _t251_inv;
        double _t284 = -Math.fma(_t16, _t82, Math.fma(_t17, _t83, -_t18 * _t84)) * _t252_inv;
        double _t285 = -Math.fma(_t16, _t88, Math.fma(_t17, _t90, -_t18 * _t91)) * _t253_inv;
        double _t286 = -Math.fma(_t0, _t82, Math.fma(_t5, _t83, -_t8 * _t84)) * _t254_inv;
        double _t287 = -Math.fma(_t0, _t88, Math.fma(_t5, _t90, -_t8 * _t91)) * _t255_inv;
        double _t288 = -Math.fma(_t0, _t75, Math.fma(_t11, _t74, -_t5 * _t92)) * _t248_inv;
        double _t289 = -Math.fma(_t0, _t77, Math.fma(_t11, _t81, -_t5 * _t93)) * _t249_inv;
        double _t290 = -Math.fma(_t16, _t75, Math.fma(_t19, _t74, -_t17 * _t92)) * _t250_inv;
        double _t291 = -Math.fma(_t16, _t77, Math.fma(_t19, _t81, -_t17 * _t93)) * _t251_inv;
        double _t292 = -Math.fma(_t16, _t85, Math.fma(_t19, _t84, -_t17 * _t94)) * _t252_inv;
        double _t293 = -Math.fma(_t16, _t87, Math.fma(_t19, _t91, -_t17 * _t95)) * _t253_inv;
        double _t294 = -Math.fma(_t0, _t85, Math.fma(_t11, _t84, -_t5 * _t94)) * _t254_inv;
        double _t295 = -Math.fma(_t0, _t87, Math.fma(_t11, _t91, -_t5 * _t95)) * _t255_inv;
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (corner) {
            case NXNYNZ: _idxSw0 = _t280 != _t280 ? _t224 > _t240 ? _t280 : _t281 : _t280; _idxSw1 = _t264 != _t264 ? _t224 > _t240 ? _t264 : _t265 : _t264; _idxSw2 = _t288 != _t288 ? _t224 > _t240 ? _t288 : _t289 : _t288; break;
            case PXNYNZ: _idxSw0 = _t282 != _t282 ? _t226 > _t242 ? _t282 : _t283 : _t282; _idxSw1 = _t266 != _t266 ? _t226 > _t242 ? _t266 : _t267 : _t266; _idxSw2 = _t290 != _t290 ? _t226 > _t242 ? _t290 : _t291 : _t290; break;
            case PXPYNZ: _idxSw0 = _t284 != _t284 ? _t228 > _t244 ? _t284 : _t285 : _t284; _idxSw1 = _t268 != _t268 ? _t228 > _t244 ? _t268 : _t269 : _t268; _idxSw2 = _t292 != _t292 ? _t228 > _t244 ? _t292 : _t293 : _t292; break;
            case NXPYNZ: _idxSw0 = _t286 != _t286 ? _t230 > _t246 ? _t286 : _t287 : _t286; _idxSw1 = _t270 != _t270 ? _t230 > _t246 ? _t270 : _t271 : _t270; _idxSw2 = _t294 != _t294 ? _t230 > _t246 ? _t294 : _t295 : _t294; break;
            case NXNYPZ: _idxSw0 = _t281 != _t281 ? _t225 > _t241 ? _t281 : _t280 : _t281; _idxSw1 = _t265 != _t265 ? _t225 > _t241 ? _t265 : _t264 : _t265; _idxSw2 = _t289 != _t289 ? _t225 > _t241 ? _t289 : _t288 : _t289; break;
            case PXNYPZ: _idxSw0 = _t283 != _t283 ? _t227 > _t243 ? _t283 : _t282 : _t283; _idxSw1 = _t267 != _t267 ? _t227 > _t243 ? _t267 : _t266 : _t267; _idxSw2 = _t291 != _t291 ? _t227 > _t243 ? _t291 : _t290 : _t291; break;
            case PXPYPZ: _idxSw0 = _t285 != _t285 ? _t229 > _t245 ? _t285 : _t284 : _t285; _idxSw1 = _t269 != _t269 ? _t229 > _t245 ? _t269 : _t268 : _t269; _idxSw2 = _t293 != _t293 ? _t229 > _t245 ? _t293 : _t292 : _t293; break;
            case NXPYPZ: _idxSw0 = _t287 != _t287 ? _t231 > _t247 ? _t287 : _t286 : _t287; _idxSw1 = _t271 != _t271 ? _t231 > _t247 ? _t271 : _t270 : _t271; _idxSw2 = _t295 != _t295 ? _t231 > _t247 ? _t295 : _t294 : _t295; break;
            default: throw new IllegalArgumentException("Unknown FrustumCorner: " + corner);
        }
        dest[destOffset] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        return dest;
    }

    public static double[] frustumCorner_zo(double[] dest, int destOffset, double[] src, int srcOffset, FrustumCorner corner, double epsilon) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = _self03 + _self33;
        double _t1 = _self11 + _self31;
        double _t2 = _self12 + _self32;
        double _t3 = _self01 + _self31;
        double _t4 = _self13 + _self33;
        double _t5 = _self02 + _self32;
        double _t6 = _self10 + _self30;
        double _t7 = _self00 + _self30;
        double _t8 = _self21 - _self31;
        double _t9 = _self20 - _self30;
        double _t10 = _self22 - _self32;
        double _t11 = _self23 - _self33;
        double _t12 = _self03 - _self33;
        double _t13 = _self01 - _self31;
        double _t14 = _self02 - _self32;
        double _t15 = _self00 - _self30;
        double _t16 = _self11 - _self31;
        double _t17 = _self12 - _self32;
        double _t18 = _self13 - _self33;
        double _t19 = _self10 - _self30;
        double _t68 = Math.fma(_self22, _t1, -(_self21 * _t2));
        double _t69 = Math.fma(_self23, _t2, -_self22 * _t4);
        double _t70 = Math.fma(_self23, _t1, -_self21 * _t4);
        double _t71 = Math.fma(_self21, _t6, -(_self20 * _t1));
        double _t72 = Math.fma(_self22, _t6, -(_self20 * _t2));
        double _t73 = Math.fma(_self22, _t16, -(_self21 * _t17));
        double _t74 = Math.fma(_self23, _t17, -_self22 * _t18);
        double _t75 = Math.fma(_self23, _t16, -_self21 * _t18);
        double _t76 = Math.fma(_self21, _t19, -(_self20 * _t16));
        double _t77 = Math.fma(_self22, _t19, -(_self20 * _t17));
        double _t78 = Math.fma(_self23, _t6, -_self20 * _t4);
        double _t79 = Math.fma(_self23, _t19, -_self20 * _t18);
        double _t82 = Math.fma(_t6, _t8, _t1 * -_t9);
        double _t83 = Math.fma(_t1, _t10, -_t2 * _t8);
        double _t84 = Math.fma(_t6, _t10, -_t2 * _t9);
        double _t85 = Math.fma(_t2, _t11, -_t4 * _t10);
        double _t86 = Math.fma(_t1, _t11, -_t4 * _t8);
        double _t91 = Math.fma(_t19, _t8, _t16 * -_t9);
        double _t92 = Math.fma(_t16, _t10, -_t17 * _t8);
        double _t93 = Math.fma(_t19, _t10, -_t17 * _t9);
        double _t94 = Math.fma(_t17, _t11, -_t18 * _t10);
        double _t95 = Math.fma(_t16, _t11, -_t18 * _t8);
        double _t99 = Math.fma(_t6, _t11, -_t4 * _t9);
        double _t102 = Math.fma(_t19, _t11, -_t18 * _t9);
        double _t189 = Math.fma(_t5, _t71, Math.fma(_t7, _t68, -(_t3 * _t72)));
        double _t191 = Math.fma(_t14, _t71, Math.fma(_t15, _t68, -(_t13 * _t72)));
        double _t193 = Math.fma(_t14, _t76, Math.fma(_t15, _t73, -(_t13 * _t77)));
        double _t195 = Math.fma(_t5, _t76, Math.fma(_t7, _t73, -(_t3 * _t77)));
        double _t204 = java.lang.Math.abs(_t189);
        double _t205 = java.lang.Math.abs(_t191);
        double _t206 = java.lang.Math.abs(_t193);
        double _t207 = java.lang.Math.abs(_t195);
        double _t212 = epsilon * _t204;
        double _t213 = epsilon * _t205;
        double _t214 = epsilon * _t206;
        double _t215 = epsilon * _t207;
        double _t216 = Math.fma(_t5, _t82, Math.fma(_t7, _t83, -(_t3 * _t84)));
        double _t218 = Math.fma(_t14, _t82, Math.fma(_t15, _t83, -(_t13 * _t84)));
        double _t220 = Math.fma(_t14, _t91, Math.fma(_t15, _t92, -(_t13 * _t93)));
        double _t222 = Math.fma(_t5, _t91, Math.fma(_t7, _t92, -(_t3 * _t93)));
        double _t232 = java.lang.Math.abs(_t216);
        double _t233 = java.lang.Math.abs(_t218);
        double _t234 = java.lang.Math.abs(_t220);
        double _t235 = java.lang.Math.abs(_t222);
        double _t240 = epsilon * _t232;
        double _t241 = epsilon * _t233;
        double _t242 = epsilon * _t234;
        double _t243 = epsilon * _t235;
        double _t244_inv = 1.0 / (_t204 > _t240 ? _t189 : Math.copySign(0.0, _t216));
        double _t245_inv = 1.0 / (_t232 > _t212 ? _t216 : Math.copySign(0.0, _t189));
        double _t246_inv = 1.0 / (_t205 > _t241 ? _t191 : Math.copySign(0.0, _t218));
        double _t247_inv = 1.0 / (_t233 > _t213 ? _t218 : Math.copySign(0.0, _t191));
        double _t248_inv = 1.0 / (_t206 > _t242 ? _t193 : Math.copySign(0.0, _t220));
        double _t249_inv = 1.0 / (_t234 > _t214 ? _t220 : Math.copySign(0.0, _t193));
        double _t250_inv = 1.0 / (_t207 > _t243 ? _t195 : Math.copySign(0.0, _t222));
        double _t251_inv = 1.0 / (_t235 > _t215 ? _t222 : Math.copySign(0.0, _t195));
        double _t256 = Math.fma(_t0, _t72, Math.fma(_t7, _t69, -_t5 * _t78)) * _t244_inv;
        double _t257 = Math.fma(_t12, _t72, Math.fma(_t15, _t69, -_t14 * _t78)) * _t246_inv;
        double _t258 = Math.fma(_t12, _t77, Math.fma(_t15, _t74, -_t14 * _t79)) * _t248_inv;
        double _t259 = Math.fma(_t0, _t77, Math.fma(_t7, _t74, -_t5 * _t79)) * _t250_inv;
        double _t264 = -Math.fma(_t0, _t68, Math.fma(_t3, _t69, -_t5 * _t70)) * _t244_inv;
        double _t265 = -Math.fma(_t12, _t68, Math.fma(_t13, _t69, -_t14 * _t70)) * _t246_inv;
        double _t266 = -Math.fma(_t12, _t73, Math.fma(_t13, _t74, -_t14 * _t75)) * _t248_inv;
        double _t267 = -Math.fma(_t0, _t73, Math.fma(_t3, _t74, -_t5 * _t75)) * _t250_inv;
        double _t268 = -Math.fma(_t0, _t71, Math.fma(_t7, _t70, -_t3 * _t78)) * _t244_inv;
        double _t269 = -Math.fma(_t12, _t71, Math.fma(_t15, _t70, -_t13 * _t78)) * _t246_inv;
        double _t270 = -Math.fma(_t12, _t76, Math.fma(_t15, _t75, -_t13 * _t79)) * _t248_inv;
        double _t271 = -Math.fma(_t0, _t76, Math.fma(_t7, _t75, -_t3 * _t79)) * _t250_inv;
        double _t276 = Math.fma(_t0, _t84, Math.fma(_t7, _t85, -_t5 * _t99)) * _t245_inv;
        double _t277 = Math.fma(_t12, _t84, Math.fma(_t15, _t85, -_t14 * _t99)) * _t247_inv;
        double _t278 = Math.fma(_t12, _t93, Math.fma(_t15, _t94, -_t14 * _t102)) * _t249_inv;
        double _t279 = Math.fma(_t0, _t93, Math.fma(_t7, _t94, -_t5 * _t102)) * _t251_inv;
        double _t284 = -Math.fma(_t0, _t83, Math.fma(_t3, _t85, -_t5 * _t86)) * _t245_inv;
        double _t285 = -Math.fma(_t12, _t83, Math.fma(_t13, _t85, -_t14 * _t86)) * _t247_inv;
        double _t286 = -Math.fma(_t12, _t92, Math.fma(_t13, _t94, -_t14 * _t95)) * _t249_inv;
        double _t287 = -Math.fma(_t0, _t92, Math.fma(_t3, _t94, -_t5 * _t95)) * _t251_inv;
        double _t288 = -Math.fma(_t0, _t82, Math.fma(_t7, _t86, -_t3 * _t99)) * _t245_inv;
        double _t289 = -Math.fma(_t12, _t82, Math.fma(_t15, _t86, -_t13 * _t99)) * _t247_inv;
        double _t290 = -Math.fma(_t12, _t91, Math.fma(_t15, _t95, -_t13 * _t102)) * _t249_inv;
        double _t291 = -Math.fma(_t0, _t91, Math.fma(_t7, _t95, -_t3 * _t102)) * _t251_inv;
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (corner) {
            case NXNYNZ: _idxSw0 = _t264 != _t264 ? _t204 > _t240 ? _t264 : _t284 : _t264; _idxSw1 = _t256 != _t256 ? _t204 > _t240 ? _t256 : _t276 : _t256; _idxSw2 = _t268 != _t268 ? _t204 > _t240 ? _t268 : _t288 : _t268; break;
            case PXNYNZ: _idxSw0 = _t265 != _t265 ? _t205 > _t241 ? _t265 : _t285 : _t265; _idxSw1 = _t257 != _t257 ? _t205 > _t241 ? _t257 : _t277 : _t257; _idxSw2 = _t269 != _t269 ? _t205 > _t241 ? _t269 : _t289 : _t269; break;
            case PXPYNZ: _idxSw0 = _t266 != _t266 ? _t206 > _t242 ? _t266 : _t286 : _t266; _idxSw1 = _t258 != _t258 ? _t206 > _t242 ? _t258 : _t278 : _t258; _idxSw2 = _t270 != _t270 ? _t206 > _t242 ? _t270 : _t290 : _t270; break;
            case NXPYNZ: _idxSw0 = _t267 != _t267 ? _t207 > _t243 ? _t267 : _t287 : _t267; _idxSw1 = _t259 != _t259 ? _t207 > _t243 ? _t259 : _t279 : _t259; _idxSw2 = _t271 != _t271 ? _t207 > _t243 ? _t271 : _t291 : _t271; break;
            case NXNYPZ: _idxSw0 = _t284 != _t284 ? _t232 > _t212 ? _t284 : _t264 : _t284; _idxSw1 = _t276 != _t276 ? _t232 > _t212 ? _t276 : _t256 : _t276; _idxSw2 = _t288 != _t288 ? _t232 > _t212 ? _t288 : _t268 : _t288; break;
            case PXNYPZ: _idxSw0 = _t285 != _t285 ? _t233 > _t213 ? _t285 : _t265 : _t285; _idxSw1 = _t277 != _t277 ? _t233 > _t213 ? _t277 : _t257 : _t277; _idxSw2 = _t289 != _t289 ? _t233 > _t213 ? _t289 : _t269 : _t289; break;
            case PXPYPZ: _idxSw0 = _t286 != _t286 ? _t234 > _t214 ? _t286 : _t266 : _t286; _idxSw1 = _t278 != _t278 ? _t234 > _t214 ? _t278 : _t258 : _t278; _idxSw2 = _t290 != _t290 ? _t234 > _t214 ? _t290 : _t270 : _t290; break;
            case NXPYPZ: _idxSw0 = _t287 != _t287 ? _t235 > _t215 ? _t287 : _t267 : _t287; _idxSw1 = _t279 != _t279 ? _t235 > _t215 ? _t279 : _t259 : _t279; _idxSw2 = _t291 != _t291 ? _t235 > _t215 ? _t291 : _t271 : _t291; break;
            default: throw new IllegalArgumentException("Unknown FrustumCorner: " + corner);
        }
        dest[destOffset] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        return dest;
    }

    public static double[] frustumPlane_no(double[] dest, int destOffset, double[] src, int srcOffset, FrustumPlane plane) {
        return frustumPlane_no_sef8802f9_1(dest, destOffset, plane, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15]);
    }

    /** Piece 2 of {@code frustumPlane_no}, split to fit the inline budget; reached only through it. */
    private static double[] frustumPlane_no_sef8802f9_1(double[] dest, int destOffset, FrustumPlane plane, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33) {
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        double _idxSw3;
        switch (plane) {
            case NX: _idxSw0 = _self00 + _self30; _idxSw1 = _self01 + _self31; _idxSw2 = _self02 + _self32; _idxSw3 = _self03 + _self33; break;
            case PX: _idxSw0 = _self30 - _self00; _idxSw1 = _self31 - _self01; _idxSw2 = _self32 - _self02; _idxSw3 = _self33 - _self03; break;
            case NY: _idxSw0 = _self10 + _self30; _idxSw1 = _self11 + _self31; _idxSw2 = _self12 + _self32; _idxSw3 = _self13 + _self33; break;
            case PY: _idxSw0 = _self30 - _self10; _idxSw1 = _self31 - _self11; _idxSw2 = _self32 - _self12; _idxSw3 = _self33 - _self13; break;
            case NZ: _idxSw0 = _self20 + _self30; _idxSw1 = _self21 + _self31; _idxSw2 = _self22 + _self32; _idxSw3 = _self23 + _self33; break;
            case PZ: _idxSw0 = _self30 - _self20; _idxSw1 = _self31 - _self21; _idxSw2 = _self32 - _self22; _idxSw3 = _self33 - _self23; break;
            default: throw new IllegalArgumentException("Unknown FrustumPlane: " + plane);
        }
        dest[destOffset] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        dest[destOffset + 3] = _idxSw3;
        return dest;
    }

    public static double[] frustumPlane_zo(double[] dest, int destOffset, double[] src, int srcOffset, FrustumPlane plane) {
        return frustumPlane_zo_sd9f50cb5_1(dest, destOffset, plane, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15]);
    }

    /** Piece 2 of {@code frustumPlane_zo}, split to fit the inline budget; reached only through it. */
    private static double[] frustumPlane_zo_sd9f50cb5_1(double[] dest, int destOffset, FrustumPlane plane, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33) {
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        double _idxSw3;
        switch (plane) {
            case NX: _idxSw0 = _self00 + _self30; _idxSw1 = _self01 + _self31; _idxSw2 = _self02 + _self32; _idxSw3 = _self03 + _self33; break;
            case PX: _idxSw0 = _self30 - _self00; _idxSw1 = _self31 - _self01; _idxSw2 = _self32 - _self02; _idxSw3 = _self33 - _self03; break;
            case NY: _idxSw0 = _self10 + _self30; _idxSw1 = _self11 + _self31; _idxSw2 = _self12 + _self32; _idxSw3 = _self13 + _self33; break;
            case PY: _idxSw0 = _self30 - _self10; _idxSw1 = _self31 - _self11; _idxSw2 = _self32 - _self12; _idxSw3 = _self33 - _self13; break;
            case NZ: _idxSw0 = _self20; _idxSw1 = _self21; _idxSw2 = _self22; _idxSw3 = _self23; break;
            case PZ: _idxSw0 = _self30 - _self20; _idxSw1 = _self31 - _self21; _idxSw2 = _self32 - _self22; _idxSw3 = _self33 - _self23; break;
            default: throw new IllegalArgumentException("Unknown FrustumPlane: " + plane);
        }
        dest[destOffset] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        dest[destOffset + 3] = _idxSw3;
        return dest;
    }

    public static double[] frustumRayDir_no(double[] dest, int destOffset, double[] src, int srcOffset, double x, double y) {
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = -_self32;
        double _t1 = -_self30;
        double _t2 = -_self31;
        double _t3 = -_self33;
        double _t4 = Math.fma(2.0, x, -1.0);
        double _t5 = _self21 + _self31;
        double _t6 = Math.fma(2.0, y, -1.0);
        double _t7 = _self20 + _self30;
        double _t8 = _self22 + _self32;
        double _t9 = _self21 - _self31;
        double _t10 = _self20 - _self30;
        double _t11 = _self22 - _self32;
        double _t12 = _self23 + _self33;
        double _t13 = _self23 - _self33;
        double _t14 = Math.fma(_t0, _t4, src[srcOffset + 8]);
        double _t15 = Math.fma(_t1, _t6, src[srcOffset + 1]);
        double _t16 = Math.fma(_t2, _t6, src[srcOffset + 5]);
        double _t17 = Math.fma(_t1, _t4, src[srcOffset]);
        double _t18 = Math.fma(_t0, _t6, src[srcOffset + 9]);
        double _t19 = Math.fma(_t2, _t4, src[srcOffset + 4]);
        double _t20 = Math.fma(_t3, _t6, src[srcOffset + 13]);
        double _t21 = Math.fma(_t3, _t4, src[srcOffset + 12]);
        double _t22 = -_t19;
        double _t23 = -_t21;
        double _t24 = -_t14;
        double _t25 = -_t17;
        double _t50 = Math.fma(_t5, _t15, -_t7 * _t16);
        double _t51 = Math.fma(_t8, _t16, -(_t5 * _t18));
        double _t52 = Math.fma(_t8, _t15, -(_t7 * _t18));
        double _t53 = Math.fma(_t15, _t9, _t16 * -_t10);
        double _t54 = Math.fma(_t16, _t11, _t18 * -_t9);
        double _t55 = Math.fma(_t15, _t11, _t18 * -_t10);
        double _t56 = Math.fma(_t12, _t18, -(_t8 * _t20));
        double _t57 = Math.fma(_t12, _t16, -(_t5 * _t20));
        double _t58 = Math.fma(_t18, _t13, -(_t20 * _t11));
        double _t59 = Math.fma(_t16, _t13, _t20 * -_t9);
        double _t60 = Math.fma(_t12, _t15, -_t7 * _t20);
        double _t61 = Math.fma(_t15, _t13, -(_t20 * _t10));
        double _t68 = Math.fma(_t14, _t50, Math.fma(_t17, _t51, -(_t19 * _t52)));
        double _t69 = Math.fma(_t14, _t53, Math.fma(_t17, _t54, -(_t19 * _t55)));
        double _sp0 = _t69 / _t68;
        double _t69_inv = 1.0 / _t69;
        double _t70 = java.lang.Math.abs(_t68);
        double _t71 = java.lang.Math.abs(_t69);
        double _t74_inv = 1.0 / (_t71 <= _t70 * 9.094947017729282E-13 ? _t68 : _t69);
        if (_t70 <= _t71 * 9.094947017729282E-13) {
            dest[destOffset] = Math.fma(_t22, _t56, Math.fma(_t14, _t57, Math.fma(_t23, _t51, _t68 * Math.fma(_t21, _t54, Math.fma(_t19, _t58, -(_t14 * _t59))) / _t69))) * _t69_inv;
            dest[destOffset + 1] = Math.fma(_t17, _t56, Math.fma(_t24, _t60, Math.fma(_t21, _t52, -(_t68 * Math.fma(_t21, _t55, Math.fma(_t17, _t58, -(_t14 * _t61))) / _t69)))) * _t69_inv;
            dest[destOffset + 2] = Math.fma(_t25, _t57, Math.fma(_t19, _t60, Math.fma(_t23, _t50, _t68 * Math.fma(_t21, _t53, Math.fma(_t17, _t59, -(_t19 * _t61))) / _t69))) * _t69_inv;
        } else {
            dest[destOffset] = Math.fma(_t22, _t58, Math.fma(_t14, _t59, Math.fma(_t23, _t54, _sp0 * Math.fma(_t21, _t51, Math.fma(_t19, _t56, -(_t14 * _t57)))))) * _t74_inv;
            dest[destOffset + 1] = Math.fma(_t17, _t58, Math.fma(_t24, _t61, Math.fma(_t21, _t55, -Math.fma(_t21, _t52, Math.fma(_t17, _t56, -(_t14 * _t60))) * _sp0))) * _t74_inv;
            dest[destOffset + 2] = Math.fma(_t25, _t59, Math.fma(_t19, _t61, Math.fma(_t23, _t53, Math.fma(_t21, _t50, Math.fma(_t17, _t57, -(_t19 * _t60))) * _sp0))) * _t74_inv;
        }
        return dest;
    }

    public static double[] frustumRayDir_zo(double[] dest, int destOffset, double[] src, int srcOffset, double x, double y) {
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = -_self32;
        double _t1 = -_self30;
        double _t2 = -_self31;
        double _t3 = -_self33;
        double _t4 = Math.fma(2.0, x, -1.0);
        double _t5 = Math.fma(2.0, y, -1.0);
        double _t6 = _self21 - _self31;
        double _t7 = _self20 - _self30;
        double _t8 = _self22 - _self32;
        double _t9 = _self23 - _self33;
        double _t10 = Math.fma(_t0, _t4, src[srcOffset + 8]);
        double _t11 = Math.fma(_t1, _t5, src[srcOffset + 1]);
        double _t12 = Math.fma(_t2, _t5, src[srcOffset + 5]);
        double _t13 = Math.fma(_t1, _t4, src[srcOffset]);
        double _t14 = Math.fma(_t0, _t5, src[srcOffset + 9]);
        double _t15 = Math.fma(_t2, _t4, src[srcOffset + 4]);
        double _t16 = Math.fma(_t3, _t5, src[srcOffset + 13]);
        double _t17 = Math.fma(_t3, _t4, src[srcOffset + 12]);
        double _t18 = -_t15;
        double _t19 = -_t17;
        double _t20 = -_t10;
        double _t21 = -_t13;
        double _t46 = Math.fma(_self21, _t11, -_self20 * _t12);
        double _t47 = Math.fma(_self22, _t12, -(_self21 * _t14));
        double _t48 = Math.fma(_self22, _t11, -(_self20 * _t14));
        double _t49 = Math.fma(_self23, _t14, -(_self22 * _t16));
        double _t50 = Math.fma(_self23, _t12, -(_self21 * _t16));
        double _t51 = Math.fma(_self23, _t11, -_self20 * _t16);
        double _t52 = Math.fma(_t11, _t6, _t12 * -_t7);
        double _t53 = Math.fma(_t12, _t8, _t14 * -_t6);
        double _t54 = Math.fma(_t11, _t8, _t14 * -_t7);
        double _t55 = Math.fma(_t14, _t9, -(_t16 * _t8));
        double _t56 = Math.fma(_t12, _t9, _t16 * -_t6);
        double _t57 = Math.fma(_t11, _t9, -(_t16 * _t7));
        double _t64 = Math.fma(_t10, _t46, Math.fma(_t13, _t47, -(_t15 * _t48)));
        double _t65 = java.lang.Math.abs(_t64);
        double _t67 = Math.fma(_t10, _t52, Math.fma(_t13, _t53, -(_t15 * _t54)));
        double _sp0 = _t67 / _t64;
        double _t67_inv = 1.0 / _t67;
        double _t68 = java.lang.Math.abs(_t67);
        double _t70_inv = 1.0 / (_t68 <= _t65 * 9.094947017729282E-13 ? _t64 : _t67);
        if (_t65 <= _t68 * 9.094947017729282E-13) {
            dest[destOffset] = Math.fma(_t18, _t49, Math.fma(_t10, _t50, Math.fma(_t19, _t47, _t64 * Math.fma(_t17, _t53, Math.fma(_t15, _t55, -(_t10 * _t56))) / _t67))) * _t67_inv;
            dest[destOffset + 1] = Math.fma(_t13, _t49, Math.fma(_t20, _t51, Math.fma(_t17, _t48, -(_t64 * Math.fma(_t17, _t54, Math.fma(_t13, _t55, -(_t10 * _t57))) / _t67)))) * _t67_inv;
            dest[destOffset + 2] = Math.fma(_t21, _t50, Math.fma(_t15, _t51, Math.fma(_t19, _t46, _t64 * Math.fma(_t17, _t52, Math.fma(_t13, _t56, -(_t15 * _t57))) / _t67))) * _t67_inv;
        } else {
            dest[destOffset] = Math.fma(_t18, _t55, Math.fma(_t10, _t56, Math.fma(_t19, _t53, _sp0 * Math.fma(_t17, _t47, Math.fma(_t15, _t49, -(_t10 * _t50)))))) * _t70_inv;
            dest[destOffset + 1] = Math.fma(_t13, _t55, Math.fma(_t20, _t57, Math.fma(_t17, _t54, -Math.fma(_t17, _t48, Math.fma(_t13, _t49, -(_t10 * _t51))) * _sp0))) * _t70_inv;
            dest[destOffset + 2] = Math.fma(_t21, _t56, Math.fma(_t15, _t57, Math.fma(_t19, _t52, Math.fma(_t17, _t46, Math.fma(_t13, _t50, -(_t15 * _t51))) * _sp0))) * _t70_inv;
        }
        return dest;
    }

    public static double[] frustumRayDir_no(double[] dest, int destOffset, double[] src, int srcOffset, double x, double y, double epsilon) {
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = -_self32;
        double _t1 = -_self30;
        double _t2 = -_self31;
        double _t3 = -_self33;
        double _t4 = Math.fma(2.0, x, -1.0);
        double _t5 = _self21 + _self31;
        double _t6 = Math.fma(2.0, y, -1.0);
        double _t7 = _self20 + _self30;
        double _t8 = _self22 + _self32;
        double _t9 = _self21 - _self31;
        double _t10 = _self20 - _self30;
        double _t11 = _self22 - _self32;
        double _t12 = _self23 + _self33;
        double _t13 = _self23 - _self33;
        double _t14 = Math.fma(_t0, _t4, src[srcOffset + 8]);
        double _t15 = Math.fma(_t1, _t6, src[srcOffset + 1]);
        double _t16 = Math.fma(_t2, _t6, src[srcOffset + 5]);
        double _t17 = Math.fma(_t1, _t4, src[srcOffset]);
        double _t18 = Math.fma(_t0, _t6, src[srcOffset + 9]);
        double _t19 = Math.fma(_t2, _t4, src[srcOffset + 4]);
        double _t20 = Math.fma(_t3, _t6, src[srcOffset + 13]);
        double _t21 = Math.fma(_t3, _t4, src[srcOffset + 12]);
        double _t22 = -_t19;
        double _t23 = -_t21;
        double _t24 = -_t14;
        double _t25 = -_t17;
        double _t50 = Math.fma(_t5, _t15, -_t7 * _t16);
        double _t51 = Math.fma(_t8, _t16, -(_t5 * _t18));
        double _t52 = Math.fma(_t8, _t15, -(_t7 * _t18));
        double _t53 = Math.fma(_t15, _t9, _t16 * -_t10);
        double _t54 = Math.fma(_t16, _t11, _t18 * -_t9);
        double _t55 = Math.fma(_t15, _t11, _t18 * -_t10);
        double _t56 = Math.fma(_t12, _t18, -(_t8 * _t20));
        double _t57 = Math.fma(_t12, _t16, -(_t5 * _t20));
        double _t58 = Math.fma(_t18, _t13, -(_t20 * _t11));
        double _t59 = Math.fma(_t16, _t13, _t20 * -_t9);
        double _t60 = Math.fma(_t12, _t15, -_t7 * _t20);
        double _t61 = Math.fma(_t15, _t13, -(_t20 * _t10));
        double _t68 = Math.fma(_t14, _t50, Math.fma(_t17, _t51, -(_t19 * _t52)));
        double _t69 = Math.fma(_t14, _t53, Math.fma(_t17, _t54, -(_t19 * _t55)));
        double _sp0 = _t69 / _t68;
        double _t69_inv = 1.0 / _t69;
        double _t70 = java.lang.Math.abs(_t68);
        double _t71 = java.lang.Math.abs(_t69);
        double _t74_inv = 1.0 / (_t71 <= epsilon * _t70 ? _t68 : _t69);
        if (_t70 <= epsilon * _t71) {
            dest[destOffset] = Math.fma(_t22, _t56, Math.fma(_t14, _t57, Math.fma(_t23, _t51, _t68 * Math.fma(_t21, _t54, Math.fma(_t19, _t58, -(_t14 * _t59))) / _t69))) * _t69_inv;
            dest[destOffset + 1] = Math.fma(_t17, _t56, Math.fma(_t24, _t60, Math.fma(_t21, _t52, -(_t68 * Math.fma(_t21, _t55, Math.fma(_t17, _t58, -(_t14 * _t61))) / _t69)))) * _t69_inv;
            dest[destOffset + 2] = Math.fma(_t25, _t57, Math.fma(_t19, _t60, Math.fma(_t23, _t50, _t68 * Math.fma(_t21, _t53, Math.fma(_t17, _t59, -(_t19 * _t61))) / _t69))) * _t69_inv;
        } else {
            dest[destOffset] = Math.fma(_t22, _t58, Math.fma(_t14, _t59, Math.fma(_t23, _t54, _sp0 * Math.fma(_t21, _t51, Math.fma(_t19, _t56, -(_t14 * _t57)))))) * _t74_inv;
            dest[destOffset + 1] = Math.fma(_t17, _t58, Math.fma(_t24, _t61, Math.fma(_t21, _t55, -Math.fma(_t21, _t52, Math.fma(_t17, _t56, -(_t14 * _t60))) * _sp0))) * _t74_inv;
            dest[destOffset + 2] = Math.fma(_t25, _t59, Math.fma(_t19, _t61, Math.fma(_t23, _t53, Math.fma(_t21, _t50, Math.fma(_t17, _t57, -(_t19 * _t60))) * _sp0))) * _t74_inv;
        }
        return dest;
    }

    public static double[] frustumRayDir_zo(double[] dest, int destOffset, double[] src, int srcOffset, double x, double y, double epsilon) {
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = -_self32;
        double _t1 = -_self30;
        double _t2 = -_self31;
        double _t3 = -_self33;
        double _t4 = Math.fma(2.0, x, -1.0);
        double _t5 = Math.fma(2.0, y, -1.0);
        double _t6 = _self21 - _self31;
        double _t7 = _self20 - _self30;
        double _t8 = _self22 - _self32;
        double _t9 = _self23 - _self33;
        double _t10 = Math.fma(_t0, _t4, src[srcOffset + 8]);
        double _t11 = Math.fma(_t1, _t5, src[srcOffset + 1]);
        double _t12 = Math.fma(_t2, _t5, src[srcOffset + 5]);
        double _t13 = Math.fma(_t1, _t4, src[srcOffset]);
        double _t14 = Math.fma(_t0, _t5, src[srcOffset + 9]);
        double _t15 = Math.fma(_t2, _t4, src[srcOffset + 4]);
        double _t16 = Math.fma(_t3, _t5, src[srcOffset + 13]);
        double _t17 = Math.fma(_t3, _t4, src[srcOffset + 12]);
        double _t18 = -_t15;
        double _t19 = -_t17;
        double _t20 = -_t10;
        double _t21 = -_t13;
        double _t46 = Math.fma(_self21, _t11, -_self20 * _t12);
        double _t47 = Math.fma(_self22, _t12, -(_self21 * _t14));
        double _t48 = Math.fma(_self22, _t11, -(_self20 * _t14));
        double _t49 = Math.fma(_self23, _t14, -(_self22 * _t16));
        double _t50 = Math.fma(_self23, _t12, -(_self21 * _t16));
        double _t51 = Math.fma(_self23, _t11, -_self20 * _t16);
        double _t52 = Math.fma(_t11, _t6, _t12 * -_t7);
        double _t53 = Math.fma(_t12, _t8, _t14 * -_t6);
        double _t54 = Math.fma(_t11, _t8, _t14 * -_t7);
        double _t55 = Math.fma(_t14, _t9, -(_t16 * _t8));
        double _t56 = Math.fma(_t12, _t9, _t16 * -_t6);
        double _t57 = Math.fma(_t11, _t9, -(_t16 * _t7));
        double _t64 = Math.fma(_t10, _t46, Math.fma(_t13, _t47, -(_t15 * _t48)));
        double _t65 = java.lang.Math.abs(_t64);
        double _t67 = Math.fma(_t10, _t52, Math.fma(_t13, _t53, -(_t15 * _t54)));
        double _sp0 = _t67 / _t64;
        double _t67_inv = 1.0 / _t67;
        double _t68 = java.lang.Math.abs(_t67);
        double _t70_inv = 1.0 / (_t68 <= epsilon * _t65 ? _t64 : _t67);
        if (_t65 <= epsilon * _t68) {
            dest[destOffset] = Math.fma(_t18, _t49, Math.fma(_t10, _t50, Math.fma(_t19, _t47, _t64 * Math.fma(_t17, _t53, Math.fma(_t15, _t55, -(_t10 * _t56))) / _t67))) * _t67_inv;
            dest[destOffset + 1] = Math.fma(_t13, _t49, Math.fma(_t20, _t51, Math.fma(_t17, _t48, -(_t64 * Math.fma(_t17, _t54, Math.fma(_t13, _t55, -(_t10 * _t57))) / _t67)))) * _t67_inv;
            dest[destOffset + 2] = Math.fma(_t21, _t50, Math.fma(_t15, _t51, Math.fma(_t19, _t46, _t64 * Math.fma(_t17, _t52, Math.fma(_t13, _t56, -(_t15 * _t57))) / _t67))) * _t67_inv;
        } else {
            dest[destOffset] = Math.fma(_t18, _t55, Math.fma(_t10, _t56, Math.fma(_t19, _t53, _sp0 * Math.fma(_t17, _t47, Math.fma(_t15, _t49, -(_t10 * _t50)))))) * _t70_inv;
            dest[destOffset + 1] = Math.fma(_t13, _t55, Math.fma(_t20, _t57, Math.fma(_t17, _t54, -Math.fma(_t17, _t48, Math.fma(_t13, _t49, -(_t10 * _t51))) * _sp0))) * _t70_inv;
            dest[destOffset + 2] = Math.fma(_t21, _t56, Math.fma(_t15, _t57, Math.fma(_t19, _t52, Math.fma(_t17, _t46, Math.fma(_t13, _t50, -(_t15 * _t51))) * _sp0))) * _t70_inv;
        }
        return dest;
    }

    public static boolean testAabb_no(double[] src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = _self00 + _self30;
        double _t1 = _self01 + _self31;
        double _t2 = _self02 + _self32;
        double _t3 = _self30 - _self00;
        double _t4 = _self31 - _self01;
        double _t5 = _self32 - _self02;
        double _t6 = _self10 + _self30;
        double _t7 = _self11 + _self31;
        double _t8 = _self12 + _self32;
        double _t9 = _self30 - _self10;
        double _t10 = _self31 - _self11;
        double _t11 = _self32 - _self12;
        double _t12 = _self20 + _self30;
        double _t13 = _self21 + _self31;
        double _t14 = _self22 + _self32;
        double _t15 = _self30 - _self20;
        double _t16 = _self31 - _self21;
        double _t17 = _self32 - _self22;
        return !(Math.fma(_t0, _t0 >= 0.0 ? maxX : minX, Math.fma(_t1, _t1 >= 0.0 ? maxY : minY, Math.fma(_t2, _t2 >= 0.0 ? maxZ : minZ, _self03 + _self33))) < 0.0) && (!(Math.fma(_t3, _t3 >= 0.0 ? maxX : minX, Math.fma(_t4, _t4 >= 0.0 ? maxY : minY, Math.fma(_t5, _t5 >= 0.0 ? maxZ : minZ, _self33 - _self03))) < 0.0) && (!(Math.fma(_t6, _t6 >= 0.0 ? maxX : minX, Math.fma(_t7, _t7 >= 0.0 ? maxY : minY, Math.fma(_t8, _t8 >= 0.0 ? maxZ : minZ, _self13 + _self33))) < 0.0) && (!(Math.fma(_t9, _t9 >= 0.0 ? maxX : minX, Math.fma(_t10, _t10 >= 0.0 ? maxY : minY, Math.fma(_t11, _t11 >= 0.0 ? maxZ : minZ, _self33 - _self13))) < 0.0) && (!(Math.fma(_t12, _t12 >= 0.0 ? maxX : minX, Math.fma(_t13, _t13 >= 0.0 ? maxY : minY, Math.fma(_t14, _t14 >= 0.0 ? maxZ : minZ, _self23 + _self33))) < 0.0) && (!(Math.fma(_t15, _t15 >= 0.0 ? maxX : minX, Math.fma(_t16, _t16 >= 0.0 ? maxY : minY, Math.fma(_t17, _t17 >= 0.0 ? maxZ : minZ, _self33 - _self23))) < 0.0) && ((minX <= maxX) && ((minY <= maxY) && (minZ <= maxZ))))))));
    }

    public static boolean testAabb_zo(double[] src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = _self00 + _self30;
        double _t1 = _self01 + _self31;
        double _t2 = _self02 + _self32;
        double _t3 = _self30 - _self00;
        double _t4 = _self31 - _self01;
        double _t5 = _self32 - _self02;
        double _t6 = _self10 + _self30;
        double _t7 = _self11 + _self31;
        double _t8 = _self12 + _self32;
        double _t9 = _self30 - _self10;
        double _t10 = _self31 - _self11;
        double _t11 = _self32 - _self12;
        double _t12 = _self30 - _self20;
        double _t13 = _self31 - _self21;
        double _t14 = _self32 - _self22;
        return !(Math.fma(_t0, _t0 >= 0.0 ? maxX : minX, Math.fma(_t1, _t1 >= 0.0 ? maxY : minY, Math.fma(_t2, _t2 >= 0.0 ? maxZ : minZ, _self03 + _self33))) < 0.0) && (!(Math.fma(_t3, _t3 >= 0.0 ? maxX : minX, Math.fma(_t4, _t4 >= 0.0 ? maxY : minY, Math.fma(_t5, _t5 >= 0.0 ? maxZ : minZ, _self33 - _self03))) < 0.0) && (!(Math.fma(_t6, _t6 >= 0.0 ? maxX : minX, Math.fma(_t7, _t7 >= 0.0 ? maxY : minY, Math.fma(_t8, _t8 >= 0.0 ? maxZ : minZ, _self13 + _self33))) < 0.0) && (!(Math.fma(_t9, _t9 >= 0.0 ? maxX : minX, Math.fma(_t10, _t10 >= 0.0 ? maxY : minY, Math.fma(_t11, _t11 >= 0.0 ? maxZ : minZ, _self33 - _self13))) < 0.0) && (!(Math.fma(_self20, _self20 >= 0.0 ? maxX : minX, Math.fma(_self21, _self21 >= 0.0 ? maxY : minY, Math.fma(_self22, _self22 >= 0.0 ? maxZ : minZ, _self23))) < 0.0) && (!(Math.fma(_t12, _t12 >= 0.0 ? maxX : minX, Math.fma(_t13, _t13 >= 0.0 ? maxY : minY, Math.fma(_t14, _t14 >= 0.0 ? maxZ : minZ, _self33 - _self23))) < 0.0) && ((minX <= maxX) && ((minY <= maxY) && (minZ <= maxZ))))))));
    }

    public static boolean testAabb_no(double[] src, int srcOffset, double[] min, int minOffset, double[] max, int maxOffset) {
        double _minx = min[minOffset];
        double _miny = min[minOffset + 1];
        double _minz = min[minOffset + 2];
        double _maxx = max[maxOffset];
        double _maxy = max[maxOffset + 1];
        double _maxz = max[maxOffset + 2];
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = _self00 + _self30;
        double _t1 = _self01 + _self31;
        double _t2 = _self02 + _self32;
        double _t3 = _self30 - _self00;
        double _t4 = _self31 - _self01;
        double _t5 = _self32 - _self02;
        double _t6 = _self10 + _self30;
        double _t7 = _self11 + _self31;
        double _t8 = _self12 + _self32;
        double _t9 = _self30 - _self10;
        double _t10 = _self31 - _self11;
        double _t11 = _self32 - _self12;
        double _t12 = _self20 + _self30;
        double _t13 = _self21 + _self31;
        double _t14 = _self22 + _self32;
        double _t15 = _self30 - _self20;
        double _t16 = _self31 - _self21;
        double _t17 = _self32 - _self22;
        return !(Math.fma(_t0, _t0 >= 0.0 ? _maxx : _minx, Math.fma(_t1, _t1 >= 0.0 ? _maxy : _miny, Math.fma(_t2, _t2 >= 0.0 ? _maxz : _minz, _self03 + _self33))) < 0.0) && (!(Math.fma(_t3, _t3 >= 0.0 ? _maxx : _minx, Math.fma(_t4, _t4 >= 0.0 ? _maxy : _miny, Math.fma(_t5, _t5 >= 0.0 ? _maxz : _minz, _self33 - _self03))) < 0.0) && (!(Math.fma(_t6, _t6 >= 0.0 ? _maxx : _minx, Math.fma(_t7, _t7 >= 0.0 ? _maxy : _miny, Math.fma(_t8, _t8 >= 0.0 ? _maxz : _minz, _self13 + _self33))) < 0.0) && (!(Math.fma(_t9, _t9 >= 0.0 ? _maxx : _minx, Math.fma(_t10, _t10 >= 0.0 ? _maxy : _miny, Math.fma(_t11, _t11 >= 0.0 ? _maxz : _minz, _self33 - _self13))) < 0.0) && (!(Math.fma(_t12, _t12 >= 0.0 ? _maxx : _minx, Math.fma(_t13, _t13 >= 0.0 ? _maxy : _miny, Math.fma(_t14, _t14 >= 0.0 ? _maxz : _minz, _self23 + _self33))) < 0.0) && (!(Math.fma(_t15, _t15 >= 0.0 ? _maxx : _minx, Math.fma(_t16, _t16 >= 0.0 ? _maxy : _miny, Math.fma(_t17, _t17 >= 0.0 ? _maxz : _minz, _self33 - _self23))) < 0.0) && ((_minx <= _maxx) && ((_miny <= _maxy) && (_minz <= _maxz))))))));
    }

    public static boolean testAabb_zo(double[] src, int srcOffset, double[] min, int minOffset, double[] max, int maxOffset) {
        double _minx = min[minOffset];
        double _miny = min[minOffset + 1];
        double _minz = min[minOffset + 2];
        double _maxx = max[maxOffset];
        double _maxy = max[maxOffset + 1];
        double _maxz = max[maxOffset + 2];
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = _self00 + _self30;
        double _t1 = _self01 + _self31;
        double _t2 = _self02 + _self32;
        double _t3 = _self30 - _self00;
        double _t4 = _self31 - _self01;
        double _t5 = _self32 - _self02;
        double _t6 = _self10 + _self30;
        double _t7 = _self11 + _self31;
        double _t8 = _self12 + _self32;
        double _t9 = _self30 - _self10;
        double _t10 = _self31 - _self11;
        double _t11 = _self32 - _self12;
        double _t12 = _self30 - _self20;
        double _t13 = _self31 - _self21;
        double _t14 = _self32 - _self22;
        return !(Math.fma(_t0, _t0 >= 0.0 ? _maxx : _minx, Math.fma(_t1, _t1 >= 0.0 ? _maxy : _miny, Math.fma(_t2, _t2 >= 0.0 ? _maxz : _minz, _self03 + _self33))) < 0.0) && (!(Math.fma(_t3, _t3 >= 0.0 ? _maxx : _minx, Math.fma(_t4, _t4 >= 0.0 ? _maxy : _miny, Math.fma(_t5, _t5 >= 0.0 ? _maxz : _minz, _self33 - _self03))) < 0.0) && (!(Math.fma(_t6, _t6 >= 0.0 ? _maxx : _minx, Math.fma(_t7, _t7 >= 0.0 ? _maxy : _miny, Math.fma(_t8, _t8 >= 0.0 ? _maxz : _minz, _self13 + _self33))) < 0.0) && (!(Math.fma(_t9, _t9 >= 0.0 ? _maxx : _minx, Math.fma(_t10, _t10 >= 0.0 ? _maxy : _miny, Math.fma(_t11, _t11 >= 0.0 ? _maxz : _minz, _self33 - _self13))) < 0.0) && (!(Math.fma(_self20, _self20 >= 0.0 ? _maxx : _minx, Math.fma(_self21, _self21 >= 0.0 ? _maxy : _miny, Math.fma(_self22, _self22 >= 0.0 ? _maxz : _minz, _self23))) < 0.0) && (!(Math.fma(_t12, _t12 >= 0.0 ? _maxx : _minx, Math.fma(_t13, _t13 >= 0.0 ? _maxy : _miny, Math.fma(_t14, _t14 >= 0.0 ? _maxz : _minz, _self33 - _self23))) < 0.0) && ((_minx <= _maxx) && ((_miny <= _maxy) && (_minz <= _maxz))))))));
    }

    public static boolean testPoint_no(double[] src, int srcOffset, double pointX, double pointY, double pointZ) {
        return testPoint_no_se6b88088_1(pointX, pointY, pointZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15]);
    }

    /**
     * Piece 2 of {@code testPoint_no}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code testPoint}; reached only through it.
     */
    private static boolean testPoint_no_se6b88088_1(double pointX, double pointY, double pointZ, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33) {
        return (Math.fma(pointX, _self00 + _self30, Math.fma(pointY, _self01 + _self31, Math.fma(pointZ, _self02 + _self32, _self03 + _self33))) >= 0.0) && ((Math.fma(pointX, _self30 - _self00, Math.fma(pointY, _self31 - _self01, Math.fma(pointZ, _self32 - _self02, _self33 - _self03))) >= 0.0) && ((Math.fma(pointX, _self10 + _self30, Math.fma(pointY, _self11 + _self31, Math.fma(pointZ, _self12 + _self32, _self13 + _self33))) >= 0.0) && ((Math.fma(pointX, _self30 - _self10, Math.fma(pointY, _self31 - _self11, Math.fma(pointZ, _self32 - _self12, _self33 - _self13))) >= 0.0) && ((Math.fma(pointX, _self20 + _self30, Math.fma(pointY, _self21 + _self31, Math.fma(pointZ, _self22 + _self32, _self23 + _self33))) >= 0.0) && (Math.fma(pointX, _self30 - _self20, Math.fma(pointY, _self31 - _self21, Math.fma(pointZ, _self32 - _self22, _self33 - _self23))) >= 0.0)))));
    }

    public static boolean testPoint_zo(double[] src, int srcOffset, double pointX, double pointY, double pointZ) {
        return testPoint_zo_sf2d05cc_1(pointX, pointY, pointZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15]);
    }

    /**
     * Piece 2 of {@code testPoint_zo}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code testPoint}; reached only through it.
     */
    private static boolean testPoint_zo_sf2d05cc_1(double pointX, double pointY, double pointZ, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33) {
        return (Math.fma(pointX, _self00 + _self30, Math.fma(pointY, _self01 + _self31, Math.fma(pointZ, _self02 + _self32, _self03 + _self33))) >= 0.0) && ((Math.fma(pointX, _self30 - _self00, Math.fma(pointY, _self31 - _self01, Math.fma(pointZ, _self32 - _self02, _self33 - _self03))) >= 0.0) && ((Math.fma(pointX, _self10 + _self30, Math.fma(pointY, _self11 + _self31, Math.fma(pointZ, _self12 + _self32, _self13 + _self33))) >= 0.0) && ((Math.fma(pointX, _self30 - _self10, Math.fma(pointY, _self31 - _self11, Math.fma(pointZ, _self32 - _self12, _self33 - _self13))) >= 0.0) && ((Math.fma(pointX, _self20, Math.fma(pointY, _self21, Math.fma(pointZ, _self22, _self23))) >= 0.0) && (Math.fma(pointX, _self30 - _self20, Math.fma(pointY, _self31 - _self21, Math.fma(pointZ, _self32 - _self22, _self33 - _self23))) >= 0.0)))));
    }

    public static boolean testPoint_no(double[] src, int srcOffset, double[] point, int pointOffset) {
        return testPoint_no_se6b88088_1(point[pointOffset], point[pointOffset + 1], point[pointOffset + 2], src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15]);
    }

    public static boolean testPoint_zo(double[] src, int srcOffset, double[] point, int pointOffset) {
        return testPoint_zo_sf2d05cc_1(point[pointOffset], point[pointOffset + 1], point[pointOffset + 2], src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15]);
    }

    public static boolean testSphere_no(double[] src, int srcOffset, double centerX, double centerY, double centerZ, double radius) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self33 = src[srcOffset + 15];
        double _t1 = _self00 + _self30;
        double _t2 = _self01 + _self31;
        double _t3 = _self02 + _self32;
        double _t5 = _self30 - _self00;
        double _t6 = _self31 - _self01;
        double _t7 = _self32 - _self02;
        return testSphere_no_sdc6aee85_1(centerX, centerY, centerZ, src[srcOffset + 13], src[srcOffset + 14], _self33, radius * radius, _t1, _t2, _t3, _t5, _t6, _t7, _self10 + _self30, _self11 + _self31, _self12 + _self32, _self30 - _self10, _self31 - _self11, _self32 - _self12, _self20 + _self30, _self21 + _self31, _self22 + _self32, _self30 - _self20, _self31 - _self21, _self32 - _self22, Math.fma(centerX, _t1, Math.fma(centerY, _t2, Math.fma(centerZ, _t3, _self03 + _self33))), Math.fma(centerX, _t5, Math.fma(centerY, _t6, Math.fma(centerZ, _t7, _self33 - _self03))));
    }

    /** Piece 2 of {@code testSphere_no}, split to fit the inline budget; reached only through it. */
    private static boolean testSphere_no_sdc6aee85_1(double centerX, double centerY, double centerZ, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t5, double _t6, double _t7, double _t9, double _t10, double _t11, double _t13, double _t14, double _t15, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t37, double _t38) {
        return testSphere_no_sdc6aee85_2(_t0, _t1, _t2, _t3, _t5, _t6, _t7, _t9, _t10, _t11, _t13, _t14, _t15, _t17, _t18, _t19, _t21, _t22, _t23, _t37, _t38, Math.fma(centerX, _t9, Math.fma(centerY, _t10, Math.fma(centerZ, _t11, _self13 + _self33))), Math.fma(centerX, _t13, Math.fma(centerY, _t14, Math.fma(centerZ, _t15, _self33 - _self13))), Math.fma(centerX, _t17, Math.fma(centerY, _t18, Math.fma(centerZ, _t19, _self23 + _self33))), Math.fma(centerX, _t21, Math.fma(centerY, _t22, Math.fma(centerZ, _t23, _self33 - _self23))));
    }

    /**
     * Piece 3 of {@code testSphere_no}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code testSphere}; reached only through it.
     */
    private static boolean testSphere_no_sdc6aee85_2(double _t0, double _t1, double _t2, double _t3, double _t5, double _t6, double _t7, double _t9, double _t10, double _t11, double _t13, double _t14, double _t15, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t37, double _t38, double _t39, double _t40, double _t41, double _t42) {
        return ((_t37 >= 0.0) || (_t37 * _t37 <= Math.fma(_t3, _t3, Math.fma(_t1, _t1, _t2 * _t2)) * _t0)) && (((_t38 >= 0.0) || (_t38 * _t38 <= Math.fma(_t7, _t7, Math.fma(_t5, _t5, _t6 * _t6)) * _t0)) && (((_t39 >= 0.0) || (_t39 * _t39 <= Math.fma(_t11, _t11, Math.fma(_t9, _t9, _t10 * _t10)) * _t0)) && (((_t40 >= 0.0) || (_t40 * _t40 <= Math.fma(_t15, _t15, Math.fma(_t13, _t13, _t14 * _t14)) * _t0)) && (((_t41 >= 0.0) || (_t41 * _t41 <= Math.fma(_t19, _t19, Math.fma(_t17, _t17, _t18 * _t18)) * _t0)) && ((_t42 >= 0.0) || (_t42 * _t42 <= Math.fma(_t23, _t23, Math.fma(_t21, _t21, _t22 * _t22)) * _t0))))));
    }

    public static boolean testSphere_zo(double[] src, int srcOffset, double centerX, double centerY, double centerZ, double radius) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t1 = _self00 + _self30;
        double _t2 = _self01 + _self31;
        double _t3 = _self02 + _self32;
        return testSphere_zo_s1b29d419_1(centerX, centerY, centerZ, _self20, _self21, _self22, _self03, src[srcOffset + 13], _self23, _self33, radius * radius, _t1, _t2, _t3, _self30 - _self00, _self31 - _self01, _self32 - _self02, _self10 + _self30, _self11 + _self31, _self12 + _self32, _self30 - _self10, _self31 - _self11, _self32 - _self12, _self30 - _self20, _self31 - _self21, _self32 - _self22, Math.fma(centerX, _self20, Math.fma(centerY, _self21, Math.fma(centerZ, _self22, _self23))), Math.fma(centerX, _t1, Math.fma(centerY, _t2, Math.fma(centerZ, _t3, _self03 + _self33))));
    }

    /**
     * Piece 2 of {@code testSphere_zo}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code testSphere}; reached only through it.
     */
    private static boolean testSphere_zo_s1b29d419_1(double centerX, double centerY, double centerZ, double _self20, double _self21, double _self22, double _self03, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t5, double _t6, double _t7, double _t9, double _t10, double _t11, double _t13, double _t14, double _t15, double _t17, double _t18, double _t19, double _t28, double _t34) {
        return testSphere_zo_s1b29d419_2(_self20, _self21, _self22, _t0, _t1, _t2, _t3, _t5, _t6, _t7, _t9, _t10, _t11, _t13, _t14, _t15, _t17, _t18, _t19, _t28, _t34, Math.fma(centerX, _t5, Math.fma(centerY, _t6, Math.fma(centerZ, _t7, _self33 - _self03))), Math.fma(centerX, _t9, Math.fma(centerY, _t10, Math.fma(centerZ, _t11, _self13 + _self33))), Math.fma(centerX, _t13, Math.fma(centerY, _t14, Math.fma(centerZ, _t15, _self33 - _self13))), Math.fma(centerX, _t17, Math.fma(centerY, _t18, Math.fma(centerZ, _t19, _self33 - _self23))));
    }

    /**
     * Piece 3 of {@code testSphere_zo}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code testSphere}; reached only through it.
     */
    private static boolean testSphere_zo_s1b29d419_2(double _self20, double _self21, double _self22, double _t0, double _t1, double _t2, double _t3, double _t5, double _t6, double _t7, double _t9, double _t10, double _t11, double _t13, double _t14, double _t15, double _t17, double _t18, double _t19, double _t28, double _t34, double _t35, double _t36, double _t37, double _t38) {
        return ((_t34 >= 0.0) || (_t34 * _t34 <= Math.fma(_t3, _t3, Math.fma(_t1, _t1, _t2 * _t2)) * _t0)) && (((_t35 >= 0.0) || (_t35 * _t35 <= Math.fma(_t7, _t7, Math.fma(_t5, _t5, _t6 * _t6)) * _t0)) && (((_t36 >= 0.0) || (_t36 * _t36 <= Math.fma(_t11, _t11, Math.fma(_t9, _t9, _t10 * _t10)) * _t0)) && (((_t37 >= 0.0) || (_t37 * _t37 <= Math.fma(_t15, _t15, Math.fma(_t13, _t13, _t14 * _t14)) * _t0)) && (((_t28 >= 0.0) || (_t28 * _t28 <= Math.fma(_self22, _self22, Math.fma(_self20, _self20, _self21 * _self21)) * _t0)) && ((_t38 >= 0.0) || (_t38 * _t38 <= Math.fma(_t19, _t19, Math.fma(_t17, _t17, _t18 * _t18)) * _t0))))));
    }

    public static boolean testSphere_no(double[] src, int srcOffset, double[] center, int centerOffset, double radius) {
        double _centerx = center[centerOffset];
        double _centery = center[centerOffset + 1];
        double _centerz = center[centerOffset + 2];
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self33 = src[srcOffset + 15];
        double _t1 = _self00 + _self30;
        double _t2 = _self01 + _self31;
        double _t3 = _self02 + _self32;
        return testSphere_no_s253ebdab_1(_centerx, _centery, _centerz, _self03, src[srcOffset + 13], src[srcOffset + 14], _self33, radius * radius, _t1, _t2, _t3, _self30 - _self00, _self31 - _self01, _self32 - _self02, _self10 + _self30, _self11 + _self31, _self12 + _self32, _self30 - _self10, _self31 - _self11, _self32 - _self12, _self20 + _self30, _self21 + _self31, _self22 + _self32, _self30 - _self20, _self31 - _self21, _self32 - _self22, Math.fma(_centerx, _t1, Math.fma(_centery, _t2, Math.fma(_centerz, _t3, _self03 + _self33))));
    }

    /** Piece 2 of {@code testSphere_no}, split to fit the inline budget; reached only through it. */
    private static boolean testSphere_no_s253ebdab_1(double _centerx, double _centery, double _centerz, double _self03, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t5, double _t6, double _t7, double _t9, double _t10, double _t11, double _t13, double _t14, double _t15, double _t17, double _t18, double _t19, double _t21, double _t22, double _t23, double _t37) {
        return testSphere_no_sdc6aee85_2(_t0, _t1, _t2, _t3, _t5, _t6, _t7, _t9, _t10, _t11, _t13, _t14, _t15, _t17, _t18, _t19, _t21, _t22, _t23, _t37, Math.fma(_centerx, _t5, Math.fma(_centery, _t6, Math.fma(_centerz, _t7, _self33 - _self03))), Math.fma(_centerx, _t9, Math.fma(_centery, _t10, Math.fma(_centerz, _t11, _self13 + _self33))), Math.fma(_centerx, _t13, Math.fma(_centery, _t14, Math.fma(_centerz, _t15, _self33 - _self13))), Math.fma(_centerx, _t17, Math.fma(_centery, _t18, Math.fma(_centerz, _t19, _self23 + _self33))), Math.fma(_centerx, _t21, Math.fma(_centery, _t22, Math.fma(_centerz, _t23, _self33 - _self23))));
    }

    public static boolean testSphere_zo(double[] src, int srcOffset, double[] center, int centerOffset, double radius) {
        double _centerx = center[centerOffset];
        double _centery = center[centerOffset + 1];
        double _centerz = center[centerOffset + 2];
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t1 = _self00 + _self30;
        double _t2 = _self01 + _self31;
        double _t3 = _self02 + _self32;
        return testSphere_zo_s1b29d419_1(_centerx, _centery, _centerz, _self20, _self21, _self22, _self03, src[srcOffset + 13], _self23, _self33, radius * radius, _t1, _t2, _t3, _self30 - _self00, _self31 - _self01, _self32 - _self02, _self10 + _self30, _self11 + _self31, _self12 + _self32, _self30 - _self10, _self31 - _self11, _self32 - _self12, _self30 - _self20, _self31 - _self21, _self32 - _self22, Math.fma(_centerx, _self20, Math.fma(_centery, _self21, Math.fma(_centerz, _self22, _self23))), Math.fma(_centerx, _t1, Math.fma(_centery, _t2, Math.fma(_centerz, _t3, _self03 + _self33))));
    }

    public static double[] frustum_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _sp4 = zNear + zNear;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t3_inv = 1.0 / (zNear - zFar);
        double _t12 = 2.0 * (zNear == Double.POSITIVE_INFINITY ? zFar : zNear);
        double _t18, _t19;
        if (zFar == Double.POSITIVE_INFINITY) {
            _t18 = 1.0;
            _t19 = -_sp4;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                _t18 = -1.0;
                _t19 = zFar + zFar;
            } else {
                _t18 = -(zFar + zNear) * _t3_inv;
                _t19 = _sp4 * zFar * _t3_inv;
            }
        }
        return frustum_no_lh_s67463abe_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t0_inv * (left + right), _t1_inv * (bottom + top), _t12 * _t1_inv, _t12 * _t0_inv, _t18, _t19);
    }

    /** Piece 2 of {@code frustum_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] frustum_no_lh_s67463abe_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp2, double _sp3, double _sp1, double _sp0, double _t18, double _t19) {
        dest[destOffset] = _self00 * _sp0;
        dest[destOffset + 1] = _self10 * _sp0;
        dest[destOffset + 2] = _self20 * _sp0;
        dest[destOffset + 3] = _self30 * _sp0;
        dest[destOffset + 4] = _self01 * _sp1;
        dest[destOffset + 5] = _self11 * _sp1;
        dest[destOffset + 6] = _self21 * _sp1;
        dest[destOffset + 7] = _self31 * _sp1;
        dest[destOffset + 8] = Math.fma(_self02, _t18, _self03 - _self00 * _sp2 - _self01 * _sp3);
        dest[destOffset + 9] = Math.fma(_self12, _t18, _self13 - _self10 * _sp2 - _self11 * _sp3);
        dest[destOffset + 10] = Math.fma(_self22, _t18, _self23 - _self20 * _sp2 - _self21 * _sp3);
        dest[destOffset + 11] = Math.fma(_self32, _t18, _self33 - _self30 * _sp2 - _self31 * _sp3);
        dest[destOffset + 12] = _self02 * _t19;
        dest[destOffset + 13] = _self12 * _t19;
        dest[destOffset + 14] = _self22 * _t19;
        dest[destOffset + 15] = _self32 * _t19;
        return dest;
    }

    public static double[] frustum_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _sp4 = zNear + zNear;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t3_inv = 1.0 / (zNear - zFar);
        double _t12 = 2.0 * (zNear == Double.POSITIVE_INFINITY ? zFar : zNear);
        double _t16, _t18;
        if (zFar == Double.POSITIVE_INFINITY) {
            _t16 = -1.0;
            _t18 = -_sp4;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                _t16 = 1.0;
                _t18 = zFar + zFar;
            } else {
                _t16 = (zFar + zNear) * _t3_inv;
                _t18 = _sp4 * zFar * _t3_inv;
            }
        }
        return frustum_no_rh_s25681ac4_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t0_inv * (left + right), _t1_inv * (bottom + top), _t12 * _t1_inv, _t12 * _t0_inv, _t16, _t18);
    }

    /** Piece 2 of {@code frustum_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] frustum_no_rh_s25681ac4_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp2, double _sp3, double _sp1, double _sp0, double _t16, double _t18) {
        dest[destOffset] = _self00 * _sp0;
        dest[destOffset + 1] = _self10 * _sp0;
        dest[destOffset + 2] = _self20 * _sp0;
        dest[destOffset + 3] = _self30 * _sp0;
        dest[destOffset + 4] = _self01 * _sp1;
        dest[destOffset + 5] = _self11 * _sp1;
        dest[destOffset + 6] = _self21 * _sp1;
        dest[destOffset + 7] = _self31 * _sp1;
        dest[destOffset + 8] = Math.fma(_self02, _t16, _self00 * _sp2 + _self01 * _sp3 - _self03);
        dest[destOffset + 9] = Math.fma(_self12, _t16, _self10 * _sp2 + _self11 * _sp3 - _self13);
        dest[destOffset + 10] = Math.fma(_self22, _t16, _self20 * _sp2 + _self21 * _sp3 - _self23);
        dest[destOffset + 11] = Math.fma(_self32, _t16, _self30 * _sp2 + _self31 * _sp3 - _self33);
        dest[destOffset + 12] = _self02 * _t18;
        dest[destOffset + 13] = _self12 * _t18;
        dest[destOffset + 14] = _self22 * _t18;
        dest[destOffset + 15] = _self32 * _t18;
        return dest;
    }

    public static double[] frustum_no(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.frustum_no_lh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Double4x4OpsKernelsArray.frustum_no_rh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static double[] frustum_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _sp4 = zFar / (zNear - zFar);
        double _t9 = 2.0 * (zNear == Double.POSITIVE_INFINITY ? zFar : zNear);
        double _sp0 = _t9 * _t0_inv;
        double _t13, _t14;
        if (zFar == Double.POSITIVE_INFINITY) {
            _t13 = 1.0;
            _t14 = -zNear;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                _t13 = 0.0;
                _t14 = zFar;
            } else {
                _t13 = -_sp4;
                _t14 = _sp4 * zNear;
            }
        }
        dest[destOffset] = _self00 * _sp0;
        return frustum_zo_lh_s2fefba2a_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t0_inv * (left + right), _t1_inv * (bottom + top), _t9 * _t1_inv, _sp0, _t13, _t14);
    }

    /** Piece 2 of {@code frustum_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] frustum_zo_lh_s2fefba2a_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp2, double _sp3, double _sp1, double _sp0, double _t13, double _t14) {
        dest[destOffset + 1] = _self10 * _sp0;
        dest[destOffset + 2] = _self20 * _sp0;
        dest[destOffset + 3] = _self30 * _sp0;
        dest[destOffset + 4] = _self01 * _sp1;
        dest[destOffset + 5] = _self11 * _sp1;
        dest[destOffset + 6] = _self21 * _sp1;
        dest[destOffset + 7] = _self31 * _sp1;
        dest[destOffset + 8] = Math.fma(_self02, _t13, _self03 - _self00 * _sp2 - _self01 * _sp3);
        dest[destOffset + 9] = Math.fma(_self12, _t13, _self13 - _self10 * _sp2 - _self11 * _sp3);
        dest[destOffset + 10] = Math.fma(_self22, _t13, _self23 - _self20 * _sp2 - _self21 * _sp3);
        dest[destOffset + 11] = Math.fma(_self32, _t13, _self33 - _self30 * _sp2 - _self31 * _sp3);
        dest[destOffset + 12] = _self02 * _t14;
        dest[destOffset + 13] = _self12 * _t14;
        dest[destOffset + 14] = _self22 * _t14;
        dest[destOffset + 15] = _self32 * _t14;
        return dest;
    }

    public static double[] frustum_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _sp4 = zFar / (zNear - zFar);
        double _t8 = 2.0 * (zNear == Double.POSITIVE_INFINITY ? zFar : zNear);
        double _sp0 = _t8 * _t0_inv;
        double _t12, _t13;
        if (zFar == Double.POSITIVE_INFINITY) {
            _t12 = -1.0;
            _t13 = -zNear;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                _t12 = 0.0;
                _t13 = zFar;
            } else {
                _t12 = _sp4;
                _t13 = _sp4 * zNear;
            }
        }
        dest[destOffset] = _self00 * _sp0;
        return frustum_zo_rh_s570ada8_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t0_inv * (left + right), _t1_inv * (bottom + top), _t8 * _t1_inv, _sp0, _t12, _t13);
    }

    /** Piece 2 of {@code frustum_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] frustum_zo_rh_s570ada8_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp2, double _sp3, double _sp1, double _sp0, double _t12, double _t13) {
        dest[destOffset + 1] = _self10 * _sp0;
        dest[destOffset + 2] = _self20 * _sp0;
        dest[destOffset + 3] = _self30 * _sp0;
        dest[destOffset + 4] = _self01 * _sp1;
        dest[destOffset + 5] = _self11 * _sp1;
        dest[destOffset + 6] = _self21 * _sp1;
        dest[destOffset + 7] = _self31 * _sp1;
        dest[destOffset + 8] = Math.fma(_self02, _t12, _self00 * _sp2 + _self01 * _sp3 - _self03);
        dest[destOffset + 9] = Math.fma(_self12, _t12, _self10 * _sp2 + _self11 * _sp3 - _self13);
        dest[destOffset + 10] = Math.fma(_self22, _t12, _self20 * _sp2 + _self21 * _sp3 - _self23);
        dest[destOffset + 11] = Math.fma(_self32, _t12, _self30 * _sp2 + _self31 * _sp3 - _self33);
        dest[destOffset + 12] = _self02 * _t13;
        dest[destOffset + 13] = _self12 * _t13;
        dest[destOffset + 14] = _self22 * _t13;
        dest[destOffset + 15] = _self32 * _t13;
        return dest;
    }

    public static double[] frustum_zo(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.frustum_zo_lh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Double4x4OpsKernelsArray.frustum_zo_rh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(upZ, _t12, Math.fma(upX, _t11, upY * _t13));
        double _t18 = Math.fma(_t17, _t11, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t12, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t11));
        double _t28 = Math.fma(_t19, _t12, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t11, -(_t18 * _t12));
        return lookAlong_lh_s62f659ca_1(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t11, _t12, _t13, _t27, _t28, _t29, Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_s62f659ca_1(double[] dest, int destOffset, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32) {
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = Math.fma(_t38, _t13, -(_t39 * _t12));
        double _t47 = Math.fma(_t37, _t12, -(_t38 * _t11));
        dest[destOffset] = Math.fma(_self02, _t11, Math.fma(_self00, _t37, _self01 * _t46));
        dest[destOffset + 1] = Math.fma(_self12, _t11, Math.fma(_self10, _t37, _self11 * _t46));
        dest[destOffset + 2] = Math.fma(_self22, _t11, Math.fma(_self20, _t37, _self21 * _t46));
        dest[destOffset + 3] = Math.fma(_self32, _t11, Math.fma(_self30, _t37, _self31 * _t46));
        dest[destOffset + 4] = Math.fma(_self02, _t13, Math.fma(_self00, _t39, _self01 * _t47));
        dest[destOffset + 5] = Math.fma(_self12, _t13, Math.fma(_self10, _t39, _self11 * _t47));
        return lookAlong_lh_s62f659ca_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t12, _t13, _t38, _t39, _t47, Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_lh_s62f659ca_2(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t12, double _t13, double _t38, double _t39, double _t47, double _t48) {
        dest[destOffset + 6] = Math.fma(_self22, _t13, Math.fma(_self20, _t39, _self21 * _t47));
        dest[destOffset + 7] = Math.fma(_self32, _t13, Math.fma(_self30, _t39, _self31 * _t47));
        dest[destOffset + 8] = Math.fma(_self02, _t12, Math.fma(_self00, _t38, _self01 * _t48));
        dest[destOffset + 9] = Math.fma(_self12, _t12, Math.fma(_self10, _t38, _self11 * _t48));
        dest[destOffset + 10] = Math.fma(_self22, _t12, Math.fma(_self20, _t38, _self21 * _t48));
        dest[destOffset + 11] = Math.fma(_self32, _t12, Math.fma(_self30, _t38, _self31 * _t48));
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 14] = _self23;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _t8 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t15, _t16, _t17;
        if (_t8 != 0.0) {
            _t15 = dirX * _t10;
            _t16 = dirZ * _t10;
            _t17 = dirY * _t10;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _t21 = -Math.fma(upZ, _t16, Math.fma(upX, _t15, upY * _t17));
        double _t22 = Math.fma(_t21, _t17, upY);
        double _t23 = Math.fma(_t21, _t15, upX);
        double _t24 = Math.fma(_t21, _t16, upZ);
        return lookAlong_rh_s90611efc_1(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], -_self02, -_self12, -_self22, -_self32, _t15, _t16, _t17, Math.fma(_t22, _t15, -(_t23 * _t17)), Math.fma(_t23, _t16, -(_t24 * _t15)), Math.fma(_t24, _t17, -(_t22 * _t16)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_s90611efc_1(double[] dest, int destOffset, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self03, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t15, double _t16, double _t17, double _t31, double _t32, double _t33) {
        double _t36 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        double _t37 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41, _t42, _t43;
        if (_t36 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0;
            _t42 = 0.0;
            _t43 = 0.0;
        }
        double _t50 = Math.fma(_t42, _t16, -(_t43 * _t17));
        double _t51 = Math.fma(_t43, _t15, -(_t41 * _t16));
        dest[destOffset] = Math.fma(_t0, _t15, Math.fma(_self00, _t41, _self01 * _t50));
        dest[destOffset + 1] = Math.fma(_t1, _t15, Math.fma(_self10, _t41, _self11 * _t50));
        dest[destOffset + 2] = Math.fma(_t2, _t15, Math.fma(_self20, _t41, _self21 * _t50));
        dest[destOffset + 3] = Math.fma(_t3, _t15, Math.fma(_self30, _t41, _self31 * _t50));
        dest[destOffset + 4] = Math.fma(_t0, _t17, Math.fma(_self00, _t42, _self01 * _t51));
        return lookAlong_rh_s90611efc_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self03, _self13, _self23, _self33, _t0, _t1, _t2, _t3, _t16, _t17, _t42, _t43, _t51, Math.fma(_t41, _t17, -(_t42 * _t15)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_rh_s90611efc_2(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self03, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t16, double _t17, double _t42, double _t43, double _t51, double _t52) {
        dest[destOffset + 5] = Math.fma(_t1, _t17, Math.fma(_self10, _t42, _self11 * _t51));
        dest[destOffset + 6] = Math.fma(_t2, _t17, Math.fma(_self20, _t42, _self21 * _t51));
        dest[destOffset + 7] = Math.fma(_t3, _t17, Math.fma(_self30, _t42, _self31 * _t51));
        dest[destOffset + 8] = Math.fma(_t0, _t16, Math.fma(_self00, _t43, _self01 * _t52));
        dest[destOffset + 9] = Math.fma(_t1, _t16, Math.fma(_self10, _t43, _self11 * _t52));
        dest[destOffset + 10] = Math.fma(_t2, _t16, Math.fma(_self20, _t43, _self21 * _t52));
        dest[destOffset + 11] = Math.fma(_t3, _t16, Math.fma(_self30, _t43, _self31 * _t52));
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 14] = _self23;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(_upz, _t12, Math.fma(_upx, _t11, _upy * _t13));
        double _t18 = Math.fma(_t17, _t11, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        return lookAlong_lh_sf22bfa3e_1(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t11, _t12, _t13, _t18, _t19, Math.fma(_t17, _t12, _upz), Math.fma(_t18, _t13, -(_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_sf22bfa3e_1(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20, double _t27) {
        double _t28 = Math.fma(_t19, _t12, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t11, -(_t18 * _t12));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = Math.fma(_t38, _t13, -(_t39 * _t12));
        dest[destOffset] = Math.fma(_self02, _t11, Math.fma(_self00, _t37, _self01 * _t46));
        dest[destOffset + 1] = Math.fma(_self12, _t11, Math.fma(_self10, _t37, _self11 * _t46));
        dest[destOffset + 2] = Math.fma(_self22, _t11, Math.fma(_self20, _t37, _self21 * _t46));
        dest[destOffset + 3] = Math.fma(_self32, _t11, Math.fma(_self30, _t37, _self31 * _t46));
        return lookAlong_lh_sf22bfa3e_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t12, _t13, _t38, _t39, Math.fma(_t37, _t12, -(_t38 * _t11)), Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_lh_sf22bfa3e_2(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t12, double _t13, double _t38, double _t39, double _t47, double _t48) {
        dest[destOffset + 4] = Math.fma(_self02, _t13, Math.fma(_self00, _t39, _self01 * _t47));
        dest[destOffset + 5] = Math.fma(_self12, _t13, Math.fma(_self10, _t39, _self11 * _t47));
        dest[destOffset + 6] = Math.fma(_self22, _t13, Math.fma(_self20, _t39, _self21 * _t47));
        dest[destOffset + 7] = Math.fma(_self32, _t13, Math.fma(_self30, _t39, _self31 * _t47));
        dest[destOffset + 8] = Math.fma(_self02, _t12, Math.fma(_self00, _t38, _self01 * _t48));
        dest[destOffset + 9] = Math.fma(_self12, _t12, Math.fma(_self10, _t38, _self11 * _t48));
        dest[destOffset + 10] = Math.fma(_self22, _t12, Math.fma(_self20, _t38, _self21 * _t48));
        dest[destOffset + 11] = Math.fma(_self32, _t12, Math.fma(_self30, _t38, _self31 * _t48));
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 14] = _self23;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _t8 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t15, _t16, _t17;
        if (_t8 != 0.0) {
            _t15 = _dirx * _t10;
            _t16 = _dirz * _t10;
            _t17 = _diry * _t10;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _t21 = -Math.fma(_upz, _t16, Math.fma(_upx, _t15, _upy * _t17));
        return lookAlong_rh_s497d3370_1(dest, destOffset, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], -_self02, -_self12, -_self22, -_self32, _t15, _t16, _t17, Math.fma(_t21, _t17, _upy), Math.fma(_t21, _t15, _upx), Math.fma(_t21, _t16, _upz));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_s497d3370_1(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self03, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t15, double _t16, double _t17, double _t22, double _t23, double _t24) {
        double _t31 = Math.fma(_t22, _t15, -(_t23 * _t17));
        double _t32 = Math.fma(_t23, _t16, -(_t24 * _t15));
        double _t33 = Math.fma(_t24, _t17, -(_t22 * _t16));
        double _t36 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        double _t37 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41, _t42, _t43;
        if (_t36 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0;
            _t42 = 0.0;
            _t43 = 0.0;
        }
        double _t50 = Math.fma(_t42, _t16, -(_t43 * _t17));
        dest[destOffset] = Math.fma(_t0, _t15, Math.fma(_self00, _t41, _self01 * _t50));
        dest[destOffset + 1] = Math.fma(_t1, _t15, Math.fma(_self10, _t41, _self11 * _t50));
        dest[destOffset + 2] = Math.fma(_t2, _t15, Math.fma(_self20, _t41, _self21 * _t50));
        return lookAlong_rh_s497d3370_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self03, _self13, _self23, _self33, _t0, _t1, _t2, _t3, _t15, _t16, _t17, _t41, _t42, _t43, _t50, Math.fma(_t43, _t15, -(_t41 * _t16)), Math.fma(_t41, _t17, -(_t42 * _t15)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private static double[] lookAlong_rh_s497d3370_2(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self03, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t15, double _t16, double _t17, double _t41, double _t42, double _t43, double _t50, double _t51, double _t52) {
        dest[destOffset + 3] = Math.fma(_t3, _t15, Math.fma(_self30, _t41, _self31 * _t50));
        dest[destOffset + 4] = Math.fma(_t0, _t17, Math.fma(_self00, _t42, _self01 * _t51));
        dest[destOffset + 5] = Math.fma(_t1, _t17, Math.fma(_self10, _t42, _self11 * _t51));
        dest[destOffset + 6] = Math.fma(_t2, _t17, Math.fma(_self20, _t42, _self21 * _t51));
        dest[destOffset + 7] = Math.fma(_t3, _t17, Math.fma(_self30, _t42, _self31 * _t51));
        dest[destOffset + 8] = Math.fma(_t0, _t16, Math.fma(_self00, _t43, _self01 * _t52));
        dest[destOffset + 9] = Math.fma(_t1, _t16, Math.fma(_self10, _t43, _self11 * _t52));
        dest[destOffset + 10] = Math.fma(_t2, _t16, Math.fma(_self20, _t43, _self21 * _t52));
        dest[destOffset + 11] = Math.fma(_t3, _t16, Math.fma(_self30, _t43, _self31 * _t52));
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 14] = _self23;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(upZ, _t12, Math.fma(upX, _t11, upY * _t13));
        double _t18 = Math.fma(_t17, _t11, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t12, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t11));
        double _t28 = Math.fma(_t19, _t12, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t11, -(_t18 * _t12));
        return lookAlong_lh_sad3c4201_1(dest, destOffset, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t11, _t12, _t13, _t27, _t28, _t29, Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_sad3c4201_1(double[] dest, int destOffset, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32) {
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = Math.fma(_t38, _t13, -(_t39 * _t12));
        double _t47 = Math.fma(_t37, _t12, -(_t38 * _t11));
        dest[destOffset] = Math.fma(_self02, _t11, Math.fma(_self00, _t37, _self01 * _t46));
        dest[destOffset + 1] = Math.fma(_self12, _t11, Math.fma(_self10, _t37, _self11 * _t46));
        dest[destOffset + 2] = Math.fma(_self22, _t11, Math.fma(_self20, _t37, _self21 * _t46));
        dest[destOffset + 3] = Math.fma(_self32, _t11, Math.fma(_self30, _t37, _self31 * _t46));
        dest[destOffset + 4] = Math.fma(_self02, _t13, Math.fma(_self00, _t39, _self01 * _t47));
        dest[destOffset + 5] = Math.fma(_self12, _t13, Math.fma(_self10, _t39, _self11 * _t47));
        return lookAlong_lh_s62f659ca_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t12, _t13, _t38, _t39, _t47, Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _t8 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t15, _t16, _t17;
        if (_t8 != 0.0) {
            _t15 = dirX * _t10;
            _t16 = dirZ * _t10;
            _t17 = dirY * _t10;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _t21 = -Math.fma(upZ, _t16, Math.fma(upX, _t15, upY * _t17));
        double _t22 = Math.fma(_t21, _t17, upY);
        double _t23 = Math.fma(_t21, _t15, upX);
        double _t24 = Math.fma(_t21, _t16, upZ);
        return lookAlong_rh_s63fca90f_1(dest, destOffset, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], -_self02, -_self12, -_self22, -_self32, _t15, _t16, _t17, Math.fma(_t22, _t15, -(_t23 * _t17)), Math.fma(_t23, _t16, -(_t24 * _t15)), Math.fma(_t24, _t17, -(_t22 * _t16)));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_s63fca90f_1(double[] dest, int destOffset, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self03, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t15, double _t16, double _t17, double _t31, double _t32, double _t33) {
        double _t36 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        double _t37 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41, _t42, _t43;
        if (_t36 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0;
            _t42 = 0.0;
            _t43 = 0.0;
        }
        double _t50 = Math.fma(_t42, _t16, -(_t43 * _t17));
        double _t51 = Math.fma(_t43, _t15, -(_t41 * _t16));
        dest[destOffset] = Math.fma(_t0, _t15, Math.fma(_self00, _t41, _self01 * _t50));
        dest[destOffset + 1] = Math.fma(_t1, _t15, Math.fma(_self10, _t41, _self11 * _t50));
        dest[destOffset + 2] = Math.fma(_t2, _t15, Math.fma(_self20, _t41, _self21 * _t50));
        dest[destOffset + 3] = Math.fma(_t3, _t15, Math.fma(_self30, _t41, _self31 * _t50));
        dest[destOffset + 4] = Math.fma(_t0, _t17, Math.fma(_self00, _t42, _self01 * _t51));
        return lookAlong_rh_s90611efc_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self03, _self13, _self23, _self33, _t0, _t1, _t2, _t3, _t16, _t17, _t42, _t43, _t51, Math.fma(_t41, _t17, -(_t42 * _t15)));
    }

    public static double[] lookAlong_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(_upz, _t12, Math.fma(_upx, _t11, _upy * _t13));
        double _t18 = Math.fma(_t17, _t11, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        return lookAlong_lh_s94d34645_1(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t11, _t12, _t13, _t18, _t19, Math.fma(_t17, _t12, _upz), Math.fma(_t18, _t13, -(_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_lh_s94d34645_1(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20, double _t27) {
        double _t28 = Math.fma(_t19, _t12, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t11, -(_t18 * _t12));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        double _t46 = Math.fma(_t38, _t13, -(_t39 * _t12));
        dest[destOffset] = Math.fma(_self02, _t11, Math.fma(_self00, _t37, _self01 * _t46));
        dest[destOffset + 1] = Math.fma(_self12, _t11, Math.fma(_self10, _t37, _self11 * _t46));
        dest[destOffset + 2] = Math.fma(_self22, _t11, Math.fma(_self20, _t37, _self21 * _t46));
        dest[destOffset + 3] = Math.fma(_self32, _t11, Math.fma(_self30, _t37, _self31 * _t46));
        return lookAlong_lh_sf22bfa3e_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t12, _t13, _t38, _t39, Math.fma(_t37, _t12, -(_t38 * _t11)), Math.fma(_t39, _t11, -(_t37 * _t13)));
    }

    public static double[] lookAlong_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _t8 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t15, _t16, _t17;
        if (_t8 != 0.0) {
            _t15 = _dirx * _t10;
            _t16 = _dirz * _t10;
            _t17 = _diry * _t10;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        double _t21 = -Math.fma(_upz, _t16, Math.fma(_upx, _t15, _upy * _t17));
        return lookAlong_rh_sa2728713_1(dest, destOffset, epsilon, _upx, _upy, _upz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], -_self02, -_self12, -_self22, -_self32, _t15, _t16, _t17, Math.fma(_t21, _t17, _upy), Math.fma(_t21, _t15, _upx), Math.fma(_t21, _t16, _upz));
    }

    /** Piece 2 of {@code lookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_rh_sa2728713_1(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self03, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t15, double _t16, double _t17, double _t22, double _t23, double _t24) {
        double _t31 = Math.fma(_t22, _t15, -(_t23 * _t17));
        double _t32 = Math.fma(_t23, _t16, -(_t24 * _t15));
        double _t33 = Math.fma(_t24, _t17, -(_t22 * _t16));
        double _t36 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        double _t37 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41, _t42, _t43;
        if (_t36 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0;
            _t42 = 0.0;
            _t43 = 0.0;
        }
        double _t50 = Math.fma(_t42, _t16, -(_t43 * _t17));
        dest[destOffset] = Math.fma(_t0, _t15, Math.fma(_self00, _t41, _self01 * _t50));
        dest[destOffset + 1] = Math.fma(_t1, _t15, Math.fma(_self10, _t41, _self11 * _t50));
        dest[destOffset + 2] = Math.fma(_t2, _t15, Math.fma(_self20, _t41, _self21 * _t50));
        return lookAlong_rh_s497d3370_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self03, _self13, _self23, _self33, _t0, _t1, _t2, _t3, _t15, _t16, _t17, _t41, _t42, _t43, _t50, Math.fma(_t43, _t15, -(_t41 * _t16)), Math.fma(_t41, _t17, -(_t42 * _t15)));
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
        return lookAt_lh_sd1cc022f_3(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t14, _t15, _t16, Math.fma(_t24, _t16, -(_t25 * _t14)), Math.fma(_t25, _t15, -(_t26 * _t16)), Math.fma(_t26, _t14, -(_t24 * _t15)));
    }

    /**
     * Part 1 of {@code lookAt_lh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static double lookAt_lh_sd1cc022f_1(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _t14, double _t16, double _t43, double _t45, double _t54, double _t55, double _t56) {
        dest[destOffset] = Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54));
        dest[destOffset + 1] = Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54));
        dest[destOffset + 2] = Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54));
        dest[destOffset + 3] = Math.fma(_self32, _t14, Math.fma(_self30, _t43, _self31 * _t54));
        dest[destOffset + 4] = Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55));
        dest[destOffset + 5] = Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55));
        dest[destOffset + 6] = Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55));
        dest[destOffset + 7] = Math.fma(_self32, _t16, Math.fma(_self30, _t45, _self31 * _t55));
        return Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55));
    }

    /**
     * Part 2 of {@code lookAt_lh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static double[] lookAt_lh_sd1cc022f_2(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t15, double _t22, double _t44, double _t56, double _t58, double _t60) {
        dest[destOffset + 8] = Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56));
        dest[destOffset + 9] = Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56));
        dest[destOffset + 10] = Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56));
        dest[destOffset + 11] = Math.fma(_self32, _t15, Math.fma(_self30, _t44, _self31 * _t56));
        dest[destOffset + 12] = Math.fma(_self00, -_t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03)));
        dest[destOffset + 13] = Math.fma(_self10, -_t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13)));
        dest[destOffset + 14] = Math.fma(_self20, -_t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23)));
        dest[destOffset + 15] = Math.fma(_self30, -_t58, Math.fma(-_self31, _t60, Math.fma(-_self32, _t22, _self33)));
        return dest;
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_sd1cc022f_3(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35) {
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
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        return lookAt_lh_sd1cc022f_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t15, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t44, _t56, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)), lookAt_lh_sd1cc022f_1(dest, destOffset, eyeX, eyeY, eyeZ, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _t14, _t16, _t43, _t45, Math.fma(_t44, _t16, -(_t45 * _t15)), Math.fma(_t43, _t15, -(_t44 * _t14)), _t56));
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _self32 = src[srcOffset + 11];
        double _t4 = centerZ - eyeZ;
        double _t5 = centerX - eyeX;
        double _t6 = centerY - eyeY;
        double _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18, _t19, _t20;
        if (_t13 != 0.0) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0;
            _t19 = 0.0;
            _t20 = 0.0;
        }
        double _t27 = -Math.fma(upZ, _t19, Math.fma(upX, _t18, upY * _t20));
        double _t28 = Math.fma(_t27, _t20, upY);
        double _t29 = Math.fma(_t27, _t18, upX);
        double _t30 = Math.fma(_t27, _t19, upZ);
        return lookAt_rh_s32404a0d_3(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], _self32, src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], -_self32, _t18, _t19, _t20, Math.fma(_t28, _t18, -(_t29 * _t20)), Math.fma(_t29, _t19, -(_t30 * _t18)), Math.fma(_t30, _t20, -(_t28 * _t19)));
    }

    /**
     * Part 1 of {@code lookAt_rh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static double lookAt_rh_s32404a0d_1(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _t0, double _t1, double _t2, double _t3, double _t18, double _t20, double _t19, double _t47, double _t48, double _t49, double _t58, double _t59, double _t60) {
        dest[destOffset] = Math.fma(_t0, _t18, Math.fma(_self00, _t47, _self01 * _t58));
        dest[destOffset + 1] = Math.fma(_t1, _t18, Math.fma(_self10, _t47, _self11 * _t58));
        dest[destOffset + 2] = Math.fma(_t2, _t18, Math.fma(_self20, _t47, _self21 * _t58));
        dest[destOffset + 3] = Math.fma(_t3, _t18, Math.fma(_self30, _t47, _self31 * _t58));
        dest[destOffset + 4] = Math.fma(_t0, _t20, Math.fma(_self00, _t48, _self01 * _t59));
        dest[destOffset + 5] = Math.fma(_t1, _t20, Math.fma(_self10, _t48, _self11 * _t59));
        dest[destOffset + 6] = Math.fma(_t2, _t20, Math.fma(_self20, _t48, _self21 * _t59));
        dest[destOffset + 7] = Math.fma(_t3, _t20, Math.fma(_self30, _t48, _self31 * _t59));
        dest[destOffset + 8] = Math.fma(_t0, _t19, Math.fma(_self00, _t49, _self01 * _t60));
        dest[destOffset + 9] = Math.fma(_t1, _t19, Math.fma(_self10, _t49, _self11 * _t60));
        dest[destOffset + 10] = Math.fma(_t2, _t19, Math.fma(_self20, _t49, _self21 * _t60));
        return Math.fma(eyeZ, _t60, Math.fma(eyeX, _t58, eyeY * _t59));
    }

    /**
     * Part 2 of {@code lookAt_rh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static double[] lookAt_rh_s32404a0d_2(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t3, double _t19, double _t26, double _t49, double _t60, double _t62, double _t64) {
        dest[destOffset + 11] = Math.fma(_t3, _t19, Math.fma(_self30, _t49, _self31 * _t60));
        dest[destOffset + 12] = Math.fma(_self00, -_t62, Math.fma(-_self01, _t64, Math.fma(_self02, _t26, _self03)));
        dest[destOffset + 13] = Math.fma(_self10, -_t62, Math.fma(-_self11, _t64, Math.fma(_self12, _t26, _self13)));
        dest[destOffset + 14] = Math.fma(_self20, -_t62, Math.fma(-_self21, _t64, Math.fma(_self22, _t26, _self23)));
        dest[destOffset + 15] = Math.fma(_self30, -_t62, Math.fma(-_self31, _t64, Math.fma(_self32, _t26, _self33)));
        return dest;
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_s32404a0d_3(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t3, double _t18, double _t19, double _t20, double _t37, double _t38, double _t39) {
        double _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        double _t43 = (1.0 / java.lang.Math.sqrt(_t42));
        double _t47, _t48, _t49;
        if (_t42 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        double _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        return lookAt_rh_s32404a0d_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t3, _t19, Math.fma(eyeZ, _t19, Math.fma(eyeX, _t18, eyeY * _t20)), _t49, _t60, Math.fma(eyeZ, _t49, Math.fma(eyeX, _t47, eyeY * _t48)), lookAt_rh_s32404a0d_1(dest, destOffset, eyeX, eyeY, eyeZ, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, (-_self02), (-_self12), (-_self22), _t3, _t18, _t20, _t19, _t47, _t48, _t49, Math.fma(_t48, _t19, -(_t49 * _t20)), Math.fma(_t49, _t18, -(_t47 * _t19)), _t60));
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
        return lookAt_lh_s2812e005_1(dest, destOffset, _eyex, _eyey, _eyez, up[upOffset], up[upOffset + 1], up[upOffset + 2], src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_s2812e005_1(double[] dest, int destOffset, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t14, double _t15, double _t16, double _t22) {
        double _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        double _t24 = Math.fma(_t23, _t14, _upx);
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
        return lookAt_lh_s2812e005_2(dest, destOffset, _eyex, _eyey, _eyez, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t14, _t15, _t16, _t22, _t43, _t44, _t45, Math.fma(_t44, _t16, -(_t45 * _t15)), Math.fma(_t43, _t15, -(_t44 * _t14)), Math.fma(_t45, _t14, -(_t43 * _t16)));
    }

    /**
     * Piece 3 of {@code lookAt_lh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static double[] lookAt_lh_s2812e005_2(double[] dest, int destOffset, double _eyex, double _eyey, double _eyez, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56) {
        dest[destOffset] = Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54));
        dest[destOffset + 1] = Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54));
        dest[destOffset + 2] = Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54));
        dest[destOffset + 3] = Math.fma(_self32, _t14, Math.fma(_self30, _t43, _self31 * _t54));
        dest[destOffset + 4] = Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55));
        dest[destOffset + 5] = Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55));
        dest[destOffset + 6] = Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55));
        dest[destOffset + 7] = Math.fma(_self32, _t16, Math.fma(_self30, _t45, _self31 * _t55));
        dest[destOffset + 8] = Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56));
        return lookAt_lh_s2812e005_3(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t15, _t22, _t44, _t56, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)), Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
    }

    /**
     * Piece 4 of {@code lookAt_lh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static double[] lookAt_lh_s2812e005_3(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t15, double _t22, double _t44, double _t56, double _t58, double _t60) {
        dest[destOffset + 9] = Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56));
        dest[destOffset + 10] = Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56));
        dest[destOffset + 11] = Math.fma(_self32, _t15, Math.fma(_self30, _t44, _self31 * _t56));
        dest[destOffset + 12] = Math.fma(_self00, -_t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03)));
        dest[destOffset + 13] = Math.fma(_self10, -_t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13)));
        dest[destOffset + 14] = Math.fma(_self20, -_t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23)));
        dest[destOffset + 15] = Math.fma(_self30, -_t58, Math.fma(-_self31, _t60, Math.fma(-_self32, _t22, _self33)));
        return dest;
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _t4 = center[centerOffset + 2] - _eyez;
        double _t5 = center[centerOffset] - _eyex;
        double _t6 = center[centerOffset + 1] - _eyey;
        double _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18, _t19, _t20;
        if (_t13 != 0.0) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0;
            _t19 = 0.0;
            _t20 = 0.0;
        }
        return lookAt_rh_sdbe11fb_1(dest, destOffset, _eyex, _eyey, _eyez, up[upOffset], up[upOffset + 1], up[upOffset + 2], src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], _self02, _self12, _self22, _self32, src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], -_self02, -_self12, -_self22, -_self32, _t18, _t19, _t20);
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_sdbe11fb_1(double[] dest, int destOffset, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t18, double _t19, double _t20) {
        double _t27 = -Math.fma(_upz, _t19, Math.fma(_upx, _t18, _upy * _t20));
        double _t28 = Math.fma(_t27, _t20, _upy);
        double _t29 = Math.fma(_t27, _t18, _upx);
        double _t30 = Math.fma(_t27, _t19, _upz);
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t20));
        double _t38 = Math.fma(_t29, _t19, -(_t30 * _t18));
        double _t39 = Math.fma(_t30, _t20, -(_t28 * _t19));
        double _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        double _t43 = (1.0 / java.lang.Math.sqrt(_t42));
        double _t47, _t48, _t49;
        if (_t42 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        return lookAt_rh_sdbe11fb_2(dest, destOffset, _eyex, _eyey, _eyez, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t0, _t1, _t2, _t3, _t18, _t19, _t20, Math.fma(_eyez, _t19, Math.fma(_eyex, _t18, _eyey * _t20)), _t47, _t48, _t49, Math.fma(_t48, _t19, -(_t49 * _t20)), Math.fma(_t49, _t18, -(_t47 * _t19)));
    }

    /**
     * Piece 3 of {@code lookAt_rh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static double[] lookAt_rh_sdbe11fb_2(double[] dest, int destOffset, double _eyex, double _eyey, double _eyez, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t18, double _t19, double _t20, double _t26, double _t47, double _t48, double _t49, double _t58, double _t59) {
        double _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        dest[destOffset] = Math.fma(_t0, _t18, Math.fma(_self00, _t47, _self01 * _t58));
        dest[destOffset + 1] = Math.fma(_t1, _t18, Math.fma(_self10, _t47, _self11 * _t58));
        dest[destOffset + 2] = Math.fma(_t2, _t18, Math.fma(_self20, _t47, _self21 * _t58));
        dest[destOffset + 3] = Math.fma(_t3, _t18, Math.fma(_self30, _t47, _self31 * _t58));
        dest[destOffset + 4] = Math.fma(_t0, _t20, Math.fma(_self00, _t48, _self01 * _t59));
        dest[destOffset + 5] = Math.fma(_t1, _t20, Math.fma(_self10, _t48, _self11 * _t59));
        dest[destOffset + 6] = Math.fma(_t2, _t20, Math.fma(_self20, _t48, _self21 * _t59));
        dest[destOffset + 7] = Math.fma(_t3, _t20, Math.fma(_self30, _t48, _self31 * _t59));
        return lookAt_rh_sdbe11fb_3(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t0, _t1, _t2, _t3, _t19, _t26, _t49, _t60, Math.fma(_eyez, _t49, Math.fma(_eyex, _t47, _eyey * _t48)), Math.fma(_eyez, _t60, Math.fma(_eyex, _t58, _eyey * _t59)));
    }

    /**
     * Piece 4 of {@code lookAt_rh}, split to fit the inline budget. Shared by 2 identical private
     * paths of {@code lookAt}; reached only through it.
     */
    private static double[] lookAt_rh_sdbe11fb_3(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t19, double _t26, double _t49, double _t60, double _t62, double _t64) {
        dest[destOffset + 8] = Math.fma(_t0, _t19, Math.fma(_self00, _t49, _self01 * _t60));
        dest[destOffset + 9] = Math.fma(_t1, _t19, Math.fma(_self10, _t49, _self11 * _t60));
        dest[destOffset + 10] = Math.fma(_t2, _t19, Math.fma(_self20, _t49, _self21 * _t60));
        dest[destOffset + 11] = Math.fma(_t3, _t19, Math.fma(_self30, _t49, _self31 * _t60));
        dest[destOffset + 12] = Math.fma(_self00, -_t62, Math.fma(-_self01, _t64, Math.fma(_self02, _t26, _self03)));
        dest[destOffset + 13] = Math.fma(_self10, -_t62, Math.fma(-_self11, _t64, Math.fma(_self12, _t26, _self13)));
        dest[destOffset + 14] = Math.fma(_self20, -_t62, Math.fma(-_self21, _t64, Math.fma(_self22, _t26, _self23)));
        dest[destOffset + 15] = Math.fma(_self30, -_t62, Math.fma(-_self31, _t64, Math.fma(_self32, _t26, _self33)));
        return dest;
    }

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
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
        return lookAt_lh_se3a360cc_3(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t14, _t15, _t16, Math.fma(_t24, _t16, -(_t25 * _t14)), Math.fma(_t25, _t15, -(_t26 * _t16)), Math.fma(_t26, _t14, -(_t24 * _t15)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_se3a360cc_3(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35) {
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        return lookAt_lh_sd1cc022f_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t15, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t44, _t56, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)), lookAt_lh_sd1cc022f_1(dest, destOffset, eyeX, eyeY, eyeZ, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _t14, _t16, _t43, _t45, Math.fma(_t44, _t16, -(_t45 * _t15)), Math.fma(_t43, _t15, -(_t44 * _t14)), _t56));
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
        double _self32 = src[srcOffset + 11];
        double _t4 = centerZ - eyeZ;
        double _t5 = centerX - eyeX;
        double _t6 = centerY - eyeY;
        double _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18, _t19, _t20;
        if (_t13 != 0.0) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0;
            _t19 = 0.0;
            _t20 = 0.0;
        }
        double _t27 = -Math.fma(upZ, _t19, Math.fma(upX, _t18, upY * _t20));
        double _t28 = Math.fma(_t27, _t20, upY);
        double _t29 = Math.fma(_t27, _t18, upX);
        double _t30 = Math.fma(_t27, _t19, upZ);
        return lookAt_rh_sdccca1ae_3(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], _self32, src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], -_self32, _t18, _t19, _t20, Math.fma(_t28, _t18, -(_t29 * _t20)), Math.fma(_t29, _t19, -(_t30 * _t18)), Math.fma(_t30, _t20, -(_t28 * _t19)));
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_sdccca1ae_3(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double epsilon, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t3, double _t18, double _t19, double _t20, double _t37, double _t38, double _t39) {
        double _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        double _t43 = (1.0 / java.lang.Math.sqrt(_t42));
        double _t47, _t48, _t49;
        if (_t42 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        double _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        return lookAt_rh_s32404a0d_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t3, _t19, Math.fma(eyeZ, _t19, Math.fma(eyeX, _t18, eyeY * _t20)), _t49, _t60, Math.fma(eyeZ, _t49, Math.fma(eyeX, _t47, eyeY * _t48)), lookAt_rh_s32404a0d_1(dest, destOffset, eyeX, eyeY, eyeZ, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, (-_self02), (-_self12), (-_self22), _t3, _t18, _t20, _t19, _t47, _t48, _t49, Math.fma(_t48, _t19, -(_t49 * _t20)), Math.fma(_t49, _t18, -(_t47 * _t19)), _t60));
    }

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset, double epsilon) {
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
        return lookAt_lh_sbc945876_1(dest, destOffset, epsilon, _eyex, _eyey, _eyez, up[upOffset], up[upOffset + 1], up[upOffset + 2], src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_sbc945876_1(double[] dest, int destOffset, double epsilon, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t14, double _t15, double _t16, double _t22) {
        double _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        double _t24 = Math.fma(_t23, _t14, _upx);
        double _t25 = Math.fma(_t23, _t16, _upy);
        double _t26 = Math.fma(_t23, _t15, _upz);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        return lookAt_lh_s2812e005_2(dest, destOffset, _eyex, _eyey, _eyez, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t14, _t15, _t16, _t22, _t43, _t44, _t45, Math.fma(_t44, _t16, -(_t45 * _t15)), Math.fma(_t43, _t15, -(_t44 * _t14)), Math.fma(_t45, _t14, -(_t43 * _t16)));
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset, double epsilon) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _t4 = center[centerOffset + 2] - _eyez;
        double _t5 = center[centerOffset] - _eyex;
        double _t6 = center[centerOffset + 1] - _eyey;
        double _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18, _t19, _t20;
        if (_t13 != 0.0) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0;
            _t19 = 0.0;
            _t20 = 0.0;
        }
        return lookAt_rh_sa5dbbe98_1(dest, destOffset, epsilon, _eyex, _eyey, _eyez, up[upOffset], up[upOffset + 1], up[upOffset + 2], src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], _self02, _self12, _self22, _self32, src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], -_self02, -_self12, -_self22, -_self32, _t18, _t19, _t20);
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_sa5dbbe98_1(double[] dest, int destOffset, double epsilon, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t0, double _t1, double _t2, double _t3, double _t18, double _t19, double _t20) {
        double _t27 = -Math.fma(_upz, _t19, Math.fma(_upx, _t18, _upy * _t20));
        double _t28 = Math.fma(_t27, _t20, _upy);
        double _t29 = Math.fma(_t27, _t18, _upx);
        double _t30 = Math.fma(_t27, _t19, _upz);
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t20));
        double _t38 = Math.fma(_t29, _t19, -(_t30 * _t18));
        double _t39 = Math.fma(_t30, _t20, -(_t28 * _t19));
        double _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        double _t43 = (1.0 / java.lang.Math.sqrt(_t42));
        double _t47, _t48, _t49;
        if (_t42 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0;
            _t48 = 0.0;
            _t49 = 0.0;
        }
        return lookAt_rh_sdbe11fb_2(dest, destOffset, _eyex, _eyey, _eyez, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t0, _t1, _t2, _t3, _t18, _t19, _t20, Math.fma(_eyez, _t19, Math.fma(_eyex, _t18, _eyey * _t20)), _t47, _t48, _t49, Math.fma(_t48, _t19, -(_t49 * _t20)), Math.fma(_t49, _t18, -(_t47 * _t19)));
    }

    public static double[] makeFrustum_no_lh(double[] dest, int destOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _sp0 = zNear + zNear;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zNear - zFar);
        double _t4 = 2.0 * (zNear == Double.POSITIVE_INFINITY ? zFar : zNear);
        if (zFar == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0;
                dest[destOffset + 14] = zFar + zFar;
            } else {
                dest[destOffset + 10] = -(zFar + zNear) * _t2_inv;
                dest[destOffset + 14] = _sp0 * zFar * _t2_inv;
            }
        }
        dest[destOffset] = _t4 * _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t4 * _t1_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = -(left + right) * _t0_inv;
        dest[destOffset + 9] = -(bottom + top) * _t1_inv;
        dest[destOffset + 11] = 1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makeFrustum_no_rh(double[] dest, int destOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _sp0 = zNear + zNear;
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zNear - zFar);
        double _t4 = 2.0 * (zNear == Double.POSITIVE_INFINITY ? zFar : zNear);
        if (zFar == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0;
                dest[destOffset + 14] = zFar + zFar;
            } else {
                dest[destOffset + 10] = (zFar + zNear) * _t2_inv;
                dest[destOffset + 14] = _sp0 * zFar * _t2_inv;
            }
        }
        dest[destOffset] = _t4 * _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t4 * _t1_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = (left + right) * _t0_inv;
        dest[destOffset + 9] = (bottom + top) * _t1_inv;
        dest[destOffset + 11] = -1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makeFrustum_no(double[] dest, int destOffset, double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makeFrustum_no_lh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Double4x4OpsKernelsArray.makeFrustum_no_rh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static double[] makeFrustum_zo_lh(double[] dest, int destOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _sp0 = zFar / (zNear - zFar);
        double _t4 = 2.0 * (zNear == Double.POSITIVE_INFINITY ? zFar : zNear);
        if (zFar == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -zNear;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = zFar;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = _sp0 * zNear;
            }
        }
        dest[destOffset] = _t4 * _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t4 * _t1_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = -(left + right) * _t0_inv;
        dest[destOffset + 9] = -(bottom + top) * _t1_inv;
        dest[destOffset + 11] = 1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makeFrustum_zo_rh(double[] dest, int destOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _sp0 = zFar / (zNear - zFar);
        double _t4 = 2.0 * (zNear == Double.POSITIVE_INFINITY ? zFar : zNear);
        if (zFar == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -zNear;
        } else {
            if (zNear == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = zFar;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = _sp0 * zNear;
            }
        }
        dest[destOffset] = _t4 * _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t4 * _t1_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = (left + right) * _t0_inv;
        dest[destOffset + 9] = (bottom + top) * _t1_inv;
        dest[destOffset + 11] = -1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makeFrustum_zo(double[] dest, int destOffset, double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makeFrustum_zo_lh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Double4x4OpsKernelsArray.makeFrustum_zo_rh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
        }
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
        dest[destOffset + 1] = _t49;
        dest[destOffset + 2] = _t15;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t41;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t16;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t42;
        dest[destOffset + 9] = _t51;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41));
        dest[destOffset + 13] = -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50));
        dest[destOffset + 14] = -Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16));
        dest[destOffset + 15] = 1.0;
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
        dest[destOffset + 1] = _t49;
        dest[destOffset + 2] = -_t15;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t41;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = -_t16;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t42;
        dest[destOffset + 9] = _t51;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41));
        dest[destOffset + 13] = -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50));
        dest[destOffset + 14] = Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16));
        dest[destOffset + 15] = 1.0;
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

    /**
     * Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget. Shared by the identical
     * private paths of {@code makeLookAt} and {@code makePerspectiveOffCenterRectangleView};
     * reached only through them.
     */
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
        dest[destOffset + 1] = _t49;
        dest[destOffset + 2] = _t15;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t41;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t16;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t42;
        dest[destOffset + 9] = _t51;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41));
        return makeLookAt_lh_scc2c3ffe_2(dest, destOffset, _eyex, _eyey, _eyez, _t14, _t15, _t16, _t49, _t50, _t51);
    }

    /**
     * Piece 3 of {@code makeLookAt_lh}, split to fit the inline budget. Shared by the identical
     * private paths of {@code makeLookAt} and {@code makePerspectiveOffCenterRectangleView};
     * reached only through them.
     */
    private static double[] makeLookAt_lh_scc2c3ffe_2(double[] dest, int destOffset, double _eyex, double _eyey, double _eyez, double _t14, double _t15, double _t16, double _t49, double _t50, double _t51) {
        dest[destOffset + 13] = -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50));
        dest[destOffset + 14] = -Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16));
        dest[destOffset + 15] = 1.0;
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

    /**
     * Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget. Shared by the identical
     * private paths of {@code makeLookAt} and {@code makePerspectiveOffCenterRectangleView};
     * reached only through them.
     */
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
        dest[destOffset + 1] = _t49;
        dest[destOffset + 2] = -_t15;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t41;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = -_t16;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t42;
        dest[destOffset + 9] = _t51;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41));
        return makeLookAt_rh_s7db9b09c_2(dest, destOffset, _eyex, _eyey, _eyez, _t14, _t15, _t16, _t49, _t50, _t51);
    }

    /**
     * Piece 3 of {@code makeLookAt_rh}, split to fit the inline budget. Shared by the identical
     * private paths of {@code makeLookAt} and {@code makePerspectiveOffCenterRectangleView};
     * reached only through them.
     */
    private static double[] makeLookAt_rh_s7db9b09c_2(double[] dest, int destOffset, double _eyex, double _eyey, double _eyez, double _t14, double _t15, double _t16, double _t49, double _t50, double _t51) {
        dest[destOffset + 13] = -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50));
        dest[destOffset + 14] = Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16));
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeLookAt_lh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
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
        return makeLookAt_lh_s62625ee7_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makeLookAt_lh_s62625ee7_1(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double epsilon, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
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
        dest[destOffset + 1] = _t49;
        dest[destOffset + 2] = _t15;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t41;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t16;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t42;
        dest[destOffset + 9] = _t51;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41));
        dest[destOffset + 13] = -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50));
        dest[destOffset + 14] = -Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16));
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeLookAt_rh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, double epsilon) {
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
        return makeLookAt_rh_sfb5497e1_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makeLookAt_rh_sfb5497e1_1(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double epsilon, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
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
        dest[destOffset + 1] = _t49;
        dest[destOffset + 2] = -_t15;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t41;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = -_t16;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t42;
        dest[destOffset + 9] = _t51;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41));
        dest[destOffset + 13] = -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50));
        dest[destOffset + 14] = Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16));
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeLookAt_lh(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset, double epsilon) {
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
        return makeLookAt_lh_sc68fdd85_1(dest, destOffset, epsilon, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t16, -(_t22 * _t15)), Math.fma(_t22, _t14, -(_t23 * _t16)), Math.fma(_t23, _t15, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makeLookAt_lh_sc68fdd85_1(double[] dest, int destOffset, double epsilon, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
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
        dest[destOffset + 1] = _t49;
        dest[destOffset + 2] = _t15;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t41;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t16;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t42;
        dest[destOffset + 9] = _t51;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41));
        return makeLookAt_lh_scc2c3ffe_2(dest, destOffset, _eyex, _eyey, _eyez, _t14, _t15, _t16, _t49, _t50, _t51);
    }

    public static double[] makeLookAt_rh(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset, double epsilon) {
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
        return makeLookAt_rh_s95111caf_1(dest, destOffset, epsilon, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t15, -(_t22 * _t16)), Math.fma(_t22, _t14, -(_t23 * _t15)), Math.fma(_t23, _t16, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makeLookAt_rh_s95111caf_1(double[] dest, int destOffset, double epsilon, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
        double _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        double _t36 = (1.0 / java.lang.Math.sqrt(_t35));
        double _t40, _t41, _t42;
        if (_t35 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
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
        dest[destOffset + 1] = _t49;
        dest[destOffset + 2] = -_t15;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t41;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = -_t16;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t42;
        dest[destOffset + 9] = _t51;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41));
        return makeLookAt_rh_s7db9b09c_2(dest, destOffset, _eyex, _eyey, _eyez, _t14, _t15, _t16, _t49, _t50, _t51);
    }

    public static double[] makeOrtho_no_lh(double[] dest, int destOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zFar - zNear);
        dest[destOffset] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = _t2_inv + _t2_inv;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(left + right) * _t0_inv;
        dest[destOffset + 13] = -(bottom + top) * _t1_inv;
        dest[destOffset + 14] = -(zFar + zNear) * _t2_inv;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeOrtho_no_rh(double[] dest, int destOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zFar - zNear);
        dest[destOffset] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = -2.0 * _t2_inv;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(left + right) * _t0_inv;
        dest[destOffset + 13] = -(bottom + top) * _t1_inv;
        dest[destOffset + 14] = -(zFar + zNear) * _t2_inv;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeOrtho_no(double[] dest, int destOffset, double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makeOrtho_no_lh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Double4x4OpsKernelsArray.makeOrtho_no_rh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static double[] makeOrtho_zo_lh(double[] dest, int destOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zFar - zNear);
        dest[destOffset] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = _t2_inv;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(left + right) * _t0_inv;
        dest[destOffset + 13] = -(bottom + top) * _t1_inv;
        dest[destOffset + 14] = -(zNear * _t2_inv);
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeOrtho_zo_rh(double[] dest, int destOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        double _t2_inv = 1.0 / (zFar - zNear);
        dest[destOffset] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = -_t2_inv;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(left + right) * _t0_inv;
        dest[destOffset + 13] = -(bottom + top) * _t1_inv;
        dest[destOffset + 14] = -(zNear * _t2_inv);
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeOrtho_zo(double[] dest, int destOffset, double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makeOrtho_zo_lh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Double4x4OpsKernelsArray.makeOrtho_zo_rh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static double[] makeOrtho2D_no_lh(double[] dest, int destOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dest[destOffset] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = 1.0;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(left + right) * _t0_inv;
        dest[destOffset + 13] = -(bottom + top) * _t1_inv;
        dest[destOffset + 14] = 0.0;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeOrtho2D_no_rh(double[] dest, int destOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dest[destOffset] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = -1.0;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(left + right) * _t0_inv;
        dest[destOffset + 13] = -(bottom + top) * _t1_inv;
        dest[destOffset + 14] = 0.0;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeOrtho2D_no(double[] dest, int destOffset, double left, double right, double bottom, double top, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makeOrtho2D_no_lh(dest, destOffset, left, right, bottom, top); }
            default -> { return Double4x4OpsKernelsArray.makeOrtho2D_no_rh(dest, destOffset, left, right, bottom, top); }
        }
    }

    public static double[] makeOrtho2D_zo_lh(double[] dest, int destOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dest[destOffset] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = 0.5;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(left + right) * _t0_inv;
        dest[destOffset + 13] = -(bottom + top) * _t1_inv;
        dest[destOffset + 14] = 0.5;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeOrtho2D_zo_rh(double[] dest, int destOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dest[destOffset] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = -0.5;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(left + right) * _t0_inv;
        dest[destOffset + 13] = -(bottom + top) * _t1_inv;
        dest[destOffset + 14] = 0.5;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeOrtho2D_zo(double[] dest, int destOffset, double left, double right, double bottom, double top, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makeOrtho2D_zo_lh(dest, destOffset, left, right, bottom, top); }
            default -> { return Double4x4OpsKernelsArray.makeOrtho2D_zo_rh(dest, destOffset, left, right, bottom, top); }
        }
    }

    public static double[] makePerspective_no_lh(double[] dest, int destOffset, double fovy, double aspect, double near, double far) {
        double _sp0 = near + near;
        double _t1_inv = 1.0 / (near - far);
        double _t2 = Math.tan(0.5 * fovy);
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = -(far + near) * _t1_inv;
                dest[destOffset + 14] = _sp0 * far * _t1_inv;
            }
        }
        dest[destOffset] = 1.0 / (aspect * _t2);
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = 1.0 / _t2;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 11] = 1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspective_no_rh(double[] dest, int destOffset, double fovy, double aspect, double near, double far) {
        double _sp0 = near + near;
        double _t1_inv = 1.0 / (near - far);
        double _t2 = Math.tan(0.5 * fovy);
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = (far + near) * _t1_inv;
                dest[destOffset + 14] = _sp0 * far * _t1_inv;
            }
        }
        dest[destOffset] = 1.0 / (aspect * _t2);
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = 1.0 / _t2;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 11] = -1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspective_no(double[] dest, int destOffset, double fovy, double aspect, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makePerspective_no_lh(dest, destOffset, fovy, aspect, near, far); }
            default -> { return Double4x4OpsKernelsArray.makePerspective_no_rh(dest, destOffset, fovy, aspect, near, far); }
        }
    }

    public static double[] makePerspective_zo_lh(double[] dest, int destOffset, double fovy, double aspect, double near, double far) {
        double _sp0 = far / (near - far);
        double _t2 = Math.tan(0.5 * fovy);
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset] = 1.0 / (aspect * _t2);
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = 1.0 / _t2;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 11] = 1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspective_zo_rh(double[] dest, int destOffset, double fovy, double aspect, double near, double far) {
        double _sp0 = far / (near - far);
        double _t2 = Math.tan(0.5 * fovy);
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset] = 1.0 / (aspect * _t2);
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = 1.0 / _t2;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 11] = -1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspective_zo(double[] dest, int destOffset, double fovy, double aspect, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makePerspective_zo_lh(dest, destOffset, fovy, aspect, near, far); }
            default -> { return Double4x4OpsKernelsArray.makePerspective_zo_rh(dest, destOffset, fovy, aspect, near, far); }
        }
    }

    public static double[] makePerspectiveFovRange_no_lh(double[] dest, int destOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _sp0 = near + near;
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t2_inv = 1.0 / (near - far);
        double _t3 = _t0 - _t1;
        double _t3_inv = 1.0 / _t3;
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = -(far + near) * _t2_inv;
                dest[destOffset + 14] = _sp0 * far * _t2_inv;
            }
        }
        dest[destOffset] = 2.0 / (aspect * _t3);
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t3_inv + _t3_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = -(_t0 + _t1) * _t3_inv;
        dest[destOffset + 11] = 1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveFovRange_no_rh(double[] dest, int destOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _sp0 = near + near;
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _t2_inv = 1.0 / (near - far);
        double _t3 = _t0 - _t1;
        double _t3_inv = 1.0 / _t3;
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = (far + near) * _t2_inv;
                dest[destOffset + 14] = _sp0 * far * _t2_inv;
            }
        }
        dest[destOffset] = 2.0 / (aspect * _t3);
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t3_inv + _t3_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = (_t0 + _t1) * _t3_inv;
        dest[destOffset + 11] = -1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveFovRange_no(double[] dest, int destOffset, double angleMin, double angleMax, double aspect, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makePerspectiveFovRange_no_lh(dest, destOffset, angleMin, angleMax, aspect, near, far); }
            default -> { return Double4x4OpsKernelsArray.makePerspectiveFovRange_no_rh(dest, destOffset, angleMin, angleMax, aspect, near, far); }
        }
    }

    public static double[] makePerspectiveFovRange_zo_lh(double[] dest, int destOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _sp0 = far / (near - far);
        double _t3 = _t0 - _t1;
        double _t3_inv = 1.0 / _t3;
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset] = 2.0 / (aspect * _t3);
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t3_inv + _t3_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = -(_t0 + _t1) * _t3_inv;
        dest[destOffset + 11] = 1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveFovRange_zo_rh(double[] dest, int destOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _sp0 = far / (near - far);
        double _t3 = _t0 - _t1;
        double _t3_inv = 1.0 / _t3;
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset] = 2.0 / (aspect * _t3);
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t3_inv + _t3_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = (_t0 + _t1) * _t3_inv;
        dest[destOffset + 11] = -1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveFovRange_zo(double[] dest, int destOffset, double angleMin, double angleMax, double aspect, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makePerspectiveFovRange_zo_lh(dest, destOffset, angleMin, angleMax, aspect, near, far); }
            default -> { return Double4x4OpsKernelsArray.makePerspectiveFovRange_zo_rh(dest, destOffset, angleMin, angleMax, aspect, near, far); }
        }
    }

    public static double[] makePerspectiveOffCenterFov_no_lh(double[] dest, int destOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _sp0 = near + near;
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _t4_inv = 1.0 / (near - far);
        double _t5_inv = 1.0 / (_t0 - _t1);
        double _t6_inv = 1.0 / (_t2 - _t3);
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = -(far + near) * _t4_inv;
                dest[destOffset + 14] = _sp0 * far * _t4_inv;
            }
        }
        dest[destOffset] = _t5_inv + _t5_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t6_inv + _t6_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = -(_t1 + _t0) * _t5_inv;
        dest[destOffset + 9] = -(_t3 + _t2) * _t6_inv;
        dest[destOffset + 11] = 1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterFov_no_rh(double[] dest, int destOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _sp0 = near + near;
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _t4_inv = 1.0 / (near - far);
        double _t5_inv = 1.0 / (_t0 - _t1);
        double _t6_inv = 1.0 / (_t2 - _t3);
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = (far + near) * _t4_inv;
                dest[destOffset + 14] = _sp0 * far * _t4_inv;
            }
        }
        dest[destOffset] = _t5_inv + _t5_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t6_inv + _t6_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = (_t1 + _t0) * _t5_inv;
        dest[destOffset + 9] = (_t3 + _t2) * _t6_inv;
        dest[destOffset + 11] = -1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterFov_no(double[] dest, int destOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makePerspectiveOffCenterFov_no_lh(dest, destOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
            default -> { return Double4x4OpsKernelsArray.makePerspectiveOffCenterFov_no_rh(dest, destOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
        }
    }

    public static double[] makePerspectiveOffCenterFov_zo_lh(double[] dest, int destOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _sp0 = far / (near - far);
        double _t5_inv = 1.0 / (_t0 - _t1);
        double _t6_inv = 1.0 / (_t2 - _t3);
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset] = _t5_inv + _t5_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t6_inv + _t6_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = -(_t1 + _t0) * _t5_inv;
        dest[destOffset + 9] = -(_t3 + _t2) * _t6_inv;
        dest[destOffset + 11] = 1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterFov_zo_rh(double[] dest, int destOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _sp0 = far / (near - far);
        double _t5_inv = 1.0 / (_t0 - _t1);
        double _t6_inv = 1.0 / (_t2 - _t3);
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset] = _t5_inv + _t5_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t6_inv + _t6_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = (_t1 + _t0) * _t5_inv;
        dest[destOffset + 9] = (_t3 + _t2) * _t6_inv;
        dest[destOffset + 11] = -1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterFov_zo(double[] dest, int destOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makePerspectiveOffCenterFov_zo_lh(dest, destOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
            default -> { return Double4x4OpsKernelsArray.makePerspectiveOffCenterFov_zo_rh(dest, destOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
        }
    }

    public static double[] makePerspectiveOffCenterRectangleProj_no_lh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double nearFarDist) {
        double _t10 = Math.fma(xY, yX, -(xX * yY));
        double _t11 = Math.fma(xZ, yY, -(xY * yZ));
        double _t12 = Math.fma(xX, yZ, -(xZ * yX));
        double _t15 = Math.fma(pZ - eyeZ, _t10, Math.fma(pX - eyeX, _t11, (pY - eyeY) * _t12));
        double _t16 = _t15 >= 0.0 ? 1.0 : -1.0;
        double _t17 = _t10 * _t16;
        double _t18 = _t12 * _t16;
        double _t19 = _t11 * _t16;
        double _t20 = _t15 * _t16;
        double _t24 = (1.0 / java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        double _t25 = _t18 * _t24;
        double _t26 = _t19 * _t24;
        double _t27 = _t17 * _t24;
        double _t34 = _t20 * _t24;
        double _t37 = Math.fma(yX, _t25, -(yY * _t26));
        double _t38 = Math.fma(yY, _t27, -(yZ * _t25));
        double _t39 = Math.fma(yZ, _t26, -(yX * _t27));
        return makePerspectiveOffCenterRectangleProj_no_lh_s9ff7376b_1(dest, destOffset, eyeX, eyeY, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, 1.0 / nearFarDist, -eyeZ, _t20, _t24, _t25, _t26, _t27, _t34, Math.fma(_t20, _t24, nearFarDist), _t34 + _t34, _t37, _t38, _t39, (1.0 / java.lang.Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39)))));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_no_lh_s9ff7376b_1(double[] dest, int destOffset, double eyeX, double eyeY, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double _rcp0, double _t0, double _t20, double _t24, double _t25, double _t26, double _t27, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t43) {
        double _t44 = _t37 * _t43;
        double _t45 = _t38 * _t43;
        double _t46 = _t39 * _t43;
        double _t63 = Math.fma(_t46, _t26, -(_t45 * _t25));
        double _t64 = Math.fma(_t44, _t25, -(_t46 * _t27));
        double _t65 = Math.fma(_t45, _t27, -(_t44 * _t26));
        if (_t35 == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -_t36;
        } else {
            if (_t34 == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0;
                dest[destOffset + 14] = _t35 + _t35;
            } else {
                dest[destOffset + 10] = Math.fma(_t20, _t24, _t35) * _rcp0;
                dest[destOffset + 14] = -(_t36 * _t35 * _rcp0);
            }
        }
        return makePerspectiveOffCenterRectangleProj_no_lh_s9ff7376b_2(dest, destOffset, xX, xY, xZ, yX, yY, yZ, _t36, _t44, _t45, _t46, Math.fma(pX, _t45, pY * _t46), Math.fma(pZ, _t44, -eyeX * _t45), Math.fma(_t0, _t44, -eyeY * _t46), _t63, _t64, _t65, 1.0 / Math.fma(xZ, _t44, Math.fma(xX, _t45, xY * _t46)), Math.fma(pX, _t64, pY * _t65), Math.fma(pZ, _t63, -eyeX * _t64), Math.fma(_t0, _t63, -eyeY * _t65), 1.0 / Math.fma(yZ, _t63, Math.fma(yX, _t64, yY * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_no_lh_s9ff7376b_2(double[] dest, int destOffset, double xX, double xY, double xZ, double yX, double yY, double yZ, double _t36, double _t44, double _t45, double _t46, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t72_inv, double _t74, double _t75, double _t76, double _t77_inv) {
        dest[destOffset] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = -(Math.fma(xX, _t45, Math.fma(xY, _t46, xZ * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv;
        dest[destOffset + 9] = -(Math.fma(yX, _t64, Math.fma(yY, _t65, yZ * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv;
        dest[destOffset + 11] = 1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterRectangleProj_no_rh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double nearFarDist) {
        double _t10 = Math.fma(xY, yX, -(xX * yY));
        double _t11 = Math.fma(xZ, yY, -(xY * yZ));
        double _t12 = Math.fma(xX, yZ, -(xZ * yX));
        double _t15 = Math.fma(pZ - eyeZ, _t10, Math.fma(pX - eyeX, _t11, (pY - eyeY) * _t12));
        double _t16 = _t15 >= 0.0 ? 1.0 : -1.0;
        double _t17 = _t10 * _t16;
        double _t18 = _t12 * _t16;
        double _t19 = _t11 * _t16;
        double _t20 = _t15 * _t16;
        double _t24 = (1.0 / java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        double _t25 = _t19 * _t24;
        double _t26 = _t18 * _t24;
        double _t27 = _t17 * _t24;
        double _t34 = _t20 * _t24;
        double _t37 = Math.fma(yY, _t25, -(yX * _t26));
        double _t38 = Math.fma(yX, _t27, -(yZ * _t25));
        double _t39 = Math.fma(yZ, _t26, -(yY * _t27));
        return makePerspectiveOffCenterRectangleProj_no_rh_s36270a49_1(dest, destOffset, eyeX, eyeY, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, 1.0 / nearFarDist, -eyeZ, _t20, _t24, _t25, _t26, _t27, _t34, Math.fma(_t20, _t24, nearFarDist), _t34 + _t34, _t37, _t38, _t39, (1.0 / java.lang.Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39)))));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_no_rh_s36270a49_1(double[] dest, int destOffset, double eyeX, double eyeY, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double _rcp0, double _t0, double _t20, double _t24, double _t25, double _t26, double _t27, double _t34, double _t35, double _t36, double _t37, double _t38, double _t39, double _t43) {
        double _t44 = _t37 * _t43;
        double _t45 = _t39 * _t43;
        double _t46 = _t38 * _t43;
        double _t63 = Math.fma(_t45, _t26, -(_t46 * _t25));
        double _t64 = Math.fma(_t46, _t27, -(_t44 * _t26));
        double _t65 = Math.fma(_t44, _t25, -(_t45 * _t27));
        if (_t35 == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -_t36;
        } else {
            if (_t34 == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0;
                dest[destOffset + 14] = _t35 + _t35;
            } else {
                dest[destOffset + 10] = Math.fma(_t20, _t24, _t35) * -_rcp0;
                dest[destOffset + 14] = _t36 * _t35 * -_rcp0;
            }
        }
        return makePerspectiveOffCenterRectangleProj_no_rh_s36270a49_2(dest, destOffset, xX, xY, xZ, yX, yY, yZ, _t36, _t44, _t45, _t46, Math.fma(pX, _t45, pY * _t46), Math.fma(pZ, _t44, -eyeX * _t45), Math.fma(_t0, _t44, -eyeY * _t46), _t63, _t64, _t65, 1.0 / Math.fma(xZ, _t44, Math.fma(xX, _t45, xY * _t46)), Math.fma(pX, _t64, pY * _t65), Math.fma(pZ, _t63, -eyeX * _t64), Math.fma(_t0, _t63, -eyeY * _t65), 1.0 / Math.fma(yZ, _t63, Math.fma(yX, _t64, yY * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_no_rh_s36270a49_2(double[] dest, int destOffset, double xX, double xY, double xZ, double yX, double yY, double yZ, double _t36, double _t44, double _t45, double _t46, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t72_inv, double _t74, double _t75, double _t76, double _t77_inv) {
        dest[destOffset] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = (Math.fma(xX, _t45, Math.fma(xY, _t46, xZ * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv;
        dest[destOffset + 9] = (Math.fma(yX, _t64, Math.fma(yY, _t65, yZ * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv;
        dest[destOffset + 11] = -1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterRectangleProj_no(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double nearFarDist, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_no_lh(dest, destOffset, eyeX, eyeY, eyeZ, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, nearFarDist); }
            default -> { return Double4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_no_rh(dest, destOffset, eyeX, eyeY, eyeZ, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, nearFarDist); }
        }
    }

    public static double[] makePerspectiveOffCenterRectangleProj_zo_lh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double nearFarDist) {
        double _t10 = Math.fma(xY, yX, -(xX * yY));
        double _t11 = Math.fma(xZ, yY, -(xY * yZ));
        double _t12 = Math.fma(xX, yZ, -(xZ * yX));
        double _t15 = Math.fma(pZ - eyeZ, _t10, Math.fma(pX - eyeX, _t11, (pY - eyeY) * _t12));
        double _t16 = _t15 >= 0.0 ? 1.0 : -1.0;
        double _t17 = _t10 * _t16;
        double _t18 = _t12 * _t16;
        double _t19 = _t11 * _t16;
        double _t20 = _t15 * _t16;
        double _t24 = (1.0 / java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        double _t25 = _t18 * _t24;
        double _t26 = _t19 * _t24;
        double _t27 = _t17 * _t24;
        double _t34 = _t20 * _t24;
        double _t35 = Math.fma(_t20, _t24, nearFarDist);
        double _t37 = Math.fma(yX, _t25, -(yY * _t26));
        double _t38 = Math.fma(yY, _t27, -(yZ * _t25));
        double _t39 = Math.fma(yZ, _t26, -(yX * _t27));
        return makePerspectiveOffCenterRectangleProj_zo_lh_s15c7e00f_1(dest, destOffset, eyeX, eyeY, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, -eyeZ, _t25, _t26, _t27, _t34, _t35, _t35 / nearFarDist, _t34 + _t34, _t37, _t38, _t39, (1.0 / java.lang.Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39)))));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_zo_lh_s15c7e00f_1(double[] dest, int destOffset, double eyeX, double eyeY, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double _t0, double _t25, double _t26, double _t27, double _t34, double _t35, double _sp0, double _t36, double _t37, double _t38, double _t39, double _t43) {
        double _t44 = _t37 * _t43;
        double _t45 = _t38 * _t43;
        double _t46 = _t39 * _t43;
        double _t63 = Math.fma(_t46, _t26, -(_t45 * _t25));
        double _t64 = Math.fma(_t44, _t25, -(_t46 * _t27));
        double _t65 = Math.fma(_t45, _t27, -(_t44 * _t26));
        double _t72_inv = 1.0 / Math.fma(xZ, _t44, Math.fma(xX, _t45, xY * _t46));
        if (_t35 == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -_t34;
        } else {
            if (_t34 == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = _t35;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = _sp0 * -_t34;
            }
        }
        dest[destOffset] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0;
        return makePerspectiveOffCenterRectangleProj_zo_lh_s15c7e00f_2(dest, destOffset, xX, xY, xZ, yX, yY, yZ, _t36, _t44, _t45, _t46, Math.fma(pX, _t45, pY * _t46), Math.fma(pZ, _t44, -eyeX * _t45), Math.fma(_t0, _t44, -eyeY * _t46), _t63, _t64, _t65, _t72_inv, Math.fma(pX, _t64, pY * _t65), Math.fma(pZ, _t63, -eyeX * _t64), Math.fma(_t0, _t63, -eyeY * _t65), 1.0 / Math.fma(yZ, _t63, Math.fma(yX, _t64, yY * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_zo_lh_s15c7e00f_2(double[] dest, int destOffset, double xX, double xY, double xZ, double yX, double yY, double yZ, double _t36, double _t44, double _t45, double _t46, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t72_inv, double _t74, double _t75, double _t76, double _t77_inv) {
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = -(Math.fma(xX, _t45, Math.fma(xY, _t46, xZ * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv;
        dest[destOffset + 9] = -(Math.fma(yX, _t64, Math.fma(yY, _t65, yZ * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv;
        dest[destOffset + 11] = 1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterRectangleProj_zo_rh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double nearFarDist) {
        double _t10 = Math.fma(xY, yX, -(xX * yY));
        double _t11 = Math.fma(xZ, yY, -(xY * yZ));
        double _t12 = Math.fma(xX, yZ, -(xZ * yX));
        double _t15 = Math.fma(pZ - eyeZ, _t10, Math.fma(pX - eyeX, _t11, (pY - eyeY) * _t12));
        double _t16 = _t15 >= 0.0 ? 1.0 : -1.0;
        double _t17 = _t10 * _t16;
        double _t18 = _t12 * _t16;
        double _t19 = _t11 * _t16;
        double _t20 = _t15 * _t16;
        double _t24 = (1.0 / java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        double _t25 = _t19 * _t24;
        double _t26 = _t18 * _t24;
        double _t27 = _t17 * _t24;
        double _t34 = _t20 * _t24;
        double _t35 = Math.fma(_t20, _t24, nearFarDist);
        double _t37 = Math.fma(yY, _t25, -(yX * _t26));
        double _t38 = Math.fma(yX, _t27, -(yZ * _t25));
        double _t39 = Math.fma(yZ, _t26, -(yY * _t27));
        return makePerspectiveOffCenterRectangleProj_zo_rh_s41c62545_1(dest, destOffset, eyeX, eyeY, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, -eyeZ, _t25, _t26, _t27, _t34, _t35, _t35 / nearFarDist, _t34 + _t34, _t37, _t38, _t39, (1.0 / java.lang.Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39)))));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_zo_rh_s41c62545_1(double[] dest, int destOffset, double eyeX, double eyeY, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double _t0, double _t25, double _t26, double _t27, double _t34, double _t35, double _sp0, double _t36, double _t37, double _t38, double _t39, double _t43) {
        double _t44 = _t37 * _t43;
        double _t45 = _t39 * _t43;
        double _t46 = _t38 * _t43;
        double _t63 = Math.fma(_t45, _t26, -(_t46 * _t25));
        double _t64 = Math.fma(_t46, _t27, -(_t44 * _t26));
        double _t65 = Math.fma(_t44, _t25, -(_t45 * _t27));
        double _t72_inv = 1.0 / Math.fma(xZ, _t44, Math.fma(xX, _t45, xY * _t46));
        if (_t35 == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -_t34;
        } else {
            if (_t34 == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = _t35;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = _sp0 * -_t34;
            }
        }
        dest[destOffset] = _t36 * _t72_inv;
        return makePerspectiveOffCenterRectangleProj_zo_rh_s41c62545_2(dest, destOffset, xX, xY, xZ, yX, yY, yZ, _t36, _t44, _t45, _t46, Math.fma(pX, _t45, pY * _t46), Math.fma(pZ, _t44, -eyeX * _t45), Math.fma(_t0, _t44, -eyeY * _t46), _t63, _t64, _t65, _t72_inv, Math.fma(pX, _t64, pY * _t65), Math.fma(pZ, _t63, -eyeX * _t64), Math.fma(_t0, _t63, -eyeY * _t65), 1.0 / Math.fma(yZ, _t63, Math.fma(yX, _t64, yY * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_zo_rh_s41c62545_2(double[] dest, int destOffset, double xX, double xY, double xZ, double yX, double yY, double yZ, double _t36, double _t44, double _t45, double _t46, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t72_inv, double _t74, double _t75, double _t76, double _t77_inv) {
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = (Math.fma(xX, _t45, Math.fma(xY, _t46, xZ * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv;
        dest[destOffset + 9] = (Math.fma(yX, _t64, Math.fma(yY, _t65, yZ * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv;
        dest[destOffset + 11] = -1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterRectangleProj_zo(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double nearFarDist, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_zo_lh(dest, destOffset, eyeX, eyeY, eyeZ, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, nearFarDist); }
            default -> { return Double4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_zo_rh(dest, destOffset, eyeX, eyeY, eyeZ, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, nearFarDist); }
        }
    }

    public static double[] makePerspectiveOffCenterRectangleProj_no_lh(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] p, int pOffset, double[] x, int xOffset, double[] y, int yOffset, double nearFarDist) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _px = p[pOffset];
        double _py = p[pOffset + 1];
        double _pz = p[pOffset + 2];
        double _xx = x[xOffset];
        double _xy = x[xOffset + 1];
        double _xz = x[xOffset + 2];
        double _yx = y[yOffset];
        double _yy = y[yOffset + 1];
        double _yz = y[yOffset + 2];
        double _t10 = Math.fma(_xy, _yx, -(_xx * _yy));
        double _t11 = Math.fma(_xz, _yy, -(_xy * _yz));
        double _t12 = Math.fma(_xx, _yz, -(_xz * _yx));
        double _t15 = Math.fma(_pz - _eyez, _t10, Math.fma(_px - _eyex, _t11, (_py - _eyey) * _t12));
        double _t16 = _t15 >= 0.0 ? 1.0 : -1.0;
        double _t17 = _t10 * _t16;
        double _t18 = _t12 * _t16;
        double _t19 = _t11 * _t16;
        double _t20 = _t15 * _t16;
        double _t24 = (1.0 / java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        return makePerspectiveOffCenterRectangleProj_no_lh_sb17787_1(dest, destOffset, _eyex, _eyey, _px, _py, _pz, _xx, _xy, _xz, _yx, _yy, _yz, 1.0 / nearFarDist, -_eyez, _t20, _t24, _t18 * _t24, _t19 * _t24, _t17 * _t24, _t20 * _t24, Math.fma(_t20, _t24, nearFarDist));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_no_lh_sb17787_1(double[] dest, int destOffset, double _eyex, double _eyey, double _px, double _py, double _pz, double _xx, double _xy, double _xz, double _yx, double _yy, double _yz, double _rcp0, double _t0, double _t20, double _t24, double _t25, double _t26, double _t27, double _t34, double _t35) {
        double _t37 = Math.fma(_yx, _t25, -(_yy * _t26));
        double _t38 = Math.fma(_yy, _t27, -(_yz * _t25));
        double _t39 = Math.fma(_yz, _t26, -(_yx * _t27));
        double _t43 = (1.0 / java.lang.Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39))));
        double _t44 = _t37 * _t43;
        double _t45 = _t38 * _t43;
        double _t46 = _t39 * _t43;
        double _t63 = Math.fma(_t46, _t26, -(_t45 * _t25));
        double _t64 = Math.fma(_t44, _t25, -(_t46 * _t27));
        double _t65 = Math.fma(_t45, _t27, -(_t44 * _t26));
        return makePerspectiveOffCenterRectangleProj_no_lh_sb17787_2(dest, destOffset, _xx, _xy, _xz, _yx, _yy, _yz, _rcp0, _t20, _t24, _t34, _t35, _t34 + _t34, _t44, _t45, _t46, Math.fma(_px, _t45, _py * _t46), Math.fma(_pz, _t44, -_eyex * _t45), Math.fma(_t0, _t44, -_eyey * _t46), _t63, _t64, _t65, 1.0 / Math.fma(_xz, _t44, Math.fma(_xx, _t45, _xy * _t46)), Math.fma(_px, _t64, _py * _t65), Math.fma(_pz, _t63, -_eyex * _t64), Math.fma(_t0, _t63, -_eyey * _t65), 1.0 / Math.fma(_yz, _t63, Math.fma(_yx, _t64, _yy * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_no_lh_sb17787_2(double[] dest, int destOffset, double _xx, double _xy, double _xz, double _yx, double _yy, double _yz, double _rcp0, double _t20, double _t24, double _t34, double _t35, double _t36, double _t44, double _t45, double _t46, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t72_inv, double _t74, double _t75, double _t76, double _t77_inv) {
        if (_t35 == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -_t36;
        } else {
            if (_t34 == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0;
                dest[destOffset + 14] = _t35 + _t35;
            } else {
                dest[destOffset + 10] = Math.fma(_t20, _t24, _t35) * _rcp0;
                dest[destOffset + 14] = -(_t36 * _t35 * _rcp0);
            }
        }
        dest[destOffset] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = -(Math.fma(_xx, _t45, Math.fma(_xy, _t46, _xz * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv;
        dest[destOffset + 9] = -(Math.fma(_yx, _t64, Math.fma(_yy, _t65, _yz * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv;
        dest[destOffset + 11] = 1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterRectangleProj_no_rh(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] p, int pOffset, double[] x, int xOffset, double[] y, int yOffset, double nearFarDist) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _px = p[pOffset];
        double _py = p[pOffset + 1];
        double _pz = p[pOffset + 2];
        double _xx = x[xOffset];
        double _xy = x[xOffset + 1];
        double _xz = x[xOffset + 2];
        double _yx = y[yOffset];
        double _yy = y[yOffset + 1];
        double _yz = y[yOffset + 2];
        double _t10 = Math.fma(_xy, _yx, -(_xx * _yy));
        double _t11 = Math.fma(_xz, _yy, -(_xy * _yz));
        double _t12 = Math.fma(_xx, _yz, -(_xz * _yx));
        double _t15 = Math.fma(_pz - _eyez, _t10, Math.fma(_px - _eyex, _t11, (_py - _eyey) * _t12));
        double _t16 = _t15 >= 0.0 ? 1.0 : -1.0;
        double _t17 = _t10 * _t16;
        double _t18 = _t12 * _t16;
        double _t19 = _t11 * _t16;
        double _t20 = _t15 * _t16;
        double _t24 = (1.0 / java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        return makePerspectiveOffCenterRectangleProj_no_rh_sbba22fc5_1(dest, destOffset, _eyex, _eyey, _px, _py, _pz, _xx, _xy, _xz, _yx, _yy, _yz, 1.0 / nearFarDist, -_eyez, _t20, _t24, _t19 * _t24, _t18 * _t24, _t17 * _t24, _t20 * _t24, Math.fma(_t20, _t24, nearFarDist));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_no_rh_sbba22fc5_1(double[] dest, int destOffset, double _eyex, double _eyey, double _px, double _py, double _pz, double _xx, double _xy, double _xz, double _yx, double _yy, double _yz, double _rcp0, double _t0, double _t20, double _t24, double _t25, double _t26, double _t27, double _t34, double _t35) {
        double _t37 = Math.fma(_yy, _t25, -(_yx * _t26));
        double _t38 = Math.fma(_yx, _t27, -(_yz * _t25));
        double _t39 = Math.fma(_yz, _t26, -(_yy * _t27));
        double _t43 = (1.0 / java.lang.Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39))));
        double _t44 = _t37 * _t43;
        double _t45 = _t39 * _t43;
        double _t46 = _t38 * _t43;
        double _t63 = Math.fma(_t45, _t26, -(_t46 * _t25));
        double _t64 = Math.fma(_t46, _t27, -(_t44 * _t26));
        double _t65 = Math.fma(_t44, _t25, -(_t45 * _t27));
        return makePerspectiveOffCenterRectangleProj_no_rh_sbba22fc5_2(dest, destOffset, _xx, _xy, _xz, _yx, _yy, _yz, _rcp0, _t20, _t24, _t34, _t35, _t34 + _t34, _t44, _t45, _t46, Math.fma(_px, _t45, _py * _t46), Math.fma(_pz, _t44, -_eyex * _t45), Math.fma(_t0, _t44, -_eyey * _t46), _t63, _t64, _t65, 1.0 / Math.fma(_xz, _t44, Math.fma(_xx, _t45, _xy * _t46)), Math.fma(_px, _t64, _py * _t65), Math.fma(_pz, _t63, -_eyex * _t64), Math.fma(_t0, _t63, -_eyey * _t65), 1.0 / Math.fma(_yz, _t63, Math.fma(_yx, _t64, _yy * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_no_rh_sbba22fc5_2(double[] dest, int destOffset, double _xx, double _xy, double _xz, double _yx, double _yy, double _yz, double _rcp0, double _t20, double _t24, double _t34, double _t35, double _t36, double _t44, double _t45, double _t46, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t72_inv, double _t74, double _t75, double _t76, double _t77_inv) {
        if (_t35 == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -_t36;
        } else {
            if (_t34 == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0;
                dest[destOffset + 14] = _t35 + _t35;
            } else {
                dest[destOffset + 10] = Math.fma(_t20, _t24, _t35) * -_rcp0;
                dest[destOffset + 14] = _t36 * _t35 * -_rcp0;
            }
        }
        dest[destOffset] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = (Math.fma(_xx, _t45, Math.fma(_xy, _t46, _xz * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv;
        dest[destOffset + 9] = (Math.fma(_yx, _t64, Math.fma(_yy, _t65, _yz * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv;
        dest[destOffset + 11] = -1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterRectangleProj_no(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] p, int pOffset, double[] x, int xOffset, double[] y, int yOffset, double nearFarDist, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_no_lh(dest, destOffset, eye, eyeOffset, p, pOffset, x, xOffset, y, yOffset, nearFarDist); }
            default -> { return Double4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_no_rh(dest, destOffset, eye, eyeOffset, p, pOffset, x, xOffset, y, yOffset, nearFarDist); }
        }
    }

    public static double[] makePerspectiveOffCenterRectangleProj_zo_lh(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] p, int pOffset, double[] x, int xOffset, double[] y, int yOffset, double nearFarDist) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _px = p[pOffset];
        double _py = p[pOffset + 1];
        double _pz = p[pOffset + 2];
        double _xx = x[xOffset];
        double _xy = x[xOffset + 1];
        double _xz = x[xOffset + 2];
        double _yx = y[yOffset];
        double _yy = y[yOffset + 1];
        double _yz = y[yOffset + 2];
        double _t10 = Math.fma(_xy, _yx, -(_xx * _yy));
        double _t11 = Math.fma(_xz, _yy, -(_xy * _yz));
        double _t12 = Math.fma(_xx, _yz, -(_xz * _yx));
        double _t15 = Math.fma(_pz - _eyez, _t10, Math.fma(_px - _eyex, _t11, (_py - _eyey) * _t12));
        double _t16 = _t15 >= 0.0 ? 1.0 : -1.0;
        double _t17 = _t10 * _t16;
        double _t18 = _t12 * _t16;
        double _t19 = _t11 * _t16;
        double _t20 = _t15 * _t16;
        double _t24 = (1.0 / java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        double _t35 = Math.fma(_t20, _t24, nearFarDist);
        return makePerspectiveOffCenterRectangleProj_zo_lh_s46af753b_1(dest, destOffset, _eyex, _eyey, _px, _py, _pz, _xx, _xy, _xz, _yx, _yy, _yz, -_eyez, _t18 * _t24, _t19 * _t24, _t17 * _t24, _t20 * _t24, _t35, _t35 / nearFarDist);
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_zo_lh_s46af753b_1(double[] dest, int destOffset, double _eyex, double _eyey, double _px, double _py, double _pz, double _xx, double _xy, double _xz, double _yx, double _yy, double _yz, double _t0, double _t25, double _t26, double _t27, double _t34, double _t35, double _sp0) {
        double _t37 = Math.fma(_yx, _t25, -(_yy * _t26));
        double _t38 = Math.fma(_yy, _t27, -(_yz * _t25));
        double _t39 = Math.fma(_yz, _t26, -(_yx * _t27));
        double _t43 = (1.0 / java.lang.Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39))));
        double _t44 = _t37 * _t43;
        double _t45 = _t38 * _t43;
        double _t46 = _t39 * _t43;
        double _t63 = Math.fma(_t46, _t26, -(_t45 * _t25));
        double _t64 = Math.fma(_t44, _t25, -(_t46 * _t27));
        double _t65 = Math.fma(_t45, _t27, -(_t44 * _t26));
        return makePerspectiveOffCenterRectangleProj_zo_lh_s46af753b_2(dest, destOffset, _xx, _xy, _xz, _yx, _yy, _yz, _t34, _t35, _sp0, _t34 + _t34, _t44, _t45, _t46, Math.fma(_px, _t45, _py * _t46), Math.fma(_pz, _t44, -_eyex * _t45), Math.fma(_t0, _t44, -_eyey * _t46), _t63, _t64, _t65, 1.0 / Math.fma(_xz, _t44, Math.fma(_xx, _t45, _xy * _t46)), Math.fma(_px, _t64, _py * _t65), Math.fma(_pz, _t63, -_eyex * _t64), Math.fma(_t0, _t63, -_eyey * _t65), 1.0 / Math.fma(_yz, _t63, Math.fma(_yx, _t64, _yy * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_zo_lh_s46af753b_2(double[] dest, int destOffset, double _xx, double _xy, double _xz, double _yx, double _yy, double _yz, double _t34, double _t35, double _sp0, double _t36, double _t44, double _t45, double _t46, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t72_inv, double _t74, double _t75, double _t76, double _t77_inv) {
        if (_t35 == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -_t34;
        } else {
            if (_t34 == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = _t35;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = _sp0 * -_t34;
            }
        }
        dest[destOffset] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = -(Math.fma(_xx, _t45, Math.fma(_xy, _t46, _xz * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv;
        dest[destOffset + 9] = -(Math.fma(_yx, _t64, Math.fma(_yy, _t65, _yz * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv;
        dest[destOffset + 11] = 1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterRectangleProj_zo_rh(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] p, int pOffset, double[] x, int xOffset, double[] y, int yOffset, double nearFarDist) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _px = p[pOffset];
        double _py = p[pOffset + 1];
        double _pz = p[pOffset + 2];
        double _xx = x[xOffset];
        double _xy = x[xOffset + 1];
        double _xz = x[xOffset + 2];
        double _yx = y[yOffset];
        double _yy = y[yOffset + 1];
        double _yz = y[yOffset + 2];
        double _t10 = Math.fma(_xy, _yx, -(_xx * _yy));
        double _t11 = Math.fma(_xz, _yy, -(_xy * _yz));
        double _t12 = Math.fma(_xx, _yz, -(_xz * _yx));
        double _t15 = Math.fma(_pz - _eyez, _t10, Math.fma(_px - _eyex, _t11, (_py - _eyey) * _t12));
        double _t16 = _t15 >= 0.0 ? 1.0 : -1.0;
        double _t17 = _t10 * _t16;
        double _t18 = _t12 * _t16;
        double _t19 = _t11 * _t16;
        double _t20 = _t15 * _t16;
        double _t24 = (1.0 / java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        double _t35 = Math.fma(_t20, _t24, nearFarDist);
        return makePerspectiveOffCenterRectangleProj_zo_rh_s7083e311_1(dest, destOffset, _eyex, _eyey, _px, _py, _pz, _xx, _xy, _xz, _yx, _yy, _yz, -_eyez, _t19 * _t24, _t18 * _t24, _t17 * _t24, _t20 * _t24, _t35, _t35 / nearFarDist);
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_zo_rh_s7083e311_1(double[] dest, int destOffset, double _eyex, double _eyey, double _px, double _py, double _pz, double _xx, double _xy, double _xz, double _yx, double _yy, double _yz, double _t0, double _t25, double _t26, double _t27, double _t34, double _t35, double _sp0) {
        double _t37 = Math.fma(_yy, _t25, -(_yx * _t26));
        double _t38 = Math.fma(_yx, _t27, -(_yz * _t25));
        double _t39 = Math.fma(_yz, _t26, -(_yy * _t27));
        double _t43 = (1.0 / java.lang.Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39))));
        double _t44 = _t37 * _t43;
        double _t45 = _t39 * _t43;
        double _t46 = _t38 * _t43;
        double _t63 = Math.fma(_t45, _t26, -(_t46 * _t25));
        double _t64 = Math.fma(_t46, _t27, -(_t44 * _t26));
        double _t65 = Math.fma(_t44, _t25, -(_t45 * _t27));
        return makePerspectiveOffCenterRectangleProj_zo_rh_s7083e311_2(dest, destOffset, _xx, _xy, _xz, _yx, _yy, _yz, _t34, _t35, _sp0, _t34 + _t34, _t44, _t45, _t46, Math.fma(_px, _t45, _py * _t46), Math.fma(_pz, _t44, -_eyex * _t45), Math.fma(_t0, _t44, -_eyey * _t46), _t63, _t64, _t65, 1.0 / Math.fma(_xz, _t44, Math.fma(_xx, _t45, _xy * _t46)), Math.fma(_px, _t64, _py * _t65), Math.fma(_pz, _t63, -_eyex * _t64), Math.fma(_t0, _t63, -_eyey * _t65), 1.0 / Math.fma(_yz, _t63, Math.fma(_yx, _t64, _yy * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleProj_zo_rh_s7083e311_2(double[] dest, int destOffset, double _xx, double _xy, double _xz, double _yx, double _yy, double _yz, double _t34, double _t35, double _sp0, double _t36, double _t44, double _t45, double _t46, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t72_inv, double _t74, double _t75, double _t76, double _t77_inv) {
        if (_t35 == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -_t34;
        } else {
            if (_t34 == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = _t35;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = _sp0 * -_t34;
            }
        }
        dest[destOffset] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = (Math.fma(_xx, _t45, Math.fma(_xy, _t46, _xz * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv;
        dest[destOffset + 9] = (Math.fma(_yx, _t64, Math.fma(_yy, _t65, _yz * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv;
        dest[destOffset + 11] = -1.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 15] = 0.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterRectangleProj_zo(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] p, int pOffset, double[] x, int xOffset, double[] y, int yOffset, double nearFarDist, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_zo_lh(dest, destOffset, eye, eyeOffset, p, pOffset, x, xOffset, y, yOffset, nearFarDist); }
            default -> { return Double4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_zo_rh(dest, destOffset, eye, eyeOffset, p, pOffset, x, xOffset, y, yOffset, nearFarDist); }
        }
    }

    public static double[] makePerspectiveOffCenterRectangleView_lh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ) {
        double _t11 = Math.fma(xY, yX, -(xX * yY));
        double _t12 = Math.fma(xZ, yY, -(xY * yZ));
        double _t13 = Math.fma(xX, yZ, -(xZ * yX));
        double _t19 = Math.fma(pZ - eyeZ, _t11, Math.fma(pX - eyeX, _t12, (pY - eyeY) * _t13)) >= 0.0 ? 1.0 : -1.0;
        double _t20 = _t11 * _t19;
        double _t21 = _t13 * _t19;
        double _t22 = _t12 * _t19;
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t30, _t31, _t32;
        if (_t25 != 0.0) {
            _t30 = _t20 * _t26;
            _t31 = _t22 * _t26;
            _t32 = _t21 * _t26;
        } else {
            _t30 = 0.0;
            _t31 = 0.0;
            _t32 = 0.0;
        }
        double _t36 = -Math.fma(yZ, _t30, Math.fma(yX, _t31, yY * _t32));
        double _t37 = Math.fma(_t36, _t31, yX);
        double _t38 = Math.fma(_t36, _t32, yY);
        double _t39 = Math.fma(_t36, _t30, yZ);
        return makeLookAt_lh_scc2c3ffe_1(dest, destOffset, eyeX, eyeY, eyeZ, yX, yY, yZ, _t30, _t31, _t32, Math.fma(_t37, _t32, -(_t38 * _t31)), Math.fma(_t38, _t30, -(_t39 * _t32)), Math.fma(_t39, _t31, -(_t37 * _t30)));
    }

    public static double[] makePerspectiveOffCenterRectangleView_rh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ) {
        double _t11 = Math.fma(xY, yX, -(xX * yY));
        double _t12 = Math.fma(xZ, yY, -(xY * yZ));
        double _t13 = Math.fma(xX, yZ, -(xZ * yX));
        double _t19 = Math.fma(pZ - eyeZ, _t11, Math.fma(pX - eyeX, _t12, (pY - eyeY) * _t13)) >= 0.0 ? 1.0 : -1.0;
        double _t20 = _t11 * _t19;
        double _t21 = _t13 * _t19;
        double _t22 = _t12 * _t19;
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t30, _t31, _t32;
        if (_t25 != 0.0) {
            _t30 = _t20 * _t26;
            _t31 = _t22 * _t26;
            _t32 = _t21 * _t26;
        } else {
            _t30 = 0.0;
            _t31 = 0.0;
            _t32 = 0.0;
        }
        double _t36 = -Math.fma(yZ, _t30, Math.fma(yX, _t31, yY * _t32));
        double _t37 = Math.fma(_t36, _t32, yY);
        double _t38 = Math.fma(_t36, _t31, yX);
        double _t39 = Math.fma(_t36, _t30, yZ);
        return makeLookAt_rh_s7db9b09c_1(dest, destOffset, eyeX, eyeY, eyeZ, yX, yY, yZ, _t30, _t31, _t32, Math.fma(_t37, _t31, -(_t38 * _t32)), Math.fma(_t38, _t30, -(_t39 * _t31)), Math.fma(_t39, _t32, -(_t37 * _t30)));
    }

    public static double[] makePerspectiveOffCenterRectangleView_lh(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] p, int pOffset, double[] x, int xOffset, double[] y, int yOffset) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _xx = x[xOffset];
        double _xy = x[xOffset + 1];
        double _xz = x[xOffset + 2];
        double _yx = y[yOffset];
        double _yy = y[yOffset + 1];
        double _yz = y[yOffset + 2];
        double _t11 = Math.fma(_xy, _yx, -(_xx * _yy));
        double _t12 = Math.fma(_xz, _yy, -(_xy * _yz));
        double _t13 = Math.fma(_xx, _yz, -(_xz * _yx));
        double _t19 = Math.fma(p[pOffset + 2] - _eyez, _t11, Math.fma(p[pOffset] - _eyex, _t12, (p[pOffset + 1] - _eyey) * _t13)) >= 0.0 ? 1.0 : -1.0;
        double _t20 = _t11 * _t19;
        double _t21 = _t13 * _t19;
        double _t22 = _t12 * _t19;
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t30, _t31, _t32;
        if (_t25 != 0.0) {
            _t30 = _t20 * _t26;
            _t31 = _t22 * _t26;
            _t32 = _t21 * _t26;
        } else {
            _t30 = 0.0;
            _t31 = 0.0;
            _t32 = 0.0;
        }
        return makePerspectiveOffCenterRectangleView_lh_s99b0703_1(dest, destOffset, _eyex, _eyey, _eyez, _yx, _yy, _yz, _t30, _t31, _t32);
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleView_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleView_lh_s99b0703_1(double[] dest, int destOffset, double _eyex, double _eyey, double _eyez, double _yx, double _yy, double _yz, double _t30, double _t31, double _t32) {
        double _t36 = -Math.fma(_yz, _t30, Math.fma(_yx, _t31, _yy * _t32));
        double _t37 = Math.fma(_t36, _t31, _yx);
        double _t38 = Math.fma(_t36, _t32, _yy);
        double _t39 = Math.fma(_t36, _t30, _yz);
        double _t46 = Math.fma(_t37, _t32, -(_t38 * _t31));
        double _t47 = Math.fma(_t38, _t30, -(_t39 * _t32));
        double _t48 = Math.fma(_t39, _t31, -(_t37 * _t30));
        double _t51 = Math.fma(_t46, _t46, Math.fma(_t47, _t47, _t48 * _t48));
        double _t52 = (1.0 / java.lang.Math.sqrt(_t51));
        double _t56, _t57, _t58;
        if (_t51 > Math.fma(Math.fma(_yz, _yz, Math.fma(_yx, _yx, _yy * _yy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t56 = _t47 * _t52;
            _t57 = _t48 * _t52;
            _t58 = _t46 * _t52;
        } else {
            _t56 = 0.0;
            _t57 = 0.0;
            _t58 = 0.0;
        }
        double _t65 = Math.fma(_t58, _t32, -(_t57 * _t30));
        double _t66 = Math.fma(_t56, _t30, -(_t58 * _t31));
        dest[destOffset] = _t56;
        dest[destOffset + 1] = _t65;
        dest[destOffset + 2] = _t31;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t57;
        dest[destOffset + 5] = _t66;
        return makePerspectiveOffCenterRectangleView_lh_s99b0703_2(dest, destOffset, _eyex, _eyey, _eyez, _t30, _t31, _t32, _t56, _t57, _t58, _t65, _t66, Math.fma(_t57, _t31, -(_t56 * _t32)));
    }

    /**
     * Piece 3 of {@code makePerspectiveOffCenterRectangleView_lh}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makePerspectiveOffCenterRectangleView}; reached
     * only through it.
     */
    private static double[] makePerspectiveOffCenterRectangleView_lh_s99b0703_2(double[] dest, int destOffset, double _eyex, double _eyey, double _eyez, double _t30, double _t31, double _t32, double _t56, double _t57, double _t58, double _t65, double _t66, double _t67) {
        dest[destOffset + 6] = _t32;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t58;
        dest[destOffset + 9] = _t67;
        dest[destOffset + 10] = _t30;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(_eyez, _t58, Math.fma(_eyex, _t56, _eyey * _t57));
        dest[destOffset + 13] = -Math.fma(_eyez, _t67, Math.fma(_eyex, _t65, _eyey * _t66));
        dest[destOffset + 14] = -Math.fma(_eyez, _t30, Math.fma(_eyex, _t31, _eyey * _t32));
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterRectangleView_rh(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] p, int pOffset, double[] x, int xOffset, double[] y, int yOffset) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _xx = x[xOffset];
        double _xy = x[xOffset + 1];
        double _xz = x[xOffset + 2];
        double _yx = y[yOffset];
        double _yy = y[yOffset + 1];
        double _yz = y[yOffset + 2];
        double _t11 = Math.fma(_xy, _yx, -(_xx * _yy));
        double _t12 = Math.fma(_xz, _yy, -(_xy * _yz));
        double _t13 = Math.fma(_xx, _yz, -(_xz * _yx));
        double _t19 = Math.fma(p[pOffset + 2] - _eyez, _t11, Math.fma(p[pOffset] - _eyex, _t12, (p[pOffset + 1] - _eyey) * _t13)) >= 0.0 ? 1.0 : -1.0;
        double _t20 = _t11 * _t19;
        double _t21 = _t13 * _t19;
        double _t22 = _t12 * _t19;
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t30, _t31, _t32;
        if (_t25 != 0.0) {
            _t30 = _t20 * _t26;
            _t31 = _t22 * _t26;
            _t32 = _t21 * _t26;
        } else {
            _t30 = 0.0;
            _t31 = 0.0;
            _t32 = 0.0;
        }
        return makePerspectiveOffCenterRectangleView_rh_s3b1a6e9_1(dest, destOffset, _eyex, _eyey, _eyez, _yx, _yy, _yz, _t30, _t31, _t32);
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleView_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleView_rh_s3b1a6e9_1(double[] dest, int destOffset, double _eyex, double _eyey, double _eyez, double _yx, double _yy, double _yz, double _t30, double _t31, double _t32) {
        double _t36 = -Math.fma(_yz, _t30, Math.fma(_yx, _t31, _yy * _t32));
        double _t37 = Math.fma(_t36, _t32, _yy);
        double _t38 = Math.fma(_t36, _t31, _yx);
        double _t39 = Math.fma(_t36, _t30, _yz);
        double _t46 = Math.fma(_t37, _t31, -(_t38 * _t32));
        double _t47 = Math.fma(_t38, _t30, -(_t39 * _t31));
        double _t48 = Math.fma(_t39, _t32, -(_t37 * _t30));
        double _t51 = Math.fma(_t46, _t46, Math.fma(_t47, _t47, _t48 * _t48));
        double _t52 = (1.0 / java.lang.Math.sqrt(_t51));
        double _t56, _t57, _t58;
        if (_t51 > Math.fma(Math.fma(_yz, _yz, Math.fma(_yx, _yx, _yy * _yy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t56 = _t48 * _t52;
            _t57 = _t47 * _t52;
            _t58 = _t46 * _t52;
        } else {
            _t56 = 0.0;
            _t57 = 0.0;
            _t58 = 0.0;
        }
        double _t65 = Math.fma(_t57, _t30, -(_t58 * _t32));
        double _t66 = Math.fma(_t58, _t31, -(_t56 * _t30));
        dest[destOffset] = _t56;
        dest[destOffset + 1] = _t65;
        dest[destOffset + 2] = -_t31;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t57;
        dest[destOffset + 5] = _t66;
        return makePerspectiveOffCenterRectangleView_rh_s3b1a6e9_2(dest, destOffset, _eyex, _eyey, _eyez, _t30, _t31, _t32, _t56, _t57, _t58, _t65, _t66, Math.fma(_t56, _t32, -(_t57 * _t31)));
    }

    /**
     * Piece 3 of {@code makePerspectiveOffCenterRectangleView_rh}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makePerspectiveOffCenterRectangleView}; reached
     * only through it.
     */
    private static double[] makePerspectiveOffCenterRectangleView_rh_s3b1a6e9_2(double[] dest, int destOffset, double _eyex, double _eyey, double _eyez, double _t30, double _t31, double _t32, double _t56, double _t57, double _t58, double _t65, double _t66, double _t67) {
        dest[destOffset + 6] = -_t32;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t58;
        dest[destOffset + 9] = _t67;
        dest[destOffset + 10] = -_t30;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(_eyez, _t58, Math.fma(_eyex, _t56, _eyey * _t57));
        dest[destOffset + 13] = -Math.fma(_eyez, _t67, Math.fma(_eyex, _t65, _eyey * _t66));
        dest[destOffset + 14] = Math.fma(_eyez, _t30, Math.fma(_eyex, _t31, _eyey * _t32));
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makePerspectiveOffCenterRectangleView_lh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double epsilon) {
        double _t11 = Math.fma(xY, yX, -(xX * yY));
        double _t12 = Math.fma(xZ, yY, -(xY * yZ));
        double _t13 = Math.fma(xX, yZ, -(xZ * yX));
        double _t19 = Math.fma(pZ - eyeZ, _t11, Math.fma(pX - eyeX, _t12, (pY - eyeY) * _t13)) >= 0.0 ? 1.0 : -1.0;
        double _t20 = _t11 * _t19;
        double _t21 = _t13 * _t19;
        double _t22 = _t12 * _t19;
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t30, _t31, _t32;
        if (_t25 != 0.0) {
            _t30 = _t20 * _t26;
            _t31 = _t22 * _t26;
            _t32 = _t21 * _t26;
        } else {
            _t30 = 0.0;
            _t31 = 0.0;
            _t32 = 0.0;
        }
        double _t36 = -Math.fma(yZ, _t30, Math.fma(yX, _t31, yY * _t32));
        double _t37 = Math.fma(_t36, _t31, yX);
        double _t38 = Math.fma(_t36, _t32, yY);
        double _t39 = Math.fma(_t36, _t30, yZ);
        return makePerspectiveOffCenterRectangleView_lh_sb287d7fc_1(dest, destOffset, eyeX, eyeY, eyeZ, yX, yY, yZ, epsilon, _t30, _t31, _t32, Math.fma(_t37, _t32, -(_t38 * _t31)), Math.fma(_t38, _t30, -(_t39 * _t32)), Math.fma(_t39, _t31, -(_t37 * _t30)));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleView_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleView_lh_sb287d7fc_1(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double yX, double yY, double yZ, double epsilon, double _t30, double _t31, double _t32, double _t46, double _t47, double _t48) {
        double _t51 = Math.fma(_t46, _t46, Math.fma(_t47, _t47, _t48 * _t48));
        double _t52 = (1.0 / java.lang.Math.sqrt(_t51));
        double _t56, _t57, _t58;
        if (_t51 > Math.fma(epsilon, Math.fma(yZ, yZ, Math.fma(yX, yX, yY * yY)), 2.2250738585072014E-308)) {
            _t56 = _t47 * _t52;
            _t57 = _t48 * _t52;
            _t58 = _t46 * _t52;
        } else {
            _t56 = 0.0;
            _t57 = 0.0;
            _t58 = 0.0;
        }
        double _t65 = Math.fma(_t58, _t32, -(_t57 * _t30));
        double _t66 = Math.fma(_t56, _t30, -(_t58 * _t31));
        double _t67 = Math.fma(_t57, _t31, -(_t56 * _t32));
        dest[destOffset] = _t56;
        dest[destOffset + 1] = _t65;
        dest[destOffset + 2] = _t31;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t57;
        dest[destOffset + 5] = _t66;
        dest[destOffset + 6] = _t32;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t58;
        dest[destOffset + 9] = _t67;
        dest[destOffset + 10] = _t30;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(eyeZ, _t58, Math.fma(eyeX, _t56, eyeY * _t57));
        return makeLookAt_lh_scc2c3ffe_2(dest, destOffset, eyeX, eyeY, eyeZ, _t30, _t31, _t32, _t65, _t66, _t67);
    }

    public static double[] makePerspectiveOffCenterRectangleView_rh(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double epsilon) {
        double _t11 = Math.fma(xY, yX, -(xX * yY));
        double _t12 = Math.fma(xZ, yY, -(xY * yZ));
        double _t13 = Math.fma(xX, yZ, -(xZ * yX));
        double _t19 = Math.fma(pZ - eyeZ, _t11, Math.fma(pX - eyeX, _t12, (pY - eyeY) * _t13)) >= 0.0 ? 1.0 : -1.0;
        double _t20 = _t11 * _t19;
        double _t21 = _t13 * _t19;
        double _t22 = _t12 * _t19;
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t30, _t31, _t32;
        if (_t25 != 0.0) {
            _t30 = _t20 * _t26;
            _t31 = _t22 * _t26;
            _t32 = _t21 * _t26;
        } else {
            _t30 = 0.0;
            _t31 = 0.0;
            _t32 = 0.0;
        }
        double _t36 = -Math.fma(yZ, _t30, Math.fma(yX, _t31, yY * _t32));
        double _t37 = Math.fma(_t36, _t32, yY);
        double _t38 = Math.fma(_t36, _t31, yX);
        double _t39 = Math.fma(_t36, _t30, yZ);
        return makePerspectiveOffCenterRectangleView_rh_s4ae1a6b6_1(dest, destOffset, eyeX, eyeY, eyeZ, yX, yY, yZ, epsilon, _t30, _t31, _t32, Math.fma(_t37, _t31, -(_t38 * _t32)), Math.fma(_t38, _t30, -(_t39 * _t31)), Math.fma(_t39, _t32, -(_t37 * _t30)));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleView_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleView_rh_s4ae1a6b6_1(double[] dest, int destOffset, double eyeX, double eyeY, double eyeZ, double yX, double yY, double yZ, double epsilon, double _t30, double _t31, double _t32, double _t46, double _t47, double _t48) {
        double _t51 = Math.fma(_t46, _t46, Math.fma(_t47, _t47, _t48 * _t48));
        double _t52 = (1.0 / java.lang.Math.sqrt(_t51));
        double _t56, _t57, _t58;
        if (_t51 > Math.fma(epsilon, Math.fma(yZ, yZ, Math.fma(yX, yX, yY * yY)), 2.2250738585072014E-308)) {
            _t56 = _t48 * _t52;
            _t57 = _t47 * _t52;
            _t58 = _t46 * _t52;
        } else {
            _t56 = 0.0;
            _t57 = 0.0;
            _t58 = 0.0;
        }
        double _t65 = Math.fma(_t57, _t30, -(_t58 * _t32));
        double _t66 = Math.fma(_t58, _t31, -(_t56 * _t30));
        double _t67 = Math.fma(_t56, _t32, -(_t57 * _t31));
        dest[destOffset] = _t56;
        dest[destOffset + 1] = _t65;
        dest[destOffset + 2] = -_t31;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t57;
        dest[destOffset + 5] = _t66;
        dest[destOffset + 6] = -_t32;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t58;
        dest[destOffset + 9] = _t67;
        dest[destOffset + 10] = -_t30;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -Math.fma(eyeZ, _t58, Math.fma(eyeX, _t56, eyeY * _t57));
        return makeLookAt_rh_s7db9b09c_2(dest, destOffset, eyeX, eyeY, eyeZ, _t30, _t31, _t32, _t65, _t66, _t67);
    }

    public static double[] makePerspectiveOffCenterRectangleView_lh(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] p, int pOffset, double[] x, int xOffset, double[] y, int yOffset, double epsilon) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _xx = x[xOffset];
        double _xy = x[xOffset + 1];
        double _xz = x[xOffset + 2];
        double _yx = y[yOffset];
        double _yy = y[yOffset + 1];
        double _yz = y[yOffset + 2];
        double _t11 = Math.fma(_xy, _yx, -(_xx * _yy));
        double _t12 = Math.fma(_xz, _yy, -(_xy * _yz));
        double _t13 = Math.fma(_xx, _yz, -(_xz * _yx));
        double _t19 = Math.fma(p[pOffset + 2] - _eyez, _t11, Math.fma(p[pOffset] - _eyex, _t12, (p[pOffset + 1] - _eyey) * _t13)) >= 0.0 ? 1.0 : -1.0;
        double _t20 = _t11 * _t19;
        double _t21 = _t13 * _t19;
        double _t22 = _t12 * _t19;
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t30, _t31, _t32;
        if (_t25 != 0.0) {
            _t30 = _t20 * _t26;
            _t31 = _t22 * _t26;
            _t32 = _t21 * _t26;
        } else {
            _t30 = 0.0;
            _t31 = 0.0;
            _t32 = 0.0;
        }
        return makePerspectiveOffCenterRectangleView_lh_s5c299d50_1(dest, destOffset, epsilon, _eyex, _eyey, _eyez, _yx, _yy, _yz, _t30, _t31, _t32);
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleView_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleView_lh_s5c299d50_1(double[] dest, int destOffset, double epsilon, double _eyex, double _eyey, double _eyez, double _yx, double _yy, double _yz, double _t30, double _t31, double _t32) {
        double _t36 = -Math.fma(_yz, _t30, Math.fma(_yx, _t31, _yy * _t32));
        double _t37 = Math.fma(_t36, _t31, _yx);
        double _t38 = Math.fma(_t36, _t32, _yy);
        double _t39 = Math.fma(_t36, _t30, _yz);
        double _t46 = Math.fma(_t37, _t32, -(_t38 * _t31));
        double _t47 = Math.fma(_t38, _t30, -(_t39 * _t32));
        double _t48 = Math.fma(_t39, _t31, -(_t37 * _t30));
        double _t51 = Math.fma(_t46, _t46, Math.fma(_t47, _t47, _t48 * _t48));
        double _t52 = (1.0 / java.lang.Math.sqrt(_t51));
        double _t56, _t57, _t58;
        if (_t51 > Math.fma(epsilon, Math.fma(_yz, _yz, Math.fma(_yx, _yx, _yy * _yy)), 2.2250738585072014E-308)) {
            _t56 = _t47 * _t52;
            _t57 = _t48 * _t52;
            _t58 = _t46 * _t52;
        } else {
            _t56 = 0.0;
            _t57 = 0.0;
            _t58 = 0.0;
        }
        double _t65 = Math.fma(_t58, _t32, -(_t57 * _t30));
        double _t66 = Math.fma(_t56, _t30, -(_t58 * _t31));
        dest[destOffset] = _t56;
        dest[destOffset + 1] = _t65;
        dest[destOffset + 2] = _t31;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t57;
        dest[destOffset + 5] = _t66;
        return makePerspectiveOffCenterRectangleView_lh_s99b0703_2(dest, destOffset, _eyex, _eyey, _eyez, _t30, _t31, _t32, _t56, _t57, _t58, _t65, _t66, Math.fma(_t57, _t31, -(_t56 * _t32)));
    }

    public static double[] makePerspectiveOffCenterRectangleView_rh(double[] dest, int destOffset, double[] eye, int eyeOffset, double[] p, int pOffset, double[] x, int xOffset, double[] y, int yOffset, double epsilon) {
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _eyez = eye[eyeOffset + 2];
        double _xx = x[xOffset];
        double _xy = x[xOffset + 1];
        double _xz = x[xOffset + 2];
        double _yx = y[yOffset];
        double _yy = y[yOffset + 1];
        double _yz = y[yOffset + 2];
        double _t11 = Math.fma(_xy, _yx, -(_xx * _yy));
        double _t12 = Math.fma(_xz, _yy, -(_xy * _yz));
        double _t13 = Math.fma(_xx, _yz, -(_xz * _yx));
        double _t19 = Math.fma(p[pOffset + 2] - _eyez, _t11, Math.fma(p[pOffset] - _eyex, _t12, (p[pOffset + 1] - _eyey) * _t13)) >= 0.0 ? 1.0 : -1.0;
        double _t20 = _t11 * _t19;
        double _t21 = _t13 * _t19;
        double _t22 = _t12 * _t19;
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t30, _t31, _t32;
        if (_t25 != 0.0) {
            _t30 = _t20 * _t26;
            _t31 = _t22 * _t26;
            _t32 = _t21 * _t26;
        } else {
            _t30 = 0.0;
            _t31 = 0.0;
            _t32 = 0.0;
        }
        return makePerspectiveOffCenterRectangleView_rh_s1727c03a_1(dest, destOffset, epsilon, _eyex, _eyey, _eyez, _yx, _yy, _yz, _t30, _t31, _t32);
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleView_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makePerspectiveOffCenterRectangleView_rh_s1727c03a_1(double[] dest, int destOffset, double epsilon, double _eyex, double _eyey, double _eyez, double _yx, double _yy, double _yz, double _t30, double _t31, double _t32) {
        double _t36 = -Math.fma(_yz, _t30, Math.fma(_yx, _t31, _yy * _t32));
        double _t37 = Math.fma(_t36, _t32, _yy);
        double _t38 = Math.fma(_t36, _t31, _yx);
        double _t39 = Math.fma(_t36, _t30, _yz);
        double _t46 = Math.fma(_t37, _t31, -(_t38 * _t32));
        double _t47 = Math.fma(_t38, _t30, -(_t39 * _t31));
        double _t48 = Math.fma(_t39, _t32, -(_t37 * _t30));
        double _t51 = Math.fma(_t46, _t46, Math.fma(_t47, _t47, _t48 * _t48));
        double _t52 = (1.0 / java.lang.Math.sqrt(_t51));
        double _t56, _t57, _t58;
        if (_t51 > Math.fma(epsilon, Math.fma(_yz, _yz, Math.fma(_yx, _yx, _yy * _yy)), 2.2250738585072014E-308)) {
            _t56 = _t48 * _t52;
            _t57 = _t47 * _t52;
            _t58 = _t46 * _t52;
        } else {
            _t56 = 0.0;
            _t57 = 0.0;
            _t58 = 0.0;
        }
        double _t65 = Math.fma(_t57, _t30, -(_t58 * _t32));
        double _t66 = Math.fma(_t58, _t31, -(_t56 * _t30));
        dest[destOffset] = _t56;
        dest[destOffset + 1] = _t65;
        dest[destOffset + 2] = -_t31;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t57;
        dest[destOffset + 5] = _t66;
        return makePerspectiveOffCenterRectangleView_rh_s3b1a6e9_2(dest, destOffset, _eyex, _eyey, _eyez, _t30, _t31, _t32, _t56, _t57, _t58, _t65, _t66, Math.fma(_t56, _t32, -(_t57 * _t31)));
    }

    public static double[] makeRotationLookAlong_lh(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t12, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest[destOffset] = _t37;
        dest[destOffset + 1] = Math.fma(_t39, _t13, -(_t38 * _t11));
        return makeRotationLookAlong_lh_s4a191ef7_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_lh_s4a191ef7_1(double[] dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest[destOffset + 2] = _t12;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t38;
        dest[destOffset + 5] = Math.fma(_t37, _t11, -(_t39 * _t12));
        dest[destOffset + 6] = _t13;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t39;
        dest[destOffset + 9] = Math.fma(_t38, _t12, -(_t37 * _t13));
        dest[destOffset + 10] = _t11;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 14] = 0.0;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeRotationLookAlong_rh(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t13, upY);
        double _t19 = Math.fma(_t17, _t12, upX);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t12, -(_t19 * _t13));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t12));
        double _t29 = Math.fma(_t20, _t13, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest[destOffset] = _t37;
        dest[destOffset + 1] = Math.fma(_t38, _t11, _t39 * -_t13);
        return makeRotationLookAlong_rh_s4129997d_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_rh_s4129997d_1(double[] dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest[destOffset + 2] = -_t12;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t38;
        dest[destOffset + 5] = Math.fma(_t39, _t12, _t37 * -_t11);
        dest[destOffset + 6] = -_t13;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t39;
        dest[destOffset + 9] = Math.fma(_t37, _t13, _t38 * -_t12);
        dest[destOffset + 10] = -_t11;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 14] = 0.0;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeRotationLookAlong_lh(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_lh_s3b595b05_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_lh_s3b595b05_1(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest[destOffset] = _t37;
        dest[destOffset + 1] = Math.fma(_t39, _t13, -(_t38 * _t11));
        dest[destOffset + 2] = _t12;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t38;
        dest[destOffset + 5] = Math.fma(_t37, _t11, -(_t39 * _t12));
        dest[destOffset + 6] = _t13;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t39;
        dest[destOffset + 9] = Math.fma(_t38, _t12, -(_t37 * _t13));
        dest[destOffset + 10] = _t11;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 14] = 0.0;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeRotationLookAlong_rh(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t13, _upy);
        double _t19 = Math.fma(_t17, _t12, _upx);
        double _t20 = Math.fma(_t17, _t11, _upz);
        double _t27 = Math.fma(_t18, _t12, -(_t19 * _t13));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t12));
        double _t29 = Math.fma(_t20, _t13, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_rh_s1e83df63_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_rh_s1e83df63_1(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest[destOffset] = _t37;
        dest[destOffset + 1] = Math.fma(_t38, _t11, _t39 * -_t13);
        dest[destOffset + 2] = -_t12;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t38;
        dest[destOffset + 5] = Math.fma(_t39, _t12, _t37 * -_t11);
        dest[destOffset + 6] = -_t13;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t39;
        dest[destOffset + 9] = Math.fma(_t37, _t13, _t38 * -_t12);
        dest[destOffset + 10] = -_t11;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 14] = 0.0;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeRotationLookAlong_lh(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t12, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest[destOffset] = _t37;
        dest[destOffset + 1] = Math.fma(_t39, _t13, -(_t38 * _t11));
        return makeRotationLookAlong_lh_s4a191ef7_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    public static double[] makeRotationLookAlong_rh(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ, double epsilon) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t13, upY);
        double _t19 = Math.fma(_t17, _t12, upX);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t12, -(_t19 * _t13));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t12));
        double _t29 = Math.fma(_t20, _t13, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(epsilon, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 2.2250738585072014E-308)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest[destOffset] = _t37;
        dest[destOffset + 1] = Math.fma(_t38, _t11, _t39 * -_t13);
        return makeRotationLookAlong_rh_s4129997d_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    public static double[] makeRotationLookAlong_lh(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_lh_s24ef9376_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_lh_s24ef9376_1(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t28 * _t33;
            _t38 = _t29 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest[destOffset] = _t37;
        dest[destOffset + 1] = Math.fma(_t39, _t13, -(_t38 * _t11));
        dest[destOffset + 2] = _t12;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t38;
        dest[destOffset + 5] = Math.fma(_t37, _t11, -(_t39 * _t12));
        dest[destOffset + 6] = _t13;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t39;
        dest[destOffset + 9] = Math.fma(_t38, _t12, -(_t37 * _t13));
        dest[destOffset + 10] = _t11;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 14] = 0.0;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] makeRotationLookAlong_rh(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset, double epsilon) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t13, _upy);
        double _t19 = Math.fma(_t17, _t12, _upx);
        double _t20 = Math.fma(_t17, _t11, _upz);
        double _t27 = Math.fma(_t18, _t12, -(_t19 * _t13));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t12));
        double _t29 = Math.fma(_t20, _t13, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        return makeRotationLookAlong_rh_scad1a1b0_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_rh_scad1a1b0_1(double[] dest, int destOffset, double epsilon, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(epsilon, Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 2.2250738585072014E-308)) {
            _t37 = _t29 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t27 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        dest[destOffset] = _t37;
        dest[destOffset + 1] = Math.fma(_t38, _t11, _t39 * -_t13);
        dest[destOffset + 2] = -_t12;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t38;
        dest[destOffset + 5] = Math.fma(_t39, _t12, _t37 * -_t11);
        dest[destOffset + 6] = -_t13;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t39;
        dest[destOffset + 9] = Math.fma(_t37, _t13, _t38 * -_t12);
        dest[destOffset + 10] = -_t11;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 14] = 0.0;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] obliqueZ_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp0 = (_self23 + _self23) / Math.fma(planeW, 1.0 - src[srcOffset + 10], _self23 * (planeZ + (planeX * ((planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0) - _self02) / _self00 + planeY * ((planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0) - _self12) / _self11)));
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = planeX * _sp0 - _self30;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = planeY * _sp0 - _self31;
        dest[destOffset + 7] = _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        return obliqueZ_no_lh_s396a6067_1(dest, destOffset, planeZ, planeW, _self32, _self03, _self13, _self33, _sp0);
    }

    /**
     * Piece 2 of {@code obliqueZ_no_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code obliqueZ}; reached only through it.
     */
    private static double[] obliqueZ_no_lh_s396a6067_1(double[] dest, int destOffset, double planeZ, double planeW, double _self32, double _self03, double _self13, double _self33, double _sp0) {
        dest[destOffset + 10] = planeZ * _sp0 - _self32;
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 14] = planeW * _sp0 - _self33;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] obliqueZ_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp0 = (_self23 + _self23) / Math.fma(planeW, 1.0 + src[srcOffset + 10], _self23 * (planeX * (_self02 + (planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0)) / _self00 + planeY * (_self12 + (planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0)) / _self11 - planeZ));
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = planeX * _sp0 - _self30;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = planeY * _sp0 - _self31;
        dest[destOffset + 7] = _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        return obliqueZ_no_lh_s396a6067_1(dest, destOffset, planeZ, planeW, _self32, _self03, _self13, _self33, _sp0);
    }

    public static double[] obliqueZ_no(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.obliqueZ_no_lh(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW); }
            default -> { return Double4x4OpsKernelsArray.obliqueZ_no_rh(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW); }
        }
    }

    public static double[] obliqueZ_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp0 = _self23 / Math.fma(planeW, 1.0 - src[srcOffset + 10], _self23 * (planeZ + (planeX * ((planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0) - _self02) / _self00 + planeY * ((planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0) - _self12) / _self11)));
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = planeX * _sp0;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = planeY * _sp0;
        dest[destOffset + 7] = _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        dest[destOffset + 10] = planeZ * _sp0;
        return obliqueZ_zo_lh_s84a13123_1(dest, destOffset, planeW, _self32, _self03, _self13, _self33, _sp0);
    }

    /**
     * Piece 2 of {@code obliqueZ_zo_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code obliqueZ}; reached only through it.
     */
    private static double[] obliqueZ_zo_lh_s84a13123_1(double[] dest, int destOffset, double planeW, double _self32, double _self03, double _self13, double _self33, double _sp0) {
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 14] = planeW * _sp0;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] obliqueZ_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp0 = _self23 / Math.fma(planeW, 1.0 + src[srcOffset + 10], _self23 * (planeX * (_self02 + (planeX < 0.0 ? -1.0 : planeX > 0.0 ? 1.0 : 0.0)) / _self00 + planeY * (_self12 + (planeY < 0.0 ? -1.0 : planeY > 0.0 ? 1.0 : 0.0)) / _self11 - planeZ));
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = planeX * _sp0;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = planeY * _sp0;
        dest[destOffset + 7] = _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        dest[destOffset + 10] = planeZ * _sp0;
        return obliqueZ_zo_lh_s84a13123_1(dest, destOffset, planeW, _self32, _self03, _self13, _self33, _sp0);
    }

    public static double[] obliqueZ_zo(double[] dest, int destOffset, double[] src, int srcOffset, double planeX, double planeY, double planeZ, double planeW, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.obliqueZ_zo_lh(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW); }
            default -> { return Double4x4OpsKernelsArray.obliqueZ_zo_rh(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW); }
        }
    }

    public static double[] obliqueZ_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp0 = (_self23 + _self23) / Math.fma(_planew, 1.0 - src[srcOffset + 10], _self23 * (_planez + (_planex * ((_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0) - _self02) / _self00 + _planey * ((_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0) - _self12) / _self11)));
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _planex * _sp0 - _self30;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        return obliqueZ_no_lh_sd85ca548_1(dest, destOffset, _planey, _planez, _planew, _self31, _self02, _self12, _self32, _self03, _self13, _self33, _sp0);
    }

    /**
     * Piece 2 of {@code obliqueZ_no_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code obliqueZ}; reached only through it.
     */
    private static double[] obliqueZ_no_lh_sd85ca548_1(double[] dest, int destOffset, double _planey, double _planez, double _planew, double _self31, double _self02, double _self12, double _self32, double _self03, double _self13, double _self33, double _sp0) {
        dest[destOffset + 6] = _planey * _sp0 - _self31;
        dest[destOffset + 7] = _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        dest[destOffset + 10] = _planez * _sp0 - _self32;
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 14] = _planew * _sp0 - _self33;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] obliqueZ_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp0 = (_self23 + _self23) / Math.fma(_planew, 1.0 + src[srcOffset + 10], _self23 * (_planex * (_self02 + (_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0)) / _self00 + _planey * (_self12 + (_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0)) / _self11 - _planez));
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _planex * _sp0 - _self30;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        return obliqueZ_no_lh_sd85ca548_1(dest, destOffset, _planey, _planez, _planew, _self31, _self02, _self12, _self32, _self03, _self13, _self33, _sp0);
    }

    public static double[] obliqueZ_no(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.obliqueZ_no_lh(dest, destOffset, src, srcOffset, plane, planeOffset); }
            default -> { return Double4x4OpsKernelsArray.obliqueZ_no_rh(dest, destOffset, src, srcOffset, plane, planeOffset); }
        }
    }

    public static double[] obliqueZ_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp0 = _self23 / Math.fma(_planew, 1.0 - src[srcOffset + 10], _self23 * (_planez + (_planex * ((_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0) - _self02) / _self00 + _planey * ((_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0) - _self12) / _self11)));
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _planex * _sp0;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _planey * _sp0;
        return obliqueZ_zo_lh_sf90f2c7c_1(dest, destOffset, _planez, _planew, _self31, _self02, _self12, _self32, _self03, _self13, _self33, _sp0);
    }

    /**
     * Piece 2 of {@code obliqueZ_zo_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code obliqueZ}; reached only through it.
     */
    private static double[] obliqueZ_zo_lh_sf90f2c7c_1(double[] dest, int destOffset, double _planez, double _planew, double _self31, double _self02, double _self12, double _self32, double _self03, double _self13, double _self33, double _sp0) {
        dest[destOffset + 7] = _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        dest[destOffset + 10] = _planez * _sp0;
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 14] = _planew * _sp0;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] obliqueZ_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset) {
        double _planex = plane[planeOffset];
        double _planey = plane[planeOffset + 1];
        double _planez = plane[planeOffset + 2];
        double _planew = plane[planeOffset + 3];
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp0 = _self23 / Math.fma(_planew, 1.0 + src[srcOffset + 10], _self23 * (_planex * (_self02 + (_planex < 0.0 ? -1.0 : _planex > 0.0 ? 1.0 : 0.0)) / _self00 + _planey * (_self12 + (_planey < 0.0 ? -1.0 : _planey > 0.0 ? 1.0 : 0.0)) / _self11 - _planez));
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _planex * _sp0;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _planey * _sp0;
        return obliqueZ_zo_lh_sf90f2c7c_1(dest, destOffset, _planez, _planew, _self31, _self02, _self12, _self32, _self03, _self13, _self33, _sp0);
    }

    public static double[] obliqueZ_zo(double[] dest, int destOffset, double[] src, int srcOffset, double[] plane, int planeOffset, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.obliqueZ_zo_lh(dest, destOffset, src, srcOffset, plane, planeOffset); }
            default -> { return Double4x4OpsKernelsArray.obliqueZ_zo_rh(dest, destOffset, src, srcOffset, plane, planeOffset); }
        }
    }

    public static double[] ortho_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _t2_inv = 1.0 / (zFar - zNear);
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        return ortho_no_lh_s53ab05be_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t2_inv + _t2_inv, _t0_inv * (left + right), _t1_inv * (bottom + top), _t2_inv * (zFar + zNear));
    }

    /** Piece 2 of {@code ortho_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] ortho_no_lh_s53ab05be_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp2, double _sp3, double _sp4, double _sp5) {
        dest[destOffset + 8] = _sp2 * _self02;
        dest[destOffset + 9] = _sp2 * _self12;
        dest[destOffset + 10] = _sp2 * _self22;
        dest[destOffset + 11] = _sp2 * _self32;
        dest[destOffset + 12] = _self03 + (-(_self00 * _sp3) - _self01 * _sp4 - _self02 * _sp5);
        dest[destOffset + 13] = _self13 + (-(_self10 * _sp3) - _self11 * _sp4 - _self12 * _sp5);
        dest[destOffset + 14] = _self23 + (-(_self20 * _sp3) - _self21 * _sp4 - _self22 * _sp5);
        dest[destOffset + 15] = _self33 + (-(_self30 * _sp3) - _self31 * _sp4 - _self32 * _sp5);
        return dest;
    }

    public static double[] ortho_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _t2_inv = 1.0 / (zFar - zNear);
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        return ortho_no_rh_s11cce5c4_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _sp1, -2.0 * _t2_inv, _t0_inv * (left + right), _t1_inv * (bottom + top), _t2_inv * (zFar + zNear));
    }

    /** Piece 2 of {@code ortho_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] ortho_no_rh_s11cce5c4_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp1, double _sp2, double _sp3, double _sp4, double _sp5) {
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = _sp2 * _self02;
        dest[destOffset + 9] = _sp2 * _self12;
        dest[destOffset + 10] = _sp2 * _self22;
        dest[destOffset + 11] = _sp2 * _self32;
        dest[destOffset + 12] = _self03 + (-(_self00 * _sp3) - _self01 * _sp4 - _self02 * _sp5);
        dest[destOffset + 13] = _self13 + (-(_self10 * _sp3) - _self11 * _sp4 - _self12 * _sp5);
        dest[destOffset + 14] = _self23 + (-(_self20 * _sp3) - _self21 * _sp4 - _self22 * _sp5);
        dest[destOffset + 15] = _self33 + (-(_self30 * _sp3) - _self31 * _sp4 - _self32 * _sp5);
        return dest;
    }

    public static double[] ortho_no(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.ortho_no_lh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Double4x4OpsKernelsArray.ortho_no_rh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static double[] ortho_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _t2_inv = 1.0 / (zFar - zNear);
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        return ortho_zo_lh_s1c54852a_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t2_inv, _t2_inv * zNear, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code ortho_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] ortho_zo_lh_s1c54852a_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t2_inv, double _sp4, double _sp2, double _sp3) {
        dest[destOffset + 8] = _self02 * _t2_inv;
        dest[destOffset + 9] = _self12 * _t2_inv;
        dest[destOffset + 10] = _self22 * _t2_inv;
        dest[destOffset + 11] = _self32 * _t2_inv;
        dest[destOffset + 12] = _self03 + (-(_self00 * _sp2) - _self01 * _sp3 - _sp4 * _self02);
        dest[destOffset + 13] = _self13 + (-(_self10 * _sp2) - _self11 * _sp3 - _sp4 * _self12);
        dest[destOffset + 14] = _self23 + (-(_self20 * _sp2) - _self21 * _sp3 - _sp4 * _self22);
        dest[destOffset + 15] = _self33 + (-(_self30 * _sp2) - _self31 * _sp3 - _sp4 * _self32);
        return dest;
    }

    public static double[] ortho_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _t2_inv = 1.0 / (zFar - zNear);
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        return ortho_zo_rh_sf1d578a8_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t2_inv, _t2_inv * zNear, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code ortho_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] ortho_zo_rh_sf1d578a8_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t2_inv, double _sp4, double _sp2, double _sp3) {
        dest[destOffset + 8] = -(_self02 * _t2_inv);
        dest[destOffset + 9] = -(_self12 * _t2_inv);
        dest[destOffset + 10] = -(_self22 * _t2_inv);
        dest[destOffset + 11] = -(_self32 * _t2_inv);
        dest[destOffset + 12] = _self03 + (-(_self00 * _sp2) - _self01 * _sp3 - _sp4 * _self02);
        dest[destOffset + 13] = _self13 + (-(_self10 * _sp2) - _self11 * _sp3 - _sp4 * _self12);
        dest[destOffset + 14] = _self23 + (-(_self20 * _sp2) - _self21 * _sp3 - _sp4 * _self22);
        dest[destOffset + 15] = _self33 + (-(_self30 * _sp2) - _self31 * _sp3 - _sp4 * _self32);
        return dest;
    }

    public static double[] ortho_zo(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.ortho_zo_lh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Double4x4OpsKernelsArray.ortho_zo_rh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static double[] ortho2D_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        dest[destOffset + 10] = _self22;
        return ortho2D_no_lh_s66dbd4f1_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self32, _self03, _self13, _self23, _self33, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code ortho2D_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] ortho2D_no_lh_s66dbd4f1_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp2, double _sp3) {
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03 + (-(_self00 * _sp2) - _self01 * _sp3);
        dest[destOffset + 13] = _self13 + (-(_self10 * _sp2) - _self11 * _sp3);
        dest[destOffset + 14] = _self23 + (-(_self20 * _sp2) - _self21 * _sp3);
        dest[destOffset + 15] = _self33 + (-(_self30 * _sp2) - _self31 * _sp3);
        return dest;
    }

    public static double[] ortho2D_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = -_self02;
        dest[destOffset + 9] = -_self12;
        dest[destOffset + 10] = -_self22;
        return ortho2D_no_rh_s82c233e7_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self32, _self03, _self13, _self23, _self33, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code ortho2D_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] ortho2D_no_rh_s82c233e7_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp2, double _sp3) {
        dest[destOffset + 11] = -_self32;
        dest[destOffset + 12] = _self03 + (-(_self00 * _sp2) - _self01 * _sp3);
        dest[destOffset + 13] = _self13 + (-(_self10 * _sp2) - _self11 * _sp3);
        dest[destOffset + 14] = _self23 + (-(_self20 * _sp2) - _self21 * _sp3);
        dest[destOffset + 15] = _self33 + (-(_self30 * _sp2) - _self31 * _sp3);
        return dest;
    }

    public static double[] ortho2D_no(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.ortho2D_no_lh(dest, destOffset, src, srcOffset, left, right, bottom, top); }
            default -> { return Double4x4OpsKernelsArray.ortho2D_no_rh(dest, destOffset, src, srcOffset, left, right, bottom, top); }
        }
    }

    public static double[] ortho2D_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = 0.5 * _self02;
        return ortho2D_zo_lh_s4cb7cfdd_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code ortho2D_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] ortho2D_zo_lh_s4cb7cfdd_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp2, double _sp3) {
        dest[destOffset + 9] = 0.5 * _self12;
        dest[destOffset + 10] = 0.5 * _self22;
        dest[destOffset + 11] = 0.5 * _self32;
        dest[destOffset + 12] = Math.fma(0.5, _self02, _self03 - _self00 * _sp2 - _self01 * _sp3);
        dest[destOffset + 13] = Math.fma(0.5, _self12, _self13 - _self10 * _sp2 - _self11 * _sp3);
        dest[destOffset + 14] = Math.fma(0.5, _self22, _self23 - _self20 * _sp2 - _self21 * _sp3);
        dest[destOffset + 15] = Math.fma(0.5, _self32, _self33 - _self30 * _sp2 - _self31 * _sp3);
        return dest;
    }

    public static double[] ortho2D_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = -0.5 * _self02;
        return ortho2D_zo_rh_s4efcf5cb_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code ortho2D_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] ortho2D_zo_rh_s4efcf5cb_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp2, double _sp3) {
        dest[destOffset + 9] = -0.5 * _self12;
        dest[destOffset + 10] = -0.5 * _self22;
        dest[destOffset + 11] = -0.5 * _self32;
        dest[destOffset + 12] = Math.fma(0.5, _self02, _self03 - _self00 * _sp2 - _self01 * _sp3);
        dest[destOffset + 13] = Math.fma(0.5, _self12, _self13 - _self10 * _sp2 - _self11 * _sp3);
        dest[destOffset + 14] = Math.fma(0.5, _self22, _self23 - _self20 * _sp2 - _self21 * _sp3);
        dest[destOffset + 15] = Math.fma(0.5, _self32, _self33 - _self30 * _sp2 - _self31 * _sp3);
        return dest;
    }

    public static double[] ortho2D_zo(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.ortho2D_zo_lh(dest, destOffset, src, srcOffset, left, right, bottom, top); }
            default -> { return Double4x4OpsKernelsArray.ortho2D_zo_rh(dest, destOffset, src, srcOffset, left, right, bottom, top); }
        }
    }

    public static double[] orthoCrop_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] view, int viewOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 10];
        double _self23 = src[srcOffset + 14];
        double _t4 = _self20 - _self21;
        return orthoCrop_no_lh_s43d68deb_1(dest, destOffset, src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], _self23, src[srcOffset + 15], view[viewOffset], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _self00 - _self01, _self10 - _self11, _self30 - _self31, _self21 - _self20, _self01 - _self00, _self11 - _self10, _self31 - _self30, _self20 + _self21, _self00 + _self01, _self10 + _self11, _self30 + _self31, -_self20 - _self21, -_self00 - _self01, -_self10 - _self11, -_self30 - _self31, _self23 + (_t4 - _self22));
    }

    /** Piece 2 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_lh_s43d68deb_1(double[] dest, int destOffset, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t52) {
        return orthoCrop_no_lh_s43d68deb_2(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t52, _self03 + (_t5 - _self02), _self13 + (_t6 - _self12), 1.0 / (_self33 + (_t7 - _self32)), _self23 + (_t8 - _self22), _self03 + (_t9 - _self02), _self13 + (_t10 - _self12), 1.0 / (_self33 + (_t11 - _self32)), _self23 + (_t12 - _self22), _self03 + (_t13 - _self02), _self13 + (_t14 - _self12), 1.0 / (_self33 + (_t15 - _self32)), _self23 + (_self22 + _t4), _self03 + (_self02 + _t5), _self13 + (_self12 + _t6), 1.0 / (_self33 + (_self32 + _t7)), _self23 + (_self22 + _t8), _self03 + (_self02 + _t9), _self13 + (_self12 + _t10), 1.0 / (_self33 + (_self32 + _t11)), _self23 + (_self22 + _t12), _self03 + (_self02 + _t13), _self13 + (_self12 + _t14), 1.0 / (_self33 + (_self32 + _t15)), _self23 + (_t16 - _self22), _self03 + (_t17 - _self02), _self13 + (_t18 - _self12), 1.0 / (_self33 + (_t19 - _self32)), _self23 + (_self22 + _t16), _self03 + (_self02 + _t17), _self13 + (_self12 + _t18), 1.0 / (_self33 + (_self32 + _t19)));
    }

    /** Piece 3 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_lh_s43d68deb_2(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t60, double _t61, double _t62, double _t63_inv, double _t64, double _t65, double _t66, double _t67_inv, double _t68, double _t69, double _t70, double _t71_inv, double _t72, double _t73, double _t74, double _t75_inv, double _t76, double _t77, double _t78, double _t79_inv, double _t80, double _t81, double _t82, double _t83_inv) {
        return orthoCrop_no_lh_s43d68deb_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t60, _t61, _t62, _t63_inv, _t64, _t65, _t66, _t67_inv, _t68, _t69, _t70, _t71_inv, _t72, _t73, _t74, _t75_inv, _t76, _t77, _t78, _t79_inv, _t80, _t81, _t82, _t83_inv, _view03 + Math.fma(_view02, _t52, Math.fma(_view00, _t53, _view01 * _t54)) * _t55_inv, _view03 + Math.fma(_view02, _t56, Math.fma(_view00, _t57, _view01 * _t58)) * _t59_inv, _view03 + Math.fma(_view02, _t60, Math.fma(_view00, _t61, _view01 * _t62)) * _t63_inv, _view03 + Math.fma(_view02, _t64, Math.fma(_view00, _t65, _view01 * _t66)) * _t67_inv, _view03 + Math.fma(_view02, _t68, Math.fma(_view00, _t69, _view01 * _t70)) * _t71_inv, _view03 + Math.fma(_view02, _t72, Math.fma(_view00, _t73, _view01 * _t74)) * _t75_inv, _view13 + Math.fma(_view12, _t52, Math.fma(_view10, _t53, _view11 * _t54)) * _t55_inv, _view13 + Math.fma(_view12, _t56, Math.fma(_view10, _t57, _view11 * _t58)) * _t59_inv);
    }

    /** Piece 4 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_lh_s43d68deb_3(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t60, double _t61, double _t62, double _t63_inv, double _t64, double _t65, double _t66, double _t67_inv, double _t68, double _t69, double _t70, double _t71_inv, double _t72, double _t73, double _t74, double _t75_inv, double _t76, double _t77, double _t78, double _t79_inv, double _t80, double _t81, double _t82, double _t83_inv, double _t174, double _t175, double _t176, double _t177, double _t178, double _t179, double _t180, double _t181) {
        return orthoCrop_no_lh_s43d68deb_4(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t72, _t73, _t74, _t75_inv, _t76, _t77, _t78, _t79_inv, _t80, _t81, _t82, _t83_inv, _t174, _t175, _t176, _t177, _t178, _t179, _t180, _t181, _view13 + Math.fma(_view12, _t60, Math.fma(_view10, _t61, _view11 * _t62)) * _t63_inv, _view13 + Math.fma(_view12, _t64, Math.fma(_view10, _t65, _view11 * _t66)) * _t67_inv, _view13 + Math.fma(_view12, _t68, Math.fma(_view10, _t69, _view11 * _t70)) * _t71_inv, _view13 + Math.fma(_view12, _t72, Math.fma(_view10, _t73, _view11 * _t74)) * _t75_inv, _view23 + Math.fma(_view22, _t52, Math.fma(_view20, _t53, _view21 * _t54)) * _t55_inv, _view23 + Math.fma(_view22, _t56, Math.fma(_view20, _t57, _view21 * _t58)) * _t59_inv, _view23 + Math.fma(_view22, _t60, Math.fma(_view20, _t61, _view21 * _t62)) * _t63_inv, _view23 + Math.fma(_view22, _t64, Math.fma(_view20, _t65, _view21 * _t66)) * _t67_inv, _view23 + Math.fma(_view22, _t68, Math.fma(_view20, _t69, _view21 * _t70)) * _t71_inv);
    }

    /** Piece 5 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_lh_s43d68deb_4(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t72, double _t73, double _t74, double _t75_inv, double _t76, double _t77, double _t78, double _t79_inv, double _t80, double _t81, double _t82, double _t83_inv, double _t174, double _t175, double _t176, double _t177, double _t178, double _t179, double _t180, double _t181, double _t182, double _t183, double _t184, double _t185, double _t186, double _t187, double _t188, double _t189, double _t190) {
        double _t198 = _view03 + Math.fma(_view02, _t76, Math.fma(_view00, _t77, _view01 * _t78)) * _t79_inv;
        double _t199 = _view03 + Math.fma(_view02, _t80, Math.fma(_view00, _t81, _view01 * _t82)) * _t83_inv;
        return orthoCrop_no_lh_s43d68deb_5(dest, destOffset, _t180, _t181, _t182, _t183, _t184, _t185, _t186, _t187, _t188, _t189, _t190, _view23 + Math.fma(_view22, _t72, Math.fma(_view20, _t73, _view21 * _t74)) * _t75_inv, _view13 + Math.fma(_view12, _t76, Math.fma(_view10, _t77, _view11 * _t78)) * _t79_inv, _view13 + Math.fma(_view12, _t80, Math.fma(_view10, _t81, _view11 * _t82)) * _t83_inv, _view23 + Math.fma(_view22, _t76, Math.fma(_view20, _t77, _view21 * _t78)) * _t79_inv, _view23 + Math.fma(_view22, _t80, Math.fma(_view20, _t81, _view21 * _t82)) * _t83_inv, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t198, _t174), _t175), _t176), _t199), _t177), _t178), _t179), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t198, _t174), _t175), _t176), _t199), _t177), _t178), _t179));
    }

    /** Piece 6 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_lh_s43d68deb_5(double[] dest, int destOffset, double _t180, double _t181, double _t182, double _t183, double _t184, double _t185, double _t186, double _t187, double _t188, double _t189, double _t190, double _t191, double _t200, double _t201, double _t202, double _t203, double _t240, double _t241) {
        double _t242 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t200, _t180), _t181), _t182), _t201), _t183), _t184), _t185);
        double _t243 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t200, _t180), _t181), _t182), _t201), _t183), _t184), _t185);
        double _t244 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t202, _t186), _t187), _t188), _t203), _t189), _t190), _t191);
        double _t245 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t202, _t186), _t187), _t188), _t203), _t189), _t190), _t191);
        double _t246_inv = 1.0 / (_t240 - _t241);
        double _t247_inv = 1.0 / (_t242 - _t243);
        double _t248_inv = 1.0 / (_t244 - _t245);
        dest[destOffset] = _t246_inv + _t246_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t247_inv + _t247_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = _t248_inv + _t248_inv;
        dest[destOffset + 11] = 0.0;
        return orthoCrop_no_lh_s43d68deb_6(dest, destOffset, _t240, _t241, _t242, _t243, _t244, _t245, _t246_inv, _t247_inv, _t248_inv);
    }

    /** Piece 7 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_lh_s43d68deb_6(double[] dest, int destOffset, double _t240, double _t241, double _t242, double _t243, double _t244, double _t245, double _t246_inv, double _t247_inv, double _t248_inv) {
        dest[destOffset + 12] = -(_t241 + _t240) * _t246_inv;
        dest[destOffset + 13] = -(_t243 + _t242) * _t247_inv;
        dest[destOffset + 14] = -(_t245 + _t244) * _t248_inv;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] orthoCrop_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] view, int viewOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 10];
        double _self23 = src[srcOffset + 14];
        double _t4 = _self20 - _self21;
        return orthoCrop_no_rh_s2e1d2499_1(dest, destOffset, src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], _self23, src[srcOffset + 15], view[viewOffset], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _self00 - _self01, _self10 - _self11, _self30 - _self31, _self21 - _self20, _self01 - _self00, _self11 - _self10, _self31 - _self30, _self20 + _self21, _self00 + _self01, _self10 + _self11, _self30 + _self31, -_self20 - _self21, -_self00 - _self01, -_self10 - _self11, -_self30 - _self31, _self23 + (_t4 - _self22));
    }

    /** Piece 2 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_rh_s2e1d2499_1(double[] dest, int destOffset, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t52) {
        return orthoCrop_no_rh_s2e1d2499_2(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t52, _self03 + (_t5 - _self02), _self13 + (_t6 - _self12), 1.0 / (_self33 + (_t7 - _self32)), _self23 + (_t8 - _self22), _self03 + (_t9 - _self02), _self13 + (_t10 - _self12), 1.0 / (_self33 + (_t11 - _self32)), _self23 + (_t12 - _self22), _self03 + (_t13 - _self02), _self13 + (_t14 - _self12), 1.0 / (_self33 + (_t15 - _self32)), _self23 + (_self22 + _t4), _self03 + (_self02 + _t5), _self13 + (_self12 + _t6), 1.0 / (_self33 + (_self32 + _t7)), _self23 + (_self22 + _t8), _self03 + (_self02 + _t9), _self13 + (_self12 + _t10), 1.0 / (_self33 + (_self32 + _t11)), _self23 + (_self22 + _t12), _self03 + (_self02 + _t13), _self13 + (_self12 + _t14), 1.0 / (_self33 + (_self32 + _t15)), _self23 + (_t16 - _self22), _self03 + (_t17 - _self02), _self13 + (_t18 - _self12), 1.0 / (_self33 + (_t19 - _self32)), _self23 + (_self22 + _t16), _self03 + (_self02 + _t17), _self13 + (_self12 + _t18), 1.0 / (_self33 + (_self32 + _t19)));
    }

    /** Piece 3 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_rh_s2e1d2499_2(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t60, double _t61, double _t62, double _t63_inv, double _t64, double _t65, double _t66, double _t67_inv, double _t68, double _t69, double _t70, double _t71_inv, double _t72, double _t73, double _t74, double _t75_inv, double _t76, double _t77, double _t78, double _t79_inv, double _t80, double _t81, double _t82, double _t83_inv) {
        return orthoCrop_no_rh_s2e1d2499_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t60, _t61, _t62, _t63_inv, _t64, _t65, _t66, _t67_inv, _t68, _t69, _t70, _t71_inv, _t72, _t73, _t74, _t75_inv, _t76, _t77, _t78, _t79_inv, _t80, _t81, _t82, _t83_inv, _view03 + Math.fma(_view02, _t52, Math.fma(_view00, _t53, _view01 * _t54)) * _t55_inv, _view03 + Math.fma(_view02, _t56, Math.fma(_view00, _t57, _view01 * _t58)) * _t59_inv, _view03 + Math.fma(_view02, _t60, Math.fma(_view00, _t61, _view01 * _t62)) * _t63_inv, _view03 + Math.fma(_view02, _t64, Math.fma(_view00, _t65, _view01 * _t66)) * _t67_inv, _view03 + Math.fma(_view02, _t68, Math.fma(_view00, _t69, _view01 * _t70)) * _t71_inv, _view03 + Math.fma(_view02, _t72, Math.fma(_view00, _t73, _view01 * _t74)) * _t75_inv, _view13 + Math.fma(_view12, _t52, Math.fma(_view10, _t53, _view11 * _t54)) * _t55_inv, _view13 + Math.fma(_view12, _t56, Math.fma(_view10, _t57, _view11 * _t58)) * _t59_inv);
    }

    /** Piece 4 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_rh_s2e1d2499_3(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t60, double _t61, double _t62, double _t63_inv, double _t64, double _t65, double _t66, double _t67_inv, double _t68, double _t69, double _t70, double _t71_inv, double _t72, double _t73, double _t74, double _t75_inv, double _t76, double _t77, double _t78, double _t79_inv, double _t80, double _t81, double _t82, double _t83_inv, double _t174, double _t175, double _t176, double _t177, double _t178, double _t179, double _t180, double _t181) {
        return orthoCrop_no_rh_s2e1d2499_4(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t72, _t73, _t74, _t75_inv, _t76, _t77, _t78, _t79_inv, _t80, _t81, _t82, _t83_inv, _t174, _t175, _t176, _t177, _t178, _t179, _t180, _t181, _view13 + Math.fma(_view12, _t60, Math.fma(_view10, _t61, _view11 * _t62)) * _t63_inv, _view13 + Math.fma(_view12, _t64, Math.fma(_view10, _t65, _view11 * _t66)) * _t67_inv, _view13 + Math.fma(_view12, _t68, Math.fma(_view10, _t69, _view11 * _t70)) * _t71_inv, _view13 + Math.fma(_view12, _t72, Math.fma(_view10, _t73, _view11 * _t74)) * _t75_inv, _view23 + Math.fma(_view22, _t52, Math.fma(_view20, _t53, _view21 * _t54)) * _t55_inv, _view23 + Math.fma(_view22, _t56, Math.fma(_view20, _t57, _view21 * _t58)) * _t59_inv, _view23 + Math.fma(_view22, _t60, Math.fma(_view20, _t61, _view21 * _t62)) * _t63_inv, _view23 + Math.fma(_view22, _t64, Math.fma(_view20, _t65, _view21 * _t66)) * _t67_inv, _view23 + Math.fma(_view22, _t68, Math.fma(_view20, _t69, _view21 * _t70)) * _t71_inv);
    }

    /** Piece 5 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_rh_s2e1d2499_4(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t72, double _t73, double _t74, double _t75_inv, double _t76, double _t77, double _t78, double _t79_inv, double _t80, double _t81, double _t82, double _t83_inv, double _t174, double _t175, double _t176, double _t177, double _t178, double _t179, double _t180, double _t181, double _t182, double _t183, double _t184, double _t185, double _t186, double _t187, double _t188, double _t189, double _t190) {
        double _t198 = _view03 + Math.fma(_view02, _t76, Math.fma(_view00, _t77, _view01 * _t78)) * _t79_inv;
        double _t199 = _view03 + Math.fma(_view02, _t80, Math.fma(_view00, _t81, _view01 * _t82)) * _t83_inv;
        return orthoCrop_no_rh_s2e1d2499_5(dest, destOffset, _t180, _t181, _t182, _t183, _t184, _t185, _t186, _t187, _t188, _t189, _t190, _view23 + Math.fma(_view22, _t72, Math.fma(_view20, _t73, _view21 * _t74)) * _t75_inv, _view13 + Math.fma(_view12, _t76, Math.fma(_view10, _t77, _view11 * _t78)) * _t79_inv, _view13 + Math.fma(_view12, _t80, Math.fma(_view10, _t81, _view11 * _t82)) * _t83_inv, _view23 + Math.fma(_view22, _t76, Math.fma(_view20, _t77, _view21 * _t78)) * _t79_inv, _view23 + Math.fma(_view22, _t80, Math.fma(_view20, _t81, _view21 * _t82)) * _t83_inv, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t198, _t174), _t175), _t176), _t199), _t177), _t178), _t179), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t198, _t174), _t175), _t176), _t199), _t177), _t178), _t179));
    }

    /** Piece 6 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_rh_s2e1d2499_5(double[] dest, int destOffset, double _t180, double _t181, double _t182, double _t183, double _t184, double _t185, double _t186, double _t187, double _t188, double _t189, double _t190, double _t191, double _t200, double _t201, double _t202, double _t203, double _t240, double _t241) {
        double _t242 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t200, _t180), _t181), _t182), _t201), _t183), _t184), _t185);
        double _t243 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t200, _t180), _t181), _t182), _t201), _t183), _t184), _t185);
        double _t244 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t202, _t186), _t187), _t188), _t203), _t189), _t190), _t191);
        double _t245 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t202, _t186), _t187), _t188), _t203), _t189), _t190), _t191);
        double _t246_inv = 1.0 / (_t240 - _t241);
        double _t247_inv = 1.0 / (_t242 - _t243);
        double _t248_inv = 1.0 / (_t244 - _t245);
        dest[destOffset] = _t246_inv + _t246_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t247_inv + _t247_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = -2.0 * _t248_inv;
        dest[destOffset + 11] = 0.0;
        return orthoCrop_no_rh_s2e1d2499_6(dest, destOffset, _t240, _t241, _t242, _t243, _t244, _t245, _t246_inv, _t247_inv, _t248_inv);
    }

    /** Piece 7 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_rh_s2e1d2499_6(double[] dest, int destOffset, double _t240, double _t241, double _t242, double _t243, double _t244, double _t245, double _t246_inv, double _t247_inv, double _t248_inv) {
        dest[destOffset + 12] = -(_t241 + _t240) * _t246_inv;
        dest[destOffset + 13] = -(_t243 + _t242) * _t247_inv;
        dest[destOffset + 14] = (_t244 + _t245) * _t248_inv;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] orthoCrop_no(double[] dest, int destOffset, double[] src, int srcOffset, double[] view, int viewOffset, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.orthoCrop_no_lh(dest, destOffset, src, srcOffset, view, viewOffset); }
            default -> { return Double4x4OpsKernelsArray.orthoCrop_no_rh(dest, destOffset, src, srcOffset, view, viewOffset); }
        }
    }

    public static double[] orthoCrop_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] view, int viewOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self23 = src[srcOffset + 14];
        double _t4 = _self20 - _self21;
        return orthoCrop_zo_lh_s324a86b7_1(dest, destOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], _self23, src[srcOffset + 15], view[viewOffset], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _self00 - _self01, _self10 - _self11, _self30 - _self31, _self21 - _self20, _self01 - _self00, _self11 - _self10, _self31 - _self30, _self20 + _self21, _self00 + _self01, _self10 + _self11, _self30 + _self31, -_self20 - _self21, -_self00 - _self01, -_self10 - _self11, -_self30 - _self31, _self23 + _t4);
    }

    /** Piece 2 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_lh_s324a86b7_1(double[] dest, int destOffset, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20) {
        double _t21 = _self03 + _t5;
        double _t22 = _self13 + _t6;
        double _t23_inv = 1.0 / (_self33 + _t7);
        return orthoCrop_zo_lh_s324a86b7_2(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t20, _t21, _t22, _t23_inv, _self23 + _t8, _self03 + _t9, _self13 + _t10, 1.0 / (_self33 + _t11), _self23 + _t12, _self03 + _t13, _self13 + _t14, 1.0 / (_self33 + _t15), _self23 + _t16, _self03 + _t17, _self13 + _t18, 1.0 / (_self33 + _t19), _self23 + (_self22 + _t4), _self03 + (_self02 + _t5), _self13 + (_self12 + _t6), 1.0 / (_self33 + (_self32 + _t7)), _self23 + (_self22 + _t8), _self03 + (_self02 + _t9), _self13 + (_self12 + _t10), 1.0 / (_self33 + (_self32 + _t11)), _self23 + (_self22 + _t12), _self03 + (_self02 + _t13), _self13 + (_self12 + _t14), 1.0 / (_self33 + (_self32 + _t15)), _self23 + (_self22 + _t16), _self03 + (_self02 + _t17), _self13 + (_self12 + _t18), 1.0 / (_self33 + (_self32 + _t19)), _view03 + Math.fma(_view02, _t20, Math.fma(_view00, _t21, _view01 * _t22)) * _t23_inv);
    }

    /** Piece 3 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_lh_s324a86b7_2(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t20, double _t21, double _t22, double _t23_inv, double _t24, double _t25, double _t26, double _t27_inv, double _t28, double _t29, double _t30, double _t31_inv, double _t44, double _t45, double _t46, double _t47_inv, double _t55, double _t56, double _t57, double _t58_inv, double _t59, double _t60, double _t61, double _t62_inv, double _t63, double _t64, double _t65, double _t66_inv, double _t74, double _t75, double _t76, double _t77_inv, double _t146) {
        return orthoCrop_zo_lh_s324a86b7_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t44, _t45, _t46, _t47_inv, _t55, _t56, _t57, _t58_inv, _t59, _t60, _t61, _t62_inv, _t63, _t64, _t65, _t66_inv, _t74, _t75, _t76, _t77_inv, _t146, _view03 + Math.fma(_view02, _t24, Math.fma(_view00, _t25, _view01 * _t26)) * _t27_inv, _view03 + Math.fma(_view02, _t28, Math.fma(_view00, _t29, _view01 * _t30)) * _t31_inv, _view13 + Math.fma(_view12, _t20, Math.fma(_view10, _t21, _view11 * _t22)) * _t23_inv, _view13 + Math.fma(_view12, _t24, Math.fma(_view10, _t25, _view11 * _t26)) * _t27_inv, _view13 + Math.fma(_view12, _t28, Math.fma(_view10, _t29, _view11 * _t30)) * _t31_inv, _view23 + Math.fma(_view22, _t20, Math.fma(_view20, _t21, _view21 * _t22)) * _t23_inv, _view23 + Math.fma(_view22, _t24, Math.fma(_view20, _t25, _view21 * _t26)) * _t27_inv, _view23 + Math.fma(_view22, _t28, Math.fma(_view20, _t29, _view21 * _t30)) * _t31_inv, _view03 + Math.fma(_view02, _t44, Math.fma(_view00, _t45, _view01 * _t46)) * _t47_inv);
    }

    /** Piece 4 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_lh_s324a86b7_3(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t44, double _t45, double _t46, double _t47_inv, double _t55, double _t56, double _t57, double _t58_inv, double _t59, double _t60, double _t61, double _t62_inv, double _t63, double _t64, double _t65, double _t66_inv, double _t74, double _t75, double _t76, double _t77_inv, double _t146, double _t147, double _t148, double _t150, double _t151, double _t152, double _t154, double _t155, double _t156, double _t161) {
        return orthoCrop_zo_lh_s324a86b7_4(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t59, _t60, _t61, _t62_inv, _t63, _t64, _t65, _t66_inv, _t74, _t75, _t76, _t77_inv, _t146, _t147, _t148, _t150, _t151, _t152, _t154, _t155, _t156, _t161, _view13 + Math.fma(_view12, _t44, Math.fma(_view10, _t45, _view11 * _t46)) * _t47_inv, _view23 + Math.fma(_view22, _t44, Math.fma(_view20, _t45, _view21 * _t46)) * _t47_inv, _view03 + Math.fma(_view02, _t55, Math.fma(_view00, _t56, _view01 * _t57)) * _t58_inv, _view03 + Math.fma(_view02, _t59, Math.fma(_view00, _t60, _view01 * _t61)) * _t62_inv, _view03 + Math.fma(_view02, _t63, Math.fma(_view00, _t64, _view01 * _t65)) * _t66_inv, _view13 + Math.fma(_view12, _t55, Math.fma(_view10, _t56, _view11 * _t57)) * _t58_inv, _view13 + Math.fma(_view12, _t59, Math.fma(_view10, _t60, _view11 * _t61)) * _t62_inv, _view13 + Math.fma(_view12, _t63, Math.fma(_view10, _t64, _view11 * _t65)) * _t66_inv, _view23 + Math.fma(_view22, _t55, Math.fma(_view20, _t56, _view21 * _t57)) * _t58_inv);
    }

    /** Piece 5 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_lh_s324a86b7_4(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t59, double _t60, double _t61, double _t62_inv, double _t63, double _t64, double _t65, double _t66_inv, double _t74, double _t75, double _t76, double _t77_inv, double _t146, double _t147, double _t148, double _t150, double _t151, double _t152, double _t154, double _t155, double _t156, double _t161, double _t162, double _t163, double _t173, double _t174, double _t175, double _t176, double _t177, double _t178, double _t179) {
        double _t185 = _view03 + Math.fma(_view02, _t74, Math.fma(_view00, _t75, _view01 * _t76)) * _t77_inv;
        double _t186 = _view13 + Math.fma(_view12, _t74, Math.fma(_view10, _t75, _view11 * _t76)) * _t77_inv;
        return orthoCrop_zo_lh_s324a86b7_5(dest, destOffset, _t154, _t155, _t156, _t163, _t179, _view23 + Math.fma(_view22, _t59, Math.fma(_view20, _t60, _view21 * _t61)) * _t62_inv, _view23 + Math.fma(_view22, _t63, Math.fma(_view20, _t64, _view21 * _t65)) * _t66_inv, _view23 + Math.fma(_view22, _t74, Math.fma(_view20, _t75, _view21 * _t76)) * _t77_inv, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t161, _t146), _t147), _t148), _t185), _t173), _t174), _t175), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t161, _t146), _t147), _t148), _t185), _t173), _t174), _t175), Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t162, _t150), _t151), _t152), _t186), _t176), _t177), _t178), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t162, _t150), _t151), _t152), _t186), _t176), _t177), _t178));
    }

    /** Piece 6 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_lh_s324a86b7_5(double[] dest, int destOffset, double _t154, double _t155, double _t156, double _t163, double _t179, double _t180, double _t181, double _t187, double _t224, double _t225, double _t226, double _t227) {
        double _t229 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t163, _t154), _t155), _t156), _t187), _t179), _t180), _t181);
        double _t230_inv = 1.0 / (_t224 - _t225);
        double _t231_inv = 1.0 / (_t226 - _t227);
        double _t232_inv = 1.0 / (Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t163, _t154), _t155), _t156), _t187), _t179), _t180), _t181) - _t229);
        dest[destOffset] = _t230_inv + _t230_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t231_inv + _t231_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = _t232_inv;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(_t225 + _t224) * _t230_inv;
        dest[destOffset + 13] = -(_t227 + _t226) * _t231_inv;
        dest[destOffset + 14] = -(_t229 * _t232_inv);
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] orthoCrop_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] view, int viewOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self23 = src[srcOffset + 14];
        double _t4 = _self20 - _self21;
        return orthoCrop_zo_rh_s700d1acd_1(dest, destOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], _self23, src[srcOffset + 15], view[viewOffset], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _self00 - _self01, _self10 - _self11, _self30 - _self31, _self21 - _self20, _self01 - _self00, _self11 - _self10, _self31 - _self30, _self20 + _self21, _self00 + _self01, _self10 + _self11, _self30 + _self31, -_self20 - _self21, -_self00 - _self01, -_self10 - _self11, -_self30 - _self31, _self23 + _t4);
    }

    /** Piece 2 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_rh_s700d1acd_1(double[] dest, int destOffset, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20) {
        double _t21 = _self03 + _t5;
        double _t22 = _self13 + _t6;
        double _t23_inv = 1.0 / (_self33 + _t7);
        return orthoCrop_zo_rh_s700d1acd_2(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t20, _t21, _t22, _t23_inv, _self23 + _t8, _self03 + _t9, _self13 + _t10, 1.0 / (_self33 + _t11), _self23 + _t12, _self03 + _t13, _self13 + _t14, 1.0 / (_self33 + _t15), _self23 + _t16, _self03 + _t17, _self13 + _t18, 1.0 / (_self33 + _t19), _self23 + (_self22 + _t4), _self03 + (_self02 + _t5), _self13 + (_self12 + _t6), 1.0 / (_self33 + (_self32 + _t7)), _self23 + (_self22 + _t8), _self03 + (_self02 + _t9), _self13 + (_self12 + _t10), 1.0 / (_self33 + (_self32 + _t11)), _self23 + (_self22 + _t12), _self03 + (_self02 + _t13), _self13 + (_self12 + _t14), 1.0 / (_self33 + (_self32 + _t15)), _self23 + (_self22 + _t16), _self03 + (_self02 + _t17), _self13 + (_self12 + _t18), 1.0 / (_self33 + (_self32 + _t19)), _view03 + Math.fma(_view02, _t20, Math.fma(_view00, _t21, _view01 * _t22)) * _t23_inv);
    }

    /** Piece 3 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_rh_s700d1acd_2(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t20, double _t21, double _t22, double _t23_inv, double _t24, double _t25, double _t26, double _t27_inv, double _t28, double _t29, double _t30, double _t31_inv, double _t44, double _t45, double _t46, double _t47_inv, double _t55, double _t56, double _t57, double _t58_inv, double _t59, double _t60, double _t61, double _t62_inv, double _t63, double _t64, double _t65, double _t66_inv, double _t74, double _t75, double _t76, double _t77_inv, double _t146) {
        return orthoCrop_zo_rh_s700d1acd_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t44, _t45, _t46, _t47_inv, _t55, _t56, _t57, _t58_inv, _t59, _t60, _t61, _t62_inv, _t63, _t64, _t65, _t66_inv, _t74, _t75, _t76, _t77_inv, _t146, _view03 + Math.fma(_view02, _t24, Math.fma(_view00, _t25, _view01 * _t26)) * _t27_inv, _view03 + Math.fma(_view02, _t28, Math.fma(_view00, _t29, _view01 * _t30)) * _t31_inv, _view13 + Math.fma(_view12, _t20, Math.fma(_view10, _t21, _view11 * _t22)) * _t23_inv, _view13 + Math.fma(_view12, _t24, Math.fma(_view10, _t25, _view11 * _t26)) * _t27_inv, _view13 + Math.fma(_view12, _t28, Math.fma(_view10, _t29, _view11 * _t30)) * _t31_inv, _view23 + Math.fma(_view22, _t20, Math.fma(_view20, _t21, _view21 * _t22)) * _t23_inv, _view23 + Math.fma(_view22, _t24, Math.fma(_view20, _t25, _view21 * _t26)) * _t27_inv, _view23 + Math.fma(_view22, _t28, Math.fma(_view20, _t29, _view21 * _t30)) * _t31_inv, _view03 + Math.fma(_view02, _t44, Math.fma(_view00, _t45, _view01 * _t46)) * _t47_inv);
    }

    /** Piece 4 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_rh_s700d1acd_3(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t44, double _t45, double _t46, double _t47_inv, double _t55, double _t56, double _t57, double _t58_inv, double _t59, double _t60, double _t61, double _t62_inv, double _t63, double _t64, double _t65, double _t66_inv, double _t74, double _t75, double _t76, double _t77_inv, double _t146, double _t147, double _t148, double _t150, double _t151, double _t152, double _t154, double _t155, double _t156, double _t161) {
        return orthoCrop_zo_rh_s700d1acd_4(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t59, _t60, _t61, _t62_inv, _t63, _t64, _t65, _t66_inv, _t74, _t75, _t76, _t77_inv, _t146, _t147, _t148, _t150, _t151, _t152, _t154, _t155, _t156, _t161, _view13 + Math.fma(_view12, _t44, Math.fma(_view10, _t45, _view11 * _t46)) * _t47_inv, _view23 + Math.fma(_view22, _t44, Math.fma(_view20, _t45, _view21 * _t46)) * _t47_inv, _view03 + Math.fma(_view02, _t55, Math.fma(_view00, _t56, _view01 * _t57)) * _t58_inv, _view03 + Math.fma(_view02, _t59, Math.fma(_view00, _t60, _view01 * _t61)) * _t62_inv, _view03 + Math.fma(_view02, _t63, Math.fma(_view00, _t64, _view01 * _t65)) * _t66_inv, _view13 + Math.fma(_view12, _t55, Math.fma(_view10, _t56, _view11 * _t57)) * _t58_inv, _view13 + Math.fma(_view12, _t59, Math.fma(_view10, _t60, _view11 * _t61)) * _t62_inv, _view13 + Math.fma(_view12, _t63, Math.fma(_view10, _t64, _view11 * _t65)) * _t66_inv, _view23 + Math.fma(_view22, _t55, Math.fma(_view20, _t56, _view21 * _t57)) * _t58_inv);
    }

    /** Piece 5 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_rh_s700d1acd_4(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t59, double _t60, double _t61, double _t62_inv, double _t63, double _t64, double _t65, double _t66_inv, double _t74, double _t75, double _t76, double _t77_inv, double _t146, double _t147, double _t148, double _t150, double _t151, double _t152, double _t154, double _t155, double _t156, double _t161, double _t162, double _t163, double _t173, double _t174, double _t175, double _t176, double _t177, double _t178, double _t179) {
        double _t185 = _view03 + Math.fma(_view02, _t74, Math.fma(_view00, _t75, _view01 * _t76)) * _t77_inv;
        double _t186 = _view13 + Math.fma(_view12, _t74, Math.fma(_view10, _t75, _view11 * _t76)) * _t77_inv;
        return orthoCrop_zo_rh_s700d1acd_5(dest, destOffset, _t154, _t155, _t156, _t163, _t179, _view23 + Math.fma(_view22, _t59, Math.fma(_view20, _t60, _view21 * _t61)) * _t62_inv, _view23 + Math.fma(_view22, _t63, Math.fma(_view20, _t64, _view21 * _t65)) * _t66_inv, _view23 + Math.fma(_view22, _t74, Math.fma(_view20, _t75, _view21 * _t76)) * _t77_inv, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t161, _t146), _t147), _t148), _t185), _t173), _t174), _t175), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t161, _t146), _t147), _t148), _t185), _t173), _t174), _t175), Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t162, _t150), _t151), _t152), _t186), _t176), _t177), _t178), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t162, _t150), _t151), _t152), _t186), _t176), _t177), _t178));
    }

    /** Piece 6 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_rh_s700d1acd_5(double[] dest, int destOffset, double _t154, double _t155, double _t156, double _t163, double _t179, double _t180, double _t181, double _t187, double _t224, double _t225, double _t226, double _t227) {
        double _t228 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t163, _t154), _t155), _t156), _t187), _t179), _t180), _t181);
        double _t230_inv = 1.0 / (_t224 - _t225);
        double _t231_inv = 1.0 / (_t226 - _t227);
        double _t232_inv = 1.0 / (_t228 - Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t163, _t154), _t155), _t156), _t187), _t179), _t180), _t181));
        dest[destOffset] = _t230_inv + _t230_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t231_inv + _t231_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = -_t232_inv;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(_t225 + _t224) * _t230_inv;
        dest[destOffset + 13] = -(_t227 + _t226) * _t231_inv;
        dest[destOffset + 14] = _t228 * _t232_inv;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] orthoCrop_zo(double[] dest, int destOffset, double[] src, int srcOffset, double[] view, int viewOffset, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.orthoCrop_zo_lh(dest, destOffset, src, srcOffset, view, viewOffset); }
            default -> { return Double4x4OpsKernelsArray.orthoCrop_zo_rh(dest, destOffset, src, srcOffset, view, viewOffset); }
        }
    }

    public static double[] orthoCrop_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] view, int viewOffset, double minZ, double maxZ) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t4 = _self23 + _self20;
        double _t5 = _self03 + _self00;
        return orthoCrop_no_lh_s7748970b_1(dest, destOffset, minZ, maxZ, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _self03, _self13, _self23, _self33, view[viewOffset], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _t5, _self13 + _self10, _self33 + _self30, _self23 - _self20 - _self21, _self03 - _self00 - _self01, _self13 - _self10 - _self11, _self33 - _self30 - _self31, _t4 - _self21, _t5 - _self01);
    }

    /** Piece 2 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_lh_s7748970b_1(double[] dest, int destOffset, double minZ, double maxZ, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t4, double _t5, double _t6, double _t7, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17) {
        double _t18 = _t6 - _self11;
        double _t19 = _t7 - _self31;
        double _t20 = _self23 + _self21 - _self20;
        double _t21 = _self03 + _self01 - _self00;
        double _t22 = _self13 + _self11 - _self10;
        double _t23 = _self33 + _self31 - _self30;
        double _t24 = _t4 + _self21;
        double _t25 = _t5 + _self01;
        double _t26 = _t6 + _self11;
        double _t27 = _t7 + _self31;
        return orthoCrop_no_lh_s7748970b_2(dest, destOffset, maxZ, _self02, _self12, _self22, _self32, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, Math.fma(minZ, _self22, _t12), Math.fma(minZ, _self02, _t13), Math.fma(minZ, _self12, _t14), 1.0 / Math.fma(minZ, _self32, _t15), Math.fma(minZ, _self22, _t16), Math.fma(minZ, _self02, _t17), Math.fma(minZ, _self12, _t18), 1.0 / Math.fma(minZ, _self32, _t19), Math.fma(minZ, _self22, _t20), Math.fma(minZ, _self02, _t21), Math.fma(minZ, _self12, _t22), 1.0 / Math.fma(minZ, _self32, _t23), Math.fma(minZ, _self22, _t24), Math.fma(minZ, _self02, _t25), Math.fma(minZ, _self12, _t26), 1.0 / Math.fma(minZ, _self32, _t27), Math.fma(maxZ, _self22, _t12), Math.fma(maxZ, _self02, _t13));
    }

    /** Piece 3 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_lh_s7748970b_2(double[] dest, int destOffset, double maxZ, double _self02, double _self12, double _self22, double _self32, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31_inv, double _t32, double _t33, double _t34, double _t35_inv, double _t36, double _t37, double _t38, double _t39_inv, double _t40, double _t41, double _t42, double _t43_inv, double _t44, double _t45) {
        return orthoCrop_no_lh_s7748970b_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, Math.fma(maxZ, _self12, _t14), 1.0 / Math.fma(maxZ, _self32, _t15), Math.fma(maxZ, _self22, _t16), Math.fma(maxZ, _self02, _t17), Math.fma(maxZ, _self12, _t18), 1.0 / Math.fma(maxZ, _self32, _t19), Math.fma(maxZ, _self22, _t20), Math.fma(maxZ, _self02, _t21), Math.fma(maxZ, _self12, _t22), 1.0 / Math.fma(maxZ, _self32, _t23), Math.fma(maxZ, _self22, _t24), Math.fma(maxZ, _self02, _t25), Math.fma(maxZ, _self12, _t26), 1.0 / Math.fma(maxZ, _self32, _t27), _view03 + Math.fma(_view02, _t28, Math.fma(_view00, _t29, _view01 * _t30)) * _t31_inv, _view03 + Math.fma(_view02, _t32, Math.fma(_view00, _t33, _view01 * _t34)) * _t35_inv, _view03 + Math.fma(_view02, _t36, Math.fma(_view00, _t37, _view01 * _t38)) * _t39_inv, _view03 + Math.fma(_view02, _t40, Math.fma(_view00, _t41, _view01 * _t42)) * _t43_inv);
    }

    /** Piece 4 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_lh_s7748970b_3(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t28, double _t29, double _t30, double _t31_inv, double _t32, double _t33, double _t34, double _t35_inv, double _t36, double _t37, double _t38, double _t39_inv, double _t40, double _t41, double _t42, double _t43_inv, double _t44, double _t45, double _t46, double _t47_inv, double _t48, double _t49, double _t50, double _t51_inv, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t156, double _t157, double _t158, double _t159) {
        return orthoCrop_no_lh_s7748970b_4(dest, destOffset, _view10, _view20, _view11, _view21, _view12, _view22, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, _t46, _t47_inv, _t48, _t49, _t50, _t51_inv, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _view03 + Math.fma(_view02, _t44, Math.fma(_view00, _t45, _view01 * _t46)) * _t47_inv, _view03 + Math.fma(_view02, _t48, Math.fma(_view00, _t49, _view01 * _t50)) * _t51_inv, _view03 + Math.fma(_view02, _t52, Math.fma(_view00, _t53, _view01 * _t54)) * _t55_inv, _view03 + Math.fma(_view02, _t56, Math.fma(_view00, _t57, _view01 * _t58)) * _t59_inv, _view13 + Math.fma(_view12, _t28, Math.fma(_view10, _t29, _view11 * _t30)) * _t31_inv, _view13 + Math.fma(_view12, _t32, Math.fma(_view10, _t33, _view11 * _t34)) * _t35_inv, _view13 + Math.fma(_view12, _t36, Math.fma(_view10, _t37, _view11 * _t38)) * _t39_inv, _view13 + Math.fma(_view12, _t40, Math.fma(_view10, _t41, _view11 * _t42)) * _t43_inv);
    }

    /** Piece 5 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_lh_s7748970b_4(double[] dest, int destOffset, double _view10, double _view20, double _view11, double _view21, double _view12, double _view22, double _view13, double _view23, double _t28, double _t29, double _t30, double _t31_inv, double _t32, double _t33, double _t34, double _t35_inv, double _t36, double _t37, double _t38, double _t39_inv, double _t40, double _t41, double _t42, double _t43_inv, double _t44, double _t45, double _t46, double _t47_inv, double _t48, double _t49, double _t50, double _t51_inv, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t156, double _t157, double _t158, double _t159, double _t160, double _t161, double _t162, double _t163, double _t164, double _t165, double _t166, double _t167) {
        return orthoCrop_no_lh_s7748970b_5(dest, destOffset, _view20, _view21, _view22, _view23, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _t160, _t161, _t162, _t163, _t164, _t165, _t166, _t167, _view13 + Math.fma(_view12, _t44, Math.fma(_view10, _t45, _view11 * _t46)) * _t47_inv, _view13 + Math.fma(_view12, _t48, Math.fma(_view10, _t49, _view11 * _t50)) * _t51_inv, _view13 + Math.fma(_view12, _t52, Math.fma(_view10, _t53, _view11 * _t54)) * _t55_inv, _view13 + Math.fma(_view12, _t56, Math.fma(_view10, _t57, _view11 * _t58)) * _t59_inv, _view23 + Math.fma(_view22, _t28, Math.fma(_view20, _t29, _view21 * _t30)) * _t31_inv, _view23 + Math.fma(_view22, _t32, Math.fma(_view20, _t33, _view21 * _t34)) * _t35_inv, _view23 + Math.fma(_view22, _t36, Math.fma(_view20, _t37, _view21 * _t38)) * _t39_inv, _view23 + Math.fma(_view22, _t40, Math.fma(_view20, _t41, _view21 * _t42)) * _t43_inv, _view23 + Math.fma(_view22, _t44, Math.fma(_view20, _t45, _view21 * _t46)) * _t47_inv, _view23 + Math.fma(_view22, _t48, Math.fma(_view20, _t49, _view21 * _t50)) * _t51_inv);
    }

    /** Piece 6 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_lh_s7748970b_5(double[] dest, int destOffset, double _view20, double _view21, double _view22, double _view23, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t156, double _t157, double _t158, double _t159, double _t160, double _t161, double _t162, double _t163, double _t164, double _t165, double _t166, double _t167, double _t168, double _t169, double _t170, double _t171, double _t172, double _t173, double _t174, double _t175, double _t176, double _t177) {
        double _t178 = _view23 + Math.fma(_view22, _t52, Math.fma(_view20, _t53, _view21 * _t54)) * _t55_inv;
        double _t179 = _view23 + Math.fma(_view22, _t56, Math.fma(_view20, _t57, _view21 * _t58)) * _t59_inv;
        double _t216 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        double _t217 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        return orthoCrop_no_lh_s7748970b_6(dest, destOffset, _t216, _t217, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171), Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179), 1.0 / (_t216 - _t217));
    }

    /** Piece 7 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_lh_s7748970b_6(double[] dest, int destOffset, double _t216, double _t217, double _t218, double _t219, double _t220, double _t221, double _t222_inv) {
        double _t223_inv = 1.0 / (_t218 - _t219);
        double _t224_inv = 1.0 / (_t220 - _t221);
        dest[destOffset] = _t222_inv + _t222_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t223_inv + _t223_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = _t224_inv + _t224_inv;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(_t217 + _t216) * _t222_inv;
        dest[destOffset + 13] = -(_t219 + _t218) * _t223_inv;
        dest[destOffset + 14] = -(_t221 + _t220) * _t224_inv;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] orthoCrop_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] view, int viewOffset, double minZ, double maxZ) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t4 = _self23 + _self20;
        double _t5 = _self03 + _self00;
        return orthoCrop_no_rh_s3783f385_1(dest, destOffset, minZ, maxZ, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _self03, _self13, _self23, _self33, view[viewOffset], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _t5, _self13 + _self10, _self33 + _self30, _self23 - _self20 - _self21, _self03 - _self00 - _self01, _self13 - _self10 - _self11, _self33 - _self30 - _self31, _t4 - _self21, _t5 - _self01);
    }

    /** Piece 2 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_rh_s3783f385_1(double[] dest, int destOffset, double minZ, double maxZ, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t4, double _t5, double _t6, double _t7, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17) {
        double _t18 = _t6 - _self11;
        double _t19 = _t7 - _self31;
        double _t20 = _self23 + _self21 - _self20;
        double _t21 = _self03 + _self01 - _self00;
        double _t22 = _self13 + _self11 - _self10;
        double _t23 = _self33 + _self31 - _self30;
        double _t24 = _t4 + _self21;
        double _t25 = _t5 + _self01;
        double _t26 = _t6 + _self11;
        double _t27 = _t7 + _self31;
        return orthoCrop_no_rh_s3783f385_2(dest, destOffset, maxZ, _self02, _self12, _self22, _self32, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, Math.fma(minZ, _self22, _t12), Math.fma(minZ, _self02, _t13), Math.fma(minZ, _self12, _t14), 1.0 / Math.fma(minZ, _self32, _t15), Math.fma(minZ, _self22, _t16), Math.fma(minZ, _self02, _t17), Math.fma(minZ, _self12, _t18), 1.0 / Math.fma(minZ, _self32, _t19), Math.fma(minZ, _self22, _t20), Math.fma(minZ, _self02, _t21), Math.fma(minZ, _self12, _t22), 1.0 / Math.fma(minZ, _self32, _t23), Math.fma(minZ, _self22, _t24), Math.fma(minZ, _self02, _t25), Math.fma(minZ, _self12, _t26), 1.0 / Math.fma(minZ, _self32, _t27), Math.fma(maxZ, _self22, _t12), Math.fma(maxZ, _self02, _t13));
    }

    /** Piece 3 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_rh_s3783f385_2(double[] dest, int destOffset, double maxZ, double _self02, double _self12, double _self22, double _self32, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31_inv, double _t32, double _t33, double _t34, double _t35_inv, double _t36, double _t37, double _t38, double _t39_inv, double _t40, double _t41, double _t42, double _t43_inv, double _t44, double _t45) {
        return orthoCrop_no_rh_s3783f385_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, Math.fma(maxZ, _self12, _t14), 1.0 / Math.fma(maxZ, _self32, _t15), Math.fma(maxZ, _self22, _t16), Math.fma(maxZ, _self02, _t17), Math.fma(maxZ, _self12, _t18), 1.0 / Math.fma(maxZ, _self32, _t19), Math.fma(maxZ, _self22, _t20), Math.fma(maxZ, _self02, _t21), Math.fma(maxZ, _self12, _t22), 1.0 / Math.fma(maxZ, _self32, _t23), Math.fma(maxZ, _self22, _t24), Math.fma(maxZ, _self02, _t25), Math.fma(maxZ, _self12, _t26), 1.0 / Math.fma(maxZ, _self32, _t27), _view03 + Math.fma(_view02, _t28, Math.fma(_view00, _t29, _view01 * _t30)) * _t31_inv, _view03 + Math.fma(_view02, _t32, Math.fma(_view00, _t33, _view01 * _t34)) * _t35_inv, _view03 + Math.fma(_view02, _t36, Math.fma(_view00, _t37, _view01 * _t38)) * _t39_inv, _view03 + Math.fma(_view02, _t40, Math.fma(_view00, _t41, _view01 * _t42)) * _t43_inv);
    }

    /** Piece 4 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_rh_s3783f385_3(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t28, double _t29, double _t30, double _t31_inv, double _t32, double _t33, double _t34, double _t35_inv, double _t36, double _t37, double _t38, double _t39_inv, double _t40, double _t41, double _t42, double _t43_inv, double _t44, double _t45, double _t46, double _t47_inv, double _t48, double _t49, double _t50, double _t51_inv, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t156, double _t157, double _t158, double _t159) {
        return orthoCrop_no_rh_s3783f385_4(dest, destOffset, _view10, _view20, _view11, _view21, _view12, _view22, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, _t46, _t47_inv, _t48, _t49, _t50, _t51_inv, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _view03 + Math.fma(_view02, _t44, Math.fma(_view00, _t45, _view01 * _t46)) * _t47_inv, _view03 + Math.fma(_view02, _t48, Math.fma(_view00, _t49, _view01 * _t50)) * _t51_inv, _view03 + Math.fma(_view02, _t52, Math.fma(_view00, _t53, _view01 * _t54)) * _t55_inv, _view03 + Math.fma(_view02, _t56, Math.fma(_view00, _t57, _view01 * _t58)) * _t59_inv, _view13 + Math.fma(_view12, _t28, Math.fma(_view10, _t29, _view11 * _t30)) * _t31_inv, _view13 + Math.fma(_view12, _t32, Math.fma(_view10, _t33, _view11 * _t34)) * _t35_inv, _view13 + Math.fma(_view12, _t36, Math.fma(_view10, _t37, _view11 * _t38)) * _t39_inv, _view13 + Math.fma(_view12, _t40, Math.fma(_view10, _t41, _view11 * _t42)) * _t43_inv);
    }

    /** Piece 5 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_rh_s3783f385_4(double[] dest, int destOffset, double _view10, double _view20, double _view11, double _view21, double _view12, double _view22, double _view13, double _view23, double _t28, double _t29, double _t30, double _t31_inv, double _t32, double _t33, double _t34, double _t35_inv, double _t36, double _t37, double _t38, double _t39_inv, double _t40, double _t41, double _t42, double _t43_inv, double _t44, double _t45, double _t46, double _t47_inv, double _t48, double _t49, double _t50, double _t51_inv, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t156, double _t157, double _t158, double _t159, double _t160, double _t161, double _t162, double _t163, double _t164, double _t165, double _t166, double _t167) {
        return orthoCrop_no_rh_s3783f385_5(dest, destOffset, _view20, _view21, _view22, _view23, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _t160, _t161, _t162, _t163, _t164, _t165, _t166, _t167, _view13 + Math.fma(_view12, _t44, Math.fma(_view10, _t45, _view11 * _t46)) * _t47_inv, _view13 + Math.fma(_view12, _t48, Math.fma(_view10, _t49, _view11 * _t50)) * _t51_inv, _view13 + Math.fma(_view12, _t52, Math.fma(_view10, _t53, _view11 * _t54)) * _t55_inv, _view13 + Math.fma(_view12, _t56, Math.fma(_view10, _t57, _view11 * _t58)) * _t59_inv, _view23 + Math.fma(_view22, _t28, Math.fma(_view20, _t29, _view21 * _t30)) * _t31_inv, _view23 + Math.fma(_view22, _t32, Math.fma(_view20, _t33, _view21 * _t34)) * _t35_inv, _view23 + Math.fma(_view22, _t36, Math.fma(_view20, _t37, _view21 * _t38)) * _t39_inv, _view23 + Math.fma(_view22, _t40, Math.fma(_view20, _t41, _view21 * _t42)) * _t43_inv, _view23 + Math.fma(_view22, _t44, Math.fma(_view20, _t45, _view21 * _t46)) * _t47_inv, _view23 + Math.fma(_view22, _t48, Math.fma(_view20, _t49, _view21 * _t50)) * _t51_inv);
    }

    /** Piece 6 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_rh_s3783f385_5(double[] dest, int destOffset, double _view20, double _view21, double _view22, double _view23, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t156, double _t157, double _t158, double _t159, double _t160, double _t161, double _t162, double _t163, double _t164, double _t165, double _t166, double _t167, double _t168, double _t169, double _t170, double _t171, double _t172, double _t173, double _t174, double _t175, double _t176, double _t177) {
        double _t178 = _view23 + Math.fma(_view22, _t52, Math.fma(_view20, _t53, _view21 * _t54)) * _t55_inv;
        double _t179 = _view23 + Math.fma(_view22, _t56, Math.fma(_view20, _t57, _view21 * _t58)) * _t59_inv;
        double _t216 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        double _t217 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        return orthoCrop_no_rh_s3783f385_6(dest, destOffset, _t216, _t217, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171), Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179), 1.0 / (_t216 - _t217));
    }

    /** Piece 7 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_no_rh_s3783f385_6(double[] dest, int destOffset, double _t216, double _t217, double _t218, double _t219, double _t220, double _t221, double _t222_inv) {
        double _t223_inv = 1.0 / (_t218 - _t219);
        double _t224_inv = 1.0 / (_t220 - _t221);
        dest[destOffset] = _t222_inv + _t222_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t223_inv + _t223_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = -2.0 * _t224_inv;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(_t217 + _t216) * _t222_inv;
        dest[destOffset + 13] = -(_t219 + _t218) * _t223_inv;
        dest[destOffset + 14] = (_t220 + _t221) * _t224_inv;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] orthoCrop_no(double[] dest, int destOffset, double[] src, int srcOffset, double[] view, int viewOffset, double minZ, double maxZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.orthoCrop_no_lh(dest, destOffset, src, srcOffset, view, viewOffset, minZ, maxZ); }
            default -> { return Double4x4OpsKernelsArray.orthoCrop_no_rh(dest, destOffset, src, srcOffset, view, viewOffset, minZ, maxZ); }
        }
    }

    public static double[] orthoCrop_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] view, int viewOffset, double minZ, double maxZ) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t4 = _self23 + _self20;
        double _t5 = _self03 + _self00;
        return orthoCrop_zo_lh_s6d591f8f_1(dest, destOffset, minZ, maxZ, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _self03, _self13, _self23, _self33, view[viewOffset], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _t5, _self13 + _self10, _self33 + _self30, _self23 - _self20 - _self21, _self03 - _self00 - _self01, _self13 - _self10 - _self11, _self33 - _self30 - _self31, _t4 - _self21, _t5 - _self01);
    }

    /** Piece 2 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_lh_s6d591f8f_1(double[] dest, int destOffset, double minZ, double maxZ, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t4, double _t5, double _t6, double _t7, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17) {
        double _t18 = _t6 - _self11;
        double _t19 = _t7 - _self31;
        double _t20 = _self23 + _self21 - _self20;
        double _t21 = _self03 + _self01 - _self00;
        double _t22 = _self13 + _self11 - _self10;
        double _t23 = _self33 + _self31 - _self30;
        double _t24 = _t4 + _self21;
        double _t25 = _t5 + _self01;
        double _t26 = _t6 + _self11;
        double _t27 = _t7 + _self31;
        return orthoCrop_zo_lh_s6d591f8f_2(dest, destOffset, maxZ, _self02, _self12, _self22, _self32, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, Math.fma(minZ, _self22, _t12), Math.fma(minZ, _self02, _t13), Math.fma(minZ, _self12, _t14), 1.0 / Math.fma(minZ, _self32, _t15), Math.fma(minZ, _self22, _t16), Math.fma(minZ, _self02, _t17), Math.fma(minZ, _self12, _t18), 1.0 / Math.fma(minZ, _self32, _t19), Math.fma(minZ, _self22, _t20), Math.fma(minZ, _self02, _t21), Math.fma(minZ, _self12, _t22), 1.0 / Math.fma(minZ, _self32, _t23), Math.fma(minZ, _self22, _t24), Math.fma(minZ, _self02, _t25), Math.fma(minZ, _self12, _t26), 1.0 / Math.fma(minZ, _self32, _t27), Math.fma(maxZ, _self22, _t12), Math.fma(maxZ, _self02, _t13));
    }

    /** Piece 3 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_lh_s6d591f8f_2(double[] dest, int destOffset, double maxZ, double _self02, double _self12, double _self22, double _self32, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31_inv, double _t32, double _t33, double _t34, double _t35_inv, double _t36, double _t37, double _t38, double _t39_inv, double _t40, double _t41, double _t42, double _t43_inv, double _t44, double _t45) {
        return orthoCrop_zo_lh_s6d591f8f_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, Math.fma(maxZ, _self12, _t14), 1.0 / Math.fma(maxZ, _self32, _t15), Math.fma(maxZ, _self22, _t16), Math.fma(maxZ, _self02, _t17), Math.fma(maxZ, _self12, _t18), 1.0 / Math.fma(maxZ, _self32, _t19), Math.fma(maxZ, _self22, _t20), Math.fma(maxZ, _self02, _t21), Math.fma(maxZ, _self12, _t22), 1.0 / Math.fma(maxZ, _self32, _t23), Math.fma(maxZ, _self22, _t24), Math.fma(maxZ, _self02, _t25), Math.fma(maxZ, _self12, _t26), 1.0 / Math.fma(maxZ, _self32, _t27), _view03 + Math.fma(_view02, _t28, Math.fma(_view00, _t29, _view01 * _t30)) * _t31_inv, _view03 + Math.fma(_view02, _t32, Math.fma(_view00, _t33, _view01 * _t34)) * _t35_inv, _view03 + Math.fma(_view02, _t36, Math.fma(_view00, _t37, _view01 * _t38)) * _t39_inv, _view03 + Math.fma(_view02, _t40, Math.fma(_view00, _t41, _view01 * _t42)) * _t43_inv);
    }

    /** Piece 4 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_lh_s6d591f8f_3(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t28, double _t29, double _t30, double _t31_inv, double _t32, double _t33, double _t34, double _t35_inv, double _t36, double _t37, double _t38, double _t39_inv, double _t40, double _t41, double _t42, double _t43_inv, double _t44, double _t45, double _t46, double _t47_inv, double _t48, double _t49, double _t50, double _t51_inv, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t156, double _t157, double _t158, double _t159) {
        return orthoCrop_zo_lh_s6d591f8f_4(dest, destOffset, _view10, _view20, _view11, _view21, _view12, _view22, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, _t46, _t47_inv, _t48, _t49, _t50, _t51_inv, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _view03 + Math.fma(_view02, _t44, Math.fma(_view00, _t45, _view01 * _t46)) * _t47_inv, _view03 + Math.fma(_view02, _t48, Math.fma(_view00, _t49, _view01 * _t50)) * _t51_inv, _view03 + Math.fma(_view02, _t52, Math.fma(_view00, _t53, _view01 * _t54)) * _t55_inv, _view03 + Math.fma(_view02, _t56, Math.fma(_view00, _t57, _view01 * _t58)) * _t59_inv, _view13 + Math.fma(_view12, _t28, Math.fma(_view10, _t29, _view11 * _t30)) * _t31_inv, _view13 + Math.fma(_view12, _t32, Math.fma(_view10, _t33, _view11 * _t34)) * _t35_inv, _view13 + Math.fma(_view12, _t36, Math.fma(_view10, _t37, _view11 * _t38)) * _t39_inv, _view13 + Math.fma(_view12, _t40, Math.fma(_view10, _t41, _view11 * _t42)) * _t43_inv);
    }

    /** Piece 5 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_lh_s6d591f8f_4(double[] dest, int destOffset, double _view10, double _view20, double _view11, double _view21, double _view12, double _view22, double _view13, double _view23, double _t28, double _t29, double _t30, double _t31_inv, double _t32, double _t33, double _t34, double _t35_inv, double _t36, double _t37, double _t38, double _t39_inv, double _t40, double _t41, double _t42, double _t43_inv, double _t44, double _t45, double _t46, double _t47_inv, double _t48, double _t49, double _t50, double _t51_inv, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t156, double _t157, double _t158, double _t159, double _t160, double _t161, double _t162, double _t163, double _t164, double _t165, double _t166, double _t167) {
        return orthoCrop_zo_lh_s6d591f8f_5(dest, destOffset, _view20, _view21, _view22, _view23, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _t160, _t161, _t162, _t163, _t164, _t165, _t166, _t167, _view13 + Math.fma(_view12, _t44, Math.fma(_view10, _t45, _view11 * _t46)) * _t47_inv, _view13 + Math.fma(_view12, _t48, Math.fma(_view10, _t49, _view11 * _t50)) * _t51_inv, _view13 + Math.fma(_view12, _t52, Math.fma(_view10, _t53, _view11 * _t54)) * _t55_inv, _view13 + Math.fma(_view12, _t56, Math.fma(_view10, _t57, _view11 * _t58)) * _t59_inv, _view23 + Math.fma(_view22, _t28, Math.fma(_view20, _t29, _view21 * _t30)) * _t31_inv, _view23 + Math.fma(_view22, _t32, Math.fma(_view20, _t33, _view21 * _t34)) * _t35_inv, _view23 + Math.fma(_view22, _t36, Math.fma(_view20, _t37, _view21 * _t38)) * _t39_inv, _view23 + Math.fma(_view22, _t40, Math.fma(_view20, _t41, _view21 * _t42)) * _t43_inv, _view23 + Math.fma(_view22, _t44, Math.fma(_view20, _t45, _view21 * _t46)) * _t47_inv, _view23 + Math.fma(_view22, _t48, Math.fma(_view20, _t49, _view21 * _t50)) * _t51_inv);
    }

    /** Piece 6 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_lh_s6d591f8f_5(double[] dest, int destOffset, double _view20, double _view21, double _view22, double _view23, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t156, double _t157, double _t158, double _t159, double _t160, double _t161, double _t162, double _t163, double _t164, double _t165, double _t166, double _t167, double _t168, double _t169, double _t170, double _t171, double _t172, double _t173, double _t174, double _t175, double _t176, double _t177) {
        double _t178 = _view23 + Math.fma(_view22, _t52, Math.fma(_view20, _t53, _view21 * _t54)) * _t55_inv;
        double _t179 = _view23 + Math.fma(_view22, _t56, Math.fma(_view20, _t57, _view21 * _t58)) * _t59_inv;
        double _t216 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        double _t217 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        double _t218 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171);
        double _t219 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171);
        return orthoCrop_zo_lh_s6d591f8f_6(dest, destOffset, _t172, _t173, _t174, _t175, _t176, _t177, _t178, _t179, _t216, _t217, _t218, _t219, Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179), 1.0 / (_t216 - _t217), 1.0 / (_t218 - _t219));
    }

    /** Piece 7 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_lh_s6d591f8f_6(double[] dest, int destOffset, double _t172, double _t173, double _t174, double _t175, double _t176, double _t177, double _t178, double _t179, double _t216, double _t217, double _t218, double _t219, double _t221, double _t222_inv, double _t223_inv) {
        double _t224_inv = 1.0 / (Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179) - _t221);
        dest[destOffset] = _t222_inv + _t222_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t223_inv + _t223_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = _t224_inv;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(_t217 + _t216) * _t222_inv;
        dest[destOffset + 13] = -(_t219 + _t218) * _t223_inv;
        dest[destOffset + 14] = -(_t221 * _t224_inv);
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] orthoCrop_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] view, int viewOffset, double minZ, double maxZ) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t4 = _self23 + _self20;
        double _t5 = _self03 + _self00;
        return orthoCrop_zo_rh_s5ade31d1_1(dest, destOffset, minZ, maxZ, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _self03, _self13, _self23, _self33, view[viewOffset], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _t5, _self13 + _self10, _self33 + _self30, _self23 - _self20 - _self21, _self03 - _self00 - _self01, _self13 - _self10 - _self11, _self33 - _self30 - _self31, _t4 - _self21, _t5 - _self01);
    }

    /** Piece 2 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_rh_s5ade31d1_1(double[] dest, int destOffset, double minZ, double maxZ, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t4, double _t5, double _t6, double _t7, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17) {
        double _t18 = _t6 - _self11;
        double _t19 = _t7 - _self31;
        double _t20 = _self23 + _self21 - _self20;
        double _t21 = _self03 + _self01 - _self00;
        double _t22 = _self13 + _self11 - _self10;
        double _t23 = _self33 + _self31 - _self30;
        double _t24 = _t4 + _self21;
        double _t25 = _t5 + _self01;
        double _t26 = _t6 + _self11;
        double _t27 = _t7 + _self31;
        return orthoCrop_zo_rh_s5ade31d1_2(dest, destOffset, maxZ, _self02, _self12, _self22, _self32, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, Math.fma(minZ, _self22, _t12), Math.fma(minZ, _self02, _t13), Math.fma(minZ, _self12, _t14), 1.0 / Math.fma(minZ, _self32, _t15), Math.fma(minZ, _self22, _t16), Math.fma(minZ, _self02, _t17), Math.fma(minZ, _self12, _t18), 1.0 / Math.fma(minZ, _self32, _t19), Math.fma(minZ, _self22, _t20), Math.fma(minZ, _self02, _t21), Math.fma(minZ, _self12, _t22), 1.0 / Math.fma(minZ, _self32, _t23), Math.fma(minZ, _self22, _t24), Math.fma(minZ, _self02, _t25), Math.fma(minZ, _self12, _t26), 1.0 / Math.fma(minZ, _self32, _t27), Math.fma(maxZ, _self22, _t12), Math.fma(maxZ, _self02, _t13));
    }

    /** Piece 3 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_rh_s5ade31d1_2(double[] dest, int destOffset, double maxZ, double _self02, double _self12, double _self22, double _self32, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31_inv, double _t32, double _t33, double _t34, double _t35_inv, double _t36, double _t37, double _t38, double _t39_inv, double _t40, double _t41, double _t42, double _t43_inv, double _t44, double _t45) {
        return orthoCrop_zo_rh_s5ade31d1_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, Math.fma(maxZ, _self12, _t14), 1.0 / Math.fma(maxZ, _self32, _t15), Math.fma(maxZ, _self22, _t16), Math.fma(maxZ, _self02, _t17), Math.fma(maxZ, _self12, _t18), 1.0 / Math.fma(maxZ, _self32, _t19), Math.fma(maxZ, _self22, _t20), Math.fma(maxZ, _self02, _t21), Math.fma(maxZ, _self12, _t22), 1.0 / Math.fma(maxZ, _self32, _t23), Math.fma(maxZ, _self22, _t24), Math.fma(maxZ, _self02, _t25), Math.fma(maxZ, _self12, _t26), 1.0 / Math.fma(maxZ, _self32, _t27), _view03 + Math.fma(_view02, _t28, Math.fma(_view00, _t29, _view01 * _t30)) * _t31_inv, _view03 + Math.fma(_view02, _t32, Math.fma(_view00, _t33, _view01 * _t34)) * _t35_inv, _view03 + Math.fma(_view02, _t36, Math.fma(_view00, _t37, _view01 * _t38)) * _t39_inv, _view03 + Math.fma(_view02, _t40, Math.fma(_view00, _t41, _view01 * _t42)) * _t43_inv);
    }

    /** Piece 4 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_rh_s5ade31d1_3(double[] dest, int destOffset, double _view00, double _view10, double _view20, double _view01, double _view11, double _view21, double _view02, double _view12, double _view22, double _view03, double _view13, double _view23, double _t28, double _t29, double _t30, double _t31_inv, double _t32, double _t33, double _t34, double _t35_inv, double _t36, double _t37, double _t38, double _t39_inv, double _t40, double _t41, double _t42, double _t43_inv, double _t44, double _t45, double _t46, double _t47_inv, double _t48, double _t49, double _t50, double _t51_inv, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t156, double _t157, double _t158, double _t159) {
        return orthoCrop_zo_rh_s5ade31d1_4(dest, destOffset, _view10, _view20, _view11, _view21, _view12, _view22, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, _t46, _t47_inv, _t48, _t49, _t50, _t51_inv, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _view03 + Math.fma(_view02, _t44, Math.fma(_view00, _t45, _view01 * _t46)) * _t47_inv, _view03 + Math.fma(_view02, _t48, Math.fma(_view00, _t49, _view01 * _t50)) * _t51_inv, _view03 + Math.fma(_view02, _t52, Math.fma(_view00, _t53, _view01 * _t54)) * _t55_inv, _view03 + Math.fma(_view02, _t56, Math.fma(_view00, _t57, _view01 * _t58)) * _t59_inv, _view13 + Math.fma(_view12, _t28, Math.fma(_view10, _t29, _view11 * _t30)) * _t31_inv, _view13 + Math.fma(_view12, _t32, Math.fma(_view10, _t33, _view11 * _t34)) * _t35_inv, _view13 + Math.fma(_view12, _t36, Math.fma(_view10, _t37, _view11 * _t38)) * _t39_inv, _view13 + Math.fma(_view12, _t40, Math.fma(_view10, _t41, _view11 * _t42)) * _t43_inv);
    }

    /** Piece 5 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_rh_s5ade31d1_4(double[] dest, int destOffset, double _view10, double _view20, double _view11, double _view21, double _view12, double _view22, double _view13, double _view23, double _t28, double _t29, double _t30, double _t31_inv, double _t32, double _t33, double _t34, double _t35_inv, double _t36, double _t37, double _t38, double _t39_inv, double _t40, double _t41, double _t42, double _t43_inv, double _t44, double _t45, double _t46, double _t47_inv, double _t48, double _t49, double _t50, double _t51_inv, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t156, double _t157, double _t158, double _t159, double _t160, double _t161, double _t162, double _t163, double _t164, double _t165, double _t166, double _t167) {
        return orthoCrop_zo_rh_s5ade31d1_5(dest, destOffset, _view20, _view21, _view22, _view23, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _t160, _t161, _t162, _t163, _t164, _t165, _t166, _t167, _view13 + Math.fma(_view12, _t44, Math.fma(_view10, _t45, _view11 * _t46)) * _t47_inv, _view13 + Math.fma(_view12, _t48, Math.fma(_view10, _t49, _view11 * _t50)) * _t51_inv, _view13 + Math.fma(_view12, _t52, Math.fma(_view10, _t53, _view11 * _t54)) * _t55_inv, _view13 + Math.fma(_view12, _t56, Math.fma(_view10, _t57, _view11 * _t58)) * _t59_inv, _view23 + Math.fma(_view22, _t28, Math.fma(_view20, _t29, _view21 * _t30)) * _t31_inv, _view23 + Math.fma(_view22, _t32, Math.fma(_view20, _t33, _view21 * _t34)) * _t35_inv, _view23 + Math.fma(_view22, _t36, Math.fma(_view20, _t37, _view21 * _t38)) * _t39_inv, _view23 + Math.fma(_view22, _t40, Math.fma(_view20, _t41, _view21 * _t42)) * _t43_inv, _view23 + Math.fma(_view22, _t44, Math.fma(_view20, _t45, _view21 * _t46)) * _t47_inv, _view23 + Math.fma(_view22, _t48, Math.fma(_view20, _t49, _view21 * _t50)) * _t51_inv);
    }

    /** Piece 6 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_rh_s5ade31d1_5(double[] dest, int destOffset, double _view20, double _view21, double _view22, double _view23, double _t52, double _t53, double _t54, double _t55_inv, double _t56, double _t57, double _t58, double _t59_inv, double _t156, double _t157, double _t158, double _t159, double _t160, double _t161, double _t162, double _t163, double _t164, double _t165, double _t166, double _t167, double _t168, double _t169, double _t170, double _t171, double _t172, double _t173, double _t174, double _t175, double _t176, double _t177) {
        double _t178 = _view23 + Math.fma(_view22, _t52, Math.fma(_view20, _t53, _view21 * _t54)) * _t55_inv;
        double _t179 = _view23 + Math.fma(_view22, _t56, Math.fma(_view20, _t57, _view21 * _t58)) * _t59_inv;
        double _t216 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        double _t217 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        double _t218 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171);
        double _t219 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171);
        return orthoCrop_zo_rh_s5ade31d1_6(dest, destOffset, _t172, _t173, _t174, _t175, _t176, _t177, _t178, _t179, _t216, _t217, _t218, _t219, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179), 1.0 / (_t216 - _t217), 1.0 / (_t218 - _t219));
    }

    /** Piece 7 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] orthoCrop_zo_rh_s5ade31d1_6(double[] dest, int destOffset, double _t172, double _t173, double _t174, double _t175, double _t176, double _t177, double _t178, double _t179, double _t216, double _t217, double _t218, double _t219, double _t220, double _t222_inv, double _t223_inv) {
        double _t224_inv = 1.0 / (_t220 - Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179));
        dest[destOffset] = _t222_inv + _t222_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = _t223_inv + _t223_inv;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 0.0;
        dest[destOffset + 9] = 0.0;
        dest[destOffset + 10] = -_t224_inv;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = -(_t217 + _t216) * _t222_inv;
        dest[destOffset + 13] = -(_t219 + _t218) * _t223_inv;
        dest[destOffset + 14] = _t220 * _t224_inv;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    public static double[] orthoCrop_zo(double[] dest, int destOffset, double[] src, int srcOffset, double[] view, int viewOffset, double minZ, double maxZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.orthoCrop_zo_lh(dest, destOffset, src, srcOffset, view, viewOffset, minZ, maxZ); }
            default -> { return Double4x4OpsKernelsArray.orthoCrop_zo_rh(dest, destOffset, src, srcOffset, view, viewOffset, minZ, maxZ); }
        }
    }

    public static double[] perspective_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        double _t6 = Math.tan(0.5 * fovy);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp0 = near + near;
        double _t2_inv = 1.0 / (near - far);
        double _t9_inv = 1.0 / (aspect * _t6);
        double _t15, _t16;
        if (far == Double.POSITIVE_INFINITY) {
            _t15 = 1.0;
            _t16 = -_sp0;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _t15 = -1.0;
                _t16 = far + far;
            } else {
                _t15 = -(far + near) * _t2_inv;
                _t16 = _sp0 * far * _t2_inv;
            }
        }
        dest[destOffset] = _self00 * _t9_inv;
        dest[destOffset + 1] = _self10 * _t9_inv;
        dest[destOffset + 2] = _self20 * _t9_inv;
        dest[destOffset + 3] = _self30 * _t9_inv;
        return perspective_no_lh_saeb118bb_1(dest, destOffset, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, 1.0 / _t6, _t15, _t16);
    }

    /** Piece 2 of {@code perspective_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] perspective_no_lh_saeb118bb_1(double[] dest, int destOffset, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t6_inv, double _t15, double _t16) {
        dest[destOffset + 4] = _self01 * _t6_inv;
        dest[destOffset + 5] = _self11 * _t6_inv;
        dest[destOffset + 6] = _self21 * _t6_inv;
        dest[destOffset + 7] = _self31 * _t6_inv;
        dest[destOffset + 8] = Math.fma(_self02, _t15, _self03);
        dest[destOffset + 9] = Math.fma(_self12, _t15, _self13);
        dest[destOffset + 10] = Math.fma(_self22, _t15, _self23);
        dest[destOffset + 11] = Math.fma(_self32, _t15, _self33);
        dest[destOffset + 12] = _self02 * _t16;
        dest[destOffset + 13] = _self12 * _t16;
        dest[destOffset + 14] = _self22 * _t16;
        dest[destOffset + 15] = _self32 * _t16;
        return dest;
    }

    public static double[] perspective_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        double _t6 = Math.tan(0.5 * fovy);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp0 = near + near;
        double _t2_inv = 1.0 / (near - far);
        double _t6_inv = 1.0 / _t6;
        double _t9_inv = 1.0 / (aspect * _t6);
        double _t13, _t15;
        if (far == Double.POSITIVE_INFINITY) {
            _t13 = -1.0;
            _t15 = -_sp0;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _t13 = 1.0;
                _t15 = far + far;
            } else {
                _t13 = (far + near) * _t2_inv;
                _t15 = _sp0 * far * _t2_inv;
            }
        }
        dest[destOffset] = _self00 * _t9_inv;
        dest[destOffset + 1] = _self10 * _t9_inv;
        dest[destOffset + 2] = _self20 * _t9_inv;
        dest[destOffset + 3] = _self30 * _t9_inv;
        dest[destOffset + 4] = _self01 * _t6_inv;
        return perspective_no_rh_s498a5d9_1(dest, destOffset, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t6_inv, _t13, _t15);
    }

    /** Piece 2 of {@code perspective_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] perspective_no_rh_s498a5d9_1(double[] dest, int destOffset, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t6_inv, double _t13, double _t15) {
        dest[destOffset + 5] = _self11 * _t6_inv;
        dest[destOffset + 6] = _self21 * _t6_inv;
        dest[destOffset + 7] = _self31 * _t6_inv;
        dest[destOffset + 8] = Math.fma(_self02, _t13, -_self03);
        dest[destOffset + 9] = Math.fma(_self12, _t13, -_self13);
        dest[destOffset + 10] = Math.fma(_self22, _t13, -_self23);
        dest[destOffset + 11] = Math.fma(_self32, _t13, -_self33);
        dest[destOffset + 12] = _self02 * _t15;
        dest[destOffset + 13] = _self12 * _t15;
        dest[destOffset + 14] = _self22 * _t15;
        dest[destOffset + 15] = _self32 * _t15;
        return dest;
    }

    public static double[] perspective_no(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.perspective_no_lh(dest, destOffset, src, srcOffset, fovy, aspect, near, far); }
            default -> { return Double4x4OpsKernelsArray.perspective_no_rh(dest, destOffset, src, srcOffset, fovy, aspect, near, far); }
        }
    }

    public static double[] perspective_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        double _t3 = Math.tan(0.5 * fovy);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp0 = far / (near - far);
        double _t3_inv = 1.0 / _t3;
        double _t5_inv = 1.0 / (aspect * _t3);
        double _t10, _t11;
        if (far == Double.POSITIVE_INFINITY) {
            _t10 = 1.0;
            _t11 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _t10 = 0.0;
                _t11 = far;
            } else {
                _t10 = -_sp0;
                _t11 = _sp0 * near;
            }
        }
        dest[destOffset] = _self00 * _t5_inv;
        dest[destOffset + 1] = _self10 * _t5_inv;
        dest[destOffset + 2] = _self20 * _t5_inv;
        dest[destOffset + 3] = _self30 * _t5_inv;
        dest[destOffset + 4] = _self01 * _t3_inv;
        dest[destOffset + 5] = _self11 * _t3_inv;
        dest[destOffset + 6] = _self21 * _t3_inv;
        return perspective_zo_lh_sb0f4baa7_1(dest, destOffset, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t3_inv, _t10, _t11);
    }

    /** Piece 2 of {@code perspective_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] perspective_zo_lh_sb0f4baa7_1(double[] dest, int destOffset, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t3_inv, double _t10, double _t11) {
        dest[destOffset + 7] = _self31 * _t3_inv;
        dest[destOffset + 8] = Math.fma(_self02, _t10, _self03);
        dest[destOffset + 9] = Math.fma(_self12, _t10, _self13);
        dest[destOffset + 10] = Math.fma(_self22, _t10, _self23);
        dest[destOffset + 11] = Math.fma(_self32, _t10, _self33);
        dest[destOffset + 12] = _self02 * _t11;
        dest[destOffset + 13] = _self12 * _t11;
        dest[destOffset + 14] = _self22 * _t11;
        dest[destOffset + 15] = _self32 * _t11;
        return dest;
    }

    public static double[] perspective_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far) {
        double _t3 = Math.tan(0.5 * fovy);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp0 = far / (near - far);
        double _t3_inv = 1.0 / _t3;
        double _t5_inv = 1.0 / (aspect * _t3);
        double _t9, _t10;
        if (far == Double.POSITIVE_INFINITY) {
            _t9 = -1.0;
            _t10 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _t9 = 0.0;
                _t10 = far;
            } else {
                _t9 = _sp0;
                _t10 = _sp0 * near;
            }
        }
        dest[destOffset] = _self00 * _t5_inv;
        dest[destOffset + 1] = _self10 * _t5_inv;
        dest[destOffset + 2] = _self20 * _t5_inv;
        dest[destOffset + 3] = _self30 * _t5_inv;
        dest[destOffset + 4] = _self01 * _t3_inv;
        dest[destOffset + 5] = _self11 * _t3_inv;
        dest[destOffset + 6] = _self21 * _t3_inv;
        return perspective_zo_rh_s2b9fc57d_1(dest, destOffset, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t3_inv, _t9, _t10);
    }

    /** Piece 2 of {@code perspective_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] perspective_zo_rh_s2b9fc57d_1(double[] dest, int destOffset, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _t3_inv, double _t9, double _t10) {
        dest[destOffset + 7] = _self31 * _t3_inv;
        dest[destOffset + 8] = Math.fma(_self02, _t9, -_self03);
        dest[destOffset + 9] = Math.fma(_self12, _t9, -_self13);
        dest[destOffset + 10] = Math.fma(_self22, _t9, -_self23);
        dest[destOffset + 11] = Math.fma(_self32, _t9, -_self33);
        dest[destOffset + 12] = _self02 * _t10;
        dest[destOffset + 13] = _self12 * _t10;
        dest[destOffset + 14] = _self22 * _t10;
        dest[destOffset + 15] = _self32 * _t10;
        return dest;
    }

    public static double[] perspective_zo(double[] dest, int destOffset, double[] src, int srcOffset, double fovy, double aspect, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.perspective_zo_lh(dest, destOffset, src, srcOffset, fovy, aspect, near, far); }
            default -> { return Double4x4OpsKernelsArray.perspective_zo_rh(dest, destOffset, src, srcOffset, fovy, aspect, near, far); }
        }
    }

    public static double[] perspectiveFovRange_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp3 = near + near;
        double _t3_inv = 1.0 / (near - far);
        double _t8 = _t0 - _t1;
        double _t8_inv = 1.0 / _t8;
        double _sp0 = 2.0 / (aspect * _t8);
        double _t17, _t18;
        if (far == Double.POSITIVE_INFINITY) {
            _t17 = 1.0;
            _t18 = -_sp3;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _t17 = -1.0;
                _t18 = far + far;
            } else {
                _t17 = -(far + near) * _t3_inv;
                _t18 = _sp3 * far * _t3_inv;
            }
        }
        dest[destOffset] = _sp0 * _self00;
        return perspectiveFovRange_no_lh_s6eabd70_1(dest, destOffset, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t8_inv + _t8_inv, _t8_inv * (_t0 + _t1), _sp0, _t17, _t18);
    }

    /** Piece 2 of {@code perspectiveFovRange_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] perspectiveFovRange_no_lh_s6eabd70_1(double[] dest, int destOffset, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp1, double _sp2, double _sp0, double _t17, double _t18) {
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = Math.fma(_self02, _t17, _self03 - _self01 * _sp2);
        dest[destOffset + 9] = Math.fma(_self12, _t17, _self13 - _self11 * _sp2);
        dest[destOffset + 10] = Math.fma(_self22, _t17, _self23 - _self21 * _sp2);
        dest[destOffset + 11] = Math.fma(_self32, _t17, _self33 - _self31 * _sp2);
        dest[destOffset + 12] = _self02 * _t18;
        dest[destOffset + 13] = _self12 * _t18;
        dest[destOffset + 14] = _self22 * _t18;
        dest[destOffset + 15] = _self32 * _t18;
        return dest;
    }

    public static double[] perspectiveFovRange_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp3 = near + near;
        double _t3_inv = 1.0 / (near - far);
        double _t8 = _t0 - _t1;
        double _t8_inv = 1.0 / _t8;
        double _sp0 = 2.0 / (aspect * _t8);
        double _t15, _t17;
        if (far == Double.POSITIVE_INFINITY) {
            _t15 = -1.0;
            _t17 = -_sp3;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _t15 = 1.0;
                _t17 = far + far;
            } else {
                _t15 = (far + near) * _t3_inv;
                _t17 = _sp3 * far * _t3_inv;
            }
        }
        dest[destOffset] = _sp0 * _self00;
        return perspectiveFovRange_no_rh_s66cf86da_1(dest, destOffset, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t8_inv + _t8_inv, _t8_inv * (_t0 + _t1), _sp0, _t15, _t17);
    }

    /** Piece 2 of {@code perspectiveFovRange_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] perspectiveFovRange_no_rh_s66cf86da_1(double[] dest, int destOffset, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp1, double _sp2, double _sp0, double _t15, double _t17) {
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = Math.fma(_self02, _t15, _self01 * _sp2 - _self03);
        dest[destOffset + 9] = Math.fma(_self12, _t15, _self11 * _sp2 - _self13);
        dest[destOffset + 10] = Math.fma(_self22, _t15, _self21 * _sp2 - _self23);
        dest[destOffset + 11] = Math.fma(_self32, _t15, _self31 * _sp2 - _self33);
        dest[destOffset + 12] = _self02 * _t17;
        dest[destOffset + 13] = _self12 * _t17;
        dest[destOffset + 14] = _self22 * _t17;
        dest[destOffset + 15] = _self32 * _t17;
        return dest;
    }

    public static double[] perspectiveFovRange_no(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.perspectiveFovRange_no_lh(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far); }
            default -> { return Double4x4OpsKernelsArray.perspectiveFovRange_no_rh(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far); }
        }
    }

    public static double[] perspectiveFovRange_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp3 = far / (near - far);
        double _t4 = _t0 - _t1;
        double _t4_inv = 1.0 / _t4;
        double _sp0 = 2.0 / (aspect * _t4);
        double _t12, _t13;
        if (far == Double.POSITIVE_INFINITY) {
            _t12 = 1.0;
            _t13 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _t12 = 0.0;
                _t13 = far;
            } else {
                _t12 = -_sp3;
                _t13 = _sp3 * near;
            }
        }
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        return perspectiveFovRange_zo_lh_scecea9bc_1(dest, destOffset, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t4_inv + _t4_inv, _t4_inv * (_t0 + _t1), _t12, _t13);
    }

    /** Piece 2 of {@code perspectiveFovRange_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] perspectiveFovRange_zo_lh_scecea9bc_1(double[] dest, int destOffset, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp1, double _sp2, double _t12, double _t13) {
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = Math.fma(_self02, _t12, _self03 - _self01 * _sp2);
        dest[destOffset + 9] = Math.fma(_self12, _t12, _self13 - _self11 * _sp2);
        dest[destOffset + 10] = Math.fma(_self22, _t12, _self23 - _self21 * _sp2);
        dest[destOffset + 11] = Math.fma(_self32, _t12, _self33 - _self31 * _sp2);
        dest[destOffset + 12] = _self02 * _t13;
        dest[destOffset + 13] = _self12 * _t13;
        dest[destOffset + 14] = _self22 * _t13;
        dest[destOffset + 15] = _self32 * _t13;
        return dest;
    }

    public static double[] perspectiveFovRange_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far) {
        double _t0 = Math.tan(angleMax);
        double _t1 = Math.tan(angleMin);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp3 = far / (near - far);
        double _t4 = _t0 - _t1;
        double _t4_inv = 1.0 / _t4;
        double _sp0 = 2.0 / (aspect * _t4);
        double _t11, _t12;
        if (far == Double.POSITIVE_INFINITY) {
            _t11 = -1.0;
            _t12 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _t11 = 0.0;
                _t12 = far;
            } else {
                _t11 = _sp3;
                _t12 = _sp3 * near;
            }
        }
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        return perspectiveFovRange_zo_rh_sd279dece_1(dest, destOffset, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t4_inv + _t4_inv, _t4_inv * (_t0 + _t1), _t11, _t12);
    }

    /** Piece 2 of {@code perspectiveFovRange_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] perspectiveFovRange_zo_rh_sd279dece_1(double[] dest, int destOffset, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp1, double _sp2, double _t11, double _t12) {
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = Math.fma(_self02, _t11, _self01 * _sp2 - _self03);
        dest[destOffset + 9] = Math.fma(_self12, _t11, _self11 * _sp2 - _self13);
        dest[destOffset + 10] = Math.fma(_self22, _t11, _self21 * _sp2 - _self23);
        dest[destOffset + 11] = Math.fma(_self32, _t11, _self31 * _sp2 - _self33);
        dest[destOffset + 12] = _self02 * _t12;
        dest[destOffset + 13] = _self12 * _t12;
        dest[destOffset + 14] = _self22 * _t12;
        dest[destOffset + 15] = _self32 * _t12;
        return dest;
    }

    public static double[] perspectiveFovRange_zo(double[] dest, int destOffset, double[] src, int srcOffset, double angleMin, double angleMax, double aspect, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.perspectiveFovRange_zo_lh(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far); }
            default -> { return Double4x4OpsKernelsArray.perspectiveFovRange_zo_rh(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far); }
        }
    }

    public static double[] perspectiveFrustumSlice_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double near, double far) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self33 = src[srcOffset + 15];
        double _sp0 = near + near;
        double _t0_inv = 1.0 / (near - far);
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = -(far + near) * _t0_inv;
                dest[destOffset + 14] = _sp0 * far * _t0_inv;
            }
        }
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self21;
        dest[destOffset + 7] = _self31;
        return perspectiveFrustumSlice_no_lh_seddf0c6b_1(dest, destOffset, _self02, _self12, _self32, _self03, _self13, _self33);
    }

    /** Piece 2 of {@code perspectiveFrustumSlice_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] perspectiveFrustumSlice_no_lh_seddf0c6b_1(double[] dest, int destOffset, double _self02, double _self12, double _self32, double _self03, double _self13, double _self33) {
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] perspectiveFrustumSlice_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double near, double far) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self33 = src[srcOffset + 15];
        double _sp0 = near + near;
        double _t0_inv = 1.0 / (near - far);
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = (far + near) * _t0_inv;
                dest[destOffset + 14] = _sp0 * far * _t0_inv;
            }
        }
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self21;
        dest[destOffset + 7] = _self31;
        return perspectiveFrustumSlice_no_rh_sd20c04b5_1(dest, destOffset, _self02, _self12, _self32, _self03, _self13, _self33);
    }

    /** Piece 2 of {@code perspectiveFrustumSlice_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] perspectiveFrustumSlice_no_rh_sd20c04b5_1(double[] dest, int destOffset, double _self02, double _self12, double _self32, double _self03, double _self13, double _self33) {
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] perspectiveFrustumSlice_no(double[] dest, int destOffset, double[] src, int srcOffset, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.perspectiveFrustumSlice_no_lh(dest, destOffset, src, srcOffset, near, far); }
            default -> { return Double4x4OpsKernelsArray.perspectiveFrustumSlice_no_rh(dest, destOffset, src, srcOffset, near, far); }
        }
    }

    public static double[] perspectiveFrustumSlice_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double near, double far) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self33 = src[srcOffset + 15];
        double _sp0 = far / (near - far);
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self21;
        dest[destOffset + 7] = _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] perspectiveFrustumSlice_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double near, double far) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self33 = src[srcOffset + 15];
        double _sp0 = far / (near - far);
        if (far == Double.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self21;
        dest[destOffset + 7] = _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static double[] perspectiveFrustumSlice_zo(double[] dest, int destOffset, double[] src, int srcOffset, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.perspectiveFrustumSlice_zo_lh(dest, destOffset, src, srcOffset, near, far); }
            default -> { return Double4x4OpsKernelsArray.perspectiveFrustumSlice_zo_rh(dest, destOffset, src, srcOffset, near, far); }
        }
    }

    public static double[] perspectiveOffCenterFov_no_lh(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _sp4 = near + near;
        double _t5_inv = 1.0 / (near - far);
        double _t10_inv = 1.0 / (_t0 - _t1);
        double _t11_inv = 1.0 / (_t2 - _t3);
        double _t20, _t21;
        if (far == Double.POSITIVE_INFINITY) {
            _t20 = 1.0;
            _t21 = -_sp4;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _t20 = -1.0;
                _t21 = far + far;
            } else {
                _t20 = -(far + near) * _t5_inv;
                _t21 = _sp4 * far * _t5_inv;
            }
        }
        return perspectiveOffCenterFov_no_lh_se0a96532_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t10_inv + _t10_inv, _t11_inv + _t11_inv, _t10_inv * (_t1 + _t0), _t11_inv * (_t3 + _t2), _t20, _t21);
    }

    /** Piece 2 of {@code perspectiveOffCenterFov_no_lh}, split to fit the inline budget; reached only through it. */
    private static double[] perspectiveOffCenterFov_no_lh_se0a96532_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp0, double _sp1, double _sp2, double _sp3, double _t20, double _t21) {
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = Math.fma(_self02, _t20, _self03 - _self00 * _sp2 - _self01 * _sp3);
        dest[destOffset + 9] = Math.fma(_self12, _t20, _self13 - _self10 * _sp2 - _self11 * _sp3);
        dest[destOffset + 10] = Math.fma(_self22, _t20, _self23 - _self20 * _sp2 - _self21 * _sp3);
        dest[destOffset + 11] = Math.fma(_self32, _t20, _self33 - _self30 * _sp2 - _self31 * _sp3);
        dest[destOffset + 12] = _self02 * _t21;
        dest[destOffset + 13] = _self12 * _t21;
        dest[destOffset + 14] = _self22 * _t21;
        dest[destOffset + 15] = _self32 * _t21;
        return dest;
    }

    public static double[] perspectiveOffCenterFov_no_rh(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _sp4 = near + near;
        double _t5_inv = 1.0 / (near - far);
        double _t10_inv = 1.0 / (_t0 - _t1);
        double _t11_inv = 1.0 / (_t2 - _t3);
        double _t18, _t20;
        if (far == Double.POSITIVE_INFINITY) {
            _t18 = -1.0;
            _t20 = -_sp4;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _t18 = 1.0;
                _t20 = far + far;
            } else {
                _t18 = (far + near) * _t5_inv;
                _t20 = _sp4 * far * _t5_inv;
            }
        }
        return perspectiveOffCenterFov_no_rh_sf6899e64_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t10_inv + _t10_inv, _t11_inv + _t11_inv, _t10_inv * (_t1 + _t0), _t11_inv * (_t3 + _t2), _t18, _t20);
    }

    /** Piece 2 of {@code perspectiveOffCenterFov_no_rh}, split to fit the inline budget; reached only through it. */
    private static double[] perspectiveOffCenterFov_no_rh_sf6899e64_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp0, double _sp1, double _sp2, double _sp3, double _t18, double _t20) {
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = Math.fma(_self02, _t18, _self00 * _sp2 + _self01 * _sp3 - _self03);
        dest[destOffset + 9] = Math.fma(_self12, _t18, _self10 * _sp2 + _self11 * _sp3 - _self13);
        dest[destOffset + 10] = Math.fma(_self22, _t18, _self20 * _sp2 + _self21 * _sp3 - _self23);
        dest[destOffset + 11] = Math.fma(_self32, _t18, _self30 * _sp2 + _self31 * _sp3 - _self33);
        dest[destOffset + 12] = _self02 * _t20;
        dest[destOffset + 13] = _self12 * _t20;
        dest[destOffset + 14] = _self22 * _t20;
        dest[destOffset + 15] = _self32 * _t20;
        return dest;
    }

    public static double[] perspectiveOffCenterFov_no(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.perspectiveOffCenterFov_no_lh(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
            default -> { return Double4x4OpsKernelsArray.perspectiveOffCenterFov_no_rh(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
        }
    }

    public static double[] perspectiveOffCenterFov_zo_lh(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp4 = far / (near - far);
        double _t6_inv = 1.0 / (_t0 - _t1);
        double _sp0 = _t6_inv + _t6_inv;
        double _t7_inv = 1.0 / (_t2 - _t3);
        double _t15, _t16;
        if (far == Double.POSITIVE_INFINITY) {
            _t15 = 1.0;
            _t16 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _t15 = 0.0;
                _t16 = far;
            } else {
                _t15 = -_sp4;
                _t16 = _sp4 * near;
            }
        }
        dest[destOffset] = _sp0 * _self00;
        return perspectiveOffCenterFov_zo_lh_s8be310e_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _sp0, _t7_inv + _t7_inv, _t6_inv * (_t1 + _t0), _t7_inv * (_t3 + _t2), _t15, _t16);
    }

    /** Piece 2 of {@code perspectiveOffCenterFov_zo_lh}, split to fit the inline budget; reached only through it. */
    private static double[] perspectiveOffCenterFov_zo_lh_s8be310e_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp0, double _sp1, double _sp2, double _sp3, double _t15, double _t16) {
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = Math.fma(_self02, _t15, _self03 - _self00 * _sp2 - _self01 * _sp3);
        dest[destOffset + 9] = Math.fma(_self12, _t15, _self13 - _self10 * _sp2 - _self11 * _sp3);
        dest[destOffset + 10] = Math.fma(_self22, _t15, _self23 - _self20 * _sp2 - _self21 * _sp3);
        dest[destOffset + 11] = Math.fma(_self32, _t15, _self33 - _self30 * _sp2 - _self31 * _sp3);
        dest[destOffset + 12] = _self02 * _t16;
        dest[destOffset + 13] = _self12 * _t16;
        dest[destOffset + 14] = _self22 * _t16;
        dest[destOffset + 15] = _self32 * _t16;
        return dest;
    }

    public static double[] perspectiveOffCenterFov_zo_rh(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) {
        double _t0 = Math.tan(angleRight);
        double _t1 = Math.tan(angleLeft);
        double _t2 = Math.tan(angleUp);
        double _t3 = Math.tan(angleDown);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _sp4 = far / (near - far);
        double _t6_inv = 1.0 / (_t0 - _t1);
        double _sp0 = _t6_inv + _t6_inv;
        double _t7_inv = 1.0 / (_t2 - _t3);
        double _t14, _t15;
        if (far == Double.POSITIVE_INFINITY) {
            _t14 = -1.0;
            _t15 = -near;
        } else {
            if (near == Double.POSITIVE_INFINITY) {
                _t14 = 0.0;
                _t15 = far;
            } else {
                _t14 = _sp4;
                _t15 = _sp4 * near;
            }
        }
        dest[destOffset] = _sp0 * _self00;
        return perspectiveOffCenterFov_zo_rh_sa3b40128_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _sp0, _t7_inv + _t7_inv, _t6_inv * (_t1 + _t0), _t7_inv * (_t3 + _t2), _t14, _t15);
    }

    /** Piece 2 of {@code perspectiveOffCenterFov_zo_rh}, split to fit the inline budget; reached only through it. */
    private static double[] perspectiveOffCenterFov_zo_rh_sa3b40128_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self02, double _self12, double _self22, double _self32, double _self03, double _self13, double _self23, double _self33, double _sp0, double _sp1, double _sp2, double _sp3, double _t14, double _t15) {
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = Math.fma(_self02, _t14, _self00 * _sp2 + _self01 * _sp3 - _self03);
        dest[destOffset + 9] = Math.fma(_self12, _t14, _self10 * _sp2 + _self11 * _sp3 - _self13);
        dest[destOffset + 10] = Math.fma(_self22, _t14, _self20 * _sp2 + _self21 * _sp3 - _self23);
        dest[destOffset + 11] = Math.fma(_self32, _t14, _self30 * _sp2 + _self31 * _sp3 - _self33);
        dest[destOffset + 12] = _self02 * _t15;
        dest[destOffset + 13] = _self12 * _t15;
        dest[destOffset + 14] = _self22 * _t15;
        dest[destOffset + 15] = _self32 * _t15;
        return dest;
    }

    public static double[] perspectiveOffCenterFov_zo(double[] dest, int destOffset, double[] src, int srcOffset, double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Double4x4OpsKernelsArray.perspectiveOffCenterFov_zo_lh(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
            default -> { return Double4x4OpsKernelsArray.perspectiveOffCenterFov_zo_rh(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
        }
    }

    public static double[] project_no(double[] dest, int destOffset, double[] src, int srcOffset, double objX, double objY, double objZ, double viewportX, double viewportY, double viewportZ, double viewportW) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _t2_inv = 1.0 / Math.fma(objX, src[srcOffset + 3], Math.fma(objY, src[srcOffset + 7], Math.fma(objZ, src[srcOffset + 11], src[srcOffset + 15])));
        dest[destOffset] = Math.fma(0.5, viewportZ * (1.0 + Math.fma(objX, src[srcOffset], Math.fma(objY, src[srcOffset + 4], Math.fma(objZ, src[srcOffset + 8], src[srcOffset + 12]))) * _t2_inv), viewportX);
        dest[destOffset + 1] = Math.fma(0.5, viewportW * (1.0 + Math.fma(objX, _self10, Math.fma(objY, _self11, Math.fma(objZ, _self12, _self13))) * _t2_inv), viewportY);
        dest[destOffset + 2] = 0.5 * (1.0 + Math.fma(objX, _self20, Math.fma(objY, _self21, Math.fma(objZ, _self22, _self23))) * _t2_inv);
        return dest;
    }

    public static double[] project_zo(double[] dest, int destOffset, double[] src, int srcOffset, double objX, double objY, double objZ, double viewportX, double viewportY, double viewportZ, double viewportW) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _t2_inv = 1.0 / Math.fma(objX, src[srcOffset + 3], Math.fma(objY, src[srcOffset + 7], Math.fma(objZ, src[srcOffset + 11], src[srcOffset + 15])));
        dest[destOffset] = Math.fma(0.5, viewportZ * (1.0 + Math.fma(objX, src[srcOffset], Math.fma(objY, src[srcOffset + 4], Math.fma(objZ, src[srcOffset + 8], src[srcOffset + 12]))) * _t2_inv), viewportX);
        dest[destOffset + 1] = Math.fma(0.5, viewportW * (1.0 + Math.fma(objX, _self10, Math.fma(objY, _self11, Math.fma(objZ, _self12, _self13))) * _t2_inv), viewportY);
        dest[destOffset + 2] = Math.fma(objX, _self20, Math.fma(objY, _self21, Math.fma(objZ, _self22, _self23))) * _t2_inv;
        return dest;
    }

    public static double[] project_no(double[] dest, int destOffset, double[] src, int srcOffset, double[] obj, int objOffset, double[] viewport, int viewportOffset) {
        double _objx = obj[objOffset];
        double _objy = obj[objOffset + 1];
        double _objz = obj[objOffset + 2];
        double _viewporty = viewport[viewportOffset + 1];
        double _viewportw = viewport[viewportOffset + 3];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _t2_inv = 1.0 / Math.fma(_objx, src[srcOffset + 3], Math.fma(_objy, src[srcOffset + 7], Math.fma(_objz, src[srcOffset + 11], src[srcOffset + 15])));
        dest[destOffset] = Math.fma(0.5, viewport[viewportOffset + 2] * (1.0 + Math.fma(_objx, src[srcOffset], Math.fma(_objy, src[srcOffset + 4], Math.fma(_objz, src[srcOffset + 8], src[srcOffset + 12]))) * _t2_inv), viewport[viewportOffset]);
        return project_no_s7e22216e_1(dest, destOffset, _objx, _objy, _objz, _viewporty, _viewportw, _self10, _self20, _self11, _self21, _self12, _self22, _self13, _self23, _t2_inv);
    }

    /** Piece 2 of {@code project_no}, split to fit the inline budget; reached only through it. */
    private static double[] project_no_s7e22216e_1(double[] dest, int destOffset, double _objx, double _objy, double _objz, double _viewporty, double _viewportw, double _self10, double _self20, double _self11, double _self21, double _self12, double _self22, double _self13, double _self23, double _t2_inv) {
        dest[destOffset + 1] = Math.fma(0.5, _viewportw * (1.0 + Math.fma(_objx, _self10, Math.fma(_objy, _self11, Math.fma(_objz, _self12, _self13))) * _t2_inv), _viewporty);
        dest[destOffset + 2] = 0.5 * (1.0 + Math.fma(_objx, _self20, Math.fma(_objy, _self21, Math.fma(_objz, _self22, _self23))) * _t2_inv);
        return dest;
    }

    public static double[] project_zo(double[] dest, int destOffset, double[] src, int srcOffset, double[] obj, int objOffset, double[] viewport, int viewportOffset) {
        double _objx = obj[objOffset];
        double _objy = obj[objOffset + 1];
        double _objz = obj[objOffset + 2];
        double _viewporty = viewport[viewportOffset + 1];
        double _viewportw = viewport[viewportOffset + 3];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _t2_inv = 1.0 / Math.fma(_objx, src[srcOffset + 3], Math.fma(_objy, src[srcOffset + 7], Math.fma(_objz, src[srcOffset + 11], src[srcOffset + 15])));
        dest[destOffset] = Math.fma(0.5, viewport[viewportOffset + 2] * (1.0 + Math.fma(_objx, src[srcOffset], Math.fma(_objy, src[srcOffset + 4], Math.fma(_objz, src[srcOffset + 8], src[srcOffset + 12]))) * _t2_inv), viewport[viewportOffset]);
        dest[destOffset + 1] = Math.fma(0.5, _viewportw * (1.0 + Math.fma(_objx, _self10, Math.fma(_objy, _self11, Math.fma(_objz, _self12, _self13))) * _t2_inv), _viewporty);
        dest[destOffset + 2] = Math.fma(_objx, _self20, Math.fma(_objy, _self21, Math.fma(_objz, _self22, _self23))) * _t2_inv;
        return dest;
    }

    public static double[] unproject_no(double[] dest, int destOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double winCoordsZ, double viewportX, double viewportY, double viewportZ, double viewportW) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _t0 = -src[srcOffset + 15];
        double _t1 = -src[srcOffset + 7];
        double _t2 = -src[srcOffset + 11];
        double _t3 = -src[srcOffset + 3];
        double _t6 = Math.fma(2.0, winCoordsZ, -1.0);
        double _t11 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t12 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        return unproject_no_s79cc5d5d_1(dest, destOffset, _self20, _self21, _self22, Math.fma(_t2, _t6, _self22), Math.fma(_t1, _t6, _self21), Math.fma(_t0, _t6, _self23), Math.fma(_t3, _t6, _self20), Math.fma(_t0, _t11, _self03), Math.fma(_t1, _t12, _self11), Math.fma(_t2, _t12, _self12), Math.fma(_t1, _t11, _self01), Math.fma(_t0, _t12, _self13), Math.fma(_t2, _t11, _self02), Math.fma(_t3, _t12, _self10), Math.fma(_t3, _t11, _self00));
    }

    /** Piece 2 of {@code unproject_no}, split to fit the inline budget; reached only through it. */
    private static double[] unproject_no_s79cc5d5d_1(double[] dest, int destOffset, double _self20, double _self21, double _self22, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        double _t47 = Math.fma(_t18, _t13, -(_t19 * _t14));
        double _t48 = Math.fma(_t19, _t15, -(_t21 * _t13));
        double _t49 = Math.fma(_t18, _t15, -(_t21 * _t14));
        double _t50 = Math.fma(_t23, _t14, -(_t18 * _t16));
        double _t51 = Math.fma(_t23, _t13, -(_t19 * _t16));
        double _t53 = Math.fma(_t23, _t15, -(_t21 * _t16));
        double _t58 = Math.fma(_t22, Math.fma(_self21, _t23, -(_self20 * _t18)), Math.fma(_t24, Math.fma(_self22, _t18, -(_self21 * _t19)), -_t20 * Math.fma(_self22, _t23, -(_self20 * _t19))));
        double _t61 = Math.fma(_t22, _t50, Math.fma(_t24, _t47, -_t20 * _t51));
        double _t64_inv = 1.0 / (java.lang.Math.abs(_t61) <= java.lang.Math.abs(_t58) * 9.094947017729282E-13 ? Math.copySign(_t61, _t58) : _t61);
        dest[destOffset] = -Math.fma(_t17, _t47, Math.fma(_t20, _t48, -_t22 * _t49)) * _t64_inv;
        dest[destOffset + 1] = Math.fma(_t17, _t51, Math.fma(_t24, _t48, -_t22 * _t53)) * _t64_inv;
        dest[destOffset + 2] = -Math.fma(_t17, _t50, Math.fma(_t24, _t49, -(_t20 * _t53))) * _t64_inv;
        return dest;
    }

    public static double[] unproject_zo(double[] dest, int destOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double winCoordsZ, double viewportX, double viewportY, double viewportZ, double viewportW) {
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _t0 = -src[srcOffset + 15];
        double _t1 = -_self31;
        double _t2 = -_self32;
        double _t3 = -_self30;
        double _t17 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t18 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        return unproject_zo_s86f28f61_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], _t3, Math.fma(-0.5, _self31, _self21), Math.fma(-0.5, _self30, _self20), Math.fma(-0.5, _self32, _self22), Math.fma(_t2, winCoordsZ, _self22), Math.fma(_t1, winCoordsZ, _self21), Math.fma(_t0, winCoordsZ, _self23), Math.fma(_t3, winCoordsZ, _self20), _t17, _t18, Math.fma(_t0, _t17, _self03), Math.fma(_t1, _t18, _self11), Math.fma(_t2, _t18, _self12), Math.fma(_t1, _t17, _self01), Math.fma(_t0, _t18, _self13), Math.fma(_t2, _t17, _self02));
    }

    /** Piece 2 of {@code unproject_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unproject_zo_s86f28f61_1(double[] dest, int destOffset, double _self00, double _self10, double _t3, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        double _t25 = Math.fma(_t3, _t18, _self10);
        double _t26 = Math.fma(_t3, _t17, _self00);
        double _t48 = Math.fma(_t20, _t11, -(_t21 * _t12));
        double _t49 = Math.fma(_t21, _t13, -(_t23 * _t11));
        double _t50 = Math.fma(_t20, _t13, -(_t23 * _t12));
        double _t51 = Math.fma(_t25, _t12, -(_t20 * _t14));
        double _t52 = Math.fma(_t25, _t11, -(_t21 * _t14));
        double _t53 = Math.fma(_t25, _t13, -(_t23 * _t14));
        double _t60 = Math.fma(_t24, Math.fma(_t25, _t8, -(_t20 * _t9)), Math.fma(_t26, Math.fma(_t20, _t10, -(_t21 * _t8)), -_t22 * Math.fma(_t25, _t10, -(_t21 * _t9))));
        double _t63 = Math.fma(_t24, _t51, Math.fma(_t26, _t48, -_t22 * _t52));
        double _t66_inv = 1.0 / (java.lang.Math.abs(_t63) <= java.lang.Math.abs(_t60) * 9.094947017729282E-13 ? Math.copySign(_t63, _t60) : _t63);
        dest[destOffset] = -Math.fma(_t19, _t48, Math.fma(_t22, _t49, -_t24 * _t50)) * _t66_inv;
        dest[destOffset + 1] = Math.fma(_t19, _t52, Math.fma(_t26, _t49, -_t24 * _t53)) * _t66_inv;
        dest[destOffset + 2] = -Math.fma(_t19, _t51, Math.fma(_t26, _t50, -(_t22 * _t53))) * _t66_inv;
        return dest;
    }

    public static double[] unproject_no(double[] dest, int destOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset) {
        double _winCoordsy = winCoords[winCoordsOffset + 1];
        double _viewporty = viewport[viewportOffset + 1];
        double _viewportw = viewport[viewportOffset + 3];
        double _self20 = src[srcOffset + 2];
        double _self21 = src[srcOffset + 6];
        double _self22 = src[srcOffset + 10];
        double _self03 = src[srcOffset + 12];
        double _self23 = src[srcOffset + 14];
        double _t0 = -src[srcOffset + 15];
        double _t1 = -src[srcOffset + 7];
        double _t2 = -src[srcOffset + 11];
        double _t3 = -src[srcOffset + 3];
        double _t6 = Math.fma(2.0, winCoords[winCoordsOffset + 2], -1.0);
        double _t11 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        return unproject_no_s644e92b1_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], _self20, src[srcOffset + 4], src[srcOffset + 5], _self21, src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 13], _t0, _t1, _t2, _t3, _t11, 2.0 * (_winCoordsy - _viewporty) / _viewportw - 1.0, Math.fma(_t2, _t6, _self22), Math.fma(_t1, _t6, _self21), Math.fma(_t0, _t6, _self23), Math.fma(_t3, _t6, _self20), Math.fma(_t0, _t11, _self03));
    }

    /** Piece 2 of {@code unproject_no}, split to fit the inline budget; reached only through it. */
    private static double[] unproject_no_s644e92b1_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _self13, double _t0, double _t1, double _t2, double _t3, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17) {
        double _t18 = Math.fma(_t1, _t12, _self11);
        double _t19 = Math.fma(_t2, _t12, _self12);
        double _t20 = Math.fma(_t1, _t11, _self01);
        double _t21 = Math.fma(_t0, _t12, _self13);
        double _t22 = Math.fma(_t2, _t11, _self02);
        double _t23 = Math.fma(_t3, _t12, _self10);
        double _t24 = Math.fma(_t3, _t11, _self00);
        double _t47 = Math.fma(_t18, _t13, -(_t19 * _t14));
        double _t50 = Math.fma(_t23, _t14, -(_t18 * _t16));
        double _t51 = Math.fma(_t23, _t13, -(_t19 * _t16));
        double _t58 = Math.fma(_t22, Math.fma(_self21, _t23, -(_self20 * _t18)), Math.fma(_t24, Math.fma(_self22, _t18, -(_self21 * _t19)), -_t20 * Math.fma(_self22, _t23, -(_self20 * _t19))));
        double _t61 = Math.fma(_t22, _t50, Math.fma(_t24, _t47, -_t20 * _t51));
        return unproject_no_s644e92b1_2(dest, destOffset, _t17, _t20, _t22, _t24, _t47, Math.fma(_t19, _t15, -(_t21 * _t13)), Math.fma(_t18, _t15, -(_t21 * _t14)), _t50, _t51, Math.fma(_t23, _t15, -(_t21 * _t16)), 1.0 / (java.lang.Math.abs(_t61) <= java.lang.Math.abs(_t58) * 9.094947017729282E-13 ? Math.copySign(_t61, _t58) : _t61));
    }

    /**
     * Piece 3 of {@code unproject_no}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code unproject}; reached only through it.
     */
    private static double[] unproject_no_s644e92b1_2(double[] dest, int destOffset, double _t17, double _t20, double _t22, double _t24, double _t47, double _t48, double _t49, double _t50, double _t51, double _t53, double _t64_inv) {
        dest[destOffset] = -Math.fma(_t17, _t47, Math.fma(_t20, _t48, -_t22 * _t49)) * _t64_inv;
        dest[destOffset + 1] = Math.fma(_t17, _t51, Math.fma(_t24, _t48, -_t22 * _t53)) * _t64_inv;
        dest[destOffset + 2] = -Math.fma(_t17, _t50, Math.fma(_t24, _t49, -(_t20 * _t53))) * _t64_inv;
        return dest;
    }

    public static double[] unproject_zo(double[] dest, int destOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset) {
        double _winCoordsy = winCoords[winCoordsOffset + 1];
        double _winCoordsz = winCoords[winCoordsOffset + 2];
        double _viewporty = viewport[viewportOffset + 1];
        double _viewportw = viewport[viewportOffset + 3];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self23 = src[srcOffset + 14];
        double _t0 = -src[srcOffset + 15];
        double _t1 = -_self31;
        double _t2 = -_self32;
        double _t3 = -_self30;
        double _t17 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        return unproject_zo_s7c61692d_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 13], _t0, _t1, _t2, _t3, Math.fma(-0.5, _self31, _self21), Math.fma(-0.5, _self30, _self20), Math.fma(-0.5, _self32, _self22), Math.fma(_t2, _winCoordsz, _self22), Math.fma(_t1, _winCoordsz, _self21), Math.fma(_t0, _winCoordsz, _self23), Math.fma(_t3, _winCoordsz, _self20), _t17, 2.0 * (_winCoordsy - _viewporty) / _viewportw - 1.0, Math.fma(_t0, _t17, _self03));
    }

    /** Piece 2 of {@code unproject_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unproject_zo_s7c61692d_1(double[] dest, int destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _self13, double _t0, double _t1, double _t2, double _t3, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t17, double _t18, double _t19) {
        double _t20 = Math.fma(_t1, _t18, _self11);
        double _t21 = Math.fma(_t2, _t18, _self12);
        double _t22 = Math.fma(_t1, _t17, _self01);
        double _t23 = Math.fma(_t0, _t18, _self13);
        double _t24 = Math.fma(_t2, _t17, _self02);
        double _t25 = Math.fma(_t3, _t18, _self10);
        double _t26 = Math.fma(_t3, _t17, _self00);
        double _t48 = Math.fma(_t20, _t11, -(_t21 * _t12));
        double _t51 = Math.fma(_t25, _t12, -(_t20 * _t14));
        double _t52 = Math.fma(_t25, _t11, -(_t21 * _t14));
        double _t60 = Math.fma(_t24, Math.fma(_t25, _t8, -(_t20 * _t9)), Math.fma(_t26, Math.fma(_t20, _t10, -(_t21 * _t8)), -_t22 * Math.fma(_t25, _t10, -(_t21 * _t9))));
        double _t63 = Math.fma(_t24, _t51, Math.fma(_t26, _t48, -_t22 * _t52));
        return unproject_no_s644e92b1_2(dest, destOffset, _t19, _t22, _t24, _t26, _t48, Math.fma(_t21, _t13, -(_t23 * _t11)), Math.fma(_t20, _t13, -(_t23 * _t12)), _t51, _t52, Math.fma(_t25, _t13, -(_t23 * _t14)), 1.0 / (java.lang.Math.abs(_t63) <= java.lang.Math.abs(_t60) * 9.094947017729282E-13 ? Math.copySign(_t63, _t60) : _t63));
    }

    public static double[] unproject_no(double[] dest, int destOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double winCoordsZ, double viewportX, double viewportY, double viewportZ, double viewportW, double epsilon) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _t0 = -src[srcOffset + 15];
        double _t1 = -src[srcOffset + 7];
        double _t2 = -src[srcOffset + 11];
        double _t3 = -src[srcOffset + 3];
        double _t6 = Math.fma(2.0, winCoordsZ, -1.0);
        double _t11 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t12 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        return unproject_no_s7db4265e_1(dest, destOffset, epsilon, _self20, _self21, _self22, Math.fma(_t2, _t6, _self22), Math.fma(_t1, _t6, _self21), Math.fma(_t0, _t6, _self23), Math.fma(_t3, _t6, _self20), Math.fma(_t0, _t11, _self03), Math.fma(_t1, _t12, _self11), Math.fma(_t2, _t12, _self12), Math.fma(_t1, _t11, _self01), Math.fma(_t0, _t12, _self13), Math.fma(_t2, _t11, _self02), Math.fma(_t3, _t12, _self10), Math.fma(_t3, _t11, _self00));
    }

    /** Piece 2 of {@code unproject_no}, split to fit the inline budget; reached only through it. */
    private static double[] unproject_no_s7db4265e_1(double[] dest, int destOffset, double epsilon, double _self20, double _self21, double _self22, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        double _t47 = Math.fma(_t18, _t13, -(_t19 * _t14));
        double _t48 = Math.fma(_t19, _t15, -(_t21 * _t13));
        double _t49 = Math.fma(_t18, _t15, -(_t21 * _t14));
        double _t50 = Math.fma(_t23, _t14, -(_t18 * _t16));
        double _t51 = Math.fma(_t23, _t13, -(_t19 * _t16));
        double _t53 = Math.fma(_t23, _t15, -(_t21 * _t16));
        double _t58 = Math.fma(_t22, Math.fma(_self21, _t23, -(_self20 * _t18)), Math.fma(_t24, Math.fma(_self22, _t18, -(_self21 * _t19)), -_t20 * Math.fma(_self22, _t23, -(_self20 * _t19))));
        double _t61 = Math.fma(_t22, _t50, Math.fma(_t24, _t47, -_t20 * _t51));
        double _t64_inv = 1.0 / (java.lang.Math.abs(_t61) <= epsilon * java.lang.Math.abs(_t58) ? Math.copySign(_t61, _t58) : _t61);
        dest[destOffset] = -Math.fma(_t17, _t47, Math.fma(_t20, _t48, -_t22 * _t49)) * _t64_inv;
        dest[destOffset + 1] = Math.fma(_t17, _t51, Math.fma(_t24, _t48, -_t22 * _t53)) * _t64_inv;
        dest[destOffset + 2] = -Math.fma(_t17, _t50, Math.fma(_t24, _t49, -(_t20 * _t53))) * _t64_inv;
        return dest;
    }

    public static double[] unproject_zo(double[] dest, int destOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double winCoordsZ, double viewportX, double viewportY, double viewportZ, double viewportW, double epsilon) {
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _t0 = -src[srcOffset + 15];
        double _t1 = -_self31;
        double _t2 = -_self32;
        double _t3 = -_self30;
        double _t17 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t18 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        return unproject_zo_s50a1cd82_1(dest, destOffset, epsilon, src[srcOffset], src[srcOffset + 1], _t3, Math.fma(-0.5, _self31, _self21), Math.fma(-0.5, _self30, _self20), Math.fma(-0.5, _self32, _self22), Math.fma(_t2, winCoordsZ, _self22), Math.fma(_t1, winCoordsZ, _self21), Math.fma(_t0, winCoordsZ, _self23), Math.fma(_t3, winCoordsZ, _self20), _t17, _t18, Math.fma(_t0, _t17, _self03), Math.fma(_t1, _t18, _self11), Math.fma(_t2, _t18, _self12), Math.fma(_t1, _t17, _self01), Math.fma(_t0, _t18, _self13), Math.fma(_t2, _t17, _self02));
    }

    /** Piece 2 of {@code unproject_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unproject_zo_s50a1cd82_1(double[] dest, int destOffset, double epsilon, double _self00, double _self10, double _t3, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        double _t25 = Math.fma(_t3, _t18, _self10);
        double _t26 = Math.fma(_t3, _t17, _self00);
        double _t48 = Math.fma(_t20, _t11, -(_t21 * _t12));
        double _t49 = Math.fma(_t21, _t13, -(_t23 * _t11));
        double _t50 = Math.fma(_t20, _t13, -(_t23 * _t12));
        double _t51 = Math.fma(_t25, _t12, -(_t20 * _t14));
        double _t52 = Math.fma(_t25, _t11, -(_t21 * _t14));
        double _t53 = Math.fma(_t25, _t13, -(_t23 * _t14));
        double _t60 = Math.fma(_t24, Math.fma(_t25, _t8, -(_t20 * _t9)), Math.fma(_t26, Math.fma(_t20, _t10, -(_t21 * _t8)), -_t22 * Math.fma(_t25, _t10, -(_t21 * _t9))));
        double _t63 = Math.fma(_t24, _t51, Math.fma(_t26, _t48, -_t22 * _t52));
        double _t66_inv = 1.0 / (java.lang.Math.abs(_t63) <= epsilon * java.lang.Math.abs(_t60) ? Math.copySign(_t63, _t60) : _t63);
        dest[destOffset] = -Math.fma(_t19, _t48, Math.fma(_t22, _t49, -_t24 * _t50)) * _t66_inv;
        dest[destOffset + 1] = Math.fma(_t19, _t52, Math.fma(_t26, _t49, -_t24 * _t53)) * _t66_inv;
        dest[destOffset + 2] = -Math.fma(_t19, _t51, Math.fma(_t26, _t50, -(_t22 * _t53))) * _t66_inv;
        return dest;
    }

    public static double[] unproject_no(double[] dest, int destOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset, double epsilon) {
        double _winCoordsy = winCoords[winCoordsOffset + 1];
        double _viewporty = viewport[viewportOffset + 1];
        double _viewportw = viewport[viewportOffset + 3];
        double _self20 = src[srcOffset + 2];
        double _self21 = src[srcOffset + 6];
        double _self22 = src[srcOffset + 10];
        double _self03 = src[srcOffset + 12];
        double _self23 = src[srcOffset + 14];
        double _t0 = -src[srcOffset + 15];
        double _t1 = -src[srcOffset + 7];
        double _t2 = -src[srcOffset + 11];
        double _t3 = -src[srcOffset + 3];
        double _t6 = Math.fma(2.0, winCoords[winCoordsOffset + 2], -1.0);
        double _t11 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        return unproject_no_s2ad2f332_1(dest, destOffset, epsilon, src[srcOffset], src[srcOffset + 1], _self20, src[srcOffset + 4], src[srcOffset + 5], _self21, src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 13], _t0, _t1, _t2, _t3, _t11, 2.0 * (_winCoordsy - _viewporty) / _viewportw - 1.0, Math.fma(_t2, _t6, _self22), Math.fma(_t1, _t6, _self21), Math.fma(_t0, _t6, _self23), Math.fma(_t3, _t6, _self20), Math.fma(_t0, _t11, _self03));
    }

    /** Piece 2 of {@code unproject_no}, split to fit the inline budget; reached only through it. */
    private static double[] unproject_no_s2ad2f332_1(double[] dest, int destOffset, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _self13, double _t0, double _t1, double _t2, double _t3, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17) {
        double _t18 = Math.fma(_t1, _t12, _self11);
        double _t19 = Math.fma(_t2, _t12, _self12);
        double _t20 = Math.fma(_t1, _t11, _self01);
        double _t21 = Math.fma(_t0, _t12, _self13);
        double _t22 = Math.fma(_t2, _t11, _self02);
        double _t23 = Math.fma(_t3, _t12, _self10);
        double _t24 = Math.fma(_t3, _t11, _self00);
        double _t47 = Math.fma(_t18, _t13, -(_t19 * _t14));
        double _t50 = Math.fma(_t23, _t14, -(_t18 * _t16));
        double _t51 = Math.fma(_t23, _t13, -(_t19 * _t16));
        double _t58 = Math.fma(_t22, Math.fma(_self21, _t23, -(_self20 * _t18)), Math.fma(_t24, Math.fma(_self22, _t18, -(_self21 * _t19)), -_t20 * Math.fma(_self22, _t23, -(_self20 * _t19))));
        double _t61 = Math.fma(_t22, _t50, Math.fma(_t24, _t47, -_t20 * _t51));
        return unproject_no_s644e92b1_2(dest, destOffset, _t17, _t20, _t22, _t24, _t47, Math.fma(_t19, _t15, -(_t21 * _t13)), Math.fma(_t18, _t15, -(_t21 * _t14)), _t50, _t51, Math.fma(_t23, _t15, -(_t21 * _t16)), 1.0 / (java.lang.Math.abs(_t61) <= epsilon * java.lang.Math.abs(_t58) ? Math.copySign(_t61, _t58) : _t61));
    }

    public static double[] unproject_zo(double[] dest, int destOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset, double epsilon) {
        double _winCoordsy = winCoords[winCoordsOffset + 1];
        double _winCoordsz = winCoords[winCoordsOffset + 2];
        double _viewporty = viewport[viewportOffset + 1];
        double _viewportw = viewport[viewportOffset + 3];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self23 = src[srcOffset + 14];
        double _t0 = -src[srcOffset + 15];
        double _t1 = -_self31;
        double _t2 = -_self32;
        double _t3 = -_self30;
        double _t17 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        return unproject_zo_sa15cb9ce_1(dest, destOffset, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 13], _t0, _t1, _t2, _t3, Math.fma(-0.5, _self31, _self21), Math.fma(-0.5, _self30, _self20), Math.fma(-0.5, _self32, _self22), Math.fma(_t2, _winCoordsz, _self22), Math.fma(_t1, _winCoordsz, _self21), Math.fma(_t0, _winCoordsz, _self23), Math.fma(_t3, _winCoordsz, _self20), _t17, 2.0 * (_winCoordsy - _viewporty) / _viewportw - 1.0, Math.fma(_t0, _t17, _self03));
    }

    /** Piece 2 of {@code unproject_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unproject_zo_sa15cb9ce_1(double[] dest, int destOffset, double epsilon, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _self13, double _t0, double _t1, double _t2, double _t3, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t17, double _t18, double _t19) {
        double _t20 = Math.fma(_t1, _t18, _self11);
        double _t21 = Math.fma(_t2, _t18, _self12);
        double _t22 = Math.fma(_t1, _t17, _self01);
        double _t23 = Math.fma(_t0, _t18, _self13);
        double _t24 = Math.fma(_t2, _t17, _self02);
        double _t25 = Math.fma(_t3, _t18, _self10);
        double _t26 = Math.fma(_t3, _t17, _self00);
        double _t48 = Math.fma(_t20, _t11, -(_t21 * _t12));
        double _t51 = Math.fma(_t25, _t12, -(_t20 * _t14));
        double _t52 = Math.fma(_t25, _t11, -(_t21 * _t14));
        double _t60 = Math.fma(_t24, Math.fma(_t25, _t8, -(_t20 * _t9)), Math.fma(_t26, Math.fma(_t20, _t10, -(_t21 * _t8)), -_t22 * Math.fma(_t25, _t10, -(_t21 * _t9))));
        double _t63 = Math.fma(_t24, _t51, Math.fma(_t26, _t48, -_t22 * _t52));
        return unproject_no_s644e92b1_2(dest, destOffset, _t19, _t22, _t24, _t26, _t48, Math.fma(_t21, _t13, -(_t23 * _t11)), Math.fma(_t20, _t13, -(_t23 * _t12)), _t51, _t52, Math.fma(_t25, _t13, -(_t23 * _t14)), 1.0 / (java.lang.Math.abs(_t63) <= epsilon * java.lang.Math.abs(_t60) ? Math.copySign(_t63, _t60) : _t63));
    }

    public static double[] unprojectInv_no(double[] dest, int destOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double winCoordsZ, double viewportX, double viewportY, double viewportZ, double viewportW) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t2 = Math.fma(2.0, winCoordsZ, -1.0);
        double _t8 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t9 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        double _t12 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, _self33));
        double _t15 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(src[srcOffset + 11], _t2, _self33)));
        double _t18_inv = 1.0 / (java.lang.Math.abs(_t15) <= java.lang.Math.abs(_t12) * 9.094947017729282E-13 ? Math.copySign(_t15, _t12) : _t15);
        dest[destOffset] = Math.fma(src[srcOffset], _t8, Math.fma(src[srcOffset + 4], _t9, Math.fma(src[srcOffset + 8], _t2, src[srcOffset + 12]))) * _t18_inv;
        return unprojectInv_no_s85dd9c92_1(dest, destOffset, _self10, _self20, _self11, _self21, _self12, _self22, _self13, _self23, _t2, _t8, _t9, _t18_inv);
    }

    /**
     * Piece 2 of {@code unprojectInv_no}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code unprojectInv}; reached only through it.
     */
    private static double[] unprojectInv_no_s85dd9c92_1(double[] dest, int destOffset, double _self10, double _self20, double _self11, double _self21, double _self12, double _self22, double _self13, double _self23, double _t2, double _t8, double _t9, double _t18_inv) {
        dest[destOffset + 1] = Math.fma(_self10, _t8, Math.fma(_self11, _t9, Math.fma(_self12, _t2, _self13))) * _t18_inv;
        dest[destOffset + 2] = Math.fma(_self20, _t8, Math.fma(_self21, _t9, Math.fma(_self22, _t2, _self23))) * _t18_inv;
        return dest;
    }

    public static double[] unprojectInv_zo(double[] dest, int destOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double winCoordsZ, double viewportX, double viewportY, double viewportZ, double viewportW) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t8 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t9 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        double _t12 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(0.5, _self32, _self33)));
        double _t13 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(_self32, winCoordsZ, _self33)));
        double _t18_inv = 1.0 / (java.lang.Math.abs(_t13) <= java.lang.Math.abs(_t12) * 9.094947017729282E-13 ? Math.copySign(_t13, _t12) : _t13);
        dest[destOffset] = Math.fma(src[srcOffset], _t8, Math.fma(src[srcOffset + 4], _t9, Math.fma(src[srcOffset + 8], winCoordsZ, src[srcOffset + 12]))) * _t18_inv;
        return unprojectInv_zo_sa2c6d356_1(dest, destOffset, winCoordsZ, _self10, _self20, _self11, _self21, _self12, _self22, _self13, _self23, _t8, _t9, _t18_inv);
    }

    /**
     * Piece 2 of {@code unprojectInv_zo}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code unprojectInv}; reached only through it.
     */
    private static double[] unprojectInv_zo_sa2c6d356_1(double[] dest, int destOffset, double winCoordsZ, double _self10, double _self20, double _self11, double _self21, double _self12, double _self22, double _self13, double _self23, double _t8, double _t9, double _t18_inv) {
        dest[destOffset + 1] = Math.fma(_self10, _t8, Math.fma(_self11, _t9, Math.fma(_self12, winCoordsZ, _self13))) * _t18_inv;
        dest[destOffset + 2] = Math.fma(_self20, _t8, Math.fma(_self21, _t9, Math.fma(_self22, winCoordsZ, _self23))) * _t18_inv;
        return dest;
    }

    public static double[] unprojectInv_no(double[] dest, int destOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset) {
        double _self30 = src[srcOffset + 3];
        double _self31 = src[srcOffset + 7];
        double _self33 = src[srcOffset + 15];
        double _t2 = Math.fma(2.0, winCoords[winCoordsOffset + 2], -1.0);
        double _t8 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        double _t9 = 2.0 * (winCoords[winCoordsOffset + 1] - viewport[viewportOffset + 1]) / viewport[viewportOffset + 3] - 1.0;
        double _t12 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, _self33));
        double _t15 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(src[srcOffset + 11], _t2, _self33)));
        return unprojectInv_no_se67dd11c_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], _t2, _t8, _t9, 1.0 / (java.lang.Math.abs(_t15) <= java.lang.Math.abs(_t12) * 9.094947017729282E-13 ? Math.copySign(_t15, _t12) : _t15));
    }

    /**
     * Piece 2 of {@code unprojectInv_no}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code unprojectInv}; reached only through it.
     */
    private static double[] unprojectInv_no_se67dd11c_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _self03, double _self13, double _self23, double _t2, double _t8, double _t9, double _t18_inv) {
        dest[destOffset] = Math.fma(_self00, _t8, Math.fma(_self01, _t9, Math.fma(_self02, _t2, _self03))) * _t18_inv;
        dest[destOffset + 1] = Math.fma(_self10, _t8, Math.fma(_self11, _t9, Math.fma(_self12, _t2, _self13))) * _t18_inv;
        dest[destOffset + 2] = Math.fma(_self20, _t8, Math.fma(_self21, _t9, Math.fma(_self22, _t2, _self23))) * _t18_inv;
        return dest;
    }

    public static double[] unprojectInv_zo(double[] dest, int destOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset) {
        double _winCoordsz = winCoords[winCoordsOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self31 = src[srcOffset + 7];
        double _self32 = src[srcOffset + 11];
        double _self33 = src[srcOffset + 15];
        double _t8 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        double _t9 = 2.0 * (winCoords[winCoordsOffset + 1] - viewport[viewportOffset + 1]) / viewport[viewportOffset + 3] - 1.0;
        double _t12 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(0.5, _self32, _self33)));
        double _t13 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(_self32, _winCoordsz, _self33)));
        return unprojectInv_zo_s1e323d68_1(dest, destOffset, _winCoordsz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], _t8, _t9, 1.0 / (java.lang.Math.abs(_t13) <= java.lang.Math.abs(_t12) * 9.094947017729282E-13 ? Math.copySign(_t13, _t12) : _t13));
    }

    /**
     * Piece 2 of {@code unprojectInv_zo}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code unprojectInv}; reached only through it.
     */
    private static double[] unprojectInv_zo_s1e323d68_1(double[] dest, int destOffset, double _winCoordsz, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _self03, double _self13, double _self23, double _t8, double _t9, double _t18_inv) {
        dest[destOffset] = Math.fma(_self00, _t8, Math.fma(_self01, _t9, Math.fma(_self02, _winCoordsz, _self03))) * _t18_inv;
        dest[destOffset + 1] = Math.fma(_self10, _t8, Math.fma(_self11, _t9, Math.fma(_self12, _winCoordsz, _self13))) * _t18_inv;
        dest[destOffset + 2] = Math.fma(_self20, _t8, Math.fma(_self21, _t9, Math.fma(_self22, _winCoordsz, _self23))) * _t18_inv;
        return dest;
    }

    public static double[] unprojectInv_no(double[] dest, int destOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double winCoordsZ, double viewportX, double viewportY, double viewportZ, double viewportW, double epsilon) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t2 = Math.fma(2.0, winCoordsZ, -1.0);
        double _t8 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t9 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        double _t12 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, _self33));
        double _t15 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(src[srcOffset + 11], _t2, _self33)));
        double _t18_inv = 1.0 / (java.lang.Math.abs(_t15) <= epsilon * java.lang.Math.abs(_t12) ? Math.copySign(_t15, _t12) : _t15);
        dest[destOffset] = Math.fma(src[srcOffset], _t8, Math.fma(src[srcOffset + 4], _t9, Math.fma(src[srcOffset + 8], _t2, src[srcOffset + 12]))) * _t18_inv;
        return unprojectInv_no_s85dd9c92_1(dest, destOffset, _self10, _self20, _self11, _self21, _self12, _self22, _self13, _self23, _t2, _t8, _t9, _t18_inv);
    }

    public static double[] unprojectInv_zo(double[] dest, int destOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double winCoordsZ, double viewportX, double viewportY, double viewportZ, double viewportW, double epsilon) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t8 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t9 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        double _t12 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(0.5, _self32, _self33)));
        double _t13 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(_self32, winCoordsZ, _self33)));
        double _t18_inv = 1.0 / (java.lang.Math.abs(_t13) <= epsilon * java.lang.Math.abs(_t12) ? Math.copySign(_t13, _t12) : _t13);
        dest[destOffset] = Math.fma(src[srcOffset], _t8, Math.fma(src[srcOffset + 4], _t9, Math.fma(src[srcOffset + 8], winCoordsZ, src[srcOffset + 12]))) * _t18_inv;
        return unprojectInv_zo_sa2c6d356_1(dest, destOffset, winCoordsZ, _self10, _self20, _self11, _self21, _self12, _self22, _self13, _self23, _t8, _t9, _t18_inv);
    }

    public static double[] unprojectInv_no(double[] dest, int destOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset, double epsilon) {
        double _self30 = src[srcOffset + 3];
        double _self31 = src[srcOffset + 7];
        double _self33 = src[srcOffset + 15];
        double _t2 = Math.fma(2.0, winCoords[winCoordsOffset + 2], -1.0);
        double _t8 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        double _t9 = 2.0 * (winCoords[winCoordsOffset + 1] - viewport[viewportOffset + 1]) / viewport[viewportOffset + 3] - 1.0;
        double _t12 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, _self33));
        double _t15 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(src[srcOffset + 11], _t2, _self33)));
        return unprojectInv_no_se67dd11c_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], _t2, _t8, _t9, 1.0 / (java.lang.Math.abs(_t15) <= epsilon * java.lang.Math.abs(_t12) ? Math.copySign(_t15, _t12) : _t15));
    }

    public static double[] unprojectInv_zo(double[] dest, int destOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset, double epsilon) {
        double _winCoordsz = winCoords[winCoordsOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self31 = src[srcOffset + 7];
        double _self32 = src[srcOffset + 11];
        double _self33 = src[srcOffset + 15];
        double _t8 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        double _t9 = 2.0 * (winCoords[winCoordsOffset + 1] - viewport[viewportOffset + 1]) / viewport[viewportOffset + 3] - 1.0;
        double _t12 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(0.5, _self32, _self33)));
        double _t13 = Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(_self32, _winCoordsz, _self33)));
        return unprojectInv_zo_s1e323d68_1(dest, destOffset, _winCoordsz, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], _t8, _t9, 1.0 / (java.lang.Math.abs(_t13) <= epsilon * java.lang.Math.abs(_t12) ? Math.copySign(_t13, _t12) : _t13));
    }

    public static double[] unprojectInvRay_no(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double viewportX, double viewportY, double viewportZ, double viewportW) {
        double _self00 = src[srcOffset];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t4 = _self03 + _self02;
        double _t14 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t15 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        double _t24 = Math.fma(_self30, _t14, Math.fma(_self31, _t15, _self33 - _self32));
        double _t24_inv = 1.0 / _t24;
        double _t25 = Math.fma(_self30, _t14, Math.fma(_self31, _t15, _self33 + _self32));
        return unprojectInvRay_no_s1b35333c_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _self00, src[srcOffset + 1], src[srcOffset + 2], _self01, src[srcOffset + 5], src[srcOffset + 6], _t4, _self03 - _self02, _self13 + _self12, _self13 - _self12, _self23 + _self22, _self23 - _self22, _t14, _t15, _t24, _t24_inv, _t25, _t24_inv * _t25, 1.0 / _t25, Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t4)));
    }

    /** Piece 2 of {@code unprojectInvRay_no}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectInvRay_no_s1b35333c_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t14, double _t15, double _t24, double _t24_inv, double _t25, double _sp0, double _t25_inv, double _t26) {
        double _t27 = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t5));
        double _t28 = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t6));
        double _t29 = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t7));
        double _t30 = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t8));
        double _t31 = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t9));
        double _t32 = java.lang.Math.abs(_t24);
        double _t33 = java.lang.Math.abs(_t25);
        double _t34 = _t33 * 9.094947017729282E-13;
        if (_t32 <= _t34) {
            rayOrigin[rayOriginOffset] = _t26 * _t25_inv;
            rayOrigin[rayOriginOffset + 1] = _t28 * _t25_inv;
            rayOrigin[rayOriginOffset + 2] = _t30 * _t25_inv;
        } else {
            rayOrigin[rayOriginOffset] = _t27 * _t24_inv;
            rayOrigin[rayOriginOffset + 1] = _t29 * _t24_inv;
            rayOrigin[rayOriginOffset + 2] = _t31 * _t24_inv;
        }
        return unprojectInvRay_no_s1b35333c_2(rayOrigin, rayDir, rayDirOffset, _self00, _self10, _self20, _self01, _self11, _self21, _t4, _t5, _t6, _t7, _t8, _t9, _t14, _t15, _t24, _t25, _sp0, _t25_inv, _t26, _t27, _t28, _t29, _t30, _t31, _t32, _t34, 1.0 / (_t33 <= _t32 * 9.094947017729282E-13 ? _t24 : _t25));
    }

    /**
     * Piece 3 of {@code unprojectInvRay_no}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code unprojectInvRay}; reached only through it.
     */
    private static double[] unprojectInvRay_no_s1b35333c_2(double[] rayOrigin, double[] rayDir, int rayDirOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t14, double _t15, double _t24, double _t25, double _sp0, double _t25_inv, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t34, double _t36_inv) {
        if (_t32 <= _t34) {
            rayDir[rayDirOffset] = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t5 - _t26 * _t24 / _t25)) * _t25_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t7 - _t28 * _t24 / _t25)) * _t25_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t9 - _t30 * _t24 / _t25)) * _t25_inv;
        } else {
            rayDir[rayDirOffset] = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t4 - _t27 * _sp0)) * _t36_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t6 - _t29 * _sp0)) * _t36_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t8 - _t31 * _sp0)) * _t36_inv;
        }
        return rayOrigin;
    }

    public static double[] unprojectInvRay_zo(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double viewportX, double viewportY, double viewportZ, double viewportW) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t10 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t11 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        double _t20 = Math.fma(_self30, _t10, Math.fma(_self31, _t11, _self33));
        return unprojectInvRay_zo_sefd984a8_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, src[srcOffset + 11], _self03, _self13, _self23, _self33, _self03 + _self02, _self13 + _self12, _self23 + _self22, _t10, _t11, _t20, 1.0 / _t20, Math.fma(_self00, _t10, Math.fma(_self01, _t11, _self03)), Math.fma(_self10, _t10, Math.fma(_self11, _t11, _self13)), Math.fma(_self20, _t10, Math.fma(_self21, _t11, _self23)));
    }

    /** Piece 2 of {@code unprojectInvRay_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectInvRay_zo_sefd984a8_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self32, double _self03, double _self13, double _self23, double _self33, double _t3, double _t4, double _t5, double _t10, double _t11, double _t20, double _t20_inv, double _t21, double _t22, double _t23) {
        double _t24 = java.lang.Math.abs(_t20);
        double _t25 = Math.fma(_self30, _t10, Math.fma(_self31, _t11, _self33 + _self32));
        double _t25_inv = 1.0 / _t25;
        double _t26 = Math.fma(_self00, _t10, Math.fma(_self01, _t11, _t3));
        double _t27 = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _t4));
        double _t28 = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _t5));
        double _t30 = java.lang.Math.abs(_t25);
        double _t31 = _t30 * 9.094947017729282E-13;
        if (_t24 <= _t31) {
            rayOrigin[rayOriginOffset] = _t26 * _t25_inv;
            rayOrigin[rayOriginOffset + 1] = _t27 * _t25_inv;
            rayOrigin[rayOriginOffset + 2] = _t28 * _t25_inv;
        } else {
            rayOrigin[rayOriginOffset] = _t21 * _t20_inv;
            rayOrigin[rayOriginOffset + 1] = _t22 * _t20_inv;
            rayOrigin[rayOriginOffset + 2] = _t23 * _t20_inv;
        }
        return unprojectInvRay_zo_sefd984a8_2(rayOrigin, rayDir, rayDirOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self03, _self13, _self23, _t3, _t4, _t5, _t10, _t11, _t20, _t21, _t22, _t23, _t24, _t25, _t20_inv * _t25, _t25_inv, _t26, _t27, _t28, _t31, 1.0 / (_t30 <= _t24 * 9.094947017729282E-13 ? _t20 : _t25));
    }

    /**
     * Piece 3 of {@code unprojectInvRay_zo}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code unprojectInvRay}; reached only through it.
     */
    private static double[] unprojectInvRay_zo_sefd984a8_2(double[] rayOrigin, double[] rayDir, int rayDirOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self03, double _self13, double _self23, double _t3, double _t4, double _t5, double _t10, double _t11, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _sp0, double _t25_inv, double _t26, double _t27, double _t28, double _t31, double _t32_inv) {
        if (_t24 <= _t31) {
            rayDir[rayDirOffset] = Math.fma(_self00, _t10, Math.fma(_self01, _t11, _self03 - _t26 * _t20 / _t25)) * _t25_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _self13 - _t27 * _t20 / _t25)) * _t25_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _self23 - _t28 * _t20 / _t25)) * _t25_inv;
        } else {
            rayDir[rayDirOffset] = Math.fma(_self00, _t10, Math.fma(_self01, _t11, _t3 - _t21 * _sp0)) * _t32_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _t4 - _t22 * _sp0)) * _t32_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _t5 - _t23 * _sp0)) * _t32_inv;
        }
        return rayOrigin;
    }

    public static double[] unprojectInvRay_no(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset) {
        double _self30 = src[srcOffset + 3];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t14 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        double _t15 = 2.0 * (winCoords[winCoordsOffset + 1] - viewport[viewportOffset + 1]) / viewport[viewportOffset + 3] - 1.0;
        double _t24 = Math.fma(_self30, _t14, Math.fma(_self31, _t15, _self33 - _self32));
        return unprojectInvRay_no_s2feb9839_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], _self30, src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], _self31, _self32, _self33, _self03 + _self02, _self03 - _self02, _self13 + _self12, _self13 - _self12, _self23 + _self22, _self23 - _self22, _t14, _t15, _t24, 1.0 / _t24);
    }

    /** Piece 2 of {@code unprojectInvRay_no}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectInvRay_no_s2feb9839_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self32, double _self33, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t14, double _t15, double _t24, double _t24_inv) {
        double _t25 = Math.fma(_self30, _t14, Math.fma(_self31, _t15, _self33 + _self32));
        double _t25_inv = 1.0 / _t25;
        double _t26 = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t4));
        double _t27 = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t5));
        double _t28 = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t6));
        double _t29 = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t7));
        double _t30 = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t8));
        double _t31 = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t9));
        double _t32 = java.lang.Math.abs(_t24);
        double _t33 = java.lang.Math.abs(_t25);
        double _t34 = _t33 * 9.094947017729282E-13;
        if (_t32 <= _t34) {
            rayOrigin[rayOriginOffset] = _t26 * _t25_inv;
            rayOrigin[rayOriginOffset + 1] = _t28 * _t25_inv;
            rayOrigin[rayOriginOffset + 2] = _t30 * _t25_inv;
        } else {
            rayOrigin[rayOriginOffset] = _t27 * _t24_inv;
            rayOrigin[rayOriginOffset + 1] = _t29 * _t24_inv;
            rayOrigin[rayOriginOffset + 2] = _t31 * _t24_inv;
        }
        return unprojectInvRay_no_s1b35333c_2(rayOrigin, rayDir, rayDirOffset, _self00, _self10, _self20, _self01, _self11, _self21, _t4, _t5, _t6, _t7, _t8, _t9, _t14, _t15, _t24, _t25, _t24_inv * _t25, _t25_inv, _t26, _t27, _t28, _t29, _t30, _t31, _t32, _t34, 1.0 / (_t33 <= _t32 * 9.094947017729282E-13 ? _t24 : _t25));
    }

    public static double[] unprojectInvRay_zo(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset) {
        double _self00 = src[srcOffset];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t10 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        double _t11 = 2.0 * (winCoords[winCoordsOffset + 1] - viewport[viewportOffset + 1]) / viewport[viewportOffset + 3] - 1.0;
        double _t20 = Math.fma(_self30, _t10, Math.fma(_self31, _t11, _self33));
        return unprojectInvRay_zo_s429cc61d_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _self00, src[srcOffset + 1], src[srcOffset + 2], _self30, _self01, src[srcOffset + 5], src[srcOffset + 6], _self31, src[srcOffset + 11], _self03, _self13, _self23, _self33, _self03 + _self02, _self13 + _self12, _self23 + _self22, _t10, _t11, _t20, 1.0 / _t20, Math.fma(_self00, _t10, Math.fma(_self01, _t11, _self03)));
    }

    /** Piece 2 of {@code unprojectInvRay_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectInvRay_zo_s429cc61d_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self32, double _self03, double _self13, double _self23, double _self33, double _t3, double _t4, double _t5, double _t10, double _t11, double _t20, double _t20_inv, double _t21) {
        double _t22 = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _self13));
        double _t23 = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _self23));
        double _t24 = java.lang.Math.abs(_t20);
        double _t25 = Math.fma(_self30, _t10, Math.fma(_self31, _t11, _self33 + _self32));
        double _t25_inv = 1.0 / _t25;
        double _t26 = Math.fma(_self00, _t10, Math.fma(_self01, _t11, _t3));
        double _t27 = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _t4));
        double _t28 = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _t5));
        double _t30 = java.lang.Math.abs(_t25);
        double _t31 = _t30 * 9.094947017729282E-13;
        if (_t24 <= _t31) {
            rayOrigin[rayOriginOffset] = _t26 * _t25_inv;
            rayOrigin[rayOriginOffset + 1] = _t27 * _t25_inv;
            rayOrigin[rayOriginOffset + 2] = _t28 * _t25_inv;
        } else {
            rayOrigin[rayOriginOffset] = _t21 * _t20_inv;
            rayOrigin[rayOriginOffset + 1] = _t22 * _t20_inv;
            rayOrigin[rayOriginOffset + 2] = _t23 * _t20_inv;
        }
        return unprojectInvRay_zo_sefd984a8_2(rayOrigin, rayDir, rayDirOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self03, _self13, _self23, _t3, _t4, _t5, _t10, _t11, _t20, _t21, _t22, _t23, _t24, _t25, _t20_inv * _t25, _t25_inv, _t26, _t27, _t28, _t31, 1.0 / (_t30 <= _t24 * 9.094947017729282E-13 ? _t20 : _t25));
    }

    public static double[] unprojectInvRay_no(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double viewportX, double viewportY, double viewportZ, double viewportW, double epsilon) {
        double _self00 = src[srcOffset];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t4 = _self03 + _self02;
        double _t14 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t15 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        double _t24 = Math.fma(_self30, _t14, Math.fma(_self31, _t15, _self33 - _self32));
        double _t24_inv = 1.0 / _t24;
        double _t25 = Math.fma(_self30, _t14, Math.fma(_self31, _t15, _self33 + _self32));
        return unprojectInvRay_no_s710e01cf_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, epsilon, _self00, src[srcOffset + 1], src[srcOffset + 2], _self01, src[srcOffset + 5], src[srcOffset + 6], _t4, _self03 - _self02, _self13 + _self12, _self13 - _self12, _self23 + _self22, _self23 - _self22, _t14, _t15, _t24, _t24_inv, _t25, _t24_inv * _t25, 1.0 / _t25, Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t4)));
    }

    /** Piece 2 of {@code unprojectInvRay_no}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectInvRay_no_s710e01cf_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double epsilon, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t14, double _t15, double _t24, double _t24_inv, double _t25, double _sp0, double _t25_inv, double _t26) {
        double _t27 = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t5));
        double _t28 = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t6));
        double _t29 = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t7));
        double _t30 = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t8));
        double _t31 = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t9));
        double _t32 = java.lang.Math.abs(_t24);
        double _t33 = java.lang.Math.abs(_t25);
        double _t34 = epsilon * _t33;
        if (_t32 <= _t34) {
            rayOrigin[rayOriginOffset] = _t26 * _t25_inv;
            rayOrigin[rayOriginOffset + 1] = _t28 * _t25_inv;
            rayOrigin[rayOriginOffset + 2] = _t30 * _t25_inv;
        } else {
            rayOrigin[rayOriginOffset] = _t27 * _t24_inv;
            rayOrigin[rayOriginOffset + 1] = _t29 * _t24_inv;
            rayOrigin[rayOriginOffset + 2] = _t31 * _t24_inv;
        }
        return unprojectInvRay_no_s1b35333c_2(rayOrigin, rayDir, rayDirOffset, _self00, _self10, _self20, _self01, _self11, _self21, _t4, _t5, _t6, _t7, _t8, _t9, _t14, _t15, _t24, _t25, _sp0, _t25_inv, _t26, _t27, _t28, _t29, _t30, _t31, _t32, _t34, 1.0 / (_t33 <= epsilon * _t32 ? _t24 : _t25));
    }

    public static double[] unprojectInvRay_zo(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double viewportX, double viewportY, double viewportZ, double viewportW, double epsilon) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t10 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t11 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        double _t20 = Math.fma(_self30, _t10, Math.fma(_self31, _t11, _self33));
        return unprojectInvRay_zo_sa7ffaafb_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, epsilon, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, src[srcOffset + 11], _self03, _self13, _self23, _self33, _self03 + _self02, _self13 + _self12, _self23 + _self22, _t10, _t11, _t20, 1.0 / _t20, Math.fma(_self00, _t10, Math.fma(_self01, _t11, _self03)), Math.fma(_self10, _t10, Math.fma(_self11, _t11, _self13)), Math.fma(_self20, _t10, Math.fma(_self21, _t11, _self23)));
    }

    /** Piece 2 of {@code unprojectInvRay_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectInvRay_zo_sa7ffaafb_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double epsilon, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self32, double _self03, double _self13, double _self23, double _self33, double _t3, double _t4, double _t5, double _t10, double _t11, double _t20, double _t20_inv, double _t21, double _t22, double _t23) {
        double _t24 = java.lang.Math.abs(_t20);
        double _t25 = Math.fma(_self30, _t10, Math.fma(_self31, _t11, _self33 + _self32));
        double _t25_inv = 1.0 / _t25;
        double _t26 = Math.fma(_self00, _t10, Math.fma(_self01, _t11, _t3));
        double _t27 = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _t4));
        double _t28 = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _t5));
        double _t30 = java.lang.Math.abs(_t25);
        double _t31 = epsilon * _t30;
        if (_t24 <= _t31) {
            rayOrigin[rayOriginOffset] = _t26 * _t25_inv;
            rayOrigin[rayOriginOffset + 1] = _t27 * _t25_inv;
            rayOrigin[rayOriginOffset + 2] = _t28 * _t25_inv;
        } else {
            rayOrigin[rayOriginOffset] = _t21 * _t20_inv;
            rayOrigin[rayOriginOffset + 1] = _t22 * _t20_inv;
            rayOrigin[rayOriginOffset + 2] = _t23 * _t20_inv;
        }
        return unprojectInvRay_zo_sefd984a8_2(rayOrigin, rayDir, rayDirOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self03, _self13, _self23, _t3, _t4, _t5, _t10, _t11, _t20, _t21, _t22, _t23, _t24, _t25, _t20_inv * _t25, _t25_inv, _t26, _t27, _t28, _t31, 1.0 / (_t30 <= epsilon * _t24 ? _t20 : _t25));
    }

    public static double[] unprojectInvRay_no(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset, double epsilon) {
        double _self30 = src[srcOffset + 3];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t14 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        double _t15 = 2.0 * (winCoords[winCoordsOffset + 1] - viewport[viewportOffset + 1]) / viewport[viewportOffset + 3] - 1.0;
        double _t24 = Math.fma(_self30, _t14, Math.fma(_self31, _t15, _self33 - _self32));
        return unprojectInvRay_no_s1831c32a_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], _self30, src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], _self31, _self32, _self33, _self03 + _self02, _self03 - _self02, _self13 + _self12, _self13 - _self12, _self23 + _self22, _self23 - _self22, _t14, _t15, _t24, 1.0 / _t24);
    }

    /** Piece 2 of {@code unprojectInvRay_no}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectInvRay_no_s1831c32a_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double epsilon, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self32, double _self33, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t14, double _t15, double _t24, double _t24_inv) {
        double _t25 = Math.fma(_self30, _t14, Math.fma(_self31, _t15, _self33 + _self32));
        double _t25_inv = 1.0 / _t25;
        double _t26 = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t4));
        double _t27 = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t5));
        double _t28 = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t6));
        double _t29 = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t7));
        double _t30 = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t8));
        double _t31 = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t9));
        double _t32 = java.lang.Math.abs(_t24);
        double _t33 = java.lang.Math.abs(_t25);
        double _t34 = epsilon * _t33;
        if (_t32 <= _t34) {
            rayOrigin[rayOriginOffset] = _t26 * _t25_inv;
            rayOrigin[rayOriginOffset + 1] = _t28 * _t25_inv;
            rayOrigin[rayOriginOffset + 2] = _t30 * _t25_inv;
        } else {
            rayOrigin[rayOriginOffset] = _t27 * _t24_inv;
            rayOrigin[rayOriginOffset + 1] = _t29 * _t24_inv;
            rayOrigin[rayOriginOffset + 2] = _t31 * _t24_inv;
        }
        return unprojectInvRay_no_s1b35333c_2(rayOrigin, rayDir, rayDirOffset, _self00, _self10, _self20, _self01, _self11, _self21, _t4, _t5, _t6, _t7, _t8, _t9, _t14, _t15, _t24, _t25, _t24_inv * _t25, _t25_inv, _t26, _t27, _t28, _t29, _t30, _t31, _t32, _t34, 1.0 / (_t33 <= epsilon * _t32 ? _t24 : _t25));
    }

    public static double[] unprojectInvRay_zo(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset, double epsilon) {
        double _self30 = src[srcOffset + 3];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self03 = src[srcOffset + 12];
        double _self13 = src[srcOffset + 13];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t10 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        double _t11 = 2.0 * (winCoords[winCoordsOffset + 1] - viewport[viewportOffset + 1]) / viewport[viewportOffset + 3] - 1.0;
        double _t20 = Math.fma(_self30, _t10, Math.fma(_self31, _t11, _self33));
        return unprojectInvRay_zo_s42daa79e_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], _self30, src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], _self31, src[srcOffset + 11], _self03, _self13, _self23, _self33, _self03 + _self02, _self13 + _self12, _self23 + _self22, _t10, _t11, _t20, 1.0 / _t20);
    }

    /** Piece 2 of {@code unprojectInvRay_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectInvRay_zo_s42daa79e_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double epsilon, double _self00, double _self10, double _self20, double _self30, double _self01, double _self11, double _self21, double _self31, double _self32, double _self03, double _self13, double _self23, double _self33, double _t3, double _t4, double _t5, double _t10, double _t11, double _t20, double _t20_inv) {
        double _t21 = Math.fma(_self00, _t10, Math.fma(_self01, _t11, _self03));
        double _t22 = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _self13));
        double _t23 = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _self23));
        double _t24 = java.lang.Math.abs(_t20);
        double _t25 = Math.fma(_self30, _t10, Math.fma(_self31, _t11, _self33 + _self32));
        double _t25_inv = 1.0 / _t25;
        double _t26 = Math.fma(_self00, _t10, Math.fma(_self01, _t11, _t3));
        double _t27 = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _t4));
        double _t28 = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _t5));
        double _t30 = java.lang.Math.abs(_t25);
        double _t31 = epsilon * _t30;
        if (_t24 <= _t31) {
            rayOrigin[rayOriginOffset] = _t26 * _t25_inv;
            rayOrigin[rayOriginOffset + 1] = _t27 * _t25_inv;
            rayOrigin[rayOriginOffset + 2] = _t28 * _t25_inv;
        } else {
            rayOrigin[rayOriginOffset] = _t21 * _t20_inv;
            rayOrigin[rayOriginOffset + 1] = _t22 * _t20_inv;
            rayOrigin[rayOriginOffset + 2] = _t23 * _t20_inv;
        }
        return unprojectInvRay_zo_sefd984a8_2(rayOrigin, rayDir, rayDirOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self03, _self13, _self23, _t3, _t4, _t5, _t10, _t11, _t20, _t21, _t22, _t23, _t24, _t25, _t20_inv * _t25, _t25_inv, _t26, _t27, _t28, _t31, 1.0 / (_t30 <= epsilon * _t24 ? _t20 : _t25));
    }

    public static double[] unprojectRay_no(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double viewportX, double viewportY, double viewportZ, double viewportW) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = -_self32;
        double _t1 = -_self30;
        double _t2 = -_self31;
        double _t18 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t19 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        return unprojectRay_no_sb5dd281d_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, src[srcOffset + 12], src[srcOffset + 13], -_self33, _self21 + _self31, _self20 + _self30, _self22 + _self32, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self23 - _self33, _self23 + _self33, _t18, _t19, Math.fma(_t0, _t18, _self02), Math.fma(_t1, _t19, _self10), Math.fma(_t2, _t19, _self11), Math.fma(_t1, _t18, _self00), Math.fma(_t0, _t19, _self12), Math.fma(_t2, _t18, _self01));
    }

    /** Piece 2 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_no_sb5dd281d_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _self03, double _self13, double _t3, double _t5, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t26 = Math.fma(_t3, _t18, _self03);
        double _t27 = Math.fma(_t3, _t19, _self13);
        double _t56 = Math.fma(_t5, _t21, -_t7 * _t22);
        double _t57 = Math.fma(_t8, _t22, -(_t5 * _t24));
        double _t58 = Math.fma(_t8, _t21, -(_t7 * _t24));
        double _t59 = Math.fma(_t21, _t9, _t22 * -_t10);
        double _t60 = Math.fma(_t22, _t11, _t24 * -_t9);
        double _t61 = Math.fma(_t21, _t11, _t24 * -_t10);
        double _t92 = Math.fma(_t20, _t56, Math.fma(_t23, _t57, -(_t25 * _t58)));
        double _t92_inv = 1.0 / _t92;
        double _t93 = Math.fma(_t20, _t59, Math.fma(_t23, _t60, -(_t25 * _t61)));
        return unprojectRay_no_sb5dd281d_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t20, _t23, _t25, _t26, -_t25, -_t26, -_t20, -_t23, _t56, _t57, _t58, _t59, _t60, _t61, Math.fma(_t24, _t12, -(_t27 * _t11)), Math.fma(_t22, _t12, _t27 * -_t9), Math.fma(_t13, _t24, -(_t8 * _t27)), Math.fma(_t13, _t22, -(_t5 * _t27)), Math.fma(_t21, _t12, -(_t27 * _t10)), Math.fma(_t13, _t21, -_t7 * _t27), _t92, _t92_inv, _t93, _t92_inv * _t93, 1.0 / _t93);
    }

    /** Piece 3 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_no_sb5dd281d_2(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _t20, double _t23, double _t25, double _t26, double _t28, double _t29, double _t30, double _t31, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t66, double _t67, double _t92, double _t92_inv, double _t93, double _sp0, double _t93_inv) {
        double _t100 = java.lang.Math.abs(_t92);
        double _t101 = java.lang.Math.abs(_t93);
        return unprojectRay_no_sb5dd281d_3(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t20, _t23, _t25, _t26, _t28, _t29, _t30, _t31, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t92, _t92_inv, _t93, _sp0, _t93_inv, Math.fma(_t26, _t60, Math.fma(_t25, _t62, -(_t20 * _t63))), Math.fma(_t26, _t57, Math.fma(_t25, _t64, -(_t20 * _t65))), Math.fma(_t26, _t61, Math.fma(_t23, _t62, -(_t20 * _t66))), Math.fma(_t26, _t58, Math.fma(_t23, _t64, -(_t20 * _t67))), Math.fma(_t26, _t59, Math.fma(_t23, _t63, -(_t25 * _t66))), Math.fma(_t26, _t56, Math.fma(_t23, _t65, -(_t25 * _t67))), _t100, _t101 * 9.094947017729282E-13, 1.0 / (_t101 <= _t100 * 9.094947017729282E-13 ? _t92 : _t93));
    }

    /**
     * Piece 4 of {@code unprojectRay_no}, split to fit the inline budget. Shared by 4 identical
     * private paths of {@code unprojectRay}; reached only through it.
     */
    private static double[] unprojectRay_no_sb5dd281d_3(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _t20, double _t23, double _t25, double _t26, double _t28, double _t29, double _t30, double _t31, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t66, double _t67, double _t92, double _t92_inv, double _t93, double _sp0, double _t93_inv, double _t94, double _t95, double _t96, double _t97, double _t98, double _t99, double _t100, double _t102, double _t104_inv) {
        if (_t100 <= _t102) {
            rayOrigin[rayOriginOffset] = -(_t94 * _t93_inv);
            rayOrigin[rayOriginOffset + 1] = _t96 * _t93_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t98 * _t93_inv);
        } else {
            rayOrigin[rayOriginOffset] = -(_t95 * _t92_inv);
            rayOrigin[rayOriginOffset + 1] = _t97 * _t92_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t99 * _t92_inv);
        }
        if (_t100 <= _t102) {
            rayDir[rayDirOffset] = Math.fma(_t28, _t64, Math.fma(_t20, _t65, Math.fma(_t29, _t57, _t92 * _t94 / _t93))) * _t93_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t23, _t64, Math.fma(_t30, _t67, Math.fma(_t26, _t58, -(_t92 * _t96 / _t93)))) * _t93_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t31, _t65, Math.fma(_t25, _t67, Math.fma(_t29, _t56, _t92 * _t98 / _t93))) * _t93_inv;
        } else {
            rayDir[rayDirOffset] = Math.fma(_t28, _t62, Math.fma(_t20, _t63, Math.fma(_t29, _t60, _sp0 * _t95))) * _t104_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t23, _t62, Math.fma(_t30, _t66, Math.fma(_t26, _t61, -(_t97 * _sp0)))) * _t104_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t31, _t63, Math.fma(_t25, _t66, Math.fma(_t29, _t59, _t99 * _sp0))) * _t104_inv;
        }
        return rayOrigin;
    }

    public static double[] unprojectRay_zo(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double viewportX, double viewportY, double viewportZ, double viewportW) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = -_self32;
        double _t1 = -_self30;
        double _t2 = -_self31;
        double _t3 = -_self33;
        double _t14 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t15 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        return unprojectRay_zo_sfb275c61_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _self20, _self21, _self22, src[srcOffset + 13], _self23, _t3, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self23 - _self33, _t15, Math.fma(_t0, _t14, _self02), Math.fma(_t1, _t15, _self10), Math.fma(_t2, _t15, _self11), Math.fma(_t1, _t14, _self00), Math.fma(_t0, _t15, _self12), Math.fma(_t2, _t14, _self01), Math.fma(_t3, _t14, _self03));
    }

    /** Piece 2 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_zo_sfb275c61_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _self20, double _self21, double _self22, double _self13, double _self23, double _t3, double _t6, double _t7, double _t8, double _t9, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22) {
        double _t23 = Math.fma(_t3, _t15, _self13);
        double _t52 = Math.fma(_self21, _t17, -_self20 * _t18);
        double _t53 = Math.fma(_self22, _t18, -(_self21 * _t20));
        double _t54 = Math.fma(_self22, _t17, -(_self20 * _t20));
        double _t55 = Math.fma(_self23, _t20, -(_self22 * _t23));
        double _t56 = Math.fma(_self23, _t18, -(_self21 * _t23));
        double _t57 = Math.fma(_self23, _t17, -_self20 * _t23);
        double _t88 = Math.fma(_t16, _t52, Math.fma(_t19, _t53, -(_t21 * _t54)));
        return unprojectRay_zo_sfb275c61_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t16, _t19, _t21, _t22, -_t21, -_t22, -_t16, -_t19, _t52, _t53, _t54, _t55, _t56, _t57, Math.fma(_t17, _t6, _t18 * -_t7), Math.fma(_t18, _t8, _t20 * -_t6), Math.fma(_t17, _t8, _t20 * -_t7), Math.fma(_t20, _t9, -(_t23 * _t8)), Math.fma(_t18, _t9, _t23 * -_t6), Math.fma(_t17, _t9, -(_t23 * _t7)), _t88, 1.0 / _t88, Math.fma(_t22, _t53, Math.fma(_t21, _t55, -(_t16 * _t56))), Math.fma(_t22, _t54, Math.fma(_t19, _t55, -(_t16 * _t57))), Math.fma(_t22, _t52, Math.fma(_t19, _t56, -(_t21 * _t57))));
    }

    /** Piece 3 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_zo_sfb275c61_2(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _t16, double _t19, double _t21, double _t22, double _t24, double _t25, double _t26, double _t27, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t88, double _t88_inv, double _t89, double _t90, double _t91) {
        double _t92 = java.lang.Math.abs(_t88);
        double _t94 = Math.fma(_t16, _t58, Math.fma(_t19, _t59, -(_t21 * _t60)));
        double _t94_inv = 1.0 / _t94;
        double _t95 = Math.fma(_t22, _t59, Math.fma(_t21, _t61, -(_t16 * _t62)));
        double _t96 = Math.fma(_t22, _t60, Math.fma(_t19, _t61, -(_t16 * _t63)));
        double _t97 = Math.fma(_t22, _t58, Math.fma(_t19, _t62, -(_t21 * _t63)));
        double _t98 = java.lang.Math.abs(_t94);
        double _t99 = _t98 * 9.094947017729282E-13;
        if (_t92 <= _t99) {
            rayOrigin[rayOriginOffset] = -(_t95 * _t94_inv);
            rayOrigin[rayOriginOffset + 1] = _t96 * _t94_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t97 * _t94_inv);
        } else {
            rayOrigin[rayOriginOffset] = -(_t89 * _t88_inv);
            rayOrigin[rayOriginOffset + 1] = _t90 * _t88_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t91 * _t88_inv);
        }
        return unprojectRay_zo_sfb275c61_3(rayOrigin, rayDir, rayDirOffset, _t16, _t19, _t21, _t22, _t24, _t25, _t26, _t27, _t52, _t53, _t54, _t55, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t88, _t89, _t90, _t91, _t92, _t94, _t88_inv * _t94, _t94_inv, _t95, _t96, _t97, _t99, 1.0 / (_t98 <= _t92 * 9.094947017729282E-13 ? _t88 : _t94));
    }

    /**
     * Piece 4 of {@code unprojectRay_zo}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code unprojectRay}; reached only through it.
     */
    private static double[] unprojectRay_zo_sfb275c61_3(double[] rayOrigin, double[] rayDir, int rayDirOffset, double _t16, double _t19, double _t21, double _t22, double _t24, double _t25, double _t26, double _t27, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t88, double _t89, double _t90, double _t91, double _t92, double _t94, double _sp0, double _t94_inv, double _t95, double _t96, double _t97, double _t99, double _t100_inv) {
        if (_t92 <= _t99) {
            rayDir[rayDirOffset] = Math.fma(_t24, _t55, Math.fma(_t16, _t56, Math.fma(_t25, _t53, _t88 * _t95 / _t94))) * _t94_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t19, _t55, Math.fma(_t26, _t57, Math.fma(_t22, _t54, -(_t88 * _t96 / _t94)))) * _t94_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t27, _t56, Math.fma(_t21, _t57, Math.fma(_t25, _t52, _t88 * _t97 / _t94))) * _t94_inv;
        } else {
            rayDir[rayDirOffset] = Math.fma(_t24, _t61, Math.fma(_t16, _t62, Math.fma(_t25, _t59, _sp0 * _t89))) * _t100_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t19, _t61, Math.fma(_t26, _t63, Math.fma(_t22, _t60, -(_t90 * _sp0)))) * _t100_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t27, _t62, Math.fma(_t21, _t63, Math.fma(_t25, _t58, _t91 * _sp0))) * _t100_inv;
        }
        return rayOrigin;
    }

    public static double[] unprojectRay_no(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset) {
        double _winCoordsy = winCoords[winCoordsOffset + 1];
        double _viewporty = viewport[viewportOffset + 1];
        double _viewportw = viewport[viewportOffset + 3];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = -_self32;
        double _t18 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        return unprojectRay_no_s7e1c4e04_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 9], src[srcOffset + 12], src[srcOffset + 13], _t0, -_self30, -_self31, -_self33, _self21 + _self31, _self20 + _self30, _self22 + _self32, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self23 - _self33, _self23 + _self33, _t18, 2.0 * (_winCoordsy - _viewporty) / _viewportw - 1.0, Math.fma(_t0, _t18, _self02));
    }

    /** Piece 2 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_no_s7e1c4e04_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _self00, double _self10, double _self01, double _self11, double _self12, double _self03, double _self13, double _t0, double _t1, double _t2, double _t3, double _t5, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20) {
        double _t21 = Math.fma(_t1, _t19, _self10);
        double _t22 = Math.fma(_t2, _t19, _self11);
        double _t23 = Math.fma(_t1, _t18, _self00);
        double _t24 = Math.fma(_t0, _t19, _self12);
        double _t25 = Math.fma(_t2, _t18, _self01);
        double _t26 = Math.fma(_t3, _t18, _self03);
        double _t27 = Math.fma(_t3, _t19, _self13);
        double _t56 = Math.fma(_t5, _t21, -_t7 * _t22);
        double _t57 = Math.fma(_t8, _t22, -(_t5 * _t24));
        double _t58 = Math.fma(_t8, _t21, -(_t7 * _t24));
        double _t92 = Math.fma(_t20, _t56, Math.fma(_t23, _t57, -(_t25 * _t58)));
        return unprojectRay_no_s7e1c4e04_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t20, _t23, _t25, _t26, -_t25, -_t26, -_t20, -_t23, _t56, _t57, _t58, Math.fma(_t21, _t9, _t22 * -_t10), Math.fma(_t22, _t11, _t24 * -_t9), Math.fma(_t21, _t11, _t24 * -_t10), Math.fma(_t24, _t12, -(_t27 * _t11)), Math.fma(_t22, _t12, _t27 * -_t9), Math.fma(_t13, _t24, -(_t8 * _t27)), Math.fma(_t13, _t22, -(_t5 * _t27)), Math.fma(_t21, _t12, -(_t27 * _t10)), Math.fma(_t13, _t21, -_t7 * _t27), _t92, 1.0 / _t92);
    }

    /** Piece 3 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_no_s7e1c4e04_2(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _t20, double _t23, double _t25, double _t26, double _t28, double _t29, double _t30, double _t31, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t66, double _t67, double _t92, double _t92_inv) {
        double _t93 = Math.fma(_t20, _t59, Math.fma(_t23, _t60, -(_t25 * _t61)));
        double _t100 = java.lang.Math.abs(_t92);
        double _t101 = java.lang.Math.abs(_t93);
        return unprojectRay_no_sb5dd281d_3(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t20, _t23, _t25, _t26, _t28, _t29, _t30, _t31, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t92, _t92_inv, _t93, _t92_inv * _t93, 1.0 / _t93, Math.fma(_t26, _t60, Math.fma(_t25, _t62, -(_t20 * _t63))), Math.fma(_t26, _t57, Math.fma(_t25, _t64, -(_t20 * _t65))), Math.fma(_t26, _t61, Math.fma(_t23, _t62, -(_t20 * _t66))), Math.fma(_t26, _t58, Math.fma(_t23, _t64, -(_t20 * _t67))), Math.fma(_t26, _t59, Math.fma(_t23, _t63, -(_t25 * _t66))), Math.fma(_t26, _t56, Math.fma(_t23, _t65, -(_t25 * _t67))), _t100, _t101 * 9.094947017729282E-13, 1.0 / (_t101 <= _t100 * 9.094947017729282E-13 ? _t92 : _t93));
    }

    public static double[] unprojectRay_zo(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = -_self32;
        double _t1 = -_self30;
        double _t14 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        double _t15 = 2.0 * (winCoords[winCoordsOffset + 1] - viewport[viewportOffset + 1]) / viewport[viewportOffset + 3] - 1.0;
        return unprojectRay_zo_s8c3b6a0_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, src[srcOffset], _self20, src[srcOffset + 4], src[srcOffset + 5], _self21, src[srcOffset + 9], _self22, src[srcOffset + 12], src[srcOffset + 13], _self23, _t0, _t1, -_self31, -_self33, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self23 - _self33, _t14, _t15, Math.fma(_t0, _t14, _self02), Math.fma(_t1, _t15, _self10));
    }

    /** Piece 2 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_zo_s8c3b6a0_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _self00, double _self20, double _self01, double _self11, double _self21, double _self12, double _self22, double _self03, double _self13, double _self23, double _t0, double _t1, double _t2, double _t3, double _t6, double _t7, double _t8, double _t9, double _t14, double _t15, double _t16, double _t17) {
        double _t18 = Math.fma(_t2, _t15, _self11);
        double _t19 = Math.fma(_t1, _t14, _self00);
        double _t20 = Math.fma(_t0, _t15, _self12);
        double _t21 = Math.fma(_t2, _t14, _self01);
        double _t22 = Math.fma(_t3, _t14, _self03);
        double _t23 = Math.fma(_t3, _t15, _self13);
        double _t52 = Math.fma(_self21, _t17, -_self20 * _t18);
        double _t53 = Math.fma(_self22, _t18, -(_self21 * _t20));
        double _t54 = Math.fma(_self22, _t17, -(_self20 * _t20));
        double _t88 = Math.fma(_t16, _t52, Math.fma(_t19, _t53, -(_t21 * _t54)));
        return unprojectRay_zo_s8c3b6a0_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t16, _t19, _t21, _t22, -_t21, -_t22, -_t16, -_t19, _t52, _t53, _t54, Math.fma(_self23, _t20, -(_self22 * _t23)), Math.fma(_self23, _t18, -(_self21 * _t23)), Math.fma(_self23, _t17, -_self20 * _t23), Math.fma(_t17, _t6, _t18 * -_t7), Math.fma(_t18, _t8, _t20 * -_t6), Math.fma(_t17, _t8, _t20 * -_t7), Math.fma(_t20, _t9, -(_t23 * _t8)), Math.fma(_t18, _t9, _t23 * -_t6), Math.fma(_t17, _t9, -(_t23 * _t7)), _t88, 1.0 / _t88);
    }

    /** Piece 3 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_zo_s8c3b6a0_2(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _t16, double _t19, double _t21, double _t22, double _t24, double _t25, double _t26, double _t27, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t88, double _t88_inv) {
        double _t92 = java.lang.Math.abs(_t88);
        double _t94 = Math.fma(_t16, _t58, Math.fma(_t19, _t59, -(_t21 * _t60)));
        double _t98 = java.lang.Math.abs(_t94);
        return unprojectRay_zo_s8c3b6a0_3(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t16, _t19, _t21, _t22, _t24, _t25, _t26, _t27, _t52, _t53, _t54, _t55, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t88, _t88_inv, Math.fma(_t22, _t53, Math.fma(_t21, _t55, -(_t16 * _t56))), Math.fma(_t22, _t54, Math.fma(_t19, _t55, -(_t16 * _t57))), Math.fma(_t22, _t52, Math.fma(_t19, _t56, -(_t21 * _t57))), _t92, _t94, _t88_inv * _t94, 1.0 / _t94, Math.fma(_t22, _t59, Math.fma(_t21, _t61, -(_t16 * _t62))), Math.fma(_t22, _t60, Math.fma(_t19, _t61, -(_t16 * _t63))), Math.fma(_t22, _t58, Math.fma(_t19, _t62, -(_t21 * _t63))), _t98 * 9.094947017729282E-13, 1.0 / (_t98 <= _t92 * 9.094947017729282E-13 ? _t88 : _t94));
    }

    /**
     * Piece 4 of {@code unprojectRay_zo}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code unprojectRay}; reached only through it.
     */
    private static double[] unprojectRay_zo_s8c3b6a0_3(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double _t16, double _t19, double _t21, double _t22, double _t24, double _t25, double _t26, double _t27, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t88, double _t88_inv, double _t89, double _t90, double _t91, double _t92, double _t94, double _sp0, double _t94_inv, double _t95, double _t96, double _t97, double _t99, double _t100_inv) {
        if (_t92 <= _t99) {
            rayOrigin[rayOriginOffset] = -(_t95 * _t94_inv);
            rayOrigin[rayOriginOffset + 1] = _t96 * _t94_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t97 * _t94_inv);
        } else {
            rayOrigin[rayOriginOffset] = -(_t89 * _t88_inv);
            rayOrigin[rayOriginOffset + 1] = _t90 * _t88_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t91 * _t88_inv);
        }
        if (_t92 <= _t99) {
            rayDir[rayDirOffset] = Math.fma(_t24, _t55, Math.fma(_t16, _t56, Math.fma(_t25, _t53, _t88 * _t95 / _t94))) * _t94_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t19, _t55, Math.fma(_t26, _t57, Math.fma(_t22, _t54, -(_t88 * _t96 / _t94)))) * _t94_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t27, _t56, Math.fma(_t21, _t57, Math.fma(_t25, _t52, _t88 * _t97 / _t94))) * _t94_inv;
        } else {
            rayDir[rayDirOffset] = Math.fma(_t24, _t61, Math.fma(_t16, _t62, Math.fma(_t25, _t59, _sp0 * _t89))) * _t100_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t19, _t61, Math.fma(_t26, _t63, Math.fma(_t22, _t60, -(_t90 * _sp0)))) * _t100_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t27, _t62, Math.fma(_t21, _t63, Math.fma(_t25, _t58, _t91 * _sp0))) * _t100_inv;
        }
        return rayOrigin;
    }

    public static double[] unprojectRay_no(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double viewportX, double viewportY, double viewportZ, double viewportW, double epsilon) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = -_self32;
        double _t1 = -_self30;
        double _t2 = -_self31;
        double _t18 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t19 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        return unprojectRay_no_sdf7899e_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, epsilon, src[srcOffset + 12], src[srcOffset + 13], -_self33, _self21 + _self31, _self20 + _self30, _self22 + _self32, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self23 - _self33, _self23 + _self33, _t18, _t19, Math.fma(_t0, _t18, _self02), Math.fma(_t1, _t19, _self10), Math.fma(_t2, _t19, _self11), Math.fma(_t1, _t18, _self00), Math.fma(_t0, _t19, _self12), Math.fma(_t2, _t18, _self01));
    }

    /** Piece 2 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_no_sdf7899e_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double epsilon, double _self03, double _self13, double _t3, double _t5, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t26 = Math.fma(_t3, _t18, _self03);
        double _t27 = Math.fma(_t3, _t19, _self13);
        double _t56 = Math.fma(_t5, _t21, -_t7 * _t22);
        double _t57 = Math.fma(_t8, _t22, -(_t5 * _t24));
        double _t58 = Math.fma(_t8, _t21, -(_t7 * _t24));
        double _t59 = Math.fma(_t21, _t9, _t22 * -_t10);
        double _t60 = Math.fma(_t22, _t11, _t24 * -_t9);
        double _t61 = Math.fma(_t21, _t11, _t24 * -_t10);
        double _t92 = Math.fma(_t20, _t56, Math.fma(_t23, _t57, -(_t25 * _t58)));
        double _t92_inv = 1.0 / _t92;
        double _t93 = Math.fma(_t20, _t59, Math.fma(_t23, _t60, -(_t25 * _t61)));
        return unprojectRay_no_sdf7899e_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, epsilon, _t20, _t23, _t25, _t26, -_t25, -_t26, -_t20, -_t23, _t56, _t57, _t58, _t59, _t60, _t61, Math.fma(_t24, _t12, -(_t27 * _t11)), Math.fma(_t22, _t12, _t27 * -_t9), Math.fma(_t13, _t24, -(_t8 * _t27)), Math.fma(_t13, _t22, -(_t5 * _t27)), Math.fma(_t21, _t12, -(_t27 * _t10)), Math.fma(_t13, _t21, -_t7 * _t27), _t92, _t92_inv, _t93, _t92_inv * _t93, 1.0 / _t93);
    }

    /** Piece 3 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_no_sdf7899e_2(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double epsilon, double _t20, double _t23, double _t25, double _t26, double _t28, double _t29, double _t30, double _t31, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t66, double _t67, double _t92, double _t92_inv, double _t93, double _sp0, double _t93_inv) {
        double _t100 = java.lang.Math.abs(_t92);
        double _t101 = java.lang.Math.abs(_t93);
        return unprojectRay_no_sb5dd281d_3(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t20, _t23, _t25, _t26, _t28, _t29, _t30, _t31, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t92, _t92_inv, _t93, _sp0, _t93_inv, Math.fma(_t26, _t60, Math.fma(_t25, _t62, -(_t20 * _t63))), Math.fma(_t26, _t57, Math.fma(_t25, _t64, -(_t20 * _t65))), Math.fma(_t26, _t61, Math.fma(_t23, _t62, -(_t20 * _t66))), Math.fma(_t26, _t58, Math.fma(_t23, _t64, -(_t20 * _t67))), Math.fma(_t26, _t59, Math.fma(_t23, _t63, -(_t25 * _t66))), Math.fma(_t26, _t56, Math.fma(_t23, _t65, -(_t25 * _t67))), _t100, epsilon * _t101, 1.0 / (_t101 <= epsilon * _t100 ? _t92 : _t93));
    }

    public static double[] unprojectRay_zo(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double winCoordsX, double winCoordsY, double viewportX, double viewportY, double viewportZ, double viewportW, double epsilon) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self01 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self12 = src[srcOffset + 9];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self03 = src[srcOffset + 12];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = -_self32;
        double _t1 = -_self30;
        double _t2 = -_self31;
        double _t3 = -_self33;
        double _t14 = 2.0 * (winCoordsX - viewportX) / viewportZ - 1.0;
        double _t15 = 2.0 * (winCoordsY - viewportY) / viewportW - 1.0;
        return unprojectRay_zo_s8123da82_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, epsilon, _self20, _self21, _self22, src[srcOffset + 13], _self23, _t3, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self23 - _self33, _t15, Math.fma(_t0, _t14, _self02), Math.fma(_t1, _t15, _self10), Math.fma(_t2, _t15, _self11), Math.fma(_t1, _t14, _self00), Math.fma(_t0, _t15, _self12), Math.fma(_t2, _t14, _self01), Math.fma(_t3, _t14, _self03));
    }

    /** Piece 2 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_zo_s8123da82_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double epsilon, double _self20, double _self21, double _self22, double _self13, double _self23, double _t3, double _t6, double _t7, double _t8, double _t9, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22) {
        double _t23 = Math.fma(_t3, _t15, _self13);
        double _t52 = Math.fma(_self21, _t17, -_self20 * _t18);
        double _t53 = Math.fma(_self22, _t18, -(_self21 * _t20));
        double _t54 = Math.fma(_self22, _t17, -(_self20 * _t20));
        double _t55 = Math.fma(_self23, _t20, -(_self22 * _t23));
        double _t56 = Math.fma(_self23, _t18, -(_self21 * _t23));
        double _t57 = Math.fma(_self23, _t17, -_self20 * _t23);
        double _t88 = Math.fma(_t16, _t52, Math.fma(_t19, _t53, -(_t21 * _t54)));
        return unprojectRay_zo_s8123da82_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, epsilon, _t16, _t19, _t21, _t22, -_t21, -_t22, -_t16, -_t19, _t52, _t53, _t54, _t55, _t56, _t57, Math.fma(_t17, _t6, _t18 * -_t7), Math.fma(_t18, _t8, _t20 * -_t6), Math.fma(_t17, _t8, _t20 * -_t7), Math.fma(_t20, _t9, -(_t23 * _t8)), Math.fma(_t18, _t9, _t23 * -_t6), Math.fma(_t17, _t9, -(_t23 * _t7)), _t88, 1.0 / _t88, Math.fma(_t22, _t53, Math.fma(_t21, _t55, -(_t16 * _t56))), Math.fma(_t22, _t54, Math.fma(_t19, _t55, -(_t16 * _t57))));
    }

    /** Piece 3 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_zo_s8123da82_2(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double epsilon, double _t16, double _t19, double _t21, double _t22, double _t24, double _t25, double _t26, double _t27, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t88, double _t88_inv, double _t89, double _t90) {
        double _t91 = Math.fma(_t22, _t52, Math.fma(_t19, _t56, -(_t21 * _t57)));
        double _t92 = java.lang.Math.abs(_t88);
        double _t94 = Math.fma(_t16, _t58, Math.fma(_t19, _t59, -(_t21 * _t60)));
        double _t94_inv = 1.0 / _t94;
        double _t95 = Math.fma(_t22, _t59, Math.fma(_t21, _t61, -(_t16 * _t62)));
        double _t96 = Math.fma(_t22, _t60, Math.fma(_t19, _t61, -(_t16 * _t63)));
        double _t97 = Math.fma(_t22, _t58, Math.fma(_t19, _t62, -(_t21 * _t63)));
        double _t98 = java.lang.Math.abs(_t94);
        double _t99 = epsilon * _t98;
        if (_t92 <= _t99) {
            rayOrigin[rayOriginOffset] = -(_t95 * _t94_inv);
            rayOrigin[rayOriginOffset + 1] = _t96 * _t94_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t97 * _t94_inv);
        } else {
            rayOrigin[rayOriginOffset] = -(_t89 * _t88_inv);
            rayOrigin[rayOriginOffset + 1] = _t90 * _t88_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t91 * _t88_inv);
        }
        return unprojectRay_zo_sfb275c61_3(rayOrigin, rayDir, rayDirOffset, _t16, _t19, _t21, _t22, _t24, _t25, _t26, _t27, _t52, _t53, _t54, _t55, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t88, _t89, _t90, _t91, _t92, _t94, _t88_inv * _t94, _t94_inv, _t95, _t96, _t97, _t99, 1.0 / (_t98 <= epsilon * _t92 ? _t88 : _t94));
    }

    public static double[] unprojectRay_no(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset, double epsilon) {
        double _winCoordsy = winCoords[winCoordsOffset + 1];
        double _viewporty = viewport[viewportOffset + 1];
        double _viewportw = viewport[viewportOffset + 3];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = -_self32;
        double _t18 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        return unprojectRay_no_sab0ee287_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, epsilon, src[srcOffset], src[srcOffset + 1], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 9], src[srcOffset + 12], src[srcOffset + 13], _t0, -_self30, -_self31, -_self33, _self21 + _self31, _self20 + _self30, _self22 + _self32, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self23 - _self33, _self23 + _self33, _t18, 2.0 * (_winCoordsy - _viewporty) / _viewportw - 1.0, Math.fma(_t0, _t18, _self02));
    }

    /** Piece 2 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_no_sab0ee287_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double epsilon, double _self00, double _self10, double _self01, double _self11, double _self12, double _self03, double _self13, double _t0, double _t1, double _t2, double _t3, double _t5, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20) {
        double _t21 = Math.fma(_t1, _t19, _self10);
        double _t22 = Math.fma(_t2, _t19, _self11);
        double _t23 = Math.fma(_t1, _t18, _self00);
        double _t24 = Math.fma(_t0, _t19, _self12);
        double _t25 = Math.fma(_t2, _t18, _self01);
        double _t26 = Math.fma(_t3, _t18, _self03);
        double _t27 = Math.fma(_t3, _t19, _self13);
        double _t56 = Math.fma(_t5, _t21, -_t7 * _t22);
        double _t57 = Math.fma(_t8, _t22, -(_t5 * _t24));
        double _t58 = Math.fma(_t8, _t21, -(_t7 * _t24));
        double _t92 = Math.fma(_t20, _t56, Math.fma(_t23, _t57, -(_t25 * _t58)));
        return unprojectRay_no_sab0ee287_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, epsilon, _t20, _t23, _t25, _t26, -_t25, -_t26, -_t20, -_t23, _t56, _t57, _t58, Math.fma(_t21, _t9, _t22 * -_t10), Math.fma(_t22, _t11, _t24 * -_t9), Math.fma(_t21, _t11, _t24 * -_t10), Math.fma(_t24, _t12, -(_t27 * _t11)), Math.fma(_t22, _t12, _t27 * -_t9), Math.fma(_t13, _t24, -(_t8 * _t27)), Math.fma(_t13, _t22, -(_t5 * _t27)), Math.fma(_t21, _t12, -(_t27 * _t10)), Math.fma(_t13, _t21, -_t7 * _t27), _t92, 1.0 / _t92);
    }

    /** Piece 3 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_no_sab0ee287_2(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double epsilon, double _t20, double _t23, double _t25, double _t26, double _t28, double _t29, double _t30, double _t31, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t64, double _t65, double _t66, double _t67, double _t92, double _t92_inv) {
        double _t93 = Math.fma(_t20, _t59, Math.fma(_t23, _t60, -(_t25 * _t61)));
        double _t100 = java.lang.Math.abs(_t92);
        double _t101 = java.lang.Math.abs(_t93);
        return unprojectRay_no_sb5dd281d_3(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t20, _t23, _t25, _t26, _t28, _t29, _t30, _t31, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t92, _t92_inv, _t93, _t92_inv * _t93, 1.0 / _t93, Math.fma(_t26, _t60, Math.fma(_t25, _t62, -(_t20 * _t63))), Math.fma(_t26, _t57, Math.fma(_t25, _t64, -(_t20 * _t65))), Math.fma(_t26, _t61, Math.fma(_t23, _t62, -(_t20 * _t66))), Math.fma(_t26, _t58, Math.fma(_t23, _t64, -(_t20 * _t67))), Math.fma(_t26, _t59, Math.fma(_t23, _t63, -(_t25 * _t66))), Math.fma(_t26, _t56, Math.fma(_t23, _t65, -(_t25 * _t67))), _t100, epsilon * _t101, 1.0 / (_t101 <= epsilon * _t100 ? _t92 : _t93));
    }

    public static double[] unprojectRay_zo(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double[] src, int srcOffset, double[] winCoords, int winCoordsOffset, double[] viewport, int viewportOffset, double epsilon) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self30 = src[srcOffset + 3];
        double _self21 = src[srcOffset + 6];
        double _self31 = src[srcOffset + 7];
        double _self02 = src[srcOffset + 8];
        double _self22 = src[srcOffset + 10];
        double _self32 = src[srcOffset + 11];
        double _self23 = src[srcOffset + 14];
        double _self33 = src[srcOffset + 15];
        double _t0 = -_self32;
        double _t1 = -_self30;
        double _t14 = 2.0 * (winCoords[winCoordsOffset] - viewport[viewportOffset]) / viewport[viewportOffset + 2] - 1.0;
        double _t15 = 2.0 * (winCoords[winCoordsOffset + 1] - viewport[viewportOffset + 1]) / viewport[viewportOffset + 3] - 1.0;
        return unprojectRay_zo_sb1e93ba3_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, epsilon, src[srcOffset], _self20, src[srcOffset + 4], src[srcOffset + 5], _self21, src[srcOffset + 9], _self22, src[srcOffset + 12], src[srcOffset + 13], _self23, _t0, _t1, -_self31, -_self33, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self23 - _self33, _t14, _t15, Math.fma(_t0, _t14, _self02), Math.fma(_t1, _t15, _self10));
    }

    /** Piece 2 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_zo_sb1e93ba3_1(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double epsilon, double _self00, double _self20, double _self01, double _self11, double _self21, double _self12, double _self22, double _self03, double _self13, double _self23, double _t0, double _t1, double _t2, double _t3, double _t6, double _t7, double _t8, double _t9, double _t14, double _t15, double _t16, double _t17) {
        double _t18 = Math.fma(_t2, _t15, _self11);
        double _t19 = Math.fma(_t1, _t14, _self00);
        double _t20 = Math.fma(_t0, _t15, _self12);
        double _t21 = Math.fma(_t2, _t14, _self01);
        double _t22 = Math.fma(_t3, _t14, _self03);
        double _t23 = Math.fma(_t3, _t15, _self13);
        double _t52 = Math.fma(_self21, _t17, -_self20 * _t18);
        double _t53 = Math.fma(_self22, _t18, -(_self21 * _t20));
        double _t54 = Math.fma(_self22, _t17, -(_self20 * _t20));
        double _t88 = Math.fma(_t16, _t52, Math.fma(_t19, _t53, -(_t21 * _t54)));
        return unprojectRay_zo_sb1e93ba3_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, epsilon, _t16, _t19, _t21, _t22, -_t21, -_t22, -_t16, -_t19, _t52, _t53, _t54, Math.fma(_self23, _t20, -(_self22 * _t23)), Math.fma(_self23, _t18, -(_self21 * _t23)), Math.fma(_self23, _t17, -_self20 * _t23), Math.fma(_t17, _t6, _t18 * -_t7), Math.fma(_t18, _t8, _t20 * -_t6), Math.fma(_t17, _t8, _t20 * -_t7), Math.fma(_t20, _t9, -(_t23 * _t8)), Math.fma(_t18, _t9, _t23 * -_t6), Math.fma(_t17, _t9, -(_t23 * _t7)), _t88, 1.0 / _t88);
    }

    /** Piece 3 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static double[] unprojectRay_zo_sb1e93ba3_2(double[] rayOrigin, int rayOriginOffset, double[] rayDir, int rayDirOffset, double epsilon, double _t16, double _t19, double _t21, double _t22, double _t24, double _t25, double _t26, double _t27, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t58, double _t59, double _t60, double _t61, double _t62, double _t63, double _t88, double _t88_inv) {
        double _t92 = java.lang.Math.abs(_t88);
        double _t94 = Math.fma(_t16, _t58, Math.fma(_t19, _t59, -(_t21 * _t60)));
        double _t98 = java.lang.Math.abs(_t94);
        return unprojectRay_zo_s8c3b6a0_3(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t16, _t19, _t21, _t22, _t24, _t25, _t26, _t27, _t52, _t53, _t54, _t55, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t88, _t88_inv, Math.fma(_t22, _t53, Math.fma(_t21, _t55, -(_t16 * _t56))), Math.fma(_t22, _t54, Math.fma(_t19, _t55, -(_t16 * _t57))), Math.fma(_t22, _t52, Math.fma(_t19, _t56, -(_t21 * _t57))), _t92, _t94, _t88_inv * _t94, 1.0 / _t94, Math.fma(_t22, _t59, Math.fma(_t21, _t61, -(_t16 * _t62))), Math.fma(_t22, _t60, Math.fma(_t19, _t61, -(_t16 * _t63))), Math.fma(_t22, _t58, Math.fma(_t19, _t62, -(_t21 * _t63))), epsilon * _t98, 1.0 / (_t98 <= epsilon * _t92 ? _t88 : _t94));
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
