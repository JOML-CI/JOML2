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
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double2x2Ops} and its sibling kernel units. Not public API.
 */
public final class Double2x2OpsKernelsTypedBuffer {
    private Double2x2OpsKernelsTypedBuffer() {}

    public static java.nio.DoubleBuffer getColumn_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.getColumn_unsafe(_destBase, _srcBase, col);
        return dest;
    }

    public static java.nio.DoubleBuffer getColumn_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.getColumn(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, col);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.getColumn_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, col);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.getColumn_apiGet(dest, destOffset, src, srcOffset, col);
        return dest;
    }

    public static java.nio.DoubleBuffer getColumn_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.put(destOffset + 0, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        return dest;
    }

    public static double getRotationAngle_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2x2OpsKernelsAddress.getRotationAngle_unsafe(_srcBase);
    }

    public static double getRotationAngle_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double2x2Ops.getRotationAngle(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2x2OpsKernelsSegment.getRotationAngle_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double2x2OpsKernelsTypedBuffer.getRotationAngle_apiGet(src, srcOffset);
    }

    public static double getRotationAngle_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        return Math.atan2(_self10, _self00);
    }

    public static java.nio.DoubleBuffer getRow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.getRow_unsafe(_destBase, _srcBase, row);
        return dest;
    }

    public static java.nio.DoubleBuffer getRow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.getRow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.getRow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, row);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.getRow_apiGet(dest, destOffset, src, srcOffset, row);
        return dest;
    }

    public static java.nio.DoubleBuffer getRow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _idxSw0;
        double _idxSw1;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.put(destOffset + 0, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        return dest;
    }

    public static java.nio.DoubleBuffer cofactor_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cofactor_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.cofactor(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.cofactor_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.cofactor_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cofactor_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset + 0, _self11);
        dest.put(destOffset + 1, -_self01);
        dest.put(destOffset + 2, -_self10);
        dest.put(destOffset + 3, _self00);
        return dest;
    }

    public static double determinant_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2x2OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static double determinant_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double2x2Ops.determinant(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2x2OpsKernelsSegment.determinant_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double2x2OpsKernelsTypedBuffer.determinant_apiGet(src, srcOffset);
    }

    public static double determinant_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        return Math.fma(_self00, _self11, -(_self01 * _self10));
    }

    public static double frobeniusNorm_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2x2OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static double frobeniusNorm_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double2x2Ops.frobeniusNorm(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2x2OpsKernelsSegment.frobeniusNorm_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double2x2OpsKernelsTypedBuffer.frobeniusNorm_apiGet(src, srcOffset);
    }

    public static double frobeniusNorm_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        return Math.sqrt(Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self00, _self00, _self01 * _self01))));
    }

    public static java.nio.DoubleBuffer invert_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.invert(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.invert_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.invert_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return Double2x2OpsKernelsTypedBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t3_inv = 1.0 / _t3;
        dest.put(destOffset + 0, _self11 * _t3_inv);
        dest.put(destOffset + 1, -(_self10 * _t3_inv));
        dest.put(destOffset + 2, -(_self01 * _t3_inv));
        dest.put(destOffset + 3, _self00 * _t3_inv);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer invert_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2OpsKernelsArray.invert_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.invert_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.invert_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
        dest.put(destOffset + 0, _t6 * _sp0);
        dest.put(destOffset + 1, -(_t9 * _sp0));
        dest.put(destOffset + 2, -(_t8 * _sp1));
        dest.put(destOffset + 3, _t7 * _sp1);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2x2OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2Ops.invertProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.invertProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.invertProduct_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset + 0);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        double _t4 = Math.fma(_other01, _self10, _other11 * _self11);
        double _t5 = Math.fma(_other00, _self00, _other10 * _self01);
        double _t6 = Math.fma(_other00, _self10, _other10 * _self11);
        double _t7 = Math.fma(_other01, _self00, _other11 * _self01);
        double _t11 = Math.fma(_t5, _t4, -(_t6 * _t7));
        if (!(Math.abs(_t11) > 2.2250738585072014E-308 && Math.abs(_t11) < 4.49423283715579E307)) return Double2x2OpsKernelsTypedBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t11_inv = 1.0 / _t11;
        dest.put(destOffset + 0, _t4 * _t11_inv);
        dest.put(destOffset + 1, -(_t6 * _t11_inv));
        dest.put(destOffset + 2, -(_t7 * _t11_inv));
        dest.put(destOffset + 3, _t5 * _t11_inv);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x2OpsKernelsTypedBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2x2OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2OpsKernelsArray.invertProduct_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.invertProduct_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.invertProduct_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset + 0);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
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
        dest.put(destOffset + 0, _t14 * _sp0);
        dest.put(destOffset + 1, -(_t16 * _sp0));
        dest.put(destOffset + 2, -(_t17 * _sp1));
        dest.put(destOffset + 3, _t15 * _sp1);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.normal(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.normal_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.normal_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(Math.abs(_t3) > 2.2250738585072014E-308 && Math.abs(_t3) < 4.49423283715579E307)) return Double2x2OpsKernelsTypedBuffer.normal_degenerate(dest, destOffset, src, srcOffset);
        double _t3_inv = 1.0 / _t3;
        dest.put(destOffset + 0, _self11 * _t3_inv);
        dest.put(destOffset + 1, -(_self01 * _t3_inv));
        dest.put(destOffset + 2, -(_self10 * _t3_inv));
        dest.put(destOffset + 3, _self00 * _t3_inv);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.normal_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer normal_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2OpsKernelsArray.normal_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.normal_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.normal_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normal_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t0 = unitScale(_self10, _self11, _self10);
        double _t1 = unitScale(_self00, _self01, _self00);
        double _t6 = _self11 * _t0;
        double _t7 = _self00 * _t1;
        double _t8 = _self01 * _t1;
        double _t9 = _self10 * _t0;
        double _t12_inv = 1.0 / Math.fma(_t7, _t6, -(_t8 * _t9));
        double _sp1 = _t0 * _t12_inv;
        double _sp0 = _t1 * _t12_inv;
        dest.put(destOffset + 0, _t6 * _sp0);
        dest.put(destOffset + 1, -(_t8 * _sp1));
        dest.put(destOffset + 2, -(_t9 * _sp0));
        dest.put(destOffset + 3, _t7 * _sp1);
        return dest;
    }

    public static double trace_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2x2OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static double trace_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double2x2Ops.trace(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2x2OpsKernelsSegment.trace_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double2x2OpsKernelsTypedBuffer.trace_apiGet(src, srcOffset);
    }

    public static double trace_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self11 = src.get(srcOffset + 3);
        return _self00 + _self11;
    }

    public static java.nio.DoubleBuffer transpose_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer transpose_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.transpose(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.transpose_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.transpose_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer transpose_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self10);
        dest.put(destOffset + 3, _self11);
        return dest;
    }

    public static java.nio.DoubleBuffer add_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2x2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer add_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.add_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer add_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset + 0);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        dest.put(destOffset + 0, _other00 + _self00);
        dest.put(destOffset + 1, _other10 + _self10);
        dest.put(destOffset + 2, _other01 + _self01);
        dest.put(destOffset + 3, _other11 + _self11);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, scalar);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset + 0, scalar * _self00);
        dest.put(destOffset + 1, scalar * _self10);
        dest.put(destOffset + 2, scalar * _self01);
        dest.put(destOffset + 3, scalar * _self11);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.negate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset + 0, -_self00);
        dest.put(destOffset + 1, -_self10);
        dest.put(destOffset + 2, -_self01);
        dest.put(destOffset + 3, -_self11);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2x2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.sub_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset + 0);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        dest.put(destOffset + 0, _self00 - _other00);
        dest.put(destOffset + 1, _self10 - _other10);
        dest.put(destOffset + 2, _self01 - _other01);
        dest.put(destOffset + 3, _self11 - _other11);
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double2x2OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4) {
            Double2x2Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _v00 = v.get(vOffset + 0);
        double _v10 = v.get(vOffset + 1);
        double _v01 = v.get(vOffset + 2);
        double _v11 = v.get(vOffset + 3);
        dest.put(destOffset + 0, _v00);
        dest.put(destOffset + 1, _v10);
        dest.put(destOffset + 2, _v01);
        dest.put(destOffset + 3, _v11);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat2x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 8L;
        Double2x2OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat2x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 6) {
            Double2x2Ops.setMat2x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.setMat2x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), (long) mOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.setMat2x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat2x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _m00 = m.get(mOffset + 0);
        double _m10 = m.get(mOffset + 1);
        double _m01 = m.get(mOffset + 2);
        double _m11 = m.get(mOffset + 3);
        dest.put(destOffset + 0, _m00);
        dest.put(destOffset + 1, _m10);
        dest.put(destOffset + 2, _m01);
        dest.put(destOffset + 3, _m11);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat3x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) mOffset * 8L;
        Double2x2OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat3x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 9) {
            Double2x2Ops.setMat3x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.setMat3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), (long) mOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.setMat3x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat3x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _m00 = m.get(mOffset + 0);
        double _m10 = m.get(mOffset + 1);
        double _m01 = m.get(mOffset + 3);
        double _m11 = m.get(mOffset + 4);
        dest.put(destOffset + 0, _m00);
        dest.put(destOffset + 1, _m10);
        dest.put(destOffset + 2, _m01);
        dest.put(destOffset + 3, _m11);
        return dest;
    }

    public static java.nio.DoubleBuffer to2x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer to2x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.to2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.to2x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.to2x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer to2x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset + 0, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self01);
        dest.put(destOffset + 3, _self11);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer to3x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer to3x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.to3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.to3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.to3x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer to3x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset + 0, _self00);
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
        long _lowerBase = UnsafeOpsHolder.U.getLong(lower, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) lowerOffset * 8L;
        long _diagonalBase = UnsafeOpsHolder.U.getLong(diagonal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) diagonalOffset * 8L;
        long _upperBase = UnsafeOpsHolder.U.getLong(upper, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) upperOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.decomposeLDU_unsafe(_lowerBase, _diagonalBase, _upperBase, _srcBase);
        return lower;
    }

    public static java.nio.DoubleBuffer decomposeLDU_api(java.nio.DoubleBuffer lower, int lowerOffset, java.nio.DoubleBuffer diagonal, int diagonalOffset, java.nio.DoubleBuffer upper, int upperOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (lower.hasArray() && lowerOffset >= 0 && lowerOffset <= lower.limit() - 4 && diagonal.hasArray() && diagonalOffset >= 0 && diagonalOffset <= diagonal.limit() - 4 && upper.hasArray() && upperOffset >= 0 && upperOffset <= upper.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.decomposeLDU(lower.array(), lower.arrayOffset() + lowerOffset, diagonal.array(), diagonal.arrayOffset() + diagonalOffset, upper.array(), upper.arrayOffset() + upperOffset, src.array(), src.arrayOffset() + srcOffset);
            return lower;
        }
        if (lower.order() == java.nio.ByteOrder.nativeOrder() && !lower.isReadOnly() && diagonal.order() == java.nio.ByteOrder.nativeOrder() && !diagonal.isReadOnly() && upper.order() == java.nio.ByteOrder.nativeOrder() && !upper.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.decomposeLDU_api(java.lang.foreign.MemorySegment.ofBuffer(lower.duplicate().position(0)), (long) lowerOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(diagonal.duplicate().position(0)), (long) diagonalOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(upper.duplicate().position(0)), (long) upperOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return lower;
        }
        Double2x2OpsKernelsTypedBuffer.decomposeLDU_apiGet(lower, lowerOffset, diagonal, diagonalOffset, upper, upperOffset, src, srcOffset);
        return lower;
    }

    public static java.nio.DoubleBuffer decomposeLDU_apiGet(java.nio.DoubleBuffer lower, int lowerOffset, java.nio.DoubleBuffer diagonal, int diagonalOffset, java.nio.DoubleBuffer upper, int upperOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _rcp0 = 1.0 / _self00;
        double _sp0 = _self10 * _rcp0;
        lower.put(lowerOffset + 0, 1.0);
        lower.put(lowerOffset + 1, _sp0);
        lower.put(lowerOffset + 2, 0.0);
        lower.put(lowerOffset + 3, 1.0);
        diagonal.put(diagonalOffset + 0, _self00);
        diagonal.put(diagonalOffset + 1, 0.0);
        diagonal.put(diagonalOffset + 2, 0.0);
        diagonal.put(diagonalOffset + 3, _self11 - _self01 * _sp0);
        upper.put(upperOffset + 0, 1.0);
        upper.put(upperOffset + 1, 0.0);
        upper.put(upperOffset + 2, _self01 * _rcp0);
        upper.put(upperOffset + 3, 1.0);
        return lower;
    }

    public static java.nio.DoubleBuffer makeIdentity_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double2x2OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeIdentity_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double2x2Ops.makeIdentity(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double2x2OpsKernelsSegment.makeIdentity_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeIdentity_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeIdentity_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2x2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L, t);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset + 0);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        dest.put(destOffset + 0, Math.fma(t, _other00 - _self00, _self00));
        dest.put(destOffset + 1, Math.fma(t, _other10 - _self10, _self10));
        dest.put(destOffset + 2, Math.fma(t, _other01 - _self01, _self01));
        dest.put(destOffset + 3, Math.fma(t, _other11 - _self11, _self11));
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rightOffset * 8L;
        Double2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 4) {
            Double2x2Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && right.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(right.duplicate().position(0)), (long) rightOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _right00 = right.get(rightOffset + 0);
        double _right10 = right.get(rightOffset + 1);
        double _right01 = right.get(rightOffset + 2);
        double _right11 = right.get(rightOffset + 3);
        dest.put(destOffset + 0, Math.fma(_right00, _self00, _right10 * _self01));
        dest.put(destOffset + 1, Math.fma(_right00, _self10, _right10 * _self11));
        dest.put(destOffset + 2, Math.fma(_right01, _self00, _right11 * _self01));
        dest.put(destOffset + 3, Math.fma(_right01, _self10, _right11 * _self11));
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2x2OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.preMul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset + 0);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        dest.put(destOffset + 0, Math.fma(_other00, _self00, _other01 * _self10));
        dest.put(destOffset + 1, Math.fma(_other10, _self00, _other11 * _self10));
        dest.put(destOffset + 2, Math.fma(_other00, _self01, _other01 * _self11));
        dest.put(destOffset + 3, Math.fma(_other10, _self01, _other11 * _self11));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2x2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double2x2Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, weight);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L, weight);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _other00 = other.get(otherOffset + 0);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        dest.put(destOffset + 0, Math.fma(weight, _other00, _self00));
        dest.put(destOffset + 1, Math.fma(weight, _other10, _self10));
        dest.put(destOffset + 2, Math.fma(weight, _other01, _self01));
        dest.put(destOffset + 3, Math.fma(weight, _other11, _self11));
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, double colX, double colY, double rowX, double rowY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, rowX, rowY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_api(java.nio.DoubleBuffer dest, int destOffset, double colX, double colY, double rowX, double rowY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double2x2Ops.makeOuterProduct(dest.array(), dest.arrayOffset() + destOffset, colX, colY, rowX, rowY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double2x2OpsKernelsSegment.makeOuterProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, colX, colY, rowX, rowY);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeOuterProduct_apiGet(dest, destOffset, colX, colY, rowX, rowY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, double colX, double colY, double rowX, double rowY) {
        dest.put(destOffset + 0, colX * rowX);
        dest.put(destOffset + 1, colY * rowX);
        dest.put(destOffset + 2, colX * rowY);
        dest.put(destOffset + 3, colY * rowY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer col, int colOffset, java.nio.DoubleBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _colBase = UnsafeOpsHolder.U.getLong(col, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) colOffset * 8L;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rowOffset * 8L;
        Double2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer col, int colOffset, java.nio.DoubleBuffer row, int rowOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && col.hasArray() && colOffset >= 0 && colOffset <= col.limit() - 2 && row.hasArray() && rowOffset >= 0 && rowOffset <= row.limit() - 2) {
            Double2x2Ops.makeOuterProduct(dest.array(), dest.arrayOffset() + destOffset, col.array(), col.arrayOffset() + colOffset, row.array(), row.arrayOffset() + rowOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && col.order() == java.nio.ByteOrder.nativeOrder() && row.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.makeOuterProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(col.duplicate().position(0)), (long) colOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(row.duplicate().position(0)), (long) rowOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeOuterProduct_apiGet(dest, destOffset, col, colOffset, row, rowOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeOuterProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer col, int colOffset, java.nio.DoubleBuffer row, int rowOffset) {
        double _colx = col.get(colOffset + 0);
        double _coly = col.get(colOffset + 1);
        double _rowx = row.get(rowOffset + 0);
        double _rowy = row.get(rowOffset + 1);
        dest.put(destOffset + 0, _colx * _rowx);
        dest.put(destOffset + 1, _coly * _rowx);
        dest.put(destOffset + 2, _colx * _rowy);
        dest.put(destOffset + 3, _coly * _rowy);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotation_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double2x2OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotation_api(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double2x2Ops.makeRotation(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double2x2OpsKernelsSegment.makeRotation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, angle);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeRotation_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotation_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _t1);
        dest.put(destOffset + 1, _t0);
        dest.put(destOffset + 2, -_t0);
        dest.put(destOffset + 3, _t1);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_unsafe(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_api(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double2x2Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double2x2OpsKernelsSegment.makeScaling_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, vX, vY);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_apiGet(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        dest.put(destOffset + 0, vX);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double2x2Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.makeScaling_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, _vx);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, _vy);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_unsafe(java.nio.DoubleBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_api(java.nio.DoubleBuffer dest, int destOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double2x2Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double2x2OpsKernelsSegment.makeScaling_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, s);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_apiGet(java.nio.DoubleBuffer dest, int destOffset, double s) {
        dest.put(destOffset + 0, s);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.preRotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.preRotate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.preRotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.put(destOffset + 1, Math.fma(_self00, _t0, _self10 * _t1));
        dest.put(destOffset + 2, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self01, _t0, _self11 * _t1));
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.preScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, vX, vY);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset + 0, _self00 * vX);
        dest.put(destOffset + 1, _self10 * vY);
        dest.put(destOffset + 2, _self01 * vX);
        dest.put(destOffset + 3, _self11 * vY);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double2x2Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.preScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, _self00 * _vx);
        dest.put(destOffset + 1, _self10 * _vy);
        dest.put(destOffset + 2, _self01 * _vx);
        dest.put(destOffset + 3, _self11 * _vy);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.preScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, s);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset + 0, s * _self00);
        dest.put(destOffset + 1, s * _self10);
        dest.put(destOffset + 2, s * _self01);
        dest.put(destOffset + 3, s * _self11);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.rotate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_self00, _t1, _self01 * _t0));
        dest.put(destOffset + 1, Math.fma(_self10, _t1, _self11 * _t0));
        dest.put(destOffset + 2, Math.fma(_self01, _t1, -(_self00 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self11, _t1, -(_self10 * _t0)));
        return dest;
    }

    public static java.nio.DoubleBuffer scale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.scale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, vX, vY);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset + 0, _self00 * vX);
        dest.put(destOffset + 1, _self10 * vX);
        dest.put(destOffset + 2, _self01 * vY);
        dest.put(destOffset + 3, _self11 * vY);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double2x2Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.scale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, _self00 * _vx);
        dest.put(destOffset + 1, _self10 * _vx);
        dest.put(destOffset + 2, _self01 * _vy);
        dest.put(destOffset + 3, _self11 * _vy);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double2x2Ops.mulVec2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.mulVec2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, vX, vY);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.mulVec2_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.fma(_self00, vX, _self01 * vY));
        dest.put(destOffset + 1, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double2x2Ops.mulVec2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2x2OpsKernelsSegment.mulVec2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double2x2OpsKernelsTypedBuffer.mulVec2_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self00 = src.get(srcOffset + 0);
        double _self10 = src.get(srcOffset + 1);
        double _self01 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 3);
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, Math.fma(_self00, _vx, _self01 * _vy));
        dest.put(destOffset + 1, Math.fma(_self10, _vx, _self11 * _vy));
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
