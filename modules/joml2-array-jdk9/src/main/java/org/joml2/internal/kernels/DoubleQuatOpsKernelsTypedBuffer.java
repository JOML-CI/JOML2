// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link DoubleQuatOps} whose leading storage
 * parameter is a typed {@link java.nio.DoubleBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code DoubleQuatOps} and its sibling kernel units. Not public API.
 */
public final class DoubleQuatOpsKernelsTypedBuffer {
    private DoubleQuatOpsKernelsTypedBuffer() {}

    public static java.nio.DoubleBuffer invert_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invert(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invert_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t3_inv = 1.0 / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        dest.put(destOffset, -(_selfx * _t3_inv));
        dest.put(destOffset + 1, -(_selfy * _t3_inv));
        dest.put(destOffset + 2, -(_selfz * _t3_inv));
        dest.put(destOffset + 3, _selfw * _t3_inv);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invertProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invertProduct_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t20 = Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherZ, _selfy, -(otherY * _selfz));
        double _t21 = Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz);
        double _t22 = Math.fma(otherY, _selfx, otherZ * _selfw) + Math.fma(otherW, _selfz, -(otherX * _selfy));
        double _t23 = Math.fma(otherX, _selfz, otherW * _selfy) + Math.fma(otherY, _selfw, -(otherZ * _selfx));
        double _t27_inv = 1.0 / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
        dest.put(destOffset, -(_t20 * _t27_inv));
        dest.put(destOffset + 1, -(_t23 * _t27_inv));
        dest.put(destOffset + 2, -(_t22 * _t27_inv));
        dest.put(destOffset + 3, _t21 * _t27_inv);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        DoubleQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            DoubleQuatOps.invertProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invertProduct_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        double _t20 = Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_otherz, _selfy, -(_othery * _selfz));
        double _t21 = Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz);
        double _t22 = Math.fma(_othery, _selfx, _otherz * _selfw) + Math.fma(_otherw, _selfz, -(_otherx * _selfy));
        double _t23 = Math.fma(_otherx, _selfz, _otherw * _selfy) + Math.fma(_othery, _selfw, -(_otherz * _selfx));
        double _t27_inv = 1.0 / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
        dest.put(destOffset, -(_t20 * _t27_inv));
        dest.put(destOffset + 1, -(_t23 * _t27_inv));
        dest.put(destOffset + 2, -(_t22 * _t27_inv));
        dest.put(destOffset + 3, _t21 * _t27_inv);
        return dest;
    }

    public static java.nio.DoubleBuffer add_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer add_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
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
        DoubleQuatOpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer add_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            DoubleQuatOps.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
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

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
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

    public static java.nio.DoubleBuffer negate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
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
        DoubleQuatOpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
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
        DoubleQuatOpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            DoubleQuatOps.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
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

    public static java.nio.DoubleBuffer makeUniformRotation_unsafe(java.nio.DoubleBuffer dest, int destOffset, double u1, double u2, double u3) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeUniformRotation_unsafe(_destBase, u1, u2, u3);
        return dest;
    }

    public static java.nio.DoubleBuffer makeUniformRotation_api(java.nio.DoubleBuffer dest, int destOffset, double u1, double u2, double u3) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeUniformRotation(dest.array(), dest.arrayOffset() + destOffset, u1, u2, u3);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeUniformRotation_apiGet(dest, destOffset, u1, u2, u3);
        return dest;
    }

    public static java.nio.DoubleBuffer makeUniformRotation_apiGet(java.nio.DoubleBuffer dest, int destOffset, double u1, double u2, double u3) {
        double _t0 = java.lang.Math.sqrt(u1);
        double _t1 = u2 * 6.283185307179586;
        double _t3 = u3 * 6.283185307179586;
        double _t4 = Math.sin(_t1);
        double _t5 = java.lang.Math.sqrt(1.0 - u1);
        double _t6 = Math.sin(_t3);
        dest.put(destOffset, _t4 * _t5);
        dest.put(destOffset + 1, Math.cosFromSin(_t4, _t1) * _t5);
        dest.put(destOffset + 2, _t6 * _t0);
        dest.put(destOffset + 3, Math.cosFromSin(_t6, _t3) * _t0);
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ, double vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ, double vW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.set(dest.array(), dest.arrayOffset() + destOffset, vX, vY, vZ, vW);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.set_apiGet(dest, destOffset, vX, vY, vZ, vW);
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
        DoubleQuatOpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4) {
            DoubleQuatOps.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
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

    public static java.nio.DoubleBuffer makeFromDualQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromDualQuat_api(java.nio.DoubleBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeFromDualQuat(dest.array(), dest.arrayOffset() + destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeFromDualQuat_apiGet(dest, destOffset, dqRX, dqRY, dqRZ, dqRW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromDualQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW) {
        dest.put(destOffset, dqRX);
        dest.put(destOffset + 1, dqRY);
        dest.put(destOffset + 2, dqRZ);
        dest.put(destOffset + 3, dqRW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromMatrixMat3x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeFromMatrixMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromMatrixMat3x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 9) {
            DoubleQuatOps.makeFromMatrixMat3x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeFromMatrixMat3x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromMatrixMat3x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _m00 = m.get(mOffset);
        double _m10 = m.get(mOffset + 1);
        double _m20 = m.get(mOffset + 2);
        double _m01 = m.get(mOffset + 3);
        double _m11 = m.get(mOffset + 4);
        double _m21 = m.get(mOffset + 5);
        double _m02 = m.get(mOffset + 6);
        double _m12 = m.get(mOffset + 7);
        double _m22 = m.get(mOffset + 8);
        double _t0 = _m00 + _m11;
        double _t10 = _m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_m00 - (_m11 + _m22));
        double _t16 = 1.0 + (_m11 - (_m00 + _m22));
        double _t17 = 1.0 + (_m22 - _t0);
        return makeFromMatrixMat3x3_apiGet_sd2af1d87_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x3_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeFromMatrixMat3x3_apiGet_sd2af1d87_1(java.nio.DoubleBuffer dest, int destOffset, double _m00, double _m11, double _m22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            dest.put(destOffset, _sp0 * _t1);
            dest.put(destOffset + 1, _sp0 * _t7);
            dest.put(destOffset + 2, _sp0 * _t9);
            dest.put(destOffset + 3, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
                dest.put(destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.put(destOffset + 1, _sp3 * _t4);
                dest.put(destOffset + 2, _sp3 * _t6);
                dest.put(destOffset + 3, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.put(destOffset, _sp1 * _t4);
                    dest.put(destOffset + 1, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.put(destOffset + 2, _sp1 * _t8);
                    dest.put(destOffset + 3, _sp1 * _t7);
                } else {
                    dest.put(destOffset, _sp2 * _t6);
                    dest.put(destOffset + 1, _sp2 * _t8);
                    dest.put(destOffset + 2, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.put(destOffset + 3, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromMatrixMat3x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeFromMatrixMat3x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromMatrixMat3x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 12) {
            DoubleQuatOps.makeFromMatrixMat3x4(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeFromMatrixMat3x4_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromMatrixMat3x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _m00 = m.get(mOffset);
        double _m01 = m.get(mOffset + 1);
        double _m02 = m.get(mOffset + 2);
        double _m10 = m.get(mOffset + 4);
        double _m11 = m.get(mOffset + 5);
        double _m12 = m.get(mOffset + 6);
        double _m20 = m.get(mOffset + 8);
        double _m21 = m.get(mOffset + 9);
        double _m22 = m.get(mOffset + 10);
        double _t0 = _m00 + _m11;
        double _t10 = _m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_m00 - (_m11 + _m22));
        double _t16 = 1.0 + (_m11 - (_m00 + _m22));
        double _t17 = 1.0 + (_m22 - _t0);
        return makeFromMatrixMat3x4_apiGet_sba2f054a_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x4_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeFromMatrixMat3x4_apiGet_sba2f054a_1(java.nio.DoubleBuffer dest, int destOffset, double _m00, double _m11, double _m22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            dest.put(destOffset, _sp0 * _t1);
            dest.put(destOffset + 1, _sp0 * _t7);
            dest.put(destOffset + 2, _sp0 * _t9);
            dest.put(destOffset + 3, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
                dest.put(destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.put(destOffset + 1, _sp3 * _t4);
                dest.put(destOffset + 2, _sp3 * _t6);
                dest.put(destOffset + 3, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.put(destOffset, _sp1 * _t4);
                    dest.put(destOffset + 1, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.put(destOffset + 2, _sp1 * _t8);
                    dest.put(destOffset + 3, _sp1 * _t7);
                } else {
                    dest.put(destOffset, _sp2 * _t6);
                    dest.put(destOffset + 1, _sp2 * _t8);
                    dest.put(destOffset + 2, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.put(destOffset + 3, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromMatrixMat4x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeFromMatrixMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromMatrixMat4x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 16) {
            DoubleQuatOps.makeFromMatrixMat4x4(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeFromMatrixMat4x4_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromMatrixMat4x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _m00 = m.get(mOffset);
        double _m10 = m.get(mOffset + 1);
        double _m20 = m.get(mOffset + 2);
        double _m01 = m.get(mOffset + 4);
        double _m11 = m.get(mOffset + 5);
        double _m21 = m.get(mOffset + 6);
        double _m02 = m.get(mOffset + 8);
        double _m12 = m.get(mOffset + 9);
        double _m22 = m.get(mOffset + 10);
        double _t0 = _m00 + _m11;
        double _t10 = _m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_m00 - (_m11 + _m22));
        double _t16 = 1.0 + (_m11 - (_m00 + _m22));
        double _t17 = 1.0 + (_m22 - _t0);
        return makeFromMatrixMat4x4_apiGet_s7bb8447f_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat4x4_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeFromMatrixMat4x4_apiGet_s7bb8447f_1(java.nio.DoubleBuffer dest, int destOffset, double _m00, double _m11, double _m22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            dest.put(destOffset, _sp0 * _t1);
            dest.put(destOffset + 1, _sp0 * _t7);
            dest.put(destOffset + 2, _sp0 * _t9);
            dest.put(destOffset + 3, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > java.lang.Math.max(_m11, _m22)) {
                dest.put(destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.put(destOffset + 1, _sp3 * _t4);
                dest.put(destOffset + 2, _sp3 * _t6);
                dest.put(destOffset + 3, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.put(destOffset, _sp1 * _t4);
                    dest.put(destOffset + 1, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.put(destOffset + 2, _sp1 * _t8);
                    dest.put(destOffset + 3, _sp1 * _t7);
                } else {
                    dest.put(destOffset, _sp2 * _t6);
                    dest.put(destOffset + 1, _sp2 * _t8);
                    dest.put(destOffset + 2, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.put(destOffset + 3, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer toDualQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toDualQuat_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 8 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.toDualQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.toDualQuat_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toDualQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _selfy);
        dest.put(destOffset + 2, _selfz);
        dest.put(destOffset + 3, _selfw);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer toMatrix_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.toMatrix_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toMatrix_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.toMatrix(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.toMatrix_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toMatrix_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        dest.put(destOffset, Math.fma(-2.0, Math.fma(_selfy, _selfy, _t0), 1.0));
        dest.put(destOffset + 1, 2.0 * Math.fma(_selfx, _selfy, _t1));
        dest.put(destOffset + 2, 2.0 * Math.fma(_selfx, _selfz, -_t2));
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 2.0 * Math.fma(_selfx, _selfy, -_t1));
        dest.put(destOffset + 5, Math.fma(-2.0, Math.fma(_selfx, _selfx, _t0), 1.0));
        dest.put(destOffset + 6, 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 2.0 * Math.fma(_selfx, _selfz, _t2));
        dest.put(destOffset + 9, 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.put(destOffset + 10, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        return toMatrix_apiGet_se28b6448_1(dest, destOffset);
    }

    /** Piece 2 of {@code toMatrix_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer toMatrix_apiGet_se28b6448_1(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset + 11, 0.0);
        dest.put(destOffset + 12, 0.0);
        dest.put(destOffset + 13, 0.0);
        dest.put(destOffset + 14, 0.0);
        dest.put(destOffset + 15, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer toMatrix3x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.toMatrix3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toMatrix3x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.toMatrix3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.toMatrix3x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toMatrix3x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        dest.put(destOffset, Math.fma(-2.0, Math.fma(_selfy, _selfy, _t0), 1.0));
        dest.put(destOffset + 1, 2.0 * Math.fma(_selfx, _selfy, _t1));
        dest.put(destOffset + 2, 2.0 * Math.fma(_selfx, _selfz, -_t2));
        dest.put(destOffset + 3, 2.0 * Math.fma(_selfx, _selfy, -_t1));
        dest.put(destOffset + 4, Math.fma(-2.0, Math.fma(_selfx, _selfx, _t0), 1.0));
        dest.put(destOffset + 5, 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.put(destOffset + 6, 2.0 * Math.fma(_selfx, _selfz, _t2));
        dest.put(destOffset + 7, 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.put(destOffset + 8, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        return dest;
    }

    public static java.nio.DoubleBuffer toMatrix3x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.toMatrix3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toMatrix3x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.toMatrix3x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.toMatrix3x4_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toMatrix3x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfz * _selfw;
        double _t2 = _selfy * _selfw;
        dest.put(destOffset, Math.fma(-2.0, Math.fma(_selfy, _selfy, _t0), 1.0));
        dest.put(destOffset + 1, 2.0 * Math.fma(_selfx, _selfy, -_t1));
        dest.put(destOffset + 2, 2.0 * Math.fma(_selfx, _selfz, _t2));
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 2.0 * Math.fma(_selfx, _selfy, _t1));
        dest.put(destOffset + 5, Math.fma(-2.0, Math.fma(_selfx, _selfx, _t0), 1.0));
        dest.put(destOffset + 6, 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 2.0 * Math.fma(_selfx, _selfz, -_t2));
        dest.put(destOffset + 9, 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.put(destOffset + 10, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeSwingTwist_unsafe(java.nio.DoubleBuffer swing, int swingOffset, java.nio.DoubleBuffer twist, int twistOffset, java.nio.DoubleBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        long _swingBase = UnsafeOpsHolder.U.getLong(swing, UnsafeCopy.BB_ADDRESS_OFFSET) + swingOffset * 8L;
        long _twistBase = UnsafeOpsHolder.U.getLong(twist, UnsafeCopy.BB_ADDRESS_OFFSET) + twistOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.decomposeSwingTwist_unsafe(_swingBase, _twistBase, _srcBase, axisX, axisY, axisZ);
        return swing;
    }

    public static java.nio.DoubleBuffer decomposeSwingTwist_api(java.nio.DoubleBuffer swing, int swingOffset, java.nio.DoubleBuffer twist, int twistOffset, java.nio.DoubleBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        if (swing.hasArray() && swingOffset >= 0 && swingOffset <= swing.limit() - 4 && twist.hasArray() && twistOffset >= 0 && twistOffset <= twist.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.decomposeSwingTwist(swing.array(), swing.arrayOffset() + swingOffset, twist.array(), twist.arrayOffset() + twistOffset, src.array(), src.arrayOffset() + srcOffset, axisX, axisY, axisZ);
            return swing;
        }
        DoubleQuatOpsKernelsTypedBuffer.decomposeSwingTwist_apiGet(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
        return swing;
    }

    public static java.nio.DoubleBuffer decomposeSwingTwist_apiGet(java.nio.DoubleBuffer swing, int swingOffset, java.nio.DoubleBuffer twist, int twistOffset, java.nio.DoubleBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t2 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        double _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        swing.put(swingOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        swing.put(swingOffset + 1, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        swing.put(swingOffset + 2, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        return decomposeSwingTwist_apiGet_s5ed46dc8_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer decomposeSwingTwist_apiGet_s5ed46dc8_1(java.nio.DoubleBuffer swing, int swingOffset, java.nio.DoubleBuffer twist, int twistOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        swing.put(swingOffset + 3, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        twist.put(twistOffset, _t12);
        twist.put(twistOffset + 1, _t13);
        twist.put(twistOffset + 2, _t14);
        twist.put(twistOffset + 3, _t11);
        return swing;
    }

    public static java.nio.DoubleBuffer decomposeSwingTwist_unsafe(java.nio.DoubleBuffer swing, int swingOffset, java.nio.DoubleBuffer twist, int twistOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset) {
        long _swingBase = UnsafeOpsHolder.U.getLong(swing, UnsafeCopy.BB_ADDRESS_OFFSET) + swingOffset * 8L;
        long _twistBase = UnsafeOpsHolder.U.getLong(twist, UnsafeCopy.BB_ADDRESS_OFFSET) + twistOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 8L;
        DoubleQuatOpsKernelsAddress.decomposeSwingTwist_unsafe(_swingBase, _twistBase, _srcBase, _axisBase);
        return swing;
    }

    public static java.nio.DoubleBuffer decomposeSwingTwist_api(java.nio.DoubleBuffer swing, int swingOffset, java.nio.DoubleBuffer twist, int twistOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset) {
        if (swing.hasArray() && swingOffset >= 0 && swingOffset <= swing.limit() - 4 && twist.hasArray() && twistOffset >= 0 && twistOffset <= twist.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            DoubleQuatOps.decomposeSwingTwist(swing.array(), swing.arrayOffset() + swingOffset, twist.array(), twist.arrayOffset() + twistOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset);
            return swing;
        }
        DoubleQuatOpsKernelsTypedBuffer.decomposeSwingTwist_apiGet(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
        return swing;
    }

    public static java.nio.DoubleBuffer decomposeSwingTwist_apiGet(java.nio.DoubleBuffer swing, int swingOffset, java.nio.DoubleBuffer twist, int twistOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _axisx = axis.get(axisOffset);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _t2 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        double _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        swing.put(swingOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        swing.put(swingOffset + 1, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        swing.put(swingOffset + 2, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        return decomposeSwingTwist_apiGet_s45cea2a8_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer decomposeSwingTwist_apiGet_s45cea2a8_1(java.nio.DoubleBuffer swing, int swingOffset, java.nio.DoubleBuffer twist, int twistOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t14) {
        swing.put(swingOffset + 3, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        twist.put(twistOffset, _t12);
        twist.put(twistOffset + 1, _t13);
        twist.put(twistOffset + 2, _t14);
        twist.put(twistOffset + 3, _t11);
        return swing;
    }

    public static java.nio.DoubleBuffer getSwing_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        DoubleQuatOpsKernelsAddress.getSwing_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer getSwing_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.getSwing(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axisX, axisY, axisZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.getSwing_apiGet(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer getSwing_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t2 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        double _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        dest.put(destOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        dest.put(destOffset + 1, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        dest.put(destOffset + 2, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        dest.put(destOffset + 3, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        return dest;
    }

    public static java.nio.DoubleBuffer getSwing_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset) {
        DoubleQuatOpsKernelsAddress.getSwing_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getSwing_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            DoubleQuatOps.getSwing(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.getSwing_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getSwing_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _axisx = axis.get(axisOffset);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _t2 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        double _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t7 = _t2 * _t5;
        double _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-30) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0;
            _t12 = 0.0;
            _t13 = 0.0;
            _t14 = 0.0;
        }
        dest.put(destOffset, Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14)));
        dest.put(destOffset + 1, Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12)));
        dest.put(destOffset + 2, Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14)));
        dest.put(destOffset + 3, Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13)));
        return dest;
    }

    public static java.nio.DoubleBuffer getTwist_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        DoubleQuatOpsKernelsAddress.getTwist_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer getTwist_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.getTwist(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axisX, axisY, axisZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.getTwist_apiGet(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer getTwist_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double axisX, double axisY, double axisZ) {
        double _selfw = src.get(srcOffset + 3);
        double _t2 = Math.fma(axisZ, src.get(srcOffset + 2), Math.fma(axisX, src.get(srcOffset), axisY * src.get(srcOffset + 1)));
        double _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t6 = _t2 * _t5;
        if (_t4 > 1.0E-30) {
            dest.put(destOffset, axisX * _t6);
            dest.put(destOffset + 1, axisY * _t6);
            dest.put(destOffset + 2, axisZ * _t6);
            dest.put(destOffset + 3, _selfw * _t5);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 1.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer getTwist_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset) {
        DoubleQuatOpsKernelsAddress.getTwist_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getTwist_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            DoubleQuatOps.getTwist(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.getTwist_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getTwist_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset) {
        double _selfw = src.get(srcOffset + 3);
        double _axisx = axis.get(axisOffset);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _t2 = Math.fma(_axisz, src.get(srcOffset + 2), Math.fma(_axisx, src.get(srcOffset), _axisy * src.get(srcOffset + 1)));
        double _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        double _t5 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t6 = _t2 * _t5;
        if (_t4 > 1.0E-30) {
            dest.put(destOffset, _axisx * _t6);
            dest.put(destOffset + 1, _axisy * _t6);
            dest.put(destOffset + 2, _axisz * _t6);
            dest.put(destOffset + 3, _selfw * _t5);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 1.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer makeIdentity_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeIdentity_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeIdentity(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeIdentity_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeIdentity_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeZero_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeZero_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeZero(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeZero_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeZero_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
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
        DoubleQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            DoubleQuatOps.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
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

    public static java.nio.DoubleBuffer nlerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer nlerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.nlerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.nlerp_apiGet(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer nlerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t4 = Math.fma(alpha, targetW - _selfw, _selfw);
        double _t5 = Math.fma(alpha, targetZ - _selfz, _selfz);
        double _t6 = Math.fma(alpha, targetX - _selfx, _selfx);
        double _t7 = Math.fma(alpha, targetY - _selfy, _selfy);
        double _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0) {
            dest.put(destOffset, _t6 * _t12);
            dest.put(destOffset + 1, _t7 * _t12);
            dest.put(destOffset + 2, _t5 * _t12);
            dest.put(destOffset + 3, _t4 * _t12);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer nlerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 8L;
        DoubleQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer nlerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            DoubleQuatOps.nlerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, target.array(), target.arrayOffset() + targetOffset, alpha);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.nlerp_apiGet(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer nlerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double alpha) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t4 = Math.fma(alpha, target.get(targetOffset + 3) - _selfw, _selfw);
        double _t5 = Math.fma(alpha, target.get(targetOffset + 2) - _selfz, _selfz);
        double _t6 = Math.fma(alpha, target.get(targetOffset) - _selfx, _selfx);
        double _t7 = Math.fma(alpha, target.get(targetOffset + 1) - _selfy, _selfy);
        double _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0) {
            dest.put(destOffset, _t6 * _t12);
            dest.put(destOffset + 1, _t7 * _t12);
            dest.put(destOffset + 2, _t5 * _t12);
            dest.put(destOffset + 3, _t4 * _t12);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer nlerpShortest_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.nlerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer nlerpShortest_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.nlerpShortest(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.nlerpShortest_apiGet(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer nlerpShortest_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t17, _t18, _t19, _t20;
        if (-Math.fma(_selfw, targetW, Math.fma(_selfz, targetZ, Math.fma(_selfx, targetX, _selfy * targetY))) > 0.0) {
            _t17 = Math.fma(alpha, -targetW - _selfw, _selfw);
            _t18 = Math.fma(alpha, -targetZ - _selfz, _selfz);
            _t19 = Math.fma(alpha, -targetX - _selfx, _selfx);
            _t20 = Math.fma(alpha, -targetY - _selfy, _selfy);
        } else {
            _t17 = Math.fma(alpha, targetW - _selfw, _selfw);
            _t18 = Math.fma(alpha, targetZ - _selfz, _selfz);
            _t19 = Math.fma(alpha, targetX - _selfx, _selfx);
            _t20 = Math.fma(alpha, targetY - _selfy, _selfy);
        }
        double _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        return nlerpShortest_apiGet_sa081597e_1(dest, destOffset, _t17, _t18, _t19, _t20, _t24, (1.0 / java.lang.Math.sqrt(_t24)));
    }

    /** Piece 2 of {@code nlerpShortest_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer nlerpShortest_apiGet_sa081597e_1(java.nio.DoubleBuffer dest, int destOffset, double _t17, double _t18, double _t19, double _t20, double _t24, double _t25) {
        if (_t24 != 0.0) {
            dest.put(destOffset, _t19 * _t25);
            dest.put(destOffset + 1, _t20 * _t25);
            dest.put(destOffset + 2, _t18 * _t25);
            dest.put(destOffset + 3, _t17 * _t25);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer nlerpShortest_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 8L;
        DoubleQuatOpsKernelsAddress.nlerpShortest_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer nlerpShortest_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            DoubleQuatOps.nlerpShortest(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, target.array(), target.arrayOffset() + targetOffset, alpha);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.nlerpShortest_apiGet(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer nlerpShortest_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double alpha) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _targetx = target.get(targetOffset);
        double _targety = target.get(targetOffset + 1);
        double _targetz = target.get(targetOffset + 2);
        double _targetw = target.get(targetOffset + 3);
        double _t17, _t18, _t19, _t20;
        if (-Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety))) > 0.0) {
            _t17 = Math.fma(alpha, -_targetw - _selfw, _selfw);
            _t18 = Math.fma(alpha, -_targetz - _selfz, _selfz);
            _t19 = Math.fma(alpha, -_targetx - _selfx, _selfx);
            _t20 = Math.fma(alpha, -_targety - _selfy, _selfy);
        } else {
            _t17 = Math.fma(alpha, _targetw - _selfw, _selfw);
            _t18 = Math.fma(alpha, _targetz - _selfz, _selfz);
            _t19 = Math.fma(alpha, _targetx - _selfx, _selfx);
            _t20 = Math.fma(alpha, _targety - _selfy, _selfy);
        }
        double _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        return nlerpShortest_apiGet_s240e823d_1(dest, destOffset, _t17, _t18, _t19, _t20, _t24, (1.0 / java.lang.Math.sqrt(_t24)));
    }

    /** Piece 2 of {@code nlerpShortest_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer nlerpShortest_apiGet_s240e823d_1(java.nio.DoubleBuffer dest, int destOffset, double _t17, double _t18, double _t19, double _t20, double _t24, double _t25) {
        if (_t24 != 0.0) {
            dest.put(destOffset, _t19 * _t25);
            dest.put(destOffset + 1, _t20 * _t25);
            dest.put(destOffset + 2, _t18 * _t25);
            dest.put(destOffset + 3, _t17 * _t25);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = 1.0 - alpha;
        double _t1 = _selfw + targetW;
        double _t2 = _selfz + targetZ;
        double _t3 = _selfx + targetX;
        double _t4 = _selfy + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = java.lang.Math.min(4.0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        double _t12 = quatArcAngle(_t11);
        double _t13 = 4.0 - _t11;
        double _t19 = java.lang.Math.sqrt(_t13 * _t11);
        double _t21 = 2.0 / _t19;
        double _t26, _t27;
        if (_t19 > 2.0E-14) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0 - _t5;
                _t27 = _t5;
            }
        }
        dest.put(destOffset, Math.fma(_selfx, _t27, targetX * _t26));
        dest.put(destOffset + 1, Math.fma(_selfy, _t27, targetY * _t26));
        dest.put(destOffset + 2, Math.fma(_selfz, _t27, targetZ * _t26));
        dest.put(destOffset + 3, Math.fma(_selfw, _t27, targetW * _t26));
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 8L;
        DoubleQuatOpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            DoubleQuatOps.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, target.array(), target.arrayOffset() + targetOffset, alpha);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer slerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double alpha) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _targetx = target.get(targetOffset);
        double _targety = target.get(targetOffset + 1);
        double _targetz = target.get(targetOffset + 2);
        double _targetw = target.get(targetOffset + 3);
        double _t0 = 1.0 - alpha;
        double _t1 = _selfw + _targetw;
        double _t2 = _selfz + _targetz;
        double _t3 = _selfx + _targetx;
        double _t4 = _selfy + _targety;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = java.lang.Math.min(4.0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        double _t12 = quatArcAngle(_t11);
        double _t13 = 4.0 - _t11;
        double _t19 = java.lang.Math.sqrt(_t13 * _t11);
        double _t21 = 2.0 / _t19;
        double _t26, _t27;
        if (_t19 > 2.0E-14) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0 - _t5;
                _t27 = _t5;
            }
        }
        dest.put(destOffset, Math.fma(_selfx, _t27, _targetx * _t26));
        return slerp_apiGet_s7364fea0_1(dest, destOffset, _selfy, _selfz, _selfw, _targety, _targetz, _targetw, _t26, _t27);
    }

    /** Piece 2 of {@code slerp_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerp_apiGet_s7364fea0_1(java.nio.DoubleBuffer dest, int destOffset, double _selfy, double _selfz, double _selfw, double _targety, double _targetz, double _targetw, double _t26, double _t27) {
        dest.put(destOffset + 1, Math.fma(_selfy, _t27, _targety * _t26));
        dest.put(destOffset + 2, Math.fma(_selfz, _t27, _targetz * _t26));
        dest.put(destOffset + 3, Math.fma(_selfw, _t27, _targetw * _t26));
        return dest;
    }

    public static java.nio.DoubleBuffer slerpShortest_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer slerpShortest_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.slerpShortest(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.slerpShortest_apiGet(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer slerpShortest_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = 1.0 - alpha;
        double _t12 = Math.fma(_selfw, targetW, Math.fma(_selfz, targetZ, Math.fma(_selfx, targetX, _selfy * targetY)));
        double _t16 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t12)));
        double _t17 = Math.sin(_t16);
        double _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0) {
            _t21 = -targetW;
            _t22 = -targetZ;
            _t23 = -targetX;
            _t24 = -targetY;
        } else {
            _t21 = targetW;
            _t22 = targetZ;
            _t23 = targetX;
            _t24 = targetY;
        }
        return slerpShortest_apiGet_sd20ca329_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t17, 1.0 / _t17, Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerpShortest_apiGet_sd20ca329_1(java.nio.DoubleBuffer dest, int destOffset, double alpha, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t17, double _t17_inv, double _t19, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = Math.fma(_selfw, _t25, _t19 * _t21) * _t17_inv;
            _t43 = Math.fma(_selfz, _t25, _t19 * _t22) * _t17_inv;
            _t44 = Math.fma(_selfx, _t25, _t19 * _t23) * _t17_inv;
            _t45 = Math.fma(_selfy, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = Math.fma(alpha, _t21, _selfw * _t0);
            _t43 = Math.fma(alpha, _t22, _selfz * _t0);
            _t44 = Math.fma(alpha, _t23, _selfx * _t0);
            _t45 = Math.fma(alpha, _t24, _selfy * _t0);
        }
        double _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        double _t50 = (1.0 / java.lang.Math.sqrt(_t49));
        if (_t49 != 0.0) {
            dest.put(destOffset, _t50 * _t44);
            dest.put(destOffset + 1, _t50 * _t45);
            dest.put(destOffset + 2, _t50 * _t43);
            dest.put(destOffset + 3, _t50 * _t42);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer slerpShortest_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 8L;
        DoubleQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer slerpShortest_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            DoubleQuatOps.slerpShortest(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, target.array(), target.arrayOffset() + targetOffset, alpha);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.slerpShortest_apiGet(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return dest;
    }

    public static java.nio.DoubleBuffer slerpShortest_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double alpha) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _targetx = target.get(targetOffset);
        double _targety = target.get(targetOffset + 1);
        double _targetz = target.get(targetOffset + 2);
        double _targetw = target.get(targetOffset + 3);
        double _t0 = 1.0 - alpha;
        double _t12 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        double _t16 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t12)));
        double _t17 = Math.sin(_t16);
        double _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0) {
            _t21 = -_targetw;
            _t22 = -_targetz;
            _t23 = -_targetx;
            _t24 = -_targety;
        } else {
            _t21 = _targetw;
            _t22 = _targetz;
            _t23 = _targetx;
            _t24 = _targety;
        }
        return slerpShortest_apiGet_sa6a63de_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t17, 1.0 / _t17, Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer slerpShortest_apiGet_sa6a63de_1(java.nio.DoubleBuffer dest, int destOffset, double alpha, double _selfx, double _selfy, double _selfz, double _selfw, double _t0, double _t17, double _t17_inv, double _t19, double _t21, double _t22, double _t23, double _t24, double _t25) {
        double _t42, _t43, _t44, _t45;
        if (_t17 > 0.0) {
            _t42 = Math.fma(_selfw, _t25, _t19 * _t21) * _t17_inv;
            _t43 = Math.fma(_selfz, _t25, _t19 * _t22) * _t17_inv;
            _t44 = Math.fma(_selfx, _t25, _t19 * _t23) * _t17_inv;
            _t45 = Math.fma(_selfy, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = Math.fma(alpha, _t21, _selfw * _t0);
            _t43 = Math.fma(alpha, _t22, _selfz * _t0);
            _t44 = Math.fma(alpha, _t23, _selfx * _t0);
            _t45 = Math.fma(alpha, _t24, _selfy * _t0);
        }
        double _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        double _t50 = (1.0 / java.lang.Math.sqrt(_t49));
        if (_t49 != 0.0) {
            dest.put(destOffset, _t50 * _t44);
            dest.put(destOffset + 1, _t50 * _t45);
            dest.put(destOffset + 2, _t50 * _t43);
            dest.put(destOffset + 3, _t50 * _t42);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer squad_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.squad_unsafe(_destBase, _srcBase, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return dest;
    }

    public static java.nio.DoubleBuffer squad_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.squad(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.squad_apiGet(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return dest;
    }

    public static java.nio.DoubleBuffer squad_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t) {
        double _t0 = 1.0 - t;
        double _t3 = control0W + control1W;
        double _t4 = control0Z + control1Z;
        double _t5 = control0X + control1X;
        double _t6 = control0Y + control1Y;
        double _t7 = t < 0.5 ? 1.0 : 0.0;
        double _t12 = 1.0 - _t7;
        double _t25 = java.lang.Math.min(4.0, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6))));
        double _t27 = quatArcAngle(_t25);
        double _t29 = 4.0 - _t25;
        double _t41 = java.lang.Math.sqrt(_t29 * _t25);
        double _t45 = 2.0 / _t41;
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t45 * Math.sin(t * _t27);
            _t57 = _t45 * Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t1 = t + t;
        return squad_apiGet_sa057af75_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t0, _t7, _t12, _t55, _t57, _selfx, _selfy, _selfz, _selfw, _t1, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t0 * _t1);
    }

    /** Piece 2 of {@code squad_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer squad_apiGet_sa057af75_1(java.nio.DoubleBuffer dest, int destOffset, double control0X, double control0Y, double control0Z, double control0W, double control1X, double control1Y, double control1Z, double control1W, double targetX, double targetY, double targetZ, double targetW, double t, double _t0, double _t7, double _t12, double _t55, double _t57, double _selfx, double _selfy, double _selfz, double _selfw, double _t1, double _t8, double _t9, double _t10, double _t11, double _t13) {
        double _t26 = java.lang.Math.min(4.0, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11))));
        double _t28 = quatArcAngle(_t26);
        double _t30 = 4.0 - _t26;
        double _t43 = java.lang.Math.sqrt(_t30 * _t26);
        double _t46 = 2.0 / _t43;
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t46 * Math.sin(t * _t28);
            _t58 = _t46 * Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        double _t67 = Math.fma(control0X, _t57, control1X * _t55);
        double _t68 = Math.fma(control0W, _t57, control1W * _t55);
        double _t69 = Math.fma(_selfw, _t58, targetW * _t56);
        double _t70 = Math.fma(control0Z, _t57, control1Z * _t55);
        double _t71 = Math.fma(_selfz, _t58, targetZ * _t56);
        double _t72 = Math.fma(_selfx, _t58, targetX * _t56);
        return squad_apiGet_sa057af75_2(dest, destOffset, _t13, Math.fma(-_t0, _t1, 1.0), _t13 < 0.5 ? 1.0 : 0.0, _t67, _t68, _t69, _t70, _t71, _t72, Math.fma(control0Y, _t57, control1Y * _t55), Math.fma(_selfy, _t58, targetY * _t56), _t68 + _t69, _t70 + _t71, _t67 + _t72);
    }

    /** Piece 3 of {@code squad_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer squad_apiGet_sa057af75_2(java.nio.DoubleBuffer dest, int destOffset, double _t13, double _t14, double _t17, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74, double _t75, double _t76, double _t77) {
        double _t78 = _t73 + _t74;
        double _t83 = java.lang.Math.min(4.0, Math.fma(_t75, _t75, Math.fma(_t76, _t76, Math.fma(_t77, _t77, _t78 * _t78))));
        double _t84 = quatArcAngle(_t83);
        double _t85 = 4.0 - _t83;
        double _t91 = java.lang.Math.sqrt(_t85 * _t83);
        double _t93 = 2.0 / _t91;
        double _t98, _t99;
        if (_t91 > 2.0E-14) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0 - _t17;
                _t99 = _t17;
            }
        }
        dest.put(destOffset, Math.fma(_t67, _t98, _t72 * _t99));
        dest.put(destOffset + 1, Math.fma(_t73, _t98, _t74 * _t99));
        dest.put(destOffset + 2, Math.fma(_t70, _t98, _t71 * _t99));
        dest.put(destOffset + 3, Math.fma(_t68, _t98, _t69 * _t99));
        return dest;
    }

    public static java.nio.DoubleBuffer squad_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer control0, int control0Offset, java.nio.DoubleBuffer control1, int control1Offset, java.nio.DoubleBuffer target, int targetOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _control0Base = UnsafeOpsHolder.U.getLong(control0, UnsafeCopy.BB_ADDRESS_OFFSET) + control0Offset * 8L;
        long _control1Base = UnsafeOpsHolder.U.getLong(control1, UnsafeCopy.BB_ADDRESS_OFFSET) + control1Offset * 8L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 8L;
        DoubleQuatOpsKernelsAddress.squad_unsafe(_destBase, _srcBase, _control0Base, _control1Base, _targetBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer squad_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer control0, int control0Offset, java.nio.DoubleBuffer control1, int control1Offset, java.nio.DoubleBuffer target, int targetOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && control0.hasArray() && control0Offset >= 0 && control0Offset <= control0.limit() - 4 && control1.hasArray() && control1Offset >= 0 && control1Offset <= control1.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            DoubleQuatOps.squad(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, control0.array(), control0.arrayOffset() + control0Offset, control1.array(), control1.arrayOffset() + control1Offset, target.array(), target.arrayOffset() + targetOffset, t);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.squad_apiGet(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer squad_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer control0, int control0Offset, java.nio.DoubleBuffer control1, int control1Offset, java.nio.DoubleBuffer target, int targetOffset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _control0x = control0.get(control0Offset);
        double _control0y = control0.get(control0Offset + 1);
        double _control0z = control0.get(control0Offset + 2);
        double _control0w = control0.get(control0Offset + 3);
        double _control1x = control1.get(control1Offset);
        double _control1y = control1.get(control1Offset + 1);
        double _control1z = control1.get(control1Offset + 2);
        double _control1w = control1.get(control1Offset + 3);
        double _targetx = target.get(targetOffset);
        double _targety = target.get(targetOffset + 1);
        double _targetz = target.get(targetOffset + 2);
        double _targetw = target.get(targetOffset + 3);
        double _t0 = 1.0 - t;
        double _t1 = t + t;
        double _t7 = t < 0.5 ? 1.0 : 0.0;
        return squad_apiGet_s29a7368e_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, _t7, _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety, 1.0 - _t7, _t0 * _t1, Math.fma(-_t0, _t1, 1.0));
    }

    /** Piece 2 of {@code squad_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer squad_apiGet_s29a7368e_1(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _control0x, double _control0y, double _control0z, double _control0w, double _control1x, double _control1y, double _control1z, double _control1w, double _targetx, double _targety, double _targetz, double _targetw, double _t0, double _t3, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        return squad_apiGet_s29a7368e_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, _t7, _t12, _t13, _t14, _t13 < 0.5 ? 1.0 : 0.0, java.lang.Math.min(4.0, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6)))), java.lang.Math.min(4.0, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11)))));
    }

    /** Piece 3 of {@code squad_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer squad_apiGet_s29a7368e_2(java.nio.DoubleBuffer dest, int destOffset, double t, double _selfx, double _selfy, double _selfz, double _selfw, double _control0x, double _control0y, double _control0z, double _control0w, double _control1x, double _control1y, double _control1z, double _control1w, double _targetx, double _targety, double _targetz, double _targetw, double _t0, double _t7, double _t12, double _t13, double _t14, double _t17, double _t25, double _t26) {
        double _t27 = quatArcAngle(_t25);
        double _t28 = quatArcAngle(_t26);
        double _t29 = 4.0 - _t25;
        double _t30 = 4.0 - _t26;
        double _t41 = java.lang.Math.sqrt(_t29 * _t25);
        double _t43 = java.lang.Math.sqrt(_t30 * _t26);
        double _t45 = 2.0 / _t41;
        double _t46 = 2.0 / _t43;
        double _t55, _t57;
        if (_t41 > 2.0E-14) {
            _t55 = _t45 * Math.sin(t * _t27);
            _t57 = _t45 * Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        double _t56, _t58;
        if (_t43 > 2.0E-14) {
            _t56 = _t46 * Math.sin(t * _t28);
            _t58 = _t46 * Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        return squad_apiGet_s29a7368e_3(dest, destOffset, _t13, _t14, _t17, Math.fma(_control0x, _t57, _control1x * _t55), Math.fma(_control0w, _t57, _control1w * _t55), Math.fma(_selfw, _t58, _targetw * _t56), Math.fma(_control0z, _t57, _control1z * _t55), Math.fma(_selfz, _t58, _targetz * _t56), Math.fma(_selfx, _t58, _targetx * _t56), Math.fma(_control0y, _t57, _control1y * _t55), Math.fma(_selfy, _t58, _targety * _t56));
    }

    /** Piece 4 of {@code squad_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer squad_apiGet_s29a7368e_3(java.nio.DoubleBuffer dest, int destOffset, double _t13, double _t14, double _t17, double _t67, double _t68, double _t69, double _t70, double _t71, double _t72, double _t73, double _t74) {
        double _t75 = _t68 + _t69;
        double _t76 = _t70 + _t71;
        double _t77 = _t67 + _t72;
        double _t78 = _t73 + _t74;
        double _t83 = java.lang.Math.min(4.0, Math.fma(_t75, _t75, Math.fma(_t76, _t76, Math.fma(_t77, _t77, _t78 * _t78))));
        double _t84 = quatArcAngle(_t83);
        double _t85 = 4.0 - _t83;
        double _t91 = java.lang.Math.sqrt(_t85 * _t83);
        double _t93 = 2.0 / _t91;
        double _t98, _t99;
        if (_t91 > 2.0E-14) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0 - _t17;
                _t99 = _t17;
            }
        }
        dest.put(destOffset, Math.fma(_t67, _t98, _t72 * _t99));
        dest.put(destOffset + 1, Math.fma(_t73, _t98, _t74 * _t99));
        dest.put(destOffset + 2, Math.fma(_t70, _t98, _t71 * _t99));
        dest.put(destOffset + 3, Math.fma(_t68, _t98, _t69 * _t99));
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        dest.put(destOffset + 1, Math.fma(otherX, _selfz, otherW * _selfy) + Math.fma(otherY, _selfw, -(otherZ * _selfx)));
        dest.put(destOffset + 2, Math.fma(otherY, _selfx, otherZ * _selfw) + Math.fma(otherW, _selfz, -(otherX * _selfy)));
        dest.put(destOffset + 3, Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        DoubleQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            DoubleQuatOps.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        dest.put(destOffset + 1, Math.fma(_otherx, _selfz, _otherw * _selfy) + Math.fma(_othery, _selfw, -(_otherz * _selfx)));
        dest.put(destOffset + 2, Math.fma(_othery, _selfx, _otherz * _selfw) + Math.fma(_otherw, _selfz, -(_otherx * _selfy)));
        dest.put(destOffset + 3, Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherY, _selfz, -(otherZ * _selfy)));
        dest.put(destOffset + 1, Math.fma(otherY, _selfw, otherZ * _selfx) + Math.fma(otherW, _selfy, -(otherX * _selfz)));
        dest.put(destOffset + 2, Math.fma(otherX, _selfy, otherW * _selfz) + Math.fma(otherZ, _selfw, -(otherY * _selfx)));
        dest.put(destOffset + 3, Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        DoubleQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            DoubleQuatOps.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_othery, _selfz, -(_otherz * _selfy)));
        dest.put(destOffset + 1, Math.fma(_othery, _selfw, _otherz * _selfx) + Math.fma(_otherw, _selfy, -(_otherx * _selfz)));
        dest.put(destOffset + 2, Math.fma(_otherx, _selfy, _otherw * _selfz) + Math.fma(_otherz, _selfw, -(_othery * _selfx)));
        dest.put(destOffset + 3, Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, weight);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double weight) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, weight);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, weight);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double weight) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(weight, otherX, src.get(srcOffset)));
        dest.put(destOffset + 1, Math.fma(weight, otherY, _selfy));
        dest.put(destOffset + 2, Math.fma(weight, otherZ, _selfz));
        dest.put(destOffset + 3, Math.fma(weight, otherW, _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        DoubleQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            DoubleQuatOps.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, weight);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, Math.fma(weight, other.get(otherOffset), src.get(srcOffset)));
        dest.put(destOffset + 1, Math.fma(weight, _othery, _selfy));
        dest.put(destOffset + 2, Math.fma(weight, _otherz, _selfz));
        dest.put(destOffset + 3, Math.fma(weight, _otherw, _selfw));
        return dest;
    }

    public static double angle_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return DoubleQuatOpsKernelsAddress.angle_unsafe(_srcBase);
    }

    public static double angle_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return DoubleQuatOps.angle(src.array(), src.arrayOffset() + srcOffset);
        }
        return DoubleQuatOpsKernelsTypedBuffer.angle_apiGet(src, srcOffset);
    }

    public static double angle_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        return 2.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))), src.get(srcOffset + 3));
    }

    public static double angleTo_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return DoubleQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double angleTo_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return DoubleQuatOps.angleTo(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return DoubleQuatOpsKernelsTypedBuffer.angleTo_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static double angleTo_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9, _t10, _t11, _t12;
        if (-Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy))) > 0.0) {
            _t9 = -otherW;
            _t10 = -otherZ;
            _t11 = -otherX;
            _t12 = -otherY;
        } else {
            _t9 = otherW;
            _t10 = otherZ;
            _t11 = otherX;
            _t12 = otherY;
        }
        double _t13 = _selfw - _t9;
        double _t14 = _selfz - _t10;
        double _t15 = _selfx - _t11;
        double _t16 = _selfy - _t12;
        double _t17 = _selfw + _t9;
        double _t18 = _selfz + _t10;
        double _t19 = _selfx + _t11;
        double _t20 = _selfy + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    public static double angleTo_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return DoubleQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, _otherBase);
    }

    public static double angleTo_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return DoubleQuatOps.angleTo(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return DoubleQuatOpsKernelsTypedBuffer.angleTo_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double angleTo_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        double _t9, _t10, _t11, _t12;
        if (-Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy))) > 0.0) {
            _t9 = -_otherw;
            _t10 = -_otherz;
            _t11 = -_otherx;
            _t12 = -_othery;
        } else {
            _t9 = _otherw;
            _t10 = _otherz;
            _t11 = _otherx;
            _t12 = _othery;
        }
        double _t13 = _selfw - _t9;
        double _t14 = _selfz - _t10;
        double _t15 = _selfx - _t11;
        double _t16 = _selfy - _t12;
        double _t17 = _selfw + _t9;
        double _t18 = _selfz + _t10;
        double _t19 = _selfx + _t11;
        double _t20 = _selfy + _t12;
        return 4.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    public static java.nio.DoubleBuffer axis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.axis_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer axis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.axis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.axis_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer axis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0) {
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

    public static java.nio.DoubleBuffer calculateW_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.calculateW_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer calculateW_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.calculateW(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.calculateW_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer calculateW_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        dest.put(destOffset, _selfx);
        dest.put(destOffset + 1, _selfy);
        dest.put(destOffset + 2, _selfz);
        dest.put(destOffset + 3, java.lang.Math.sqrt(java.lang.Math.max(0.0, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0))))));
        return dest;
    }

    public static java.nio.DoubleBuffer conjugate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.conjugate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer conjugate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.conjugate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.conjugate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer conjugate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, -src.get(srcOffset));
        dest.put(destOffset + 1, -_selfy);
        dest.put(destOffset + 2, -_selfz);
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.DoubleBuffer conjugateBy_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.conjugateBy_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.DoubleBuffer conjugateBy_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.conjugateBy(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, qX, qY, qZ, qW);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.conjugateBy_apiGet(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.DoubleBuffer conjugateBy_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t20 = Math.fma(qX, _selfy, qW * _selfz) + Math.fma(qZ, _selfw, -(qY * _selfx));
        double _t21 = Math.fma(qY, _selfw, qZ * _selfx) + Math.fma(qW, _selfy, -(qX * _selfz));
        double _t22 = Math.fma(qX, _selfw, qW * _selfx) + Math.fma(qY, _selfz, -(qZ * _selfy));
        double _t23 = Math.fma(qW, _selfw, -(qX * _selfx)) - Math.fma(qY, _selfy, qZ * _selfz);
        dest.put(destOffset, Math.fma(qY, _t20, -(qZ * _t21)) + Math.fma(qW, _t22, -(qX * _t23)));
        dest.put(destOffset + 1, Math.fma(qZ, _t22, -(qY * _t23)) + Math.fma(qW, _t21, -(qX * _t20)));
        dest.put(destOffset + 2, Math.fma(qX, _t21, qW * _t20) + Math.fma(-qY, _t22, -(qZ * _t23)));
        dest.put(destOffset + 3, Math.fma(qX, _t22, qW * _t23) - Math.fma(-qZ, _t20, -(qY * _t21)));
        return dest;
    }

    public static java.nio.DoubleBuffer conjugateBy_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset * 8L;
        DoubleQuatOpsKernelsAddress.conjugateBy_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.nio.DoubleBuffer conjugateBy_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer q, int qOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && q.hasArray() && qOffset >= 0 && qOffset <= q.limit() - 4) {
            DoubleQuatOps.conjugateBy(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, q.array(), q.arrayOffset() + qOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.conjugateBy_apiGet(dest, destOffset, src, srcOffset, q, qOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer conjugateBy_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer q, int qOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _qx = q.get(qOffset);
        double _qy = q.get(qOffset + 1);
        double _qz = q.get(qOffset + 2);
        double _qw = q.get(qOffset + 3);
        double _t20 = Math.fma(_qx, _selfy, _qw * _selfz) + Math.fma(_qz, _selfw, -(_qy * _selfx));
        double _t21 = Math.fma(_qy, _selfw, _qz * _selfx) + Math.fma(_qw, _selfy, -(_qx * _selfz));
        double _t22 = Math.fma(_qx, _selfw, _qw * _selfx) + Math.fma(_qy, _selfz, -(_qz * _selfy));
        double _t23 = Math.fma(_qw, _selfw, -(_qx * _selfx)) - Math.fma(_qy, _selfy, _qz * _selfz);
        dest.put(destOffset, Math.fma(_qy, _t20, -(_qz * _t21)) + Math.fma(_qw, _t22, -(_qx * _t23)));
        dest.put(destOffset + 1, Math.fma(_qz, _t22, -(_qy * _t23)) + Math.fma(_qw, _t21, -(_qx * _t20)));
        return conjugateBy_apiGet_s92d39cea_1(dest, destOffset, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer conjugateBy_apiGet_s92d39cea_1(java.nio.DoubleBuffer dest, int destOffset, double _qx, double _qy, double _qz, double _qw, double _t20, double _t21, double _t22, double _t23) {
        dest.put(destOffset + 2, Math.fma(_qx, _t21, _qw * _t20) + Math.fma(-_qy, _t22, -(_qz * _t23)));
        dest.put(destOffset + 3, Math.fma(_qx, _t22, _qw * _t23) - Math.fma(-_qz, _t20, -(_qy * _t21)));
        return dest;
    }

    public static java.nio.DoubleBuffer difference_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer difference_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.difference(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.difference_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.DoubleBuffer difference_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t3_inv = 1.0 / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _sp1 = _t3_inv * _selfz;
        double _sp0 = _selfy * _t3_inv;
        dest.put(destOffset, (Math.fma(otherX, _selfw, -(otherW * _selfx)) + Math.fma(otherY, _selfz, -(otherZ * _selfy))) * _t3_inv);
        dest.put(destOffset + 1, -(otherW * _sp0) - otherX * _sp1 + Math.fma(otherY, _selfw, otherZ * _selfx) * _t3_inv);
        dest.put(destOffset + 2, (Math.fma(otherX, _selfy, -(otherW * _selfz)) + Math.fma(otherZ, _selfw, -(otherY * _selfx))) * _t3_inv);
        dest.put(destOffset + 3, Math.fma(otherX, _selfx, otherW * _selfw) * _t3_inv - (-(otherY * _sp0) - otherZ * _sp1));
        return dest;
    }

    public static java.nio.DoubleBuffer difference_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        DoubleQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer difference_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            DoubleQuatOps.difference(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.difference_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer difference_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _otherx = other.get(otherOffset);
        double _othery = other.get(otherOffset + 1);
        double _otherz = other.get(otherOffset + 2);
        double _otherw = other.get(otherOffset + 3);
        double _t3_inv = 1.0 / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        double _sp1 = _t3_inv * _selfz;
        double _sp0 = _selfy * _t3_inv;
        dest.put(destOffset, (Math.fma(_otherx, _selfw, -(_otherw * _selfx)) + Math.fma(_othery, _selfz, -(_otherz * _selfy))) * _t3_inv);
        dest.put(destOffset + 1, -(_otherw * _sp0) - _otherx * _sp1 + Math.fma(_othery, _selfw, _otherz * _selfx) * _t3_inv);
        dest.put(destOffset + 2, (Math.fma(_otherx, _selfy, -(_otherw * _selfz)) + Math.fma(_otherz, _selfw, -(_othery * _selfx))) * _t3_inv);
        dest.put(destOffset + 3, Math.fma(_otherx, _selfx, _otherw * _selfw) * _t3_inv - (-(_othery * _sp0) - _otherz * _sp1));
        return dest;
    }

    public static double dot_unsafe(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return DoubleQuatOpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static double dot_api(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return DoubleQuatOps.dot(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        return DoubleQuatOpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static double dot_apiGet(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double otherW) {
        return Math.fma(otherW, src.get(srcOffset + 3), Math.fma(otherZ, src.get(srcOffset + 2), Math.fma(otherX, src.get(srcOffset), otherY * src.get(srcOffset + 1))));
    }

    public static double dot_unsafe(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        return DoubleQuatOpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static double dot_api(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return DoubleQuatOps.dot(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        return DoubleQuatOpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, other, otherOffset);
    }

    public static double dot_apiGet(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        return Math.fma(other.get(otherOffset + 3), src.get(srcOffset + 3), Math.fma(other.get(otherOffset + 2), src.get(srcOffset + 2), Math.fma(other.get(otherOffset), src.get(srcOffset), other.get(otherOffset + 1) * src.get(srcOffset + 1))));
    }

    public static java.nio.DoubleBuffer exp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer exp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.exp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.exp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer exp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _t2 = java.lang.Math.min(Math.exp(src.get(srcOffset + 3)), 1.7976931348623157E308);
        double _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t5 = java.lang.Math.sqrt(_t4);
        double _t7 = Math.sin(_t5);
        double _t10 = _t2 * (_t7 / _t5);
        if (_t4 > 0.0) {
            dest.put(destOffset, _selfx * _t10);
            dest.put(destOffset + 1, _selfy * _t10);
            dest.put(destOffset + 2, _selfz * _t10);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        dest.put(destOffset + 3, Math.cosFromSin(_t7, _t5) * _t2);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXYZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.getEulerAnglesXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.getEulerAnglesXYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t1 = _selfy * _selfz;
        double _t3 = _selfz * _selfz;
        double _t8 = 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t9 = 2.0 * Math.fma(_selfx, _selfw, -_t1);
        double _t10 = Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-15) {
            dest.put(destOffset, Math.atan2(2.0 * Math.fma(_selfx, _selfw, _t1), Math.fma(-2.0, Math.fma(_selfx, _selfx, _t3), 1.0)));
            dest.put(destOffset + 2, 0.0);
        } else {
            dest.put(destOffset, Math.atan2(_t9, _t10));
            dest.put(destOffset + 2, Math.atan2(2.0 * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), Math.fma(-2.0, Math.fma(_selfy, _selfy, _t3), 1.0)));
        }
        dest.put(destOffset + 1, Math.atan2(_t8, java.lang.Math.sqrt(_t12)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXZY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.getEulerAnglesXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.getEulerAnglesXZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXZY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = _selfz * _selfz;
        double _t1 = _selfy * _selfz;
        double _t7 = 2.0 * Math.fma(_selfx, _selfw, _t1);
        double _t8 = 2.0 * Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        double _t9 = Math.fma(-2.0, Math.fma(_selfx, _selfx, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            dest.put(destOffset, Math.atan2(2.0 * Math.fma(_selfx, _selfw, -_t1), Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0)));
            dest.put(destOffset + 1, 0.0);
        } else {
            dest.put(destOffset, Math.atan2(_t7, _t9));
            dest.put(destOffset + 1, Math.atan2(2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw), Math.fma(-2.0, Math.fma(_selfy, _selfy, _t0), 1.0)));
        }
        dest.put(destOffset + 2, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYXZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.getEulerAnglesYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.getEulerAnglesYXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t3 = _selfz * _selfz;
        double _t8 = 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t9 = 2.0 * Math.fma(_selfx, _selfw, -(_selfy * _selfz));
        double _t10 = Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0);
        double _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-15) {
            dest.put(destOffset + 1, Math.atan2(2.0 * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), Math.fma(-2.0, Math.fma(_selfy, _selfy, _t3), 1.0)));
            dest.put(destOffset + 2, 0.0);
        } else {
            dest.put(destOffset + 1, Math.atan2(_t8, _t10));
            dest.put(destOffset + 2, Math.atan2(2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw), Math.fma(-2.0, Math.fma(_selfx, _selfx, _t3), 1.0)));
        }
        dest.put(destOffset, Math.atan2(_t9, java.lang.Math.sqrt(_t12)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYZX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.getEulerAnglesYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.getEulerAnglesYZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYZX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = _selfz * _selfz;
        double _t7 = 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t8 = 2.0 * Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        double _t9 = Math.fma(-2.0, Math.fma(_selfy, _selfy, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, Math.atan2(2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw), Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0)));
        } else {
            dest.put(destOffset, Math.atan2(2.0 * Math.fma(_selfx, _selfw, -(_selfy * _selfz)), Math.fma(-2.0, Math.fma(_selfx, _selfx, _t0), 1.0)));
            dest.put(destOffset + 1, Math.atan2(_t8, _t9));
        }
        dest.put(destOffset + 2, Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZXY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.getEulerAnglesZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.getEulerAnglesZXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZXY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t1 = _selfz * _selfz;
        double _t7 = 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t8 = 2.0 * Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        double _t9 = Math.fma(-2.0, Math.fma(_selfx, _selfx, _t1), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-15) {
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, Math.atan2(2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw), Math.fma(-2.0, Math.fma(_selfy, _selfy, _t1), 1.0)));
        } else {
            dest.put(destOffset + 1, Math.atan2(2.0 * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0)));
            dest.put(destOffset + 2, Math.atan2(_t8, _t9));
        }
        dest.put(destOffset, Math.atan2(_t7, java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        DoubleQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZYX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.getEulerAnglesZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.getEulerAnglesZYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZYX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = _selfz * _selfz;
        double _t7 = 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t8 = 2.0 * Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        double _t9 = Math.fma(-2.0, Math.fma(_selfy, _selfy, _t0), 1.0);
        double _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-15) {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 2, Math.atan2(2.0 * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), Math.fma(-2.0, Math.fma(_selfx, _selfx, _t0), 1.0)));
        } else {
            dest.put(destOffset, Math.atan2(2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz), Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0)));
            dest.put(destOffset + 2, Math.atan2(_t7, _t9));
        }
        dest.put(destOffset + 1, Math.atan2(_t8, java.lang.Math.sqrt(_t11)));
        return dest;
    }

    public static java.nio.DoubleBuffer integrate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angularVelX, double angularVelY, double angularVelZ, double dt) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, angularVelX, angularVelY, angularVelZ, dt);
        return dest;
    }

    public static java.nio.DoubleBuffer integrate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angularVelX, double angularVelY, double angularVelZ, double dt) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.integrate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angularVelX, angularVelY, angularVelZ, dt);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.integrate_apiGet(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
        return dest;
    }

    public static java.nio.DoubleBuffer integrate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angularVelX, double angularVelY, double angularVelZ, double dt) {
        double _t0 = 0.5 * dt;
        double _t1 = angularVelZ * _t0;
        double _t2 = angularVelX * _t0;
        double _t3 = angularVelY * _t0;
        double _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.sin(_t7);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t10 = Math.cosFromSin(_t9, _t7);
        double _t11 = _t9 * (1.0 / java.lang.Math.sqrt(_t6));
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        dest.put(destOffset, Math.fma(_selfx, _t10, _selfw * _t15) + Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.put(destOffset + 1, Math.fma(_selfx, _t17, _selfw * _t16) + Math.fma(_selfy, _t10, -(_selfz * _t15)));
        dest.put(destOffset + 2, Math.fma(_selfy, _t15, _selfz * _t10) + Math.fma(_selfw, _t17, -(_selfx * _t16)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t10, -(_selfx * _t15)) - Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    public static java.nio.DoubleBuffer integrate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer angularVel, int angularVelOffset, double dt) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _angularVelBase = UnsafeOpsHolder.U.getLong(angularVel, UnsafeCopy.BB_ADDRESS_OFFSET) + angularVelOffset * 8L;
        DoubleQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, _angularVelBase, dt);
        return dest;
    }

    public static java.nio.DoubleBuffer integrate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer angularVel, int angularVelOffset, double dt) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && angularVel.hasArray() && angularVelOffset >= 0 && angularVelOffset <= angularVel.limit() - 3) {
            DoubleQuatOps.integrate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angularVel.array(), angularVel.arrayOffset() + angularVelOffset, dt);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.integrate_apiGet(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
        return dest;
    }

    public static java.nio.DoubleBuffer integrate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer angularVel, int angularVelOffset, double dt) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = 0.5 * dt;
        double _t1 = angularVel.get(angularVelOffset + 2) * _t0;
        double _t2 = angularVel.get(angularVelOffset) * _t0;
        double _t3 = angularVel.get(angularVelOffset + 1) * _t0;
        double _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        double _t7 = java.lang.Math.sqrt(_t6);
        double _t9 = Math.sin(_t7);
        double _t10 = Math.cosFromSin(_t9, _t7);
        double _t11 = _t9 / _t7;
        double _t15, _t16, _t17;
        if (_t6 > 0.0) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0;
            _t16 = 0.0;
            _t17 = 0.0;
        }
        dest.put(destOffset, Math.fma(_selfx, _t10, _selfw * _t15) + Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.put(destOffset + 1, Math.fma(_selfx, _t17, _selfw * _t16) + Math.fma(_selfy, _t10, -(_selfz * _t15)));
        dest.put(destOffset + 2, Math.fma(_selfy, _t15, _selfz * _t10) + Math.fma(_selfw, _t17, -(_selfx * _t16)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t10, -(_selfx * _t15)) - Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invNegativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invNegativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.put(destOffset, -(_t12 * _t16));
            dest.put(destOffset + 1, -(_t10 * _t16));
            dest.put(destOffset + 2, -(_t9 * _t16));
        } else {
            dest.put(destOffset, -0.0);
            dest.put(destOffset + 1, -0.0);
            dest.put(destOffset + 2, -0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invNegativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invNegativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.put(destOffset, -(_t9 * _t16));
            dest.put(destOffset + 1, -(_t12 * _t16));
            dest.put(destOffset + 2, -(_t10 * _t16));
        } else {
            dest.put(destOffset, -0.0);
            dest.put(destOffset + 1, -0.0);
            dest.put(destOffset + 2, -0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invNegativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invNegativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        double _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.put(destOffset, -(_t10 * _t16));
            dest.put(destOffset + 1, -(_t9 * _t16));
            dest.put(destOffset + 2, -(_t12 * _t16));
        } else {
            dest.put(destOffset, -0.0);
            dest.put(destOffset + 1, -0.0);
            dest.put(destOffset + 2, -0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invNormalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invNormalizedNegativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invNormalizedNegativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(2.0, Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0));
        dest.put(destOffset + 1, -(2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw))));
        dest.put(destOffset + 2, -(2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw)));
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invNormalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invNormalizedNegativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invNormalizedNegativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, -(2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw)));
        dest.put(destOffset + 1, Math.fma(2.0, Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0));
        dest.put(destOffset + 2, -(2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw))));
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invNormalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invNormalizedNegativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invNormalizedNegativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, -(2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw))));
        dest.put(destOffset + 1, -(2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz)));
        dest.put(destOffset + 2, Math.fma(2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0));
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invNormalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invNormalizedPositiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invNormalizedPositiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(-2.0, Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0));
        dest.put(destOffset + 1, 2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        dest.put(destOffset + 2, 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw));
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invNormalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invNormalizedPositiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invNormalizedPositiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw));
        dest.put(destOffset + 1, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0));
        dest.put(destOffset + 2, 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invNormalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invNormalizedPositiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invNormalizedPositiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, 2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        dest.put(destOffset + 1, 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.put(destOffset + 2, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invPositiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invPositiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.put(destOffset, _t12 * _t16);
            dest.put(destOffset + 1, _t10 * _t16);
            dest.put(destOffset + 2, _t9 * _t16);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invPositiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invPositiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.put(destOffset, _t9 * _t16);
            dest.put(destOffset + 1, _t12 * _t16);
            dest.put(destOffset + 2, _t10 * _t16);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.invPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.invPositiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.invPositiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        double _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.put(destOffset, _t10 * _t16);
            dest.put(destOffset + 1, _t9 * _t16);
            dest.put(destOffset + 2, _t12 * _t16);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static double length_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return DoubleQuatOpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static double length_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return DoubleQuatOps.length(src.array(), src.arrayOffset() + srcOffset);
        }
        return DoubleQuatOpsKernelsTypedBuffer.length_apiGet(src, srcOffset);
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
        return DoubleQuatOpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static double lengthSquared_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return DoubleQuatOps.lengthSquared(src.array(), src.arrayOffset() + srcOffset);
        }
        return DoubleQuatOpsKernelsTypedBuffer.lengthSquared_apiGet(src, srcOffset);
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
        DoubleQuatOpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer log_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.log(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.log_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer log_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t6 = Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0) {
            dest.put(destOffset, _selfx * _t6);
            dest.put(destOffset + 1, _selfy * _t6);
            dest.put(destOffset + 2, _selfz * _t6);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        dest.put(destOffset + 3, Math.log(java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2))));
        return dest;
    }

    public static java.nio.DoubleBuffer negativeX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.negativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negativeX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.negativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.negativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negativeX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.put(destOffset, -(_t12 * _t16));
            dest.put(destOffset + 1, -(_t9 * _t16));
            dest.put(destOffset + 2, -(_t10 * _t16));
        } else {
            dest.put(destOffset, -0.0);
            dest.put(destOffset + 1, -0.0);
            dest.put(destOffset + 2, -0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer negativeY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.negativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negativeY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.negativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.negativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negativeY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.put(destOffset, -(_t10 * _t16));
            dest.put(destOffset + 1, -(_t12 * _t16));
            dest.put(destOffset + 2, -(_t9 * _t16));
        } else {
            dest.put(destOffset, -0.0);
            dest.put(destOffset + 1, -0.0);
            dest.put(destOffset + 2, -0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer negativeZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.negativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negativeZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.negativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.negativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negativeZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        double _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.put(destOffset, -(_t9 * _t16));
            dest.put(destOffset + 1, -(_t10 * _t16));
            dest.put(destOffset + 2, -(_t12 * _t16));
        } else {
            dest.put(destOffset, -0.0);
            dest.put(destOffset + 1, -0.0);
            dest.put(destOffset + 2, -0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer normalize_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalize_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.normalize(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.normalize_apiGet(dest, destOffset, src, srcOffset);
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

    public static java.nio.DoubleBuffer normalizedNegativeX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.normalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.normalizedNegativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.normalizedNegativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(2.0, Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0));
        dest.put(destOffset + 1, -(2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw)));
        dest.put(destOffset + 2, -(2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw))));
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.normalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.normalizedNegativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.normalizedNegativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, -(2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw))));
        dest.put(destOffset + 1, Math.fma(2.0, Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0));
        dest.put(destOffset + 2, -(2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz)));
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.normalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.normalizedNegativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.normalizedNegativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, -(2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw)));
        dest.put(destOffset + 1, -(2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw))));
        dest.put(destOffset + 2, Math.fma(2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0));
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.normalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.normalizedPositiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.normalizedPositiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(-2.0, Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0));
        dest.put(destOffset + 1, 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw));
        dest.put(destOffset + 2, 2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.normalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.normalizedPositiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.normalizedPositiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, 2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        dest.put(destOffset + 1, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0));
        dest.put(destOffset + 2, 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz));
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.normalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.normalizedPositiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.normalizedPositiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw));
        dest.put(destOffset + 1, 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.put(destOffset + 2, Math.fma(-2.0, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0));
        return dest;
    }

    public static java.nio.DoubleBuffer positiveX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.positiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer positiveX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.positiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.positiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer positiveX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, _selfy, _selfz * _selfw);
        double _t10 = 2.0 * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        double _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.put(destOffset, _t12 * _t16);
            dest.put(destOffset + 1, _t9 * _t16);
            dest.put(destOffset + 2, _t10 * _t16);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer positiveY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.positiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer positiveY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.positiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.positiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer positiveY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, _selfw, _selfy * _selfz);
        double _t10 = 2.0 * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        double _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.put(destOffset, _t10 * _t16);
            dest.put(destOffset + 1, _t12 * _t16);
            dest.put(destOffset + 2, _t9 * _t16);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer positiveZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.positiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer positiveZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.positiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.positiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer positiveZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, _selfz, _selfy * _selfw);
        double _t10 = 2.0 * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        double _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        double _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0) {
            dest.put(destOffset, _t9 * _t16);
            dest.put(destOffset + 1, _t10 * _t16);
            dest.put(destOffset + 2, _t12 * _t16);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer pow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.pow_unsafe(_destBase, _srcBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer pow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t12 = java.lang.Math.min(Math.exp(t == 0.0 ? 0.0 : t * Math.log(java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2)))), 1.7976931348623157E308);
        double _t13 = Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2));
        double _t20, _t21, _t22;
        if (_t2 > 0.0) {
            _t20 = t * _selfz * _t13;
            _t21 = t * _selfx * _t13;
            _t22 = t * _selfy * _t13;
        } else {
            _t20 = t * 0.0;
            _t21 = t * 0.0;
            _t22 = t * 0.0;
        }
        double _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        double _t26 = java.lang.Math.sqrt(_t25);
        double _t28 = Math.sin(_t26);
        return pow_apiGet_sd44af105_1(dest, destOffset, _t12, _t20, _t21, _t22, _t25, _t26, _t28, _t12 * (_t28 / _t26));
    }

    /** Piece 2 of {@code pow_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer pow_apiGet_sd44af105_1(java.nio.DoubleBuffer dest, int destOffset, double _t12, double _t20, double _t21, double _t22, double _t25, double _t26, double _t28, double _t31) {
        if (_t25 > 0.0) {
            dest.put(destOffset, _t21 * _t31);
            dest.put(destOffset + 1, _t22 * _t31);
            dest.put(destOffset + 2, _t20 * _t31);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        dest.put(destOffset + 3, Math.cosFromSin(_t28, _t26) * _t12);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateTowards_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double step) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, step);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateTowards_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double step) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.rotateTowards(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, targetX, targetY, targetZ, targetW, step);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateTowards_apiGet(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateTowards_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double step) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t7 = Math.fma(_selfw, targetW, Math.fma(_selfz, targetZ, Math.fma(_selfx, targetX, _selfy * targetY)));
        double _t11 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t7)));
        double _t12 = Math.sin(_t11);
        double _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0) {
            _t13 = -targetW;
            _t14 = -targetZ;
            _t15 = -targetX;
            _t16 = -targetY;
        } else {
            _t13 = targetW;
            _t14 = targetZ;
            _t15 = targetX;
            _t16 = targetY;
        }
        double _t17 = _selfw - _t13;
        double _t18 = _selfz - _t14;
        double _t19 = _selfx - _t15;
        double _t20 = _selfy - _t16;
        double _t21 = _selfw + _t13;
        double _t22 = _selfz + _t14;
        double _t23 = _selfx + _t15;
        double _t24 = _selfy + _t16;
        double _t36 = 4.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        return rotateTowards_apiGet_sdf4864c0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, 1.0 / _t12, _t13, _t14, _t15, _t16, _t36 > 0.0 ? java.lang.Math.min(1.0, step / _t36) : 0.0);
    }

    /** Piece 2 of {@code rotateTowards_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateTowards_apiGet_sdf4864c0_1(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t12_inv, double _t13, double _t14, double _t15, double _t16, double _t39) {
        double _t40 = 1.0 - _t39;
        double _t42 = Math.sin(_t11 * _t39);
        double _t44 = Math.sin(_t40 * _t11);
        double _t65, _t66, _t67, _t68;
        if (_t12 > 0.0) {
            _t65 = Math.fma(_selfw, _t44, _t42 * _t13) * _t12_inv;
            _t66 = Math.fma(_selfz, _t44, _t42 * _t14) * _t12_inv;
            _t67 = Math.fma(_selfx, _t44, _t42 * _t15) * _t12_inv;
            _t68 = Math.fma(_selfy, _t44, _t42 * _t16) * _t12_inv;
        } else {
            _t65 = Math.fma(_selfw, _t40, _t13 * _t39);
            _t66 = Math.fma(_selfz, _t40, _t14 * _t39);
            _t67 = Math.fma(_selfx, _t40, _t15 * _t39);
            _t68 = Math.fma(_selfy, _t40, _t16 * _t39);
        }
        double _t72 = Math.fma(_t65, _t65, Math.fma(_t66, _t66, Math.fma(_t67, _t67, _t68 * _t68)));
        double _t73 = (1.0 / java.lang.Math.sqrt(_t72));
        if (_t72 != 0.0) {
            dest.put(destOffset, _t73 * _t67);
            dest.put(destOffset + 1, _t73 * _t68);
            dest.put(destOffset + 2, _t73 * _t66);
            dest.put(destOffset + 3, _t73 * _t65);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer rotateTowards_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double step) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, _targetBase, step);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateTowards_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double step) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            DoubleQuatOps.rotateTowards(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, target.array(), target.arrayOffset() + targetOffset, step);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateTowards_apiGet(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateTowards_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer target, int targetOffset, double step) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _targetx = target.get(targetOffset);
        double _targety = target.get(targetOffset + 1);
        double _targetz = target.get(targetOffset + 2);
        double _targetw = target.get(targetOffset + 3);
        double _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        double _t11 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t7)));
        double _t12 = Math.sin(_t11);
        double _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0) {
            _t13 = -_targetw;
            _t14 = -_targetz;
            _t15 = -_targetx;
            _t16 = -_targety;
        } else {
            _t13 = _targetw;
            _t14 = _targetz;
            _t15 = _targetx;
            _t16 = _targety;
        }
        return rotateTowards_apiGet_se57a0a65_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t11, _t12, 1.0 / _t12, _t13, _t14, _t15, _t16, _selfw - _t13, _selfz - _t14, _selfx - _t15, _selfy - _t16, _selfw + _t13, _selfz + _t14, _selfx + _t15, _selfy + _t16);
    }

    /** Piece 2 of {@code rotateTowards_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateTowards_apiGet_se57a0a65_1(java.nio.DoubleBuffer dest, int destOffset, double step, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t12_inv, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        double _t36 = 4.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        double _t39 = _t36 > 0.0 ? java.lang.Math.min(1.0, step / _t36) : 0.0;
        double _t40 = 1.0 - _t39;
        double _t42 = Math.sin(_t11 * _t39);
        double _t44 = Math.sin(_t40 * _t11);
        double _t65, _t66, _t67, _t68;
        if (_t12 > 0.0) {
            _t65 = Math.fma(_selfw, _t44, _t42 * _t13) * _t12_inv;
            _t66 = Math.fma(_selfz, _t44, _t42 * _t14) * _t12_inv;
            _t67 = Math.fma(_selfx, _t44, _t42 * _t15) * _t12_inv;
            _t68 = Math.fma(_selfy, _t44, _t42 * _t16) * _t12_inv;
        } else {
            _t65 = Math.fma(_selfw, _t40, _t13 * _t39);
            _t66 = Math.fma(_selfz, _t40, _t14 * _t39);
            _t67 = Math.fma(_selfx, _t40, _t15 * _t39);
            _t68 = Math.fma(_selfy, _t40, _t16 * _t39);
        }
        double _t72 = Math.fma(_t65, _t65, Math.fma(_t66, _t66, Math.fma(_t67, _t67, _t68 * _t68)));
        return rotateTowards_apiGet_se57a0a65_2(dest, destOffset, _t65, _t66, _t67, _t68, _t72, (1.0 / java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code rotateTowards_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateTowards_apiGet_se57a0a65_2(java.nio.DoubleBuffer dest, int destOffset, double _t65, double _t66, double _t67, double _t68, double _t72, double _t73) {
        if (_t72 != 0.0) {
            dest.put(destOffset, _t73 * _t67);
            dest.put(destOffset + 1, _t73 * _t68);
            dest.put(destOffset + 2, _t73 * _t66);
            dest.put(destOffset + 3, _t73 * _t65);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 3, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.lookAlong(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.lookAlong_apiGet(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t19 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t20 = Math.fma(_t19, _t12, upX);
        double _t21 = Math.fma(_t19, _t13, upY);
        double _t22 = Math.fma(_t19, _t11, upZ);
        double _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        double _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        double _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        double _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        return lookAlong_apiGet_s60ad30e0_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t29, _t30, _t31, _t34, (1.0 / java.lang.Math.sqrt(_t34)));
    }

    /** Piece 2 of {@code lookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAlong_apiGet_s60ad30e0_1(java.nio.DoubleBuffer dest, int destOffset, double upX, double upY, double upZ, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t13, double _t29, double _t30, double _t31, double _t34, double _t35) {
        double _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0;
            _t40 = 0.0;
            _t41 = 0.0;
        }
        double _t42 = -_t40;
        double _t43 = -_t39;
        double _t45 = 1.0 + _t39;
        double _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        double _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0 - _t39 - _t11));
        return lookAlong_apiGet_s60ad30e0_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t42, _t12 - _t40, _t40 + _t12, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11)), _t73, _t74, Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0 + _t11 - _t39)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAlong_apiGet_s60ad30e0_2(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t39, double _t42, double _t49, double _t50, double _t61, double _t66, double _t68, double _t69, double _t70, double _t72, double _t73, double _t74, double _t75, double _sp1, double _sp2) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t72));
        double _t120, _t121, _t122, _t123;
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0) {
            _t120 = _sp0 * _t69;
            _t121 = _sp0 * _t49;
            _t122 = 0.5 * java.lang.Math.sqrt(_t72);
            _t123 = _sp0 * _t68;
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                _t120 = _sp1 * _t50;
                _t121 = _sp1 * _t70;
                _t122 = _sp1 * _t68;
                _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            } else {
                if (_t61 > _t11) {
                    _t120 = _sp2 * _t66;
                    _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                    _t122 = _sp2 * _t49;
                    _t123 = _sp2 * _t70;
                } else {
                    _t120 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t121 = _sp3 * _t66;
                    _t122 = _sp3 * _t69;
                    _t123 = _sp3 * _t50;
                }
            }
        }
        dest.put(destOffset, Math.fma(_selfx, _t122, _selfw * _t123) + Math.fma(_selfy, _t120, -(_selfz * _t121)));
        dest.put(destOffset + 1, Math.fma(_selfy, _t122, _selfz * _t123) + Math.fma(_selfw, _t121, -(_selfx * _t120)));
        return lookAlong_apiGet_s60ad30e0_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t120, _t121, _t122, _t123);
    }

    /** Piece 4 of {@code lookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAlong_apiGet_s60ad30e0_3(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t120, double _t121, double _t122, double _t123) {
        dest.put(destOffset + 2, Math.fma(_selfx, _t121, _selfw * _t120) + Math.fma(_selfz, _t122, -(_selfy * _t123)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t122, -(_selfx * _t123)) - Math.fma(_selfy, _t121, _selfz * _t120));
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset * 8L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 8L;
        DoubleQuatOpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            DoubleQuatOps.lookAlong(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.lookAlong_apiGet(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _dirx = dir.get(dirOffset);
        double _diry = dir.get(dirOffset + 1);
        double _dirz = dir.get(dirOffset + 2);
        double _upx = up.get(upOffset);
        double _upy = up.get(upOffset + 1);
        double _upz = up.get(upOffset + 2);
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t19 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t20 = Math.fma(_t19, _t12, _upx);
        double _t21 = Math.fma(_t19, _t13, _upy);
        double _t22 = Math.fma(_t19, _t11, _upz);
        return lookAlong_apiGet_s33ef3e0a_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _upx, _upy, _upz, _t11, _t12, _t13, Math.fma(_t20, _t13, -(_t21 * _t12)), Math.fma(_t21, _t11, -(_t22 * _t13)), Math.fma(_t22, _t12, -(_t20 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAlong_apiGet_s33ef3e0a_1(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t29, double _t30, double _t31) {
        double _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        double _t35 = (1.0 / java.lang.Math.sqrt(_t34));
        double _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0;
            _t40 = 0.0;
            _t41 = 0.0;
        }
        double _t42 = -_t40;
        double _t43 = -_t39;
        double _t45 = 1.0 + _t39;
        return lookAlong_apiGet_s33ef3e0a_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t42, _t12 - _t40, _t40 + _t12, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11)), Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0 - _t39 - _t11)), Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0 + _t11 - _t39)));
    }

    /** Piece 3 of {@code lookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAlong_apiGet_s33ef3e0a_2(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t11, double _t12, double _t39, double _t42, double _t49, double _t50, double _t61, double _t66, double _t68, double _t69, double _t70, double _t72, double _t73, double _t74, double _t75) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t74));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t72));
        double _t120, _t121, _t122, _t123;
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0) {
            _t120 = _sp0 * _t69;
            _t121 = _sp0 * _t49;
            _t122 = 0.5 * java.lang.Math.sqrt(_t72);
            _t123 = _sp0 * _t68;
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                _t120 = _sp1 * _t50;
                _t121 = _sp1 * _t70;
                _t122 = _sp1 * _t68;
                _t123 = 0.5 * java.lang.Math.sqrt(_t73);
            } else {
                if (_t61 > _t11) {
                    _t120 = _sp2 * _t66;
                    _t121 = 0.5 * java.lang.Math.sqrt(_t74);
                    _t122 = _sp2 * _t49;
                    _t123 = _sp2 * _t70;
                } else {
                    _t120 = 0.5 * java.lang.Math.sqrt(_t75);
                    _t121 = _sp3 * _t66;
                    _t122 = _sp3 * _t69;
                    _t123 = _sp3 * _t50;
                }
            }
        }
        dest.put(destOffset, Math.fma(_selfx, _t122, _selfw * _t123) + Math.fma(_selfy, _t120, -(_selfz * _t121)));
        return lookAlong_apiGet_s33ef3e0a_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t120, _t121, _t122, _t123);
    }

    /** Piece 4 of {@code lookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAlong_apiGet_s33ef3e0a_3(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t120, double _t121, double _t122, double _t123) {
        dest.put(destOffset + 1, Math.fma(_selfy, _t122, _selfz * _t123) + Math.fma(_selfw, _t121, -(_selfx * _t120)));
        dest.put(destOffset + 2, Math.fma(_selfx, _t121, _selfw * _t120) + Math.fma(_selfz, _t122, -(_selfy * _t123)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t122, -(_selfx * _t123)) - Math.fma(_selfy, _t121, _selfz * _t120));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_api(java.nio.DoubleBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeRotationAxis(dest.array(), dest.arrayOffset() + destOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationAxis_apiGet(dest, destOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dest.put(destOffset, axisX * _t1);
        dest.put(destOffset + 1, axisY * _t1);
        dest.put(destOffset + 2, axisZ * _t1);
        dest.put(destOffset + 3, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            DoubleQuatOps.makeRotationAxis(dest.array(), dest.arrayOffset() + destOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationAxis_apiGet(dest, destOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dest.put(destOffset, axis.get(axisOffset) * _t1);
        dest.put(destOffset + 1, _axisy * _t1);
        dest.put(destOffset + 2, _axisz * _t1);
        dest.put(destOffset + 3, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_unsafe(java.nio.DoubleBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_api(java.nio.DoubleBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeRotationLookAlong(dest.array(), dest.arrayOffset() + destOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationLookAlong_apiGet(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_apiGet(java.nio.DoubleBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t19 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t20 = Math.fma(_t19, _t12, upX);
        double _t21 = Math.fma(_t19, _t13, upY);
        double _t22 = Math.fma(_t19, _t11, upZ);
        double _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        double _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        double _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        double _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        double _t35 = (1.0 / java.lang.Math.sqrt(_t34));
        double _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0;
            _t40 = 0.0;
            _t41 = 0.0;
        }
        return makeRotationLookAlong_apiGet_se21a0127_1(dest, destOffset, _t11, _t12, _t13, _t39, _t40, _t41, -_t40, -_t39, 1.0 + _t39, _t40 + _t12, _t12 - _t40);
    }

    /** Piece 2 of {@code makeRotationLookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeRotationLookAlong_apiGet_se21a0127_1(java.nio.DoubleBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t39, double _t40, double _t41, double _t42, double _t43, double _t45, double _t49, double _t50) {
        double _t72 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11));
        double _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        double _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0 - _t39 - _t11));
        double _t75 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0 + _t11 - _t39));
        return makeRotationLookAlong_apiGet_se21a0127_2(dest, destOffset, _t11, _t12, _t39, _t42, _t49, _t50, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), _t72, _t73, _t74, _t75, 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)), 0.5 * (1.0 / java.lang.Math.sqrt(_t75)), 0.5 * (1.0 / java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeRotationLookAlong_apiGet_se21a0127_2(java.nio.DoubleBuffer dest, int destOffset, double _t11, double _t12, double _t39, double _t42, double _t49, double _t50, double _t61, double _t66, double _t67, double _t69, double _t70, double _t72, double _t73, double _t74, double _t75, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0) {
            dest.put(destOffset, _sp0 * _t67);
            dest.put(destOffset + 1, _sp0 * _t50);
            dest.put(destOffset + 2, _sp0 * _t70);
            dest.put(destOffset + 3, 0.5 * java.lang.Math.sqrt(_t72));
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                dest.put(destOffset, 0.5 * java.lang.Math.sqrt(_t73));
                dest.put(destOffset + 1, _sp3 * _t69);
                dest.put(destOffset + 2, _sp3 * _t49);
                dest.put(destOffset + 3, _sp3 * _t67);
            } else {
                if (_t61 > _t11) {
                    dest.put(destOffset, _sp1 * _t69);
                    dest.put(destOffset + 1, 0.5 * java.lang.Math.sqrt(_t74));
                    dest.put(destOffset + 2, _sp1 * _t66);
                    dest.put(destOffset + 3, _sp1 * _t50);
                } else {
                    dest.put(destOffset, _sp2 * _t49);
                    dest.put(destOffset + 1, _sp2 * _t66);
                    dest.put(destOffset + 2, 0.5 * java.lang.Math.sqrt(_t75));
                    dest.put(destOffset + 3, _sp2 * _t70);
                }
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset * 8L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            DoubleQuatOps.makeRotationLookAlong(dest.array(), dest.arrayOffset() + destOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationLookAlong_apiGet(dest, destOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        double _dirx = dir.get(dirOffset);
        double _diry = dir.get(dirOffset + 1);
        double _dirz = dir.get(dirOffset + 2);
        double _upx = up.get(upOffset);
        double _upy = up.get(upOffset + 1);
        double _upz = up.get(upOffset + 2);
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t19 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t20 = Math.fma(_t19, _t12, _upx);
        double _t21 = Math.fma(_t19, _t13, _upy);
        double _t22 = Math.fma(_t19, _t11, _upz);
        double _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        double _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        double _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        double _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        return makeRotationLookAlong_apiGet_s4218861f_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t29, _t30, _t31, _t34, (1.0 / java.lang.Math.sqrt(_t34)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeRotationLookAlong_apiGet_s4218861f_1(java.nio.DoubleBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t29, double _t30, double _t31, double _t34, double _t35) {
        double _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0;
            _t40 = 0.0;
            _t41 = 0.0;
        }
        double _t42 = -_t40;
        double _t43 = -_t39;
        double _t45 = 1.0 + _t39;
        double _t72 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11));
        double _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0 - _t39 - _t11));
        return makeRotationLookAlong_apiGet_s4218861f_2(dest, destOffset, _t11, _t12, _t39, _t42, _t40 + _t12, _t12 - _t40, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), _t72, Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11)), _t74, Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0 + _t11 - _t39)), 0.5 * (1.0 / java.lang.Math.sqrt(_t72)), 0.5 * (1.0 / java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeRotationLookAlong_apiGet_s4218861f_2(java.nio.DoubleBuffer dest, int destOffset, double _t11, double _t12, double _t39, double _t42, double _t49, double _t50, double _t61, double _t66, double _t67, double _t69, double _t70, double _t72, double _t73, double _t74, double _t75, double _sp0, double _sp1) {
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t75));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t73));
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0) {
            dest.put(destOffset, _sp0 * _t67);
            dest.put(destOffset + 1, _sp0 * _t50);
            dest.put(destOffset + 2, _sp0 * _t70);
            dest.put(destOffset + 3, 0.5 * java.lang.Math.sqrt(_t72));
        } else {
            if (_t39 > java.lang.Math.max(_t61, _t11)) {
                dest.put(destOffset, 0.5 * java.lang.Math.sqrt(_t73));
                dest.put(destOffset + 1, _sp3 * _t69);
                dest.put(destOffset + 2, _sp3 * _t49);
                dest.put(destOffset + 3, _sp3 * _t67);
            } else {
                if (_t61 > _t11) {
                    dest.put(destOffset, _sp1 * _t69);
                    dest.put(destOffset + 1, 0.5 * java.lang.Math.sqrt(_t74));
                    dest.put(destOffset + 2, _sp1 * _t66);
                    dest.put(destOffset + 3, _sp1 * _t50);
                } else {
                    dest.put(destOffset, _sp2 * _t49);
                    dest.put(destOffset + 1, _sp2 * _t66);
                    dest.put(destOffset + 2, 0.5 * java.lang.Math.sqrt(_t75));
                    dest.put(destOffset + 3, _sp2 * _t70);
                }
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationTo_unsafe(java.nio.DoubleBuffer dest, int destOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationTo_api(java.nio.DoubleBuffer dest, int destOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeRotationTo(dest.array(), dest.arrayOffset() + destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationTo_apiGet(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationTo_apiGet(java.nio.DoubleBuffer dest, int destOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t16 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        double _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        double _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_apiGet_s971a49c1_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(Math.fma(0.25, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeRotationTo_apiGet_s971a49c1_1(java.nio.DoubleBuffer dest, int destOffset, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > 1.0E-13) {
            dest.put(destOffset, _t15 * _t32);
            dest.put(destOffset + 1, _t17 * _t32);
            dest.put(destOffset + 2, _t16 * _t32);
            dest.put(destOffset + 3, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                dest.put(destOffset, _t30 * _t13);
                dest.put(destOffset + 1, _t30 * _t19);
                dest.put(destOffset + 2, _t30 * _t18);
                dest.put(destOffset + 3, 0.0);
            } else {
                dest.put(destOffset, 0.0);
                dest.put(destOffset + 1, 0.0);
                dest.put(destOffset + 2, 0.0);
                dest.put(destOffset + 3, 0.0);
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationTo_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer fromDir, int fromDirOffset, java.nio.DoubleBuffer toDir, int toDirOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset * 8L;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, _fromDirBase, _toDirBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationTo_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer fromDir, int fromDirOffset, java.nio.DoubleBuffer toDir, int toDirOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && fromDir.hasArray() && fromDirOffset >= 0 && fromDirOffset <= fromDir.limit() - 3 && toDir.hasArray() && toDirOffset >= 0 && toDirOffset <= toDir.limit() - 3) {
            DoubleQuatOps.makeRotationTo(dest.array(), dest.arrayOffset() + destOffset, fromDir.array(), fromDir.arrayOffset() + fromDirOffset, toDir.array(), toDir.arrayOffset() + toDirOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationTo_apiGet(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationTo_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer fromDir, int fromDirOffset, java.nio.DoubleBuffer toDir, int toDirOffset) {
        double _fromDirx = fromDir.get(fromDirOffset);
        double _fromDiry = fromDir.get(fromDirOffset + 1);
        double _fromDirz = fromDir.get(fromDirOffset + 2);
        double _toDirx = toDir.get(toDirOffset);
        double _toDiry = toDir.get(toDirOffset + 1);
        double _toDirz = toDir.get(toDirOffset + 2);
        double _t4 = _fromDirz + _toDirz;
        double _t5 = _fromDirx + _toDirx;
        double _t6 = _fromDiry + _toDiry;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        double _t15 = Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        double _t16 = Math.fma(_fromDirx, _toDiry, -(_fromDiry * _toDirx));
        double _t17 = Math.fma(_fromDirz, _toDirx, -(_fromDirx * _toDirz));
        double _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_apiGet_s4b8e650e_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(Math.fma(0.25, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeRotationTo_apiGet_s4b8e650e_1(java.nio.DoubleBuffer dest, int destOffset, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t32) {
        if (_t24 > 1.0E-13) {
            dest.put(destOffset, _t15 * _t32);
            dest.put(destOffset + 1, _t17 * _t32);
            dest.put(destOffset + 2, _t16 * _t32);
            dest.put(destOffset + 3, _t24 * _t32);
        } else {
            if (_t29 != 0.0) {
                dest.put(destOffset, _t30 * _t13);
                dest.put(destOffset + 1, _t30 * _t19);
                dest.put(destOffset + 2, _t30 * _t18);
                dest.put(destOffset + 3, 0.0);
            } else {
                dest.put(destOffset, 0.0);
                dest.put(destOffset + 1, 0.0);
                dest.put(destOffset + 2, 0.0);
                dest.put(destOffset + 3, 0.0);
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationX_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationX_api(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeRotationX(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationX_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationX_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dest.put(destOffset, _t1);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXYZ_api(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeRotationXYZ(dest.array(), dest.arrayOffset() + destOffset, angleX, angleY, angleZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationXYZ_apiGet(dest, destOffset, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        dest.put(destOffset, Math.fma(_t10, _t7, _t11 * _t5));
        dest.put(destOffset + 1, Math.fma(_t11, _t7, -(_t10 * _t5)));
        dest.put(destOffset + 2, Math.fma(_t9, _t7, _t12 * _t5));
        dest.put(destOffset + 3, Math.fma(_t12, _t7, -(_t9 * _t5)));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXZY_api(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeRotationXZY(dest.array(), dest.arrayOffset() + destOffset, angleX, angleZ, angleY);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationXZY_apiGet(dest, destOffset, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXZY_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        dest.put(destOffset, Math.fma(_t10, _t7, -(_t11 * _t5)));
        dest.put(destOffset + 1, Math.fma(_t12, _t5, -(_t9 * _t7)));
        dest.put(destOffset + 2, Math.fma(_t10, _t5, _t11 * _t7));
        dest.put(destOffset + 3, Math.fma(_t9, _t5, _t12 * _t7));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationY_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationY_api(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeRotationY(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationY_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationY_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, _t1);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYXZ_api(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeRotationYXZ(dest.array(), dest.arrayOffset() + destOffset, angleY, angleX, angleZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationYXZ_apiGet(dest, destOffset, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        dest.put(destOffset, Math.fma(_t10, _t7, _t11 * _t5));
        dest.put(destOffset + 1, Math.fma(_t11, _t7, -(_t10 * _t5)));
        dest.put(destOffset + 2, Math.fma(_t12, _t5, -(_t9 * _t7)));
        dest.put(destOffset + 3, Math.fma(_t9, _t5, _t12 * _t7));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYZX_api(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeRotationYZX(dest.array(), dest.arrayOffset() + destOffset, angleY, angleZ, angleX);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationYZX_apiGet(dest, destOffset, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYZX_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t5, _t2);
        double _t7 = Math.cosFromSin(_t3, _t0);
        double _t8 = Math.cosFromSin(_t4, _t1);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t8;
        double _t11 = _t4 * _t7;
        double _t12 = _t7 * _t8;
        dest.put(destOffset, Math.fma(_t9, _t6, _t12 * _t5));
        dest.put(destOffset + 1, Math.fma(_t10, _t6, _t11 * _t5));
        dest.put(destOffset + 2, Math.fma(_t11, _t6, -(_t10 * _t5)));
        dest.put(destOffset + 3, Math.fma(_t12, _t6, -(_t9 * _t5)));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationZ_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZ_api(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeRotationZ(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationZ_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, _t1);
        dest.put(destOffset + 3, Math.cosFromSin(_t1, _t0));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZXY_api(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeRotationZXY(dest.array(), dest.arrayOffset() + destOffset, angleZ, angleX, angleY);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationZXY_apiGet(dest, destOffset, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZXY_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t4, _t1);
        double _t7 = Math.cosFromSin(_t5, _t2);
        double _t8 = Math.cosFromSin(_t3, _t0);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t6;
        double _t11 = _t4 * _t8;
        double _t12 = _t8 * _t6;
        dest.put(destOffset, Math.fma(_t10, _t7, -(_t11 * _t5)));
        dest.put(destOffset + 1, Math.fma(_t9, _t7, _t12 * _t5));
        dest.put(destOffset + 2, Math.fma(_t10, _t5, _t11 * _t7));
        dest.put(destOffset + 3, Math.fma(_t12, _t7, -(_t9 * _t5)));
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        DoubleQuatOpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZYX_api(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            DoubleQuatOps.makeRotationZYX(dest.array(), dest.arrayOffset() + destOffset, angleZ, angleY, angleX);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.makeRotationZYX_apiGet(dest, destOffset, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZYX_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        dest.put(destOffset, Math.fma(_t12, _t5, -(_t9 * _t8)));
        dest.put(destOffset + 1, Math.fma(_t10, _t8, _t11 * _t5));
        dest.put(destOffset + 2, Math.fma(_t11, _t8, -(_t10 * _t5)));
        dest.put(destOffset + 3, Math.fma(_t9, _t5, _t12 * _t8));
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.preRotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.preRotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dest.put(destOffset, Math.fma(_selfx, _t2, _selfw * _t1));
        dest.put(destOffset + 1, Math.fma(_selfy, _t2, -(_selfz * _t1)));
        dest.put(destOffset + 2, Math.fma(_selfy, _t1, _selfz * _t2));
        dest.put(destOffset + 3, Math.fma(_selfw, _t2, -(_selfx * _t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.preRotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.preRotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dest.put(destOffset, Math.fma(_selfx, _t2, _selfz * _t1));
        dest.put(destOffset + 1, Math.fma(_selfy, _t2, _selfw * _t1));
        dest.put(destOffset + 2, Math.fma(_selfz, _t2, -(_selfx * _t1)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t2, -(_selfy * _t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.preRotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.preRotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.preRotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dest.put(destOffset, Math.fma(_selfx, _t2, -(_selfy * _t1)));
        dest.put(destOffset + 1, Math.fma(_selfx, _t1, _selfy * _t2));
        dest.put(destOffset + 2, Math.fma(_selfz, _t2, _selfw * _t1));
        dest.put(destOffset + 3, Math.fma(_selfw, _t2, -(_selfz * _t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axisX * _t1;
        double _t3 = axisZ * _t1;
        double _t4 = axisY * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        dest.put(destOffset, Math.fma(_selfx, _t5, _selfw * _t2) + Math.fma(_selfy, _t3, -(_selfz * _t4)));
        dest.put(destOffset + 1, Math.fma(_selfy, _t5, _selfz * _t2) + Math.fma(_selfw, _t4, -(_selfx * _t3)));
        dest.put(destOffset + 2, Math.fma(_selfx, _t4, _selfw * _t3) + Math.fma(_selfz, _t5, -(_selfy * _t2)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t5, -(_selfx * _t2)) - Math.fma(_selfy, _t4, _selfz * _t3));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            DoubleQuatOps.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = axis.get(axisOffset) * _t1;
        double _t3 = axis.get(axisOffset + 2) * _t1;
        double _t4 = axis.get(axisOffset + 1) * _t1;
        double _t5 = Math.cosFromSin(_t1, _t0);
        dest.put(destOffset, Math.fma(_selfx, _t5, _selfw * _t2) + Math.fma(_selfy, _t3, -(_selfz * _t4)));
        dest.put(destOffset + 1, Math.fma(_selfy, _t5, _selfz * _t2) + Math.fma(_selfw, _t4, -(_selfx * _t3)));
        dest.put(destOffset + 2, Math.fma(_selfx, _t4, _selfw * _t3) + Math.fma(_selfz, _t5, -(_selfy * _t2)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t5, -(_selfx * _t2)) - Math.fma(_selfy, _t4, _selfz * _t3));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateTo_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateTo_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.rotateTo(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateTo_apiGet(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateTo_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double fromDirX, double fromDirY, double fromDirZ, double toDirX, double toDirY, double toDirZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t4 = fromDirZ + toDirZ;
        double _t5 = fromDirX + toDirX;
        double _t6 = fromDirY + toDirY;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        double _t15 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        double _t16 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        double _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        double _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_apiGet_sf17ced23_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)), (1.0 / java.lang.Math.sqrt(Math.fma(0.25, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateTo_apiGet_sf17ced23_1(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t24, double _t29, double _t30, double _t35) {
        double _t44, _t45, _t46, _t47;
        if (_t24 > 1.0E-13) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        dest.put(destOffset, Math.fma(_selfx, _t44, _selfw * _t45) + Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.put(destOffset + 1, Math.fma(_selfy, _t44, _selfz * _t45) + Math.fma(_selfw, _t47, -(_selfx * _t46)));
        dest.put(destOffset + 2, Math.fma(_selfx, _t47, _selfw * _t46) + Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t44, -(_selfx * _t45)) - Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateTo_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer fromDir, int fromDirOffset, java.nio.DoubleBuffer toDir, int toDirOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset * 8L;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, _fromDirBase, _toDirBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateTo_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer fromDir, int fromDirOffset, java.nio.DoubleBuffer toDir, int toDirOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && fromDir.hasArray() && fromDirOffset >= 0 && fromDirOffset <= fromDir.limit() - 3 && toDir.hasArray() && toDirOffset >= 0 && toDirOffset <= toDir.limit() - 3) {
            DoubleQuatOps.rotateTo(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, fromDir.array(), fromDir.arrayOffset() + fromDirOffset, toDir.array(), toDir.arrayOffset() + toDirOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateTo_apiGet(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateTo_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer fromDir, int fromDirOffset, java.nio.DoubleBuffer toDir, int toDirOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _fromDirx = fromDir.get(fromDirOffset);
        double _fromDiry = fromDir.get(fromDirOffset + 1);
        double _fromDirz = fromDir.get(fromDirOffset + 2);
        double _toDirx = toDir.get(toDirOffset);
        double _toDiry = toDir.get(toDirOffset + 1);
        double _toDirz = toDir.get(toDirOffset + 2);
        double _t4 = _fromDirz + _toDirz;
        double _t5 = _fromDirx + _toDirx;
        double _t6 = _fromDiry + _toDiry;
        double _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        double _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_apiGet_s2fd7f1d0_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, Math.fma(_fromDirx, _toDiry, -(_fromDiry * _toDirx)), Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), Math.fma(_fromDirz, _toDirx, -(_fromDirx * _toDirz)), _t23, 0.5 * _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateTo_apiGet_s2fd7f1d0_1(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t13, double _t18, double _t19, double _t15, double _t16, double _t17, double _t23, double _t24, double _t29, double _t30) {
        double _t35 = (1.0 / java.lang.Math.sqrt(Math.fma(0.25, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        double _t44, _t45, _t46, _t47;
        if (_t24 > 1.0E-13) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0) {
                _t44 = 0.0;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0;
                _t45 = 0.0;
                _t46 = 0.0;
                _t47 = 0.0;
            }
        }
        dest.put(destOffset, Math.fma(_selfx, _t44, _selfw * _t45) + Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.put(destOffset + 1, Math.fma(_selfy, _t44, _selfz * _t45) + Math.fma(_selfw, _t47, -(_selfx * _t46)));
        dest.put(destOffset + 2, Math.fma(_selfx, _t47, _selfw * _t46) + Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t44, -(_selfx * _t45)) - Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.rotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dest.put(destOffset, Math.fma(_selfx, _t2, _selfw * _t1));
        dest.put(destOffset + 1, Math.fma(_selfy, _t2, _selfz * _t1));
        dest.put(destOffset + 2, Math.fma(_selfz, _t2, -(_selfy * _t1)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t2, -(_selfx * _t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXYZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.rotateXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleX, angleY, angleZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateXYZ_apiGet(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t14 = _t6 * _t7;
        double _t19 = Math.fma(_t10, _t8, _t11 * _t5);
        double _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        double _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        dest.put(destOffset, Math.fma(_selfx, _t21, _selfw * _t19) + Math.fma(_selfy, _t20, -(_selfz * _t22)));
        dest.put(destOffset + 1, Math.fma(_selfy, _t21, _selfz * _t19) + Math.fma(_selfw, _t22, -(_selfx * _t20)));
        return rotateXYZ_apiGet_sb84d38b5_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateXYZ_apiGet_sb84d38b5_1(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.put(destOffset + 2, Math.fma(_selfx, _t22, _selfw * _t20) + Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t21, -(_selfx * _t19)) - Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXZY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.rotateXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleX, angleZ, angleY);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateXZY_apiGet(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXZY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        double _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = Math.fma(_t10, _t5, _t11 * _t8);
        double _t21 = Math.fma(_t10, _t8, -(_t11 * _t5));
        double _t22 = Math.fma(_t12, _t5, -(_t9 * _t8));
        dest.put(destOffset, Math.fma(_selfx, _t19, _selfw * _t21) + Math.fma(_selfy, _t20, -(_selfz * _t22)));
        dest.put(destOffset + 1, Math.fma(_selfy, _t19, _selfz * _t21) + Math.fma(_selfw, _t22, -(_selfx * _t20)));
        return rotateXZY_apiGet_s3b84394d_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateXZY_apiGet_s3b84394d_1(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.put(destOffset + 2, Math.fma(_selfx, _t22, _selfw * _t20) + Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t19, -(_selfx * _t21)) - Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.rotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dest.put(destOffset, Math.fma(_selfx, _t2, -(_selfz * _t1)));
        dest.put(destOffset + 1, Math.fma(_selfy, _t2, _selfw * _t1));
        dest.put(destOffset + 2, Math.fma(_selfx, _t1, _selfz * _t2));
        dest.put(destOffset + 3, Math.fma(_selfw, _t2, -(_selfy * _t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYXZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.rotateYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleY, angleX, angleZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateYXZ_apiGet(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleY;
        double _t2 = 0.5 * angleZ;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t12 = _t6 * _t7;
        double _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = Math.fma(_t10, _t8, _t11 * _t5);
        double _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        double _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        dest.put(destOffset, Math.fma(_selfx, _t19, _selfw * _t20) + Math.fma(_selfy, _t21, -(_selfz * _t22)));
        dest.put(destOffset + 1, Math.fma(_selfy, _t19, _selfz * _t20) + Math.fma(_selfw, _t22, -(_selfx * _t21)));
        return rotateYXZ_apiGet_sb01cbf11_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateYXZ_apiGet_sb01cbf11_1(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.put(destOffset + 2, Math.fma(_selfx, _t22, _selfw * _t21) + Math.fma(_selfz, _t19, -(_selfy * _t20)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t19, -(_selfx * _t20)) - Math.fma(_selfy, _t22, _selfz * _t21));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYZX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.rotateYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleY, angleZ, angleX);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateYZX_apiGet(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYZX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t4 * _t6;
        double _t11 = _t3 * _t7;
        double _t14 = _t6 * _t7;
        double _t19 = Math.fma(_t9, _t8, _t14 * _t5);
        double _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        double _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dest.put(destOffset, Math.fma(_selfx, _t21, _selfw * _t19) + Math.fma(_selfy, _t22, -(_selfz * _t20)));
        dest.put(destOffset + 1, Math.fma(_selfy, _t21, _selfz * _t19) + Math.fma(_selfw, _t20, -(_selfx * _t22)));
        return rotateYZX_apiGet_s539ae65d_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateYZX_apiGet_s539ae65d_1(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.put(destOffset + 2, Math.fma(_selfx, _t20, _selfw * _t22) + Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t21, -(_selfx * _t19)) - Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.rotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        double _t2 = Math.cosFromSin(_t1, _t0);
        dest.put(destOffset, Math.fma(_selfx, _t2, _selfy * _t1));
        dest.put(destOffset + 1, Math.fma(_selfy, _t2, -(_selfx * _t1)));
        dest.put(destOffset + 2, Math.fma(_selfz, _t2, _selfw * _t1));
        dest.put(destOffset + 3, Math.fma(_selfw, _t2, -(_selfz * _t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZXY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.rotateZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleZ, angleX, angleY);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateZXY_apiGet(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZXY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = 0.5 * angleX;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleY;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t3 * _t7;
        double _t11 = _t4 * _t6;
        double _t14 = _t6 * _t7;
        double _t19 = Math.fma(_t10, _t5, _t11 * _t8);
        double _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        double _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dest.put(destOffset, Math.fma(_selfx, _t21, _selfw * _t22) + Math.fma(_selfy, _t19, -(_selfz * _t20)));
        dest.put(destOffset + 1, Math.fma(_selfy, _t21, _selfz * _t22) + Math.fma(_selfw, _t20, -(_selfx * _t19)));
        return rotateZXY_apiGet_s86436381_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateZXY_apiGet_s86436381_1(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.put(destOffset + 2, Math.fma(_selfx, _t20, _selfw * _t19) + Math.fma(_selfz, _t21, -(_selfy * _t22)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t21, -(_selfx * _t22)) - Math.fma(_selfy, _t20, _selfz * _t19));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZYX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.rotateZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleZ, angleY, angleX);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.rotateZYX_apiGet(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZYX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = 0.5 * angleY;
        double _t1 = 0.5 * angleZ;
        double _t2 = 0.5 * angleX;
        double _t3 = Math.sin(_t0);
        double _t4 = Math.sin(_t1);
        double _t5 = Math.sin(_t2);
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t6 = Math.cosFromSin(_t3, _t0);
        double _t7 = Math.cosFromSin(_t4, _t1);
        double _t8 = Math.cosFromSin(_t5, _t2);
        double _t9 = _t3 * _t4;
        double _t10 = _t4 * _t6;
        double _t11 = _t3 * _t7;
        double _t12 = _t6 * _t7;
        double _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        double _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        double _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        double _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dest.put(destOffset, Math.fma(_selfx, _t19, _selfw * _t21) + Math.fma(_selfy, _t22, -(_selfz * _t20)));
        dest.put(destOffset + 1, Math.fma(_selfy, _t19, _selfz * _t21) + Math.fma(_selfw, _t20, -(_selfx * _t22)));
        return rotateZYX_apiGet_s1a5c95d5_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateZYX_apiGet_s1a5c95d5_1(java.nio.DoubleBuffer dest, int destOffset, double _selfx, double _selfy, double _selfz, double _selfw, double _t19, double _t20, double _t21, double _t22) {
        dest.put(destOffset + 2, Math.fma(_selfx, _t20, _selfw * _t22) + Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.put(destOffset + 3, Math.fma(_selfw, _t19, -(_selfx * _t21)) - Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    public static java.nio.DoubleBuffer transform_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer transform_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.transform(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.transform_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer transform_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, vY, -(_selfy * vX));
        double _t10 = 2.0 * Math.fma(_selfz, vX, -(_selfx * vZ));
        double _t11 = 2.0 * Math.fma(_selfy, vZ, -(_selfz * vY));
        dest.put(destOffset, Math.fma(_selfy, _t9, Math.fma(-_selfz, _t10, Math.fma(_selfw, _t11, vX))));
        dest.put(destOffset + 1, Math.fma(_selfz, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, vY))));
        dest.put(destOffset + 2, Math.fma(_selfx, _t10, Math.fma(-_selfy, _t11, Math.fma(_selfw, _t9, vZ))));
        return dest;
    }

    public static java.nio.DoubleBuffer transform_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        DoubleQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer transform_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            DoubleQuatOps.transform(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.transform_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer transform_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        double _t9 = 2.0 * Math.fma(_selfx, _vy, -(_selfy * _vx));
        double _t10 = 2.0 * Math.fma(_selfz, _vx, -(_selfx * _vz));
        double _t11 = 2.0 * Math.fma(_selfy, _vz, -(_selfz * _vy));
        dest.put(destOffset, Math.fma(_selfy, _t9, Math.fma(-_selfz, _t10, Math.fma(_selfw, _t11, _vx))));
        dest.put(destOffset + 1, Math.fma(_selfz, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, _vy))));
        dest.put(destOffset + 2, Math.fma(_selfx, _t10, Math.fma(-_selfy, _t11, Math.fma(_selfw, _t9, _vz))));
        return dest;
    }

    public static java.nio.DoubleBuffer transformInverse_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        DoubleQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer transformInverse_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            DoubleQuatOps.transformInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.transformInverse_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer transformInverse_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _t9 = 2.0 * Math.fma(_selfx, vZ, -(_selfz * vX));
        double _t10 = 2.0 * Math.fma(_selfy, vX, -(_selfx * vY));
        double _t11 = 2.0 * Math.fma(_selfz, vY, -(_selfy * vZ));
        dest.put(destOffset, Math.fma(_selfz, _t9, Math.fma(-_selfy, _t10, Math.fma(_selfw, _t11, vX))));
        dest.put(destOffset + 1, Math.fma(_selfx, _t10, Math.fma(-_selfz, _t11, Math.fma(_selfw, _t9, vY))));
        dest.put(destOffset + 2, Math.fma(_selfy, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, vZ))));
        return dest;
    }

    public static java.nio.DoubleBuffer transformInverse_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        DoubleQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer transformInverse_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            DoubleQuatOps.transformInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        DoubleQuatOpsKernelsTypedBuffer.transformInverse_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer transformInverse_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _selfx = src.get(srcOffset);
        double _selfy = src.get(srcOffset + 1);
        double _selfz = src.get(srcOffset + 2);
        double _selfw = src.get(srcOffset + 3);
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        double _t9 = 2.0 * Math.fma(_selfx, _vz, -(_selfz * _vx));
        double _t10 = 2.0 * Math.fma(_selfy, _vx, -(_selfx * _vy));
        double _t11 = 2.0 * Math.fma(_selfz, _vy, -(_selfy * _vz));
        dest.put(destOffset, Math.fma(_selfz, _t9, Math.fma(-_selfy, _t10, Math.fma(_selfw, _t11, _vx))));
        dest.put(destOffset + 1, Math.fma(_selfx, _t10, Math.fma(-_selfz, _t11, Math.fma(_selfw, _t9, _vy))));
        dest.put(destOffset + 2, Math.fma(_selfy, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, _vz))));
        return dest;
    }

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]:
     * 2 asin(|a-b|/2) up to pi/2 and pi - 2 asin(|a+b|/2) beyond, so asin always sees an
     * argument of at most sqrt(2)/2 and the angle stays accurate at both ends.
     */
    private static double quatArcAngle(double s) {
        double d = 4.0 - s;
        return s > d ? 2.0 * Math.asin(0.5 * java.lang.Math.sqrt(d)) : Math.PI - 2.0 * Math.asin(0.5 * java.lang.Math.sqrt(s));
    }
}
