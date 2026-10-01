// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2x3Ops} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float2x3Ops} and its sibling kernel units. Not public API.
 */
public final class Float2x3OpsKernelsArray {
    private Float2x3OpsKernelsArray() {}

    public static float[] invert_degenerate(float[] dest, int destOffset, float[] src, int srcOffset) {
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
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        dest[destOffset] = _t8 * _sp0;
        dest[destOffset + 1] = -(_t11 * _sp0);
        dest[destOffset + 2] = -(_t10 * _sp1);
        dest[destOffset + 3] = _t9 * _sp1;
        dest[destOffset + 4] = -(Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv);
        dest[destOffset + 5] = -(Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv);
        return dest;
    }

    public static float[] invertProduct_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
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
        float _t6 = Math.fma(_other01, _self10, _other11 * _self11);
        float _t7 = Math.fma(_other00, _self10, _other10 * _self11);
        float _t8 = Math.fma(_other00, _self00, _other10 * _self01);
        float _t9 = Math.fma(_other01, _self00, _other11 * _self01);
        float _t12 = unitScale(_t7, _t6, _t7);
        float _t13 = unitScale(_t8, _t9, _t8);
        float _t18 = _t6 * _t12;
        float _t19 = _t8 * _t13;
        float _t20 = _t7 * _t12;
        float _t21 = _t9 * _t13;
        float _t28_inv = 1.0f / Math.fma(_t19, _t18, -(_t20 * _t21));
        float _sp0 = _t13 * _t28_inv;
        dest[destOffset] = _t18 * _sp0;
        dest[destOffset + 1] = -(_t20 * _sp0);
        return invertProduct_degenerate_sffcca8a5_1(dest, destOffset, _t18, _t19, _t20, _t21, Math.fma(_other02, _self00, Math.fma(_other12, _self01, _self02)) * _t13, Math.fma(_other02, _self10, Math.fma(_other12, _self11, _self12)) * _t12, _t28_inv, _t12 * _t28_inv);
    }

    /** Piece 2 of {@code invertProduct_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_degenerate_sffcca8a5_1(float[] dest, int destOffset, float _t18, float _t19, float _t20, float _t21, float _t24, float _t25, float _t28_inv, float _sp1) {
        dest[destOffset + 2] = -(_t21 * _sp1);
        dest[destOffset + 3] = _t19 * _sp1;
        dest[destOffset + 4] = -(Math.fma(_t24, _t18, -(_t25 * _t21)) * _t28_inv);
        dest[destOffset + 5] = -(Math.fma(_t25, _t19, -(_t24 * _t20)) * _t28_inv);
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
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _tx = t[tOffset];
        float _ty = t[tOffset + 1];
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
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self01;
        dest[destOffset + 3] = _self11;
        dest[destOffset + 4] = _self02 + _vx;
        dest[destOffset + 5] = _self12 + _vy;
        return dest;
    }

    public static float[] translate_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY) {
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
        dest[destOffset + 4] = Math.fma(_self00, vX, Math.fma(_self01, vY, _self02));
        dest[destOffset + 5] = Math.fma(_self10, vX, Math.fma(_self11, vY, _self12));
        return dest;
    }

    public static float[] translate_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        dest[destOffset] = _self00;
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self01;
        dest[destOffset + 3] = _self11;
        dest[destOffset + 4] = Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12));
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
