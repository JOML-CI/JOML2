// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2x2Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float2x2Ops} and its sibling kernel units. Not public API.
 */
public final class Float2x2OpsKernelsByteBuffer {
    private Float2x2OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer getColumn_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        Float2x2OpsKernelsAddress.getColumn_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, col);
        return dest;
    }

    public static java.nio.ByteBuffer getColumn_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.putFloat(destOffset, _idxSw0);
        dest.putFloat(destOffset + 4, _idxSw1);
        return dest;
    }

    public static float getRotationAngle_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        return Float2x2OpsKernelsAddress.getRotationAngle_unsafe(UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
    }

    public static float getRotationAngle_api(java.nio.ByteBuffer src, int srcOffset) {
        return Math.atan2(src.getFloat(srcOffset + 4), src.getFloat(srcOffset));
    }

    public static java.nio.ByteBuffer getRow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        Float2x2OpsKernelsAddress.getRow_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, row);
        return dest;
    }

    public static java.nio.ByteBuffer getRow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _idxSw0;
        float _idxSw1;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.putFloat(destOffset, _idxSw0);
        dest.putFloat(destOffset + 4, _idxSw1);
        return dest;
    }

    public static java.nio.ByteBuffer cofactor_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cofactor_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _self11);
        dest.putFloat(destOffset + 4, -_self01);
        dest.putFloat(destOffset + 8, -_self10);
        dest.putFloat(destOffset + 12, _self00);
        return dest;
    }

    public static float determinant_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2x2OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static float determinant_api(java.nio.ByteBuffer src, int srcOffset) {
        return Math.fma(src.getFloat(srcOffset), src.getFloat(srcOffset + 12), -(src.getFloat(srcOffset + 8) * src.getFloat(srcOffset + 4)));
    }

    public static float frobeniusNorm_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2x2OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        return (float) java.lang.Math.sqrt(Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self00, _self00, _self01 * _self01))));
    }

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return Float2x2OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t3_inv = 1.0f / _t3;
        dest.putFloat(destOffset, _self11 * _t3_inv);
        dest.putFloat(destOffset + 4, -(_self10 * _t3_inv));
        dest.putFloat(destOffset + 8, -(_self01 * _t3_inv));
        dest.putFloat(destOffset + 12, _self00 * _t3_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x2OpsKernelsByteBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float2x2OpsKernelsByteBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invert_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t12_inv = 1.0f / Math.fma(_t7, _t6, -(_t8 * _t9));
        float _sp1 = _t0 * _t12_inv;
        float _sp0 = _t1 * _t12_inv;
        dest.putFloat(destOffset, _t6 * _sp0);
        dest.putFloat(destOffset + 4, -(_t9 * _sp0));
        dest.putFloat(destOffset + 8, -(_t8 * _sp1));
        dest.putFloat(destOffset + 12, _t7 * _sp1);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x2OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _t4 = Math.fma(_other01, _self10, _other11 * _self11);
        float _t5 = Math.fma(_other00, _self00, _other10 * _self01);
        float _t6 = Math.fma(_other00, _self10, _other10 * _self11);
        float _t7 = Math.fma(_other01, _self00, _other11 * _self01);
        float _t11 = Math.fma(_t5, _t4, -(_t6 * _t7));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return Float2x2OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t11_inv = 1.0f / _t11;
        dest.putFloat(destOffset, _t4 * _t11_inv);
        dest.putFloat(destOffset + 4, -(_t6 * _t11_inv));
        dest.putFloat(destOffset + 8, -(_t7 * _t11_inv));
        dest.putFloat(destOffset + 12, _t5 * _t11_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x2OpsKernelsByteBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x2OpsKernelsByteBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x2OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
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
        dest.putFloat(destOffset, _t14 * _sp0);
        dest.putFloat(destOffset + 4, -(_t16 * _sp0));
        dest.putFloat(destOffset + 8, -(_t17 * _sp1));
        dest.putFloat(destOffset + 12, _t15 * _sp1);
        return dest;
    }

    public static java.nio.ByteBuffer normal_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normal_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return Float2x2OpsKernelsByteBuffer.normal_degenerate(dest, destOffset, src, srcOffset);
        float _t3_inv = 1.0f / _t3;
        dest.putFloat(destOffset, _self11 * _t3_inv);
        dest.putFloat(destOffset + 4, -(_self01 * _t3_inv));
        dest.putFloat(destOffset + 8, -(_self10 * _t3_inv));
        dest.putFloat(destOffset + 12, _self00 * _t3_inv);
        return dest;
    }

    public static java.nio.ByteBuffer normal_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x2OpsKernelsByteBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float2x2OpsKernelsByteBuffer.normal_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer normal_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normal_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t12_inv = 1.0f / Math.fma(_t7, _t6, -(_t8 * _t9));
        float _sp1 = _t0 * _t12_inv;
        float _sp0 = _t1 * _t12_inv;
        dest.putFloat(destOffset, _t6 * _sp0);
        dest.putFloat(destOffset + 4, -(_t8 * _sp1));
        dest.putFloat(destOffset + 8, -(_t9 * _sp0));
        dest.putFloat(destOffset + 12, _t7 * _sp1);
        return dest;
    }

    public static float trace_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2x2OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static float trace_api(java.nio.ByteBuffer src, int srcOffset) {
        return src.getFloat(srcOffset) + src.getFloat(srcOffset + 12);
    }

    public static java.nio.ByteBuffer transpose_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer transpose_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self01);
        dest.putFloat(destOffset + 8, _self10);
        dest.putFloat(destOffset + 12, _self11);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, _other00 + _self00);
        dest.putFloat(destOffset + 4, _other10 + _self10);
        dest.putFloat(destOffset + 8, _other01 + _self01);
        dest.putFloat(destOffset + 12, _other11 + _self11);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, scalar * _self00);
        dest.putFloat(destOffset + 4, scalar * _self10);
        dest.putFloat(destOffset + 8, scalar * _self01);
        dest.putFloat(destOffset + 12, scalar * _self11);
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, -_self00);
        dest.putFloat(destOffset + 4, -_self10);
        dest.putFloat(destOffset + 8, -_self01);
        dest.putFloat(destOffset + 12, -_self11);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, _self00 - _other00);
        dest.putFloat(destOffset + 4, _self10 - _other10);
        dest.putFloat(destOffset + 8, _self01 - _other01);
        dest.putFloat(destOffset + 12, _self11 - _other11);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x2OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        float _v00 = v.getFloat(vOffset);
        float _v10 = v.getFloat(vOffset + 4);
        float _v01 = v.getFloat(vOffset + 8);
        float _v11 = v.getFloat(vOffset + 12);
        dest.putFloat(destOffset, _v00);
        dest.putFloat(destOffset + 4, _v10);
        dest.putFloat(destOffset + 8, _v01);
        dest.putFloat(destOffset + 12, _v11);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Float2x2OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        float _m00 = m.getFloat(mOffset);
        float _m10 = m.getFloat(mOffset + 4);
        float _m01 = m.getFloat(mOffset + 8);
        float _m11 = m.getFloat(mOffset + 12);
        dest.putFloat(destOffset, _m00);
        dest.putFloat(destOffset + 4, _m10);
        dest.putFloat(destOffset + 8, _m01);
        dest.putFloat(destOffset + 12, _m11);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Float2x2OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        float _m00 = m.getFloat(mOffset);
        float _m10 = m.getFloat(mOffset + 4);
        float _m01 = m.getFloat(mOffset + 12);
        float _m11 = m.getFloat(mOffset + 16);
        dest.putFloat(destOffset, _m00);
        dest.putFloat(destOffset + 4, _m10);
        dest.putFloat(destOffset + 8, _m01);
        dest.putFloat(destOffset + 12, _m11);
        return dest;
    }

    public static java.nio.ByteBuffer to2x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to2x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self01);
        dest.putFloat(destOffset + 12, _self11);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer to3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, 0.0f);
        dest.putFloat(destOffset + 28, 0.0f);
        dest.putFloat(destOffset + 32, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer decomposeLDU_unsafe(java.nio.ByteBuffer lower, int lowerOffset, java.nio.ByteBuffer diagonal, int diagonalOffset, java.nio.ByteBuffer upper, int upperOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _lowerBase = UnsafeOpsHolder.U.getLong(lower, UnsafeCopy.BB_ADDRESS_OFFSET) + lowerOffset;
        long _diagonalBase = UnsafeOpsHolder.U.getLong(diagonal, UnsafeCopy.BB_ADDRESS_OFFSET) + diagonalOffset;
        long _upperBase = UnsafeOpsHolder.U.getLong(upper, UnsafeCopy.BB_ADDRESS_OFFSET) + upperOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.decomposeLDU_unsafe(_lowerBase, _diagonalBase, _upperBase, _srcBase);
        return lower;
    }

    public static java.nio.ByteBuffer decomposeLDU_api(java.nio.ByteBuffer lower, int lowerOffset, java.nio.ByteBuffer diagonal, int diagonalOffset, java.nio.ByteBuffer upper, int upperOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _rcp0 = 1.0f / _self00;
        float _sp0 = src.getFloat(srcOffset + 4) * _rcp0;
        lower.putFloat(lowerOffset, 1.0f);
        lower.putFloat(lowerOffset + 4, _sp0);
        lower.putFloat(lowerOffset + 8, 0.0f);
        lower.putFloat(lowerOffset + 12, 1.0f);
        diagonal.putFloat(diagonalOffset, _self00);
        diagonal.putFloat(diagonalOffset + 4, 0.0f);
        diagonal.putFloat(diagonalOffset + 8, 0.0f);
        diagonal.putFloat(diagonalOffset + 12, _self11 - _self01 * _sp0);
        upper.putFloat(upperOffset, 1.0f);
        upper.putFloat(upperOffset + 4, 0.0f);
        upper.putFloat(upperOffset + 8, _self01 * _rcp0);
        upper.putFloat(upperOffset + 12, 1.0f);
        return lower;
    }

    public static java.nio.ByteBuffer makeIdentity_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2x2OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, Math.fma(t, _other00 - _self00, _self00));
        dest.putFloat(destOffset + 4, Math.fma(t, _other10 - _self10, _self10));
        dest.putFloat(destOffset + 8, Math.fma(t, _other01 - _self01, _self01));
        dest.putFloat(destOffset + 12, Math.fma(t, _other11 - _self11, _self11));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _right00 = right.getFloat(rightOffset);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        dest.putFloat(destOffset, Math.fma(_right00, _self00, _right10 * _self01));
        dest.putFloat(destOffset + 4, Math.fma(_right00, _self10, _right10 * _self11));
        dest.putFloat(destOffset + 8, Math.fma(_right01, _self00, _right11 * _self01));
        dest.putFloat(destOffset + 12, Math.fma(_right01, _self10, _right11 * _self11));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x2OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, Math.fma(_other00, _self00, _other01 * _self10));
        dest.putFloat(destOffset + 4, Math.fma(_other10, _self00, _other11 * _self10));
        dest.putFloat(destOffset + 8, Math.fma(_other00, _self01, _other01 * _self11));
        dest.putFloat(destOffset + 12, Math.fma(_other10, _self01, _other11 * _self11));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, Math.fma(weight, _other00, _self00));
        dest.putFloat(destOffset + 4, Math.fma(weight, _other10, _self10));
        dest.putFloat(destOffset + 8, Math.fma(weight, _other01, _self01));
        dest.putFloat(destOffset + 12, Math.fma(weight, _other11, _self11));
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, float colX, float colY, float rowX, float rowY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, rowX, rowY);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_api(java.nio.ByteBuffer dest, int destOffset, float colX, float colY, float rowX, float rowY) {
        dest.putFloat(destOffset, colX * rowX);
        dest.putFloat(destOffset + 4, colY * rowX);
        dest.putFloat(destOffset + 8, colX * rowY);
        dest.putFloat(destOffset + 12, colY * rowY);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer col, int colOffset, java.nio.ByteBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _colBase = UnsafeOpsHolder.U.getLong(col, UnsafeCopy.BB_ADDRESS_OFFSET) + colOffset;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset;
        Float2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeOuterProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer col, int colOffset, java.nio.ByteBuffer row, int rowOffset) {
        float _colx = col.getFloat(colOffset);
        float _coly = col.getFloat(colOffset + 4);
        float _rowx = row.getFloat(rowOffset);
        float _rowy = row.getFloat(rowOffset + 4);
        dest.putFloat(destOffset, _colx * _rowx);
        dest.putFloat(destOffset + 4, _coly * _rowx);
        dest.putFloat(destOffset + 8, _colx * _rowy);
        dest.putFloat(destOffset + 12, _coly * _rowy);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2x2OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset, _t1);
        dest.putFloat(destOffset + 4, _t0);
        dest.putFloat(destOffset + 8, -_t0);
        dest.putFloat(destOffset + 12, _t1);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        dest.putFloat(destOffset, vX);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset, _vx);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _vy);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, float s) {
        dest.putFloat(destOffset, s);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, s);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.putFloat(destOffset + 4, Math.fma(_self00, _t0, _self10 * _t1));
        dest.putFloat(destOffset + 8, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.putFloat(destOffset + 12, Math.fma(_self01, _t0, _self11 * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _self00 * vX);
        dest.putFloat(destOffset + 4, _self10 * vY);
        dest.putFloat(destOffset + 8, _self01 * vX);
        dest.putFloat(destOffset + 12, _self11 * vY);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset, _self00 * _vx);
        dest.putFloat(destOffset + 4, _self10 * _vy);
        dest.putFloat(destOffset + 8, _self01 * _vx);
        dest.putFloat(destOffset + 12, _self11 * _vy);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset, Math.fma(_self00, _t1, _self01 * _t0));
        dest.putFloat(destOffset + 4, Math.fma(_self10, _t1, _self11 * _t0));
        dest.putFloat(destOffset + 8, Math.fma(_self01, _t1, -(_self00 * _t0)));
        dest.putFloat(destOffset + 12, Math.fma(_self11, _t1, -(_self10 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _self00 * vX);
        dest.putFloat(destOffset + 4, _self10 * vX);
        dest.putFloat(destOffset + 8, _self01 * vY);
        dest.putFloat(destOffset + 12, _self11 * vY);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset, _self00 * _vx);
        dest.putFloat(destOffset + 4, _self10 * _vx);
        dest.putFloat(destOffset + 8, _self01 * _vy);
        dest.putFloat(destOffset + 12, _self11 * _vy);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.fma(_self00, vX, _self01 * vY));
        dest.putFloat(destOffset + 4, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset, Math.fma(_self00, _vx, _self01 * _vy));
        dest.putFloat(destOffset + 4, Math.fma(_self10, _vx, _self11 * _vy));
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
