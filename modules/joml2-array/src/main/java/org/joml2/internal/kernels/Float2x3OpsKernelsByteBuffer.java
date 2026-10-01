// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2x3Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float2x3Ops} and its sibling kernel units. Not public API.
 */
public final class Float2x3OpsKernelsByteBuffer {
    private Float2x3OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer getColumn_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.nio.ByteBuffer getColumn_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.putFloat(destOffset + 0, _idxSw0);
        dest.putFloat(destOffset + 4, _idxSw1);
        return dest;
    }

    public static float getRotationAngle_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2x3OpsKernelsAddress.getRotationAngle_unsafe(_srcBase);
    }

    public static float getRotationAngle_api(java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        return (float) Math.atan2(_self10, _self00);
    }

    public static java.nio.ByteBuffer getRow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.nio.ByteBuffer getRow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.putFloat(destOffset + 0, _idxSw0);
        dest.putFloat(destOffset + 4, _idxSw1);
        dest.putFloat(destOffset + 8, _idxSw2);
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.getTranslation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset + 0, _self02);
        dest.putFloat(destOffset + 4, _self12);
        return dest;
    }

    public static float determinant_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2x3OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static float determinant_api(java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        return Math.fma(_self00, _self11, -(_self01 * _self10));
    }

    public static float frobeniusNorm_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        return (float) Math.sqrt(Math.fma(_self12, _self12, Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self02, _self02, Math.fma(_self00, _self00, _self01 * _self01))))));
    }

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(Math.abs(_t3) > 1.1754944E-38f && Math.abs(_t3) < 8.507059E37f)) return Float2x3OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t3_inv = 1.0f / _t3;
        dest.putFloat(destOffset + 0, _self11 * _t3_inv);
        dest.putFloat(destOffset + 4, -(_self10 * _t3_inv));
        dest.putFloat(destOffset + 8, -(_self01 * _t3_inv));
        dest.putFloat(destOffset + 12, _self00 * _t3_inv);
        dest.putFloat(destOffset + 16, -(Math.fma(_self02, _self11, -(_self01 * _self12)) * _t3_inv));
        dest.putFloat(destOffset + 20, -(Math.fma(_self00, _self12, -(_self02 * _self10)) * _t3_inv));
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsByteBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invert_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
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
        dest.putFloat(destOffset + 0, _t8 * _sp0);
        dest.putFloat(destOffset + 4, -(_t11 * _sp0));
        dest.putFloat(destOffset + 8, -(_t10 * _sp1));
        dest.putFloat(destOffset + 12, _t9 * _sp1);
        dest.putFloat(destOffset + 16, -(Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv));
        dest.putFloat(destOffset + 20, -(Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv));
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x3OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _other00 = other.getFloat(otherOffset + 0);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _other02 = other.getFloat(otherOffset + 16);
        float _other12 = other.getFloat(otherOffset + 20);
        float _t6 = Math.fma(_other01, _self10, _other11 * _self11);
        float _t7 = Math.fma(_other00, _self00, _other10 * _self01);
        float _t8 = Math.fma(_other00, _self10, _other10 * _self11);
        float _t9 = Math.fma(_other01, _self00, _other11 * _self01);
        float _t10 = Math.fma(_other02, _self00, Math.fma(_other12, _self01, _self02));
        float _t11 = Math.fma(_other02, _self10, Math.fma(_other12, _self11, _self12));
        float _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(Math.abs(_t15) > 1.1754944E-38f && Math.abs(_t15) < 8.507059E37f)) return Float2x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t15_inv = 1.0f / _t15;
        dest.putFloat(destOffset + 0, _t6 * _t15_inv);
        dest.putFloat(destOffset + 4, -(_t8 * _t15_inv));
        dest.putFloat(destOffset + 8, -(_t9 * _t15_inv));
        dest.putFloat(destOffset + 12, _t7 * _t15_inv);
        dest.putFloat(destOffset + 16, -(Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv));
        dest.putFloat(destOffset + 20, -(Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv));
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsByteBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _other00 = other.getFloat(otherOffset + 0);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _other02 = other.getFloat(otherOffset + 16);
        float _other12 = other.getFloat(otherOffset + 20);
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
        float _t24 = Math.fma(_other02, _self00, Math.fma(_other12, _self01, _self02)) * _t13;
        float _t25 = Math.fma(_other02, _self10, Math.fma(_other12, _self11, _self12)) * _t12;
        float _t28_inv = 1.0f / Math.fma(_t19, _t18, -(_t20 * _t21));
        float _sp1 = _t12 * _t28_inv;
        float _sp0 = _t13 * _t28_inv;
        dest.putFloat(destOffset + 0, _t18 * _sp0);
        dest.putFloat(destOffset + 4, -(_t20 * _sp0));
        dest.putFloat(destOffset + 8, -(_t21 * _sp1));
        dest.putFloat(destOffset + 12, _t19 * _sp1);
        dest.putFloat(destOffset + 16, -(Math.fma(_t24, _t18, -(_t25 * _t21)) * _t28_inv));
        dest.putFloat(destOffset + 20, -(Math.fma(_t25, _t19, -(_t24 * _t20)) * _t28_inv));
        return dest;
    }

    public static java.nio.ByteBuffer transpose_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer transpose_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self01);
        dest.putFloat(destOffset + 8, _self02);
        dest.putFloat(destOffset + 12, _self10);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eother0 = other.getFloat(otherOffset + _lo * 4);
            float _eother1 = other.getFloat(otherOffset + (_lo + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, _eother0 + _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eother1 + _eself1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, scalar * _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, scalar * _eself1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, -_eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, -_eself1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eother0 = other.getFloat(otherOffset + _lo * 4);
            float _eother1 = other.getFloat(otherOffset + (_lo + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0 - _eother0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1 - _eother1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _ev0 = v.getFloat(vOffset + _lo * 4);
            float _ev1 = v.getFloat(vOffset + (_lo + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, _ev0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _ev1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Float2x3OpsKernelsAddress.setMat2x2_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        float _m00 = m.getFloat(mOffset + 0);
        float _m10 = m.getFloat(mOffset + 4);
        float _m01 = m.getFloat(mOffset + 8);
        float _m11 = m.getFloat(mOffset + 12);
        dest.putFloat(destOffset + 0, _m00);
        dest.putFloat(destOffset + 4, _m10);
        dest.putFloat(destOffset + 8, _m01);
        dest.putFloat(destOffset + 12, _m11);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Float2x3OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _lom = _l * 3;
            float _em0 = m.getFloat(mOffset + _lom * 4);
            float _em1 = m.getFloat(mOffset + (_lom + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, _em0);
            dest.putFloat(destOffset + (_lo + 1) * 4, _em1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float tX, float tY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float tX, float tY) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self01);
        dest.putFloat(destOffset + 12, _self11);
        dest.putFloat(destOffset + 16, tX);
        dest.putFloat(destOffset + 20, tY);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Float2x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _tx = t.getFloat(tOffset + 0);
        float _ty = t.getFloat(tOffset + 4);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self01);
        dest.putFloat(destOffset + 12, _self11);
        dest.putFloat(destOffset + 16, _tx);
        dest.putFloat(destOffset + 20, _ty);
        return dest;
    }

    public static java.nio.ByteBuffer to2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.to2x2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self01);
        dest.putFloat(destOffset + 12, _self11);
        return dest;
    }

    public static java.nio.ByteBuffer to3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _self01);
        dest.putFloat(destOffset + 16, _self11);
        dest.putFloat(destOffset + 20, 0.0f);
        dest.putFloat(destOffset + 24, _self02);
        dest.putFloat(destOffset + 28, _self12);
        dest.putFloat(destOffset + 32, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2x3OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 1.0f);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eother0 = other.getFloat(otherOffset + _lo * 4);
            float _eother1 = other.getFloat(otherOffset + (_lo + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, Math.fma(t, _eother0 - _eself0, _eself0));
            dest.putFloat(destOffset + (_lo + 1) * 4, Math.fma(t, _eother1 - _eself1, _eself1));
        }
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float2x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _right00 = right.getFloat(rightOffset + 0);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        float _right02 = right.getFloat(rightOffset + 16);
        float _right12 = right.getFloat(rightOffset + 20);
        dest.putFloat(destOffset + 0, Math.fma(_right00, _self00, _right10 * _self01));
        dest.putFloat(destOffset + 4, Math.fma(_right00, _self10, _right10 * _self11));
        dest.putFloat(destOffset + 8, Math.fma(_right01, _self00, _right11 * _self01));
        dest.putFloat(destOffset + 12, Math.fma(_right01, _self10, _right11 * _self11));
        dest.putFloat(destOffset + 16, Math.fma(_right02, _self00, Math.fma(_right12, _self01, _self02)));
        dest.putFloat(destOffset + 20, Math.fma(_right02, _self10, Math.fma(_right12, _self11, _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float2x3OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _right00 = right.getFloat(rightOffset + 0);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        dest.putFloat(destOffset + 0, Math.fma(_right00, _self00, _right10 * _self01));
        dest.putFloat(destOffset + 4, Math.fma(_right00, _self10, _right10 * _self11));
        dest.putFloat(destOffset + 8, Math.fma(_right01, _self00, _right11 * _self01));
        dest.putFloat(destOffset + 12, Math.fma(_right01, _self10, _right11 * _self11));
        dest.putFloat(destOffset + 16, _self02);
        dest.putFloat(destOffset + 20, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float2x3OpsKernelsAddress.mulMat3x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = right.getFloat(rightOffset + _lo * 4);
            float _eright1 = right.getFloat(rightOffset + (_lo + 1) * 4);
            float _eright2 = right.getFloat(rightOffset + (_lo + 2) * 4);
            dest.putFloat(destOffset + _lo * 4, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.putFloat(destOffset + (_lo + 1) * 4, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.putFloat(destOffset + (_lo + 2) * 4, _eright2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _other00 = other.getFloat(otherOffset + 0);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _other02 = other.getFloat(otherOffset + 16);
        float _other12 = other.getFloat(otherOffset + 20);
        dest.putFloat(destOffset + 0, Math.fma(_other00, _self00, _other01 * _self10));
        dest.putFloat(destOffset + 4, Math.fma(_other10, _self00, _other11 * _self10));
        dest.putFloat(destOffset + 8, Math.fma(_other00, _self01, _other01 * _self11));
        dest.putFloat(destOffset + 12, Math.fma(_other10, _self01, _other11 * _self11));
        dest.putFloat(destOffset + 16, Math.fma(_other00, _self02, Math.fma(_other01, _self12, _other02)));
        dest.putFloat(destOffset + 20, Math.fma(_other10, _self02, Math.fma(_other11, _self12, _other12)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x3OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _other00 = other.getFloat(otherOffset + 0);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, Math.fma(_other00, _eself0, _other01 * _eself1));
            dest.putFloat(destOffset + (_lo + 1) * 4, Math.fma(_other10, _eself0, _other11 * _eself1));
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x3OpsKernelsAddress.preMulMat3x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _other00 = other.getFloat(otherOffset + 0);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other20 = other.getFloat(otherOffset + 8);
        float _other01 = other.getFloat(otherOffset + 12);
        float _other11 = other.getFloat(otherOffset + 16);
        float _other21 = other.getFloat(otherOffset + 20);
        float _other02 = other.getFloat(otherOffset + 24);
        float _other12 = other.getFloat(otherOffset + 28);
        float _other22 = other.getFloat(otherOffset + 32);
        dest.putFloat(destOffset + 0, Math.fma(_other00, _self00, _other01 * _self10));
        dest.putFloat(destOffset + 4, Math.fma(_other10, _self00, _other11 * _self10));
        dest.putFloat(destOffset + 8, Math.fma(_other20, _self00, _other21 * _self10));
        dest.putFloat(destOffset + 12, Math.fma(_other00, _self01, _other01 * _self11));
        dest.putFloat(destOffset + 16, Math.fma(_other10, _self01, _other11 * _self11));
        dest.putFloat(destOffset + 20, Math.fma(_other20, _self01, _other21 * _self11));
        dest.putFloat(destOffset + 24, Math.fma(_other00, _self02, Math.fma(_other01, _self12, _other02)));
        dest.putFloat(destOffset + 28, Math.fma(_other10, _self02, Math.fma(_other11, _self12, _other12)));
        dest.putFloat(destOffset + 32, Math.fma(_other20, _self02, Math.fma(_other21, _self12, _other22)));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            float _eother0 = other.getFloat(otherOffset + _lo * 4);
            float _eother1 = other.getFloat(otherOffset + (_lo + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, Math.fma(weight, _eother0, _eself0));
            dest.putFloat(destOffset + (_lo + 1) * 4, Math.fma(weight, _eother1, _eself1));
        }
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, _t1);
        dest.putFloat(destOffset + 4, _t0);
        dest.putFloat(destOffset + 8, -_t0);
        dest.putFloat(destOffset + 12, _t1);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        dest.putFloat(destOffset + 0, vX);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, vY);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset + 0, _vx);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _vy);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, float s) {
        dest.putFloat(destOffset + 0, s);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, s);
        dest.putFloat(destOffset + 16, 0.0f);
        dest.putFloat(destOffset + 20, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 1.0f);
        dest.putFloat(destOffset + 16, vX);
        dest.putFloat(destOffset + 20, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset + 0, 1.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 1.0f);
        dest.putFloat(destOffset + 16, _vx);
        dest.putFloat(destOffset + 20, _vy);
        return dest;
    }

    public static java.nio.ByteBuffer makeView_unsafe(java.nio.ByteBuffer dest, int destOffset, float left, float right, float bottom, float top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2x3OpsKernelsAddress.makeView_unsafe(_destBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.ByteBuffer makeView_api(java.nio.ByteBuffer dest, int destOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dest.putFloat(destOffset + 0, _t0_inv + _t0_inv);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _t1_inv + _t1_inv);
        dest.putFloat(destOffset + 16, -((left + right) * _t0_inv));
        dest.putFloat(destOffset + 20, -((bottom + top) * _t1_inv));
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.putFloat(destOffset + 4, Math.fma(_self00, _t0, _self10 * _t1));
        dest.putFloat(destOffset + 8, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.putFloat(destOffset + 12, Math.fma(_self01, _t0, _self11 * _t1));
        dest.putFloat(destOffset + 16, Math.fma(_self02, _t1, -(_self12 * _t0)));
        dest.putFloat(destOffset + 20, Math.fma(_self02, _t0, _self12 * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dest.putFloat(destOffset + 0, Math.fma(_self00, _t2, -(_self10 * _t0)));
        dest.putFloat(destOffset + 4, Math.fma(_self00, _t0, _self10 * _t2));
        dest.putFloat(destOffset + 8, Math.fma(_self01, _t2, -(_self11 * _t0)));
        dest.putFloat(destOffset + 12, Math.fma(_self01, _t0, _self11 * _t2));
        dest.putFloat(destOffset + 16, Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(_self02, _t2, -(_self12 * _t0)));
        dest.putFloat(destOffset + 20, Math.fma(_self02, _t0, _self12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float2x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dest.putFloat(destOffset + 0, Math.fma(_self00, _t2, -(_self10 * _t0)));
        dest.putFloat(destOffset + 4, Math.fma(_self00, _t0, _self10 * _t2));
        dest.putFloat(destOffset + 8, Math.fma(_self01, _t2, -(_self11 * _t0)));
        dest.putFloat(destOffset + 12, Math.fma(_self01, _t0, _self11 * _t2));
        dest.putFloat(destOffset + 16, Math.fma(_pivotx, _t5, _pivoty * _t0) + Math.fma(_self02, _t2, -(_self12 * _t0)));
        dest.putFloat(destOffset + 20, Math.fma(_self02, _t0, _self12 * _t2) + Math.fma(_pivoty, _t5, -(_pivotx * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0 * vX);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1 * vY);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, _eself0 * _vx);
            dest.putFloat(destOffset + (_lo + 1) * 4, _eself1 * _vy);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.getFloat(srcOffset + _lo * 4);
            float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
            dest.putFloat(destOffset + _lo * 4, s * _eself0);
            dest.putFloat(destOffset + (_lo + 1) * 4, s * _eself1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = 1.0f - s;
        dest.putFloat(destOffset + 0, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, Math.fma(s, _self02, pivotX * _t0));
        dest.putFloat(destOffset + 20, Math.fma(s, _self12, pivotY * _t0));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _t0 = 1.0f - s;
        dest.putFloat(destOffset + 0, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, Math.fma(s, _self02, _pivotx * _t0));
        dest.putFloat(destOffset + 20, Math.fma(s, _self12, _pivoty * _t0));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset + 0, sX * _self00);
        dest.putFloat(destOffset + 4, sY * _self10);
        dest.putFloat(destOffset + 8, sX * _self01);
        dest.putFloat(destOffset + 12, sY * _self11);
        dest.putFloat(destOffset + 16, Math.fma(pivotX, 1.0f - sX, sX * _self02));
        dest.putFloat(destOffset + 20, Math.fma(pivotY, 1.0f - sY, sY * _self12));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _sx = s.getFloat(sOffset + 0);
        float _sy = s.getFloat(sOffset + 4);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        dest.putFloat(destOffset + 0, _sx * _self00);
        dest.putFloat(destOffset + 4, _sy * _self10);
        dest.putFloat(destOffset + 8, _sx * _self01);
        dest.putFloat(destOffset + 12, _sy * _self11);
        dest.putFloat(destOffset + 16, Math.fma(_pivotx, 1.0f - _sx, _sx * _self02));
        dest.putFloat(destOffset + 20, Math.fma(_pivoty, 1.0f - _sy, _sy * _self12));
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self01);
        dest.putFloat(destOffset + 12, _self11);
        dest.putFloat(destOffset + 16, _self02 + vX);
        dest.putFloat(destOffset + 20, _self12 + vY);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self01);
        dest.putFloat(destOffset + 12, _self11);
        dest.putFloat(destOffset + 16, _self02 + _vx);
        dest.putFloat(destOffset + 20, _self12 + _vy);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset + 0, Math.fma(_self00, _t1, _self01 * _t0));
        dest.putFloat(destOffset + 4, Math.fma(_self10, _t1, _self11 * _t0));
        dest.putFloat(destOffset + 8, Math.fma(_self01, _t1, -(_self00 * _t0)));
        dest.putFloat(destOffset + 12, Math.fma(_self11, _t1, -(_self10 * _t0)));
        dest.putFloat(destOffset + 16, _self02);
        dest.putFloat(destOffset + 20, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        dest.putFloat(destOffset + 0, Math.fma(_self00, _t2, _self01 * _t0));
        dest.putFloat(destOffset + 4, Math.fma(_self10, _t2, _self11 * _t0));
        dest.putFloat(destOffset + 8, Math.fma(_self01, _t2, -(_self00 * _t0)));
        dest.putFloat(destOffset + 12, Math.fma(_self11, _t2, -(_self10 * _t0)));
        dest.putFloat(destOffset + 16, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        dest.putFloat(destOffset + 20, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float2x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _t0 = (float) Math.sin(angle);
        float _t2 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = (float) Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        float _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
        dest.putFloat(destOffset + 0, Math.fma(_self00, _t2, _self01 * _t0));
        dest.putFloat(destOffset + 4, Math.fma(_self10, _t2, _self11 * _t0));
        dest.putFloat(destOffset + 8, Math.fma(_self01, _t2, -(_self00 * _t0)));
        dest.putFloat(destOffset + 12, Math.fma(_self11, _t2, -(_self10 * _t0)));
        dest.putFloat(destOffset + 16, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        dest.putFloat(destOffset + 20, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset + 0, _self00 * vX);
        dest.putFloat(destOffset + 4, _self10 * vX);
        dest.putFloat(destOffset + 8, _self01 * vY);
        dest.putFloat(destOffset + 12, _self11 * vY);
        dest.putFloat(destOffset + 16, _self02);
        dest.putFloat(destOffset + 20, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset + 0, _self00 * _vx);
        dest.putFloat(destOffset + 4, _self10 * _vx);
        dest.putFloat(destOffset + 8, _self01 * _vy);
        dest.putFloat(destOffset + 12, _self11 * _vy);
        dest.putFloat(destOffset + 16, _self02);
        dest.putFloat(destOffset + 20, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset + 0, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, _self02);
        dest.putFloat(destOffset + 20, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.putFloat(destOffset + 0, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        dest.putFloat(destOffset + 20, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _t0 = 1.0f - s;
        float _t1 = _pivotx * _t0;
        float _t2 = _pivoty * _t0;
        dest.putFloat(destOffset + 0, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        dest.putFloat(destOffset + 20, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.putFloat(destOffset + 0, sX * _self00);
        dest.putFloat(destOffset + 4, sX * _self10);
        dest.putFloat(destOffset + 8, sY * _self01);
        dest.putFloat(destOffset + 12, sY * _self11);
        dest.putFloat(destOffset + 16, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        dest.putFloat(destOffset + 20, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _sx = s.getFloat(sOffset + 0);
        float _sy = s.getFloat(sOffset + 4);
        float _pivotx = pivot.getFloat(pivotOffset + 0);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _t2 = _pivotx * (1.0f - _sx);
        float _t3 = _pivoty * (1.0f - _sy);
        dest.putFloat(destOffset + 0, _sx * _self00);
        dest.putFloat(destOffset + 4, _sx * _self10);
        dest.putFloat(destOffset + 8, _sy * _self01);
        dest.putFloat(destOffset + 12, _sy * _self11);
        dest.putFloat(destOffset + 16, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        dest.putFloat(destOffset + 20, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self01);
        dest.putFloat(destOffset + 12, _self11);
        dest.putFloat(destOffset + 16, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        dest.putFloat(destOffset + 20, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset + 0, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self01);
        dest.putFloat(destOffset + 12, _self11);
        dest.putFloat(destOffset + 16, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        dest.putFloat(destOffset + 20, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer view_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.ByteBuffer view_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dest.putFloat(destOffset + 0, _sp0 * _self00);
        dest.putFloat(destOffset + 4, _sp0 * _self10);
        dest.putFloat(destOffset + 8, _sp1 * _self01);
        dest.putFloat(destOffset + 12, _sp1 * _self11);
        dest.putFloat(destOffset + 16, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        dest.putFloat(destOffset + 20, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset + 0, Math.fma(_self02, vZ, Math.fma(_self00, vX, _self01 * vY)));
        dest.putFloat(destOffset + 4, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        dest.putFloat(destOffset + 0, Math.fma(_self02, _vz, Math.fma(_self00, _vx, _self01 * _vy)));
        dest.putFloat(destOffset + 4, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset + 0, Math.fma(_self00, vX, _self01 * vY));
        dest.putFloat(destOffset + 4, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(_self00, _vx, _self01 * _vy));
        dest.putFloat(destOffset + 4, Math.fma(_self10, _vx, _self11 * _vy));
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset + 0, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        dest.putFloat(destOffset + 4, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _self00 = src.getFloat(srcOffset + 0);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _vx = v.getFloat(vOffset + 0);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset + 0, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        dest.putFloat(destOffset + 4, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
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
