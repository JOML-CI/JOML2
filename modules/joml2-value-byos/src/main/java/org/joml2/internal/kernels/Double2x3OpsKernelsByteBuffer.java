// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2x3Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double2x3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double2x3OpsKernelsByteBuffer {
    private Double2x3OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer getColumn_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        Double2x3OpsKernelsAddress.getColumn_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, col);
        return dest;
    }

    public static java.nio.ByteBuffer getColumn_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = src.getDouble(srcOffset); _idxSw1 = src.getDouble(srcOffset + 8); break;
            case 1: _idxSw0 = src.getDouble(srcOffset + 16); _idxSw1 = src.getDouble(srcOffset + 24); break;
            case 2: _idxSw0 = src.getDouble(srcOffset + 32); _idxSw1 = src.getDouble(srcOffset + 40); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.putDouble(destOffset, _idxSw0);
        dest.putDouble(destOffset + 8, _idxSw1);
        return dest;
    }

    public static double getRotationAngle_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        return Double2x3OpsKernelsAddress.getRotationAngle_unsafe(UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
    }

    public static double getRotationAngle_api(java.nio.ByteBuffer src, int srcOffset) {
        return Math.atan2(src.getDouble(srcOffset + 8), src.getDouble(srcOffset));
    }

    public static java.nio.ByteBuffer getRow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        Double2x3OpsKernelsAddress.getRow_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, row);
        return dest;
    }

    public static java.nio.ByteBuffer getRow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (row) {
            case 0: _idxSw0 = src.getDouble(srcOffset); _idxSw1 = src.getDouble(srcOffset + 16); _idxSw2 = src.getDouble(srcOffset + 32); break;
            case 1: _idxSw0 = src.getDouble(srcOffset + 8); _idxSw1 = src.getDouble(srcOffset + 24); _idxSw2 = src.getDouble(srcOffset + 40); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.putDouble(destOffset, _idxSw0);
        dest.putDouble(destOffset + 8, _idxSw1);
        dest.putDouble(destOffset + 16, _idxSw2);
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        Double2x3OpsKernelsAddress.getTranslation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer getTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, _self02);
        dest.putDouble(destOffset + 8, _self12);
        return dest;
    }

    public static double determinant_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2x3OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static double determinant_api(java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _t1 = -(_self01 * _self10);
            return java.lang.Math.fma(src.getDouble(srcOffset), src.getDouble(srcOffset + 24), _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        } else {
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _t1 = -(_self01 * _self10);
            return ((src.getDouble(srcOffset)) * (src.getDouble(srcOffset + 24)) + (_t1)) - ((_self01) * (_self10) + (_t1));
        }
    }

    public static double frobeniusNorm_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Double2x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static double frobeniusNorm_api(java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            double _self02 = src.getDouble(srcOffset + 32);
            double _self12 = src.getDouble(srcOffset + 40);
            return java.lang.Math.sqrt(java.lang.Math.fma(_self12, _self12, java.lang.Math.fma(_self11, _self11, java.lang.Math.fma(_self10, _self10, java.lang.Math.fma(_self02, _self02, java.lang.Math.fma(_self00, _self00, _self01 * _self01))))));
        } else {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            double _self02 = src.getDouble(srcOffset + 32);
            double _self12 = src.getDouble(srcOffset + 40);
            return java.lang.Math.sqrt(((_self12) * (_self12) + (((_self11) * (_self11) + (((_self10) * (_self10) + (((_self02) * (_self02) + (((_self00) * (_self00) + (_self01 * _self01)))))))))));
        }
    }

    public static java.nio.ByteBuffer invert_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_api_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t3 = -(_self01 * _self10);
        double _t4 = -(_self01 * _self12);
        double _t5 = -(_self02 * _self10);
        double _t9 = java.lang.Math.fma(_self00, _self11, _t3) - java.lang.Math.fma(_self01, _self10, _t3);
        if (!(java.lang.Math.abs(_t9) > 2.2250738585072014E-308 && java.lang.Math.abs(_t9) < 4.49423283715579E307)) return Double2x3OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t9_inv = 1.0 / _t9;
        dest.putDouble(destOffset, _self11 * _t9_inv);
        dest.putDouble(destOffset + 8, -(_self10 * _t9_inv));
        dest.putDouble(destOffset + 16, -(_self01 * _t9_inv));
        dest.putDouble(destOffset + 24, _self00 * _t9_inv);
        dest.putDouble(destOffset + 32, (java.lang.Math.fma(_self01, _self12, _t4) - java.lang.Math.fma(_self02, _self11, _t4)) * _t9_inv);
        dest.putDouble(destOffset + 40, (java.lang.Math.fma(_self02, _self10, _t5) - java.lang.Math.fma(_self00, _self12, _t5)) * _t9_inv);
        return dest;
    }

    /** {@code invert_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t3 = -(_self01 * _self10);
        double _t4 = -(_self01 * _self12);
        double _t5 = -(_self02 * _self10);
        double _t9 = ((_self00) * (_self11) + (_t3)) - ((_self01) * (_self10) + (_t3));
        if (!(java.lang.Math.abs(_t9) > 2.2250738585072014E-308 && java.lang.Math.abs(_t9) < 4.49423283715579E307)) return Double2x3OpsKernelsByteBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t9_inv = 1.0 / _t9;
        dest.putDouble(destOffset, _self11 * _t9_inv);
        dest.putDouble(destOffset + 8, -(_self10 * _t9_inv));
        dest.putDouble(destOffset + 16, -(_self01 * _t9_inv));
        dest.putDouble(destOffset + 24, _self00 * _t9_inv);
        dest.putDouble(destOffset + 32, (((_self01) * (_self12) + (_t4)) - ((_self02) * (_self11) + (_t4))) * _t9_inv);
        dest.putDouble(destOffset + 40, (((_self02) * (_self10) + (_t5)) - ((_self00) * (_self12) + (_t5))) * _t9_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    public static java.nio.ByteBuffer invert_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer invert_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_api_fma(dest, destOffset, src, srcOffset);
        return invert_degenerate_api_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code invert_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t8 = _self11 * _t0;
        double _t9 = _self00 * _t1;
        double _t10 = _self01 * _t1;
        double _t11 = _self10 * _t0;
        double _t12 = _self02 * _t1;
        double _t13 = _self12 * _t0;
        double _t17 = -(_t10 * _t11);
        double _t18 = -(_t10 * _t13);
        double _t19 = -(_t12 * _t11);
        double _t22_inv = 1.0 / (java.lang.Math.fma(_t9, _t8, _t17) - java.lang.Math.fma(_t10, _t11, _t17));
        double _sp1 = _t0 * _t22_inv;
        double _sp0 = _t1 * _t22_inv;
        dest.putDouble(destOffset, _t8 * _sp0);
        dest.putDouble(destOffset + 8, -(_t11 * _sp0));
        dest.putDouble(destOffset + 16, -(_t10 * _sp1));
        dest.putDouble(destOffset + 24, _t9 * _sp1);
        dest.putDouble(destOffset + 32, (java.lang.Math.fma(_t10, _t13, _t18) - java.lang.Math.fma(_t12, _t8, _t18)) * _t22_inv);
        dest.putDouble(destOffset + 40, (java.lang.Math.fma(_t12, _t11, _t19) - java.lang.Math.fma(_t9, _t13, _t19)) * _t22_inv);
        return dest;
    }

    /** {@code invert_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invert_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t8 = _self11 * _t0;
        double _t9 = _self00 * _t1;
        double _t10 = _self01 * _t1;
        double _t11 = _self10 * _t0;
        double _t12 = _self02 * _t1;
        double _t13 = _self12 * _t0;
        double _t17 = -(_t10 * _t11);
        double _t18 = -(_t10 * _t13);
        double _t19 = -(_t12 * _t11);
        double _t22_inv = 1.0 / (((_t9) * (_t8) + (_t17)) - ((_t10) * (_t11) + (_t17)));
        double _sp1 = _t0 * _t22_inv;
        double _sp0 = _t1 * _t22_inv;
        dest.putDouble(destOffset, _t8 * _sp0);
        dest.putDouble(destOffset + 8, -(_t11 * _sp0));
        dest.putDouble(destOffset + 16, -(_t10 * _sp1));
        dest.putDouble(destOffset + 24, _t9 * _sp1);
        dest.putDouble(destOffset + 32, (((_t10) * (_t13) + (_t18)) - ((_t12) * (_t8) + (_t18))) * _t22_inv);
        dest.putDouble(destOffset + 40, (((_t12) * (_t11) + (_t19)) - ((_t9) * (_t13) + (_t19))) * _t22_inv);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x3OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        double _other02 = other.getDouble(otherOffset + 32);
        double _other12 = other.getDouble(otherOffset + 40);
        double _t6 = -(_other01 * _other10);
        double _t7 = -(_self01 * _self10);
        double _t24 = java.lang.Math.fma(_other00, _other11, _t6) - java.lang.Math.fma(_other01, _other10, _t6);
        if (!(java.lang.Math.abs(_t24) > 2.2250738585072014E-308 && java.lang.Math.abs(_t24) < 4.49423283715579E307)) return Double2x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_fma_s2dc24fa2_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12, -(_self02 * _self10), -(_self01 * _self12), -(_other01 * _other12), -(_other02 * _other10), _t24, 1.0 / _t24, java.lang.Math.fma(_self00, _self11, _t7) - java.lang.Math.fma(_self01, _self10, _t7));
    }

    /** Piece 2 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma_s2dc24fa2_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12, double _t8, double _t9, double _t10, double _t11, double _t24, double _t24_inv, double _t25) {
        if (!(java.lang.Math.abs(_t25) > 2.2250738585072014E-308 && java.lang.Math.abs(_t25) < 4.49423283715579E307)) return Double2x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t25_inv = 1.0 / _t25;
        double _t36 = _other11 / _t24;
        double _t37 = _self11 / _t25;
        double _t38 = _self00 / _t25;
        double _t41 = _other00 / _t24;
        double _t42 = -(_other01 * _t24_inv);
        double _t43 = -(_self10 * _t25_inv);
        double _t44 = -(_self01 * _t25_inv);
        double _t45 = -(_other10 * _t24_inv);
        double _t52 = _t36 * _t37;
        double _t54 = _t44 * _t36;
        double _t55 = _t43 * _t41;
        dest.putDouble(destOffset, java.lang.Math.fma(_t42, _t43, _t52) + java.lang.Math.fma(_t36, _t37, -_t52));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t45, _t37, _t55) + java.lang.Math.fma(_t43, _t41, -_t55));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t42, _t38, _t54) + java.lang.Math.fma(_t44, _t36, -_t54));
        return invertProduct_api_fma_s2dc24fa2_2(dest, destOffset, _other00, _other10, _other01, _other11, _other02, _other12, _t10, _t11, _t24_inv, _t36, _t38, _t41, _t42, _t44, _t45, -((java.lang.Math.fma(_self00, _self12, _t8) - java.lang.Math.fma(_self02, _self10, _t8)) * _t25_inv), -((java.lang.Math.fma(_self02, _self11, _t9) - java.lang.Math.fma(_self01, _self12, _t9)) * _t25_inv), _t41 * _t38);
    }

    /** Piece 3 of {@code invertProduct_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_fma_s2dc24fa2_2(java.nio.ByteBuffer dest, int destOffset, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12, double _t10, double _t11, double _t24_inv, double _t36, double _t38, double _t41, double _t42, double _t44, double _t45, double _t50, double _t51, double _t53) {
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t45, _t44, _t53) + java.lang.Math.fma(_t41, _t38, -_t53));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t42, _t50, java.lang.Math.fma(_t51, _t36, -((java.lang.Math.fma(_other02, _other11, _t10) - java.lang.Math.fma(_other01, _other12, _t10)) * _t24_inv))));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t45, _t51, java.lang.Math.fma(_t50, _t41, -((java.lang.Math.fma(_other00, _other12, _t11) - java.lang.Math.fma(_other02, _other10, _t11)) * _t24_inv))));
        return dest;
    }

    /** {@code invertProduct_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        double _other02 = other.getDouble(otherOffset + 32);
        double _other12 = other.getDouble(otherOffset + 40);
        double _t6 = -(_other01 * _other10);
        double _t7 = -(_self01 * _self10);
        double _t24 = ((_other00) * (_other11) + (_t6)) - ((_other01) * (_other10) + (_t6));
        if (!(java.lang.Math.abs(_t24) > 2.2250738585072014E-308 && java.lang.Math.abs(_t24) < 4.49423283715579E307)) return Double2x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_api_mulAdd_s234a47a5_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self01, _self11, _self02, _self12, _other00, _other10, _other01, _other11, _other02, _other12, -(_self02 * _self10), -(_self01 * _self12), -(_other01 * _other12), -(_other02 * _other10), _t24, 1.0 / _t24, ((_self00) * (_self11) + (_t7)) - ((_self01) * (_self10) + (_t7)));
    }

    /** Piece 2 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd_s234a47a5_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12, double _t8, double _t9, double _t10, double _t11, double _t24, double _t24_inv, double _t25) {
        if (!(java.lang.Math.abs(_t25) > 2.2250738585072014E-308 && java.lang.Math.abs(_t25) < 4.49423283715579E307)) return Double2x3OpsKernelsByteBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t25_inv = 1.0 / _t25;
        double _t36 = _other11 / _t24;
        double _t37 = _self11 / _t25;
        double _t38 = _self00 / _t25;
        double _t41 = _other00 / _t24;
        double _t42 = -(_other01 * _t24_inv);
        double _t43 = -(_self10 * _t25_inv);
        double _t44 = -(_self01 * _t25_inv);
        double _t45 = -(_other10 * _t24_inv);
        double _t52 = _t36 * _t37;
        double _t53 = _t41 * _t38;
        double _t54 = _t44 * _t36;
        double _t55 = _t43 * _t41;
        dest.putDouble(destOffset, ((_t42) * (_t43) + (_t52)) + ((_t36) * (_t37) - (_t52)));
        dest.putDouble(destOffset + 8, ((_t45) * (_t37) + (_t55)) + ((_t43) * (_t41) - (_t55)));
        dest.putDouble(destOffset + 16, ((_t42) * (_t38) + (_t54)) + ((_t44) * (_t36) - (_t54)));
        dest.putDouble(destOffset + 24, ((_t45) * (_t44) + (_t53)) + ((_t41) * (_t38) - (_t53)));
        return invertProduct_api_mulAdd_s234a47a5_2(dest, destOffset, _other00, _other10, _other01, _other11, _other02, _other12, _t10, _t11, _t24_inv, _t36, _t41, _t42, _t45, -((((_self00) * (_self12) + (_t8)) - ((_self02) * (_self10) + (_t8))) * _t25_inv), -((((_self02) * (_self11) + (_t9)) - ((_self01) * (_self12) + (_t9))) * _t25_inv));
    }

    /** Piece 3 of {@code invertProduct_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_api_mulAdd_s234a47a5_2(java.nio.ByteBuffer dest, int destOffset, double _other00, double _other10, double _other01, double _other11, double _other02, double _other12, double _t10, double _t11, double _t24_inv, double _t36, double _t41, double _t42, double _t45, double _t50, double _t51) {
        dest.putDouble(destOffset + 32, ((_t42) * (_t50) + (((_t51) * (_t36) - ((((_other02) * (_other11) + (_t10)) - ((_other01) * (_other12) + (_t10))) * _t24_inv)))));
        dest.putDouble(destOffset + 40, ((_t45) * (_t51) + (((_t50) * (_t41) - ((((_other00) * (_other12) + (_t11)) - ((_other02) * (_other10) + (_t11))) * _t24_inv)))));
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer invertProduct_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        double _other02 = other.getDouble(otherOffset + 32);
        double _other12 = other.getDouble(otherOffset + 40);
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        double _t16 = _other01 * _t0;
        double _t19 = _other10 * _t1;
        double _t20 = _self10 * _t2;
        double _t23 = _self01 * _t3;
        double _t24 = _self12 * _t2;
        double _t25 = _self02 * _t3;
        double _t27 = _other12 * _t1;
        return invertProduct_degenerate_api_fma_sdbea5e19_1(dest, destOffset, _t0, _t1, _t2, _t3, _t16, _other00 * _t0, _other11 * _t1, _t19, _t20, _self00 * _t3, _self11 * _t2, _t23, _t24, _t25, _other02 * _t0, _t27, -(_t16 * _t19), -(_t23 * _t20), -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27));
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_fma_sdbea5e19_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _t2, double _t3, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t27, double _t42, double _t43, double _t44, double _t45, double _t46) {
        double _t56 = java.lang.Math.fma(_t17, _t18, _t42) - java.lang.Math.fma(_t16, _t19, _t42);
        double _t56_inv = 1.0 / _t56;
        double _t57 = java.lang.Math.fma(_t21, _t22, _t43) - java.lang.Math.fma(_t23, _t20, _t43);
        double _t57_inv = 1.0 / _t57;
        double _t70 = _t18 * _t0 / _t56;
        double _t71 = _t22 * _t3 / _t57;
        double _t72 = _t21 * _t2 / _t57;
        double _t75 = _t17 * _t1 / _t56;
        double _t76 = -(_t16 * _t1 * _t56_inv);
        double _t77 = -(_t20 * _t3 * _t57_inv);
        double _t78 = -(_t23 * _t2 * _t57_inv);
        double _t79 = -(_t19 * _t0 * _t56_inv);
        double _t86 = _t70 * _t71;
        double _t89 = _t77 * _t75;
        dest.putDouble(destOffset, java.lang.Math.fma(_t76, _t77, _t86) + java.lang.Math.fma(_t70, _t71, -_t86));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_t79, _t71, _t89) + java.lang.Math.fma(_t77, _t75, -_t89));
        return invertProduct_degenerate_api_fma_sdbea5e19_2(dest, destOffset, _t16, _t17, _t18, _t19, _t26, _t27, _t46, -(_t26 * _t19), _t56_inv, _t70, _t72, _t75, _t76, _t78, _t79, -((java.lang.Math.fma(_t21, _t24, _t44) - java.lang.Math.fma(_t25, _t20, _t44)) * _t57_inv), -((java.lang.Math.fma(_t25, _t22, _t45) - java.lang.Math.fma(_t23, _t24, _t45)) * _t57_inv), _t75 * _t72, _t78 * _t70);
    }

    /** Piece 3 of {@code invertProduct_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_fma_sdbea5e19_2(java.nio.ByteBuffer dest, int destOffset, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t46, double _t47, double _t56_inv, double _t70, double _t72, double _t75, double _t76, double _t78, double _t79, double _t84, double _t85, double _t87, double _t88) {
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_t76, _t72, _t88) + java.lang.Math.fma(_t78, _t70, -_t88));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_t79, _t78, _t87) + java.lang.Math.fma(_t75, _t72, -_t87));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_t84, _t76, java.lang.Math.fma(_t85, _t70, -((java.lang.Math.fma(_t26, _t18, _t46) - java.lang.Math.fma(_t16, _t27, _t46)) * _t56_inv))));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_t84, _t75, java.lang.Math.fma(_t85, _t79, -((java.lang.Math.fma(_t17, _t27, _t47) - java.lang.Math.fma(_t26, _t19, _t47)) * _t56_inv))));
        return dest;
    }

    /** {@code invertProduct_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        double _other02 = other.getDouble(otherOffset + 32);
        double _other12 = other.getDouble(otherOffset + 40);
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        double _t16 = _other01 * _t0;
        double _t19 = _other10 * _t1;
        double _t20 = _self10 * _t2;
        double _t23 = _self01 * _t3;
        double _t24 = _self12 * _t2;
        double _t25 = _self02 * _t3;
        double _t27 = _other12 * _t1;
        return invertProduct_degenerate_api_mulAdd_s913a584c_1(dest, destOffset, _t0, _t1, _t2, _t3, _t16, _other00 * _t0, _other11 * _t1, _t19, _t20, _self00 * _t3, _self11 * _t2, _t23, _t24, _t25, _other02 * _t0, _t27, -(_t16 * _t19), -(_t23 * _t20), -(_t25 * _t20), -(_t23 * _t24), -(_t16 * _t27));
    }

    /** Piece 2 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd_s913a584c_1(java.nio.ByteBuffer dest, int destOffset, double _t0, double _t1, double _t2, double _t3, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t27, double _t42, double _t43, double _t44, double _t45, double _t46) {
        double _t56 = ((_t17) * (_t18) + (_t42)) - ((_t16) * (_t19) + (_t42));
        double _t56_inv = 1.0 / _t56;
        double _t57 = ((_t21) * (_t22) + (_t43)) - ((_t23) * (_t20) + (_t43));
        double _t57_inv = 1.0 / _t57;
        double _t70 = _t18 * _t0 / _t56;
        double _t71 = _t22 * _t3 / _t57;
        double _t72 = _t21 * _t2 / _t57;
        double _t75 = _t17 * _t1 / _t56;
        double _t76 = -(_t16 * _t1 * _t56_inv);
        double _t77 = -(_t20 * _t3 * _t57_inv);
        double _t78 = -(_t23 * _t2 * _t57_inv);
        double _t79 = -(_t19 * _t0 * _t56_inv);
        double _t86 = _t70 * _t71;
        double _t89 = _t77 * _t75;
        dest.putDouble(destOffset, ((_t76) * (_t77) + (_t86)) + ((_t70) * (_t71) - (_t86)));
        dest.putDouble(destOffset + 8, ((_t79) * (_t71) + (_t89)) + ((_t77) * (_t75) - (_t89)));
        return invertProduct_degenerate_api_mulAdd_s913a584c_2(dest, destOffset, _t16, _t17, _t18, _t19, _t26, _t27, _t46, -(_t26 * _t19), _t56_inv, _t70, _t72, _t75, _t76, _t78, _t79, -((((_t21) * (_t24) + (_t44)) - ((_t25) * (_t20) + (_t44))) * _t57_inv), -((((_t25) * (_t22) + (_t45)) - ((_t23) * (_t24) + (_t45))) * _t57_inv), _t75 * _t72, _t78 * _t70);
    }

    /** Piece 3 of {@code invertProduct_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer invertProduct_degenerate_api_mulAdd_s913a584c_2(java.nio.ByteBuffer dest, int destOffset, double _t16, double _t17, double _t18, double _t19, double _t26, double _t27, double _t46, double _t47, double _t56_inv, double _t70, double _t72, double _t75, double _t76, double _t78, double _t79, double _t84, double _t85, double _t87, double _t88) {
        dest.putDouble(destOffset + 16, ((_t76) * (_t72) + (_t88)) + ((_t78) * (_t70) - (_t88)));
        dest.putDouble(destOffset + 24, ((_t79) * (_t78) + (_t87)) + ((_t75) * (_t72) - (_t87)));
        dest.putDouble(destOffset + 32, ((_t84) * (_t76) + (((_t85) * (_t70) - ((((_t26) * (_t18) + (_t46)) - ((_t16) * (_t27) + (_t46))) * _t56_inv)))));
        dest.putDouble(destOffset + 40, ((_t84) * (_t75) + (((_t85) * (_t79) - ((((_t17) * (_t27) + (_t47)) - ((_t26) * (_t19) + (_t47))) * _t56_inv)))));
        return dest;
    }

    public static java.nio.ByteBuffer transpose_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer transpose_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self01);
        dest.putDouble(destOffset + 16, _self02);
        dest.putDouble(destOffset + 24, _self10);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eother0 = other.getDouble(otherOffset + _lo * 8);
            double _eother1 = other.getDouble(otherOffset + (_lo + 1) * 8);
            dest.putDouble(destOffset + _lo * 8, _eother0 + _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eother1 + _eself1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            dest.putDouble(destOffset + _lo * 8, scalar * _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, scalar * _eself1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            dest.putDouble(destOffset + _lo * 8, -_eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, -_eself1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            double _eother0 = other.getDouble(otherOffset + _lo * 8);
            double _eother1 = other.getDouble(otherOffset + (_lo + 1) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0 - _eother0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1 - _eother1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _ev0 = v.getDouble(vOffset + _lo * 8);
            double _ev1 = v.getDouble(vOffset + (_lo + 1) * 8);
            dest.putDouble(destOffset + _lo * 8, _ev0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _ev1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double2x3OpsKernelsAddress.setMat2x2_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        double _m00 = m.getDouble(mOffset);
        double _m10 = m.getDouble(mOffset + 8);
        double _m01 = m.getDouble(mOffset + 16);
        double _m11 = m.getDouble(mOffset + 24);
        dest.putDouble(destOffset, _m00);
        dest.putDouble(destOffset + 8, _m10);
        dest.putDouble(destOffset + 16, _m01);
        dest.putDouble(destOffset + 24, _m11);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset;
        Double2x3OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.ByteBuffer setMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _lom = _l * 3;
            double _em0 = m.getDouble(mOffset + _lom * 8);
            double _em1 = m.getDouble(mOffset + (_lom + 1) * 8);
            dest.putDouble(destOffset + _lo * 8, _em0);
            dest.putDouble(destOffset + (_lo + 1) * 8, _em1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double tX, double tY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double tX, double tY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self01);
        dest.putDouble(destOffset + 24, _self11);
        dest.putDouble(destOffset + 32, tX);
        dest.putDouble(destOffset + 40, tY);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Double2x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer withTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        double _tx = t.getDouble(tOffset);
        double _ty = t.getDouble(tOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self01);
        dest.putDouble(destOffset + 24, _self11);
        dest.putDouble(destOffset + 32, _tx);
        dest.putDouble(destOffset + 40, _ty);
        return dest;
    }

    public static java.nio.ByteBuffer to2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.to2x2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self01);
        dest.putDouble(destOffset + 24, _self11);
        return dest;
    }

    public static java.nio.ByteBuffer to3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer to3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _self01);
        dest.putDouble(destOffset + 32, _self11);
        dest.putDouble(destOffset + 40, 0.0);
        dest.putDouble(destOffset + 48, _self02);
        dest.putDouble(destOffset + 56, _self12);
        dest.putDouble(destOffset + 64, 1.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2x3OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeIdentity_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 1.0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                double _eself0 = src.getDouble(srcOffset + _lo * 8);
                double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
                double _eother0 = other.getDouble(otherOffset + _lo * 8);
                double _eother1 = other.getDouble(otherOffset + (_lo + 1) * 8);
                dest.putDouble(destOffset + _lo * 8, java.lang.Math.fma(t, _eother0 - _eself0, _eself0));
                dest.putDouble(destOffset + (_lo + 1) * 8, java.lang.Math.fma(t, _eother1 - _eself1, _eself1));
            }
            return dest;
        } else {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                double _eself0 = src.getDouble(srcOffset + _lo * 8);
                double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
                double _eother0 = other.getDouble(otherOffset + _lo * 8);
                double _eother1 = other.getDouble(otherOffset + (_lo + 1) * 8);
                dest.putDouble(destOffset + _lo * 8, ((t) * (_eother0 - _eself0) + (_eself0)));
                dest.putDouble(destOffset + (_lo + 1) * 8, ((t) * (_eother1 - _eself1) + (_eself1)));
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double2x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        double _right02 = right.getDouble(rightOffset + 32);
        double _right12 = right.getDouble(rightOffset + 40);
        dest.putDouble(destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_right02, _self00, java.lang.Math.fma(_right12, _self01, _self02)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_right02, _self10, java.lang.Math.fma(_right12, _self11, _self12)));
        return dest;
    }

    /** {@code mul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        double _right02 = right.getDouble(rightOffset + 32);
        double _right12 = right.getDouble(rightOffset + 40);
        dest.putDouble(destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.putDouble(destOffset + 8, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.putDouble(destOffset + 16, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.putDouble(destOffset + 24, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.putDouble(destOffset + 32, ((_right02) * (_self00) + (((_right12) * (_self01) + (_self02)))));
        dest.putDouble(destOffset + 40, ((_right02) * (_self10) + (((_right12) * (_self11) + (_self12)))));
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double2x3OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat2x2_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        dest.putDouble(destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        dest.putDouble(destOffset + 32, _self02);
        dest.putDouble(destOffset + 40, _self12);
        return dest;
    }

    /** {@code mulMat2x2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulMat2x2_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _right00 = right.getDouble(rightOffset);
        double _right10 = right.getDouble(rightOffset + 8);
        double _right01 = right.getDouble(rightOffset + 16);
        double _right11 = right.getDouble(rightOffset + 24);
        dest.putDouble(destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.putDouble(destOffset + 8, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.putDouble(destOffset + 16, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.putDouble(destOffset + 24, ((_right01) * (_self10) + (_right11 * _self11)));
        dest.putDouble(destOffset + 32, _self02);
        dest.putDouble(destOffset + 40, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset;
        Double2x3OpsKernelsAddress.mulMat3x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mulMat3x3_api_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = right.getDouble(rightOffset + _lo * 8);
            double _eright1 = right.getDouble(rightOffset + (_lo + 1) * 8);
            double _eright2 = right.getDouble(rightOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, java.lang.Math.fma(_eright2, _self02, java.lang.Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.putDouble(destOffset + (_lo + 1) * 8, java.lang.Math.fma(_eright2, _self12, java.lang.Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.putDouble(destOffset + (_lo + 2) * 8, _eright2);
        }
        return dest;
    }

    /** {@code mulMat3x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer mulMat3x3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = right.getDouble(rightOffset + _lo * 8);
            double _eright1 = right.getDouble(rightOffset + (_lo + 1) * 8);
            double _eright2 = right.getDouble(rightOffset + (_lo + 2) * 8);
            dest.putDouble(destOffset + _lo * 8, ((_eright2) * (_self02) + (((_eright0) * (_self00) + (_eright1 * _self01)))));
            dest.putDouble(destOffset + (_lo + 1) * 8, ((_eright2) * (_self12) + (((_eright0) * (_self10) + (_eright1 * _self11)))));
            dest.putDouble(destOffset + (_lo + 2) * 8, _eright2);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        double _other02 = other.getDouble(otherOffset + 32);
        double _other12 = other.getDouble(otherOffset + 40);
        dest.putDouble(destOffset, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_other00, _self02, java.lang.Math.fma(_other01, _self12, _other02)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_other10, _self02, java.lang.Math.fma(_other11, _self12, _other12)));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other01 = other.getDouble(otherOffset + 16);
        double _other11 = other.getDouble(otherOffset + 24);
        double _other02 = other.getDouble(otherOffset + 32);
        double _other12 = other.getDouble(otherOffset + 40);
        dest.putDouble(destOffset, ((_other00) * (_self00) + (_other01 * _self10)));
        dest.putDouble(destOffset + 8, ((_other10) * (_self00) + (_other11 * _self10)));
        dest.putDouble(destOffset + 16, ((_other00) * (_self01) + (_other01 * _self11)));
        dest.putDouble(destOffset + 24, ((_other10) * (_self01) + (_other11 * _self11)));
        dest.putDouble(destOffset + 32, ((_other00) * (_self02) + (((_other01) * (_self12) + (_other02)))));
        dest.putDouble(destOffset + 40, ((_other10) * (_self02) + (((_other11) * (_self12) + (_other12)))));
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x3OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat2x2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _other00 = other.getDouble(otherOffset);
            double _other10 = other.getDouble(otherOffset + 8);
            double _other01 = other.getDouble(otherOffset + 16);
            double _other11 = other.getDouble(otherOffset + 24);
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                double _eself0 = src.getDouble(srcOffset + _lo * 8);
                double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
                dest.putDouble(destOffset + _lo * 8, java.lang.Math.fma(_other00, _eself0, _other01 * _eself1));
                dest.putDouble(destOffset + (_lo + 1) * 8, java.lang.Math.fma(_other10, _eself0, _other11 * _eself1));
            }
            return dest;
        } else {
            double _other00 = other.getDouble(otherOffset);
            double _other10 = other.getDouble(otherOffset + 8);
            double _other01 = other.getDouble(otherOffset + 16);
            double _other11 = other.getDouble(otherOffset + 24);
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                double _eself0 = src.getDouble(srcOffset + _lo * 8);
                double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
                dest.putDouble(destOffset + _lo * 8, ((_other00) * (_eself0) + (_other01 * _eself1)));
                dest.putDouble(destOffset + (_lo + 1) * 8, ((_other10) * (_eself0) + (_other11 * _eself1)));
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer preMulMat3x3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x3OpsKernelsAddress.preMulMat3x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulMat3x3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulMat3x3_api_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code preMulMat3x3_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMulMat3x3_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other01 = other.getDouble(otherOffset + 24);
        double _other11 = other.getDouble(otherOffset + 32);
        double _other21 = other.getDouble(otherOffset + 40);
        double _other02 = other.getDouble(otherOffset + 48);
        double _other12 = other.getDouble(otherOffset + 56);
        double _other22 = other.getDouble(otherOffset + 64);
        dest.putDouble(destOffset, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_other20, _self00, _other21 * _self10));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        return preMulMat3x3_api_fma_s8bc2d90_1(dest, destOffset, _self01, _self11, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMulMat3x3_api_fma_s8bc2d90_1(java.nio.ByteBuffer dest, int destOffset, double _self01, double _self11, double _self02, double _self12, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_other20, _self01, _other21 * _self11));
        dest.putDouble(destOffset + 48, java.lang.Math.fma(_other00, _self02, java.lang.Math.fma(_other01, _self12, _other02)));
        dest.putDouble(destOffset + 56, java.lang.Math.fma(_other10, _self02, java.lang.Math.fma(_other11, _self12, _other12)));
        dest.putDouble(destOffset + 64, java.lang.Math.fma(_other20, _self02, java.lang.Math.fma(_other21, _self12, _other22)));
        return dest;
    }

    /** {@code preMulMat3x3_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMulMat3x3_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _other00 = other.getDouble(otherOffset);
        double _other10 = other.getDouble(otherOffset + 8);
        double _other20 = other.getDouble(otherOffset + 16);
        double _other01 = other.getDouble(otherOffset + 24);
        double _other11 = other.getDouble(otherOffset + 32);
        double _other21 = other.getDouble(otherOffset + 40);
        double _other02 = other.getDouble(otherOffset + 48);
        double _other12 = other.getDouble(otherOffset + 56);
        double _other22 = other.getDouble(otherOffset + 64);
        dest.putDouble(destOffset, ((_other00) * (_self00) + (_other01 * _self10)));
        dest.putDouble(destOffset + 8, ((_other10) * (_self00) + (_other11 * _self10)));
        dest.putDouble(destOffset + 16, ((_other20) * (_self00) + (_other21 * _self10)));
        dest.putDouble(destOffset + 24, ((_other00) * (_self01) + (_other01 * _self11)));
        dest.putDouble(destOffset + 32, ((_other10) * (_self01) + (_other11 * _self11)));
        dest.putDouble(destOffset + 40, ((_other20) * (_self01) + (_other21 * _self11)));
        return preMulMat3x3_api_mulAdd_s29efad73_1(dest, destOffset, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMulMat3x3_api_mulAdd_s29efad73_1(java.nio.ByteBuffer dest, int destOffset, double _self02, double _self12, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
        dest.putDouble(destOffset + 48, ((_other00) * (_self02) + (((_other01) * (_self12) + (_other02)))));
        dest.putDouble(destOffset + 56, ((_other10) * (_self02) + (((_other11) * (_self12) + (_other12)))));
        dest.putDouble(destOffset + 64, ((_other20) * (_self02) + (((_other21) * (_self12) + (_other22)))));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Double2x3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                double _eself0 = src.getDouble(srcOffset + _lo * 8);
                double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
                double _eother0 = other.getDouble(otherOffset + _lo * 8);
                double _eother1 = other.getDouble(otherOffset + (_lo + 1) * 8);
                dest.putDouble(destOffset + _lo * 8, java.lang.Math.fma(weight, _eother0, _eself0));
                dest.putDouble(destOffset + (_lo + 1) * 8, java.lang.Math.fma(weight, _eother1, _eself1));
            }
            return dest;
        } else {
            for (int _l = 0; _l < 3; _l++) {
                int _lo = _l * 2;
                double _eself0 = src.getDouble(srcOffset + _lo * 8);
                double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
                double _eother0 = other.getDouble(otherOffset + _lo * 8);
                double _eother1 = other.getDouble(otherOffset + (_lo + 1) * 8);
                dest.putDouble(destOffset + _lo * 8, ((weight) * (_eother0) + (_eself0)));
                dest.putDouble(destOffset + (_lo + 1) * 8, ((weight) * (_eother1) + (_eself1)));
            }
            return dest;
        }
    }

    public static java.nio.ByteBuffer makeRotation_unsafe(java.nio.ByteBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer makeRotation_api(java.nio.ByteBuffer dest, int destOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, _t0);
        dest.putDouble(destOffset + 8, _t1);
        dest.putDouble(destOffset + 16, -_t1);
        dest.putDouble(destOffset + 24, _t0);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        dest.putDouble(destOffset, vX);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, vY);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset, _vx);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _vy);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_unsafe(java.nio.ByteBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeScaling_api(java.nio.ByteBuffer dest, int destOffset, double s) {
        dest.putDouble(destOffset, s);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, s);
        dest.putDouble(destOffset + 32, 0.0);
        dest.putDouble(destOffset + 40, 0.0);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 1.0);
        dest.putDouble(destOffset + 32, vX);
        dest.putDouble(destOffset + 40, vY);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeTranslation_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        dest.putDouble(destOffset, 1.0);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, 1.0);
        dest.putDouble(destOffset + 32, _vx);
        dest.putDouble(destOffset + 40, _vy);
        return dest;
    }

    public static java.nio.ByteBuffer makeView_unsafe(java.nio.ByteBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Double2x3OpsKernelsAddress.makeView_unsafe(_destBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.ByteBuffer makeView_api(java.nio.ByteBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dest.putDouble(destOffset, _t0_inv + _t0_inv);
        dest.putDouble(destOffset + 8, 0.0);
        dest.putDouble(destOffset + 16, 0.0);
        dest.putDouble(destOffset + 24, _t1_inv + _t1_inv);
        dest.putDouble(destOffset + 32, -(left + right) * _t0_inv);
        dest.putDouble(destOffset + 40, -(bottom + top) * _t1_inv);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self02, _t1, _self12 * _t0));
        return dest;
    }

    /** {@code preRotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.putDouble(destOffset + 8, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.putDouble(destOffset + 16, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.putDouble(destOffset + 24, ((_self01) * (_t1) + (_self11 * _t0)));
        dest.putDouble(destOffset + 32, ((_self02) * (_t0) - (_self12 * _t1)));
        dest.putDouble(destOffset + 40, ((_self02) * (_t1) + (_self12 * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t6 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        dest.putDouble(destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(pivotX, _t6, pivotY * _t1) + java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self02, _t1, _self12 * _t0) + java.lang.Math.fma(pivotY, _t6, -(pivotX * _t1)));
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t6 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        dest.putDouble(destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.putDouble(destOffset + 8, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.putDouble(destOffset + 16, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.putDouble(destOffset + 24, ((_self01) * (_t1) + (_self11 * _t0)));
        dest.putDouble(destOffset + 32, ((pivotX) * (_t6) + (pivotY * _t1)) + ((_self02) * (_t0) - (_self12 * _t1)));
        dest.putDouble(destOffset + 40, ((_self02) * (_t1) + (_self12 * _t0)) + ((pivotY) * (_t6) - (pivotX * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double2x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer preRotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preRotateAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t6 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        dest.putDouble(destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_pivotx, _t6, _pivoty * _t1) + java.lang.Math.fma(_self02, _t0, -(_self12 * _t1)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self02, _t1, _self12 * _t0) + java.lang.Math.fma(_pivoty, _t6, -(_pivotx * _t1)));
        return dest;
    }

    /** {@code preRotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preRotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t6 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        dest.putDouble(destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
        dest.putDouble(destOffset + 8, ((_self00) * (_t1) + (_self10 * _t0)));
        dest.putDouble(destOffset + 16, ((_self01) * (_t0) - (_self11 * _t1)));
        dest.putDouble(destOffset + 24, ((_self01) * (_t1) + (_self11 * _t0)));
        dest.putDouble(destOffset + 32, ((_pivotx) * (_t6) + (_pivoty * _t1)) + ((_self02) * (_t0) - (_self12 * _t1)));
        dest.putDouble(destOffset + 40, ((_self02) * (_t1) + (_self12 * _t0)) + ((_pivoty) * (_t6) - (_pivotx * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0 * vX);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1 * vY);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            dest.putDouble(destOffset + _lo * 8, _eself0 * _vx);
            dest.putDouble(destOffset + (_lo + 1) * 8, _eself1 * _vy);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src.getDouble(srcOffset + _lo * 8);
            double _eself1 = src.getDouble(srcOffset + (_lo + 1) * 8);
            dest.putDouble(destOffset + _lo * 8, s * _eself0);
            dest.putDouble(destOffset + (_lo + 1) * 8, s * _eself1);
        }
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = 1.0 - s;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self01);
        dest.putDouble(destOffset + 24, s * _self11);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(s, _self02, pivotX * _t0));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(s, _self12, pivotY * _t0));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = 1.0 - s;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self01);
        dest.putDouble(destOffset + 24, s * _self11);
        dest.putDouble(destOffset + 32, ((s) * (_self02) + (pivotX * _t0)));
        dest.putDouble(destOffset + 40, ((s) * (_self12) + (pivotY * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = 1.0 - s;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self01);
        dest.putDouble(destOffset + 24, s * _self11);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(s, _self02, _pivotx * _t0));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(s, _self12, _pivoty * _t0));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = 1.0 - s;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self01);
        dest.putDouble(destOffset + 24, s * _self11);
        dest.putDouble(destOffset + 32, ((s) * (_self02) + (_pivotx * _t0)));
        dest.putDouble(destOffset + 40, ((s) * (_self12) + (_pivoty * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, sX * _self00);
        dest.putDouble(destOffset + 8, sY * _self10);
        dest.putDouble(destOffset + 16, sX * _self01);
        dest.putDouble(destOffset + 24, sY * _self11);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(pivotX, 1.0 - sX, sX * _self02));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(pivotY, 1.0 - sY, sY * _self12));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, sX * _self00);
        dest.putDouble(destOffset + 8, sY * _self10);
        dest.putDouble(destOffset + 16, sX * _self01);
        dest.putDouble(destOffset + 24, sY * _self11);
        dest.putDouble(destOffset + 32, ((pivotX) * (1.0 - sX) + (sX * _self02)));
        dest.putDouble(destOffset + 40, ((pivotY) * (1.0 - sY) + (sY * _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer preScaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preScaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        double _sx = s.getDouble(sOffset);
        double _sy = s.getDouble(sOffset + 8);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, _sx * _self00);
        dest.putDouble(destOffset + 8, _sy * _self10);
        dest.putDouble(destOffset + 16, _sx * _self01);
        dest.putDouble(destOffset + 24, _sy * _self11);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_pivotx, 1.0 - _sx, _sx * _self02));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_pivoty, 1.0 - _sy, _sy * _self12));
        return dest;
    }

    /** {@code preScaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preScaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _sx = s.getDouble(sOffset);
        double _sy = s.getDouble(sOffset + 8);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, _sx * _self00);
        dest.putDouble(destOffset + 8, _sy * _self10);
        dest.putDouble(destOffset + 16, _sx * _self01);
        dest.putDouble(destOffset + 24, _sy * _self11);
        dest.putDouble(destOffset + 32, ((_pivotx) * (1.0 - _sx) + (_sx * _self02)));
        dest.putDouble(destOffset + 40, ((_pivoty) * (1.0 - _sy) + (_sy * _self12)));
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self01);
        dest.putDouble(destOffset + 24, _self11);
        dest.putDouble(destOffset + 32, _self02 + vX);
        dest.putDouble(destOffset + 40, _self12 + vY);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer preTranslate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self01);
        dest.putDouble(destOffset + 24, _self11);
        dest.putDouble(destOffset + 32, _self02 + _vx);
        dest.putDouble(destOffset + 40, _self12 + _vy);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, angle);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.putDouble(destOffset + 32, _self02);
        dest.putDouble(destOffset + 40, _self12);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.putDouble(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.putDouble(destOffset + 8, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.putDouble(destOffset + 16, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.putDouble(destOffset + 24, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.putDouble(destOffset + 32, _self02);
        dest.putDouble(destOffset + 40, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = java.lang.Math.fma(pivotX, _t9, pivotY * _t1);
        double _t11 = java.lang.Math.fma(pivotY, _t9, -(pivotX * _t1));
        dest.putDouble(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = ((pivotX) * (_t9) + (pivotY * _t1));
        double _t11 = ((pivotY) * (_t9) - (pivotX * _t1));
        dest.putDouble(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.putDouble(destOffset + 8, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.putDouble(destOffset + 16, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.putDouble(destOffset + 24, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.putDouble(destOffset + 32, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.putDouble(destOffset + 40, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double2x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = java.lang.Math.fma(_pivotx, _t9, _pivoty * _t1);
        double _t11 = java.lang.Math.fma(_pivoty, _t9, -(_pivotx * _t1));
        dest.putDouble(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
        dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
        dest.putDouble(destOffset + 16, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
        dest.putDouble(destOffset + 24, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self00, _t10, java.lang.Math.fma(_self01, _t11, _self02)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self10, _t10, java.lang.Math.fma(_self11, _t11, _self12)));
        return dest;
    }

    /** {@code rotateAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        double _pivotx = pivot.getDouble(pivotOffset);
        double _pivoty = pivot.getDouble(pivotOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t9 = _t0 > 0.0 ? _t1 * _t1 / (1.0 + _t0) : 1.0 - _t0;
        double _t10 = ((_pivotx) * (_t9) + (_pivoty * _t1));
        double _t11 = ((_pivoty) * (_t9) - (_pivotx * _t1));
        dest.putDouble(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
        dest.putDouble(destOffset + 8, ((_self10) * (_t0) + (_self11 * _t1)));
        dest.putDouble(destOffset + 16, ((_self01) * (_t0) - (_self00 * _t1)));
        dest.putDouble(destOffset + 24, ((_self11) * (_t0) - (_self10 * _t1)));
        dest.putDouble(destOffset + 32, ((_self00) * (_t10) + (((_self01) * (_t11) + (_self02)))));
        dest.putDouble(destOffset + 40, ((_self10) * (_t10) + (((_self11) * (_t11) + (_self12)))));
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, _self00 * vX);
        dest.putDouble(destOffset + 8, _self10 * vX);
        dest.putDouble(destOffset + 16, _self01 * vY);
        dest.putDouble(destOffset + 24, _self11 * vY);
        dest.putDouble(destOffset + 32, _self02);
        dest.putDouble(destOffset + 40, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, _self00 * _vx);
        dest.putDouble(destOffset + 8, _self10 * _vx);
        dest.putDouble(destOffset + 16, _self01 * _vy);
        dest.putDouble(destOffset + 24, _self11 * _vy);
        dest.putDouble(destOffset + 32, _self02);
        dest.putDouble(destOffset + 40, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer scale_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scale_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self01);
        dest.putDouble(destOffset + 24, s * _self11);
        dest.putDouble(destOffset + 32, _self02);
        dest.putDouble(destOffset + 40, _self12);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self01);
        dest.putDouble(destOffset + 24, s * _self11);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self01);
        dest.putDouble(destOffset + 24, s * _self11);
        dest.putDouble(destOffset + 32, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.putDouble(destOffset + 40, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = 1.0 - s;
        double _t1 = pivot.getDouble(pivotOffset) * _t0;
        double _t2 = pivot.getDouble(pivotOffset + 8) * _t0;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self01);
        dest.putDouble(destOffset + 24, s * _self11);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self00, _t1, java.lang.Math.fma(_self01, _t2, _self02)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self10, _t1, java.lang.Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0 = 1.0 - s;
        double _t1 = pivot.getDouble(pivotOffset) * _t0;
        double _t2 = pivot.getDouble(pivotOffset + 8) * _t0;
        dest.putDouble(destOffset, s * _self00);
        dest.putDouble(destOffset + 8, s * _self10);
        dest.putDouble(destOffset + 16, s * _self01);
        dest.putDouble(destOffset + 24, s * _self11);
        dest.putDouble(destOffset + 32, ((_self00) * (_t1) + (((_self01) * (_t2) + (_self02)))));
        dest.putDouble(destOffset + 40, ((_self10) * (_t1) + (((_self11) * (_t2) + (_self12)))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest.putDouble(destOffset, sX * _self00);
        dest.putDouble(destOffset + 8, sX * _self10);
        dest.putDouble(destOffset + 16, sY * _self01);
        dest.putDouble(destOffset + 24, sY * _self11);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest.putDouble(destOffset, sX * _self00);
        dest.putDouble(destOffset + 8, sX * _self10);
        dest.putDouble(destOffset + 16, sY * _self01);
        dest.putDouble(destOffset + 24, sY * _self11);
        dest.putDouble(destOffset + 32, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.putDouble(destOffset + 40, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Double2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer scaleAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return scaleAround_api_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        double _sx = s.getDouble(sOffset);
        double _sy = s.getDouble(sOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t2 = pivot.getDouble(pivotOffset) * (1.0 - _sx);
        double _t3 = pivot.getDouble(pivotOffset + 8) * (1.0 - _sy);
        dest.putDouble(destOffset, _sx * _self00);
        dest.putDouble(destOffset + 8, _sx * _self10);
        dest.putDouble(destOffset + 16, _sy * _self01);
        dest.putDouble(destOffset + 24, _sy * _self11);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self00, _t2, java.lang.Math.fma(_self01, _t3, _self02)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self10, _t2, java.lang.Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    /** {@code scaleAround_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer scaleAround_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        double _sx = s.getDouble(sOffset);
        double _sy = s.getDouble(sOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t2 = pivot.getDouble(pivotOffset) * (1.0 - _sx);
        double _t3 = pivot.getDouble(pivotOffset + 8) * (1.0 - _sy);
        dest.putDouble(destOffset, _sx * _self00);
        dest.putDouble(destOffset + 8, _sx * _self10);
        dest.putDouble(destOffset + 16, _sy * _self01);
        dest.putDouble(destOffset + 24, _sy * _self11);
        dest.putDouble(destOffset + 32, ((_self00) * (_t2) + (((_self01) * (_t3) + (_self02)))));
        dest.putDouble(destOffset + 40, ((_self10) * (_t2) + (((_self11) * (_t3) + (_self12)))));
        return dest;
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            double _self02 = src.getDouble(srcOffset + 32);
            double _self12 = src.getDouble(srcOffset + 40);
            dest.putDouble(destOffset, _self00);
            dest.putDouble(destOffset + 8, _self10);
            dest.putDouble(destOffset + 16, _self01);
            dest.putDouble(destOffset + 24, _self11);
            dest.putDouble(destOffset + 32, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
            dest.putDouble(destOffset + 40, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
            return dest;
        } else {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            double _self02 = src.getDouble(srcOffset + 32);
            double _self12 = src.getDouble(srcOffset + 40);
            dest.putDouble(destOffset, _self00);
            dest.putDouble(destOffset + 8, _self10);
            dest.putDouble(destOffset + 16, _self01);
            dest.putDouble(destOffset + 24, _self11);
            dest.putDouble(destOffset + 32, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
            dest.putDouble(destOffset + 40, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer translate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer translate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return translate_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self01);
        dest.putDouble(destOffset + 24, _self11);
        dest.putDouble(destOffset + 32, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
        dest.putDouble(destOffset + 40, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
        return dest;
    }

    /** {@code translate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer translate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        double _vx = v.getDouble(vOffset);
        double _vy = v.getDouble(vOffset + 8);
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        dest.putDouble(destOffset, _self00);
        dest.putDouble(destOffset + 8, _self10);
        dest.putDouble(destOffset + 16, _self01);
        dest.putDouble(destOffset + 24, _self11);
        dest.putDouble(destOffset + 32, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
        dest.putDouble(destOffset + 40, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
        return dest;
    }

    public static java.nio.ByteBuffer view_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.ByteBuffer view_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        double _self00 = src.getDouble(srcOffset);
        double _self10 = src.getDouble(srcOffset + 8);
        double _self01 = src.getDouble(srcOffset + 16);
        double _self11 = src.getDouble(srcOffset + 24);
        double _self02 = src.getDouble(srcOffset + 32);
        double _self12 = src.getDouble(srcOffset + 40);
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        dest.putDouble(destOffset, _sp0 * _self00);
        dest.putDouble(destOffset + 8, _sp0 * _self10);
        dest.putDouble(destOffset + 16, _sp1 * _self01);
        dest.putDouble(destOffset + 24, _sp1 * _self11);
        dest.putDouble(destOffset + 32, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        dest.putDouble(destOffset + 40, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            double _self02 = src.getDouble(srcOffset + 32);
            double _self12 = src.getDouble(srcOffset + 40);
            dest.putDouble(destOffset, java.lang.Math.fma(_self02, vZ, java.lang.Math.fma(_self00, vX, _self01 * vY)));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, vZ, java.lang.Math.fma(_self10, vX, _self11 * vY)));
            return dest;
        } else {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            double _self02 = src.getDouble(srcOffset + 32);
            double _self12 = src.getDouble(srcOffset + 40);
            dest.putDouble(destOffset, ((_self02) * (vZ) + (((_self00) * (vX) + (_self01 * vY)))));
            dest.putDouble(destOffset + 8, ((_self12) * (vZ) + (((_self10) * (vX) + (_self11 * vY)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer mulVec3_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer mulVec3_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _vx = v.getDouble(vOffset);
            double _vy = v.getDouble(vOffset + 8);
            double _vz = v.getDouble(vOffset + 16);
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            double _self02 = src.getDouble(srcOffset + 32);
            double _self12 = src.getDouble(srcOffset + 40);
            dest.putDouble(destOffset, java.lang.Math.fma(_self02, _vz, java.lang.Math.fma(_self00, _vx, _self01 * _vy)));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_self12, _vz, java.lang.Math.fma(_self10, _vx, _self11 * _vy)));
            return dest;
        } else {
            double _vx = v.getDouble(vOffset);
            double _vy = v.getDouble(vOffset + 8);
            double _vz = v.getDouble(vOffset + 16);
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            double _self02 = src.getDouble(srcOffset + 32);
            double _self12 = src.getDouble(srcOffset + 40);
            dest.putDouble(destOffset, ((_self02) * (_vz) + (((_self00) * (_vx) + (_self01 * _vy)))));
            dest.putDouble(destOffset + 8, ((_self12) * (_vz) + (((_self10) * (_vx) + (_self11 * _vy)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, java.lang.Math.fma(_self00, vX, _self01 * vY));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, vX, _self11 * vY));
            return dest;
        } else {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, ((_self00) * (vX) + (_self01 * vY)));
            dest.putDouble(destOffset + 8, ((_self10) * (vX) + (_self11 * vY)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer transformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformDirection_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _vx = v.getDouble(vOffset);
            double _vy = v.getDouble(vOffset + 8);
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, java.lang.Math.fma(_self00, _vx, _self01 * _vy));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, _vx, _self11 * _vy));
            return dest;
        } else {
            double _vx = v.getDouble(vOffset);
            double _vy = v.getDouble(vOffset + 8);
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            dest.putDouble(destOffset, ((_self00) * (_vx) + (_self01 * _vy)));
            dest.putDouble(destOffset + 8, ((_self10) * (_vx) + (_self11 * _vy)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Double2x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            double _self02 = src.getDouble(srcOffset + 32);
            double _self12 = src.getDouble(srcOffset + 40);
            dest.putDouble(destOffset, java.lang.Math.fma(_self00, vX, java.lang.Math.fma(_self01, vY, _self02)));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, vX, java.lang.Math.fma(_self11, vY, _self12)));
            return dest;
        } else {
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            double _self02 = src.getDouble(srcOffset + 32);
            double _self12 = src.getDouble(srcOffset + 40);
            dest.putDouble(destOffset, ((_self00) * (vX) + (((_self01) * (vY) + (_self02)))));
            dest.putDouble(destOffset + 8, ((_self10) * (vX) + (((_self11) * (vY) + (_self12)))));
            return dest;
        }
    }

    public static java.nio.ByteBuffer transformPosition_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Double2x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer transformPosition_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _vx = v.getDouble(vOffset);
            double _vy = v.getDouble(vOffset + 8);
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            double _self02 = src.getDouble(srcOffset + 32);
            double _self12 = src.getDouble(srcOffset + 40);
            dest.putDouble(destOffset, java.lang.Math.fma(_self00, _vx, java.lang.Math.fma(_self01, _vy, _self02)));
            dest.putDouble(destOffset + 8, java.lang.Math.fma(_self10, _vx, java.lang.Math.fma(_self11, _vy, _self12)));
            return dest;
        } else {
            double _vx = v.getDouble(vOffset);
            double _vy = v.getDouble(vOffset + 8);
            double _self00 = src.getDouble(srcOffset);
            double _self10 = src.getDouble(srcOffset + 8);
            double _self01 = src.getDouble(srcOffset + 16);
            double _self11 = src.getDouble(srcOffset + 24);
            double _self02 = src.getDouble(srcOffset + 32);
            double _self12 = src.getDouble(srcOffset + 40);
            dest.putDouble(destOffset, ((_self00) * (_vx) + (((_self01) * (_vy) + (_self02)))));
            dest.putDouble(destOffset + 8, ((_self10) * (_vx) + (((_self11) * (_vy) + (_self12)))));
            return dest;
        }
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
