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
 * parameter is a {@link java.nio.ByteBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float3Ops} and its sibling kernel units. Not public API.
 */
public final class Float3OpsKernelsByteBuffer {
    private Float3OpsKernelsByteBuffer() {}

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, otherX + _selfx);
        dest.putFloat(destOffset + 4, otherY + _selfy);
        dest.putFloat(destOffset + 8, otherZ + _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer add_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer add_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        dest.putFloat(destOffset, _otherx + _selfx);
        dest.putFloat(destOffset + 4, _othery + _selfy);
        dest.putFloat(destOffset + 8, _otherz + _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, _selfx / scalar);
        dest.putFloat(destOffset + 4, _selfy / scalar);
        dest.putFloat(destOffset + 8, _selfz / scalar);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, _selfx / otherX);
        dest.putFloat(destOffset + 4, _selfy / otherY);
        dest.putFloat(destOffset + 8, _selfz / otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer div_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3OpsKernelsAddress.div_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer div_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        dest.putFloat(destOffset, _selfx / _otherx);
        dest.putFloat(destOffset + 4, _selfy / _othery);
        dest.putFloat(destOffset + 8, _selfz / _otherz);
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float b, float cX, float cY, float cZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, b, cX, cY, cZ);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float b, float cX, float cY, float cZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.fma(_selfx, b, cX));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, b, cY));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, b, cZ));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, float b) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _cBase, b);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, float b) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _cx = c.getFloat(cOffset);
        float _cy = c.getFloat(cOffset + 4);
        float _cz = c.getFloat(cOffset + 8);
        dest.putFloat(destOffset, Math.fma(_selfx, b, _cx));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, b, _cy));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, b, _cz));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.fma(_selfx, bX, cX));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, bY, cY));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, bZ, cZ));
        return dest;
    }

    public static java.nio.ByteBuffer fma_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Float3OpsKernelsAddress.fma_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.ByteBuffer fma_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _bx = b.getFloat(bOffset);
        float _by = b.getFloat(bOffset + 4);
        float _bz = b.getFloat(bOffset + 8);
        float _cx = c.getFloat(cOffset);
        float _cy = c.getFloat(cOffset + 4);
        float _cz = c.getFloat(cOffset + 8);
        dest.putFloat(destOffset, Math.fma(_selfx, _bx, _cx));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _by, _cy));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, _bz, _cz));
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, scalar * _selfx);
        dest.putFloat(destOffset + 4, scalar * _selfy);
        dest.putFloat(destOffset + 8, scalar * _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, otherX * _selfx);
        dest.putFloat(destOffset + 4, otherY * _selfy);
        dest.putFloat(destOffset + 8, otherZ * _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer mul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer mul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        dest.putFloat(destOffset, _otherx * _selfx);
        dest.putFloat(destOffset + 4, _othery * _selfy);
        dest.putFloat(destOffset + 8, _otherz * _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer negate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer negate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, -_selfx);
        dest.putFloat(destOffset + 4, -_selfy);
        dest.putFloat(destOffset + 8, -_selfz);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, _selfx - otherX);
        dest.putFloat(destOffset + 4, _selfy - otherY);
        dest.putFloat(destOffset + 8, _selfz - otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer sub_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer sub_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        dest.putFloat(destOffset, _selfx - _otherx);
        dest.putFloat(destOffset + 4, _selfy - _othery);
        dest.putFloat(destOffset + 8, _selfz - _otherz);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformDirection_unsafe(java.nio.ByteBuffer dest, int destOffset, float u, float v) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3OpsKernelsAddress.makeUniformDirection_unsafe(_destBase, u, v);
        return dest;
    }

    public static java.nio.ByteBuffer makeUniformDirection_api(java.nio.ByteBuffer dest, int destOffset, float u, float v) {
        float _t1 = v * 6.2831855f;
        float _t2 = Math.sin(_t1);
        float _t5 = 2.0f * (float) java.lang.Math.sqrt(u * (1.0f - u));
        dest.putFloat(destOffset, _t5 * Math.cosFromSin(_t2, _t1));
        dest.putFloat(destOffset + 4, _t5 * _t2);
        dest.putFloat(destOffset + 8, Math.fma(2.0f, u, -1.0f));
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3OpsKernelsAddress.set_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, float vX, float vY, float vZ) {
        dest.putFloat(destOffset, vX);
        dest.putFloat(destOffset + 4, vY);
        dest.putFloat(destOffset + 8, vZ);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset;
        Float3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        float _vx = v.getFloat(vOffset);
        float _vy = v.getFloat(vOffset + 4);
        float _vz = v.getFloat(vOffset + 8);
        dest.putFloat(destOffset, _vx);
        dest.putFloat(destOffset + 4, _vy);
        dest.putFloat(destOffset + 8, _vz);
        return dest;
    }

    public static java.nio.ByteBuffer set_unsafe(java.nio.ByteBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3OpsKernelsAddress.set_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.ByteBuffer set_api(java.nio.ByteBuffer dest, int destOffset, float s) {
        dest.putFloat(destOffset, s);
        dest.putFloat(destOffset + 4, s);
        dest.putFloat(destOffset + 8, s);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_unsafe(java.nio.ByteBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        Float3OpsKernelsAddress.makeZero_unsafe(_destBase);
        return dest;
    }

    public static java.nio.ByteBuffer makeZero_api(java.nio.ByteBuffer dest, int destOffset) {
        dest.putFloat(destOffset, 0.0f);
        dest.putFloat(destOffset + 4, 0.0f);
        dest.putFloat(destOffset + 8, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.putFloat(destOffset, Math.fma(p1X, _t7, _selfx * _t8) + Math.fma(p2X, _t6, p3X * _t2));
        dest.putFloat(destOffset + 4, Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2));
        dest.putFloat(destOffset + 8, Math.fma(p1Z, _t7, _selfz * _t8) + Math.fma(p2Z, _t6, p3Z * _t2));
        return dest;
    }

    public static java.nio.ByteBuffer bezier_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Float3OpsKernelsAddress.bezier_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p3x = p3.getFloat(p3Offset);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _p3z = p3.getFloat(p3Offset + 8);
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest.putFloat(destOffset, Math.fma(_p1x, _t7, _selfx * _t8) + Math.fma(_p2x, _t6, _p3x * _t2));
        dest.putFloat(destOffset + 4, Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2));
        dest.putFloat(destOffset + 8, Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.putFloat(destOffset, Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4)));
        dest.putFloat(destOffset + 4, Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4)));
        dest.putFloat(destOffset + 8, Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Float3OpsKernelsAddress.bezier2_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest.putFloat(destOffset, Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4)));
        dest.putFloat(destOffset + 4, Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4)));
        dest.putFloat(destOffset + 8, Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4)));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.putFloat(destOffset, Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1));
        dest.putFloat(destOffset + 4, Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1));
        dest.putFloat(destOffset + 8, Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Float3OpsKernelsAddress.bezier2Tangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezier2Tangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest.putFloat(destOffset, Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1));
        dest.putFloat(destOffset + 4, Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1));
        dest.putFloat(destOffset + 8, Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.putFloat(destOffset, Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5)));
        dest.putFloat(destOffset + 4, Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5)));
        dest.putFloat(destOffset + 8, Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Float3OpsKernelsAddress.bezierTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer bezierTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p3x = p3.getFloat(p3Offset);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _p3z = p3.getFloat(p3Offset + 8);
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest.putFloat(destOffset, Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5)));
        dest.putFloat(destOffset + 4, Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5)));
        dest.putFloat(destOffset + 8, Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.putFloat(destOffset, 0.5f * (Math.fma(2.0f, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)) * _t1)));
        dest.putFloat(destOffset + 4, 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1)));
        dest.putFloat(destOffset + 8, 0.5f * (Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), _t0, Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Float3OpsKernelsAddress.catmullRom_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRom_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p3x = p3.getFloat(p3Offset);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _p3z = p3.getFloat(p3Offset + 8);
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest.putFloat(destOffset, 0.5f * (Math.fma(2.0f, _p1x, t * (_p2x - _selfx)) + Math.fma(Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), _t0, Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)) * _t1)));
        return catmullRom_api_s867b38f0_1(dest, destOffset, t, _selfy, _selfz, _p1y, _p1z, _p2y, _p2z, _p3y, _p3z, _t0, _t1);
    }

    /** Piece 2 of {@code catmullRom_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer catmullRom_api_s867b38f0_1(java.nio.ByteBuffer dest, int destOffset, float t, float _selfy, float _selfz, float _p1y, float _p1z, float _p2y, float _p2z, float _p3y, float _p3z, float _t0, float _t1) {
        dest.putFloat(destOffset + 4, 0.5f * (Math.fma(2.0f, _p1y, t * (_p2y - _selfy)) + Math.fma(Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), _t0, Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)) * _t1)));
        dest.putFloat(destOffset + 8, 0.5f * (Math.fma(2.0f, _p1z, t * (_p2z - _selfz)) + Math.fma(Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), _t0, Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)) * _t1)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = t * t;
        dest.putFloat(destOffset, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx)));
        dest.putFloat(destOffset + 4, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy)));
        dest.putFloat(destOffset + 8, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), Math.fma(3.0f * Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz)));
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        long _p3Base = UnsafeOpsHolder.U.getLong(p3, UnsafeCopy.BB_ADDRESS_OFFSET) + p3Offset;
        Float3OpsKernelsAddress.catmullRomTangent_unsafe(_destBase, _srcBase, _p1Base, _p2Base, _p3Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer catmullRomTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _p3x = p3.getFloat(p3Offset);
        float _p3y = p3.getFloat(p3Offset + 4);
        float _p3z = p3.getFloat(p3Offset + 8);
        float _t0 = t * t;
        dest.putFloat(destOffset, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1x, Math.fma(2.0f, _selfx, Math.fma(4.0f, _p2x, -_p3x))), Math.fma(3.0f * Math.fma(-3.0f, _p2x, Math.fma(3.0f, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx)));
        dest.putFloat(destOffset + 4, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1y, Math.fma(2.0f, _selfy, Math.fma(4.0f, _p2y, -_p3y))), Math.fma(3.0f * Math.fma(-3.0f, _p2y, Math.fma(3.0f, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy)));
        return catmullRomTangent_api_s5217da4f_1(dest, destOffset, t, _selfz, _p1z, _p2z, _p3z, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer catmullRomTangent_api_s5217da4f_1(java.nio.ByteBuffer dest, int destOffset, float t, float _selfz, float _p1z, float _p2z, float _p3z, float _t0) {
        dest.putFloat(destOffset + 8, 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, _p1z, Math.fma(2.0f, _selfz, Math.fma(4.0f, _p2z, -_p3z))), Math.fma(3.0f * Math.fma(-3.0f, _p2z, Math.fma(3.0f, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz)));
        return dest;
    }

    public static java.nio.ByteBuffer hermite_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermite_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.putFloat(destOffset, Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9));
        return dest;
    }

    public static java.nio.ByteBuffer hermite_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        Float3OpsKernelsAddress.hermite_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermite_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0x = t0.getFloat(t0Offset);
        float _t0y = t0.getFloat(t0Offset + 4);
        float _t0z = t0.getFloat(t0Offset + 8);
        float _v1x = v1.getFloat(v1Offset);
        float _v1y = v1.getFloat(v1Offset + 4);
        float _v1z = v1.getFloat(v1Offset + 8);
        float _t1x = t1.getFloat(t1Offset);
        float _t1y = t1.getFloat(t1Offset + 4);
        float _t1z = t1.getFloat(t1Offset + 8);
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest.putFloat(destOffset, Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9));
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.putFloat(destOffset, Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _t0Base = UnsafeOpsHolder.U.getLong(t0, UnsafeCopy.BB_ADDRESS_OFFSET) + t0Offset;
        long _v1Base = UnsafeOpsHolder.U.getLong(v1, UnsafeCopy.BB_ADDRESS_OFFSET) + v1Offset;
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset;
        Float3OpsKernelsAddress.hermiteTangent_unsafe(_destBase, _srcBase, _t0Base, _v1Base, _t1Base, t);
        return dest;
    }

    public static java.nio.ByteBuffer hermiteTangent_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0x = t0.getFloat(t0Offset);
        float _t0y = t0.getFloat(t0Offset + 4);
        float _t0z = t0.getFloat(t0Offset + 8);
        float _v1x = v1.getFloat(v1Offset);
        float _v1y = v1.getFloat(v1Offset + 4);
        float _v1z = v1.getFloat(v1Offset + 8);
        float _t1x = t1.getFloat(t1Offset);
        float _t1y = t1.getFloat(t1Offset + 4);
        float _t1z = t1.getFloat(t1Offset + 8);
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest.putFloat(destOffset, Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7));
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7));
        dest.putFloat(destOffset + 8, Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.fma(t, otherX - _selfx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(t, otherY - _selfy, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(t, otherZ - _selfz, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        dest.putFloat(destOffset, Math.fma(t, _otherx - _selfx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(t, _othery - _selfy, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(t, _otherz - _selfz, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, tX, tY, tZ);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.fma(tX, otherX - _selfx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(tY, otherY - _selfy, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(tZ, otherZ - _selfz, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer lerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset;
        Float3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, _tBase);
        return dest;
    }

    public static java.nio.ByteBuffer lerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _tx = t.getFloat(tOffset);
        float _ty = t.getFloat(tOffset + 4);
        float _tz = t.getFloat(tOffset + 8);
        dest.putFloat(destOffset, Math.fma(_tx, _otherx - _selfx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(_ty, _othery - _selfy, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(_tz, _otherz - _selfz, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t7 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t8 = Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = Math.fma(otherZ * _t12, _t17, Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_api_s3ecbe0bf_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, Math.fma(otherZ, _t12, -(_t26 * _t17)), Math.fma(otherX, _t12, -(_t26 * _t14)), Math.fma(otherY, _t12, -(_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_s3ecbe0bf_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33, float _t34, float _t35) {
        float _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = Math.fma(_t39, _t17, _t33);
        float _t41 = Math.fma(_t39, _t14, _t34);
        float _t42 = Math.fma(_t39, _t20, _t35);
        float _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _sp0 = _t24 * Math.sin(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        float _t55 = _t24 * Math.cos(_t50);
        dest.putFloat(destOffset, Math.fma(_t14, _t55, _sp0 * _t41));
        dest.putFloat(destOffset + 4, Math.fma(_t20, _t55, _sp0 * _t42));
        dest.putFloat(destOffset + 8, Math.fma(_t17, _t55, _sp0 * _t40));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Float3OpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, otherX, otherY, otherZ, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t1 = unitScale(otherX, otherY, otherZ);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = otherZ * _t1;
        float _t10 = otherX * _t1;
        float _t11 = otherY * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = java.lang.Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
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
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
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
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t99 = _t48 * Math.sin(_t95);
        float _t100 = _t48 * Math.cos(_t95);
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
                    dest.putFloat(destOffset, Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv);
                    dest.putFloat(destOffset + 4, Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv);
                    dest.putFloat(destOffset + 8, Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv);
                } else {
                    dest.putFloat(destOffset, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                    dest.putFloat(destOffset + 4, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                    dest.putFloat(destOffset + 8, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
                }
            } else {
                dest.putFloat(destOffset, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                dest.putFloat(destOffset + 4, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                dest.putFloat(destOffset + 8, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
            }
        } else {
            dest.putFloat(destOffset, Math.fma(t, otherX - _selfx, _selfx));
            dest.putFloat(destOffset + 4, Math.fma(t, otherY - _selfy, _selfy));
            dest.putFloat(destOffset + 8, Math.fma(t, otherZ - _selfz, _selfz));
        }
        return dest;
    }

    public static java.nio.ByteBuffer slerp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3OpsKernelsAddress.slerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _t7 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t8 = Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = Math.fma(_otherz * _t12, _t17, Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        return slerp_api_sb06449d4_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _otherx, _othery, _t12, _t14, _t17, _t20, t * (float) java.lang.Math.sqrt(_t8) + (1.0f - t) * (float) java.lang.Math.sqrt(_t7), _t26, Math.fma(_otherz, _t12, -(_t26 * _t17)));
    }

    /** Piece 2 of {@code slerp_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer slerp_api_sb06449d4_1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t, float _otherx, float _othery, float _t12, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33) {
        float _t34 = Math.fma(_otherx, _t12, -(_t26 * _t14));
        float _t35 = Math.fma(_othery, _t12, -(_t26 * _t20));
        float _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = Math.fma(_t39, _t17, _t33);
        float _t41 = Math.fma(_t39, _t14, _t34);
        float _t42 = Math.fma(_t39, _t20, _t35);
        float _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsByteBuffer.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t50 = t * Math.atan2((float) java.lang.Math.sqrt(_t46), _t26);
        float _sp0 = _t24 * Math.sin(_t50) * (1.0f / (float) java.lang.Math.sqrt(_t46));
        float _t55 = _t24 * Math.cos(_t50);
        dest.putFloat(destOffset, Math.fma(_t14, _t55, _sp0 * _t41));
        dest.putFloat(destOffset + 4, Math.fma(_t20, _t55, _sp0 * _t42));
        dest.putFloat(destOffset + 8, Math.fma(_t17, _t55, _sp0 * _t40));
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.slerp_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float3OpsKernelsByteBuffer.slerp_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.nio.ByteBuffer slerp_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3OpsKernelsAddress.slerp_degenerate_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.ByteBuffer slerp_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _t1 = unitScale(_otherx, _othery, _otherz);
        float _t2 = unitScale(_selfx, _selfy, _selfz);
        float _t9 = _otherz * _t1;
        float _t10 = _otherx * _t1;
        float _t11 = _othery * _t1;
        float _t12 = _selfz * _t2;
        float _t13 = _selfx * _t2;
        float _t14 = _selfy * _t2;
        float _t15 = java.lang.Math.min(_t2, _t1);
        float _t15_inv = 1.0f / _t15;
        float _t24 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        float _t25 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        float _t29 = (1.0f / (float) java.lang.Math.sqrt(_t25));
        float _t31 = _t29 * _t12;
        float _t33 = _t29 * _t13;
        float _t35 = _t29 * _t14;
        float _t48 = t * (float) java.lang.Math.sqrt(_t24) * (_t15 / _t1) + (1.0f - t) * (float) java.lang.Math.sqrt(_t25) * (_t15 / _t2);
        float _t49, _t50, _t52;
        if (java.lang.Math.abs(_t31) < java.lang.Math.abs(_t33)) {
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
        float _t68 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t50, _t50, Math.fma(_t52, _t52, _t49 * _t49))));
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
        float _t93 = (1.0f / (float) java.lang.Math.sqrt(_t91));
        float _t95 = t * Math.atan2((float) java.lang.Math.sqrt(_t91), _t53 * _t78);
        float _t99 = _t48 * Math.sin(_t95);
        float _t100 = _t48 * Math.cos(_t95);
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
                    dest.putFloat(destOffset, Math.fma(_t99, _t69, _t100 * _t33) * _t15_inv);
                    dest.putFloat(destOffset + 4, Math.fma(_t99, _t71, _t100 * _t35) * _t15_inv);
                    dest.putFloat(destOffset + 8, Math.fma(_t99, _t70, _t100 * _t31) * _t15_inv);
                } else {
                    dest.putFloat(destOffset, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                    dest.putFloat(destOffset + 4, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                    dest.putFloat(destOffset + 8, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
                }
            } else {
                dest.putFloat(destOffset, Math.fma(_t99, _t104, _t100 * _t33) * _t15_inv);
                dest.putFloat(destOffset + 4, Math.fma(_t99, _t106, _t100 * _t35) * _t15_inv);
                dest.putFloat(destOffset + 8, Math.fma(_t99, _t105, _t100 * _t31) * _t15_inv);
            }
        } else {
            dest.putFloat(destOffset, Math.fma(t, _otherx - _selfx, _selfx));
            dest.putFloat(destOffset + 4, Math.fma(t, _othery - _selfy, _selfy));
            dest.putFloat(destOffset + 8, Math.fma(t, _otherz - _selfz, _selfz));
        }
        return dest;
    }

    public static java.nio.ByteBuffer absolute_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.absolute_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer absolute_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, java.lang.Math.abs(_selfx));
        dest.putFloat(destOffset + 4, java.lang.Math.abs(_selfy));
        dest.putFloat(destOffset + 8, java.lang.Math.abs(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer acos_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.acos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer acos_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.acos(_selfx));
        dest.putFloat(destOffset + 4, Math.acos(_selfy));
        dest.putFloat(destOffset + 8, Math.acos(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float scalar) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.fma(scalar, bX, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(scalar, bY, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(scalar, bZ, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _bx = b.getFloat(bOffset);
        float _by = b.getFloat(bOffset + 4);
        float _bz = b.getFloat(bOffset + 8);
        dest.putFloat(destOffset, Math.fma(scalar, _bx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(scalar, _by, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(scalar, _bz, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, bX, bY, bZ, cX, cY, cZ);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.fma(bX, cX, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(bY, cY, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(bZ, cZ, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _bBase = UnsafeOpsHolder.U.getLong(b, UnsafeCopy.BB_ADDRESS_OFFSET) + bOffset;
        long _cBase = UnsafeOpsHolder.U.getLong(c, UnsafeCopy.BB_ADDRESS_OFFSET) + cOffset;
        Float3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _bBase, _cBase);
        return dest;
    }

    public static java.nio.ByteBuffer addScaled_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _bx = b.getFloat(bOffset);
        float _by = b.getFloat(bOffset + 4);
        float _bz = b.getFloat(bOffset + 8);
        float _cx = c.getFloat(cOffset);
        float _cy = c.getFloat(cOffset + 4);
        float _cz = c.getFloat(cOffset + 8);
        dest.putFloat(destOffset, Math.fma(_bx, _cx, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(_by, _cy, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(_bz, _cz, _selfz));
        return dest;
    }

    public static float angleBetween_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.angleBetween_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float angleBetween_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t6 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t7 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _t8 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        float _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    public static float angleBetween_degenerate(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.angleBetween_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.angleBetween_degenerate_api(src, srcOffset, otherX, otherY, otherZ);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float angleBetween_degenerate_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
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
        return Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    public static float angleBetween_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float3OpsKernelsAddress.angleBetween_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _t6 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t7 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _t8 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        float _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsByteBuffer.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    public static float angleBetween_degenerate(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.angleBetween_degenerate_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.angleBetween_degenerate_api(src, srcOffset, other, otherOffset);
    }

    public static float angleBetween_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float3OpsKernelsAddress.angleBetween_degenerate_unsafe(_srcBase, _otherBase);
    }

    public static float angleBetween_degenerate_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
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
        return Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29))), Math.fma(_t8, _t11, Math.fma(_t13, _t12, _t10 * _t9)) * _t23);
    }

    public static java.nio.ByteBuffer asin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.asin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer asin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.asin(_selfx));
        dest.putFloat(destOffset + 4, Math.asin(_selfy));
        dest.putFloat(destOffset + 8, Math.asin(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer atan_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.atan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer atan_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.atan(_selfx));
        dest.putFloat(destOffset + 4, Math.atan(_selfy));
        dest.putFloat(destOffset + 8, Math.atan(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float x) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, x);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float x) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.atan2(_selfx, x));
        dest.putFloat(destOffset + 4, Math.atan2(_selfy, x));
        dest.putFloat(destOffset + 8, Math.atan2(_selfz, x));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float xX, float xY, float xZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, xX, xY, xZ);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float xX, float xY, float xZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.atan2(_selfx, xX));
        dest.putFloat(destOffset + 4, Math.atan2(_selfy, xY));
        dest.putFloat(destOffset + 8, Math.atan2(_selfz, xZ));
        return dest;
    }

    public static java.nio.ByteBuffer atan2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _xBase = UnsafeOpsHolder.U.getLong(x, UnsafeCopy.BB_ADDRESS_OFFSET) + xOffset;
        Float3OpsKernelsAddress.atan2_unsafe(_destBase, _srcBase, _xBase);
        return dest;
    }

    public static java.nio.ByteBuffer atan2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _xx = x.getFloat(xOffset);
        float _xy = x.getFloat(xOffset + 4);
        float _xz = x.getFloat(xOffset + 8);
        dest.putFloat(destOffset, Math.atan2(_selfx, _xx));
        dest.putFloat(destOffset + 4, Math.atan2(_selfy, _xy));
        dest.putFloat(destOffset + 8, Math.atan2(_selfz, _xz));
        return dest;
    }

    public static java.nio.ByteBuffer cbrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.cbrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cbrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.cbrt(_selfx));
        dest.putFloat(destOffset + 4, Math.cbrt(_selfy));
        dest.putFloat(destOffset + 8, Math.cbrt(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer ceil_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.ceil_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer ceil_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.ceil(_selfx));
        dest.putFloat(destOffset + 4, Math.ceil(_selfy));
        dest.putFloat(destOffset + 8, Math.ceil(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float min, float max) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, min, max);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float min, float max) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, java.lang.Math.min(java.lang.Math.max(_selfx, min), max));
        dest.putFloat(destOffset + 4, java.lang.Math.min(java.lang.Math.max(_selfy, min), max));
        dest.putFloat(destOffset + 8, java.lang.Math.min(java.lang.Math.max(_selfz, min), max));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, java.lang.Math.min(java.lang.Math.max(_selfx, minX), maxX));
        dest.putFloat(destOffset + 4, java.lang.Math.min(java.lang.Math.max(_selfy, minY), maxY));
        dest.putFloat(destOffset + 8, java.lang.Math.min(java.lang.Math.max(_selfz, minZ), maxZ));
        return dest;
    }

    public static java.nio.ByteBuffer clamp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _minBase = UnsafeOpsHolder.U.getLong(min, UnsafeCopy.BB_ADDRESS_OFFSET) + minOffset;
        long _maxBase = UnsafeOpsHolder.U.getLong(max, UnsafeCopy.BB_ADDRESS_OFFSET) + maxOffset;
        Float3OpsKernelsAddress.clamp_unsafe(_destBase, _srcBase, _minBase, _maxBase);
        return dest;
    }

    public static java.nio.ByteBuffer clamp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _minx = min.getFloat(minOffset);
        float _miny = min.getFloat(minOffset + 4);
        float _minz = min.getFloat(minOffset + 8);
        float _maxx = max.getFloat(maxOffset);
        float _maxy = max.getFloat(maxOffset + 4);
        float _maxz = max.getFloat(maxOffset + 8);
        dest.putFloat(destOffset, java.lang.Math.min(java.lang.Math.max(_selfx, _minx), _maxx));
        dest.putFloat(destOffset + 4, java.lang.Math.min(java.lang.Math.max(_selfy, _miny), _maxy));
        dest.putFloat(destOffset + 8, java.lang.Math.min(java.lang.Math.max(_selfz, _minz), _maxz));
        return dest;
    }

    public static java.nio.ByteBuffer closestPointOnLine_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return dest;
    }

    public static java.nio.ByteBuffer closestPointOnLine_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        float _t0 = lineEndZ - lineStartZ;
        float _t1 = lineEndX - lineStartX;
        float _t2 = lineEndY - lineStartY;
        float _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, Math.fma(_t0, src.getFloat(srcOffset + 8) - lineStartZ, Math.fma(_t1, src.getFloat(srcOffset) - lineStartX, _t2 * (src.getFloat(srcOffset + 4) - lineStartY))) / _t10));
        if (_t10 > 0.0f) {
            dest.putFloat(destOffset, Math.fma(_t1, _t14, lineStartX));
            dest.putFloat(destOffset + 4, Math.fma(_t2, _t14, lineStartY));
            dest.putFloat(destOffset + 8, Math.fma(_t0, _t14, lineStartZ));
        } else {
            dest.putFloat(destOffset, lineStartX);
            dest.putFloat(destOffset + 4, lineStartY);
            dest.putFloat(destOffset + 8, lineStartZ);
        }
        return dest;
    }

    public static java.nio.ByteBuffer closestPointOnLine_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer lineStart, int lineStartOffset, java.nio.ByteBuffer lineEnd, int lineEndOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _lineStartBase = UnsafeOpsHolder.U.getLong(lineStart, UnsafeCopy.BB_ADDRESS_OFFSET) + lineStartOffset;
        long _lineEndBase = UnsafeOpsHolder.U.getLong(lineEnd, UnsafeCopy.BB_ADDRESS_OFFSET) + lineEndOffset;
        Float3OpsKernelsAddress.closestPointOnLine_unsafe(_destBase, _srcBase, _lineStartBase, _lineEndBase);
        return dest;
    }

    public static java.nio.ByteBuffer closestPointOnLine_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer lineStart, int lineStartOffset, java.nio.ByteBuffer lineEnd, int lineEndOffset) {
        float _lineStartx = lineStart.getFloat(lineStartOffset);
        float _lineStarty = lineStart.getFloat(lineStartOffset + 4);
        float _lineStartz = lineStart.getFloat(lineStartOffset + 8);
        float _t0 = lineEnd.getFloat(lineEndOffset + 8) - _lineStartz;
        float _t1 = lineEnd.getFloat(lineEndOffset) - _lineStartx;
        float _t2 = lineEnd.getFloat(lineEndOffset + 4) - _lineStarty;
        float _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, Math.fma(_t0, src.getFloat(srcOffset + 8) - _lineStartz, Math.fma(_t1, src.getFloat(srcOffset) - _lineStartx, _t2 * (src.getFloat(srcOffset + 4) - _lineStarty))) / _t10));
        if (_t10 > 0.0f) {
            dest.putFloat(destOffset, Math.fma(_t1, _t14, _lineStartx));
            dest.putFloat(destOffset + 4, Math.fma(_t2, _t14, _lineStarty));
            dest.putFloat(destOffset + 8, Math.fma(_t0, _t14, _lineStartz));
        } else {
            dest.putFloat(destOffset, _lineStartx);
            dest.putFloat(destOffset + 4, _lineStarty);
            dest.putFloat(destOffset + 8, _lineStartz);
        }
        return dest;
    }

    public static float compAdd_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.compAdd_unsafe(_srcBase);
    }

    public static float compAdd_api(java.nio.ByteBuffer src, int srcOffset) {
        return src.getFloat(srcOffset + 8) + (src.getFloat(srcOffset) + src.getFloat(srcOffset + 4));
    }

    public static float compMax_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.compMax_unsafe(_srcBase);
    }

    public static float compMax_api(java.nio.ByteBuffer src, int srcOffset) {
        return java.lang.Math.max(java.lang.Math.max(src.getFloat(srcOffset), src.getFloat(srcOffset + 4)), src.getFloat(srcOffset + 8));
    }

    public static float compMin_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.compMin_unsafe(_srcBase);
    }

    public static float compMin_api(java.nio.ByteBuffer src, int srcOffset) {
        return java.lang.Math.min(java.lang.Math.min(src.getFloat(srcOffset), src.getFloat(srcOffset + 4)), src.getFloat(srcOffset + 8));
    }

    public static float compMul_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.compMul_unsafe(_srcBase);
    }

    public static float compMul_api(java.nio.ByteBuffer src, int srcOffset) {
        return src.getFloat(srcOffset + 8) * src.getFloat(srcOffset) * src.getFloat(srcOffset + 4);
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sign) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, sign);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sign) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.copySign(_selfx, sign));
        dest.putFloat(destOffset + 4, Math.copySign(_selfy, sign));
        dest.putFloat(destOffset + 8, Math.copySign(_selfz, sign));
        return dest;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float signX, float signY, float signZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, signX, signY, signZ);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float signX, float signY, float signZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.copySign(_selfx, signX));
        dest.putFloat(destOffset + 4, Math.copySign(_selfy, signY));
        dest.putFloat(destOffset + 8, Math.copySign(_selfz, signZ));
        return dest;
    }

    public static java.nio.ByteBuffer copySign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _signBase = UnsafeOpsHolder.U.getLong(sign, UnsafeCopy.BB_ADDRESS_OFFSET) + signOffset;
        Float3OpsKernelsAddress.copySign_unsafe(_destBase, _srcBase, _signBase);
        return dest;
    }

    public static java.nio.ByteBuffer copySign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _signx = sign.getFloat(signOffset);
        float _signy = sign.getFloat(signOffset + 4);
        float _signz = sign.getFloat(signOffset + 8);
        dest.putFloat(destOffset, Math.copySign(_selfx, _signx));
        dest.putFloat(destOffset + 4, Math.copySign(_selfy, _signy));
        dest.putFloat(destOffset + 8, Math.copySign(_selfz, _signz));
        return dest;
    }

    public static java.nio.ByteBuffer cos_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.cos_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cos_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.cos(_selfx));
        dest.putFloat(destOffset + 4, Math.cos(_selfy));
        dest.putFloat(destOffset + 8, Math.cos(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer cosh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.cosh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer cosh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.cosh(_selfx));
        dest.putFloat(destOffset + 4, Math.cosh(_selfy));
        dest.putFloat(destOffset + 8, Math.cosh(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer cross_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer cross_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.fma(otherZ, _selfy, -(otherY * _selfz)));
        dest.putFloat(destOffset + 4, Math.fma(otherX, _selfz, -(otherZ * _selfx)));
        dest.putFloat(destOffset + 8, Math.fma(otherY, _selfx, -(otherX * _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer cross_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3OpsKernelsAddress.cross_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer cross_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        dest.putFloat(destOffset, Math.fma(_otherz, _selfy, -(_othery * _selfz)));
        dest.putFloat(destOffset + 4, Math.fma(_otherx, _selfz, -(_otherz * _selfx)));
        dest.putFloat(destOffset + 8, Math.fma(_othery, _selfx, -(_otherx * _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer degrees_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.degrees_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer degrees_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.toDegrees(_selfx));
        dest.putFloat(destOffset + 4, Math.toDegrees(_selfy));
        dest.putFloat(destOffset + 8, Math.toDegrees(_selfz));
        return dest;
    }

    public static float distance_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.distance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float distance_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _t0 = src.getFloat(srcOffset + 8) - otherZ;
        float _t1 = src.getFloat(srcOffset) - otherX;
        float _t2 = src.getFloat(srcOffset + 4) - otherY;
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    public static float distance_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float3OpsKernelsAddress.distance_unsafe(_srcBase, _otherBase);
    }

    public static float distance_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _t0 = src.getFloat(srcOffset + 8) - other.getFloat(otherOffset + 8);
        float _t1 = src.getFloat(srcOffset) - other.getFloat(otherOffset);
        float _t2 = src.getFloat(srcOffset + 4) - other.getFloat(otherOffset + 4);
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    public static float distanceSquared_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float distanceSquared_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _t0 = src.getFloat(srcOffset + 8) - otherZ;
        float _t1 = src.getFloat(srcOffset) - otherX;
        float _t2 = src.getFloat(srcOffset + 4) - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }

    public static float distanceSquared_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float3OpsKernelsAddress.distanceSquared_unsafe(_srcBase, _otherBase);
    }

    public static float distanceSquared_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _t0 = src.getFloat(srcOffset + 8) - other.getFloat(otherOffset + 8);
        float _t1 = src.getFloat(srcOffset) - other.getFloat(otherOffset);
        float _t2 = src.getFloat(srcOffset + 4) - other.getFloat(otherOffset + 4);
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }

    public static float dot_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.dot_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float dot_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        return Math.fma(otherZ, src.getFloat(srcOffset + 8), Math.fma(otherX, src.getFloat(srcOffset), otherY * src.getFloat(srcOffset + 4)));
    }

    public static float dot_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float3OpsKernelsAddress.dot_unsafe(_srcBase, _otherBase);
    }

    public static float dot_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        return Math.fma(other.getFloat(otherOffset + 8), src.getFloat(srcOffset + 8), Math.fma(other.getFloat(otherOffset), src.getFloat(srcOffset), other.getFloat(otherOffset + 4) * src.getFloat(srcOffset + 4)));
    }

    public static java.nio.ByteBuffer exp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.exp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.exp(_selfx));
        dest.putFloat(destOffset + 4, Math.exp(_selfy));
        dest.putFloat(destOffset + 8, Math.exp(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer exp2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.exp2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer exp2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.pow(2.0f, _selfx));
        dest.putFloat(destOffset + 4, Math.pow(2.0f, _selfy));
        dest.putFloat(destOffset + 8, Math.pow(2.0f, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer expm1_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.expm1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer expm1_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.expm1(_selfx));
        dest.putFloat(destOffset + 4, Math.expm1(_selfy));
        dest.putFloat(destOffset + 8, Math.expm1(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t3 = Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY)) < 0.0f ? 1.0f : -1.0f;
        dest.putFloat(destOffset, _selfx * _t3);
        dest.putFloat(destOffset + 4, _selfy * _t3);
        dest.putFloat(destOffset + 8, _selfz * _t3);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _IBase = UnsafeOpsHolder.U.getLong(I, UnsafeCopy.BB_ADDRESS_OFFSET) + IOffset;
        long _NrefBase = UnsafeOpsHolder.U.getLong(Nref, UnsafeCopy.BB_ADDRESS_OFFSET) + NrefOffset;
        Float3OpsKernelsAddress.faceforward_unsafe(_destBase, _srcBase, _IBase, _NrefBase);
        return dest;
    }

    public static java.nio.ByteBuffer faceforward_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t3 = Math.fma(I.getFloat(IOffset + 8), Nref.getFloat(NrefOffset + 8), Math.fma(I.getFloat(IOffset), Nref.getFloat(NrefOffset), I.getFloat(IOffset + 4) * Nref.getFloat(NrefOffset + 4))) < 0.0f ? 1.0f : -1.0f;
        dest.putFloat(destOffset, _selfx * _t3);
        dest.putFloat(destOffset + 4, _selfy * _t3);
        dest.putFloat(destOffset + 8, _selfz * _t3);
        return dest;
    }

    public static java.nio.ByteBuffer floor_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.floor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer floor_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.floor(_selfx));
        dest.putFloat(destOffset + 4, Math.floor(_selfy));
        dest.putFloat(destOffset + 8, Math.floor(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer fract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.fract_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer fract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, java.lang.Math.min(_selfx - Math.floor(_selfx), 0.99999994f));
        dest.putFloat(destOffset + 4, java.lang.Math.min(_selfy - Math.floor(_selfy), 0.99999994f));
        dest.putFloat(destOffset + 8, java.lang.Math.min(_selfz - Math.floor(_selfz), 0.99999994f));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.hypot(_selfx, y));
        dest.putFloat(destOffset + 4, Math.hypot(_selfy, y));
        dest.putFloat(destOffset + 8, Math.hypot(_selfz, y));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY, float yZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY, float yZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.hypot(_selfx, yX));
        dest.putFloat(destOffset + 4, Math.hypot(_selfy, yY));
        dest.putFloat(destOffset + 8, Math.hypot(_selfz, yZ));
        return dest;
    }

    public static java.nio.ByteBuffer hypot_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset;
        Float3OpsKernelsAddress.hypot_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.ByteBuffer hypot_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _yx = y.getFloat(yOffset);
        float _yy = y.getFloat(yOffset + 4);
        float _yz = y.getFloat(yOffset + 8);
        dest.putFloat(destOffset, Math.hypot(_selfx, _yx));
        dest.putFloat(destOffset + 4, Math.hypot(_selfy, _yy));
        dest.putFloat(destOffset + 8, Math.hypot(_selfz, _yz));
        return dest;
    }

    public static java.nio.ByteBuffer inverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.inverse_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer inverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, 1.0f / _selfx);
        dest.putFloat(destOffset + 4, 1.0f / _selfy);
        dest.putFloat(destOffset + 8, 1.0f / _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer inverseSqrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.inverseSqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer inverseSqrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, (1.0f / (float) java.lang.Math.sqrt(_selfx)));
        dest.putFloat(destOffset + 4, (1.0f / (float) java.lang.Math.sqrt(_selfy)));
        dest.putFloat(destOffset + 8, (1.0f / (float) java.lang.Math.sqrt(_selfz)));
        return dest;
    }

    public static float length_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.length_unsafe(_srcBase);
    }

    public static float length_api(java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        return (float) java.lang.Math.sqrt(Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    public static float lengthSquared_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.lengthSquared_unsafe(_srcBase);
    }

    public static float lengthSquared_api(java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        return Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    public static java.nio.ByteBuffer log_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.log_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.log(_selfx));
        dest.putFloat(destOffset + 4, Math.log(_selfy));
        dest.putFloat(destOffset + 8, Math.log(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer log10_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.log10_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log10_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.log10(_selfx));
        dest.putFloat(destOffset + 4, Math.log10(_selfy));
        dest.putFloat(destOffset + 8, Math.log10(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer log1p_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.log1p_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log1p_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.log1p(_selfx));
        dest.putFloat(destOffset + 4, Math.log1p(_selfy));
        dest.putFloat(destOffset + 8, Math.log1p(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer log2_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.log2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer log2_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.log2(_selfx));
        dest.putFloat(destOffset + 4, Math.log2(_selfy));
        dest.putFloat(destOffset + 8, Math.log2(_selfz));
        return dest;
    }

    public static float manhattanDistance_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, otherX, otherY, otherZ);
    }

    public static float manhattanDistance_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        return java.lang.Math.abs(src.getFloat(srcOffset) - otherX) + java.lang.Math.abs(src.getFloat(srcOffset + 4) - otherY) + java.lang.Math.abs(src.getFloat(srcOffset + 8) - otherZ);
    }

    public static float manhattanDistance_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        return Float3OpsKernelsAddress.manhattanDistance_unsafe(_srcBase, _otherBase);
    }

    public static float manhattanDistance_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        return java.lang.Math.abs(src.getFloat(srcOffset) - other.getFloat(otherOffset)) + java.lang.Math.abs(src.getFloat(srcOffset + 4) - other.getFloat(otherOffset + 4)) + java.lang.Math.abs(src.getFloat(srcOffset + 8) - other.getFloat(otherOffset + 8));
    }

    public static float manhattanLength_unsafe(java.nio.ByteBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.manhattanLength_unsafe(_srcBase);
    }

    public static float manhattanLength_api(java.nio.ByteBuffer src, int srcOffset) {
        return java.lang.Math.abs(src.getFloat(srcOffset)) + java.lang.Math.abs(src.getFloat(srcOffset + 4)) + java.lang.Math.abs(src.getFloat(srcOffset + 8));
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, java.lang.Math.max(_selfx, scalar));
        dest.putFloat(destOffset + 4, java.lang.Math.max(_selfy, scalar));
        dest.putFloat(destOffset + 8, java.lang.Math.max(_selfz, scalar));
        return dest;
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, java.lang.Math.max(_selfx, otherX));
        dest.putFloat(destOffset + 4, java.lang.Math.max(_selfy, otherY));
        dest.putFloat(destOffset + 8, java.lang.Math.max(_selfz, otherZ));
        return dest;
    }

    public static java.nio.ByteBuffer max_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3OpsKernelsAddress.max_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer max_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        dest.putFloat(destOffset, java.lang.Math.max(_selfx, _otherx));
        dest.putFloat(destOffset + 4, java.lang.Math.max(_selfy, _othery));
        dest.putFloat(destOffset + 8, java.lang.Math.max(_selfz, _otherz));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, java.lang.Math.min(_selfx, scalar));
        dest.putFloat(destOffset + 4, java.lang.Math.min(_selfy, scalar));
        dest.putFloat(destOffset + 8, java.lang.Math.min(_selfz, scalar));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, otherX, otherY, otherZ);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, java.lang.Math.min(_selfx, otherX));
        dest.putFloat(destOffset + 4, java.lang.Math.min(_selfy, otherY));
        dest.putFloat(destOffset + 8, java.lang.Math.min(_selfz, otherZ));
        return dest;
    }

    public static java.nio.ByteBuffer min_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        Float3OpsKernelsAddress.min_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.ByteBuffer min_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        dest.putFloat(destOffset, java.lang.Math.min(_selfx, _otherx));
        dest.putFloat(destOffset + 4, java.lang.Math.min(_selfy, _othery));
        dest.putFloat(destOffset + 8, java.lang.Math.min(_selfz, _otherz));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, y);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, flooredMod(_selfx, y));
        dest.putFloat(destOffset + 4, flooredMod(_selfy, y));
        dest.putFloat(destOffset + 8, flooredMod(_selfz, y));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY, float yZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, yX, yY, yZ);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY, float yZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, flooredMod(_selfx, yX));
        dest.putFloat(destOffset + 4, flooredMod(_selfy, yY));
        dest.putFloat(destOffset + 8, flooredMod(_selfz, yZ));
        return dest;
    }

    public static java.nio.ByteBuffer mod_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _yBase = UnsafeOpsHolder.U.getLong(y, UnsafeCopy.BB_ADDRESS_OFFSET) + yOffset;
        Float3OpsKernelsAddress.mod_unsafe(_destBase, _srcBase, _yBase);
        return dest;
    }

    public static java.nio.ByteBuffer mod_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _yx = y.getFloat(yOffset);
        float _yy = y.getFloat(yOffset + 4);
        float _yz = y.getFloat(yOffset + 8);
        dest.putFloat(destOffset, flooredMod(_selfx, _yx));
        dest.putFloat(destOffset + 4, flooredMod(_selfy, _yy));
        dest.putFloat(destOffset + 8, flooredMod(_selfz, _yz));
        return dest;
    }

    public static java.nio.ByteBuffer nextDown_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.nextDown_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer nextDown_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.nextDown(_selfx));
        dest.putFloat(destOffset + 4, Math.nextDown(_selfy));
        dest.putFloat(destOffset + 8, Math.nextDown(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer nextUp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.nextUp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer nextUp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.nextUp(_selfx));
        dest.putFloat(destOffset + 4, Math.nextUp(_selfy));
        dest.putFloat(destOffset + 8, Math.nextUp(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer normalize_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.normalize_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer normalize_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.putFloat(destOffset, _selfx * _t3);
            dest.putFloat(destOffset + 4, _selfy * _t3);
            dest.putFloat(destOffset + 8, _selfz * _t3);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer normalizeMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float length) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.normalizeMul_unsafe(_destBase, _srcBase, length);
        return dest;
    }

    public static java.nio.ByteBuffer normalizeMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float length) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t4 = length * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest.putFloat(destOffset, _selfx * _t4);
            dest.putFloat(destOffset + 4, _selfy * _t4);
            dest.putFloat(destOffset + 8, _selfz * _t4);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static float orientedAngle_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t7 = unitScale(normalX, normalY, normalZ);
        float _t9 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _t10 = Math.fma(otherX, _selfz, -(otherZ * _selfx));
        float _t11 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsByteBuffer.orientedAngle_degenerate(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        float _t18 = Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
        return Math.fma(_t9, normalZ * _t7, Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0f ? -_t18 : _t18;
    }

    public static float orientedAngle_degenerate(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return Float3OpsKernelsByteBuffer.orientedAngle_degenerate_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        return Float3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    public static float orientedAngle_degenerate_api(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
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
        float _t40 = Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(normalZ * _t0, _t31, Math.fma(normalX * _t0, _t32, normalY * _t0 * _t33)) < 0.0f ? -_t40 : _t40;
    }

    public static float orientedAngle_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        return Float3OpsKernelsAddress.orientedAngle_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static float orientedAngle_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer normal, int normalOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _normalx = normal.getFloat(normalOffset);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
        float _t7 = unitScale(_normalx, _normaly, _normalz);
        float _t9 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _t10 = Math.fma(_otherx, _selfz, -(_otherz * _selfx));
        float _t11 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsByteBuffer.orientedAngle_degenerate(src, srcOffset, other, otherOffset, normal, normalOffset);
        float _t18 = Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
        return Math.fma(_t9, _normalz * _t7, Math.fma(_t10, _normaly * _t7, _t11 * (_normalx * _t7))) < 0.0f ? -_t18 : _t18;
    }

    public static float orientedAngle_degenerate(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.orientedAngle_degenerate_unsafe(src, srcOffset, other, otherOffset, normal, normalOffset);
        return Float3OpsKernelsByteBuffer.orientedAngle_degenerate_api(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    public static float orientedAngle_degenerate_unsafe(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        return Float3OpsKernelsAddress.orientedAngle_degenerate_unsafe(_srcBase, _otherBase, _normalBase);
    }

    public static float orientedAngle_degenerate_api(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer normal, int normalOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _otherx = other.getFloat(otherOffset);
        float _othery = other.getFloat(otherOffset + 4);
        float _otherz = other.getFloat(otherOffset + 8);
        float _normalx = normal.getFloat(normalOffset);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
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
        return orientedAngle_degenerate_api_sb9f89de8_1(_normalx, _normaly, _normalz, unitScale(_normalx, _normaly, _normalz), _t9, _t10, _t11, _t12, _t13, _t14, _t27, _t23 * _t27, _t24 * _t27, _t25 * _t27);
    }

    /** Piece 2 of {@code orientedAngle_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static float orientedAngle_degenerate_api_sb9f89de8_1(float _normalx, float _normaly, float _normalz, float _t0, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t27, float _t31, float _t32, float _t33) {
        float _t40 = Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t31, _t31, Math.fma(_t33, _t33, _t32 * _t32))), Math.fma(_t13, _t14, Math.fma(_t11, _t10, _t9 * _t12)) * _t27);
        return Math.fma(_normalz * _t0, _t31, Math.fma(_normalx * _t0, _t32, _normaly * _t0 * _t33)) < 0.0f ? -_t40 : _t40;
    }

    public static java.nio.ByteBuffer outerProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float rowX, float rowY, float rowZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, rowX, rowY, rowZ);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float rowX, float rowY, float rowZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, rowX * _selfx);
        dest.putFloat(destOffset + 4, rowX * _selfy);
        dest.putFloat(destOffset + 8, rowX * _selfz);
        dest.putFloat(destOffset + 12, rowY * _selfx);
        dest.putFloat(destOffset + 16, rowY * _selfy);
        dest.putFloat(destOffset + 20, rowY * _selfz);
        dest.putFloat(destOffset + 24, rowZ * _selfx);
        dest.putFloat(destOffset + 28, rowZ * _selfy);
        dest.putFloat(destOffset + 32, rowZ * _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset;
        Float3OpsKernelsAddress.outerProduct_unsafe(_destBase, _srcBase, _rowBase);
        return dest;
    }

    public static java.nio.ByteBuffer outerProduct_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _rowx = row.getFloat(rowOffset);
        float _rowy = row.getFloat(rowOffset + 4);
        float _rowz = row.getFloat(rowOffset + 8);
        dest.putFloat(destOffset, _rowx * _selfx);
        dest.putFloat(destOffset + 4, _rowx * _selfy);
        dest.putFloat(destOffset + 8, _rowx * _selfz);
        dest.putFloat(destOffset + 12, _rowy * _selfx);
        dest.putFloat(destOffset + 16, _rowy * _selfy);
        dest.putFloat(destOffset + 20, _rowy * _selfz);
        dest.putFloat(destOffset + 24, _rowz * _selfx);
        dest.putFloat(destOffset + 28, _rowz * _selfy);
        dest.putFloat(destOffset + 32, _rowz * _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer perpendicular_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.perpendicular_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer perpendicular_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        if (java.lang.Math.abs(_selfz) < java.lang.Math.abs(_selfx)) {
            dest.putFloat(destOffset, _selfy);
            dest.putFloat(destOffset + 4, -_selfx);
            dest.putFloat(destOffset + 8, 0.0f);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, _selfz);
            dest.putFloat(destOffset + 8, -_selfy);
        }
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponent) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponent);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponent) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.pow(_selfx, exponent));
        dest.putFloat(destOffset + 4, Math.pow(_selfy, exponent));
        dest.putFloat(destOffset + 8, Math.pow(_selfz, exponent));
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, exponentX, exponentY, exponentZ);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.pow(_selfx, exponentX));
        dest.putFloat(destOffset + 4, Math.pow(_selfy, exponentY));
        dest.putFloat(destOffset + 8, Math.pow(_selfz, exponentZ));
        return dest;
    }

    public static java.nio.ByteBuffer pow_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _exponentBase = UnsafeOpsHolder.U.getLong(exponent, UnsafeCopy.BB_ADDRESS_OFFSET) + exponentOffset;
        Float3OpsKernelsAddress.pow_unsafe(_destBase, _srcBase, _exponentBase);
        return dest;
    }

    public static java.nio.ByteBuffer pow_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _exponentx = exponent.getFloat(exponentOffset);
        float _exponenty = exponent.getFloat(exponentOffset + 4);
        float _exponentz = exponent.getFloat(exponentOffset + 8);
        dest.putFloat(destOffset, Math.pow(_selfx, _exponentx));
        dest.putFloat(destOffset + 4, Math.pow(_selfy, _exponenty));
        dest.putFloat(destOffset + 8, Math.pow(_selfz, _exponentz));
        return dest;
    }

    public static java.nio.ByteBuffer project_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, ontoX, ontoY, ontoZ);
        return dest;
    }

    public static java.nio.ByteBuffer project_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ) {
        float _t7 = Math.fma(ontoZ, src.getFloat(srcOffset + 8), Math.fma(ontoX, src.getFloat(srcOffset), ontoY * src.getFloat(srcOffset + 4))) / Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY));
        dest.putFloat(destOffset, ontoX * _t7);
        dest.putFloat(destOffset + 4, ontoY * _t7);
        dest.putFloat(destOffset + 8, ontoZ * _t7);
        return dest;
    }

    public static java.nio.ByteBuffer project_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _ontoBase = UnsafeOpsHolder.U.getLong(onto, UnsafeCopy.BB_ADDRESS_OFFSET) + ontoOffset;
        Float3OpsKernelsAddress.project_unsafe(_destBase, _srcBase, _ontoBase);
        return dest;
    }

    public static java.nio.ByteBuffer project_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        float _ontox = onto.getFloat(ontoOffset);
        float _ontoy = onto.getFloat(ontoOffset + 4);
        float _ontoz = onto.getFloat(ontoOffset + 8);
        float _t7 = Math.fma(_ontoz, src.getFloat(srcOffset + 8), Math.fma(_ontox, src.getFloat(srcOffset), _ontoy * src.getFloat(srcOffset + 4))) / Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy));
        dest.putFloat(destOffset, _ontox * _t7);
        dest.putFloat(destOffset + 4, _ontoy * _t7);
        dest.putFloat(destOffset + 8, _ontoz * _t7);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t2 = Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        dest.putFloat(destOffset, Math.fma(-normalX, _t2, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(-normalY, _t2, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(-normalZ, _t2, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Float3OpsKernelsAddress.projectOnPlane_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer projectOnPlane_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _normalx = normal.getFloat(normalOffset);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
        float _t2 = Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest.putFloat(destOffset, Math.fma(-_normalx, _t2, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(-_normaly, _t2, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(-_normalz, _t2, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer radians_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.radians_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer radians_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.toRadians(_selfx));
        dest.putFloat(destOffset + 4, Math.toRadians(_selfy));
        dest.putFloat(destOffset + 8, Math.toRadians(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t3 = 2.0f * Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        dest.putFloat(destOffset, Math.fma(-normalX, _t3, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(-normalY, _t3, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(-normalZ, _t3, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer reflect_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Float3OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.ByteBuffer reflect_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _normalx = normal.getFloat(normalOffset);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
        float _t3 = 2.0f * Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest.putFloat(destOffset, Math.fma(-_normalx, _t3, _selfx));
        dest.putFloat(destOffset + 4, Math.fma(-_normaly, _t3, _selfy));
        dest.putFloat(destOffset + 8, Math.fma(-_normalz, _t3, _selfz));
        return dest;
    }

    public static java.nio.ByteBuffer refract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, normalX, normalY, normalZ, eta);
        return dest;
    }

    public static java.nio.ByteBuffer refract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float eta) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t3 = Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        float _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0f), eta * eta, 1.0f);
        float _t10 = Math.fma(eta, _t3, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            dest.putFloat(destOffset, Math.fma(eta, _selfx, -(normalX * _t10)));
            dest.putFloat(destOffset + 4, Math.fma(eta, _selfy, -(normalY * _t10)));
            dest.putFloat(destOffset + 8, Math.fma(eta, _selfz, -(normalZ * _t10)));
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer refract_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, float eta) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset;
        Float3OpsKernelsAddress.refract_unsafe(_destBase, _srcBase, _normalBase, eta);
        return dest;
    }

    public static java.nio.ByteBuffer refract_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, float eta) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _normalx = normal.getFloat(normalOffset);
        float _normaly = normal.getFloat(normalOffset + 4);
        float _normalz = normal.getFloat(normalOffset + 8);
        float _t3 = Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        float _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0f), eta * eta, 1.0f);
        float _t10 = Math.fma(eta, _t3, (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            dest.putFloat(destOffset, Math.fma(eta, _selfx, -(_normalx * _t10)));
            dest.putFloat(destOffset + 4, Math.fma(eta, _selfy, -(_normaly * _t10)));
            dest.putFloat(destOffset + 8, Math.fma(eta, _selfz, -(_normalz * _t10)));
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer round_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.round_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer round_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.rint(_selfx));
        dest.putFloat(destOffset + 4, Math.rint(_selfy));
        dest.putFloat(destOffset + 8, Math.rint(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer sign_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.sign_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sign_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.signum(_selfx));
        dest.putFloat(destOffset + 4, Math.signum(_selfy));
        dest.putFloat(destOffset + 8, Math.signum(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer sin_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.sin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sin_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.sin(_selfx));
        dest.putFloat(destOffset + 4, Math.sin(_selfy));
        dest.putFloat(destOffset + 8, Math.sin(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer sinh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.sinh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sinh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.sinh(_selfx));
        dest.putFloat(destOffset + 4, Math.sinh(_selfy));
        dest.putFloat(destOffset + 8, Math.sinh(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0, float edge1) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0, edge1);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t10 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.getFloat(srcOffset) - edge0) * _t0_inv));
        float _t11 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.getFloat(srcOffset + 4) - edge0) * _t0_inv));
        float _t12 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.getFloat(srcOffset + 8) - edge0) * _t0_inv));
        dest.putFloat(destOffset, Math.fma(-2.0f, _t10, 3.0f) * _t10 * _t10);
        dest.putFloat(destOffset + 4, Math.fma(-2.0f, _t11, 3.0f) * _t11 * _t11);
        dest.putFloat(destOffset + 8, Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        float _t12 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.getFloat(srcOffset) - edge0X) / (edge1X - edge0X)));
        float _t13 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.getFloat(srcOffset + 4) - edge0Y) / (edge1Y - edge0Y)));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.getFloat(srcOffset + 8) - edge0Z) / (edge1Z - edge0Z)));
        dest.putFloat(destOffset, Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        dest.putFloat(destOffset + 4, Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.putFloat(destOffset + 8, Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _edge0Base = UnsafeOpsHolder.U.getLong(edge0, UnsafeCopy.BB_ADDRESS_OFFSET) + edge0Offset;
        long _edge1Base = UnsafeOpsHolder.U.getLong(edge1, UnsafeCopy.BB_ADDRESS_OFFSET) + edge1Offset;
        Float3OpsKernelsAddress.smoothstep_unsafe(_destBase, _srcBase, _edge0Base, _edge1Base);
        return dest;
    }

    public static java.nio.ByteBuffer smoothstep_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        float _edge0x = edge0.getFloat(edge0Offset);
        float _edge0y = edge0.getFloat(edge0Offset + 4);
        float _edge0z = edge0.getFloat(edge0Offset + 8);
        float _t12 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.getFloat(srcOffset) - _edge0x) / (edge1.getFloat(edge1Offset) - _edge0x)));
        float _t13 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.getFloat(srcOffset + 4) - _edge0y) / (edge1.getFloat(edge1Offset + 4) - _edge0y)));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src.getFloat(srcOffset + 8) - _edge0z) / (edge1.getFloat(edge1Offset + 8) - _edge0z)));
        dest.putFloat(destOffset, Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12);
        dest.putFloat(destOffset + 4, Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13);
        dest.putFloat(destOffset + 8, Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14);
        return dest;
    }

    public static java.nio.ByteBuffer sqrt_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.sqrt_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer sqrt_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, (float) java.lang.Math.sqrt(_selfx));
        dest.putFloat(destOffset + 4, (float) java.lang.Math.sqrt(_selfy));
        dest.putFloat(destOffset + 8, (float) java.lang.Math.sqrt(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edge);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, _selfx < edge ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 4, _selfy < edge ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 8, _selfz < edge ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, edgeX, edgeY, edgeZ);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, _selfx < edgeX ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 4, _selfy < edgeY ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 8, _selfz < edgeZ ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer step_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _edgeBase = UnsafeOpsHolder.U.getLong(edge, UnsafeCopy.BB_ADDRESS_OFFSET) + edgeOffset;
        Float3OpsKernelsAddress.step_unsafe(_destBase, _srcBase, _edgeBase);
        return dest;
    }

    public static java.nio.ByteBuffer step_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _edgex = edge.getFloat(edgeOffset);
        float _edgey = edge.getFloat(edgeOffset + 4);
        float _edgez = edge.getFloat(edgeOffset + 8);
        dest.putFloat(destOffset, _selfx < _edgex ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 4, _selfy < _edgey ? 0.0f : 1.0f);
        dest.putFloat(destOffset + 8, _selfz < _edgez ? 0.0f : 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer tan_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.tan_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer tan_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.tan(_selfx));
        dest.putFloat(destOffset + 4, Math.tan(_selfy));
        dest.putFloat(destOffset + 8, Math.tan(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer tanh_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.tanh_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer tanh_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.tanh(_selfx));
        dest.putFloat(destOffset + 4, Math.tanh(_selfy));
        dest.putFloat(destOffset + 8, Math.tanh(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
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
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsByteBuffer.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        dest.putFloat(destOffset, _t12 * _t19);
        dest.putFloat(destOffset + 4, _t14 * _t19);
        dest.putFloat(destOffset + 8, _t13 * _t19);
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return Float3OpsKernelsByteBuffer.triangleNormal_degenerate_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    public static java.nio.ByteBuffer triangleNormal_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t19 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(p1Z)), java.lang.Math.max(java.lang.Math.abs(p2Z), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(p1X)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p2X), java.lang.Math.abs(_selfy))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(p1Y), java.lang.Math.abs(p2Y))))));
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
        return triangleNormal_degenerate_api_sed81f8b7_1(dest, destOffset, Math.fma(_t52, _t53, -(_t54 * _t55)), Math.fma(_t54, _t56, -(_t57 * _t53)), Math.fma(_t57, _t55, -(_t52 * _t56)));
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer triangleNormal_degenerate_api_sed81f8b7_1(java.nio.ByteBuffer dest, int destOffset, float _t64, float _t65, float _t66) {
        float _t67 = unitScale(_t65, _t66, _t64);
        float _t71 = _t64 * _t67;
        float _t72 = _t65 * _t67;
        float _t73 = _t66 * _t67;
        float _t76 = Math.fma(_t71, _t71, Math.fma(_t72, _t72, _t73 * _t73));
        float _t77 = (1.0f / (float) java.lang.Math.sqrt(_t76));
        if (_t76 != 0.0f) {
            dest.putFloat(destOffset, _t77 * _t72);
            dest.putFloat(destOffset + 4, _t77 * _t73);
            dest.putFloat(destOffset + 8, _t77 * _t71);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Float3OpsKernelsAddress.triangleNormal_unsafe(_destBase, _srcBase, _p1Base, _p2Base);
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = p1.getFloat(p1Offset + 4) - _selfy;
        float _t1 = p2.getFloat(p2Offset + 8) - _selfz;
        float _t2 = p1.getFloat(p1Offset + 8) - _selfz;
        float _t3 = p2.getFloat(p2Offset + 4) - _selfy;
        float _t4 = p1.getFloat(p1Offset) - _selfx;
        float _t5 = p2.getFloat(p2Offset) - _selfx;
        float _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        float _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        float _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsByteBuffer.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        float _t19 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        dest.putFloat(destOffset, _t12 * _t19);
        dest.putFloat(destOffset + 4, _t14 * _t19);
        dest.putFloat(destOffset + 8, _t13 * _t19);
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_degenerate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.triangleNormal_degenerate_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return Float3OpsKernelsByteBuffer.triangleNormal_degenerate_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    public static java.nio.ByteBuffer triangleNormal_degenerate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _p1Base = UnsafeOpsHolder.U.getLong(p1, UnsafeCopy.BB_ADDRESS_OFFSET) + p1Offset;
        long _p2Base = UnsafeOpsHolder.U.getLong(p2, UnsafeCopy.BB_ADDRESS_OFFSET) + p2Offset;
        Float3OpsKernelsAddress.triangleNormal_degenerate_unsafe(_destBase, _srcBase, _p1Base, _p2Base);
        return dest;
    }

    public static java.nio.ByteBuffer triangleNormal_degenerate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _p1x = p1.getFloat(p1Offset);
        float _p1y = p1.getFloat(p1Offset + 4);
        float _p1z = p1.getFloat(p1Offset + 8);
        float _p2x = p2.getFloat(p2Offset);
        float _p2y = p2.getFloat(p2Offset + 4);
        float _p2z = p2.getFloat(p2Offset + 8);
        float _t19 = java.lang.Math.min(1.0f, unitScale(java.lang.Math.max(java.lang.Math.abs(_selfz), java.lang.Math.abs(_p1z)), java.lang.Math.max(java.lang.Math.abs(_p2z), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_selfx), java.lang.Math.abs(_p1x)))), java.lang.Math.max(java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_p2x), java.lang.Math.abs(_selfy))), java.lang.Math.abs(java.lang.Math.max(java.lang.Math.abs(_p1y), java.lang.Math.abs(_p2y))))));
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
        return triangleNormal_degenerate_api_s4d3d020e_1(dest, destOffset, _t40, _t43, _t44, _t45, _t38 * _t44, _t41 * _t45, _t39 * _t44, _t42 * _t45);
    }

    /** Piece 2 of {@code triangleNormal_degenerate_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer triangleNormal_degenerate_api_s4d3d020e_1(java.nio.ByteBuffer dest, int destOffset, float _t40, float _t43, float _t44, float _t45, float _t52, float _t53, float _t54, float _t55) {
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
        float _t77 = (1.0f / (float) java.lang.Math.sqrt(_t76));
        if (_t76 != 0.0f) {
            dest.putFloat(destOffset, _t77 * _t72);
            dest.putFloat(destOffset + 4, _t77 * _t73);
            dest.putFloat(destOffset + 8, _t77 * _t71);
        } else {
            dest.putFloat(destOffset, 0.0f);
            dest.putFloat(destOffset + 4, 0.0f);
            dest.putFloat(destOffset + 8, 0.0f);
        }
        return dest;
    }

    public static java.nio.ByteBuffer trunc_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.trunc_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer trunc_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx));
        dest.putFloat(destOffset + 4, _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy));
        dest.putFloat(destOffset + 8, _selfz >= 0.0f ? Math.floor(_selfz) : Math.ceil(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer ulp_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.ulp_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer ulp_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, Math.ulp(_selfx));
        dest.putFloat(destOffset + 4, Math.ulp(_selfy));
        dest.putFloat(destOffset + 8, Math.ulp(_selfz));
        return dest;
    }

    public static java.nio.ByteBuffer xyz0_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.xyz0_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer xyz0_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, _selfx);
        dest.putFloat(destOffset + 4, _selfy);
        dest.putFloat(destOffset + 8, _selfz);
        dest.putFloat(destOffset + 12, 0.0f);
        return dest;
    }

    public static java.nio.ByteBuffer xyz1_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.xyz1_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.ByteBuffer xyz1_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        dest.putFloat(destOffset, _selfx);
        dest.putFloat(destOffset + 4, _selfy);
        dest.putFloat(destOffset + 8, _selfz);
        dest.putFloat(destOffset + 12, 1.0f);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMul_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _mat00 = mat.getFloat(matOffset);
        float _mat10 = mat.getFloat(matOffset + 4);
        float _mat20 = mat.getFloat(matOffset + 8);
        float _mat01 = mat.getFloat(matOffset + 12);
        float _mat11 = mat.getFloat(matOffset + 16);
        float _mat21 = mat.getFloat(matOffset + 20);
        float _mat02 = mat.getFloat(matOffset + 24);
        float _mat12 = mat.getFloat(matOffset + 28);
        float _mat22 = mat.getFloat(matOffset + 32);
        dest.putFloat(destOffset, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        dest.putFloat(destOffset + 4, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.putFloat(destOffset + 8, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float3OpsKernelsAddress.preMulDirectionMat3x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _mat00 = mat.getFloat(matOffset);
        float _mat01 = mat.getFloat(matOffset + 4);
        float _mat02 = mat.getFloat(matOffset + 8);
        float _mat10 = mat.getFloat(matOffset + 16);
        float _mat11 = mat.getFloat(matOffset + 20);
        float _mat12 = mat.getFloat(matOffset + 24);
        float _mat20 = mat.getFloat(matOffset + 32);
        float _mat21 = mat.getFloat(matOffset + 36);
        float _mat22 = mat.getFloat(matOffset + 40);
        dest.putFloat(destOffset, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        dest.putFloat(destOffset + 4, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.putFloat(destOffset + 8, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float3OpsKernelsAddress.preMulDirectionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulDirectionMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _mat00 = mat.getFloat(matOffset);
        float _mat10 = mat.getFloat(matOffset + 4);
        float _mat20 = mat.getFloat(matOffset + 8);
        float _mat01 = mat.getFloat(matOffset + 16);
        float _mat11 = mat.getFloat(matOffset + 20);
        float _mat21 = mat.getFloat(matOffset + 24);
        float _mat02 = mat.getFloat(matOffset + 32);
        float _mat12 = mat.getFloat(matOffset + 36);
        float _mat22 = mat.getFloat(matOffset + 40);
        dest.putFloat(destOffset, Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy)));
        dest.putFloat(destOffset + 4, Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy)));
        dest.putFloat(destOffset + 8, Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy)));
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat3x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float3OpsKernelsAddress.preMulPositionMat3x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat3x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _mat00 = mat.getFloat(matOffset);
        float _mat01 = mat.getFloat(matOffset + 4);
        float _mat02 = mat.getFloat(matOffset + 8);
        float _mat03 = mat.getFloat(matOffset + 12);
        float _mat10 = mat.getFloat(matOffset + 16);
        float _mat11 = mat.getFloat(matOffset + 20);
        float _mat12 = mat.getFloat(matOffset + 24);
        float _mat13 = mat.getFloat(matOffset + 28);
        float _mat20 = mat.getFloat(matOffset + 32);
        float _mat21 = mat.getFloat(matOffset + 36);
        float _mat22 = mat.getFloat(matOffset + 40);
        float _mat23 = mat.getFloat(matOffset + 44);
        dest.putFloat(destOffset, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, Math.fma(_mat02, _selfz, _mat03))));
        dest.putFloat(destOffset + 4, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))));
        dest.putFloat(destOffset + 8, Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat4x4_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float3OpsKernelsAddress.preMulPositionMat4x4_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulPositionMat4x4_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _mat00 = mat.getFloat(matOffset);
        float _mat10 = mat.getFloat(matOffset + 4);
        float _mat20 = mat.getFloat(matOffset + 8);
        float _mat01 = mat.getFloat(matOffset + 16);
        float _mat11 = mat.getFloat(matOffset + 20);
        float _mat21 = mat.getFloat(matOffset + 24);
        float _mat02 = mat.getFloat(matOffset + 32);
        float _mat12 = mat.getFloat(matOffset + 36);
        float _mat22 = mat.getFloat(matOffset + 40);
        float _mat03 = mat.getFloat(matOffset + 48);
        float _mat13 = mat.getFloat(matOffset + 52);
        float _mat23 = mat.getFloat(matOffset + 56);
        dest.putFloat(destOffset, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, Math.fma(_mat02, _selfz, _mat03))));
        dest.putFloat(destOffset + 4, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))));
        dest.putFloat(destOffset + 8, Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))));
        return dest;
    }

    public static java.nio.ByteBuffer preMulProject_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _matBase = UnsafeOpsHolder.U.getLong(mat, UnsafeCopy.BB_ADDRESS_OFFSET) + matOffset;
        Float3OpsKernelsAddress.preMulProject_unsafe(_destBase, _srcBase, _matBase);
        return dest;
    }

    public static java.nio.ByteBuffer preMulProject_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _mat00 = mat.getFloat(matOffset);
        float _mat10 = mat.getFloat(matOffset + 4);
        float _mat20 = mat.getFloat(matOffset + 8);
        float _mat01 = mat.getFloat(matOffset + 16);
        float _mat11 = mat.getFloat(matOffset + 20);
        float _mat21 = mat.getFloat(matOffset + 24);
        float _mat02 = mat.getFloat(matOffset + 32);
        float _mat12 = mat.getFloat(matOffset + 36);
        float _mat22 = mat.getFloat(matOffset + 40);
        float _mat03 = mat.getFloat(matOffset + 48);
        float _mat13 = mat.getFloat(matOffset + 52);
        float _mat23 = mat.getFloat(matOffset + 56);
        float _t2_inv = 1.0f / Math.fma(mat.getFloat(matOffset + 12), _selfx, Math.fma(mat.getFloat(matOffset + 28), _selfy, Math.fma(mat.getFloat(matOffset + 44), _selfz, mat.getFloat(matOffset + 60))));
        dest.putFloat(destOffset, Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, Math.fma(_mat02, _selfz, _mat03))) * _t2_inv);
        return preMulProject_api_s6cb270c_1(dest, destOffset, _selfx, _selfy, _selfz, _mat10, _mat20, _mat11, _mat21, _mat12, _mat22, _mat13, _mat23, _t2_inv);
    }

    /** Piece 2 of {@code preMulProject_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.ByteBuffer preMulProject_api_s6cb270c_1(java.nio.ByteBuffer dest, int destOffset, float _selfx, float _selfy, float _selfz, float _mat10, float _mat20, float _mat11, float _mat21, float _mat12, float _mat22, float _mat13, float _mat23, float _t2_inv) {
        dest.putFloat(destOffset + 4, Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))) * _t2_inv);
        dest.putFloat(destOffset + 8, Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))) * _t2_inv);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t9 = 2.0f * Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest.putFloat(destOffset, Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.putFloat(destOffset + 4, Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy))));
        dest.putFloat(destOffset + 8, Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotate_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset;
        Float3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotate_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _quatx = quat.getFloat(quatOffset);
        float _quaty = quat.getFloat(quatOffset + 4);
        float _quatz = quat.getFloat(quatOffset + 8);
        float _quatw = quat.getFloat(quatOffset + 12);
        float _t9 = 2.0f * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        float _t10 = 2.0f * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        float _t11 = 2.0f * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest.putFloat(destOffset, Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.putFloat(destOffset + 4, Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy))));
        dest.putFloat(destOffset + 8, Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = _selfy - pivotY;
        float _t1 = _selfx - pivotX;
        float _t2 = _selfz - pivotZ;
        float _t12 = 2.0f * Math.fma(quatX, _t0, -(quatY * _t1));
        float _t13 = 2.0f * Math.fma(quatZ, _t1, -(quatX * _t2));
        float _t14 = 2.0f * Math.fma(quatY, _t2, -(quatZ * _t0));
        dest.putFloat(destOffset, Math.fma(quatY, _t12, Math.fma(-quatZ, _t13, Math.fma(quatW, _t14, _selfx))));
        dest.putFloat(destOffset + 4, Math.fma(quatZ, _t14, Math.fma(-quatX, _t12, Math.fma(quatW, _t13, _selfy))));
        dest.putFloat(destOffset + 8, Math.fma(quatX, _t13, Math.fma(-quatY, _t14, Math.fma(quatW, _t12, _selfz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _quatBase, _pivotBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _quatx = quat.getFloat(quatOffset);
        float _quaty = quat.getFloat(quatOffset + 4);
        float _quatz = quat.getFloat(quatOffset + 8);
        float _quatw = quat.getFloat(quatOffset + 12);
        float _t0 = _selfy - pivot.getFloat(pivotOffset + 4);
        float _t1 = _selfx - pivot.getFloat(pivotOffset);
        float _t2 = _selfz - pivot.getFloat(pivotOffset + 8);
        float _t12 = 2.0f * Math.fma(_quatx, _t0, -(_quaty * _t1));
        float _t13 = 2.0f * Math.fma(_quatz, _t1, -(_quatx * _t2));
        float _t14 = 2.0f * Math.fma(_quaty, _t2, -(_quatz * _t0));
        dest.putFloat(destOffset, Math.fma(_quaty, _t12, Math.fma(-_quatz, _t13, Math.fma(_quatw, _t14, _selfx))));
        dest.putFloat(destOffset + 4, Math.fma(_quatz, _t14, Math.fma(-_quatx, _t12, Math.fma(_quatw, _t13, _selfy))));
        dest.putFloat(destOffset + 8, Math.fma(_quatx, _t13, Math.fma(-_quaty, _t14, Math.fma(_quatw, _t12, _selfz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        dest.putFloat(destOffset, Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0)));
        dest.putFloat(destOffset + 4, Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0)));
        dest.putFloat(destOffset + 8, Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        Float3OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxis_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest.putFloat(destOffset, Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0)));
        dest.putFloat(destOffset + 4, Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0)));
        dest.putFloat(destOffset + 8, Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxisAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxisAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.getFloat(srcOffset) - pivotX;
        float _t3 = src.getFloat(srcOffset + 8) - pivotZ;
        float _t4 = src.getFloat(srcOffset + 4) - pivotY;
        float _t5 = 1.0f - _t1;
        float _t8 = Math.fma(axisZ, _t3, Math.fma(axisX, _t2, axisY * _t4));
        dest.putFloat(destOffset, Math.fma(_t2, _t1, Math.fma(Math.fma(axisY, _t3, -(axisZ * _t4)), _t0, Math.fma(_t5, axisX * _t8, pivotX))));
        dest.putFloat(destOffset + 4, Math.fma(_t4, _t1, Math.fma(Math.fma(axisZ, _t2, -(axisX * _t3)), _t0, Math.fma(_t5, axisY * _t8, pivotY))));
        dest.putFloat(destOffset + 8, Math.fma(_t3, _t1, Math.fma(Math.fma(axisX, _t4, -(axisY * _t2)), _t0, Math.fma(_t5, axisZ * _t8, pivotZ))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxisAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3OpsKernelsAddress.rotateAxisAround_unsafe(_destBase, _srcBase, _axisBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateAxisAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _axisx = axis.getFloat(axisOffset);
        float _axisy = axis.getFloat(axisOffset + 4);
        float _axisz = axis.getFloat(axisOffset + 8);
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _pivotz = pivot.getFloat(pivotOffset + 8);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.getFloat(srcOffset) - _pivotx;
        float _t3 = src.getFloat(srcOffset + 8) - _pivotz;
        float _t4 = src.getFloat(srcOffset + 4) - _pivoty;
        float _t5 = 1.0f - _t1;
        float _t8 = Math.fma(_axisz, _t3, Math.fma(_axisx, _t2, _axisy * _t4));
        dest.putFloat(destOffset, Math.fma(_t2, _t1, Math.fma(Math.fma(_axisy, _t3, -(_axisz * _t4)), _t0, Math.fma(_t5, _axisx * _t8, _pivotx))));
        dest.putFloat(destOffset + 4, Math.fma(_t4, _t1, Math.fma(Math.fma(_axisz, _t2, -(_axisx * _t3)), _t0, Math.fma(_t5, _axisy * _t8, _pivoty))));
        dest.putFloat(destOffset + 8, Math.fma(_t3, _t1, Math.fma(Math.fma(_axisx, _t4, -(_axisy * _t2)), _t0, Math.fma(_t5, _axisz * _t8, _pivotz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, quatX, quatY, quatZ, quatW);
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t9 = 2.0f * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest.putFloat(destOffset, Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx))));
        dest.putFloat(destOffset + 4, Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy))));
        dest.putFloat(destOffset + 8, Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _quatBase = UnsafeOpsHolder.U.getLong(quat, UnsafeCopy.BB_ADDRESS_OFFSET) + quatOffset;
        Float3OpsKernelsAddress.rotateInverse_unsafe(_destBase, _srcBase, _quatBase);
        return dest;
    }

    public static java.nio.ByteBuffer rotateInverse_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _quatx = quat.getFloat(quatOffset);
        float _quaty = quat.getFloat(quatOffset + 4);
        float _quatz = quat.getFloat(quatOffset + 8);
        float _quatw = quat.getFloat(quatOffset + 12);
        float _t9 = 2.0f * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        float _t10 = 2.0f * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        float _t11 = 2.0f * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest.putFloat(destOffset, Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx))));
        dest.putFloat(destOffset + 4, Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy))));
        dest.putFloat(destOffset + 8, Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz))));
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateX_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset, _selfx);
        dest.putFloat(destOffset + 4, Math.fma(_selfy, _t1, -(_selfz * _t0)));
        dest.putFloat(destOffset + 8, Math.fma(_selfy, _t0, _selfz * _t1));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotY, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, angle, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotY, float pivotZ) {
        float _selfx = src.getFloat(srcOffset);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.getFloat(srcOffset + 4) - pivotY;
        float _t3 = src.getFloat(srcOffset + 8) - pivotZ;
        dest.putFloat(destOffset, _selfx);
        dest.putFloat(destOffset + 4, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotY)));
        dest.putFloat(destOffset + 8, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotZ)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateXAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3OpsKernelsAddress.rotateXAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateXAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _pivotz = pivot.getFloat(pivotOffset + 8);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.getFloat(srcOffset + 4) - _pivoty;
        float _t3 = src.getFloat(srcOffset + 8) - _pivotz;
        dest.putFloat(destOffset, _selfx);
        dest.putFloat(destOffset + 4, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivoty)));
        dest.putFloat(destOffset + 8, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivotz)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateY_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset, Math.fma(_selfx, _t1, _selfz * _t0));
        dest.putFloat(destOffset + 4, _selfy);
        dest.putFloat(destOffset + 8, Math.fma(_selfz, _t1, -(_selfx * _t0)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotZ);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotZ) {
        float _selfy = src.getFloat(srcOffset + 4);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.getFloat(srcOffset) - pivotX;
        float _t3 = src.getFloat(srcOffset + 8) - pivotZ;
        dest.putFloat(destOffset, Math.fma(_t2, _t1, Math.fma(_t3, _t0, pivotX)));
        dest.putFloat(destOffset + 4, _selfy);
        dest.putFloat(destOffset + 8, Math.fma(_t3, _t1, Math.fma(-_t2, _t0, pivotZ)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateYAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3OpsKernelsAddress.rotateYAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateYAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _selfy = src.getFloat(srcOffset + 4);
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivotz = pivot.getFloat(pivotOffset + 8);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.getFloat(srcOffset) - _pivotx;
        float _t3 = src.getFloat(srcOffset + 8) - _pivotz;
        dest.putFloat(destOffset, Math.fma(_t2, _t1, Math.fma(_t3, _t0, _pivotx)));
        dest.putFloat(destOffset + 4, _selfy);
        dest.putFloat(destOffset + 8, Math.fma(_t3, _t1, Math.fma(-_t2, _t0, _pivotz)));
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZ_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        float _selfx = src.getFloat(srcOffset);
        float _selfy = src.getFloat(srcOffset + 4);
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.putFloat(destOffset, Math.fma(_selfx, _t1, -(_selfy * _t0)));
        dest.putFloat(destOffset + 4, Math.fma(_selfx, _t0, _selfy * _t1));
        dest.putFloat(destOffset + 8, _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        Float3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _selfz = src.getFloat(srcOffset + 8);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.getFloat(srcOffset) - pivotX;
        float _t3 = src.getFloat(srcOffset + 4) - pivotY;
        dest.putFloat(destOffset, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX)));
        dest.putFloat(destOffset + 4, Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY)));
        dest.putFloat(destOffset + 8, _selfz);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZAround_unsafe(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset;
        Float3OpsKernelsAddress.rotateZAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.ByteBuffer rotateZAround_api(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        float _selfz = src.getFloat(srcOffset + 8);
        float _pivotx = pivot.getFloat(pivotOffset);
        float _pivoty = pivot.getFloat(pivotOffset + 4);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = src.getFloat(srcOffset) - _pivotx;
        float _t3 = src.getFloat(srcOffset + 4) - _pivoty;
        dest.putFloat(destOffset, Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx)));
        dest.putFloat(destOffset + 4, Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivoty)));
        dest.putFloat(destOffset + 8, _selfz);
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
