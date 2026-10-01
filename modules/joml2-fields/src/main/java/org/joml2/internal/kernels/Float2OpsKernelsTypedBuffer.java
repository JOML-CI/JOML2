// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2Ops} whose leading storage
 * parameter is a typed {@link java.nio.FloatBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float2Ops} and its sibling kernel units. Not public API.
 */
public final class Float2OpsKernelsTypedBuffer {
    private Float2OpsKernelsTypedBuffer() {}

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.add_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, otherX, otherY);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, otherX + src.get(srcOffset));
        dest.put(destOffset + 1, otherY + _selfy);
        return dest;
    }

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Float2Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.add_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _othery = other.get(otherOffset + 1);
        dest.put(destOffset, other.get(otherOffset) + src.get(srcOffset));
        dest.put(destOffset + 1, _othery + _selfy);
        return dest;
    }

    public static java.nio.FloatBuffer div_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer div_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.div_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, scalar);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer div_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, src.get(srcOffset) / scalar);
        dest.put(destOffset + 1, _selfy / scalar);
        return dest;
    }

    public static java.nio.FloatBuffer div_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.FloatBuffer div_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.div_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, otherX, otherY);
        return dest;
    }

    public static java.nio.FloatBuffer div_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, src.get(srcOffset) / otherX);
        dest.put(destOffset + 1, _selfy / otherY);
        return dest;
    }

    public static java.nio.FloatBuffer div_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer div_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Float2Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.div_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer div_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _othery = other.get(otherOffset + 1);
        dest.put(destOffset, src.get(srcOffset) / other.get(otherOffset));
        dest.put(destOffset + 1, _selfy / _othery);
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b, cX, cY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.fma_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, b, cX, cY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, cX, cY);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.fma(src.get(srcOffset), b, cX));
        dest.put(destOffset + 1, Math.fma(_selfy, b, cY));
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset * 4L;
        Float2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 2) {
            Float2Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, c.array(), c.arrayOffset() + cOffset, b);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && c.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.fma_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(c.duplicate().position(0)), cOffset * 4L, b);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, c, cOffset, b);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        float _selfy = src.get(srcOffset + 1);
        float _cy = c.get(cOffset + 1);
        dest.put(destOffset, Math.fma(src.get(srcOffset), b, c.get(cOffset)));
        dest.put(destOffset + 1, Math.fma(_selfy, b, _cy));
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float cX, float cY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, cX, cY);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float cX, float cY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, cX, cY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.fma_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, bX, bY, cX, cY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, bX, bY, cX, cY);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float cX, float cY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.fma(src.get(srcOffset), bX, cX));
        dest.put(destOffset + 1, Math.fma(_selfy, bY, cY));
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset * 4L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset * 4L;
        Float2OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 2 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 2) {
            Float2Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && b.order() == java.nio.ByteOrder.nativeOrder() && c.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.fma_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(b.duplicate().position(0)), bOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(c.duplicate().position(0)), cOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _by = b.get(bOffset + 1);
        float _cy = c.get(cOffset + 1);
        dest.put(destOffset, Math.fma(src.get(srcOffset), b.get(bOffset), c.get(cOffset)));
        dest.put(destOffset + 1, Math.fma(_selfy, _by, _cy));
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, scalar);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, scalar * src.get(srcOffset));
        dest.put(destOffset + 1, scalar * _selfy);
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, otherX, otherY);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, otherX * src.get(srcOffset));
        dest.put(destOffset + 1, otherY * _selfy);
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Float2Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _othery = other.get(otherOffset + 1);
        dest.put(destOffset, other.get(otherOffset) * src.get(srcOffset));
        dest.put(destOffset + 1, _othery * _selfy);
        return dest;
    }

    public static java.nio.FloatBuffer negate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.negate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, -src.get(srcOffset));
        dest.put(destOffset + 1, -_selfy);
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.sub_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, otherX, otherY);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, src.get(srcOffset) - otherX);
        dest.put(destOffset + 1, _selfy - otherY);
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Float2Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.sub_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _othery = other.get(otherOffset + 1);
        dest.put(destOffset, src.get(srcOffset) - other.get(otherOffset));
        dest.put(destOffset + 1, _selfy - _othery);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformDirection_unsafe(java.nio.FloatBuffer dest, int destOffset, float u) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformDirection_api(java.nio.FloatBuffer dest, int destOffset, float u) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2) {
            Float2Ops.makeUniformDirection(dest.array(), dest.arrayOffset() + destOffset, u);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float2OpsKernelsSegment.makeUniformDirection_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, u);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.makeUniformDirection_apiGet(dest, destOffset, u);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformDirection_apiGet(java.nio.FloatBuffer dest, int destOffset, float u) {
        float _t0 = u * 6.2831855f;
        float _t1 = Math.sin(_t0);
        dest.put(destOffset, Math.cosFromSin(_t1, _t0));
        dest.put(destOffset + 1, _t1);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2OpsKernelsAddress.set_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2) {
            Float2Ops.set(dest.array(), dest.arrayOffset() + destOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float2OpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, vX, vY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        dest.put(destOffset, vX);
        dest.put(destOffset + 1, vY);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset, v.get(vOffset));
        dest.put(destOffset + 1, _vy);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2) {
            Float2Ops.set(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float2OpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, s);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, float s) {
        dest.put(destOffset, s);
        dest.put(destOffset + 1, s);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2) {
            Float2Ops.makeZero(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float2OpsKernelsSegment.makeZero_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.makeZero_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.bezier_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.put(destOffset, Math.fma(p1X, _t7, src.get(srcOffset) * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        dest.put(destOffset + 1, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        return dest;
    }

    public static java.nio.FloatBuffer bezier_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 4L;
        Float2OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 2 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 2 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 2) {
            Float2Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        return bezier_api_sd5c68965_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** Piece 2 of {@code bezier_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer bezier_api_sd5c68965_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.bezier_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), p2Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p3.duplicate().position(0)), p3Offset * 4L, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _p1y = p1.get(p1Offset + 1);
        float _p2y = p2.get(p2Offset + 1);
        float _p3y = p3.get(p3Offset + 1);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.put(destOffset, Math.fma(p1.get(p1Offset), _t7, src.get(srcOffset) * _t8) + Math.fma(p2.get(p2Offset), _t6, p3.get(p3Offset) * _t2));
        dest.put(destOffset + 1, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p2X, p2Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.bezier2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, p1X, p1Y, p2X, p2Y, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.put(destOffset, Math.fma(p2X, _t0, Math.fma(p1X, _t3, src.get(srcOffset) * _t4)));
        dest.put(destOffset + 1, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 4L;
        Float2OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 2 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 2) {
            Float2Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.bezier2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), p2Offset * 4L, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _p1y = p1.get(p1Offset + 1);
        float _p2y = p2.get(p2Offset + 1);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.put(destOffset, Math.fma(p2.get(p2Offset), _t0, Math.fma(p1.get(p1Offset), _t3, src.get(srcOffset) * _t4)));
        dest.put(destOffset + 1, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p2X, p2Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.bezier2Tangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, p1X, p1Y, p2X, p2Y, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p2X, p2Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.put(destOffset, Math.fma(p1X - src.get(srcOffset), _t2, (p2X - p1X) * _t1));
        dest.put(destOffset + 1, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 4L;
        Float2OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 2 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 2) {
            Float2Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.bezier2Tangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), p2Offset * 4L, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _p1x = p1.get(p1Offset);
        float _p1y = p1.get(p1Offset + 1);
        float _p2y = p2.get(p2Offset + 1);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.put(destOffset, Math.fma(_p1x - src.get(srcOffset), _t2, (p2.get(p2Offset) - _p1x) * _t1));
        dest.put(destOffset + 1, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.bezierTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.put(destOffset, Math.fma(p3X - p2X, _t2, Math.fma(p1X - src.get(srcOffset), _t6, (p2X - p1X) * _t5)));
        dest.put(destOffset + 1, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 4L;
        Float2OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 2 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 2 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 2) {
            Float2Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        return bezierTangent_api_s81bfc46e_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** Piece 2 of {@code bezierTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer bezierTangent_api_s81bfc46e_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.bezierTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), p2Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p3.duplicate().position(0)), p3Offset * 4L, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _p1x = p1.get(p1Offset);
        float _p1y = p1.get(p1Offset + 1);
        float _p2x = p2.get(p2Offset);
        float _p2y = p2.get(p2Offset + 1);
        float _p3y = p3.get(p3Offset + 1);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.put(destOffset, Math.fma(p3.get(p3Offset) - _p2x, _t2, Math.fma(_p1x - src.get(srcOffset), _t6, (_p2x - _p1x) * _t5)));
        dest.put(destOffset + 1, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.catmullRom_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.put(destOffset, 0.5f * (Math.fma(2.0f, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        dest.put(destOffset + 1, 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 4L;
        Float2OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 2 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 2 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 2) {
            Float2Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        return catmullRom_api_s6024c4f0_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** Piece 2 of {@code catmullRom_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer catmullRom_api_s6024c4f0_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.catmullRom_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), p2Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p3.duplicate().position(0)), p3Offset * 4L, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _p1x = p1.get(p1Offset);
        float _p1y = p1.get(p1Offset + 1);
        float _p2x = p2.get(p2Offset);
        float _p2y = p2.get(p2Offset + 1);
        float _p3x = p3.get(p3Offset);
        float _p3y = p3.get(p3Offset + 1);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.put(destOffset, 0.5f * (Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), _t0, Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        dest.put(destOffset + 1, 0.5f * (Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), _t0, Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.catmullRomTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p2X, p2Y, p3X, p3Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p2X, float p2Y, float p3X, float p3Y, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t0 = t * t;
        dest.put(destOffset, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.put(destOffset + 1, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 4L;
        Float2OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 2 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 2 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 2) {
            Float2Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        return catmullRomTangent_api_sea7c0511_1(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** Piece 2 of {@code catmullRomTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer catmullRomTangent_api_sea7c0511_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.catmullRomTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), p2Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p3.duplicate().position(0)), p3Offset * 4L, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _p1x = p1.get(p1Offset);
        float _p1y = p1.get(p1Offset + 1);
        float _p2x = p2.get(p2Offset);
        float _p2y = p2.get(p2Offset + 1);
        float _p3x = p3.get(p3Offset);
        float _p3y = p3.get(p3Offset + 1);
        float _t0 = t * t;
        dest.put(destOffset, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), Math.fma(3.0f * Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.put(destOffset + 1, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), Math.fma(3.0f * Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        return dest;
    }

    public static java.nio.FloatBuffer hermite_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.hermite_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.put(destOffset, Math.fma(src.get(srcOffset), _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.put(destOffset + 1, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        return dest;
    }

    public static java.nio.FloatBuffer hermite_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset * 4L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset * 4L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset * 4L;
        Float2OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 2 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 2 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 2) {
            Float2Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        return hermite_api_s306903e0_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** Piece 2 of {@code hermite_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer hermite_api_s306903e0_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.hermite_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(t0.duplicate().position(0)), t0Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v1.duplicate().position(0)), v1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(t1.duplicate().position(0)), t1Offset * 4L, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _t0y = t0.get(t0Offset + 1);
        float _v1y = v1.get(v1Offset + 1);
        float _t1y = t1.get(t1Offset + 1);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.put(destOffset, Math.fma(src.get(srcOffset), _t10, t0.get(t0Offset) * _t7) + Math.fma(t1.get(t1Offset), _t5, v1.get(v1Offset) * _t9));
        dest.put(destOffset + 1, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.hermiteTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, v1X, v1Y, t1X, t1Y, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float v1X, float v1Y, float t1X, float t1Y, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.put(destOffset, Math.fma(src.get(srcOffset), _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.put(destOffset + 1, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset * 4L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset * 4L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset * 4L;
        Float2OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 2 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 2 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 2) {
            Float2Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        return hermiteTangent_api_s182a4df1_1(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** Piece 2 of {@code hermiteTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer hermiteTangent_api_s182a4df1_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.hermiteTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(t0.duplicate().position(0)), t0Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v1.duplicate().position(0)), v1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(t1.duplicate().position(0)), t1Offset * 4L, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        float _selfy = src.get(srcOffset + 1);
        float _t0y = t0.get(t0Offset + 1);
        float _v1y = v1.get(v1Offset + 1);
        float _t1y = t1.get(t1Offset + 1);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.put(destOffset, Math.fma(src.get(srcOffset), _t6, t0.get(t0Offset) * _t9) + Math.fma(t1.get(t1Offset), _t8, v1.get(v1Offset) * _t7));
        dest.put(destOffset + 1, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.fma(t, otherX - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(t, otherY - _selfy, _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Float2Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _othery = other.get(otherOffset + 1);
        dest.put(destOffset, Math.fma(t, other.get(otherOffset) - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(t, _othery - _selfy, _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float tX, float tY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, tX, tY);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float tX, float tY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, tX, tY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, tX, tY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, tX, tY);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float tX, float tY) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.fma(tX, otherX - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(tY, otherY - _selfy, _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset * 4L;
        Float2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2 && t.hasArray() && tOffset >= 0 && tOffset <= t.limit() - 2) {
            Float2Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t.array(), t.arrayOffset() + tOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder() && t.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(t.duplicate().position(0)), tOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _othery = other.get(otherOffset + 1);
        float _ty = t.get(tOffset + 1);
        dest.put(destOffset, Math.fma(t.get(tOffset), other.get(otherOffset) - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(_ty, _othery - _selfy, _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.slerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        float _t6 = Math.fma(otherX, otherX, otherY * otherY);
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t21 = Math.fma(otherX * _t10, _t12, otherY * _t10 * _t16);
        float _t26 = Math.fma(otherX, _t10, -(_t21 * _t12));
        float _t27 = Math.fma(otherY, _t10, -(_t21 * _t16));
        float _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        float _t31 = Math.fma(_t30, _t12, _t26);
        float _t32 = Math.fma(_t30, _t16, _t27);
        return slerp_apiGet_s75a23e25_1(dest, destOffset, src, srcOffset, otherX, otherY, t, _t12, _t16, t * (float) java.lang.Math.sqrt(_t6) + (1.0f - t) * (float) java.lang.Math.sqrt(_t5), _t21, _t31, _t32, Math.fma(_t31, _t31, _t32 * _t32));
    }

    /** Piece 2 of {@code slerp_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_s75a23e25_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float t, float _t12, float _t16, float _t20, float _t21, float _t31, float _t32, float _t35) {
        if (!(_t35 > 1.4551915E-11f && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, t);
        float _t39 = t * Math.atan2((float) java.lang.Math.sqrt(_t35), _t21);
        float _sp0 = _t20 * Math.sin(_t39) * (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t44 = _t20 * Math.cos(_t39);
        dest.put(destOffset, Math.fma(_t12, _t44, _sp0 * _t31));
        dest.put(destOffset + 1, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return Float2OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, t);
    }

    public static java.nio.FloatBuffer slerp_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.slerp_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t1 = unitScale(otherX, otherY, otherX);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = otherX * _t1;
        float _t8 = otherY * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = java.lang.Math.min(_t2, _t1);
        float _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        float _t48 = Math.fma(_t47, _t25, _t43);
        float _t49 = Math.fma(_t47, _t27, _t44);
        return slerp_degenerate_apiGet_s9a6406d8_1(dest, destOffset, otherX, otherY, t, _selfx, _selfy, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t48, _t49, unitScale(_t48, _t49, _t48));
    }

    /** Piece 2 of {@code slerp_degenerate_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_s9a6406d8_1(java.nio.FloatBuffer dest, int destOffset, float otherX, float otherY, float t, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t48, float _t49, float _t51) {
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t72, _t73;
        if (_t60 > 0.0f) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        return slerp_degenerate_apiGet_s9a6406d8_2(dest, destOffset, otherX, otherY, t, _selfx, _selfy, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sin(_t64), _t37 * Math.cos(_t64), _t72, _t73);
    }

    /** Piece 3 of {@code slerp_degenerate_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_s9a6406d8_2(java.nio.FloatBuffer dest, int destOffset, float otherX, float otherY, float t, float _selfx, float _selfy, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t68, float _t69, float _t72, float _t73) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
                    dest.put(destOffset, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    dest.put(destOffset + 1, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    dest.put(destOffset, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    dest.put(destOffset + 1, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                dest.put(destOffset, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                dest.put(destOffset + 1, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            dest.put(destOffset, Math.fma(t, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, Math.fma(t, otherY - _selfy, _selfy));
        }
        return dest;
    }

    public static java.nio.FloatBuffer slerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Float2Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.slerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _t5 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        if (!(_t5 > 1.1754944E-38f && _t5 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t6 = Math.fma(_otherx, _otherx, _othery * _othery);
        if (!(_t6 > 1.1754944E-38f && _t6 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t12 = _selfx * _t7;
        float _t16 = _selfy * _t7;
        float _t21 = Math.fma(_otherx * _t10, _t12, _othery * _t10 * _t16);
        float _t26 = Math.fma(_otherx, _t10, -(_t21 * _t12));
        float _t27 = Math.fma(_othery, _t10, -(_t21 * _t16));
        float _t30 = -Math.fma(_t26, _t12, _t27 * _t16);
        return slerp_apiGet_sc0355d58_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _t12, _t16, t * (float) java.lang.Math.sqrt(_t6) + (1.0f - t) * (float) java.lang.Math.sqrt(_t5), _t21, Math.fma(_t30, _t12, _t26), Math.fma(_t30, _t16, _t27));
    }

    /** Piece 2 of {@code slerp_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_sc0355d58_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t, float _t12, float _t16, float _t20, float _t21, float _t31, float _t32) {
        float _t35 = Math.fma(_t31, _t31, _t32 * _t32);
        if (!(_t35 > 1.4551915E-11f && _t35 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t39 = t * Math.atan2((float) java.lang.Math.sqrt(_t35), _t21);
        float _sp0 = _t20 * Math.sin(_t39) * (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t44 = _t20 * Math.cos(_t39);
        dest.put(destOffset, Math.fma(_t12, _t44, _sp0 * _t31));
        dest.put(destOffset + 1, Math.fma(_t16, _t44, _sp0 * _t32));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float2OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.FloatBuffer slerp_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Float2OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.slerp_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L, t);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _t1 = unitScale(_otherx, _othery, _otherx);
        float _t2 = unitScale(_selfx, _selfy, _selfx);
        float _t7 = _otherx * _t1;
        float _t8 = _othery * _t1;
        float _t9 = _selfx * _t2;
        float _t10 = _selfy * _t2;
        float _t11 = java.lang.Math.min(_t2, _t1);
        float _t18 = Math.fma(_t7, _t7, _t8 * _t8);
        float _t19 = Math.fma(_t9, _t9, _t10 * _t10);
        float _t22 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        float _t23 = (1.0f / (float) java.lang.Math.sqrt(_t19));
        float _t25 = _t23 * _t9;
        float _t27 = _t23 * _t10;
        float _t38 = Math.fma(_t22 * _t7, _t25, _t22 * _t8 * _t27);
        float _t43 = Math.fma(_t22, _t7, -(_t38 * _t25));
        float _t44 = Math.fma(_t22, _t8, -(_t38 * _t27));
        float _t47 = -Math.fma(_t43, _t25, _t44 * _t27);
        return slerp_degenerate_apiGet_s3ea7a5c3_1(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, 1.0f / _t11, _t18, _t19, _t25, _t27, -_t27, t * (float) java.lang.Math.sqrt(_t18) * (_t11 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t19) * (_t11 / _t2), _t38, _t44, _t47, Math.fma(_t47, _t25, _t43));
    }

    /** Piece 2 of {@code slerp_degenerate_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_s3ea7a5c3_1(java.nio.FloatBuffer dest, int destOffset, float t, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t37, float _t38, float _t44, float _t47, float _t48) {
        float _t49 = Math.fma(_t47, _t27, _t44);
        float _t51 = unitScale(_t48, _t49, _t48);
        float _t57 = _t48 * _t51;
        float _t58 = _t49 * _t51;
        float _t60 = Math.fma(_t57, _t57, _t58 * _t58);
        float _t62 = (1.0f / (float) java.lang.Math.sqrt(_t60));
        float _t64 = t * Math.atan2((float) java.lang.Math.sqrt(_t60), _t38 * _t51);
        float _t72, _t73;
        if (_t60 > 0.0f) {
            _t72 = _t62 * _t58;
            _t73 = _t62 * _t57;
        } else {
            _t72 = _t25;
            _t73 = _t28;
        }
        return slerp_degenerate_apiGet_s3ea7a5c3_2(dest, destOffset, t, _selfx, _selfy, _otherx, _othery, _t11_inv, _t18, _t19, _t25, _t27, _t28, _t38, _t48, _t49, _t37 * Math.sin(_t64), _t37 * Math.cos(_t64), _t72, _t73);
    }

    /** Piece 3 of {@code slerp_degenerate_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_s3ea7a5c3_2(java.nio.FloatBuffer dest, int destOffset, float t, float _selfx, float _selfy, float _otherx, float _othery, float _t11_inv, float _t18, float _t19, float _t25, float _t27, float _t28, float _t38, float _t48, float _t49, float _t68, float _t69, float _t72, float _t73) {
        if (_t18 * _t19 > 0.0f) {
            if (_t38 < 0.0f) {
                if (Math.fma(_t48, _t48, _t49 * _t49) <= 1.4551915E-11f) {
                    dest.put(destOffset, Math.fma(_t68, _t28, _t69 * _t25) * _t11_inv);
                    dest.put(destOffset + 1, Math.fma(_t68, _t25, _t69 * _t27) * _t11_inv);
                } else {
                    dest.put(destOffset, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                    dest.put(destOffset + 1, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
                }
            } else {
                dest.put(destOffset, Math.fma(_t68, _t73, _t69 * _t25) * _t11_inv);
                dest.put(destOffset + 1, Math.fma(_t68, _t72, _t69 * _t27) * _t11_inv);
            }
        } else {
            dest.put(destOffset, Math.fma(t, _otherx - _selfx, _selfx));
            dest.put(destOffset + 1, Math.fma(t, _othery - _selfy, _selfy));
        }
        return dest;
    }

    public static java.nio.FloatBuffer absolute_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer absolute_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.absolute(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.absolute_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.absolute_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer absolute_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, java.lang.Math.abs(src.get(srcOffset)));
        dest.put(destOffset + 1, java.lang.Math.abs(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer acos_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer acos_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.acos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.acos_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.acos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer acos_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.acos(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.acos(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, bX, bY, scalar);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float scalar) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.fma(scalar, bX, src.get(srcOffset)));
        dest.put(destOffset + 1, Math.fma(scalar, bY, _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset * 4L;
        Float2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 2) {
            Float2Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && b.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(b.duplicate().position(0)), bOffset * 4L, scalar);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        float _selfy = src.get(srcOffset + 1);
        float _by = b.get(bOffset + 1);
        dest.put(destOffset, Math.fma(scalar, b.get(bOffset), src.get(srcOffset)));
        dest.put(destOffset + 1, Math.fma(scalar, _by, _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float cX, float cY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, cX, cY);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float cX, float cY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, cX, cY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, bX, bY, cX, cY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, cX, cY);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float cX, float cY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.fma(bX, cX, src.get(srcOffset)));
        dest.put(destOffset + 1, Math.fma(bY, cY, _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset * 4L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset * 4L;
        Float2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 2 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 2) {
            Float2Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && b.order() == java.nio.ByteOrder.nativeOrder() && c.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(b.duplicate().position(0)), bOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(c.duplicate().position(0)), cOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _by = b.get(bOffset + 1);
        float _cy = c.get(cOffset + 1);
        dest.put(destOffset, Math.fma(b.get(bOffset), c.get(cOffset), src.get(srcOffset)));
        dest.put(destOffset + 1, Math.fma(_by, _cy, _selfy));
        return dest;
    }

    public static float angleBetween_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY);
    }

    public static float angleBetween_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.angleBetween_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
        }
        return Float2OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, otherX, otherY);
    }

    public static float angleBetween_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _ct0 = java.lang.Math.abs(Math.fma(otherY, _selfx, -(otherX * _selfy)));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY);
        return Math.atan2(_ct0, Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static float angleBetween_degenerate(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY);
        return Float2OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY);
    }

    public static float angleBetween_degenerate_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.angleBetween_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
        }
        return Float2OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, otherX, otherY);
    }

    public static float angleBetween_degenerate_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t0 = unitScale(otherX, otherY, otherX);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = otherY * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = otherX * _t0;
        float _t9 = _selfy * _t1;
        float _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(java.lang.Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static float angleBetween_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float2OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Float2Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.angleBetween_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _ct0 = java.lang.Math.abs(Math.fma(_othery, _selfx, -(_otherx * _selfy)));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(_ct0, Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static float angleBetween_degenerate(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Float2OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float2OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_degenerate_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Float2OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.angleBetween_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t12 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t13 = unitScale(_t12, _t12, _t12);
        return Math.atan2(java.lang.Math.abs(_t12 * _t13), Math.fma(_t8, _t7, _t6 * _t9) * _t13);
    }

    public static java.nio.FloatBuffer asin_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer asin_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.asin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.asin_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.asin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer asin_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.asin(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.asin(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer atan_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer atan_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.atan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.atan_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.atan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer atan_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.atan(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.atan(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer atan2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.atan2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, x);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.atan2(src.get(srcOffset), x));
        dest.put(destOffset + 1, Math.atan2(_selfy, x));
        return dest;
    }

    public static java.nio.FloatBuffer atan2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, xX, xY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.atan2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, xX, xY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, xX, xY);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.atan2(src.get(srcOffset), xX));
        dest.put(destOffset + 1, Math.atan2(_selfy, xY));
        return dest;
    }

    public static java.nio.FloatBuffer atan2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + xOffset * 4L;
        Float2OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && x.hasArray() && xOffset >= 0 && xOffset <= x.limit() - 2) {
            Float2Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x.array(), x.arrayOffset() + xOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && x.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.atan2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(x.duplicate().position(0)), xOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x, xOffset);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _xy = x.get(xOffset + 1);
        dest.put(destOffset, Math.atan2(src.get(srcOffset), x.get(xOffset)));
        dest.put(destOffset + 1, Math.atan2(_selfy, _xy));
        return dest;
    }

    public static java.nio.FloatBuffer cbrt_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cbrt_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.cbrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.cbrt_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.cbrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cbrt_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.cbrt(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.cbrt(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer ceil_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer ceil_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.ceil(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.ceil_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.ceil_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer ceil_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.ceil(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.ceil(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer clamp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min, max);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.clamp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, min, max);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, max);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, java.lang.Math.min(java.lang.Math.max(src.get(srcOffset), min), max));
        dest.put(destOffset + 1, java.lang.Math.min(java.lang.Math.max(_selfy, min), max));
        return dest;
    }

    public static java.nio.FloatBuffer clamp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float maxX, float maxY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, maxX, maxY);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float maxX, float maxY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, minX, minY, maxX, maxY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.clamp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, minX, minY, maxX, maxY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, minX, minY, maxX, maxY);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float maxX, float maxY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, java.lang.Math.min(java.lang.Math.max(src.get(srcOffset), minX), maxX));
        dest.put(destOffset + 1, java.lang.Math.min(java.lang.Math.max(_selfy, minY), maxY));
        return dest;
    }

    public static java.nio.FloatBuffer clamp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + minOffset * 4L;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + maxOffset * 4L;
        Float2OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && min.hasArray() && minOffset >= 0 && minOffset <= min.limit() - 2 && max.hasArray() && maxOffset >= 0 && maxOffset <= max.limit() - 2) {
            Float2Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min.array(), min.arrayOffset() + minOffset, max.array(), max.arrayOffset() + maxOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && min.order() == java.nio.ByteOrder.nativeOrder() && max.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.clamp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(min.duplicate().position(0)), minOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(max.duplicate().position(0)), maxOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _miny = min.get(minOffset + 1);
        float _maxy = max.get(maxOffset + 1);
        dest.put(destOffset, java.lang.Math.min(java.lang.Math.max(src.get(srcOffset), min.get(minOffset)), max.get(maxOffset)));
        dest.put(destOffset + 1, java.lang.Math.min(java.lang.Math.max(_selfy, _miny), _maxy));
        return dest;
    }

    public static float compAdd_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static float compAdd_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.compAdd(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.compAdd_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.compAdd_apiGet(src, srcOffset);
    }

    public static float compAdd_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return src.get(srcOffset) + src.get(srcOffset + 1);
    }

    public static float compMax_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static float compMax_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.compMax(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.compMax_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.compMax_apiGet(src, srcOffset);
    }

    public static float compMax_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return java.lang.Math.max(src.get(srcOffset), src.get(srcOffset + 1));
    }

    public static float compMin_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static float compMin_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.compMin(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.compMin_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.compMin_apiGet(src, srcOffset);
    }

    public static float compMin_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return java.lang.Math.min(src.get(srcOffset), src.get(srcOffset + 1));
    }

    public static float compMul_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static float compMul_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.compMul(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.compMul_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.compMul_apiGet(src, srcOffset);
    }

    public static float compMul_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return src.get(srcOffset) * src.get(srcOffset + 1);
    }

    public static java.nio.FloatBuffer copySign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.copySign_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, sign);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.copySign(src.get(srcOffset), sign));
        dest.put(destOffset + 1, Math.copySign(_selfy, sign));
        return dest;
    }

    public static java.nio.FloatBuffer copySign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, signX, signY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.copySign_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, signX, signY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, signX, signY);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.copySign(src.get(srcOffset), signX));
        dest.put(destOffset + 1, Math.copySign(_selfy, signY));
        return dest;
    }

    public static java.nio.FloatBuffer copySign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + signOffset * 4L;
        Float2OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && sign.hasArray() && signOffset >= 0 && signOffset <= sign.limit() - 2) {
            Float2Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign.array(), sign.arrayOffset() + signOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && sign.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.copySign_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(sign.duplicate().position(0)), signOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign, signOffset);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _signy = sign.get(signOffset + 1);
        dest.put(destOffset, Math.copySign(src.get(srcOffset), sign.get(signOffset)));
        dest.put(destOffset + 1, Math.copySign(_selfy, _signy));
        return dest;
    }

    public static java.nio.FloatBuffer cos_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cos_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.cos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.cos_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.cos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cos_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.cos(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.cos(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer cosh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cosh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.cosh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.cosh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.cosh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cosh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.cosh(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.cosh(_selfy));
        return dest;
    }

    public static float cross_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.cross_unsafe(_srcBase, otherX, otherY);
    }

    public static float cross_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.cross(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.cross_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
        }
        return Float2OpsKernelsTypedBuffer.cross_apiGet(src, srcOffset, otherX, otherY);
    }

    public static float cross_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        return Math.fma(otherY, src.get(srcOffset), -(otherX * src.get(srcOffset + 1)));
    }

    public static float cross_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float2OpsKernelsAddress.cross_unsafe(_srcBase, _otherBase);
    }

    public static float cross_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Float2Ops.cross(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.cross_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.cross_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float cross_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        return Math.fma(other.get(otherOffset + 1), src.get(srcOffset), -(other.get(otherOffset) * src.get(srcOffset + 1)));
    }

    public static java.nio.FloatBuffer degrees_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer degrees_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.degrees(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.degrees_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.degrees_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer degrees_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.toDegrees(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.toDegrees(_selfy));
        return dest;
    }

    public static float distance_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY);
    }

    public static float distance_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.distance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.distance_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
        }
        return Float2OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, otherX, otherY);
    }

    public static float distance_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        float _t0 = src.get(srcOffset) - otherX;
        float _t1 = src.get(srcOffset + 1) - otherY;
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static float distance_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float2OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static float distance_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Float2Ops.distance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.distance_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float distance_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _t0 = src.get(srcOffset) - other.get(otherOffset);
        float _t1 = src.get(srcOffset + 1) - other.get(otherOffset + 1);
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, _t1 * _t1));
    }

    public static float distanceSquared_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY);
    }

    public static float distanceSquared_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.distanceSquared_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
        }
        return Float2OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, otherX, otherY);
    }

    public static float distanceSquared_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        float _t0 = src.get(srcOffset) - otherX;
        float _t1 = src.get(srcOffset + 1) - otherY;
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static float distanceSquared_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float2OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static float distanceSquared_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Float2Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.distanceSquared_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float distanceSquared_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _t0 = src.get(srcOffset) - other.get(otherOffset);
        float _t1 = src.get(srcOffset + 1) - other.get(otherOffset + 1);
        return Math.fma(_t0, _t0, _t1 * _t1);
    }

    public static float dot_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY);
    }

    public static float dot_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.dot(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.dot_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
        }
        return Float2OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, otherX, otherY);
    }

    public static float dot_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        return Math.fma(otherX, src.get(srcOffset), otherY * src.get(srcOffset + 1));
    }

    public static float dot_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float2OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Float2Ops.dot(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.dot_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float dot_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        return Math.fma(other.get(otherOffset), src.get(srcOffset), other.get(otherOffset + 1) * src.get(srcOffset + 1));
    }

    public static java.nio.FloatBuffer exp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer exp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.exp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.exp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.exp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer exp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.exp(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.exp(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer exp2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer exp2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.exp2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.exp2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.exp2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer exp2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.pow(2.0f, src.get(srcOffset)));
        dest.put(destOffset + 1, Math.pow(2.0f, _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer expm1_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer expm1_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.expm1(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.expm1_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.expm1_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer expm1_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.expm1(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.expm1(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float NrefX, float NrefY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, NrefX, NrefY);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float NrefX, float NrefY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, IX, IY, NrefX, NrefY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.faceforward_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, IX, IY, NrefX, NrefY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, IX, IY, NrefX, NrefY);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float NrefX, float NrefY) {
        float _selfy = src.get(srcOffset + 1);
        float _t2 = Math.fma(IX, NrefX, IY * NrefY) < 0.0f ? 1.0f : -1.0f;
        dest.put(destOffset, src.get(srcOffset) * _t2);
        dest.put(destOffset + 1, _selfy * _t2);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + IOffset * 4L;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + NrefOffset * 4L;
        Float2OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && I.hasArray() && IOffset >= 0 && IOffset <= I.limit() - 2 && Nref.hasArray() && NrefOffset >= 0 && NrefOffset <= Nref.limit() - 2) {
            Float2Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, I.array(), I.arrayOffset() + IOffset, Nref.array(), Nref.arrayOffset() + NrefOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && I.order() == java.nio.ByteOrder.nativeOrder() && Nref.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.faceforward_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(I.duplicate().position(0)), IOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(Nref.duplicate().position(0)), NrefOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _t2 = Math.fma(I.get(IOffset), Nref.get(NrefOffset), I.get(IOffset + 1) * Nref.get(NrefOffset + 1)) < 0.0f ? 1.0f : -1.0f;
        dest.put(destOffset, src.get(srcOffset) * _t2);
        dest.put(destOffset + 1, _selfy * _t2);
        return dest;
    }

    public static java.nio.FloatBuffer floor_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer floor_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.floor(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.floor_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.floor_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer floor_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.floor(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.floor(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer fract_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer fract_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.fract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.fract_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.fract_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer fract_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, java.lang.Math.min(_selfx - Math.floor(_selfx), 0.99999994f));
        dest.put(destOffset + 1, java.lang.Math.min(_selfy - Math.floor(_selfy), 0.99999994f));
        return dest;
    }

    public static java.nio.FloatBuffer hypot_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.hypot_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, y);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.hypot(src.get(srcOffset), y));
        dest.put(destOffset + 1, Math.hypot(_selfy, y));
        return dest;
    }

    public static java.nio.FloatBuffer hypot_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.hypot_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, yX, yY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, yX, yY);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.hypot(src.get(srcOffset), yX));
        dest.put(destOffset + 1, Math.hypot(_selfy, yY));
        return dest;
    }

    public static java.nio.FloatBuffer hypot_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset * 4L;
        Float2OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 2) {
            Float2Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && y.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.hypot_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(y.duplicate().position(0)), yOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _yy = y.get(yOffset + 1);
        dest.put(destOffset, Math.hypot(src.get(srcOffset), y.get(yOffset)));
        dest.put(destOffset + 1, Math.hypot(_selfy, _yy));
        return dest;
    }

    public static java.nio.FloatBuffer inverse_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer inverse_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.inverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.inverse_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.inverse_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer inverse_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, 1.0f / src.get(srcOffset));
        dest.put(destOffset + 1, 1.0f / _selfy);
        return dest;
    }

    public static java.nio.FloatBuffer inverseSqrt_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer inverseSqrt_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.inverseSqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.inverseSqrt_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.inverseSqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer inverseSqrt_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, (1.0f / (float) java.lang.Math.sqrt(src.get(srcOffset))));
        dest.put(destOffset + 1, (1.0f / (float) java.lang.Math.sqrt(_selfy)));
        return dest;
    }

    public static float length_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.length(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.length_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.length_apiGet(src, srcOffset);
    }

    public static float length_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        return (float) java.lang.Math.sqrt(Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    public static float lengthSquared_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.lengthSquared(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.lengthSquared_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.lengthSquared_apiGet(src, srcOffset);
    }

    public static float lengthSquared_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        return Math.fma(_selfx, _selfx, _selfy * _selfy);
    }

    public static java.nio.FloatBuffer log_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.log(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.log_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.log_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.log(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer log10_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log10_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.log10(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.log10_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.log10_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log10_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.log10(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log10(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer log1p_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log1p_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.log1p(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.log1p_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.log1p_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log1p_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.log1p(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log1p(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer log2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.log2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.log2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.log2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.log2(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log2(_selfy));
        return dest;
    }

    public static float manhattanDistance_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY);
    }

    public static float manhattanDistance_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.manhattanDistance_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
        }
        return Float2OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, otherX, otherY);
    }

    public static float manhattanDistance_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        return java.lang.Math.abs(src.get(srcOffset) - otherX) + java.lang.Math.abs(src.get(srcOffset + 1) - otherY);
    }

    public static float manhattanDistance_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float2OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static float manhattanDistance_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Float2Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.manhattanDistance_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float manhattanDistance_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        return java.lang.Math.abs(src.get(srcOffset) - other.get(otherOffset)) + java.lang.Math.abs(src.get(srcOffset + 1) - other.get(otherOffset + 1));
    }

    public static float manhattanLength_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static float manhattanLength_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.manhattanLength(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.manhattanLength_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.manhattanLength_apiGet(src, srcOffset);
    }

    public static float manhattanLength_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return java.lang.Math.abs(src.get(srcOffset)) + java.lang.Math.abs(src.get(srcOffset + 1));
    }

    public static java.nio.FloatBuffer max_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer max_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.max_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, scalar);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer max_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, java.lang.Math.max(src.get(srcOffset), scalar));
        dest.put(destOffset + 1, java.lang.Math.max(_selfy, scalar));
        return dest;
    }

    public static java.nio.FloatBuffer max_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.FloatBuffer max_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.max_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, otherX, otherY);
        return dest;
    }

    public static java.nio.FloatBuffer max_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, java.lang.Math.max(src.get(srcOffset), otherX));
        dest.put(destOffset + 1, java.lang.Math.max(_selfy, otherY));
        return dest;
    }

    public static java.nio.FloatBuffer max_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer max_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Float2Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.max_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer max_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _othery = other.get(otherOffset + 1);
        dest.put(destOffset, java.lang.Math.max(src.get(srcOffset), other.get(otherOffset)));
        dest.put(destOffset + 1, java.lang.Math.max(_selfy, _othery));
        return dest;
    }

    public static java.nio.FloatBuffer min_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer min_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.min_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, scalar);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer min_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, java.lang.Math.min(src.get(srcOffset), scalar));
        dest.put(destOffset + 1, java.lang.Math.min(_selfy, scalar));
        return dest;
    }

    public static java.nio.FloatBuffer min_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY);
        return dest;
    }

    public static java.nio.FloatBuffer min_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.min_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, otherX, otherY);
        return dest;
    }

    public static java.nio.FloatBuffer min_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, java.lang.Math.min(src.get(srcOffset), otherX));
        dest.put(destOffset + 1, java.lang.Math.min(_selfy, otherY));
        return dest;
    }

    public static java.nio.FloatBuffer min_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer min_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            Float2Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.min_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer min_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _othery = other.get(otherOffset + 1);
        dest.put(destOffset, java.lang.Math.min(src.get(srcOffset), other.get(otherOffset)));
        dest.put(destOffset + 1, java.lang.Math.min(_selfy, _othery));
        return dest;
    }

    public static java.nio.FloatBuffer mod_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.FloatBuffer mod_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.mod_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, y);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.FloatBuffer mod_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, flooredMod(_selfx, y));
        dest.put(destOffset + 1, flooredMod(_selfy, y));
        return dest;
    }

    public static java.nio.FloatBuffer mod_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY);
        return dest;
    }

    public static java.nio.FloatBuffer mod_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.mod_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, yX, yY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, yX, yY);
        return dest;
    }

    public static java.nio.FloatBuffer mod_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, flooredMod(_selfx, yX));
        dest.put(destOffset + 1, flooredMod(_selfy, yY));
        return dest;
    }

    public static java.nio.FloatBuffer mod_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset * 4L;
        Float2OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.FloatBuffer mod_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 2) {
            Float2Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && y.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.mod_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(y.duplicate().position(0)), yOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mod_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _yx = y.get(yOffset);
        float _yy = y.get(yOffset + 1);
        dest.put(destOffset, flooredMod(_selfx, _yx));
        dest.put(destOffset + 1, flooredMod(_selfy, _yy));
        return dest;
    }

    public static java.nio.FloatBuffer nextDown_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer nextDown_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.nextDown(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.nextDown_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.nextDown_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer nextDown_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.nextDown(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.nextDown(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer nextUp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer nextUp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.nextUp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.nextUp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.nextUp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer nextUp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.nextUp(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.nextUp(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer normalize_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalize_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.normalize(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.normalize_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.normalize_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalize_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        float _t2 = (1.0f / (float) java.lang.Math.sqrt(_t1));
        if (_t1 != 0.0f) {
            dest.put(destOffset, _selfx * _t2);
            dest.put(destOffset + 1, _selfy * _t2);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer normalizeMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.FloatBuffer normalizeMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.normalizeMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, length);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.normalizeMul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, length);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.normalizeMul_apiGet(dest, destOffset, src, srcOffset, length);
        return dest;
    }

    public static java.nio.FloatBuffer normalizeMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t1 = Math.fma(_selfx, _selfx, _selfy * _selfy);
        float _t3 = length * (1.0f / (float) java.lang.Math.sqrt(_t1));
        if (_t1 != 0.0f) {
            dest.put(destOffset, _selfx * _t3);
            dest.put(destOffset + 1, _selfy * _t3);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
        }
        return dest;
    }

    public static float orientedAngle_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.orientedAngle_unsafe(_srcBase, otherX, otherY);
    }

    public static float orientedAngle_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2Ops.orientedAngle(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.orientedAngle_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
        }
        return Float2OpsKernelsTypedBuffer.orientedAngle_apiGet(src, srcOffset, otherX, otherY);
    }

    public static float orientedAngle_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t2 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _ct0 = java.lang.Math.abs(_t2);
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsTypedBuffer.orientedAngle_degenerate(src, srcOffset, otherX, otherY);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static float orientedAngle_degenerate(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2OpsKernelsTypedBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY);
        return Float2OpsKernelsTypedBuffer.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY);
    }

    public static float orientedAngle_degenerate_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, otherX, otherY);
    }

    public static float orientedAngle_degenerate_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            return Float2OpsKernelsArray.orientedAngle_degenerate(src.array(), src.arrayOffset() + srcOffset, otherX, otherY);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.orientedAngle_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY);
        }
        return Float2OpsKernelsTypedBuffer.orientedAngle_degenerate_apiGet(src, srcOffset, otherX, otherY);
    }

    public static float orientedAngle_degenerate_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t0 = unitScale(otherX, otherY, otherX);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = otherY * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = otherX * _t0;
        float _t9 = _selfy * _t1;
        float _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t15 = unitScale(_t14, _t14, _t14);
        float _t19 = _t14 * _t15;
        float _t21 = Math.atan2(java.lang.Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0f ? -_t21 : _t21;
    }

    public static float orientedAngle_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float2OpsKernelsAddress.orientedAngle_unsafe(_srcBase, _otherBase);
    }

    public static float orientedAngle_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Float2Ops.orientedAngle(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.orientedAngle_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.orientedAngle_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float orientedAngle_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _t2 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _ct0 = java.lang.Math.abs(_t2);
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float2OpsKernelsTypedBuffer.orientedAngle_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(Math.copySign(_ct0, _t2), Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static float orientedAngle_degenerate(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2OpsKernelsTypedBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Float2OpsKernelsTypedBuffer.orientedAngle_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static float orientedAngle_degenerate_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return Float2OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float orientedAngle_degenerate_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 2) {
            return Float2OpsKernelsArray.orientedAngle_degenerate(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2OpsKernelsSegment.orientedAngle_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
        }
        return Float2OpsKernelsTypedBuffer.orientedAngle_degenerate_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float orientedAngle_degenerate_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _t0 = unitScale(_otherx, _othery, _otherx);
        float _t1 = unitScale(_selfx, _selfy, _selfx);
        float _t6 = _othery * _t0;
        float _t7 = _selfx * _t1;
        float _t8 = _otherx * _t0;
        float _t9 = _selfy * _t1;
        float _t14 = Math.fma(_t6, _t7, -(_t8 * _t9));
        float _t15 = unitScale(_t14, _t14, _t14);
        float _t19 = _t14 * _t15;
        float _t21 = Math.atan2(java.lang.Math.abs(_t19), Math.fma(_t8, _t7, _t6 * _t9) * _t15);
        return _t19 < 0.0f ? -_t21 : _t21;
    }

    public static java.nio.FloatBuffer outerProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rowX, rowY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.outerProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, rowX, rowY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, rowX, rowY);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, rowX * _selfx);
        dest.put(destOffset + 1, rowX * _selfy);
        dest.put(destOffset + 2, rowY * _selfx);
        dest.put(destOffset + 3, rowY * _selfy);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset * 4L;
        Float2OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && row.hasArray() && rowOffset >= 0 && rowOffset <= row.limit() - 2) {
            Float2Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row.array(), row.arrayOffset() + rowOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && row.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.outerProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(row.duplicate().position(0)), rowOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, row, rowOffset);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _rowx = row.get(rowOffset);
        float _rowy = row.get(rowOffset + 1);
        dest.put(destOffset, _rowx * _selfx);
        dest.put(destOffset + 1, _rowx * _selfy);
        dest.put(destOffset + 2, _rowy * _selfx);
        dest.put(destOffset + 3, _rowy * _selfy);
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.pow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, exponent);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.pow(src.get(srcOffset), exponent));
        dest.put(destOffset + 1, Math.pow(_selfy, exponent));
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponentX, exponentY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.pow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, exponentX, exponentY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponentX, exponentY);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.pow(src.get(srcOffset), exponentX));
        dest.put(destOffset + 1, Math.pow(_selfy, exponentY));
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + exponentOffset * 4L;
        Float2OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && exponent.hasArray() && exponentOffset >= 0 && exponentOffset <= exponent.limit() - 2) {
            Float2Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent.array(), exponent.arrayOffset() + exponentOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && exponent.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.pow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(exponent.duplicate().position(0)), exponentOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _exponenty = exponent.get(exponentOffset + 1);
        dest.put(destOffset, Math.pow(src.get(srcOffset), exponent.get(exponentOffset)));
        dest.put(destOffset + 1, Math.pow(_selfy, _exponenty));
        return dest;
    }

    public static java.nio.FloatBuffer project_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY);
        return dest;
    }

    public static java.nio.FloatBuffer project_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, ontoX, ontoY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.project_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, ontoX, ontoY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, ontoX, ontoY);
        return dest;
    }

    public static java.nio.FloatBuffer project_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY) {
        float _t5 = Math.fma(ontoX, src.get(srcOffset), ontoY * src.get(srcOffset + 1)) / Math.fma(ontoX, ontoX, ontoY * ontoY);
        dest.put(destOffset, ontoX * _t5);
        dest.put(destOffset + 1, ontoY * _t5);
        return dest;
    }

    public static java.nio.FloatBuffer project_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + ontoOffset * 4L;
        Float2OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.FloatBuffer project_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && onto.hasArray() && ontoOffset >= 0 && ontoOffset <= onto.limit() - 2) {
            Float2Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, onto.array(), onto.arrayOffset() + ontoOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && onto.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.project_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(onto.duplicate().position(0)), ontoOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return dest;
    }

    public static java.nio.FloatBuffer project_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        float _ontox = onto.get(ontoOffset);
        float _ontoy = onto.get(ontoOffset + 1);
        float _t5 = Math.fma(_ontox, src.get(srcOffset), _ontoy * src.get(srcOffset + 1)) / Math.fma(_ontox, _ontox, _ontoy * _ontoy);
        dest.put(destOffset, _ontox * _t5);
        dest.put(destOffset + 1, _ontoy * _t5);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.projectOnPlane_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, normalX, normalY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normalX, normalY);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t1 = Math.fma(normalX, _selfx, normalY * _selfy);
        dest.put(destOffset, Math.fma(-normalX, _t1, _selfx));
        dest.put(destOffset + 1, Math.fma(-normalY, _t1, _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 4L;
        Float2OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 2) {
            Float2Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.projectOnPlane_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(normal.duplicate().position(0)), normalOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _normalx = normal.get(normalOffset);
        float _normaly = normal.get(normalOffset + 1);
        float _t1 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        dest.put(destOffset, Math.fma(-_normalx, _t1, _selfx));
        dest.put(destOffset + 1, Math.fma(-_normaly, _t1, _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer radians_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer radians_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.radians(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.radians_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.radians_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer radians_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.toRadians(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.toRadians(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer reflect_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.reflect_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, normalX, normalY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normalX, normalY);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t2 = 2.0f * Math.fma(normalX, _selfx, normalY * _selfy);
        dest.put(destOffset, Math.fma(-normalX, _t2, _selfx));
        dest.put(destOffset + 1, Math.fma(-normalY, _t2, _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer reflect_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 4L;
        Float2OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 2) {
            Float2Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.reflect_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(normal.duplicate().position(0)), normalOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _normalx = normal.get(normalOffset);
        float _normaly = normal.get(normalOffset + 1);
        float _t2 = 2.0f * Math.fma(_normalx, _selfx, _normaly * _selfy);
        dest.put(destOffset, Math.fma(-_normalx, _t2, _selfx));
        dest.put(destOffset + 1, Math.fma(-_normaly, _t2, _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer refract_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, eta);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.refract_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, normalX, normalY, eta);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float eta) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t2 = Math.fma(normalX, _selfx, normalY * _selfy);
        float _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0f), eta * eta, 1.0f);
        float _t9 = Math.fma(eta, _t2, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t6)));
        if (_t6 >= 0.0f) {
            dest.put(destOffset, Math.fma(eta, _selfx, -(normalX * _t9)));
            dest.put(destOffset + 1, Math.fma(eta, _selfy, -(normalY * _t9)));
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer refract_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 4L;
        Float2OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 2) {
            Float2Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset, eta);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.refract_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(normal.duplicate().position(0)), normalOffset * 4L, eta);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _normalx = normal.get(normalOffset);
        float _normaly = normal.get(normalOffset + 1);
        float _t2 = Math.fma(_normalx, _selfx, _normaly * _selfy);
        float _t6 = Math.fma(-Math.fma(-_t2, _t2, 1.0f), eta * eta, 1.0f);
        float _t9 = Math.fma(eta, _t2, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t6)));
        if (_t6 >= 0.0f) {
            dest.put(destOffset, Math.fma(eta, _selfx, -(_normalx * _t9)));
            dest.put(destOffset + 1, Math.fma(eta, _selfy, -(_normaly * _t9)));
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer round_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer round_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.round(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.round_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.round_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer round_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.rint(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.rint(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer sign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.sign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.sign_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.sign_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.signum(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.signum(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer sin_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sin_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.sin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.sin_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.sin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sin_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.sin(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.sin(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer sinh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sinh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.sinh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.sinh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.sinh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sinh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.sinh(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.sinh(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0, edge1);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.smoothstep_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, edge0, edge1);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge1);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t7 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(srcOffset) - edge0) * _t0_inv));
        float _t8 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(srcOffset + 1) - edge0) * _t0_inv));
        dest.put(destOffset, Math.fma(-2.0f, _t7, 3.0f) * _t7 * _t7);
        dest.put(destOffset + 1, Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge1X, float edge1Y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge1X, edge1Y);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge1X, float edge1Y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0X, edge0Y, edge1X, edge1Y);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.smoothstep_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, edge0X, edge0Y, edge1X, edge1Y);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge1X, edge1Y);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge1X, float edge1Y) {
        float _t8 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t9 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(srcOffset + 1) - edge0Y) / (edge1Y - edge0Y)));
        dest.put(destOffset, Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        dest.put(destOffset + 1, Math.fma(-2.0f, _t9, 3.0f) * _t9 * _t9);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + edge0Offset * 4L;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + edge1Offset * 4L;
        Float2OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && edge0.hasArray() && edge0Offset >= 0 && edge0Offset <= edge0.limit() - 2 && edge1.hasArray() && edge1Offset >= 0 && edge1Offset <= edge1.limit() - 2) {
            Float2Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0.array(), edge0.arrayOffset() + edge0Offset, edge1.array(), edge1.arrayOffset() + edge1Offset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && edge0.order() == java.nio.ByteOrder.nativeOrder() && edge1.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.smoothstep_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(edge0.duplicate().position(0)), edge0Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(edge1.duplicate().position(0)), edge1Offset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        float _edge0x = edge0.get(edge0Offset);
        float _edge0y = edge0.get(edge0Offset + 1);
        float _t8 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(srcOffset) - _edge0x) / (edge1.get(edge1Offset) - _edge0x)));
        float _t9 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.get(srcOffset + 1) - _edge0y) / (edge1.get(edge1Offset + 1) - _edge0y)));
        dest.put(destOffset, Math.fma(-2.0f, _t8, 3.0f) * _t8 * _t8);
        dest.put(destOffset + 1, Math.fma(-2.0f, _t9, 3.0f) * _t9 * _t9);
        return dest;
    }

    public static java.nio.FloatBuffer sqrt_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sqrt_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.sqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.sqrt_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.sqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sqrt_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, (float) java.lang.Math.sqrt(src.get(srcOffset)));
        dest.put(destOffset + 1, (float) java.lang.Math.sqrt(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer step_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.FloatBuffer step_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.step_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, edge);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge);
        return dest;
    }

    public static java.nio.FloatBuffer step_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, src.get(srcOffset) < edge ? 0.0f : 1.0f);
        dest.put(destOffset + 1, _selfy < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer step_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY);
        return dest;
    }

    public static java.nio.FloatBuffer step_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edgeX, edgeY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.step_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, edgeX, edgeY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edgeX, edgeY);
        return dest;
    }

    public static java.nio.FloatBuffer step_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, src.get(srcOffset) < edgeX ? 0.0f : 1.0f);
        dest.put(destOffset + 1, _selfy < edgeY ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer step_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + edgeOffset * 4L;
        Float2OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.FloatBuffer step_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && edge.hasArray() && edgeOffset >= 0 && edgeOffset <= edge.limit() - 2) {
            Float2Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge.array(), edge.arrayOffset() + edgeOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && edge.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.step_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(edge.duplicate().position(0)), edgeOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return dest;
    }

    public static java.nio.FloatBuffer step_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _edgey = edge.get(edgeOffset + 1);
        dest.put(destOffset, src.get(srcOffset) < edge.get(edgeOffset) ? 0.0f : 1.0f);
        dest.put(destOffset + 1, _selfy < _edgey ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer tan_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer tan_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.tan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.tan_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.tan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer tan_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.tan(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.tan(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer tanh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer tanh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.tanh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.tanh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.tanh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer tanh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.tanh(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.tanh(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer trunc_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer trunc_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.trunc(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.trunc_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.trunc_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer trunc_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.put(destOffset + 1, _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer ulp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer ulp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.ulp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.ulp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.ulp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer ulp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        dest.put(destOffset, Math.ulp(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.ulp(_selfy));
        return dest;
    }

    public static java.nio.FloatBuffer preMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 4L;
        Float2OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 4) {
            Float2Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.preMul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), matOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _mat10 = mat.get(matOffset + 1);
        float _mat11 = mat.get(matOffset + 3);
        dest.put(destOffset, Math.fma(mat.get(matOffset), _selfx, mat.get(matOffset + 2) * _selfy));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer preMulDirectionMat2x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 4L;
        Float2OpsKernelsAddress.preMulDirectionMat2x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulDirectionMat2x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 6) {
            Float2Ops.preMulDirectionMat2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.preMulDirectionMat2x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), matOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.preMulDirectionMat2x3_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulDirectionMat2x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _mat10 = mat.get(matOffset + 1);
        float _mat11 = mat.get(matOffset + 3);
        dest.put(destOffset, Math.fma(mat.get(matOffset), _selfx, mat.get(matOffset + 2) * _selfy));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer preMulDirectionMat3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 4L;
        Float2OpsKernelsAddress.preMulDirectionMat3x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulDirectionMat3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 9) {
            Float2Ops.preMulDirectionMat3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.preMulDirectionMat3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), matOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.preMulDirectionMat3x3_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulDirectionMat3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _mat10 = mat.get(matOffset + 1);
        float _mat11 = mat.get(matOffset + 4);
        dest.put(destOffset, Math.fma(mat.get(matOffset), _selfx, mat.get(matOffset + 3) * _selfy));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat4x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 4L;
        Float2OpsKernelsAddress.preMulPositionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat4x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 16) {
            Float2Ops.preMulPositionMat4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.preMulPositionMat4x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), matOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.preMulPositionMat4x4_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat4x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _mat10 = mat.get(matOffset + 1);
        float _mat11 = mat.get(matOffset + 5);
        float _mat13 = mat.get(matOffset + 13);
        dest.put(destOffset, Math.fma(mat.get(matOffset), _selfx, Math.fma(mat.get(matOffset + 4), _selfy, mat.get(matOffset + 12))));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat13)));
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat2x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 4L;
        Float2OpsKernelsAddress.preMulPositionMat2x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat2x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 6) {
            Float2Ops.preMulPositionMat2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.preMulPositionMat2x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), matOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.preMulPositionMat2x3_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat2x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _mat10 = mat.get(matOffset + 1);
        float _mat11 = mat.get(matOffset + 3);
        float _mat12 = mat.get(matOffset + 5);
        dest.put(destOffset, Math.fma(mat.get(matOffset), _selfx, Math.fma(mat.get(matOffset + 2), _selfy, mat.get(matOffset + 4))));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 4L;
        Float2OpsKernelsAddress.preMulPositionMat3x3_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 9) {
            Float2Ops.preMulPositionMat3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.preMulPositionMat3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), matOffset * 4L);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.preMulPositionMat3x3_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _mat10 = mat.get(matOffset + 1);
        float _mat11 = mat.get(matOffset + 4);
        float _mat12 = mat.get(matOffset + 7);
        dest.put(destOffset, Math.fma(mat.get(matOffset), _selfx, Math.fma(mat.get(matOffset + 3), _selfy, mat.get(matOffset + 6))));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, _mat12)));
        return dest;
    }

    public static java.nio.FloatBuffer rotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.rotate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.put(destOffset + 1, Math.fma(_selfx, _t0, _selfy * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2) {
            Float2Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.rotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle, pivotX, pivotY);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.get(srcOffset) - pivotX;
        float _t3 = src.get(srcOffset + 1) - pivotY;
        dest.put(destOffset, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)));
        dest.put(destOffset + 1, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 4L;
        Float2OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 2 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float2Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2OpsKernelsSegment.rotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), pivotOffset * 4L, angle);
            return dest;
        }
        Float2OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        float _pivotx = pivot.get(pivotOffset);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.get(srcOffset) - _pivotx;
        float _t3 = src.get(srcOffset + 1) - _pivoty;
        dest.put(destOffset, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx)));
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

    /**
     * The floored remainder of x and y, exactly kotlin.Float.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
    private static float flooredMod(float x, float y) {
        float q = Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p24f && java.lang.Math.abs(y) <= Float.MAX_VALUE) {
            float r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0f), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        float r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }
}
