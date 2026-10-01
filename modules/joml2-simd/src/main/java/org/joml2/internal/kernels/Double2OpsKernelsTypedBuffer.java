// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double2Ops} whose leading storage
 * parameter is a typed {@link java.nio.DoubleBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double2Ops} and its sibling kernel units. Not public API.
 */
public final class Double2OpsKernelsTypedBuffer {
    private Double2OpsKernelsTypedBuffer() {}

    public static java.nio.DoubleBuffer add_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer add_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.add_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, otherX, otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer add_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, otherX + _selfx);
        dest.put(destOffset + 1, otherY + _selfy);
        return dest;
    }

    public static java.nio.DoubleBuffer add_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer add_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Double2Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.add_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer add_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        dest.put(destOffset + 0, _otherx + _selfx);
        dest.put(destOffset + 1, _othery + _selfy);
        return dest;
    }

    public static java.nio.DoubleBuffer div_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer div_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.div_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, scalar);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer div_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, _selfx / scalar);
        dest.put(destOffset + 1, _selfy / scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer div_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer div_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.div_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, otherX, otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer div_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, _selfx / otherX);
        dest.put(destOffset + 1, _selfy / otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer div_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer div_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Double2Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.div_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer div_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        dest.put(destOffset + 0, _selfx / _otherx);
        dest.put(destOffset + 1, _selfy / _othery);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double b, double cX, double cY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double b, double cX, double cY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b, cX, cY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.fma_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, b, cX, cY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, cX, cY);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double b, double cX, double cY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.fma(_selfx, b, cX));
        dest.put(destOffset + 1, Math.fma(_selfy, b, cY));
        return dest;
    }

    public static java.nio.DoubleBuffer fma_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer c, int cOffset, double b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) cOffset * 8L;
        Double2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer c, int cOffset, double b) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 2) {
            Double2Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, c.array(), c.arrayOffset() + cOffset, b);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && c.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.fma_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(c.duplicate().position(0)), (long) cOffset * 8L, b);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, c, cOffset, b);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer c, int cOffset, double b) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _cx = c.get(cOffset + 0);
        double _cy = c.get(cOffset + 1);
        dest.put(destOffset + 0, Math.fma(_selfx, b, _cx));
        dest.put(destOffset + 1, Math.fma(_selfy, b, _cy));
        return dest;
    }

    public static java.nio.DoubleBuffer fma_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double cX, double cY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, cX, cY);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double cX, double cY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, cX, cY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.fma_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, bX, bY, cX, cY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, bX, bY, cX, cY);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double cX, double cY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.fma(_selfx, bX, cX));
        dest.put(destOffset + 1, Math.fma(_selfy, bY, cY));
        return dest;
    }

    public static java.nio.DoubleBuffer fma_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) bOffset * 8L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) cOffset * 8L;
        Double2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 2 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 2) {
            Double2Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && b.order() == java.nio.ByteOrder.nativeOrder() && c.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.fma_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(b.duplicate().position(0)), (long) bOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(c.duplicate().position(0)), (long) cOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _bx = b.get(bOffset + 0);
        double _by = b.get(bOffset + 1);
        double _cx = c.get(cOffset + 0);
        double _cy = c.get(cOffset + 1);
        dest.put(destOffset + 0, Math.fma(_selfx, _bx, _cx));
        dest.put(destOffset + 1, Math.fma(_selfy, _by, _cy));
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, scalar);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, scalar * _selfx);
        dest.put(destOffset + 1, scalar * _selfy);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, otherX, otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, otherX * _selfx);
        dest.put(destOffset + 1, otherY * _selfy);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Double2Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        dest.put(destOffset + 0, _otherx * _selfx);
        dest.put(destOffset + 1, _othery * _selfy);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.negate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, -_selfx);
        dest.put(destOffset + 1, -_selfy);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.sub_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, otherX, otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, _selfx - otherX);
        dest.put(destOffset + 1, _selfy - otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Double2Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.sub_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        dest.put(destOffset + 0, _selfx - _otherx);
        dest.put(destOffset + 1, _selfy - _othery);
        return dest;
    }

    public static java.nio.DoubleBuffer makeUniformDirection_unsafe(java.nio.DoubleBuffer dest, int destOffset, double u) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double2OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u);
        return dest;
    }

    public static java.nio.DoubleBuffer makeUniformDirection_api(java.nio.DoubleBuffer dest, int destOffset, double u) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2) {
            Double2Ops.makeUniformDirection(dest.array(), dest.arrayOffset() + destOffset, u);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double2OpsKernelsSegment.makeUniformDirection_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, u);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.makeUniformDirection_apiGet(dest, destOffset, u);
        return dest;
    }

    public static java.nio.DoubleBuffer makeUniformDirection_apiGet(java.nio.DoubleBuffer dest, int destOffset, double u) {
        double _t0 = u * 6.283185307179586;
        double _t1 = Math.sin(_t0);
        dest.put(destOffset + 0, Math.cosFromSin(_t1, _t0));
        dest.put(destOffset + 1, _t1);
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double2OpsKernelsAddress.set_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2) {
            Double2Ops.set(dest.array(), dest.arrayOffset() + destOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double2OpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, vX, vY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, vX, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        dest.put(destOffset + 0, vX);
        dest.put(destOffset + 1, vY);
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 8L;
        Double2OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Double2Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vx = v.get(vOffset + 0);
        double _vy = v.get(vOffset + 1);
        dest.put(destOffset + 0, _vx);
        dest.put(destOffset + 1, _vy);
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double2OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2) {
            Double2Ops.set(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double2OpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, s);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, double s) {
        dest.put(destOffset + 0, s);
        dest.put(destOffset + 1, s);
        return dest;
    }

    public static java.nio.DoubleBuffer makeZero_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        Double2OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeZero_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2) {
            Double2Ops.makeZero(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double2OpsKernelsSegment.makeZero_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.makeZero_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeZero_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0);
        dest.put(destOffset + 1, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.bezier_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.put(destOffset + 0, Math.fma(p1X, _t7, _selfx * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        dest.put(destOffset + 1, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 8L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p3Offset * 8L;
        Double2OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 2 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 2 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 2) {
            Double2Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.bezier_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p3.duplicate().position(0)), (long) p3Offset * 8L, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _p1x = p1.get(p1Offset + 0);
        double _p1y = p1.get(p1Offset + 1);
        double _p2x = p2.get(p2Offset + 0);
        double _p2y = p2.get(p2Offset + 1);
        double _p3x = p3.get(p3Offset + 0);
        double _p3y = p3.get(p3Offset + 1);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.put(destOffset + 0, Math.fma(_p1x, _t7, _selfx * _t8) + Math.fma(_p2x, _t6, _p3x * _t2));
        dest.put(destOffset + 1, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p2X, p2Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.bezier2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, p1X, p1Y, p2X, p2Y, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.put(destOffset + 0, Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4)));
        dest.put(destOffset + 1, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 8L;
        Double2OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 2 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 2) {
            Double2Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.bezier2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 8L, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _p1x = p1.get(p1Offset + 0);
        double _p1y = p1.get(p1Offset + 1);
        double _p2x = p2.get(p2Offset + 0);
        double _p2y = p2.get(p2Offset + 1);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.put(destOffset + 0, Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4)));
        dest.put(destOffset + 1, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p2X, p2Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.bezier2Tangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, p1X, p1Y, p2X, p2Y, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.put(destOffset + 0, Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        dest.put(destOffset + 1, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 8L;
        Double2OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 2 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 2) {
            Double2Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.bezier2Tangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 8L, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _p1x = p1.get(p1Offset + 0);
        double _p1y = p1.get(p1Offset + 1);
        double _p2x = p2.get(p2Offset + 0);
        double _p2y = p2.get(p2Offset + 1);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.put(destOffset + 0, Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        dest.put(destOffset + 1, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.bezierTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.put(destOffset + 0, Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        dest.put(destOffset + 1, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 8L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p3Offset * 8L;
        Double2OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 2 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 2 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 2) {
            Double2Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.bezierTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p3.duplicate().position(0)), (long) p3Offset * 8L, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _p1x = p1.get(p1Offset + 0);
        double _p1y = p1.get(p1Offset + 1);
        double _p2x = p2.get(p2Offset + 0);
        double _p2y = p2.get(p2Offset + 1);
        double _p3x = p3.get(p3Offset + 0);
        double _p3y = p3.get(p3Offset + 1);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.put(destOffset + 0, Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        dest.put(destOffset + 1, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.catmullRom_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.put(destOffset + 0, 0.5 * (Math.fma(2.0, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        dest.put(destOffset + 1, 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 8L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p3Offset * 8L;
        Double2OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 2 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 2 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 2) {
            Double2Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.catmullRom_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p3.duplicate().position(0)), (long) p3Offset * 8L, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _p1x = p1.get(p1Offset + 0);
        double _p1y = p1.get(p1Offset + 1);
        double _p2x = p2.get(p2Offset + 0);
        double _p2y = p2.get(p2Offset + 1);
        double _p3x = p3.get(p3Offset + 0);
        double _p3y = p3.get(p3Offset + 1);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.put(destOffset + 0, 0.5 * (Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), _t0, Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        dest.put(destOffset + 1, 0.5 * (Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), _t0, Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.catmullRomTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0 = t * t;
        dest.put(destOffset + 0, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.put(destOffset + 1, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 8L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p3Offset * 8L;
        Double2OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 2 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 2 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 2) {
            Double2Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.catmullRomTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(p3.duplicate().position(0)), (long) p3Offset * 8L, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _p1x = p1.get(p1Offset + 0);
        double _p1y = p1.get(p1Offset + 1);
        double _p2x = p2.get(p2Offset + 0);
        double _p2y = p2.get(p2Offset + 1);
        double _p3x = p3.get(p3Offset + 0);
        double _p3y = p3.get(p3Offset + 1);
        double _t0 = t * t;
        dest.put(destOffset + 0, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), Math.fma(3.0 * Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.put(destOffset + 1, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), Math.fma(3.0 * Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.hermite_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest.put(destOffset + 0, Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.put(destOffset + 1, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t0Offset * 8L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) v1Offset * 8L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t1Offset * 8L;
        Double2OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 2 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 2 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 2) {
            Double2Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.hermite_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(t0.duplicate().position(0)), (long) t0Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v1.duplicate().position(0)), (long) v1Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(t1.duplicate().position(0)), (long) t1Offset * 8L, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0x = t0.get(t0Offset + 0);
        double _t0y = t0.get(t0Offset + 1);
        double _v1x = v1.get(v1Offset + 0);
        double _v1y = v1.get(v1Offset + 1);
        double _t1x = t1.get(t1Offset + 0);
        double _t1y = t1.get(t1Offset + 1);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest.put(destOffset + 0, Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9));
        dest.put(destOffset + 1, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.hermiteTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest.put(destOffset + 0, Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.put(destOffset + 1, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t0Offset * 8L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) v1Offset * 8L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t1Offset * 8L;
        Double2OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 2 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 2 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 2) {
            Double2Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.hermiteTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(t0.duplicate().position(0)), (long) t0Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v1.duplicate().position(0)), (long) v1Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(t1.duplicate().position(0)), (long) t1Offset * 8L, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0x = t0.get(t0Offset + 0);
        double _t0y = t0.get(t0Offset + 1);
        double _v1x = v1.get(v1Offset + 0);
        double _v1y = v1.get(v1Offset + 1);
        double _t1x = t1.get(t1Offset + 0);
        double _t1y = t1.get(t1Offset + 1);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest.put(destOffset + 0, Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7));
        dest.put(destOffset + 1, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.fma(t, otherX - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(t, otherY - _selfy, _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Double2Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        dest.put(destOffset + 0, Math.fma(t, _otherx - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(t, _othery - _selfy, _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double tX, double tY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, tX, tY);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double tX, double tY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, tX, tY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY, tX, tY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, tX, tY);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double tX, double tY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.fma(tX, otherX - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(tY, otherY - _selfy, _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) tOffset * 8L;
        Double2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer t, int tOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2 && t.hasArray() && tOffset >= 0 && tOffset <= t.limit() - 2) {
            Double2Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t.array(), t.arrayOffset() + tOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder() && t.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(t.duplicate().position(0)), (long) tOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer t, int tOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        double _tx = t.get(tOffset + 0);
        double _ty = t.get(tOffset + 1);
        dest.put(destOffset + 0, Math.fma(_tx, _otherx - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(_ty, _othery - _selfy, _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.slerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 2.2250738585072014E-308 && _t5 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        double _t6 = Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 2.2250738585072014E-308 && _t6 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        double _t7 = (1.0 / Math.sqrt(_t5));
        double _t10 = (1.0 / Math.sqrt(_t6));
        double _t12 = _selfx * _t7;
        double _t16 = _selfy * _t7;
        double _t20 = t * Math.sqrt(_t6) + (1.0 - t) * Math.sqrt(_t5);
        double _t21 = Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        double _t26 = Math.fma(otherX, _t10, -(_t21 * _t12));
        double _t27 = Math.fma(otherY, _t10, -(_t21 * _t16));
        double _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        double _t31 = Math.fma(_t30, _t12, _t26);
        double _t32 = Math.fma(_t30, _t16, _t27);
        double _t35 = Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 5.048709793414476E-29 && _t35 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        double _t39 = t * Math.atan2(Math.sqrt(_t35), _t21);
        double _sp0 = _t20 * Math.sin(_t39) * (1.0 / Math.sqrt(_t35));
        double _t44 = _t20 * Math.cos(_t39);
        dest.put(destOffset + 0, Math.fma(_t12, _t44, _sp0 * _t31));
        dest.put(destOffset + 1, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return Double2OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, t);
    }

    public static java.nio.DoubleBuffer slerp_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.slerp_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t1 = unitScale(otherX, otherY, otherX);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = otherX * _t1;
        double _t8 = otherY * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t11_inv = 1.0 / _t11;
        double _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / Math.sqrt(_t18));
        double _t23 = (1.0 / Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t28 = -_t27;
        double _t37 = t * Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * Math.sqrt(_t19) * (_t11 / _t2);
        double _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        double _t48 = Math.fma(_t47, _t25, _t43);
        double _t49 = Math.fma(_t47, _t27, _t44);
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / Math.sqrt(_t60));
        double _t64 = t * Math.atan2(Math.sqrt(_t60), _t38 * _t51);
        double _t68 = _t37 * Math.sin(_t64);
        double _t69 = _t37 * Math.cos(_t64);
        double _t72, _t73;
        if (_t60 > 0.0) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 5.048709793414476E-29) {
                    dest.put(destOffset + 0, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    dest.put(destOffset + 1, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    dest.put(destOffset + 0, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    dest.put(destOffset + 1, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                dest.put(destOffset + 0, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                dest.put(destOffset + 1, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            dest.put(destOffset + 0, Math.fma(t, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, Math.fma(t, otherY - _selfy, _selfy));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Double2Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.slerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        double _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 2.2250738585072014E-308 && _t5 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t6 = Math.fma(_otherx, _otherx, _othery * _othery);
        if (!(_t6 > 2.2250738585072014E-308 && _t6 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t7 = (1.0 / Math.sqrt(_t5));
        double _t10 = (1.0 / Math.sqrt(_t6));
        double _t12 = _selfx * _t7;
        double _t16 = _selfy * _t7;
        double _t20 = t * Math.sqrt(_t6) + (1.0 - t) * Math.sqrt(_t5);
        double _t21 = Math.fma(_otherx * _t10, _t12, _othery * _t10 * _t16);
        double _t26 = Math.fma(_otherx, _t10, -(_t21 * _t12));
        double _t27 = Math.fma(_othery, _t10, -(_t21 * _t16));
        double _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        double _t31 = Math.fma(_t30, _t12, _t26);
        double _t32 = Math.fma(_t30, _t16, _t27);
        double _t35 = Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 5.048709793414476E-29 && _t35 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t39 = t * Math.atan2(Math.sqrt(_t35), _t21);
        double _sp0 = _t20 * Math.sin(_t39) * (1.0 / Math.sqrt(_t35));
        double _t44 = _t20 * Math.cos(_t39);
        dest.put(destOffset + 0, Math.fma(_t12, _t44, _sp0 * _t31));
        dest.put(destOffset + 1, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double2OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.DoubleBuffer slerp_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Double2OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.slerp_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L, t);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        double _t1 = unitScale(_otherx, _othery, _otherx);
        double _t2 = unitScale(_selfx, _selfy, _selfx);
        double _t7 = _otherx * _t1;
        double _t8 = _othery * _t1;
        double _t9 = _selfx * _t2;
        double _t10 = _selfy * _t2;
        double _t11 = Math.min(_t2, _t1);
        double _t11_inv = 1.0 / _t11;
        double _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        double _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        double _t22 = (1.0 / Math.sqrt(_t18));
        double _t23 = (1.0 / Math.sqrt(_t19));
        double _t25 = _t23 * _t9;
        double _t27 = _t23 * _t10;
        double _t28 = -_t27;
        double _t37 = t * Math.sqrt(_t18) * (_t11 / _t1) + (1.0 - t) * Math.sqrt(_t19) * (_t11 / _t2);
        double _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        double _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        double _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        double _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        double _t48 = Math.fma(_t47, _t25, _t43);
        double _t49 = Math.fma(_t47, _t27, _t44);
        double _t51 = unitScale(_t48, _t49, _t48);
        double _t57 = _t48 * _t51;
        double _t58 = _t49 * _t51;
        double _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        double _t62 = (1.0 / Math.sqrt(_t60));
        double _t64 = t * Math.atan2(Math.sqrt(_t60), _t38 * _t51);
        double _t68 = _t37 * Math.sin(_t64);
        double _t69 = _t37 * Math.cos(_t64);
        double _t72, _t73;
        if (_t60 > 0.0) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        if (_t18 * _t19 > 0.0) {
            if (_t38 < 0.0) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 5.048709793414476E-29) {
                    dest.put(destOffset + 0, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    dest.put(destOffset + 1, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    dest.put(destOffset + 0, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    dest.put(destOffset + 1, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                dest.put(destOffset + 0, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                dest.put(destOffset + 1, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            dest.put(destOffset + 0, Math.fma(t, _otherx - _selfx, _selfx));
            dest.put(destOffset + 1, Math.fma(t, _othery - _selfy, _selfy));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer absolute_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer absolute_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.absolute(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.absolute_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.absolute_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer absolute_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.abs(_selfx));
        dest.put(destOffset + 1, Math.abs(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer acos_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer acos_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.acos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.acos_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.acos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer acos_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.acos(_selfx));
        dest.put(destOffset + 1, Math.acos(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, bX, bY, scalar);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double scalar) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.fma(scalar, bX, _selfx));
        dest.put(destOffset + 1, Math.fma(scalar, bY, _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) bOffset * 8L;
        Double2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 2) {
            Double2Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && b.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(b.duplicate().position(0)), (long) bOffset * 8L, scalar);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, double scalar) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _bx = b.get(bOffset + 0);
        double _by = b.get(bOffset + 1);
        dest.put(destOffset + 0, Math.fma(scalar, _bx, _selfx));
        dest.put(destOffset + 1, Math.fma(scalar, _by, _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double cX, double cY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, cX, cY);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double cX, double cY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, cX, cY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, bX, bY, cX, cY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, cX, cY);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double cX, double cY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.fma(bX, cX, _selfx));
        dest.put(destOffset + 1, Math.fma(bY, cY, _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) bOffset * 8L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) cOffset * 8L;
        Double2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 2 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 2) {
            Double2Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && b.order() == java.nio.ByteOrder.nativeOrder() && c.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(b.duplicate().position(0)), (long) bOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(c.duplicate().position(0)), (long) cOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _bx = b.get(bOffset + 0);
        double _by = b.get(bOffset + 1);
        double _cx = c.get(cOffset + 0);
        double _cy = c.get(cOffset + 1);
        dest.put(destOffset + 0, Math.fma(_bx, _cx, _selfx));
        dest.put(destOffset + 1, Math.fma(_by, _cy, _selfy));
        return dest;
    }

    public static double angleBetween_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY);
    }

    public static double angleBetween_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.angleBetween_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
        }
        return Double2OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, otherX, otherY);
    }

    public static double angleBetween_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _ct0 = Math.abs(Math.fma(otherY, _selfx, -(otherX * _selfy)));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY);
        return Math.atan2(_ct0, Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static double angleBetween_degenerate(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY);
        return Double2OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY);
    }

    public static double angleBetween_degenerate_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY);
    }

    public static double angleBetween_degenerate_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.angleBetween_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
        }
        return Double2OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, otherX, otherY);
    }

    public static double angleBetween_degenerate_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = otherY * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = otherX * _t0;
        double _t9 = _selfy * _t1;
        double _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static double angleBetween_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        return Double2OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Double2Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.angleBetween_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        double _ct0 = Math.abs(Math.fma(_othery, _selfx, -(_otherx * _selfy)));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(_ct0, Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static double angleBetween_degenerate(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Double2OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_degenerate_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        return Double2OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_degenerate_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Double2OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.angleBetween_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_degenerate_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        double _t0 = unitScale(_otherx, _othery, _otherx);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = _othery * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = _otherx * _t0;
        double _t9 = _selfy * _t1;
        double _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static java.nio.DoubleBuffer asin_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer asin_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.asin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.asin_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.asin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer asin_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.asin(_selfx));
        dest.put(destOffset + 1, Math.asin(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer atan_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer atan_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.atan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.atan_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.atan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer atan_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.atan(_selfx));
        dest.put(destOffset + 1, Math.atan(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double x) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.atan2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, x);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double x) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.atan2(_selfx, x));
        dest.put(destOffset + 1, Math.atan2(_selfy, x));
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double xX, double xY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double xX, double xY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, xX, xY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.atan2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, xX, xY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, xX, xY);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double xX, double xY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.atan2(_selfx, xX));
        dest.put(destOffset + 1, Math.atan2(_selfy, xY));
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) xOffset * 8L;
        Double2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer x, int xOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && x.hasArray() && xOffset >= 0 && xOffset <= x.limit() - 2) {
            Double2Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x.array(), x.arrayOffset() + xOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && x.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.atan2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(x.duplicate().position(0)), (long) xOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x, xOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer x, int xOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _xx = x.get(xOffset + 0);
        double _xy = x.get(xOffset + 1);
        dest.put(destOffset + 0, Math.atan2(_selfx, _xx));
        dest.put(destOffset + 1, Math.atan2(_selfy, _xy));
        return dest;
    }

    public static java.nio.DoubleBuffer cbrt_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cbrt_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.cbrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.cbrt_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.cbrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cbrt_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.cbrt(_selfx));
        dest.put(destOffset + 1, Math.cbrt(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer ceil_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer ceil_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.ceil(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.ceil_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.ceil_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer ceil_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.ceil(_selfx));
        dest.put(destOffset + 1, Math.ceil(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double min, double max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double min, double max) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min, max);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.clamp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, min, max);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, max);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double min, double max) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.min(Math.max(_selfx, min), max));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, min), max));
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double maxX, double maxY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, maxX, maxY);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double maxX, double maxY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, minX, minY, maxX, maxY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.clamp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, minX, minY, maxX, maxY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, minX, minY, maxX, maxY);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double maxX, double maxY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.min(Math.max(_selfx, minX), maxX));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, minY), maxY));
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer min, int minOffset, java.nio.DoubleBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) minOffset * 8L;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) maxOffset * 8L;
        Double2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer min, int minOffset, java.nio.DoubleBuffer max, int maxOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && min.hasArray() && minOffset >= 0 && minOffset <= min.limit() - 2 && max.hasArray() && maxOffset >= 0 && maxOffset <= max.limit() - 2) {
            Double2Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min.array(), min.arrayOffset() + minOffset, max.array(), max.arrayOffset() + maxOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && min.order() == java.nio.ByteOrder.nativeOrder() && max.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.clamp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(min.duplicate().position(0)), (long) minOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(max.duplicate().position(0)), (long) maxOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer min, int minOffset, java.nio.DoubleBuffer max, int maxOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _minx = min.get(minOffset + 0);
        double _miny = min.get(minOffset + 1);
        double _maxx = max.get(maxOffset + 0);
        double _maxy = max.get(maxOffset + 1);
        dest.put(destOffset + 0, Math.min(Math.max(_selfx, _minx), _maxx));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, _miny), _maxy));
        return dest;
    }

    public static double compAdd_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static double compAdd_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.compAdd(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.compAdd_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.compAdd_apiGet(src, srcOffset);
    }

    public static double compAdd_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        return _selfx + _selfy;
    }

    public static double compMax_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static double compMax_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.compMax(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.compMax_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.compMax_apiGet(src, srcOffset);
    }

    public static double compMax_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        return Math.max(_selfx, _selfy);
    }

    public static double compMin_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static double compMin_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.compMin(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.compMin_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.compMin_apiGet(src, srcOffset);
    }

    public static double compMin_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        return Math.min(_selfx, _selfy);
    }

    public static double compMul_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static double compMul_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.compMul(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.compMul_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.compMul_apiGet(src, srcOffset);
    }

    public static double compMul_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        return _selfx * _selfy;
    }

    public static java.nio.DoubleBuffer copySign_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sign) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.copySign_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, sign);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sign) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.copySign(_selfx, sign));
        dest.put(destOffset + 1, Math.copySign(_selfy, sign));
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double signX, double signY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double signX, double signY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, signX, signY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.copySign_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, signX, signY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, signX, signY);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double signX, double signY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.copySign(_selfx, signX));
        dest.put(destOffset + 1, Math.copySign(_selfy, signY));
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) signOffset * 8L;
        Double2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer sign, int signOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && sign.hasArray() && signOffset >= 0 && signOffset <= sign.limit() - 2) {
            Double2Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign.array(), sign.arrayOffset() + signOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && sign.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.copySign_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(sign.duplicate().position(0)), (long) signOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign, signOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer sign, int signOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _signx = sign.get(signOffset + 0);
        double _signy = sign.get(signOffset + 1);
        dest.put(destOffset + 0, Math.copySign(_selfx, _signx));
        dest.put(destOffset + 1, Math.copySign(_selfy, _signy));
        return dest;
    }

    public static java.nio.DoubleBuffer cos_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cos_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.cos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.cos_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.cos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cos_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.cos(_selfx));
        dest.put(destOffset + 1, Math.cos(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer cosh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cosh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.cosh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.cosh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.cosh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cosh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.cosh(_selfx));
        dest.put(destOffset + 1, Math.cosh(_selfy));
        return dest;
    }

    public static double cross_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.cross_unsafe(_srcBase, otherX, otherY);
    }

    public static double cross_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.cross(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.cross_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
        }
        return Double2OpsKernelsTypedBuffer.cross_apiGet(src, srcOffset, otherX, otherY);
    }

    public static double cross_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        return Math.fma(otherY, _selfx, -(otherX * _selfy));
    }

    public static double cross_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        return Double2OpsKernelsAddress.cross_unsafe(_srcBase, _otherBase);
    }

    public static double cross_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Double2Ops.cross(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.cross_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.cross_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double cross_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        return Math.fma(_othery, _selfx, -(_otherx * _selfy));
    }

    public static java.nio.DoubleBuffer degrees_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer degrees_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.degrees(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.degrees_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.degrees_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer degrees_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.toDegrees(_selfx));
        dest.put(destOffset + 1, Math.toDegrees(_selfy));
        return dest;
    }

    public static double distance_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY);
    }

    public static double distance_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.distance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.distance_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
        }
        return Double2OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, otherX, otherY);
    }

    public static double distance_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0 = _selfx - otherX;
        double _t1 = _selfy - otherY;
        return Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static double distance_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        return Double2OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static double distance_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Double2Ops.distance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.distance_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double distance_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        double _t0 = _selfx - _otherx;
        double _t1 = _selfy - _othery;
        return Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static double distanceSquared_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY);
    }

    public static double distanceSquared_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.distanceSquared_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
        }
        return Double2OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, otherX, otherY);
    }

    public static double distanceSquared_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0 = _selfx - otherX;
        double _t1 = _selfy - otherY;
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static double distanceSquared_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        return Double2OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static double distanceSquared_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Double2Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.distanceSquared_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double distanceSquared_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        double _t0 = _selfx - _otherx;
        double _t1 = _selfy - _othery;
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static double dot_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY);
    }

    public static double dot_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.dot(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.dot_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
        }
        return Double2OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, otherX, otherY);
    }

    public static double dot_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        return Math.fma(otherX, _selfx, otherY * _selfy);
    }

    public static double dot_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        return Double2OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static double dot_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Double2Ops.dot(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.dot_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double dot_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        return Math.fma(_otherx, _selfx, _othery * _selfy);
    }

    public static java.nio.DoubleBuffer exp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer exp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.exp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.exp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.exp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer exp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.exp(_selfx));
        dest.put(destOffset + 1, Math.exp(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer exp2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer exp2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.exp2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.exp2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.exp2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer exp2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.pow(2.0, _selfx));
        dest.put(destOffset + 1, Math.pow(2.0, _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer expm1_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer expm1_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.expm1(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.expm1_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.expm1_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer expm1_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.expm1(_selfx));
        dest.put(destOffset + 1, Math.expm1(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double IX, double IY, double NrefX, double NrefY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, NrefX, NrefY);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double IX, double IY, double NrefX, double NrefY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, IX, IY, NrefX, NrefY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.faceforward_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, IX, IY, NrefX, NrefY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, IX, IY, NrefX, NrefY);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double IX, double IY, double NrefX, double NrefY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        if (Math.fma(IX, NrefX, IY * NrefY) < 0.0) {
            dest.put(destOffset + 0, _selfx);
            dest.put(destOffset + 1, _selfy);
        } else {
            dest.put(destOffset + 0, -_selfx);
            dest.put(destOffset + 1, -_selfy);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer I, int IOffset, java.nio.DoubleBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) IOffset * 8L;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) NrefOffset * 8L;
        Double2OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer I, int IOffset, java.nio.DoubleBuffer Nref, int NrefOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && I.hasArray() && IOffset >= 0 && IOffset <= I.limit() - 2 && Nref.hasArray() && NrefOffset >= 0 && NrefOffset <= Nref.limit() - 2) {
            Double2Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, I.array(), I.arrayOffset() + IOffset, Nref.array(), Nref.arrayOffset() + NrefOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && I.order() == java.nio.ByteOrder.nativeOrder() && Nref.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.faceforward_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(I.duplicate().position(0)), (long) IOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(Nref.duplicate().position(0)), (long) NrefOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer I, int IOffset, java.nio.DoubleBuffer Nref, int NrefOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _Ix = I.get(IOffset + 0);
        double _Iy = I.get(IOffset + 1);
        double _Nrefx = Nref.get(NrefOffset + 0);
        double _Nrefy = Nref.get(NrefOffset + 1);
        if (Math.fma(_Ix, _Nrefx, _Iy * _Nrefy) < 0.0) {
            dest.put(destOffset + 0, _selfx);
            dest.put(destOffset + 1, _selfy);
        } else {
            dest.put(destOffset + 0, -_selfx);
            dest.put(destOffset + 1, -_selfy);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer floor_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer floor_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.floor(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.floor_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.floor_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer floor_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.floor(_selfx));
        dest.put(destOffset + 1, Math.floor(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer fract_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer fract_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.fract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.fract_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.fract_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer fract_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        dest.put(destOffset + 1, Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.hypot_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, y);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.hypot(_selfx, y));
        dest.put(destOffset + 1, Math.hypot(_selfy, y));
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.hypot_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, yX, yY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, yX, yY);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.hypot(_selfx, yX));
        dest.put(destOffset + 1, Math.hypot(_selfy, yY));
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) yOffset * 8L;
        Double2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 2) {
            Double2Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && y.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.hypot_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(y.duplicate().position(0)), (long) yOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _yx = y.get(yOffset + 0);
        double _yy = y.get(yOffset + 1);
        dest.put(destOffset + 0, Math.hypot(_selfx, _yx));
        dest.put(destOffset + 1, Math.hypot(_selfy, _yy));
        return dest;
    }

    public static java.nio.DoubleBuffer inverse_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer inverse_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.inverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.inverse_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.inverse_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer inverse_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, 1.0 / _selfx);
        dest.put(destOffset + 1, 1.0 / _selfy);
        return dest;
    }

    public static java.nio.DoubleBuffer inverseSqrt_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer inverseSqrt_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.inverseSqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.inverseSqrt_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.inverseSqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer inverseSqrt_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, (1.0 / Math.sqrt(_selfx)));
        dest.put(destOffset + 1, (1.0 / Math.sqrt(_selfy)));
        return dest;
    }

    public static double length_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static double length_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.length(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.length_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.length_apiGet(src, srcOffset);
    }

    public static double length_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        return Math.sqrt(Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    public static double lengthSquared_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static double lengthSquared_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.lengthSquared(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.lengthSquared_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.lengthSquared_apiGet(src, srcOffset);
    }

    public static double lengthSquared_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        return Math.fma(_selfx, _selfx, _selfy * _selfy);
    }

    public static java.nio.DoubleBuffer log_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.log(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.log_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.log_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.log(_selfx));
        dest.put(destOffset + 1, Math.log(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer log10_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log10_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.log10(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.log10_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.log10_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log10_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.log10(_selfx));
        dest.put(destOffset + 1, Math.log10(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer log1p_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log1p_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.log1p(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.log1p_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.log1p_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log1p_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.log1p(_selfx));
        dest.put(destOffset + 1, Math.log1p(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer log2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.log2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.log2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.log2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.log2(_selfx));
        dest.put(destOffset + 1, Math.log2(_selfy));
        return dest;
    }

    public static double manhattanDistance_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY);
    }

    public static double manhattanDistance_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.manhattanDistance_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
        }
        return Double2OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, otherX, otherY);
    }

    public static double manhattanDistance_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        return Math.abs(_selfx - otherX) + Math.abs(_selfy - otherY);
    }

    public static double manhattanDistance_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        return Double2OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static double manhattanDistance_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Double2Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.manhattanDistance_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double manhattanDistance_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        return Math.abs(_selfx - _otherx) + Math.abs(_selfy - _othery);
    }

    public static double manhattanLength_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static double manhattanLength_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.manhattanLength(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.manhattanLength_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.manhattanLength_apiGet(src, srcOffset);
    }

    public static double manhattanLength_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        return Math.abs(_selfx) + Math.abs(_selfy);
    }

    public static java.nio.DoubleBuffer max_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer max_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.max_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, scalar);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer max_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.max(_selfx, scalar));
        dest.put(destOffset + 1, Math.max(_selfy, scalar));
        return dest;
    }

    public static java.nio.DoubleBuffer max_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer max_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.max_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, otherX, otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer max_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.max(_selfx, otherX));
        dest.put(destOffset + 1, Math.max(_selfy, otherY));
        return dest;
    }

    public static java.nio.DoubleBuffer max_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer max_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Double2Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.max_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer max_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        dest.put(destOffset + 0, Math.max(_selfx, _otherx));
        dest.put(destOffset + 1, Math.max(_selfy, _othery));
        return dest;
    }

    public static java.nio.DoubleBuffer min_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer min_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.min_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, scalar);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer min_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.min(_selfx, scalar));
        dest.put(destOffset + 1, Math.min(_selfy, scalar));
        return dest;
    }

    public static java.nio.DoubleBuffer min_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer min_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.min_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, otherX, otherY);
        return dest;
    }

    public static java.nio.DoubleBuffer min_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.min(_selfx, otherX));
        dest.put(destOffset + 1, Math.min(_selfy, otherY));
        return dest;
    }

    public static java.nio.DoubleBuffer min_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        Double2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer min_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Double2Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.min_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer min_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        dest.put(destOffset + 0, Math.min(_selfx, _otherx));
        dest.put(destOffset + 1, Math.min(_selfy, _othery));
        return dest;
    }

    public static java.nio.DoubleBuffer mod_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.mod_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, y);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, flooredMod(_selfx, y));
        dest.put(destOffset + 1, flooredMod(_selfy, y));
        return dest;
    }

    public static java.nio.DoubleBuffer mod_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.mod_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, yX, yY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, yX, yY);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, flooredMod(_selfx, yX));
        dest.put(destOffset + 1, flooredMod(_selfy, yY));
        return dest;
    }

    public static java.nio.DoubleBuffer mod_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) yOffset * 8L;
        Double2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 2) {
            Double2Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && y.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.mod_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(y.duplicate().position(0)), (long) yOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _yx = y.get(yOffset + 0);
        double _yy = y.get(yOffset + 1);
        dest.put(destOffset + 0, flooredMod(_selfx, _yx));
        dest.put(destOffset + 1, flooredMod(_selfy, _yy));
        return dest;
    }

    public static java.nio.DoubleBuffer nextDown_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer nextDown_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.nextDown(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.nextDown_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.nextDown_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer nextDown_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.nextDown(_selfx));
        dest.put(destOffset + 1, Math.nextDown(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer nextUp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer nextUp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.nextUp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.nextUp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.nextUp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer nextUp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.nextUp(_selfx));
        dest.put(destOffset + 1, Math.nextUp(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer normalize_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalize_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.normalize(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.normalize_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.normalize_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalize_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        double _t2 = (1.0 / Math.sqrt(_t1));
        if (_t1 != 0.0) {
            dest.put(destOffset + 0, _selfx * _t2);
            dest.put(destOffset + 1, _selfy * _t2);
        } else {
            dest.put(destOffset + 0, 0.0);
            dest.put(destOffset + 1, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer normalizeMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizeMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double length) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.normalizeMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, length);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.normalizeMul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, length);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.normalizeMul_apiGet(dest, destOffset, src, srcOffset, length);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizeMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double length) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        double _t3 = length * (1.0 / Math.sqrt(_t1));
        if (_t1 != 0.0) {
            dest.put(destOffset + 0, _selfx * _t3);
            dest.put(destOffset + 1, _selfy * _t3);
        } else {
            dest.put(destOffset + 0, 0.0);
            dest.put(destOffset + 1, 0.0);
        }
        return dest;
    }

    public static double orientedAngle_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.orientedAngle_unsafe(_srcBase, otherX, otherY);
    }

    public static double orientedAngle_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2Ops.orientedAngle(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.orientedAngle_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
        }
        return Double2OpsKernelsTypedBuffer.orientedAngle_apiGet(src, srcOffset, otherX, otherY);
    }

    public static double orientedAngle_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t2 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        double _ct0 = Math.abs(_t2);
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsTypedBuffer.orientedAngle_degenerate(src, srcOffset, otherX, otherY);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static double orientedAngle_degenerate(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2OpsKernelsTypedBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY);
        return Double2OpsKernelsTypedBuffer.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY);
    }

    public static double orientedAngle_degenerate_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        return Double2OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, otherX, otherY);
    }

    public static double orientedAngle_degenerate_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Double2OpsKernelsArray.orientedAngle_degenerate(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.orientedAngle_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, otherX, otherY);
        }
        return Double2OpsKernelsTypedBuffer.orientedAngle_degenerate_apiGet(src, srcOffset, otherX, otherY);
    }

    public static double orientedAngle_degenerate_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0 = unitScale(otherX, otherY, otherX);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = otherY * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = otherX * _t0;
        double _t9 = _selfy * _t1;
        double _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t15 = unitScale(_t14, _t14, _t14);
        double _t19 = _t14 * _t15;
        double _t21 = Math.atan2(Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0 ? -_t21 : _t21;
    }

    public static double orientedAngle_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        return Double2OpsKernelsAddress.orientedAngle_unsafe(_srcBase, _otherBase);
    }

    public static double orientedAngle_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Double2Ops.orientedAngle(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.orientedAngle_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.orientedAngle_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double orientedAngle_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        double _t2 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        double _ct0 = Math.abs(_t2);
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double2OpsKernelsTypedBuffer.orientedAngle_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static double orientedAngle_degenerate(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2OpsKernelsTypedBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Double2OpsKernelsTypedBuffer.orientedAngle_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static double orientedAngle_degenerate_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 8L;
        return Double2OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static double orientedAngle_degenerate_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Double2OpsKernelsArray.orientedAngle_degenerate(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double2OpsKernelsSegment.orientedAngle_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 8L);
        }
        return Double2OpsKernelsTypedBuffer.orientedAngle_degenerate_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double orientedAngle_degenerate_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _otherx = other.get(otherOffset + 0);
        double _othery = other.get(otherOffset + 1);
        double _t0 = unitScale(_otherx, _othery, _otherx);
        double _t1 = unitScale(_selfx, _selfy, _selfx);
        double _t6 = _othery * _t0;
        double _t7 = _selfx * _t1;
        double _t8 = _otherx * _t0;
        double _t9 = _selfy * _t1;
        double _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        double _t15 = unitScale(_t14, _t14, _t14);
        double _t19 = _t14 * _t15;
        double _t21 = Math.atan2(Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0 ? -_t21 : _t21;
    }

    public static java.nio.DoubleBuffer outerProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rowX, double rowY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rowX, double rowY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rowX, rowY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.outerProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, rowX, rowY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, rowX, rowY);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rowX, double rowY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, rowX * _selfx);
        dest.put(destOffset + 1, rowX * _selfy);
        dest.put(destOffset + 2, rowY * _selfx);
        dest.put(destOffset + 3, rowY * _selfy);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rowOffset * 8L;
        Double2OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer row, int rowOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && row.hasArray() && rowOffset >= 0 && rowOffset <= row.limit() - 2) {
            Double2Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row.array(), row.arrayOffset() + rowOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && row.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.outerProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(row.duplicate().position(0)), (long) rowOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, row, rowOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer row, int rowOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _rowx = row.get(rowOffset + 0);
        double _rowy = row.get(rowOffset + 1);
        dest.put(destOffset + 0, _rowx * _selfx);
        dest.put(destOffset + 1, _rowx * _selfy);
        dest.put(destOffset + 2, _rowy * _selfx);
        dest.put(destOffset + 3, _rowy * _selfy);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponent) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.pow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, exponent);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponent) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.pow(_selfx, exponent));
        dest.put(destOffset + 1, Math.pow(_selfy, exponent));
        return dest;
    }

    public static java.nio.DoubleBuffer pow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponentX, double exponentY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponentX, double exponentY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponentX, exponentY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.pow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, exponentX, exponentY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponentX, exponentY);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponentX, double exponentY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.pow(_selfx, exponentX));
        dest.put(destOffset + 1, Math.pow(_selfy, exponentY));
        return dest;
    }

    public static java.nio.DoubleBuffer pow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) exponentOffset * 8L;
        Double2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer exponent, int exponentOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && exponent.hasArray() && exponentOffset >= 0 && exponentOffset <= exponent.limit() - 2) {
            Double2Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent.array(), exponent.arrayOffset() + exponentOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && exponent.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.pow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(exponent.duplicate().position(0)), (long) exponentOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer exponent, int exponentOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _exponentx = exponent.get(exponentOffset + 0);
        double _exponenty = exponent.get(exponentOffset + 1);
        dest.put(destOffset + 0, Math.pow(_selfx, _exponentx));
        dest.put(destOffset + 1, Math.pow(_selfy, _exponenty));
        return dest;
    }

    public static java.nio.DoubleBuffer project_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double ontoX, double ontoY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY);
        return dest;
    }

    public static java.nio.DoubleBuffer project_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double ontoX, double ontoY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, ontoX, ontoY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.project_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, ontoX, ontoY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, ontoX, ontoY);
        return dest;
    }

    public static java.nio.DoubleBuffer project_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double ontoX, double ontoY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t5 = Math.fma(ontoX, _selfx, ontoY * _selfy) / Math.fma(ontoX, ontoX, ontoY * ontoY);
        dest.put(destOffset + 0, ontoX * _t5);
        dest.put(destOffset + 1, ontoY * _t5);
        return dest;
    }

    public static java.nio.DoubleBuffer project_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) ontoOffset * 8L;
        Double2OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.DoubleBuffer project_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer onto, int ontoOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && onto.hasArray() && ontoOffset >= 0 && ontoOffset <= onto.limit() - 2) {
            Double2Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, onto.array(), onto.arrayOffset() + ontoOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && onto.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.project_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(onto.duplicate().position(0)), (long) ontoOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer project_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer onto, int ontoOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _ontox = onto.get(ontoOffset + 0);
        double _ontoy = onto.get(ontoOffset + 1);
        double _t5 = Math.fma(_ontox, _selfx, _ontoy * _selfy) / Math.fma(_ontox, _ontox, _ontoy * _ontoy);
        dest.put(destOffset + 0, _ontox * _t5);
        dest.put(destOffset + 1, _ontoy * _t5);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.projectOnPlane_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, normalX, normalY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normalX, normalY);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t1 = Math.fma(normalX, _selfx, normalY * _selfy);
        dest.put(destOffset + 0, Math.fma(-normalX, _t1, _selfx));
        dest.put(destOffset + 1, Math.fma(-normalY, _t1, _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 8L;
        Double2OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 2) {
            Double2Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.projectOnPlane_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(normal.duplicate().position(0)), (long) normalOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _normalx = normal.get(normalOffset + 0);
        double _normaly = normal.get(normalOffset + 1);
        double _t1 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        dest.put(destOffset + 0, Math.fma(-_normalx, _t1, _selfx));
        dest.put(destOffset + 1, Math.fma(-_normaly, _t1, _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer radians_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer radians_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.radians(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.radians_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.radians_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer radians_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.toRadians(_selfx));
        dest.put(destOffset + 1, Math.toRadians(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.reflect_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, normalX, normalY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normalX, normalY);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t2 = 2.0 * Math.fma(normalX, _selfx, normalY * _selfy);
        dest.put(destOffset + 0, Math.fma(-normalX, _t2, _selfx));
        dest.put(destOffset + 1, Math.fma(-normalY, _t2, _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 8L;
        Double2OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 2) {
            Double2Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.reflect_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(normal.duplicate().position(0)), (long) normalOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _normalx = normal.get(normalOffset + 0);
        double _normaly = normal.get(normalOffset + 1);
        double _t2 = 2.0 * Math.fma(_normalx, _selfx, _normaly * _selfy);
        dest.put(destOffset + 0, Math.fma(-_normalx, _t2, _selfx));
        dest.put(destOffset + 1, Math.fma(-_normaly, _t2, _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer refract_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, eta);
        return dest;
    }

    public static java.nio.DoubleBuffer refract_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, eta);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.refract_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, normalX, normalY, eta);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, eta);
        return dest;
    }

    public static java.nio.DoubleBuffer refract_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double eta) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t2 = Math.fma(normalX, _selfx, normalY * _selfy);
        double _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0), eta * eta, 1.0);
        double _t9 = Math.fma(eta, _t2, Math.sqrt(Math.max(0.0, _t6)));
        if (_t6 >= 0.0) {
            dest.put(destOffset + 0, Math.fma(eta, _selfx, -(normalX * _t9)));
            dest.put(destOffset + 1, Math.fma(eta, _selfy, -(normalY * _t9)));
        } else {
            dest.put(destOffset + 0, 0.0);
            dest.put(destOffset + 1, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer refract_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset, double eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 8L;
        Double2OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.DoubleBuffer refract_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset, double eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 2) {
            Double2Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset, eta);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.refract_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(normal.duplicate().position(0)), (long) normalOffset * 8L, eta);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return dest;
    }

    public static java.nio.DoubleBuffer refract_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset, double eta) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _normalx = normal.get(normalOffset + 0);
        double _normaly = normal.get(normalOffset + 1);
        double _t2 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        double _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0), eta * eta, 1.0);
        double _t9 = Math.fma(eta, _t2, Math.sqrt(Math.max(0.0, _t6)));
        if (_t6 >= 0.0) {
            dest.put(destOffset + 0, Math.fma(eta, _selfx, -(_normalx * _t9)));
            dest.put(destOffset + 1, Math.fma(eta, _selfy, -(_normaly * _t9)));
        } else {
            dest.put(destOffset + 0, 0.0);
            dest.put(destOffset + 1, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer round_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer round_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.round(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.round_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.round_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer round_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.rint(_selfx));
        dest.put(destOffset + 1, Math.rint(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer sign_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sign_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.sign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.sign_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.sign_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sign_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.signum(_selfx));
        dest.put(destOffset + 1, Math.signum(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer sin_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sin_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.sin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.sin_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.sin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sin_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.sin(_selfx));
        dest.put(destOffset + 1, Math.sin(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer sinh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sinh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.sinh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.sinh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.sinh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sinh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.sinh(_selfx));
        dest.put(destOffset + 1, Math.sinh(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0, double edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0, double edge1) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0, edge1);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.smoothstep_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, edge0, edge1);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge1);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0, double edge1) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t7 = Math.max(0.0, Math.min(1.0, (_selfx - edge0) * _t0_inv));
        double _t8 = Math.max(0.0, Math.min(1.0, (_selfy - edge0) * _t0_inv));
        dest.put(destOffset + 0, Math.fma(-2.0, _t7, 3.0) * _t7 * _t7);
        dest.put(destOffset + 1, Math.fma(-2.0, _t8, 3.0) * _t8 * _t8);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0X, double edge0Y, double edge1X, double edge1Y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge1X, edge1Y);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0X, double edge0Y, double edge1X, double edge1Y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0X, edge0Y, edge1X, edge1Y);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.smoothstep_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, edge0X, edge0Y, edge1X, edge1Y);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge1X, edge1Y);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0X, double edge0Y, double edge1X, double edge1Y) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t8 = Math.max(0.0, Math.min(1.0, (_selfx - edge0X) / (edge1X - edge0X)));
        double _t9 = Math.max(0.0, Math.min(1.0, (_selfy - edge0Y) / (edge1Y - edge0Y)));
        dest.put(destOffset + 0, Math.fma(-2.0, _t8, 3.0) * _t8 * _t8);
        dest.put(destOffset + 1, Math.fma(-2.0, _t9, 3.0) * _t9 * _t9);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge0, int edge0Offset, java.nio.DoubleBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) edge0Offset * 8L;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) edge1Offset * 8L;
        Double2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge0, int edge0Offset, java.nio.DoubleBuffer edge1, int edge1Offset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && edge0.hasArray() && edge0Offset >= 0 && edge0Offset <= edge0.limit() - 2 && edge1.hasArray() && edge1Offset >= 0 && edge1Offset <= edge1.limit() - 2) {
            Double2Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0.array(), edge0.arrayOffset() + edge0Offset, edge1.array(), edge1.arrayOffset() + edge1Offset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && edge0.order() == java.nio.ByteOrder.nativeOrder() && edge1.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.smoothstep_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(edge0.duplicate().position(0)), (long) edge0Offset * 8L, java.lang.foreign.MemorySegment.ofBuffer(edge1.duplicate().position(0)), (long) edge1Offset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge0, int edge0Offset, java.nio.DoubleBuffer edge1, int edge1Offset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _edge0x = edge0.get(edge0Offset + 0);
        double _edge0y = edge0.get(edge0Offset + 1);
        double _edge1x = edge1.get(edge1Offset + 0);
        double _edge1y = edge1.get(edge1Offset + 1);
        double _t8 = Math.max(0.0, Math.min(1.0, (_selfx - _edge0x) / (_edge1x - _edge0x)));
        double _t9 = Math.max(0.0, Math.min(1.0, (_selfy - _edge0y) / (_edge1y - _edge0y)));
        dest.put(destOffset + 0, Math.fma(-2.0, _t8, 3.0) * _t8 * _t8);
        dest.put(destOffset + 1, Math.fma(-2.0, _t9, 3.0) * _t9 * _t9);
        return dest;
    }

    public static java.nio.DoubleBuffer sqrt_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sqrt_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.sqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.sqrt_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.sqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sqrt_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.sqrt(_selfx));
        dest.put(destOffset + 1, Math.sqrt(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer step_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.DoubleBuffer step_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.step_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, edge);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge);
        return dest;
    }

    public static java.nio.DoubleBuffer step_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, _selfx < edge ? 0.0 : 1.0);
        dest.put(destOffset + 1, _selfy < edge ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer step_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edgeX, double edgeY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY);
        return dest;
    }

    public static java.nio.DoubleBuffer step_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edgeX, double edgeY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edgeX, edgeY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.step_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, edgeX, edgeY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edgeX, edgeY);
        return dest;
    }

    public static java.nio.DoubleBuffer step_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edgeX, double edgeY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, _selfx < edgeX ? 0.0 : 1.0);
        dest.put(destOffset + 1, _selfy < edgeY ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer step_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) edgeOffset * 8L;
        Double2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.DoubleBuffer step_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge, int edgeOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && edge.hasArray() && edgeOffset >= 0 && edgeOffset <= edge.limit() - 2) {
            Double2Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge.array(), edge.arrayOffset() + edgeOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && edge.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.step_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(edge.duplicate().position(0)), (long) edgeOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer step_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge, int edgeOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _edgex = edge.get(edgeOffset + 0);
        double _edgey = edge.get(edgeOffset + 1);
        dest.put(destOffset + 0, _selfx < _edgex ? 0.0 : 1.0);
        dest.put(destOffset + 1, _selfy < _edgey ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer tan_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer tan_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.tan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.tan_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.tan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer tan_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.tan(_selfx));
        dest.put(destOffset + 1, Math.tan(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer tanh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer tanh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.tanh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.tanh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.tanh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer tanh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.tanh(_selfx));
        dest.put(destOffset + 1, Math.tanh(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer trunc_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer trunc_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.trunc(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.trunc_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.trunc_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer trunc_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.put(destOffset + 1, _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer ulp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer ulp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.ulp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.ulp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.ulp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer ulp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        dest.put(destOffset + 0, Math.ulp(_selfx));
        dest.put(destOffset + 1, Math.ulp(_selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 8L;
        Double2OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 4) {
            Double2Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.preMul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), (long) matOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _mat00 = mat.get(matOffset + 0);
        double _mat10 = mat.get(matOffset + 1);
        double _mat01 = mat.get(matOffset + 2);
        double _mat11 = mat.get(matOffset + 3);
        dest.put(destOffset + 0, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer preMulDirectionMat2x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 8L;
        Double2OpsKernelsAddress.preMulDirectionMat2x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulDirectionMat2x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 6) {
            Double2Ops.preMulDirectionMat2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.preMulDirectionMat2x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), (long) matOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.preMulDirectionMat2x3_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulDirectionMat2x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _mat00 = mat.get(matOffset + 0);
        double _mat10 = mat.get(matOffset + 1);
        double _mat01 = mat.get(matOffset + 2);
        double _mat11 = mat.get(matOffset + 3);
        dest.put(destOffset + 0, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer preMulDirectionMat3x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 8L;
        Double2OpsKernelsAddress.preMulDirectionMat3x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulDirectionMat3x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 9) {
            Double2Ops.preMulDirectionMat3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.preMulDirectionMat3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), (long) matOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.preMulDirectionMat3x3_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulDirectionMat3x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _mat00 = mat.get(matOffset + 0);
        double _mat10 = mat.get(matOffset + 1);
        double _mat01 = mat.get(matOffset + 3);
        double _mat11 = mat.get(matOffset + 4);
        dest.put(destOffset + 0, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat4x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 8L;
        Double2OpsKernelsAddress.preMulPositionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat4x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 16) {
            Double2Ops.preMulPositionMat4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.preMulPositionMat4x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), (long) matOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.preMulPositionMat4x4_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat4x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _mat00 = mat.get(matOffset + 0);
        double _mat10 = mat.get(matOffset + 1);
        double _mat01 = mat.get(matOffset + 4);
        double _mat11 = mat.get(matOffset + 5);
        double _mat03 = mat.get(matOffset + 12);
        double _mat13 = mat.get(matOffset + 13);
        dest.put(destOffset + 0, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat03)));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat13)));
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat2x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 8L;
        Double2OpsKernelsAddress.preMulPositionMat2x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat2x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 6) {
            Double2Ops.preMulPositionMat2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.preMulPositionMat2x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), (long) matOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.preMulPositionMat2x3_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat2x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _mat00 = mat.get(matOffset + 0);
        double _mat10 = mat.get(matOffset + 1);
        double _mat01 = mat.get(matOffset + 2);
        double _mat11 = mat.get(matOffset + 3);
        double _mat02 = mat.get(matOffset + 4);
        double _mat12 = mat.get(matOffset + 5);
        dest.put(destOffset + 0, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat02)));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat3x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 8L;
        Double2OpsKernelsAddress.preMulPositionMat3x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat3x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 9) {
            Double2Ops.preMulPositionMat3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.preMulPositionMat3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), (long) matOffset * 8L);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.preMulPositionMat3x3_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat3x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _mat00 = mat.get(matOffset + 0);
        double _mat10 = mat.get(matOffset + 1);
        double _mat01 = mat.get(matOffset + 3);
        double _mat11 = mat.get(matOffset + 4);
        double _mat02 = mat.get(matOffset + 6);
        double _mat12 = mat.get(matOffset + 7);
        dest.put(destOffset + 0, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, _mat02)));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.rotate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.put(destOffset + 1, Math.fma(_selfx, _t0, _selfy * _t1));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        Double2OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Double2Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.rotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, angle, pivotX, pivotY);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _selfx - pivotX;
        double _t3 = _selfy - pivotY;
        dest.put(destOffset + 0, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)));
        dest.put(destOffset + 1, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 8L;
        Double2OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Double2Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Double2OpsKernelsSegment.rotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), (long) pivotOffset * 8L, angle);
            return dest;
        }
        Double2OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        double _selfx = src.get(srcOffset + 0);
        double _selfy = src.get(srcOffset + 1);
        double _pivotx = pivot.get(pivotOffset + 0);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _selfx - _pivotx;
        double _t3 = _selfy - _pivoty;
        dest.put(destOffset + 0, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx)));
        dest.put(destOffset + 1, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivoty)));
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

    /**
     * The floored remainder of x and y, exactly kotlin.Float.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
    private static float flooredMod(float x, float y) {
        float q = (float) Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p24f && java.lang.Math.abs(y) <= Float.MAX_VALUE) {
            float r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0f), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        float r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }

    /** Double-precision twin of {@link #flooredMod(float, float)}. */
    private static double flooredMod(double x, double y) {
        double q = Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p53 && java.lang.Math.abs(y) <= Double.MAX_VALUE) {
            double r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        double r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }
}
