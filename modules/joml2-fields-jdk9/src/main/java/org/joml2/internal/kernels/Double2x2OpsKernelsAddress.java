// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2x2Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double2x2Ops} and its sibling kernel units. Not public API.
 */
public final class Double2x2OpsKernelsAddress {
    private Double2x2OpsKernelsAddress() {}

    public static long getColumn_unsafe(long dest, long src, int col) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        UnsafeOpsHolder.U.putDouble(dest, _idxSw0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _idxSw1);
        return dest;
    }

    public static double getRotationAngle_unsafe(long src) {
        return Math.atan2(UnsafeOpsHolder.U.getDouble(src + 8L), UnsafeOpsHolder.U.getDouble(src));
    }

    public static long getRow_unsafe(long dest, long src, int row) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _idxSw0;
        double _idxSw1;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        UnsafeOpsHolder.U.putDouble(dest, _idxSw0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _idxSw1);
        return dest;
    }

    public static long cofactor_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_self10);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self00);
        return dest;
    }

    public static double determinant_unsafe(long src) {
        return Math.fma(UnsafeOpsHolder.U.getDouble(src), UnsafeOpsHolder.U.getDouble(src + 24L), -(UnsafeOpsHolder.U.getDouble(src + 16L) * UnsafeOpsHolder.U.getDouble(src + 8L)));
    }

    public static double frobeniusNorm_unsafe(long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        return java.lang.Math.sqrt(Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self00, _self00, _self01 * _self01))));
    }

    public static long invert_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return Double2x2OpsKernelsAddress.invert_degenerate(dest, src);
        double _t3_inv = 1.0 / _t3;
        UnsafeOpsHolder.U.putDouble(dest, _self11 * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_self10 * _t3_inv));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_self01 * _t3_inv));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self00 * _t3_inv);
        return dest;
    }

    public static long invert_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.invert_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invert_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
        UnsafeOpsHolder.U.putDouble(dest, _t6 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t9 * _sp0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t8 * _sp1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t7 * _sp1);
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t4 = Math.fma(_other01, _self10, _other11 * _self11);
        double _t5 = Math.fma(_other00, _self00, _other10 * _self01);
        double _t6 = Math.fma(_other00, _self10, _other10 * _self11);
        double _t7 = Math.fma(_other01, _self00, _other11 * _self01);
        double _t11 = Math.fma(_t5, _t4, -(_t6 * _t7));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return Double2x2OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        double _t11_inv = 1.0 / _t11;
        UnsafeOpsHolder.U.putDouble(dest, _t4 * _t11_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t6 * _t11_inv));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t7 * _t11_inv));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t5 * _t11_inv);
        return dest;
    }

    public static long invertProduct_degenerate(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.invertProduct_degenerate_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long invertProduct_degenerate_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        double _t4 = Math.fma(_other01, _self10, _other11 * _self11);
        double _t5 = Math.fma(_other00, _self10, _other10 * _self11);
        double _t6 = Math.fma(_other00, _self00, _other10 * _self01);
        double _t7 = Math.fma(_other01, _self00, _other11 * _self01);
        double _t8 = unitScale(_t5, _t4, _t5);
        double _t9 = unitScale(_t6, _t7, _t6);
        double _t14 = _t4 * _t8;
        double _t15 = _t6 * _t9;
        double _t16 = _t5 * _t8;
        double _t17 = _t7 * _t9;
        double _t20_inv = 1.0 / Math.fma(_t15, _t14, -(_t16 * _t17));
        double _sp1 = _t8 * _t20_inv;
        double _sp0 = _t9 * _t20_inv;
        UnsafeOpsHolder.U.putDouble(dest, _t14 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t16 * _sp0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t17 * _sp1));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t15 * _sp1);
        return dest;
    }

    public static long normal_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return Double2x2OpsKernelsAddress.normal_degenerate(dest, src);
        double _t3_inv = 1.0 / _t3;
        UnsafeOpsHolder.U.putDouble(dest, _self11 * _t3_inv);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_self01 * _t3_inv));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_self10 * _t3_inv));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self00 * _t3_inv);
        return dest;
    }

    public static long normal_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.normal_degenerate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    public static long normal_degenerate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
        UnsafeOpsHolder.U.putDouble(dest, _t6 * _sp0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -(_t8 * _sp1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, -(_t9 * _sp0));
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t7 * _sp1);
        return dest;
    }

    public static double trace_unsafe(long src) {
        return UnsafeOpsHolder.U.getDouble(src) + UnsafeOpsHolder.U.getDouble(src + 24L);
    }

    public static long transpose_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _other00 + _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _other10 + _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _other01 + _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _other11 + _self11);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, double scalar) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, scalar * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, scalar * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, scalar * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, scalar * _self11);
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, -_self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, -_self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, -_self11);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _self00 - _other00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10 - _other10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01 - _other01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11 - _other11);
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        double _v00 = UnsafeOpsHolder.U.getDouble(v);
        double _v10 = UnsafeOpsHolder.U.getDouble(v + 8L);
        double _v01 = UnsafeOpsHolder.U.getDouble(v + 16L);
        double _v11 = UnsafeOpsHolder.U.getDouble(v + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _v00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _v10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _v01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _v11);
        return dest;
    }

    public static long setMat2x3_unsafe(long dest, long m) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 16L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _m00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _m10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _m01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _m11);
        return dest;
    }

    public static long setMat3x3_unsafe(long dest, long m) {
        double _m00 = UnsafeOpsHolder.U.getDouble(m);
        double _m10 = UnsafeOpsHolder.U.getDouble(m + 8L);
        double _m01 = UnsafeOpsHolder.U.getDouble(m + 24L);
        double _m11 = UnsafeOpsHolder.U.getDouble(m + 32L);
        UnsafeOpsHolder.U.putDouble(dest, _m00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _m10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _m01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _m11);
        return dest;
    }

    public static long to2x3_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        return dest;
    }

    public static long to3x3_unsafe(long dest, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self01);
        UnsafeOpsHolder.U.putDouble(dest + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(dest + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 56L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 64L, 1.0);
        return dest;
    }

    public static long decomposeLDU_unsafe(long lower, long diagonal, long upper, long src) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _rcp0 = 1.0 / _self00;
        double _sp0 = UnsafeOpsHolder.U.getDouble(src + 8L) * _rcp0;
        UnsafeOpsHolder.U.putDouble(lower, 1.0);
        UnsafeOpsHolder.U.putDouble(lower + 8L, _sp0);
        UnsafeOpsHolder.U.putDouble(lower + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(lower + 24L, 1.0);
        UnsafeOpsHolder.U.putDouble(diagonal, _self00);
        UnsafeOpsHolder.U.putDouble(diagonal + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(diagonal + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(diagonal + 24L, _self11 - _self01 * _sp0);
        UnsafeOpsHolder.U.putDouble(upper, 1.0);
        UnsafeOpsHolder.U.putDouble(upper + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(upper + 16L, _self01 * _rcp0);
        UnsafeOpsHolder.U.putDouble(upper + 24L, 1.0);
        return lower;
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putDouble(dest, 1.0);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, 1.0);
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, double t) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(t, _other00 - _self00, _self00));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(t, _other10 - _self10, _self10));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(t, _other01 - _self01, _self01));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(t, _other11 - _self11, _self11));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long right) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _right00 = UnsafeOpsHolder.U.getDouble(right);
        double _right10 = UnsafeOpsHolder.U.getDouble(right + 8L);
        double _right01 = UnsafeOpsHolder.U.getDouble(right + 16L);
        double _right11 = UnsafeOpsHolder.U.getDouble(right + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_right01, _self10, _right11 * _self11));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_other00, _self00, _other01 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_other10, _self01, _other11 * _self11));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long other, double weight) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _other00 = UnsafeOpsHolder.U.getDouble(other);
        double _other10 = UnsafeOpsHolder.U.getDouble(other + 8L);
        double _other01 = UnsafeOpsHolder.U.getDouble(other + 16L);
        double _other11 = UnsafeOpsHolder.U.getDouble(other + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(weight, _other00, _self00));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(weight, _other10, _self10));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(weight, _other01, _self01));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(weight, _other11, _self11));
        return dest;
    }

    public static long makeOuterProduct_unsafe(long dest, double colX, double colY, double rowX, double rowY) {
        UnsafeOpsHolder.U.putDouble(dest, colX * rowX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, colY * rowX);
        UnsafeOpsHolder.U.putDouble(dest + 16L, colX * rowY);
        UnsafeOpsHolder.U.putDouble(dest + 24L, colY * rowY);
        return dest;
    }

    public static long makeOuterProduct_unsafe(long dest, long col, long row) {
        double _colx = UnsafeOpsHolder.U.getDouble(col);
        double _coly = UnsafeOpsHolder.U.getDouble(col + 8L);
        double _rowx = UnsafeOpsHolder.U.getDouble(row);
        double _rowy = UnsafeOpsHolder.U.getDouble(row + 8L);
        UnsafeOpsHolder.U.putDouble(dest, _colx * _rowx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _coly * _rowx);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _colx * _rowy);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _coly * _rowy);
        return dest;
    }

    public static long makeRotation_unsafe(long dest, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, _t1);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _t0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, -_t0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _t1);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, double vX, double vY) {
        UnsafeOpsHolder.U.putDouble(dest, vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, vY);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, long v) {
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest, _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _vy);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, double s) {
        UnsafeOpsHolder.U.putDouble(dest, s);
        UnsafeOpsHolder.U.putDouble(dest + 8L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 16L, 0.0);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s);
        return dest;
    }

    public static long preRotate_unsafe(long dest, long src, double angle) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self00, _t1, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self00, _t0, _self10 * _t1));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self01, _t1, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self01, _t0, _self11 * _t1));
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _self00 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11 * vY);
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest, _self00 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10 * _vy);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11 * _vy);
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, double s) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, s * _self00);
        UnsafeOpsHolder.U.putDouble(dest + 8L, s * _self10);
        UnsafeOpsHolder.U.putDouble(dest + 16L, s * _self01);
        UnsafeOpsHolder.U.putDouble(dest + 24L, s * _self11);
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, double angle) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self00, _t1, _self01 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putDouble(dest + 16L, Math.fma(_self01, _t1, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putDouble(dest + 24L, Math.fma(_self11, _t1, -(_self10 * _t0)));
        return dest;
    }

    public static long scale_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, _self00 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10 * vX);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01 * vY);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11 * vY);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest, _self00 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 8L, _self10 * _vx);
        UnsafeOpsHolder.U.putDouble(dest + 16L, _self01 * _vy);
        UnsafeOpsHolder.U.putDouble(dest + 24L, _self11 * _vy);
        return dest;
    }

    public static long mulVec2_unsafe(long dest, long src, double vX, double vY) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self00, vX, _self01 * vY));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static long mulVec2_unsafe(long dest, long src, long v) {
        double _self00 = UnsafeOpsHolder.U.getDouble(src);
        double _self10 = UnsafeOpsHolder.U.getDouble(src + 8L);
        double _self01 = UnsafeOpsHolder.U.getDouble(src + 16L);
        double _self11 = UnsafeOpsHolder.U.getDouble(src + 24L);
        double _vx = UnsafeOpsHolder.U.getDouble(v);
        double _vy = UnsafeOpsHolder.U.getDouble(v + 8L);
        UnsafeOpsHolder.U.putDouble(dest, Math.fma(_self00, _vx, _self01 * _vy));
        UnsafeOpsHolder.U.putDouble(dest + 8L, Math.fma(_self10, _vx, _self11 * _vy));
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
