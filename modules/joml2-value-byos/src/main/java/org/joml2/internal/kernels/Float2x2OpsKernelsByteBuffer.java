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
 * to keep generated sources IDE-sized; public only because {@code Float2x2Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float2x2OpsKernelsByteBuffer {
    private Float2x2OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer getColumn_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        Float2x2OpsKernelsAddress.getColumn_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, col);
        return dest;
    }

    public static java.nio.ByteBuffer getColumn_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = src.getFloat(srcOffset); _idxSw1 = src.getFloat(srcOffset + 4); break;
            case 1: _idxSw0 = src.getFloat(srcOffset + 8); _idxSw1 = src.getFloat(srcOffset + 12); break;
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
        float _idxSw0;
        float _idxSw1;
        switch (row) {
            case 0: _idxSw0 = src.getFloat(srcOffset); _idxSw1 = src.getFloat(srcOffset + 8); break;
            case 1: _idxSw0 = src.getFloat(srcOffset + 4); _idxSw1 = src.getFloat(srcOffset + 12); break;
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _t1 = -(_self01 * _self10);
            return java.lang.Math.fma(src.getFloat(srcOffset), src.getFloat(srcOffset + 12), _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        } else {
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _t1 = -(_self01 * _self10);
            return ((src.getFloat(srcOffset)) * (src.getFloat(srcOffset + 12)) + (_t1)) - ((_self01) * (_self10) + (_t1));
        }
    }

    public static float frobeniusNorm_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2x2OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_self11, _self11, java.lang.Math.fma(_self10, _self10, java.lang.Math.fma(_self00, _self00, _self01 * _self01))));
        } else {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            return (float) java.lang.Math.sqrt(((_self11) * (_self11) + (((_self10) * (_self10) + (((_self00) * (_self00) + (_self01 * _self01)))))));
        }
    }

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_api_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _t1 = -(_self01 * _self10);
        float _t5 = java.lang.Math.fma(_self00, _self11, _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x2OpsKernelsByteBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Float2x2OpsKernelsByteBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
        }
        float _t5_inv = 1.0f / _t5;
        dest.putFloat(destOffset, _self11 * _t5_inv);
        dest.putFloat(destOffset + 4, -(_self10 * _t5_inv));
        dest.putFloat(destOffset + 8, -(_self01 * _t5_inv));
        dest.putFloat(destOffset + 12, _self00 * _t5_inv);
        return dest;
    }

    /** {@code invert_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _t1 = -(_self01 * _self10);
        float _t5 = ((_self00) * (_self11) + (_t1)) - ((_self01) * (_self10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x2OpsKernelsByteBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Float2x2OpsKernelsByteBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
        }
        float _t5_inv = 1.0f / _t5;
        dest.putFloat(destOffset, _self11 * _t5_inv);
        dest.putFloat(destOffset + 4, -(_self10 * _t5_inv));
        dest.putFloat(destOffset + 8, -(_self01 * _t5_inv));
        dest.putFloat(destOffset + 12, _self00 * _t5_inv);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
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
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dest.putFloat(destOffset, _t6 * _sp0);
        dest.putFloat(destOffset + 4, -(_t9 * _sp0));
        dest.putFloat(destOffset + 8, -(_t8 * _sp1));
        dest.putFloat(destOffset + 12, _t7 * _sp1);
        return dest;
    }

    /** {@code invert_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
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
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _t2 = -(_other01 * _other10);
        float _t3 = -(_self01 * _self10);
        float _t10 = java.lang.Math.fma(_other00, _other11, _t2) - java.lang.Math.fma(_other01, _other10, _t2);
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return Float2x2OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t10_inv = 1.0f / _t10;
        float _t11 = java.lang.Math.fma(_self00, _self11, _t3) - java.lang.Math.fma(_self01, _self10, _t3);
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return Float2x2OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t11_inv = 1.0f / _t11;
        float _t22 = _other11 / _t10;
        float _t23 = _self11 / _t11;
        float _t24 = _self00 / _t11;
        float _t27 = _other00 / _t10;
        float _t30 = -(_self01 * _t11_inv);
        return invertProduct_api_fma_s2dc24fa2_1(dest, destOffset, _t22, _t23, _t24, _t27, -(_other01 * _t10_inv), -(_self10 * _t11_inv), _t30, -(_other10 * _t10_inv), _t22 * _t23, _t27 * _t24, _t30 * _t22);
    }

    /** Piece 2 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma_s2dc24fa2_1(java.nio.ByteBuffer dest, int destOffset, float _t22, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t33, float _t34) {
        float _t35 = _t29 * _t27;
        dest.putFloat(destOffset, java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33));
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _t2 = -(_other01 * _other10);
        float _t3 = -(_self01 * _self10);
        float _t10 = ((_other00) * (_other11) + (_t2)) - ((_other01) * (_other10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 1.1754944E-38f && java.lang.Math.abs(_t10) < 8.507059E37f)) return Float2x2OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t10_inv = 1.0f / _t10;
        float _t11 = ((_self00) * (_self11) + (_t3)) - ((_self01) * (_self10) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return Float2x2OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t11_inv = 1.0f / _t11;
        float _t22 = _other11 / _t10;
        float _t23 = _self11 / _t11;
        float _t24 = _self00 / _t11;
        float _t27 = _other00 / _t10;
        float _t30 = -(_self01 * _t11_inv);
        return invertProduct_api_mulAdd_s234a47a5_1(dest, destOffset, _t22, _t23, _t24, _t27, -(_other01 * _t10_inv), -(_self10 * _t11_inv), _t30, -(_other10 * _t10_inv), _t22 * _t23, _t27 * _t24, _t30 * _t22);
    }

    /** Piece 2 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd_s234a47a5_1(java.nio.ByteBuffer dest, int destOffset, float _t22, float _t23, float _t24, float _t27, float _t28, float _t29, float _t30, float _t31, float _t32, float _t33, float _t34) {
        float _t35 = _t29 * _t27;
        dest.putFloat(destOffset, ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32)));
        dest.putFloat(destOffset + 4, ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35)));
        dest.putFloat(destOffset + 8, ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34)));
        dest.putFloat(destOffset + 12, ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33)));
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x2OpsKernelsByteBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x2OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _t0 = unitScale(_other00, _other01, _other00);
        float _t1 = unitScale(_other10, _other11, _other10);
        float _t2 = unitScale(_self10, _self11, _self10);
        float _t3 = unitScale(_self00, _self01, _self00);
        float _t12 = _other01 * _t0;
        float _t13 = _other00 * _t0;
        float _t14 = _other11 * _t1;
        float _t15 = _other10 * _t1;
        float _t16 = _self10 * _t2;
        float _t17 = _self00 * _t3;
        float _t18 = _self11 * _t2;
        float _t19 = _self01 * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        float _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        return invertProduct_degenerate_api_fma_sdbea5e19_1(dest, destOffset, _t0, _t1, _t2, _t3, _t12, _t15, _t16, _t19, 1.0f / _t36, 1.0f / _t37, _t14 * _t0 / _t36, _t18 * _t3 / _t37, _t17 * _t2 / _t37, _t13 * _t1 / _t36);
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_fma_sdbea5e19_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t1, float _t2, float _t3, float _t12, float _t15, float _t16, float _t19, float _t36_inv, float _t37_inv, float _t48, float _t49, float _t50, float _t53) {
        float _t54 = -(_t12 * _t1 * _t36_inv);
        float _t55 = -(_t16 * _t3 * _t37_inv);
        float _t56 = -(_t19 * _t2 * _t37_inv);
        float _t57 = -(_t15 * _t0 * _t36_inv);
        float _t58 = _t48 * _t49;
        float _t59 = _t53 * _t50;
        float _t60 = _t56 * _t48;
        float _t61 = _t55 * _t53;
        dest.putFloat(destOffset, java.lang.Math.fma(_t54, _t55, _t58) + java.lang.Math.fma(_t48, _t49, -_t58));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t57, _t49, _t61) + java.lang.Math.fma(_t55, _t53, -_t61));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t54, _t50, _t60) + java.lang.Math.fma(_t56, _t48, -_t60));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t57, _t56, _t59) + java.lang.Math.fma(_t53, _t50, -_t59));
        return dest;
    }

    /** {@code invertProduct_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _t0 = unitScale(_other00, _other01, _other00);
        float _t1 = unitScale(_other10, _other11, _other10);
        float _t2 = unitScale(_self10, _self11, _self10);
        float _t3 = unitScale(_self00, _self01, _self00);
        float _t12 = _other01 * _t0;
        float _t13 = _other00 * _t0;
        float _t14 = _other11 * _t1;
        float _t15 = _other10 * _t1;
        float _t16 = _self10 * _t2;
        float _t17 = _self00 * _t3;
        float _t18 = _self11 * _t2;
        float _t19 = _self01 * _t3;
        float _t30 = -(_t12 * _t15);
        float _t31 = -(_t19 * _t16);
        float _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        float _t36_inv = 1.0f / _t36;
        float _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        return invertProduct_degenerate_api_mulAdd_s913a584c_1(dest, destOffset, _t0, _t2, _t3, _t15, _t16, _t19, _t36_inv, 1.0f / _t37, _t14 * _t0 / _t36, _t18 * _t3 / _t37, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv));
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd_s913a584c_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t2, float _t3, float _t15, float _t16, float _t19, float _t36_inv, float _t37_inv, float _t48, float _t49, float _t50, float _t53, float _t54) {
        float _t55 = -(_t16 * _t3 * _t37_inv);
        float _t56 = -(_t19 * _t2 * _t37_inv);
        float _t57 = -(_t15 * _t0 * _t36_inv);
        float _t58 = _t48 * _t49;
        float _t59 = _t53 * _t50;
        float _t60 = _t56 * _t48;
        float _t61 = _t55 * _t53;
        dest.putFloat(destOffset, ((_t54) * (_t55) + (_t58)) + ((_t48) * (_t49) - (_t58)));
        dest.putFloat(destOffset + 4, ((_t57) * (_t49) + (_t61)) + ((_t55) * (_t53) - (_t61)));
        dest.putFloat(destOffset + 8, ((_t54) * (_t50) + (_t60)) + ((_t56) * (_t48) - (_t60)));
        dest.putFloat(destOffset + 12, ((_t57) * (_t56) + (_t59)) + ((_t53) * (_t50) - (_t59)));
        return dest;
    }

    public static java.nio.ByteBuffer normal_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x2OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normal_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normal_api_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _t1 = -(_self01 * _self10);
        float _t5 = java.lang.Math.fma(_self00, _self11, _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x2OpsKernelsByteBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Float2x2OpsKernelsByteBuffer.normal_degenerate_api(dest, destOffset, src, srcOffset);
        }
        float _t5_inv = 1.0f / _t5;
        dest.putFloat(destOffset, _self11 * _t5_inv);
        dest.putFloat(destOffset + 4, -(_self01 * _t5_inv));
        dest.putFloat(destOffset + 8, -(_self10 * _t5_inv));
        dest.putFloat(destOffset + 12, _self00 * _t5_inv);
        return dest;
    }

    /** {@code normal_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normal_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _t1 = -(_self01 * _self10);
        float _t5 = ((_self00) * (_self11) + (_t1)) - ((_self01) * (_self10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x2OpsKernelsByteBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Float2x2OpsKernelsByteBuffer.normal_degenerate_api(dest, destOffset, src, srcOffset);
        }
        float _t5_inv = 1.0f / _t5;
        dest.putFloat(destOffset, _self11 * _t5_inv);
        dest.putFloat(destOffset + 4, -(_self01 * _t5_inv));
        dest.putFloat(destOffset + 8, -(_self10 * _t5_inv));
        dest.putFloat(destOffset + 12, _self00 * _t5_inv);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
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
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
        dest.putFloat(destOffset, _t6 * _sp0);
        dest.putFloat(destOffset + 4, -(_t8 * _sp1));
        dest.putFloat(destOffset + 8, -(_t9 * _sp0));
        dest.putFloat(destOffset + 12, _t7 * _sp1);
        return dest;
    }

    /** {@code normal_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normal_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
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
        float _t11 = -(_t8 * _t9);
        float _t14_inv = 1.0f / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        float _sp1 = _t0 * _t14_inv;
        float _sp0 = _t1 * _t14_inv;
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(t, _other00 - _self00, _self00));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(t, _other10 - _self10, _self10));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(t, _other01 - _self01, _self01));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(t, _other11 - _self11, _self11));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, ((t) * (_other00 - _self00) + (_self00)));
        dest.putFloat(destOffset + 4, ((t) * (_other10 - _self10) + (_self10)));
        dest.putFloat(destOffset + 8, ((t) * (_other01 - _self01) + (_self01)));
        dest.putFloat(destOffset + 12, ((t) * (_other11 - _self11) + (_self11)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _right00 = right.getFloat(rightOffset);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _right00 = right.getFloat(rightOffset);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        dest.putFloat(destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.putFloat(destOffset + 4, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.putFloat(destOffset + 8, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.putFloat(destOffset + 12, ((_right01) * (_self10) + (_right11 * _self11)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, ((_other00) * (_self00) + (_other01 * _self10)));
        dest.putFloat(destOffset + 4, ((_other10) * (_self00) + (_other11 * _self10)));
        dest.putFloat(destOffset + 8, ((_other00) * (_self01) + (_other01 * _self11)));
        dest.putFloat(destOffset + 12, ((_other10) * (_self01) + (_other11 * _self11)));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _other00 = other.getFloat(otherOffset);
            float _other10 = other.getFloat(otherOffset + 4);
            float _other01 = other.getFloat(otherOffset + 8);
            float _other11 = other.getFloat(otherOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(weight, _other00, _self00));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(weight, _other10, _self10));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(weight, _other01, _self01));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(weight, _other11, _self11));
            return dest;
        } else {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _other00 = other.getFloat(otherOffset);
            float _other10 = other.getFloat(otherOffset + 4);
            float _other01 = other.getFloat(otherOffset + 8);
            float _other11 = other.getFloat(otherOffset + 12);
            dest.putFloat(destOffset, ((weight) * (_other00) + (_self00)));
            dest.putFloat(destOffset + 4, ((weight) * (_other10) + (_self10)));
            dest.putFloat(destOffset + 8, ((weight) * (_other01) + (_self01)));
            dest.putFloat(destOffset + 12, ((weight) * (_other11) + (_self11)));
            return dest;
        }
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
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, _t0);
        dest.putFloat(destOffset + 4, _t1);
        dest.putFloat(destOffset + 8, -_t1);
        dest.putFloat(destOffset + 12, _t0);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
            return dest;
        } else {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.putFloat(destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
            dest.putFloat(destOffset + 4, ((_self00) * (_t1) + (_self10 * _t0)));
            dest.putFloat(destOffset + 8, ((_self01) * (_t0) - (_self11 * _t1)));
            dest.putFloat(destOffset + 12, ((_self01) * (_t1) + (_self11 * _t0)));
            return dest;
        }
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
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
            return dest;
        } else {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.putFloat(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
            dest.putFloat(destOffset + 4, ((_self10) * (_t0) + (_self11 * _t1)));
            dest.putFloat(destOffset + 8, ((_self01) * (_t0) - (_self00 * _t1)));
            dest.putFloat(destOffset + 12, ((_self11) * (_t0) - (_self10 * _t1)));
            return dest;
        }
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
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(_self00, vX, _self01 * vY));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, vX, _self11 * vY));
            return dest;
        } else {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((_self00) * (vX) + (_self01 * vY)));
            dest.putFloat(destOffset + 4, ((_self10) * (vX) + (_self11 * vY)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer mulVec2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = v.getFloat(vOffset);
            float _vy = v.getFloat(vOffset + 4);
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(_self00, _vx, _self01 * _vy));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _vx, _self11 * _vy));
            return dest;
        } else {
            float _vx = v.getFloat(vOffset);
            float _vy = v.getFloat(vOffset + 4);
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((_self00) * (_vx) + (_self01 * _vy)));
            dest.putFloat(destOffset + 4, ((_self10) * (_vx) + (_self11 * _vy)));
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
