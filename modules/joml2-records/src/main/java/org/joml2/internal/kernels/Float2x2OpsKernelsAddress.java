// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2x2Ops} whose leading storage
 * parameter is a raw {@code long} native address (the shared Unsafe kernels). Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float2x2Ops} and its sibling kernel units. Not public API.
 */
public final class Float2x2OpsKernelsAddress {
    private Float2x2OpsKernelsAddress() {}

    public static long getColumn_unsafe(long dest, long src, int col) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        UnsafeOpsHolder.U.putFloat(dest + 0L, _idxSw0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _idxSw1);
        return dest;
    }

    public static float getRotationAngle_unsafe(long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        return (float) Math.atan2(_self10, _self00);
    }

    public static long getRow_unsafe(long dest, long src, int row) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _idxSw0;
        float _idxSw1;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        UnsafeOpsHolder.U.putFloat(dest + 0L, _idxSw0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _idxSw1);
        return dest;
    }

    public static long cofactor_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self10);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self00);
        return dest;
    }

    public static float determinant_unsafe(long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        return Math.fma(_self00, _self11, -(_self01 * _self10));
    }

    public static float frobeniusNorm_unsafe(long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        return (float) Math.sqrt(Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self00, _self00, _self01 * _self01))));
    }

    public static long invert_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return Float2x2OpsKernelsAddress.invert_degenerate(dest, src);
        float _t3_inv = 1.0f / _t3;
        UnsafeOpsHolder.U.putFloat(dest + 0L, _self11 * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_self10 * _t3_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_self01 * _t3_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self00 * _t3_inv);
        return dest;
    }

    public static long invert_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x2OpsKernelsAddress.invert_degenerate_unsafe(dest, src);
        Float2x2OpsKernelsSegment.invert_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    public static long invert_degenerate_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t12_inv = 1.0f / Math.fma(_t7, _t6, -(_t8 * _t9));
        float _sp1 = _t0 * _t12_inv;
        float _sp0 = _t1 * _t12_inv;
        UnsafeOpsHolder.U.putFloat(dest + 0L, _t6 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t9 * _sp0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t8 * _sp1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t7 * _sp1);
        return dest;
    }

    public static long invertProduct_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t4 = Math.fma(_other01, _self10, _other11 * _self11);
        float _t5 = Math.fma(_other00, _self00, _other10 * _self01);
        float _t6 = Math.fma(_other00, _self10, _other10 * _self11);
        float _t7 = Math.fma(_other01, _self00, _other11 * _self01);
        float _t11 = Math.fma(_t5, _t4, -(_t6 * _t7));
        if (!(Math.abs(_t11) > 1.1754944E-38f && Math.abs(_t11) < 8.507059E37f)) return Float2x2OpsKernelsAddress.invertProduct_degenerate(dest, src, other);
        float _t11_inv = 1.0f / _t11;
        UnsafeOpsHolder.U.putFloat(dest + 0L, _t4 * _t11_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t6 * _t11_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t7 * _t11_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t5 * _t11_inv);
        return dest;
    }

    public static long invertProduct_degenerate(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x2OpsKernelsAddress.invertProduct_degenerate_unsafe(dest, src, other);
        Float2x2OpsKernelsSegment.invertProduct_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    public static long invertProduct_degenerate_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        float _t4 = Math.fma(_other01, _self10, _other11 * _self11);
        float _t5 = Math.fma(_other00, _self10, _other10 * _self11);
        float _t6 = Math.fma(_other00, _self00, _other10 * _self01);
        float _t7 = Math.fma(_other01, _self00, _other11 * _self01);
        float _t8 = unitScale(_t5, _t4, _t5);
        float _t9 = unitScale(_t6, _t7, _t6);
        float _t14 = _t4 * _t8;
        float _t15 = _t6 * _t9;
        float _t16 = _t5 * _t8;
        float _t17 = _t7 * _t9;
        float _t20_inv = 1.0f / Math.fma(_t15, _t14, -(_t16 * _t17));
        float _sp1 = _t8 * _t20_inv;
        float _sp0 = _t9 * _t20_inv;
        UnsafeOpsHolder.U.putFloat(dest + 0L, _t14 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t16 * _sp0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t17 * _sp1));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t15 * _sp1);
        return dest;
    }

    public static long normal_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return Float2x2OpsKernelsAddress.normal_degenerate(dest, src);
        float _t3_inv = 1.0f / _t3;
        UnsafeOpsHolder.U.putFloat(dest + 0L, _self11 * _t3_inv);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_self01 * _t3_inv));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_self10 * _t3_inv));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self00 * _t3_inv);
        return dest;
    }

    public static long normal_degenerate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x2OpsKernelsAddress.normal_degenerate_unsafe(dest, src);
        Float2x2OpsKernelsSegment.normal_degenerate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    public static long normal_degenerate_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t12_inv = 1.0f / Math.fma(_t7, _t6, -(_t8 * _t9));
        float _sp1 = _t0 * _t12_inv;
        float _sp0 = _t1 * _t12_inv;
        UnsafeOpsHolder.U.putFloat(dest + 0L, _t6 * _sp0);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -(_t8 * _sp1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, -(_t9 * _sp0));
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t7 * _sp1);
        return dest;
    }

    public static float trace_unsafe(long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        return _self00 + _self11;
    }

    public static long transpose_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11);
        return dest;
    }

    public static long add_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _other00 + _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _other10 + _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _other01 + _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _other11 + _self11);
        return dest;
    }

    public static long mul_unsafe(long dest, long src, float scalar) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, scalar * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, scalar * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, scalar * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, scalar * _self11);
        return dest;
    }

    public static long negate_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, -_self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, -_self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, -_self11);
        return dest;
    }

    public static long sub_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _self00 - _other00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 - _other10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01 - _other01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11 - _other11);
        return dest;
    }

    public static long set_unsafe(long dest, long v) {
        float _v00 = UnsafeOpsHolder.U.getFloat(v + 0L);
        float _v10 = UnsafeOpsHolder.U.getFloat(v + 4L);
        float _v01 = UnsafeOpsHolder.U.getFloat(v + 8L);
        float _v11 = UnsafeOpsHolder.U.getFloat(v + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _v00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _v10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _v01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _v11);
        return dest;
    }

    public static long setMat2x3_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m + 0L);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 8L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _m00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _m10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _m01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _m11);
        return dest;
    }

    public static long setMat3x3_unsafe(long dest, long m) {
        float _m00 = UnsafeOpsHolder.U.getFloat(m + 0L);
        float _m10 = UnsafeOpsHolder.U.getFloat(m + 4L);
        float _m01 = UnsafeOpsHolder.U.getFloat(m + 12L);
        float _m11 = UnsafeOpsHolder.U.getFloat(m + 16L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _m00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _m10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _m01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _m11);
        return dest;
    }

    public static long to2x3_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        return dest;
    }

    public static long to3x3_unsafe(long dest, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self01);
        UnsafeOpsHolder.U.putFloat(dest + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(dest + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 28L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 32L, 1.0f);
        return dest;
    }

    public static long decomposeLDU_unsafe(long lower, long diagonal, long upper, long src) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _rcp0 = 1.0f / _self00;
        float _sp0 = _self10 * _rcp0;
        UnsafeOpsHolder.U.putFloat(lower + 0L, 1.0f);
        UnsafeOpsHolder.U.putFloat(lower + 4L, _sp0);
        UnsafeOpsHolder.U.putFloat(lower + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(lower + 12L, 1.0f);
        UnsafeOpsHolder.U.putFloat(diagonal + 0L, _self00);
        UnsafeOpsHolder.U.putFloat(diagonal + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(diagonal + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(diagonal + 12L, _self11 - _self01 * _sp0);
        UnsafeOpsHolder.U.putFloat(upper + 0L, 1.0f);
        UnsafeOpsHolder.U.putFloat(upper + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(upper + 8L, _self01 * _rcp0);
        UnsafeOpsHolder.U.putFloat(upper + 12L, 1.0f);
        return lower;
    }

    public static long makeIdentity_unsafe(long dest) {
        UnsafeOpsHolder.U.putFloat(dest + 0L, 1.0f);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, 1.0f);
        return dest;
    }

    public static long lerp_unsafe(long dest, long src, long other, float t) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(t, _other00 - _self00, _self00));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(t, _other10 - _self10, _self10));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(t, _other01 - _self01, _self01));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(t, _other11 - _self11, _self11));
        return dest;
    }

    public static long mul_unsafe(long dest, long src, long right) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _right00 = UnsafeOpsHolder.U.getFloat(right + 0L);
        float _right10 = UnsafeOpsHolder.U.getFloat(right + 4L);
        float _right01 = UnsafeOpsHolder.U.getFloat(right + 8L);
        float _right11 = UnsafeOpsHolder.U.getFloat(right + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_right00, _self00, _right10 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_right00, _self10, _right10 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_right01, _self00, _right11 * _self01));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_right01, _self10, _right11 * _self11));
        return dest;
    }

    public static long preMul_unsafe(long dest, long src, long other) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_other00, _self00, _other01 * _self10));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_other10, _self00, _other11 * _self10));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_other00, _self01, _other01 * _self11));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_other10, _self01, _other11 * _self11));
        return dest;
    }

    public static long addScaled_unsafe(long dest, long src, long other, float weight) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _other00 = UnsafeOpsHolder.U.getFloat(other + 0L);
        float _other10 = UnsafeOpsHolder.U.getFloat(other + 4L);
        float _other01 = UnsafeOpsHolder.U.getFloat(other + 8L);
        float _other11 = UnsafeOpsHolder.U.getFloat(other + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(weight, _other00, _self00));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(weight, _other10, _self10));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(weight, _other01, _self01));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(weight, _other11, _self11));
        return dest;
    }

    public static long makeOuterProduct_unsafe(long dest, float colX, float colY, float rowX, float rowY) {
        UnsafeOpsHolder.U.putFloat(dest + 0L, colX * rowX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, colY * rowX);
        UnsafeOpsHolder.U.putFloat(dest + 8L, colX * rowY);
        UnsafeOpsHolder.U.putFloat(dest + 12L, colY * rowY);
        return dest;
    }

    public static long makeOuterProduct_unsafe(long dest, long col, long row) {
        float _colx = UnsafeOpsHolder.U.getFloat(col + 0L);
        float _coly = UnsafeOpsHolder.U.getFloat(col + 4L);
        float _rowx = UnsafeOpsHolder.U.getFloat(row + 0L);
        float _rowy = UnsafeOpsHolder.U.getFloat(row + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _colx * _rowx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _coly * _rowx);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _colx * _rowy);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _coly * _rowy);
        return dest;
    }

    public static long makeRotation_unsafe(long dest, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _t1);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _t0);
        UnsafeOpsHolder.U.putFloat(dest + 8L, -_t0);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _t1);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, float vX, float vY) {
        UnsafeOpsHolder.U.putFloat(dest + 0L, vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, vY);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, long v) {
        float _vx = UnsafeOpsHolder.U.getFloat(v + 0L);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _vy);
        return dest;
    }

    public static long makeScaling_unsafe(long dest, float s) {
        UnsafeOpsHolder.U.putFloat(dest + 0L, s);
        UnsafeOpsHolder.U.putFloat(dest + 4L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 8L, 0.0f);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s);
        return dest;
    }

    public static long preRotate_unsafe(long dest, long src, float angle) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_self00, _t1, -(_self10 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self00, _t0, _self10 * _t1));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self01, _t1, -(_self11 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self01, _t0, _self11 * _t1));
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _self00 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11 * vY);
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _vx = UnsafeOpsHolder.U.getFloat(v + 0L);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _self00 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * _vy);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11 * _vy);
        return dest;
    }

    public static long preScale_unsafe(long dest, long src, float s) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, s * _self00);
        UnsafeOpsHolder.U.putFloat(dest + 4L, s * _self10);
        UnsafeOpsHolder.U.putFloat(dest + 8L, s * _self01);
        UnsafeOpsHolder.U.putFloat(dest + 12L, s * _self11);
        return dest;
    }

    public static long rotate_unsafe(long dest, long src, float angle) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_self00, _t1, _self01 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _t1, _self11 * _t0));
        UnsafeOpsHolder.U.putFloat(dest + 8L, Math.fma(_self01, _t1, -(_self00 * _t0)));
        UnsafeOpsHolder.U.putFloat(dest + 12L, Math.fma(_self11, _t1, -(_self10 * _t0)));
        return dest;
    }

    public static long scale_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _self00 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * vX);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01 * vY);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11 * vY);
        return dest;
    }

    public static long scale_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _vx = UnsafeOpsHolder.U.getFloat(v + 0L);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, _self00 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 4L, _self10 * _vx);
        UnsafeOpsHolder.U.putFloat(dest + 8L, _self01 * _vy);
        UnsafeOpsHolder.U.putFloat(dest + 12L, _self11 * _vy);
        return dest;
    }

    public static long mulVec2_unsafe(long dest, long src, float vX, float vY) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_self00, vX, _self01 * vY));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static long mulVec2_unsafe(long dest, long src, long v) {
        float _self00 = UnsafeOpsHolder.U.getFloat(src + 0L);
        float _self10 = UnsafeOpsHolder.U.getFloat(src + 4L);
        float _self01 = UnsafeOpsHolder.U.getFloat(src + 8L);
        float _self11 = UnsafeOpsHolder.U.getFloat(src + 12L);
        float _vx = UnsafeOpsHolder.U.getFloat(v + 0L);
        float _vy = UnsafeOpsHolder.U.getFloat(v + 4L);
        UnsafeOpsHolder.U.putFloat(dest + 0L, Math.fma(_self00, _vx, _self01 * _vy));
        UnsafeOpsHolder.U.putFloat(dest + 4L, Math.fma(_self10, _vx, _self11 * _vy));
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
