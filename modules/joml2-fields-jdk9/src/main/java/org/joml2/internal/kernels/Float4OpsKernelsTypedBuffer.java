// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float4Ops} whose leading storage
 * parameter is a typed {@link java.nio.FloatBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float4Ops} and its sibling kernel units. Not public API.
 */
public final class Float4OpsKernelsTypedBuffer {
    private Float4OpsKernelsTypedBuffer() {}

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, otherX + _selfx);
        dest.put(destOffset + 1, otherY + _selfy);
        dest.put(destOffset + 2, otherZ + _selfz);
        dest.put(destOffset + 3, otherW + _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset + 0, _otherx + _selfx);
        dest.put(destOffset + 1, _othery + _selfy);
        dest.put(destOffset + 2, _otherz + _selfz);
        dest.put(destOffset + 3, _otherw + _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer div_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer div_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer div_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, _selfx / scalar);
        dest.put(destOffset + 1, _selfy / scalar);
        dest.put(destOffset + 2, _selfz / scalar);
        dest.put(destOffset + 3, _selfw / scalar);
        return dest;
    }

    public static java.nio.FloatBuffer div_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer div_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer div_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, _selfx / otherX);
        dest.put(destOffset + 1, _selfy / otherY);
        dest.put(destOffset + 2, _selfz / otherZ);
        dest.put(destOffset + 3, _selfw / otherW);
        return dest;
    }

    public static java.nio.FloatBuffer div_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer div_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer div_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset + 0, _selfx / _otherx);
        dest.put(destOffset + 1, _selfy / _othery);
        dest.put(destOffset + 2, _selfz / _otherz);
        dest.put(destOffset + 3, _selfw / _otherw);
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY, float cZ, float cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY, float cZ, float cW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b, cX, cY, cZ, cW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY, float cZ, float cW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.fma(_selfx, b, cX));
        dest.put(destOffset + 1, Math.fma(_selfy, b, cY));
        dest.put(destOffset + 2, Math.fma(_selfz, b, cZ));
        dest.put(destOffset + 3, Math.fma(_selfw, b, cW));
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) cOffset * 4L;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 4) {
            Float4Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, c.array(), c.arrayOffset() + cOffset, b);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, c, cOffset, b);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _cx = c.get(cOffset + 0);
        float _cy = c.get(cOffset + 1);
        float _cz = c.get(cOffset + 2);
        float _cw = c.get(cOffset + 3);
        dest.put(destOffset + 0, Math.fma(_selfx, b, _cx));
        dest.put(destOffset + 1, Math.fma(_selfy, b, _cy));
        dest.put(destOffset + 2, Math.fma(_selfz, b, _cz));
        dest.put(destOffset + 3, Math.fma(_selfw, b, _cw));
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.fma(_selfx, bX, cX));
        dest.put(destOffset + 1, Math.fma(_selfy, bY, cY));
        dest.put(destOffset + 2, Math.fma(_selfz, bZ, cZ));
        dest.put(destOffset + 3, Math.fma(_selfw, bW, cW));
        return dest;
    }

    public static java.nio.FloatBuffer fma_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) bOffset * 4L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) cOffset * 4L;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.FloatBuffer fma_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 4 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 4) {
            Float4Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.FloatBuffer fma_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _bx = b.get(bOffset + 0);
        float _by = b.get(bOffset + 1);
        float _bz = b.get(bOffset + 2);
        float _bw = b.get(bOffset + 3);
        float _cx = c.get(cOffset + 0);
        float _cy = c.get(cOffset + 1);
        float _cz = c.get(cOffset + 2);
        float _cw = c.get(cOffset + 3);
        dest.put(destOffset + 0, Math.fma(_selfx, _bx, _cx));
        dest.put(destOffset + 1, Math.fma(_selfy, _by, _cy));
        dest.put(destOffset + 2, Math.fma(_selfz, _bz, _cz));
        dest.put(destOffset + 3, Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, scalar * _selfx);
        dest.put(destOffset + 1, scalar * _selfy);
        dest.put(destOffset + 2, scalar * _selfz);
        dest.put(destOffset + 3, scalar * _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, otherX * _selfx);
        dest.put(destOffset + 1, otherY * _selfy);
        dest.put(destOffset + 2, otherZ * _selfz);
        dest.put(destOffset + 3, otherW * _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset + 0, _otherx * _selfx);
        dest.put(destOffset + 1, _othery * _selfy);
        dest.put(destOffset + 2, _otherz * _selfz);
        dest.put(destOffset + 3, _otherw * _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer negate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, -_selfx);
        dest.put(destOffset + 1, -_selfy);
        dest.put(destOffset + 2, -_selfz);
        dest.put(destOffset + 3, -_selfw);
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, _selfx - otherX);
        dest.put(destOffset + 1, _selfy - otherY);
        dest.put(destOffset + 2, _selfz - otherZ);
        dest.put(destOffset + 3, _selfw - otherW);
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset + 0, _selfx - _otherx);
        dest.put(destOffset + 1, _selfy - _othery);
        dest.put(destOffset + 2, _selfz - _otherz);
        dest.put(destOffset + 3, _selfw - _otherw);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformDirection_unsafe(java.nio.FloatBuffer dest, int destOffset, float u, float v, float w) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float4OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v, w);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformDirection_api(java.nio.FloatBuffer dest, int destOffset, float u, float v, float w) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float4Ops.makeUniformDirection(dest.array(), dest.arrayOffset() + destOffset, u, v, w);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.makeUniformDirection_apiGet(dest, destOffset, u, v, w);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformDirection_apiGet(java.nio.FloatBuffer dest, int destOffset, float u, float v, float w) {
        float _t0 = (float) Math.sqrt(u);
        float _t1 = v * 6.2831855f;
        float _t3 = w * 6.2831855f;
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sqrt(1.0f - u);
        float _t6 = (float) Math.sin(_t3);
        dest.put(destOffset + 0, (float) Math.cosFromSin(_t4, _t1) * _t5);
        dest.put(destOffset + 1, _t4 * _t5);
        dest.put(destOffset + 2, (float) Math.cosFromSin(_t6, _t3) * _t0);
        dest.put(destOffset + 3, _t6 * _t0);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float4OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float4Ops.set(dest.array(), dest.arrayOffset() + destOffset, vX, vY, vZ, vW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        dest.put(destOffset + 0, vX);
        dest.put(destOffset + 1, vY);
        dest.put(destOffset + 2, vZ);
        dest.put(destOffset + 3, vW);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        Float4OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4) {
            Float4Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        float _vw = v.get(vOffset + 3);
        dest.put(destOffset + 0, _vx);
        dest.put(destOffset + 1, _vy);
        dest.put(destOffset + 2, _vz);
        dest.put(destOffset + 3, _vw);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float4OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float4Ops.set(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, float s) {
        dest.put(destOffset + 0, s);
        dest.put(destOffset + 1, s);
        dest.put(destOffset + 2, s);
        dest.put(destOffset + 3, s);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        Float4OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float4Ops.makeZero(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.makeZero_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 0, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
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
        dest.put(destOffset + 3, Math.fma(p1W, _t7, _selfw * _t8) + Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    public static java.nio.FloatBuffer bezier_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p3Offset * 4L;
        Float4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 4) {
            Float4Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _p3x = p3.get(p3Offset + 0);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _p3w = p3.get(p3Offset + 3);
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
        dest.put(destOffset + 3, Math.fma(_p1w, _t7, _selfw * _t8) + Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.put(destOffset + 0, Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4)));
        dest.put(destOffset + 1, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.put(destOffset + 2, Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4)));
        dest.put(destOffset + 3, Math.fma(p2W, _t0, Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        Float4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4) {
            Float4Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.put(destOffset + 0, Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4)));
        dest.put(destOffset + 1, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        dest.put(destOffset + 2, Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4)));
        dest.put(destOffset + 3, Math.fma(_p2w, _t0, Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.put(destOffset + 0, Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        dest.put(destOffset + 1, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.put(destOffset + 2, Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        dest.put(destOffset + 3, Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        Float4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4) {
            Float4Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezier2Tangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.put(destOffset + 0, Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        dest.put(destOffset + 1, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        dest.put(destOffset + 2, Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        dest.put(destOffset + 3, Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.put(destOffset + 0, Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        dest.put(destOffset + 1, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.put(destOffset + 2, Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        dest.put(destOffset + 3, Math.fma(p3W - p2W, _t2, Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p3Offset * 4L;
        Float4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 4) {
            Float4Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer bezierTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _p3x = p3.get(p3Offset + 0);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _p3w = p3.get(p3Offset + 3);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.put(destOffset + 0, Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        dest.put(destOffset + 1, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.put(destOffset + 2, Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        dest.put(destOffset + 3, Math.fma(_p3w - _p2w, _t2, Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.put(destOffset + 0, 0.5f * (Math.fma(2.0f, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        dest.put(destOffset + 1, 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        dest.put(destOffset + 2, 0.5f * (Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), _t0, Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1)));
        dest.put(destOffset + 3, 0.5f * (Math.fma(2.0f, p1W, t * (p2W - _selfw)) + Math.fma(Math.fma(-5.0f, p1W, Math.fma(2.0f, _selfw, Math.fma(4.0f, p2W, -p3W))), _t0, Math.fma(-3.0f, p2W, Math.fma(3.0f, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p3Offset * 4L;
        Float4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 4) {
            Float4Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRom_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _p3x = p3.get(p3Offset + 0);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _p3w = p3.get(p3Offset + 3);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.put(destOffset + 0, 0.5f * (Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), _t0, Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        dest.put(destOffset + 1, 0.5f * (Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), _t0, Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        dest.put(destOffset + 2, 0.5f * (Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + Math.fma(Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), _t0, Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1)));
        dest.put(destOffset + 3, 0.5f * (Math.fma(2.0f, _p1w, t * (_p2w - _selfw)) + Math.fma(Math.fma(-5.0f, _p1w, Math.fma(2.0f, _selfw, Math.fma(4.0f, _p2w, -_p3w))), _t0, Math.fma(-3.0f, _p2w, Math.fma(3.0f, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        dest.put(destOffset + 0, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.put(destOffset + 1, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        dest.put(destOffset + 2, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), Math.fma(3.0f * Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        dest.put(destOffset + 3, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1W, Math.fma(2.0f, _selfw, Math.fma(4.0f, p2W, -p3W))), Math.fma(3.0f * Math.fma(-3.0f, p2W, Math.fma(3.0f, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p1Offset * 4L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p2Offset * 4L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) p3Offset * 4L;
        Float4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 4) {
            Float4Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer catmullRomTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _p1x = p1.get(p1Offset + 0);
        float _p1y = p1.get(p1Offset + 1);
        float _p1z = p1.get(p1Offset + 2);
        float _p1w = p1.get(p1Offset + 3);
        float _p2x = p2.get(p2Offset + 0);
        float _p2y = p2.get(p2Offset + 1);
        float _p2z = p2.get(p2Offset + 2);
        float _p2w = p2.get(p2Offset + 3);
        float _p3x = p3.get(p3Offset + 0);
        float _p3y = p3.get(p3Offset + 1);
        float _p3z = p3.get(p3Offset + 2);
        float _p3w = p3.get(p3Offset + 3);
        float _t0 = t * t;
        dest.put(destOffset + 0, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), Math.fma(3.0f * Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.put(destOffset + 1, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), Math.fma(3.0f * Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.put(destOffset + 2, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), Math.fma(3.0f * Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        dest.put(destOffset + 3, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1w, Math.fma(2.0f, _selfw, Math.fma(4.0f, _p2w, -_p3w))), Math.fma(3.0f * Math.fma(-3.0f, _p2w, Math.fma(3.0f, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    public static java.nio.FloatBuffer hermite_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.put(destOffset + 0, Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.put(destOffset + 1, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        dest.put(destOffset + 2, Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
        dest.put(destOffset + 3, Math.fma(_selfw, _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    public static java.nio.FloatBuffer hermite_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t0Offset * 4L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) v1Offset * 4L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t1Offset * 4L;
        Float4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 4 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 4 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 4) {
            Float4Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermite_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0x = t0.get(t0Offset + 0);
        float _t0y = t0.get(t0Offset + 1);
        float _t0z = t0.get(t0Offset + 2);
        float _t0w = t0.get(t0Offset + 3);
        float _v1x = v1.get(v1Offset + 0);
        float _v1y = v1.get(v1Offset + 1);
        float _v1z = v1.get(v1Offset + 2);
        float _v1w = v1.get(v1Offset + 3);
        float _t1x = t1.get(t1Offset + 0);
        float _t1y = t1.get(t1Offset + 1);
        float _t1z = t1.get(t1Offset + 2);
        float _t1w = t1.get(t1Offset + 3);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.put(destOffset + 0, Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9));
        dest.put(destOffset + 1, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        dest.put(destOffset + 2, Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9));
        dest.put(destOffset + 3, Math.fma(_selfw, _t10, _t0w * _t7) + Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.put(destOffset + 0, Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.put(destOffset + 1, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        dest.put(destOffset + 2, Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
        dest.put(destOffset + 3, Math.fma(_selfw, _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t0Offset * 4L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) v1Offset * 4L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) t1Offset * 4L;
        Float4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 4 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 4 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 4) {
            Float4Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.FloatBuffer hermiteTangent_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0x = t0.get(t0Offset + 0);
        float _t0y = t0.get(t0Offset + 1);
        float _t0z = t0.get(t0Offset + 2);
        float _t0w = t0.get(t0Offset + 3);
        float _v1x = v1.get(v1Offset + 0);
        float _v1y = v1.get(v1Offset + 1);
        float _v1z = v1.get(v1Offset + 2);
        float _v1w = v1.get(v1Offset + 3);
        float _t1x = t1.get(t1Offset + 0);
        float _t1y = t1.get(t1Offset + 1);
        float _t1z = t1.get(t1Offset + 2);
        float _t1w = t1.get(t1Offset + 3);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.put(destOffset + 0, Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7));
        dest.put(destOffset + 1, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        dest.put(destOffset + 2, Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7));
        dest.put(destOffset + 3, Math.fma(_selfw, _t6, _t0w * _t9) + Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.fma(t, otherX - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(t, otherY - _selfy, _selfy));
        dest.put(destOffset + 2, Math.fma(t, otherZ - _selfz, _selfz));
        dest.put(destOffset + 3, Math.fma(t, otherW - _selfw, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset + 0, Math.fma(t, _otherx - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(t, _othery - _selfy, _selfy));
        dest.put(destOffset + 2, Math.fma(t, _otherz - _selfz, _selfz));
        dest.put(destOffset + 3, Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.fma(tX, otherX - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(tY, otherY - _selfy, _selfy));
        dest.put(destOffset + 2, Math.fma(tZ, otherZ - _selfz, _selfz));
        dest.put(destOffset + 3, Math.fma(tW, otherW - _selfw, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) tOffset * 4L;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4 && t.hasArray() && tOffset >= 0 && tOffset <= t.limit() - 4) {
            Float4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t.array(), t.arrayOffset() + tOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _tx = t.get(tOffset + 0);
        float _ty = t.get(tOffset + 1);
        float _tz = t.get(tOffset + 2);
        float _tw = t.get(tOffset + 3);
        dest.put(destOffset + 0, Math.fma(_tx, _otherx - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(_ty, _othery - _selfy, _selfy));
        dest.put(destOffset + 2, Math.fma(_tz, _otherz - _selfz, _selfz));
        dest.put(destOffset + 3, Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t10 = Math.fma(otherW, otherW, Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) Math.sqrt(_t9));
        float _t14 = (1.0f / (float) Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        float _t28 = t * (float) Math.sqrt(_t10) + (1.0f - t) * (float) Math.sqrt(_t9);
        float _t31 = Math.fma(otherW * _t14, _t19, Math.fma(otherZ * _t14, _t21, Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        float _t40 = Math.fma(otherW, _t14, -(_t31 * _t19));
        float _t41 = Math.fma(otherZ, _t14, -(_t31 * _t21));
        float _t42 = Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = Math.fma(_t48, _t19, _t40);
        float _t50 = Math.fma(_t48, _t21, _t41);
        float _t51 = Math.fma(_t48, _t16, _t42);
        float _t52 = Math.fma(_t48, _t24, _t43);
        float _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t61 = t * (float) Math.atan2((float) Math.sqrt(_t57), _t31);
        float _sp0 = _t28 * (float) Math.sin(_t61) * (1.0f / (float) Math.sqrt(_t57));
        float _t66 = _t28 * (float) Math.cos(_t61);
        dest.put(destOffset + 0, Math.fma(_t16, _t66, _sp0 * _t51));
        dest.put(destOffset + 1, Math.fma(_t24, _t66, _sp0 * _t52));
        dest.put(destOffset + 2, Math.fma(_t21, _t66, _sp0 * _t50));
        dest.put(destOffset + 3, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return Float4OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.nio.FloatBuffer slerp_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t7 = unitScale(otherZ, otherW, Math.max(Math.abs(otherX), Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(Math.abs(_selfx), Math.abs(_selfy)));
        float _t17 = otherW * _t7;
        float _t18 = otherZ * _t7;
        float _t19 = otherX * _t7;
        float _t20 = otherY * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) Math.sqrt(_t36));
        float _t41 = (1.0f / (float) Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = Math.fma(_t80, _t43, _t72);
        float _t82 = Math.fma(_t80, _t45, _t73);
        float _t83 = Math.fma(_t80, _t47, _t74);
        float _t84 = Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(Math.abs(_t83), Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) Math.sqrt(_t106));
        float _t110 = t * (float) Math.atan2((float) Math.sqrt(_t106), _t63 * _t90);
        float _t114 = _t60 * (float) Math.sin(_t110);
        float _t115 = _t60 * (float) Math.cos(_t110);
        float _t120, _t121, _t122, _t123;
        if (_t106 > 0.0f) {
            _t120 = _t108 * _t100;
            _t121 = _t108 * _t97;
            _t122 = _t108 * _t99;
            _t123 = _t108 * _t98;
        } else {
            _t120 = _t47;
            _t121 = _t45;
            _t122 = _t50;
            _t123 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dest.put(destOffset + 0, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    dest.put(destOffset + 0, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                dest.put(destOffset + 0, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                dest.put(destOffset + 1, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                dest.put(destOffset + 2, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                dest.put(destOffset + 3, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            dest.put(destOffset + 0, Math.fma(t, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, Math.fma(t, otherY - _selfy, _selfy));
            dest.put(destOffset + 2, Math.fma(t, otherZ - _selfz, _selfz));
            dest.put(destOffset + 3, Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    public static java.nio.FloatBuffer slerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t10 = Math.fma(_otherw, _otherw, Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t11 = (1.0f / (float) Math.sqrt(_t9));
        float _t14 = (1.0f / (float) Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        float _t28 = t * (float) Math.sqrt(_t10) + (1.0f - t) * (float) Math.sqrt(_t9);
        float _t31 = Math.fma(_otherw * _t14, _t19, Math.fma(_otherz * _t14, _t21, Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        float _t40 = Math.fma(_otherw, _t14, -(_t31 * _t19));
        float _t41 = Math.fma(_otherz, _t14, -(_t31 * _t21));
        float _t42 = Math.fma(_otherx, _t14, -(_t31 * _t16));
        float _t43 = Math.fma(_othery, _t14, -(_t31 * _t24));
        float _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = Math.fma(_t48, _t19, _t40);
        float _t50 = Math.fma(_t48, _t21, _t41);
        float _t51 = Math.fma(_t48, _t16, _t42);
        float _t52 = Math.fma(_t48, _t24, _t43);
        float _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t61 = t * (float) Math.atan2((float) Math.sqrt(_t57), _t31);
        float _sp0 = _t28 * (float) Math.sin(_t61) * (1.0f / (float) Math.sqrt(_t57));
        float _t66 = _t28 * (float) Math.cos(_t61);
        dest.put(destOffset + 0, Math.fma(_t16, _t66, _sp0 * _t51));
        dest.put(destOffset + 1, Math.fma(_t24, _t66, _sp0 * _t52));
        dest.put(destOffset + 2, Math.fma(_t21, _t66, _sp0 * _t50));
        dest.put(destOffset + 3, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float4OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.FloatBuffer slerp_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t7 = unitScale(_otherz, _otherw, Math.max(Math.abs(_otherx), Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(Math.abs(_selfx), Math.abs(_selfy)));
        float _t17 = _otherw * _t7;
        float _t18 = _otherz * _t7;
        float _t19 = _otherx * _t7;
        float _t20 = _othery * _t7;
        float _t21 = _selfw * _t8;
        float _t22 = _selfz * _t8;
        float _t23 = _selfx * _t8;
        float _t24 = _selfy * _t8;
        float _t25 = Math.min(_t8, _t7);
        float _t25_inv = 1.0f / _t25;
        float _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) Math.sqrt(_t36));
        float _t41 = (1.0f / (float) Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = Math.fma(_t80, _t43, _t72);
        float _t82 = Math.fma(_t80, _t45, _t73);
        float _t83 = Math.fma(_t80, _t47, _t74);
        float _t84 = Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(Math.abs(_t83), Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) Math.sqrt(_t106));
        float _t110 = t * (float) Math.atan2((float) Math.sqrt(_t106), _t63 * _t90);
        float _t114 = _t60 * (float) Math.sin(_t110);
        float _t115 = _t60 * (float) Math.cos(_t110);
        float _t120, _t121, _t122, _t123;
        if (_t106 > 0.0f) {
            _t120 = _t108 * _t100;
            _t121 = _t108 * _t97;
            _t122 = _t108 * _t99;
            _t123 = _t108 * _t98;
        } else {
            _t120 = _t47;
            _t121 = _t45;
            _t122 = _t50;
            _t123 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dest.put(destOffset + 0, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    dest.put(destOffset + 0, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                dest.put(destOffset + 0, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                dest.put(destOffset + 1, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                dest.put(destOffset + 2, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                dest.put(destOffset + 3, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            dest.put(destOffset + 0, Math.fma(t, _otherx - _selfx, _selfx));
            dest.put(destOffset + 1, Math.fma(t, _othery - _selfy, _selfy));
            dest.put(destOffset + 2, Math.fma(t, _otherz - _selfz, _selfz));
            dest.put(destOffset + 3, Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    public static java.nio.FloatBuffer absolute_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer absolute_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.absolute(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.absolute_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer absolute_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.abs(_selfx));
        dest.put(destOffset + 1, Math.abs(_selfy));
        dest.put(destOffset + 2, Math.abs(_selfz));
        dest.put(destOffset + 3, Math.abs(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer acos_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer acos_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.acos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.acos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer acos_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.acos(_selfx));
        dest.put(destOffset + 1, (float) Math.acos(_selfy));
        dest.put(destOffset + 2, (float) Math.acos(_selfz));
        dest.put(destOffset + 3, (float) Math.acos(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, bW, scalar);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.fma(scalar, bX, _selfx));
        dest.put(destOffset + 1, Math.fma(scalar, bY, _selfy));
        dest.put(destOffset + 2, Math.fma(scalar, bZ, _selfz));
        dest.put(destOffset + 3, Math.fma(scalar, bW, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) bOffset * 4L;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 4) {
            Float4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, scalar);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _bx = b.get(bOffset + 0);
        float _by = b.get(bOffset + 1);
        float _bz = b.get(bOffset + 2);
        float _bw = b.get(bOffset + 3);
        dest.put(destOffset + 0, Math.fma(scalar, _bx, _selfx));
        dest.put(destOffset + 1, Math.fma(scalar, _by, _selfy));
        dest.put(destOffset + 2, Math.fma(scalar, _bz, _selfz));
        dest.put(destOffset + 3, Math.fma(scalar, _bw, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.fma(bX, cX, _selfx));
        dest.put(destOffset + 1, Math.fma(bY, cY, _selfy));
        dest.put(destOffset + 2, Math.fma(bZ, cZ, _selfz));
        dest.put(destOffset + 3, Math.fma(bW, cW, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) bOffset * 4L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) cOffset * 4L;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 4 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 4) {
            Float4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _bx = b.get(bOffset + 0);
        float _by = b.get(bOffset + 1);
        float _bz = b.get(bOffset + 2);
        float _bw = b.get(bOffset + 3);
        float _cx = c.get(cOffset + 0);
        float _cy = c.get(cOffset + 1);
        float _cz = c.get(cOffset + 2);
        float _cw = c.get(cOffset + 3);
        dest.put(destOffset + 0, Math.fma(_bx, _cx, _selfx));
        dest.put(destOffset + 1, Math.fma(_by, _cy, _selfy));
        dest.put(destOffset + 2, Math.fma(_bz, _cz, _selfz));
        dest.put(destOffset + 3, Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    public static float angleBetween_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Float4OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t12 = Math.fma(otherW, _selfz, -(otherZ * _selfw));
        float _t13 = Math.fma(otherW, _selfy, -(otherY * _selfw));
        float _t14 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t15 = Math.fma(otherW, _selfx, -(otherX * _selfw));
        float _t16 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _t17 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        float _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return (float) Math.atan2((float) Math.sqrt(_ct0), Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    public static float angleBetween_degenerate(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Float4OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t6 = unitScale(otherZ, otherW, Math.max(Math.abs(otherX), Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(Math.abs(_selfx), Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t36 = Math.fma(_t16, _t17, -(_t18 * _t19));
        float _t37 = Math.fma(_t20, _t21, -(_t22 * _t23));
        float _t38 = Math.fma(_t18, _t21, -(_t22 * _t17));
        float _t39 = Math.fma(_t16, _t21, -(_t22 * _t19));
        float _t40 = Math.fma(_t18, _t23, -(_t20 * _t17));
        float _t41 = Math.fma(_t16, _t23, -(_t20 * _t19));
        float _t51 = unitScale(Math.max(Math.abs(_t37), Math.abs(_t38)), Math.max(Math.abs(_t39), Math.abs(_t40)), Math.max(Math.abs(_t41), Math.abs(_t36)));
        float _t58 = _t36 * _t51;
        float _t59 = _t41 * _t51;
        float _t60 = _t40 * _t51;
        float _t61 = _t39 * _t51;
        float _t62 = _t37 * _t51;
        float _t63 = _t38 * _t51;
        return (float) Math.atan2((float) Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static float angleBetween_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        return Float4OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Float4Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Float4OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t12 = Math.fma(_otherw, _selfz, -(_otherz * _selfw));
        float _t13 = Math.fma(_otherw, _selfy, -(_othery * _selfw));
        float _t14 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t15 = Math.fma(_otherw, _selfx, -(_otherx * _selfw));
        float _t16 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _t17 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        float _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return (float) Math.atan2((float) Math.sqrt(_ct0), Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    public static float angleBetween_degenerate(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_degenerate_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Float4OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Float4OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t6 = unitScale(_otherz, _otherw, Math.max(Math.abs(_otherx), Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(Math.abs(_selfx), Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        float _t36 = Math.fma(_t16, _t17, -(_t18 * _t19));
        float _t37 = Math.fma(_t20, _t21, -(_t22 * _t23));
        float _t38 = Math.fma(_t18, _t21, -(_t22 * _t17));
        float _t39 = Math.fma(_t16, _t21, -(_t22 * _t19));
        float _t40 = Math.fma(_t18, _t23, -(_t20 * _t17));
        float _t41 = Math.fma(_t16, _t23, -(_t20 * _t19));
        float _t51 = unitScale(Math.max(Math.abs(_t37), Math.abs(_t38)), Math.max(Math.abs(_t39), Math.abs(_t40)), Math.max(Math.abs(_t41), Math.abs(_t36)));
        float _t58 = _t36 * _t51;
        float _t59 = _t41 * _t51;
        float _t60 = _t40 * _t51;
        float _t61 = _t39 * _t51;
        float _t62 = _t37 * _t51;
        float _t63 = _t38 * _t51;
        return (float) Math.atan2((float) Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static java.nio.FloatBuffer asin_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer asin_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.asin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.asin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer asin_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.asin(_selfx));
        dest.put(destOffset + 1, (float) Math.asin(_selfy));
        dest.put(destOffset + 2, (float) Math.asin(_selfz));
        dest.put(destOffset + 3, (float) Math.asin(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer atan_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer atan_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.atan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.atan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer atan_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.atan(_selfx));
        dest.put(destOffset + 1, (float) Math.atan(_selfy));
        dest.put(destOffset + 2, (float) Math.atan(_selfz));
        dest.put(destOffset + 3, (float) Math.atan(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer atan2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.atan2(_selfx, x));
        dest.put(destOffset + 1, (float) Math.atan2(_selfy, x));
        dest.put(destOffset + 2, (float) Math.atan2(_selfz, x));
        dest.put(destOffset + 3, (float) Math.atan2(_selfw, x));
        return dest;
    }

    public static java.nio.FloatBuffer atan2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY, float xZ, float xW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ, xW);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY, float xZ, float xW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, xX, xY, xZ, xW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, xX, xY, xZ, xW);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY, float xZ, float xW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.atan2(_selfx, xX));
        dest.put(destOffset + 1, (float) Math.atan2(_selfy, xY));
        dest.put(destOffset + 2, (float) Math.atan2(_selfz, xZ));
        dest.put(destOffset + 3, (float) Math.atan2(_selfw, xW));
        return dest;
    }

    public static java.nio.FloatBuffer atan2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) xOffset * 4L;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && x.hasArray() && xOffset >= 0 && xOffset <= x.limit() - 4) {
            Float4Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x.array(), x.arrayOffset() + xOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x, xOffset);
        return dest;
    }

    public static java.nio.FloatBuffer atan2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _xx = x.get(xOffset + 0);
        float _xy = x.get(xOffset + 1);
        float _xz = x.get(xOffset + 2);
        float _xw = x.get(xOffset + 3);
        dest.put(destOffset + 0, (float) Math.atan2(_selfx, _xx));
        dest.put(destOffset + 1, (float) Math.atan2(_selfy, _xy));
        dest.put(destOffset + 2, (float) Math.atan2(_selfz, _xz));
        dest.put(destOffset + 3, (float) Math.atan2(_selfw, _xw));
        return dest;
    }

    public static java.nio.FloatBuffer cbrt_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cbrt_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.cbrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.cbrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cbrt_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.cbrt(_selfx));
        dest.put(destOffset + 1, (float) Math.cbrt(_selfy));
        dest.put(destOffset + 2, (float) Math.cbrt(_selfz));
        dest.put(destOffset + 3, (float) Math.cbrt(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer ceil_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer ceil_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.ceil(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.ceil_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer ceil_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.ceil(_selfx));
        dest.put(destOffset + 1, (float) Math.ceil(_selfy));
        dest.put(destOffset + 2, (float) Math.ceil(_selfz));
        dest.put(destOffset + 3, (float) Math.ceil(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer clamp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min, max);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, max);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.min(Math.max(_selfx, min), max));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, min), max));
        dest.put(destOffset + 2, Math.min(Math.max(_selfz, min), max));
        dest.put(destOffset + 3, Math.min(Math.max(_selfw, min), max));
        return dest;
    }

    public static java.nio.FloatBuffer clamp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.min(Math.max(_selfx, minX), maxX));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, minY), maxY));
        dest.put(destOffset + 2, Math.min(Math.max(_selfz, minZ), maxZ));
        dest.put(destOffset + 3, Math.min(Math.max(_selfw, minW), maxW));
        return dest;
    }

    public static java.nio.FloatBuffer clamp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) minOffset * 4L;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) maxOffset * 4L;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && min.hasArray() && minOffset >= 0 && minOffset <= min.limit() - 4 && max.hasArray() && maxOffset >= 0 && maxOffset <= max.limit() - 4) {
            Float4Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min.array(), min.arrayOffset() + minOffset, max.array(), max.arrayOffset() + maxOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return dest;
    }

    public static java.nio.FloatBuffer clamp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _minx = min.get(minOffset + 0);
        float _miny = min.get(minOffset + 1);
        float _minz = min.get(minOffset + 2);
        float _minw = min.get(minOffset + 3);
        float _maxx = max.get(maxOffset + 0);
        float _maxy = max.get(maxOffset + 1);
        float _maxz = max.get(maxOffset + 2);
        float _maxw = max.get(maxOffset + 3);
        dest.put(destOffset + 0, Math.min(Math.max(_selfx, _minx), _maxx));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, _miny), _maxy));
        dest.put(destOffset + 2, Math.min(Math.max(_selfz, _minz), _maxz));
        dest.put(destOffset + 3, Math.min(Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static float compAdd_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static float compAdd_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.compAdd(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.compAdd_apiGet(src, srcOffset);
    }

    public static float compAdd_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        return _selfw + (_selfz + (_selfx + _selfy));
    }

    public static float compMax_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static float compMax_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.compMax(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.compMax_apiGet(src, srcOffset);
    }

    public static float compMax_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        return Math.max(Math.max(Math.max(_selfx, _selfy), _selfz), _selfw);
    }

    public static float compMin_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static float compMin_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.compMin(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.compMin_apiGet(src, srcOffset);
    }

    public static float compMin_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        return Math.min(Math.min(Math.min(_selfx, _selfy), _selfz), _selfw);
    }

    public static float compMul_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static float compMul_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.compMul(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.compMul_apiGet(src, srcOffset);
    }

    public static float compMul_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        return _selfw * _selfz * _selfx * _selfy;
    }

    public static java.nio.FloatBuffer copySign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.copySign(_selfx, sign));
        dest.put(destOffset + 1, Math.copySign(_selfy, sign));
        dest.put(destOffset + 2, Math.copySign(_selfz, sign));
        dest.put(destOffset + 3, Math.copySign(_selfw, sign));
        return dest;
    }

    public static java.nio.FloatBuffer copySign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY, float signZ, float signW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ, signW);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY, float signZ, float signW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, signX, signY, signZ, signW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, signX, signY, signZ, signW);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY, float signZ, float signW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.copySign(_selfx, signX));
        dest.put(destOffset + 1, Math.copySign(_selfy, signY));
        dest.put(destOffset + 2, Math.copySign(_selfz, signZ));
        dest.put(destOffset + 3, Math.copySign(_selfw, signW));
        return dest;
    }

    public static java.nio.FloatBuffer copySign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) signOffset * 4L;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && sign.hasArray() && signOffset >= 0 && signOffset <= sign.limit() - 4) {
            Float4Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign.array(), sign.arrayOffset() + signOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign, signOffset);
        return dest;
    }

    public static java.nio.FloatBuffer copySign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _signx = sign.get(signOffset + 0);
        float _signy = sign.get(signOffset + 1);
        float _signz = sign.get(signOffset + 2);
        float _signw = sign.get(signOffset + 3);
        dest.put(destOffset + 0, Math.copySign(_selfx, _signx));
        dest.put(destOffset + 1, Math.copySign(_selfy, _signy));
        dest.put(destOffset + 2, Math.copySign(_selfz, _signz));
        dest.put(destOffset + 3, Math.copySign(_selfw, _signw));
        return dest;
    }

    public static java.nio.FloatBuffer cos_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cos_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.cos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.cos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cos_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.cos(_selfx));
        dest.put(destOffset + 1, (float) Math.cos(_selfy));
        dest.put(destOffset + 2, (float) Math.cos(_selfz));
        dest.put(destOffset + 3, (float) Math.cos(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer cosh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cosh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.cosh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.cosh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cosh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.cosh(_selfx));
        dest.put(destOffset + 1, (float) Math.cosh(_selfy));
        dest.put(destOffset + 2, (float) Math.cosh(_selfz));
        dest.put(destOffset + 3, (float) Math.cosh(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer cross_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    public static java.nio.FloatBuffer cross_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.cross(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.cross_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    public static java.nio.FloatBuffer cross_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t12 = Math.fma(vY, wZ, -(vZ * wY));
        float _t13 = Math.fma(vZ, wW, -(vW * wZ));
        float _t14 = Math.fma(vY, wW, -(vW * wY));
        float _t15 = Math.fma(vX, wZ, -(vZ * wX));
        float _t16 = Math.fma(vX, wW, -(vW * wX));
        float _t17 = Math.fma(vX, wY, -(vY * wX));
        dest.put(destOffset + 0, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        dest.put(destOffset + 1, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        dest.put(destOffset + 2, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        dest.put(destOffset + 3, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static java.nio.FloatBuffer cross_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset, java.nio.FloatBuffer w, int wOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) vOffset * 4L;
        long _wBase = UnsafeOpsHolder.U.getLong(w, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) wOffset * 4L;
        Float4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _vBase, _wBase);
        return dest;
    }

    public static java.nio.FloatBuffer cross_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset, java.nio.FloatBuffer w, int wOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4 && w.hasArray() && wOffset >= 0 && wOffset <= w.limit() - 4) {
            Float4Ops.cross(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset, w.array(), w.arrayOffset() + wOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.cross_apiGet(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cross_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset, java.nio.FloatBuffer w, int wOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _vx = v.get(vOffset + 0);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        float _vw = v.get(vOffset + 3);
        float _wx = w.get(wOffset + 0);
        float _wy = w.get(wOffset + 1);
        float _wz = w.get(wOffset + 2);
        float _ww = w.get(wOffset + 3);
        float _t12 = Math.fma(_vy, _wz, -(_vz * _wy));
        float _t13 = Math.fma(_vz, _ww, -(_vw * _wz));
        float _t14 = Math.fma(_vy, _ww, -(_vw * _wy));
        float _t15 = Math.fma(_vx, _wz, -(_vz * _wx));
        float _t16 = Math.fma(_vx, _ww, -(_vw * _wx));
        float _t17 = Math.fma(_vx, _wy, -(_vy * _wx));
        dest.put(destOffset + 0, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        dest.put(destOffset + 1, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        dest.put(destOffset + 2, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        dest.put(destOffset + 3, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static java.nio.FloatBuffer degrees_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer degrees_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.degrees(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.degrees_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer degrees_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.toDegrees(_selfx));
        dest.put(destOffset + 1, (float) Math.toDegrees(_selfy));
        dest.put(destOffset + 2, (float) Math.toDegrees(_selfz));
        dest.put(destOffset + 3, (float) Math.toDegrees(_selfw));
        return dest;
    }

    public static float distance_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float distance_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.distance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Float4OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float distance_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfw - otherW;
        float _t1 = _selfz - otherZ;
        float _t2 = _selfx - otherX;
        float _t3 = _selfy - otherY;
        return (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static float distance_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        return Float4OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static float distance_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Float4Ops.distance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Float4OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float distance_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t0 = _selfw - _otherw;
        float _t1 = _selfz - _otherz;
        float _t2 = _selfx - _otherx;
        float _t3 = _selfy - _othery;
        return (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static float distanceSquared_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float distanceSquared_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Float4OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float distanceSquared_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfw - otherW;
        float _t1 = _selfz - otherZ;
        float _t2 = _selfx - otherX;
        float _t3 = _selfy - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static float distanceSquared_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        return Float4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static float distanceSquared_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Float4Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Float4OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float distanceSquared_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t0 = _selfw - _otherw;
        float _t1 = _selfz - _otherz;
        float _t2 = _selfx - _otherx;
        float _t3 = _selfy - _othery;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static float dot_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float dot_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.dot(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Float4OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float dot_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        return Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    public static float dot_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        return Float4OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Float4Ops.dot(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Float4OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float dot_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        return Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    public static java.nio.FloatBuffer exp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer exp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.exp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.exp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer exp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.exp(_selfx));
        dest.put(destOffset + 1, (float) Math.exp(_selfy));
        dest.put(destOffset + 2, (float) Math.exp(_selfz));
        dest.put(destOffset + 3, (float) Math.exp(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer exp2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer exp2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.exp2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.exp2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer exp2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.pow(2.0f, _selfx));
        dest.put(destOffset + 1, (float) Math.pow(2.0f, _selfy));
        dest.put(destOffset + 2, (float) Math.pow(2.0f, _selfz));
        dest.put(destOffset + 3, (float) Math.pow(2.0f, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer expm1_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer expm1_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.expm1(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.expm1_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer expm1_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.expm1(_selfx));
        dest.put(destOffset + 1, (float) Math.expm1(_selfy));
        dest.put(destOffset + 2, (float) Math.expm1(_selfz));
        dest.put(destOffset + 3, (float) Math.expm1(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        if (Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0f) {
            dest.put(destOffset + 0, _selfx);
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, _selfz);
            dest.put(destOffset + 3, _selfw);
        } else {
            dest.put(destOffset + 0, -_selfx);
            dest.put(destOffset + 1, -_selfy);
            dest.put(destOffset + 2, -_selfz);
            dest.put(destOffset + 3, -_selfw);
        }
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) IOffset * 4L;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) NrefOffset * 4L;
        Float4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && I.hasArray() && IOffset >= 0 && IOffset <= I.limit() - 4 && Nref.hasArray() && NrefOffset >= 0 && NrefOffset <= Nref.limit() - 4) {
            Float4Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, I.array(), I.arrayOffset() + IOffset, Nref.array(), Nref.arrayOffset() + NrefOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return dest;
    }

    public static java.nio.FloatBuffer faceforward_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _Ix = I.get(IOffset + 0);
        float _Iy = I.get(IOffset + 1);
        float _Iz = I.get(IOffset + 2);
        float _Iw = I.get(IOffset + 3);
        float _Nrefx = Nref.get(NrefOffset + 0);
        float _Nrefy = Nref.get(NrefOffset + 1);
        float _Nrefz = Nref.get(NrefOffset + 2);
        float _Nrefw = Nref.get(NrefOffset + 3);
        if (Math.fma(_Iw, _Nrefw, Math.fma(_Iz, _Nrefz, Math.fma(_Ix, _Nrefx, _Iy * _Nrefy))) < 0.0f) {
            dest.put(destOffset + 0, _selfx);
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, _selfz);
            dest.put(destOffset + 3, _selfw);
        } else {
            dest.put(destOffset + 0, -_selfx);
            dest.put(destOffset + 1, -_selfy);
            dest.put(destOffset + 2, -_selfz);
            dest.put(destOffset + 3, -_selfw);
        }
        return dest;
    }

    public static java.nio.FloatBuffer floor_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer floor_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.floor(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.floor_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer floor_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.floor(_selfx));
        dest.put(destOffset + 1, (float) Math.floor(_selfy));
        dest.put(destOffset + 2, (float) Math.floor(_selfz));
        dest.put(destOffset + 3, (float) Math.floor(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer fract_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer fract_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.fract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.fract_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer fract_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.min(_selfx - (float) Math.floor(_selfx), 0.99999994f));
        dest.put(destOffset + 1, Math.min(_selfy - (float) Math.floor(_selfy), 0.99999994f));
        dest.put(destOffset + 2, Math.min(_selfz - (float) Math.floor(_selfz), 0.99999994f));
        dest.put(destOffset + 3, Math.min(_selfw - (float) Math.floor(_selfw), 0.99999994f));
        return dest;
    }

    public static java.nio.FloatBuffer hypot_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.hypot(_selfx, y));
        dest.put(destOffset + 1, (float) Math.hypot(_selfy, y));
        dest.put(destOffset + 2, (float) Math.hypot(_selfz, y));
        dest.put(destOffset + 3, (float) Math.hypot(_selfw, y));
        return dest;
    }

    public static java.nio.FloatBuffer hypot_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY, yZ, yW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.hypot(_selfx, yX));
        dest.put(destOffset + 1, (float) Math.hypot(_selfy, yY));
        dest.put(destOffset + 2, (float) Math.hypot(_selfz, yZ));
        dest.put(destOffset + 3, (float) Math.hypot(_selfw, yW));
        return dest;
    }

    public static java.nio.FloatBuffer hypot_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) yOffset * 4L;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 4) {
            Float4Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.FloatBuffer hypot_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _yx = y.get(yOffset + 0);
        float _yy = y.get(yOffset + 1);
        float _yz = y.get(yOffset + 2);
        float _yw = y.get(yOffset + 3);
        dest.put(destOffset + 0, (float) Math.hypot(_selfx, _yx));
        dest.put(destOffset + 1, (float) Math.hypot(_selfy, _yy));
        dest.put(destOffset + 2, (float) Math.hypot(_selfz, _yz));
        dest.put(destOffset + 3, (float) Math.hypot(_selfw, _yw));
        return dest;
    }

    public static java.nio.FloatBuffer inverse_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer inverse_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.inverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.inverse_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer inverse_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, 1.0f / _selfx);
        dest.put(destOffset + 1, 1.0f / _selfy);
        dest.put(destOffset + 2, 1.0f / _selfz);
        dest.put(destOffset + 3, 1.0f / _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer inverseSqrt_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer inverseSqrt_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.inverseSqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.inverseSqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer inverseSqrt_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (1.0f / (float) Math.sqrt(_selfx)));
        dest.put(destOffset + 1, (1.0f / (float) Math.sqrt(_selfy)));
        dest.put(destOffset + 2, (1.0f / (float) Math.sqrt(_selfz)));
        dest.put(destOffset + 3, (1.0f / (float) Math.sqrt(_selfw)));
        return dest;
    }

    public static float length_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.length(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.length_apiGet(src, srcOffset);
    }

    public static float length_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        return (float) Math.sqrt(Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))));
    }

    public static float lengthSquared_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.lengthSquared(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.lengthSquared_apiGet(src, srcOffset);
    }

    public static float lengthSquared_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        return Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static java.nio.FloatBuffer log_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.log(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.log_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.log(_selfx));
        dest.put(destOffset + 1, (float) Math.log(_selfy));
        dest.put(destOffset + 2, (float) Math.log(_selfz));
        dest.put(destOffset + 3, (float) Math.log(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer log10_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log10_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.log10(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.log10_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log10_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.log10(_selfx));
        dest.put(destOffset + 1, (float) Math.log10(_selfy));
        dest.put(destOffset + 2, (float) Math.log10(_selfz));
        dest.put(destOffset + 3, (float) Math.log10(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer log1p_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log1p_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.log1p(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.log1p_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log1p_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.log1p(_selfx));
        dest.put(destOffset + 1, (float) Math.log1p(_selfy));
        dest.put(destOffset + 2, (float) Math.log1p(_selfz));
        dest.put(destOffset + 3, (float) Math.log1p(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer log2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.log2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.log2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.log2(_selfx));
        dest.put(destOffset + 1, (float) Math.log2(_selfy));
        dest.put(destOffset + 2, (float) Math.log2(_selfz));
        dest.put(destOffset + 3, (float) Math.log2(_selfw));
        return dest;
    }

    public static float manhattanDistance_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float manhattanDistance_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Float4OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float manhattanDistance_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        return Math.abs(_selfx - otherX) + Math.abs(_selfy - otherY) + Math.abs(_selfz - otherZ) + Math.abs(_selfw - otherW);
    }

    public static float manhattanDistance_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        return Float4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static float manhattanDistance_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Float4Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Float4OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float manhattanDistance_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        return Math.abs(_selfx - _otherx) + Math.abs(_selfy - _othery) + Math.abs(_selfz - _otherz) + Math.abs(_selfw - _otherw);
    }

    public static float manhattanLength_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        return Float4OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static float manhattanLength_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float4Ops.manhattanLength(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float4OpsKernelsTypedBuffer.manhattanLength_apiGet(src, srcOffset);
    }

    public static float manhattanLength_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        return Math.abs(_selfx) + Math.abs(_selfy) + Math.abs(_selfz) + Math.abs(_selfw);
    }

    public static java.nio.FloatBuffer max_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer max_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer max_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.max(_selfx, scalar));
        dest.put(destOffset + 1, Math.max(_selfy, scalar));
        dest.put(destOffset + 2, Math.max(_selfz, scalar));
        dest.put(destOffset + 3, Math.max(_selfw, scalar));
        return dest;
    }

    public static java.nio.FloatBuffer max_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer max_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer max_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.max(_selfx, otherX));
        dest.put(destOffset + 1, Math.max(_selfy, otherY));
        dest.put(destOffset + 2, Math.max(_selfz, otherZ));
        dest.put(destOffset + 3, Math.max(_selfw, otherW));
        return dest;
    }

    public static java.nio.FloatBuffer max_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer max_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer max_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset + 0, Math.max(_selfx, _otherx));
        dest.put(destOffset + 1, Math.max(_selfy, _othery));
        dest.put(destOffset + 2, Math.max(_selfz, _otherz));
        dest.put(destOffset + 3, Math.max(_selfw, _otherw));
        return dest;
    }

    public static java.nio.FloatBuffer min_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer min_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer min_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.min(_selfx, scalar));
        dest.put(destOffset + 1, Math.min(_selfy, scalar));
        dest.put(destOffset + 2, Math.min(_selfz, scalar));
        dest.put(destOffset + 3, Math.min(_selfw, scalar));
        return dest;
    }

    public static java.nio.FloatBuffer min_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer min_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer min_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.min(_selfx, otherX));
        dest.put(destOffset + 1, Math.min(_selfy, otherY));
        dest.put(destOffset + 2, Math.min(_selfz, otherZ));
        dest.put(destOffset + 3, Math.min(_selfw, otherW));
        return dest;
    }

    public static java.nio.FloatBuffer min_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) otherOffset * 4L;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer min_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float4Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer min_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset + 0);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset + 0, Math.min(_selfx, _otherx));
        dest.put(destOffset + 1, Math.min(_selfy, _othery));
        dest.put(destOffset + 2, Math.min(_selfz, _otherz));
        dest.put(destOffset + 3, Math.min(_selfw, _otherw));
        return dest;
    }

    public static java.nio.FloatBuffer mod_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.FloatBuffer mod_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.FloatBuffer mod_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, flooredMod(_selfx, y));
        dest.put(destOffset + 1, flooredMod(_selfy, y));
        dest.put(destOffset + 2, flooredMod(_selfz, y));
        dest.put(destOffset + 3, flooredMod(_selfw, y));
        return dest;
    }

    public static java.nio.FloatBuffer mod_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.FloatBuffer mod_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY, yZ, yW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.FloatBuffer mod_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, flooredMod(_selfx, yX));
        dest.put(destOffset + 1, flooredMod(_selfy, yY));
        dest.put(destOffset + 2, flooredMod(_selfz, yZ));
        dest.put(destOffset + 3, flooredMod(_selfw, yW));
        return dest;
    }

    public static java.nio.FloatBuffer mod_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) yOffset * 4L;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.FloatBuffer mod_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 4) {
            Float4Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mod_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _yx = y.get(yOffset + 0);
        float _yy = y.get(yOffset + 1);
        float _yz = y.get(yOffset + 2);
        float _yw = y.get(yOffset + 3);
        dest.put(destOffset + 0, flooredMod(_selfx, _yx));
        dest.put(destOffset + 1, flooredMod(_selfy, _yy));
        dest.put(destOffset + 2, flooredMod(_selfz, _yz));
        dest.put(destOffset + 3, flooredMod(_selfw, _yw));
        return dest;
    }

    public static java.nio.FloatBuffer nextDown_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer nextDown_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.nextDown(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.nextDown_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer nextDown_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.nextDown(_selfx));
        dest.put(destOffset + 1, Math.nextDown(_selfy));
        dest.put(destOffset + 2, Math.nextDown(_selfz));
        dest.put(destOffset + 3, Math.nextDown(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer nextUp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer nextUp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.nextUp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.nextUp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer nextUp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.nextUp(_selfx));
        dest.put(destOffset + 1, Math.nextUp(_selfy));
        dest.put(destOffset + 2, Math.nextUp(_selfz));
        dest.put(destOffset + 3, Math.nextUp(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer normalize_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalize_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.normalize(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.normalize_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalize_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t4 = (1.0f / (float) Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.put(destOffset + 0, _selfx * _t4);
            dest.put(destOffset + 1, _selfy * _t4);
            dest.put(destOffset + 2, _selfz * _t4);
            dest.put(destOffset + 3, _selfw * _t4);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer normalizeMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.FloatBuffer normalizeMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.normalizeMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, length);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.normalizeMul_apiGet(dest, destOffset, src, srcOffset, length);
        return dest;
    }

    public static java.nio.FloatBuffer normalizeMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t5 = length * (1.0f / (float) Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.put(destOffset + 0, _selfx * _t5);
            dest.put(destOffset + 1, _selfy * _t5);
            dest.put(destOffset + 2, _selfz * _t5);
            dest.put(destOffset + 3, _selfw * _t5);
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ, rowW);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rowX, rowY, rowZ, rowW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, rowX, rowY, rowZ, rowW);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, rowX * _selfx);
        dest.put(destOffset + 1, rowX * _selfy);
        dest.put(destOffset + 2, rowX * _selfz);
        dest.put(destOffset + 3, rowX * _selfw);
        dest.put(destOffset + 4, rowY * _selfx);
        dest.put(destOffset + 5, rowY * _selfy);
        dest.put(destOffset + 6, rowY * _selfz);
        dest.put(destOffset + 7, rowY * _selfw);
        dest.put(destOffset + 8, rowZ * _selfx);
        dest.put(destOffset + 9, rowZ * _selfy);
        dest.put(destOffset + 10, rowZ * _selfz);
        dest.put(destOffset + 11, rowZ * _selfw);
        dest.put(destOffset + 12, rowW * _selfx);
        dest.put(destOffset + 13, rowW * _selfy);
        dest.put(destOffset + 14, rowW * _selfz);
        dest.put(destOffset + 15, rowW * _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) rowOffset * 4L;
        Float4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && row.hasArray() && rowOffset >= 0 && rowOffset <= row.limit() - 4) {
            Float4Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row.array(), row.arrayOffset() + rowOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, row, rowOffset);
        return dest;
    }

    public static java.nio.FloatBuffer outerProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _rowx = row.get(rowOffset + 0);
        float _rowy = row.get(rowOffset + 1);
        float _rowz = row.get(rowOffset + 2);
        float _roww = row.get(rowOffset + 3);
        dest.put(destOffset + 0, _rowx * _selfx);
        dest.put(destOffset + 1, _rowx * _selfy);
        dest.put(destOffset + 2, _rowx * _selfz);
        dest.put(destOffset + 3, _rowx * _selfw);
        dest.put(destOffset + 4, _rowy * _selfx);
        dest.put(destOffset + 5, _rowy * _selfy);
        dest.put(destOffset + 6, _rowy * _selfz);
        dest.put(destOffset + 7, _rowy * _selfw);
        dest.put(destOffset + 8, _rowz * _selfx);
        dest.put(destOffset + 9, _rowz * _selfy);
        dest.put(destOffset + 10, _rowz * _selfz);
        dest.put(destOffset + 11, _rowz * _selfw);
        dest.put(destOffset + 12, _roww * _selfx);
        dest.put(destOffset + 13, _roww * _selfy);
        dest.put(destOffset + 14, _roww * _selfz);
        dest.put(destOffset + 15, _roww * _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.pow(_selfx, exponent));
        dest.put(destOffset + 1, (float) Math.pow(_selfy, exponent));
        dest.put(destOffset + 2, (float) Math.pow(_selfz, exponent));
        dest.put(destOffset + 3, (float) Math.pow(_selfw, exponent));
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponentX, exponentY, exponentZ, exponentW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.pow(_selfx, exponentX));
        dest.put(destOffset + 1, (float) Math.pow(_selfy, exponentY));
        dest.put(destOffset + 2, (float) Math.pow(_selfz, exponentZ));
        dest.put(destOffset + 3, (float) Math.pow(_selfw, exponentW));
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) exponentOffset * 4L;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && exponent.hasArray() && exponentOffset >= 0 && exponentOffset <= exponent.limit() - 4) {
            Float4Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent.array(), exponent.arrayOffset() + exponentOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _exponentx = exponent.get(exponentOffset + 0);
        float _exponenty = exponent.get(exponentOffset + 1);
        float _exponentz = exponent.get(exponentOffset + 2);
        float _exponentw = exponent.get(exponentOffset + 3);
        dest.put(destOffset + 0, (float) Math.pow(_selfx, _exponentx));
        dest.put(destOffset + 1, (float) Math.pow(_selfy, _exponenty));
        dest.put(destOffset + 2, (float) Math.pow(_selfz, _exponentz));
        dest.put(destOffset + 3, (float) Math.pow(_selfw, _exponentw));
        return dest;
    }

    public static java.nio.FloatBuffer project_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    public static java.nio.FloatBuffer project_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, ontoX, ontoY, ontoZ, ontoW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    public static java.nio.FloatBuffer project_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = Math.fma(ontoW, _selfw, Math.fma(ontoZ, _selfz, Math.fma(ontoX, _selfx, ontoY * _selfy))) / Math.fma(ontoW, ontoW, Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY)));
        dest.put(destOffset + 0, ontoX * _t9);
        dest.put(destOffset + 1, ontoY * _t9);
        dest.put(destOffset + 2, ontoZ * _t9);
        dest.put(destOffset + 3, ontoW * _t9);
        return dest;
    }

    public static java.nio.FloatBuffer project_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) ontoOffset * 4L;
        Float4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.FloatBuffer project_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && onto.hasArray() && ontoOffset >= 0 && ontoOffset <= onto.limit() - 4) {
            Float4Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, onto.array(), onto.arrayOffset() + ontoOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return dest;
    }

    public static java.nio.FloatBuffer project_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _ontox = onto.get(ontoOffset + 0);
        float _ontoy = onto.get(ontoOffset + 1);
        float _ontoz = onto.get(ontoOffset + 2);
        float _ontow = onto.get(ontoOffset + 3);
        float _t9 = Math.fma(_ontow, _selfw, Math.fma(_ontoz, _selfz, Math.fma(_ontox, _selfx, _ontoy * _selfy))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        dest.put(destOffset + 0, _ontox * _t9);
        dest.put(destOffset + 1, _ontoy * _t9);
        dest.put(destOffset + 2, _ontoz * _t9);
        dest.put(destOffset + 3, _ontow * _t9);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ, normalW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t3 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.put(destOffset + 0, Math.fma(-normalX, _t3, _selfx));
        dest.put(destOffset + 1, Math.fma(-normalY, _t3, _selfy));
        dest.put(destOffset + 2, Math.fma(-normalZ, _t3, _selfz));
        dest.put(destOffset + 3, Math.fma(-normalW, _t3, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 4L;
        Float4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 4) {
            Float4Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.FloatBuffer projectOnPlane_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _normalx = normal.get(normalOffset + 0);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _normalw = normal.get(normalOffset + 3);
        float _t3 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.put(destOffset + 0, Math.fma(-_normalx, _t3, _selfx));
        dest.put(destOffset + 1, Math.fma(-_normaly, _t3, _selfy));
        dest.put(destOffset + 2, Math.fma(-_normalz, _t3, _selfz));
        dest.put(destOffset + 3, Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer radians_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer radians_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.radians(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.radians_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer radians_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.toRadians(_selfx));
        dest.put(destOffset + 1, (float) Math.toRadians(_selfy));
        dest.put(destOffset + 2, (float) Math.toRadians(_selfz));
        dest.put(destOffset + 3, (float) Math.toRadians(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer reflect_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ, normalW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = 2.0f * Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.put(destOffset + 0, Math.fma(-normalX, _t4, _selfx));
        dest.put(destOffset + 1, Math.fma(-normalY, _t4, _selfy));
        dest.put(destOffset + 2, Math.fma(-normalZ, _t4, _selfz));
        dest.put(destOffset + 3, Math.fma(-normalW, _t4, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer reflect_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 4L;
        Float4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 4) {
            Float4Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.FloatBuffer reflect_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _normalx = normal.get(normalOffset + 0);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _normalw = normal.get(normalOffset + 3);
        float _t4 = 2.0f * Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.put(destOffset + 0, Math.fma(-_normalx, _t4, _selfx));
        dest.put(destOffset + 1, Math.fma(-_normaly, _t4, _selfy));
        dest.put(destOffset + 2, Math.fma(-_normalz, _t4, _selfz));
        dest.put(destOffset + 3, Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    public static java.nio.FloatBuffer refract_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ, normalW, eta);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dest.put(destOffset + 0, Math.fma(eta, _selfx, -(normalX * _t11)));
            dest.put(destOffset + 1, Math.fma(eta, _selfy, -(normalY * _t11)));
            dest.put(destOffset + 2, Math.fma(eta, _selfz, -(normalZ * _t11)));
            dest.put(destOffset + 3, Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer refract_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) normalOffset * 4L;
        Float4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 4) {
            Float4Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset, eta);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return dest;
    }

    public static java.nio.FloatBuffer refract_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _normalx = normal.get(normalOffset + 0);
        float _normaly = normal.get(normalOffset + 1);
        float _normalz = normal.get(normalOffset + 2);
        float _normalw = normal.get(normalOffset + 3);
        float _t4 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        float _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0f), eta * eta, 1.0f);
        float _t11 = Math.fma(eta, _t4, (float) Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dest.put(destOffset + 0, Math.fma(eta, _selfx, -(_normalx * _t11)));
            dest.put(destOffset + 1, Math.fma(eta, _selfy, -(_normaly * _t11)));
            dest.put(destOffset + 2, Math.fma(eta, _selfz, -(_normalz * _t11)));
            dest.put(destOffset + 3, Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            dest.put(destOffset + 0, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer round_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer round_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.round(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.round_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer round_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.rint(_selfx));
        dest.put(destOffset + 1, (float) Math.rint(_selfy));
        dest.put(destOffset + 2, (float) Math.rint(_selfz));
        dest.put(destOffset + 3, (float) Math.rint(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer sign_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sign_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.sign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.sign_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sign_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.signum(_selfx));
        dest.put(destOffset + 1, Math.signum(_selfy));
        dest.put(destOffset + 2, Math.signum(_selfz));
        dest.put(destOffset + 3, Math.signum(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer sin_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sin_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.sin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.sin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sin_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.sin(_selfx));
        dest.put(destOffset + 1, (float) Math.sin(_selfy));
        dest.put(destOffset + 2, (float) Math.sin(_selfz));
        dest.put(destOffset + 3, (float) Math.sin(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer sinh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sinh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.sinh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.sinh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sinh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.sinh(_selfx));
        dest.put(destOffset + 1, (float) Math.sinh(_selfy));
        dest.put(destOffset + 2, (float) Math.sinh(_selfz));
        dest.put(destOffset + 3, (float) Math.sinh(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0, edge1);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge1);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = Math.max(0.0f, Math.min(1.0f, (_selfx - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (_selfy - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (_selfz - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (_selfw - edge0) * _t0_inv));
        dest.put(destOffset + 0, Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.put(destOffset + 1, Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        dest.put(destOffset + 2, Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15);
        dest.put(destOffset + 3, Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t16 = Math.max(0.0f, Math.min(1.0f, (_selfx - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (_selfy - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (_selfz - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (_selfw - edge0W) / (edge1W - edge0W)));
        dest.put(destOffset + 0, Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        dest.put(destOffset + 1, Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        dest.put(destOffset + 2, Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        dest.put(destOffset + 3, Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) edge0Offset * 4L;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) edge1Offset * 4L;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && edge0.hasArray() && edge0Offset >= 0 && edge0Offset <= edge0.limit() - 4 && edge1.hasArray() && edge1Offset >= 0 && edge1Offset <= edge1.limit() - 4) {
            Float4Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0.array(), edge0.arrayOffset() + edge0Offset, edge1.array(), edge1.arrayOffset() + edge1Offset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return dest;
    }

    public static java.nio.FloatBuffer smoothstep_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _edge0x = edge0.get(edge0Offset + 0);
        float _edge0y = edge0.get(edge0Offset + 1);
        float _edge0z = edge0.get(edge0Offset + 2);
        float _edge0w = edge0.get(edge0Offset + 3);
        float _edge1x = edge1.get(edge1Offset + 0);
        float _edge1y = edge1.get(edge1Offset + 1);
        float _edge1z = edge1.get(edge1Offset + 2);
        float _edge1w = edge1.get(edge1Offset + 3);
        float _t16 = Math.max(0.0f, Math.min(1.0f, (_selfx - _edge0x) / (_edge1x - _edge0x)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (_selfy - _edge0y) / (_edge1y - _edge0y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (_selfz - _edge0z) / (_edge1z - _edge0z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (_selfw - _edge0w) / (_edge1w - _edge0w)));
        dest.put(destOffset + 0, Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        dest.put(destOffset + 1, Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        dest.put(destOffset + 2, Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        dest.put(destOffset + 3, Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    public static java.nio.FloatBuffer sqrt_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer sqrt_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.sqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.sqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sqrt_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.sqrt(_selfx));
        dest.put(destOffset + 1, (float) Math.sqrt(_selfy));
        dest.put(destOffset + 2, (float) Math.sqrt(_selfz));
        dest.put(destOffset + 3, (float) Math.sqrt(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer step_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.FloatBuffer step_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge);
        return dest;
    }

    public static java.nio.FloatBuffer step_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, _selfx < edge ? 0.0f : 1.0f);
        dest.put(destOffset + 1, _selfy < edge ? 0.0f : 1.0f);
        dest.put(destOffset + 2, _selfz < edge ? 0.0f : 1.0f);
        dest.put(destOffset + 3, _selfw < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer step_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    public static java.nio.FloatBuffer step_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edgeX, edgeY, edgeZ, edgeW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    public static java.nio.FloatBuffer step_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, _selfx < edgeX ? 0.0f : 1.0f);
        dest.put(destOffset + 1, _selfy < edgeY ? 0.0f : 1.0f);
        dest.put(destOffset + 2, _selfz < edgeZ ? 0.0f : 1.0f);
        dest.put(destOffset + 3, _selfw < edgeW ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer step_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) edgeOffset * 4L;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.FloatBuffer step_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && edge.hasArray() && edgeOffset >= 0 && edgeOffset <= edge.limit() - 4) {
            Float4Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge.array(), edge.arrayOffset() + edgeOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return dest;
    }

    public static java.nio.FloatBuffer step_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _edgex = edge.get(edgeOffset + 0);
        float _edgey = edge.get(edgeOffset + 1);
        float _edgez = edge.get(edgeOffset + 2);
        float _edgew = edge.get(edgeOffset + 3);
        dest.put(destOffset + 0, _selfx < _edgex ? 0.0f : 1.0f);
        dest.put(destOffset + 1, _selfy < _edgey ? 0.0f : 1.0f);
        dest.put(destOffset + 2, _selfz < _edgez ? 0.0f : 1.0f);
        dest.put(destOffset + 3, _selfw < _edgew ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer tan_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer tan_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.tan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.tan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer tan_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.tan(_selfx));
        dest.put(destOffset + 1, (float) Math.tan(_selfy));
        dest.put(destOffset + 2, (float) Math.tan(_selfz));
        dest.put(destOffset + 3, (float) Math.tan(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer tanh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer tanh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.tanh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.tanh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer tanh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, (float) Math.tanh(_selfx));
        dest.put(destOffset + 1, (float) Math.tanh(_selfy));
        dest.put(destOffset + 2, (float) Math.tanh(_selfz));
        dest.put(destOffset + 3, (float) Math.tanh(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer trunc_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer trunc_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.trunc(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.trunc_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer trunc_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, _selfx >= 0.0f ? (float) Math.floor(_selfx) : (float) Math.ceil(_selfx));
        dest.put(destOffset + 1, _selfy >= 0.0f ? (float) Math.floor(_selfy) : (float) Math.ceil(_selfy));
        dest.put(destOffset + 2, _selfz >= 0.0f ? (float) Math.floor(_selfz) : (float) Math.ceil(_selfz));
        dest.put(destOffset + 3, _selfw >= 0.0f ? (float) Math.floor(_selfw) : (float) Math.ceil(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer ulp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer ulp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.ulp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.ulp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer ulp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset + 0, Math.ulp(_selfx));
        dest.put(destOffset + 1, Math.ulp(_selfy));
        dest.put(destOffset + 2, Math.ulp(_selfz));
        dest.put(destOffset + 3, Math.ulp(_selfw));
        return dest;
    }

    public static java.nio.FloatBuffer preMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) matOffset * 4L;
        Float4OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 16) {
            Float4Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
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
        dest.put(destOffset + 0, Math.fma(_mat03, _selfw, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy))));
        dest.put(destOffset + 1, Math.fma(_mat13, _selfw, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        dest.put(destOffset + 2, Math.fma(_mat23, _selfw, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        dest.put(destOffset + 3, Math.fma(_mat33, _selfw, Math.fma(_mat32, _selfz, Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    public static java.nio.FloatBuffer rotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quatX, quatY, quatZ, quatW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.put(destOffset + 0, Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.put(destOffset + 1, Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy))));
        dest.put(destOffset + 2, Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) quatOffset * 4L;
        Float4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && quat.hasArray() && quatOffset >= 0 && quatOffset <= quat.limit() - 4) {
            Float4Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quat.array(), quat.arrayOffset() + quatOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, quat, quatOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
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
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        dest.put(destOffset + 0, Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0)));
        dest.put(destOffset + 1, Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0)));
        dest.put(destOffset + 2, Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0)));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) axisOffset * 4L;
        Float4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Float4Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
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
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.rotateInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quatX, quatY, quatZ, quatW);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateInverse_apiGet(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.put(destOffset + 0, Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.put(destOffset + 1, Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy))));
        dest.put(destOffset + 2, Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) quatOffset * 4L;
        Float4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && quat.hasArray() && quatOffset >= 0 && quatOffset <= quat.limit() - 4) {
            Float4Ops.rotateInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quat.array(), quat.arrayOffset() + quatOffset);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateInverse_apiGet(dest, destOffset, src, srcOffset, quat, quatOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateInverse_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
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
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.rotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, _selfx);
        dest.put(destOffset + 1, Math.fma(_selfy, _t1, -(_selfz * _t0)));
        dest.put(destOffset + 2, Math.fma(_selfy, _t0, _selfz * _t1));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.rotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_selfx, _t1, _selfz * _t0));
        dest.put(destOffset + 1, _selfy);
        dest.put(destOffset + 2, Math.fma(_selfz, _t1, -(_selfx * _t0)));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L;
        Float4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float4Ops.rotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float4OpsKernelsTypedBuffer.rotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _selfx = src.get(srcOffset + 0);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest.put(destOffset + 0, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.put(destOffset + 1, Math.fma(_selfx, _t0, _selfy * _t1));
        dest.put(destOffset + 2, _selfz);
        dest.put(destOffset + 3, _selfw);
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
