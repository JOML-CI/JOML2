// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2x2Ops} whose leading storage
 * parameter is a typed {@link java.nio.DoubleBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double2x2Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double2x2OpsKernelsTypedBuffer {
    private Double2x2OpsKernelsTypedBuffer() {}

    public static java.nio.DoubleBuffer getColumn_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        Double2x2OpsKernelsAddress.getColumn_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, col);
        return dest;
    }

    public static java.nio.DoubleBuffer getColumn_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.getColumn(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, col);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.getColumn_apiGet(dest, destOffset, src, srcOffset, col);
        return dest;
    }

    public static java.nio.DoubleBuffer getColumn_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = src.get(srcOffset); _idxSw1 = src.get(srcOffset + 1); break;
            case 1: _idxSw0 = src.get(srcOffset + 2); _idxSw1 = src.get(srcOffset + 3); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.put(destOffset, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        return dest;
    }

    public static double getRotationAngle_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        return Double2x2OpsKernelsAddress.getRotationAngle_unsafe(UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
    }

    public static double getRotationAngle_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double2x2Ops.getRotationAngle(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double2x2OpsKernelsTypedBuffer.getRotationAngle_apiGet(src, srcOffset);
    }

    public static double getRotationAngle_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        return Math.atan2(src.get(srcOffset + 1), src.get(srcOffset));
    }

    public static java.nio.DoubleBuffer getRow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        Double2x2OpsKernelsAddress.getRow_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, row);
        return dest;
    }

    public static java.nio.DoubleBuffer getRow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.getRow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.getRow_apiGet(dest, destOffset, src, srcOffset, row);
        return dest;
    }

    public static java.nio.DoubleBuffer getRow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        double _idxSw0;
        double _idxSw1;
        switch (row) {
            case 0: _idxSw0 = src.get(srcOffset); _idxSw1 = src.get(srcOffset + 2); break;
            case 1: _idxSw0 = src.get(srcOffset + 1); _idxSw1 = src.get(srcOffset + 3); break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.put(destOffset, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        return dest;
    }

    public static java.nio.DoubleBuffer cofactor_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cofactor_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.cofactor(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.cofactor_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cofactor_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        dest.put(destOffset, src.get(srcOffset + 3));
        dest.put(destOffset + 1, -_self01);
        dest.put(destOffset + 2, -_self10);
        dest.put(destOffset + 3, _self00);
        return dest;
    }

    public static double determinant_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double2x2OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static double determinant_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double2x2Ops.determinant(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double2x2OpsKernelsTypedBuffer.determinant_apiGet(src, srcOffset);
    }

    public static double determinant_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self10 = src.get(srcOffset + 1);
            double _self01 = src.get(srcOffset + 2);
            double _t1 = -(_self01 * _self10);
            return java.lang.Math.fma(src.get(srcOffset), src.get(srcOffset + 3), _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        } else {
            double _self10 = src.get(srcOffset + 1);
            double _self01 = src.get(srcOffset + 2);
            double _t1 = -(_self01 * _self10);
            return ((src.get(srcOffset)) * (src.get(srcOffset + 3)) + (_t1)) - ((_self01) * (_self10) + (_t1));
        }
    }

    public static double frobeniusNorm_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double2x2OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static double frobeniusNorm_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double2x2Ops.frobeniusNorm(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double2x2OpsKernelsTypedBuffer.frobeniusNorm_apiGet(src, srcOffset);
    }

    public static double frobeniusNorm_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.get(srcOffset);
            double _self10 = src.get(srcOffset + 1);
            double _self01 = src.get(srcOffset + 2);
            double _self11 = src.get(srcOffset + 3);
            return java.lang.Math.sqrt(java.lang.Math.fma(_self11, _self11, java.lang.Math.fma(_self10, _self10, java.lang.Math.fma(_self00, _self00, _self01 * _self01))));
        } else {
            double _self00 = src.get(srcOffset);
            double _self10 = src.get(srcOffset + 1);
            double _self01 = src.get(srcOffset + 2);
            double _self11 = src.get(srcOffset + 3);
            return java.lang.Math.sqrt(((_self11) * (_self11) + (((_self10) * (_self10) + (((_self00) * (_self00) + (_self01 * _self01)))))));
        }
    }

    public static java.nio.DoubleBuffer invert_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.invert(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.invert_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t1 = -(_self01 * _self10);
        double _t5 = java.lang.Math.fma(_self00, _self11, _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Double2x2OpsKernelsTypedBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
        }
        double _t5_inv = 1.0 / _t5;
        dest.put(destOffset, _self11 * _t5_inv);
        dest.put(destOffset + 1, -(_self10 * _t5_inv));
        dest.put(destOffset + 2, -(_self01 * _t5_inv));
        dest.put(destOffset + 3, _self00 * _t5_inv);
        return dest;
    }

    /** {@code invert_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer invert_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t1 = -(_self01 * _self10);
        double _t5 = ((_self00) * (_self11) + (_t1)) - ((_self01) * (_self10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Double2x2OpsKernelsTypedBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
        }
        double _t5_inv = 1.0 / _t5;
        dest.put(destOffset, _self11 * _t5_inv);
        dest.put(destOffset + 1, -(_self10 * _t5_inv));
        dest.put(destOffset + 2, -(_self01 * _t5_inv));
        dest.put(destOffset + 3, _self00 * _t5_inv);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer invert_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2OpsKernelsArray.invert_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.invert_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invert_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dest.put(destOffset, _t6 * _sp0);
        dest.put(destOffset + 1, -(_t9 * _sp0));
        dest.put(destOffset + 2, -(_t8 * _sp1));
        dest.put(destOffset + 3, _t7 * _sp1);
        return dest;
    }

    /** {@code invert_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer invert_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dest.put(destOffset, _t6 * _sp0);
        dest.put(destOffset + 1, -(_t9 * _sp0));
        dest.put(destOffset + 2, -(_t8 * _sp1));
        dest.put(destOffset + 3, _t7 * _sp1);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double2x2OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2Ops.invertProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.invertProduct_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_apiGet_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        double _t2 = -(_other01 * _other10);
        double _t3 = -(_self01 * _self10);
        double _t10 = java.lang.Math.fma(_other00, _other11, _t2) - java.lang.Math.fma(_other01, _other10, _t2);
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return Double2x2OpsKernelsTypedBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t10_inv = 1.0 / _t10;
        double _t11 = java.lang.Math.fma(_self00, _self11, _t3) - java.lang.Math.fma(_self01, _self10, _t3);
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return Double2x2OpsKernelsTypedBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t11_inv = 1.0 / _t11;
        double _t22 = _other11 / _t10;
        double _t23 = _self11 / _t11;
        double _t24 = _self00 / _t11;
        double _t27 = _other00 / _t10;
        double _t30 = -(_self01 * _t11_inv);
        return invertProduct_apiGet_fma_s4a040649_1(dest, destOffset, _t22, _t23, _t24, _t27, -(_other01 * _t10_inv), -(_self10 * _t11_inv), _t30, -(_other10 * _t10_inv), _t22 * _t23, _t27 * _t24, _t30 * _t22);
    }

    /** Piece 2 of {@code invertProduct_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_apiGet_fma_s4a040649_1(java.nio.DoubleBuffer dest, int destOffset, double _t22, double _t23, double _t24, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t33, double _t34) {
        double _t35 = _t29 * _t27;
        dest.put(destOffset, java.lang.Math.fma(_t28, _t29, _t32) + java.lang.Math.fma(_t22, _t23, -_t32));
        dest.put(destOffset + 1, java.lang.Math.fma(_t31, _t23, _t35) + java.lang.Math.fma(_t29, _t27, -_t35));
        dest.put(destOffset + 2, java.lang.Math.fma(_t28, _t24, _t34) + java.lang.Math.fma(_t30, _t22, -_t34));
        dest.put(destOffset + 3, java.lang.Math.fma(_t31, _t30, _t33) + java.lang.Math.fma(_t27, _t24, -_t33));
        return dest;
    }

    /** {@code invertProduct_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        double _t2 = -(_other01 * _other10);
        double _t3 = -(_self01 * _self10);
        double _t10 = ((_other00) * (_other11) + (_t2)) - ((_other01) * (_other10) + (_t2));
        if (!(java.lang.Math.abs(_t10) > 2.2250738585072014E-308 && java.lang.Math.abs(_t10) < 4.49423283715579E307)) return Double2x2OpsKernelsTypedBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t10_inv = 1.0 / _t10;
        double _t11 = ((_self00) * (_self11) + (_t3)) - ((_self01) * (_self10) + (_t3));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return Double2x2OpsKernelsTypedBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t11_inv = 1.0 / _t11;
        double _t22 = _other11 / _t10;
        double _t23 = _self11 / _t11;
        double _t24 = _self00 / _t11;
        double _t27 = _other00 / _t10;
        double _t29 = -(_self10 * _t11_inv);
        double _t30 = -(_self01 * _t11_inv);
        return invertProduct_apiGet_mulAdd_s5e0bcc8e_1(dest, destOffset, _t22, _t23, _t24, _t27, -(_other01 * _t10_inv), _t29, _t30, -(_other10 * _t10_inv), _t22 * _t23, _t27 * _t24, _t30 * _t22, _t29 * _t27);
    }

    /** Piece 2 of {@code invertProduct_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_apiGet_mulAdd_s5e0bcc8e_1(java.nio.DoubleBuffer dest, int destOffset, double _t22, double _t23, double _t24, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35) {
        dest.put(destOffset, ((_t28) * (_t29) + (_t32)) + ((_t22) * (_t23) - (_t32)));
        dest.put(destOffset + 1, ((_t31) * (_t23) + (_t35)) + ((_t29) * (_t27) - (_t35)));
        dest.put(destOffset + 2, ((_t28) * (_t24) + (_t34)) + ((_t30) * (_t22) - (_t34)));
        dest.put(destOffset + 3, ((_t31) * (_t30) + (_t33)) + ((_t27) * (_t24) - (_t33)));
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x2OpsKernelsTypedBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double2x2OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2OpsKernelsArray.invertProduct_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.invertProduct_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_degenerate_apiGet_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return invertProduct_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@code invertProduct_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_degenerate_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        double _t12 = _other01 * _t0;
        double _t13 = _other00 * _t0;
        double _t14 = _other11 * _t1;
        double _t15 = _other10 * _t1;
        double _t16 = _self10 * _t2;
        double _t17 = _self00 * _t3;
        double _t18 = _self11 * _t2;
        double _t19 = _self01 * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = java.lang.Math.fma(_t13, _t14, _t30) - java.lang.Math.fma(_t12, _t15, _t30);
        double _t36_inv = 1.0 / _t36;
        double _t37 = java.lang.Math.fma(_t17, _t18, _t31) - java.lang.Math.fma(_t19, _t16, _t31);
        return invertProduct_degenerate_apiGet_fma_saff5b874_1(dest, destOffset, _t0, _t2, _t3, _t15, _t16, _t19, _t36_inv, 1.0 / _t37, _t14 * _t0 / _t36, _t18 * _t3 / _t37, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv));
    }

    /** Piece 2 of {@code invertProduct_degenerate_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_degenerate_apiGet_fma_saff5b874_1(java.nio.DoubleBuffer dest, int destOffset, double _t0, double _t2, double _t3, double _t15, double _t16, double _t19, double _t36_inv, double _t37_inv, double _t48, double _t49, double _t50, double _t53, double _t54) {
        double _t55 = -(_t16 * _t3 * _t37_inv);
        double _t56 = -(_t19 * _t2 * _t37_inv);
        double _t57 = -(_t15 * _t0 * _t36_inv);
        double _t58 = _t48 * _t49;
        double _t59 = _t53 * _t50;
        double _t60 = _t56 * _t48;
        double _t61 = _t55 * _t53;
        dest.put(destOffset, java.lang.Math.fma(_t54, _t55, _t58) + java.lang.Math.fma(_t48, _t49, -_t58));
        dest.put(destOffset + 1, java.lang.Math.fma(_t57, _t49, _t61) + java.lang.Math.fma(_t55, _t53, -_t61));
        dest.put(destOffset + 2, java.lang.Math.fma(_t54, _t50, _t60) + java.lang.Math.fma(_t56, _t48, -_t60));
        dest.put(destOffset + 3, java.lang.Math.fma(_t57, _t56, _t59) + java.lang.Math.fma(_t53, _t50, -_t59));
        return dest;
    }

    /** {@code invertProduct_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        double _t0 = unitScale(_other00, _other01, _other00);
        double _t1 = unitScale(_other10, _other11, _other10);
        double _t2 = unitScale(_self10, _self11, _self10);
        double _t3 = unitScale(_self00, _self01, _self00);
        double _t12 = _other01 * _t0;
        double _t13 = _other00 * _t0;
        double _t14 = _other11 * _t1;
        double _t15 = _other10 * _t1;
        double _t16 = _self10 * _t2;
        double _t17 = _self00 * _t3;
        double _t18 = _self11 * _t2;
        double _t19 = _self01 * _t3;
        double _t30 = -(_t12 * _t15);
        double _t31 = -(_t19 * _t16);
        double _t36 = ((_t13) * (_t14) + (_t30)) - ((_t12) * (_t15) + (_t30));
        double _t36_inv = 1.0 / _t36;
        double _t37 = ((_t17) * (_t18) + (_t31)) - ((_t19) * (_t16) + (_t31));
        return invertProduct_degenerate_apiGet_mulAdd_sb1085419_1(dest, destOffset, _t0, _t2, _t3, _t15, _t16, _t19, _t36_inv, 1.0 / _t37, _t14 * _t0 / _t36, _t18 * _t3 / _t37, _t17 * _t2 / _t37, _t13 * _t1 / _t36, -(_t12 * _t1 * _t36_inv));
    }

    /** Piece 2 of {@code invertProduct_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_degenerate_apiGet_mulAdd_sb1085419_1(java.nio.DoubleBuffer dest, int destOffset, double _t0, double _t2, double _t3, double _t15, double _t16, double _t19, double _t36_inv, double _t37_inv, double _t48, double _t49, double _t50, double _t53, double _t54) {
        double _t55 = -(_t16 * _t3 * _t37_inv);
        double _t56 = -(_t19 * _t2 * _t37_inv);
        double _t57 = -(_t15 * _t0 * _t36_inv);
        double _t58 = _t48 * _t49;
        double _t59 = _t53 * _t50;
        double _t60 = _t56 * _t48;
        double _t61 = _t55 * _t53;
        dest.put(destOffset, ((_t54) * (_t55) + (_t58)) + ((_t48) * (_t49) - (_t58)));
        dest.put(destOffset + 1, ((_t57) * (_t49) + (_t61)) + ((_t55) * (_t53) - (_t61)));
        dest.put(destOffset + 2, ((_t54) * (_t50) + (_t60)) + ((_t56) * (_t48) - (_t60)));
        dest.put(destOffset + 3, ((_t57) * (_t56) + (_t59)) + ((_t53) * (_t50) - (_t59)));
        return dest;
    }

    public static java.nio.DoubleBuffer normal_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.normal(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.normal_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normal_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t1 = -(_self01 * _self10);
        double _t5 = java.lang.Math.fma(_self00, _self11, _t1) - java.lang.Math.fma(_self01, _self10, _t1);
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Double2x2OpsKernelsTypedBuffer.normal_degenerate_api(dest, destOffset, src, srcOffset);
        }
        double _t5_inv = 1.0 / _t5;
        dest.put(destOffset, _self11 * _t5_inv);
        dest.put(destOffset + 1, -(_self01 * _t5_inv));
        dest.put(destOffset + 2, -(_self10 * _t5_inv));
        dest.put(destOffset + 3, _self00 * _t5_inv);
        return dest;
    }

    /** {@code normal_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer normal_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t1 = -(_self01 * _self10);
        double _t5 = ((_self00) * (_self11) + (_t1)) - ((_self01) * (_self10) + (_t1));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
            return Double2x2OpsKernelsTypedBuffer.normal_degenerate_api(dest, destOffset, src, srcOffset);
        }
        double _t5_inv = 1.0 / _t5;
        dest.put(destOffset, _self11 * _t5_inv);
        dest.put(destOffset + 1, -(_self01 * _t5_inv));
        dest.put(destOffset + 2, -(_self10 * _t5_inv));
        dest.put(destOffset + 3, _self00 * _t5_inv);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.normal_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer normal_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2OpsKernelsArray.normal_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.normal_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normal_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (java.lang.Math.fma(_t7, _t6, _t11) - java.lang.Math.fma(_t8, _t9, _t11));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dest.put(destOffset, _t6 * _sp0);
        dest.put(destOffset + 1, -(_t8 * _sp1));
        dest.put(destOffset + 2, -(_t9 * _sp0));
        dest.put(destOffset + 3, _t7 * _sp1);
        return dest;
    }

    /** {@code normal_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer normal_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t11 = -(_t8 * _t9);
        double _t14_inv = 1.0 / (((_t7) * (_t6) + (_t11)) - ((_t8) * (_t9) + (_t11)));
        double _sp1 = _t0 * _t14_inv;
        double _sp0 = _t1 * _t14_inv;
        dest.put(destOffset, _t6 * _sp0);
        dest.put(destOffset + 1, -(_t8 * _sp1));
        dest.put(destOffset + 2, -(_t9 * _sp0));
        dest.put(destOffset + 3, _t7 * _sp1);
        return dest;
    }

    public static double trace_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double2x2OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static double trace_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double2x2Ops.trace(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double2x2OpsKernelsTypedBuffer.trace_apiGet(src, srcOffset);
    }

    public static double trace_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        return src.get(srcOffset) + src.get(srcOffset + 3);
    }

    public static java.nio.DoubleBuffer transpose_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer transpose_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.transpose(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.transpose_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer transpose_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self10);
        dest.put(destOffset + 3, _self11);
        return dest;
    }

    public static java.nio.DoubleBuffer add_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double2x2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer add_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer add_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        dest.put(destOffset, other.get(otherOffset) + src.get(srcOffset));
        dest.put(destOffset + 1, _other10 + _self10);
        dest.put(destOffset + 2, _other01 + _self01);
        dest.put(destOffset + 3, _other11 + _self11);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, scalar * src.get(srcOffset));
        dest.put(destOffset + 1, scalar * _self10);
        dest.put(destOffset + 2, scalar * _self01);
        dest.put(destOffset + 3, scalar * _self11);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, -src.get(srcOffset));
        dest.put(destOffset + 1, -_self10);
        dest.put(destOffset + 2, -_self01);
        dest.put(destOffset + 3, -_self11);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double2x2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        dest.put(destOffset, src.get(srcOffset) - other.get(otherOffset));
        dest.put(destOffset + 1, _self10 - _other10);
        dest.put(destOffset + 2, _self01 - _other01);
        dest.put(destOffset + 3, _self11 - _other11);
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double2x2OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4) {
            Double2x2Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _v10 = v.get(vOffset + 1);
        double _v01 = v.get(vOffset + 2);
        double _v11 = v.get(vOffset + 3);
        dest.put(destOffset, v.get(vOffset));
        dest.put(destOffset + 1, _v10);
        dest.put(destOffset + 2, _v01);
        dest.put(destOffset + 3, _v11);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat2x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        Double2x2OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat2x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 6) {
            Double2x2Ops.setMat2x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.setMat2x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat2x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _m10 = m.get(mOffset + 1);
        double _m01 = m.get(mOffset + 2);
        double _m11 = m.get(mOffset + 3);
        dest.put(destOffset, m.get(mOffset));
        dest.put(destOffset + 1, _m10);
        dest.put(destOffset + 2, _m01);
        dest.put(destOffset + 3, _m11);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat3x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        Double2x2OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat3x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 9) {
            Double2x2Ops.setMat3x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.setMat3x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat3x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _m10 = m.get(mOffset + 1);
        double _m01 = m.get(mOffset + 3);
        double _m11 = m.get(mOffset + 4);
        dest.put(destOffset, m.get(mOffset));
        dest.put(destOffset + 1, _m10);
        dest.put(destOffset + 2, _m01);
        dest.put(destOffset + 3, _m11);
        return dest;
    }

    public static java.nio.DoubleBuffer to2x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer to2x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.to2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.to2x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer to2x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self01);
        dest.put(destOffset + 3, _self11);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer to3x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer to3x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.to3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.to3x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer to3x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeLDU_unsafe(java.nio.DoubleBuffer lower, int lowerOffset, java.nio.DoubleBuffer diagonal, int diagonalOffset, java.nio.DoubleBuffer upper, int upperOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _lowerBase = UnsafeOpsHolder.U.getLong(lower, UnsafeCopy.BB_ADDRESS_OFFSET) + lowerOffset * 8L;
        long _diagonalBase = UnsafeOpsHolder.U.getLong(diagonal, UnsafeCopy.BB_ADDRESS_OFFSET) + diagonalOffset * 8L;
        long _upperBase = UnsafeOpsHolder.U.getLong(upper, UnsafeCopy.BB_ADDRESS_OFFSET) + upperOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.decomposeLDU_unsafe(_lowerBase, _diagonalBase, _upperBase, _srcBase);
        return lower;
    }

    public static java.nio.DoubleBuffer decomposeLDU_api(java.nio.DoubleBuffer lower, int lowerOffset, java.nio.DoubleBuffer diagonal, int diagonalOffset, java.nio.DoubleBuffer upper, int upperOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (lower.hasArray() && lowerOffset >= 0 && lowerOffset <= lower.limit() - 4 && diagonal.hasArray() && diagonalOffset >= 0 && diagonalOffset <= diagonal.limit() - 4 && upper.hasArray() && upperOffset >= 0 && upperOffset <= upper.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.decomposeLDU(lower.array(), lower.arrayOffset() + lowerOffset, diagonal.array(), diagonal.arrayOffset() + diagonalOffset, upper.array(), upper.arrayOffset() + upperOffset, src.array(), src.arrayOffset() + srcOffset);
            return lower;
        }
        Double2x2OpsKernelsTypedBuffer.decomposeLDU_apiGet(lower, lowerOffset, diagonal, diagonalOffset, upper, upperOffset, src, srcOffset);
        return lower;
    }

    public static java.nio.DoubleBuffer decomposeLDU_apiGet(java.nio.DoubleBuffer lower, int lowerOffset, java.nio.DoubleBuffer diagonal, int diagonalOffset, java.nio.DoubleBuffer upper, int upperOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _rcp0 = 1.0 / _self00;
        double _sp0 = src.get(srcOffset + 1) * _rcp0;
        lower.put(lowerOffset, 1.0);
        lower.put(lowerOffset + 1, _sp0);
        lower.put(lowerOffset + 2, 0.0);
        lower.put(lowerOffset + 3, 1.0);
        diagonal.put(diagonalOffset, _self00);
        diagonal.put(diagonalOffset + 1, 0.0);
        diagonal.put(diagonalOffset + 2, 0.0);
        diagonal.put(diagonalOffset + 3, _self11 - _self01 * _sp0);
        upper.put(upperOffset, 1.0);
        upper.put(upperOffset + 1, 0.0);
        upper.put(upperOffset + 2, _self01 * _rcp0);
        upper.put(upperOffset + 3, 1.0);
        return lower;
    }

    public static java.nio.DoubleBuffer makeIdentity_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double2x2OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeIdentity_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double2x2Ops.makeIdentity(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeIdentity_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeIdentity_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double2x2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        dest.put(destOffset, java.lang.Math.fma(t, other.get(otherOffset) - _self00, _self00));
        dest.put(destOffset + 1, java.lang.Math.fma(t, _other10 - _self10, _self10));
        dest.put(destOffset + 2, java.lang.Math.fma(t, _other01 - _self01, _self01));
        dest.put(destOffset + 3, java.lang.Math.fma(t, _other11 - _self11, _self11));
        return dest;
    }

    /** {@code lerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer lerp_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        dest.put(destOffset, ((t) * (other.get(otherOffset) - _self00) + (_self00)));
        dest.put(destOffset + 1, ((t) * (_other10 - _self10) + (_self10)));
        dest.put(destOffset + 2, ((t) * (_other01 - _self01) + (_self01)));
        dest.put(destOffset + 3, ((t) * (_other11 - _self11) + (_self11)));
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset * 8L;
        Double2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 4) {
            Double2x2Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_apiGet_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _right00 = right.get(rightOffset);
        double _right10 = right.get(rightOffset + 1);
        double _right01 = right.get(rightOffset + 2);
        double _right11 = right.get(rightOffset + 3);
        dest.put(destOffset, java.lang.Math.fma(_right00, _self00, _right10 * _self01));
        dest.put(destOffset + 1, java.lang.Math.fma(_right00, _self10, _right10 * _self11));
        dest.put(destOffset + 2, java.lang.Math.fma(_right01, _self00, _right11 * _self01));
        dest.put(destOffset + 3, java.lang.Math.fma(_right01, _self10, _right11 * _self11));
        return dest;
    }

    /** {@code mul_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer mul_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _right00 = right.get(rightOffset);
        double _right10 = right.get(rightOffset + 1);
        double _right01 = right.get(rightOffset + 2);
        double _right11 = right.get(rightOffset + 3);
        dest.put(destOffset, ((_right00) * (_self00) + (_right10 * _self01)));
        dest.put(destOffset + 1, ((_right00) * (_self10) + (_right10 * _self11)));
        dest.put(destOffset + 2, ((_right01) * (_self00) + (_right11 * _self01)));
        dest.put(destOffset + 3, ((_right01) * (_self10) + (_right11 * _self11)));
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double2x2OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        dest.put(destOffset, java.lang.Math.fma(_other00, _self00, _other01 * _self10));
        dest.put(destOffset + 1, java.lang.Math.fma(_other10, _self00, _other11 * _self10));
        dest.put(destOffset + 2, java.lang.Math.fma(_other00, _self01, _other01 * _self11));
        dest.put(destOffset + 3, java.lang.Math.fma(_other10, _self01, _other11 * _self11));
        return dest;
    }

    /** {@code preMul_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer preMul_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        dest.put(destOffset, ((_other00) * (_self00) + (_other01 * _self10)));
        dest.put(destOffset + 1, ((_other10) * (_self00) + (_other11 * _self10)));
        dest.put(destOffset + 2, ((_other00) * (_self01) + (_other01 * _self11)));
        dest.put(destOffset + 3, ((_other10) * (_self01) + (_other11 * _self11)));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double2x2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, weight);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self10 = src.get(srcOffset + 1);
            double _self01 = src.get(srcOffset + 2);
            double _self11 = src.get(srcOffset + 3);
            double _other10 = other.get(otherOffset + 1);
            double _other01 = other.get(otherOffset + 2);
            double _other11 = other.get(otherOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(weight, other.get(otherOffset), src.get(srcOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(weight, _other10, _self10));
            dest.put(destOffset + 2, java.lang.Math.fma(weight, _other01, _self01));
            dest.put(destOffset + 3, java.lang.Math.fma(weight, _other11, _self11));
            return dest;
        } else {
            double _self10 = src.get(srcOffset + 1);
            double _self01 = src.get(srcOffset + 2);
            double _self11 = src.get(srcOffset + 3);
            double _other10 = other.get(otherOffset + 1);
            double _other01 = other.get(otherOffset + 2);
            double _other11 = other.get(otherOffset + 3);
            dest.put(destOffset, ((weight) * (other.get(otherOffset)) + (src.get(srcOffset))));
            dest.put(destOffset + 1, ((weight) * (_other10) + (_self10)));
            dest.put(destOffset + 2, ((weight) * (_other01) + (_self01)));
            dest.put(destOffset + 3, ((weight) * (_other11) + (_self11)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer makeOuterProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, double colX, double colY, double rowX, double rowY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, rowX, rowY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_api(java.nio.DoubleBuffer dest, int destOffset, double colX, double colY, double rowX, double rowY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double2x2Ops.makeOuterProduct(dest.array(), dest.arrayOffset() + destOffset, colX, colY, rowX, rowY);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeOuterProduct_apiGet(dest, destOffset, colX, colY, rowX, rowY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, double colX, double colY, double rowX, double rowY) {
        dest.put(destOffset, colX * rowX);
        dest.put(destOffset + 1, colY * rowX);
        dest.put(destOffset + 2, colX * rowY);
        dest.put(destOffset + 3, colY * rowY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer col, int colOffset, java.nio.DoubleBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _colBase = UnsafeOpsHolder.U.getLong(col, UnsafeCopy.BB_ADDRESS_OFFSET) + colOffset * 8L;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset * 8L;
        Double2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer col, int colOffset, java.nio.DoubleBuffer row, int rowOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && col.hasArray() && colOffset >= 0 && colOffset <= col.limit() - 2 && row.hasArray() && rowOffset >= 0 && rowOffset <= row.limit() - 2) {
            Double2x2Ops.makeOuterProduct(dest.array(), dest.arrayOffset() + destOffset, col.array(), col.arrayOffset() + colOffset, row.array(), row.arrayOffset() + rowOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeOuterProduct_apiGet(dest, destOffset, col, colOffset, row, rowOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer col, int colOffset, java.nio.DoubleBuffer row, int rowOffset) {
        double _colx = col.get(colOffset);
        double _coly = col.get(colOffset + 1);
        double _rowx = row.get(rowOffset);
        double _rowy = row.get(rowOffset + 1);
        dest.put(destOffset, _colx * _rowx);
        dest.put(destOffset + 1, _coly * _rowx);
        dest.put(destOffset + 2, _colx * _rowy);
        dest.put(destOffset + 3, _coly * _rowy);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotation_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double2x2OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotation_api(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double2x2Ops.makeRotation(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeRotation_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotation_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        dest.put(destOffset, _t0);
        dest.put(destOffset + 1, _t1);
        dest.put(destOffset + 2, -_t1);
        dest.put(destOffset + 3, _t0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_unsafe(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_api(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double2x2Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, vX, vY);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_apiGet(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        dest.put(destOffset, vX);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double2x2Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vy = v.get(vOffset + 1);
        dest.put(destOffset, v.get(vOffset));
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, _vy);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_unsafe(java.nio.DoubleBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_api(java.nio.DoubleBuffer dest, int destOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double2x2Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_apiGet(java.nio.DoubleBuffer dest, int destOffset, double s) {
        dest.put(destOffset, s);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.preRotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.preRotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.get(srcOffset);
            double _self10 = src.get(srcOffset + 1);
            double _self01 = src.get(srcOffset + 2);
            double _self11 = src.get(srcOffset + 3);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, java.lang.Math.fma(_self00, _t0, -(_self10 * _t1)));
            dest.put(destOffset + 1, java.lang.Math.fma(_self00, _t1, _self10 * _t0));
            dest.put(destOffset + 2, java.lang.Math.fma(_self01, _t0, -(_self11 * _t1)));
            dest.put(destOffset + 3, java.lang.Math.fma(_self01, _t1, _self11 * _t0));
            return dest;
        } else {
            double _self00 = src.get(srcOffset);
            double _self10 = src.get(srcOffset + 1);
            double _self01 = src.get(srcOffset + 2);
            double _self11 = src.get(srcOffset + 3);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, ((_self00) * (_t0) - (_self10 * _t1)));
            dest.put(destOffset + 1, ((_self00) * (_t1) + (_self10 * _t0)));
            dest.put(destOffset + 2, ((_self01) * (_t0) - (_self11 * _t1)));
            dest.put(destOffset + 3, ((_self01) * (_t1) + (_self11 * _t0)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer preScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) * vX);
        dest.put(destOffset + 1, _self10 * vY);
        dest.put(destOffset + 2, _self01 * vX);
        dest.put(destOffset + 3, _self11 * vY);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double2x2Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) * _vx);
        dest.put(destOffset + 1, _self10 * _vy);
        dest.put(destOffset + 2, _self01 * _vx);
        dest.put(destOffset + 3, _self11 * _vy);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, s * src.get(srcOffset));
        dest.put(destOffset + 1, s * _self10);
        dest.put(destOffset + 2, s * _self01);
        dest.put(destOffset + 3, s * _self11);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self00 = src.get(srcOffset);
            double _self10 = src.get(srcOffset + 1);
            double _self01 = src.get(srcOffset + 2);
            double _self11 = src.get(srcOffset + 3);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, java.lang.Math.fma(_self00, _t0, _self01 * _t1));
            dest.put(destOffset + 1, java.lang.Math.fma(_self10, _t0, _self11 * _t1));
            dest.put(destOffset + 2, java.lang.Math.fma(_self01, _t0, -(_self00 * _t1)));
            dest.put(destOffset + 3, java.lang.Math.fma(_self11, _t0, -(_self10 * _t1)));
            return dest;
        } else {
            double _self00 = src.get(srcOffset);
            double _self10 = src.get(srcOffset + 1);
            double _self01 = src.get(srcOffset + 2);
            double _self11 = src.get(srcOffset + 3);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, ((_self00) * (_t0) + (_self01 * _t1)));
            dest.put(destOffset + 1, ((_self10) * (_t0) + (_self11 * _t1)));
            dest.put(destOffset + 2, ((_self01) * (_t0) - (_self00 * _t1)));
            dest.put(destOffset + 3, ((_self11) * (_t0) - (_self10 * _t1)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer scale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) * vX);
        dest.put(destOffset + 1, _self10 * vX);
        dest.put(destOffset + 2, _self01 * vY);
        dest.put(destOffset + 3, _self11 * vY);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double2x2Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) * _vx);
        dest.put(destOffset + 1, _self10 * _vx);
        dest.put(destOffset + 2, _self01 * _vy);
        dest.put(destOffset + 3, _self11 * _vy);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.mulVec2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.mulVec2_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _self10 = src.get(srcOffset + 1);
            double _self11 = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), vX, src.get(srcOffset + 2) * vY));
            dest.put(destOffset + 1, java.lang.Math.fma(_self10, vX, _self11 * vY));
            return dest;
        } else {
            double _self10 = src.get(srcOffset + 1);
            double _self11 = src.get(srcOffset + 3);
            dest.put(destOffset, ((src.get(srcOffset)) * (vX) + (src.get(srcOffset + 2) * vY)));
            dest.put(destOffset + 1, ((_self10) * (vX) + (_self11 * vY)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer mulVec2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double2x2Ops.mulVec2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.mulVec2_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _vx = v.get(vOffset);
            double _vy = v.get(vOffset + 1);
            double _self10 = src.get(srcOffset + 1);
            double _self11 = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), _vx, src.get(srcOffset + 2) * _vy));
            dest.put(destOffset + 1, java.lang.Math.fma(_self10, _vx, _self11 * _vy));
            return dest;
        } else {
            double _vx = v.get(vOffset);
            double _vy = v.get(vOffset + 1);
            double _self10 = src.get(srcOffset + 1);
            double _self11 = src.get(srcOffset + 3);
            dest.put(destOffset, ((src.get(srcOffset)) * (_vx) + (src.get(srcOffset + 2) * _vy)));
            dest.put(destOffset + 1, ((_self10) * (_vx) + (_self11 * _vy)));
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
