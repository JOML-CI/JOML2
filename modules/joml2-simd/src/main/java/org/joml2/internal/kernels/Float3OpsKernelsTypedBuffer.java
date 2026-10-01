// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float3Ops} whose leading storage
 * parameter is a typed {@link java.nio.FloatBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float3Ops} and its sibling kernel units. Not public API.
 */
public final class Float3OpsKernelsTypedBuffer {
    private Float3OpsKernelsTypedBuffer() {}

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.add_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, otherX + _selfx);
        dest.put(destOffset + 1, otherY + _selfy);
        dest.put(destOffset + 2, otherZ + _selfz);
        return dest;
    }

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Float3Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.add_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        dest.put(destOffset + 0, _otherx + _selfx);
        dest.put(destOffset + 1, _othery + _selfy);
        dest.put(destOffset + 2, _otherz + _selfz);
        return dest;
    }

    public static java.nio.FloatBuffer div_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer div_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.div_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, scalar);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer div_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, _selfx / scalar);
        dest.put(destOffset + 1, _selfy / scalar);
        dest.put(destOffset + 2, _selfz / scalar);
        return dest;
    }

    public static java.nio.FloatBuffer div_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer div_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.div_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer div_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, _selfx / otherX);
        dest.put(destOffset + 1, _selfy / otherY);
        dest.put(destOffset + 2, _selfz / otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer div_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer div_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Float3Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.div_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer div_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        dest.put(destOffset + 0, _selfx / _otherx);
        dest.put(destOffset + 1, _selfy / _othery);
        dest.put(destOffset + 2, _selfz / _otherz);
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY, float cZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY, float cZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b, cX, cY, cZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.fma_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, b, cX, cY, cZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, cX, cY, cZ);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY, float cZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.fma(_selfx, b, cX));
        dest.put(destOffset + 1, Math.fma(_selfy, b, cY));
        dest.put(destOffset + 2, Math.fma(_selfz, b, cZ));
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) cOffset * 4L;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 3) {
            Float3Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, c.array(), c.arrayOffset() + cOffset, b);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && c.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.fma_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(c.duplicate().position(0)), (long) cOffset * 4L, b);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, c, cOffset, b);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _cx = c.get(cOffset + 0);
        float _cy = c.get(cOffset + 1);
        float _cz = c.get(cOffset + 2);
        dest.put(destOffset + 0, Math.fma(_selfx, b, _cx));
        dest.put(destOffset + 1, Math.fma(_selfy, b, _cy));
        dest.put(destOffset + 2, Math.fma(_selfz, b, _cz));
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, cX, cY, cZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.fma_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, bX, bY, bZ, cX, cY, cZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.fma(_selfx, bX, cX));
        dest.put(destOffset + 1, Math.fma(_selfy, bY, cY));
        dest.put(destOffset + 2, Math.fma(_selfz, bZ, cZ));
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) bOffset * 4L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) cOffset * 4L;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 3 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 3) {
            Float3Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && b.order() == java.nio.ByteOrder.nativeOrder() && c.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.fma_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(b.duplicate().position(0)), (long) bOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(c.duplicate().position(0)), (long) cOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _bx = b.get(bOffset + 0);
        float _by = b.get(bOffset + 1);
        float _bz = b.get(bOffset + 2);
        float _cx = c.get(cOffset + 0);
        float _cy = c.get(cOffset + 1);
        float _cz = c.get(cOffset + 2);
        dest.put(destOffset + 0, Math.fma(_selfx, _bx, _cx));
        dest.put(destOffset + 1, Math.fma(_selfy, _by, _cy));
        dest.put(destOffset + 2, Math.fma(_selfz, _bz, _cz));
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, scalar);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, scalar * _selfx);
        dest.put(destOffset + 1, scalar * _selfy);
        dest.put(destOffset + 2, scalar * _selfz);
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, otherX * _selfx);
        dest.put(destOffset + 1, otherY * _selfy);
        dest.put(destOffset + 2, otherZ * _selfz);
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Float3Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        dest.put(destOffset + 0, _otherx * _selfx);
        dest.put(destOffset + 1, _othery * _selfy);
        dest.put(destOffset + 2, _otherz * _selfz);
        return dest;
    }

    public static java.nio.FloatBuffer negate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.negate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, -_selfx);
        dest.put(destOffset + 1, -_selfy);
        dest.put(destOffset + 2, -_selfz);
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.sub_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, _selfx - otherX);
        dest.put(destOffset + 1, _selfy - otherY);
        dest.put(destOffset + 2, _selfz - otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Float3Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.sub_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        dest.put(destOffset + 0, _selfx - _otherx);
        dest.put(destOffset + 1, _selfy - _othery);
        dest.put(destOffset + 2, _selfz - _otherz);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformDirection_unsafe(java.nio.FloatBuffer dest, int destOffset, float u, float v) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformDirection_api(java.nio.FloatBuffer dest, int destOffset, float u, float v) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            Float3Ops.makeUniformDirection(dest.array(), dest.arrayOffset() + destOffset, u, v);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float3OpsKernelsSegment.makeUniformDirection_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, u, v);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.makeUniformDirection_apiGet(dest, destOffset, u, v);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformDirection_apiGet(java.nio.FloatBuffer dest, int destOffset, float u, float v) {
        float _t1 = v * 6.2831855f;
        float _t2 = (float) Math.sin(_t1);
        float _t5 = 2.0f * (float) Math.sqrt(u * (1.0f - u));
        dest.put(destOffset + 0, _t5 * (float) Math.cosFromSin(_t2, _t1));
        dest.put(destOffset + 1, _t5 * _t2);
        dest.put(destOffset + 2, Math.fma(2.0f, u, -1.0f));
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            Float3Ops.set(dest.array(), dest.arrayOffset() + destOffset, vX, vY, vZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float3OpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, vX, vY, vZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ) {
        dest.put(destOffset + 0, vX);
        dest.put(destOffset + 1, vY);
        dest.put(destOffset + 2, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Float3Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), (long) vOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        dest.put(destOffset + 0, _vx);
        dest.put(destOffset + 1, _vy);
        dest.put(destOffset + 2, _vz);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            Float3Ops.set(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float3OpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, s);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, float s) {
        dest.put(destOffset + 0, s);
        dest.put(destOffset + 1, s);
        dest.put(destOffset + 2, s);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float3OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            Float3Ops.makeZero(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float3OpsKernelsSegment.makeZero_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.makeZero_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.bezier_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.put(destOffset + 0, Math.fma(p1X, _t7, _selfx * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        dest.put(destOffset + 1, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        dest.put(destOffset + 2, Math.fma(p1Z, _t7, _selfz * _t8) + Math.fma(p2Z, _t6, p3Z * _t2));
        return dest;
    }

    public static java.nio.FloatBuffer bezier_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p3Offset * 4L;
        Float3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 3) {
            Float3Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.bezier_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p3.duplicate().position(0)), (long) p3Offset * 4L, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p3x = p3.get(p3Offset + 0);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.put(destOffset + 0, Math.fma(_p1x, _t7, _selfx * _t8) + Math.fma(_p2x, _t6, _p3x * _t2));
        dest.put(destOffset + 1, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        dest.put(destOffset + 2, Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.bezier2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.put(destOffset + 0, Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4)));
        dest.put(destOffset + 1, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.put(destOffset + 2, Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4)));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        Float3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3) {
            Float3Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.bezier2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 4L, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.put(destOffset + 0, Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4)));
        dest.put(destOffset + 1, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        dest.put(destOffset + 2, Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4)));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.bezier2Tangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.put(destOffset + 0, Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        dest.put(destOffset + 1, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.put(destOffset + 2, Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        Float3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3) {
            Float3Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.bezier2Tangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 4L, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.put(destOffset + 0, Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        dest.put(destOffset + 1, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        dest.put(destOffset + 2, Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.bezierTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.put(destOffset + 0, Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        dest.put(destOffset + 1, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.put(destOffset + 2, Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p3Offset * 4L;
        Float3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 3) {
            Float3Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.bezierTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p3.duplicate().position(0)), (long) p3Offset * 4L, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p3x = p3.get(p3Offset + 0);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.put(destOffset + 0, Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        dest.put(destOffset + 1, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.put(destOffset + 2, Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.catmullRom_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.put(destOffset + 0, 0.5f * (Math.fma(2.0f, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        dest.put(destOffset + 1, 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        dest.put(destOffset + 2, 0.5f * (Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), _t0, Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1)));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p3Offset * 4L;
        Float3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 3) {
            Float3Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.catmullRom_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p3.duplicate().position(0)), (long) p3Offset * 4L, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p3x = p3.get(p3Offset + 0);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.put(destOffset + 0, 0.5f * (Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), _t0, Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        dest.put(destOffset + 1, 0.5f * (Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), _t0, Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        dest.put(destOffset + 2, 0.5f * (Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + Math.fma(Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), _t0, Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1)));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.catmullRomTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = t * t;
        dest.put(destOffset + 0, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.put(destOffset + 1, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        dest.put(destOffset + 2, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), Math.fma(3.0f * Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p3Offset * 4L;
        Float3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 3) {
            Float3Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.catmullRomTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p3.duplicate().position(0)), (long) p3Offset * 4L, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p3x = p3.get(p3Offset + 0);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _t0 = t * t;
        dest.put(destOffset + 0, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), Math.fma(3.0f * Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.put(destOffset + 1, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), Math.fma(3.0f * Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.put(destOffset + 2, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), Math.fma(3.0f * Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return dest;
    }

    public static java.nio.FloatBuffer hermite_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.hermite_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.put(destOffset + 0, Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.put(destOffset + 1, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        dest.put(destOffset + 2, Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
        return dest;
    }

    public static java.nio.FloatBuffer hermite_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t0Offset * 4L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) v1Offset * 4L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t1Offset * 4L;
        Float3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 3 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 3 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 3) {
            Float3Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.hermite_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(t0.duplicate().position(0)), (long) t0Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v1.duplicate().position(0)), (long) v1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(t1.duplicate().position(0)), (long) t1Offset * 4L, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0x = t0.get(t0Offset + 0);
        float _t0y = t0.get(t0Offset + 1);
        float _t0z = t0.get(t0Offset + 2);
        float _v1x = v1.get(v1Offset + 0);
        float _v1y = v1.get(v1Offset + 1);
        float _v1z = v1.get(v1Offset + 2);
        float _t1x = t1.get(t1Offset + 0);
        float _t1y = t1.get(t1Offset + 1);
        float _t1z = t1.get(t1Offset + 2);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.put(destOffset + 0, Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9));
        dest.put(destOffset + 1, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        dest.put(destOffset + 2, Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9));
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.hermiteTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.put(destOffset + 0, Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.put(destOffset + 1, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        dest.put(destOffset + 2, Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t0Offset * 4L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) v1Offset * 4L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t1Offset * 4L;
        Float3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 3 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 3 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 3) {
            Float3Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.hermiteTangent_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(t0.duplicate().position(0)), (long) t0Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v1.duplicate().position(0)), (long) v1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(t1.duplicate().position(0)), (long) t1Offset * 4L, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0x = t0.get(t0Offset + 0);
        float _t0y = t0.get(t0Offset + 1);
        float _t0z = t0.get(t0Offset + 2);
        float _v1x = v1.get(v1Offset + 0);
        float _v1y = v1.get(v1Offset + 1);
        float _v1z = v1.get(v1Offset + 2);
        float _t1x = t1.get(t1Offset + 0);
        float _t1y = t1.get(t1Offset + 1);
        float _t1z = t1.get(t1Offset + 2);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.put(destOffset + 0, Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7));
        dest.put(destOffset + 1, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        dest.put(destOffset + 2, Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.fma(t, otherX - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(t, otherY - _selfy, _selfy));
        dest.put(destOffset + 2, Math.fma(t, otherZ - _selfz, _selfz));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Float3Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        dest.put(destOffset + 0, Math.fma(t, _otherx - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(t, _othery - _selfy, _selfy));
        dest.put(destOffset + 2, Math.fma(t, _otherz - _selfz, _selfz));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, tX, tY, tZ);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, tX, tY, tZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ, tX, tY, tZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, tX, tY, tZ);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.fma(tX, otherX - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(tY, otherY - _selfy, _selfy));
        dest.put(destOffset + 2, Math.fma(tZ, otherZ - _selfz, _selfz));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) tOffset * 4L;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3 && t.hasArray() && tOffset >= 0 && tOffset <= t.limit() - 3) {
            Float3Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t.array(), t.arrayOffset() + tOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder() && t.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(t.duplicate().position(0)), (long) tOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _tx = t.get(tOffset + 0);
        float _ty = t.get(tOffset + 1);
        float _tz = t.get(tOffset + 2);
        dest.put(destOffset + 0, Math.fma(_tx, _otherx - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(_ty, _othery - _selfy, _selfy));
        dest.put(destOffset + 2, Math.fma(_tz, _otherz - _selfz, _selfz));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.slerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t7 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t8 = Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t9 = (1.0f / (float) Math.sqrt(_t7));
        float _t12 = (1.0f / (float) Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t24 = t * (float) Math.sqrt(_t8) + (1.0f - t) * (float) Math.sqrt(_t7);
        float _t26 = Math.fma(otherZ * _t12, _t17, Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        float _t33 = Math.fma(otherZ, _t12, -(_t26 * _t17));
        float _t34 = Math.fma(otherX, _t12, -(_t26 * _t14));
        float _t35 = Math.fma(otherY, _t12, -(_t26 * _t20));
        float _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = Math.fma(_t39, _t17, _t33);
        float _t41 = Math.fma(_t39, _t14, _t34);
        float _t42 = Math.fma(_t39, _t20, _t35);
        float _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t50 = t * (float) Math.atan2((float) Math.sqrt(_t46), _t26);
        float _sp0 = _t24 * (float) Math.sin(_t50) * (1.0f / (float) Math.sqrt(_t46));
        float _t55 = _t24 * (float) Math.cos(_t50);
        dest.put(destOffset + 0, Math.fma(_t14, _t55, _sp0 * _t41));
        dest.put(destOffset + 1, Math.fma(_t20, _t55, _sp0 * _t42));
        dest.put(destOffset + 2, Math.fma(_t17, _t55, _sp0 * _t40));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Float3OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    public static java.nio.FloatBuffer slerp_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.slerp_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = (1.0f / (float) Math.sqrt(_t24));
        float _t29 = (1.0f / (float) Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (Math.abs(_t31) < Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = Math.fma(_t28 * _t9, _t31, Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -Math.fma(_t61, _t31, Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = Math.fma(_t73, _t31, _t61);
        float _t75 = Math.fma(_t73, _t33, _t62);
        float _t76 = Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = Math.fma(_t85, _t85, Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) Math.sqrt(_t91));
        float _t95 = t * (float) Math.atan2((float) Math.sqrt(_t91), _t53 * _t78);
        float _t99 = _t48 * (float) Math.sin(_t95);
        float _t100 = _t48 * (float) Math.cos(_t95);
        float _t104, _t105, _t106;
        if (_t91 > 0.0f) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (Math.fma(_t74, _t74, Math.fma(_t75, _t75, _t76 * _t76)) <= 1.4551915E-11f) {
                    dest.put(destOffset + 0, Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv);
                    dest.put(destOffset + 1, Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv);
                    dest.put(destOffset + 2, Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv);
                } else {
                    dest.put(destOffset + 0, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                    dest.put(destOffset + 1, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                    dest.put(destOffset + 2, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
                }
            } else {
                dest.put(destOffset + 0, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                dest.put(destOffset + 1, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                dest.put(destOffset + 2, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
            }
        } else {
            dest.put(destOffset + 0, Math.fma(t, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, Math.fma(t, otherY - _selfy, _selfy));
            dest.put(destOffset + 2, Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    public static java.nio.FloatBuffer slerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Float3Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.slerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _t7 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t8 = Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t9 = (1.0f / (float) Math.sqrt(_t7));
        float _t12 = (1.0f / (float) Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t24 = t * (float) Math.sqrt(_t8) + (1.0f - t) * (float) Math.sqrt(_t7);
        float _t26 = Math.fma(_otherz * _t12, _t17, Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        float _t33 = Math.fma(_otherz, _t12, -(_t26 * _t17));
        float _t34 = Math.fma(_otherx, _t12, -(_t26 * _t14));
        float _t35 = Math.fma(_othery, _t12, -(_t26 * _t20));
        float _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = Math.fma(_t39, _t17, _t33);
        float _t41 = Math.fma(_t39, _t14, _t34);
        float _t42 = Math.fma(_t39, _t20, _t35);
        float _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t50 = t * (float) Math.atan2((float) Math.sqrt(_t46), _t26);
        float _sp0 = _t24 * (float) Math.sin(_t50) * (1.0f / (float) Math.sqrt(_t46));
        float _t55 = _t24 * (float) Math.cos(_t50);
        dest.put(destOffset + 0, Math.fma(_t14, _t55, _sp0 * _t41));
        dest.put(destOffset + 1, Math.fma(_t20, _t55, _sp0 * _t42));
        dest.put(destOffset + 2, Math.fma(_t17, _t55, _sp0 * _t40));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float3OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.FloatBuffer slerp_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Float3OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.slerp_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L, t);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = (1.0f / (float) Math.sqrt(_t24));
        float _t29 = (1.0f / (float) Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (Math.abs(_t31) < Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0f;
            _t52 = -_t33;
        } else {
            _t49 = 0.0f;
            _t50 = -_t35;
            _t52 = _t31;
        }
        float _t53 = Math.fma(_t28 * _t9, _t31, Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        float _t61 = Math.fma(_t28, _t9, -(_t53 * _t31));
        float _t62 = Math.fma(_t28, _t10, -(_t53 * _t33));
        float _t63 = Math.fma(_t28, _t11, -(_t53 * _t35));
        float _t68 = (1.0f / (float) Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
        float _t69 = _t68 * _t49;
        float _t70 = _t68 * _t50;
        float _t71 = _t68 * _t52;
        float _t73 = -Math.fma(_t61, _t31, Math.fma(_t62, _t33, _t63 * _t35));
        float _t74 = Math.fma(_t73, _t31, _t61);
        float _t75 = Math.fma(_t73, _t33, _t62);
        float _t76 = Math.fma(_t73, _t35, _t63);
        float _t78 = unitScale(_t75, _t76, _t74);
        float _t85 = _t74 * _t78;
        float _t86 = _t75 * _t78;
        float _t87 = _t76 * _t78;
        float _t91 = Math.fma(_t85, _t85, Math.fma(_t86, _t86, _t87 * _t87));
        float _t93 = (1.0f / (float) Math.sqrt(_t91));
        float _t95 = t * (float) Math.atan2((float) Math.sqrt(_t91), _t53 * _t78);
        float _t99 = _t48 * (float) Math.sin(_t95);
        float _t100 = _t48 * (float) Math.cos(_t95);
        float _t104, _t105, _t106;
        if (_t91 > 0.0f) {
            _t104 = _t93 * _t86;
            _t105 = _t93 * _t85;
            _t106 = _t93 * _t87;
        } else {
            _t104 = _t69;
            _t105 = _t70;
            _t106 = _t71;
        }
        if (_t24 * _t25 > 0.0f) {
            if (_t53 < 0.0f) {
                if (Math.fma(_t74, _t74, Math.fma(_t75, _t75, _t76 * _t76)) <= 1.4551915E-11f) {
                    dest.put(destOffset + 0, Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv);
                    dest.put(destOffset + 1, Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv);
                    dest.put(destOffset + 2, Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv);
                } else {
                    dest.put(destOffset + 0, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                    dest.put(destOffset + 1, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                    dest.put(destOffset + 2, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
                }
            } else {
                dest.put(destOffset + 0, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                dest.put(destOffset + 1, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                dest.put(destOffset + 2, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
            }
        } else {
            dest.put(destOffset + 0, Math.fma(t, _otherx - _selfx, _selfx));
            dest.put(destOffset + 1, Math.fma(t, _othery - _selfy, _selfy));
            dest.put(destOffset + 2, Math.fma(t, _otherz - _selfz, _selfz));
        }
        return dest;
    }

    public static java.nio.FloatBuffer absolute_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer absolute_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.absolute(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.absolute_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.absolute_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer absolute_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.abs(_selfx));
        dest.put(destOffset + 1, Math.abs(_selfy));
        dest.put(destOffset + 2, Math.abs(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer acos_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer acos_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.acos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.acos_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.acos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer acos_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.acos(_selfx));
        dest.put(destOffset + 1, (float) Math.acos(_selfy));
        dest.put(destOffset + 2, (float) Math.acos(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, bX, bY, bZ, scalar);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float scalar) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.fma(scalar, bX, _selfx));
        dest.put(destOffset + 1, Math.fma(scalar, bY, _selfy));
        dest.put(destOffset + 2, Math.fma(scalar, bZ, _selfz));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) bOffset * 4L;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 3) {
            Float3Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && b.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(b.duplicate().position(0)), (long) bOffset * 4L, scalar);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _bx = b.get(bOffset + 0);
        float _by = b.get(bOffset + 1);
        float _bz = b.get(bOffset + 2);
        dest.put(destOffset + 0, Math.fma(scalar, _bx, _selfx));
        dest.put(destOffset + 1, Math.fma(scalar, _by, _selfy));
        dest.put(destOffset + 2, Math.fma(scalar, _bz, _selfz));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, cX, cY, cZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, bX, bY, bZ, cX, cY, cZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.fma(bX, cX, _selfx));
        dest.put(destOffset + 1, Math.fma(bY, cY, _selfy));
        dest.put(destOffset + 2, Math.fma(bZ, cZ, _selfz));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) bOffset * 4L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) cOffset * 4L;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 3 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 3) {
            Float3Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && b.order() == java.nio.ByteOrder.nativeOrder() && c.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(b.duplicate().position(0)), (long) bOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(c.duplicate().position(0)), (long) cOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _bx = b.get(bOffset + 0);
        float _by = b.get(bOffset + 1);
        float _bz = b.get(bOffset + 2);
        float _cx = c.get(cOffset + 0);
        float _cy = c.get(cOffset + 1);
        float _cz = c.get(cOffset + 2);
        dest.put(destOffset + 0, Math.fma(_bx, _cx, _selfx));
        dest.put(destOffset + 1, Math.fma(_by, _cy, _selfy));
        dest.put(destOffset + 2, Math.fma(_bz, _cz, _selfz));
        return dest;
    }

    public static float angleBetween_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float angleBetween_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.angleBetween_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
        }
        return Float3OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, otherX, otherY, otherZ);
    }

    public static float angleBetween_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t6 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t7 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _t8 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        float _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ);
        return (float) Math.atan2((float) Math.sqrt(_ct0), Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    public static float angleBetween_degenerate(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float angleBetween_degenerate_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.angleBetween_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
        }
        return Float3OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, otherX, otherY, otherZ);
    }

    public static float angleBetween_degenerate_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = unitScale(otherX, otherY, otherZ);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = otherZ * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = otherY * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = otherX * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t8, _t12, -(_t13 * _t11));
        float _t23 = unitScale(_t21, _t22, _t20);
        float _t27 = _t20 * _t23;
        float _t28 = _t21 * _t23;
        float _t29 = _t22 * _t23;
        return (float) Math.atan2((float) Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    public static float angleBetween_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        return Float3OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            return Float3Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.angleBetween_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _t6 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t7 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _t8 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        float _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return (float) Math.atan2((float) Math.sqrt(_ct0), Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    public static float angleBetween_degenerate(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        return Float3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_degenerate_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            return Float3OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.angleBetween_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _t0 = unitScale(_otherx, _othery, _otherz);
        float _t1 = unitScale(_selfx, _selfy, _selfz);
        float _t8 = _otherz * _t0;
        float _t9 = _selfy * _t1;
        float _t10 = _othery * _t0;
        float _t11 = _selfz * _t1;
        float _t12 = _selfx * _t1;
        float _t13 = _otherx * _t0;
        float _t20 = Math.fma(_t8, _t9, -(_t10 * _t11));
        float _t21 = Math.fma(_t10, _t12, -(_t13 * _t9));
        float _t22 = Math.fma(_t8, _t12, -(_t13 * _t11));
        float _t23 = unitScale(_t21, _t22, _t20);
        float _t27 = _t20 * _t23;
        float _t28 = _t21 * _t23;
        float _t29 = _t22 * _t23;
        return (float) Math.atan2((float) Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    public static java.nio.FloatBuffer asin_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer asin_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.asin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.asin_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.asin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer asin_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.asin(_selfx));
        dest.put(destOffset + 1, (float) Math.asin(_selfy));
        dest.put(destOffset + 2, (float) Math.asin(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer atan_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer atan_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.atan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.atan_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.atan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer atan_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.atan(_selfx));
        dest.put(destOffset + 1, (float) Math.atan(_selfy));
        dest.put(destOffset + 2, (float) Math.atan(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer atan2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.atan2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, x);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.atan2(_selfx, x));
        dest.put(destOffset + 1, (float) Math.atan2(_selfy, x));
        dest.put(destOffset + 2, (float) Math.atan2(_selfz, x));
        return dest;
    }

    public static java.nio.FloatBuffer atan2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY, float xZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY, float xZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, xX, xY, xZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.atan2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, xX, xY, xZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, xX, xY, xZ);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY, float xZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.atan2(_selfx, xX));
        dest.put(destOffset + 1, (float) Math.atan2(_selfy, xY));
        dest.put(destOffset + 2, (float) Math.atan2(_selfz, xZ));
        return dest;
    }

    public static java.nio.FloatBuffer atan2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) xOffset * 4L;
        Float3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && x.hasArray() && xOffset >= 0 && xOffset <= x.limit() - 3) {
            Float3Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x.array(), x.arrayOffset() + xOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && x.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.atan2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(x.duplicate().position(0)), (long) xOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x, xOffset);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _xx = x.get(xOffset + 0);
        float _xy = x.get(xOffset + 1);
        float _xz = x.get(xOffset + 2);
        dest.put(destOffset + 0, (float) Math.atan2(_selfx, _xx));
        dest.put(destOffset + 1, (float) Math.atan2(_selfy, _xy));
        dest.put(destOffset + 2, (float) Math.atan2(_selfz, _xz));
        return dest;
    }

    public static java.nio.FloatBuffer cbrt_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cbrt_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.cbrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.cbrt_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.cbrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cbrt_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.cbrt(_selfx));
        dest.put(destOffset + 1, (float) Math.cbrt(_selfy));
        dest.put(destOffset + 2, (float) Math.cbrt(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer ceil_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer ceil_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.ceil(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.ceil_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.ceil_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer ceil_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.ceil(_selfx));
        dest.put(destOffset + 1, (float) Math.ceil(_selfy));
        dest.put(destOffset + 2, (float) Math.ceil(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer clamp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min, max);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.clamp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, min, max);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, max);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.min(Math.max(_selfx, min), max));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, min), max));
        dest.put(destOffset + 2, Math.min(Math.max(_selfz, min), max));
        return dest;
    }

    public static java.nio.FloatBuffer clamp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.clamp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, minX, minY, minZ, maxX, maxY, maxZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.min(Math.max(_selfx, minX), maxX));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, minY), maxY));
        dest.put(destOffset + 2, Math.min(Math.max(_selfz, minZ), maxZ));
        return dest;
    }

    public static java.nio.FloatBuffer clamp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) minOffset * 4L;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) maxOffset * 4L;
        Float3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && min.hasArray() && minOffset >= 0 && minOffset <= min.limit() - 3 && max.hasArray() && maxOffset >= 0 && maxOffset <= max.limit() - 3) {
            Float3Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min.array(), min.arrayOffset() + minOffset, max.array(), max.arrayOffset() + maxOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && min.order() == java.nio.ByteOrder.nativeOrder() && max.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.clamp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(min.duplicate().position(0)), (long) minOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(max.duplicate().position(0)), (long) maxOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _minx = min.get(minOffset + 0);
        float _miny = min.get(minOffset + 1);
        float _minz = min.get(minOffset + 2);
        float _maxx = max.get(maxOffset + 0);
        float _maxy = max.get(maxOffset + 1);
        float _maxz = max.get(maxOffset + 2);
        dest.put(destOffset + 0, Math.min(Math.max(_selfx, _minx), _maxx));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, _miny), _maxy));
        dest.put(destOffset + 2, Math.min(Math.max(_selfz, _minz), _maxz));
        return dest;
    }

    public static java.nio.FloatBuffer closestPointOnLine_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return dest;
    }

    public static java.nio.FloatBuffer closestPointOnLine_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.closestPointOnLine(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.closestPointOnLine_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.closestPointOnLine_apiGet(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return dest;
    }

    public static java.nio.FloatBuffer closestPointOnLine_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = lineEndZ - lineStartZ;
        float _t1 = lineEndX - lineStartX;
        float _t2 = lineEndY - lineStartY;
        float _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t14 = Math.max(0.0f, Math.min(1.0f, Math.fma(_t0, _selfz - lineStartZ, Math.fma(_t1, _selfx - lineStartX, _t2 * (_selfy - lineStartY))) / _t10));
        if (_t10 > 0.0f) {
            dest.put(destOffset + 0, Math.fma(_t1, _t14, lineStartX));
            dest.put(destOffset + 1, Math.fma(_t2, _t14, lineStartY));
            dest.put(destOffset + 2, Math.fma(_t0, _t14, lineStartZ));
        } else {
            dest.put(destOffset + 0, lineStartX);
            dest.put(destOffset + 1, lineStartY);
            dest.put(destOffset + 2, lineStartZ);
        }
        return dest;
    }

    public static java.nio.FloatBuffer closestPointOnLine_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer lineStart, int lineStartOffset, java.nio.FloatBuffer lineEnd, int lineEndOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _lineStartBase = UnsafeOpsHolder.U.getLong(lineStart, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) lineStartOffset * 4L;
        long _lineEndBase = UnsafeOpsHolder.U.getLong(lineEnd, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) lineEndOffset * 4L;
        Float3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, _lineStartBase, _lineEndBase);
        return dest;
    }

    public static java.nio.FloatBuffer closestPointOnLine_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer lineStart, int lineStartOffset, java.nio.FloatBuffer lineEnd, int lineEndOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && lineStart.hasArray() && lineStartOffset >= 0 && lineStartOffset <= lineStart.limit() - 3 && lineEnd.hasArray() && lineEndOffset >= 0 && lineEndOffset <= lineEnd.limit() - 3) {
            Float3Ops.closestPointOnLine(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, lineStart.array(), lineStart.arrayOffset() + lineStartOffset, lineEnd.array(), lineEnd.arrayOffset() + lineEndOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && lineStart.order() == java.nio.ByteOrder.nativeOrder() && lineEnd.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.closestPointOnLine_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(lineStart.duplicate().position(0)), (long) lineStartOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(lineEnd.duplicate().position(0)), (long) lineEndOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.closestPointOnLine_apiGet(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
        return dest;
    }

    public static java.nio.FloatBuffer closestPointOnLine_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer lineStart, int lineStartOffset, java.nio.FloatBuffer lineEnd, int lineEndOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _lineStartx = lineStart.get(lineStartOffset + 0);
        float _lineStarty = lineStart.get(lineStartOffset + 1);
        float _lineStartz = lineStart.get(lineStartOffset + 2);
        float _lineEndx = lineEnd.get(lineEndOffset + 0);
        float _lineEndy = lineEnd.get(lineEndOffset + 1);
        float _lineEndz = lineEnd.get(lineEndOffset + 2);
        float _t0 = _lineEndz - _lineStartz;
        float _t1 = _lineEndx - _lineStartx;
        float _t2 = _lineEndy - _lineStarty;
        float _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t14 = Math.max(0.0f, Math.min(1.0f, Math.fma(_t0, _selfz - _lineStartz, Math.fma(_t1, _selfx - _lineStartx, _t2 * (_selfy - _lineStarty))) / _t10));
        if (_t10 > 0.0f) {
            dest.put(destOffset + 0, Math.fma(_t1, _t14, _lineStartx));
            dest.put(destOffset + 1, Math.fma(_t2, _t14, _lineStarty));
            dest.put(destOffset + 2, Math.fma(_t0, _t14, _lineStartz));
        } else {
            dest.put(destOffset + 0, _lineStartx);
            dest.put(destOffset + 1, _lineStarty);
            dest.put(destOffset + 2, _lineStartz);
        }
        return dest;
    }

    public static float compAdd_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static float compAdd_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.compAdd(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.compAdd_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.compAdd_apiGet(src, srcOffset);
    }

    public static float compAdd_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        return _selfz + (_selfx + _selfy);
    }

    public static float compMax_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static float compMax_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.compMax(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.compMax_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.compMax_apiGet(src, srcOffset);
    }

    public static float compMax_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        return Math.max(Math.max(_selfx, _selfy), _selfz);
    }

    public static float compMin_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static float compMin_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.compMin(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.compMin_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.compMin_apiGet(src, srcOffset);
    }

    public static float compMin_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        return Math.min(Math.min(_selfx, _selfy), _selfz);
    }

    public static float compMul_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static float compMul_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.compMul(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.compMul_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.compMul_apiGet(src, srcOffset);
    }

    public static float compMul_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        return _selfz * _selfx * _selfy;
    }

    public static java.nio.FloatBuffer copySign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.copySign_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, sign);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.copySign(_selfx, sign));
        dest.put(destOffset + 1, Math.copySign(_selfy, sign));
        dest.put(destOffset + 2, Math.copySign(_selfz, sign));
        return dest;
    }

    public static java.nio.FloatBuffer copySign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY, float signZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY, float signZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, signX, signY, signZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.copySign_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, signX, signY, signZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, signX, signY, signZ);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY, float signZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.copySign(_selfx, signX));
        dest.put(destOffset + 1, Math.copySign(_selfy, signY));
        dest.put(destOffset + 2, Math.copySign(_selfz, signZ));
        return dest;
    }

    public static java.nio.FloatBuffer copySign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) signOffset * 4L;
        Float3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && sign.hasArray() && signOffset >= 0 && signOffset <= sign.limit() - 3) {
            Float3Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign.array(), sign.arrayOffset() + signOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && sign.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.copySign_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(sign.duplicate().position(0)), (long) signOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign, signOffset);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _signx = sign.get(signOffset + 0);
        float _signy = sign.get(signOffset + 1);
        float _signz = sign.get(signOffset + 2);
        dest.put(destOffset + 0, Math.copySign(_selfx, _signx));
        dest.put(destOffset + 1, Math.copySign(_selfy, _signy));
        dest.put(destOffset + 2, Math.copySign(_selfz, _signz));
        return dest;
    }

    public static java.nio.FloatBuffer cos_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cos_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.cos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.cos_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.cos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cos_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.cos(_selfx));
        dest.put(destOffset + 1, (float) Math.cos(_selfy));
        dest.put(destOffset + 2, (float) Math.cos(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer cosh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cosh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.cosh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.cosh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.cosh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cosh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.cosh(_selfx));
        dest.put(destOffset + 1, (float) Math.cosh(_selfy));
        dest.put(destOffset + 2, (float) Math.cosh(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer cross_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer cross_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.cross(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.cross_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.cross_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer cross_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        dest.put(destOffset + 1, Math.fma(otherX, _selfz, -(otherZ * _selfx)));
        dest.put(destOffset + 2, Math.fma(otherY, _selfx, -(otherX * _selfy)));
        return dest;
    }

    public static java.nio.FloatBuffer cross_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer cross_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Float3Ops.cross(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.cross_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.cross_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cross_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        dest.put(destOffset + 0, Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        dest.put(destOffset + 1, Math.fma(_otherx, _selfz, -(_otherz * _selfx)));
        dest.put(destOffset + 2, Math.fma(_othery, _selfx, -(_otherx * _selfy)));
        return dest;
    }

    public static java.nio.FloatBuffer degrees_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer degrees_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.degrees(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.degrees_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.degrees_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer degrees_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.toDegrees(_selfx));
        dest.put(destOffset + 1, (float) Math.toDegrees(_selfy));
        dest.put(destOffset + 2, (float) Math.toDegrees(_selfz));
        return dest;
    }

    public static float distance_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float distance_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.distance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.distance_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
        }
        return Float3OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, otherX, otherY, otherZ);
    }

    public static float distance_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = _selfz - otherZ;
        float _t1 = _selfx - otherX;
        float _t2 = _selfy - otherY;
        return (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    public static float distance_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        return Float3OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static float distance_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            return Float3Ops.distance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.distance_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float distance_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _t0 = _selfz - _otherz;
        float _t1 = _selfx - _otherx;
        float _t2 = _selfy - _othery;
        return (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    public static float distanceSquared_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float distanceSquared_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.distanceSquared_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
        }
        return Float3OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, otherX, otherY, otherZ);
    }

    public static float distanceSquared_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = _selfz - otherZ;
        float _t1 = _selfx - otherX;
        float _t2 = _selfy - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }

    public static float distanceSquared_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        return Float3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static float distanceSquared_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            return Float3Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.distanceSquared_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float distanceSquared_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _t0 = _selfz - _otherz;
        float _t1 = _selfx - _otherx;
        float _t2 = _selfy - _othery;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }

    public static float dot_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float dot_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.dot(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.dot_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
        }
        return Float3OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, otherX, otherY, otherZ);
    }

    public static float dot_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        return Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy));
    }

    public static float dot_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        return Float3OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            return Float3Ops.dot(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.dot_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float dot_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        return Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    public static java.nio.FloatBuffer exp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer exp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.exp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.exp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.exp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer exp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.exp(_selfx));
        dest.put(destOffset + 1, (float) Math.exp(_selfy));
        dest.put(destOffset + 2, (float) Math.exp(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer exp2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer exp2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.exp2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.exp2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.exp2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer exp2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.pow(2.0f, _selfx));
        dest.put(destOffset + 1, (float) Math.pow(2.0f, _selfy));
        dest.put(destOffset + 2, (float) Math.pow(2.0f, _selfz));
        return dest;
    }

    public static java.nio.FloatBuffer expm1_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer expm1_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.expm1(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.expm1_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.expm1_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer expm1_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.expm1(_selfx));
        dest.put(destOffset + 1, (float) Math.expm1(_selfy));
        dest.put(destOffset + 2, (float) Math.expm1(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, IX, IY, IZ, NrefX, NrefY, NrefZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.faceforward_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, IX, IY, IZ, NrefX, NrefY, NrefZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        if (Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY)) < 0.0f) {
            dest.put(destOffset + 0, _selfx);
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, _selfz);
        } else {
            dest.put(destOffset + 0, -_selfx);
            dest.put(destOffset + 1, -_selfy);
            dest.put(destOffset + 2, -_selfz);
        }
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) IOffset * 4L;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) NrefOffset * 4L;
        Float3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && I.hasArray() && IOffset >= 0 && IOffset <= I.limit() - 3 && Nref.hasArray() && NrefOffset >= 0 && NrefOffset <= Nref.limit() - 3) {
            Float3Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, I.array(), I.arrayOffset() + IOffset, Nref.array(), Nref.arrayOffset() + NrefOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && I.order() == java.nio.ByteOrder.nativeOrder() && Nref.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.faceforward_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(I.duplicate().position(0)), (long) IOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(Nref.duplicate().position(0)), (long) NrefOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _Ix = I.get(IOffset + 0);
        float _Iy = I.get(IOffset + 1);
        float _Iz = I.get(IOffset + 2);
        float _Nrefx = Nref.get(NrefOffset + 0);
        float _Nrefy = Nref.get(NrefOffset + 1);
        float _Nrefz = Nref.get(NrefOffset + 2);
        if (Math.fma(_Iz, _Nrefz, Math.fma(_Ix, _Nrefx, _Iy * _Nrefy)) < 0.0f) {
            dest.put(destOffset + 0, _selfx);
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, _selfz);
        } else {
            dest.put(destOffset + 0, -_selfx);
            dest.put(destOffset + 1, -_selfy);
            dest.put(destOffset + 2, -_selfz);
        }
        return dest;
    }

    public static java.nio.FloatBuffer floor_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer floor_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.floor(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.floor_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.floor_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer floor_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.floor(_selfx));
        dest.put(destOffset + 1, (float) Math.floor(_selfy));
        dest.put(destOffset + 2, (float) Math.floor(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer fract_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer fract_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.fract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.fract_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.fract_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer fract_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.min(_selfx - (float) Math.floor(_selfx), 0.99999994f));
        dest.put(destOffset + 1, Math.min(_selfy - (float) Math.floor(_selfy), 0.99999994f));
        dest.put(destOffset + 2, Math.min(_selfz - (float) Math.floor(_selfz), 0.99999994f));
        return dest;
    }

    public static java.nio.FloatBuffer hypot_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.hypot_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, y);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.hypot(_selfx, y));
        dest.put(destOffset + 1, (float) Math.hypot(_selfy, y));
        dest.put(destOffset + 2, (float) Math.hypot(_selfz, y));
        return dest;
    }

    public static java.nio.FloatBuffer hypot_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY, yZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.hypot_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, yX, yY, yZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, yX, yY, yZ);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.hypot(_selfx, yX));
        dest.put(destOffset + 1, (float) Math.hypot(_selfy, yY));
        dest.put(destOffset + 2, (float) Math.hypot(_selfz, yZ));
        return dest;
    }

    public static java.nio.FloatBuffer hypot_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) yOffset * 4L;
        Float3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 3) {
            Float3Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && y.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.hypot_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(y.duplicate().position(0)), (long) yOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _yx = y.get(yOffset + 0);
        float _yy = y.get(yOffset + 1);
        float _yz = y.get(yOffset + 2);
        dest.put(destOffset + 0, (float) Math.hypot(_selfx, _yx));
        dest.put(destOffset + 1, (float) Math.hypot(_selfy, _yy));
        dest.put(destOffset + 2, (float) Math.hypot(_selfz, _yz));
        return dest;
    }

    public static java.nio.FloatBuffer inverse_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer inverse_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.inverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.inverse_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.inverse_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer inverse_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, 1.0f / _selfx);
        dest.put(destOffset + 1, 1.0f / _selfy);
        dest.put(destOffset + 2, 1.0f / _selfz);
        return dest;
    }

    public static java.nio.FloatBuffer inverseSqrt_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer inverseSqrt_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.inverseSqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.inverseSqrt_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.inverseSqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer inverseSqrt_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (1.0f / (float) Math.sqrt(_selfx)));
        dest.put(destOffset + 1, (1.0f / (float) Math.sqrt(_selfy)));
        dest.put(destOffset + 2, (1.0f / (float) Math.sqrt(_selfz)));
        return dest;
    }

    public static float length_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.length(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.length_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.length_apiGet(src, srcOffset);
    }

    public static float length_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        return (float) Math.sqrt(Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static float lengthSquared_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.lengthSquared(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.lengthSquared_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.lengthSquared_apiGet(src, srcOffset);
    }

    public static float lengthSquared_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        return Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    public static java.nio.FloatBuffer log_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.log(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.log_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.log_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.log(_selfx));
        dest.put(destOffset + 1, (float) Math.log(_selfy));
        dest.put(destOffset + 2, (float) Math.log(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer log10_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log10_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.log10(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.log10_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.log10_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log10_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.log10(_selfx));
        dest.put(destOffset + 1, (float) Math.log10(_selfy));
        dest.put(destOffset + 2, (float) Math.log10(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer log1p_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log1p_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.log1p(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.log1p_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.log1p_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log1p_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.log1p(_selfx));
        dest.put(destOffset + 1, (float) Math.log1p(_selfy));
        dest.put(destOffset + 2, (float) Math.log1p(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer log2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.log2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.log2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.log2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.log2(_selfx));
        dest.put(destOffset + 1, (float) Math.log2(_selfy));
        dest.put(destOffset + 2, (float) Math.log2(_selfz));
        return dest;
    }

    public static float manhattanDistance_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float manhattanDistance_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.manhattanDistance_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
        }
        return Float3OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, otherX, otherY, otherZ);
    }

    public static float manhattanDistance_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        return Math.abs(_selfx - otherX) + Math.abs(_selfy - otherY) + Math.abs(_selfz - otherZ);
    }

    public static float manhattanDistance_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        return Float3OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static float manhattanDistance_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            return Float3Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.manhattanDistance_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float manhattanDistance_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        return Math.abs(_selfx - _otherx) + Math.abs(_selfy - _othery) + Math.abs(_selfz - _otherz);
    }

    public static float manhattanLength_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static float manhattanLength_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.manhattanLength(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.manhattanLength_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.manhattanLength_apiGet(src, srcOffset);
    }

    public static float manhattanLength_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        return Math.abs(_selfx) + Math.abs(_selfy) + Math.abs(_selfz);
    }

    public static java.nio.FloatBuffer max_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer max_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.max_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, scalar);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer max_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.max(_selfx, scalar));
        dest.put(destOffset + 1, Math.max(_selfy, scalar));
        dest.put(destOffset + 2, Math.max(_selfz, scalar));
        return dest;
    }

    public static java.nio.FloatBuffer max_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer max_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.max_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer max_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.max(_selfx, otherX));
        dest.put(destOffset + 1, Math.max(_selfy, otherY));
        dest.put(destOffset + 2, Math.max(_selfz, otherZ));
        return dest;
    }

    public static java.nio.FloatBuffer max_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer max_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Float3Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.max_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer max_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        dest.put(destOffset + 0, Math.max(_selfx, _otherx));
        dest.put(destOffset + 1, Math.max(_selfy, _othery));
        dest.put(destOffset + 2, Math.max(_selfz, _otherz));
        return dest;
    }

    public static java.nio.FloatBuffer min_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer min_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.min_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, scalar);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer min_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.min(_selfx, scalar));
        dest.put(destOffset + 1, Math.min(_selfy, scalar));
        dest.put(destOffset + 2, Math.min(_selfz, scalar));
        return dest;
    }

    public static java.nio.FloatBuffer min_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer min_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.min_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.FloatBuffer min_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.min(_selfx, otherX));
        dest.put(destOffset + 1, Math.min(_selfy, otherY));
        dest.put(destOffset + 2, Math.min(_selfz, otherZ));
        return dest;
    }

    public static java.nio.FloatBuffer min_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer min_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Float3Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.min_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer min_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        dest.put(destOffset + 0, Math.min(_selfx, _otherx));
        dest.put(destOffset + 1, Math.min(_selfy, _othery));
        dest.put(destOffset + 2, Math.min(_selfz, _otherz));
        return dest;
    }

    public static java.nio.FloatBuffer mod_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.FloatBuffer mod_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.mod_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, y);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.FloatBuffer mod_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, flooredMod(_selfx, y));
        dest.put(destOffset + 1, flooredMod(_selfy, y));
        dest.put(destOffset + 2, flooredMod(_selfz, y));
        return dest;
    }

    public static java.nio.FloatBuffer mod_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.nio.FloatBuffer mod_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY, yZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.mod_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, yX, yY, yZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, yX, yY, yZ);
        return dest;
    }

    public static java.nio.FloatBuffer mod_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, flooredMod(_selfx, yX));
        dest.put(destOffset + 1, flooredMod(_selfy, yY));
        dest.put(destOffset + 2, flooredMod(_selfz, yZ));
        return dest;
    }

    public static java.nio.FloatBuffer mod_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) yOffset * 4L;
        Float3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.FloatBuffer mod_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 3) {
            Float3Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && y.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.mod_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(y.duplicate().position(0)), (long) yOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mod_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _yx = y.get(yOffset + 0);
        float _yy = y.get(yOffset + 1);
        float _yz = y.get(yOffset + 2);
        dest.put(destOffset + 0, flooredMod(_selfx, _yx));
        dest.put(destOffset + 1, flooredMod(_selfy, _yy));
        dest.put(destOffset + 2, flooredMod(_selfz, _yz));
        return dest;
    }

    public static java.nio.FloatBuffer nextDown_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer nextDown_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.nextDown(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.nextDown_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.nextDown_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer nextDown_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.nextDown(_selfx));
        dest.put(destOffset + 1, Math.nextDown(_selfy));
        dest.put(destOffset + 2, Math.nextDown(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer nextUp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer nextUp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.nextUp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.nextUp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.nextUp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer nextUp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.nextUp(_selfx));
        dest.put(destOffset + 1, Math.nextUp(_selfy));
        dest.put(destOffset + 2, Math.nextUp(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer normalize_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalize_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.normalize(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.normalize_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.normalize_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalize_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.put(destOffset + 0, _selfx * _t3);
            dest.put(destOffset + 1, _selfy * _t3);
            dest.put(destOffset + 2, _selfz * _t3);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer normalizeMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.FloatBuffer normalizeMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.normalizeMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, length);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.normalizeMul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, length);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.normalizeMul_apiGet(dest, destOffset, src, srcOffset, length);
        return dest;
    }

    public static java.nio.FloatBuffer normalizeMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t4 = length * (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.put(destOffset + 0, _selfx * _t4);
            dest.put(destOffset + 1, _selfy * _t4);
            dest.put(destOffset + 2, _selfz * _t4);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static float orientedAngle_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3Ops.orientedAngle(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.orientedAngle_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ, normalX, normalY, normalZ);
        }
        return Float3OpsKernelsTypedBuffer.orientedAngle_apiGet(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t7 = unitScale(normalX, normalY, normalZ);
        float _t9 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _t10 = Math.fma(otherX, _selfz, -(otherZ * _selfx));
        float _t11 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsTypedBuffer.orientedAngle_degenerate(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        float _t18 = (float) Math.atan2((float) Math.sqrt(_ct0), Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
        return Math.fma(_t9, normalZ * _t7, Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0f ? -_t18 : _t18;
    }

    public static float orientedAngle_degenerate(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return Float3OpsKernelsTypedBuffer.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_degenerate_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_degenerate_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Float3OpsKernelsArray.orientedAngle_degenerate(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.orientedAngle_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, otherX, otherY, otherZ, normalX, normalY, normalZ);
        }
        return Float3OpsKernelsTypedBuffer.orientedAngle_degenerate_apiGet(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_degenerate_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = unitScale(normalX, normalY, normalZ);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherY * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = otherX * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = otherZ * _t1;
        float _t14 = _selfz * _t2;
        float _t23 = Math.fma(_t9, _t10, -(_t11 * _t12));
        float _t24 = Math.fma(_t13, _t12, -(_t9 * _t14));
        float _t25 = Math.fma(_t11, _t14, -(_t13 * _t10));
        float _t27 = unitScale(_t24, _t25, _t23);
        float _t31 = _t23 * _t27;
        float _t32 = _t24 * _t27;
        float _t33 = _t25 * _t27;
        float _t40 = (float) Math.atan2((float) Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(normalZ * _t0, _t31, Math.fma(normalX * _t0, _t32, normalY * _t0 * _t33)) < 0.0f ? -_t40 : _t40;
    }

    public static float orientedAngle_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer normal, int normalOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 4L;
        return Float3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static float orientedAngle_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            return Float3Ops.orientedAngle(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, normal.array(), normal.arrayOffset() + normalOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder() && normal.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.orientedAngle_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(normal.duplicate().position(0)), (long) normalOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.orientedAngle_apiGet(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    public static float orientedAngle_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer normal, int normalOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _normalx = normal.get(normalOffset + 0);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _t7 = unitScale(_normalx, _normaly, _normalz);
        float _t9 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _t10 = Math.fma(_otherx, _selfz, -(_otherz * _selfx));
        float _t11 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsTypedBuffer.orientedAngle_degenerate(src, srcOffset, other, otherOffset, normal, normalOffset);
        float _t18 = (float) Math.atan2((float) Math.sqrt(_ct0), Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
        return Math.fma(_t9, _normalz * _t7, Math.fma(_t10, _normaly * _t7, _t11 * (_normalx * _t7))) < 0.0f ? -_t18 : _t18;
    }

    public static float orientedAngle_degenerate(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, other, otherOffset, normal, normalOffset);
        return Float3OpsKernelsTypedBuffer.orientedAngle_degenerate_api(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    public static float orientedAngle_degenerate_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer normal, int normalOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 4L;
        return Float3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static float orientedAngle_degenerate_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            return Float3OpsKernelsArray.orientedAngle_degenerate(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, normal.array(), normal.arrayOffset() + normalOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder() && normal.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float3OpsKernelsSegment.orientedAngle_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), (long) otherOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(normal.duplicate().position(0)), (long) normalOffset * 4L);
        }
        return Float3OpsKernelsTypedBuffer.orientedAngle_degenerate_apiGet(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    public static float orientedAngle_degenerate_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer normal, int normalOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _normalx = normal.get(normalOffset + 0);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _t0 = unitScale(_normalx, _normaly, _normalz);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _othery * _t1;
        float _t10 = _selfx * _t2;
        float _t11 = _otherx * _t1;
        float _t12 = _selfy * _t2;
        float _t13 = _otherz * _t1;
        float _t14 = _selfz * _t2;
        float _t23 = Math.fma(_t9, _t10, -(_t11 * _t12));
        float _t24 = Math.fma(_t13, _t12, -(_t9 * _t14));
        float _t25 = Math.fma(_t11, _t14, -(_t13 * _t10));
        float _t27 = unitScale(_t24, _t25, _t23);
        float _t31 = _t23 * _t27;
        float _t32 = _t24 * _t27;
        float _t33 = _t25 * _t27;
        float _t40 = (float) Math.atan2((float) Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(_normalz * _t0, _t31, Math.fma(_normalx * _t0, _t32, _normaly * _t0 * _t33)) < 0.0f ? -_t40 : _t40;
    }

    public static java.nio.FloatBuffer outerProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY, float rowZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY, float rowZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rowX, rowY, rowZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.outerProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, rowX, rowY, rowZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY, float rowZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, rowX * _selfx);
        dest.put(destOffset + 1, rowX * _selfy);
        dest.put(destOffset + 2, rowX * _selfz);
        dest.put(destOffset + 3, rowY * _selfx);
        dest.put(destOffset + 4, rowY * _selfy);
        dest.put(destOffset + 5, rowY * _selfz);
        dest.put(destOffset + 6, rowZ * _selfx);
        dest.put(destOffset + 7, rowZ * _selfy);
        dest.put(destOffset + 8, rowZ * _selfz);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rowOffset * 4L;
        Float3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && row.hasArray() && rowOffset >= 0 && rowOffset <= row.limit() - 3) {
            Float3Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row.array(), row.arrayOffset() + rowOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && row.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.outerProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(row.duplicate().position(0)), (long) rowOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, row, rowOffset);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _rowx = row.get(rowOffset + 0);
        float _rowy = row.get(rowOffset + 1);
        float _rowz = row.get(rowOffset + 2);
        dest.put(destOffset + 0, _rowx * _selfx);
        dest.put(destOffset + 1, _rowx * _selfy);
        dest.put(destOffset + 2, _rowx * _selfz);
        dest.put(destOffset + 3, _rowy * _selfx);
        dest.put(destOffset + 4, _rowy * _selfy);
        dest.put(destOffset + 5, _rowy * _selfz);
        dest.put(destOffset + 6, _rowz * _selfx);
        dest.put(destOffset + 7, _rowz * _selfy);
        dest.put(destOffset + 8, _rowz * _selfz);
        return dest;
    }

    public static java.nio.FloatBuffer perpendicular_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.perpendicular_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer perpendicular_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.perpendicular(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.perpendicular_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.perpendicular_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer perpendicular_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        if (Math.abs(_selfz) < Math.abs(_selfx)) {
            dest.put(destOffset + 0, _selfy);
            dest.put(destOffset + 1, -_selfx);
            dest.put(destOffset + 2, 0.0f);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, _selfz);
            dest.put(destOffset + 2, -_selfy);
        }
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.pow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, exponent);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.pow(_selfx, exponent));
        dest.put(destOffset + 1, (float) Math.pow(_selfy, exponent));
        dest.put(destOffset + 2, (float) Math.pow(_selfz, exponent));
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponentX, exponentY, exponentZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.pow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, exponentX, exponentY, exponentZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.pow(_selfx, exponentX));
        dest.put(destOffset + 1, (float) Math.pow(_selfy, exponentY));
        dest.put(destOffset + 2, (float) Math.pow(_selfz, exponentZ));
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) exponentOffset * 4L;
        Float3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && exponent.hasArray() && exponentOffset >= 0 && exponentOffset <= exponent.limit() - 3) {
            Float3Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent.array(), exponent.arrayOffset() + exponentOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && exponent.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.pow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(exponent.duplicate().position(0)), (long) exponentOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _exponentx = exponent.get(exponentOffset + 0);
        float _exponenty = exponent.get(exponentOffset + 1);
        float _exponentz = exponent.get(exponentOffset + 2);
        dest.put(destOffset + 0, (float) Math.pow(_selfx, _exponentx));
        dest.put(destOffset + 1, (float) Math.pow(_selfy, _exponenty));
        dest.put(destOffset + 2, (float) Math.pow(_selfz, _exponentz));
        return dest;
    }

    public static java.nio.FloatBuffer project_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ);
        return dest;
    }

    public static java.nio.FloatBuffer project_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, ontoX, ontoY, ontoZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.project_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, ontoX, ontoY, ontoZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ);
        return dest;
    }

    public static java.nio.FloatBuffer project_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t7 = Math.fma(ontoZ, _selfz, Math.fma(ontoX, _selfx, ontoY * _selfy)) / Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY));
        dest.put(destOffset + 0, ontoX * _t7);
        dest.put(destOffset + 1, ontoY * _t7);
        dest.put(destOffset + 2, ontoZ * _t7);
        return dest;
    }

    public static java.nio.FloatBuffer project_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) ontoOffset * 4L;
        Float3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.FloatBuffer project_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && onto.hasArray() && ontoOffset >= 0 && ontoOffset <= onto.limit() - 3) {
            Float3Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, onto.array(), onto.arrayOffset() + ontoOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && onto.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.project_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(onto.duplicate().position(0)), (long) ontoOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return dest;
    }

    public static java.nio.FloatBuffer project_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _ontox = onto.get(ontoOffset + 0);
        float _ontoy = onto.get(ontoOffset + 1);
        float _ontoz = onto.get(ontoOffset + 2);
        float _t7 = Math.fma(_ontoz, _selfz, Math.fma(_ontox, _selfx, _ontoy * _selfy)) / Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy));
        dest.put(destOffset + 0, _ontox * _t7);
        dest.put(destOffset + 1, _ontoy * _t7);
        dest.put(destOffset + 2, _ontoz * _t7);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.projectOnPlane_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, normalX, normalY, normalZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t2 = Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        dest.put(destOffset + 0, Math.fma(-normalX, _t2, _selfx));
        dest.put(destOffset + 1, Math.fma(-normalY, _t2, _selfy));
        dest.put(destOffset + 2, Math.fma(-normalZ, _t2, _selfz));
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 4L;
        Float3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            Float3Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.projectOnPlane_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(normal.duplicate().position(0)), (long) normalOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _normalx = normal.get(normalOffset + 0);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _t2 = Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest.put(destOffset + 0, Math.fma(-_normalx, _t2, _selfx));
        dest.put(destOffset + 1, Math.fma(-_normaly, _t2, _selfy));
        dest.put(destOffset + 2, Math.fma(-_normalz, _t2, _selfz));
        return dest;
    }

    public static java.nio.FloatBuffer radians_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer radians_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.radians(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.radians_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.radians_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer radians_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.toRadians(_selfx));
        dest.put(destOffset + 1, (float) Math.toRadians(_selfy));
        dest.put(destOffset + 2, (float) Math.toRadians(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer reflect_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.reflect_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, normalX, normalY, normalZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t3 = 2.0f * Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        dest.put(destOffset + 0, Math.fma(-normalX, _t3, _selfx));
        dest.put(destOffset + 1, Math.fma(-normalY, _t3, _selfy));
        dest.put(destOffset + 2, Math.fma(-normalZ, _t3, _selfz));
        return dest;
    }

    public static java.nio.FloatBuffer reflect_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 4L;
        Float3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            Float3Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.reflect_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(normal.duplicate().position(0)), (long) normalOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _normalx = normal.get(normalOffset + 0);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _t3 = 2.0f * Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest.put(destOffset + 0, Math.fma(-_normalx, _t3, _selfx));
        dest.put(destOffset + 1, Math.fma(-_normaly, _t3, _selfy));
        dest.put(destOffset + 2, Math.fma(-_normalz, _t3, _selfz));
        return dest;
    }

    public static java.nio.FloatBuffer refract_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ, eta);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.refract_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, normalX, normalY, normalZ, eta);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float eta) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t3 = Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        float _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0f), eta * eta, 1.0f);
        float _t10 = Math.fma(eta, _t3, (float) Math.sqrt(Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            dest.put(destOffset + 0, Math.fma(eta, _selfx, -(normalX * _t10)));
            dest.put(destOffset + 1, Math.fma(eta, _selfy, -(normalY * _t10)));
            dest.put(destOffset + 2, Math.fma(eta, _selfz, -(normalZ * _t10)));
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer refract_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 4L;
        Float3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            Float3Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset, eta);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.refract_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(normal.duplicate().position(0)), (long) normalOffset * 4L, eta);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _normalx = normal.get(normalOffset + 0);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _t3 = Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        float _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0f), eta * eta, 1.0f);
        float _t10 = Math.fma(eta, _t3, (float) Math.sqrt(Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            dest.put(destOffset + 0, Math.fma(eta, _selfx, -(_normalx * _t10)));
            dest.put(destOffset + 1, Math.fma(eta, _selfy, -(_normaly * _t10)));
            dest.put(destOffset + 2, Math.fma(eta, _selfz, -(_normalz * _t10)));
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer round_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer round_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.round(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.round_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.round_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer round_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.rint(_selfx));
        dest.put(destOffset + 1, (float) Math.rint(_selfy));
        dest.put(destOffset + 2, (float) Math.rint(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer sign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.sign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.sign_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.sign_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.signum(_selfx));
        dest.put(destOffset + 1, Math.signum(_selfy));
        dest.put(destOffset + 2, Math.signum(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer sin_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sin_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.sin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.sin_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.sin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sin_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.sin(_selfx));
        dest.put(destOffset + 1, (float) Math.sin(_selfy));
        dest.put(destOffset + 2, (float) Math.sin(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer sinh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sinh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.sinh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.sinh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.sinh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sinh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.sinh(_selfx));
        dest.put(destOffset + 1, (float) Math.sinh(_selfy));
        dest.put(destOffset + 2, (float) Math.sinh(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0, edge1);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.smoothstep_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, edge0, edge1);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge1);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t10 = Math.max(0.0f, Math.min(1.0f, (_selfx - edge0) * _t0_inv));
        float _t11 = Math.max(0.0f, Math.min(1.0f, (_selfy - edge0) * _t0_inv));
        float _t12 = Math.max(0.0f, Math.min(1.0f, (_selfz - edge0) * _t0_inv));
        dest.put(destOffset + 0, Math.fma(-2.0f, _t10, 3.0f) * _t10 * _t10);
        dest.put(destOffset + 1, Math.fma(-2.0f, _t11, 3.0f) * _t11 * _t11);
        dest.put(destOffset + 2, Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.smoothstep_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t12 = Math.max(0.0f, Math.min(1.0f, (_selfx - edge0X) / (edge1X - edge0X)));
        float _t13 = Math.max(0.0f, Math.min(1.0f, (_selfy - edge0Y) / (edge1Y - edge0Y)));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (_selfz - edge0Z) / (edge1Z - edge0Z)));
        dest.put(destOffset + 0, Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        dest.put(destOffset + 1, Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.put(destOffset + 2, Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) edge0Offset * 4L;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) edge1Offset * 4L;
        Float3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && edge0.hasArray() && edge0Offset >= 0 && edge0Offset <= edge0.limit() - 3 && edge1.hasArray() && edge1Offset >= 0 && edge1Offset <= edge1.limit() - 3) {
            Float3Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0.array(), edge0.arrayOffset() + edge0Offset, edge1.array(), edge1.arrayOffset() + edge1Offset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && edge0.order() == java.nio.ByteOrder.nativeOrder() && edge1.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.smoothstep_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(edge0.duplicate().position(0)), (long) edge0Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(edge1.duplicate().position(0)), (long) edge1Offset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _edge0x = edge0.get(edge0Offset + 0);
        float _edge0y = edge0.get(edge0Offset + 1);
        float _edge0z = edge0.get(edge0Offset + 2);
        float _edge1x = edge1.get(edge1Offset + 0);
        float _edge1y = edge1.get(edge1Offset + 1);
        float _edge1z = edge1.get(edge1Offset + 2);
        float _t12 = Math.max(0.0f, Math.min(1.0f, (_selfx - _edge0x) / (_edge1x - _edge0x)));
        float _t13 = Math.max(0.0f, Math.min(1.0f, (_selfy - _edge0y) / (_edge1y - _edge0y)));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (_selfz - _edge0z) / (_edge1z - _edge0z)));
        dest.put(destOffset + 0, Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        dest.put(destOffset + 1, Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.put(destOffset + 2, Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        return dest;
    }

    public static java.nio.FloatBuffer sqrt_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sqrt_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.sqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.sqrt_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.sqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sqrt_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.sqrt(_selfx));
        dest.put(destOffset + 1, (float) Math.sqrt(_selfy));
        dest.put(destOffset + 2, (float) Math.sqrt(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer step_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.FloatBuffer step_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.step_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, edge);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge);
        return dest;
    }

    public static java.nio.FloatBuffer step_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, _selfx < edge ? 0.0f : 1.0f);
        dest.put(destOffset + 1, _selfy < edge ? 0.0f : 1.0f);
        dest.put(destOffset + 2, _selfz < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer step_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ);
        return dest;
    }

    public static java.nio.FloatBuffer step_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edgeX, edgeY, edgeZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.step_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, edgeX, edgeY, edgeZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ);
        return dest;
    }

    public static java.nio.FloatBuffer step_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, _selfx < edgeX ? 0.0f : 1.0f);
        dest.put(destOffset + 1, _selfy < edgeY ? 0.0f : 1.0f);
        dest.put(destOffset + 2, _selfz < edgeZ ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer step_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) edgeOffset * 4L;
        Float3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.FloatBuffer step_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && edge.hasArray() && edgeOffset >= 0 && edgeOffset <= edge.limit() - 3) {
            Float3Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge.array(), edge.arrayOffset() + edgeOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && edge.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.step_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(edge.duplicate().position(0)), (long) edgeOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return dest;
    }

    public static java.nio.FloatBuffer step_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _edgex = edge.get(edgeOffset + 0);
        float _edgey = edge.get(edgeOffset + 1);
        float _edgez = edge.get(edgeOffset + 2);
        dest.put(destOffset + 0, _selfx < _edgex ? 0.0f : 1.0f);
        dest.put(destOffset + 1, _selfy < _edgey ? 0.0f : 1.0f);
        dest.put(destOffset + 2, _selfz < _edgez ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer tan_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer tan_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.tan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.tan_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.tan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer tan_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.tan(_selfx));
        dest.put(destOffset + 1, (float) Math.tan(_selfy));
        dest.put(destOffset + 2, (float) Math.tan(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer tanh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer tanh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.tanh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.tanh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.tanh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer tanh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, (float) Math.tanh(_selfx));
        dest.put(destOffset + 1, (float) Math.tanh(_selfy));
        dest.put(destOffset + 2, (float) Math.tanh(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer triangleNormal_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.nio.FloatBuffer triangleNormal_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.triangleNormal(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.triangleNormal_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.triangleNormal_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.nio.FloatBuffer triangleNormal_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = p1Y - _selfy;
        float _t1 = p2Z - _selfz;
        float _t2 = p1Z - _selfz;
        float _t3 = p2Y - _selfy;
        float _t4 = p1X - _selfx;
        float _t5 = p2X - _selfx;
        float _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        float _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        float _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsTypedBuffer.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        float _t19 = (1.0f / (float) Math.sqrt(_ct0));
        dest.put(destOffset + 0, _t12 * _t19);
        dest.put(destOffset + 1, _t14 * _t19);
        dest.put(destOffset + 2, _t13 * _t19);
        return dest;
    }

    public static java.nio.FloatBuffer triangleNormal_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return Float3OpsKernelsTypedBuffer.triangleNormal_degenerate_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    public static java.nio.FloatBuffer triangleNormal_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.nio.FloatBuffer triangleNormal_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3OpsKernelsArray.triangleNormal_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.triangleNormal_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.triangleNormal_degenerate_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.nio.FloatBuffer triangleNormal_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t19 = Math.min(1.0f, unitScale(Math.max(Math.abs(_selfz), Math.abs(p1Z)), Math.max(Math.abs(p2Z), Math.abs(Math.max(Math.abs(_selfx), Math.abs(p1X)))), Math.max(Math.abs(Math.max(Math.abs(p2X), Math.abs(_selfy))), Math.abs(Math.max(Math.abs(p1Y), Math.abs(p2Y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        float _t38 = p1X * _t19 - _t30;
        float _t39 = p1Y * _t19 - _t32;
        float _t40 = p1Z * _t19 - _t34;
        float _t41 = p2Y * _t19 - _t32;
        float _t42 = p2X * _t19 - _t30;
        float _t43 = p2Z * _t19 - _t34;
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t56 = _t43 * _t45;
        float _t57 = _t40 * _t44;
        float _t64 = Math.fma(_t52, _t53, -(_t54 * _t55));
        float _t65 = Math.fma(_t54, _t56, -(_t57 * _t53));
        float _t66 = Math.fma(_t57, _t55, -(_t52 * _t56));
        float _t67 = unitScale(_t65, _t66, _t64);
        float _t71 = _t64 * _t67;
        float _t72 = _t65 * _t67;
        float _t73 = _t66 * _t67;
        float _t76 = Math.fma(_t71, _t71, Math.fma(_t72, _t72, _t73 * _t73));
        float _t77 = (1.0f / (float) Math.sqrt(_t76));
        if (_t76 != 0.0f) {
            dest.put(destOffset + 0, _t77 * _t72);
            dest.put(destOffset + 1, _t77 * _t73);
            dest.put(destOffset + 2, _t77 * _t71);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer triangleNormal_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        Float3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, _p1Base, _p2Base);
        return dest;
    }

    public static java.nio.FloatBuffer triangleNormal_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3) {
            Float3Ops.triangleNormal(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.triangleNormal_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.triangleNormal_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return dest;
    }

    public static java.nio.FloatBuffer triangleNormal_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _t0 = _p1y - _selfy;
        float _t1 = _p2z - _selfz;
        float _t2 = _p1z - _selfz;
        float _t3 = _p2y - _selfy;
        float _t4 = _p1x - _selfx;
        float _t5 = _p2x - _selfx;
        float _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        float _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        float _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsTypedBuffer.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        float _t19 = (1.0f / (float) Math.sqrt(_ct0));
        dest.put(destOffset + 0, _t12 * _t19);
        dest.put(destOffset + 1, _t14 * _t19);
        dest.put(destOffset + 2, _t13 * _t19);
        return dest;
    }

    public static java.nio.FloatBuffer triangleNormal_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return Float3OpsKernelsTypedBuffer.triangleNormal_degenerate_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    public static java.nio.FloatBuffer triangleNormal_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        Float3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, _p1Base, _p2Base);
        return dest;
    }

    public static java.nio.FloatBuffer triangleNormal_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3) {
            Float3OpsKernelsArray.triangleNormal_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.triangleNormal_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p1.duplicate().position(0)), (long) p1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(p2.duplicate().position(0)), (long) p2Offset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.triangleNormal_degenerate_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return dest;
    }

    public static java.nio.FloatBuffer triangleNormal_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _t19 = Math.min(1.0f, unitScale(Math.max(Math.abs(_selfz), Math.abs(_p1z)), Math.max(Math.abs(_p2z), Math.abs(Math.max(Math.abs(_selfx), Math.abs(_p1x)))), Math.max(Math.abs(Math.max(Math.abs(_p2x), Math.abs(_selfy))), Math.abs(Math.max(Math.abs(_p1y), Math.abs(_p2y))))));
        float _t30 = _selfx * _t19;
        float _t32 = _selfy * _t19;
        float _t34 = _selfz * _t19;
        float _t38 = _p1x * _t19 - _t30;
        float _t39 = _p1y * _t19 - _t32;
        float _t40 = _p1z * _t19 - _t34;
        float _t41 = _p2y * _t19 - _t32;
        float _t42 = _p2x * _t19 - _t30;
        float _t43 = _p2z * _t19 - _t34;
        float _t44 = unitScale(_t38, _t39, _t40);
        float _t45 = unitScale(_t42, _t41, _t43);
        float _t52 = _t38 * _t44;
        float _t53 = _t41 * _t45;
        float _t54 = _t39 * _t44;
        float _t55 = _t42 * _t45;
        float _t56 = _t43 * _t45;
        float _t57 = _t40 * _t44;
        float _t64 = Math.fma(_t52, _t53, -(_t54 * _t55));
        float _t65 = Math.fma(_t54, _t56, -(_t57 * _t53));
        float _t66 = Math.fma(_t57, _t55, -(_t52 * _t56));
        float _t67 = unitScale(_t65, _t66, _t64);
        float _t71 = _t64 * _t67;
        float _t72 = _t65 * _t67;
        float _t73 = _t66 * _t67;
        float _t76 = Math.fma(_t71, _t71, Math.fma(_t72, _t72, _t73 * _t73));
        float _t77 = (1.0f / (float) Math.sqrt(_t76));
        if (_t76 != 0.0f) {
            dest.put(destOffset + 0, _t77 * _t72);
            dest.put(destOffset + 1, _t77 * _t73);
            dest.put(destOffset + 2, _t77 * _t71);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer trunc_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer trunc_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.trunc(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.trunc_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.trunc_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer trunc_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, _selfx >= 0.0f ? (float) Math.floor(_selfx) : (float) Math.ceil(_selfx));
        dest.put(destOffset + 1, _selfy >= 0.0f ? (float) Math.floor(_selfy) : (float) Math.ceil(_selfy));
        dest.put(destOffset + 2, _selfz >= 0.0f ? (float) Math.floor(_selfz) : (float) Math.ceil(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer ulp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer ulp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.ulp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.ulp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.ulp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer ulp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, Math.ulp(_selfx));
        dest.put(destOffset + 1, Math.ulp(_selfy));
        dest.put(destOffset + 2, Math.ulp(_selfz));
        return dest;
    }

    public static java.nio.FloatBuffer xyz0_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.xyz0_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer xyz0_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.xyz0(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.xyz0_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.xyz0_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer xyz0_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, _selfx);
        dest.put(destOffset + 1, _selfy);
        dest.put(destOffset + 2, _selfz);
        dest.put(destOffset + 3, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer xyz1_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.xyz1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer xyz1_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.xyz1(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.xyz1_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.xyz1_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer xyz1_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        dest.put(destOffset + 0, _selfx);
        dest.put(destOffset + 1, _selfy);
        dest.put(destOffset + 2, _selfz);
        dest.put(destOffset + 3, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 4L;
        Float3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 9) {
            Float3Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.preMul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), (long) matOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _mat00 = mat.get(matOffset + 0);
        float _mat10 = mat.get(matOffset + 1);
        float _mat20 = mat.get(matOffset + 2);
        float _mat01 = mat.get(matOffset + 3);
        float _mat11 = mat.get(matOffset + 4);
        float _mat21 = mat.get(matOffset + 5);
        float _mat02 = mat.get(matOffset + 6);
        float _mat12 = mat.get(matOffset + 7);
        float _mat22 = mat.get(matOffset + 8);
        dest.put(destOffset + 0, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        dest.put(destOffset + 1, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.put(destOffset + 2, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    public static java.nio.FloatBuffer preMulDirectionMat3x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 4L;
        Float3OpsKernelsAddress.preMulDirectionMat3x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulDirectionMat3x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 12) {
            Float3Ops.preMulDirectionMat3x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.preMulDirectionMat3x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), (long) matOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.preMulDirectionMat3x4_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulDirectionMat3x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _mat00 = mat.get(matOffset + 0);
        float _mat01 = mat.get(matOffset + 1);
        float _mat02 = mat.get(matOffset + 2);
        float _mat10 = mat.get(matOffset + 4);
        float _mat11 = mat.get(matOffset + 5);
        float _mat12 = mat.get(matOffset + 6);
        float _mat20 = mat.get(matOffset + 8);
        float _mat21 = mat.get(matOffset + 9);
        float _mat22 = mat.get(matOffset + 10);
        dest.put(destOffset + 0, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        dest.put(destOffset + 1, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.put(destOffset + 2, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    public static java.nio.FloatBuffer preMulDirectionMat4x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 4L;
        Float3OpsKernelsAddress.preMulDirectionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulDirectionMat4x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 16) {
            Float3Ops.preMulDirectionMat4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.preMulDirectionMat4x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), (long) matOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.preMulDirectionMat4x4_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulDirectionMat4x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _mat00 = mat.get(matOffset + 0);
        float _mat10 = mat.get(matOffset + 1);
        float _mat20 = mat.get(matOffset + 2);
        float _mat01 = mat.get(matOffset + 4);
        float _mat11 = mat.get(matOffset + 5);
        float _mat21 = mat.get(matOffset + 6);
        float _mat02 = mat.get(matOffset + 8);
        float _mat12 = mat.get(matOffset + 9);
        float _mat22 = mat.get(matOffset + 10);
        dest.put(destOffset + 0, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        dest.put(destOffset + 1, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.put(destOffset + 2, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat3x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 4L;
        Float3OpsKernelsAddress.preMulPositionMat3x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat3x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 12) {
            Float3Ops.preMulPositionMat3x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.preMulPositionMat3x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), (long) matOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.preMulPositionMat3x4_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat3x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _mat00 = mat.get(matOffset + 0);
        float _mat01 = mat.get(matOffset + 1);
        float _mat02 = mat.get(matOffset + 2);
        float _mat03 = mat.get(matOffset + 3);
        float _mat10 = mat.get(matOffset + 4);
        float _mat11 = mat.get(matOffset + 5);
        float _mat12 = mat.get(matOffset + 6);
        float _mat13 = mat.get(matOffset + 7);
        float _mat20 = mat.get(matOffset + 8);
        float _mat21 = mat.get(matOffset + 9);
        float _mat22 = mat.get(matOffset + 10);
        float _mat23 = mat.get(matOffset + 11);
        dest.put(destOffset + 0, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, Math.fma(_mat02, _selfz, _mat03))));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))));
        dest.put(destOffset + 2, Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat4x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 4L;
        Float3OpsKernelsAddress.preMulPositionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat4x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 16) {
            Float3Ops.preMulPositionMat4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.preMulPositionMat4x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), (long) matOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.preMulPositionMat4x4_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulPositionMat4x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _mat00 = mat.get(matOffset + 0);
        float _mat10 = mat.get(matOffset + 1);
        float _mat20 = mat.get(matOffset + 2);
        float _mat01 = mat.get(matOffset + 4);
        float _mat11 = mat.get(matOffset + 5);
        float _mat21 = mat.get(matOffset + 6);
        float _mat02 = mat.get(matOffset + 8);
        float _mat12 = mat.get(matOffset + 9);
        float _mat22 = mat.get(matOffset + 10);
        float _mat03 = mat.get(matOffset + 12);
        float _mat13 = mat.get(matOffset + 13);
        float _mat23 = mat.get(matOffset + 14);
        dest.put(destOffset + 0, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, Math.fma(_mat02, _selfz, _mat03))));
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))));
        dest.put(destOffset + 2, Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    public static java.nio.FloatBuffer preMulProject_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 4L;
        Float3OpsKernelsAddress.preMulProject_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulProject_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 16) {
            Float3Ops.preMulProject(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.preMulProject_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(mat.duplicate().position(0)), (long) matOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.preMulProject_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulProject_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _mat00 = mat.get(matOffset + 0);
        float _mat10 = mat.get(matOffset + 1);
        float _mat20 = mat.get(matOffset + 2);
        float _mat30 = mat.get(matOffset + 3);
        float _mat01 = mat.get(matOffset + 4);
        float _mat11 = mat.get(matOffset + 5);
        float _mat21 = mat.get(matOffset + 6);
        float _mat31 = mat.get(matOffset + 7);
        float _mat02 = mat.get(matOffset + 8);
        float _mat12 = mat.get(matOffset + 9);
        float _mat22 = mat.get(matOffset + 10);
        float _mat32 = mat.get(matOffset + 11);
        float _mat03 = mat.get(matOffset + 12);
        float _mat13 = mat.get(matOffset + 13);
        float _mat23 = mat.get(matOffset + 14);
        float _mat33 = mat.get(matOffset + 15);
        float _t2_inv = 1.0f / Math.fma(_mat30, _selfx, Math.fma(_mat31, _selfy, Math.fma(_mat32, _selfz, _mat33)));
        dest.put(destOffset + 0, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, Math.fma(_mat02, _selfz, _mat03))) * _t2_inv);
        dest.put(destOffset + 1, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))) * _t2_inv);
        dest.put(destOffset + 2, Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))) * _t2_inv);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quatX, quatY, quatZ, quatW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, quatX, quatY, quatZ, quatW);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t9 = 2.0f * Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.put(destOffset + 0, Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.put(destOffset + 1, Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy))));
        dest.put(destOffset + 2, Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz))));
        return dest;
    }

    public static java.nio.FloatBuffer rotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) quatOffset * 4L;
        Float3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && quat.hasArray() && quatOffset >= 0 && quatOffset <= quat.limit() - 4) {
            Float3Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quat.array(), quat.arrayOffset() + quatOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(quat.duplicate().position(0)), (long) quatOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, quat, quatOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _quatx = quat.get(quatOffset + 0);
        float _quaty = quat.get(quatOffset + 1);
        float _quatz = quat.get(quatOffset + 2);
        float _quatw = quat.get(quatOffset + 3);
        float _t9 = 2.0f * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        float _t10 = 2.0f * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        float _t11 = 2.0f * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.put(destOffset + 0, Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.put(destOffset + 1, Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy))));
        dest.put(destOffset + 2, Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz))));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = _selfy - pivotY;
        float _t1 = _selfx - pivotX;
        float _t2 = _selfz - pivotZ;
        float _t12 = 2.0f * Math.fma(quatX, _t0, -(quatY * _t1));
        float _t13 = 2.0f * Math.fma(quatZ, _t1, -(quatX * _t2));
        float _t14 = 2.0f * Math.fma(quatY, _t2, -(quatZ * _t0));
        dest.put(destOffset + 0, Math.fma(quatY, _t12, Math.fma(-quatZ, _t13, Math.fma(quatW, _t14, _selfx))));
        dest.put(destOffset + 1, Math.fma(quatZ, _t14, Math.fma(-quatX, _t12, Math.fma(quatW, _t13, _selfy))));
        dest.put(destOffset + 2, Math.fma(quatX, _t13, Math.fma(-quatY, _t14, Math.fma(quatW, _t12, _selfz))));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) quatOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _quatBase, _pivotBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && quat.hasArray() && quatOffset >= 0 && quatOffset <= quat.limit() - 4 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Float3Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quat.array(), quat.arrayOffset() + quatOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(quat.duplicate().position(0)), (long) quatOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), (long) pivotOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _quatx = quat.get(quatOffset + 0);
        float _quaty = quat.get(quatOffset + 1);
        float _quatz = quat.get(quatOffset + 2);
        float _quatw = quat.get(quatOffset + 3);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _pivotz = pivot.get(pivotOffset + 2);
        float _t0 = _selfy - _pivoty;
        float _t1 = _selfx - _pivotx;
        float _t2 = _selfz - _pivotz;
        float _t12 = 2.0f * Math.fma(_quatx, _t0, -(_quaty * _t1));
        float _t13 = 2.0f * Math.fma(_quatz, _t1, -(_quatx * _t2));
        float _t14 = 2.0f * Math.fma(_quaty, _t2, -(_quatz * _t0));
        dest.put(destOffset + 0, Math.fma(_quaty, _t12, Math.fma(-_quatz, _t13, Math.fma(_quatw, _t14, _selfx))));
        dest.put(destOffset + 1, Math.fma(_quatz, _t14, Math.fma(-_quatx, _t12, Math.fma(_quatw, _t13, _selfy))));
        dest.put(destOffset + 2, Math.fma(_quatx, _t13, Math.fma(-_quaty, _t14, Math.fma(_quatw, _t12, _selfz))));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, angle, axisX, axisY, axisZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        dest.put(destOffset + 0, Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0)));
        dest.put(destOffset + 1, Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0)));
        dest.put(destOffset + 2, Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) axisOffset * 4L;
        Float3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Float3Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(axis.duplicate().position(0)), (long) axisOffset * 4L, angle);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _axisx = axis.get(axisOffset + 0);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.put(destOffset + 0, Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0)));
        dest.put(destOffset + 1, Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0)));
        dest.put(destOffset + 2, Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxisAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxisAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.rotateAxisAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateAxisAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateAxisAround_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxisAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - pivotX;
        float _t3 = _selfz - pivotZ;
        float _t4 = _selfy - pivotY;
        float _t5 = 1.0f - _t1;
        float _t8 = Math.fma(axisZ, _t3, Math.fma(axisX, _t2, axisY * _t4));
        dest.put(destOffset + 0, Math.fma(_t2, _t1, Math.fma(Math.fma(axisY, _t3, -(axisZ * _t4)), _t0, Math.fma(_t5, axisX * _t8, pivotX))));
        dest.put(destOffset + 1, Math.fma(_t4, _t1, Math.fma(Math.fma(axisZ, _t2, -(axisX * _t3)), _t0, Math.fma(_t5, axisY * _t8, pivotY))));
        dest.put(destOffset + 2, Math.fma(_t3, _t1, Math.fma(Math.fma(axisX, _t4, -(axisY * _t2)), _t0, Math.fma(_t5, axisZ * _t8, pivotZ))));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxisAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) axisOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, _axisBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxisAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Float3Ops.rotateAxisAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateAxisAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(axis.duplicate().position(0)), (long) axisOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), (long) pivotOffset * 4L, angle);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateAxisAround_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxisAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _axisx = axis.get(axisOffset + 0);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _pivotz = pivot.get(pivotOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - _pivotx;
        float _t3 = _selfz - _pivotz;
        float _t4 = _selfy - _pivoty;
        float _t5 = 1.0f - _t1;
        float _t8 = Math.fma(_axisz, _t3, Math.fma(_axisx, _t2, _axisy * _t4));
        dest.put(destOffset + 0, Math.fma(_t2, _t1, Math.fma(Math.fma(_axisy, _t3, -(_axisz * _t4)), _t0, Math.fma(_t5, _axisx * _t8, _pivotx))));
        dest.put(destOffset + 1, Math.fma(_t4, _t1, Math.fma(Math.fma(_axisz, _t2, -(_axisx * _t3)), _t0, Math.fma(_t5, _axisy * _t8, _pivoty))));
        dest.put(destOffset + 2, Math.fma(_t3, _t1, Math.fma(Math.fma(_axisx, _t4, -(_axisy * _t2)), _t0, Math.fma(_t5, _axisz * _t8, _pivotz))));
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.rotateInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quatX, quatY, quatZ, quatW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateInverse_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, quatX, quatY, quatZ, quatW);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateInverse_apiGet(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t9 = 2.0f * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.put(destOffset + 0, Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.put(destOffset + 1, Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy))));
        dest.put(destOffset + 2, Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz))));
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) quatOffset * 4L;
        Float3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && quat.hasArray() && quatOffset >= 0 && quatOffset <= quat.limit() - 4) {
            Float3Ops.rotateInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quat.array(), quat.arrayOffset() + quatOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateInverse_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(quat.duplicate().position(0)), (long) quatOffset * 4L);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateInverse_apiGet(dest, destOffset, src, srcOffset, quat, quatOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _quatx = quat.get(quatOffset + 0);
        float _quaty = quat.get(quatOffset + 1);
        float _quatz = quat.get(quatOffset + 2);
        float _quatw = quat.get(quatOffset + 3);
        float _t9 = 2.0f * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        float _t10 = 2.0f * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        float _t11 = 2.0f * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.put(destOffset + 0, Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.put(destOffset + 1, Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy))));
        dest.put(destOffset + 2, Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz))));
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.rotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, angle);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _selfx);
        dest.put(destOffset + 1, Math.fma(_selfy, _t1, -(_selfz * _t0)));
        dest.put(destOffset + 2, Math.fma(_selfy, _t0, _selfz * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer rotateXAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.rotateXAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY, pivotZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateXAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, angle, pivotX, pivotY, pivotZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateXAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfy - pivotY;
        float _t3 = _selfz - pivotZ;
        dest.put(destOffset + 0, _selfx);
        dest.put(destOffset + 1, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotY)));
        dest.put(destOffset + 2, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotZ)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateXAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Float3Ops.rotateXAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateXAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), (long) pivotOffset * 4L, angle);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateXAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _pivotz = pivot.get(pivotOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfy - _pivoty;
        float _t3 = _selfz - _pivotz;
        dest.put(destOffset + 0, _selfx);
        dest.put(destOffset + 1, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivoty)));
        dest.put(destOffset + 2, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivotz)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.rotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, angle);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_selfx, _t1, _selfz * _t0));
        dest.put(destOffset + 1, _selfy);
        dest.put(destOffset + 2, Math.fma(_selfz, _t1, -(_selfx * _t0)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateYAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.rotateYAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY, pivotZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateYAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, angle, pivotX, pivotY, pivotZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateYAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - pivotX;
        float _t3 = _selfz - pivotZ;
        dest.put(destOffset + 0, Math.fma(_t2, _t1, Math.fma(_t3, _t0, pivotX)));
        dest.put(destOffset + 1, _selfy);
        dest.put(destOffset + 2, Math.fma(_t3, _t1, Math.fma(-_t2, _t0, pivotZ)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateYAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Float3Ops.rotateYAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateYAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), (long) pivotOffset * 4L, angle);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateYAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivotz = pivot.get(pivotOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - _pivotx;
        float _t3 = _selfz - _pivotz;
        dest.put(destOffset + 0, Math.fma(_t2, _t1, Math.fma(_t3, _t0, _pivotx)));
        dest.put(destOffset + 1, _selfy);
        dest.put(destOffset + 2, Math.fma(_t3, _t1, Math.fma(-_t2, _t0, _pivotz)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.rotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, angle);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.put(destOffset + 1, Math.fma(_selfx, _t0, _selfy * _t1));
        dest.put(destOffset + 2, _selfz);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Float3Ops.rotateZAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY, pivotZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateZAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, angle, pivotX, pivotY, pivotZ);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateZAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - pivotX;
        float _t3 = _selfy - pivotY;
        dest.put(destOffset + 0, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)));
        dest.put(destOffset + 1, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
        dest.put(destOffset + 2, _selfz);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) pivotOffset * 4L;
        Float3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Float3Ops.rotateZAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Float3OpsKernelsSegment.rotateZAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), (long) destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), (long) srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), (long) pivotOffset * 4L, angle);
            return dest;
        }
        Float3OpsKernelsTypedBuffer.rotateZAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _pivotx = pivot.get(pivotOffset + 0);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - _pivotx;
        float _t3 = _selfy - _pivoty;
        dest.put(destOffset + 0, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx)));
        dest.put(destOffset + 1, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivoty)));
        dest.put(destOffset + 2, _selfz);
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
