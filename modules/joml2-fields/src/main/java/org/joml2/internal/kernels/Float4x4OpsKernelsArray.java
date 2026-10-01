// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float4x4Ops} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float4x4Ops} and its sibling kernel units. Not public API.
 */
public final class Float4x4OpsKernelsArray {
    private Float4x4OpsKernelsArray() {}

    public static float[] invNegativeX_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self10 * _t0;
        float _t9 = _self21 * _t1;
        float _t10 = _self11 * _t0;
        float _t11 = _self20 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self12 * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest[destOffset + 0] = -(_t21 * _t26);
            dest[destOffset + 1] = -(_t22 * _t26);
            dest[destOffset + 2] = -(_t20 * _t26);
        } else {
            dest[destOffset + 0] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    public static float[] invNegativeY_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self20 = src[srcOffset + 2];
        float _self01 = src[srcOffset + 4];
        float _self21 = src[srcOffset + 6];
        float _self02 = src[srcOffset + 8];
        float _self22 = src[srcOffset + 10];
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self01 * _t0;
        float _t9 = _self20 * _t1;
        float _t10 = _self00 * _t0;
        float _t11 = _self21 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self02 * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest[destOffset + 0] = -(_t22 * _t26);
            dest[destOffset + 1] = -(_t21 * _t26);
            dest[destOffset + 2] = -(_t20 * _t26);
        } else {
            dest[destOffset + 0] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    public static float[] invNegativeZ_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self10, _self11, _self12);
        float _t8 = _self00 * _t0;
        float _t9 = _self11 * _t1;
        float _t10 = _self01 * _t0;
        float _t11 = _self10 * _t1;
        float _t12 = _self12 * _t1;
        float _t13 = _self02 * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest[destOffset + 0] = -(_t21 * _t26);
            dest[destOffset + 1] = -(_t22 * _t26);
            dest[destOffset + 2] = -(_t20 * _t26);
        } else {
            dest[destOffset + 0] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    public static float[] invPositiveX_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _t0 = unitScale(_self10, _self11, _self12);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self10 * _t0;
        float _t9 = _self21 * _t1;
        float _t10 = _self11 * _t0;
        float _t11 = _self20 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self12 * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest[destOffset + 0] = _t21 * _t26;
            dest[destOffset + 1] = _t22 * _t26;
            dest[destOffset + 2] = _t20 * _t26;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    public static float[] invPositiveY_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self20 = src[srcOffset + 2];
        float _self01 = src[srcOffset + 4];
        float _self21 = src[srcOffset + 6];
        float _self02 = src[srcOffset + 8];
        float _self22 = src[srcOffset + 10];
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self20, _self21, _self22);
        float _t8 = _self01 * _t0;
        float _t9 = _self20 * _t1;
        float _t10 = _self00 * _t0;
        float _t11 = _self21 * _t1;
        float _t12 = _self22 * _t1;
        float _t13 = _self02 * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest[destOffset + 0] = _t22 * _t26;
            dest[destOffset + 1] = _t21 * _t26;
            dest[destOffset + 2] = _t20 * _t26;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    public static float[] invPositiveZ_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _t0 = unitScale(_self00, _self01, _self02);
        float _t1 = unitScale(_self10, _self11, _self12);
        float _t8 = _self00 * _t0;
        float _t9 = _self11 * _t1;
        float _t10 = _self01 * _t0;
        float _t11 = _self10 * _t1;
        float _t12 = _self12 * _t1;
        float _t13 = _self02 * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t13, _t11, -(_t8 * _t12));
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest[destOffset + 0] = _t21 * _t26;
            dest[destOffset + 1] = _t22 * _t26;
            dest[destOffset + 2] = _t20 * _t26;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    public static float[] invert_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t12 = unitScale(_self22, _self23, Math.max(Math.abs(_self20), Math.abs(_self21)));
        float _t13 = unitScale(_self32, _self33, Math.max(Math.abs(_self30), Math.abs(_self31)));
        float _t14 = unitScale(_self12, _self13, Math.max(Math.abs(_self10), Math.abs(_self11)));
        float _t15 = unitScale(_self02, _self03, Math.max(Math.abs(_self00), Math.abs(_self01)));
        return invert_degenerate_sc8781f7c_1(dest, destOffset, _t12, _t13, _t14, _t15, _self21 * _t12, _self32 * _t13, _self22 * _t12, _self31 * _t13, _self13 * _t14, _self33 * _t13, _self23 * _t12, _self11 * _t14, _self12 * _t14, _self20 * _t12, _self30 * _t13, _self10 * _t14, _self03 * _t15, _self02 * _t15, _self00 * _t15, _self01 * _t15);
    }

    /** Piece 2 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invert_degenerate_sc8781f7c_1(float[] dest, int destOffset, float _t12, float _t13, float _t14, float _t15, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47) {
        float _t84 = Math.fma(_t32, _t33, -(_t34 * _t35));
        float _t85 = Math.fma(_t34, _t37, -(_t38 * _t33));
        float _t86 = Math.fma(_t32, _t37, -(_t38 * _t35));
        return invert_degenerate_sc8781f7c_2(dest, destOffset, _t12, _t13, _t14, _t15, _t36, _t39, _t40, _t43, _t44, _t45, _t46, _t47, _t84, _t85, _t86, Math.fma(_t41, _t35, -(_t32 * _t42)), Math.fma(_t41, _t33, -(_t34 * _t42)), Math.fma(_t41, _t37, -(_t38 * _t42)), Math.fma(_t39, _t33, -(_t40 * _t35)), Math.fma(_t40, _t37, -(_t36 * _t33)), Math.fma(_t39, _t37, -(_t36 * _t35)), Math.fma(_t39, _t34, -(_t40 * _t32)), Math.fma(_t40, _t38, -(_t36 * _t34)), Math.fma(_t39, _t38, -(_t36 * _t32)), Math.fma(_t43, _t33, -(_t40 * _t42)), Math.fma(_t43, _t37, -(_t36 * _t42)), Math.fma(_t43, _t34, -(_t40 * _t41)), Math.fma(_t43, _t38, -(_t36 * _t41)), Math.fma(_t43, _t35, -(_t39 * _t42)), Math.fma(_t43, _t32, -(_t39 * _t41)), Math.fma(_t84, _t36, Math.fma(_t85, _t39, -(_t86 * _t40))));
    }

    /** Piece 3 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invert_degenerate_sc8781f7c_2(float[] dest, int destOffset, float _t12, float _t13, float _t14, float _t15, float _t36, float _t39, float _t40, float _t43, float _t44, float _t45, float _t46, float _t47, float _t84, float _t85, float _t86, float _t87, float _t88, float _t89, float _t90, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t101, float _t114) {
        float _t115 = Math.fma(_t87, _t40, Math.fma(_t84, _t43, -(_t88 * _t39)));
        float _t116 = Math.fma(_t87, _t36, Math.fma(_t86, _t43, -(_t89 * _t39)));
        float _t117 = Math.fma(_t88, _t36, Math.fma(_t85, _t43, -(_t89 * _t40)));
        float _t123_inv = 1.0f / Math.fma(-_t115, _t44, Math.fma(_t116, _t45, Math.fma(_t114, _t46, -(_t117 * _t47))));
        float _sp1 = _t14 * _t123_inv;
        float _sp0 = _t15 * _t123_inv;
        dest[destOffset + 0] = _t114 * _sp0;
        dest[destOffset + 1] = -(_t117 * _sp0);
        dest[destOffset + 2] = _t116 * _sp0;
        dest[destOffset + 3] = -(_t115 * _sp0);
        dest[destOffset + 4] = -(Math.fma(_t84, _t44, Math.fma(_t85, _t47, -(_t86 * _t45))) * _sp1);
        dest[destOffset + 5] = Math.fma(_t88, _t44, Math.fma(_t85, _t46, -(_t89 * _t45))) * _sp1;
        dest[destOffset + 6] = -(Math.fma(_t87, _t44, Math.fma(_t86, _t46, -(_t89 * _t47))) * _sp1);
        return invert_degenerate_sc8781f7c_3(dest, destOffset, _t44, _t45, _t46, _t47, _t84, _t87, _t88, _t90, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, _t101, _t13 * _t123_inv, _t12 * _t123_inv, _sp1);
    }

    /** Piece 4 of {@code invert_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invert_degenerate_sc8781f7c_3(float[] dest, int destOffset, float _t44, float _t45, float _t46, float _t47, float _t84, float _t87, float _t88, float _t90, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t101, float _sp3, float _sp2, float _sp1) {
        dest[destOffset + 7] = Math.fma(_t87, _t45, Math.fma(_t84, _t46, -(_t88 * _t47))) * _sp1;
        dest[destOffset + 8] = Math.fma(_t90, _t44, Math.fma(_t91, _t47, -(_t92 * _t45))) * _sp2;
        dest[destOffset + 9] = -(Math.fma(_t96, _t44, Math.fma(_t91, _t46, -(_t97 * _t45))) * _sp2);
        dest[destOffset + 10] = Math.fma(_t100, _t44, Math.fma(_t92, _t46, -(_t97 * _t47))) * _sp2;
        dest[destOffset + 11] = -(Math.fma(_t100, _t45, Math.fma(_t90, _t46, -(_t96 * _t47))) * _sp2);
        dest[destOffset + 12] = -(Math.fma(_t93, _t44, Math.fma(_t94, _t47, -(_t95 * _t45))) * _sp3);
        dest[destOffset + 13] = Math.fma(_t98, _t44, Math.fma(_t94, _t46, -(_t99 * _t45))) * _sp3;
        dest[destOffset + 14] = -(Math.fma(_t101, _t44, Math.fma(_t95, _t46, -(_t99 * _t47))) * _sp3);
        dest[destOffset + 15] = Math.fma(_t101, _t45, Math.fma(_t93, _t46, -(_t98 * _t47))) * _sp3;
        return dest;
    }

    public static float[] invert_affine(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _t12 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t13 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t14 = Math.fma(_self10, _self22, -(_self12 * _self20));
        float _t22 = Math.fma(_self02, _t13, Math.fma(_self00, _t12, -(_self01 * _t14)));
        if (!(Math.abs(_t22) > 1.1754944E-38f && Math.abs(_t22) < 8.507059E37f)) return Float4x4OpsKernelsArray.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t22_inv = 1.0f / _t22;
        dest[destOffset + 0] = _t12 * _t22_inv;
        dest[destOffset + 1] = Math.fma(_self12, _self20, -(_self10 * _self22)) * _t22_inv;
        dest[destOffset + 2] = _t13 * _t22_inv;
        return invert_affine_sfb36bc81_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _self03, _t12, _t13, _t14, Math.fma(_self12, _self23, -(_self13 * _self22)), Math.fma(_self11, _self23, -(_self13 * _self21)), Math.fma(_self10, _self23, -(_self13 * _self20)), _t22_inv);
    }

    /** Piece 2 of {@code invert_affine}, split to fit the inline budget; reached only through it. */
    private static float[] invert_affine_sfb36bc81_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _self03, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t22_inv) {
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = Math.fma(_self02, _self21, -(_self01 * _self22)) * _t22_inv;
        dest[destOffset + 5] = Math.fma(_self00, _self22, -(_self02 * _self20)) * _t22_inv;
        dest[destOffset + 6] = Math.fma(_self01, _self20, -(_self00 * _self21)) * _t22_inv;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = Math.fma(_self01, _self12, -(_self02 * _self11)) * _t22_inv;
        dest[destOffset + 9] = Math.fma(_self02, _self10, -(_self00 * _self12)) * _t22_inv;
        dest[destOffset + 10] = Math.fma(_self00, _self11, -(_self01 * _self10)) * _t22_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -(Math.fma(_self03, _t12, Math.fma(_self01, _t15, -(_self02 * _t16))) * _t22_inv);
        dest[destOffset + 13] = Math.fma(_self03, _t14, Math.fma(_self00, _t15, -(_self02 * _t17))) * _t22_inv;
        dest[destOffset + 14] = -(Math.fma(_self03, _t13, Math.fma(_self00, _t16, -(_self01 * _t17))) * _t22_inv);
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] invertProduct_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self20 = src[srcOffset + 2];
        float _self21 = src[srcOffset + 6];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 14];
        float _other01 = other[otherOffset + 4];
        float _other11 = other[otherOffset + 5];
        float _other21 = other[otherOffset + 6];
        float _other31 = other[otherOffset + 7];
        return invertProduct_degenerate_sffcca8a5_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], _self20, src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], _self21, src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], _self23, src[srcOffset + 15], other[otherOffset + 0], other[otherOffset + 1], other[otherOffset + 2], other[otherOffset + 3], _other01, _other11, _other21, _other31, other[otherOffset + 8], other[otherOffset + 9], other[otherOffset + 10], other[otherOffset + 11], other[otherOffset + 12], other[otherOffset + 13], other[otherOffset + 14], other[otherOffset + 15], Math.fma(_other31, _self23, Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21))));
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _other00, float _other10, float _other20, float _other30, float _other01, float _other11, float _other21, float _other31, float _other02, float _other12, float _other22, float _other32, float _other03, float _other13, float _other23, float _other33, float _t48) {
        return invertProduct_degenerate_sffcca8a5_2(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _self03, _self13, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22, _other32, _other03, _other13, _other23, _other33, _t48, Math.fma(_other32, _self23, Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21))), Math.fma(_other33, _self23, Math.fma(_other23, _self22, Math.fma(_other03, _self20, _other13 * _self21))), Math.fma(_other30, _self23, Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21))), Math.fma(_other32, _self33, Math.fma(_other22, _self32, Math.fma(_other02, _self30, _other12 * _self31))), Math.fma(_other33, _self33, Math.fma(_other23, _self32, Math.fma(_other03, _self30, _other13 * _self31))), Math.fma(_other30, _self33, Math.fma(_other20, _self32, Math.fma(_other00, _self30, _other10 * _self31))), Math.fma(_other31, _self33, Math.fma(_other21, _self32, Math.fma(_other01, _self30, _other11 * _self31))), Math.fma(_other33, _self13, Math.fma(_other23, _self12, Math.fma(_other03, _self10, _other13 * _self11))), Math.fma(_other32, _self13, Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11))));
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_2(float[] dest, int destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _self03, float _self13, float _other00, float _other10, float _other20, float _other30, float _other01, float _other11, float _other21, float _other31, float _other02, float _other12, float _other22, float _other32, float _other03, float _other13, float _other23, float _other33, float _t48, float _t49, float _t50, float _t51, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57) {
        float _t58 = Math.fma(_other30, _self13, Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11)));
        float _t59 = Math.fma(_other31, _self13, Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)));
        float _t60 = Math.fma(_other32, _self03, Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01)));
        float _t61 = Math.fma(_other33, _self03, Math.fma(_other23, _self02, Math.fma(_other03, _self00, _other13 * _self01)));
        float _t62 = Math.fma(_other30, _self03, Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01)));
        float _t63 = Math.fma(_other31, _self03, Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01)));
        float _t76 = unitScale(_t49, _t50, Math.max(Math.abs(_t51), Math.abs(_t48)));
        float _t77 = unitScale(_t52, _t53, Math.max(Math.abs(_t54), Math.abs(_t55)));
        return invertProduct_degenerate_sffcca8a5_3(dest, destOffset, _t50, _t51, _t53, _t54, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t76, _t77, unitScale(_t57, _t56, Math.max(Math.abs(_t58), Math.abs(_t59))), unitScale(_t60, _t61, Math.max(Math.abs(_t62), Math.abs(_t63))), _t48 * _t76, _t52 * _t77, _t55 * _t77, _t49 * _t76);
    }

    /** Piece 4 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_3(float[] dest, int destOffset, float _t50, float _t51, float _t53, float _t54, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t62, float _t63, float _t76, float _t77, float _t78, float _t79, float _t96, float _t97, float _t98, float _t99) {
        float _t100 = _t56 * _t78;
        float _t101 = _t53 * _t77;
        float _t102 = _t50 * _t76;
        float _t103 = _t59 * _t78;
        float _t104 = _t57 * _t78;
        float _t105 = _t51 * _t76;
        float _t106 = _t54 * _t77;
        float _t107 = _t58 * _t78;
        return invertProduct_degenerate_sffcca8a5_4(dest, destOffset, _t76, _t77, _t78, _t79, _t96, _t98, _t100, _t102, _t103, _t104, _t105, _t106, _t107, _t61 * _t79, _t60 * _t79, _t62 * _t79, _t63 * _t79, Math.fma(_t96, _t97, -(_t98 * _t99)), Math.fma(_t99, _t101, -(_t97 * _t102)), Math.fma(_t96, _t101, -(_t98 * _t102)), Math.fma(_t105, _t98, -(_t106 * _t96)), Math.fma(_t105, _t97, -(_t106 * _t99)), Math.fma(_t105, _t101, -(_t106 * _t102)), Math.fma(_t103, _t97, -(_t98 * _t104)), Math.fma(_t104, _t101, -(_t97 * _t100)), Math.fma(_t103, _t101, -(_t98 * _t100)), Math.fma(_t103, _t99, -(_t96 * _t104)), Math.fma(_t104, _t102, -(_t99 * _t100)), Math.fma(_t103, _t102, -(_t96 * _t100)), Math.fma(_t107, _t97, -(_t106 * _t104)), Math.fma(_t107, _t101, -(_t106 * _t100)), Math.fma(_t107, _t99, -(_t105 * _t104)));
    }

    /** Piece 5 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_4(float[] dest, int destOffset, float _t76, float _t77, float _t78, float _t79, float _t96, float _t98, float _t100, float _t102, float _t103, float _t104, float _t105, float _t106, float _t107, float _t108, float _t109, float _t110, float _t111, float _t148, float _t149, float _t150, float _t151, float _t152, float _t153, float _t154, float _t155, float _t156, float _t157, float _t158, float _t159, float _t160, float _t161, float _t162) {
        float _t178 = Math.fma(_t148, _t100, Math.fma(_t149, _t103, -(_t150 * _t104)));
        float _t179 = Math.fma(_t151, _t104, Math.fma(_t148, _t107, -(_t152 * _t103)));
        float _t180 = Math.fma(_t151, _t100, Math.fma(_t150, _t107, -(_t153 * _t103)));
        float _t181 = Math.fma(_t152, _t100, Math.fma(_t149, _t107, -(_t153 * _t104)));
        float _t187_inv = 1.0f / Math.fma(-_t179, _t108, Math.fma(_t180, _t109, Math.fma(_t178, _t110, -(_t181 * _t111))));
        float _sp1 = _t78 * _t187_inv;
        float _sp0 = _t79 * _t187_inv;
        dest[destOffset + 0] = _t178 * _sp0;
        dest[destOffset + 1] = -(_t181 * _sp0);
        dest[destOffset + 2] = _t180 * _sp0;
        dest[destOffset + 3] = -(_t179 * _sp0);
        dest[destOffset + 4] = -(Math.fma(_t148, _t108, Math.fma(_t149, _t111, -(_t150 * _t109))) * _sp1);
        return invertProduct_degenerate_sffcca8a5_5(dest, destOffset, _t108, _t109, _t110, _t111, _t148, _t149, _t150, _t151, _t152, _t153, _t154, _t155, _t156, _t157, _t158, _t159, _t160, _t161, _t162, Math.fma(_t107, _t102, -(_t105 * _t100)), Math.fma(_t107, _t98, -(_t106 * _t103)), Math.fma(_t107, _t96, -(_t105 * _t103)), _t77 * _t187_inv, _t76 * _t187_inv, _sp1);
    }

    /** Piece 6 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_5(float[] dest, int destOffset, float _t108, float _t109, float _t110, float _t111, float _t148, float _t149, float _t150, float _t151, float _t152, float _t153, float _t154, float _t155, float _t156, float _t157, float _t158, float _t159, float _t160, float _t161, float _t162, float _t163, float _t164, float _t165, float _sp3, float _sp2, float _sp1) {
        dest[destOffset + 5] = Math.fma(_t152, _t108, Math.fma(_t149, _t110, -(_t153 * _t109))) * _sp1;
        dest[destOffset + 6] = -(Math.fma(_t151, _t108, Math.fma(_t150, _t110, -(_t153 * _t111))) * _sp1);
        dest[destOffset + 7] = Math.fma(_t151, _t109, Math.fma(_t148, _t110, -(_t152 * _t111))) * _sp1;
        dest[destOffset + 8] = Math.fma(_t154, _t108, Math.fma(_t155, _t111, -(_t156 * _t109))) * _sp2;
        dest[destOffset + 9] = -(Math.fma(_t160, _t108, Math.fma(_t155, _t110, -(_t161 * _t109))) * _sp2);
        dest[destOffset + 10] = Math.fma(_t164, _t108, Math.fma(_t156, _t110, -(_t161 * _t111))) * _sp2;
        dest[destOffset + 11] = -(Math.fma(_t164, _t109, Math.fma(_t154, _t110, -(_t160 * _t111))) * _sp2);
        dest[destOffset + 12] = -(Math.fma(_t157, _t108, Math.fma(_t158, _t111, -(_t159 * _t109))) * _sp3);
        dest[destOffset + 13] = Math.fma(_t162, _t108, Math.fma(_t158, _t110, -(_t163 * _t109))) * _sp3;
        dest[destOffset + 14] = -(Math.fma(_t165, _t108, Math.fma(_t159, _t110, -(_t163 * _t111))) * _sp3);
        dest[destOffset + 15] = Math.fma(_t165, _t109, Math.fma(_t157, _t110, -(_t162 * _t111))) * _sp3;
        return dest;
    }

    public static float[] normal_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t12 = unitScale(_self22, _self23, Math.max(Math.abs(_self20), Math.abs(_self21)));
        float _t13 = unitScale(_self32, _self33, Math.max(Math.abs(_self30), Math.abs(_self31)));
        float _t14 = unitScale(_self12, _self13, Math.max(Math.abs(_self10), Math.abs(_self11)));
        float _t15 = unitScale(_self02, _self03, Math.max(Math.abs(_self00), Math.abs(_self01)));
        return normal_degenerate_s2861130b_1(dest, destOffset, _t12, _t13, _t14, _t15, _self21 * _t12, _self32 * _t13, _self22 * _t12, _self31 * _t13, _self13 * _t14, _self33 * _t13, _self23 * _t12, _self11 * _t14, _self12 * _t14, _self20 * _t12, _self30 * _t13, _self10 * _t14, _self03 * _t15, _self02 * _t15, _self00 * _t15, _self01 * _t15);
    }

    /** Piece 2 of {@code normal_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] normal_degenerate_s2861130b_1(float[] dest, int destOffset, float _t12, float _t13, float _t14, float _t15, float _t32, float _t33, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t47) {
        float _t84 = Math.fma(_t32, _t33, -(_t34 * _t35));
        float _t85 = Math.fma(_t34, _t37, -(_t38 * _t33));
        float _t86 = Math.fma(_t32, _t37, -(_t38 * _t35));
        return normal_degenerate_s2861130b_2(dest, destOffset, _t12, _t13, _t14, _t15, _t36, _t39, _t40, _t43, _t44, _t45, _t46, _t47, _t84, _t85, _t86, Math.fma(_t41, _t35, -(_t32 * _t42)), Math.fma(_t41, _t33, -(_t34 * _t42)), Math.fma(_t41, _t37, -(_t38 * _t42)), Math.fma(_t39, _t33, -(_t40 * _t35)), Math.fma(_t40, _t37, -(_t36 * _t33)), Math.fma(_t39, _t37, -(_t36 * _t35)), Math.fma(_t43, _t33, -(_t40 * _t42)), Math.fma(_t43, _t37, -(_t36 * _t42)), Math.fma(_t43, _t35, -(_t39 * _t42)), Math.fma(_t39, _t34, -(_t40 * _t32)), Math.fma(_t40, _t38, -(_t36 * _t34)), Math.fma(_t39, _t38, -(_t36 * _t32)), Math.fma(_t43, _t34, -(_t40 * _t41)), Math.fma(_t43, _t38, -(_t36 * _t41)), Math.fma(_t43, _t32, -(_t39 * _t41)), Math.fma(_t84, _t36, Math.fma(_t85, _t39, -(_t86 * _t40))));
    }

    /** Piece 3 of {@code normal_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] normal_degenerate_s2861130b_2(float[] dest, int destOffset, float _t12, float _t13, float _t14, float _t15, float _t36, float _t39, float _t40, float _t43, float _t44, float _t45, float _t46, float _t47, float _t84, float _t85, float _t86, float _t87, float _t88, float _t89, float _t90, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t101, float _t114) {
        float _t115 = Math.fma(_t87, _t40, Math.fma(_t84, _t43, -(_t88 * _t39)));
        float _t116 = Math.fma(_t87, _t36, Math.fma(_t86, _t43, -(_t89 * _t39)));
        float _t117 = Math.fma(_t88, _t36, Math.fma(_t85, _t43, -(_t89 * _t40)));
        float _t123_inv = 1.0f / Math.fma(-_t115, _t44, Math.fma(_t116, _t45, Math.fma(_t114, _t46, -(_t117 * _t47))));
        float _sp3 = _t13 * _t123_inv;
        float _sp2 = _t12 * _t123_inv;
        float _sp1 = _t14 * _t123_inv;
        float _sp0 = _t15 * _t123_inv;
        dest[destOffset + 0] = _t114 * _sp0;
        dest[destOffset + 1] = -(Math.fma(_t84, _t44, Math.fma(_t85, _t47, -(_t86 * _t45))) * _sp1);
        dest[destOffset + 2] = Math.fma(_t90, _t44, Math.fma(_t91, _t47, -(_t92 * _t45))) * _sp2;
        dest[destOffset + 3] = -(Math.fma(_t96, _t44, Math.fma(_t97, _t47, -(_t98 * _t45))) * _sp3);
        dest[destOffset + 4] = -(_t117 * _sp0);
        return normal_degenerate_s2861130b_3(dest, destOffset, _t44, _t45, _t46, _t47, _t84, _t85, _t86, _t87, _t88, _t89, _t90, _t91, _t92, _t93, _t94, _t95, _t96, _t97, _t98, _t99, _t100, _t101, _t115, _t116, _sp3, _sp2, _sp1, _sp0);
    }

    /** Piece 4 of {@code normal_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] normal_degenerate_s2861130b_3(float[] dest, int destOffset, float _t44, float _t45, float _t46, float _t47, float _t84, float _t85, float _t86, float _t87, float _t88, float _t89, float _t90, float _t91, float _t92, float _t93, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t101, float _t115, float _t116, float _sp3, float _sp2, float _sp1, float _sp0) {
        dest[destOffset + 5] = Math.fma(_t88, _t44, Math.fma(_t85, _t46, -(_t89 * _t45))) * _sp1;
        dest[destOffset + 6] = -(Math.fma(_t93, _t44, Math.fma(_t91, _t46, -(_t94 * _t45))) * _sp2);
        dest[destOffset + 7] = Math.fma(_t99, _t44, Math.fma(_t97, _t46, -(_t100 * _t45))) * _sp3;
        dest[destOffset + 8] = _t116 * _sp0;
        dest[destOffset + 9] = -(Math.fma(_t87, _t44, Math.fma(_t86, _t46, -(_t89 * _t47))) * _sp1);
        dest[destOffset + 10] = Math.fma(_t95, _t44, Math.fma(_t92, _t46, -(_t94 * _t47))) * _sp2;
        dest[destOffset + 11] = -(Math.fma(_t101, _t44, Math.fma(_t98, _t46, -(_t100 * _t47))) * _sp3);
        dest[destOffset + 12] = -(_t115 * _sp0);
        dest[destOffset + 13] = Math.fma(_t87, _t45, Math.fma(_t84, _t46, -(_t88 * _t47))) * _sp1;
        dest[destOffset + 14] = -(Math.fma(_t95, _t45, Math.fma(_t90, _t46, -(_t93 * _t47))) * _sp2);
        dest[destOffset + 15] = Math.fma(_t101, _t45, Math.fma(_t96, _t46, -(_t99 * _t47))) * _sp3;
        return dest;
    }

    public static float[] normal_affine(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _t12 = Math.fma(_self11, _self22, -(_self12 * _self21));
        float _t13 = Math.fma(_self10, _self21, -(_self11 * _self20));
        float _t14 = Math.fma(_self10, _self22, -(_self12 * _self20));
        float _t22 = Math.fma(_self02, _t13, Math.fma(_self00, _t12, -(_self01 * _t14)));
        if (!(Math.abs(_t22) > 1.1754944E-38f && Math.abs(_t22) < 8.507059E37f)) return Float4x4OpsKernelsArray.normal_degenerate(dest, destOffset, src, srcOffset);
        float _t22_inv = 1.0f / _t22;
        dest[destOffset + 0] = _t12 * _t22_inv;
        dest[destOffset + 1] = Math.fma(_self02, _self21, -(_self01 * _self22)) * _t22_inv;
        return normal_affine_scaa7765a_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _self03, _t12, _t13, _t14, Math.fma(_self12, _self23, -(_self13 * _self22)), Math.fma(_self11, _self23, -(_self13 * _self21)), Math.fma(_self10, _self23, -(_self13 * _self20)), _t22_inv);
    }

    /** Piece 2 of {@code normal_affine}, split to fit the inline budget; reached only through it. */
    private static float[] normal_affine_scaa7765a_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _self03, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t22_inv) {
        dest[destOffset + 2] = Math.fma(_self01, _self12, -(_self02 * _self11)) * _t22_inv;
        dest[destOffset + 3] = -(Math.fma(_self03, _t12, Math.fma(_self01, _t15, -(_self02 * _t16))) * _t22_inv);
        dest[destOffset + 4] = Math.fma(_self12, _self20, -(_self10 * _self22)) * _t22_inv;
        dest[destOffset + 5] = Math.fma(_self00, _self22, -(_self02 * _self20)) * _t22_inv;
        dest[destOffset + 6] = Math.fma(_self02, _self10, -(_self00 * _self12)) * _t22_inv;
        dest[destOffset + 7] = Math.fma(_self03, _t14, Math.fma(_self00, _t15, -(_self02 * _t17))) * _t22_inv;
        dest[destOffset + 8] = _t13 * _t22_inv;
        dest[destOffset + 9] = Math.fma(_self01, _self20, -(_self00 * _self21)) * _t22_inv;
        dest[destOffset + 10] = Math.fma(_self00, _self11, -(_self01 * _self10)) * _t22_inv;
        dest[destOffset + 11] = -(Math.fma(_self03, _t13, Math.fma(_self00, _t16, -(_self01 * _t17))) * _t22_inv);
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 14] = 0.0f;
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] toRigid_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _t0 = unitScale(_self01, _self11, _self21);
        float _t1 = unitScale(_self02, _self12, _self22);
        float _t2 = unitScale(_self00, _self10, _self20);
        float _t12 = _self21 * _t0;
        float _t13 = _self01 * _t0;
        float _t14 = _self11 * _t0;
        float _t15 = _self22 * _t1;
        float _t16 = _self02 * _t1;
        float _t17 = _self12 * _t1;
        float _t18 = _self20 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self10 * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t31 = (1.0f / (float) Math.sqrt(_t28));
        float _t32 = (1.0f / (float) Math.sqrt(_t27));
        float _t33 = _t30 * _t18;
        float _t34 = _t30 * _t19;
        float _t35 = _t30 * _t20;
        float _t36 = _t31 * _t17;
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t72, _t75, _t87;
        if (Math.abs(_t33) < Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (Math.abs(_t37) < Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77, _t89;
        if (Math.abs(_t40) < Math.abs(_t39)) {
            _t74 = _t41;
            _t77 = 0.0f;
            _t89 = -_t39;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
            _t89 = _t40;
        }
        float _t99 = (1.0f / (float) Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        float _t102 = _t99 * _t72;
        float _t103 = _t100 * _t73;
        float _t104 = _t101 * _t74;
        float _t105 = _t100 * _t76;
        float _t106 = _t99 * _t75;
        float _t107 = _t101 * _t77;
        float _t114 = _t100 * _t88;
        float _t115 = _t101 * _t89;
        float _t116 = _t99 * _t87;
        float _t165, _t167, _t170, _t166, _t168, _t171, _t169, _t172, _t173;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t167 = 1.0f;
                    _t170 = 0.0f;
                    _t166 = 0.0f;
                    _t168 = 0.0f;
                    _t171 = 1.0f;
                    _t169 = 0.0f;
                    _t172 = 0.0f;
                    _t173 = 1.0f;
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
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t182 = _t170 - _t166;
        float _t184 = _t170 + _t166;
        float _t194, _t195, _t196;
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0f) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        float _t199 = _t195 + _t165;
        float _t200 = _t196 + _t168;
        float _t201 = _t168 - _t196;
        float _t202 = _t195 - _t165;
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t208));
        if (_t206 > 0.0f) {
            dest[destOffset + 3] = _sp0 * _t182;
            dest[destOffset + 4] = _sp0 * _t201;
            dest[destOffset + 5] = _sp0 * _t202;
            dest[destOffset + 6] = 0.5f * (float) Math.sqrt(_t207);
        } else {
            if (_t194 > Math.max(_t167, _t171)) {
                dest[destOffset + 3] = 0.5f * (float) Math.sqrt(_t208);
                dest[destOffset + 4] = _sp3 * _t199;
                dest[destOffset + 5] = _sp3 * _t200;
                dest[destOffset + 6] = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    dest[destOffset + 3] = _sp1 * _t199;
                    dest[destOffset + 4] = 0.5f * (float) Math.sqrt(_t209);
                    dest[destOffset + 5] = _sp1 * _t184;
                    dest[destOffset + 6] = _sp1 * _t201;
                } else {
                    dest[destOffset + 3] = _sp2 * _t200;
                    dest[destOffset + 4] = _sp2 * _t184;
                    dest[destOffset + 5] = 0.5f * (float) Math.sqrt(_t210);
                    dest[destOffset + 6] = _sp2 * _t202;
                }
            }
        }
        dest[destOffset + 0] = _self03;
        dest[destOffset + 1] = _self13;
        dest[destOffset + 2] = _self23;
        return dest;
    }

    public static float[] toTransform_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _t0 = unitScale(_self01, _self11, _self21);
        float _t1 = unitScale(_self02, _self12, _self22);
        float _t2 = unitScale(_self00, _self10, _self20);
        float _t12 = _self21 * _t0;
        float _t13 = _self01 * _t0;
        float _t14 = _self11 * _t0;
        float _t15 = _self22 * _t1;
        float _t16 = _self02 * _t1;
        float _t17 = _self12 * _t1;
        float _t18 = _self20 * _t2;
        float _t19 = _self00 * _t2;
        float _t20 = _self10 * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t31 = (1.0f / (float) Math.sqrt(_t28));
        float _t32 = (1.0f / (float) Math.sqrt(_t27));
        float _t35 = _t30 * _t18;
        float _t36 = _t30 * _t19;
        float _t37 = _t30 * _t20;
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t56 = _t29 <= 0.0f ? 0.0f : (float) Math.sqrt(_t29) / _t2;
        float _t75, _t78, _t90;
        if (Math.abs(_t35) < Math.abs(_t36)) {
            _t75 = _t37;
            _t78 = 0.0f;
            _t90 = -_t36;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
            _t90 = _t35;
        }
        float _t76, _t79, _t91;
        if (Math.abs(_t39) < Math.abs(_t40)) {
            _t76 = _t38;
            _t79 = 0.0f;
            _t91 = -_t40;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
            _t91 = _t39;
        }
        float _t77, _t80, _t92;
        if (Math.abs(_t42) < Math.abs(_t41)) {
            _t77 = _t43;
            _t80 = 0.0f;
            _t92 = -_t41;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
            _t92 = _t42;
        }
        float _t102 = (1.0f / (float) Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        float _t105 = _t102 * _t75;
        float _t106 = _t103 * _t76;
        float _t107 = _t104 * _t77;
        float _t108 = _t103 * _t79;
        float _t109 = _t102 * _t78;
        float _t110 = _t104 * _t80;
        float _t117 = _t103 * _t91;
        float _t118 = _t104 * _t92;
        float _t119 = _t102 * _t90;
        float _t168, _t170, _t173, _t169, _t171, _t174, _t172, _t175, _t176;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t170 = 1.0f;
                    _t173 = 0.0f;
                    _t169 = 0.0f;
                    _t171 = 0.0f;
                    _t174 = 1.0f;
                    _t172 = 0.0f;
                    _t175 = 0.0f;
                    _t176 = 1.0f;
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
                if (_t29 <= 0.0f) {
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
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
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
                if (_t29 <= 0.0f) {
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
        float _t185 = _t173 - _t169;
        float _t186 = Math.max(_t170, _t174);
        float _t187 = _t173 + _t169;
        float _t196 = Math.fma(Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, Math.fma(Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        float _t197, _t198, _t199;
        if (_t196 < 0.0f) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        float _t202 = _t198 + _t168;
        float _t203 = _t199 + _t171;
        float _t204 = _t171 - _t199;
        float _t205 = _t198 - _t168;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t213));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t211));
        dest[destOffset + 0] = _self03;
        dest[destOffset + 1] = _self13;
        dest[destOffset + 2] = _self23;
        dest[destOffset + 3] = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        dest[destOffset + 4] = _t209 > 0.0f ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5f * (float) Math.sqrt(_t212) : _sp2 * _t187;
        dest[destOffset + 5] = _t209 > 0.0f ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5f * (float) Math.sqrt(_t213);
        dest[destOffset + 6] = _t209 > 0.0f ? 0.5f * (float) Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205;
        dest[destOffset + 7] = _t196 < 0.0f ? -_t56 : _t56;
        dest[destOffset + 8] = _t27 <= 0.0f ? 0.0f : (float) Math.sqrt(_t27) / _t0;
        dest[destOffset + 9] = _t28 <= 0.0f ? 0.0f : (float) Math.sqrt(_t28) / _t1;
        return dest;
    }

    public static float[] frustumAabb_no(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t1 = _self11 + _self31;
        float _t2 = _self22 + _self32;
        float _t3 = _self12 + _self32;
        float _t4 = _self21 + _self31;
        float _t6 = _self23 + _self33;
        float _t7 = _self13 + _self33;
        return frustumAabb_no_s4045e94f_1(dest, destOffset, _self03 + _self33, _t1, _t2, _t3, _t4, _self01 + _self31, _t6, _t7, _self02 + _self32, _self10 + _self30, _self20 + _self30, _self00 + _self30, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self03 - _self33, _self01 - _self31, _self02 - _self32, _self00 - _self30, _self11 - _self31, _self12 - _self32, _self13 - _self33, _self10 - _self30, _self23 - _self33, Math.fma(_t1, _t2, -(_t3 * _t4)), Math.fma(_t3, _t6, -(_t7 * _t2)));
    }

    /** Piece 2 of {@code frustumAabb_no}, split to fit the inline budget; reached only through it. */
    private static float[] frustumAabb_no_s4045e94f_1(float[] dest, int destOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t72, float _t73) {
        return frustumAabb_no_s4045e94f_2(dest, destOffset, _t0, _t5, _t8, _t11, _t15, _t16, _t17, _t18, _t72, _t73, Math.fma(_t1, _t6, -(_t7 * _t4)), Math.fma(_t9, _t4, -(_t1 * _t10)), Math.fma(_t9, _t2, -(_t3 * _t10)), Math.fma(_t9, _t12, -(_t1 * _t13)), Math.fma(_t1, _t14, -(_t3 * _t12)), Math.fma(_t9, _t14, -(_t3 * _t13)), Math.fma(_t2, _t19, -(_t4 * _t20)), Math.fma(_t6, _t20, -(_t2 * _t21)), Math.fma(_t6, _t19, -(_t4 * _t21)), Math.fma(_t4, _t22, -(_t10 * _t19)), Math.fma(_t2, _t22, -(_t10 * _t20)), Math.fma(_t22, _t12, -(_t19 * _t13)), Math.fma(_t19, _t14, -(_t20 * _t12)), Math.fma(_t22, _t14, -(_t20 * _t13)), Math.fma(_t3, _t23, -(_t7 * _t14)), Math.fma(_t1, _t23, -(_t7 * _t12)), Math.fma(_t20, _t23, -(_t21 * _t14)), Math.fma(_t19, _t23, -(_t21 * _t12)), Math.fma(_t9, _t6, -(_t7 * _t10)), Math.fma(_t6, _t22, -(_t10 * _t21)), Math.fma(_t9, _t23, -(_t7 * _t13)), Math.fma(_t22, _t23, -(_t21 * _t13)));
    }

    /** Piece 3 of {@code frustumAabb_no}, split to fit the inline budget; reached only through it. */
    private static float[] frustumAabb_no_s4045e94f_2(float[] dest, int destOffset, float _t0, float _t5, float _t8, float _t11, float _t15, float _t16, float _t17, float _t18, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t79, float _t80, float _t81, float _t82, float _t83, float _t84, float _t85, float _t86, float _t87, float _t88, float _t89, float _t90, float _t91, float _t92, float _t93, float _t94, float _t95) {
        float _t193 = Math.fma(_t8, _t75, Math.fma(_t11, _t72, -(_t5 * _t76)));
        float _t194 = Math.fma(_t8, _t77, Math.fma(_t11, _t78, -(_t5 * _t79)));
        float _t196 = Math.fma(_t17, _t75, Math.fma(_t18, _t72, -(_t16 * _t76)));
        float _t197 = Math.fma(_t17, _t77, Math.fma(_t18, _t78, -(_t16 * _t79)));
        float _t199 = Math.fma(_t17, _t83, Math.fma(_t18, _t80, -(_t16 * _t84)));
        float _t200 = Math.fma(_t17, _t85, Math.fma(_t18, _t86, -(_t16 * _t87)));
        float _t202 = Math.fma(_t8, _t83, Math.fma(_t11, _t80, -(_t5 * _t84)));
        float _t203 = Math.fma(_t8, _t85, Math.fma(_t11, _t86, -(_t5 * _t87)));
        return frustumAabb_no_s4045e94f_3(dest, destOffset, _t0, _t5, _t8, _t11, _t15, _t16, _t17, _t18, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t79, _t80, _t81, _t82, _t83, _t84, _t85, _t86, _t87, _t88, _t89, _t90, _t91, _t92, _t93, _t94, _t95, _t193, _t194, _t196, _t197, _t199, _t200, _t202, _t203, Math.abs(_t193), Math.abs(_t194), Math.abs(_t196), Math.abs(_t197), Math.abs(_t199), Math.abs(_t200), Math.abs(_t202), Math.abs(_t203));
    }

    /** Piece 4 of {@code frustumAabb_no}, split to fit the inline budget; reached only through it. */
    private static float[] frustumAabb_no_s4045e94f_3(float[] dest, int destOffset, float _t0, float _t5, float _t8, float _t11, float _t15, float _t16, float _t17, float _t18, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t79, float _t80, float _t81, float _t82, float _t83, float _t84, float _t85, float _t86, float _t87, float _t88, float _t89, float _t90, float _t91, float _t92, float _t93, float _t94, float _t95, float _t193, float _t194, float _t196, float _t197, float _t199, float _t200, float _t202, float _t203, float _t224, float _t225, float _t226, float _t227, float _t228, float _t229, float _t230, float _t231) {
        float _t248_inv = 1.0f / (_t224 > _t225 * 9.536743E-7f ? _t193 : Math.copySign(0.0f, _t194));
        return frustumAabb_no_s4045e94f_4(dest, destOffset, _t0, _t5, _t8, _t11, _t15, _t16, _t17, _t18, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t79, _t80, _t81, _t82, _t83, _t84, _t85, _t86, _t87, _t88, _t89, _t90, _t91, _t92, _t93, _t94, _t95, _t248_inv, 1.0f / (_t226 > _t227 * 9.536743E-7f ? _t196 : Math.copySign(0.0f, _t197)), 1.0f / (_t228 > _t229 * 9.536743E-7f ? _t199 : Math.copySign(0.0f, _t200)), 1.0f / (_t230 > _t231 * 9.536743E-7f ? _t202 : Math.copySign(0.0f, _t203)), 1.0f / (_t225 > _t224 * 9.536743E-7f ? _t194 : Math.copySign(0.0f, _t193)), 1.0f / (_t227 > _t226 * 9.536743E-7f ? _t197 : Math.copySign(0.0f, _t196)), 1.0f / (_t229 > _t228 * 9.536743E-7f ? _t200 : Math.copySign(0.0f, _t199)), 1.0f / (_t231 > _t230 * 9.536743E-7f ? _t203 : Math.copySign(0.0f, _t202)), Math.fma(_t0, _t76, Math.fma(_t11, _t73, -(_t8 * _t92))) * _t248_inv);
    }

    /** Piece 5 of {@code frustumAabb_no}, split to fit the inline budget; reached only through it. */
    private static float[] frustumAabb_no_s4045e94f_4(float[] dest, int destOffset, float _t0, float _t5, float _t8, float _t11, float _t15, float _t16, float _t17, float _t18, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t79, float _t80, float _t81, float _t82, float _t83, float _t84, float _t85, float _t86, float _t87, float _t88, float _t89, float _t90, float _t91, float _t92, float _t93, float _t94, float _t95, float _t248_inv, float _t249_inv, float _t250_inv, float _t251_inv, float _t252_inv, float _t253_inv, float _t254_inv, float _t255_inv, float _t264) {
        return frustumAabb_no_s4045e94f_5(dest, destOffset, _t0, _t5, _t8, _t11, _t15, _t16, _t17, _t18, _t74, _t75, _t77, _t78, _t80, _t81, _t82, _t83, _t85, _t86, _t88, _t89, _t90, _t91, _t92, _t93, _t94, _t95, _t248_inv, _t249_inv, _t250_inv, _t251_inv, _t252_inv, _t253_inv, _t254_inv, _t255_inv, _t264, Math.fma(_t15, _t76, Math.fma(_t18, _t73, -(_t17 * _t92))) * _t249_inv, Math.fma(_t15, _t84, Math.fma(_t18, _t81, -(_t17 * _t93))) * _t250_inv, Math.fma(_t0, _t84, Math.fma(_t11, _t81, -(_t8 * _t93))) * _t251_inv, Math.fma(_t0, _t79, Math.fma(_t11, _t88, -(_t8 * _t94))) * _t252_inv, Math.fma(_t15, _t79, Math.fma(_t18, _t88, -(_t17 * _t94))) * _t253_inv, Math.fma(_t15, _t87, Math.fma(_t18, _t90, -(_t17 * _t95))) * _t254_inv, Math.fma(_t0, _t87, Math.fma(_t11, _t90, -(_t8 * _t95))) * _t255_inv, -(Math.fma(_t0, _t72, Math.fma(_t5, _t73, -(_t8 * _t74))) * _t248_inv), -(Math.fma(_t15, _t72, Math.fma(_t16, _t73, -(_t17 * _t74))) * _t249_inv), -(Math.fma(_t15, _t80, Math.fma(_t16, _t81, -(_t17 * _t82))) * _t250_inv));
    }

    /** Piece 6 of {@code frustumAabb_no}, split to fit the inline budget; reached only through it. */
    private static float[] frustumAabb_no_s4045e94f_5(float[] dest, int destOffset, float _t0, float _t5, float _t8, float _t11, float _t15, float _t16, float _t17, float _t18, float _t74, float _t75, float _t77, float _t78, float _t80, float _t81, float _t82, float _t83, float _t85, float _t86, float _t88, float _t89, float _t90, float _t91, float _t92, float _t93, float _t94, float _t95, float _t248_inv, float _t249_inv, float _t250_inv, float _t251_inv, float _t252_inv, float _t253_inv, float _t254_inv, float _t255_inv, float _t264, float _t265, float _t266, float _t267, float _t268, float _t269, float _t270, float _t271, float _t280, float _t281, float _t282) {
        return frustumAabb_no_s4045e94f_6(dest, destOffset, _t0, _t5, _t11, _t15, _t16, _t18, _t85, _t91, _t95, _t254_inv, _t255_inv, _t264, _t265, _t266, _t267, _t268, _t269, _t270, _t271, _t280, _t281, _t282, -(Math.fma(_t0, _t80, Math.fma(_t5, _t81, -(_t8 * _t82))) * _t251_inv), -(Math.fma(_t0, _t78, Math.fma(_t5, _t88, -(_t8 * _t89))) * _t252_inv), -(Math.fma(_t15, _t78, Math.fma(_t16, _t88, -(_t17 * _t89))) * _t253_inv), -(Math.fma(_t15, _t86, Math.fma(_t16, _t90, -(_t17 * _t91))) * _t254_inv), -(Math.fma(_t0, _t86, Math.fma(_t5, _t90, -(_t8 * _t91))) * _t255_inv), -(Math.fma(_t0, _t75, Math.fma(_t11, _t74, -(_t5 * _t92))) * _t248_inv), -(Math.fma(_t15, _t75, Math.fma(_t18, _t74, -(_t16 * _t92))) * _t249_inv), -(Math.fma(_t15, _t83, Math.fma(_t18, _t82, -(_t16 * _t93))) * _t250_inv), -(Math.fma(_t0, _t83, Math.fma(_t11, _t82, -(_t5 * _t93))) * _t251_inv), -(Math.fma(_t0, _t77, Math.fma(_t11, _t89, -(_t5 * _t94))) * _t252_inv), -(Math.fma(_t15, _t77, Math.fma(_t18, _t89, -(_t16 * _t94))) * _t253_inv));
    }

    /** Piece 7 of {@code frustumAabb_no}, split to fit the inline budget; reached only through it. */
    private static float[] frustumAabb_no_s4045e94f_6(float[] dest, int destOffset, float _t0, float _t5, float _t11, float _t15, float _t16, float _t18, float _t85, float _t91, float _t95, float _t254_inv, float _t255_inv, float _t264, float _t265, float _t266, float _t267, float _t268, float _t269, float _t270, float _t271, float _t280, float _t281, float _t282, float _t283, float _t284, float _t285, float _t286, float _t287, float _t288, float _t289, float _t290, float _t291, float _t292, float _t293) {
        float _t294 = -(Math.fma(_t15, _t85, Math.fma(_t18, _t91, -(_t16 * _t95))) * _t254_inv);
        float _t295 = -(Math.fma(_t0, _t85, Math.fma(_t11, _t91, -(_t5 * _t95))) * _t255_inv);
        dest[destOffset + 0] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t280, _t281), _t282), _t283), _t284), _t285), _t286), _t287);
        dest[destOffset + 1] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t264, _t265), _t266), _t267), _t268), _t269), _t270), _t271);
        dest[destOffset + 2] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t288, _t289), _t290), _t291), _t292), _t293), _t294), _t295);
        dest[destOffset + 3] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t280, _t281), _t282), _t283), _t284), _t285), _t286), _t287);
        dest[destOffset + 4] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t264, _t265), _t266), _t267), _t268), _t269), _t270), _t271);
        dest[destOffset + 5] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t288, _t289), _t290), _t291), _t292), _t293), _t294), _t295);
        return dest;
    }

    public static float[] frustumAabb_zo(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t1 = _self11 + _self31;
        float _t2 = _self12 + _self32;
        float _t4 = _self13 + _self33;
        float _t6 = _self10 + _self30;
        return frustumAabb_zo_sc5561463_1(dest, destOffset, _self20, _self21, _self22, _self23, _self03 + _self33, _t1, _t2, _self01 + _self31, _t4, _self02 + _self32, _t6, _self00 + _self30, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self03 - _self33, _self01 - _self31, _self02 - _self32, _self00 - _self30, _self11 - _self31, _self12 - _self32, _self13 - _self33, _self10 - _self30, _self23 - _self33, Math.fma(_self22, _t1, -(_self21 * _t2)), Math.fma(_self23, _t2, -(_self22 * _t4)), Math.fma(_self23, _t1, -(_self21 * _t4)), Math.fma(_self21, _t6, -(_self20 * _t1)));
    }

    /** Piece 2 of {@code frustumAabb_zo}, split to fit the inline budget; reached only through it. */
    private static float[] frustumAabb_zo_sc5561463_1(float[] dest, int destOffset, float _self20, float _self21, float _self22, float _self23, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t68, float _t69, float _t70, float _t71) {
        float _t72 = Math.fma(_self22, _t6, -(_self20 * _t2));
        return frustumAabb_zo_sc5561463_2(dest, destOffset, _t0, _t3, _t5, _t7, _t11, _t12, _t13, _t14, _t68, _t69, _t70, _t71, _t72, Math.fma(_self22, _t15, -(_self21 * _t16)), Math.fma(_self23, _t16, -(_self22 * _t17)), Math.fma(_self23, _t15, -(_self21 * _t17)), Math.fma(_self21, _t18, -(_self20 * _t15)), Math.fma(_self22, _t18, -(_self20 * _t16)), Math.fma(_self23, _t6, -(_self20 * _t4)), Math.fma(_self23, _t18, -(_self20 * _t17)), Math.fma(_t6, _t8, -(_t1 * _t9)), Math.fma(_t1, _t10, -(_t2 * _t8)), Math.fma(_t6, _t10, -(_t2 * _t9)), Math.fma(_t18, _t8, -(_t15 * _t9)), Math.fma(_t15, _t10, -(_t16 * _t8)), Math.fma(_t18, _t10, -(_t16 * _t9)), Math.fma(_t2, _t19, -(_t4 * _t10)), Math.fma(_t1, _t19, -(_t4 * _t8)), Math.fma(_t16, _t19, -(_t17 * _t10)), Math.fma(_t15, _t19, -(_t17 * _t8)), Math.fma(_t6, _t19, -(_t4 * _t9)), Math.fma(_t18, _t19, -(_t17 * _t9)), Math.fma(_t5, _t71, Math.fma(_t7, _t68, -(_t3 * _t72))));
    }

    /** Piece 3 of {@code frustumAabb_zo}, split to fit the inline budget; reached only through it. */
    private static float[] frustumAabb_zo_sc5561463_2(float[] dest, int destOffset, float _t0, float _t3, float _t5, float _t7, float _t11, float _t12, float _t13, float _t14, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t79, float _t82, float _t83, float _t84, float _t89, float _t90, float _t91, float _t94, float _t95, float _t96, float _t97, float _t102, float _t103, float _t189) {
        float _t191 = Math.fma(_t13, _t71, Math.fma(_t14, _t68, -(_t12 * _t72)));
        float _t193 = Math.fma(_t13, _t76, Math.fma(_t14, _t73, -(_t12 * _t77)));
        float _t195 = Math.fma(_t5, _t76, Math.fma(_t7, _t73, -(_t3 * _t77)));
        float _t204 = Math.abs(_t189);
        float _t216 = Math.fma(_t5, _t82, Math.fma(_t7, _t83, -(_t3 * _t84)));
        float _t217 = Math.fma(_t13, _t82, Math.fma(_t14, _t83, -(_t12 * _t84)));
        float _t218 = Math.fma(_t13, _t89, Math.fma(_t14, _t90, -(_t12 * _t91)));
        float _t219 = Math.fma(_t5, _t89, Math.fma(_t7, _t90, -(_t3 * _t91)));
        float _t232 = Math.abs(_t216);
        return frustumAabb_zo_sc5561463_3(dest, destOffset, _t0, _t3, _t5, _t7, _t11, _t12, _t13, _t14, _t68, _t69, _t70, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t79, _t82, _t83, _t84, _t89, _t90, _t91, _t94, _t95, _t96, _t97, _t102, _t103, _t189, _t191, _t193, _t195, _t204, Math.abs(_t191), Math.abs(_t193), Math.abs(_t195), _t216, _t217, _t218, _t219, _t232, Math.abs(_t217), Math.abs(_t218), Math.abs(_t219), 1.0f / (_t204 > _t232 * 9.536743E-7f ? _t189 : Math.copySign(0.0f, _t216)));
    }

    /** Piece 4 of {@code frustumAabb_zo}, split to fit the inline budget; reached only through it. */
    private static float[] frustumAabb_zo_sc5561463_3(float[] dest, int destOffset, float _t0, float _t3, float _t5, float _t7, float _t11, float _t12, float _t13, float _t14, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t79, float _t82, float _t83, float _t84, float _t89, float _t90, float _t91, float _t94, float _t95, float _t96, float _t97, float _t102, float _t103, float _t189, float _t191, float _t193, float _t195, float _t204, float _t205, float _t206, float _t207, float _t216, float _t217, float _t218, float _t219, float _t232, float _t233, float _t234, float _t235, float _t244_inv) {
        float _t245_inv = 1.0f / (_t205 > _t233 * 9.536743E-7f ? _t191 : Math.copySign(0.0f, _t217));
        return frustumAabb_zo_sc5561463_4(dest, destOffset, _t0, _t3, _t5, _t7, _t11, _t12, _t13, _t14, _t68, _t69, _t70, _t71, _t73, _t74, _t75, _t76, _t77, _t78, _t79, _t82, _t83, _t84, _t89, _t90, _t91, _t94, _t95, _t96, _t97, _t102, _t103, _t244_inv, _t245_inv, 1.0f / (_t206 > _t234 * 9.536743E-7f ? _t193 : Math.copySign(0.0f, _t218)), 1.0f / (_t207 > _t235 * 9.536743E-7f ? _t195 : Math.copySign(0.0f, _t219)), 1.0f / (_t232 > _t204 * 9.536743E-7f ? _t216 : Math.copySign(0.0f, _t189)), 1.0f / (_t233 > _t205 * 9.536743E-7f ? _t217 : Math.copySign(0.0f, _t191)), 1.0f / (_t234 > _t206 * 9.536743E-7f ? _t218 : Math.copySign(0.0f, _t193)), 1.0f / (_t235 > _t207 * 9.536743E-7f ? _t219 : Math.copySign(0.0f, _t195)), Math.fma(_t0, _t72, Math.fma(_t7, _t69, -(_t5 * _t78))) * _t244_inv, Math.fma(_t11, _t72, Math.fma(_t14, _t69, -(_t13 * _t78))) * _t245_inv);
    }

    /** Piece 5 of {@code frustumAabb_zo}, split to fit the inline budget; reached only through it. */
    private static float[] frustumAabb_zo_sc5561463_4(float[] dest, int destOffset, float _t0, float _t3, float _t5, float _t7, float _t11, float _t12, float _t13, float _t14, float _t68, float _t69, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t79, float _t82, float _t83, float _t84, float _t89, float _t90, float _t91, float _t94, float _t95, float _t96, float _t97, float _t102, float _t103, float _t244_inv, float _t245_inv, float _t246_inv, float _t247_inv, float _t248_inv, float _t249_inv, float _t250_inv, float _t251_inv, float _t256, float _t257) {
        return frustumAabb_zo_sc5561463_5(dest, destOffset, _t0, _t3, _t5, _t7, _t11, _t12, _t13, _t14, _t82, _t83, _t84, _t89, _t90, _t91, _t94, _t95, _t96, _t97, _t102, _t103, _t248_inv, _t249_inv, _t250_inv, _t251_inv, _t256, _t257, Math.fma(_t11, _t77, Math.fma(_t14, _t74, -(_t13 * _t79))) * _t246_inv, Math.fma(_t0, _t77, Math.fma(_t7, _t74, -(_t5 * _t79))) * _t247_inv, -(Math.fma(_t0, _t68, Math.fma(_t3, _t69, -(_t5 * _t70))) * _t244_inv), -(Math.fma(_t11, _t68, Math.fma(_t12, _t69, -(_t13 * _t70))) * _t245_inv), -(Math.fma(_t11, _t73, Math.fma(_t12, _t74, -(_t13 * _t75))) * _t246_inv), -(Math.fma(_t0, _t73, Math.fma(_t3, _t74, -(_t5 * _t75))) * _t247_inv), -(Math.fma(_t0, _t71, Math.fma(_t7, _t70, -(_t3 * _t78))) * _t244_inv), -(Math.fma(_t11, _t71, Math.fma(_t14, _t70, -(_t12 * _t78))) * _t245_inv), -(Math.fma(_t11, _t76, Math.fma(_t14, _t75, -(_t12 * _t79))) * _t246_inv), -(Math.fma(_t0, _t76, Math.fma(_t7, _t75, -(_t3 * _t79))) * _t247_inv), Math.fma(_t0, _t84, Math.fma(_t7, _t94, -(_t5 * _t102))) * _t248_inv);
    }

    /** Piece 6 of {@code frustumAabb_zo}, split to fit the inline budget; reached only through it. */
    private static float[] frustumAabb_zo_sc5561463_5(float[] dest, int destOffset, float _t0, float _t3, float _t5, float _t7, float _t11, float _t12, float _t13, float _t14, float _t82, float _t83, float _t84, float _t89, float _t90, float _t91, float _t94, float _t95, float _t96, float _t97, float _t102, float _t103, float _t248_inv, float _t249_inv, float _t250_inv, float _t251_inv, float _t256, float _t257, float _t258, float _t259, float _t264, float _t265, float _t266, float _t267, float _t268, float _t269, float _t270, float _t271, float _t276) {
        return frustumAabb_zo_sc5561463_6(dest, destOffset, _t256, _t257, _t258, _t259, _t264, _t265, _t266, _t267, _t268, _t269, _t270, _t271, _t276, Math.fma(_t11, _t84, Math.fma(_t14, _t94, -(_t13 * _t102))) * _t249_inv, Math.fma(_t11, _t91, Math.fma(_t14, _t96, -(_t13 * _t103))) * _t250_inv, Math.fma(_t0, _t91, Math.fma(_t7, _t96, -(_t5 * _t103))) * _t251_inv, -(Math.fma(_t0, _t83, Math.fma(_t3, _t94, -(_t5 * _t95))) * _t248_inv), -(Math.fma(_t11, _t83, Math.fma(_t12, _t94, -(_t13 * _t95))) * _t249_inv), -(Math.fma(_t11, _t90, Math.fma(_t12, _t96, -(_t13 * _t97))) * _t250_inv), -(Math.fma(_t0, _t90, Math.fma(_t3, _t96, -(_t5 * _t97))) * _t251_inv), -(Math.fma(_t0, _t82, Math.fma(_t7, _t95, -(_t3 * _t102))) * _t248_inv), -(Math.fma(_t11, _t82, Math.fma(_t14, _t95, -(_t12 * _t102))) * _t249_inv), -(Math.fma(_t11, _t89, Math.fma(_t14, _t97, -(_t12 * _t103))) * _t250_inv), -(Math.fma(_t0, _t89, Math.fma(_t7, _t97, -(_t3 * _t103))) * _t251_inv));
    }

    /** Piece 7 of {@code frustumAabb_zo}, split to fit the inline budget; reached only through it. */
    private static float[] frustumAabb_zo_sc5561463_6(float[] dest, int destOffset, float _t256, float _t257, float _t258, float _t259, float _t264, float _t265, float _t266, float _t267, float _t268, float _t269, float _t270, float _t271, float _t276, float _t277, float _t278, float _t279, float _t284, float _t285, float _t286, float _t287, float _t288, float _t289, float _t290, float _t291) {
        dest[destOffset + 0] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t264, _t265), _t266), _t267), _t284), _t285), _t286), _t287);
        dest[destOffset + 1] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t256, _t257), _t258), _t259), _t276), _t277), _t278), _t279);
        dest[destOffset + 2] = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t268, _t269), _t270), _t271), _t288), _t289), _t290), _t291);
        dest[destOffset + 3] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t264, _t265), _t266), _t267), _t284), _t285), _t286), _t287);
        dest[destOffset + 4] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t256, _t257), _t258), _t259), _t276), _t277), _t278), _t279);
        dest[destOffset + 5] = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t268, _t269), _t270), _t271), _t288), _t289), _t290), _t291);
        return dest;
    }

    public static float[] frustumCorner_no(float[] dest, int destOffset, float[] src, int srcOffset, FrustumCorner corner) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = _self03 + _self33;
        float _t1 = _self11 + _self31;
        float _t2 = _self22 + _self32;
        float _t3 = _self12 + _self32;
        float _t4 = _self21 + _self31;
        float _t5 = _self01 + _self31;
        float _t6 = _self23 + _self33;
        float _t7 = _self13 + _self33;
        float _t8 = _self02 + _self32;
        float _t9 = _self10 + _self30;
        float _t10 = _self20 + _self30;
        float _t11 = _self00 + _self30;
        float _t12 = _self21 - _self31;
        float _t13 = _self20 - _self30;
        float _t14 = _self22 - _self32;
        float _t15 = _self03 - _self33;
        float _t16 = _self01 - _self31;
        float _t17 = _self02 - _self32;
        float _t18 = _self00 - _self30;
        float _t19 = _self11 - _self31;
        float _t20 = _self12 - _self32;
        float _t21 = _self13 - _self33;
        float _t22 = _self10 - _self30;
        float _t23 = _self23 - _self33;
        float _t72 = Math.fma(_t1, _t2, -(_t3 * _t4));
        float _t73 = Math.fma(_t3, _t6, -(_t7 * _t2));
        float _t74 = Math.fma(_t1, _t6, -(_t7 * _t4));
        float _t75 = Math.fma(_t9, _t4, -(_t1 * _t10));
        float _t76 = Math.fma(_t9, _t2, -(_t3 * _t10));
        float _t77 = Math.fma(_t9, _t12, -(_t1 * _t13));
        float _t78 = Math.fma(_t1, _t14, -(_t3 * _t12));
        float _t79 = Math.fma(_t9, _t14, -(_t3 * _t13));
        float _t80 = Math.fma(_t2, _t19, -(_t4 * _t20));
        float _t81 = Math.fma(_t6, _t20, -(_t2 * _t21));
        float _t82 = Math.fma(_t6, _t19, -(_t4 * _t21));
        float _t83 = Math.fma(_t4, _t22, -(_t10 * _t19));
        float _t84 = Math.fma(_t2, _t22, -(_t10 * _t20));
        float _t85 = Math.fma(_t22, _t12, -(_t19 * _t13));
        float _t86 = Math.fma(_t19, _t14, -(_t20 * _t12));
        float _t87 = Math.fma(_t22, _t14, -(_t20 * _t13));
        float _t88 = Math.fma(_t3, _t23, -(_t7 * _t14));
        float _t89 = Math.fma(_t1, _t23, -(_t7 * _t12));
        float _t90 = Math.fma(_t20, _t23, -(_t21 * _t14));
        float _t91 = Math.fma(_t19, _t23, -(_t21 * _t12));
        float _t92 = Math.fma(_t9, _t6, -(_t7 * _t10));
        float _t93 = Math.fma(_t6, _t22, -(_t10 * _t21));
        float _t94 = Math.fma(_t9, _t23, -(_t7 * _t13));
        float _t95 = Math.fma(_t22, _t23, -(_t21 * _t13));
        float _t120 = Math.fma(_t8, _t75, Math.fma(_t11, _t72, -(_t5 * _t76)));
        float _t121 = Math.fma(_t8, _t77, Math.fma(_t11, _t78, -(_t5 * _t79)));
        float _t122 = Math.fma(_t17, _t75, Math.fma(_t18, _t72, -(_t16 * _t76)));
        float _t123 = Math.fma(_t17, _t77, Math.fma(_t18, _t78, -(_t16 * _t79)));
        float _t124 = Math.fma(_t17, _t83, Math.fma(_t18, _t80, -(_t16 * _t84)));
        float _t125 = Math.fma(_t17, _t85, Math.fma(_t18, _t86, -(_t16 * _t87)));
        float _t126 = Math.fma(_t8, _t83, Math.fma(_t11, _t80, -(_t5 * _t84)));
        float _t127 = Math.fma(_t8, _t85, Math.fma(_t11, _t86, -(_t5 * _t87)));
        float _t128 = Math.abs(_t120);
        float _t129 = Math.abs(_t121);
        float _t130 = Math.abs(_t122);
        float _t131 = Math.abs(_t123);
        float _t132 = Math.abs(_t124);
        float _t133 = Math.abs(_t125);
        float _t134 = Math.abs(_t126);
        float _t135 = Math.abs(_t127);
        float _t152_inv = 1.0f / (_t128 > _t129 * 9.536743E-7f ? _t120 : Math.copySign(0.0f, _t121));
        float _t153_inv = 1.0f / (_t130 > _t131 * 9.536743E-7f ? _t122 : Math.copySign(0.0f, _t123));
        float _t154_inv = 1.0f / (_t132 > _t133 * 9.536743E-7f ? _t124 : Math.copySign(0.0f, _t125));
        float _t155_inv = 1.0f / (_t134 > _t135 * 9.536743E-7f ? _t126 : Math.copySign(0.0f, _t127));
        float _t156_inv = 1.0f / (_t129 > _t128 * 9.536743E-7f ? _t121 : Math.copySign(0.0f, _t120));
        float _t157_inv = 1.0f / (_t131 > _t130 * 9.536743E-7f ? _t123 : Math.copySign(0.0f, _t122));
        float _t158_inv = 1.0f / (_t133 > _t132 * 9.536743E-7f ? _t125 : Math.copySign(0.0f, _t124));
        float _t159_inv = 1.0f / (_t135 > _t134 * 9.536743E-7f ? _t127 : Math.copySign(0.0f, _t126));
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (corner) {
            case NXNYNZ: _idxSw0 = -(Math.fma(_t0, _t72, Math.fma(_t5, _t73, -(_t8 * _t74))) * _t152_inv); _idxSw1 = Math.fma(_t0, _t76, Math.fma(_t11, _t73, -(_t8 * _t92))) * _t152_inv; _idxSw2 = -(Math.fma(_t0, _t75, Math.fma(_t11, _t74, -(_t5 * _t92))) * _t152_inv); break;
            case PXNYNZ: _idxSw0 = -(Math.fma(_t15, _t72, Math.fma(_t16, _t73, -(_t17 * _t74))) * _t153_inv); _idxSw1 = Math.fma(_t15, _t76, Math.fma(_t18, _t73, -(_t17 * _t92))) * _t153_inv; _idxSw2 = -(Math.fma(_t15, _t75, Math.fma(_t18, _t74, -(_t16 * _t92))) * _t153_inv); break;
            case PXPYNZ: _idxSw0 = -(Math.fma(_t15, _t80, Math.fma(_t16, _t81, -(_t17 * _t82))) * _t154_inv); _idxSw1 = Math.fma(_t15, _t84, Math.fma(_t18, _t81, -(_t17 * _t93))) * _t154_inv; _idxSw2 = -(Math.fma(_t15, _t83, Math.fma(_t18, _t82, -(_t16 * _t93))) * _t154_inv); break;
            case NXPYNZ: _idxSw0 = -(Math.fma(_t0, _t80, Math.fma(_t5, _t81, -(_t8 * _t82))) * _t155_inv); _idxSw1 = Math.fma(_t0, _t84, Math.fma(_t11, _t81, -(_t8 * _t93))) * _t155_inv; _idxSw2 = -(Math.fma(_t0, _t83, Math.fma(_t11, _t82, -(_t5 * _t93))) * _t155_inv); break;
            case NXNYPZ: _idxSw0 = -(Math.fma(_t0, _t78, Math.fma(_t5, _t88, -(_t8 * _t89))) * _t156_inv); _idxSw1 = Math.fma(_t0, _t79, Math.fma(_t11, _t88, -(_t8 * _t94))) * _t156_inv; _idxSw2 = -(Math.fma(_t0, _t77, Math.fma(_t11, _t89, -(_t5 * _t94))) * _t156_inv); break;
            case PXNYPZ: _idxSw0 = -(Math.fma(_t15, _t78, Math.fma(_t16, _t88, -(_t17 * _t89))) * _t157_inv); _idxSw1 = Math.fma(_t15, _t79, Math.fma(_t18, _t88, -(_t17 * _t94))) * _t157_inv; _idxSw2 = -(Math.fma(_t15, _t77, Math.fma(_t18, _t89, -(_t16 * _t94))) * _t157_inv); break;
            case PXPYPZ: _idxSw0 = -(Math.fma(_t15, _t86, Math.fma(_t16, _t90, -(_t17 * _t91))) * _t158_inv); _idxSw1 = Math.fma(_t15, _t87, Math.fma(_t18, _t90, -(_t17 * _t95))) * _t158_inv; _idxSw2 = -(Math.fma(_t15, _t85, Math.fma(_t18, _t91, -(_t16 * _t95))) * _t158_inv); break;
            case NXPYPZ: _idxSw0 = -(Math.fma(_t0, _t86, Math.fma(_t5, _t90, -(_t8 * _t91))) * _t159_inv); _idxSw1 = Math.fma(_t0, _t87, Math.fma(_t11, _t90, -(_t8 * _t95))) * _t159_inv; _idxSw2 = -(Math.fma(_t0, _t85, Math.fma(_t11, _t91, -(_t5 * _t95))) * _t159_inv); break;
            default: throw new IllegalArgumentException("Unknown FrustumCorner: " + corner);
        }
        dest[destOffset + 0] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        return dest;
    }

    public static float[] frustumCorner_zo(float[] dest, int destOffset, float[] src, int srcOffset, FrustumCorner corner) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = _self03 + _self33;
        float _t1 = _self11 + _self31;
        float _t2 = _self12 + _self32;
        float _t3 = _self01 + _self31;
        float _t4 = _self13 + _self33;
        float _t5 = _self02 + _self32;
        float _t6 = _self10 + _self30;
        float _t7 = _self00 + _self30;
        float _t8 = _self21 - _self31;
        float _t9 = _self20 - _self30;
        float _t10 = _self22 - _self32;
        float _t11 = _self03 - _self33;
        float _t12 = _self01 - _self31;
        float _t13 = _self02 - _self32;
        float _t14 = _self00 - _self30;
        float _t15 = _self11 - _self31;
        float _t16 = _self12 - _self32;
        float _t17 = _self13 - _self33;
        float _t18 = _self10 - _self30;
        float _t19 = _self23 - _self33;
        float _t68 = Math.fma(_self22, _t1, -(_self21 * _t2));
        float _t69 = Math.fma(_self23, _t2, -(_self22 * _t4));
        float _t70 = Math.fma(_self23, _t1, -(_self21 * _t4));
        float _t71 = Math.fma(_self21, _t6, -(_self20 * _t1));
        float _t72 = Math.fma(_self22, _t6, -(_self20 * _t2));
        float _t73 = Math.fma(_self22, _t15, -(_self21 * _t16));
        float _t74 = Math.fma(_self23, _t16, -(_self22 * _t17));
        float _t75 = Math.fma(_self23, _t15, -(_self21 * _t17));
        float _t76 = Math.fma(_self21, _t18, -(_self20 * _t15));
        float _t77 = Math.fma(_self22, _t18, -(_self20 * _t16));
        float _t78 = Math.fma(_self23, _t6, -(_self20 * _t4));
        float _t79 = Math.fma(_self23, _t18, -(_self20 * _t17));
        float _t81 = Math.fma(_t6, _t8, -(_t1 * _t9));
        float _t82 = Math.fma(_t1, _t10, -(_t2 * _t8));
        float _t83 = Math.fma(_t6, _t10, -(_t2 * _t9));
        float _t86 = Math.fma(_t18, _t8, -(_t15 * _t9));
        float _t87 = Math.fma(_t15, _t10, -(_t16 * _t8));
        float _t88 = Math.fma(_t18, _t10, -(_t16 * _t9));
        float _t90 = Math.fma(_t2, _t19, -(_t4 * _t10));
        float _t91 = Math.fma(_t1, _t19, -(_t4 * _t8));
        float _t92 = Math.fma(_t16, _t19, -(_t17 * _t10));
        float _t93 = Math.fma(_t15, _t19, -(_t17 * _t8));
        float _t94 = Math.fma(_t6, _t19, -(_t4 * _t9));
        float _t95 = Math.fma(_t18, _t19, -(_t17 * _t9));
        float _t116 = Math.fma(_t5, _t71, Math.fma(_t7, _t68, -(_t3 * _t72)));
        float _t117 = Math.fma(_t13, _t71, Math.fma(_t14, _t68, -(_t12 * _t72)));
        float _t118 = Math.fma(_t13, _t76, Math.fma(_t14, _t73, -(_t12 * _t77)));
        float _t119 = Math.fma(_t5, _t76, Math.fma(_t7, _t73, -(_t3 * _t77)));
        float _t120 = Math.abs(_t116);
        float _t121 = Math.abs(_t117);
        float _t122 = Math.abs(_t118);
        float _t123 = Math.abs(_t119);
        float _t132 = Math.fma(_t5, _t81, Math.fma(_t7, _t82, -(_t3 * _t83)));
        float _t133 = Math.fma(_t13, _t81, Math.fma(_t14, _t82, -(_t12 * _t83)));
        float _t134 = Math.fma(_t13, _t86, Math.fma(_t14, _t87, -(_t12 * _t88)));
        float _t135 = Math.fma(_t5, _t86, Math.fma(_t7, _t87, -(_t3 * _t88)));
        float _t136 = Math.abs(_t132);
        float _t137 = Math.abs(_t133);
        float _t138 = Math.abs(_t134);
        float _t139 = Math.abs(_t135);
        float _t148_inv = 1.0f / (_t120 > _t136 * 9.536743E-7f ? _t116 : Math.copySign(0.0f, _t132));
        float _t149_inv = 1.0f / (_t121 > _t137 * 9.536743E-7f ? _t117 : Math.copySign(0.0f, _t133));
        float _t150_inv = 1.0f / (_t122 > _t138 * 9.536743E-7f ? _t118 : Math.copySign(0.0f, _t134));
        float _t151_inv = 1.0f / (_t123 > _t139 * 9.536743E-7f ? _t119 : Math.copySign(0.0f, _t135));
        float _t152_inv = 1.0f / (_t136 > _t120 * 9.536743E-7f ? _t132 : Math.copySign(0.0f, _t116));
        float _t153_inv = 1.0f / (_t137 > _t121 * 9.536743E-7f ? _t133 : Math.copySign(0.0f, _t117));
        float _t154_inv = 1.0f / (_t138 > _t122 * 9.536743E-7f ? _t134 : Math.copySign(0.0f, _t118));
        float _t155_inv = 1.0f / (_t139 > _t123 * 9.536743E-7f ? _t135 : Math.copySign(0.0f, _t119));
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (corner) {
            case NXNYNZ: _idxSw0 = -(Math.fma(_t0, _t68, Math.fma(_t3, _t69, -(_t5 * _t70))) * _t148_inv); _idxSw1 = Math.fma(_t0, _t72, Math.fma(_t7, _t69, -(_t5 * _t78))) * _t148_inv; _idxSw2 = -(Math.fma(_t0, _t71, Math.fma(_t7, _t70, -(_t3 * _t78))) * _t148_inv); break;
            case PXNYNZ: _idxSw0 = -(Math.fma(_t11, _t68, Math.fma(_t12, _t69, -(_t13 * _t70))) * _t149_inv); _idxSw1 = Math.fma(_t11, _t72, Math.fma(_t14, _t69, -(_t13 * _t78))) * _t149_inv; _idxSw2 = -(Math.fma(_t11, _t71, Math.fma(_t14, _t70, -(_t12 * _t78))) * _t149_inv); break;
            case PXPYNZ: _idxSw0 = -(Math.fma(_t11, _t73, Math.fma(_t12, _t74, -(_t13 * _t75))) * _t150_inv); _idxSw1 = Math.fma(_t11, _t77, Math.fma(_t14, _t74, -(_t13 * _t79))) * _t150_inv; _idxSw2 = -(Math.fma(_t11, _t76, Math.fma(_t14, _t75, -(_t12 * _t79))) * _t150_inv); break;
            case NXPYNZ: _idxSw0 = -(Math.fma(_t0, _t73, Math.fma(_t3, _t74, -(_t5 * _t75))) * _t151_inv); _idxSw1 = Math.fma(_t0, _t77, Math.fma(_t7, _t74, -(_t5 * _t79))) * _t151_inv; _idxSw2 = -(Math.fma(_t0, _t76, Math.fma(_t7, _t75, -(_t3 * _t79))) * _t151_inv); break;
            case NXNYPZ: _idxSw0 = -(Math.fma(_t0, _t82, Math.fma(_t3, _t90, -(_t5 * _t91))) * _t152_inv); _idxSw1 = Math.fma(_t0, _t83, Math.fma(_t7, _t90, -(_t5 * _t94))) * _t152_inv; _idxSw2 = -(Math.fma(_t0, _t81, Math.fma(_t7, _t91, -(_t3 * _t94))) * _t152_inv); break;
            case PXNYPZ: _idxSw0 = -(Math.fma(_t11, _t82, Math.fma(_t12, _t90, -(_t13 * _t91))) * _t153_inv); _idxSw1 = Math.fma(_t11, _t83, Math.fma(_t14, _t90, -(_t13 * _t94))) * _t153_inv; _idxSw2 = -(Math.fma(_t11, _t81, Math.fma(_t14, _t91, -(_t12 * _t94))) * _t153_inv); break;
            case PXPYPZ: _idxSw0 = -(Math.fma(_t11, _t87, Math.fma(_t12, _t92, -(_t13 * _t93))) * _t154_inv); _idxSw1 = Math.fma(_t11, _t88, Math.fma(_t14, _t92, -(_t13 * _t95))) * _t154_inv; _idxSw2 = -(Math.fma(_t11, _t86, Math.fma(_t14, _t93, -(_t12 * _t95))) * _t154_inv); break;
            case NXPYPZ: _idxSw0 = -(Math.fma(_t0, _t87, Math.fma(_t3, _t92, -(_t5 * _t93))) * _t155_inv); _idxSw1 = Math.fma(_t0, _t88, Math.fma(_t7, _t92, -(_t5 * _t95))) * _t155_inv; _idxSw2 = -(Math.fma(_t0, _t86, Math.fma(_t7, _t93, -(_t3 * _t95))) * _t155_inv); break;
            default: throw new IllegalArgumentException("Unknown FrustumCorner: " + corner);
        }
        dest[destOffset + 0] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        return dest;
    }

    public static float[] frustumPlane_no(float[] dest, int destOffset, float[] src, int srcOffset, FrustumPlane plane) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        float _idxSw3;
        switch (plane) {
            case NX: _idxSw0 = _self00 + _self30; _idxSw1 = _self01 + _self31; _idxSw2 = _self02 + _self32; _idxSw3 = _self03 + _self33; break;
            case PX: _idxSw0 = _self30 - _self00; _idxSw1 = _self31 - _self01; _idxSw2 = _self32 - _self02; _idxSw3 = _self33 - _self03; break;
            case NY: _idxSw0 = _self10 + _self30; _idxSw1 = _self11 + _self31; _idxSw2 = _self12 + _self32; _idxSw3 = _self13 + _self33; break;
            case PY: _idxSw0 = _self30 - _self10; _idxSw1 = _self31 - _self11; _idxSw2 = _self32 - _self12; _idxSw3 = _self33 - _self13; break;
            case NZ: _idxSw0 = _self20 + _self30; _idxSw1 = _self21 + _self31; _idxSw2 = _self22 + _self32; _idxSw3 = _self23 + _self33; break;
            case PZ: _idxSw0 = _self30 - _self20; _idxSw1 = _self31 - _self21; _idxSw2 = _self32 - _self22; _idxSw3 = _self33 - _self23; break;
            default: throw new IllegalArgumentException("Unknown FrustumPlane: " + plane);
        }
        dest[destOffset + 0] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        dest[destOffset + 3] = _idxSw3;
        return dest;
    }

    public static float[] frustumPlane_zo(float[] dest, int destOffset, float[] src, int srcOffset, FrustumPlane plane) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        float _idxSw3;
        switch (plane) {
            case NX: _idxSw0 = _self00 + _self30; _idxSw1 = _self01 + _self31; _idxSw2 = _self02 + _self32; _idxSw3 = _self03 + _self33; break;
            case PX: _idxSw0 = _self30 - _self00; _idxSw1 = _self31 - _self01; _idxSw2 = _self32 - _self02; _idxSw3 = _self33 - _self03; break;
            case NY: _idxSw0 = _self10 + _self30; _idxSw1 = _self11 + _self31; _idxSw2 = _self12 + _self32; _idxSw3 = _self13 + _self33; break;
            case PY: _idxSw0 = _self30 - _self10; _idxSw1 = _self31 - _self11; _idxSw2 = _self32 - _self12; _idxSw3 = _self33 - _self13; break;
            case NZ: _idxSw0 = _self20; _idxSw1 = _self21; _idxSw2 = _self22; _idxSw3 = _self23; break;
            case PZ: _idxSw0 = _self30 - _self20; _idxSw1 = _self31 - _self21; _idxSw2 = _self32 - _self22; _idxSw3 = _self33 - _self23; break;
            default: throw new IllegalArgumentException("Unknown FrustumPlane: " + plane);
        }
        dest[destOffset + 0] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        dest[destOffset + 3] = _idxSw3;
        return dest;
    }

    public static float[] frustumRayDir_no(float[] dest, int destOffset, float[] src, int srcOffset, float x, float y) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = -_self32;
        float _t1 = -_self30;
        float _t2 = -_self31;
        float _t3 = -_self33;
        float _t4 = Math.fma(2.0f, x, -1.0f);
        float _t5 = _self21 + _self31;
        float _t6 = Math.fma(2.0f, y, -1.0f);
        float _t7 = _self20 + _self30;
        float _t8 = _self22 + _self32;
        float _t9 = _self21 - _self31;
        float _t10 = _self20 - _self30;
        float _t11 = _self22 - _self32;
        float _t12 = _self23 + _self33;
        float _t13 = _self23 - _self33;
        float _t14 = Math.fma(_t0, _t4, _self02);
        float _t15 = Math.fma(_t1, _t6, _self10);
        float _t16 = Math.fma(_t2, _t6, _self11);
        float _t17 = Math.fma(_t1, _t4, _self00);
        float _t18 = Math.fma(_t0, _t6, _self12);
        float _t19 = Math.fma(_t2, _t4, _self01);
        float _t20 = Math.fma(_t3, _t6, _self13);
        float _t21 = Math.fma(_t3, _t4, _self03);
        float _t22 = -_t19;
        float _t23 = -_t21;
        float _t24 = -_t14;
        float _t25 = -_t17;
        float _t50 = Math.fma(_t5, _t15, -(_t7 * _t16));
        float _t51 = Math.fma(_t8, _t16, -(_t5 * _t18));
        float _t52 = Math.fma(_t8, _t15, -(_t7 * _t18));
        float _t53 = Math.fma(_t15, _t9, -(_t16 * _t10));
        float _t54 = Math.fma(_t16, _t11, -(_t18 * _t9));
        float _t55 = Math.fma(_t15, _t11, -(_t18 * _t10));
        float _t56 = Math.fma(_t12, _t18, -(_t8 * _t20));
        float _t57 = Math.fma(_t12, _t16, -(_t5 * _t20));
        float _t58 = Math.fma(_t18, _t13, -(_t20 * _t11));
        float _t59 = Math.fma(_t16, _t13, -(_t20 * _t9));
        float _t60 = Math.fma(_t12, _t15, -(_t7 * _t20));
        float _t61 = Math.fma(_t15, _t13, -(_t20 * _t10));
        float _t68 = Math.fma(_t14, _t50, Math.fma(_t17, _t51, -(_t19 * _t52)));
        float _t69 = Math.fma(_t14, _t53, Math.fma(_t17, _t54, -(_t19 * _t55)));
        float _sp0 = _t69 / _t68;
        float _t69_inv = 1.0f / _t69;
        float _t70 = Math.abs(_t68);
        float _t71 = Math.abs(_t69);
        float _t74_inv = 1.0f / (_t71 <= _t70 * 9.536743E-7f ? _t68 : _t69);
        if (_t70 <= _t71 * 9.536743E-7f) {
            dest[destOffset + 0] = Math.fma(_t22, _t56, Math.fma(_t14, _t57, Math.fma(_t23, _t51, _t68 * Math.fma(_t21, _t54, Math.fma(_t19, _t58, -(_t14 * _t59))) / _t69))) * _t69_inv;
            dest[destOffset + 1] = Math.fma(_t17, _t56, Math.fma(_t24, _t60, Math.fma(_t21, _t52, -(_t68 * Math.fma(_t21, _t55, Math.fma(_t17, _t58, -(_t14 * _t61))) / _t69)))) * _t69_inv;
            dest[destOffset + 2] = Math.fma(_t25, _t57, Math.fma(_t19, _t60, Math.fma(_t23, _t50, _t68 * Math.fma(_t21, _t53, Math.fma(_t17, _t59, -(_t19 * _t61))) / _t69))) * _t69_inv;
        } else {
            dest[destOffset + 0] = Math.fma(_t22, _t58, Math.fma(_t14, _t59, Math.fma(_t23, _t54, _sp0 * Math.fma(_t21, _t51, Math.fma(_t19, _t56, -(_t14 * _t57)))))) * _t74_inv;
            dest[destOffset + 1] = Math.fma(_t17, _t58, Math.fma(_t24, _t61, Math.fma(_t21, _t55, -(Math.fma(_t21, _t52, Math.fma(_t17, _t56, -(_t14 * _t60))) * _sp0)))) * _t74_inv;
            dest[destOffset + 2] = Math.fma(_t25, _t59, Math.fma(_t19, _t61, Math.fma(_t23, _t53, Math.fma(_t21, _t50, Math.fma(_t17, _t57, -(_t19 * _t60))) * _sp0))) * _t74_inv;
        }
        return dest;
    }

    public static float[] frustumRayDir_zo(float[] dest, int destOffset, float[] src, int srcOffset, float x, float y) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = -_self32;
        float _t1 = -_self30;
        float _t2 = -_self31;
        float _t3 = -_self33;
        float _t4 = Math.fma(2.0f, x, -1.0f);
        float _t5 = Math.fma(2.0f, y, -1.0f);
        float _t6 = _self21 - _self31;
        float _t7 = _self20 - _self30;
        float _t8 = _self22 - _self32;
        float _t9 = _self23 - _self33;
        float _t10 = Math.fma(_t0, _t4, _self02);
        float _t11 = Math.fma(_t1, _t5, _self10);
        float _t12 = Math.fma(_t2, _t5, _self11);
        float _t13 = Math.fma(_t1, _t4, _self00);
        float _t14 = Math.fma(_t0, _t5, _self12);
        float _t15 = Math.fma(_t2, _t4, _self01);
        float _t16 = Math.fma(_t3, _t5, _self13);
        float _t17 = Math.fma(_t3, _t4, _self03);
        float _t18 = -_t15;
        float _t19 = -_t17;
        float _t20 = -_t10;
        float _t21 = -_t13;
        float _t46 = Math.fma(_self21, _t11, -(_self20 * _t12));
        float _t47 = Math.fma(_self22, _t12, -(_self21 * _t14));
        float _t48 = Math.fma(_self22, _t11, -(_self20 * _t14));
        float _t49 = Math.fma(_self23, _t14, -(_self22 * _t16));
        float _t50 = Math.fma(_self23, _t12, -(_self21 * _t16));
        float _t51 = Math.fma(_self23, _t11, -(_self20 * _t16));
        float _t52 = Math.fma(_t11, _t6, -(_t12 * _t7));
        float _t53 = Math.fma(_t12, _t8, -(_t14 * _t6));
        float _t54 = Math.fma(_t11, _t8, -(_t14 * _t7));
        float _t55 = Math.fma(_t14, _t9, -(_t16 * _t8));
        float _t56 = Math.fma(_t12, _t9, -(_t16 * _t6));
        float _t57 = Math.fma(_t11, _t9, -(_t16 * _t7));
        float _t64 = Math.fma(_t10, _t46, Math.fma(_t13, _t47, -(_t15 * _t48)));
        float _t65 = Math.abs(_t64);
        float _t67 = Math.fma(_t10, _t52, Math.fma(_t13, _t53, -(_t15 * _t54)));
        float _sp0 = _t67 / _t64;
        float _t67_inv = 1.0f / _t67;
        float _t68 = Math.abs(_t67);
        float _t70_inv = 1.0f / (_t68 <= _t65 * 9.536743E-7f ? _t64 : _t67);
        if (_t65 <= _t68 * 9.536743E-7f) {
            dest[destOffset + 0] = Math.fma(_t18, _t49, Math.fma(_t10, _t50, Math.fma(_t19, _t47, _t64 * Math.fma(_t17, _t53, Math.fma(_t15, _t55, -(_t10 * _t56))) / _t67))) * _t67_inv;
            dest[destOffset + 1] = Math.fma(_t13, _t49, Math.fma(_t20, _t51, Math.fma(_t17, _t48, -(_t64 * Math.fma(_t17, _t54, Math.fma(_t13, _t55, -(_t10 * _t57))) / _t67)))) * _t67_inv;
            dest[destOffset + 2] = Math.fma(_t21, _t50, Math.fma(_t15, _t51, Math.fma(_t19, _t46, _t64 * Math.fma(_t17, _t52, Math.fma(_t13, _t56, -(_t15 * _t57))) / _t67))) * _t67_inv;
        } else {
            dest[destOffset + 0] = Math.fma(_t18, _t55, Math.fma(_t10, _t56, Math.fma(_t19, _t53, _sp0 * Math.fma(_t17, _t47, Math.fma(_t15, _t49, -(_t10 * _t50)))))) * _t70_inv;
            dest[destOffset + 1] = Math.fma(_t13, _t55, Math.fma(_t20, _t57, Math.fma(_t17, _t54, -(Math.fma(_t17, _t48, Math.fma(_t13, _t49, -(_t10 * _t51))) * _sp0)))) * _t70_inv;
            dest[destOffset + 2] = Math.fma(_t21, _t56, Math.fma(_t15, _t57, Math.fma(_t19, _t52, Math.fma(_t17, _t46, Math.fma(_t13, _t50, -(_t15 * _t51))) * _sp0))) * _t70_inv;
        }
        return dest;
    }

    public static boolean testAabb_no(float[] src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = _self00 + _self30;
        float _t1 = _self01 + _self31;
        float _t2 = _self02 + _self32;
        float _t3 = _self30 - _self00;
        float _t4 = _self31 - _self01;
        float _t5 = _self32 - _self02;
        float _t6 = _self10 + _self30;
        float _t7 = _self11 + _self31;
        float _t8 = _self12 + _self32;
        float _t9 = _self30 - _self10;
        float _t10 = _self31 - _self11;
        float _t11 = _self32 - _self12;
        float _t12 = _self20 + _self30;
        float _t13 = _self21 + _self31;
        float _t14 = _self22 + _self32;
        float _t15 = _self30 - _self20;
        float _t16 = _self31 - _self21;
        float _t17 = _self32 - _self22;
        return !(Math.fma(_t0, _t0 >= 0.0f ? maxX : minX, Math.fma(_t1, _t1 >= 0.0f ? maxY : minY, Math.fma(_t2, _t2 >= 0.0f ? maxZ : minZ, _self03 + _self33))) < 0.0f) && (!(Math.fma(_t3, _t3 >= 0.0f ? maxX : minX, Math.fma(_t4, _t4 >= 0.0f ? maxY : minY, Math.fma(_t5, _t5 >= 0.0f ? maxZ : minZ, _self33 - _self03))) < 0.0f) && (!(Math.fma(_t6, _t6 >= 0.0f ? maxX : minX, Math.fma(_t7, _t7 >= 0.0f ? maxY : minY, Math.fma(_t8, _t8 >= 0.0f ? maxZ : minZ, _self13 + _self33))) < 0.0f) && (!(Math.fma(_t9, _t9 >= 0.0f ? maxX : minX, Math.fma(_t10, _t10 >= 0.0f ? maxY : minY, Math.fma(_t11, _t11 >= 0.0f ? maxZ : minZ, _self33 - _self13))) < 0.0f) && (!(Math.fma(_t12, _t12 >= 0.0f ? maxX : minX, Math.fma(_t13, _t13 >= 0.0f ? maxY : minY, Math.fma(_t14, _t14 >= 0.0f ? maxZ : minZ, _self23 + _self33))) < 0.0f) && (!(Math.fma(_t15, _t15 >= 0.0f ? maxX : minX, Math.fma(_t16, _t16 >= 0.0f ? maxY : minY, Math.fma(_t17, _t17 >= 0.0f ? maxZ : minZ, _self33 - _self23))) < 0.0f) && ((minX <= maxX) && ((minY <= maxY) && (minZ <= maxZ))))))));
    }

    public static boolean testAabb_zo(float[] src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = _self00 + _self30;
        float _t1 = _self01 + _self31;
        float _t2 = _self02 + _self32;
        float _t3 = _self30 - _self00;
        float _t4 = _self31 - _self01;
        float _t5 = _self32 - _self02;
        float _t6 = _self10 + _self30;
        float _t7 = _self11 + _self31;
        float _t8 = _self12 + _self32;
        float _t9 = _self30 - _self10;
        float _t10 = _self31 - _self11;
        float _t11 = _self32 - _self12;
        float _t12 = _self30 - _self20;
        float _t13 = _self31 - _self21;
        float _t14 = _self32 - _self22;
        return !(Math.fma(_t0, _t0 >= 0.0f ? maxX : minX, Math.fma(_t1, _t1 >= 0.0f ? maxY : minY, Math.fma(_t2, _t2 >= 0.0f ? maxZ : minZ, _self03 + _self33))) < 0.0f) && (!(Math.fma(_t3, _t3 >= 0.0f ? maxX : minX, Math.fma(_t4, _t4 >= 0.0f ? maxY : minY, Math.fma(_t5, _t5 >= 0.0f ? maxZ : minZ, _self33 - _self03))) < 0.0f) && (!(Math.fma(_t6, _t6 >= 0.0f ? maxX : minX, Math.fma(_t7, _t7 >= 0.0f ? maxY : minY, Math.fma(_t8, _t8 >= 0.0f ? maxZ : minZ, _self13 + _self33))) < 0.0f) && (!(Math.fma(_t9, _t9 >= 0.0f ? maxX : minX, Math.fma(_t10, _t10 >= 0.0f ? maxY : minY, Math.fma(_t11, _t11 >= 0.0f ? maxZ : minZ, _self33 - _self13))) < 0.0f) && (!(Math.fma(_self20, _self20 >= 0.0f ? maxX : minX, Math.fma(_self21, _self21 >= 0.0f ? maxY : minY, Math.fma(_self22, _self22 >= 0.0f ? maxZ : minZ, _self23))) < 0.0f) && (!(Math.fma(_t12, _t12 >= 0.0f ? maxX : minX, Math.fma(_t13, _t13 >= 0.0f ? maxY : minY, Math.fma(_t14, _t14 >= 0.0f ? maxZ : minZ, _self33 - _self23))) < 0.0f) && ((minX <= maxX) && ((minY <= maxY) && (minZ <= maxZ))))))));
    }

    public static boolean testAabb_no(float[] src, int srcOffset, float[] min, int minOffset, float[] max, int maxOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _minx = min[minOffset + 0];
        float _miny = min[minOffset + 1];
        float _minz = min[minOffset + 2];
        float _maxx = max[maxOffset + 0];
        float _maxy = max[maxOffset + 1];
        float _maxz = max[maxOffset + 2];
        float _t0 = _self00 + _self30;
        float _t1 = _self01 + _self31;
        float _t2 = _self02 + _self32;
        float _t3 = _self30 - _self00;
        float _t4 = _self31 - _self01;
        float _t5 = _self32 - _self02;
        float _t6 = _self10 + _self30;
        float _t7 = _self11 + _self31;
        float _t8 = _self12 + _self32;
        float _t9 = _self30 - _self10;
        float _t10 = _self31 - _self11;
        float _t11 = _self32 - _self12;
        float _t12 = _self20 + _self30;
        float _t13 = _self21 + _self31;
        float _t14 = _self22 + _self32;
        float _t15 = _self30 - _self20;
        float _t16 = _self31 - _self21;
        float _t17 = _self32 - _self22;
        return !(Math.fma(_t0, _t0 >= 0.0f ? _maxx : _minx, Math.fma(_t1, _t1 >= 0.0f ? _maxy : _miny, Math.fma(_t2, _t2 >= 0.0f ? _maxz : _minz, _self03 + _self33))) < 0.0f) && (!(Math.fma(_t3, _t3 >= 0.0f ? _maxx : _minx, Math.fma(_t4, _t4 >= 0.0f ? _maxy : _miny, Math.fma(_t5, _t5 >= 0.0f ? _maxz : _minz, _self33 - _self03))) < 0.0f) && (!(Math.fma(_t6, _t6 >= 0.0f ? _maxx : _minx, Math.fma(_t7, _t7 >= 0.0f ? _maxy : _miny, Math.fma(_t8, _t8 >= 0.0f ? _maxz : _minz, _self13 + _self33))) < 0.0f) && (!(Math.fma(_t9, _t9 >= 0.0f ? _maxx : _minx, Math.fma(_t10, _t10 >= 0.0f ? _maxy : _miny, Math.fma(_t11, _t11 >= 0.0f ? _maxz : _minz, _self33 - _self13))) < 0.0f) && (!(Math.fma(_t12, _t12 >= 0.0f ? _maxx : _minx, Math.fma(_t13, _t13 >= 0.0f ? _maxy : _miny, Math.fma(_t14, _t14 >= 0.0f ? _maxz : _minz, _self23 + _self33))) < 0.0f) && (!(Math.fma(_t15, _t15 >= 0.0f ? _maxx : _minx, Math.fma(_t16, _t16 >= 0.0f ? _maxy : _miny, Math.fma(_t17, _t17 >= 0.0f ? _maxz : _minz, _self33 - _self23))) < 0.0f) && ((_minx <= _maxx) && ((_miny <= _maxy) && (_minz <= _maxz))))))));
    }

    public static boolean testAabb_zo(float[] src, int srcOffset, float[] min, int minOffset, float[] max, int maxOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _minx = min[minOffset + 0];
        float _miny = min[minOffset + 1];
        float _minz = min[minOffset + 2];
        float _maxx = max[maxOffset + 0];
        float _maxy = max[maxOffset + 1];
        float _maxz = max[maxOffset + 2];
        float _t0 = _self00 + _self30;
        float _t1 = _self01 + _self31;
        float _t2 = _self02 + _self32;
        float _t3 = _self30 - _self00;
        float _t4 = _self31 - _self01;
        float _t5 = _self32 - _self02;
        float _t6 = _self10 + _self30;
        float _t7 = _self11 + _self31;
        float _t8 = _self12 + _self32;
        float _t9 = _self30 - _self10;
        float _t10 = _self31 - _self11;
        float _t11 = _self32 - _self12;
        float _t12 = _self30 - _self20;
        float _t13 = _self31 - _self21;
        float _t14 = _self32 - _self22;
        return !(Math.fma(_t0, _t0 >= 0.0f ? _maxx : _minx, Math.fma(_t1, _t1 >= 0.0f ? _maxy : _miny, Math.fma(_t2, _t2 >= 0.0f ? _maxz : _minz, _self03 + _self33))) < 0.0f) && (!(Math.fma(_t3, _t3 >= 0.0f ? _maxx : _minx, Math.fma(_t4, _t4 >= 0.0f ? _maxy : _miny, Math.fma(_t5, _t5 >= 0.0f ? _maxz : _minz, _self33 - _self03))) < 0.0f) && (!(Math.fma(_t6, _t6 >= 0.0f ? _maxx : _minx, Math.fma(_t7, _t7 >= 0.0f ? _maxy : _miny, Math.fma(_t8, _t8 >= 0.0f ? _maxz : _minz, _self13 + _self33))) < 0.0f) && (!(Math.fma(_t9, _t9 >= 0.0f ? _maxx : _minx, Math.fma(_t10, _t10 >= 0.0f ? _maxy : _miny, Math.fma(_t11, _t11 >= 0.0f ? _maxz : _minz, _self33 - _self13))) < 0.0f) && (!(Math.fma(_self20, _self20 >= 0.0f ? _maxx : _minx, Math.fma(_self21, _self21 >= 0.0f ? _maxy : _miny, Math.fma(_self22, _self22 >= 0.0f ? _maxz : _minz, _self23))) < 0.0f) && (!(Math.fma(_t12, _t12 >= 0.0f ? _maxx : _minx, Math.fma(_t13, _t13 >= 0.0f ? _maxy : _miny, Math.fma(_t14, _t14 >= 0.0f ? _maxz : _minz, _self33 - _self23))) < 0.0f) && ((_minx <= _maxx) && ((_miny <= _maxy) && (_minz <= _maxz))))))));
    }

    public static boolean testPoint_no(float[] src, int srcOffset, float pointX, float pointY, float pointZ) {
        return testPoint_no_s5e6a1646_1(pointX, pointY, pointZ, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15]);
    }

    /** Piece 2 of {@code testPoint_no}, split to fit the inline budget; reached only through it. */
    private static boolean testPoint_no_s5e6a1646_1(float pointX, float pointY, float pointZ, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33) {
        return (Math.fma(pointX, _self00 + _self30, Math.fma(pointY, _self01 + _self31, Math.fma(pointZ, _self02 + _self32, _self03 + _self33))) >= 0.0f) && ((Math.fma(pointX, _self30 - _self00, Math.fma(pointY, _self31 - _self01, Math.fma(pointZ, _self32 - _self02, _self33 - _self03))) >= 0.0f) && ((Math.fma(pointX, _self10 + _self30, Math.fma(pointY, _self11 + _self31, Math.fma(pointZ, _self12 + _self32, _self13 + _self33))) >= 0.0f) && ((Math.fma(pointX, _self30 - _self10, Math.fma(pointY, _self31 - _self11, Math.fma(pointZ, _self32 - _self12, _self33 - _self13))) >= 0.0f) && ((Math.fma(pointX, _self20 + _self30, Math.fma(pointY, _self21 + _self31, Math.fma(pointZ, _self22 + _self32, _self23 + _self33))) >= 0.0f) && (Math.fma(pointX, _self30 - _self20, Math.fma(pointY, _self31 - _self21, Math.fma(pointZ, _self32 - _self22, _self33 - _self23))) >= 0.0f)))));
    }

    public static boolean testPoint_zo(float[] src, int srcOffset, float pointX, float pointY, float pointZ) {
        return testPoint_zo_s6f87eb5a_1(pointX, pointY, pointZ, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15]);
    }

    /** Piece 2 of {@code testPoint_zo}, split to fit the inline budget; reached only through it. */
    private static boolean testPoint_zo_s6f87eb5a_1(float pointX, float pointY, float pointZ, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33) {
        return (Math.fma(pointX, _self00 + _self30, Math.fma(pointY, _self01 + _self31, Math.fma(pointZ, _self02 + _self32, _self03 + _self33))) >= 0.0f) && ((Math.fma(pointX, _self30 - _self00, Math.fma(pointY, _self31 - _self01, Math.fma(pointZ, _self32 - _self02, _self33 - _self03))) >= 0.0f) && ((Math.fma(pointX, _self10 + _self30, Math.fma(pointY, _self11 + _self31, Math.fma(pointZ, _self12 + _self32, _self13 + _self33))) >= 0.0f) && ((Math.fma(pointX, _self30 - _self10, Math.fma(pointY, _self31 - _self11, Math.fma(pointZ, _self32 - _self12, _self33 - _self13))) >= 0.0f) && ((Math.fma(pointX, _self20, Math.fma(pointY, _self21, Math.fma(pointZ, _self22, _self23))) >= 0.0f) && (Math.fma(pointX, _self30 - _self20, Math.fma(pointY, _self31 - _self21, Math.fma(pointZ, _self32 - _self22, _self33 - _self23))) >= 0.0f)))));
    }

    public static boolean testPoint_no(float[] src, int srcOffset, float[] point, int pointOffset) {
        return testPoint_no_se8a172b_1(src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], point[pointOffset + 0], point[pointOffset + 1], point[pointOffset + 2]);
    }

    /** Piece 2 of {@code testPoint_no}, split to fit the inline budget; reached only through it. */
    private static boolean testPoint_no_se8a172b_1(float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _pointx, float _pointy, float _pointz) {
        return (Math.fma(_pointx, _self00 + _self30, Math.fma(_pointy, _self01 + _self31, Math.fma(_pointz, _self02 + _self32, _self03 + _self33))) >= 0.0f) && ((Math.fma(_pointx, _self30 - _self00, Math.fma(_pointy, _self31 - _self01, Math.fma(_pointz, _self32 - _self02, _self33 - _self03))) >= 0.0f) && ((Math.fma(_pointx, _self10 + _self30, Math.fma(_pointy, _self11 + _self31, Math.fma(_pointz, _self12 + _self32, _self13 + _self33))) >= 0.0f) && ((Math.fma(_pointx, _self30 - _self10, Math.fma(_pointy, _self31 - _self11, Math.fma(_pointz, _self32 - _self12, _self33 - _self13))) >= 0.0f) && ((Math.fma(_pointx, _self20 + _self30, Math.fma(_pointy, _self21 + _self31, Math.fma(_pointz, _self22 + _self32, _self23 + _self33))) >= 0.0f) && (Math.fma(_pointx, _self30 - _self20, Math.fma(_pointy, _self31 - _self21, Math.fma(_pointz, _self32 - _self22, _self33 - _self23))) >= 0.0f)))));
    }

    public static boolean testPoint_zo(float[] src, int srcOffset, float[] point, int pointOffset) {
        return testPoint_zo_sc561a5f_1(src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], point[pointOffset + 0], point[pointOffset + 1], point[pointOffset + 2]);
    }

    /** Piece 2 of {@code testPoint_zo}, split to fit the inline budget; reached only through it. */
    private static boolean testPoint_zo_sc561a5f_1(float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _pointx, float _pointy, float _pointz) {
        return (Math.fma(_pointx, _self00 + _self30, Math.fma(_pointy, _self01 + _self31, Math.fma(_pointz, _self02 + _self32, _self03 + _self33))) >= 0.0f) && ((Math.fma(_pointx, _self30 - _self00, Math.fma(_pointy, _self31 - _self01, Math.fma(_pointz, _self32 - _self02, _self33 - _self03))) >= 0.0f) && ((Math.fma(_pointx, _self10 + _self30, Math.fma(_pointy, _self11 + _self31, Math.fma(_pointz, _self12 + _self32, _self13 + _self33))) >= 0.0f) && ((Math.fma(_pointx, _self30 - _self10, Math.fma(_pointy, _self31 - _self11, Math.fma(_pointz, _self32 - _self12, _self33 - _self13))) >= 0.0f) && ((Math.fma(_pointx, _self20, Math.fma(_pointy, _self21, Math.fma(_pointz, _self22, _self23))) >= 0.0f) && (Math.fma(_pointx, _self30 - _self20, Math.fma(_pointy, _self31 - _self21, Math.fma(_pointz, _self32 - _self22, _self33 - _self23))) >= 0.0f)))));
    }

    public static boolean testSphere_no(float[] src, int srcOffset, float centerX, float centerY, float centerZ, float radius) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self33 = src[srcOffset + 15];
        float _t0 = radius * radius;
        float _t1 = _self00 + _self30;
        float _t2 = _self01 + _self31;
        float _t3 = _self02 + _self32;
        return testSphere_no_s98989b6a_1(centerX, centerY, centerZ, _self03, src[srcOffset + 13], src[srcOffset + 14], _self33, _t0, _t1, _t2, _t3, _self30 - _self00, _self31 - _self01, _self32 - _self02, _self10 + _self30, _self11 + _self31, _self12 + _self32, _self30 - _self10, _self31 - _self11, _self32 - _self12, _self20 + _self30, _self21 + _self31, _self22 + _self32, _self30 - _self20, _self31 - _self21, _self32 - _self22, _t0 >= 0.0f ? 1.0f : 0.0f, Math.fma(centerX, _t1, Math.fma(centerY, _t2, Math.fma(centerZ, _t3, _self03 + _self33))));
    }

    /** Piece 2 of {@code testSphere_no}, split to fit the inline budget; reached only through it. */
    private static boolean testSphere_no_s98989b6a_1(float centerX, float centerY, float centerZ, float _self03, float _self13, float _self23, float _self33, float _t0, float _t1, float _t2, float _t3, float _t5, float _t6, float _t7, float _t9, float _t10, float _t11, float _t13, float _t14, float _t15, float _t17, float _t18, float _t19, float _t21, float _t22, float _t23, float _t25, float _t48) {
        float _t51 = Math.fma(centerX, _t13, Math.fma(centerY, _t14, Math.fma(centerZ, _t15, _self33 - _self13)));
        float _t52 = Math.fma(centerX, _t17, Math.fma(centerY, _t18, Math.fma(centerZ, _t19, _self23 + _self33)));
        float _t53 = Math.fma(centerX, _t21, Math.fma(centerY, _t22, Math.fma(centerZ, _t23, _self33 - _self23)));
        float _t70 = _t53 >= 0.0f ? _t25 : _t53 * _t53 <= Math.fma(_t23, _t23, Math.fma(_t21, _t21, _t22 * _t22)) * _t0 ? _t25 : 0.0f;
        float _t72 = _t52 >= 0.0f ? _t70 : _t52 * _t52 <= Math.fma(_t19, _t19, Math.fma(_t17, _t17, _t18 * _t18)) * _t0 ? _t70 : 0.0f;
        return testSphere_no_s98989b6a_2(_t0, _t1, _t2, _t3, _t5, _t6, _t7, _t9, _t10, _t11, _t48, Math.fma(centerX, _t5, Math.fma(centerY, _t6, Math.fma(centerZ, _t7, _self33 - _self03))), Math.fma(centerX, _t9, Math.fma(centerY, _t10, Math.fma(centerZ, _t11, _self13 + _self33))), _t51 >= 0.0f ? _t72 : _t51 * _t51 <= Math.fma(_t15, _t15, Math.fma(_t13, _t13, _t14 * _t14)) * _t0 ? _t72 : 0.0f);
    }

    /** Piece 3 of {@code testSphere_no}, split to fit the inline budget; reached only through it. */
    private static boolean testSphere_no_s98989b6a_2(float _t0, float _t1, float _t2, float _t3, float _t5, float _t6, float _t7, float _t9, float _t10, float _t11, float _t48, float _t49, float _t50, float _t74) {
        float _t76 = _t50 >= 0.0f ? _t74 : _t50 * _t50 <= Math.fma(_t11, _t11, Math.fma(_t9, _t9, _t10 * _t10)) * _t0 ? _t74 : 0.0f;
        float _t78 = _t49 >= 0.0f ? _t76 : _t49 * _t49 <= Math.fma(_t7, _t7, Math.fma(_t5, _t5, _t6 * _t6)) * _t0 ? _t76 : 0.0f;
        return (_t48 >= 0.0f ? _t78 : _t48 * _t48 <= Math.fma(_t3, _t3, Math.fma(_t1, _t1, _t2 * _t2)) * _t0 ? _t78 : 0.0f) != 0;
    }

    public static boolean testSphere_zo(float[] src, int srcOffset, float centerX, float centerY, float centerZ, float radius) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = radius * radius;
        float _t1 = _self00 + _self30;
        float _t2 = _self01 + _self31;
        float _t3 = _self02 + _self32;
        return testSphere_zo_s35cd2dce_1(centerX, centerY, centerZ, _self20, _self21, _self22, _self03, src[srcOffset + 13], _self23, _self33, _t0, _t1, _t2, _t3, _self30 - _self00, _self31 - _self01, _self32 - _self02, _self10 + _self30, _self11 + _self31, _self12 + _self32, _self30 - _self10, _self31 - _self11, _self32 - _self12, _self30 - _self20, _self31 - _self21, _self32 - _self22, _t0 >= 0.0f ? 1.0f : 0.0f, Math.fma(centerX, _self20, Math.fma(centerY, _self21, Math.fma(centerZ, _self22, _self23))), Math.fma(centerX, _t1, Math.fma(centerY, _t2, Math.fma(centerZ, _t3, _self03 + _self33))));
    }

    /** Piece 2 of {@code testSphere_zo}, split to fit the inline budget; reached only through it. */
    private static boolean testSphere_zo_s35cd2dce_1(float centerX, float centerY, float centerZ, float _self20, float _self21, float _self22, float _self03, float _self13, float _self23, float _self33, float _t0, float _t1, float _t2, float _t3, float _t5, float _t6, float _t7, float _t9, float _t10, float _t11, float _t13, float _t14, float _t15, float _t17, float _t18, float _t19, float _t23, float _t36, float _t47) {
        float _t50 = Math.fma(centerX, _t13, Math.fma(centerY, _t14, Math.fma(centerZ, _t15, _self33 - _self13)));
        float _t51 = Math.fma(centerX, _t17, Math.fma(centerY, _t18, Math.fma(centerZ, _t19, _self33 - _self23)));
        float _t66 = _t51 >= 0.0f ? _t23 : _t51 * _t51 <= Math.fma(_t19, _t19, Math.fma(_t17, _t17, _t18 * _t18)) * _t0 ? _t23 : 0.0f;
        float _t68 = _t36 >= 0.0f ? _t66 : _t36 * _t36 <= Math.fma(_self22, _self22, Math.fma(_self20, _self20, _self21 * _self21)) * _t0 ? _t66 : 0.0f;
        return testSphere_zo_s35cd2dce_2(_t0, _t1, _t2, _t3, _t5, _t6, _t7, _t9, _t10, _t11, _t47, Math.fma(centerX, _t5, Math.fma(centerY, _t6, Math.fma(centerZ, _t7, _self33 - _self03))), Math.fma(centerX, _t9, Math.fma(centerY, _t10, Math.fma(centerZ, _t11, _self13 + _self33))), _t50 >= 0.0f ? _t68 : _t50 * _t50 <= Math.fma(_t15, _t15, Math.fma(_t13, _t13, _t14 * _t14)) * _t0 ? _t68 : 0.0f);
    }

    /** Piece 3 of {@code testSphere_zo}, split to fit the inline budget; reached only through it. */
    private static boolean testSphere_zo_s35cd2dce_2(float _t0, float _t1, float _t2, float _t3, float _t5, float _t6, float _t7, float _t9, float _t10, float _t11, float _t47, float _t48, float _t49, float _t70) {
        float _t72 = _t49 >= 0.0f ? _t70 : _t49 * _t49 <= Math.fma(_t11, _t11, Math.fma(_t9, _t9, _t10 * _t10)) * _t0 ? _t70 : 0.0f;
        float _t74 = _t48 >= 0.0f ? _t72 : _t48 * _t48 <= Math.fma(_t7, _t7, Math.fma(_t5, _t5, _t6 * _t6)) * _t0 ? _t72 : 0.0f;
        return (_t47 >= 0.0f ? _t74 : _t47 * _t47 <= Math.fma(_t3, _t3, Math.fma(_t1, _t1, _t2 * _t2)) * _t0 ? _t74 : 0.0f) != 0;
    }

    public static boolean testSphere_no(float[] src, int srcOffset, float[] center, int centerOffset, float radius) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self33 = src[srcOffset + 15];
        float _centerx = center[centerOffset + 0];
        float _centery = center[centerOffset + 1];
        float _centerz = center[centerOffset + 2];
        float _t0 = radius * radius;
        float _t1 = _self00 + _self30;
        float _t2 = _self01 + _self31;
        float _t3 = _self02 + _self32;
        return testSphere_no_sd031f1fc_1(_self03, src[srcOffset + 13], src[srcOffset + 14], _self33, _centerx, _centery, _centerz, _t0, _t1, _t2, _t3, _self30 - _self00, _self31 - _self01, _self32 - _self02, _self10 + _self30, _self11 + _self31, _self12 + _self32, _self30 - _self10, _self31 - _self11, _self32 - _self12, _self20 + _self30, _self21 + _self31, _self22 + _self32, _self30 - _self20, _self31 - _self21, _self32 - _self22, _t0 >= 0.0f ? 1.0f : 0.0f, Math.fma(_centerx, _t1, Math.fma(_centery, _t2, Math.fma(_centerz, _t3, _self03 + _self33))));
    }

    /** Piece 2 of {@code testSphere_no}, split to fit the inline budget; reached only through it. */
    private static boolean testSphere_no_sd031f1fc_1(float _self03, float _self13, float _self23, float _self33, float _centerx, float _centery, float _centerz, float _t0, float _t1, float _t2, float _t3, float _t5, float _t6, float _t7, float _t9, float _t10, float _t11, float _t13, float _t14, float _t15, float _t17, float _t18, float _t19, float _t21, float _t22, float _t23, float _t25, float _t48) {
        float _t51 = Math.fma(_centerx, _t13, Math.fma(_centery, _t14, Math.fma(_centerz, _t15, _self33 - _self13)));
        float _t52 = Math.fma(_centerx, _t17, Math.fma(_centery, _t18, Math.fma(_centerz, _t19, _self23 + _self33)));
        float _t53 = Math.fma(_centerx, _t21, Math.fma(_centery, _t22, Math.fma(_centerz, _t23, _self33 - _self23)));
        float _t70 = _t53 >= 0.0f ? _t25 : _t53 * _t53 <= Math.fma(_t23, _t23, Math.fma(_t21, _t21, _t22 * _t22)) * _t0 ? _t25 : 0.0f;
        float _t72 = _t52 >= 0.0f ? _t70 : _t52 * _t52 <= Math.fma(_t19, _t19, Math.fma(_t17, _t17, _t18 * _t18)) * _t0 ? _t70 : 0.0f;
        return testSphere_no_sd031f1fc_2(_t0, _t1, _t2, _t3, _t5, _t6, _t7, _t9, _t10, _t11, _t48, Math.fma(_centerx, _t5, Math.fma(_centery, _t6, Math.fma(_centerz, _t7, _self33 - _self03))), Math.fma(_centerx, _t9, Math.fma(_centery, _t10, Math.fma(_centerz, _t11, _self13 + _self33))), _t51 >= 0.0f ? _t72 : _t51 * _t51 <= Math.fma(_t15, _t15, Math.fma(_t13, _t13, _t14 * _t14)) * _t0 ? _t72 : 0.0f);
    }

    /** Piece 3 of {@code testSphere_no}, split to fit the inline budget; reached only through it. */
    private static boolean testSphere_no_sd031f1fc_2(float _t0, float _t1, float _t2, float _t3, float _t5, float _t6, float _t7, float _t9, float _t10, float _t11, float _t48, float _t49, float _t50, float _t74) {
        float _t76 = _t50 >= 0.0f ? _t74 : _t50 * _t50 <= Math.fma(_t11, _t11, Math.fma(_t9, _t9, _t10 * _t10)) * _t0 ? _t74 : 0.0f;
        float _t78 = _t49 >= 0.0f ? _t76 : _t49 * _t49 <= Math.fma(_t7, _t7, Math.fma(_t5, _t5, _t6 * _t6)) * _t0 ? _t76 : 0.0f;
        return (_t48 >= 0.0f ? _t78 : _t48 * _t48 <= Math.fma(_t3, _t3, Math.fma(_t1, _t1, _t2 * _t2)) * _t0 ? _t78 : 0.0f) != 0;
    }

    public static boolean testSphere_zo(float[] src, int srcOffset, float[] center, int centerOffset, float radius) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self23 = src[srcOffset + 14];
        float _centerx = center[centerOffset + 0];
        float _centery = center[centerOffset + 1];
        float _centerz = center[centerOffset + 2];
        float _t0 = radius * radius;
        return testSphere_zo_sb4224f10_1(_self20, _self21, _self22, src[srcOffset + 12], src[srcOffset + 13], _self23, src[srcOffset + 15], _centerx, _centery, _centerz, _t0, _self00 + _self30, _self01 + _self31, _self02 + _self32, _self30 - _self00, _self31 - _self01, _self32 - _self02, _self10 + _self30, _self11 + _self31, _self12 + _self32, _self30 - _self10, _self31 - _self11, _self32 - _self12, _self30 - _self20, _self31 - _self21, _self32 - _self22, _t0 >= 0.0f ? 1.0f : 0.0f, Math.fma(_centerx, _self20, Math.fma(_centery, _self21, Math.fma(_centerz, _self22, _self23))));
    }

    /** Piece 2 of {@code testSphere_zo}, split to fit the inline budget; reached only through it. */
    private static boolean testSphere_zo_sb4224f10_1(float _self20, float _self21, float _self22, float _self03, float _self13, float _self23, float _self33, float _centerx, float _centery, float _centerz, float _t0, float _t1, float _t2, float _t3, float _t5, float _t6, float _t7, float _t9, float _t10, float _t11, float _t13, float _t14, float _t15, float _t17, float _t18, float _t19, float _t23, float _t36) {
        float _t50 = Math.fma(_centerx, _t13, Math.fma(_centery, _t14, Math.fma(_centerz, _t15, _self33 - _self13)));
        float _t51 = Math.fma(_centerx, _t17, Math.fma(_centery, _t18, Math.fma(_centerz, _t19, _self33 - _self23)));
        float _t66 = _t51 >= 0.0f ? _t23 : _t51 * _t51 <= Math.fma(_t19, _t19, Math.fma(_t17, _t17, _t18 * _t18)) * _t0 ? _t23 : 0.0f;
        float _t68 = _t36 >= 0.0f ? _t66 : _t36 * _t36 <= Math.fma(_self22, _self22, Math.fma(_self20, _self20, _self21 * _self21)) * _t0 ? _t66 : 0.0f;
        return testSphere_zo_sb4224f10_2(_t0, _t1, _t2, _t3, _t5, _t6, _t7, _t9, _t10, _t11, Math.fma(_centerx, _t1, Math.fma(_centery, _t2, Math.fma(_centerz, _t3, _self03 + _self33))), Math.fma(_centerx, _t5, Math.fma(_centery, _t6, Math.fma(_centerz, _t7, _self33 - _self03))), Math.fma(_centerx, _t9, Math.fma(_centery, _t10, Math.fma(_centerz, _t11, _self13 + _self33))), _t50 >= 0.0f ? _t68 : _t50 * _t50 <= Math.fma(_t15, _t15, Math.fma(_t13, _t13, _t14 * _t14)) * _t0 ? _t68 : 0.0f);
    }

    /** Piece 3 of {@code testSphere_zo}, split to fit the inline budget; reached only through it. */
    private static boolean testSphere_zo_sb4224f10_2(float _t0, float _t1, float _t2, float _t3, float _t5, float _t6, float _t7, float _t9, float _t10, float _t11, float _t47, float _t48, float _t49, float _t70) {
        float _t72 = _t49 >= 0.0f ? _t70 : _t49 * _t49 <= Math.fma(_t11, _t11, Math.fma(_t9, _t9, _t10 * _t10)) * _t0 ? _t70 : 0.0f;
        float _t74 = _t48 >= 0.0f ? _t72 : _t48 * _t48 <= Math.fma(_t7, _t7, Math.fma(_t5, _t5, _t6 * _t6)) * _t0 ? _t72 : 0.0f;
        return (_t47 >= 0.0f ? _t74 : _t47 * _t47 <= Math.fma(_t3, _t3, Math.fma(_t1, _t1, _t2 * _t2)) * _t0 ? _t74 : 0.0f) != 0;
    }

    public static float[] frustum_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = zNear + zNear;
        float _t1_inv = 1.0f / (right - left);
        float _sp0 = _t0 * _t1_inv;
        float _t2_inv = 1.0f / (top - bottom);
        float _t4_inv = 1.0f / (zNear - zFar);
        float _t16, _t17;
        if (zFar == Float.POSITIVE_INFINITY) {
            _t16 = 1.0f;
            _t17 = -_t0;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                _t16 = -1.0f;
                _t17 = zFar + zFar;
            } else {
                _t16 = -((zFar + zNear) * _t4_inv);
                _t17 = _t0 * zFar * _t4_inv;
            }
        }
        dest[destOffset + 0] = _self00 * _sp0;
        return frustum_no_lh_s81a8d300_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _sp0, _t0 * _t2_inv, _t1_inv * (left + right), _t2_inv * (bottom + top), _t16, _t17);
    }

    /** Piece 2 of {@code frustum_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] frustum_no_lh_s81a8d300_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp0, float _sp1, float _sp2, float _sp3, float _t16, float _t17) {
        dest[destOffset + 1] = _self10 * _sp0;
        dest[destOffset + 2] = _self20 * _sp0;
        dest[destOffset + 3] = _self30 * _sp0;
        dest[destOffset + 4] = _self01 * _sp1;
        dest[destOffset + 5] = _self11 * _sp1;
        dest[destOffset + 6] = _self21 * _sp1;
        dest[destOffset + 7] = _self31 * _sp1;
        dest[destOffset + 8] = Math.fma(_self02, _t16, _self03 - _self00 * _sp2 - _self01 * _sp3);
        dest[destOffset + 9] = Math.fma(_self12, _t16, _self13 - _self10 * _sp2 - _self11 * _sp3);
        dest[destOffset + 10] = Math.fma(_self22, _t16, _self23 - _self20 * _sp2 - _self21 * _sp3);
        dest[destOffset + 11] = Math.fma(_self32, _t16, _self33 - _self30 * _sp2 - _self31 * _sp3);
        dest[destOffset + 12] = _self02 * _t17;
        dest[destOffset + 13] = _self12 * _t17;
        dest[destOffset + 14] = _self22 * _t17;
        dest[destOffset + 15] = _self32 * _t17;
        return dest;
    }

    public static float[] frustum_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = zNear + zNear;
        float _t1_inv = 1.0f / (right - left);
        float _sp0 = _t0 * _t1_inv;
        float _t2_inv = 1.0f / (top - bottom);
        float _t4_inv = 1.0f / (zNear - zFar);
        float _t14, _t16;
        if (zFar == Float.POSITIVE_INFINITY) {
            _t14 = -1.0f;
            _t16 = -_t0;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                _t14 = 1.0f;
                _t16 = zFar + zFar;
            } else {
                _t14 = (zFar + zNear) * _t4_inv;
                _t16 = _t0 * zFar * _t4_inv;
            }
        }
        dest[destOffset + 0] = _self00 * _sp0;
        return frustum_no_rh_s2295f9ae_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _sp0, _t0 * _t2_inv, _t1_inv * (left + right), _t2_inv * (bottom + top), _t14, _t16);
    }

    /** Piece 2 of {@code frustum_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] frustum_no_rh_s2295f9ae_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp0, float _sp1, float _sp2, float _sp3, float _t14, float _t16) {
        dest[destOffset + 1] = _self10 * _sp0;
        dest[destOffset + 2] = _self20 * _sp0;
        dest[destOffset + 3] = _self30 * _sp0;
        dest[destOffset + 4] = _self01 * _sp1;
        dest[destOffset + 5] = _self11 * _sp1;
        dest[destOffset + 6] = _self21 * _sp1;
        dest[destOffset + 7] = _self31 * _sp1;
        dest[destOffset + 8] = Math.fma(_self02, _t14, _self00 * _sp2 + _self01 * _sp3 - _self03);
        dest[destOffset + 9] = Math.fma(_self12, _t14, _self10 * _sp2 + _self11 * _sp3 - _self13);
        dest[destOffset + 10] = Math.fma(_self22, _t14, _self20 * _sp2 + _self21 * _sp3 - _self23);
        dest[destOffset + 11] = Math.fma(_self32, _t14, _self30 * _sp2 + _self31 * _sp3 - _self33);
        dest[destOffset + 12] = _self02 * _t16;
        dest[destOffset + 13] = _self12 * _t16;
        dest[destOffset + 14] = _self22 * _t16;
        dest[destOffset + 15] = _self32 * _t16;
        return dest;
    }

    public static float[] frustum_no(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.frustum_no_lh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Float4x4OpsKernelsArray.frustum_no_rh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static float[] frustum_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = zNear + zNear;
        float _t1_inv = 1.0f / (right - left);
        float _sp0 = _t0 * _t1_inv;
        float _t2_inv = 1.0f / (top - bottom);
        float _sp4 = zFar / (zNear - zFar);
        float _t12, _t13;
        if (zFar == Float.POSITIVE_INFINITY) {
            _t12 = 1.0f;
            _t13 = -zNear;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                _t12 = 0.0f;
                _t13 = zFar;
            } else {
                _t12 = -_sp4;
                _t13 = _sp4 * zNear;
            }
        }
        dest[destOffset + 0] = _self00 * _sp0;
        dest[destOffset + 1] = _self10 * _sp0;
        return frustum_zo_lh_se6bcd1fc_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _sp0, _t0 * _t2_inv, _t1_inv * (left + right), _t2_inv * (bottom + top), _t12, _t13);
    }

    /** Piece 2 of {@code frustum_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] frustum_zo_lh_se6bcd1fc_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp0, float _sp1, float _sp2, float _sp3, float _t12, float _t13) {
        dest[destOffset + 2] = _self20 * _sp0;
        dest[destOffset + 3] = _self30 * _sp0;
        dest[destOffset + 4] = _self01 * _sp1;
        dest[destOffset + 5] = _self11 * _sp1;
        dest[destOffset + 6] = _self21 * _sp1;
        dest[destOffset + 7] = _self31 * _sp1;
        dest[destOffset + 8] = Math.fma(_self02, _t12, _self03 - _self00 * _sp2 - _self01 * _sp3);
        dest[destOffset + 9] = Math.fma(_self12, _t12, _self13 - _self10 * _sp2 - _self11 * _sp3);
        dest[destOffset + 10] = Math.fma(_self22, _t12, _self23 - _self20 * _sp2 - _self21 * _sp3);
        dest[destOffset + 11] = Math.fma(_self32, _t12, _self33 - _self30 * _sp2 - _self31 * _sp3);
        dest[destOffset + 12] = _self02 * _t13;
        dest[destOffset + 13] = _self12 * _t13;
        dest[destOffset + 14] = _self22 * _t13;
        dest[destOffset + 15] = _self32 * _t13;
        return dest;
    }

    public static float[] frustum_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = zNear + zNear;
        float _t1_inv = 1.0f / (right - left);
        float _sp0 = _t0 * _t1_inv;
        float _t2_inv = 1.0f / (top - bottom);
        float _sp4 = zFar / (zNear - zFar);
        float _t11, _t12;
        if (zFar == Float.POSITIVE_INFINITY) {
            _t11 = -1.0f;
            _t12 = -zNear;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                _t11 = 0.0f;
                _t12 = zFar;
            } else {
                _t11 = _sp4;
                _t12 = _sp4 * zNear;
            }
        }
        dest[destOffset + 0] = _self00 * _sp0;
        dest[destOffset + 1] = _self10 * _sp0;
        return frustum_zo_rh_sb02d9182_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _sp0, _t0 * _t2_inv, _t1_inv * (left + right), _t2_inv * (bottom + top), _t11, _t12);
    }

    /** Piece 2 of {@code frustum_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] frustum_zo_rh_sb02d9182_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp0, float _sp1, float _sp2, float _sp3, float _t11, float _t12) {
        dest[destOffset + 2] = _self20 * _sp0;
        dest[destOffset + 3] = _self30 * _sp0;
        dest[destOffset + 4] = _self01 * _sp1;
        dest[destOffset + 5] = _self11 * _sp1;
        dest[destOffset + 6] = _self21 * _sp1;
        dest[destOffset + 7] = _self31 * _sp1;
        dest[destOffset + 8] = Math.fma(_self02, _t11, _self00 * _sp2 + _self01 * _sp3 - _self03);
        dest[destOffset + 9] = Math.fma(_self12, _t11, _self10 * _sp2 + _self11 * _sp3 - _self13);
        dest[destOffset + 10] = Math.fma(_self22, _t11, _self20 * _sp2 + _self21 * _sp3 - _self23);
        dest[destOffset + 11] = Math.fma(_self32, _t11, _self30 * _sp2 + _self31 * _sp3 - _self33);
        dest[destOffset + 12] = _self02 * _t12;
        dest[destOffset + 13] = _self12 * _t12;
        dest[destOffset + 14] = _self22 * _t12;
        dest[destOffset + 15] = _self32 * _t12;
        return dest;
    }

    public static float[] frustum_zo(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.frustum_zo_lh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Float4x4OpsKernelsArray.frustum_zo_rh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        float _t24 = Math.fma(_t23, _t14, upX);
        float _t25 = Math.fma(_t23, _t16, upY);
        return lookAt_lh_s4f06fa92_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t24, _t25, Math.fma(_t23, _t15, upZ), Math.fma(_t24, _t16, -(_t25 * _t14)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_s4f06fa92_1(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t14, float _t15, float _t16, float _t22, float _t24, float _t25, float _t26, float _t33) {
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        float _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        float _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        dest[destOffset + 0] = Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54));
        return lookAt_lh_s4f06fa92_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)), Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
    }

    /** Piece 3 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_s4f06fa92_2(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t58, float _t60) {
        dest[destOffset + 1] = Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54));
        dest[destOffset + 2] = Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54));
        dest[destOffset + 3] = Math.fma(_self32, _t14, Math.fma(_self30, _t43, _self31 * _t54));
        dest[destOffset + 4] = Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55));
        dest[destOffset + 5] = Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55));
        dest[destOffset + 6] = Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55));
        dest[destOffset + 7] = Math.fma(_self32, _t16, Math.fma(_self30, _t45, _self31 * _t55));
        dest[destOffset + 8] = Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56));
        dest[destOffset + 9] = Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56));
        dest[destOffset + 10] = Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56));
        dest[destOffset + 11] = Math.fma(_self32, _t15, Math.fma(_self30, _t44, _self31 * _t56));
        return lookAt_lh_s4f06fa92_3(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t22, _t58, _t60);
    }

    /** Piece 4 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_s4f06fa92_3(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t22, float _t58, float _t60) {
        dest[destOffset + 12] = Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03)));
        dest[destOffset + 13] = Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13)));
        dest[destOffset + 14] = Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23)));
        dest[destOffset + 15] = Math.fma(-_self30, _t58, Math.fma(-_self31, _t60, Math.fma(-_self32, _t22, _self33)));
        return dest;
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _t4 = centerZ - eyeZ;
        float _t5 = centerX - eyeX;
        float _t6 = centerY - eyeY;
        float _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t14 = (1.0f / (float) Math.sqrt(_t13));
        float _t18, _t19, _t20;
        if (_t13 != 0.0f) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0f;
            _t19 = 0.0f;
            _t20 = 0.0f;
        }
        float _t27 = -Math.fma(upZ, _t19, Math.fma(upX, _t18, upY * _t20));
        return lookAt_rh_s943596e4_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], _self02, _self12, _self22, _self32, src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], -_self02, -_self12, -_self22, -_self32, _t18, _t19, _t20, Math.fma(eyeZ, _t19, Math.fma(eyeX, _t18, eyeY * _t20)), Math.fma(_t27, _t20, upY), Math.fma(_t27, _t18, upX), Math.fma(_t27, _t19, upZ));
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s943596e4_1(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t0, float _t1, float _t2, float _t3, float _t18, float _t19, float _t20, float _t26, float _t28, float _t29, float _t30) {
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t20));
        float _t38 = Math.fma(_t29, _t19, -(_t30 * _t18));
        float _t39 = Math.fma(_t30, _t20, -(_t28 * _t19));
        float _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        float _t43 = (1.0f / (float) Math.sqrt(_t42));
        float _t47, _t48, _t49;
        if (_t42 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        float _t58 = Math.fma(_t48, _t19, -(_t49 * _t20));
        float _t59 = Math.fma(_t49, _t18, -(_t47 * _t19));
        float _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        return lookAt_rh_s943596e4_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t0, _t1, _t2, _t3, _t18, _t19, _t20, _t26, _t47, _t48, _t49, _t58, _t59, _t60, Math.fma(eyeZ, _t49, Math.fma(eyeX, _t47, eyeY * _t48)), Math.fma(eyeZ, _t60, Math.fma(eyeX, _t58, eyeY * _t59)));
    }

    /** Piece 3 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s943596e4_2(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t0, float _t1, float _t2, float _t3, float _t18, float _t19, float _t20, float _t26, float _t47, float _t48, float _t49, float _t58, float _t59, float _t60, float _t62, float _t64) {
        dest[destOffset + 0] = Math.fma(_t0, _t18, Math.fma(_self00, _t47, _self01 * _t58));
        dest[destOffset + 1] = Math.fma(_t1, _t18, Math.fma(_self10, _t47, _self11 * _t58));
        dest[destOffset + 2] = Math.fma(_t2, _t18, Math.fma(_self20, _t47, _self21 * _t58));
        dest[destOffset + 3] = Math.fma(_t3, _t18, Math.fma(_self30, _t47, _self31 * _t58));
        dest[destOffset + 4] = Math.fma(_t0, _t20, Math.fma(_self00, _t48, _self01 * _t59));
        dest[destOffset + 5] = Math.fma(_t1, _t20, Math.fma(_self10, _t48, _self11 * _t59));
        dest[destOffset + 6] = Math.fma(_t2, _t20, Math.fma(_self20, _t48, _self21 * _t59));
        dest[destOffset + 7] = Math.fma(_t3, _t20, Math.fma(_self30, _t48, _self31 * _t59));
        dest[destOffset + 8] = Math.fma(_t0, _t19, Math.fma(_self00, _t49, _self01 * _t60));
        dest[destOffset + 9] = Math.fma(_t1, _t19, Math.fma(_self10, _t49, _self11 * _t60));
        return lookAt_rh_s943596e4_3(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t2, _t3, _t19, _t26, _t49, _t60, _t62, _t64);
    }

    /** Piece 4 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s943596e4_3(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t2, float _t3, float _t19, float _t26, float _t49, float _t60, float _t62, float _t64) {
        dest[destOffset + 10] = Math.fma(_t2, _t19, Math.fma(_self20, _t49, _self21 * _t60));
        dest[destOffset + 11] = Math.fma(_t3, _t19, Math.fma(_self30, _t49, _self31 * _t60));
        dest[destOffset + 12] = Math.fma(-_self00, _t62, Math.fma(-_self01, _t64, Math.fma(_self02, _t26, _self03)));
        dest[destOffset + 13] = Math.fma(-_self10, _t62, Math.fma(-_self11, _t64, Math.fma(_self12, _t26, _self13)));
        dest[destOffset + 14] = Math.fma(-_self20, _t62, Math.fma(-_self21, _t64, Math.fma(_self22, _t26, _self23)));
        dest[destOffset + 15] = Math.fma(-_self30, _t62, Math.fma(-_self31, _t64, Math.fma(_self32, _t26, _self33)));
        return dest;
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _centerx = center[centerOffset + 0];
        float _centery = center[centerOffset + 1];
        float _centerz = center[centerOffset + 2];
        float _t0 = _centerz - _eyez;
        float _t1 = _centerx - _eyex;
        float _t2 = _centery - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        return lookAt_lh_s8ed8f70_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _eyex, _eyey, _eyez, up[upOffset + 0], up[upOffset + 1], up[upOffset + 2], _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)));
    }

    /** Piece 2 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_s8ed8f70_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t22) {
        float _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        float _t24 = Math.fma(_t23, _t14, _upx);
        float _t25 = Math.fma(_t23, _t16, _upy);
        float _t26 = Math.fma(_t23, _t15, _upz);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        return lookAt_lh_s8ed8f70_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _eyex, _eyey, _eyez, _t14, _t15, _t16, _t22, _t43, _t44, _t45, Math.fma(_t44, _t16, -(_t45 * _t15)), Math.fma(_t43, _t15, -(_t44 * _t14)), Math.fma(_t45, _t14, -(_t43 * _t16)));
    }

    /** Piece 3 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_s8ed8f70_2(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _eyex, float _eyey, float _eyez, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56) {
        dest[destOffset + 0] = Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54));
        dest[destOffset + 1] = Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54));
        dest[destOffset + 2] = Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54));
        dest[destOffset + 3] = Math.fma(_self32, _t14, Math.fma(_self30, _t43, _self31 * _t54));
        dest[destOffset + 4] = Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55));
        dest[destOffset + 5] = Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55));
        dest[destOffset + 6] = Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55));
        dest[destOffset + 7] = Math.fma(_self32, _t16, Math.fma(_self30, _t45, _self31 * _t55));
        dest[destOffset + 8] = Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56));
        return lookAt_lh_s8ed8f70_3(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t15, _t22, _t44, _t56, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)), Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
    }

    /** Piece 4 of {@code lookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_s8ed8f70_3(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t15, float _t22, float _t44, float _t56, float _t58, float _t60) {
        dest[destOffset + 9] = Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56));
        dest[destOffset + 10] = Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56));
        dest[destOffset + 11] = Math.fma(_self32, _t15, Math.fma(_self30, _t44, _self31 * _t56));
        dest[destOffset + 12] = Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03)));
        dest[destOffset + 13] = Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13)));
        dest[destOffset + 14] = Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23)));
        dest[destOffset + 15] = Math.fma(-_self30, _t58, Math.fma(-_self31, _t60, Math.fma(-_self32, _t22, _self33)));
        return dest;
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _centerx = center[centerOffset + 0];
        float _centery = center[centerOffset + 1];
        float _centerz = center[centerOffset + 2];
        float _t4 = _centerz - _eyez;
        float _t5 = _centerx - _eyex;
        float _t6 = _centery - _eyey;
        float _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        return lookAt_rh_s6349a11a_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], _self02, _self12, _self22, _self32, src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _eyex, _eyey, _eyez, up[upOffset + 0], up[upOffset + 1], up[upOffset + 2], -_self02, -_self12, -_self22, -_self32, _t4, _t5, _t6, _t13, (1.0f / (float) Math.sqrt(_t13)));
    }

    /** Piece 2 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s6349a11a_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t13, float _t14) {
        float _t18, _t19, _t20;
        if (_t13 != 0.0f) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0f;
            _t19 = 0.0f;
            _t20 = 0.0f;
        }
        float _t27 = -Math.fma(_upz, _t19, Math.fma(_upx, _t18, _upy * _t20));
        float _t28 = Math.fma(_t27, _t20, _upy);
        float _t29 = Math.fma(_t27, _t18, _upx);
        float _t30 = Math.fma(_t27, _t19, _upz);
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t20));
        float _t38 = Math.fma(_t29, _t19, -(_t30 * _t18));
        float _t39 = Math.fma(_t30, _t20, -(_t28 * _t19));
        float _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        return lookAt_rh_s6349a11a_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t0, _t1, _t2, _t3, _t18, _t19, _t20, Math.fma(_eyez, _t19, Math.fma(_eyex, _t18, _eyey * _t20)), _t37, _t38, _t39, _t42, (1.0f / (float) Math.sqrt(_t42)));
    }

    /** Piece 3 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s6349a11a_2(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t0, float _t1, float _t2, float _t3, float _t18, float _t19, float _t20, float _t26, float _t37, float _t38, float _t39, float _t42, float _t43) {
        float _t47, _t48, _t49;
        if (_t42 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        float _t58 = Math.fma(_t48, _t19, -(_t49 * _t20));
        float _t59 = Math.fma(_t49, _t18, -(_t47 * _t19));
        float _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        dest[destOffset + 0] = Math.fma(_t0, _t18, Math.fma(_self00, _t47, _self01 * _t58));
        dest[destOffset + 1] = Math.fma(_t1, _t18, Math.fma(_self10, _t47, _self11 * _t58));
        dest[destOffset + 2] = Math.fma(_t2, _t18, Math.fma(_self20, _t47, _self21 * _t58));
        dest[destOffset + 3] = Math.fma(_t3, _t18, Math.fma(_self30, _t47, _self31 * _t58));
        return lookAt_rh_s6349a11a_3(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t0, _t1, _t2, _t3, _t19, _t20, _t26, _t48, _t49, _t59, _t60, Math.fma(_eyez, _t49, Math.fma(_eyex, _t47, _eyey * _t48)), Math.fma(_eyez, _t60, Math.fma(_eyex, _t58, _eyey * _t59)));
    }

    /** Piece 4 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s6349a11a_3(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t0, float _t1, float _t2, float _t3, float _t19, float _t20, float _t26, float _t48, float _t49, float _t59, float _t60, float _t62, float _t64) {
        dest[destOffset + 4] = Math.fma(_t0, _t20, Math.fma(_self00, _t48, _self01 * _t59));
        dest[destOffset + 5] = Math.fma(_t1, _t20, Math.fma(_self10, _t48, _self11 * _t59));
        dest[destOffset + 6] = Math.fma(_t2, _t20, Math.fma(_self20, _t48, _self21 * _t59));
        dest[destOffset + 7] = Math.fma(_t3, _t20, Math.fma(_self30, _t48, _self31 * _t59));
        dest[destOffset + 8] = Math.fma(_t0, _t19, Math.fma(_self00, _t49, _self01 * _t60));
        dest[destOffset + 9] = Math.fma(_t1, _t19, Math.fma(_self10, _t49, _self11 * _t60));
        dest[destOffset + 10] = Math.fma(_t2, _t19, Math.fma(_self20, _t49, _self21 * _t60));
        dest[destOffset + 11] = Math.fma(_t3, _t19, Math.fma(_self30, _t49, _self31 * _t60));
        dest[destOffset + 12] = Math.fma(-_self00, _t62, Math.fma(-_self01, _t64, Math.fma(_self02, _t26, _self03)));
        dest[destOffset + 13] = Math.fma(-_self10, _t62, Math.fma(-_self11, _t64, Math.fma(_self12, _t26, _self13)));
        return lookAt_rh_s6349a11a_4(dest, destOffset, _self20, _self30, _self21, _self31, _self22, _self32, _self23, _self33, _t26, _t62, _t64);
    }

    /** Piece 5 of {@code lookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_s6349a11a_4(float[] dest, int destOffset, float _self20, float _self30, float _self21, float _self31, float _self22, float _self32, float _self23, float _self33, float _t26, float _t62, float _t64) {
        dest[destOffset + 14] = Math.fma(-_self20, _t62, Math.fma(-_self21, _t64, Math.fma(_self22, _t26, _self23)));
        dest[destOffset + 15] = Math.fma(-_self30, _t62, Math.fma(-_self31, _t64, Math.fma(_self32, _t26, _self33)));
        return dest;
    }

    public static float[] makeFrustum_no_lh(float[] dest, int destOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0 = zNear + zNear;
        float _t1_inv = 1.0f / (right - left);
        float _t2_inv = 1.0f / (top - bottom);
        float _t3_inv = 1.0f / (zNear - zFar);
        if (zFar == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -_t0;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0f;
                dest[destOffset + 14] = zFar + zFar;
            } else {
                dest[destOffset + 10] = -((zFar + zNear) * _t3_inv);
                dest[destOffset + 14] = _t0 * zFar * _t3_inv;
            }
        }
        dest[destOffset + 0] = _t0 * _t1_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t0 * _t2_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -((left + right) * _t1_inv);
        dest[destOffset + 9] = -((bottom + top) * _t2_inv);
        dest[destOffset + 11] = 1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makeFrustum_no_rh(float[] dest, int destOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0 = zNear + zNear;
        float _t1_inv = 1.0f / (right - left);
        float _t2_inv = 1.0f / (top - bottom);
        float _t3_inv = 1.0f / (zNear - zFar);
        if (zFar == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -_t0;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0f;
                dest[destOffset + 14] = zFar + zFar;
            } else {
                dest[destOffset + 10] = (zFar + zNear) * _t3_inv;
                dest[destOffset + 14] = _t0 * zFar * _t3_inv;
            }
        }
        dest[destOffset + 0] = _t0 * _t1_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t0 * _t2_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = (left + right) * _t1_inv;
        dest[destOffset + 9] = (bottom + top) * _t2_inv;
        dest[destOffset + 11] = -1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makeFrustum_no(float[] dest, int destOffset, float left, float right, float bottom, float top, float zNear, float zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makeFrustum_no_lh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Float4x4OpsKernelsArray.makeFrustum_no_rh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static float[] makeFrustum_zo_lh(float[] dest, int destOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0 = zNear + zNear;
        float _t1_inv = 1.0f / (right - left);
        float _t2_inv = 1.0f / (top - bottom);
        float _sp0 = zFar / (zNear - zFar);
        if (zFar == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -zNear;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = zFar;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = _sp0 * zNear;
            }
        }
        dest[destOffset + 0] = _t0 * _t1_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t0 * _t2_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -((left + right) * _t1_inv);
        dest[destOffset + 9] = -((bottom + top) * _t2_inv);
        dest[destOffset + 11] = 1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makeFrustum_zo_rh(float[] dest, int destOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0 = zNear + zNear;
        float _t1_inv = 1.0f / (right - left);
        float _t2_inv = 1.0f / (top - bottom);
        float _sp0 = zFar / (zNear - zFar);
        if (zFar == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -zNear;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = zFar;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = _sp0 * zNear;
            }
        }
        dest[destOffset + 0] = _t0 * _t1_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t0 * _t2_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = (left + right) * _t1_inv;
        dest[destOffset + 9] = (bottom + top) * _t2_inv;
        dest[destOffset + 11] = -1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makeFrustum_zo(float[] dest, int destOffset, float left, float right, float bottom, float top, float zNear, float zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makeFrustum_zo_lh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Float4x4OpsKernelsArray.makeFrustum_zo_rh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static float[] makeLookAt_lh(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -Math.fma(upZ, _t14, Math.fma(upX, _t15, upY * _t16));
        float _t21 = Math.fma(_t20, _t15, upX);
        float _t22 = Math.fma(_t20, _t16, upY);
        float _t23 = Math.fma(_t20, _t14, upZ);
        float _t30 = Math.fma(_t21, _t16, -(_t22 * _t15));
        float _t31 = Math.fma(_t22, _t14, -(_t23 * _t16));
        float _t32 = Math.fma(_t23, _t15, -(_t21 * _t14));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeLookAt_lh_s583cd9f4_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_lh_s583cd9f4_1(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = Math.fma(_t42, _t16, -(_t41 * _t14));
        float _t50 = Math.fma(_t40, _t14, -(_t42 * _t15));
        float _t51 = Math.fma(_t41, _t15, -(_t40 * _t16));
        dest[destOffset + 0] = _t40;
        dest[destOffset + 1] = _t49;
        dest[destOffset + 2] = _t15;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = _t41;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t16;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t42;
        dest[destOffset + 9] = _t51;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41));
        dest[destOffset + 13] = -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50));
        dest[destOffset + 14] = -Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16));
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makeLookAt_rh(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -Math.fma(upZ, _t14, Math.fma(upX, _t15, upY * _t16));
        float _t21 = Math.fma(_t20, _t16, upY);
        float _t22 = Math.fma(_t20, _t15, upX);
        float _t23 = Math.fma(_t20, _t14, upZ);
        float _t30 = Math.fma(_t21, _t15, -(_t22 * _t16));
        float _t31 = Math.fma(_t22, _t14, -(_t23 * _t15));
        float _t32 = Math.fma(_t23, _t16, -(_t21 * _t14));
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        return makeLookAt_rh_s5db7274a_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0f / (float) Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_rh_s5db7274a_1(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = Math.fma(_t41, _t14, -(_t42 * _t16));
        float _t50 = Math.fma(_t42, _t15, -(_t40 * _t14));
        float _t51 = Math.fma(_t40, _t16, -(_t41 * _t15));
        dest[destOffset + 0] = _t40;
        dest[destOffset + 1] = _t49;
        dest[destOffset + 2] = -_t15;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = _t41;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = -_t16;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t42;
        dest[destOffset + 9] = _t51;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -Math.fma(eyeZ, _t42, Math.fma(eyeX, _t40, eyeY * _t41));
        dest[destOffset + 13] = -Math.fma(eyeZ, _t51, Math.fma(eyeX, _t49, eyeY * _t50));
        dest[destOffset + 14] = Math.fma(eyeZ, _t14, Math.fma(eyeX, _t15, eyeY * _t16));
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makeLookAt_lh(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _centerx = center[centerOffset + 0];
        float _centery = center[centerOffset + 1];
        float _centerz = center[centerOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = _centerz - _eyez;
        float _t1 = _centerx - _eyex;
        float _t2 = _centery - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -Math.fma(_upz, _t14, Math.fma(_upx, _t15, _upy * _t16));
        float _t21 = Math.fma(_t20, _t15, _upx);
        float _t22 = Math.fma(_t20, _t16, _upy);
        float _t23 = Math.fma(_t20, _t14, _upz);
        return makeLookAt_lh_s61056a8a_1(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t16, -(_t22 * _t15)), Math.fma(_t22, _t14, -(_t23 * _t16)), Math.fma(_t23, _t15, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_lh_s61056a8a_1(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32) {
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t31 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = Math.fma(_t42, _t16, -(_t41 * _t14));
        float _t50 = Math.fma(_t40, _t14, -(_t42 * _t15));
        float _t51 = Math.fma(_t41, _t15, -(_t40 * _t16));
        dest[destOffset + 0] = _t40;
        dest[destOffset + 1] = _t49;
        dest[destOffset + 2] = _t15;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = _t41;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = _t16;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t42;
        dest[destOffset + 9] = _t51;
        dest[destOffset + 10] = _t14;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41));
        return makeLookAt_lh_s61056a8a_2(dest, destOffset, _eyex, _eyey, _eyez, _t14, _t15, _t16, _t49, _t50, _t51);
    }

    /** Piece 3 of {@code makeLookAt_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_lh_s61056a8a_2(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _t14, float _t15, float _t16, float _t49, float _t50, float _t51) {
        dest[destOffset + 13] = -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50));
        dest[destOffset + 14] = -Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16));
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makeLookAt_rh(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _centerx = center[centerOffset + 0];
        float _centery = center[centerOffset + 1];
        float _centerz = center[centerOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
        float _t0 = _centerz - _eyez;
        float _t1 = _centerx - _eyex;
        float _t2 = _centery - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t0 * _t10;
            _t15 = _t1 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t20 = -Math.fma(_upz, _t14, Math.fma(_upx, _t15, _upy * _t16));
        float _t21 = Math.fma(_t20, _t16, _upy);
        float _t22 = Math.fma(_t20, _t15, _upx);
        float _t23 = Math.fma(_t20, _t14, _upz);
        return makeLookAt_rh_sc987b5b8_1(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t15, -(_t22 * _t16)), Math.fma(_t22, _t14, -(_t23 * _t15)), Math.fma(_t23, _t16, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_rh_sc987b5b8_1(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _upx, float _upy, float _upz, float _t14, float _t15, float _t16, float _t30, float _t31, float _t32) {
        float _t35 = Math.fma(_t30, _t30, Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t32 * _t36;
            _t41 = _t31 * _t36;
            _t42 = _t30 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t49 = Math.fma(_t41, _t14, -(_t42 * _t16));
        float _t50 = Math.fma(_t42, _t15, -(_t40 * _t14));
        float _t51 = Math.fma(_t40, _t16, -(_t41 * _t15));
        dest[destOffset + 0] = _t40;
        dest[destOffset + 1] = _t49;
        dest[destOffset + 2] = -_t15;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = _t41;
        dest[destOffset + 5] = _t50;
        dest[destOffset + 6] = -_t16;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t42;
        dest[destOffset + 9] = _t51;
        dest[destOffset + 10] = -_t14;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41));
        return makeLookAt_rh_sc987b5b8_2(dest, destOffset, _eyex, _eyey, _eyez, _t14, _t15, _t16, _t49, _t50, _t51);
    }

    /** Piece 3 of {@code makeLookAt_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makeLookAt_rh_sc987b5b8_2(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _t14, float _t15, float _t16, float _t49, float _t50, float _t51) {
        dest[destOffset + 13] = -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50));
        dest[destOffset + 14] = Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16));
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makeOrtho_no_lh(float[] dest, int destOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zFar - zNear);
        dest[destOffset + 0] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = _t2_inv + _t2_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((left + right) * _t0_inv);
        dest[destOffset + 13] = -((bottom + top) * _t1_inv);
        dest[destOffset + 14] = -((zFar + zNear) * _t2_inv);
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makeOrtho_no_rh(float[] dest, int destOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zFar - zNear);
        dest[destOffset + 0] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = -2.0f * _t2_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((left + right) * _t0_inv);
        dest[destOffset + 13] = -((bottom + top) * _t1_inv);
        dest[destOffset + 14] = -((zFar + zNear) * _t2_inv);
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makeOrtho_no(float[] dest, int destOffset, float left, float right, float bottom, float top, float zNear, float zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makeOrtho_no_lh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Float4x4OpsKernelsArray.makeOrtho_no_rh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static float[] makeOrtho_zo_lh(float[] dest, int destOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zFar - zNear);
        dest[destOffset + 0] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = _t2_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((left + right) * _t0_inv);
        dest[destOffset + 13] = -((bottom + top) * _t1_inv);
        dest[destOffset + 14] = -(zNear * _t2_inv);
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makeOrtho_zo_rh(float[] dest, int destOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zFar - zNear);
        dest[destOffset + 0] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = -_t2_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((left + right) * _t0_inv);
        dest[destOffset + 13] = -((bottom + top) * _t1_inv);
        dest[destOffset + 14] = -(zNear * _t2_inv);
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makeOrtho_zo(float[] dest, int destOffset, float left, float right, float bottom, float top, float zNear, float zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makeOrtho_zo_lh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Float4x4OpsKernelsArray.makeOrtho_zo_rh(dest, destOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static float[] makeOrtho2D_no_lh(float[] dest, int destOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dest[destOffset + 0] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = 1.0f;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((left + right) * _t0_inv);
        dest[destOffset + 13] = -((bottom + top) * _t1_inv);
        dest[destOffset + 14] = 0.0f;
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makeOrtho2D_no_rh(float[] dest, int destOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dest[destOffset + 0] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = -1.0f;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((left + right) * _t0_inv);
        dest[destOffset + 13] = -((bottom + top) * _t1_inv);
        dest[destOffset + 14] = 0.0f;
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makeOrtho2D_no(float[] dest, int destOffset, float left, float right, float bottom, float top, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makeOrtho2D_no_lh(dest, destOffset, left, right, bottom, top); }
            default -> { return Float4x4OpsKernelsArray.makeOrtho2D_no_rh(dest, destOffset, left, right, bottom, top); }
        }
    }

    public static float[] makeOrtho2D_zo_lh(float[] dest, int destOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dest[destOffset + 0] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = 0.5f;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((left + right) * _t0_inv);
        dest[destOffset + 13] = -((bottom + top) * _t1_inv);
        dest[destOffset + 14] = 0.5f;
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makeOrtho2D_zo_rh(float[] dest, int destOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dest[destOffset + 0] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t1_inv + _t1_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = -0.5f;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((left + right) * _t0_inv);
        dest[destOffset + 13] = -((bottom + top) * _t1_inv);
        dest[destOffset + 14] = 0.5f;
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makeOrtho2D_zo(float[] dest, int destOffset, float left, float right, float bottom, float top, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makeOrtho2D_zo_lh(dest, destOffset, left, right, bottom, top); }
            default -> { return Float4x4OpsKernelsArray.makeOrtho2D_zo_rh(dest, destOffset, left, right, bottom, top); }
        }
    }

    public static float[] makePerspective_no_lh(float[] dest, int destOffset, float fovy, float aspect, float near, float far) {
        float _sp0 = near + near;
        float _t1_inv = 1.0f / (near - far);
        float _t2 = (float) Math.tan(0.5f * fovy);
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0f;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = -((far + near) * _t1_inv);
                dest[destOffset + 14] = _sp0 * far * _t1_inv;
            }
        }
        dest[destOffset + 0] = 1.0f / (aspect * _t2);
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 1.0f / _t2;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 11] = 1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspective_no_rh(float[] dest, int destOffset, float fovy, float aspect, float near, float far) {
        float _sp0 = near + near;
        float _t1_inv = 1.0f / (near - far);
        float _t2 = (float) Math.tan(0.5f * fovy);
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0f;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = (far + near) * _t1_inv;
                dest[destOffset + 14] = _sp0 * far * _t1_inv;
            }
        }
        dest[destOffset + 0] = 1.0f / (aspect * _t2);
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 1.0f / _t2;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 11] = -1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspective_no(float[] dest, int destOffset, float fovy, float aspect, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makePerspective_no_lh(dest, destOffset, fovy, aspect, near, far); }
            default -> { return Float4x4OpsKernelsArray.makePerspective_no_rh(dest, destOffset, fovy, aspect, near, far); }
        }
    }

    public static float[] makePerspective_zo_lh(float[] dest, int destOffset, float fovy, float aspect, float near, float far) {
        float _sp0 = far / (near - far);
        float _t2 = (float) Math.tan(0.5f * fovy);
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset + 0] = 1.0f / (aspect * _t2);
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 1.0f / _t2;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 11] = 1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspective_zo_rh(float[] dest, int destOffset, float fovy, float aspect, float near, float far) {
        float _sp0 = far / (near - far);
        float _t2 = (float) Math.tan(0.5f * fovy);
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset + 0] = 1.0f / (aspect * _t2);
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 1.0f / _t2;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 11] = -1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspective_zo(float[] dest, int destOffset, float fovy, float aspect, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makePerspective_zo_lh(dest, destOffset, fovy, aspect, near, far); }
            default -> { return Float4x4OpsKernelsArray.makePerspective_zo_rh(dest, destOffset, fovy, aspect, near, far); }
        }
    }

    public static float[] makePerspectiveFovRange_no_lh(float[] dest, int destOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _sp0 = near + near;
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _t2_inv = 1.0f / (near - far);
        float _t3 = _t0 - _t1;
        float _t3_inv = 1.0f / _t3;
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0f;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = -((far + near) * _t2_inv);
                dest[destOffset + 14] = _sp0 * far * _t2_inv;
            }
        }
        dest[destOffset + 0] = 2.0f / (aspect * _t3);
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t3_inv + _t3_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = -((_t0 + _t1) * _t3_inv);
        dest[destOffset + 11] = 1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveFovRange_no_rh(float[] dest, int destOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _sp0 = near + near;
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _t2_inv = 1.0f / (near - far);
        float _t3 = _t0 - _t1;
        float _t3_inv = 1.0f / _t3;
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0f;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = (far + near) * _t2_inv;
                dest[destOffset + 14] = _sp0 * far * _t2_inv;
            }
        }
        dest[destOffset + 0] = 2.0f / (aspect * _t3);
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t3_inv + _t3_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = (_t0 + _t1) * _t3_inv;
        dest[destOffset + 11] = -1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveFovRange_no(float[] dest, int destOffset, float angleMin, float angleMax, float aspect, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makePerspectiveFovRange_no_lh(dest, destOffset, angleMin, angleMax, aspect, near, far); }
            default -> { return Float4x4OpsKernelsArray.makePerspectiveFovRange_no_rh(dest, destOffset, angleMin, angleMax, aspect, near, far); }
        }
    }

    public static float[] makePerspectiveFovRange_zo_lh(float[] dest, int destOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _sp0 = far / (near - far);
        float _t3 = _t0 - _t1;
        float _t3_inv = 1.0f / _t3;
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset + 0] = 2.0f / (aspect * _t3);
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t3_inv + _t3_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = -((_t0 + _t1) * _t3_inv);
        dest[destOffset + 11] = 1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveFovRange_zo_rh(float[] dest, int destOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _sp0 = far / (near - far);
        float _t3 = _t0 - _t1;
        float _t3_inv = 1.0f / _t3;
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset + 0] = 2.0f / (aspect * _t3);
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t3_inv + _t3_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = (_t0 + _t1) * _t3_inv;
        dest[destOffset + 11] = -1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveFovRange_zo(float[] dest, int destOffset, float angleMin, float angleMax, float aspect, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makePerspectiveFovRange_zo_lh(dest, destOffset, angleMin, angleMax, aspect, near, far); }
            default -> { return Float4x4OpsKernelsArray.makePerspectiveFovRange_zo_rh(dest, destOffset, angleMin, angleMax, aspect, near, far); }
        }
    }

    public static float[] makePerspectiveOffCenterFov_no_lh(float[] dest, int destOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _sp0 = near + near;
        float _t0 = (float) Math.tan(angleRight);
        float _t1 = (float) Math.tan(angleLeft);
        float _t2 = (float) Math.tan(angleUp);
        float _t3 = (float) Math.tan(angleDown);
        float _t4_inv = 1.0f / (near - far);
        float _t5_inv = 1.0f / (_t0 - _t1);
        float _t6_inv = 1.0f / (_t2 - _t3);
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0f;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = -((far + near) * _t4_inv);
                dest[destOffset + 14] = _sp0 * far * _t4_inv;
            }
        }
        dest[destOffset + 0] = _t5_inv + _t5_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t6_inv + _t6_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -((_t1 + _t0) * _t5_inv);
        dest[destOffset + 9] = -((_t3 + _t2) * _t6_inv);
        dest[destOffset + 11] = 1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterFov_no_rh(float[] dest, int destOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _sp0 = near + near;
        float _t0 = (float) Math.tan(angleRight);
        float _t1 = (float) Math.tan(angleLeft);
        float _t2 = (float) Math.tan(angleUp);
        float _t3 = (float) Math.tan(angleDown);
        float _t4_inv = 1.0f / (near - far);
        float _t5_inv = 1.0f / (_t0 - _t1);
        float _t6_inv = 1.0f / (_t2 - _t3);
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0f;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = (far + near) * _t4_inv;
                dest[destOffset + 14] = _sp0 * far * _t4_inv;
            }
        }
        dest[destOffset + 0] = _t5_inv + _t5_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t6_inv + _t6_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = (_t1 + _t0) * _t5_inv;
        dest[destOffset + 9] = (_t3 + _t2) * _t6_inv;
        dest[destOffset + 11] = -1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterFov_no(float[] dest, int destOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makePerspectiveOffCenterFov_no_lh(dest, destOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
            default -> { return Float4x4OpsKernelsArray.makePerspectiveOffCenterFov_no_rh(dest, destOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
        }
    }

    public static float[] makePerspectiveOffCenterFov_zo_lh(float[] dest, int destOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = (float) Math.tan(angleRight);
        float _t1 = (float) Math.tan(angleLeft);
        float _t2 = (float) Math.tan(angleUp);
        float _t3 = (float) Math.tan(angleDown);
        float _sp0 = far / (near - far);
        float _t5_inv = 1.0f / (_t0 - _t1);
        float _t6_inv = 1.0f / (_t2 - _t3);
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset + 0] = _t5_inv + _t5_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t6_inv + _t6_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -((_t1 + _t0) * _t5_inv);
        dest[destOffset + 9] = -((_t3 + _t2) * _t6_inv);
        dest[destOffset + 11] = 1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterFov_zo_rh(float[] dest, int destOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = (float) Math.tan(angleRight);
        float _t1 = (float) Math.tan(angleLeft);
        float _t2 = (float) Math.tan(angleUp);
        float _t3 = (float) Math.tan(angleDown);
        float _sp0 = far / (near - far);
        float _t5_inv = 1.0f / (_t0 - _t1);
        float _t6_inv = 1.0f / (_t2 - _t3);
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset + 0] = _t5_inv + _t5_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t6_inv + _t6_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = (_t1 + _t0) * _t5_inv;
        dest[destOffset + 9] = (_t3 + _t2) * _t6_inv;
        dest[destOffset + 11] = -1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterFov_zo(float[] dest, int destOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makePerspectiveOffCenterFov_zo_lh(dest, destOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
            default -> { return Float4x4OpsKernelsArray.makePerspectiveOffCenterFov_zo_rh(dest, destOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
        }
    }

    public static float[] makePerspectiveOffCenterRectangleProj_no_lh(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float pX, float pY, float pZ, float xX, float xY, float xZ, float yX, float yY, float yZ, float nearFarDist) {
        float _t10 = Math.fma(xY, yX, -(xX * yY));
        float _t11 = Math.fma(xZ, yY, -(xY * yZ));
        float _t12 = Math.fma(xX, yZ, -(xZ * yX));
        float _t15 = Math.fma(pZ - eyeZ, _t10, Math.fma(pX - eyeX, _t11, (pY - eyeY) * _t12));
        float _t16 = _t15 >= 0.0f ? 1.0f : -1.0f;
        float _t17 = _t10 * _t16;
        float _t18 = _t12 * _t16;
        float _t19 = _t11 * _t16;
        float _t20 = _t15 * _t16;
        float _t24 = (1.0f / (float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        float _t25 = _t18 * _t24;
        float _t26 = _t19 * _t24;
        float _t27 = _t17 * _t24;
        float _t34 = _t20 * _t24;
        float _t37 = Math.fma(yX, _t25, -(yY * _t26));
        float _t38 = Math.fma(yY, _t27, -(yZ * _t25));
        float _t39 = Math.fma(yZ, _t26, -(yX * _t27));
        return makePerspectiveOffCenterRectangleProj_no_lh_s88241c27_1(dest, destOffset, eyeX, eyeY, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, 1.0f / nearFarDist, -eyeZ, _t20, _t24, _t25, _t26, _t27, _t34, Math.fma(_t20, _t24, nearFarDist), _t34 + _t34, _t37, _t38, _t39, (1.0f / (float) Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39)))));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_no_lh_s88241c27_1(float[] dest, int destOffset, float eyeX, float eyeY, float pX, float pY, float pZ, float xX, float xY, float xZ, float yX, float yY, float yZ, float _rcp0, float _t0, float _t20, float _t24, float _t25, float _t26, float _t27, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t43) {
        float _t44 = _t37 * _t43;
        float _t45 = _t38 * _t43;
        float _t46 = _t39 * _t43;
        float _t63 = Math.fma(_t46, _t26, -(_t45 * _t25));
        float _t64 = Math.fma(_t44, _t25, -(_t46 * _t27));
        float _t65 = Math.fma(_t45, _t27, -(_t44 * _t26));
        if (_t35 == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -_t36;
        } else {
            if (_t34 == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0f;
                dest[destOffset + 14] = _t35 + _t35;
            } else {
                dest[destOffset + 10] = Math.fma(_t20, _t24, _t35) * _rcp0;
                dest[destOffset + 14] = -(_t36 * _t35 * _rcp0);
            }
        }
        return makePerspectiveOffCenterRectangleProj_no_lh_s88241c27_2(dest, destOffset, xX, xY, xZ, yX, yY, yZ, _t36, _t44, _t45, _t46, Math.fma(pX, _t45, pY * _t46), Math.fma(pZ, _t44, -(eyeX * _t45)), Math.fma(_t0, _t44, -(eyeY * _t46)), _t63, _t64, _t65, 1.0f / Math.fma(xZ, _t44, Math.fma(xX, _t45, xY * _t46)), Math.fma(pX, _t64, pY * _t65), Math.fma(pZ, _t63, -(eyeX * _t64)), Math.fma(_t0, _t63, -(eyeY * _t65)), 1.0f / Math.fma(yZ, _t63, Math.fma(yX, _t64, yY * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_no_lh_s88241c27_2(float[] dest, int destOffset, float xX, float xY, float xZ, float yX, float yY, float yZ, float _t36, float _t44, float _t45, float _t46, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t72_inv, float _t74, float _t75, float _t76, float _t77_inv) {
        dest[destOffset + 0] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -((Math.fma(xX, _t45, Math.fma(xY, _t46, xZ * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv);
        dest[destOffset + 9] = -((Math.fma(yX, _t64, Math.fma(yY, _t65, yZ * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv);
        dest[destOffset + 11] = 1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterRectangleProj_no_rh(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float pX, float pY, float pZ, float xX, float xY, float xZ, float yX, float yY, float yZ, float nearFarDist) {
        float _t10 = Math.fma(xY, yX, -(xX * yY));
        float _t11 = Math.fma(xZ, yY, -(xY * yZ));
        float _t12 = Math.fma(xX, yZ, -(xZ * yX));
        float _t15 = Math.fma(pZ - eyeZ, _t10, Math.fma(pX - eyeX, _t11, (pY - eyeY) * _t12));
        float _t16 = _t15 >= 0.0f ? 1.0f : -1.0f;
        float _t17 = _t10 * _t16;
        float _t18 = _t12 * _t16;
        float _t19 = _t11 * _t16;
        float _t20 = _t15 * _t16;
        float _t24 = (1.0f / (float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        float _t25 = _t19 * _t24;
        float _t26 = _t18 * _t24;
        float _t27 = _t17 * _t24;
        float _t34 = _t20 * _t24;
        float _t37 = Math.fma(yY, _t25, -(yX * _t26));
        float _t38 = Math.fma(yX, _t27, -(yZ * _t25));
        float _t39 = Math.fma(yZ, _t26, -(yY * _t27));
        return makePerspectiveOffCenterRectangleProj_no_rh_s953b2fb5_1(dest, destOffset, eyeX, eyeY, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, 1.0f / nearFarDist, -eyeZ, _t20, _t24, _t25, _t26, _t27, _t34, Math.fma(_t20, _t24, nearFarDist), _t34 + _t34, _t37, _t38, _t39, (1.0f / (float) Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39)))));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_no_rh_s953b2fb5_1(float[] dest, int destOffset, float eyeX, float eyeY, float pX, float pY, float pZ, float xX, float xY, float xZ, float yX, float yY, float yZ, float _rcp0, float _t0, float _t20, float _t24, float _t25, float _t26, float _t27, float _t34, float _t35, float _t36, float _t37, float _t38, float _t39, float _t43) {
        float _t44 = _t37 * _t43;
        float _t45 = _t39 * _t43;
        float _t46 = _t38 * _t43;
        float _t63 = Math.fma(_t45, _t26, -(_t46 * _t25));
        float _t64 = Math.fma(_t46, _t27, -(_t44 * _t26));
        float _t65 = Math.fma(_t44, _t25, -(_t45 * _t27));
        if (_t35 == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -_t36;
        } else {
            if (_t34 == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0f;
                dest[destOffset + 14] = _t35 + _t35;
            } else {
                dest[destOffset + 10] = -(Math.fma(_t20, _t24, _t35) * _rcp0);
                dest[destOffset + 14] = -(_t36 * _t35 * _rcp0);
            }
        }
        return makePerspectiveOffCenterRectangleProj_no_rh_s953b2fb5_2(dest, destOffset, xX, xY, xZ, yX, yY, yZ, _t36, _t44, _t45, _t46, Math.fma(pX, _t45, pY * _t46), Math.fma(pZ, _t44, -(eyeX * _t45)), Math.fma(_t0, _t44, -(eyeY * _t46)), _t63, _t64, _t65, 1.0f / Math.fma(xZ, _t44, Math.fma(xX, _t45, xY * _t46)), Math.fma(pX, _t64, pY * _t65), Math.fma(pZ, _t63, -(eyeX * _t64)), Math.fma(_t0, _t63, -(eyeY * _t65)), 1.0f / Math.fma(yZ, _t63, Math.fma(yX, _t64, yY * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_no_rh_s953b2fb5_2(float[] dest, int destOffset, float xX, float xY, float xZ, float yX, float yY, float yZ, float _t36, float _t44, float _t45, float _t46, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t72_inv, float _t74, float _t75, float _t76, float _t77_inv) {
        dest[destOffset + 0] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = (Math.fma(xX, _t45, Math.fma(xY, _t46, xZ * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv;
        dest[destOffset + 9] = (Math.fma(yX, _t64, Math.fma(yY, _t65, yZ * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv;
        dest[destOffset + 11] = -1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterRectangleProj_no(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float pX, float pY, float pZ, float xX, float xY, float xZ, float yX, float yY, float yZ, float nearFarDist, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_no_lh(dest, destOffset, eyeX, eyeY, eyeZ, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, nearFarDist); }
            default -> { return Float4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_no_rh(dest, destOffset, eyeX, eyeY, eyeZ, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, nearFarDist); }
        }
    }

    public static float[] makePerspectiveOffCenterRectangleProj_zo_lh(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float pX, float pY, float pZ, float xX, float xY, float xZ, float yX, float yY, float yZ, float nearFarDist) {
        float _t10 = Math.fma(xY, yX, -(xX * yY));
        float _t11 = Math.fma(xZ, yY, -(xY * yZ));
        float _t12 = Math.fma(xX, yZ, -(xZ * yX));
        float _t15 = Math.fma(pZ - eyeZ, _t10, Math.fma(pX - eyeX, _t11, (pY - eyeY) * _t12));
        float _t16 = _t15 >= 0.0f ? 1.0f : -1.0f;
        float _t17 = _t10 * _t16;
        float _t18 = _t12 * _t16;
        float _t19 = _t11 * _t16;
        float _t20 = _t15 * _t16;
        float _t24 = (1.0f / (float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        float _t25 = _t18 * _t24;
        float _t26 = _t19 * _t24;
        float _t27 = _t17 * _t24;
        float _t34 = _t20 * _t24;
        float _t35 = Math.fma(_t20, _t24, nearFarDist);
        float _t37 = Math.fma(yX, _t25, -(yY * _t26));
        float _t38 = Math.fma(yY, _t27, -(yZ * _t25));
        float _t39 = Math.fma(yZ, _t26, -(yX * _t27));
        return makePerspectiveOffCenterRectangleProj_zo_lh_s9577612b_1(dest, destOffset, eyeX, eyeY, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, -eyeZ, _t25, _t26, _t27, _t34, _t35, _t35 / nearFarDist, _t34 + _t34, _t37, _t38, _t39, (1.0f / (float) Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39)))));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_zo_lh_s9577612b_1(float[] dest, int destOffset, float eyeX, float eyeY, float pX, float pY, float pZ, float xX, float xY, float xZ, float yX, float yY, float yZ, float _t0, float _t25, float _t26, float _t27, float _t34, float _t35, float _sp0, float _t36, float _t37, float _t38, float _t39, float _t43) {
        float _t44 = _t37 * _t43;
        float _t45 = _t38 * _t43;
        float _t46 = _t39 * _t43;
        float _t63 = Math.fma(_t46, _t26, -(_t45 * _t25));
        float _t64 = Math.fma(_t44, _t25, -(_t46 * _t27));
        float _t65 = Math.fma(_t45, _t27, -(_t44 * _t26));
        float _t72_inv = 1.0f / Math.fma(xZ, _t44, Math.fma(xX, _t45, xY * _t46));
        if (_t35 == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -_t34;
        } else {
            if (_t34 == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = _t35;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = -(_sp0 * _t34);
            }
        }
        dest[destOffset + 0] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0f;
        return makePerspectiveOffCenterRectangleProj_zo_lh_s9577612b_2(dest, destOffset, xX, xY, xZ, yX, yY, yZ, _t36, _t44, _t45, _t46, Math.fma(pX, _t45, pY * _t46), Math.fma(pZ, _t44, -(eyeX * _t45)), Math.fma(_t0, _t44, -(eyeY * _t46)), _t63, _t64, _t65, _t72_inv, Math.fma(pX, _t64, pY * _t65), Math.fma(pZ, _t63, -(eyeX * _t64)), Math.fma(_t0, _t63, -(eyeY * _t65)), 1.0f / Math.fma(yZ, _t63, Math.fma(yX, _t64, yY * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_zo_lh_s9577612b_2(float[] dest, int destOffset, float xX, float xY, float xZ, float yX, float yY, float yZ, float _t36, float _t44, float _t45, float _t46, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t72_inv, float _t74, float _t75, float _t76, float _t77_inv) {
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -((Math.fma(xX, _t45, Math.fma(xY, _t46, xZ * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv);
        dest[destOffset + 9] = -((Math.fma(yX, _t64, Math.fma(yY, _t65, yZ * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv);
        dest[destOffset + 11] = 1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterRectangleProj_zo_rh(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float pX, float pY, float pZ, float xX, float xY, float xZ, float yX, float yY, float yZ, float nearFarDist) {
        float _t10 = Math.fma(xY, yX, -(xX * yY));
        float _t11 = Math.fma(xZ, yY, -(xY * yZ));
        float _t12 = Math.fma(xX, yZ, -(xZ * yX));
        float _t15 = Math.fma(pZ - eyeZ, _t10, Math.fma(pX - eyeX, _t11, (pY - eyeY) * _t12));
        float _t16 = _t15 >= 0.0f ? 1.0f : -1.0f;
        float _t17 = _t10 * _t16;
        float _t18 = _t12 * _t16;
        float _t19 = _t11 * _t16;
        float _t20 = _t15 * _t16;
        float _t24 = (1.0f / (float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        float _t25 = _t19 * _t24;
        float _t26 = _t18 * _t24;
        float _t27 = _t17 * _t24;
        float _t34 = _t20 * _t24;
        float _t35 = Math.fma(_t20, _t24, nearFarDist);
        float _t37 = Math.fma(yY, _t25, -(yX * _t26));
        float _t38 = Math.fma(yX, _t27, -(yZ * _t25));
        float _t39 = Math.fma(yZ, _t26, -(yY * _t27));
        return makePerspectiveOffCenterRectangleProj_zo_rh_sdfab6cc1_1(dest, destOffset, eyeX, eyeY, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, -eyeZ, _t25, _t26, _t27, _t34, _t35, _t35 / nearFarDist, _t34 + _t34, _t37, _t38, _t39, (1.0f / (float) Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39)))));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_zo_rh_sdfab6cc1_1(float[] dest, int destOffset, float eyeX, float eyeY, float pX, float pY, float pZ, float xX, float xY, float xZ, float yX, float yY, float yZ, float _t0, float _t25, float _t26, float _t27, float _t34, float _t35, float _sp0, float _t36, float _t37, float _t38, float _t39, float _t43) {
        float _t44 = _t37 * _t43;
        float _t45 = _t39 * _t43;
        float _t46 = _t38 * _t43;
        float _t63 = Math.fma(_t45, _t26, -(_t46 * _t25));
        float _t64 = Math.fma(_t46, _t27, -(_t44 * _t26));
        float _t65 = Math.fma(_t44, _t25, -(_t45 * _t27));
        float _t72_inv = 1.0f / Math.fma(xZ, _t44, Math.fma(xX, _t45, xY * _t46));
        if (_t35 == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -_t34;
        } else {
            if (_t34 == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = _t35;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = -(_sp0 * _t34);
            }
        }
        dest[destOffset + 0] = _t36 * _t72_inv;
        return makePerspectiveOffCenterRectangleProj_zo_rh_sdfab6cc1_2(dest, destOffset, xX, xY, xZ, yX, yY, yZ, _t36, _t44, _t45, _t46, Math.fma(pX, _t45, pY * _t46), Math.fma(pZ, _t44, -(eyeX * _t45)), Math.fma(_t0, _t44, -(eyeY * _t46)), _t63, _t64, _t65, _t72_inv, Math.fma(pX, _t64, pY * _t65), Math.fma(pZ, _t63, -(eyeX * _t64)), Math.fma(_t0, _t63, -(eyeY * _t65)), 1.0f / Math.fma(yZ, _t63, Math.fma(yX, _t64, yY * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_zo_rh_sdfab6cc1_2(float[] dest, int destOffset, float xX, float xY, float xZ, float yX, float yY, float yZ, float _t36, float _t44, float _t45, float _t46, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t72_inv, float _t74, float _t75, float _t76, float _t77_inv) {
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = (Math.fma(xX, _t45, Math.fma(xY, _t46, xZ * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv;
        dest[destOffset + 9] = (Math.fma(yX, _t64, Math.fma(yY, _t65, yZ * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv;
        dest[destOffset + 11] = -1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterRectangleProj_zo(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float pX, float pY, float pZ, float xX, float xY, float xZ, float yX, float yY, float yZ, float nearFarDist, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_zo_lh(dest, destOffset, eyeX, eyeY, eyeZ, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, nearFarDist); }
            default -> { return Float4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_zo_rh(dest, destOffset, eyeX, eyeY, eyeZ, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, nearFarDist); }
        }
    }

    public static float[] makePerspectiveOffCenterRectangleProj_no_lh(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] p, int pOffset, float[] x, int xOffset, float[] y, int yOffset, float nearFarDist) {
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _px = p[pOffset + 0];
        float _py = p[pOffset + 1];
        float _pz = p[pOffset + 2];
        float _xx = x[xOffset + 0];
        float _xy = x[xOffset + 1];
        float _xz = x[xOffset + 2];
        float _yx = y[yOffset + 0];
        float _yy = y[yOffset + 1];
        float _yz = y[yOffset + 2];
        float _t10 = Math.fma(_xy, _yx, -(_xx * _yy));
        float _t11 = Math.fma(_xz, _yy, -(_xy * _yz));
        float _t12 = Math.fma(_xx, _yz, -(_xz * _yx));
        float _t15 = Math.fma(_pz - _eyez, _t10, Math.fma(_px - _eyex, _t11, (_py - _eyey) * _t12));
        float _t16 = _t15 >= 0.0f ? 1.0f : -1.0f;
        float _t17 = _t10 * _t16;
        float _t18 = _t12 * _t16;
        float _t19 = _t11 * _t16;
        float _t20 = _t15 * _t16;
        float _t24 = (1.0f / (float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        return makePerspectiveOffCenterRectangleProj_no_lh_sb2d07c39_1(dest, destOffset, nearFarDist, _eyex, _eyey, _px, _py, _pz, _xx, _xy, _xz, _yx, _yy, _yz, 1.0f / nearFarDist, -_eyez, _t20, _t24, _t18 * _t24, _t19 * _t24, _t17 * _t24, _t20 * _t24);
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_no_lh_sb2d07c39_1(float[] dest, int destOffset, float nearFarDist, float _eyex, float _eyey, float _px, float _py, float _pz, float _xx, float _xy, float _xz, float _yx, float _yy, float _yz, float _rcp0, float _t0, float _t20, float _t24, float _t25, float _t26, float _t27, float _t34) {
        float _t37 = Math.fma(_yx, _t25, -(_yy * _t26));
        float _t38 = Math.fma(_yy, _t27, -(_yz * _t25));
        float _t39 = Math.fma(_yz, _t26, -(_yx * _t27));
        float _t43 = (1.0f / (float) Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39))));
        float _t44 = _t37 * _t43;
        float _t45 = _t38 * _t43;
        float _t46 = _t39 * _t43;
        float _t63 = Math.fma(_t46, _t26, -(_t45 * _t25));
        float _t64 = Math.fma(_t44, _t25, -(_t46 * _t27));
        float _t65 = Math.fma(_t45, _t27, -(_t44 * _t26));
        return makePerspectiveOffCenterRectangleProj_no_lh_sb2d07c39_2(dest, destOffset, _xx, _xy, _xz, _yx, _yy, _yz, _rcp0, _t20, _t24, _t34, Math.fma(_t20, _t24, nearFarDist), _t34 + _t34, _t44, _t45, _t46, Math.fma(_px, _t45, _py * _t46), Math.fma(_pz, _t44, -(_eyex * _t45)), Math.fma(_t0, _t44, -(_eyey * _t46)), _t63, _t64, _t65, 1.0f / Math.fma(_xz, _t44, Math.fma(_xx, _t45, _xy * _t46)), Math.fma(_px, _t64, _py * _t65), Math.fma(_pz, _t63, -(_eyex * _t64)), Math.fma(_t0, _t63, -(_eyey * _t65)), 1.0f / Math.fma(_yz, _t63, Math.fma(_yx, _t64, _yy * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_no_lh_sb2d07c39_2(float[] dest, int destOffset, float _xx, float _xy, float _xz, float _yx, float _yy, float _yz, float _rcp0, float _t20, float _t24, float _t34, float _t35, float _t36, float _t44, float _t45, float _t46, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t72_inv, float _t74, float _t75, float _t76, float _t77_inv) {
        if (_t35 == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -_t36;
        } else {
            if (_t34 == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0f;
                dest[destOffset + 14] = _t35 + _t35;
            } else {
                dest[destOffset + 10] = Math.fma(_t20, _t24, _t35) * _rcp0;
                dest[destOffset + 14] = -(_t36 * _t35 * _rcp0);
            }
        }
        dest[destOffset + 0] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -((Math.fma(_xx, _t45, Math.fma(_xy, _t46, _xz * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv);
        dest[destOffset + 9] = -((Math.fma(_yx, _t64, Math.fma(_yy, _t65, _yz * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv);
        dest[destOffset + 11] = 1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterRectangleProj_no_rh(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] p, int pOffset, float[] x, int xOffset, float[] y, int yOffset, float nearFarDist) {
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _px = p[pOffset + 0];
        float _py = p[pOffset + 1];
        float _pz = p[pOffset + 2];
        float _xx = x[xOffset + 0];
        float _xy = x[xOffset + 1];
        float _xz = x[xOffset + 2];
        float _yx = y[yOffset + 0];
        float _yy = y[yOffset + 1];
        float _yz = y[yOffset + 2];
        float _t10 = Math.fma(_xy, _yx, -(_xx * _yy));
        float _t11 = Math.fma(_xz, _yy, -(_xy * _yz));
        float _t12 = Math.fma(_xx, _yz, -(_xz * _yx));
        float _t15 = Math.fma(_pz - _eyez, _t10, Math.fma(_px - _eyex, _t11, (_py - _eyey) * _t12));
        float _t16 = _t15 >= 0.0f ? 1.0f : -1.0f;
        float _t17 = _t10 * _t16;
        float _t18 = _t12 * _t16;
        float _t19 = _t11 * _t16;
        float _t20 = _t15 * _t16;
        float _t24 = (1.0f / (float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        return makePerspectiveOffCenterRectangleProj_no_rh_s6d08da07_1(dest, destOffset, nearFarDist, _eyex, _eyey, _px, _py, _pz, _xx, _xy, _xz, _yx, _yy, _yz, 1.0f / nearFarDist, -_eyez, _t20, _t24, _t19 * _t24, _t18 * _t24, _t17 * _t24, _t20 * _t24);
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_no_rh_s6d08da07_1(float[] dest, int destOffset, float nearFarDist, float _eyex, float _eyey, float _px, float _py, float _pz, float _xx, float _xy, float _xz, float _yx, float _yy, float _yz, float _rcp0, float _t0, float _t20, float _t24, float _t25, float _t26, float _t27, float _t34) {
        float _t37 = Math.fma(_yy, _t25, -(_yx * _t26));
        float _t38 = Math.fma(_yx, _t27, -(_yz * _t25));
        float _t39 = Math.fma(_yz, _t26, -(_yy * _t27));
        float _t43 = (1.0f / (float) Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39))));
        float _t44 = _t37 * _t43;
        float _t45 = _t39 * _t43;
        float _t46 = _t38 * _t43;
        float _t63 = Math.fma(_t45, _t26, -(_t46 * _t25));
        float _t64 = Math.fma(_t46, _t27, -(_t44 * _t26));
        float _t65 = Math.fma(_t44, _t25, -(_t45 * _t27));
        return makePerspectiveOffCenterRectangleProj_no_rh_s6d08da07_2(dest, destOffset, _xx, _xy, _xz, _yx, _yy, _yz, _rcp0, _t20, _t24, _t34, Math.fma(_t20, _t24, nearFarDist), _t34 + _t34, _t44, _t45, _t46, Math.fma(_px, _t45, _py * _t46), Math.fma(_pz, _t44, -(_eyex * _t45)), Math.fma(_t0, _t44, -(_eyey * _t46)), _t63, _t64, _t65, 1.0f / Math.fma(_xz, _t44, Math.fma(_xx, _t45, _xy * _t46)), Math.fma(_px, _t64, _py * _t65), Math.fma(_pz, _t63, -(_eyex * _t64)), Math.fma(_t0, _t63, -(_eyey * _t65)), 1.0f / Math.fma(_yz, _t63, Math.fma(_yx, _t64, _yy * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_no_rh_s6d08da07_2(float[] dest, int destOffset, float _xx, float _xy, float _xz, float _yx, float _yy, float _yz, float _rcp0, float _t20, float _t24, float _t34, float _t35, float _t36, float _t44, float _t45, float _t46, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t72_inv, float _t74, float _t75, float _t76, float _t77_inv) {
        if (_t35 == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -_t36;
        } else {
            if (_t34 == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0f;
                dest[destOffset + 14] = _t35 + _t35;
            } else {
                dest[destOffset + 10] = -(Math.fma(_t20, _t24, _t35) * _rcp0);
                dest[destOffset + 14] = -(_t36 * _t35 * _rcp0);
            }
        }
        dest[destOffset + 0] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = (Math.fma(_xx, _t45, Math.fma(_xy, _t46, _xz * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv;
        dest[destOffset + 9] = (Math.fma(_yx, _t64, Math.fma(_yy, _t65, _yz * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv;
        dest[destOffset + 11] = -1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterRectangleProj_no(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] p, int pOffset, float[] x, int xOffset, float[] y, int yOffset, float nearFarDist, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_no_lh(dest, destOffset, eye, eyeOffset, p, pOffset, x, xOffset, y, yOffset, nearFarDist); }
            default -> { return Float4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_no_rh(dest, destOffset, eye, eyeOffset, p, pOffset, x, xOffset, y, yOffset, nearFarDist); }
        }
    }

    public static float[] makePerspectiveOffCenterRectangleProj_zo_lh(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] p, int pOffset, float[] x, int xOffset, float[] y, int yOffset, float nearFarDist) {
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _px = p[pOffset + 0];
        float _py = p[pOffset + 1];
        float _pz = p[pOffset + 2];
        float _xx = x[xOffset + 0];
        float _xy = x[xOffset + 1];
        float _xz = x[xOffset + 2];
        float _yx = y[yOffset + 0];
        float _yy = y[yOffset + 1];
        float _yz = y[yOffset + 2];
        float _t10 = Math.fma(_xy, _yx, -(_xx * _yy));
        float _t11 = Math.fma(_xz, _yy, -(_xy * _yz));
        float _t12 = Math.fma(_xx, _yz, -(_xz * _yx));
        float _t15 = Math.fma(_pz - _eyez, _t10, Math.fma(_px - _eyex, _t11, (_py - _eyey) * _t12));
        float _t16 = _t15 >= 0.0f ? 1.0f : -1.0f;
        float _t17 = _t10 * _t16;
        float _t18 = _t12 * _t16;
        float _t19 = _t11 * _t16;
        float _t20 = _t15 * _t16;
        float _t24 = (1.0f / (float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        return makePerspectiveOffCenterRectangleProj_zo_lh_s8dd9057d_1(dest, destOffset, nearFarDist, _eyex, _eyey, _px, _py, _pz, _xx, _xy, _xz, _yx, _yy, _yz, -_eyez, _t20, _t24, _t18 * _t24, _t19 * _t24, _t17 * _t24, _t20 * _t24);
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_zo_lh_s8dd9057d_1(float[] dest, int destOffset, float nearFarDist, float _eyex, float _eyey, float _px, float _py, float _pz, float _xx, float _xy, float _xz, float _yx, float _yy, float _yz, float _t0, float _t20, float _t24, float _t25, float _t26, float _t27, float _t34) {
        float _t35 = Math.fma(_t20, _t24, nearFarDist);
        float _t37 = Math.fma(_yx, _t25, -(_yy * _t26));
        float _t38 = Math.fma(_yy, _t27, -(_yz * _t25));
        float _t39 = Math.fma(_yz, _t26, -(_yx * _t27));
        float _t43 = (1.0f / (float) Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39))));
        float _t44 = _t37 * _t43;
        float _t45 = _t38 * _t43;
        float _t46 = _t39 * _t43;
        float _t63 = Math.fma(_t46, _t26, -(_t45 * _t25));
        float _t64 = Math.fma(_t44, _t25, -(_t46 * _t27));
        float _t65 = Math.fma(_t45, _t27, -(_t44 * _t26));
        return makePerspectiveOffCenterRectangleProj_zo_lh_s8dd9057d_2(dest, destOffset, _xx, _xy, _xz, _yx, _yy, _yz, _t34, _t35, _t35 / nearFarDist, _t34 + _t34, _t44, _t45, _t46, Math.fma(_px, _t45, _py * _t46), Math.fma(_pz, _t44, -(_eyex * _t45)), Math.fma(_t0, _t44, -(_eyey * _t46)), _t63, _t64, _t65, 1.0f / Math.fma(_xz, _t44, Math.fma(_xx, _t45, _xy * _t46)), Math.fma(_px, _t64, _py * _t65), Math.fma(_pz, _t63, -(_eyex * _t64)), Math.fma(_t0, _t63, -(_eyey * _t65)), 1.0f / Math.fma(_yz, _t63, Math.fma(_yx, _t64, _yy * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_zo_lh_s8dd9057d_2(float[] dest, int destOffset, float _xx, float _xy, float _xz, float _yx, float _yy, float _yz, float _t34, float _t35, float _sp0, float _t36, float _t44, float _t45, float _t46, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t72_inv, float _t74, float _t75, float _t76, float _t77_inv) {
        if (_t35 == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -_t34;
        } else {
            if (_t34 == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = _t35;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = -(_sp0 * _t34);
            }
        }
        dest[destOffset + 0] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = -((Math.fma(_xx, _t45, Math.fma(_xy, _t46, _xz * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv);
        dest[destOffset + 9] = -((Math.fma(_yx, _t64, Math.fma(_yy, _t65, _yz * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv);
        dest[destOffset + 11] = 1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterRectangleProj_zo_rh(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] p, int pOffset, float[] x, int xOffset, float[] y, int yOffset, float nearFarDist) {
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _px = p[pOffset + 0];
        float _py = p[pOffset + 1];
        float _pz = p[pOffset + 2];
        float _xx = x[xOffset + 0];
        float _xy = x[xOffset + 1];
        float _xz = x[xOffset + 2];
        float _yx = y[yOffset + 0];
        float _yy = y[yOffset + 1];
        float _yz = y[yOffset + 2];
        float _t10 = Math.fma(_xy, _yx, -(_xx * _yy));
        float _t11 = Math.fma(_xz, _yy, -(_xy * _yz));
        float _t12 = Math.fma(_xx, _yz, -(_xz * _yx));
        float _t15 = Math.fma(_pz - _eyez, _t10, Math.fma(_px - _eyex, _t11, (_py - _eyey) * _t12));
        float _t16 = _t15 >= 0.0f ? 1.0f : -1.0f;
        float _t17 = _t10 * _t16;
        float _t18 = _t12 * _t16;
        float _t19 = _t11 * _t16;
        float _t20 = _t15 * _t16;
        float _t24 = (1.0f / (float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, _t19 * _t19))));
        return makePerspectiveOffCenterRectangleProj_zo_rh_s23c59c23_1(dest, destOffset, nearFarDist, _eyex, _eyey, _px, _py, _pz, _xx, _xy, _xz, _yx, _yy, _yz, -_eyez, _t20, _t24, _t19 * _t24, _t18 * _t24, _t17 * _t24, _t20 * _t24);
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleProj_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_zo_rh_s23c59c23_1(float[] dest, int destOffset, float nearFarDist, float _eyex, float _eyey, float _px, float _py, float _pz, float _xx, float _xy, float _xz, float _yx, float _yy, float _yz, float _t0, float _t20, float _t24, float _t25, float _t26, float _t27, float _t34) {
        float _t35 = Math.fma(_t20, _t24, nearFarDist);
        float _t37 = Math.fma(_yy, _t25, -(_yx * _t26));
        float _t38 = Math.fma(_yx, _t27, -(_yz * _t25));
        float _t39 = Math.fma(_yz, _t26, -(_yy * _t27));
        float _t43 = (1.0f / (float) Math.sqrt(Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39))));
        float _t44 = _t37 * _t43;
        float _t45 = _t39 * _t43;
        float _t46 = _t38 * _t43;
        float _t63 = Math.fma(_t45, _t26, -(_t46 * _t25));
        float _t64 = Math.fma(_t46, _t27, -(_t44 * _t26));
        float _t65 = Math.fma(_t44, _t25, -(_t45 * _t27));
        return makePerspectiveOffCenterRectangleProj_zo_rh_s23c59c23_2(dest, destOffset, _xx, _xy, _xz, _yx, _yy, _yz, _t34, _t35, _t35 / nearFarDist, _t34 + _t34, _t44, _t45, _t46, Math.fma(_px, _t45, _py * _t46), Math.fma(_pz, _t44, -(_eyex * _t45)), Math.fma(_t0, _t44, -(_eyey * _t46)), _t63, _t64, _t65, 1.0f / Math.fma(_xz, _t44, Math.fma(_xx, _t45, _xy * _t46)), Math.fma(_px, _t64, _py * _t65), Math.fma(_pz, _t63, -(_eyex * _t64)), Math.fma(_t0, _t63, -(_eyey * _t65)), 1.0f / Math.fma(_yz, _t63, Math.fma(_yx, _t64, _yy * _t65)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleProj_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleProj_zo_rh_s23c59c23_2(float[] dest, int destOffset, float _xx, float _xy, float _xz, float _yx, float _yy, float _yz, float _t34, float _t35, float _sp0, float _t36, float _t44, float _t45, float _t46, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t72_inv, float _t74, float _t75, float _t76, float _t77_inv) {
        if (_t35 == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -_t34;
        } else {
            if (_t34 == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = _t35;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = -(_sp0 * _t34);
            }
        }
        dest[destOffset + 0] = _t36 * _t72_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t36 * _t77_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = (Math.fma(_xx, _t45, Math.fma(_xy, _t46, _xz * _t44)) + (_t60 + _t61) + (_t62 + _t60 + (_t61 + _t62))) * _t72_inv;
        dest[destOffset + 9] = (Math.fma(_yx, _t64, Math.fma(_yy, _t65, _yz * _t63)) + (_t74 + _t75) + (_t76 + _t74 + (_t75 + _t76))) * _t77_inv;
        dest[destOffset + 11] = -1.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 15] = 0.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterRectangleProj_zo(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] p, int pOffset, float[] x, int xOffset, float[] y, int yOffset, float nearFarDist, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_zo_lh(dest, destOffset, eye, eyeOffset, p, pOffset, x, xOffset, y, yOffset, nearFarDist); }
            default -> { return Float4x4OpsKernelsArray.makePerspectiveOffCenterRectangleProj_zo_rh(dest, destOffset, eye, eyeOffset, p, pOffset, x, xOffset, y, yOffset, nearFarDist); }
        }
    }

    public static float[] makePerspectiveOffCenterRectangleView_lh(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float pX, float pY, float pZ, float xX, float xY, float xZ, float yX, float yY, float yZ) {
        float _t11 = Math.fma(xY, yX, -(xX * yY));
        float _t12 = Math.fma(xZ, yY, -(xY * yZ));
        float _t13 = Math.fma(xX, yZ, -(xZ * yX));
        float _t19 = Math.fma(pZ - eyeZ, _t11, Math.fma(pX - eyeX, _t12, (pY - eyeY) * _t13)) >= 0.0f ? 1.0f : -1.0f;
        float _t20 = _t11 * _t19;
        float _t21 = _t13 * _t19;
        float _t22 = _t12 * _t19;
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        float _t30, _t31, _t32;
        if (_t25 != 0.0f) {
            _t30 = _t20 * _t26;
            _t31 = _t22 * _t26;
            _t32 = _t21 * _t26;
        } else {
            _t30 = 0.0f;
            _t31 = 0.0f;
            _t32 = 0.0f;
        }
        float _t36 = -Math.fma(yZ, _t30, Math.fma(yX, _t31, yY * _t32));
        float _t37 = Math.fma(_t36, _t31, yX);
        float _t38 = Math.fma(_t36, _t32, yY);
        float _t39 = Math.fma(_t36, _t30, yZ);
        return makePerspectiveOffCenterRectangleView_lh_sa4aa0aac_1(dest, destOffset, eyeX, eyeY, eyeZ, yX, yY, yZ, _t30, _t31, _t32, Math.fma(_t37, _t32, -(_t38 * _t31)), Math.fma(_t38, _t30, -(_t39 * _t32)), Math.fma(_t39, _t31, -(_t37 * _t30)));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleView_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleView_lh_sa4aa0aac_1(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float yX, float yY, float yZ, float _t30, float _t31, float _t32, float _t46, float _t47, float _t48) {
        float _t51 = Math.fma(_t46, _t46, Math.fma(_t47, _t47, _t48 * _t48));
        float _t52 = (1.0f / (float) Math.sqrt(_t51));
        float _t56, _t57, _t58;
        if (_t51 > Math.fma(Math.fma(yZ, yZ, Math.fma(yX, yX, yY * yY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t56 = _t47 * _t52;
            _t57 = _t48 * _t52;
            _t58 = _t46 * _t52;
        } else {
            _t56 = 0.0f;
            _t57 = 0.0f;
            _t58 = 0.0f;
        }
        float _t65 = Math.fma(_t58, _t32, -(_t57 * _t30));
        float _t66 = Math.fma(_t56, _t30, -(_t58 * _t31));
        float _t67 = Math.fma(_t57, _t31, -(_t56 * _t32));
        dest[destOffset + 0] = _t56;
        dest[destOffset + 1] = _t65;
        dest[destOffset + 2] = _t31;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = _t57;
        dest[destOffset + 5] = _t66;
        dest[destOffset + 6] = _t32;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t58;
        dest[destOffset + 9] = _t67;
        dest[destOffset + 10] = _t30;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -Math.fma(eyeZ, _t58, Math.fma(eyeX, _t56, eyeY * _t57));
        return makePerspectiveOffCenterRectangleView_lh_sa4aa0aac_2(dest, destOffset, eyeX, eyeY, eyeZ, _t30, _t31, _t32, _t65, _t66, _t67);
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleView_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleView_lh_sa4aa0aac_2(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float _t30, float _t31, float _t32, float _t65, float _t66, float _t67) {
        dest[destOffset + 13] = -Math.fma(eyeZ, _t67, Math.fma(eyeX, _t65, eyeY * _t66));
        dest[destOffset + 14] = -Math.fma(eyeZ, _t30, Math.fma(eyeX, _t31, eyeY * _t32));
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterRectangleView_rh(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float pX, float pY, float pZ, float xX, float xY, float xZ, float yX, float yY, float yZ) {
        float _t11 = Math.fma(xY, yX, -(xX * yY));
        float _t12 = Math.fma(xZ, yY, -(xY * yZ));
        float _t13 = Math.fma(xX, yZ, -(xZ * yX));
        float _t19 = Math.fma(pZ - eyeZ, _t11, Math.fma(pX - eyeX, _t12, (pY - eyeY) * _t13)) >= 0.0f ? 1.0f : -1.0f;
        float _t20 = _t11 * _t19;
        float _t21 = _t13 * _t19;
        float _t22 = _t12 * _t19;
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (1.0f / (float) Math.sqrt(_t25));
        float _t30, _t31, _t32;
        if (_t25 != 0.0f) {
            _t30 = _t20 * _t26;
            _t31 = _t22 * _t26;
            _t32 = _t21 * _t26;
        } else {
            _t30 = 0.0f;
            _t31 = 0.0f;
            _t32 = 0.0f;
        }
        float _t36 = -Math.fma(yZ, _t30, Math.fma(yX, _t31, yY * _t32));
        float _t37 = Math.fma(_t36, _t32, yY);
        float _t38 = Math.fma(_t36, _t31, yX);
        float _t39 = Math.fma(_t36, _t30, yZ);
        return makePerspectiveOffCenterRectangleView_rh_s928365e_1(dest, destOffset, eyeX, eyeY, eyeZ, yX, yY, yZ, _t30, _t31, _t32, Math.fma(_t37, _t31, -(_t38 * _t32)), Math.fma(_t38, _t30, -(_t39 * _t31)), Math.fma(_t39, _t32, -(_t37 * _t30)));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleView_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleView_rh_s928365e_1(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float yX, float yY, float yZ, float _t30, float _t31, float _t32, float _t46, float _t47, float _t48) {
        float _t51 = Math.fma(_t46, _t46, Math.fma(_t47, _t47, _t48 * _t48));
        float _t52 = (1.0f / (float) Math.sqrt(_t51));
        float _t56, _t57, _t58;
        if (_t51 > Math.fma(Math.fma(yZ, yZ, Math.fma(yX, yX, yY * yY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t56 = _t48 * _t52;
            _t57 = _t47 * _t52;
            _t58 = _t46 * _t52;
        } else {
            _t56 = 0.0f;
            _t57 = 0.0f;
            _t58 = 0.0f;
        }
        float _t65 = Math.fma(_t57, _t30, -(_t58 * _t32));
        float _t66 = Math.fma(_t58, _t31, -(_t56 * _t30));
        float _t67 = Math.fma(_t56, _t32, -(_t57 * _t31));
        dest[destOffset + 0] = _t56;
        dest[destOffset + 1] = _t65;
        dest[destOffset + 2] = -_t31;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = _t57;
        dest[destOffset + 5] = _t66;
        dest[destOffset + 6] = -_t32;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t58;
        dest[destOffset + 9] = _t67;
        dest[destOffset + 10] = -_t30;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -Math.fma(eyeZ, _t58, Math.fma(eyeX, _t56, eyeY * _t57));
        return makePerspectiveOffCenterRectangleView_rh_s928365e_2(dest, destOffset, eyeX, eyeY, eyeZ, _t30, _t31, _t32, _t65, _t66, _t67);
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleView_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleView_rh_s928365e_2(float[] dest, int destOffset, float eyeX, float eyeY, float eyeZ, float _t30, float _t31, float _t32, float _t65, float _t66, float _t67) {
        dest[destOffset + 13] = -Math.fma(eyeZ, _t67, Math.fma(eyeX, _t65, eyeY * _t66));
        dest[destOffset + 14] = Math.fma(eyeZ, _t30, Math.fma(eyeX, _t31, eyeY * _t32));
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterRectangleView_lh(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] p, int pOffset, float[] x, int xOffset, float[] y, int yOffset) {
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _px = p[pOffset + 0];
        float _py = p[pOffset + 1];
        float _pz = p[pOffset + 2];
        float _xx = x[xOffset + 0];
        float _xy = x[xOffset + 1];
        float _xz = x[xOffset + 2];
        float _yx = y[yOffset + 0];
        float _yy = y[yOffset + 1];
        float _yz = y[yOffset + 2];
        float _t11 = Math.fma(_xy, _yx, -(_xx * _yy));
        float _t12 = Math.fma(_xz, _yy, -(_xy * _yz));
        float _t13 = Math.fma(_xx, _yz, -(_xz * _yx));
        float _t19 = Math.fma(_pz - _eyez, _t11, Math.fma(_px - _eyex, _t12, (_py - _eyey) * _t13)) >= 0.0f ? 1.0f : -1.0f;
        float _t20 = _t11 * _t19;
        float _t21 = _t13 * _t19;
        float _t22 = _t12 * _t19;
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        return makePerspectiveOffCenterRectangleView_lh_sc710429e_1(dest, destOffset, _eyex, _eyey, _eyez, _yx, _yy, _yz, _t20, _t21, _t22, _t25, (1.0f / (float) Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleView_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleView_lh_sc710429e_1(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _yx, float _yy, float _yz, float _t20, float _t21, float _t22, float _t25, float _t26) {
        float _t30, _t31, _t32;
        if (_t25 != 0.0f) {
            _t30 = _t20 * _t26;
            _t31 = _t22 * _t26;
            _t32 = _t21 * _t26;
        } else {
            _t30 = 0.0f;
            _t31 = 0.0f;
            _t32 = 0.0f;
        }
        float _t36 = -Math.fma(_yz, _t30, Math.fma(_yx, _t31, _yy * _t32));
        float _t37 = Math.fma(_t36, _t31, _yx);
        float _t38 = Math.fma(_t36, _t32, _yy);
        float _t39 = Math.fma(_t36, _t30, _yz);
        float _t46 = Math.fma(_t37, _t32, -(_t38 * _t31));
        float _t47 = Math.fma(_t38, _t30, -(_t39 * _t32));
        float _t48 = Math.fma(_t39, _t31, -(_t37 * _t30));
        float _t51 = Math.fma(_t46, _t46, Math.fma(_t47, _t47, _t48 * _t48));
        float _t52 = (1.0f / (float) Math.sqrt(_t51));
        float _t56, _t57, _t58;
        if (_t51 > Math.fma(Math.fma(_yz, _yz, Math.fma(_yx, _yx, _yy * _yy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t56 = _t47 * _t52;
            _t57 = _t48 * _t52;
            _t58 = _t46 * _t52;
        } else {
            _t56 = 0.0f;
            _t57 = 0.0f;
            _t58 = 0.0f;
        }
        return makePerspectiveOffCenterRectangleView_lh_sc710429e_2(dest, destOffset, _eyex, _eyey, _eyez, _t30, _t31, _t32, _t56, _t57, _t58, Math.fma(_t58, _t32, -(_t57 * _t30)), Math.fma(_t56, _t30, -(_t58 * _t31)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleView_lh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleView_lh_sc710429e_2(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _t30, float _t31, float _t32, float _t56, float _t57, float _t58, float _t65, float _t66) {
        float _t67 = Math.fma(_t57, _t31, -(_t56 * _t32));
        dest[destOffset + 0] = _t56;
        dest[destOffset + 1] = _t65;
        dest[destOffset + 2] = _t31;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = _t57;
        dest[destOffset + 5] = _t66;
        dest[destOffset + 6] = _t32;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t58;
        dest[destOffset + 9] = _t67;
        dest[destOffset + 10] = _t30;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -Math.fma(_eyez, _t58, Math.fma(_eyex, _t56, _eyey * _t57));
        dest[destOffset + 13] = -Math.fma(_eyez, _t67, Math.fma(_eyex, _t65, _eyey * _t66));
        dest[destOffset + 14] = -Math.fma(_eyez, _t30, Math.fma(_eyex, _t31, _eyey * _t32));
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] makePerspectiveOffCenterRectangleView_rh(float[] dest, int destOffset, float[] eye, int eyeOffset, float[] p, int pOffset, float[] x, int xOffset, float[] y, int yOffset) {
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _eyez = eye[eyeOffset + 2];
        float _px = p[pOffset + 0];
        float _py = p[pOffset + 1];
        float _pz = p[pOffset + 2];
        float _xx = x[xOffset + 0];
        float _xy = x[xOffset + 1];
        float _xz = x[xOffset + 2];
        float _yx = y[yOffset + 0];
        float _yy = y[yOffset + 1];
        float _yz = y[yOffset + 2];
        float _t11 = Math.fma(_xy, _yx, -(_xx * _yy));
        float _t12 = Math.fma(_xz, _yy, -(_xy * _yz));
        float _t13 = Math.fma(_xx, _yz, -(_xz * _yx));
        float _t19 = Math.fma(_pz - _eyez, _t11, Math.fma(_px - _eyex, _t12, (_py - _eyey) * _t13)) >= 0.0f ? 1.0f : -1.0f;
        float _t20 = _t11 * _t19;
        float _t21 = _t13 * _t19;
        float _t22 = _t12 * _t19;
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        return makePerspectiveOffCenterRectangleView_rh_s3e763658_1(dest, destOffset, _eyex, _eyey, _eyez, _yx, _yy, _yz, _t20, _t21, _t22, _t25, (1.0f / (float) Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code makePerspectiveOffCenterRectangleView_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleView_rh_s3e763658_1(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _yx, float _yy, float _yz, float _t20, float _t21, float _t22, float _t25, float _t26) {
        float _t30, _t31, _t32;
        if (_t25 != 0.0f) {
            _t30 = _t20 * _t26;
            _t31 = _t22 * _t26;
            _t32 = _t21 * _t26;
        } else {
            _t30 = 0.0f;
            _t31 = 0.0f;
            _t32 = 0.0f;
        }
        float _t36 = -Math.fma(_yz, _t30, Math.fma(_yx, _t31, _yy * _t32));
        float _t37 = Math.fma(_t36, _t32, _yy);
        float _t38 = Math.fma(_t36, _t31, _yx);
        float _t39 = Math.fma(_t36, _t30, _yz);
        float _t46 = Math.fma(_t37, _t31, -(_t38 * _t32));
        float _t47 = Math.fma(_t38, _t30, -(_t39 * _t31));
        float _t48 = Math.fma(_t39, _t32, -(_t37 * _t30));
        float _t51 = Math.fma(_t46, _t46, Math.fma(_t47, _t47, _t48 * _t48));
        float _t52 = (1.0f / (float) Math.sqrt(_t51));
        float _t56, _t57, _t58;
        if (_t51 > Math.fma(Math.fma(_yz, _yz, Math.fma(_yx, _yx, _yy * _yy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t56 = _t48 * _t52;
            _t57 = _t47 * _t52;
            _t58 = _t46 * _t52;
        } else {
            _t56 = 0.0f;
            _t57 = 0.0f;
            _t58 = 0.0f;
        }
        return makePerspectiveOffCenterRectangleView_rh_s3e763658_2(dest, destOffset, _eyex, _eyey, _eyez, _t30, _t31, _t32, _t56, _t57, _t58, Math.fma(_t57, _t30, -(_t58 * _t32)), Math.fma(_t58, _t31, -(_t56 * _t30)));
    }

    /** Piece 3 of {@code makePerspectiveOffCenterRectangleView_rh}, split to fit the inline budget; reached only through it. */
    private static float[] makePerspectiveOffCenterRectangleView_rh_s3e763658_2(float[] dest, int destOffset, float _eyex, float _eyey, float _eyez, float _t30, float _t31, float _t32, float _t56, float _t57, float _t58, float _t65, float _t66) {
        float _t67 = Math.fma(_t56, _t32, -(_t57 * _t31));
        dest[destOffset + 0] = _t56;
        dest[destOffset + 1] = _t65;
        dest[destOffset + 2] = -_t31;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = _t57;
        dest[destOffset + 5] = _t66;
        dest[destOffset + 6] = -_t32;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = _t58;
        dest[destOffset + 9] = _t67;
        dest[destOffset + 10] = -_t30;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -Math.fma(_eyez, _t58, Math.fma(_eyex, _t56, _eyey * _t57));
        dest[destOffset + 13] = -Math.fma(_eyez, _t67, Math.fma(_eyex, _t65, _eyey * _t66));
        dest[destOffset + 14] = Math.fma(_eyez, _t30, Math.fma(_eyex, _t31, _eyey * _t32));
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] obliqueZ_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _sp0 = (_self23 + _self23) / Math.fma(planeW, 1.0f - _self22, _self23 * (planeZ + (planeX * ((planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f) - _self02) / _self00 + planeY * ((planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f) - _self12) / _self11)));
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = planeX * _sp0 - _self30;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = planeY * _sp0 - _self31;
        dest[destOffset + 7] = _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        return obliqueZ_no_lh_s94f099f1_1(dest, destOffset, planeZ, planeW, _self32, _self03, _self13, _self33, _sp0);
    }

    /** Piece 2 of {@code obliqueZ_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] obliqueZ_no_lh_s94f099f1_1(float[] dest, int destOffset, float planeZ, float planeW, float _self32, float _self03, float _self13, float _self33, float _sp0) {
        dest[destOffset + 10] = planeZ * _sp0 - _self32;
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 14] = planeW * _sp0 - _self33;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static float[] obliqueZ_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _sp0 = (_self23 + _self23) / Math.fma(planeW, 1.0f + _self22, _self23 * (planeX * (_self02 + (planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f)) / _self00 + planeY * (_self12 + (planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f)) / _self11 - planeZ));
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = planeX * _sp0 - _self30;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = planeY * _sp0 - _self31;
        dest[destOffset + 7] = _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        return obliqueZ_no_rh_s32a08b93_1(dest, destOffset, planeZ, planeW, _self32, _self03, _self13, _self33, _sp0);
    }

    /** Piece 2 of {@code obliqueZ_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] obliqueZ_no_rh_s32a08b93_1(float[] dest, int destOffset, float planeZ, float planeW, float _self32, float _self03, float _self13, float _self33, float _sp0) {
        dest[destOffset + 10] = planeZ * _sp0 - _self32;
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 14] = planeW * _sp0 - _self33;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static float[] obliqueZ_no(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.obliqueZ_no_lh(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW); }
            default -> { return Float4x4OpsKernelsArray.obliqueZ_no_rh(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW); }
        }
    }

    public static float[] obliqueZ_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _sp0 = _self23 / Math.fma(planeW, 1.0f - _self22, _self23 * (planeZ + (planeX * ((planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f) - _self02) / _self00 + planeY * ((planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f) - _self12) / _self11)));
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = planeX * _sp0;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = planeY * _sp0;
        dest[destOffset + 7] = _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        return obliqueZ_zo_lh_s7f1176ad_1(dest, destOffset, planeZ, planeW, _self32, _self03, _self13, _self33, _sp0);
    }

    /** Piece 2 of {@code obliqueZ_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] obliqueZ_zo_lh_s7f1176ad_1(float[] dest, int destOffset, float planeZ, float planeW, float _self32, float _self03, float _self13, float _self33, float _sp0) {
        dest[destOffset + 10] = planeZ * _sp0;
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 14] = planeW * _sp0;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static float[] obliqueZ_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _sp0 = _self23 / Math.fma(planeW, 1.0f + _self22, _self23 * (planeX * (_self02 + (planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f)) / _self00 + planeY * (_self12 + (planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f)) / _self11 - planeZ));
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = planeX * _sp0;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = planeY * _sp0;
        dest[destOffset + 7] = _self31;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        return obliqueZ_zo_rh_s33d7d977_1(dest, destOffset, planeZ, planeW, _self32, _self03, _self13, _self33, _sp0);
    }

    /** Piece 2 of {@code obliqueZ_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] obliqueZ_zo_rh_s33d7d977_1(float[] dest, int destOffset, float planeZ, float planeW, float _self32, float _self03, float _self13, float _self33, float _sp0) {
        dest[destOffset + 10] = planeZ * _sp0;
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03;
        dest[destOffset + 13] = _self13;
        dest[destOffset + 14] = planeW * _sp0;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static float[] obliqueZ_zo(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.obliqueZ_zo_lh(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW); }
            default -> { return Float4x4OpsKernelsArray.obliqueZ_zo_rh(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW); }
        }
    }

    public static float[] obliqueZ_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _planex = plane[planeOffset + 0];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _sp0 = (_self23 + _self23) / Math.fma(_planew, 1.0f - _self22, _self23 * (_planez + (_planex * ((_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f) - _self02) / _self00 + _planey * ((_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f) - _self12) / _self11)));
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _planex * _sp0 - _self30;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        return obliqueZ_no_lh_s22940b01_1(dest, destOffset, _self11, _self31, _self02, _self12, _self32, _self03, _self13, _self33, _planey, _planez, _planew, _sp0);
    }

    /** Piece 2 of {@code obliqueZ_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] obliqueZ_no_lh_s22940b01_1(float[] dest, int destOffset, float _self11, float _self31, float _self02, float _self12, float _self32, float _self03, float _self13, float _self33, float _planey, float _planez, float _planew, float _sp0) {
        dest[destOffset + 5] = _self11;
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

    public static float[] obliqueZ_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _planex = plane[planeOffset + 0];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _sp0 = (_self23 + _self23) / Math.fma(_planew, 1.0f + _self22, _self23 * (_planex * (_self02 + (_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f)) / _self00 + _planey * (_self12 + (_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f)) / _self11 - _planez));
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _planex * _sp0 - _self30;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        return obliqueZ_no_rh_s93a26e8b_1(dest, destOffset, _self11, _self31, _self02, _self12, _self32, _self03, _self13, _self33, _planey, _planez, _planew, _sp0);
    }

    /** Piece 2 of {@code obliqueZ_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] obliqueZ_no_rh_s93a26e8b_1(float[] dest, int destOffset, float _self11, float _self31, float _self02, float _self12, float _self32, float _self03, float _self13, float _self33, float _planey, float _planez, float _planew, float _sp0) {
        dest[destOffset + 5] = _self11;
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

    public static float[] obliqueZ_no(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.obliqueZ_no_lh(dest, destOffset, src, srcOffset, plane, planeOffset); }
            default -> { return Float4x4OpsKernelsArray.obliqueZ_no_rh(dest, destOffset, src, srcOffset, plane, planeOffset); }
        }
    }

    public static float[] obliqueZ_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _planex = plane[planeOffset + 0];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _sp0 = _self23 / Math.fma(_planew, 1.0f - _self22, _self23 * (_planez + (_planex * ((_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f) - _self02) / _self00 + _planey * ((_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f) - _self12) / _self11)));
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _planex * _sp0;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        return obliqueZ_zo_lh_sec5e2ee5_1(dest, destOffset, _self31, _self02, _self12, _self32, _self03, _self13, _self33, _planey, _planez, _planew, _sp0);
    }

    /** Piece 2 of {@code obliqueZ_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] obliqueZ_zo_lh_sec5e2ee5_1(float[] dest, int destOffset, float _self31, float _self02, float _self12, float _self32, float _self03, float _self13, float _self33, float _planey, float _planez, float _planew, float _sp0) {
        dest[destOffset + 6] = _planey * _sp0;
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

    public static float[] obliqueZ_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _planex = plane[planeOffset + 0];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _sp0 = _self23 / Math.fma(_planew, 1.0f + _self22, _self23 * (_planex * (_self02 + (_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f)) / _self00 + _planey * (_self12 + (_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f)) / _self11 - _planez));
        dest[destOffset + 0] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _planex * _sp0;
        dest[destOffset + 3] = _self30;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        return obliqueZ_zo_rh_sc8021f17_1(dest, destOffset, _self31, _self02, _self12, _self32, _self03, _self13, _self33, _planey, _planez, _planew, _sp0);
    }

    /** Piece 2 of {@code obliqueZ_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] obliqueZ_zo_rh_sc8021f17_1(float[] dest, int destOffset, float _self31, float _self02, float _self12, float _self32, float _self03, float _self13, float _self33, float _planey, float _planez, float _planew, float _sp0) {
        dest[destOffset + 6] = _planey * _sp0;
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

    public static float[] obliqueZ_zo(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.obliqueZ_zo_lh(dest, destOffset, src, srcOffset, plane, planeOffset); }
            default -> { return Float4x4OpsKernelsArray.obliqueZ_zo_rh(dest, destOffset, src, srcOffset, plane, planeOffset); }
        }
    }

    public static float[] ortho_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _t2_inv = 1.0f / (zFar - zNear);
        dest[destOffset + 0] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        return ortho_no_lh_s710b3e00_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _sp1, _t2_inv + _t2_inv, _t0_inv * (left + right), _t1_inv * (bottom + top), _t2_inv * (zFar + zNear));
    }

    /** Piece 2 of {@code ortho_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] ortho_no_lh_s710b3e00_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp1, float _sp2, float _sp3, float _sp4, float _sp5) {
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

    public static float[] ortho_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _t2_inv = 1.0f / (zFar - zNear);
        dest[destOffset + 0] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        return ortho_no_rh_s11f864ae_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _sp1, -2.0f * _t2_inv, _t0_inv * (left + right), _t1_inv * (bottom + top), _t2_inv * (zFar + zNear));
    }

    /** Piece 2 of {@code ortho_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] ortho_no_rh_s11f864ae_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp1, float _sp2, float _sp3, float _sp4, float _sp5) {
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

    public static float[] ortho_no(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.ortho_no_lh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Float4x4OpsKernelsArray.ortho_no_rh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static float[] ortho_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _t2_inv = 1.0f / (zFar - zNear);
        dest[destOffset + 0] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        return ortho_zo_lh_sd61f3cfc_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t2_inv, _t2_inv * zNear, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code ortho_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] ortho_zo_lh_sd61f3cfc_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t2_inv, float _sp4, float _sp2, float _sp3) {
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

    public static float[] ortho_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _t2_inv = 1.0f / (zFar - zNear);
        dest[destOffset + 0] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        return ortho_zo_rh_s9f8ffc82_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t2_inv, _t2_inv * zNear, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code ortho_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] ortho_zo_rh_s9f8ffc82_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t2_inv, float _sp4, float _sp2, float _sp3) {
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

    public static float[] ortho_zo(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.ortho_zo_lh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
            default -> { return Float4x4OpsKernelsArray.ortho_zo_rh(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar); }
        }
    }

    public static float[] ortho2D_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        dest[destOffset + 0] = _sp0 * _self00;
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
        return ortho2D_no_lh_s108fdd8b_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self32, _self03, _self13, _self23, _self33, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code ortho2D_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] ortho2D_no_lh_s108fdd8b_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp2, float _sp3) {
        dest[destOffset + 11] = _self32;
        dest[destOffset + 12] = _self03 + (-(_self00 * _sp2) - _self01 * _sp3);
        dest[destOffset + 13] = _self13 + (-(_self10 * _sp2) - _self11 * _sp3);
        dest[destOffset + 14] = _self23 + (-(_self20 * _sp2) - _self21 * _sp3);
        dest[destOffset + 15] = _self33 + (-(_self30 * _sp2) - _self31 * _sp3);
        return dest;
    }

    public static float[] ortho2D_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        dest[destOffset + 0] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = -_self02;
        dest[destOffset + 9] = -_self12;
        return ortho2D_no_rh_s759435e1_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self22, _self32, _self03, _self13, _self23, _self33, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code ortho2D_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] ortho2D_no_rh_s759435e1_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp2, float _sp3) {
        dest[destOffset + 10] = -_self22;
        dest[destOffset + 11] = -_self32;
        dest[destOffset + 12] = _self03 + (-(_self00 * _sp2) - _self01 * _sp3);
        dest[destOffset + 13] = _self13 + (-(_self10 * _sp2) - _self11 * _sp3);
        dest[destOffset + 14] = _self23 + (-(_self20 * _sp2) - _self21 * _sp3);
        dest[destOffset + 15] = _self33 + (-(_self30 * _sp2) - _self31 * _sp3);
        return dest;
    }

    public static float[] ortho2D_no(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.ortho2D_no_lh(dest, destOffset, src, srcOffset, left, right, bottom, top); }
            default -> { return Float4x4OpsKernelsArray.ortho2D_no_rh(dest, destOffset, src, srcOffset, left, right, bottom, top); }
        }
    }

    public static float[] ortho2D_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        dest[destOffset + 0] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = 0.5f * _self02;
        return ortho2D_zo_lh_s53adda57_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code ortho2D_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] ortho2D_zo_lh_s53adda57_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp2, float _sp3) {
        dest[destOffset + 9] = 0.5f * _self12;
        dest[destOffset + 10] = 0.5f * _self22;
        dest[destOffset + 11] = 0.5f * _self32;
        dest[destOffset + 12] = Math.fma(0.5f, _self02, _self03 - _self00 * _sp2 - _self01 * _sp3);
        dest[destOffset + 13] = Math.fma(0.5f, _self12, _self13 - _self10 * _sp2 - _self11 * _sp3);
        dest[destOffset + 14] = Math.fma(0.5f, _self22, _self23 - _self20 * _sp2 - _self21 * _sp3);
        dest[destOffset + 15] = Math.fma(0.5f, _self32, _self33 - _self30 * _sp2 - _self31 * _sp3);
        return dest;
    }

    public static float[] ortho2D_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        dest[destOffset + 0] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp0 * _self30;
        dest[destOffset + 4] = _sp1 * _self01;
        dest[destOffset + 5] = _sp1 * _self11;
        dest[destOffset + 6] = _sp1 * _self21;
        dest[destOffset + 7] = _sp1 * _self31;
        dest[destOffset + 8] = -0.5f * _self02;
        return ortho2D_zo_rh_s846b4505_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t0_inv * (left + right), _t1_inv * (bottom + top));
    }

    /** Piece 2 of {@code ortho2D_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] ortho2D_zo_rh_s846b4505_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp2, float _sp3) {
        dest[destOffset + 9] = -0.5f * _self12;
        dest[destOffset + 10] = -0.5f * _self22;
        dest[destOffset + 11] = -0.5f * _self32;
        dest[destOffset + 12] = Math.fma(0.5f, _self02, _self03 - _self00 * _sp2 - _self01 * _sp3);
        dest[destOffset + 13] = Math.fma(0.5f, _self12, _self13 - _self10 * _sp2 - _self11 * _sp3);
        dest[destOffset + 14] = Math.fma(0.5f, _self22, _self23 - _self20 * _sp2 - _self21 * _sp3);
        dest[destOffset + 15] = Math.fma(0.5f, _self32, _self33 - _self30 * _sp2 - _self31 * _sp3);
        return dest;
    }

    public static float[] ortho2D_zo(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.ortho2D_zo_lh(dest, destOffset, src, srcOffset, left, right, bottom, top); }
            default -> { return Float4x4OpsKernelsArray.ortho2D_zo_rh(dest, destOffset, src, srcOffset, left, right, bottom, top); }
        }
    }

    public static float[] orthoCrop_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] view, int viewOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 14];
        float _t4 = _self20 - _self21;
        return orthoCrop_no_lh_sb3c10718_1(dest, destOffset, src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], _self23, src[srcOffset + 15], view[viewOffset + 0], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _self00 - _self01, _self10 - _self11, _self30 - _self31, _self21 - _self20, _self01 - _self00, _self11 - _self10, _self31 - _self30, _self20 + _self21, _self00 + _self01, _self10 + _self11, _self30 + _self31, -_self20 - _self21, -_self00 - _self01, -_self10 - _self11, -_self30 - _self31, _self23 + (_t4 - _self22));
    }

    /** Piece 2 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_lh_sb3c10718_1(float[] dest, int destOffset, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t52) {
        return orthoCrop_no_lh_sb3c10718_2(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t52, _self03 + (_t5 - _self02), _self13 + (_t6 - _self12), 1.0f / (_self33 + (_t7 - _self32)), _self23 + (_t8 - _self22), _self03 + (_t9 - _self02), _self13 + (_t10 - _self12), 1.0f / (_self33 + (_t11 - _self32)), _self23 + (_t12 - _self22), _self03 + (_t13 - _self02), _self13 + (_t14 - _self12), 1.0f / (_self33 + (_t15 - _self32)), _self23 + (_self22 + _t4), _self03 + (_self02 + _t5), _self13 + (_self12 + _t6), 1.0f / (_self33 + (_self32 + _t7)), _self23 + (_self22 + _t8), _self03 + (_self02 + _t9), _self13 + (_self12 + _t10), 1.0f / (_self33 + (_self32 + _t11)), _self23 + (_self22 + _t12), _self03 + (_self02 + _t13), _self13 + (_self12 + _t14), 1.0f / (_self33 + (_self32 + _t15)), _self23 + (_t16 - _self22), _self03 + (_t17 - _self02), _self13 + (_t18 - _self12), 1.0f / (_self33 + (_t19 - _self32)), _self23 + (_self22 + _t16), _self03 + (_self02 + _t17), _self13 + (_self12 + _t18), 1.0f / (_self33 + (_self32 + _t19)));
    }

    /** Piece 3 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_lh_sb3c10718_2(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t60, float _t61, float _t62, float _t63_inv, float _t64, float _t65, float _t66, float _t67_inv, float _t68, float _t69, float _t70, float _t71_inv, float _t72, float _t73, float _t74, float _t75_inv, float _t76, float _t77, float _t78, float _t79_inv, float _t80, float _t81, float _t82, float _t83_inv) {
        return orthoCrop_no_lh_sb3c10718_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t60, _t61, _t62, _t63_inv, _t64, _t65, _t66, _t67_inv, _t68, _t69, _t70, _t71_inv, _t72, _t73, _t74, _t75_inv, _t76, _t77, _t78, _t79_inv, _t80, _t81, _t82, _t83_inv, _view03 + Math.fma(_view02, _t52, Math.fma(_view00, _t53, _view01 * _t54)) * _t55_inv, _view03 + Math.fma(_view02, _t56, Math.fma(_view00, _t57, _view01 * _t58)) * _t59_inv, _view03 + Math.fma(_view02, _t60, Math.fma(_view00, _t61, _view01 * _t62)) * _t63_inv, _view03 + Math.fma(_view02, _t64, Math.fma(_view00, _t65, _view01 * _t66)) * _t67_inv, _view03 + Math.fma(_view02, _t68, Math.fma(_view00, _t69, _view01 * _t70)) * _t71_inv, _view03 + Math.fma(_view02, _t72, Math.fma(_view00, _t73, _view01 * _t74)) * _t75_inv, _view13 + Math.fma(_view12, _t52, Math.fma(_view10, _t53, _view11 * _t54)) * _t55_inv, _view13 + Math.fma(_view12, _t56, Math.fma(_view10, _t57, _view11 * _t58)) * _t59_inv, _view13 + Math.fma(_view12, _t60, Math.fma(_view10, _t61, _view11 * _t62)) * _t63_inv);
    }

    /** Piece 4 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_lh_sb3c10718_3(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t60, float _t61, float _t62, float _t63_inv, float _t64, float _t65, float _t66, float _t67_inv, float _t68, float _t69, float _t70, float _t71_inv, float _t72, float _t73, float _t74, float _t75_inv, float _t76, float _t77, float _t78, float _t79_inv, float _t80, float _t81, float _t82, float _t83_inv, float _t174, float _t175, float _t176, float _t177, float _t178, float _t179, float _t180, float _t181, float _t182) {
        return orthoCrop_no_lh_sb3c10718_4(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t76, _t77, _t78, _t79_inv, _t80, _t81, _t82, _t83_inv, _t174, _t175, _t176, _t177, _t178, _t179, _t180, _t181, _t182, _view13 + Math.fma(_view12, _t64, Math.fma(_view10, _t65, _view11 * _t66)) * _t67_inv, _view13 + Math.fma(_view12, _t68, Math.fma(_view10, _t69, _view11 * _t70)) * _t71_inv, _view13 + Math.fma(_view12, _t72, Math.fma(_view10, _t73, _view11 * _t74)) * _t75_inv, _view23 + Math.fma(_view22, _t52, Math.fma(_view20, _t53, _view21 * _t54)) * _t55_inv, _view23 + Math.fma(_view22, _t56, Math.fma(_view20, _t57, _view21 * _t58)) * _t59_inv, _view23 + Math.fma(_view22, _t60, Math.fma(_view20, _t61, _view21 * _t62)) * _t63_inv, _view23 + Math.fma(_view22, _t64, Math.fma(_view20, _t65, _view21 * _t66)) * _t67_inv, _view23 + Math.fma(_view22, _t68, Math.fma(_view20, _t69, _view21 * _t70)) * _t71_inv, _view23 + Math.fma(_view22, _t72, Math.fma(_view20, _t73, _view21 * _t74)) * _t75_inv, _view03 + Math.fma(_view02, _t76, Math.fma(_view00, _t77, _view01 * _t78)) * _t79_inv);
    }

    /** Piece 5 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_lh_sb3c10718_4(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t76, float _t77, float _t78, float _t79_inv, float _t80, float _t81, float _t82, float _t83_inv, float _t174, float _t175, float _t176, float _t177, float _t178, float _t179, float _t180, float _t181, float _t182, float _t183, float _t184, float _t185, float _t186, float _t187, float _t188, float _t189, float _t190, float _t191, float _t198) {
        float _t199 = _view03 + Math.fma(_view02, _t80, Math.fma(_view00, _t81, _view01 * _t82)) * _t83_inv;
        float _t200 = _view13 + Math.fma(_view12, _t76, Math.fma(_view10, _t77, _view11 * _t78)) * _t79_inv;
        float _t201 = _view13 + Math.fma(_view12, _t80, Math.fma(_view10, _t81, _view11 * _t82)) * _t83_inv;
        return orthoCrop_no_lh_sb3c10718_5(dest, destOffset, _t186, _t187, _t188, _t189, _t190, _t191, _view23 + Math.fma(_view22, _t76, Math.fma(_view20, _t77, _view21 * _t78)) * _t79_inv, _view23 + Math.fma(_view22, _t80, Math.fma(_view20, _t81, _view21 * _t82)) * _t83_inv, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t198, _t174), _t175), _t176), _t199), _t177), _t178), _t179), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t198, _t174), _t175), _t176), _t199), _t177), _t178), _t179), Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t200, _t180), _t181), _t182), _t201), _t183), _t184), _t185), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t200, _t180), _t181), _t182), _t201), _t183), _t184), _t185));
    }

    /** Piece 6 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_lh_sb3c10718_5(float[] dest, int destOffset, float _t186, float _t187, float _t188, float _t189, float _t190, float _t191, float _t202, float _t203, float _t240, float _t241, float _t242, float _t243) {
        float _t244 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t202, _t186), _t187), _t188), _t203), _t189), _t190), _t191);
        float _t245 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t202, _t186), _t187), _t188), _t203), _t189), _t190), _t191);
        float _t246_inv = 1.0f / (_t240 - _t241);
        float _t247_inv = 1.0f / (_t242 - _t243);
        float _t248_inv = 1.0f / (_t244 - _t245);
        dest[destOffset + 0] = _t246_inv + _t246_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t247_inv + _t247_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = _t248_inv + _t248_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((_t241 + _t240) * _t246_inv);
        dest[destOffset + 13] = -((_t243 + _t242) * _t247_inv);
        dest[destOffset + 14] = -((_t245 + _t244) * _t248_inv);
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] orthoCrop_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] view, int viewOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self22 = src[srcOffset + 10];
        float _self23 = src[srcOffset + 14];
        float _t4 = _self20 - _self21;
        return orthoCrop_no_rh_s1453e702_1(dest, destOffset, src[srcOffset + 8], src[srcOffset + 9], _self22, src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], _self23, src[srcOffset + 15], view[viewOffset + 0], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _self00 - _self01, _self10 - _self11, _self30 - _self31, _self21 - _self20, _self01 - _self00, _self11 - _self10, _self31 - _self30, _self20 + _self21, _self00 + _self01, _self10 + _self11, _self30 + _self31, -_self20 - _self21, -_self00 - _self01, -_self10 - _self11, -_self30 - _self31, _self23 + (_t4 - _self22));
    }

    /** Piece 2 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_rh_s1453e702_1(float[] dest, int destOffset, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t52) {
        return orthoCrop_no_rh_s1453e702_2(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t52, _self03 + (_t5 - _self02), _self13 + (_t6 - _self12), 1.0f / (_self33 + (_t7 - _self32)), _self23 + (_t8 - _self22), _self03 + (_t9 - _self02), _self13 + (_t10 - _self12), 1.0f / (_self33 + (_t11 - _self32)), _self23 + (_t12 - _self22), _self03 + (_t13 - _self02), _self13 + (_t14 - _self12), 1.0f / (_self33 + (_t15 - _self32)), _self23 + (_self22 + _t4), _self03 + (_self02 + _t5), _self13 + (_self12 + _t6), 1.0f / (_self33 + (_self32 + _t7)), _self23 + (_self22 + _t8), _self03 + (_self02 + _t9), _self13 + (_self12 + _t10), 1.0f / (_self33 + (_self32 + _t11)), _self23 + (_self22 + _t12), _self03 + (_self02 + _t13), _self13 + (_self12 + _t14), 1.0f / (_self33 + (_self32 + _t15)), _self23 + (_t16 - _self22), _self03 + (_t17 - _self02), _self13 + (_t18 - _self12), 1.0f / (_self33 + (_t19 - _self32)), _self23 + (_self22 + _t16), _self03 + (_self02 + _t17), _self13 + (_self12 + _t18), 1.0f / (_self33 + (_self32 + _t19)));
    }

    /** Piece 3 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_rh_s1453e702_2(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t60, float _t61, float _t62, float _t63_inv, float _t64, float _t65, float _t66, float _t67_inv, float _t68, float _t69, float _t70, float _t71_inv, float _t72, float _t73, float _t74, float _t75_inv, float _t76, float _t77, float _t78, float _t79_inv, float _t80, float _t81, float _t82, float _t83_inv) {
        return orthoCrop_no_rh_s1453e702_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t60, _t61, _t62, _t63_inv, _t64, _t65, _t66, _t67_inv, _t68, _t69, _t70, _t71_inv, _t72, _t73, _t74, _t75_inv, _t76, _t77, _t78, _t79_inv, _t80, _t81, _t82, _t83_inv, _view03 + Math.fma(_view02, _t52, Math.fma(_view00, _t53, _view01 * _t54)) * _t55_inv, _view03 + Math.fma(_view02, _t56, Math.fma(_view00, _t57, _view01 * _t58)) * _t59_inv, _view03 + Math.fma(_view02, _t60, Math.fma(_view00, _t61, _view01 * _t62)) * _t63_inv, _view03 + Math.fma(_view02, _t64, Math.fma(_view00, _t65, _view01 * _t66)) * _t67_inv, _view03 + Math.fma(_view02, _t68, Math.fma(_view00, _t69, _view01 * _t70)) * _t71_inv, _view03 + Math.fma(_view02, _t72, Math.fma(_view00, _t73, _view01 * _t74)) * _t75_inv, _view13 + Math.fma(_view12, _t52, Math.fma(_view10, _t53, _view11 * _t54)) * _t55_inv, _view13 + Math.fma(_view12, _t56, Math.fma(_view10, _t57, _view11 * _t58)) * _t59_inv, _view13 + Math.fma(_view12, _t60, Math.fma(_view10, _t61, _view11 * _t62)) * _t63_inv);
    }

    /** Piece 4 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_rh_s1453e702_3(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t60, float _t61, float _t62, float _t63_inv, float _t64, float _t65, float _t66, float _t67_inv, float _t68, float _t69, float _t70, float _t71_inv, float _t72, float _t73, float _t74, float _t75_inv, float _t76, float _t77, float _t78, float _t79_inv, float _t80, float _t81, float _t82, float _t83_inv, float _t174, float _t175, float _t176, float _t177, float _t178, float _t179, float _t180, float _t181, float _t182) {
        return orthoCrop_no_rh_s1453e702_4(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t76, _t77, _t78, _t79_inv, _t80, _t81, _t82, _t83_inv, _t174, _t175, _t176, _t177, _t178, _t179, _t180, _t181, _t182, _view13 + Math.fma(_view12, _t64, Math.fma(_view10, _t65, _view11 * _t66)) * _t67_inv, _view13 + Math.fma(_view12, _t68, Math.fma(_view10, _t69, _view11 * _t70)) * _t71_inv, _view13 + Math.fma(_view12, _t72, Math.fma(_view10, _t73, _view11 * _t74)) * _t75_inv, _view23 + Math.fma(_view22, _t52, Math.fma(_view20, _t53, _view21 * _t54)) * _t55_inv, _view23 + Math.fma(_view22, _t56, Math.fma(_view20, _t57, _view21 * _t58)) * _t59_inv, _view23 + Math.fma(_view22, _t60, Math.fma(_view20, _t61, _view21 * _t62)) * _t63_inv, _view23 + Math.fma(_view22, _t64, Math.fma(_view20, _t65, _view21 * _t66)) * _t67_inv, _view23 + Math.fma(_view22, _t68, Math.fma(_view20, _t69, _view21 * _t70)) * _t71_inv, _view23 + Math.fma(_view22, _t72, Math.fma(_view20, _t73, _view21 * _t74)) * _t75_inv, _view03 + Math.fma(_view02, _t76, Math.fma(_view00, _t77, _view01 * _t78)) * _t79_inv);
    }

    /** Piece 5 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_rh_s1453e702_4(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t76, float _t77, float _t78, float _t79_inv, float _t80, float _t81, float _t82, float _t83_inv, float _t174, float _t175, float _t176, float _t177, float _t178, float _t179, float _t180, float _t181, float _t182, float _t183, float _t184, float _t185, float _t186, float _t187, float _t188, float _t189, float _t190, float _t191, float _t198) {
        float _t199 = _view03 + Math.fma(_view02, _t80, Math.fma(_view00, _t81, _view01 * _t82)) * _t83_inv;
        float _t200 = _view13 + Math.fma(_view12, _t76, Math.fma(_view10, _t77, _view11 * _t78)) * _t79_inv;
        float _t201 = _view13 + Math.fma(_view12, _t80, Math.fma(_view10, _t81, _view11 * _t82)) * _t83_inv;
        return orthoCrop_no_rh_s1453e702_5(dest, destOffset, _t186, _t187, _t188, _t189, _t190, _t191, _view23 + Math.fma(_view22, _t76, Math.fma(_view20, _t77, _view21 * _t78)) * _t79_inv, _view23 + Math.fma(_view22, _t80, Math.fma(_view20, _t81, _view21 * _t82)) * _t83_inv, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t198, _t174), _t175), _t176), _t199), _t177), _t178), _t179), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t198, _t174), _t175), _t176), _t199), _t177), _t178), _t179), Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t200, _t180), _t181), _t182), _t201), _t183), _t184), _t185), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t200, _t180), _t181), _t182), _t201), _t183), _t184), _t185));
    }

    /** Piece 6 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_rh_s1453e702_5(float[] dest, int destOffset, float _t186, float _t187, float _t188, float _t189, float _t190, float _t191, float _t202, float _t203, float _t240, float _t241, float _t242, float _t243) {
        float _t244 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t202, _t186), _t187), _t188), _t203), _t189), _t190), _t191);
        float _t245 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t202, _t186), _t187), _t188), _t203), _t189), _t190), _t191);
        float _t246_inv = 1.0f / (_t240 - _t241);
        float _t247_inv = 1.0f / (_t242 - _t243);
        float _t248_inv = 1.0f / (_t244 - _t245);
        dest[destOffset + 0] = _t246_inv + _t246_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t247_inv + _t247_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = -2.0f * _t248_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((_t241 + _t240) * _t246_inv);
        dest[destOffset + 13] = -((_t243 + _t242) * _t247_inv);
        dest[destOffset + 14] = -((-_t245 - _t244) * _t248_inv);
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] orthoCrop_no(float[] dest, int destOffset, float[] src, int srcOffset, float[] view, int viewOffset, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.orthoCrop_no_lh(dest, destOffset, src, srcOffset, view, viewOffset); }
            default -> { return Float4x4OpsKernelsArray.orthoCrop_no_rh(dest, destOffset, src, srcOffset, view, viewOffset); }
        }
    }

    public static float[] orthoCrop_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] view, int viewOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self23 = src[srcOffset + 14];
        float _t4 = _self20 - _self21;
        return orthoCrop_zo_lh_s886c7434_1(dest, destOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], _self23, src[srcOffset + 15], view[viewOffset + 0], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _self00 - _self01, _self10 - _self11, _self30 - _self31, _self21 - _self20, _self01 - _self00, _self11 - _self10, _self31 - _self30, _self20 + _self21, _self00 + _self01, _self10 + _self11, _self30 + _self31, -_self20 - _self21, -_self00 - _self01, -_self10 - _self11, -_self30 - _self31, _self23 + _t4);
    }

    /** Piece 2 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_lh_s886c7434_1(float[] dest, int destOffset, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20) {
        float _t21 = _self03 + _t5;
        float _t22 = _self13 + _t6;
        float _t23_inv = 1.0f / (_self33 + _t7);
        return orthoCrop_zo_lh_s886c7434_2(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t20, _t21, _t22, _t23_inv, _self23 + _t8, _self03 + _t9, _self13 + _t10, 1.0f / (_self33 + _t11), _self23 + _t12, _self03 + _t13, _self13 + _t14, 1.0f / (_self33 + _t15), _self23 + _t16, _self03 + _t17, _self13 + _t18, 1.0f / (_self33 + _t19), _self23 + (_self22 + _t4), _self03 + (_self02 + _t5), _self13 + (_self12 + _t6), 1.0f / (_self33 + (_self32 + _t7)), _self23 + (_self22 + _t8), _self03 + (_self02 + _t9), _self13 + (_self12 + _t10), 1.0f / (_self33 + (_self32 + _t11)), _self23 + (_self22 + _t12), _self03 + (_self02 + _t13), _self13 + (_self12 + _t14), 1.0f / (_self33 + (_self32 + _t15)), _self23 + (_self22 + _t16), _self03 + (_self02 + _t17), _self13 + (_self12 + _t18), 1.0f / (_self33 + (_self32 + _t19)), _view03 + Math.fma(_view02, _t20, Math.fma(_view00, _t21, _view01 * _t22)) * _t23_inv);
    }

    /** Piece 3 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_lh_s886c7434_2(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t20, float _t21, float _t22, float _t23_inv, float _t24, float _t25, float _t26, float _t27_inv, float _t28, float _t29, float _t30, float _t31_inv, float _t44, float _t45, float _t46, float _t47_inv, float _t55, float _t56, float _t57, float _t58_inv, float _t59, float _t60, float _t61, float _t62_inv, float _t63, float _t64, float _t65, float _t66_inv, float _t74, float _t75, float _t76, float _t77_inv, float _t146) {
        return orthoCrop_zo_lh_s886c7434_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t44, _t45, _t46, _t47_inv, _t55, _t56, _t57, _t58_inv, _t59, _t60, _t61, _t62_inv, _t63, _t64, _t65, _t66_inv, _t74, _t75, _t76, _t77_inv, _t146, _view03 + Math.fma(_view02, _t24, Math.fma(_view00, _t25, _view01 * _t26)) * _t27_inv, _view03 + Math.fma(_view02, _t28, Math.fma(_view00, _t29, _view01 * _t30)) * _t31_inv, _view13 + Math.fma(_view12, _t20, Math.fma(_view10, _t21, _view11 * _t22)) * _t23_inv, _view13 + Math.fma(_view12, _t24, Math.fma(_view10, _t25, _view11 * _t26)) * _t27_inv, _view13 + Math.fma(_view12, _t28, Math.fma(_view10, _t29, _view11 * _t30)) * _t31_inv, _view23 + Math.fma(_view22, _t20, Math.fma(_view20, _t21, _view21 * _t22)) * _t23_inv, _view23 + Math.fma(_view22, _t24, Math.fma(_view20, _t25, _view21 * _t26)) * _t27_inv, _view23 + Math.fma(_view22, _t28, Math.fma(_view20, _t29, _view21 * _t30)) * _t31_inv, _view03 + Math.fma(_view02, _t44, Math.fma(_view00, _t45, _view01 * _t46)) * _t47_inv);
    }

    /** Piece 4 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_lh_s886c7434_3(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t44, float _t45, float _t46, float _t47_inv, float _t55, float _t56, float _t57, float _t58_inv, float _t59, float _t60, float _t61, float _t62_inv, float _t63, float _t64, float _t65, float _t66_inv, float _t74, float _t75, float _t76, float _t77_inv, float _t146, float _t147, float _t148, float _t150, float _t151, float _t152, float _t154, float _t155, float _t156, float _t161) {
        return orthoCrop_zo_lh_s886c7434_4(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t63, _t64, _t65, _t66_inv, _t74, _t75, _t76, _t77_inv, _t146, _t147, _t148, _t150, _t151, _t152, _t154, _t155, _t156, _t161, _view13 + Math.fma(_view12, _t44, Math.fma(_view10, _t45, _view11 * _t46)) * _t47_inv, _view23 + Math.fma(_view22, _t44, Math.fma(_view20, _t45, _view21 * _t46)) * _t47_inv, _view03 + Math.fma(_view02, _t55, Math.fma(_view00, _t56, _view01 * _t57)) * _t58_inv, _view03 + Math.fma(_view02, _t59, Math.fma(_view00, _t60, _view01 * _t61)) * _t62_inv, _view03 + Math.fma(_view02, _t63, Math.fma(_view00, _t64, _view01 * _t65)) * _t66_inv, _view13 + Math.fma(_view12, _t55, Math.fma(_view10, _t56, _view11 * _t57)) * _t58_inv, _view13 + Math.fma(_view12, _t59, Math.fma(_view10, _t60, _view11 * _t61)) * _t62_inv, _view13 + Math.fma(_view12, _t63, Math.fma(_view10, _t64, _view11 * _t65)) * _t66_inv, _view23 + Math.fma(_view22, _t55, Math.fma(_view20, _t56, _view21 * _t57)) * _t58_inv, _view23 + Math.fma(_view22, _t59, Math.fma(_view20, _t60, _view21 * _t61)) * _t62_inv);
    }

    /** Piece 5 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_lh_s886c7434_4(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t63, float _t64, float _t65, float _t66_inv, float _t74, float _t75, float _t76, float _t77_inv, float _t146, float _t147, float _t148, float _t150, float _t151, float _t152, float _t154, float _t155, float _t156, float _t161, float _t162, float _t163, float _t173, float _t174, float _t175, float _t176, float _t177, float _t178, float _t179, float _t180) {
        float _t185 = _view03 + Math.fma(_view02, _t74, Math.fma(_view00, _t75, _view01 * _t76)) * _t77_inv;
        float _t186 = _view13 + Math.fma(_view12, _t74, Math.fma(_view10, _t75, _view11 * _t76)) * _t77_inv;
        return orthoCrop_zo_lh_s886c7434_5(dest, destOffset, _t154, _t155, _t156, _t163, _t179, _t180, _view23 + Math.fma(_view22, _t63, Math.fma(_view20, _t64, _view21 * _t65)) * _t66_inv, _view23 + Math.fma(_view22, _t74, Math.fma(_view20, _t75, _view21 * _t76)) * _t77_inv, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t161, _t146), _t147), _t148), _t185), _t173), _t174), _t175), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t161, _t146), _t147), _t148), _t185), _t173), _t174), _t175), Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t162, _t150), _t151), _t152), _t186), _t176), _t177), _t178), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t162, _t150), _t151), _t152), _t186), _t176), _t177), _t178));
    }

    /** Piece 6 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_lh_s886c7434_5(float[] dest, int destOffset, float _t154, float _t155, float _t156, float _t163, float _t179, float _t180, float _t181, float _t187, float _t224, float _t225, float _t226, float _t227) {
        float _t229 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t163, _t154), _t155), _t156), _t187), _t179), _t180), _t181);
        float _t230_inv = 1.0f / (_t224 - _t225);
        float _t231_inv = 1.0f / (_t226 - _t227);
        float _t232_inv = 1.0f / (Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t163, _t154), _t155), _t156), _t187), _t179), _t180), _t181) - _t229);
        dest[destOffset + 0] = _t230_inv + _t230_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t231_inv + _t231_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = _t232_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((_t225 + _t224) * _t230_inv);
        dest[destOffset + 13] = -((_t227 + _t226) * _t231_inv);
        dest[destOffset + 14] = -(_t229 * _t232_inv);
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] orthoCrop_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] view, int viewOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self23 = src[srcOffset + 14];
        float _t4 = _self20 - _self21;
        return orthoCrop_zo_rh_se4e10906_1(dest, destOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], _self23, src[srcOffset + 15], view[viewOffset + 0], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _self00 - _self01, _self10 - _self11, _self30 - _self31, _self21 - _self20, _self01 - _self00, _self11 - _self10, _self31 - _self30, _self20 + _self21, _self00 + _self01, _self10 + _self11, _self30 + _self31, -_self20 - _self21, -_self00 - _self01, -_self10 - _self11, -_self30 - _self31, _self23 + _t4);
    }

    /** Piece 2 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_rh_se4e10906_1(float[] dest, int destOffset, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20) {
        float _t21 = _self03 + _t5;
        float _t22 = _self13 + _t6;
        float _t23_inv = 1.0f / (_self33 + _t7);
        return orthoCrop_zo_rh_se4e10906_2(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t20, _t21, _t22, _t23_inv, _self23 + _t8, _self03 + _t9, _self13 + _t10, 1.0f / (_self33 + _t11), _self23 + _t12, _self03 + _t13, _self13 + _t14, 1.0f / (_self33 + _t15), _self23 + _t16, _self03 + _t17, _self13 + _t18, 1.0f / (_self33 + _t19), _self23 + (_self22 + _t4), _self03 + (_self02 + _t5), _self13 + (_self12 + _t6), 1.0f / (_self33 + (_self32 + _t7)), _self23 + (_self22 + _t8), _self03 + (_self02 + _t9), _self13 + (_self12 + _t10), 1.0f / (_self33 + (_self32 + _t11)), _self23 + (_self22 + _t12), _self03 + (_self02 + _t13), _self13 + (_self12 + _t14), 1.0f / (_self33 + (_self32 + _t15)), _self23 + (_self22 + _t16), _self03 + (_self02 + _t17), _self13 + (_self12 + _t18), 1.0f / (_self33 + (_self32 + _t19)), _view03 + Math.fma(_view02, _t20, Math.fma(_view00, _t21, _view01 * _t22)) * _t23_inv);
    }

    /** Piece 3 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_rh_se4e10906_2(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t20, float _t21, float _t22, float _t23_inv, float _t24, float _t25, float _t26, float _t27_inv, float _t28, float _t29, float _t30, float _t31_inv, float _t44, float _t45, float _t46, float _t47_inv, float _t55, float _t56, float _t57, float _t58_inv, float _t59, float _t60, float _t61, float _t62_inv, float _t63, float _t64, float _t65, float _t66_inv, float _t74, float _t75, float _t76, float _t77_inv, float _t146) {
        return orthoCrop_zo_rh_se4e10906_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t44, _t45, _t46, _t47_inv, _t55, _t56, _t57, _t58_inv, _t59, _t60, _t61, _t62_inv, _t63, _t64, _t65, _t66_inv, _t74, _t75, _t76, _t77_inv, _t146, _view03 + Math.fma(_view02, _t24, Math.fma(_view00, _t25, _view01 * _t26)) * _t27_inv, _view03 + Math.fma(_view02, _t28, Math.fma(_view00, _t29, _view01 * _t30)) * _t31_inv, _view13 + Math.fma(_view12, _t20, Math.fma(_view10, _t21, _view11 * _t22)) * _t23_inv, _view13 + Math.fma(_view12, _t24, Math.fma(_view10, _t25, _view11 * _t26)) * _t27_inv, _view13 + Math.fma(_view12, _t28, Math.fma(_view10, _t29, _view11 * _t30)) * _t31_inv, _view23 + Math.fma(_view22, _t20, Math.fma(_view20, _t21, _view21 * _t22)) * _t23_inv, _view23 + Math.fma(_view22, _t24, Math.fma(_view20, _t25, _view21 * _t26)) * _t27_inv, _view23 + Math.fma(_view22, _t28, Math.fma(_view20, _t29, _view21 * _t30)) * _t31_inv, _view03 + Math.fma(_view02, _t44, Math.fma(_view00, _t45, _view01 * _t46)) * _t47_inv);
    }

    /** Piece 4 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_rh_se4e10906_3(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t44, float _t45, float _t46, float _t47_inv, float _t55, float _t56, float _t57, float _t58_inv, float _t59, float _t60, float _t61, float _t62_inv, float _t63, float _t64, float _t65, float _t66_inv, float _t74, float _t75, float _t76, float _t77_inv, float _t146, float _t147, float _t148, float _t150, float _t151, float _t152, float _t154, float _t155, float _t156, float _t161) {
        return orthoCrop_zo_rh_se4e10906_4(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t63, _t64, _t65, _t66_inv, _t74, _t75, _t76, _t77_inv, _t146, _t147, _t148, _t150, _t151, _t152, _t154, _t155, _t156, _t161, _view13 + Math.fma(_view12, _t44, Math.fma(_view10, _t45, _view11 * _t46)) * _t47_inv, _view23 + Math.fma(_view22, _t44, Math.fma(_view20, _t45, _view21 * _t46)) * _t47_inv, _view03 + Math.fma(_view02, _t55, Math.fma(_view00, _t56, _view01 * _t57)) * _t58_inv, _view03 + Math.fma(_view02, _t59, Math.fma(_view00, _t60, _view01 * _t61)) * _t62_inv, _view03 + Math.fma(_view02, _t63, Math.fma(_view00, _t64, _view01 * _t65)) * _t66_inv, _view13 + Math.fma(_view12, _t55, Math.fma(_view10, _t56, _view11 * _t57)) * _t58_inv, _view13 + Math.fma(_view12, _t59, Math.fma(_view10, _t60, _view11 * _t61)) * _t62_inv, _view13 + Math.fma(_view12, _t63, Math.fma(_view10, _t64, _view11 * _t65)) * _t66_inv, _view23 + Math.fma(_view22, _t55, Math.fma(_view20, _t56, _view21 * _t57)) * _t58_inv, _view23 + Math.fma(_view22, _t59, Math.fma(_view20, _t60, _view21 * _t61)) * _t62_inv);
    }

    /** Piece 5 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_rh_se4e10906_4(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t63, float _t64, float _t65, float _t66_inv, float _t74, float _t75, float _t76, float _t77_inv, float _t146, float _t147, float _t148, float _t150, float _t151, float _t152, float _t154, float _t155, float _t156, float _t161, float _t162, float _t163, float _t173, float _t174, float _t175, float _t176, float _t177, float _t178, float _t179, float _t180) {
        float _t185 = _view03 + Math.fma(_view02, _t74, Math.fma(_view00, _t75, _view01 * _t76)) * _t77_inv;
        float _t186 = _view13 + Math.fma(_view12, _t74, Math.fma(_view10, _t75, _view11 * _t76)) * _t77_inv;
        return orthoCrop_zo_rh_se4e10906_5(dest, destOffset, _t154, _t155, _t156, _t163, _t179, _t180, _view23 + Math.fma(_view22, _t63, Math.fma(_view20, _t64, _view21 * _t65)) * _t66_inv, _view23 + Math.fma(_view22, _t74, Math.fma(_view20, _t75, _view21 * _t76)) * _t77_inv, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t161, _t146), _t147), _t148), _t185), _t173), _t174), _t175), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t161, _t146), _t147), _t148), _t185), _t173), _t174), _t175), Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t162, _t150), _t151), _t152), _t186), _t176), _t177), _t178), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t162, _t150), _t151), _t152), _t186), _t176), _t177), _t178));
    }

    /** Piece 6 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_rh_se4e10906_5(float[] dest, int destOffset, float _t154, float _t155, float _t156, float _t163, float _t179, float _t180, float _t181, float _t187, float _t224, float _t225, float _t226, float _t227) {
        float _t228 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t163, _t154), _t155), _t156), _t187), _t179), _t180), _t181);
        float _t230_inv = 1.0f / (_t224 - _t225);
        float _t231_inv = 1.0f / (_t226 - _t227);
        float _t232_inv = 1.0f / (_t228 - Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t163, _t154), _t155), _t156), _t187), _t179), _t180), _t181));
        dest[destOffset + 0] = _t230_inv + _t230_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t231_inv + _t231_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = -_t232_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((_t225 + _t224) * _t230_inv);
        dest[destOffset + 13] = -((_t227 + _t226) * _t231_inv);
        dest[destOffset + 14] = _t228 * _t232_inv;
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] orthoCrop_zo(float[] dest, int destOffset, float[] src, int srcOffset, float[] view, int viewOffset, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.orthoCrop_zo_lh(dest, destOffset, src, srcOffset, view, viewOffset); }
            default -> { return Float4x4OpsKernelsArray.orthoCrop_zo_rh(dest, destOffset, src, srcOffset, view, viewOffset); }
        }
    }

    public static float[] orthoCrop_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] view, int viewOffset, float minZ, float maxZ) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t4 = _self23 + _self20;
        return orthoCrop_no_lh_sc7de3ce6_1(dest, destOffset, minZ, maxZ, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _self03, _self13, _self23, _self33, view[viewOffset + 0], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _self03 + _self00, _self13 + _self10, _self33 + _self30, _self23 - _self20 - _self21, _self03 - _self00 - _self01, _self13 - _self10 - _self11, _self33 - _self30 - _self31, _t4 - _self21);
    }

    /** Piece 2 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_lh_sc7de3ce6_1(float[] dest, int destOffset, float minZ, float maxZ, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t4, float _t5, float _t6, float _t7, float _t12, float _t13, float _t14, float _t15, float _t16) {
        float _t17 = _t5 - _self01;
        float _t18 = _t6 - _self11;
        float _t19 = _t7 - _self31;
        float _t20 = _self23 + _self21 - _self20;
        float _t21 = _self03 + _self01 - _self00;
        float _t22 = _self13 + _self11 - _self10;
        float _t23 = _self33 + _self31 - _self30;
        float _t24 = _t4 + _self21;
        float _t25 = _t5 + _self01;
        float _t26 = _t6 + _self11;
        float _t27 = _t7 + _self31;
        return orthoCrop_no_lh_sc7de3ce6_2(dest, destOffset, maxZ, _self02, _self12, _self22, _self32, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, Math.fma(minZ, _self22, _t12), Math.fma(minZ, _self02, _t13), Math.fma(minZ, _self12, _t14), 1.0f / Math.fma(minZ, _self32, _t15), Math.fma(minZ, _self22, _t16), Math.fma(minZ, _self02, _t17), Math.fma(minZ, _self12, _t18), 1.0f / Math.fma(minZ, _self32, _t19), Math.fma(minZ, _self22, _t20), Math.fma(minZ, _self02, _t21), Math.fma(minZ, _self12, _t22), 1.0f / Math.fma(minZ, _self32, _t23), Math.fma(minZ, _self22, _t24), Math.fma(minZ, _self02, _t25), Math.fma(minZ, _self12, _t26), 1.0f / Math.fma(minZ, _self32, _t27), Math.fma(maxZ, _self22, _t12), Math.fma(maxZ, _self02, _t13));
    }

    /** Piece 3 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_lh_sc7de3ce6_2(float[] dest, int destOffset, float maxZ, float _self02, float _self12, float _self22, float _self32, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31_inv, float _t32, float _t33, float _t34, float _t35_inv, float _t36, float _t37, float _t38, float _t39_inv, float _t40, float _t41, float _t42, float _t43_inv, float _t44, float _t45) {
        return orthoCrop_no_lh_sc7de3ce6_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, Math.fma(maxZ, _self12, _t14), 1.0f / Math.fma(maxZ, _self32, _t15), Math.fma(maxZ, _self22, _t16), Math.fma(maxZ, _self02, _t17), Math.fma(maxZ, _self12, _t18), 1.0f / Math.fma(maxZ, _self32, _t19), Math.fma(maxZ, _self22, _t20), Math.fma(maxZ, _self02, _t21), Math.fma(maxZ, _self12, _t22), 1.0f / Math.fma(maxZ, _self32, _t23), Math.fma(maxZ, _self22, _t24), Math.fma(maxZ, _self02, _t25), Math.fma(maxZ, _self12, _t26), 1.0f / Math.fma(maxZ, _self32, _t27), _view03 + Math.fma(_view02, _t28, Math.fma(_view00, _t29, _view01 * _t30)) * _t31_inv, _view03 + Math.fma(_view02, _t32, Math.fma(_view00, _t33, _view01 * _t34)) * _t35_inv, _view03 + Math.fma(_view02, _t36, Math.fma(_view00, _t37, _view01 * _t38)) * _t39_inv, _view03 + Math.fma(_view02, _t40, Math.fma(_view00, _t41, _view01 * _t42)) * _t43_inv);
    }

    /** Piece 4 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_lh_sc7de3ce6_3(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t28, float _t29, float _t30, float _t31_inv, float _t32, float _t33, float _t34, float _t35_inv, float _t36, float _t37, float _t38, float _t39_inv, float _t40, float _t41, float _t42, float _t43_inv, float _t44, float _t45, float _t46, float _t47_inv, float _t48, float _t49, float _t50, float _t51_inv, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t156, float _t157, float _t158, float _t159) {
        return orthoCrop_no_lh_sc7de3ce6_4(dest, destOffset, _view10, _view20, _view11, _view21, _view12, _view22, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, _t46, _t47_inv, _t48, _t49, _t50, _t51_inv, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _view03 + Math.fma(_view02, _t44, Math.fma(_view00, _t45, _view01 * _t46)) * _t47_inv, _view03 + Math.fma(_view02, _t48, Math.fma(_view00, _t49, _view01 * _t50)) * _t51_inv, _view03 + Math.fma(_view02, _t52, Math.fma(_view00, _t53, _view01 * _t54)) * _t55_inv, _view03 + Math.fma(_view02, _t56, Math.fma(_view00, _t57, _view01 * _t58)) * _t59_inv, _view13 + Math.fma(_view12, _t28, Math.fma(_view10, _t29, _view11 * _t30)) * _t31_inv, _view13 + Math.fma(_view12, _t32, Math.fma(_view10, _t33, _view11 * _t34)) * _t35_inv, _view13 + Math.fma(_view12, _t36, Math.fma(_view10, _t37, _view11 * _t38)) * _t39_inv, _view13 + Math.fma(_view12, _t40, Math.fma(_view10, _t41, _view11 * _t42)) * _t43_inv);
    }

    /** Piece 5 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_lh_sc7de3ce6_4(float[] dest, int destOffset, float _view10, float _view20, float _view11, float _view21, float _view12, float _view22, float _view13, float _view23, float _t28, float _t29, float _t30, float _t31_inv, float _t32, float _t33, float _t34, float _t35_inv, float _t36, float _t37, float _t38, float _t39_inv, float _t40, float _t41, float _t42, float _t43_inv, float _t44, float _t45, float _t46, float _t47_inv, float _t48, float _t49, float _t50, float _t51_inv, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t156, float _t157, float _t158, float _t159, float _t160, float _t161, float _t162, float _t163, float _t164, float _t165, float _t166, float _t167) {
        return orthoCrop_no_lh_sc7de3ce6_5(dest, destOffset, _view20, _view21, _view22, _view23, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _t160, _t161, _t162, _t163, _t164, _t165, _t166, _t167, _view13 + Math.fma(_view12, _t44, Math.fma(_view10, _t45, _view11 * _t46)) * _t47_inv, _view13 + Math.fma(_view12, _t48, Math.fma(_view10, _t49, _view11 * _t50)) * _t51_inv, _view13 + Math.fma(_view12, _t52, Math.fma(_view10, _t53, _view11 * _t54)) * _t55_inv, _view13 + Math.fma(_view12, _t56, Math.fma(_view10, _t57, _view11 * _t58)) * _t59_inv, _view23 + Math.fma(_view22, _t28, Math.fma(_view20, _t29, _view21 * _t30)) * _t31_inv, _view23 + Math.fma(_view22, _t32, Math.fma(_view20, _t33, _view21 * _t34)) * _t35_inv, _view23 + Math.fma(_view22, _t36, Math.fma(_view20, _t37, _view21 * _t38)) * _t39_inv, _view23 + Math.fma(_view22, _t40, Math.fma(_view20, _t41, _view21 * _t42)) * _t43_inv, _view23 + Math.fma(_view22, _t44, Math.fma(_view20, _t45, _view21 * _t46)) * _t47_inv, _view23 + Math.fma(_view22, _t48, Math.fma(_view20, _t49, _view21 * _t50)) * _t51_inv);
    }

    /** Piece 6 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_lh_sc7de3ce6_5(float[] dest, int destOffset, float _view20, float _view21, float _view22, float _view23, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t156, float _t157, float _t158, float _t159, float _t160, float _t161, float _t162, float _t163, float _t164, float _t165, float _t166, float _t167, float _t168, float _t169, float _t170, float _t171, float _t172, float _t173, float _t174, float _t175, float _t176, float _t177) {
        float _t178 = _view23 + Math.fma(_view22, _t52, Math.fma(_view20, _t53, _view21 * _t54)) * _t55_inv;
        float _t179 = _view23 + Math.fma(_view22, _t56, Math.fma(_view20, _t57, _view21 * _t58)) * _t59_inv;
        float _t216 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        float _t217 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        float _t218 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171);
        float _t219 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171);
        return orthoCrop_no_lh_sc7de3ce6_6(dest, destOffset, _t216, _t217, _t218, _t219, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179), 1.0f / (_t216 - _t217), 1.0f / (_t218 - _t219));
    }

    /** Piece 7 of {@code orthoCrop_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_lh_sc7de3ce6_6(float[] dest, int destOffset, float _t216, float _t217, float _t218, float _t219, float _t220, float _t221, float _t222_inv, float _t223_inv) {
        float _t224_inv = 1.0f / (_t220 - _t221);
        dest[destOffset + 0] = _t222_inv + _t222_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t223_inv + _t223_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = _t224_inv + _t224_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((_t217 + _t216) * _t222_inv);
        dest[destOffset + 13] = -((_t219 + _t218) * _t223_inv);
        dest[destOffset + 14] = -((_t221 + _t220) * _t224_inv);
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] orthoCrop_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] view, int viewOffset, float minZ, float maxZ) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t4 = _self23 + _self20;
        return orthoCrop_no_rh_s6fcfc03c_1(dest, destOffset, minZ, maxZ, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _self03, _self13, _self23, _self33, view[viewOffset + 0], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _self03 + _self00, _self13 + _self10, _self33 + _self30, _self23 - _self20 - _self21, _self03 - _self00 - _self01, _self13 - _self10 - _self11, _self33 - _self30 - _self31, _t4 - _self21);
    }

    /** Piece 2 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_rh_s6fcfc03c_1(float[] dest, int destOffset, float minZ, float maxZ, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t4, float _t5, float _t6, float _t7, float _t12, float _t13, float _t14, float _t15, float _t16) {
        float _t17 = _t5 - _self01;
        float _t18 = _t6 - _self11;
        float _t19 = _t7 - _self31;
        float _t20 = _self23 + _self21 - _self20;
        float _t21 = _self03 + _self01 - _self00;
        float _t22 = _self13 + _self11 - _self10;
        float _t23 = _self33 + _self31 - _self30;
        float _t24 = _t4 + _self21;
        float _t25 = _t5 + _self01;
        float _t26 = _t6 + _self11;
        float _t27 = _t7 + _self31;
        return orthoCrop_no_rh_s6fcfc03c_2(dest, destOffset, maxZ, _self02, _self12, _self22, _self32, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, Math.fma(minZ, _self22, _t12), Math.fma(minZ, _self02, _t13), Math.fma(minZ, _self12, _t14), 1.0f / Math.fma(minZ, _self32, _t15), Math.fma(minZ, _self22, _t16), Math.fma(minZ, _self02, _t17), Math.fma(minZ, _self12, _t18), 1.0f / Math.fma(minZ, _self32, _t19), Math.fma(minZ, _self22, _t20), Math.fma(minZ, _self02, _t21), Math.fma(minZ, _self12, _t22), 1.0f / Math.fma(minZ, _self32, _t23), Math.fma(minZ, _self22, _t24), Math.fma(minZ, _self02, _t25), Math.fma(minZ, _self12, _t26), 1.0f / Math.fma(minZ, _self32, _t27), Math.fma(maxZ, _self22, _t12), Math.fma(maxZ, _self02, _t13));
    }

    /** Piece 3 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_rh_s6fcfc03c_2(float[] dest, int destOffset, float maxZ, float _self02, float _self12, float _self22, float _self32, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31_inv, float _t32, float _t33, float _t34, float _t35_inv, float _t36, float _t37, float _t38, float _t39_inv, float _t40, float _t41, float _t42, float _t43_inv, float _t44, float _t45) {
        return orthoCrop_no_rh_s6fcfc03c_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, Math.fma(maxZ, _self12, _t14), 1.0f / Math.fma(maxZ, _self32, _t15), Math.fma(maxZ, _self22, _t16), Math.fma(maxZ, _self02, _t17), Math.fma(maxZ, _self12, _t18), 1.0f / Math.fma(maxZ, _self32, _t19), Math.fma(maxZ, _self22, _t20), Math.fma(maxZ, _self02, _t21), Math.fma(maxZ, _self12, _t22), 1.0f / Math.fma(maxZ, _self32, _t23), Math.fma(maxZ, _self22, _t24), Math.fma(maxZ, _self02, _t25), Math.fma(maxZ, _self12, _t26), 1.0f / Math.fma(maxZ, _self32, _t27), _view03 + Math.fma(_view02, _t28, Math.fma(_view00, _t29, _view01 * _t30)) * _t31_inv, _view03 + Math.fma(_view02, _t32, Math.fma(_view00, _t33, _view01 * _t34)) * _t35_inv, _view03 + Math.fma(_view02, _t36, Math.fma(_view00, _t37, _view01 * _t38)) * _t39_inv, _view03 + Math.fma(_view02, _t40, Math.fma(_view00, _t41, _view01 * _t42)) * _t43_inv);
    }

    /** Piece 4 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_rh_s6fcfc03c_3(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t28, float _t29, float _t30, float _t31_inv, float _t32, float _t33, float _t34, float _t35_inv, float _t36, float _t37, float _t38, float _t39_inv, float _t40, float _t41, float _t42, float _t43_inv, float _t44, float _t45, float _t46, float _t47_inv, float _t48, float _t49, float _t50, float _t51_inv, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t156, float _t157, float _t158, float _t159) {
        return orthoCrop_no_rh_s6fcfc03c_4(dest, destOffset, _view10, _view20, _view11, _view21, _view12, _view22, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, _t46, _t47_inv, _t48, _t49, _t50, _t51_inv, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _view03 + Math.fma(_view02, _t44, Math.fma(_view00, _t45, _view01 * _t46)) * _t47_inv, _view03 + Math.fma(_view02, _t48, Math.fma(_view00, _t49, _view01 * _t50)) * _t51_inv, _view03 + Math.fma(_view02, _t52, Math.fma(_view00, _t53, _view01 * _t54)) * _t55_inv, _view03 + Math.fma(_view02, _t56, Math.fma(_view00, _t57, _view01 * _t58)) * _t59_inv, _view13 + Math.fma(_view12, _t28, Math.fma(_view10, _t29, _view11 * _t30)) * _t31_inv, _view13 + Math.fma(_view12, _t32, Math.fma(_view10, _t33, _view11 * _t34)) * _t35_inv, _view13 + Math.fma(_view12, _t36, Math.fma(_view10, _t37, _view11 * _t38)) * _t39_inv, _view13 + Math.fma(_view12, _t40, Math.fma(_view10, _t41, _view11 * _t42)) * _t43_inv);
    }

    /** Piece 5 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_rh_s6fcfc03c_4(float[] dest, int destOffset, float _view10, float _view20, float _view11, float _view21, float _view12, float _view22, float _view13, float _view23, float _t28, float _t29, float _t30, float _t31_inv, float _t32, float _t33, float _t34, float _t35_inv, float _t36, float _t37, float _t38, float _t39_inv, float _t40, float _t41, float _t42, float _t43_inv, float _t44, float _t45, float _t46, float _t47_inv, float _t48, float _t49, float _t50, float _t51_inv, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t156, float _t157, float _t158, float _t159, float _t160, float _t161, float _t162, float _t163, float _t164, float _t165, float _t166, float _t167) {
        return orthoCrop_no_rh_s6fcfc03c_5(dest, destOffset, _view20, _view21, _view22, _view23, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _t160, _t161, _t162, _t163, _t164, _t165, _t166, _t167, _view13 + Math.fma(_view12, _t44, Math.fma(_view10, _t45, _view11 * _t46)) * _t47_inv, _view13 + Math.fma(_view12, _t48, Math.fma(_view10, _t49, _view11 * _t50)) * _t51_inv, _view13 + Math.fma(_view12, _t52, Math.fma(_view10, _t53, _view11 * _t54)) * _t55_inv, _view13 + Math.fma(_view12, _t56, Math.fma(_view10, _t57, _view11 * _t58)) * _t59_inv, _view23 + Math.fma(_view22, _t28, Math.fma(_view20, _t29, _view21 * _t30)) * _t31_inv, _view23 + Math.fma(_view22, _t32, Math.fma(_view20, _t33, _view21 * _t34)) * _t35_inv, _view23 + Math.fma(_view22, _t36, Math.fma(_view20, _t37, _view21 * _t38)) * _t39_inv, _view23 + Math.fma(_view22, _t40, Math.fma(_view20, _t41, _view21 * _t42)) * _t43_inv, _view23 + Math.fma(_view22, _t44, Math.fma(_view20, _t45, _view21 * _t46)) * _t47_inv, _view23 + Math.fma(_view22, _t48, Math.fma(_view20, _t49, _view21 * _t50)) * _t51_inv);
    }

    /** Piece 6 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_rh_s6fcfc03c_5(float[] dest, int destOffset, float _view20, float _view21, float _view22, float _view23, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t156, float _t157, float _t158, float _t159, float _t160, float _t161, float _t162, float _t163, float _t164, float _t165, float _t166, float _t167, float _t168, float _t169, float _t170, float _t171, float _t172, float _t173, float _t174, float _t175, float _t176, float _t177) {
        float _t178 = _view23 + Math.fma(_view22, _t52, Math.fma(_view20, _t53, _view21 * _t54)) * _t55_inv;
        float _t179 = _view23 + Math.fma(_view22, _t56, Math.fma(_view20, _t57, _view21 * _t58)) * _t59_inv;
        float _t216 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        float _t217 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        float _t218 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171);
        float _t219 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171);
        return orthoCrop_no_rh_s6fcfc03c_6(dest, destOffset, _t216, _t217, _t218, _t219, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179), Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179), 1.0f / (_t216 - _t217), 1.0f / (_t218 - _t219));
    }

    /** Piece 7 of {@code orthoCrop_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_no_rh_s6fcfc03c_6(float[] dest, int destOffset, float _t216, float _t217, float _t218, float _t219, float _t220, float _t221, float _t222_inv, float _t223_inv) {
        float _t224_inv = 1.0f / (_t220 - _t221);
        dest[destOffset + 0] = _t222_inv + _t222_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t223_inv + _t223_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = -2.0f * _t224_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((_t217 + _t216) * _t222_inv);
        dest[destOffset + 13] = -((_t219 + _t218) * _t223_inv);
        dest[destOffset + 14] = -((-_t221 - _t220) * _t224_inv);
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] orthoCrop_no(float[] dest, int destOffset, float[] src, int srcOffset, float[] view, int viewOffset, float minZ, float maxZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.orthoCrop_no_lh(dest, destOffset, src, srcOffset, view, viewOffset, minZ, maxZ); }
            default -> { return Float4x4OpsKernelsArray.orthoCrop_no_rh(dest, destOffset, src, srcOffset, view, viewOffset, minZ, maxZ); }
        }
    }

    public static float[] orthoCrop_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] view, int viewOffset, float minZ, float maxZ) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t4 = _self23 + _self20;
        return orthoCrop_zo_lh_sc523096a_1(dest, destOffset, minZ, maxZ, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _self03, _self13, _self23, _self33, view[viewOffset + 0], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _self03 + _self00, _self13 + _self10, _self33 + _self30, _self23 - _self20 - _self21, _self03 - _self00 - _self01, _self13 - _self10 - _self11, _self33 - _self30 - _self31, _t4 - _self21);
    }

    /** Piece 2 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_lh_sc523096a_1(float[] dest, int destOffset, float minZ, float maxZ, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t4, float _t5, float _t6, float _t7, float _t12, float _t13, float _t14, float _t15, float _t16) {
        float _t17 = _t5 - _self01;
        float _t18 = _t6 - _self11;
        float _t19 = _t7 - _self31;
        float _t20 = _self23 + _self21 - _self20;
        float _t21 = _self03 + _self01 - _self00;
        float _t22 = _self13 + _self11 - _self10;
        float _t23 = _self33 + _self31 - _self30;
        float _t24 = _t4 + _self21;
        float _t25 = _t5 + _self01;
        float _t26 = _t6 + _self11;
        float _t27 = _t7 + _self31;
        return orthoCrop_zo_lh_sc523096a_2(dest, destOffset, maxZ, _self02, _self12, _self22, _self32, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, Math.fma(minZ, _self22, _t12), Math.fma(minZ, _self02, _t13), Math.fma(minZ, _self12, _t14), 1.0f / Math.fma(minZ, _self32, _t15), Math.fma(minZ, _self22, _t16), Math.fma(minZ, _self02, _t17), Math.fma(minZ, _self12, _t18), 1.0f / Math.fma(minZ, _self32, _t19), Math.fma(minZ, _self22, _t20), Math.fma(minZ, _self02, _t21), Math.fma(minZ, _self12, _t22), 1.0f / Math.fma(minZ, _self32, _t23), Math.fma(minZ, _self22, _t24), Math.fma(minZ, _self02, _t25), Math.fma(minZ, _self12, _t26), 1.0f / Math.fma(minZ, _self32, _t27), Math.fma(maxZ, _self22, _t12), Math.fma(maxZ, _self02, _t13));
    }

    /** Piece 3 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_lh_sc523096a_2(float[] dest, int destOffset, float maxZ, float _self02, float _self12, float _self22, float _self32, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31_inv, float _t32, float _t33, float _t34, float _t35_inv, float _t36, float _t37, float _t38, float _t39_inv, float _t40, float _t41, float _t42, float _t43_inv, float _t44, float _t45) {
        return orthoCrop_zo_lh_sc523096a_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, Math.fma(maxZ, _self12, _t14), 1.0f / Math.fma(maxZ, _self32, _t15), Math.fma(maxZ, _self22, _t16), Math.fma(maxZ, _self02, _t17), Math.fma(maxZ, _self12, _t18), 1.0f / Math.fma(maxZ, _self32, _t19), Math.fma(maxZ, _self22, _t20), Math.fma(maxZ, _self02, _t21), Math.fma(maxZ, _self12, _t22), 1.0f / Math.fma(maxZ, _self32, _t23), Math.fma(maxZ, _self22, _t24), Math.fma(maxZ, _self02, _t25), Math.fma(maxZ, _self12, _t26), 1.0f / Math.fma(maxZ, _self32, _t27), _view03 + Math.fma(_view02, _t28, Math.fma(_view00, _t29, _view01 * _t30)) * _t31_inv, _view03 + Math.fma(_view02, _t32, Math.fma(_view00, _t33, _view01 * _t34)) * _t35_inv, _view03 + Math.fma(_view02, _t36, Math.fma(_view00, _t37, _view01 * _t38)) * _t39_inv, _view03 + Math.fma(_view02, _t40, Math.fma(_view00, _t41, _view01 * _t42)) * _t43_inv);
    }

    /** Piece 4 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_lh_sc523096a_3(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t28, float _t29, float _t30, float _t31_inv, float _t32, float _t33, float _t34, float _t35_inv, float _t36, float _t37, float _t38, float _t39_inv, float _t40, float _t41, float _t42, float _t43_inv, float _t44, float _t45, float _t46, float _t47_inv, float _t48, float _t49, float _t50, float _t51_inv, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t156, float _t157, float _t158, float _t159) {
        return orthoCrop_zo_lh_sc523096a_4(dest, destOffset, _view10, _view20, _view11, _view21, _view12, _view22, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, _t46, _t47_inv, _t48, _t49, _t50, _t51_inv, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _view03 + Math.fma(_view02, _t44, Math.fma(_view00, _t45, _view01 * _t46)) * _t47_inv, _view03 + Math.fma(_view02, _t48, Math.fma(_view00, _t49, _view01 * _t50)) * _t51_inv, _view03 + Math.fma(_view02, _t52, Math.fma(_view00, _t53, _view01 * _t54)) * _t55_inv, _view03 + Math.fma(_view02, _t56, Math.fma(_view00, _t57, _view01 * _t58)) * _t59_inv, _view13 + Math.fma(_view12, _t28, Math.fma(_view10, _t29, _view11 * _t30)) * _t31_inv, _view13 + Math.fma(_view12, _t32, Math.fma(_view10, _t33, _view11 * _t34)) * _t35_inv, _view13 + Math.fma(_view12, _t36, Math.fma(_view10, _t37, _view11 * _t38)) * _t39_inv, _view13 + Math.fma(_view12, _t40, Math.fma(_view10, _t41, _view11 * _t42)) * _t43_inv);
    }

    /** Piece 5 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_lh_sc523096a_4(float[] dest, int destOffset, float _view10, float _view20, float _view11, float _view21, float _view12, float _view22, float _view13, float _view23, float _t28, float _t29, float _t30, float _t31_inv, float _t32, float _t33, float _t34, float _t35_inv, float _t36, float _t37, float _t38, float _t39_inv, float _t40, float _t41, float _t42, float _t43_inv, float _t44, float _t45, float _t46, float _t47_inv, float _t48, float _t49, float _t50, float _t51_inv, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t156, float _t157, float _t158, float _t159, float _t160, float _t161, float _t162, float _t163, float _t164, float _t165, float _t166, float _t167) {
        return orthoCrop_zo_lh_sc523096a_5(dest, destOffset, _view20, _view21, _view22, _view23, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _t160, _t161, _t162, _t163, _t164, _t165, _t166, _t167, _view13 + Math.fma(_view12, _t44, Math.fma(_view10, _t45, _view11 * _t46)) * _t47_inv, _view13 + Math.fma(_view12, _t48, Math.fma(_view10, _t49, _view11 * _t50)) * _t51_inv, _view13 + Math.fma(_view12, _t52, Math.fma(_view10, _t53, _view11 * _t54)) * _t55_inv, _view13 + Math.fma(_view12, _t56, Math.fma(_view10, _t57, _view11 * _t58)) * _t59_inv, _view23 + Math.fma(_view22, _t28, Math.fma(_view20, _t29, _view21 * _t30)) * _t31_inv, _view23 + Math.fma(_view22, _t32, Math.fma(_view20, _t33, _view21 * _t34)) * _t35_inv, _view23 + Math.fma(_view22, _t36, Math.fma(_view20, _t37, _view21 * _t38)) * _t39_inv, _view23 + Math.fma(_view22, _t40, Math.fma(_view20, _t41, _view21 * _t42)) * _t43_inv, _view23 + Math.fma(_view22, _t44, Math.fma(_view20, _t45, _view21 * _t46)) * _t47_inv, _view23 + Math.fma(_view22, _t48, Math.fma(_view20, _t49, _view21 * _t50)) * _t51_inv);
    }

    /** Piece 6 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_lh_sc523096a_5(float[] dest, int destOffset, float _view20, float _view21, float _view22, float _view23, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t156, float _t157, float _t158, float _t159, float _t160, float _t161, float _t162, float _t163, float _t164, float _t165, float _t166, float _t167, float _t168, float _t169, float _t170, float _t171, float _t172, float _t173, float _t174, float _t175, float _t176, float _t177) {
        float _t178 = _view23 + Math.fma(_view22, _t52, Math.fma(_view20, _t53, _view21 * _t54)) * _t55_inv;
        float _t179 = _view23 + Math.fma(_view22, _t56, Math.fma(_view20, _t57, _view21 * _t58)) * _t59_inv;
        float _t216 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        float _t217 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        float _t218 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171);
        float _t219 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171);
        return orthoCrop_zo_lh_sc523096a_6(dest, destOffset, _t172, _t173, _t174, _t175, _t176, _t177, _t178, _t179, _t216, _t217, _t218, _t219, Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179), 1.0f / (_t216 - _t217), 1.0f / (_t218 - _t219));
    }

    /** Piece 7 of {@code orthoCrop_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_lh_sc523096a_6(float[] dest, int destOffset, float _t172, float _t173, float _t174, float _t175, float _t176, float _t177, float _t178, float _t179, float _t216, float _t217, float _t218, float _t219, float _t221, float _t222_inv, float _t223_inv) {
        float _t224_inv = 1.0f / (Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179) - _t221);
        dest[destOffset + 0] = _t222_inv + _t222_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t223_inv + _t223_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = _t224_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((_t217 + _t216) * _t222_inv);
        dest[destOffset + 13] = -((_t219 + _t218) * _t223_inv);
        dest[destOffset + 14] = -(_t221 * _t224_inv);
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] orthoCrop_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] view, int viewOffset, float minZ, float maxZ) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t4 = _self23 + _self20;
        return orthoCrop_zo_rh_saa36a348_1(dest, destOffset, minZ, maxZ, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _self03, _self13, _self23, _self33, view[viewOffset + 0], view[viewOffset + 1], view[viewOffset + 2], view[viewOffset + 4], view[viewOffset + 5], view[viewOffset + 6], view[viewOffset + 8], view[viewOffset + 9], view[viewOffset + 10], view[viewOffset + 12], view[viewOffset + 13], view[viewOffset + 14], _t4, _self03 + _self00, _self13 + _self10, _self33 + _self30, _self23 - _self20 - _self21, _self03 - _self00 - _self01, _self13 - _self10 - _self11, _self33 - _self30 - _self31, _t4 - _self21);
    }

    /** Piece 2 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_rh_saa36a348_1(float[] dest, int destOffset, float minZ, float maxZ, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t4, float _t5, float _t6, float _t7, float _t12, float _t13, float _t14, float _t15, float _t16) {
        float _t17 = _t5 - _self01;
        float _t18 = _t6 - _self11;
        float _t19 = _t7 - _self31;
        float _t20 = _self23 + _self21 - _self20;
        float _t21 = _self03 + _self01 - _self00;
        float _t22 = _self13 + _self11 - _self10;
        float _t23 = _self33 + _self31 - _self30;
        float _t24 = _t4 + _self21;
        float _t25 = _t5 + _self01;
        float _t26 = _t6 + _self11;
        float _t27 = _t7 + _self31;
        return orthoCrop_zo_rh_saa36a348_2(dest, destOffset, maxZ, _self02, _self12, _self22, _self32, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t14, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t24, _t25, _t26, _t27, Math.fma(minZ, _self22, _t12), Math.fma(minZ, _self02, _t13), Math.fma(minZ, _self12, _t14), 1.0f / Math.fma(minZ, _self32, _t15), Math.fma(minZ, _self22, _t16), Math.fma(minZ, _self02, _t17), Math.fma(minZ, _self12, _t18), 1.0f / Math.fma(minZ, _self32, _t19), Math.fma(minZ, _self22, _t20), Math.fma(minZ, _self02, _t21), Math.fma(minZ, _self12, _t22), 1.0f / Math.fma(minZ, _self32, _t23), Math.fma(minZ, _self22, _t24), Math.fma(minZ, _self02, _t25), Math.fma(minZ, _self12, _t26), 1.0f / Math.fma(minZ, _self32, _t27), Math.fma(maxZ, _self22, _t12), Math.fma(maxZ, _self02, _t13));
    }

    /** Piece 3 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_rh_saa36a348_2(float[] dest, int destOffset, float maxZ, float _self02, float _self12, float _self22, float _self32, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31_inv, float _t32, float _t33, float _t34, float _t35_inv, float _t36, float _t37, float _t38, float _t39_inv, float _t40, float _t41, float _t42, float _t43_inv, float _t44, float _t45) {
        return orthoCrop_zo_rh_saa36a348_3(dest, destOffset, _view00, _view10, _view20, _view01, _view11, _view21, _view02, _view12, _view22, _view03, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, Math.fma(maxZ, _self12, _t14), 1.0f / Math.fma(maxZ, _self32, _t15), Math.fma(maxZ, _self22, _t16), Math.fma(maxZ, _self02, _t17), Math.fma(maxZ, _self12, _t18), 1.0f / Math.fma(maxZ, _self32, _t19), Math.fma(maxZ, _self22, _t20), Math.fma(maxZ, _self02, _t21), Math.fma(maxZ, _self12, _t22), 1.0f / Math.fma(maxZ, _self32, _t23), Math.fma(maxZ, _self22, _t24), Math.fma(maxZ, _self02, _t25), Math.fma(maxZ, _self12, _t26), 1.0f / Math.fma(maxZ, _self32, _t27), _view03 + Math.fma(_view02, _t28, Math.fma(_view00, _t29, _view01 * _t30)) * _t31_inv, _view03 + Math.fma(_view02, _t32, Math.fma(_view00, _t33, _view01 * _t34)) * _t35_inv, _view03 + Math.fma(_view02, _t36, Math.fma(_view00, _t37, _view01 * _t38)) * _t39_inv, _view03 + Math.fma(_view02, _t40, Math.fma(_view00, _t41, _view01 * _t42)) * _t43_inv);
    }

    /** Piece 4 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_rh_saa36a348_3(float[] dest, int destOffset, float _view00, float _view10, float _view20, float _view01, float _view11, float _view21, float _view02, float _view12, float _view22, float _view03, float _view13, float _view23, float _t28, float _t29, float _t30, float _t31_inv, float _t32, float _t33, float _t34, float _t35_inv, float _t36, float _t37, float _t38, float _t39_inv, float _t40, float _t41, float _t42, float _t43_inv, float _t44, float _t45, float _t46, float _t47_inv, float _t48, float _t49, float _t50, float _t51_inv, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t156, float _t157, float _t158, float _t159) {
        return orthoCrop_zo_rh_saa36a348_4(dest, destOffset, _view10, _view20, _view11, _view21, _view12, _view22, _view13, _view23, _t28, _t29, _t30, _t31_inv, _t32, _t33, _t34, _t35_inv, _t36, _t37, _t38, _t39_inv, _t40, _t41, _t42, _t43_inv, _t44, _t45, _t46, _t47_inv, _t48, _t49, _t50, _t51_inv, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _view03 + Math.fma(_view02, _t44, Math.fma(_view00, _t45, _view01 * _t46)) * _t47_inv, _view03 + Math.fma(_view02, _t48, Math.fma(_view00, _t49, _view01 * _t50)) * _t51_inv, _view03 + Math.fma(_view02, _t52, Math.fma(_view00, _t53, _view01 * _t54)) * _t55_inv, _view03 + Math.fma(_view02, _t56, Math.fma(_view00, _t57, _view01 * _t58)) * _t59_inv, _view13 + Math.fma(_view12, _t28, Math.fma(_view10, _t29, _view11 * _t30)) * _t31_inv, _view13 + Math.fma(_view12, _t32, Math.fma(_view10, _t33, _view11 * _t34)) * _t35_inv, _view13 + Math.fma(_view12, _t36, Math.fma(_view10, _t37, _view11 * _t38)) * _t39_inv, _view13 + Math.fma(_view12, _t40, Math.fma(_view10, _t41, _view11 * _t42)) * _t43_inv);
    }

    /** Piece 5 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_rh_saa36a348_4(float[] dest, int destOffset, float _view10, float _view20, float _view11, float _view21, float _view12, float _view22, float _view13, float _view23, float _t28, float _t29, float _t30, float _t31_inv, float _t32, float _t33, float _t34, float _t35_inv, float _t36, float _t37, float _t38, float _t39_inv, float _t40, float _t41, float _t42, float _t43_inv, float _t44, float _t45, float _t46, float _t47_inv, float _t48, float _t49, float _t50, float _t51_inv, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t156, float _t157, float _t158, float _t159, float _t160, float _t161, float _t162, float _t163, float _t164, float _t165, float _t166, float _t167) {
        return orthoCrop_zo_rh_saa36a348_5(dest, destOffset, _view20, _view21, _view22, _view23, _t52, _t53, _t54, _t55_inv, _t56, _t57, _t58, _t59_inv, _t156, _t157, _t158, _t159, _t160, _t161, _t162, _t163, _t164, _t165, _t166, _t167, _view13 + Math.fma(_view12, _t44, Math.fma(_view10, _t45, _view11 * _t46)) * _t47_inv, _view13 + Math.fma(_view12, _t48, Math.fma(_view10, _t49, _view11 * _t50)) * _t51_inv, _view13 + Math.fma(_view12, _t52, Math.fma(_view10, _t53, _view11 * _t54)) * _t55_inv, _view13 + Math.fma(_view12, _t56, Math.fma(_view10, _t57, _view11 * _t58)) * _t59_inv, _view23 + Math.fma(_view22, _t28, Math.fma(_view20, _t29, _view21 * _t30)) * _t31_inv, _view23 + Math.fma(_view22, _t32, Math.fma(_view20, _t33, _view21 * _t34)) * _t35_inv, _view23 + Math.fma(_view22, _t36, Math.fma(_view20, _t37, _view21 * _t38)) * _t39_inv, _view23 + Math.fma(_view22, _t40, Math.fma(_view20, _t41, _view21 * _t42)) * _t43_inv, _view23 + Math.fma(_view22, _t44, Math.fma(_view20, _t45, _view21 * _t46)) * _t47_inv, _view23 + Math.fma(_view22, _t48, Math.fma(_view20, _t49, _view21 * _t50)) * _t51_inv);
    }

    /** Piece 6 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_rh_saa36a348_5(float[] dest, int destOffset, float _view20, float _view21, float _view22, float _view23, float _t52, float _t53, float _t54, float _t55_inv, float _t56, float _t57, float _t58, float _t59_inv, float _t156, float _t157, float _t158, float _t159, float _t160, float _t161, float _t162, float _t163, float _t164, float _t165, float _t166, float _t167, float _t168, float _t169, float _t170, float _t171, float _t172, float _t173, float _t174, float _t175, float _t176, float _t177) {
        float _t178 = _view23 + Math.fma(_view22, _t52, Math.fma(_view20, _t53, _view21 * _t54)) * _t55_inv;
        float _t179 = _view23 + Math.fma(_view22, _t56, Math.fma(_view20, _t57, _view21 * _t58)) * _t59_inv;
        float _t216 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        float _t217 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t156, _t157), _t158), _t159), _t160), _t161), _t162), _t163);
        float _t218 = Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171);
        float _t219 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t164, _t165), _t166), _t167), _t168), _t169), _t170), _t171);
        return orthoCrop_zo_rh_saa36a348_6(dest, destOffset, _t172, _t173, _t174, _t175, _t176, _t177, _t178, _t179, _t216, _t217, _t218, _t219, Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(Math.max(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179), 1.0f / (_t216 - _t217), 1.0f / (_t218 - _t219));
    }

    /** Piece 7 of {@code orthoCrop_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] orthoCrop_zo_rh_saa36a348_6(float[] dest, int destOffset, float _t172, float _t173, float _t174, float _t175, float _t176, float _t177, float _t178, float _t179, float _t216, float _t217, float _t218, float _t219, float _t220, float _t222_inv, float _t223_inv) {
        float _t224_inv = 1.0f / (_t220 - Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(_t172, _t173), _t174), _t175), _t176), _t177), _t178), _t179));
        dest[destOffset + 0] = _t222_inv + _t222_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = _t223_inv + _t223_inv;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 0.0f;
        dest[destOffset + 9] = 0.0f;
        dest[destOffset + 10] = -_t224_inv;
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = -((_t217 + _t216) * _t222_inv);
        dest[destOffset + 13] = -((_t219 + _t218) * _t223_inv);
        dest[destOffset + 14] = _t220 * _t224_inv;
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    public static float[] orthoCrop_zo(float[] dest, int destOffset, float[] src, int srcOffset, float[] view, int viewOffset, float minZ, float maxZ, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.orthoCrop_zo_lh(dest, destOffset, src, srcOffset, view, viewOffset, minZ, maxZ); }
            default -> { return Float4x4OpsKernelsArray.orthoCrop_zo_rh(dest, destOffset, src, srcOffset, view, viewOffset, minZ, maxZ); }
        }
    }

    public static float[] perspective_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t6 = (float) Math.tan(0.5f * fovy);
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _sp0 = near + near;
        float _t2_inv = 1.0f / (near - far);
        float _t9_inv = 1.0f / (aspect * _t6);
        float _t15, _t16;
        if (far == Float.POSITIVE_INFINITY) {
            _t15 = 1.0f;
            _t16 = -_sp0;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _t15 = -1.0f;
                _t16 = far + far;
            } else {
                _t15 = -((far + near) * _t2_inv);
                _t16 = _sp0 * far * _t2_inv;
            }
        }
        dest[destOffset + 0] = _self00 * _t9_inv;
        dest[destOffset + 1] = _self10 * _t9_inv;
        dest[destOffset + 2] = _self20 * _t9_inv;
        dest[destOffset + 3] = _self30 * _t9_inv;
        return perspective_no_lh_s9596d359_1(dest, destOffset, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, 1.0f / _t6, _t15, _t16);
    }

    /** Piece 2 of {@code perspective_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] perspective_no_lh_s9596d359_1(float[] dest, int destOffset, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t6_inv, float _t15, float _t16) {
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

    public static float[] perspective_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t6 = (float) Math.tan(0.5f * fovy);
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _sp0 = near + near;
        float _t2_inv = 1.0f / (near - far);
        float _t9_inv = 1.0f / (aspect * _t6);
        float _t13, _t15;
        if (far == Float.POSITIVE_INFINITY) {
            _t13 = -1.0f;
            _t15 = -_sp0;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _t13 = 1.0f;
                _t15 = far + far;
            } else {
                _t13 = (far + near) * _t2_inv;
                _t15 = _sp0 * far * _t2_inv;
            }
        }
        dest[destOffset + 0] = _self00 * _t9_inv;
        dest[destOffset + 1] = _self10 * _t9_inv;
        dest[destOffset + 2] = _self20 * _t9_inv;
        dest[destOffset + 3] = _self30 * _t9_inv;
        return perspective_no_rh_sb5382037_1(dest, destOffset, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, 1.0f / _t6, _t13, _t15);
    }

    /** Piece 2 of {@code perspective_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] perspective_no_rh_sb5382037_1(float[] dest, int destOffset, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t6_inv, float _t13, float _t15) {
        dest[destOffset + 4] = _self01 * _t6_inv;
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

    public static float[] perspective_no(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.perspective_no_lh(dest, destOffset, src, srcOffset, fovy, aspect, near, far); }
            default -> { return Float4x4OpsKernelsArray.perspective_no_rh(dest, destOffset, src, srcOffset, fovy, aspect, near, far); }
        }
    }

    public static float[] perspective_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t3 = (float) Math.tan(0.5f * fovy);
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _sp0 = far / (near - far);
        float _t3_inv = 1.0f / _t3;
        float _t5_inv = 1.0f / (aspect * _t3);
        float _t10, _t11;
        if (far == Float.POSITIVE_INFINITY) {
            _t10 = 1.0f;
            _t11 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _t10 = 0.0f;
                _t11 = far;
            } else {
                _t10 = -_sp0;
                _t11 = _sp0 * near;
            }
        }
        dest[destOffset + 0] = _self00 * _t5_inv;
        dest[destOffset + 1] = _self10 * _t5_inv;
        dest[destOffset + 2] = _self20 * _t5_inv;
        dest[destOffset + 3] = _self30 * _t5_inv;
        dest[destOffset + 4] = _self01 * _t3_inv;
        dest[destOffset + 5] = _self11 * _t3_inv;
        return perspective_zo_lh_s72f9a395_1(dest, destOffset, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t3_inv, _t10, _t11);
    }

    /** Piece 2 of {@code perspective_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] perspective_zo_lh_s72f9a395_1(float[] dest, int destOffset, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t3_inv, float _t10, float _t11) {
        dest[destOffset + 6] = _self21 * _t3_inv;
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

    public static float[] perspective_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t3 = (float) Math.tan(0.5f * fovy);
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _sp0 = far / (near - far);
        float _t3_inv = 1.0f / _t3;
        float _t5_inv = 1.0f / (aspect * _t3);
        float _t9, _t10;
        if (far == Float.POSITIVE_INFINITY) {
            _t9 = -1.0f;
            _t10 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _t9 = 0.0f;
                _t10 = far;
            } else {
                _t9 = _sp0;
                _t10 = _sp0 * near;
            }
        }
        dest[destOffset + 0] = _self00 * _t5_inv;
        dest[destOffset + 1] = _self10 * _t5_inv;
        dest[destOffset + 2] = _self20 * _t5_inv;
        dest[destOffset + 3] = _self30 * _t5_inv;
        dest[destOffset + 4] = _self01 * _t3_inv;
        dest[destOffset + 5] = _self11 * _t3_inv;
        return perspective_zo_rh_s87d220fb_1(dest, destOffset, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t3_inv, _t9, _t10);
    }

    /** Piece 2 of {@code perspective_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] perspective_zo_rh_s87d220fb_1(float[] dest, int destOffset, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _t3_inv, float _t9, float _t10) {
        dest[destOffset + 6] = _self21 * _t3_inv;
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

    public static float[] perspective_zo(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.perspective_zo_lh(dest, destOffset, src, srcOffset, fovy, aspect, near, far); }
            default -> { return Float4x4OpsKernelsArray.perspective_zo_rh(dest, destOffset, src, srcOffset, fovy, aspect, near, far); }
        }
    }

    public static float[] perspectiveFovRange_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _sp3 = near + near;
        float _t3_inv = 1.0f / (near - far);
        float _t8 = _t0 - _t1;
        float _t8_inv = 1.0f / _t8;
        float _t17, _t18;
        if (far == Float.POSITIVE_INFINITY) {
            _t17 = 1.0f;
            _t18 = -_sp3;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _t17 = -1.0f;
                _t18 = far + far;
            } else {
                _t17 = -((far + near) * _t3_inv);
                _t18 = _sp3 * far * _t3_inv;
            }
        }
        return perspectiveFovRange_no_lh_s140c7657_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t8_inv + _t8_inv, _t8_inv * (_t0 + _t1), 2.0f / (aspect * _t8), _t17, _t18);
    }

    /** Piece 2 of {@code perspectiveFovRange_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] perspectiveFovRange_no_lh_s140c7657_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp1, float _sp2, float _sp0, float _t17, float _t18) {
        dest[destOffset + 0] = _sp0 * _self00;
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

    public static float[] perspectiveFovRange_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _sp3 = near + near;
        float _t3_inv = 1.0f / (near - far);
        float _t8 = _t0 - _t1;
        float _t8_inv = 1.0f / _t8;
        float _sp0 = 2.0f / (aspect * _t8);
        float _t15, _t17;
        if (far == Float.POSITIVE_INFINITY) {
            _t15 = -1.0f;
            _t17 = -_sp3;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _t15 = 1.0f;
                _t17 = far + far;
            } else {
                _t15 = (far + near) * _t3_inv;
                _t17 = _sp3 * far * _t3_inv;
            }
        }
        dest[destOffset + 0] = _sp0 * _self00;
        return perspectiveFovRange_no_rh_sfa09b38d_1(dest, destOffset, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t8_inv + _t8_inv, _t8_inv * (_t0 + _t1), _sp0, _t15, _t17);
    }

    /** Piece 2 of {@code perspectiveFovRange_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] perspectiveFovRange_no_rh_sfa09b38d_1(float[] dest, int destOffset, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp1, float _sp2, float _sp0, float _t15, float _t17) {
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

    public static float[] perspectiveFovRange_no(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.perspectiveFovRange_no_lh(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far); }
            default -> { return Float4x4OpsKernelsArray.perspectiveFovRange_no_rh(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far); }
        }
    }

    public static float[] perspectiveFovRange_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _sp3 = far / (near - far);
        float _t4 = _t0 - _t1;
        float _t4_inv = 1.0f / _t4;
        float _sp0 = 2.0f / (aspect * _t4);
        float _t12, _t13;
        if (far == Float.POSITIVE_INFINITY) {
            _t12 = 1.0f;
            _t13 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _t12 = 0.0f;
                _t13 = far;
            } else {
                _t12 = -_sp3;
                _t13 = _sp3 * near;
            }
        }
        dest[destOffset + 0] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        return perspectiveFovRange_zo_lh_s5f88ea43_1(dest, destOffset, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t4_inv + _t4_inv, _t4_inv * (_t0 + _t1), _sp0, _t12, _t13);
    }

    /** Piece 2 of {@code perspectiveFovRange_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] perspectiveFovRange_zo_lh_s5f88ea43_1(float[] dest, int destOffset, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp1, float _sp2, float _sp0, float _t12, float _t13) {
        dest[destOffset + 3] = _sp0 * _self30;
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

    public static float[] perspectiveFovRange_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _sp3 = far / (near - far);
        float _t4 = _t0 - _t1;
        float _t4_inv = 1.0f / _t4;
        float _sp0 = 2.0f / (aspect * _t4);
        float _t11, _t12;
        if (far == Float.POSITIVE_INFINITY) {
            _t11 = -1.0f;
            _t12 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _t11 = 0.0f;
                _t12 = far;
            } else {
                _t11 = _sp3;
                _t12 = _sp3 * near;
            }
        }
        dest[destOffset + 0] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        return perspectiveFovRange_zo_rh_s50778331_1(dest, destOffset, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32, _self03, _self13, _self23, _self33, _t4_inv + _t4_inv, _t4_inv * (_t0 + _t1), _sp0, _t11, _t12);
    }

    /** Piece 2 of {@code perspectiveFovRange_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] perspectiveFovRange_zo_rh_s50778331_1(float[] dest, int destOffset, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp1, float _sp2, float _sp0, float _t11, float _t12) {
        dest[destOffset + 3] = _sp0 * _self30;
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

    public static float[] perspectiveFovRange_zo(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.perspectiveFovRange_zo_lh(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far); }
            default -> { return Float4x4OpsKernelsArray.perspectiveFovRange_zo_rh(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far); }
        }
    }

    public static float[] perspectiveFrustumSlice_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float near, float far) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self33 = src[srcOffset + 15];
        float _sp0 = near + near;
        float _t0_inv = 1.0f / (near - far);
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = -1.0f;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = -((far + near) * _t0_inv);
                dest[destOffset + 14] = _sp0 * far * _t0_inv;
            }
        }
        dest[destOffset + 0] = _self00;
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

    public static float[] perspectiveFrustumSlice_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float near, float far) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self33 = src[srcOffset + 15];
        float _sp0 = near + near;
        float _t0_inv = 1.0f / (near - far);
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -_sp0;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 1.0f;
                dest[destOffset + 14] = far + far;
            } else {
                dest[destOffset + 10] = (far + near) * _t0_inv;
                dest[destOffset + 14] = _sp0 * far * _t0_inv;
            }
        }
        dest[destOffset + 0] = _self00;
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

    public static float[] perspectiveFrustumSlice_no(float[] dest, int destOffset, float[] src, int srcOffset, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.perspectiveFrustumSlice_no_lh(dest, destOffset, src, srcOffset, near, far); }
            default -> { return Float4x4OpsKernelsArray.perspectiveFrustumSlice_no_rh(dest, destOffset, src, srcOffset, near, far); }
        }
    }

    public static float[] perspectiveFrustumSlice_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float near, float far) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self33 = src[srcOffset + 15];
        float _sp0 = far / (near - far);
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = 1.0f;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = -_sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset + 0] = _self00;
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

    public static float[] perspectiveFrustumSlice_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float near, float far) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self33 = src[srcOffset + 15];
        float _sp0 = far / (near - far);
        if (far == Float.POSITIVE_INFINITY) {
            dest[destOffset + 10] = -1.0f;
            dest[destOffset + 14] = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                dest[destOffset + 10] = 0.0f;
                dest[destOffset + 14] = far;
            } else {
                dest[destOffset + 10] = _sp0;
                dest[destOffset + 14] = _sp0 * near;
            }
        }
        dest[destOffset + 0] = _self00;
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

    public static float[] perspectiveFrustumSlice_zo(float[] dest, int destOffset, float[] src, int srcOffset, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.perspectiveFrustumSlice_zo_lh(dest, destOffset, src, srcOffset, near, far); }
            default -> { return Float4x4OpsKernelsArray.perspectiveFrustumSlice_zo_rh(dest, destOffset, src, srcOffset, near, far); }
        }
    }

    public static float[] perspectiveOffCenterFov_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = (float) Math.tan(angleRight);
        float _t1 = (float) Math.tan(angleLeft);
        float _t2 = (float) Math.tan(angleUp);
        float _t3 = (float) Math.tan(angleDown);
        float _sp4 = near + near;
        float _t5_inv = 1.0f / (near - far);
        float _t10_inv = 1.0f / (_t0 - _t1);
        float _t11_inv = 1.0f / (_t2 - _t3);
        float _t20, _t21;
        if (far == Float.POSITIVE_INFINITY) {
            _t20 = 1.0f;
            _t21 = -_sp4;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _t20 = -1.0f;
                _t21 = far + far;
            } else {
                _t20 = -((far + near) * _t5_inv);
                _t21 = _sp4 * far * _t5_inv;
            }
        }
        return perspectiveOffCenterFov_no_lh_sfe9928bc_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t10_inv + _t10_inv, _t11_inv + _t11_inv, _t10_inv * (_t1 + _t0), _t11_inv * (_t3 + _t2), _t20, _t21);
    }

    /** Piece 2 of {@code perspectiveOffCenterFov_no_lh}, split to fit the inline budget; reached only through it. */
    private static float[] perspectiveOffCenterFov_no_lh_sfe9928bc_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp0, float _sp1, float _sp2, float _sp3, float _t20, float _t21) {
        dest[destOffset + 0] = _sp0 * _self00;
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

    public static float[] perspectiveOffCenterFov_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = (float) Math.tan(angleRight);
        float _t1 = (float) Math.tan(angleLeft);
        float _t2 = (float) Math.tan(angleUp);
        float _t3 = (float) Math.tan(angleDown);
        float _sp4 = near + near;
        float _t5_inv = 1.0f / (near - far);
        float _t10_inv = 1.0f / (_t0 - _t1);
        float _t11_inv = 1.0f / (_t2 - _t3);
        float _t18, _t20;
        if (far == Float.POSITIVE_INFINITY) {
            _t18 = -1.0f;
            _t20 = -_sp4;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _t18 = 1.0f;
                _t20 = far + far;
            } else {
                _t18 = (far + near) * _t5_inv;
                _t20 = _sp4 * far * _t5_inv;
            }
        }
        return perspectiveOffCenterFov_no_rh_s21a42086_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t10_inv + _t10_inv, _t11_inv + _t11_inv, _t10_inv * (_t1 + _t0), _t11_inv * (_t3 + _t2), _t18, _t20);
    }

    /** Piece 2 of {@code perspectiveOffCenterFov_no_rh}, split to fit the inline budget; reached only through it. */
    private static float[] perspectiveOffCenterFov_no_rh_s21a42086_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp0, float _sp1, float _sp2, float _sp3, float _t18, float _t20) {
        dest[destOffset + 0] = _sp0 * _self00;
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

    public static float[] perspectiveOffCenterFov_no(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.perspectiveOffCenterFov_no_lh(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
            default -> { return Float4x4OpsKernelsArray.perspectiveOffCenterFov_no_rh(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
        }
    }

    public static float[] perspectiveOffCenterFov_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = (float) Math.tan(angleRight);
        float _t1 = (float) Math.tan(angleLeft);
        float _t2 = (float) Math.tan(angleUp);
        float _t3 = (float) Math.tan(angleDown);
        float _sp4 = far / (near - far);
        float _t6_inv = 1.0f / (_t0 - _t1);
        float _t7_inv = 1.0f / (_t2 - _t3);
        float _t15, _t16;
        if (far == Float.POSITIVE_INFINITY) {
            _t15 = 1.0f;
            _t16 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _t15 = 0.0f;
                _t16 = far;
            } else {
                _t15 = -_sp4;
                _t16 = _sp4 * near;
            }
        }
        return perspectiveOffCenterFov_zo_lh_sb7b87f88_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t6_inv + _t6_inv, _t7_inv + _t7_inv, _t6_inv * (_t1 + _t0), _t7_inv * (_t3 + _t2), _t15, _t16);
    }

    /** Piece 2 of {@code perspectiveOffCenterFov_zo_lh}, split to fit the inline budget; reached only through it. */
    private static float[] perspectiveOffCenterFov_zo_lh_sb7b87f88_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp0, float _sp1, float _sp2, float _sp3, float _t15, float _t16) {
        dest[destOffset + 0] = _sp0 * _self00;
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

    public static float[] perspectiveOffCenterFov_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = (float) Math.tan(angleRight);
        float _t1 = (float) Math.tan(angleLeft);
        float _t2 = (float) Math.tan(angleUp);
        float _t3 = (float) Math.tan(angleDown);
        float _sp4 = far / (near - far);
        float _t6_inv = 1.0f / (_t0 - _t1);
        float _t7_inv = 1.0f / (_t2 - _t3);
        float _t14, _t15;
        if (far == Float.POSITIVE_INFINITY) {
            _t14 = -1.0f;
            _t15 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _t14 = 0.0f;
                _t15 = far;
            } else {
                _t14 = _sp4;
                _t15 = _sp4 * near;
            }
        }
        return perspectiveOffCenterFov_zo_rh_s634690ba_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], src[srcOffset + 15], _t6_inv + _t6_inv, _t7_inv + _t7_inv, _t6_inv * (_t1 + _t0), _t7_inv * (_t3 + _t2), _t14, _t15);
    }

    /** Piece 2 of {@code perspectiveOffCenterFov_zo_rh}, split to fit the inline budget; reached only through it. */
    private static float[] perspectiveOffCenterFov_zo_rh_s634690ba_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32, float _self03, float _self13, float _self23, float _self33, float _sp0, float _sp1, float _sp2, float _sp3, float _t14, float _t15) {
        dest[destOffset + 0] = _sp0 * _self00;
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

    public static float[] perspectiveOffCenterFov_zo(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far, Handedness handedness) {
        switch (handedness) {
            case LEFT_HANDED -> { return Float4x4OpsKernelsArray.perspectiveOffCenterFov_zo_lh(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
            default -> { return Float4x4OpsKernelsArray.perspectiveOffCenterFov_zo_rh(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far); }
        }
    }

    public static float[] project_no(float[] dest, int destOffset, float[] src, int srcOffset, float objX, float objY, float objZ, float viewportX, float viewportY, float viewportZ, float viewportW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t2_inv = 1.0f / Math.fma(objX, _self30, Math.fma(objY, _self31, Math.fma(objZ, _self32, _self33)));
        dest[destOffset + 0] = Math.fma(0.5f, viewportZ * (1.0f + Math.fma(objX, _self00, Math.fma(objY, _self01, Math.fma(objZ, _self02, _self03))) * _t2_inv), viewportX);
        dest[destOffset + 1] = Math.fma(0.5f, viewportW * (1.0f + Math.fma(objX, _self10, Math.fma(objY, _self11, Math.fma(objZ, _self12, _self13))) * _t2_inv), viewportY);
        dest[destOffset + 2] = 0.5f * (1.0f + Math.fma(objX, _self20, Math.fma(objY, _self21, Math.fma(objZ, _self22, _self23))) * _t2_inv);
        return dest;
    }

    public static float[] project_zo(float[] dest, int destOffset, float[] src, int srcOffset, float objX, float objY, float objZ, float viewportX, float viewportY, float viewportZ, float viewportW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t2_inv = 1.0f / Math.fma(objX, _self30, Math.fma(objY, _self31, Math.fma(objZ, _self32, _self33)));
        dest[destOffset + 0] = Math.fma(0.5f, viewportZ * (1.0f + Math.fma(objX, _self00, Math.fma(objY, _self01, Math.fma(objZ, _self02, _self03))) * _t2_inv), viewportX);
        dest[destOffset + 1] = Math.fma(0.5f, viewportW * (1.0f + Math.fma(objX, _self10, Math.fma(objY, _self11, Math.fma(objZ, _self12, _self13))) * _t2_inv), viewportY);
        dest[destOffset + 2] = Math.fma(objX, _self20, Math.fma(objY, _self21, Math.fma(objZ, _self22, _self23))) * _t2_inv;
        return dest;
    }

    public static float[] project_no(float[] dest, int destOffset, float[] src, int srcOffset, float[] obj, int objOffset, float[] viewport, int viewportOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _objx = obj[objOffset + 0];
        float _objy = obj[objOffset + 1];
        float _objz = obj[objOffset + 2];
        float _viewportx = viewport[viewportOffset + 0];
        float _viewporty = viewport[viewportOffset + 1];
        float _viewportz = viewport[viewportOffset + 2];
        float _viewportw = viewport[viewportOffset + 3];
        float _t2_inv = 1.0f / Math.fma(_objx, _self30, Math.fma(_objy, _self31, Math.fma(_objz, _self32, _self33)));
        dest[destOffset + 0] = Math.fma(0.5f, _viewportz * (1.0f + Math.fma(_objx, _self00, Math.fma(_objy, _self01, Math.fma(_objz, _self02, _self03))) * _t2_inv), _viewportx);
        return project_no_s5099c3a_1(dest, destOffset, _self10, _self20, _self11, _self21, _self12, _self22, _self13, _self23, _objx, _objy, _objz, _viewporty, _viewportw, _t2_inv);
    }

    /** Piece 2 of {@code project_no}, split to fit the inline budget; reached only through it. */
    private static float[] project_no_s5099c3a_1(float[] dest, int destOffset, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _self13, float _self23, float _objx, float _objy, float _objz, float _viewporty, float _viewportw, float _t2_inv) {
        dest[destOffset + 1] = Math.fma(0.5f, _viewportw * (1.0f + Math.fma(_objx, _self10, Math.fma(_objy, _self11, Math.fma(_objz, _self12, _self13))) * _t2_inv), _viewporty);
        dest[destOffset + 2] = 0.5f * (1.0f + Math.fma(_objx, _self20, Math.fma(_objy, _self21, Math.fma(_objz, _self22, _self23))) * _t2_inv);
        return dest;
    }

    public static float[] project_zo(float[] dest, int destOffset, float[] src, int srcOffset, float[] obj, int objOffset, float[] viewport, int viewportOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _objx = obj[objOffset + 0];
        float _objy = obj[objOffset + 1];
        float _objz = obj[objOffset + 2];
        float _viewportx = viewport[viewportOffset + 0];
        float _viewporty = viewport[viewportOffset + 1];
        float _viewportz = viewport[viewportOffset + 2];
        float _viewportw = viewport[viewportOffset + 3];
        float _t2_inv = 1.0f / Math.fma(_objx, _self30, Math.fma(_objy, _self31, Math.fma(_objz, _self32, _self33)));
        dest[destOffset + 0] = Math.fma(0.5f, _viewportz * (1.0f + Math.fma(_objx, _self00, Math.fma(_objy, _self01, Math.fma(_objz, _self02, _self03))) * _t2_inv), _viewportx);
        return project_zo_s153b7fe_1(dest, destOffset, _self10, _self20, _self11, _self21, _self12, _self22, _self13, _self23, _objx, _objy, _objz, _viewporty, _viewportw, _t2_inv);
    }

    /** Piece 2 of {@code project_zo}, split to fit the inline budget; reached only through it. */
    private static float[] project_zo_s153b7fe_1(float[] dest, int destOffset, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _self13, float _self23, float _objx, float _objy, float _objz, float _viewporty, float _viewportw, float _t2_inv) {
        dest[destOffset + 1] = Math.fma(0.5f, _viewportw * (1.0f + Math.fma(_objx, _self10, Math.fma(_objy, _self11, Math.fma(_objz, _self12, _self13))) * _t2_inv), _viewporty);
        dest[destOffset + 2] = Math.fma(_objx, _self20, Math.fma(_objy, _self21, Math.fma(_objz, _self22, _self23))) * _t2_inv;
        return dest;
    }

    public static float[] unproject_no(float[] dest, int destOffset, float[] src, int srcOffset, float winCoordsX, float winCoordsY, float winCoordsZ, float viewportX, float viewportY, float viewportZ, float viewportW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = -_self33;
        float _t1 = -_self31;
        float _t2 = -_self32;
        float _t3 = -_self30;
        float _t6 = Math.fma(2.0f, winCoordsZ, -1.0f);
        float _t11 = 2.0f * (winCoordsX - viewportX) / viewportZ - 1.0f;
        float _t12 = 2.0f * (winCoordsY - viewportY) / viewportW - 1.0f;
        return unproject_no_s58689dc_1(dest, destOffset, Math.fma(_t2, _t6, _self22), Math.fma(_t1, _t6, _self21), Math.fma(_t0, _t6, _self23), Math.fma(_t3, _t6, _self20), Math.fma(_t0, _t11, _self03), Math.fma(_t1, _t12, _self11), Math.fma(_t2, _t12, _self12), Math.fma(_t1, _t11, _self01), Math.fma(_t0, _t12, _self13), Math.fma(_t2, _t11, _self02), Math.fma(_t3, _t12, _self10), Math.fma(_t3, _t11, _self00));
    }

    /** Piece 2 of {@code unproject_no}, split to fit the inline budget; reached only through it. */
    private static float[] unproject_no_s58689dc_1(float[] dest, int destOffset, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
        float _t37 = Math.fma(_t18, _t13, -(_t19 * _t14));
        float _t38 = Math.fma(_t19, _t15, -(_t21 * _t13));
        float _t39 = Math.fma(_t18, _t15, -(_t21 * _t14));
        float _t40 = Math.fma(_t23, _t14, -(_t18 * _t16));
        float _t41 = Math.fma(_t23, _t13, -(_t19 * _t16));
        float _t42 = Math.fma(_t23, _t15, -(_t21 * _t16));
        float _t46_inv = 1.0f / Math.fma(_t22, _t40, Math.fma(_t24, _t37, -(_t20 * _t41)));
        dest[destOffset + 0] = -(Math.fma(_t17, _t37, Math.fma(_t20, _t38, -(_t22 * _t39))) * _t46_inv);
        dest[destOffset + 1] = Math.fma(_t17, _t41, Math.fma(_t24, _t38, -(_t22 * _t42))) * _t46_inv;
        dest[destOffset + 2] = -(Math.fma(_t17, _t40, Math.fma(_t24, _t39, -(_t20 * _t42))) * _t46_inv);
        return dest;
    }

    public static float[] unproject_zo(float[] dest, int destOffset, float[] src, int srcOffset, float winCoordsX, float winCoordsY, float winCoordsZ, float viewportX, float viewportY, float viewportZ, float viewportW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = -_self33;
        float _t1 = -_self31;
        float _t2 = -_self32;
        float _t3 = -_self30;
        float _t14 = 2.0f * (winCoordsX - viewportX) / viewportZ - 1.0f;
        float _t15 = 2.0f * (winCoordsY - viewportY) / viewportW - 1.0f;
        return unproject_zo_s7dedd9f0_1(dest, destOffset, Math.fma(_t2, winCoordsZ, _self22), Math.fma(_t1, winCoordsZ, _self21), Math.fma(_t0, winCoordsZ, _self23), Math.fma(_t3, winCoordsZ, _self20), Math.fma(_t0, _t14, _self03), Math.fma(_t1, _t15, _self11), Math.fma(_t2, _t15, _self12), Math.fma(_t1, _t14, _self01), Math.fma(_t0, _t15, _self13), Math.fma(_t2, _t14, _self02), Math.fma(_t3, _t15, _self10), Math.fma(_t3, _t14, _self00));
    }

    /** Piece 2 of {@code unproject_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unproject_zo_s7dedd9f0_1(float[] dest, int destOffset, float _t8, float _t9, float _t10, float _t11, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23) {
        float _t36 = Math.fma(_t17, _t8, -(_t18 * _t9));
        float _t37 = Math.fma(_t18, _t10, -(_t20 * _t8));
        float _t38 = Math.fma(_t17, _t10, -(_t20 * _t9));
        float _t39 = Math.fma(_t22, _t9, -(_t17 * _t11));
        float _t40 = Math.fma(_t22, _t8, -(_t18 * _t11));
        float _t41 = Math.fma(_t22, _t10, -(_t20 * _t11));
        float _t45_inv = 1.0f / Math.fma(_t21, _t39, Math.fma(_t23, _t36, -(_t19 * _t40)));
        dest[destOffset + 0] = -(Math.fma(_t16, _t36, Math.fma(_t19, _t37, -(_t21 * _t38))) * _t45_inv);
        dest[destOffset + 1] = Math.fma(_t16, _t40, Math.fma(_t23, _t37, -(_t21 * _t41))) * _t45_inv;
        dest[destOffset + 2] = -(Math.fma(_t16, _t39, Math.fma(_t23, _t38, -(_t19 * _t41))) * _t45_inv);
        return dest;
    }

    public static float[] unproject_no(float[] dest, int destOffset, float[] src, int srcOffset, float[] winCoords, int winCoordsOffset, float[] viewport, int viewportOffset) {
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _winCoordsx = winCoords[winCoordsOffset + 0];
        float _winCoordsy = winCoords[winCoordsOffset + 1];
        float _winCoordsz = winCoords[winCoordsOffset + 2];
        float _viewportx = viewport[viewportOffset + 0];
        float _viewporty = viewport[viewportOffset + 1];
        float _viewportz = viewport[viewportOffset + 2];
        float _viewportw = viewport[viewportOffset + 3];
        float _t0 = -_self33;
        float _t1 = -_self31;
        float _t2 = -_self32;
        float _t3 = -_self30;
        float _t6 = Math.fma(2.0f, _winCoordsz, -1.0f);
        float _t11 = 2.0f * (_winCoordsx - _viewportx) / _viewportz - 1.0f;
        float _t12 = 2.0f * (_winCoordsy - _viewporty) / _viewportw - 1.0f;
        return unproject_no_sa0e6ffe5_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 4], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 13], _t0, _t1, _t2, _t3, _t11, _t12, Math.fma(_t2, _t6, _self22), Math.fma(_t1, _t6, _self21), Math.fma(_t0, _t6, _self23), Math.fma(_t3, _t6, _self20), Math.fma(_t0, _t11, _self03), Math.fma(_t1, _t12, _self11));
    }

    /** Piece 2 of {@code unproject_no}, split to fit the inline budget; reached only through it. */
    private static float[] unproject_no_sa0e6ffe5_1(float[] dest, int destOffset, float _self00, float _self10, float _self01, float _self02, float _self12, float _self13, float _t0, float _t1, float _t2, float _t3, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18) {
        float _t19 = Math.fma(_t2, _t12, _self12);
        float _t20 = Math.fma(_t1, _t11, _self01);
        float _t21 = Math.fma(_t0, _t12, _self13);
        float _t22 = Math.fma(_t2, _t11, _self02);
        float _t23 = Math.fma(_t3, _t12, _self10);
        float _t24 = Math.fma(_t3, _t11, _self00);
        float _t37 = Math.fma(_t18, _t13, -(_t19 * _t14));
        float _t38 = Math.fma(_t19, _t15, -(_t21 * _t13));
        float _t39 = Math.fma(_t18, _t15, -(_t21 * _t14));
        float _t40 = Math.fma(_t23, _t14, -(_t18 * _t16));
        float _t41 = Math.fma(_t23, _t13, -(_t19 * _t16));
        float _t42 = Math.fma(_t23, _t15, -(_t21 * _t16));
        float _t46_inv = 1.0f / Math.fma(_t22, _t40, Math.fma(_t24, _t37, -(_t20 * _t41)));
        dest[destOffset + 0] = -(Math.fma(_t17, _t37, Math.fma(_t20, _t38, -(_t22 * _t39))) * _t46_inv);
        dest[destOffset + 1] = Math.fma(_t17, _t41, Math.fma(_t24, _t38, -(_t22 * _t42))) * _t46_inv;
        dest[destOffset + 2] = -(Math.fma(_t17, _t40, Math.fma(_t24, _t39, -(_t20 * _t42))) * _t46_inv);
        return dest;
    }

    public static float[] unproject_zo(float[] dest, int destOffset, float[] src, int srcOffset, float[] winCoords, int winCoordsOffset, float[] viewport, int viewportOffset) {
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _winCoordsx = winCoords[winCoordsOffset + 0];
        float _winCoordsy = winCoords[winCoordsOffset + 1];
        float _winCoordsz = winCoords[winCoordsOffset + 2];
        float _viewportx = viewport[viewportOffset + 0];
        float _viewporty = viewport[viewportOffset + 1];
        float _viewportz = viewport[viewportOffset + 2];
        float _viewportw = viewport[viewportOffset + 3];
        float _t0 = -_self33;
        float _t1 = -_self31;
        float _t2 = -_self32;
        float _t3 = -_self30;
        float _t14 = 2.0f * (_winCoordsx - _viewportx) / _viewportz - 1.0f;
        float _t15 = 2.0f * (_winCoordsy - _viewporty) / _viewportw - 1.0f;
        return unproject_zo_sf92b7a1_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 4], src[srcOffset + 8], src[srcOffset + 13], _t0, _t1, _t2, _t3, Math.fma(_t2, _winCoordsz, _self22), Math.fma(_t1, _winCoordsz, _self21), Math.fma(_t0, _winCoordsz, _self23), Math.fma(_t3, _winCoordsz, _self20), _t14, _t15, Math.fma(_t0, _t14, _self03), Math.fma(_t1, _t15, _self11), Math.fma(_t2, _t15, _self12));
    }

    /** Piece 2 of {@code unproject_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unproject_zo_sf92b7a1_1(float[] dest, int destOffset, float _self00, float _self10, float _self01, float _self02, float _self13, float _t0, float _t1, float _t2, float _t3, float _t8, float _t9, float _t10, float _t11, float _t14, float _t15, float _t16, float _t17, float _t18) {
        float _t19 = Math.fma(_t1, _t14, _self01);
        float _t20 = Math.fma(_t0, _t15, _self13);
        float _t21 = Math.fma(_t2, _t14, _self02);
        float _t22 = Math.fma(_t3, _t15, _self10);
        float _t23 = Math.fma(_t3, _t14, _self00);
        float _t36 = Math.fma(_t17, _t8, -(_t18 * _t9));
        float _t37 = Math.fma(_t18, _t10, -(_t20 * _t8));
        float _t38 = Math.fma(_t17, _t10, -(_t20 * _t9));
        float _t39 = Math.fma(_t22, _t9, -(_t17 * _t11));
        float _t40 = Math.fma(_t22, _t8, -(_t18 * _t11));
        float _t41 = Math.fma(_t22, _t10, -(_t20 * _t11));
        float _t45_inv = 1.0f / Math.fma(_t21, _t39, Math.fma(_t23, _t36, -(_t19 * _t40)));
        dest[destOffset + 0] = -(Math.fma(_t16, _t36, Math.fma(_t19, _t37, -(_t21 * _t38))) * _t45_inv);
        dest[destOffset + 1] = Math.fma(_t16, _t40, Math.fma(_t23, _t37, -(_t21 * _t41))) * _t45_inv;
        dest[destOffset + 2] = -(Math.fma(_t16, _t39, Math.fma(_t23, _t38, -(_t19 * _t41))) * _t45_inv);
        return dest;
    }

    public static float[] unprojectInv_no(float[] dest, int destOffset, float[] src, int srcOffset, float winCoordsX, float winCoordsY, float winCoordsZ, float viewportX, float viewportY, float viewportZ, float viewportW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t2 = Math.fma(2.0f, winCoordsZ, -1.0f);
        float _t8 = 2.0f * (winCoordsX - viewportX) / viewportZ - 1.0f;
        float _t9 = 2.0f * (winCoordsY - viewportY) / viewportW - 1.0f;
        float _t11_inv = 1.0f / Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(_self32, _t2, _self33)));
        dest[destOffset + 0] = Math.fma(_self00, _t8, Math.fma(_self01, _t9, Math.fma(_self02, _t2, _self03))) * _t11_inv;
        dest[destOffset + 1] = Math.fma(_self10, _t8, Math.fma(_self11, _t9, Math.fma(_self12, _t2, _self13))) * _t11_inv;
        dest[destOffset + 2] = Math.fma(_self20, _t8, Math.fma(_self21, _t9, Math.fma(_self22, _t2, _self23))) * _t11_inv;
        return dest;
    }

    public static float[] unprojectInv_zo(float[] dest, int destOffset, float[] src, int srcOffset, float winCoordsX, float winCoordsY, float winCoordsZ, float viewportX, float viewportY, float viewportZ, float viewportW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t7 = 2.0f * (winCoordsX - viewportX) / viewportZ - 1.0f;
        float _t8 = 2.0f * (winCoordsY - viewportY) / viewportW - 1.0f;
        float _t10_inv = 1.0f / Math.fma(_self30, _t7, Math.fma(_self31, _t8, Math.fma(_self32, winCoordsZ, _self33)));
        dest[destOffset + 0] = Math.fma(_self00, _t7, Math.fma(_self01, _t8, Math.fma(_self02, winCoordsZ, _self03))) * _t10_inv;
        dest[destOffset + 1] = Math.fma(_self10, _t7, Math.fma(_self11, _t8, Math.fma(_self12, winCoordsZ, _self13))) * _t10_inv;
        dest[destOffset + 2] = Math.fma(_self20, _t7, Math.fma(_self21, _t8, Math.fma(_self22, winCoordsZ, _self23))) * _t10_inv;
        return dest;
    }

    public static float[] unprojectInv_no(float[] dest, int destOffset, float[] src, int srcOffset, float[] winCoords, int winCoordsOffset, float[] viewport, int viewportOffset) {
        float _self30 = src[srcOffset + 3];
        float _self31 = src[srcOffset + 7];
        float _self32 = src[srcOffset + 11];
        float _self33 = src[srcOffset + 15];
        float _winCoordsx = winCoords[winCoordsOffset + 0];
        float _winCoordsy = winCoords[winCoordsOffset + 1];
        float _winCoordsz = winCoords[winCoordsOffset + 2];
        float _viewportx = viewport[viewportOffset + 0];
        float _viewporty = viewport[viewportOffset + 1];
        float _viewportz = viewport[viewportOffset + 2];
        float _viewportw = viewport[viewportOffset + 3];
        float _t2 = Math.fma(2.0f, _winCoordsz, -1.0f);
        float _t8 = 2.0f * (_winCoordsx - _viewportx) / _viewportz - 1.0f;
        float _t9 = 2.0f * (_winCoordsy - _viewporty) / _viewportw - 1.0f;
        return unprojectInv_no_sa18427f8_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 12], src[srcOffset + 13], src[srcOffset + 14], _t2, _t8, _t9, 1.0f / Math.fma(_self30, _t8, Math.fma(_self31, _t9, Math.fma(_self32, _t2, _self33))));
    }

    /** Piece 2 of {@code unprojectInv_no}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectInv_no_sa18427f8_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _self03, float _self13, float _self23, float _t2, float _t8, float _t9, float _t11_inv) {
        dest[destOffset + 0] = Math.fma(_self00, _t8, Math.fma(_self01, _t9, Math.fma(_self02, _t2, _self03))) * _t11_inv;
        dest[destOffset + 1] = Math.fma(_self10, _t8, Math.fma(_self11, _t9, Math.fma(_self12, _t2, _self13))) * _t11_inv;
        dest[destOffset + 2] = Math.fma(_self20, _t8, Math.fma(_self21, _t9, Math.fma(_self22, _t2, _self23))) * _t11_inv;
        return dest;
    }

    public static float[] unprojectInv_zo(float[] dest, int destOffset, float[] src, int srcOffset, float[] winCoords, int winCoordsOffset, float[] viewport, int viewportOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _winCoordsx = winCoords[winCoordsOffset + 0];
        float _winCoordsy = winCoords[winCoordsOffset + 1];
        float _winCoordsz = winCoords[winCoordsOffset + 2];
        float _viewportx = viewport[viewportOffset + 0];
        float _viewporty = viewport[viewportOffset + 1];
        float _viewportz = viewport[viewportOffset + 2];
        float _viewportw = viewport[viewportOffset + 3];
        float _t7 = 2.0f * (_winCoordsx - _viewportx) / _viewportz - 1.0f;
        float _t8 = 2.0f * (_winCoordsy - _viewporty) / _viewportw - 1.0f;
        float _t10_inv = 1.0f / Math.fma(_self30, _t7, Math.fma(_self31, _t8, Math.fma(_self32, _winCoordsz, _self33)));
        dest[destOffset + 0] = Math.fma(_self00, _t7, Math.fma(_self01, _t8, Math.fma(_self02, _winCoordsz, _self03))) * _t10_inv;
        return unprojectInv_zo_s6baed534_1(dest, destOffset, _self10, _self20, _self11, _self21, _self12, _self22, _self13, _self23, _winCoordsz, _t7, _t8, _t10_inv);
    }

    /** Piece 2 of {@code unprojectInv_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectInv_zo_s6baed534_1(float[] dest, int destOffset, float _self10, float _self20, float _self11, float _self21, float _self12, float _self22, float _self13, float _self23, float _winCoordsz, float _t7, float _t8, float _t10_inv) {
        dest[destOffset + 1] = Math.fma(_self10, _t7, Math.fma(_self11, _t8, Math.fma(_self12, _winCoordsz, _self13))) * _t10_inv;
        dest[destOffset + 2] = Math.fma(_self20, _t7, Math.fma(_self21, _t8, Math.fma(_self22, _winCoordsz, _self23))) * _t10_inv;
        return dest;
    }

    public static float[] unprojectInvRay_no(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float[] src, int srcOffset, float winCoordsX, float winCoordsY, float viewportX, float viewportY, float viewportZ, float viewportW) {
        float _self00 = src[srcOffset + 0];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t4 = _self03 + _self02;
        float _t14 = 2.0f * (winCoordsX - viewportX) / viewportZ - 1.0f;
        float _t15 = 2.0f * (winCoordsY - viewportY) / viewportW - 1.0f;
        float _t24 = Math.fma(_self30, _t14, Math.fma(_self31, _t15, _self33 - _self32));
        float _t24_inv = 1.0f / _t24;
        float _t25 = Math.fma(_self30, _t14, Math.fma(_self31, _t15, _self33 + _self32));
        return unprojectInvRay_no_s82b78bb5_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _self00, src[srcOffset + 1], src[srcOffset + 2], _self01, src[srcOffset + 5], src[srcOffset + 6], _t4, _self03 - _self02, _self13 + _self12, _self13 - _self12, _self23 + _self22, _self23 - _self22, _t14, _t15, _t24, _t24_inv, _t25, _t24_inv * _t25, 1.0f / _t25, Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t4)));
    }

    /** Piece 2 of {@code unprojectInvRay_no}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectInvRay_no_s82b78bb5_1(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t14, float _t15, float _t24, float _t24_inv, float _t25, float _sp0, float _t25_inv, float _t26) {
        float _t27 = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t5));
        float _t28 = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t6));
        float _t29 = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t7));
        float _t30 = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t8));
        float _t31 = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t9));
        float _t32 = Math.abs(_t24);
        float _t33 = Math.abs(_t25);
        float _t34 = _t33 * 9.536743E-7f;
        if (_t32 <= _t34) {
            rayOrigin[rayOriginOffset + 0] = _t26 * _t25_inv;
            rayOrigin[rayOriginOffset + 1] = _t28 * _t25_inv;
            rayOrigin[rayOriginOffset + 2] = _t30 * _t25_inv;
        } else {
            rayOrigin[rayOriginOffset + 0] = _t27 * _t24_inv;
            rayOrigin[rayOriginOffset + 1] = _t29 * _t24_inv;
            rayOrigin[rayOriginOffset + 2] = _t31 * _t24_inv;
        }
        return unprojectInvRay_no_s82b78bb5_2(rayOrigin, rayDir, rayDirOffset, _self00, _self10, _self20, _self01, _self11, _self21, _t4, _t5, _t6, _t7, _t8, _t9, _t14, _t15, _t24, _t25, _sp0, _t25_inv, _t26, _t27, _t28, _t29, _t30, _t31, _t32, _t34, 1.0f / (_t33 <= _t32 * 9.536743E-7f ? _t24 : _t25));
    }

    /** Piece 3 of {@code unprojectInvRay_no}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectInvRay_no_s82b78bb5_2(float[] rayOrigin, float[] rayDir, int rayDirOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t14, float _t15, float _t24, float _t25, float _sp0, float _t25_inv, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t34, float _t36_inv) {
        if (_t32 <= _t34) {
            rayDir[rayDirOffset + 0] = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t5 - _t26 * _t24 / _t25)) * _t25_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t7 - _t28 * _t24 / _t25)) * _t25_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t9 - _t30 * _t24 / _t25)) * _t25_inv;
        } else {
            rayDir[rayDirOffset + 0] = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t4 - _t27 * _sp0)) * _t36_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t6 - _t29 * _sp0)) * _t36_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t8 - _t31 * _sp0)) * _t36_inv;
        }
        return rayOrigin;
    }

    public static float[] unprojectInvRay_zo(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float[] src, int srcOffset, float winCoordsX, float winCoordsY, float viewportX, float viewportY, float viewportZ, float viewportW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t10 = 2.0f * (winCoordsX - viewportX) / viewportZ - 1.0f;
        float _t11 = 2.0f * (winCoordsY - viewportY) / viewportW - 1.0f;
        float _t20 = Math.fma(_self30, _t10, Math.fma(_self31, _t11, _self33));
        return unprojectInvRay_zo_sad57ede1_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, src[srcOffset + 11], _self03, _self13, _self23, _self33, _self03 + _self02, _self13 + _self12, _self23 + _self22, _t10, _t11, _t20, 1.0f / _t20, Math.fma(_self00, _t10, Math.fma(_self01, _t11, _self03)), Math.fma(_self10, _t10, Math.fma(_self11, _t11, _self13)), Math.fma(_self20, _t10, Math.fma(_self21, _t11, _self23)), Math.abs(_t20));
    }

    /** Piece 2 of {@code unprojectInvRay_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectInvRay_zo_sad57ede1_1(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self32, float _self03, float _self13, float _self23, float _self33, float _t3, float _t4, float _t5, float _t10, float _t11, float _t20, float _t20_inv, float _t21, float _t22, float _t23, float _t24) {
        float _t25 = Math.fma(_self30, _t10, Math.fma(_self31, _t11, _self33 + _self32));
        float _t25_inv = 1.0f / _t25;
        float _t26 = Math.fma(_self00, _t10, Math.fma(_self01, _t11, _t3));
        float _t27 = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _t4));
        float _t28 = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _t5));
        float _t30 = Math.abs(_t25);
        float _t31 = _t30 * 9.536743E-7f;
        if (_t24 <= _t31) {
            rayOrigin[rayOriginOffset + 0] = _t26 * _t25_inv;
            rayOrigin[rayOriginOffset + 1] = _t27 * _t25_inv;
            rayOrigin[rayOriginOffset + 2] = _t28 * _t25_inv;
        } else {
            rayOrigin[rayOriginOffset + 0] = _t21 * _t20_inv;
            rayOrigin[rayOriginOffset + 1] = _t22 * _t20_inv;
            rayOrigin[rayOriginOffset + 2] = _t23 * _t20_inv;
        }
        return unprojectInvRay_zo_sad57ede1_2(rayOrigin, rayDir, rayDirOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self03, _self13, _self23, _t3, _t4, _t5, _t10, _t11, _t20, _t21, _t22, _t23, _t24, _t25, _t20_inv * _t25, _t25_inv, _t26, _t27, _t28, _t31, 1.0f / (_t30 <= _t24 * 9.536743E-7f ? _t20 : _t25));
    }

    /** Piece 3 of {@code unprojectInvRay_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectInvRay_zo_sad57ede1_2(float[] rayOrigin, float[] rayDir, int rayDirOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self03, float _self13, float _self23, float _t3, float _t4, float _t5, float _t10, float _t11, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _sp0, float _t25_inv, float _t26, float _t27, float _t28, float _t31, float _t32_inv) {
        if (_t24 <= _t31) {
            rayDir[rayDirOffset + 0] = Math.fma(_self00, _t10, Math.fma(_self01, _t11, _self03 - _t26 * _t20 / _t25)) * _t25_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _self13 - _t27 * _t20 / _t25)) * _t25_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _self23 - _t28 * _t20 / _t25)) * _t25_inv;
        } else {
            rayDir[rayDirOffset + 0] = Math.fma(_self00, _t10, Math.fma(_self01, _t11, _t3 - _t21 * _sp0)) * _t32_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _t4 - _t22 * _sp0)) * _t32_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _t5 - _t23 * _sp0)) * _t32_inv;
        }
        return rayOrigin;
    }

    public static float[] unprojectInvRay_no(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float[] src, int srcOffset, float[] winCoords, int winCoordsOffset, float[] viewport, int viewportOffset) {
        float _self30 = src[srcOffset + 3];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _winCoordsx = winCoords[winCoordsOffset + 0];
        float _winCoordsy = winCoords[winCoordsOffset + 1];
        float _viewportx = viewport[viewportOffset + 0];
        float _viewporty = viewport[viewportOffset + 1];
        float _viewportz = viewport[viewportOffset + 2];
        float _viewportw = viewport[viewportOffset + 3];
        float _t14 = 2.0f * (_winCoordsx - _viewportx) / _viewportz - 1.0f;
        float _t15 = 2.0f * (_winCoordsy - _viewporty) / _viewportw - 1.0f;
        float _t24 = Math.fma(_self30, _t14, Math.fma(_self31, _t15, _self33 - _self32));
        return unprojectInvRay_no_se0d1db08_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], _self30, src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], _self31, _self32, _self33, _self03 + _self02, _self03 - _self02, _self13 + _self12, _self13 - _self12, _self23 + _self22, _self23 - _self22, _t14, _t15, _t24, 1.0f / _t24);
    }

    /** Piece 2 of {@code unprojectInvRay_no}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectInvRay_no_se0d1db08_1(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self32, float _self33, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t14, float _t15, float _t24, float _t24_inv) {
        float _t25 = Math.fma(_self30, _t14, Math.fma(_self31, _t15, _self33 + _self32));
        float _t32 = Math.abs(_t24);
        float _t33 = Math.abs(_t25);
        return unprojectInvRay_no_se0d1db08_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _self00, _self10, _self20, _self01, _self11, _self21, _t4, _t5, _t6, _t7, _t8, _t9, _t14, _t15, _t24, _t24_inv, _t25, _t24_inv * _t25, 1.0f / _t25, Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t4)), Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t5)), Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t6)), Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t7)), Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t8)), Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t9)), _t32, _t33 * 9.536743E-7f, 1.0f / (_t33 <= _t32 * 9.536743E-7f ? _t24 : _t25));
    }

    /** Piece 3 of {@code unprojectInvRay_no}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectInvRay_no_se0d1db08_2(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t14, float _t15, float _t24, float _t24_inv, float _t25, float _sp0, float _t25_inv, float _t26, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t34, float _t36_inv) {
        if (_t32 <= _t34) {
            rayOrigin[rayOriginOffset + 0] = _t26 * _t25_inv;
            rayOrigin[rayOriginOffset + 1] = _t28 * _t25_inv;
            rayOrigin[rayOriginOffset + 2] = _t30 * _t25_inv;
        } else {
            rayOrigin[rayOriginOffset + 0] = _t27 * _t24_inv;
            rayOrigin[rayOriginOffset + 1] = _t29 * _t24_inv;
            rayOrigin[rayOriginOffset + 2] = _t31 * _t24_inv;
        }
        if (_t32 <= _t34) {
            rayDir[rayDirOffset + 0] = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t5 - _t26 * _t24 / _t25)) * _t25_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t7 - _t28 * _t24 / _t25)) * _t25_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t9 - _t30 * _t24 / _t25)) * _t25_inv;
        } else {
            rayDir[rayDirOffset + 0] = Math.fma(_self00, _t14, Math.fma(_self01, _t15, _t4 - _t27 * _sp0)) * _t36_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_self10, _t14, Math.fma(_self11, _t15, _t6 - _t29 * _sp0)) * _t36_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_self20, _t14, Math.fma(_self21, _t15, _t8 - _t31 * _sp0)) * _t36_inv;
        }
        return rayOrigin;
    }

    public static float[] unprojectInvRay_zo(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float[] src, int srcOffset, float[] winCoords, int winCoordsOffset, float[] viewport, int viewportOffset) {
        float _self30 = src[srcOffset + 3];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _winCoordsx = winCoords[winCoordsOffset + 0];
        float _winCoordsy = winCoords[winCoordsOffset + 1];
        float _viewportx = viewport[viewportOffset + 0];
        float _viewporty = viewport[viewportOffset + 1];
        float _viewportz = viewport[viewportOffset + 2];
        float _viewportw = viewport[viewportOffset + 3];
        float _t10 = 2.0f * (_winCoordsx - _viewportx) / _viewportz - 1.0f;
        float _t11 = 2.0f * (_winCoordsy - _viewporty) / _viewportw - 1.0f;
        float _t20 = Math.fma(_self30, _t10, Math.fma(_self31, _t11, _self33));
        return unprojectInvRay_zo_sd044e26c_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], _self30, src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], _self31, src[srcOffset + 11], _self03, _self13, _self23, _self33, _self03 + _self02, _self13 + _self12, _self23 + _self22, _t10, _t11, _t20, 1.0f / _t20);
    }

    /** Piece 2 of {@code unprojectInvRay_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectInvRay_zo_sd044e26c_1(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self32, float _self03, float _self13, float _self23, float _self33, float _t3, float _t4, float _t5, float _t10, float _t11, float _t20, float _t20_inv) {
        float _t24 = Math.abs(_t20);
        float _t25 = Math.fma(_self30, _t10, Math.fma(_self31, _t11, _self33 + _self32));
        float _t30 = Math.abs(_t25);
        return unprojectInvRay_zo_sd044e26c_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self03, _self13, _self23, _t3, _t4, _t5, _t10, _t11, _t20, _t20_inv, Math.fma(_self00, _t10, Math.fma(_self01, _t11, _self03)), Math.fma(_self10, _t10, Math.fma(_self11, _t11, _self13)), Math.fma(_self20, _t10, Math.fma(_self21, _t11, _self23)), _t24, _t25, _t20_inv * _t25, 1.0f / _t25, Math.fma(_self00, _t10, Math.fma(_self01, _t11, _t3)), Math.fma(_self10, _t10, Math.fma(_self11, _t11, _t4)), Math.fma(_self20, _t10, Math.fma(_self21, _t11, _t5)), _t30 * 9.536743E-7f, 1.0f / (_t30 <= _t24 * 9.536743E-7f ? _t20 : _t25));
    }

    /** Piece 3 of {@code unprojectInvRay_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectInvRay_zo_sd044e26c_2(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self03, float _self13, float _self23, float _t3, float _t4, float _t5, float _t10, float _t11, float _t20, float _t20_inv, float _t21, float _t22, float _t23, float _t24, float _t25, float _sp0, float _t25_inv, float _t26, float _t27, float _t28, float _t31, float _t32_inv) {
        if (_t24 <= _t31) {
            rayOrigin[rayOriginOffset + 0] = _t26 * _t25_inv;
            rayOrigin[rayOriginOffset + 1] = _t27 * _t25_inv;
            rayOrigin[rayOriginOffset + 2] = _t28 * _t25_inv;
        } else {
            rayOrigin[rayOriginOffset + 0] = _t21 * _t20_inv;
            rayOrigin[rayOriginOffset + 1] = _t22 * _t20_inv;
            rayOrigin[rayOriginOffset + 2] = _t23 * _t20_inv;
        }
        if (_t24 <= _t31) {
            rayDir[rayDirOffset + 0] = Math.fma(_self00, _t10, Math.fma(_self01, _t11, _self03 - _t26 * _t20 / _t25)) * _t25_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _self13 - _t27 * _t20 / _t25)) * _t25_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _self23 - _t28 * _t20 / _t25)) * _t25_inv;
        } else {
            rayDir[rayDirOffset + 0] = Math.fma(_self00, _t10, Math.fma(_self01, _t11, _t3 - _t21 * _sp0)) * _t32_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_self10, _t10, Math.fma(_self11, _t11, _t4 - _t22 * _sp0)) * _t32_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_self20, _t10, Math.fma(_self21, _t11, _t5 - _t23 * _sp0)) * _t32_inv;
        }
        return rayOrigin;
    }

    public static float[] unprojectRay_no(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float[] src, int srcOffset, float winCoordsX, float winCoordsY, float viewportX, float viewportY, float viewportZ, float viewportW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = -_self32;
        float _t1 = -_self30;
        float _t2 = -_self31;
        float _t18 = 2.0f * (winCoordsX - viewportX) / viewportZ - 1.0f;
        float _t19 = 2.0f * (winCoordsY - viewportY) / viewportW - 1.0f;
        return unprojectRay_no_s1aba37e6_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, src[srcOffset + 12], src[srcOffset + 13], -_self33, _self21 + _self31, _self20 + _self30, _self22 + _self32, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self23 - _self33, _self23 + _self33, _t18, _t19, Math.fma(_t0, _t18, _self02), Math.fma(_t1, _t19, _self10), Math.fma(_t2, _t19, _self11), Math.fma(_t1, _t18, _self00), Math.fma(_t0, _t19, _self12), Math.fma(_t2, _t18, _self01));
    }

    /** Piece 2 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectRay_no_s1aba37e6_1(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _self03, float _self13, float _t3, float _t5, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t26 = Math.fma(_t3, _t18, _self03);
        float _t27 = Math.fma(_t3, _t19, _self13);
        float _t56 = Math.fma(_t5, _t21, -(_t7 * _t22));
        float _t57 = Math.fma(_t8, _t22, -(_t5 * _t24));
        float _t58 = Math.fma(_t8, _t21, -(_t7 * _t24));
        float _t59 = Math.fma(_t21, _t9, -(_t22 * _t10));
        float _t60 = Math.fma(_t22, _t11, -(_t24 * _t9));
        float _t61 = Math.fma(_t21, _t11, -(_t24 * _t10));
        float _t92 = Math.fma(_t20, _t56, Math.fma(_t23, _t57, -(_t25 * _t58)));
        float _t92_inv = 1.0f / _t92;
        float _t93 = Math.fma(_t20, _t59, Math.fma(_t23, _t60, -(_t25 * _t61)));
        return unprojectRay_no_s1aba37e6_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t20, _t23, _t25, _t26, -_t25, -_t26, -_t20, -_t23, _t56, _t57, _t58, _t59, _t60, _t61, Math.fma(_t24, _t12, -(_t27 * _t11)), Math.fma(_t22, _t12, -(_t27 * _t9)), Math.fma(_t13, _t24, -(_t8 * _t27)), Math.fma(_t13, _t22, -(_t5 * _t27)), Math.fma(_t21, _t12, -(_t27 * _t10)), Math.fma(_t13, _t21, -(_t7 * _t27)), _t92, _t92_inv, _t93, _t92_inv * _t93, 1.0f / _t93);
    }

    /** Piece 3 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectRay_no_s1aba37e6_2(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _t20, float _t23, float _t25, float _t26, float _t28, float _t29, float _t30, float _t31, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t92, float _t92_inv, float _t93, float _sp0, float _t93_inv) {
        float _t100 = Math.abs(_t92);
        float _t101 = Math.abs(_t93);
        return unprojectRay_no_s1aba37e6_3(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t20, _t23, _t25, _t26, _t28, _t29, _t30, _t31, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t92, _t92_inv, _t93, _sp0, _t93_inv, Math.fma(_t26, _t60, Math.fma(_t25, _t62, -(_t20 * _t63))), Math.fma(_t26, _t57, Math.fma(_t25, _t64, -(_t20 * _t65))), Math.fma(_t26, _t61, Math.fma(_t23, _t62, -(_t20 * _t66))), Math.fma(_t26, _t58, Math.fma(_t23, _t64, -(_t20 * _t67))), Math.fma(_t26, _t59, Math.fma(_t23, _t63, -(_t25 * _t66))), Math.fma(_t26, _t56, Math.fma(_t23, _t65, -(_t25 * _t67))), _t100, _t101 * 9.536743E-7f, 1.0f / (_t101 <= _t100 * 9.536743E-7f ? _t92 : _t93));
    }

    /** Piece 4 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectRay_no_s1aba37e6_3(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _t20, float _t23, float _t25, float _t26, float _t28, float _t29, float _t30, float _t31, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t92, float _t92_inv, float _t93, float _sp0, float _t93_inv, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t102, float _t104_inv) {
        if (_t100 <= _t102) {
            rayOrigin[rayOriginOffset + 0] = -(_t94 * _t93_inv);
            rayOrigin[rayOriginOffset + 1] = _t96 * _t93_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t98 * _t93_inv);
        } else {
            rayOrigin[rayOriginOffset + 0] = -(_t95 * _t92_inv);
            rayOrigin[rayOriginOffset + 1] = _t97 * _t92_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t99 * _t92_inv);
        }
        if (_t100 <= _t102) {
            rayDir[rayDirOffset + 0] = Math.fma(_t28, _t64, Math.fma(_t20, _t65, Math.fma(_t29, _t57, _t92 * _t94 / _t93))) * _t93_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t23, _t64, Math.fma(_t30, _t67, Math.fma(_t26, _t58, -(_t92 * _t96 / _t93)))) * _t93_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t31, _t65, Math.fma(_t25, _t67, Math.fma(_t29, _t56, _t92 * _t98 / _t93))) * _t93_inv;
        } else {
            rayDir[rayDirOffset + 0] = Math.fma(_t28, _t62, Math.fma(_t20, _t63, Math.fma(_t29, _t60, _sp0 * _t95))) * _t104_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t23, _t62, Math.fma(_t30, _t66, Math.fma(_t26, _t61, -(_t97 * _sp0)))) * _t104_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t31, _t63, Math.fma(_t25, _t66, Math.fma(_t29, _t59, _t99 * _sp0))) * _t104_inv;
        }
        return rayOrigin;
    }

    public static float[] unprojectRay_zo(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float[] src, int srcOffset, float winCoordsX, float winCoordsY, float viewportX, float viewportY, float viewportZ, float viewportW) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self03 = src[srcOffset + 12];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _t0 = -_self32;
        float _t1 = -_self30;
        float _t2 = -_self31;
        float _t3 = -_self33;
        float _t14 = 2.0f * (winCoordsX - viewportX) / viewportZ - 1.0f;
        float _t15 = 2.0f * (winCoordsY - viewportY) / viewportW - 1.0f;
        return unprojectRay_zo_se99c306a_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _self20, _self21, _self22, src[srcOffset + 13], _self23, _t3, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self23 - _self33, _t15, Math.fma(_t0, _t14, _self02), Math.fma(_t1, _t15, _self10), Math.fma(_t2, _t15, _self11), Math.fma(_t1, _t14, _self00), Math.fma(_t0, _t15, _self12), Math.fma(_t2, _t14, _self01), Math.fma(_t3, _t14, _self03));
    }

    /** Piece 2 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectRay_zo_se99c306a_1(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _self20, float _self21, float _self22, float _self13, float _self23, float _t3, float _t6, float _t7, float _t8, float _t9, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22) {
        float _t23 = Math.fma(_t3, _t15, _self13);
        float _t52 = Math.fma(_self21, _t17, -(_self20 * _t18));
        float _t53 = Math.fma(_self22, _t18, -(_self21 * _t20));
        float _t54 = Math.fma(_self22, _t17, -(_self20 * _t20));
        float _t55 = Math.fma(_self23, _t20, -(_self22 * _t23));
        float _t56 = Math.fma(_self23, _t18, -(_self21 * _t23));
        float _t57 = Math.fma(_self23, _t17, -(_self20 * _t23));
        float _t88 = Math.fma(_t16, _t52, Math.fma(_t19, _t53, -(_t21 * _t54)));
        return unprojectRay_zo_se99c306a_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t16, _t19, _t21, _t22, -_t21, -_t22, -_t16, -_t19, _t52, _t53, _t54, _t55, _t56, _t57, Math.fma(_t17, _t6, -(_t18 * _t7)), Math.fma(_t18, _t8, -(_t20 * _t6)), Math.fma(_t17, _t8, -(_t20 * _t7)), Math.fma(_t20, _t9, -(_t23 * _t8)), Math.fma(_t18, _t9, -(_t23 * _t6)), Math.fma(_t17, _t9, -(_t23 * _t7)), _t88, 1.0f / _t88, Math.fma(_t22, _t53, Math.fma(_t21, _t55, -(_t16 * _t56))), Math.fma(_t22, _t54, Math.fma(_t19, _t55, -(_t16 * _t57))), Math.fma(_t22, _t52, Math.fma(_t19, _t56, -(_t21 * _t57))));
    }

    /** Piece 3 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectRay_zo_se99c306a_2(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _t16, float _t19, float _t21, float _t22, float _t24, float _t25, float _t26, float _t27, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t62, float _t63, float _t88, float _t88_inv, float _t89, float _t90, float _t91) {
        float _t92 = Math.abs(_t88);
        float _t94 = Math.fma(_t16, _t58, Math.fma(_t19, _t59, -(_t21 * _t60)));
        float _t94_inv = 1.0f / _t94;
        float _t95 = Math.fma(_t22, _t59, Math.fma(_t21, _t61, -(_t16 * _t62)));
        float _t96 = Math.fma(_t22, _t60, Math.fma(_t19, _t61, -(_t16 * _t63)));
        float _t97 = Math.fma(_t22, _t58, Math.fma(_t19, _t62, -(_t21 * _t63)));
        float _t98 = Math.abs(_t94);
        float _t99 = _t98 * 9.536743E-7f;
        if (_t92 <= _t99) {
            rayOrigin[rayOriginOffset + 0] = -(_t95 * _t94_inv);
            rayOrigin[rayOriginOffset + 1] = _t96 * _t94_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t97 * _t94_inv);
        } else {
            rayOrigin[rayOriginOffset + 0] = -(_t89 * _t88_inv);
            rayOrigin[rayOriginOffset + 1] = _t90 * _t88_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t91 * _t88_inv);
        }
        return unprojectRay_zo_se99c306a_3(rayOrigin, rayDir, rayDirOffset, _t16, _t19, _t21, _t22, _t24, _t25, _t26, _t27, _t52, _t53, _t54, _t55, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t88, _t89, _t90, _t91, _t92, _t94, _t88_inv * _t94, _t94_inv, _t95, _t96, _t97, _t99, 1.0f / (_t98 <= _t92 * 9.536743E-7f ? _t88 : _t94));
    }

    /** Piece 4 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectRay_zo_se99c306a_3(float[] rayOrigin, float[] rayDir, int rayDirOffset, float _t16, float _t19, float _t21, float _t22, float _t24, float _t25, float _t26, float _t27, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t62, float _t63, float _t88, float _t89, float _t90, float _t91, float _t92, float _t94, float _sp0, float _t94_inv, float _t95, float _t96, float _t97, float _t99, float _t100_inv) {
        if (_t92 <= _t99) {
            rayDir[rayDirOffset + 0] = Math.fma(_t24, _t55, Math.fma(_t16, _t56, Math.fma(_t25, _t53, _t88 * _t95 / _t94))) * _t94_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t19, _t55, Math.fma(_t26, _t57, Math.fma(_t22, _t54, -(_t88 * _t96 / _t94)))) * _t94_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t27, _t56, Math.fma(_t21, _t57, Math.fma(_t25, _t52, _t88 * _t97 / _t94))) * _t94_inv;
        } else {
            rayDir[rayDirOffset + 0] = Math.fma(_t24, _t61, Math.fma(_t16, _t62, Math.fma(_t25, _t59, _sp0 * _t89))) * _t100_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t19, _t61, Math.fma(_t26, _t63, Math.fma(_t22, _t60, -(_t90 * _sp0)))) * _t100_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t27, _t62, Math.fma(_t21, _t63, Math.fma(_t25, _t58, _t91 * _sp0))) * _t100_inv;
        }
        return rayOrigin;
    }

    public static float[] unprojectRay_no(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float[] src, int srcOffset, float[] winCoords, int winCoordsOffset, float[] viewport, int viewportOffset) {
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _winCoordsx = winCoords[winCoordsOffset + 0];
        float _winCoordsy = winCoords[winCoordsOffset + 1];
        float _viewportx = viewport[viewportOffset + 0];
        float _viewporty = viewport[viewportOffset + 1];
        float _viewportz = viewport[viewportOffset + 2];
        float _viewportw = viewport[viewportOffset + 3];
        float _t0 = -_self32;
        float _t18 = 2.0f * (_winCoordsx - _viewportx) / _viewportz - 1.0f;
        return unprojectRay_no_s45c5cc2f_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 9], src[srcOffset + 12], src[srcOffset + 13], _t0, -_self30, -_self31, -_self33, _self21 + _self31, _self20 + _self30, _self22 + _self32, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self23 - _self33, _self23 + _self33, _t18, 2.0f * (_winCoordsy - _viewporty) / _viewportw - 1.0f, Math.fma(_t0, _t18, _self02));
    }

    /** Piece 2 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectRay_no_s45c5cc2f_1(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _self00, float _self10, float _self01, float _self11, float _self12, float _self03, float _self13, float _t0, float _t1, float _t2, float _t3, float _t5, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t18, float _t19, float _t20) {
        float _t21 = Math.fma(_t1, _t19, _self10);
        float _t22 = Math.fma(_t2, _t19, _self11);
        float _t23 = Math.fma(_t1, _t18, _self00);
        float _t24 = Math.fma(_t0, _t19, _self12);
        float _t25 = Math.fma(_t2, _t18, _self01);
        float _t26 = Math.fma(_t3, _t18, _self03);
        float _t27 = Math.fma(_t3, _t19, _self13);
        float _t56 = Math.fma(_t5, _t21, -(_t7 * _t22));
        float _t57 = Math.fma(_t8, _t22, -(_t5 * _t24));
        float _t58 = Math.fma(_t8, _t21, -(_t7 * _t24));
        float _t92 = Math.fma(_t20, _t56, Math.fma(_t23, _t57, -(_t25 * _t58)));
        return unprojectRay_no_s45c5cc2f_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t20, _t23, _t25, _t26, -_t25, -_t26, -_t20, -_t23, _t56, _t57, _t58, Math.fma(_t21, _t9, -(_t22 * _t10)), Math.fma(_t22, _t11, -(_t24 * _t9)), Math.fma(_t21, _t11, -(_t24 * _t10)), Math.fma(_t24, _t12, -(_t27 * _t11)), Math.fma(_t22, _t12, -(_t27 * _t9)), Math.fma(_t13, _t24, -(_t8 * _t27)), Math.fma(_t13, _t22, -(_t5 * _t27)), Math.fma(_t21, _t12, -(_t27 * _t10)), Math.fma(_t13, _t21, -(_t7 * _t27)), _t92, 1.0f / _t92);
    }

    /** Piece 3 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectRay_no_s45c5cc2f_2(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _t20, float _t23, float _t25, float _t26, float _t28, float _t29, float _t30, float _t31, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t92, float _t92_inv) {
        float _t93 = Math.fma(_t20, _t59, Math.fma(_t23, _t60, -(_t25 * _t61)));
        float _t100 = Math.abs(_t92);
        float _t101 = Math.abs(_t93);
        return unprojectRay_no_s45c5cc2f_3(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t20, _t23, _t25, _t26, _t28, _t29, _t30, _t31, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t64, _t65, _t66, _t67, _t92, _t92_inv, _t93, _t92_inv * _t93, 1.0f / _t93, Math.fma(_t26, _t60, Math.fma(_t25, _t62, -(_t20 * _t63))), Math.fma(_t26, _t57, Math.fma(_t25, _t64, -(_t20 * _t65))), Math.fma(_t26, _t61, Math.fma(_t23, _t62, -(_t20 * _t66))), Math.fma(_t26, _t58, Math.fma(_t23, _t64, -(_t20 * _t67))), Math.fma(_t26, _t59, Math.fma(_t23, _t63, -(_t25 * _t66))), Math.fma(_t26, _t56, Math.fma(_t23, _t65, -(_t25 * _t67))), _t100, _t101 * 9.536743E-7f, 1.0f / (_t101 <= _t100 * 9.536743E-7f ? _t92 : _t93));
    }

    /** Piece 4 of {@code unprojectRay_no}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectRay_no_s45c5cc2f_3(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _t20, float _t23, float _t25, float _t26, float _t28, float _t29, float _t30, float _t31, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t62, float _t63, float _t64, float _t65, float _t66, float _t67, float _t92, float _t92_inv, float _t93, float _sp0, float _t93_inv, float _t94, float _t95, float _t96, float _t97, float _t98, float _t99, float _t100, float _t102, float _t104_inv) {
        if (_t100 <= _t102) {
            rayOrigin[rayOriginOffset + 0] = -(_t94 * _t93_inv);
            rayOrigin[rayOriginOffset + 1] = _t96 * _t93_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t98 * _t93_inv);
        } else {
            rayOrigin[rayOriginOffset + 0] = -(_t95 * _t92_inv);
            rayOrigin[rayOriginOffset + 1] = _t97 * _t92_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t99 * _t92_inv);
        }
        if (_t100 <= _t102) {
            rayDir[rayDirOffset + 0] = Math.fma(_t28, _t64, Math.fma(_t20, _t65, Math.fma(_t29, _t57, _t92 * _t94 / _t93))) * _t93_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t23, _t64, Math.fma(_t30, _t67, Math.fma(_t26, _t58, -(_t92 * _t96 / _t93)))) * _t93_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t31, _t65, Math.fma(_t25, _t67, Math.fma(_t29, _t56, _t92 * _t98 / _t93))) * _t93_inv;
        } else {
            rayDir[rayDirOffset + 0] = Math.fma(_t28, _t62, Math.fma(_t20, _t63, Math.fma(_t29, _t60, _sp0 * _t95))) * _t104_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t23, _t62, Math.fma(_t30, _t66, Math.fma(_t26, _t61, -(_t97 * _sp0)))) * _t104_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t31, _t63, Math.fma(_t25, _t66, Math.fma(_t29, _t59, _t99 * _sp0))) * _t104_inv;
        }
        return rayOrigin;
    }

    public static float[] unprojectRay_zo(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float[] src, int srcOffset, float[] winCoords, int winCoordsOffset, float[] viewport, int viewportOffset) {
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _winCoordsx = winCoords[winCoordsOffset + 0];
        float _winCoordsy = winCoords[winCoordsOffset + 1];
        float _viewportx = viewport[viewportOffset + 0];
        float _viewporty = viewport[viewportOffset + 1];
        float _viewportz = viewport[viewportOffset + 2];
        float _viewportw = viewport[viewportOffset + 3];
        float _t0 = -_self32;
        float _t1 = -_self30;
        float _t14 = 2.0f * (_winCoordsx - _viewportx) / _viewportz - 1.0f;
        float _t15 = 2.0f * (_winCoordsy - _viewporty) / _viewportw - 1.0f;
        return unprojectRay_zo_s95ac2b7b_1(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, src[srcOffset + 0], _self20, src[srcOffset + 4], src[srcOffset + 5], _self21, src[srcOffset + 9], _self22, src[srcOffset + 12], src[srcOffset + 13], _self23, _t0, _t1, -_self31, -_self33, _self21 - _self31, _self20 - _self30, _self22 - _self32, _self23 - _self33, _t14, _t15, Math.fma(_t0, _t14, _self02), Math.fma(_t1, _t15, _self10));
    }

    /** Piece 2 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectRay_zo_s95ac2b7b_1(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _self00, float _self20, float _self01, float _self11, float _self21, float _self12, float _self22, float _self03, float _self13, float _self23, float _t0, float _t1, float _t2, float _t3, float _t6, float _t7, float _t8, float _t9, float _t14, float _t15, float _t16, float _t17) {
        float _t18 = Math.fma(_t2, _t15, _self11);
        float _t19 = Math.fma(_t1, _t14, _self00);
        float _t20 = Math.fma(_t0, _t15, _self12);
        float _t21 = Math.fma(_t2, _t14, _self01);
        float _t22 = Math.fma(_t3, _t14, _self03);
        float _t23 = Math.fma(_t3, _t15, _self13);
        float _t52 = Math.fma(_self21, _t17, -(_self20 * _t18));
        float _t53 = Math.fma(_self22, _t18, -(_self21 * _t20));
        float _t54 = Math.fma(_self22, _t17, -(_self20 * _t20));
        float _t88 = Math.fma(_t16, _t52, Math.fma(_t19, _t53, -(_t21 * _t54)));
        return unprojectRay_zo_s95ac2b7b_2(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t16, _t19, _t21, _t22, -_t21, -_t22, -_t16, -_t19, _t52, _t53, _t54, Math.fma(_self23, _t20, -(_self22 * _t23)), Math.fma(_self23, _t18, -(_self21 * _t23)), Math.fma(_self23, _t17, -(_self20 * _t23)), Math.fma(_t17, _t6, -(_t18 * _t7)), Math.fma(_t18, _t8, -(_t20 * _t6)), Math.fma(_t17, _t8, -(_t20 * _t7)), Math.fma(_t20, _t9, -(_t23 * _t8)), Math.fma(_t18, _t9, -(_t23 * _t6)), Math.fma(_t17, _t9, -(_t23 * _t7)), _t88, 1.0f / _t88);
    }

    /** Piece 3 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectRay_zo_s95ac2b7b_2(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _t16, float _t19, float _t21, float _t22, float _t24, float _t25, float _t26, float _t27, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t62, float _t63, float _t88, float _t88_inv) {
        float _t92 = Math.abs(_t88);
        float _t94 = Math.fma(_t16, _t58, Math.fma(_t19, _t59, -(_t21 * _t60)));
        float _t98 = Math.abs(_t94);
        return unprojectRay_zo_s95ac2b7b_3(rayOrigin, rayOriginOffset, rayDir, rayDirOffset, _t16, _t19, _t21, _t22, _t24, _t25, _t26, _t27, _t52, _t53, _t54, _t55, _t56, _t57, _t58, _t59, _t60, _t61, _t62, _t63, _t88, _t88_inv, Math.fma(_t22, _t53, Math.fma(_t21, _t55, -(_t16 * _t56))), Math.fma(_t22, _t54, Math.fma(_t19, _t55, -(_t16 * _t57))), Math.fma(_t22, _t52, Math.fma(_t19, _t56, -(_t21 * _t57))), _t92, _t94, _t88_inv * _t94, 1.0f / _t94, Math.fma(_t22, _t59, Math.fma(_t21, _t61, -(_t16 * _t62))), Math.fma(_t22, _t60, Math.fma(_t19, _t61, -(_t16 * _t63))), Math.fma(_t22, _t58, Math.fma(_t19, _t62, -(_t21 * _t63))), _t98 * 9.536743E-7f, 1.0f / (_t98 <= _t92 * 9.536743E-7f ? _t88 : _t94));
    }

    /** Piece 4 of {@code unprojectRay_zo}, split to fit the inline budget; reached only through it. */
    private static float[] unprojectRay_zo_s95ac2b7b_3(float[] rayOrigin, int rayOriginOffset, float[] rayDir, int rayDirOffset, float _t16, float _t19, float _t21, float _t22, float _t24, float _t25, float _t26, float _t27, float _t52, float _t53, float _t54, float _t55, float _t56, float _t57, float _t58, float _t59, float _t60, float _t61, float _t62, float _t63, float _t88, float _t88_inv, float _t89, float _t90, float _t91, float _t92, float _t94, float _sp0, float _t94_inv, float _t95, float _t96, float _t97, float _t99, float _t100_inv) {
        if (_t92 <= _t99) {
            rayOrigin[rayOriginOffset + 0] = -(_t95 * _t94_inv);
            rayOrigin[rayOriginOffset + 1] = _t96 * _t94_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t97 * _t94_inv);
        } else {
            rayOrigin[rayOriginOffset + 0] = -(_t89 * _t88_inv);
            rayOrigin[rayOriginOffset + 1] = _t90 * _t88_inv;
            rayOrigin[rayOriginOffset + 2] = -(_t91 * _t88_inv);
        }
        if (_t92 <= _t99) {
            rayDir[rayDirOffset + 0] = Math.fma(_t24, _t55, Math.fma(_t16, _t56, Math.fma(_t25, _t53, _t88 * _t95 / _t94))) * _t94_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t19, _t55, Math.fma(_t26, _t57, Math.fma(_t22, _t54, -(_t88 * _t96 / _t94)))) * _t94_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t27, _t56, Math.fma(_t21, _t57, Math.fma(_t25, _t52, _t88 * _t97 / _t94))) * _t94_inv;
        } else {
            rayDir[rayDirOffset + 0] = Math.fma(_t24, _t61, Math.fma(_t16, _t62, Math.fma(_t25, _t59, _sp0 * _t89))) * _t100_inv;
            rayDir[rayDirOffset + 1] = Math.fma(_t19, _t61, Math.fma(_t26, _t63, Math.fma(_t22, _t60, -(_t90 * _sp0)))) * _t100_inv;
            rayDir[rayDirOffset + 2] = Math.fma(_t27, _t62, Math.fma(_t21, _t63, Math.fma(_t25, _t58, _t91 * _sp0))) * _t100_inv;
        }
        return rayOrigin;
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
