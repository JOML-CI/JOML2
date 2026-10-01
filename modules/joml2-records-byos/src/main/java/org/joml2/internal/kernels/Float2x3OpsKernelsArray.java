// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2x3Ops} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float2x3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float2x3OpsKernelsArray {
    private Float2x3OpsKernelsArray() {}

    public static float[] invert_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t8 = _self11 * _t0;
        float _t9 = _self00 * _t1;
        float _t10 = _self01 * _t1;
        float _t11 = _self10 * _t0;
        float _t12 = _self02 * _t1;
        float _t13 = _self12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22_inv = 1.0f / (java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        dest[destOffset] = _t8 * _sp0;
        dest[destOffset + 1] = -(_t11 * _sp0);
        dest[destOffset + 2] = -(_t10 * _sp1);
        dest[destOffset + 3] = _t9 * _sp1;
        dest[destOffset + 4] = (java.lang.Math.fma(_t10, _t13, _t18) - java.lang.Math.fma(_t12, _t8, _t18)) * _t22_inv;
        dest[destOffset + 5] = (java.lang.Math.fma(_t12, _t11, _t19) - java.lang.Math.fma(_t9, _t13, _t19)) * _t22_inv;
        return dest;
    }

    /** {@code invert_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invert_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t8 = _self11 * _t0;
        float _t9 = _self00 * _t1;
        float _t10 = _self01 * _t1;
        float _t11 = _self10 * _t0;
        float _t12 = _self02 * _t1;
        float _t13 = _self12 * _t0;
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22_inv = 1.0f / (((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17)));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        dest[destOffset] = _t8 * _sp0;
        dest[destOffset + 1] = -(_t11 * _sp0);
        dest[destOffset + 2] = -(_t10 * _sp1);
        dest[destOffset + 3] = _t9 * _sp1;
        dest[destOffset + 4] = (((_t10) * (_t13) + (_t18)) - ((_t12) * (_t8) + (_t18))) * _t22_inv;
        dest[destOffset + 5] = (((_t12) * (_t11) + (_t19)) - ((_t9) * (_t13) + (_t19))) * _t22_inv;
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
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other01 = other[otherOffset + 2];
        float _other11 = other[otherOffset + 3];
        float _other02 = other[otherOffset + 4];
        float _other12 = other[otherOffset + 5];
        float _t0 = unitScale(_other00, _other01, _other00);
        float _t1 = unitScale(_other10, _other11, _other10);
        float _t2 = unitScale(_self10, _self11, _self10);
        float _t3 = unitScale(_self00, _self01, _self00);
        float _t16 = _other01 * _t0;
        float _t17 = _other00 * _t0;
        float _t18 = _other11 * _t1;
        float _t19 = _other10 * _t1;
        float _t20 = _self10 * _t2;
        float _t23 = _self01 * _t3;
        float _t24 = _self12 * _t2;
        float _t25 = _self02 * _t3;
        float _t26 = _other02 * _t0;
        float _t27 = _other12 * _t1;
        float _t42 = -(_t16 * _t19);
        return invertProduct_degenerate_sffcca8a5_1_fma(dest, destOffset, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _self00 * _t3, _self11 * _t2, _t23, _t24, _t25, _t26, _t27, -(_t23 * _t20), -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42));
    }

    /** {@code invertProduct_degenerate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] invertProduct_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other01 = other[otherOffset + 2];
        float _other11 = other[otherOffset + 3];
        float _other02 = other[otherOffset + 4];
        float _other12 = other[otherOffset + 5];
        float _t0 = unitScale(_other00, _other01, _other00);
        float _t1 = unitScale(_other10, _other11, _other10);
        float _t2 = unitScale(_self10, _self11, _self10);
        float _t3 = unitScale(_self00, _self01, _self00);
        float _t16 = _other01 * _t0;
        float _t17 = _other00 * _t0;
        float _t18 = _other11 * _t1;
        float _t19 = _other10 * _t1;
        float _t20 = _self10 * _t2;
        float _t23 = _self01 * _t3;
        float _t24 = _self12 * _t2;
        float _t25 = _self02 * _t3;
        float _t26 = _other02 * _t0;
        float _t27 = _other12 * _t1;
        float _t42 = -(_t16 * _t19);
        return invertProduct_degenerate_sffcca8a5_1_mulAdd(dest, destOffset, _t0, _t1, _t2, _t3, _t16, _t17, _t18, _t19, _t20, _self00 * _t3, _self11 * _t2, _t23, _t24, _t25, _t26, _t27, -(_t23 * _t20), -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27), -(_t26 * _t19), ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42)));
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_1_fma(float[] dest, int destOffset, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t43, float _t44, float _t45, float _t46, float _t47, float _t56) {
        float _t56_inv = 1.0f / _t56;
        float _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        float _t57_inv = 1.0f / _t57;
        float _t70 = _t18 * _t0 / _t56;
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t86 = _t70 * _t71;
        float _t88 = _t78 * _t70;
        float _t89 = _t77 * _t75;
        dest[destOffset] = java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86);
        dest[destOffset + 1] = java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89);
        dest[destOffset + 2] = java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88);
        return invertProduct_degenerate_sffcca8a5_2_fma(dest, destOffset, _t16, _t17, _t18, _t19, _t26, _t27, _t46, _t47, _t56_inv, _t70, _t72, _t75, _t76, _t78, _t79, -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv), -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv), _t75 * _t72);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_1_mulAdd(float[] dest, int destOffset, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t43, float _t44, float _t45, float _t46, float _t47, float _t56) {
        float _t56_inv = 1.0f / _t56;
        float _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        float _t57_inv = 1.0f / _t57;
        float _t70 = _t18 * _t0 / _t56;
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t86 = _t70 * _t71;
        float _t88 = _t78 * _t70;
        float _t89 = _t77 * _t75;
        dest[destOffset] = ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86));
        dest[destOffset + 1] = ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89));
        dest[destOffset + 2] = ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88));
        return invertProduct_degenerate_sffcca8a5_2_mulAdd(dest, destOffset, _t16, _t17, _t18, _t19, _t26, _t27, _t46, _t47, _t56_inv, _t70, _t72, _t75, _t76, _t78, _t79, -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv), -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv), _t75 * _t72);
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_2_fma(float[] dest, int destOffset, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t46, float _t47, float _t56_inv, float _t70, float _t72, float _t75, float _t76, float _t78, float _t79, float _t84, float _t85, float _t87) {
        dest[destOffset + 3] = java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87);
        dest[destOffset + 4] = java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv)));
        dest[destOffset + 5] = java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv)));
        return dest;
    }

    /** Piece 3 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_2_mulAdd(float[] dest, int destOffset, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t46, float _t47, float _t56_inv, float _t70, float _t72, float _t75, float _t76, float _t78, float _t79, float _t84, float _t85, float _t87) {
        dest[destOffset + 3] = ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87));
        dest[destOffset + 4] = ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv))));
        dest[destOffset + 5] = ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv))));
        return dest;
    }

    public static float[] set_scalar(float[] dest, int destOffset, float[] v, int vOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _ev1 = v[vOffset + _lo + 1];
            dest[destOffset + _lo] = v[vOffset + _lo];
            dest[destOffset + _lo + 1] = _ev1;
        }
        return dest;
    }

    public static float[] setMat2x2_scalar(float[] dest, int destOffset, float[] m, int mOffset) {
        float _m10 = m[mOffset + 1];
        float _m01 = m[mOffset + 2];
        float _m11 = m[mOffset + 3];
        dest[destOffset] = m[mOffset];
        dest[destOffset + 1] = _m10;
        dest[destOffset + 2] = _m01;
        dest[destOffset + 3] = _m11;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 0.0f;
        return dest;
    }

    public static float[] withTranslation_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float tX, float tY) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self01;
        dest[destOffset + 3] = _self11;
        dest[destOffset + 4] = tX;
        dest[destOffset + 5] = tY;
        return dest;
    }

    public static float[] withTranslation_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] t, int tOffset) {
        float _tx = t[tOffset];
        float _ty = t[tOffset + 1];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self01;
        dest[destOffset + 3] = _self11;
        dest[destOffset + 4] = _tx;
        dest[destOffset + 5] = _ty;
        return dest;
    }

    public static float[] to2x2_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self01;
        dest[destOffset + 3] = _self11;
        return dest;
    }

    public static float[] preTranslate_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self01;
        dest[destOffset + 3] = _self11;
        dest[destOffset + 4] = _self02 + vX;
        dest[destOffset + 5] = _self12 + vY;
        return dest;
    }

    public static float[] preTranslate_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self01;
        dest[destOffset + 3] = _self11;
        dest[destOffset + 4] = _self02 + _vx;
        dest[destOffset + 5] = _self12 + _vy;
        return dest;
    }

    public static float[] translate_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src[srcOffset];
            float _self10 = src[srcOffset + 1];
            float _self01 = src[srcOffset + 2];
            float _self11 = src[srcOffset + 3];
            float _self02 = src[srcOffset + 4];
            float _self12 = src[srcOffset + 5];
            dest[destOffset] = _self00;
            dest[destOffset + 1] = _self10;
            dest[destOffset + 2] = _self01;
            dest[destOffset + 3] = _self11;
            dest[destOffset + 4] = java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02));
            dest[destOffset + 5] = java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12));
            return dest;
        } else {
            float _self00 = src[srcOffset];
            float _self10 = src[srcOffset + 1];
            float _self01 = src[srcOffset + 2];
            float _self11 = src[srcOffset + 3];
            float _self02 = src[srcOffset + 4];
            float _self12 = src[srcOffset + 5];
            dest[destOffset] = _self00;
            dest[destOffset + 1] = _self10;
            dest[destOffset + 2] = _self01;
            dest[destOffset + 3] = _self11;
            dest[destOffset + 4] = ((_self00) * (vX) + (((_self01) * (vY) + (_self02))));
            dest[destOffset + 5] = ((_self10) * (vX) + (((_self11) * (vY) + (_self12))));
            return dest;
        }
    }

    public static float[] translate_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = v[vOffset];
            float _vy = v[vOffset + 1];
            float _self00 = src[srcOffset];
            float _self10 = src[srcOffset + 1];
            float _self01 = src[srcOffset + 2];
            float _self11 = src[srcOffset + 3];
            float _self02 = src[srcOffset + 4];
            float _self12 = src[srcOffset + 5];
            dest[destOffset] = _self00;
            dest[destOffset + 1] = _self10;
            dest[destOffset + 2] = _self01;
            dest[destOffset + 3] = _self11;
            dest[destOffset + 4] = java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02));
            dest[destOffset + 5] = java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12));
            return dest;
        } else {
            float _vx = v[vOffset];
            float _vy = v[vOffset + 1];
            float _self00 = src[srcOffset];
            float _self10 = src[srcOffset + 1];
            float _self01 = src[srcOffset + 2];
            float _self11 = src[srcOffset + 3];
            float _self02 = src[srcOffset + 4];
            float _self12 = src[srcOffset + 5];
            dest[destOffset] = _self00;
            dest[destOffset + 1] = _self10;
            dest[destOffset + 2] = _self01;
            dest[destOffset + 3] = _self11;
            dest[destOffset + 4] = ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02))));
            dest[destOffset + 5] = ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12))));
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
