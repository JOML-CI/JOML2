// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2x2Ops} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float2x2Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float2x2OpsKernelsArray {
    private Float2x2OpsKernelsArray() {}

    public static float[] invert_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
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
        dest[destOffset] = _t6 * _sp0;
        dest[destOffset + 1] = -(_t9 * _sp0);
        dest[destOffset + 2] = -(_t8 * _sp1);
        dest[destOffset + 3] = _t7 * _sp1;
        return dest;
    }

    /** {@code invert_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invert_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
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
        dest[destOffset] = _t6 * _sp0;
        dest[destOffset + 1] = -(_t9 * _sp0);
        dest[destOffset + 2] = -(_t8 * _sp1);
        dest[destOffset + 3] = _t7 * _sp1;
        return dest;
    }

    public static float[] invertProduct_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_degenerate} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invertProduct_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other01 = other[otherOffset + 2];
        float _other11 = other[otherOffset + 3];
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
        float _t36_inv = 1.0f / _t36;
        float _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        float _t37_inv = 1.0f / _t37;
        return invertProduct_degenerate_sffcca8a5_1_fma(dest, destOffset, _t14 * _t0 / _t36, _t18 * _t3 / _t37, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), -(_t19 * _t2 * _t37_inv), -(_t15 * _t0 * _t36_inv));
    }

    /** {@code invertProduct_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invertProduct_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other01 = other[otherOffset + 2];
        float _other11 = other[otherOffset + 3];
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
        float _t36_inv = 1.0f / _t36;
        float _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        float _t37_inv = 1.0f / _t37;
        return invertProduct_degenerate_sffcca8a5_1_mulAdd(dest, destOffset, _t14 * _t0 / _t36, _t18 * _t3 / _t37, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv), -(_t16 * _t3 * _t37_inv), -(_t19 * _t2 * _t37_inv), -(_t15 * _t0 * _t36_inv));
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_1_fma(float[] dest, int destOffset, float _t48, float _t49, float _t50, float _t53, float _t54, float _t55, float _t56, float _t57) {
        float _t58 = _t48 * _t49;
        float _t59 = _t53 * _t50;
        float _t60 = _t56 * _t48;
        float _t61 = _t55 * _t53;
        dest[destOffset] = java.lang.Math.fma(_t54, _t55, _t58) + java.lang.Math.fma(_t48, _t49, -_t58);
        dest[destOffset + 1] = java.lang.Math.fma(_t57, _t49, _t61) + java.lang.Math.fma(_t55, _t53, -_t61);
        dest[destOffset + 2] = java.lang.Math.fma(_t54, _t50, _t60) + java.lang.Math.fma(_t56, _t48, -_t60);
        dest[destOffset + 3] = java.lang.Math.fma(_t57, _t56, _t59) + java.lang.Math.fma(_t53, _t50, -_t59);
        return dest;
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_1_mulAdd(float[] dest, int destOffset, float _t48, float _t49, float _t50, float _t53, float _t54, float _t55, float _t56, float _t57) {
        float _t58 = _t48 * _t49;
        float _t59 = _t53 * _t50;
        float _t60 = _t56 * _t48;
        float _t61 = _t55 * _t53;
        dest[destOffset] = ((_t54) * (_t55) + (_t58)) + ((_t48) * (_t49) - (_t58));
        dest[destOffset + 1] = ((_t57) * (_t49) + (_t61)) + ((_t55) * (_t53) - (_t61));
        dest[destOffset + 2] = ((_t54) * (_t50) + (_t60)) + ((_t56) * (_t48) - (_t60));
        dest[destOffset + 3] = ((_t57) * (_t56) + (_t59)) + ((_t53) * (_t50) - (_t59));
        return dest;
    }

    public static float[] normal_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
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
        dest[destOffset] = _t6 * _sp0;
        dest[destOffset + 1] = -(_t8 * _sp1);
        dest[destOffset + 2] = -(_t9 * _sp0);
        dest[destOffset + 3] = _t7 * _sp1;
        return dest;
    }

    /** {@code normal_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] normal_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
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
        dest[destOffset] = _t6 * _sp0;
        dest[destOffset + 1] = -(_t8 * _sp1);
        dest[destOffset + 2] = -(_t9 * _sp0);
        dest[destOffset + 3] = _t7 * _sp1;
        return dest;
    }

    public static float[] set_scalar(float[] dest, int destOffset, float[] v, int vOffset) {
        float _v10 = v[vOffset + 1];
        float _v01 = v[vOffset + 2];
        float _v11 = v[vOffset + 3];
        dest[destOffset] = v[vOffset];
        dest[destOffset + 1] = _v10;
        dest[destOffset + 2] = _v01;
        dest[destOffset + 3] = _v11;
        return dest;
    }

    public static float[] setMat2x3_scalar(float[] dest, int destOffset, float[] m, int mOffset) {
        float _m10 = m[mOffset + 1];
        float _m01 = m[mOffset + 2];
        float _m11 = m[mOffset + 3];
        dest[destOffset] = m[mOffset];
        dest[destOffset + 1] = _m10;
        dest[destOffset + 2] = _m01;
        dest[destOffset + 3] = _m11;
        return dest;
    }

    public static float[] to2x3_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self01;
        dest[destOffset + 3] = _self11;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 0.0f;
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
}
