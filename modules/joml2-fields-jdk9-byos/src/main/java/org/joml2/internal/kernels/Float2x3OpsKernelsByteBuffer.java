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
 * to keep generated sources IDE-sized; public only because {@code Float2x3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float2x3OpsKernelsByteBuffer {
    private Float2x3OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer getColumn_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        Float2x3OpsKernelsAddress.getColumn_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, col);
        return dest;
    }

    public static java.nio.ByteBuffer getColumn_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = src.getFloat(srcOffset); _idxSw1 = src.getFloat(srcOffset + 4); break;
            case 1: _idxSw0 = src.getFloat(srcOffset + 8); _idxSw1 = src.getFloat(srcOffset + 12); break;
            case 2: _idxSw0 = src.getFloat(srcOffset + 16); _idxSw1 = src.getFloat(srcOffset + 20); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.putFloat(destOffset, _idxSw0);
        dest.putFloat(destOffset + 4, _idxSw1);
        return dest;
    }

    public static float getRotationAngle_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        return Float2x3OpsKernelsAddress.getRotationAngle_unsafe(UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
    }

    public static float getRotationAngle_api(java.nio.ByteBuffer src, int srcOffset) {
        return Math.atan2(src.getFloat(srcOffset + 4), src.getFloat(srcOffset));
    }

    public static java.nio.ByteBuffer getRow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        Float2x3OpsKernelsAddress.getRow_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, row);
        return dest;
    }

    public static java.nio.ByteBuffer getRow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (row) {
            case 0: _idxSw0 = src.getFloat(srcOffset); _idxSw1 = src.getFloat(srcOffset + 8); _idxSw2 = src.getFloat(srcOffset + 16); break;
            case 1: _idxSw0 = src.getFloat(srcOffset + 4); _idxSw1 = src.getFloat(srcOffset + 12); _idxSw2 = src.getFloat(srcOffset + 20); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.putFloat(destOffset, _idxSw0);
        dest.putFloat(destOffset + 4, _idxSw1);
        dest.putFloat(destOffset + 8, _idxSw2);
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Float2x3OpsKernelsAddress.getTranslation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, _self02);
        dest.putFloat(destOffset + 4, _self12);
        return dest;
    }

    public static float determinant_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float2x3OpsKernelsAddress.determinant_unsafe(_srcBase);
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
        return Float2x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _self02 = src.getFloat(srcOffset + 16);
            float _self12 = src.getFloat(srcOffset + 20);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_self12, _self12, java.lang.Math.fma(_self11, _self11, java.lang.Math.fma(_self10, _self10, java.lang.Math.fma(_self02, _self02, java.lang.Math.fma(_self00, _self00, _self01 * _self01))))));
        } else {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _self02 = src.getFloat(srcOffset + 16);
            float _self12 = src.getFloat(srcOffset + 20);
            return (float) java.lang.Math.sqrt(((_self12) * (_self12) + (((_self11) * (_self11) + (((_self10) * (_self10) + (((_self02) * (_self02) + (((_self00) * (_self00) + (_self01 * _self01)))))))))));
        }
    }

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_api_mulAdd(dest, destOffset, src, srcOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t3 = -(_self01 * _self10);
        float _t4 = -(_self01 * _self12);
        float _t5 = -(_self02 * _self10);
        float _t9 = java.lang.Math.fma(_self00, _self11, _t3) - java.lang.Math.fma(_self01, _self10, _t3);
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return Float2x3OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t9_inv = 1.0f / _t9;
        dest.putFloat(destOffset, _self11 * _t9_inv);
        dest.putFloat(destOffset + 4, -(_self10 * _t9_inv));
        dest.putFloat(destOffset + 8, -(_self01 * _t9_inv));
        dest.putFloat(destOffset + 12, _self00 * _t9_inv);
        dest.putFloat(destOffset + 16, (java.lang.Math.fma(_self01, _self12, _t4) - java.lang.Math.fma(_self02, _self11, _t4)) * _t9_inv);
        dest.putFloat(destOffset + 20, (java.lang.Math.fma(_self02, _self10, _t5) - java.lang.Math.fma(_self00, _self12, _t5)) * _t9_inv);
        return dest;
    }

    /** {@code invert_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t3 = -(_self01 * _self10);
        float _t4 = -(_self01 * _self12);
        float _t5 = -(_self02 * _self10);
        float _t9 = ((_self00) * (_self11) + (_t3)) - ((_self01) * (_self10) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 1.1754944E-38f && java.lang.Math.abs(_t9) < 8.507059E37f)) return Float2x3OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t9_inv = 1.0f / _t9;
        dest.putFloat(destOffset, _self11 * _t9_inv);
        dest.putFloat(destOffset + 4, -(_self10 * _t9_inv));
        dest.putFloat(destOffset + 8, -(_self01 * _t9_inv));
        dest.putFloat(destOffset + 12, _self00 * _t9_inv);
        dest.putFloat(destOffset + 16, (((_self01) * (_self12) + (_t4)) - ((_self02) * (_self11) + (_t4))) * _t9_inv);
        dest.putFloat(destOffset + 20, (((_self02) * (_self10) + (_t5)) - ((_self00) * (_self12) + (_t5))) * _t9_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invert_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code invert_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
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
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22_inv = 1.0f / (java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        dest.putFloat(destOffset, _t8 * _sp0);
        dest.putFloat(destOffset + 4, -(_t11 * _sp0));
        dest.putFloat(destOffset + 8, -(_t10 * _sp1));
        dest.putFloat(destOffset + 12, _t9 * _sp1);
        dest.putFloat(destOffset + 16, (java.lang.Math.fma(_t10, _t13, _t18) - java.lang.Math.fma(_t12, _t8, _t18)) * _t22_inv);
        dest.putFloat(destOffset + 20, (java.lang.Math.fma(_t12, _t11, _t19) - java.lang.Math.fma(_t9, _t13, _t19)) * _t22_inv);
        return dest;
    }

    /** {@code invert_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
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
        float _t17 = -(_t10 * _t11);
        float _t18 = -(_t10 * _t13);
        float _t19 = -(_t12 * _t11);
        float _t22_inv = 1.0f / (((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17)));
        float _sp1 = _t0 * _t22_inv;
        float _sp0 = _t1 * _t22_inv;
        dest.putFloat(destOffset, _t8 * _sp0);
        dest.putFloat(destOffset + 4, -(_t11 * _sp0));
        dest.putFloat(destOffset + 8, -(_t10 * _sp1));
        dest.putFloat(destOffset + 12, _t9 * _sp1);
        dest.putFloat(destOffset + 16, (((_t10) * (_t13) + (_t18)) - ((_t12) * (_t8) + (_t18))) * _t22_inv);
        dest.putFloat(destOffset + 20, (((_t12) * (_t11) + (_t19)) - ((_t9) * (_t13) + (_t19))) * _t22_inv);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _other02 = other.getFloat(otherOffset + 16);
        float _other12 = other.getFloat(otherOffset + 20);
        float _t6 = -(_other01 * _other10);
        float _t7 = -(_self01 * _self10);
        float _t24 = java.lang.Math.fma(_other00, _other11, _t6) - java.lang.Math.fma(_other01, _other10, _t6);
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return Float2x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_fma_s2dc24fa2_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12, -(_self02 * _self10), -(_self01 * _self12), -(_other01 * _other12), -(_other02 * _other10), _t24, 1.0f / _t24, java.lang.Math.fma(_self00, _self11, _t7) - java.lang.Math.fma(_self01, _self10, _t7));
    }

    /** Piece 2 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma_s2dc24fa2_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _other00, float _other10, float _other01, float _other11, float _other02, float _other12, float _t8, float _t9, float _t10, float _t11, float _t24, float _t24_inv, float _t25) {
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return Float2x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t25_inv = 1.0f / _t25;
        float _t36 = _other11 / _t24;
        float _t37 = _self11 / _t25;
        float _t38 = _self00 / _t25;
        float _t41 = _other00 / _t24;
        float _t42 = -(_other01 * _t24_inv);
        float _t43 = -(_self10 * _t25_inv);
        float _t44 = -(_self01 * _t25_inv);
        float _t45 = -(_other10 * _t24_inv);
        float _t52 = _t36 * _t37;
        float _t54 = _t44 * _t36;
        float _t55 = _t43 * _t41;
        dest.putFloat(destOffset, java.lang.Math.fma(_t42, _t43, _t52) + java.lang.Math.fma(_t36, _t37, -_t52));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t45, _t37, _t55) + java.lang.Math.fma(_t43, _t41, -_t55));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t42, _t38, _t54) + java.lang.Math.fma(_t44, _t36, -_t54));
        return invertProduct_api_fma_s2dc24fa2_2(dest, destOffset, _other00, _other10, _other01, _other11, _other02, _other12, _t10, _t11, _t24_inv, _t36, _t38, _t41, _t42, _t44, _t45, -((java.lang.Math.fma(_self00, _self12, _t8) - java.lang.Math.fma(_self02, _self10, _t8)) * _t25_inv), -((java.lang.Math.fma(_self02, _self11, _t9) - java.lang.Math.fma(_self01, _self12, _t9)) * _t25_inv), _t41 * _t38);
    }

    /** Piece 3 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma_s2dc24fa2_2(java.nio.ByteBuffer dest, int destOffset, float _other00, float _other10, float _other01, float _other11, float _other02, float _other12, float _t10, float _t11, float _t24_inv, float _t36, float _t38, float _t41, float _t42, float _t44, float _t45, float _t50, float _t51, float _t53) {
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t45, _t44, _t53) + java.lang.Math.fma(_t41, _t38, -_t53));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t42, _t50, java.lang.Math.fma(_t51, _t36, -((java.lang.Math.fma(_other02, _other11, _t10) - java.lang.Math.fma(_other01, _other12, _t10)) * _t24_inv))));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t45, _t51, java.lang.Math.fma(_t50, _t41, -((java.lang.Math.fma(_other00, _other12, _t11) - java.lang.Math.fma(_other02, _other10, _t11)) * _t24_inv))));
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _other02 = other.getFloat(otherOffset + 16);
        float _other12 = other.getFloat(otherOffset + 20);
        float _t6 = -(_other01 * _other10);
        float _t7 = -(_self01 * _self10);
        float _t24 = ((_other00) * (_other11) + (_t6)) - ((_other01) * (_other10) + (_t6));
        if (!(java.lang.Math.abs(_t24) > 1.1754944E-38f && java.lang.Math.abs(_t24) < 8.507059E37f)) return Float2x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd_s234a47a5_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12, -(_self02 * _self10), -(_self01 * _self12), -(_other01 * _other12), -(_other02 * _other10), _t24, 1.0f / _t24, ((_self00) * (_self11) + (_t7)) - ((_self01) * (_self10) + (_t7)));
    }

    /** Piece 2 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd_s234a47a5_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12, float _other00, float _other10, float _other01, float _other11, float _other02, float _other12, float _t8, float _t9, float _t10, float _t11, float _t24, float _t24_inv, float _t25) {
        if (!(java.lang.Math.abs(_t25) > 1.1754944E-38f && java.lang.Math.abs(_t25) < 8.507059E37f)) return Float2x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t25_inv = 1.0f / _t25;
        float _t36 = _other11 / _t24;
        float _t37 = _self11 / _t25;
        float _t38 = _self00 / _t25;
        float _t41 = _other00 / _t24;
        float _t42 = -(_other01 * _t24_inv);
        float _t43 = -(_self10 * _t25_inv);
        float _t44 = -(_self01 * _t25_inv);
        float _t45 = -(_other10 * _t24_inv);
        float _t52 = _t36 * _t37;
        float _t53 = _t41 * _t38;
        float _t54 = _t44 * _t36;
        float _t55 = _t43 * _t41;
        dest.putFloat(destOffset, ((_t42) * (_t43) + (_t52)) + ((_t36) * (_t37) - (_t52)));
        dest.putFloat(destOffset + 4, ((_t45) * (_t37) + (_t55)) + ((_t43) * (_t41) - (_t55)));
        dest.putFloat(destOffset + 8, ((_t42) * (_t38) + (_t54)) + ((_t44) * (_t36) - (_t54)));
        dest.putFloat(destOffset + 12, ((_t45) * (_t44) + (_t53)) + ((_t41) * (_t38) - (_t53)));
        return invertProduct_api_mulAdd_s234a47a5_2(dest, destOffset, _other00, _other10, _other01, _other11, _other02, _other12, _t10, _t11, _t24_inv, _t36, _t41, _t42, _t45, -((((_self00) * (_self12) + (_t8)) - ((_self02) * (_self10) + (_t8))) * _t25_inv), -((((_self02) * (_self11) + (_t9)) - ((_self01) * (_self12) + (_t9))) * _t25_inv));
    }

    /** Piece 3 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd_s234a47a5_2(java.nio.ByteBuffer dest, int destOffset, float _other00, float _other10, float _other01, float _other11, float _other02, float _other12, float _t10, float _t11, float _t24_inv, float _t36, float _t41, float _t42, float _t45, float _t50, float _t51) {
        dest.putFloat(destOffset + 16, ((_t42) * (_t50) + (((_t51) * (_t36) - ((((_other02) * (_other11) + (_t10)) - ((_other01) * (_other12) + (_t10))) * _t24_inv)))));
        dest.putFloat(destOffset + 20, ((_t45) * (_t51) + (((_t50) * (_t41) - ((((_other00) * (_other12) + (_t11)) - ((_other02) * (_other10) + (_t11))) * _t24_inv)))));
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
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
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _other02 = other.getFloat(otherOffset + 16);
        float _other12 = other.getFloat(otherOffset + 20);
        float _t0 = unitScale(_other00, _other01, _other00);
        float _t1 = unitScale(_other10, _other11, _other10);
        float _t2 = unitScale(_self10, _self11, _self10);
        float _t3 = unitScale(_self00, _self01, _self00);
        float _t16 = _other01 * _t0;
        float _t19 = _other10 * _t1;
        float _t20 = _self10 * _t2;
        float _t23 = _self01 * _t3;
        float _t24 = _self12 * _t2;
        float _t25 = _self02 * _t3;
        float _t27 = _other12 * _t1;
        return invertProduct_degenerate_api_fma_sdbea5e19_1(dest, destOffset, _t0, _t1, _t2, _t3, _t16, _other00 * _t0, _other11 * _t1, _t19, _t20, _self00 * _t3, _self11 * _t2, _t23, _t24, _t25, _other02 * _t0, _t27, -(_t16 * _t19), -(_t23 * _t20), -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27));
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_fma_sdbea5e19_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t42, float _t43, float _t44, float _t45, float _t46) {
        float _t56 = java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42);
        float _t56_inv = 1.0f / _t56;
        float _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        float _t57_inv = 1.0f / _t57;
        float _t70 = _t18 * _t0 / _t56;
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t86 = _t70 * _t71;
        float _t89 = _t77 * _t75;
        dest.putFloat(destOffset, java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89));
        return invertProduct_degenerate_api_fma_sdbea5e19_2(dest, destOffset, _t16, _t17, _t18, _t19, _t26, _t27, _t46, -(_t26 * _t19), _t56_inv, _t70, _t72, _t75, _t76, _t78, _t79, -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv), -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv), _t75 * _t72, _t78 * _t70);
    }

    /** Piece 3 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_fma_sdbea5e19_2(java.nio.ByteBuffer dest, int destOffset, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t46, float _t47, float _t56_inv, float _t70, float _t72, float _t75, float _t76, float _t78, float _t79, float _t84, float _t85, float _t87, float _t88) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv))));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv))));
        return dest;
    }

    /** {@code invertProduct_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _other02 = other.getFloat(otherOffset + 16);
        float _other12 = other.getFloat(otherOffset + 20);
        float _t0 = unitScale(_other00, _other01, _other00);
        float _t1 = unitScale(_other10, _other11, _other10);
        float _t2 = unitScale(_self10, _self11, _self10);
        float _t3 = unitScale(_self00, _self01, _self00);
        float _t16 = _other01 * _t0;
        float _t19 = _other10 * _t1;
        float _t20 = _self10 * _t2;
        float _t23 = _self01 * _t3;
        float _t24 = _self12 * _t2;
        float _t25 = _self02 * _t3;
        float _t27 = _other12 * _t1;
        return invertProduct_degenerate_api_mulAdd_s913a584c_1(dest, destOffset, _t0, _t1, _t2, _t3, _t16, _other00 * _t0, _other11 * _t1, _t19, _t20, _self00 * _t3, _self11 * _t2, _t23, _t24, _t25, _other02 * _t0, _t27, -(_t16 * _t19), -(_t23 * _t20), -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27));
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd_s913a584c_1(java.nio.ByteBuffer dest, int destOffset, float _t0, float _t1, float _t2, float _t3, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t27, float _t42, float _t43, float _t44, float _t45, float _t46) {
        float _t56 = ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42));
        float _t56_inv = 1.0f / _t56;
        float _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        float _t57_inv = 1.0f / _t57;
        float _t70 = _t18 * _t0 / _t56;
        float _t71 = _t22 * _t3 / _t57;
        float _t72 = _t21 * _t2 / _t57;
        float _t75 = _t17 * _t1 / _t56;
        float _t76 = -(_t16 * _t1 * _t56_inv);
        float _t77 = -(_t20 * _t3 * _t57_inv);
        float _t78 = -(_t23 * _t2 * _t57_inv);
        float _t79 = -(_t19 * _t0 * _t56_inv);
        float _t86 = _t70 * _t71;
        float _t89 = _t77 * _t75;
        dest.putFloat(destOffset, ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86)));
        dest.putFloat(destOffset + 4, ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89)));
        return invertProduct_degenerate_api_mulAdd_s913a584c_2(dest, destOffset, _t16, _t17, _t18, _t19, _t26, _t27, _t46, -(_t26 * _t19), _t56_inv, _t70, _t72, _t75, _t76, _t78, _t79, -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv), -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv), _t75 * _t72, _t78 * _t70);
    }

    /** Piece 3 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd_s913a584c_2(java.nio.ByteBuffer dest, int destOffset, float _t16, float _t17, float _t18, float _t19, float _t26, float _t27, float _t46, float _t47, float _t56_inv, float _t70, float _t72, float _t75, float _t76, float _t78, float _t79, float _t84, float _t85, float _t87, float _t88) {
        dest.putFloat(destOffset + 8, ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88)));
        dest.putFloat(destOffset + 12, ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87)));
        dest.putFloat(destOffset + 16, ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv)))));
        dest.putFloat(destOffset + 20, ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv)))));
        return dest;
    }

    public static java.nio.ByteBuffer transpose_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer transpose_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, _self00);
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
        float _m00 = m.getFloat(mOffset);
        float _m10 = m.getFloat(mOffset + 4);
        float _m01 = m.getFloat(mOffset + 8);
        float _m11 = m.getFloat(mOffset + 12);
        dest.putFloat(destOffset, _m00);
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
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _self00);
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
        float _tx = t.getFloat(tOffset);
        float _ty = t.getFloat(tOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _self00);
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
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _self00);
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
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, _self00);
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
        dest.putFloat(destOffset, 1.0f);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                float _eself0 = src.getFloat(srcOffset + _lo * 4);
                float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
                float _eother0 = other.getFloat(otherOffset + _lo * 4);
                float _eother1 = other.getFloat(otherOffset + (_lo + 1) * 4);
                dest.putFloat(destOffset + _lo * 4, java.lang.Math.fma(t, _eother0 - _eself0, _eself0));
                dest.putFloat(destOffset + (_lo + 1) * 4, java.lang.Math.fma(t, _eother1 - _eself1, _eself1));
            }
            return dest;
        } else {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                float _eself0 = src.getFloat(srcOffset + _lo * 4);
                float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
                float _eother0 = other.getFloat(otherOffset + _lo * 4);
                float _eother1 = other.getFloat(otherOffset + (_lo + 1) * 4);
                dest.putFloat(destOffset + _lo * 4, ((t) * (_eother0 - _eself0) + (_eself0)));
                dest.putFloat(destOffset + (_lo + 1) * 4, ((t) * (_eother1 - _eself1) + (_eself1)));
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Float2x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _right00 = right.getFloat(rightOffset);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        float _right02 = right.getFloat(rightOffset + 16);
        float _right12 = right.getFloat(rightOffset + 20);
        dest.putFloat(destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_right02, _self00, java.lang.Math.fma(_right12, _self01, _self02)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_right02, _self10, java.lang.Math.fma(_right12, _self11, _self12)));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _right00 = right.getFloat(rightOffset);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        float _right02 = right.getFloat(rightOffset + 16);
        float _right12 = right.getFloat(rightOffset + 20);
        dest.putFloat(destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.putFloat(destOffset + 4, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.putFloat(destOffset + 8, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.putFloat(destOffset + 12, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.putFloat(destOffset + 16, ((_right02) * (_self00) + (((_right12) * (_self01) + (_self02)))));
        dest.putFloat(destOffset + 20, ((_right02) * (_self10) + (((_right12) * (_self11) + (_self12)))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x2_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _right00 = right.getFloat(rightOffset);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.putFloat(destOffset + 16, _self02);
        dest.putFloat(destOffset + 20, _self12);
        return dest;
    }

    /** {@code mulMat2x2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulMat2x2_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _right00 = right.getFloat(rightOffset);
        float _right10 = right.getFloat(rightOffset + 4);
        float _right01 = right.getFloat(rightOffset + 8);
        float _right11 = right.getFloat(rightOffset + 12);
        dest.putFloat(destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.putFloat(destOffset + 4, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.putFloat(destOffset + 8, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.putFloat(destOffset + 12, ((_right01) * (_self10) + (_right11 * _self11)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat3x3_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        float _self00 = src.getFloat(srcOffset);
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
            dest.putFloat(destOffset + _lo * 4, java.lang.Math.fma(_eright2, _self02, java.lang.Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.putFloat(destOffset + (_lo + 1) * 4, java.lang.Math.fma(_eright2, _self12, java.lang.Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.putFloat(destOffset + (_lo + 2) * 4, _eright2);
        }
        return dest;
    }

    /** {@code mulMat3x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulMat3x3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        float _self00 = src.getFloat(srcOffset);
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
            dest.putFloat(destOffset + _lo * 4, ((_eright2) * (_self02) + (((_eright0) * (_self00) + (_eright1 * _self01)))));
            dest.putFloat(destOffset + (_lo + 1) * 4, ((_eright2) * (_self12) + (((_eright0) * (_self10) + (_eright1 * _self11)))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _other02 = other.getFloat(otherOffset + 16);
        float _other12 = other.getFloat(otherOffset + 20);
        dest.putFloat(destOffset, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_other00, _self02, java.lang.Math.fma(_other01, _self12, _other02)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_other10, _self02, java.lang.Math.fma(_other11, _self12, _other12)));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other01 = other.getFloat(otherOffset + 8);
        float _other11 = other.getFloat(otherOffset + 12);
        float _other02 = other.getFloat(otherOffset + 16);
        float _other12 = other.getFloat(otherOffset + 20);
        dest.putFloat(destOffset, ((_other00) * (_self00) + (_other01 * _self10)));
        dest.putFloat(destOffset + 4, ((_other10) * (_self00) + (_other11 * _self10)));
        dest.putFloat(destOffset + 8, ((_other00) * (_self01) + (_other01 * _self11)));
        dest.putFloat(destOffset + 12, ((_other10) * (_self01) + (_other11 * _self11)));
        dest.putFloat(destOffset + 16, ((_other00) * (_self02) + (((_other01) * (_self12) + (_other02)))));
        dest.putFloat(destOffset + 20, ((_other10) * (_self02) + (((_other11) * (_self12) + (_other12)))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _other00 = other.getFloat(otherOffset);
            float _other10 = other.getFloat(otherOffset + 4);
            float _other01 = other.getFloat(otherOffset + 8);
            float _other11 = other.getFloat(otherOffset + 12);
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                float _eself0 = src.getFloat(srcOffset + _lo * 4);
                float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
                dest.putFloat(destOffset + _lo * 4, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1));
                dest.putFloat(destOffset + (_lo + 1) * 4, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1));
            }
            return dest;
        } else {
            float _other00 = other.getFloat(otherOffset);
            float _other10 = other.getFloat(otherOffset + 4);
            float _other01 = other.getFloat(otherOffset + 8);
            float _other11 = other.getFloat(otherOffset + 12);
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                float _eself0 = src.getFloat(srcOffset + _lo * 4);
                float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
                dest.putFloat(destOffset + _lo * 4, ((_other00) * (_eself0) + (_other01 * _eself1)));
                dest.putFloat(destOffset + (_lo + 1) * 4, ((_other10) * (_eself0) + (_other11 * _eself1)));
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer preMulMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float2x3OpsKernelsAddress.preMulMat3x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat3x3_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code preMulMat3x3_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMulMat3x3_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other20 = other.getFloat(otherOffset + 8);
        float _other01 = other.getFloat(otherOffset + 12);
        float _other11 = other.getFloat(otherOffset + 16);
        float _other21 = other.getFloat(otherOffset + 20);
        float _other02 = other.getFloat(otherOffset + 24);
        float _other12 = other.getFloat(otherOffset + 28);
        float _other22 = other.getFloat(otherOffset + 32);
        dest.putFloat(destOffset, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_other20, _self00, _other21 * _self10));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        return preMulMat3x3_api_fma_s8bc2d90_1(dest, destOffset, _self01, _self11, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMulMat3x3_api_fma_s8bc2d90_1(java.nio.ByteBuffer dest, int destOffset, float _self01, float _self11, float _self02, float _self12, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_other20, _self01, _other21 * _self11));
        dest.putFloat(destOffset + 24, java.lang.Math.fma(_other00, _self02, java.lang.Math.fma(_other01, _self12, _other02)));
        dest.putFloat(destOffset + 28, java.lang.Math.fma(_other10, _self02, java.lang.Math.fma(_other11, _self12, _other12)));
        dest.putFloat(destOffset + 32, java.lang.Math.fma(_other20, _self02, java.lang.Math.fma(_other21, _self12, _other22)));
        return dest;
    }

    /** {@code preMulMat3x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMulMat3x3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _other00 = other.getFloat(otherOffset);
        float _other10 = other.getFloat(otherOffset + 4);
        float _other20 = other.getFloat(otherOffset + 8);
        float _other01 = other.getFloat(otherOffset + 12);
        float _other11 = other.getFloat(otherOffset + 16);
        float _other21 = other.getFloat(otherOffset + 20);
        float _other02 = other.getFloat(otherOffset + 24);
        float _other12 = other.getFloat(otherOffset + 28);
        float _other22 = other.getFloat(otherOffset + 32);
        dest.putFloat(destOffset, ((_other00) * (_self00) + (_other01 * _self10)));
        dest.putFloat(destOffset + 4, ((_other10) * (_self00) + (_other11 * _self10)));
        dest.putFloat(destOffset + 8, ((_other20) * (_self00) + (_other21 * _self10)));
        dest.putFloat(destOffset + 12, ((_other00) * (_self01) + (_other01 * _self11)));
        dest.putFloat(destOffset + 16, ((_other10) * (_self01) + (_other11 * _self11)));
        dest.putFloat(destOffset + 20, ((_other20) * (_self01) + (_other21 * _self11)));
        return preMulMat3x3_api_mulAdd_s29efad73_1(dest, destOffset, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMulMat3x3_api_mulAdd_s29efad73_1(java.nio.ByteBuffer dest, int destOffset, float _self02, float _self12, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        dest.putFloat(destOffset + 24, ((_other00) * (_self02) + (((_other01) * (_self12) + (_other02)))));
        dest.putFloat(destOffset + 28, ((_other10) * (_self02) + (((_other11) * (_self12) + (_other12)))));
        dest.putFloat(destOffset + 32, ((_other20) * (_self02) + (((_other21) * (_self12) + (_other22)))));
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                float _eself0 = src.getFloat(srcOffset + _lo * 4);
                float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
                float _eother0 = other.getFloat(otherOffset + _lo * 4);
                float _eother1 = other.getFloat(otherOffset + (_lo + 1) * 4);
                dest.putFloat(destOffset + _lo * 4, java.lang.Math.fma(weight, _eother0, _eself0));
                dest.putFloat(destOffset + (_lo + 1) * 4, java.lang.Math.fma(weight, _eother1, _eself1));
            }
            return dest;
        } else {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                float _eself0 = src.getFloat(srcOffset + _lo * 4);
                float _eself1 = src.getFloat(srcOffset + (_lo + 1) * 4);
                float _eother0 = other.getFloat(otherOffset + _lo * 4);
                float _eother1 = other.getFloat(otherOffset + (_lo + 1) * 4);
                dest.putFloat(destOffset + _lo * 4, ((weight) * (_eother0) + (_eself0)));
                dest.putFloat(destOffset + (_lo + 1) * 4, ((weight) * (_eother1) + (_eself1)));
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer makeRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float2x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_api(java.nio.ByteBuffer dest, int destOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, _t0);
        dest.putFloat(destOffset + 4, _t1);
        dest.putFloat(destOffset + 8, -_t1);
        dest.putFloat(destOffset + 12, _t0);
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
        dest.putFloat(destOffset, vX);
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
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset, _vx);
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
        dest.putFloat(destOffset, s);
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
        dest.putFloat(destOffset, 1.0f);
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
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        dest.putFloat(destOffset, 1.0f);
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
        dest.putFloat(destOffset, _t0_inv + _t0_inv);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, _t1_inv + _t1_inv);
        dest.putFloat(destOffset + 16, -(left + right) * _t0_inv);
        dest.putFloat(destOffset + 20, -(bottom + top) * _t1_inv);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self02, _t1, _self12 * _t0));
        return dest;
    }

    /** {@code preRotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.putFloat(destOffset + 4, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.putFloat(destOffset + 8, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.putFloat(destOffset + 12, ((_self01) * (_t1) + (_self11 * _t0)));
        dest.putFloat(destOffset + 16, ((_self02) * (_t0) - (_self12 * _t1)));
        dest.putFloat(destOffset + 20, ((_self02) * (_t1) + (_self12 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self02, _t1, _self12 * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1)));
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.putFloat(destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.putFloat(destOffset + 4, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.putFloat(destOffset + 8, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.putFloat(destOffset + 12, ((_self01) * (_t1) + (_self11 * _t0)));
        dest.putFloat(destOffset + 16, ((pivotX) * (_t6) + (pivotY * _t1)) + ((_self02) * (_t0) - (_self12 * _t1)));
        dest.putFloat(destOffset + 20, ((_self02) * (_t1) + (_self12 * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_pivotx, _t6, _pivoty * _t1) + java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self02, _t1, _self12 * _t0) + java.lang.Math.fma(_pivoty, _t6, -(_pivotx * _t1)));
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t6 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        dest.putFloat(destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.putFloat(destOffset + 4, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.putFloat(destOffset + 8, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.putFloat(destOffset + 12, ((_self01) * (_t1) + (_self11 * _t0)));
        dest.putFloat(destOffset + 16, ((_pivotx) * (_t6) + (_pivoty * _t1)) + ((_self02) * (_t0) - (_self12 * _t1)));
        dest.putFloat(destOffset + 20, ((_self02) * (_t1) + (_self12 * _t0)) + ((_pivoty) * (_t6) - (_pivotx * _t1)));
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
        float _vx = v.getFloat(vOffset);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = 1.0f - s;
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(s, _self02, pivotX * _t0));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(s, _self12, pivotY * _t0));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = 1.0f - s;
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, ((s) * (_self02) + (pivotX * _t0)));
        dest.putFloat(destOffset + 20, ((s) * (_self12) + (pivotY * _t0)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = 1.0f - s;
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(s, _self02, _pivotx * _t0));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(s, _self12, _pivoty * _t0));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = 1.0f - s;
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, ((s) * (_self02) + (_pivotx * _t0)));
        dest.putFloat(destOffset + 20, ((s) * (_self12) + (_pivoty * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, sX * _self00);
        dest.putFloat(destOffset + 4, sY * _self10);
        dest.putFloat(destOffset + 8, sX * _self01);
        dest.putFloat(destOffset + 12, sY * _self11);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(pivotX, 1.0f - sX, sX * _self02));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(pivotY, 1.0f - sY, sY * _self12));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, sX * _self00);
        dest.putFloat(destOffset + 4, sY * _self10);
        dest.putFloat(destOffset + 8, sX * _self01);
        dest.putFloat(destOffset + 12, sY * _self11);
        dest.putFloat(destOffset + 16, ((pivotX) * (1.0f - sX) + (sX * _self02)));
        dest.putFloat(destOffset + 20, ((pivotY) * (1.0f - sY) + (sY * _self12)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        float _sx = s.getFloat(sOffset);
        float _sy = s.getFloat(sOffset + 4);
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, _sx * _self00);
        dest.putFloat(destOffset + 4, _sy * _self10);
        dest.putFloat(destOffset + 8, _sx * _self01);
        dest.putFloat(destOffset + 12, _sy * _self11);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_pivotx, 1.0f - _sx, _sx * _self02));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_pivoty, 1.0f - _sy, _sy * _self12));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _sx = s.getFloat(sOffset);
        float _sy = s.getFloat(sOffset + 4);
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, _sx * _self00);
        dest.putFloat(destOffset + 4, _sy * _self10);
        dest.putFloat(destOffset + 8, _sx * _self01);
        dest.putFloat(destOffset + 12, _sy * _self11);
        dest.putFloat(destOffset + 16, ((_pivotx) * (1.0f - _sx) + (_sx * _self02)));
        dest.putFloat(destOffset + 20, ((_pivoty) * (1.0f - _sy) + (_sy * _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, _self00);
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
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, _self00);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.putFloat(destOffset + 16, _self02);
        dest.putFloat(destOffset + 20, _self12);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        dest.putFloat(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.putFloat(destOffset + 4, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.putFloat(destOffset + 8, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.putFloat(destOffset + 12, ((_self11) * (_t0) - (_self10 * _t1)));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        float _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        float _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        dest.putFloat(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.putFloat(destOffset + 4, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.putFloat(destOffset + 8, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.putFloat(destOffset + 12, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.putFloat(destOffset + 16, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.putFloat(destOffset + 20, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1);
        float _t11 = java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1));
        dest.putFloat(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t9 = _t0 > 0.0f ? _t1 * _t1 / (1.0f + _t0) : 1.0f - _t0;
        float _t10 = ((_pivotx) * (_t9) + (_pivoty * _t1));
        float _t11 = ((_pivoty) * (_t9) - (_pivotx * _t1));
        dest.putFloat(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.putFloat(destOffset + 4, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.putFloat(destOffset + 8, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.putFloat(destOffset + 12, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.putFloat(destOffset + 16, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.putFloat(destOffset + 20, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, _self00 * vX);
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
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, _self00 * _vx);
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
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, s * _self00);
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.putFloat(destOffset + 20, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = 1.0f - s;
        float _t1 = pivot.getFloat(pivotOffset) * _t0;
        float _t2 = pivot.getFloat(pivotOffset + 4) * _t0;
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t0 = 1.0f - s;
        float _t1 = pivot.getFloat(pivotOffset) * _t0;
        float _t2 = pivot.getFloat(pivotOffset + 4) * _t0;
        dest.putFloat(destOffset, s * _self00);
        dest.putFloat(destOffset + 4, s * _self10);
        dest.putFloat(destOffset + 8, s * _self01);
        dest.putFloat(destOffset + 12, s * _self11);
        dest.putFloat(destOffset + 16, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.putFloat(destOffset + 20, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.putFloat(destOffset, sX * _self00);
        dest.putFloat(destOffset + 4, sX * _self10);
        dest.putFloat(destOffset + 8, sY * _self01);
        dest.putFloat(destOffset + 12, sY * _self11);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.putFloat(destOffset, sX * _self00);
        dest.putFloat(destOffset + 4, sX * _self10);
        dest.putFloat(destOffset + 8, sY * _self01);
        dest.putFloat(destOffset + 12, sY * _self11);
        dest.putFloat(destOffset + 16, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.putFloat(destOffset + 20, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
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
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        float _sx = s.getFloat(sOffset);
        float _sy = s.getFloat(sOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t2 = pivot.getFloat(pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.getFloat(pivotOffset + 4) * (1.0f - _sy);
        dest.putFloat(destOffset, _sx * _self00);
        dest.putFloat(destOffset + 4, _sx * _self10);
        dest.putFloat(destOffset + 8, _sy * _self01);
        dest.putFloat(destOffset + 12, _sy * _self11);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _sx = s.getFloat(sOffset);
        float _sy = s.getFloat(sOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        float _t2 = pivot.getFloat(pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.getFloat(pivotOffset + 4) * (1.0f - _sy);
        dest.putFloat(destOffset, _sx * _self00);
        dest.putFloat(destOffset + 4, _sx * _self10);
        dest.putFloat(destOffset + 8, _sy * _self01);
        dest.putFloat(destOffset + 12, _sy * _self11);
        dest.putFloat(destOffset + 16, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.putFloat(destOffset + 20, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _self02 = src.getFloat(srcOffset + 16);
            float _self12 = src.getFloat(srcOffset + 20);
            dest.putFloat(destOffset, _self00);
            dest.putFloat(destOffset + 4, _self10);
            dest.putFloat(destOffset + 8, _self01);
            dest.putFloat(destOffset + 12, _self11);
            dest.putFloat(destOffset + 16, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
            dest.putFloat(destOffset + 20, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
            return dest;
        } else {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _self02 = src.getFloat(srcOffset + 16);
            float _self12 = src.getFloat(srcOffset + 20);
            dest.putFloat(destOffset, _self00);
            dest.putFloat(destOffset + 4, _self10);
            dest.putFloat(destOffset + 8, _self01);
            dest.putFloat(destOffset + 12, _self11);
            dest.putFloat(destOffset + 16, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
            dest.putFloat(destOffset + 20, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self01);
        dest.putFloat(destOffset + 12, _self11);
        dest.putFloat(destOffset + 16, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
        dest.putFloat(destOffset + 20, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        return dest;
    }

    /** {@code translate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer translate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _self00 = src.getFloat(srcOffset);
        float _self10 = src.getFloat(srcOffset + 4);
        float _self01 = src.getFloat(srcOffset + 8);
        float _self11 = src.getFloat(srcOffset + 12);
        float _self02 = src.getFloat(srcOffset + 16);
        float _self12 = src.getFloat(srcOffset + 20);
        dest.putFloat(destOffset, _self00);
        dest.putFloat(destOffset + 4, _self10);
        dest.putFloat(destOffset + 8, _self01);
        dest.putFloat(destOffset + 12, _self11);
        dest.putFloat(destOffset + 16, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
        dest.putFloat(destOffset + 20, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
        return dest;
    }

    public static java.nio.ByteBuffer view_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.ByteBuffer view_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        float _self00 = src.getFloat(srcOffset);
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
        dest.putFloat(destOffset, _sp0 * _self00);
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
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _self02 = src.getFloat(srcOffset + 16);
            float _self12 = src.getFloat(srcOffset + 20);
            dest.putFloat(destOffset, java.lang.Math.fma(_self02, vZ, java.lang.Math.fma(_self00, vX, _self01 * vY)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, vZ, java.lang.Math.fma(_self10, vX, _self11 * vY)));
            return dest;
        } else {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _self02 = src.getFloat(srcOffset + 16);
            float _self12 = src.getFloat(srcOffset + 20);
            dest.putFloat(destOffset, ((_self02) * (vZ) + (((_self00) * (vX) + (_self01 * vY)))));
            dest.putFloat(destOffset + 4, ((_self12) * (vZ) + (((_self10) * (vX) + (_self11 * vY)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer mulVec3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = v.getFloat(vOffset);
            float _vy = v.getFloat(vOffset + 4);
            float _vz = v.getFloat(vOffset + 8);
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _self02 = src.getFloat(srcOffset + 16);
            float _self12 = src.getFloat(srcOffset + 20);
            dest.putFloat(destOffset, java.lang.Math.fma(_self02, _vz, java.lang.Math.fma(_self00, _vx, _self01 * _vy)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_self12, _vz, java.lang.Math.fma(_self10, _vx, _self11 * _vy)));
            return dest;
        } else {
            float _vx = v.getFloat(vOffset);
            float _vy = v.getFloat(vOffset + 4);
            float _vz = v.getFloat(vOffset + 8);
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _self02 = src.getFloat(srcOffset + 16);
            float _self12 = src.getFloat(srcOffset + 20);
            dest.putFloat(destOffset, ((_self02) * (_vz) + (((_self00) * (_vx) + (_self01 * _vy)))));
            dest.putFloat(destOffset + 4, ((_self12) * (_vz) + (((_self10) * (_vx) + (_self11 * _vy)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
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

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
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

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float2x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _self02 = src.getFloat(srcOffset + 16);
            float _self12 = src.getFloat(srcOffset + 20);
            dest.putFloat(destOffset, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
            return dest;
        } else {
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _self02 = src.getFloat(srcOffset + 16);
            float _self12 = src.getFloat(srcOffset + 20);
            dest.putFloat(destOffset, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
            dest.putFloat(destOffset + 4, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float2x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _vx = v.getFloat(vOffset);
            float _vy = v.getFloat(vOffset + 4);
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _self02 = src.getFloat(srcOffset + 16);
            float _self12 = src.getFloat(srcOffset + 20);
            dest.putFloat(destOffset, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
            return dest;
        } else {
            float _vx = v.getFloat(vOffset);
            float _vy = v.getFloat(vOffset + 4);
            float _self00 = src.getFloat(srcOffset);
            float _self10 = src.getFloat(srcOffset + 4);
            float _self01 = src.getFloat(srcOffset + 8);
            float _self11 = src.getFloat(srcOffset + 12);
            float _self02 = src.getFloat(srcOffset + 16);
            float _self12 = src.getFloat(srcOffset + 20);
            dest.putFloat(destOffset, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
            dest.putFloat(destOffset + 4, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
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
