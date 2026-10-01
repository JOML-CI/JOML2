// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2Ops} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float2Ops} and its sibling kernel units. Not public API.
 */
public final class Float2OpsKernelsArray {
    private Float2OpsKernelsArray() {}

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _t1 = unitScale(otherX, otherY, otherX);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = otherX * _t1;
        float _t8 = otherY * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        float _t48 = Math.fma(_t47, _t25, _t43);
        float _t49 = Math.fma(_t47, _t27, _t44);
        return slerp_degenerate_sb43cd409_1(dest, destOffset, otherX, otherY, t, _selfx, _selfy, _t1, _t2, java.lang.Math.min(_t2, _t1), _t18, _t19, _t25, _t27, _t38, _t48, _t49, unitScale(_t48, _t49, _t48));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_sb43cd409_1(float[] dest, int destOffset, float otherX, float otherY, float t, float _selfx, float _selfy, float _t1, float _t2, float _t11, float _t18, float _t19, float _t25, float _t27, float _t38, float _t48, float _t49, float _t51) {
        return slerp_degenerate_s962af6d5_1(dest, destOffset, otherX, otherY, t, _selfx, _selfy, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, _t51, _t48 * _t51);
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s962af6d5_1(float[] dest, int destOffset, float otherX, float otherY, float t, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49, float _t51, float _t57) {
        float _t58 = _t49 * _t51;
        float _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t68 = _t37 * Math.sin(_t64);
        float _t69 = _t37 * Math.cos(_t64);
        float _t72, _t73;
        if (_t60 > 0.0f) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
                    dest[destOffset] = Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv;
                    dest[destOffset + 1] = Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv;
                } else {
                    dest[destOffset] = Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv;
                    dest[destOffset + 1] = Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv;
                }
            } else {
                dest[destOffset] = Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv;
                dest[destOffset + 1] = Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv;
            }
        } else {
            dest[destOffset] = Math.fma(t, otherX - _selfx, _selfx);
            dest[destOffset + 1] = Math.fma(t, otherY - _selfy, _selfy);
        }
        return dest;
    }

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _t1 = unitScale(_otherx, _othery, _otherx);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = _otherx * _t1;
        float _t8 = _othery * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = java.lang.Math.min(_t2, _t1);
        float _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        return slerp_degenerate_s83a862c0_1(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, Math.fma(_t47, _t25, _t43), Math.fma(_t47, _t27, _t44));
    }

    /** Piece 2 of {@code slerp_degenerate}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_degenerate_s83a862c0_1(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49) {
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t68 = _t37 * Math.sin(_t64);
        float _t69 = _t37 * Math.cos(_t64);
        float _t72, _t73;
        if (_t60 > 0.0f) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
                    dest[destOffset] = Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv;
                    dest[destOffset + 1] = Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv;
                } else {
                    dest[destOffset] = Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv;
                    dest[destOffset + 1] = Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv;
                }
            } else {
                dest[destOffset] = Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv;
                dest[destOffset + 1] = Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv;
            }
        } else {
            dest[destOffset] = Math.fma(t, _otherx - _selfx, _selfx);
            dest[destOffset + 1] = Math.fma(t, _othery - _selfy, _selfy);
        }
        return dest;
    }

    public static float angleBetween_degenerate(float[] src, int srcOffset, float otherX, float otherY) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _t0 = unitScale(otherX, otherY, otherX);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = otherY * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = otherX * _t0;
        float _t9 = _selfy * _t1;
        float _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(java.lang.Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static float angleBetween_degenerate(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(java.lang.Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static float orientedAngle_degenerate(float[] src, int srcOffset, float otherX, float otherY) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _t0 = unitScale(otherX, otherY, otherX);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = otherY * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = otherX * _t0;
        float _t9 = _selfy * _t1;
        float _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t15 = unitScale(_t14, _t14, _t14);
        float _t19 = _t14 * _t15;
        float _t21 = Math.atan2(java.lang.Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0f ? -_t21 : _t21;
    }

    public static float orientedAngle_degenerate(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t15 = unitScale(_t14, _t14, _t14);
        float _t19 = _t14 * _t15;
        float _t21 = Math.atan2(java.lang.Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0f ? -_t21 : _t21;
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
