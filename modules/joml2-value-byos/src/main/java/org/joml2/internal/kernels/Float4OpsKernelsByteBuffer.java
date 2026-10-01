// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float4Ops} whose leading storage
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; public only because {@code Float4Ops}
 * and its SIMD cell call it from their own packages. Not public API.
 */
public final class Float4OpsKernelsByteBuffer {
    private Float4OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, otherX + _selfx);
        dest.putFloat(destOffset + 4, otherY + _selfy);
        dest.putFloat(destOffset + 8, otherZ + _selfz);
        dest.putFloat(destOffset + 12, otherW + _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, _otherx + _selfx);
        dest.putFloat(destOffset + 4, _othery + _selfy);
        dest.putFloat(destOffset + 8, _otherz + _selfz);
        dest.putFloat(destOffset + 12, _otherw + _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _selfx / scalar);
        dest.putFloat(destOffset + 4, _selfy / scalar);
        dest.putFloat(destOffset + 8, _selfz / scalar);
        dest.putFloat(destOffset + 12, _selfw / scalar);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _selfx / otherX);
        dest.putFloat(destOffset + 4, _selfy / otherY);
        dest.putFloat(destOffset + 8, _selfz / otherZ);
        dest.putFloat(destOffset + 12, _selfw / otherW);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float4OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, _selfx / _otherx);
        dest.putFloat(destOffset + 4, _selfy / _othery);
        dest.putFloat(destOffset + 8, _selfz / _otherz);
        dest.putFloat(destOffset + 12, _selfw / _otherw);
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float b, float cX, float cY, float cZ, float cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float b, float cX, float cY, float cZ, float cW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(_selfx, b, cX));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, b, cY));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, b, cZ));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, b, cW));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((_selfx) * (b) + (cX)));
            dest.putFloat(destOffset + 4, ((_selfy) * (b) + (cY)));
            dest.putFloat(destOffset + 8, ((_selfz) * (b) + (cZ)));
            dest.putFloat(destOffset + 12, ((_selfw) * (b) + (cW)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, float b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, float b) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _cx = c.getFloat(cOffset);
            float _cy = c.getFloat(cOffset + 4);
            float _cz = c.getFloat(cOffset + 8);
            float _cw = c.getFloat(cOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(_selfx, b, _cx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, b, _cy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, b, _cz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, b, _cw));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _cx = c.getFloat(cOffset);
            float _cy = c.getFloat(cOffset + 4);
            float _cz = c.getFloat(cOffset + 8);
            float _cw = c.getFloat(cOffset + 12);
            dest.putFloat(destOffset, ((_selfx) * (b) + (_cx)));
            dest.putFloat(destOffset + 4, ((_selfy) * (b) + (_cy)));
            dest.putFloat(destOffset + 8, ((_selfz) * (b) + (_cz)));
            dest.putFloat(destOffset + 12, ((_selfw) * (b) + (_cw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(_selfx, bX, cX));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, bY, cY));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, bZ, cZ));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, bW, cW));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((_selfx) * (bX) + (cX)));
            dest.putFloat(destOffset + 4, ((_selfy) * (bY) + (cY)));
            dest.putFloat(destOffset + 8, ((_selfz) * (bZ) + (cZ)));
            dest.putFloat(destOffset + 12, ((_selfw) * (bW) + (cW)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Float4OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return fma_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _bx = b.getFloat(bOffset);
        float _by = b.getFloat(bOffset + 4);
        float _bz = b.getFloat(bOffset + 8);
        float _bw = b.getFloat(bOffset + 12);
        float _cx = c.getFloat(cOffset);
        float _cy = c.getFloat(cOffset + 4);
        float _cz = c.getFloat(cOffset + 8);
        float _cw = c.getFloat(cOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _bx, _cx));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _by, _cy));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _bz, _cz));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _bw, _cw));
        return dest;
    }

    /** {@code fma_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer fma_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _bx = b.getFloat(bOffset);
        float _by = b.getFloat(bOffset + 4);
        float _bz = b.getFloat(bOffset + 8);
        float _bw = b.getFloat(bOffset + 12);
        float _cx = c.getFloat(cOffset);
        float _cy = c.getFloat(cOffset + 4);
        float _cz = c.getFloat(cOffset + 8);
        float _cw = c.getFloat(cOffset + 12);
        dest.putFloat(destOffset, ((_selfx) * (_bx) + (_cx)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_by) + (_cy)));
        dest.putFloat(destOffset + 8, ((_selfz) * (_bz) + (_cz)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_bw) + (_cw)));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, scalar * _selfx);
        dest.putFloat(destOffset + 4, scalar * _selfy);
        dest.putFloat(destOffset + 8, scalar * _selfz);
        dest.putFloat(destOffset + 12, scalar * _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, otherX * _selfx);
        dest.putFloat(destOffset + 4, otherY * _selfy);
        dest.putFloat(destOffset + 8, otherZ * _selfz);
        dest.putFloat(destOffset + 12, otherW * _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, _otherx * _selfx);
        dest.putFloat(destOffset + 4, _othery * _selfy);
        dest.putFloat(destOffset + 8, _otherz * _selfz);
        dest.putFloat(destOffset + 12, _otherw * _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, -_selfx);
        dest.putFloat(destOffset + 4, -_selfy);
        dest.putFloat(destOffset + 8, -_selfz);
        dest.putFloat(destOffset + 12, -_selfw);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _selfx - otherX);
        dest.putFloat(destOffset + 4, _selfy - otherY);
        dest.putFloat(destOffset + 8, _selfz - otherZ);
        dest.putFloat(destOffset + 12, _selfw - otherW);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, _selfx - _otherx);
        dest.putFloat(destOffset + 4, _selfy - _othery);
        dest.putFloat(destOffset + 8, _selfz - _otherz);
        dest.putFloat(destOffset + 12, _selfw - _otherw);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, float u, float v, float w) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float4OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v, w);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformDirection_api(java.nio.ByteBuffer dest, int destOffset, float u, float v, float w) {
        float _t0 = (float) java.lang.Math.sqrt(u);
        float _t1 = v * 6.2831855f;
        float _t3 = w * 6.2831855f;
        float _t4 = (float) java.lang.Math.sqrt(1.0f - u);
        dest.putFloat(destOffset, Math.cosWithSin(_t1) * _t4);
        dest.putFloat(destOffset + 4, Math.sinWithCos(_t1) * _t4);
        dest.putFloat(destOffset + 8, Math.cosWithSin(_t3) * _t0);
        dest.putFloat(destOffset + 12, Math.sinWithCos(_t3) * _t0);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float4OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        dest.putFloat(destOffset, vX);
        dest.putFloat(destOffset + 4, vY);
        dest.putFloat(destOffset + 8, vZ);
        dest.putFloat(destOffset + 12, vW);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float4OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        float _vw = v.getFloat(vOffset + 12);
        dest.putFloat(destOffset, _vx);
        dest.putFloat(destOffset + 4, _vy);
        dest.putFloat(destOffset + 8, _vz);
        dest.putFloat(destOffset + 12, _vw);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float4OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, float s) {
        dest.putFloat(destOffset, s);
        dest.putFloat(destOffset + 4, s);
        dest.putFloat(destOffset + 8, s);
        dest.putFloat(destOffset + 12, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float4OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        dest.putFloat(destOffset + 12, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.putFloat(destOffset, java.lang.Math.fma(p1X, _t7, _selfx * _t8) + java.lang.Math.fma(p2X, _t6, p3X * _t2));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(p1Y, _t7, _selfy * _t8) + java.lang.Math.fma(p2Y, _t6, p3Y * _t2));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(p1Z, _t7, _selfz * _t8) + java.lang.Math.fma(p2Z, _t6, p3Z * _t2));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(p1W, _t7, _selfw * _t8) + java.lang.Math.fma(p2W, _t6, p3W * _t2));
        return dest;
    }

    /** {@code bezier_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer bezier_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.putFloat(destOffset, ((p1X) * (_t7) + (_selfx * _t8)) + ((p2X) * (_t6) + (p3X * _t2)));
        dest.putFloat(destOffset + 4, ((p1Y) * (_t7) + (_selfy * _t8)) + ((p2Y) * (_t6) + (p3Y * _t2)));
        dest.putFloat(destOffset + 8, ((p1Z) * (_t7) + (_selfz * _t8)) + ((p2Z) * (_t6) + (p3Z * _t2)));
        dest.putFloat(destOffset + 12, ((p1W) * (_t7) + (_selfw * _t8)) + ((p2W) * (_t6) + (p3W * _t2)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Float4OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezier_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezier_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezier_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer bezier_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p1w = p1.getFloat(p1Offset + 12);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p2w = p2.getFloat(p2Offset + 12);
        float _p3x = p3.getFloat(p3Offset);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _p3z = p3.getFloat(p3Offset + 8);
        float _p3w = p3.getFloat(p3Offset + 12);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.putFloat(destOffset, java.lang.Math.fma(_p1x, _t7, _selfx * _t8) + java.lang.Math.fma(_p2x, _t6, _p3x * _t2));
        return bezier_api_fma_sb58cf7dc_1(dest, destOffset, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t2, _t6, _t7, _t8);
    }

    /** Piece 2 of {@code bezier_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer bezier_api_fma_sb58cf7dc_1(java.nio.ByteBuffer dest, int destOffset, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t2, float _t6, float _t7, float _t8) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_p1y, _t7, _selfy * _t8) + java.lang.Math.fma(_p2y, _t6, _p3y * _t2));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_p1z, _t7, _selfz * _t8) + java.lang.Math.fma(_p2z, _t6, _p3z * _t2));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_p1w, _t7, _selfw * _t8) + java.lang.Math.fma(_p2w, _t6, _p3w * _t2));
        return dest;
    }

    /** {@code bezier_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer bezier_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p1w = p1.getFloat(p1Offset + 12);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p2w = p2.getFloat(p2Offset + 12);
        float _p3x = p3.getFloat(p3Offset);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _p3z = p3.getFloat(p3Offset + 8);
        float _p3w = p3.getFloat(p3Offset + 12);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.putFloat(destOffset, ((_p1x) * (_t7) + (_selfx * _t8)) + ((_p2x) * (_t6) + (_p3x * _t2)));
        return bezier_api_mulAdd_s200dcb21_1(dest, destOffset, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t2, _t6, _t7, _t8);
    }

    /** Piece 2 of {@code bezier_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer bezier_api_mulAdd_s200dcb21_1(java.nio.ByteBuffer dest, int destOffset, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t2, float _t6, float _t7, float _t8) {
        dest.putFloat(destOffset + 4, ((_p1y) * (_t7) + (_selfy * _t8)) + ((_p2y) * (_t6) + (_p3y * _t2)));
        dest.putFloat(destOffset + 8, ((_p1z) * (_t7) + (_selfz * _t8)) + ((_p2z) * (_t6) + (_p3z * _t2)));
        dest.putFloat(destOffset + 12, ((_p1w) * (_t7) + (_selfw * _t8)) + ((_p2w) * (_t6) + (_p3w * _t2)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.putFloat(destOffset, java.lang.Math.fma(p2X, _t0, java.lang.Math.fma(p1X, _t3, _selfx * _t4)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(p2Y, _t0, java.lang.Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(p2Z, _t0, java.lang.Math.fma(p1Z, _t3, _selfz * _t4)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(p2W, _t0, java.lang.Math.fma(p1W, _t3, _selfw * _t4)));
        return dest;
    }

    /** {@code bezier2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer bezier2_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.putFloat(destOffset, ((p2X) * (_t0) + (((p1X) * (_t3) + (_selfx * _t4)))));
        dest.putFloat(destOffset + 4, ((p2Y) * (_t0) + (((p1Y) * (_t3) + (_selfy * _t4)))));
        dest.putFloat(destOffset + 8, ((p2Z) * (_t0) + (((p1Z) * (_t3) + (_selfz * _t4)))));
        dest.putFloat(destOffset + 12, ((p2W) * (_t0) + (((p1W) * (_t3) + (_selfw * _t4)))));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Float4OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p1w = p1.getFloat(p1Offset + 12);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p2w = p2.getFloat(p2Offset + 12);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.putFloat(destOffset, java.lang.Math.fma(_p2x, _t0, java.lang.Math.fma(_p1x, _t3, _selfx * _t4)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_p2y, _t0, java.lang.Math.fma(_p1y, _t3, _selfy * _t4)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_p2z, _t0, java.lang.Math.fma(_p1z, _t3, _selfz * _t4)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_p2w, _t0, java.lang.Math.fma(_p1w, _t3, _selfw * _t4)));
        return dest;
    }

    /** {@code bezier2_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer bezier2_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p1w = p1.getFloat(p1Offset + 12);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p2w = p2.getFloat(p2Offset + 12);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.putFloat(destOffset, ((_p2x) * (_t0) + (((_p1x) * (_t3) + (_selfx * _t4)))));
        dest.putFloat(destOffset + 4, ((_p2y) * (_t0) + (((_p1y) * (_t3) + (_selfy * _t4)))));
        dest.putFloat(destOffset + 8, ((_p2z) * (_t0) + (((_p1z) * (_t3) + (_selfz * _t4)))));
        dest.putFloat(destOffset + 12, ((_p2w) * (_t0) + (((_p1w) * (_t3) + (_selfw * _t4)))));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.putFloat(destOffset, java.lang.Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(p1W - _selfw, _t2, (p2W - p1W) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer bezier2Tangent_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.putFloat(destOffset, ((p1X - _selfx) * (_t2) + ((p2X - p1X) * _t1)));
        dest.putFloat(destOffset + 4, ((p1Y - _selfy) * (_t2) + ((p2Y - p1Y) * _t1)));
        dest.putFloat(destOffset + 8, ((p1Z - _selfz) * (_t2) + ((p2Z - p1Z) * _t1)));
        dest.putFloat(destOffset + 12, ((p1W - _selfw) * (_t2) + ((p2W - p1W) * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Float4OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezier2Tangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p1w = p1.getFloat(p1Offset + 12);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p2w = p2.getFloat(p2Offset + 12);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.putFloat(destOffset, java.lang.Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_p1w - _selfw, _t2, (_p2w - _p1w) * _t1));
        return dest;
    }

    /** {@code bezier2Tangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer bezier2Tangent_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p1w = p1.getFloat(p1Offset + 12);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p2w = p2.getFloat(p2Offset + 12);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.putFloat(destOffset, ((_p1x - _selfx) * (_t2) + ((_p2x - _p1x) * _t1)));
        dest.putFloat(destOffset + 4, ((_p1y - _selfy) * (_t2) + ((_p2y - _p1y) * _t1)));
        dest.putFloat(destOffset + 8, ((_p1z - _selfz) * (_t2) + ((_p2z - _p1z) * _t1)));
        dest.putFloat(destOffset + 12, ((_p1w - _selfw) * (_t2) + ((_p2w - _p1w) * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.putFloat(destOffset, java.lang.Math.fma(p3X - p2X, _t2, java.lang.Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(p3Y - p2Y, _t2, java.lang.Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(p3Z - p2Z, _t2, java.lang.Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(p3W - p2W, _t2, java.lang.Math.fma(p1W - _selfw, _t6, (p2W - p1W) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer bezierTangent_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.putFloat(destOffset, ((p3X - p2X) * (_t2) + (((p1X - _selfx) * (_t6) + ((p2X - p1X) * _t5)))));
        dest.putFloat(destOffset + 4, ((p3Y - p2Y) * (_t2) + (((p1Y - _selfy) * (_t6) + ((p2Y - p1Y) * _t5)))));
        dest.putFloat(destOffset + 8, ((p3Z - p2Z) * (_t2) + (((p1Z - _selfz) * (_t6) + ((p2Z - p1Z) * _t5)))));
        dest.putFloat(destOffset + 12, ((p3W - p2W) * (_t2) + (((p1W - _selfw) * (_t6) + ((p2W - p1W) * _t5)))));
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Float4OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return bezierTangent_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return bezierTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code bezierTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer bezierTangent_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p1w = p1.getFloat(p1Offset + 12);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p2w = p2.getFloat(p2Offset + 12);
        float _p3x = p3.getFloat(p3Offset);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _p3z = p3.getFloat(p3Offset + 8);
        float _p3w = p3.getFloat(p3Offset + 12);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.putFloat(destOffset, java.lang.Math.fma(_p3x - _p2x, _t2, java.lang.Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_p3y - _p2y, _t2, java.lang.Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        return bezierTangent_api_fma_s2524ec79_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _p3z, _p3w, _t2, _t5, _t6);
    }

    /** Piece 2 of {@code bezierTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer bezierTangent_api_fma_s2524ec79_1(java.nio.ByteBuffer dest, int destOffset, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _p3z, float _p3w, float _t2, float _t5, float _t6) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_p3z - _p2z, _t2, java.lang.Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_p3w - _p2w, _t2, java.lang.Math.fma(_p1w - _selfw, _t6, (_p2w - _p1w) * _t5)));
        return dest;
    }

    /** {@code bezierTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer bezierTangent_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p1w = p1.getFloat(p1Offset + 12);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p2w = p2.getFloat(p2Offset + 12);
        float _p3x = p3.getFloat(p3Offset);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _p3z = p3.getFloat(p3Offset + 8);
        float _p3w = p3.getFloat(p3Offset + 12);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.putFloat(destOffset, ((_p3x - _p2x) * (_t2) + (((_p1x - _selfx) * (_t6) + ((_p2x - _p1x) * _t5)))));
        dest.putFloat(destOffset + 4, ((_p3y - _p2y) * (_t2) + (((_p1y - _selfy) * (_t6) + ((_p2y - _p1y) * _t5)))));
        return bezierTangent_api_mulAdd_s481f1ec6_1(dest, destOffset, _selfz, _selfw, _p1z, _p1w, _p2z, _p2w, _p3z, _p3w, _t2, _t5, _t6);
    }

    /** Piece 2 of {@code bezierTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer bezierTangent_api_mulAdd_s481f1ec6_1(java.nio.ByteBuffer dest, int destOffset, float _selfz, float _selfw, float _p1z, float _p1w, float _p2z, float _p2w, float _p3z, float _p3w, float _t2, float _t5, float _t6) {
        dest.putFloat(destOffset + 8, ((_p3z - _p2z) * (_t2) + (((_p1z - _selfz) * (_t6) + ((_p2z - _p1z) * _t5)))));
        dest.putFloat(destOffset + 12, ((_p3w - _p2w) * (_t2) + (((_p1w - _selfw) * (_t6) + ((_p2w - _p1w) * _t5)))));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRom_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRom_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer catmullRom_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.putFloat(destOffset, 0.5f * (java.lang.Math.fma(2.0f, p1X, t * (p2X - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), _t0, java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        dest.putFloat(destOffset + 4, 0.5f * (java.lang.Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), _t0, java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        return catmullRom_api_fma_s1d93be1_1(dest, destOffset, p1Z, p1W, p2Z, p2W, p3Z, p3W, t, _selfz, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer catmullRom_api_fma_s1d93be1_1(java.nio.ByteBuffer dest, int destOffset, float p1Z, float p1W, float p2Z, float p2W, float p3Z, float p3W, float t, float _selfz, float _selfw, float _t0, float _t1) {
        dest.putFloat(destOffset + 8, 0.5f * (java.lang.Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, p2Z, -p3Z))), _t0, java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1)));
        dest.putFloat(destOffset + 12, 0.5f * (java.lang.Math.fma(2.0f, p1W, t * (p2W - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, p2W, -p3W))), _t0, java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - _selfw)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer catmullRom_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.putFloat(destOffset, 0.5f * (((2.0f) * (p1X) + (t * (p2X - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) * (_t0) + (((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X)) * _t1))));
        dest.putFloat(destOffset + 4, 0.5f * (((2.0f) * (p1Y) + (t * (p2Y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) * (_t0) + (((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y)) * _t1))));
        dest.putFloat(destOffset + 8, 0.5f * (((2.0f) * (p1Z) + (t * (p2Z - _selfz))) + ((((((2.0f) * (_selfz) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) * (_t0) + (((((3.0f) * (p1Z) + (p3Z - _selfz))) - (3.0f) * (p2Z)) * _t1))));
        return catmullRom_api_mulAdd_sd36589ce_1(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer catmullRom_api_mulAdd_sd36589ce_1(java.nio.ByteBuffer dest, int destOffset, float p1W, float p2W, float p3W, float t, float _selfw, float _t0, float _t1) {
        dest.putFloat(destOffset + 12, 0.5f * (((2.0f) * (p1W) + (t * (p2W - _selfw))) + ((((((2.0f) * (_selfw) + (((4.0f) * (p2W) - (p3W))))) - (5.0f) * (p1W))) * (_t0) + (((((3.0f) * (p1W) + (p3W - _selfw))) - (3.0f) * (p2W)) * _t1))));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Float4OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRom_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRom_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRom_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer catmullRom_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p1w = p1.getFloat(p1Offset + 12);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p2w = p2.getFloat(p2Offset + 12);
        float _p3x = p3.getFloat(p3Offset);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _p3z = p3.getFloat(p3Offset + 8);
        float _p3w = p3.getFloat(p3Offset + 12);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.putFloat(destOffset, 0.5f * (java.lang.Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), _t0, java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        return catmullRom_api_fma_s6a06f773_1(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer catmullRom_api_fma_s6a06f773_1(java.nio.ByteBuffer dest, int destOffset, float t, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t0, float _t1) {
        dest.putFloat(destOffset + 4, 0.5f * (java.lang.Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), _t0, java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        dest.putFloat(destOffset + 8, 0.5f * (java.lang.Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, _p2z, -_p3z))), _t0, java.lang.Math.fma(-3.0f, _p2z, java.lang.Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1)));
        dest.putFloat(destOffset + 12, 0.5f * (java.lang.Math.fma(2.0f, _p1w, t * (_p2w - _selfw)) + java.lang.Math.fma(java.lang.Math.fma(-5.0f, _p1w, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, _p2w, -_p3w))), _t0, java.lang.Math.fma(-3.0f, _p2w, java.lang.Math.fma(3.0f, _p1w, _p3w - _selfw)) * _t1)));
        return dest;
    }

    /** {@code catmullRom_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer catmullRom_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p1w = p1.getFloat(p1Offset + 12);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p2w = p2.getFloat(p2Offset + 12);
        float _p3x = p3.getFloat(p3Offset);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _p3z = p3.getFloat(p3Offset + 8);
        float _p3w = p3.getFloat(p3Offset + 12);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.putFloat(destOffset, 0.5f * (((2.0f) * (_p1x) + (t * (_p2x - _selfx))) + ((((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) * (_t0) + (((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x)) * _t1))));
        return catmullRom_api_mulAdd_s211a6600_1(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer catmullRom_api_mulAdd_s211a6600_1(java.nio.ByteBuffer dest, int destOffset, float t, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t0, float _t1) {
        dest.putFloat(destOffset + 4, 0.5f * (((2.0f) * (_p1y) + (t * (_p2y - _selfy))) + ((((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) * (_t0) + (((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y)) * _t1))));
        dest.putFloat(destOffset + 8, 0.5f * (((2.0f) * (_p1z) + (t * (_p2z - _selfz))) + ((((((2.0f) * (_selfz) + (((4.0f) * (_p2z) - (_p3z))))) - (5.0f) * (_p1z))) * (_t0) + (((((3.0f) * (_p1z) + (_p3z - _selfz))) - (3.0f) * (_p2z)) * _t1))));
        dest.putFloat(destOffset + 12, 0.5f * (((2.0f) * (_p1w) + (t * (_p2w - _selfw))) + ((((((2.0f) * (_selfw) + (((4.0f) * (_p2w) - (_p3w))))) - (5.0f) * (_p1w))) * (_t0) + (((((3.0f) * (_p1w) + (_p3w - _selfw))) - (3.0f) * (_p2w)) * _t1))));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_api_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return catmullRomTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@code catmullRomTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer catmullRomTangent_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = t * t;
        dest.putFloat(destOffset, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1X, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, p2X, -p3X))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2X, java.lang.Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.putFloat(destOffset + 4, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, p2Y, -p3Y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Y, java.lang.Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        dest.putFloat(destOffset + 8, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1Z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, p2Z, -p3Z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2Z, java.lang.Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        return catmullRomTangent_api_fma_sbdaa1236_1(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer catmullRomTangent_api_fma_sbdaa1236_1(java.nio.ByteBuffer dest, int destOffset, float p1W, float p2W, float p3W, float t, float _selfw, float _t0) {
        dest.putFloat(destOffset + 12, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, p1W, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, p2W, -p3W))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, p2W, java.lang.Math.fma(3.0f, p1W, p3W - _selfw)), _t0, p2W - _selfw)));
        return dest;
    }

    /** {@code catmullRomTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer catmullRomTangent_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = t * t;
        dest.putFloat(destOffset, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (p2X) - (p3X))))) - (5.0f) * (p1X))) + (((3.0f * ((((3.0f) * (p1X) + (p3X - _selfx))) - (3.0f) * (p2X))) * (_t0) + (p2X - _selfx)))));
        dest.putFloat(destOffset + 4, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (p2Y) - (p3Y))))) - (5.0f) * (p1Y))) + (((3.0f * ((((3.0f) * (p1Y) + (p3Y - _selfy))) - (3.0f) * (p2Y))) * (_t0) + (p2Y - _selfy)))));
        dest.putFloat(destOffset + 8, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfz) + (((4.0f) * (p2Z) - (p3Z))))) - (5.0f) * (p1Z))) + (((3.0f * ((((3.0f) * (p1Z) + (p3Z - _selfz))) - (3.0f) * (p2Z))) * (_t0) + (p2Z - _selfz)))));
        return catmullRomTangent_api_mulAdd_s5e8dc06b_1(dest, destOffset, p1W, p2W, p3W, t, _selfw, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer catmullRomTangent_api_mulAdd_s5e8dc06b_1(java.nio.ByteBuffer dest, int destOffset, float p1W, float p2W, float p3W, float t, float _selfw, float _t0) {
        dest.putFloat(destOffset + 12, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfw) + (((4.0f) * (p2W) - (p3W))))) - (5.0f) * (p1W))) + (((3.0f * ((((3.0f) * (p1W) + (p3W - _selfw))) - (3.0f) * (p2W))) * (_t0) + (p2W - _selfw)))));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Float4OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return catmullRomTangent_api_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return catmullRomTangent_api_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@code catmullRomTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer catmullRomTangent_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p1w = p1.getFloat(p1Offset + 12);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p2w = p2.getFloat(p2Offset + 12);
        float _p3x = p3.getFloat(p3Offset);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _p3z = p3.getFloat(p3Offset + 8);
        float _p3w = p3.getFloat(p3Offset + 12);
        float _t0 = t * t;
        dest.putFloat(destOffset, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1x, java.lang.Math.fma(2.0f, _selfx, java.lang.Math.fma(4.0f, _p2x, -_p3x))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2x, java.lang.Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        return catmullRomTangent_api_fma_s284ce4b8_1(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer catmullRomTangent_api_fma_s284ce4b8_1(java.nio.ByteBuffer dest, int destOffset, float t, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t0) {
        dest.putFloat(destOffset + 4, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1y, java.lang.Math.fma(2.0f, _selfy, java.lang.Math.fma(4.0f, _p2y, -_p3y))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2y, java.lang.Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        dest.putFloat(destOffset + 8, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1z, java.lang.Math.fma(2.0f, _selfz, java.lang.Math.fma(4.0f, _p2z, -_p3z))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2z, java.lang.Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        dest.putFloat(destOffset + 12, 0.5f * java.lang.Math.fma(t, 2.0f * java.lang.Math.fma(-5.0f, _p1w, java.lang.Math.fma(2.0f, _selfw, java.lang.Math.fma(4.0f, _p2w, -_p3w))), java.lang.Math.fma(3.0f * java.lang.Math.fma(-3.0f, _p2w, java.lang.Math.fma(3.0f, _p1w, _p3w - _selfw)), _t0, _p2w - _selfw)));
        return dest;
    }

    /** {@code catmullRomTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer catmullRomTangent_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p1w = p1.getFloat(p1Offset + 12);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p2w = p2.getFloat(p2Offset + 12);
        float _p3x = p3.getFloat(p3Offset);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _p3z = p3.getFloat(p3Offset + 8);
        float _p3w = p3.getFloat(p3Offset + 12);
        float _t0 = t * t;
        dest.putFloat(destOffset, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfx) + (((4.0f) * (_p2x) - (_p3x))))) - (5.0f) * (_p1x))) + (((3.0f * ((((3.0f) * (_p1x) + (_p3x - _selfx))) - (3.0f) * (_p2x))) * (_t0) + (_p2x - _selfx)))));
        return catmullRomTangent_api_mulAdd_sc3cf5b3d_1(dest, destOffset, t, _selfy, _selfz, _selfw, _p1y, _p1z, _p1w, _p2y, _p2z, _p2w, _p3y, _p3z, _p3w, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer catmullRomTangent_api_mulAdd_sc3cf5b3d_1(java.nio.ByteBuffer dest, int destOffset, float t, float _selfy, float _selfz, float _selfw, float _p1y, float _p1z, float _p1w, float _p2y, float _p2z, float _p2w, float _p3y, float _p3z, float _p3w, float _t0) {
        dest.putFloat(destOffset + 4, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfy) + (((4.0f) * (_p2y) - (_p3y))))) - (5.0f) * (_p1y))) + (((3.0f * ((((3.0f) * (_p1y) + (_p3y - _selfy))) - (3.0f) * (_p2y))) * (_t0) + (_p2y - _selfy)))));
        dest.putFloat(destOffset + 8, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfz) + (((4.0f) * (_p2z) - (_p3z))))) - (5.0f) * (_p1z))) + (((3.0f * ((((3.0f) * (_p1z) + (_p3z - _selfz))) - (3.0f) * (_p2z))) * (_t0) + (_p2z - _selfz)))));
        dest.putFloat(destOffset + 12, 0.5f * ((t) * (2.0f * ((((2.0f) * (_selfw) + (((4.0f) * (_p2w) - (_p3w))))) - (5.0f) * (_p1w))) + (((3.0f * ((((3.0f) * (_p1w) + (_p3w - _selfw))) - (3.0f) * (_p2w))) * (_t0) + (_p2w - _selfw)))));
        return dest;
    }

    public static java.nio.ByteBuffer hermite_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermite_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermite_api_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * java.lang.Math.fma(t, t, -t);
        float _t7 = java.lang.Math.fma(t - 2.0f, _t0, t);
        float _t9 = java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f));
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t10, t0X * _t7) + java.lang.Math.fma(t1X, _t5, v1X * _t9));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t10, t0Y * _t7) + java.lang.Math.fma(t1Y, _t5, v1Y * _t9));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t10, t0Z * _t7) + java.lang.Math.fma(t1Z, _t5, v1Z * _t9));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t10, t0W * _t7) + java.lang.Math.fma(t1W, _t5, v1W * _t9));
        return dest;
    }

    /** {@code hermite_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer hermite_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        dest.putFloat(destOffset, ((_selfx) * (_t10) + (t0X * _t7)) + ((t1X) * (_t5) + (v1X * _t9)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t10) + (t0Y * _t7)) + ((t1Y) * (_t5) + (v1Y * _t9)));
        dest.putFloat(destOffset + 8, ((_selfz) * (_t10) + (t0Z * _t7)) + ((t1Z) * (_t5) + (v1Z * _t9)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t10) + (t0W * _t7)) + ((t1W) * (_t5) + (v1W * _t9)));
        return dest;
    }

    public static java.nio.ByteBuffer hermite_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        Float4OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermite_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermite_api_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermite_api_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermite_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer hermite_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0x = t0.getFloat(t0Offset);
        float _t0y = t0.getFloat(t0Offset + 4);
        float _t0z = t0.getFloat(t0Offset + 8);
        float _t0w = t0.getFloat(t0Offset + 12);
        float _v1x = v1.getFloat(v1Offset);
        float _v1y = v1.getFloat(v1Offset + 4);
        float _v1z = v1.getFloat(v1Offset + 8);
        float _v1w = v1.getFloat(v1Offset + 12);
        float _t1x = t1.getFloat(t1Offset);
        float _t1y = t1.getFloat(t1Offset + 4);
        float _t1z = t1.getFloat(t1Offset + 8);
        float _t1w = t1.getFloat(t1Offset + 12);
        float _t0 = t * t;
        float _t2 = t * _t0;
        return hermite_api_fma_se0155453_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t0x, _t0y, _t0z, _t0w, _v1x, _v1y, _v1z, _v1w, _t1x, _t1y, _t1z, _t1w, t * java.lang.Math.fma(t, t, -t), java.lang.Math.fma(t - 2.0f, _t0, t), java.lang.Math.fma(3.0f, _t0, -(_t2 + _t2)), java.lang.Math.fma(2.0f, _t2, java.lang.Math.fma(-3.0f, _t0, 1.0f)));
    }

    /** Piece 2 of {@code hermite_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer hermite_api_fma_se0155453_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t0x, float _t0y, float _t0z, float _t0w, float _v1x, float _v1y, float _v1z, float _v1w, float _t1x, float _t1y, float _t1z, float _t1w, float _t5, float _t7, float _t9, float _t10) {
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t10, _t0x * _t7) + java.lang.Math.fma(_t1x, _t5, _v1x * _t9));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t10, _t0y * _t7) + java.lang.Math.fma(_t1y, _t5, _v1y * _t9));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t10, _t0z * _t7) + java.lang.Math.fma(_t1z, _t5, _v1z * _t9));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t10, _t0w * _t7) + java.lang.Math.fma(_t1w, _t5, _v1w * _t9));
        return dest;
    }

    /** {@code hermite_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer hermite_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0x = t0.getFloat(t0Offset);
        float _t0y = t0.getFloat(t0Offset + 4);
        float _t0z = t0.getFloat(t0Offset + 8);
        float _t0w = t0.getFloat(t0Offset + 12);
        float _v1x = v1.getFloat(v1Offset);
        float _v1y = v1.getFloat(v1Offset + 4);
        float _v1z = v1.getFloat(v1Offset + 8);
        float _v1w = v1.getFloat(v1Offset + 12);
        float _t1x = t1.getFloat(t1Offset);
        float _t1y = t1.getFloat(t1Offset + 4);
        float _t1z = t1.getFloat(t1Offset + 8);
        float _t1w = t1.getFloat(t1Offset + 12);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * ((t) * (t) - (t));
        float _t7 = ((t - 2.0f) * (_t0) + (t));
        float _t9 = ((3.0f) * (_t0) - (_t2 + _t2));
        float _t10 = ((2.0f) * (_t2) + (((1.0f) - (3.0f) * (_t0))));
        dest.putFloat(destOffset, ((_selfx) * (_t10) + (_t0x * _t7)) + ((_t1x) * (_t5) + (_v1x * _t9)));
        return hermite_api_mulAdd_saae7e018_1(dest, destOffset, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t5, _t7, _t9, _t10);
    }

    /** Piece 2 of {@code hermite_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer hermite_api_mulAdd_saae7e018_1(java.nio.ByteBuffer dest, int destOffset, float _selfy, float _selfz, float _selfw, float _t0y, float _t0z, float _t0w, float _v1y, float _v1z, float _v1w, float _t1y, float _t1z, float _t1w, float _t5, float _t7, float _t9, float _t10) {
        dest.putFloat(destOffset + 4, ((_selfy) * (_t10) + (_t0y * _t7)) + ((_t1y) * (_t5) + (_v1y * _t9)));
        dest.putFloat(destOffset + 8, ((_selfz) * (_t10) + (_t0z * _t7)) + ((_t1z) * (_t5) + (_v1z * _t9)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t10) + (_t0w * _t7)) + ((_t1w) * (_t5) + (_v1w * _t9)));
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_api_mulAdd(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = t * t;
        float _t6 = 6.0f * java.lang.Math.fma(t, t, -t);
        float _t7 = 6.0f * java.lang.Math.fma(-t, t, t);
        float _t8 = java.lang.Math.fma(3.0f, _t0, -(t + t));
        float _t9 = java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f));
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t6, t0X * _t9) + java.lang.Math.fma(t1X, _t8, v1X * _t7));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t6, t0Y * _t9) + java.lang.Math.fma(t1Y, _t8, v1Y * _t7));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t6, t0Z * _t9) + java.lang.Math.fma(t1Z, _t8, v1Z * _t7));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t6, t0W * _t9) + java.lang.Math.fma(t1W, _t8, v1W * _t7));
        return dest;
    }

    /** {@code hermiteTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer hermiteTangent_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        dest.putFloat(destOffset, ((_selfx) * (_t6) + (t0X * _t9)) + ((t1X) * (_t8) + (v1X * _t7)));
        dest.putFloat(destOffset + 4, ((_selfy) * (_t6) + (t0Y * _t9)) + ((t1Y) * (_t8) + (v1Y * _t7)));
        dest.putFloat(destOffset + 8, ((_selfz) * (_t6) + (t0Z * _t9)) + ((t1Z) * (_t8) + (v1Z * _t7)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t6) + (t0W * _t9)) + ((t1W) * (_t8) + (v1W * _t7)));
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        Float4OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return hermiteTangent_api_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return hermiteTangent_api_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@code hermiteTangent_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer hermiteTangent_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0x = t0.getFloat(t0Offset);
        float _t0y = t0.getFloat(t0Offset + 4);
        float _t0z = t0.getFloat(t0Offset + 8);
        float _t0w = t0.getFloat(t0Offset + 12);
        float _v1x = v1.getFloat(v1Offset);
        float _v1y = v1.getFloat(v1Offset + 4);
        float _v1z = v1.getFloat(v1Offset + 8);
        float _v1w = v1.getFloat(v1Offset + 12);
        float _t1x = t1.getFloat(t1Offset);
        float _t1y = t1.getFloat(t1Offset + 4);
        float _t1z = t1.getFloat(t1Offset + 8);
        float _t1w = t1.getFloat(t1Offset + 12);
        float _t0 = t * t;
        return hermiteTangent_api_fma_sb408a708_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t0x, _t0y, _t0z, _t0w, _v1x, _v1y, _v1z, _v1w, _t1x, _t1y, _t1z, _t1w, 6.0f * java.lang.Math.fma(t, t, -t), 6.0f * java.lang.Math.fma(-t, t, t), java.lang.Math.fma(3.0f, _t0, -(t + t)), java.lang.Math.fma(3.0f, _t0, java.lang.Math.fma(-4.0f, t, 1.0f)));
    }

    /** Piece 2 of {@code hermiteTangent_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer hermiteTangent_api_fma_sb408a708_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t0x, float _t0y, float _t0z, float _t0w, float _v1x, float _v1y, float _v1z, float _v1w, float _t1x, float _t1y, float _t1z, float _t1w, float _t6, float _t7, float _t8, float _t9) {
        dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t6, _t0x * _t9) + java.lang.Math.fma(_t1x, _t8, _v1x * _t7));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t6, _t0y * _t9) + java.lang.Math.fma(_t1y, _t8, _v1y * _t7));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t6, _t0z * _t9) + java.lang.Math.fma(_t1z, _t8, _v1z * _t7));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_selfw, _t6, _t0w * _t9) + java.lang.Math.fma(_t1w, _t8, _v1w * _t7));
        return dest;
    }

    /** {@code hermiteTangent_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer hermiteTangent_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0x = t0.getFloat(t0Offset);
        float _t0y = t0.getFloat(t0Offset + 4);
        float _t0z = t0.getFloat(t0Offset + 8);
        float _t0w = t0.getFloat(t0Offset + 12);
        float _v1x = v1.getFloat(v1Offset);
        float _v1y = v1.getFloat(v1Offset + 4);
        float _v1z = v1.getFloat(v1Offset + 8);
        float _v1w = v1.getFloat(v1Offset + 12);
        float _t1x = t1.getFloat(t1Offset);
        float _t1y = t1.getFloat(t1Offset + 4);
        float _t1z = t1.getFloat(t1Offset + 8);
        float _t1w = t1.getFloat(t1Offset + 12);
        float _t0 = t * t;
        float _t6 = 6.0f * ((t) * (t) - (t));
        float _t7 = 6.0f * ((t) - (t) * (t));
        float _t8 = ((3.0f) * (_t0) - (t + t));
        float _t9 = ((3.0f) * (_t0) + (((1.0f) - (4.0f) * (t))));
        dest.putFloat(destOffset, ((_selfx) * (_t6) + (_t0x * _t9)) + ((_t1x) * (_t8) + (_v1x * _t7)));
        return hermiteTangent_api_mulAdd_s1404703d_1(dest, destOffset, _selfy, _selfz, _selfw, _t0y, _t0z, _t0w, _v1y, _v1z, _v1w, _t1y, _t1z, _t1w, _t6, _t7, _t8, _t9);
    }

    /** Piece 2 of {@code hermiteTangent_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer hermiteTangent_api_mulAdd_s1404703d_1(java.nio.ByteBuffer dest, int destOffset, float _selfy, float _selfz, float _selfw, float _t0y, float _t0z, float _t0w, float _v1y, float _v1z, float _v1w, float _t1y, float _t1z, float _t1w, float _t6, float _t7, float _t8, float _t9) {
        dest.putFloat(destOffset + 4, ((_selfy) * (_t6) + (_t0y * _t9)) + ((_t1y) * (_t8) + (_v1y * _t7)));
        dest.putFloat(destOffset + 8, ((_selfz) * (_t6) + (_t0z * _t9)) + ((_t1z) * (_t8) + (_v1z * _t7)));
        dest.putFloat(destOffset + 12, ((_selfw) * (_t6) + (_t0w * _t9)) + ((_t1w) * (_t8) + (_v1w * _t7)));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(t, otherW - _selfw, _selfw));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.putFloat(destOffset + 4, ((t) * (otherY - _selfy) + (_selfy)));
            dest.putFloat(destOffset + 8, ((t) * (otherZ - _selfz) + (_selfz)));
            dest.putFloat(destOffset + 12, ((t) * (otherW - _selfw) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(t, _othery - _selfy, _selfy));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
        dest.putFloat(destOffset + 4, ((t) * (_othery - _selfy) + (_selfy)));
        dest.putFloat(destOffset + 8, ((t) * (_otherz - _selfz) + (_selfz)));
        dest.putFloat(destOffset + 12, ((t) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(tX, otherX - _selfx, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(tY, otherY - _selfy, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(tZ, otherZ - _selfz, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(tW, otherW - _selfw, _selfw));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((tX) * (otherX - _selfx) + (_selfx)));
            dest.putFloat(destOffset + 4, ((tY) * (otherY - _selfy) + (_selfy)));
            dest.putFloat(destOffset + 8, ((tZ) * (otherZ - _selfz) + (_selfz)));
            dest.putFloat(destOffset + 12, ((tW) * (otherW - _selfw) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Float4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return lerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _tx = t.getFloat(tOffset);
        float _ty = t.getFloat(tOffset + 4);
        float _tz = t.getFloat(tOffset + 8);
        float _tw = t.getFloat(tOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(_tx, _otherx - _selfx, _selfx));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_ty, _othery - _selfy, _selfy));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_tz, _otherz - _selfz, _selfz));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_tw, _otherw - _selfw, _selfw));
        return dest;
    }

    /** {@code lerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer lerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _tx = t.getFloat(tOffset);
        float _ty = t.getFloat(tOffset + 4);
        float _tz = t.getFloat(tOffset + 8);
        float _tw = t.getFloat(tOffset + 12);
        dest.putFloat(destOffset, ((_tx) * (_otherx - _selfx) + (_selfx)));
        dest.putFloat(destOffset + 4, ((_ty) * (_othery - _selfy) + (_selfy)));
        dest.putFloat(destOffset + 8, ((_tz) * (_otherz - _selfz) + (_selfz)));
        dest.putFloat(destOffset + 12, ((_tw) * (_otherw - _selfw) + (_selfw)));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        return slerp_api_fma_s842c3baf_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24))));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma_s842c3baf_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31) {
        float _t40 = java.lang.Math.fma(otherW, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.putFloat(destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        return slerp_api_fma_s842c3baf_2(dest, destOffset, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma_s842c3baf_2(java.nio.ByteBuffer dest, int destOffset, float _t19, float _t21, float _t24, float _t49, float _t50, float _t52, float _t64, float _sp0) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        return slerp_api_mulAdd_s64461808_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24)))))));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd_s64461808_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31) {
        float _t40 = ((otherW) * (_t14) - (_t31 * _t19));
        float _t41 = ((otherZ) * (_t14) - (_t31 * _t21));
        float _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        float _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.putFloat(destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        dest.putFloat(destOffset + 4, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.putFloat(destOffset + 8, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.putFloat(destOffset + 12, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dest.putFloat(destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.putFloat(destOffset + 4, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.putFloat(destOffset + 8, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.putFloat(destOffset + 12, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.putFloat(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.putFloat(destOffset + 4, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.putFloat(destOffset + 8, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.putFloat(destOffset + 12, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.putFloat(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.putFloat(destOffset + 4, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.putFloat(destOffset + 8, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.putFloat(destOffset + 12, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.putFloat(destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 1.4551915E-11f) {
                    dest.putFloat(destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.putFloat(destOffset + 4, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.putFloat(destOffset + 8, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.putFloat(destOffset + 12, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.putFloat(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.putFloat(destOffset + 4, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.putFloat(destOffset + 8, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.putFloat(destOffset + 12, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.putFloat(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.putFloat(destOffset + 4, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.putFloat(destOffset + 8, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.putFloat(destOffset + 12, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.putFloat(destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.putFloat(destOffset + 4, ((t) * (otherY - _selfy) + (_selfy)));
            dest.putFloat(destOffset + 8, ((t) * (otherZ - _selfz) + (_selfz)));
            dest.putFloat(destOffset + 12, ((t) * (otherW - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t10 = java.lang.Math.fma(_otherw, _otherw, java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_api_fma_s881f3747_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma_s881f3747_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = java.lang.Math.fma(_otherw * _t14, _t19, java.lang.Math.fma(_otherz * _t14, _t21, java.lang.Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(_otherw, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(_otherz, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(_otherx, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(_othery, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_api_fma_s881f3747_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma_s881f3747_2(java.nio.ByteBuffer dest, int destOffset, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _t64, float _sp0) {
        dest.putFloat(destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t10 = ((_otherw) * (_otherw) + (((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_api_mulAdd_s78a923f4_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd_s78a923f4_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = ((_otherw * _t14) * (_t19) + (((_otherz * _t14) * (_t21) + (((_otherx * _t14) * (_t16) + (_othery * _t14 * _t24))))));
        float _t40 = ((_otherw) * (_t14) - (_t31 * _t19));
        float _t41 = ((_otherz) * (_t14) - (_t31 * _t21));
        float _t42 = ((_otherx) * (_t14) - (_t31 * _t16));
        float _t43 = ((_othery) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.putFloat(destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        return slerp_api_mulAdd_s78a923f4_2(dest, destOffset, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd_s78a923f4_2(java.nio.ByteBuffer dest, int destOffset, float _t19, float _t21, float _t24, float _t49, float _t50, float _t52, float _t64, float _sp0) {
        dest.putFloat(destOffset + 4, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.putFloat(destOffset + 8, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.putFloat(destOffset + 12, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= 1.4551915E-11f) {
                    dest.putFloat(destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.putFloat(destOffset + 4, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.putFloat(destOffset + 8, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.putFloat(destOffset + 12, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.putFloat(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.putFloat(destOffset + 4, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.putFloat(destOffset + 8, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.putFloat(destOffset + 12, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.putFloat(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.putFloat(destOffset + 4, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.putFloat(destOffset + 8, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.putFloat(destOffset + 12, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.putFloat(destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= 1.4551915E-11f) {
                    dest.putFloat(destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.putFloat(destOffset + 4, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.putFloat(destOffset + 8, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.putFloat(destOffset + 12, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.putFloat(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.putFloat(destOffset + 4, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.putFloat(destOffset + 8, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.putFloat(destOffset + 12, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.putFloat(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.putFloat(destOffset + 4, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.putFloat(destOffset + 8, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.putFloat(destOffset + 12, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.putFloat(destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.putFloat(destOffset + 4, ((t) * (_othery - _selfy) + (_selfy)));
            dest.putFloat(destOffset + 8, ((t) * (_otherz - _selfz) + (_selfz)));
            dest.putFloat(destOffset + 12, ((t) * (_otherw - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t10 = java.lang.Math.fma(otherW, otherW, java.lang.Math.fma(otherZ, otherZ, java.lang.Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_api_fma_sd621d5e5_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma_sd621d5e5_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = java.lang.Math.fma(otherW * _t14, _t19, java.lang.Math.fma(otherZ * _t14, _t21, java.lang.Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(otherW, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(otherZ, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_api_fma_sd621d5e5_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma_sd621d5e5_2(java.nio.ByteBuffer dest, int destOffset, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _t64, float _sp0) {
        dest.putFloat(destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t10 = ((otherW) * (otherW) + (((otherZ) * (otherZ) + (((otherX) * (otherX) + (otherY * otherY))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        return slerp_api_mulAdd_s473d06bc_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), ((otherW * _t14) * (_t19) + (((otherZ * _t14) * (_t21) + (((otherX * _t14) * (_t16) + (otherY * _t14 * _t24)))))));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd_s473d06bc_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31) {
        float _t40 = ((otherW) * (_t14) - (_t31 * _t19));
        float _t41 = ((otherZ) * (_t14) - (_t31 * _t21));
        float _t42 = ((otherX) * (_t14) - (_t31 * _t16));
        float _t43 = ((otherY) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.putFloat(destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        dest.putFloat(destOffset + 4, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.putFloat(destOffset + 8, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.putFloat(destOffset + 12, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW, t, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, epsilon);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    dest.putFloat(destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.putFloat(destOffset + 4, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.putFloat(destOffset + 8, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.putFloat(destOffset + 12, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.putFloat(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.putFloat(destOffset + 4, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.putFloat(destOffset + 8, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.putFloat(destOffset + 12, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.putFloat(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.putFloat(destOffset + 4, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.putFloat(destOffset + 8, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.putFloat(destOffset + 12, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.putFloat(destOffset, java.lang.Math.fma(t, otherX - _selfx, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(t, otherY - _selfy, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(t, otherZ - _selfz, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(t, otherW - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t7 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    dest.putFloat(destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.putFloat(destOffset + 4, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.putFloat(destOffset + 8, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.putFloat(destOffset + 12, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.putFloat(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.putFloat(destOffset + 4, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.putFloat(destOffset + 8, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.putFloat(destOffset + 12, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.putFloat(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.putFloat(destOffset + 4, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.putFloat(destOffset + 8, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.putFloat(destOffset + 12, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.putFloat(destOffset, ((t) * (otherX - _selfx) + (_selfx)));
            dest.putFloat(destOffset + 4, ((t) * (otherY - _selfy) + (_selfy)));
            dest.putFloat(destOffset + 8, ((t) * (otherZ - _selfz) + (_selfz)));
            dest.putFloat(destOffset + 12, ((t) * (otherW - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float4OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t9 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t10 = java.lang.Math.fma(_otherw, _otherw, java.lang.Math.fma(_otherz, _otherz, java.lang.Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_api_fma_se00693dd_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma_se00693dd_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float epsilon, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = java.lang.Math.fma(_otherw * _t14, _t19, java.lang.Math.fma(_otherz * _t14, _t21, java.lang.Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        float _t40 = java.lang.Math.fma(_otherw, _t14, -(_t31 * _t19));
        float _t41 = java.lang.Math.fma(_otherz, _t14, -(_t31 * _t21));
        float _t42 = java.lang.Math.fma(_otherx, _t14, -(_t31 * _t16));
        float _t43 = java.lang.Math.fma(_othery, _t14, -(_t31 * _t24));
        float _t48 = -java.lang.Math.fma(_t40, _t19, java.lang.Math.fma(_t41, _t21, java.lang.Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = java.lang.Math.fma(_t48, _t19, _t40);
        float _t50 = java.lang.Math.fma(_t48, _t21, _t41);
        float _t51 = java.lang.Math.fma(_t48, _t16, _t42);
        float _t52 = java.lang.Math.fma(_t48, _t24, _t43);
        float _t57 = java.lang.Math.fma(_t49, _t49, java.lang.Math.fma(_t50, _t50, java.lang.Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_api_fma_se00693dd_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.cosWithSin(_t61), _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)));
    }

    /** Piece 3 of {@code slerp_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_fma_se00693dd_2(java.nio.ByteBuffer dest, int destOffset, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _t64, float _sp0) {
        dest.putFloat(destOffset, java.lang.Math.fma(_t16, _t64, _sp0 * _t51));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t24, _t64, _sp0 * _t52));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t21, _t64, _sp0 * _t50));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_t19, _t64, _sp0 * _t49));
        return dest;
    }

    /** {@code slerp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t9 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t10 = ((_otherw) * (_otherw) + (((_otherz) * (_otherz) + (((_otherx) * (_otherx) + (_othery * _othery))))));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_api_mulAdd_sc9711e38_1(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd_sc9711e38_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float epsilon, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = ((_otherw * _t14) * (_t19) + (((_otherz * _t14) * (_t21) + (((_otherx * _t14) * (_t16) + (_othery * _t14 * _t24))))));
        float _t40 = ((_otherw) * (_t14) - (_t31 * _t19));
        float _t41 = ((_otherz) * (_t14) - (_t31 * _t21));
        float _t42 = ((_otherx) * (_t14) - (_t31 * _t16));
        float _t43 = ((_othery) * (_t14) - (_t31 * _t24));
        float _t48 = -((_t40) * (_t19) + (((_t41) * (_t21) + (((_t42) * (_t16) + (_t43 * _t24))))));
        float _t49 = ((_t48) * (_t19) + (_t40));
        float _t50 = ((_t48) * (_t21) + (_t41));
        float _t51 = ((_t48) * (_t16) + (_t42));
        float _t52 = ((_t48) * (_t24) + (_t43));
        float _t57 = ((_t49) * (_t49) + (((_t50) * (_t50) + (((_t51) * (_t51) + (_t52 * _t52))))));
        if (!(_t57 > epsilon && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _t64 = _t28 * Math.cosWithSin(_t61);
        float _sp0 = _t28 * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        dest.putFloat(destOffset, ((_t16) * (_t64) + (_sp0 * _t51)));
        return slerp_api_mulAdd_sc9711e38_2(dest, destOffset, _t19, _t21, _t24, _t49, _t50, _t52, _t64, _sp0);
    }

    /** Piece 3 of {@code slerp_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_mulAdd_sc9711e38_2(java.nio.ByteBuffer dest, int destOffset, float _t19, float _t21, float _t24, float _t49, float _t50, float _t52, float _t64, float _sp0) {
        dest.putFloat(destOffset + 4, ((_t24) * (_t64) + (_sp0 * _t52)));
        dest.putFloat(destOffset + 8, ((_t21) * (_t64) + (_sp0 * _t50)));
        dest.putFloat(destOffset + 12, ((_t19) * (_t64) + (_sp0 * _t49)));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float epsilon) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float epsilon) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float4OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t, epsilon);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float epsilon) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return slerp_degenerate_api_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
        return slerp_degenerate_api_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, epsilon);
    }

    /** {@code slerp_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t37 = java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = java.lang.Math.fma(_t40 * _t17, _t43, java.lang.Math.fma(_t40 * _t18, _t45, java.lang.Math.fma(_t40 * _t19, _t47, _t40 * _t20 * _t49)));
        float _t72 = java.lang.Math.fma(_t40, _t17, -(_t63 * _t43));
        float _t73 = java.lang.Math.fma(_t40, _t18, -(_t63 * _t45));
        float _t74 = java.lang.Math.fma(_t40, _t19, -(_t63 * _t47));
        float _t75 = java.lang.Math.fma(_t40, _t20, -(_t63 * _t49));
        float _t80 = -java.lang.Math.fma(_t72, _t43, java.lang.Math.fma(_t73, _t45, java.lang.Math.fma(_t74, _t47, _t75 * _t49)));
        float _t81 = java.lang.Math.fma(_t80, _t43, _t72);
        float _t82 = java.lang.Math.fma(_t80, _t45, _t73);
        float _t83 = java.lang.Math.fma(_t80, _t47, _t74);
        float _t84 = java.lang.Math.fma(_t80, _t49, _t75);
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = java.lang.Math.fma(_t97, _t97, java.lang.Math.fma(_t98, _t98, java.lang.Math.fma(_t99, _t99, _t100 * _t100)));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (java.lang.Math.fma(_t81, _t81, java.lang.Math.fma(_t82, _t82, java.lang.Math.fma(_t83, _t83, _t84 * _t84))) <= epsilon) {
                    dest.putFloat(destOffset, java.lang.Math.fma(_t113, _t50, _t114 * _t47) * _t25_inv);
                    dest.putFloat(destOffset + 4, java.lang.Math.fma(_t113, _t47, _t114 * _t49) * _t25_inv);
                    dest.putFloat(destOffset + 8, java.lang.Math.fma(_t113, _t51, _t114 * _t45) * _t25_inv);
                    dest.putFloat(destOffset + 12, java.lang.Math.fma(_t113, _t45, _t114 * _t43) * _t25_inv);
                } else {
                    dest.putFloat(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                    dest.putFloat(destOffset + 4, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                    dest.putFloat(destOffset + 8, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                    dest.putFloat(destOffset + 12, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
                }
            } else {
                dest.putFloat(destOffset, java.lang.Math.fma(_t113, _t121, _t114 * _t47) * _t25_inv);
                dest.putFloat(destOffset + 4, java.lang.Math.fma(_t113, _t119, _t114 * _t49) * _t25_inv);
                dest.putFloat(destOffset + 8, java.lang.Math.fma(_t113, _t122, _t114 * _t45) * _t25_inv);
                dest.putFloat(destOffset + 12, java.lang.Math.fma(_t113, _t120, _t114 * _t43) * _t25_inv);
            }
        } else {
            dest.putFloat(destOffset, java.lang.Math.fma(t, _otherx - _selfx, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(t, _othery - _selfy, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(t, _otherz - _selfz, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(t, _otherw - _selfw, _selfw));
        }
        return dest;
    }

    /** {@code slerp_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer slerp_degenerate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float epsilon) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t7 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t8 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
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
        float _t36 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t37 = ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))));
        float _t40 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41 = (1.0f / (float) java.lang.Math.sqrt(_t37));
        float _t43 = _t41 * _t21;
        float _t45 = _t41 * _t22;
        float _t47 = _t41 * _t23;
        float _t49 = _t41 * _t24;
        float _t50 = -_t49;
        float _t51 = -_t43;
        float _t60 = t * (float) java.lang.Math.sqrt(_t36) * (_t25 / _t7) + (1.0f - t) * (float) java.lang.Math.sqrt(_t37) * (_t25 / _t8);
        float _t63 = ((_t40 * _t17) * (_t43) + (((_t40 * _t18) * (_t45) + (((_t40 * _t19) * (_t47) + (_t40 * _t20 * _t49))))));
        float _t72 = ((_t40) * (_t17) - (_t63 * _t43));
        float _t73 = ((_t40) * (_t18) - (_t63 * _t45));
        float _t74 = ((_t40) * (_t19) - (_t63 * _t47));
        float _t75 = ((_t40) * (_t20) - (_t63 * _t49));
        float _t80 = -((_t72) * (_t43) + (((_t73) * (_t45) + (((_t74) * (_t47) + (_t75 * _t49))))));
        float _t81 = ((_t80) * (_t43) + (_t72));
        float _t82 = ((_t80) * (_t45) + (_t73));
        float _t83 = ((_t80) * (_t47) + (_t74));
        float _t84 = ((_t80) * (_t49) + (_t75));
        float _t90 = unitScale(_t82, _t81, Math.max(java.lang.Math.abs(_t83), java.lang.Math.abs(_t84)));
        float _t97 = _t81 * _t90;
        float _t98 = _t82 * _t90;
        float _t99 = _t83 * _t90;
        float _t100 = _t84 * _t90;
        float _t106 = ((_t97) * (_t97) + (((_t98) * (_t98) + (((_t99) * (_t99) + (_t100 * _t100))))));
        float _t108 = (1.0f / (float) java.lang.Math.sqrt(_t106));
        float _t110 = t * Math.atan2((float) java.lang.Math.sqrt(_t106), _t63 * _t90);
        float _t113 = _t60 * Math.sinWithCos(_t110);
        float _t114 = _t60 * Math.cosWithSin(_t110);
        float _t119, _t120, _t121, _t122;
        if (_t106 > 0.0f) {
            _t119 = _t108 * _t100;
            _t120 = _t108 * _t97;
            _t121 = _t108 * _t99;
            _t122 = _t108 * _t98;
        } else {
            _t119 = _t47;
            _t120 = _t45;
            _t121 = _t50;
            _t122 = _t51;
        }
        if (_t36 * _t37 > 0.0f) {
            if (_t63 < 0.0f) {
                if (((_t81) * (_t81) + (((_t82) * (_t82) + (((_t83) * (_t83) + (_t84 * _t84)))))) <= epsilon) {
                    dest.putFloat(destOffset, ((_t113) * (_t50) + (_t114 * _t47)) * _t25_inv);
                    dest.putFloat(destOffset + 4, ((_t113) * (_t47) + (_t114 * _t49)) * _t25_inv);
                    dest.putFloat(destOffset + 8, ((_t113) * (_t51) + (_t114 * _t45)) * _t25_inv);
                    dest.putFloat(destOffset + 12, ((_t113) * (_t45) + (_t114 * _t43)) * _t25_inv);
                } else {
                    dest.putFloat(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                    dest.putFloat(destOffset + 4, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                    dest.putFloat(destOffset + 8, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                    dest.putFloat(destOffset + 12, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
                }
            } else {
                dest.putFloat(destOffset, ((_t113) * (_t121) + (_t114 * _t47)) * _t25_inv);
                dest.putFloat(destOffset + 4, ((_t113) * (_t119) + (_t114 * _t49)) * _t25_inv);
                dest.putFloat(destOffset + 8, ((_t113) * (_t122) + (_t114 * _t45)) * _t25_inv);
                dest.putFloat(destOffset + 12, ((_t113) * (_t120) + (_t114 * _t43)) * _t25_inv);
            }
        } else {
            dest.putFloat(destOffset, ((t) * (_otherx - _selfx) + (_selfx)));
            dest.putFloat(destOffset + 4, ((t) * (_othery - _selfy) + (_selfy)));
            dest.putFloat(destOffset + 8, ((t) * (_otherz - _selfz) + (_selfz)));
            dest.putFloat(destOffset + 12, ((t) * (_otherw - _selfw) + (_selfw)));
        }
        return dest;
    }

    public static java.nio.ByteBuffer absolute_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer absolute_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.abs(_selfx));
        dest.putFloat(destOffset + 4, java.lang.Math.abs(_selfy));
        dest.putFloat(destOffset + 8, java.lang.Math.abs(_selfz));
        dest.putFloat(destOffset + 12, java.lang.Math.abs(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer acos_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer acos_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.acos(_selfx));
        dest.putFloat(destOffset + 4, Math.acos(_selfy));
        dest.putFloat(destOffset + 8, Math.acos(_selfz));
        dest.putFloat(destOffset + 12, Math.acos(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(scalar, bX, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(scalar, bY, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(scalar, bZ, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(scalar, bW, _selfw));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((scalar) * (bX) + (_selfx)));
            dest.putFloat(destOffset + 4, ((scalar) * (bY) + (_selfy)));
            dest.putFloat(destOffset + 8, ((scalar) * (bZ) + (_selfz)));
            dest.putFloat(destOffset + 12, ((scalar) * (bW) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, float scalar) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _bx = b.getFloat(bOffset);
            float _by = b.getFloat(bOffset + 4);
            float _bz = b.getFloat(bOffset + 8);
            float _bw = b.getFloat(bOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(scalar, _bx, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(scalar, _by, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(scalar, _bz, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(scalar, _bw, _selfw));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _bx = b.getFloat(bOffset);
            float _by = b.getFloat(bOffset + 4);
            float _bz = b.getFloat(bOffset + 8);
            float _bw = b.getFloat(bOffset + 12);
            dest.putFloat(destOffset, ((scalar) * (_bx) + (_selfx)));
            dest.putFloat(destOffset + 4, ((scalar) * (_by) + (_selfy)));
            dest.putFloat(destOffset + 8, ((scalar) * (_bz) + (_selfz)));
            dest.putFloat(destOffset + 12, ((scalar) * (_bw) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, java.lang.Math.fma(bX, cX, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(bY, cY, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(bZ, cZ, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(bW, cW, _selfw));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            dest.putFloat(destOffset, ((bX) * (cX) + (_selfx)));
            dest.putFloat(destOffset + 4, ((bY) * (cY) + (_selfy)));
            dest.putFloat(destOffset + 8, ((bZ) * (cZ) + (_selfz)));
            dest.putFloat(destOffset + 12, ((bW) * (cW) + (_selfw)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Float4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return addScaled_api_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _bx = b.getFloat(bOffset);
        float _by = b.getFloat(bOffset + 4);
        float _bz = b.getFloat(bOffset + 8);
        float _bw = b.getFloat(bOffset + 12);
        float _cx = c.getFloat(cOffset);
        float _cy = c.getFloat(cOffset + 4);
        float _cz = c.getFloat(cOffset + 8);
        float _cw = c.getFloat(cOffset + 12);
        dest.putFloat(destOffset, java.lang.Math.fma(_bx, _cx, _selfx));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_by, _cy, _selfy));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_bz, _cz, _selfz));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_bw, _cw, _selfw));
        return dest;
    }

    /** {@code addScaled_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer addScaled_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _bx = b.getFloat(bOffset);
        float _by = b.getFloat(bOffset + 4);
        float _bz = b.getFloat(bOffset + 8);
        float _bw = b.getFloat(bOffset + 12);
        float _cx = c.getFloat(cOffset);
        float _cy = c.getFloat(cOffset + 4);
        float _cz = c.getFloat(cOffset + 8);
        float _cw = c.getFloat(cOffset + 12);
        dest.putFloat(destOffset, ((_bx) * (_cx) + (_selfx)));
        dest.putFloat(destOffset + 4, ((_by) * (_cy) + (_selfy)));
        dest.putFloat(destOffset + 8, ((_bz) * (_cz) + (_selfz)));
        dest.putFloat(destOffset + 12, ((_bw) * (_cw) + (_selfw)));
        return dest;
    }

    public static float angleBetween_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_api_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_api_fma(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t6 = otherZ * -_selfw;
        float _t7 = otherY * -_selfw;
        float _t8 = -(otherY * _selfz);
        float _t9 = -otherX * _selfw;
        float _t10 = -otherX * _selfy;
        float _t11 = -otherX * _selfz;
        float _t24 = java.lang.Math.fma(otherW, _selfz, _t6) - java.lang.Math.fma(otherZ, _selfw, _t6);
        float _t25 = java.lang.Math.fma(otherW, _selfy, _t7) - java.lang.Math.fma(otherY, _selfw, _t7);
        float _t26 = java.lang.Math.fma(otherZ, _selfy, _t8) - java.lang.Math.fma(otherY, _selfz, _t8);
        float _t27 = java.lang.Math.fma(otherW, _selfx, _t9) - java.lang.Math.fma(otherX, _selfw, _t9);
        float _t28 = java.lang.Math.fma(otherY, _selfx, _t10) - java.lang.Math.fma(otherX, _selfy, _t10);
        float _t29 = java.lang.Math.fma(otherZ, _selfx, _t11) - java.lang.Math.fma(otherX, _selfz, _t11);
        float _ct0 = java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(otherW, _selfw, java.lang.Math.fma(otherZ, _selfz, java.lang.Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    /** {@code angleBetween_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_api_mulAdd(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t6 = otherZ * -_selfw;
        float _t7 = otherY * -_selfw;
        float _t8 = -(otherY * _selfz);
        float _t9 = -otherX * _selfw;
        float _t10 = -otherX * _selfy;
        float _t11 = -otherX * _selfz;
        float _t24 = ((otherW) * (_selfz) + (_t6)) - ((otherZ) * (_selfw) + (_t6));
        float _t25 = ((otherW) * (_selfy) + (_t7)) - ((otherY) * (_selfw) + (_t7));
        float _t26 = ((otherZ) * (_selfy) + (_t8)) - ((otherY) * (_selfz) + (_t8));
        float _t27 = ((otherW) * (_selfx) + (_t9)) - ((otherX) * (_selfw) + (_t9));
        float _t28 = ((otherY) * (_selfx) + (_t10)) - ((otherX) * (_selfy) + (_t10));
        float _t29 = ((otherZ) * (_selfx) + (_t11)) - ((otherX) * (_selfz) + (_t11));
        float _ct0 = ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))))))))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((otherW) * (_selfw) + (((otherZ) * (_selfz) + (((otherX) * (_selfx) + (otherY * _selfy)))))));
    }

    public static float angleBetween_degenerate(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float angleBetween_degenerate_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, otherX, otherY, otherZ, otherW);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@code angleBetween_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_api_fma(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        float _t34 = -(_t20 * _t17);
        return angleBetween_degenerate_api_fma_s949040bb_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32), java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33), java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_api_fma_s949040bb_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t35, float _t48, float _t49, float _t50, float _t51, float _t52) {
        float _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** {@code angleBetween_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_api_mulAdd(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t6 = unitScale(otherZ, otherW, Math.max(java.lang.Math.abs(otherX), java.lang.Math.abs(otherY)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = otherW * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = otherZ * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = otherY * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = otherX * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        float _t34 = -(_t20 * _t17);
        float _t35 = _t20 * -_t19;
        return angleBetween_degenerate_api_mulAdd_s53b7b686_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32)), ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33)), ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34)), ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_api_mulAdd_s53b7b686_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t48, float _t49, float _t50, float _t51, float _t52, float _t53) {
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static float angleBetween_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float4OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_api_fma(src, srcOffset, other, otherOffset);
        return angleBetween_api_mulAdd(src, srcOffset, other, otherOffset);
    }

    /** {@code angleBetween_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_api_fma(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t6 = _otherz * -_selfw;
        float _t7 = _othery * -_selfw;
        float _t8 = -(_othery * _selfz);
        float _t9 = -_otherx * _selfw;
        float _t10 = -_otherx * _selfy;
        float _t11 = -_otherx * _selfz;
        return angleBetween_api_fma_sd9f688b8_1(src, srcOffset, other, otherOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, java.lang.Math.fma(_otherw, _selfz, _t6) - java.lang.Math.fma(_otherz, _selfw, _t6), java.lang.Math.fma(_otherw, _selfy, _t7) - java.lang.Math.fma(_othery, _selfw, _t7), java.lang.Math.fma(_otherz, _selfy, _t8) - java.lang.Math.fma(_othery, _selfz, _t8), java.lang.Math.fma(_otherw, _selfx, _t9) - java.lang.Math.fma(_otherx, _selfw, _t9), java.lang.Math.fma(_othery, _selfx, _t10) - java.lang.Math.fma(_otherx, _selfy, _t10), java.lang.Math.fma(_otherz, _selfx, _t11) - java.lang.Math.fma(_otherx, _selfz, _t11));
    }

    /** Piece 2 of {@code angleBetween_api_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_api_fma_sd9f688b8_1(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        float _ct0 = java.lang.Math.fma(_t24, _t24, java.lang.Math.fma(_t25, _t25, java.lang.Math.fma(_t26, _t26, java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), java.lang.Math.fma(_otherw, _selfw, java.lang.Math.fma(_otherz, _selfz, java.lang.Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    /** {@code angleBetween_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_api_mulAdd(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t6 = _otherz * -_selfw;
        float _t7 = _othery * -_selfw;
        float _t8 = -(_othery * _selfz);
        float _t9 = -_otherx * _selfw;
        float _t10 = -_otherx * _selfy;
        float _t11 = -_otherx * _selfz;
        float _t24 = ((_otherw) * (_selfz) + (_t6)) - ((_otherz) * (_selfw) + (_t6));
        float _t25 = ((_otherw) * (_selfy) + (_t7)) - ((_othery) * (_selfw) + (_t7));
        float _t26 = ((_otherz) * (_selfy) + (_t8)) - ((_othery) * (_selfz) + (_t8));
        float _t27 = ((_otherw) * (_selfx) + (_t9)) - ((_otherx) * (_selfw) + (_t9));
        float _t28 = ((_othery) * (_selfx) + (_t10)) - ((_otherx) * (_selfy) + (_t10));
        float _t29 = ((_otherz) * (_selfx) + (_t11)) - ((_otherx) * (_selfz) + (_t11));
        return angleBetween_api_mulAdd_s2f1d0eaf_1(src, srcOffset, other, otherOffset, _selfx, _selfy, _selfz, _selfw, _otherx, _othery, _otherz, _otherw, ((_t24) * (_t24) + (((_t25) * (_t25) + (((_t26) * (_t26) + (((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29)))))))))));
    }

    /** Piece 2 of {@code angleBetween_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_api_mulAdd_s2f1d0eaf_1(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _otherx, float _othery, float _otherz, float _otherw, float _ct0) {
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), ((_otherw) * (_selfw) + (((_otherz) * (_selfz) + (((_otherx) * (_selfx) + (_othery * _selfy)))))));
    }

    public static float angleBetween_degenerate(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, other, otherOffset);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float4OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_degenerate_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return angleBetween_degenerate_api_fma(src, srcOffset, other, otherOffset);
        return angleBetween_degenerate_api_mulAdd(src, srcOffset, other, otherOffset);
    }

    /** {@code angleBetween_degenerate_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_api_fma(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        return angleBetween_degenerate_api_fma_s2d620173_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, _t22 * -_t19, -(_t20 * _t17), _t20 * -_t19, java.lang.Math.fma(_t16, _t17, _t30) - java.lang.Math.fma(_t18, _t19, _t30), java.lang.Math.fma(_t20, _t21, _t31) - java.lang.Math.fma(_t22, _t23, _t31), java.lang.Math.fma(_t18, _t21, _t32) - java.lang.Math.fma(_t22, _t17, _t32));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_fma}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_api_fma_s2d620173_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t33, float _t34, float _t35, float _t48, float _t49, float _t50) {
        float _t51 = java.lang.Math.fma(_t16, _t21, _t33) - java.lang.Math.fma(_t22, _t19, _t33);
        float _t52 = java.lang.Math.fma(_t18, _t23, _t34) - java.lang.Math.fma(_t20, _t17, _t34);
        float _t53 = java.lang.Math.fma(_t16, _t23, _t35) - java.lang.Math.fma(_t20, _t19, _t35);
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t70, _t70, java.lang.Math.fma(_t71, _t71, java.lang.Math.fma(_t72, _t72, java.lang.Math.fma(_t73, _t73, java.lang.Math.fma(_t74, _t74, _t75 * _t75)))))), java.lang.Math.fma(_t16, _t19, java.lang.Math.fma(_t18, _t17, java.lang.Math.fma(_t22, _t21, _t20 * _t23))) * _t63);
    }

    /** {@code angleBetween_degenerate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float angleBetween_degenerate_api_mulAdd(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        float _t6 = unitScale(_otherz, _otherw, Math.max(java.lang.Math.abs(_otherx), java.lang.Math.abs(_othery)));
        float _t7 = unitScale(_selfz, _selfw, Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_selfy)));
        float _t16 = _otherw * _t6;
        float _t17 = _selfz * _t7;
        float _t18 = _otherz * _t6;
        float _t19 = _selfw * _t7;
        float _t20 = _othery * _t6;
        float _t21 = _selfx * _t7;
        float _t22 = _otherx * _t6;
        float _t23 = _selfy * _t7;
        float _t30 = _t18 * -_t19;
        float _t31 = -_t22 * _t23;
        float _t32 = -_t22 * _t17;
        float _t33 = _t22 * -_t19;
        return angleBetween_degenerate_api_mulAdd_sb6ed7882_1(_t16, _t17, _t18, _t19, _t20, _t21, _t22, _t23, -(_t20 * _t17), _t20 * -_t19, ((_t16) * (_t17) + (_t30)) - ((_t18) * (_t19) + (_t30)), ((_t20) * (_t21) + (_t31)) - ((_t22) * (_t23) + (_t31)), ((_t18) * (_t21) + (_t32)) - ((_t22) * (_t17) + (_t32)), ((_t16) * (_t21) + (_t33)) - ((_t22) * (_t19) + (_t33)));
    }

    /** Piece 2 of {@code angleBetween_degenerate_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float angleBetween_degenerate_api_mulAdd_sb6ed7882_1(float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t34, float _t35, float _t48, float _t49, float _t50, float _t51) {
        float _t52 = ((_t18) * (_t23) + (_t34)) - ((_t20) * (_t17) + (_t34));
        float _t53 = ((_t16) * (_t23) + (_t35)) - ((_t20) * (_t19) + (_t35));
        float _t63 = unitScale(Math.max(java.lang.Math.abs(_t49), java.lang.Math.abs(_t50)), Math.max(java.lang.Math.abs(_t51), java.lang.Math.abs(_t52)), Math.max(java.lang.Math.abs(_t53), java.lang.Math.abs(_t48)));
        float _t70 = _t48 * _t63;
        float _t71 = _t53 * _t63;
        float _t72 = _t52 * _t63;
        float _t73 = _t51 * _t63;
        float _t74 = _t49 * _t63;
        float _t75 = _t50 * _t63;
        return Math.atan2((float) java.lang.Math.sqrt(((_t70) * (_t70) + (((_t71) * (_t71) + (((_t72) * (_t72) + (((_t73) * (_t73) + (((_t74) * (_t74) + (_t75 * _t75))))))))))), ((_t16) * (_t19) + (((_t18) * (_t17) + (((_t22) * (_t21) + (_t20 * _t23)))))) * _t63);
    }

    public static java.nio.ByteBuffer asin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer asin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.asin(_selfx));
        dest.putFloat(destOffset + 4, Math.asin(_selfy));
        dest.putFloat(destOffset + 8, Math.asin(_selfz));
        dest.putFloat(destOffset + 12, Math.asin(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer atan_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer atan_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.atan(_selfx));
        dest.putFloat(destOffset + 4, Math.atan(_selfy));
        dest.putFloat(destOffset + 8, Math.atan(_selfz));
        dest.putFloat(destOffset + 12, Math.atan(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float x) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.atan2(_selfx, x));
        dest.putFloat(destOffset + 4, Math.atan2(_selfy, x));
        dest.putFloat(destOffset + 8, Math.atan2(_selfz, x));
        dest.putFloat(destOffset + 12, Math.atan2(_selfw, x));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float xX, float xY, float xZ, float xW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ, xW);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float xX, float xY, float xZ, float xW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.atan2(_selfx, xX));
        dest.putFloat(destOffset + 4, Math.atan2(_selfy, xY));
        dest.putFloat(destOffset + 8, Math.atan2(_selfz, xZ));
        dest.putFloat(destOffset + 12, Math.atan2(_selfw, xW));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + xOffset;
        Float4OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _xx = x.getFloat(xOffset);
        float _xy = x.getFloat(xOffset + 4);
        float _xz = x.getFloat(xOffset + 8);
        float _xw = x.getFloat(xOffset + 12);
        dest.putFloat(destOffset, Math.atan2(_selfx, _xx));
        dest.putFloat(destOffset + 4, Math.atan2(_selfy, _xy));
        dest.putFloat(destOffset + 8, Math.atan2(_selfz, _xz));
        dest.putFloat(destOffset + 12, Math.atan2(_selfw, _xw));
        return dest;
    }

    public static java.nio.ByteBuffer cbrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cbrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.cbrt(_selfx));
        dest.putFloat(destOffset + 4, Math.cbrt(_selfy));
        dest.putFloat(destOffset + 8, Math.cbrt(_selfz));
        dest.putFloat(destOffset + 12, Math.cbrt(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer ceil_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer ceil_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.ceil(_selfx));
        dest.putFloat(destOffset + 4, Math.ceil(_selfy));
        dest.putFloat(destOffset + 8, Math.ceil(_selfz));
        dest.putFloat(destOffset + 12, Math.ceil(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float min, float max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float min, float max) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.min(Math.max(_selfx, min), max));
        dest.putFloat(destOffset + 4, Math.min(Math.max(_selfy, min), max));
        dest.putFloat(destOffset + 8, Math.min(Math.max(_selfz, min), max));
        dest.putFloat(destOffset + 12, Math.min(Math.max(_selfw, min), max));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.min(Math.max(_selfx, minX), maxX));
        dest.putFloat(destOffset + 4, Math.min(Math.max(_selfy, minY), maxY));
        dest.putFloat(destOffset + 8, Math.min(Math.max(_selfz, minZ), maxZ));
        dest.putFloat(destOffset + 12, Math.min(Math.max(_selfw, minW), maxW));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + minOffset;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + maxOffset;
        Float4OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _minx = min.getFloat(minOffset);
        float _miny = min.getFloat(minOffset + 4);
        float _minz = min.getFloat(minOffset + 8);
        float _minw = min.getFloat(minOffset + 12);
        float _maxx = max.getFloat(maxOffset);
        float _maxy = max.getFloat(maxOffset + 4);
        float _maxz = max.getFloat(maxOffset + 8);
        float _maxw = max.getFloat(maxOffset + 12);
        dest.putFloat(destOffset, Math.min(Math.max(_selfx, _minx), _maxx));
        dest.putFloat(destOffset + 4, Math.min(Math.max(_selfy, _miny), _maxy));
        dest.putFloat(destOffset + 8, Math.min(Math.max(_selfz, _minz), _maxz));
        dest.putFloat(destOffset + 12, Math.min(Math.max(_selfw, _minw), _maxw));
        return dest;
    }

    public static float compAdd_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static float compAdd_api(java.nio.ByteBuffer src, int srcOffset) {
        return src.getFloat(srcOffset + 12) + (src.getFloat(srcOffset + 8) + (src.getFloat(srcOffset) + src.getFloat(srcOffset + 4)));
    }

    public static float compMax_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static float compMax_api(java.nio.ByteBuffer src, int srcOffset) {
        return Math.max(Math.max(Math.max(src.getFloat(srcOffset), src.getFloat(srcOffset + 4)), src.getFloat(srcOffset + 8)), src.getFloat(srcOffset + 12));
    }

    public static float compMin_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static float compMin_api(java.nio.ByteBuffer src, int srcOffset) {
        return Math.min(Math.min(Math.min(src.getFloat(srcOffset), src.getFloat(srcOffset + 4)), src.getFloat(srcOffset + 8)), src.getFloat(srcOffset + 12));
    }

    public static float compMul_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static float compMul_api(java.nio.ByteBuffer src, int srcOffset) {
        return src.getFloat(srcOffset + 12) * src.getFloat(srcOffset + 8) * src.getFloat(srcOffset) * src.getFloat(srcOffset + 4);
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sign) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.copySign(_selfx, sign));
        dest.putFloat(destOffset + 4, Math.copySign(_selfy, sign));
        dest.putFloat(destOffset + 8, Math.copySign(_selfz, sign));
        dest.putFloat(destOffset + 12, Math.copySign(_selfw, sign));
        return dest;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float signX, float signY, float signZ, float signW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ, signW);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float signX, float signY, float signZ, float signW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.copySign(_selfx, signX));
        dest.putFloat(destOffset + 4, Math.copySign(_selfy, signY));
        dest.putFloat(destOffset + 8, Math.copySign(_selfz, signZ));
        dest.putFloat(destOffset + 12, Math.copySign(_selfw, signW));
        return dest;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + signOffset;
        Float4OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _signx = sign.getFloat(signOffset);
        float _signy = sign.getFloat(signOffset + 4);
        float _signz = sign.getFloat(signOffset + 8);
        float _signw = sign.getFloat(signOffset + 12);
        dest.putFloat(destOffset, Math.copySign(_selfx, _signx));
        dest.putFloat(destOffset + 4, Math.copySign(_selfy, _signy));
        dest.putFloat(destOffset + 8, Math.copySign(_selfz, _signz));
        dest.putFloat(destOffset + 12, Math.copySign(_selfw, _signw));
        return dest;
    }

    public static java.nio.ByteBuffer cos_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cos_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.cos(_selfx));
        dest.putFloat(destOffset + 4, Math.cos(_selfy));
        dest.putFloat(destOffset + 8, Math.cos(_selfz));
        dest.putFloat(destOffset + 12, Math.cos(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer cosh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cosh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.cosh(_selfx));
        dest.putFloat(destOffset + 4, Math.cosh(_selfy));
        dest.putFloat(destOffset + 8, Math.cosh(_selfz));
        dest.putFloat(destOffset + 12, Math.cosh(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer cross_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    public static java.nio.ByteBuffer cross_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cross_api_fma(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return cross_api_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
    }

    /** {@code cross_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer cross_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t6 = -vZ * wY;
        float _t7 = -vW * wZ;
        float _t8 = -vW * wY;
        float _t9 = -vZ * wX;
        float _t10 = -vW * wX;
        float _t11 = -(vY * wX);
        float _t24 = java.lang.Math.fma(vY, wZ, _t6) - java.lang.Math.fma(vZ, wY, _t6);
        float _t25 = java.lang.Math.fma(vZ, wW, _t7) - java.lang.Math.fma(vW, wZ, _t7);
        float _t26 = java.lang.Math.fma(vY, wW, _t8) - java.lang.Math.fma(vW, wY, _t8);
        float _t27 = java.lang.Math.fma(vX, wZ, _t9) - java.lang.Math.fma(vZ, wX, _t9);
        float _t28 = java.lang.Math.fma(vX, wW, _t10) - java.lang.Math.fma(vW, wX, _t10);
        dest.putFloat(destOffset, java.lang.Math.fma(_selfw, _t24, java.lang.Math.fma(_selfy, _t25, -_selfz * _t26)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(-_selfw, _t27, java.lang.Math.fma(_selfz, _t28, -_selfx * _t25)));
        return cross_api_fma_s7c59be6f_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t24, _t26, _t27, _t28, java.lang.Math.fma(vX, wY, _t11) - java.lang.Math.fma(vY, wX, _t11));
    }

    /** Piece 2 of {@code cross_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer cross_api_fma_s7c59be6f_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t24, float _t26, float _t27, float _t28, float _t29) {
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfw, _t29, java.lang.Math.fma(_selfx, _t26, -(_selfy * _t28))));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(-_selfz, _t29, java.lang.Math.fma(_selfy, _t27, -_selfx * _t24)));
        return dest;
    }

    /** {@code cross_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer cross_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t6 = -vZ * wY;
        float _t7 = -vW * wZ;
        float _t8 = -vW * wY;
        float _t9 = -vZ * wX;
        float _t10 = -vW * wX;
        float _t11 = -(vY * wX);
        float _t24 = ((vY) * (wZ) + (_t6)) - ((vZ) * (wY) + (_t6));
        float _t25 = ((vZ) * (wW) + (_t7)) - ((vW) * (wZ) + (_t7));
        float _t26 = ((vY) * (wW) + (_t8)) - ((vW) * (wY) + (_t8));
        float _t27 = ((vX) * (wZ) + (_t9)) - ((vZ) * (wX) + (_t9));
        float _t28 = ((vX) * (wW) + (_t10)) - ((vW) * (wX) + (_t10));
        float _t29 = ((vX) * (wY) + (_t11)) - ((vY) * (wX) + (_t11));
        dest.putFloat(destOffset, ((_selfw) * (_t24) + (((_selfy) * (_t25) - (_selfz * _t26)))));
        dest.putFloat(destOffset + 4, ((((_selfz) * (_t28) - (_selfx * _t25))) - (_selfw) * (_t27)));
        dest.putFloat(destOffset + 8, ((_selfw) * (_t29) + (((_selfx) * (_t26) - (_selfy * _t28)))));
        dest.putFloat(destOffset + 12, ((((_selfy) * (_t27) - (_selfx * _t24))) - (_selfz) * (_t29)));
        return dest;
    }

    public static java.nio.ByteBuffer cross_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset, java.nio.ByteBuffer w, int wOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        long _wBase = UnsafeOpsHolder.U.getLong(w, UnsafeCopy.BB_ADDRESS_OFFSET) + wOffset;
        Float4OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _vBase, _wBase);
        return dest;
    }

    public static java.nio.ByteBuffer cross_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset, java.nio.ByteBuffer w, int wOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return cross_api_fma(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
        return cross_api_mulAdd(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
    }

    /** {@code cross_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer cross_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset, java.nio.ByteBuffer w, int wOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        float _vw = v.getFloat(vOffset + 12);
        float _wx = w.getFloat(wOffset);
        float _wy = w.getFloat(wOffset + 4);
        float _wz = w.getFloat(wOffset + 8);
        float _ww = w.getFloat(wOffset + 12);
        float _t6 = -_vz * _wy;
        float _t7 = -_vw * _wz;
        float _t8 = -_vw * _wy;
        float _t9 = -_vz * _wx;
        float _t10 = -_vw * _wx;
        float _t11 = -(_vy * _wx);
        return cross_api_fma_sfe69b601_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, java.lang.Math.fma(_vy, _wz, _t6) - java.lang.Math.fma(_vz, _wy, _t6), java.lang.Math.fma(_vz, _ww, _t7) - java.lang.Math.fma(_vw, _wz, _t7), java.lang.Math.fma(_vy, _ww, _t8) - java.lang.Math.fma(_vw, _wy, _t8), java.lang.Math.fma(_vx, _wz, _t9) - java.lang.Math.fma(_vz, _wx, _t9), java.lang.Math.fma(_vx, _ww, _t10) - java.lang.Math.fma(_vw, _wx, _t10), java.lang.Math.fma(_vx, _wy, _t11) - java.lang.Math.fma(_vy, _wx, _t11));
    }

    /** Piece 2 of {@code cross_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer cross_api_fma_sfe69b601_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        dest.putFloat(destOffset, java.lang.Math.fma(_selfw, _t24, java.lang.Math.fma(_selfy, _t25, -_selfz * _t26)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(-_selfw, _t27, java.lang.Math.fma(_selfz, _t28, -_selfx * _t25)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfw, _t29, java.lang.Math.fma(_selfx, _t26, -(_selfy * _t28))));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(-_selfz, _t29, java.lang.Math.fma(_selfy, _t27, -_selfx * _t24)));
        return dest;
    }

    /** {@code cross_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer cross_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset, java.nio.ByteBuffer w, int wOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        float _vw = v.getFloat(vOffset + 12);
        float _wx = w.getFloat(wOffset);
        float _wy = w.getFloat(wOffset + 4);
        float _wz = w.getFloat(wOffset + 8);
        float _ww = w.getFloat(wOffset + 12);
        float _t6 = -_vz * _wy;
        float _t7 = -_vw * _wz;
        float _t8 = -_vw * _wy;
        float _t9 = -_vz * _wx;
        float _t10 = -_vw * _wx;
        float _t11 = -(_vy * _wx);
        return cross_api_mulAdd_s7536b094_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, ((_vy) * (_wz) + (_t6)) - ((_vz) * (_wy) + (_t6)), ((_vz) * (_ww) + (_t7)) - ((_vw) * (_wz) + (_t7)), ((_vy) * (_ww) + (_t8)) - ((_vw) * (_wy) + (_t8)), ((_vx) * (_wz) + (_t9)) - ((_vz) * (_wx) + (_t9)), ((_vx) * (_ww) + (_t10)) - ((_vw) * (_wx) + (_t10)), ((_vx) * (_wy) + (_t11)) - ((_vy) * (_wx) + (_t11)));
    }

    /** Piece 2 of {@code cross_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer cross_api_mulAdd_s7536b094_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29) {
        dest.putFloat(destOffset, ((_selfw) * (_t24) + (((_selfy) * (_t25) - (_selfz * _t26)))));
        dest.putFloat(destOffset + 4, ((((_selfz) * (_t28) - (_selfx * _t25))) - (_selfw) * (_t27)));
        dest.putFloat(destOffset + 8, ((_selfw) * (_t29) + (((_selfx) * (_t26) - (_selfy * _t28)))));
        dest.putFloat(destOffset + 12, ((((_selfy) * (_t27) - (_selfx * _t24))) - (_selfz) * (_t29)));
        return dest;
    }

    public static java.nio.ByteBuffer degrees_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer degrees_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.toDegrees(_selfx));
        dest.putFloat(destOffset + 4, Math.toDegrees(_selfy));
        dest.putFloat(destOffset + 8, Math.toDegrees(_selfz));
        dest.putFloat(destOffset + 12, Math.toDegrees(_selfw));
        return dest;
    }

    public static float distance_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float distance_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.getFloat(srcOffset + 12) - otherW;
            float _t1 = src.getFloat(srcOffset + 8) - otherZ;
            float _t2 = src.getFloat(srcOffset) - otherX;
            float _t3 = src.getFloat(srcOffset + 4) - otherY;
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
        } else {
            float _t0 = src.getFloat(srcOffset + 12) - otherW;
            float _t1 = src.getFloat(srcOffset + 8) - otherZ;
            float _t2 = src.getFloat(srcOffset) - otherX;
            float _t3 = src.getFloat(srcOffset + 4) - otherY;
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
        }
    }

    public static float distance_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float4OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static float distance_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.getFloat(srcOffset + 12) - other.getFloat(otherOffset + 12);
            float _t1 = src.getFloat(srcOffset + 8) - other.getFloat(otherOffset + 8);
            float _t2 = src.getFloat(srcOffset) - other.getFloat(otherOffset);
            float _t3 = src.getFloat(srcOffset + 4) - other.getFloat(otherOffset + 4);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3))));
        } else {
            float _t0 = src.getFloat(srcOffset + 12) - other.getFloat(otherOffset + 12);
            float _t1 = src.getFloat(srcOffset + 8) - other.getFloat(otherOffset + 8);
            float _t2 = src.getFloat(srcOffset) - other.getFloat(otherOffset);
            float _t3 = src.getFloat(srcOffset + 4) - other.getFloat(otherOffset + 4);
            return (float) java.lang.Math.sqrt(((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3)))))));
        }
    }

    public static float distanceSquared_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float distanceSquared_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.getFloat(srcOffset + 12) - otherW;
            float _t1 = src.getFloat(srcOffset + 8) - otherZ;
            float _t2 = src.getFloat(srcOffset) - otherX;
            float _t3 = src.getFloat(srcOffset + 4) - otherY;
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            float _t0 = src.getFloat(srcOffset + 12) - otherW;
            float _t1 = src.getFloat(srcOffset + 8) - otherZ;
            float _t2 = src.getFloat(srcOffset) - otherX;
            float _t3 = src.getFloat(srcOffset + 4) - otherY;
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
        }
    }

    public static float distanceSquared_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float4OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static float distanceSquared_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t0 = src.getFloat(srcOffset + 12) - other.getFloat(otherOffset + 12);
            float _t1 = src.getFloat(srcOffset + 8) - other.getFloat(otherOffset + 8);
            float _t2 = src.getFloat(srcOffset) - other.getFloat(otherOffset);
            float _t3 = src.getFloat(srcOffset + 4) - other.getFloat(otherOffset + 4);
            return java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3)));
        } else {
            float _t0 = src.getFloat(srcOffset + 12) - other.getFloat(otherOffset + 12);
            float _t1 = src.getFloat(srcOffset + 8) - other.getFloat(otherOffset + 8);
            float _t2 = src.getFloat(srcOffset) - other.getFloat(otherOffset);
            float _t3 = src.getFloat(srcOffset + 4) - other.getFloat(otherOffset + 4);
            return ((_t0) * (_t0) + (((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))))));
        }
    }

    public static float dot_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float dot_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(otherW, src.getFloat(srcOffset + 12), java.lang.Math.fma(otherZ, src.getFloat(srcOffset + 8), java.lang.Math.fma(otherX, src.getFloat(srcOffset), otherY * src.getFloat(srcOffset + 4))));
        } else {
            return ((otherW) * (src.getFloat(srcOffset + 12)) + (((otherZ) * (src.getFloat(srcOffset + 8)) + (((otherX) * (src.getFloat(srcOffset)) + (otherY * src.getFloat(srcOffset + 4)))))));
        }
    }

    public static float dot_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float4OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            return java.lang.Math.fma(other.getFloat(otherOffset + 12), src.getFloat(srcOffset + 12), java.lang.Math.fma(other.getFloat(otherOffset + 8), src.getFloat(srcOffset + 8), java.lang.Math.fma(other.getFloat(otherOffset), src.getFloat(srcOffset), other.getFloat(otherOffset + 4) * src.getFloat(srcOffset + 4))));
        } else {
            return ((other.getFloat(otherOffset + 12)) * (src.getFloat(srcOffset + 12)) + (((other.getFloat(otherOffset + 8)) * (src.getFloat(srcOffset + 8)) + (((other.getFloat(otherOffset)) * (src.getFloat(srcOffset)) + (other.getFloat(otherOffset + 4) * src.getFloat(srcOffset + 4)))))));
        }
    }

    public static java.nio.ByteBuffer exp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.exp(_selfx));
        dest.putFloat(destOffset + 4, Math.exp(_selfy));
        dest.putFloat(destOffset + 8, Math.exp(_selfz));
        dest.putFloat(destOffset + 12, Math.exp(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer exp2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.pow(2.0f, _selfx));
        dest.putFloat(destOffset + 4, Math.pow(2.0f, _selfy));
        dest.putFloat(destOffset + 8, Math.pow(2.0f, _selfz));
        dest.putFloat(destOffset + 12, Math.pow(2.0f, _selfw));
        return dest;
    }

    public static java.nio.ByteBuffer expm1_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer expm1_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.expm1(_selfx));
        dest.putFloat(destOffset + 4, Math.expm1(_selfy));
        dest.putFloat(destOffset + 8, Math.expm1(_selfz));
        dest.putFloat(destOffset + 12, Math.expm1(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t4 = java.lang.Math.fma(IW, NrefW, java.lang.Math.fma(IZ, NrefZ, java.lang.Math.fma(IX, NrefX, IY * NrefY))) < 0.0f ? 1.0f : -1.0f;
            dest.putFloat(destOffset, _selfx * _t4);
            dest.putFloat(destOffset + 4, _selfy * _t4);
            dest.putFloat(destOffset + 8, _selfz * _t4);
            dest.putFloat(destOffset + 12, _selfw * _t4);
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t4 = ((IW) * (NrefW) + (((IZ) * (NrefZ) + (((IX) * (NrefX) + (IY * NrefY)))))) < 0.0f ? 1.0f : -1.0f;
            dest.putFloat(destOffset, _selfx * _t4);
            dest.putFloat(destOffset + 4, _selfy * _t4);
            dest.putFloat(destOffset + 8, _selfz * _t4);
            dest.putFloat(destOffset + 12, _selfw * _t4);
            return dest;
        }
    }

    public static java.nio.ByteBuffer faceforward_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + IOffset;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + NrefOffset;
        Float4OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return faceforward_api_mulAdd(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t4 = java.lang.Math.fma(I.getFloat(IOffset + 12), Nref.getFloat(NrefOffset + 12), java.lang.Math.fma(I.getFloat(IOffset + 8), Nref.getFloat(NrefOffset + 8), java.lang.Math.fma(I.getFloat(IOffset), Nref.getFloat(NrefOffset), I.getFloat(IOffset + 4) * Nref.getFloat(NrefOffset + 4)))) < 0.0f ? 1.0f : -1.0f;
        dest.putFloat(destOffset, _selfx * _t4);
        dest.putFloat(destOffset + 4, _selfy * _t4);
        dest.putFloat(destOffset + 8, _selfz * _t4);
        dest.putFloat(destOffset + 12, _selfw * _t4);
        return dest;
    }

    /** {@code faceforward_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer faceforward_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t4 = ((I.getFloat(IOffset + 12)) * (Nref.getFloat(NrefOffset + 12)) + (((I.getFloat(IOffset + 8)) * (Nref.getFloat(NrefOffset + 8)) + (((I.getFloat(IOffset)) * (Nref.getFloat(NrefOffset)) + (I.getFloat(IOffset + 4) * Nref.getFloat(NrefOffset + 4))))))) < 0.0f ? 1.0f : -1.0f;
        dest.putFloat(destOffset, _selfx * _t4);
        dest.putFloat(destOffset + 4, _selfy * _t4);
        dest.putFloat(destOffset + 8, _selfz * _t4);
        dest.putFloat(destOffset + 12, _selfw * _t4);
        return dest;
    }

    public static java.nio.ByteBuffer floor_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer floor_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.floor(_selfx));
        dest.putFloat(destOffset + 4, Math.floor(_selfy));
        dest.putFloat(destOffset + 8, Math.floor(_selfz));
        dest.putFloat(destOffset + 12, Math.floor(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer fract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer fract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.min(_selfx - Math.floor(_selfx), 0.99999994f));
        dest.putFloat(destOffset + 4, Math.min(_selfy - Math.floor(_selfy), 0.99999994f));
        dest.putFloat(destOffset + 8, Math.min(_selfz - Math.floor(_selfz), 0.99999994f));
        dest.putFloat(destOffset + 12, Math.min(_selfw - Math.floor(_selfw), 0.99999994f));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.hypot(_selfx, y));
        dest.putFloat(destOffset + 4, Math.hypot(_selfy, y));
        dest.putFloat(destOffset + 8, Math.hypot(_selfz, y));
        dest.putFloat(destOffset + 12, Math.hypot(_selfw, y));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.hypot(_selfx, yX));
        dest.putFloat(destOffset + 4, Math.hypot(_selfy, yY));
        dest.putFloat(destOffset + 8, Math.hypot(_selfz, yZ));
        dest.putFloat(destOffset + 12, Math.hypot(_selfw, yW));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset;
        Float4OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _yx = y.getFloat(yOffset);
        float _yy = y.getFloat(yOffset + 4);
        float _yz = y.getFloat(yOffset + 8);
        float _yw = y.getFloat(yOffset + 12);
        dest.putFloat(destOffset, Math.hypot(_selfx, _yx));
        dest.putFloat(destOffset + 4, Math.hypot(_selfy, _yy));
        dest.putFloat(destOffset + 8, Math.hypot(_selfz, _yz));
        dest.putFloat(destOffset + 12, Math.hypot(_selfw, _yw));
        return dest;
    }

    public static java.nio.ByteBuffer inverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer inverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, 1.0f / _selfx);
        dest.putFloat(destOffset + 4, 1.0f / _selfy);
        dest.putFloat(destOffset + 8, 1.0f / _selfz);
        dest.putFloat(destOffset + 12, 1.0f / _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer inverseSqrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer inverseSqrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, (1.0f / (float) java.lang.Math.sqrt(_selfx)));
        dest.putFloat(destOffset + 4, (1.0f / (float) java.lang.Math.sqrt(_selfy)));
        dest.putFloat(destOffset + 8, (1.0f / (float) java.lang.Math.sqrt(_selfz)));
        dest.putFloat(destOffset + 12, (1.0f / (float) java.lang.Math.sqrt(_selfw)));
        return dest;
    }

    public static float length_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy))));
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            return (float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy)))))));
        }
    }

    public static float lengthSquared_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.nio.ByteBuffer src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            return java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            return ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        }
    }

    public static java.nio.ByteBuffer log_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.log(_selfx));
        dest.putFloat(destOffset + 4, Math.log(_selfy));
        dest.putFloat(destOffset + 8, Math.log(_selfz));
        dest.putFloat(destOffset + 12, Math.log(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer log10_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log10_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.log10(_selfx));
        dest.putFloat(destOffset + 4, Math.log10(_selfy));
        dest.putFloat(destOffset + 8, Math.log10(_selfz));
        dest.putFloat(destOffset + 12, Math.log10(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer log1p_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log1p_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.log1p(_selfx));
        dest.putFloat(destOffset + 4, Math.log1p(_selfy));
        dest.putFloat(destOffset + 8, Math.log1p(_selfz));
        dest.putFloat(destOffset + 12, Math.log1p(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer log2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.log2(_selfx));
        dest.putFloat(destOffset + 4, Math.log2(_selfy));
        dest.putFloat(destOffset + 8, Math.log2(_selfz));
        dest.putFloat(destOffset + 12, Math.log2(_selfw));
        return dest;
    }

    public static float manhattanDistance_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ, otherW);
    }

    public static float manhattanDistance_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        return java.lang.Math.abs(src.getFloat(srcOffset) - otherX) + java.lang.Math.abs(src.getFloat(srcOffset + 4) - otherY) + java.lang.Math.abs(src.getFloat(srcOffset + 8) - otherZ) + java.lang.Math.abs(src.getFloat(srcOffset + 12) - otherW);
    }

    public static float manhattanDistance_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float4OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static float manhattanDistance_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        return java.lang.Math.abs(src.getFloat(srcOffset) - other.getFloat(otherOffset)) + java.lang.Math.abs(src.getFloat(srcOffset + 4) - other.getFloat(otherOffset + 4)) + java.lang.Math.abs(src.getFloat(srcOffset + 8) - other.getFloat(otherOffset + 8)) + java.lang.Math.abs(src.getFloat(srcOffset + 12) - other.getFloat(otherOffset + 12));
    }

    public static float manhattanLength_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float4OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static float manhattanLength_api(java.nio.ByteBuffer src, int srcOffset) {
        return java.lang.Math.abs(src.getFloat(srcOffset)) + java.lang.Math.abs(src.getFloat(srcOffset + 4)) + java.lang.Math.abs(src.getFloat(srcOffset + 8)) + java.lang.Math.abs(src.getFloat(srcOffset + 12));
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.max(_selfx, scalar));
        dest.putFloat(destOffset + 4, Math.max(_selfy, scalar));
        dest.putFloat(destOffset + 8, Math.max(_selfz, scalar));
        dest.putFloat(destOffset + 12, Math.max(_selfw, scalar));
        return dest;
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.max(_selfx, otherX));
        dest.putFloat(destOffset + 4, Math.max(_selfy, otherY));
        dest.putFloat(destOffset + 8, Math.max(_selfz, otherZ));
        dest.putFloat(destOffset + 12, Math.max(_selfw, otherW));
        return dest;
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float4OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, Math.max(_selfx, _otherx));
        dest.putFloat(destOffset + 4, Math.max(_selfy, _othery));
        dest.putFloat(destOffset + 8, Math.max(_selfz, _otherz));
        dest.putFloat(destOffset + 12, Math.max(_selfw, _otherw));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.min(_selfx, scalar));
        dest.putFloat(destOffset + 4, Math.min(_selfy, scalar));
        dest.putFloat(destOffset + 8, Math.min(_selfz, scalar));
        dest.putFloat(destOffset + 12, Math.min(_selfw, scalar));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, otherW);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.min(_selfx, otherX));
        dest.putFloat(destOffset + 4, Math.min(_selfy, otherY));
        dest.putFloat(destOffset + 8, Math.min(_selfz, otherZ));
        dest.putFloat(destOffset + 12, Math.min(_selfw, otherW));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float4OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _otherw = other.getFloat(otherOffset + 12);
        dest.putFloat(destOffset, Math.min(_selfx, _otherx));
        dest.putFloat(destOffset + 4, Math.min(_selfy, _othery));
        dest.putFloat(destOffset + 8, Math.min(_selfz, _otherz));
        dest.putFloat(destOffset + 12, Math.min(_selfw, _otherw));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, flooredMod(_selfx, y));
        dest.putFloat(destOffset + 4, flooredMod(_selfy, y));
        dest.putFloat(destOffset + 8, flooredMod(_selfz, y));
        dest.putFloat(destOffset + 12, flooredMod(_selfw, y));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ, yW);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, flooredMod(_selfx, yX));
        dest.putFloat(destOffset + 4, flooredMod(_selfy, yY));
        dest.putFloat(destOffset + 8, flooredMod(_selfz, yZ));
        dest.putFloat(destOffset + 12, flooredMod(_selfw, yW));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset;
        Float4OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _yx = y.getFloat(yOffset);
        float _yy = y.getFloat(yOffset + 4);
        float _yz = y.getFloat(yOffset + 8);
        float _yw = y.getFloat(yOffset + 12);
        dest.putFloat(destOffset, flooredMod(_selfx, _yx));
        dest.putFloat(destOffset + 4, flooredMod(_selfy, _yy));
        dest.putFloat(destOffset + 8, flooredMod(_selfz, _yz));
        dest.putFloat(destOffset + 12, flooredMod(_selfw, _yw));
        return dest;
    }

    public static java.nio.ByteBuffer nextDown_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer nextDown_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.nextDown(_selfx));
        dest.putFloat(destOffset + 4, Math.nextDown(_selfy));
        dest.putFloat(destOffset + 8, Math.nextDown(_selfz));
        dest.putFloat(destOffset + 12, Math.nextDown(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer nextUp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer nextUp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.nextUp(_selfx));
        dest.putFloat(destOffset + 4, Math.nextUp(_selfy));
        dest.putFloat(destOffset + 8, Math.nextUp(_selfz));
        dest.putFloat(destOffset + 12, Math.nextUp(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer normalize_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalize_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalize_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.putFloat(destOffset, _selfx * _t4);
            dest.putFloat(destOffset + 4, _selfy * _t4);
            dest.putFloat(destOffset + 8, _selfz * _t4);
            dest.putFloat(destOffset + 12, _selfw * _t4);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    /** {@code normalize_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normalize_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.putFloat(destOffset, _selfx * _t4);
            dest.putFloat(destOffset + 4, _selfy * _t4);
            dest.putFloat(destOffset + 8, _selfz * _t4);
            dest.putFloat(destOffset + 12, _selfw * _t4);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer normalizeMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.ByteBuffer normalizeMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float length) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return normalizeMul_api_mulAdd(dest, destOffset, src, srcOffset, length);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t3 = java.lang.Math.fma(_selfw, _selfw, java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.putFloat(destOffset, _selfx * _t5);
            dest.putFloat(destOffset + 4, _selfy * _t5);
            dest.putFloat(destOffset + 8, _selfz * _t5);
            dest.putFloat(destOffset + 12, _selfw * _t5);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    /** {@code normalizeMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer normalizeMul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float length) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t3 = ((_selfw) * (_selfw) + (((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))))));
        float _t5 = length * (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest.putFloat(destOffset, _selfx * _t5);
            dest.putFloat(destOffset + 4, _selfy * _t5);
            dest.putFloat(destOffset + 8, _selfz * _t5);
            dest.putFloat(destOffset + 12, _selfw * _t5);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ, rowW);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, rowX * _selfx);
        dest.putFloat(destOffset + 4, rowX * _selfy);
        dest.putFloat(destOffset + 8, rowX * _selfz);
        dest.putFloat(destOffset + 12, rowX * _selfw);
        dest.putFloat(destOffset + 16, rowY * _selfx);
        dest.putFloat(destOffset + 20, rowY * _selfy);
        dest.putFloat(destOffset + 24, rowY * _selfz);
        dest.putFloat(destOffset + 28, rowY * _selfw);
        dest.putFloat(destOffset + 32, rowZ * _selfx);
        dest.putFloat(destOffset + 36, rowZ * _selfy);
        dest.putFloat(destOffset + 40, rowZ * _selfz);
        dest.putFloat(destOffset + 44, rowZ * _selfw);
        dest.putFloat(destOffset + 48, rowW * _selfx);
        dest.putFloat(destOffset + 52, rowW * _selfy);
        dest.putFloat(destOffset + 56, rowW * _selfz);
        dest.putFloat(destOffset + 60, rowW * _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset;
        Float4OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _rowx = row.getFloat(rowOffset);
        float _rowy = row.getFloat(rowOffset + 4);
        float _rowz = row.getFloat(rowOffset + 8);
        float _roww = row.getFloat(rowOffset + 12);
        dest.putFloat(destOffset, _rowx * _selfx);
        dest.putFloat(destOffset + 4, _rowx * _selfy);
        dest.putFloat(destOffset + 8, _rowx * _selfz);
        dest.putFloat(destOffset + 12, _rowx * _selfw);
        dest.putFloat(destOffset + 16, _rowy * _selfx);
        dest.putFloat(destOffset + 20, _rowy * _selfy);
        dest.putFloat(destOffset + 24, _rowy * _selfz);
        dest.putFloat(destOffset + 28, _rowy * _selfw);
        dest.putFloat(destOffset + 32, _rowz * _selfx);
        dest.putFloat(destOffset + 36, _rowz * _selfy);
        dest.putFloat(destOffset + 40, _rowz * _selfz);
        dest.putFloat(destOffset + 44, _rowz * _selfw);
        dest.putFloat(destOffset + 48, _roww * _selfx);
        dest.putFloat(destOffset + 52, _roww * _selfy);
        dest.putFloat(destOffset + 56, _roww * _selfz);
        dest.putFloat(destOffset + 60, _roww * _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponent) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.pow(_selfx, exponent));
        dest.putFloat(destOffset + 4, Math.pow(_selfy, exponent));
        dest.putFloat(destOffset + 8, Math.pow(_selfz, exponent));
        dest.putFloat(destOffset + 12, Math.pow(_selfw, exponent));
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.pow(_selfx, exponentX));
        dest.putFloat(destOffset + 4, Math.pow(_selfy, exponentY));
        dest.putFloat(destOffset + 8, Math.pow(_selfz, exponentZ));
        dest.putFloat(destOffset + 12, Math.pow(_selfw, exponentW));
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + exponentOffset;
        Float4OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _exponentx = exponent.getFloat(exponentOffset);
        float _exponenty = exponent.getFloat(exponentOffset + 4);
        float _exponentz = exponent.getFloat(exponentOffset + 8);
        float _exponentw = exponent.getFloat(exponentOffset + 12);
        dest.putFloat(destOffset, Math.pow(_selfx, _exponentx));
        dest.putFloat(destOffset + 4, Math.pow(_selfy, _exponenty));
        dest.putFloat(destOffset + 8, Math.pow(_selfz, _exponentz));
        dest.putFloat(destOffset + 12, Math.pow(_selfw, _exponentw));
        return dest;
    }

    public static java.nio.ByteBuffer project_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    public static java.nio.ByteBuffer project_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _t9 = java.lang.Math.fma(ontoW, src.getFloat(srcOffset + 12), java.lang.Math.fma(ontoZ, src.getFloat(srcOffset + 8), java.lang.Math.fma(ontoX, src.getFloat(srcOffset), ontoY * src.getFloat(srcOffset + 4)))) / java.lang.Math.fma(ontoW, ontoW, java.lang.Math.fma(ontoZ, ontoZ, java.lang.Math.fma(ontoX, ontoX, ontoY * ontoY)));
            dest.putFloat(destOffset, ontoX * _t9);
            dest.putFloat(destOffset + 4, ontoY * _t9);
            dest.putFloat(destOffset + 8, ontoZ * _t9);
            dest.putFloat(destOffset + 12, ontoW * _t9);
            return dest;
        } else {
            float _t9 = ((ontoW) * (src.getFloat(srcOffset + 12)) + (((ontoZ) * (src.getFloat(srcOffset + 8)) + (((ontoX) * (src.getFloat(srcOffset)) + (ontoY * src.getFloat(srcOffset + 4))))))) / ((ontoW) * (ontoW) + (((ontoZ) * (ontoZ) + (((ontoX) * (ontoX) + (ontoY * ontoY))))));
            dest.putFloat(destOffset, ontoX * _t9);
            dest.putFloat(destOffset + 4, ontoY * _t9);
            dest.putFloat(destOffset + 8, ontoZ * _t9);
            dest.putFloat(destOffset + 12, ontoW * _t9);
            return dest;
        }
    }

    public static java.nio.ByteBuffer project_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + ontoOffset;
        Float4OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.ByteBuffer project_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return project_api_mulAdd(dest, destOffset, src, srcOffset, onto, ontoOffset);
        float _ontox = onto.getFloat(ontoOffset);
        float _ontoy = onto.getFloat(ontoOffset + 4);
        float _ontoz = onto.getFloat(ontoOffset + 8);
        float _ontow = onto.getFloat(ontoOffset + 12);
        float _t9 = java.lang.Math.fma(_ontow, src.getFloat(srcOffset + 12), java.lang.Math.fma(_ontoz, src.getFloat(srcOffset + 8), java.lang.Math.fma(_ontox, src.getFloat(srcOffset), _ontoy * src.getFloat(srcOffset + 4)))) / java.lang.Math.fma(_ontow, _ontow, java.lang.Math.fma(_ontoz, _ontoz, java.lang.Math.fma(_ontox, _ontox, _ontoy * _ontoy)));
        dest.putFloat(destOffset, _ontox * _t9);
        dest.putFloat(destOffset + 4, _ontoy * _t9);
        dest.putFloat(destOffset + 8, _ontoz * _t9);
        dest.putFloat(destOffset + 12, _ontow * _t9);
        return dest;
    }

    /** {@code project_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer project_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        float _ontox = onto.getFloat(ontoOffset);
        float _ontoy = onto.getFloat(ontoOffset + 4);
        float _ontoz = onto.getFloat(ontoOffset + 8);
        float _ontow = onto.getFloat(ontoOffset + 12);
        float _t9 = ((_ontow) * (src.getFloat(srcOffset + 12)) + (((_ontoz) * (src.getFloat(srcOffset + 8)) + (((_ontox) * (src.getFloat(srcOffset)) + (_ontoy * src.getFloat(srcOffset + 4))))))) / ((_ontow) * (_ontow) + (((_ontoz) * (_ontoz) + (((_ontox) * (_ontox) + (_ontoy * _ontoy))))));
        dest.putFloat(destOffset, _ontox * _t9);
        dest.putFloat(destOffset + 4, _ontoy * _t9);
        dest.putFloat(destOffset + 8, _ontoz * _t9);
        dest.putFloat(destOffset + 12, _ontow * _t9);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t3 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
            dest.putFloat(destOffset, java.lang.Math.fma(-normalX, _t3, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(-normalY, _t3, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(-normalZ, _t3, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(-normalW, _t3, _selfw));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t3 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
            dest.putFloat(destOffset, ((_selfx) - (normalX) * (_t3)));
            dest.putFloat(destOffset + 4, ((_selfy) - (normalY) * (_t3)));
            dest.putFloat(destOffset + 8, ((_selfz) - (normalZ) * (_t3)));
            dest.putFloat(destOffset + 12, ((_selfw) - (normalW) * (_t3)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer projectOnPlane_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Float4OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return projectOnPlane_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _normalx = normal.getFloat(normalOffset);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
        float _normalw = normal.getFloat(normalOffset + 12);
        float _t3 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.putFloat(destOffset, java.lang.Math.fma(-_normalx, _t3, _selfx));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(-_normaly, _t3, _selfy));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(-_normalz, _t3, _selfz));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(-_normalw, _t3, _selfw));
        return dest;
    }

    /** {@code projectOnPlane_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer projectOnPlane_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _normalx = normal.getFloat(normalOffset);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
        float _normalw = normal.getFloat(normalOffset + 12);
        float _t3 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        dest.putFloat(destOffset, ((_selfx) - (_normalx) * (_t3)));
        dest.putFloat(destOffset + 4, ((_selfy) - (_normaly) * (_t3)));
        dest.putFloat(destOffset + 8, ((_selfz) - (_normalz) * (_t3)));
        dest.putFloat(destOffset + 12, ((_selfw) - (_normalw) * (_t3)));
        return dest;
    }

    public static java.nio.ByteBuffer radians_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer radians_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.toRadians(_selfx));
        dest.putFloat(destOffset + 4, Math.toRadians(_selfy));
        dest.putFloat(destOffset + 8, Math.toRadians(_selfz));
        dest.putFloat(destOffset + 12, Math.toRadians(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t4 = 2.0f * java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
            dest.putFloat(destOffset, java.lang.Math.fma(-normalX, _t4, _selfx));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(-normalY, _t4, _selfy));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(-normalZ, _t4, _selfz));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(-normalW, _t4, _selfw));
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t4 = 2.0f * ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
            dest.putFloat(destOffset, ((_selfx) - (normalX) * (_t4)));
            dest.putFloat(destOffset + 4, ((_selfy) - (normalY) * (_t4)));
            dest.putFloat(destOffset + 8, ((_selfz) - (normalZ) * (_t4)));
            dest.putFloat(destOffset + 12, ((_selfw) - (normalW) * (_t4)));
            return dest;
        }
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Float4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return reflect_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _normalx = normal.getFloat(normalOffset);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
        float _normalw = normal.getFloat(normalOffset + 12);
        float _t4 = 2.0f * java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        dest.putFloat(destOffset, java.lang.Math.fma(-_normalx, _t4, _selfx));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(-_normaly, _t4, _selfy));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(-_normalz, _t4, _selfz));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(-_normalw, _t4, _selfw));
        return dest;
    }

    /** {@code reflect_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer reflect_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _normalx = normal.getFloat(normalOffset);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
        float _normalw = normal.getFloat(normalOffset + 12);
        float _t4 = 2.0f * ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        dest.putFloat(destOffset, ((_selfx) - (_normalx) * (_t4)));
        dest.putFloat(destOffset + 4, ((_selfy) - (_normaly) * (_t4)));
        dest.putFloat(destOffset + 8, ((_selfz) - (_normalz) * (_t4)));
        dest.putFloat(destOffset + 12, ((_selfw) - (_normalw) * (_t4)));
        return dest;
    }

    public static java.nio.ByteBuffer refract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    public static java.nio.ByteBuffer refract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_api_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t4 = java.lang.Math.fma(normalW, _selfw, java.lang.Math.fma(normalZ, _selfz, java.lang.Math.fma(normalX, _selfx, normalY * _selfy)));
        float _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0f), eta * eta, 1.0f);
        float _t11 = java.lang.Math.fma(eta, _t4, (float) java.lang.Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dest.putFloat(destOffset, java.lang.Math.fma(eta, _selfx, -(normalX * _t11)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(eta, _selfy, -(normalY * _t11)));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(eta, _selfz, -(normalZ * _t11)));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(eta, _selfw, -(normalW * _t11)));
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    /** {@code refract_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer refract_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t4 = ((normalW) * (_selfw) + (((normalZ) * (_selfz) + (((normalX) * (_selfx) + (normalY * _selfy))))));
        float _t8 = ((((_t4) * (_t4) - (1.0f))) * (eta * eta) + (1.0f));
        float _t11 = ((eta) * (_t4) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t8))));
        if (_t8 >= 0.0f) {
            dest.putFloat(destOffset, ((eta) * (_selfx) - (normalX * _t11)));
            dest.putFloat(destOffset + 4, ((eta) * (_selfy) - (normalY * _t11)));
            dest.putFloat(destOffset + 8, ((eta) * (_selfz) - (normalZ * _t11)));
            dest.putFloat(destOffset + 12, ((eta) * (_selfw) - (normalW * _t11)));
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer refract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Float4OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.ByteBuffer refract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, float eta) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return refract_api_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _normalx = normal.getFloat(normalOffset);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
        float _normalw = normal.getFloat(normalOffset + 12);
        float _t4 = java.lang.Math.fma(_normalw, _selfw, java.lang.Math.fma(_normalz, _selfz, java.lang.Math.fma(_normalx, _selfx, _normaly * _selfy)));
        float _t8 = java.lang.Math.fma(java.lang.Math.fma(_t4, _t4, -1.0f), eta * eta, 1.0f);
        float _t11 = java.lang.Math.fma(eta, _t4, (float) java.lang.Math.sqrt(Math.max(0.0f, _t8)));
        if (_t8 >= 0.0f) {
            dest.putFloat(destOffset, java.lang.Math.fma(eta, _selfx, -(_normalx * _t11)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(eta, _selfy, -(_normaly * _t11)));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(eta, _selfz, -(_normalz * _t11)));
            dest.putFloat(destOffset + 12, java.lang.Math.fma(eta, _selfw, -(_normalw * _t11)));
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    /** {@code refract_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer refract_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, float eta) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _normalx = normal.getFloat(normalOffset);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
        float _normalw = normal.getFloat(normalOffset + 12);
        float _t4 = ((_normalw) * (_selfw) + (((_normalz) * (_selfz) + (((_normalx) * (_selfx) + (_normaly * _selfy))))));
        float _t8 = ((((_t4) * (_t4) - (1.0f))) * (eta * eta) + (1.0f));
        float _t11 = ((eta) * (_t4) + ((float) java.lang.Math.sqrt(Math.max(0.0f, _t8))));
        if (_t8 >= 0.0f) {
            dest.putFloat(destOffset, ((eta) * (_selfx) - (_normalx * _t11)));
            dest.putFloat(destOffset + 4, ((eta) * (_selfy) - (_normaly * _t11)));
            dest.putFloat(destOffset + 8, ((eta) * (_selfz) - (_normalz * _t11)));
            dest.putFloat(destOffset + 12, ((eta) * (_selfw) - (_normalw * _t11)));
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
            dest.putFloat(destOffset + 12, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer round_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer round_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.rint(_selfx));
        dest.putFloat(destOffset + 4, Math.rint(_selfy));
        dest.putFloat(destOffset + 8, Math.rint(_selfz));
        dest.putFloat(destOffset + 12, Math.rint(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer sign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.signum(_selfx));
        dest.putFloat(destOffset + 4, Math.signum(_selfy));
        dest.putFloat(destOffset + 8, Math.signum(_selfz));
        dest.putFloat(destOffset + 12, Math.signum(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer sin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.sin(_selfx));
        dest.putFloat(destOffset + 4, Math.sin(_selfy));
        dest.putFloat(destOffset + 8, Math.sin(_selfz));
        dest.putFloat(destOffset + 12, Math.sin(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer sinh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sinh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.sinh(_selfx));
        dest.putFloat(destOffset + 4, Math.sinh(_selfy));
        dest.putFloat(destOffset + 8, Math.sinh(_selfz));
        dest.putFloat(destOffset + 12, Math.sinh(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0, float edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0, float edge1) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0, edge1);
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset) - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 4) - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 8) - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 12) - edge0) * _t0_inv));
        dest.putFloat(destOffset, java.lang.Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.putFloat(destOffset + 4, java.lang.Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        dest.putFloat(destOffset + 8, java.lang.Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer smoothstep_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset) - edge0) * _t0_inv));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 4) - edge0) * _t0_inv));
        float _t15 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 8) - edge0) * _t0_inv));
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 12) - edge0) * _t0_inv));
        dest.putFloat(destOffset, ((3.0f) - (2.0f) * (_t13)) * _t13 * _t13);
        dest.putFloat(destOffset + 4, ((3.0f) - (2.0f) * (_t14)) * _t14 * _t14);
        dest.putFloat(destOffset + 8, ((3.0f) - (2.0f) * (_t15)) * _t15 * _t15);
        dest.putFloat(destOffset + 12, ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 4) - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 8) - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 12) - edge0W) / (edge1W - edge0W)));
        dest.putFloat(destOffset, java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        dest.putFloat(destOffset + 4, java.lang.Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        dest.putFloat(destOffset + 8, java.lang.Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer smoothstep_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 4) - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 8) - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 12) - edge0W) / (edge1W - edge0W)));
        dest.putFloat(destOffset, ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16);
        dest.putFloat(destOffset + 4, ((3.0f) - (2.0f) * (_t17)) * _t17 * _t17);
        dest.putFloat(destOffset + 8, ((3.0f) - (2.0f) * (_t18)) * _t18 * _t18);
        dest.putFloat(destOffset + 12, ((3.0f) - (2.0f) * (_t19)) * _t19 * _t19);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + edge0Offset;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + edge1Offset;
        Float4OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return smoothstep_api_fma(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return smoothstep_api_mulAdd(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@code smoothstep_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer smoothstep_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        float _edge0x = edge0.getFloat(edge0Offset);
        float _edge0y = edge0.getFloat(edge0Offset + 4);
        float _edge0z = edge0.getFloat(edge0Offset + 8);
        float _edge0w = edge0.getFloat(edge0Offset + 12);
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset) - _edge0x) / (edge1.getFloat(edge1Offset) - _edge0x)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 4) - _edge0y) / (edge1.getFloat(edge1Offset + 4) - _edge0y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 8) - _edge0z) / (edge1.getFloat(edge1Offset + 8) - _edge0z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 12) - _edge0w) / (edge1.getFloat(edge1Offset + 12) - _edge0w)));
        dest.putFloat(destOffset, java.lang.Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16);
        dest.putFloat(destOffset + 4, java.lang.Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17);
        dest.putFloat(destOffset + 8, java.lang.Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18);
        dest.putFloat(destOffset + 12, java.lang.Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19);
        return dest;
    }

    /** {@code smoothstep_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer smoothstep_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        float _edge0x = edge0.getFloat(edge0Offset);
        float _edge0y = edge0.getFloat(edge0Offset + 4);
        float _edge0z = edge0.getFloat(edge0Offset + 8);
        float _edge0w = edge0.getFloat(edge0Offset + 12);
        float _t16 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset) - _edge0x) / (edge1.getFloat(edge1Offset) - _edge0x)));
        float _t17 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 4) - _edge0y) / (edge1.getFloat(edge1Offset + 4) - _edge0y)));
        float _t18 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 8) - _edge0z) / (edge1.getFloat(edge1Offset + 8) - _edge0z)));
        float _t19 = Math.max(0.0f, Math.min(1.0f, (src.getFloat(srcOffset + 12) - _edge0w) / (edge1.getFloat(edge1Offset + 12) - _edge0w)));
        dest.putFloat(destOffset, ((3.0f) - (2.0f) * (_t16)) * _t16 * _t16);
        dest.putFloat(destOffset + 4, ((3.0f) - (2.0f) * (_t17)) * _t17 * _t17);
        dest.putFloat(destOffset + 8, ((3.0f) - (2.0f) * (_t18)) * _t18 * _t18);
        dest.putFloat(destOffset + 12, ((3.0f) - (2.0f) * (_t19)) * _t19 * _t19);
        return dest;
    }

    public static java.nio.ByteBuffer sqrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sqrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, (float) java.lang.Math.sqrt(_selfx));
        dest.putFloat(destOffset + 4, (float) java.lang.Math.sqrt(_selfy));
        dest.putFloat(destOffset + 8, (float) java.lang.Math.sqrt(_selfz));
        dest.putFloat(destOffset + 12, (float) java.lang.Math.sqrt(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _selfx < edge ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 4, _selfy < edge ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 8, _selfz < edge ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 12, _selfw < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _selfx < edgeX ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 4, _selfy < edgeY ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 8, _selfz < edgeZ ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 12, _selfw < edgeW ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + edgeOffset;
        Float4OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _edgex = edge.getFloat(edgeOffset);
        float _edgey = edge.getFloat(edgeOffset + 4);
        float _edgez = edge.getFloat(edgeOffset + 8);
        float _edgew = edge.getFloat(edgeOffset + 12);
        dest.putFloat(destOffset, _selfx < _edgex ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 4, _selfy < _edgey ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 8, _selfz < _edgez ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 12, _selfw < _edgew ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer tan_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer tan_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.tan(_selfx));
        dest.putFloat(destOffset + 4, Math.tan(_selfy));
        dest.putFloat(destOffset + 8, Math.tan(_selfz));
        dest.putFloat(destOffset + 12, Math.tan(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer tanh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer tanh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.tanh(_selfx));
        dest.putFloat(destOffset + 4, Math.tanh(_selfy));
        dest.putFloat(destOffset + 8, Math.tanh(_selfz));
        dest.putFloat(destOffset + 12, Math.tanh(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer trunc_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer trunc_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.putFloat(destOffset + 4, _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.putFloat(destOffset + 8, _selfz >= 0.0f ? Math.floor(_selfz) : Math.ceil(_selfz));
        dest.putFloat(destOffset + 12, _selfw >= 0.0f ? Math.floor(_selfw) : Math.ceil(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer ulp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer ulp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        dest.putFloat(destOffset, Math.ulp(_selfx));
        dest.putFloat(destOffset + 4, Math.ulp(_selfy));
        dest.putFloat(destOffset + 8, Math.ulp(_selfz));
        dest.putFloat(destOffset + 12, Math.ulp(_selfw));
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float4OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return preMul_api_fma(dest, destOffset, src, srcOffset, mat, matOffset);
        return preMul_api_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@code preMul_api} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMul_api_fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _mat00 = mat.getFloat(matOffset);
        float _mat10 = mat.getFloat(matOffset + 4);
        float _mat20 = mat.getFloat(matOffset + 8);
        float _mat30 = mat.getFloat(matOffset + 12);
        float _mat01 = mat.getFloat(matOffset + 16);
        float _mat11 = mat.getFloat(matOffset + 20);
        float _mat21 = mat.getFloat(matOffset + 24);
        float _mat31 = mat.getFloat(matOffset + 28);
        float _mat02 = mat.getFloat(matOffset + 32);
        float _mat12 = mat.getFloat(matOffset + 36);
        float _mat22 = mat.getFloat(matOffset + 40);
        float _mat32 = mat.getFloat(matOffset + 44);
        float _mat03 = mat.getFloat(matOffset + 48);
        float _mat13 = mat.getFloat(matOffset + 52);
        float _mat23 = mat.getFloat(matOffset + 56);
        float _mat33 = mat.getFloat(matOffset + 60);
        dest.putFloat(destOffset, java.lang.Math.fma(_mat03, _selfw, java.lang.Math.fma(_mat02, _selfz, java.lang.Math.fma(_mat00, _selfx, _mat01 * _selfy))));
        return preMul_api_fma_s42575f8a_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _mat10, _mat20, _mat30, _mat11, _mat21, _mat31, _mat12, _mat22, _mat32, _mat13, _mat23, _mat33);
    }

    /** Piece 2 of {@code preMul_api_fma}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMul_api_fma_s42575f8a_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _mat10, float _mat20, float _mat30, float _mat11, float _mat21, float _mat31, float _mat12, float _mat22, float _mat32, float _mat13, float _mat23, float _mat33) {
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_mat13, _selfw, java.lang.Math.fma(_mat12, _selfz, java.lang.Math.fma(_mat10, _selfx, _mat11 * _selfy))));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_mat23, _selfw, java.lang.Math.fma(_mat22, _selfz, java.lang.Math.fma(_mat20, _selfx, _mat21 * _selfy))));
        dest.putFloat(destOffset + 12, java.lang.Math.fma(_mat33, _selfw, java.lang.Math.fma(_mat32, _selfz, java.lang.Math.fma(_mat30, _selfx, _mat31 * _selfy))));
        return dest;
    }

    /** {@code preMul_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer preMul_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _mat00 = mat.getFloat(matOffset);
        float _mat10 = mat.getFloat(matOffset + 4);
        float _mat20 = mat.getFloat(matOffset + 8);
        float _mat30 = mat.getFloat(matOffset + 12);
        float _mat01 = mat.getFloat(matOffset + 16);
        float _mat11 = mat.getFloat(matOffset + 20);
        float _mat21 = mat.getFloat(matOffset + 24);
        float _mat31 = mat.getFloat(matOffset + 28);
        float _mat02 = mat.getFloat(matOffset + 32);
        float _mat12 = mat.getFloat(matOffset + 36);
        float _mat22 = mat.getFloat(matOffset + 40);
        float _mat32 = mat.getFloat(matOffset + 44);
        float _mat03 = mat.getFloat(matOffset + 48);
        float _mat13 = mat.getFloat(matOffset + 52);
        float _mat23 = mat.getFloat(matOffset + 56);
        float _mat33 = mat.getFloat(matOffset + 60);
        dest.putFloat(destOffset, ((_mat03) * (_selfw) + (((_mat02) * (_selfz) + (((_mat00) * (_selfx) + (_mat01 * _selfy)))))));
        return preMul_api_mulAdd_s2f4f743d_1(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _mat10, _mat20, _mat30, _mat11, _mat21, _mat31, _mat12, _mat22, _mat32, _mat13, _mat23, _mat33);
    }

    /** Piece 2 of {@code preMul_api_mulAdd}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMul_api_mulAdd_s2f4f743d_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _mat10, float _mat20, float _mat30, float _mat11, float _mat21, float _mat31, float _mat12, float _mat22, float _mat32, float _mat13, float _mat23, float _mat33) {
        dest.putFloat(destOffset + 4, ((_mat13) * (_selfw) + (((_mat12) * (_selfz) + (((_mat10) * (_selfx) + (_mat11 * _selfy)))))));
        dest.putFloat(destOffset + 8, ((_mat23) * (_selfw) + (((_mat22) * (_selfz) + (((_mat20) * (_selfx) + (_mat21 * _selfy)))))));
        dest.putFloat(destOffset + 12, ((_mat33) * (_selfw) + (((_mat32) * (_selfz) + (((_mat30) * (_selfx) + (_mat31 * _selfy)))))));
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * java.lang.Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.putFloat(destOffset, java.lang.Math.fma(quatY, _t9, java.lang.Math.fma(-quatZ, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(quatZ, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfy))));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatY, _t11, java.lang.Math.fma(quatW, _t9, _selfz))));
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((quatX) * (_selfy) - (quatY * _selfx));
        float _t10 = 2.0f * ((quatZ) * (_selfx) - (quatX * _selfz));
        float _t11 = 2.0f * ((quatY) * (_selfz) - (quatZ * _selfy));
        dest.putFloat(destOffset, ((quatY) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatZ) * (_t10)))));
        dest.putFloat(destOffset + 4, ((quatZ) * (_t11) + (((((quatW) * (_t10) + (_selfy))) - (quatX) * (_t9)))));
        dest.putFloat(destOffset + 8, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfz))) - (quatY) * (_t11)))));
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset;
        Float4OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotate_api_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _quatx = quat.getFloat(quatOffset);
        float _quaty = quat.getFloat(quatOffset + 4);
        float _quatz = quat.getFloat(quatOffset + 8);
        float _quatw = quat.getFloat(quatOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        float _t11 = 2.0f * java.lang.Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.putFloat(destOffset, java.lang.Math.fma(_quaty, _t9, java.lang.Math.fma(-_quatz, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_quatz, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfy))));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quaty, _t11, java.lang.Math.fma(_quatw, _t9, _selfz))));
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    /** {@code rotate_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotate_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _quatx = quat.getFloat(quatOffset);
        float _quaty = quat.getFloat(quatOffset + 4);
        float _quatz = quat.getFloat(quatOffset + 8);
        float _quatw = quat.getFloat(quatOffset + 12);
        float _t9 = 2.0f * ((_quatx) * (_selfy) - (_quaty * _selfx));
        float _t10 = 2.0f * ((_quatz) * (_selfx) - (_quatx * _selfz));
        float _t11 = 2.0f * ((_quaty) * (_selfz) - (_quatz * _selfy));
        dest.putFloat(destOffset, ((_quaty) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quatz) * (_t10)))));
        dest.putFloat(destOffset + 4, ((_quatz) * (_t11) + (((((_quatw) * (_t10) + (_selfy))) - (_quatx) * (_t9)))));
        dest.putFloat(destOffset + 8, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfz))) - (_quaty) * (_t11)))));
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(axisZ, _selfz, java.lang.Math.fma(axisX, _selfx, axisY * _selfy));
        dest.putFloat(destOffset, java.lang.Math.fma(_t2, axisX * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t1)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t2, axisY * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t1)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t2, axisZ * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t1)));
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((axisZ) * (_selfz) + (((axisX) * (_selfx) + (axisY * _selfy))));
        dest.putFloat(destOffset, ((_t2) * (axisX * _t5) + (((_selfx) * (_t0) + (((axisY) * (_selfz) - (axisZ * _selfy)) * _t1)))));
        dest.putFloat(destOffset + 4, ((_t2) * (axisY * _t5) + (((_selfy) * (_t0) + (((axisZ) * (_selfx) - (axisX * _selfz)) * _t1)))));
        dest.putFloat(destOffset + 8, ((_t2) * (axisZ * _t5) + (((_selfz) * (_t0) + (((axisX) * (_selfy) - (axisY * _selfx)) * _t1)))));
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Float4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateAxis_api_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = java.lang.Math.fma(_axisz, _selfz, java.lang.Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.putFloat(destOffset, java.lang.Math.fma(_t2, _axisx * _t5, java.lang.Math.fma(_selfx, _t0, java.lang.Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t1)));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_t2, _axisy * _t5, java.lang.Math.fma(_selfy, _t0, java.lang.Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t1)));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_t2, _axisz * _t5, java.lang.Math.fma(_selfz, _t0, java.lang.Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t1)));
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    /** {@code rotateAxis_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateAxis_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t0 = Math.cosWithSin(angle);
        float _t1 = Math.sinWithCos(angle);
        float _t2 = 1.0f - _t0;
        float _t5 = ((_axisz) * (_selfz) + (((_axisx) * (_selfx) + (_axisy * _selfy))));
        dest.putFloat(destOffset, ((_t2) * (_axisx * _t5) + (((_selfx) * (_t0) + (((_axisy) * (_selfz) - (_axisz * _selfy)) * _t1)))));
        dest.putFloat(destOffset + 4, ((_t2) * (_axisy * _t5) + (((_selfy) * (_t0) + (((_axisz) * (_selfx) - (_axisx * _selfz)) * _t1)))));
        dest.putFloat(destOffset + 8, ((_t2) * (_axisz * _t5) + (((_selfz) * (_t0) + (((_axisx) * (_selfy) - (_axisy * _selfx)) * _t1)))));
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_api_mulAdd(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * java.lang.Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.putFloat(destOffset, java.lang.Math.fma(quatZ, _t9, java.lang.Math.fma(-quatY, _t10, java.lang.Math.fma(quatW, _t11, _selfx))));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(quatX, _t10, java.lang.Math.fma(-quatZ, _t11, java.lang.Math.fma(quatW, _t9, _selfy))));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(quatY, _t11, java.lang.Math.fma(-quatX, _t9, java.lang.Math.fma(quatW, _t10, _selfz))));
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    /** {@code rotateInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateInverse_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _t9 = 2.0f * ((quatX) * (_selfz) - (quatZ * _selfx));
        float _t10 = 2.0f * ((quatY) * (_selfx) - (quatX * _selfy));
        float _t11 = 2.0f * ((quatZ) * (_selfy) - (quatY * _selfz));
        dest.putFloat(destOffset, ((quatZ) * (_t9) + (((((quatW) * (_t11) + (_selfx))) - (quatY) * (_t10)))));
        dest.putFloat(destOffset + 4, ((quatX) * (_t10) + (((((quatW) * (_t9) + (_selfy))) - (quatZ) * (_t11)))));
        dest.putFloat(destOffset + 8, ((quatY) * (_t11) + (((((quatW) * (_t10) + (_selfz))) - (quatX) * (_t9)))));
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset;
        Float4OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return rotateInverse_api_mulAdd(dest, destOffset, src, srcOffset, quat, quatOffset);
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _quatx = quat.getFloat(quatOffset);
        float _quaty = quat.getFloat(quatOffset + 4);
        float _quatz = quat.getFloat(quatOffset + 8);
        float _quatw = quat.getFloat(quatOffset + 12);
        float _t9 = 2.0f * java.lang.Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        float _t10 = 2.0f * java.lang.Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        float _t11 = 2.0f * java.lang.Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.putFloat(destOffset, java.lang.Math.fma(_quatz, _t9, java.lang.Math.fma(-_quaty, _t10, java.lang.Math.fma(_quatw, _t11, _selfx))));
        dest.putFloat(destOffset + 4, java.lang.Math.fma(_quatx, _t10, java.lang.Math.fma(-_quatz, _t11, java.lang.Math.fma(_quatw, _t9, _selfy))));
        dest.putFloat(destOffset + 8, java.lang.Math.fma(_quaty, _t11, java.lang.Math.fma(-_quatx, _t9, java.lang.Math.fma(_quatw, _t10, _selfz))));
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    /** {@code rotateInverse_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.nio.ByteBuffer rotateInverse_api_mulAdd(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _selfw = src.getFloat(srcOffset + 12);
        float _quatx = quat.getFloat(quatOffset);
        float _quaty = quat.getFloat(quatOffset + 4);
        float _quatz = quat.getFloat(quatOffset + 8);
        float _quatw = quat.getFloat(quatOffset + 12);
        float _t9 = 2.0f * ((_quatx) * (_selfz) - (_quatz * _selfx));
        float _t10 = 2.0f * ((_quaty) * (_selfx) - (_quatx * _selfy));
        float _t11 = 2.0f * ((_quatz) * (_selfy) - (_quaty * _selfz));
        dest.putFloat(destOffset, ((_quatz) * (_t9) + (((((_quatw) * (_t11) + (_selfx))) - (_quaty) * (_t10)))));
        dest.putFloat(destOffset + 4, ((_quatx) * (_t10) + (((((_quatw) * (_t9) + (_selfy))) - (_quatz) * (_t11)))));
        dest.putFloat(destOffset + 8, ((_quaty) * (_t11) + (((((_quatw) * (_t10) + (_selfz))) - (_quatx) * (_t9)))));
        dest.putFloat(destOffset + 12, _selfw);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.putFloat(destOffset, _selfx);
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfy, _t0, -(_selfz * _t1)));
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfy, _t1, _selfz * _t0));
            dest.putFloat(destOffset + 12, _selfw);
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.putFloat(destOffset, _selfx);
            dest.putFloat(destOffset + 4, ((_selfy) * (_t0) - (_selfz * _t1)));
            dest.putFloat(destOffset + 8, ((_selfy) * (_t1) + (_selfz * _t0)));
            dest.putFloat(destOffset + 12, _selfw);
            return dest;
        }
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t0, _selfz * _t1));
            dest.putFloat(destOffset + 4, _selfy);
            dest.putFloat(destOffset + 8, java.lang.Math.fma(_selfz, _t0, -(_selfx * _t1)));
            dest.putFloat(destOffset + 12, _selfw);
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.putFloat(destOffset, ((_selfx) * (_t0) + (_selfz * _t1)));
            dest.putFloat(destOffset + 4, _selfy);
            dest.putFloat(destOffset + 8, ((_selfz) * (_t0) - (_selfx * _t1)));
            dest.putFloat(destOffset + 12, _selfw);
            return dest;
        }
    }

    public static java.nio.ByteBuffer rotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.putFloat(destOffset, java.lang.Math.fma(_selfx, _t0, -(_selfy * _t1)));
            dest.putFloat(destOffset + 4, java.lang.Math.fma(_selfx, _t1, _selfy * _t0));
            dest.putFloat(destOffset + 8, _selfz);
            dest.putFloat(destOffset + 12, _selfw);
            return dest;
        } else {
            float _selfx = src.getFloat(srcOffset);
            float _selfy = src.getFloat(srcOffset + 4);
            float _selfz = src.getFloat(srcOffset + 8);
            float _selfw = src.getFloat(srcOffset + 12);
            float _t0 = Math.cosWithSin(angle);
            float _t1 = Math.sinWithCos(angle);
            dest.putFloat(destOffset, ((_selfx) * (_t0) - (_selfy * _t1)));
            dest.putFloat(destOffset + 4, ((_selfx) * (_t1) + (_selfy * _t0)));
            dest.putFloat(destOffset + 8, _selfz);
            dest.putFloat(destOffset + 12, _selfw);
            return dest;
        }
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
