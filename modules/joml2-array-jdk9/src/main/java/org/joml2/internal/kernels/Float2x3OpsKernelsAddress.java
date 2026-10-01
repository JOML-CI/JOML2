// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2x3Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float2x3Ops} and its sibling kernel units. Not public API.
 */
public final class Float2x3OpsKernelsAddress {
    private Float2x3OpsKernelsAddress() {}

    public static long getColumn_unsafe(long dest, long src, int col) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        UnsafeOpsHolder.U.putFloat(dest, _idxSw0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _idxSw1);
        return dest;
    }

    public static float getRotationAngle_unsafe(long src) {
        return Math.atan2(UnsafeOpsHolder.U.getFloat(src + 4L), UnsafeOpsHolder.U.getFloat(src));
    }

    public static long getRow_unsafe(long dest, long src, int row) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        UnsafeOpsHolder.U.putFloat(dest, _idxSw0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _idxSw1);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _idxSw2);
        return dest;
    }

    public static long getTranslation_unsafe(long dest, long src) {
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        UnsafeOpsHolder.U.putFloat(dest, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self12);
        return dest;
    }

    public static float determinant_unsafe(long src) {
        return Math.fma(UnsafeOpsHolder.U.getFloat(src), UnsafeOpsHolder.U.getFloat(src + 12L), -(UnsafeOpsHolder.U.getFloat(src + 8L) * UnsafeOpsHolder.U.getFloat(src + 4L)));
    }

    public static float frobeniusNorm_unsafe(long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        return (float) java.lang.Math.sqrt(Math.fma(_self12, _self12, Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self02, _self02, Math.fma(_self00, _self00, _self01 * _self01))))));
    }

    public static long invert_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return Float2x3OpsKernelsAddress.invert_degenerate(dest, src);
        float _t3_inv = 1.0f / _t3;
        UnsafeOpsHolder.U.putFloat(dest, _self11 * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_self10 * _t3_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_self01 * _t3_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self00 * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -(Math.fma(_self02, _self11, -(_self01 * _self12)) * _t3_inv));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -(Math.fma(_self00, _self12, -(_self02 * _self10)) * _t3_inv));
        return dest;
    }

    public static long invert_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.invert_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invert_degenerate_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
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
        UnsafeOpsHolder.U.putFloat(dest, _t8 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t11 * _sp0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t10 * _sp1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t9 * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -(Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -(Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv));
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 20L);
        return invertProduct_unsafe_sec86c98d_1(dest, src, other, _self00, _self10, _self01, _self11, _other02, _other12, Math.fma(_other01, _self10, _other11 * _self11), Math.fma(_other00, _self00, _other10 * _self01), Math.fma(_other00, _self10, _other10 * _self11), Math.fma(_other01, _self00, _other11 * _self01));
    }

    /** Piece 2 of {@code invertProduct_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_unsafe_sec86c98d_1(long dest, long src, long other, float _self00, float _self10, float _self01, float _self11, float _other02, float _other12, float _t6, float _t7, float _t8, float _t9) {
        float _t10 = Math.fma(_other02, _self00, Math.fma(_other12, _self01, UnsafeOpsHolder.U.getFloat(src + 16L)));
        float _t11 = Math.fma(_other02, _self10, Math.fma(_other12, _self11, UnsafeOpsHolder.U.getFloat(src + 20L)));
        float _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 1.1754944E-38f && java.lang.Math.abs(_t15) < 8.507059E37f)) return Float2x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        float _t15_inv = 1.0f / _t15;
        UnsafeOpsHolder.U.putFloat(dest, _t6 * _t15_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t8 * _t15_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t9 * _t15_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t7 * _t15_inv);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -(Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -(Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv));
        return dest;
    }

    public static long invertProduct_degenerate(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.invertProduct_degenerate_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invertProduct_degenerate_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 20L);
        float _t6 = Math.fma(_other01, _self10, _other11 * _self11);
        float _t7 = Math.fma(_other00, _self10, _other10 * _self11);
        float _t8 = Math.fma(_other00, _self00, _other10 * _self01);
        float _t9 = Math.fma(_other01, _self00, _other11 * _self01);
        float _t12 = unitScale(_t7, _t6, _t7);
        float _t13 = unitScale(_t8, _t9, _t8);
        return invertProduct_degenerate_unsafe_s3fc4d1ee_1(dest, _t12, _t13, _t6 * _t12, _t8 * _t13, _t7 * _t12, _t9 * _t13, Math.fma(_other02, _self00, Math.fma(_other12, _self01, _self02)) * _t13, Math.fma(_other02, _self10, Math.fma(_other12, _self11, _self12)) * _t12);
    }

    /** Piece 2 of {@code invertProduct_degenerate_unsafe}, split to fit the inline budget; reached only through it. */
    private static long invertProduct_degenerate_unsafe_s3fc4d1ee_1(long dest, float _t12, float _t13, float _t18, float _t19, float _t20, float _t21, float _t24, float _t25) {
        float _t28_inv = 1.0f / Math.fma(_t19, _t18, -(_t20 * _t21));
        float _sp1 = _t12 * _t28_inv;
        float _sp0 = _t13 * _t28_inv;
        UnsafeOpsHolder.U.putFloat(dest, _t18 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t20 * _sp0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t21 * _sp1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t19 * _sp1);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -(Math.fma(_t24, _t18, -(_t25 * _t21)) * _t28_inv));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -(Math.fma(_t25, _t19, -(_t24 * _t20)) * _t28_inv));
        return dest;
    }

    public static long transpose_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self12);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eother0 = UnsafeOpsHolder.U.getFloat(other + _lo * 4L);
            float _eother1 = UnsafeOpsHolder.U.getFloat(other + (_lo + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eother0 + _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eother1 + _eself1);
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float scalar) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, scalar * _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, scalar * _eself1);
        }
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, -_eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, -_eself1);
        }
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eother0 = UnsafeOpsHolder.U.getFloat(other + _lo * 4L);
            float _eother1 = UnsafeOpsHolder.U.getFloat(other + (_lo + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0 - _eother0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1 - _eother1);
        }
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _ev0 = UnsafeOpsHolder.U.getFloat(v + _lo * 4L);
            float _ev1 = UnsafeOpsHolder.U.getFloat(v + (_lo + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _ev0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _ev1);
        }
        return dest;
    }

    public static long setMat2x2_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _m00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _m10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _m01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _m11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        return dest;
    }

    public static long setMat3x3_unsafe(long dest, long m) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _lom = _l * 3;
            float _em0 = UnsafeOpsHolder.U.getFloat(m + _lom * 4L);
            float _em1 = UnsafeOpsHolder.U.getFloat(m + (_lom + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _em0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _em1);
        }
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, float tX, float tY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, tX);
        UnsafeOpsHolder.U.putFloat(dest + 20L, tY);
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, long t) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _tx = UnsafeOpsHolder.U.getFloat(t);
        float _ty = UnsafeOpsHolder.U.getFloat(t + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _tx);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _ty);
        return dest;
    }

    public static long to2x2_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11);
        return dest;
    }

    public static long to3x3_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 28L, _self12);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, float t) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eother0 = UnsafeOpsHolder.U.getFloat(other + _lo * 4L);
            float _eother1 = UnsafeOpsHolder.U.getFloat(other + (_lo + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(t, _eother0 - _eself0, _eself0));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(t, _eother1 - _eself1, _eself1));
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long right) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _right00 = UnsafeOpsHolder.U.getFloat(right);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 12L);
        float _right02 = UnsafeOpsHolder.U.getFloat(right + 16L);
        float _right12 = UnsafeOpsHolder.U.getFloat(right + 20L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_right01, _self10, _right11 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_right02, _self00, Math.fma(_right12, _self01, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_right02, _self10, Math.fma(_right12, _self11, _self12)));
        return dest;
    }

    public static long mulMat2x2_unsafe(long dest, long src, long right) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _right00 = UnsafeOpsHolder.U.getFloat(right);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_right01, _self10, _right11 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self12);
        return dest;
    }

    public static long mulMat3x3_unsafe(long dest, long src, long right) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = UnsafeOpsHolder.U.getFloat(right + _lo * 4L);
            float _eright1 = UnsafeOpsHolder.U.getFloat(right + (_lo + 1) * 4L);
            float _eright2 = UnsafeOpsHolder.U.getFloat(right + (_lo + 2) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11)));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 2) * 4L, _eright2);
        }
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 20L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_other00, _self00, _other01 * _self10));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_other10, _self01, _other11 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_other00, _self02, Math.fma(_other01, _self12, _other02)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_other10, _self02, Math.fma(_other11, _self12, _other12)));
        return dest;
    }

    public static long preMulMat2x2_unsafe(long dest, long src, long other) {
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(_other00, _eself0, _other01 * _eself1));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(_other10, _eself0, _other11 * _eself1));
        }
        return dest;
    }

    public static long preMulMat3x3_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other20 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 16L);
        float _other21 = UnsafeOpsHolder.U.getFloat(other + 20L);
        float _other02 = UnsafeOpsHolder.U.getFloat(other + 24L);
        float _other12 = UnsafeOpsHolder.U.getFloat(other + 28L);
        float _other22 = UnsafeOpsHolder.U.getFloat(other + 32L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_other00, _self00, _other01 * _self10));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_other20, _self00, _other21 * _self10));
        return preMulMat3x3_unsafe_s8133e2b_1(dest, _self01, _self11, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_unsafe}, split to fit the inline budget; reached only through it. */
    private static long preMulMat3x3_unsafe_s8133e2b_1(long dest, float _self01, float _self11, float _self02, float _self12, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_other10, _self01, _other11 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_other20, _self01, _other21 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 24L, Math.fma(_other00, _self02, Math.fma(_other01, _self12, _other02)));
        UnsafeOpsHolder.U.putFloat(dest + 28L, Math.fma(_other10, _self02, Math.fma(_other11, _self12, _other12)));
        UnsafeOpsHolder.U.putFloat(dest + 32L, Math.fma(_other20, _self02, Math.fma(_other21, _self12, _other22)));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long other, float weight) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            float _eother0 = UnsafeOpsHolder.U.getFloat(other + _lo * 4L);
            float _eother1 = UnsafeOpsHolder.U.getFloat(other + (_lo + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, Math.fma(weight, _eother0, _eself0));
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, Math.fma(weight, _eother1, _eself1));
        }
        return dest;
    }

    public static long makeRotation_unsafe(long dest, float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, float vX, float vY) {
        UnsafeOpsHolder.U.putFloat(dest, vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, vY);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _vy);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, float s) {
        UnsafeOpsHolder.U.putFloat(dest, s);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, float vX, float vY) {
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, vX);
        UnsafeOpsHolder.U.putFloat(dest + 20L, vY);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _vy);
        return dest;
    }

    public static long makeView_unsafe(long dest, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        UnsafeOpsHolder.U.putFloat(dest, _t0_inv + _t0_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t1_inv + _t1_inv);
        UnsafeOpsHolder.U.putFloat(dest + 16L, -((left + right) * _t0_inv));
        UnsafeOpsHolder.U.putFloat(dest + 20L, -((bottom + top) * _t1_inv));
        return dest;
    }

    public static long preRotate_unsafe(long dest, long src, float angle) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t1, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self00, _t0, _self10 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self01, _t1, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self01, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self02, _t1, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self02, _t0, _self12 * _t1));
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, float angle, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t2, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self00, _t0, _self10 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self01, _t2, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self01, _t0, _self11 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(_self02, _t2, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self02, _t0, _self12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0)));
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, long pivot, float angle) {
        float _t0 = Math.sin(angle);
        float _t3 = Math.sin(0.5f * angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t5 = (_t3 + _t3) * _t3;
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t2, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self00, _t0, _self10 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self01, _t2, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self01, _t0, _self11 * _t2));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_pivotx, _t5, _pivoty * _t0) + Math.fma(_self02, _t2, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self02, _t0, _self12 * _t2) + Math.fma(_pivoty, _t5, -(_pivotx * _t0)));
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, float vX, float vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0 * vX);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1 * vY);
        }
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, _eself0 * _vx);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, _eself1 * _vy);
        }
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, float s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = UnsafeOpsHolder.U.getFloat(src + _lo * 4L);
            float _eself1 = UnsafeOpsHolder.U.getFloat(src + (_lo + 1) * 4L);
            UnsafeOpsHolder.U.putFloat(dest + _lo * 4L, s * _eself0);
            UnsafeOpsHolder.U.putFloat(dest + (_lo + 1) * 4L, s * _eself1);
        }
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, float s, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _t0 = 1.0f - s;
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(s, _self02, pivotX * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(s, _self12, pivotY * _t0));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long pivot, float s) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _t0 = 1.0f - s;
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(s, _self02, _pivotx * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(s, _self12, _pivoty * _t0));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        UnsafeOpsHolder.U.putFloat(dest, sX * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, sY * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, sX * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, sY * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(pivotX, 1.0f - sX, sX * _self02));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(pivotY, 1.0f - sY, sY * _self12));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long s, long pivot) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _sx = UnsafeOpsHolder.U.getFloat(s);
        float _sy = UnsafeOpsHolder.U.getFloat(s + 4L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _sx * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _sy * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _sx * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _sy * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_pivotx, 1.0f - _sx, _sx * _self02));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_pivoty, 1.0f - _sy, _sy * _self12));
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self02 + vX);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self12 + vY);
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self02 + _vx);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self12 + _vy);
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, float angle) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t1, _self01 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self01, _t1, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self11, _t1, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self12);
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, float angle, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t2, _self01 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _t2, _self11 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self01, _t2, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self11, _t2, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long pivot, float angle) {
        float _t0 = Math.sin(angle);
        float _t3 = Math.sin(0.5f * angle);
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(pivot);
        float _pivoty = UnsafeOpsHolder.U.getFloat(pivot + 4L);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        float _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _t2, _self01 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _t2, _self11 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self01, _t2, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self11, _t2, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        return dest;
    }

    public static long scale_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        UnsafeOpsHolder.U.putFloat(dest, _self00 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self12);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _self00 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01 * _vy);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11 * _vy);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self12);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, float s) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self02);
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self12);
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, float s, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long pivot, float s) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _t0 = 1.0f - s;
        float _t1 = UnsafeOpsHolder.U.getFloat(pivot) * _t0;
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * _t0;
        UnsafeOpsHolder.U.putFloat(dest, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        UnsafeOpsHolder.U.putFloat(dest, sX * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, sX * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, sY * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, sY * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long s, long pivot) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _sx = UnsafeOpsHolder.U.getFloat(s);
        float _sy = UnsafeOpsHolder.U.getFloat(s + 4L);
        float _t2 = UnsafeOpsHolder.U.getFloat(pivot) * (1.0f - _sx);
        float _t3 = UnsafeOpsHolder.U.getFloat(pivot + 4L) * (1.0f - _sy);
        UnsafeOpsHolder.U.putFloat(dest, _sx * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _sx * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _sy * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _sy * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 20L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        return dest;
    }

    public static long view_unsafe(long dest, long src, float left, float right, float bottom, float top) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        UnsafeOpsHolder.U.putFloat(dest, _sp0 * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _sp0 * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _sp1 * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _sp1 * _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        UnsafeOpsHolder.U.putFloat(dest + 20L, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        return dest;
    }

    public static long mulVec3_unsafe(long dest, long src, float vX, float vY, float vZ) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        return dest;
    }

    public static long mulVec3_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(v + 8L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, vX, _self01 * vY));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _vx, _self01 * _vy));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _vx, _self11 * _vy));
        return dest;
    }

    public static long transformPosition_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static long transformPosition_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _self02 = UnsafeOpsHolder.U.getFloat(src + 16L);
        float _self12 = UnsafeOpsHolder.U.getFloat(src + 20L);
        float _vx = UnsafeOpsHolder.U.getFloat(v);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
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
