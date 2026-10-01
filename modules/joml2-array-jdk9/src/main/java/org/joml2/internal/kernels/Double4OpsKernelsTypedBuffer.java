// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double4Ops} whose leading storage
 * parameter is a typed {@link java.nio.DoubleBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double4Ops} and its sibling kernel units. Not public API.
 */
public final class Double4OpsKernelsTypedBuffer {
    private Double4OpsKernelsTypedBuffer() {}

    public static java.nio.DoubleBuffer add_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer add_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer add_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, otherX + src.get(srcOffset));
        dest.put(destOffset + 1, otherY + _selfy);
        dest.put(destOffset + 2, otherZ + _selfz);
        dest.put(destOffset + 3, otherW + _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer add_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer add_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double4Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer add_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, other.get(otherOffset) + src.get(srcOffset));
        dest.put(destOffset + 1, _othery + _selfy);
        dest.put(destOffset + 2, _otherz + _selfz);
        dest.put(destOffset + 3, _otherw + _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer div_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer div_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer div_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) / scalar);
        dest.put(destOffset + 1, _selfy / scalar);
        dest.put(destOffset + 2, _selfz / scalar);
        dest.put(destOffset + 3, _selfw / scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer div_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer div_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer div_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) / otherX);
        dest.put(destOffset + 1, _selfy / otherY);
        dest.put(destOffset + 2, _selfz / otherZ);
        dest.put(destOffset + 3, _selfw / otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer div_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer div_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double4Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer div_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, src.get(srcOffset) / other.get(otherOffset));
        dest.put(destOffset + 1, _selfy / _othery);
        dest.put(destOffset + 2, _selfz / _otherz);
        dest.put(destOffset + 3, _selfw / _otherw);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double b, double cX, double cY, double cZ, double cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double b, double cX, double cY, double cZ, double cW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b, cX, cY, cZ, cW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double b, double cX, double cY, double cZ, double cW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(src.get(srcOffset), b, cX));
        dest.put(destOffset + 1, Math.fma(_selfy, b, cY));
        dest.put(destOffset + 2, Math.fma(_selfz, b, cZ));
        dest.put(destOffset + 3, Math.fma(_selfw, b, cW));
        return dest;
    }

    public static java.nio.DoubleBuffer fma_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer c, int cOffset, double b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset * 8L;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer c, int cOffset, double b) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 4) {
            Double4Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, c.array(), c.arrayOffset() + cOffset, b);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, c, cOffset, b);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer c, int cOffset, double b) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _cy = c.get(cOffset + 1);
        double _cz = c.get(cOffset + 2);
        double _cw = c.get(cOffset + 3);
        dest.put(destOffset, Math.fma(src.get(srcOffset), b, c.get(cOffset)));
        dest.put(destOffset + 1, Math.fma(_selfy, b, _cy));
        dest.put(destOffset + 2, Math.fma(_selfz, b, _cz));
        dest.put(destOffset + 3, Math.fma(_selfw, b, _cw));
        return dest;
    }

    public static java.nio.DoubleBuffer fma_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(src.get(srcOffset), bX, cX));
        dest.put(destOffset + 1, Math.fma(_selfy, bY, cY));
        dest.put(destOffset + 2, Math.fma(_selfz, bZ, cZ));
        dest.put(destOffset + 3, Math.fma(_selfw, bW, cW));
        return dest;
    }

    public static java.nio.DoubleBuffer fma_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset * 8L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset * 8L;
        Double4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 4 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 4) {
            Double4Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _by = b.get(bOffset + 1);
        double _bz = b.get(bOffset + 2);
        double _bw = b.get(bOffset + 3);
        double _cy = c.get(cOffset + 1);
        double _cz = c.get(cOffset + 2);
        double _cw = c.get(cOffset + 3);
        dest.put(destOffset, Math.fma(src.get(srcOffset), b.get(bOffset), c.get(cOffset)));
        dest.put(destOffset + 1, Math.fma(_selfy, _by, _cy));
        dest.put(destOffset + 2, Math.fma(_selfz, _bz, _cz));
        dest.put(destOffset + 3, Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, scalar * src.get(srcOffset));
        dest.put(destOffset + 1, scalar * _selfy);
        dest.put(destOffset + 2, scalar * _selfz);
        dest.put(destOffset + 3, scalar * _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, otherX * src.get(srcOffset));
        dest.put(destOffset + 1, otherY * _selfy);
        dest.put(destOffset + 2, otherZ * _selfz);
        dest.put(destOffset + 3, otherW * _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, other.get(otherOffset) * src.get(srcOffset));
        dest.put(destOffset + 1, _othery * _selfy);
        dest.put(destOffset + 2, _otherz * _selfz);
        dest.put(destOffset + 3, _otherw * _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, -src.get(srcOffset));
        dest.put(destOffset + 1, -_selfy);
        dest.put(destOffset + 2, -_selfz);
        dest.put(destOffset + 3, -_selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) - otherX);
        dest.put(destOffset + 1, _selfy - otherY);
        dest.put(destOffset + 2, _selfz - otherZ);
        dest.put(destOffset + 3, _selfw - otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double4Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, src.get(srcOffset) - other.get(otherOffset));
        dest.put(destOffset + 1, _selfy - _othery);
        dest.put(destOffset + 2, _selfz - _otherz);
        dest.put(destOffset + 3, _selfw - _otherw);
        return dest;
    }

    public static java.nio.DoubleBuffer makeUniformDirection_unsafe(java.nio.DoubleBuffer dest, int destOffset, double u, double v, double w) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double4OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v, w);
        return dest;
    }

    public static java.nio.DoubleBuffer makeUniformDirection_api(java.nio.DoubleBuffer dest, int destOffset, double u, double v, double w) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double4Ops.makeUniformDirection(dest.array(), dest.arrayOffset() + destOffset, u, v, w);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.makeUniformDirection_apiGet(dest, destOffset, u, v, w);
        return dest;
    }

    public static java.nio.DoubleBuffer makeUniformDirection_apiGet(java.nio.DoubleBuffer dest, int destOffset, double u, double v, double w) {
        double _t0 = java.lang.Math.sqrt(u);
        double _t1 = v * 6.283185307179586;
        double _t3 = w * 6.283185307179586;
        double _t4 = Math.sin(_t1);
        double _t5 = java.lang.Math.sqrt(1.0 - u);
        double _t6 = Math.sin(_t3);
        dest.put(destOffset, Math.cosFromSin(_t4, _t1) * _t5);
        dest.put(destOffset + 1, _t4 * _t5);
        dest.put(destOffset + 2, Math.cosFromSin(_t6, _t3) * _t0);
        dest.put(destOffset + 3, _t6 * _t0);
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ, double vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double4OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ, double vW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double4Ops.set(dest.array(), dest.arrayOffset() + destOffset, vX, vY, vZ, vW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ, double vW) {
        dest.put(destOffset, vX);
        dest.put(destOffset + 1, vY);
        dest.put(destOffset + 2, vZ);
        dest.put(destOffset + 3, vW);
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double4OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4) {
            Double4Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        double _vw = v.get(vOffset + 3);
        dest.put(destOffset, v.get(vOffset));
        dest.put(destOffset + 1, _vy);
        dest.put(destOffset + 2, _vz);
        dest.put(destOffset + 3, _vw);
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double4OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double4Ops.set(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, double s) {
        dest.put(destOffset, s);
        dest.put(destOffset + 1, s);
        dest.put(destOffset + 2, s);
        dest.put(destOffset + 3, s);
        return dest;
    }

    public static java.nio.DoubleBuffer makeZero_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double4OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeZero_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Double4Ops.makeZero(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.makeZero_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeZero_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.put(destOffset, Math.fma(p1X, _t7, src.get(srcOffset) * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        dest.put(destOffset + 1, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        dest.put(destOffset + 2, Math.fma(p1Z, _t7, _selfz * _t8) + Math.fma(p2Z, _t6, p3Z * _t2));
        dest.put(destOffset + 3, Math.fma(p1W, _t7, _selfw * _t8) + Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 8L;
        Double4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 4) {
            Double4Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p1w = p1.get(p1Offset + 3);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p2w = p2.get(p2Offset + 3);
        double _p3y = p3.get(p3Offset + 1);
        double _p3z = p3.get(p3Offset + 2);
        double _p3w = p3.get(p3Offset + 3);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.put(destOffset, Math.fma(p1.get(p1Offset), _t7, src.get(srcOffset) * _t8) + Math.fma(p2.get(p2Offset), _t6, p3.get(p3Offset) * _t2));
        dest.put(destOffset + 1, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        return bezier_apiGet_s19c6041d_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _p3z, _p3w, _t2, _t6, _t7, _t8);
    }

    /** Piece 2 of {@code bezier_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer bezier_apiGet_s19c6041d_1(java.nio.DoubleBuffer dest, int destOffset, double _selfz, double _selfw, double _p1z, double _p1w, double _p2z, double _p2w, double _p3z, double _p3w, double _t2, double _t6, double _t7, double _t8) {
        dest.put(destOffset + 2, Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2));
        dest.put(destOffset + 3, Math.fma(_p1w, _t7, _selfw * _t8) + Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.put(destOffset, Math.fma(p2X, _t0, Math.fma(p1X, _t3, src.get(srcOffset) * _t4)));
        dest.put(destOffset + 1, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.put(destOffset + 2, Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4)));
        dest.put(destOffset + 3, Math.fma(p2W, _t0, Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        Double4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4) {
            Double4Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p1w = p1.get(p1Offset + 3);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p2w = p2.get(p2Offset + 3);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.put(destOffset, Math.fma(p2.get(p2Offset), _t0, Math.fma(p1.get(p1Offset), _t3, src.get(srcOffset) * _t4)));
        dest.put(destOffset + 1, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        dest.put(destOffset + 2, Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4)));
        dest.put(destOffset + 3, Math.fma(_p2w, _t0, Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.put(destOffset, Math.fma(p1X - src.get(srcOffset), _t2, (p2X - p1X) * _t1));
        dest.put(destOffset + 1, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.put(destOffset + 2, Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        dest.put(destOffset + 3, Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        Double4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4) {
            Double4Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p1w = p1.get(p1Offset + 3);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p2w = p2.get(p2Offset + 3);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.put(destOffset, Math.fma(_p1x - src.get(srcOffset), _t2, (p2.get(p2Offset) - _p1x) * _t1));
        dest.put(destOffset + 1, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        dest.put(destOffset + 2, Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        dest.put(destOffset + 3, Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.put(destOffset, Math.fma(p3X - p2X, _t2, Math.fma(p1X - src.get(srcOffset), _t6, (p2X - p1X) * _t5)));
        dest.put(destOffset + 1, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.put(destOffset + 2, Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        dest.put(destOffset + 3, Math.fma(p3W - p2W, _t2, Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 8L;
        Double4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 4) {
            Double4Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p1w = p1.get(p1Offset + 3);
        double _p2x = p2.get(p2Offset);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p2w = p2.get(p2Offset + 3);
        double _p3y = p3.get(p3Offset + 1);
        double _p3z = p3.get(p3Offset + 2);
        double _p3w = p3.get(p3Offset + 3);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.put(destOffset, Math.fma(p3.get(p3Offset) - _p2x, _t2, Math.fma(_p1x - src.get(srcOffset), _t6, (_p2x - _p1x) * _t5)));
        dest.put(destOffset + 1, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        return bezierTangent_apiGet_sce678400_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _p3z, _p3w, _t2, _t5, _t6);
    }

    /** Piece 2 of {@code bezierTangent_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer bezierTangent_apiGet_sce678400_1(java.nio.DoubleBuffer dest, int destOffset, double _selfz, double _selfw, double _p1z, double _p1w, double _p2z, double _p2w, double _p3z, double _p3w, double _t2, double _t5, double _t6) {
        dest.put(destOffset + 2, Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        dest.put(destOffset + 3, Math.fma(_p3w - _p2w, _t2, Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.put(destOffset, 0.5 * (Math.fma(2.0, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        dest.put(destOffset + 1, 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_apiGet_sfc9e3f2d_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer catmullRom_apiGet_sfc9e3f2d_1(java.nio.DoubleBuffer dest, int destOffset, double p1Z, double p1W, double p2Z, double p2W, double p3Z, double p3W, double t, double _selfz, double _selfw, double _t0, double _t1) {
        dest.put(destOffset + 2, 0.5 * (Math.fma(2.0, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0, p1Z, Math.fma(2.0, _selfz, Math.fma(4.0, p2Z, -p3Z))), _t0, Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _selfz)) * _t1)));
        dest.put(destOffset + 3, 0.5 * (Math.fma(2.0, p1W, t * (p2W - _selfw)) + Math.fma(Math.fma(-5.0, p1W, Math.fma(2.0, _selfw, Math.fma(4.0, p2W, -p3W))), _t0, Math.fma(-3.0, p2W, Math.fma(3.0, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 8L;
        Double4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 4) {
            Double4Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p1w = p1.get(p1Offset + 3);
        double _p2x = p2.get(p2Offset);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p2w = p2.get(p2Offset + 3);
        double _p3x = p3.get(p3Offset);
        double _p3y = p3.get(p3Offset + 1);
        double _p3z = p3.get(p3Offset + 2);
        double _p3w = p3.get(p3Offset + 3);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.put(destOffset, 0.5 * (Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), _t0, Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        return catmullRom_apiGet_s17b756fa_1(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer catmullRom_apiGet_s17b756fa_1(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfy, double _selfz, double _selfw, double _p1y, double _p1z, double _p1w, double _p2y, double _p2z, double _p2w, double _p3y, double _p3z, double _p3w, double _t0, double _t1) {
        dest.put(destOffset + 1, 0.5 * (Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), _t0, Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        dest.put(destOffset + 2, 0.5 * (Math.fma(2.0, _p1z, t * (_p2z - _selfz)) + Math.fma(Math.fma(-5.0, _p1z, Math.fma(2.0, _selfz, Math.fma(4.0, _p2z, -_p3z))), _t0, Math.fma(-3.0, _p2z, Math.fma(3.0, _p1z, _p3z - _selfz)) * _t1)));
        dest.put(destOffset + 3, 0.5 * (Math.fma(2.0, _p1w, t * (_p2w - _selfw)) + Math.fma(Math.fma(-5.0, _p1w, Math.fma(2.0, _selfw, Math.fma(4.0, _p2w, -_p3w))), _t0, Math.fma(-3.0, _p2w, Math.fma(3.0, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p1W, double p2X, double p2Y, double p2Z, double p2W, double p3X, double p3Y, double p3Z, double p3W, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = t * t;
        dest.put(destOffset, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.put(destOffset + 1, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        dest.put(destOffset + 2, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Z, Math.fma(2.0, _selfz, Math.fma(4.0, p2Z, -p3Z))), Math.fma(3.0 * Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        return catmullRomTangent_apiGet_sacbb2b32_1(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer catmullRomTangent_apiGet_sacbb2b32_1(java.nio.DoubleBuffer dest, int destOffset, double p1W, double p2W, double p3W, double t, double _selfw, double _t0) {
        dest.put(destOffset + 3, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1W, Math.fma(2.0, _selfw, Math.fma(4.0, p2W, -p3W))), Math.fma(3.0 * Math.fma(-3.0, p2W, Math.fma(3.0, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 8L;
        Double4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 4 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 4 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 4) {
            Double4Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p1w = p1.get(p1Offset + 3);
        double _p2x = p2.get(p2Offset);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p2w = p2.get(p2Offset + 3);
        double _p3x = p3.get(p3Offset);
        double _p3y = p3.get(p3Offset + 1);
        double _p3z = p3.get(p3Offset + 2);
        double _p3w = p3.get(p3Offset + 3);
        double _t0 = t * t;
        dest.put(destOffset, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), Math.fma(3.0 * Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        return catmullRomTangent_apiGet_s858a9419_1(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer catmullRomTangent_apiGet_s858a9419_1(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfy, double _selfz, double _selfw, double _p1y, double _p1z, double _p1w, double _p2y, double _p2z, double _p2w, double _p3y, double _p3z, double _p3w, double _t0) {
        dest.put(destOffset + 1, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), Math.fma(3.0 * Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.put(destOffset + 2, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1z, Math.fma(2.0, _selfz, Math.fma(4.0, _p2z, -_p3z))), Math.fma(3.0 * Math.fma(-3.0, _p2z, Math.fma(3.0, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        dest.put(destOffset + 3, 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1w, Math.fma(2.0, _selfw, Math.fma(4.0, _p2w, -_p3w))), Math.fma(3.0 * Math.fma(-3.0, _p2w, Math.fma(3.0, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest.put(destOffset, Math.fma(src.get(srcOffset), _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.put(destOffset + 1, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        dest.put(destOffset + 2, Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
        dest.put(destOffset + 3, Math.fma(_selfw, _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset * 8L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset * 8L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset * 8L;
        Double4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 4 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 4 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 4) {
            Double4Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0y = t0.get(t0Offset + 1);
        double _t0z = t0.get(t0Offset + 2);
        double _t0w = t0.get(t0Offset + 3);
        double _v1y = v1.get(v1Offset + 1);
        double _v1z = v1.get(v1Offset + 2);
        double _v1w = v1.get(v1Offset + 3);
        double _t1y = t1.get(t1Offset + 1);
        double _t1z = t1.get(t1Offset + 2);
        double _t1w = t1.get(t1Offset + 3);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest.put(destOffset, Math.fma(src.get(srcOffset), _t10, t0.get(t0Offset) * _t7) + Math.fma(t1.get(t1Offset), _t5, v1.get(v1Offset) * _t9));
        return hermite_apiGet_s4e558a62_1(dest, destOffset, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t5, _t7, _t9, _t10);
    }

    /** Piece 2 of {@code hermite_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer hermite_apiGet_s4e558a62_1(java.nio.DoubleBuffer dest, int destOffset, double _selfy, double _selfz, double _selfw, double _t0y, double _t0z, double _t0w, double _v1y, double _v1z, double _v1w, double _t1y, double _t1z, double _t1w, double _t5, double _t7, double _t9, double _t10) {
        dest.put(destOffset + 1, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        dest.put(destOffset + 2, Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9));
        dest.put(destOffset + 3, Math.fma(_selfw, _t10, _t0w * _t7) + Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double t0W, double v1X, double v1Y, double v1Z, double v1W, double t1X, double t1Y, double t1Z, double t1W, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest.put(destOffset, Math.fma(src.get(srcOffset), _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.put(destOffset + 1, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        dest.put(destOffset + 2, Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
        dest.put(destOffset + 3, Math.fma(_selfw, _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset * 8L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset * 8L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset * 8L;
        Double4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 4 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 4 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 4) {
            Double4Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0y = t0.get(t0Offset + 1);
        double _t0z = t0.get(t0Offset + 2);
        double _t0w = t0.get(t0Offset + 3);
        double _v1y = v1.get(v1Offset + 1);
        double _v1z = v1.get(v1Offset + 2);
        double _v1w = v1.get(v1Offset + 3);
        double _t1y = t1.get(t1Offset + 1);
        double _t1z = t1.get(t1Offset + 2);
        double _t1w = t1.get(t1Offset + 3);
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest.put(destOffset, Math.fma(src.get(srcOffset), _t6, t0.get(t0Offset) * _t9) + Math.fma(t1.get(t1Offset), _t8, v1.get(v1Offset) * _t7));
        return hermiteTangent_apiGet_s70a3c5d9_1(dest, destOffset, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t6, _t7, _t8, _t9);
    }

    /** Piece 2 of {@code hermiteTangent_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer hermiteTangent_apiGet_s70a3c5d9_1(java.nio.DoubleBuffer dest, int destOffset, double _selfy, double _selfz, double _selfw, double _t0y, double _t0z, double _t0w, double _v1y, double _v1z, double _v1w, double _t1y, double _t1z, double _t1w, double _t6, double _t7, double _t8, double _t9) {
        dest.put(destOffset + 1, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        dest.put(destOffset + 2, Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7));
        dest.put(destOffset + 3, Math.fma(_selfw, _t6, _t0w * _t9) + Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(t, otherX - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(t, otherY - _selfy, _selfy));
        dest.put(destOffset + 2, Math.fma(t, otherZ - _selfz, _selfz));
        dest.put(destOffset + 3, Math.fma(t, otherW - _selfw, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, Math.fma(t, other.get(otherOffset) - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(t, _othery - _selfy, _selfy));
        dest.put(destOffset + 2, Math.fma(t, _otherz - _selfz, _selfz));
        dest.put(destOffset + 3, Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double tX, double tY, double tZ, double tW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(tX, otherX - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(tY, otherY - _selfy, _selfy));
        dest.put(destOffset + 2, Math.fma(tZ, otherZ - _selfz, _selfz));
        dest.put(destOffset + 3, Math.fma(tW, otherW - _selfw, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset * 8L;
        Double4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer t, int tOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4 && t.hasArray() && tOffset >= 0 && tOffset <= t.limit() - 4) {
            Double4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t.array(), t.arrayOffset() + tOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer t, int tOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        double _ty = t.get(tOffset + 1);
        double _tz = t.get(tOffset + 2);
        double _tw = t.get(tOffset + 3);
        dest.put(destOffset, Math.fma(t.get(tOffset), other.get(otherOffset) - _selfx, _selfx));
        dest.put(destOffset + 1, Math.fma(_ty, _othery - _selfy, _selfy));
        dest.put(destOffset + 2, Math.fma(_tz, _otherz - _selfz, _selfz));
        dest.put(destOffset + 3, Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t10 = Math.fma(otherW, otherW, Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t10));
        double _t16 = _selfx * _t11;
        double _t19 = _selfw * _t11;
        double _t21 = _selfz * _t11;
        double _t24 = _selfy * _t11;
        return slerp_apiGet_sabd04cab_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, _t14, _t16, _t19, _t21, _t24, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9), Math.fma(otherW * _t14, _t19, Math.fma(otherZ * _t14, _t21, Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24))));
    }

    /** Piece 2 of {@code slerp_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_sabd04cab_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28, double _t31) {
        double _t40 = Math.fma(otherW, _t14, -(_t31 * _t19));
        double _t41 = Math.fma(otherZ, _t14, -(_t31 * _t21));
        double _t42 = Math.fma(otherX, _t14, -(_t31 * _t16));
        double _t43 = Math.fma(otherY, _t14, -(_t31 * _t24));
        double _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = Math.fma(_t48, _t19, _t40);
        double _t50 = Math.fma(_t48, _t21, _t41);
        double _t51 = Math.fma(_t48, _t16, _t42);
        double _t52 = Math.fma(_t48, _t24, _t43);
        double _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        double _sp0 = _t28 * Math.sin(_t61) * (1.0 / java.lang.Math.sqrt(_t57));
        double _t66 = _t28 * Math.cos(_t61);
        dest.put(destOffset, Math.fma(_t16, _t66, _sp0 * _t51));
        return slerp_apiGet_sabd04cab_2(dest, destOffset, _t19, _t21, _t24, _t49, _t50, _t52, _sp0, _t66);
    }

    /** Piece 3 of {@code slerp_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_sabd04cab_2(java.nio.DoubleBuffer dest, int destOffset, double _t19, double _t21, double _t24, double _t49, double _t50, double _t52, double _sp0, double _t66) {
        dest.put(destOffset + 1, Math.fma(_t24, _t66, _sp0 * _t52));
        dest.put(destOffset + 2, Math.fma(_t21, _t66, _sp0 * _t50));
        dest.put(destOffset + 3, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double4OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return Double4OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.nio.DoubleBuffer slerp_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t7 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t8 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = otherW * _t7;
        double _t18 = otherZ * _t7;
        double _t19 = otherX * _t7;
        double _t20 = otherY * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = java.lang.Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = Math.fma(_t80, _t43, _t72);
        double _t82 = Math.fma(_t80, _t45, _t73);
        double _t83 = Math.fma(_t80, _t47, _t74);
        double _t84 = Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t114 = _t60 * Math.sin(_t110);
        double _t115 = _t60 * Math.cos(_t110);
        double _t120, _t121, _t122, _t123;
        if (_t106 > 0.0) {
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 5.048709793414476E-29) {
                    dest.put(destOffset, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    dest.put(destOffset, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                dest.put(destOffset, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                dest.put(destOffset + 1, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                dest.put(destOffset + 2, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                dest.put(destOffset + 3, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            dest.put(destOffset, Math.fma(t, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, Math.fma(t, otherY - _selfy, _selfy));
            dest.put(destOffset + 2, Math.fma(t, otherZ - _selfz, _selfz));
            dest.put(destOffset + 3, Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double4Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        double _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 2.2250738585072014E-308 && _t9 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t10 = Math.fma(_otherw, _otherw, Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 2.2250738585072014E-308 && _t10 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t11 = (1.0 / java.lang.Math.sqrt(_t9));
        return slerp_apiGet_sdb87e472_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _otherx, _othery, _otherz, _otherw, (1.0 / java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * java.lang.Math.sqrt(_t10) + (1.0 - t) * java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_sdb87e472_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double _otherx, double _othery, double _otherz, double _otherw, double _t14, double _t16, double _t19, double _t21, double _t24, double _t28) {
        double _t31 = Math.fma(_otherw * _t14, _t19, Math.fma(_otherz * _t14, _t21, Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        double _t40 = Math.fma(_otherw, _t14, -(_t31 * _t19));
        double _t41 = Math.fma(_otherz, _t14, -(_t31 * _t21));
        double _t42 = Math.fma(_otherx, _t14, -(_t31 * _t16));
        double _t43 = Math.fma(_othery, _t14, -(_t31 * _t24));
        double _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        double _t49 = Math.fma(_t48, _t19, _t40);
        double _t50 = Math.fma(_t48, _t21, _t41);
        double _t51 = Math.fma(_t48, _t16, _t42);
        double _t52 = Math.fma(_t48, _t24, _t43);
        double _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 5.048709793414476E-29 && _t57 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t61 = t * Math.atan2(java.lang.Math.sqrt(_t57), _t31);
        return slerp_apiGet_sdb87e472_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.sin(_t61) * (1.0 / java.lang.Math.sqrt(_t57)), _t28 * Math.cos(_t61));
    }

    /** Piece 3 of {@code slerp_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_sdb87e472_2(java.nio.DoubleBuffer dest, int destOffset, double _t16, double _t19, double _t21, double _t24, double _t49, double _t50, double _t51, double _t52, double _sp0, double _t66) {
        dest.put(destOffset, Math.fma(_t16, _t66, _sp0 * _t51));
        dest.put(destOffset + 1, Math.fma(_t24, _t66, _sp0 * _t52));
        dest.put(destOffset + 2, Math.fma(_t21, _t66, _sp0 * _t50));
        dest.put(destOffset + 3, Math.fma(_t19, _t66, _sp0 * _t49));
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double4OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double4OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.DoubleBuffer slerp_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double4OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        double _t7 = unitScale(_otherz, _otherw, java.lang.Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t8 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t17 = _otherw * _t7;
        double _t18 = _otherz * _t7;
        double _t19 = _otherx * _t7;
        double _t20 = _othery * _t7;
        double _t21 = _selfw * _t8;
        double _t22 = _selfz * _t8;
        double _t23 = _selfx * _t8;
        double _t24 = _selfy * _t8;
        double _t25 = java.lang.Math.min(_t8, _t7);
        double _t25_inv = 1.0 / _t25;
        double _t36 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        double _t37 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)));
        double _t40 = (1.0 / java.lang.Math.sqrt(_t36));
        double _t41 = (1.0 / java.lang.Math.sqrt(_t37));
        double _t43 = _t41 * _t21;
        double _t45 = _t41 * _t22;
        double _t47 = _t41 * _t23;
        double _t49 = _t41 * _t24;
        double _t50 = -_t49;
        double _t51 = -_t43;
        double _t60 = t * java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0 - t) * java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        double _t63 = Math.fma(_t40 * _t17, _t43, Math.fma(_t40 * _t18, _t45, Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        double _t72 = Math.fma(_t40, _t17, -(_t63 * _t43));
        double _t73 = Math.fma(_t40, _t18, -(_t63 * _t45));
        double _t74 = Math.fma(_t40, _t19, -(_t63 * _t47));
        double _t75 = Math.fma(_t40, _t20, -(_t63 * _t49));
        double _t80 = -Math.fma(_t72, _t43, Math.fma(_t73, _t45, Math.fma(_t74, _t47, _t75 * _t49)));
        double _t81 = Math.fma(_t80, _t43, _t72);
        double _t82 = Math.fma(_t80, _t45, _t73);
        double _t83 = Math.fma(_t80, _t47, _t74);
        double _t84 = Math.fma(_t80, _t49, _t75);
        double _t90 = unitScale(_t82, _t81, java.lang.Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        double _t97 = _t81 * _t90;
        double _t98 = _t82 * _t90;
        double _t99 = _t83 * _t90;
        double _t100 = _t84 * _t90;
        double _t106 = Math.fma(_t97, _t97, Math.fma(_t98, _t98, Math.fma(_t99, _t99, _t100 * _t100)));
        double _t108 = (1.0 / java.lang.Math.sqrt(_t106));
        double _t110 = t * Math.atan2(java.lang.Math.sqrt(_t106), _t63 * _t90);
        double _t114 = _t60 * Math.sin(_t110);
        double _t115 = _t60 * Math.cos(_t110);
        double _t120, _t121, _t122, _t123;
        if (_t106 > 0.0) {
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
        if (_t36 * _t37 > 0.0) {
            if (_t63 < 0.0) {
                if (Math.fma(_t81, _t81, Math.fma(_t82, _t82, Math.fma(_t83, _t83, _t84 * _t84))) <= 5.048709793414476E-29) {
                    dest.put(destOffset, Math.fma(_t114, _t50, _t115 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, Math.fma(_t114, _t47, _t115 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, Math.fma(_t114, _t51, _t115 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, Math.fma(_t114, _t45, _t115 * _t43) * _t25_inv);
                } else {
                    dest.put(destOffset, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                    dest.put(destOffset + 1, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                    dest.put(destOffset + 2, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                    dest.put(destOffset + 3, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
                }
            } else {
                dest.put(destOffset, Math.fma(_t114, _t122, _t115 * _t47) * _t25_inv);
                dest.put(destOffset + 1, Math.fma(_t114, _t120, _t115 * _t49) * _t25_inv);
                dest.put(destOffset + 2, Math.fma(_t114, _t123, _t115 * _t45) * _t25_inv);
                dest.put(destOffset + 3, Math.fma(_t114, _t121, _t115 * _t43) * _t25_inv);
            }
        } else {
            dest.put(destOffset, Math.fma(t, _otherx - _selfx, _selfx));
            dest.put(destOffset + 1, Math.fma(t, _othery - _selfy, _selfy));
            dest.put(destOffset + 2, Math.fma(t, _otherz - _selfz, _selfz));
            dest.put(destOffset + 3, Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer absolute_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer absolute_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.absolute(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.absolute_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer absolute_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, java.lang.Math.abs(src.get(srcOffset)));
        dest.put(destOffset + 1, java.lang.Math.abs(_selfy));
        dest.put(destOffset + 2, java.lang.Math.abs(_selfz));
        dest.put(destOffset + 3, java.lang.Math.abs(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer acos_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer acos_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.acos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.acos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer acos_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.acos(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.acos(_selfy));
        dest.put(destOffset + 2, Math.acos(_selfz));
        dest.put(destOffset + 3, Math.acos(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, bW, scalar);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double scalar) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(scalar, bX, src.get(srcOffset)));
        dest.put(destOffset + 1, Math.fma(scalar, bY, _selfy));
        dest.put(destOffset + 2, Math.fma(scalar, bZ, _selfz));
        dest.put(destOffset + 3, Math.fma(scalar, bW, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset * 8L;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 4) {
            Double4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, scalar);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, double scalar) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _by = b.get(bOffset + 1);
        double _bz = b.get(bOffset + 2);
        double _bw = b.get(bOffset + 3);
        dest.put(destOffset, Math.fma(scalar, b.get(bOffset), src.get(srcOffset)));
        dest.put(destOffset + 1, Math.fma(scalar, _by, _selfy));
        dest.put(destOffset + 2, Math.fma(scalar, _bz, _selfz));
        dest.put(destOffset + 3, Math.fma(scalar, _bw, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double bW, double cX, double cY, double cZ, double cW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(bX, cX, src.get(srcOffset)));
        dest.put(destOffset + 1, Math.fma(bY, cY, _selfy));
        dest.put(destOffset + 2, Math.fma(bZ, cZ, _selfz));
        dest.put(destOffset + 3, Math.fma(bW, cW, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset * 8L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset * 8L;
        Double4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 4 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 4) {
            Double4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _by = b.get(bOffset + 1);
        double _bz = b.get(bOffset + 2);
        double _bw = b.get(bOffset + 3);
        double _cy = c.get(cOffset + 1);
        double _cz = c.get(cOffset + 2);
        double _cw = c.get(cOffset + 3);
        dest.put(destOffset, Math.fma(b.get(bOffset), c.get(cOffset), src.get(srcOffset)));
        dest.put(destOffset + 1, Math.fma(_by, _cy, _selfy));
        dest.put(destOffset + 2, Math.fma(_bz, _cz, _selfz));
        dest.put(destOffset + 3, Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    public static double angleBetween_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Double4OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t12 = Math.fma(otherW, _selfz, -(otherZ * _selfw));
        double _t13 = Math.fma(otherW, _selfy, -(otherY * _selfw));
        double _t14 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        double _t15 = Math.fma(otherW, _selfx, -(otherX * _selfw));
        double _t16 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        double _t17 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        double _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    public static double angleBetween_degenerate(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double4OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Double4OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_degenerate_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_degenerate_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Double4OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static double angleBetween_degenerate_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t6 = unitScale(otherZ, otherW, java.lang.Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        double _t7 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = otherW * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = otherZ * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = otherY * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = otherX * _t6;
        double _t23 = _selfy * _t7;
        double _t36 = Math.fma(_t16, _t17, -(_t18 * _t19));
        double _t37 = Math.fma(_t20, _t21, -(_t22 * _t23));
        double _t38 = Math.fma(_t18, _t21, -(_t22 * _t17));
        double _t39 = Math.fma(_t16, _t21, -(_t22 * _t19));
        double _t40 = Math.fma(_t18, _t23, -(_t20 * _t17));
        double _t41 = Math.fma(_t16, _t23, -(_t20 * _t19));
        double _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        return angleBetween_degenerate_apiGet_sd96285f7_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t37, _t38, _t39, _t40, _t51, _t36 * _t51, _t41 * _t51);
    }

    /** Piece 2 of {@code angleBetween_degenerate_apiGet}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_apiGet_sd96285f7_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t37, double _t38, double _t39, double _t40, double _t51, double _t58, double _t59) {
        double _t60 = _t40 * _t51;
        double _t61 = _t39 * _t51;
        double _t62 = _t37 * _t51;
        double _t63 = _t38 * _t51;
        return Math.atan2(java.lang.Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static double angleBetween_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return Double4OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Double4Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Double4OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        double _t12 = Math.fma(_otherw, _selfz, -(_otherz * _selfw));
        double _t13 = Math.fma(_otherw, _selfy, -(_othery * _selfw));
        double _t14 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        double _t15 = Math.fma(_otherw, _selfx, -(_otherx * _selfw));
        double _t16 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        double _t17 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        double _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double4OpsKernelsTypedBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    public static double angleBetween_degenerate(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double4OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Double4OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_degenerate_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return Double4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_degenerate_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Double4OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Double4OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_degenerate_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        double _t6 = unitScale(_otherz, _otherw, java.lang.Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        double _t7 = unitScale(_selfz, _selfw, java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        double _t16 = _otherw * _t6;
        double _t17 = _selfz * _t7;
        double _t18 = _otherz * _t6;
        double _t19 = _selfw * _t7;
        double _t20 = _othery * _t6;
        double _t21 = _selfx * _t7;
        double _t22 = _otherx * _t6;
        double _t23 = _selfy * _t7;
        return angleBetween_degenerate_apiGet_s53f26f46_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, Math.fma(_t16, _t17, -(_t18 * _t19)), Math.fma(_t20, _t21, -(_t22 * _t23)), Math.fma(_t18, _t21, -(_t22 * _t17)), Math.fma(_t16, _t21, -(_t22 * _t19)), Math.fma(_t18, _t23, -(_t20 * _t17)), Math.fma(_t16, _t23, -(_t20 * _t19)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_apiGet}, split to fit the inline budget; reached only through it. */
    private static double angleBetween_degenerate_apiGet_s53f26f46_1(double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t36, double _t37, double _t38, double _t39, double _t40, double _t41) {
        double _t51 = unitScale(java.lang.Math.max(java.lang.Math.abs(_t37), java.lang.Math.abs(_t38)), java.lang.Math.max(java.lang.Math.abs(_t39), java.lang.Math.abs(_t40)), java.lang.Math.max(java.lang.Math.abs(_t41), java.lang.Math.abs(_t36)));
        double _t58 = _t36 * _t51;
        double _t59 = _t41 * _t51;
        double _t60 = _t40 * _t51;
        double _t61 = _t39 * _t51;
        double _t62 = _t37 * _t51;
        double _t63 = _t38 * _t51;
        return Math.atan2(java.lang.Math.sqrt(Math.fma(_t58, _t58, Math.fma(_t59, _t59, Math.fma(_t60, _t60, Math.fma(_t61, _t61, Math.fma(_t62, _t62, _t63 * _t63)))))), Math.fma(_t16, _t19, Math.fma(_t18, _t17, Math.fma(_t22, _t21, _t20 * _t23))) * _t51);
    }

    public static java.nio.DoubleBuffer asin_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer asin_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.asin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.asin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer asin_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.asin(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.asin(_selfy));
        dest.put(destOffset + 2, Math.asin(_selfz));
        dest.put(destOffset + 3, Math.asin(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer atan_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer atan_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.atan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.atan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer atan_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.atan(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.atan(_selfy));
        dest.put(destOffset + 2, Math.atan(_selfz));
        dest.put(destOffset + 3, Math.atan(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double x) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double x) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.atan2(src.get(srcOffset), x));
        dest.put(destOffset + 1, Math.atan2(_selfy, x));
        dest.put(destOffset + 2, Math.atan2(_selfz, x));
        dest.put(destOffset + 3, Math.atan2(_selfw, x));
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double xX, double xY, double xZ, double xW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ, xW);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double xX, double xY, double xZ, double xW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, xX, xY, xZ, xW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, xX, xY, xZ, xW);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double xX, double xY, double xZ, double xW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.atan2(src.get(srcOffset), xX));
        dest.put(destOffset + 1, Math.atan2(_selfy, xY));
        dest.put(destOffset + 2, Math.atan2(_selfz, xZ));
        dest.put(destOffset + 3, Math.atan2(_selfw, xW));
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + xOffset * 8L;
        Double4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer x, int xOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && x.hasArray() && xOffset >= 0 && xOffset <= x.limit() - 4) {
            Double4Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x.array(), x.arrayOffset() + xOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x, xOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer x, int xOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _xy = x.get(xOffset + 1);
        double _xz = x.get(xOffset + 2);
        double _xw = x.get(xOffset + 3);
        dest.put(destOffset, Math.atan2(src.get(srcOffset), x.get(xOffset)));
        dest.put(destOffset + 1, Math.atan2(_selfy, _xy));
        dest.put(destOffset + 2, Math.atan2(_selfz, _xz));
        dest.put(destOffset + 3, Math.atan2(_selfw, _xw));
        return dest;
    }

    public static java.nio.DoubleBuffer cbrt_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cbrt_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.cbrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.cbrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cbrt_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.cbrt(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.cbrt(_selfy));
        dest.put(destOffset + 2, Math.cbrt(_selfz));
        dest.put(destOffset + 3, Math.cbrt(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer ceil_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer ceil_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.ceil(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.ceil_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer ceil_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.ceil(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.ceil(_selfy));
        dest.put(destOffset + 2, Math.ceil(_selfz));
        dest.put(destOffset + 3, Math.ceil(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double min, double max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double min, double max) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min, max);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, max);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double min, double max) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, java.lang.Math.min(java.lang.Math.max(src.get(srcOffset), min), max));
        dest.put(destOffset + 1, java.lang.Math.min(java.lang.Math.max(_selfy, min), max));
        dest.put(destOffset + 2, java.lang.Math.min(java.lang.Math.max(_selfz, min), max));
        dest.put(destOffset + 3, java.lang.Math.min(java.lang.Math.max(_selfw, min), max));
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double minZ, double minW, double maxX, double maxY, double maxZ, double maxW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double minZ, double minW, double maxX, double maxY, double maxZ, double maxW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double minZ, double minW, double maxX, double maxY, double maxZ, double maxW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, java.lang.Math.min(java.lang.Math.max(src.get(srcOffset), minX), maxX));
        dest.put(destOffset + 1, java.lang.Math.min(java.lang.Math.max(_selfy, minY), maxY));
        dest.put(destOffset + 2, java.lang.Math.min(java.lang.Math.max(_selfz, minZ), maxZ));
        dest.put(destOffset + 3, java.lang.Math.min(java.lang.Math.max(_selfw, minW), maxW));
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer min, int minOffset, java.nio.DoubleBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + minOffset * 8L;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + maxOffset * 8L;
        Double4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer min, int minOffset, java.nio.DoubleBuffer max, int maxOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && min.hasArray() && minOffset >= 0 && minOffset <= min.limit() - 4 && max.hasArray() && maxOffset >= 0 && maxOffset <= max.limit() - 4) {
            Double4Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min.array(), min.arrayOffset() + minOffset, max.array(), max.arrayOffset() + maxOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer min, int minOffset, java.nio.DoubleBuffer max, int maxOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _miny = min.get(minOffset + 1);
        double _minz = min.get(minOffset + 2);
        double _minw = min.get(minOffset + 3);
        double _maxy = max.get(maxOffset + 1);
        double _maxz = max.get(maxOffset + 2);
        double _maxw = max.get(maxOffset + 3);
        dest.put(destOffset, java.lang.Math.min(java.lang.Math.max(src.get(srcOffset), min.get(minOffset)), max.get(maxOffset)));
        dest.put(destOffset + 1, java.lang.Math.min(java.lang.Math.max(_selfy, _miny), _maxy));
        dest.put(destOffset + 2, java.lang.Math.min(java.lang.Math.max(_selfz, _minz), _maxz));
        dest.put(destOffset + 3, java.lang.Math.min(java.lang.Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static double compAdd_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static double compAdd_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4Ops.compAdd(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double4OpsKernelsTypedBuffer.compAdd_apiGet(src, srcOffset);
    }

    public static double compAdd_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        return src.get(srcOffset + 3) + (src.get(srcOffset + 2) + (src.get(srcOffset) + src.get(srcOffset + 1)));
    }

    public static double compMax_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static double compMax_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4Ops.compMax(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double4OpsKernelsTypedBuffer.compMax_apiGet(src, srcOffset);
    }

    public static double compMax_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(src.get(srcOffset), src.get(srcOffset + 1)), src.get(srcOffset + 2)), src.get(srcOffset + 3));
    }

    public static double compMin_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static double compMin_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4Ops.compMin(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double4OpsKernelsTypedBuffer.compMin_apiGet(src, srcOffset);
    }

    public static double compMin_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(src.get(srcOffset), src.get(srcOffset + 1)), src.get(srcOffset + 2)), src.get(srcOffset + 3));
    }

    public static double compMul_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static double compMul_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4Ops.compMul(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double4OpsKernelsTypedBuffer.compMul_apiGet(src, srcOffset);
    }

    public static double compMul_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        return src.get(srcOffset + 3) * src.get(srcOffset + 2) * src.get(srcOffset) * src.get(srcOffset + 1);
    }

    public static java.nio.DoubleBuffer copySign_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sign) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sign) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.copySign(src.get(srcOffset), sign));
        dest.put(destOffset + 1, Math.copySign(_selfy, sign));
        dest.put(destOffset + 2, Math.copySign(_selfz, sign));
        dest.put(destOffset + 3, Math.copySign(_selfw, sign));
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double signX, double signY, double signZ, double signW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ, signW);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double signX, double signY, double signZ, double signW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, signX, signY, signZ, signW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, signX, signY, signZ, signW);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double signX, double signY, double signZ, double signW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.copySign(src.get(srcOffset), signX));
        dest.put(destOffset + 1, Math.copySign(_selfy, signY));
        dest.put(destOffset + 2, Math.copySign(_selfz, signZ));
        dest.put(destOffset + 3, Math.copySign(_selfw, signW));
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + signOffset * 8L;
        Double4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer sign, int signOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && sign.hasArray() && signOffset >= 0 && signOffset <= sign.limit() - 4) {
            Double4Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign.array(), sign.arrayOffset() + signOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign, signOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer sign, int signOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _signy = sign.get(signOffset + 1);
        double _signz = sign.get(signOffset + 2);
        double _signw = sign.get(signOffset + 3);
        dest.put(destOffset, Math.copySign(src.get(srcOffset), sign.get(signOffset)));
        dest.put(destOffset + 1, Math.copySign(_selfy, _signy));
        dest.put(destOffset + 2, Math.copySign(_selfz, _signz));
        dest.put(destOffset + 3, Math.copySign(_selfw, _signw));
        return dest;
    }

    public static java.nio.DoubleBuffer cos_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cos_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.cos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.cos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cos_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.cos(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.cos(_selfy));
        dest.put(destOffset + 2, Math.cos(_selfz));
        dest.put(destOffset + 3, Math.cos(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer cosh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cosh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.cosh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.cosh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cosh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.cosh(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.cosh(_selfy));
        dest.put(destOffset + 2, Math.cosh(_selfz));
        dest.put(destOffset + 3, Math.cosh(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer cross_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    public static java.nio.DoubleBuffer cross_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.cross(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.cross_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    public static java.nio.DoubleBuffer cross_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ, double vW, double wX, double wY, double wZ, double wW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t12 = Math.fma(vY, wZ, -(vZ * wY));
        double _t13 = Math.fma(vZ, wW, -(vW * wZ));
        double _t14 = Math.fma(vY, wW, -(vW * wY));
        double _t15 = Math.fma(vX, wZ, -(vZ * wX));
        double _t16 = Math.fma(vX, wW, -(vW * wX));
        double _t17 = Math.fma(vX, wY, -(vY * wX));
        dest.put(destOffset, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        dest.put(destOffset + 1, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        dest.put(destOffset + 2, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        dest.put(destOffset + 3, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static java.nio.DoubleBuffer cross_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset, java.nio.DoubleBuffer w, int wOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        long _wBase = UnsafeOpsHolder.U.getLong(w, UnsafeCopy.BB_ADDRESS_OFFSET) + wOffset * 8L;
        Double4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _vBase, _wBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cross_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset, java.nio.DoubleBuffer w, int wOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4 && w.hasArray() && wOffset >= 0 && wOffset <= w.limit() - 4) {
            Double4Ops.cross(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset, w.array(), w.arrayOffset() + wOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.cross_apiGet(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cross_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset, java.nio.DoubleBuffer w, int wOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        double _vw = v.get(vOffset + 3);
        double _wx = w.get(wOffset);
        double _wy = w.get(wOffset + 1);
        double _wz = w.get(wOffset + 2);
        double _ww = w.get(wOffset + 3);
        double _t12 = Math.fma(_vy, _wz, -(_vz * _wy));
        double _t13 = Math.fma(_vz, _ww, -(_vw * _wz));
        double _t14 = Math.fma(_vy, _ww, -(_vw * _wy));
        double _t15 = Math.fma(_vx, _wz, -(_vz * _wx));
        double _t16 = Math.fma(_vx, _ww, -(_vw * _wx));
        dest.put(destOffset, Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14))));
        dest.put(destOffset + 1, Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13))));
        return cross_apiGet_s7aed8f78_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t12, _t14, _t15, _t16, Math.fma(_vx, _wy, -(_vy * _wx)));
    }

    /** Piece 2 of {@code cross_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer cross_apiGet_s7aed8f78_1(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t12, double _t14, double _t15, double _t16, double _t17) {
        dest.put(destOffset + 2, Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16))));
        dest.put(destOffset + 3, Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12))));
        return dest;
    }

    public static java.nio.DoubleBuffer degrees_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer degrees_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.degrees(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.degrees_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer degrees_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.toDegrees(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.toDegrees(_selfy));
        dest.put(destOffset + 2, Math.toDegrees(_selfz));
        dest.put(destOffset + 3, Math.toDegrees(_selfw));
        return dest;
    }

    public static double distance_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double distance_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4Ops.distance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Double4OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static double distance_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _t0 = src.get(srcOffset + 3) - otherW;
        double _t1 = src.get(srcOffset + 2) - otherZ;
        double _t2 = src.get(srcOffset) - otherX;
        double _t3 = src.get(srcOffset + 1) - otherY;
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static double distance_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return Double4OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static double distance_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Double4Ops.distance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Double4OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double distance_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _t0 = src.get(srcOffset + 3) - other.get(otherOffset + 3);
        double _t1 = src.get(srcOffset + 2) - other.get(otherOffset + 2);
        double _t2 = src.get(srcOffset) - other.get(otherOffset);
        double _t3 = src.get(srcOffset + 1) - other.get(otherOffset + 1);
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    public static double distanceSquared_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double distanceSquared_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Double4OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static double distanceSquared_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _t0 = src.get(srcOffset + 3) - otherW;
        double _t1 = src.get(srcOffset + 2) - otherZ;
        double _t2 = src.get(srcOffset) - otherX;
        double _t3 = src.get(srcOffset + 1) - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static double distanceSquared_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return Double4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static double distanceSquared_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Double4Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Double4OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double distanceSquared_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _t0 = src.get(srcOffset + 3) - other.get(otherOffset + 3);
        double _t1 = src.get(srcOffset + 2) - other.get(otherOffset + 2);
        double _t2 = src.get(srcOffset) - other.get(otherOffset);
        double _t3 = src.get(srcOffset + 1) - other.get(otherOffset + 1);
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    public static double dot_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double dot_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4Ops.dot(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Double4OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static double dot_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        return Math.fma(otherW, src.get(srcOffset + 3), Math.fma(otherZ, src.get(srcOffset + 2), Math.fma(otherX, src.get(srcOffset), otherY * src.get(srcOffset + 1))));
    }

    public static double dot_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return Double4OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static double dot_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Double4Ops.dot(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Double4OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double dot_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        return Math.fma(other.get(otherOffset + 3), src.get(srcOffset + 3), Math.fma(other.get(otherOffset + 2), src.get(srcOffset + 2), Math.fma(other.get(otherOffset), src.get(srcOffset), other.get(otherOffset + 1) * src.get(srcOffset + 1))));
    }

    public static java.nio.DoubleBuffer exp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer exp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.exp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.exp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer exp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.exp(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.exp(_selfy));
        dest.put(destOffset + 2, Math.exp(_selfz));
        dest.put(destOffset + 3, Math.exp(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer exp2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer exp2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.exp2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.exp2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer exp2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.pow(2.0, src.get(srcOffset)));
        dest.put(destOffset + 1, Math.pow(2.0, _selfy));
        dest.put(destOffset + 2, Math.pow(2.0, _selfz));
        dest.put(destOffset + 3, Math.pow(2.0, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer expm1_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer expm1_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.expm1(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.expm1_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer expm1_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.expm1(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.expm1(_selfy));
        dest.put(destOffset + 2, Math.expm1(_selfz));
        dest.put(destOffset + 3, Math.expm1(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double IX, double IY, double IZ, double IW, double NrefX, double NrefY, double NrefZ, double NrefW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t4 = Math.fma(IW, NrefW, Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY))) < 0.0 ? 1.0 : -1.0;
        dest.put(destOffset, src.get(srcOffset) * _t4);
        dest.put(destOffset + 1, _selfy * _t4);
        dest.put(destOffset + 2, _selfz * _t4);
        dest.put(destOffset + 3, _selfw * _t4);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer I, int IOffset, java.nio.DoubleBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + IOffset * 8L;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + NrefOffset * 8L;
        Double4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer I, int IOffset, java.nio.DoubleBuffer Nref, int NrefOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && I.hasArray() && IOffset >= 0 && IOffset <= I.limit() - 4 && Nref.hasArray() && NrefOffset >= 0 && NrefOffset <= Nref.limit() - 4) {
            Double4Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, I.array(), I.arrayOffset() + IOffset, Nref.array(), Nref.arrayOffset() + NrefOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer I, int IOffset, java.nio.DoubleBuffer Nref, int NrefOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t4 = Math.fma(I.get(IOffset + 3), Nref.get(NrefOffset + 3), Math.fma(I.get(IOffset + 2), Nref.get(NrefOffset + 2), Math.fma(I.get(IOffset), Nref.get(NrefOffset), I.get(IOffset + 1) * Nref.get(NrefOffset + 1)))) < 0.0 ? 1.0 : -1.0;
        dest.put(destOffset, src.get(srcOffset) * _t4);
        dest.put(destOffset + 1, _selfy * _t4);
        dest.put(destOffset + 2, _selfz * _t4);
        dest.put(destOffset + 3, _selfw * _t4);
        return dest;
    }

    public static java.nio.DoubleBuffer floor_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer floor_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.floor(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.floor_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer floor_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.floor(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.floor(_selfy));
        dest.put(destOffset + 2, Math.floor(_selfz));
        dest.put(destOffset + 3, Math.floor(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer fract_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer fract_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.fract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.fract_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer fract_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, java.lang.Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        dest.put(destOffset + 1, java.lang.Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        dest.put(destOffset + 2, java.lang.Math.min(_selfz - Math.floor(_selfz), 0.9999999999999999));
        dest.put(destOffset + 3, java.lang.Math.min(_selfw - Math.floor(_selfw), 0.9999999999999999));
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.hypot(src.get(srcOffset), y));
        dest.put(destOffset + 1, Math.hypot(_selfy, y));
        dest.put(destOffset + 2, Math.hypot(_selfz, y));
        dest.put(destOffset + 3, Math.hypot(_selfw, y));
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ, double yW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ, double yW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY, yZ, yW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ, double yW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.hypot(src.get(srcOffset), yX));
        dest.put(destOffset + 1, Math.hypot(_selfy, yY));
        dest.put(destOffset + 2, Math.hypot(_selfz, yZ));
        dest.put(destOffset + 3, Math.hypot(_selfw, yW));
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset * 8L;
        Double4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 4) {
            Double4Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _yy = y.get(yOffset + 1);
        double _yz = y.get(yOffset + 2);
        double _yw = y.get(yOffset + 3);
        dest.put(destOffset, Math.hypot(src.get(srcOffset), y.get(yOffset)));
        dest.put(destOffset + 1, Math.hypot(_selfy, _yy));
        dest.put(destOffset + 2, Math.hypot(_selfz, _yz));
        dest.put(destOffset + 3, Math.hypot(_selfw, _yw));
        return dest;
    }

    public static java.nio.DoubleBuffer inverse_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer inverse_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.inverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.inverse_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer inverse_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, 1.0 / src.get(srcOffset));
        dest.put(destOffset + 1, 1.0 / _selfy);
        dest.put(destOffset + 2, 1.0 / _selfz);
        dest.put(destOffset + 3, 1.0 / _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer inverseSqrt_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer inverseSqrt_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.inverseSqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.inverseSqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer inverseSqrt_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, (1.0 / java.lang.Math.sqrt(src.get(srcOffset))));
        dest.put(destOffset + 1, (1.0 / java.lang.Math.sqrt(_selfy)));
        dest.put(destOffset + 2, (1.0 / java.lang.Math.sqrt(_selfz)));
        dest.put(destOffset + 3, (1.0 / java.lang.Math.sqrt(_selfw)));
        return dest;
    }

    public static double length_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static double length_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4Ops.length(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double4OpsKernelsTypedBuffer.length_apiGet(src, srcOffset);
    }

    public static double length_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        return java.lang.Math.sqrt(Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))));
    }

    public static double lengthSquared_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static double lengthSquared_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4Ops.lengthSquared(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double4OpsKernelsTypedBuffer.lengthSquared_apiGet(src, srcOffset);
    }

    public static double lengthSquared_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        return Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static java.nio.DoubleBuffer log_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.log(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.log_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.log(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log(_selfy));
        dest.put(destOffset + 2, Math.log(_selfz));
        dest.put(destOffset + 3, Math.log(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer log10_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log10_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.log10(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.log10_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log10_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.log10(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log10(_selfy));
        dest.put(destOffset + 2, Math.log10(_selfz));
        dest.put(destOffset + 3, Math.log10(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer log1p_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log1p_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.log1p(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.log1p_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log1p_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.log1p(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log1p(_selfy));
        dest.put(destOffset + 2, Math.log1p(_selfz));
        dest.put(destOffset + 3, Math.log1p(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer log2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.log2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.log2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.log2(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log2(_selfy));
        dest.put(destOffset + 2, Math.log2(_selfz));
        dest.put(destOffset + 3, Math.log2(_selfw));
        return dest;
    }

    public static double manhattanDistance_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double manhattanDistance_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return Double4OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static double manhattanDistance_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        return java.lang.Math.abs(src.get(srcOffset) - otherX) + java.lang.Math.abs(src.get(srcOffset + 1) - otherY) + java.lang.Math.abs(src.get(srcOffset + 2) - otherZ) + java.lang.Math.abs(src.get(srcOffset + 3) - otherW);
    }

    public static double manhattanDistance_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return Double4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static double manhattanDistance_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return Double4Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Double4OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double manhattanDistance_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        return java.lang.Math.abs(src.get(srcOffset) - other.get(otherOffset)) + java.lang.Math.abs(src.get(srcOffset + 1) - other.get(otherOffset + 1)) + java.lang.Math.abs(src.get(srcOffset + 2) - other.get(otherOffset + 2)) + java.lang.Math.abs(src.get(srcOffset + 3) - other.get(otherOffset + 3));
    }

    public static double manhattanLength_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double4OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static double manhattanLength_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Double4Ops.manhattanLength(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double4OpsKernelsTypedBuffer.manhattanLength_apiGet(src, srcOffset);
    }

    public static double manhattanLength_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        return java.lang.Math.abs(src.get(srcOffset)) + java.lang.Math.abs(src.get(srcOffset + 1)) + java.lang.Math.abs(src.get(srcOffset + 2)) + java.lang.Math.abs(src.get(srcOffset + 3));
    }

    public static java.nio.DoubleBuffer max_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer max_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer max_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, java.lang.Math.max(src.get(srcOffset), scalar));
        dest.put(destOffset + 1, java.lang.Math.max(_selfy, scalar));
        dest.put(destOffset + 2, java.lang.Math.max(_selfz, scalar));
        dest.put(destOffset + 3, java.lang.Math.max(_selfw, scalar));
        return dest;
    }

    public static java.nio.DoubleBuffer max_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer max_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer max_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, java.lang.Math.max(src.get(srcOffset), otherX));
        dest.put(destOffset + 1, java.lang.Math.max(_selfy, otherY));
        dest.put(destOffset + 2, java.lang.Math.max(_selfz, otherZ));
        dest.put(destOffset + 3, java.lang.Math.max(_selfw, otherW));
        return dest;
    }

    public static java.nio.DoubleBuffer max_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer max_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double4Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer max_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, java.lang.Math.max(src.get(srcOffset), other.get(otherOffset)));
        dest.put(destOffset + 1, java.lang.Math.max(_selfy, _othery));
        dest.put(destOffset + 2, java.lang.Math.max(_selfz, _otherz));
        dest.put(destOffset + 3, java.lang.Math.max(_selfw, _otherw));
        return dest;
    }

    public static java.nio.DoubleBuffer min_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer min_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer min_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, java.lang.Math.min(src.get(srcOffset), scalar));
        dest.put(destOffset + 1, java.lang.Math.min(_selfy, scalar));
        dest.put(destOffset + 2, java.lang.Math.min(_selfz, scalar));
        dest.put(destOffset + 3, java.lang.Math.min(_selfw, scalar));
        return dest;
    }

    public static java.nio.DoubleBuffer min_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer min_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer min_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, java.lang.Math.min(src.get(srcOffset), otherX));
        dest.put(destOffset + 1, java.lang.Math.min(_selfy, otherY));
        dest.put(destOffset + 2, java.lang.Math.min(_selfz, otherZ));
        dest.put(destOffset + 3, java.lang.Math.min(_selfw, otherW));
        return dest;
    }

    public static java.nio.DoubleBuffer min_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer min_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double4Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer min_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, java.lang.Math.min(src.get(srcOffset), other.get(otherOffset)));
        dest.put(destOffset + 1, java.lang.Math.min(_selfy, _othery));
        dest.put(destOffset + 2, java.lang.Math.min(_selfz, _otherz));
        dest.put(destOffset + 3, java.lang.Math.min(_selfw, _otherw));
        return dest;
    }

    public static java.nio.DoubleBuffer mod_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, flooredMod(_selfx, y));
        dest.put(destOffset + 1, flooredMod(_selfy, y));
        dest.put(destOffset + 2, flooredMod(_selfz, y));
        dest.put(destOffset + 3, flooredMod(_selfw, y));
        return dest;
    }

    public static java.nio.DoubleBuffer mod_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ, double yW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ, double yW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY, yZ, yW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ, double yW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, flooredMod(_selfx, yX));
        dest.put(destOffset + 1, flooredMod(_selfy, yY));
        dest.put(destOffset + 2, flooredMod(_selfz, yZ));
        dest.put(destOffset + 3, flooredMod(_selfw, yW));
        return dest;
    }

    public static java.nio.DoubleBuffer mod_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset * 8L;
        Double4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 4) {
            Double4Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _yx = y.get(yOffset);
        double _yy = y.get(yOffset + 1);
        double _yz = y.get(yOffset + 2);
        double _yw = y.get(yOffset + 3);
        dest.put(destOffset, flooredMod(_selfx, _yx));
        dest.put(destOffset + 1, flooredMod(_selfy, _yy));
        dest.put(destOffset + 2, flooredMod(_selfz, _yz));
        dest.put(destOffset + 3, flooredMod(_selfw, _yw));
        return dest;
    }

    public static java.nio.DoubleBuffer nextDown_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer nextDown_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.nextDown(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.nextDown_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer nextDown_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.nextDown(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.nextDown(_selfy));
        dest.put(destOffset + 2, Math.nextDown(_selfz));
        dest.put(destOffset + 3, Math.nextDown(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer nextUp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer nextUp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.nextUp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.nextUp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer nextUp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.nextUp(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.nextUp(_selfy));
        dest.put(destOffset + 2, Math.nextUp(_selfz));
        dest.put(destOffset + 3, Math.nextUp(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer normalize_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalize_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.normalize(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.normalize_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalize_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _t4 = (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            dest.put(destOffset, _selfx * _t4);
            dest.put(destOffset + 1, _selfy * _t4);
            dest.put(destOffset + 2, _selfz * _t4);
            dest.put(destOffset + 3, _selfw * _t4);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer normalizeMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizeMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double length) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.normalizeMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, length);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.normalizeMul_apiGet(dest, destOffset, src, srcOffset, length);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizeMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double length) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _t5 = length * (1.0 / java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0) {
            dest.put(destOffset, _selfx * _t5);
            dest.put(destOffset + 1, _selfy * _t5);
            dest.put(destOffset + 2, _selfz * _t5);
            dest.put(destOffset + 3, _selfw * _t5);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rowX, double rowY, double rowZ, double rowW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ, rowW);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rowX, double rowY, double rowZ, double rowW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rowX, rowY, rowZ, rowW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, rowX, rowY, rowZ, rowW);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rowX, double rowY, double rowZ, double rowW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, rowX * _selfx);
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

    public static java.nio.DoubleBuffer outerProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset * 8L;
        Double4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer row, int rowOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && row.hasArray() && rowOffset >= 0 && rowOffset <= row.limit() - 4) {
            Double4Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row.array(), row.arrayOffset() + rowOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, row, rowOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer row, int rowOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _rowx = row.get(rowOffset);
        double _rowy = row.get(rowOffset + 1);
        double _rowz = row.get(rowOffset + 2);
        double _roww = row.get(rowOffset + 3);
        dest.put(destOffset, _rowx * _selfx);
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

    public static java.nio.DoubleBuffer pow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponent) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponent) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.pow(src.get(srcOffset), exponent));
        dest.put(destOffset + 1, Math.pow(_selfy, exponent));
        dest.put(destOffset + 2, Math.pow(_selfz, exponent));
        dest.put(destOffset + 3, Math.pow(_selfw, exponent));
        return dest;
    }

    public static java.nio.DoubleBuffer pow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponentX, double exponentY, double exponentZ, double exponentW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponentX, double exponentY, double exponentZ, double exponentW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponentX, exponentY, exponentZ, exponentW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponentX, double exponentY, double exponentZ, double exponentW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.pow(src.get(srcOffset), exponentX));
        dest.put(destOffset + 1, Math.pow(_selfy, exponentY));
        dest.put(destOffset + 2, Math.pow(_selfz, exponentZ));
        dest.put(destOffset + 3, Math.pow(_selfw, exponentW));
        return dest;
    }

    public static java.nio.DoubleBuffer pow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + exponentOffset * 8L;
        Double4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer exponent, int exponentOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && exponent.hasArray() && exponentOffset >= 0 && exponentOffset <= exponent.limit() - 4) {
            Double4Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent.array(), exponent.arrayOffset() + exponentOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer exponent, int exponentOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _exponenty = exponent.get(exponentOffset + 1);
        double _exponentz = exponent.get(exponentOffset + 2);
        double _exponentw = exponent.get(exponentOffset + 3);
        dest.put(destOffset, Math.pow(src.get(srcOffset), exponent.get(exponentOffset)));
        dest.put(destOffset + 1, Math.pow(_selfy, _exponenty));
        dest.put(destOffset + 2, Math.pow(_selfz, _exponentz));
        dest.put(destOffset + 3, Math.pow(_selfw, _exponentw));
        return dest;
    }

    public static java.nio.DoubleBuffer project_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double ontoX, double ontoY, double ontoZ, double ontoW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    public static java.nio.DoubleBuffer project_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double ontoX, double ontoY, double ontoZ, double ontoW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, ontoX, ontoY, ontoZ, ontoW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    public static java.nio.DoubleBuffer project_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double ontoX, double ontoY, double ontoZ, double ontoW) {
        double _t9 = Math.fma(ontoW, src.get(srcOffset + 3), Math.fma(ontoZ, src.get(srcOffset + 2), Math.fma(ontoX, src.get(srcOffset), ontoY * src.get(srcOffset + 1)))) / Math.fma(ontoW, ontoW, Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY)));
        dest.put(destOffset, ontoX * _t9);
        dest.put(destOffset + 1, ontoY * _t9);
        dest.put(destOffset + 2, ontoZ * _t9);
        dest.put(destOffset + 3, ontoW * _t9);
        return dest;
    }

    public static java.nio.DoubleBuffer project_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + ontoOffset * 8L;
        Double4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.DoubleBuffer project_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer onto, int ontoOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && onto.hasArray() && ontoOffset >= 0 && ontoOffset <= onto.limit() - 4) {
            Double4Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, onto.array(), onto.arrayOffset() + ontoOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer project_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer onto, int ontoOffset) {
        double _ontox = onto.get(ontoOffset);
        double _ontoy = onto.get(ontoOffset + 1);
        double _ontoz = onto.get(ontoOffset + 2);
        double _ontow = onto.get(ontoOffset + 3);
        double _t9 = Math.fma(_ontow, src.get(srcOffset + 3), Math.fma(_ontoz, src.get(srcOffset + 2), Math.fma(_ontox, src.get(srcOffset), _ontoy * src.get(srcOffset + 1)))) / Math.fma(_ontow, _ontow, Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        dest.put(destOffset, _ontox * _t9);
        dest.put(destOffset + 1, _ontoy * _t9);
        dest.put(destOffset + 2, _ontoz * _t9);
        dest.put(destOffset + 3, _ontow * _t9);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ, normalW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t3 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.put(destOffset, Math.fma(-normalX, _t3, _selfx));
        dest.put(destOffset + 1, Math.fma(-normalY, _t3, _selfy));
        dest.put(destOffset + 2, Math.fma(-normalZ, _t3, _selfz));
        dest.put(destOffset + 3, Math.fma(-normalW, _t3, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 8L;
        Double4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 4) {
            Double4Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _normalx = normal.get(normalOffset);
        double _normaly = normal.get(normalOffset + 1);
        double _normalz = normal.get(normalOffset + 2);
        double _normalw = normal.get(normalOffset + 3);
        double _t3 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.put(destOffset, Math.fma(-_normalx, _t3, _selfx));
        dest.put(destOffset + 1, Math.fma(-_normaly, _t3, _selfy));
        dest.put(destOffset + 2, Math.fma(-_normalz, _t3, _selfz));
        dest.put(destOffset + 3, Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer radians_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer radians_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.radians(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.radians_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer radians_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.toRadians(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.toRadians(_selfy));
        dest.put(destOffset + 2, Math.toRadians(_selfz));
        dest.put(destOffset + 3, Math.toRadians(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ, normalW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t4 = 2.0 * Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        dest.put(destOffset, Math.fma(-normalX, _t4, _selfx));
        dest.put(destOffset + 1, Math.fma(-normalY, _t4, _selfy));
        dest.put(destOffset + 2, Math.fma(-normalZ, _t4, _selfz));
        dest.put(destOffset + 3, Math.fma(-normalW, _t4, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 8L;
        Double4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 4) {
            Double4Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _normalx = normal.get(normalOffset);
        double _normaly = normal.get(normalOffset + 1);
        double _normalz = normal.get(normalOffset + 2);
        double _normalw = normal.get(normalOffset + 3);
        double _t4 = 2.0 * Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.put(destOffset, Math.fma(-_normalx, _t4, _selfx));
        dest.put(destOffset + 1, Math.fma(-_normaly, _t4, _selfy));
        dest.put(destOffset + 2, Math.fma(-_normalz, _t4, _selfz));
        dest.put(destOffset + 3, Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer refract_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    public static java.nio.DoubleBuffer refract_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ, normalW, eta);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    public static java.nio.DoubleBuffer refract_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double normalW, double eta) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t4 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        double _t11 = Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            dest.put(destOffset, Math.fma(eta, _selfx, -(normalX * _t11)));
            dest.put(destOffset + 1, Math.fma(eta, _selfy, -(normalY * _t11)));
            dest.put(destOffset + 2, Math.fma(eta, _selfz, -(normalZ * _t11)));
            dest.put(destOffset + 3, Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer refract_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset, double eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 8L;
        Double4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.DoubleBuffer refract_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset, double eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 4) {
            Double4Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset, eta);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return dest;
    }

    public static java.nio.DoubleBuffer refract_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset, double eta) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _normalx = normal.get(normalOffset);
        double _normaly = normal.get(normalOffset + 1);
        double _normalz = normal.get(normalOffset + 2);
        double _normalw = normal.get(normalOffset + 3);
        double _t4 = Math.fma(_normalw, _selfw, Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy)));
        double _t8 = Math.fma(-Math.fma(-_t4, _t4, 1.0), eta * eta, 1.0);
        double _t11 = Math.fma(eta, _t4, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t8)));
        if (_t8 >= 0.0) {
            dest.put(destOffset, Math.fma(eta, _selfx, -(_normalx * _t11)));
            dest.put(destOffset + 1, Math.fma(eta, _selfy, -(_normaly * _t11)));
            dest.put(destOffset + 2, Math.fma(eta, _selfz, -(_normalz * _t11)));
            dest.put(destOffset + 3, Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer round_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer round_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.round(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.round_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer round_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.rint(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.rint(_selfy));
        dest.put(destOffset + 2, Math.rint(_selfz));
        dest.put(destOffset + 3, Math.rint(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer sign_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sign_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.sign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.sign_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sign_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.signum(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.signum(_selfy));
        dest.put(destOffset + 2, Math.signum(_selfz));
        dest.put(destOffset + 3, Math.signum(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer sin_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sin_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.sin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.sin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sin_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.sin(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.sin(_selfy));
        dest.put(destOffset + 2, Math.sin(_selfz));
        dest.put(destOffset + 3, Math.sin(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer sinh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sinh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.sinh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.sinh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sinh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.sinh(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.sinh(_selfy));
        dest.put(destOffset + 2, Math.sinh(_selfz));
        dest.put(destOffset + 3, Math.sinh(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0, double edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0, double edge1) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0, edge1);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge1);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0, double edge1) {
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t13 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(srcOffset) - edge0) * _t0_inv));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(srcOffset + 1) - edge0) * _t0_inv));
        double _t15 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(srcOffset + 2) - edge0) * _t0_inv));
        double _t16 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(srcOffset + 3) - edge0) * _t0_inv));
        dest.put(destOffset, Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        dest.put(destOffset + 1, Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        dest.put(destOffset + 2, Math.fma(-2.0, _t15, 3.0) * _t15 * _t15);
        dest.put(destOffset + 3, Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge0W, double edge1X, double edge1Y, double edge1Z, double edge1W) {
        double _t16 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(srcOffset) - edge0X) / (edge1X - edge0X)));
        double _t17 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(srcOffset + 1) - edge0Y) / (edge1Y - edge0Y)));
        double _t18 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(srcOffset + 2) - edge0Z) / (edge1Z - edge0Z)));
        double _t19 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(srcOffset + 3) - edge0W) / (edge1W - edge0W)));
        dest.put(destOffset, Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        dest.put(destOffset + 1, Math.fma(-2.0, _t17, 3.0) * _t17 * _t17);
        dest.put(destOffset + 2, Math.fma(-2.0, _t18, 3.0) * _t18 * _t18);
        dest.put(destOffset + 3, Math.fma(-2.0, _t19, 3.0) * _t19 * _t19);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge0, int edge0Offset, java.nio.DoubleBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + edge0Offset * 8L;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + edge1Offset * 8L;
        Double4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge0, int edge0Offset, java.nio.DoubleBuffer edge1, int edge1Offset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && edge0.hasArray() && edge0Offset >= 0 && edge0Offset <= edge0.limit() - 4 && edge1.hasArray() && edge1Offset >= 0 && edge1Offset <= edge1.limit() - 4) {
            Double4Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0.array(), edge0.arrayOffset() + edge0Offset, edge1.array(), edge1.arrayOffset() + edge1Offset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge0, int edge0Offset, java.nio.DoubleBuffer edge1, int edge1Offset) {
        double _edge0x = edge0.get(edge0Offset);
        double _edge0y = edge0.get(edge0Offset + 1);
        double _edge0z = edge0.get(edge0Offset + 2);
        double _edge0w = edge0.get(edge0Offset + 3);
        double _t16 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(srcOffset) - _edge0x) / (edge1.get(edge1Offset) - _edge0x)));
        double _t17 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(srcOffset + 1) - _edge0y) / (edge1.get(edge1Offset + 1) - _edge0y)));
        double _t18 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(srcOffset + 2) - _edge0z) / (edge1.get(edge1Offset + 2) - _edge0z)));
        double _t19 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src.get(srcOffset + 3) - _edge0w) / (edge1.get(edge1Offset + 3) - _edge0w)));
        dest.put(destOffset, Math.fma(-2.0, _t16, 3.0) * _t16 * _t16);
        dest.put(destOffset + 1, Math.fma(-2.0, _t17, 3.0) * _t17 * _t17);
        dest.put(destOffset + 2, Math.fma(-2.0, _t18, 3.0) * _t18 * _t18);
        dest.put(destOffset + 3, Math.fma(-2.0, _t19, 3.0) * _t19 * _t19);
        return dest;
    }

    public static java.nio.DoubleBuffer sqrt_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sqrt_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.sqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.sqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sqrt_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, java.lang.Math.sqrt(src.get(srcOffset)));
        dest.put(destOffset + 1, java.lang.Math.sqrt(_selfy));
        dest.put(destOffset + 2, java.lang.Math.sqrt(_selfz));
        dest.put(destOffset + 3, java.lang.Math.sqrt(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer step_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.DoubleBuffer step_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge);
        return dest;
    }

    public static java.nio.DoubleBuffer step_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) < edge ? 0.0 : 1.0);
        dest.put(destOffset + 1, _selfy < edge ? 0.0 : 1.0);
        dest.put(destOffset + 2, _selfz < edge ? 0.0 : 1.0);
        dest.put(destOffset + 3, _selfw < edge ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer step_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edgeX, double edgeY, double edgeZ, double edgeW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    public static java.nio.DoubleBuffer step_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edgeX, double edgeY, double edgeZ, double edgeW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edgeX, edgeY, edgeZ, edgeW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    public static java.nio.DoubleBuffer step_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edgeX, double edgeY, double edgeZ, double edgeW) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) < edgeX ? 0.0 : 1.0);
        dest.put(destOffset + 1, _selfy < edgeY ? 0.0 : 1.0);
        dest.put(destOffset + 2, _selfz < edgeZ ? 0.0 : 1.0);
        dest.put(destOffset + 3, _selfw < edgeW ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer step_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + edgeOffset * 8L;
        Double4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.DoubleBuffer step_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge, int edgeOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && edge.hasArray() && edgeOffset >= 0 && edgeOffset <= edge.limit() - 4) {
            Double4Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge.array(), edge.arrayOffset() + edgeOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer step_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge, int edgeOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _edgey = edge.get(edgeOffset + 1);
        double _edgez = edge.get(edgeOffset + 2);
        double _edgew = edge.get(edgeOffset + 3);
        dest.put(destOffset, src.get(srcOffset) < edge.get(edgeOffset) ? 0.0 : 1.0);
        dest.put(destOffset + 1, _selfy < _edgey ? 0.0 : 1.0);
        dest.put(destOffset + 2, _selfz < _edgez ? 0.0 : 1.0);
        dest.put(destOffset + 3, _selfw < _edgew ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer tan_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer tan_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.tan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.tan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer tan_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.tan(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.tan(_selfy));
        dest.put(destOffset + 2, Math.tan(_selfz));
        dest.put(destOffset + 3, Math.tan(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer tanh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer tanh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.tanh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.tanh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer tanh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.tanh(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.tanh(_selfy));
        dest.put(destOffset + 2, Math.tanh(_selfz));
        dest.put(destOffset + 3, Math.tanh(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer trunc_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer trunc_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.trunc(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.trunc_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer trunc_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.put(destOffset + 1, _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.put(destOffset + 2, _selfz >= 0.0 ? Math.floor(_selfz) : Math.ceil(_selfz));
        dest.put(destOffset + 3, _selfw >= 0.0 ? Math.floor(_selfw) : Math.ceil(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer ulp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer ulp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.ulp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.ulp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer ulp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.ulp(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.ulp(_selfy));
        dest.put(destOffset + 2, Math.ulp(_selfz));
        dest.put(destOffset + 3, Math.ulp(_selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 8L;
        Double4OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 16) {
            Double4Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _mat10 = mat.get(matOffset + 1);
        double _mat20 = mat.get(matOffset + 2);
        double _mat30 = mat.get(matOffset + 3);
        double _mat11 = mat.get(matOffset + 5);
        double _mat21 = mat.get(matOffset + 6);
        double _mat31 = mat.get(matOffset + 7);
        double _mat12 = mat.get(matOffset + 9);
        double _mat22 = mat.get(matOffset + 10);
        double _mat32 = mat.get(matOffset + 11);
        double _mat13 = mat.get(matOffset + 13);
        double _mat23 = mat.get(matOffset + 14);
        double _mat33 = mat.get(matOffset + 15);
        dest.put(destOffset, Math.fma(mat.get(matOffset + 12), _selfw, Math.fma(mat.get(matOffset + 8), _selfz, Math.fma(mat.get(matOffset), _selfx, mat.get(matOffset + 4) * _selfy))));
        dest.put(destOffset + 1, Math.fma(_mat13, _selfw, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        return preMul_apiGet_sd8714568_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _mat20, _mat30, _mat21, _mat31, _mat22, _mat32, _mat23, _mat33);
    }

    /** Piece 2 of {@code preMul_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preMul_apiGet_sd8714568_1(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _mat20, double _mat30, double _mat21, double _mat31, double _mat22, double _mat32, double _mat23, double _mat33) {
        dest.put(destOffset + 2, Math.fma(_mat23, _selfw, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        dest.put(destOffset + 3, Math.fma(_mat33, _selfw, Math.fma(_mat32, _selfz, Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quatX, quatY, quatZ, quatW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(quatX, _selfy, -(quatY * _selfx));
        double _t10 = 2.0 * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        double _t11 = 2.0 * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.put(destOffset, Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.put(destOffset + 1, Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy))));
        dest.put(destOffset + 2, Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset * 8L;
        Double4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && quat.hasArray() && quatOffset >= 0 && quatOffset <= quat.limit() - 4) {
            Double4Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quat.array(), quat.arrayOffset() + quatOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, quat, quatOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _quatx = quat.get(quatOffset);
        double _quaty = quat.get(quatOffset + 1);
        double _quatz = quat.get(quatOffset + 2);
        double _quatw = quat.get(quatOffset + 3);
        double _t9 = 2.0 * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        double _t10 = 2.0 * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        double _t11 = 2.0 * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.put(destOffset, Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.put(destOffset + 1, Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy))));
        dest.put(destOffset + 2, Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        dest.put(destOffset, Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0)));
        dest.put(destOffset + 1, Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0)));
        dest.put(destOffset + 2, Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0)));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 8L;
        Double4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Double4Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _axisx = axis.get(axisOffset);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.put(destOffset, Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0)));
        dest.put(destOffset + 1, Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0)));
        dest.put(destOffset + 2, Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0)));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateInverse_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateInverse_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.rotateInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quatX, quatY, quatZ, quatW);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.rotateInverse_apiGet(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateInverse_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        double _t10 = 2.0 * Math.fma(quatY, _selfx, -(quatX * _selfy));
        double _t11 = 2.0 * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.put(destOffset, Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.put(destOffset + 1, Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy))));
        dest.put(destOffset + 2, Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateInverse_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset * 8L;
        Double4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateInverse_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && quat.hasArray() && quatOffset >= 0 && quatOffset <= quat.limit() - 4) {
            Double4Ops.rotateInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quat.array(), quat.arrayOffset() + quatOffset);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.rotateInverse_apiGet(dest, destOffset, src, srcOffset, quat, quatOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateInverse_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _quatx = quat.get(quatOffset);
        double _quaty = quat.get(quatOffset + 1);
        double _quatz = quat.get(quatOffset + 2);
        double _quatw = quat.get(quatOffset + 3);
        double _t9 = 2.0 * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        double _t10 = 2.0 * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        double _t11 = 2.0 * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.put(destOffset, Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.put(destOffset + 1, Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy))));
        dest.put(destOffset + 2, Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz))));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.rotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.rotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, Math.fma(_selfy, _t1, -(_selfz * _t0)));
        dest.put(destOffset + 2, Math.fma(_selfy, _t0, _selfz * _t1));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.rotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.rotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, Math.fma(_selfx, _t1, _selfz * _t0));
        dest.put(destOffset + 1, _selfy);
        dest.put(destOffset + 2, Math.fma(_selfz, _t1, -(_selfx * _t0)));
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Double4Ops.rotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Double4OpsKernelsTypedBuffer.rotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.put(destOffset + 1, Math.fma(_selfx, _t0, _selfy * _t1));
        dest.put(destOffset + 2, _selfz);
        dest.put(destOffset + 3, _selfw);
        return dest;
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

    /**
     * The floored remainder of x and y, exactly kotlin.Double.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
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
