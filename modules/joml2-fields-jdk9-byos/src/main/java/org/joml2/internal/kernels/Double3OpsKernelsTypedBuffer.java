// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Double3Ops} whose leading storage
 * parameter is a typed {@link java.nio.DoubleBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Double3Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Double3OpsKernelsTypedBuffer {
    private Double3OpsKernelsTypedBuffer() {}

    public static java.nio.DoubleBuffer add_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer add_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer add_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, otherX + src.get(srcOffset));
        dest.put(destOffset + 1, otherY + _selfy);
        dest.put(destOffset + 2, otherZ + _selfz);
        return dest;
    }

    public static java.nio.DoubleBuffer add_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer add_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Double3Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer add_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        dest.put(destOffset, other.get(otherOffset) + src.get(srcOffset));
        dest.put(destOffset + 1, _othery + _selfy);
        dest.put(destOffset + 2, _otherz + _selfz);
        return dest;
    }

    public static java.nio.DoubleBuffer div_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer div_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer div_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, src.get(srcOffset) / scalar);
        dest.put(destOffset + 1, _selfy / scalar);
        dest.put(destOffset + 2, _selfz / scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer div_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer div_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer div_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, src.get(srcOffset) / otherX);
        dest.put(destOffset + 1, _selfy / otherY);
        dest.put(destOffset + 2, _selfz / otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer div_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer div_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Double3Ops.div(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.div_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer div_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        dest.put(destOffset, src.get(srcOffset) / other.get(otherOffset));
        dest.put(destOffset + 1, _selfy / _othery);
        dest.put(destOffset + 2, _selfz / _otherz);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double b, double cX, double cY, double cZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double b, double cX, double cY, double cZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b, cX, cY, cZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, cX, cY, cZ);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double b, double cX, double cY, double cZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), b, cX));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, b, cY));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, b, cZ));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, ((src.get(srcOffset)) * (b) + (cX)));
            dest.put(destOffset + 1, ((_selfy) * (b) + (cY)));
            dest.put(destOffset + 2, ((_selfz) * (b) + (cZ)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer fma_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer c, int cOffset, double b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset * 8L;
        Double3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer c, int cOffset, double b) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 3) {
            Double3Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, c.array(), c.arrayOffset() + cOffset, b);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, c, cOffset, b);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer c, int cOffset, double b) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _cy = c.get(cOffset + 1);
            double _cz = c.get(cOffset + 2);
            dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), b, c.get(cOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, b, _cy));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, b, _cz));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _cy = c.get(cOffset + 1);
            double _cz = c.get(cOffset + 2);
            dest.put(destOffset, ((src.get(srcOffset)) * (b) + (c.get(cOffset))));
            dest.put(destOffset + 1, ((_selfy) * (b) + (_cy)));
            dest.put(destOffset + 2, ((_selfz) * (b) + (_cz)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer fma_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, cX, cY, cZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), bX, cX));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, bY, cY));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, bZ, cZ));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, ((src.get(srcOffset)) * (bX) + (cX)));
            dest.put(destOffset + 1, ((_selfy) * (bY) + (cY)));
            dest.put(destOffset + 2, ((_selfz) * (bZ) + (cZ)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer fma_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset * 8L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset * 8L;
        Double3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 3 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 3) {
            Double3Ops.fma(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.fma_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer fma_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _by = b.get(bOffset + 1);
            double _bz = b.get(bOffset + 2);
            double _cy = c.get(cOffset + 1);
            double _cz = c.get(cOffset + 2);
            dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), b.get(bOffset), c.get(cOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _by, _cy));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _bz, _cz));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _by = b.get(bOffset + 1);
            double _bz = b.get(bOffset + 2);
            double _cy = c.get(cOffset + 1);
            double _cz = c.get(cOffset + 2);
            dest.put(destOffset, ((src.get(srcOffset)) * (b.get(bOffset)) + (c.get(cOffset))));
            dest.put(destOffset + 1, ((_selfy) * (_by) + (_cy)));
            dest.put(destOffset + 2, ((_selfz) * (_bz) + (_cz)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, scalar * src.get(srcOffset));
        dest.put(destOffset + 1, scalar * _selfy);
        dest.put(destOffset + 2, scalar * _selfz);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, otherX * src.get(srcOffset));
        dest.put(destOffset + 1, otherY * _selfy);
        dest.put(destOffset + 2, otherZ * _selfz);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Double3Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        dest.put(destOffset, other.get(otherOffset) * src.get(srcOffset));
        dest.put(destOffset + 1, _othery * _selfy);
        dest.put(destOffset + 2, _otherz * _selfz);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, -src.get(srcOffset));
        dest.put(destOffset + 1, -_selfy);
        dest.put(destOffset + 2, -_selfz);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, src.get(srcOffset) - otherX);
        dest.put(destOffset + 1, _selfy - otherY);
        dest.put(destOffset + 2, _selfz - otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Double3Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        dest.put(destOffset, src.get(srcOffset) - other.get(otherOffset));
        dest.put(destOffset + 1, _selfy - _othery);
        dest.put(destOffset + 2, _selfz - _otherz);
        return dest;
    }

    public static java.nio.DoubleBuffer makeUniformDirection_unsafe(java.nio.DoubleBuffer dest, int destOffset, double u, double v) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v);
        return dest;
    }

    public static java.nio.DoubleBuffer makeUniformDirection_api(java.nio.DoubleBuffer dest, int destOffset, double u, double v) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            Double3Ops.makeUniformDirection(dest.array(), dest.arrayOffset() + destOffset, u, v);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.makeUniformDirection_apiGet(dest, destOffset, u, v);
        return dest;
    }

    public static java.nio.DoubleBuffer makeUniformDirection_apiGet(java.nio.DoubleBuffer dest, int destOffset, double u, double v) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t1 = v * 6.283185307179586;
            double _t4 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
            dest.put(destOffset, _t4 * Math.cosWithSin(_t1));
            dest.put(destOffset + 1, _t4 * Math.sinWithCos(_t1));
            dest.put(destOffset + 2, java.lang.Math.fma(2.0, u, -1.0));
            return dest;
        } else {
            double _t1 = v * 6.283185307179586;
            double _t4 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
            dest.put(destOffset, _t4 * Math.cosWithSin(_t1));
            dest.put(destOffset + 1, _t4 * Math.sinWithCos(_t1));
            dest.put(destOffset + 2, ((2.0) * (u) - (1.0)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            Double3Ops.set(dest.array(), dest.arrayOffset() + destOffset, vX, vY, vZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ) {
        dest.put(destOffset, vX);
        dest.put(destOffset + 1, vY);
        dest.put(destOffset + 2, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Double3Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        dest.put(destOffset, v.get(vOffset));
        dest.put(destOffset + 1, _vy);
        dest.put(destOffset + 2, _vz);
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            Double3Ops.set(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, double s) {
        dest.put(destOffset, s);
        dest.put(destOffset + 1, s);
        dest.put(destOffset + 2, s);
        return dest;
    }

    public static java.nio.DoubleBuffer makeZero_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeZero_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            Double3Ops.makeZero(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.makeZero_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeZero_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.put(destOffset, java.lang.Math.fma(p1X, _t7, src.get(srcOffset) * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2));
        dest.put(destOffset + 1, java.lang.Math.fma(p1Y, _t7, _selfy * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2));
        dest.put(destOffset + 2, java.lang.Math.fma(p1Z, _t7, _selfz * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2));
        return dest;
    }

    /** {@code bezier_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer bezier_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.put(destOffset, ((p1X) * (_t7) + (src.get(srcOffset) * _t8)) + ((p2X) * (_t6) + (p3X * _t2)));
        dest.put(destOffset + 1, ((p1Y) * (_t7) + (_selfy * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2)));
        dest.put(destOffset + 2, ((p1Z) * (_t7) + (_selfz * _t8)) + ((p2Z) * (_t6) + (p3Z * _t2)));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 8L;
        Double3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 3) {
            Double3Ops.bezier(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.bezier_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p3y = p3.get(p3Offset + 1);
        double _p3z = p3.get(p3Offset + 2);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.put(destOffset, java.lang.Math.fma(p1.get(p1Offset), _t7, src.get(srcOffset) * _t8) + java.lang.Math.fma(p2.get(p2Offset), _t6, p3.get(p3Offset) * _t2));
        dest.put(destOffset + 1, java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2));
        dest.put(destOffset + 2, java.lang.Math.fma(_p1z, _t7, _selfz * _t8) + java.lang.Math.fma(_p2z, _t6, _p3z * _t2));
        return dest;
    }

    /** {@code bezier_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer bezier_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p3y = p3.get(p3Offset + 1);
        double _p3z = p3.get(p3Offset + 2);
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest.put(destOffset, ((p1.get(p1Offset)) * (_t7) + (src.get(srcOffset) * _t8)) + ((p2.get(p2Offset)) * (_t6) + (p3.get(p3Offset) * _t2)));
        dest.put(destOffset + 1, ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2)));
        dest.put(destOffset + 2, ((_p1z) * (_t7) + (_selfz * _t8)) + ((_p2z) * (_t6) + (_p3z * _t2)));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            dest.put(destOffset, java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, src.get(srcOffset) * _t4)));
            dest.put(destOffset + 1, java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, _selfy * _t4)));
            dest.put(destOffset + 2, java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, _selfz * _t4)));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t0 = t * t;
            double _t1 = 1.0 - t;
            double _t3 = (t + t) * _t1;
            double _t4 = _t1 * _t1;
            dest.put(destOffset, ((p2X) * (_t0) + (((p1X) * (_t3) + (src.get(srcOffset) * _t4)))));
            dest.put(destOffset + 1, ((p2Y) * (_t0) + (((p1Y) * (_t3) + (_selfy * _t4)))));
            dest.put(destOffset + 2, ((p2Z) * (_t0) + (((p1Z) * (_t3) + (_selfz * _t4)))));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer bezier2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        Double3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3) {
            Double3Ops.bezier2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.bezier2_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.put(destOffset, java.lang.Math.fma(p2.get(p2Offset), _t0, java.lang.Math.fma(p1.get(p1Offset), _t3, src.get(srcOffset) * _t4)));
        dest.put(destOffset + 1, java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4)));
        dest.put(destOffset + 2, java.lang.Math.fma(_p2z, _t0, java.lang.Math.fma(_p1z, _t3, _selfz * _t4)));
        return dest;
    }

    /** {@code bezier2_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer bezier2_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest.put(destOffset, ((p2.get(p2Offset)) * (_t0) + (((p1.get(p1Offset)) * (_t3) + (src.get(srcOffset) * _t4)))));
        dest.put(destOffset + 1, ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4)))));
        dest.put(destOffset + 2, ((_p2z) * (_t0) + (((_p1z) * (_t3) + (_selfz * _t4)))));
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            dest.put(destOffset, java.lang.Math.fma(p1X - src.get(srcOffset), _t2, (p2X - p1X) * _t1));
            dest.put(destOffset + 1, java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
            dest.put(destOffset + 2, java.lang.Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t1 = t + t;
            double _t2 = 2.0 * (1.0 - t);
            dest.put(destOffset, ((p1X - src.get(srcOffset)) * (_t2) + ((p2X - p1X) * _t1)));
            dest.put(destOffset + 1, ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1)));
            dest.put(destOffset + 2, ((p1Z - _selfz) * (_t2) + ((p2Z - p1Z) * _t1)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer bezier2Tangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        Double3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3) {
            Double3Ops.bezier2Tangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.bezier2Tangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezier2Tangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.put(destOffset, java.lang.Math.fma(_p1x - src.get(srcOffset), _t2, (p2.get(p2Offset) - _p1x) * _t1));
        dest.put(destOffset + 1, java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        dest.put(destOffset + 2, java.lang.Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer bezier2Tangent_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest.put(destOffset, ((_p1x - src.get(srcOffset)) * (_t2) + ((p2.get(p2Offset) - _p1x) * _t1)));
        dest.put(destOffset + 1, ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1)));
        dest.put(destOffset + 2, ((_p1z - _selfz) * (_t2) + ((_p2z - _p1z) * _t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.put(destOffset, java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - src.get(srcOffset), _t6, (p2X - p1X) * _t5)));
        dest.put(destOffset + 1, java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.put(destOffset + 2, java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer bezierTangent_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.put(destOffset, ((p3X - p2X) * (_t2) + (((p1X - src.get(srcOffset)) * (_t6) + ((p2X - p1X) * _t5)))));
        dest.put(destOffset + 1, ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5)))));
        dest.put(destOffset + 2, ((p3Z - p2Z) * (_t2) + (((p1Z - _selfz) * (_t6) + ((p2Z - p1Z) * _t5)))));
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 8L;
        Double3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 3) {
            Double3Ops.bezierTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.bezierTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer bezierTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2x = p2.get(p2Offset);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p3y = p3.get(p3Offset + 1);
        double _p3z = p3.get(p3Offset + 2);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.put(destOffset, java.lang.Math.fma(p3.get(p3Offset) - _p2x, _t2, java.lang.Math.fma(_p1x - src.get(srcOffset), _t6, (_p2x - _p1x) * _t5)));
        dest.put(destOffset + 1, java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.put(destOffset + 2, java.lang.Math.fma(_p3z - _p2z, _t2, java.lang.Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer bezierTangent_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2x = p2.get(p2Offset);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p3y = p3.get(p3Offset + 1);
        double _p3z = p3.get(p3Offset + 2);
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest.put(destOffset, ((p3.get(p3Offset) - _p2x) * (_t2) + (((_p1x - src.get(srcOffset)) * (_t6) + ((_p2x - _p1x) * _t5)))));
        dest.put(destOffset + 1, ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5)))));
        dest.put(destOffset + 2, ((_p3z - _p2z) * (_t2) + (((_p1z - _selfz) * (_t6) + ((_p2z - _p1z) * _t5)))));
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_apiGet_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return catmullRom_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@code catmullRom_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer catmullRom_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.put(destOffset, 0.5 * (java.lang.Math.fma(2.0, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - _selfx)) * _t1)));
        dest.put(destOffset + 1, 0.5 * (java.lang.Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1)));
        dest.put(destOffset + 2, 0.5 * (java.lang.Math.fma(2.0, p1Z, t * (p2Z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - _selfz)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer catmullRom_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.put(destOffset, 0.5 * (((2.0) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0) * (_selfx) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) * (_t0) + (((((3.0) * (p1X) + (p3X - _selfx))) - (3.0) * (p2X)) * _t1))));
        dest.put(destOffset + 1, 0.5 * (((2.0) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0) * (_selfy) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) * (_t0) + (((((3.0) * (p1Y) + (p3Y - _selfy))) - (3.0) * (p2Y)) * _t1))));
        dest.put(destOffset + 2, 0.5 * (((2.0) * (p1Z) + (t * (p2Z - _selfz))) + ((((((2.0) * (_selfz) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) * (_t0) + (((((3.0) * (p1Z) + (p3Z - _selfz))) - (3.0) * (p2Z)) * _t1))));
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 8L;
        Double3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 3) {
            Double3Ops.catmullRom(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.catmullRom_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRom_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_apiGet_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRom_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRom_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer catmullRom_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2x = p2.get(p2Offset);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p3x = p3.get(p3Offset);
        double _p3y = p3.get(p3Offset + 1);
        double _p3z = p3.get(p3Offset + 2);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.put(destOffset, 0.5 * (java.lang.Math.fma(2.0, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1x, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0, _p2x, java.lang.Math.fma(3.0, _p1x, _p3x - _selfx)) * _t1)));
        return catmullRom_apiGet_fma_saa3e2537_1(dest, destOffset, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer catmullRom_apiGet_fma_saa3e2537_1(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfy, double _selfz, double _p1y, double _p1z, double _p2y, double _p2z, double _p3y, double _p3z, double _t0, double _t1) {
        dest.put(destOffset + 1, 0.5 * (java.lang.Math.fma(2.0, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0, _p2y, java.lang.Math.fma(3.0, _p1y, _p3y - _selfy)) * _t1)));
        dest.put(destOffset + 2, 0.5 * (java.lang.Math.fma(2.0, _p1z, t * (_p2z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0, _p1z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, _p2z, -_p3z))), _t0, java.lang.Math.fma(-3.0, _p2z, java.lang.Math.fma(3.0, _p1z, _p3z - _selfz)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer catmullRom_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2x = p2.get(p2Offset);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p3x = p3.get(p3Offset);
        double _p3y = p3.get(p3Offset + 1);
        double _p3z = p3.get(p3Offset + 2);
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest.put(destOffset, 0.5 * (((2.0) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0) * (_selfx) + (((4.0) * (_p2x) - (_p3x))))) - (5.0) * (_p1x))) * (_t0) + (((((3.0) * (_p1x) + (_p3x - _selfx))) - (3.0) * (_p2x)) * _t1))));
        dest.put(destOffset + 1, 0.5 * (((2.0) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0) * (_selfy) + (((4.0) * (_p2y) - (_p3y))))) - (5.0) * (_p1y))) * (_t0) + (((((3.0) * (_p1y) + (_p3y - _selfy))) - (3.0) * (_p2y)) * _t1))));
        return catmullRom_apiGet_mulAdd_s99c5ac72_1(dest, destOffset, t, _selfz, _p1z, _p2z, _p3z, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer catmullRom_apiGet_mulAdd_s99c5ac72_1(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfz, double _p1z, double _p2z, double _p3z, double _t0, double _t1) {
        dest.put(destOffset + 2, 0.5 * (((2.0) * (_p1z) + (t * (_p2z - _selfz))) + ((((((2.0) * (_selfz) + (((4.0) * (_p2z) - (_p3z))))) - (5.0) * (_p1z))) * (_t0) + (((((3.0) * (_p1z) + (_p3z - _selfz))) - (3.0) * (_p2z)) * _t1))));
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_apiGet_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return catmullRomTangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@code catmullRomTangent_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer catmullRomTangent_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = t * t;
        dest.put(destOffset, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1X, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, p2X, -p3X))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2X, java.lang.Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.put(destOffset + 1, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, p2Y, -p3Y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Y, java.lang.Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        dest.put(destOffset + 2, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, p1Z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, p2Z, -p3Z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, p2Z, java.lang.Math.fma(3.0, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        return dest;
    }

    /** {@code catmullRomTangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer catmullRomTangent_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = t * t;
        dest.put(destOffset, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfx) + (((4.0) * (p2X) - (p3X))))) - (5.0) * (p1X))) + (((3.0 * ((((3.0) * (p1X) + (p3X - _selfx))) - (3.0) * (p2X))) * (_t0) + (p2X - _selfx)))));
        dest.put(destOffset + 1, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfy) + (((4.0) * (p2Y) - (p3Y))))) - (5.0) * (p1Y))) + (((3.0 * ((((3.0) * (p1Y) + (p3Y - _selfy))) - (3.0) * (p2Y))) * (_t0) + (p2Y - _selfy)))));
        dest.put(destOffset + 2, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfz) + (((4.0) * (p2Z) - (p3Z))))) - (5.0) * (p1Z))) + (((3.0 * ((((3.0) * (p1Z) + (p3Z - _selfz))) - (3.0) * (p2Z))) * (_t0) + (p2Z - _selfz)))));
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset * 8L;
        Double3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3 && p3.hasArray() && p3Offset >= 0 && p3Offset <= p3.limit() - 3) {
            Double3Ops.catmullRomTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset, p3.array(), p3.arrayOffset() + p3Offset, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.catmullRomTangent_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer catmullRomTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_apiGet_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRomTangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRomTangent_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer catmullRomTangent_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2x = p2.get(p2Offset);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p3x = p3.get(p3Offset);
        double _p3y = p3.get(p3Offset + 1);
        double _p3z = p3.get(p3Offset + 2);
        double _t0 = t * t;
        dest.put(destOffset, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1x, java.lang.Math.fma(2.0, _selfx, java.lang.Math.fma(4.0, _p2x, -_p3x))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2x, java.lang.Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.put(destOffset + 1, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1y, java.lang.Math.fma(2.0, _selfy, java.lang.Math.fma(4.0, _p2y, -_p3y))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2y, java.lang.Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        return catmullRomTangent_apiGet_fma_s3ee8e070_1(dest, destOffset, t, _selfz, _p1z, _p2z, _p3z, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer catmullRomTangent_apiGet_fma_s3ee8e070_1(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfz, double _p1z, double _p2z, double _p3z, double _t0) {
        dest.put(destOffset + 2, 0.5 * java.lang.Math.fma(t, 2.0 * java.lang.Math.fma(-5.0, _p1z, java.lang.Math.fma(2.0, _selfz, java.lang.Math.fma(4.0, _p2z, -_p3z))), java.lang.Math.fma(3.0 * java.lang.Math.fma(-3.0, _p2z, java.lang.Math.fma(3.0, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return dest;
    }

    /** {@code catmullRomTangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer catmullRomTangent_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2x = p2.get(p2Offset);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _p3x = p3.get(p3Offset);
        double _p3y = p3.get(p3Offset + 1);
        double _p3z = p3.get(p3Offset + 2);
        double _t0 = t * t;
        dest.put(destOffset, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfx) + (((4.0) * (_p2x) - (_p3x))))) - (5.0) * (_p1x))) + (((3.0 * ((((3.0) * (_p1x) + (_p3x - _selfx))) - (3.0) * (_p2x))) * (_t0) + (_p2x - _selfx)))));
        dest.put(destOffset + 1, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfy) + (((4.0) * (_p2y) - (_p3y))))) - (5.0) * (_p1y))) + (((3.0 * ((((3.0) * (_p1y) + (_p3y - _selfy))) - (3.0) * (_p2y))) * (_t0) + (_p2y - _selfy)))));
        return catmullRomTangent_apiGet_mulAdd_s64d36a87_1(dest, destOffset, t, _selfz, _p1z, _p2z, _p3z, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer catmullRomTangent_apiGet_mulAdd_s64d36a87_1(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfz, double _p1z, double _p2z, double _p3z, double _t0) {
        dest.put(destOffset + 2, 0.5 * ((t) * (2.0 * ((((2.0) * (_selfz) + (((4.0) * (_p2z) - (_p3z))))) - (5.0) * (_p1z))) + (((3.0 * ((((3.0) * (_p1z) + (_p3z - _selfz))) - (3.0) * (_p2z))) * (_t0) + (_p2z - _selfz)))));
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermite_apiGet_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * java.lang.Math.fma(t, t, -t);
        double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
        return dest;
    }

    /** {@code hermite_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer hermite_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * ((t) * (t) - (t));
        double _t7 = ((t - 2.0) * (_t0) + (t));
        double _t9 = ((3.0) * (_t0) - (_t2 + _t2));
        double _t10 = ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0))));
        dest.put(destOffset, ((src.get(srcOffset)) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)));
        dest.put(destOffset + 1, ((_selfy) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)));
        dest.put(destOffset + 2, ((_selfz) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9)));
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset * 8L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset * 8L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset * 8L;
        Double3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 3 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 3 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 3) {
            Double3Ops.hermite(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.hermite_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermite_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermite_apiGet_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermite_apiGet_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermite_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer hermite_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0y = t0.get(t0Offset + 1);
        double _t0z = t0.get(t0Offset + 2);
        double _v1y = v1.get(v1Offset + 1);
        double _v1z = v1.get(v1Offset + 2);
        double _t1y = t1.get(t1Offset + 1);
        double _t1z = t1.get(t1Offset + 2);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * java.lang.Math.fma(t, t, -t);
        double _t7 = java.lang.Math.fma(t - 2.0, _t0, t);
        double _t9 = java.lang.Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = java.lang.Math.fma(2.0, _t2, java.lang.Math.fma(-3.0, _t0, 1.0));
        dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), _t10, t0.get(t0Offset) * _t7) + java.lang.Math.fma(t1.get(t1Offset), _t5, v1.get(v1Offset) * _t9));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t10, _t0z * _t7) + java.lang.Math.fma(_t1z, _t5, _v1z * _t9));
        return dest;
    }

    /** {@code hermite_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer hermite_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0y = t0.get(t0Offset + 1);
        double _t0z = t0.get(t0Offset + 2);
        double _v1y = v1.get(v1Offset + 1);
        double _v1z = v1.get(v1Offset + 2);
        double _t1y = t1.get(t1Offset + 1);
        double _t1z = t1.get(t1Offset + 2);
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * ((t) * (t) - (t));
        double _t7 = ((t - 2.0) * (_t0) + (t));
        double _t9 = ((3.0) * (_t0) - (_t2 + _t2));
        double _t10 = ((2.0) * (_t2) + (((1.0) - (3.0) * (_t0))));
        dest.put(destOffset, ((src.get(srcOffset)) * (_t10) + (t0.get(t0Offset) * _t7)) + ((t1.get(t1Offset)) * (_t5) + (v1.get(v1Offset) * _t9)));
        dest.put(destOffset + 1, ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9)));
        dest.put(destOffset + 2, ((_selfz) * (_t10) + (_t0z * _t7)) + ((_t1z) * (_t5) + (_v1z * _t9)));
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = t * t;
        double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
        double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
        return dest;
    }

    /** {@code hermiteTangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer hermiteTangent_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = t * t;
        double _t6 = 6.0 * ((t) * (t) - (t));
        double _t7 = 6.0 * ((t) - (t) * (t));
        double _t8 = ((3.0) * (_t0) - (t + t));
        double _t9 = ((3.0) * (_t0) + (((1.0) - (4.0) * (t))));
        dest.put(destOffset, ((src.get(srcOffset)) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)));
        dest.put(destOffset + 1, ((_selfy) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)));
        dest.put(destOffset + 2, ((_selfz) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7)));
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset * 8L;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset * 8L;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset * 8L;
        Double3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && t0.hasArray() && t0Offset >= 0 && t0Offset <= t0.limit() - 3 && v1.hasArray() && v1Offset >= 0 && v1Offset <= v1.limit() - 3 && t1.hasArray() && t1Offset >= 0 && t1Offset <= t1.limit() - 3) {
            Double3Ops.hermiteTangent(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t0.array(), t0.arrayOffset() + t0Offset, v1.array(), v1.arrayOffset() + v1Offset, t1.array(), t1.arrayOffset() + t1Offset, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.hermiteTangent_apiGet(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer hermiteTangent_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_apiGet_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermiteTangent_apiGet_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermiteTangent_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer hermiteTangent_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0y = t0.get(t0Offset + 1);
        double _t0z = t0.get(t0Offset + 2);
        double _v1y = v1.get(v1Offset + 1);
        double _v1z = v1.get(v1Offset + 2);
        double _t1y = t1.get(t1Offset + 1);
        double _t1z = t1.get(t1Offset + 2);
        double _t0 = t * t;
        double _t6 = 6.0 * java.lang.Math.fma(t, t, -t);
        double _t7 = 6.0 * java.lang.Math.fma(-t, t, t);
        double _t8 = java.lang.Math.fma(3.0, _t0, -(t + t));
        double _t9 = java.lang.Math.fma(3.0, _t0, java.lang.Math.fma(-4.0, t, 1.0));
        dest.put(destOffset, java.lang.Math.fma(src.get(srcOffset), _t6, t0.get(t0Offset) * _t9) + java.lang.Math.fma(t1.get(t1Offset), _t8, v1.get(v1Offset) * _t7));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t6, _t0z * _t9) + java.lang.Math.fma(_t1z, _t8, _v1z * _t7));
        return dest;
    }

    /** {@code hermiteTangent_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer hermiteTangent_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0y = t0.get(t0Offset + 1);
        double _t0z = t0.get(t0Offset + 2);
        double _v1y = v1.get(v1Offset + 1);
        double _v1z = v1.get(v1Offset + 2);
        double _t1y = t1.get(t1Offset + 1);
        double _t1z = t1.get(t1Offset + 2);
        double _t0 = t * t;
        double _t6 = 6.0 * ((t) * (t) - (t));
        double _t7 = 6.0 * ((t) - (t) * (t));
        double _t8 = ((3.0) * (_t0) - (t + t));
        double _t9 = ((3.0) * (_t0) + (((1.0) - (4.0) * (t))));
        dest.put(destOffset, ((src.get(srcOffset)) * (_t6) + (t0.get(t0Offset) * _t9)) + ((t1.get(t1Offset)) * (_t8) + (v1.get(v1Offset) * _t7)));
        dest.put(destOffset + 1, ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7)));
        dest.put(destOffset + 2, ((_selfz) * (_t6) + (_t0z * _t9)) + ((_t1z) * (_t8) + (_v1z * _t7)));
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((t) * (otherY - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((t) * (otherZ - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Double3Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _othery = other.get(otherOffset + 1);
            double _otherz = other.get(otherOffset + 2);
            dest.put(destOffset, java.lang.Math.fma(t, other.get(otherOffset) - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _othery = other.get(otherOffset + 1);
            double _otherz = other.get(otherOffset + 2);
            dest.put(destOffset, ((t) * (other.get(otherOffset) - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((t) * (_othery - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((t) * (_otherz - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, tX, tY, tZ);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, tX, tY, tZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, tX, tY, tZ);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, java.lang.Math.fma(tX, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(tY, otherY - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(tZ, otherZ - _selfz, _selfz));
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, ((tX) * (otherX - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((tY) * (otherY - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((tZ) * (otherZ - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset * 8L;
        Double3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer t, int tOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3 && t.hasArray() && tOffset >= 0 && tOffset <= t.limit() - 3) {
            Double3Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t.array(), t.arrayOffset() + tOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer t, int tOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _othery = other.get(otherOffset + 1);
            double _otherz = other.get(otherOffset + 2);
            double _ty = t.get(tOffset + 1);
            double _tz = t.get(tOffset + 2);
            dest.put(destOffset, java.lang.Math.fma(t.get(tOffset), other.get(otherOffset) - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(_ty, _othery - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(_tz, _otherz - _selfz, _selfz));
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _othery = other.get(otherOffset + 1);
            double _otherz = other.get(otherOffset + 2);
            double _ty = t.get(tOffset + 1);
            double _tz = t.get(tOffset + 2);
            dest.put(destOffset, ((t.get(tOffset)) * (other.get(otherOffset) - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((_ty) * (_othery - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((_tz) * (_otherz - _selfz) + (_selfz)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer slerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_apiGet_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return slerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@code slerp_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_apiGet_fma_sf9978398_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), java.lang.Math.fma(otherX, _t12, -(_t26 * _t14)), java.lang.Math.fma(otherY, _t12, -(_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_fma_sf9978398_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.put(destOffset, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        dest.put(destOffset + 1, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        dest.put(destOffset + 2, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        double _t33 = ((otherZ) * (_t12) - (_t26 * _t17));
        double _t34 = ((otherX) * (_t12) - (_t26 * _t14));
        double _t35 = ((otherY) * (_t12) - (_t26 * _t20));
        return slerp_apiGet_mulAdd_sb88c3df3_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, _t33, _t34, _t35, -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20)))));
    }

    /** Piece 2 of {@code slerp_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_mulAdd_sb88c3df3_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35, double _t39) {
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.put(destOffset, ((_t14) * (_t53) + (_sp0 * _t41)));
        dest.put(destOffset + 1, ((_t20) * (_t53) + (_sp0 * _t42)));
        dest.put(destOffset + 2, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Double3OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    public static java.nio.DoubleBuffer slerp_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_apiGet_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return slerp_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@code slerp_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = (1.0 / java.lang.Math.sqrt(_t24));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t48 = t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 5.048709793414476E-29) {
                    dest.put(destOffset, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    dest.put(destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                dest.put(destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                dest.put(destOffset + 1, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                dest.put(destOffset + 2, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            dest.put(destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        return slerp_degenerate_apiGet_mulAdd_s3a4b0ef0_1(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t9, _t10, _t11, 1.0 / _t15, _t24, _t25, (1.0 / java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /** Piece 2 of {@code slerp_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd_s3a4b0ef0_1(java.nio.DoubleBuffer dest, int destOffset, double otherX, double otherY, double otherZ, double t, double _selfx, double _selfy, double _selfz, double _t9, double _t10, double _t11, double _t15_inv, double _t24, double _t25, double _t28, double _t31, double _t33, double _t35, double _t48, double _t49, double _t50, double _t52) {
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        return slerp_degenerate_apiGet_mulAdd_s3a4b0ef0_2(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0 / java.lang.Math.sqrt(_t91)), _t48 * Math.sinWithCos(_t95), _t48 * Math.cosWithSin(_t95));
    }

    /** Piece 3 of {@code slerp_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd_s3a4b0ef0_2(java.nio.DoubleBuffer dest, int destOffset, double otherX, double otherY, double otherZ, double t, double _selfx, double _selfy, double _selfz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t85, double _t86, double _t87, double _t91, double _t93, double _t98, double _t99) {
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_apiGet_mulAdd_s3a4b0ef0_3(dest, destOffset, otherX, otherY, otherZ, t, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t98, _t99, _t103, _t104, _t105);
    }

    /** Piece 4 of {@code slerp_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd_s3a4b0ef0_3(java.nio.DoubleBuffer dest, int destOffset, double otherX, double otherY, double otherZ, double t, double _selfx, double _selfy, double _selfz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t98, double _t99, double _t103, double _t104, double _t105) {
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 5.048709793414476E-29) {
                    dest.put(destOffset, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    dest.put(destOffset + 1, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    dest.put(destOffset + 2, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    dest.put(destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    dest.put(destOffset + 1, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    dest.put(destOffset + 2, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                dest.put(destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                dest.put(destOffset + 1, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                dest.put(destOffset + 2, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            dest.put(destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((t) * (otherY - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((t) * (otherZ - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Double3Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_apiGet_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t8 = java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = java.lang.Math.fma(_otherz * _t12, _t17, java.lang.Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        return slerp_apiGet_fma_sa056580f_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _othery, _t12, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(_otherz, _t12, -(_t26 * _t17)), java.lang.Math.fma(_otherx, _t12, -(_t26 * _t14)));
    }

    /** Piece 2 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_fma_sa056580f_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double _othery, double _t12, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34) {
        double _t35 = java.lang.Math.fma(_othery, _t12, -(_t26 * _t20));
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.put(destOffset, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        dest.put(destOffset + 1, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        dest.put(destOffset + 2, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t8 = ((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = ((_otherz * _t12) * (_t17) + (((_otherx * _t12) * (_t14) + (_othery * _t12 * _t20))));
        return slerp_apiGet_mulAdd_sb696971a_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, ((_otherz) * (_t12) - (_t26 * _t17)), ((_otherx) * (_t12) - (_t26 * _t14)), ((_othery) * (_t12) - (_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_mulAdd_sb696971a_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.put(destOffset, ((_t14) * (_t53) + (_sp0 * _t41)));
        dest.put(destOffset + 1, ((_t20) * (_t53) + (_sp0 * _t42)));
        dest.put(destOffset + 2, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double3OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.DoubleBuffer slerp_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Double3OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_apiGet_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        slerp_degenerate_apiGet_fma_sf22a4c38_506(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz);
        return dest;
    }

    /** Part of {@code slerp_degenerate_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static void slerp_degenerate_apiGet_fma_sf22a4c38_506(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz) {
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = (1.0 / java.lang.Math.sqrt(_t24));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        slerp_degenerate_apiGet_fma_sf22a4c38_506_s82de92f1_1(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t9, _t10, _t11, 1.0 / _t15, _t24, _t25, _t28, _t31, _t33, _t35, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52, java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35)));
    }

    /** Piece 2 of {@code slerp_degenerate_apiGet_fma_sf22a4c38_506}, split to fit the inline budget; reached only through it. */
    private static void slerp_degenerate_apiGet_fma_sf22a4c38_506_s82de92f1_1(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t9, double _t10, double _t11, double _t15_inv, double _t24, double _t25, double _t28, double _t31, double _t33, double _t35, double _t48, double _t49, double _t50, double _t52, double _t53) {
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        slerp_degenerate_apiGet_fma_sf22a4c38_506_s82de92f1_2(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0 / java.lang.Math.sqrt(_t91)), _t48 * Math.sinWithCos(_t95), _t48 * Math.cosWithSin(_t95));
    }

    /** Piece 3 of {@code slerp_degenerate_apiGet_fma_sf22a4c38_506}, split to fit the inline budget; reached only through it. */
    private static void slerp_degenerate_apiGet_fma_sf22a4c38_506_s82de92f1_2(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t85, double _t86, double _t87, double _t91, double _t93, double _t98, double _t99) {
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        slerp_degenerate_apiGet_fma_sf22a4c38_506_s82de92f1_3(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t98, _t99, _t103, _t104, _t105);
    }

    /** Piece 4 of {@code slerp_degenerate_apiGet_fma_sf22a4c38_506}, split to fit the inline budget; reached only through it. */
    private static void slerp_degenerate_apiGet_fma_sf22a4c38_506_s82de92f1_3(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t98, double _t99, double _t103, double _t104, double _t105) {
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= 5.048709793414476E-29) {
                    dest.put(destOffset, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    dest.put(destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                dest.put(destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                dest.put(destOffset + 1, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                dest.put(destOffset + 2, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            dest.put(destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        }
    }

    /** {@code slerp_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        return slerp_degenerate_apiGet_mulAdd_sd56354e7_1(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t9, _t10, _t11, 1.0 / _t15, _t24, _t25, (1.0 / java.lang.Math.sqrt(_t24)), _t29 * _t12, _t29 * _t13, _t29 * _t14, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2));
    }

    /** Piece 2 of {@code slerp_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd_sd56354e7_1(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t9, double _t10, double _t11, double _t15_inv, double _t24, double _t25, double _t28, double _t31, double _t33, double _t35, double _t48) {
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        return slerp_degenerate_apiGet_mulAdd_sd56354e7_2(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t78, _t85, _t86, _t87, _t91, (1.0 / java.lang.Math.sqrt(_t91)));
    }

    /** Piece 3 of {@code slerp_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd_sd56354e7_2(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t48, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t78, double _t85, double _t86, double _t87, double _t91, double _t93) {
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_apiGet_mulAdd_sd56354e7_3(dest, destOffset, t, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t48 * Math.sinWithCos(_t95), _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 4 of {@code slerp_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd_sd56354e7_3(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t98, double _t99, double _t103, double _t104, double _t105) {
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= 5.048709793414476E-29) {
                    dest.put(destOffset, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    dest.put(destOffset + 1, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    dest.put(destOffset + 2, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    dest.put(destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    dest.put(destOffset + 1, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    dest.put(destOffset + 2, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                dest.put(destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                dest.put(destOffset + 1, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                dest.put(destOffset + 2, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            dest.put(destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((t) * (_othery - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((t) * (_otherz - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t, epsilon);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, t, epsilon);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_apiGet_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        return slerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
    }

    /** {@code slerp_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        double _t8 = java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = java.lang.Math.fma(otherZ * _t12, _t17, java.lang.Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_apiGet_fma_s51a4d64b_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(otherZ, _t12, -(_t26 * _t17)), java.lang.Math.fma(otherX, _t12, -(_t26 * _t14)), java.lang.Math.fma(otherY, _t12, -(_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_fma_s51a4d64b_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.put(destOffset, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        dest.put(destOffset + 1, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        dest.put(destOffset + 2, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        double _t8 = ((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = ((otherZ * _t12) * (_t17) + (((otherX * _t12) * (_t14) + (otherY * _t12 * _t20))));
        return slerp_apiGet_mulAdd_sa10b1b60_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, ((otherZ) * (_t12) - (_t26 * _t17)), ((otherX) * (_t12) - (_t26 * _t14)), ((otherY) * (_t12) - (_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_mulAdd_sa10b1b60_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.put(destOffset, ((_t14) * (_t53) + (_sp0 * _t41)));
        dest.put(destOffset + 1, ((_t20) * (_t53) + (_sp0 * _t42)));
        dest.put(destOffset + 2, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        return Double3OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
    }

    public static java.nio.DoubleBuffer slerp_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t, epsilon);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, t, epsilon);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_apiGet_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
        return slerp_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, epsilon);
    }

    /** {@code slerp_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t15_inv = 1.0 / _t15;
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = (1.0 / java.lang.Math.sqrt(_t24));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t48 = t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t69 = _t68 * _t49;
        double _t70 = _t68 * _t50;
        double _t71 = _t68 * _t52;
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t98 = _t48 * Math.sinWithCos(_t95);
        double _t99 = _t48 * Math.cosWithSin(_t95);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    dest.put(destOffset, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    dest.put(destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                dest.put(destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                dest.put(destOffset + 1, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                dest.put(destOffset + 2, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            dest.put(destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t, double epsilon) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherZ * _t1;
        double _t10 = otherX * _t1;
        double _t11 = otherY * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        double _t31 = _t29 * _t12;
        double _t33 = _t29 * _t13;
        double _t35 = _t29 * _t14;
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        return slerp_degenerate_apiGet_mulAdd_s56ae4293_1(dest, destOffset, otherX, otherY, otherZ, t, epsilon, _selfx, _selfy, _selfz, _t9, _t10, _t11, 1.0 / _t15, _t24, _t25, (1.0 / java.lang.Math.sqrt(_t24)), _t31, _t33, _t35, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2), _t49, _t50, _t52);
    }

    /** Piece 2 of {@code slerp_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd_s56ae4293_1(java.nio.DoubleBuffer dest, int destOffset, double otherX, double otherY, double otherZ, double t, double epsilon, double _selfx, double _selfy, double _selfz, double _t9, double _t10, double _t11, double _t15_inv, double _t24, double _t25, double _t28, double _t31, double _t33, double _t35, double _t48, double _t49, double _t50, double _t52) {
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        return slerp_degenerate_apiGet_mulAdd_s56ae4293_2(dest, destOffset, otherX, otherY, otherZ, t, epsilon, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t85, _t86, _t87, _t91, (1.0 / java.lang.Math.sqrt(_t91)), _t48 * Math.sinWithCos(_t95), _t48 * Math.cosWithSin(_t95));
    }

    /** Piece 3 of {@code slerp_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd_s56ae4293_2(java.nio.DoubleBuffer dest, int destOffset, double otherX, double otherY, double otherZ, double t, double epsilon, double _selfx, double _selfy, double _selfz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t85, double _t86, double _t87, double _t91, double _t93, double _t98, double _t99) {
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_apiGet_mulAdd_s56ae4293_3(dest, destOffset, otherX, otherY, otherZ, t, epsilon, _selfx, _selfy, _selfz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t98, _t99, _t103, _t104, _t105);
    }

    /** Piece 4 of {@code slerp_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd_s56ae4293_3(java.nio.DoubleBuffer dest, int destOffset, double otherX, double otherY, double otherZ, double t, double epsilon, double _selfx, double _selfy, double _selfz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t98, double _t99, double _t103, double _t104, double _t105) {
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    dest.put(destOffset, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    dest.put(destOffset + 1, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    dest.put(destOffset + 2, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    dest.put(destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    dest.put(destOffset + 1, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    dest.put(destOffset + 2, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                dest.put(destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                dest.put(destOffset + 1, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                dest.put(destOffset + 2, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            dest.put(destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((t) * (otherY - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((t) * (otherZ - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Double3Ops.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t, epsilon);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_apiGet_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t7 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t8 = java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = java.lang.Math.fma(_otherz * _t12, _t17, java.lang.Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        return slerp_apiGet_fma_sa22c082c_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _othery, _t12, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, java.lang.Math.fma(_otherz, _t12, -(_t26 * _t17)), java.lang.Math.fma(_otherx, _t12, -(_t26 * _t14)));
    }

    /** Piece 2 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_fma_sa22c082c_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon, double _othery, double _t12, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34) {
        double _t35 = java.lang.Math.fma(_othery, _t12, -(_t26 * _t20));
        double _t39 = -java.lang.Math.fma(_t33, _t17, java.lang.Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = java.lang.Math.fma(_t39, _t17, _t33);
        double _t41 = java.lang.Math.fma(_t39, _t14, _t34);
        double _t42 = java.lang.Math.fma(_t39, _t20, _t35);
        double _t46 = java.lang.Math.fma(_t40, _t40, java.lang.Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.put(destOffset, java.lang.Math.fma(_t14, _t53, _sp0 * _t41));
        dest.put(destOffset + 1, java.lang.Math.fma(_t20, _t53, _sp0 * _t42));
        dest.put(destOffset + 2, java.lang.Math.fma(_t17, _t53, _sp0 * _t40));
        return dest;
    }

    /** {@code slerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t7 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t8 = ((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = ((_otherz * _t12) * (_t17) + (((_otherx * _t12) * (_t14) + (_othery * _t12 * _t20))));
        return slerp_apiGet_mulAdd_sf0e3f031_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, ((_otherz) * (_t12) - (_t26 * _t17)), ((_otherx) * (_t12) - (_t26 * _t14)), ((_othery) * (_t12) - (_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_mulAdd_sf0e3f031_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -((_t33) * (_t17) + (((_t34) * (_t14) + (_t35 * _t20))));
        double _t40 = ((_t39) * (_t17) + (_t33));
        double _t41 = ((_t39) * (_t14) + (_t34));
        double _t42 = ((_t39) * (_t20) + (_t35));
        double _t46 = ((_t40) * (_t40) + (((_t41) * (_t41) + (_t42 * _t42))));
        if (!(_t46 > epsilon && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _t53 = _t24 * Math.cosWithSin(_t50);
        double _sp0 = _t24 * Math.sinWithCos(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        dest.put(destOffset, ((_t14) * (_t53) + (_sp0 * _t41)));
        dest.put(destOffset + 1, ((_t20) * (_t53) + (_sp0 * _t42)));
        dest.put(destOffset + 2, ((_t17) * (_t53) + (_sp0 * _t40)));
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return Double3OpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    public static java.nio.DoubleBuffer slerp_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Double3OpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t, epsilon);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_apiGet_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t24 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11));
        double _t25 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        return slerp_degenerate_apiGet_fma_sd4bd6eb_1(dest, destOffset, t, epsilon, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t9, _t10, _t11, 1.0 / _t15, _t24, _t25, (1.0 / java.lang.Math.sqrt(_t24)), _t29 * _t12, _t29 * _t13, _t29 * _t14, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2));
    }

    /** Piece 2 of {@code slerp_degenerate_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_fma_sd4bd6eb_1(java.nio.DoubleBuffer dest, int destOffset, double t, double epsilon, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t9, double _t10, double _t11, double _t15_inv, double _t24, double _t25, double _t28, double _t31, double _t33, double _t35, double _t48) {
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = java.lang.Math.fma(_t28 * _t9, _t31, java.lang.Math.fma(_t28 * _t10, _t33, _t28 * _t11 * _t35));
        double _t61 = java.lang.Math.fma(_t28, _t9, -(_t53 * _t31));
        double _t62 = java.lang.Math.fma(_t28, _t10, -(_t53 * _t33));
        double _t63 = java.lang.Math.fma(_t28, _t11, -(_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t52, _t52, _t49 * _t49))));
        double _t73 = -java.lang.Math.fma(_t61, _t31, java.lang.Math.fma(_t62, _t33, _t63 * _t35));
        double _t74 = java.lang.Math.fma(_t73, _t31, _t61);
        double _t75 = java.lang.Math.fma(_t73, _t33, _t62);
        double _t76 = java.lang.Math.fma(_t73, _t35, _t63);
        double _t78 = unitScale(_t75, _t76, _t74);
        return slerp_degenerate_apiGet_fma_sd4bd6eb_2(dest, destOffset, t, epsilon, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t78, _t74 * _t78, _t75 * _t78, _t76 * _t78);
    }

    /** Piece 3 of {@code slerp_degenerate_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_fma_sd4bd6eb_2(java.nio.DoubleBuffer dest, int destOffset, double t, double epsilon, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t48, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t78, double _t85, double _t86, double _t87) {
        double _t91 = java.lang.Math.fma(_t85, _t85, java.lang.Math.fma(_t86, _t86, _t87 * _t87));
        double _t93 = (1.0 / java.lang.Math.sqrt(_t91));
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_apiGet_fma_sd4bd6eb_3(dest, destOffset, t, epsilon, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t48 * Math.sinWithCos(_t95), _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 4 of {@code slerp_degenerate_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_fma_sd4bd6eb_3(java.nio.DoubleBuffer dest, int destOffset, double t, double epsilon, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t98, double _t99, double _t103, double _t104, double _t105) {
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (java.lang.Math.fma(_t74, _t74, java.lang.Math.fma(_t75, _t75, _t76 * _t76)) <= epsilon) {
                    dest.put(destOffset, java.lang.Math.fma(_t98, _t69, _t99 * _t33) * _t15_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t98, _t71, _t99 * _t35) * _t15_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t98, _t70, _t99 * _t31) * _t15_inv);
                } else {
                    dest.put(destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                    dest.put(destOffset + 1, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                    dest.put(destOffset + 2, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
                }
            } else {
                dest.put(destOffset, java.lang.Math.fma(_t98, _t103, _t99 * _t33) * _t15_inv);
                dest.put(destOffset + 1, java.lang.Math.fma(_t98, _t105, _t99 * _t35) * _t15_inv);
                dest.put(destOffset + 2, java.lang.Math.fma(_t98, _t104, _t99 * _t31) * _t15_inv);
            }
        } else {
            dest.put(destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        }
        return dest;
    }

    /** {@code slerp_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t, double epsilon) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _otherz * _t1;
        double _t10 = _otherx * _t1;
        double _t11 = _othery * _t1;
        double _t12 = _selfz * _t2;
        double _t13 = _selfx * _t2;
        double _t14 = _selfy * _t2;
        double _t15 = Math.min(_t2, _t1);
        double _t24 = ((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))));
        double _t25 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        double _t29 = (1.0 / java.lang.Math.sqrt(_t25));
        return slerp_degenerate_apiGet_mulAdd_s85d8c934_1(dest, destOffset, t, epsilon, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t9, _t10, _t11, 1.0 / _t15, _t24, _t25, (1.0 / java.lang.Math.sqrt(_t24)), _t29 * _t12, _t29 * _t13, _t29 * _t14, t * java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0 - t) * java.lang.Math.sqrt(_t25) * (_t15 / _t2));
    }

    /** Piece 2 of {@code slerp_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd_s85d8c934_1(java.nio.DoubleBuffer dest, int destOffset, double t, double epsilon, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t9, double _t10, double _t11, double _t15_inv, double _t24, double _t25, double _t28, double _t31, double _t33, double _t35, double _t48) {
        double _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
            _t49 = _t35;
            _t50 = 0.0;
            _t52 = -_t33;
        } else {
            _t49 = 0.0;
            _t50 = -_t35;
            _t52 = _t31;
        }
        double _t53 = ((_t28 * _t9) * (_t31) + (((_t28 * _t10) * (_t33) + (_t28 * _t11 * _t35))));
        double _t61 = ((_t28) * (_t9) - (_t53 * _t31));
        double _t62 = ((_t28) * (_t10) - (_t53 * _t33));
        double _t63 = ((_t28) * (_t11) - (_t53 * _t35));
        double _t68 = (1.0 / java.lang.Math.sqrt(((_t50) * (_t50) + (((_t52) * (_t52) + (_t49 * _t49))))));
        double _t73 = -((_t61) * (_t31) + (((_t62) * (_t33) + (_t63 * _t35))));
        double _t74 = ((_t73) * (_t31) + (_t61));
        double _t75 = ((_t73) * (_t33) + (_t62));
        double _t76 = ((_t73) * (_t35) + (_t63));
        double _t78 = unitScale(_t75, _t76, _t74);
        double _t85 = _t74 * _t78;
        double _t86 = _t75 * _t78;
        double _t87 = _t76 * _t78;
        double _t91 = ((_t85) * (_t85) + (((_t86) * (_t86) + (_t87 * _t87))));
        return slerp_degenerate_apiGet_mulAdd_s85d8c934_2(dest, destOffset, t, epsilon, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t48, _t53, _t68 * _t49, _t68 * _t50, _t68 * _t52, _t74, _t75, _t76, _t78, _t85, _t86, _t87, _t91, (1.0 / java.lang.Math.sqrt(_t91)));
    }

    /** Piece 3 of {@code slerp_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd_s85d8c934_2(java.nio.DoubleBuffer dest, int destOffset, double t, double epsilon, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t48, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t78, double _t85, double _t86, double _t87, double _t91, double _t93) {
        double _t95 = t * Math.atan2(java.lang.Math.sqrt(_t91), _t53 * _t78);
        double _t103, _t104, _t105;
        if (_t91 > 0.0) {
            _t103 = _t93 * _t86;
            _t104 = _t93 * _t85;
            _t105 = _t93 * _t87;
        } else {
            _t103 = _t69;
            _t104 = _t70;
            _t105 = _t71;
        }
        return slerp_degenerate_apiGet_mulAdd_s85d8c934_3(dest, destOffset, t, epsilon, _selfx, _selfy, _selfz, _otherx, _othery, _otherz, _t15_inv, _t24, _t25, _t31, _t33, _t35, _t53, _t69, _t70, _t71, _t74, _t75, _t76, _t48 * Math.sinWithCos(_t95), _t48 * Math.cosWithSin(_t95), _t103, _t104, _t105);
    }

    /** Piece 4 of {@code slerp_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_degenerate_apiGet_mulAdd_s85d8c934_3(java.nio.DoubleBuffer dest, int destOffset, double t, double epsilon, double _selfx, double _selfy, double _selfz, double _otherx, double _othery, double _otherz, double _t15_inv, double _t24, double _t25, double _t31, double _t33, double _t35, double _t53, double _t69, double _t70, double _t71, double _t74, double _t75, double _t76, double _t98, double _t99, double _t103, double _t104, double _t105) {
        if (_t24 * _t25 > 0.0) {
            if (_t53 < 0.0) {
                if (((_t74) * (_t74) + (((_t75) * (_t75) + (_t76 * _t76)))) <= epsilon) {
                    dest.put(destOffset, ((_t98) * (_t69) + (_t99 * _t33)) * _t15_inv);
                    dest.put(destOffset + 1, ((_t98) * (_t71) + (_t99 * _t35)) * _t15_inv);
                    dest.put(destOffset + 2, ((_t98) * (_t70) + (_t99 * _t31)) * _t15_inv);
                } else {
                    dest.put(destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                    dest.put(destOffset + 1, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                    dest.put(destOffset + 2, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
                }
            } else {
                dest.put(destOffset, ((_t98) * (_t103) + (_t99 * _t33)) * _t15_inv);
                dest.put(destOffset + 1, ((_t98) * (_t105) + (_t99 * _t35)) * _t15_inv);
                dest.put(destOffset + 2, ((_t98) * (_t104) + (_t99 * _t31)) * _t15_inv);
            }
        } else {
            dest.put(destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((t) * (_othery - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((t) * (_otherz - _selfz) + (_selfz)));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer absolute_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer absolute_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.absolute(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.absolute_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer absolute_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, java.lang.Math.abs(src.get(srcOffset)));
        dest.put(destOffset + 1, java.lang.Math.abs(_selfy));
        dest.put(destOffset + 2, java.lang.Math.abs(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer acos_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer acos_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.acos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.acos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer acos_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.acos(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.acos(_selfy));
        dest.put(destOffset + 2, Math.acos(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, scalar);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, java.lang.Math.fma(scalar, bX, src.get(srcOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(scalar, bY, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(scalar, bZ, _selfz));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, ((scalar) * (bX) + (src.get(srcOffset))));
            dest.put(destOffset + 1, ((scalar) * (bY) + (_selfy)));
            dest.put(destOffset + 2, ((scalar) * (bZ) + (_selfz)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset * 8L;
        Double3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 3) {
            Double3Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, scalar);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, double scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _by = b.get(bOffset + 1);
            double _bz = b.get(bOffset + 2);
            dest.put(destOffset, java.lang.Math.fma(scalar, b.get(bOffset), src.get(srcOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(scalar, _by, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(scalar, _bz, _selfz));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _by = b.get(bOffset + 1);
            double _bz = b.get(bOffset + 2);
            dest.put(destOffset, ((scalar) * (b.get(bOffset)) + (src.get(srcOffset))));
            dest.put(destOffset + 1, ((scalar) * (_by) + (_selfy)));
            dest.put(destOffset + 2, ((scalar) * (_bz) + (_selfz)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, bX, bY, bZ, cX, cY, cZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, java.lang.Math.fma(bX, cX, src.get(srcOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(bY, cY, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(bZ, cZ, _selfz));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, ((bX) * (cX) + (src.get(srcOffset))));
            dest.put(destOffset + 1, ((bY) * (cY) + (_selfy)));
            dest.put(destOffset + 2, ((bZ) * (cZ) + (_selfz)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset * 8L;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset * 8L;
        Double3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && b.hasArray() && bOffset >= 0 && bOffset <= b.limit() - 3 && c.hasArray() && cOffset >= 0 && cOffset <= c.limit() - 3) {
            Double3Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, b.array(), b.arrayOffset() + bOffset, c.array(), c.arrayOffset() + cOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _by = b.get(bOffset + 1);
            double _bz = b.get(bOffset + 2);
            double _cy = c.get(cOffset + 1);
            double _cz = c.get(cOffset + 2);
            dest.put(destOffset, java.lang.Math.fma(b.get(bOffset), c.get(cOffset), src.get(srcOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(_by, _cy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(_bz, _cz, _selfz));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _by = b.get(bOffset + 1);
            double _bz = b.get(bOffset + 2);
            double _cy = c.get(cOffset + 1);
            double _cz = c.get(cOffset + 2);
            dest.put(destOffset, ((b.get(bOffset)) * (c.get(cOffset)) + (src.get(srcOffset))));
            dest.put(destOffset + 1, ((_by) * (_cy) + (_selfy)));
            dest.put(destOffset + 2, ((_bz) * (_cz) + (_selfz)));
            return dest;
        }
    }

    public static double angleBetween_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double angleBetween_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
        }
        return Double3OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, otherX, otherY, otherZ);
    }

    public static double angleBetween_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_apiGet_mulAdd(src, srcOffset, otherX, otherY, otherZ);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t3 = otherY * -_selfz;
        double _t4 = -(otherX * _selfy);
        double _t5 = otherX * -_selfz;
        double _t12 = java.lang.Math.fma(otherZ, _selfy, _t3) - java.lang.Math.fma(otherY, _selfz, _t3);
        double _t13 = java.lang.Math.fma(otherY, _selfx, _t4) - java.lang.Math.fma(otherX, _selfy, _t4);
        double _t14 = java.lang.Math.fma(otherZ, _selfx, _t5) - java.lang.Math.fma(otherX, _selfz, _t5);
        double _ct0 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ);
            return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ);
        }
        return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    /** {@code angleBetween_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_apiGet_mulAdd(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t3 = otherY * -_selfz;
        double _t4 = -(otherX * _selfy);
        double _t5 = otherX * -_selfz;
        double _t12 = ((otherZ) * (_selfy) + (_t3)) - ((otherY) * (_selfz) + (_t3));
        double _t13 = ((otherY) * (_selfx) + (_t4)) - ((otherX) * (_selfy) + (_t4));
        double _t14 = ((otherZ) * (_selfx) + (_t5)) - ((otherX) * (_selfz) + (_t5));
        double _ct0 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ);
            return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ);
        }
        return Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))));
    }

    public static double angleBetween_degenerate(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ);
    }

    public static double angleBetween_degenerate_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double angleBetween_degenerate_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
        }
        return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, otherX, otherY, otherZ);
    }

    public static double angleBetween_degenerate_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_apiGet_mulAdd(src, srcOffset, otherX, otherY, otherZ);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = otherZ * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = otherY * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = otherX * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        double _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        double _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        double _t29 = unitScale(_t27, _t28, _t26);
        double _t33 = _t26 * _t29;
        double _t34 = _t27 * _t29;
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /** {@code angleBetween_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = unitScale(otherX, otherY, otherZ);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = otherZ * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = otherY * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = otherX * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        double _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        double _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        double _t29 = unitScale(_t27, _t28, _t26);
        double _t33 = _t26 * _t29;
        double _t34 = _t27 * _t29;
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
    }

    public static double angleBetween_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return Double3OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            return Double3Ops.angleBetween(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Double3OpsKernelsTypedBuffer.angleBetween_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_apiGet_mulAdd(src, srcOffset, other, otherOffset);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t3 = _othery * -_selfz;
        double _t4 = -(_otherx * _selfy);
        double _t5 = _otherx * -_selfz;
        double _t12 = java.lang.Math.fma(_otherz, _selfy, _t3) - java.lang.Math.fma(_othery, _selfz, _t3);
        double _t13 = java.lang.Math.fma(_othery, _selfx, _t4) - java.lang.Math.fma(_otherx, _selfy, _t4);
        double _t14 = java.lang.Math.fma(_otherz, _selfx, _t5) - java.lang.Math.fma(_otherx, _selfz, _t5);
        double _ct0 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t13, _t13, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
            return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
        }
        return Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    /** {@code angleBetween_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_apiGet_mulAdd(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t3 = _othery * -_selfz;
        double _t4 = -(_otherx * _selfy);
        double _t5 = _otherx * -_selfz;
        double _t12 = ((_otherz) * (_selfy) + (_t3)) - ((_othery) * (_selfz) + (_t3));
        double _t13 = ((_othery) * (_selfx) + (_t4)) - ((_otherx) * (_selfy) + (_t4));
        double _t14 = ((_otherz) * (_selfx) + (_t5)) - ((_otherx) * (_selfz) + (_t5));
        double _ct0 = ((_t12) * (_t12) + (((_t13) * (_t13) + (_t14 * _t14))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
            return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
        }
        return Math.atan2(java.lang.Math.sqrt(_ct0), ((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))));
    }

    public static double angleBetween_degenerate(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_degenerate_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return Double3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static double angleBetween_degenerate_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            return Double3OpsKernelsArray.angleBetween_degenerate(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Double3OpsKernelsTypedBuffer.angleBetween_degenerate_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double angleBetween_degenerate_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_apiGet_mulAdd(src, srcOffset, other, otherOffset);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t0 = unitScale(_otherx, _othery, _otherz);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = _otherz * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = _othery * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = _otherx * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = java.lang.Math.fma(_t8, _t9, _t17) - java.lang.Math.fma(_t10, _t11, _t17);
        double _t27 = java.lang.Math.fma(_t10, _t12, _t18) - java.lang.Math.fma(_t13, _t9, _t18);
        double _t28 = java.lang.Math.fma(_t8, _t12, _t19) - java.lang.Math.fma(_t13, _t11, _t19);
        double _t29 = unitScale(_t27, _t28, _t26);
        double _t33 = _t26 * _t29;
        double _t34 = _t27 * _t29;
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35))), java.lang.Math.fma(_t8, _t11, java.lang.Math.fma(_t13, _t12, _t10 * _t9)) * _t29);
    }

    /** {@code angleBetween_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double angleBetween_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t0 = unitScale(_otherx, _othery, _otherz);
        double _t1 = unitScale(_selfx, _selfy, _selfz);
        double _t8 = _otherz * _t0;
        double _t9 = _selfy * _t1;
        double _t10 = _othery * _t0;
        double _t11 = _selfz * _t1;
        double _t12 = _selfx * _t1;
        double _t13 = _otherx * _t0;
        double _t17 = _t10 * -_t11;
        double _t18 = -(_t13 * _t9);
        double _t19 = _t13 * -_t11;
        double _t26 = ((_t8) * (_t9) + (_t17)) - ((_t10) * (_t11) + (_t17));
        double _t27 = ((_t10) * (_t12) + (_t18)) - ((_t13) * (_t9) + (_t18));
        double _t28 = ((_t8) * (_t12) + (_t19)) - ((_t13) * (_t11) + (_t19));
        double _t29 = unitScale(_t27, _t28, _t26);
        double _t33 = _t26 * _t29;
        double _t34 = _t27 * _t29;
        double _t35 = _t28 * _t29;
        return Math.atan2(java.lang.Math.sqrt(((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))))), ((_t8) * (_t11) + (((_t13) * (_t12) + (_t10 * _t9)))) * _t29);
    }

    public static java.nio.DoubleBuffer asin_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer asin_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.asin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.asin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer asin_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.asin(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.asin(_selfy));
        dest.put(destOffset + 2, Math.asin(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer atan_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer atan_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.atan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.atan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer atan_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.atan(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.atan(_selfy));
        dest.put(destOffset + 2, Math.atan(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double x) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double x) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.atan2(src.get(srcOffset), x));
        dest.put(destOffset + 1, Math.atan2(_selfy, x));
        dest.put(destOffset + 2, Math.atan2(_selfz, x));
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double xX, double xY, double xZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double xX, double xY, double xZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, xX, xY, xZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, xX, xY, xZ);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double xX, double xY, double xZ) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.atan2(src.get(srcOffset), xX));
        dest.put(destOffset + 1, Math.atan2(_selfy, xY));
        dest.put(destOffset + 2, Math.atan2(_selfz, xZ));
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + xOffset * 8L;
        Double3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer x, int xOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && x.hasArray() && xOffset >= 0 && xOffset <= x.limit() - 3) {
            Double3Ops.atan2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, x.array(), x.arrayOffset() + xOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.atan2_apiGet(dest, destOffset, src, srcOffset, x, xOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer atan2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer x, int xOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _xy = x.get(xOffset + 1);
        double _xz = x.get(xOffset + 2);
        dest.put(destOffset, Math.atan2(src.get(srcOffset), x.get(xOffset)));
        dest.put(destOffset + 1, Math.atan2(_selfy, _xy));
        dest.put(destOffset + 2, Math.atan2(_selfz, _xz));
        return dest;
    }

    public static java.nio.DoubleBuffer cbrt_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cbrt_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.cbrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.cbrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cbrt_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.cbrt(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.cbrt(_selfy));
        dest.put(destOffset + 2, Math.cbrt(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer ceil_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer ceil_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.ceil(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.ceil_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer ceil_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.ceil(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.ceil(_selfy));
        dest.put(destOffset + 2, Math.ceil(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double min, double max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double min, double max) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min, max);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, max);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double min, double max) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.min(Math.max(src.get(srcOffset), min), max));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, min), max));
        dest.put(destOffset + 2, Math.min(Math.max(_selfz, min), max));
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.min(Math.max(src.get(srcOffset), minX), maxX));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, minY), maxY));
        dest.put(destOffset + 2, Math.min(Math.max(_selfz, minZ), maxZ));
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer min, int minOffset, java.nio.DoubleBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + minOffset * 8L;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + maxOffset * 8L;
        Double3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer min, int minOffset, java.nio.DoubleBuffer max, int maxOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && min.hasArray() && minOffset >= 0 && minOffset <= min.limit() - 3 && max.hasArray() && maxOffset >= 0 && maxOffset <= max.limit() - 3) {
            Double3Ops.clamp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, min.array(), min.arrayOffset() + minOffset, max.array(), max.arrayOffset() + maxOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.clamp_apiGet(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer clamp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer min, int minOffset, java.nio.DoubleBuffer max, int maxOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _miny = min.get(minOffset + 1);
        double _minz = min.get(minOffset + 2);
        double _maxy = max.get(maxOffset + 1);
        double _maxz = max.get(maxOffset + 2);
        dest.put(destOffset, Math.min(Math.max(src.get(srcOffset), min.get(minOffset)), max.get(maxOffset)));
        dest.put(destOffset + 1, Math.min(Math.max(_selfy, _miny), _maxy));
        dest.put(destOffset + 2, Math.min(Math.max(_selfz, _minz), _maxz));
        return dest;
    }

    public static java.nio.DoubleBuffer closestPointOnLine_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return dest;
    }

    public static java.nio.DoubleBuffer closestPointOnLine_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.closestPointOnLine(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.closestPointOnLine_apiGet(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return dest;
    }

    public static java.nio.DoubleBuffer closestPointOnLine_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return closestPointOnLine_apiGet_mulAdd(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        double _t0 = lineEndZ - lineStartZ;
        double _t1 = lineEndX - lineStartX;
        double _t2 = lineEndY - lineStartY;
        double _t10 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        double _t14 = Math.max(0.0, Math.min(1.0, java.lang.Math.fma(_t0, src.get(srcOffset + 2) - lineStartZ, java.lang.Math.fma(_t1, src.get(srcOffset) - lineStartX, _t2 * (src.get(srcOffset + 1) - lineStartY))) / _t10));
        if (_t10 > 0.0) {
            dest.put(destOffset, java.lang.Math.fma(_t1, _t14, lineStartX));
            dest.put(destOffset + 1, java.lang.Math.fma(_t2, _t14, lineStartY));
            dest.put(destOffset + 2, java.lang.Math.fma(_t0, _t14, lineStartZ));
        } else {
            dest.put(destOffset, lineStartX);
            dest.put(destOffset + 1, lineStartY);
            dest.put(destOffset + 2, lineStartZ);
        }
        return dest;
    }

    /** {@code closestPointOnLine_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer closestPointOnLine_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        double _t0 = lineEndZ - lineStartZ;
        double _t1 = lineEndX - lineStartX;
        double _t2 = lineEndY - lineStartY;
        double _t10 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        double _t14 = Math.max(0.0, Math.min(1.0, ((_t0) * (src.get(srcOffset + 2) - lineStartZ) + (((_t1) * (src.get(srcOffset) - lineStartX) + (_t2 * (src.get(srcOffset + 1) - lineStartY))))) / _t10));
        if (_t10 > 0.0) {
            dest.put(destOffset, ((_t1) * (_t14) + (lineStartX)));
            dest.put(destOffset + 1, ((_t2) * (_t14) + (lineStartY)));
            dest.put(destOffset + 2, ((_t0) * (_t14) + (lineStartZ)));
        } else {
            dest.put(destOffset, lineStartX);
            dest.put(destOffset + 1, lineStartY);
            dest.put(destOffset + 2, lineStartZ);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer closestPointOnLine_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer lineStart, int lineStartOffset, java.nio.DoubleBuffer lineEnd, int lineEndOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _lineStartBase = UnsafeOpsHolder.U.getLong(lineStart, UnsafeCopy.BB_ADDRESS_OFFSET) + lineStartOffset * 8L;
        long _lineEndBase = UnsafeOpsHolder.U.getLong(lineEnd, UnsafeCopy.BB_ADDRESS_OFFSET) + lineEndOffset * 8L;
        Double3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, _lineStartBase, _lineEndBase);
        return dest;
    }

    public static java.nio.DoubleBuffer closestPointOnLine_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer lineStart, int lineStartOffset, java.nio.DoubleBuffer lineEnd, int lineEndOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && lineStart.hasArray() && lineStartOffset >= 0 && lineStartOffset <= lineStart.limit() - 3 && lineEnd.hasArray() && lineEndOffset >= 0 && lineEndOffset <= lineEnd.limit() - 3) {
            Double3Ops.closestPointOnLine(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, lineStart.array(), lineStart.arrayOffset() + lineStartOffset, lineEnd.array(), lineEnd.arrayOffset() + lineEndOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.closestPointOnLine_apiGet(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer closestPointOnLine_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer lineStart, int lineStartOffset, java.nio.DoubleBuffer lineEnd, int lineEndOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return closestPointOnLine_apiGet_mulAdd(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
        double _lineStartx = lineStart.get(lineStartOffset);
        double _lineStarty = lineStart.get(lineStartOffset + 1);
        double _lineStartz = lineStart.get(lineStartOffset + 2);
        double _t0 = lineEnd.get(lineEndOffset + 2) - _lineStartz;
        double _t1 = lineEnd.get(lineEndOffset) - _lineStartx;
        double _t2 = lineEnd.get(lineEndOffset + 1) - _lineStarty;
        double _t10 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        double _t14 = Math.max(0.0, Math.min(1.0, java.lang.Math.fma(_t0, src.get(srcOffset + 2) - _lineStartz, java.lang.Math.fma(_t1, src.get(srcOffset) - _lineStartx, _t2 * (src.get(srcOffset + 1) - _lineStarty))) / _t10));
        if (_t10 > 0.0) {
            dest.put(destOffset, java.lang.Math.fma(_t1, _t14, _lineStartx));
            dest.put(destOffset + 1, java.lang.Math.fma(_t2, _t14, _lineStarty));
            dest.put(destOffset + 2, java.lang.Math.fma(_t0, _t14, _lineStartz));
        } else {
            dest.put(destOffset, _lineStartx);
            dest.put(destOffset + 1, _lineStarty);
            dest.put(destOffset + 2, _lineStartz);
        }
        return dest;
    }

    /** {@code closestPointOnLine_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer closestPointOnLine_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer lineStart, int lineStartOffset, java.nio.DoubleBuffer lineEnd, int lineEndOffset) {
        double _lineStartx = lineStart.get(lineStartOffset);
        double _lineStarty = lineStart.get(lineStartOffset + 1);
        double _lineStartz = lineStart.get(lineStartOffset + 2);
        double _t0 = lineEnd.get(lineEndOffset + 2) - _lineStartz;
        double _t1 = lineEnd.get(lineEndOffset) - _lineStartx;
        double _t2 = lineEnd.get(lineEndOffset + 1) - _lineStarty;
        double _t10 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        double _t14 = Math.max(0.0, Math.min(1.0, ((_t0) * (src.get(srcOffset + 2) - _lineStartz) + (((_t1) * (src.get(srcOffset) - _lineStartx) + (_t2 * (src.get(srcOffset + 1) - _lineStarty))))) / _t10));
        if (_t10 > 0.0) {
            dest.put(destOffset, ((_t1) * (_t14) + (_lineStartx)));
            dest.put(destOffset + 1, ((_t2) * (_t14) + (_lineStarty)));
            dest.put(destOffset + 2, ((_t0) * (_t14) + (_lineStartz)));
        } else {
            dest.put(destOffset, _lineStartx);
            dest.put(destOffset + 1, _lineStarty);
            dest.put(destOffset + 2, _lineStartz);
        }
        return dest;
    }

    public static double compAdd_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static double compAdd_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.compAdd(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double3OpsKernelsTypedBuffer.compAdd_apiGet(src, srcOffset);
    }

    public static double compAdd_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        return src.get(srcOffset + 2) + (src.get(srcOffset) + src.get(srcOffset + 1));
    }

    public static double compMax_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static double compMax_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.compMax(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double3OpsKernelsTypedBuffer.compMax_apiGet(src, srcOffset);
    }

    public static double compMax_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        return Math.max(Math.max(src.get(srcOffset), src.get(srcOffset + 1)), src.get(srcOffset + 2));
    }

    public static double compMin_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static double compMin_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.compMin(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double3OpsKernelsTypedBuffer.compMin_apiGet(src, srcOffset);
    }

    public static double compMin_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        return Math.min(Math.min(src.get(srcOffset), src.get(srcOffset + 1)), src.get(srcOffset + 2));
    }

    public static double compMul_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static double compMul_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.compMul(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double3OpsKernelsTypedBuffer.compMul_apiGet(src, srcOffset);
    }

    public static double compMul_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        return src.get(srcOffset + 2) * src.get(srcOffset) * src.get(srcOffset + 1);
    }

    public static java.nio.DoubleBuffer copySign_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sign) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sign) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.copySign(src.get(srcOffset), sign));
        dest.put(destOffset + 1, Math.copySign(_selfy, sign));
        dest.put(destOffset + 2, Math.copySign(_selfz, sign));
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double signX, double signY, double signZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double signX, double signY, double signZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, signX, signY, signZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, signX, signY, signZ);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double signX, double signY, double signZ) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.copySign(src.get(srcOffset), signX));
        dest.put(destOffset + 1, Math.copySign(_selfy, signY));
        dest.put(destOffset + 2, Math.copySign(_selfz, signZ));
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + signOffset * 8L;
        Double3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer sign, int signOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && sign.hasArray() && signOffset >= 0 && signOffset <= sign.limit() - 3) {
            Double3Ops.copySign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sign.array(), sign.arrayOffset() + signOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.copySign_apiGet(dest, destOffset, src, srcOffset, sign, signOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer copySign_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer sign, int signOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _signy = sign.get(signOffset + 1);
        double _signz = sign.get(signOffset + 2);
        dest.put(destOffset, Math.copySign(src.get(srcOffset), sign.get(signOffset)));
        dest.put(destOffset + 1, Math.copySign(_selfy, _signy));
        dest.put(destOffset + 2, Math.copySign(_selfz, _signz));
        return dest;
    }

    public static java.nio.DoubleBuffer cos_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cos_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.cos(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.cos_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cos_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.cos(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.cos(_selfy));
        dest.put(destOffset + 2, Math.cos(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer cosh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cosh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.cosh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.cosh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cosh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.cosh(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.cosh(_selfy));
        dest.put(destOffset + 2, Math.cosh(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer cross_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer cross_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.cross(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.cross_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer cross_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t3 = -(otherY * _selfz);
            double _t4 = -(otherZ * _selfx);
            double _t5 = -(otherX * _selfy);
            dest.put(destOffset, java.lang.Math.fma(otherZ, _selfy, _t3) - java.lang.Math.fma(otherY, _selfz, _t3));
            dest.put(destOffset + 1, java.lang.Math.fma(otherX, _selfz, _t4) - java.lang.Math.fma(otherZ, _selfx, _t4));
            dest.put(destOffset + 2, java.lang.Math.fma(otherY, _selfx, _t5) - java.lang.Math.fma(otherX, _selfy, _t5));
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t3 = -(otherY * _selfz);
            double _t4 = -(otherZ * _selfx);
            double _t5 = -(otherX * _selfy);
            dest.put(destOffset, ((otherZ) * (_selfy) + (_t3)) - ((otherY) * (_selfz) + (_t3)));
            dest.put(destOffset + 1, ((otherX) * (_selfz) + (_t4)) - ((otherZ) * (_selfx) + (_t4)));
            dest.put(destOffset + 2, ((otherY) * (_selfx) + (_t5)) - ((otherX) * (_selfy) + (_t5)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer cross_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer cross_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Double3Ops.cross(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.cross_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer cross_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return cross_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t3 = -(_othery * _selfz);
        double _t4 = -(_otherz * _selfx);
        double _t5 = -(_otherx * _selfy);
        dest.put(destOffset, java.lang.Math.fma(_otherz, _selfy, _t3) - java.lang.Math.fma(_othery, _selfz, _t3));
        dest.put(destOffset + 1, java.lang.Math.fma(_otherx, _selfz, _t4) - java.lang.Math.fma(_otherz, _selfx, _t4));
        dest.put(destOffset + 2, java.lang.Math.fma(_othery, _selfx, _t5) - java.lang.Math.fma(_otherx, _selfy, _t5));
        return dest;
    }

    /** {@code cross_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer cross_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _t3 = -(_othery * _selfz);
        double _t4 = -(_otherz * _selfx);
        double _t5 = -(_otherx * _selfy);
        dest.put(destOffset, ((_otherz) * (_selfy) + (_t3)) - ((_othery) * (_selfz) + (_t3)));
        dest.put(destOffset + 1, ((_otherx) * (_selfz) + (_t4)) - ((_otherz) * (_selfx) + (_t4)));
        dest.put(destOffset + 2, ((_othery) * (_selfx) + (_t5)) - ((_otherx) * (_selfy) + (_t5)));
        return dest;
    }

    public static java.nio.DoubleBuffer degrees_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer degrees_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.degrees(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.degrees_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer degrees_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.toDegrees(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.toDegrees(_selfy));
        dest.put(destOffset + 2, Math.toDegrees(_selfz));
        return dest;
    }

    public static double distance_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double distance_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.distance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
        }
        return Double3OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, otherX, otherY, otherZ);
    }

    public static double distance_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = src.get(srcOffset + 2) - otherZ;
            double _t1 = src.get(srcOffset) - otherX;
            double _t2 = src.get(srcOffset + 1) - otherY;
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            double _t0 = src.get(srcOffset + 2) - otherZ;
            double _t1 = src.get(srcOffset) - otherX;
            double _t2 = src.get(srcOffset + 1) - otherY;
            return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
        }
    }

    public static double distance_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return Double3OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static double distance_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            return Double3Ops.distance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Double3OpsKernelsTypedBuffer.distance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double distance_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = src.get(srcOffset + 2) - other.get(otherOffset + 2);
            double _t1 = src.get(srcOffset) - other.get(otherOffset);
            double _t2 = src.get(srcOffset + 1) - other.get(otherOffset + 1);
            return java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2)));
        } else {
            double _t0 = src.get(srcOffset + 2) - other.get(otherOffset + 2);
            double _t1 = src.get(srcOffset) - other.get(otherOffset);
            double _t2 = src.get(srcOffset + 1) - other.get(otherOffset + 1);
            return java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2)))));
        }
    }

    public static double distanceSquared_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double distanceSquared_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
        }
        return Double3OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, otherX, otherY, otherZ);
    }

    public static double distanceSquared_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = src.get(srcOffset + 2) - otherZ;
            double _t1 = src.get(srcOffset) - otherX;
            double _t2 = src.get(srcOffset + 1) - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            double _t0 = src.get(srcOffset + 2) - otherZ;
            double _t1 = src.get(srcOffset) - otherX;
            double _t2 = src.get(srcOffset + 1) - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        }
    }

    public static double distanceSquared_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return Double3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static double distanceSquared_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            return Double3Ops.distanceSquared(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Double3OpsKernelsTypedBuffer.distanceSquared_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double distanceSquared_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = src.get(srcOffset + 2) - other.get(otherOffset + 2);
            double _t1 = src.get(srcOffset) - other.get(otherOffset);
            double _t2 = src.get(srcOffset + 1) - other.get(otherOffset + 1);
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        } else {
            double _t0 = src.get(srcOffset + 2) - other.get(otherOffset + 2);
            double _t1 = src.get(srcOffset) - other.get(otherOffset);
            double _t2 = src.get(srcOffset + 1) - other.get(otherOffset + 1);
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        }
    }

    public static double dot_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double dot_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.dot(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
        }
        return Double3OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, otherX, otherY, otherZ);
    }

    public static double dot_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherZ, src.get(srcOffset + 2), java.lang.Math.fma(otherX, src.get(srcOffset), otherY * src.get(srcOffset + 1)));
        } else {
            return ((otherZ) * (src.get(srcOffset + 2)) + (((otherX) * (src.get(srcOffset)) + (otherY * src.get(srcOffset + 1)))));
        }
    }

    public static double dot_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return Double3OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static double dot_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            return Double3Ops.dot(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Double3OpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double dot_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(other.get(otherOffset + 2), src.get(srcOffset + 2), java.lang.Math.fma(other.get(otherOffset), src.get(srcOffset), other.get(otherOffset + 1) * src.get(srcOffset + 1)));
        } else {
            return ((other.get(otherOffset + 2)) * (src.get(srcOffset + 2)) + (((other.get(otherOffset)) * (src.get(srcOffset)) + (other.get(otherOffset + 1) * src.get(srcOffset + 1)))));
        }
    }

    public static java.nio.DoubleBuffer exp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer exp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.exp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.exp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer exp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.exp(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.exp(_selfy));
        dest.put(destOffset + 2, Math.exp(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer exp2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer exp2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.exp2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.exp2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer exp2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.pow(2.0, src.get(srcOffset)));
        dest.put(destOffset + 1, Math.pow(2.0, _selfy));
        dest.put(destOffset + 2, Math.pow(2.0, _selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer expm1_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer expm1_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.expm1(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.expm1_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer expm1_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.expm1(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.expm1(_selfy));
        dest.put(destOffset + 2, Math.expm1(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, IX, IY, IZ, NrefX, NrefY, NrefZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t3 = java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY)) < 0.0 ? 1.0 : -1.0;
            dest.put(destOffset, src.get(srcOffset) * _t3);
            dest.put(destOffset + 1, _selfy * _t3);
            dest.put(destOffset + 2, _selfz * _t3);
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t3 = ((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))) < 0.0 ? 1.0 : -1.0;
            dest.put(destOffset, src.get(srcOffset) * _t3);
            dest.put(destOffset + 1, _selfy * _t3);
            dest.put(destOffset + 2, _selfz * _t3);
            return dest;
        }
    }

    public static java.nio.DoubleBuffer faceforward_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer I, int IOffset, java.nio.DoubleBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + IOffset * 8L;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + NrefOffset * 8L;
        Double3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer I, int IOffset, java.nio.DoubleBuffer Nref, int NrefOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && I.hasArray() && IOffset >= 0 && IOffset <= I.limit() - 3 && Nref.hasArray() && NrefOffset >= 0 && NrefOffset <= Nref.limit() - 3) {
            Double3Ops.faceforward(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, I.array(), I.arrayOffset() + IOffset, Nref.array(), Nref.arrayOffset() + NrefOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.faceforward_apiGet(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer faceforward_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer I, int IOffset, java.nio.DoubleBuffer Nref, int NrefOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_apiGet_mulAdd(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t3 = java.lang.Math.fma(I.get(IOffset + 2), Nref.get(NrefOffset + 2), java.lang.Math.fma(I.get(IOffset), Nref.get(NrefOffset), I.get(IOffset + 1) * Nref.get(NrefOffset + 1))) < 0.0 ? 1.0 : -1.0;
        dest.put(destOffset, src.get(srcOffset) * _t3);
        dest.put(destOffset + 1, _selfy * _t3);
        dest.put(destOffset + 2, _selfz * _t3);
        return dest;
    }

    /** {@code faceforward_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer faceforward_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer I, int IOffset, java.nio.DoubleBuffer Nref, int NrefOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t3 = ((I.get(IOffset + 2)) * (Nref.get(NrefOffset + 2)) + (((I.get(IOffset)) * (Nref.get(NrefOffset)) + (I.get(IOffset + 1) * Nref.get(NrefOffset + 1))))) < 0.0 ? 1.0 : -1.0;
        dest.put(destOffset, src.get(srcOffset) * _t3);
        dest.put(destOffset + 1, _selfy * _t3);
        dest.put(destOffset + 2, _selfz * _t3);
        return dest;
    }

    public static java.nio.DoubleBuffer floor_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer floor_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.floor(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.floor_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer floor_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.floor(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.floor(_selfy));
        dest.put(destOffset + 2, Math.floor(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer fract_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer fract_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.fract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.fract_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer fract_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999));
        dest.put(destOffset + 1, Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999));
        dest.put(destOffset + 2, Math.min(_selfz - Math.floor(_selfz), 0.9999999999999999));
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.hypot(src.get(srcOffset), y));
        dest.put(destOffset + 1, Math.hypot(_selfy, y));
        dest.put(destOffset + 2, Math.hypot(_selfz, y));
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY, yZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, yX, yY, yZ);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.hypot(src.get(srcOffset), yX));
        dest.put(destOffset + 1, Math.hypot(_selfy, yY));
        dest.put(destOffset + 2, Math.hypot(_selfz, yZ));
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset * 8L;
        Double3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 3) {
            Double3Ops.hypot(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.hypot_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer hypot_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _yy = y.get(yOffset + 1);
        double _yz = y.get(yOffset + 2);
        dest.put(destOffset, Math.hypot(src.get(srcOffset), y.get(yOffset)));
        dest.put(destOffset + 1, Math.hypot(_selfy, _yy));
        dest.put(destOffset + 2, Math.hypot(_selfz, _yz));
        return dest;
    }

    public static java.nio.DoubleBuffer inverse_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer inverse_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.inverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.inverse_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer inverse_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, 1.0 / src.get(srcOffset));
        dest.put(destOffset + 1, 1.0 / _selfy);
        dest.put(destOffset + 2, 1.0 / _selfz);
        return dest;
    }

    public static java.nio.DoubleBuffer inverseSqrt_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer inverseSqrt_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.inverseSqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.inverseSqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer inverseSqrt_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, (1.0 / java.lang.Math.sqrt(src.get(srcOffset))));
        dest.put(destOffset + 1, (1.0 / java.lang.Math.sqrt(_selfy)));
        dest.put(destOffset + 2, (1.0 / java.lang.Math.sqrt(_selfz)));
        return dest;
    }

    public static double length_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static double length_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.length(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double3OpsKernelsTypedBuffer.length_apiGet(src, srcOffset);
    }

    public static double length_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            return java.lang.Math.sqrt(java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            return java.lang.Math.sqrt(((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        }
    }

    public static double lengthSquared_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static double lengthSquared_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.lengthSquared(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double3OpsKernelsTypedBuffer.lengthSquared_apiGet(src, srcOffset);
    }

    public static double lengthSquared_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            return java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            return ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        }
    }

    public static java.nio.DoubleBuffer log_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.log(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.log_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.log(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log(_selfy));
        dest.put(destOffset + 2, Math.log(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer log10_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log10_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.log10(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.log10_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log10_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.log10(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log10(_selfy));
        dest.put(destOffset + 2, Math.log10(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer log1p_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log1p_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.log1p(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.log1p_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log1p_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.log1p(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log1p(_selfy));
        dest.put(destOffset + 2, Math.log1p(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer log2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.log2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.log2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.log2(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.log2(_selfy));
        dest.put(destOffset + 2, Math.log2(_selfz));
        return dest;
    }

    public static double manhattanDistance_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static double manhattanDistance_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
        }
        return Double3OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, otherX, otherY, otherZ);
    }

    public static double manhattanDistance_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        return java.lang.Math.abs(src.get(srcOffset) - otherX) + java.lang.Math.abs(src.get(srcOffset + 1) - otherY) + java.lang.Math.abs(src.get(srcOffset + 2) - otherZ);
    }

    public static double manhattanDistance_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return Double3OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static double manhattanDistance_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            return Double3Ops.manhattanDistance(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return Double3OpsKernelsTypedBuffer.manhattanDistance_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double manhattanDistance_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        return java.lang.Math.abs(src.get(srcOffset) - other.get(otherOffset)) + java.lang.Math.abs(src.get(srcOffset + 1) - other.get(otherOffset + 1)) + java.lang.Math.abs(src.get(srcOffset + 2) - other.get(otherOffset + 2));
    }

    public static double manhattanLength_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static double manhattanLength_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.manhattanLength(src.array(), src.arrayOffset() + srcOffset);
        }
        return Double3OpsKernelsTypedBuffer.manhattanLength_apiGet(src, srcOffset);
    }

    public static double manhattanLength_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        return java.lang.Math.abs(src.get(srcOffset)) + java.lang.Math.abs(src.get(srcOffset + 1)) + java.lang.Math.abs(src.get(srcOffset + 2));
    }

    public static java.nio.DoubleBuffer max_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer max_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer max_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.max(src.get(srcOffset), scalar));
        dest.put(destOffset + 1, Math.max(_selfy, scalar));
        dest.put(destOffset + 2, Math.max(_selfz, scalar));
        return dest;
    }

    public static java.nio.DoubleBuffer max_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer max_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer max_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.max(src.get(srcOffset), otherX));
        dest.put(destOffset + 1, Math.max(_selfy, otherY));
        dest.put(destOffset + 2, Math.max(_selfz, otherZ));
        return dest;
    }

    public static java.nio.DoubleBuffer max_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer max_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Double3Ops.max(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.max_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer max_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        dest.put(destOffset, Math.max(src.get(srcOffset), other.get(otherOffset)));
        dest.put(destOffset + 1, Math.max(_selfy, _othery));
        dest.put(destOffset + 2, Math.max(_selfz, _otherz));
        return dest;
    }

    public static java.nio.DoubleBuffer min_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer min_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer min_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.min(src.get(srcOffset), scalar));
        dest.put(destOffset + 1, Math.min(_selfy, scalar));
        dest.put(destOffset + 2, Math.min(_selfz, scalar));
        return dest;
    }

    public static java.nio.DoubleBuffer min_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer min_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.DoubleBuffer min_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.min(src.get(srcOffset), otherX));
        dest.put(destOffset + 1, Math.min(_selfy, otherY));
        dest.put(destOffset + 2, Math.min(_selfz, otherZ));
        return dest;
    }

    public static java.nio.DoubleBuffer min_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer min_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3) {
            Double3Ops.min(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.min_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer min_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        dest.put(destOffset, Math.min(src.get(srcOffset), other.get(otherOffset)));
        dest.put(destOffset + 1, Math.min(_selfy, _othery));
        dest.put(destOffset + 2, Math.min(_selfz, _otherz));
        return dest;
    }

    public static java.nio.DoubleBuffer mod_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, flooredMod(_selfx, y));
        dest.put(destOffset + 1, flooredMod(_selfy, y));
        dest.put(destOffset + 2, flooredMod(_selfz, y));
        return dest;
    }

    public static java.nio.DoubleBuffer mod_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, yX, yY, yZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, yX, yY, yZ);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, flooredMod(_selfx, yX));
        dest.put(destOffset + 1, flooredMod(_selfy, yY));
        dest.put(destOffset + 2, flooredMod(_selfz, yZ));
        return dest;
    }

    public static java.nio.DoubleBuffer mod_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset * 8L;
        Double3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && y.hasArray() && yOffset >= 0 && yOffset <= y.limit() - 3) {
            Double3Ops.mod(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, y.array(), y.arrayOffset() + yOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.mod_apiGet(dest, destOffset, src, srcOffset, y, yOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mod_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _yx = y.get(yOffset);
        double _yy = y.get(yOffset + 1);
        double _yz = y.get(yOffset + 2);
        dest.put(destOffset, flooredMod(_selfx, _yx));
        dest.put(destOffset + 1, flooredMod(_selfy, _yy));
        dest.put(destOffset + 2, flooredMod(_selfz, _yz));
        return dest;
    }

    public static java.nio.DoubleBuffer nextDown_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer nextDown_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.nextDown(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.nextDown_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer nextDown_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.nextDown(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.nextDown(_selfy));
        dest.put(destOffset + 2, Math.nextDown(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer nextUp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer nextUp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.nextUp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.nextUp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer nextUp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.nextUp(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.nextUp(_selfy));
        dest.put(destOffset + 2, Math.nextUp(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer normalize_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalize_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.normalize(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.normalize_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalize_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                dest.put(destOffset, _selfx * _t3);
                dest.put(destOffset + 1, _selfy * _t3);
                dest.put(destOffset + 2, _selfz * _t3);
            } else {
                dest.put(destOffset, 0.0);
                dest.put(destOffset + 1, 0.0);
                dest.put(destOffset + 2, 0.0);
            }
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
            double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                dest.put(destOffset, _selfx * _t3);
                dest.put(destOffset + 1, _selfy * _t3);
                dest.put(destOffset + 2, _selfz * _t3);
            } else {
                dest.put(destOffset, 0.0);
                dest.put(destOffset + 1, 0.0);
                dest.put(destOffset + 2, 0.0);
            }
            return dest;
        }
    }

    public static java.nio.DoubleBuffer normalizeMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizeMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double length) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.normalizeMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, length);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.normalizeMul_apiGet(dest, destOffset, src, srcOffset, length);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizeMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double length) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
            double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                dest.put(destOffset, _selfx * _t4);
                dest.put(destOffset + 1, _selfy * _t4);
                dest.put(destOffset + 2, _selfz * _t4);
            } else {
                dest.put(destOffset, 0.0);
                dest.put(destOffset + 1, 0.0);
                dest.put(destOffset + 2, 0.0);
            }
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
            double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
            if (_t2 != 0.0) {
                dest.put(destOffset, _selfx * _t4);
                dest.put(destOffset + 1, _selfy * _t4);
                dest.put(destOffset + 2, _selfz * _t4);
            } else {
                dest.put(destOffset, 0.0);
                dest.put(destOffset + 1, 0.0);
                dest.put(destOffset + 2, 0.0);
            }
            return dest;
        }
    }

    public static double orientedAngle_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static double orientedAngle_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3Ops.orientedAngle(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        }
        return Double3OpsKernelsTypedBuffer.orientedAngle_apiGet(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static double orientedAngle_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_apiGet_fma(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return orientedAngle_apiGet_mulAdd(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@code orientedAngle_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_apiGet_fma(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t4 = -(otherX * _selfy);
        double _t5 = -(otherZ * _selfx);
        double _t6 = -(otherY * _selfz);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t16 = java.lang.Math.fma(otherY, _selfx, _t4) - java.lang.Math.fma(otherX, _selfy, _t4);
        double _t17 = java.lang.Math.fma(otherX, _selfz, _t5) - java.lang.Math.fma(otherZ, _selfx, _t5);
        double _t18 = java.lang.Math.fma(otherZ, _selfy, _t6) - java.lang.Math.fma(otherY, _selfz, _t6);
        double _ct0 = java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t17, _t17, _t18 * _t18));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
            return Double3OpsKernelsTypedBuffer.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        }
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy)));
        return java.lang.Math.fma(_t16, normalZ * _t7, java.lang.Math.fma(_t17, normalY * _t7, _t18 * (normalX * _t7))) < 0.0 ? -_t24 : _t24;
    }

    /** {@code orientedAngle_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_apiGet_mulAdd(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t4 = -(otherX * _selfy);
        double _t5 = -(otherZ * _selfx);
        double _t6 = -(otherY * _selfz);
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t16 = ((otherY) * (_selfx) + (_t4)) - ((otherX) * (_selfy) + (_t4));
        double _t17 = ((otherX) * (_selfz) + (_t5)) - ((otherZ) * (_selfx) + (_t5));
        double _t18 = ((otherZ) * (_selfy) + (_t6)) - ((otherY) * (_selfz) + (_t6));
        double _ct0 = ((_t16) * (_t16) + (((_t17) * (_t17) + (_t18 * _t18))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
            return Double3OpsKernelsTypedBuffer.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        }
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), ((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))));
        return ((_t16) * (normalZ * _t7) + (((_t17) * (normalY * _t7) + (_t18 * (normalX * _t7))))) < 0.0 ? -_t24 : _t24;
    }

    public static double orientedAngle_degenerate(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return Double3OpsKernelsTypedBuffer.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static double orientedAngle_degenerate_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static double orientedAngle_degenerate_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            return Double3OpsKernelsArray.orientedAngle_degenerate(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        }
        return Double3OpsKernelsTypedBuffer.orientedAngle_degenerate_apiGet(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static double orientedAngle_degenerate_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_apiGet_fma(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return orientedAngle_degenerate_apiGet_mulAdd(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@code orientedAngle_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_apiGet_fma(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = unitScale(normalX, normalY, normalZ);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherY * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = otherX * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = _selfz * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        double _t21 = -(_t13 * _t10);
        double _t30 = java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19);
        double _t31 = java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20);
        double _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        double _t33 = unitScale(_t31, _t32, _t30);
        double _t39 = _t30 * _t33;
        double _t40 = _t31 * _t33;
        double _t41 = _t32 * _t33;
        double _t46 = Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(normalZ * _t0, _t39, java.lang.Math.fma(normalX * _t0, _t40, normalY * _t0 * _t41)) < 0.0 ? -_t46 : _t46;
    }

    /** {@code orientedAngle_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = unitScale(normalX, normalY, normalZ);
        double _t1 = unitScale(otherX, otherY, otherZ);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = otherY * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = otherX * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = otherZ * _t1;
        double _t14 = _selfz * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        double _t21 = -(_t13 * _t10);
        double _t30 = ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19));
        double _t31 = ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20));
        double _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        double _t33 = unitScale(_t31, _t32, _t30);
        double _t39 = _t30 * _t33;
        double _t40 = _t31 * _t33;
        double _t41 = _t32 * _t33;
        double _t46 = Math.atan2(java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((normalZ * _t0) * (_t39) + (((normalX * _t0) * (_t40) + (normalY * _t0 * _t41)))) < 0.0 ? -_t46 : _t46;
    }

    public static double orientedAngle_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 8L;
        return Double3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static double orientedAngle_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            return Double3Ops.orientedAngle(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, normal.array(), normal.arrayOffset() + normalOffset);
        }
        return Double3OpsKernelsTypedBuffer.orientedAngle_apiGet(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    public static double orientedAngle_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_apiGet_fma(src, srcOffset, other, otherOffset, normal, normalOffset);
        return orientedAngle_apiGet_mulAdd(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    /** {@code orientedAngle_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_apiGet_fma(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _normalx = normal.get(normalOffset);
        double _normaly = normal.get(normalOffset + 1);
        double _normalz = normal.get(normalOffset + 2);
        double _t4 = -(_otherx * _selfy);
        double _t5 = -(_otherz * _selfx);
        double _t6 = -(_othery * _selfz);
        double _t7 = unitScale(_normalx, _normaly, _normalz);
        double _t16 = java.lang.Math.fma(_othery, _selfx, _t4) - java.lang.Math.fma(_otherx, _selfy, _t4);
        double _t17 = java.lang.Math.fma(_otherx, _selfz, _t5) - java.lang.Math.fma(_otherz, _selfx, _t5);
        double _t18 = java.lang.Math.fma(_otherz, _selfy, _t6) - java.lang.Math.fma(_othery, _selfz, _t6);
        double _ct0 = java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t17, _t17, _t18 * _t18));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.orientedAngle_degenerate(src, srcOffset, other, otherOffset, normal, normalOffset);
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy)));
        return java.lang.Math.fma(_t16, _normalz * _t7, java.lang.Math.fma(_t17, _normaly * _t7, _t18 * (_normalx * _t7))) < 0.0 ? -_t24 : _t24;
    }

    /** {@code orientedAngle_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_apiGet_mulAdd(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _normalx = normal.get(normalOffset);
        double _normaly = normal.get(normalOffset + 1);
        double _normalz = normal.get(normalOffset + 2);
        double _t4 = -(_otherx * _selfy);
        double _t5 = -(_otherz * _selfx);
        double _t6 = -(_othery * _selfz);
        double _t7 = unitScale(_normalx, _normaly, _normalz);
        double _t16 = ((_othery) * (_selfx) + (_t4)) - ((_otherx) * (_selfy) + (_t4));
        double _t17 = ((_otherx) * (_selfz) + (_t5)) - ((_otherz) * (_selfx) + (_t5));
        double _t18 = ((_otherz) * (_selfy) + (_t6)) - ((_othery) * (_selfz) + (_t6));
        double _ct0 = ((_t16) * (_t16) + (((_t17) * (_t17) + (_t18 * _t18))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.orientedAngle_degenerate(src, srcOffset, other, otherOffset, normal, normalOffset);
        double _t24 = Math.atan2(java.lang.Math.sqrt(_ct0), ((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))));
        return ((_t16) * (_normalz * _t7) + (((_t17) * (_normaly * _t7) + (_t18 * (_normalx * _t7))))) < 0.0 ? -_t24 : _t24;
    }

    public static double orientedAngle_degenerate(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, other, otherOffset, normal, normalOffset);
        return Double3OpsKernelsTypedBuffer.orientedAngle_degenerate_api(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    public static double orientedAngle_degenerate_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 8L;
        return Double3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static double orientedAngle_degenerate_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 3 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            return Double3OpsKernelsArray.orientedAngle_degenerate(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, normal.array(), normal.arrayOffset() + normalOffset);
        }
        return Double3OpsKernelsTypedBuffer.orientedAngle_degenerate_apiGet(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    public static double orientedAngle_degenerate_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return orientedAngle_degenerate_apiGet_fma(src, srcOffset, other, otherOffset, normal, normalOffset);
        return orientedAngle_degenerate_apiGet_mulAdd(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    /** {@code orientedAngle_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_apiGet_fma(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _normalx = normal.get(normalOffset);
        double _normaly = normal.get(normalOffset + 1);
        double _normalz = normal.get(normalOffset + 2);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _othery * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = _otherx * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = _otherz * _t1;
        double _t14 = _selfz * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        double _t21 = -(_t13 * _t10);
        double _t30 = java.lang.Math.fma(_t9, _t10, _t19) - java.lang.Math.fma(_t11, _t12, _t19);
        double _t31 = java.lang.Math.fma(_t13, _t12, _t20) - java.lang.Math.fma(_t9, _t14, _t20);
        double _t32 = java.lang.Math.fma(_t11, _t14, _t21) - java.lang.Math.fma(_t13, _t10, _t21);
        double _t33 = unitScale(_t31, _t32, _t30);
        return orientedAngle_degenerate_apiGet_fma_s8ac6f5dc_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, _t33, _t30 * _t33, _t31 * _t33, _t32 * _t33);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_degenerate_apiGet_fma_s8ac6f5dc_1(double _normalx, double _normaly, double _normalz, double _t0, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t33, double _t39, double _t40, double _t41) {
        double _t46 = Math.atan2(java.lang.Math.sqrt(java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t41, _t41, _t40 * _t40))), java.lang.Math.fma(_t13, _t14, java.lang.Math.fma(_t11, _t10, _t9 * _t12)) * _t33);
        return java.lang.Math.fma(_normalz * _t0, _t39, java.lang.Math.fma(_normalx * _t0, _t40, _normaly * _t0 * _t41)) < 0.0 ? -_t46 : _t46;
    }

    /** {@code orientedAngle_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static double orientedAngle_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _normalx = normal.get(normalOffset);
        double _normaly = normal.get(normalOffset + 1);
        double _normalz = normal.get(normalOffset + 2);
        double _t1 = unitScale(_otherx, _othery, _otherz);
        double _t2 = unitScale(_selfx, _selfy, _selfz);
        double _t9 = _othery * _t1;
        double _t10 = _selfx * _t2;
        double _t11 = _otherx * _t1;
        double _t12 = _selfy * _t2;
        double _t13 = _otherz * _t1;
        double _t14 = _selfz * _t2;
        double _t19 = -(_t11 * _t12);
        double _t20 = -(_t9 * _t14);
        double _t21 = -(_t13 * _t10);
        double _t30 = ((_t9) * (_t10) + (_t19)) - ((_t11) * (_t12) + (_t19));
        double _t31 = ((_t13) * (_t12) + (_t20)) - ((_t9) * (_t14) + (_t20));
        double _t32 = ((_t11) * (_t14) + (_t21)) - ((_t13) * (_t10) + (_t21));
        double _t33 = unitScale(_t31, _t32, _t30);
        return orientedAngle_degenerate_apiGet_mulAdd_s48394e21_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, _t33, _t30 * _t33, _t31 * _t33, _t32 * _t33);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double orientedAngle_degenerate_apiGet_mulAdd_s48394e21_1(double _normalx, double _normaly, double _normalz, double _t0, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t33, double _t39, double _t40, double _t41) {
        double _t46 = Math.atan2(java.lang.Math.sqrt(((_t39) * (_t39) + (((_t41) * (_t41) + (_t40 * _t40))))), ((_t13) * (_t14) + (((_t11) * (_t10) + (_t9 * _t12)))) * _t33);
        return ((_normalz * _t0) * (_t39) + (((_normalx * _t0) * (_t40) + (_normaly * _t0 * _t41)))) < 0.0 ? -_t46 : _t46;
    }

    public static java.nio.DoubleBuffer outerProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rowX, double rowY, double rowZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rowX, double rowY, double rowZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rowX, rowY, rowZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rowX, double rowY, double rowZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, rowX * _selfx);
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

    public static java.nio.DoubleBuffer outerProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset * 8L;
        Double3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer row, int rowOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && row.hasArray() && rowOffset >= 0 && rowOffset <= row.limit() - 3) {
            Double3Ops.outerProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row.array(), row.arrayOffset() + rowOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.outerProduct_apiGet(dest, destOffset, src, srcOffset, row, rowOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer outerProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer row, int rowOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _rowx = row.get(rowOffset);
        double _rowy = row.get(rowOffset + 1);
        double _rowz = row.get(rowOffset + 2);
        dest.put(destOffset, _rowx * _selfx);
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

    public static java.nio.DoubleBuffer perpendicular_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.perpendicular_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer perpendicular_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.perpendicular(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.perpendicular_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer perpendicular_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        if (java.lang.Math.abs(_selfz) < java.lang.Math.abs(_selfx)) {
            dest.put(destOffset, _selfy);
            dest.put(destOffset + 1, -_selfx);
            dest.put(destOffset + 2, 0.0);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, _selfz);
            dest.put(destOffset + 2, -_selfy);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer pow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponent) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponent) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.pow(src.get(srcOffset), exponent));
        dest.put(destOffset + 1, Math.pow(_selfy, exponent));
        dest.put(destOffset + 2, Math.pow(_selfz, exponent));
        return dest;
    }

    public static java.nio.DoubleBuffer pow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponentX, double exponentY, double exponentZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponentX, double exponentY, double exponentZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponentX, exponentY, exponentZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponentX, double exponentY, double exponentZ) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.pow(src.get(srcOffset), exponentX));
        dest.put(destOffset + 1, Math.pow(_selfy, exponentY));
        dest.put(destOffset + 2, Math.pow(_selfz, exponentZ));
        return dest;
    }

    public static java.nio.DoubleBuffer pow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + exponentOffset * 8L;
        Double3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer exponent, int exponentOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && exponent.hasArray() && exponentOffset >= 0 && exponentOffset <= exponent.limit() - 3) {
            Double3Ops.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, exponent.array(), exponent.arrayOffset() + exponentOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer exponent, int exponentOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _exponenty = exponent.get(exponentOffset + 1);
        double _exponentz = exponent.get(exponentOffset + 2);
        dest.put(destOffset, Math.pow(src.get(srcOffset), exponent.get(exponentOffset)));
        dest.put(destOffset + 1, Math.pow(_selfy, _exponenty));
        dest.put(destOffset + 2, Math.pow(_selfz, _exponentz));
        return dest;
    }

    public static java.nio.DoubleBuffer project_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double ontoX, double ontoY, double ontoZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ);
        return dest;
    }

    public static java.nio.DoubleBuffer project_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double ontoX, double ontoY, double ontoZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, ontoX, ontoY, ontoZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ);
        return dest;
    }

    public static java.nio.DoubleBuffer project_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double ontoX, double ontoY, double ontoZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t7 = java.lang.Math.fma(ontoZ, src.get(srcOffset + 2), java.lang.Math.fma(ontoX, src.get(srcOffset), ontoY * src.get(srcOffset + 1))) / java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY));
            dest.put(destOffset, ontoX * _t7);
            dest.put(destOffset + 1, ontoY * _t7);
            dest.put(destOffset + 2, ontoZ * _t7);
            return dest;
        } else {
            double _t7 = ((ontoZ) * (src.get(srcOffset + 2)) + (((ontoX) * (src.get(srcOffset)) + (ontoY * src.get(srcOffset + 1))))) / ((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))));
            dest.put(destOffset, ontoX * _t7);
            dest.put(destOffset + 1, ontoY * _t7);
            dest.put(destOffset + 2, ontoZ * _t7);
            return dest;
        }
    }

    public static java.nio.DoubleBuffer project_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + ontoOffset * 8L;
        Double3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.DoubleBuffer project_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer onto, int ontoOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && onto.hasArray() && ontoOffset >= 0 && ontoOffset <= onto.limit() - 3) {
            Double3Ops.project(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, onto.array(), onto.arrayOffset() + ontoOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.project_apiGet(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer project_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer onto, int ontoOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _ontox = onto.get(ontoOffset);
            double _ontoy = onto.get(ontoOffset + 1);
            double _ontoz = onto.get(ontoOffset + 2);
            double _t7 = java.lang.Math.fma(_ontoz, src.get(srcOffset + 2), java.lang.Math.fma(_ontox, src.get(srcOffset), _ontoy * src.get(srcOffset + 1))) / java.lang.Math.fma(_ontoz, _ontoz, java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy));
            dest.put(destOffset, _ontox * _t7);
            dest.put(destOffset + 1, _ontoy * _t7);
            dest.put(destOffset + 2, _ontoz * _t7);
            return dest;
        } else {
            double _ontox = onto.get(ontoOffset);
            double _ontoy = onto.get(ontoOffset + 1);
            double _ontoz = onto.get(ontoOffset + 2);
            double _t7 = ((_ontoz) * (src.get(srcOffset + 2)) + (((_ontox) * (src.get(srcOffset)) + (_ontoy * src.get(srcOffset + 1))))) / ((_ontoz) * (_ontoz) + (((_ontox) * (_ontox) + (_ontoy * _ontoy))));
            dest.put(destOffset, _ontox * _t7);
            dest.put(destOffset + 1, _ontoy * _t7);
            dest.put(destOffset + 2, _ontoz * _t7);
            return dest;
        }
    }

    public static java.nio.DoubleBuffer projectOnPlane_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t2 = java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
            dest.put(destOffset, java.lang.Math.fma(-normalX, _t2, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(-normalY, _t2, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(-normalZ, _t2, _selfz));
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t2 = ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
            dest.put(destOffset, ((_selfx) - (normalX) * (_t2)));
            dest.put(destOffset + 1, ((_selfy) - (normalY) * (_t2)));
            dest.put(destOffset + 2, ((_selfz) - (normalZ) * (_t2)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer projectOnPlane_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 8L;
        Double3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            Double3Ops.projectOnPlane(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.projectOnPlane_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer projectOnPlane_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _normalx = normal.get(normalOffset);
            double _normaly = normal.get(normalOffset + 1);
            double _normalz = normal.get(normalOffset + 2);
            double _t2 = java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
            dest.put(destOffset, java.lang.Math.fma(-_normalx, _t2, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(-_normaly, _t2, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(-_normalz, _t2, _selfz));
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _normalx = normal.get(normalOffset);
            double _normaly = normal.get(normalOffset + 1);
            double _normalz = normal.get(normalOffset + 2);
            double _t2 = ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
            dest.put(destOffset, ((_selfx) - (_normalx) * (_t2)));
            dest.put(destOffset + 1, ((_selfy) - (_normaly) * (_t2)));
            dest.put(destOffset + 2, ((_selfz) - (_normalz) * (_t2)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer radians_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer radians_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.radians(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.radians_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer radians_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.toRadians(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.toRadians(_selfy));
        dest.put(destOffset + 2, Math.toRadians(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t3 = 2.0 * java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
            dest.put(destOffset, java.lang.Math.fma(-normalX, _t3, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(-normalY, _t3, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(-normalZ, _t3, _selfz));
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t3 = 2.0 * ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
            dest.put(destOffset, ((_selfx) - (normalX) * (_t3)));
            dest.put(destOffset + 1, ((_selfy) - (normalY) * (_t3)));
            dest.put(destOffset + 2, ((_selfz) - (normalZ) * (_t3)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer reflect_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 8L;
        Double3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            Double3Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _normalx = normal.get(normalOffset);
            double _normaly = normal.get(normalOffset + 1);
            double _normalz = normal.get(normalOffset + 2);
            double _t3 = 2.0 * java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
            dest.put(destOffset, java.lang.Math.fma(-_normalx, _t3, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(-_normaly, _t3, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(-_normalz, _t3, _selfz));
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _normalx = normal.get(normalOffset);
            double _normaly = normal.get(normalOffset + 1);
            double _normalz = normal.get(normalOffset + 2);
            double _t3 = 2.0 * ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
            dest.put(destOffset, ((_selfx) - (_normalx) * (_t3)));
            dest.put(destOffset + 1, ((_selfy) - (_normaly) * (_t3)));
            dest.put(destOffset + 2, ((_selfz) - (_normalz) * (_t3)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer refract_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, eta);
        return dest;
    }

    public static java.nio.DoubleBuffer refract_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ, eta);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
        return dest;
    }

    public static java.nio.DoubleBuffer refract_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_apiGet_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t3 = java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy));
        double _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0), eta * eta, 1.0);
        double _t10 = java.lang.Math.fma(eta, _t3, java.lang.Math.sqrt(Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            dest.put(destOffset, java.lang.Math.fma(eta, _selfx, -(normalX * _t10)));
            dest.put(destOffset + 1, java.lang.Math.fma(eta, _selfy, -(normalY * _t10)));
            dest.put(destOffset + 2, java.lang.Math.fma(eta, _selfz, -(normalZ * _t10)));
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    /** {@code refract_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer refract_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double eta) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t3 = ((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))));
        double _t7 = ((((_t3) * (_t3) - (1.0))) * (eta * eta) + (1.0));
        double _t10 = ((eta) * (_t3) + (java.lang.Math.sqrt(Math.max(0.0, _t7))));
        if (_t7 >= 0.0) {
            dest.put(destOffset, ((eta) * (_selfx) - (normalX * _t10)));
            dest.put(destOffset + 1, ((eta) * (_selfy) - (normalY * _t10)));
            dest.put(destOffset + 2, ((eta) * (_selfz) - (normalZ * _t10)));
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer refract_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset, double eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 8L;
        Double3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.DoubleBuffer refract_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset, double eta) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            Double3Ops.refract(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset, eta);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.refract_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return dest;
    }

    public static java.nio.DoubleBuffer refract_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset, double eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_apiGet_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _normalx = normal.get(normalOffset);
        double _normaly = normal.get(normalOffset + 1);
        double _normalz = normal.get(normalOffset + 2);
        double _t3 = java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy));
        double _t7 = java.lang.Math.fma(java.lang.Math.fma(_t3, _t3, -1.0), eta * eta, 1.0);
        double _t10 = java.lang.Math.fma(eta, _t3, java.lang.Math.sqrt(Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            dest.put(destOffset, java.lang.Math.fma(eta, _selfx, -(_normalx * _t10)));
            dest.put(destOffset + 1, java.lang.Math.fma(eta, _selfy, -(_normaly * _t10)));
            dest.put(destOffset + 2, java.lang.Math.fma(eta, _selfz, -(_normalz * _t10)));
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    /** {@code refract_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer refract_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset, double eta) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _normalx = normal.get(normalOffset);
        double _normaly = normal.get(normalOffset + 1);
        double _normalz = normal.get(normalOffset + 2);
        double _t3 = ((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))));
        double _t7 = ((((_t3) * (_t3) - (1.0))) * (eta * eta) + (1.0));
        double _t10 = ((eta) * (_t3) + (java.lang.Math.sqrt(Math.max(0.0, _t7))));
        if (_t7 >= 0.0) {
            dest.put(destOffset, ((eta) * (_selfx) - (_normalx * _t10)));
            dest.put(destOffset + 1, ((eta) * (_selfy) - (_normaly * _t10)));
            dest.put(destOffset + 2, ((eta) * (_selfz) - (_normalz * _t10)));
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer round_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer round_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.round(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.round_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer round_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.rint(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.rint(_selfy));
        dest.put(destOffset + 2, Math.rint(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer sign_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sign_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.sign(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.sign_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sign_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.signum(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.signum(_selfy));
        dest.put(destOffset + 2, Math.signum(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer sin_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sin_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.sin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.sin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sin_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.sin(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.sin(_selfy));
        dest.put(destOffset + 2, Math.sin(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer sinh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sinh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.sinh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.sinh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sinh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.sinh(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.sinh(_selfy));
        dest.put(destOffset + 2, Math.sinh(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0, double edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0, double edge1) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0, edge1);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge1);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0, double edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_apiGet_mulAdd(dest, destOffset, src, srcOffset, edge0, edge1);
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t10 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset) - edge0) * _t0_inv));
        double _t11 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset + 1) - edge0) * _t0_inv));
        double _t12 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset + 2) - edge0) * _t0_inv));
        dest.put(destOffset, java.lang.Math.fma(-2.0, _t10, 3.0) * _t10 * _t10);
        dest.put(destOffset + 1, java.lang.Math.fma(-2.0, _t11, 3.0) * _t11 * _t11);
        dest.put(destOffset + 2, java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        return dest;
    }

    /** {@code smoothstep_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer smoothstep_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0, double edge1) {
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t10 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset) - edge0) * _t0_inv));
        double _t11 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset + 1) - edge0) * _t0_inv));
        double _t12 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset + 2) - edge0) * _t0_inv));
        dest.put(destOffset, ((3.0) - (2.0) * (_t10)) * _t10 * _t10);
        dest.put(destOffset + 1, ((3.0) - (2.0) * (_t11)) * _t11 * _t11);
        dest.put(destOffset + 2, ((3.0) - (2.0) * (_t12)) * _t12 * _t12);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_apiGet_mulAdd(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        double _t12 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset) - edge0X) / (edge1X - edge0X)));
        double _t13 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset + 1) - edge0Y) / (edge1Y - edge0Y)));
        double _t14 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset + 2) - edge0Z) / (edge1Z - edge0Z)));
        dest.put(destOffset, java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        dest.put(destOffset + 1, java.lang.Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        dest.put(destOffset + 2, java.lang.Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        return dest;
    }

    /** {@code smoothstep_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer smoothstep_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        double _t12 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset) - edge0X) / (edge1X - edge0X)));
        double _t13 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset + 1) - edge0Y) / (edge1Y - edge0Y)));
        double _t14 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset + 2) - edge0Z) / (edge1Z - edge0Z)));
        dest.put(destOffset, ((3.0) - (2.0) * (_t12)) * _t12 * _t12);
        dest.put(destOffset + 1, ((3.0) - (2.0) * (_t13)) * _t13 * _t13);
        dest.put(destOffset + 2, ((3.0) - (2.0) * (_t14)) * _t14 * _t14);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge0, int edge0Offset, java.nio.DoubleBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + edge0Offset * 8L;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + edge1Offset * 8L;
        Double3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge0, int edge0Offset, java.nio.DoubleBuffer edge1, int edge1Offset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && edge0.hasArray() && edge0Offset >= 0 && edge0Offset <= edge0.limit() - 3 && edge1.hasArray() && edge1Offset >= 0 && edge1Offset <= edge1.limit() - 3) {
            Double3Ops.smoothstep(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge0.array(), edge0.arrayOffset() + edge0Offset, edge1.array(), edge1.arrayOffset() + edge1Offset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.smoothstep_apiGet(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return dest;
    }

    public static java.nio.DoubleBuffer smoothstep_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge0, int edge0Offset, java.nio.DoubleBuffer edge1, int edge1Offset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_apiGet_mulAdd(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        double _edge0x = edge0.get(edge0Offset);
        double _edge0y = edge0.get(edge0Offset + 1);
        double _edge0z = edge0.get(edge0Offset + 2);
        double _t12 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset) - _edge0x) / (edge1.get(edge1Offset) - _edge0x)));
        double _t13 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset + 1) - _edge0y) / (edge1.get(edge1Offset + 1) - _edge0y)));
        double _t14 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset + 2) - _edge0z) / (edge1.get(edge1Offset + 2) - _edge0z)));
        dest.put(destOffset, java.lang.Math.fma(-2.0, _t12, 3.0) * _t12 * _t12);
        dest.put(destOffset + 1, java.lang.Math.fma(-2.0, _t13, 3.0) * _t13 * _t13);
        dest.put(destOffset + 2, java.lang.Math.fma(-2.0, _t14, 3.0) * _t14 * _t14);
        return dest;
    }

    /** {@code smoothstep_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer smoothstep_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge0, int edge0Offset, java.nio.DoubleBuffer edge1, int edge1Offset) {
        double _edge0x = edge0.get(edge0Offset);
        double _edge0y = edge0.get(edge0Offset + 1);
        double _edge0z = edge0.get(edge0Offset + 2);
        double _t12 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset) - _edge0x) / (edge1.get(edge1Offset) - _edge0x)));
        double _t13 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset + 1) - _edge0y) / (edge1.get(edge1Offset + 1) - _edge0y)));
        double _t14 = Math.max(0.0, Math.min(1.0, (src.get(srcOffset + 2) - _edge0z) / (edge1.get(edge1Offset + 2) - _edge0z)));
        dest.put(destOffset, ((3.0) - (2.0) * (_t12)) * _t12 * _t12);
        dest.put(destOffset + 1, ((3.0) - (2.0) * (_t13)) * _t13 * _t13);
        dest.put(destOffset + 2, ((3.0) - (2.0) * (_t14)) * _t14 * _t14);
        return dest;
    }

    public static java.nio.DoubleBuffer sqrt_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sqrt_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.sqrt(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.sqrt_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sqrt_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, java.lang.Math.sqrt(src.get(srcOffset)));
        dest.put(destOffset + 1, java.lang.Math.sqrt(_selfy));
        dest.put(destOffset + 2, java.lang.Math.sqrt(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer step_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.DoubleBuffer step_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge);
        return dest;
    }

    public static java.nio.DoubleBuffer step_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, src.get(srcOffset) < edge ? 0.0 : 1.0);
        dest.put(destOffset + 1, _selfy < edge ? 0.0 : 1.0);
        dest.put(destOffset + 2, _selfz < edge ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer step_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edgeX, double edgeY, double edgeZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ);
        return dest;
    }

    public static java.nio.DoubleBuffer step_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edgeX, double edgeY, double edgeZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edgeX, edgeY, edgeZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ);
        return dest;
    }

    public static java.nio.DoubleBuffer step_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edgeX, double edgeY, double edgeZ) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, src.get(srcOffset) < edgeX ? 0.0 : 1.0);
        dest.put(destOffset + 1, _selfy < edgeY ? 0.0 : 1.0);
        dest.put(destOffset + 2, _selfz < edgeZ ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer step_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + edgeOffset * 8L;
        Double3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.DoubleBuffer step_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge, int edgeOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && edge.hasArray() && edgeOffset >= 0 && edgeOffset <= edge.limit() - 3) {
            Double3Ops.step(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, edge.array(), edge.arrayOffset() + edgeOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.step_apiGet(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer step_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge, int edgeOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _edgey = edge.get(edgeOffset + 1);
        double _edgez = edge.get(edgeOffset + 2);
        dest.put(destOffset, src.get(srcOffset) < edge.get(edgeOffset) ? 0.0 : 1.0);
        dest.put(destOffset + 1, _selfy < _edgey ? 0.0 : 1.0);
        dest.put(destOffset + 2, _selfz < _edgez ? 0.0 : 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer tan_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer tan_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.tan(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.tan_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer tan_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.tan(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.tan(_selfy));
        dest.put(destOffset + 2, Math.tan(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer tanh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer tanh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.tanh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.tanh_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer tanh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.tanh(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.tanh(_selfy));
        dest.put(destOffset + 2, Math.tanh(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer triangleNormal_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.nio.DoubleBuffer triangleNormal_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.triangleNormal(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.triangleNormal_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.nio.DoubleBuffer triangleNormal_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = p1Y - _selfy;
        double _t1 = p2Z - _selfz;
        double _t2 = p1Z - _selfz;
        double _t3 = p2Y - _selfy;
        double _t4 = p1X - _selfx;
        double _t5 = p2X - _selfx;
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = java.lang.Math.fma(_t0, _t1, _t9) - java.lang.Math.fma(_t2, _t3, _t9);
        double _t19 = java.lang.Math.fma(_t4, _t3, _t10) - java.lang.Math.fma(_t0, _t5, _t10);
        double _t20 = java.lang.Math.fma(_t2, _t5, _t11) - java.lang.Math.fma(_t4, _t1, _t11);
        double _ct0 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t25 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.put(destOffset, _t18 * _t25);
        dest.put(destOffset + 1, _t20 * _t25);
        dest.put(destOffset + 2, _t19 * _t25);
        return dest;
    }

    /** {@code triangleNormal_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer triangleNormal_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = p1Y - _selfy;
        double _t1 = p2Z - _selfz;
        double _t2 = p1Z - _selfz;
        double _t3 = p2Y - _selfy;
        double _t4 = p1X - _selfx;
        double _t5 = p2X - _selfx;
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = ((_t0) * (_t1) + (_t9)) - ((_t2) * (_t3) + (_t9));
        double _t19 = ((_t4) * (_t3) + (_t10)) - ((_t0) * (_t5) + (_t10));
        double _t20 = ((_t2) * (_t5) + (_t11)) - ((_t4) * (_t1) + (_t11));
        double _ct0 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t25 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.put(destOffset, _t18 * _t25);
        dest.put(destOffset + 1, _t20 * _t25);
        dest.put(destOffset + 2, _t19 * _t25);
        return dest;
    }

    public static java.nio.DoubleBuffer triangleNormal_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return Double3OpsKernelsTypedBuffer.triangleNormal_degenerate_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    public static java.nio.DoubleBuffer triangleNormal_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.nio.DoubleBuffer triangleNormal_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3OpsKernelsArray.triangleNormal_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.triangleNormal_degenerate_apiGet(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.nio.DoubleBuffer triangleNormal_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_apiGet_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return triangleNormal_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    /** {@code triangleNormal_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer triangleNormal_degenerate_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = _selfx * _t19;
        double _t32 = _selfy * _t19;
        double _t34 = _selfz * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        double _t41 = p2Y * _t19 - _t32;
        double _t42 = p2X * _t19 - _t30;
        double _t43 = p2Z * _t19 - _t34;
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t61 = -(_t54 * _t55);
        return triangleNormal_degenerate_apiGet_fma_s61b9b300_1(dest, destOffset, _t52, _t53, _t54, _t55, _t56, _t57, -(_t57 * _t53), -(_t52 * _t56), java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer triangleNormal_degenerate_apiGet_fma_s61b9b300_1(java.nio.DoubleBuffer dest, int destOffset, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t62, double _t63, double _t70) {
        double _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        double _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0) {
            dest.put(destOffset, _t83 * _t78);
            dest.put(destOffset + 1, _t83 * _t79);
            dest.put(destOffset + 2, _t83 * _t77);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    /** {@code triangleNormal_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer triangleNormal_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
        double _t30 = _selfx * _t19;
        double _t32 = _selfy * _t19;
        double _t34 = _selfz * _t19;
        double _t38 = p1X * _t19 - _t30;
        double _t39 = p1Y * _t19 - _t32;
        double _t40 = p1Z * _t19 - _t34;
        double _t41 = p2Y * _t19 - _t32;
        double _t42 = p2X * _t19 - _t30;
        double _t43 = p2Z * _t19 - _t34;
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        double _t52 = _t38 * _t44;
        double _t53 = _t41 * _t45;
        double _t54 = _t39 * _t44;
        double _t55 = _t42 * _t45;
        double _t56 = _t43 * _t45;
        double _t57 = _t40 * _t44;
        double _t61 = -(_t54 * _t55);
        return triangleNormal_degenerate_apiGet_mulAdd_s768c82d1_1(dest, destOffset, _t52, _t53, _t54, _t55, _t56, _t57, -(_t57 * _t53), -(_t52 * _t56), ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61)));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer triangleNormal_degenerate_apiGet_mulAdd_s768c82d1_1(java.nio.DoubleBuffer dest, int destOffset, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57, double _t62, double _t63, double _t70) {
        double _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        double _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0) {
            dest.put(destOffset, _t83 * _t78);
            dest.put(destOffset + 1, _t83 * _t79);
            dest.put(destOffset + 2, _t83 * _t77);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer triangleNormal_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        Double3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, _p1Base, _p2Base);
        return dest;
    }

    public static java.nio.DoubleBuffer triangleNormal_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3) {
            Double3Ops.triangleNormal(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.triangleNormal_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return dest;
    }

    public static java.nio.DoubleBuffer triangleNormal_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_apiGet_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return triangleNormal_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    /** {@code triangleNormal_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer triangleNormal_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = p1.get(p1Offset + 1) - _selfy;
        double _t1 = p2.get(p2Offset + 2) - _selfz;
        double _t2 = p1.get(p1Offset + 2) - _selfz;
        double _t3 = p2.get(p2Offset + 1) - _selfy;
        double _t4 = p1.get(p1Offset) - _selfx;
        double _t5 = p2.get(p2Offset) - _selfx;
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = java.lang.Math.fma(_t0, _t1, _t9) - java.lang.Math.fma(_t2, _t3, _t9);
        double _t19 = java.lang.Math.fma(_t4, _t3, _t10) - java.lang.Math.fma(_t0, _t5, _t10);
        double _t20 = java.lang.Math.fma(_t2, _t5, _t11) - java.lang.Math.fma(_t4, _t1, _t11);
        double _ct0 = java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t18, _t18, _t20 * _t20));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        double _t25 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.put(destOffset, _t18 * _t25);
        dest.put(destOffset + 1, _t20 * _t25);
        dest.put(destOffset + 2, _t19 * _t25);
        return dest;
    }

    /** {@code triangleNormal_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer triangleNormal_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = p1.get(p1Offset + 1) - _selfy;
        double _t1 = p2.get(p2Offset + 2) - _selfz;
        double _t2 = p1.get(p1Offset + 2) - _selfz;
        double _t3 = p2.get(p2Offset + 1) - _selfy;
        double _t4 = p1.get(p1Offset) - _selfx;
        double _t5 = p2.get(p2Offset) - _selfx;
        double _t9 = -(_t2 * _t3);
        double _t10 = -(_t0 * _t5);
        double _t11 = -(_t4 * _t1);
        double _t18 = ((_t0) * (_t1) + (_t9)) - ((_t2) * (_t3) + (_t9));
        double _t19 = ((_t4) * (_t3) + (_t10)) - ((_t0) * (_t5) + (_t10));
        double _t20 = ((_t2) * (_t5) + (_t11)) - ((_t4) * (_t1) + (_t11));
        double _ct0 = ((_t19) * (_t19) + (((_t18) * (_t18) + (_t20 * _t20))));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsTypedBuffer.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        double _t25 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.put(destOffset, _t18 * _t25);
        dest.put(destOffset + 1, _t20 * _t25);
        dest.put(destOffset + 2, _t19 * _t25);
        return dest;
    }

    public static java.nio.DoubleBuffer triangleNormal_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return Double3OpsKernelsTypedBuffer.triangleNormal_degenerate_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    public static java.nio.DoubleBuffer triangleNormal_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset * 8L;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset * 8L;
        Double3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, _p1Base, _p2Base);
        return dest;
    }

    public static java.nio.DoubleBuffer triangleNormal_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && p1.hasArray() && p1Offset >= 0 && p1Offset <= p1.limit() - 3 && p2.hasArray() && p2Offset >= 0 && p2Offset <= p2.limit() - 3) {
            Double3OpsKernelsArray.triangleNormal_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, p1.array(), p1.arrayOffset() + p1Offset, p2.array(), p2.arrayOffset() + p2Offset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.triangleNormal_degenerate_apiGet(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return dest;
    }

    public static java.nio.DoubleBuffer triangleNormal_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return triangleNormal_degenerate_apiGet_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return triangleNormal_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    /** {@code triangleNormal_degenerate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer triangleNormal_degenerate_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2x = p2.get(p2Offset);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        double _t30 = _selfx * _t19;
        double _t32 = _selfy * _t19;
        double _t34 = _selfz * _t19;
        double _t38 = _p1x * _t19 - _t30;
        double _t39 = _p1y * _t19 - _t32;
        double _t40 = _p1z * _t19 - _t34;
        double _t41 = _p2y * _t19 - _t32;
        double _t42 = _p2x * _t19 - _t30;
        double _t43 = _p2z * _t19 - _t34;
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        return triangleNormal_degenerate_apiGet_fma_sc424200b_1(dest, destOffset, _t38 * _t44, _t41 * _t45, _t39 * _t44, _t42 * _t45, _t43 * _t45, _t40 * _t44);
    }

    /** Piece 2 of {@code triangleNormal_degenerate_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer triangleNormal_degenerate_apiGet_fma_sc424200b_1(java.nio.DoubleBuffer dest, int destOffset, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57) {
        double _t61 = -(_t54 * _t55);
        double _t62 = -(_t57 * _t53);
        double _t63 = -(_t52 * _t56);
        double _t70 = java.lang.Math.fma(_t52, _t53, _t61) - java.lang.Math.fma(_t54, _t55, _t61);
        double _t71 = java.lang.Math.fma(_t54, _t56, _t62) - java.lang.Math.fma(_t57, _t53, _t62);
        double _t72 = java.lang.Math.fma(_t57, _t55, _t63) - java.lang.Math.fma(_t52, _t56, _t63);
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = java.lang.Math.fma(_t77, _t77, java.lang.Math.fma(_t78, _t78, _t79 * _t79));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0) {
            dest.put(destOffset, _t83 * _t78);
            dest.put(destOffset + 1, _t83 * _t79);
            dest.put(destOffset + 2, _t83 * _t77);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    /** {@code triangleNormal_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer triangleNormal_degenerate_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _p1x = p1.get(p1Offset);
        double _p1y = p1.get(p1Offset + 1);
        double _p1z = p1.get(p1Offset + 2);
        double _p2x = p2.get(p2Offset);
        double _p2y = p2.get(p2Offset + 1);
        double _p2z = p2.get(p2Offset + 2);
        double _t19 = Math.min(1.0, unitScale(Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), Math.max(java.lang.Math.abs(Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
        double _t30 = _selfx * _t19;
        double _t32 = _selfy * _t19;
        double _t34 = _selfz * _t19;
        double _t38 = _p1x * _t19 - _t30;
        double _t39 = _p1y * _t19 - _t32;
        double _t40 = _p1z * _t19 - _t34;
        double _t41 = _p2y * _t19 - _t32;
        double _t42 = _p2x * _t19 - _t30;
        double _t43 = _p2z * _t19 - _t34;
        double _t44 = unitScale(_t38, _t39, _t40);
        double _t45 = unitScale(_t42, _t41, _t43);
        return triangleNormal_degenerate_apiGet_mulAdd_s33e4c35a_1(dest, destOffset, _t38 * _t44, _t41 * _t45, _t39 * _t44, _t42 * _t45, _t43 * _t45, _t40 * _t44);
    }

    /** Piece 2 of {@code triangleNormal_degenerate_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer triangleNormal_degenerate_apiGet_mulAdd_s33e4c35a_1(java.nio.DoubleBuffer dest, int destOffset, double _t52, double _t53, double _t54, double _t55, double _t56, double _t57) {
        double _t61 = -(_t54 * _t55);
        double _t62 = -(_t57 * _t53);
        double _t63 = -(_t52 * _t56);
        double _t70 = ((_t52) * (_t53) + (_t61)) - ((_t54) * (_t55) + (_t61));
        double _t71 = ((_t54) * (_t56) + (_t62)) - ((_t57) * (_t53) + (_t62));
        double _t72 = ((_t57) * (_t55) + (_t63)) - ((_t52) * (_t56) + (_t63));
        double _t73 = unitScale(_t71, _t72, _t70);
        double _t77 = _t70 * _t73;
        double _t78 = _t71 * _t73;
        double _t79 = _t72 * _t73;
        double _t82 = ((_t77) * (_t77) + (((_t78) * (_t78) + (_t79 * _t79))));
        double _t83 = (1.0 / java.lang.Math.sqrt(_t82));
        if (_t82 != 0.0) {
            dest.put(destOffset, _t83 * _t78);
            dest.put(destOffset + 1, _t83 * _t79);
            dest.put(destOffset + 2, _t83 * _t77);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer trunc_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer trunc_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.trunc(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.trunc_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer trunc_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.put(destOffset + 1, _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.put(destOffset + 2, _selfz >= 0.0 ? Math.floor(_selfz) : Math.ceil(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer ulp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer ulp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.ulp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.ulp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer ulp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, Math.ulp(src.get(srcOffset)));
        dest.put(destOffset + 1, Math.ulp(_selfy));
        dest.put(destOffset + 2, Math.ulp(_selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer xyz0_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.xyz0_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer xyz0_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.xyz0(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.xyz0_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer xyz0_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _selfy);
        dest.put(destOffset + 2, _selfz);
        dest.put(destOffset + 3, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer xyz1_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.xyz1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer xyz1_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.xyz1(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.xyz1_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer xyz1_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _selfy);
        dest.put(destOffset + 2, _selfz);
        dest.put(destOffset + 3, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 8L;
        Double3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 9) {
            Double3Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_apiGet_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _mat10 = mat.get(matOffset + 1);
        double _mat20 = mat.get(matOffset + 2);
        double _mat11 = mat.get(matOffset + 4);
        double _mat21 = mat.get(matOffset + 5);
        double _mat12 = mat.get(matOffset + 7);
        double _mat22 = mat.get(matOffset + 8);
        dest.put(destOffset, java.lang.Math.fma(mat.get(matOffset + 6), _selfz, java.lang.Math.fma(mat.get(matOffset), _selfx, mat.get(matOffset + 3) * _selfy)));
        dest.put(destOffset + 1, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.put(destOffset + 2, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMul_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer preMul_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _mat10 = mat.get(matOffset + 1);
        double _mat20 = mat.get(matOffset + 2);
        double _mat11 = mat.get(matOffset + 4);
        double _mat21 = mat.get(matOffset + 5);
        double _mat12 = mat.get(matOffset + 7);
        double _mat22 = mat.get(matOffset + 8);
        dest.put(destOffset, ((mat.get(matOffset + 6)) * (_selfz) + (((mat.get(matOffset)) * (_selfx) + (mat.get(matOffset + 3) * _selfy)))));
        dest.put(destOffset + 1, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        dest.put(destOffset + 2, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static java.nio.DoubleBuffer preMulDirectionMat3x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 8L;
        Double3OpsKernelsAddress.preMulDirectionMat3x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulDirectionMat3x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 12) {
            Double3Ops.preMulDirectionMat3x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.preMulDirectionMat3x4_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulDirectionMat3x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulDirectionMat3x4_apiGet_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _mat10 = mat.get(matOffset + 4);
        double _mat11 = mat.get(matOffset + 5);
        double _mat12 = mat.get(matOffset + 6);
        double _mat20 = mat.get(matOffset + 8);
        double _mat21 = mat.get(matOffset + 9);
        double _mat22 = mat.get(matOffset + 10);
        dest.put(destOffset, java.lang.Math.fma(mat.get(matOffset + 2), _selfz, java.lang.Math.fma(mat.get(matOffset), _selfx, mat.get(matOffset + 1) * _selfy)));
        dest.put(destOffset + 1, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.put(destOffset + 2, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMulDirectionMat3x4_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer preMulDirectionMat3x4_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _mat10 = mat.get(matOffset + 4);
        double _mat11 = mat.get(matOffset + 5);
        double _mat12 = mat.get(matOffset + 6);
        double _mat20 = mat.get(matOffset + 8);
        double _mat21 = mat.get(matOffset + 9);
        double _mat22 = mat.get(matOffset + 10);
        dest.put(destOffset, ((mat.get(matOffset + 2)) * (_selfz) + (((mat.get(matOffset)) * (_selfx) + (mat.get(matOffset + 1) * _selfy)))));
        dest.put(destOffset + 1, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        dest.put(destOffset + 2, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static java.nio.DoubleBuffer preMulDirectionMat4x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 8L;
        Double3OpsKernelsAddress.preMulDirectionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulDirectionMat4x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 16) {
            Double3Ops.preMulDirectionMat4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.preMulDirectionMat4x4_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulDirectionMat4x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulDirectionMat4x4_apiGet_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _mat10 = mat.get(matOffset + 1);
        double _mat20 = mat.get(matOffset + 2);
        double _mat11 = mat.get(matOffset + 5);
        double _mat21 = mat.get(matOffset + 6);
        double _mat12 = mat.get(matOffset + 9);
        double _mat22 = mat.get(matOffset + 10);
        dest.put(destOffset, java.lang.Math.fma(mat.get(matOffset + 8), _selfz, java.lang.Math.fma(mat.get(matOffset), _selfx, mat.get(matOffset + 4) * _selfy)));
        dest.put(destOffset + 1, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.put(destOffset + 2, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    /** {@code preMulDirectionMat4x4_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer preMulDirectionMat4x4_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _mat10 = mat.get(matOffset + 1);
        double _mat20 = mat.get(matOffset + 2);
        double _mat11 = mat.get(matOffset + 5);
        double _mat21 = mat.get(matOffset + 6);
        double _mat12 = mat.get(matOffset + 9);
        double _mat22 = mat.get(matOffset + 10);
        dest.put(destOffset, ((mat.get(matOffset + 8)) * (_selfz) + (((mat.get(matOffset)) * (_selfx) + (mat.get(matOffset + 4) * _selfy)))));
        dest.put(destOffset + 1, ((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))));
        dest.put(destOffset + 2, ((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))));
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat3x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 8L;
        Double3OpsKernelsAddress.preMulPositionMat3x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat3x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 12) {
            Double3Ops.preMulPositionMat3x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.preMulPositionMat3x4_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat3x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat3x4_apiGet_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _mat10 = mat.get(matOffset + 4);
        double _mat11 = mat.get(matOffset + 5);
        double _mat12 = mat.get(matOffset + 6);
        double _mat13 = mat.get(matOffset + 7);
        double _mat20 = mat.get(matOffset + 8);
        double _mat21 = mat.get(matOffset + 9);
        double _mat22 = mat.get(matOffset + 10);
        double _mat23 = mat.get(matOffset + 11);
        dest.put(destOffset, java.lang.Math.fma(mat.get(matOffset), _selfx, java.lang.Math.fma(mat.get(matOffset + 1), _selfy, java.lang.Math.fma(mat.get(matOffset + 2), _selfz, mat.get(matOffset + 3)))));
        dest.put(destOffset + 1, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))));
        dest.put(destOffset + 2, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    /** {@code preMulPositionMat3x4_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer preMulPositionMat3x4_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _mat10 = mat.get(matOffset + 4);
        double _mat11 = mat.get(matOffset + 5);
        double _mat12 = mat.get(matOffset + 6);
        double _mat13 = mat.get(matOffset + 7);
        double _mat20 = mat.get(matOffset + 8);
        double _mat21 = mat.get(matOffset + 9);
        double _mat22 = mat.get(matOffset + 10);
        double _mat23 = mat.get(matOffset + 11);
        dest.put(destOffset, ((mat.get(matOffset)) * (_selfx) + (((mat.get(matOffset + 1)) * (_selfy) + (((mat.get(matOffset + 2)) * (_selfz) + (mat.get(matOffset + 3))))))));
        dest.put(destOffset + 1, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))));
        dest.put(destOffset + 2, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))));
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat4x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 8L;
        Double3OpsKernelsAddress.preMulPositionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat4x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 16) {
            Double3Ops.preMulPositionMat4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.preMulPositionMat4x4_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulPositionMat4x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMulPositionMat4x4_apiGet_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _mat10 = mat.get(matOffset + 1);
        double _mat20 = mat.get(matOffset + 2);
        double _mat11 = mat.get(matOffset + 5);
        double _mat21 = mat.get(matOffset + 6);
        double _mat12 = mat.get(matOffset + 9);
        double _mat22 = mat.get(matOffset + 10);
        double _mat13 = mat.get(matOffset + 13);
        double _mat23 = mat.get(matOffset + 14);
        dest.put(destOffset, java.lang.Math.fma(mat.get(matOffset), _selfx, java.lang.Math.fma(mat.get(matOffset + 4), _selfy, java.lang.Math.fma(mat.get(matOffset + 8), _selfz, mat.get(matOffset + 12)))));
        dest.put(destOffset + 1, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))));
        dest.put(destOffset + 2, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    /** {@code preMulPositionMat4x4_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer preMulPositionMat4x4_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _mat10 = mat.get(matOffset + 1);
        double _mat20 = mat.get(matOffset + 2);
        double _mat11 = mat.get(matOffset + 5);
        double _mat21 = mat.get(matOffset + 6);
        double _mat12 = mat.get(matOffset + 9);
        double _mat22 = mat.get(matOffset + 10);
        double _mat13 = mat.get(matOffset + 13);
        double _mat23 = mat.get(matOffset + 14);
        dest.put(destOffset, ((mat.get(matOffset)) * (_selfx) + (((mat.get(matOffset + 4)) * (_selfy) + (((mat.get(matOffset + 8)) * (_selfz) + (mat.get(matOffset + 12))))))));
        dest.put(destOffset + 1, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))));
        dest.put(destOffset + 2, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))));
        return dest;
    }

    public static java.nio.DoubleBuffer preMulProject_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset * 8L;
        Double3OpsKernelsAddress.preMulProject_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulProject_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && mat.hasArray() && matOffset >= 0 && matOffset <= mat.limit() - 16) {
            Double3Ops.preMulProject(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, mat.array(), mat.arrayOffset() + matOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.preMulProject_apiGet(dest, destOffset, src, srcOffset, mat, matOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulProject_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMulProject_apiGet_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMulProject_apiGet_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMulProject_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer preMulProject_apiGet_fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _mat10 = mat.get(matOffset + 1);
        double _mat20 = mat.get(matOffset + 2);
        double _mat11 = mat.get(matOffset + 5);
        double _mat21 = mat.get(matOffset + 6);
        double _mat12 = mat.get(matOffset + 9);
        double _mat22 = mat.get(matOffset + 10);
        double _mat13 = mat.get(matOffset + 13);
        double _mat23 = mat.get(matOffset + 14);
        double _t2_inv = 1.0 / java.lang.Math.fma(mat.get(matOffset + 3), _selfx, java.lang.Math.fma(mat.get(matOffset + 7), _selfy, java.lang.Math.fma(mat.get(matOffset + 11), _selfz, mat.get(matOffset + 15))));
        dest.put(destOffset, java.lang.Math.fma(mat.get(matOffset), _selfx, java.lang.Math.fma(mat.get(matOffset + 4), _selfy, java.lang.Math.fma(mat.get(matOffset + 8), _selfz, mat.get(matOffset + 12)))) * _t2_inv);
        dest.put(destOffset + 1, java.lang.Math.fma(_mat10, _selfx, java.lang.Math.fma(_mat11, _selfy, java.lang.Math.fma(_mat12, _selfz, _mat13))) * _t2_inv);
        dest.put(destOffset + 2, java.lang.Math.fma(_mat20, _selfx, java.lang.Math.fma(_mat21, _selfy, java.lang.Math.fma(_mat22, _selfz, _mat23))) * _t2_inv);
        return dest;
    }

    /** {@code preMulProject_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer preMulProject_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _mat10 = mat.get(matOffset + 1);
        double _mat20 = mat.get(matOffset + 2);
        double _mat11 = mat.get(matOffset + 5);
        double _mat21 = mat.get(matOffset + 6);
        double _mat12 = mat.get(matOffset + 9);
        double _mat22 = mat.get(matOffset + 10);
        double _mat13 = mat.get(matOffset + 13);
        double _mat23 = mat.get(matOffset + 14);
        double _t2_inv = 1.0 / ((mat.get(matOffset + 3)) * (_selfx) + (((mat.get(matOffset + 7)) * (_selfy) + (((mat.get(matOffset + 11)) * (_selfz) + (mat.get(matOffset + 15)))))));
        dest.put(destOffset, ((mat.get(matOffset)) * (_selfx) + (((mat.get(matOffset + 4)) * (_selfy) + (((mat.get(matOffset + 8)) * (_selfz) + (mat.get(matOffset + 12))))))) * _t2_inv);
        dest.put(destOffset + 1, ((_mat10) * (_selfx) + (((_mat11) * (_selfy) + (((_mat12) * (_selfz) + (_mat13)))))) * _t2_inv);
        dest.put(destOffset + 2, ((_mat20) * (_selfx) + (((_mat21) * (_selfy) + (((_mat22) * (_selfz) + (_mat23)))))) * _t2_inv);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quatX, quatY, quatZ, quatW);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_apiGet_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t9 = 2.0 * java.lang.Math.fma(quatX, _selfy, -(quatY * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(quatZ, _selfx, -(quatX * _selfz));
        double _t11 = 2.0 * java.lang.Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.put(destOffset, java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.put(destOffset + 1, java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfy))));
        dest.put(destOffset + 2, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, _selfz))));
        return dest;
    }

    /** {@code rotate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer rotate_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t9 = 2.0 * ((quatX) * (_selfy) - (quatY * _selfx));
        double _t10 = 2.0 * ((quatZ) * (_selfx) - (quatX * _selfz));
        double _t11 = 2.0 * ((quatY) * (_selfz) - (quatZ * _selfy));
        dest.put(destOffset, ((quatY) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatZ) * (_t10)))));
        dest.put(destOffset + 1, ((quatZ) * (_t11) + (((((quatW) * (_t10) + (_selfy))) - (quatX) * (_t9)))));
        dest.put(destOffset + 2, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfz))) - (quatY) * (_t11)))));
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset * 8L;
        Double3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && quat.hasArray() && quatOffset >= 0 && quatOffset <= quat.limit() - 4) {
            Double3Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quat.array(), quat.arrayOffset() + quatOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, quat, quatOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_apiGet_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _quatx = quat.get(quatOffset);
        double _quaty = quat.get(quatOffset + 1);
        double _quatz = quat.get(quatOffset + 2);
        double _quatw = quat.get(quatOffset + 3);
        double _t9 = 2.0 * java.lang.Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        double _t11 = 2.0 * java.lang.Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.put(destOffset, java.lang.Math.fma(_quaty, _t9, java.lang.Math.fma(-_quatz, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.put(destOffset + 1, java.lang.Math.fma(_quatz, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfy))));
        dest.put(destOffset + 2, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quaty, _t11, java.lang.Math.fma(_quatw, _t9, _selfz))));
        return dest;
    }

    /** {@code rotate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer rotate_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _quatx = quat.get(quatOffset);
        double _quaty = quat.get(quatOffset + 1);
        double _quatz = quat.get(quatOffset + 2);
        double _quatw = quat.get(quatOffset + 3);
        double _t9 = 2.0 * ((_quatx) * (_selfy) - (_quaty * _selfx));
        double _t10 = 2.0 * ((_quatz) * (_selfx) - (_quatx * _selfz));
        double _t11 = 2.0 * ((_quaty) * (_selfz) - (_quatz * _selfy));
        dest.put(destOffset, ((_quaty) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quatz) * (_t10)))));
        dest.put(destOffset + 1, ((_quatz) * (_t11) + (((((_quatw) * (_t10) + (_selfy))) - (_quatx) * (_t9)))));
        dest.put(destOffset + 2, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfz))) - (_quaty) * (_t11)))));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_apiGet_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = _selfy - pivotY;
        double _t1 = _selfx - pivotX;
        double _t2 = _selfz - pivotZ;
        double _t12 = 2.0 * java.lang.Math.fma(quatX, _t0, -(quatY * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(quatZ, _t1, -(quatX * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(quatY, _t2, -(quatZ * _t0));
        dest.put(destOffset, java.lang.Math.fma(quatY, _t12, java.lang.Math.fma(-quatZ, _t13, java.lang.Math.fma(quatW, _t14, _selfx))));
        dest.put(destOffset + 1, java.lang.Math.fma(quatZ, _t14, java.lang.Math.fma(-quatX, _t12, java.lang.Math.fma(quatW, _t13, _selfy))));
        dest.put(destOffset + 2, java.lang.Math.fma(quatX, _t13, java.lang.Math.fma(-quatY, _t14, java.lang.Math.fma(quatW, _t12, _selfz))));
        return dest;
    }

    /** {@code rotateAround_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer rotateAround_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = _selfy - pivotY;
        double _t1 = _selfx - pivotX;
        double _t2 = _selfz - pivotZ;
        double _t12 = 2.0 * ((quatX) * (_t0) - (quatY * _t1));
        double _t13 = 2.0 * ((quatZ) * (_t1) - (quatX * _t2));
        double _t14 = 2.0 * ((quatY) * (_t2) - (quatZ * _t0));
        dest.put(destOffset, ((quatY) * (_t12) + (((((quatW) * (_t14) + (_selfx))) - (quatZ) * (_t13)))));
        dest.put(destOffset + 1, ((quatZ) * (_t14) + (((((quatW) * (_t13) + (_selfy))) - (quatX) * (_t12)))));
        dest.put(destOffset + 2, ((quatX) * (_t13) + (((((quatW) * (_t12) + (_selfz))) - (quatY) * (_t14)))));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 8L;
        Double3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _quatBase, _pivotBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && quat.hasArray() && quatOffset >= 0 && quatOffset <= quat.limit() - 4 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Double3Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quat.array(), quat.arrayOffset() + quatOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAround_apiGet_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _quatx = quat.get(quatOffset);
        double _quaty = quat.get(quatOffset + 1);
        double _quatz = quat.get(quatOffset + 2);
        double _quatw = quat.get(quatOffset + 3);
        double _t0 = _selfy - pivot.get(pivotOffset + 1);
        double _t1 = _selfx - pivot.get(pivotOffset);
        double _t2 = _selfz - pivot.get(pivotOffset + 2);
        double _t12 = 2.0 * java.lang.Math.fma(_quatx, _t0, -(_quaty * _t1));
        double _t13 = 2.0 * java.lang.Math.fma(_quatz, _t1, -(_quatx * _t2));
        double _t14 = 2.0 * java.lang.Math.fma(_quaty, _t2, -(_quatz * _t0));
        dest.put(destOffset, java.lang.Math.fma(_quaty, _t12, java.lang.Math.fma(-_quatz, _t13, java.lang.Math.fma(_quatw, _t14, _selfx))));
        dest.put(destOffset + 1, java.lang.Math.fma(_quatz, _t14, java.lang.Math.fma(-_quatx, _t12, java.lang.Math.fma(_quatw, _t13, _selfy))));
        dest.put(destOffset + 2, java.lang.Math.fma(_quatx, _t13, java.lang.Math.fma(-_quaty, _t14, java.lang.Math.fma(_quatw, _t12, _selfz))));
        return dest;
    }

    /** {@code rotateAround_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer rotateAround_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _quatx = quat.get(quatOffset);
        double _quaty = quat.get(quatOffset + 1);
        double _quatz = quat.get(quatOffset + 2);
        double _quatw = quat.get(quatOffset + 3);
        double _t0 = _selfy - pivot.get(pivotOffset + 1);
        double _t1 = _selfx - pivot.get(pivotOffset);
        double _t2 = _selfz - pivot.get(pivotOffset + 2);
        double _t12 = 2.0 * ((_quatx) * (_t0) - (_quaty * _t1));
        double _t13 = 2.0 * ((_quatz) * (_t1) - (_quatx * _t2));
        double _t14 = 2.0 * ((_quaty) * (_t2) - (_quatz * _t0));
        dest.put(destOffset, ((_quaty) * (_t12) + (((((_quatw) * (_t14) + (_selfx))) - (_quatz) * (_t13)))));
        dest.put(destOffset + 1, ((_quatz) * (_t14) + (((((_quatw) * (_t13) + (_selfy))) - (_quatx) * (_t12)))));
        dest.put(destOffset + 2, ((_quatx) * (_t13) + (((((_quatw) * (_t12) + (_selfz))) - (_quaty) * (_t14)))));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_apiGet_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        dest.put(destOffset, java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t1)));
        dest.put(destOffset + 1, java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t1)));
        dest.put(destOffset + 2, java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t1)));
        return dest;
    }

    /** {@code rotateAxis_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer rotateAxis_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        dest.put(destOffset, ((_t2) * (axisX * _t5) + (((_selfx) * (_t0) + (((axisY) * (_selfz) - (axisZ * _selfy)) * _t1)))));
        dest.put(destOffset + 1, ((_t2) * (axisY * _t5) + (((_selfy) * (_t0) + (((axisZ) * (_selfx) - (axisX * _selfz)) * _t1)))));
        dest.put(destOffset + 2, ((_t2) * (axisZ * _t5) + (((_selfz) * (_t0) + (((axisX) * (_selfy) - (axisY * _selfx)) * _t1)))));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 8L;
        Double3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Double3Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_apiGet_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _axisx = axis.get(axisOffset);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.put(destOffset, java.lang.Math.fma(_t2, _axisx * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t1)));
        dest.put(destOffset + 1, java.lang.Math.fma(_t2, _axisy * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t1)));
        dest.put(destOffset + 2, java.lang.Math.fma(_t2, _axisz * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t1)));
        return dest;
    }

    /** {@code rotateAxis_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer rotateAxis_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _axisx = axis.get(axisOffset);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t5 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        dest.put(destOffset, ((_t2) * (_axisx * _t5) + (((_selfx) * (_t0) + (((_axisy) * (_selfz) - (_axisz * _selfy)) * _t1)))));
        dest.put(destOffset + 1, ((_t2) * (_axisy * _t5) + (((_selfy) * (_t0) + (((_axisz) * (_selfx) - (_axisx * _selfz)) * _t1)))));
        dest.put(destOffset + 2, ((_t2) * (_axisz * _t5) + (((_selfz) * (_t0) + (((_axisx) * (_selfy) - (_axisy * _selfx)) * _t1)))));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxisAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxisAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.rotateAxisAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateAxisAround_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxisAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxisAround_apiGet_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = src.get(srcOffset) - pivotX;
        double _t4 = src.get(srcOffset + 2) - pivotZ;
        double _t5 = src.get(srcOffset + 1) - pivotY;
        double _t8 = java.lang.Math.fma(axisZ, _t4, java.lang.Math.fma(axisX, _t3, axisY * _t5));
        dest.put(destOffset, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(java.lang.Math.fma(axisY, _t4, -(axisZ * _t5)), _t1, java.lang.Math.fma(_t2, axisX * _t8, pivotX))));
        dest.put(destOffset + 1, java.lang.Math.fma(_t5, _t0, java.lang.Math.fma(java.lang.Math.fma(axisZ, _t3, -(axisX * _t4)), _t1, java.lang.Math.fma(_t2, axisY * _t8, pivotY))));
        dest.put(destOffset + 2, java.lang.Math.fma(_t4, _t0, java.lang.Math.fma(java.lang.Math.fma(axisX, _t5, -(axisY * _t3)), _t1, java.lang.Math.fma(_t2, axisZ * _t8, pivotZ))));
        return dest;
    }

    /** {@code rotateAxisAround_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer rotateAxisAround_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = src.get(srcOffset) - pivotX;
        double _t4 = src.get(srcOffset + 2) - pivotZ;
        double _t5 = src.get(srcOffset + 1) - pivotY;
        double _t8 = ((axisZ) * (_t4) + (((axisX) * (_t3) + (axisY * _t5))));
        dest.put(destOffset, ((_t3) * (_t0) + (((((axisY) * (_t4) - (axisZ * _t5))) * (_t1) + (((_t2) * (axisX * _t8) + (pivotX)))))));
        dest.put(destOffset + 1, ((_t5) * (_t0) + (((((axisZ) * (_t3) - (axisX * _t4))) * (_t1) + (((_t2) * (axisY * _t8) + (pivotY)))))));
        dest.put(destOffset + 2, ((_t4) * (_t0) + (((((axisX) * (_t5) - (axisY * _t3))) * (_t1) + (((_t2) * (axisZ * _t8) + (pivotZ)))))));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxisAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 8L;
        Double3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, _axisBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxisAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Double3Ops.rotateAxisAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateAxisAround_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxisAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxisAround_apiGet_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
        double _axisx = axis.get(axisOffset);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _pivotx = pivot.get(pivotOffset);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _pivotz = pivot.get(pivotOffset + 2);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = src.get(srcOffset) - _pivotx;
        double _t4 = src.get(srcOffset + 2) - _pivotz;
        double _t5 = src.get(srcOffset + 1) - _pivoty;
        double _t8 = java.lang.Math.fma(_axisz, _t4, java.lang.Math.fma(_axisx, _t3, _axisy * _t5));
        dest.put(destOffset, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisy, _t4, -(_axisz * _t5)), _t1, java.lang.Math.fma(_t2, _axisx * _t8, _pivotx))));
        dest.put(destOffset + 1, java.lang.Math.fma(_t5, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisz, _t3, -(_axisx * _t4)), _t1, java.lang.Math.fma(_t2, _axisy * _t8, _pivoty))));
        dest.put(destOffset + 2, java.lang.Math.fma(_t4, _t0, java.lang.Math.fma(java.lang.Math.fma(_axisx, _t5, -(_axisy * _t3)), _t1, java.lang.Math.fma(_t2, _axisz * _t8, _pivotz))));
        return dest;
    }

    /** {@code rotateAxisAround_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer rotateAxisAround_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        double _axisx = axis.get(axisOffset);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _pivotx = pivot.get(pivotOffset);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _pivotz = pivot.get(pivotOffset + 2);
        double _t0 = Math.cosWithSin(angle);
        double _t1 = Math.sinWithCos(angle);
        double _t2 = 1.0 - _t0;
        double _t3 = src.get(srcOffset) - _pivotx;
        double _t4 = src.get(srcOffset + 2) - _pivotz;
        double _t5 = src.get(srcOffset + 1) - _pivoty;
        double _t8 = ((_axisz) * (_t4) + (((_axisx) * (_t3) + (_axisy * _t5))));
        dest.put(destOffset, ((_t3) * (_t0) + (((((_axisy) * (_t4) - (_axisz * _t5))) * (_t1) + (((_t2) * (_axisx * _t8) + (_pivotx)))))));
        dest.put(destOffset + 1, ((_t5) * (_t0) + (((((_axisz) * (_t3) - (_axisx * _t4))) * (_t1) + (((_t2) * (_axisy * _t8) + (_pivoty)))))));
        dest.put(destOffset + 2, ((_t4) * (_t0) + (((((_axisx) * (_t5) - (_axisy * _t3))) * (_t1) + (((_t2) * (_axisz * _t8) + (_pivotz)))))));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateInverse_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateInverse_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.rotateInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quatX, quatY, quatZ, quatW);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateInverse_apiGet(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateInverse_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_apiGet_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t9 = 2.0 * java.lang.Math.fma(quatX, _selfz, -(quatZ * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(quatY, _selfx, -(quatX * _selfy));
        double _t11 = 2.0 * java.lang.Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.put(destOffset, java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.put(destOffset + 1, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, _selfy))));
        dest.put(destOffset + 2, java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfz))));
        return dest;
    }

    /** {@code rotateInverse_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer rotateInverse_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t9 = 2.0 * ((quatX) * (_selfz) - (quatZ * _selfx));
        double _t10 = 2.0 * ((quatY) * (_selfx) - (quatX * _selfy));
        double _t11 = 2.0 * ((quatZ) * (_selfy) - (quatY * _selfz));
        dest.put(destOffset, ((quatZ) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatY) * (_t10)))));
        dest.put(destOffset + 1, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfy))) - (quatZ) * (_t11)))));
        dest.put(destOffset + 2, ((quatY) * (_t11) + (((((quatW) * (_t10) + (_selfz))) - (quatX) * (_t9)))));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateInverse_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset * 8L;
        Double3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateInverse_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && quat.hasArray() && quatOffset >= 0 && quatOffset <= quat.limit() - 4) {
            Double3Ops.rotateInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, quat.array(), quat.arrayOffset() + quatOffset);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateInverse_apiGet(dest, destOffset, src, srcOffset, quat, quatOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateInverse_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_apiGet_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _quatx = quat.get(quatOffset);
        double _quaty = quat.get(quatOffset + 1);
        double _quatz = quat.get(quatOffset + 2);
        double _quatw = quat.get(quatOffset + 3);
        double _t9 = 2.0 * java.lang.Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        double _t10 = 2.0 * java.lang.Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        double _t11 = 2.0 * java.lang.Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.put(destOffset, java.lang.Math.fma(_quatz, _t9, java.lang.Math.fma(-_quaty, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.put(destOffset + 1, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quatz, _t11, java.lang.Math.fma(_quatw, _t9, _selfy))));
        dest.put(destOffset + 2, java.lang.Math.fma(_quaty, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfz))));
        return dest;
    }

    /** {@code rotateInverse_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.DoubleBuffer rotateInverse_apiGet_mulAdd(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _quatx = quat.get(quatOffset);
        double _quaty = quat.get(quatOffset + 1);
        double _quatz = quat.get(quatOffset + 2);
        double _quatw = quat.get(quatOffset + 3);
        double _t9 = 2.0 * ((_quatx) * (_selfz) - (_quatz * _selfx));
        double _t10 = 2.0 * ((_quaty) * (_selfx) - (_quatx * _selfy));
        double _t11 = 2.0 * ((_quatz) * (_selfy) - (_quaty * _selfz));
        dest.put(destOffset, ((_quatz) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quaty) * (_t10)))));
        dest.put(destOffset + 1, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfy))) - (_quatz) * (_t11)))));
        dest.put(destOffset + 2, ((_quaty) * (_t11) + (((((_quatw) * (_t10) + (_selfz))) - (_quatx) * (_t9)))));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.rotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, src.get(srcOffset));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t0, -(_selfz * _t1)));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfy, _t1, _selfz * _t0));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, src.get(srcOffset));
            dest.put(destOffset + 1, ((_selfy) * (_t0) - (_selfz * _t1)));
            dest.put(destOffset + 2, ((_selfy) * (_t1) + (_selfz * _t0)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer rotateXAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, angle, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY, double pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.rotateXAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY, pivotZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateXAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotY, double pivotZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(srcOffset + 1) - pivotY;
            double _t3 = src.get(srcOffset + 2) - pivotZ;
            dest.put(destOffset, src.get(srcOffset));
            dest.put(destOffset + 1, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotY)));
            dest.put(destOffset + 2, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotZ)));
            return dest;
        } else {
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(srcOffset + 1) - pivotY;
            double _t3 = src.get(srcOffset + 2) - pivotZ;
            dest.put(destOffset, src.get(srcOffset));
            dest.put(destOffset + 1, ((_t2) * (_t0) + (((pivotY) - (_t3) * (_t1)))));
            dest.put(destOffset + 2, ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotZ)))));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer rotateXAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 8L;
        Double3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Double3Ops.rotateXAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateXAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _pivoty = pivot.get(pivotOffset + 1);
            double _pivotz = pivot.get(pivotOffset + 2);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(srcOffset + 1) - _pivoty;
            double _t3 = src.get(srcOffset + 2) - _pivotz;
            dest.put(destOffset, src.get(srcOffset));
            dest.put(destOffset + 1, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, _pivoty)));
            dest.put(destOffset + 2, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, _pivotz)));
            return dest;
        } else {
            double _pivoty = pivot.get(pivotOffset + 1);
            double _pivotz = pivot.get(pivotOffset + 2);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(srcOffset + 1) - _pivoty;
            double _t3 = src.get(srcOffset + 2) - _pivotz;
            dest.put(destOffset, src.get(srcOffset));
            dest.put(destOffset + 1, ((_t2) * (_t0) + (((_pivoty) - (_t3) * (_t1)))));
            dest.put(destOffset + 2, ((_t2) * (_t1) + (((_t3) * (_t0) + (_pivotz)))));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer rotateY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.rotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, java.lang.Math.fma(_selfx, _t0, _selfz * _t1));
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t0, -(_selfx * _t1)));
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, ((_selfx) * (_t0) + (_selfz * _t1)));
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, ((_selfz) * (_t0) - (_selfx * _t1)));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer rotateYAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY, double pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.rotateYAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY, pivotZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateYAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(srcOffset) - pivotX;
            double _t3 = src.get(srcOffset + 2) - pivotZ;
            dest.put(destOffset, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, pivotX)));
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(-_t2, _t1, pivotZ)));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(srcOffset) - pivotX;
            double _t3 = src.get(srcOffset + 2) - pivotZ;
            dest.put(destOffset, ((_t2) * (_t0) + (((_t3) * (_t1) + (pivotX)))));
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, ((_t3) * (_t0) + (((pivotZ) - (_t2) * (_t1)))));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer rotateYAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 8L;
        Double3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Double3Ops.rotateYAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateYAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfy = src.get(srcOffset + 1);
            double _pivotx = pivot.get(pivotOffset);
            double _pivotz = pivot.get(pivotOffset + 2);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(srcOffset) - _pivotx;
            double _t3 = src.get(srcOffset + 2) - _pivotz;
            dest.put(destOffset, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(_t3, _t1, _pivotx)));
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, java.lang.Math.fma(_t3, _t0, java.lang.Math.fma(-_t2, _t1, _pivotz)));
            return dest;
        } else {
            double _selfy = src.get(srcOffset + 1);
            double _pivotx = pivot.get(pivotOffset);
            double _pivotz = pivot.get(pivotOffset + 2);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(srcOffset) - _pivotx;
            double _t3 = src.get(srcOffset + 2) - _pivotz;
            dest.put(destOffset, ((_t2) * (_t0) + (((_t3) * (_t1) + (_pivotx)))));
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, ((_t3) * (_t0) + (((_pivotz) - (_t2) * (_t1)))));
            return dest;
        }
    }

    public static java.nio.DoubleBuffer rotateZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.rotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, java.lang.Math.fma(_selfx, _t0, -(_selfy * _t1)));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfx, _t1, _selfy * _t0));
            dest.put(destOffset + 2, _selfz);
            return dest;
        } else {
            double _selfx = src.get(srcOffset);
            double _selfy = src.get(srcOffset + 1);
            double _selfz = src.get(srcOffset + 2);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            dest.put(destOffset, ((_selfx) * (_t0) - (_selfy * _t1)));
            dest.put(destOffset + 1, ((_selfx) * (_t1) + (_selfy * _t0)));
            dest.put(destOffset + 2, _selfz);
            return dest;
        }
    }

    public static java.nio.DoubleBuffer rotateZAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY, double pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            Double3Ops.rotateZAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY, pivotZ);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateZAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfz = src.get(srcOffset + 2);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(srcOffset) - pivotX;
            double _t3 = src.get(srcOffset + 1) - pivotY;
            dest.put(destOffset, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, pivotX)));
            dest.put(destOffset + 1, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, pivotY)));
            dest.put(destOffset + 2, _selfz);
            return dest;
        } else {
            double _selfz = src.get(srcOffset + 2);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(srcOffset) - pivotX;
            double _t3 = src.get(srcOffset + 1) - pivotY;
            dest.put(destOffset, ((_t2) * (_t0) + (((pivotX) - (_t3) * (_t1)))));
            dest.put(destOffset + 1, ((_t2) * (_t1) + (((_t3) * (_t0) + (pivotY)))));
            dest.put(destOffset + 2, _selfz);
            return dest;
        }
    }

    public static java.nio.DoubleBuffer rotateZAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 8L;
        Double3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Double3Ops.rotateZAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        Double3OpsKernelsTypedBuffer.rotateZAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfz = src.get(srcOffset + 2);
            double _pivotx = pivot.get(pivotOffset);
            double _pivoty = pivot.get(pivotOffset + 1);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(srcOffset) - _pivotx;
            double _t3 = src.get(srcOffset + 1) - _pivoty;
            dest.put(destOffset, java.lang.Math.fma(_t2, _t0, java.lang.Math.fma(-_t3, _t1, _pivotx)));
            dest.put(destOffset + 1, java.lang.Math.fma(_t2, _t1, java.lang.Math.fma(_t3, _t0, _pivoty)));
            dest.put(destOffset + 2, _selfz);
            return dest;
        } else {
            double _selfz = src.get(srcOffset + 2);
            double _pivotx = pivot.get(pivotOffset);
            double _pivoty = pivot.get(pivotOffset + 1);
            double _t0 = Math.cosWithSin(angle);
            double _t1 = Math.sinWithCos(angle);
            double _t2 = src.get(srcOffset) - _pivotx;
            double _t3 = src.get(srcOffset + 1) - _pivoty;
            dest.put(destOffset, ((_t2) * (_t0) + (((_pivotx) - (_t3) * (_t1)))));
            dest.put(destOffset + 1, ((_t2) * (_t1) + (((_t3) * (_t0) + (_pivoty)))));
            dest.put(destOffset + 2, _selfz);
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
