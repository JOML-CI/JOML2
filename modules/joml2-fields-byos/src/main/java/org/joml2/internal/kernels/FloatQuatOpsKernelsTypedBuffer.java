// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link FloatQuatOps} whose leading storage
 * parameter is a typed {@link java.nio.FloatBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code FloatQuatOps}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class FloatQuatOpsKernelsTypedBuffer {
    private FloatQuatOpsKernelsTypedBuffer() {}

    public static java.nio.FloatBuffer invert_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invert_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invert(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invert_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invert_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invert_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t3_inv = 1.0f / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
            dest.put(destOffset, -(_selfx * _t3_inv));
            dest.put(destOffset + 1, -(_selfy * _t3_inv));
            dest.put(destOffset + 2, -(_selfz * _t3_inv));
            dest.put(destOffset + 3, _selfw * _t3_inv);
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t3_inv = 1.0f / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
            dest.put(destOffset, -(_selfx * _t3_inv));
            dest.put(destOffset + 1, -(_selfy * _t3_inv));
            dest.put(destOffset + 2, -(_selfz * _t3_inv));
            dest.put(destOffset + 3, _selfw * _t3_inv);
            return dest;
        }
    }

    public static java.nio.FloatBuffer invertProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invertProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invertProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, otherZ, otherW);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invertProduct_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_apiGet_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t20 = java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t21 = java.lang.Math.fma(otherW, _selfw, -otherX * _selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz);
        float _t22 = java.lang.Math.fma(otherY, _selfx, otherZ * _selfw) + java.lang.Math.fma(otherW, _selfz, -otherX * _selfy);
        float _t23 = java.lang.Math.fma(otherX, _selfz, otherW * _selfy) + java.lang.Math.fma(otherY, _selfw, -(otherZ * _selfx));
        float _t27_inv = 1.0f / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        dest.put(destOffset, -(_t20 * _t27_inv));
        dest.put(destOffset + 1, -(_t23 * _t27_inv));
        dest.put(destOffset + 2, -(_t22 * _t27_inv));
        dest.put(destOffset + 3, _t21 * _t27_inv);
        return dest;
    }

    /** {@code invertProduct_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer invertProduct_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t20 = ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherZ) * (_selfy) - (otherY * _selfz));
        float _t21 = ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz));
        float _t22 = ((otherY) * (_selfx) + (otherZ * _selfw)) + ((otherW) * (_selfz) - (otherX * _selfy));
        float _t23 = ((otherX) * (_selfz) + (otherW * _selfy)) + ((otherY) * (_selfw) - (otherZ * _selfx));
        float _t27_inv = 1.0f / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
        dest.put(destOffset, -(_t20 * _t27_inv));
        dest.put(destOffset + 1, -(_t23 * _t27_inv));
        dest.put(destOffset + 2, -(_t22 * _t27_inv));
        dest.put(destOffset + 3, _t21 * _t27_inv);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        FloatQuatOpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            FloatQuatOps.invertProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invertProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invertProduct_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invertProduct_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t20 = java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t21 = java.lang.Math.fma(_otherw, _selfw, -_otherx * _selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz);
        float _t22 = java.lang.Math.fma(_othery, _selfx, _otherz * _selfw) + java.lang.Math.fma(_otherw, _selfz, -_otherx * _selfy);
        float _t23 = java.lang.Math.fma(_otherx, _selfz, _otherw * _selfy) + java.lang.Math.fma(_othery, _selfw, -(_otherz * _selfx));
        float _t27_inv = 1.0f / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        dest.put(destOffset, -(_t20 * _t27_inv));
        dest.put(destOffset + 1, -(_t23 * _t27_inv));
        dest.put(destOffset + 2, -(_t22 * _t27_inv));
        dest.put(destOffset + 3, _t21 * _t27_inv);
        return dest;
    }

    /** {@code invertProduct_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer invertProduct_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t20 = ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_otherz) * (_selfy) - (_othery * _selfz));
        float _t21 = ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz));
        float _t22 = ((_othery) * (_selfx) + (_otherz * _selfw)) + ((_otherw) * (_selfz) - (_otherx * _selfy));
        float _t23 = ((_otherx) * (_selfz) + (_otherw * _selfy)) + ((_othery) * (_selfw) - (_otherz * _selfx));
        float _t27_inv = 1.0f / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
        dest.put(destOffset, -(_t20 * _t27_inv));
        dest.put(destOffset + 1, -(_t23 * _t27_inv));
        dest.put(destOffset + 2, -(_t22 * _t27_inv));
        dest.put(destOffset + 3, _t21 * _t27_inv);
        return dest;
    }

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.add_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, otherZ, otherW);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, otherX + src.get(srcOffset));
        dest.put(destOffset + 1, otherY + _selfy);
        dest.put(destOffset + 2, otherZ + _selfz);
        dest.put(destOffset + 3, otherW + _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        FloatQuatOpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            FloatQuatOps.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.add_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, other.get(otherOffset) + src.get(srcOffset));
        dest.put(destOffset + 1, _othery + _selfy);
        dest.put(destOffset + 2, _otherz + _selfz);
        dest.put(destOffset + 3, _otherw + _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, scalar);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, scalar * src.get(srcOffset));
        dest.put(destOffset + 1, scalar * _selfy);
        dest.put(destOffset + 2, scalar * _selfz);
        dest.put(destOffset + 3, scalar * _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer negate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.negate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, -src.get(srcOffset));
        dest.put(destOffset + 1, -_selfy);
        dest.put(destOffset + 2, -_selfz);
        dest.put(destOffset + 3, -_selfw);
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.sub_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, otherZ, otherW);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) - otherX);
        dest.put(destOffset + 1, _selfy - otherY);
        dest.put(destOffset + 2, _selfz - otherZ);
        dest.put(destOffset + 3, _selfw - otherW);
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        FloatQuatOpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            FloatQuatOps.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.sub_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, src.get(srcOffset) - other.get(otherOffset));
        dest.put(destOffset + 1, _selfy - _othery);
        dest.put(destOffset + 2, _selfz - _otherz);
        dest.put(destOffset + 3, _selfw - _otherw);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformRotation_unsafe(java.nio.FloatBuffer dest, int destOffset, float u1, float u2, float u3) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeUniformRotation_unsafe(_destBase, u1, u2, u3);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformRotation_api(java.nio.FloatBuffer dest, int destOffset, float u1, float u2, float u3) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeUniformRotation(dest.array(), dest.arrayOffset() + destOffset, u1, u2, u3);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeUniformRotation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, u1, u2, u3);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeUniformRotation_apiGet(dest, destOffset, u1, u2, u3);
        return dest;
    }

    public static java.nio.FloatBuffer makeUniformRotation_apiGet(java.nio.FloatBuffer dest, int destOffset, float u1, float u2, float u3) {
        float _t0 = (float) java.lang.Math.sqrt(u1);
        float _t1 = u2 * 6.2831855f;
        float _t3 = u3 * 6.2831855f;
        float _t4 = (float) java.lang.Math.sqrt(1.0f - u1);
        dest.put(destOffset, Math.sinWithCos(_t1) * _t4);
        dest.put(destOffset + 1, Math.cosWithSin(_t1) * _t4);
        dest.put(destOffset + 2, Math.sinWithCos(_t3) * _t0);
        dest.put(destOffset + 3, Math.cosWithSin(_t3) * _t0);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.set(dest.array(), dest.arrayOffset() + destOffset, vX, vY, vZ, vW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, vX, vY, vZ, vW);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.set_apiGet(dest, destOffset, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        dest.put(destOffset, vX);
        dest.put(destOffset + 1, vY);
        dest.put(destOffset + 2, vZ);
        dest.put(destOffset + 3, vW);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        FloatQuatOpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4) {
            FloatQuatOps.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        float _vw = v.get(vOffset + 3);
        dest.put(destOffset, v.get(vOffset));
        dest.put(destOffset + 1, _vy);
        dest.put(destOffset + 2, _vz);
        dest.put(destOffset + 3, _vw);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromDualQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromDualQuat_api(java.nio.FloatBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeFromDualQuat(dest.array(), dest.arrayOffset() + destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeFromDualQuat_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, dqRX, dqRY, dqRZ, dqRW);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeFromDualQuat_apiGet(dest, destOffset, dqRX, dqRY, dqRZ, dqRW);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromDualQuat_apiGet(java.nio.FloatBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW) {
        dest.put(destOffset, dqRX);
        dest.put(destOffset + 1, dqRY);
        dest.put(destOffset + 2, dqRZ);
        dest.put(destOffset + 3, dqRW);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromMatrixMat3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 4L;
        FloatQuatOpsKernelsAddress.makeFromMatrixMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromMatrixMat3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 9) {
            FloatQuatOps.makeFromMatrixMat3x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.makeFromMatrixMat3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), mOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeFromMatrixMat3x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromMatrixMat3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        float _m00 = m.get(mOffset);
        float _m10 = m.get(mOffset + 1);
        float _m20 = m.get(mOffset + 2);
        float _m01 = m.get(mOffset + 3);
        float _m11 = m.get(mOffset + 4);
        float _m21 = m.get(mOffset + 5);
        float _m02 = m.get(mOffset + 6);
        float _m12 = m.get(mOffset + 7);
        float _m22 = m.get(mOffset + 8);
        float _t0 = _m00 + _m11;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        return makeFromMatrixMat3x3_apiGet_se820cc8b_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x3_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeFromMatrixMat3x3_apiGet_se820cc8b_1(java.nio.FloatBuffer dest, int destOffset, float _m00, float _m11, float _m22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            dest.put(destOffset, _sp0 * _t1);
            dest.put(destOffset + 1, _sp0 * _t7);
            dest.put(destOffset + 2, _sp0 * _t9);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.put(destOffset + 1, _sp3 * _t4);
                dest.put(destOffset + 2, _sp3 * _t6);
                dest.put(destOffset + 3, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.put(destOffset, _sp1 * _t4);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.put(destOffset + 2, _sp1 * _t8);
                    dest.put(destOffset + 3, _sp1 * _t7);
                } else {
                    dest.put(destOffset, _sp2 * _t6);
                    dest.put(destOffset + 1, _sp2 * _t8);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.put(destOffset + 3, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeFromMatrixMat3x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 4L;
        FloatQuatOpsKernelsAddress.makeFromMatrixMat3x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromMatrixMat3x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 12) {
            FloatQuatOps.makeFromMatrixMat3x4(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.makeFromMatrixMat3x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), mOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeFromMatrixMat3x4_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromMatrixMat3x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        float _m00 = m.get(mOffset);
        float _m01 = m.get(mOffset + 1);
        float _m02 = m.get(mOffset + 2);
        float _m10 = m.get(mOffset + 4);
        float _m11 = m.get(mOffset + 5);
        float _m12 = m.get(mOffset + 6);
        float _m20 = m.get(mOffset + 8);
        float _m21 = m.get(mOffset + 9);
        float _m22 = m.get(mOffset + 10);
        float _t0 = _m00 + _m11;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        return makeFromMatrixMat3x4_apiGet_s9b87c622_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x4_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeFromMatrixMat3x4_apiGet_s9b87c622_1(java.nio.FloatBuffer dest, int destOffset, float _m00, float _m11, float _m22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            dest.put(destOffset, _sp0 * _t1);
            dest.put(destOffset + 1, _sp0 * _t7);
            dest.put(destOffset + 2, _sp0 * _t9);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.put(destOffset + 1, _sp3 * _t4);
                dest.put(destOffset + 2, _sp3 * _t6);
                dest.put(destOffset + 3, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.put(destOffset, _sp1 * _t4);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.put(destOffset + 2, _sp1 * _t8);
                    dest.put(destOffset + 3, _sp1 * _t7);
                } else {
                    dest.put(destOffset, _sp2 * _t6);
                    dest.put(destOffset + 1, _sp2 * _t8);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.put(destOffset + 3, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeFromMatrixMat4x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 4L;
        FloatQuatOpsKernelsAddress.makeFromMatrixMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromMatrixMat4x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 16) {
            FloatQuatOps.makeFromMatrixMat4x4(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.makeFromMatrixMat4x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), mOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeFromMatrixMat4x4_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeFromMatrixMat4x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        float _m00 = m.get(mOffset);
        float _m10 = m.get(mOffset + 1);
        float _m20 = m.get(mOffset + 2);
        float _m01 = m.get(mOffset + 4);
        float _m11 = m.get(mOffset + 5);
        float _m21 = m.get(mOffset + 6);
        float _m02 = m.get(mOffset + 8);
        float _m12 = m.get(mOffset + 9);
        float _m22 = m.get(mOffset + 10);
        float _t0 = _m00 + _m11;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        return makeFromMatrixMat4x4_apiGet_s75a845c3_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat4x4_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeFromMatrixMat4x4_apiGet_s75a845c3_1(java.nio.FloatBuffer dest, int destOffset, float _m00, float _m11, float _m22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            dest.put(destOffset, _sp0 * _t1);
            dest.put(destOffset + 1, _sp0 * _t7);
            dest.put(destOffset + 2, _sp0 * _t9);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t14));
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t15));
                dest.put(destOffset + 1, _sp3 * _t4);
                dest.put(destOffset + 2, _sp3 * _t6);
                dest.put(destOffset + 3, _sp3 * _t1);
            } else {
                if (_m11 > _m22) {
                    dest.put(destOffset, _sp1 * _t4);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t16));
                    dest.put(destOffset + 2, _sp1 * _t8);
                    dest.put(destOffset + 3, _sp1 * _t7);
                } else {
                    dest.put(destOffset, _sp2 * _t6);
                    dest.put(destOffset + 1, _sp2 * _t8);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t17));
                    dest.put(destOffset + 3, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer toDualQuat_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toDualQuat_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 8 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.toDualQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.toDualQuat_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.toDualQuat_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toDualQuat_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _selfy);
        dest.put(destOffset + 2, _selfz);
        dest.put(destOffset + 3, _selfw);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer toMatrix_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.toMatrix_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toMatrix_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.toMatrix(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.toMatrix_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.toMatrix_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toMatrix_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix_apiGet_fma(dest, destOffset, src, srcOffset);
        return toMatrix_apiGet_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code toMatrix_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer toMatrix_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.put(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.put(destOffset + 1, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1));
        dest.put(destOffset + 2, 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2));
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1));
        dest.put(destOffset + 5, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.put(destOffset + 6, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2));
        dest.put(destOffset + 9, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.put(destOffset + 10, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return toMatrix_apiGet_fma_sd34f510d_1(dest, destOffset);
    }

    /** Piece 2 of {@code toMatrix_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer toMatrix_apiGet_fma_sd34f510d_1(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset + 11, 0.0f);
        dest.put(destOffset + 12, 0.0f);
        dest.put(destOffset + 13, 0.0f);
        dest.put(destOffset + 14, 0.0f);
        dest.put(destOffset + 15, 1.0f);
        return dest;
    }

    /** {@code toMatrix_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer toMatrix_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.put(destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0)))));
        dest.put(destOffset + 1, 2.0f * ((_selfx) * (_selfy) + (_t1)));
        dest.put(destOffset + 2, 2.0f * ((_selfx) * (_selfz) - (_t2)));
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 2.0f * ((_selfx) * (_selfy) - (_t1)));
        dest.put(destOffset + 5, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))));
        dest.put(destOffset + 6, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 2.0f * ((_selfx) * (_selfz) + (_t2)));
        dest.put(destOffset + 9, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.put(destOffset + 10, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        dest.put(destOffset + 11, 0.0f);
        dest.put(destOffset + 12, 0.0f);
        dest.put(destOffset + 13, 0.0f);
        dest.put(destOffset + 14, 0.0f);
        dest.put(destOffset + 15, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer toMatrix3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.toMatrix3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toMatrix3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.toMatrix3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.toMatrix3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.toMatrix3x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toMatrix3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix3x3_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.put(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.put(destOffset + 1, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1));
        dest.put(destOffset + 2, 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2));
        dest.put(destOffset + 3, 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1));
        dest.put(destOffset + 4, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.put(destOffset + 5, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.put(destOffset + 6, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2));
        dest.put(destOffset + 7, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.put(destOffset + 8, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        return dest;
    }

    /** {@code toMatrix3x3_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer toMatrix3x3_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.put(destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0)))));
        dest.put(destOffset + 1, 2.0f * ((_selfx) * (_selfy) + (_t1)));
        dest.put(destOffset + 2, 2.0f * ((_selfx) * (_selfz) - (_t2)));
        dest.put(destOffset + 3, 2.0f * ((_selfx) * (_selfy) - (_t1)));
        dest.put(destOffset + 4, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))));
        dest.put(destOffset + 5, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.put(destOffset + 6, 2.0f * ((_selfx) * (_selfz) + (_t2)));
        dest.put(destOffset + 7, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.put(destOffset + 8, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        return dest;
    }

    public static java.nio.FloatBuffer toMatrix3x4_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.toMatrix3x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer toMatrix3x4_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.toMatrix3x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.toMatrix3x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.toMatrix3x4_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer toMatrix3x4_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return toMatrix3x4_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.put(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f));
        dest.put(destOffset + 1, 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1));
        dest.put(destOffset + 2, 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2));
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1));
        dest.put(destOffset + 5, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f));
        dest.put(destOffset + 6, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2));
        dest.put(destOffset + 9, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest.put(destOffset + 10, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    /** {@code toMatrix3x4_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer toMatrix3x4_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest.put(destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0)))));
        dest.put(destOffset + 1, 2.0f * ((_selfx) * (_selfy) - (_t1)));
        dest.put(destOffset + 2, 2.0f * ((_selfx) * (_selfz) + (_t2)));
        dest.put(destOffset + 3, 0.0f);
        dest.put(destOffset + 4, 2.0f * ((_selfx) * (_selfy) + (_t1)));
        dest.put(destOffset + 5, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0)))));
        dest.put(destOffset + 6, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 2.0f * ((_selfx) * (_selfz) - (_t2)));
        dest.put(destOffset + 9, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
        dest.put(destOffset + 10, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
        dest.put(destOffset + 11, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeSwingTwist_unsafe(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        long _swingBase = UnsafeOpsHolder.U.getLong(swing, UnsafeCopy.BB_ADDRESS_OFFSET) + swingOffset * 4L;
        long _twistBase = UnsafeOpsHolder.U.getLong(twist, UnsafeCopy.BB_ADDRESS_OFFSET) + twistOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.decomposeSwingTwist_unsafe(_swingBase, _twistBase, _srcBase, axisX, axisY, axisZ);
        return swing;
    }

    public static java.nio.FloatBuffer decomposeSwingTwist_api(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (swing.hasArray() && swingOffset >= 0 && swingOffset <= swing.limit() - 4 && twist.hasArray() && twistOffset >= 0 && twistOffset <= twist.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.decomposeSwingTwist(swing.array(), swing.arrayOffset() + swingOffset, twist.array(), twist.arrayOffset() + twistOffset, src.array(), src.arrayOffset() + srcOffset, axisX, axisY, axisZ);
            return swing;
        }
        if (swing.order() == java.nio.ByteOrder.nativeOrder() && !swing.isReadOnly() && twist.order() == java.nio.ByteOrder.nativeOrder() && !twist.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.decomposeSwingTwist_api(java.lang.foreign.MemorySegment.ofBuffer(swing.duplicate().position(0)), swingOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(twist.duplicate().position(0)), twistOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, axisX, axisY, axisZ);
            return swing;
        }
        FloatQuatOpsKernelsTypedBuffer.decomposeSwingTwist_apiGet(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
        return swing;
    }

    public static java.nio.FloatBuffer decomposeSwingTwist_apiGet(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSwingTwist_apiGet_fma(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
        return decomposeSwingTwist_apiGet_mulAdd(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@code decomposeSwingTwist_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer decomposeSwingTwist_apiGet_fma(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        swing.put(swingOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        swing.put(swingOffset + 1, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        swing.put(swingOffset + 2, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        return decomposeSwingTwist_apiGet_fma_se5245713_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer decomposeSwingTwist_apiGet_fma_se5245713_1(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing.put(swingOffset + 3, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        twist.put(twistOffset, _t12);
        twist.put(twistOffset + 1, _t13);
        twist.put(twistOffset + 2, _t14);
        twist.put(twistOffset + 3, _t11);
        return swing;
    }

    /** {@code decomposeSwingTwist_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer decomposeSwingTwist_apiGet_mulAdd(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        swing.put(swingOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        swing.put(swingOffset + 1, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        swing.put(swingOffset + 2, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        swing.put(swingOffset + 3, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        twist.put(twistOffset, _t12);
        twist.put(twistOffset + 1, _t13);
        twist.put(twistOffset + 2, _t14);
        twist.put(twistOffset + 3, _t11);
        return swing;
    }

    public static java.nio.FloatBuffer decomposeSwingTwist_unsafe(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        long _swingBase = UnsafeOpsHolder.U.getLong(swing, UnsafeCopy.BB_ADDRESS_OFFSET) + swingOffset * 4L;
        long _twistBase = UnsafeOpsHolder.U.getLong(twist, UnsafeCopy.BB_ADDRESS_OFFSET) + twistOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 4L;
        FloatQuatOpsKernelsAddress.decomposeSwingTwist_unsafe(_swingBase, _twistBase, _srcBase, _axisBase);
        return swing;
    }

    public static java.nio.FloatBuffer decomposeSwingTwist_api(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        if (swing.hasArray() && swingOffset >= 0 && swingOffset <= swing.limit() - 4 && twist.hasArray() && twistOffset >= 0 && twistOffset <= twist.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            FloatQuatOps.decomposeSwingTwist(swing.array(), swing.arrayOffset() + swingOffset, twist.array(), twist.arrayOffset() + twistOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset);
            return swing;
        }
        if (swing.order() == java.nio.ByteOrder.nativeOrder() && !swing.isReadOnly() && twist.order() == java.nio.ByteOrder.nativeOrder() && !twist.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.decomposeSwingTwist_api(java.lang.foreign.MemorySegment.ofBuffer(swing.duplicate().position(0)), swingOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(twist.duplicate().position(0)), twistOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(axis.duplicate().position(0)), axisOffset * 4L);
            return swing;
        }
        FloatQuatOpsKernelsTypedBuffer.decomposeSwingTwist_apiGet(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
        return swing;
    }

    public static java.nio.FloatBuffer decomposeSwingTwist_apiGet(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return decomposeSwingTwist_apiGet_fma(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
        return decomposeSwingTwist_apiGet_mulAdd(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@code decomposeSwingTwist_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer decomposeSwingTwist_apiGet_fma(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        float _axisx = axis.get(axisOffset);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        swing.put(swingOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        swing.put(swingOffset + 1, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        swing.put(swingOffset + 2, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        return decomposeSwingTwist_apiGet_fma_sbd4cfe19_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer decomposeSwingTwist_apiGet_fma_sbd4cfe19_1(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing.put(swingOffset + 3, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        twist.put(twistOffset, _t12);
        twist.put(twistOffset + 1, _t13);
        twist.put(twistOffset + 2, _t14);
        twist.put(twistOffset + 3, _t11);
        return swing;
    }

    /** {@code decomposeSwingTwist_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer decomposeSwingTwist_apiGet_mulAdd(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        float _axisx = axis.get(axisOffset);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        swing.put(swingOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        swing.put(swingOffset + 1, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        swing.put(swingOffset + 2, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        return decomposeSwingTwist_apiGet_mulAdd_s830079d4_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer decomposeSwingTwist_apiGet_mulAdd_s830079d4_1(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing.put(swingOffset + 3, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        twist.put(twistOffset, _t12);
        twist.put(twistOffset + 1, _t13);
        twist.put(twistOffset + 2, _t14);
        twist.put(twistOffset + 3, _t11);
        return swing;
    }

    public static java.nio.FloatBuffer getSwing_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        FloatQuatOpsKernelsAddress.getSwing_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer getSwing_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getSwing(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axisX, axisY, axisZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getSwing_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, axisX, axisY, axisZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getSwing_apiGet(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer getSwing_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getSwing_apiGet_mulAdd(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        return dest;
    }

    /** {@code getSwing_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getSwing_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        dest.put(destOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        dest.put(destOffset + 1, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        dest.put(destOffset + 2, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        dest.put(destOffset + 3, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return dest;
    }

    public static java.nio.FloatBuffer getSwing_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        FloatQuatOpsKernelsAddress.getSwing_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 4L);
        return dest;
    }

    public static java.nio.FloatBuffer getSwing_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            FloatQuatOps.getSwing(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getSwing_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(axis.duplicate().position(0)), axisOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getSwing_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getSwing_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getSwing_apiGet_fma(dest, destOffset, src, srcOffset, axis, axisOffset);
        return getSwing_apiGet_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@code getSwing_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getSwing_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        float _axisx = axis.get(axisOffset);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t11, -_selfw * _t12) + java.lang.Math.fma(_selfz, _t13, -_selfy * _t14));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfx, _t14, -_selfw * _t13) + java.lang.Math.fma(_selfy, _t11, -_selfz * _t12));
        return getSwing_apiGet_fma_sd901e093_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code getSwing_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer getSwing_apiGet_fma_sd901e093_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        dest.put(destOffset + 2, java.lang.Math.fma(_selfy, _t12, _selfz * _t11) + java.lang.Math.fma(-_selfx, _t13, -_selfw * _t14));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfx, _t12, _selfw * _t11) - java.lang.Math.fma(-_selfz, _t14, -_selfy * _t13));
        return dest;
    }

    /** {@code getSwing_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getSwing_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        float _axisx = axis.get(axisOffset);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        dest.put(destOffset, ((_selfx) * (_t11) - (_selfw * _t12)) + ((_selfz) * (_t13) - (_selfy * _t14)));
        dest.put(destOffset + 1, ((_selfx) * (_t14) - (_selfw * _t13)) + ((_selfy) * (_t11) - (_selfz * _t12)));
        dest.put(destOffset + 2, ((_selfy) * (_t12) + (_selfz * _t11)) + ((-_selfx) * (_t13) - (_selfw * _t14)));
        dest.put(destOffset + 3, ((_selfx) * (_t12) + (_selfw * _t11)) - ((-_selfz) * (_t14) - (_selfy * _t13)));
        return dest;
    }

    public static java.nio.FloatBuffer getTwist_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        FloatQuatOpsKernelsAddress.getTwist_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer getTwist_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getTwist(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axisX, axisY, axisZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getTwist_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, axisX, axisY, axisZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getTwist_apiGet(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer getTwist_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getTwist_apiGet_mulAdd(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = java.lang.Math.fma(axisZ, src.get(srcOffset + 2), java.lang.Math.fma(axisX, src.get(srcOffset), axisY * src.get(srcOffset + 1)));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest.put(destOffset, axisX * _t6);
            dest.put(destOffset + 1, axisY * _t6);
            dest.put(destOffset + 2, axisZ * _t6);
            dest.put(destOffset + 3, _selfw * _t5);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 1.0f);
        }
        return dest;
    }

    /** {@code getTwist_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getTwist_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfw = src.get(srcOffset + 3);
        float _t2 = ((axisZ) * (src.get(srcOffset + 2)) + (((axisX) * (src.get(srcOffset)) + (axisY * src.get(srcOffset + 1)))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest.put(destOffset, axisX * _t6);
            dest.put(destOffset + 1, axisY * _t6);
            dest.put(destOffset + 2, axisZ * _t6);
            dest.put(destOffset + 3, _selfw * _t5);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 1.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer getTwist_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        FloatQuatOpsKernelsAddress.getTwist_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 4L);
        return dest;
    }

    public static java.nio.FloatBuffer getTwist_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            FloatQuatOps.getTwist(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getTwist_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(axis.duplicate().position(0)), axisOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getTwist_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getTwist_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getTwist_apiGet_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset);
        float _axisx = axis.get(axisOffset);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = java.lang.Math.fma(_axisz, src.get(srcOffset + 2), java.lang.Math.fma(_axisx, src.get(srcOffset), _axisy * src.get(srcOffset + 1)));
        float _t4 = java.lang.Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest.put(destOffset, _axisx * _t6);
            dest.put(destOffset + 1, _axisy * _t6);
            dest.put(destOffset + 2, _axisz * _t6);
            dest.put(destOffset + 3, _selfw * _t5);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 1.0f);
        }
        return dest;
    }

    /** {@code getTwist_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getTwist_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        float _axisx = axis.get(axisOffset);
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = ((_axisz) * (src.get(srcOffset + 2)) + (((_axisx) * (src.get(srcOffset)) + (_axisy * src.get(srcOffset + 1)))));
        float _t4 = ((_selfw) * (_selfw) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest.put(destOffset, _axisx * _t6);
            dest.put(destOffset + 1, _axisy * _t6);
            dest.put(destOffset + 2, _axisz * _t6);
            dest.put(destOffset + 3, _selfw * _t5);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 1.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeIdentity(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeIdentity_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeIdentity_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeZero(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeZero_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeZero_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeZero_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.put(destOffset + 1, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(t, otherW - _selfw, _selfw));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.put(destOffset + 1, ((t) * (otherY - _selfy) + (_selfy)));
            dest.put(destOffset + 2, ((t) * (otherZ - _selfz) + (_selfz)));
            dest.put(destOffset + 3, ((t) * (otherW - _selfw) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        FloatQuatOpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            FloatQuatOps.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L, t);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, java.lang.Math.fma(t, other.get(otherOffset) - _selfx, _selfx));
        dest.put(destOffset + 1, java.lang.Math.fma(t, _othery - _selfy, _selfy));
        dest.put(destOffset + 2, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        dest.put(destOffset + 3, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lerp_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, ((t) * (other.get(otherOffset) - _selfx) + (_selfx)));
        dest.put(destOffset + 1, ((t) * (_othery - _selfy) + (_selfy)));
        dest.put(destOffset + 2, ((t) * (_otherz - _selfz) + (_selfz)));
        dest.put(destOffset + 3, ((t) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static java.nio.FloatBuffer nlerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer nlerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.nlerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.nlerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.nlerp_apiGet(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer nlerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return nlerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t0 = 1.0f - alpha;
        float _t5 = java.lang.Math.fma(alpha, targetW, src.get(srcOffset + 3) * _t0);
        float _t6 = java.lang.Math.fma(alpha, targetZ, src.get(srcOffset + 2) * _t0);
        float _t7 = java.lang.Math.fma(alpha, targetX, src.get(srcOffset) * _t0);
        float _t8 = java.lang.Math.fma(alpha, targetY, src.get(srcOffset + 1) * _t0);
        float _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            dest.put(destOffset, _t7 * _t13);
            dest.put(destOffset + 1, _t8 * _t13);
            dest.put(destOffset + 2, _t6 * _t13);
            dest.put(destOffset + 3, _t5 * _t13);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code nlerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer nlerp_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t5 = ((alpha) * (targetW) + (src.get(srcOffset + 3) * _t0));
        float _t6 = ((alpha) * (targetZ) + (src.get(srcOffset + 2) * _t0));
        float _t7 = ((alpha) * (targetX) + (src.get(srcOffset) * _t0));
        float _t8 = ((alpha) * (targetY) + (src.get(srcOffset + 1) * _t0));
        float _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            dest.put(destOffset, _t7 * _t13);
            dest.put(destOffset + 1, _t8 * _t13);
            dest.put(destOffset + 2, _t6 * _t13);
            dest.put(destOffset + 3, _t5 * _t13);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer nlerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 4L;
        FloatQuatOpsKernelsAddress.nlerp_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer nlerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            FloatQuatOps.nlerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, target.array(), target.arrayOffset() + targetOffset, alpha);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && target.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.nlerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(target.duplicate().position(0)), targetOffset * 4L, alpha);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.nlerp_apiGet(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer nlerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return nlerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _t0 = 1.0f - alpha;
        float _t5 = java.lang.Math.fma(alpha, target.get(targetOffset + 3), src.get(srcOffset + 3) * _t0);
        float _t6 = java.lang.Math.fma(alpha, target.get(targetOffset + 2), src.get(srcOffset + 2) * _t0);
        float _t7 = java.lang.Math.fma(alpha, target.get(targetOffset), src.get(srcOffset) * _t0);
        float _t8 = java.lang.Math.fma(alpha, target.get(targetOffset + 1), src.get(srcOffset + 1) * _t0);
        float _t12 = java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, java.lang.Math.fma(_t7, _t7, _t8 * _t8)));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            dest.put(destOffset, _t7 * _t13);
            dest.put(destOffset + 1, _t8 * _t13);
            dest.put(destOffset + 2, _t6 * _t13);
            dest.put(destOffset + 3, _t5 * _t13);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code nlerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer nlerp_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t5 = ((alpha) * (target.get(targetOffset + 3)) + (src.get(srcOffset + 3) * _t0));
        float _t6 = ((alpha) * (target.get(targetOffset + 2)) + (src.get(srcOffset + 2) * _t0));
        float _t7 = ((alpha) * (target.get(targetOffset)) + (src.get(srcOffset) * _t0));
        float _t8 = ((alpha) * (target.get(targetOffset + 1)) + (src.get(srcOffset + 1) * _t0));
        float _t12 = ((_t5) * (_t5) + (((_t6) * (_t6) + (((_t7) * (_t7) + (_t8 * _t8))))));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        if (_t12 != 0.0f) {
            dest.put(destOffset, _t7 * _t13);
            dest.put(destOffset + 1, _t8 * _t13);
            dest.put(destOffset + 2, _t6 * _t13);
            dest.put(destOffset + 3, _t5 * _t13);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer nlerpShortest_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.nlerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer nlerpShortest_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.nlerpShortest(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.nlerpShortest_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.nlerpShortest_apiGet(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer nlerpShortest_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerpShortest_apiGet_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return nlerpShortest_apiGet_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code nlerpShortest_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer nlerpShortest_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = 1.0f - alpha;
        float _t18, _t19, _t20, _t21;
        if (-java.lang.Math.fma(_selfw, targetW, java.lang.Math.fma(_selfz, targetZ, java.lang.Math.fma(_selfx, targetX, _selfy * targetY))) > 0.0f) {
            _t18 = java.lang.Math.fma(alpha, -targetW, _selfw * _t1);
            _t19 = java.lang.Math.fma(alpha, -targetZ, _selfz * _t1);
            _t20 = java.lang.Math.fma(alpha, -targetX, _selfx * _t1);
            _t21 = java.lang.Math.fma(alpha, -targetY, _selfy * _t1);
        } else {
            _t18 = java.lang.Math.fma(alpha, targetW, _selfw * _t1);
            _t19 = java.lang.Math.fma(alpha, targetZ, _selfz * _t1);
            _t20 = java.lang.Math.fma(alpha, targetX, _selfx * _t1);
            _t21 = java.lang.Math.fma(alpha, targetY, _selfy * _t1);
        }
        float _t25 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t20, _t20, _t21 * _t21)));
        return nlerpShortest_apiGet_fma_scd19bdc8_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer nlerpShortest_apiGet_fma_scd19bdc8_1(java.nio.FloatBuffer dest, int destOffset, float _t18, float _t19, float _t20, float _t21, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            dest.put(destOffset, _t20 * _t26);
            dest.put(destOffset + 1, _t21 * _t26);
            dest.put(destOffset + 2, _t19 * _t26);
            dest.put(destOffset + 3, _t18 * _t26);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code nlerpShortest_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer nlerpShortest_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = 1.0f - alpha;
        float _t18, _t19, _t20, _t21;
        if (-((_selfw) * (targetW) + (((_selfz) * (targetZ) + (((_selfx) * (targetX) + (_selfy * targetY)))))) > 0.0f) {
            _t18 = ((_selfw * _t1) - (alpha) * (targetW));
            _t19 = ((_selfz * _t1) - (alpha) * (targetZ));
            _t20 = ((_selfx * _t1) - (alpha) * (targetX));
            _t21 = ((_selfy * _t1) - (alpha) * (targetY));
        } else {
            _t18 = ((alpha) * (targetW) + (_selfw * _t1));
            _t19 = ((alpha) * (targetZ) + (_selfz * _t1));
            _t20 = ((alpha) * (targetX) + (_selfx * _t1));
            _t21 = ((alpha) * (targetY) + (_selfy * _t1));
        }
        float _t25 = ((_t18) * (_t18) + (((_t19) * (_t19) + (((_t20) * (_t20) + (_t21 * _t21))))));
        float _t26 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        if (_t25 != 0.0f) {
            dest.put(destOffset, _t20 * _t26);
            dest.put(destOffset + 1, _t21 * _t26);
            dest.put(destOffset + 2, _t19 * _t26);
            dest.put(destOffset + 3, _t18 * _t26);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer nlerpShortest_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 4L;
        FloatQuatOpsKernelsAddress.nlerpShortest_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer nlerpShortest_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            FloatQuatOps.nlerpShortest(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, target.array(), target.arrayOffset() + targetOffset, alpha);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && target.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.nlerpShortest_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(target.duplicate().position(0)), targetOffset * 4L, alpha);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.nlerpShortest_apiGet(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer nlerpShortest_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return nlerpShortest_apiGet_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_apiGet_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code nlerpShortest_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer nlerpShortest_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _targetx = target.get(targetOffset);
        float _targety = target.get(targetOffset + 1);
        float _targetz = target.get(targetOffset + 2);
        float _targetw = target.get(targetOffset + 3);
        float _t1 = 1.0f - alpha;
        float _t18, _t19, _t20, _t21;
        if (-java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety))) > 0.0f) {
            _t18 = java.lang.Math.fma(alpha, -_targetw, _selfw * _t1);
            _t19 = java.lang.Math.fma(alpha, -_targetz, _selfz * _t1);
            _t20 = java.lang.Math.fma(alpha, -_targetx, _selfx * _t1);
            _t21 = java.lang.Math.fma(alpha, -_targety, _selfy * _t1);
        } else {
            _t18 = java.lang.Math.fma(alpha, _targetw, _selfw * _t1);
            _t19 = java.lang.Math.fma(alpha, _targetz, _selfz * _t1);
            _t20 = java.lang.Math.fma(alpha, _targetx, _selfx * _t1);
            _t21 = java.lang.Math.fma(alpha, _targety, _selfy * _t1);
        }
        float _t25 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, java.lang.Math.fma(_t20, _t20, _t21 * _t21)));
        return nlerpShortest_apiGet_fma_s6635d3b6_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer nlerpShortest_apiGet_fma_s6635d3b6_1(java.nio.FloatBuffer dest, int destOffset, float _t18, float _t19, float _t20, float _t21, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            dest.put(destOffset, _t20 * _t26);
            dest.put(destOffset + 1, _t21 * _t26);
            dest.put(destOffset + 2, _t19 * _t26);
            dest.put(destOffset + 3, _t18 * _t26);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code nlerpShortest_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer nlerpShortest_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _targetx = target.get(targetOffset);
        float _targety = target.get(targetOffset + 1);
        float _targetz = target.get(targetOffset + 2);
        float _targetw = target.get(targetOffset + 3);
        float _t1 = 1.0f - alpha;
        float _t18, _t19, _t20, _t21;
        if (-((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety)))))) > 0.0f) {
            _t18 = ((_selfw * _t1) - (alpha) * (_targetw));
            _t19 = ((_selfz * _t1) - (alpha) * (_targetz));
            _t20 = ((_selfx * _t1) - (alpha) * (_targetx));
            _t21 = ((_selfy * _t1) - (alpha) * (_targety));
        } else {
            _t18 = ((alpha) * (_targetw) + (_selfw * _t1));
            _t19 = ((alpha) * (_targetz) + (_selfz * _t1));
            _t20 = ((alpha) * (_targetx) + (_selfx * _t1));
            _t21 = ((alpha) * (_targety) + (_selfy * _t1));
        }
        float _t25 = ((_t18) * (_t18) + (((_t19) * (_t19) + (((_t20) * (_t20) + (_t21 * _t21))))));
        return nlerpShortest_apiGet_mulAdd_s5a7172b1_1(dest, destOffset, _t18, _t19, _t20, _t21, _t25, (1.0f / (float) java.lang.Math.sqrt(_t25)));
    }

    /** Piece 2 of {@code nlerpShortest_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer nlerpShortest_apiGet_mulAdd_s5a7172b1_1(java.nio.FloatBuffer dest, int destOffset, float _t18, float _t19, float _t20, float _t21, float _t25, float _t26) {
        if (_t25 != 0.0f) {
            dest.put(destOffset, _t20 * _t26);
            dest.put(destOffset + 1, _t21 * _t26);
            dest.put(destOffset + 2, _t19 * _t26);
            dest.put(destOffset + 3, _t18 * _t26);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer slerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.slerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_apiGet_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code slerp_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = _selfw + targetW;
        float _t3 = _selfz + targetZ;
        float _t4 = _selfx + targetX;
        float _t5 = _selfy + targetY;
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _ct0 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct0 > 2.0E-6f && _ct0 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t22 = 2.0f * Math.sinWithCos(_t14) / _ct0;
        float _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t22;
        float _t25, _t26;
        if (alpha < 0.5f) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t25, targetX * _t26));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t25, targetY * _t26));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t25, targetZ * _t26));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t25, targetW * _t26));
        return dest;
    }

    /** {@code slerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = _selfw + targetW;
        float _t3 = _selfz + targetZ;
        float _t4 = _selfx + targetX;
        float _t5 = _selfy + targetY;
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _ct0 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct0 > 2.0E-6f && _ct0 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t22 = 2.0f * Math.sinWithCos(_t14) / _ct0;
        float _t24 = Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t22;
        float _t25, _t26;
        if (alpha < 0.5f) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dest.put(destOffset, ((_selfx) * (_t25) + (targetX * _t26)));
        dest.put(destOffset + 1, ((_selfy) * (_t25) + (targetY * _t26)));
        dest.put(destOffset + 2, ((_selfz) * (_t25) + (targetZ * _t26)));
        dest.put(destOffset + 3, ((_selfw) * (_t25) + (targetW * _t26)));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.nio.FloatBuffer slerp_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.slerp_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfw + targetW;
        float _t2 = _selfz + targetZ;
        float _t3 = _selfx + targetX;
        float _t4 = _selfy + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _t13, _t14;
        if (_t11 > 4.0f - _t11) {
            _t13 = 1.0f - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0f - _t5;
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t13, targetX * _t14));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t13, targetY * _t14));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t13, targetZ * _t14));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t13, targetW * _t14));
        return dest;
    }

    /** {@code slerp_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfw + targetW;
        float _t2 = _selfz + targetZ;
        float _t3 = _selfx + targetX;
        float _t4 = _selfy + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _t13, _t14;
        if (_t11 > 4.0f - _t11) {
            _t13 = 1.0f - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0f - _t5;
        }
        dest.put(destOffset, ((_selfx) * (_t13) + (targetX * _t14)));
        dest.put(destOffset + 1, ((_selfy) * (_t13) + (targetY * _t14)));
        dest.put(destOffset + 2, ((_selfz) * (_t13) + (targetZ * _t14)));
        dest.put(destOffset + 3, ((_selfw) * (_t13) + (targetW * _t14)));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 4L;
        FloatQuatOpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            FloatQuatOps.slerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, target.array(), target.arrayOffset() + targetOffset, alpha);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && target.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.slerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(target.duplicate().position(0)), targetOffset * 4L, alpha);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.slerp_apiGet(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_apiGet_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_apiGet_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code slerp_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _targetx = target.get(targetOffset);
        float _targety = target.get(targetOffset + 1);
        float _targetz = target.get(targetOffset + 2);
        float _targetw = target.get(targetOffset + 3);
        float _t2 = _selfw + _targetw;
        float _t3 = _selfz + _targetz;
        float _t4 = _selfx + _targetx;
        float _t5 = _selfy + _targety;
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _ct0 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct0 > 2.0E-6f && _ct0 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _t22 = 2.0f * Math.sinWithCos(_t14) / _ct0;
        float _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t22;
        float _t25, _t26;
        if (alpha < 0.5f) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t25, _targetx * _t26));
        return slerp_apiGet_fma_s23694793_1(dest, destOffset, _selfy, _selfz, _selfw, _targety, _targetz, _targetw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_fma_s23694793_1(java.nio.FloatBuffer dest, int destOffset, float _selfy, float _selfz, float _selfw, float _targety, float _targetz, float _targetw, float _t25, float _t26) {
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t25, _targety * _t26));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t25, _targetz * _t26));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t25, _targetw * _t26));
        return dest;
    }

    /** {@code slerp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _targetx = target.get(targetOffset);
        float _targety = target.get(targetOffset + 1);
        float _targetz = target.get(targetOffset + 2);
        float _targetw = target.get(targetOffset + 3);
        float _t2 = _selfw + _targetw;
        float _t3 = _selfz + _targetz;
        float _t4 = _selfx + _targetx;
        float _t5 = _selfy + _targety;
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _ct0 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct0 > 2.0E-6f && _ct0 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsTypedBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _t22 = 2.0f * Math.sinWithCos(_t14) / _ct0;
        float _t24 = Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t22;
        float _t25, _t26;
        if (alpha < 0.5f) {
            _t25 = _t24;
            _t26 = _t22;
        } else {
            _t25 = _t22;
            _t26 = _t24;
        }
        dest.put(destOffset, ((_selfx) * (_t25) + (_targetx * _t26)));
        return slerp_apiGet_mulAdd_sbd2acc0a_1(dest, destOffset, _selfy, _selfz, _selfw, _targety, _targetz, _targetw, _t25, _t26);
    }

    /** Piece 2 of {@code slerp_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerp_apiGet_mulAdd_sbd2acc0a_1(java.nio.FloatBuffer dest, int destOffset, float _selfy, float _selfz, float _selfw, float _targety, float _targetz, float _targetw, float _t25, float _t26) {
        dest.put(destOffset + 1, ((_selfy) * (_t25) + (_targety * _t26)));
        dest.put(destOffset + 2, ((_selfz) * (_t25) + (_targetz * _t26)));
        dest.put(destOffset + 3, ((_selfw) * (_t25) + (_targetw * _t26)));
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsTypedBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.nio.FloatBuffer slerp_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 4L;
        FloatQuatOpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            FloatQuatOpsKernelsArray.slerp_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, target.array(), target.arrayOffset() + targetOffset, alpha);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && target.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.slerp_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(target.duplicate().position(0)), targetOffset * 4L, alpha);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.slerp_degenerate_apiGet(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer slerp_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_apiGet_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _targetx = target.get(targetOffset);
        float _targety = target.get(targetOffset + 1);
        float _targetz = target.get(targetOffset + 2);
        float _targetw = target.get(targetOffset + 3);
        float _t1 = _selfw + _targetw;
        float _t2 = _selfz + _targetz;
        float _t3 = _selfx + _targetx;
        float _t4 = _selfy + _targety;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _t13, _t14;
        if (_t11 > 4.0f - _t11) {
            _t13 = 1.0f - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0f - _t5;
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t13, _targetx * _t14));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t13, _targety * _t14));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t13, _targetz * _t14));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t13, _targetw * _t14));
        return dest;
    }

    /** {@code slerp_degenerate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerp_degenerate_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _targetx = target.get(targetOffset);
        float _targety = target.get(targetOffset + 1);
        float _targetz = target.get(targetOffset + 2);
        float _targetw = target.get(targetOffset + 3);
        float _t1 = _selfw + _targetw;
        float _t2 = _selfz + _targetz;
        float _t3 = _selfx + _targetx;
        float _t4 = _selfy + _targety;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _t13, _t14;
        if (_t11 > 4.0f - _t11) {
            _t13 = 1.0f - alpha;
            _t14 = alpha;
        } else {
            _t13 = _t5;
            _t14 = 1.0f - _t5;
        }
        dest.put(destOffset, ((_selfx) * (_t13) + (_targetx * _t14)));
        dest.put(destOffset + 1, ((_selfy) * (_t13) + (_targety * _t14)));
        dest.put(destOffset + 2, ((_selfz) * (_t13) + (_targetz * _t14)));
        dest.put(destOffset + 3, ((_selfw) * (_t13) + (_targetw * _t14)));
        return dest;
    }

    public static java.nio.FloatBuffer slerpShortest_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer slerpShortest_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.slerpShortest(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.slerpShortest_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, targetX, targetY, targetZ, targetW, alpha);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.slerpShortest_apiGet(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer slerpShortest_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerpShortest_apiGet_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerpShortest_apiGet_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@code slerpShortest_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerpShortest_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t12 = java.lang.Math.fma(_selfw, targetW, java.lang.Math.fma(_selfz, targetZ, java.lang.Math.fma(_selfx, targetX, _selfy * targetY)));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
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
        float _t30 = (float) java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0f));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return slerpShortest_apiGet_fma_s49bf1459_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)));
    }

    /** Piece 2 of {@code slerpShortest_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerpShortest_apiGet_fma_s49bf1459_1(java.nio.FloatBuffer dest, int destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = java.lang.Math.fma(_selfw, _t38, _t32 * _t21);
            _t44 = java.lang.Math.fma(_selfz, _t38, _t32 * _t22);
            _t45 = java.lang.Math.fma(_selfx, _t38, _t32 * _t23);
            _t46 = java.lang.Math.fma(_selfy, _t38, _t32 * _t24);
        } else {
            _t43 = java.lang.Math.fma(alpha, _t21, _selfw * _t1);
            _t44 = java.lang.Math.fma(alpha, _t22, _selfz * _t1);
            _t45 = java.lang.Math.fma(alpha, _t23, _selfx * _t1);
            _t46 = java.lang.Math.fma(alpha, _t24, _selfy * _t1);
        }
        float _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            dest.put(destOffset, _t51 * _t45);
            dest.put(destOffset + 1, _t51 * _t46);
            dest.put(destOffset + 2, _t51 * _t44);
            dest.put(destOffset + 3, _t51 * _t43);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code slerpShortest_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerpShortest_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = 1.0f - alpha;
        float _t12 = ((_selfw) * (targetW) + (((_selfz) * (targetZ) + (((_selfx) * (targetX) + (_selfy * targetY))))));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
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
        float _t30 = (float) java.lang.Math.sqrt(((1.0f) - (_t15) * (_t15)));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        float _t38 = ((Math.cosWithSin(_t18)) - (_t32) * (_t15));
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = ((_selfw) * (_t38) + (_t32 * _t21));
            _t44 = ((_selfz) * (_t38) + (_t32 * _t22));
            _t45 = ((_selfx) * (_t38) + (_t32 * _t23));
            _t46 = ((_selfy) * (_t38) + (_t32 * _t24));
        } else {
            _t43 = ((alpha) * (_t21) + (_selfw * _t1));
            _t44 = ((alpha) * (_t22) + (_selfz * _t1));
            _t45 = ((alpha) * (_t23) + (_selfx * _t1));
            _t46 = ((alpha) * (_t24) + (_selfy * _t1));
        }
        return slerpShortest_apiGet_mulAdd_s962ad7f2_1(dest, destOffset, _t43, _t44, _t45, _t46);
    }

    /** Piece 2 of {@code slerpShortest_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerpShortest_apiGet_mulAdd_s962ad7f2_1(java.nio.FloatBuffer dest, int destOffset, float _t43, float _t44, float _t45, float _t46) {
        float _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            dest.put(destOffset, _t51 * _t45);
            dest.put(destOffset + 1, _t51 * _t46);
            dest.put(destOffset + 2, _t51 * _t44);
            dest.put(destOffset + 3, _t51 * _t43);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer slerpShortest_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 4L;
        FloatQuatOpsKernelsAddress.slerpShortest_unsafe(_destBase, _srcBase, _targetBase, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer slerpShortest_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            FloatQuatOps.slerpShortest(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, target.array(), target.arrayOffset() + targetOffset, alpha);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && target.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.slerpShortest_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(target.duplicate().position(0)), targetOffset * 4L, alpha);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.slerpShortest_apiGet(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return dest;
    }

    public static java.nio.FloatBuffer slerpShortest_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerpShortest_apiGet_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_apiGet_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@code slerpShortest_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerpShortest_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _targetx = target.get(targetOffset);
        float _targety = target.get(targetOffset + 1);
        float _targetz = target.get(targetOffset + 2);
        float _targetw = target.get(targetOffset + 3);
        float _t12 = java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
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
        float _t30 = (float) java.lang.Math.sqrt(java.lang.Math.fma(-_t15, _t15, 1.0f));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return slerpShortest_apiGet_fma_s3385a481_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18)));
    }

    /** Piece 2 of {@code slerpShortest_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerpShortest_apiGet_fma_s3385a481_1(java.nio.FloatBuffer dest, int destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = java.lang.Math.fma(_selfw, _t38, _t32 * _t21);
            _t44 = java.lang.Math.fma(_selfz, _t38, _t32 * _t22);
            _t45 = java.lang.Math.fma(_selfx, _t38, _t32 * _t23);
            _t46 = java.lang.Math.fma(_selfy, _t38, _t32 * _t24);
        } else {
            _t43 = java.lang.Math.fma(alpha, _t21, _selfw * _t1);
            _t44 = java.lang.Math.fma(alpha, _t22, _selfz * _t1);
            _t45 = java.lang.Math.fma(alpha, _t23, _selfx * _t1);
            _t46 = java.lang.Math.fma(alpha, _t24, _selfy * _t1);
        }
        float _t50 = java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, java.lang.Math.fma(_t45, _t45, _t46 * _t46)));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            dest.put(destOffset, _t51 * _t45);
            dest.put(destOffset + 1, _t51 * _t46);
            dest.put(destOffset + 2, _t51 * _t44);
            dest.put(destOffset + 3, _t51 * _t43);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code slerpShortest_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer slerpShortest_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _targetx = target.get(targetOffset);
        float _targety = target.get(targetOffset + 1);
        float _targetz = target.get(targetOffset + 2);
        float _targetw = target.get(targetOffset + 3);
        float _t12 = ((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety))))));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
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
        float _t30 = (float) java.lang.Math.sqrt(((1.0f) - (_t15) * (_t15)));
        float _t32 = Math.sinWithCos(_t18) / _t30;
        return slerpShortest_apiGet_mulAdd_s52948e48_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, 1.0f - alpha, _t21, _t22, _t23, _t24, _t30, _t32, ((Math.cosWithSin(_t18)) - (_t32) * (_t15)));
    }

    /** Piece 2 of {@code slerpShortest_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer slerpShortest_apiGet_mulAdd_s52948e48_1(java.nio.FloatBuffer dest, int destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t21, float _t22, float _t23, float _t24, float _t30, float _t32, float _t38) {
        float _t43, _t44, _t45, _t46;
        if (_t30 > 0.0f) {
            _t43 = ((_selfw) * (_t38) + (_t32 * _t21));
            _t44 = ((_selfz) * (_t38) + (_t32 * _t22));
            _t45 = ((_selfx) * (_t38) + (_t32 * _t23));
            _t46 = ((_selfy) * (_t38) + (_t32 * _t24));
        } else {
            _t43 = ((alpha) * (_t21) + (_selfw * _t1));
            _t44 = ((alpha) * (_t22) + (_selfz * _t1));
            _t45 = ((alpha) * (_t23) + (_selfx * _t1));
            _t46 = ((alpha) * (_t24) + (_selfy * _t1));
        }
        float _t50 = ((_t43) * (_t43) + (((_t44) * (_t44) + (((_t45) * (_t45) + (_t46 * _t46))))));
        float _t51 = (1.0f / (float) java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0f) {
            dest.put(destOffset, _t51 * _t45);
            dest.put(destOffset + 1, _t51 * _t46);
            dest.put(destOffset + 2, _t51 * _t44);
            dest.put(destOffset + 3, _t51 * _t43);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer squad_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.squad_unsafe(_destBase, _srcBase, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return dest;
    }

    public static java.nio.FloatBuffer squad_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.squad(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.squad_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.squad_apiGet(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return dest;
    }

    public static java.nio.FloatBuffer squad_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return squad_apiGet_fma(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return squad_apiGet_mulAdd(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
    }

    /** {@code squad_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        float _t0 = 1.0f - t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = Math.min(t, _t0);
        float _t23 = Math.min(4.0f, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6))));
        float _t27 = 4.0f - _t23;
        float _t33 = _t7 * quatArcAngle_fma(_t23);
        float _t41 = (float) java.lang.Math.sqrt(_t27 * _t23);
        float _t47 = _t23 > _t27 ? _t7 : 0.0f;
        float _t51 = 2.0f / _t41 * Math.sinWithCos(_t33);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = t + t;
        float _t12 = _t0 * _t1;
        return squad_apiGet_fma_sc1e4611_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t7, _t41, _t47, _t51, _t41 > 2.0E-6f ? java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t23, 1.0f), _t51, Math.cosWithSin(_t33)) : 1.0f - _t47, _selfx, _selfy, _selfz, _selfw, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0f)));
    }

    /** Piece 2 of {@code squad_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_fma_sc1e4611_1(java.nio.FloatBuffer dest, int destOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, float _t7, float _t41, float _t47, float _t51, float _t57, float _selfx, float _selfy, float _selfz, float _selfw, float _t8, float _t9, float _t10, float _t11, float _t12, float _t16) {
        float _t24 = Math.min(4.0f, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
        float _t28 = 4.0f - _t24;
        float _t34 = _t7 * quatArcAngle_fma(_t24);
        float _t43 = (float) java.lang.Math.sqrt(_t28 * _t24);
        float _t48 = _t24 > _t28 ? _t7 : 0.0f;
        float _t52 = 2.0f / _t43 * Math.sinWithCos(_t34);
        float _t55 = _t41 > 2.0E-6f ? _t51 : _t47;
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t52;
            _t58 = java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t24, 1.0f), _t52, Math.cosWithSin(_t34));
        } else {
            _t56 = _t48;
            _t58 = 1.0f - _t48;
        }
        float _t59, _t60, _t61, _t62;
        if (t < 0.5f) {
            _t59 = _t57;
            _t60 = _t55;
            _t61 = _t58;
            _t62 = _t56;
        } else {
            _t59 = _t55;
            _t60 = _t57;
            _t61 = _t56;
            _t62 = _t58;
        }
        return squad_apiGet_fma_sc1e4611_2(dest, destOffset, _t12, _t16, java.lang.Math.fma(control0X, _t59, control1X * _t60), java.lang.Math.fma(control0W, _t59, control1W * _t60), java.lang.Math.fma(_selfw, _t61, targetW * _t62), java.lang.Math.fma(control0Z, _t59, control1Z * _t60), java.lang.Math.fma(_selfz, _t61, targetZ * _t62), java.lang.Math.fma(_selfx, _t61, targetX * _t62), java.lang.Math.fma(control0Y, _t59, control1Y * _t60), java.lang.Math.fma(_selfy, _t61, targetY * _t62));
    }

    /** Piece 3 of {@code squad_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_fma_sc1e4611_2(java.nio.FloatBuffer dest, int destOffset, float _t12, float _t16, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78) {
        float _t79 = _t72 + _t73;
        float _t80 = _t74 + _t75;
        float _t81 = _t71 + _t76;
        float _t82 = _t77 + _t78;
        float _t87 = Math.min(4.0f, java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82))));
        float _t89 = 4.0f - _t87;
        float _t92 = _t16 * quatArcAngle_fma(_t87);
        float _t96 = (float) java.lang.Math.sqrt(_t89 * _t87);
        float _t99 = _t87 > _t89 ? _t16 : 0.0f;
        float _t101 = 2.0f / _t96 * Math.sinWithCos(_t92);
        float _t103, _t104;
        if (_t96 > 2.0E-6f) {
            _t103 = _t101;
            _t104 = java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t87, 1.0f), _t101, Math.cosWithSin(_t92));
        } else {
            _t103 = _t99;
            _t104 = 1.0f - _t99;
        }
        float _t105, _t106;
        if (_t12 < 0.5f) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        dest.put(destOffset, java.lang.Math.fma(_t71, _t105, _t76 * _t106));
        dest.put(destOffset + 1, java.lang.Math.fma(_t77, _t105, _t78 * _t106));
        dest.put(destOffset + 2, java.lang.Math.fma(_t74, _t105, _t75 * _t106));
        dest.put(destOffset + 3, java.lang.Math.fma(_t72, _t105, _t73 * _t106));
        return dest;
    }

    /** {@code squad_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        float _t0 = 1.0f - t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = Math.min(t, _t0);
        float _t23 = Math.min(4.0f, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6)))))));
        float _t27 = 4.0f - _t23;
        float _t33 = _t7 * quatArcAngle_mulAdd(_t23);
        float _t41 = (float) java.lang.Math.sqrt(_t27 * _t23);
        float _t47 = _t23 > _t27 ? _t7 : 0.0f;
        float _t51 = 2.0f / _t41 * Math.sinWithCos(_t33);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = t + t;
        float _t12 = _t0 * _t1;
        return squad_apiGet_mulAdd_sbfc844a_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t7, _t41, _t47, _t51, _t41 > 2.0E-6f ? ((((1.0f) - (0.5f) * (_t23))) * (_t51) + (Math.cosWithSin(_t33))) : 1.0f - _t47, _selfx, _selfy, _selfz, _selfw, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t12, Math.min(_t12, ((1.0f) - (_t0) * (_t1))));
    }

    /** Piece 2 of {@code squad_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_mulAdd_sbfc844a_1(java.nio.FloatBuffer dest, int destOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, float _t7, float _t41, float _t47, float _t51, float _t57, float _selfx, float _selfy, float _selfz, float _selfw, float _t8, float _t9, float _t10, float _t11, float _t12, float _t16) {
        float _t24 = Math.min(4.0f, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
        float _t28 = 4.0f - _t24;
        float _t34 = _t7 * quatArcAngle_mulAdd(_t24);
        float _t43 = (float) java.lang.Math.sqrt(_t28 * _t24);
        float _t48 = _t24 > _t28 ? _t7 : 0.0f;
        float _t52 = 2.0f / _t43 * Math.sinWithCos(_t34);
        float _t55 = _t41 > 2.0E-6f ? _t51 : _t47;
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t52;
            _t58 = ((((1.0f) - (0.5f) * (_t24))) * (_t52) + (Math.cosWithSin(_t34)));
        } else {
            _t56 = _t48;
            _t58 = 1.0f - _t48;
        }
        float _t59, _t60, _t61, _t62;
        if (t < 0.5f) {
            _t59 = _t57;
            _t60 = _t55;
            _t61 = _t58;
            _t62 = _t56;
        } else {
            _t59 = _t55;
            _t60 = _t57;
            _t61 = _t56;
            _t62 = _t58;
        }
        float _t72 = ((control0W) * (_t59) + (control1W * _t60));
        float _t73 = ((_selfw) * (_t61) + (targetW * _t62));
        return squad_apiGet_mulAdd_sbfc844a_2(dest, destOffset, _t12, _t16, ((control0X) * (_t59) + (control1X * _t60)), _t72, _t73, ((control0Z) * (_t59) + (control1Z * _t60)), ((_selfz) * (_t61) + (targetZ * _t62)), ((_selfx) * (_t61) + (targetX * _t62)), ((control0Y) * (_t59) + (control1Y * _t60)), ((_selfy) * (_t61) + (targetY * _t62)), _t72 + _t73);
    }

    /** Piece 3 of {@code squad_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_mulAdd_sbfc844a_2(java.nio.FloatBuffer dest, int destOffset, float _t12, float _t16, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t79) {
        float _t80 = _t74 + _t75;
        float _t81 = _t71 + _t76;
        float _t82 = _t77 + _t78;
        float _t87 = Math.min(4.0f, ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82)))))));
        float _t89 = 4.0f - _t87;
        float _t92 = _t16 * quatArcAngle_mulAdd(_t87);
        float _t96 = (float) java.lang.Math.sqrt(_t89 * _t87);
        float _t99 = _t87 > _t89 ? _t16 : 0.0f;
        float _t101 = 2.0f / _t96 * Math.sinWithCos(_t92);
        float _t103, _t104;
        if (_t96 > 2.0E-6f) {
            _t103 = _t101;
            _t104 = ((((1.0f) - (0.5f) * (_t87))) * (_t101) + (Math.cosWithSin(_t92)));
        } else {
            _t103 = _t99;
            _t104 = 1.0f - _t99;
        }
        float _t105, _t106;
        if (_t12 < 0.5f) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        dest.put(destOffset, ((_t71) * (_t105) + (_t76 * _t106)));
        dest.put(destOffset + 1, ((_t77) * (_t105) + (_t78 * _t106)));
        dest.put(destOffset + 2, ((_t74) * (_t105) + (_t75 * _t106)));
        dest.put(destOffset + 3, ((_t72) * (_t105) + (_t73 * _t106)));
        return dest;
    }

    public static java.nio.FloatBuffer squad_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer control0, int control0Offset, java.nio.FloatBuffer control1, int control1Offset, java.nio.FloatBuffer target, int targetOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _control0Base = UnsafeOpsHolder.U.getLong(control0, UnsafeCopy.BB_ADDRESS_OFFSET) + control0Offset * 4L;
        long _control1Base = UnsafeOpsHolder.U.getLong(control1, UnsafeCopy.BB_ADDRESS_OFFSET) + control1Offset * 4L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 4L;
        FloatQuatOpsKernelsAddress.squad_unsafe(_destBase, _srcBase, _control0Base, _control1Base, _targetBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer squad_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer control0, int control0Offset, java.nio.FloatBuffer control1, int control1Offset, java.nio.FloatBuffer target, int targetOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && control0.hasArray() && control0Offset >= 0 && control0Offset <= control0.limit() - 4 && control1.hasArray() && control1Offset >= 0 && control1Offset <= control1.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            FloatQuatOps.squad(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, control0.array(), control0.arrayOffset() + control0Offset, control1.array(), control1.arrayOffset() + control1Offset, target.array(), target.arrayOffset() + targetOffset, t);
            return dest;
        }
        return squad_api_sd7b8583c_1(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
    }

    /** Piece 2 of {@code squad_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer squad_api_sd7b8583c_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer control0, int control0Offset, java.nio.FloatBuffer control1, int control1Offset, java.nio.FloatBuffer target, int targetOffset, float t) {
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && control0.order() == java.nio.ByteOrder.nativeOrder() && control1.order() == java.nio.ByteOrder.nativeOrder() && target.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.squad_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(control0.duplicate().position(0)), control0Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(control1.duplicate().position(0)), control1Offset * 4L, java.lang.foreign.MemorySegment.ofBuffer(target.duplicate().position(0)), targetOffset * 4L, t);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.squad_apiGet(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer squad_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer control0, int control0Offset, java.nio.FloatBuffer control1, int control1Offset, java.nio.FloatBuffer target, int targetOffset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return squad_apiGet_fma(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
        return squad_apiGet_mulAdd(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
    }

    /** {@code squad_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer control0, int control0Offset, java.nio.FloatBuffer control1, int control1Offset, java.nio.FloatBuffer target, int targetOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _control0x = control0.get(control0Offset);
        float _control0y = control0.get(control0Offset + 1);
        float _control0z = control0.get(control0Offset + 2);
        float _control0w = control0.get(control0Offset + 3);
        float _control1x = control1.get(control1Offset);
        float _control1y = control1.get(control1Offset + 1);
        float _control1z = control1.get(control1Offset + 2);
        float _control1w = control1.get(control1Offset + 3);
        float _targetx = target.get(targetOffset);
        float _targety = target.get(targetOffset + 1);
        float _targetz = target.get(targetOffset + 2);
        float _targetw = target.get(targetOffset + 3);
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t12 = _t0 * _t1;
        return squad_apiGet_fma_sef23f0dd_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, Math.min(t, _t0), _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety, _t12, Math.min(_t12, java.lang.Math.fma(-_t0, _t1, 1.0f)));
    }

    /** Piece 2 of {@code squad_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_fma_sef23f0dd_1(java.nio.FloatBuffer dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t16) {
        float _t23 = Math.min(4.0f, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6))));
        float _t24 = Math.min(4.0f, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11))));
        float _t27 = 4.0f - _t23;
        float _t28 = 4.0f - _t24;
        float _t33 = _t7 * quatArcAngle_fma(_t23);
        float _t34 = _t7 * quatArcAngle_fma(_t24);
        float _t41 = (float) java.lang.Math.sqrt(_t27 * _t23);
        float _t43 = (float) java.lang.Math.sqrt(_t28 * _t24);
        float _t47 = _t23 > _t27 ? _t7 : 0.0f;
        float _t51 = 2.0f / _t41 * Math.sinWithCos(_t33);
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t51;
            _t57 = java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t23, 1.0f), _t51, Math.cosWithSin(_t33));
        } else {
            _t55 = _t47;
            _t57 = 1.0f - _t47;
        }
        return squad_apiGet_fma_sef23f0dd_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t12, _t16, _t24, _t34, _t43, _t24 > _t28 ? _t7 : 0.0f, 2.0f / _t43 * Math.sinWithCos(_t34), _t55, _t57);
    }

    /** Piece 3 of {@code squad_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_fma_sef23f0dd_2(java.nio.FloatBuffer dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t12, float _t16, float _t24, float _t34, float _t43, float _t48, float _t52, float _t55, float _t57) {
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t52;
            _t58 = java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t24, 1.0f), _t52, Math.cosWithSin(_t34));
        } else {
            _t56 = _t48;
            _t58 = 1.0f - _t48;
        }
        float _t59, _t60, _t61, _t62;
        if (t < 0.5f) {
            _t59 = _t57;
            _t60 = _t55;
            _t61 = _t58;
            _t62 = _t56;
        } else {
            _t59 = _t55;
            _t60 = _t57;
            _t61 = _t56;
            _t62 = _t58;
        }
        float _t71 = java.lang.Math.fma(_control0x, _t59, _control1x * _t60);
        float _t72 = java.lang.Math.fma(_control0w, _t59, _control1w * _t60);
        float _t73 = java.lang.Math.fma(_selfw, _t61, _targetw * _t62);
        float _t74 = java.lang.Math.fma(_control0z, _t59, _control1z * _t60);
        float _t75 = java.lang.Math.fma(_selfz, _t61, _targetz * _t62);
        float _t76 = java.lang.Math.fma(_selfx, _t61, _targetx * _t62);
        float _t77 = java.lang.Math.fma(_control0y, _t59, _control1y * _t60);
        float _t78 = java.lang.Math.fma(_selfy, _t61, _targety * _t62);
        float _t79 = _t72 + _t73;
        float _t80 = _t74 + _t75;
        float _t81 = _t71 + _t76;
        float _t82 = _t77 + _t78;
        float _t87 = Math.min(4.0f, java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82))));
        return squad_apiGet_fma_sef23f0dd_3(dest, destOffset, _t12, _t16, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t87, 4.0f - _t87);
    }

    /** Piece 4 of {@code squad_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_fma_sef23f0dd_3(java.nio.FloatBuffer dest, int destOffset, float _t12, float _t16, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t87, float _t89) {
        float _t92 = _t16 * quatArcAngle_fma(_t87);
        float _t96 = (float) java.lang.Math.sqrt(_t89 * _t87);
        float _t99 = _t87 > _t89 ? _t16 : 0.0f;
        float _t101 = 2.0f / _t96 * Math.sinWithCos(_t92);
        float _t103, _t104;
        if (_t96 > 2.0E-6f) {
            _t103 = _t101;
            _t104 = java.lang.Math.fma(java.lang.Math.fma(-0.5f, _t87, 1.0f), _t101, Math.cosWithSin(_t92));
        } else {
            _t103 = _t99;
            _t104 = 1.0f - _t99;
        }
        float _t105, _t106;
        if (_t12 < 0.5f) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        dest.put(destOffset, java.lang.Math.fma(_t71, _t105, _t76 * _t106));
        dest.put(destOffset + 1, java.lang.Math.fma(_t77, _t105, _t78 * _t106));
        dest.put(destOffset + 2, java.lang.Math.fma(_t74, _t105, _t75 * _t106));
        dest.put(destOffset + 3, java.lang.Math.fma(_t72, _t105, _t73 * _t106));
        return dest;
    }

    /** {@code squad_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer control0, int control0Offset, java.nio.FloatBuffer control1, int control1Offset, java.nio.FloatBuffer target, int targetOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _control0x = control0.get(control0Offset);
        float _control0y = control0.get(control0Offset + 1);
        float _control0z = control0.get(control0Offset + 2);
        float _control0w = control0.get(control0Offset + 3);
        float _control1x = control1.get(control1Offset);
        float _control1y = control1.get(control1Offset + 1);
        float _control1z = control1.get(control1Offset + 2);
        float _control1w = control1.get(control1Offset + 3);
        float _targetx = target.get(targetOffset);
        float _targety = target.get(targetOffset + 1);
        float _targetz = target.get(targetOffset + 2);
        float _targetw = target.get(targetOffset + 3);
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t12 = _t0 * _t1;
        return squad_apiGet_mulAdd_s8c1ed218_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, Math.min(t, _t0), _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety, _t12, Math.min(_t12, ((1.0f) - (_t0) * (_t1))));
    }

    /** Piece 2 of {@code squad_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_mulAdd_s8c1ed218_1(java.nio.FloatBuffer dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t16) {
        float _t23 = Math.min(4.0f, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6)))))));
        float _t24 = Math.min(4.0f, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11)))))));
        float _t27 = 4.0f - _t23;
        float _t28 = 4.0f - _t24;
        float _t33 = _t7 * quatArcAngle_mulAdd(_t23);
        float _t34 = _t7 * quatArcAngle_mulAdd(_t24);
        float _t41 = (float) java.lang.Math.sqrt(_t27 * _t23);
        float _t43 = (float) java.lang.Math.sqrt(_t28 * _t24);
        float _t47 = _t23 > _t27 ? _t7 : 0.0f;
        float _t51 = 2.0f / _t41 * Math.sinWithCos(_t33);
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t51;
            _t57 = ((((1.0f) - (0.5f) * (_t23))) * (_t51) + (Math.cosWithSin(_t33)));
        } else {
            _t55 = _t47;
            _t57 = 1.0f - _t47;
        }
        return squad_apiGet_mulAdd_s8c1ed218_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t12, _t16, _t24, _t34, _t43, _t24 > _t28 ? _t7 : 0.0f, 2.0f / _t43 * Math.sinWithCos(_t34), _t55, _t57);
    }

    /** Piece 3 of {@code squad_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_mulAdd_s8c1ed218_2(java.nio.FloatBuffer dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t12, float _t16, float _t24, float _t34, float _t43, float _t48, float _t52, float _t55, float _t57) {
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t52;
            _t58 = ((((1.0f) - (0.5f) * (_t24))) * (_t52) + (Math.cosWithSin(_t34)));
        } else {
            _t56 = _t48;
            _t58 = 1.0f - _t48;
        }
        float _t59, _t60, _t61, _t62;
        if (t < 0.5f) {
            _t59 = _t57;
            _t60 = _t55;
            _t61 = _t58;
            _t62 = _t56;
        } else {
            _t59 = _t55;
            _t60 = _t57;
            _t61 = _t56;
            _t62 = _t58;
        }
        float _t71 = ((_control0x) * (_t59) + (_control1x * _t60));
        float _t72 = ((_control0w) * (_t59) + (_control1w * _t60));
        float _t73 = ((_selfw) * (_t61) + (_targetw * _t62));
        float _t74 = ((_control0z) * (_t59) + (_control1z * _t60));
        float _t75 = ((_selfz) * (_t61) + (_targetz * _t62));
        float _t76 = ((_selfx) * (_t61) + (_targetx * _t62));
        float _t77 = ((_control0y) * (_t59) + (_control1y * _t60));
        float _t78 = ((_selfy) * (_t61) + (_targety * _t62));
        float _t79 = _t72 + _t73;
        float _t80 = _t74 + _t75;
        float _t81 = _t71 + _t76;
        float _t82 = _t77 + _t78;
        float _t87 = Math.min(4.0f, ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82)))))));
        return squad_apiGet_mulAdd_s8c1ed218_3(dest, destOffset, _t12, _t16, _t71, _t72, _t73, _t74, _t75, _t76, _t77, _t78, _t87, 4.0f - _t87);
    }

    /** Piece 4 of {@code squad_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer squad_apiGet_mulAdd_s8c1ed218_3(java.nio.FloatBuffer dest, int destOffset, float _t12, float _t16, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77, float _t78, float _t87, float _t89) {
        float _t92 = _t16 * quatArcAngle_mulAdd(_t87);
        float _t96 = (float) java.lang.Math.sqrt(_t89 * _t87);
        float _t99 = _t87 > _t89 ? _t16 : 0.0f;
        float _t101 = 2.0f / _t96 * Math.sinWithCos(_t92);
        float _t103, _t104;
        if (_t96 > 2.0E-6f) {
            _t103 = _t101;
            _t104 = ((((1.0f) - (0.5f) * (_t87))) * (_t101) + (Math.cosWithSin(_t92)));
        } else {
            _t103 = _t99;
            _t104 = 1.0f - _t99;
        }
        float _t105, _t106;
        if (_t12 < 0.5f) {
            _t105 = _t103;
            _t106 = _t104;
        } else {
            _t105 = _t104;
            _t106 = _t103;
        }
        dest.put(destOffset, ((_t71) * (_t105) + (_t76 * _t106)));
        dest.put(destOffset + 1, ((_t77) * (_t105) + (_t78 * _t106)));
        dest.put(destOffset + 2, ((_t74) * (_t105) + (_t75 * _t106)));
        dest.put(destOffset + 3, ((_t72) * (_t105) + (_t73 * _t106)));
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, otherZ, otherW);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_apiGet_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        dest.put(destOffset + 1, java.lang.Math.fma(otherX, _selfz, otherW * _selfy) + java.lang.Math.fma(otherY, _selfw, otherZ * -_selfx));
        dest.put(destOffset + 2, java.lang.Math.fma(otherY, _selfx, otherZ * _selfw) + java.lang.Math.fma(otherW, _selfz, -(otherX * _selfy)));
        dest.put(destOffset + 3, java.lang.Math.fma(otherW, _selfw, otherX * -_selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    /** {@code mul_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer mul_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherZ) * (_selfy) - (otherY * _selfz)));
        dest.put(destOffset + 1, ((otherX) * (_selfz) + (otherW * _selfy)) + ((otherY) * (_selfw) - (otherZ * _selfx)));
        dest.put(destOffset + 2, ((otherY) * (_selfx) + (otherZ * _selfw)) + ((otherW) * (_selfz) - (otherX * _selfy)));
        dest.put(destOffset + 3, ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz)));
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        FloatQuatOpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            FloatQuatOps.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return mul_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        dest.put(destOffset + 1, java.lang.Math.fma(_otherx, _selfz, _otherw * _selfy) + java.lang.Math.fma(_othery, _selfw, _otherz * -_selfx));
        dest.put(destOffset + 2, java.lang.Math.fma(_othery, _selfx, _otherz * _selfw) + java.lang.Math.fma(_otherw, _selfz, -(_otherx * _selfy)));
        dest.put(destOffset + 3, java.lang.Math.fma(_otherw, _selfw, _otherx * -_selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    /** {@code mul_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer mul_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_otherz) * (_selfy) - (_othery * _selfz)));
        dest.put(destOffset + 1, ((_otherx) * (_selfz) + (_otherw * _selfy)) + ((_othery) * (_selfw) - (_otherz * _selfx)));
        dest.put(destOffset + 2, ((_othery) * (_selfx) + (_otherz * _selfw)) + ((_otherw) * (_selfz) - (_otherx * _selfy)));
        dest.put(destOffset + 3, ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz)));
        return dest;
    }

    public static java.nio.FloatBuffer preMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.preMul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, otherZ, otherW);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_apiGet_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, java.lang.Math.fma(otherX, _selfw, otherW * _selfx) + java.lang.Math.fma(otherY, _selfz, -(otherZ * _selfy)));
        dest.put(destOffset + 1, java.lang.Math.fma(otherY, _selfw, otherZ * _selfx) + java.lang.Math.fma(otherW, _selfy, -otherX * _selfz));
        dest.put(destOffset + 2, java.lang.Math.fma(otherX, _selfy, otherW * _selfz) + java.lang.Math.fma(otherZ, _selfw, -(otherY * _selfx)));
        dest.put(destOffset + 3, java.lang.Math.fma(otherW, _selfw, -otherX * _selfx) - java.lang.Math.fma(otherY, _selfy, otherZ * _selfz));
        return dest;
    }

    /** {@code preMul_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer preMul_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, ((otherX) * (_selfw) + (otherW * _selfx)) + ((otherY) * (_selfz) - (otherZ * _selfy)));
        dest.put(destOffset + 1, ((otherY) * (_selfw) + (otherZ * _selfx)) + ((otherW) * (_selfy) - (otherX * _selfz)));
        dest.put(destOffset + 2, ((otherX) * (_selfy) + (otherW * _selfz)) + ((otherZ) * (_selfw) - (otherY * _selfx)));
        dest.put(destOffset + 3, ((otherW) * (_selfw) - (otherX * _selfx)) - ((otherY) * (_selfy) + (otherZ * _selfz)));
        return dest;
    }

    public static java.nio.FloatBuffer preMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        FloatQuatOpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            FloatQuatOps.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.preMul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return preMul_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, java.lang.Math.fma(_otherx, _selfw, _otherw * _selfx) + java.lang.Math.fma(_othery, _selfz, -(_otherz * _selfy)));
        dest.put(destOffset + 1, java.lang.Math.fma(_othery, _selfw, _otherz * _selfx) + java.lang.Math.fma(_otherw, _selfy, -_otherx * _selfz));
        dest.put(destOffset + 2, java.lang.Math.fma(_otherx, _selfy, _otherw * _selfz) + java.lang.Math.fma(_otherz, _selfw, -(_othery * _selfx)));
        dest.put(destOffset + 3, java.lang.Math.fma(_otherw, _selfw, -_otherx * _selfx) - java.lang.Math.fma(_othery, _selfy, _otherz * _selfz));
        return dest;
    }

    /** {@code preMul_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer preMul_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        dest.put(destOffset, ((_otherx) * (_selfw) + (_otherw * _selfx)) + ((_othery) * (_selfz) - (_otherz * _selfy)));
        dest.put(destOffset + 1, ((_othery) * (_selfw) + (_otherz * _selfx)) + ((_otherw) * (_selfy) - (_otherx * _selfz)));
        dest.put(destOffset + 2, ((_otherx) * (_selfy) + (_otherw * _selfz)) + ((_otherz) * (_selfw) - (_othery * _selfx)));
        dest.put(destOffset + 3, ((_otherw) * (_selfw) - (_otherx * _selfx)) - ((_othery) * (_selfy) + (_otherz * _selfz)));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, weight);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW, weight);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, otherZ, otherW, weight);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, weight);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(weight, otherX, src.get(srcOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(weight, otherY, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(weight, otherZ, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(weight, otherW, _selfw));
            return dest;
        } else {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, ((weight) * (otherX) + (src.get(srcOffset))));
            dest.put(destOffset + 1, ((weight) * (otherY) + (_selfy)));
            dest.put(destOffset + 2, ((weight) * (otherZ) + (_selfz)));
            dest.put(destOffset + 3, ((weight) * (otherW) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        FloatQuatOpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            FloatQuatOps.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, weight);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L, weight);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _othery = other.get(otherOffset + 1);
            float _otherz = other.get(otherOffset + 2);
            float _otherw = other.get(otherOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(weight, other.get(otherOffset), src.get(srcOffset)));
            dest.put(destOffset + 1, java.lang.Math.fma(weight, _othery, _selfy));
            dest.put(destOffset + 2, java.lang.Math.fma(weight, _otherz, _selfz));
            dest.put(destOffset + 3, java.lang.Math.fma(weight, _otherw, _selfw));
            return dest;
        } else {
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _othery = other.get(otherOffset + 1);
            float _otherz = other.get(otherOffset + 2);
            float _otherw = other.get(otherOffset + 3);
            dest.put(destOffset, ((weight) * (other.get(otherOffset)) + (src.get(srcOffset))));
            dest.put(destOffset + 1, ((weight) * (_othery) + (_selfy)));
            dest.put(destOffset + 2, ((weight) * (_otherz) + (_selfz)));
            dest.put(destOffset + 3, ((weight) * (_otherw) + (_selfw)));
            return dest;
        }
    }

    public static float angle_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return FloatQuatOpsKernelsAddress.angle_unsafe(_srcBase);
    }

    public static float angle_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return FloatQuatOps.angle(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return FloatQuatOpsKernelsSegment.angle_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return FloatQuatOpsKernelsTypedBuffer.angle_apiGet(src, srcOffset);
    }

    public static float angle_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            return 2.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))), src.get(srcOffset + 3));
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            return 2.0f * Math.atan2((float) java.lang.Math.sqrt(((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))), src.get(srcOffset + 3));
        }
    }

    public static float angleTo_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return FloatQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleTo_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return FloatQuatOps.angleTo(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return FloatQuatOpsKernelsSegment.angleTo_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, otherZ, otherW);
        }
        return FloatQuatOpsKernelsTypedBuffer.angleTo_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float angleTo_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleTo_apiGet_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(otherW, _selfw, java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy))) > 0.0f) {
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
        float _t13 = _selfw - _t9;
        float _t14 = _selfz - _t10;
        float _t15 = _selfx - _t11;
        float _t16 = _selfy - _t12;
        float _t17 = _selfw + _t9;
        float _t18 = _selfz + _t10;
        float _t19 = _selfx + _t11;
        float _t20 = _selfy + _t12;
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), (float) java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleTo_apiGet_mulAdd(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9, _t10, _t11, _t12;
        if (-((otherW) * (_selfw) + (((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))))) > 0.0f) {
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
        float _t13 = _selfw - _t9;
        float _t14 = _selfz - _t10;
        float _t15 = _selfx - _t11;
        float _t16 = _selfy - _t12;
        float _t17 = _selfw + _t9;
        float _t18 = _selfz + _t10;
        float _t19 = _selfx + _t11;
        float _t20 = _selfy + _t12;
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))))), (float) java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))));
    }

    public static float angleTo_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return FloatQuatOpsKernelsAddress.angleTo_unsafe(_srcBase, _otherBase);
    }

    public static float angleTo_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return FloatQuatOps.angleTo(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return FloatQuatOpsKernelsSegment.angleTo_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
        }
        return FloatQuatOpsKernelsTypedBuffer.angleTo_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float angleTo_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return angleTo_apiGet_mulAdd(src, srcOffset, other, otherOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_otherw, _selfw, java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy))) > 0.0f) {
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
        float _t13 = _selfw - _t9;
        float _t14 = _selfz - _t10;
        float _t15 = _selfx - _t11;
        float _t16 = _selfy - _t12;
        float _t17 = _selfw + _t9;
        float _t18 = _selfz + _t10;
        float _t19 = _selfx + _t11;
        float _t20 = _selfy + _t12;
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), (float) java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleTo_apiGet_mulAdd(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t9, _t10, _t11, _t12;
        if (-((_otherw) * (_selfw) + (((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))))) > 0.0f) {
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
        float _t13 = _selfw - _t9;
        float _t14 = _selfz - _t10;
        float _t15 = _selfx - _t11;
        float _t16 = _selfy - _t12;
        float _t17 = _selfw + _t9;
        float _t18 = _selfz + _t10;
        float _t19 = _selfx + _t11;
        float _t20 = _selfy + _t12;
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))))), (float) java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))));
    }

    public static java.nio.FloatBuffer axis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.axis_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer axis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.axis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.axis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.axis_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer axis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
            float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0f) {
                dest.put(destOffset, _selfx * _t3);
                dest.put(destOffset + 1, _selfy * _t3);
                dest.put(destOffset + 2, _selfz * _t3);
            } else {
                dest.put(destOffset, 0.0f);
                dest.put(destOffset + 1, 0.0f);
                dest.put(destOffset + 2, 0.0f);
            }
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
            float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0f) {
                dest.put(destOffset, _selfx * _t3);
                dest.put(destOffset + 1, _selfy * _t3);
                dest.put(destOffset + 2, _selfz * _t3);
            } else {
                dest.put(destOffset, 0.0f);
                dest.put(destOffset + 1, 0.0f);
                dest.put(destOffset + 2, 0.0f);
            }
            return dest;
        }
    }

    public static java.nio.FloatBuffer calculateW_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.calculateW_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer calculateW_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.calculateW(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.calculateW_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.calculateW_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer calculateW_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, _selfx);
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, _selfz);
            dest.put(destOffset + 3, (float) java.lang.Math.sqrt(Math.max(0.0f, java.lang.Math.fma(-_selfx, _selfx, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(-_selfz, _selfz, 1.0f))))));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            dest.put(destOffset, _selfx);
            dest.put(destOffset + 1, _selfy);
            dest.put(destOffset + 2, _selfz);
            dest.put(destOffset + 3, (float) java.lang.Math.sqrt(Math.max(0.0f, ((((((1.0f) - (_selfz) * (_selfz))) - (_selfy) * (_selfy))) - (_selfx) * (_selfx)))));
            return dest;
        }
    }

    public static java.nio.FloatBuffer conjugate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.conjugate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer conjugate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.conjugate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.conjugate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.conjugate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer conjugate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        dest.put(destOffset, -src.get(srcOffset));
        dest.put(destOffset + 1, -_selfy);
        dest.put(destOffset + 2, -_selfz);
        dest.put(destOffset + 3, _selfw);
        return dest;
    }

    public static java.nio.FloatBuffer conjugateBy_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.conjugateBy_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.FloatBuffer conjugateBy_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.conjugateBy(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, qX, qY, qZ, qW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.conjugateBy_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, qX, qY, qZ, qW);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.conjugateBy_apiGet(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.FloatBuffer conjugateBy_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return conjugateBy_apiGet_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t20 = java.lang.Math.fma(qX, _selfy, qW * _selfz) + java.lang.Math.fma(qZ, _selfw, qY * -_selfx);
        float _t21 = java.lang.Math.fma(qY, _selfw, qZ * _selfx) + java.lang.Math.fma(qW, _selfy, -(qX * _selfz));
        float _t22 = java.lang.Math.fma(qX, _selfw, qW * _selfx) + java.lang.Math.fma(qY, _selfz, -(qZ * _selfy));
        float _t23 = java.lang.Math.fma(qW, _selfw, qX * -_selfx) - java.lang.Math.fma(qY, _selfy, qZ * _selfz);
        dest.put(destOffset, java.lang.Math.fma(qY, _t20, -qZ * _t21) + java.lang.Math.fma(qW, _t22, -qX * _t23));
        dest.put(destOffset + 1, java.lang.Math.fma(qZ, _t22, -qY * _t23) + java.lang.Math.fma(qW, _t21, -qX * _t20));
        dest.put(destOffset + 2, java.lang.Math.fma(qX, _t21, qW * _t20) + java.lang.Math.fma(-qY, _t22, -qZ * _t23));
        dest.put(destOffset + 3, java.lang.Math.fma(qX, _t22, qW * _t23) - java.lang.Math.fma(-qZ, _t20, -qY * _t21));
        return dest;
    }

    /** {@code conjugateBy_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer conjugateBy_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t20 = ((qX) * (_selfy) + (qW * _selfz)) + ((qZ) * (_selfw) - (qY * _selfx));
        float _t21 = ((qY) * (_selfw) + (qZ * _selfx)) + ((qW) * (_selfy) - (qX * _selfz));
        float _t22 = ((qX) * (_selfw) + (qW * _selfx)) + ((qY) * (_selfz) - (qZ * _selfy));
        float _t23 = ((qW) * (_selfw) - (qX * _selfx)) - ((qY) * (_selfy) + (qZ * _selfz));
        dest.put(destOffset, ((qY) * (_t20) - (qZ * _t21)) + ((qW) * (_t22) - (qX * _t23)));
        dest.put(destOffset + 1, ((qZ) * (_t22) - (qY * _t23)) + ((qW) * (_t21) - (qX * _t20)));
        dest.put(destOffset + 2, ((qX) * (_t21) + (qW * _t20)) + ((-qY) * (_t22) - (qZ * _t23)));
        dest.put(destOffset + 3, ((qX) * (_t22) + (qW * _t23)) - ((-qZ) * (_t20) - (qY * _t21)));
        return dest;
    }

    public static java.nio.FloatBuffer conjugateBy_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset * 4L;
        FloatQuatOpsKernelsAddress.conjugateBy_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.nio.FloatBuffer conjugateBy_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer q, int qOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && q.hasArray() && qOffset >= 0 && qOffset <= q.limit() - 4) {
            FloatQuatOps.conjugateBy(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, q.array(), q.arrayOffset() + qOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && q.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.conjugateBy_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(q.duplicate().position(0)), qOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.conjugateBy_apiGet(dest, destOffset, src, srcOffset, q, qOffset);
        return dest;
    }

    public static java.nio.FloatBuffer conjugateBy_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer q, int qOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return conjugateBy_apiGet_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return conjugateBy_apiGet_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    /** {@code conjugateBy_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer conjugateBy_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer q, int qOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _qx = q.get(qOffset);
        float _qy = q.get(qOffset + 1);
        float _qz = q.get(qOffset + 2);
        float _qw = q.get(qOffset + 3);
        float _t20 = java.lang.Math.fma(_qx, _selfy, _qw * _selfz) + java.lang.Math.fma(_qz, _selfw, _qy * -_selfx);
        float _t21 = java.lang.Math.fma(_qy, _selfw, _qz * _selfx) + java.lang.Math.fma(_qw, _selfy, -(_qx * _selfz));
        float _t22 = java.lang.Math.fma(_qx, _selfw, _qw * _selfx) + java.lang.Math.fma(_qy, _selfz, -(_qz * _selfy));
        float _t23 = java.lang.Math.fma(_qw, _selfw, _qx * -_selfx) - java.lang.Math.fma(_qy, _selfy, _qz * _selfz);
        dest.put(destOffset, java.lang.Math.fma(_qy, _t20, -_qz * _t21) + java.lang.Math.fma(_qw, _t22, -_qx * _t23));
        dest.put(destOffset + 1, java.lang.Math.fma(_qz, _t22, -_qy * _t23) + java.lang.Math.fma(_qw, _t21, -_qx * _t20));
        return conjugateBy_apiGet_fma_s5c5a9872_1(dest, destOffset, _qx, _qy, _qz, _qw, _t20, _t21, _t22, _t23);
    }

    /** Piece 2 of {@code conjugateBy_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer conjugateBy_apiGet_fma_s5c5a9872_1(java.nio.FloatBuffer dest, int destOffset, float _qx, float _qy, float _qz, float _qw, float _t20, float _t21, float _t22, float _t23) {
        dest.put(destOffset + 2, java.lang.Math.fma(_qx, _t21, _qw * _t20) + java.lang.Math.fma(-_qy, _t22, -_qz * _t23));
        dest.put(destOffset + 3, java.lang.Math.fma(_qx, _t22, _qw * _t23) - java.lang.Math.fma(-_qz, _t20, -_qy * _t21));
        return dest;
    }

    /** {@code conjugateBy_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer conjugateBy_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer q, int qOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _qx = q.get(qOffset);
        float _qy = q.get(qOffset + 1);
        float _qz = q.get(qOffset + 2);
        float _qw = q.get(qOffset + 3);
        float _t20 = ((_qx) * (_selfy) + (_qw * _selfz)) + ((_qz) * (_selfw) - (_qy * _selfx));
        float _t21 = ((_qy) * (_selfw) + (_qz * _selfx)) + ((_qw) * (_selfy) - (_qx * _selfz));
        float _t22 = ((_qx) * (_selfw) + (_qw * _selfx)) + ((_qy) * (_selfz) - (_qz * _selfy));
        float _t23 = ((_qw) * (_selfw) - (_qx * _selfx)) - ((_qy) * (_selfy) + (_qz * _selfz));
        dest.put(destOffset, ((_qy) * (_t20) - (_qz * _t21)) + ((_qw) * (_t22) - (_qx * _t23)));
        dest.put(destOffset + 1, ((_qz) * (_t22) - (_qy * _t23)) + ((_qw) * (_t21) - (_qx * _t20)));
        dest.put(destOffset + 2, ((_qx) * (_t21) + (_qw * _t20)) + ((-_qy) * (_t22) - (_qz * _t23)));
        dest.put(destOffset + 3, ((_qx) * (_t22) + (_qw * _t23)) - ((-_qz) * (_t20) - (_qy * _t21)));
        return dest;
    }

    public static java.nio.FloatBuffer difference_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer difference_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.difference(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.difference_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, otherZ, otherW);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.difference_apiGet(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.FloatBuffer difference_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return difference_apiGet_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t3_inv = 1.0f / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.put(destOffset, (java.lang.Math.fma(otherX, _selfw, -otherW * _selfx) + java.lang.Math.fma(otherY, _selfz, -(otherZ * _selfy))) * _t3_inv);
        dest.put(destOffset + 1, -otherW * _sp0 - otherX * _sp1 + java.lang.Math.fma(otherY, _selfw, otherZ * _selfx) * _t3_inv);
        dest.put(destOffset + 2, (java.lang.Math.fma(otherX, _selfy, -otherW * _selfz) + java.lang.Math.fma(otherZ, _selfw, -otherY * _selfx)) * _t3_inv);
        dest.put(destOffset + 3, java.lang.Math.fma(otherX, _selfx, otherW * _selfw) * _t3_inv - (-otherY * _sp0 - otherZ * _sp1));
        return dest;
    }

    /** {@code difference_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer difference_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t3_inv = 1.0f / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.put(destOffset, (((otherX) * (_selfw) - (otherW * _selfx)) + ((otherY) * (_selfz) - (otherZ * _selfy))) * _t3_inv);
        dest.put(destOffset + 1, -otherW * _sp0 - otherX * _sp1 + ((otherY) * (_selfw) + (otherZ * _selfx)) * _t3_inv);
        dest.put(destOffset + 2, (((otherX) * (_selfy) - (otherW * _selfz)) + ((otherZ) * (_selfw) - (otherY * _selfx))) * _t3_inv);
        dest.put(destOffset + 3, ((otherX) * (_selfx) + (otherW * _selfw)) * _t3_inv - (-otherY * _sp0 - otherZ * _sp1));
        return dest;
    }

    public static java.nio.FloatBuffer difference_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        FloatQuatOpsKernelsAddress.difference_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer difference_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            FloatQuatOps.difference(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.difference_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.difference_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer difference_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return difference_apiGet_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t3_inv = 1.0f / java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.put(destOffset, (java.lang.Math.fma(_otherx, _selfw, -_otherw * _selfx) + java.lang.Math.fma(_othery, _selfz, -(_otherz * _selfy))) * _t3_inv);
        dest.put(destOffset + 1, -_otherw * _sp0 - _otherx * _sp1 + java.lang.Math.fma(_othery, _selfw, _otherz * _selfx) * _t3_inv);
        dest.put(destOffset + 2, (java.lang.Math.fma(_otherx, _selfy, -_otherw * _selfz) + java.lang.Math.fma(_otherz, _selfw, -_othery * _selfx)) * _t3_inv);
        dest.put(destOffset + 3, java.lang.Math.fma(_otherx, _selfx, _otherw * _selfw) * _t3_inv - (-_othery * _sp0 - _otherz * _sp1));
        return dest;
    }

    /** {@code difference_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer difference_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _otherx = other.get(otherOffset);
        float _othery = other.get(otherOffset + 1);
        float _otherz = other.get(otherOffset + 2);
        float _otherw = other.get(otherOffset + 3);
        float _t3_inv = 1.0f / ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest.put(destOffset, (((_otherx) * (_selfw) - (_otherw * _selfx)) + ((_othery) * (_selfz) - (_otherz * _selfy))) * _t3_inv);
        dest.put(destOffset + 1, -_otherw * _sp0 - _otherx * _sp1 + ((_othery) * (_selfw) + (_otherz * _selfx)) * _t3_inv);
        dest.put(destOffset + 2, (((_otherx) * (_selfy) - (_otherw * _selfz)) + ((_otherz) * (_selfw) - (_othery * _selfx))) * _t3_inv);
        dest.put(destOffset + 3, ((_otherx) * (_selfx) + (_otherw * _selfw)) * _t3_inv - (-_othery * _sp0 - _otherz * _sp1));
        return dest;
    }

    public static float dot_unsafe(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return FloatQuatOpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float dot_api(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return FloatQuatOps.dot(src.array(), src.arrayOffset() + srcOffset, otherX, otherY, otherZ, otherW);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return FloatQuatOpsKernelsSegment.dot_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, otherX, otherY, otherZ, otherW);
        }
        return FloatQuatOpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float dot_apiGet(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherW, src.get(srcOffset + 3), java.lang.Math.fma(otherZ, src.get(srcOffset + 2), java.lang.Math.fma(otherX, src.get(srcOffset), otherY * src.get(srcOffset + 1))));
        } else {
            return ((otherW) * (src.get(srcOffset + 3)) + (((otherZ) * (src.get(srcOffset + 2)) + (((otherX) * (src.get(srcOffset)) + (otherY * src.get(srcOffset + 1)))))));
        }
    }

    public static float dot_unsafe(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        return FloatQuatOpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            return FloatQuatOps.dot(src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            return FloatQuatOpsKernelsSegment.dot_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
        }
        return FloatQuatOpsKernelsTypedBuffer.dot_apiGet(src, srcOffset, other, otherOffset);
    }

    public static float dot_apiGet(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(other.get(otherOffset + 3), src.get(srcOffset + 3), java.lang.Math.fma(other.get(otherOffset + 2), src.get(srcOffset + 2), java.lang.Math.fma(other.get(otherOffset), src.get(srcOffset), other.get(otherOffset + 1) * src.get(srcOffset + 1))));
        } else {
            return ((other.get(otherOffset + 3)) * (src.get(srcOffset + 3)) + (((other.get(otherOffset + 2)) * (src.get(srcOffset + 2)) + (((other.get(otherOffset)) * (src.get(srcOffset)) + (other.get(otherOffset + 1) * src.get(srcOffset + 1)))))));
        }
    }

    public static java.nio.FloatBuffer exp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer exp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.exp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.exp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.exp_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer exp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return exp_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t2 = Math.min(Math.exp(src.get(srcOffset + 3)), 3.4028235E38f);
        float _t4 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        float _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
        if (_t4 > 0.0f) {
            dest.put(destOffset, _selfx * _t10);
            dest.put(destOffset + 1, _selfy * _t10);
            dest.put(destOffset + 2, _selfz * _t10);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        dest.put(destOffset + 3, Math.cosWithSin(_t5) * _t2);
        return dest;
    }

    /** {@code exp_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer exp_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _t2 = Math.min(Math.exp(src.get(srcOffset + 3)), 3.4028235E38f);
        float _t4 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        float _t10 = _t2 * (Math.sinWithCos(_t5) / _t5);
        if (_t4 > 0.0f) {
            dest.put(destOffset, _selfx * _t10);
            dest.put(destOffset + 1, _selfy * _t10);
            dest.put(destOffset + 2, _selfz * _t10);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        dest.put(destOffset + 3, Math.cosWithSin(_t5) * _t2);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXYZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getEulerAnglesXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getEulerAnglesXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getEulerAnglesXYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dest.put(destOffset, Math.atan2(_t11, _t14));
            dest.put(destOffset + 2, 0.0f);
        } else {
            dest.put(destOffset, Math.atan2(_t12, _t13));
            dest.put(destOffset + 2, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
        dest.put(destOffset + 1, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesXYZ_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t10 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t11 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t12 = 2.0f * ((_selfx) * (_selfw) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t14 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dest.put(destOffset, Math.atan2(_t11, _t14));
            dest.put(destOffset + 2, 0.0f);
        } else {
            dest.put(destOffset, Math.atan2(_t12, _t13));
            dest.put(destOffset + 2, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) + (2.0f * ((_selfx) * (_selfz) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
        dest.put(destOffset + 1, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        FloatQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXYZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getEulerAnglesXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getEulerAnglesXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getEulerAnglesXYZ_apiGet(dest, destOffset, src, srcOffset, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXYZ_apiGet_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1);
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dest.put(destOffset, Math.atan2(_t11, _t14));
            dest.put(destOffset + 2, 0.0f);
        } else {
            dest.put(destOffset, Math.atan2(_t12, _t13));
            dest.put(destOffset + 2, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), 2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
        }
        dest.put(destOffset + 1, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesXYZ_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesXYZ_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t10 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t11 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t12 = 2.0f * ((_selfx) * (_selfw) - (_t1));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t14 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dest.put(destOffset, Math.atan2(_t11, _t14));
            dest.put(destOffset + 2, 0.0f);
        } else {
            dest.put(destOffset, Math.atan2(_t12, _t13));
            dest.put(destOffset + 2, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) + (2.0f * ((_selfx) * (_selfz) - (_t2)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
        }
        dest.put(destOffset + 1, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXZY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXZY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getEulerAnglesXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getEulerAnglesXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getEulerAnglesXZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXZY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t10 = 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dest.put(destOffset, Math.atan2(2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1), _t12));
            dest.put(destOffset + 1, 0.0f);
        } else {
            dest.put(destOffset, Math.atan2(_t9, _t11));
            dest.put(destOffset + 1, Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t9, _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * -_selfw)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(_selfy, _selfz, _selfx * -_selfw))));
        }
        dest.put(destOffset + 2, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesXZY_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t10 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        float _t12 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dest.put(destOffset, Math.atan2(2.0f * ((_selfx) * (_selfw) - (_t1)), _t12));
            dest.put(destOffset + 1, 0.0f);
        } else {
            dest.put(destOffset, Math.atan2(_t9, _t11));
            dest.put(destOffset + 1, Math.atan2(((2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) * (_t9) - (_t11 * 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)))), ((_t12) * (_t11) - (_t9 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw))))));
        }
        dest.put(destOffset + 2, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXZY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        FloatQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXZY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getEulerAnglesXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getEulerAnglesXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getEulerAnglesXZY_apiGet(dest, destOffset, src, srcOffset, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesXZY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesXZY_apiGet_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _t1);
        float _t10 = 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dest.put(destOffset, Math.atan2(2.0f * java.lang.Math.fma(_selfx, _selfw, -_t1), _t12));
            dest.put(destOffset + 1, 0.0f);
        } else {
            dest.put(destOffset, Math.atan2(_t9, _t11));
            dest.put(destOffset + 1, Math.atan2(java.lang.Math.fma(2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw), _t9, _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * -_selfw)), java.lang.Math.fma(_t12, _t11, _t9 * -2.0f * java.lang.Math.fma(_selfy, _selfz, _selfx * -_selfw))));
        }
        dest.put(destOffset + 2, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesXZY_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesXZY_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfy * _selfz;
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_t1));
        float _t10 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))));
        float _t12 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dest.put(destOffset, Math.atan2(2.0f * ((_selfx) * (_selfw) - (_t1)), _t12));
            dest.put(destOffset + 1, 0.0f);
        } else {
            dest.put(destOffset, Math.atan2(_t9, _t11));
            dest.put(destOffset + 1, Math.atan2(((2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw))) * (_t9) - (_t11 * 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)))), ((_t12) * (_t11) - (_t9 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw))))));
        }
        dest.put(destOffset + 2, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYXZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getEulerAnglesYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getEulerAnglesYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getEulerAnglesYXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        return getEulerAnglesYXZ_apiGet_se920f8e8_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t0, _t2, _t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t0), _t11, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f), java.lang.Math.fma(_t11, _t11, _t9 * _t9));
    }

    /** Piece 2 of {@code getEulerAnglesYXZ_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesYXZ_apiGet_se920f8e8_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t2, float _t9, float _t10, float _t11, float _t12, float _t14) {
        if (_t14 <= java.lang.Math.fma(_t10, _t10, _t14) * 2.2737368E-13f) {
            dest.put(destOffset + 1, Math.atan2(2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t12));
            dest.put(destOffset + 2, 0.0f);
        } else {
            dest.put(destOffset + 1, Math.atan2(_t9, _t11));
            dest.put(destOffset + 2, Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, _t0), _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2))));
        }
        dest.put(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesYXZ_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t10 = 2.0f * ((_selfx) * (_selfw) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t12 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= ((_t10) * (_t10) + (_t14)) * 2.2737368E-13f) {
            dest.put(destOffset + 1, Math.atan2(2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t12));
            dest.put(destOffset + 2, 0.0f);
        } else {
            dest.put(destOffset + 1, Math.atan2(_t9, _t11));
            dest.put(destOffset + 2, Math.atan2(((_t9) * (2.0f * ((_selfx) * (_selfw) + (_t0))) - (_t11 * 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)))), ((_t11) * (_t12) - (_t9 * 2.0f * ((_selfx) * (_selfz) - (_t2))))));
        }
        dest.put(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        FloatQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYXZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getEulerAnglesYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getEulerAnglesYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getEulerAnglesYXZ_apiGet(dest, destOffset, src, srcOffset, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYXZ_apiGet_fma(dest, destOffset, src, srcOffset, epsilon);
        return getEulerAnglesYXZ_apiGet_mulAdd(dest, destOffset, src, srcOffset, epsilon);
    }

    /** {@code getEulerAnglesYXZ_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesYXZ_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _t2);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfw, -_t0);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t12 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        float _t14 = java.lang.Math.fma(_t11, _t11, _t9 * _t9);
        if (_t14 <= epsilon * java.lang.Math.fma(_t10, _t10, _t14)) {
            dest.put(destOffset + 1, Math.atan2(2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz)), _t12));
            dest.put(destOffset + 2, 0.0f);
        } else {
            dest.put(destOffset + 1, Math.atan2(_t9, _t11));
            dest.put(destOffset + 2, Math.atan2(java.lang.Math.fma(_t9, 2.0f * java.lang.Math.fma(_selfx, _selfw, _t0), _t11 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw))), java.lang.Math.fma(_t11, _t12, _t9 * -2.0f * java.lang.Math.fma(_selfx, _selfz, -_t2))));
        }
        dest.put(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    /** {@code getEulerAnglesYXZ_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesYXZ_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfy * _selfz;
        float _t2 = _selfy * _selfw;
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_t2));
        float _t10 = 2.0f * ((_selfx) * (_selfw) - (_t0));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t12 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        float _t14 = ((_t11) * (_t11) + (_t9 * _t9));
        if (_t14 <= epsilon * ((_t10) * (_t10) + (_t14))) {
            dest.put(destOffset + 1, Math.atan2(2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz)), _t12));
            dest.put(destOffset + 2, 0.0f);
        } else {
            dest.put(destOffset + 1, Math.atan2(_t9, _t11));
            dest.put(destOffset + 2, Math.atan2(((_t9) * (2.0f * ((_selfx) * (_selfw) + (_t0))) - (_t11 * 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)))), ((_t11) * (_t12) - (_t9 * 2.0f * ((_selfx) * (_selfz) - (_t2))))));
        }
        dest.put(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t14)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYZX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYZX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getEulerAnglesYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getEulerAnglesYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getEulerAnglesYZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYZX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = _selfz * _selfw;
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t2);
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t13 = 2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= java.lang.Math.fma(_t11, _t11, _t17) * 2.2737368E-13f) {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, Math.atan2(_t12, _t15));
        } else {
            dest.put(destOffset, Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)));
            dest.put(destOffset + 1, Math.atan2(_t13, _t14));
        }
        dest.put(destOffset + 2, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesYZX_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = _selfz * _selfw;
        float _t11 = 2.0f * ((_selfx) * (_selfy) + (_t2));
        float _t12 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t13 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        float _t15 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= ((_t11) * (_t11) + (_t17)) * 2.2737368E-13f) {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, Math.atan2(_t12, _t15));
        } else {
            dest.put(destOffset, Math.atan2(((_t14) * (2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz))) + (2.0f * ((_selfx) * (_selfy) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))));
            dest.put(destOffset + 1, Math.atan2(_t13, _t14));
        }
        dest.put(destOffset + 2, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYZX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        FloatQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYZX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getEulerAnglesYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getEulerAnglesYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getEulerAnglesYZX_apiGet(dest, destOffset, src, srcOffset, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesYZX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesYZX_apiGet_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = _selfz * _selfw;
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t2);
        float _t12 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t13 = 2.0f * java.lang.Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        float _t15 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t17 = java.lang.Math.fma(_t14, _t14, _t13 * _t13);
        if (_t17 <= epsilon * java.lang.Math.fma(_t11, _t11, _t17)) {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, Math.atan2(_t12, _t15));
        } else {
            dest.put(destOffset, Math.atan2(java.lang.Math.fma(_t14, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz), 2.0f * java.lang.Math.fma(_selfx, _selfy, -_t2) * _t13), java.lang.Math.fma(_t15, _t14, _t12 * _t13)));
            dest.put(destOffset + 1, Math.atan2(_t13, _t14));
        }
        dest.put(destOffset + 2, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    /** {@code getEulerAnglesYZX_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesYZX_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = _selfz * _selfw;
        float _t11 = 2.0f * ((_selfx) * (_selfy) + (_t2));
        float _t12 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t13 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))));
        float _t15 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t17 = ((_t14) * (_t14) + (_t13 * _t13));
        if (_t17 <= epsilon * ((_t11) * (_t11) + (_t17))) {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, Math.atan2(_t12, _t15));
        } else {
            dest.put(destOffset, Math.atan2(((_t14) * (2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz))) + (2.0f * ((_selfx) * (_selfy) - (_t2)) * _t13)), ((_t15) * (_t14) + (_t12 * _t13))));
            dest.put(destOffset + 1, Math.atan2(_t13, _t14));
        }
        dest.put(destOffset + 2, Math.atan2(_t11, (float) java.lang.Math.sqrt(_t17)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZXY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZXY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getEulerAnglesZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getEulerAnglesZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getEulerAnglesZXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZXY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfz * _selfz;
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t12 = 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= java.lang.Math.fma(_t10, _t10, _t16) * 2.2737368E-13f) {
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, Math.atan2(_t11, _t14));
        } else {
            dest.put(destOffset + 1, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
            dest.put(destOffset + 2, Math.atan2(_t12, _t13));
        }
        dest.put(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesZXY_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfz * _selfz;
        float _t10 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t11 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t12 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= ((_t10) * (_t10) + (_t16)) * 2.2737368E-13f) {
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, Math.atan2(_t11, _t14));
        } else {
            dest.put(destOffset + 1, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) + (2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
            dest.put(destOffset + 2, Math.atan2(_t12, _t13));
        }
        dest.put(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZXY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        FloatQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZXY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getEulerAnglesZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getEulerAnglesZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getEulerAnglesZXY_apiGet(dest, destOffset, src, srcOffset, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZXY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZXY_apiGet_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfz * _selfz;
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t11 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t12 = 2.0f * java.lang.Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t13 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t1), 1.0f);
        float _t14 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t1), 1.0f);
        float _t16 = java.lang.Math.fma(_t13, _t13, _t12 * _t12);
        if (_t16 <= epsilon * java.lang.Math.fma(_t10, _t10, _t16)) {
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, Math.atan2(_t11, _t14));
        } else {
            dest.put(destOffset + 1, Math.atan2(java.lang.Math.fma(_t13, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)) * _t12), java.lang.Math.fma(_t13, _t14, _t11 * _t12)));
            dest.put(destOffset + 2, Math.atan2(_t12, _t13));
        }
        dest.put(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    /** {@code getEulerAnglesZXY_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesZXY_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t1 = _selfz * _selfz;
        float _t10 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t11 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t12 = 2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy));
        float _t13 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t1))));
        float _t14 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t1))));
        float _t16 = ((_t13) * (_t13) + (_t12 * _t12));
        if (_t16 <= epsilon * ((_t10) * (_t10) + (_t16))) {
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, Math.atan2(_t11, _t14));
        } else {
            dest.put(destOffset + 1, Math.atan2(((_t13) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) + (2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)) * _t12)), ((_t13) * (_t14) + (_t11 * _t12))));
            dest.put(destOffset + 2, Math.atan2(_t12, _t13));
        }
        dest.put(destOffset, Math.atan2(_t10, (float) java.lang.Math.sqrt(_t16)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZYX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        FloatQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZYX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getEulerAnglesZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getEulerAnglesZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getEulerAnglesZYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZYX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1);
        float _t9 = 2.0f * java.lang.Math.fma(_selfy, _selfw, -_selfx * _selfz);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f);
        float _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= java.lang.Math.fma(_t9, _t9, _t13) * 2.2737368E-13f) {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 2, Math.atan2(2.0f * java.lang.Math.fma(_selfz, _selfw, -_selfx * _selfy), _t11));
        } else {
            dest.put(destOffset, Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t10 * -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1))));
            dest.put(destOffset + 2, Math.atan2(_t8, _t10));
        }
        dest.put(destOffset + 1, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesZYX_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * ((_selfx) * (_selfy) + (_t1));
        float _t9 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t10 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= ((_t9) * (_t9) + (_t13)) * 2.2737368E-13f) {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 2, Math.atan2(2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11));
        } else {
            dest.put(destOffset, Math.atan2(((_t8) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) - (_t10 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)))), ((_t11) * (_t10) - (_t8 * 2.0f * ((_selfx) * (_selfy) - (_t1))))));
            dest.put(destOffset + 2, Math.atan2(_t8, _t10));
        }
        dest.put(destOffset + 1, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZYX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        FloatQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZYX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.getEulerAnglesZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.getEulerAnglesZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.getEulerAnglesZYX_apiGet(dest, destOffset, src, srcOffset, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer getEulerAnglesZYX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return getEulerAnglesZYX_apiGet_mulAdd(dest, destOffset, src, srcOffset, epsilon);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _t1);
        float _t9 = 2.0f * java.lang.Math.fma(_selfy, _selfw, -_selfx * _selfz);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _t0), 1.0f);
        float _t13 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        if (_t13 <= epsilon * java.lang.Math.fma(_t9, _t9, _t13)) {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 2, Math.atan2(2.0f * java.lang.Math.fma(_selfz, _selfw, -_selfx * _selfy), _t11));
        } else {
            dest.put(destOffset, Math.atan2(java.lang.Math.fma(_t8, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw), _t10 * -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw))), java.lang.Math.fma(_t11, _t10, _t8 * -2.0f * java.lang.Math.fma(_selfx, _selfy, -_t1))));
            dest.put(destOffset + 2, Math.atan2(_t8, _t10));
        }
        dest.put(destOffset + 1, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    /** {@code getEulerAnglesZYX_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer getEulerAnglesZYX_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t8 = 2.0f * ((_selfx) * (_selfy) + (_t1));
        float _t9 = 2.0f * ((_selfy) * (_selfw) - (_selfx * _selfz));
        float _t10 = ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_t0))));
        float _t11 = ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_t0))));
        float _t13 = ((_t10) * (_t10) + (_t8 * _t8));
        if (_t13 <= epsilon * ((_t9) * (_t9) + (_t13))) {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 2, Math.atan2(2.0f * ((_selfz) * (_selfw) - (_selfx * _selfy)), _t11));
        } else {
            dest.put(destOffset, Math.atan2(((_t8) * (2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw))) - (_t10 * 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)))), ((_t11) * (_t10) - (_t8 * 2.0f * ((_selfx) * (_selfy) - (_t1))))));
            dest.put(destOffset + 2, Math.atan2(_t8, _t10));
        }
        dest.put(destOffset + 1, Math.atan2(_t9, (float) java.lang.Math.sqrt(_t13)));
        return dest;
    }

    public static java.nio.FloatBuffer integrate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, angularVelX, angularVelY, angularVelZ, dt);
        return dest;
    }

    public static java.nio.FloatBuffer integrate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.integrate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angularVelX, angularVelY, angularVelZ, dt);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.integrate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angularVelX, angularVelY, angularVelZ, dt);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.integrate_apiGet(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
        return dest;
    }

    public static java.nio.FloatBuffer integrate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return integrate_apiGet_mulAdd(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.cosWithSin(_t7);
        float _t11 = Math.sinWithCos(_t7) / _t7;
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t15, _t16, _t17;
        if (_t6 > 0.0f) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t9, _selfw * _t15) + java.lang.Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfx, _t17, _selfw * _t16) + java.lang.Math.fma(_selfy, _t9, -(_selfz * _t15)));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfy, _t15, _selfz * _t9) + java.lang.Math.fma(_selfw, _t17, -_selfx * _t16));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t9, -_selfx * _t15) - java.lang.Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    /** {@code integrate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer integrate_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.cosWithSin(_t7);
        float _t11 = Math.sinWithCos(_t7) / _t7;
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t15, _t16, _t17;
        if (_t6 > 0.0f) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        dest.put(destOffset, ((_selfx) * (_t9) + (_selfw * _t15)) + ((_selfz) * (_t16) - (_selfy * _t17)));
        dest.put(destOffset + 1, ((_selfx) * (_t17) + (_selfw * _t16)) + ((_selfy) * (_t9) - (_selfz * _t15)));
        dest.put(destOffset + 2, ((_selfy) * (_t15) + (_selfz * _t9)) + ((_selfw) * (_t17) - (_selfx * _t16)));
        dest.put(destOffset + 3, ((_selfw) * (_t9) - (_selfx * _t15)) - ((_selfy) * (_t16) + (_selfz * _t17)));
        return dest;
    }

    public static java.nio.FloatBuffer integrate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer angularVel, int angularVelOffset, float dt) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _angularVelBase = UnsafeOpsHolder.U.getLong(angularVel, UnsafeCopy.BB_ADDRESS_OFFSET) + angularVelOffset * 4L;
        FloatQuatOpsKernelsAddress.integrate_unsafe(_destBase, _srcBase, _angularVelBase, dt);
        return dest;
    }

    public static java.nio.FloatBuffer integrate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer angularVel, int angularVelOffset, float dt) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && angularVel.hasArray() && angularVelOffset >= 0 && angularVelOffset <= angularVel.limit() - 3) {
            FloatQuatOps.integrate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angularVel.array(), angularVel.arrayOffset() + angularVelOffset, dt);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && angularVel.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.integrate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(angularVel.duplicate().position(0)), angularVelOffset * 4L, dt);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.integrate_apiGet(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
        return dest;
    }

    public static java.nio.FloatBuffer integrate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer angularVel, int angularVelOffset, float dt) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return integrate_apiGet_fma(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
        return integrate_apiGet_mulAdd(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
    }

    /** {@code integrate_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer integrate_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer angularVel, int angularVelOffset, float dt) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = 0.5f * dt;
        float _t1 = angularVel.get(angularVelOffset + 2) * _t0;
        float _t2 = angularVel.get(angularVelOffset) * _t0;
        float _t3 = angularVel.get(angularVelOffset + 1) * _t0;
        float _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.cosWithSin(_t7);
        float _t11 = Math.sinWithCos(_t7) / _t7;
        float _t15, _t16, _t17;
        if (_t6 > 0.0f) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t9, _selfw * _t15) + java.lang.Math.fma(_selfz, _t16, -(_selfy * _t17)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfx, _t17, _selfw * _t16) + java.lang.Math.fma(_selfy, _t9, -(_selfz * _t15)));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfy, _t15, _selfz * _t9) + java.lang.Math.fma(_selfw, _t17, -_selfx * _t16));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t9, -_selfx * _t15) - java.lang.Math.fma(_selfy, _t16, _selfz * _t17));
        return dest;
    }

    /** {@code integrate_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer integrate_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer angularVel, int angularVelOffset, float dt) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = 0.5f * dt;
        float _t1 = angularVel.get(angularVelOffset + 2) * _t0;
        float _t2 = angularVel.get(angularVelOffset) * _t0;
        float _t3 = angularVel.get(angularVelOffset + 1) * _t0;
        float _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.cosWithSin(_t7);
        float _t11 = Math.sinWithCos(_t7) / _t7;
        float _t15, _t16, _t17;
        if (_t6 > 0.0f) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        dest.put(destOffset, ((_selfx) * (_t9) + (_selfw * _t15)) + ((_selfz) * (_t16) - (_selfy * _t17)));
        dest.put(destOffset + 1, ((_selfx) * (_t17) + (_selfw * _t16)) + ((_selfy) * (_t9) - (_selfz * _t15)));
        dest.put(destOffset + 2, ((_selfy) * (_t15) + (_selfz * _t9)) + ((_selfw) * (_t17) - (_selfx * _t16)));
        dest.put(destOffset + 3, ((_selfw) * (_t9) - (_selfx * _t15)) - ((_selfy) * (_t16) + (_selfz * _t17)));
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invNegativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invNegativeX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invNegativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeX_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, -(_t12 * _t16));
            dest.put(destOffset + 1, -(_t10 * _t16));
            dest.put(destOffset + 2, -(_t9 * _t16));
        } else {
            dest.put(destOffset, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    /** {@code invNegativeX_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer invNegativeX_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, -(_t12 * _t16));
            dest.put(destOffset + 1, -(_t10 * _t16));
            dest.put(destOffset + 2, -(_t9 * _t16));
        } else {
            dest.put(destOffset, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invNegativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invNegativeY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invNegativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeY_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, -(_t9 * _t16));
            dest.put(destOffset + 1, -(_t12 * _t16));
            dest.put(destOffset + 2, -(_t10 * _t16));
        } else {
            dest.put(destOffset, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    /** {@code invNegativeY_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer invNegativeY_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, -(_t9 * _t16));
            dest.put(destOffset + 1, -(_t12 * _t16));
            dest.put(destOffset + 2, -(_t10 * _t16));
        } else {
            dest.put(destOffset, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invNegativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invNegativeZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invNegativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNegativeZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invNegativeZ_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, -(_t10 * _t16));
            dest.put(destOffset + 1, -(_t9 * _t16));
            dest.put(destOffset + 2, -(_t12 * _t16));
        } else {
            dest.put(destOffset, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    /** {@code invNegativeZ_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer invNegativeZ_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, -(_t10 * _t16));
            dest.put(destOffset + 1, -(_t9 * _t16));
            dest.put(destOffset + 2, -(_t12 * _t16));
        } else {
            dest.put(destOffset, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invNormalizedNegativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invNormalizedNegativeX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invNormalizedNegativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
            dest.put(destOffset + 1, -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.put(destOffset + 2, -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, ((2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))) - (1.0f)));
            dest.put(destOffset + 1, -2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.put(destOffset + 2, -2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer invNormalizedNegativeY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invNormalizedNegativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invNormalizedNegativeY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invNormalizedNegativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, -2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.put(destOffset + 1, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
            dest.put(destOffset + 2, -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, -2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.put(destOffset + 1, ((2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))) - (1.0f)));
            dest.put(destOffset + 2, -2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer invNormalizedNegativeZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invNormalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invNormalizedNegativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invNormalizedNegativeZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invNormalizedNegativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedNegativeZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, -2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            dest.put(destOffset + 1, -2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            dest.put(destOffset + 2, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, -2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            dest.put(destOffset + 1, -2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            dest.put(destOffset + 2, ((2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))) - (1.0f)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer invNormalizedPositiveX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invNormalizedPositiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invNormalizedPositiveX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invNormalizedPositiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
            dest.put(destOffset + 1, 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.put(destOffset + 2, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz)))));
            dest.put(destOffset + 1, 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.put(destOffset + 2, 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer invNormalizedPositiveY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invNormalizedPositiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invNormalizedPositiveY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invNormalizedPositiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.put(destOffset + 1, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
            dest.put(destOffset + 2, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.put(destOffset + 1, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz)))));
            dest.put(destOffset + 2, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer invNormalizedPositiveZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invNormalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invNormalizedPositiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invNormalizedPositiveZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invNormalizedPositiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invNormalizedPositiveZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            dest.put(destOffset + 1, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            dest.put(destOffset + 2, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            dest.put(destOffset + 1, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            dest.put(destOffset + 2, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
            return dest;
        }
    }

    public static java.nio.FloatBuffer invPositiveX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invPositiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invPositiveX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invPositiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveX_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, _t12 * _t16);
            dest.put(destOffset + 1, _t10 * _t16);
            dest.put(destOffset + 2, _t9 * _t16);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    /** {@code invPositiveX_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer invPositiveX_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, _t12 * _t16);
            dest.put(destOffset + 1, _t10 * _t16);
            dest.put(destOffset + 2, _t9 * _t16);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invPositiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invPositiveY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invPositiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveY_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, _t9 * _t16);
            dest.put(destOffset + 1, _t12 * _t16);
            dest.put(destOffset + 2, _t10 * _t16);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    /** {@code invPositiveY_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer invPositiveY_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, _t9 * _t16);
            dest.put(destOffset + 1, _t12 * _t16);
            dest.put(destOffset + 2, _t10 * _t16);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.invPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.invPositiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.invPositiveZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.invPositiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invPositiveZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return invPositiveZ_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, _t10 * _t16);
            dest.put(destOffset + 1, _t9 * _t16);
            dest.put(destOffset + 2, _t12 * _t16);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    /** {@code invPositiveZ_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer invPositiveZ_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, _t10 * _t16);
            dest.put(destOffset + 1, _t9 * _t16);
            dest.put(destOffset + 2, _t12 * _t16);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static float length_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return FloatQuatOpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return FloatQuatOps.length(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return FloatQuatOpsKernelsSegment.length_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return FloatQuatOpsKernelsTypedBuffer.length_apiGet(src, srcOffset);
    }

    public static float length_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))));
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            return (float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))))));
        }
    }

    public static float lengthSquared_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return FloatQuatOpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return FloatQuatOps.lengthSquared(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return FloatQuatOpsKernelsSegment.lengthSquared_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return FloatQuatOpsKernelsTypedBuffer.lengthSquared_apiGet(src, srcOffset);
    }

    public static float lengthSquared_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            return java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            return ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        }
    }

    public static java.nio.FloatBuffer log_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer log_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.log(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.log_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.log_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer log_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return log_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dest.put(destOffset, _selfx * _t6);
            dest.put(destOffset + 1, _selfy * _t6);
            dest.put(destOffset + 2, _selfz * _t6);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        dest.put(destOffset + 3, Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2))));
        return dest;
    }

    /** {@code log_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer log_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dest.put(destOffset, _selfx * _t6);
            dest.put(destOffset + 1, _selfy * _t6);
            dest.put(destOffset + 2, _selfz * _t6);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        dest.put(destOffset + 3, Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2)))));
        return dest;
    }

    public static java.nio.FloatBuffer negativeX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.negativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negativeX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.negativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.negativeX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.negativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negativeX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeX_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -_selfy * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, -(_t12 * _t16));
            dest.put(destOffset + 1, -(_t9 * _t16));
            dest.put(destOffset + 2, -(_t10 * _t16));
        } else {
            dest.put(destOffset, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    /** {@code negativeX_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer negativeX_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, -(_t12 * _t16));
            dest.put(destOffset + 1, -(_t9 * _t16));
            dest.put(destOffset + 2, -(_t10 * _t16));
        } else {
            dest.put(destOffset, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer negativeY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.negativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negativeY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.negativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.negativeY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.negativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negativeY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeY_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, -(_t10 * _t16));
            dest.put(destOffset + 1, -(_t12 * _t16));
            dest.put(destOffset + 2, -(_t9 * _t16));
        } else {
            dest.put(destOffset, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    /** {@code negativeY_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer negativeY_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, -(_t10 * _t16));
            dest.put(destOffset + 1, -(_t12 * _t16));
            dest.put(destOffset + 2, -(_t9 * _t16));
        } else {
            dest.put(destOffset, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer negativeZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.negativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negativeZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.negativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.negativeZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.negativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negativeZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return negativeZ_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, -(_t9 * _t16));
            dest.put(destOffset + 1, -(_t10 * _t16));
            dest.put(destOffset + 2, -(_t12 * _t16));
        } else {
            dest.put(destOffset, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    /** {@code negativeZ_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer negativeZ_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, -(_t9 * _t16));
            dest.put(destOffset + 1, -(_t10 * _t16));
            dest.put(destOffset + 2, -(_t12 * _t16));
        } else {
            dest.put(destOffset, -0.0f);
            dest.put(destOffset + 1, -0.0f);
            dest.put(destOffset + 2, -0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer normalize_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalize_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.normalize(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.normalize_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.normalize_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalize_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalize_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.put(destOffset, _selfx * _t4);
            dest.put(destOffset + 1, _selfy * _t4);
            dest.put(destOffset + 2, _selfz * _t4);
            dest.put(destOffset + 3, _selfw * _t4);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code normalize_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer normalize_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.put(destOffset, _selfx * _t4);
            dest.put(destOffset + 1, _selfy * _t4);
            dest.put(destOffset + 2, _selfz * _t4);
            dest.put(destOffset + 3, _selfw * _t4);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.normalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.normalizedNegativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.normalizedNegativeX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.normalizedNegativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f));
            dest.put(destOffset + 1, -2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.put(destOffset + 2, -2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, ((2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz))) - (1.0f)));
            dest.put(destOffset + 1, -2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.put(destOffset + 2, -2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer normalizedNegativeY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.normalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.normalizedNegativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.normalizedNegativeY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.normalizedNegativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, -2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.put(destOffset + 1, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f));
            dest.put(destOffset + 2, -2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, -2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.put(destOffset + 1, ((2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz))) - (1.0f)));
            dest.put(destOffset + 2, -2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer normalizedNegativeZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.normalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.normalizedNegativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.normalizedNegativeZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.normalizedNegativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedNegativeZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, -2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            dest.put(destOffset + 1, -2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            dest.put(destOffset + 2, java.lang.Math.fma(2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, -2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            dest.put(destOffset + 1, -2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            dest.put(destOffset + 2, ((2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy))) - (1.0f)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer normalizedPositiveX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.normalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.normalizedPositiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.normalizedPositiveX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.normalizedPositiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f));
            dest.put(destOffset + 1, 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw));
            dest.put(destOffset + 2, 2.0f * java.lang.Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, ((1.0f) - (2.0f) * (((_selfy) * (_selfy) + (_selfz * _selfz)))));
            dest.put(destOffset + 1, 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw)));
            dest.put(destOffset + 2, 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer normalizedPositiveY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.normalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.normalizedPositiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.normalizedPositiveY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.normalizedPositiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
            dest.put(destOffset + 1, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f));
            dest.put(destOffset + 2, 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw)));
            dest.put(destOffset + 1, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfz * _selfz)))));
            dest.put(destOffset + 2, 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer normalizedPositiveZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.normalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.normalizedPositiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.normalizedPositiveZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.normalizedPositiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normalizedPositiveZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw));
            dest.put(destOffset + 1, 2.0f * java.lang.Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
            dest.put(destOffset + 2, java.lang.Math.fma(-2.0f, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            dest.put(destOffset, 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw)));
            dest.put(destOffset + 1, 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw)));
            dest.put(destOffset + 2, ((1.0f) - (2.0f) * (((_selfx) * (_selfx) + (_selfy * _selfy)))));
            return dest;
        }
    }

    public static java.nio.FloatBuffer positiveX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.positiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer positiveX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.positiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.positiveX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.positiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer positiveX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveX_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfz, -_selfy * _selfw);
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, _t12 * _t16);
            dest.put(destOffset + 1, _t9 * _t16);
            dest.put(destOffset + 2, _t10 * _t16);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    /** {@code positiveX_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer positiveX_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_selfy) + (_selfz * _selfw));
        float _t10 = 2.0f * ((_selfx) * (_selfz) - (_selfy * _selfw));
        float _t12 = ((((((_selfx) * (_selfx) + (_selfw * _selfw))) - (_selfy) * (_selfy))) - (_selfz) * (_selfz));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, _t12 * _t16);
            dest.put(destOffset + 1, _t9 * _t16);
            dest.put(destOffset + 2, _t10 * _t16);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer positiveY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.positiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer positiveY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.positiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.positiveY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.positiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer positiveY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveY_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * java.lang.Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = java.lang.Math.fma(-_selfz, _selfz, java.lang.Math.fma(_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, _t10 * _t16);
            dest.put(destOffset + 1, _t12 * _t16);
            dest.put(destOffset + 2, _t9 * _t16);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    /** {@code positiveY_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer positiveY_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_selfw) + (_selfy * _selfz));
        float _t10 = 2.0f * ((_selfx) * (_selfy) - (_selfz * _selfw));
        float _t12 = ((((_selfy) * (_selfy) + (((_selfw) * (_selfw) - (_selfx * _selfx))))) - (_selfz) * (_selfz));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, _t10 * _t16);
            dest.put(destOffset + 1, _t12 * _t16);
            dest.put(destOffset + 2, _t9 * _t16);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer positiveZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.positiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer positiveZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.positiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.positiveZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.positiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer positiveZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return positiveZ_apiGet_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _selfz, -_selfx * _selfw);
        float _t12 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(_selfw, _selfw, -_selfx * _selfx)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, _t9 * _t16);
            dest.put(destOffset + 1, _t10 * _t16);
            dest.put(destOffset + 2, _t12 * _t16);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    /** {@code positiveZ_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer positiveZ_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_selfz) + (_selfy * _selfw));
        float _t10 = 2.0f * ((_selfy) * (_selfz) - (_selfx * _selfw));
        float _t12 = ((_selfz) * (_selfz) + (((((_selfw) * (_selfw) - (_selfx * _selfx))) - (_selfy) * (_selfy))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest.put(destOffset, _t9 * _t16);
            dest.put(destOffset + 1, _t10 * _t16);
            dest.put(destOffset + 2, _t12 * _t16);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer pow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.pow_unsafe(_destBase, _srcBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer pow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.pow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.pow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, t);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.pow_apiGet(dest, destOffset, src, srcOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer pow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return pow_apiGet_fma(dest, destOffset, src, srcOffset, t);
        return pow_apiGet_mulAdd(dest, destOffset, src, srcOffset, t);
    }

    /** {@code pow_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer pow_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t12 = Math.min(Math.exp(t == 0.0f ? 0.0f : t * Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2)))), 3.4028235E38f);
        float _t13 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t20, _t21, _t22;
        if (_t2 > 0.0f) {
            _t20 = t * _selfz * _t13;
            _t21 = t * _selfx * _t13;
            _t22 = t * _selfy * _t13;
        } else {
            _t20 = t * 0.0f;
            _t21 = t * 0.0f;
            _t22 = t * 0.0f;
        }
        float _t25 = java.lang.Math.fma(_t20, _t20, java.lang.Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (float) java.lang.Math.sqrt(_t25);
        return pow_apiGet_fma_sfa5926ad_1(dest, destOffset, _t12, _t20, _t21, _t22, _t25, _t26, _t12 * (Math.sinWithCos(_t26) / _t26));
    }

    /** Piece 2 of {@code pow_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer pow_apiGet_fma_sfa5926ad_1(java.nio.FloatBuffer dest, int destOffset, float _t12, float _t20, float _t21, float _t22, float _t25, float _t26, float _t31) {
        if (_t25 > 0.0f) {
            dest.put(destOffset, _t21 * _t31);
            dest.put(destOffset + 1, _t22 * _t31);
            dest.put(destOffset + 2, _t20 * _t31);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        dest.put(destOffset + 3, Math.cosWithSin(_t26) * _t12);
        return dest;
    }

    /** {@code pow_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer pow_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t12 = Math.min(Math.exp(t == 0.0f ? 0.0f : t * Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2))))), 3.4028235E38f);
        float _t13 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t20, _t21, _t22;
        if (_t2 > 0.0f) {
            _t20 = t * _selfz * _t13;
            _t21 = t * _selfx * _t13;
            _t22 = t * _selfy * _t13;
        } else {
            _t20 = t * 0.0f;
            _t21 = t * 0.0f;
            _t22 = t * 0.0f;
        }
        float _t25 = ((_t20) * (_t20) + (((_t21) * (_t21) + (_t22 * _t22))));
        float _t26 = (float) java.lang.Math.sqrt(_t25);
        float _t31 = _t12 * (Math.sinWithCos(_t26) / _t26);
        if (_t25 > 0.0f) {
            dest.put(destOffset, _t21 * _t31);
            dest.put(destOffset + 1, _t22 * _t31);
            dest.put(destOffset + 2, _t20 * _t31);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
        }
        dest.put(destOffset + 3, Math.cosWithSin(_t26) * _t12);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTowards_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, targetX, targetY, targetZ, targetW, step);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTowards_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateTowards(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, targetX, targetY, targetZ, targetW, step);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateTowards_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, targetX, targetY, targetZ, targetW, step);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateTowards_apiGet(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTowards_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_apiGet_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
        return rotateTowards_apiGet_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
    }

    /** {@code rotateTowards_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateTowards_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_selfw, targetW, java.lang.Math.fma(_selfz, targetZ, java.lang.Math.fma(_selfx, targetX, _selfy * targetY))) > 0.0f) {
            _t9 = -targetW;
            _t10 = -targetZ;
            _t11 = -targetX;
            _t12 = -targetY;
        } else {
            _t9 = targetW;
            _t10 = targetZ;
            _t11 = targetX;
            _t12 = targetY;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t17 = _selfw - _t9;
        float _t18 = _selfz - _t10;
        float _t19 = _selfx - _t11;
        float _t20 = _selfy - _t12;
        float _t30 = java.lang.Math.fma(_t15, _t15, _t16 * _t16);
        float _t37 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, _t30));
        float _t38 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        return rotateTowards_apiGet_fma_s5b0a31ba_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37 * _t38, _t46, 4.0f * _t46);
    }

    /** Piece 2 of {@code rotateTowards_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTowards_apiGet_fma_s5b0a31ba_1(java.nio.FloatBuffer dest, int destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t46, float _t49) {
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t56 = 1.0f - _t55;
        float _t61 = (_t46 + _t46) * _t55;
        float _t65 = 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43));
        float _t74 = java.lang.Math.fma(-0.25f * (_t30 + java.lang.Math.fma(_t14, _t14, _t13 * _t13) + (java.lang.Math.fma(-_t20, _t20, -_t19 * _t19) + java.lang.Math.fma(-_t17, _t17, -_t18 * _t18))), _t65, Math.cosWithSin(_t61));
        float _t79, _t80, _t81, _t82;
        if (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f) {
            _t79 = java.lang.Math.fma(_selfw, _t74, _t65 * _t9);
            _t80 = java.lang.Math.fma(_selfz, _t74, _t65 * _t10);
            _t81 = java.lang.Math.fma(_selfx, _t74, _t65 * _t11);
            _t82 = java.lang.Math.fma(_selfy, _t74, _t65 * _t12);
        } else {
            _t79 = java.lang.Math.fma(_selfw, _t56, _t9 * _t55);
            _t80 = java.lang.Math.fma(_selfz, _t56, _t10 * _t55);
            _t81 = java.lang.Math.fma(_selfx, _t56, _t11 * _t55);
            _t82 = java.lang.Math.fma(_selfy, _t56, _t12 * _t55);
        }
        float _t86 = java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82)));
        return rotateTowards_apiGet_fma_s5b0a31ba_2(dest, destOffset, _t79, _t80, _t81, _t82, _t86, (1.0f / (float) java.lang.Math.sqrt(_t86)));
    }

    /** Piece 3 of {@code rotateTowards_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTowards_apiGet_fma_s5b0a31ba_2(java.nio.FloatBuffer dest, int destOffset, float _t79, float _t80, float _t81, float _t82, float _t86, float _t87) {
        if (_t86 != 0.0f) {
            dest.put(destOffset, _t87 * _t81);
            dest.put(destOffset + 1, _t87 * _t82);
            dest.put(destOffset + 2, _t87 * _t80);
            dest.put(destOffset + 3, _t87 * _t79);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code rotateTowards_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateTowards_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9, _t10, _t11, _t12;
        if (-((_selfw) * (targetW) + (((_selfz) * (targetZ) + (((_selfx) * (targetX) + (_selfy * targetY)))))) > 0.0f) {
            _t9 = -targetW;
            _t10 = -targetZ;
            _t11 = -targetX;
            _t12 = -targetY;
        } else {
            _t9 = targetW;
            _t10 = targetZ;
            _t11 = targetX;
            _t12 = targetY;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t17 = _selfw - _t9;
        float _t18 = _selfz - _t10;
        float _t19 = _selfx - _t11;
        float _t20 = _selfy - _t12;
        float _t30 = ((_t15) * (_t15) + (_t16 * _t16));
        float _t37 = ((_t13) * (_t13) + (((_t14) * (_t14) + (_t30))));
        float _t38 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        float _t49 = 4.0f * _t46;
        return rotateTowards_apiGet_mulAdd_s4d51a7f9_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37 * _t38, _t46, _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f);
    }

    /** Piece 2 of {@code rotateTowards_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTowards_apiGet_mulAdd_s4d51a7f9_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t46, float _t55) {
        float _t56 = 1.0f - _t55;
        float _t61 = (_t46 + _t46) * _t55;
        float _t65 = 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43));
        float _t74 = ((-0.25f * (_t30 + ((_t14) * (_t14) + (_t13 * _t13)) + (((-_t20) * (_t20) - (_t19 * _t19)) + ((-_t17) * (_t17) - (_t18 * _t18))))) * (_t65) + (Math.cosWithSin(_t61)));
        float _t79, _t80, _t81, _t82;
        if (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f) {
            _t79 = ((_selfw) * (_t74) + (_t65 * _t9));
            _t80 = ((_selfz) * (_t74) + (_t65 * _t10));
            _t81 = ((_selfx) * (_t74) + (_t65 * _t11));
            _t82 = ((_selfy) * (_t74) + (_t65 * _t12));
        } else {
            _t79 = ((_selfw) * (_t56) + (_t9 * _t55));
            _t80 = ((_selfz) * (_t56) + (_t10 * _t55));
            _t81 = ((_selfx) * (_t56) + (_t11 * _t55));
            _t82 = ((_selfy) * (_t56) + (_t12 * _t55));
        }
        float _t86 = ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82))))));
        return rotateTowards_apiGet_mulAdd_s4d51a7f9_2(dest, destOffset, _t79, _t80, _t81, _t82, _t86, (1.0f / (float) java.lang.Math.sqrt(_t86)));
    }

    /** Piece 3 of {@code rotateTowards_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTowards_apiGet_mulAdd_s4d51a7f9_2(java.nio.FloatBuffer dest, int destOffset, float _t79, float _t80, float _t81, float _t82, float _t86, float _t87) {
        if (_t86 != 0.0f) {
            dest.put(destOffset, _t87 * _t81);
            dest.put(destOffset + 1, _t87 * _t82);
            dest.put(destOffset + 2, _t87 * _t80);
            dest.put(destOffset + 3, _t87 * _t79);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer rotateTowards_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float step) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _targetBase = UnsafeOpsHolder.U.getLong(target, UnsafeCopy.BB_ADDRESS_OFFSET) + targetOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateTowards_unsafe(_destBase, _srcBase, _targetBase, step);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTowards_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float step) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && target.hasArray() && targetOffset >= 0 && targetOffset <= target.limit() - 4) {
            FloatQuatOps.rotateTowards(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, target.array(), target.arrayOffset() + targetOffset, step);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && target.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateTowards_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(target.duplicate().position(0)), targetOffset * 4L, step);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateTowards_apiGet(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTowards_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float step) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTowards_apiGet_fma(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_apiGet_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    /** {@code rotateTowards_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateTowards_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float step) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _targetx = target.get(targetOffset);
        float _targety = target.get(targetOffset + 1);
        float _targetz = target.get(targetOffset + 2);
        float _targetw = target.get(targetOffset + 3);
        float _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety))) > 0.0f) {
            _t9 = -_targetw;
            _t10 = -_targetz;
            _t11 = -_targetx;
            _t12 = -_targety;
        } else {
            _t9 = _targetw;
            _t10 = _targetz;
            _t11 = _targetx;
            _t12 = _targety;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t17 = _selfw - _t9;
        float _t18 = _selfz - _t10;
        float _t19 = _selfx - _t11;
        float _t20 = _selfy - _t12;
        float _t30 = java.lang.Math.fma(_t15, _t15, _t16 * _t16);
        return rotateTowards_apiGet_fma_sb1e88bb0_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _t17, _t18, _t19, _t20, _t30, java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, _t30)), java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20))));
    }

    /** Piece 2 of {@code rotateTowards_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTowards_apiGet_fma_sb1e88bb0_1(java.nio.FloatBuffer dest, int destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t37, float _t38) {
        float _t43 = _t37 * _t38;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        float _t49 = 4.0f * _t46;
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t56 = 1.0f - _t55;
        float _t61 = (_t46 + _t46) * _t55;
        float _t65 = 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43));
        float _t74 = java.lang.Math.fma(-0.25f * (_t30 + java.lang.Math.fma(_t14, _t14, _t13 * _t13) + (java.lang.Math.fma(-_t20, _t20, -_t19 * _t19) + java.lang.Math.fma(-_t17, _t17, -_t18 * _t18))), _t65, Math.cosWithSin(_t61));
        float _t79, _t80, _t81, _t82;
        if (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f) {
            _t79 = java.lang.Math.fma(_selfw, _t74, _t65 * _t9);
            _t80 = java.lang.Math.fma(_selfz, _t74, _t65 * _t10);
            _t81 = java.lang.Math.fma(_selfx, _t74, _t65 * _t11);
            _t82 = java.lang.Math.fma(_selfy, _t74, _t65 * _t12);
        } else {
            _t79 = java.lang.Math.fma(_selfw, _t56, _t9 * _t55);
            _t80 = java.lang.Math.fma(_selfz, _t56, _t10 * _t55);
            _t81 = java.lang.Math.fma(_selfx, _t56, _t11 * _t55);
            _t82 = java.lang.Math.fma(_selfy, _t56, _t12 * _t55);
        }
        return rotateTowards_apiGet_fma_sb1e88bb0_2(dest, destOffset, _t79, _t80, _t81, _t82);
    }

    /** Piece 3 of {@code rotateTowards_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTowards_apiGet_fma_sb1e88bb0_2(java.nio.FloatBuffer dest, int destOffset, float _t79, float _t80, float _t81, float _t82) {
        float _t86 = java.lang.Math.fma(_t79, _t79, java.lang.Math.fma(_t80, _t80, java.lang.Math.fma(_t81, _t81, _t82 * _t82)));
        float _t87 = (1.0f / (float) java.lang.Math.sqrt(_t86));
        if (_t86 != 0.0f) {
            dest.put(destOffset, _t87 * _t81);
            dest.put(destOffset + 1, _t87 * _t82);
            dest.put(destOffset + 2, _t87 * _t80);
            dest.put(destOffset + 3, _t87 * _t79);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    /** {@code rotateTowards_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateTowards_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float step) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _targetx = target.get(targetOffset);
        float _targety = target.get(targetOffset + 1);
        float _targetz = target.get(targetOffset + 2);
        float _targetw = target.get(targetOffset + 3);
        float _t9, _t10, _t11, _t12;
        if (-((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety)))))) > 0.0f) {
            _t9 = -_targetw;
            _t10 = -_targetz;
            _t11 = -_targetx;
            _t12 = -_targety;
        } else {
            _t9 = _targetw;
            _t10 = _targetz;
            _t11 = _targetx;
            _t12 = _targety;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t17 = _selfw - _t9;
        float _t18 = _selfz - _t10;
        float _t19 = _selfx - _t11;
        float _t20 = _selfy - _t12;
        float _t30 = ((_t15) * (_t15) + (_t16 * _t16));
        float _t37 = ((_t13) * (_t13) + (((_t14) * (_t14) + (_t30))));
        float _t38 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        return rotateTowards_apiGet_mulAdd_s39df1571_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t9, _t10, _t11, _t12, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37, _t38, _t37 * _t38);
    }

    /** Piece 2 of {@code rotateTowards_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTowards_apiGet_mulAdd_s39df1571_1(java.nio.FloatBuffer dest, int destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t37, float _t38, float _t43) {
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        float _t49 = 4.0f * _t46;
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t56 = 1.0f - _t55;
        float _t61 = (_t46 + _t46) * _t55;
        float _t65 = 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43));
        float _t74 = ((-0.25f * (_t30 + ((_t14) * (_t14) + (_t13 * _t13)) + (((-_t20) * (_t20) - (_t19 * _t19)) + ((-_t17) * (_t17) - (_t18 * _t18))))) * (_t65) + (Math.cosWithSin(_t61)));
        float _t79, _t80, _t81, _t82;
        if (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f) {
            _t79 = ((_selfw) * (_t74) + (_t65 * _t9));
            _t80 = ((_selfz) * (_t74) + (_t65 * _t10));
            _t81 = ((_selfx) * (_t74) + (_t65 * _t11));
            _t82 = ((_selfy) * (_t74) + (_t65 * _t12));
        } else {
            _t79 = ((_selfw) * (_t56) + (_t9 * _t55));
            _t80 = ((_selfz) * (_t56) + (_t10 * _t55));
            _t81 = ((_selfx) * (_t56) + (_t11 * _t55));
            _t82 = ((_selfy) * (_t56) + (_t12 * _t55));
        }
        return rotateTowards_apiGet_mulAdd_s39df1571_2(dest, destOffset, _t79, _t80, _t81, _t82, ((_t79) * (_t79) + (((_t80) * (_t80) + (((_t81) * (_t81) + (_t82 * _t82)))))));
    }

    /** Piece 3 of {@code rotateTowards_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTowards_apiGet_mulAdd_s39df1571_2(java.nio.FloatBuffer dest, int destOffset, float _t79, float _t80, float _t81, float _t82, float _t86) {
        float _t87 = (1.0f / (float) java.lang.Math.sqrt(_t86));
        if (_t86 != 0.0f) {
            dest.put(destOffset, _t87 * _t81);
            dest.put(destOffset + 1, _t87 * _t82);
            dest.put(destOffset + 2, _t87 * _t80);
            dest.put(destOffset + 3, _t87 * _t79);
        } else {
            dest.put(destOffset, 0.0f);
            dest.put(destOffset + 1, 0.0f);
            dest.put(destOffset + 2, 0.0f);
            dest.put(destOffset + 3, 0.0f);
        }
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_lh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return FloatQuatOpsKernelsTypedBuffer.lookAlong_lh_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.FloatBuffer lookAlong_lh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_lh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOpsKernelsArray.lookAlong_lh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.lookAlong_lh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.lookAlong_lh_apiGet(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_lh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_apiGet_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_apiGet_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_lh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, upX);
        float _t20 = java.lang.Math.fma(_t18, _t13, upY);
        float _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_apiGet_fma_s7abf681a_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma_s7abf681a_1(java.nio.FloatBuffer dest, int destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        return lookAlong_lh_apiGet_fma_s7abf681a_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma_s7abf681a_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        return lookAlong_lh_apiGet_fma_s7abf681a_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma_s7abf681a_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t19 = ((_t18) * (_t12) + (upX));
        float _t20 = ((_t18) * (_t13) + (upY));
        float _t21 = ((_t18) * (_t11) + (upZ));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return lookAlong_lh_apiGet_mulAdd_s7f42d5ab_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd_s7f42d5ab_1(java.nio.FloatBuffer dest, int destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return lookAlong_lh_apiGet_mulAdd_s7f42d5ab_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd_s7f42d5ab_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2) {
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.put(destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.put(destOffset + 1, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_apiGet_mulAdd_s7f42d5ab_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd_s7f42d5ab_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.put(destOffset + 2, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.put(destOffset + 3, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_rh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return FloatQuatOpsKernelsTypedBuffer.lookAlong_rh_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.FloatBuffer lookAlong_rh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_rh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOpsKernelsArray.lookAlong_rh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.lookAlong_rh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.lookAlong_rh_apiGet(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_rh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_apiGet_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_apiGet_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code lookAlong_rh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, upY);
        float _t22 = java.lang.Math.fma(_t20, _t12, upX);
        float _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_apiGet_fma_s3424eef0_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma_s3424eef0_1(java.nio.FloatBuffer dest, int destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        return lookAlong_rh_apiGet_fma_s3424eef0_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma_s3424eef0_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        return lookAlong_rh_apiGet_fma_s3424eef0_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma_s3424eef0_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t21 = ((_t20) * (_t13) + (upY));
        float _t22 = ((_t20) * (_t12) + (upX));
        float _t23 = ((_t20) * (_t11) + (upZ));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_apiGet_mulAdd_sb8316501_1(dest, destOffset, upX, upY, upZ, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd_sb8316501_1(java.nio.FloatBuffer dest, int destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return lookAlong_rh_apiGet_mulAdd_sb8316501_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code lookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd_sb8316501_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2) {
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest.put(destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        dest.put(destOffset + 1, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_apiGet_mulAdd_sb8316501_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd_sb8316501_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.put(destOffset + 2, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.put(destOffset + 3, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_lh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return FloatQuatOpsKernelsTypedBuffer.lookAlong_lh_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.FloatBuffer lookAlong_lh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 4L;
        FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_lh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            FloatQuatOpsKernelsArray.lookAlong_lh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.lookAlong_lh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(dir.duplicate().position(0)), dirOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.lookAlong_lh_apiGet(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_lh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_apiGet_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_apiGet_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_lh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        return lookAlong_lh_apiGet_fma_s195c651c_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, java.lang.Math.fma(_t19, _t13, -(_t20 * _t12)), java.lang.Math.fma(_t20, _t11, -(_t21 * _t13)), java.lang.Math.fma(_t21, _t12, -(_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma_s195c651c_1(java.nio.FloatBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30) {
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        return lookAlong_lh_apiGet_fma_s195c651c_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)));
    }

    /** Piece 3 of {@code lookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma_s195c651c_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        return lookAlong_lh_apiGet_fma_s195c651c_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma_s195c651c_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t19 = ((_t18) * (_t12) + (_upx));
        float _t20 = ((_t18) * (_t13) + (_upy));
        float _t21 = ((_t18) * (_t11) + (_upz));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        return lookAlong_lh_apiGet_mulAdd_sb8419c23_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30)))));
    }

    /** Piece 2 of {@code lookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd_sb8419c23_1(java.nio.FloatBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33) {
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        return lookAlong_lh_apiGet_mulAdd_sb8419c23_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd_sb8419c23_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.put(destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.put(destOffset + 1, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_apiGet_mulAdd_sb8419c23_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd_sb8419c23_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.put(destOffset + 2, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.put(destOffset + 3, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_rh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return FloatQuatOpsKernelsTypedBuffer.lookAlong_rh_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.FloatBuffer lookAlong_rh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 4L;
        FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_rh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            FloatQuatOpsKernelsArray.lookAlong_rh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.lookAlong_rh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(dir.duplicate().position(0)), dirOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.lookAlong_rh_apiGet(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_rh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_apiGet_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_apiGet_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code lookAlong_rh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        return lookAlong_rh_apiGet_fma_s39be2f12_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, java.lang.Math.fma(_t21, _t12, -(_t22 * _t13)), java.lang.Math.fma(_t22, _t11, -(_t23 * _t12)), java.lang.Math.fma(_t23, _t13, -(_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma_s39be2f12_1(java.nio.FloatBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32) {
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        return lookAlong_rh_apiGet_fma_s39be2f12_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma_s39be2f12_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t44, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75) {
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        return lookAlong_rh_apiGet_fma_s39be2f12_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma_s39be2f12_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        return lookAlong_rh_apiGet_mulAdd_s57a641f1_1(dest, destOffset, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, ((_t21) * (_t12) - (_t22 * _t13)), ((_t22) * (_t11) - (_t23 * _t12)), ((_t23) * (_t13) - (_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd_s57a641f1_1(java.nio.FloatBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32) {
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        return lookAlong_rh_apiGet_mulAdd_s57a641f1_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd_s57a641f1_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest.put(destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        return lookAlong_rh_apiGet_mulAdd_s57a641f1_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd_s57a641f1_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.put(destOffset + 1, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        dest.put(destOffset + 2, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.put(destOffset + 3, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_lh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return FloatQuatOpsKernelsTypedBuffer.lookAlong_lh_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.FloatBuffer lookAlong_lh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_lh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOpsKernelsArray.lookAlong_lh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.lookAlong_lh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.lookAlong_lh_apiGet(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_lh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_apiGet_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_apiGet_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_lh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, upX);
        float _t20 = java.lang.Math.fma(_t18, _t13, upY);
        float _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return lookAlong_lh_apiGet_fma_s2d958696_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma_s2d958696_1(java.nio.FloatBuffer dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        return lookAlong_lh_apiGet_fma_s2d958696_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), _t72, _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma_s2d958696_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        return lookAlong_lh_apiGet_fma_s2d958696_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma_s2d958696_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t19 = ((_t18) * (_t12) + (upX));
        float _t20 = ((_t18) * (_t13) + (upY));
        float _t21 = ((_t18) * (_t11) + (upZ));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return lookAlong_lh_apiGet_mulAdd_sdb40991_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code lookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd_sdb40991_1(java.nio.FloatBuffer dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return lookAlong_lh_apiGet_mulAdd_sdb40991_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd_sdb40991_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1, float _sp2) {
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.put(destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.put(destOffset + 1, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_apiGet_mulAdd_sdb40991_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd_sdb40991_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.put(destOffset + 2, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.put(destOffset + 3, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_rh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return FloatQuatOpsKernelsTypedBuffer.lookAlong_rh_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.FloatBuffer lookAlong_rh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_rh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOpsKernelsArray.lookAlong_rh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.lookAlong_rh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.lookAlong_rh_apiGet(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_rh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_apiGet_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_apiGet_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code lookAlong_rh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, upY);
        float _t22 = java.lang.Math.fma(_t20, _t12, upX);
        float _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return lookAlong_rh_apiGet_fma_sf86c0d44_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma_sf86c0d44_1(java.nio.FloatBuffer dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        return lookAlong_rh_apiGet_fma_sf86c0d44_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), _t74, java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma_sf86c0d44_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        return lookAlong_rh_apiGet_fma_sf86c0d44_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma_sf86c0d44_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t21 = ((_t20) * (_t13) + (upY));
        float _t22 = ((_t20) * (_t12) + (upX));
        float _t23 = ((_t20) * (_t11) + (upZ));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return lookAlong_rh_apiGet_mulAdd_sc5c0c467_1(dest, destOffset, upX, upY, upZ, epsilon, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code lookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd_sc5c0c467_1(java.nio.FloatBuffer dest, int destOffset, float upX, float upY, float upZ, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return lookAlong_rh_apiGet_mulAdd_sc5c0c467_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code lookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd_sc5c0c467_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3, float _sp1, float _sp2) {
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest.put(destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        dest.put(destOffset + 1, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        return lookAlong_rh_apiGet_mulAdd_sc5c0c467_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd_sc5c0c467_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.put(destOffset + 2, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.put(destOffset + 3, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_lh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lookAlong_lh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return FloatQuatOpsKernelsTypedBuffer.lookAlong_lh_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.FloatBuffer lookAlong_lh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 4L;
        FloatQuatOpsKernelsAddress.lookAlong_lh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_lh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            FloatQuatOpsKernelsArray.lookAlong_lh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.lookAlong_lh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(dir.duplicate().position(0)), dirOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 4L, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.lookAlong_lh_apiGet(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_lh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_lh_apiGet_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_apiGet_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_lh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        return lookAlong_lh_apiGet_fma_s34951420_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, java.lang.Math.fma(_t19, _t13, -(_t20 * _t12)), java.lang.Math.fma(_t20, _t11, -(_t21 * _t13)), java.lang.Math.fma(_t21, _t12, -(_t19 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma_s34951420_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30) {
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        return lookAlong_lh_apiGet_fma_s34951420_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11)), java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)));
    }

    /** Piece 3 of {@code lookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma_s34951420_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t121, _selfw * _t119) + java.lang.Math.fma(_selfy, _t122, -(_selfz * _t120)));
        return lookAlong_lh_apiGet_fma_s34951420_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_fma_s34951420_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t121, _selfz * _t119) + java.lang.Math.fma(_selfw, _t120, -_selfx * _t122));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t120, _selfw * _t122) + java.lang.Math.fma(_selfz, _t121, -(_selfy * _t119)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t121, -_selfx * _t119) - java.lang.Math.fma(_selfy, _t120, _selfz * _t122));
        return dest;
    }

    /** {@code lookAlong_lh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t19 = ((_t18) * (_t12) + (_upx));
        float _t20 = ((_t18) * (_t13) + (_upy));
        float _t21 = ((_t18) * (_t11) + (_upz));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        return lookAlong_lh_apiGet_mulAdd_s5a687dc9_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t28, _t29, _t30, ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30)))));
    }

    /** Piece 2 of {@code lookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd_s5a687dc9_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33) {
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        return lookAlong_lh_apiGet_mulAdd_s5a687dc9_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t38, _t41, _t39 - _t12, _t39 + _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11)))), _t72, _t73, ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code lookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd_s5a687dc9_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp3, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71));
        float _t119, _t120, _t121, _t122;
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            _t119 = _sp0 * _t65;
            _t120 = _sp0 * _t48;
            _t121 = 0.5f * (float) java.lang.Math.sqrt(_t71);
            _t122 = _sp0 * _t69;
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                _t119 = 0.5f * (float) java.lang.Math.sqrt(_t72);
                _t120 = _sp3 * _t68;
                _t121 = _sp3 * _t65;
                _t122 = _sp3 * _t49;
            } else {
                if (_t60 > _t11) {
                    _t119 = _sp1 * _t68;
                    _t120 = 0.5f * (float) java.lang.Math.sqrt(_t73);
                    _t121 = _sp1 * _t48;
                    _t122 = _sp1 * _t66;
                } else {
                    _t119 = _sp2 * _t49;
                    _t120 = _sp2 * _t66;
                    _t121 = _sp2 * _t69;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                }
            }
        }
        dest.put(destOffset, ((_selfx) * (_t121) + (_selfw * _t119)) + ((_selfy) * (_t122) - (_selfz * _t120)));
        dest.put(destOffset + 1, ((_selfy) * (_t121) + (_selfz * _t119)) + ((_selfw) * (_t120) - (_selfx * _t122)));
        return lookAlong_lh_apiGet_mulAdd_s5a687dc9_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t119, _t120, _t121, _t122);
    }

    /** Piece 4 of {@code lookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_lh_apiGet_mulAdd_s5a687dc9_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t119, float _t120, float _t121, float _t122) {
        dest.put(destOffset + 2, ((_selfx) * (_t120) + (_selfw * _t122)) + ((_selfz) * (_t121) - (_selfy * _t119)));
        dest.put(destOffset + 3, ((_selfw) * (_t121) - (_selfx * _t119)) - ((_selfy) * (_t120) + (_selfz * _t122)));
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_rh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lookAlong_rh_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return FloatQuatOpsKernelsTypedBuffer.lookAlong_rh_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.FloatBuffer lookAlong_rh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 4L;
        FloatQuatOpsKernelsAddress.lookAlong_rh_unsafe(_destBase, _srcBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_rh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            FloatQuatOpsKernelsArray.lookAlong_rh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.lookAlong_rh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(dir.duplicate().position(0)), dirOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 4L, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.lookAlong_rh_apiGet(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer lookAlong_rh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return lookAlong_rh_apiGet_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_apiGet_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code lookAlong_rh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        return lookAlong_rh_apiGet_fma_s335ec78e_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, java.lang.Math.fma(_t21, _t12, -(_t22 * _t13)), java.lang.Math.fma(_t22, _t11, -(_t23 * _t12)), java.lang.Math.fma(_t23, _t13, -(_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma_s335ec78e_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32) {
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        return lookAlong_rh_apiGet_fma_s335ec78e_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t44, _t40 + _t12, _t40 - _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t15)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t15)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11)), java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11)));
    }

    /** Piece 3 of {@code lookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma_s335ec78e_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t44, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75) {
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        return lookAlong_rh_apiGet_fma_s335ec78e_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_fma_s335ec78e_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t123, _selfw * _t121) + java.lang.Math.fma(_selfy, _t124, -(_selfz * _t122)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t123, _selfz * _t121) + java.lang.Math.fma(_selfw, _t122, -_selfx * _t124));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t122, _selfw * _t124) + java.lang.Math.fma(_selfz, _t123, -(_selfy * _t121)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t123, -_selfx * _t121) - java.lang.Math.fma(_selfy, _t122, _selfz * _t124));
        return dest;
    }

    /** {@code lookAlong_rh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        return lookAlong_rh_apiGet_mulAdd_s15c3a617_1(dest, destOffset, epsilon, _upx, _upy, _upz, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, -_t11, -_t13, ((_t21) * (_t12) - (_t22 * _t13)), ((_t22) * (_t11) - (_t23 * _t12)), ((_t23) * (_t13) - (_t21 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd_s15c3a617_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32) {
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        return lookAlong_rh_apiGet_mulAdd_s15c3a617_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t14, _t40, _t41, _t43, _t40 + _t12, _t40 - _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t15)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t15)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11)))), _t74, ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11)))), ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd_s15c3a617_2(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t14, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp3) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73));
        float _t121, _t122, _t123, _t124;
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            _t121 = _sp0 * _t67;
            _t122 = _sp0 * _t50;
            _t123 = 0.5f * (float) java.lang.Math.sqrt(_t73);
            _t124 = _sp0 * _t71;
        } else {
            if (_t41 > Math.max(_t62, _t14)) {
                _t121 = 0.5f * (float) java.lang.Math.sqrt(_t74);
                _t122 = _sp3 * _t70;
                _t123 = _sp3 * _t67;
                _t124 = _sp3 * _t51;
            } else {
                if (_t62 > _t14) {
                    _t121 = _sp1 * _t70;
                    _t122 = 0.5f * (float) java.lang.Math.sqrt(_t75);
                    _t123 = _sp1 * _t50;
                    _t124 = _sp1 * _t68;
                } else {
                    _t121 = _sp2 * _t51;
                    _t122 = _sp2 * _t68;
                    _t123 = _sp2 * _t71;
                    _t124 = 0.5f * (float) java.lang.Math.sqrt(_t76);
                }
            }
        }
        dest.put(destOffset, ((_selfx) * (_t123) + (_selfw * _t121)) + ((_selfy) * (_t124) - (_selfz * _t122)));
        return lookAlong_rh_apiGet_mulAdd_s15c3a617_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t121, _t122, _t123, _t124);
    }

    /** Piece 4 of {@code lookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer lookAlong_rh_apiGet_mulAdd_s15c3a617_3(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t121, float _t122, float _t123, float _t124) {
        dest.put(destOffset + 1, ((_selfy) * (_t123) + (_selfz * _t121)) + ((_selfw) * (_t122) - (_selfx * _t124)));
        dest.put(destOffset + 2, ((_selfx) * (_t122) + (_selfw * _t124)) + ((_selfz) * (_t123) - (_selfy * _t121)));
        dest.put(destOffset + 3, ((_selfw) * (_t123) - (_selfx * _t121)) - ((_selfy) * (_t122) + (_selfz * _t124)));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_api(java.nio.FloatBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeRotationAxis(dest.array(), dest.arrayOffset() + destOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, angle, axisX, axisY, axisZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationAxis_apiGet(dest, destOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        dest.put(destOffset, axisX * _t1);
        dest.put(destOffset + 1, axisY * _t1);
        dest.put(destOffset + 2, axisZ * _t1);
        dest.put(destOffset + 3, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            FloatQuatOps.makeRotationAxis(dest.array(), dest.arrayOffset() + destOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && axis.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.makeRotationAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(axis.duplicate().position(0)), axisOffset * 4L, angle);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationAxis_apiGet(dest, destOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        float _axisy = axis.get(axisOffset + 1);
        float _axisz = axis.get(axisOffset + 2);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sinWithCos(_t0);
        dest.put(destOffset, axis.get(axisOffset) * _t1);
        dest.put(destOffset + 1, _axisy * _t1);
        dest.put(destOffset + 2, _axisz * _t1);
        dest.put(destOffset + 3, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_lh_api(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh_unsafe(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh_api(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOpsKernelsArray.makeRotationLookAlong_lh(dest.array(), dest.arrayOffset() + destOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationLookAlong_lh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_lh_apiGet(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_apiGet_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_lh_apiGet_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_lh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, upX);
        float _t20 = java.lang.Math.fma(_t18, _t13, upY);
        float _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return makeRotationLookAlong_lh_apiGet_fma_s22d835be_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_fma_s22d835be_1(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t48, float _t49) {
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        return makeRotationLookAlong_lh_apiGet_fma_s22d835be_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_fma_s22d835be_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest.put(destOffset, _sp0 * _t65);
            dest.put(destOffset + 1, _sp0 * _t49);
            dest.put(destOffset + 2, _sp0 * _t69);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.put(destOffset + 1, _sp3 * _t68);
                dest.put(destOffset + 2, _sp3 * _t48);
                dest.put(destOffset + 3, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.put(destOffset, _sp1 * _t68);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.put(destOffset + 2, _sp1 * _t66);
                    dest.put(destOffset + 3, _sp1 * _t49);
                } else {
                    dest.put(destOffset, _sp2 * _t48);
                    dest.put(destOffset + 1, _sp2 * _t66);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.put(destOffset + 3, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t19 = ((_t18) * (_t12) + (upX));
        float _t20 = ((_t18) * (_t13) + (upY));
        float _t21 = ((_t18) * (_t11) + (upZ));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return makeRotationLookAlong_lh_apiGet_mulAdd_s2f117859_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_mulAdd_s2f117859_1(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t48, float _t49, float _t60) {
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return makeRotationLookAlong_lh_apiGet_mulAdd_s2f117859_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, _t60, ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_mulAdd_s2f117859_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest.put(destOffset, _sp0 * _t65);
            dest.put(destOffset + 1, _sp0 * _t49);
            dest.put(destOffset + 2, _sp0 * _t69);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.put(destOffset + 1, _sp3 * _t68);
                dest.put(destOffset + 2, _sp3 * _t48);
                dest.put(destOffset + 3, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.put(destOffset, _sp1 * _t68);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.put(destOffset + 2, _sp1 * _t66);
                    dest.put(destOffset + 3, _sp1 * _t49);
                } else {
                    dest.put(destOffset, _sp2 * _t48);
                    dest.put(destOffset + 1, _sp2 * _t66);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.put(destOffset + 3, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_rh_api(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh_unsafe(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh_api(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOpsKernelsArray.makeRotationLookAlong_rh(dest.array(), dest.arrayOffset() + destOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationLookAlong_rh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_rh_apiGet(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_apiGet_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_rh_apiGet_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@code makeRotationLookAlong_rh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, upY);
        float _t22 = java.lang.Math.fma(_t20, _t12, upX);
        float _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeRotationLookAlong_rh_apiGet_fma_s223849a4_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_fma_s223849a4_1(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t50) {
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        return makeRotationLookAlong_rh_apiGet_fma_s223849a4_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_fma_s223849a4_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest.put(destOffset, _sp0 * _t67);
            dest.put(destOffset + 1, _sp0 * _t51);
            dest.put(destOffset + 2, _sp0 * _t71);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.put(destOffset + 1, _sp3 * _t70);
                dest.put(destOffset + 2, _sp3 * _t50);
                dest.put(destOffset + 3, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.put(destOffset, _sp1 * _t70);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.put(destOffset + 2, _sp1 * _t68);
                    dest.put(destOffset + 3, _sp1 * _t51);
                } else {
                    dest.put(destOffset, _sp2 * _t50);
                    dest.put(destOffset + 1, _sp2 * _t68);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.put(destOffset + 3, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t21 = ((_t20) * (_t13) + (upY));
        float _t22 = ((_t20) * (_t12) + (upX));
        float _t23 = ((_t20) * (_t11) + (upZ));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeRotationLookAlong_rh_apiGet_mulAdd_s62ea10df_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_mulAdd_s62ea10df_1(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t50, float _t51, float _t62) {
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return makeRotationLookAlong_rh_apiGet_mulAdd_s62ea10df_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_mulAdd_s62ea10df_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest.put(destOffset, _sp0 * _t67);
            dest.put(destOffset + 1, _sp0 * _t51);
            dest.put(destOffset + 2, _sp0 * _t71);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.put(destOffset + 1, _sp3 * _t70);
                dest.put(destOffset + 2, _sp3 * _t50);
                dest.put(destOffset + 3, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.put(destOffset, _sp1 * _t70);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.put(destOffset + 2, _sp1 * _t68);
                    dest.put(destOffset + 3, _sp1 * _t51);
                } else {
                    dest.put(destOffset, _sp2 * _t50);
                    dest.put(destOffset + 1, _sp2 * _t68);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.put(destOffset + 3, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_lh_api(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            FloatQuatOpsKernelsArray.makeRotationLookAlong_lh(dest.array(), dest.arrayOffset() + destOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.makeRotationLookAlong_lh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(dir.duplicate().position(0)), dirOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_lh_apiGet(dest, destOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_apiGet_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_lh_apiGet_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_lh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_apiGet_fma_sb7889ca8_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_fma_sb7889ca8_1(java.nio.FloatBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        return makeRotationLookAlong_lh_apiGet_fma_sb7889ca8_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_fma_sb7889ca8_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest.put(destOffset, _sp0 * _t65);
            dest.put(destOffset + 1, _sp0 * _t49);
            dest.put(destOffset + 2, _sp0 * _t69);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.put(destOffset + 1, _sp3 * _t68);
                dest.put(destOffset + 2, _sp3 * _t48);
                dest.put(destOffset + 3, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.put(destOffset, _sp1 * _t68);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.put(destOffset + 2, _sp1 * _t66);
                    dest.put(destOffset + 3, _sp1 * _t49);
                } else {
                    dest.put(destOffset, _sp2 * _t48);
                    dest.put(destOffset + 1, _sp2 * _t66);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.put(destOffset + 3, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t19 = ((_t18) * (_t12) + (_upx));
        float _t20 = ((_t18) * (_t13) + (_upy));
        float _t21 = ((_t18) * (_t11) + (_upz));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return makeRotationLookAlong_lh_apiGet_mulAdd_sb867dfe9_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_mulAdd_sb867dfe9_1(java.nio.FloatBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return makeRotationLookAlong_lh_apiGet_mulAdd_sb867dfe9_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11)))), _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_mulAdd_sb867dfe9_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest.put(destOffset, _sp0 * _t65);
            dest.put(destOffset + 1, _sp0 * _t49);
            dest.put(destOffset + 2, _sp0 * _t69);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.put(destOffset + 1, _sp3 * _t68);
                dest.put(destOffset + 2, _sp3 * _t48);
                dest.put(destOffset + 3, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.put(destOffset, _sp1 * _t68);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.put(destOffset + 2, _sp1 * _t66);
                    dest.put(destOffset + 3, _sp1 * _t49);
                } else {
                    dest.put(destOffset, _sp2 * _t48);
                    dest.put(destOffset + 1, _sp2 * _t66);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.put(destOffset + 3, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_rh_api(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            FloatQuatOpsKernelsArray.makeRotationLookAlong_rh(dest.array(), dest.arrayOffset() + destOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.makeRotationLookAlong_rh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(dir.duplicate().position(0)), dirOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_rh_apiGet(dest, destOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_apiGet_fma(dest, destOffset, dir, dirOffset, up, upOffset);
        return makeRotationLookAlong_rh_apiGet_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@code makeRotationLookAlong_rh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_apiGet_fma_sdf12b8ce_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_fma_sdf12b8ce_1(java.nio.FloatBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        return makeRotationLookAlong_rh_apiGet_fma_sdf12b8ce_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_fma_sdf12b8ce_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest.put(destOffset, _sp0 * _t67);
            dest.put(destOffset + 1, _sp0 * _t51);
            dest.put(destOffset + 2, _sp0 * _t71);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.put(destOffset + 1, _sp3 * _t70);
                dest.put(destOffset + 2, _sp3 * _t50);
                dest.put(destOffset + 3, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.put(destOffset, _sp1 * _t70);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.put(destOffset + 2, _sp1 * _t68);
                    dest.put(destOffset + 3, _sp1 * _t51);
                } else {
                    dest.put(destOffset, _sp2 * _t50);
                    dest.put(destOffset + 1, _sp2 * _t68);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.put(destOffset + 3, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return makeRotationLookAlong_rh_apiGet_mulAdd_sa48ea0bf_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_mulAdd_sa48ea0bf_1(java.nio.FloatBuffer dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return makeRotationLookAlong_rh_apiGet_mulAdd_sa48ea0bf_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_mulAdd_sa48ea0bf_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest.put(destOffset, _sp0 * _t67);
            dest.put(destOffset + 1, _sp0 * _t51);
            dest.put(destOffset + 2, _sp0 * _t71);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.put(destOffset + 1, _sp3 * _t70);
                dest.put(destOffset + 2, _sp3 * _t50);
                dest.put(destOffset + 3, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.put(destOffset, _sp1 * _t70);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.put(destOffset + 2, _sp1 * _t68);
                    dest.put(destOffset + 3, _sp1 * _t51);
                } else {
                    dest.put(destOffset, _sp2 * _t50);
                    dest.put(destOffset + 1, _sp2 * _t68);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.put(destOffset + 3, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_lh_api(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh_unsafe(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh_api(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOpsKernelsArray.makeRotationLookAlong_lh(dest.array(), dest.arrayOffset() + destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationLookAlong_lh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_lh_apiGet(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_apiGet_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_lh_apiGet_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, upX);
        float _t20 = java.lang.Math.fma(_t18, _t13, upY);
        float _t21 = java.lang.Math.fma(_t18, _t11, upZ);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return makeRotationLookAlong_lh_apiGet_fma_sb381294a_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_fma_sb381294a_1(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t48, float _t49) {
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t72 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        float _t74 = java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38));
        return makeRotationLookAlong_lh_apiGet_fma_sb381294a_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_fma_sb381294a_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest.put(destOffset, _sp0 * _t65);
            dest.put(destOffset + 1, _sp0 * _t49);
            dest.put(destOffset + 2, _sp0 * _t69);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.put(destOffset + 1, _sp3 * _t68);
                dest.put(destOffset + 2, _sp3 * _t48);
                dest.put(destOffset + 3, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.put(destOffset, _sp1 * _t68);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.put(destOffset + 2, _sp1 * _t66);
                    dest.put(destOffset + 3, _sp1 * _t49);
                } else {
                    dest.put(destOffset, _sp2 * _t48);
                    dest.put(destOffset + 1, _sp2 * _t66);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.put(destOffset + 3, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t19 = ((_t18) * (_t12) + (upX));
        float _t20 = ((_t18) * (_t13) + (upY));
        float _t21 = ((_t18) * (_t11) + (upZ));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        float _t34 = (1.0f / (float) java.lang.Math.sqrt(_t33));
        float _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        return makeRotationLookAlong_lh_apiGet_mulAdd_s2540295f_1(dest, destOffset, _t11, _t12, _t13, _t38, _t39, _t40, -_t39, -_t40, -_t38, 1.0f + _t38, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_mulAdd_s2540295f_1(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t38, float _t39, float _t40, float _t41, float _t42, float _t43, float _t44, float _t48, float _t49, float _t60) {
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t72 = ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return makeRotationLookAlong_lh_apiGet_mulAdd_s2540295f_2(dest, destOffset, _t11, _t12, _t38, _t41, _t48, _t49, _t60, ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, _t72, _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_mulAdd_s2540295f_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest.put(destOffset, _sp0 * _t65);
            dest.put(destOffset + 1, _sp0 * _t49);
            dest.put(destOffset + 2, _sp0 * _t69);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.put(destOffset + 1, _sp3 * _t68);
                dest.put(destOffset + 2, _sp3 * _t48);
                dest.put(destOffset + 3, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.put(destOffset, _sp1 * _t68);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.put(destOffset + 2, _sp1 * _t66);
                    dest.put(destOffset + 3, _sp1 * _t49);
                } else {
                    dest.put(destOffset, _sp2 * _t48);
                    dest.put(destOffset + 1, _sp2 * _t66);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.put(destOffset + 3, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_rh_api(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh_unsafe(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh_api(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOpsKernelsArray.makeRotationLookAlong_rh(dest.array(), dest.arrayOffset() + destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationLookAlong_rh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_rh_apiGet(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_apiGet_fma(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return makeRotationLookAlong_rh_apiGet_mulAdd(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, upY);
        float _t22 = java.lang.Math.fma(_t20, _t12, upX);
        float _t23 = java.lang.Math.fma(_t20, _t11, upZ);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeRotationLookAlong_rh_apiGet_fma_s917c1e68_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_fma_s917c1e68_1(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t50) {
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t74 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        float _t76 = java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41));
        return makeRotationLookAlong_rh_apiGet_fma_s917c1e68_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_fma_s917c1e68_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest.put(destOffset, _sp0 * _t67);
            dest.put(destOffset + 1, _sp0 * _t51);
            dest.put(destOffset + 2, _sp0 * _t71);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.put(destOffset + 1, _sp3 * _t70);
                dest.put(destOffset + 2, _sp3 * _t50);
                dest.put(destOffset + 3, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.put(destOffset, _sp1 * _t70);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.put(destOffset + 2, _sp1 * _t68);
                    dest.put(destOffset + 3, _sp1 * _t51);
                } else {
                    dest.put(destOffset, _sp2 * _t50);
                    dest.put(destOffset + 1, _sp2 * _t68);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.put(destOffset + 3, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t21 = ((_t20) * (_t13) + (upY));
        float _t22 = ((_t20) * (_t12) + (upX));
        float _t23 = ((_t20) * (_t11) + (upZ));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        float _t36 = (1.0f / (float) java.lang.Math.sqrt(_t35));
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        return makeRotationLookAlong_rh_apiGet_mulAdd_sb3851015_1(dest, destOffset, _t11, _t12, _t13, -_t13, -_t11, _t40, _t41, _t42, -_t41, -_t40, -_t42, 1.0f + _t41, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_mulAdd_sb3851015_1(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t13, float _t14, float _t15, float _t40, float _t41, float _t42, float _t43, float _t44, float _t45, float _t46, float _t50, float _t51, float _t62) {
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t74 = ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return makeRotationLookAlong_rh_apiGet_mulAdd_sb3851015_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t50, _t51, _t62, ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, _t74, _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_mulAdd_sb3851015_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest.put(destOffset, _sp0 * _t67);
            dest.put(destOffset + 1, _sp0 * _t51);
            dest.put(destOffset + 2, _sp0 * _t71);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.put(destOffset + 1, _sp3 * _t70);
                dest.put(destOffset + 2, _sp3 * _t50);
                dest.put(destOffset + 3, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.put(destOffset, _sp1 * _t70);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.put(destOffset + 2, _sp1 * _t68);
                    dest.put(destOffset + 3, _sp1 * _t51);
                } else {
                    dest.put(destOffset, _sp2 * _t50);
                    dest.put(destOffset + 1, _sp2 * _t68);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.put(destOffset + 3, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_lh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_lh_api(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_lh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            FloatQuatOpsKernelsArray.makeRotationLookAlong_lh(dest.array(), dest.arrayOffset() + destOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.makeRotationLookAlong_lh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(dir.duplicate().position(0)), dirOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 4L, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_lh_apiGet(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_lh_apiGet_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_lh_apiGet_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_lh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t19 = java.lang.Math.fma(_t18, _t12, _upx);
        float _t20 = java.lang.Math.fma(_t18, _t13, _upy);
        float _t21 = java.lang.Math.fma(_t18, _t11, _upz);
        float _t28 = java.lang.Math.fma(_t19, _t13, -(_t20 * _t12));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t21 * _t13));
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t19 * _t11));
        float _t33 = java.lang.Math.fma(_t28, _t28, java.lang.Math.fma(_t29, _t29, _t30 * _t30));
        return makeRotationLookAlong_lh_apiGet_fma_s51c592dc_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_fma_s51c592dc_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t71 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t44 + _t11));
        float _t73 = java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, 1.0f - _t38 - _t11));
        return makeRotationLookAlong_lh_apiGet_fma_s51c592dc_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, java.lang.Math.fma(_t38, _t11, -(_t39 * _t12)), java.lang.Math.fma(_t42, _t12, java.lang.Math.fma(_t38, _t13, _t13)), java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t13, _t13)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t40)), java.lang.Math.fma(_t39, _t13, java.lang.Math.fma(_t42, _t11, _t42)), _t71, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, _t44 - _t11)), _t73, java.lang.Math.fma(_t43, _t11, java.lang.Math.fma(_t39, _t12, 1.0f + _t11 - _t38)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_fma_s51c592dc_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (java.lang.Math.fma(_t38, _t11, java.lang.Math.fma(_t41, _t12, _t38 + _t11)) > 0.0f) {
            dest.put(destOffset, _sp0 * _t65);
            dest.put(destOffset + 1, _sp0 * _t49);
            dest.put(destOffset + 2, _sp0 * _t69);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.put(destOffset + 1, _sp3 * _t68);
                dest.put(destOffset + 2, _sp3 * _t48);
                dest.put(destOffset + 3, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.put(destOffset, _sp1 * _t68);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.put(destOffset + 2, _sp1 * _t66);
                    dest.put(destOffset + 3, _sp1 * _t49);
                } else {
                    dest.put(destOffset, _sp2 * _t48);
                    dest.put(destOffset + 1, _sp2 * _t66);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.put(destOffset + 3, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_lh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t18 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t19 = ((_t18) * (_t12) + (_upx));
        float _t20 = ((_t18) * (_t13) + (_upy));
        float _t21 = ((_t18) * (_t11) + (_upz));
        float _t28 = ((_t19) * (_t13) - (_t20 * _t12));
        float _t29 = ((_t20) * (_t11) - (_t21 * _t13));
        float _t30 = ((_t21) * (_t12) - (_t19 * _t11));
        float _t33 = ((_t28) * (_t28) + (((_t29) * (_t29) + (_t30 * _t30))));
        return makeRotationLookAlong_lh_apiGet_mulAdd_s982e7e6f_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, _t28, _t29, _t30, _t33, (1.0f / (float) java.lang.Math.sqrt(_t33)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_mulAdd_s982e7e6f_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t28, float _t29, float _t30, float _t33, float _t34) {
        float _t38, _t39, _t40;
        if (_t33 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t38 = _t29 * _t34;
            _t39 = _t28 * _t34;
            _t40 = _t30 * _t34;
        } else {
            _t38 = 0.0f;
            _t39 = 0.0f;
            _t40 = 0.0f;
        }
        float _t41 = -_t39;
        float _t42 = -_t40;
        float _t43 = -_t38;
        float _t44 = 1.0f + _t38;
        float _t71 = ((_t38) * (_t11) + (((_t41) * (_t12) + (_t44 + _t11))));
        float _t73 = ((_t38) * (_t11) + (((_t41) * (_t12) + (1.0f - _t38 - _t11))));
        float _t74 = ((_t43) * (_t11) + (((_t39) * (_t12) + (1.0f + _t11 - _t38))));
        return makeRotationLookAlong_lh_apiGet_mulAdd_s982e7e6f_2(dest, destOffset, _t11, _t12, _t38, _t41, _t39 + _t12, _t39 - _t12, ((_t38) * (_t11) - (_t39 * _t12)), ((_t42) * (_t12) + (((_t38) * (_t13) + (_t13)))), ((_t40) * (_t12) + (((_t43) * (_t13) + (_t13)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t40)))), ((_t39) * (_t13) + (((_t42) * (_t11) + (_t42)))), _t71, ((_t43) * (_t11) + (((_t39) * (_t12) + (_t44 - _t11)))), _t73, _t74, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t71)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_lh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_lh_apiGet_mulAdd_s982e7e6f_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t38, float _t41, float _t48, float _t49, float _t60, float _t65, float _t66, float _t68, float _t69, float _t71, float _t72, float _t73, float _t74, float _sp0, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (((_t38) * (_t11) + (((_t41) * (_t12) + (_t38 + _t11)))) > 0.0f) {
            dest.put(destOffset, _sp0 * _t65);
            dest.put(destOffset + 1, _sp0 * _t49);
            dest.put(destOffset + 2, _sp0 * _t69);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t71));
        } else {
            if (_t38 > Math.max(_t60, _t11)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t72));
                dest.put(destOffset + 1, _sp3 * _t68);
                dest.put(destOffset + 2, _sp3 * _t48);
                dest.put(destOffset + 3, _sp3 * _t65);
            } else {
                if (_t60 > _t11) {
                    dest.put(destOffset, _sp1 * _t68);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t73));
                    dest.put(destOffset + 2, _sp1 * _t66);
                    dest.put(destOffset + 3, _sp1 * _t49);
                } else {
                    dest.put(destOffset, _sp2 * _t48);
                    dest.put(destOffset + 1, _sp2 * _t66);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t74));
                    dest.put(destOffset + 3, _sp2 * _t69);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_rh_unsafe(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_rh_api(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset * 4L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationLookAlong_rh_unsafe(_destBase, _dirBase, _upBase, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            FloatQuatOpsKernelsArray.makeRotationLookAlong_rh(dest.array(), dest.arrayOffset() + destOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.makeRotationLookAlong_rh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(dir.duplicate().position(0)), dirOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 4L, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_rh_apiGet(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationLookAlong_rh_apiGet_fma(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
        return makeRotationLookAlong_rh_apiGet_mulAdd(dest, destOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    /** {@code makeRotationLookAlong_rh_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t21 = java.lang.Math.fma(_t20, _t13, _upy);
        float _t22 = java.lang.Math.fma(_t20, _t12, _upx);
        float _t23 = java.lang.Math.fma(_t20, _t11, _upz);
        float _t30 = java.lang.Math.fma(_t21, _t12, -(_t22 * _t13));
        float _t31 = java.lang.Math.fma(_t22, _t11, -(_t23 * _t12));
        float _t32 = java.lang.Math.fma(_t23, _t13, -(_t21 * _t11));
        float _t35 = java.lang.Math.fma(_t30, _t30, java.lang.Math.fma(_t31, _t31, _t32 * _t32));
        return makeRotationLookAlong_rh_apiGet_fma_sad7c8f1a_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_fma_sad7c8f1a_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t73 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t46 - _t11));
        float _t75 = java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, 1.0f - _t41 + _t11));
        return makeRotationLookAlong_rh_apiGet_fma_sad7c8f1a_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, java.lang.Math.fma(_t40, _t12, _t41 * -_t11), java.lang.Math.fma(_t43, _t13, java.lang.Math.fma(_t42, _t12, _t14)), java.lang.Math.fma(_t41, _t13, java.lang.Math.fma(_t45, _t12, _t14)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t42)), java.lang.Math.fma(_t42, _t11, java.lang.Math.fma(_t44, _t13, _t45)), _t73, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, _t46 + _t11)), _t75, java.lang.Math.fma(_t44, _t12, java.lang.Math.fma(_t41, _t11, 1.0f - _t11 - _t41)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_fma_sad7c8f1a_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (java.lang.Math.fma(_t40, _t12, java.lang.Math.fma(_t43, _t11, _t41 - _t11)) > 0.0f) {
            dest.put(destOffset, _sp0 * _t67);
            dest.put(destOffset + 1, _sp0 * _t51);
            dest.put(destOffset + 2, _sp0 * _t71);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.put(destOffset + 1, _sp3 * _t70);
                dest.put(destOffset + 2, _sp3 * _t50);
                dest.put(destOffset + 3, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.put(destOffset, _sp1 * _t70);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.put(destOffset + 2, _sp1 * _t68);
                    dest.put(destOffset + 3, _sp1 * _t51);
                } else {
                    dest.put(destOffset, _sp2 * _t50);
                    dest.put(destOffset + 1, _sp2 * _t68);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.put(destOffset + 3, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    /** {@code makeRotationLookAlong_rh_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset, float epsilon) {
        float _dirx = dir.get(dirOffset);
        float _diry = dir.get(dirOffset + 1);
        float _dirz = dir.get(dirOffset + 2);
        float _upx = up.get(upOffset);
        float _upy = up.get(upOffset + 1);
        float _upz = up.get(upOffset + 2);
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t20 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t21 = ((_t20) * (_t13) + (_upy));
        float _t22 = ((_t20) * (_t12) + (_upx));
        float _t23 = ((_t20) * (_t11) + (_upz));
        float _t30 = ((_t21) * (_t12) - (_t22 * _t13));
        float _t31 = ((_t22) * (_t11) - (_t23 * _t12));
        float _t32 = ((_t23) * (_t13) - (_t21 * _t11));
        float _t35 = ((_t30) * (_t30) + (((_t31) * (_t31) + (_t32 * _t32))));
        return makeRotationLookAlong_rh_apiGet_mulAdd_s1e9314f5_1(dest, destOffset, epsilon, _upx, _upy, _upz, _t11, _t12, _t13, -_t13, -_t11, _t30, _t31, _t32, _t35, (1.0f / (float) java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_mulAdd_s1e9314f5_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t14, float _t15, float _t30, float _t31, float _t32, float _t35, float _t36) {
        float _t40, _t41, _t42;
        if (_t35 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t40 = _t30 * _t36;
            _t41 = _t32 * _t36;
            _t42 = _t31 * _t36;
        } else {
            _t40 = 0.0f;
            _t41 = 0.0f;
            _t42 = 0.0f;
        }
        float _t43 = -_t41;
        float _t44 = -_t40;
        float _t45 = -_t42;
        float _t46 = 1.0f + _t41;
        float _t73 = ((_t40) * (_t12) + (((_t43) * (_t11) + (_t46 - _t11))));
        float _t75 = ((_t40) * (_t12) + (((_t43) * (_t11) + (1.0f - _t41 + _t11))));
        float _t76 = ((_t44) * (_t12) + (((_t41) * (_t11) + (1.0f - _t11 - _t41))));
        return makeRotationLookAlong_rh_apiGet_mulAdd_s1e9314f5_2(dest, destOffset, _t11, _t12, _t15, _t40, _t41, _t43, _t40 - _t12, _t40 + _t12, ((_t40) * (_t12) - (_t41 * _t11)), ((_t43) * (_t13) + (((_t42) * (_t12) + (_t14)))), ((_t41) * (_t13) + (((_t45) * (_t12) + (_t14)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t42)))), ((_t42) * (_t11) + (((_t44) * (_t13) + (_t45)))), _t73, ((_t44) * (_t12) + (((_t41) * (_t11) + (_t46 + _t11)))), _t75, _t76, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t73)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t75)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t76)));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationLookAlong_rh_apiGet_mulAdd_s1e9314f5_2(java.nio.FloatBuffer dest, int destOffset, float _t11, float _t12, float _t15, float _t40, float _t41, float _t43, float _t50, float _t51, float _t62, float _t67, float _t68, float _t70, float _t71, float _t73, float _t74, float _t75, float _t76, float _sp0, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t74));
        if (((_t40) * (_t12) + (((_t43) * (_t11) + (_t41 - _t11)))) > 0.0f) {
            dest.put(destOffset, _sp0 * _t67);
            dest.put(destOffset + 1, _sp0 * _t51);
            dest.put(destOffset + 2, _sp0 * _t71);
            dest.put(destOffset + 3, 0.5f * (float) java.lang.Math.sqrt(_t73));
        } else {
            if (_t41 > Math.max(_t62, _t15)) {
                dest.put(destOffset, 0.5f * (float) java.lang.Math.sqrt(_t74));
                dest.put(destOffset + 1, _sp3 * _t70);
                dest.put(destOffset + 2, _sp3 * _t50);
                dest.put(destOffset + 3, _sp3 * _t67);
            } else {
                if (_t62 > _t15) {
                    dest.put(destOffset, _sp1 * _t70);
                    dest.put(destOffset + 1, 0.5f * (float) java.lang.Math.sqrt(_t75));
                    dest.put(destOffset + 2, _sp1 * _t68);
                    dest.put(destOffset + 3, _sp1 * _t51);
                } else {
                    dest.put(destOffset, _sp2 * _t50);
                    dest.put(destOffset + 1, _sp2 * _t68);
                    dest.put(destOffset + 2, 0.5f * (float) java.lang.Math.sqrt(_t76));
                    dest.put(destOffset + 3, _sp2 * _t71);
                }
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationTo_unsafe(java.nio.FloatBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationTo_api(java.nio.FloatBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeRotationTo(dest.array(), dest.arrayOffset() + destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationTo_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationTo_apiGet(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationTo_apiGet(java.nio.FloatBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_apiGet_fma(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return makeRotationTo_apiGet_mulAdd(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code makeRotationTo_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_apiGet_fma_sa9469c9_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_fma_sa9469c9_1(java.nio.FloatBuffer dest, int destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > 6.0E-8f) {
            dest.put(destOffset, _t15 * _t32);
            dest.put(destOffset + 1, _t17 * _t32);
            dest.put(destOffset + 2, _t16 * _t32);
            dest.put(destOffset + 3, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.put(destOffset, _t30 * _t13);
                dest.put(destOffset + 1, _t30 * _t19);
                dest.put(destOffset + 2, _t30 * _t18);
                dest.put(destOffset + 3, 0.0f);
            } else {
                dest.put(destOffset, 0.0f);
                dest.put(destOffset + 1, 0.0f);
                dest.put(destOffset + 2, 0.0f);
                dest.put(destOffset + 3, 0.0f);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t16 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_apiGet_mulAdd_s4d9bfcc4_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_mulAdd_s4d9bfcc4_1(java.nio.FloatBuffer dest, int destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > 6.0E-8f) {
            dest.put(destOffset, _t15 * _t32);
            dest.put(destOffset + 1, _t17 * _t32);
            dest.put(destOffset + 2, _t16 * _t32);
            dest.put(destOffset + 3, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.put(destOffset, _t30 * _t13);
                dest.put(destOffset + 1, _t30 * _t19);
                dest.put(destOffset + 2, _t30 * _t18);
                dest.put(destOffset + 3, 0.0f);
            } else {
                dest.put(destOffset, 0.0f);
                dest.put(destOffset + 1, 0.0f);
                dest.put(destOffset + 2, 0.0f);
                dest.put(destOffset + 3, 0.0f);
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationTo_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset * 4L;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, _fromDirBase, _toDirBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationTo_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && fromDir.hasArray() && fromDirOffset >= 0 && fromDirOffset <= fromDir.limit() - 3 && toDir.hasArray() && toDirOffset >= 0 && toDirOffset <= toDir.limit() - 3) {
            FloatQuatOps.makeRotationTo(dest.array(), dest.arrayOffset() + destOffset, fromDir.array(), fromDir.arrayOffset() + fromDirOffset, toDir.array(), toDir.arrayOffset() + toDirOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && fromDir.order() == java.nio.ByteOrder.nativeOrder() && toDir.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.makeRotationTo_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(fromDir.duplicate().position(0)), fromDirOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(toDir.duplicate().position(0)), toDirOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationTo_apiGet(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationTo_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_apiGet_fma(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return makeRotationTo_apiGet_mulAdd(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@code makeRotationTo_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset) {
        float _fromDirx = fromDir.get(fromDirOffset);
        float _fromDiry = fromDir.get(fromDirOffset + 1);
        float _fromDirz = fromDir.get(fromDirOffset + 2);
        float _toDirx = toDir.get(toDirOffset);
        float _toDiry = toDir.get(toDirOffset + 1);
        float _toDirz = toDir.get(toDirOffset + 2);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t15 = java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        float _t16 = java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx);
        float _t17 = java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_apiGet_fma_s7ec39f1a_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_fma_s7ec39f1a_1(java.nio.FloatBuffer dest, int destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > 6.0E-8f) {
            dest.put(destOffset, _t15 * _t32);
            dest.put(destOffset + 1, _t17 * _t32);
            dest.put(destOffset + 2, _t16 * _t32);
            dest.put(destOffset + 3, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.put(destOffset, _t30 * _t13);
                dest.put(destOffset + 1, _t30 * _t19);
                dest.put(destOffset + 2, _t30 * _t18);
                dest.put(destOffset + 3, 0.0f);
            } else {
                dest.put(destOffset, 0.0f);
                dest.put(destOffset + 1, 0.0f);
                dest.put(destOffset + 2, 0.0f);
                dest.put(destOffset + 3, 0.0f);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset) {
        float _fromDirx = fromDir.get(fromDirOffset);
        float _fromDiry = fromDir.get(fromDirOffset + 1);
        float _fromDirz = fromDir.get(fromDirOffset + 2);
        float _toDirx = toDir.get(toDirOffset);
        float _toDiry = toDir.get(toDirOffset + 1);
        float _toDirz = toDir.get(toDirOffset + 2);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t15 = ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry));
        float _t16 = ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx));
        float _t17 = ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_apiGet_mulAdd_s446a20e7_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_mulAdd_s446a20e7_1(java.nio.FloatBuffer dest, int destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > 6.0E-8f) {
            dest.put(destOffset, _t15 * _t32);
            dest.put(destOffset + 1, _t17 * _t32);
            dest.put(destOffset + 2, _t16 * _t32);
            dest.put(destOffset + 3, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.put(destOffset, _t30 * _t13);
                dest.put(destOffset + 1, _t30 * _t19);
                dest.put(destOffset + 2, _t30 * _t18);
                dest.put(destOffset + 3, 0.0f);
            } else {
                dest.put(destOffset, 0.0f);
                dest.put(destOffset + 1, 0.0f);
                dest.put(destOffset + 2, 0.0f);
                dest.put(destOffset + 3, 0.0f);
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationTo_unsafe(java.nio.FloatBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationTo_api(java.nio.FloatBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeRotationTo(dest.array(), dest.arrayOffset() + destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationTo_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationTo_apiGet(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationTo_apiGet(java.nio.FloatBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_apiGet_fma(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return makeRotationTo_apiGet_mulAdd(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code makeRotationTo_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_apiGet_fma_sec70e04f_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_fma_sec70e04f_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > epsilon) {
            dest.put(destOffset, _t15 * _t32);
            dest.put(destOffset + 1, _t17 * _t32);
            dest.put(destOffset + 2, _t16 * _t32);
            dest.put(destOffset + 3, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.put(destOffset, _t30 * _t13);
                dest.put(destOffset + 1, _t30 * _t19);
                dest.put(destOffset + 2, _t30 * _t18);
                dest.put(destOffset + 3, 0.0f);
            } else {
                dest.put(destOffset, 0.0f);
                dest.put(destOffset + 1, 0.0f);
                dest.put(destOffset + 2, 0.0f);
                dest.put(destOffset + 3, 0.0f);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t16 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_apiGet_mulAdd_s821a9088_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_mulAdd_s821a9088_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > epsilon) {
            dest.put(destOffset, _t15 * _t32);
            dest.put(destOffset + 1, _t17 * _t32);
            dest.put(destOffset + 2, _t16 * _t32);
            dest.put(destOffset + 3, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.put(destOffset, _t30 * _t13);
                dest.put(destOffset + 1, _t30 * _t19);
                dest.put(destOffset + 2, _t30 * _t18);
                dest.put(destOffset + 3, 0.0f);
            } else {
                dest.put(destOffset, 0.0f);
                dest.put(destOffset + 1, 0.0f);
                dest.put(destOffset + 2, 0.0f);
                dest.put(destOffset + 3, 0.0f);
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationTo_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset * 4L;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(_destBase, _fromDirBase, _toDirBase, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationTo_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && fromDir.hasArray() && fromDirOffset >= 0 && fromDirOffset <= fromDir.limit() - 3 && toDir.hasArray() && toDirOffset >= 0 && toDirOffset <= toDir.limit() - 3) {
            FloatQuatOps.makeRotationTo(dest.array(), dest.arrayOffset() + destOffset, fromDir.array(), fromDir.arrayOffset() + fromDirOffset, toDir.array(), toDir.arrayOffset() + toDirOffset, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && fromDir.order() == java.nio.ByteOrder.nativeOrder() && toDir.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.makeRotationTo_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(fromDir.duplicate().position(0)), fromDirOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(toDir.duplicate().position(0)), toDirOffset * 4L, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationTo_apiGet(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationTo_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationTo_apiGet_fma(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
        return makeRotationTo_apiGet_mulAdd(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
    }

    /** {@code makeRotationTo_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset, float epsilon) {
        float _fromDirx = fromDir.get(fromDirOffset);
        float _fromDiry = fromDir.get(fromDirOffset + 1);
        float _fromDirz = fromDir.get(fromDirOffset + 2);
        float _toDirx = toDir.get(toDirOffset);
        float _toDiry = toDir.get(toDirOffset + 1);
        float _toDirz = toDir.get(toDirOffset + 2);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t15 = java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        float _t16 = java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx);
        float _t17 = java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_apiGet_fma_se12c1396_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_fma_se12c1396_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > epsilon) {
            dest.put(destOffset, _t15 * _t32);
            dest.put(destOffset + 1, _t17 * _t32);
            dest.put(destOffset + 2, _t16 * _t32);
            dest.put(destOffset + 3, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.put(destOffset, _t30 * _t13);
                dest.put(destOffset + 1, _t30 * _t19);
                dest.put(destOffset + 2, _t30 * _t18);
                dest.put(destOffset + 3, 0.0f);
            } else {
                dest.put(destOffset, 0.0f);
                dest.put(destOffset + 1, 0.0f);
                dest.put(destOffset + 2, 0.0f);
                dest.put(destOffset + 3, 0.0f);
            }
        }
        return dest;
    }

    /** {@code makeRotationTo_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset, float epsilon) {
        float _fromDirx = fromDir.get(fromDirOffset);
        float _fromDiry = fromDir.get(fromDirOffset + 1);
        float _fromDirz = fromDir.get(fromDirOffset + 2);
        float _toDirx = toDir.get(toDirOffset);
        float _toDiry = toDir.get(toDirOffset + 1);
        float _toDirz = toDir.get(toDirOffset + 2);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t15 = ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry));
        float _t16 = ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx));
        float _t17 = ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return makeRotationTo_apiGet_mulAdd_sc5063afd_1(dest, destOffset, epsilon, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code makeRotationTo_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer makeRotationTo_apiGet_mulAdd_sc5063afd_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > epsilon) {
            dest.put(destOffset, _t15 * _t32);
            dest.put(destOffset + 1, _t17 * _t32);
            dest.put(destOffset + 2, _t16 * _t32);
            dest.put(destOffset + 3, _t24 * _t32);
        } else {
            if (_t29 != 0.0f) {
                dest.put(destOffset, _t30 * _t13);
                dest.put(destOffset + 1, _t30 * _t19);
                dest.put(destOffset + 2, _t30 * _t18);
                dest.put(destOffset + 3, 0.0f);
            } else {
                dest.put(destOffset, 0.0f);
                dest.put(destOffset + 1, 0.0f);
                dest.put(destOffset + 2, 0.0f);
                dest.put(destOffset + 3, 0.0f);
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationX_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationX_api(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeRotationX(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, angle);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationX_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationX_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle) {
        float _t0 = 0.5f * angle;
        dest.put(destOffset, Math.sinWithCos(_t0));
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXYZ_api(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeRotationXYZ(dest.array(), dest.arrayOffset() + destOffset, angleX, angleY, angleZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, angleX, angleY, angleZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationXYZ_apiGet(dest, destOffset, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXYZ_apiGet_mulAdd(dest, destOffset, angleX, angleY, angleZ);
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t6;
        float _t12 = _t7 * _t4;
        dest.put(destOffset, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        dest.put(destOffset + 1, java.lang.Math.fma(_t10, _t5, _t9 * -_t8));
        dest.put(destOffset + 2, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        dest.put(destOffset + 3, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        return dest;
    }

    /** {@code makeRotationXYZ_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationXYZ_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t6;
        float _t12 = _t7 * _t4;
        dest.put(destOffset, ((_t9) * (_t5) + (_t10 * _t8)));
        dest.put(destOffset + 1, ((_t10) * (_t5) - (_t9 * _t8)));
        dest.put(destOffset + 2, ((_t11) * (_t5) + (_t12 * _t8)));
        dest.put(destOffset + 3, ((_t12) * (_t5) - (_t11 * _t8)));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXZY_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXZY_api(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeRotationXZY(dest.array(), dest.arrayOffset() + destOffset, angleX, angleZ, angleY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, angleX, angleZ, angleY);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationXZY_apiGet(dest, destOffset, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationXZY_apiGet(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationXZY_apiGet_mulAdd(dest, destOffset, angleX, angleZ, angleY);
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        dest.put(destOffset, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)));
        dest.put(destOffset + 1, java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)));
        dest.put(destOffset + 2, java.lang.Math.fma(_t9, _t8, _t10 * _t5));
        dest.put(destOffset + 3, java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        return dest;
    }

    /** {@code makeRotationXZY_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationXZY_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        dest.put(destOffset, ((_t9) * (_t5) - (_t10 * _t8)));
        dest.put(destOffset + 1, ((_t11) * (_t8) - (_t12 * _t5)));
        dest.put(destOffset + 2, ((_t9) * (_t8) + (_t10 * _t5)));
        dest.put(destOffset + 3, ((_t12) * (_t8) + (_t11 * _t5)));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationY_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationY_api(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeRotationY(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, angle);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationY_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationY_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle) {
        float _t0 = 0.5f * angle;
        dest.put(destOffset, 0.0f);
        dest.put(destOffset + 1, Math.sinWithCos(_t0));
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYXZ_api(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeRotationYXZ(dest.array(), dest.arrayOffset() + destOffset, angleY, angleX, angleZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, angleY, angleX, angleZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationYXZ_apiGet(dest, destOffset, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYXZ_apiGet_mulAdd(dest, destOffset, angleY, angleX, angleZ);
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        dest.put(destOffset, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        dest.put(destOffset + 1, java.lang.Math.fma(_t10, _t5, -(_t9 * _t8)));
        dest.put(destOffset + 2, java.lang.Math.fma(_t11, _t8, -(_t12 * _t5)));
        dest.put(destOffset + 3, java.lang.Math.fma(_t12, _t8, _t11 * _t5));
        return dest;
    }

    /** {@code makeRotationYXZ_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationYXZ_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        dest.put(destOffset, ((_t9) * (_t5) + (_t10 * _t8)));
        dest.put(destOffset + 1, ((_t10) * (_t5) - (_t9 * _t8)));
        dest.put(destOffset + 2, ((_t11) * (_t8) - (_t12 * _t5)));
        dest.put(destOffset + 3, ((_t12) * (_t8) + (_t11 * _t5)));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYZX_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYZX_api(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeRotationYZX(dest.array(), dest.arrayOffset() + destOffset, angleY, angleZ, angleX);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, angleY, angleZ, angleX);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationYZX_apiGet(dest, destOffset, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationYZX_apiGet(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationYZX_apiGet_mulAdd(dest, destOffset, angleY, angleZ, angleX);
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        dest.put(destOffset, java.lang.Math.fma(_t9, _t5, _t10 * _t8));
        dest.put(destOffset + 1, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        dest.put(destOffset + 2, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        dest.put(destOffset + 3, java.lang.Math.fma(_t10, _t5, _t9 * -_t8));
        return dest;
    }

    /** {@code makeRotationYZX_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationYZX_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        dest.put(destOffset, ((_t9) * (_t5) + (_t10 * _t8)));
        dest.put(destOffset + 1, ((_t11) * (_t5) + (_t12 * _t8)));
        dest.put(destOffset + 2, ((_t12) * (_t5) - (_t11 * _t8)));
        dest.put(destOffset + 3, ((_t10) * (_t5) - (_t9 * _t8)));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZ_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationZ_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZ_api(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeRotationZ(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, angle);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationZ_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZ_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle) {
        float _t0 = 0.5f * angle;
        dest.put(destOffset, 0.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, Math.sinWithCos(_t0));
        dest.put(destOffset + 3, Math.cosWithSin(_t0));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZXY_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZXY_api(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeRotationZXY(dest.array(), dest.arrayOffset() + destOffset, angleZ, angleX, angleY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, angleZ, angleX, angleY);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationZXY_apiGet(dest, destOffset, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZXY_apiGet(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZXY_apiGet_mulAdd(dest, destOffset, angleZ, angleX, angleY);
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t6;
        float _t12 = _t7 * _t4;
        dest.put(destOffset, java.lang.Math.fma(_t9, _t5, _t10 * -_t8));
        dest.put(destOffset + 1, java.lang.Math.fma(_t11, _t5, _t12 * _t8));
        dest.put(destOffset + 2, java.lang.Math.fma(_t9, _t8, _t10 * _t5));
        dest.put(destOffset + 3, java.lang.Math.fma(_t12, _t5, _t11 * -_t8));
        return dest;
    }

    /** {@code makeRotationZXY_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationZXY_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t6;
        float _t12 = _t7 * _t4;
        dest.put(destOffset, ((_t9) * (_t5) - (_t10 * _t8)));
        dest.put(destOffset + 1, ((_t11) * (_t5) + (_t12 * _t8)));
        dest.put(destOffset + 2, ((_t9) * (_t8) + (_t10 * _t5)));
        dest.put(destOffset + 3, ((_t12) * (_t5) - (_t11 * _t8)));
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZYX_unsafe(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        FloatQuatOpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZYX_api(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            FloatQuatOps.makeRotationZYX(dest.array(), dest.arrayOffset() + destOffset, angleZ, angleY, angleX);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            FloatQuatOpsKernelsSegment.makeRotationZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, angleZ, angleY, angleX);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.makeRotationZYX_apiGet(dest, destOffset, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotationZYX_apiGet(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return makeRotationZYX_apiGet_mulAdd(dest, destOffset, angleZ, angleY, angleX);
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        dest.put(destOffset, java.lang.Math.fma(_t9, _t5, -(_t10 * _t8)));
        dest.put(destOffset + 1, java.lang.Math.fma(_t11, _t8, _t12 * _t5));
        dest.put(destOffset + 2, java.lang.Math.fma(_t12, _t8, -(_t11 * _t5)));
        dest.put(destOffset + 3, java.lang.Math.fma(_t10, _t5, _t9 * _t8));
        return dest;
    }

    /** {@code makeRotationZYX_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer makeRotationZYX_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        dest.put(destOffset, ((_t9) * (_t5) - (_t10 * _t8)));
        dest.put(destOffset + 1, ((_t11) * (_t8) + (_t12 * _t5)));
        dest.put(destOffset + 2, ((_t12) * (_t8) - (_t11 * _t5)));
        dest.put(destOffset + 3, ((_t10) * (_t5) + (_t9 * _t8)));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.preRotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.preRotateX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.preRotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.put(destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t2));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t1, -(_selfz * _t2)));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfy, _t2, _selfz * _t1));
            dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t1, -(_selfx * _t2)));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.put(destOffset, ((_selfx) * (_t1) + (_selfw * _t2)));
            dest.put(destOffset + 1, ((_selfy) * (_t1) - (_selfz * _t2)));
            dest.put(destOffset + 2, ((_selfy) * (_t2) + (_selfz * _t1)));
            dest.put(destOffset + 3, ((_selfw) * (_t1) - (_selfx * _t2)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer preRotateY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.preRotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.preRotateY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.preRotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.put(destOffset, java.lang.Math.fma(_selfx, _t1, _selfz * _t2));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t1, _selfw * _t2));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t1, -(_selfx * _t2)));
            dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t1, -(_selfy * _t2)));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.put(destOffset, ((_selfx) * (_t1) + (_selfz * _t2)));
            dest.put(destOffset + 1, ((_selfy) * (_t1) + (_selfw * _t2)));
            dest.put(destOffset + 2, ((_selfz) * (_t1) - (_selfx * _t2)));
            dest.put(destOffset + 3, ((_selfw) * (_t1) - (_selfy * _t2)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer preRotateZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.preRotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.preRotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.preRotateZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.preRotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.put(destOffset, java.lang.Math.fma(_selfx, _t1, -(_selfy * _t2)));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfx, _t2, _selfy * _t1));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t1, _selfw * _t2));
            dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t1, -(_selfz * _t2)));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.put(destOffset, ((_selfx) * (_t1) - (_selfy * _t2)));
            dest.put(destOffset + 1, ((_selfx) * (_t2) + (_selfy * _t1)));
            dest.put(destOffset + 2, ((_selfz) * (_t1) + (_selfw * _t2)));
            dest.put(destOffset + 3, ((_selfw) * (_t1) - (_selfz * _t2)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer rotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle, axisX, axisY, axisZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_apiGet_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t3) + java.lang.Math.fma(_selfy, _t4, -(_selfz * _t5)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t1, _selfz * _t3) + java.lang.Math.fma(_selfw, _t5, -_selfx * _t4));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t5, _selfw * _t4) + java.lang.Math.fma(_selfz, _t1, -(_selfy * _t3)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t1, -_selfx * _t3) - java.lang.Math.fma(_selfy, _t5, _selfz * _t4));
        return dest;
    }

    /** {@code rotateAxis_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateAxis_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axisX * _t2;
        float _t4 = axisZ * _t2;
        float _t5 = axisY * _t2;
        dest.put(destOffset, ((_selfx) * (_t1) + (_selfw * _t3)) + ((_selfy) * (_t4) - (_selfz * _t5)));
        dest.put(destOffset + 1, ((_selfy) * (_t1) + (_selfz * _t3)) + ((_selfw) * (_t5) - (_selfx * _t4)));
        dest.put(destOffset + 2, ((_selfx) * (_t5) + (_selfw * _t4)) + ((_selfz) * (_t1) - (_selfy * _t3)));
        dest.put(destOffset + 3, ((_selfw) * (_t1) - (_selfx * _t3)) - ((_selfy) * (_t5) + (_selfz * _t4)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            FloatQuatOps.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(axis.duplicate().position(0)), axisOffset * 4L, angle);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAxis_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_apiGet_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axis.get(axisOffset) * _t2;
        float _t4 = axis.get(axisOffset + 2) * _t2;
        float _t5 = axis.get(axisOffset + 1) * _t2;
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t3) + java.lang.Math.fma(_selfy, _t4, -(_selfz * _t5)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t1, _selfz * _t3) + java.lang.Math.fma(_selfw, _t5, -_selfx * _t4));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t5, _selfw * _t4) + java.lang.Math.fma(_selfz, _t1, -(_selfy * _t3)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t1, -_selfx * _t3) - java.lang.Math.fma(_selfy, _t5, _selfz * _t4));
        return dest;
    }

    /** {@code rotateAxis_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateAxis_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t0 = 0.5f * angle;
        float _t1 = Math.cosWithSin(_t0);
        float _t2 = Math.sinWithCos(_t0);
        float _t3 = axis.get(axisOffset) * _t2;
        float _t4 = axis.get(axisOffset + 2) * _t2;
        float _t5 = axis.get(axisOffset + 1) * _t2;
        dest.put(destOffset, ((_selfx) * (_t1) + (_selfw * _t3)) + ((_selfy) * (_t4) - (_selfz * _t5)));
        dest.put(destOffset + 1, ((_selfy) * (_t1) + (_selfz * _t3)) + ((_selfw) * (_t5) - (_selfx * _t4)));
        dest.put(destOffset + 2, ((_selfx) * (_t5) + (_selfw * _t4)) + ((_selfz) * (_t1) - (_selfy * _t3)));
        dest.put(destOffset + 3, ((_selfw) * (_t1) - (_selfx * _t3)) - ((_selfy) * (_t5) + (_selfz * _t4)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateTo_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTo_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateTo(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateTo_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateTo_apiGet(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTo_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_apiGet_fma(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return rotateTo_apiGet_mulAdd(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@code rotateTo_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_apiGet_fma_s452cfd26_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_fma_s452cfd26_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_apiGet_mulAdd_s1adbc48b_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_mulAdd_s1adbc48b_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        dest.put(destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.put(destOffset + 1, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.put(destOffset + 2, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.put(destOffset + 3, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateTo_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset * 4L;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, _fromDirBase, _toDirBase);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTo_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && fromDir.hasArray() && fromDirOffset >= 0 && fromDirOffset <= fromDir.limit() - 3 && toDir.hasArray() && toDirOffset >= 0 && toDirOffset <= toDir.limit() - 3) {
            FloatQuatOps.rotateTo(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, fromDir.array(), fromDir.arrayOffset() + fromDirOffset, toDir.array(), toDir.arrayOffset() + toDirOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && fromDir.order() == java.nio.ByteOrder.nativeOrder() && toDir.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateTo_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(fromDir.duplicate().position(0)), fromDirOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(toDir.duplicate().position(0)), toDirOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateTo_apiGet(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTo_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_apiGet_fma(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return rotateTo_apiGet_mulAdd(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@code rotateTo_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset) {
        float _fromDirx = fromDir.get(fromDirOffset);
        float _fromDiry = fromDir.get(fromDirOffset + 1);
        float _fromDirz = fromDir.get(fromDirOffset + 2);
        float _toDirx = toDir.get(toDirOffset);
        float _toDiry = toDir.get(toDirOffset + 1);
        float _toDirz = toDir.get(toDirOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_apiGet_fma_s347c0bc1_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_fma_s347c0bc1_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset) {
        float _fromDirx = fromDir.get(fromDirOffset);
        float _fromDiry = fromDir.get(fromDirOffset + 1);
        float _fromDirz = fromDir.get(fromDirOffset + 2);
        float _toDirx = toDir.get(toDirOffset);
        float _toDiry = toDir.get(toDirOffset + 1);
        float _toDirz = toDir.get(toDirOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_apiGet_mulAdd_s18d087e4_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx)), ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry)), ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz)), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_mulAdd_s18d087e4_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        dest.put(destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.put(destOffset + 1, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.put(destOffset + 2, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.put(destOffset + 3, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateTo_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTo_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateTo(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateTo_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateTo_apiGet(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTo_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_apiGet_fma(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        return rotateTo_apiGet_mulAdd(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
    }

    /** {@code rotateTo_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t16 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_apiGet_fma_s5ac624f2_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_fma_s5ac624f2_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t16 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_apiGet_mulAdd_s1d7d2371_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17)))))))));
    }

    /** Piece 2 of {@code rotateTo_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_mulAdd_s1d7d2371_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        dest.put(destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.put(destOffset + 1, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.put(destOffset + 2, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.put(destOffset + 3, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateTo_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _fromDirBase = UnsafeOpsHolder.U.getLong(fromDir, UnsafeCopy.BB_ADDRESS_OFFSET) + fromDirOffset * 4L;
        long _toDirBase = UnsafeOpsHolder.U.getLong(toDir, UnsafeCopy.BB_ADDRESS_OFFSET) + toDirOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateTo_unsafe(_destBase, _srcBase, _fromDirBase, _toDirBase, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTo_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset, float epsilon) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && fromDir.hasArray() && fromDirOffset >= 0 && fromDirOffset <= fromDir.limit() - 3 && toDir.hasArray() && toDirOffset >= 0 && toDirOffset <= toDir.limit() - 3) {
            FloatQuatOps.rotateTo(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, fromDir.array(), fromDir.arrayOffset() + fromDirOffset, toDir.array(), toDir.arrayOffset() + toDirOffset, epsilon);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && fromDir.order() == java.nio.ByteOrder.nativeOrder() && toDir.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateTo_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(fromDir.duplicate().position(0)), fromDirOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(toDir.duplicate().position(0)), toDirOffset * 4L, epsilon);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateTo_apiGet(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
        return dest;
    }

    public static java.nio.FloatBuffer rotateTo_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateTo_apiGet_fma(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
        return rotateTo_apiGet_mulAdd(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset, epsilon);
    }

    /** {@code rotateTo_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset, float epsilon) {
        float _fromDirx = fromDir.get(fromDirOffset);
        float _fromDiry = fromDir.get(fromDirOffset + 1);
        float _fromDirz = fromDir.get(fromDirOffset + 2);
        float _toDirx = toDir.get(toDirOffset);
        float _toDiry = toDir.get(toDirOffset + 1);
        float _toDirz = toDir.get(toDirOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_apiGet_fma_s807593a7_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, java.lang.Math.fma(_fromDirx, _toDiry, -_fromDiry * _toDirx), java.lang.Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry)), java.lang.Math.fma(_fromDirz, _toDirx, -_fromDirx * _toDirz), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_fma_s807593a7_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
        float _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t44, _selfw * _t45) + java.lang.Math.fma(_selfy, _t46, -(_selfz * _t47)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t44, _selfz * _t45) + java.lang.Math.fma(_selfw, _t47, -_selfx * _t46));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t47, _selfw * _t46) + java.lang.Math.fma(_selfz, _t44, -(_selfy * _t45)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t44, -_selfx * _t45) - java.lang.Math.fma(_selfy, _t47, _selfz * _t46));
        return dest;
    }

    /** {@code rotateTo_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset, float epsilon) {
        float _fromDirx = fromDir.get(fromDirOffset);
        float _fromDiry = fromDir.get(fromDirOffset + 1);
        float _fromDirz = fromDir.get(fromDirOffset + 2);
        float _toDirx = toDir.get(toDirOffset);
        float _toDiry = toDir.get(toDirOffset + 1);
        float _toDirz = toDir.get(toDirOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(_fromDirz) < java.lang.Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        return rotateTo_apiGet_mulAdd_s7efd9628_1(dest, destOffset, epsilon, _selfx, _selfy, _selfz, _selfw, _t13, _t18, _t19, ((_fromDirx) * (_toDiry) - (_fromDiry * _toDirx)), ((_fromDiry) * (_toDirz) - (_fromDirz * _toDiry)), ((_fromDirz) * (_toDirx) - (_fromDirx * _toDirz)), _t23, 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code rotateTo_apiGet_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateTo_apiGet_mulAdd_s7efd9628_1(java.nio.FloatBuffer dest, int destOffset, float epsilon, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t23, float _t24, float _t29, float _t30) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
        float _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        dest.put(destOffset, ((_selfx) * (_t44) + (_selfw * _t45)) + ((_selfy) * (_t46) - (_selfz * _t47)));
        dest.put(destOffset + 1, ((_selfy) * (_t44) + (_selfz * _t45)) + ((_selfw) * (_t47) - (_selfx * _t46)));
        dest.put(destOffset + 2, ((_selfx) * (_t47) + (_selfw * _t46)) + ((_selfz) * (_t44) - (_selfy * _t45)));
        dest.put(destOffset + 3, ((_selfw) * (_t44) - (_selfx * _t45)) - ((_selfy) * (_t47) + (_selfz * _t46)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.put(destOffset, java.lang.Math.fma(_selfx, _t1, _selfw * _t2));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t1, _selfz * _t2));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t1, -(_selfy * _t2)));
            dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t1, -(_selfx * _t2)));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.put(destOffset, ((_selfx) * (_t1) + (_selfw * _t2)));
            dest.put(destOffset + 1, ((_selfy) * (_t1) + (_selfz * _t2)));
            dest.put(destOffset + 2, ((_selfz) * (_t1) - (_selfy * _t2)));
            dest.put(destOffset + 3, ((_selfw) * (_t1) - (_selfx * _t2)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer rotateXYZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXYZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleX, angleY, angleZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angleX, angleY, angleZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateXYZ_apiGet(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXYZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXYZ_apiGet_fma(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return rotateXYZ_apiGet_mulAdd(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@code rotateXYZ_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateXYZ_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t12, _t5, _t11 * -_t8);
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t21, _selfw * _t19) + java.lang.Math.fma(_selfy, _t20, -(_selfz * _t22)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t21, _selfz * _t19) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t20));
        return rotateXYZ_apiGet_fma_s6a859ee3_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXYZ_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateXYZ_apiGet_fma_s6a859ee3_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t22, _selfw * _t20) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t21, -_selfx * _t19) - java.lang.Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    /** {@code rotateXYZ_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateXYZ_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t12) * (_t5) - (_t11 * _t8));
        dest.put(destOffset, ((_selfx) * (_t21) + (_selfw * _t19)) + ((_selfy) * (_t20) - (_selfz * _t22)));
        dest.put(destOffset + 1, ((_selfy) * (_t21) + (_selfz * _t19)) + ((_selfw) * (_t22) - (_selfx * _t20)));
        dest.put(destOffset + 2, ((_selfx) * (_t22) + (_selfw * _t20)) + ((_selfz) * (_t21) - (_selfy * _t19)));
        dest.put(destOffset + 3, ((_selfw) * (_t21) - (_selfx * _t19)) - ((_selfy) * (_t22) + (_selfz * _t20)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateXZY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXZY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleX, angleZ, angleY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angleX, angleZ, angleY);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateXZY_apiGet(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateXZY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateXZY_apiGet_fma(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return rotateXZY_apiGet_mulAdd(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@code rotateXZY_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateXZY_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t5, _t12 * _t8);
        float _t21 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        float _t22 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t19, _selfw * _t21) + java.lang.Math.fma(_selfy, _t20, -(_selfz * _t22)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t19, _selfz * _t21) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t20));
        return rotateXZY_apiGet_fma_scfa03965_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateXZY_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateXZY_apiGet_fma_scfa03965_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t22, _selfw * _t20) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t19, -_selfx * _t21) - java.lang.Math.fma(_selfy, _t22, _selfz * _t20));
        return dest;
    }

    /** {@code rotateXZY_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateXZY_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t5) + (_t12 * _t8));
        float _t21 = ((_t11) * (_t8) - (_t12 * _t5));
        float _t22 = ((_t10) * (_t5) - (_t9 * _t8));
        dest.put(destOffset, ((_selfx) * (_t19) + (_selfw * _t21)) + ((_selfy) * (_t20) - (_selfz * _t22)));
        dest.put(destOffset + 1, ((_selfy) * (_t19) + (_selfz * _t21)) + ((_selfw) * (_t22) - (_selfx * _t20)));
        dest.put(destOffset + 2, ((_selfx) * (_t22) + (_selfw * _t20)) + ((_selfz) * (_t19) - (_selfy * _t21)));
        dest.put(destOffset + 3, ((_selfw) * (_t19) - (_selfx * _t21)) - ((_selfy) * (_t22) + (_selfz * _t20)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.put(destOffset, java.lang.Math.fma(_selfx, _t1, -(_selfz * _t2)));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t1, _selfw * _t2));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t2, _selfz * _t1));
            dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t1, -(_selfy * _t2)));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.put(destOffset, ((_selfx) * (_t1) - (_selfz * _t2)));
            dest.put(destOffset + 1, ((_selfy) * (_t1) + (_selfw * _t2)));
            dest.put(destOffset + 2, ((_selfx) * (_t2) + (_selfz * _t1)));
            dest.put(destOffset + 3, ((_selfw) * (_t1) - (_selfy * _t2)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer rotateYXZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYXZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleY, angleX, angleZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angleY, angleX, angleZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateYXZ_apiGet(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYXZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYXZ_apiGet_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_apiGet_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@code rotateYXZ_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateYXZ_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t12, _t8, -(_t11 * _t5));
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t19, _selfw * _t20) + java.lang.Math.fma(_selfy, _t21, -(_selfz * _t22)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t19, _selfz * _t20) + java.lang.Math.fma(_selfw, _t22, -_selfx * _t21));
        return rotateYXZ_apiGet_fma_s1c881031_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYXZ_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateYXZ_apiGet_fma_s1c881031_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t22, _selfw * _t21) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t20)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t19, -_selfx * _t20) - java.lang.Math.fma(_selfy, _t22, _selfz * _t21));
        return dest;
    }

    /** {@code rotateYXZ_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateYXZ_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t3 * _t7;
        float _t12 = _t4 * _t6;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t12) * (_t8) - (_t11 * _t5));
        dest.put(destOffset, ((_selfx) * (_t19) + (_selfw * _t20)) + ((_selfy) * (_t21) - (_selfz * _t22)));
        dest.put(destOffset + 1, ((_selfy) * (_t19) + (_selfz * _t20)) + ((_selfw) * (_t22) - (_selfx * _t21)));
        dest.put(destOffset + 2, ((_selfx) * (_t22) + (_selfw * _t21)) + ((_selfz) * (_t19) - (_selfy * _t20)));
        dest.put(destOffset + 3, ((_selfw) * (_t19) - (_selfx * _t20)) - ((_selfy) * (_t22) + (_selfz * _t21)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateYZX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYZX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleY, angleZ, angleX);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angleY, angleZ, angleX);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateYZX_apiGet(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer rotateYZX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateYZX_apiGet_fma(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return rotateYZX_apiGet_mulAdd(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@code rotateYZX_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateYZX_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t5, _t11 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t21, _selfw * _t19) + java.lang.Math.fma(_selfy, _t22, -(_selfz * _t20)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t21, _selfz * _t19) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t22));
        return rotateYZX_apiGet_fma_sebd9229_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateYZX_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateYZX_apiGet_fma_sebd9229_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t20, _selfw * _t22) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t19)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t21, -_selfx * _t19) - java.lang.Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    /** {@code rotateYZX_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateYZX_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t3;
        float _t12 = _t6 * _t4;
        float _t19 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t20 = ((_t12) * (_t5) + (_t11 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        dest.put(destOffset, ((_selfx) * (_t21) + (_selfw * _t19)) + ((_selfy) * (_t22) - (_selfz * _t20)));
        dest.put(destOffset + 1, ((_selfy) * (_t21) + (_selfz * _t19)) + ((_selfw) * (_t20) - (_selfx * _t22)));
        dest.put(destOffset + 2, ((_selfx) * (_t20) + (_selfw * _t22)) + ((_selfz) * (_t21) - (_selfy * _t19)));
        dest.put(destOffset + 3, ((_selfw) * (_t21) - (_selfx * _t19)) - ((_selfy) * (_t20) + (_selfz * _t22)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZ_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.put(destOffset, java.lang.Math.fma(_selfx, _t1, _selfy * _t2));
            dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t1, -(_selfx * _t2)));
            dest.put(destOffset + 2, java.lang.Math.fma(_selfz, _t1, _selfw * _t2));
            dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t1, -(_selfz * _t2)));
            return dest;
        } else {
            float _selfx = src.get(srcOffset);
            float _selfy = src.get(srcOffset + 1);
            float _selfz = src.get(srcOffset + 2);
            float _selfw = src.get(srcOffset + 3);
            float _t0 = 0.5f * angle;
            float _t1 = Math.cosWithSin(_t0);
            float _t2 = Math.sinWithCos(_t0);
            dest.put(destOffset, ((_selfx) * (_t1) + (_selfy * _t2)));
            dest.put(destOffset + 1, ((_selfy) * (_t1) - (_selfx * _t2)));
            dest.put(destOffset + 2, ((_selfz) * (_t1) + (_selfw * _t2)));
            dest.put(destOffset + 3, ((_selfw) * (_t1) - (_selfz * _t2)));
            return dest;
        }
    }

    public static java.nio.FloatBuffer rotateZXY_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZXY_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleZ, angleX, angleY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angleZ, angleX, angleY);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateZXY_apiGet(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZXY_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZXY_apiGet_fma(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return rotateZXY_apiGet_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@code rotateZXY_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateZXY_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = java.lang.Math.fma(_t11, _t8, _t12 * _t5);
        float _t20 = java.lang.Math.fma(_t10, _t5, _t9 * _t8);
        float _t21 = java.lang.Math.fma(_t9, _t5, _t10 * -_t8);
        float _t22 = java.lang.Math.fma(_t11, _t5, _t12 * -_t8);
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t21, _selfw * _t22) + java.lang.Math.fma(_selfy, _t19, -(_selfz * _t20)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t21, _selfz * _t22) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t19));
        return rotateZXY_apiGet_fma_s6581c4d9_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZXY_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateZXY_apiGet_fma_s6581c4d9_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t20, _selfw * _t19) + java.lang.Math.fma(_selfz, _t21, -(_selfy * _t22)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t21, -_selfx * _t22) - java.lang.Math.fma(_selfy, _t20, _selfz * _t19));
        return dest;
    }

    /** {@code rotateZXY_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateZXY_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = Math.cosWithSin(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t0);
        float _t7 = Math.sinWithCos(_t1);
        float _t8 = Math.sinWithCos(_t2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t6 * _t4;
        float _t12 = _t7 * _t3;
        float _t19 = ((_t11) * (_t8) + (_t12 * _t5));
        float _t20 = ((_t10) * (_t5) + (_t9 * _t8));
        float _t21 = ((_t9) * (_t5) - (_t10 * _t8));
        float _t22 = ((_t11) * (_t5) - (_t12 * _t8));
        dest.put(destOffset, ((_selfx) * (_t21) + (_selfw * _t22)) + ((_selfy) * (_t19) - (_selfz * _t20)));
        dest.put(destOffset + 1, ((_selfy) * (_t21) + (_selfz * _t22)) + ((_selfw) * (_t20) - (_selfx * _t19)));
        dest.put(destOffset + 2, ((_selfx) * (_t20) + (_selfw * _t19)) + ((_selfz) * (_t21) - (_selfy * _t22)));
        dest.put(destOffset + 3, ((_selfw) * (_t21) - (_selfx * _t22)) - ((_selfy) * (_t20) + (_selfz * _t19)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateZYX_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZYX_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.rotateZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleZ, angleY, angleX);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.rotateZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angleZ, angleY, angleX);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.rotateZYX_apiGet(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.FloatBuffer rotateZYX_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return rotateZYX_apiGet_fma(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return rotateZYX_apiGet_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@code rotateZYX_apiGet} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateZYX_apiGet_fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = java.lang.Math.fma(_t9, _t5, _t10 * _t8);
        float _t20 = java.lang.Math.fma(_t12, _t8, _t11 * _t5);
        float _t21 = java.lang.Math.fma(_t10, _t5, -(_t9 * _t8));
        float _t22 = java.lang.Math.fma(_t11, _t8, -(_t12 * _t5));
        dest.put(destOffset, java.lang.Math.fma(_selfx, _t19, _selfw * _t21) + java.lang.Math.fma(_selfy, _t22, -(_selfz * _t20)));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfy, _t19, _selfz * _t21) + java.lang.Math.fma(_selfw, _t20, -_selfx * _t22));
        return rotateZYX_apiGet_fma_sa59f499f_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t19, _t20, _t21, _t22);
    }

    /** Piece 2 of {@code rotateZYX_apiGet_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer rotateZYX_apiGet_fma_sa59f499f_1(java.nio.FloatBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t19, float _t20, float _t21, float _t22) {
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t20, _selfw * _t22) + java.lang.Math.fma(_selfz, _t19, -(_selfy * _t21)));
        dest.put(destOffset + 3, java.lang.Math.fma(_selfw, _t19, -_selfx * _t21) - java.lang.Math.fma(_selfy, _t20, _selfz * _t22));
        return dest;
    }

    /** {@code rotateZYX_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer rotateZYX_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.sinWithCos(_t1);
        float _t5 = Math.sinWithCos(_t2);
        float _t6 = Math.cosWithSin(_t0);
        float _t7 = Math.cosWithSin(_t1);
        float _t8 = Math.cosWithSin(_t2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t3 * _t7;
        float _t19 = ((_t9) * (_t5) + (_t10 * _t8));
        float _t20 = ((_t12) * (_t8) + (_t11 * _t5));
        float _t21 = ((_t10) * (_t5) - (_t9 * _t8));
        float _t22 = ((_t11) * (_t8) - (_t12 * _t5));
        dest.put(destOffset, ((_selfx) * (_t19) + (_selfw * _t21)) + ((_selfy) * (_t22) - (_selfz * _t20)));
        dest.put(destOffset + 1, ((_selfy) * (_t19) + (_selfz * _t21)) + ((_selfw) * (_t20) - (_selfx * _t22)));
        dest.put(destOffset + 2, ((_selfx) * (_t20) + (_selfw * _t22)) + ((_selfz) * (_t19) - (_selfy * _t21)));
        dest.put(destOffset + 3, ((_selfw) * (_t19) - (_selfx * _t21)) - ((_selfy) * (_t20) + (_selfz * _t22)));
        return dest;
    }

    public static java.nio.FloatBuffer transform_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer transform_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.transform(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.transform_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, vX, vY, vZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.transform_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer transform_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transform_apiGet_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, vY, -(_selfy * vX));
        float _t10 = 2.0f * java.lang.Math.fma(_selfz, vX, -(_selfx * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(_selfy, vZ, -(_selfz * vY));
        dest.put(destOffset, java.lang.Math.fma(_selfy, _t9, java.lang.Math.fma(-_selfz, _t10, java.lang.Math.fma(_selfw, _t11, vX))));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfz, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, vY))));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfy, _t11, java.lang.Math.fma(_selfw, _t9, vZ))));
        return dest;
    }

    /** {@code transform_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer transform_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (vY) - (_selfy * vX));
        float _t10 = 2.0f * ((_selfz) * (vX) - (_selfx * vZ));
        float _t11 = 2.0f * ((_selfy) * (vZ) - (_selfz * vY));
        dest.put(destOffset, ((_selfy) * (_t9) + (((((_selfw) * (_t11) + (vX))) - (_selfz) * (_t10)))));
        dest.put(destOffset + 1, ((_selfz) * (_t11) + (((((_selfw) * (_t10) + (vY))) - (_selfx) * (_t9)))));
        dest.put(destOffset + 2, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (vZ))) - (_selfy) * (_t11)))));
        return dest;
    }

    public static java.nio.FloatBuffer transform_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        FloatQuatOpsKernelsAddress.transform_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer transform_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            FloatQuatOps.transform(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.transform_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.transform_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer transform_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transform_apiGet_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _vy, -(_selfy * _vx));
        float _t10 = 2.0f * java.lang.Math.fma(_selfz, _vx, -(_selfx * _vz));
        float _t11 = 2.0f * java.lang.Math.fma(_selfy, _vz, -(_selfz * _vy));
        dest.put(destOffset, java.lang.Math.fma(_selfy, _t9, java.lang.Math.fma(-_selfz, _t10, java.lang.Math.fma(_selfw, _t11, _vx))));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfz, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, _vy))));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfy, _t11, java.lang.Math.fma(_selfw, _t9, _vz))));
        return dest;
    }

    /** {@code transform_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer transform_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_vy) - (_selfy * _vx));
        float _t10 = 2.0f * ((_selfz) * (_vx) - (_selfx * _vz));
        float _t11 = 2.0f * ((_selfy) * (_vz) - (_selfz * _vy));
        dest.put(destOffset, ((_selfy) * (_t9) + (((((_selfw) * (_t11) + (_vx))) - (_selfz) * (_t10)))));
        dest.put(destOffset + 1, ((_selfz) * (_t11) + (((((_selfw) * (_t10) + (_vy))) - (_selfx) * (_t9)))));
        dest.put(destOffset + 2, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (_vz))) - (_selfy) * (_t11)))));
        return dest;
    }

    public static java.nio.FloatBuffer transformInverse_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        FloatQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer transformInverse_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            FloatQuatOps.transformInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.transformInverse_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, vX, vY, vZ);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.transformInverse_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer transformInverse_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformInverse_apiGet_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, vZ, -(_selfz * vX));
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, vX, -(_selfx * vY));
        float _t11 = 2.0f * java.lang.Math.fma(_selfz, vY, -(_selfy * vZ));
        dest.put(destOffset, java.lang.Math.fma(_selfz, _t9, java.lang.Math.fma(-_selfy, _t10, java.lang.Math.fma(_selfw, _t11, vX))));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfz, _t11, java.lang.Math.fma(_selfw, _t9, vY))));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfy, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, vZ))));
        return dest;
    }

    /** {@code transformInverse_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer transformInverse_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (vZ) - (_selfz * vX));
        float _t10 = 2.0f * ((_selfy) * (vX) - (_selfx * vY));
        float _t11 = 2.0f * ((_selfz) * (vY) - (_selfy * vZ));
        dest.put(destOffset, ((_selfz) * (_t9) + (((((_selfw) * (_t11) + (vX))) - (_selfy) * (_t10)))));
        dest.put(destOffset + 1, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (vY))) - (_selfz) * (_t11)))));
        dest.put(destOffset + 2, ((_selfy) * (_t11) + (((((_selfw) * (_t10) + (vZ))) - (_selfx) * (_t9)))));
        return dest;
    }

    public static java.nio.FloatBuffer transformInverse_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        FloatQuatOpsKernelsAddress.transformInverse_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer transformInverse_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            FloatQuatOps.transformInverse(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            FloatQuatOpsKernelsSegment.transformInverse_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        FloatQuatOpsKernelsTypedBuffer.transformInverse_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer transformInverse_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return transformInverse_apiGet_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * java.lang.Math.fma(_selfx, _vz, -(_selfz * _vx));
        float _t10 = 2.0f * java.lang.Math.fma(_selfy, _vx, -(_selfx * _vy));
        float _t11 = 2.0f * java.lang.Math.fma(_selfz, _vy, -(_selfy * _vz));
        dest.put(destOffset, java.lang.Math.fma(_selfz, _t9, java.lang.Math.fma(-_selfy, _t10, java.lang.Math.fma(_selfw, _t11, _vx))));
        dest.put(destOffset + 1, java.lang.Math.fma(_selfx, _t10, java.lang.Math.fma(-_selfz, _t11, java.lang.Math.fma(_selfw, _t9, _vy))));
        dest.put(destOffset + 2, java.lang.Math.fma(_selfy, _t11, java.lang.Math.fma(-_selfx, _t9, java.lang.Math.fma(_selfw, _t10, _vz))));
        return dest;
    }

    /** {@code transformInverse_apiGet} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.FloatBuffer transformInverse_apiGet_mulAdd(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        float _selfx = src.get(srcOffset);
        float _selfy = src.get(srcOffset + 1);
        float _selfz = src.get(srcOffset + 2);
        float _selfw = src.get(srcOffset + 3);
        float _t9 = 2.0f * ((_selfx) * (_vz) - (_selfz * _vx));
        float _t10 = 2.0f * ((_selfy) * (_vx) - (_selfx * _vy));
        float _t11 = 2.0f * ((_selfz) * (_vy) - (_selfy * _vz));
        dest.put(destOffset, ((_selfz) * (_t9) + (((((_selfw) * (_t11) + (_vx))) - (_selfy) * (_t10)))));
        dest.put(destOffset + 1, ((_selfx) * (_t10) + (((((_selfw) * (_t9) + (_vy))) - (_selfz) * (_t11)))));
        dest.put(destOffset + 2, ((_selfy) * (_t11) + (((((_selfw) * (_t10) + (_vz))) - (_selfx) * (_t9)))));
        return dest;
    }

    /** cos(k pi/8) and sin(k pi/8), k = 0..4: the float quatArcAngle's rotations. */
    private static final double[] QUAT_ARC_ANGLE_COS = new double[] {1.0, 0x1.d906bcf328d46p-1, 0x1.6a09e667f3bcdp-1, 0x1.87de2a6aea963p-2, 0.0};
    private static final double[] QUAT_ARC_ANGLE_SIN = new double[] {0.0, 0x1.87de2a6aea963p-2, 0x1.6a09e667f3bcdp-1, 0x1.d906bcf328d46p-1, 1.0};

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]. With
     * |a-b|^2 = 4 - s and half the angle in [0, pi/2], that half is k pi/8 + asin(|a-b|/2
     * cos(k pi/8) - |a+b|/2 sin(k pi/8)) for the k (four comparisons of 4 - s) that keeps the
     * argument within sin(pi/16): there the arcsine's odd Taylor polynomial to x^11 (3e-10 of it
     * left out), in double, is exact to well below a float ulp. Within 0.5005 ulp of the angle,
     * and no call, where java.lang.Math.asin is one: slerp is 15-24% faster.
     */
    private static float quatArcAngle_fma(float s) {
        double d = 4.0 - s, sd = java.lang.Math.sqrt(d), ss = java.lang.Math.sqrt(s);
        int k = (d > 0x1.37ca1866b95cfp-3 ? 1 : 0) + (d > 0x1.3c10eaca8ab4fp+0 ? 1 : 0)
                + (d > 0x1.61f78a9abaa59p+1 ? 1 : 0) + (d > 0x1.ec835e79946a3p+1 ? 1 : 0);
        double x = 0.5 * java.lang.Math.fma(sd, QUAT_ARC_ANGLE_COS[k], -ss * QUAT_ARC_ANGLE_SIN[k]), t = x * x;
        double p = java.lang.Math.fma(t, java.lang.Math.fma(t, java.lang.Math.fma(t, java.lang.Math.fma(t, 0x1.6e8ba2e8ba2e9p-6, 0x1.f1c71c71c71c7p-6),
                0x1.6db6db6db6db7p-5), 0x1.3333333333333p-4), 0x1.5555555555555p-3);
        return (float) (k * (Math.PI / 4) + 2 * java.lang.Math.fma(x * t, p, x));
    }

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]. With
     * |a-b|^2 = 4 - s and half the angle in [0, pi/2], that half is k pi/8 + asin(|a-b|/2
     * cos(k pi/8) - |a+b|/2 sin(k pi/8)) for the k (four comparisons of 4 - s) that keeps the
     * argument within sin(pi/16): there the arcsine's odd Taylor polynomial to x^11 (3e-10 of it
     * left out), in double, is exact to well below a float ulp. Within 0.5005 ulp of the angle,
     * and no call, where java.lang.Math.asin is one: slerp is 15-24% faster.
     */
    private static float quatArcAngle_mulAdd(float s) {
        double d = 4.0 - s, sd = java.lang.Math.sqrt(d), ss = java.lang.Math.sqrt(s);
        int k = (d > 0x1.37ca1866b95cfp-3 ? 1 : 0) + (d > 0x1.3c10eaca8ab4fp+0 ? 1 : 0)
                + (d > 0x1.61f78a9abaa59p+1 ? 1 : 0) + (d > 0x1.ec835e79946a3p+1 ? 1 : 0);
        double x = 0.5 * ((sd) * (QUAT_ARC_ANGLE_COS[k]) - (ss * QUAT_ARC_ANGLE_SIN[k])), t = x * x;
        double p = ((t) * (((t) * (((t) * (((t) * (0x1.6e8ba2e8ba2e9p-6) + (0x1.f1c71c71c71c7p-6))) + (0x1.6db6db6db6db7p-5))) + (0x1.3333333333333p-4))) + (0x1.5555555555555p-3));
        return (float) (k * (Math.PI / 4) + 2 * ((x * t) * (p) + (x)));
    }
}
