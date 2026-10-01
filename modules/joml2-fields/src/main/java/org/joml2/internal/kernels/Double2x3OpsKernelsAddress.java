// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2x3Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double2x3Ops} and its sibling kernel units. Not public API.
 */
public final class Double2x3OpsKernelsAddress {
    private Double2x3OpsKernelsAddress() {}

    public static long getColumn_unsafe(long dest, long src, int col) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        UnsafeOpsHolder.U.putDouble(dest + 0L, _idxSw0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _idxSw1);
        return dest;
    }

    public static double getRotationAngle_unsafe(long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        return Math.atan2(_self10, _self00);
    }

    public static long getRow_unsafe(long dest, long src, int row) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        UnsafeOpsHolder.U.putDouble(dest + 0L, _idxSw0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _idxSw1);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _idxSw2);
        return dest;
    }

    public static long getTranslation_unsafe(long dest, long src) {
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self12);
        return dest;
    }

    public static double determinant_unsafe(long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        return Math.fma(_self00, _self11, -(_self01 * _self10));
    }

    public static double frobeniusNorm_unsafe(long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        return Math.sqrt(Math.fma(_self12, _self12, Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self02, _self02, Math.fma(_self00, _self00, _self01 * _self01))))));
    }

    public static long invert_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return Double2x3OpsKernelsAddress.invert_degenerate(dest, src);
        double _t3_inv = 1.0 / _t3;
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self11 * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_self10 * _t3_inv));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_self01 * _t3_inv));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self00 * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -(Math.fma(_self02, _self11, -(_self01 * _self12)) * _t3_inv));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -(Math.fma(_self00, _self12, -(_self02 * _self10)) * _t3_inv));
        return dest;
    }

    public static long invert_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.invert_degenerate_unsafe(dest, src);
        Double2x3OpsKernelsSegment.invert_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
        return dest;
    }

    public static long invert_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t8 = _self11 * _t0;
        double _t9 = _self00 * _t1;
        double _t10 = _self01 * _t1;
        double _t11 = _self10 * _t0;
        double _t12 = _self02 * _t1;
        double _t13 = _self12 * _t0;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t8 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t11 * _sp0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t10 * _sp1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t9 * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -(Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -(Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv));
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _t6 = Math.fma(_other01, _self10, _other11 * _self11);
        double _t7 = Math.fma(_other00, _self00, _other10 * _self01);
        double _t8 = Math.fma(_other00, _self10, _other10 * _self11);
        double _t9 = Math.fma(_other01, _self00, _other11 * _self01);
        double _t10 = Math.fma(_other02, _self00, Math.fma(_other12, _self01, _self02));
        double _t11 = Math.fma(_other02, _self10, Math.fma(_other12, _self11, _self12));
        double _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(Math.abs(_t15) > 2.2250738585072014E-308 && Math.abs(_t15) < 4.49423283715579E307)) return Double2x3OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        double _t15_inv = 1.0 / _t15;
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t6 * _t15_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t8 * _t15_inv));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t9 * _t15_inv));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t7 * _t15_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -(Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -(Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv));
        return dest;
    }

    public static long invertProduct_degenerate(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.invertProduct_degenerate_unsafe(dest, src, other);
        Double2x3OpsKernelsSegment.invertProduct_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 48L), 0L);
        return dest;
    }

    public static long invertProduct_degenerate_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _t6 = Math.fma(_other01, _self10, _other11 * _self11);
        double _t7 = Math.fma(_other00, _self10, _other10 * _self11);
        double _t8 = Math.fma(_other00, _self00, _other10 * _self01);
        double _t9 = Math.fma(_other01, _self00, _other11 * _self01);
        double _t12 = unitScale(_t7, _t6, _t7);
        double _t13 = unitScale(_t8, _t9, _t8);
        double _t18 = _t6 * _t12;
        double _t19 = _t8 * _t13;
        double _t20 = _t7 * _t12;
        double _t21 = _t9 * _t13;
        double _t24 = Math.fma(_other02, _self00, Math.fma(_other12, _self01, _self02)) * _t13;
        double _t25 = Math.fma(_other02, _self10, Math.fma(_other12, _self11, _self12)) * _t12;
        double _t28_inv = 1.0 / Math.fma(_t19, _t18, -(_t20 * _t21));
        double _sp1 = _t12 * _t28_inv;
        double _sp0 = _t13 * _t28_inv;
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t18 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t20 * _sp0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t21 * _sp1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t19 * _sp1);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -(Math.fma(_t24, _t18, -(_t25 * _t21)) * _t28_inv));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -(Math.fma(_t25, _t19, -(_t24 * _t20)) * _t28_inv));
        return dest;
    }

    public static long transpose_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eother0 = UnsafeOpsHolder.U.getDouble(other + _lo * 8L);
            double _eother1 = UnsafeOpsHolder.U.getDouble(other + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eother0 + _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eother1 + _eself1);
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, double scalar) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, scalar * _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, scalar * _eself1);
        }
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, -_eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, -_eself1);
        }
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eother0 = UnsafeOpsHolder.U.getDouble(other + _lo * 8L);
            double _eother1 = UnsafeOpsHolder.U.getDouble(other + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0 - _eother0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1 - _eother1);
        }
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _ev0 = UnsafeOpsHolder.U.getDouble(v + _lo * 8L);
            double _ev1 = UnsafeOpsHolder.U.getDouble(v + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _ev0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _ev1);
        }
        return dest;
    }

    public static long setMat2x2_unsafe(long dest, long m) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m + 0L);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 24L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _m00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _m10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _m01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _m11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long setMat3x3_unsafe(long dest, long m) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _lom = _l * 3;
            double _em0 = UnsafeOpsHolder.U.getDouble(m + _lom * 8L);
            double _em1 = UnsafeOpsHolder.U.getDouble(m + (_lom + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _em0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _em1);
        }
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, double tX, double tY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, tX);
        UnsafeOpsHolder.U.putDouble(dest + 40L, tY);
        return dest;
    }

    public static long withTranslation_unsafe(long dest, long src, long t) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _tx = UnsafeOpsHolder.U.getDouble(t + 0L);
        double _ty = UnsafeOpsHolder.U.getDouble(t + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _tx);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _ty);
        return dest;
    }

    public static long to2x2_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        return dest;
    }

    public static long to3x3_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 56L, _self12);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest + 0L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, double t) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eother0 = UnsafeOpsHolder.U.getDouble(other + _lo * 8L);
            double _eother1 = UnsafeOpsHolder.U.getDouble(other + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(t, _eother0 - _eself0, _eself0));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(t, _eother1 - _eself1, _eself1));
        }
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right + 0L);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        double _right02 = UnsafeOpsHolder.U.getDouble(right + 32L);
        double _right12 = UnsafeOpsHolder.U.getDouble(right + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_right01, _self10, _right11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_right02, _self00, Math.fma(_right12, _self01, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_right02, _self10, Math.fma(_right12, _self11, _self12)));
        return dest;
    }

    public static long mulMat2x2_unsafe(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right + 0L);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_right01, _self10, _right11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    public static long mulMat3x3_unsafe(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = UnsafeOpsHolder.U.getDouble(right + _lo * 8L);
            double _eright1 = UnsafeOpsHolder.U.getDouble(right + (_lo + 1) * 8L);
            double _eright2 = UnsafeOpsHolder.U.getDouble(right + (_lo + 2) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11)));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 2) * 8L, _eright2);
        }
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_other00, _self00, _other01 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_other10, _self01, _other11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_other00, _self02, Math.fma(_other01, _self12, _other02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_other10, _self02, Math.fma(_other11, _self12, _other12)));
        return dest;
    }

    public static long preMulMat2x2_unsafe(long dest, long src, long other) {
        double _other00 = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(_other00, _eself0, _other01 * _eself1));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(_other10, _eself0, _other11 * _eself1));
        }
        return dest;
    }

    public static long preMulMat3x3_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other + 0L);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other20 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 32L);
        double _other21 = UnsafeOpsHolder.U.getDouble(other + 40L);
        double _other02 = UnsafeOpsHolder.U.getDouble(other + 48L);
        double _other12 = UnsafeOpsHolder.U.getDouble(other + 56L);
        double _other22 = UnsafeOpsHolder.U.getDouble(other + 64L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_other00, _self00, _other01 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_other20, _self00, _other21 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_other10, _self01, _other11 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_other20, _self01, _other21 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 48L, Math.fma(_other00, _self02, Math.fma(_other01, _self12, _other02)));
        UnsafeOpsHolder.U.putDouble(dest + 56L, Math.fma(_other10, _self02, Math.fma(_other11, _self12, _other12)));
        UnsafeOpsHolder.U.putDouble(dest + 64L, Math.fma(_other20, _self02, Math.fma(_other21, _self12, _other22)));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long other, double weight) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            double _eother0 = UnsafeOpsHolder.U.getDouble(other + _lo * 8L);
            double _eother1 = UnsafeOpsHolder.U.getDouble(other + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, Math.fma(weight, _eother0, _eself0));
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, Math.fma(weight, _eother1, _eself1));
        }
        return dest;
    }

    public static long makeRotation_unsafe(long dest, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, double vX, double vY) {
        UnsafeOpsHolder.U.putDouble(dest + 0L, vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, vY);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _vy);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, double s) {
        UnsafeOpsHolder.U.putDouble(dest + 0L, s);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, double vX, double vY) {
        UnsafeOpsHolder.U.putDouble(dest + 0L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, vX);
        UnsafeOpsHolder.U.putDouble(dest + 40L, vY);
        return dest;
    }

    public static long makeTranslation_unsafe(long dest, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _vx);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _vy);
        return dest;
    }

    public static long makeView_unsafe(long dest, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _t0_inv + _t0_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t1_inv + _t1_inv);
        UnsafeOpsHolder.U.putDouble(dest + 32L, -((left + right) * _t0_inv));
        UnsafeOpsHolder.U.putDouble(dest + 40L, -((bottom + top) * _t1_inv));
        return dest;
    }

    public static long preRotate_unsafe(long dest, long src, double angle) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _t1, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self00, _t0, _self10 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self01, _t1, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self01, _t0, _self11 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self02, _t1, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self02, _t0, _self12 * _t1));
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, double angle, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _t2, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self00, _t0, _self10 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self01, _t2, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self01, _t0, _self11 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(_self02, _t2, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self02, _t0, _self12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0)));
        return dest;
    }

    public static long preRotateAround_unsafe(long dest, long src, long pivot, double angle) {
        double _t0 = Math.sin(angle);
        double _t3 = Math.sin(0.5 * angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t5 = (_t3 + _t3) * _t3;
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _t2, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self00, _t0, _self10 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self01, _t2, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self01, _t0, _self11 * _t2));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_pivotx, _t5, _pivoty * _t0) + Math.fma(_self02, _t2, -(_self12 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self02, _t0, _self12 * _t2) + Math.fma(_pivoty, _t5, -(_pivotx * _t0)));
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0 * vX);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1 * vY);
        }
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, _eself0 * _vx);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, _eself1 * _vy);
        }
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = UnsafeOpsHolder.U.getDouble(src + _lo * 8L);
            double _eself1 = UnsafeOpsHolder.U.getDouble(src + (_lo + 1) * 8L);
            UnsafeOpsHolder.U.putDouble(dest + _lo * 8L, s * _eself0);
            UnsafeOpsHolder.U.putDouble(dest + (_lo + 1) * 8L, s * _eself1);
        }
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, double s, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = 1.0 - s;
        UnsafeOpsHolder.U.putDouble(dest + 0L, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(s, _self02, pivotX * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(s, _self12, pivotY * _t0));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long pivot, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t0 = 1.0 - s;
        UnsafeOpsHolder.U.putDouble(dest + 0L, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(s, _self02, _pivotx * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(s, _self12, _pivoty * _t0));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, sX * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, sY * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, sX * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, sY * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(pivotX, 1.0 - sX, sX * _self02));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(pivotY, 1.0 - sY, sY * _self12));
        return dest;
    }

    public static long preScaleAround_unsafe(long dest, long src, long s, long pivot) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _sx = UnsafeOpsHolder.U.getDouble(s + 0L);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _sx * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sy * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sx * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sy * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_pivotx, 1.0 - _sx, _sx * _self02));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_pivoty, 1.0 - _sy, _sy * _self12));
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02 + vX);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12 + vY);
        return dest;
    }

    public static long preTranslate_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02 + _vx);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12 + _vy);
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, double angle) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _t1, _self01 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self01, _t1, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self11, _t1, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, double angle, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _t2, _self01 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _t2, _self11 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self01, _t2, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self11, _t2, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        return dest;
    }

    public static long rotateAround_unsafe(long dest, long src, long pivot, double angle) {
        double _t0 = Math.sin(angle);
        double _t3 = Math.sin(0.5 * angle);
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        double _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _t2, _self01 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _t2, _self11 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self01, _t2, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self11, _t2, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        return dest;
    }

    public static long scale_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01 * _vy);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11 * _vy);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02);
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12);
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, double s, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        UnsafeOpsHolder.U.putDouble(dest + 0L, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long pivot, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t0 = 1.0 - s;
        double _t1 = _pivotx * _t0;
        double _t2 = _pivoty * _t0;
        UnsafeOpsHolder.U.putDouble(dest + 0L, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        UnsafeOpsHolder.U.putDouble(dest + 0L, sX * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, sX * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, sY * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, sY * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    public static long scaleAround_unsafe(long dest, long src, long s, long pivot) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _sx = UnsafeOpsHolder.U.getDouble(s + 0L);
        double _sy = UnsafeOpsHolder.U.getDouble(s + 8L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(pivot + 0L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(pivot + 8L);
        double _t2 = _pivotx * (1.0 - _sx);
        double _t3 = _pivoty * (1.0 - _sy);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _sx * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sx * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sy * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sy * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static long translate_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 40L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        return dest;
    }

    public static long view_unsafe(long dest, long src, double left, double right, double bottom, double top) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        UnsafeOpsHolder.U.putDouble(dest + 0L, _sp0 * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _sp0 * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _sp1 * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _sp1 * _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        UnsafeOpsHolder.U.putDouble(dest + 40L, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        return dest;
    }

    public static long mulVec3_unsafe(long dest, long src, double vX, double vY, double vZ) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        return dest;
    }

    public static long mulVec3_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(v + 16L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, vX, _self01 * vY));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static long transformDirection_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _vx, _self01 * _vy));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _vx, _self11 * _vy));
        return dest;
    }

    public static long transformPosition_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static long transformPosition_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src + 0L);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _self02 = UnsafeOpsHolder.U.getDouble(src + 32L);
        double _self12 = UnsafeOpsHolder.U.getDouble(src + 40L);
        double _vx = UnsafeOpsHolder.U.getDouble(v + 0L);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest + 0L, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
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
