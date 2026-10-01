// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2x2Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double2x2Ops} and its sibling kernel units. Not public API.
 */
public final class Double2x2OpsKernelsByteBuffer {
    private Double2x2OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer getColumn_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.nio.ByteBuffer getColumn_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.putDouble(destOffset + 0, _idxSw0);
        dest.putDouble(destOffset + 8, _idxSw1);
        return dest;
    }

    public static double getRotationAngle_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2x2OpsKernelsAddress.getRotationAngle_unsafe(_srcBase);
    }

    public static double getRotationAngle_api(java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        return Math.atan2(_self10, _self00);
    }

    public static java.nio.ByteBuffer getRow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.nio.ByteBuffer getRow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _idxSw0;
        double _idxSw1;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.putDouble(destOffset + 0, _idxSw0);
        dest.putDouble(destOffset + 8, _idxSw1);
        return dest;
    }

    public static java.nio.ByteBuffer cofactor_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cofactor_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, _self11);
        dest.putDouble(destOffset + 8, -_self01);
        dest.putDouble(destOffset + 16, -_self10);
        dest.putDouble(destOffset + 24, _self00);
        return dest;
    }

    public static double determinant_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2x2OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static double determinant_api(java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        return Math.fma(_self00, _self11, -(_self01 * _self10));
    }

    public static double frobeniusNorm_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2x2OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static double frobeniusNorm_api(java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        return Math.sqrt(Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self00, _self00, _self01 * _self01))));
    }

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return Double2x2OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t3_inv = 1.0 / _t3;
        dest.putDouble(destOffset + 0, _self11 * _t3_inv);
        dest.putDouble(destOffset + 8, -(_self10 * _t3_inv));
        dest.putDouble(destOffset + 16, -(_self01 * _t3_inv));
        dest.putDouble(destOffset + 24, _self00 * _t3_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invert_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
        dest.putDouble(destOffset + 0, _t6 * _sp0);
        dest.putDouble(destOffset + 8, -(_t9 * _sp0));
        dest.putDouble(destOffset + 16, -(_t8 * _sp1));
        dest.putDouble(destOffset + 24, _t7 * _sp1);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x2OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _other00 = other.getDouble(otherOffset + 0);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        double _t4 = Math.fma(_other01, _self10, _other11 * _self11);
        double _t5 = Math.fma(_other00, _self00, _other10 * _self01);
        double _t6 = Math.fma(_other00, _self10, _other10 * _self11);
        double _t7 = Math.fma(_other01, _self00, _other11 * _self01);
        double _t11 = Math.fma(_t5, _t4, -(_t6 * _t7));
        if (!(Math.abs(_t11) > 2.2250738585072014E-308 && Math.abs(_t11) < 4.49423283715579E307)) return Double2x2OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t11_inv = 1.0 / _t11;
        dest.putDouble(destOffset + 0, _t4 * _t11_inv);
        dest.putDouble(destOffset + 8, -(_t6 * _t11_inv));
        dest.putDouble(destOffset + 16, -(_t7 * _t11_inv));
        dest.putDouble(destOffset + 24, _t5 * _t11_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x2OpsKernelsByteBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x2OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _other00 = other.getDouble(otherOffset + 0);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
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
        dest.putDouble(destOffset + 0, _t14 * _sp0);
        dest.putDouble(destOffset + 8, -(_t16 * _sp0));
        dest.putDouble(destOffset + 16, -(_t17 * _sp1));
        dest.putDouble(destOffset + 24, _t15 * _sp1);
        return dest;
    }

    public static java.nio.ByteBuffer normal_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normal_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return Double2x2OpsKernelsByteBuffer.normal_degenerate(dest, destOffset, src, srcOffset);
        double _t3_inv = 1.0 / _t3;
        dest.putDouble(destOffset + 0, _self11 * _t3_inv);
        dest.putDouble(destOffset + 8, -(_self01 * _t3_inv));
        dest.putDouble(destOffset + 16, -(_self10 * _t3_inv));
        dest.putDouble(destOffset + 24, _self00 * _t3_inv);
        return dest;
    }

    public static java.nio.ByteBuffer normal_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.normal_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer normal_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normal_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
        dest.putDouble(destOffset + 0, _t6 * _sp0);
        dest.putDouble(destOffset + 8, -(_t8 * _sp1));
        dest.putDouble(destOffset + 16, -(_t9 * _sp0));
        dest.putDouble(destOffset + 24, _t7 * _sp1);
        return dest;
    }

    public static double trace_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2x2OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static double trace_api(java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self11 = src.getDouble(srcOffset + 24);
        return _self00 + _self11;
    }

    public static java.nio.ByteBuffer transpose_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer transpose_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, _self00);
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, _self10);
        dest.putDouble(destOffset + 24, _self11);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _other00 = other.getDouble(otherOffset + 0);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset + 0, _other00 + _self00);
        dest.putDouble(destOffset + 8, _other10 + _self10);
        dest.putDouble(destOffset + 16, _other01 + _self01);
        dest.putDouble(destOffset + 24, _other11 + _self11);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, scalar * _self00);
        dest.putDouble(destOffset + 8, scalar * _self10);
        dest.putDouble(destOffset + 16, scalar * _self01);
        dest.putDouble(destOffset + 24, scalar * _self11);
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, -_self00);
        dest.putDouble(destOffset + 8, -_self10);
        dest.putDouble(destOffset + 16, -_self01);
        dest.putDouble(destOffset + 24, -_self11);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _other00 = other.getDouble(otherOffset + 0);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset + 0, _self00 - _other00);
        dest.putDouble(destOffset + 8, _self10 - _other10);
        dest.putDouble(destOffset + 16, _self01 - _other01);
        dest.putDouble(destOffset + 24, _self11 - _other11);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x2OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _v00 = v.getDouble(vOffset + 0);
        double _v10 = v.getDouble(vOffset + 8);
        double _v01 = v.getDouble(vOffset + 16);
        double _v11 = v.getDouble(vOffset + 24);
        dest.putDouble(destOffset + 0, _v00);
        dest.putDouble(destOffset + 8, _v10);
        dest.putDouble(destOffset + 16, _v01);
        dest.putDouble(destOffset + 24, _v11);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double2x2OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset + 0);
        double _m10 = m.getDouble(mOffset + 8);
        double _m01 = m.getDouble(mOffset + 16);
        double _m11 = m.getDouble(mOffset + 24);
        dest.putDouble(destOffset + 0, _m00);
        dest.putDouble(destOffset + 8, _m10);
        dest.putDouble(destOffset + 16, _m01);
        dest.putDouble(destOffset + 24, _m11);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double2x2OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset + 0);
        double _m10 = m.getDouble(mOffset + 8);
        double _m01 = m.getDouble(mOffset + 24);
        double _m11 = m.getDouble(mOffset + 32);
        dest.putDouble(destOffset + 0, _m00);
        dest.putDouble(destOffset + 8, _m10);
        dest.putDouble(destOffset + 16, _m01);
        dest.putDouble(destOffset + 24, _m11);
        return dest;
    }

    public static java.nio.ByteBuffer to2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self01);
        dest.putDouble(destOffset + 24, _self11);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer to3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, 0.0);
        dest.putDouble(destOffset + 56, 0.0);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeLDU_unsafe(java.nio.ByteBuffer lower, int lowerOffset, java.nio.ByteBuffer diagonal, int diagonalOffset, java.nio.ByteBuffer upper, int upperOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _lowerBase = UnsafeOpsHolder.U.getLong(lower, UnsafeCopy.BB_ADDRESS_OFFSET) + lowerOffset;
        long _diagonalBase = UnsafeOpsHolder.U.getLong(diagonal, UnsafeCopy.BB_ADDRESS_OFFSET) + diagonalOffset;
        long _upperBase = UnsafeOpsHolder.U.getLong(upper, UnsafeCopy.BB_ADDRESS_OFFSET) + upperOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.decomposeLDU_unsafe(_lowerBase, _diagonalBase, _upperBase, _srcBase);
        return lower;
    }

    public static java.nio.ByteBuffer decomposeLDU_api(java.nio.ByteBuffer lower, int lowerOffset, java.nio.ByteBuffer diagonal, int diagonalOffset, java.nio.ByteBuffer upper, int upperOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _rcp0 = 1.0 / _self00;
        double _sp0 = _self10 * _rcp0;
        lower.putDouble(lowerOffset + 0, 1.0);
        lower.putDouble(lowerOffset + 8, _sp0);
        lower.putDouble(lowerOffset + 16, 0.0);
        lower.putDouble(lowerOffset + 24, 1.0);
        diagonal.putDouble(diagonalOffset + 0, _self00);
        diagonal.putDouble(diagonalOffset + 8, 0.0);
        diagonal.putDouble(diagonalOffset + 16, 0.0);
        diagonal.putDouble(diagonalOffset + 24, _self11 - _self01 * _sp0);
        upper.putDouble(upperOffset + 0, 1.0);
        upper.putDouble(upperOffset + 8, 0.0);
        upper.putDouble(upperOffset + 16, _self01 * _rcp0);
        upper.putDouble(upperOffset + 24, 1.0);
        return lower;
    }

    public static java.nio.ByteBuffer makeIdentity_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2x2OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset + 0, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _other00 = other.getDouble(otherOffset + 0);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(t, _other00 - _self00, _self00));
        dest.putDouble(destOffset + 8, Math.fma(t, _other10 - _self10, _self10));
        dest.putDouble(destOffset + 16, Math.fma(t, _other01 - _self01, _self01));
        dest.putDouble(destOffset + 24, Math.fma(t, _other11 - _self11, _self11));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _right00 = right.getDouble(rightOffset + 0);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(_right00, _self00, _right10 * _self01));
        dest.putDouble(destOffset + 8, Math.fma(_right00, _self10, _right10 * _self11));
        dest.putDouble(destOffset + 16, Math.fma(_right01, _self00, _right11 * _self01));
        dest.putDouble(destOffset + 24, Math.fma(_right01, _self10, _right11 * _self11));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x2OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _other00 = other.getDouble(otherOffset + 0);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(_other00, _self00, _other01 * _self10));
        dest.putDouble(destOffset + 8, Math.fma(_other10, _self00, _other11 * _self10));
        dest.putDouble(destOffset + 16, Math.fma(_other00, _self01, _other01 * _self11));
        dest.putDouble(destOffset + 24, Math.fma(_other10, _self01, _other11 * _self11));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _other00 = other.getDouble(otherOffset + 0);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(weight, _other00, _self00));
        dest.putDouble(destOffset + 8, Math.fma(weight, _other10, _self10));
        dest.putDouble(destOffset + 16, Math.fma(weight, _other01, _self01));
        dest.putDouble(destOffset + 24, Math.fma(weight, _other11, _self11));
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, double colX, double colY, double rowX, double rowY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, rowX, rowY);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_api(java.nio.ByteBuffer dest, int destOffset, double colX, double colY, double rowX, double rowY) {
        dest.putDouble(destOffset + 0, colX * rowX);
        dest.putDouble(destOffset + 8, colY * rowX);
        dest.putDouble(destOffset + 16, colX * rowY);
        dest.putDouble(destOffset + 24, colY * rowY);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer col, int colOffset, java.nio.ByteBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _colBase = UnsafeOpsHolder.U.getLong(col, UnsafeCopy.BB_ADDRESS_OFFSET) + colOffset;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset;
        Double2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer col, int colOffset, java.nio.ByteBuffer row, int rowOffset) {
        double _colx = col.getDouble(colOffset + 0);
        double _coly = col.getDouble(colOffset + 8);
        double _rowx = row.getDouble(rowOffset + 0);
        double _rowy = row.getDouble(rowOffset + 8);
        dest.putDouble(destOffset + 0, _colx * _rowx);
        dest.putDouble(destOffset + 8, _coly * _rowx);
        dest.putDouble(destOffset + 16, _colx * _rowy);
        dest.putDouble(destOffset + 24, _coly * _rowy);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2x2OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset + 0, _t1);
        dest.putDouble(destOffset + 8, _t0);
        dest.putDouble(destOffset + 16, -_t0);
        dest.putDouble(destOffset + 24, _t1);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        dest.putDouble(destOffset + 0, vX);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset + 0);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset + 0, _vx);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _vy);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, double s) {
        dest.putDouble(destOffset + 0, s);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, s);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset + 0, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.putDouble(destOffset + 8, Math.fma(_self00, _t0, _self10 * _t1));
        dest.putDouble(destOffset + 16, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.putDouble(destOffset + 24, Math.fma(_self01, _t0, _self11 * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, _self00 * vX);
        dest.putDouble(destOffset + 8, _self10 * vY);
        dest.putDouble(destOffset + 16, _self01 * vX);
        dest.putDouble(destOffset + 24, _self11 * vY);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _vx = v.getDouble(vOffset + 0);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset + 0, _self00 * _vx);
        dest.putDouble(destOffset + 8, _self10 * _vy);
        dest.putDouble(destOffset + 16, _self01 * _vx);
        dest.putDouble(destOffset + 24, _self11 * _vy);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self01);
        dest.putDouble(destOffset + 24, s * _self11);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.putDouble(destOffset + 0, Math.fma(_self00, _t1, _self01 * _t0));
        dest.putDouble(destOffset + 8, Math.fma(_self10, _t1, _self11 * _t0));
        dest.putDouble(destOffset + 16, Math.fma(_self01, _t1, -(_self00 * _t0)));
        dest.putDouble(destOffset + 24, Math.fma(_self11, _t1, -(_self10 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, _self00 * vX);
        dest.putDouble(destOffset + 8, _self10 * vX);
        dest.putDouble(destOffset + 16, _self01 * vY);
        dest.putDouble(destOffset + 24, _self11 * vY);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _vx = v.getDouble(vOffset + 0);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset + 0, _self00 * _vx);
        dest.putDouble(destOffset + 8, _self10 * _vx);
        dest.putDouble(destOffset + 16, _self01 * _vy);
        dest.putDouble(destOffset + 24, _self11 * _vy);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset + 0, Math.fma(_self00, vX, _self01 * vY));
        dest.putDouble(destOffset + 8, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _self00 = src.getDouble(srcOffset + 0);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _vx = v.getDouble(vOffset + 0);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset + 0, Math.fma(_self00, _vx, _self01 * _vy));
        dest.putDouble(destOffset + 8, Math.fma(_self10, _vx, _self11 * _vy));
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
